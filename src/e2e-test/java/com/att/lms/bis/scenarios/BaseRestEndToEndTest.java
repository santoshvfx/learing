package com.att.lms.bis.scenarios;

import com.att.lms.bis.EndtoEndTestApplication;
import com.att.lms.bis.common.CommonApi;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import io.vavr.Tuple;
import io.vavr.Tuple2;
import org.apache.commons.lang3.StringUtils;
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

import javax.annotation.PostConstruct;

@ExtendWith(SpringExtension.class)
@DirtiesContext
@TestPropertySource(locations = {"classpath:application-e2e-test.properties"})
@SpringBootTest(classes = {EndtoEndTestApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, properties = {"server.port=6543"})
public abstract class BaseRestEndToEndTest {

    protected static final Logger LOG = LoggerFactory.getLogger(BaseRestEndToEndTest.class);
    public static final String DEFAULT_SCHEME = "http";
    public static final String DEFAULT_HOST = "localhost";
    public static String DEFAULT_SPRING_BOOT_BASE_URI;

    protected Tuple2 basicAuthCreds;

    protected WireMockServer wireMockServer;

    @Autowired
    ObjectMapper objectMapper;
    @Value("${server.port}")
    int port;

    //==========================================================================================

    @BeforeEach
    public void setup() {
        LOG.info("calling setup beforeEach");
        basicAuthCreds = Tuple.of("m49476@lms.att.com", "Middleware$123");
    }

    @AfterEach
    public void tearDown() {
        LOG.info("calling tearDown afterEach");
    }

    @AfterEach
    void stopWireMockServer() {
        if (withWiremock())
            this.wireMockServer.stop();
    }

    protected abstract boolean withWiremock();

    @PostConstruct
    private void init() {
        DEFAULT_SPRING_BOOT_BASE_URI = StringUtils.join(DEFAULT_SCHEME, "://", DEFAULT_HOST, ":", port);
    }

}