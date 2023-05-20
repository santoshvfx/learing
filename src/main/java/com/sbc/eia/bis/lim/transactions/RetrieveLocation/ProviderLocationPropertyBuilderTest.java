// $Id: ProviderLocationPropertyBuilderTest.java,v 1.10 2007/07/05 18:34:05 jh9785 Exp $

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
package com.sbc.eia.bis.lim.transactions.RetrieveLocation;

import junit.framework.TestCase;

import org.omg.CORBA.BAD_OPERATION;

import com.sbc.bccs.idl.helpers.IDLUtil;
import com.sbc.eia.bis.lim.util.LIMBase;
import com.sbc.eia.bis.lim.util.LIMIDLUtil;
import com.sbc.eia.idl.lim.helpers.AddressHandler;
import com.sbc.eia.idl.lim_types.LocationPropertiesToGetValue;
import com.sbc.eia.idl.lim_types.ProviderLocationProperties;
import com.sbc.eia.idl.lim_types.ProviderLocationProperty;
import com.sbc.eia.idl.types.StringOpt;

/** Description
 *  Appropriate description of what this class is used for.
 *  Description
 */
public class ProviderLocationPropertyBuilderTest extends TestCase
{
    private ProviderLocationPropertyBuilder m_junitProviderBuilder = null;
    private RetrieveLocReq m_junitRequest = null;
    private StringOpt m_defaultProviderName = IDLUtil.toOpt ("Data Services");

    /**
     * Constructor for ProviderLocationPropertyBuilderTest.
     * @param arg0
     */
    public ProviderLocationPropertyBuilderTest(String arg0)
    {
        super(arg0);
    }

    /**
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception
    {
        super.setUp();
        setupForProperty(LocationPropertiesToGetValue.ALL);
    }

    /**
     * @see TestCase#tearDown()
     */
    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    
    private void setupForProperties( String[] propsToGet ) {
        String[] propertiesToGet = propsToGet;
        ProviderLocationProperties[] junitPropsToGet = new ProviderLocationProperties[]{ new ProviderLocationProperties(getDefaultProviderName(),  propertiesToGet ) };
        setRequest(new RetrieveLocReq( new LIMBase(), new AddressHandler(), junitPropsToGet));
        setBuilder(new ProviderLocationPropertyBuilder( getRequest().getLocationPropertiesRequested() ));
    }
    
    private void setupForProperty( String propsToGet ) {
        setupForProperties( new String[]{propsToGet} );
    }

    private void setupForAllProperty() {
        setupForProperty( LocationPropertiesToGetValue.ALL );
    }
    
    private void setupForNullProperty() {
        ProviderLocationProperties[] junitPropsToGet = null;
        setRequest(new RetrieveLocReq( new LIMBase(), new AddressHandler(), junitPropsToGet));
        setBuilder(new ProviderLocationPropertyBuilder( getRequest().getLocationPropertiesRequested() ));
    }

    private void display( ProviderLocationProperty prop ) {
        System.out.println( LIMIDLUtil.display(prop) );
    }
    
    private void display( ProviderLocationProperties[] properties ) {
        if( properties != null )
        {
            for ( int i = 0 ; i < properties.length ; i++ )
            {
                System.out.println( LIMIDLUtil.displayProviderLocationProperties(properties[i]) );
            }
        }
        else
        {
            System.out.println(properties);
        }
    }
    
    private ProviderLocationProperty getProvLocProp()
    {
        return getBuilder().getProviderLocationProperty();
    }
    
    private void setDefaultProviderName(StringOpt defaultProviderName)
    {
        m_defaultProviderName = defaultProviderName;
    }

    private StringOpt getDefaultProviderName()
    {
        return m_defaultProviderName;
    }

    private void setBuilder(ProviderLocationPropertyBuilder junitProviderBuilder)
    {
        m_junitProviderBuilder = junitProviderBuilder;
    }

    private ProviderLocationPropertyBuilder getBuilder()
    {
        return m_junitProviderBuilder;
    }

    private void setRequest(RetrieveLocReq junitRequest)
    {
        m_junitRequest = junitRequest;
    }

    private RetrieveLocReq getRequest()
    {
        return m_junitRequest;
    }
    
