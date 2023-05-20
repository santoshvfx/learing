// $Id: AtlasEncoderDecoder.java,v 1.6 2005/02/09 16:52:36 as5472 Exp $

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

import java.io.IOException;
import java.io.StringReader;
import java.util.Properties;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

import com.sbc.bccs.utilities.Logger;
import com.sbc.bccs.utilities.PropertiesFileLoader;
import com.sbc.eia.bis.embus.service.access.DecoderException;
import com.sbc.eia.bis.embus.service.access.DefaultJAXBEncoderDecoder;
import com.sbc.eia.bis.embus.service.access.EncoderException;
import com.sbc.eia.bis.embus.service.access.ServiceException;
import com.sbc.eia.bis.embus.service.atlas.AtlasSoapFaultException;
import com.sbc.eia.bis.embus.service.atlas.interfaces.ResponseInfo;
import com.sbc.eia.bis.framework.logging.LogEventId;

/** 
 *  Encoding and decoding XML messages for Atlas.
 *  
 */

public class AtlasEncoderDecoder extends DefaultJAXBEncoderDecoder
{
    // URIs used in decoding an Atlas Fault
    private static String s_cingular_data_model_uri = null;
    private static String s_error_response_uri = null;
    private static String s_soap_11_env_uri = null;
    
    // name of file containing the namespace version information
    private static final String NAME_SPACE_VERSION_FILE_NAME = "namespaceversion.properties";
    
    // use as defaults to construct version specific URIs, will need to change
    // if base URI convention changes too
    private static final String CNG_MODEL_PRE =
        "http://csi.cingular.com/CSI/Namespaces/";
    private static final String CNG_MODEL_POST =
        "Types/Public/CingularDataModel.xsd";
    private static final String ERR_RESPONSE_PRE =
        "http://csi.cingular.com/CSI/Namespaces/";
    private static final String ERR_RESPONSE_POST =
        "Types/Public/ErrorResponse.xsd";

    private static final String DEFAULT_SOAP_ENV_URI = "http://schemas.xmlsoap.org/soap/envelope/";
    
    private static final String DATA_MODEL_NS_URI_KEY= "com.cingular.csi.datamodel.namespace.uri";
    private static final String ERROR_RESPONSE_NS_URI_KEY= "com.cingular.csi.errorresponse.namespace.uri";
    private static final String SOAP_ENV_NS_URI_KEY = "com.cingular.csi.soapenvelope.namespace.uri";
    private static final String NAMESPACE_VERSION_KEY = "com.cingular.csi.namespace.version";
    
    private XMLReader parser = null;
    private Logger m_logger = null;

    private class AtlasExceptionSAXHandler extends DefaultHandler
    {
        private static final String RESPONSE_LOCAL_NAME = "Response";
        private static final String SERVICE_PROVIDER_ENTITY_LOCAL_NAME =
            "ServiceProviderEntity";
        private static final String SOAP_11_FAULT_LOCAL_NAME = "Fault";

        private String currentElement = null;

        private boolean isResponseParent = false;
        private boolean isServiceProviderEntityParent = false;

        private AtlasSoapFaultException aAtlasSoapFaultException = null;
        private AtlasSoapFaultException
            .ServiceProviderEntity tmpServiceProviderEntity =
            null;

        /** Description
         *  Exception class used specifically to indicate that parsing has
         *  ceased in order to allow normal xml parsing to continue..
         *  Description
         */
        private class StopParsingException extends SAXException
        {
            public StopParsingException(String s)
            {
                super(s);
            }
        }

        /**
         * Method initAtlasSoapFaultException initializes and returns an 
         * instance of the AtlasSoapFaultException.
         * @return AtlasSoapFaultException
         */
        private AtlasSoapFaultException initAtlasSoapFaultException()
        {
            if (aAtlasSoapFaultException == null)
                aAtlasSoapFaultException = new AtlasSoapFaultException();

            return aAtlasSoapFaultException;
        }

        /**
         * @see org.xml.sax.ContentHandler#startElement(String, String, String, Attributes)
         */
        public void startElement(
            String URI,
            String localName,
            String qname,
            Attributes attributes)
            throws SAXException
        {
            super.startElement(URI, localName, qname, attributes);

            currentElement = URI + ":" + localName;

            if (URI != null && localName != null)
            {
                if (URI.equalsIgnoreCase(s_error_response_uri))
                {
                    if (localName.equalsIgnoreCase(RESPONSE_LOCAL_NAME))
                    {
                        isResponseParent = true;
                    }
                    else if (
                        localName.equalsIgnoreCase(
                            SERVICE_PROVIDER_ENTITY_LOCAL_NAME))
                    {
                        isServiceProviderEntityParent = true;
                        initTemporaryServiceProviderEntity();
                    }

                }
                else if (URI.equalsIgnoreCase(s_soap_11_env_uri))
                {
                    if (!localName
                        .equalsIgnoreCase(SOAP_11_FAULT_LOCAL_NAME))
                    {
                        throw new StopParsingException("Response is not a Fault, stopping parsing.");
                    }
                    else
                    {
                        // ensure the fault exception is initialized,
                        // create the exception now so it will be thrown
                        initAtlasSoapFaultException();
                    }
                }
            }
        }

        /**
         * @see org.xml.sax.ContentHandler#endElement(String, String, String)
         */
        /**
         * @see org.xml.sax.helpers.DefaultHandler#endElement(String, String, String)
         */
        public void endElement(String URI, String localName, String qname)
            throws SAXException
        {
            super.endElement(URI, localName, qname);

            // clear element to indicate end of element            
            currentElement = null;

            if (URI != null && localName != null)
            {
                if (URI.equalsIgnoreCase(s_error_response_uri))
                {
                    if (localName.equalsIgnoreCase(RESPONSE_LOCAL_NAME))
                    {
                        isResponseParent = false;
                    }
                    else if (
                        localName.equalsIgnoreCase(
                            SERVICE_PROVIDER_ENTITY_LOCAL_NAME))
                    {
                        isServiceProviderEntityParent = false;
                        initAtlasSoapFaultException()
                            .addServiceProviderEntity(
                            getTmpServiceProviderEntity());
                            
                        // dereference the service provider entity
                        // in case there's more than one to be handled
                        setTmpServiceProviderEntity(null);
                    }
                }
            }
        }

