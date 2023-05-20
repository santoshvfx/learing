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

import java.util.Properties;

/** Description
 *  Appropriate description of what this class is used for.
 *  Description
 */
public class ResourceConnectorServiceException extends ServiceException
{
    private String errorCode = null;
    private String errorDescription = null;
    private Properties properties = null;

    /**
     * Constructor for ResourceConnectorServiceException.
     * @param i_serviceExceptionMessage
     * @param i_originalException
     */
    public ResourceConnectorServiceException(
        String i_serviceExceptionMessage,
        Exception i_originalException)
    {
        super(i_serviceExceptionMessage, i_originalException);
    }

    /**
     * Constructor for ResourceConnectorServiceException.
     * @param i_serviceExceptionMessage
     */
    public ResourceConnectorServiceException(String i_serviceExceptionMessage)
    {
        super(i_serviceExceptionMessage);
    }

    /**
     * Constructor for ResourceConnectorServiceException.
     * @param i_originalException
     */
    public ResourceConnectorServiceException(Exception i_originalException)
    {
        super(i_originalException);
    }
    
    public ResourceConnectorServiceException( String code, String description, Properties properties, String body )
    {
        super(body);
        errorCode = code;
        errorDescription = description;
        this.properties = properties;
    }

    public static void main(String[] args)
    {
    }
    /**
     * Returns the errorCode.
     * @return String
     */
    public String getErrorCode()
    {
        return errorCode;
    }

    /**
     * Returns the errorDescription.
     * @return String
     */
    public String getErrorDescription()
    {
        return errorDescription;
    }

    /**
     * Returns the properties.
     * @return Properties
     */
    public Properties getPropertiesInResponse()
    {
        return properties;
    }

    /**
     * Sets the errorCode.
     * @param errorCode The errorCode to set
     */
    public void setErrorCode(String errorCode)
    {
        this.errorCode = errorCode;
    }

    /**
     * Sets the errorDescription.
     * @param errorDescription The errorDescription to set
     */
    public void setErrorDescription(String errorDescription)
    {
        this.errorDescription = errorDescription;
    }

    /**
     * Sets the properties.
     * @param properties The properties to set
     */
    public void setPropertiesInResponse(Properties properties)
    {
        this.properties = properties;
    }

}