    private void assertBadOperation( StringOpt optToTest )
    {
        try
        {
            optToTest.theValue();
            fail("This should have failed.  The property should be defaulted.");
        } catch (BAD_OPERATION badOpt){}
    }
    
    /* ======================================================= *
     * Test methods section
     * ======================================================= */

    public void testValueIsDefaultedWhenRetrieveAll()
    {
        String testValue = "aLocalProviderServingOfficeCode";

        setupForAllProperty();
        getBuilder().setLocalProviderServingOfficeCode(testValue);
        assertEquals("request ALL, set LocalProviderServingOfficeCode", testValue, getProvLocProp().aLocalProviderServingOfficeCode.theValue());

        assertBadOperation(getProvLocProp().aCommunityName);
        
        setupForAllProperty();
        getBuilder().setCityStatePostalCodeValid(testValue);
        // for CSZ, if they didn't explicitly ask for it, they can't expect
        // it to be returned.
        assertEquals(0, getProvLocProp().aExtensions.theValue().length);
        
    }
    
    public void testValueIsEmptyStringWhenPropertyIsRequestedAndIsNotRetrieveAll()
    {
        String testValue = "";
        setupForProperty( LocationPropertiesToGetValue.LOCALPROVIDERSERVINGOFFICECODE );
        getBuilder().setLocalProviderServingOfficeCode(testValue);
        assertEquals("", getProvLocProp().aLocalProviderServingOfficeCode.theValue());
        
        setupForProperty( LocationPropertiesToGetValue.CITYSTATEPOSTALCODEVALID );
        getBuilder().setCityStatePostalCodeValid(testValue);
        assertEquals(1, getProvLocProp().aExtensions.theValue().length);
        assertEquals("", getProvLocProp().aExtensions.theValue()[0].aValue);
    }

    public void testValueIsSetWhenNullPropertiesAreRequested()
    {
        String testValue = "aLocalProviderServingOfficeCode";        
        setupForNullProperty();
        getBuilder().setLocalProviderServingOfficeCode(testValue);
        
        assertEquals(testValue, getProvLocProp().aLocalProviderServingOfficeCode.theValue());
        
        setupForNullProperty();
        getBuilder().setCityStatePostalCodeValid(testValue);
        // for CSZ, if they didn't explicitly ask for it, they can't expect
        // it to be returned.
        assertEquals(0, getProvLocProp().aExtensions.theValue().length);
    }
    
    public void testDefaultValueIsDefaulted()
    {
        // null property requested, not set
        setupForNullProperty();
        assertBadOperation(getProvLocProp().aLocalProviderServingOfficeCode);
        assertEquals(0, getProvLocProp().aExtensions.theValue().length);

        // null property, set with ""
        setupForNullProperty();
        getBuilder().setLocalProviderServingOfficeCode("");
        getBuilder().setCityStatePostalCodeValid("");
        assertBadOperation(getProvLocProp().aLocalProviderServingOfficeCode);
        assertEquals(0, getProvLocProp().aExtensions.theValue().length);

        // null property, set with ""
        setupForNullProperty();
        getBuilder().setLocalProviderServingOfficeCode(null);
        getBuilder().setCityStatePostalCodeValid(null);
        assertBadOperation(getProvLocProp().aLocalProviderServingOfficeCode);
        assertEquals(0, getProvLocProp().aExtensions.theValue().length);
                
        // all property, not set
        setupForAllProperty();
        assertBadOperation(getProvLocProp().aLocalProviderServingOfficeCode);
        assertEquals(0, getProvLocProp().aExtensions.theValue().length);

        // all property, set with ""        
        setupForAllProperty();
        getBuilder().setLocalProviderServingOfficeCode("");
        getBuilder().setCityStatePostalCodeValid("");
        assertBadOperation(getProvLocProp().aLocalProviderServingOfficeCode);
        assertEquals(0, getProvLocProp().aExtensions.theValue().length);

        // all property, set with null        
        setupForAllProperty();
        getBuilder().setLocalProviderServingOfficeCode(null);
        getBuilder().setCityStatePostalCodeValid(null);
        assertBadOperation(getProvLocProp().aLocalProviderServingOfficeCode);
        assertEquals(0, getProvLocProp().aExtensions.theValue().length);
    }

