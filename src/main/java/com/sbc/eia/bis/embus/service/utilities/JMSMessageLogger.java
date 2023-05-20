// $Id: JMSMessageLogger.java,v 1.2 2004/03/11 19:17:17 as5472 Exp $

package com.sbc.eia.bis.embus.service.utilities;

import java.util.Enumeration;

import javax.jms.Message;
import javax.jms.TextMessage;

import com.sbc.bccs.utilities.Logger;
import com.sbc.eia.bis.framework.logging.LogEventId;

/**
 * @author as5472
 *
 * The JMSMessageLogger can be used to record the standard JMS properties, application defined properties and message body 
 * (if content is text).
 */
public class JMSMessageLogger {


    public static String recordJMSMessageProperties( Logger logger, Message msg ) {
        
        StringBuffer sBuffer = new StringBuffer();
        
        try {
            sBuffer.append( "****JMS Message Properties****");
            sBuffer.append("\n");
            sBuffer.append("JMSCorrelationID=" + msg.getJMSCorrelationID());
            sBuffer.append("\n");
            sBuffer.append("JMSCorrelationIDAsBytes=" + msg.getJMSCorrelationIDAsBytes());
            sBuffer.append("\n");
            sBuffer.append("JMSDeliveryMode=" + msg.getJMSDeliveryMode());
            sBuffer.append("\n");
            sBuffer.append("JMSDestination=" + msg.getJMSDestination());
            sBuffer.append("\n");
            sBuffer.append("JMSExpiration=" + msg.getJMSExpiration());
            sBuffer.append("\n");
            sBuffer.append("JMSMessageID=" + msg.getJMSMessageID());
            sBuffer.append("\n");
            sBuffer.append("JMSPriority=" + msg.getJMSPriority());
            sBuffer.append("\n");
            sBuffer.append("JMSRedelivered=" + msg.getJMSRedelivered());
            sBuffer.append("\n");
            sBuffer.append("JMSReplyTo=" + msg.getJMSReplyTo());
            sBuffer.append("\n");
            sBuffer.append("JMSTimestamp=" + msg.getJMSTimestamp());
            sBuffer.append("\n");
            sBuffer.append("JMSType=" + msg.getJMSType());

            if ( logger != null ) {           
                logger.log(LogEventId.INFO_LEVEL_2, sBuffer.toString());
            }
                
            
        } catch ( Exception e ) {
            String s = e.getClass().getName() + " encountered in JMSMessageLogger::recordJMSMessageProperties";
            if ( logger != null ) {
                logger.log(LogEventId.INFO_LEVEL_2,s );
            } else {
                sBuffer.append("\n" + s);
            }
        }   
        
        return sBuffer.toString();      
    }

	/**
	 * Method recordApplicationMessageProperties.
	 * @param logger
	 * @param msg
	 */
	public static String recordApplicationMessageProperties( Logger logger, Message msg ) {
		StringBuffer sBuffer = new StringBuffer();
		try {
			/*
			TODO Renamed enum variable to enum1
			 */
			Enumeration enum1 = msg.getPropertyNames();
			boolean printHeader = true;
			while (enum1.hasMoreElements()) {
				if ( printHeader  ) {
					sBuffer.append( "****Application Message Properties****");
					printHeader = false;
				}
					
				String enumVal = (String)enum1.nextElement();
				sBuffer.append("\n" + enumVal + "=" + msg.getObjectProperty(enumVal) );
			}
	
			if ( logger != null && sBuffer.toString().trim().length() > 0) 
				logger.log(LogEventId.INFO_LEVEL_2, sBuffer.toString());
	
		} catch ( Exception e ) {
			String s = e.getClass().getName() + " encountered in JMSMessageLogger::recordMessageProperties";
			if ( logger != null )
				logger.log(LogEventId.INFO_LEVEL_2, s);
			else
				sBuffer.append( "\n" + s );
		}
		
		return sBuffer.toString();
	}
	    
	/**
	 * Method recordAllMessageProperties.
	 * @param logger
	 * @param msg
	 */
	public static String recordAllMessageProperties(  Logger logger , Message msg ) {
		
		StringBuffer sb = new StringBuffer(recordJMSMessageProperties( logger, msg ));
		sb.append(recordApplicationMessageProperties( logger , msg ));
		
		return sb.toString();
		
	}

	/**
	 * Method recordMessageBody.
	 * @param logger
	 * @param msg
	 */
	public static String recordMessageBody( Logger logger, Message msg ) {
		StringBuffer sBuffer = new StringBuffer();

		try {
			if ( msg instanceof TextMessage ) {
				sBuffer.append("****JMS Message Body****");
				sBuffer.append("\n");
				sBuffer.append(((TextMessage)msg).getText());
				sBuffer.append("\n");
			}				
			
			if ( logger != null ) 	
				logger.log(LogEventId.INFO_LEVEL_2,sBuffer.toString());
		} catch ( Exception e ) {
			String s = e.getClass().getName() + " encountered in JMSMessageLogger::recordMessageBody";
			if( logger != null ) 
				logger.log(LogEventId.INFO_LEVEL_2, s);
			else 
				sBuffer.append( "\n" + s);
		}
		
		return sBuffer.toString();
	}
    
	/**
	 * Method recordMessage.
	 * @param logger
	 * @param msg
	 */
	public static String recordMessage( Logger logger , Message msg ) {
		StringBuffer sb = new StringBuffer(recordAllMessageProperties(logger, msg));
		sb.append(recordMessageBody(logger, msg));
		return sb.toString();
	}		
    
}
