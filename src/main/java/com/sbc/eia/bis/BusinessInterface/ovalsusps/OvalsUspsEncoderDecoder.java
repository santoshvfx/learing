// $Id: OvalsUspsEncoderDecoder.java,v 1.5 2006/01/30 22:55:20 jd3462 Exp $

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
 *  This file contains the OvalsUspsEncoderDecoder class.
 *  Description
 */
package com.sbc.eia.bis.BusinessInterface.ovalsusps;

import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.xml.sax.InputSource;

import com.sbc.eia.bis.embus.service.access.DecoderException;
import com.sbc.eia.bis.embus.service.access.DefaultJAXBEncoderDecoder;
import com.sbc.eia.bis.embus.service.access.EncoderException;
import com.sbc.eia.bis.embus.service.access.ServiceException;
import com.sbc.eia.bis.lim.jaxb.ovalsusps.OVALS;
import com.sbc.eia.bis.lim.jaxb.ovalsusps.impl.ADDRESSTypeImpl;
import com.sbc.eia.bis.lim.util.LIMBase;
import com.sbc.eia.bis.lim.util.LIMBisContextManager;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.lim.helpers.AddressHandlerUSPS;

/** Description
 *  OvalsUspsEncoderDecoder is a JAXB encoder decoder.  It relies on the
 *  default behavior of the DefaultJAXBEncoderDecoder to marshal and
 *  unmarshal XML documents.  It provides the functions to populate
 *  the JAXB objects for marshalling to XML.
 *  Description
 */
public class OvalsUspsEncoderDecoder extends DefaultJAXBEncoderDecoder
{
    
    protected AddressHandlerUSPS uspsAddr   = null;
    protected OVALS ovals                   = null;
    protected String adrll              = null;
    protected String xml                    = null;
    protected LIMBase m_base = null;
    protected boolean incudeCSZ = false;
    protected LIMBisContextManager limBisContextManager = null;

    /**
     * Constructor for OvalsUspsEncoderDecoder.
     */
    public OvalsUspsEncoderDecoder(LIMBase base,
        String i_packageName,
        Properties i_marshalUnmarshalOptions)
    {
        super(i_packageName, i_marshalUnmarshalOptions);
        m_base = base;
    }

    /**
     * Sets the adrll.
     * @param adrll The adrll to set
     */
    public void setAdrll( BisContext bisContext ) {
        limBisContextManager = new LIMBisContextManager(bisContext);
        try{
            if (((limBisContextManager.getAddressCassMaximumLengthPerLine()
                != null)
                && (limBisContextManager
                    .getAddressCassMaximumLengthPerLine().trim())
                    .length()> 0)
                && LIMBase.allNumeric(
                    (limBisContextManager
                        .getAddressCassMaximumLengthPerLine().trim())))
            {
                this.adrll =
                    limBisContextManager
                        .getAddressCassMaximumLengthPerLine()
                        .trim();
            }
        }
        catch(Exception e){}
    }


    /**
     * Sets the uspsAddr.
     * @param uspsAddr The uspsAddr to set
     */
    public void setUspsAddr(AddressHandlerUSPS uspsAddr) {
        this.uspsAddr = uspsAddr;
    }

    /**
     * Returns the adrll.
     * @return String
     */
    public String getAdrll() {
        return adrll;
    }


    /**
     * Returns the ovals.
     * @return OVALS
     */
    public OVALS getOvals() throws ServiceException {
        setOvals();
        return ovals;
    }


    /**
     * Returns the uspsAddr.
     * @return AddressHandlerUSPS
     */
    public AddressHandlerUSPS getUspsAddr() {
        return uspsAddr;
    }

