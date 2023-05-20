package com.att.lms.bis.http.sat;

import com.att.lms.bis.AbstractControllerE2E;
import com.att.lms.bis.common.BisApi;
import com.att.lms.bis.common.CommonApi;
import com.att.lms.bis.controller.LimController;
import com.att.lms.bis.security.User;
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
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.subsectionWithPath;

@TestPropertySource(locations = { "classpath:application-e2e-test-sat.properties", "classpath:application-e2e-real.properties" })
public class RetrieveLocationForAddressTestE2ESat extends AbstractControllerE2E {

    @ParameterizedTest
    @MethodSource("com.att.lms.bis.security.ParameterizedDataForPrototypicalSpringSecurityAuthenticationAuthorizationIT#usersToExpectedSuccessfulAuthResultsMappings")
    public void shouldReturnValidResponse(User user, int httpStatus) throws Exception {

    String EXPECTED_RESPONSE =
        "{\"aContext\":{\"aProperties\":[{\"aTag\":\"CustomerName\",\"aValue\":\"LIM_BIS\"},{\"aTag\":\"ServiceCenter\",\"aValue\":\"OH\"},{\"aTag\":\"Application\",\"aValue\":\"LIM_BIS\"},{\"aTag\":\"BusinessUnit\",\"aValue\":\"SBCMidwest\"},{\"aTag\":\"LoggingInformation\",\"aValue\":\"\"},{\"aTag\":\"APP_NAME\",\"aValue\":\"LIM_BIS\"}]},\"aAddressMatchResult\":{\"___aLocation\":{\"aLocationId\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aProviderLocationProperties\":[{\"aProviderName\":{\"___theValue\":\"SBC\",\"__discriminator\":true,\"__uninitialized\":false},\"aPostalAddress\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aServiceAddress\":{\"___theValue\":{\"___aFieldedAddress\":{\"aRoute\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aBox\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aHouseNumberPrefix\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aHouseNumber\":{\"___theValue\":\"1236\",\"__discriminator\":true,\"__uninitialized\":false},\"aAssignedHouseNumber\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aHouseNumberSuffix\":{\"___theValue\":\"\",\"__discriminator\":true,\"__uninitialized\":false},\"aStreetDirection\":{\"___theValue\":\"\",\"__discriminator\":true,\"__uninitialized\":false},\"aStreetName\":{\"___theValue\":\"JACKSON\",\"__discriminator\":true,\"__uninitialized\":false},\"aStreetThoroughfare\":{\"___theValue\":\"AV\",\"__discriminator\":true,\"__uninitialized\":false},\"aStreetNameSuffix\":{\"___theValue\":\"\",\"__discriminator\":true,\"__uninitialized\":false},\"aCity\":{\"___theValue\":\"LAKEWOOD\",\"__discriminator\":true,\"__uninitialized\":false},\"aState\":{\"___theValue\":\"OH\",\"__discriminator\":true,\"__uninitialized\":false},\"aPostalCode\":{\"___theValue\":\"44107\",\"__discriminator\":true,\"__uninitialized\":false},\"aPostalCodePlus4\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aCounty\":{\"___theValue\":\"CUYA\",\"__discriminator\":true,\"__uninitialized\":false},\"aCountry\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aStructureType\":{\"___theValue\":\"\",\"__discriminator\":true,\"__uninitialized\":false},\"aStructureValue\":{\"___theValue\":\"\",\"__discriminator\":true,\"__uninitialized\":false},\"aLevelType\":{\"___theValue\":\"\",\"__discriminator\":true,\"__uninitialized\":false},\"aLevelValue\":{\"___theValue\":\"\",\"__discriminator\":true,\"__uninitialized\":false},\"aUnitType\":{\"___theValue\":\"\",\"__discriminator\":true,\"__uninitialized\":false},\"aUnitValue\":{\"___theValue\":\"\",\"__discriminator\":true,\"__uninitialized\":false},\"aOriginalStreetDirection\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aOriginalStreetNameSuffix\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aCassAddressLines\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aAuxiliaryAddressLines\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aCassAdditionalInfo\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aAdditionalInfo\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aBusinessName\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aCountryCode\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aCityCode\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aServiceLocationName\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aAddressId\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aAliasName\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aAttention\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false}},\"___aUnfieldedAddress\":null,\"__discriminator\":{\"__value\":0},\"__uninitialized\":false},\"__discriminator\":true,\"__uninitialized\":false},\"aE911Address\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aSwitchSuperPopAddress\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aAddressMatchCode\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aAddressMatchCodeDescription\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aCoSwitchSuperPop\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aCentralOfficeCode\":{\"___theValue\":\"52X\",\"__discriminator\":true,\"__uninitialized\":false},\"aCentralOfficeType\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aCommunityName\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aDomSwitchPop\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aE911Exempt\":{\"___theValue\":\"EX\",\"__discriminator\":true,\"__uninitialized\":false},\"aE911NonRecurringCharge\":{\"___theValue\":\"  \",\"__discriminator\":true,\"__uninitialized\":false},\"aE911Surcharge\":{\"___theValue\":\"18\",\"__discriminator\":true,\"__uninitialized\":false},\"aExchangeCode\":{\"___theValue\":\"CLEV\",\"__discriminator\":true,\"__uninitialized\":false},\"aExco\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aGeoCode\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aHorizontalCoordinate\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aLataCode\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aLataName\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aLatitude\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aLongitude\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aLocalProviderAbbreviatedName\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aLocalProviderExchangeCode\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aLocalProviderName\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aLocalProviderNumber\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aLocalProviderServingOfficeCode\":{\"___theValue\":\"216226\",\"__discriminator\":true,\"__uninitialized\":false},\"aMailingBarCodeSuffix\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aMsaCode\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aMsaName\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aNearestDistanceColoToCo\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aNearestDistanceSuperPopToCo\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aNearestSbcColo\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aNearestSbcCoSuperPop\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aNearestSbcCoWirecenter\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aOwnedWiring\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aPostalCarrierCode\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aPrimaryDirectoryCode\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aPrimaryNpaNxx\":{\"___theValue\":\"216226\",\"__discriminator\":true,\"__uninitialized\":false},\"aQuickDialTone\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aQuickDialToneNumber\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aRateCenterCode\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aRateZone\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aRateZoneBandCode\":{\"___theValue\":\" B\",\"__discriminator\":true,\"__uninitialized\":false},\"aSagNpa\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aSagWireCenter\":{\"___theValue\":\"30    \",\"__discriminator\":true,\"__uninitialized\":false},\"aSbcColoLsoCode\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aSbcColoWirecenter\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aSbcServingOfficeCode\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aSbcServingOfficeWirecenter\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aServingAreaDescription\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aStreetAddressGuideArea\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aSurcharge4Percent\":{\"___theValue\":\" \",\"__discriminator\":true,\"__uninitialized\":false},\"aSurcharge16Percent\":{\"___theValue\":\" \",\"__discriminator\":true,\"__uninitialized\":false},\"aSwitchDataSuperPop\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aSwitchVoiceSuperPop\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aTarCode\":{\"___theValue\":\"0018\",\"__discriminator\":true,\"__uninitialized\":false},\"aTelephoneNumber\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aVerticalCoordinate\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aWorkingServiceOnLocation\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aEcktId\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aCustomerPremiseIndicator\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aSAGAddress\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aBuildingType\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aLegalEntity\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aVideoHubOffice\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aSmartmoves\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aServingNetworkType\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aLocationType\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aCityStatePostalCodeValid\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aPublicSafetyAnsweringPointId\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aCountyId\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aExceptionCode\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aExceptionDescription\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false},\"aExtensions\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":false}}]},\"___aAlternativeAddressResult\":null,\"__discriminator\":{\"__value\":0},\"__uninitialized\":false}}";
