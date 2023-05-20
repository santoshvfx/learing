package com.att.lms.bis.http.clec;

import com.att.lms.bis.AbstractControllerE2E;
import com.att.lms.bis.common.BisApi;
import com.att.lms.bis.common.CommonApi;
import com.att.lms.bis.controller.LimController;
import com.att.lms.bis.dto.lim.RetrieveLocationForServiceAddressRequestDto;
import com.att.lms.bis.security.User;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.lim_types.Address;
import com.sbc.eia.idl.lim_types.FieldedAddress;
import com.sbc.eia.idl.lim_types.LocationPropertiesToGetSeqOpt;
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
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.restassured3.RestAssuredRestDocumentation.document;

@TestPropertySource(locations = { "classpath:application-e2e-test-clec.properties", "classpath:application-e2e-real.properties" })
public class RetrieveLocationForServiceAddressTestE2EClec extends AbstractControllerE2E {

    @ParameterizedTest
    @MethodSource("com.att.lms.bis.security.ParameterizedDataForPrototypicalSpringSecurityAuthenticationAuthorizationIT#usersToExpectedSuccessfulAuthResultsMappings")
    public void shouldReturnValidResponse(User user, int httpStatus) throws Exception {

        BisContext aBisContext = getBisContext("LIM_BIS", "LIM_BIS");

        Address aAddress = new Address();
        FieldedAddress fieldedAddress = new FieldedAddress();
        fieldedAddress.aRoute = new StringOpt();
        fieldedAddress.aBox = new StringOpt();
        fieldedAddress.aHouseNumberPrefix = new StringOpt();
        fieldedAddress.aHouseNumber = toStringOpt("1110");
        fieldedAddress.aAssignedHouseNumber = new StringOpt();
        fieldedAddress.aHouseNumberSuffix = new StringOpt();
        fieldedAddress.aStreetDirection = toStringOpt("");
        fieldedAddress.aStreetName = toStringOpt("HARBOUR ISLAND RD");
        fieldedAddress.aStreetThoroughfare = new StringOpt();
        fieldedAddress.aStreetNameSuffix = new StringOpt();
        fieldedAddress.aCity = toStringOpt("EDGEWOOD");
        fieldedAddress.aState = toStringOpt("FL");
        fieldedAddress.aPostalCode = toStringOpt("32809");
        fieldedAddress.aPostalCodePlus4 = toStringOpt("");
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

        UnfieldedAddress unfieldedAddress = new UnfieldedAddress();
//        fieldedAddress.aHouseNumber = toStringOpt("100");
//        fieldedAddress.aAssignedHouseNumber = new StringOpt();
//        fieldedAddress.aHouseNumberSuffix = new StringOpt();
//        fieldedAddress.aStreetDirection = toStringOpt("S");
//        fieldedAddress.aStreetName = toStringOpt("S MAIN ST");
//        fieldedAddress.aStreetThoroughfare = new StringOpt();
//        fieldedAddress.aStreetNameSuffix = new StringOpt();
        unfieldedAddress.aAddressLines = new StringSeqOpt();
        unfieldedAddress.aAddressLines.theValue(new String[]{"1110 HARBOUR ISLAND RD"});
        unfieldedAddress.aCity = toStringOpt("EDGEWOOD");
        unfieldedAddress.aState = toStringOpt("FL");
        unfieldedAddress.aPostalCode = toStringOpt("32809");
        unfieldedAddress.aPostalCodePlus4 = toStringOpt("");
        unfieldedAddress.aCounty = new StringOpt();
        unfieldedAddress.aCountry = new StringOpt();
        unfieldedAddress.aStructureType = new StringOpt();
        unfieldedAddress.aStructureValue = new StringOpt();
        unfieldedAddress.aLevelType = new StringOpt();
        unfieldedAddress.aLevelValue = new StringOpt();
        unfieldedAddress.aUnitType = new StringOpt();
        unfieldedAddress.aUnitValue = new StringOpt();
        unfieldedAddress.aAdditionalInfo = new StringOpt();
        unfieldedAddress.aBusinessName = new StringOpt();
        unfieldedAddress.aCountryCode = new StringOpt();
        unfieldedAddress.aCityCode = new StringOpt();
        unfieldedAddress.aServiceLocationName = new StringOpt();
        unfieldedAddress.aAddressId = new StringOpt();
        unfieldedAddress.aAliasName = new StringOpt();
        unfieldedAddress.aAttention = new StringOpt();
        aAddress.aUnfieldedAddress(unfieldedAddress);
        String aLocationProperties[] = new String[1];
        aLocationProperties[0] = "City";
        LocationPropertiesToGetSeqOpt aLocationPropertiesToGet = new LocationPropertiesToGetSeqOpt();
        aLocationPropertiesToGet.theValue(aLocationProperties);
        StringOpt aPreviousCustomerName = new StringOpt();
        StringOpt aCrossBoundaryState = new StringOpt();
        LongOpt aMaxBasicAddressAlternatives = new LongOpt();
        aMaxBasicAddressAlternatives.theValue(5);
        LongOpt aMaxLivingUnitAlternatives = new LongOpt();
        aMaxLivingUnitAlternatives.theValue(5);

        RetrieveLocationForServiceAddressRequestDto request = RetrieveLocationForServiceAddressRequestDto.builder()
                .aBisContext(aBisContext)
                .aAddress(aAddress)
                .aLocationPropertiesToGet(aLocationPropertiesToGet)
                .aPreviousCustomerName(aPreviousCustomerName)
                .aCrossBoundaryState(aCrossBoundaryState)
                .aMaxBasicAddressAlternatives(aMaxBasicAddressAlternatives)
                .aMaxLivingUnitAlternatives(aMaxLivingUnitAlternatives)
                .build();

        String requestBodyAsJSON = objectMapper.writeValueAsString(request);

        given(documentationSpec)
                .header(CommonApi.Headers.APPLICATION_ID, "BIS")
                .header(CommonApi.Headers.CORRELATION_ID, UUID.randomUUID().toString())
                .accept(APPLICATION_JSON.toString())
                .filter(document("retrieveLocationForServiceAddress-clec",
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
                        LimController.RETRIEVE_LOCATION_FOR_SERVICE_ADDRESS_PATH_NAME))
                .then()
                .statusCode(httpStatus)
                .extract()
                .response()
                .body();

    }

