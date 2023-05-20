package com.att.lms.bis.enumeration;

import java.util.Arrays;
import java.util.Random;

public enum ApropertiesTagLabelName {

  METHOD_NAME("METHOD_NAME"),
  APP_NAME("APP_NAME");

  private String propertyLabelName;

  private ApropertiesTagLabelName(String propertyLabelName) {
    this.propertyLabelName = propertyLabelName;
  }

  public static ApropertiesTagLabelName fromPropertyLabelName(String name) {
    ApropertiesTagLabelName[] var1 = values();
    int var2 = var1.length;

    for (int var3 = 0; var3 < var2; ++var3) {
      ApropertiesTagLabelName value = var1[var3];
      if (value.getPropertyLabelName().equals(name)) {
        return value;
      }
    }

    return null;
  }

  public static ApropertiesTagLabelName getRandomRequestTypes() {
    Random rand = new Random();
    return fromNumber(Math.abs(rand.nextInt()) % (getLength() - 1));
  }

  public static ApropertiesTagLabelName fromNumber(int value) {
    return (ApropertiesTagLabelName) Arrays.asList(values()).get(value);
  }

  public static int getLength() {
    return values().length;
  }

  public String getPropertyLabelName() {
    return this.propertyLabelName;
  }

}