    public void testDefaultValueIsEmptySpace()
    {
        setupForProperty( LocationPropertiesToGetValue.CENTRALOFFICECODE );
        assertEquals("", getProvLocProp().aCentralOfficeCode.theValue());
        
        setupForProperty( LocationPropertiesToGetValue.CITYSTATEPOSTALCODEVALID);
        assertEquals(1, getProvLocProp().aExtensions.theValue().length);
        assertEquals("", getProvLocProp().aExtensions.theValue()[0].aValue);

    }
   
    public void testResetProperty()
    {
        String testValue = "ABC";

        /* ======================================================= *
         * Test reset when null property requested
         * ======================================================= */

        // null property, set with valid value
        setupForNullProperty();
        getBuilder().setCentralOfficeType(testValue);
        assertEquals(testValue, getProvLocProp().aCentralOfficeType.theValue());
        // reset property using null
        getBuilder().setCentralOfficeType(null);
        // This should fail because passing null should reset to default value.
        assertBadOperation(getProvLocProp().aCentralOfficeType);
        
        // null property, set with valid value
        setupForNullProperty();
        getBuilder().setCentralOfficeType(testValue);
        // reset property with ""
        getBuilder().setCentralOfficeType("");
        // This should fail because passing \"\" should reset to default value.
        assertBadOperation(getProvLocProp().aCentralOfficeType);
        
        setupForNullProperty();
        getBuilder().setCityStatePostalCodeValid(testValue);

        // for CSZ, if they didn't explicitly ask for it, they can't expect
        // it to be returned.
        assertEquals(getProvLocProp().aExtensions.theValue().length, 0);
        getBuilder().setCityStatePostalCodeValid(null);
        assertEquals(0, getProvLocProp().aExtensions.theValue().length);

        setupForNullProperty();
        assertEquals(0, getProvLocProp().aExtensions.theValue().length);
        getBuilder().setCityStatePostalCodeValid(null);
        assertEquals(0, getProvLocProp().aExtensions.theValue().length);
                
        /* ======================================================= *
         * Test reset when all property requested
         * ======================================================= */
        
        // All property, set with valid value
        setupForAllProperty();
        getBuilder().setCentralOfficeType(testValue);
        assertEquals(testValue, getProvLocProp().aCentralOfficeType.theValue());
        // reset property using null
        getBuilder().setCentralOfficeType(null);
        // This should fail because passing null should reset to default value.
        assertBadOperation(getProvLocProp().aCentralOfficeType);
        
        // All property, set with valid value
        setupForAllProperty();
        getBuilder().setCityStatePostalCodeValid(testValue);
        // for CSZ, if they didn't explicitly ask for it, they can't expect
        // it to be returned.
        assertEquals(0, getProvLocProp().aExtensions.theValue().length);
        // reset property using null
        getBuilder().setCityStatePostalCodeValid(null);
        assertEquals(0, getProvLocProp().aExtensions.theValue().length);
        
        // All property, set with valid value
        setupForAllProperty();
        getBuilder().setCentralOfficeType(testValue);
        // reset property with ""
        getBuilder().setCentralOfficeType("");
        // This should fail because passing \"\" should reset to default value.
        assertBadOperation(getProvLocProp().aCentralOfficeType);
        
        /* ======================================================= *
         * Test reset when explicit property requested
         * ======================================================= */
        
        // All property, set with valid value
//        setupForProperty( LocationPropertiesToGetValue.CENTRALOFFICETYPE );
//        getBuilder().setCentralOfficeType(testValue);
//        assertEquals(testValue, getProvLocProp().aCentralOfficeType.theValue());
//        // reset property using null
//        getBuilder().setCentralOfficeType(null);
//        assertEquals(new String(""), getProvLocProp().aCentralOfficeType.theValue());
        
        // All property, set with valid value
        setupForProperty( LocationPropertiesToGetValue.CITYSTATEPOSTALCODEVALID );
        getBuilder().setCityStatePostalCodeValid(testValue);
        assertEquals(testValue, getProvLocProp().aExtensions.theValue()[0].aValue);
        // reset property using null
        getBuilder().setCityStatePostalCodeValid(null);
        assertEquals(1, getProvLocProp().aExtensions.theValue().length);
        
        // All property, set with valid value
//        setupForProperty( LocationPropertiesToGetValue.CENTRALOFFICETYPE );
//        getBuilder().setCentralOfficeType(testValue);
//        // reset property with ""
//        getBuilder().setCentralOfficeType("");
//        assertEquals("", getProvLocProp().aCentralOfficeType.theValue());
    }
    
