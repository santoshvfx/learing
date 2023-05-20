// $Id: OVALS.java,v 1.16 2007/03/02 18:49:13 rz7367 Exp $

package com.sbc.eia.bis.BusinessInterface.ovals;

import java.util.Hashtable;

import com.att.lms.bis.service.ovals.OvalsAvsqub;
import com.att.lms.bis.service.ovals.OvalsWrapper;
import com.sbc.bccs.idl.helpers.TN;
import com.sbc.bccs.utilities.Utility;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilder;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilderResult;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilderRule;
import com.sbc.eia.bis.BusinessInterface.Company;
import com.sbc.eia.bis.BusinessInterface.Host;
import com.sbc.eia.bis.BusinessInterface.InvalidCompanyException;
import com.sbc.eia.bis.BusinessInterface.InvalidStateException;
import com.sbc.eia.bis.BusinessInterface.NullDataException;
import com.sbc.eia.bis.BusinessInterface.Selector;
import com.sbc.eia.bis.BusinessInterface.State;
import com.sbc.eia.bis.BusinessInterface.location.LocationI;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.bis.lim.ovals.api.OvalsApi;
import com.sbc.eia.bis.lim.transactions.RetrieveLocation.ProviderLocationPropertyBuilder;
import com.sbc.eia.bis.lim.transactions.RetrieveLocation.RetrieveLocResp;
import com.sbc.eia.bis.lim.transactions.RetrieveLocation.RetrieveLocationBase;
import com.sbc.eia.bis.lim.util.LIMBase;
import com.sbc.eia.bis.lim.util.LIMBisContextManager;
import com.sbc.eia.bis.lim.util.LIMTag;
import com.sbc.eia.idl.bis_types.AccessDenied;
import com.sbc.eia.idl.bis_types.BusinessViolation;
import com.sbc.eia.idl.bis_types.DataNotFound;
import com.sbc.eia.idl.bis_types.InvalidData;
import com.sbc.eia.idl.bis_types.NotImplemented;
import com.sbc.eia.idl.bis_types.ObjectNotFound;
import com.sbc.eia.idl.bis_types.SystemFailure;
import com.sbc.eia.idl.lim.RetrieveLocationForAddressReturn;
import com.sbc.eia.idl.lim.RetrieveLocationForServiceReturn;
import com.sbc.eia.idl.lim.helpers.AddressHandlerOvals;
import com.sbc.eia.idl.lim_types.Address;
import com.sbc.eia.idl.lim_types.ProviderLocationProperties;
import com.sbc.eia.idl.types.LongOpt;
import com.sbc.eia.idl.types.SbcServiceProviderValue;
import com.sbc.eia.idl.types.Severity;
import com.sbc.eia.idl.types.StringOpt;

/**
 * This is the OVALS Host BusinessInterface class for LIM transactions.
 * Creation date: (12/4/01 11:31:31 AM)
 * @author: Rachel Zadok - Local
 */
