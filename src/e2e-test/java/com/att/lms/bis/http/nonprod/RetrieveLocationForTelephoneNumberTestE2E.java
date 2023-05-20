package com.att.lms.bis.http.nonprod;

import com.att.lms.bis.common.BisApi;
import com.att.lms.bis.common.CommonApi;
import com.att.lms.bis.controller.LimController;
import com.att.lms.bis.dto.lim.RetrieveLocationForTelephoneNumberRequestDto;
import com.att.lms.bis.AbstractControllerE2E;
import com.att.lms.bis.security.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.bis_types.TelephoneNumber;
import com.sbc.eia.idl.lim_types.Address;
import com.sbc.eia.idl.lim_types.LocationPropertiesToGetSeqOpt;
import com.sbc.eia.idl.lim_types.ProviderLocationPropertiesSeqOpt;
import com.sbc.eia.idl.types.LongOpt;
import com.sbc.eia.idl.types.StringOpt;
import io.restassured.http.ContentType;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.test.context.TestPropertySource;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasEntry;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.restassured3.RestAssuredRestDocumentation.document;

@TestPropertySource(locations = { "classpath:application-e2e-test.properties", "classpath:application-e2e-real.properties" })
public class RetrieveLocationForTelephoneNumberTestE2E extends AbstractControllerE2E {

    @ParameterizedTest
    @MethodSource("com.att.lms.bis.security.ParameterizedDataForPrototypicalSpringSecurityAuthenticationAuthorizationIT#usersToExpectedSuccessfulAuthResultsMappings")
    public void shouldReturnValidResponse(User user, int httpStatus) throws Exception {

        BisContext aBisContext = getBisContext("LIM_BIS", "LIM_BIS");
        String aLocationProperties[] = new String[1];
        aLocationProperties[0] = "City";
        LocationPropertiesToGetSeqOpt aLocationPropertiesToGet = new LocationPropertiesToGetSeqOpt();
        aLocationPropertiesToGet.theValue(aLocationProperties);
        TelephoneNumber aTelephoneNumber = new TelephoneNumber("555","555","5555","1234");

        RetrieveLocationForTelephoneNumberRequestDto request = RetrieveLocationForTelephoneNumberRequestDto.builder()
                .aContext(aBisContext)
                .aTelephoneNumber(aTelephoneNumber)
                .aLocationPropertiesToGet(aLocationPropertiesToGet)
                .build();

        String requestBodyAsJSON = objectMapper.writeValueAsString(request);

        given(documentationSpec)
                .header(CommonApi.Headers.APPLICATION_ID, "BIS")
                .header(CommonApi.Headers.CORRELATION_ID, UUID.randomUUID().toString())
                .accept(APPLICATION_JSON.toString())
                .filter(document("retrieveLocationForTelephoneNumber",
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
                        LimController.RETRIEVE_LOCATION_FOR_TELEPHONE_NUMBER_PATH_NAME))
                .then()
                .statusCode(httpStatus)
                .extract()
                .response()
                .body();

    }

    private FieldDescriptor[] getRequestFieldDescriptor() {
        return new FieldDescriptor[] {
                fieldWithPath("aContext.aProperties[].aTag").description("BIS Context Properties Tag"),
                fieldWithPath("aContext.aProperties[].aValue").description("BIS Context Properties Value"),
                subsectionWithPath("aLocationPropertiesToGet").description("Location Properties To Get"),
                subsectionWithPath("aTelephoneNumber").description("Telephone Number")
        };
    }

    private FieldDescriptor[] getResponseFieldDescriptor() {
        return new FieldDescriptor[] {
                fieldWithPath("aContext").description("Context"),
                fieldWithPath("aContext.aProperties[].aTag").description("Context Properties Tag"),
                fieldWithPath("aContext.aProperties[].aValue").description("Context Properties Value"),
                subsectionWithPath("aServiceLocation").description("Service Location"),
        };
    }
}