    public void testOverwritePropertyValue()
    {
        String oldValue = "ABC";
        String newValue = "DEF";

        /* ======================================================= *
         * Test overwrite when null property requested
         * ======================================================= */

        setupForNullProperty();
        getBuilder().setCentralOfficeType(oldValue);
        getBuilder().setCentralOfficeType(newValue);
        assertEquals(newValue, getProvLocProp().aCentralOfficeType.theValue());
        
        setupForNullProperty();
        getBuilder().setCityStatePostalCodeValid(oldValue);
        getBuilder().setCityStatePostalCodeValid(newValue);
        
        // for CSZ, if they didn't explicitly ask for it, they can't expect
        // it to be returned.
        assertEquals(0, getProvLocProp().aExtensions.theValue().length);
        
        /* ======================================================= *
         * Test overwrite when all property requested
         * ======================================================= */
        
        setupForAllProperty();
        getBuilder().setCentralOfficeType(oldValue);
        getBuilder().setCentralOfficeType(newValue);
        assertEquals(newValue, getProvLocProp().aCentralOfficeType.theValue());
        
        setupForAllProperty();
        getBuilder().setCityStatePostalCodeValid(oldValue);
        getBuilder().setCityStatePostalCodeValid(newValue);
        
        // for CSZ, if they didn't explicitly ask for it, they can't expect
        // it to be returned.
        assertEquals(0, getProvLocProp().aExtensions.theValue().length);

        /* ======================================================= *
         * Test reset when explicit property requested
         * ======================================================= */
        
        // All property, set with valid value
//        setupForProperty( LocationPropertiesToGetValue.CENTRALOFFICETYPE );
//        getBuilder().setCentralOfficeType(oldValue);
//        getBuilder().setCentralOfficeType(newValue);
//        assertEquals(newValue, getProvLocProp().aCentralOfficeType.theValue());        

        setupForProperty( LocationPropertiesToGetValue.CITYSTATEPOSTALCODEVALID );
        getBuilder().setCityStatePostalCodeValid(oldValue);
        getBuilder().setCityStatePostalCodeValid(newValue);
        assertEquals(newValue, getProvLocProp().aExtensions.theValue()[0].aValue);   
    }

    public void testPassingEmptySpace()
    {
        /* ======================================================= *
         * test with null property requested
         * ======================================================= */
        setupForNullProperty();
        getBuilder().setCentralOfficeType("");
        // Null property requested and COT set to "".  COT should be defaulted.
        assertBadOperation( getProvLocProp().aCentralOfficeType );

        setupForNullProperty();
        getBuilder().setCityStatePostalCodeValid("");
        assertEquals(0, getProvLocProp().aExtensions.theValue().length);

        /* ======================================================= *
         * test with all property requested
         * ======================================================= */
        setupForAllProperty();
        // Null property requested and COT set to "".  COT should be defaulted.
        getBuilder().setCentralOfficeType("");
        assertBadOperation( getProvLocProp().aCentralOfficeType );
        
        setupForAllProperty();
        getBuilder().setCityStatePostalCodeValid("");
        assertEquals( 0, getProvLocProp().aExtensions.theValue().length );
                
        /* ======================================================= *
         * test with explicit property requested
         * ======================================================= */
//        setupForProperty(LocationPropertiesToGetValue.CENTRALOFFICETYPE);
//        getBuilder().setCentralOfficeType("");
//        assertEquals("", getProvLocProp().aCentralOfficeType.theValue());
        
        setupForProperty(LocationPropertiesToGetValue.CITYSTATEPOSTALCODEVALID);
        getBuilder().setCityStatePostalCodeValid("");
        assertEquals("", getProvLocProp().aExtensions.theValue()[0].aValue);

    }

    public void testProviderLocationPropertyBuilder()
    {
    }

    public void testGetProviderLocationProperty()
    {
    }

