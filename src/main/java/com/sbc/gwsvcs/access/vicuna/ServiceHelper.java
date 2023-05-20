// $Id: ServiceHelper.java,v 1.7 2006/10/31 21:52:06 cm2159 Exp $

package com.sbc.gwsvcs.access.vicuna;

import com.sbc.vicunalite.api.*;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.exception_types.ExceptionCode;
import com.sbc.gwsvcs.access.vicuna.exceptions.*;

/**
 * Provides support for client access to a Vicuna/Datagate service.
 * Creation date: (4/16/00 11:35:56 AM)
 * @author: Creighton Malet
 */
public abstract class ServiceHelper 
{
	protected ServiceAccess serviceAccess = null;
	protected String vicunaXmlFile = null;
	protected String serviceXmlDir = null;
	protected com.sbc.bccs.utilities.Logger logger = null;
	protected java.lang.String extractedTimeOut = null;
	protected java.lang.String extractedApplData = null;
	protected java.lang.String extractedService = null;
	private long timeOutFactor = 1000;
	public static long USE_DEFAULT_TIMEOUT = 0;
	public static final String SELF_TEST_REQ = "SELF_TEST_REQ";
	public static final String SELF_TEST_RESP = "SELF_TEST_RESP";
/**
 * Class constructor.
 */
private ServiceHelper() {
}
/**
 * Class constructor accepting properties, logger and service name.
 * @param properties java.util.Hashtable
 * @param aLogger com.sbc.bccs.utilities.Logger
 * @param serviceName java.lang.String
 */
public ServiceHelper(java.util.Hashtable properties, com.sbc.bccs.utilities.Logger aLogger,
	String serviceName)
{
	vicunaXmlFile = (String)properties.get("VICUNA_XML_FILE");
	if (vicunaXmlFile != null)
		if (vicunaXmlFile.length() < 1)
			vicunaXmlFile = null;
				
	serviceXmlDir = (String)properties.get("VICUNA_SERVICE_CONFIG_DIR");
	if (serviceXmlDir != null)
		if (serviceXmlDir.length() < 1)
			serviceXmlDir = null;

	if ((extractedService = (String)properties.get(serviceName + "_SERVICE_NAME")) == null)
		extractedService = (String)properties.get(serviceName.toUpperCase() + "_SERVICE_NAME");
	if (extractedService != null)
		if (extractedService.length() < 1)
			extractedService = null;

	if ((extractedApplData = (String)properties.get(serviceName + "_APPLDATA")) == null)
	{
		if ((extractedApplData = (String)properties.get(serviceName.toUpperCase() + "_APPLDATA")) == null)
			extractedApplData = (String)properties.get("APPLDATA");
	}
	
	if ((extractedTimeOut = (String)properties.get(serviceName + "_TIMEOUT")) == null)
		extractedTimeOut = (String)properties.get(serviceName.toUpperCase() + "_TIMEOUT");
	if (extractedTimeOut != null)
		if (extractedTimeOut.length() < 1 || Integer.parseInt(extractedTimeOut) == USE_DEFAULT_TIMEOUT)
			extractedTimeOut = null;

	logger = aLogger;
}
/**
 * Assert the defaults.
 * Creation date: (3/11/01 10:36:36 AM)
 * @exception com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException: an exception occurred.
 */
protected void assertDefaults() throws ServiceException {
	if (extractedService != null)
		setDefaultService(extractedService);
	if (extractedTimeOut != null)
		setDefaultTimeOut(Integer.parseInt(extractedTimeOut));
	if (extractedApplData != null)
		setDefaultApplData(extractedApplData);
}
/**
 * Connect to a service.
 * Creation date: (2/5/01 3:56:22 PM)
 * @param applData java.lang.String
 * @param aService java.lang.String
 * @exception com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException: an exception occurred.
 */
public void connect(String anApplData, String aService)
	 throws ServiceException
{
	if (serviceAccess != null)
	{
		serviceAccess.connect(anApplData, aService);
	}
}
/**
 * Disconnect from a service.
 * Creation date: (4/16/00 1:32:58 PM)
 * @exception com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException: an exception occurred.
 */
public void disconnect() throws ServiceException
{
	if (serviceAccess != null)
	{
		serviceAccess.disconnect();
	}
}
/**
 * Adjust a timeout according to scaling (eg. convert from seconds to internal specification).
 * Creation date: (3/11/01 10:36:36 AM)
 * @return long
 * @param aTimeOut long
 */
protected long factorTimeOut(long aTimeOut) {
	return aTimeOut * getTimeOutFactor();
}
/**
 * Flush all the messages on a commHandle.
 * Creation date: (4/16/00 1:32:58 PM)
 */
public void flushMessages()
{
	if (serviceAccess != null)
	{
		serviceAccess.flushMessages();
	}
}
/**
 * Return the time out factor.
 * Creation date: (3/11/01 10:31:26 AM)
 * @return long
 */
public long getTimeOutFactor() {
	return timeOutFactor;
}
/**
 * Log a message, if a valid logger was passed at creation.
 * Creation date: (3/8/01 10:45:59 AM)
 * @param eventId java.lang.String
 * @param message java.lang.String
 */
protected void log(String eventId, String message)
{
	if (logger != null)
		logger.log(eventId, message);
}
/**
 * Log a message, if a valid logger was passed at creation.
 * Creation date: (3/8/01 10:45:59 AM)
 * @param eventId java.lang.String
 * @param location java.lang.String
 * @param service java.lang.String
 * @param component java.lang.String
 * @param operation java.lang.String
 */
protected void log(String eventId, String location, String service,
	String component, String operation)
{
	if (logger != null)
	{
		logger.log(eventId, location, service, component, operation);
	}
}
/**
 * Set the default appl data.
 * Creation date: (1/29/01 12:08:01 PM)
 * @param newDefaultApplData java.lang.String
 * @exception com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException: an exception occurred.
 */
public void setDefaultApplData(java.lang.String newDefaultApplData) 
	 throws ServiceException
{
	serviceAccess.setDefaultApplData(newDefaultApplData);
}
/**
 * Set the default service name.
 * Creation date: (1/29/01 12:08:01 PM)
 * @param newDefaultService java.lang.String
 * @exception com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException: an exception occurred.
 */
public void setDefaultService(java.lang.String newDefaultService) 
	 throws ServiceException
{
	serviceAccess.setDefaultService(newDefaultService);
}
/**
 * Set the default time out.
 * Creation date: (1/28/01 3:31:43 PM)
 * @param newDefaultTimeOut long
 * @exception com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException: an exception occurred.
 */
public void setDefaultTimeOut(long newDefaultTimeOut)
	 throws ServiceException
{
	serviceAccess.setDefaultTimeOut(newDefaultTimeOut * timeOutFactor);
}
/**
 * Set the default time out factor.
 * Creation date: (3/11/01 10:31:26 AM)
 * @param newTimeOutFactor long
 */
public void setTimeOutFactor(long newTimeOutFactor) {
	timeOutFactor = newTimeOutFactor;
}
/**
 * Strip characters past a null in a char array and convert to a String.
 * Creation date: (5/19/00 3:20:32 PM)
 * @return java.lang.String
 * @param param char[]
 */
public static String stripGarbage(char[] ca) {
	if (ca.length < 1)
		return new String("");
	int i = 0;
	for (; i < ca.length; i++)
	{
		if (ca[i] == '\0')
		{
			i++;
			break;
		}
	}
	return new String(ca, 0 , --i);
}
/**
 * Invoke selfTest on the ServiceAccess object.
 * @return com.sbc.eia.idl.bis_types.BisContext
 * @param aBisContext com.sbc.eia.idl.bis_types.BisContext
 * @exception com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException: an exception occurred.
 */
public BisContext selfTest(BisContext aBisContext)
	throws ServiceException
{
	return	selfTest(aBisContext, null, null);
}
/**
 * Invoke selfTest on the ServiceAccess object.
 * @return com.sbc.eia.idl.bis_types.BisContext
 * @param aBisContext com.sbc.eia.idl.bis_types.BisContext
 * @param applData java.lang.String
 * @param aService java.lang.String
 * @exception com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException: an exception occurred.
 */
public BisContext selfTest(BisContext aBisContext, String anApplData, String aService)
	throws ServiceException
{
	if (serviceAccess != null)
	{
		return serviceAccess.selfTest(aBisContext, anApplData, aService);
	}
	throw new ServiceException(ExceptionCode.ERR_VCS_SERVICE,
		"serviceAccess reference is null");
}
/**
 * Invoke selfTest on the ServiceAccess object.
 * @return com.sbc.eia.idl.bis_types.BisContext
 * @param aBisContext com.sbc.eia.idl.bis_types.BisContext
 * @param aMessage com.sbc.vicunalite.api.MMarshalObject
 * @param autoHandleSysMsg boolean
 * @exception com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException: an exception occurred.
 */
public BisContext selfTest(BisContext aBisContext,	MMarshalObject aMessage,
		boolean autoHandleSysMsg)
	throws ServiceException
{
	return selfTest(aBisContext,
		new EventObjectPair(new MEventType(SELF_TEST_REQ), aMessage, 1),
		new MEventType(SELF_TEST_RESP), autoHandleSysMsg, null, null, 0);
}
/**
 * Invoke selfTest on the ServiceAccess object.
 * @return com.sbc.eia.idl.bis_types.BisContext
 * @param aBisContext com.sbc.eia.idl.bis_types.BisContext
 * @param aMessage com.sbc.vicunalite.api.MMarshalObject
 * @param autoHandleSysMsg boolean
 * @param applData java.lang.String
 * @param aService java.lang.String
 * @param aTimeOut long
 * @exception com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException: an exception occurred.
 */
public BisContext selfTest(BisContext aBisContext,
		MMarshalObject aMessage, boolean autoHandleSysMsg, String anApplData,
		String aService, long aTimeOut)
	throws ServiceException
{
	return selfTest(aBisContext,
		new EventObjectPair(new MEventType(SELF_TEST_REQ), aMessage, 1),
		new MEventType(SELF_TEST_RESP), autoHandleSysMsg, anApplData, aService,
			aTimeOut);
}
/**
 * Invoke selfTest on the ServiceAccess object.
 * @return com.sbc.eia.idl.bis_types.BisContext
 * @param aBisContext com.sbc.eia.idl.bis_types.BisContext
 * @param aSendMessage com.sbc.gwsvcs.access.vicuna.EventObjectPair
 * @param anExpectedEvent com.sbc.vicunalite.api.MEventType
 * @param autoHandleSysMsg boolean
 * @param anApplData java.lang.String
 * @param aService java.lang.String
 * @param aTimeOut long
 * @exception com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException: an exception occurred.
 */
public BisContext selfTest(BisContext aBisContext, EventObjectPair aSendMessage,
		MEventType anExpectedEvent, boolean autoHandleSysMsg, String anApplData,
		String aService, long aTimeOut)
	throws ServiceException
{
	if (serviceAccess != null)
	{
		return serviceAccess.selfTest(aBisContext,
			aSendMessage, anExpectedEvent, autoHandleSysMsg, anApplData, aService,
			factorTimeOut(aTimeOut));
	}
	throw new ServiceException(ExceptionCode.ERR_VCS_SERVICE,
		"serviceAccess reference is null");
}
}
