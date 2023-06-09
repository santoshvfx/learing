//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.12.06 at 02:01:52 PM PST 
//


package com.att.lms.bis.service.ovals.model.pavs.request1;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * Empty tag will not return
 * 
 * <p>Java class for USAUnfieldedAddressInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="USAUnfieldedAddressInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="addressLine" maxOccurs="5">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="256"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="city" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="50"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="state" type="{http://ovalgis.att.com/GIS/Namespaces/OVALSGIS/Types/Public/CommonDataModelPAV.xsd}StateCodeUSOnlyInfo" minOccurs="0"/>
 *         &lt;element name="postalCode" type="{http://ovalgis.att.com/GIS/Namespaces/OVALSGIS/Types/Public/CommonDataModelPAV.xsd}ZipCodeInfo" minOccurs="0"/>
 *         &lt;element name="postalCodePlus4" type="{http://ovalgis.att.com/GIS/Namespaces/OVALSGIS/Types/Public/CommonDataModelPAV.xsd}ZipCodeExtensionInfo" minOccurs="0"/>
 *         &lt;element name="county" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://ovalgis.att.com/GIS/Namespaces/OVALSGIS/Types/Public/CommonDataModelPAV.xsd}AddressCountyDescriptionInfo">
 *               &lt;maxLength value="20"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="country" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://ovalgis.att.com/GIS/Namespaces/OVALSGIS/Types/Public/CommonDataModelPAV.xsd}AddressCountryNameInfo">
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="structureType" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://ovalgis.att.com/GIS/Namespaces/OVALSGIS/Types/Public/CommonDataModelPAV.xsd}AddressUnitInfo">
 *               &lt;maxLength value="4"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="structureValue" type="{http://ovalgis.att.com/GIS/Namespaces/OVALSGIS/Types/Public/CommonDataModelPAV.xsd}AddressUnitInfo" minOccurs="0"/>
 *         &lt;element name="levelType" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://ovalgis.att.com/GIS/Namespaces/OVALSGIS/Types/Public/CommonDataModelPAV.xsd}AddressUnitInfo">
 *               &lt;maxLength value="4"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="levelValue" type="{http://ovalgis.att.com/GIS/Namespaces/OVALSGIS/Types/Public/CommonDataModelPAV.xsd}AddressUnitInfo" minOccurs="0"/>
 *         &lt;element name="unitType" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://ovalgis.att.com/GIS/Namespaces/OVALSGIS/Types/Public/CommonDataModelPAV.xsd}AddressUnitInfo">
 *               &lt;maxLength value="4"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="unitValue" type="{http://ovalgis.att.com/GIS/Namespaces/OVALSGIS/Types/Public/CommonDataModelPAV.xsd}AddressUnitInfo" minOccurs="0"/>
 *         &lt;element name="additionalName" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="60"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="crossStreet" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="128"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="serviceId" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="12"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="AdditionalData" type="{http://ovalgis.att.com/GIS/Namespaces/OVALSGIS/Types/Public/CommonDataModelPAV.xsd}PostalAddressExtralDataInfo" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "USAUnfieldedAddressInfo", propOrder = {
    "addressLine",
    "city",
    "state",
    "postalCode",
    "postalCodePlus4",
    "county",
    "country",
    "structureType",
    "structureValue",
    "levelType",
    "levelValue",
    "unitType",
    "unitValue",
    "additionalName",
    "crossStreet",
    "serviceId",
    "additionalData"
})
public class USAUnfieldedAddressInfo {

    @XmlElement(required = true)
    protected List<String> addressLine;
    protected String city;
    @XmlSchemaType(name = "string")
    protected StateCodeUSOnlyInfo state;
    protected String postalCode;
    protected String postalCodePlus4;
    protected String county;
    protected String country;
    protected String structureType;
    protected String structureValue;
    protected String levelType;
    protected String levelValue;
    protected String unitType;
    protected String unitValue;
    protected String additionalName;
    protected String crossStreet;
    protected String serviceId;
    @XmlElement(name = "AdditionalData")
    protected PostalAddressExtralDataInfo additionalData;

    /**
     * Gets the value of the addressLine property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the addressLine property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAddressLine().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getAddressLine() {
        if (addressLine == null) {
            addressLine = new ArrayList<String>();
        }
        return this.addressLine;
    }

    public void setAddressLine(List<String> addressLines) {
        if (addressLines != null) {
            this.addressLine = addressLines;
        }

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
     *     {@link StateCodeUSOnlyInfo }
     *     
     */
    public StateCodeUSOnlyInfo getState() {
        return state;
    }

    /**
     * Sets the value of the state property.
     * 
     * @param value
     *     allowed object is
     *     {@link StateCodeUSOnlyInfo }
     *     
     */
    public void setState(StateCodeUSOnlyInfo value) {
        this.state = value;
    }

    /**
     * Gets the value of the postalCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * Sets the value of the postalCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPostalCode(String value) {
        this.postalCode = value;
    }

    /**
     * Gets the value of the postalCodePlus4 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPostalCodePlus4() {
        return postalCodePlus4;
    }

    /**
     * Sets the value of the postalCodePlus4 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPostalCodePlus4(String value) {
        this.postalCodePlus4 = value;
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
     * Gets the value of the structureType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStructureType() {
        return structureType;
    }

    /**
     * Sets the value of the structureType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStructureType(String value) {
        this.structureType = value;
    }

    /**
     * Gets the value of the structureValue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStructureValue() {
        return structureValue;
    }

    /**
     * Sets the value of the structureValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStructureValue(String value) {
        this.structureValue = value;
    }

    /**
     * Gets the value of the levelType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLevelType() {
        return levelType;
    }

    /**
     * Sets the value of the levelType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLevelType(String value) {
        this.levelType = value;
    }

    /**
     * Gets the value of the levelValue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLevelValue() {
        return levelValue;
    }

    /**
     * Sets the value of the levelValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLevelValue(String value) {
        this.levelValue = value;
    }

    /**
     * Gets the value of the unitType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUnitType() {
        return unitType;
    }

    /**
     * Sets the value of the unitType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUnitType(String value) {
        this.unitType = value;
    }

    /**
     * Gets the value of the unitValue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUnitValue() {
        return unitValue;
    }

    /**
     * Sets the value of the unitValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUnitValue(String value) {
        this.unitValue = value;
    }

    /**
     * Gets the value of the additionalName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAdditionalName() {
        return additionalName;
    }

    /**
     * Sets the value of the additionalName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAdditionalName(String value) {
        this.additionalName = value;
    }

    /**
     * Gets the value of the crossStreet property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCrossStreet() {
        return crossStreet;
    }

    /**
     * Sets the value of the crossStreet property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCrossStreet(String value) {
        this.crossStreet = value;
    }

    /**
     * Gets the value of the serviceId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceId() {
        return serviceId;
    }

    /**
     * Sets the value of the serviceId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceId(String value) {
        this.serviceId = value;
    }

    /**
     * Gets the value of the additionalData property.
     * 
     * @return
     *     possible object is
     *     {@link PostalAddressExtralDataInfo }
     *     
     */
    public PostalAddressExtralDataInfo getAdditionalData() {
        return additionalData;
    }

    /**
     * Sets the value of the additionalData property.
     * 
     * @param value
     *     allowed object is
     *     {@link PostalAddressExtralDataInfo }
     *     
     */
    public void setAdditionalData(PostalAddressExtralDataInfo value) {
        this.additionalData = value;
    }

}
