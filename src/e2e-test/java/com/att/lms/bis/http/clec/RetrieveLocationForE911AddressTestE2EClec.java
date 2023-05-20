package com.att.lms.bis.http.clec;

import com.att.lms.bis.AbstractControllerE2E;
import com.att.lms.bis.common.BisApi;
import com.att.lms.bis.common.CommonApi;
import com.att.lms.bis.controller.LimController;
import com.att.lms.bis.dto.lim.RetrieveLocationForE911AddressRequestDto;
import com.att.lms.bis.security.User;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.lim_types.Address;
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
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.restassured3.RestAssuredRestDocumentation.document;

@TestPropertySource(locations = { "classpath:application-e2e-test-sat-prod.properties", "classpath:application-e2e-real.properties" })
public class RetrieveLocationForE911AddressTestE2EClec extends AbstractControllerE2E {

    @ParameterizedTest
    @MethodSource("com.att.lms.bis.security.ParameterizedDataForPrototypicalSpringSecurityAuthenticationAuthorizationIT#usersToExpectedSuccessfulAuthResultsMappings")
    public void shouldReturnNotImplemented(User user, int httpStatus) throws Exception {

//    String EXPECTED_RESPONSE =
//        "[{\"aContext\":{\"aProperties\":[{\"aTag\":\"Application\",\"aValue\":\"LIM_BIS\"},{\"aTag\":\"CustomerName\",\"aValue\":\"LIM_BIS\"}]},\"aExceptionData\":{\"aCode\":\"LIM-NotImplemented-00080\",\"aDescription\":\"retrieveLocationForE911Address is not implemented.\",\"aOrigination\":{\"___theValue\":\"LIM\",\"__discriminator\":true,\"__uninitialized\":false},\"aSeverity\":{\"___theValue\":{\"__value\":1},\"__discriminator\":true,\"__uninitialized\":false}}}]";

        BisContext aBisContext = getBisContext("LIM_BIS", "LIM_BIS");

        Address aAddress = new Address();
        LongOpt aMaxAddressAlternatives = new LongOpt();
        StringOpt aExchangeCode = new StringOpt();

        RetrieveLocationForE911AddressRequestDto request = RetrieveLocationForE911AddressRequestDto.builder()
                .aContext(aBisContext)
                .aAddress(aAddress)
                .aMaxAddressAlternatives(aMaxAddressAlternatives)
                .aExchangeCode(aExchangeCode)
                .build();

        String requestBodyAsJSON = objectMapper.writeValueAsString(request);

        String responseBody = given(documentationSpec)
                .header(CommonApi.Headers.APPLICATION_ID, "BIS")
                .header(CommonApi.Headers.CORRELATION_ID, UUID.randomUUID().toString())
                .accept(APPLICATION_JSON.toString())
                .filter(document("retrieveLocationForE911Address-sat-prod",
                        preprocessRequest(maskCredentials(),prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestFields(getRequestFieldDescriptor()),
                        responseFields(getErrorResponseFieldDescriptor())))
                .auth()
                .preemptive()
                .basic(user.getUserId(), user.getPassword())
                .contentType(ContentType.JSON)
                .when()
                .request().body(requestBodyAsJSON)
                .post(StringUtils.join(DEFAULT_SPRING_BOOT_BASE_URI,
                        BisApi.LIM_BASE_URL,
                        "/",
                        LimController.RETRIEVE_LOCATION_FOR_E911_ADDRESS_PATH_NAME))
                .then()
                .statusCode(500)
                .extract()
                .response()
                .body()
                .asString();

//        assertThat(cleanResponse(responseBody)).isEqualTo(EXPECTED_RESPONSE);
    }


    private FieldDescriptor[] getRequestFieldDescriptor() {
        return new FieldDescriptor[] {
                fieldWithPath("aContext.aProperties[].aTag").description("BIS Context Properties Tag"),
                fieldWithPath("aContext.aProperties[].aValue").description("BIS Context Properties Value"),
                subsectionWithPath("aMaxAddressAlternatives").description("Max Basic Address Alternatives"),
                subsectionWithPath("aAddress").description("Address"),
                subsectionWithPath("aExchangeCode").description("Exchange Code"),
        };
    }
}
