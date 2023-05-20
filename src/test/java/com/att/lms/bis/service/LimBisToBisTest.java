package com.att.lms.bis.service;

import com.att.lms.bis.rest.service.SmRestService;
import com.att.transformation.BaseJUnit5Test;
import com.ibm.mq.jms.services.Trace;
import com.sbc.eia.bis.RmNam.utilities.BimxClient.BimxClient;
import com.sbc.eia.bis.RmNam.utilities.SmClient.SmClient;
import com.sbc.eia.bis.framework.BisContextManager;
import com.sbc.eia.bis.lim.util.BisContextHelper;
import com.sbc.eia.idl.bim_types.AddNotesToBillingAccountReturn;
import com.sbc.eia.idl.bimx.BimxFacade;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.bis_types.BisTypesObjectKeyKind;
import com.sbc.eia.idl.bis_types.TelephoneNumber;
import com.sbc.eia.idl.lim.*;
import com.sbc.eia.idl.lim_types.*;
import com.sbc.eia.idl.sm.GetServiceAddressReturn;
import com.sbc.eia.idl.sm.PingReturn;
import com.sbc.eia.idl.sm.SmFacade;
import com.sbc.eia.idl.types.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LimBisToBisTest extends BaseJUnit5Test {

    @Autowired
    private LimLegacyService limLegacyService;

    @Autowired
    private SmRestService smRestService;

    private BisContext getContext(String customerName, String businessUnit, String loggingInfo) {
        return BisContextHelper.setBisContext
                ("LIM_BIS", customerName, businessUnit, loggingInfo, limLegacyService.PROPERTIES);
    }

    private BisContext getContext(String application, String customerName, String businessUnit, String loggingInfo) {
        return BisContextHelper.setBisContext
                (application, customerName, businessUnit, loggingInfo, limLegacyService.PROPERTIES);
    }

    @BeforeAll
    public static void setup() throws Exception {
        System.setProperty("VoltageFlag","on");
        System.setProperty("VoltageExceptions","VoltageExceptions.properties");
        System.setProperty("VoltageIdentities","VoltageIdentities.properties");
        System.setProperty("VoltageServerConnection","VoltageServerConnection.properties");
        System.setProperty("bis.platform", "NON271SAT");
        System.setProperty("com.ibm.mq.cfg.useIBMCipherMappings","false");
        Trace.isOn = true;
        //System.setProperty("SSLCIPHERSUITE","TLS_RSA_WITH_AES_128_CBC_SHA256");
        //System.setProperty("AFT_ENVIRONMENT","AFTUAT");
        //System.setProperty("AFT_LATITUDE","40");
        //System.setProperty("AFT_LONGITUDE","74");
    }

    @Test
    public void testSmPing() throws Exception{
        String EXPECTED_RESPONSE =
                "{\"aBisContext\":{\"aProperties\":[{\"aTag\":\"Application\",\"aValue\":\"JMAS\"},{\"aTag\":\"CustomerName\",\"aValue\":\"test\"},{\"aTag\":\"BusinessUnit\",\"aValue\":\"test\"},{\"aTag\":\"LoggingInformation\",\"aValue\":\"\"}]}}";

        BisContext context = getContext("JMAS","test", "test", "unit");
        SmClient smClient = new SmClient();
        SmFacade smBean = smClient.getSmBean(context, limLegacyService);
        PingReturn response = smBean.ping(context);
        assertEquals(parseResponse(response), EXPECTED_RESPONSE);
    }

    @Test
    public void testSmGetServiceLocation() throws Exception{
        String EXPECTED_RESPONSE =
                "{\"aContext\":{\"aProperties\":[{\"aTag\":\"CustomerName\",\"aValue\":\"LSP-WEST\"},{\"aTag\":\"Application\",\"aValue\":\"SM_BIS\"},{\"aTag\":\"UserID\",\"aValue\":\"rb4129\"},{\"aTag\":\"ServiceCenter\",\"aValue\":\"IN\"},{\"aTag\":\"ServiceProviderSystemRegion\",\"aValue\":\"MAPWMAI1\"},{\"aTag\":\"ServiceProvider\",\"aValue\":\"Ameritech\"},{\"aTag\":\"ServiceProviderSystem\",\"aValue\":\"ACIS\"},{\"aTag\":\"LoggingInformation\",\"aValue\":\"\"}]},\"aAddress\":{\"aLocationId\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aProviderLocationProperties\":[{\"aProviderName\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aPostalAddress\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aServiceAddress\":{\"___theValue\":{\"___aFieldedAddress\":{\"aRoute\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aBox\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aHouseNumberPrefix\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aHouseNumber\":{\"___theValue\":\"11015\",\"__discriminator\":true,\"__uninitialized\":false},\"aAssignedHouseNumber\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aHouseNumberSuffix\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aStreetDirection\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aStreetName\":{\"___theValue\":\"BROADWAY\",\"__discriminator\":true,\"__uninitialized\":false},\"aStreetThoroughfare\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aStreetNameSuffix\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aCity\":{\"___theValue\":\"INDIANAPOLIS\",\"__discriminator\":true,\"__uninitialized\":false},\"aState\":{\"___theValue\":\"IN\",\"__discriminator\":true,\"__uninitialized\":false},\"aPostalCode\":{\"___theValue\":\"46280\",\"__discriminator\":true,\"__uninitialized\":false},\"aPostalCodePlus4\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aCounty\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aCountry\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aStructureType\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aStructureValue\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aLevelType\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aLevelValue\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aUnitType\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aUnitValue\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aOriginalStreetDirection\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aOriginalStreetNameSuffix\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aCassAddressLines\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aAuxiliaryAddressLines\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aCassAdditionalInfo\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aAdditionalInfo\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aBusinessName\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aCountryCode\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aCityCode\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aServiceLocationName\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aAddressId\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aAliasName\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aAttention\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false}},\"___aUnfieldedAddress\":null,\"__discriminator\":{\"__value\":0},\"__uninitialized\":false},\"__discriminator\":true,\"__uninitialized\":false},\"aE911Address\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aSwitchSuperPopAddress\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aAddressMatchCode\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aAddressMatchCodeDescription\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aCoSwitchSuperPop\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aCentralOfficeCode\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aCentralOfficeType\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aCommunityName\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aDomSwitchPop\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aE911Exempt\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aE911NonRecurringCharge\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aE911Surcharge\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aExchangeCode\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aExco\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aGeoCode\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aHorizontalCoordinate\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aLataCode\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aLataName\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aLatitude\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aLongitude\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aLocalProviderAbbreviatedName\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aLocalProviderExchangeCode\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aLocalProviderName\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aLocalProviderNumber\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aLocalProviderServingOfficeCode\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aMailingBarCodeSuffix\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aMsaCode\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aMsaName\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aNearestDistanceColoToCo\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aNearestDistanceSuperPopToCo\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aNearestSbcColo\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aNearestSbcCoSuperPop\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aNearestSbcCoWirecenter\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aOwnedWiring\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aPostalCarrierCode\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aPrimaryDirectoryCode\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aPrimaryNpaNxx\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aQuickDialTone\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aQuickDialToneNumber\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aRateCenterCode\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aRateZone\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aRateZoneBandCode\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aSagNpa\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aSagWireCenter\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aSbcColoLsoCode\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aSbcColoWirecenter\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aSbcServingOfficeCode\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aSbcServingOfficeWirecenter\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aServingAreaDescription\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aStreetAddressGuideArea\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aSurcharge4Percent\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aSurcharge16Percent\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aSwitchDataSuperPop\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aSwitchVoiceSuperPop\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aTarCode\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aTelephoneNumber\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aVerticalCoordinate\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aWorkingServiceOnLocation\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aEcktId\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aCustomerPremiseIndicator\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aSAGAddress\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aBuildingType\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aLegalEntity\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aVideoHubOffice\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aSmartmoves\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aServingNetworkType\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aLocationType\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aCityStatePostalCodeValid\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aPublicSafetyAnsweringPointId\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aCountyId\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aExceptionCode\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aExceptionDescription\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aExtensions\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false}}]}}";

        BisContextManager bcm = new BisContextManager();
        bcm.setApplication("SM_BIS");
        bcm.setUserId("rb4129");
        bcm.setCustomerName("LSP-WEST");
        BisContext context = bcm.getBisContext();

        String btn = "3175718659";
        ObjectKey aObjectKey = new ObjectKey(btn, BisTypesObjectKeyKind.BILLINGTELEPHONENUMBER);

        SmClient smClient = new SmClient();
        SmFacade smBean = smClient.getSmBean(context, limLegacyService);
        GetServiceAddressReturn response = smBean.getServiceLocation(context,aObjectKey);
        assertEquals(parseResponse(response), EXPECTED_RESPONSE);
    }

    @Test
    public void testBimxPing() throws Exception{
        BisContext context = getContext("MVT","MVT", "test", "unit");
        BimxClient bimxClient = new BimxClient();
        BimxFacade bimxBean = bimxClient.getBimxConnection(context, limLegacyService, "","");
        com.sbc.eia.idl.bimx.PingReturn response = bimxBean.ping(context);
    }

    @Test
    @Disabled
    public void testBimxAddNotesToBillingAccount() throws Exception{
        String EXPECTED_RESPONSE =
                "{\"aContext\":{\"aProperties\":[{\"aTag\":\"Application\",\"aValue\":\"BIMX_BIS\"},{\"aTag\":\"CustomerName\",\"aValue\":\"BIMX_BIS\"}]},\"aBillingAccountContext\":{\"aProperties\":[{\"aTag\":\"\",\"aValue\":\"\"}]}}";

        BisContextManager bcm = new BisContextManager();
        bcm.setApplication("BIMX_BIS");
        bcm.setCustomerName("BIMX_BIS");
        BisContext context = bcm.getBisContext();
        CompositeObjectKey aBillingAccountKey = new CompositeObjectKey();
        aBillingAccountKey.aKeys = new ObjectKey[1];
        aBillingAccountKey.aKeys[0] = new ObjectKey();
        aBillingAccountKey.aKeys[0].aKind = "";
        aBillingAccountKey.aKeys[0].aValue = "";

        ObjectProperty[] aNoteProperties = new ObjectProperty[1];
        aNoteProperties[0] = new ObjectProperty();
        aNoteProperties[0].aTag = "";
        aNoteProperties[0].aValue = "";

        BimxClient bimxClient = new BimxClient();
        BimxFacade bimxBean = bimxClient.getBimxConnection(context, limLegacyService, "","");
        AddNotesToBillingAccountReturn response = bimxBean.addNotesToBillingAccount(context, aBillingAccountKey,"",new String[1],aNoteProperties);
        assertEquals(parseResponse(response), EXPECTED_RESPONSE);
    }

    private String parseResponse(Object obj) throws Exception {
        // Replace logging id
        return objectMapper
                .writeValueAsString(obj)
                .replaceAll(
                        "\\{\"aTag\":\"LoggingInformation\",\"aValue\":\".*\"\\}",
                        "\\{\"aTag\":\"LoggingInformation\",\"aValue\":\"\"\\}")
                .replaceAll(
                        "\"aStatusTime\":\\{\"aEiaDate\":\\{\"aYear\":\\d+,\"aMonth\":\\d+,\"aDay\":\\d+\\},\"aHour\":\\d+,\"aMinute\":\\d+,\"aSecond\":\\d+,\"aMilliSeconds\":\\d+,\"UTCOffset\":-?\\d+\\}",
                        "\"aStatusTime\":\\{\\}");
    }
}