public class OVALS extends Host implements LocationI
{
	private static final String HostList[] = null;
	private static OvalsApi ovals = null;
	private String ruleFile =null;
	private ExceptionBuilderResult exBldReslt = null;
	
/**
 * OVALS constructor.
 * Creation date: (12/4/01 11:47:26 AM)
 * @param aCompany Company
 * @param aUtility Utility
 * @param aProperties Hashtable
 */
public OVALS (Company aCompany, Utility aUtility, Hashtable aProperties)
{
	super (aCompany, aUtility, aProperties);
	ruleFile = (String) getProperties().get(LIMTag.CSV_FileName_OVALS);
}
/**
 * Send the AddressValidation request to OVALS and get the response.
 * Creation date: (12/5/01 5:21:51 PM)
 * @return RetrieveLocResp
 * @param request OvalsRetrieveLocReq
 * @param maxAddressLimit LongOpt
 * @param maxUnitLimit LongOpt
 * @exception BusinessViolation
 * @exception InvalidData
 * @exception SystemFailure
 * @exception AccessDenied
 * @exception ObjectNotFound
 * @exception NotImplemented
 * @exception DataNotFound
 */
protected RetrieveLocResp doAddressValidation(
	OvalsRetrieveLocReq request,
	LongOpt maxAddressLimit,
	LongOpt maxUnitLimit)
	throws
		BusinessViolation,
		InvalidData,
		SystemFailure,
		AccessDenied,
		ObjectNotFound,
		NotImplemented,
		DataNotFound 
	{
	int maxAddr = getMaxValue(maxAddressLimit);
	if (maxAddr <= 0)
		maxAddr = Integer.parseInt(getLimBase().limBase.OVALS_MAX_ADDRESS_LIMIT);
	int maxUnit = getMaxValue(maxUnitLimit);

	Address candidateAddress [] = new Address[maxAddr];
	String  matchCode [] = new String[maxAddr];
	String  matchDesc [] = new String[maxAddr];
	String addressIndicator [] = new String[maxAddr];
	
	boolean x = false;
	String errDesc = "";
	
	// First make connection to OVALS if needed
	try 
	{
		if (ovals == null) 
		{
			ovals = new OvalsApi((LIMBase) getLimBase().limBase);
		}
		
		getLimBase().limBase.log(LogEventId.INFO_LEVEL_2, "OVALS::doAddressValidation()|OvalsApi::checkConnection()|PRE");
		ovals.checkConnection ();
		getLimBase().limBase.log(LogEventId.INFO_LEVEL_2, "OVALS::doAddressValidation()|OvalsApi::checkConnection()|POST");		
	} 
	catch (SystemFailure e) 
	{
		throw e;
	}

	int candidateSize = 0;
	boolean findMatch = false;
	int nCandidate = -1;
	boolean poBox = false;
	String cityNm = null;
	String savLong[] = new String[maxAddr];
	String savLat[] = new String[maxAddr];

	try 
	{
		// First find if the request is for PO BOX
		//
		getLimBase().limBase.log(
			LogEventId.INFO_LEVEL_2,
			"Find if the input address is PO BOX.");
		if (request.getAddr() != null) 
		{
			if (request.getAddr().getCity() != null)
				cityNm = request.getAddr().getCity();
			if (request.getAddr().getStName() != null)
			{
				if (request.getAddr().getStName().equalsIgnoreCase("po box")
					|| request.getAddr().getStName().equalsIgnoreCase("p o box")
					|| request.getAddr().getStName().equalsIgnoreCase("p.o. box")
					|| request.getAddr().getStName().equalsIgnoreCase("p. o. box")) {
					poBox = true;
					getLimBase().limBase.log(LogEventId.INFO_LEVEL_2, "Address is PO BOX.");
				}
			}
		}

		try 
		{
			getLimBase().limBase.log(LogEventId.INFO_LEVEL_2, "Creating an address GeoObject.");
			x = ovals.findValidRange(request.getAddr(), poBox);
		} 
		catch (SystemFailure e) 
		{
			getLimBase().limBase.log(LogEventId.INFO_LEVEL_2, "Disconnecting from OVALS.");
			ovals.disconnect();
			ovals = null;
			throw e;
		}

		if (x == true) {
			candidateSize = Math.max(ovals.addressList.size(), maxAddr);
			// This for loop builds a range of addresses for selection
			//
			for (int n = 0; n < candidateSize; n++) {
				if (n == ovals.addressList.size())
					break;
					
				getLimBase().limBase.log(
					LogEventId.INFO_LEVEL_2,
					"getFeature for <" + n + "> candidate.");
				
				ovals.aFeature = ovals.addressList.getFeature(n);
				
				// Ignore features with Error matchcode.
				//
				if (ovals.aFeature.getValue("matchcode").toString().startsWith("E")) {

					errDesc = findErrDesc(ovals.aFeature.getValue("matchcode").toString());
					getLimBase().limBase.log(
						LogEventId.INFO_LEVEL_2,
						"Found Candidate with No Match: <"
							+ ovals.aFeature.getValue("matchcode").toString()
							+ ">");
					continue;
				}
				// If we get features with the same Longitude and Latitude => ignore them.
				//
				boolean nextCand = false;
				for (int kji = 0; kji <= nCandidate; kji++)
					if (savLong[kji].equals(ovals.aFeature.getValue("longitude").toString())
						&& savLat[kji].equals(ovals.aFeature.getValue("latitude").toString())) {
						getLimBase().limBase.log(
							LogEventId.INFO_LEVEL_2,
							"Same Longitude and Latitude were encountered, Transaction will continue.");
						nextCand = true;
						break;
					}
				if (nextCand)
					continue;

				getLimBase().limBase.log(LogEventId.INFO_LEVEL_2, "Calling buildFieldedAddress.");
				AddressHandlerOvals ahRet = ovals.buildAddress(poBox);

				nCandidate++;

				savLong[nCandidate] = ovals.aFeature.getValue("longitude").toString();
				savLat[nCandidate] = ovals.aFeature.getValue("latitude").toString();

				matchCode[nCandidate] = ovals.aFeature.getValue("matchcode").toString();
				matchDesc[nCandidate] = findDescCode(ovals.aFeature.getValue("matchcode").toString());
					
				addressIndicator[nCandidate] = ovals.aFeature.getValue("longitude")
											+ "@"
											+ ovals.aFeature.getValue("latitude");

				candidateAddress[nCandidate] = ahRet.getAddress();
				getLimBase().limBase.log(
					LogEventId.INFO_LEVEL_2,
					"ObjectId[" + nCandidate + "] = <" + addressIndicator[nCandidate] + ">");
				getLimBase().limBase.log(
					LogEventId.INFO_LEVEL_2,
					"MatchCode & Description[" + nCandidate + "] = <" + matchCode[nCandidate] + "< "
					+ matchDesc[nCandidate] + ">");

				if ((nCandidate + 1) == Math.min(ovals.addressList.size(), maxAddr))
					break;
			} // end loop candidateSize 
		} // end if x==true
		else {
			exBldReslt =
				ExceptionBuilder.parseException(
					getLimBase().limBase.getCallerContext(),
					ruleFile,
					"",
					LIMTag.CSV_AddressError,
					"Unable to Validate Address -- ",
					true,
					ExceptionBuilderRule.NO_DEFAULT,
					null,
					null,
					utility,
					"OVALS",
					Severity.UnRecoverable,
					null);
		}

		if (nCandidate == -1) {
			exBldReslt =
				ExceptionBuilder.parseException(
					getLimBase().limBase.getCallerContext(),
					ruleFile,
					"",
					LIMTag.CSV_AddressError,
					"Unable to Validate Address, Found Candidate with No Match. " + errDesc,
					true,
					ExceptionBuilderRule.NO_DEFAULT,
					null,
					null,
					utility,
					"OVALS",
					Severity.UnRecoverable,
					null);
		}
		candidateSize = nCandidate + 1;
		
		// ovals.disconnect ();

	} 
	catch (SystemFailure e) 
	{
		throw e;
	} 
	catch (ObjectNotFound e) 
	{
		throw e;
	} 
	catch (com.sbc.ovals.OvalsException e) 
	{
		ovals.disconnect ();
		ovals = null;
		exBldReslt =
			ExceptionBuilder.parseException(
				getLimBase().limBase.getCallerContext(),
				ruleFile,
				"",
				LIMTag.CSV_InternalError,
				"OVALS Exception - ValidateAddress -- " + e.getMessage(),
				true,
				ExceptionBuilderRule.NO_DEFAULT,
				null,
				null,
				utility,
				"LIM",
				Severity.UnRecoverable,
				null);
	}
	
	RetrieveLocResp locResp = null;
	
	if (candidateSize == 1) 
	{
        ProviderLocationPropertyBuilder propertyBuilder = new ProviderLocationPropertyBuilder (request.getLocationPropertiesRequested());
        propertyBuilder.setServiceAddress (candidateAddress[0]);
        propertyBuilder.setAddressMatchCode (matchCode[0], false);
        propertyBuilder.setAddressMatchCodeDescription (matchDesc[0], false);
   
        propertyBuilder.setLatitude (savLat[0]);
        propertyBuilder.setLongitude (savLong[0]);
        
        GetAddressLocationProperties locProps = null;
        boolean retrieveCOT = false;
        String busUnit = "";
        LIMBisContextManager contextManager = new LIMBisContextManager(getLimBase().limBase.getCallerContext());
        if (contextManager.getBusinessUnit() != null)
            busUnit = contextManager.getBusinessUnit().trim();
        
        if (busUnit.equals(SbcServiceProviderValue.SBCDATASERVICES))
        	retrieveCOT = true;
        try
        {
			if (retrieveProperties (request) || retrieveCOT)
  			{
  				locProps = new GetAddressLocationProperties ((LIMBase)utility, request, addressIndicator[0], ovals);
  				propertyBuilder = locProps.getAddressLocationProperties (propertyBuilder, retrieveCOT);
  			}
        }
        catch (SystemFailure e)
        {
			ovals.disconnect ();
			ovals = null;
        	throw e;
        }
		locResp = new OvalsHitResp (getLimBase().limBase, propertyBuilder);
	}
	else
		locResp = new OvalsListResp (getLimBase().limBase, candidateAddress, matchCode, matchDesc, savLat, savLong, candidateSize);
	

	locResp.setMaxAddresses(candidateSize);
	locResp.setMaxUnits(0);

	return locResp;
}

/**
 * Find the matching description for OVALS return code.
 * Creation date: (12/5/01 5:21:51 PM)
 * @return String
 * @param code String
 */

private String findDescCode (String code) 
{
	char a = ' ', b = ' ', c = ' ', d = ' ';
	if (code.length() > 0)
		a = code.charAt(0);
	if (code.length() > 1)
		b = code.charAt(1);
	if (code.length() > 2)
		c = code.charAt(2);
	if (code.length() > 3)
		d = code.charAt(3);

	String desc = "";
	if (a == 'S')
		desc = "Best Match. ";
	else if (a == 'A')
		desc += "Best Match to an alias. ";
	else if (a == 'D')
		desc += "Match is a small town with PO Boxes. ";
	else if (a == 'U')
		desc += "Match found in the USPS data. ";
	else if (a == 'T')
		desc += "Match to the street network file. ";
	else if (a == 'X')
		desc += "Match found was an intersection of two streets. ";
	else if (a == 'Y')
		desc += "Match found was an intersection of two streets with an alias. ";
	else if (a == 'A')
		desc = "No address was given, but valid ZIP. ";

		
	if (b == '1')
		desc += "ZIP was changed. ";
	else if (b == '2')
		desc += "City was changed. ";
	else if (b == '3')
		desc += "City and ZIP were changed. ";
	else if (b == '4')
		desc += "State was changed. ";
	else if (b == '5')
		desc += "State and ZIP were changed. ";
	else if (b == '6')
		desc += "State and City were changed. ";
	else if (b == '7')
		desc += "State, City and ZIP were changed. ";
	else if (b == '8')
		desc += "ZIP+4 was changed. ";
	else if (b == '9')
		desc += "ZIP and ZIP+4 were changed. ";
	else if (b == 'A')
		desc += "City and ZIP+4 were changed. ";
	else if (b == 'B')
		desc += "City, ZIP and ZIP+4 were changed. ";
	else if (b == 'C')
		desc += "State and ZIP+4 were changed. ";
	else if (b == 'D')
		desc += "State, City and ZIP+4 were changed. ";
	else if (b == 'E')
		desc += "State, City and ZIP+4 were changed. ";
	else if (b == 'F')
		desc += "State, City, ZIP and ZIP+4 were changed. ";

		
	if (c == '1')
		desc += "Street type was changed. ";
	else if (c == '2')
		desc += "Pre-directional was changed. ";
	else if (c == '3')
		desc += "Street type and Pre-directional were changed. ";
	else if (c == '4')
		desc += "Post-directional was changed. ";
	else if (c == '5')
		desc += "Street type and Post-directional were changed. ";
	else if (c == '6')
		desc += "Pre-directional and Post-directional were changed. ";
	else if (c == '7')
		desc += "Street type, Pre-directional and Post-directional were changed. ";
	else if (c == '8')
		desc += "Street name was changed. ";
	else if (c == '9')
		desc += "Street name and Street type were changed. ";
	else if (c == 'A')
		desc += "Street name and Pre-directional were changed. ";
	else if (c == 'B')
		desc += "Street name, Street type and Pre-directional were changed. ";
	else if (c == 'C')
		desc += "Street name and Post-directional were changed. ";
	else if (c == 'D')
		desc += "Street name, Street type and Post-directional were changed. ";
	else if (c == 'E')
		desc += "Street name, Pre-directional and Post-directional were changed. ";
	else if (c == 'F')
		desc += "Street name, Street type, Pre-directional and Post-directional were changed. ";
		
	return desc;	
}

/**
 * Find the matching description for OVALS return error code.
 * Creation date: (12/5/01 5:21:51 PM)
 * @return String
 * @param code String
 */

private String findErrDesc (String code) 
{
	String desc = code + " No Match. ";
	
	if (code.length() < 4)
		return desc;
		
	if (code.substring(1,4).equals("000"))
		desc += "LookupReord found no address.";
	else if (code.substring(1,4).equals("001"))
		desc += "Low level error.";
	else if (code.substring(1,4).equals("002"))
		desc += "Did not find GSD file for state.";
	else if (code.substring(1,4).equals("003"))
		desc += "Incorrect GSD file signature or version ID.";
	else if (code.substring(1,4).equals("004"))
		desc += "GSD file out of date. Only occurs when MatchMode is set to AB_MODE_CASS.";
	else if (code.substring(1,4).equals("010"))
		desc += "No city+state or ZIP Code found.";
	else if (code.substring(1,4).equals("011"))
		desc += "Input ZIP was not in the directory.";
	else if (code.substring(1,4).equals("012"))
		desc += "Input city was not in the directory.";
	else if (code.substring(1,4).equals("013"))
		desc += "Input city was not unique in the directory.";
	else if (code.substring(1,4).equals("014"))
		desc += "Out of licensed area.";
	else if (code.substring(1,4).equals("015"))
		desc += "GeoStan record count has been depleted and license has expired.";
	else if (code.substring(1,4).equals("020"))
		desc += "No matching streets found in directory.";
	else if (code.substring(1,4).equals("021"))
		desc += "No matching cross streets for an Intersection match.";
	else if (code.substring(1,4).equals("022"))
		desc += "No matching ranges.";
	else if (code.substring(1,4).equals("023"))
		desc += "Match is unresolved.";
	else if (code.substring(1,4).equals("024"))
		desc += "No matching ranges.";
	else if (code.substring(1,4).equals("025"))
		desc += "Too many possible cross streets or intersection matching.";
	else if (code.substring(1,4).equals("026"))
		desc += "No address found when attempting a multiline match.";
		
	return desc;	
}

/**
 * Return a list of Selectors of company/state combinations that this class supports.
 * Creation date: (12/4/01 11:49:47 AM)
 * @return Selector[]
 * @param util  an Utility object
 * @exception InvalidStateException
 * @exception NullDataException
 * @exception InvalidCompanyException
 */
public static Selector[] getCacheEntries (Utility util)
throws InvalidStateException, NullDataException, InvalidCompanyException
{
	util.log (LogEventId.INFO_LEVEL_2, "OVALS::getCacheEntries()");
	
	// Add entries to the HostFactory cache at start time to avoid long searches
	return new Selector[] {
		new Selector(new Company(Company.Company_SBCTelecom, State.getAnAnyState(), null, null),
			LocationI.LOCATION_INTERFACE_NAME, (OVALS.class).getName()),
	};
}
/**
 * Return a list of host subclasses of this class.
 * Creation date: (3/19/01 10:36:20 AM)
 * @return String[] an array of host subclasses
 * @param util Utility a logging utility
 */
public static String[] getHostList(Utility util)
{
	util.log(LogEventId.INFO_LEVEL_2, "OVALS::getHostList()");
	return HostList;
}
/**
 * Return the business interfaces that this class implements.
 * Creation date: (12/4/01 1:05:59 PM)
 * @return String[]
 * @param util Utility
 */
public static String[] getInterfaceList(Utility util)
{
	util.log (LogEventId.INFO_LEVEL_2, "OVALS::getInterfaceList()");
	return new String[] { LocationI.LOCATION_INTERFACE_NAME };
}
/**
 * Get the RetrieveLocationBase object.
 * Creation date: (3/21/01 3:21:40 PM)
 * @return RetrieveLocationBase
 */
protected RetrieveLocationBase getLimBase()
{
	return (RetrieveLocationBase) getPassThru();
}
/**
 * Extract the int value from a max-value LongOpt object.
 * Creation date: (3/26/01 2:42:08 PM)
 * @return int
 * @param max LongOpt
 */
 
protected int getMaxValue (LongOpt max)
{
	try
	{
		if (max != null)
			return max.theValue();
	}
	catch (org.omg.CORBA.BAD_OPERATION bo) {}

	return 0;
}
/**
 * Return a list of supported companies.
 * Creation date: (12/4/01 1:05:59 PM)
 * @return Company[]
 * @param util  an Utility object
 * @exception InvalidStateException
 * @exception NullDataException
 * @exception InvalidCompanyException
 */
public static Company[] getSupportedCompanies(Utility util)
throws InvalidStateException, NullDataException, InvalidCompanyException
{
	util.log(LogEventId.INFO_LEVEL_2, "OVALS::getSupportedCompanies()");
	return new Company[] {
		new Company(Company.Company_SBCTelecom, State.getAnAnyState(), null, null)
		};
}

/**
 * @return boolean
 * @param ovalsRequest OvalsRetrieveLocReq
 */

private boolean retrieveProperties (OvalsRetrieveLocReq ovalsRequest)
{
	
	if (ovalsRequest.locationPropertiesRequested.isRetrieveDomSwitchPop() ||
		ovalsRequest.locationPropertiesRequested.isRetrieveHorizontalCoordinate() ||
		ovalsRequest.locationPropertiesRequested.isRetrieveLataCode() ||
		ovalsRequest.locationPropertiesRequested.isRetrieveLataName() ||		
		ovalsRequest.locationPropertiesRequested.isRetrieveLocalProviderName() ||
		ovalsRequest.locationPropertiesRequested.isRetrieveLocalProviderNumber() ||
		ovalsRequest.locationPropertiesRequested.isRetrieveMsaCode() ||
		ovalsRequest.locationPropertiesRequested.isRetrieveMsaName() ||
		ovalsRequest.locationPropertiesRequested.isRetrieveRateCenterCode() ||		
		ovalsRequest.locationPropertiesRequested.isRetrieveSbcColoWirecenter() ||
		ovalsRequest.locationPropertiesRequested.isRetrieveSbcServingOfficeWirecenter() ||
		ovalsRequest.locationPropertiesRequested.isRetrieveTarCode() ||
		ovalsRequest.locationPropertiesRequested.isRetrieveVerticalCoordinate() ||
		ovalsRequest.locationPropertiesRequested.isRetrieveLocalProviderAbbreviatedName() ||
		ovalsRequest.locationPropertiesRequested.isRetrieveNearestSbcColo() ||
		ovalsRequest.locationPropertiesRequested.isRetrieveNearestDistanceColoToCo() ||
		ovalsRequest.locationPropertiesRequested.isRetrieveNearestDistanceSuperPopToCo() ||
		ovalsRequest.locationPropertiesRequested.isRetrieveNearestSbcCoSuperPop() ||
		ovalsRequest.locationPropertiesRequested.isRetrieveNearestSbcCoWirecenter() ||
		ovalsRequest.locationPropertiesRequested.isRetrieveSbcServingOfficeCode() ||
		ovalsRequest.locationPropertiesRequested.isRetrieveSwitchVoiceSuperPop() ||
		ovalsRequest.locationPropertiesRequested.isRetrieveSwitchDataSuperPop () ||
		ovalsRequest.locationPropertiesRequested.isRetrieveSwitchSuperPopAddress() )
		return true;
	return false;
}

/**
 * Retrieve location information by address.
 * Creation date: (12/4/01 1:05:59 PM)
 * @return RetrieveLocationForAddressReturn
 * @param aAddress AddressHandler
 * @param aMaximumBasicAddressReturnLimit LongOpt
 * @param aMaximumUnitReturnLimit LongOpt
 * @exception InvalidData
 * @exception AccessDenied
 * @exception BusinessViolation
 * @exception DataNotFound
 * @exception SystemFailure
 * @exception NotImplemented
 * @exception ObjectNotFound
 */
public RetrieveLocationForAddressReturn retrieveLocationForAddress(
	Address aAddress, 
	ProviderLocationProperties[] locationPropertiesToGet,
	LongOpt aMaximumBasicAddressReturnLimit, 
	LongOpt aMaximumUnitReturnLimit,
	StringOpt aExchangeCode)
throws InvalidData, AccessDenied, BusinessViolation, DataNotFound, SystemFailure, NotImplemented, ObjectNotFound
{
	/*
	getLimBase().limBase.log(LogEventId.AUDIT_TRAIL, "OVALS::retrieveLocationForAddress()|OVALS::doAddressValidation()|PRE");
	
	RetrieveLocationForAddressReturn result = null;

	try
	{
		AddressHandlerOvals addrOvals = new AddressHandlerOvals (aAddress);
		OvalsRetrieveLocReq request = new OvalsRetrieveLocReq(getLimBase().limBase, addrOvals, locationPropertiesToGet);
		
		result =
			((RetrieveLocResp)(doAddressValidation( request,
				aMaximumBasicAddressReturnLimit, aMaximumUnitReturnLimit ))).
			toAddressReturn();
	}
	catch (InvalidData e)
	{
		throw e;
	}
	catch (AccessDenied e)
	{
		throw e;
	}
	catch (BusinessViolation e)
	{
		throw e;
	}
	catch (DataNotFound e)
	{
		throw e;
	}
	catch (SystemFailure e)
	{
		throw e;
	}
	catch (NotImplemented e)
	{
		throw e;
	}
	catch (ObjectNotFound e)
	{
		throw e;
	}
	catch (AddressHandlerException e) 
	{

	}
	*/
	RetrieveLocationForAddressReturn result = null;

	LIMBase limBase = getLimBase().limBase;

	OvalsWrapper wrapper = new OvalsWrapper(limBase,new OvalsAvsqub(limBase), null,null, null);
	return wrapper.retrieveLocationForAddressAvsqub(
			aAddress,locationPropertiesToGet,
			aMaximumBasicAddressReturnLimit,
			aMaximumUnitReturnLimit,aExchangeCode);
}
/**
 * Retrieve location information by telephone number.
 * Creation date: (12/4/01 1:05:59 PM)
 * @return RetrieveLocationForServiceReturn
 * @param aService TN
 * @param aMaximumBasicAddressReturnLimit LongOpt
 * @param aMaximumUnitReturnLimit LongOpt
 * @exception InvalidData
 * @exception AccessDenied
 * @exception BusinessViolation
 * @exception DataNotFound
 * @exception SystemFailure
 * @exception NotImplemented
 * @exception ObjectNotFound
 */
public RetrieveLocationForServiceReturn retrieveLocationForService(
	TN aService, ProviderLocationProperties[] locationPropertiesToGet, LongOpt aMaximumBasicAddressReturnLimit, LongOpt aMaximumUnitReturnLimit)
throws InvalidData, AccessDenied, BusinessViolation, DataNotFound, SystemFailure, NotImplemented, ObjectNotFound
{
	RetrieveLocationForServiceReturn result = null;

	exBldReslt =
				ExceptionBuilder.parseException(
					getLimBase().limBase.getCallerContext (),
					ruleFile,
					"",
					LIMTag.CSV_InternalError,
					"Not Implemented", 
					true,
					ExceptionBuilderRule.NO_DEFAULT,
					null,
					null,
					utility,
					"LIM",
					Severity.UnRecoverable,
					null);
	return result;
}

}
