//$Id: JaxbXmlEncoderDecoder.java,v 1.2 2008/01/18 23:04:30 gg2712 Exp $

package com.sbc.eia.bis.lim.util;

import java.util.Properties;

import com.sbc.eia.bis.embus.service.access.DefaultJAXBEncoderDecoder;
import com.sbc.eia.bis.embus.service.access.EncoderException;
import com.sbc.eia.bis.embus.service.access.DecoderException;
import com.sbc.eia.bis.embus.service.access.ServiceException;
import com.sbc.eia.bis.lim.util.LIMBase;

/*
 *  JaxbXmlEncoderDecoder is a JAXB encoder decoder.  It relies on the
 *  default behavior of the DefaultJAXBEncoderDecoder to marshal and
 *  unmarshal XML documents.  It provides the functions to populate
 *  the JAXB objects for marshalling to XML.
 * 
 */
public class JaxbXmlEncoderDecoder extends DefaultJAXBEncoderDecoder
{
    protected LIMBase m_base = null;
    
    public JaxbXmlEncoderDecoder(
            LIMBase base,
            String i_packageName,
            Properties i_marshalUnmarshalOptions) 
    {
        super(i_packageName, i_marshalUnmarshalOptions);
        m_base = base;
    }
    
    /**
     * @see encode(Object[])
     * Used to encode a number of objects into xml.
     */

    public String encode(Object[] request)
        throws 
        ServiceException, 
        EncoderException
    {

        return (String) super.encode(request);
    }
    
    /**
     * @see com.sbc.eia.bis.lim.ovalsusps.IDecoder#decode(String)
     */
    public Object[] decode(String message)
        throws 
        DecoderException, 
        ServiceException
    {
        return super.decode(message);
    }
}
