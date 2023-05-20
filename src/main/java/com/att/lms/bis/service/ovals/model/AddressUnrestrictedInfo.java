//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.05.20 at 02:19:21 AM EDT 
//


package com.att.lms.bis.service.ovals.model;

import javax.xml.bind.annotation.*;


/**
 * Manditory Fields
 * Field Name      Case when Mandatory 
 * city        Mandatory only when Address type = 'Street', 'Military', 'P.O. Box', or 'Rural Route'. 
 * stateCode       Mandatory only when Address type = 'Street', 'Military', 'P.O. Box', or 'Rural Route'. 
 * zip         Mandatory only when Address type = 'Street', 'Military', 'P.O. Box', or 'Rural Route'. 
 * pob         Mandatory only when Address type = 'P.O. Box'. 
 * streetName      Mandatory only when Address type = 'Street'.
 * 
 * <p>Java class for AddressUnrestrictedInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AddressUnrestrictedInfo"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="attention" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="addressLine1" type="{http://csi.att.com/CSI/Namespaces/OVALSGISAddressValidationServiceQualUB/Types/Public/CommonDataModel.xsd}AddressLineInfo" minOccurs="0"/&gt;
 *         &lt;element name="addressLine2" type="{http://csi.att.com/CSI/Namespaces/OVALSGISAddressValidationServiceQualUB/Types/Public/CommonDataModel.xsd}AddressLineInfo" minOccurs="0"/&gt;
 *         &lt;element name="Street" type="{http://csi.att.com/CSI/Namespaces/OVALSGISAddressValidationServiceQualUB/Types/Public/CommonDataModel.xsd}AddressStreetUnrestrictedInfo" minOccurs="0"/&gt;
 *         &lt;element name="Elevation" type="{http://csi.att.com/CSI/Namespaces/OVALSGISAddressValidationServiceQualUB/Types/Public/CommonDataModel.xsd}AddressAttributeInfo" minOccurs="0"/&gt;
 *         &lt;element name="Structure" type="{http://csi.att.com/CSI/Namespaces/OVALSGISAddressValidationServiceQualUB/Types/Public/CommonDataModel.xsd}AddressAttributeInfo" minOccurs="0"/&gt;
 *         &lt;element name="Unit" type="{http://csi.att.com/CSI/Namespaces/OVALSGISAddressValidationServiceQualUB/Types/Public/CommonDataModel.xsd}AddressAttributeInfo" minOccurs="0"/&gt;
 *         &lt;element name="postOfficeBox" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="RuralRoute" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="ruralRouteCenterNumber" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                   &lt;element name="ruralRouteBoxNumber" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="city" type="{http://csi.att.com/CSI/Namespaces/OVALSGISAddressValidationServiceQualUB/Types/Public/CommonDataModel.xsd}AddressCityInfo" minOccurs="0"/&gt;
 *         &lt;element name="state" type="{http://csi.att.com/CSI/Namespaces/OVALSGISAddressValidationServiceQualUB/Types/Public/CommonDataModel.xsd}AddressStateInfo" minOccurs="0"/&gt;
 *         &lt;element name="Zip" type="{http://csi.att.com/CSI/Namespaces/OVALSGISAddressValidationServiceQualUB/Types/Public/CommonDataModel.xsd}AddressZipInfo" minOccurs="0"/&gt;
 *         &lt;element name="country" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="county" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="countyCode" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="3"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="urbanizationCode" type="{http://csi.att.com/CSI/Namespaces/OVALSGISAddressValidationServiceQualUB/Types/Public/CommonDataModel.xsd}AddressUrbanizationInfo" minOccurs="0"/&gt;
 *         &lt;element name="countyFIPS" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;length value="5"/&gt;
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
@XmlType(name = "AddressUnrestrictedInfo", propOrder = {
    "attention",
    "addressLine1",
    "addressLine2",
    "street",
    "elevation",
    "structure",
    "unit",
    "postOfficeBox",
    "ruralRoute",
    "city",
    "state",
    "zip",
    "country",
    "county",
    "countyCode",
    "urbanizationCode",
    "countyFIPS"
})
@XmlSeeAlso({
    FiberServiceAddressInfo.FieldedAddress.class,
    FiberServiceExtendedAddressUnrestrictedInfo.class
})
public class AddressUnrestrictedInfo {

    protected String attention;
    protected String addressLine1;
    protected String addressLine2;
    @XmlElement(name = "Street")
    protected AddressStreetUnrestrictedInfo street;
    @XmlElement(name = "Elevation")
    protected AddressAttributeInfo elevation;
    @XmlElement(name = "Structure")
    protected AddressAttributeInfo structure;
    @XmlElement(name = "Unit")
    protected AddressAttributeInfo unit;
    protected String postOfficeBox;
    @XmlElement(name = "RuralRoute")
    protected RuralRoute ruralRoute;
    protected String city;
    @XmlSchemaType(name = "string")
    protected AddressStateInfo state;
    @XmlElement(name = "Zip")
    protected AddressZipInfo zip;
    protected String country;
    protected String county;
    protected String countyCode;
    protected String urbanizationCode;
    protected String countyFIPS;

    /**
     * Gets the value of the attention property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAttention() {
        return attention;
    }

    /**
     * Sets the value of the attention property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAttention(String value) {
        this.attention = value;
    }

    /**
     * Gets the value of the addressLine1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddressLine1() {
        return addressLine1;
    }

    /**
     * Sets the value of the addressLine1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddressLine1(String value) {
        this.addressLine1 = value;
    }

    /**
     * Gets the value of the addressLine2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddressLine2() {
        return addressLine2;
    }

    /**
     * Sets the value of the addressLine2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddressLine2(String value) {
        this.addressLine2 = value;
    }

    /**
     * Gets the value of the street property.
     * 
     * @return
     *     possible object is
     *     {@link AddressStreetUnrestrictedInfo }
     *     
     */
    public AddressStreetUnrestrictedInfo getStreet() {
        return street;
    }

    /**
     * Sets the value of the street property.
     * 
     * @param value
     *     allowed object is
     *     {@link AddressStreetUnrestrictedInfo }
     *     
     */
    public void setStreet(AddressStreetUnrestrictedInfo value) {
        this.street = value;
    }

    /**
     * Gets the value of the elevation property.
     * 
     * @return
     *     possible object is
     *     {@link AddressAttributeInfo }
     *     
     */
    public AddressAttributeInfo getElevation() {
        return elevation;
    }

    /**
     * Sets the value of the elevation property.
     * 
     * @param value
     *     allowed object is
     *     {@link AddressAttributeInfo }
     *     
     */
    public void setElevation(AddressAttributeInfo value) {
        this.elevation = value;
    }

    /**
     * Gets the value of the structure property.
     * 
     * @return
     *     possible object is
     *     {@link AddressAttributeInfo }
     *     
     */
    public AddressAttributeInfo getStructure() {
        return structure;
    }

    /**
     * Sets the value of the structure property.
     * 
     * @param value
     *     allowed object is
     *     {@link AddressAttributeInfo }
     *     
     */
    public void setStructure(AddressAttributeInfo value) {
        this.structure = value;
    }

    /**
     * Gets the value of the unit property.
     * 
     * @return
     *     possible object is
     *     {@link AddressAttributeInfo }
     *     
     */
    public AddressAttributeInfo getUnit() {
        return unit;
    }

    /**
     * Sets the value of the unit property.
     * 
     * @param value
     *     allowed object is
     *     {@link AddressAttributeInfo }
     *     
     */
    public void setUnit(AddressAttributeInfo value) {
        this.unit = value;
    }

    /**
     * Gets the value of the postOfficeBox property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPostOfficeBox() {
        return postOfficeBox;
    }

    /**
     * Sets the value of the postOfficeBox property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPostOfficeBox(String value) {
        this.postOfficeBox = value;
    }

    /**
     * Gets the value of the ruralRoute property.
     * 
     * @return
     *     possible object is
     *     {@link RuralRoute }
     *     
     */
    public RuralRoute getRuralRoute() {
        return ruralRoute;
    }

    /**
     * Sets the value of the ruralRoute property.
     * 
     * @param value
     *     allowed object is
     *     {@link RuralRoute }
     *     
     */
    public void setRuralRoute(RuralRoute value) {
        this.ruralRoute = value;
    }

    /**
     * Gets the value of the city property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets the value of the city property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCity(String value) {
        this.city = value;
    }

    /**
     * Gets the value of the state property.
     * 
     * @return
     *     possible object is
     *     {@link AddressStateInfo }
     *     
     */
    public AddressStateInfo getState() {
        return state;
    }

    /**
     * Sets the value of the state property.
     * 
     * @param value
     *     allowed object is
     *     {@link AddressStateInfo }
     *     
     */
    public void setState(AddressStateInfo value) {
        this.state = value;
    }

    /**
     * Gets the value of the zip property.
     * 
     * @return
     *     possible object is
     *     {@link AddressZipInfo }
     *     
     */
    public AddressZipInfo getZip() {
        return zip;
    }

    /**
     * Sets the value of the zip property.
     * 
     * @param value
     *     allowed object is
     *     {@link AddressZipInfo }
     *     
     */
    public void setZip(AddressZipInfo value) {
        this.zip = value;
    }

    /**
     * Gets the value of the country property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets the value of the country property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCountry(String value) {
        this.country = value;
    }

    /**
     * Gets the value of the county property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCounty() {
        return county;
    }

    /**
     * Sets the value of the county property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCounty(String value) {
        this.county = value;
    }

    /**
     * Gets the value of the countyCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCountyCode() {
        return countyCode;
    }

    /**
     * Sets the value of the countyCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCountyCode(String value) {
        this.countyCode = value;
    }

    /**
     * Gets the value of the urbanizationCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUrbanizationCode() {
        return urbanizationCode;
    }

    /**
     * Sets the value of the urbanizationCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUrbanizationCode(String value) {
        this.urbanizationCode = value;
    }

    /**
     * Gets the value of the countyFIPS property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCountyFIPS() {
        return countyFIPS;
    }

    /**
     * Sets the value of the countyFIPS property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCountyFIPS(String value) {
        this.countyFIPS = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="ruralRouteCenterNumber" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *         &lt;element name="ruralRouteBoxNumber" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "ruralRouteCenterNumber",
        "ruralRouteBoxNumber"
    })
    public static class RuralRoute {

        @XmlElement(required = true)
        protected String ruralRouteCenterNumber;
        @XmlElement(required = true)
        protected String ruralRouteBoxNumber;

        /**
         * Gets the value of the ruralRouteCenterNumber property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getRuralRouteCenterNumber() {
            return ruralRouteCenterNumber;
        }

        /**
         * Sets the value of the ruralRouteCenterNumber property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setRuralRouteCenterNumber(String value) {
            this.ruralRouteCenterNumber = value;
        }

        /**
         * Gets the value of the ruralRouteBoxNumber property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getRuralRouteBoxNumber() {
            return ruralRouteBoxNumber;
        }

        /**
         * Sets the value of the ruralRouteBoxNumber property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setRuralRouteBoxNumber(String value) {
            this.ruralRouteBoxNumber = value;
        }

    }

}
