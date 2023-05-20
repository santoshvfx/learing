// $Id: ServiceHelper.java,v 1.4 2005/02/03 17:17:49 as5472 Exp $

/* Copyright Notice
 * RESTRICTED - PROPRIETARY INFORMATION
 * The information herein is for use only by authorized employees
 * of SBC Services Inc. and authorized Affiliates of SBC Services,
 * Inc., and is not for general distribution within or outside the
 * the respective companies.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2004 SBC Services, Inc.
 * All rights reserved.
 */

/** Description
 *  Appropriate description of what this file (or classes within) is used for.
 *  Description
 */
package com.sbc.eia.bis.embus.service.access;

import java.util.Hashtable;
import java.util.Properties;

import javax.jms.JMSException;

import com.sbc.bccs.utilities.Logger;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.embus.common.MessageConstants;

/** Description
 *  Appropriate description of what this class is used for.
 *  Description
 */
public abstract class ServiceHelper
{
	private ServiceAccess m_serviceAccess = null;
	private String m_environmentName = null;
	private String m_clientConfigFileName = null;
	private String m_notificationLevel = null;

	private Logger m_logger = null;

	private final static String CLIENT_CONFIG_FILE_NAME_KEY_SUFFIX = "_CLIENT_CONFIG_FILE_NAME";
	private final static String ENVIRONMENT_NAME_KEY_SUFFIX = "_ENVIRONMENT_NAME";
	private final static String NOTIFICATION_LEVEL_KEY_SUFFIX = "_NOTIFICATION_LEVEL";

	protected String m_serviceName = null;

	/**
	 * Method cleanup asks its ServiceAccess to perform clean up duties.  The
	 * ServiceAccess should "dismiss" its messenger to release its resources
	 * if any.
	 */
	public void cleanup()
	{
		getServiceAccess().exit();
	}

	/**
	 * Constructor for ServiceHelper.
	 * @param properties Hashtable
	 * @param logger Logger
	 * @param serviceName String the name of the Service, this name is used
	 * as part of a key to retrieve the configuration file name/location and
	 * the enviorment name used to configure the ServiceAcccess.  The service
	 * name should not be confused with the actual environment name used to
	 * configure the service access.
	 */
	public ServiceHelper(Hashtable properties, Logger logger, String serviceName)
	{
		m_serviceName = serviceName;

		setLogger(logger);
		setEnvironmentName((String) properties.get(serviceName.toUpperCase() + ENVIRONMENT_NAME_KEY_SUFFIX));
		setClientConfigFileName(
			(String) properties.get(serviceName.toUpperCase() + CLIENT_CONFIG_FILE_NAME_KEY_SUFFIX));
		setNotificationLevel((String) properties.get(serviceName.toUpperCase() + NOTIFICATION_LEVEL_KEY_SUFFIX));
	}

	/**
	 * Method setClientConfigFileName.
	 * @param clientConfigFileName
	 */
	private void setClientConfigFileName(String i_clientConfigFileName)
	{
		m_clientConfigFileName = i_clientConfigFileName;
	}

	/**
	 * Method getClientConfigFileName.
	 * @return String
	 */
	protected String getClientConfigFileName()
	{
		return m_clientConfigFileName;
	}

	/**
	 * Method setEnvironmentName.
	 * @param environmentName
	 */
	private void setEnvironmentName(String i_environmentName)
	{
		m_environmentName = i_environmentName;
	}

	/**
	 * Method getEnvironmentName.
	 * @return String
	 */
	protected String getEnvironmentName()
	{
		return m_environmentName;
	}

	/**
	 * Method setNotificationLevel.
	 * @param notificationLevel
	 */
	private void setNotificationLevel(String notificationLevel)
	{
		m_notificationLevel = notificationLevel;
	}

	/**
	 * Method getNotificationLevel.
	 * @return String
	 */
	protected String getNotificationLevel()
	{
		return m_notificationLevel;
	}

	/**
	 * Method setServiceAccess.
	 * @param serviceAccess
	 */
	protected void setServiceAccess(ServiceAccess i_serviceAccess)
	{
		m_serviceAccess = i_serviceAccess;
	}

	/**
	 * Method getServiceAccess.
	 * @return ServiceAccess
	 */
	protected ServiceAccess getServiceAccess()
	{
		return m_serviceAccess;
	}

	/**
	 * Method setLogger.
	 * @param logger
	 */
	private void setLogger(Logger i_logger)
	{
		m_logger = i_logger;
	}

	/**
	 * Method getLogger.
	 * @return Logger
	 */
	private Logger getLogger()
	{
		return m_logger;
	}

	/**
	 * Method log.
	 * @param eventId
	 * @param message
	 */
	protected void log(String i_eventId, String i_message)
	{
		if (getLogger() != null)
		{
			getLogger().log(i_eventId, i_message);
		}
	}

	/**
	 * overridden to ensure cleanup is performed
	 * @see java.lang.Object#finalize()
	 */
	protected void finalize() throws Throwable
	{
		super.finalize();
		cleanup();
	}

	/**
	 * Method selfTest.
	 */
	public BisContext selfTest(BisContext aBisContext) throws ServiceException
	{

		log(LogEventId.DEBUG_LEVEL_2, "ServiceHelper::selfTest started.");

		try
		{
		    getServiceAccess().selfTest();
		}
		catch(Exception e)
		{
		    log(LogEventId.DEBUG_LEVEL_2, "ServiceHelper::selfTest failed. Exception message: " + e.getMessage());
			throw new ServiceHelperSelfTestException("ServiceHelper::selfTest failed! " + e.getMessage(),	e);
		}
		    
		log(LogEventId.DEBUG_LEVEL_2, "ServiceHelper::selfTest completed.");

		return aBisContext;
	}


}
