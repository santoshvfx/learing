package com.att.lms.bis.http.nonprod;

import com.att.lms.bis.common.BisApi;
import com.att.lms.bis.common.CommonApi;
import com.att.lms.bis.controller.LimController;
import com.att.lms.bis.dto.lim.UpdateBillingAddressRequestDto;
import com.att.lms.bis.AbstractControllerE2E;
import com.att.lms.bis.security.User;
import com.sbc.eia.bis.framework.BisContextManager;
import com.sbc.eia.idl.lim_types.Address;
import com.sbc.eia.idl.lim_types.AddressOpt;
import com.sbc.eia.idl.types.CompositeObjectKey;
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
public class UpdateBillingAddressTestE2E extends AbstractControllerE2E {

    @ParameterizedTest
    @MethodSource("com.att.lms.bis.security.ParameterizedDataForPrototypicalSpringSecurityAuthenticationAuthorizationIT#usersToExpectedSuccessfulAuthResultsMappings")
    public void shouldReturnValidResponse(User user, int httpStatus) throws Exception {

        BisContextManager bcm = new BisContextManager();
        bcm.setApplication("LIM_BIS");
        bcm.setCustomerName("LIM_BIS");

        CompositeObjectKey aBillingAccountKey = new CompositeObjectKey();
        StringOpt aContactName = new StringOpt();
        Address aNewAddress = new Address();
        AddressOpt aOldAddress = new AddressOpt();
        StringOpt aDeliveryPointValidationCode = new StringOpt();

        UpdateBillingAddressRequestDto request = UpdateBillingAddressRequestDto.builder()
                .aBisContext(bcm.getBisContext())
                .aBillingAccountKey(aBillingAccountKey)
                .aContactName(aContactName)
                .aNewAddress(aNewAddress)
                .aOldAddress(aOldAddress)
                .aDeliveryPointValidationCode(aDeliveryPointValidationCode)
                .build();

        String requestBodyAsJSON = objectMapper.writeValueAsString(request);

        given(documentationSpec)
                .header(CommonApi.Headers.APPLICATION_ID, "BIS")
                .header(CommonApi.Headers.CORRELATION_ID, UUID.randomUUID().toString())
                .accept(APPLICATION_JSON.toString())
                .filter(document("updateBillingAddress",
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
                        LimController.UPDATE_BILLING_ADDRESS_PATH_NAME))
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
                subsectionWithPath("aBillingAccountKey").description("Billing Account Key"),
                subsectionWithPath("aContactName").description("Contact Name"),
                subsectionWithPath("aNewAddress").description("New Address"),
                subsectionWithPath("aOldAddress").description("Old Address"),
                subsectionWithPath("aDeliveryPointValidationCode").description("Delivery Point Validation Code").optional(),
                subsectionWithPath("aContactName").description("Contact Name").optional()
        };
    }

    private FieldDescriptor[] getResponseFieldDescriptor() {
        return new FieldDescriptor[] {
                fieldWithPath("aContext").description("Context"),
                fieldWithPath("aContext.aProperties[].aTag").description("Context Property Tag"),
                fieldWithPath("aContext.aProperties[].aValue").description("Context Property Value"),
                subsectionWithPath("aCode").description("Code"),
                subsectionWithPath("aMessage").description("Message")
        };
    }
}
