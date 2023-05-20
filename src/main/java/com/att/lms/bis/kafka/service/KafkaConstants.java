package com.att.lms.bis.kafka.service;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConstants {
  public static int sleepInterval;

  public static int maxIterations;

  @Value("${rcEgress.response.sleepInterval:2000}")
  public void setSleepInterval(int sleepInterval){
    this.sleepInterval = sleepInterval;
  }

  @Value("${rcEgress.response.maxIterations:60}")
  public void setMaxIterations(int maxIterations){
    this.maxIterations = maxIterations;
  }

  public static final String JMS_IBM_Format_HEADER_LABEL = "JMS_IBM_Format";

  public static final String JMS_MESSAGE_ID_HEADER_LABEL = "jms_messageId";

}
