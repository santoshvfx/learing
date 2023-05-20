package com.att.lms.bis.security;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;

import io.vavr.collection.Stream;
import org.junit.jupiter.params.provider.Arguments;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.request.RequestPostProcessor;

public interface ParameterizedDataForPrototypicalSpringSecurityAuthenticationAuthorizationIT {

    final RequestPostProcessor LMS_MECHID_REQUEST_POST_PROCESSOR = httpBasic(User.M49476.getUserId(), User.M49476.getPassword());

    final RequestPostProcessor MISTER_NOBODY_REQUEST_POST_PROCESSOR = httpBasic(User.MISTER_NOBODY.getUserId(), User.MISTER_NOBODY.getPassword());

    final String MODULE_CUSTOM_NAMING_PLUS_ENDPOINT_TYPES = "MODULE_CUSTOM_NAMING.name() and (#body.endpointType == EndpointType.VRA or #body.endpointType == EndpointType.VRAC)";

    public static Stream<Arguments> usersToExpectedAuthResultsMappings() {
        return Stream.of(
            Arguments.of(User.M49476, HttpStatus.OK.value()),
            Arguments.of(User.MISTER_NOBODY, HttpStatus.UNAUTHORIZED.value())
        );
    }

    public static Stream<Arguments> usersToExpectedSuccessfulAuthResultsMappings() {
        return Stream.of(
                Arguments.of(User.M49476, HttpStatus.OK.value())
        );
    }
}
