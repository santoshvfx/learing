// $Id: AddressHandlerUSPSTest.java,v 1.1 2005/07/12 21:28:42 as5472 Exp $
/**
 * This file contains the JUnit test cases for the AddressHandlerUSPS class.
 */
package com.sbc.eia.idl.lim.helpers;

import junit.framework.TestCase;

/**
 * This class contains the JUnit TestCases for the AddressHandlerUSPS class.
 */
public class AddressHandlerUSPSTest extends TestCase
{

    String TEST = "TEST";
    
    String APT = "APT";
    String DRW = "DRW";
    String LOT = "LOT";
    String RM = "RM";
    String SLIP = "SLIP";
    String SUIT = "SUIT";
    String UNIT = "UNIT";
    String STE = "STE";
    
    String FLR = "FLR";
    String FL = "FL";
    
    String DEPT = "DEPT";
    String BLDG = "BLDG";
    String WING = "WING";
    String WNG = "WNG";
    String PIER = "PIER";
    String TRLR = "TRLR";

        
    /**
     * Constructor for AddressHandlerUSPSTest.
     * @param arg0
     */
    public AddressHandlerUSPSTest(String arg0)
    {
        super(arg0);
    }

    public static void main(String[] args)
    {
        junit.textui.TestRunner.run(AddressHandlerUSPSTest.class);
    }

