// $Id: HostFactory.java,v 1.3 2003/03/12 00:10:58 dm2328 Exp $

package com.sbc.eia.bis.BusinessInterface;

import java.util.Hashtable;
import com.sbc.eia.bis.framework.logging.LogEventId;
import java.util.Enumeration;
import com.sbc.eia.idl.exception_types.*;

/**
 * HostFactory is the class which defines the host factory.
 * Creation date: (10/19/00 2:57:05 PM)
 * @author: Creighton Malet - Local
 */
public class HostFactory implements Cache {

	private Class[] constructorParameters = null;
	private String hostList[] = null;
	private Hashtable hostCache = new Hashtable();
	public com.sbc.bccs.utilities.Utility utility = null;
	private boolean loadCache = true;
	private java.util.Hashtable properties = null;
	private Selector SelSpecCoAnySt = null;
	private Selector SelAnyCoSpecSt = null;
	private Selector SelAnyCoAnySt = null;
/**
 * Class constructor.
 */
private HostFactory() {
}
/**
 * Class constructor accepting host list, utility and properties.
 * Creation date: (12/19/00 11:29:49 AM)
 * @param aHostList java.lang.String[]
 * @param aUtility com.sbc.bccs.utilities.Utility aUtility
 * @param aProperties java.util.Hashtable
 */
public HostFactory(String[] aHostList, com.sbc.bccs.utilities.Utility aUtility, Hashtable aProperties)
{
	hostList = aHostList;
	utility = aUtility;
	properties = aProperties;
}
/**
 * Adds a key to the host cache.
 * @param aSelector com.sbc.eia.bis.BusinessInterface.Selector
 * Creation date: (1/22/01 4:19:39 PM)
 */
public void addKeyToCache(Selector aSelector)
{
	HostCacheObject hostCacheObject = new HostCacheObject();
	hostCacheObject.setClassName(aSelector.getHost());
	aSelector.setHost(null);
	utility.log(LogEventId.DEBUG_LEVEL_2, "HostFactory::addKeyToCache<KEY ONLY><" +
		hostCache.size() + "><" + aSelector.getKey() + ">");
	hostCacheObject.setHost(null);
	hostCache.put(aSelector.getKey(), hostCacheObject);
	aSelector.setHost(hostCacheObject.getClassName());
}
/**
 * Initialises the constructor parameters
 *	for a host object.
 * @exception com.sbc.eia.bis.BusinessInterface.SeriousFailureException. A serious failure occurred.
 * Creation date: (1/22/01 2:53:52 PM)
 */
private void buildConstructorParameters() throws SeriousFailureException
{
	if (constructorParameters != null)
		return;
		
	try {
		constructorParameters = new Class[]
		{
			Class.forName("com.sbc.eia.bis.BusinessInterface.Company"),
			Class.forName("com.sbc.bccs.utilities.Utility"),
			Class.forName("java.util.Hashtable")
		};
	}
	catch (ClassNotFoundException e)
	{
		utility.log(LogEventId.ERROR, "ClassNotFoundException (HostFactory::buildConstructorParameters()a) " + e.getMessage());
		throw new SeriousFailureException(ExceptionCode.ERR_BSI_BUSINESS_SERIOUS_FAILURE,
			"ClassNotFoundException (HostFactory::buildConstructorParameters()a) " + e.getMessage());
	}
}
/**
 * Called by the garbage collector.
 * Creation date: (3/15/01 6:05:52 PM)
 */
public void finalize()
{
	hostCache.clear();
}
/**
 * Finds a Host object that implements the required interface
 *	for that company/state combination. The cache is searched first; then the Host hierachy.
 * @return com.sbc.eia.bis.BusinessInterface.Host
 * @param aSelector com.sbc.eia.bis.BusinessInterface.Selector
 * @param aPassThru java.lang.Object
 * @exception com.sbc.eia.bis.BusinessInterface.InvalidInterfaceException. The interface supplied is invalid.
 * @exception com.sbc.eia.bis.BusinessInterface.NotImplementedException. The interface requested is not implemented.
 * @exception com.sbc.eia.bis.BusinessInterface.SeriousFailureException. A serious failure occurred.
 * Creation date: (12/27/00 1:27:57 PM)
 */
public Host getHost(Selector aSelector, Object aPassThru)
	throws InvalidInterfaceException, NotImplementedException, SeriousFailureException
{
	utility.log(LogEventId.DEBUG_LEVEL_2, "HostFactory::getHost() for <" +
		aSelector.getKey() + ">");
	
	HostCacheObject hostCacheObject = null;
	Host hostObject = null;
	Object[] constructorObjects = null;
	String implementingClass = null;

	// First time - add all the cache entries
	if (loadCache)
	{
		utility.log(LogEventId.DEBUG_LEVEL_2, "HostFactory::load cache");
		Host.addCacheEntries(hostList, this, utility);
		loadCache = false;
	}

	// The search in the cache is done in a specific order:
	//	specific company/specific state
	//	specific company/any state
	//	any company/specific state
	//	any state/any company
	try {
		if (SelSpecCoAnySt == null)
		{
			SelSpecCoAnySt = new Selector(Company.getAnAnyCompany(State.getAnAnyState()), "", "");
			SelAnyCoSpecSt = new Selector(Company.getAnAnyCompany(State.getAnAnyState()), "", "");
			SelAnyCoAnySt = new Selector(Company.getAnAnyCompany(State.getAnAnyState()), "", "");
		}

		SelSpecCoAnySt.setCompany(new Company(aSelector.getCompany().getCode(), State.getAnAnyState(), null, null));
		SelSpecCoAnySt.setInterface(aSelector.getInterface());
		SelSpecCoAnySt.setClientKey(aSelector.getClientKey());
		SelSpecCoAnySt.setHost(aSelector.getHost());
			
		SelAnyCoSpecSt.setCompany(Company.getAnAnyCompany(aSelector.getCompany().getState()));
		SelAnyCoSpecSt.setInterface(aSelector.getInterface());
		SelAnyCoSpecSt.setClientKey(aSelector.getClientKey());
		SelAnyCoSpecSt.setHost(aSelector.getHost());
		
		SelAnyCoAnySt.setInterface(aSelector.getInterface());
		SelAnyCoAnySt.setClientKey(aSelector.getClientKey());
		SelAnyCoAnySt.setHost(aSelector.getHost());
	}
	catch (NullDataException e)
	{
		throw new SeriousFailureException(ExceptionCode.ERR_BSI_BUSINESS_SERIOUS_FAILURE,
			"Caught NullDataException in Host::getHost(): " + e.getMessage());
	}
	catch (InvalidStateException e)
	{
		throw new SeriousFailureException(ExceptionCode.ERR_BSI_BUSINESS_SERIOUS_FAILURE,
			"Caught InvalidStateException in Host::getHost(): " + e.getMessage());
	}
	catch (InvalidCompanyException e)
	{
		throw new SeriousFailureException(ExceptionCode.ERR_BSI_BUSINESS_SERIOUS_FAILURE,
			"Caught InvalidCompanyException in Host::getHost(): " + e.getMessage());
	}

	try
	{
		// Look in the cache
		if (((hostCacheObject = (HostCacheObject)hostCache.get(aSelector.getKey())) != null) ||
			((hostCacheObject = (HostCacheObject)hostCache.get(SelSpecCoAnySt.getKey())) != null) ||
			((hostCacheObject = (HostCacheObject)hostCache.get(SelAnyCoSpecSt.getKey())) != null) ||
			((hostCacheObject = (HostCacheObject)hostCache.get(SelAnyCoAnySt.getKey())) != null))
		{
			if (hostCacheObject.getHost() != null && hostCacheObject.getHost().isCacheable())
			{
				// The key is in the cache, with an object, and the object is cacheable - use the object
				utility.log(LogEventId.DEBUG_LEVEL_2, "HostFactory cache HIT<KEY and OBJECT><" +
					hostCache.size() + "><" + aSelector.getKey() + ">");
				hostCacheObject.getHost().setPassThru(aPassThru);
				hostCacheObject.getHost().setCompany(aSelector.getCompany()); // an 'any' could have a different co/ste
				utility.log(LogEventId.DEBUG_LEVEL_2, "Host: " + hostCacheObject.getClassName());
				return hostCacheObject.getHost();
			}
			else
			{
				// The key is in the cache but there is no object or the object is no longer cacheable -
				//	build a new object
				utility.log(LogEventId.DEBUG_LEVEL_2, "HostFactory cache HIT<KEY ONLY><" +
					hostCache.size() + "><" + aSelector.getKey() + ">");
				buildConstructorParameters();
				implementingClass = hostCacheObject.getClassName(); // For exception handling
				java.lang.reflect.Constructor myConstructor = Class.forName(implementingClass).getConstructor(constructorParameters);
				constructorObjects = new Object[] { aSelector.getCompany(), utility, properties };
				hostObject = (Host)myConstructor.newInstance(constructorObjects);
				hostObject.setPassThru(aPassThru);
				if (hostObject.isCacheable())
				{
					// If the object is cacheable keep it
					utility.log(LogEventId.DEBUG_LEVEL_2, "HostFactory cache ADD<OBJECT ONLY><" +
					hostCache.size() + "><" + aSelector.getKey() + ">");
					hostCacheObject.setHost(hostObject);
				}
				else
					// Not cacheable - don't keep hold
					hostCacheObject.setHost(null); 
				utility.log(LogEventId.DEBUG_LEVEL_2, "Host: " + hostCacheObject.getClassName());
				return hostObject;
			}
		}

		// The key is not in the cache - search the Host hierachy for the impelemnting class
		if (aSelector.getHost() == null)
			// 	Start at the original base Host list
			implementingClass = Host.searchForInterface(hostList, aSelector, utility);
		else
			// Start at a specific node
			implementingClass = Host.searchForInterface(new String[] { aSelector.getHost() }, aSelector, utility);	

		// Build the new Host object
		buildConstructorParameters();
		java.lang.reflect.Constructor myConstructor = Class.forName(implementingClass).getConstructor(constructorParameters);
		constructorObjects = new Object[] { aSelector.getCompany(), utility, properties };
		hostObject = (Host)myConstructor.newInstance(constructorObjects);
		hostObject.setPassThru(aPassThru);
		hostCacheObject = new HostCacheObject();
		hostCacheObject.setClassName(implementingClass);
		if (hostObject.isCacheable())
		{
			// Object is cacheable - add the key and keep the object
			utility.log(LogEventId.DEBUG_LEVEL_2, "HostFactory cache ADD<KEY and OBJECT><" +
					hostCache.size() + "><" + aSelector.getKey() + ">");
			hostCacheObject.setHost(hostObject);
		}
		else
		{
			// Object is not cacheable - just add the key
			utility.log(LogEventId.DEBUG_LEVEL_2, "HostFactory cache ADD<KEY ONLY><" +
				hostCache.size() + "><" + aSelector.getKey() + ">");
			hostCacheObject.setHost(null);
		}
		hostCache.put(aSelector.getKey(), hostCacheObject);
		utility.log(LogEventId.DEBUG_LEVEL_2, "Host: " + hostCacheObject.getClassName());
		return hostObject;
	}
	catch (ClassNotFoundException e)
	{
		String errorMessage = "ClassNotFoundException  (HostFactory::getHost()a)" + e.getMessage();
		utility.log(LogEventId.ERROR, errorMessage);
		throw new SeriousFailureException(ExceptionCode.ERR_BSI_BUSINESS_SERIOUS_FAILURE, errorMessage);
	}
	catch (NoSuchMethodException e)
	{
		String errorMessage = "NoSuchMethodException " + e.getMessage() + " on " + implementingClass;
		utility.log(LogEventId.ERROR, errorMessage);
		throw new SeriousFailureException(ExceptionCode.ERR_BSI_BUSINESS_SERIOUS_FAILURE, errorMessage);
	}
	catch (IllegalAccessException e)
	{
		String errorMessage = "IllegalAccessException " + e.getMessage() + " on " + implementingClass;
		utility.log(LogEventId.ERROR, errorMessage);
		throw new SeriousFailureException(ExceptionCode.ERR_BSI_BUSINESS_SERIOUS_FAILURE, errorMessage);
	}
	catch (java.lang.reflect.InvocationTargetException e)
	{
		String errorMessage = "InvocationTargetException: <" + e.getTargetException().getMessage() +
			"> on " + implementingClass;
		utility.log(LogEventId.ERROR, errorMessage);
		throw new SeriousFailureException(ExceptionCode.ERR_BSI_BUSINESS_SERIOUS_FAILURE, errorMessage);
	}
	catch (InstantiationException e)
	{
		String errorMessage = "InstantiationException " + e.getMessage() + " on " + implementingClass;
		utility.log(LogEventId.ERROR, errorMessage);
		throw new SeriousFailureException(ExceptionCode.ERR_BSI_BUSINESS_SERIOUS_FAILURE, errorMessage);
	}
}
/**
 * Prints out the cache contents.
 * @param logLevel java.lang.String
 * Creation date: (1/23/01 4:44:30 PM)
 */
public void printHostCache(String logLevel) {
	Object element;
	for (Enumeration keys = hostCache.keys(); keys.hasMoreElements();)
	{
		element = keys.nextElement();
		utility.log(logLevel, (String)element + ":" + ((HostCacheObject)hostCache.get(element)).getClassName());
	}
}
/**
 * Clears all entries on the host factory cache.
 */
public void release()
{
	Enumeration hce = hostCache.elements();
	HostCacheObject hco;
	while (hce.hasMoreElements())
	{
		hco = (HostCacheObject)hce.nextElement();
		if (hco.getHost() != null)
			hco.getHost().release();
	}
	hostCache.clear();
}
}