//        String requestBodyAsJSON = "{\n" +
//                "    \"aBisContext\": {\n" +
//                "        \"aProperties\": [{\n" +
//                "            \"aTag\": \"Application\",\n" +
//                "            \"aValue\": \"SORD\"\n" +
//                "        }, {\n" +
//                "            \"aTag\": \"BusinessUnit\",\n" +
//                "            \"aValue\": \"SBCWest\"\n" +
//                "        },\n" +
//                "        {\n" +
//                "            \"aTag\": \"CustomerName\",\n" +
//                "            \"aValue\": \"EWBAV\"\n" +
//                "        },\n" +
//                "        {\n" +
//                "            \"aTag\": \"METHOD_NAME\",\n" +
//                "            \"aValue\": \"retrieveLocationForAddress\"\n" +
//                "        },\n" +
//                "        {\n" +
//                "            \"aTag\": \"APP_NAME\",\n" +
//                "            \"aValue\": \"LIM_BIS\"\n" +
//                "        },\n" +
//                "        {\n" +
//                "            \"aTag\": \"ServiceCenter\",\n" +
//                "            \"aValue\": \"CA  \"\n" +
//                "        },\n" +
//                "        {\n" +
//                "            \"aTag\": \"MaxCassCharPerLine\",\n" +
//                "            \"aValue\": \"28\"\n" +
//                "        },\n" +
//                "        {\n" +
//                "            \"aTag\": \"ServiceOrderNumber\",\n" +
//                "            \"aValue\": \"C10000876 \"\n" +
//                "        },\n" +
//                "        {\n" +
//                "            \"aTag\": \"BN2_KEYED\",\n" +
//                "            \"aValue\": \"true\"\n" +
//                "            \n" +
//                "        }]\n" +
//                "    },\n" +
//                "    \"aAddress\": {\n" +
//                "        \"___aUnfieldedAddress\": {\n" +
//                "            \"aAddressLines\": {\n" +
//                "                \"___theValue\": [\"JERRY & DEANIE RUST             \",\n" +
//                "\"ATTN SOME BODY   \",\n" +
//                "\"2325 E MCKINLEY AVE                      \",\n" +
//                "\"STE 105                              \",\n" +
//                "\"FRESNO CA  93703-3006       \"],\n" +
//                "                \"__discriminator\": true,\n" +
//                "                \"__uninitialized\": false\n" +
//                "            }\n" +
//                "        },\n" +
//                "        \"__discriminator\": {\n" +
//                "            \"__value\": 1\n" +
//                "        },\n" +
//                "        \"__uninitialized\": false\n" +
//                "    },\n" +
//                "    \"aProviderLocationProperties\": {\n" +
//                "        \"___theValue\": {\n" +
//                "            \"aProviderName\": {\n" +
//                "                \"___theValue\": \"DataServices\",\n" +
//                "                \"__discriminator\": true,\n" +
//                "                \"__uninitialized\": false\n" +
//                "            },\n" +
//                "            \"aLocationPropertiesToGet\":[\"PostalAddress\",\n" +
//                "            \"All\"]\n" +
//                "        },\n" +
//                "        \"__discriminator\": true,\n" +
//                "        \"__uninitialized\": false\n" +
//                "    },\n" +
//                "    \"aMaxBasicAddressAlternatives\": {\n" +
//                "        \"___theValue\": 20,\n" +
//                "        \"__discriminator\": true,\n" +
//                "        \"__uninitialized\": false\n" +
//                "    },\n" +
//                "    \"aMaxLivingUnitAlternatives\": {\n" +
//                "        \"___theValue\": 20,\n" +
//                "        \"__discriminator\": true,\n" +
//                "        \"__uninitialized\": false\n" +
//                "    }\n" +
//                "}";


        String request = "{ \"aBisContext\": { \"aProperties\": [ { \"aTag\": \"CustomerName\", \"aValue\": \"LIM_BIS\" }, { \"aTag\": \"ServiceCenter\", \"aValue\": \"OH\" }, { \"aTag\": \"Application\", \"aValue\": \"LIM_BIS\" }, { \"aTag\": \"LoggingInformation\", \"aValue\": \"CHRIS567r\" }, { \"aTag\": \"BusinessUnit\", \"aValue\": \"SBCMidwest\" } ] }, \"aAddress\": { \"___aFieldedAddress\": { \"aRoute\": { \"___theValue\": null, \"__discriminator\": false, \"__uninitialized\": false }, \"aBox\": { \"___theValue\": null, \"__discriminator\": false, \"__uninitialized\": false }, \"aHouseNumberPrefix\": { \"___theValue\": null, \"__discriminator\": false, \"__uninitialized\": false }, \"aHouseNumber\": { \"___theValue\": \"1236\", \"__discriminator\": true, \"__uninitialized\": false }, \"aAssignedHouseNumber\": { \"___theValue\": null, \"__discriminator\": false, \"__uninitialized\": false }, \"aHouseNumberSuffix\": { \"___theValue\": null, \"__discriminator\": false, \"__uninitialized\": false }, \"aStreetDirection\": { \"___theValue\": null, \"__discriminator\": false, \"__uninitialized\": false }, \"aStreetName\": { \"___theValue\": \"JACKSON\", \"__discriminator\": true, \"__uninitialized\": false }, \"aStreetThoroughfare\": { \"___theValue\": \"AV\", \"__discriminator\": true, \"__uninitialized\": false }, \"aStreetNameSuffix\": { \"___theValue\": null, \"__discriminator\": false, \"__uninitialized\": false }, \"aCity\": { \"___theValue\": \"LKWD\", \"__discriminator\": true, \"__uninitialized\": false }, \"aState\": { \"___theValue\": \"OH\", \"__discriminator\": true, \"__uninitialized\": false }, \"aPostalCode\": { \"___theValue\": \"44107\", \"__discriminator\": true, \"__uninitialized\": false }, \"aPostalCodePlus4\": { \"___theValue\": null, \"__discriminator\": false, \"__uninitialized\": false }, \"aCounty\": { \"___theValue\": null, \"__discriminator\": false, \"__uninitialized\": false }, \"aCountry\": { \"___theValue\": \"null\", \"__discriminator\": true, \"__uninitialized\": false }, \"aStructureType\": { \"___theValue\": null, \"__discriminator\": false, \"__uninitialized\": false }, \"aStructureValue\": { \"___theValue\": null, \"__discriminator\": false, \"__uninitialized\": false }, \"aLevelType\": { \"___theValue\": null, \"__discriminator\": false, \"__uninitialized\": false }, \"aLevelValue\": { \"___theValue\": null, \"__discriminator\": false, \"__uninitialized\": false }, \"aUnitType\": { \"___theValue\": null, \"__discriminator\": false, \"__uninitialized\": false }, \"aUnitValue\": { \"___theValue\": null, \"__discriminator\": false, \"__uninitialized\": false }, \"aOriginalStreetDirection\": { \"___theValue\": null, \"__discriminator\": false, \"__uninitialized\": false }, \"aOriginalStreetNameSuffix\": { \"___theValue\": null, \"__discriminator\": false, \"__uninitialized\": false }, \"aCassAddressLines\": { \"___theValue\": null, \"__discriminator\": false, \"__uninitialized\": false }, \"aAuxiliaryAddressLines\": { \"___theValue\": null, \"__discriminator\": false, \"__uninitialized\": false }, \"aCassAdditionalInfo\": { \"___theValue\": null, \"__discriminator\": false, \"__uninitialized\": false }, \"aAdditionalInfo\": { \"___theValue\": null, \"__discriminator\": false, \"__uninitialized\": false }, \"aBusinessName\": { \"___theValue\": null, \"__discriminator\": false, \"__uninitialized\": false }, \"aCountryCode\": { \"___theValue\": null, \"__discriminator\": false, \"__uninitialized\": false }, \"aCityCode\": { \"___theValue\": null, \"__discriminator\": false, \"__uninitialized\": false }, \"aServiceLocationName\": { \"___theValue\": null, \"__discriminator\": false, \"__uninitialized\": false }, \"aAddressId\": { \"___theValue\": null, \"__discriminator\": false, \"__uninitialized\": false }, \"aAliasName\": { \"___theValue\": null, \"__discriminator\": false, \"__uninitialized\": false }, \"aAttention\": { \"___theValue\": null, \"__discriminator\": false, \"__uninitialized\": false } }, \"___aUnfieldedAddress\": null, \"__discriminator\": { \"__value\": 0 }, \"__uninitialized\": false }, \"aProviderLocationProperties\": { \"___theValue\": [ { \"aProviderName\": { \"___theValue\": \"SBC\", \"__discriminator\": true, \"__uninitialized\": false }, \"aLocationPropertiesToGet\": [ \"ServiceAddress\", \"All\" ] } ], \"__discriminator\": true, \"__uninitialized\": false }, \"aMaxBasicAddressAlternatives\": { \"___theValue\": 56, \"__discriminator\": true, \"__uninitialized\": false }, \"aMaxLivingUnitAlternatives\": { \"___theValue\": 70, \"__discriminator\": true, \"__uninitialized\": false }, \"aExchangeCode\": { \"___theValue\": null, \"__discriminator\": false, \"__uninitialized\": false } }";

        String response = given(documentationSpec)
                .header(CommonApi.Headers.APPLICATION_ID, "BIS")
                .header(CommonApi.Headers.CORRELATION_ID, UUID.randomUUID().toString())
                .accept(APPLICATION_JSON.toString())
