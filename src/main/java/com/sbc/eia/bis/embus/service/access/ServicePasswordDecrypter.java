// $Id: ServicePasswordDecrypter.java,v 1.1 2005/06/20 20:29:45 as5472 Exp $

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

import com.sbc.eia.bis.encryption.Encryption;
import com.sbc.eia.bis.encryption.EncryptionIO;

/** Description
 *  Appropriate description of what this class is used for.
 *  Description
 */
public class ServicePasswordDecrypter implements PasswordDecrypterI
{
    private final static String PASSWORD_KEY_KEY_SUFFIX =
        "_PASSWORD_KEY";
    private String passKey;
    
    /**
     * Method ServicePasswordDecrypter.
     * @param name String the service name, this is used as part of a key to
     * retrieve the password key for the specific service
     * @param props Hashtable the hash containing the key and value for the
     * password key.
     */
    public ServicePasswordDecrypter(String name, Hashtable props)
    {
        super();
        passKey = (String)props.get(name + PASSWORD_KEY_KEY_SUFFIX);
    }
    
    /**
     * Method ServicePasswordDecrypter.
     * @param key the password key
     */
    public ServicePasswordDecrypter(String key)
    {
        super();
        passKey = key;
    }

    /**
     * @see com.sbc.eia.bis.embus.service.access.PasswordDecrypterI#decryptPassword(String)
     */
    public String decryptPassword(String encryptedPassword) throws Exception
    {
        Encryption enc = new Encryption();
        return enc.decodePassword(passKey, EncryptionIO.loadConvert(encryptedPassword));
    }

}
