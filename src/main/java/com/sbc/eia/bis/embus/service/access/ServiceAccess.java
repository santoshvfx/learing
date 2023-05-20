// $Id: ServiceAccess.java,v 1.22 2005/06/20 20:24:23 as5472 Exp $

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
 *  This file contains the ServiceAccess base class and any internal helper
 *  classes it might define.
 *  Description
 */
package com.sbc.eia.bis.embus.service.access;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;

import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

import com.att.aioneops.common.util.DateTimeUtils;
import com.att.lms.bis.common.config.StaticContextAccessor;
import com.att.lms.bis.kafka.service.KafkaConstants;
import com.att.lms.bis.rest.facade.IRcRestFacade;
import com.att.lms.bis.rest.facade.RcRestFacade;
import com.att.lms.oauth.SharedDataDictionary;
import com.att.lms.oauth.config.settings.input.OauthIngressTopicSettings;
import com.att.lms.oauth.config.settings.output.OauthEgressEventhubTopicSettings;
import com.att.lms.oauth.kafka.producer.OauthIngressEventhubProducer;
import com.att.lms.rc.dto.RcTextMessage;
import com.att.lms.rc.enumeration.RcResponseStatusCodesEnum;
import com.att.lms.rc.enumeration.RcTypesEnum;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sbc.bccs.utilities.Logger;
import com.sbc.bccs.utilities.PropertiesFileLoader;
import com.sbc.eia.bis.embus.service.utilities.JMSMessageLogger;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.embus.common.EMBusException;
import com.sbc.embus.common.MapExtractor;
import com.sbc.embus.common.MessageConstants;
import com.sbc.embus.jxclient.Messenger;
import com.sbc.embus.util.xml.MapGenerator;
import lombok.extern.slf4j.Slf4j;

import static java.lang.Thread.sleep;

/** Description
 *  The ServiceAccess class is the base class that a user can
 *  extend to provide messaging access to a specific host.  This class
 *  uses the EMBus Client Framework for delivering JMS messages to the host,
 *  and provides convenient methods for performing a send and receive.
 *
 *  In addition to being a messaging layer the ServiceAccess base also allows
 *  the user to specify whether it should also perform automatic encoding
 *  and decoding of java objects to and from XML (respectively).  The user
 *  does this by setting an encoder and decoder through the subclasses
 *  assigner methods.
 *  Description
 */

@Slf4j
public abstract class ServiceAccess
{
	// New instance variables for JMSOps

	private static final String JMSOPS_MESSAGING_FUNCTIONS_PREFIX_KEY = "jmsops.messagingFunctions.";
	private static final String DELIVER = "deliver";
	private static final String DELIVERY_MODE = "deliveryMode";
	private static final String PRIORITY = "priority";
	private static final String MESSAGE_EXPIRATION_MILLIS = "MessageExpirationMillis";
	private static final String CORRELATION_ID = "correlationID";
	private static final String MESSAGING_MODE = "messagingMode";
	private static final String MESSAGE_TAG = "messageTag";
	private static final String MESSAGE_ID_TO_CORRELATION_ID = "messageIDToCorrelationID";
	private static final String RESPONSE_MESSAGE_EXPIRATION = "ResponseMessageExpiration";
	private static final String TIME_TO_WAIT_MILLIS = "pickup.timeToWaitMillis";
	private static final String RETRY_THRESHOLD = "retryThreshold";
	private static final String PERSISTENT = "persistent";
	private static final String NONPERSISTENT = "nonpersistent";
	private static final String SHOULD_RETRY_ON_ERROR = "shouldRetryOnError";
	private static final String KEY_SEPARATOR = ".";

	// literal values used as part of configuration keys
	private static final String CORRELATION_ID_PROVIDER_LITERAL = "correlationIDProvider";

	// literal values used for comparing against message properties
	private static final String EMBUS_ERROR_LITERAL = "embusError";
	private static final String RC_ERROR_LITERAL = "RCError";

	/* the environment name used to lookup the QCF and queues */
	private String m_environmentName = null;

	/* the name of the ovals usps client configuration file */
	private String m_embusConfigFileName = null;

	/* the EMBUS client configuration information */
	private HashMap m_propertiesMap = null;

	/* the messenger that encaspulates much of what's involved
	 * in message delivery and receiving messages
	 */
	private Messenger m_messenger = null;

	/* the optional encoder decoder objects */
	private IEncoder m_encoder = null;
	private IDecoder m_decoder = null;

	private Logger m_logger = null;

	private CorrelationIDProviderI defaultCorrelationIDProvider = null;

	private Receipt m_receipt = null;

    private IRcRestFacade rcRestFacade;

	private ObjectMapper objectMapper;

	/***********************************************************************
	 *  Constructors
	 ***********************************************************************/

	/**
	 * Constructor for ServiceAccess.
	 * @param environmentName String A unique name to identify the target
	 * environment. The name is essentially used to associate a connection (or
	 * set of connections) and its pool of sessions to the host.  You can
	 * therefore use multiple names to identify the same target environment,
	 * thereby creating separate pools of connections and sessions to the same
	 * host.
	 * @param configFileName String The name of the configuration file (or file
	 * location) to use for configuring the Service Access.
	 * @param logger Logger used for logging.
	 * @throws ServiceException A ServiceException is thrown to indicate a
	 * problem in configuring the ServiceWrapper.
	 */
	public ServiceAccess(String environmentName, String configFileName, Logger logger) throws ServiceException
	{
		this(environmentName, configFileName, new Hashtable(), logger);
	}

	/**
	 * Constructor for ServiceAccess.
	 * @param environmentName String A unique name to identify the target
	 * environment. The name is essentially used to associate a connection (or
	 * set of connections) and its pool of sessions to the host.  You can
	 * therefore use multiple names to identify the same target environment,
	 * thereby creating separate pools of connections and sessions to the same
	 * host.
	 * @param configFileName String The name of the configuration file (or file
	 * location) to use for configuring the Service Access.
	 * @param notificationLevel String An optional notification level passed
	 * to the underlying EMBus Client API to enable logging of EMBus log
	 * events.  For a list of available log levels consult the
	 * @see com.sbc.embus.common.NotificationListener and
	 * @see com.sbc.embus.common.NotificationLevel classes for details.
	 * @param logger Logger used for logging.
	 * @throws ServiceException A ServiceException is thrown to indicate a
	 * problem in configuring the ServiceWrapper.
	 * @deprecated This method is deprecated. The parameter notificationLevel is no
	 * longer being used. Use ServiceAccess(String environmentName, String configFileName, Logger logger)
	 * instead.
	 */
	public ServiceAccess(String environmentName, String configFileName, String notificationLevel, Logger logger)
			throws ServiceException
	{
		this(environmentName, configFileName, logger);
	}

	/**
	 * Constructor for ServiceAccess.
	 * @param environmentName String A unique name to identify the target
	 * environment. The name is essentially used to associate a connection (or
	 * set of connections) and its pool of sessions to the host.  You can
	 * therefore use multiple names to identify the same target environment,
	 * thereby creating separate pools of connections and sessions to the same
	 * host.
	 * @param configFileName String The name of the configuration file (or file
	 * location) to use for configuring the Service Access.
	 * @param Hashtable additionalCustomProperties used to pass additional
	 * properties to allow for more flexibility in adding new features and
	 * options without having to create additional overloaded constructors.
	 * @param logger Logger used for logging.
	 * @throws ServiceException A ServiceException is thrown to indicate a
	 * problem in configuring the ServiceWrapper.
	 */
	public ServiceAccess(
			String environmentName,
			String configFileName,
			Hashtable additionalCustomProperties,
			Logger logger)
			throws ServiceException
	{
		super();
		setLogger(logger);

		try
		{
			setConfigFileName(configFileName);

			if (m_propertiesMap == null)
			{
				m_propertiesMap =
						new HashMap(MapGenerator.generateMap(PropertiesFileLoader.getAsURL(getConfigFileName(), logger)));
				log(LogEventId.DEBUG_LEVEL_2, "Loaded " + m_embusConfigFileName + ".");
			}

			if (getEnvironmentName() == null)
			{
				setEnvironmentName(environmentName);
			}

			rcRestFacade = StaticContextAccessor.getBean(IRcRestFacade.class);
			objectMapper = StaticContextAccessor.getBean(ObjectMapper.class);
		}
		catch (Exception exception)
		{
			throw new ServiceException(exception);
		}
	}

	/**
	 * Constructor for ServiceAccess.
	 * @param environmentName String A unique name to identify the target
	 * environment. The name is essentially used to associate a connection (or
	 * set of connections) and its pool of sessions to the host.  You can
	 * therefore use multiple names to identify the same target environment,
	 * thereby creating separate pools of connections and sessions to the same
	 * host.
	 * @param configFileName String The name of the configuration file (or file
	 * location) to use for configuring the Service Access.
	 * @param notificationLevel String An optional notification level passed
	 * to the underlying EMBus Client API to enable logging of EMBus log
	 * events.  For a list of available log levels consult the
	 * @param Hashtable additionalCustomProperties used to pass additional
	 * properties to allow for more flexibility in adding new features and
	 * options without having to create additional overloaded constructors.
	 * @param logger Logger used for logging.
	 * @throws ServiceException A ServiceException is thrown to indicate a
	 * problem in configuring the ServiceWrapper.
	 * @deprecated This method is deprecated. The parameter notificationLevel is
	 * no longer being used. Please use
	 * ServiceAccess(environmentName, configFileName, additionalCustomProperties, logger).
	 */
	public ServiceAccess(
			String environmentName,
			String configFileName,
			String notificationLevel,
			Hashtable additionalCustomProperties,
			Logger logger)
			throws ServiceException
	{
		this(environmentName, configFileName, additionalCustomProperties, logger);
	}