        /**
         * @see org.xml.sax.ContentHandler#characters(char[], int, int)
         */
        /**
         * @see org.xml.sax.helpers.DefaultHandler#characters(char[], int, int)
         */
        public void characters(char[] arg0, int arg1, int arg2)
            throws SAXException
        {
            super.characters(arg0, arg1, arg2);

            if (isResponseParent && currentElement != null)
            {
                if (currentElement
                    .equalsIgnoreCase(s_cingular_data_model_uri + ":code"))
                {
                    initAtlasSoapFaultException()
                        .initFaultResponse()
                        .initCodeBuffer()
                        .append(
                        arg0,
                        arg1,
                        arg2);
                }

                if (currentElement
                    .equalsIgnoreCase(
                        s_cingular_data_model_uri + ":description"))
                {
                    initAtlasSoapFaultException()
                        .initFaultResponse()
                        .initDescriptionBuffer()
                        .append(arg0, arg1, arg2);
                }
            }

            if (isServiceProviderEntityParent && currentElement != null)
            {
                if (currentElement
                    .equalsIgnoreCase(
                        s_error_response_uri + ":reportingServiceEntity"))
                {
                    initTemporaryServiceProviderEntity()
                        .initReportingServiceEntityBuffer()
                        .append(
                        arg0,
                        arg1,
                        arg2);
                }

                if (currentElement
                    .equalsIgnoreCase(s_error_response_uri + ":faultDate"))
                {
                    initTemporaryServiceProviderEntity()
                        .initFaultDateBuffer()
                        .append(
                        arg0,
                        arg1,
                        arg2);
                }

                if (currentElement
                    .equalsIgnoreCase(
                        s_error_response_uri + ":faultSequenceNumber"))
                {
                    initTemporaryServiceProviderEntity()
                        .initFaultSequenceNumberBuffer()
                        .append(
                        arg0,
                        arg1,
                        arg2);
                }

                if (currentElement
                    .equalsIgnoreCase(s_error_response_uri + ":faultLevel"))
                {
                    initTemporaryServiceProviderEntity()
                        .initFaultLevelBuffer()
                        .append(
                        arg0,
                        arg1,
                        arg2);
                }

                if (currentElement
                    .equalsIgnoreCase(s_error_response_uri + ":faultCode"))
                {
                    initTemporaryServiceProviderEntity()
                        .initFaultCodeBuffer()
                        .append(
                        arg0,
                        arg1,
                        arg2);
                }

                if (currentElement
                    .equalsIgnoreCase(
                        s_error_response_uri + ":faultDescription"))
                {
                    initTemporaryServiceProviderEntity()
                        .initFaultDescriptionBuffer()
                        .append(
                        arg0,
                        arg1,
                        arg2);
                }
            }
        }
        
        private AtlasSoapFaultException
            .ServiceProviderEntity initTemporaryServiceProviderEntity()
        {
            if(getTmpServiceProviderEntity() == null )
            {
                setTmpServiceProviderEntity(
                    new AtlasSoapFaultException.ServiceProviderEntity());
            }
            return getTmpServiceProviderEntity();
        }


        /**
         * Returns the AtlasSoapFaultException.
         * @return AtlasSoapAtlasSoapFaultExceptionect
         */
        public AtlasSoapFaultException getAtlasSoapFaultException()
        {
            return aAtlasSoapFaultException;
        }

        /**
         * Returns the tmpServiceProviderEntity.
         * @return AtlasSoapFaultException.ServiceProviderEntity
         */
        public AtlasSoapFaultException
            .ServiceProviderEntity getTmpServiceProviderEntity()
        {
            return tmpServiceProviderEntity;
        }

        /**
         * Sets the tmpServiceProviderEntity.
         * @param tmpServiceProviderEntity The tmpServiceProviderEntity to set
         */
        public void setTmpServiceProviderEntity(
            AtlasSoapFaultException
                .ServiceProviderEntity tmpServiceProviderEntity)
        {
            this.tmpServiceProviderEntity = tmpServiceProviderEntity;
        }

    }
    /**
     * @see com.sbc.eia.bis.embus.service.access.DefaultJAXBEncoderDecoder#DefaultJAXBEncoderDecoder(String, Properties)
     */
    public AtlasEncoderDecoder(
        String packageName,
        Properties marshalUnmarshalOptions,
        Logger i_logger)
    {
        this(packageName, marshalUnmarshalOptions);
        m_logger = i_logger;
    }

