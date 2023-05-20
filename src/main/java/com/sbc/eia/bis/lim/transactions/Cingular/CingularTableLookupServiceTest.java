//$Id: CingularTableLookupServiceTest.java ,v 1.7 2008/02/20 21:40:17 jd3462 Exp $

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
 *  Appropriate description of what this file (or classes within) is used for.
 *  Description
 */
package com.sbc.eia.bis.lim.transactions.Cingular;

import java.util.Properties;

import junit.framework.TestCase;

import com.sbc.bccs.utilities.Logger;
import com.sbc.bccs.utilities.PropertiesFileLoader;

/** Description
 *  Appropriate description of what this class is used for.
 *  Description
 */
public class CingularTableLookupServiceTest extends TestCase
{
    Logger i_logger = null;
    Properties configurationInfo = null;
    /**
     * Constructor for CingularTableLookupServiceTest.
     * @param arg0
     */
    public CingularTableLookupServiceTest(String arg0)
    {
        super(arg0);
    }

    public static void main(String[] args)
    {
        System.setProperty("bis.platform", "PC");
        junit.textui.TestRunner.run(CingularTableLookupServiceTest.class);
    }

    public void testLookupCasRegionLookupServiceByState() {

        // testing with 13 states, using ficticious billing markets,
        // except for IN which returns INDY when 22, and 66
        String[][]validScBmCasCombinations = new String[][]
            {{   "CA",   "NV",   "CT",   "IL",   "MI",   "OH",   "WI",   "IN",   "AR",   "KS",   "MO",   "OK",   "TX",   "IN",   "IN"},    
             {    "464",    "46X",    "46T",    "4X6",    "X46",    "4D6",    "4W6",    "4XX",    "R46",    "S46",    "46P",    "46B",    "E6",   "22",   "66"},
             {"WCRED","WCRED","ECRED","CCRED","CCRED","CCRED","CCRED","CCRED","PCRED","PCRED","PCRED","PCRED","PCRED", "INDY", "INDY"}};

        for( int jj = 0 ; jj < validScBmCasCombinations[0].length ; jj++ )
        {
            String sc = validScBmCasCombinations[0][jj];
            String bm = validScBmCasCombinations[1][jj];
            String cr = validScBmCasCombinations[2][jj];
            
            try {
                runTestLookupCasRegionLookupServiceByState(sc, bm, cr);
            } catch (Exception e){
                fail(e.toString());
            }
        }

        // test case sensitivity
        // trimming spaces
        // non-numeric market codes
        // null references
        // and even the word "null"
        validScBmCasCombinations = new String[][]
            {{   "Ca",   "nv",   "ct",   " iL",   " mi",   "oh",   " Wi",   "In ",   "Ar",   "kS",   "mO",   "oK",   "tx",   "iN",   "In "},    
             {    "464 ",    " 46X",    null,    "4X6",    "X46",    "4D6",    "null",    "4XX",    "R46",    "S46",    "46P",    "46B",    "E6",   "22",   "66"},
             {"WCRED","WCRED","ECRED","CCRED","CCRED","CCRED","CCRED","CCRED","PCRED","PCRED","PCRED","PCRED","PCRED", "INDY", "INDY"}};

        for( int jj = 0 ; jj < validScBmCasCombinations[0].length ; jj++ )
        {
            String sc = validScBmCasCombinations[0][jj];
            String bm = validScBmCasCombinations[1][jj];
            String cr = validScBmCasCombinations[2][jj];
            
            try {
                runTestLookupCasRegionLookupServiceByState(sc, bm, cr);
            } catch (Exception e){
                fail(e.toString());
            }
        }
        // test invalid state
        validScBmCasCombinations = new String[][]
            {{   "FL",   "NY",   "XX"},
             {    "22 ",    " 46X",    "66"},
             {null, null, null}};

        for( int jj = 0 ; jj < validScBmCasCombinations[0].length ; jj++ )
        {
            String sc = validScBmCasCombinations[0][jj];
            String bm = validScBmCasCombinations[1][jj];
            String cr = validScBmCasCombinations[2][jj];
            
            try {
                runTestLookupCasRegionLookupServiceByInvalidState(sc, bm, cr);
            } catch (Exception e){
                fail();
            }
        }
        // test that application should fail with null pointer exception
        // when state is "null"
        String sc = null;
        String bm = "22";
        String expCr = "XXXX"; // should fail, use "INVALID" CAS region
        
        try {
            runTestLookupCasRegionLookupServiceByState(sc, bm, expCr);
            fail("Should have thrown NullPointerException instead.  " +
                "Should not have come to this line.");
        } catch (NullPointerException ne) {
            // should fail with null pointer, so considered success
        } catch (Exception e) {
            fail("Unexpected exception occured: " + e.toString());
        }
        
    }
    
