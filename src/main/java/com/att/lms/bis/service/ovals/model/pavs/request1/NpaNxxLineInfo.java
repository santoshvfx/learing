//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.12.06 at 02:01:52 PM PST 
//


package com.att.lms.bis.service.ovals.model.pavs.request1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for NpaNxxLineInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="NpaNxxLineInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="npa" type="{http://ovalgis.att.com/GIS/Namespaces/OVALSGIS/Types/Public/CommonDataModelPAV.xsd}PhonePrefixInfo"/>
 *         &lt;element name="nxx" type="{http://ovalgis.att.com/GIS/Namespaces/OVALSGIS/Types/Public/CommonDataModelPAV.xsd}PhonePrefixInfo" minOccurs="0"/>
 *         &lt;element name="line" type="{http://ovalgis.att.com/GIS/Namespaces/OVALSGIS/Types/Public/CommonDataModelPAV.xsd}PhoneLastFourDigitsInfo" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NpaNxxLineInfo", propOrder = {
    "npa",
    "nxx",
    "line"
})
public class NpaNxxLineInfo {

    @XmlElement(required = true)
    protected String npa;
    protected String nxx;
    protected String line;

    /**
     * Gets the value of the npa property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNpa() {
        return npa;
    }

    /**
     * Sets the value of the npa property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNpa(String value) {
        this.npa = value;
    }

    /**
     * Gets the value of the nxx property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNxx() {
        return nxx;
    }

    /**
     * Sets the value of the nxx property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNxx(String value) {
        this.nxx = value;
    }

    /**
     * Gets the value of the line property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLine() {
        return line;
    }

    /**
     * Sets the value of the line property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLine(String value) {
        this.line = value;
    }

}