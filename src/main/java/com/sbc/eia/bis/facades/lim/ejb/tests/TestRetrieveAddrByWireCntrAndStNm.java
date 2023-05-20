// $Id: TestRetrieveAddrByWireCntrAndStNm.java,v 1.4 2004/04/22 22:46:59 as5472 Exp $

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
 *  This file contains the TestRetrieveAddrByWireCntrAndStNm class.
 *  Description
 */

package com.sbc.eia.bis.facades.lim.ejb.tests;

import java.util.Properties;

import com.sbc.bccs.idl.helpers.ObjectPropertyManager;
import com.sbc.bccs.utilities.PropertiesFileLoader;
import com.sbc.eia.bis.lim.database.queries.RetrieveAddrByWireCntrAndStNm;
import com.sbc.eia.bis.lim.util.LIMBase;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.bis_types.BisContextProperty;
import com.sbc.eia.idl.lim.helpers.AddressHandler;
import com.sbc.eia.idl.lim.helpers.AddressHandlerException;
import com.sbc.eia.idl.lim_types.Address;

/**
 * Test TestRetrieveAddrByWireCntrAndStNm.
 * Creation date: (08/24/01 2:27:36 PM)
 * @author: Rachel Zadok
 */
public class TestRetrieveAddrByWireCntrAndStNm extends LIMBase {
    /**
     * Construct a TestRetrieveAddrByWireCntrAndStNm object.
     * @param aContext com.sbc.eia.idl.bis_types.BisContext
     * @param aProperties java.util.Properties
     */
    public TestRetrieveAddrByWireCntrAndStNm(BisContext aContext, Properties aProperties ) {
        super.setCallerContext( aContext );
        super.setPROPERTIES( aProperties );
        initLIMBase();
    }
    /**
     * Starts the TestRetrieveAddrByWireCntrAndStNm transaction.
     * Creation date: (08/24/01 2:29:24 PM)
     * @param args java.lang.String[]
     */
    public static void main(String[] args) 
    {
        System.out.println("Retrieving Addresses Data by Street Name and Wire Center ");
    
        //String propertiesFile = "c:/deploy/limJdbc.properties";
        String propertiesFile = "c:/deploy/lim.properties";
    
        // use alternate properties file    
        if ( args.length > 0 ) {
            propertiesFile = args[0];
        }
        
        ObjectPropertyManager opm = new ObjectPropertyManager();
        opm.add(BisContextProperty.APPLICATION,"LIM_BIS");
        opm.add(BisContextProperty.SERVICECENTER,"CT");
        BisContext aContext = new BisContext(opm.toArray()) ;
    
        Properties myProperty = new Properties();
        
        try {
            // Load LIM properties for the LIMBase class..
            try{
                myProperty = PropertiesFileLoader.read(propertiesFile, null);
            }
            catch(Exception e){
                e.printStackTrace();
                System.out.println("Load Property file failed.");
            }
        
            System.out.println("Setup DB stuff... ");
    
            TestRetrieveAddrByWireCntrAndStNm aDbHelper = new TestRetrieveAddrByWireCntrAndStNm (aContext, myProperty);
            aDbHelper.retrieveAddrByWireCntrAndStNm( myProperty );
    
        }
        catch( Exception e ) {
            System.out.println("Main() - Exception caught...") ;
            e.printStackTrace();
        };
        
    }
    /**
     * Populate RetrieveAddrByWireCntrAndStNm fields.
     * Creation date: (08/24/01 11:08:36 AM)
     * @param aFileName java.lang.String
     * @exception java.sql.SQLException The exception description.
     * @exception AddressHandlerException
     */
    public void retrieveAddrByWireCntrAndStNm( String aFileName ) 
    throws java.sql.SQLException, AddressHandlerException
    {
        String wireCenter = "203132";
        String streetName = "ADIRONDACK LA";
        String houseNumber = null;
        String city = null;
        String structValue = null;
        String levelValue = null;
        String unitValue = null;
        
        Address[] addressList =
            RetrieveAddrByWireCntrAndStNm.retrieveAddrByWireCntrAndStNm(
                aFileName,
                wireCenter,
                streetName,
                houseNumber,
                city,
                structValue,
                levelValue,
                unitValue,
                75,
                150,
                this);
    
        if (addressList != null && addressList.length > 0)
        {
            System.out.println ("The following addresses were returned:" );
            for (int i = 0; i < addressList.length; i++)
            {
                AddressHandler ah_i = new AddressHandler (addressList[i]);
                System.out.println (ah_i);
            }
        }
        else
            System.out.println ("No matching addresses were found." );
    }
    /**
     * Populate RetrieveAddrByWireCntrAndStNm fields.
     * Creation date: (08/24/01 11:08:36 AM)
     * @param aProperties Properties
     * @exception java.sql.SQLException The exception description.
     * @exception AddressHandlerException
     */
    public void retrieveAddrByWireCntrAndStNm( Properties aProperties ) 
    throws java.sql.SQLException, AddressHandlerException
    {
        String wireCenter = "203132";
        String streetName = "ADIRONDACK LA";
        String houseNumber = null;
        String city = null;
        String structValue = null;
        String levelValue = null;
        String unitValue = null;
        
        Address[] addressList =
            RetrieveAddrByWireCntrAndStNm.retrieveAddrByWireCntrAndStNm(
                aProperties,
                wireCenter,
                streetName,
                houseNumber,
                city,
                structValue,
                levelValue,
                unitValue,
                75,
                150,
                this);
    
        if (addressList != null && addressList.length > 0)
        {
            System.out.println ("The following addresses were returned:" );
            for (int i = 0; i < addressList.length; i++)
            {
                AddressHandler ah_i = new AddressHandler (addressList[i]);
                System.out.println (ah_i);
            }
        }
        else
            System.out.println ("No matching addresses were found." );
            
    }
}