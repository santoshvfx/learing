
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

import java.util.Enumeration;
import java.util.Properties;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

import com.sbc.bccs.utilities.Logger;
import com.sbc.eia.bis.embus.service.access.ServiceAccess;
import com.sbc.eia.bis.embus.service.access.ServiceException;
import com.sbc.eia.bis.embus.service.atlas.AtlasException;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.embus.common.MessageConstants;

/** 
 *  Atlas access.
 *  Object for access to Atlas.
 */

public class AtlasAccess extends ServiceAccess
{
	private Logger aLogger = null;

	/**
	 * Constructor for AtlasAccess.
	 * @param environmentName
	 * @param configFileName
	 * @param logger
	 * @throws ServiceException
	 */

	public AtlasAccess(
		String environmentName,
		String configurationFileName,
		Logger logger)
		throws ServiceException
	{
		
		super(environmentName, configurationFileName, logger);
		aLogger = logger;
	}
	
	public String handleEmbusResponse(Message responseMessage, Properties jmsMessagePropertiesInResponse)
	throws ServiceException
	{
		
		 String responseXMLString = null;
		
        try {

            if( jmsMessagePropertiesInResponse != null )
            {
                try
                {
                	log(LogEventId.INFO_LEVEL_2, "Retrieving all JMS application properties from the response.");

                    Enumeration propertyNames = responseMessage.getPropertyNames();
                    while(propertyNames.hasMoreElements())
                    {
                        String propName = (String)propertyNames.nextElement();
                        jmsMessagePropertiesInResponse.setProperty(
                            propName,
                            responseMessage.getStringProperty(propName));
                    }
                }
                catch (Exception exception)
                {
                    throw new ServiceException(
                        "Failed to retrieve all JMS properties from the response "
                        + "message.", exception);
                }
                
				try
				{
					log(LogEventId.INFO_LEVEL_2, "Retrieving all JMS Header properties from the response.");
	
					addJMSPropertyToPropertiesInResponse(
						jmsMessagePropertiesInResponse,
						"JMSCorrelationID",
						responseMessage.getJMSCorrelationID());
					addJMSPropertyToPropertiesInResponse(
						jmsMessagePropertiesInResponse,
						"JMSCorrelationIDAsBytes",
						responseMessage.getJMSCorrelationIDAsBytes());
					addJMSPropertyToPropertiesInResponse(
						jmsMessagePropertiesInResponse,
						"JMSDeliveryMode",
						String.valueOf(responseMessage.getJMSDeliveryMode()));
					addJMSPropertyToPropertiesInResponse(
						jmsMessagePropertiesInResponse,
						"JMSDestination",
						responseMessage.getJMSDestination());
					addJMSPropertyToPropertiesInResponse(
						jmsMessagePropertiesInResponse,
						"JMSExpiration",
						String.valueOf(responseMessage.getJMSExpiration()));
					addJMSPropertyToPropertiesInResponse(jmsMessagePropertiesInResponse, "JMSMessageID", responseMessage.getJMSMessageID());
					addJMSPropertyToPropertiesInResponse(
						jmsMessagePropertiesInResponse,
						"JMSPriority",
						String.valueOf(responseMessage.getJMSPriority()));
					addJMSPropertyToPropertiesInResponse(
						jmsMessagePropertiesInResponse,
						"JMSRedelivered",
						String.valueOf(responseMessage.getJMSRedelivered()));
	
					Object replyToDest = responseMessage.getJMSReplyTo();
					// To reduce the amount of log chatter, only attempt to add the
					// JMSReplyTo if it exists.  Most responses will NOT include a
					// JMSReplyTo.
					if (replyToDest != null)
					{
						addJMSPropertyToPropertiesInResponse(jmsMessagePropertiesInResponse, "JMSReplyTo", replyToDest);
					}
	
					addJMSPropertyToPropertiesInResponse(
						jmsMessagePropertiesInResponse,
						"JMSTimestamp",
						String.valueOf(responseMessage.getJMSTimestamp()));
					addJMSPropertyToPropertiesInResponse(jmsMessagePropertiesInResponse, "JMSType", responseMessage.getJMSType());
				}
				catch (Exception exception)
				{
					log(
						LogEventId.DEBUG_LEVEL_2,
						"Failed to retrieve some or all JMS Header properties "
							+ " from the response message."
							+ exception);
				}                
            }
            
            String messageTag = responseMessage.getStringProperty(MessageConstants.MESSAGE_TAG);
            
            if ( messageTag != null && (messageTag.equalsIgnoreCase("embusError") ||
            							messageTag.equalsIgnoreCase("atlasError"))) {
           	
                throw new AtlasException( responseMessage.getStringProperty("embusErrorCode"), responseMessage.getStringProperty("embusErrorDescription") );
            	
           } else  if (responseMessage instanceof TextMessage){
            	TextMessage aTextMessage = (TextMessage)responseMessage;
            	responseXMLString = aTextMessage.getText();
            }
        } catch( JMSException jmsException ) {
            throw new ServiceException(jmsException);
        } 
        
    	return responseXMLString;
	
	}

	private void addJMSPropertyToPropertiesInResponse(
		Properties io_propertiesInResponse,
		String propName,
		Object propValue)
	{
		try
		{
			if (propValue != null)
			{
				io_propertiesInResponse.put(propName, propValue);
			}
			else
			{
				log(
					LogEventId.INFO_LEVEL_2,
					"Value of JMS Header/Application property ["
						+ propName
						+ "] from the response "
						+ "message is ["
						+ propValue
						+ "].  Cannot add it to properties object.");
			}
		}
		catch (Exception exception)
		{
			log(
				LogEventId.DEBUG_LEVEL_2,
				"Failed to add JMS Header/Application property ["
					+ propName
					+ "] from the response "
					+ "message.  The value of the property was ["
					+ propValue
					+ "]:  "
					+ exception);
		}
	}
}
