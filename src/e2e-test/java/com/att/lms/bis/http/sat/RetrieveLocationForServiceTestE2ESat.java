package com.att.lms.bis.http.sat;

import com.att.lms.bis.AbstractControllerE2E;
import com.att.lms.bis.common.BisApi;
import com.att.lms.bis.common.CommonApi;
import com.att.lms.bis.controller.LimController;
import com.att.lms.bis.dto.lim.RetrieveLocationForServiceRequestDto;
import com.att.lms.bis.security.User;
import com.sbc.bccs.idl.helpers.ObjectPropertyManager;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.bis_types.BisContextProperty;
import com.sbc.eia.idl.bis_types.TelephoneNumber;
import com.sbc.eia.idl.lim_types.ProviderLocationProperties;
import com.sbc.eia.idl.lim_types.ProviderLocationPropertiesSeqOpt;
import com.sbc.eia.idl.types.LongOpt;
import io.restassured.http.ContentType;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.test.context.TestPropertySource;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.restassured3.RestAssuredRestDocumentation.document;

@TestPropertySource(locations = { "classpath:application-e2e-test-sat.properties", "classpath:application-e2e-real.properties" })
public class RetrieveLocationForServiceTestE2ESat extends AbstractControllerE2E {

    @ParameterizedTest
    @MethodSource("com.att.lms.bis.security.ParameterizedDataForPrototypicalSpringSecurityAuthenticationAuthorizationIT#usersToExpectedSuccessfulAuthResultsMappings")
    public void shouldReturnValidResponse(User user, int httpStatus) throws Exception {

        String EXPECTED_RESPONSE =
                "{\"aContext\":{\"aProperties\":[{\"aTag\":\"Application\",\"aValue\":\"LIM_BIS\"},{\"aTag\":\"CustomerName\",\"aValue\":\"ZZZZ\"},{\"aTag\":\"ServiceCenter\",\"aValue\":\"IN\"}]},\"aAddressMatchResult\":{\"___aLocation\":{\"aLocationId\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aProviderLocationProperties\":[]},\"___aAlternativeAddressResult\":{\"aAlternativeAddresses\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aSubmittedAddressExceptions\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false}},\"__discriminator\":{\"__value\":1},\"__uninitialized\":false}}";

        ObjectPropertyManager opm = new ObjectPropertyManager();
//        opm.addWithOverride(BisContextProperty.BUSINESSUNIT, "Claims");
        opm.addWithOverride(BisContextProperty.APPLICATION, "LIM_BIS");
        opm.addWithOverride(BisContextProperty.CUSTOMERNAME, "ZZZZ");
        opm.addWithOverride(BisContextProperty.SERVICECENTER, "IN");
        BisContext aBisContext = new BisContext(opm.toArray());

//        BisContext aBisContext = getBisContext("LIM_BIS", "LIM_BIS");

        TelephoneNumber aTelephoneNumber = new TelephoneNumber();
        aTelephoneNumber.setANpa("920");
        aTelephoneNumber.setANxx("563");
        aTelephoneNumber.setALine("2343");
//        aTelephoneNumber.setAExtension("8");
        ProviderLocationPropertiesSeqOpt aProviderLocationProperties = new ProviderLocationPropertiesSeqOpt();
        ProviderLocationProperties[] props = new ProviderLocationProperties[1];
        props[0] = new ProviderLocationProperties();
        aProviderLocationProperties.theValue(props);
        LongOpt aMaxBasicAddressAlternatives = new LongOpt();
        aMaxBasicAddressAlternatives.theValue(5);
        LongOpt aMaxLivingUnitAlternatives = new LongOpt();
        aMaxLivingUnitAlternatives.theValue(5);

        RetrieveLocationForServiceRequestDto request = RetrieveLocationForServiceRequestDto.builder()
                .aBisContext(aBisContext)
                .aTelephoneNumber(aTelephoneNumber)
                .aProviderLocationProperties(aProviderLocationProperties)
                .aMaxBasicAddressAlternatives(aMaxBasicAddressAlternatives)
                .aMaxLivingUnitAlternatives(aMaxLivingUnitAlternatives)
                .build();

        String requestBodyAsJSON = objectMapper.writeValueAsString(request);

        String responseBody = given(documentationSpec)
                .header(CommonApi.Headers.APPLICATION_ID, "BIS")
                .header(CommonApi.Headers.CORRELATION_ID, UUID.randomUUID().toString())
                .accept(APPLICATION_JSON.toString())
                .filter(document("retrieveLocationForService-sat",
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
                        LimController.RETRIEVE_LOCATION_FOR_SERVICE_PATH_NAME))
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
                subsectionWithPath("aMaxBasicAddressAlternatives").description("Max Basic Address Alternatives"),
                subsectionWithPath("aMaxLivingUnitAlternatives").description("Max Living Unit Alternatives"),
                subsectionWithPath("aTelephoneNumber").description("Telephone Number"),
                subsectionWithPath("aProviderLocationProperties").description("Provider Location Properties")
        };
    }

    private FieldDescriptor[] getResponseFieldDescriptor() {
        return new FieldDescriptor[] {
                fieldWithPath("aContext").description("Context"),
                fieldWithPath("aContext.aProperties[].aTag").description("Context Properties Tag"),
                fieldWithPath("aContext.aProperties[].aValue").description("Context Properties Value"),
                subsectionWithPath("aAddressMatchResult").description("Address Match Result")
        };
    }
}