	/** Description
	 *  NoDeliveryExpectedException is an exception that is thrown by a Command
	 *  object that wants to indicate that a delivery is not "possible" because
	 *  the messaging operation being performed by the command does not return
	 *  a delivery (response).  This exception is used in this manner so that
	 *  the command types can stay relatively turse in terms of operations but
	 *  provide a way to skip funcationality (e.g. dealing with responses)
	 *  that may not apply to the command being executed.  For example, a send
	 *  command shares the same setup leading up to a send as that of a send
	 *  and receive command.  The only different is a send command does not
	 *  need to perform a receive and therefore do not have to deal with a
	 *  response.
	 *  Description
	 */
	private class NoDeliveryExpectedException extends Exception
	{
		/**
		 * @see java.lang.Object#Object()
		 */
		private NoDeliveryExpectedException()
		{
			super();
		}
	}

	/** Description
	 *  An abstract class representing a messaging command.  It is meant to
	 *  provide a single interface for executing all the different types of
	 *  messaging operations supported by the EMBus Client framework, such as
	 *  synchronous send and receive calls, asynchronous notifications,
	 *  asynchronous send and receive calls, etc.
	 *
	 *  This type should be extended and its execute method implemented to
	 *  provide other types of messaging operations.
	 *  Description
	 */
	private abstract class Command
	{
		protected Logger commandLogger = null;

		/**
		 * Method execute defines the interfaces for executing a messaging
		 * command.  Although the interface requires that the implemented
		 * method return a Delivery object, the Delivery object may simply
		 * be a null reference.  This is the case for send only commands where
		 * no Delivery (response) is expected.  The implementor should
		 * throw a NoDeliveryExpectedException to indicate that no delivery is
		 * to be expected and therefore handled.  Throwing the
		 * NoDeliveryExpected is preffered over returning a null because it may
		 * be possible for the API to return a "null" reference for Delivery.
		 *
		 * @param messenger Messenger The messenger that will perform the
		 * messaging.
		 * @param message Message The request message to send.
		 * @param i_functionKey String The function to perform.  The function
		 * is defined in the XML configuration file as an element of the same
		 * name (e.g. passing validateStateCode as the functionKey will pass
		 * the configuration defined by the "validateStateCode" element).
		 * Depending on the type of messaging, the function element will
		 * have a "delivery" and a "pickup" child element.
		 * @return Delivery a Delivery object, this object may be null when
		 * send only messaging is performed.  To properly indicate that
		 * No Delivery is expected, throw a NoDeliveryExpectedException instead.
		 * @throws JMSException
		 * @throws EMBusException
		 * @throws IOException
		 * @throws NoDeliveryExpectedException An exception to indicate to the
		 * command framework that no delivery is to be expected and therefore
		 * should not expect to handle a delivery, such is the caes of a
		 * send only messaging call.
		 */

		protected abstract Message execute(
				Messenger messenger,
				Message message,
				String i_functionKey,
				Properties configOverrides)
				throws JMSException, EMBusException, IOException, NoDeliveryExpectedException;

		/**
		 * Method addCorrelationId.  Checks to see if a correlation ID is
		 * necessary to complete the messaging transaction.  If so and a
		 * correlation ID is not present, the method checks to see if a
		 * correlation ID provider is configured to generate/provide one.
		 *
		 * If no providers are found, the configuration is left untouched.
		 * @param i_functionKey String indicating the messaging function
		 * configuration that would like to add a correlation id if necessary.
		 * @param config HashMap containing deliver configuration information
		 * used to deliver messages, which can include a function level
		 * correlation ID provider
		 */
		protected void addCorrelationId(String i_functionKey, HashMap io_config)
		{

			Object msgIdToCIdOptionValue = io_config.get(DELIVER + KEY_SEPARATOR + MESSAGE_ID_TO_CORRELATION_ID);

			boolean useMessageIdToCorrelationId = true;

			// if true, then true
			// any other value including false, or null, returns false
			if (msgIdToCIdOptionValue != null)
				useMessageIdToCorrelationId = ((Boolean) msgIdToCIdOptionValue).booleanValue();

			// when messageIdToCorrelationID is true, the correlationID is
			// set in the config passed but is not used for correlation,
			// so check messageIdToCorrelatioID option first
			if (!useMessageIdToCorrelationId)
			{
				Object correlationIdValue = io_config.get(DELIVER + KEY_SEPARATOR + CORRELATION_ID);

				// when messageIdToCorrelationID is false,
				// a correlationID must be present
				// if one is not found, then check to see if a correlation ID
				// provider is configured to provide one.
				if (correlationIdValue == null)
				{
					log(LogEventId.DEBUG_LEVEL_2, "Adding correlationID to configuration: " + correlationIdValue);
					// correlationID is required, but not there
					// try to generate one
					String aCorrelationID = getCorrelationID(io_config);

					// and add it to the config
					io_config.put(DELIVER + KEY_SEPARATOR + CORRELATION_ID, aCorrelationID);
				}
			}
		}

		/**
		 * Method getCorrelationID.  Tries to retrieve a correlation ID.
		 * @param config HashMap containing the deliver configuration
		 * information, which can include a CorrelationID provider instance
		 * @return String
		 */
		public String getCorrelationID(HashMap i_config)
		{
			String correlationID = null;
			CorrelationIDProviderI provider = getCorrelationIDProvider(i_config);
			if (provider != null)
			{
				correlationID = provider.getCorrelationId();
			}
			return correlationID;
		}

		/**
		 * Method getCorrelationIDProvider Attempts to retrieve a correlation ID
		 * provider in the following order
		 *  1. From the deliver configuration HashMap passed by the user
		 *  2. Checks if there's a default provider configured
		 * If a correlation ID provider is not found, a null reference
		 * is returned.
		 * @param config HashMap containing deliver configuration
		 * information, which can include a CorrelationID provider instance
		 * @return CorrelationIDProviderI
		 */
		public CorrelationIDProviderI getCorrelationIDProvider(HashMap i_config)
		{
			Object o = i_config.get(CORRELATION_ID_PROVIDER_LITERAL);
			CorrelationIDProviderI correlationIDProviderI = null;
			if (o != null && (o instanceof CorrelationIDProviderI))
			{
				log(LogEventId.DEBUG_LEVEL_2, "Found a function specific provider.");
				correlationIDProviderI = (CorrelationIDProviderI) o;
			}
			else if (getDefaultCorrelationIDProvider() != null)
			{
				log(LogEventId.DEBUG_LEVEL_2, "Found a default provider. Using default correlation id " + "provider.");
				correlationIDProviderI = getDefaultCorrelationIDProvider();
			}
			else
			{
				log(LogEventId.DEBUG_LEVEL_2, "No function specific or default CorrelationID Provider " + "found.");
			}

			return correlationIDProviderI;
		}
	}

	/** Description
	 *  A messaging command that performs an asynchronous send.  The class
	 *  itself makes a call to the EMBus Client Frameworks "messenger::deliver"
	 *  method which performs a asynchronous send.
	 *  Description
	 */
	private class SendCommand extends Command

	{

		/**
		 * Method SendCommand.
		 * @param logger
		 */
		private SendCommand(Logger logger)
		{
			commandLogger = logger;
		}

		/**
		 * @see com.sbc.eia.bis.embus.service.access.ServiceAccess.Command#execute(Messenger, Message, String)
		 */
		protected Message execute(
				Messenger messenger,
				Message requestMessage,
				String i_functionKey,
				Properties deliverConfigOverrides)
				throws JMSException, EMBusException, NoDeliveryExpectedException
		{
			HashMap config = getDeliverProperties(getSubsetMap(JMSOPS_MESSAGING_FUNCTIONS_PREFIX_KEY + i_functionKey));

			// Add configOverrides
			if (deliverConfigOverrides != null && !deliverConfigOverrides.isEmpty())
			{
				config.putAll(getDeliverProperties(new HashMap(deliverConfigOverrides)));
			}

			log(LogEventId.DEBUG_LEVEL_2, "deliverConfigOverrides:" + deliverConfigOverrides);
			log(LogEventId.DEBUG_LEVEL_2, "Config:" + config);
			log(LogEventId.DEBUG_LEVEL_2, "Deliver config retrieved.");

			try
			{
				messenger.deliver(config, requestMessage);
			}
			catch(java.lang.RuntimeException re)
			{
				throw new JMSException(re.getMessage());
			}

			commandLogger.log(LogEventId.DEBUG_LEVEL_2, "Call to Messenger::deliver completed.");

			if (true)
			{
				throw new NoDeliveryExpectedException();
			}
			return null;
		}
	}

	/** Description
	 *  A messaging command that performs an asynchronous send.  The class
	 *  itself makes a call to the EMBus Client Frameworks "messenger::deliverRequest"
	 *  method which performs a asynchronous send.
	 *  Description
	 */
	private class SendAndGetReceiptCommand extends Command
	{
		private SendAndGetReceiptCommand(Logger logger)
		{
			commandLogger = logger;
		}

