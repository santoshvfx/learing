// $Id: ServiceHelperHolder.java,v 1.1 2002/09/29 05:31:38 dm2328 Exp $

package com.sbc.gwsvcs.access.vicuna;

import com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.gwsvcs.access.vicuna.exceptions.*;
/**
 * Manages a ServiceHelper and its connectivity.
 * Creation date: (8/31/01 9:26:32 AM)
 * @author: Creighton Malet
 */
public class ServiceHelperHolder
{
	private ServiceHelperHolderSupport serviceHelperHolderSupport = null;
	private com.sbc.bccs.utilities.Logger logger = null;
	private ServiceHelper serviceHelper = null;
	private boolean connected = false;
	private boolean keepConnectionOpen = true;

	public static final String CONNECT_IN_EJBCREATE = "CONNECT_IN_EJBCREATE";
	public static final String KEEP_CONNECTION_OPEN = "KEEP_CONNECTION_OPEN";
	public static final String FALSE = "FALSE";
/**
 * Class constructor.
 */
private ServiceHelperHolder()
{
}
/**
 * Class constructor accepting parameters.
 * @param aServiceHelperHolderSupport com.sbc.gwsvcs.access.vicuna.aServiceHelperHolderSupport
 * @param aLogger com.sbc.bccs.utilities.Logger
 */
public ServiceHelperHolder(ServiceHelperHolderSupport aServiceHelperHolderSupport,
	com.sbc.bccs.utilities.Logger aLogger)
{
	serviceHelperHolderSupport = aServiceHelperHolderSupport;
	logger = aLogger;
}
/**
 * Connects the ServiceHelper.
 * Creation date: (8/31/01 9:38:46 AM)
 * @param boolean flushMessages
 * @exception com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException: a service exception occurred.
 */
public void connect(boolean flushMessages) throws ServiceException
{
	// If already connected just return.
	if (connected)
	{
		if (flushMessages)
			serviceHelper.flushMessages();
		return;
	}

	// If the helper hasn't been created do that.
	if (serviceHelper == null)
	{
		serviceHelper = serviceHelperHolderSupport.getNewServiceHelper();
	}

	// Have the helper connect.
	try {
		serviceHelper.connect(null, null);
	}
	catch (ServiceException e)
	{
		serviceHelper = null;
		throw e;
	}
	connected = true;
}
/**
 * Connects the ServiceHelper, suppresing exceptions.
 * Creation date: (8/31/01 9:38:46 AM)
 * @param boolean flushMessages
 */
public void connectNoException(boolean flushMessages)
{
	// Catch exceptions for the caller
	try {
		connect(flushMessages);
	}
	catch (ServiceException e)
	{
		log(LogEventId.EXCEPTION, "ServiceHelperHolder::connect() failure: " + e.getMessages());
		return;
	}
}
/**
 * Disconnects the ServiceHelper.
 * Creation date: (8/31/01 9:38:46 AM)
 */
public void disconnect()
{
	if (! connected)
		return;

	try {
		serviceHelper.disconnect();
		connected = false;
	}
	catch (ServiceException e)
	{
		connected = false;
		serviceHelper = null;
		log(LogEventId.EXCEPTION, "ServiceHelperHolder::disconnect() failure: " + e.getMessages());
	}
}
/**
 * Gets the ServiceHelper.
 * Creation date: (8/31/01 11:01:33 AM)
 * @return com.sbc.gwsvcs.access.vicuna.ServiceHelper
 */
public ServiceHelper getServiceHelper() {
	return serviceHelper;
}
/**
 * Gets whether to keep the connection open.
 * Creation date: (1/2/02 12:09:50 PM)
 * @return boolean
 */
public boolean isKeepConnectionOpen() {
	return keepConnectionOpen;
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
 * Runs a transaction, handling connections, exceptions and retries for the caller.
 * @param transactionId int
 * @param aContext java.lang.Object
 * @param input java.lang.Object
 * @param aTimeOut long
 * @param retry boolean
 * @param logName String
 * @param flushMessages boolean
 * @return com.sbc.gwsvcs.access.vicuna.EventResultPair
 * @exception com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException: a service exception occurred.
 */
public com.sbc.gwsvcs.access.vicuna.EventResultPair run(int transactionId, Object aContext, Object input,
		long aTimeOut, boolean retry, String logName, boolean flushMessages)
	throws ServiceException
{
	// Set the context into the logger
	serviceHelperHolderSupport.setCallerContext(aContext);

	// Log the entry
	log(LogEventId.ENTRY, logName);

	// Send, receive and return the result
	ServiceException serviceException = null;
	try
	{
		connect(flushMessages);
		return serviceHelperHolderSupport.runTransaction(transactionId, input, aTimeOut);
	}
	catch (ServiceException e)
	{
		try {
			serviceException = serviceHelperHolderSupport.filterException(e);
		}
		catch (ServiceTimeoutException s)
		{
			log(LogEventId.EXCEPTION, "ServiceTimeOutException: " + e.getMessage());
			disconnect();
			throw e;
		}
		catch (ServiceException s)
		{
			log(LogEventId.EXCEPTION, "ServiceException: " + e.getMessages());
			disconnect();
			if (retry)
				return(run(transactionId, aContext, input, aTimeOut, false, logName, false));
			else
				throw e;
		}
	}
	finally
	{
		// Close the connection, if so configured, and log the exit
		if (! keepConnectionOpen)
			disconnect();
		log(LogEventId.EXIT, logName);
	}
	throw serviceException;
}
/**
 * Sets whether to keep the connection open.
 * Creation date: (1/2/02 12:09:50 PM)
 * @param newKeepConnectionOpen boolean
 */
public void setKeepConnectionOpen(boolean newKeepConnectionOpen) {
	keepConnectionOpen = newKeepConnectionOpen;
}
}
