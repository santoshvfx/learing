package com.att.lms.bis.service;

import com.att.lms.bis.common.dto.Throwables;
import com.att.lms.bis.common.persistence.IPersistable;
import com.att.lms.bis.dto.lim.FieldAddressRequestDto;
import com.att.lms.bis.dto.lim.PingRequestDto;
import com.att.lms.bis.dto.lim.RetrieveLocationForAddressRequestDto;
import com.att.lms.bis.dto.lim.RetrieveLocationForE911AddressRequestDto;
import com.att.lms.bis.dto.lim.RetrieveLocationForGeoAddressRequestDto;
import com.att.lms.bis.dto.lim.RetrieveLocationForPostalAddress2RequestDto;
import com.att.lms.bis.dto.lim.RetrieveLocationForPostalAddressRequestDto;
import com.att.lms.bis.dto.lim.RetrieveLocationForServiceAddressRequestDto;
import com.att.lms.bis.dto.lim.RetrieveLocationForServiceRequestDto;
import com.att.lms.bis.dto.lim.RetrieveLocationForTelephoneNumberRequestDto;
import com.att.lms.bis.dto.lim.RetrieveServiceAreaByPostalCodeRequestDto;
import com.att.lms.bis.dto.lim.SelfTestRequestDto;
import com.att.lms.bis.dto.lim.UpdateBillingAddressRequestDto;
import com.sbc.eia.idl.lim.FieldAddressReturn;
import com.sbc.eia.idl.lim.PingReturn;
import com.sbc.eia.idl.lim.RetrieveLocationForAddressReturn;
import com.sbc.eia.idl.lim.RetrieveLocationForE911AddressReturn;
import com.sbc.eia.idl.lim.RetrieveLocationForGeoAddressReturn;
import com.sbc.eia.idl.lim.RetrieveLocationForPostalAddress2Return;
import com.sbc.eia.idl.lim.RetrieveLocationForPostalAddressReturn;
import com.sbc.eia.idl.lim.RetrieveLocationForServiceAddressReturn;
import com.sbc.eia.idl.lim.RetrieveLocationForServiceReturn;
import com.sbc.eia.idl.lim.RetrieveLocationForTelephoneNumberReturn;
import com.sbc.eia.idl.lim.RetrieveServiceAreaByPostalCodeReturn;
import com.sbc.eia.idl.lim.SelfTestReturn;
import com.sbc.eia.idl.lim.UpdateBillingAddressReturn;
import io.vavr.control.Either;
import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public interface ILimService {

  public Either<Throwables, PingReturn> ping(PingRequestDto requestDto);

  public Either<Throwables, SelfTestReturn> selfTest(SelfTestRequestDto requestDto);

  public Either<Throwables, UpdateBillingAddressReturn> updateBillingAddress(UpdateBillingAddressRequestDto requestDto);

  public Either<Throwables, FieldAddressReturn> fieldAddress(FieldAddressRequestDto requestDto);

  public Either<Throwables, RetrieveLocationForAddressReturn> retrieveLocationForAddress(RetrieveLocationForAddressRequestDto requestDto);

  public Either<Throwables, RetrieveLocationForPostalAddressReturn> retrieveLocationForPostalAddress(RetrieveLocationForPostalAddressRequestDto requestDto);

  public Either<Throwables, RetrieveLocationForServiceReturn> retrieveLocationForService(RetrieveLocationForServiceRequestDto requestDto);

  public Either<Throwables, RetrieveLocationForServiceAddressReturn> retrieveLocationForServiceAddress(RetrieveLocationForServiceAddressRequestDto requestDto);

  public Either<Throwables, RetrieveLocationForE911AddressReturn> retrieveLocationForE911Address(
      RetrieveLocationForE911AddressRequestDto requestDto);

  public Either<Throwables, RetrieveLocationForGeoAddressReturn> retrieveLocationForGeoAddress(
      RetrieveLocationForGeoAddressRequestDto requestDto);

  public Either<Throwables, RetrieveLocationForPostalAddress2Return>
      retrieveLocationForPostalAddress2(RetrieveLocationForPostalAddress2RequestDto requestDto);

  public Either<Throwables, RetrieveServiceAreaByPostalCodeReturn> retrieveServiceAreaByPostalCode(
      RetrieveServiceAreaByPostalCodeRequestDto requestDto);

  public Either<Throwables, RetrieveLocationForTelephoneNumberReturn>
      retrieveLocationForTelephoneNumber(RetrieveLocationForTelephoneNumberRequestDto requestDto);
}
