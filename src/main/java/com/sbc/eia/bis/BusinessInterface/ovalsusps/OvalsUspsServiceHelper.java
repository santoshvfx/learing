//$Id: OvalsUspsServiceHelper.java,v 1.8 2008/07/17 16:55:24 nl9267 Exp $

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
 *  This file contains the OvalsUspsServiceHelper.
 *  @author Noemi Luzung 6/11/2008 
 *  Enhancements for OvalsUsps Helper
 */
package com.sbc.eia.bis.BusinessInterface.ovalsusps;

import java.util.Hashtable;
import java.util.Properties;

import com.sbc.bccs.utilities.Logger;
import com.sbc.eia.bis.embus.service.access.ServiceException;
import com.sbc.eia.bis.embus.service.access.ServiceHelper;
import com.sbc.eia.bis.embus.service.access.ServicePasswordDecrypter;
import com.sbc.eia.bis.lim.jaxb.ovalsusps.OVALS;
import com.sbc.eia.bis.lim.util.LIMBase;

/** Description
 *  The OvalsUspsServiceHelper helps configure the service access layer
 *  and provides a view of service specific methods.
 *  Description
 */
public class OvalsUspsServiceHelper extends ServiceHelper
{
    /*
     * The name of the ovalsusps service, this value is used as part of
     * a configuration property to find both the envionment name
     * and the service access configuration file.
     */
    private final static String SERVICENAME = "OVALSUSPS";
    
    /**
     * Constructor for OvalsUspsServiceHelper.
     */
    public OvalsUspsServiceHelper(Hashtable properties, LIMBase base)
        throws ServiceException
    {
        super( properties, base, SERVICENAME );
        setServiceAccess(
            new OvalsUspsServiceAccess(
                getEnvironmentName(),
                getClientConfigFileName(),
                base));
    }

    
    /**
     * Constructor for OvalsUspsServiceHelper SelfTest
     */
   public OvalsUspsServiceHelper (Hashtable hashTable, Logger logger)
        throws ServiceException
		
		{
				super( hashTable, logger, SERVICENAME );
        setServiceAccess(
                new OvalsUspsServiceAccess(
                    getEnvironmentName(),
                    getClientConfigFileName(),
                    logger));
		}
   
   
    public OVALS ovalsUspsRequestAndResponse(
        OvalsUspsRetrieveLocReq request)
        throws ServiceException
    {
        return (OVALS) getServiceAccess().sendAndReceive(
            "getLocationPropertiesByAddress",
            new Object[] { request })[0];
    }

    /**
     * Closes any existing messaging artifacts that may cause resource
     * leaks.
     */
    public void exit()
    {
        getServiceAccess().exit();

    }

}
