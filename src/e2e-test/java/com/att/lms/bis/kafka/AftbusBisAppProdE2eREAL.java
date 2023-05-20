package com.att.lms.bis.kafka;

import static java.lang.Thread.sleep;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


import com.att.lms.aftbusinbound.dto.AftbusMessageDto;
import com.att.lms.bis.dto.lim.RetrieveLocationForAddressRequestDto;
import com.att.lms.bis.scenarios.BaseKafkaRestEndToEndTest;
import com.att.lms.oauth.SharedDataDictionary;
import com.att.lms.oauth.kafka.consumer.OauthEgressPayloadConsumer;
import com.att.lms.oauth.kafka.consumer.OauthFourthConsumer;
import com.att.lms.oauth.kafka.consumer.OauthIngressConsumer;
import com.att.lms.oauth.kafka.consumer.OauthThirdConsumer;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.lim_types.Address;
import com.sbc.eia.idl.lim_types.ProviderLocationPropertiesSeqOpt;
import com.sbc.eia.idl.types.LongOpt;
import com.sbc.eia.idl.types.StringOpt;
import com.sbc.ula.Log;
import io.vavr.collection.List;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.test.context.TestPropertySource;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestPropertySource(locations = {"classpath:application-e2e-test-kafka-prod.properties"})
public class AftbusBisAppProdE2eREAL extends BaseKafkaRestEndToEndTest {

  private static final Logger LOG = LoggerFactory.getLogger(AftbusBisAppProdE2eREAL.class);

  public final static String EXPECTED_RESPONSE_LimBisRetreiveLocationForServiceAddressFromAftbus = "{\"aContext\":{\"aProperties\":[{\"aTag\":\"Application\",\"aValue\":\"LIM_BIS\"},{\"aTag\":\"CustomerName\",\"aValue\":\"LIM_BIS\"}]},\"aServiceAddressMatchResult\":{\"___aServiceLocation\":{\"aServiceAddress\":{\"__uninitialized\":true},\"aAddressMatchCode\":{\"__discriminator\":false,\"__uninitialized\":false},\"aAddressMatchCodeDescription\":{\"__discriminator\":false,\"__uninitialized\":false},\"aCentralOfficeCode\":{\"__discriminator\":false,\"__uninitialized\":false},\"aCommunityName\":{\"__discriminator\":false,\"__uninitialized\":false},\"aE911Exempt\":{\"__discriminator\":false,\"__uninitialized\":false},\"aE911NonRecurringCharge\":{\"__discriminator\":false,\"__uninitialized\":false},\"aE911Surcharge\":{\"__discriminator\":false,\"__uninitialized\":false},\"aExchangeCode\":{\"__discriminator\":false,\"__uninitialized\":false},\"aExco\":{\"__discriminator\":false,\"__uninitialized\":false},\"aLataCode\":{\"__discriminator\":false,\"__uninitialized\":false},\"aLocalProviderServingOfficeCode\":{\"__discriminator\":false,\"__uninitialized\":false},\"aOwnedWiring\":{\"__discriminator\":false,\"__uninitialized\":false},\"aPrimaryDirectoryCode\":{\"__discriminator\":false,\"__uninitialized\":false},\"aPrimaryNpaNxx\":{\"__discriminator\":false,\"__uninitialized\":false},\"aQuickDialTone\":{\"__discriminator\":false,\"__uninitialized\":false},\"aQuickDialToneNumber\":{\"__discriminator\":false,\"__uninitialized\":false},\"aRateZoneBandCode\":{\"__discriminator\":false,\"__uninitialized\":false},\"aSagWireCenter\":{\"__discriminator\":false,\"__uninitialized\":false},\"aServingAreaDescription\":{\"__discriminator\":false,\"__uninitialized\":false},\"aStreetAddressGuideArea\":{\"__discriminator\":false,\"__uninitialized\":false},\"aSurcharge4Percent\":{\"__discriminator\":false,\"__uninitialized\":false},\"aSurcharge16Percent\":{\"__discriminator\":false,\"__uninitialized\":false},\"aTarCode\":{\"__discriminator\":false,\"__uninitialized\":false},\"aTelephoneNumber\":{\"__discriminator\":false,\"__uninitialized\":false},\"aWorkingServiceOnLocation\":{\"__discriminator\":false,\"__uninitialized\":false},\"aSAGAddress\":{\"__discriminator\":false,\"__uninitialized\":false},\"aBuildingType\":{\"__discriminator\":false,\"__uninitialized\":false},\"aLegalEntity\":{\"__discriminator\":false,\"__uninitialized\":false},\"aVideoHubOffice\":{\"__discriminator\":false,\"__uninitialized\":false},\"aSmartmoves\":{\"__discriminator\":false,\"__uninitialized\":false},\"aServingNetworkType\":{\"__discriminator\":false,\"__uninitialized\":false},\"aLocationType\":{\"__discriminator\":false,\"__uninitialized\":false},\"aRateZone\":{\"__discriminator\":false,\"__uninitialized\":false},\"aIndependentCompanyIndicator\":{\"__discriminator\":false,\"__uninitialized\":false},\"aAreaTransferCutDate\":{\"__discriminator\":false,\"__uninitialized\":false},\"aAreaTransferNumberChangeDate\":{\"__discriminator\":false,\"__uninitialized\":false},\"aAreaTransferNpaNxx\":{\"__discriminator\":false,\"__uninitialized\":false},\"aAreaTransferWireCenter\":{\"__discriminator\":false,\"__uninitialized\":false},\"aWireCenter\":{\"__discriminator\":false,\"__uninitialized\":false},\"aConnectThrough\":{\"__discriminator\":false,\"__uninitialized\":false},\"aExtensions\":{\"__discriminator\":false,\"__uninitialized\":false}},\"___aAlternativeServiceAddresses\":[],\"__discriminator\":{\"__value\":1},\"__uninitialized\":false}}";

