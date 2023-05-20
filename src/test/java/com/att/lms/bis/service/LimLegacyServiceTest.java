package com.att.lms.bis.service;

import com.att.lms.bis.rest.service.SmRestService;
import com.att.transformation.BaseJUnit5Test;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LimLegacyServiceTest extends BaseJUnit5Test {

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
    public void pingTest() throws Exception {
        BisContext context = getContext("test", "test", "unit");
        limLegacyService.ping(context);
    }

    @Test
    public void selfTest() throws Exception {
        BisContext context = getContext("JMAS","test", "test", "unit");
        limLegacyService.selfTest(context);
    }

    @Test
    public void testFieldAddress() throws Exception {

            UnfieldedAddress addr = createUnfieldedAddress();
            BisContext context = getContext("name", "Claims", "info");
            FieldAddressReturn fieldAddr = limLegacyService.fieldAddress(context, addr);
            assertEquals("Syracuse", fieldAddr.aAddress.aFieldedAddress().aCity.theValue());
            //Assert.assertArrayEquals(new String[] {"line 1","line 2","line 3"},fieldAddr.aAddress.aFieldedAddress().aCassAddressLines.theValue());
    }

    @Test
    public void testRetrieveLocationForAddress() throws Exception {

            UnfieldedAddress addr = createUnfieldedAddress();
            BisContext context = getContext("name", "Claims", "info");
            FieldAddressReturn fieldAddr = limLegacyService.fieldAddress(context, addr);

            RetrieveLocationForAddressReturn location =
                    limLegacyService.retrieveLocationForAddress(
                            context,
                            fieldAddr.aAddress,
                            propSeq(provider("test provider", "loc1", "loc2")),
                            lng(5), lng(5), str("code"));
            assertEquals(1, location.aAddressMatchResult.aLocation().aProviderLocationProperties.length);

    }

    @Test
    public void testRetrieveLocationForService() throws Exception {

            BisContext context = getContext("name", "Claims", "info");
            TelephoneNumber phoneNum = new TelephoneNumber("317", "571", "8659", "8");

            RetrieveLocationForServiceReturn location =
                    limLegacyService.retrieveLocationForService(
                            context,
                            phoneNum,
                            propSeq(provider("test provider", "loc1", "loc2")),
                            lng(5), lng(5));
            //Assert.assertEquals(1,location.aAddressMatchResult.aLocation().aProviderLocationProperties.length);
    }

    /*
    @Test(expected = NotImplemented.class)
    public void testRetrieveServiceAreaByPostalCode() throws Throwable {
        UnfieldedAddress addr = createUnfieldedAddress();
        BisContext context = getContext("name", "Claims", "info");
        FieldAddressReturn fieldAddr = limLegacyService.fieldAddress(context, addr);

        RetrieveServiceAreaByPostalCodeReturn service =
                limLegacyService.retrieveServiceAreaByPostalCode(
                        context,
                        "1",
                        "12531",
                        true,
                        false);
        Assert.fail("Should have thrown a NotImplemented Exception");
    }

    @Test(expected = NotImplemented.class)
    public void testRetrieveLocationForE911Address() throws Throwable {
        UnfieldedAddress addr = createUnfieldedAddress();
        BisContext context = getContext("name", "Claims", "info");
        FieldAddressReturn fieldAddr = limLegacyService.fieldAddress(context, addr);

        RetrieveLocationForE911AddressReturn location =
                limLegacyService.retrieveLocationForE911Address(
                        context,
                        fieldAddr.aAddress,
                        str("845"),
                        lng(5));
        Assert.fail("Should have thrown a NotImplemented Exception");

    }

    @Test(expected=NotImplemented.class)
    public void testRetrieveLocationForGeoAddress() throws Throwable {
        UnfieldedAddress addr = createUnfieldedAddress();
        BisContext context = getContext("name", "Claims", "info");
        FieldAddressReturn fieldAddr = limLegacyService.fieldAddress(context, addr);

        RetrieveLocationForGeoAddressReturn location =
                limLegacyService.retrieveLocationForGeoAddress(
                        context,
                        fieldAddr.aAddress,
                        lng(5));
        Assert.fail("Should have thrown NotImplemented exception");
    }*/

    @Test
    public void testRetrieveLocationForPostalAddress() throws Exception {

            UnfieldedAddress addr = createUnfieldedAddress();
            BisContext context = getContext("name", "Claims", "info");
            FieldAddressReturn fieldAddr = limLegacyService.fieldAddress(context, addr);

            RetrieveLocationForPostalAddressReturn location =
                    limLegacyService.retrieveLocationForPostalAddress(
                            context,
                            fieldAddr.aAddress,
                            lng(5));
            assertEquals(fieldAddr, location.aPostalAddressMatchResult.aPostalLocation().aPostalAddress.aFieldedAddress());
    }
/*
    @Test(expected = NotImplemented.class)
    public void testRetrieveLocationForPostalAddress2() throws Throwable {
        UnfieldedAddress addr = createUnfieldedAddress();
        BisContext context = getContext("name", "Claims", "info");
        FieldAddressReturn fieldAddr = limLegacyService.fieldAddress(context, addr);

        RetrieveLocationForPostalAddress2Return location =
                limLegacyService.retrieveLocationForPostalAddress2(
                        context,
                        fieldAddr.aAddress,
                        lng(2),
                        lng(5));
        Assert.fail("Should have thrown a NotImplemented Exception");
    }*/

    @Test
    public void testRetrieveLocationForServiceAddress() throws Exception {

            UnfieldedAddress addr = createUnfieldedAddress();
            BisContext context = getContext("name", "Claims", "info");
            FieldAddressReturn fieldAddr = limLegacyService.fieldAddress(context, addr);

            RetrieveLocationForServiceAddressReturn location =
                    limLegacyService.retrieveLocationForServiceAddress(
                            context,
                            fieldAddr.aAddress,
                            locSeq("loc1", "loc2", "loc3"),
                            str("previous customer"), str("NY"),
                            lng(2), lng(3));
//            Assert.assertNotNull(location);
            assertEquals(fieldAddr, location.aServiceAddressMatchResult.aServiceLocation().aServiceAddress.aFieldedAddress());
    }

    @Test
    public void testRetrieveLocationForTelephoneNumberReturn() throws Exception {

            BisContext context = getContext("name", "Claims", "info");
            TelephoneNumber phoneNum = new TelephoneNumber("1", "845", "555-5555", "8");

            RetrieveLocationForTelephoneNumberReturn location =
                    limLegacyService.retrieveLocationForTelephoneNumber(
                            context,
                            phoneNum,
                            locSeq("first", "second", "third"));
//            Assert.assertNotNull(location);
            //Assert.assertEquals(phoneNum.aLine,location.aServiceLocation.aTelephoneNumber.theValue());
    }

    @Test
    public void testUpdateBillingAddress() throws Exception {

            UnfieldedAddress addr = createUnfieldedAddress();
            BisContext context = getContext("name", "Claims", "info");
            FieldAddressReturn fieldAddr = limLegacyService.fieldAddress(context, addr);

            UpdateBillingAddressReturn updated =
                    limLegacyService.updateBillingAddress(
                            context,
                            keys(),
                            addr(fieldAddr.aAddress),
                            fieldAddr.aAddress,
                            str("123"), str("John Smith"));
    }

    private UnfieldedAddress createUnfieldedAddress() {
        return new UnfieldedAddress(
                seq("line 1", "line 2", "line 3"),
                str("Syracuse"),
                str("NY"),
                str("12345"),
                str("12345-0123"),
                str("Syracuse"),
                str("USA"),
                str("structType"),
                str("structValue"),
                str("levelType"),
                str("unitValue"),
                str("unitType"),
                str("unitValue"),
                str("additional"),
                str("Biz"),
                str("US"),
                str("cityCode"),
                str("svcLoc"),
                str("addId"),
                str("alias"),
                str("attn"));
    }

    private StringSeqOpt seq(String... seq) {
        StringSeqOpt opt = new StringSeqOpt();
        opt.theValue(seq);
        return opt;
    }

    private StringOpt str(String value) {
        StringOpt opt = new StringOpt();
        opt.theValue(value);
        return opt;
    }

    private LongOpt lng(int value) {
        LongOpt opt = new LongOpt();
        opt.theValue(value);
        return opt;
    }

    private ProviderLocationProperties provider(String providerName, String... locationsToGet) {
        return new ProviderLocationProperties(str(providerName), locationsToGet);
    }

    private ProviderLocationPropertiesSeqOpt propSeq(ProviderLocationProperties... props) {
        ProviderLocationPropertiesSeqOpt opt = new ProviderLocationPropertiesSeqOpt();
        opt.theValue(props);
        return opt;
    }

    private LocationPropertiesToGetSeqOpt locSeq(String... props) {
        LocationPropertiesToGetSeqOpt opt = new LocationPropertiesToGetSeqOpt();
        opt.theValue(props);
        return opt;
    }

    private AddressOpt addr(Address address) {
        AddressOpt opt = new AddressOpt();
        opt.theValue(address);
        return opt;
    }

    private CompositeObjectKey keys() {
        ObjectKey[] keys = {key("name", "John"), key("number", "845")};
        return new CompositeObjectKey(keys);
    }

    private ObjectKey key(String value, String kind) {
        return new ObjectKey(value, kind);
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
