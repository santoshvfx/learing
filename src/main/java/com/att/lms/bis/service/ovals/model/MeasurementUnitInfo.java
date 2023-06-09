//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.05.20 at 02:19:21 AM EDT 
//


package com.att.lms.bis.service.ovals.model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for MeasurementUnitInfo.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="MeasurementUnitInfo"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}NCName"&gt;
 *     &lt;enumeration value="MEGABITS_PER_SECOND"/&gt;
 *     &lt;enumeration value="FEET"/&gt;
 *     &lt;enumeration value="MILES"/&gt;
 *     &lt;enumeration value="QUARTER_MILES"/&gt;
 *     &lt;enumeration value="KILO_FEET"/&gt;
 *     &lt;enumeration value="KILO_METER"/&gt;
 *     &lt;enumeration value="HUNDRED_OHMS"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "MeasurementUnitInfo")
@XmlEnum
public enum MeasurementUnitInfo {

    MEGABITS_PER_SECOND,
    FEET,
    MILES,
    QUARTER_MILES,
    KILO_FEET,
    KILO_METER,
    HUNDRED_OHMS;

    public String value() {
        return name();
    }

    public static MeasurementUnitInfo fromValue(String v) {
        return valueOf(v);
    }

}
