package com.att.lms.bis.http.nonprod;

import com.att.lms.bis.common.BisApi;
import com.att.lms.bis.common.CommonApi;
import com.att.lms.bis.controller.LimController;
import com.att.lms.bis.dto.lim.RetrieveLocationForPostalAddressRequestDto;
import com.att.lms.bis.AbstractControllerE2E;
import com.att.lms.bis.security.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.lim_types.Address;
import com.sbc.eia.idl.lim_types.FieldedAddress;
import com.sbc.eia.idl.lim_types.UnfieldedAddress;
import com.sbc.eia.idl.types.LongOpt;
import com.sbc.eia.idl.types.StringOpt;
import com.sbc.eia.idl.types.StringSeqOpt;
import io.restassured.http.ContentType;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.test.context.TestPropertySource;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasEntry;
import static org.junit.Assert.assertEquals;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.restassured3.RestAssuredRestDocumentation.document;

@TestPropertySource(locations = { "classpath:application-e2e-test.properties", "classpath:application-e2e-real.properties" })
public class RetrieveLocationForPostalAddressTestE2E extends AbstractControllerE2E {

    @ParameterizedTest
    @MethodSource("com.att.lms.bis.security.ParameterizedDataForPrototypicalSpringSecurityAuthenticationAuthorizationIT#usersToExpectedSuccessfulAuthResultsMappings")
    public void shouldReturnValidResponse(User user, int httpStatus) throws Exception {

        String EXPECTED_RESPONSE = "{\"aContext\":null,\"aPostalAddressMatchResult\":{\"___aPostalLocation\":{\"aPostalAddress\":{\"___aFieldedAddress\":{\"aRoute\":{\"___theValue\":null,\"__discriminator\":true,\"__uninitialized\":false},\"aBox\":{\"___theValue\":null,\"__discriminator\":true,\"__uninitialized\":false},\"aHouseNumberPrefix\":{\"___theValue\":null,\"__discriminator\":true,\"__uninitialized\":false},\"aHouseNumber\":{\"___theValue\":\"100\",\"__discriminator\":true,\"__uninitialized\":false},\"aAssignedHouseNumber\":{\"___theValue\":null,\"__discriminator\":true,\"__uninitialized\":false},\"aHouseNumberSuffix\":{\"___theValue\":null,\"__discriminator\":true,\"__uninitialized\":false},\"aStreetDirection\":{\"___theValue\":\"S\",\"__discriminator\":true,\"__uninitialized\":false},\"aStreetName\":{\"___theValue\":\"MAIN\",\"__discriminator\":true,\"__uninitialized\":false},\"aStreetThoroughfare\":{\"___theValue\":\"ST\",\"__discriminator\":true,\"__uninitialized\":false},\"aStreetNameSuffix\":{\"___theValue\":null,\"__discriminator\":true,\"__uninitialized\":false},\"aCity\":{\"___theValue\":\"SAINT CHARLES\",\"__discriminator\":true,\"__uninitialized\":false},\"aState\":{\"___theValue\":\"MO\",\"__discriminator\":true,\"__uninitialized\":false},\"aPostalCode\":{\"___theValue\":\"63301\",\"__discriminator\":true,\"__uninitialized\":false},\"aPostalCodePlus4\":{\"___theValue\":\"2803\",\"__discriminator\":true,\"__uninitialized\":false},\"aCounty\":{\"___theValue\":\"SAINT CHARLES\",\"__discriminator\":true,\"__uninitialized\":false},\"aCountry\":{\"___theValue\":null,\"__discriminator\":true,\"__uninitialized\":false},\"aStructureType\":{\"___theValue\":null,\"__discriminator\":true,\"__uninitialized\":false},\"aStructureValue\":{\"___theValue\":null,\"__discriminator\":true,\"__uninitialized\":false},\"aLevelType\":{\"___theValue\":null,\"__discriminator\":true,\"__uninitialized\":false},\"aLevelValue\":{\"___theValue\":null,\"__discriminator\":true,\"__uninitialized\":false},\"aUnitType\":{\"___theValue\":null,\"__discriminator\":true,\"__uninitialized\":false},\"aUnitValue\":{\"___theValue\":null,\"__discriminator\":true,\"__uninitialized\":false},\"aOriginalStreetDirection\":null,\"aOriginalStreetNameSuffix\":null,\"aCassAddressLines\":{\"___theValue\":[\"SAINT CHARLES MO  63301-2803\"],\"__discriminator\":true,\"__uninitialized\":false},\"aAuxiliaryAddressLines\":{\"___theValue\":[null],\"__discriminator\":true,\"__uninitialized\":false},\"aCassAdditionalInfo\":null,\"aAdditionalInfo\":null,\"aBusinessName\":{\"___theValue\":\"HEINZ SCHAEFER INS\",\"__discriminator\":true,\"__uninitialized\":false},\"aCountryCode\":{\"___theValue\":\"183\",\"__discriminator\":true,\"__uninitialized\":false},\"aCityCode\":null,\"aServiceLocationName\":null,\"aAddressId\":null,\"aAliasName\":null,\"aAttention\":null},\"___aUnfieldedAddress\":null,\"__discriminator\":{\"__value\":0},\"__uninitialized\":false},\"aAddressMatchCode\":\"4SA0\",\"aAddressMatchCodeDescription\":{\"___theValue\":\"Address validation successful\",\"__discriminator\":true,\"__uninitialized\":false},\"aDeliveryPointValidationCode\":{\"___theValue\":\"Y\",\"__discriminator\":true,\"__uninitialized\":false},\"aCityStatePostalCodeValid\":{\"___theValue\":null,\"__discriminator\":true,\"__uninitialized\":false}},\"___aAlternativePostalAddressResult\":null,\"__discriminator\":{\"__value\":0},\"__uninitialized\":false}}";

        BisContext aBisContext = getBisContext("LIM_BIS", "LIM_BIS");

        Address aAddress = new Address();
        LongOpt aMaxAddressAlternatives = new LongOpt();

        FieldedAddress fieldedAddress = new FieldedAddress();
        fieldedAddress.aRoute = new StringOpt();
        fieldedAddress.aBox = new StringOpt();
        fieldedAddress.aHouseNumberPrefix = new StringOpt();
        fieldedAddress.aHouseNumber = toStringOpt("100");
        fieldedAddress.aAssignedHouseNumber = new StringOpt();
        fieldedAddress.aHouseNumberSuffix = new StringOpt();
        fieldedAddress.aStreetDirection = toStringOpt("S");
        fieldedAddress.aStreetName = toStringOpt("S MAIN ST");
        fieldedAddress.aStreetThoroughfare = new StringOpt();
        fieldedAddress.aStreetNameSuffix = new StringOpt();
        fieldedAddress.aCity = toStringOpt("ST CHARLES");
        fieldedAddress.aState = toStringOpt("MO");
        fieldedAddress.aPostalCode = toStringOpt("63301");
        fieldedAddress.aPostalCodePlus4 = new StringOpt();
        fieldedAddress.aCounty = new StringOpt();
        fieldedAddress.aCountry = new StringOpt();
//        fieldedAddress.aStructureType = toStringOpt("BLDG");
//        fieldedAddress.aStructureValue = toStringOpt("A");
//        fieldedAddress.aLevelType = toStringOpt("FLR");
//        fieldedAddress.aLevelValue = toStringOpt("11");
//        fieldedAddress.aUnitType = toStringOpt("APT");
//        fieldedAddress.aUnitValue = toStringOpt("201");
        fieldedAddress.aStructureType = new StringOpt();
        fieldedAddress.aStructureValue = new StringOpt();
        fieldedAddress.aLevelType = new StringOpt();
        fieldedAddress.aLevelValue = new StringOpt();
        fieldedAddress.aUnitType = new StringOpt();
        fieldedAddress.aUnitValue = new StringOpt();
        fieldedAddress.aOriginalStreetDirection = new StringOpt();
        fieldedAddress.aOriginalStreetNameSuffix = new StringOpt();
        fieldedAddress.aCassAddressLines = new StringSeqOpt();
        fieldedAddress.aAuxiliaryAddressLines = new StringSeqOpt();
        fieldedAddress.aCassAdditionalInfo = new StringOpt();
        fieldedAddress.aAdditionalInfo = new StringOpt();
        fieldedAddress.aBusinessName = new StringOpt();
        fieldedAddress.aCountryCode = new StringOpt();
        fieldedAddress.aCityCode = new StringOpt();
        fieldedAddress.aServiceLocationName = new StringOpt();
        fieldedAddress.aAddressId = new StringOpt();
        fieldedAddress.aAliasName = new StringOpt();
        fieldedAddress.aAttention = new StringOpt();
        aAddress.aFieldedAddress(fieldedAddress);
//
//        UnfieldedAddress unfieldedAddress = new UnfieldedAddress();
//        unfieldedAddress.aAddressLines = new StringSeqOpt();
//        unfieldedAddress.aCity = new StringOpt();
//        unfieldedAddress.aState = new StringOpt();
//        unfieldedAddress.aPostalCode = new StringOpt();
//        unfieldedAddress.aPostalCodePlus4 = new StringOpt();
//        unfieldedAddress.aCounty = new StringOpt();
//        unfieldedAddress.aCountry = new StringOpt();
//        unfieldedAddress.aStructureType = new StringOpt();
//        unfieldedAddress.aStructureValue = new StringOpt();
//        unfieldedAddress.aLevelType = new StringOpt();
//        unfieldedAddress.aLevelValue = new StringOpt();
//        unfieldedAddress.aUnitType = new StringOpt();
//        unfieldedAddress.aUnitValue = new StringOpt();
//        unfieldedAddress.aAdditionalInfo = new StringOpt();
//        unfieldedAddress.aBusinessName = new StringOpt();
//        unfieldedAddress.aCountryCode = new StringOpt();
//        unfieldedAddress.aCityCode = new StringOpt();
//        unfieldedAddress.aServiceLocationName = new StringOpt();
//        unfieldedAddress.aAddressId = new StringOpt();
//        unfieldedAddress.aAliasName = new StringOpt();
//        unfieldedAddress.aAttention = new StringOpt();
//        aAddress.aUnfieldedAddress(unfieldedAddress);



        RetrieveLocationForPostalAddressRequestDto request = RetrieveLocationForPostalAddressRequestDto.builder()
                .aBisContext(aBisContext)
                .aAddress(aAddress)
                .aMaxAddressAlternatives(aMaxAddressAlternatives)
                .build();

        String requestBodyAsJSON = objectMapper.writeValueAsString(request);

        String responseBody = given(documentationSpec)
                .header(CommonApi.Headers.APPLICATION_ID, "BIS")
                .header(CommonApi.Headers.CORRELATION_ID, UUID.randomUUID().toString())
                .accept(APPLICATION_JSON.toString())
                .filter(document("retrieveLocationForPostalAddress",
                        preprocessRequest(maskCredentials(),prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestFields(getRequestFieldDescriptor()),
                        responseFields(getResponseFieldDescriptor())))
                .auth()
                .preemptive()
                .basic(user.getUserId(), user.getPassword())
                .contentType(ContentType.JSON)
                .when()
                .request().body(requestBodyAsJSON)
                .post(StringUtils.join(DEFAULT_SPRING_BOOT_BASE_URI,
                        BisApi.LIM_BASE_URL,
                        "/",
                        LimController.RETRIEVE_LOCATION_FOR_POSTAL_ADDRESS_PATH_NAME))
                .then()
                .statusCode(httpStatus)
                .extract()
                .response()
                .body()
                .asString();

        assertEquals(EXPECTED_RESPONSE, responseBody);

    }

    private FieldDescriptor[] getRequestFieldDescriptor() {
        return new FieldDescriptor[] {
                fieldWithPath("aBisContext.aProperties[].aTag").description("BIS Context Properties Tag"),
                fieldWithPath("aBisContext.aProperties[].aValue").description("BIS Context Properties Value"),
                subsectionWithPath("aMaxAddressAlternatives").description("Max Address Alternatives"),
                subsectionWithPath("aAddress").description("Address"),
        };
    }

    private FieldDescriptor[] getResponseFieldDescriptor() {
        return new FieldDescriptor[] {
                subsectionWithPath("aPostalAddressMatchResult").description("Postal Address Match Result"),
                subsectionWithPath("aContext").description("Context"),
        };
    }

    private static StringOpt toStringOpt(String val) {
        StringOpt opt = new StringOpt();
        opt.theValue(val);
        return opt;
    }
}
