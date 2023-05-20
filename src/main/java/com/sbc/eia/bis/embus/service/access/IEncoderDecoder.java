// $Id: IEncoderDecoder.java,v 1.1.6.1 2004/03/11 19:06:36 as5472 Exp $

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
 *  This file contains the IEncoderDecoder interface definition
 *  Description
 */
package com.sbc.eia.bis.embus.service.access;

/** Description
 *  The IEncoderDecoder simply combines both IEncoder and IDecoder interface
 *  definitions.  The current intent is to simply provided a convenient way
 *  to indicate a class is oth an encoder and a decoder.
 *  Description
 */
public interface IEncoderDecoder extends IEncoder, IDecoder
{

}
