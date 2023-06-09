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
 * <p>Java class for TransportProductTelephoneNumberInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TransportProductTelephoneNumberInfo"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="telephoneNumber" type="{http://csi.att.com/CSI/Namespaces/OVALSGISAddressValidationServiceQualUB/Types/Public/CommonDataModel.xsd}TelephoneNumberInfo"/&gt;
 *         &lt;element name="telephoneNumberStatus" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;minLength value="0"/&gt;
 *               &lt;maxLength value="2"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="quickServiceIndicator" type="{http://csi.att.com/CSI/Namespaces/OVALSGISAddressValidationServiceQualUB/Types/Public/CommonDataModel.xsd}BooleanTrueInfo" minOccurs="0"/&gt;
 *         &lt;element name="qdtIndicator" type="{http://csi.att.com/CSI/Namespaces/OVALSGISAddressValidationServiceQualUB/Types/Public/CommonDataModel.xsd}BooleanTrueInfo" minOccurs="0"/&gt;
 *         &lt;element name="telephoneNumberActivity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="listedName" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;minLength value="0"/&gt;
 *               &lt;maxLength value="30"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="facilityAvailableIndicator" type="{http://csi.att.com/CSI/Namespaces/OVALSGISAddressValidationServiceQualUB/Types/Public/CommonDataModel.xsd}BooleanTrueInfo" minOccurs="0"/&gt;
 *         &lt;element name="disconnectReason" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;minLength value="0"/&gt;
 *               &lt;maxLength value="10"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TransportProductTelephoneNumberInfo", propOrder = {
    "telephoneNumber",
    "telephoneNumberStatus",
    "quickServiceIndicator",
    "qdtIndicator",
    "telephoneNumberActivity",
    "listedName",
    "facilityAvailableIndicator",
    "disconnectReason"
})
public class TransportProductTelephoneNumberInfo {

    @XmlElement(required = true)
    protected String telephoneNumber;
    protected String telephoneNumberStatus;
    protected Boolean quickServiceIndicator;
    protected Boolean qdtIndicator;
    protected String telephoneNumberActivity;
    protected String listedName;
    protected Boolean facilityAvailableIndicator;
    protected String disconnectReason;

    /**
     * Gets the value of the telephoneNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    /**
     * Sets the value of the telephoneNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTelephoneNumber(String value) {
        this.telephoneNumber = value;
    }

    /**
     * Gets the value of the telephoneNumberStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTelephoneNumberStatus() {
        return telephoneNumberStatus;
    }

    /**
     * Sets the value of the telephoneNumberStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTelephoneNumberStatus(String value) {
        this.telephoneNumberStatus = value;
    }

    /**
     * Gets the value of the quickServiceIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isQuickServiceIndicator() {
        return quickServiceIndicator;
    }

    /**
     * Sets the value of the quickServiceIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setQuickServiceIndicator(Boolean value) {
        this.quickServiceIndicator = value;
    }

    /**
     * Gets the value of the qdtIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isQdtIndicator() {
        return qdtIndicator;
    }

    /**
     * Sets the value of the qdtIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setQdtIndicator(Boolean value) {
        this.qdtIndicator = value;
    }

    /**
     * Gets the value of the telephoneNumberActivity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTelephoneNumberActivity() {
        return telephoneNumberActivity;
    }

    /**
     * Sets the value of the telephoneNumberActivity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTelephoneNumberActivity(String value) {
        this.telephoneNumberActivity = value;
    }

    /**
     * Gets the value of the listedName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getListedName() {
        return listedName;
    }

    /**
     * Sets the value of the listedName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setListedName(String value) {
        this.listedName = value;
    }

    /**
     * Gets the value of the facilityAvailableIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isFacilityAvailableIndicator() {
        return facilityAvailableIndicator;
    }

    /**
     * Sets the value of the facilityAvailableIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setFacilityAvailableIndicator(Boolean value) {
        this.facilityAvailableIndicator = value;
    }

    /**
     * Gets the value of the disconnectReason property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDisconnectReason() {
        return disconnectReason;
    }

    /**
     * Sets the value of the disconnectReason property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDisconnectReason(String value) {
        this.disconnectReason = value;
    }

}