    /**
     * @see com.sbc.eia.bis.embus.service.access.DefaultJAXBEncoderDecoder#DefaultJAXBEncoderDecoder(String, Properties)
     */
    public AtlasEncoderDecoder(
        String packageName,
        Properties marshalUnmarshalOptions)
    {
        super(packageName, marshalUnmarshalOptions);
       
        if (s_cingular_data_model_uri == null
            || s_error_response_uri == null
            || s_soap_11_env_uri == null)
        {
            /* set version specific Namespace URIs
             * The search sequence is
             * 1. System Properties
             * 2. Options passed in
             * 3. Properties file
             * 4. Default
             * 
             * Each properties object is searched for,
             * full URI names first, then for a version number.
             */
             
            // check System properties
            if( !setNamespaceUris(System.getProperties(), false) )
            {
                log("Unable to set Namespace URIs via System properties, will try via options.");
                // then check options
                if( !setNamespaceUris(marshalUnmarshalOptions, false) )
                {
                    String propertiesFileLocation = ResponseInfo.class.getPackage().getName().replace('.', '/' ) + "/" + NAME_SPACE_VERSION_FILE_NAME ;
                    log("Unable to set Namespace URIs via options, will now try via " + propertiesFileLocation + " file.");
                    try {
                        // check Properties file
                        if (!setNamespaceUris(PropertiesFileLoader
                            .read(propertiesFileLocation, null),
                            true))
                        {
                            log("Unable to set Namespace URIs via " + propertiesFileLocation + ", will now try defaulting.");
                            // try defaulting without a properties file
                            if( !setNamespaceUris(null, true) )
                            {
                                log("WARNING: Failed to load Namespace URIs in " + this.getClass().getName() + ".");
                            }
                            else
                            {
                                log("Namespace URIs set via defaults.");
                            }
                        }
                        else
                        {
                            log("Namespace URIs set via " + propertiesFileLocation + ".");
                        }
                    }
                    catch (Exception e)
                    {
                        // in case properties not there or anything wrong happens, try to default
                        log("Exception encounted trying to set namespace uri from " + propertiesFileLocation + ". " + e.getMessage());
                        log("Will try using defaults.");
                        if( !setNamespaceUris(null, true) )
                        {
                            log("WARNING: Failed to load Namespace URIs in " + this.getClass().getName() + ".");
                        }
                        else
                        {
                            log("Namespace URIs set via defaults.");
                        }
                    }
                }
                else
                {
                    log("Namespace URIs set via options.");
                }
            }
            else
            {
                log("Namespace URIs set via System properties.");
            }
        }
    }
    
    /**
     * Method log.
     * @param logMsg
     */
    private void log( String logMsg ) {
        if(m_logger != null)
        {
            m_logger.log(LogEventId.DEBUG_LEVEL_2, logMsg);
        }
        else
        {
            System.out.println(logMsg);
        }
    }
    
    /**
     * Method setNamespaceUris sets the version specific URIs necessary to
     * properly decode the SOAP faults from Cingular Atlas.
     * 
     * This method searches the passed in properties file for the following keys
     * first, these are keys to the actual URI values
     *      com.cingular.csi.datamodel.namespace.uri.
     *      com.cingular.csi.errorresponse.namespace.uri
     *      com.cingular.csi.soapfault.namespace.uri
     *      com.cingular.csi.soapenvelope.namespace.uri
     * If they all exist, then they're used as the acutal URIs.
     * If ANY of the above do not exist, then the method searchs for the
     * version number to use
     *      com.cingular.csi.namespace.version
     * If the version number key-value pair is found, then the value is inserted
     * into the "version number" portion of the default URIs to use.
     * 
     * If the version number key-value pair does not exist, and the
     * shouldDefaultIfUnsuccessful parameter is true, then defaults are used.
     * 
     * @param properties the object containing the key-value pairs for the URI
     * value
     * @param shouldDefaultIfUnsuccessful determines whether this method should
     * default the URI values in the event it's unable to retrieve the values
     * from the passed in properties object.
     * @return boolean
     */
    private boolean setNamespaceUris( Properties properties, boolean shouldDefaultIfUnsuccessful ) {

        String dataModelUri = null;
        String errorResponseUri = null;
        
        String soapEnvelopeUri = null;
        
        String namespaceVersion = null;
        
        boolean isAllSet = false;
        boolean shouldLogNameSpace = false;
        
        // It's all or nothing, if any of the URIs not set, then try to set them
        if (s_cingular_data_model_uri == null
            || s_error_response_uri == null
            || s_soap_11_env_uri == null)
        {
            shouldLogNameSpace = true;    
            if(properties != null)
            {
                dataModelUri = properties.getProperty(DATA_MODEL_NS_URI_KEY);
                errorResponseUri = properties.getProperty(ERROR_RESPONSE_NS_URI_KEY);
                soapEnvelopeUri = properties.getProperty(SOAP_ENV_NS_URI_KEY);
                
                namespaceVersion = properties.getProperty(NAMESPACE_VERSION_KEY);
                
                // first, check if ALL URIs are provided
                if (dataModelUri == null
                    || errorResponseUri == null
                    || soapEnvelopeUri == null)
                {
                    // otherwise, check for version number
                    if (namespaceVersion != null )
                    {
                        namespaceVersion = namespaceVersion.trim();
                        dataModelUri = CNG_MODEL_PRE + namespaceVersion + (namespaceVersion.length() > 0? "/" : "") + CNG_MODEL_POST;
                        errorResponseUri = ERR_RESPONSE_PRE + namespaceVersion + (namespaceVersion.length() > 0? "/" : "") + ERR_RESPONSE_POST;
                        soapEnvelopeUri = DEFAULT_SOAP_ENV_URI;
                        
                        isAllSet = true;
                    }
                }
                else
                {
                    isAllSet = true;
                }

            }

            // if any values retrieved are null and should default, then do so
            if ((dataModelUri == null
                || errorResponseUri == null
                || soapEnvelopeUri == null)
                && shouldDefaultIfUnsuccessful)
            {
                // default
                dataModelUri = CNG_MODEL_PRE + CNG_MODEL_POST;
                errorResponseUri = ERR_RESPONSE_PRE + ERR_RESPONSE_POST;
                soapEnvelopeUri = DEFAULT_SOAP_ENV_URI;
        
                isAllSet = true;
            }
            
            // assign
            s_cingular_data_model_uri = dataModelUri;
            s_error_response_uri = errorResponseUri;
            s_soap_11_env_uri = soapEnvelopeUri;

        }
        else
        {
            // all properties are set
            isAllSet = true;
        }
        
        if(isAllSet && shouldLogNameSpace)
        {
            log("cingular data model namespace uri: "+ s_cingular_data_model_uri);
            log("error response namespace uri: " + s_error_response_uri);
            log("soap 1.1 envelope namespace uri: " + s_soap_11_env_uri);
        }

        return isAllSet;
    }

