// $Id: ServiceTimeOutException.java,v 1.1 2004/03/05 00:17:19 as5472 Exp $

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

/** Description
 *  Appropriate description of what this class is used for.
 *  Description
 */
public class ServiceTimeOutException extends ServiceException
{
    /**
     * Constructor for ServiceTimeOutException.
     * @param serviceExceptionMessage
     * @param originalException
     */
    public ServiceTimeOutException(
        String serviceExceptionMessage,
        Exception originalException)
    {
        super(serviceExceptionMessage, originalException);
    }

    /**
     * Constructor for ServiceTimeOutException.
     * @param serviceExceptionMessage
     */
    public ServiceTimeOutException(String serviceExceptionMessage)
    {
        super(serviceExceptionMessage);
    }

    /**
     * Constructor for ServiceTimeOutException.
     * @param originalException
     */
    public ServiceTimeOutException(Exception originalException)
    {
        super(originalException);
    }

}
