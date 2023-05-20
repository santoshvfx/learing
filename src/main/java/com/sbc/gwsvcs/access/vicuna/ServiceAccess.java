// $Id: ServiceAccess.java,v 1.16 2006/10/31 21:52:06 cm2159 Exp $

package com.sbc.gwsvcs.access.vicuna;

import com.sbc.vicunalite.api.*;
import com.sbc.vicunalite.api.orb.*;
import com.sbc.gwsvcs.access.vicuna.exceptions.*;
import com.sbc.eia.bis.framework.logging.LogEventId;
import java.util.*;
import java.net.*;
import java.io.*;

import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.exception_types.*;
import com.sbc.bccs.utilities.PropertiesFileLoader;

/**
 * Wraps the service side for access to a Vicuna/Datagate service.
 * Creation date: (4/16/00 11:36:38 AM)
 * @author: Creighton Malet
 */
public abstract class ServiceAccess {
	private com.sbc.vicunalite.api.MCommHandle commHandle = null;
	private com.sbc.vicunalite.api.MProperty propertyList = null;
	private com.sbc.vicunalite.api.MProperty eventList = null;
	private com.sbc.vicunalite.api.MEnv env = null;
	private com.sbc.bccs.utilities.Logger logger = null;
	private MProperty defaultService = null;
	private MProperty defaultApplData = null;
	private long defaultTimeOut = 0;
/**
 * Class constructor.
 * Creation date: (2/7/01 2:40:34 PM)
 */
protected ServiceAccess() {}
/**
 * Class constructor accepting a version, base xml file name, default time out, Vicuna xml file name, directory for
 *	xml files and a logger.
 * @param version java.lang.String
 * @param xmlName java.lang.String
 * @param aDefaultTimeOut long
 * @param vicunaXmlFile java.lang.String
 * @param serviceXmlDir java.lang.String
 * @param aLogger com.sbc.bccs.utilities.Logger
 * @exception com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException: an exception occurred.
 */
public ServiceAccess(String version, String xmlName, long aDefaultTimeOut, String vicunaXmlFile, String serviceXmlDir, com.sbc.bccs.utilities.Logger aLogger) throws ServiceException
{
	setup(version, xmlName, aDefaultTimeOut, vicunaXmlFile, serviceXmlDir,
		aLogger);
}
/**
 * Class constructor accepting a version, base xml file name, default time out, directory for
 *	xml files, a logger and an MEnv.
 * @param version java.lang.String
 * @param xmlName java.lang.String
 * @param aDefaultTimeOut long
 * @param serviceXmlDir java.lang.String
 * @param aLogger com.sbc.bccs.utilities.Logger
 * @param mEnv com.sbc.vicunalite.api.MEnv
 * @exception com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException: an exception occurred.
 */
public ServiceAccess(String version, String xmlName, long aDefaultTimeOut,
	String serviceXmlDir, com.sbc.bccs.utilities.Logger aLogger, MEnv mEnv)
		throws ServiceException
{
	if (mEnv == null)
		throw new ServiceException(ExceptionCode.ERR_VCS_SERVICE, "MEnv is null");
	env = mEnv;
	setup(version, xmlName, aDefaultTimeOut, null, serviceXmlDir,
		aLogger);
}
/**
 * Common constructor setup code accepting a version, base xml file name, default time out, Vicuna xml file name, directory for
 *	xml files and a logger.
 * @param version java.lang.String
 * @param xmlName java.lang.String
 * @param aDefaultTimeOut long
 * @param vicunaXmlFile java.lang.String
 * @param serviceXmlDir java.lang.String
 * @param aLogger com.sbc.bccs.utilities.Logger
 * @exception com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException: an exception occurred.
 */
private void setup(String version, String xmlName, long aDefaultTimeOut, String vicunaXmlFile, String serviceXmlDir, com.sbc.bccs.utilities.Logger aLogger) throws ServiceException
{
	defaultTimeOut = aDefaultTimeOut;
	logger = aLogger;

	InputStreamReader bufIn = null;
	InputStream input = null;
	try
	{
//		if (serviceXmlDir == null)
//			throw new ServiceException(ExceptionCode.ERR_VCS_SERVICE, "Location for vicuna service configuration files not set");


		if (env == null) { // May be set by one of the constructors
			if (vicunaXmlFile == null)
				throw new ServiceException(ExceptionCode.ERR_VCS_SERVICE, "Full path name of vicuna xml file not set");
//		if (serviceXmlDir == null)
//			throw new ServiceException(ExceptionCode.ERR_VCS_SERVICE, "Location for vicuna service configuration files not set");

			System.out.println(vicunaXmlFile);
			System.out.println(this.getClass().getClassLoader().getResource(vicunaXmlFile));

			//java.io.File vxml = new java.io.File(vicunaXmlFile.trim());
			URL vxml = this.getClass().getClassLoader().getResource(vicunaXmlFile);
			env = new MEnv(vxml, "G");
		}

		String xmlPath = xmlName + "_"+ version + ".xml";
		if (version == null || version.equals(""))
			xmlPath = xmlName + ".xml";
		log(LogEventId.DEBUG_LEVEL_2, "ServiceAccess::setup() load service file: " + xmlPath);
		input = PropertiesFileLoader.getAsStream(xmlPath, logger);
		bufIn = new InputStreamReader(input);
		MProperty ServiceInitList = new MProperty(bufIn);
		propertyList = ServiceInitList.getProperty("propertyList").getSubList(xmlName);
		eventList = ServiceInitList.getProperty("propertyList").getSubList("EventList");
		if (propertyList.isPropertyDefined("directory/search/service"))
			setDefaultService(propertyList.getProperty("directory/search/service").extract_string());
		if (propertyList.isPropertyDefined("directory/search/applData"))
			setDefaultApplData(propertyList.getProperty("directory/search/applData").extract_string());
	}
	catch (FileNotFoundException e)
	{
		throw new ServiceException(ExceptionCode.ERR_VCS_SERVICE, "FileNotFoundException: " + e.getMessage(), e);
	}
	catch (MException e)
	{
		throw new ServiceException(ExceptionCode.ERR_VCS_SERVICE, e.getMessages(), e);
	}
	finally
	{
		if (bufIn != null)
		{
			try{
				bufIn.close();
			}
			catch (IOException i)
			{
				log(LogEventId.EXCEPTION, "IOException in ServiceAcccess::setup - bufIn.close(): " + i.getMessage());
			}
		}
		if (input != null)
		{
			try{
				input.close();
			}
			catch (IOException i)
			{
				log(LogEventId.EXCEPTION, "IOException in ServiceAcccess::setup - input.close(): " + i.getMessage());
			}
		}
	}
}
/**
 * Connect to a service (get a commhandle) with default service name.
 * Creation date: (4/16/00 11:52:01 AM)
 * @param applData java.lang.String
 * @exception com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException: an exception occurred.
 */
public void connect(String applData) throws ServiceException
{
	disconnect();
	try
	{
		setProperty("directory/search/service", null, defaultService, false);
		setProperty("directory/search/applData", applData, defaultApplData, true);
		log(LogEventId.DEBUG_LEVEL_2, "ServiceAccess::connect(): " + showServiceDetails());
		commHandle = env.createCommHandle(propertyList, eventList);
	}
	catch (MEnvException e)
	{
		throw new ServiceException(ExceptionCode.ERR_VCS_SERVICE, e.getMessages() + showServiceDetails(), e);
	}
}
/**
 * Connect to a service (get a commhandle).
 * Creation date: (4/16/00 11:52:01 AM)
 * @param applData java.lang.String
 * @param aService java.lang.String
 * @exception com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException: an exception occurred.
 */
public void connect(String applData, String aService) throws ServiceException
{
	disconnect();
	setProperty("directory/search/service", aService, defaultService, false);
	setProperty("directory/search/applData", applData, defaultApplData, true);
	try
	{
		log(LogEventId.DEBUG_LEVEL_2, ("ServiceAccess::connect(): " + showServiceDetails()));
		commHandle = env.createCommHandle(propertyList, eventList);
	}
	catch (MEnvException e)
	{
		throw new ServiceException(ExceptionCode.ERR_VCS_SERVICE, e.getMessages() + showServiceDetails(), e);
	}
}
/**
 * Connect to a service, send a message, receive a message and disconnect. Use default time out and service name.
 * Creation date: (4/16/00 2:45:12 PM)
 * @return com.sbc.gwsvcs.access.vicuna.EventObjectPair
 * @param applData java.lang.String
 * @param input com.sbc.gwsvcs.access.vicuna.EventObjectPair
 * @param expected com.sbc.gwsvcs.access.vicuna.EventClassPair[]
 * @exception com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException: an exception occurred.
 */
public EventObjectPair connectSendReceiveAndDisconnect(String applData, EventObjectPair input, EventClassPair[] expected)
	throws ServiceException
{
	disconnect();
	try
	{
		connect(applData);
		send(input);
		return receive(expected, 0); // Default time out and service
	}
	finally
	{
		disconnect();
	}
}
/**
 * Connect to a service, send a message, receive a message and disconnect.
 * Creation date: (4/16/00 2:45:12 PM)
 * @return com.sbc.gwsvcs.access.vicuna.EventObjectPair
 * @param applData java.lang.String
 * @param aService java.lang.String
 * @param aTimeOut long
 * @param input com.sbc.gwsvcs.access.vicuna.EventObjectPair
 * @param expected com.sbc.gwsvcs.access.vicuna.EventClassPair[]
 * @exception com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException: an exception occurred.
 */
public EventObjectPair connectSendReceiveAndDisconnect(String applData, String aService, long aTimeOut, EventObjectPair input, EventClassPair[] expected)
	throws ServiceException
{
	disconnect();
	try
	{
		connect(applData, aService);
		send(input);
		return receive(expected, aTimeOut);
	}
	finally
	{
		disconnect();
	}
}
/**
 * Disconnect from a service (dispose of the commhandle).
 * Creation date: (4/16/00 11:52:37 AM)
 */
public void disconnect()
{
	try
	{
		if (commHandle != null)
		{
			log(LogEventId.DEBUG_LEVEL_2, ("ServiceAccess::disconnect(): " + showServiceDetails()));
			commHandle.shutDown();
			commHandle = null;
		}
	}
	catch (MException e)
	{
		log(LogEventId.EXCEPTION, "MException in ServiceAccess::disconnect() - commHandle.shutdown(): " + e.getMessages() + showServiceDetails());
	}
}
/**
 * Flush all the messages on a commHandle.
 * Creation date: (4/16/00 11:52:01 AM)
 */
public void flushMessages()
{
	if (commHandle == null)
		return;

	MEventType event = new MEventType();
	try
	{
		while (commHandle.peekEvent(event, 1) == commHandle.EVENT_PRESENT)
			commHandle.flushNextMsg();
	}
	catch (MCommPeerClosedException e)
	{
		log(LogEventId.INFO_LEVEL_1, "ServiceAccess::flushMessages(): MCommPeerClosedException on commHandle.flushNextMsg(): "
			+ e.getMessages());
	}
	catch (MCommTermException e)
	{
		log(LogEventId.INFO_LEVEL_1, "ServiceAccess::flushMessages(): MCommTermException on commHandle.flushNextMsg(): "
			+ e.getMessages());
	}
	catch (MCommIOException e)
	{
		log(LogEventId.INFO_LEVEL_1, "ServiceAccess::flushMessages(): MCommIOException on commHandle.flushNextMsg(): "
			+ e.getMessages());
	}
}
/**
 * Get the env.
 * Creation date: (3/21/02 9:47:59 AM)
 * @return com.sbc.vicunalite.api.MEnv
 */
public com.sbc.vicunalite.api.MEnv getEnv() {
	return env;
}
/**
 * Get the event list.
 * Creation date: (3/21/02 9:47:59 AM)
 * @return com.sbc.vicunalite.api.MProperty
 */
public com.sbc.vicunalite.api.MProperty getEventList() {
	return eventList;
}
/**
 * Get the logger.
 * Creation date: (3/21/02 9:47:59 AM)
 * @return com.sbc.bccs.utilities.Logger
 */
public com.sbc.bccs.utilities.Logger getLogger() {
	return logger;
}
/**
 * Get the property list.
 * Creation date: (3/21/02 9:47:59 AM)
 * @return com.sbc.vicunalite.api.MProperty
 */
public com.sbc.vicunalite.api.MProperty getPropertyList() {
	return propertyList;
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
 * Receive a message.
 * Creation date: (4/16/00 11:53:31 AM)
 * @return com.sbc.gwsvcs.access.vicuna.EventObjectPair
 * @param expected com.sbc.gwsvcs.access.vicuna.EventClassPair[]
 * @param aTimeOut long
 * @exception com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException: an exception occurred.
 */
public EventObjectPair receive(EventClassPair[] expected, long aTimeOut) throws
	ServiceException
{
	try
	{
		long theTimeOut = aTimeOut < 1 ? defaultTimeOut : aTimeOut;
		MEventType returnEvent = new MEventType();
		log(LogEventId.DEBUG_LEVEL_2, ("ServiceAccess::receive().peekEvent(" + theTimeOut + ")"));
		int peekReturn;
		if ((peekReturn = commHandle. peekEvent(returnEvent, theTimeOut)) == commHandle.EVENT_NOT_PRESENT)
			throw new ServiceTimeoutException(ExceptionCode.ERR_VCS_SERVICE_TIMEOUT, "Timeout occurred in receive"
				 + showServiceDetails());
		if (peekReturn == commHandle.EVENT_HANDLED)
			throw new ServiceException(ExceptionCode.ERR_VCS_SERVICE, "Event already handled"
				 + showServiceDetails());
		log(LogEventId.DEBUG_LEVEL_2, ("ServiceAccess::receive() received event: " + returnEvent));
		for (int i = 0; i < expected.length; i++)
		{
			if (returnEvent.equals(expected[i].event))
			{
				MMarshalObject recvObject = (MMarshalObject)expected[i].aClass.newInstance();
				commHandle.recv(recvObject);
				return new EventObjectPair(returnEvent, recvObject, expected[i].eventNbr);
			}
		}

		commHandle.flushNextMsg();
		throw new ServiceException(ExceptionCode.ERR_VCS_SERVICE, "Unexpected event returned in receive " + returnEvent
			 + showServiceDetails());
	}
	catch (ServiceException e)
	{
		throw e;
	}
	catch (MException e)
	{
		throw new ServiceException(ExceptionCode.ERR_VCS_SERVICE, e.getMessages() + showServiceDetails(), e);
	}
	catch (Exception e)
	{
		throw new ServiceException(ExceptionCode.ERR_VCS_SERVICE, e.getMessage() + showServiceDetails(), e);
	}
}
/**
 * Receive an event, check it is correct and flush the associated message.
 * @param expectedEvent com.sbc.vicunalite.api.MEventType
 * @param aTimeOut long
 * @param autoHandleSysMsg boolean
 * @exception com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException: an exception occurred.
 */
private void receiveEventFlushMessageSystem(MEventType expectedEvent, long aTimeOut,
		boolean autoHandleSysMsg)
	throws ServiceException
{
	try
	{
		long theTimeOut = aTimeOut < 1 ? defaultTimeOut : aTimeOut;
		MEventType returnEvent = new MEventType();
		log(LogEventId.DEBUG_LEVEL_2, ("ServiceAccess::receiveEventFlushMessageSystem().peekEvent(" + theTimeOut + ")"));
		int peekReturn;
		if ((peekReturn = commHandle. peekEvent(returnEvent, theTimeOut, autoHandleSysMsg)) == commHandle.EVENT_NOT_PRESENT)
			throw new ServiceTimeoutException(ExceptionCode.ERR_VCS_SERVICE_TIMEOUT, "Timeout occurred in receiveEventFlushMessageSystem"
				 + showServiceDetails());
		if (peekReturn == commHandle.EVENT_HANDLED)
			throw new ServiceException(ExceptionCode.ERR_VCS_SERVICE, "Event already handled"
				 + showServiceDetails());
		log(LogEventId.DEBUG_LEVEL_2, ("ServiceAccess::receiveEventFlushMessageSystem() received event: " + returnEvent));
		if (returnEvent.equals(expectedEvent))
		{
			commHandle.flushNextMsg();
			return;
		}

		commHandle.flushNextMsg();
		throw new ServiceException(ExceptionCode.ERR_VCS_SERVICE, "Unexpected event returned in receiveEventFlushMessageSystem " + returnEvent
			 + showServiceDetails());
	}
	catch (ServiceException e)
	{
		throw e;
	}
	catch (MException e)
	{
		throw new ServiceException(ExceptionCode.ERR_VCS_SERVICE, e.getMessages() + showServiceDetails(), e);
	}
	catch (Exception e)
	{
		throw new ServiceException(ExceptionCode.ERR_VCS_SERVICE, e.getMessage() + showServiceDetails(), e);
	}

}
/**
 * Send a message.
 * Creation date: (4/16/00 11:53:05 AM)
 * @param input com.sbc.gwsvcs.access.vicuna.EventObjectPair
 * @exception com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException: an exception occurred.
 */
public void send(EventObjectPair input) throws ServiceException
{
	try
	{
		log(LogEventId.DEBUG_LEVEL_2, ("ServiceAccess::send() send event: " + input.event));
		commHandle.send((MMarshalObject)input.anObject, input.event);
	}
	catch (MException e)
	{
		throw new ServiceException(ExceptionCode.ERR_VCS_SERVICE, e.getMessages() + showServiceDetails(), e);
	}
	catch (Exception e)
	{
		throw new ServiceException(ExceptionCode.ERR_VCS_SERVICE, e.getMessage() + showServiceDetails(), e);
	}
}
/**
 * Send a message and receive a message.
 * Creation date: (4/16/00 2:45:12 PM)
 * @return com.sbc.gwsvcs.access.vicuna.EventObjectPair
 * @param aTimeOut long
 * @param input com.sbc.gwsvcs.access.vicuna.EventObjectPair
 * @param expected com.sbc.gwsvcs.access.vicuna.EventClassPair[]
 * @exception com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException: an exception occurred.
 */
public EventObjectPair sendAndReceive(long aTimeOut, EventObjectPair input, EventClassPair[] expected)
	throws ServiceException
{
	send(input);
	return receive(expected, aTimeOut);
}
/**
 * Set the default appl data.
 * Creation date: (1/29/01 12:36:57 PM)
 * @param newDefaultApplData java.lang.String
 * @exception com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException: an exception occurred.
 */
public void setDefaultApplData(String newDefaultApplData) throws ServiceException
{
	try
	{
		if (newDefaultApplData == null || newDefaultApplData.length() < 1)
			defaultApplData = null;
		else
		if (defaultApplData == null)
			defaultApplData = new MProperty("applData", new MAny(newDefaultApplData));
		else
			defaultApplData.setValue(new MAny(newDefaultApplData));
	}
	catch (MPropertyException e)
	{
		throw new ServiceException(ExceptionCode.ERR_VCS_SERVICE, e.getMessages() + showServiceDetails(), e);
	}
}
/**
 * Set the default service name.
 * Creation date: (1/29/01 12:36:57 PM)
 * @param newDefaultService java.lang.String
 * @exception com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException: an exception occurred.
 */
public void setDefaultService(String newDefaultService) throws ServiceException
{
	try
	{
		if (newDefaultService == null)
			defaultService = null;
		else
		if (defaultService == null)
			defaultService = new MProperty("service", new MAny(newDefaultService));
		else
			defaultService.setValue(new MAny(newDefaultService));
	}
	catch (MPropertyException e)
	{
		throw new ServiceException(ExceptionCode.ERR_VCS_SERVICE, e.getMessages() + showServiceDetails(), e);
	}
}
/**
 * Set the default time out.
 * Creation date: (1/29/01 12:40:14 PM)
 * @param newDefaultTimeOut long
 */
public void setDefaultTimeOut(long newDefaultTimeOut) {
	defaultTimeOut = newDefaultTimeOut;
}
/**
 * Set the env.
 * Creation date: (3/21/02 9:47:59 AM)
 * @param newEnv com.sbc.vicunalite.api.MEnv
 */
public void setEnv(com.sbc.vicunalite.api.MEnv newEnv) {
	env = newEnv;
}
/**
 * Set the event list.
 * Creation date: (3/21/02 9:47:59 AM)
 * @param newEventList com.sbc.vicunalite.api.MProperty
 */
public void setEventList(com.sbc.vicunalite.api.MProperty newEventList) {
	eventList = newEventList;
}
/**
 * Set the logger.
 * Creation date: (3/21/02 9:47:59 AM)
 * @param newLogger com.sbc.bccs.utilities.Logger
 */
public void setLogger(com.sbc.bccs.utilities.Logger newLogger) {
	logger = newLogger;
}
/**
 * Set a property, dealing with defaults and empty values.
 * Creation date: (1/28/01 4:22:24 PM)
 * @param propertyName java.lang.String
 * @param propertyValue java.lang.String
 * @param originalValue com.sbc.vicunalite.api.MProperty
 * @param zeroIsNone boolean
 * @exception com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException: an exception occurred.
 */
private void setProperty(String propertyName, String propertyValue, MProperty originalValue, boolean zeroIsNone)
	throws ServiceException
{
	try
	{
		if (propertyValue != null)
		{
			if (zeroIsNone && propertyValue.length() < 1)
			{
				if (propertyList.isPropertyDefined(propertyName))
					propertyList.removeProperty(propertyName);
			}
			else
				propertyList.setProperty(propertyName, new MAny(propertyValue));
		}
		else
		if (originalValue != null)
			propertyList.setProperty(propertyName, originalValue.getValue());
		else
		if (propertyList.isPropertyDefined(propertyName))
			propertyList.removeProperty(propertyName);
	}
	catch (MPropertyException e)
	{
		throw new ServiceException(ExceptionCode.ERR_VCS_SERVICE, e.getMessages() + showServiceDetails(), e);
	}
}
/**
 * Set the property list.
 * Creation date: (3/21/02 9:47:59 AM)
 * @param newPropertyList com.sbc.vicunalite.api.MProperty
 */
public void setPropertyList(com.sbc.vicunalite.api.MProperty newPropertyList) {
	propertyList = newPropertyList;
}
/**
 * Show the service details (name, version, appl data) in a format for logging and exceptions.
 * Creation date: (9/20/00 12:38:28 PM)
 * @return java.lang.String
 */
public String showServiceDetails()
{
	String svcDetails = "";
	if (propertyList != null)
	{
		try {
			svcDetails =
					" [Service<" +
				propertyList.getProperty("directory/search/service").getValue().extract_string() + "> " +
					"Version<" 	+
				propertyList.getProperty("directory/search/version").getValue().extract_string() + "> " +
					" ApplData<";
			svcDetails +=
				propertyList.getProperty("directory/search/applData").getValue().extract_string() + ">]";
		}
		catch (MPropertyNotFound e) // Maybe no appl data
		{
			return(svcDetails + "NOT DEFINED>]");
		}
		catch (MException e)
		{
			log(LogEventId.EXCEPTION, "MException in ServiceAccess::showServiceDetails() - propertyList.getProperty(): " + e.getMessages());
			return("");
		}
	}
	return svcDetails;
}
/** com.sbc.eia.idl.bis_types
 * Self test using connect/disconnect and optional send/receive.
 * @return com.sbc.eia.idl.bis_types.BisContext
 * @param bisContext com.sbc.eia.idl.bis_types.BisContext
 * @param applData java.lang.String
 * @param service java.lang.String
 * @exception com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException: an exception occurred.
 */
public BisContext selfTest(BisContext bisContext, String applData, String service)
	throws ServiceException
{
	log(LogEventId.INFO_LEVEL_1, "ServiceAccess::selfTest()");
	try {
		connect(applData, service);
	}
	catch (ServiceException e)
	{
		log(LogEventId.ERROR, "ServiceAccess::selfTest FAILED(): " +
			e.getMessage());
		throw e;
	}
	finally
	{
		disconnect();
	}
	log(LogEventId.INFO_LEVEL_1, "ServiceAccess::selfTest successful()");
	return  bisContext;
}
/** com.sbc.eia.idl.bis_types
 * Self test using connect/disconnect and optional send/receive.
 * @return com.sbc.eia.idl.bis_types.BisContext
 * @param bisContext com.sbc.eia.idl.bis_types.BisContext
 * @param sendMessage com.sbc.gwsvcs.access.vicuna.EventObjectPair
 * @param expectedEvent com.sbc.vicunalite.api.MEventType
 * @param autoHandleSysMsg boolean
 * @param applData java.lang.String
 * @param service java.lang.String
 * @param timeOut long
 * @exception com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException: an exception occurred.
 */
public BisContext selfTest(BisContext bisContext, EventObjectPair sendMessage,
		MEventType expectedEvent, boolean autoHandleSysMsg, String applData,
		String service, long timeOut)
	throws ServiceException
{
	log(LogEventId.INFO_LEVEL_1, "ServiceAccess::selfTest()");
	try {
		connect(applData, service);
		send(sendMessage);
		receiveEventFlushMessageSystem(expectedEvent, timeOut, autoHandleSysMsg);
	}
	catch (ServiceException e)
	{
		log(LogEventId.ERROR, "ServiceAccess::selfTest FAILED(): " +
			e.getMessage());
		throw e;
	}
	finally
	{
		disconnect();
	}
	log(LogEventId.INFO_LEVEL_1, "ServiceAccess::selfTest successful()");
	return  bisContext;
}
}
