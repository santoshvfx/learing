//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.12.06 at 02:01:52 PM PST 
//


package com.att.lms.bis.service.ovals.model.pavs.request1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * Empty tag will not return
 * 
 * <p>Java class for PostalLocationAttributesAdditionalDataInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PostalLocationAttributesAdditionalDataInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="deliveryPointValidationFootnote" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="12"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="taxationGeocode" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="13"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="incorporateType" type="{http://ovalgis.att.com/GIS/Namespaces/OVALSGIS/Types/Public/CommonDataModelPAV.xsd}BooleanWithUndefinedInfo" minOccurs="0"/>
 *         &lt;element name="foreignAddressIndicator" type="{http://ovalgis.att.com/GIS/Namespaces/OVALSGIS/Types/Public/CommonDataModelPAV.xsd}BooleanTrueInfo" minOccurs="0"/>
 *         &lt;element name="suiteLinkReturnCode" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="2"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="earlyWarningIndicator" type="{http://ovalgis.att.com/GIS/Namespaces/OVALSGIS/Types/Public/CommonDataModelPAV.xsd}BooleanTrueInfo" minOccurs="0"/>
 *         &lt;element name="poboxOnlyIndicator" type="{http://ovalgis.att.com/GIS/Namespaces/OVALSGIS/Types/Public/CommonDataModelPAV.xsd}BooleanTrueInfo" minOccurs="0"/>
 *         &lt;element name="twoCharacterISOCountryCode" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;length value="2"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="threeCharacterISOCountryCode" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;length value="3"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="changeOfAddressIndicator" type="{http://ovalgis.att.com/GIS/Namespaces/OVALSGIS/Types/Public/CommonDataModelPAV.xsd}BooleanTrueInfo" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PostalLocationAttributesAdditionalDataInfo", propOrder = {
    "deliveryPointValidationFootnote",
    "taxationGeocode",
    "incorporateType",
    "foreignAddressIndicator",
    "suiteLinkReturnCode",
    "earlyWarningIndicator",
    "poboxOnlyIndicator",
    "twoCharacterISOCountryCode",
    "threeCharacterISOCountryCode",
    "changeOfAddressIndicator"
})
public class PostalLocationAttributesAdditionalDataInfo {

    protected String deliveryPointValidationFootnote;
    protected String taxationGeocode;
    @XmlSchemaType(name = "string")
    protected BooleanWithUndefinedInfo incorporateType;
    protected Boolean foreignAddressIndicator;
    protected String suiteLinkReturnCode;
    protected Boolean earlyWarningIndicator;
    protected Boolean poboxOnlyIndicator;
    protected String twoCharacterISOCountryCode;
    protected String threeCharacterISOCountryCode;
    protected Boolean changeOfAddressIndicator;

    /**
     * Gets the value of the deliveryPointValidationFootnote property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeliveryPointValidationFootnote() {
        return deliveryPointValidationFootnote;
    }

    /**
     * Sets the value of the deliveryPointValidationFootnote property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeliveryPointValidationFootnote(String value) {
        this.deliveryPointValidationFootnote = value;
    }

    /**
     * Gets the value of the taxationGeocode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTaxationGeocode() {
        return taxationGeocode;
    }

    /**
     * Sets the value of the taxationGeocode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTaxationGeocode(String value) {
        this.taxationGeocode = value;
    }

    /**
     * Gets the value of the incorporateType property.
     * 
     * @return
     *     possible object is
     *     {@link BooleanWithUndefinedInfo }
     *     
     */
    public BooleanWithUndefinedInfo getIncorporateType() {
        return incorporateType;
    }

    /**
     * Sets the value of the incorporateType property.
     * 
     * @param value
     *     allowed object is
     *     {@link BooleanWithUndefinedInfo }
     *     
     */
    public void setIncorporateType(BooleanWithUndefinedInfo value) {
        this.incorporateType = value;
    }

    /**
     * Gets the value of the foreignAddressIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isForeignAddressIndicator() {
        return foreignAddressIndicator;
    }

    /**
     * Sets the value of the foreignAddressIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setForeignAddressIndicator(Boolean value) {
        this.foreignAddressIndicator = value;
    }

    /**
     * Gets the value of the suiteLinkReturnCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSuiteLinkReturnCode() {
        return suiteLinkReturnCode;
    }

    /**
     * Sets the value of the suiteLinkReturnCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSuiteLinkReturnCode(String value) {
        this.suiteLinkReturnCode = value;
    }

    /**
     * Gets the value of the earlyWarningIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isEarlyWarningIndicator() {
        return earlyWarningIndicator;
    }

    /**
     * Sets the value of the earlyWarningIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setEarlyWarningIndicator(Boolean value) {
        this.earlyWarningIndicator = value;
    }

    /**
     * Gets the value of the poboxOnlyIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isPoboxOnlyIndicator() {
        return poboxOnlyIndicator;
    }

    /**
     * Sets the value of the poboxOnlyIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setPoboxOnlyIndicator(Boolean value) {
        this.poboxOnlyIndicator = value;
    }

    /**
     * Gets the value of the twoCharacterISOCountryCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTwoCharacterISOCountryCode() {
        return twoCharacterISOCountryCode;
    }

    /**
     * Sets the value of the twoCharacterISOCountryCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTwoCharacterISOCountryCode(String value) {
        this.twoCharacterISOCountryCode = value;
    }

    /**
     * Gets the value of the threeCharacterISOCountryCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getThreeCharacterISOCountryCode() {
        return threeCharacterISOCountryCode;
    }

    /**
     * Sets the value of the threeCharacterISOCountryCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setThreeCharacterISOCountryCode(String value) {
        this.threeCharacterISOCountryCode = value;
    }

    /**
     * Gets the value of the changeOfAddressIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isChangeOfAddressIndicator() {
        return changeOfAddressIndicator;
    }

    /**
     * Sets the value of the changeOfAddressIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setChangeOfAddressIndicator(Boolean value) {
        this.changeOfAddressIndicator = value;
    }

}
