// $Id:$

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

import javax.jms.Message;

import com.sbc.bccs.utilities.Logger;

/** Description
 *  A simple class for implementing the default behavior of a ServiceAccess.
 *  Description
 */
public class DefaultServiceAccess extends ServiceAccess
{

    /**
     * Constructor for DefaultServiceAccess.
     * @param environmentName
     * @param configFileName
     * @param logger
     * @throws ServiceException
     */
    public DefaultServiceAccess(
        String environmentName,
        String configFileName,
        Logger logger)
        throws ServiceException
    {
        super(environmentName, configFileName, logger);
    }

    /**
     * Constructor for DefaultServiceAccess.
     * @param environmentName
     * @param configFileName
     * @param notificationLevel
     * @param logger
     * @throws ServiceException
	 * @deprecated This method is deprecated. The parameter notificationLevel is no 
	 * longer being used. Use DefaultServiceAccess(environmentName, configFileName, logger)
	 * instead. 
     */
    public DefaultServiceAccess(
        String environmentName,
        String configFileName,
        String notificationLevel,
        Logger logger)
        throws ServiceException
    {
        super(environmentName, configFileName, logger);
    }

    /**
     * Constructor for DefaultServiceAccess.
     * @param environmentName
     * @param configFileName
     * @param notificationLevel
     * @param additionalCustomProperties
     * @param logger
     * @throws ServiceException
	 * @deprecated This method is deprecated. The parameter notificationLevel is no 
	 * longer being used. Use DefaultServiceAccess(environmentName, configFileName, additionalCustomProperties, logger)
	 * instead. 
     */
    public DefaultServiceAccess(
        String environmentName,
        String configFileName,
        String notificationLevel,
        Hashtable additionalCustomProperties,
        Logger logger)
        throws ServiceException
    {
        super(
            environmentName,
            configFileName,
            additionalCustomProperties,
            logger);
    }

    /**
     * Constructor for DefaultServiceAccess.
     * @param environmentName
     * @param configFileName
     * @param additionalCustomProperties
     * @param logger
     * @throws ServiceException
     */
    public DefaultServiceAccess(
        String environmentName,
        String configFileName,
        Hashtable additionalCustomProperties,
        Logger logger)
        throws ServiceException
    {
        super(
            environmentName,
            configFileName,
            additionalCustomProperties,
            logger);
    }
    
    /**
     * @see com.sbc.eia.bis.embus.service.access.ServiceAccess#handleEmbusResponse(Message, Properties)
     */
    public String handleEmbusResponse(
        Message aMessage,
        Properties propertiesInResponse)
        throws ServiceException
    {
        return defaultHandleEmbusResponse(aMessage, propertiesInResponse);
    }
}
