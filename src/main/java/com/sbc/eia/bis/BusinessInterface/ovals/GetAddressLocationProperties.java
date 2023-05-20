// $Id: GetAddressLocationProperties.java,v 1.18 2009/03/03 22:12:38 jr5306 Exp $

package com.sbc.eia.bis.BusinessInterface.ovals;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.sbc.bccs.idl.helpers.IDLUtil;
import com.sbc.bccs.utilities.exceptionbuilder.*;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.bis.lim.util.*;
import com.sbc.eia.idl.bis_types.*;
import com.sbc.eia.idl.types.*;
import com.sbc.eia.idl.lim_types.*;
import com.sbc.eia.idl.lim.*;
import com.sbc.ovals.*;
import com.sbc.eia.bis.lim.ovals.api.*;
import com.sbc.eia.bis.lim.transactions.RetrieveLocation.*;

/**
 * GetAddressLocationProperties get Address Properties values from ovals layers based on lat&long.
 * Also, get other properties by direct call to PSAM database.
 * Creation date: (4/13/01 12:15:27 PM)
 * @author: Donald W. Lee - Local
 */
public class GetAddressLocationProperties
{
	ProviderLocationPropertyBuilder locProp = null;
	
	static private DataSource ds = null;									// ORACLE data source
	private Connection dbConn   = null;

	LIMBase limBase = null;
	OvalsRetrieveLocReq ovalsRequest = null;
	OvalsApi ovalsApi = null;
	private String ruleFile =null;
	private ExceptionBuilderResult exBldReslt = null;

	private String GIS_SOURCE_URL = null;
	private String GIS_SOURCE_NAME = null;
	private String GIS_USERID = null;
	private String GIS_PSWD = null;

	//
	//  Hashtable for the conflation attributes.
	//
	java.util.Hashtable myOfcHash = new java.util.Hashtable();

	private String latLongId = null;
	
	private static ArrayList SBCStates = new ArrayList ();
	private static ArrayList SBCTMSA = new ArrayList ();
	private static ArrayList layerNames = new ArrayList ();
 
 	// final static String Tag_CoSwitchSuperPop = "SBCT SuperPop.CLLI11";   
	final static String Tag_DomSwitchPop = "GDT WC Boundary.DOMSWITCH"; 
	final static String Tag_HorizontalCoordinate = "Rate Center.H";   
	final static String Tag_LataCode = "Lata.LATA";	   
	final static String Tag_LataName = "Lata.NAME";   
	// localProviderAbbreviatedName	ovals_short_owner_name.SHORT_OWNER_NAME	   
	// final static String Tag_LocalProviderExchangeCode = "GDT WC Boundary.EXCHANGE";
	// final static String Tag_LocalProviderServingOfficeCode = "GDT WC Boundary.LSO";
	final static String Tag_LocalProviderName = "GDT WC Boundary.OCNAME";   
	final static String Tag_LocalProviderNumber = "GDT WC Boundary.OCN";	   
	final static String Tag_MsaCode = "MKT_MSA.MSA_CODE";   
	final static String Tag_MsaName = "MKT_MSA.MSA_NAME";   
	// nearestSbcColo TO_COLO_CLLI11   	   
	// nearestDistanceColoToCo	TO_COLO_DIST    	   
	// nearestDistanceSuperPopToCo	TO_SUPERPOP_DIST    	   
	// nearestSbcCoSuperPop		TO_SUPERPOP_CLLI11    	   
	// nearestSbcCoWirecenter	TO_COLO_CLLI8  	   
	// rz ??? final static String Tag_RateCenterCode = "Rate Center.CODE";   
	final static String Tag_RateCenterCode = "Rate Center.ABBR";   
	// final static String Tag_SbcColoLsoCode = "GDT WC Boundary.LSO";   
	final static String Tag_SbcColoWirecenter = "SBCT Colocated WC.CLLI_11";	   
	// sbcServingOfficeCode	N/A	OVALS_LSO.LSO	   
	final static String Tag_SbcServingOfficeWirecenter = "GDT WC Boundary.CLLI_8";  
	// switchVoiceSuperPop		PSAMDEV.OVALS_MSA.msa_superpop_clli_11	   
	// switchDataSuperPop		PSAMDEV.OVALS_MSA.msa_cbx_clli_11	   
	// switchSuperPopAddress		Aggregate	   
	// UnfieldedAddress		Aggregate	   
	// addressLines[0]		PSAMDEV.OVALS_CLLI_11_DETAILS.STREET	   
	// city		PSAMDEV.OVALS_CLLI_11_DETAILS.CITY	   
	// state		PSAMDEV.OVALS_CLLI_11_DETAILS.STATE	   
	// postalCode		PSAMDEV.OVALS_CLLI_11_DETAILS.ZIP	   
	final static String Tag_TarCode = "SBCT Tax Area.TAR_CODE";   
	final static String Tag_VerticalCoordinate = "Rate Center.V"; 