    public void testSetPostalAddress()
    {
    }

    public void testSetServiceAddress()
    {
    }

    public void testSetSwitchSuperPopAddress()
    {
    }
    
    public void testSetProviderName()
    {
        String testValue = "Test Provider Name";
        getBuilder().setProviderName(testValue);
        assertEquals(testValue, getProvLocProp().aProviderName );
    }
            
    public void testSetAddressMatchCode()
    {
        String testValue = "4S00";
        getBuilder().setAddressMatchCode(testValue, false);
        assertEquals(testValue, getProvLocProp().aAddressMatchCode.theValue());
    }

    public void testSetCityStatePostalCodeValid()
    {
        String testValue = "Y";
        setupForProperty( LocationPropertiesToGetValue.CITYSTATEPOSTALCODEVALID );
        getBuilder().setCityStatePostalCodeValid(testValue);
        assertEquals(1, getProvLocProp().aExtensions.theValue().length);
        assertEquals(testValue, getProvLocProp().aExtensions.theValue()[0].aValue);
        assertEquals(LocationPropertiesToGetValue.CITYSTATEPOSTALCODEVALID, getProvLocProp().aExtensions.theValue()[0].aId);
    }
        
    public void testAddressMatchCodeNotSetWhenNotRequested()
    {
        String testValue = "4S00";
        setupForProperty( LocationPropertiesToGetValue.COMMUNITYNAME );
        getBuilder().setAddressMatchCode(testValue, false);

        assertBadOperation( getProvLocProp().aAddressMatchCode );
    }

    public void testAlwaysSetAddressMatchCodeEvenWhenNotRequested()
    {
        String testValue = "4S00";
        setupForProperty( LocationPropertiesToGetValue.COMMUNITYNAME );
        getBuilder().setAddressMatchCode(testValue, true);
        assertEquals("request COMMUNITYNAME only, set AMQ", testValue, getProvLocProp().aAddressMatchCode.theValue());
    }

    public void testSetAddressMatchCodeDescription()
    {
        String testValue = "aAddressMatchCodeDescription";
        getBuilder().setAddressMatchCodeDescription(testValue, false);
        assertEquals(testValue, getProvLocProp().aAddressMatchCodeDescription.theValue());
    }

    public void testAddressMatchCodeDescriptionNotSetWhenNotRequested()
    {
        String testValue = "aAddressMatchCodeDescription";
        setupForProperty( LocationPropertiesToGetValue.COMMUNITYNAME );
        getBuilder().setAddressMatchCodeDescription(testValue, false);

        assertBadOperation( getProvLocProp().aAddressMatchCodeDescription );
    }

    public void testAlwaysSetAddressMatchCodeDescriptionEvenWhenNotRequested()
    {
        String testValue = "aAddressMatchCodeDescription";
        setupForProperty( LocationPropertiesToGetValue.COMMUNITYNAME );
        getBuilder().setAddressMatchCodeDescription(testValue, true);
        assertEquals("request COMMUNITYNAME only, set set AMQ Description", testValue, getProvLocProp().aAddressMatchCodeDescription.theValue());
    }

    public void testSetCentralOfficeCode()
    {
        String testValue = "aCentralOfficeCode";
        getBuilder().setCentralOfficeCode(testValue);
        assertEquals(testValue, getProvLocProp().aCentralOfficeCode.theValue());
    }

    public void testSetCentralOfficeType()
    {
        String testValue = "aCentralOfficeType";
        getBuilder().setCentralOfficeType(testValue);
        assertEquals(testValue, getProvLocProp().aCentralOfficeType.theValue());
    }

    public void testSetCommunityName()
    {
        String testValue = "aCommunityName";
        getBuilder().setCommunityName(testValue);
        assertEquals(testValue, getProvLocProp().aCommunityName.theValue());
    }

    public void testSetCoSwitchSuperPop()
    {
        String testValue = "aCoSwitchSuperPop";
        getBuilder().setCoSwitchSuperPop(testValue);
        assertEquals(testValue, getProvLocProp().aCoSwitchSuperPop.theValue());
    }

    public void testSetDomSwitchPop()
    {
        String testValue = "aDomSwitchPop";
        getBuilder().setDomSwitchPop(testValue);
        assertEquals(testValue, getProvLocProp().aDomSwitchPop.theValue());
    }

