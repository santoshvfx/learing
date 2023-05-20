package com.att.lms.bis.controller;

import com.att.lms.bis.common.BisApi;
import com.att.lms.bis.common.CommonApi;
import com.att.lms.bis.common.controller.IController;
import com.att.lms.bis.dto.lim.*;
import com.att.lms.bis.service.LimService;
import com.sbc.eia.bis.framework.BisContextManager;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.lim.*;
import com.sbc.eia.idl.lim_types.*;
import com.sbc.eia.idl.pm.RetrieveIdentifiersReturn;
import com.sbc.eia.idl.types.EiaDateOpt;
import com.sbc.eia.idl.types.LongOpt;
import com.sbc.eia.idl.types.StringOpt;
import com.sbc.eia.idl.types.StringSeqOpt;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;
import java.util.UUID;
import java.util.function.Function;

import static io.vavr.API.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@RestController
@Validated
@RequestMapping(BisApi.LIM_BASE_URL)
public class LimController {

    public static final String PING_PATH_NAME = "ping";
    public static final String SELF_TEST_PATH_NAME = "selfTest";
    public static final String UPDATE_BILLING_ADDRESS_PATH_NAME = "updateBillingAddress";
    public static final String FIELD_ADDRESS_PATH_NAME = "fieldAddress";
    public static final String RETRIEVE_LOCATION_FOR_ADDRESS_PATH_NAME = "retrieveLocationForAddress";
    public static final String RETRIEVE_LOCATION_FOR_POSTAL_ADDRESS_PATH_NAME = "retrieveLocationForPostalAddress";
    public static final String RETRIEVE_LOCATION_FOR_SERVICE_PATH_NAME = "retrieveLocationForService";
    public static final String RETRIEVE_LOCATION_FOR_SERVICE_ADDRESS_PATH_NAME = "retrieveLocationForServiceAddress";
    public static final String RETRIEVE_LOCATION_FOR_E911_ADDRESS_PATH_NAME = "retrieveLocationForE911Address";
    public static final String RETRIEVE_LOCATION_FOR_GEO_ADDRESS_PATH_NAME = "retrieveLocationForGeoAddress";
    public static final String RETRIEVE_LOCATION_FOR_POSTAL_ADDRESS_2_PATH_NAME = "retrieveLocationForPostalAddress2";
    public static final String RETRIEVE_LOCATION_FOR_TELEPHONE_NUMBER_PATH_NAME = "retrieveLocationForTelephoneNumber";
    public static final String RETRIEVE_SERVICE_AREA_BY_POSTAL_CODE_PATH_NAME = "retrieveServiceAreaByPostalCode";

    @Autowired
    LimService limService;

