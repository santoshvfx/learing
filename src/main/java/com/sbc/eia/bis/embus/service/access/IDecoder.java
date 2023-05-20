// $Id: IDecoder.java,v 1.2 2004/03/11 19:12:10 as5472 Exp $

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
 *  This file contains the IDecoder interface.
 *  Description
 */
package com.sbc.eia.bis.embus.service.access;

/** Description
 *  The IDecoder interface defines the decode signature for all Decoders.
 *  Description
 */
public interface IDecoder
{
    /**
     * Method decode uses the information in a String (usually XML) to create
     * a object or an array of java objects.  The String will usually contain
     * the data to use to populate the new objects.
     * @return Object[] the array of objects decoded from the String
     * @throws ServiceException
     * @throws DecoderException
     */
    public abstract Object[] decode(
        String i_stringToDecode) throws ServiceException, DecoderException;
}
