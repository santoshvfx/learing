package com.att.lms.bis.kafka;

import static java.lang.Thread.sleep;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


import com.att.lms.aftbusinbound.dto.AftbusMessageDto;
import com.att.lms.bis.dto.lim.RetrieveLocationForAddressRequestDto;
import com.att.lms.bis.enumeration.RequestTypes;
import com.att.lms.bis.kafka.service.KafkaConstants;
import com.att.lms.bis.scenarios.BaseKafkaRestEndToEndTest;
import com.att.lms.oauth.SharedDataDictionary;
import com.att.lms.oauth.kafka.consumer.OauthEgressPayloadConsumer;
import com.att.lms.oauth.kafka.consumer.OauthFourthConsumer;
import com.att.lms.oauth.kafka.consumer.OauthIngressConsumer;
import com.att.lms.oauth.kafka.consumer.OauthThirdConsumer;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.bis_types.DataNotFound;
import com.sbc.eia.idl.lim.RetrieveLocationForAddressReturn;
import com.sbc.eia.idl.lim_types.Address;
import com.sbc.eia.idl.lim_types.ProviderLocationPropertiesSeqOpt;
import com.sbc.eia.idl.types.LongOpt;
import com.sbc.eia.idl.types.StringOpt;
import com.sbc.ula.Log;
import io.vavr.collection.List;
import io.vavr.control.Try;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.test.context.TestPropertySource;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestPropertySource(locations = {"classpath:application-e2e-test-kafka-nonprod.properties"})
public class AftbusBisAppDevE2eREAL extends BaseKafkaRestEndToEndTest {

  private static final Logger LOG = LoggerFactory.getLogger(AftbusBisAppDevE2eREAL.class);

