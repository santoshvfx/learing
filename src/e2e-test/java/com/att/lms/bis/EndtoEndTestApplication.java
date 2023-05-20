package com.att.lms.bis;

import java.util.TimeZone;
import javax.annotation.PostConstruct;

import com.att.lms.bis.service.LimLegacyService;
import com.att.lms.bis.service.LimLegacyStubService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@ServletComponentScan
@SpringBootApplication
@EntityScan(basePackages = {"com.att.transformation"})
@Configuration
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@ComponentScan(basePackages = {"com.att.lms","com.att.transformation"})
public class EndtoEndTestApplication {

  @PostConstruct
  void started() {
    System.setProperty("ULA.configURL", "ULALogging.prop");
    System.setProperty("OrbRequestTimeoutSec", "60");
    System.setProperty("VoltageFlag","on");
    System.setProperty("VoltageExceptions","VoltageExceptions.properties");
    System.setProperty("VoltageIdentities","VoltageIdentities.properties");
    System.setProperty("VoltageServerConnection","VoltageServerConnection.properties");

    System.setProperty("user.timezone", "UTC");
    TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
  }

  public static void main(String[] args) {
    SpringApplication.run(EndtoEndTestApplication.class, args);
  }

  @Primary
  @Bean
  public LimLegacyService limLegacyStubService() throws Exception {
    return new LimLegacyStubService();
  }
}
