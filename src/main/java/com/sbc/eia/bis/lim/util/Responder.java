// $Id: Responder.java,v 1.2 2004/09/03 17:20:16 as5472 Exp $

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
 *  This file contains the Responder class, a message sender that can be
 *  used to send response messsages for an associated request.
 *  Description
 */

package com.sbc.eia.bis.lim.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.jms.DeliveryMode;
import javax.jms.IllegalStateException;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.TextMessage;
import javax.naming.CommunicationException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameNotFoundException;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

import com.sbc.bccs.utilities.Logger;
import com.sbc.bccs.utilities.PropertiesFileLoader;
import com.sbc.eia.bis.embus.service.utilities.EMBusMessagePolicy;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.embus.common.MessageConstants;

/**
 * @author as5472
 *
 * The Responder is a message sender.  Its main function is to produce
 * response messages and send them back to the client that made the
 * initial request.
 */
public class Responder {
    
    private final static String JMS_REPLY_CONFIG_FILE_LOCATION =
        "java:comp/env/RESPONDER_CONFIG_FILE_LOCATION";

    private static String USERNAME_KEY = "username";
    private static String PASSWORD_KEY = "password";
    private static String DELIVERYMODE_KEY = "deliveryMode";
    private static String TIMETOLIVEMILLIS_KEY = "timeToLiveMillis";
    private static String CONNECTIONFACTORYNAME_KEY = "connectionFactoryName";
    
    private static String URL_DELIMITER = " ";

    protected static Properties responderConfig = null;
    
    private QueueConnectionFactory queueConnectionFactory = null;

    // these properties can be static because they should remain 
    // unchanged once initialized
    private static String[] provider_urls = null;
    private static String initial_context_factory = null;
    private static String username = null;
    private static String password = null;
    private static int defaultDeliveryMode = DeliveryMode.NON_PERSISTENT;
    private static String connectionFactoryName = null;
    
    // this is the default time out in milliseconds for response messages
    // where the client's request does not define a message response
    // expiration property.
    private static long defaultTimeToLiveMilliseconds = 5000;
    
    private Logger logger = null;
    
    
    /**
     * Responder is designed to deliver asynchronous response messages to 
     * clients.  The response messages delivered adhere to the EMBus messaging
     * specification.  The Responder should not be used concurrently.
     * @throws NamingException
     * @throws FileNotFoundException
     * @throws IOException
     */
    public Responder()
        throws NamingException, FileNotFoundException, IOException
    {
        this(null);
    }
    /**
     * Responder is designed to deliver asynchronous response messages to clients.  The response messages delivered 
     * adhere to the EMBus messaging specification.  The Responder should not be used concurrently.
     * @param newBase
     * @throws NamingException
     * @throws FileNotFoundException
     * @throws IOException
     */
    public Responder( Logger newLogger) throws NamingException, FileNotFoundException, IOException {
        logger = newLogger;
        loadProperty();
    }