    /**
     * @see decode(String)
     * Used to decode an xml message into objects.
     */

    public Object[] decode(String message)
        throws DecoderException, ServiceException
    {

        if (message != null)
        {
            // parse the String for the exception code,
            AtlasEncoderDecoder.AtlasExceptionSAXHandler handler =
                new AtlasEncoderDecoder.AtlasExceptionSAXHandler();

            try
            {
                if (parser == null)
                {
                    parser =
                        XMLReaderFactory.createXMLReader(
                            "org.apache.xerces.parsers.SAXParser");
                }

                parser.setContentHandler(handler);
                parser.parse(new InputSource(new StringReader(message)));

                if (handler.getAtlasSoapFaultException() != null)
                {
                    throw handler.getAtlasSoapFaultException();
                }

            }
            catch (
                AtlasEncoderDecoder
                    .AtlasExceptionSAXHandler
                    .StopParsingException e)
            {
                // do nothing, its an error
            }
            catch (SAXException e)
            {
                throw new DecoderException(e);
            }
            catch (IOException e)
            {
                throw new DecoderException(e);
            }
        }

        return super.decode(message);

    }

    /**
     * @see encode(Object[])
     * Used to encode a number of objects into xml.
     */

    public String encode(Object[] request)
        throws ServiceException, EncoderException
    {

        return (String) super.encode(request);

    }