		protected Message execute(
				Messenger messenger,
				Message requestMessage,
				String i_functionKey,
				Properties deliverConfigOverrides)
				throws JMSException, EMBusException, NoDeliveryExpectedException
		{
			HashMap config = getDeliverProperties(getSubsetMap(JMSOPS_MESSAGING_FUNCTIONS_PREFIX_KEY + i_functionKey));
			Receipt receipt = null;

			// Add configOverrides
			if (deliverConfigOverrides != null && !deliverConfigOverrides.isEmpty())
			{
				config.putAll(getDeliverProperties(new HashMap(deliverConfigOverrides)));
			}

			addCorrelationId(i_functionKey, config);

			log(LogEventId.DEBUG_LEVEL_2, "deliverConfigOverrides:" + deliverConfigOverrides);
			log(LogEventId.DEBUG_LEVEL_2, "Config:" + config);
			log(LogEventId.DEBUG_LEVEL_2, "Deliver config retrieved.");

			try
			{
				setReceipt(new Receipt(messenger.deliverRequest(config, requestMessage)));

			}
			catch(java.lang.RuntimeException re)
			{
				throw new JMSException(re.getMessage());
			}

			commandLogger.log(LogEventId.DEBUG_LEVEL_2, "Call to Messenger::deliverRequest completed.");

			if (true)
			{
				throw new NoDeliveryExpectedException();
			}
			return null;

		}
	}

	/** Description
	 *  A messaging command that performs an asynchronous send.  The class
	 *  itself makes a call to the EMBus Client Frameworks "messenger::deliver"
	 *  method which performs a asynchronous send.
	 *  Description
	 */
	private class SendAndReceiveCommand extends Command
	{
		/**
		 * Method SendAndReceiveCommand.
		 * @param logger
		 */
		private SendAndReceiveCommand(Logger logger)
		{
			commandLogger = logger;
		}

		/**
		 * @see com.sbc.eia.bis.embus.service.access.ServiceAccess.Command
		 * #execute(Messenger, Message, String)
		 */
		protected Message execute(
				Messenger messenger,
				Message requestMessage,
				String i_functionKey,
				Properties deliverConfigOverrides)
				throws JMSException, EMBusException, IOException
		{
			Message message = null;
			HashMap config = getDeliverProperties(getSubsetMap(JMSOPS_MESSAGING_FUNCTIONS_PREFIX_KEY + i_functionKey));

			// Add configOverrides
			if (deliverConfigOverrides != null && !deliverConfigOverrides.isEmpty())
			{
				config.putAll(getDeliverProperties(new HashMap(deliverConfigOverrides)));
			}

			addCorrelationId(i_functionKey, config);

			log(LogEventId.DEBUG_LEVEL_2, "deliverConfigOverrides:" + deliverConfigOverrides);
			log(LogEventId.DEBUG_LEVEL_2, "Config:" + config);
			log(LogEventId.DEBUG_LEVEL_2, "Request config retrieved.");

			try
			{
				message = messenger.request(config, requestMessage);
			}
			catch(java.lang.RuntimeException re)
			{
				throw new JMSException(re.getMessage());
			}

			commandLogger.log(LogEventId.DEBUG_LEVEL_2, "Call to Messenger::request/response completed.");

			return message;
		}


	}

	/**
	 * @see ServiceAccess#sendAndReceive(String, String, Properties, Properties, Properties)
	 */
	public String sendAndReceive(String i_functionKey, String i_inputMsg) throws ServiceException
	{
		return sendAndReceive(i_functionKey, i_inputMsg, null);
	}

	/**
	 * @link #sendAndReceive(String, String, Properties, Properties, Properties)
	 */
	public String sendAndReceive(String i_functionKey, String i_inputMsg, Properties i_jmsPropertyTagAndValuesToSet)
			throws ServiceException
	{
		return sendAndReceive(i_functionKey, i_inputMsg, i_jmsPropertyTagAndValuesToSet, null);
	}

	/**
	 * @link #sendAndReceive(String, String, Properties, Properties, Properties)
	 */
	public String sendAndReceive(
			String i_functionKey,
			String i_inputMsg,
			Properties i_jmsPropertyTagAndValuesToSet,
			Properties o_jmsPropertiesInResponse)
			throws ServiceException
	{
		return sendAndReceive(
				i_functionKey,
				i_inputMsg,
				i_jmsPropertyTagAndValuesToSet,
				o_jmsPropertiesInResponse,
				null);
	}

	/**
	 * Method sendAndReceive.  Performs a send and then a receive.  The input
	 * String message is sent as is, with neither encoding nor decoding
	 * performed.  The response message is expected to be a TextMessage.
	 * @param i_functionKey String The function to perform.  The function
	 * is defined in the XML configuration file as an element of the same
	 * name (e.g. passing validateStateCode as the functionKey will pass
	 * the configuration defined by the "validateStateCode" element).
	 * Depending on the type of messaging, the function element will
	 * have a "delivery" and a "pickup" child element.
	 * @param i_inputMsg String The input message as a String to send.
	 * @param i_jmsPropertyTagAndValuesToSet Properties a Properties object
	 * containing the set of JMS Message properties to set to the message
	 * to deliver.  It can be null.  In which case it won't be used.
	 * @param o_jmsPropertiesInResponse Properties a Properties object
	 * to hold the properties retrieved from the response message.
	 * It can be null.  In which case it won't be used.
	 * @param i_deliverConfigOverrides Properties a Properties object
	 * containing a sets of key value pairs used to override the
	 * configuration settings are runtime.  It can be null.
	 * In which case it won't be used.
	 * @return String the text body of the response message as a String
	 * @throws ServiceException
	 */
	public String sendAndReceive(
			String i_functionKey,
			String i_inputMsg,
			Properties i_jmsPropertyTagAndValuesToSet,
			Properties o_jmsPropertiesInResponse,
			Properties i_deliverConfigOverrides)
			throws ServiceException
	{
		return sendAndReceiveCommandInvoker(
				i_functionKey,
				i_inputMsg,
				i_jmsPropertyTagAndValuesToSet,
				o_jmsPropertiesInResponse,
				i_deliverConfigOverrides,
				new SendAndReceiveCommand(m_logger));
	}

	/**
	 * @see ServiceAccess#sendAndRecieve(String, Object, Properties, Properties, Properties)
	 */
	public Object sendAndReceive(String i_functionKey, Object i_object) throws ServiceException
	{
		return sendAndReceive(i_functionKey, i_object, null, null, null);
	}

	/**
	 * Method sendAndReceive will do the following:
	 *   1. validate that an encoder and decoder are set
	 *   2. encode the Object to a String
	 *   3. deliver the XML in a message
	 *   4. get the response message as a String
	 *   5. decode the Object and returns it
	 *
	 * The Object must be encodable by the "Encoder" object set to the
	 * ServiceAccess.
	 *
	 * @see ServiceAccess#sendAndRecieve(String, String, Properties, Properties, Properties)
	 */
	public Object sendAndReceive(
			String i_functionKey,
			Object i_object,
			Properties i_jmsPropertyTagAndValuesToSet,
			Properties o_jmsPropertiesInResponse,
			Properties i_deliverConfigOverrides)
			throws ServiceException
	{
		return sendAndReceive(
				i_functionKey,
				new Object[] { i_object },
				i_jmsPropertyTagAndValuesToSet,
				o_jmsPropertiesInResponse,
				i_deliverConfigOverrides)[0];
	}

	/**
	 * @see ServiceAccess#sendAndRecieve(String, Object[], Properties, Properties, Properties)
	 */
	public Object[] sendAndReceive(String i_functionKey, Object[] i_objectArray) throws ServiceException
	{
		return sendAndReceive(i_functionKey, i_objectArray, null);
	}

	/**
	 * @link #sendAndReceive(String, Object[], Properties, Properties, Properties)
	 */
	public Object[] sendAndReceive(String i_functionKey, Object[] i_objectArray, Properties i_jmsPropertiesToSet)
			throws ServiceException
	{
		return sendAndReceive(i_functionKey, i_objectArray, i_jmsPropertiesToSet, null);
	}

	/**
	 * @link #sendAndReceive(String, Object[], Properties, Properties, Properties)
	 */
	public Object[] sendAndReceive(
			String i_functionKey,
			Object[] i_objectArray,
			Properties i_jmsPropertyTagAndValuesToSet,
			Properties o_jmsPropertiesInResponse)
			throws ServiceException
	{
		return sendAndReceive(
				i_functionKey,
				i_objectArray,
				i_jmsPropertyTagAndValuesToSet,
				o_jmsPropertiesInResponse,
				null);
	}

	/**
	 * Method sendAndReceive will do the following:
	 *   1. validate that an encoder and decoder are set
	 *   2. encode the object array to a String
	 *   3. deliver the XML in a message
	 *   4. get the response message as a String
	 *   5. decode the Object(s) to an array and returns it
	 *
	 * The array of Objects must be encodable by the "Encoder" object set to the
	 * ServiceAccess.
	 *
	 * @see ServiceAccess#sendAndReceive(String, String, Properties, Properties, Properties)
	 */
	public Object[] sendAndReceive(
			String i_functionKey,
			Object[] i_objectArray,
			Properties i_jmsPropertyTagAndValuesToSet,
			Properties o_jmsPropertiesInResponse,
			Properties i_deliveryConfigOverrides)
			throws ServiceException
	{
		validateEncoderAndDecoderAreSet();
		String inputMessage = getEncoder().encode(i_objectArray);

		String returnMessage =
				sendAndReceive(
						i_functionKey,
						inputMessage,
						i_jmsPropertyTagAndValuesToSet,
						o_jmsPropertiesInResponse,
						i_deliveryConfigOverrides);

		return getDecoder().decode(returnMessage);
	}

	/**
	 * Method sendAndReceive will do the following:
	 *   1. validate that an encoder and decoder are set
	 *   2. encode the object array to a String
	 *   3. deliver the XML in a message
	 *   4. get the response message as a String
	 *   5. decode the Object(s) to an array and returns it
	 *
	 * The array of Objects must be encodable by the "Encoder" object set to the
	 * ServiceAccess.
	 *
	 * @see ServiceAccess#sendAndReceive(String, String, Properties, Properties, Properties)
	 */
	public String sendAndReceive(
			Object[] i_objectArray,
			String i_functionKey,
			Properties i_jmsPropertyTagAndValuesToSet,
			Properties o_jmsPropertiesInResponse,
			Properties i_deliveryConfigOverrides)
			throws ServiceException
	{
		validateEncoderAndDecoderAreSet();
		String inputMessage = getEncoder().encode(i_objectArray);

		String returnMessage =
				sendAndReceive(
						i_functionKey,
						inputMessage,
						i_jmsPropertyTagAndValuesToSet,
						o_jmsPropertiesInResponse,
						i_deliveryConfigOverrides);

		return returnMessage;
	}