	// private String Val_CoSwitchSuperPop				= "";
	private String Val_DomSwitchPop					= "";
	private String Val_HorizontalCoordinate 	      	= "";
	private String Val_LataCode       					= "";
	private String Val_LataName       					= "";
	private String Val_LocalProviderAbbreviatedName	= "";
	// private String Val_LocalProviderExchangeCode		= "";
	// private String Val_LocalProviderServingOfficeCode	= "";	
	private String Val_LocalProviderName				= "";
	private String Val_LocalProviderNumber				= "";
	private String Val_MsaCode           			    = "";
	private String Val_MsaName           	        	= "";
	private String Val_NearestSbcColo					= "";	
	private String Val_NearestDistanceColoToCo			= "";	   
	private String Val_NearestDistanceSuperPopToCo		= "";	
	private String Val_NearestSbcCoSuperPop			= "";		 
	private String Val_NearestSbcCoWirecenter			= "";
	private String Val_RateCenterCode 					= "";
	// private String Val_SbcColoLsoCode 					= "";  
	private String Val_SbcColoWirecenter				= "";
	private String Val_SbcServingOfficeCode			= "";
	private String Val_SbcServingOfficeWirecenter		= ""; 
	private String Val_SwitchVoiceSuperPop				= "";   
	private String Val_SwitchDataSuperPop 				= "";  
	private Address Val_SwitchSuperPopAddress			= new Address ();
	private String Val_TarCode                     	= "";
	private String Val_VerticalCoordinate          	= "";
	private String Val_COT          					= "";
	private String Val_State							= "";
		 

/**
 * Constructor for GetAddressLocationProperties.
 * @param lim_base LIMBase
 * @param request OvalsRetrieveLocReq
 * @param lotLong String
 * @param ovals OvalsApi
 */
public GetAddressLocationProperties (LIMBase lim_base, OvalsRetrieveLocReq request, String lotLong, OvalsApi ovals) 
{
	limBase = lim_base;
	ruleFile = (String) limBase.getPROPERTIES().get(LIMTag.CSV_FileName_OVALS);
	ovalsRequest = request;
	latLongId = lotLong;
	ovalsApi = ovals;
		
	GIS_SOURCE_URL = (String)limBase.getPROPERTIES().get("GIS_DATA_SOURCE_URL");
	GIS_SOURCE_NAME = (String)limBase.getPROPERTIES().get("GIS_DATA_SOURCE_NAME");
	GIS_USERID = (String)limBase.getPROPERTIES().get("GIS_USERID");
	GIS_PSWD = (String)limBase.getPROPERTIES().get("GIS_PSWD");
	
	SBCStates.add ("MO");
	SBCStates.add ("OK"); 
	SBCStates.add ("KS");
	SBCStates.add ("TX");
	SBCStates.add ("AR");
	SBCStates.add ("IL");
	SBCStates.add ("IN");
	SBCStates.add ("MI");
	SBCStates.add ("OH");
	SBCStates.add ("WI");
	SBCStates.add ("CA");
	SBCStates.add ("NV");
	SBCStates.add ("CT");
	
	SBCTMSA.add ("0520");
	SBCTMSA.add ("0720");
	SBCTMSA.add ("0875");
	SBCTMSA.add ("1120");
	SBCTMSA.add ("1280");
	SBCTMSA.add ("1520");
	SBCTMSA.add ("1640");
	SBCTMSA.add ("1840");
	SBCTMSA.add ("2080");
	SBCTMSA.add ("2680");
	SBCTMSA.add ("3600");
	SBCTMSA.add ("4120");
	SBCTMSA.add ("4520");
	SBCTMSA.add ("4920");
	SBCTMSA.add ("5000");
	SBCTMSA.add ("5015");
	SBCTMSA.add ("5120");
	SBCTMSA.add ("5360");
	SBCTMSA.add ("5380");
	SBCTMSA.add ("5560");
	SBCTMSA.add ("5600");
	SBCTMSA.add ("5640");
	SBCTMSA.add ("5720");
	SBCTMSA.add ("5960");
	SBCTMSA.add ("6160");
	SBCTMSA.add ("6200");
	SBCTMSA.add ("6440");
	SBCTMSA.add ("6640");
	SBCTMSA.add ("7160");
	SBCTMSA.add ("7600");
	SBCTMSA.add ("8280");
	SBCTMSA.add ("8520");
	SBCTMSA.add ("8840");
	SBCTMSA.add ("8960");
	SBCTMSA.add ("1640");
	SBCTMSA.add ("0080");
	
	layerNames.add ("Lata");
	layerNames.add ("MKT_MSA");
	layerNames.add ("SBCT Colocated WC");
	layerNames.add ("SBCT Tax Area");
	layerNames.add ("Rate Center");
	layerNames.add ("GDT WC Boundary");
	// layerNames.add ("SBCT SuperPop");
}

/**
 * Create a ProviderLocationPropertyBuilder object.
 * Creation date: (4/13/01 12:20:04 PM)
 * @return ProviderLocationPropertyBuilder
 * @param providerLocProp ProviderLocationPropertyBuilder
 * @exception InvalidData An input parameter contained invalid data.
 * @exception AccessDenied Access to the specified domain object or information is not allowed.
 * @exception BusinessViolation The attempted action violates a business rule.
 * @exception ObjectNotFound The desired domain object could not be found.
 * @exception SystemFailure The method could not be completed due to system level errors.
 * @exception DataNotFound No data found.
 * @exception NotImplemented The method has not been implemented.
 */
public ProviderLocationPropertyBuilder getAddressLocationProperties (ProviderLocationPropertyBuilder providerLocProp, boolean retrieveCOT)
throws InvalidData, 
	   AccessDenied, 
	   BusinessViolation,
	   ObjectNotFound, 
	   SystemFailure,
	   DataNotFound, 
       NotImplemented 
{	
	locProp = providerLocProp;
	Address address = locProp.getProviderLocationProperty().aServiceAddress.theValue();
		
	try 
	{ 
		Val_State = address.aFieldedAddress().aState.theValue().trim(); 
	} 
	catch (org.omg.CORBA.BAD_OPERATION e) {}
	
	//
	//  Get conflation layer data...It will take a while ...
	//
	try 
	{
		limBase.log(LogEventId.AUDIT_TRAIL,"GetAddressLocationProperties::getAddressLocationProperties()|GetAddressLocationProperties::getConflationLayerData()|PRE");
	
		getConflationLayerData( latLongId ) ;
	
		limBase.log(LogEventId.AUDIT_TRAIL,"GgetAddressLocationProperties::getAddressLocationProperties()|GetAddressLocationProperties::getConflationLayerData()|POST");
	}
	catch( SystemFailure e ) 
	{ 
		throw e;
	}
/*	
	Val_CoSwitchSuperPop	= (String) myOfcHash.get (Tag_CoSwitchSuperPop);
	limBase.log (LogEventId.DEBUG_LEVEL_2, Val_CoSwitchSuperPop + "-->Tag_CoSwitchSuperPop");
	locProp.setCoSwitchSuperPop (Val_CoSwitchSuperPop);
*/	
	Val_DomSwitchPop	= (String) myOfcHash.get (Tag_DomSwitchPop);
	limBase.log (LogEventId.DEBUG_LEVEL_2, Val_DomSwitchPop + "-->Tag_DomSwitchPop");
	locProp.setDomSwitchPop (Val_DomSwitchPop);
	
	Val_HorizontalCoordinate	= (String) myOfcHash.get (Tag_HorizontalCoordinate);
	limBase.log (LogEventId.DEBUG_LEVEL_2, Val_HorizontalCoordinate + "-->Tag_HorizontalCoordinate");
	locProp.setHorizontalCoordinate (Val_HorizontalCoordinate);
	
	Val_LataCode	= (String) myOfcHash.get (Tag_LataCode);
	limBase.log (LogEventId.DEBUG_LEVEL_2, Val_LataCode + "-->Tag_LataCode");
	locProp.setLataCode (Val_LataCode);
	
	Val_LataName	= (String) myOfcHash.get (Tag_LataName);
	limBase.log (LogEventId.DEBUG_LEVEL_2, Val_LataName + "-->Tag_LataName");
	locProp.setLataName (Val_LataName);
/*	
	Val_LocalProviderExchangeCode	= (String) myOfcHash.get (Tag_LocalProviderExchangeCode);
	limBase.log (LogEventId.DEBUG_LEVEL_2, Val_LocalProviderExchangeCode + "-->Tag_LocalProviderExchangeCode");
	locProp.setLocalProviderExchangeCode (Val_LocalProviderExchangeCode);
*/
/*	
	Val_LocalProviderServingOfficeCode	= (String) myOfcHash.get (Tag_LocalProviderServingOfficeCode);
	limBase.log (LogEventId.DEBUG_LEVEL_2, Val_LocalProviderServingOfficeCode + "-->Tag_CLocalProviderServingOfficeCode");
	locProp.setLocalProviderServingOfficeCode (Val_LocalProviderServingOfficeCode);
*/	
	Val_LocalProviderName	= (String) myOfcHash.get (Tag_LocalProviderName);
	limBase.log (LogEventId.DEBUG_LEVEL_2, Val_LocalProviderName + "-->Tag_LocalProviderName");
	locProp.setLocalProviderName (Val_LocalProviderName);
	
	Val_LocalProviderNumber	= (String) myOfcHash.get (Tag_LocalProviderNumber);
	limBase.log (LogEventId.DEBUG_LEVEL_2, Val_LocalProviderNumber + "-->Tag_LocalProviderNumber");
	locProp.setLocalProviderNumber (Val_LocalProviderNumber);
	
	Val_MsaCode	= (String) myOfcHash.get (Tag_MsaCode);
	limBase.log (LogEventId.DEBUG_LEVEL_2, Val_MsaCode + "-->Tag_MsaCode");
	locProp.setMsaCode (Val_MsaCode);
	
	Val_MsaName	= (String) myOfcHash.get (Tag_MsaName);
	limBase.log (LogEventId.DEBUG_LEVEL_2, Val_MsaName + "-->Tag_MsaName");
	locProp.setMsaName (Val_MsaName);
	
	Val_RateCenterCode	= (String) myOfcHash.get (Tag_RateCenterCode);
	limBase.log (LogEventId.DEBUG_LEVEL_2, Val_RateCenterCode + "-->Tag_RateCenterCode");
	locProp.setRateCenterCode (Val_RateCenterCode);
/*	
	Val_SbcColoLsoCode	= (String) myOfcHash.get (Tag_SbcColoLsoCode);
	limBase.log (LogEventId.DEBUG_LEVEL_2, Val_SbcColoLsoCode + "-->Tag_SbcColoLsoCode");
	locProp.setSbcColoLsoCode (Val_SbcColoLsoCode);
*/	
	Val_SbcColoWirecenter	= (String) myOfcHash.get (Tag_SbcColoWirecenter);
	limBase.log (LogEventId.DEBUG_LEVEL_2, Val_SbcColoWirecenter + "-->Tag_SbcColoWirecenter");
	locProp.setSbcColoWirecenter (Val_SbcColoWirecenter);
	
	Val_SbcServingOfficeWirecenter	= (String) myOfcHash.get (Tag_SbcServingOfficeWirecenter);
	limBase.log (LogEventId.DEBUG_LEVEL_2, Val_SbcServingOfficeWirecenter + "-->Tag_SbcServingOfficeWirecenter");
	locProp.setSbcServingOfficeWirecenter (Val_SbcServingOfficeWirecenter);
	
	Val_TarCode	= (String) myOfcHash.get (Tag_TarCode);
	limBase.log (LogEventId.DEBUG_LEVEL_2, Val_TarCode + "-->Tag_TarCode");
	locProp.setTarCode (Val_TarCode);
	
	Val_VerticalCoordinate	= (String) myOfcHash.get (Tag_VerticalCoordinate);
	limBase.log (LogEventId.DEBUG_LEVEL_2, Val_VerticalCoordinate + "-->Tag_VerticalCoordinate");
	locProp.setVerticalCoordinate (Val_VerticalCoordinate);
	
	try
	{
		limBase.log (LogEventId.REMOTE_CALL, "PSAM DB", "PSAM DB", "OVALSQMS", "PSAM DB");
		getPSAMdbInfo();
		
		if (retrieveCOT || ovalsRequest.locationPropertiesRequested.isRetrieveCentralOfficeType ())
		{
			getCOTValue ();
			locProp.setCentralOfficeType (Val_COT, true);
		}
		limBase.log (LogEventId.REMOTE_RETURN, "PSAM DB", "PSAM DB", "OVALSQMS", "PSAM DB");
	}
	catch (SystemFailure e) 
	{
		limBase.log (LogEventId.INFO_LEVEL_2,"SystemFailure Exception in retrieving PSAM data." );	
		throw e;
	}

	return locProp;
}

/**
 * getConflationLayerData.
 * @param aLocation String
 * @exception BusinessViolation
 * @exception InvalidData
 * @exception SystemFailure
 * @exception AccessDenied
 * @exception ObjectNotFound
 * @exception NotImplemented
 * @exception DataNotFound
 */

public void getConflationLayerData( String aLocation )
	throws
		BusinessViolation,
		InvalidData,
		SystemFailure,
		AccessDenied,
		ObjectNotFound,
		NotImplemented,
		DataNotFound
{
	// myOfcHash.put (Tag_CoSwitchSuperPop, ""); 
	myOfcHash.put (Tag_DomSwitchPop, "");
	myOfcHash.put (Tag_HorizontalCoordinate, "");
	myOfcHash.put (Tag_LataCode, ""); 
	myOfcHash.put (Tag_LataName, "");
	// myOfcHash.put (Tag_LocalProviderExchangeCode, "");
	// myOfcHash.put (Tag_LocalProviderServingOfficeCode, "");
	myOfcHash.put (Tag_LocalProviderName, "");
	myOfcHash.put (Tag_LocalProviderNumber, ""); 
	myOfcHash.put (Tag_MsaCode, "");
	myOfcHash.put (Tag_MsaName, "");
	myOfcHash.put (Tag_RateCenterCode, "");
	// myOfcHash.put (Tag_SbcColoLsoCode, "");
	myOfcHash.put (Tag_SbcColoWirecenter, "");
	myOfcHash.put (Tag_SbcServingOfficeWirecenter, "");
	myOfcHash.put (Tag_TarCode, "");
	myOfcHash.put (Tag_VerticalCoordinate, "");
	
	limBase.log( LogEventId.INFO_LEVEL_1,"*** Conflation Id <" + aLocation + ">" );
	limBase.log( LogEventId.INFO_LEVEL_2,"Get Conflation Layers in Progress...It will take a while. # of Elements=" + myOfcHash.size() );

	try 
	{
		myOfcHash = ovalsApi.buildLayerInfoTable (layerNames, aLocation/*lotLongId*/, myOfcHash ); 
	}
	catch(SystemFailure e) 
	{
		limBase.log (LogEventId.INFO_LEVEL_2,"SystmeFailure Exception in getConflationLayerData " + e.getMessage());	
		throw e;
	}
	
	limBase.log (LogEventId.INFO_LEVEL_2,"Get Conflation Layers is done...# of Elements=" + myOfcHash.size() );
}

/**
 * getDBConnection.
 * @return a Connection object
 * @exception BusinessViolation
 * @exception InvalidData
 * @exception SystemFailure
 * @exception AccessDenied
 * @exception ObjectNotFound
 * @exception NotImplemented
 * @exception DataNotFound
 */

private Connection getDBConnection () 
	throws
		BusinessViolation,
		InvalidData,
		SystemFailure,
		AccessDenied,
		ObjectNotFound,
		NotImplemented,
		DataNotFound
{
	Connection dbConn = null;

	//
	//  DataSource, ds, will be null for the first time request. Subsequent calls will just
	//  get the connection from the WebSphere connection pool.
	//
	if (ds == null) 
	{	
		limBase.log (LogEventId.DEBUG_LEVEL_2, "Processing getDBConnection" );
	
		try 
		{
			// Get the initial Oracle database connection 
			Hashtable htParms = new Hashtable();
		
			// Use the WebSphere connection pooling resource
			limBase.log (LogEventId.DEBUG_LEVEL_2, "Connection Pooling URL=[" + GIS_SOURCE_URL + "]" );
			limBase.log (LogEventId.DEBUG_LEVEL_2, "Connection DataSourceName=[" + GIS_SOURCE_NAME +"]");
			htParms.put(javax.naming.Context.PROVIDER_URL, GIS_SOURCE_URL); 
			
			// Use IBM name services
			htParms.put(javax.naming.Context.INITIAL_CONTEXT_FACTORY,
			  	  "com.ibm.ejs.ns.jndi.CNInitialContextFactory");

			InitialContext initialDBContext = new javax.naming.InitialContext(htParms);
			
			limBase.log (LogEventId.DEBUG_LEVEL_2, "Lookup Connection Pooling DataSource...") ;

			ds = (DataSource)initialDBContext.lookup( GIS_SOURCE_NAME );
			
			if( ds == null ) 
			{
				exBldReslt =
					ExceptionBuilder.parseException(
						limBase.getCallerContext(),
						ruleFile,
						"",
						LIMTag.CSV_InternalError,
						"connect:",
						true,
						ExceptionBuilderRule.NO_DEFAULT,
						null,
						null,
						limBase, 
						"LIM",
						Severity.UnRecoverable,
						null);
			}
			
		} 
		catch (javax.naming.NamingException e) 
		{
			e.printStackTrace();
			exBldReslt =
				ExceptionBuilder.parseException(
					limBase.getCallerContext(),
					ruleFile,
					"",
					LIMTag.CSV_InternalError,
					"naming:" + e.getMessage(),
					true,
					ExceptionBuilderRule.NO_DEFAULT,
					null,
					null,
					limBase, 
					"LIM",
					Severity.UnRecoverable,
					null);
		} // endtry
	} // endif

	int reTryCnt = 0;
	while (true)
	{
		try 
		{
			if (reTryCnt == 1)
				dbConn.close();
			
			dbConn = ds.getConnection(GIS_USERID, GIS_PSWD );
			
			break;
		}
		catch(SQLException e) 
		{
			reTryCnt++;
	 		e.printStackTrace();
	 		limBase.log (LogEventId.DEBUG_LEVEL_1, "Failed on SQL getConnection-- " + e.getMessage());
	 		if (reTryCnt > 1)
	 		{
				exBldReslt =
					ExceptionBuilder.parseException(
						limBase.getCallerContext(),
						ruleFile,
						"",
						LIMTag.CSV_OracleError,
						"SQL Exception:" + e.getMessage(),
						true,
						ExceptionBuilderRule.NO_DEFAULT,
						null,
						null,
						limBase,
						"OVALS",
						Severity.UnRecoverable,
						null);
	 		}
		}		
		catch(Exception e) 
		{
			e.printStackTrace();
			exBldReslt =
				ExceptionBuilder.parseException(
					limBase.getCallerContext(),
					ruleFile,
					"",
					LIMTag.CSV_OracleError,
					"getConnection:" + e.getMessage(),
					true,
					ExceptionBuilderRule.NO_DEFAULT,
					null,
					null,
					limBase,
					"OVALS",
					Severity.UnRecoverable,
					null);
		}
	}
	return dbConn ; 
}

private void getPSAMdbInfo () 
throws InvalidData, 
	   AccessDenied, 
	   BusinessViolation,
	   ObjectNotFound, 
	   SystemFailure,
	   DataNotFound, 
	   NotImplemented 
{	
	limBase.log (LogEventId.DEBUG_LEVEL_2, "Processing getPSAMdbInfo" );
	
	try 
	{
		try 
		{
			dbConn = getDBConnection ();

			int reTryCnt = 0;
			java.sql.Statement stmt = null;
			while (true)
			{
				try
				{
					stmt = dbConn.createStatement();
					break;
				}
 				catch(SQLException e) 
 				{
	 				reTryCnt++;
	 				e.printStackTrace();
	 				limBase.log (LogEventId.DEBUG_LEVEL_1, "SQL exception in getPSAMdbInfo() -- " + e.getMessage());
	 				dbConn.close();
					
	 				if (reTryCnt == 2) 
	 				{
		 				exBldReslt =
							ExceptionBuilder.parseException(
								limBase.getCallerContext(),
								ruleFile,
								"",
								LIMTag.CSV_OracleError,
								"getPSAMdbInfo:" + e.getMessage(),
								true,
								ExceptionBuilderRule.NO_DEFAULT,
								null,
								null,
								limBase,
								"OVALS",
								Severity.UnRecoverable,
								null);
		 			}
					dbConn = getDBConnection ();
				}
			}
			
			ResultSet rsltSet  = null;
			String sqlCmd      = null;

//
// rz ---IMPORTENT--- original has different results for SBCT and ILEC	
			if (ovalsRequest.locationPropertiesRequested.isRetrieveLocalProviderAbbreviatedName())
			{
				try
				{
					sqlCmd = "select SHORT_OWNER_NAME from ovals_short_owner_name where STATE='" + Val_State + "'and OCN='"+ Val_LocalProviderNumber + "'";
					limBase.log (LogEventId.DEBUG_LEVEL_2, "sqlCmd <" + sqlCmd + ">" );
			
					rsltSet = stmt.executeQuery ( sqlCmd );
			
					if (rsltSet.next()) 
					{
						Val_LocalProviderAbbreviatedName = rsltSet.getString("SHORT_OWNER_NAME");
						if (Val_LocalProviderAbbreviatedName != null && !Val_LocalProviderAbbreviatedName.equals ("null"))
							locProp.setLocalProviderAbbreviatedName (Val_LocalProviderAbbreviatedName);
					}
					rsltSet.close();
				}
				catch (Exception e)
				{
					throw e;
				}
			}
				
			if (ovalsRequest.locationPropertiesRequested.isRetrieveNearestSbcColo() ||
				ovalsRequest.locationPropertiesRequested.isRetrieveNearestDistanceColoToCo() ||
				ovalsRequest.locationPropertiesRequested.isRetrieveNearestDistanceSuperPopToCo() ||
				ovalsRequest.locationPropertiesRequested.isRetrieveNearestSbcCoSuperPop() ||
				ovalsRequest.locationPropertiesRequested.isRetrieveNearestSbcCoWirecenter() )
			{		
				try
				{	
				sqlCmd = "select * from eplanet.ovals_co_distance where FROM_CO_CLLI8='" + Val_SbcServingOfficeWirecenter +"'" ;
				limBase.log( LogEventId.DEBUG_LEVEL_2, "sqlCmd <" + sqlCmd + ">" );

				rsltSet = stmt.executeQuery ( sqlCmd );
				if (rsltSet.next()) 
				{
					if (ovalsRequest.locationPropertiesRequested.isRetrieveNearestSbcColo())
					{
						Val_NearestSbcColo = rsltSet.getString("TO_COLO_CLLI11");
						limBase.log( LogEventId.DEBUG_LEVEL_2, "TO_COLO_CLLI11 = " + Val_NearestSbcColo );
						if (Val_NearestSbcColo == null || Val_NearestSbcColo.equals ("null"))
							Val_NearestSbcColo = "";
						locProp.setNearestSbcColo (Val_NearestSbcColo);
					}
					
					if (ovalsRequest.locationPropertiesRequested.isRetrieveNearestDistanceColoToCo())
					{
						Val_NearestDistanceColoToCo = rsltSet.getString("TO_COLO_DIST");
						limBase.log( LogEventId.DEBUG_LEVEL_2, "TO_COLO_DIST = " + Val_NearestDistanceColoToCo );
						if (Val_NearestDistanceColoToCo != null && !Val_NearestDistanceColoToCo.equals ("null"))
							locProp.setNearestDistanceColoToCo (Val_NearestDistanceColoToCo);
					}
					
					if (ovalsRequest.locationPropertiesRequested.isRetrieveNearestDistanceSuperPopToCo())
					{
						Val_NearestDistanceSuperPopToCo = rsltSet.getString("TO_SUPERPOP_DIST");
						limBase.log( LogEventId.DEBUG_LEVEL_2, "TO_SUPERPOP_DIST = " + Val_NearestDistanceSuperPopToCo );
						if (Val_NearestDistanceSuperPopToCo != null && ! Val_NearestDistanceSuperPopToCo.equals ("null"))
							locProp.setNearestDistanceSuperPopToCo (Val_NearestDistanceSuperPopToCo);
					}
					
					if (ovalsRequest.locationPropertiesRequested.isRetrieveNearestSbcCoSuperPop())
					{
						Val_NearestSbcCoSuperPop = rsltSet.getString("TO_SUPERPOP_CLLI11");
						limBase.log( LogEventId.DEBUG_LEVEL_2, "TO_SUPERPOP_CLLI11 = " + Val_NearestSbcCoSuperPop );
						if (Val_NearestSbcCoSuperPop != null && !Val_NearestSbcCoSuperPop.equals ("null"))
							locProp.setNearestSbcCoSuperPop (Val_NearestSbcCoSuperPop);
					}
					
					if (ovalsRequest.locationPropertiesRequested.isRetrieveNearestSbcCoWirecenter())
					{
						Val_NearestSbcCoWirecenter = rsltSet.getString("TO_COLO_CLLI8");
						limBase.log( LogEventId.DEBUG_LEVEL_2, "TO_COLO_CLLI8 = " + Val_NearestSbcCoWirecenter );
						if (Val_NearestSbcCoWirecenter != null && !Val_NearestSbcCoWirecenter.equals ("null"))
							locProp.setNearestSbcCoWirecenter (Val_NearestSbcCoWirecenter);
					}
				}
				rsltSet.close();
				}
				catch (Exception e)
				{
					throw e;
				}
			}

			if (ovalsRequest.locationPropertiesRequested.isRetrieveSbcServingOfficeCode())
			{
				try
				{
				sqlCmd = "select LSO from ovals_lso where CLLI_8='" + Val_SbcServingOfficeWirecenter + "'" ;
				limBase.log( LogEventId.DEBUG_LEVEL_2, "sqlCmd <" + sqlCmd + ">" );

				rsltSet = stmt.executeQuery ( sqlCmd );
			
				if (rsltSet.next()) 
				{
					Val_SbcServingOfficeCode = rsltSet.getString("LSO");
					limBase.log( LogEventId.DEBUG_LEVEL_2, "SBC Lso = " + Val_SbcServingOfficeCode );
					if (Val_SbcServingOfficeCode == null || Val_SbcServingOfficeCode.equals ("null"))
						Val_SbcServingOfficeCode = "";
					locProp.setSbcServingOfficeCode (Val_SbcServingOfficeCode);
				}
				rsltSet.close();
				}
				catch (Exception e)
				{
					throw e;
				}
			}
			
			if (ovalsRequest.locationPropertiesRequested.isRetrieveSwitchVoiceSuperPop() ||
				ovalsRequest.locationPropertiesRequested.isRetrieveSwitchDataSuperPop () ||
				ovalsRequest.locationPropertiesRequested.isRetrieveSwitchSuperPopAddress() )
			{
				try
				{
				sqlCmd = "select MSA_SUPERPOP_CLLI_11,MSA_CBX_CLLI_11 from psamdev.ovals_msa where MSA_CODE='" + Val_MsaCode + "' and STATE='"+ Val_State + "'" ;
				limBase.log( LogEventId.DEBUG_LEVEL_2, "sqlCmd <" + sqlCmd + ">" );

				rsltSet = stmt.executeQuery ( sqlCmd );
							
				if (rsltSet.next()) 
				{
					Val_SwitchVoiceSuperPop = rsltSet.getString("MSA_SUPERPOP_CLLI_11");
					limBase.log( LogEventId.DEBUG_LEVEL_2, "MSA_SUPERPOP_CLLI_11 = " + Val_SwitchVoiceSuperPop );
					if (Val_SwitchVoiceSuperPop == null || Val_SwitchVoiceSuperPop.equals ("null"))
						Val_SwitchVoiceSuperPop = "";
					if (ovalsRequest.locationPropertiesRequested.isRetrieveSwitchVoiceSuperPop())
						locProp.setSwitchVoiceSuperPop (Val_SwitchVoiceSuperPop);
							
					Val_SwitchDataSuperPop = rsltSet.getString("MSA_CBX_CLLI_11");
					limBase.log( LogEventId.DEBUG_LEVEL_2, "MSA_CBX_CLLI_11 = " + Val_SwitchDataSuperPop );
					if (Val_SwitchDataSuperPop== null || Val_SwitchDataSuperPop.equals ("null"))
						Val_SwitchDataSuperPop = "";
					if (ovalsRequest.locationPropertiesRequested.isRetrieveSwitchDataSuperPop())
						locProp.setSwitchDataSuperPop (Val_SwitchDataSuperPop);
					rsltSet.close();
				
					if (ovalsRequest.locationPropertiesRequested.isRetrieveSwitchSuperPopAddress())
					{
						sqlCmd = "select STREET,CITY,STATE,ZIP from psamdev.ovals_clli_11_details where CLLI_11='" + Val_SwitchVoiceSuperPop + "'" ;
						limBase.log( LogEventId.DEBUG_LEVEL_2, "sqlCmd <" + sqlCmd + ">" );

						rsltSet = stmt.executeQuery ( sqlCmd );
							
						if (rsltSet.next()) 
						{
							UnfieldedAddress ufAddr = new UnfieldedAddress(
							IDLUtil.toOpt (new String [1]),
							IDLUtil.toOpt(""),
							IDLUtil.toOpt(""),
							IDLUtil.toOpt(""),
							IDLUtil.toOpt(""),
							IDLUtil.toOpt(""),
							IDLUtil.toOpt(""),
							IDLUtil.toOpt(""),
							IDLUtil.toOpt(""),
							IDLUtil.toOpt(""),
							IDLUtil.toOpt(""),
							IDLUtil.toOpt(""),
							IDLUtil.toOpt(""),
							IDLUtil.toOpt(""),
							IDLUtil.toOpt(""),
							IDLUtil.toOpt(""),
							IDLUtil.toOpt(""),
							IDLUtil.toOpt(""),
							IDLUtil.toOpt(""),
							IDLUtil.toOpt(""),
							IDLUtil.toOpt("")
							);			
		
							Val_SwitchSuperPopAddress.aUnfieldedAddress (ufAddr);
							if (!(rsltSet.getString("STREET") == null || rsltSet.getString("STREET").equals("null")))
								Val_SwitchSuperPopAddress.aUnfieldedAddress().aAddressLines.theValue()[0] = rsltSet.getString("STREET");
							if (!(rsltSet.getString("CITY") == null || rsltSet.getString("CITY").equals("null")))
								Val_SwitchSuperPopAddress.aUnfieldedAddress().aCity.theValue (rsltSet.getString("CITY"));
							if (!(rsltSet.getString("STATE") == null || rsltSet.getString("STATE").equals("null")))
								Val_SwitchSuperPopAddress.aUnfieldedAddress().aState.theValue (rsltSet.getString("STATE"));
							if (!(rsltSet.getString("ZIP") == null || rsltSet.getString("ZIP").equals("null")))
								Val_SwitchSuperPopAddress.aUnfieldedAddress().aPostalCode.theValue (rsltSet.getString("ZIP"));
							
							try {
								limBase.log( LogEventId.DEBUG_LEVEL_2, "ADDRESS = " + 
									Val_SwitchSuperPopAddress.aUnfieldedAddress().aAddressLines.theValue()[0] + " " +
									Val_SwitchSuperPopAddress.aUnfieldedAddress().aCity.theValue () + " " +
									Val_SwitchSuperPopAddress.aUnfieldedAddress().aState.theValue () + " " +
									Val_SwitchSuperPopAddress.aUnfieldedAddress().aPostalCode.theValue ());
		}
							catch (Exception e)
							{
								limBase.log( LogEventId.DEBUG_LEVEL_2, "Some elements of the SuperPopAddress are NULL, address will not be printed");
							}
							locProp.setSwitchSuperPopAddress ((AddressOpt) IDLUtil.toOpt (AddressOpt.class, Val_SwitchSuperPopAddress));
						}
					}
				}
				}
				catch (Exception e)
				{
					throw e;
				}
			}

			try
			{
				rsltSet.close();
	 			stmt.close();
			}
			catch(Exception e) { /* ignored exception on close, nothing we can do */ }
 		}
 		catch(SystemFailure e) { 
			throw e;
 		}
 		catch(SQLException e) 
 		{
	 		e.printStackTrace();
		 	exBldReslt =
				ExceptionBuilder.parseException(
					limBase.getCallerContext(),
					ruleFile,
					"",
					LIMTag.CSV_OracleError,
					"getPSAMdbInfo:" + e.getMessage(),
					true,
					ExceptionBuilderRule.NO_DEFAULT,
					null,
					null,
					limBase, 
					"OVALS",
					Severity.UnRecoverable,
					null);	 		
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		 	exBldReslt =
				ExceptionBuilder.parseException(
					limBase.getCallerContext(),
					ruleFile,
					"",
					LIMTag.CSV_OracleError,
					"getPSAMdbInfo:" + e.getMessage(),
					true,
					ExceptionBuilderRule.NO_DEFAULT,
					null,
					null,
					limBase, 
					"OVALS",
					Severity.UnRecoverable,
					null);
		}
	} 
	finally 
	{
		try 
		{
			dbConn.close();
		}
		catch(SQLException e) { /* ignored exception on close, nothing we can do */ }
		catch(Exception e) { /* ignored exception on close, nothing we can do */ }
	}	
}

			
private void getCOTValue () 
	throws
		BusinessViolation,
		InvalidData,
		SystemFailure,
		AccessDenied,
		ObjectNotFound,
		NotImplemented,
		DataNotFound
{	
	limBase.log( LogEventId.DEBUG_LEVEL_2, "Processing getCOTValue..." );
	
	String SBCflag = "";
	long wcTypeId = -1;
	String wcType = null;
	String zone = null;
	long rateCenterId = -1;
	boolean checkCollocationStatus = false;
	
  	try 
  	{
		try 
		{
			dbConn = getDBConnection ();

			int reTryCnt = 0;
			java.sql.Statement stmt = null;
			while (true)
			{
				try
				{
					stmt = dbConn.createStatement();
					break;
				}
 				catch(SQLException e) 
 				{
	 				reTryCnt++;
	 				e.printStackTrace();
	 				limBase.log (LogEventId.DEBUG_LEVEL_1, "SQL exception in getCOTValue() -- " + e.getMessage());
	 				dbConn.close();

	 				if (reTryCnt == 2) 
	 				{ 
		 				exBldReslt =
							ExceptionBuilder.parseException(
								limBase.getCallerContext(),
								ruleFile,
								"",
								LIMTag.CSV_OracleError,
								"getCOTValue:" + e.getMessage(),
								true,
								ExceptionBuilderRule.NO_DEFAULT,
								null,
								null,
								limBase, 
								"OVALS",
								Severity.UnRecoverable,
								null);
		 			}
					dbConn = getDBConnection ();
				}
			}
			
			String sqlCmd = "select SBC_FLAG from PSAMDEV.OVALS_OWNER where OCN='" + Val_LocalProviderNumber + "'" ;
			limBase.log( LogEventId.DEBUG_LEVEL_2, "sqlCmd <" + sqlCmd + ">" );
			ResultSet rsltSet = stmt.executeQuery ( sqlCmd );
			if (rsltSet.next()) 
			{
				SBCflag = rsltSet.getString ("SBC_FLAG");
				if (SBCflag != null && SBCflag.startsWith("Y"))
					Val_COT = "In-Region";
				else {
					if (SBCStates.contains(Val_State)) {
						if (Val_MsaCode.equals("1840") || Val_MsaCode.equals("4120") || 
							Val_MsaCode.equals("1640") || Val_MsaCode.equals("0080"))
							checkCollocationStatus = true;
						else
							Val_COT = "In-Region Out-of-Franchise";
					}
					else {
						if (SBCTMSA.contains(Val_MsaCode)) 
							checkCollocationStatus = true;
						else
							Val_COT = "SBCT Outside of MSA";
					}
				}
								
				if (checkCollocationStatus == true) {	
					sqlCmd = "select RATE_CENTER_ID from OVALS_RATE_CENTER where RATE_CENTER_NAME='" + Val_RateCenterCode + "'  and STATE='" + Val_State + "'" ;
					limBase.log( LogEventId.DEBUG_LEVEL_2, "sqlCmd <" + sqlCmd + ">" );
					rsltSet = stmt.executeQuery ( sqlCmd );
					if (rsltSet.next()) 
					{
						rateCenterId = rsltSet.getLong ("RATE_CENTER_ID");
						sqlCmd = "select CURRENT_WC_RC_TYPE_ID from PSAMDEV.PSAM_AVAILABLE_PRODUCT where CLLI_8='" + Val_SbcServingOfficeWirecenter + "' and RATE_CENTER_ID=" + rateCenterId;
						limBase.log( LogEventId.DEBUG_LEVEL_2, "sqlCmd <" + sqlCmd + ">" );
						rsltSet = stmt.executeQuery ( sqlCmd );
						if (rsltSet.next()) 
						{
							wcTypeId = rsltSet.getLong ("CURRENT_WC_RC_TYPE_ID");
							sqlCmd = "select WC_RC_TYPE from PSAMDEV.PSAM_WC_RC_TYPE where WC_RC_TYPE_ID=" + wcTypeId;
							limBase.log( LogEventId.DEBUG_LEVEL_2, "sqlCmd <" + sqlCmd + ">" );
							rsltSet = stmt.executeQuery ( sqlCmd );
							if (rsltSet.next()) 
							{
								wcType = rsltSet.getString ("WC_RC_TYPE");
								if (wcType != null)
								{
									if (wcType.indexOf ("NON-COLLOCATED") >= 0)
									{
										sqlCmd = "select ZONE from psamdev.psam_wc_zone where CLLI_8='" + Val_SbcServingOfficeWirecenter + "'" ;
										limBase.log( LogEventId.DEBUG_LEVEL_2, "sqlCmd <" + sqlCmd + ">" );
										rsltSet = stmt.executeQuery ( sqlCmd );
										if (rsltSet.next()) 
										{
											zone = rsltSet.getString ("ZONE");
											if (zone != null && !zone.equals ("null"))
											{	
												if (zone.equals ("A"))
													Val_COT = "NON-COLLOCATED W/RATE CENTER Frame Relay / ATM Zone A";
												else  
													Val_COT = "SBCT NON-COLLOCATED"; 
											}
										}
										else
											Val_COT = "SBCT NON-COLLOCATED";
									}
									else if (wcType.indexOf ("COLLOCATED") >= 0)
										Val_COT = "SBCT COLLOCATED";
								}
							}
						}
					}
				}
			}
			try
			{
				rsltSet.close();
	 			stmt.close();
			}
			catch(Exception e) { /* ignored exception on close, nothing we can do */ }
 		}
 		catch(SystemFailure e) 
 		{
	 		throw e;
 		}
 		catch(SQLException e) 
 		{
	 		e.printStackTrace();
		 	exBldReslt =
				ExceptionBuilder.parseException(
					limBase.getCallerContext(),
					ruleFile,
					"",
					LIMTag.CSV_OracleError,
					"getCOTValue:" + e.getMessage(),
					true,
					ExceptionBuilderRule.NO_DEFAULT,
					null,
					null,
					limBase,
					"OVALS",
					Severity.UnRecoverable,
					null);
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		 	exBldReslt =
				ExceptionBuilder.parseException(
					limBase.getCallerContext(),
					ruleFile,
					"",
					LIMTag.CSV_OracleError,
					"getCOTValue:" + e.getMessage(),
					true,
					ExceptionBuilderRule.NO_DEFAULT,
					null,
					null,
					limBase,
					"OVALS",
					Severity.UnRecoverable,
					null);
		}

 	} 
  	finally 
  	{
	 	try
	 	{
	  		dbConn.close();
	  	}
	  	catch(SQLException e) { /* ignored exception on close, nothing we can do */ }
	  	catch(Exception e) { /* ignored exception on close, nothing we can do */ }
 	 }
 	 limBase.log( LogEventId.DEBUG_LEVEL_2, "COT=<" + Val_COT + ">");
}
}