    /**
     * Sets the ovals.
     * @param ovals The ovals to set
     */
    public void setOvals() throws ServiceException { 
        try {
            
            // Set CASS address line length if option provided in BisContext
            setAdrll(m_base.getCallerContext());

                  
            // create a JAXBContext capable of handling classes generated
            // into the generated package
            JAXBContext jc =
                JAXBContext.newInstance(OVALSUSPSTag.OVALS_JAXB_PACKAGE);

            // create an Unmarshaller
            Unmarshaller u = jc.createUnmarshaller();

            // unmarshal a generated instance document into a tree of Java
            // content objects composed of classes from the generated
            // package.
            // use tag/class name for XML document e.g. <USPSVALIDATE>
            StringBuffer sb = new StringBuffer();
            sb.append(OVALSUSPSTag.XML_PROLOG).append(
                OVALSUSPSTag.XML_OPEN_REQ);
            sb.append(OVALSUSPSTag.XML_ADDRESS);

            if (getAdrll() != null && getAdrll().length() > 0)
            {
                sb.append(OVALSUSPSTag.XML_ADRLL);
            }
                
            sb.append(OVALSUSPSTag.XML_CLOSING_BRACKET);
            sb.append(OVALSUSPSTag.XML_CLOSE_REQ);
            
            this.ovals = 
                (OVALS) u.unmarshal(
                    new InputSource(new StringReader(sb.toString())));

            ((ADDRESSTypeImpl) ovals.getUSPSVALIDATE().getREQUEST()
                .getADDRESS().get(0)).setLINE1(getUspsAddr().getLine1());
            ((ADDRESSTypeImpl) ovals.getUSPSVALIDATE().getREQUEST()
                .getADDRESS().get(0)).setLINE2(getUspsAddr().getLine2());
            ((ADDRESSTypeImpl) ovals.getUSPSVALIDATE().getREQUEST()
                .getADDRESS().get(0)).setLINE3(getUspsAddr().getLine3());
            ((ADDRESSTypeImpl) ovals.getUSPSVALIDATE().getREQUEST()
                .getADDRESS().get(0)).setLINE4(getUspsAddr().getLine4());
            ((ADDRESSTypeImpl) ovals.getUSPSVALIDATE().getREQUEST()
                .getADDRESS().get(0)).setLINE5(getUspsAddr().getLine5());

            if (getAdrll() !=null && getAdrll().length() > 0)
            {
                ((ADDRESSTypeImpl) ovals.getUSPSVALIDATE().getREQUEST()
                    .getADDRESS().get(0)).setADRLL(getAdrll());                      
            }

            if( isIncudeCSZ() )
            {
                ((ADDRESSTypeImpl) ovals.getUSPSVALIDATE().getREQUEST().getADDRESS().get(0)).setCSZCITY(getUspsAddr().getCity());
                ((ADDRESSTypeImpl) ovals.getUSPSVALIDATE().getREQUEST().getADDRESS().get(0)).setCSZSTATE(getUspsAddr().getState());
                ((ADDRESSTypeImpl) ovals.getUSPSVALIDATE().getREQUEST().getADDRESS().get(0)).setCSZZIP(getUspsAddr().getPostalCode());
            }
            
            if (limBisContextManager.getSagValidationRequest()!= null)
                if (limBisContextManager.getSagValidationRequest().equalsIgnoreCase ("true"))
                    ((ADDRESSTypeImpl) ovals.getUSPSVALIDATE().getREQUEST().getADDRESS().get(0)).setCASSONLY ("True");
            Date today = new java.util.Date();
            SimpleDateFormat formatter =
                new SimpleDateFormat("yyyyMMddHHmmssSSS");
            String dateString = formatter.format( today ) ;
            // get random number between 1000 and 9999
            int randomNbr = 1000 + (int)(9000*Math.random());  

            ((ADDRESSTypeImpl) ovals.getUSPSVALIDATE().getREQUEST()
                .getADDRESS().get(0))
                .setID("LIMBIS" + dateString + randomNbr);
        }
        catch( JAXBException je ) {
            throw new ServiceException(je );
        } 
    }

    /**
     * @see com.sbc.eia.bis.lim.ovalsusps.IDecoder#decode(String)
     */
    public Object[] decode(String message)
        throws DecoderException, ServiceException
    {
        return super.decode(message);
    }

    /**
     * @see com.sbc.eia.bis.lim.ovalsusps.IEncoder#encode(Object[])
     */
    public String encode(Object[] objectArray)
        throws EncoderException, ServiceException
    {
        OvalsUspsRetrieveLocReq req = ((OvalsUspsRetrieveLocReq)objectArray[0]);
        setUspsAddr(req.getUspsAddr());
        setIncudeCSZ(
            !req.locationPropertiesRequested.isRetrieveALL()
                && req
                    .locationPropertiesRequested
                    .isRetrieveCityStatePostalCodeValid());
        return super.encode(new Object[]{getOvals()});
    }

    /**
     * Returns the incudeCSZ.
     * @return boolean
     */
    public boolean isIncudeCSZ()
    {
        return incudeCSZ;
    }

    /**
     * Sets the incudeCSZ.
     * @param incudeCSZ The incudeCSZ to set
     */
    public void setIncudeCSZ(boolean incudeCSZ)
    {
        this.incudeCSZ = incudeCSZ;
    }

}