	/**
	 * @link #sendAndAcknowledge(String, Object, Properties, Properties, Properties)
	 */
	public String sendAndAcknowledge(
			String i_functionKey,
			Object i_objectToSend,
			Properties i_jmsPropertyTagAndValuesToSet,
			Properties o_jmsPropertiesInResponse)
			throws ServiceException
	{
		return sendAndAcknowledge(
				i_functionKey,
				new Object[] { i_objectToSend },
				i_jmsPropertyTagAndValuesToSet,
				o_jmsPropertiesInResponse);
	}

	/**
	 * Method sendAndAcknowledge.
	 * @param i_functionKey
	 * @param i_objectToSend
	 * @param i_jmsPropertyTagAndValuesToSet
	 * @param o_jmsPropertiesInResponse
	 * @param i_deliverConfigOverrides
	 * @return String
	 * @throws ServiceException
	 */
	public String sendAndAcknowledge(
			String i_functionKey,
			Object i_objectToSend,
			Properties i_jmsPropertyTagAndValuesToSet,
			Properties o_jmsPropertiesInResponse,
			Properties i_deliverConfigOverrides)
			throws ServiceException
	{
		return sendAndAcknowledge(
				i_functionKey,
				new Object[] { i_objectToSend },
				i_jmsPropertyTagAndValuesToSet,
				o_jmsPropertiesInResponse,
				i_deliverConfigOverrides);
	}

	/**
	 * @link #sendAndAcknowledge(String, Object[], Properties, Properties, Properties)
	 */
	public String sendAndAcknowledge(
			String i_functionKey,
			Object[] i_objectsToSend,
			Properties i_jmsPropertyTagAndValuesToSet,
			Properties o_jmsPropertiesInResponse)
			throws ServiceException
	{
		validateEncoderIsSet();
		String inputMessage = getEncoder().encode(i_objectsToSend);

		return sendAndAcknowledge(
				i_functionKey,
				inputMessage,
				i_jmsPropertyTagAndValuesToSet,
				o_jmsPropertiesInResponse);
	}

	/**
	 * Method sendAndAcknowledge.
	 * @param i_functionKey
	 * @param i_objectsToSend
	 * @param i_jmsPropertyTagAndValuesToSet
	 * @param o_jmsPropertiesInResponse
	 * @return String
	 * @throws ServiceException
	 */
	public String sendAndAcknowledge(
			String i_functionKey,
			Object[] i_objectsToSend,
			Properties i_jmsPropertyTagAndValuesToSet,
			Properties o_jmsPropertiesInResponse,
			Properties i_deliverConfigOverrides)
			throws ServiceException
	{
		validateEncoderIsSet();
		String inputMessage = getEncoder().encode(i_objectsToSend);

		return sendAndAcknowledge(
				i_functionKey,
				inputMessage,
				i_jmsPropertyTagAndValuesToSet,
				o_jmsPropertiesInResponse,
				i_deliverConfigOverrides);
	}

	/**
	 * @link #sendAndAcknowledge(String, String, Properties, Properties, Properties)
	 */
	public String sendAndAcknowledge(
			String i_functionKey,
			String i_inputMsg,
			Properties i_jmsPropertyTagAndValuesToSet,
			Properties o_jmsPropertiesInResponse)
			throws ServiceException
	{
		return sendAndReceiveCommandInvoker(
				i_functionKey,
				i_inputMsg,
				i_jmsPropertyTagAndValuesToSet,
				o_jmsPropertiesInResponse,
				null,
				new SendAndReceiveCommand(m_logger));
	}

	/**
	 * Method sendAndAcknowledge.
	 * @param i_functionKey
	 * @param i_inputMsg
	 * @param i_jmsPropertyTagAndValuesToSet
	 * @param o_jmsPropertiesInResponse
	 * @param i_deliverConfigOverrides
	 * @return String
	 * @throws ServiceException
	 */
	public String sendAndAcknowledge(
			String i_functionKey,
			String i_inputMsg,
			Properties i_jmsPropertyTagAndValuesToSet,
			Properties o_jmsPropertiesInResponse,
			Properties i_deliverConfigOverrides)
			throws ServiceException
	{
		return sendAndReceiveCommandInvoker(
				i_functionKey,
				i_inputMsg,
				i_jmsPropertyTagAndValuesToSet,
				o_jmsPropertiesInResponse,
				i_deliverConfigOverrides,
				new SendAndReceiveCommand(m_logger));
	}

	/**
	 * Method sendAndGetReceipt.
	 */

	public Receipt sendAndGetReceipt(
			String i_functionKey,
			String i_inputMsg)
			throws ServiceException
	{
		return sendAndGetReceipt(i_functionKey, i_inputMsg, null, null);
	}

	public Receipt sendAndGetReceipt(
			String i_functionKey,
			String i_inputMsg,
			Properties i_jmsPropertyTagAndValuesToSet)
			throws ServiceException
	{
		return sendAndGetReceipt(i_functionKey, i_inputMsg, i_jmsPropertyTagAndValuesToSet, null);
	}

	public Receipt sendAndGetReceipt(
			String i_functionKey,
			String i_inputMsg,
			Properties i_jmsPropertyTagAndValuesToSet,
			Properties i_deliverConfigOverrides)
			throws ServiceException
	{
		//initialize receipt
		setReceipt(null);

		sendAndReceiveCommandInvoker(
				i_functionKey,
				i_inputMsg,
				i_jmsPropertyTagAndValuesToSet,
				null,
				i_deliverConfigOverrides,
				new SendAndGetReceiptCommand(m_logger));

		return getReceipt();
	}

	public Receipt sendAndGetReceipt(
			String i_functionKey,
			Object i_object)
			throws ServiceException
	{
		return sendAndGetReceipt(i_functionKey, i_object, null, null);
	}

	public Receipt sendAndGetReceipt(
			String i_functionKey,
			Object i_object,
			Properties i_jmsPropertyTagAndValuesToSet)
			throws ServiceException
	{
		return sendAndGetReceipt(
				i_functionKey,
				i_object,
				i_jmsPropertyTagAndValuesToSet,
				null);
	}

	public Receipt sendAndGetReceipt(
			String i_functionKey,
			Object i_object,
			Properties i_jmsPropertyTagAndValuesToSet,
			Properties i_deliverConfigOverrides)
			throws ServiceException
	{
		return sendAndGetReceipt(
				i_functionKey,
				new Object[] { i_object },
				i_jmsPropertyTagAndValuesToSet,
				i_deliverConfigOverrides);
	}

	public Receipt sendAndGetReceipt(
			String i_functionKey,
			Object[] i_objectArray)
			throws ServiceException
	{
		return sendAndGetReceipt(i_functionKey, i_objectArray, null, null);
	}

	public Receipt sendAndGetReceipt(
			String i_functionKey,
			Object[] i_objectArray,
			Properties i_jmsPropertiesToSet)
			throws ServiceException
	{
		return sendAndGetReceipt(i_functionKey, i_objectArray, i_jmsPropertiesToSet, null);
	}

	public Receipt sendAndGetReceipt(
			String i_functionKey,
			Object[] i_objectArray,
			Properties i_jmsPropertyTagAndValuesToSet,
			Properties i_deliveryConfigOverrides)
			throws ServiceException
	{
		validateEncoderAndDecoderAreSet();
		String inputMessage = getEncoder().encode(i_objectArray);

		return
				sendAndGetReceipt(
						i_functionKey,
						inputMessage,
						i_jmsPropertyTagAndValuesToSet,
						i_deliveryConfigOverrides);

	}

	public Object[] pickup(
			Receipt receipt)
			throws ServiceException
	{
		return pickup(receipt, null);
	}

	public Object[] pickup(
			Receipt receipt,
			Properties jmsPropertiesInResponse)
			throws ServiceException
	{
		Message responseMessage = null;
		try
		{
			responseMessage = getMessenger().pickup(receipt.getValue());
			m_logger.log(LogEventId.DEBUG_LEVEL_1, "pickup() received message:" + responseMessage);

		}
		catch(Exception e)
		{
			throw new ServiceException(e);
		}

		try
		{
			JMSMessageLogger.recordMessage(m_logger, responseMessage);
		}
		catch (Exception e)
		{
			log(
					LogEventId.DEBUG_LEVEL_2,
					"Failed to log request message with the following exception: " + e);
		}

		String returnMessage = handleEmbusResponse(responseMessage, jmsPropertiesInResponse);
		return getDecoder().decode(returnMessage);
	}

	/**
	 * @see ServiceAccess#send(String, Object, Properties, Properties)
	 */
	public void send(String i_functionKey, Object objectToSend) throws ServiceException
	{
		send(i_functionKey, new Object[] { objectToSend });
	}

