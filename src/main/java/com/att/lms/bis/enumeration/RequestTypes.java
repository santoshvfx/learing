package com.att.lms.bis.enumeration;

import com.att.lms.bis.common.config.StaticContextAccessor;
import com.att.lms.bis.common.dto.Throwables;
import com.att.lms.bis.dto.lim.RetrieveLocationForAddressRequestDto;
import com.att.lms.bis.dto.lim.RetrieveLocationForServiceAddressRequestDto;
import com.att.lms.bis.service.ILimService;
import com.att.lms.bis.service.LimService;
import io.vavr.control.Either;
import java.util.Arrays;
import java.util.Random;
import java.util.function.Function;

public enum RequestTypes {

  RETRIEVE_LOCATION_FOR_ADDRESS("retrieveLocationForAddress", com.att.lms.bis.dto.lim.RetrieveLocationForAddressRequestDto.class,
      (RetrieveLocationForAddressRequestDto r) -> StaticContextAccessor.getBean(ILimService.class).retrieveLocationForAddress(r));

  private String propertyName;

  private Class clazz;

  private Function function;

  private <T,R> RequestTypes(String propertyName, Class clazz, Function<T, Either<Throwables, R>> limBisFunction) {
    this.propertyName = propertyName;
    this.clazz = clazz;
    this.function = limBisFunction;
  }

  public static RequestTypes fromPropertyName(String name) {
    RequestTypes[] var1 = values();
    int var2 = var1.length;

    for (int var3 = 0; var3 < var2; ++var3) {
      RequestTypes value = var1[var3];
      if (value.getPropertyName().equals(name)) {
        return value;
      }
    }

    return null;
  }

  public static RequestTypes fromClass(Class clazz) {
    RequestTypes[] var1 = values();
    int var2 = var1.length;

    for (int var3 = 0; var3 < var2; ++var3) {
      RequestTypes value = var1[var3];
      if (value.getClazz().equals(clazz)) {
        return value;
      }
    }

    return null;
  }

  public static RequestTypes getRandomRequestTypes() {
    Random rand = new Random();
    return fromNumber(Math.abs(rand.nextInt()) % (getLength() - 1));
  }

  public static RequestTypes fromNumber(int value) {
    return (RequestTypes) Arrays.asList(values()).get(value);
  }

  public static int getLength() {
    return values().length;
  }

  public String getPropertyName() {
    return this.propertyName;
  }

  public Class getClazz() {
    return this.clazz;
  }

  public <T,R> Function<T,Either<Throwables, R>> getFunction(){
    return this.function;
  }

}
