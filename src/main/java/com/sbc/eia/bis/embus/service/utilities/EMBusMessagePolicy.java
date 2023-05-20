// $Id: EMBusMessagePolicy.java,v 1.3 2004/09/03 18:18:19 as5472 Exp $

package com.sbc.eia.bis.embus.service.utilities;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.QueueSession;
import javax.jms.TextMessage;

import com.sbc.bccs.utilities.Logger;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.embus.common.MessageConstants;

/**
 * @author as5472
 *
 * The EMBusMessagePolicy class helps create JMS Messages that conform to the EMBus XML Message
 * specification.
 */
public class EMBusMessagePolicy {
    
    private static String RESPONSE = "response";

	/**
	 * Method createReplyTextMessageBasedOnPolicies generates a new TextMessage using policies defined
	 * by the EMBus XML message specification.  The original request message is required to provided
	 * information on how to apply the policies.
	 * @param messenger com.sbc.embus.client.Messenger  The messenger used to create the message.
	 * @param originalMsg javax.jms.Message  The original request message.
	 * @return javax.jms.TextMessage  The new TextMessage
	 * @throws javax.jms.JMSException
	 * @throws javax.jms.EMBusException
	 */
	public static TextMessage createReplyTextMessageBasedOnPolicies( Logger base, QueueSession queueSession,	Message originalMsg, String xmlOutputMsg) throws JMSException {
		TextMessage newMsg = queueSession.createTextMessage();	

		String responseMesssageTag = null;
		
		if ( originalMsg.propertyExists(MessageConstants.MESSAGE_TAG) && originalMsg.getStringProperty(MessageConstants.MESSAGE_TAG) != null ) {
			responseMesssageTag = originalMsg.getStringProperty(MessageConstants.MESSAGE_TAG) + "Response";
			newMsg.setStringProperty(MessageConstants.MESSAGE_TAG, responseMesssageTag);
			newMsg.setStringProperty(MessageConstants.RESPONSE_TO, originalMsg.getStringProperty(MessageConstants.MESSAGE_TAG));
		} else {
			base.log(LogEventId.INFO_LEVEL_2, "Unable to set " + MessageConstants.MESSAGE_TAG + " and " + MessageConstants.RESPONSE_TO + " properties to response message since it is not set in the original message.");
		}

		if ( originalMsg.propertyExists(MessageConstants.RESPONSE_MESSAGE_EXPIRATION) ) {
			newMsg.setLongProperty(MessageConstants.RESPONSE_MESSAGE_EXPIRATION, originalMsg.getLongProperty(MessageConstants.RESPONSE_MESSAGE_EXPIRATION));
		} else {
			base.log(LogEventId.INFO_LEVEL_2, "Unable to set " + MessageConstants.RESPONSE_MESSAGE_EXPIRATION + " property to response message since it is not set in the original message.");
		}

		
		newMsg.setStringProperty(MessageConstants.IS_FINAL_MESSAGE, "true");

		if ( originalMsg.propertyExists(MessageConstants.APPLICATION_ID)  && originalMsg.getStringProperty(MessageConstants.APPLICATION_ID) != null ) {
			newMsg.setStringProperty(MessageConstants.APPLICATION_ID, originalMsg.getStringProperty(MessageConstants.APPLICATION_ID));
		} else {
			base.log(LogEventId.INFO_LEVEL_2, "Unable to set " + MessageConstants.APPLICATION_ID + " property to response message since it is not set in the original message.");
		}

        newMsg.setStringProperty(MessageConstants.MESSAGING_MODE, RESPONSE);

        if( originalMsg.propertyExists(MessageConstants.MESSAGE_ID_TO_CORRELATION_ID) )
        {
            Boolean msgIdToCoorId = new Boolean(originalMsg.getStringProperty(MessageConstants.MESSAGE_ID_TO_CORRELATION_ID));
            if (msgIdToCoorId.booleanValue())
            {
                if ( originalMsg.propertyExists(MessageConstants.ORIGINAL_MESSAGE_ID) )
                {
                    newMsg.setJMSCorrelationID(originalMsg.getStringProperty(MessageConstants.ORIGINAL_MESSAGE_ID));
                }
                else
                {
                    newMsg.setJMSCorrelationID(originalMsg.getJMSMessageID());
                }
            }
            else
            {
                newMsg.setJMSCorrelationID(originalMsg.getJMSCorrelationID());
            }
        }            
        else
        {
                newMsg.setJMSCorrelationID(originalMsg.getJMSCorrelationID());
        }
        
        if( originalMsg.getJMSRedelivered() )
        {
            newMsg.setStringProperty("embusIsRedelivered", "true");
        }
        
        if( originalMsg.propertyExists("embusResponseFilter"))
        {
            newMsg.setStringProperty("embusResponseFilter", originalMsg.getStringProperty("embusResponseFilter"));
        }
        
        if( originalMsg.propertyExists("embusConversationKey"))
        {
            newMsg.setStringProperty("embusConversationKey", originalMsg.getStringProperty("embusConversationKey"));
        }

        if( originalMsg.propertyExists(MessageConstants.LOGGING_KEY))
        {
            newMsg.setStringProperty(MessageConstants.LOGGING_KEY, originalMsg.getStringProperty(MessageConstants.LOGGING_KEY));
        }

		newMsg.setText(xmlOutputMsg);
            
						
		return newMsg;
	}	
	
}
