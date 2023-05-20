package com.att.lms.bis.scenarios;

import com.att.lms.bis.EndtoEndTestApplication;
import com.att.lms.oauth.SharedDataDictionary;
import com.att.lms.oauth.config.OauthEgressEventhubTopics;
import com.att.lms.oauth.config.OauthFourthEventhubTopics;
import com.att.lms.oauth.config.OauthIngressEventhubTopics;
import com.att.lms.oauth.config.OauthThirdEventhubTopics;
import com.att.lms.oauth.constants.Constants;
import com.att.lms.oauth.kafka.KafkaUtils;
import com.att.lms.oauth.kafka.TopicUtils;
import com.att.lms.oauth.kafka.consumer.OauthEgressPayloadConsumer;
import com.att.lms.oauth.kafka.producer.OauthEgressEventhubProducer;
import com.att.lms.oauth.kafka.producer.OauthFourthEventhubProducer;
import com.att.lms.oauth.kafka.producer.OauthIngressEventhubProducer;
import com.att.lms.oauth.kafka.producer.OauthThirdEventhubProducer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.sbc.eia.bis.framework.BisContextManager;
import com.sbc.eia.idl.bis_types.BisContext;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import org.apache.kafka.clients.admin.AdminClient;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DirtiesContext
@TestPropertySource(locations = { "classpath:application-e2e-test.properties" })
@SpringBootTest(classes = {EndtoEndTestApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, properties = {"server.port=2555"})
public abstract class BaseKafkaRestEndToEndTest {

	protected static final Logger LOG = LoggerFactory.getLogger(BaseKafkaRestEndToEndTest.class);

	public static final short NUMBER_OF_BROKERS = 1;

	public static final int NUMBER_OF_PARTITIONS = 1;

	@Autowired
	private TopicUtils rcIngressEventhubTopicUtils;

	@Autowired
	private TopicUtils rcEgressEventhubTopicUtils;

	@Autowired
	private TopicUtils oauthThirdEventhubTopicUtils;

	@Autowired
	private TopicUtils oauthFourthEventhubTopicUtils;


	@Autowired
	protected OauthIngressEventhubTopics rcIngressEventhubTopics;

	@Autowired
	protected OauthIngressEventhubProducer rcIngressEventhubProducer;

	@Autowired
	protected OauthEgressEventhubTopics rcEgressEventhubTopics;

	@Autowired
	protected OauthEgressEventhubProducer rcEgressEventhubProducer;

	@Autowired
	protected OauthThirdEventhubTopics oauthThirdEventhubTopics;

	@Autowired
	protected OauthThirdEventhubProducer oauthThirdEventhubProducer;

	@Autowired
	protected OauthFourthEventhubTopics oauthFourthEventhubTopics;

	@Autowired
	protected OauthFourthEventhubProducer oauthFourthEventhubProducer;

	//---------------------BEGIN consumerAssignment check autowires---------------------
	@Value(Constants.KafkaConstants.OauthIngressEventhubConstants.OauthIngressConsumerConstants.OAUTH_INGRESS_EVENTHUB_CONSUMER_GROUP_ID)
	protected String rcIngressConsumerGroupId;

	@Value(Constants.KafkaConstants.OauthEgressEventhubConstants.OauthEgressConsumerConstants.OAUTH_EGRESS_EVENTHUB_CONSUMER_GROUP_ID)
	protected String rcEgressConsumerGroupId;

	@Value(Constants.KafkaConstants.OauthThirdEventhubConstants.OauthThirdConsumerConstants.OAUTH_THIRD_EVENTHUB_CONSUMER_GROUP_ID)
	protected String oauthThirdConsumerGroupId;

	@Value(Constants.KafkaConstants.OauthFourthEventhubConstants.OauthFourthConsumerConstants.OAUTH_FOURTH_EVENTHUB_CONSUMER_GROUP_ID)
	protected String oauthFourthConsumerGroupId;

	@Autowired
	protected AdminClient rcIngressEventhubAdminClient;

	@Autowired
	protected AdminClient rcEgressEventhubAdminClient;

	@Autowired
	protected AdminClient oauthThirdEventhubAdminClient;

	@Autowired
	protected AdminClient oauthFourthEventhubAdminClient;
	//----------------------END consumerAssignment check autowires----------------------

	private static boolean doneOnce;

	public static boolean lastMethodRun;

	private static String firstBrokersAddress = "";

	static {
		System.setProperty("kafka.numPartitions", "" + NUMBER_OF_PARTITIONS);
		System.setProperty("java.security.auth.login.config","src\\e2e-test\\resources\\kafka_server_jaas.conf");
	}

	@BeforeEach
	public void setup(){
		if(!doneOnce){
			KafkaUtils.waitForAllMembersToBeAssignedToAPartitionInConsumerGroup(rcIngressEventhubAdminClient, rcIngressConsumerGroupId, 10, 0);
			KafkaUtils.waitForAllMembersToBeAssignedToAPartitionInConsumerGroup(rcEgressEventhubAdminClient, rcEgressConsumerGroupId, 10, 0);
			KafkaUtils.waitForAllMembersToBeAssignedToAPartitionInConsumerGroup(oauthThirdEventhubAdminClient, oauthThirdConsumerGroupId, 10, 0);
			KafkaUtils.waitForAllMembersToBeAssignedToAPartitionInConsumerGroup(oauthFourthEventhubAdminClient, oauthFourthConsumerGroupId, 10, 0);
			doneOnce=true;
		}

		LOG.info("calling setup beforeEach");
		SharedDataDictionary.resetAll();
		SharedDataDictionary.active = true;
		OauthEgressPayloadConsumer.totalRecordsForSession=0;
		//needed for the filtering so that we only get messages for this particular test run
		LocalDateTime localDateTime = LocalDateTime.now();
		SharedDataDictionary.E2E_TEST_OAUTH_CLIENT_ID_INSTANTANEOUS_VALUE = getGlobalClientId()+ localDateTime.toEpochSecond(
				ZoneOffset.UTC);
		SharedDataDictionary.ingresTopicFilterKeyValue = SharedDataDictionary.E2E_TEST_OAUTH_CLIENT_ID_INSTANTANEOUS_VALUE;
		SharedDataDictionary.egresTopicFilterKeyValue = SharedDataDictionary.E2E_TEST_OAUTH_CLIENT_ID_INSTANTANEOUS_VALUE;
		SharedDataDictionary.thirdTopicFilterKeyValue = SharedDataDictionary.E2E_TEST_OAUTH_CLIENT_ID_INSTANTANEOUS_VALUE;
		SharedDataDictionary.fourthTopicFilterKeyValue = SharedDataDictionary.E2E_TEST_OAUTH_CLIENT_ID_INSTANTANEOUS_VALUE;
	}

	//==========================================================================================
	@Autowired
	public ObjectMapper objectMapper;

	protected WireMockServer wireMockServer;

	@AfterEach
	public void tearDown(){
		LOG.info("calling tearDown afterEach");
		SharedDataDictionary.resetAll();
		SharedDataDictionary.active = false;
		OauthEgressPayloadConsumer.totalRecordsForSession=0;

		if(withWiremock())
			this.wireMockServer.stop();
	}

	public BisContext getBisContext(String application, String customerName) {
		BisContextManager contextMgr = new BisContextManager();
		contextMgr.setApplication(application);
		contextMgr.setCustomerName(customerName);
		return contextMgr.getBisContext();
	}

	protected abstract boolean withWiremock();

	protected abstract String getGlobalClientId();
}