	/**
	 * Method send performs a send only.
	 * @param i_functionKey String The function to perform.  The function
	 * is defined in the XML configuration file as an element of the same
	 * name (e.g. passing validateStateCode as the functionKey will pass
	 * the configuration defined by the "validateStateCode" element).
	 * Depending on the type of messaging, the function element will
	 * have a "delivery" and a "pickup" child element.
	 * @param i_objectToSend Object The Object to be encoded and sent.  The Object
	 * must be encodable by the "Encoder" object set to the ServiceAccess.
	 * @param i_jmsPropertyTagAndValuesToSet Properties a Properties object
	 * containing the set of JMS Message properties to set to the message
	 * to deliver.  It can be null.  In which case it won't be used.
	 * @param i_deliverConfigOverrides Properties a Properties object
	 * containing a sets of key value pairs used to override the
	 * configuration settings are runtime.  It can be null.
	 * In which case it won't be used.
	 * @throws ServiceException
	 */
	public void send(
			String i_functionKey,
			Object i_objectToSend,
			Properties i_jmsPropertyTagAndValuesToSet,
			Properties i_deliveryConfigOverrides)
			throws ServiceException
	{
		send(i_functionKey, new Object[] { i_objectToSend }, i_jmsPropertyTagAndValuesToSet, i_deliveryConfigOverrides);
	}

	/**
	 * @see ServiceAccess#send(String, Object[], Properties, Properties)
	 */
	public void send(String i_functionKey, Object[] i_objectToSend) throws ServiceException
	{
		send(i_functionKey, i_objectToSend, null);
	}

	/**
	 * @link #send(String, Object[], Properties, Properties)
	 */
	public void send(String i_functionKey, Object[] i_objectsToSend, Properties i_jmsPropertyTagAndValuesToSet)
			throws ServiceException
	{
		validateEncoderIsSet();
		String inputMessage = getEncoder().encode(i_objectsToSend);
		send(i_functionKey, inputMessage, i_jmsPropertyTagAndValuesToSet);
	}

	/**
	 * Method send performs a send only.
	 *   1. validate that an encoder is set
	 *   2. encode the object array to a String
	 *   3. deliver the XML in a message
	 * @param i_functionKey String The function to perform.  The function
	 * is defined in the XML configuration file as an element of the same
	 * name (e.g. passing validateStateCode as the functionKey will pass
	 * the configuration defined by the "validateStateCode" element).
	 * Depending on the type of messaging, the function element will
	 * have a "delivery" and a "pickup" child element.
	 * @param i_objectsToSend Object[] The Array of Objects to be encoded and sent.
	 * The Object must be encodable by the "Encoder" object set to the
	 * ServiceAccess.
	 * @param i_jmsPropertyTagAndValuesToSet Properties a Properties object
	 * containing the set of JMS Message properties to set to the message
	 * to deliver.  It can be null.  In which case it won't be used.
	 * @param i_deliverConfigOverrides Properties a Properties object
	 * containing a sets of key value pairs used to override the
	 * configuration settings are runtime.  It can be null.
	 * In which case it won't be used.
	 * @throws ServiceException
	 */
	public void send(
			String i_functionKey,
			Object[] i_objectsToSend,
			Properties i_jmsPropertyTagAndValuesToSet,
			Properties i_deliveryConfigOverrides)
			throws ServiceException
	{
		validateEncoderIsSet();
		String inputMessage = getEncoder().encode(i_objectsToSend);
		send(i_functionKey, inputMessage, i_jmsPropertyTagAndValuesToSet, i_deliveryConfigOverrides);
	}

	/**
	 * @see ServiceAccess#send(String, String, Properties, Properties)
	 */
	public void send(String i_functionKey, String i_inputMsg) throws ServiceException
	{
		send(i_functionKey, i_inputMsg, null);
	}

	/**
	 * @link #send(String, String, Properties, Properties)
	 */
	public void send(String i_functionKey, String i_inputMsg, Properties i_jmsPropertyTagAndValuesToSet)
			throws ServiceException
	{
		send(i_functionKey, i_inputMsg, i_jmsPropertyTagAndValuesToSet, null);
	}

	/**
	 * Method send performs a send only.
	 * @param i_functionKey String The function to perform.  The function
	 * is defined in the XML configuration file as an element of the same
	 * name (e.g. passing validateStateCode as the functionKey will pass
	 * the configuration defined by the "validateStateCode" element).
	 * Depending on the type of messaging, the function element will
	 * have a "delivery" and a "pickup" child element.
	 * @param i_inputMsg String The Object to be sent as is.  No encoding nor
	 * decoding is performed.
	 * @param i_jmsPropertyTagAndValuesToSet Properties a Properties object
	 * containing the set of JMS Message properties to set to the message
	 * to deliver.  It can be null.  In which case it won't be used.
	 * @param i_deliverConfigOverrides Properties a Properties object
	 * containing a sets of key value pairs used to override the
	 * configuration settings are runtime.  It can be null.
	 * In which case it won't be used.
	 * @throws ServiceException
	 */
	public void send(
			String i_functionKey,
			String i_inputMsg,
			Properties i_jmsPropertyTagAndValuesToSet,
			Properties i_deliveryConfigOverrides)
			throws ServiceException
	{
		sendAndReceiveCommandInvoker(
				i_functionKey,
				i_inputMsg,
				i_jmsPropertyTagAndValuesToSet,
				null,
				i_deliveryConfigOverrides,
				new SendCommand(m_logger));
	}

	/**
	 * Method sendAndReceiveCommandInvoker.  This method activates the commands.
	 * Prior to doing so the invoker performs setup work to create the
	 * messengers, messages, etc. and then calls activates the commands passing
	 * the required objects.
	 * @param i_functionKey String The function to perform.  The function
	 * is defined in the XML configuration file as an element of the same
	 * name (e.g. passing validateStateCode as the functionKey will pass
	 * the configuration defined by the "validateStateCode" element).
	 * Depending on the type of messaging, the function element will
	 * have a "delivery" and a "pickup" child element.
	 * @param inputMsg String The input message as a String to send.
	 * @param i_jmsPropertyTagAndValuesToSet Properties a Properties object
	 * containing the set of JMS Message properties to set to the message
	 * to deliver.  It can be null.  In which case it won't be used.
	 * @param o_jmsPropertiesInResponse Properties a Properties object
	 * to hold the properties retrieved from the response message.
	 * It can be null.  In which case it won't be used.
	 * @param i_deliverConfigOverrides Properties a Properties object
	 * containing a sets of key value pairs used to override the
	 * configuration settings are runtime.  It can be null.
	 * In which case it won't be used.
	 * @param command The command to invoke.
	 * @return String the text body of the response message as a String
	 * @throws ServiceException
	 */
	private String sendAndReceiveCommandInvoker(
			String i_functionKey,
			String inputMsg,
			Properties jmsPropertyTagAndValuesToSet,
			Properties jmsPropertiesInResponse,
			Properties deliverConfigOverrides,
			Command command)
			throws ServiceException
	{
		return sendAndReceiveHttp(i_functionKey,inputMsg,jmsPropertyTagAndValuesToSet);
		/*
		Message responseMessage = null;

		try
		{
			try
			{
				TextMessage requestMessage = null;

				try
				{

					// JMSOps - call getMessenger() to get Messenger

					requestMessage = getMessenger().createTextMessage(inputMsg);

					log(LogEventId.DEBUG_LEVEL_2, "Request message created.");

					setJMSPropertiesToMessage(requestMessage, jmsPropertyTagAndValuesToSet);

					log(LogEventId.DEBUG_LEVEL_2, "setJMSPropertiesToMessage completed.");

					try
					{
						responseMessage =
							command.execute(m_messenger, requestMessage, i_functionKey, deliverConfigOverrides);
					}
					catch (NoDeliveryExpectedException noDeliveryExpectedException)
					{
						// return early if no delivery expected
						// not the same as a null delivery which
						// may be an indication of an error
						return null;
					}
					finally
					{
						try
						{
							try
							{
								JMSMessageLogger.recordMessage(m_logger, requestMessage);
							}
							catch (Exception e)
							{
								log(
									LogEventId.DEBUG_LEVEL_2,
									"Failed to log request message with the following exception: " + e);
							}
						}
						catch (Exception e)
						{
							e.printStackTrace();
						}
					}

				}
				catch (Exception exception)
				{
					log(
						LogEventId.DEBUG_LEVEL_2,
						exception.getClass().getName()
							+ " encountered.  Attempting to refresh messenger for environment: "
							+ getEnvironmentName());

					m_messenger = null;

					boolean retryIndicator = true;
					try
					{
						String retryValue =
							(String) getSubsetMap(JMSOPS_MESSAGING_FUNCTIONS_PREFIX_KEY + i_functionKey).get(
								"shouldRetryOnError");

						if (retryValue != null)
						{
							retryIndicator = new Boolean(retryValue).booleanValue();
						}
					}
					catch (Exception e)
					{
						log(LogEventId.DEBUG_LEVEL_2, "Unable to retrieve shouldRetryOnError indicator: " + e);
					}

					if (retryIndicator)
					{
						log(LogEventId.INFO_LEVEL_2, "RetryIndicator is set " + retryIndicator + ".  Will retry.");
						try
						{
							requestMessage = getMessenger().createTextMessage(inputMsg);

							log(LogEventId.DEBUG_LEVEL_2, "Retry request message created.");

							setJMSPropertiesToMessage(requestMessage, jmsPropertyTagAndValuesToSet);

							log(LogEventId.DEBUG_LEVEL_2, "Retry setJMSPropertiesToMessage completed.");

							try
							{
								responseMessage =
									command.execute(m_messenger, requestMessage, i_functionKey, deliverConfigOverrides);
							}
							catch (NoDeliveryExpectedException noDeliveryExpectedException)
							{
								// return early if no delivery expected
								// not the same as a null delivery which
								// may be an indication of an error
								return null;
							}
							finally
							{
								try
								{
									try
									{
										JMSMessageLogger.recordMessage(m_logger, requestMessage);
									}
									catch (Exception e)
									{
										log(
											LogEventId.DEBUG_LEVEL_2,
											"Failed to log request message"
												+ " with the following"
												+ " exception: "
												+ e);
									}
								}
								catch (Exception e)
								{
									e.printStackTrace();
								}
							}
						}
						catch (RuntimeException re)
						{
							throw new JMSException(re.getMessage());
						}
						catch (Exception e)
						{
							log(LogEventId.DEBUG_LEVEL_2, "Retry failed with the following exception: " + e);

						}
					}
					else
					{
						log(LogEventId.INFO_LEVEL_2, "Retry indicator is " + retryIndicator + ". No retry attemped.");

						if (exception instanceof RuntimeException)
						{
							throw new JMSException(exception.getMessage());
						}
						else
						{
							throw exception;
						}
					}

				}
			}
			catch (Exception exception)
			{
				throw new ServiceException(exception);
			}

			if (responseMessage == null)
			{
				// response is null, receive timed out
				throw new ReceiveTimeOutException("Receive timeout reached" + " and the response received is null.");
			}
			else
			{
				try
				{
					// log response message
					JMSMessageLogger.recordMessage(m_logger, responseMessage);
				}
				catch (Exception e)
				{
					log(
						LogEventId.DEBUG_LEVEL_2,
						"Failed to log response message with the following" + " exception: " + e);
				}
			}
		}
		finally
		{
		}

		return handleEmbusResponse(responseMessage, jmsPropertiesInResponse);
		*/
	}

