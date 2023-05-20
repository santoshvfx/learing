package com.att.lms.bis.kafka.service;


import com.att.aioneops.common.dto.Throwables;
import com.att.aioneops.common.util.JsonUtils;
import com.att.lms.bis.enumeration.ApropertiesTagLabelName;
import com.att.lms.bis.enumeration.RequestTypes;
import com.att.lms.bis.service.LimService;
import com.att.lms.oauth.SharedDataDictionary;
import com.att.lms.oauth.kafka.producer.OauthFourthEventhubProducer;
import com.att.lms.oauth.service.IConsumerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.sbc.eia.idl.bis_types.AccessDenied;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.bis_types.BusinessViolation;
import com.sbc.eia.idl.bis_types.DataNotFound;
import com.sbc.eia.idl.bis_types.InvalidData;
import com.sbc.eia.idl.bis_types.MultipleExceptions;
import com.sbc.eia.idl.bis_types.NotImplemented;
import com.sbc.eia.idl.bis_types.ObjectNotFound;
import com.sbc.eia.idl.bis_types.SystemFailure;
import com.sbc.eia.idl.types.ObjectProperty;
import io.vavr.collection.List;
import io.vavr.control.Either;
import io.vavr.control.Option;
import io.vavr.control.Try;
import java.util.Arrays;
import java.util.Objects;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.UUID;
import java.util.stream.StreamSupport;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.omg.CORBA.LmsUserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BisIngressConsumerService
    implements
    IConsumerService<ConsumerRecord<String, String>, Object, Throwables> {

  final String MISSING_BIS_METHOD_NAME_PROPERTY =
      "filterForConsumer->%s ::skipping record because aTag=METHOD_NAME property is missing; payload=%s";

  @Autowired
  OauthFourthEventhubProducer oauthFourthEventhubProducer;
  @Autowired
  ObjectMapper objectMapper;
  @Autowired
  LimService limService;
  @Value("${lms.bis.requestProperties.path}")
  String requestPropertiesPath;
  @Value("${lms.bis.type}")
  String bisType;

  @Override
  public Either<Throwables, Option<Object>> execute(
      ConsumerRecord<String, String> consumerRecord) {

    Either<Throwables, Option<Object>> eitherRightOrLeft =  Try.of(() -> {

      if (Objects.isNull(consumerRecord.headers().lastHeader(KafkaHeaders.REPLY_TOPIC)) ||
          Objects.isNull(consumerRecord.headers().lastHeader(KafkaHeaders.REPLY_TOPIC).value())) {
        throw new IllegalArgumentException("header value KafkaHeaders.REPLY_TOPIC cannot be null");
      }
      if (!JsonUtils.isValidJSON(consumerRecord.value())) {
        throw new IllegalArgumentException(
            "payload =" + consumerRecord.value() + " is not in a valid JSON message format");
      }
      //check if the methodName property is present
      JsonNode rootTree = objectMapper.readTree(consumerRecord.value());

      ArrayNode aPropertiesMap = (ArrayNode) rootTree.at(requestPropertiesPath);

      if (!StreamSupport
          .stream(
              Spliterators.spliteratorUnknownSize(aPropertiesMap.elements(), Spliterator.ORDERED),
              false).filter(objectNode -> objectNode.get("aTag").textValue().equals(
              ApropertiesTagLabelName.METHOD_NAME.name()))
          .findFirst().isPresent()) {

        log.error(MISSING_BIS_METHOD_NAME_PROPERTY, new Object() {
        }.getClass().getEnclosingMethod().getName(), consumerRecord.value());

        throw new IllegalArgumentException(
            String.format(MISSING_BIS_METHOD_NAME_PROPERTY, new Object() {
            }.getClass().getEnclosingMethod().getName(), consumerRecord.value()));
      } else {
        String methodName = StreamSupport.stream(Spliterators.spliteratorUnknownSize(aPropertiesMap.elements(), Spliterator.ORDERED), false).filter(objectNode -> objectNode.get("aTag").textValue().equals("METHOD_NAME")).findFirst().get().findValue("aValue").textValue();

        System.out.println("BisIngressConsumerService.execute.RequestTypes.method ="+objectMapper.readValue(consumerRecord.value(), RequestTypes.fromPropertyName(methodName).getClazz()));

        Object response = RequestTypes.fromPropertyName(methodName).getFunction().apply(Try.of(() -> objectMapper.readValue(consumerRecord.value(), RequestTypes.fromPropertyName(methodName).getClazz())).get())
            .getOrElseThrow(t ->t.getErrors().get(0));

        ProducerRecord<String, Object> producerRecord =
            new ProducerRecord<String, Object>(new String(consumerRecord.headers().lastHeader(KafkaHeaders.REPLY_TOPIC).value()),
        null, UUID.randomUUID().toString(), response, mqRequestOptionalResponseHeaders(consumerRecord));

        oauthFourthEventhubProducer.send(producerRecord);

        if(SharedDataDictionary.thirdTopicFilterKey != null)
        SharedDataDictionary.OauthThirdEventhubConsumerConfig_PAYLOADS.put(SharedDataDictionary.thirdTopicFilterKey, response);

        return response;
       }
    }).onSuccess(lmsRequest -> log
            .info("successfully executed method {} and got result: paylaod={}",
                lmsRequest))
        .onFailure(throwuble -> log.error(throwuble.getMessage()))
        .toEither()
        .map(result -> {
          return Option.of(result);
        })
        .mapLeft((Throwable t) -> {

          if(t instanceof LmsUserException){

            //check if the methodName property is present
            JsonNode rootTree = null;
            try {
              rootTree = objectMapper.readTree(consumerRecord.value());
            } catch (JsonProcessingException e) {
              e.printStackTrace();
              throw new RuntimeException(e);
            }

            ArrayNode aPropertiesMap = (ArrayNode) rootTree.at(requestPropertiesPath);

            String methodName = StreamSupport.stream(Spliterators.spliteratorUnknownSize(aPropertiesMap.elements(), Spliterator.ORDERED), false).filter(objectNode -> objectNode.get("aTag").textValue().equals("METHOD_NAME")).findFirst().get().findValue("aValue").textValue();

            if(t instanceof DataNotFound){
              ((DataNotFound)t).setAContext(appendBisTagsToAContext(((DataNotFound)t).getAContext(), methodName));
            }
            else if(t instanceof SystemFailure){
              ((SystemFailure)t).setAContext(appendBisTagsToAContext(((SystemFailure)t).getAContext(), methodName));
            }
            else if(t instanceof BusinessViolation){
              ((BusinessViolation)t).setAContext(appendBisTagsToAContext(((BusinessViolation)t).getAContext(), methodName));
            }
            else if(t instanceof InvalidData){
              ((InvalidData)t).setAContext(appendBisTagsToAContext(((InvalidData)t).getAContext(), methodName));
            }
            else if(t instanceof AccessDenied){
              ((AccessDenied)t).setAContext(appendBisTagsToAContext(((AccessDenied)t).getAContext(), methodName));
            }
            else if(t instanceof MultipleExceptions){
              ((MultipleExceptions)t).setAContext(appendBisTagsToAContext(((MultipleExceptions)t).getAContext(), methodName));
            }
            else if(t instanceof NotImplemented){
              ((NotImplemented)t).setAContext(appendBisTagsToAContext(((NotImplemented)t).getAContext(), methodName));
            }
            else if(t instanceof ObjectNotFound){
              ((ObjectNotFound)t).setAContext(appendBisTagsToAContext(((ObjectNotFound)t).getAContext(), methodName));
            }

            //we need to send back an errorResponses to the caller
            ProducerRecord<String, Throwable> producerRecord =
                new ProducerRecord<String, Throwable>(new String(consumerRecord.headers().lastHeader(KafkaHeaders.REPLY_TOPIC).value()),
                    null, UUID.randomUUID().toString(), t, mqRequestOptionalResponseHeaders(consumerRecord));

            oauthFourthEventhubProducer.send(producerRecord);
          }

          if(SharedDataDictionary.thirdTopicFilterKey != null)
          SharedDataDictionary.OauthThirdEventhubConsumerConfig_PAYLOADS.put(SharedDataDictionary.thirdTopicFilterKey, t);

          return Throwables.fromThrowable(t);
        });

    return eitherRightOrLeft;
  }

  protected List<Header> mqRequestOptionalResponseHeaders(final ConsumerRecord consumerRecord){

    for (Header header : consumerRecord.headers()) {
      System.out.println("Header:: name=+header.key(), value=" +new String(header.value()));
    }

    return  List.of(new RecordHeader(KafkaConstants.JMS_IBM_Format_HEADER_LABEL, "MQSTR".getBytes()), messageIdRecordHeader(consumerRecord));
  }

  protected RecordHeader messageIdRecordHeader(final ConsumerRecord consumerRecord){
    if (Objects.nonNull(consumerRecord.headers().lastHeader(KafkaConstants.JMS_MESSAGE_ID_HEADER_LABEL))) {
      if(Objects.nonNull(consumerRecord.headers().lastHeader(KafkaConstants.JMS_MESSAGE_ID_HEADER_LABEL).value())){

        log.info("found "+KafkaConstants.JMS_MESSAGE_ID_HEADER_LABEL+" header value ="+new String(consumerRecord.headers().lastHeader(KafkaConstants.JMS_MESSAGE_ID_HEADER_LABEL).value()));

        //mapping jms_message_id to kafka_correlationid as per John Raflores
        return new RecordHeader(KafkaHeaders.CORRELATION_ID, consumerRecord.headers().lastHeader(KafkaConstants.JMS_MESSAGE_ID_HEADER_LABEL).value());
      }
    }

    return new RecordHeader("","".getBytes());
  }

  protected BisContext appendBisTagsToAContext(final BisContext aContext, final String methodName){
    ObjectProperty[] existingObjectProperties = aContext.getAProperties();

    List<ObjectProperty> objectPropertiesMerge = List.ofAll(Arrays.asList(existingObjectProperties));

    ObjectProperty[] objectProperties = new ObjectProperty[objectPropertiesMerge.size()+2];

    System.arraycopy(objectPropertiesMerge.appendAll(List.of(new ObjectProperty(ApropertiesTagLabelName.METHOD_NAME.name(), methodName), new ObjectProperty(ApropertiesTagLabelName.APP_NAME.name(), bisType))).map(o -> (ObjectProperty)o).toJavaArray(),0,objectProperties,0, objectProperties.length);

    aContext.setAProperties(objectProperties);

    return aContext;
  }

  @Override
  public Throwables handleException(Throwables exs) {
    return exs;
  }
}
