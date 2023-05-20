// $Id: AtlasService.java,v 1.4 2008/02/08 17:54:08 jd3462 Exp $

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


package com.sbc.eia.bis.embus.service.atlas;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import com.sbc.bccs.utilities.Logger;
import com.sbc.eia.bis.embus.service.access.ServiceException;
import com.sbc.eia.bis.embus.service.atlas.access.AtlasHelper;
import com.sbc.eia.bis.embus.service.atlas.interfaces.InquireMarketServiceAreasRequest;
import com.sbc.eia.bis.embus.service.atlas.interfaces.InquireMarketServiceAreasResponse;


/**
 * @author hw7243
 *
 * AtlasService
 * This layer is used to provide access to the Atlas
 */

public class AtlasService
{
    AtlasHelper theAtlasHelper;

    Properties theProperties;
    Logger theLogger;

    /**
     * @see java.lang.Object#Object()
     */
    public AtlasService()
        throws ServiceException
    {
        theAtlasHelper = null;
    }
    
    /**
     * Method AtlasService.
     * @param hash
     * @param logger
     * @throws ServiceException
     */
    public AtlasService(Hashtable hash, Logger logger)
        throws ServiceException
    {
        this();
        
        theLogger = logger;
        theProperties = new Properties();
        
        // Convert the hash to a properties.
        
        Set set = hash.entrySet();
        Iterator it = set.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            theProperties.setProperty((String) entry.getKey(), (String) entry.getValue());
        } 
    }

    /**
     * Method AtlasService.
     * @param properties
     * @param logger
     * @throws ServiceException
     */
    public AtlasService(Properties properties, Logger logger)
        throws ServiceException
    {
        this();
        
        theLogger = logger;
        theProperties = properties;
    }

    /**
     * Method sendInquireMarketServiceAreasRequests.
     * @param request
     * @param converId
     * @param propertiesFromResponseMessage
     * @return InquireMarketServiceAreasResponse
     * @throws ServiceException
     */
    public InquireMarketServiceAreasResponse sendInquireMarketServiceAreasRequests (
        InquireMarketServiceAreasRequest request, 
        String converId,
        Properties propertiesFromResponseMessage)
        throws ServiceException
    {
        if (theAtlasHelper == null)
        {
            theAtlasHelper = new AtlasHelper(theProperties, theLogger);
        }
        return theAtlasHelper.sendInquireMarketServiceAreasRequests(request, converId, propertiesFromResponseMessage);
    }
    
    /**
     * Method getAtlasHelper.
     */
    private AtlasHelper getAtlasHelper() throws ServiceException 
    {
        if (theAtlasHelper == null) {
            theAtlasHelper = new AtlasHelper(theProperties, theLogger);
        }
        return theAtlasHelper;
    }
    
    public void cleanup()
    {
        if( theAtlasHelper != null )
        {
            theAtlasHelper.cleanup();
        }
    }

}