    @ApiOperation(value = "ping", response = PingReturn.class)
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, path = PING_PATH_NAME)
    public @ResponseBody
    ResponseEntity<?> ping(@RequestBody PingRequestDto requestDto, HttpServletRequest httpServletRequest) {
        return  limService.ping(requestDto)
                .mapLeft(IController::handleException)
                .map(dtoOption -> {
                    return Match(dtoOption).of(
                            Case($(value -> Objects.isNull(value)),
                                    dto -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND)),
                            Case($(),
                                    dto -> {
                                        return new ResponseEntity(dto, HttpStatus.OK);
                                    })
                    );
                }).fold(Function.identity(), Function.identity());
    }


    @ApiOperation(value = "selfTest", response = RetrieveIdentifiersReturn.class)
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, path = SELF_TEST_PATH_NAME)
    public @ResponseBody
    ResponseEntity<?> selfTest(@RequestBody SelfTestRequestDto selfTestRequestDto, HttpServletRequest httpServletRequest) {
        return  limService.selfTest(selfTestRequestDto)
                .mapLeft(IController::handleException)
                .map(dtoOption -> {
                    return Match(dtoOption).of(
                            Case($(value -> Objects.isNull(value)),
                                    dto -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND)),
                            Case($(),
                                    dto -> {
                                        return new ResponseEntity(dto, HttpStatus.OK);
                                    })
                    );
                }).fold(Function.identity(), Function.identity());
    }

    @ApiOperation(value = "updateBillingAddress", response = UpdateBillingAddressReturn.class)
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, path = UPDATE_BILLING_ADDRESS_PATH_NAME)
    public @ResponseBody
    ResponseEntity<?> updateBillingAddress(@RequestBody UpdateBillingAddressRequestDto requestDto, HttpServletRequest httpServletRequest) {
        return  limService.updateBillingAddress(requestDto)
                .mapLeft(IController::handleException)
                .map(dtoOption -> {
                    return Match(dtoOption).of(
                            Case($(value -> Objects.isNull(value)),
                                    dto -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND)),
                            Case($(),
                                    dto -> {
                                        return new ResponseEntity(dto, HttpStatus.OK);
                                    })
                    );
                }).fold(Function.identity(), Function.identity());
    }

    @ApiOperation(value = "fieldAddress", response = FieldAddressReturn.class)
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, path = FIELD_ADDRESS_PATH_NAME)
    public @ResponseBody
    ResponseEntity<?> fieldAddress(@RequestBody FieldAddressRequestDto requestDto, HttpServletRequest httpServletRequest) {

        return  limService.fieldAddress(requestDto)
                .mapLeft(IController::handleException)
                .map(dtoOption -> {
                    return Match(dtoOption).of(
                            Case($(value -> Objects.isNull(value)),
                                    dto -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND)),
                            Case($(),
                                    dto -> {
                                        return new ResponseEntity(dto, HttpStatus.OK);
                                    })
                    );
                }).fold(Function.identity(), Function.identity());
    }

    @ApiOperation(value = "retrieveLocationForServiceAddress", response = RetrieveLocationForServiceAddressReturn.class)
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, path = RETRIEVE_LOCATION_FOR_SERVICE_ADDRESS_PATH_NAME)
    public @ResponseBody
    ResponseEntity<?> retrieveLocationForServiceAddress(@RequestBody RetrieveLocationForServiceAddressRequestDto requestDto, HttpServletRequest httpServletRequest) {
        return  limService.retrieveLocationForServiceAddress(requestDto)
                .mapLeft(IController::handleException)
                .map(dtoOption -> {
                    return Match(dtoOption).of(
                            Case($(value -> Objects.isNull(value)),
                                    dto -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND)),
                            Case($(),
                                    dto -> {
                                        return new ResponseEntity(dto, HttpStatus.OK);
                                    })
                    );
                }).fold(Function.identity(), Function.identity());

    }

    @ApiOperation(value = "retrieveLocationForAddress", response = RetrieveLocationForAddressReturn.class)
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, path = RETRIEVE_LOCATION_FOR_ADDRESS_PATH_NAME)
    public @ResponseBody
    ResponseEntity<?> retrieveLocationForAddress(@RequestBody RetrieveLocationForAddressRequestDto requestDto, HttpServletRequest httpServletRequest) {
       System.out.println("Address::"+requestDto.getAAddress());
       

        return  limService.retrieveLocationForAddress(requestDto)
                .mapLeft(IController::handleException)
                .map(dtoOption -> {
                    return Match(dtoOption).of(
                            Case($(value -> Objects.isNull(value)),
                                    dto -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND)),
                            Case($(),
                                    dto -> {
                                        return new ResponseEntity(dto, HttpStatus.OK);
                                    })
                    );
                }).fold(Function.identity(), Function.identity());
    }

    @ApiOperation(value = "retrieveLocationForService", response = RetrieveLocationForServiceReturn.class)
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, path = RETRIEVE_LOCATION_FOR_SERVICE_PATH_NAME)
    public @ResponseBody
    ResponseEntity<?> retrieveLocationForService(@RequestBody RetrieveLocationForServiceRequestDto requestDto, HttpServletRequest httpServletRequest) {
        return  limService.retrieveLocationForService(requestDto)
                .mapLeft(IController::handleException)
                .map(dtoOption -> {
                    return Match(dtoOption).of(
                            Case($(value -> Objects.isNull(value)),
                                    dto -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND)),
                            Case($(),
                                    dto -> {
                                        return new ResponseEntity(dto, HttpStatus.OK);
                                    })
                    );
                }).fold(Function.identity(), Function.identity());
    }

    @ApiOperation(value = "retrieveLocationForPostalAddress", response = RetrieveLocationForPostalAddressReturn.class)
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, path = RETRIEVE_LOCATION_FOR_POSTAL_ADDRESS_PATH_NAME)
    public @ResponseBody
    ResponseEntity<?> retrieveLocationForPostalAddress(@RequestBody RetrieveLocationForPostalAddressRequestDto requestDto, HttpServletRequest httpServletRequest) {
        return  limService.retrieveLocationForPostalAddress(requestDto)
                .mapLeft(IController::handleException)
                .map(dtoOption -> {
                    return Match(dtoOption).of(
                            Case($(value -> Objects.isNull(value)),
                                    dto -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND)),
                            Case($(),
                                    dto -> {
                                        return new ResponseEntity(dto, HttpStatus.OK);
                                    })
                    );
                }).fold(Function.identity(), Function.identity());
    }

    @ApiOperation(value = "retrieveLocationForE911Address", response = RetrieveLocationForE911AddressReturn.class)
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, path = RETRIEVE_LOCATION_FOR_E911_ADDRESS_PATH_NAME)
    public @ResponseBody
    ResponseEntity<?> retrieveLocationForE911Address(@RequestBody RetrieveLocationForE911AddressRequestDto requestDto, HttpServletRequest httpServletRequest) {
        return  limService.retrieveLocationForE911Address(requestDto)
                .mapLeft(IController::handleException)
                .map(dtoOption -> {
                    return Match(dtoOption).of(
                            Case($(value -> Objects.isNull(value)),
                                    dto -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND)),
                            Case($(),
                                    dto -> {
                                        return new ResponseEntity(dto, HttpStatus.OK);
                                    })
                    );
                }).fold(Function.identity(), Function.identity());
    }

    @ApiOperation(value = "retrieveLocationForGeoAddress", response = RetrieveLocationForGeoAddressReturn.class)
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, path = RETRIEVE_LOCATION_FOR_GEO_ADDRESS_PATH_NAME)
    public @ResponseBody
    ResponseEntity<?> retrieveLocationForGeoAddress(@RequestBody RetrieveLocationForGeoAddressRequestDto requestDto, HttpServletRequest httpServletRequest) {
        return  limService.retrieveLocationForGeoAddress(requestDto)
                .mapLeft(IController::handleException)
                .map(dtoOption -> {
                    return Match(dtoOption).of(
                            Case($(value -> Objects.isNull(value)),
                                    dto -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND)),
                            Case($(),
                                    dto -> {
                                        return new ResponseEntity(dto, HttpStatus.OK);
                                    })
                    );
                }).fold(Function.identity(), Function.identity());
    }


    @ApiOperation(value = "retrieveLocationForPostalAddress2", response = RetrieveLocationForPostalAddress2Return.class)
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, path = RETRIEVE_LOCATION_FOR_POSTAL_ADDRESS_2_PATH_NAME)
    public @ResponseBody
    ResponseEntity<?> retrieveLocationForPostalAddress2(@RequestBody RetrieveLocationForPostalAddress2RequestDto requestDto, HttpServletRequest httpServletRequest) {
        return  limService.retrieveLocationForPostalAddress2(requestDto)
                .mapLeft(IController::handleException)
                .map(dtoOption -> {
                    return Match(dtoOption).of(
                            Case($(value -> Objects.isNull(value)),
                                    dto -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND)),
                            Case($(),
                                    dto -> {
                                        return new ResponseEntity(dto, HttpStatus.OK);
                                    })
                    );
                }).fold(Function.identity(), Function.identity());
    }


    @ApiOperation(value = "retrieveServiceAreaByPostalCode", response = RetrieveServiceAreaByPostalCodeReturn.class)
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, path = RETRIEVE_SERVICE_AREA_BY_POSTAL_CODE_PATH_NAME)
    public @ResponseBody
    ResponseEntity<?> retrieveServiceAreaByPostalCode(@RequestBody RetrieveServiceAreaByPostalCodeRequestDto requestDto, HttpServletRequest httpServletRequest) {
        return  limService.retrieveServiceAreaByPostalCode(requestDto)
                .mapLeft(IController::handleException)
                .map(dtoOption -> {
                    return Match(dtoOption).of(
                            Case($(value -> Objects.isNull(value)),
                                    dto -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND)),
                            Case($(),
                                    dto -> {
                                        return new ResponseEntity(dto, HttpStatus.OK);
                                    })
                    );
                }).fold(Function.identity(), Function.identity());
    }


    @ApiOperation(value = "retrieveLocationForTelephoneNumber", response = RetrieveLocationForTelephoneNumberReturn.class)
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, path = RETRIEVE_LOCATION_FOR_TELEPHONE_NUMBER_PATH_NAME)
    public @ResponseBody
    ResponseEntity<?> retrieveLocationForTelephoneNumber(@RequestBody RetrieveLocationForTelephoneNumberRequestDto requestDto, HttpServletRequest httpServletRequest) {
        return  limService.retrieveLocationForTelephoneNumber(requestDto)
                .mapLeft(IController::handleException)
                .map(dtoOption -> {
                    return Match(dtoOption).of(
                            Case($(value -> Objects.isNull(value)),
                                    dto -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND)),
                            Case($(),
                                    dto -> {
                                        return new ResponseEntity(dto, HttpStatus.OK);
                                    })
                    );
                }).fold(Function.identity(), Function.identity());
    }


}
