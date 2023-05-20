// $Id: DecoderException.java,v 1.3 2004/03/11 19:12:09 as5472 Exp $

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
 *  This file contains the DecoderException class.
 *  Description
 */
package com.sbc.eia.bis.embus.service.access;

/** Description
 *  This is the decoder exception class.  A type of service exception, decoder
 *  exceptions are used to indicate an exception has occurred while decoding
 *  XML into a java object(s).
 *  Description
 */
public class DecoderException extends ServiceException
{

    /**
     * This constructor should be used to create a DecoderException where an
     * original exception exists and you want to provide a custom service
     * exception message.
     * @param i_serviceExceptionMessage String the message indicating the error.
     * @param i_originalException Exception the original exception that was thrown
     */
    public DecoderException( String i_serviceExceptionMessage,
        Exception i_originalException)
    {
        super(i_serviceExceptionMessage, i_originalException);
    }

    /**
     * Constructor for DecoderException.  Use this constructor if you want to
     * save the original exception for use by the caller.
     * @param i_originalException Exception the original exception that was thrown
     */
    public DecoderException(Exception i_originalException)
    {
        super(i_originalException);
    }

    /**
     * This constructor should be used to create a DecoderException that 
     * indicates that the some precondition check has failed while performing 
     * a decode operation.  Use this constructor when there is no system
     * exception and you wish to indicate an error has occured.
     * @param i_serviceExceptionMessage String the message indicating the error.
     */
    public DecoderException( String i_serviceExceptionMessage )
    {
        super( i_serviceExceptionMessage );
    }

}
