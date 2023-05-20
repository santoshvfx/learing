package com.att.lms.bis.kafka.service;


import static io.vavr.API.$;
import static io.vavr.API.Case;
import static io.vavr.API.Match;


import com.att.aioneops.common.dto.Throwables;
import com.att.aioneops.common.util.JsonUtils;
import com.att.lms.aftbusinbound.dto.AftbusMessageDto;
import com.att.lms.bis.enumeration.RequestTypes;
import com.att.lms.oauth.SharedDataDictionary;
import com.att.lms.oauth.config.settings.output.OauthEgressEventhubTopicSettings;
import com.att.lms.oauth.kafka.producer.OauthEgressEventhubProducer;
import com.att.lms.oauth.service.IConsumerService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.sbc.eia.idl.lim.RetrieveLocationForAddressReturn;
import io.vavr.control.Either;
import io.vavr.control.Option;
import io.vavr.control.Try;
import java.util.Objects;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.StreamSupport;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BisEgressTestConsumerService
    implements
    IConsumerService<ConsumerRecord<String, String>, String, Throwables> {

  @Autowired
  ObjectMapper objectMapper;

  @Override
  public Either<Throwables, Option<String>> execute(
      ConsumerRecord<String, String> consumerRecord) {

    return Try.of(() -> {

      if (!JsonUtils.isValidJSON(consumerRecord.value())) {
        throw new IllegalArgumentException(
            "payload =" + consumerRecord.value() + " is not in a valid JSON message format");
      }
      else {

        System.out.println("BisEgressTestConsumerService.execute.RequestTypes.method ="+objectMapper.readValue(consumerRecord.value(),
            RetrieveLocationForAddressReturn.class));

        if(SharedDataDictionary.fourthTopicFilterKey != null)
        SharedDataDictionary.OauthFourthEventhubConsumerConfig_PAYLOADS.put(SharedDataDictionary.fourthTopicFilterKey, consumerRecord.value());

        return consumerRecord.value();

      }
    }).onSuccess(lmsRequest -> log
        .info("successfully converted payload to instance of lmsRequest dto: paylaod={}",
            lmsRequest))
        .onFailure(throwuble -> log.error(throwuble.getMessage()))
        .toEither()
        .map(result -> Option.of(result))
        .mapLeft((Throwable t) -> Throwables.fromThrowable(t));
  }

  @Override
  public Throwables handleException(Throwables exs) {
    return null;
  }
}