    /**
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception
    {
        
        super.setUp();
    }

    /**
     * @see TestCase#tearDown()
     */
    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    
    public void testUnitTwo() throws Exception {
        /*3.1.5.1 If data is present in the “unittype” field and equals to 
         * "APT", "DRW”, "LOT", "RM", "SLIP", "SUIT", “STE” or “UNIT", then 
         * map it to the “aFieldedAddress.aUnitType”; and if data is present 
         * in “unitnumber” then also map it to the “aFieldedAddress.aUnitValue”.
         */
        assertThisCase(APT, "", "", "", "", "", "" ,"" , APT, "");
        assertThisCase(APT, "1", "", "", "", "", "" ,"" , APT, "1");
        
        /*3.1.5.2   If data is present in the “unittype” field and equals to 
         * "FLR", “FL” or “DEPT”, then map it to the “aFieldedAddress.aLevelType”; 
         * and if data is present in “unitnumber” then also map it to the 
         * “aFieldedAddress.aLevelValue”.
         */

        assertThisCase(DEPT, "", "", "", "", "", DEPT, "" ,"", "");
        assertThisCase(DEPT, "1", "", "", "", "", DEPT, "1" ,"", "");
         
        /*3.1.5.3 If data is present in the “unittype” field and equals to 
         * "BLDG", "WING”, "WNG", "PIER", or "TRLR", then map it to the 
         * “aFieldedAddress.aStructureType”; and if data is present in 
         * “unitnumber” then also map it to the “aFieldedAddress.aStructureValue”.
         */
         

        assertThisCase(BLDG, "", "", "", BLDG, "", "", "" ,"", "");
        assertThisCase(BLDG, "1", "", "", BLDG, "1", "", "" ,"", "");

         
        /*3.1.5.4 If data is present in the “unittype” field and not equals 
         * to  "APT", "DRW”, "LOT", "RM", "SLIP", "SUIT", “STE”, “UNIT”, 
         * “FLR”, “FL”, “DEPT”, "BLDG", "WING”, "WNG", "PIER", or "TRLR", 
         * then map it to the “aFieldedAddress.aUnitType”; and if data is 
         * present in “unitnumber” then also map it to the 
         * “aFieldedAddress.aUnitValue”.
         */

        assertThisCase(TEST, "", "", "", "", "", "", "" ,TEST, "");
        assertThisCase(TEST, "1", "", "", "", "", "", "" ,TEST, "1");
         
        /*3.1.5.5 If data is present in the “unittype2” field and equals to 
         * "APT", "DRW”,"LOT", "RM", "SLIP", "SUIT", or “UNIT", then check 
         * to see if data has already been mapped to the 
         * aFieldedAddress.aUnitType” as follows: 
         */
         
            /*3.1.5.5.1   If not, then map it to the “aFieldedAddress.aUnitType” 
             * and if data is present in “unitnumber2” then map it to the 
             * “aFieldedAddress.aUnitValue”. 
             */
             
            assertThisCase("", "", SUIT, "", "", "", "", "" , SUIT, "");
            assertThisCase("", "", SUIT, "1", "", "", "", "" , SUIT, "1");
             
            /*3.1.5.5.2   Else, map it to the aFieldedAddress.aLevelType” and 
             * if data is present in “unitnumber2” then map it to the 
             * “aFieldedAddress.aLevelValue”. 
             */

            assertThisCase(APT, "", SUIT, "", "", "", SUIT, "" , APT, "");
            assertThisCase(APT, "x", SUIT, "1", "", "", SUIT, "1" , APT, "x");
         
        /*3.1.5.6 If data is present in the “unittype2” field and equals to 
         * "FLR", “FL” or “DEPT”, then check to see if data has already been 
         * mapped to the aFieldedAddress.aLevelType” as follows: 
         */
         
            /*3.1.5.6.1   If not, then map it to the “aFieldedAddress.aLevelType” 
             * and if data is present in “unitnumber2” then map it to the 
             * “aFieldedAddress.aLevelValue”. 
             */

            assertThisCase("", "", FLR, "", "", "", FLR, "" , "", "");
            assertThisCase("", "", FLR, "1", "", "", FLR, "1" , "", "");
                         
            /*3.1.5.6.2   Else, map it to the aFieldedAddress.aStructureType” 
             * and if data is present in “unitnumber2” then map it to the 
             * “aFieldedAddress.aStructureValue”. 
             */

            assertThisCase(FL, "", FLR, "", FLR, "", FL, "" , "", "");
            assertThisCase(FL, "x", FLR, "1", FLR, "1", FL, "x" , "", "");
             
        /*3.1.5.7 If data is present in the “unittype2” field and equals to 
         * "BLDG", "WING”, "WNG", "PIER", or "TRLR", then check to see if 
         * data has already been mapped to the aFieldedAddress.aStructureType” 
         * as follows:
         */
         
            /*3.1.5.7.1   If not, then map it to the “aFieldedAddress.aStructureType” 
             * and if data is present in “unitnumber2” then map it to the 
             * “aFieldedAddress.aStructureValue”. 
             */
             
            assertThisCase("", "", PIER, "", PIER, "", "", "" , "", "");
            assertThisCase("", "", PIER, "1", PIER, "1", "", "" , "", "");
         
            /*3.1.5.7.2   Else, map it to the aFieldedAddress.aUnitType” and 
             * if data is present in “unitnumber2” then map it to the 
             * “aFieldedAddress.aUnitValue”. 
             */

            assertThisCase(WING, "", PIER, "", WING, "", "", "" , PIER, "");
            assertThisCase(WING, "x", PIER, "1", WING, "x", "", "" , PIER, "1");
             
        /*3.1.5.8 If data is present in the “unittype2”  and not equals to  
         * "APT", "DRW”, "LOT", "RM", "SLIP", "SUIT", “UNIT”, “FLR”, “FL”, 
         * “DEPT”, "BLDG", "WING”, "WNG", "PIER", or "TRLR", then check to 
         * see if data has already been mapped to the aFieldedAddress.aUnitType” 
         * as follows: 
         */
         
            /*3.1.5.8.1   If not, then map it to the “aFieldedAddress.aUnitType” 
             * and if data is present in “unitnumber2” then map it to the 
             * “aFieldedAddress.aUnitValue”. 
             */
             
            assertThisCase("", "", TEST, "", "", "", "", "" , TEST, "");
            assertThisCase("", "", TEST, "1", "", "", "", "" , TEST, "1");
             
            /*3.1.5.8.2   If already mapped, then map it to the 
             * aFieldedAddress.aLevelType”, and if data is present in 
             * “unitnumber2” then map it to the “aFieldedAddress.aLevelValue”. 
             */

            assertThisCase(APT, "", TEST, "", "", "", TEST, "" , APT, "");
            assertThisCase(APT, "x", TEST, "1", "", "", TEST, "1" , APT, "x");

            /*3.1.5.8.3   If “aFieldedAddress.aLevelType” is also already 
             * mapped to, then map it to the aFieldedAddress.aStructureType” 
             * and if data is present in “unitnumber2” then map it to the 
             * “aFieldedAddress.aStructureValue”. 
             */

            assertThisCase(FLR, "", TEST, "", TEST, "", FLR, "" , "", "");
            assertThisCase(FLR, "x", TEST, "1", TEST, "1", FLR, "x" , "", "");
                         
        /*3.1.5.9 If data is present in the “unittnumber” or “unitnumber2” 
         * and data is “not” present in the “unittype” or “unittype2”, 
         * then map as follows: 
         */
         
            /*3.1.5.9.1   If both “unittnumber” or “unitnumber2” are 
             * populated with data, then map as follows:
                /*a)  Map “unitnumber” to “aFieldedAddress.aUnitValue”.
                 */
                /*b)  Map “unitnumber2” to ““aFieldedAddress.aLevelValue”.
                 */
                assertThisCase("", "u1", "", "u2", "", "", "", "u2" , "", "u1");
                
        /*3.1.5.9.2   If  “unitnumber” or “unitnumber2” is populated with 
         * data, then:
         */
            /*a)  Map to “aFieldedAddress.aUnitValue”. 
             */
            assertThisCase("", "u1", "", "", "", "", "", "" , "", "u1");
            assertThisCase("", "", "", "u2", "", "", "", "" , "", "u2");
            
            assertThisCase("ut1", "u1", "", "u2", "", "", "", "u2" , "ut1", "u1");
            assertThisCase(FLR, "1", "", "u2", "", "u2", FLR, "1" , "", "");
    }
    
    public void assertThisCase(
        String ut1,
        String uv1,
        String ut2,
        String uv2,
        String st,
        String sv,
        String lt,
        String lv,
        String unitT,
        String unitV) throws AddressHandlerException
    {
        AddressHandlerUSPS addr = new AddressHandlerUSPS(
                        "SAN RAMON",              // aCity
                        "",        // aCounty
                        "2600",       // aHouseNumber
                        "",   // aStreetDirection
                        "",  // aStreetNameSuffix
                        "CA",             // aState
                        "CAMINO RAMON",        // aStreetName
                        "",        // aStreetThoroughfare
                        uv1,        // Unit_value
                        uv2,        // Unit_value2
                        ut1,          // Unit_type
                        ut2,          // Unit_type2
                        "94583",
                        "",
                        new String[5],
                        new String[5],
                        ""
                        );
        assertEquals(addr.getStructType(), st);
        assertEquals(addr.getStructValue(), sv);
        assertEquals(addr.getLevelType(), lt);
        assertEquals(addr.getLevelValue(), lv);
        assertEquals(addr.getUnitType(), unitT);
        assertEquals(addr.getUnitValue(), unitV);
    }

}