	public String sendAndReceiveHttp(
			String functionKey,
			String inputMessage,
			Properties jmsPropertyTagAndValuesToSet)
			throws ServiceException
	{
		RcTypesEnum i_rcType = RcTypesEnum.FACS;
		RcTextMessage textMessage = createMessage(inputMessage, i_rcType, jmsPropertyTagAndValuesToSet);

		m_logger.log(LogEventId.DEBUG_LEVEL_1, "entered ServiceAccess.sendAndReceiveHttp()");

		//legacy log statement
		m_logger.log(LogEventId.REMOTE_CALL, "ServiceAccess:sendAndReceiveHttp():");

		final RcTextMessage response = rcRestFacade.processRequest(textMessage);

		if (response.getStatusCode() == RcResponseStatusCodesEnum.APPLICATION_ERROR) {
			throw new ServiceException("EXTERNAL exception @ ServiceAccess.sendAndReceiveHttp():"+response.getErrorDetails());
		}

		return response.getText();
	}

	private RcTextMessage createMessage(String message, RcTypesEnum rcType, Properties properties)
	{
		HashMap<String, String> map = new HashMap<String,String>();

		// set application specific properties
		map.put(
				"embusResponseMessageExpiration",
				properties.getProperty("embusResponseMessageExpiration"));
		map.put(
				"embusApplicationID",
				properties.getProperty("embusApplicationID"));
		map.put(
				"embusMessageTag",
				properties.getProperty("embusMessageTag"));
		map.put(
				"embusMessagingMode",
				properties.getProperty("embusMessagingMode"));

		// set the environment destination, DV, SAT, etc.
		if (properties.getProperty("DESTINATION") != null)
		{
			map.put(
					"DESTINATION",
					properties.getProperty("DESTINATION"));
		}
		if (properties.getProperty("EVENTNAME") != null)
		{
			map.put(
					"EVENTNAME",
					properties.getProperty("EVENTNAME"));
		}
		if (properties.getProperty("CLIENTID") != null)
		{
			map.put(
					"CLIENTID",
					properties.getProperty("CLIENTID"));
		}
		if (properties.getProperty("GROUPID") != null)
		{
			map.put(
					"GROUPID",
					properties.getProperty("GROUPID"));
		}
		if (properties.getProperty("USERID") != null)
		{
			map.put(
					"USERID",
					properties.getProperty("USERID"));
		}
		if (properties.getProperty("PASSWORD") != null)
		{
			map.put(
					"PASSWORD",
					properties.getProperty("PASSWORD"));
		}
		if (properties.getProperty("WIRECENTER") != null)
		{
			map.put(
					"WIRECENTER",
					properties.getProperty("WIRECENTER"));
		}
		if (properties.getProperty("Destination") != null)
		{
			map.put(
					"DESTINATION",
					properties.getProperty("Destination"));
		}

		LocalDateTime localDateTime = LocalDateTime.now();

		return RcTextMessage.builder()
				.correlationID(
						"LmsLimBis-" + UUID.randomUUID() + "-" + localDateTime.toEpochSecond(ZoneOffset.UTC))
				.clientId("LmsLimBis")
				.text(message)
				.timestamp(DateTimeUtils.nowUTC().toEpochSecond(ZoneOffset.UTC))
				.type(rcType)
				.motsId(99999l)
				.map(map)
				.build();
	}

