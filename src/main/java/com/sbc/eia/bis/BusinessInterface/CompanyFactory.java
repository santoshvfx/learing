// $Id: CompanyFactory.java,v 1.3 2003/03/19 22:57:56 cm2159 Exp $

package com.sbc.eia.bis.BusinessInterface;

import java.util.Hashtable;
import com.sbc.eia.bis.framework.logging.LogEventId;
import java.io.*;
import java.util.Properties;
import com.sbc.eia.idl.exception_types.*;
import com.sbc.bccs.utilities.PropertiesFileLoader;

/**
 * CompanyFactory is the class which defines a company factory.
 * Creation date: (3/13/01 2:39:06 PM)
 * @author: Creighton Malet
 */
public class CompanyFactory {
	private String[] hostList = null;
	private Hashtable companyCache = new Hashtable();
	private HostFactory hostFactory = null;
	private com.sbc.bccs.utilities.Utility utility = null;
	private Properties stateToCompany = new Properties();
	private java.util.Hashtable properties = null;
/**
 * Class constructor.
 */
private CompanyFactory() {
}
/**
 * Class constructor accepting host list, utility and properties.
 * Creation date: (3/13/01 5:52:00 PM)
 * @param aHostList java.lang.String[]
 * @param aState com.sbc.eia.bis.BusinessInterface.State
 * @param aUtility com.sbc.bccs.utilities.Utility
 * @param aProperties java.util.Hashtable
 * @exception com.sbc.eia.bis.BusinessInterface.NullDataException. A required input parameter was null.
 * @exception com.sbc.eia.bis.BusinessInterface.SeriousFailureException. A serious failure occurred.
 */
public CompanyFactory(String aHostList[], com.sbc.bccs.utilities.Utility aUtility, java.util.Hashtable aProperties)
	throws NullDataException, SeriousFailureException
{
	if (aHostList == null)
		throw new NullDataException(ExceptionCode.ERR_BSI_BUSINESS_NULL_DATA, "Null HostList in CompanyFactory::CompanyFactory()");
	if (aUtility == null)
		throw new NullDataException(ExceptionCode.ERR_BSI_BUSINESS_NULL_DATA, "Null Utility in CompanyFactory::CompanyFactory()");
	if (aProperties == null)
		throw new NullDataException(ExceptionCode.ERR_BSI_BUSINESS_NULL_DATA, "Null Properties in CompanyFactory::CompanyFactory()");
		
	hostFactory = new HostFactory(aHostList, aUtility, aProperties);
	hostList = aHostList;
	utility = aUtility;
	properties = aProperties;
	loadStateToCompany();
}
/**
 * Derives a company code from a state.
 * @return java.lang.String
 * @param aState com.sbc.eia.bis.BusinessInterface.State
 * @exception com.sbc.eia.bis.BusinessInterface.UnknownStateException. An unknown state was supplied.
 * @exception com.sbc.eia.bis.BusinessInterface.InvalidCompanyException. An invalid company code was supplied.
 */
private String determineCompany(State aState)
	throws UnknownStateException, InvalidCompanyException
{
	String tmpCode = null;
	if ((tmpCode = (String)stateToCompany.get(aState.getCode())) != null)
		return tmpCode;
	throw new UnknownStateException(ExceptionCode.ERR_BSI_BUSINESS_UNKNOWN_STATE,
		"State " + aState.getCode() + " not mapped to a company"); 
}
/**
 * Called by the garbage collector.
 */
public void finalize()
{
	companyCache.clear();
	stateToCompany.clear();
}
/**
 * Returns a company derived from a service center.
 * @return com.sbc.eia.bis.BusinessInterface.Company
 * @param aServiceCenter com.sbc.eia.bis.BusinessInterface.ServiceCenter
 * @exception com.sbc.eia.bis.BusinessInterface.NullDataException. A required input parameter was null.
 * @exception com.sbc.eia.bis.BusinessInterface.UnknownServiceCenterException. An unknown service center was supplied.
 * @exception com.sbc.eia.bis.BusinessInterface.InvalidCompanyException. An invalid company was used.
 */
public Company getCompany(ServiceCenter aServiceCenter)
	throws NullDataException, UnknownServiceCenterException, InvalidCompanyException
{
	try {
		return getFromCache(determineCompany(aServiceCenter.getState()), aServiceCenter.getState());
	}
	catch (UnknownStateException e)
	{
		throw new UnknownServiceCenterException(ExceptionCode.ERR_BSI_BUSINESS_UNKNOWN_SERVICE_CENTER,
			"ServiceCenter " + aServiceCenter.getState().getCode() + " not mapped to a company");
	}
}
/**
 * Returns a company derived from a state.
 * @return com.sbc.eia.bis.BusinessInterface.Company
 * @param aState com.sbc.eia.bis.BusinessInterface.State
 * @exception com.sbc.eia.bis.BusinessInterface.NullDataException. A required input parameter was null.
 * @exception com.sbc.eia.bis.BusinessInterface.UnknownStateException. An unknown state was supplied.
 * @exception com.sbc.eia.bis.BusinessInterface.InvalidCompanyException. An invalid company was used.
 */
public Company getCompany(State aState)
	throws NullDataException, UnknownStateException, InvalidCompanyException
{
	return getFromCache(determineCompany(aState), aState); 
}
/**
 * Returns a company derived from a company code.
 * @return com.sbc.eia.bis.BusinessInterface.Company
 * @param aCode java.lang.String
 * @exception com.sbc.eia.bis.BusinessInterface.NullDataException. A required input parameter was null.
 * @exception com.sbc.eia.bis.BusinessInterface.InvalidStateException. An invalid state was supplied.
 * @exception com.sbc.eia.bis.BusinessInterface.InvalidCompanyException. An invalid company code was supplied.
 */
public Company getCompany(String aCode)
	throws NullDataException, InvalidStateException, InvalidCompanyException
{
	return getFromCache(aCode, State.getAnAnyState());
}
/**
 * Returns a company derived from a company code and state.
 * @return com.sbc.eia.bis.BusinessInterface.Company
 * @param aCode java.lang.String
 * @param aState com.sbc.eia.bis.BusinessInterface.State
 * @exception com.sbc.eia.bis.BusinessInterface.NullDataException. A required input parameter was null.
 * @exception com.sbc.eia.bis.BusinessInterface.UnknownStateException. An unknown state was supplied.
 * @exception com.sbc.eia.bis.BusinessInterface.InvalidCompanyException. An invalid company code was supplied.
 */
public Company getCompany(String aCode, State aState)
	throws NullDataException, UnknownStateException, InvalidCompanyException
{
	return getFromCache(aCode, aState);
}
/**
 * Returns a company from the company cache. The company is added to the
 *	cache if it does not already exist there.
 * @return com.sbc.eia.bis.BusinessInterface.Company
 * @param aCode java.lang.String
 * @param aState com.sbc.eia.bis.BusinessInterface.State
 * @exception com.sbc.eia.bis.BusinessInterface.NullDataException. A required input parameter was null.
 * @exception com.sbc.eia.bis.BusinessInterface.InvalidCompanyException. An invalid company code was supplied.
 */
private Company getFromCache(String aCode, State aState)
	throws NullDataException, InvalidCompanyException
{
	if (aCode == null)
		throw new NullDataException(ExceptionCode.ERR_BSI_BUSINESS_NULL_DATA, "Null Code in CompanyFactory::getFromCache()");
	if (aState == null)
		throw new NullDataException(ExceptionCode.ERR_BSI_BUSINESS_NULL_DATA, "Null State in CompanyFactory::getFromCache()");
		
	String key = aCode + "/" + aState.getCode();				// The key is company code / state code
	Company aCompany = null;
	if ((aCompany = (Company)companyCache.get(key)) != null)	// Its in the cache
	{
		utility.log(LogEventId.DEBUG_LEVEL_2, "CompanyFactory cache HIT<" +
			companyCache.size() + "><" + key + ">");
	}
	else														// Its not in the cache - add it
	{
		utility.log(LogEventId.DEBUG_LEVEL_2, "CompanyFactory cache ADD<" +
			companyCache.size() + "><" + key + ">");
		aCompany = new Company(aCode, aState, hostFactory, utility);
		companyCache.put(key, aCompany);
	}
	return aCompany;
}
/**
 * Initializes the state to company map from a properties file.
 * @param aCode java.lang.String
 * @exception com.sbc.eia.bis.BusinessInterface.SeriousFailureException. A serious failure occurred.
 */
private void loadStateToCompany() throws SeriousFailureException
{
	// The file contains data fo the form: StateCode=CompanyCode (eg: CA=PB)
	String stateToCompanyFile = (String)properties.get("STATE_TO_COMPANY_MAP_FILE");
	if (stateToCompanyFile == null || stateToCompanyFile.length() < 1)
		throw new SeriousFailureException(ExceptionCode.ERR_BSI_BUSINESS_SERIOUS_FAILURE,
			"STATE_TO_COMPANY_MAP_FILE property not found/not set");

	try
	{
		PropertiesFileLoader.read(stateToCompany, stateToCompanyFile, utility);
		utility.log(LogEventId.DEBUG_LEVEL_2, ("Loaded " + stateToCompany.size() + " state to company mappings"));
	}
	catch (FileNotFoundException e)
	{
		throw new SeriousFailureException(ExceptionCode.ERR_BSI_BUSINESS_SERIOUS_FAILURE,
			"FileNotFoundException: " + e.getMessage());
	}
	catch (IOException e)
	{
		throw new SeriousFailureException(ExceptionCode.ERR_BSI_BUSINESS_SERIOUS_FAILURE, e.getMessage());
	}
}
/**
 * Prints out the contents of the host factory cache.
 * @param logLevel java.lang.String
 */
public void printHostCache(String logLevel) {
	hostFactory.printHostCache(logLevel);
}
/**
 * Clears all entries on the host factory cache and the company cache.
 */
public void release()
{
	hostFactory.release();
	companyCache.clear();
}
}