    /**
     * Method deliverResponse.  Initializes the JMS environment for sending a response.  Produces the JMS message
     * based on messaging specifications and delivers the response message.
     * @return Message the response message sent
     * @param originalMsg
     * @param xmlOutputToSend
     * @throws JMSException
     * @throws CommunicationException
     * @throws NamingException
     * @throws FileNotFoundException
     * @throws IOException
     */
    public Message deliverResponse( TextMessage originalMsg, String xmlOutputToSend) throws JMSException, CommunicationException, NamingException, FileNotFoundException, IOException {

        if ( responderConfig == null ) {
            loadProperty();
        }
        
        TextMessage outputMsg;
        long timeToLiveInMilliseconds = defaultTimeToLiveMilliseconds;
        if( originalMsg.propertyExists(MessageConstants.RESPONSE_MESSAGE_EXPIRATION) ) {
            try
            {
                timeToLiveInMilliseconds  = originalMsg.getLongProperty(MessageConstants.RESPONSE_MESSAGE_EXPIRATION);
            }
            catch( Exception exception )
            {
                log(
                    LogEventId.INFO_LEVEL_1,
                    "Failed to parse "
                        + MessageConstants.RESPONSE_MESSAGE_EXPIRATION
                        + " property from request message.  Responder will use default TTL of "
                        + timeToLiveInMilliseconds
                        + ".  Exception message is "
                        + exception.getMessage());
            }
                
        }
        
        QueueConnection queueConnection = null;
        QueueSession queueSession = null;
        QueueSender queueSender = null;

        try {
            
            if ( queueConnectionFactory == null ) {
                initQueueConnectionFactory();
                
                log(LogEventId.DEBUG_LEVEL_2, "Getting queue connection.");
                queueConnection = queueConnectionFactory.createQueueConnection( username, password );
                log(LogEventId.DEBUG_LEVEL_2, "Getting queue session.");
                queueSession = queueConnection.createQueueSession( false, defaultDeliveryMode );
                log(LogEventId.DEBUG_LEVEL_2, "Getting queue sender.");
                queueSender = queueSession.createSender(null);
            } else {
                log(LogEventId.DEBUG_LEVEL_2, "Getting queue connection.");
                queueConnection = queueConnectionFactory.createQueueConnection( username, password );
                log(LogEventId.DEBUG_LEVEL_2, "Getting queue session.");
                queueSession = queueConnection.createQueueSession( false, defaultDeliveryMode );
                log(LogEventId.DEBUG_LEVEL_2, "Getting queue sender.");
                queueSender = queueSession.createSender(null);
            }
            
            outputMsg = EMBusMessagePolicy.createReplyTextMessageBasedOnPolicies( logger , queueSession, originalMsg, xmlOutputToSend );
            
            sendResponseToQueue(queueSender, (Queue)originalMsg.getJMSReplyTo(), outputMsg, originalMsg.getJMSDeliveryMode(), timeToLiveInMilliseconds);

        } catch (IllegalStateException ise) {
            
            log( LogEventId.DEBUG_LEVEL_2, ise.getClass().getName() + " encountered.  Attempting to refresh connection.");
            
            initQueueConnectionFactory();
            
            log(LogEventId.DEBUG_LEVEL_2, "Getting queue connection.");
            queueConnection = queueConnectionFactory.createQueueConnection( username, password );
            log(LogEventId.DEBUG_LEVEL_2, "Getting queue session.");
            queueSession = queueConnection.createQueueSession(false, defaultDeliveryMode );
            log(LogEventId.DEBUG_LEVEL_2, "Getting queue sender.");
            queueSender = queueSession.createSender(null);
                
            outputMsg = EMBusMessagePolicy.createReplyTextMessageBasedOnPolicies( logger , queueSession, originalMsg, xmlOutputToSend );
            
            sendResponseToQueue(queueSender, (Queue)originalMsg.getJMSReplyTo(), outputMsg, originalMsg.getJMSDeliveryMode(), timeToLiveInMilliseconds);
            
        } finally {
            
            try {
                queueSender.close();
                log(LogEventId.DEBUG_LEVEL_2, "Queue sender closed.");
            } catch (Exception e){
                log(LogEventId.DEBUG_LEVEL_2, "Queue sender close failed with " + e.getMessage());
            }
            
            try {
                queueSession.close();
                log(LogEventId.DEBUG_LEVEL_2, "Queue session closed.");
            } catch (Exception e){
                log(LogEventId.DEBUG_LEVEL_2, "Queue session close failed with " + e.getMessage());
            }
            
            try {
                queueConnection.close();
                log(LogEventId.DEBUG_LEVEL_2, "Queue connection closed.");
            } catch (Exception e) {
                log(LogEventId.DEBUG_LEVEL_2, "Queue connection close failed with " + e.getMessage());
            }
        }
        
        return outputMsg;

    }

    /**
     * Method sendResponseToQueue.  Performs the actual send.
     * @param queue
     * @param outputMsg
     * @throws JMSException
     */
    public void sendResponseToQueue( QueueSender queueSender, Queue queue, TextMessage outputMsg, int deliveryMode, long timeToLiveInMilliseconds) throws JMSException {
        log( LogEventId.DEBUG_LEVEL_2, "Sending response.");
        queueSender.send( queue , outputMsg, deliveryMode, Message.DEFAULT_PRIORITY, timeToLiveInMilliseconds);
    }
    