	/**
	 * Method defaultHandleEmbusResponse.  Provides the default response 
	 * handling logic.
	 * @see handleEmbusResponse
	 * @param aMessage
	 * @return String
	 * @throws ServiceException
	 */
	protected String defaultHandleEmbusResponse(Message aMessage, Properties propertiesInResponse)
			throws ServiceException
	{

		String responseXMLString = null;

		if (propertiesInResponse != null)
		{
			try
			{
				log(LogEventId.INFO_LEVEL_2, "Retrieving all JMS application properties from the " + "response.");

				Enumeration propertyNames = aMessage.getPropertyNames();
				while (propertyNames.hasMoreElements())
				{
					String propName = (String) propertyNames.nextElement();

					addJMSPropertyToPropertiesInResponse(
							propertiesInResponse,
							propName,
							aMessage.getStringProperty(propName));
				}
			}
			catch (Exception exception)
			{
				log(
						LogEventId.DEBUG_LEVEL_2,
						"Failed to retrieve all JMS application properties from " + "the response message." + exception);
			}

			try
			{
				log(LogEventId.INFO_LEVEL_2, "Retrieving all JMS Header properties from the response.");

				addJMSPropertyToPropertiesInResponse(
						propertiesInResponse,
						"JMSCorrelationID",
						aMessage.getJMSCorrelationID());
				addJMSPropertyToPropertiesInResponse(
						propertiesInResponse,
						"JMSCorrelationIDAsBytes",
						aMessage.getJMSCorrelationIDAsBytes());
				addJMSPropertyToPropertiesInResponse(
						propertiesInResponse,
						"JMSDeliveryMode",
						String.valueOf(aMessage.getJMSDeliveryMode()));
				addJMSPropertyToPropertiesInResponse(
						propertiesInResponse,
						"JMSDestination",
						aMessage.getJMSDestination());
				addJMSPropertyToPropertiesInResponse(
						propertiesInResponse,
						"JMSExpiration",
						String.valueOf(aMessage.getJMSExpiration()));
				addJMSPropertyToPropertiesInResponse(propertiesInResponse, "JMSMessageID", aMessage.getJMSMessageID());
				addJMSPropertyToPropertiesInResponse(
						propertiesInResponse,
						"JMSPriority",
						String.valueOf(aMessage.getJMSPriority()));
				addJMSPropertyToPropertiesInResponse(
						propertiesInResponse,
						"JMSRedelivered",
						String.valueOf(aMessage.getJMSRedelivered()));

				Object replyToDest = aMessage.getJMSReplyTo();
				// To reduce the amount of log chatter, only attempt to add the
				// JMSReplyTo if it exists.  Most responses will NOT include a
				// JMSReplyTo.
				if (replyToDest != null)
				{
					addJMSPropertyToPropertiesInResponse(propertiesInResponse, "JMSReplyTo", replyToDest);
				}

				addJMSPropertyToPropertiesInResponse(
						propertiesInResponse,
						"JMSTimestamp",
						String.valueOf(aMessage.getJMSTimestamp()));
				addJMSPropertyToPropertiesInResponse(propertiesInResponse, "JMSType", aMessage.getJMSType());
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
		else
		{
			log(
					LogEventId.DEBUG_LEVEL_2,
					"Unable to fill the properties object with the JMS "
							+ "Header/Application Properties from the response message "
							+ "because the properties object itself is null.");
		}

		try
		{
			if (aMessage instanceof TextMessage)
			{
				TextMessage aTextMessage = (TextMessage) aMessage;
				responseXMLString = aTextMessage.getText();
			}
			else
			{
				log(
						LogEventId.DEBUG_LEVEL_1,
						"The message is not of type TextMessage.  " + "Default handling expects only TextMessage.");
			}

			if (aMessage.propertyExists(MessageConstants.ERROR_CODE))
			{
				if (aMessage.propertyExists(MessageConstants.MESSAGE_TAG))
				{
					if (aMessage.getStringProperty(MessageConstants.MESSAGE_TAG) != null)
					{
						if (aMessage.getStringProperty(MessageConstants.MESSAGE_TAG).equals(EMBUS_ERROR_LITERAL))
						{
							throw new EmbusServiceException(
									aMessage.getStringProperty(MessageConstants.ERROR_CODE),
									aMessage.getStringProperty(MessageConstants.ERROR_DESCRIPTION),
									propertiesInResponse,
									responseXMLString);
						}
						else if (aMessage.getStringProperty(MessageConstants.MESSAGE_TAG).endsWith(RC_ERROR_LITERAL))
						{
							throw new ResourceConnectorServiceException(
									aMessage.getStringProperty(MessageConstants.ERROR_CODE),
									aMessage.getStringProperty(MessageConstants.ERROR_DESCRIPTION),
									propertiesInResponse,
									responseXMLString);
						}
					}
				}
			}
		}
		catch (ServiceException serviceException)
		{
			throw serviceException;
		}
		catch (Exception exception)
		{
			throw new ServiceException(exception);
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

	/**
	 * Method handleEmbusResponse provides default behavior that expects and
	 * handles text response message.  It does noting but retrives the text
	 * content of the message.  If you need more control over handling of the
	 * response message you will need to override this method and provide
	 * your own implementation.
	 * @param aMessage Message The Message to handle.  By default this version
	 * of the method only handles TextMessage.
	 * @param propertiesInResponse Properties The properties object to "fill"
	 * with the message properties of the response message.
	 * @return String The String body of the TextMessage.
	 * @throws ServiceException Will throw a ServiceException if the message is 
	 * not a TextMessage.
	 */
	public abstract String handleEmbusResponse(Message aMessage, Properties propertiesInResponse)
			throws ServiceException;

	/**
	 * Method setJMSPropertiesToMessage transfers the properties found in the
	 * Properties parameter to the JMS Message parameter as message properties.
	 *
	 * @param msg the JMS message to add the JMS properties to.
	 * @param jmsPropertyTagAndValuesToSet a Properties object containing key
	 * value pairs that should be set the JMS Message.
	 * @throws JMSException can be thrown if any calls to 
	 * Message::setObjectProperty fails.
	 */
	private void setJMSPropertiesToMessage(Message msg, Properties jmsPropertyTagAndValuesToSet) throws JMSException
	{
		if (jmsPropertyTagAndValuesToSet != null)
		{
			log(LogEventId.DEBUG_LEVEL_2, "JMSPropertiesToSet: " + jmsPropertyTagAndValuesToSet);

			Enumeration keys = jmsPropertyTagAndValuesToSet.keys();

			String key = null;
			while (keys.hasMoreElements())
			{
				key = keys.nextElement().toString();
				if (key.equals("JMSCorrelationID"))
				{
					try
					{
						msg.setJMSCorrelationID(jmsPropertyTagAndValuesToSet.getProperty(key));
					}
					catch (Exception e)
					{
						log(LogEventId.DEBUG_LEVEL_2, "Unable to set " + key + ": " + e.getMessage());
					}
				}
				else if (key.equals("JMSCorrelationIDAsBytes"))
				{
					try
					{
						msg.setJMSCorrelationIDAsBytes((byte[]) jmsPropertyTagAndValuesToSet.get(key));
					}
					catch (Exception e)
					{
						log(LogEventId.DEBUG_LEVEL_2, "Unable to set " + key + ": " + e.getMessage());
					}

				}
				else if (key.equals("JMSDeliveryMode"))
				{
					try
					{
						msg.setJMSDeliveryMode(new Integer(jmsPropertyTagAndValuesToSet.getProperty(key)).intValue());
					}
					catch (Exception e)
					{
						log(LogEventId.DEBUG_LEVEL_2, "Unable to set " + key + ": " + e.getMessage());
					}
				}
				else if (key.equals("JMSDestination"))
				{
					try
					{
						msg.setJMSDestination((Destination) jmsPropertyTagAndValuesToSet.get(key));
					}
					catch (Exception e)
					{
						log(LogEventId.DEBUG_LEVEL_2, "Unable to set " + key + ": " + e.getMessage());
					}
				}
				else if (key.equals("JMSExpiration"))
				{
					try
					{
						msg.setJMSExpiration(new Long(jmsPropertyTagAndValuesToSet.getProperty(key)).longValue());
					}
					catch (Exception e)
					{
						log(LogEventId.DEBUG_LEVEL_2, "Unable to set " + key + ": " + e.getMessage());
					}

				}
				else if (key.equals("JMSMessageID"))
				{
					try
					{
						msg.setJMSMessageID(jmsPropertyTagAndValuesToSet.getProperty(key));
					}
					catch (Exception e)
					{
						log(LogEventId.DEBUG_LEVEL_2, "Unable to set " + key + ": " + e.getMessage());
					}

				}
				else if (key.equals("JMSPriority"))
				{
					try
					{
						msg.setJMSPriority(new Integer(jmsPropertyTagAndValuesToSet.getProperty(key)).intValue());
					}
					catch (Exception e)
					{
						log(LogEventId.DEBUG_LEVEL_2, "Unable to set " + key + ": " + e.getMessage());
					}
				}
				else if (key.equals("JMSRedelivered"))
				{
					try
					{
						msg.setJMSRedelivered(
								new Boolean(jmsPropertyTagAndValuesToSet.getProperty(key)).booleanValue());
					}
					catch (Exception e)
					{
						log(LogEventId.DEBUG_LEVEL_2, "Unable to set " + key + ": " + e.getMessage());
					}

				}
				else if (key.equals("JMSReplyTo"))
				{
					try
					{
						msg.setJMSReplyTo((Destination) jmsPropertyTagAndValuesToSet.get(key));
					}
					catch (Exception e)
					{
						log(LogEventId.DEBUG_LEVEL_2, "Unable to set " + key + ": " + e.getMessage());
					}
				}
				else if (key.equals("JMSTimestamp"))
				{
					try
					{
						msg.setJMSTimestamp(new Long(jmsPropertyTagAndValuesToSet.getProperty(key)).longValue());
					}
					catch (Exception e)
					{
						log(LogEventId.DEBUG_LEVEL_2, "Unable to set " + key + ": " + e.getMessage());
					}
				}
				else if (key.equals("JMSType"))
				{
					try
					{
						msg.setJMSType(jmsPropertyTagAndValuesToSet.getProperty(key));
					}
					catch (Exception e)
					{
						log(LogEventId.DEBUG_LEVEL_2, "Unable to set " + key + ": " + e.getMessage());
					}

				}
				else
				{
					try
					{
						msg.setObjectProperty(key, jmsPropertyTagAndValuesToSet.getProperty(key));
					}
					catch (Exception e)
					{
						log(LogEventId.DEBUG_LEVEL_2, "Unable to set " + key + ": " + e.getMessage());
					}

				}
			}
		}
	}

	/**
	 * Returns the m_embusConfigFileName.
	 * @return String
	 */
	public String getConfigFileName()
	{
		return m_embusConfigFileName;
	}

	/**
	 * Sets the m_embusConfigFileName.
	 * @param embusConfigFileName The embusConfigFileName to set
	 */
	public void setConfigFileName(String embusConfigFileName)
	{
		this.m_embusConfigFileName = embusConfigFileName;
	}

	/**
	 * Returns the environmentName.
	 * @return String
	 */
	public String getEnvironmentName()
	{
		return m_environmentName;
	}

	/**
	 * Sets the m_environmentName.
	 * @param environmentName The environmentName to set
	 */
	public void setEnvironmentName(String environmentName)
	{
		this.m_environmentName = environmentName;
	}

	/**
	 * Returns the m_propertiesMap.
	 * @return HashMap
	 */
	private HashMap getPropertiesMap()
	{
		return m_propertiesMap;
	}

	/**
	 * Sets the m_propertiesMap.
	 * @param propertiesMap The propertiesMap to set
	 */
	private void setPropertiesMap(HashMap propertiesMap)
	{
		this.m_propertiesMap = propertiesMap;
	}

	/**
	 * Method getSubsetMap retrieves a subset of the XML Configuration
	 * file converted to a HashMap.  The key is a period (.) delimited String
	 * denoting the location of the element in the XML tree.  For example:
	 * To retrieve the "messenger" subset from the XML configuration, pass
	 * "embus.messenger" as the subsetKey.
	 * @param subsetKey
	 * @return HashMap
	 */
	protected HashMap getSubsetMap(String subsetKey)
	{
		HashMap o_map = null;

		try
		{
			o_map = MapExtractor.subset(getPropertiesMap(), subsetKey);
		}
		catch (Exception e)
		{
			log(
					LogEventId.INFO_LEVEL_1,
					"Error occured while retrieving "
							+ subsetKey
							+ " configuration.  "
							+ subsetKey
							+ " configuration will not be available for use.");
		}

		if (o_map == null)
		{
			log(LogEventId.INFO_LEVEL_2, "Map extractor was unable to find + " + subsetKey + " map.");
		}

		return o_map;
	}

	/**
	 * Closes any existing messaging artifacts that may cause resource leaks.
	 */
	public void exit()
	{
	}

	/**
	 * finalize is overridden to ensure that this ServiceAccess is cleaned up
	 * on garbage collection
	 * @see java.lang.Object#finalize()
	 */
	protected void finalize() throws Throwable
	{
		super.finalize();
	}

	/**
	 * Method setLogger.
	 * @param logger
	 */
	private void setLogger(Logger logger)
	{
		this.m_logger = logger;
	}

	/**
	 * Method log.
	 * @param eventId
	 * @param message
	 */
	protected void log(String eventId, String message)
	{
		if (m_logger != null)
		{
			m_logger.log(eventId, message);
		}
	}

	/**
	 * Method setEncoder.
	 * @param encoder
	 */
	public void setEncoder(IEncoder encoder)
	{
		m_encoder = encoder;
	}

	/**
	 * Method getEncoder.
	 * @return IEncoder
	 */
	private IEncoder getEncoder()
	{
		return m_encoder;
	}

	/**
	 * Method setDecoder.
	 * @param decoder
	 */
	public void setDecoder(IDecoder decoder)
	{
		m_decoder = decoder;
	}

	/**
	 * Method getDecoder.
	 * @return IDecoder
	 */
	private IDecoder getDecoder()
	{
		return m_decoder;
	}

	/**
	 * Method validateEncoderAndDecoderSet ensures that both encoder and decoder
	 * are set.
	 * @throws ServiceException
	 */
	private void validateEncoderAndDecoderAreSet() throws ServiceException
	{

		if (!isEncoderSet() && !isDecoderSet())
		{
			throw new ServiceException(
					"Encoder and Decoder is null.  "
							+ "ServiceAccess will not be able to perform any "
							+ "encoding or decoding.");
		}

		validateEncoderIsSet();

		validateDecoderIsSet();

	}

	/**
	 * Method validateDecoderIsSet ensures a decoder is set.
	 * @throws ServiceException thrown if a Decoder is not set.
	 */
	private void validateDecoderIsSet() throws ServiceException
	{
		if (!isDecoderSet())
		{
			throw new ServiceException(
					"Decoder is null.  ServiceAccess will " + "not be able to perform any decoding.");
		}
	}

	/**
	 * Method validateEncoderIsSet ensures an encoder is set.
	 * @throws ServiceException thrown if an Encoder is not set.
	 */
	private void validateEncoderIsSet() throws ServiceException
	{
		if (!isEncoderSet())
		{
			throw new ServiceException(
					"Encoder is null.  ServiceAccess will " + "not be able to perform any encoding.");
		}
	}

	/**
	 * Method isEncoderSet.
	 * @return boolean
	 * @throws ServiceException
	 */
	private boolean isEncoderSet() throws ServiceException
	{
		return getEncoder() != null;
	}

	/**
	 * Method isDecoderSet.
	 * @return boolean
	 */
	private boolean isDecoderSet()
	{
		return getDecoder() != null;
	}

	/**
	 * Returns the defaultCorrelationIDProvider.
	 * @return CorrelationIDProviderI
	 */
	public CorrelationIDProviderI getDefaultCorrelationIDProvider()
	{
		return defaultCorrelationIDProvider;
	}

	/**
	 * Sets the defaultCorrelationIDProvider.
	 * @param defaultCorrelationIDProvider The defaultCorrelationIDProvider to set
	 */
	public void setDefaultCorrelationIDProvider(CorrelationIDProviderI defaultCorrelationIDProvider)
	{
		this.defaultCorrelationIDProvider = defaultCorrelationIDProvider;
	}

	/**
	 * Returns a Messenger object.
	 */
	private Messenger getMessenger()
	{
		if (m_messenger == null)
		{
			log(LogEventId.DEBUG_LEVEL_2, "Creating messenger for environment: " + getEnvironmentName());
			m_messenger = new Messenger(m_environmentName);
		}

		return m_messenger;
	}

	/**
	 * Method getDeliverProperties retrieves only the valid properties from the input 
	 * hashmap. It returns a hashmap containing the valid properties that the JMSOps
	 * framework expects.
	 * @param iMap HashMap
	 * @return HashMap
	 */
	public HashMap getDeliverProperties(HashMap iMap)
	{
		HashMap deliverProperties = new HashMap();

		Set keys = iMap.keySet();
		Iterator iter = keys.iterator();

		while (iter.hasNext())
		{
			String key = ((String) iter.next()).trim();
			String jmsopsKey = null;

			if (key.trim().equalsIgnoreCase(RETRY_THRESHOLD))
			{
				try
				{
					deliverProperties.put(RETRY_THRESHOLD, new Integer((String) iMap.get(key)));
				}
				catch (Exception e)
				{
					log(LogEventId.DEBUG_LEVEL_2, "JMSOps property " + key + " not set.");
				}
			}
			else if (key.equalsIgnoreCase(DELIVERY_MODE) || key.equalsIgnoreCase(DELIVER + KEY_SEPARATOR + DELIVERY_MODE))
			{
				String deliveryMode = (String) iMap.get(key);
				if (key.equalsIgnoreCase(DELIVERY_MODE))
				{
					jmsopsKey = DELIVERY_MODE;
				}
				else
				{
					jmsopsKey = DELIVER + KEY_SEPARATOR + DELIVERY_MODE;
				}
				if (deliveryMode.trim().equalsIgnoreCase(PERSISTENT))
				{
					deliverProperties.put(jmsopsKey, new Integer(DeliveryMode.PERSISTENT));
				}
				else if (deliveryMode.trim().equalsIgnoreCase(NONPERSISTENT))
				{
					deliverProperties.put(jmsopsKey, new Integer(DeliveryMode.NON_PERSISTENT));
				}
			}
			else if (key.equalsIgnoreCase(PRIORITY) || key.equalsIgnoreCase(DELIVER + KEY_SEPARATOR + PRIORITY))
			{
				if (key.equalsIgnoreCase(PRIORITY))
				{
					jmsopsKey = PRIORITY;
				}
				else
				{
					jmsopsKey = DELIVER + KEY_SEPARATOR + PRIORITY;
				}
				try
				{
					deliverProperties.put(jmsopsKey, new Integer((String) iMap.get(key)));
				}
				catch (Exception e)
				{
					log(LogEventId.DEBUG_LEVEL_2, "JMSOps property " + key + " not set.");
				}
			}
			else if (key.equalsIgnoreCase(MESSAGE_EXPIRATION_MILLIS) || key.equalsIgnoreCase(DELIVER + KEY_SEPARATOR + MESSAGE_EXPIRATION_MILLIS))
			{
				if (key.equalsIgnoreCase(MESSAGE_EXPIRATION_MILLIS))
				{
					jmsopsKey = MESSAGE_EXPIRATION_MILLIS;
				}
				else
				{
					jmsopsKey = DELIVER + KEY_SEPARATOR + MESSAGE_EXPIRATION_MILLIS;
				}
				try
				{
					deliverProperties.put(jmsopsKey, new Long((String) iMap.get(key)));
				}
				catch (Exception e)
				{
					log(LogEventId.DEBUG_LEVEL_2, "JMSOps property " + key + " not set.");
				}
			}
			else if (key.equalsIgnoreCase(CORRELATION_ID) || key.equalsIgnoreCase(DELIVER + KEY_SEPARATOR + CORRELATION_ID))
			{
				if (key.equalsIgnoreCase(CORRELATION_ID))
				{
					jmsopsKey = CORRELATION_ID;
				}
				else
				{
					jmsopsKey = DELIVER + KEY_SEPARATOR + CORRELATION_ID;
				}
				deliverProperties.put(jmsopsKey, iMap.get(key));
			}
			else if (key.equalsIgnoreCase(MESSAGING_MODE) || key.equalsIgnoreCase(DELIVER + KEY_SEPARATOR + MESSAGING_MODE))
			{
				if (key.equalsIgnoreCase(MESSAGING_MODE))
				{
					jmsopsKey = MESSAGING_MODE;
				}
				else
				{
					jmsopsKey = DELIVER + KEY_SEPARATOR + MESSAGING_MODE;
				}
				deliverProperties.put(jmsopsKey, iMap.get(key));
			}
			else if (key.equalsIgnoreCase(MESSAGE_TAG) || key.equalsIgnoreCase(DELIVER + KEY_SEPARATOR + MESSAGE_TAG))
			{
				if (key.equalsIgnoreCase(MESSAGE_TAG))
				{
					jmsopsKey = MESSAGE_TAG;
				}
				else
				{
					jmsopsKey = DELIVER + KEY_SEPARATOR + MESSAGE_TAG;
				}
				deliverProperties.put(jmsopsKey, iMap.get(key));
			}
			else if (key.equalsIgnoreCase(MESSAGE_ID_TO_CORRELATION_ID) || key.equalsIgnoreCase(DELIVER + KEY_SEPARATOR + MESSAGE_ID_TO_CORRELATION_ID))
			{
				if (key.equalsIgnoreCase(MESSAGE_ID_TO_CORRELATION_ID))
				{
					jmsopsKey = MESSAGE_ID_TO_CORRELATION_ID;
				}
				else
				{
					jmsopsKey = DELIVER + KEY_SEPARATOR + MESSAGE_ID_TO_CORRELATION_ID;
				}
				try
				{
					deliverProperties.put(jmsopsKey, new Boolean((String) iMap.get(key)));
				}
				catch (Exception e)
				{
					log(LogEventId.DEBUG_LEVEL_2, "JMSOps property " + key + " not set.");
				}
			}
			else if (key.equalsIgnoreCase(RESPONSE_MESSAGE_EXPIRATION) || key.equalsIgnoreCase(DELIVER + KEY_SEPARATOR + RESPONSE_MESSAGE_EXPIRATION))
			{
				if (key.equalsIgnoreCase(RESPONSE_MESSAGE_EXPIRATION))
				{
					jmsopsKey = RESPONSE_MESSAGE_EXPIRATION;
				}
				else
				{
					jmsopsKey = DELIVER + KEY_SEPARATOR + RESPONSE_MESSAGE_EXPIRATION;
				}
				try
				{
					deliverProperties.put(jmsopsKey, new Long((String) iMap.get(key)));
				}
				catch (Exception e)
				{
					log(LogEventId.DEBUG_LEVEL_2, "JMSOps property " + key + " not set.");
				}
			}
			else if (key.equalsIgnoreCase(TIME_TO_WAIT_MILLIS))
			{
				try
				{
					deliverProperties.put(TIME_TO_WAIT_MILLIS, new Long((String) iMap.get(key)));
				}
				catch (Exception e)
				{
					log(LogEventId.DEBUG_LEVEL_2, "JMSOps property " + key + " not set.");
				}
			}
			else
			{
				if (!key.equalsIgnoreCase(SHOULD_RETRY_ON_ERROR))
				{
					log(LogEventId.DEBUG_LEVEL_2, "Invalid JMSOps property: " + key);
				}
			}
		}

		log(LogEventId.DEBUG_LEVEL_2, "deliverProperties: " + deliverProperties);

		return deliverProperties;
	}

	protected void setReceipt(Receipt rcpt)
	{
		m_receipt = rcpt;
	}

	protected Receipt getReceipt()
	{
		return m_receipt;
	}

	/**
	 * selfTest will validate connection to the broker by creating a dummy TextMessage.
	 */
	protected void selfTest()
	{
		log(LogEventId.DEBUG_LEVEL_2, "ServiceAccess::selfTest - Invoking getMessenger().createTextMessage().");
		getMessenger().createTextMessage("SELFTEST");
	}

}
