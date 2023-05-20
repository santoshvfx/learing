//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.05.20 at 02:19:21 AM EDT 
//


package com.att.lms.bis.service.ovals.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for WirelineAddressInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WirelineAddressInfo"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;choice&gt;
 *         &lt;element name="FieldedAddress" type="{http://csi.att.com/CSI/Namespaces/OVALSGISAddressValidationServiceQualUB/Types/Public/CommonDataModel.xsd}FieldedWirelineAddressInfo"/&gt;
 *         &lt;element name="UnfieldedAddress" type="{http://csi.att.com/CSI/Namespaces/OVALSGISAddressValidationServiceQualUB/Types/Public/CommonDataModel.xsd}UnfieldedWirelineAddressInfo"/&gt;
 *       &lt;/choice&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WirelineAddressInfo", propOrder = {
    "fieldedAddress",
    "unfieldedAddress"
})
public class WirelineAddressInfo {

    @XmlElement(name = "FieldedAddress")
    protected FieldedWirelineAddressInfo fieldedAddress;
    @XmlElement(name = "UnfieldedAddress")
    protected UnfieldedWirelineAddressInfo unfieldedAddress;

    /**
     * Gets the value of the fieldedAddress property.
     * 
     * @return
     *     possible object is
     *     {@link FieldedWirelineAddressInfo }
     *     
     */
    public FieldedWirelineAddressInfo getFieldedAddress() {
        return fieldedAddress;
    }

    /**
     * Sets the value of the fieldedAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link FieldedWirelineAddressInfo }
     *     
     */
    public void setFieldedAddress(FieldedWirelineAddressInfo value) {
        this.fieldedAddress = value;
    }

    /**
     * Gets the value of the unfieldedAddress property.
     * 
     * @return
     *     possible object is
     *     {@link UnfieldedWirelineAddressInfo }
     *     
     */
    public UnfieldedWirelineAddressInfo getUnfieldedAddress() {
        return unfieldedAddress;
    }

    /**
     * Sets the value of the unfieldedAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link UnfieldedWirelineAddressInfo }
     *     
     */
    public void setUnfieldedAddress(UnfieldedWirelineAddressInfo value) {
        this.unfieldedAddress = value;
    }

}
