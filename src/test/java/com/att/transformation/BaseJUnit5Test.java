package com.att.transformation;

import com.att.lms.bis.EndtoEndTestApplication;
import com.att.lms.bis.common.config.JacksonConfig;
import com.att.lms.bis.common.config.RestConfig;
import com.att.lms.bis.common.config.settings.ProxyServerEndpointSettings;
import com.att.lms.bis.config.LimLegacyConfig;
import com.att.lms.bis.rest.config.settings.BisRestEndpointSettings;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(value = {MockitoExtension.class, SpringExtension.class})
@SpringBootTest(classes = {
        EndtoEndTestApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, properties = {
        "server.port=2200"})
@TestPropertySource(locations = {"classpath:application-e2e-test.properties",
        "classpath:application-e2e-real.properties"})
@ComponentScan(basePackages = {"com.att.lms", "com.sbc.bis"}, useDefaultFilters = true)
public class BaseJUnit5Test {

    protected ObjectMapper objectMapper;

    public BaseJUnit5Test() {
        objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(com.fasterxml.jackson.annotation.JsonInclude.Include.ALWAYS);
    }
}
