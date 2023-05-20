// $Id: PasswordDecrypterI.java,v 1.1 2005/06/20 20:29:45 as5472 Exp $

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
 *  This file contains the PasswordDecrypterI interface used to define the
 *  method signature used to decrypt encrypted passwords in the EMBus
 *  configuration file.
 *  Description
 */
package com.sbc.eia.bis.embus.service.access;

/** Description
 *  This is the PasswordDecrypterI interface.  It describes the method signature
 *  used by services to decrypt encrypted passwords in the EMBus configuration
 *  file.
 *  Description
 */
public interface PasswordDecrypterI
{
    /**
     * Method decryptPassword.
     * @param encPassword the encrypted password
     * @return String the decrypted password
     * @throws Exception any exception that may occur during decryption
     */
    public String decryptPassword(String encPassword) throws Exception;
    
}
