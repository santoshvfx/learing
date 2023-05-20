package com.att.lms.bis.service;

import com.att.lms.bis.common.dto.Throwables;
import com.att.lms.bis.common.persistence.IPersistable;
import com.att.lms.bis.dto.lim.*;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.bis_types.TelephoneNumber;
import com.sbc.eia.idl.lim.*;
import com.sbc.eia.idl.lim_types.Address;
import com.sbc.eia.idl.lim_types.AddressOpt;
import com.sbc.eia.idl.lim_types.LocationPropertiesToGetSeqOpt;
import com.sbc.eia.idl.lim_types.ProviderLocationPropertiesSeqOpt;
import com.sbc.eia.idl.types.CompositeObjectKey;
import com.sbc.eia.idl.types.LongOpt;
import com.sbc.eia.idl.types.StringOpt;
import io.vavr.control.Either;
import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LimService implements ILimService {

  public static final String SUCCESS_MESSAGE_TEMPLATE = "operation {} succeeded with value {}";

  public static final String FAILURE_MESSAGE_TEMPLATE =
      "operation {} failed with error type={}, msg={}";

  @Autowired LimLegacyService legacyService;

  public Either<Throwables, PingReturn> ping(PingRequestDto requestDto) {
    return Try.of(() -> legacyService.ping(requestDto.getABisContext()))
            .onSuccess(
                    result ->
                            log.info(
                                    SUCCESS_MESSAGE_TEMPLATE,
                                    new Object() {}.getClass().getEnclosingMethod().getName(),
                                    result))
            .onFailure(
                    exc ->
                            log.error(
                                    FAILURE_MESSAGE_TEMPLATE,
                                    new Object() {}.getClass().getEnclosingMethod().getName(),
                                    exc,
                                    exc.getMessage()))
            .toEither()
            .mapLeft(IPersistable::throwables);
  }

  public Either<Throwables, SelfTestReturn> selfTest(SelfTestRequestDto requestDto) {
    return Try.of(() -> legacyService.selfTest(requestDto.getABisContext()))
        .onSuccess(
            result ->
                log.info(
                    SUCCESS_MESSAGE_TEMPLATE,
                    new Object() {}.getClass().getEnclosingMethod().getName(),
                    result))
        .onFailure(
            exc ->
                log.error(
                    FAILURE_MESSAGE_TEMPLATE,
                    new Object() {}.getClass().getEnclosingMethod().getName(),
                    exc,
                    exc.getMessage()))
        .toEither()
        .mapLeft(IPersistable::throwables);
  }

  public Either<Throwables, UpdateBillingAddressReturn> updateBillingAddress(UpdateBillingAddressRequestDto requestDto) {
    return Try.of(() -> legacyService.updateBillingAddress(
                    requestDto.getABisContext(),
                    requestDto.getABillingAccountKey(),
                    requestDto.getAOldAddress(),
                    requestDto.getANewAddress(),
                    requestDto.getADeliveryPointValidationCode(),
                    requestDto.getAContactName()))
            .onSuccess(
                    result ->
                            log.info(
                                    SUCCESS_MESSAGE_TEMPLATE,
                                    new Object() {}.getClass().getEnclosingMethod().getName(),
                                    result))
            .onFailure(
                    exc ->
                            log.error(
                                    FAILURE_MESSAGE_TEMPLATE,
                                    new Object() {}.getClass().getEnclosingMethod().getName(),
                                    exc,
                                    exc.getMessage()))
            .toEither()
            .mapLeft(IPersistable::throwables);
  }

  public Either<Throwables, FieldAddressReturn> fieldAddress(FieldAddressRequestDto requestDto) {
    return Try.of(() -> legacyService.fieldAddress(
                    requestDto.getABisContext(),
                    requestDto.getAUnfieldedAddress()))
            .onSuccess(
                    result ->
                            log.info(
                                    SUCCESS_MESSAGE_TEMPLATE,
                                    new Object() {}.getClass().getEnclosingMethod().getName(),
                                    result))
            .onFailure(
                    exc ->
                            log.error(
                                    FAILURE_MESSAGE_TEMPLATE,
                                    new Object() {}.getClass().getEnclosingMethod().getName(),
                                    exc,
                                    exc.getMessage()))
            .toEither()
            .mapLeft(IPersistable::throwables);
  }

  public Either<Throwables, RetrieveLocationForAddressReturn> retrieveLocationForAddress(RetrieveLocationForAddressRequestDto requestDto) {
    return Try.of(() -> legacyService.retrieveLocationForAddress(

                    requestDto.getABisContext(),
                    requestDto.getAAddress(),
                    requestDto.getAProviderLocationProperties(),
                    requestDto.getAMaxBasicAddressAlternatives(),
                    requestDto.getAMaxLivingUnitAlternatives(),
                    requestDto.getAExchangeCode()))
            .onSuccess(
                    result ->
                            log.info(
                                    SUCCESS_MESSAGE_TEMPLATE,
                                    new Object() {}.getClass().getEnclosingMethod().getName(),
                                    result))
            .onFailure(
                    exc ->
                            log.error(
                                    FAILURE_MESSAGE_TEMPLATE,
                                    new Object() {}.getClass().getEnclosingMethod().getName(),
                                    exc,
                                    exc.getMessage()))
            .toEither()
            .mapLeft(IPersistable::throwables);
  }

  public Either<Throwables, RetrieveLocationForPostalAddressReturn> retrieveLocationForPostalAddress(RetrieveLocationForPostalAddressRequestDto requestDto) {
    return Try.of(() -> legacyService.retrieveLocationForPostalAddress(

                    requestDto.getABisContext(),
                    requestDto.getAAddress(),
                    requestDto.getAMaxAddressAlternatives()))
            .onSuccess(
                    result ->
                            log.info(
                                    SUCCESS_MESSAGE_TEMPLATE,
                                    new Object() {}.getClass().getEnclosingMethod().getName(),
                                    result))
            .onFailure(
                    exc ->
                            log.error(
                                    FAILURE_MESSAGE_TEMPLATE,
                                    new Object() {}.getClass().getEnclosingMethod().getName(),
                                    exc,
                                    exc.getMessage()))
            .toEither()
            .mapLeft(IPersistable::throwables);
  }

  public Either<Throwables, RetrieveLocationForServiceReturn> retrieveLocationForService(RetrieveLocationForServiceRequestDto requestDto) {
    return Try.of(() -> legacyService.retrieveLocationForService(

                    requestDto.getABisContext(),
                    requestDto.getATelephoneNumber(),
                    requestDto.getAProviderLocationProperties(),
                    requestDto.getAMaxBasicAddressAlternatives(),
                    requestDto.getAMaxLivingUnitAlternatives()))
            .onSuccess(
                    result ->
                            log.info(
                                    SUCCESS_MESSAGE_TEMPLATE,
                                    new Object() {}.getClass().getEnclosingMethod().getName(),
                                    result))
            .onFailure(
                    exc ->
                            log.error(
                                    FAILURE_MESSAGE_TEMPLATE,
                                    new Object() {}.getClass().getEnclosingMethod().getName(),
                                    exc,
                                    exc.getMessage()))
            .toEither()
            .mapLeft(IPersistable::throwables);
  }

  public Either<Throwables, RetrieveLocationForServiceAddressReturn> retrieveLocationForServiceAddress(RetrieveLocationForServiceAddressRequestDto requestDto) {
    return Try.of(() -> legacyService.retrieveLocationForServiceAddress(
                    requestDto.getABisContext(),
                    requestDto.getAAddress(),
                    requestDto.getALocationPropertiesToGet(),
                    requestDto.getAPreviousCustomerName(),
                    requestDto.getACrossBoundaryState(),
                    requestDto.getAMaxBasicAddressAlternatives(),
                    requestDto.getAMaxLivingUnitAlternatives()))
            .onSuccess(
                    result ->
                            log.info(
                                    SUCCESS_MESSAGE_TEMPLATE,
                                    new Object() {}.getClass().getEnclosingMethod().getName(),
                                    result))
            .onFailure(
                    exc ->
                            log.error(
                                    FAILURE_MESSAGE_TEMPLATE,
                                    new Object() {}.getClass().getEnclosingMethod().getName(),
                                    exc,
                                    exc.getMessage()))
            .toEither()
            .mapLeft(IPersistable::throwables);
  }

  public Either<Throwables, RetrieveLocationForE911AddressReturn> retrieveLocationForE911Address(
      RetrieveLocationForE911AddressRequestDto requestDto) {
    return Try.of(
            () ->
                legacyService.retrieveLocationForE911Address(
                    requestDto.getAContext(),
                    requestDto.getAAddress(),
                    requestDto.getAExchangeCode(),
                    requestDto.getAMaxAddressAlternatives()))
        .onSuccess(
            result ->
                log.info(
                    SUCCESS_MESSAGE_TEMPLATE,
                    new Object() {}.getClass().getEnclosingMethod().getName(),
                    result))
        .onFailure(
            exc ->
                log.error(
                    FAILURE_MESSAGE_TEMPLATE,
                    new Object() {}.getClass().getEnclosingMethod().getName(),
                    exc,
                    exc.getMessage()))
        .toEither()
        .mapLeft(IPersistable::throwables);
  }

  public Either<Throwables, RetrieveLocationForGeoAddressReturn> retrieveLocationForGeoAddress(
      RetrieveLocationForGeoAddressRequestDto requestDto) {
    return Try.of(
            () ->
                legacyService.retrieveLocationForGeoAddress(
                    requestDto.getAContext(),
                    requestDto.getAAddress(),
                    requestDto.getAMaxAddressAlternatives()))
        .onSuccess(
            result ->
                log.info(
                    SUCCESS_MESSAGE_TEMPLATE,
                    new Object() {}.getClass().getEnclosingMethod().getName(),
                    result))
        .onFailure(
            exc ->
                log.error(
                    FAILURE_MESSAGE_TEMPLATE,
                    new Object() {}.getClass().getEnclosingMethod().getName(),
                    exc,
                    exc.getMessage()))
        .toEither()
        .mapLeft(IPersistable::throwables);
  }

  public Either<Throwables, RetrieveLocationForPostalAddress2Return>
      retrieveLocationForPostalAddress2(RetrieveLocationForPostalAddress2RequestDto requestDto) {
    return Try.of(
            () ->
                legacyService.retrieveLocationForPostalAddress2(
                    requestDto.getABisContext(),
                    requestDto.getAAddress(),
                    requestDto.getAMaxAddressAlternatives(),
                    requestDto.getAMaxCassCharPerLine()))
        .onSuccess(
            result ->
                log.info(
                    SUCCESS_MESSAGE_TEMPLATE,
                    new Object() {}.getClass().getEnclosingMethod().getName(),
                    result))
        .onFailure(
            exc ->
                log.error(
                    FAILURE_MESSAGE_TEMPLATE,
                    new Object() {}.getClass().getEnclosingMethod().getName(),
                    exc,
                    exc.getMessage()))
        .toEither()
        .mapLeft(IPersistable::throwables);
  }

  public Either<Throwables, RetrieveServiceAreaByPostalCodeReturn> retrieveServiceAreaByPostalCode(
      RetrieveServiceAreaByPostalCodeRequestDto requestDto) {
    return Try.of(
            () ->
                legacyService.retrieveServiceAreaByPostalCode(
                    requestDto.getAContext(),
                    requestDto.getACingularSalesChannel(),
                    requestDto.getAPostalCode(),
                    requestDto.isARequestServiceAreaIndicator(),
                    requestDto.isARequestMarketInformationIndicator()))
        .onSuccess(
            result ->
                log.info(
                    SUCCESS_MESSAGE_TEMPLATE,
                    new Object() {}.getClass().getEnclosingMethod().getName(),
                    result))
        .onFailure(
            exc ->
                log.error(
                    FAILURE_MESSAGE_TEMPLATE,
                    new Object() {}.getClass().getEnclosingMethod().getName(),
                    exc,
                    exc.getMessage()))
        .toEither()
        .mapLeft(IPersistable::throwables);
  }

  public Either<Throwables, RetrieveLocationForTelephoneNumberReturn>
      retrieveLocationForTelephoneNumber(RetrieveLocationForTelephoneNumberRequestDto requestDto) {
    return Try.of(
            () ->
                legacyService.retrieveLocationForTelephoneNumber(
                    requestDto.getAContext(),
                    requestDto.getATelephoneNumber(),
                    requestDto.getALocationPropertiesToGet()))
        .onSuccess(
            result ->
                log.info(
                    SUCCESS_MESSAGE_TEMPLATE,
                    new Object() {}.getClass().getEnclosingMethod().getName(),
                    result))
        .onFailure(
            exc ->
                log.error(
                    FAILURE_MESSAGE_TEMPLATE,
                    new Object() {}.getClass().getEnclosingMethod().getName(),
                    exc,
                    exc.getMessage()))
        .toEither()
        .mapLeft(IPersistable::throwables);
  }
}