//                .filter(document("retrieveLocationForAddress-sat",
//                        preprocessRequest(maskCredentials(),prettyPrint()),
//                        preprocessResponse(prettyPrint()),
//                        requestFields(getRequestFieldDescriptor()),
//                        responseFields(getResponseFieldDescriptor())))
                .auth()
                .preemptive()
                .basic(user.getUserId(), user.getPassword())
                .contentType(ContentType.JSON)
                .when()
                .request().body(request)
                .post(StringUtils.join(DEFAULT_SPRING_BOOT_BASE_URI,
                        BisApi.LIM_BASE_URL,
                        "/",
                        LimController.RETRIEVE_LOCATION_FOR_ADDRESS_PATH_NAME))
                .then()
                .statusCode(httpStatus)
                .extract()
                .response()
                .body()
                .asString();

        assertEquals(cleanResponse(response), EXPECTED_RESPONSE);

    }

    private FieldDescriptor[] getRequestFieldDescriptor() {
        return new FieldDescriptor[] {
                subsectionWithPath("aProviderLocationProperties").description("Provider Location"),
                fieldWithPath("aBisContext.aProperties[].aTag").description("BIS Context Properties Tag"),
                fieldWithPath("aBisContext.aProperties[].aValue").description("BIS Context Properties Value"),
                subsectionWithPath("aMaxBasicAddressAlternatives").description("Max Basic Address Alternatives"),
                subsectionWithPath("aMaxLivingUnitAlternatives").description("Max Living Unit Alternatives"),
                subsectionWithPath("aAddress").description("Address"),
                subsectionWithPath("aExchangeCode").description("Exchange Code Discriminator"),
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