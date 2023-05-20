// $Id: InquireMarketServiceAreasRequestHelper.java,v 1.2 2008/02/25 22:14:51 jd3462 Exp $

/* Copyright Notice
 * RESTRICTED - PROPRIETARY INFORMATION
 * The information herein is for use only by authorized employees
 * of SBC Services Inc. and authorized Affiliates of SBC Services,
 * Inc., and is not for general distribution within or outside the
 * the respective companies.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2005 SBC Services, Inc.
 * All rights reserved.    
 */

package com.sbc.eia.bis.embus.service.atlas.utilities;

import com.sbc.eia.bis.embus.service.atlas.interfaces.InquireMarketServiceAreasRequest;
import com.sbc.eia.bis.embus.service.atlas.interfaces.InquireMarketServiceAreasRequestInfo;
import com.sbc.eia.bis.embus.service.atlas.interfaces.impl.InquireMarketServiceAreasRequestImpl;
import com.sbc.eia.bis.embus.service.atlas.interfaces.impl.InquireMarketServiceAreasRequestInfoImpl;
import com.sbc.eia.bis.embus.service.atlas.interfaces.impl.InquireMarketServiceAreasResponseInfoImpl;

/**
 * @author jd3462
 *
 * This is a high level class to encapsulate the XML  generated objects.
 */
public class InquireMarketServiceAreasRequestHelper
{
    protected InquireMarketServiceAreasRequestImpl theRequest;
    
    public InquireMarketServiceAreasRequestHelper (String postalCode) 
    {
        theRequest = new InquireMarketServiceAreasRequestImpl ();
        theRequest.setRequestType("Activation");
        
        InquireMarketServiceAreasRequestInfoImpl.ServiceAreasSelectorTypeImpl.SelectorsWithMarketTypeTypeImpl
        selectorsWithMarketType = new InquireMarketServiceAreasRequestInfoImpl.ServiceAreasSelectorTypeImpl.SelectorsWithMarketTypeTypeImpl();
        selectorsWithMarketType.setZipCode(postalCode);
        selectorsWithMarketType.setMarketType("Both");
        
        InquireMarketServiceAreasRequestInfoImpl.ServiceAreasSelectorTypeImpl selectorType = new InquireMarketServiceAreasRequestInfoImpl.ServiceAreasSelectorTypeImpl();
        selectorType.setSelectorsWithMarketType(selectorsWithMarketType);
        theRequest.setServiceAreasSelector(selectorType);
    }

    public InquireMarketServiceAreasRequest getRequest() 
    {
        return theRequest;
    }

}