    public void testSetE911Exempt()
    {
        String testValue = "aE911Exempt";
        getBuilder().setE911Exempt(testValue);
        assertEquals(testValue, getProvLocProp().aE911Exempt.theValue());
    }

    public void testSetE911NonRecurringCharge()
    {
        String testValue = "aE911NonRecurringCharge";
        getBuilder().setE911NonRecurringCharge(testValue);
        assertEquals(testValue, getProvLocProp().aE911NonRecurringCharge.theValue());
    }

    public void testSetE911Surcharge()
    {
        String testValue = "aE911Surcharge";
        getBuilder().setE911Surcharge(testValue);
        assertEquals(testValue, getProvLocProp().aE911Surcharge.theValue());
    }

    public void testSetExchangeCode()
    {
        String testValue = "aExchangeCode";
        getBuilder().setExchangeCode(testValue);
        assertEquals(testValue, getProvLocProp().aExchangeCode.theValue());
    }

    public void testSetExco()
    {
        String testValue = "aExco";
        getBuilder().setExco(testValue);
        assertEquals(testValue, getProvLocProp().aExco.theValue());
    }

    public void testSetGeoCode()
    {
        String testValue = "aGeoCode";
        getBuilder().setGeoCode(testValue);
        assertEquals(testValue, getProvLocProp().aGeoCode.theValue());
    }

    public void testSetHorizontalCoordinate()
    {
        String testValue = "aHorizontalCoordinate";
        getBuilder().setHorizontalCoordinate(testValue);
        assertEquals(testValue, getProvLocProp().aHorizontalCoordinate.theValue());
    }

    public void testSetLataCode()
    {
        String testValue = "aLataCode";
        getBuilder().setLataCode(testValue);
        assertEquals(testValue, getProvLocProp().aLataCode.theValue());
    }

    public void testSetLataName()
    {
        String testValue = "aLataName";
        getBuilder().setLataName(testValue);
        assertEquals(testValue, getProvLocProp().aLataName.theValue());
    }

//    public void testSetLatitudeLongitude()
//    {
//        String testValue = "aLatitudeLongitude";
//        getBuilder().setLatitudeLongitude(testValue);
//        assertEquals(testValue, getProvLocProp().aLatitudeLongitude.theValue());
//    }

    public void testSetLatitude()
    {
        String testValue = "aLatitude";
        getBuilder().setLatitude(testValue);
        assertEquals(testValue, getProvLocProp().aLatitude.theValue());
    }
    
    public void testSetLongitude()
    {
        String testValue = "aLongitude";
        getBuilder().setLongitude(testValue);
        assertEquals(testValue, getProvLocProp().aLongitude.theValue());
    }

    public void testSetLocalProviderExchangeCode()
    {
        String testValue = "aLocalProviderExchangeCode";
        getBuilder().setLocalProviderExchangeCode(testValue);
        assertEquals(testValue, getProvLocProp().aLocalProviderExchangeCode.theValue());
    }

    public void testSetLocalProviderName()
    {
        String testValue = "aLocalProviderName";
        getBuilder().setLocalProviderName(testValue);
        assertEquals(testValue, getProvLocProp().aLocalProviderName.theValue());
    }

    public void testSetLocalProviderNumber()
    {
        String testValue = "aLocalProviderNumber";
        getBuilder().setLocalProviderNumber(testValue);
        assertEquals(testValue, getProvLocProp().aLocalProviderNumber.theValue());
    }

    public void testSetLocalProviderServingOfficeCode()
    {
        String testValue = "aLocalProviderServingOfficeCode";
        getBuilder().setLocalProviderServingOfficeCode(testValue);
        assertEquals(testValue, getProvLocProp().aLocalProviderServingOfficeCode.theValue());
    }

    public void testSetLocalProviderAbbreviatedName()
    {
        String testValue = "aLocalProviderAbbreviatedName";
        getBuilder().setLocalProviderAbbreviatedName(testValue);
        assertEquals(testValue, getProvLocProp().aLocalProviderAbbreviatedName.theValue());
    }

