package com.att.lms.bis.http.satprod;

import com.att.lms.bis.AbstractControllerE2E;
import com.att.lms.bis.common.BisApi;
import com.att.lms.bis.common.CommonApi;
import com.att.lms.bis.controller.LimController;
import com.att.lms.bis.dto.lim.FieldAddressRequestDto;
import com.att.lms.bis.security.User;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.lim_types.UnfieldedAddress;
import io.restassured.http.ContentType;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.test.context.TestPropertySource;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.restassured3.RestAssuredRestDocumentation.document;

@TestPropertySource(locations = { "classpath:application-e2e-test-sat-prod.properties", "classpath:application-e2e-real.properties" })
public class FieldAddressTestE2ESatProd extends AbstractControllerE2E {

    @ParameterizedTest
    @MethodSource("com.att.lms.bis.security.ParameterizedDataForPrototypicalSpringSecurityAuthenticationAuthorizationIT#usersToExpectedSuccessfulAuthResultsMappings")
    public void shouldReturnValidResponse(User user, int httpStatus) throws Exception {

    String EXPECTED_RESPONSE =
        "{\"aContext\":{\"aProperties\":[{\"aTag\":\"Application\",\"aValue\":\"LIM_BIS\"},{\"aTag\":\"CustomerName\",\"aValue\":\"LIM_BIS\"},{\"aTag\":\"LoggingInformation\",\"aValue\":\"\"}]},\"aAddress\":{\"___aFieldedAddress\":{\"aRoute\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aBox\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aHouseNumberPrefix\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aHouseNumber\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aAssignedHouseNumber\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aHouseNumberSuffix\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aStreetDirection\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aStreetName\":{\"___theValue\":\"line 1 line 2 line 3\",\"__discriminator\":true,\"__uninitialized\":false},\"aStreetThoroughfare\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aStreetNameSuffix\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aCity\":{\"___theValue\":\"Syracuse\",\"__discriminator\":true,\"__uninitialized\":false},\"aState\":{\"___theValue\":\"NY\",\"__discriminator\":true,\"__uninitialized\":false},\"aPostalCode\":{\"___theValue\":\"12345\",\"__discriminator\":true,\"__uninitialized\":false},\"aPostalCodePlus4\":{\"___theValue\":\"12345-0123\",\"__discriminator\":true,\"__uninitialized\":false},\"aCounty\":{\"___theValue\":\"Syracuse\",\"__discriminator\":true,\"__uninitialized\":false},\"aCountry\":{\"___theValue\":\"USA\",\"__discriminator\":true,\"__uninitialized\":false},\"aStructureType\":{\"___theValue\":\"structType\",\"__discriminator\":true,\"__uninitialized\":false},\"aStructureValue\":{\"___theValue\":\"structValu\",\"__discriminator\":true,\"__uninitialized\":false},\"aLevelType\":{\"___theValue\":\"levelType\",\"__discriminator\":true,\"__uninitialized\":false},\"aLevelValue\":{\"___theValue\":\"unitValue\",\"__discriminator\":true,\"__uninitialized\":false},\"aUnitType\":{\"___theValue\":\"unitType\",\"__discriminator\":true,\"__uninitialized\":false},\"aUnitValue\":{\"___theValue\":\"unitValue\",\"__discriminator\":true,\"__uninitialized\":false},\"aOriginalStreetDirection\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aOriginalStreetNameSuffix\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aCassAddressLines\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aAuxiliaryAddressLines\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aCassAdditionalInfo\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aAdditionalInfo\":{\"___theValue\":\"additional\",\"__discriminator\":true,\"__uninitialized\":false},\"aBusinessName\":{\"___theValue\":\"Biz\",\"__discriminator\":true,\"__uninitialized\":false},\"aCountryCode\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aCityCode\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aServiceLocationName\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aAddressId\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aAliasName\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aAttention\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false}},\"___aUnfieldedAddress\":null,\"__discriminator\":{\"__value\":0},\"__uninitialized\":false}}";

        BisContext aBisContext = getBisContext("LIM_BIS", "LIM_BIS");

        UnfieldedAddress aUnfieldedAddress = createUnfieldedAddress();

        FieldAddressRequestDto request = FieldAddressRequestDto.builder()
                .aBisContext(aBisContext)
                .aUnfieldedAddress(aUnfieldedAddress)
                .build();

        String requestBodyAsJSON = objectMapper.writeValueAsString(request);

        String responseBody = given(documentationSpec)
                .header(CommonApi.Headers.APPLICATION_ID, "BIS")
                .header(CommonApi.Headers.CORRELATION_ID, UUID.randomUUID().toString())
                .accept(APPLICATION_JSON.toString())
                .filter(document("fieldAddress-sat-prod",
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
                        LimController.FIELD_ADDRESS_PATH_NAME))
                .then()
                .statusCode(httpStatus)
                .extract()
                .response()
                .body()
                .asString();

        assertThat(cleanResponse(responseBody)).isEqualTo(EXPECTED_RESPONSE);
    }

    private FieldDescriptor[] getRequestFieldDescriptor() {
        return new FieldDescriptor[] {
                fieldWithPath("aBisContext.aProperties[].aTag").description("BIS Context Property Tag"),
                fieldWithPath("aBisContext.aProperties[].aValue").description("BIS Context Property Value"),
                subsectionWithPath("aUnfieldedAddress").description("Unfielded Address")
        };
    }

    private FieldDescriptor[] getResponseFieldDescriptor() {
        return new FieldDescriptor[] {
                fieldWithPath("aContext").description("Context"),
                fieldWithPath("aContext.aProperties[].aTag").description("Context Property Tag"),
                fieldWithPath("aContext.aProperties[].aValue").description("Contact Property Value"),
                subsectionWithPath("aAddress").description("Address"),
        };
    }
}
