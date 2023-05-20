// $Id: DefaultJAXBEncoderDecoder.java,v 1.4 2004/03/15 19:25:39 as5472 Exp $

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
 *  This file contains the DefaultJAXBEncoderDecoder class and any helpers
 * it may use internally.
 *  Description
 */
package com.sbc.eia.bis.embus.service.access;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.Properties;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.bind.Unmarshaller;

import org.xml.sax.InputSource;

/** Description
 *  DefaultJAXBEncoderDecoder provides convenient methods for marshalling
 *  and unmarshalling java objects to and from XML.
 *  Description
 */
public class DefaultJAXBEncoderDecoder implements IEncoderDecoder
{
    private String m_packageName = null;
    private Properties m_marshalUnmarshalOptions = null;

    /**
     * Constructor for DefaultJAXBEncoderDecoder.
     */
    public DefaultJAXBEncoderDecoder( String i_packageName, Properties i_marshalUnmarshalOptions )
    {
        super();
        m_packageName = i_packageName;
        m_marshalUnmarshalOptions = i_marshalUnmarshalOptions;
        
    }

    /**
     * @see com.sbc.eia.bis.embus.service.access.IDecoder#decode(String)
     */
    public Object[] decode(String i_message)
        throws DecoderException, ServiceException
    {
        Object[] returnObjects = null;
        JAXBContext jc = null;
        try {
            // create a JAXBContext capable of handling classes generated into
            // the generated package
            jc = JAXBContext.newInstance( m_packageName );  // package name
            Unmarshaller u = null;

            // create an Unmarshaller
            u = jc.createUnmarshaller();

            // unmarshal a generated instance document into a tree of Java content
            // objects composed of classes from the generated package.
            returnObjects = new Object[]{u.unmarshal( new InputSource(new StringReader(i_message)))};
            
        } 
        catch( JAXBException je ) {
            throw new DecoderException(je);
        } 
        
        return returnObjects;
    }
    


    /**
     * @see com.sbc.eia.bis.embus.service.access.IEncoder#encode(Object[])
     */
    public String encode(Object[] i_objectArray)
        throws EncoderException, ServiceException
    {
        // create a Marshaller and marshal to a string
        StringWriter temp = new StringWriter();
        JAXBContext jc = null;
        try {
            // create a JAXBContext capable of handling classes generated into
            // the generated package
            jc = JAXBContext.newInstance( m_packageName );  // package name
            Marshaller m = null;
            m = jc.createMarshaller();
            setupMarshaller( m, m_marshalUnmarshalOptions );         
            m.marshal( i_objectArray[0], temp );
        } 
        catch( PropertyException pe ) {
            throw new EncoderException(pe);
        }
        catch( JAXBException je ) {
            throw new EncoderException(je);
        }        
        
        return temp.toString(); 
    }
    
    /**
     * Method setupMarshaller sets up the marshaller with any available
     * marshaller options.
     * @param io_marshaller Marshaller
     * @param i_options Properties Available options include:
     * jaxb.formatted.output, jaxb.encoding, jaxb.noNamespaceSchemaLocation
     * jaxb.schemaLocation
     * @see javax.xml.bind.Marshaller
     * @throws PropertyException
     */
    private void setupMarshaller( Marshaller io_marshaller, Properties i_options ) throws PropertyException{
        
        if ( i_options != null ) {
            if ( i_options.containsKey( Marshaller.JAXB_FORMATTED_OUTPUT ) && i_options.getProperty(Marshaller.JAXB_FORMATTED_OUTPUT) != null )
            {
                if ( i_options.getProperty(Marshaller.JAXB_FORMATTED_OUTPUT).trim().equalsIgnoreCase("true") )
                {
                    io_marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE );
                }
            }
    
            if ( i_options.containsKey( Marshaller.JAXB_ENCODING ) && i_options.getProperty(Marshaller.JAXB_ENCODING ) != null )
            {
                io_marshaller.setProperty(Marshaller.JAXB_ENCODING, i_options.getProperty(Marshaller.JAXB_ENCODING));
            }
            
            if ( i_options.containsKey( Marshaller.JAXB_NO_NAMESPACE_SCHEMA_LOCATION ) && i_options.getProperty( Marshaller.JAXB_NO_NAMESPACE_SCHEMA_LOCATION ) != null )
            {
                io_marshaller.setProperty(Marshaller.JAXB_NO_NAMESPACE_SCHEMA_LOCATION, i_options.getProperty( Marshaller.JAXB_NO_NAMESPACE_SCHEMA_LOCATION ));
            }
            
            if ( i_options.containsKey( Marshaller.JAXB_SCHEMA_LOCATION ) && i_options.getProperty( Marshaller.JAXB_SCHEMA_LOCATION ) != null )
            {
                io_marshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, i_options.getProperty(Marshaller.JAXB_SCHEMA_LOCATION));
            }
        }
    }


    /**
     * Returns the marshalUnmarshalOptions.
     * @return Properties
     */
    private Properties getMarshalUnmarshalOptions()
    {
        return m_marshalUnmarshalOptions;
    }

    /**
     * Returns the packageName for the JAXB classes.
     * @return String
     */
    private String getPackageName()
    {
        return m_packageName;
    }

    /**
     * Sets the marshalUnmarshalOptions.
     * @param marshalUnmarshalOptions The marshalUnmarshalOptions to set.
     * Properties include the following:
     * jaxb.formatted.output, jaxb.encoding, jaxb.noNamespaceSchemaLocation
     * jaxb.schemaLocation
     * @see javax.xml.bind.Marshaller
     */
    private void setMarshalUnmarshalOptions(Properties i_marshalUnmarshalOptions)
    {
        m_marshalUnmarshalOptions = i_marshalUnmarshalOptions;
    }

    /**
     * Sets the packageNamefor the JAXB classes.
     * @param packageName The packageName to set
     */
    private void setPackageName(String i_packageName)
    {
        m_packageName = i_packageName;
    }

}
