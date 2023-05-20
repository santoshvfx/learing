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
 *  This file contains the ReceiveTimeOutException.
 *  Description
 */
package com.sbc.eia.bis.embus.service.access;

/** Description
 *  The ReceiveTimeOutException class should be used to indicate that a
 *  message receive has timed out.
 *  Description
 */
public class ReceiveTimeOutException extends ServiceTimeOutException
{

    /**
     * Constructor for ReceiveTimeOutException.
     * @param serviceExceptionMessage
     * @param originalException
     */
    public ReceiveTimeOutException(
        String serviceExceptionMessage,
        Exception originalException)
    {
        super(serviceExceptionMessage, originalException);
    }

    /**
     * Constructor for ReceiveTimeOutException.
     * @param serviceExceptionMessage
     */
    public ReceiveTimeOutException(String serviceExceptionMessage)
    {
        super(serviceExceptionMessage);
    }

    /**
     * Constructor for ReceiveTimeOutException.
     * @param originalException
     */
    public ReceiveTimeOutException(Exception originalException)
    {
        super(originalException);
    }

}
