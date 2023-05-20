// $Id: OvalsUspsServiceAccess.java,v 1.1 2007/09/25 19:54:30 jh9785 Exp $

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
 *  This file contains the OvalsUspsServiceAccess class.
 *  Description
 */

package com.sbc.eia.bis.BusinessInterface.PostalAddress.ovalsusps;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Vector;

import javax.jms.Message;
import javax.xml.bind.Marshaller;

import com.sbc.bccs.idl.helpers.ObjectPropertyManager;
import com.sbc.bccs.utilities.Logger;
import com.sbc.bccs.utilities.PropertiesFileLoader;
import com.sbc.eia.bis.embus.service.access.ServiceAccess;
import com.sbc.eia.bis.embus.service.access.ServiceException;
import com.sbc.eia.bis.lim.util.LIMBase;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.bis_types.BisContextProperty;
import com.sbc.logging.LogAssistant;
import com.sbc.logging.LogAssistantFactory;
import com.sbc.logging.message.MessageFactory;

/**
 *
 * The OvalsUspsServiceAccess class delivers and then retrieves a reply
 * messages from Ovals USPS.
 * 
 */
public class OvalsUspsServiceAccess extends ServiceAccess
{

    /**
     * Constructor for OvalsUspsServiceAccess.
     */
    public OvalsUspsServiceAccess(
        String environmentName,
        String configFileName,
        LIMBase base)
        throws ServiceException
    {
        super(environmentName, configFileName, (Logger) base);

        Properties options = new Properties();
        options.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, "true");

        OvalsUspsEncoderDecoder ovalsUspsEncoderDecoder =
            new OvalsUspsEncoderDecoder(
                base,
                OVALSUSPSTag.OVALS_JAXB_PACKAGE,
                options);
        setEncoder(ovalsUspsEncoderDecoder);
        setDecoder(ovalsUspsEncoderDecoder);

    }

    static public String[] getTestCases() throws Exception
    {
        Vector tempVector = new Vector();
        InputStreamReader isr =
            new InputStreamReader(
                PropertiesFileLoader.getAsStream(
                    "ovalsusps-access.testcases",
                    null));
        BufferedReader br = new BufferedReader(isr);

        int i = 0;
        String testCase;
        while ((testCase = br.readLine()) != null)
        {
            tempVector.add(testCase);
            i++;
        }

        String[] testCases = new String[i];
        tempVector.toArray(testCases);
        return testCases;

    };

    static public void main(String args[])
    {
        try
        {
            String[] testcases = getTestCases();
            LogAssistant logAssist =
                LogAssistantFactory.create("LIM6.0", "6.0.0");
            logAssist.log(MessageFactory.create(""));
            ;
            ObjectPropertyManager opm = new ObjectPropertyManager();
            opm.add(BisContextProperty.APPLICATION, "LIM_BIS");
            opm.add(BisContextProperty.CUSTOMERNAME, "LIMTest");
            opm.add(BisContextProperty.SERVICECENTER, "XX");
            opm.add(
                BisContextProperty.LOGGINGINFORMATION,
                logAssist.getCorrID());

            BisContext bisContext = new BisContext(opm.toArray());

            Properties p =
                PropertiesFileLoader.read("lim.properties", null);
            LIMBase base = new LIMBase();
            base.setPROPERTIES(p);
            base.setCallerContext(bisContext);
            base.initLIMBase();

            OvalsUspsServiceHelper helper =
                new OvalsUspsServiceHelper(p, base);

            /* basic case */
            for (int xCoor = 0; xCoor < testcases.length; xCoor++)
            {
                try
                {
                    helper.ovalsUspsRequestAndResponse(null);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }

            /* Illegal state exeption from javax.jms.connection test case
             * Another illegal state exception from javax.jms.Session test
             * case may be performed by restarting the server between a send
             * and receive
             */
//            		try {
//            			access.ovalsUspsRequestAndResponse(testcases[0]);
//            			/* restart the broker to get make session invalid*/
//            			System.out.println("Restart the broker and the test server now.");
//            			Thread.sleep(15000);
//            			access.ovalsUspsRequestAndResponse(testcases[1]);
//            		} catch (Exception e){
//            			e.printStackTrace();
//            		}
        }
        catch (Throwable t)
        {
            t.printStackTrace();
        }

        System.exit(0);

    }

    /**
     * @see com.sbc.eia.bis.embus.service.access.ServiceAccess#handleEmbusResponse(Message, Properties)
     */
    public String handleEmbusResponse(
        Message aMessage,
        Properties propertiesInResponse)
        throws ServiceException
    {
        return defaultHandleEmbusResponse(aMessage, propertiesInResponse);
    }

}