  private static final String LIMBIS_REQUEST_LOCATION_FOR_ADDRESS =
      "{\"aBisContext\":{\"aProperties\":[{\"aTag\":\"Application\",\"aValue\":\"LIM_BIS\"},{\"aTag\":\"CustomerName\",\"aValue\":\"LIM_BIS\"}]},\"aAddress\":{\"___aFieldedAddress\":null,\"___aUnfieldedAddress\":null,\"__discriminator\":null,\"__uninitialized\":true},\"aProviderLocationProperties\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":true},\"aMaxBasicAddressAlternatives\":{\"___theValue\":0,\"__discriminator\":false,\"__uninitialized\":true},\"aMaxLivingUnitAlternatives\":{\"___theValue\":0,\"__discriminator\":false,\"__uninitialized\":true},\"aExchangeCode\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":true}}";

  private static final String RETRIEVE_LOCATION_FOR_ADDRESS_REQUEST_JSON =
      "{\"aBisContext\":{\"aProperties\":[{\"aTag\":\"Application\",\"aValue\":\"LIM_BIS\"},{\"aTag\":\"METHOD_NAME\",\"aValue\":\"retrieveLocationForAddress\"},{\"aTag\":\"APP_NAME\",\"aValue\":\"LIM_BIS\"},{\"aTag\":\"CustomerName\",\"aValue\":\"LIM_BIS\"},{\"aTag\":\"ServiceCenter\",\"aValue\":\"OH\"}]},\"aAddress\":{\"___aFieldedAddress\":{\"aRoute\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":true},\"aBox\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":true},\"aHouseNumberPrefix\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":true},\"aHouseNumber\":{\"___theValue\":\"1236\",\"__discriminator\":true,\"__uninitialized\":false},\"aAssignedHouseNumber\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":true},\"aHouseNumberSuffix\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":true},\"aStreetDirection\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":true},\"aStreetName\":{\"___theValue\":\"Jackson\",\"__discriminator\":true,\"__uninitialized\":false},\"aStreetThoroughfare\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":true},\"aStreetNameSuffix\":{\"___theValue\":\"AV\",\"__discriminator\":true,\"__uninitialized\":false},\"aCity\":{\"___theValue\":\"LKWD\",\"__discriminator\":true,\"__uninitialized\":false},\"aState\":{\"___theValue\":\"OH\",\"__discriminator\":true,\"__uninitialized\":false},\"aPostalCode\":{\"___theValue\":\"44107\",\"__discriminator\":true,\"__uninitialized\":false},\"aPostalCodePlus4\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":true},\"aCounty\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":true},\"aCountry\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":true},\"aStructureType\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":true},\"aStructureValue\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":true},\"aLevelType\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":true},\"aLevelValue\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":true},\"aUnitType\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":true},\"aUnitValue\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":true},\"aOriginalStreetDirection\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":true},\"aOriginalStreetNameSuffix\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":true},\"aCassAddressLines\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":true},\"aAuxiliaryAddressLines\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":true},\"aCassAdditionalInfo\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":true},\"aAdditionalInfo\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":true},\"aBusinessName\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":true},\"aCountryCode\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":true},\"aCityCode\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":true},\"aServiceLocationName\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":true},\"aAddressId\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":true},\"aAliasName\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":true},\"aAttention\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":true}},\"___aUnfieldedAddress\":{\"aAddressLines\":{\"___theValue\":[\"LOVELOCK NV 89419-0147\"],\"__discriminator\":false,\"__uninitialized\":true}},\"__discriminator\":{\"__value\":0},\"__uninitialized\":false},\"aProviderLocationProperties\":{\"___theValue\":[{\"aProviderName\":{\"___theValue\":\"SBC\",\"__discriminator\":true,\"__uninitialized\":false},\"aLocationPropertiesToGet\":[\"ServiceAddress\",\"All\"]}],\"__discriminator\":true,\"__uninitialized\":false},\"aMaxBasicAddressAlternatives\":{\"___theValue\":56,\"__discriminator\":true,\"__uninitialized\":false},\"aMaxLivingUnitAlternatives\":{\"___theValue\":70,\"__discriminator\":true,\"__uninitialized\":false},\"aExchangeCode\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":true}}";


  static LocalDateTime localDateTime = LocalDateTime.now();
  String messageStr = null;
  int messageCount = 0;
  @Autowired
  private KafkaListenerEndpointRegistry registry;
  private Log log;

  @Test
  public void shouldExecuteLimBisRetreiveLocationForServiceAddressFromAftbus() throws Exception {

/*
    20220721
    NOTE: I need to fix this in the lib design
    right now each consumer saves the response from its corresponding service class
    into the SharedDictionary hashmap with the corresponding name to that consumer
    as long as this value is set to active
     */
    SharedDataDictionary.active = false;

    final String JMS_CORRELATINO_ID =
        "COR:BBNMS_LFACS_L3/IOM3/809897962//SB_622412694358484&source=LSGUI";

    final String JMS_CORRELATION_ID_LABEL = "jms_correlationId";

    BisContext aBisContext = getBisContext("LIM_BIS", "LIM_BIS");

    Address aAddress = new Address();
    ProviderLocationPropertiesSeqOpt aProviderLocationProperties =
        new ProviderLocationPropertiesSeqOpt();
    LongOpt aMaxBasicAddressAlternatives = new LongOpt();
    LongOpt aMaxLivingUnitAlternatives = new LongOpt();
    StringOpt aExchangeCode = new StringOpt();

    RetrieveLocationForAddressRequestDto request = RetrieveLocationForAddressRequestDto.builder()
        .aBisContext(aBisContext)
        .aAddress(aAddress)
        .aProviderLocationProperties(aProviderLocationProperties)
        .aMaxBasicAddressAlternatives(aMaxBasicAddressAlternatives)
        .aMaxLivingUnitAlternatives(aMaxLivingUnitAlternatives)
        .aExchangeCode(aExchangeCode)
        .build();

    String messageJson = objectMapper.writeValueAsString(request);

    System.out.println("messageAsJson =" + messageJson);

    RetrieveLocationForAddressRequestDto deserializedMessage =
        objectMapper.readValue(messageJson, RetrieveLocationForAddressRequestDto.class);

    List<Header> headers = List.of(
        new RecordHeader(KafkaHeaders.REPLY_TOPIC, oauthFourthEventhubTopics.OAUTH_FOURTH_TOPIC
            .getBytes()));

    ProducerRecord<String, String> producerRecord =
        new ProducerRecord<String, String>(oauthThirdEventhubTopics.OAUTH_THIRD_TOPIC,
            null, UUID.randomUUID().toString(),
            RETRIEVE_LOCATION_FOR_ADDRESS_REQUEST_JSON
            , headers);

    oauthThirdEventhubProducer.send(producerRecord);

    SharedDataDictionary.ingressTopicFilterKey = "/clientId";
    SharedDataDictionary.egressTopicFilterKey = "/clientId";
    SharedDataDictionary.thirdTopicFilterKey = "/clientId";
    SharedDataDictionary.fourthTopicFilterKey = "/clientId";

    final long SLEEP_INTERVAL_WAITING_FOR_EDGE_COUNT_LATCH = 1000;

    final long MAX_ITERATIONS_WAITING_FOR_EDGE_COUNT_LATCH = 600;

    long iterations = 0;

    messageStr = null;

    messageCount = 0;

    do {
      LOG.error(
          "\n\n\t\t\t\t" + "######@@@@sleeping for " + SLEEP_INTERVAL_WAITING_FOR_EDGE_COUNT_LATCH +
              " seconds until SharedDataDictionary.OauthThirdEventhubConsumerConfig_PAYLOADS is not empty ");

      try {
        messageCount =
            SharedDataDictionary.OauthThirdEventhubConsumerConfig_PAYLOADS
                .size();

        if (messageCount > 0) {
          messageStr =
              ((String)SharedDataDictionary.OauthThirdEventhubConsumerConfig_PAYLOADS
                  .values().toArray()[0]);
        }

        sleep(SLEEP_INTERVAL_WAITING_FOR_EDGE_COUNT_LATCH);
      } catch (InterruptedException e) {
        e.printStackTrace();
        throw new RuntimeException(e);
      }
    } while (iterations++ < MAX_ITERATIONS_WAITING_FOR_EDGE_COUNT_LATCH &&
        Objects.isNull(messageStr));

    AssertionsForClassTypes.assertThat(OauthThirdConsumer.totalRecordsForSession).isEqualTo(1);

    assertThat(messageStr).isNotNull();

    System.out.println("OauthIngressTopic.string =" + messageStr);

    messageCount = 0;
    messageStr = null;
    String resultantMessage = null;

    do {
      LOG.error(
          "\n\n\t\t\t\t" + "######@@@@sleeping for " + SLEEP_INTERVAL_WAITING_FOR_EDGE_COUNT_LATCH +
              " seconds until SharedDataDictionary.OauthFourthEventhubConsumerConfig_PAYLOADS is not empty ");

      try {
        messageCount =
            SharedDataDictionary.OauthFourthEventhubConsumerConfig_PAYLOADS
                .size();

        if (messageCount > 0) {
          resultantMessage =
              ((String) SharedDataDictionary.OauthFourthEventhubConsumerConfig_PAYLOADS
                  .values().toArray()[0]);
        }

        sleep(SLEEP_INTERVAL_WAITING_FOR_EDGE_COUNT_LATCH);
      } catch (InterruptedException e) {
        e.printStackTrace();
        throw new RuntimeException(e);
      }
    } while (iterations++ < MAX_ITERATIONS_WAITING_FOR_EDGE_COUNT_LATCH &&
        Objects.isNull(resultantMessage));

    AssertionsForClassTypes.assertThat(OauthFourthConsumer.totalRecordsForSession)
        .isEqualTo(1);

    assertThat(resultantMessage).isNotNull();

    System.out.println("OauthEgressTopic.string =" + resultantMessage);

    messageCount = 0;
    messageStr = null;


  }

  @Override
  protected boolean withWiremock() {
    return false;
  }

  @Override
  protected String getGlobalClientId() {
    return "LMS";
  }

}