    private FieldDescriptor[] getRequestFieldDescriptor() {
        return new FieldDescriptor[] {
                fieldWithPath("aBisContext.aProperties[].aTag").description("BIS Context Property Tag"),
                fieldWithPath("aBisContext.aProperties[].aValue").description("BIS Context Property Value"),
                subsectionWithPath("aPreviousCustomerName").description("Previous Customer Name"),
                subsectionWithPath("aCrossBoundaryState").description("Cross Boundary State"),
                subsectionWithPath("aMaxBasicAddressAlternatives").description("Max Basic Address Alternatives"),
                subsectionWithPath("aMaxLivingUnitAlternatives").description("Max Living Unit Alternatives"),
                subsectionWithPath("aAddress").description("Address"),
                subsectionWithPath("aLocationPropertiesToGet").description("Location Properties To Get"),
        };
    }

    private FieldDescriptor[] getResponseFieldDescriptor() {
        return new FieldDescriptor[] {
                fieldWithPath("aContext.aProperties[].aTag").description("Context Properties Tag"),
                fieldWithPath("aContext.aProperties[].aValue").description("Context Properties Value"),
                subsectionWithPath("aServiceAddressMatchResult").description("Service Address Match Result"),
        };
    }

    private static StringOpt toStringOpt(String val) {
        StringOpt opt = new StringOpt();
        opt.theValue(val);
        return opt;
    }

}
