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
 * <p>Java class for SBSTariffGeographyCodeInfo.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="SBSTariffGeographyCodeInfo"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="OOF"/&gt;
 *     &lt;enumeration value="OOR"/&gt;
 *     &lt;enumeration value="IR"/&gt;
 *     &lt;enumeration value="UNK"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "SBSTariffGeographyCodeInfo")
@XmlEnum
public enum SBSTariffGeographyCodeInfo {

    OOF,
    OOR,
    IR,
    UNK;

    public String value() {
        return name();
    }

    public static SBSTariffGeographyCodeInfo fromValue(String v) {
        return valueOf(v);
    }

}