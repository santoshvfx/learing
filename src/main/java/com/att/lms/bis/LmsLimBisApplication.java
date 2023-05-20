package com.att.lms.bis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration;

import javax.annotation.PostConstruct;
import java.util.TimeZone;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {
    DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class,  JmxAutoConfiguration.class})
@ComponentScan(basePackages = {"com.att.lms","com.att.transformation"})
public class LmsLimBisApplication {

    private static final Logger LOG = LoggerFactory.getLogger(LmsLimBisApplication.class);

    public LmsLimBisApplication() {
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(LmsLimBisApplication.class, args);
        System.setProperty("VoltageFlag","on");
        System.setProperty("VoltageExceptions","VoltageExceptions.properties");
        System.setProperty("VoltageIdentities","VoltageIdentities.properties");
        System.setProperty("VoltageServerConnection","VoltageServerConnection.properties");
        // Setting this property to correct the SNI issue with aaf_url
        System.setProperty("jsse.enableSNIExtension", "true");
        // TODO: CHANGE_ME add any System properties that are NOT Bis Platform
    }

    @PostConstruct
    void started() {
        System.setProperty("user.timezone", "UTC");
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        LOG.info("-- LmsLimBisApplication - It' UP!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        //throw new RuntimeException("It's Broken!!!!!!!!!!!!!");
    }

}