    public void testSetMailingBarCodeSuffix()
    {
        String testValue = "aMailingBarCodeSuffix";
        getBuilder().setMailingBarCodeSuffix(testValue);
        assertEquals(testValue, getProvLocProp().aMailingBarCodeSuffix.theValue());
    }

    public void testSetMsaCode()
    {
        String testValue = "aMsaCode";
        getBuilder().setMsaCode(testValue);
        assertEquals(testValue, getProvLocProp().aMsaCode.theValue());
    }

    public void testSetMsaName()
    {
        String testValue = "aMsaName";
        getBuilder().setMsaName(testValue);
        assertEquals(testValue, getProvLocProp().aMsaName.theValue());
    }

    public void testSetNearestDistanceColoToCo()
    {
        String testValue = "aNearestDistanceColoToCo";
        getBuilder().setNearestDistanceColoToCo(testValue);
        assertEquals(testValue, getProvLocProp().aNearestDistanceColoToCo.theValue());
    }

    public void testSetNearestDistanceSuperPopToCo()
    {
        String testValue = "aNearestDistanceSuperPopToCo";
        getBuilder().setNearestDistanceSuperPopToCo(testValue);
        assertEquals(testValue, getProvLocProp().aNearestDistanceSuperPopToCo.theValue());
    }

    public void testSetNearestSbcColo()
    {
        String testValue = "aNearestSbcColo";
        getBuilder().setNearestSbcColo(testValue);
        assertEquals(testValue, getProvLocProp().aNearestSbcColo.theValue());
    }

    public void testSetNearestSbcCoSuperPop()
    {
        String testValue = "aNearestSbcCoSuperPop";
        getBuilder().setNearestSbcCoSuperPop(testValue);
        assertEquals(testValue, getProvLocProp().aNearestSbcCoSuperPop.theValue());
    }

    public void testSetNearestSbcCoWirecenter()
    {
        String testValue = "aNearestSbcCoWirecenter";
        getBuilder().setNearestSbcCoWirecenter(testValue);
        assertEquals(testValue, getProvLocProp().aNearestSbcCoWirecenter.theValue());
    }

    public void testSetOwnedWiring()
    {
        String testValue = "aOwnedWiring";
        getBuilder().setOwnedWiring(testValue);
        assertEquals(testValue, getProvLocProp().aOwnedWiring.theValue());
    }

    public void testSetPostalCarrierCode()
    {
        String testValue = "aPostalCarrierCode";
        getBuilder().setPostalCarrierCode(testValue);
        assertEquals(testValue, getProvLocProp().aPostalCarrierCode.theValue());
    }

    public void testSetPrimaryDirectoryCode()
    {
        String testValue = "aPrimaryDirectoryCode";
        getBuilder().setPrimaryDirectoryCode(testValue);
        assertEquals(testValue, getProvLocProp().aPrimaryDirectoryCode.theValue());
    }

    public void testSetPrimaryNpaNxx()
    {
        String testValue = "aPrimaryNpaNxx";
        getBuilder().setPrimaryNpaNxx(testValue);
        assertEquals(testValue, getProvLocProp().aPrimaryNpaNxx.theValue());
    }

    public void testSetQuickDialTone()
    {
        String testValue = "aQuickDialTone";
        getBuilder().setQuickDialTone(testValue);
        assertEquals(testValue, getProvLocProp().aQuickDialTone.theValue());
    }

    public void testSetQuickDialToneNumber()
    {
        String testValue = "aQuickDialToneNumber";
        getBuilder().setQuickDialToneNumber(testValue);
        assertEquals(testValue, getProvLocProp().aQuickDialToneNumber.theValue());
    }

    public void testSetRateCenterCode()
    {
        String testValue = "aRateCenterCode";
        getBuilder().setRateCenterCode(testValue);
        assertEquals(testValue, getProvLocProp().aRateCenterCode.theValue());
    }

    public void testSetRateZone()
    {
        String testValue = "aRateZone";
        getBuilder().setRateZone(testValue);
        assertEquals(testValue, getProvLocProp().aRateZone.theValue());
    }

    public void testSetRateZoneBandCode()
    {
        String testValue = "aRateZoneBandCode";
        getBuilder().setRateZoneBandCode(testValue);
        assertEquals(testValue, getProvLocProp().aRateZoneBandCode.theValue());
    }

