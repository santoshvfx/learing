// $Id: Host.java,v 1.3 2003/03/12 00:10:58 dm2328 Exp $

package com.sbc.eia.bis.BusinessInterface;

import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.idl.exception_types.*;

/**
 * Host is the class which defines a host.
 * Creation date: (10/19/00 2:55:22 PM)
 * @author: Creighton Malet - Local
 */
public class Host
{
	protected Company company = null;
	protected com.sbc.bccs.utilities.Utility utility = null;
	private java.lang.Object passThru = null;
	private java.util.Hashtable properties = null;
	private boolean cacheable = true;
/**
 * Class constructor.
 */
private Host() {
}
/**
 * Class constructor accepting company, utility and properties.
 * Creation date: (12/19/00 11:29:49 AM)
 * @param aCompany com.sbc.eia.bis.BusinessInterface.Company
 * @param aUtility com.sbc.bccs.utilities.Utility aUtility
 * @param aProperties java.util.Hashtable
 */
public Host(Company aCompany, com.sbc.bccs.utilities.Utility aUtility, java.util.Hashtable aProperties) {
	company = aCompany;
	utility = aUtility;
	properties = aProperties;
}
/**
 * Called by the Host Factory to add entries into the Host cache.
 * @param aHostList java.lang.String[]
 * @param aCache com.sbc.eia.bis.BusinessInterface
 * @param aUtility com.sbc.bccs.utilities.Utility
 * @exception com.sbc.eia.bis.BusinessInterface.SeriousFailureException. A serious failure occurred.
 */
public static void addCacheEntries(String[] aHostList, Cache aCache,
		com.sbc.bccs.utilities.Utility aUtility)
	throws SeriousFailureException
{
	aUtility.log(LogEventId.DEBUG_LEVEL_2, "Host::addCacheEntries()");
	
	Class[] utilityParameter = null ;

	try {
		utilityParameter = new Class[] {
			Class.forName("com.sbc.bccs.utilities.Utility")
		};
	}
	catch (ClassNotFoundException e)
	{
		aUtility.log(LogEventId.ERROR, "ClassNotFoundException (Host::addCacheEntries()a) " + e.getMessage());
		throw new SeriousFailureException(ExceptionCode.ERR_BSI_BUSINESS_SERIOUS_FAILURE,
			"ClassNotFoundException (Host::addCacheEntries()a) " + e.getMessage());
	}
		 
	for (int i = 0; i < aHostList.length; i++)
	{
		try {
			aUtility.log(LogEventId.DEBUG_LEVEL_2, "Host::addCacheEntries on " + aHostList[i]);

			Object[] utilityObject = new Object[] { aUtility };
			Selector[] aSelectorList = (Selector[])Class.forName(aHostList[i]).getMethod("getCacheEntries",
				utilityParameter).invoke(null, utilityObject);
			if (aSelectorList != null)
			{
				for (int j = 0; j < aSelectorList.length; j++)
				{
					aCache.addKeyToCache(aSelectorList[j]);
				}
			}	
				
			String[] anotherHostList = (String[])Class.forName(aHostList[i]).getMethod("getHostList",
				utilityParameter).invoke(null, utilityObject);
			if (anotherHostList != null)
					addCacheEntries(anotherHostList, aCache, aUtility);
		}
		catch (ClassNotFoundException e)
		{
			aUtility.log(LogEventId.ERROR, "ClassNotFoundException (Host::addCacheEntries()b) " + e.getMessage());
		}
		catch (NoSuchMethodException e)
		{
			aUtility.log(LogEventId.ERROR, "NoSuchMethodException " + e.getMessage() + " on " + aHostList[i]);
		}
		catch (IllegalAccessException e)
		{
			aUtility.log(LogEventId.ERROR, "IllegalAccessException " + e.getMessage() + " on " + aHostList[i]);
		}
		catch (java.lang.reflect.InvocationTargetException e)
		{
			aUtility.log(LogEventId.ERROR, "InvocationTargetException: <" + e.getTargetException().getMessage() + "> on " + aHostList[i]);
		}
	}
}
/**
 * Called by the Host Factory to get entries for preloading into the Host cache.
 * 	This method must be overridden.
 * @return com.sbc.eia.bis.BusinessInterface.Selector[]
 * @param aUtility com.sbc.bccs.utilities.Utility
 * @exception com.sbc.eia.bis.BusinessInterface.NullDataException: A required input parameter was null.
 * @exception com.sbc.eia.bis.BusinessInterface.InvalidCompanyException: An attempt was made to create an invalid company.
 * @exception com.sbc.eia.bis.BusinessInterface.InvalidStateException: An attempt was made to create an invalid state.
 */
public static Selector[] getCacheEntries(com.sbc.bccs.utilities.Utility aUtility)
	throws NullDataException, InvalidCompanyException, InvalidStateException
{
	aUtility.log(LogEventId.ERROR, "Host::getCacheEntries() should not be called");
	return null;
}
/**
 * Called by the Host Factory to obtain the client key. If there is no client key return null.
 * @return com.sbc.eia.bis.BusinessInterface.ClientKey
 * @param aUtility com.sbc.bccs.utilities.Utility
 */
public static ClientKey getClientKey(com.sbc.bccs.utilities.Utility aUtility) {
	return null;
}
/**
 * Called by the Host Factory to obtain a list of hosts that are the immediate
 * 	children of the called class.
 *	This method must be overridden.
 * @return java.lang.String[]
 * @param aUtility com.sbc.bccs.utilities.Utility
 */
public static String[] getHostList(com.sbc.bccs.utilities.Utility aUtility) {
	aUtility.log(LogEventId.ERROR, "Host::getHostList() should not be called");
	return null;
}
/**
 * Called by Host::searchForInterface() to check if a Host implements a
 *	specific interface.
 *	First a match is sought on Company/State in a list of companies. If that works a match is sought on Interface
 *	in a list of interfaces.
 * Creation date: (12/27/00 2:39:25 PM)
 * @return java.lang.String
 * @param aSelector com.sbc.eia.bis.BusinessInterface.Selector
 * @param aCompanies com.sbc.eia.bis.BusinessInterface.Company[]
 * @param anInterfaceList java.lang.String[]
 * @param aClientKey com.sbc.eia.bis.BusinessInterface.ClientKey
 * @param thisClass java.lang.String
 * @param aUtility com.sbc.bccs.utilities.Utility
 * @exception com.sbc.eia.bis.BusinessInterface.NotImplementedException. The interface requested is not implemented.
 */
public static String getImplementingHost(Selector aSelector,
		Company[] aCompanies, String[] anInterfaceList, ClientKey aClientKey, String thisClass,
		com.sbc.bccs.utilities.Utility aUtility)
	throws NotImplementedException
{
	aUtility.log(LogEventId.DEBUG_LEVEL_2, "Host::getImplementingHost() for <" +
		aSelector.getKey() + ">");

	if (aCompanies == null)
		throw new NotImplementedException(ExceptionCode.ERR_BSI_BUSINESS_NOT_IMPLEMENTED,
		"Not implemented by " + thisClass);
	
	// Look for a match on the Company/State in the list of companies	
	for (int j = 0; j < aCompanies.length; j++)
	{
		if ((	aCompanies[j].equals(aSelector.getCompany()) ||
			aCompanies[j].isAnyCompany())
			&&
			(	aCompanies[j].getState().equals(aSelector.getCompany().getState()) ||
				aCompanies[j].getState().isAnyState()))
		{
			// Company/State matches. Look for a match on the interface in the list of interfaces
			for (int i = 0; i < anInterfaceList.length; i++)
			{
				if (anInterfaceList[i].equals(aSelector.getInterface()))
				{
					// Interface matches. Now look at the the client key
					if (aSelector.getClientKey() == null && aClientKey == null)
						return thisClass;
					if (aSelector.getClientKey() != null && aClientKey != null)
					{
						if (aClientKey.toString().equals(aSelector.getClientKey().toString()))
						{
							return thisClass;
						}
					}
				}
			}
		}
	}
	throw new NotImplementedException(ExceptionCode.ERR_BSI_BUSINESS_NOT_IMPLEMENTED,
		"Not implemented by " + thisClass);
}
/**
 * Called by the Host Factory to obtain a list of the interfaces 
 *	that are the supported by the called class.
 * 	This method must be overridden.
 * @return java.lang.String[]
 * @param aUtility com.sbc.bccs.utilities.Utility
 */
public static String[] getInterfaceList(com.sbc.bccs.utilities.Utility aUtility) {
	aUtility.log(LogEventId.ERROR, "Host::getInterfaceList() should not be called");
	return null;
}
/**
 * Returns the pass through object passed in by the caller when
 *	the getBusinessInterface method was invoked on Company.
 * Creation date: (1/22/01 12:39:22 PM)
 * @return java.lang.Object
 */
public java.lang.Object getPassThru() {
	return passThru;
}
/**
 * Returns the properties passed in by the caller when
 *	the CompanyFactory was constructed.
 * Creation date: (1/31/01 10:37:51 AM)
 * @return java.util.Hashtable
 */
public java.util.Hashtable getProperties() {
	return properties;
}
/**
 * Called by the Host Factory to obtain a list of the companies 
 *	that are the supported by the called class.
 * 	This method must be overridden.
 * @return com.sbc.eia.bis.BusinessInterface.Company[]
 * @param aUtility com.sbc.bccs.utilities.Utility
 * @exception com.sbc.eia.bis.BusinessInterface.NullDataException: A required input parameter was null.
 * @exception com.sbc.eia.bis.BusinessInterface.InvalidCompanyException: An attempt was made to create an invalid company.
 * @exception com.sbc.eia.bis.BusinessInterface.InvalidStateException: An attempt was made to create an invalid state.
 */
public static Company[] getSupportedCompanies(com.sbc.bccs.utilities.Utility aUtility)
	throws NullDataException, InvalidCompanyException, InvalidStateException
{
	aUtility.log(LogEventId.ERROR, "Host::getSupportedCompanies() should not be called");
	return null;
}
/**
 * States whether the host factory can cache instances (objects) of this Host class.
 * Creation date: (1/22/01 1:48:25 PM)
 * @return boolean
 */
public boolean isCacheable() {
	return cacheable;
}
/**
 * Called on each specific object when the release method is called
 *	on the company factory. Override the method if required.
 * Creation date: (3/15/01 2:02:06 PM)
 */
public void release() {}
/**
 * Called by the Host Factory to find a Host that implements an interface when
 *	there is no cache entry.
 *	The search starts with a list of base Hosts.
 * Creation date: (12/27/00 4:17:22 PM)
 * @return java.lang.String
 * @param aHostList java.lang.String[]
 * @param aSelector com.sbc.eia.bis.BusinessInterface.Selector
 * @param aUtility com.sbc.bccs.utilities.Utility
 * @exception com.sbc.eia.bis.BusinessInterface.NotImplementedException. The interface requested is not implemented.
 * @exception com.sbc.eia.bis.BusinessInterface.SeriousFailureException. A serious failure occurred.
 */
public static String searchForInterface(String[] aHostList, Selector aSelector,
		com.sbc.bccs.utilities.Utility aUtility)
	throws NotImplementedException, SeriousFailureException
{
	Class[] methodParameters = null;
	Class[] utilityParameter = null ;

	// The paramaters for getImplementingHost() and Utility in general
	try {
		methodParameters = new Class[] {
		  	Class.forName("com.sbc.eia.bis.BusinessInterface.Selector"),
		  	Class.forName("[Lcom.sbc.eia.bis.BusinessInterface.Company;"),
		  	Class.forName("[Ljava.lang.String;"),
		  	Class.forName("com.sbc.eia.bis.BusinessInterface.ClientKey"),
		  	Class.forName("java.lang.String"),
		  	Class.forName("com.sbc.bccs.utilities.Utility")
		};

		utilityParameter = new Class[] {
			Class.forName("com.sbc.bccs.utilities.Utility")
		};
	}
	catch (ClassNotFoundException e)
	{
		aUtility.log(LogEventId.ERROR, "ClassNotFoundException (Host::searchForInterface()a) " + e.getMessage());
		throw new SeriousFailureException(ExceptionCode.ERR_BSI_BUSINESS_SERIOUS_FAILURE,
			"ClassNotFoundException (Host::searchForInterface()a) " + e.getMessage());
	}

	// Go through the list of Hosts 
	for (int i = 0; i < aHostList.length; i++)
	{
		try {
			aUtility.log(LogEventId.DEBUG_LEVEL_2, "Host::searchForInterface on " + aHostList[i]);
			Object[] utilityObject = new Object[] { aUtility };
			// Get the children Hosts of this particular Host
			String[] anotherHostList = (String[])Class.forName(aHostList[i]).getMethod("getHostList",
				utilityParameter).invoke(null, utilityObject);
			try {
				// If this Host has children recursively invoke them
				if (anotherHostList != null)
					return searchForInterface(anotherHostList, aSelector, aUtility);
			}
			catch (NotImplementedException e)
			{
				aUtility.log(LogEventId.DEBUG_LEVEL_2, e.getMessage());
			}

			// At the bottom of the tree - no children or none of the children implement the interface
			aUtility.log(LogEventId.DEBUG_LEVEL_2, "Trying this class");
			
			// Get the supported companies, interfaces and client key for this Host	
			Company[] theCompanies = (Company[])Class.forName(aHostList[i]).getMethod("getSupportedCompanies",
				utilityParameter).invoke(null, utilityObject);
			String[] theInterfaceList = (String[])Class.forName(aHostList[i]).
				getMethod("getInterfaceList", utilityParameter).invoke(null, utilityObject);
			ClientKey theClientKey = (ClientKey)Class.forName(aHostList[i]).
				getMethod("getClientKey", utilityParameter).invoke(null, utilityObject);
			Object[] methodObjects = new Object[] {	aSelector, theCompanies, theInterfaceList, theClientKey,
				aHostList[i], aUtility };

			// See if this Host implements this interface
			java.lang.reflect.Method myMethod = Class.forName(aHostList[i]).getMethod("getImplementingHost", 
				methodParameters);
			return (String) myMethod.invoke(null, methodObjects);
		}
		catch (ClassNotFoundException e)
		{
			aUtility.log(LogEventId.ERROR, "ClassNotFoundException (Host::searchForInterface()b) " + e.getMessage());
		}
		catch (NoSuchMethodException e)
		{
			aUtility.log(LogEventId.ERROR, "NoSuchMethodException " + e.getMessage() + " on " + aHostList[i]);
		}
		catch (IllegalAccessException e)
		{
			aUtility.log(LogEventId.ERROR, "IllegalAccessException " + e.getMessage() + " on " + aHostList[i]);
		}
		catch (java.lang.reflect.InvocationTargetException e)
		{
			if (e.getTargetException().getClass().equals(NotImplementedException.class))
				aUtility.log(LogEventId.DEBUG_LEVEL_2, "InvocationTargetException: <" + e.getTargetException().getMessage() + "> on " + aHostList[i]);
			else
				aUtility.log(LogEventId.ERROR, "InvocationTargetException: <" + e.getTargetException().getMessage() + "> on " + aHostList[i]);
		}
	}

	throw new NotImplementedException(ExceptionCode.ERR_BSI_BUSINESS_NOT_IMPLEMENTED,
		aSelector.getKey() + " not implemented");
}
/**
 * Sets whether the host factory can cache instances (objects) of this Host class.
 * Creation date: (1/22/01 1:48:25 PM)
 * @param newCacheable boolean
 */
public void setCacheable(boolean newCacheable) {
	cacheable = newCacheable;
}
/**
 * Sets the company object. Only for use by HostFactory.
 * Creation date: (2/24/02 11:51:13 AM)
 * @param newCompany com.sbc.eia.bis.BusinessInterface.Company
 */
public void setCompany(Company newCompany) {
	company = newCompany;
}
/**
 * Sets the pass through object passed in by the caller when
 *	the getBusinessInterface method is invoked on Company.
 * Creation date: (1/22/01 12:39:22 PM)
 * @param newPassThru java.lang.Object
 */
public void setPassThru(java.lang.Object newPassThru) {
	passThru = newPassThru;
}
}
