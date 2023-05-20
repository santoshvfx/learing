package com.att.lms.bis;

import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.restassured3.RestAssuredRestDocumentation.documentationConfiguration;


import com.att.lms.bis.EndtoEndTestApplication;
import com.att.lms.bis.common.CommonApi;
import com.att.lms.bis.common.util.CredentialsMaskingPreprocessor;
import com.att.lms.bis.security.ParameterizedDataForPrototypicalSpringSecurityAuthenticationAuthorizationIT;
import com.att.lms.oauth.constants.Constants;
import com.att.lms.oauth.kafka.KafkaUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sbc.bccs.idl.helpers.IDLUtil;
import com.sbc.eia.bis.framework.BisContextManager;
import com.sbc.eia.idl.bis_types.*;
import com.sbc.eia.idl.lim_types.*;
import com.sbc.eia.idl.types.*;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import javax.annotation.PostConstruct;
import org.apache.commons.lang3.StringUtils;
import org.apache.kafka.clients.admin.AdminClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.operation.preprocess.OperationPreprocessor;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith({SpringExtension.class, RestDocumentationExtension.class})
@SpringBootTest(classes = {
    EndtoEndTestApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, properties = {
    "server.port=2200"})
@TestPropertySource(
    locations = {"classpath:application-e2e-test.properties"}
)
@ContextConfiguration(classes = AuthenticationManager.class)
public class AbstractControllerE2E
    implements ParameterizedDataForPrototypicalSpringSecurityAuthenticationAuthorizationIT {
  public static final String DEFAULT_SCHEME = "http";
  public static final String DEFAULT_HOST = "localhost";
  public String DEFAULT_SPRING_BOOT_BASE_URI;
  @Autowired
  public ObjectMapper objectMapper;
  protected RequestSpecification documentationSpec;
  @Value("${server.port}")
  int port;

  //---------------------BEGIN consumerAssignment check autowires---------------------
  @Value(Constants.KafkaConstants.OauthEgressEventhubConstants.OauthEgressConsumerConstants.OAUTH_EGRESS_EVENTHUB_CONSUMER_GROUP_ID)
  protected String rcEgressConsumerGroupId;

  @Autowired
  protected AdminClient rcEgressEventhubAdminClient;

  private static boolean doneOnce;

  @PostConstruct
  private void init() {
    objectMapper.setSerializationInclusion(com.fasterxml.jackson.annotation.JsonInclude.Include.ALWAYS);
    DEFAULT_SPRING_BOOT_BASE_URI = StringUtils.join(DEFAULT_SCHEME, "://", DEFAULT_HOST, ":", port);
  }

  @BeforeEach
  public void setUp(RestDocumentationContextProvider restDocumentation) {
    if(!doneOnce){

      KafkaUtils.waitForAllMembersToBeAssignedToAPartitionInConsumerGroup(rcEgressEventhubAdminClient, rcEgressConsumerGroupId, 10, 0);

      doneOnce=true;
    }

    this.documentationSpec = new RequestSpecBuilder()
        .addFilter(documentationConfiguration(restDocumentation))
        .build();
  }

  public BisContext getBisContext(String application, String customerName) {
    BisContextManager contextMgr = new BisContextManager();
    contextMgr.setApplication(application);
    contextMgr.setCustomerName(customerName);
    return contextMgr.getBisContext();
  }

  protected OperationPreprocessor maskCredentials() {
    return new CredentialsMaskingPreprocessor();
  }

  protected String cleanResponse(String response) throws Exception {
    // Replace logging id
    return response.replaceAll("COR:[A-Z0-9|:.-]*", "")
        .replaceAll(
            "\"aStatusTime\":\\{\"aEiaDate\":\\{\"aYear\":\\d+,\"aMonth\":\\d+,\"aDay\":\\d+\\},\"aHour\":\\d+,\"aMinute\":\\d+,\"aSecond\":\\d+,\"aMilliSeconds\":\\d+,\"UTCOffset\":-?\\d+\\}",
            "\"aStatusTime\":\\{\\}");
  }

  protected UnfieldedAddress createUnfieldedAddress()
  {
    return new UnfieldedAddress(
            seq("line 1","line 2","line 3"),
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

  private StringSeqOpt seq(String... seq)
  {
    StringSeqOpt opt = new StringSeqOpt();
    opt.theValue(seq);
    return opt;
  }

  private StringOpt str(String value)
  {
    StringOpt opt = new StringOpt();
    opt.theValue(value);
    return opt;
  }

  private LongOpt lng(int value)
  {
    LongOpt opt = new LongOpt();
    opt.theValue(value);
    return opt;
  }

  private ProviderLocationProperties provider(String providerName, String... locationsToGet)
  {
    return new ProviderLocationProperties(str(providerName),locationsToGet);
  }

  private ProviderLocationPropertiesSeqOpt propSeq(ProviderLocationProperties... props)
  {
    ProviderLocationPropertiesSeqOpt opt = new ProviderLocationPropertiesSeqOpt();
    opt.theValue(props);
    return opt;
  }

  private LocationPropertiesToGetSeqOpt locSeq(String... props)
  {
    LocationPropertiesToGetSeqOpt opt = new LocationPropertiesToGetSeqOpt();
    opt.theValue(props);
    return opt;
  }

  private AddressOpt addr(Address address)
  {
    AddressOpt opt = new AddressOpt();
    opt.theValue(address);
    return opt;
  }

  private CompositeObjectKey keys()
  {
    ObjectKey[] keys = {key("name","John"),key("number","845")};
    return new CompositeObjectKey(keys);
  }

  private ObjectKey key(String value, String kind)
  {
    return new ObjectKey(value,kind);
  }


  protected FieldDescriptor[] getErrorResponseFieldDescriptor() {
    return new FieldDescriptor[] {
            fieldWithPath("type").description("Error Type"),
            fieldWithPath("aContext.aProperties[].aTag").description("BIS Context Properties Tag"),
            fieldWithPath("aContext.aProperties[].aValue").description("BIS Context Properties Value"),
            fieldWithPath("aExceptionData.aCode").description("Exception Data Code"),
            fieldWithPath("aExceptionData.aDescription").description("Exception Data Description"),
            fieldWithPath("aExceptionData.aOrigination.___theValue").description("Exception Data Origination Value"),
            fieldWithPath("aExceptionData.aOrigination.__discriminator").description("Exception Data Origination Discriminator"),
            fieldWithPath("aExceptionData.aOrigination.__uninitialized").description("Exception Data Origination Uninitialized"),
            fieldWithPath("aExceptionData.aSeverity.___theValue.__value").description("Exception Data Severity Value"),
            fieldWithPath("aExceptionData.aSeverity.__discriminator").description("Exception Data Severity Discriminator"),
            fieldWithPath("aExceptionData.aSeverity.__uninitialized").description("Exception Data Severity Uninitialized")
    };
  }
}