    public static void main(String[] args)
    {
        AtlasEncoderDecoder decoder =
            new AtlasEncoderDecoder(
                ResponseInfo.class.getPackage().getName(),
                null);

        //            This test case is from the SF-Data.xml error response example.
        //            It will not decode properly since the sample is what the RC intercepts and not what the helper
        //            will receive.
        //            decoder.decode("<?xml version=\"1.0\" encoding=\"UTF-8\"?><SOAP-ENV:Envelope SOAP-ENV:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\" xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\"><SOAP-ENV:Header><n:MessageHeader xmlns:n=\"http://csi.cingular.com/CSI/Namespaces/Types/Public/MessageHeader.xsd\" xmlns:cng=\"http://csi.cingular.com/CSI/Namespaces/Types/Public/CingularDataModel.xsd\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://csi.cingular.com/CSI/Namespaces/Types/Public/MessageHeader.xsd\"><n:TrackingMessageHeader><cng:messageId>321ug41i2d1q3223irglq3s4hc72634</cng:messageId><cng:originatorId>sbcwow</cng:originatorId><cng:responseTo>ResponseToSoapClient</cng:responseTo><cng:conversationId>asd7f9sad6gf9asd6gf9asdgf6asdgf6</cng:conversationId><cng:dateTimeStamp>2004-09-15T22:33:12Z</cng:dateTimeStamp></n:TrackingMessageHeader><n:SecurityMessageHeader><cng:userName>sbcwow</cng:userName><cng:userPassword>sbc1010w</cng:userPassword></n:SecurityMessageHeader><n:SequenceMessageHeader><cng:sequenceNumber>1</cng:sequenceNumber><cng:totalInSequence>1</cng:totalInSequence></n:SequenceMessageHeader></n:MessageHeader></SOAP-ENV:Header><SOAP-ENV:Body><soapenv:Fault xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\">    <faultcode xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\">soapenv:Server</faultcode>    <faultstring>Undefined</faultstring>        <detail><soap:Fault xmlns:ns1=\"http://csi.cingular.com/CSI/Namespaces/Types/Public/SoapFault.xsd\" xmlns:cer=\"http://csi.cingular.com/CSI/Namespaces/Types/Public/ErrorResponse.xsd\" xmlns:cng=\"http://csi.cingular.com/CSI/Namespaces/Types/Public/CingularDataModel.xsd\">        <soap:Code>        <soap:Value>soap:Client</soap:Value>        </soap:Code>        <soap:Reason>        <soap:Text>Unknown Error</soap:Text>        </soap:Reason><soap:Role>Client</soap:Role>        <soap:Detail>        <cer:Response>            <cng:code>300</cng:code>            <cng:description>Data Error</cng:description>          </cer:Response>          <cer:ServiceProviderEntity>            <cer:reportingServiceEntity>CAM</cer:reportingServiceEntity>            <cer:faultDate>2004-09-15T22:33:12Z</cer:faultDate>            <cer:faultSequenceNumber>1</cer:faultSequenceNumber>            <cer:faultLevel>ERROR</cer:faultLevel>            <cer:faultCode>2010010002</cer:faultCode>            <cer:faultDescription>billingAccountNumber Is Invalid</cer:faultDescription>          </cer:ServiceProviderEntity>          <cer:ServiceProviderEntity>            <cer:reportingServiceEntity>CAM</cer:reportingServiceEntity>            <cer:faultDate>2004-09-15T22:33:12Z</cer:faultDate>            <cer:faultSequenceNumber>1</cer:faultSequenceNumber>            <cer:faultLevel>ERROR</cer:faultLevel>            <cer:faultCode>2010010005</cer:faultCode>            <cer:faultDescription>accountType Is Missing</cer:faultDescription>          </cer:ServiceProviderEntity>          <cer:ServiceProviderEntity>            <cer:reportingServiceEntity>CAM</cer:reportingServiceEntity>            <cer:faultDate>2004-09-15T22:33:12Z</cer:faultDate>            <cer:faultSequenceNumber>1</cer:faultSequenceNumber>            <cer:faultLevel>ERROR</cer:faultLevel>            <cer:faultCode>2010010006</cer:faultCode>            <cer:faultDescription>requestedQuantity Exceeds Allowable Quantity</cer:faultDescription>          </cer:ServiceProviderEntity>        </soap:Detail>      </soap:Fault>    </detail>    </soapenv:Fault></SOAP-ENV:Body></SOAP-ENV:Envelope>");

        /**************************************/
        /* old test cases based on 8.4 schema */
        /**************************************/
        try
        {
            System.out.println("Example: Decoding the following Fault.");
            System.out.println(
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?><SOAP-ENV:Fault xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\" xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><faultcode>SOAP-ENV:Server</faultcode><faultstring><![CDATA[Undefined]]></faultstring><detail><soap:Fault xmlns:cer=\"http://csi.cingular.com/CSI/Namespaces/Types/Public/ErrorResponse.xsd\" xmlns:cng=\"http://csi.cingular.com/CSI/Namespaces/Types/Public/CingularDataModel.xsd\" xmlns:ns1=\"http://csi.cingular.com/CSI/Namespaces/Types/Public/SoapFault.xsd\"><soap:Code><soap:Value>soap:Client</soap:Value></soap:Code><soap:Reason><soap:Text>Request for Available Quantity Is Missing</soap:Text></soap:Reason><soap:Role>Client</soap:Role><soap:Detail><cer:Response><cng:code>300</cng:code><cng:description>Data Error</cng:description></cer:Response><cer:ServiceProviderEntity><cer:reportingServiceEntity>CSI</cer:reportingServiceEntity><cer:faultDate>2005-01-13Z</cer:faultDate><cer:faultSequenceNumber>1</cer:faultSequenceNumber><cer:faultLevel>ERROR</cer:faultLevel><cer:faultCode>30000249005</cer:faultCode><cer:faultDescription>Request for Available Quantity Is Missing</cer:faultDescription></cer:ServiceProviderEntity></soap:Detail></soap:Fault></detail></SOAP-ENV:Fault>");
            decoder.decode(
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?><SOAP-ENV:Fault xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\" xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><faultcode>SOAP-ENV:Server</faultcode><faultstring><![CDATA[Undefined]]></faultstring><detail><soap:Fault xmlns:cer=\"http://csi.cingular.com/CSI/Namespaces/Types/Public/ErrorResponse.xsd\" xmlns:cng=\"http://csi.cingular.com/CSI/Namespaces/Types/Public/CingularDataModel.xsd\" xmlns:ns1=\"http://csi.cingular.com/CSI/Namespaces/Types/Public/SoapFault.xsd\"><soap:Code><soap:Value>soap:Client</soap:Value></soap:Code><soap:Reason><soap:Text>Request for Available Quantity Is Missing</soap:Text></soap:Reason><soap:Role>Client</soap:Role><soap:Detail><cer:Response><cng:code>300</cng:code><cng:description>Data Error</cng:description></cer:Response><cer:ServiceProviderEntity><cer:reportingServiceEntity>CSI</cer:reportingServiceEntity><cer:faultDate>2005-01-13Z</cer:faultDate><cer:faultSequenceNumber>1</cer:faultSequenceNumber><cer:faultLevel>ERROR</cer:faultLevel><cer:faultCode>30000249005</cer:faultCode><cer:faultDescription>Request for Available Quantity Is Missing</cer:faultDescription></cer:ServiceProviderEntity></soap:Detail></soap:Fault></detail></SOAP-ENV:Fault>");

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        try
        {
            System.out.println("Example: Decoding the fault response.");
            System.out.println(
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?><SOAP-ENV:Fault xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\" xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><faultcode>SOAP-ENV:Server</faultcode><faultstring><![CDATA[Undefined]]></faultstring><detail><soap:Fault xmlns:cer=\"http://csi.cingular.com/CSI/Namespaces/Types/Public/ErrorResponse.xsd\" xmlns:cng=\"http://csi.cingular.com/CSI/Namespaces/Types/Public/CingularDataModel.xsd\" xmlns:ns1=\"http://csi.cingular.com/CSI/Namespaces/Types/Public/SoapFault.xsd\"><soap:Code><soap:Value>soap:Client</soap:Value></soap:Code><soap:Reason><soap:Text>CAS Region Is Invalid</soap:Text></soap:Reason><soap:Role>Client</soap:Role><soap:Detail><cer:Response><cng:code>300</cng:code><cng:description>Data Error</cng:description></cer:Response><cer:ServiceProviderEntity><cer:reportingServiceEntity>CSI</cer:reportingServiceEntity><cer:faultDate>2005-01-13Z</cer:faultDate><cer:faultSequenceNumber>1</cer:faultSequenceNumber><cer:faultLevel>ERROR</cer:faultLevel><cer:faultCode>30000128002</cer:faultCode><cer:faultDescription>CAS Region Is Invalid</cer:faultDescription></cer:ServiceProviderEntity></soap:Detail></soap:Fault></detail></SOAP-ENV:Fault>");
            decoder.decode(
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?><SOAP-ENV:Fault xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\" xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><faultcode>SOAP-ENV:Server</faultcode><faultstring><![CDATA[Undefined]]></faultstring><detail><soap:Fault xmlns:cer=\"http://csi.cingular.com/CSI/Namespaces/Types/Public/ErrorResponse.xsd\" xmlns:cng=\"http://csi.cingular.com/CSI/Namespaces/Types/Public/CingularDataModel.xsd\" xmlns:ns1=\"http://csi.cingular.com/CSI/Namespaces/Types/Public/SoapFault.xsd\"><soap:Code><soap:Value>soap:Client</soap:Value></soap:Code><soap:Reason><soap:Text>CAS Region Is Invalid</soap:Text></soap:Reason><soap:Role>Client</soap:Role><soap:Detail><cer:Response><cng:code>300</cng:code><cng:description>Data Error</cng:description></cer:Response><cer:ServiceProviderEntity><cer:reportingServiceEntity>CSI</cer:reportingServiceEntity><cer:faultDate>2005-01-13Z</cer:faultDate><cer:faultSequenceNumber>1</cer:faultSequenceNumber><cer:faultLevel>ERROR</cer:faultLevel><cer:faultCode>30000128002</cer:faultCode><cer:faultDescription>CAS Region Is Invalid</cer:faultDescription></cer:ServiceProviderEntity></soap:Detail></soap:Fault></detail></SOAP-ENV:Fault>");
        }
        catch (AtlasSoapFaultException e)
        {
            e.printStackTrace();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        try
        {
            System.out.println(
                "Example: Decoding the fault response with ].");
            System.out.println(
                "<SOAP-ENV:Fault xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\" xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><faultcode>SOAP-ENV:Server</faultcode><faultstring><![CDATA[Undefined]]></faultstring><detail><soap:Fault xmlns:cer=\"http://csi.cingular.com/CSI/Namespaces/Types/Public/ErrorResponse.xsd\" xmlns:cng=\"http://csi.cingular.com/CSI/Namespaces/Types/Public/CingularDataModel.xsd\" xmlns:ns1=\"http://csi.cingular.com/CSI/Namespaces/Types/Public/SoapFault.xsd\"><soap:Code><soap:Value>soap:Client</soap:Value></soap:Code><soap:Reason><soap:Text>Unknown Error</soap:Text></soap:Reason><soap:Role>Client</soap:Role><soap:Detail><cer:Response><cng:code>900</cng:code><cng:description>Unknown Error</cng:description></cer:Response><cer:ServiceProviderEntity><cer:reportingServiceEntity>CAM</cer:reportingServiceEntity><cer:faultDate>2005-01-24Z</cer:faultDate><cer:faultSequenceNumber>1</cer:faultSequenceNumber><cer:faultLevel>WARNING</cer:faultLevel><cer:faultCode>90000000001</cer:faultCode><cer:faultDescription>Error while resetting the telegence ticket pool for originator SBCWOW due to Unable to login to the Telegence API server. The server returned the following error: [Login failed. Please contact support team.].</cer:faultDescription></cer:ServiceProviderEntity></soap:Detail></soap:Fault></detail></SOAP-ENV:Fault>");
            decoder.decode(
                "<SOAP-ENV:Fault xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\" xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><faultcode>SOAP-ENV:Server</faultcode><faultstring><![CDATA[Undefined]]></faultstring><detail><soap:Fault xmlns:cer=\"http://csi.cingular.com/CSI/Namespaces/Types/Public/ErrorResponse.xsd\" xmlns:cng=\"http://csi.cingular.com/CSI/Namespaces/Types/Public/CingularDataModel.xsd\" xmlns:ns1=\"http://csi.cingular.com/CSI/Namespaces/Types/Public/SoapFault.xsd\"><soap:Code><soap:Value>soap:Client</soap:Value></soap:Code><soap:Reason><soap:Text>Unknown Error</soap:Text></soap:Reason><soap:Role>Client</soap:Role><soap:Detail><cer:Response><cng:code>900</cng:code><cng:description>Unknown Error</cng:description></cer:Response><cer:ServiceProviderEntity><cer:reportingServiceEntity>CAM</cer:reportingServiceEntity><cer:faultDate>2005-01-24Z</cer:faultDate><cer:faultSequenceNumber>1</cer:faultSequenceNumber><cer:faultLevel>WARNING</cer:faultLevel><cer:faultCode>90000000001</cer:faultCode><cer:faultDescription>Error while resetting the telegence ticket pool for originator SBCWOW due to Unable to login to the Telegence API server. The server returned the following error: [Login failed. Please contact support team.].</cer:faultDescription></cer:ServiceProviderEntity></soap:Detail></soap:Fault></detail></SOAP-ENV:Fault>");
        }
        catch (AtlasSoapFaultException e)
        {
            e.printStackTrace();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        try
        {
            System.out.println(
                "\nExample: Decoding the following Fault without Response and ServiceProviderEntity.");
            System.out.println(
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?><SOAP-ENV:Fault xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\" xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><faultcode>SOAP-ENV:Server</faultcode><faultstring><![CDATA[Undefined]]></faultstring><detail><soap:Fault><soap:Code><soap:Value>900</soap:Value></soap:Code><soap:Reason><soap:Text>Unknown Error</soap:Text></soap:Reason><soap:Detail /></soap:Fault></detail></SOAP-ENV:Fault>");
            decoder.decode(
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?><SOAP-ENV:Fault xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\" xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><faultcode>SOAP-ENV:Server</faultcode><faultstring><![CDATA[Undefined]]></faultstring><detail><soap:Fault><soap:Code><soap:Value>900</soap:Value></soap:Code><soap:Reason><soap:Text>Unknown Error</soap:Text></soap:Reason><soap:Detail /></soap:Fault></detail></SOAP-ENV:Fault>");

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        try
        {
            System.out.println(
                "\nExample: Multiple ServiceEntityProviders.");
            System.out.println(
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?><SOAP-ENV:Fault xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\" xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"> <faultcode>SOAP-ENV:Server</faultcode>  <faultstring>       <![CDATA[Undefined]]>   </faultstring>  <detail>        <soap:Fault xmlns:cer=\"http://csi.cingular.com/CSI/Namespaces/Types/Public/ErrorResponse.xsd\" xmlns:cng=\"http://csi.cingular.com/CSI/Namespaces/Types/Public/CingularDataModel.xsd\" xmlns:ns1=\"http://csi.cingular.com/CSI/Namespaces/Types/Public/SoapFault.xsd\">          <soap:Code>             <soap:Value>soap:Client</soap:Value>            </soap:Code>            <soap:Reason>               <soap:Text>Request for Available Quantity Is Missing</soap:Text>            </soap:Reason>          <soap:Role>Client</soap:Role>           <soap:Detail>               <cer:Response>                  <cng:code>300</cng:code>                    <cng:description>Data Error</cng:description>               </cer:Response>             <cer:ServiceProviderEntity>                 <cer:reportingServiceEntity>CSI</cer:reportingServiceEntity>                    <cer:faultDate>2005-01-13Z</cer:faultDate>                  <cer:faultSequenceNumber>1</cer:faultSequenceNumber>                    <cer:faultLevel>ERROR</cer:faultLevel>                  <cer:faultCode>30000249005</cer:faultCode>                  <cer:faultDescription>Request for Available Quantity Is Missing</cer:faultDescription>              </cer:ServiceProviderEntity>                <cer:ServiceProviderEntity>                 <cer:reportingServiceEntity>CSI</cer:reportingServiceEntity>                    <cer:faultDate>2005-01-13Z</cer:faultDate>                  <cer:faultSequenceNumber>1</cer:faultSequenceNumber>                    <cer:faultLevel>ERROR</cer:faultLevel>                  <cer:faultCode>30000249005</cer:faultCode>                  <cer:faultDescription>Request for Available Quantity Is Missing</cer:faultDescription>              </cer:ServiceProviderEntity>            </soap:Detail>      </soap:Fault>   </detail></SOAP-ENV:Fault>"
            );
            
            decoder.decode(
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?><SOAP-ENV:Fault xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\" xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"> <faultcode>SOAP-ENV:Server</faultcode>  <faultstring>       <![CDATA[Undefined]]>   </faultstring>  <detail>        <soap:Fault xmlns:cer=\"http://csi.cingular.com/CSI/Namespaces/Types/Public/ErrorResponse.xsd\" xmlns:cng=\"http://csi.cingular.com/CSI/Namespaces/Types/Public/CingularDataModel.xsd\" xmlns:ns1=\"http://csi.cingular.com/CSI/Namespaces/Types/Public/SoapFault.xsd\">          <soap:Code>             <soap:Value>soap:Client</soap:Value>            </soap:Code>            <soap:Reason>               <soap:Text>Request for Available Quantity Is Missing</soap:Text>            </soap:Reason>          <soap:Role>Client</soap:Role>           <soap:Detail>               <cer:Response>                  <cng:code>300</cng:code>                    <cng:description>Data Error</cng:description>               </cer:Response>             <cer:ServiceProviderEntity>                 <cer:reportingServiceEntity>CSI</cer:reportingServiceEntity>                    <cer:faultDate>2005-01-13Z</cer:faultDate>                  <cer:faultSequenceNumber>1</cer:faultSequenceNumber>                    <cer:faultLevel>ERROR</cer:faultLevel>                  <cer:faultCode>30000249005</cer:faultCode>                  <cer:faultDescription>Request for Available Quantity Is Missing</cer:faultDescription>              </cer:ServiceProviderEntity>                <cer:ServiceProviderEntity>                 <cer:reportingServiceEntity>CSI</cer:reportingServiceEntity>                    <cer:faultDate>2005-01-13Z</cer:faultDate>                  <cer:faultSequenceNumber>1</cer:faultSequenceNumber>                    <cer:faultLevel>ERROR</cer:faultLevel>                  <cer:faultCode>30000249005</cer:faultCode>                  <cer:faultDescription>Request for Available Quantity Is Missing</cer:faultDescription>              </cer:ServiceProviderEntity>            </soap:Detail>      </soap:Fault>   </detail></SOAP-ENV:Fault>"
            );
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        /**************************************/
        /*   Test cases based on 8.5 schema   */
        /**************************************/
        try
        {
            System.out.println(
                "\nExample: Multiple ServiceEntityProviders using 8.5 schema.");
            System.out.println(
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?><SOAP-ENV:Fault xmlns:xyz=\"http://whatever.com\" xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\" xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"> <faultcode>SOAP-ENV:Server</faultcode>  <faultstring>       <![CDATA[Undefined]]>   </faultstring>  <detail>        <soap:Fault xmlns:cer=\"http://csi.cingular.com/CSI/Namespaces/Types/Public/ErrorResponse.xsd\" xmlns:cng=\"http://csi.cingular.com/CSI/Namespaces/Types/Public/CingularDataModel.xsd\" xmlns:ns1=\"http://csi.cingular.com/CSI/Namespaces/Types/Public/SoapFault.xsd\">          <soap:Code>             <soap:Value>soap:Client</soap:Value>            </soap:Code>            <soap:Reason>               <soap:Text>Request for Available Quantity Is Missing</soap:Text>            </soap:Reason>          <soap:Role>Client</soap:Role>           <soap:Detail><xyz:CSIApplicationException>               <cer:Response>                  <cng:code>300</cng:code>                    <cng:description>Data Error</cng:description>               </cer:Response>             <cer:ServiceProviderEntity>                 <cer:reportingServiceEntity>CSI</cer:reportingServiceEntity>                    <cer:faultDate>2005-01-13Z</cer:faultDate>                  <cer:faultSequenceNumber>1</cer:faultSequenceNumber>                    <cer:faultLevel>ERROR</cer:faultLevel>                  <cer:faultCode>30000249005</cer:faultCode>                  <cer:faultDescription>Request for Available Quantity Is Missing</cer:faultDescription>              </cer:ServiceProviderEntity>                <cer:ServiceProviderEntity>                 <cer:reportingServiceEntity>CSI</cer:reportingServiceEntity>                    <cer:faultDate>2005-01-13Z</cer:faultDate>                  <cer:faultSequenceNumber>1</cer:faultSequenceNumber>                    <cer:faultLevel>ERROR</cer:faultLevel>                  <cer:faultCode>30000249005</cer:faultCode>                  <cer:faultDescription>Request for Available Quantity Is Missing</cer:faultDescription>              </cer:ServiceProviderEntity>            </xyz:CSIApplicationException></soap:Detail>      </soap:Fault>   </detail></SOAP-ENV:Fault>"
            );
            
            decoder.decode(
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?><SOAP-ENV:Fault xmlns:xyz=\"http://whatever.com\" xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\" xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"> <faultcode>SOAP-ENV:Server</faultcode>  <faultstring>       <![CDATA[Undefined]]>   </faultstring>  <detail>        <soap:Fault xmlns:cer=\"http://csi.cingular.com/CSI/Namespaces/Types/Public/ErrorResponse.xsd\" xmlns:cng=\"http://csi.cingular.com/CSI/Namespaces/Types/Public/CingularDataModel.xsd\" xmlns:ns1=\"http://csi.cingular.com/CSI/Namespaces/Types/Public/SoapFault.xsd\">          <soap:Code>             <soap:Value>soap:Client</soap:Value>            </soap:Code>            <soap:Reason>               <soap:Text>Request for Available Quantity Is Missing</soap:Text>            </soap:Reason>          <soap:Role>Client</soap:Role>           <soap:Detail><xyz:CSIApplicationException>               <cer:Response>                  <cng:code>300</cng:code>                    <cng:description>Data Error</cng:description>               </cer:Response>             <cer:ServiceProviderEntity>                 <cer:reportingServiceEntity>CSI</cer:reportingServiceEntity>                    <cer:faultDate>2005-01-13Z</cer:faultDate>                  <cer:faultSequenceNumber>1</cer:faultSequenceNumber>                    <cer:faultLevel>ERROR</cer:faultLevel>                  <cer:faultCode>30000249005</cer:faultCode>                  <cer:faultDescription>Request for Available Quantity Is Missing</cer:faultDescription>              </cer:ServiceProviderEntity>                <cer:ServiceProviderEntity>                 <cer:reportingServiceEntity>CSI</cer:reportingServiceEntity>                    <cer:faultDate>2005-01-13Z</cer:faultDate>                  <cer:faultSequenceNumber>1</cer:faultSequenceNumber>                    <cer:faultLevel>ERROR</cer:faultLevel>                  <cer:faultCode>30000249005</cer:faultCode>                  <cer:faultDescription>Request for Available Quantity Is Missing</cer:faultDescription>              </cer:ServiceProviderEntity>            </xyz:CSIApplicationException></soap:Detail>      </soap:Fault>   </detail></SOAP-ENV:Fault>"
            );
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        try
        {
            System.out.println(
                "Example: Decoding the successful response.");
            System.out.println(
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?><p:InquirePortEligibilityResponse xmlns:cng=\"http://csi.cingular.com/CSI/Namespaces/Types/Public/CingularDataModel.xsd\" xmlns:p=\"http://csi.cingular.com/CSI/Namespaces/Container/Public/InquirePortEligibilityResponse.xsd\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://csi.cingular.com/CSI/Namespaces/Container/Public/InquirePortEligibilityResponse.xsd InquirePortEligibilityResponse.xsd http://csi.cingular.com/CSI/Namespaces/Types/Public/CingularDataModel.xsd ../../Types/Public/CingularDataModel.xsd \">  <p:PortEligibilityResponse>    <cng:eligibilityFlag>true</cng:eligibilityFlag>    <cng:reasonCode>cng:reasonCode</cng:reasonCode>    <cng:reasonDescription>cng:reasonDescription</cng:reasonDescription>    <cng:OldServiceProvider>      <cng:localId>cng:localId</cng:localId>      <cng:networkId>cng:networkId</cng:networkId>      <cng:resellerName>cng:resellerName</cng:resellerName>    </cng:OldServiceProvider>    <cng:NewServiceProvider>      <cng:localId>cng:localId</cng:localId>      <cng:networkId>cng:networkId</cng:networkId>      <cng:resellerName>cng:resellerName</cng:resellerName>    </cng:NewServiceProvider>    <cng:subscriberNumber>cng:subscriberNumber</cng:subscriberNumber>    <cng:equipmentType>C</cng:equipmentType>    <cng:serviceArea>cng:serviceArea</cng:serviceArea>  </p:PortEligibilityResponse>  <p:Response>    <cng:code>cng:code</cng:code>    <cng:description>cng:description</cng:description>  </p:Response></p:InquirePortEligibilityResponse>"
            );
            System.out.println(
                decoder.decode(
                      "<?xml version=\"1.0\" encoding=\"UTF-8\"?><p:InquirePortEligibilityResponse xmlns:cng=\"http://csi.cingular.com/CSI/Namespaces/Types/Public/CingularDataModel.xsd\" xmlns:p=\"http://csi.cingular.com/CSI/Namespaces/Container/Public/InquirePortEligibilityResponse.xsd\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://csi.cingular.com/CSI/Namespaces/Container/Public/InquirePortEligibilityResponse.xsd InquirePortEligibilityResponse.xsd http://csi.cingular.com/CSI/Namespaces/Types/Public/CingularDataModel.xsd ../../Types/Public/CingularDataModel.xsd \">  <p:PortEligibilityResponse>    <cng:eligibilityFlag>true</cng:eligibilityFlag>    <cng:reasonCode>cng:reasonCode</cng:reasonCode>    <cng:reasonDescription>cng:reasonDescription</cng:reasonDescription>    <cng:OldServiceProvider>      <cng:localId>cng:localId</cng:localId>      <cng:networkId>cng:networkId</cng:networkId>      <cng:resellerName>cng:resellerName</cng:resellerName>    </cng:OldServiceProvider>    <cng:NewServiceProvider>      <cng:localId>cng:localId</cng:localId>      <cng:networkId>cng:networkId</cng:networkId>      <cng:resellerName>cng:resellerName</cng:resellerName>    </cng:NewServiceProvider>    <cng:subscriberNumber>cng:subscriberNumber</cng:subscriberNumber>    <cng:equipmentType>C</cng:equipmentType>    <cng:serviceArea>cng:serviceArea</cng:serviceArea>  </p:PortEligibilityResponse>  <p:Response>    <cng:code>cng:code</cng:code>    <cng:description>cng:description</cng:description>  </p:Response></p:InquirePortEligibilityResponse>"
                    )[0]
                );
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }                
    }
}
