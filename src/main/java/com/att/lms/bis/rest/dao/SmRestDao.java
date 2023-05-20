package com.att.lms.bis.rest.dao;

import com.att.lms.bis.common.config.settings.IHttpSettings;
import com.att.lms.bis.common.dao.impl.RestEndpointDao;
import com.att.lms.bis.common.dto.Throwables;
import com.att.lms.bis.common.rest.Restful;
import com.att.lms.bis.common.url.UriPath;
import com.att.lms.bis.dto.sm.GetServiceLocationRequestDto;
import com.att.lms.bis.dto.sm.PingRequestDto;
import com.sbc.eia.idl.sm.GetServiceAddressReturn;
import com.sbc.eia.idl.sm.PingReturn;
import io.vavr.collection.HashMap;
import io.vavr.collection.Map;
import io.vavr.control.Either;
import io.vavr.control.Option;
import java.util.function.Function;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class SmRestDao extends RestEndpointDao implements Restful {

    public static final String PING_PATH_NAME = "ping";
    public static final String GET_SERVICE_LOCATION_PATH_NAME = "getServiceLocation";

    public Either<Throwables, Option<PingReturn>> ping(
            IHttpSettings httpSettings,
            PingRequestDto requestDto,
            ParameterizedTypeReference<PingReturn> type,
            MultiValueMap<String, String> headers) {

        Map<String, String> parameters = HashMap.empty();

        return tryHttp(
                httpPostFunc(UriPath.authorizationTokenBase64Encoded(httpSettings),
                        type, requestDto, headers),
                UriPath.builder().iHttpSettings(httpSettings)
                        .parameters(parameters)
                        .appendPath(httpSettings.getPath())
                        .build()
                        .toString() + PING_PATH_NAME,
                new Object() {
                }.getClass().getEnclosingMethod().getName());
    }


    public Either<Throwables, Option<GetServiceAddressReturn>> getServiceLocation(
            IHttpSettings httpSettings,
            GetServiceLocationRequestDto requestDto,
            ParameterizedTypeReference<GetServiceAddressReturn> type,
            MultiValueMap<String, String> headers) {

        Map<String, String> parameters = HashMap.empty();

        return tryHttp(
                httpPostFunc(UriPath.authorizationTokenBase64Encoded(httpSettings),
                        type, requestDto, headers),
                UriPath.builder().iHttpSettings(httpSettings)
                        .parameters(parameters)
                        .appendPath(httpSettings.getPath())
                        .build()
                        .toString() + GET_SERVICE_LOCATION_PATH_NAME,
                new Object() {
                }.getClass().getEnclosingMethod().getName());
    }

    @Override
    public Logger log() {
        return log;
    }

    @Override
    public Function<String, RestTemplate> restTemplate() {
        return this.getPartialRestTemplateBuilder();
    }
}