  private static final String RETRIEVE_LOCATION_FOR_ADDRESS_REQUEST_JSON =
          "{\"aBisContext\":{\"aProperties\":[{\"aTag\":\"Application\",\"aValue\":\"LIM_BIS\"},{\"aTag\":\"METHOD_NAME\",\"aValue\":\"retrieveLocationForAddress\"},{\"aTag\":\"APP_NAME\",\"aValue\":\"LIM_BIS\"},{\"aTag\":\"CustomerName\",\"aValue\":\"LIM_BIS\"},{\"aTag\":\"ServiceCenter\",\"aValue\":\"OH\"}]},\"aAddress\":{\"___aFieldedAddress\":{\"aRoute\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":true},\"aBox\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":true},\"aHouseNumberPrefix\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":true},\"aHouseNumber\":{\"___theValue\":\"1236\",\"__discriminator\":true,\"__uninitialized\":false},\"aAssignedHouseNumber\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":true},\"aHouseNumberSuffix\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":true},\"aStreetDirection\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":true},\"aStreetName\":{\"___theValue\":\"Jackson\",\"__discriminator\":true,\"__uninitialized\":false},\"aStreetThoroughfare\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":true},\"aStreetNameSuffix\":{\"___theValue\":\"AV\",\"__discriminator\":true,\"__uninitialized\":false},\"aCity\":{\"___theValue\":\"LKWD\",\"__discriminator\":true,\"__uninitialized\":false},\"aState\":{\"___theValue\":\"OH\",\"__discriminator\":true,\"__uninitialized\":false},\"aPostalCode\":{\"___theValue\":\"44107\",\"__discriminator\":true,\"__uninitialized\":false},\"aPostalCodePlus4\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":true},\"aCounty\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":true},\"aCountry\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":true},\"aStructureType\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":true},\"aStructureValue\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":true},\"aLevelType\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":true},\"aLevelValue\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":true},\"aUnitType\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":true},\"aUnitValue\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":true},\"aOriginalStreetDirection\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":true},\"aOriginalStreetNameSuffix\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":true},\"aCassAddressLines\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":true},\"aAuxiliaryAddressLines\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":true},\"aCassAdditionalInfo\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":true},\"aAdditionalInfo\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":true},\"aBusinessName\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":true},\"aCountryCode\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":true},\"aCityCode\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":true},\"aServiceLocationName\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":true},\"aAddressId\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":true},\"aAliasName\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":true},\"aAttention\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":true}},\"___aUnfieldedAddress\":{\"aAddressLines\":{\"___theValue\":[\"LOVELOCK NV 89419-0147\"],\"__discriminator\":false,\"__uninitialized\":true}},\"__discriminator\":{\"__value\":0},\"__uninitialized\":false},\"aProviderLocationProperties\":{\"___theValue\":[{\"aProviderName\":{\"___theValue\":\"SBC\",\"__discriminator\":true,\"__uninitialized\":false},\"aLocationPropertiesToGet\":[\"ServiceAddress\",\"All\"]}],\"__discriminator\":true,\"__uninitialized\":false},\"aMaxBasicAddressAlternatives\":{\"___theValue\":56,\"__discriminator\":true,\"__uninitialized\":false},\"aMaxLivingUnitAlternatives\":{\"___theValue\":70,\"__discriminator\":true,\"__uninitialized\":false},\"aExchangeCode\":{\"___theValue\":null,\"__discriminator\":false,\"__uninitialized\":true}}";


  private static final String RETRIEVE_LOCATION_FOR_ADDRESS_REQUEST_JSON_DATANOTFOUND_JSON =
      "{\"aBisContext\":{\"aProperties\":[{\"aTag\":\"Application\",\"aValue\":\"SORD\"},{\"aTag\":\"BusinessUnit\",\"aValue\":\"SBCWest\"},{\"aTag\":\"CustomerName\",\"aValue\":\"EWBAV\"},{\"aTag\":\"METHOD_NAME\",\"aValue\":\"retrieveLocationForAddress\"},{\"aTag\":\"APP_NAME\",\"aValue\":\"LIM_BIS\"},{\"aTag\":\"ServiceCenter\",\"aValue\":\"CA-N\"},{\"aTag\":\"MaxCassCharPerLine\",\"aValue\":\"28\"},{\"aTag\":\"ServiceOrderNumber\",\"aValue\":\"C38000713 \"}]},\"aAddress\":{\"___aUnfieldedAddress\":{\"aAddressLines\":{\"___theValue\":[\"DEPT OFHUMAN RESOURCES CALHR  \",\"1515 S ST STE 400 BLDG X      \",\"                              \",\"                              \",\"SACRAMENTO CA  95811-7246     \"],\"__discriminator\":true,\"__uninitialized\":false}},\"__discriminator\":{\"__value\":1},\"__uninitialized\":false},\"aProviderLocationProperties\":{\"___theValue\":[{\"aProviderName\":{\"___theValue\":\"DataServices\",\"__discriminator\":true,\"__uninitialized\":false},\"aLocationPropertiesToGet\":[\"PostalAddress\",\"All\"]}],\"__discriminator\":true,\"__uninitialized\":false},\"aMaxBasicAddressAlternatives\":{\"___theValue\":20,\"__discriminator\":true,\"__uninitialized\":false},\"aMaxLivingUnitAlternatives\":{\"___theValue\":20,\"__discriminator\":true,\"__uninitialized\":false}}";


  static LocalDateTime localDateTime = LocalDateTime.now();
  String messageStr = null;
  int messageCount = 0;
  @Autowired
  private KafkaListenerEndpointRegistry registry;
  private Log log;

  @Test
  public void shouldReturnExpectedSuccessfulResponseFromRetreiveLocationForOhioAddress() throws Exception {

    /*
    20220721
    NOTE: I need to fix this in the lib design
    right now each consumer saves the response from its corresponding service class
    into the SharedDictionary hashmap with the corresponding name to that consumer
    as long as this value is set to active
     */
    SharedDataDictionary.active = false;


    RetrieveLocationForAddressRequestDto request = Try
        .of(() -> objectMapper.readValue((String)RETRIEVE_LOCATION_FOR_ADDRESS_REQUEST_JSON, RetrieveLocationForAddressRequestDto.class)).getOrNull();

    String messageJson = objectMapper.writeValueAsString(request);

    System.out.println("messageAsJson =" + messageJson);

    RetrieveLocationForAddressRequestDto deserializedMessage =
        objectMapper.readValue(messageJson, RetrieveLocationForAddressRequestDto.class);

    List<Header> headers = List.of(new RecordHeader(KafkaConstants.JMS_MESSAGE_ID_HEADER_LABEL, "ID:c1d7e2d9c4c1f2e3f0f0f0f3f6f7f3c3f3f4f0f0f0f5f4f4".getBytes()),
        new RecordHeader(KafkaConstants.JMS_IBM_Format_HEADER_LABEL, "MQSTR".getBytes()),
        new RecordHeader(KafkaHeaders.REPLY_TOPIC, oauthFourthEventhubTopics.OAUTH_FOURTH_TOPIC
            .getBytes()));

    ProducerRecord<String, RetrieveLocationForAddressRequestDto> producerRecord =
        new ProducerRecord<String, RetrieveLocationForAddressRequestDto>(oauthThirdEventhubTopics.OAUTH_THIRD_TOPIC,
            null, UUID.randomUUID().toString(),
              request
            , headers);

    oauthThirdEventhubProducer.send(producerRecord);

    SharedDataDictionary.ingressTopicFilterKey = "/clientId";
    SharedDataDictionary.egressTopicFilterKey = "/clientId";
    SharedDataDictionary.thirdTopicFilterKey = "/clientId";
    SharedDataDictionary.fourthTopicFilterKey = "/clientId";

    //timetout after 4 minutes = 240000ms
    final long SLEEP_INTERVAL_WAITING_FOR_EDGE_COUNT_LATCH = 6000;

    final long MAX_ITERATIONS_WAITING_FOR_EDGE_COUNT_LATCH = 40;

    long iterations = 0;

    Object thirdTopicMessage = null;

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
          thirdTopicMessage =
              SharedDataDictionary.OauthThirdEventhubConsumerConfig_PAYLOADS
                  .values().toArray()[0];
        }

        sleep(SLEEP_INTERVAL_WAITING_FOR_EDGE_COUNT_LATCH);
      } catch (InterruptedException e) {
        e.printStackTrace();
        throw new RuntimeException(e);
      }
    } while (iterations++ < MAX_ITERATIONS_WAITING_FOR_EDGE_COUNT_LATCH &&
        Objects.isNull(thirdTopicMessage));

    final Object thirdTopicImmutableMessage = thirdTopicMessage;

    AssertionsForClassTypes.assertThat(OauthThirdConsumer.totalRecordsForSession).isEqualTo(1);

    assertThat(thirdTopicImmutableMessage).isNotNull();


    RetrieveLocationForAddressReturn retrieveLocationForAddressReturn = (RetrieveLocationForAddressReturn)thirdTopicImmutableMessage;

    assertThat(retrieveLocationForAddressReturn).isNotNull();

    assertThat(retrieveLocationForAddressReturn.getAAddressMatchResult().get___aLocation().getAProviderLocationProperties().length).isGreaterThan(0);

    assertThat(retrieveLocationForAddressReturn.getAAddressMatchResult().get___aLocation().getAProviderLocationProperties()[0].getALocalProviderServingOfficeCode().get___theValue()).isEqualTo("216226");

    assertThat(retrieveLocationForAddressReturn.getAAddressMatchResult().get___aLocation().getAProviderLocationProperties()[0].getAPrimaryNpaNxx().get___theValue()).isEqualTo("216226");

    assertThat(retrieveLocationForAddressReturn.getAAddressMatchResult().get___aLocation().getAProviderLocationProperties()[0].getASagWireCenter().get___theValue().trim()).isEqualTo("30");

    assertThat(retrieveLocationForAddressReturn.getAAddressMatchResult().get___aLocation().getAProviderLocationProperties()[0].getATarCode().get___theValue().trim()).isEqualTo("0018");

    assertThat(retrieveLocationForAddressReturn.getAAddressMatchResult().get___aLocation().getAProviderLocationProperties()[0].getAServiceAddress().get___theValue().get___aFieldedAddress().getAStreetName().get___theValue().trim()).isEqualTo("JACKSON");

    assertThat(retrieveLocationForAddressReturn.getAAddressMatchResult().get___aLocation().getAProviderLocationProperties()[0].getAServiceAddress().get___theValue().get___aFieldedAddress().getAHouseNumber().get___theValue().trim()).isEqualTo("1236");

    assertThat(retrieveLocationForAddressReturn.getAAddressMatchResult().get___aLocation().getAProviderLocationProperties()[0].getAServiceAddress().get___theValue().get___aFieldedAddress().getACity().get___theValue().trim()).isEqualTo("LAKEWOOD");

    assertThat(retrieveLocationForAddressReturn.getAAddressMatchResult().get___aLocation().getAProviderLocationProperties()[0].getAServiceAddress().get___theValue().get___aFieldedAddress().getAState().get___theValue().trim()).isEqualTo("OH");

    assertThat(retrieveLocationForAddressReturn.getAAddressMatchResult().get___aLocation().getAProviderLocationProperties()[0].getAServiceAddress().get___theValue().get___aFieldedAddress().getAPostalCode().get___theValue().trim()).isEqualTo("44107");

    assertThat(retrieveLocationForAddressReturn.getAAddressMatchResult().get___aLocation().getAProviderLocationProperties()[0].getAServiceAddress().get___theValue().get___aFieldedAddress().getACounty().get___theValue().trim()).isEqualTo("CUYA");

    System.out.println("OauthThirdTopic.string =" + thirdTopicMessage);

    messageCount = 0;
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
    thirdTopicMessage = null;
    resultantMessage = null;


  }

  @Test
  public void shouldReturnExpectedDataNotFoundResponseFromRetreiveLocationForSacramentoAddress() throws Exception {

    /*
    20220721
    NOTE: I need to fix this in the lib design
    right now each consumer saves the response from its corresponding service class
    into the SharedDictionary hashmap with the corresponding name to that consumer
    as long as this value is set to active
     */
    SharedDataDictionary.active = false;


    RetrieveLocationForAddressRequestDto request = Try
        .of(() -> objectMapper.readValue((String)RETRIEVE_LOCATION_FOR_ADDRESS_REQUEST_JSON_DATANOTFOUND_JSON, RetrieveLocationForAddressRequestDto.class)).getOrNull();

    String messageJson = objectMapper.writeValueAsString(request);

    System.out.println("messageAsJson =" + messageJson);

    RetrieveLocationForAddressRequestDto deserializedMessage =
        objectMapper.readValue(messageJson, RetrieveLocationForAddressRequestDto.class);

    List<Header> headers = List.of(
        new RecordHeader(KafkaHeaders.REPLY_TOPIC, oauthFourthEventhubTopics.OAUTH_FOURTH_TOPIC
            .getBytes()));

    ProducerRecord<String, RetrieveLocationForAddressRequestDto> producerRecord =
        new ProducerRecord<String, RetrieveLocationForAddressRequestDto>(oauthThirdEventhubTopics.OAUTH_THIRD_TOPIC,
            null, UUID.randomUUID().toString(),
            request
            , headers);

    oauthThirdEventhubProducer.send(producerRecord);

    SharedDataDictionary.ingressTopicFilterKey = "/clientId";
    SharedDataDictionary.egressTopicFilterKey = "/clientId";
    SharedDataDictionary.thirdTopicFilterKey = "/clientId";
    SharedDataDictionary.fourthTopicFilterKey = "/clientId";

    //timetout after 4 minutes = 240000ms
    final long SLEEP_INTERVAL_WAITING_FOR_EDGE_COUNT_LATCH = 6000;

    final long MAX_ITERATIONS_WAITING_FOR_EDGE_COUNT_LATCH = 40;

    long iterations = 0;

    Object thirdTopicMessage = null;

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
          thirdTopicMessage =
              SharedDataDictionary.OauthThirdEventhubConsumerConfig_PAYLOADS
                  .values().toArray()[0];
        }

        sleep(SLEEP_INTERVAL_WAITING_FOR_EDGE_COUNT_LATCH);
      } catch (InterruptedException e) {
        e.printStackTrace();
        throw new RuntimeException(e);
      }
    } while (iterations++ < MAX_ITERATIONS_WAITING_FOR_EDGE_COUNT_LATCH &&
        Objects.isNull(thirdTopicMessage));

    final Object thirdTopicImmutableMessage = thirdTopicMessage;

    AssertionsForClassTypes.assertThat(OauthThirdConsumer.totalRecordsForSession).isEqualTo(1);

    assertThat(thirdTopicImmutableMessage).isNotNull();

    DataNotFound dataNotFound = (DataNotFound)thirdTopicMessage;

    assertThat(dataNotFound).isNotNull();

    System.out.println("OauthThirdTopic.string =" + thirdTopicMessage);

    messageCount = 0;
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
    thirdTopicMessage = null;
    resultantMessage = null;


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