    public void testSetSagNpa()
    {
        String testValue = "aSagNpa";
        getBuilder().setSagNpa(testValue);
        assertEquals(testValue, getProvLocProp().aSagNpa.theValue());
    }

    public void testSetSagWireCenter()
    {
        String testValue = "aSagWireCenter";
        getBuilder().setSagWireCenter(testValue);
        assertEquals(testValue, getProvLocProp().aSagWireCenter.theValue());
    }

    public void testSetSbcColoLsoCode()
    {
        String testValue = "aSbcColoLsoCode";
        getBuilder().setSbcColoLsoCode(testValue);
        assertEquals(testValue, getProvLocProp().aSbcColoLsoCode.theValue());
    }

    public void testSetSbcColoWirecenter()
    {
        String testValue = "aSbcColoWirecenter";
        getBuilder().setSbcColoWirecenter(testValue);
        assertEquals(testValue, getProvLocProp().aSbcColoWirecenter.theValue());
    }

    public void testSetSbcServingOfficeCode()
    {
        String testValue = "aSbcServingOfficeCode";
        getBuilder().setSbcServingOfficeCode(testValue);
        assertEquals(testValue, getProvLocProp().aSbcServingOfficeCode.theValue());
    }

    public void testSetSbcServingOfficeWirecenter()
    {
        String testValue = "aSbcServingOfficeWirecenter";
        getBuilder().setSbcServingOfficeWirecenter(testValue);
        assertEquals(testValue, getProvLocProp().aSbcServingOfficeWirecenter.theValue());
    }

    public void testSetServingAreaDescription()
    {
        String testValue = "aServingAreaDescription";
        getBuilder().setServingAreaDescription(testValue);
        assertEquals(testValue, getProvLocProp().aServingAreaDescription.theValue());
    }

    public void testSetStreetAddressGuideArea()
    {
        String testValue = "aStreetAddressGuideArea";
        getBuilder().setStreetAddressGuideArea(testValue);
        assertEquals(testValue, getProvLocProp().aStreetAddressGuideArea.theValue());
    }

    public void testSetSurcharge16Percent()
    {
        String testValue = "aSurcharge16Percent";
        getBuilder().setSurcharge16Percent(testValue);
        assertEquals(testValue, getProvLocProp().aSurcharge16Percent.theValue());
    }

    public void testSetSurcharge4Percent()
    {
        String testValue = "aSurcharge4Percent";
        getBuilder().setSurcharge4Percent(testValue);
        assertEquals(testValue, getProvLocProp().aSurcharge4Percent.theValue());
    }

    public void testSetSwitchDataSuperPop()
    {
        String testValue = "aSwitchDataSuperPop";
        getBuilder().setSwitchDataSuperPop(testValue);
        assertEquals(testValue, getProvLocProp().aSwitchDataSuperPop.theValue());
    }

    public void testSetSwitchVoiceSuperPop()
    {
        String testValue = "aSwitchVoiceSuperPop";
        getBuilder().setSwitchVoiceSuperPop(testValue);
        assertEquals(testValue, getProvLocProp().aSwitchVoiceSuperPop.theValue());
    }

    public void testSetTarCode()
    {
        String testValue = "aTarCode";
        getBuilder().setTarCode(testValue);
        assertEquals(testValue, getProvLocProp().aTarCode.theValue());
    }

    public void testSetTelephoneNumber()
    {
        String testValue = "aTelephoneNumber";
        getBuilder().setTelephoneNumber(testValue);
        assertEquals(testValue, getProvLocProp().aTelephoneNumber.theValue());
    }

    public void testSetVerticalCoordinate()
    {
        String testValue = "aVerticalCoordinate";
        getBuilder().setVerticalCoordinate(testValue);
        assertEquals(testValue, getProvLocProp().aVerticalCoordinate.theValue());
    }

    public void testSetWorkingServiceOnLocation()
    {
        String testValue = "aWorkingServiceOnLocation";
        getBuilder().setWorkingServiceOnLocation( testValue );
        assertEquals(testValue, getProvLocProp().aWorkingServiceOnLocation.theValue());
    }
    
    public static void main( String[] args ) {
        junit.textui.TestRunner.run(ProviderLocationPropertyBuilderTest.class);
    }


}