    /**
     * Method initQueueConnection.  Initialize a QueueConnection and an unbound QueueSender.
     * @throws JMSException
     * @throws CommunicationException
     * @throws NamingException
     */
    public void initQueueConnectionFactory() throws JMSException, CommunicationException, NamingException {
        
        log(LogEventId.DEBUG_LEVEL_2, "Getting queue connection factory.");
        Context ctx = null;
        Properties p = new Properties();
        
        if ( initial_context_factory != null && initial_context_factory.trim().length() > 0) {
            p.put(Context.INITIAL_CONTEXT_FACTORY, initial_context_factory);
        }
    
        if ( provider_urls == null || provider_urls.length == 0 ) {
            // provider list is empty, use default
            ctx = new InitialContext(p);
        } else {
            // cycle through urls to find a valid provider url
            int tries = 0;
            for ( int i = 0 ; i < provider_urls.length ; i++ ) {
                p.put(Context.PROVIDER_URL, provider_urls[i]);
                try {
                    ctx = new InitialContext(p);
                    // found a valid context, break out;
                    break;
                } catch (CommunicationException ce) {
                    // unable to communiate with provider
                    if ( ++tries == provider_urls.length ) {
                        throw ce;
                    }
                } catch (NameNotFoundException nfe) {
                    // unable to find provider
                    if ( ++tries == provider_urls.length ) {
                        throw nfe;
                    }
                }
            }
        }
        
        Object cf = ctx.lookup( connectionFactoryName ); 
        queueConnectionFactory = (QueueConnectionFactory)PortableRemoteObject.narrow(cf, QueueConnectionFactory.class);
        
    }
    
    /**
     * Method loadProperty.  Retrueves the config file name and loads the file and its properties.
     * The properties to load in
     * @throws NamingException
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void loadProperty() throws   NamingException, FileNotFoundException, IOException {
                
        if ( responderConfig == null ) {
            Context cxt = new InitialContext();
            String loc = (String)cxt.lookup(JMS_REPLY_CONFIG_FILE_LOCATION);
            responderConfig = PropertiesFileLoader.read(loc, null);
    
            String urlString = (String)responderConfig.get(Context.PROVIDER_URL);
            // parse the url string for failover lookup urls
            
            if( urlString != null && urlString.trim().length() > 0 ) {
                provider_urls = parseUrls(urlString);
            }
            
            initial_context_factory = (String)responderConfig.get(Context.INITIAL_CONTEXT_FACTORY);
            username = (String)responderConfig.get(USERNAME_KEY);
            password = (String)responderConfig.get(PASSWORD_KEY);
            
            try {
                defaultDeliveryMode = Integer.parseInt((String)responderConfig.get(DELIVERYMODE_KEY));
            } catch (NumberFormatException nfe){
                //use default
            }
            
            try {
                defaultTimeToLiveMilliseconds = Long.parseLong((String)responderConfig.get(TIMETOLIVEMILLIS_KEY));
            } catch (NumberFormatException nfe) {
                //use default
            }

            connectionFactoryName = (String)responderConfig.get(CONNECTIONFACTORYNAME_KEY);
        }
        
    }

    /**
     * Method parseUrls parses a URL string separated by delimiter into an array of strings.
     * @param s
     * @return String[]
     */
    private String[] parseUrls( String s ) {
        StringTokenizer stk = new StringTokenizer(s, URL_DELIMITER);
        Vector v = new Vector();
        
        while (stk.hasMoreTokens()) {
            v.add(stk.nextToken());
        }
        
        String[] urls = new String[v.size()];
        v.toArray(urls);

        return urls;
    }
    
    /**
     * Method log logs messages to logger.  If logger is null, will log to System.out instead.
     * @param logId
     * @param s
     */
    private void log( String logId, String s ) {
        if ( logger == null ) {
            System.out.println(s);
        } else {
            logger.log( logId, s );
        }
    }

}