    void runTestLookupCasRegionLookupServiceByState(
        String i_stateCode,
        String i_billingMarket,
        String expectedCas)
        throws Exception
    {
        assertEquals(
            "Testing SC=" + i_stateCode + ", BM=" + i_billingMarket + ".",
            expectedCas,
            new CingularTableLookupService().lookupCasRegionLookupServiceByState(
                i_logger,
                configurationInfo,
                i_stateCode,
                i_billingMarket));
    }

    void runTestLookupCasRegionLookupServiceByInvalidState(
        String i_stateCode,
        String i_billingMarket,
        String expectedCas)
        throws Exception
    {
        assertEquals(
            "Testing SC=" + i_stateCode + ", BM=" + i_billingMarket + ".",
            expectedCas,
            new CingularTableLookupService().lookupCasRegionLookupServiceByState(
                i_logger,
                configurationInfo,
                i_stateCode,
                i_billingMarket));
    }
    
    public void testIsSellableToChannel() {

        // Testing true cases
        String swot_channel = "SWOT";
        String wow_channel = "WOW";
        String iwow_channel = "iWOW";
        String ls_channel = "LIGHTSPEED";
        String good_zc = "94530";
        String one_spe_error_zc = "94531";
        String invalid_zc = "94532";
        String three_spe_error_zc = "94533";
        
        runTestIsSellableForChannels(good_zc, swot_channel);
        runTestIsSellableForChannels(good_zc, wow_channel);
        runTestIsSellableForChannels(good_zc, iwow_channel);
        runTestIsSellableForChannels(good_zc, ls_channel);
        
        // Testing false cases
        runTestIsNotSellableForChannels(invalid_zc, swot_channel);
        runTestIsNotSellableForChannels(invalid_zc, ls_channel);
        
        // Testing for WOW/iWOW, WOW/iWOW sells to all ZC since validated
        // by client
        runTestIsSellableForChannels(invalid_zc, wow_channel);
        runTestIsSellableForChannels(invalid_zc, iwow_channel);

        // Testing for CASE INSENSITIVITY
        String i_channel = "Swot";
        runTestIsSellableForChannels(good_zc, i_channel);

        i_channel = "Wow";
        runTestIsSellableForChannels(good_zc, i_channel);

        i_channel = "iWow";
        runTestIsSellableForChannels(good_zc, i_channel);

        i_channel = "LightSpeed";
        runTestIsSellableForChannels(good_zc, i_channel);
        
        // Testing with invalid channel names
        i_channel = "LS";
        runTestIsNotSellableForChannels(good_zc, i_channel);
        
        // Testing for trimming trailing non-printable characters and spaces.
        i_channel = "LightSpeed ";
        runTestIsSellableForChannels(good_zc, i_channel);

    }
    
    void runTestIsSellableForChannels( String i_zc, String i_channel ) {
        try {
            assertTrue(
                CingularTableLookupService.isSellableToChannel(
                    new DummyLogger(),
                    configurationInfo,
                    i_zc,
                    i_channel));
        } catch (Exception e) {
            fail(e.toString());
        }
    }
    
    void runTestIsNotSellableForChannels( String i_zc, String i_channel ) {
        try {
            assertTrue(
                "Testing with ZC=" + i_zc + " CH=" + i_channel + ".",
                !CingularTableLookupService.isSellableToChannel(
                    new DummyLogger(),
                    configurationInfo,
                    i_zc,
                    i_channel));
        } catch (Exception e) {
            fail();
        }
    }
    
    /**
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception
    {
        super.setUp();
        i_logger = new DummyLogger();
        configurationInfo = PropertiesFileLoader.read("lim.properties", i_logger);       
    }

    /**
     * @see TestCase#tearDown()
     */
    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    
    class DummyLogger implements Logger {
        DummyLogger() {
        }
        /**
         * @see com.sbc.bccs.utilities.Logger#log(String, String)
         */
        public void log(String eventId, String message)
        {
            System.out.println(eventId + ":" + message);
        }
        /**
         * @see com.sbc.bccs.utilities.Logger#log(String, String, String, String)
         */
        public void log(
            String eventId,
            String the_error,
            String the_message,
            String the_source)
        {
        }
        /**
         * @see com.sbc.bccs.utilities.Logger#log(String, String, String, String, String)
         */
        public void log(
            String eventId,
            String the_location,
            String the_service,
            String the_component,
            String the_operation)
        {
        }
    }

}
