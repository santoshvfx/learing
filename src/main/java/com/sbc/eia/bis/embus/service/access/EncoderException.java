// $Id: EncoderException.java,v 1.3 2004/03/11 19:12:10 as5472 Exp $

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
 *  This file contains the EncoderException class.
 *  Description
 */
package com.sbc.eia.bis.embus.service.access;

/** Description
 *  The EncoderException should be used to indicate that an exception
 *  was encountered while trying to encode an object to XML.
 *  Description
 */
public class EncoderException extends ServiceException
{

    /**
     * Constructor for EncoderException.
     * @param i_serviceExceptionMessage String 
     * @param i_originalException Exception
     */
    public EncoderException( String i_serviceExceptionMessage,
        Exception i_originalException)
    {
        super( i_serviceExceptionMessage, i_originalException);
    }

    /**
     * Constructor for EncoderException.
     * @param i_originalException Exception
     */
    public EncoderException(Exception i_originalException)
    {
        super(i_originalException);
    }

    /**
     * Constructor for EncoderException.
     * @param i_serviceExceptionMessage String
     */
    public EncoderException( String i_serviceExceptionMessage )
    {
        super(i_serviceExceptionMessage);
    }

}
