package com.att.lms.oauth.kafka.consumer.filter;

import static io.vavr.API.$;
import static io.vavr.API.Case;
import static io.vavr.API.Match;
import static io.vavr.Predicates.instanceOf;


import com.att.aioneops.common.util.JsonUtils;
import com.att.lms.oauth.constants.Constants;
import com.att.lms.rc.dto.RcTextMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import io.vavr.control.Try;
import java.util.function.Predicate;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@Configuration
@Slf4j
@Profile(Constants.LmsOauthConstants.BIS_APP_FILTER_PROFILE)
public class KafkaBisAppFilterPredicates {

  final String MISSING_PROPERTIES_OBJECT_PROPERTY =
      "KafkaBisAppFilterPredicates->{} ::skipping record because aProperties Map is missing; payload={}";
  final String MISSING_APP_NAME_PROPERTY =
      "KafkaBisAppFilterPredicates->{} ::skipping record because aTag=APP_NAME property is missing; payload={}";
  final String INVALID_JSON =
      "KafkaBisAppFilterPredicates->{} ::skipping record payload is not valid JSON; payload={}";
  final String UNEXPECTED_PAYLOAD_TYPE =
      "KafkaBisAppFilterPredicates->{} ::skipping record payload is not String type; payload={}";
  final String ACCEPTING_PAYLOAD =
      "KafkaBisAppFilterPredicates->{} ::accepting this consumerRecord; payload={}";
  final String ERROR_PROCESSING_PAYLOAD =
      "KafkaBisAppFilterPredicates->{} ::unesxptected error processing payload={}, error={}";
  @Value("${lms.bis.type}")
  String bisType;
  @Value("${lms.bis.requestProperties.path}")
  String requestPropertiesPath;
  @Value("${lms.bis.responseProperties.path}")
  String responsePropertiesPath;
  @Autowired
  ObjectMapper objectMapper;

  final Predicate<ConsumerRecord> commonRecordFilterStrategy = (consumerRecord) -> {
    return Match(consumerRecord.value()).of(

        Case($(instanceOf(String.class)), target -> {
          try{

              if (JsonUtils.isValidJSON(target)) {
                return isRcTextMessageJson(target) ? shouldSkipRcAppRecord(target) : shouldSkipBisAppRecord(target);
              }

              log.error(INVALID_JSON, new Object() {
              }.getClass().getEnclosingMethod().getName(), target);

              return true;//skip the record; it's not valid JSon

          } catch (Exception e) {
            log.error(INVALID_JSON, new Object() {
            }.getClass().getEnclosingMethod().getName(),target,  e.getMessage());

            e.printStackTrace();
            log.error(e.getMessage());
            return true;//skip this message because there was an error
          }

        }),
        Case($(),
            target -> {
              log.error(UNEXPECTED_PAYLOAD_TYPE, new Object() {
              }.getClass().getEnclosingMethod().getName(), target);
              return true;
            }));
  };

  protected boolean shouldSkipBisAppRecord(final String value) {

    try {

      ArrayNode aPropertiesMap = null;

      JsonNode rootTree = objectMapper.readTree(value);

      JsonNode aPropertiesJsonNode = rootTree.at(requestPropertiesPath);

      //could be different for the responseMessage types
      if (aPropertiesJsonNode.isEmpty()) {
        aPropertiesJsonNode = rootTree.at(responsePropertiesPath);
      }

      if (aPropertiesJsonNode == null || aPropertiesJsonNode.isEmpty()) {
        log.error(MISSING_PROPERTIES_OBJECT_PROPERTY, new Object() {
        }.getClass().getEnclosingMethod().getName(), value);
        //skipping this record
        return true;
      } else {

        aPropertiesMap = (ArrayNode) aPropertiesJsonNode;

        if (!aPropertiesMap.findValues("aValue").stream()
            .filter(aProperty -> aProperty.textValue().equals(bisType))
            .map(limBisJsonNode -> limBisJsonNode.textValue()).findFirst().isPresent()) {
          log.error(MISSING_APP_NAME_PROPERTY, new Object() {
          }.getClass().getEnclosingMethod().getName(), value);
          //skipping this record
          return true;
        }

      }

      //not skipping this record
      log.info(ACCEPTING_PAYLOAD, new Object() {
      }.getClass().getEnclosingMethod().getName(), value);
      return false;

    } catch (JsonProcessingException e) {
      log.error(INVALID_JSON, new Object() {
      }.getClass().getEnclosingMethod().getName(),value,  e.getMessage());

      e.printStackTrace();

      log.error(e.getMessage());
      return true;//skip this message because there was an error
    }

  }

  protected boolean shouldSkipRcAppRecord(final String value) {
    final String SKIPPING_BECAUSE_NOT_INSTANCEOF_STRING =
        "skipping record because either type ={} is not type RcTextMessage.class";

    return Match(value).of(
        Case($(val -> isRcTextMessageJson(val)), target -> {
          return false;
        }),
        Case($(), target -> {
          log.error(SKIPPING_BECAUSE_NOT_INSTANCEOF_STRING, target.getClass().getName());
          return true;
        }));
  }

  protected boolean isRcTextMessageJson(final String possibleRcTextMessage) {
    return Try.of(() -> {
      return new ObjectMapper().readValue(possibleRcTextMessage, RcTextMessage.class);
    }).isSuccess();
  }

  @Bean
  protected Predicate<ConsumerRecord> ingressTopcMessagePredicate() {
    return commonRecordFilterStrategy;
  }

  @Bean
  protected Predicate<ConsumerRecord> egressTopicMessagePredicate() {
    return commonRecordFilterStrategy;
  }

  @Bean
  protected Predicate<ConsumerRecord> thirdTopicMessagePredicate() {
    return commonRecordFilterStrategy;
  }

  @Bean
  protected Predicate<ConsumerRecord> fourthTopicMessagePredicate() {
    return commonRecordFilterStrategy;
  }

}