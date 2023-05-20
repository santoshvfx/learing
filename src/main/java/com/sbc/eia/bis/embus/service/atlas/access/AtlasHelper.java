// $Id: AtlasHelper.java,v 1.5 2008/02/25 22:13:02 jd3462 Exp $

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

package com.sbc.eia.bis.embus.service.atlas.access;

import java.util.Hashtable;
import java.util.Properties;

import javax.xml.bind.Marshaller;

import com.sbc.bccs.utilities.Logger;
import com.sbc.eia.bis.embus.service.access.ServiceAccess;
import com.sbc.eia.bis.embus.service.access.ServiceException;
import com.sbc.eia.bis.embus.service.access.ServiceHelper;
import com.sbc.eia.bis.embus.service.atlas.interfaces.InquireMarketServiceAreasRequest;
import com.sbc.eia.bis.embus.service.atlas.interfaces.InquireMarketServiceAreasRequestInfo;
import com.sbc.eia.bis.embus.service.atlas.interfaces.InquireMarketServiceAreasResponse;
import com.sbc.eia.bis.embus.service.atlas.interfaces.InquireMarketServiceAreasResponseInfo;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.embus.common.MessageConstants;

/** 
 *  Used for Atlas requests.
 *  
 */

public class AtlasHelper extends ServiceHelper
{
	public final static String name = "ATLAS";
	public final static String ATLAS_REQUEST = "AtlasRequest";
    private final static String aInqMarketServiceAreasMsgTag = "AtlasRCAccess.InquireMarketServiceAreas";
	private final static String MESSAGE_TAG = "deliver.messageTag";

	public void cleanup()
	{
		getServiceAccess().exit();
	}

	/**
	 * @see java.lang.Object#finalize()
	 */
	protected void finalize() throws Throwable
	{
		super.finalize();

		try
		{
			cleanup();
		}
		catch (Exception e)
		{

		}
	}

	/**
	 * Method AtlasHelper.
	 * @param properties
	 * @param logger
	 * @throws ServiceException
	 */
	public AtlasHelper(Hashtable properties, Logger logger) throws ServiceException
	{
		super(properties, logger, name);

		setServiceAccess(new AtlasAccess(getEnvironmentName(), getClientConfigFileName(), logger));

	}

    /**
     * Method sendInquireMarketServiceAreasRequests.
     * @param request
     * @param converId
     * @param propertiesFromResponseMessage
     * @return InquireMarketServiceAreasResponse
     * @throws ServiceException
     */
    public InquireMarketServiceAreasResponse sendInquireMarketServiceAreasRequests(
        InquireMarketServiceAreasRequest request,
        String converId,
        Properties propertiesFromResponseMessage)
        throws ServiceException
    {

        // Set the properties.

        Properties options = new Properties();

        options.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, "true");

        // Set the encoder for the request class.

        getServiceAccess().setEncoder(
            new AtlasEncoderDecoder(InquireMarketServiceAreasRequestInfo.class.getPackage().getName(), options));

        // Set the decoder for the response class.

        getServiceAccess().setDecoder(
            new AtlasEncoderDecoder(InquireMarketServiceAreasRequestInfo.class.getPackage().getName(), options));

        Properties propsToSet = new Properties();
        
        if (converId != null)
        {
            propsToSet.setProperty(MessageConstants.CONVERSATION_KEY, converId);
        }

        // With the new ServiceAccess/JMSOps Framework, the messageTag needs to be passed via deliveryConfigOverrides
         
        Properties deliveryConfigOverrides = new Properties();
        deliveryConfigOverrides.setProperty(MESSAGE_TAG, aInqMarketServiceAreasMsgTag);

        return (InquireMarketServiceAreasResponse) getServiceAccess().sendAndReceive(
            ATLAS_REQUEST,
            new Object[] { request },
            propsToSet,
            propertiesFromResponseMessage,
            deliveryConfigOverrides)[0];
    }
}
