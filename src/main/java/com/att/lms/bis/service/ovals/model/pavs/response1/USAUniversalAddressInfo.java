//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.12.06 at 02:20:41 PM PST 
//


package com.att.lms.bis.service.ovals.model.pavs.response1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * USA Service validation
 * 
 * <p>Java class for USAUniversalAddressInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="USAUniversalAddressInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element name="Unfielded" type="{http://ovalgis.att.com/GIS/Namespaces/OVALSGIS/Types/Public/CommonDataModelPAV.xsd}USAUnfieldedAddressInfo" minOccurs="0"/>
 *         &lt;element name="Fielded" type="{http://ovalgis.att.com/GIS/Namespaces/OVALSGIS/Types/Public/CommonDataModelPAV.xsd}USAFieldedAddressInfo" minOccurs="0"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "USAUniversalAddressInfo", propOrder = {
    "unfielded",
    "fielded"
})
public class USAUniversalAddressInfo {

    @XmlElement(name = "Unfielded")
    protected USAUnfieldedAddressInfo unfielded;
    @XmlElement(name = "Fielded")
    protected USAFieldedAddressInfo fielded;

    /**
     * Gets the value of the unfielded property.
     * 
     * @return
     *     possible object is
     *     {@link USAUnfieldedAddressInfo }
     *     
     */
    public USAUnfieldedAddressInfo getUnfielded() {
        return unfielded;
    }

    /**
     * Sets the value of the unfielded property.
     * 
     * @param value
     *     allowed object is
     *     {@link USAUnfieldedAddressInfo }
     *     
     */
    public void setUnfielded(USAUnfieldedAddressInfo value) {
        this.unfielded = value;
    }

    /**
     * Gets the value of the fielded property.
     * 
     * @return
     *     possible object is
     *     {@link USAFieldedAddressInfo }
     *     
     */
    public USAFieldedAddressInfo getFielded() {
        return fielded;
    }

    /**
     * Sets the value of the fielded property.
     * 
     * @param value
     *     allowed object is
     *     {@link USAFieldedAddressInfo }
     *     
     */
    public void setFielded(USAFieldedAddressInfo value) {
        this.fielded = value;
    }

}