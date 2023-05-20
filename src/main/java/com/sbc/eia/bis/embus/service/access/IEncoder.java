// $Id: IEncoder.java,v 1.1.6.1 2004/03/11 19:06:36 as5472 Exp $

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
 *  This file contains the IEncoder interface definition.
 *  Description
 */
package com.sbc.eia.bis.embus.service.access;

/** Description
 *  The IEncoder interface defines the signature for all encoders.
 *  Description
 */
public interface IEncoder
{
    /**
     * Method encode takes an array of java objects to encode into a String
     * of data (usually but not necessarily to XML).
     * @param objectArray Object[] an array of java objects to convert to
     * a String of data
     * @return String the String representing the java objects
     * @throws ServiceException
     * @throws EncoderException
     */
    public abstract String encode(
        Object[] objectArray) throws ServiceException, EncoderException;
}
