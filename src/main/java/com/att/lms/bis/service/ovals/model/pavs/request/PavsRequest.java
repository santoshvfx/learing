package com.att.lms.bis.service.ovals.model.pavs.request;

import javax.xml.bind.annotation.*;

/**
 *
 *
 *
 * <?xml version="1.0" encoding="UTF-8" standalone="yes"?>
 * <tns:postalAddressValidationServiceRequest
 * 	xmlns:cdm="http://ovalgis.att.com/GIS/Namespaces/OVALSGIS/Types/Public/CommonDataModelPAV.xsd"
 * 	xmlns:tns="http://ovalgis.att.com/PostalAddressValidationRequest.xsd"
 * 	xmlns:rns="http://ovalgis.att.com/PostalAddressValidationResponse.xsd">
 * 	<tns:maximumCASSAddressLineLength>40</tns:maximumCASSAddressLineLength>
 * 	<tns:maximumAlternativeAddresses>3</tns:maximumAlternativeAddresses>
 * 	<tns:Address>
 * 		<cdm:Fielded>
 * 			<cdm:houseNumber>1730</cdm:houseNumber>
 * 			<cdm:streetName>GRANDIN</cdm:streetName>
 * 			<cdm:streetThoroughfare>RD</cdm:streetThoroughfare>
 * 			<cdm:streetNameSuffix>SW</cdm:streetNameSuffix>
 *             <cdm:city>ROANOKE</cdm:city>
 * 			<cdm:state>VA</cdm:state>
 * 			<cdm:postalCode>24015</cdm:postalCode>
 *  <!--           cdm:structureType>BLDG</cdm:structureType>-->
 *  <!--			<cdm:structureValue>A</cdm:structureValue>-->
 *  <!--			<cdm:levelType>FLR</cdm:levelType>-->
 *  <!--			<cdm:levelValue>11</cdm:levelValue>-->
 *  			<cdm:unitType>APT</cdm:unitType>
 * 			<cdm:unitValue>45</cdm:unitValue>
 * 			<cdm:AdditionalData>
 * 				<cdm:customerName>JOHN SMITH</cdm:customerName>
 * 				<cdm:businessName>AMY SMITH</cdm:businessName>
 * 				<cdm:attention>C/O AMY SMITH</cdm:attention>
 * 				<cdm:urbanizationCode></cdm:urbanizationCode>
 * 			</cdm:AdditionalData>
 * 		</cdm:Fielded>
 * 	</tns:Address>
 * </tns:postalAddressValidationServiceRequest>
 *
 *
 * <?xml version="1.0" encoding="UTF-8" standalone="yes"?>
 *  <tns:postalAddressValidationServiceRequest
 *  	xmlns:cdm="http://ovalgis.att.com/GIS/Namespaces/OVALSGIS/Types/Public/CommonDataModelPAV.xsd"
 *  	xmlns:tns="http://ovalgis.att.com/PostalAddressValidationRequest.xsd"
 *  	xmlns:rns="http://ovalgis.att.com/PostalAddressValidationResponse.xsd">
 *  	<tns:maximumCASSAddressLineLength>40</tns:maximumCASSAddressLineLength>
 *  	<tns:maximumAlternativeAddresses>3</tns:maximumAlternativeAddresses>
 *  	<tns:Address>
 *             <cdm:Unfielded>
 *             <cdm:addressLine>8546 BROADWAY ST</cdm:addressLine>
 *             <cdm:structureType></cdm:structureType>
 *             <cdm:structureValue></cdm:structureValue>
 *             <cdm:levelType></cdm:levelType>
 *             <cdm:levelValue></cdm:levelValue>
 *  			<cdm:unitType>APT</cdm:unitType>
 *     	     <cdm:unitValue>201</cdm:unitValue>
 *  			<cdm:city>SAN ANTONIO</cdm:city>
 *  			<cdm:state>TX</cdm:state>
 *  			<cdm:postalCode>78217</cdm:postalCode>
 *             <cdm:AdditionalData>
 *                 <cdm:customerName>JOHN SMITH</cdm:customerName>
 *                 <cdm:businessName></cdm:businessName>
 *                 <cdm:attention>AMY SMITH</cdm:attention>
 *                 <cdm:urbanizationCode></cdm:urbanizationCode>
 *             </cdm:AdditionalData>
 *  		</cdm:Unfielded>
 *  	</tns:Address>
 *
 *  </tns:postalAddressValidationServiceRequest>
 *
 *
 *
 *
 *
 *
 *  <?xml version="1.0" encoding="UTF-8"?>
 * <!-- =========================================================================
 * 	AT&T Proprietary (Internal Use Only) Not for use or disclosure outside the
 * 	AT&T companies except under written agreement (c) 2007 AT&T Intellectual
 * 	Property. All rights reserved. AT&T and the AT&T logo are trademarks of AT&T
 * 	Intellectual Property. ======================================================================= -->
 *
 * <xs:schema xmlns:xs="http://www.w3.org/2001/XMLSchema"
 * 	targetNamespace="http://ovalgis.att.com/PostalAddressValidationRequest.xsd"
 * 	xmlns:tns="http://ovalgis.att.com/PostalAddressValidationRequest.xsd"
 * 	xmlns:cdm="http://ovalgis.att.com/GIS/Namespaces/OVALSGIS/Types/Public/CommonDataModelPAV.xsd"
 * 	elementFormDefault="qualified" xmlns:pref="http://ovalgis.att.com/GIS/Namespaces/OVALSGIS/Types/Public/CommonDataModelPAV.xsd">
 *
 * 	<xs:import schemaLocation="CommonDataModelPAV.xsd" namespace="http://ovalgis.att.com/GIS/Namespaces/OVALSGIS/Types/Public/CommonDataModelPAV.xsd"></xs:import>
 * 	<xs:element name="postalAddressValidationServiceRequest"
 * 		type="tns:postalAddressValidationServiceRequestType">
 * 	</xs:element>
 *
 *
 * 	<xs:complexType name="postalAddressValidationServiceRequestType">
 * 		<xs:sequence>
 *
 * 			<xs:annotation>
 * 				<xs:documentation>
 * 					For reference, the OVALS GIS counterpart elements
 * 					are located in the GIS-OVALS PAVS schema in
 * 					PostalAddressValidationServiceRequest:Address
 * 				</xs:documentation>
 * 			</xs:annotation>
 * 			<xs:element name="conversationId" type="xs:string" maxOccurs="1" minOccurs="0"></xs:element>
 * 			<xs:element name="maximumCASSAddressLineLength"
 * 				maxOccurs="1" minOccurs="1" type="xs:int">
 * 				<xs:annotation>
 * 					<xs:documentation>
 * 						Response limit restricting maximum length of
 * 						Coding Accuracy Support System (CASS) Address
 * 						lines. Enter 28 or 30 or 40
 * 					</xs:documentation>
 * 				</xs:annotation>
 * 			</xs:element>
 * 			<xs:element name="maximumAlternativeAddresses" maxOccurs="1"
 * 				minOccurs="0" type="xs:int">
 * 				<xs:annotation>
 * 					<xs:documentation>
 * 						Response limit restricting maximum number of
 * 						discrete postal addresses returned if
 * 						alternatives list returned. If not provided
 * 						default will be maximum of 10.
 * 					</xs:documentation>
 * 				</xs:annotation>
 * 			</xs:element>
 *
 * 			<xs:element name="Address"
 * 				type="cdm:USAUniversalAddressInfo" minOccurs="0">
 * 				<xs:annotation>
 * 					<xs:documentation>USA addresses</xs:documentation>
 * 				</xs:annotation>
 * 			</xs:element>
 *
 * 		</xs:sequence>
 * 	</xs:complexType>
 *
 * </xs:schema>
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "postalAddressValidationServiceRequest", propOrder = {
        "address",
        "conversationId",
        "maximumCASSAddressLineLength",
        "maximumAlternativeAddresses"
})
@XmlRootElement(name = "postalAddressValidationServiceRequest", namespace = "http://ovalgis.att.com/PostalAddressValidationRequest.xsd")
public class PavsRequest {

    @XmlElement(name = "Address", namespace = "http://ovalgis.att.com/PostalAddressValidationRequest.xsd")
    protected Address address;
    @XmlElement(name = "conversationId", namespace = "http://ovalgis.att.com/PostalAddressValidationRequest.xsd")
    protected String conversationId;
    @XmlElement(namespace = "http://ovalgis.att.com/PostalAddressValidationRequest.xsd")
    protected Integer maximumCASSAddressLineLength;
    @XmlElement(namespace = "http://ovalgis.att.com/PostalAddressValidationRequest.xsd")
    protected Integer maximumAlternativeAddresses;

    public Address getAddress() { return address; }

    public void setAddress(Address value) { this.address = value; }

    public Integer getMaximumCASSAddressLineLength() {
        return maximumCASSAddressLineLength;
    }

    public void setMaximumCASSAddressLineLength(Integer maximumCASSAddressLineLength) {
        this.maximumCASSAddressLineLength = maximumCASSAddressLineLength;
    }

    public Integer getMaximumAlternativeAddresses() {
        return maximumAlternativeAddresses;
    }

    public void setMaximumAlternativeAddresses(Integer maximumAlternativeAddresses) {
        this.maximumAlternativeAddresses = maximumAlternativeAddresses;
    }

    public String getConversationId() {
        return conversationId;
    }

    public void setConversationId(String conversationId) {
        this.conversationId = conversationId;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "fielded",
            "unfielded"
    })
    public static class Address {
        @XmlElement(name = "Fielded", namespace = "http://ovalgis.att.com/GIS/Namespaces/OVALSGIS/Types/Public/CommonDataModelPAV.xsd")
        protected Fielded fielded;
        @XmlElement(name = "Unfielded", namespace = "http://ovalgis.att.com/GIS/Namespaces/OVALSGIS/Types/Public/CommonDataModelPAV.xsd")
        protected Unfielded unfielded;

        public Fielded getFielded() { return fielded; }

        public void setFielded(Fielded value) { this.fielded = value; }

        public Unfielded getUnfielded() { return unfielded; }

        public void setUnfielded(Unfielded value) { this.unfielded = value; }

        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", namespace = "http://ovalgis.att.com/GIS/Namespaces/OVALSGIS/Types/Public/CommonDataModelPAV.xsd", propOrder = {
                "route",
                "box",
                "houseNumberPrefix",
                "assignedHouseNumber",
                "houseNumberSuffix",
                "streetDirection",
                "primaryStreetUnnamedIndicator",
                "postalCodePlus4",
                "county",
                "country",
                "additionalName",
                "serviceId",
                "singleLineStandardizedAddress",
                "postalBox",
                "cityAbbreviatedName",
                "primaryAddressLine",
                "secondaryAddressLine",
                "houseNumber",
                "streetName",
                "streetThoroughfare",
                "streetNameSuffix",
                "city",
                "state",
                "postalCode",
                "structureType",
                "structureValue",
                "levelType",
                "levelValue",
                "unitType",
                "unitValue",
                "additionalData"
        })
        public static class Fielded {
            @XmlElement(name = "primaryAddressLine", namespace = "http://ovalgis.att.com/GIS/Namespaces/OVALSGIS/Types/Public/CommonDataModelPAV.xsd")
            protected String primaryAddressLine;
            @XmlElement(name = "secondaryAddressLine", namespace = "http://ovalgis.att.com/GIS/Namespaces/OVALSGIS/Types/Public/CommonDataModelPAV.xsd")
            protected String secondaryAddressLine;
            @XmlElement(name = "houseNumber", namespace = "http://ovalgis.att.com/GIS/Namespaces/OVALSGIS/Types/Public/CommonDataModelPAV.xsd")
            protected String houseNumber;
            @XmlElement(name = "streetName", namespace = "http://ovalgis.att.com/GIS/Namespaces/OVALSGIS/Types/Public/CommonDataModelPAV.xsd")
            protected String streetName;
            @XmlElement(name = "streetThoroughfare", namespace = "http://ovalgis.att.com/GIS/Namespaces/OVALSGIS/Types/Public/CommonDataModelPAV.xsd")
            protected String streetThoroughfare;
            @XmlElement(name = "streetNameSuffix", namespace = "http://ovalgis.att.com/GIS/Namespaces/OVALSGIS/Types/Public/CommonDataModelPAV.xsd")
            protected String streetNameSuffix;
            @XmlElement(name = "city", namespace = "http://ovalgis.att.com/GIS/Namespaces/OVALSGIS/Types/Public/CommonDataModelPAV.xsd")
            protected String city;
            @XmlElement(name = "state", namespace = "http://ovalgis.att.com/GIS/Namespaces/OVALSGIS/Types/Public/CommonDataModelPAV.xsd")
            protected String state;
            @XmlElement(name = "postalCode", namespace = "http://ovalgis.att.com/GIS/Namespaces/OVALSGIS/Types/Public/CommonDataModelPAV.xsd")
            protected String postalCode;
            @XmlElement(name = "structureType", namespace = "http://ovalgis.att.com/GIS/Namespaces/OVALSGIS/Types/Public/CommonDataModelPAV.xsd")
            protected String structureType;
            @XmlElement(name = "structureValue", namespace = "http://ovalgis.att.com/GIS/Namespaces/OVALSGIS/Types/Public/CommonDataModelPAV.xsd")
            protected String structureValue;
            @XmlElement(name = "levelType", namespace = "http://ovalgis.att.com/GIS/Namespaces/OVALSGIS/Types/Public/CommonDataModelPAV.xsd")
            protected String levelType;
            @XmlElement(name = "levelValue", namespace = "http://ovalgis.att.com/GIS/Namespaces/OVALSGIS/Types/Public/CommonDataModelPAV.xsd")
            protected String levelValue;
            @XmlElement(name = "unitType", namespace = "http://ovalgis.att.com/GIS/Namespaces/OVALSGIS/Types/Public/CommonDataModelPAV.xsd")
            protected String unitType;
            @XmlElement(name = "unitValue", namespace = "http://ovalgis.att.com/GIS/Namespaces/OVALSGIS/Types/Public/CommonDataModelPAV.xsd")
            protected String unitValue;
            @XmlElement(name = "AdditionalData", namespace = "http://ovalgis.att.com/GIS/Namespaces/OVALSGIS/Types/Public/CommonDataModelPAV.xsd")
            protected AdditionalData additionalData;
            @XmlElement(name = "route", namespace = "http://ovalgis.att.com/GIS/Namespaces/OVALSGIS/Types/Public/CommonDataModelPAV.xsd")
            protected String route;
            @XmlElement(name = "box", namespace = "http://ovalgis.att.com/GIS/Namespaces/OVALSGIS/Types/Public/CommonDataModelPAV.xsd")
            protected String box;
            @XmlElement(name = "houseNumberPrefix", namespace = "http://ovalgis.att.com/GIS/Namespaces/OVALSGIS/Types/Public/CommonDataModelPAV.xsd")
            protected String houseNumberPrefix;
            @XmlElement(name = "assignedHouseNumber", namespace = "http://ovalgis.att.com/GIS/Namespaces/OVALSGIS/Types/Public/CommonDataModelPAV.xsd")
            protected String assignedHouseNumber;
            @XmlElement(name = "houseNumberSuffix", namespace = "http://ovalgis.att.com/GIS/Namespaces/OVALSGIS/Types/Public/CommonDataModelPAV.xsd")
            protected String houseNumberSuffix;
            @XmlElement(name = "streetDirection", namespace = "http://ovalgis.att.com/GIS/Namespaces/OVALSGIS/Types/Public/CommonDataModelPAV.xsd")
            protected String streetDirection;
            @XmlElement(name = "primaryStreetUnnamedIndicator", namespace = "http://ovalgis.att.com/GIS/Namespaces/OVALSGIS/Types/Public/CommonDataModelPAV.xsd")
            protected Boolean primaryStreetUnnamedIndicator;
            @XmlElement(name = "postalCodePlus4", namespace = "http://ovalgis.att.com/GIS/Namespaces/OVALSGIS/Types/Public/CommonDataModelPAV.xsd")
            protected String postalCodePlus4;
            @XmlElement(name = "county", namespace = "http://ovalgis.att.com/GIS/Namespaces/OVALSGIS/Types/Public/CommonDataModelPAV.xsd")
            protected String county;
            @XmlElement(name = "country", namespace = "http://ovalgis.att.com/GIS/Namespaces/OVALSGIS/Types/Public/CommonDataModelPAV.xsd")
            protected String country;
            @XmlElement(name = "additionalName", namespace = "http://ovalgis.att.com/GIS/Namespaces/OVALSGIS/Types/Public/CommonDataModelPAV.xsd")
            protected String additionalName;
            @XmlElement(name = "serviceId", namespace = "http://ovalgis.att.com/GIS/Namespaces/OVALSGIS/Types/Public/CommonDataModelPAV.xsd")
            protected String serviceId;
            @XmlElement(name = "singleLineStandardizedAddress", namespace = "http://ovalgis.att.com/GIS/Namespaces/OVALSGIS/Types/Public/CommonDataModelPAV.xsd")
            protected String singleLineStandardizedAddress;
            @XmlElement(name = "postalBox", namespace = "http://ovalgis.att.com/GIS/Namespaces/OVALSGIS/Types/Public/CommonDataModelPAV.xsd")
            protected String postalBox;
            @XmlElement(name = "cityAbbreviatedName", namespace = "http://ovalgis.att.com/GIS/Namespaces/OVALSGIS/Types/Public/CommonDataModelPAV.xsd")
            protected String cityAbbreviatedName;


            public String getPrimaryAddressLine() {
                return primaryAddressLine;
            }

            public void setPrimaryAddressLine(String primaryAddressLine) {
                this.primaryAddressLine = primaryAddressLine;
            }

            public String getSecondaryAddressLine() {
                return secondaryAddressLine;
            }

            public void setSecondaryAddressLine(String secondaryAddressLine) {
                this.secondaryAddressLine = secondaryAddressLine;
            }

            public String getHouseNumber() {
                return houseNumber;
            }

            public void setHouseNumber(String houseNumber) {
                this.houseNumber = houseNumber;
            }

            public String getStreetName() {
                return streetName;
            }

            public void setStreetName(String streetName) {
                this.streetName = streetName;
            }

            public String getStreetThoroughfare() {
                return streetThoroughfare;
            }

            public void setStreetThoroughfare(String streetThoroughfare) {
                this.streetThoroughfare = streetThoroughfare;
            }

            public String getStreetNameSuffix() {
                return streetNameSuffix;
            }

            public void setStreetNameSuffix(String streetNameSuffix) {
                this.streetNameSuffix = streetNameSuffix;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getState() {
                return state;
            }

            public void setState(String state) {
                this.state = state;
            }

            public String getPostalCode() {
                return postalCode;
            }

            public void setPostalCode(String postalCode) {
                this.postalCode = postalCode;
            }

            public String getStructureType() {
                return structureType;
            }

            public void setStructureType(String structureType) {
                this.structureType = structureType;
            }

            public String getStructureValue() {
                return structureValue;
            }

            public void setStructureValue(String structureValue) {
                this.structureValue = structureValue;
            }

            public String getLevelType() {
                return levelType;
            }

            public void setLevelType(String levelType) {
                this.levelType = levelType;
            }

            public String getLevelValue() {
                return levelValue;
            }

            public void setLevelValue(String levelValue) {
                this.levelValue = levelValue;
            }

            public String getUnitType() {
                return unitType;
            }

            public void setUnitType(String unitType) {
                this.unitType = unitType;
            }

            public String getUnitValue() {
                return unitValue;
            }

            public void setUnitValue(String unitValue) {
                this.unitValue = unitValue;
            }

            public AdditionalData getAdditionalData() {
                return additionalData;
            }

            public void setAdditionalData(AdditionalData additionalData) {
                this.additionalData = additionalData;
            }

            public String getRoute() {
                return route;
            }

            public void setRoute(String route) {
                this.route = route;
            }

            public String getBox() {
                return box;
            }

            public void setBox(String box) {
                this.box = box;
            }

            public String getHouseNumberPrefix() {
                return houseNumberPrefix;
            }

            public void setHouseNumberPrefix(String houseNumberPrefix) {
                this.houseNumberPrefix = houseNumberPrefix;
            }

            public String getAssignedHouseNumber() {
                return assignedHouseNumber;
            }

            public void setAssignedHouseNumber(String assignedHouseNumber) {
                this.assignedHouseNumber = assignedHouseNumber;
            }

            public String getHouseNumberSuffix() {
                return houseNumberSuffix;
            }

            public void setHouseNumberSuffix(String houseNumberSuffix) {
                this.houseNumberSuffix = houseNumberSuffix;
            }

            public String getStreetDirection() {
                return streetDirection;
            }

            public void setStreetDirection(String streetDirection) {
                this.streetDirection = streetDirection;
            }

            public Boolean getPrimaryStreetUnnamedIndicator() {
                return primaryStreetUnnamedIndicator;
            }

            public void setPrimaryStreetUnnamedIndicator(Boolean primaryStreetUnnamedIndicator) {
                this.primaryStreetUnnamedIndicator = primaryStreetUnnamedIndicator;
            }

            public String getPostalCodePlus4() {
                return postalCodePlus4;
            }

            public void setPostalCodePlus4(String postalCodePlus4) {
                this.postalCodePlus4 = postalCodePlus4;
            }

            public String getCounty() {
                return county;
            }

            public void setCounty(String county) {
                this.county = county;
            }

            public String getCountry() {
                return country;
            }

            public void setCountry(String country) {
                this.country = country;
            }

            public String getAdditionalName() {
                return additionalName;
            }

            public void setAdditionalName(String additionalName) {
                this.additionalName = additionalName;
            }

            public String getServiceId() {
                return serviceId;
            }

            public void setServiceId(String serviceId) {
                this.serviceId = serviceId;
            }

            public String getSingleLineStandardizedAddress() {
                return singleLineStandardizedAddress;
            }

            public void setSingleLineStandardizedAddress(String singleLineStandardizedAddress) {
                this.singleLineStandardizedAddress = singleLineStandardizedAddress;
            }

            public String getPostalBox() {
                return postalBox;
            }

            public void setPostalBox(String postalBox) {
                this.postalBox = postalBox;
            }

            public String getCityAbbreviatedName() {
                return cityAbbreviatedName;
            }

            public void setCityAbbreviatedName(String cityAbbreviatedName) {
                this.cityAbbreviatedName = cityAbbreviatedName;
            }

            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", namespace = "http://ovalgis.att.com/GIS/Namespaces/OVALSGIS/Types/Public/CommonDataModelPAV.xsd", propOrder = {
                    "customerName",
                    "businessName",
                    "attention",
                    "urbanizationCode"
            })
            public static class AdditionalData {
                private String customerName;
                private String businessName;
                private String attention;
                private String urbanizationCode;

                public String getCustomerName() {
                    return customerName;
                }

                public void setCustomerName(String customerName) {
                    this.customerName = customerName;
                }

                public String getBusinessName() {
                    return businessName;
                }

                public void setBusinessName(String businessName) {
                    this.businessName = businessName;
                }

                public String getAttention() {
                    return attention;
                }

                public void setAttention(String attention) {
                    this.attention = attention;
                }

                public String getUrbanizationCode() {
                    return urbanizationCode;
                }

                public void setUrbanizationCode(String urbanizationCode) {
                    this.urbanizationCode = urbanizationCode;
                }
            }
        }

        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", namespace = "http://ovalgis.att.com/GIS/Namespaces/OVALSGIS/Types/Public/CommonDataModelPAV.xsd", propOrder =  {
                "addressLine",
                "structureType",
                "structureValue",
                "levelType",
                "levelValue",
                "unitType",
                "unitValue",
                "city",
                "state",
                "postalCode",
                "postalCodePlus4",
                "county",
                "country",
                "additionalName",
                "crossStreet",
                "serviceId",
                "additionalData"
        })
        public static class Unfielded {
            private String addressLine;
            private String structureType;
            private String structureValue;
            private String levelType;
            private String levelValue;
            private String unitType;
            private String unitValue;
            private String city;
            private String state;
            private String postalCode;
            @XmlElement(name = "AdditionalData")
            private AdditionalData additionalData;
            private String postalCodePlus4;
            private String county;
            private String country;
            private String additionalName;
            private String crossStreet;
            private String serviceId;

            public String getAddressLine() {
                return addressLine;
            }

            public void setAddressLine(String addressLine) {
                this.addressLine = addressLine;
            }

            public String getStructureType() {
                return structureType;
            }

            public void setStructureType(String structureType) {
                this.structureType = structureType;
            }

            public String getStructureValue() {
                return structureValue;
            }

            public void setStructureValue(String structureValue) {
                this.structureValue = structureValue;
            }

            public String getLevelType() {
                return levelType;
            }

            public void setLevelType(String levelType) {
                this.levelType = levelType;
            }

            public String getLevelValue() {
                return levelValue;
            }

            public void setLevelValue(String levelValue) {
                this.levelValue = levelValue;
            }

            public String getUnitType() {
                return unitType;
            }

            public void setUnitType(String unitType) {
                this.unitType = unitType;
            }

            public String getUnitValue() {
                return unitValue;
            }

            public void setUnitValue(String unitValue) {
                this.unitValue = unitValue;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getState() {
                return state;
            }

            public void setState(String state) {
                this.state = state;
            }

            public String getPostalCode() {
                return postalCode;
            }

            public void setPostalCode(String postalCode) {
                this.postalCode = postalCode;
            }

            public String getPostalCodePlus4() {
                return postalCodePlus4;
            }

            public void setPostalCodePlus4(String postalCodePlus4) {
                this.postalCodePlus4 = postalCodePlus4;
            }

            public String getCounty() {
                return county;
            }

            public void setCounty(String county) {
                this.county = county;
            }

            public String getCountry() {
                return country;
            }

            public void setCountry(String country) {
                this.country = country;
            }

            public String getAdditionalName() {
                return additionalName;
            }

            public void setAdditionalName(String additionalName) {
                this.additionalName = additionalName;
            }

            public String getCrossStreet() {
                return crossStreet;
            }

            public void setCrossStreet(String crossStreet) {
                this.crossStreet = crossStreet;
            }

            public String getServiceId() {
                return serviceId;
            }

            public void setServiceId(String serviceId) {
                this.serviceId = serviceId;
            }

            public AdditionalData getAdditionalData() {
                return additionalData;
            }

            public void setAdditionalData(AdditionalData additionalData) {
                this.additionalData = additionalData;
            }

            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", namespace = "http://ovalgis.att.com/GIS/Namespaces/OVALSGIS/Types/Public/CommonDataModelPAV.xsd", propOrder =  {
                    "customerName",
                    "businessName",
                    "attention",
                    "urbanizationCode"
            })
            public static class AdditionalData {
                private String customerName;
                private String businessName;
                private String attention;
                private String urbanizationCode;

                public String getCustomerName() {
                    return customerName;
                }

                public void setCustomerName(String customerName) {
                    this.customerName = customerName;
                }

                public String getBusinessName() {
                    return businessName;
                }

                public void setBusinessName(String businessName) {
                    this.businessName = businessName;
                }

                public String getAttention() {
                    return attention;
                }

                public void setAttention(String attention) {
                    this.attention = attention;
                }

                public String getUrbanizationCode() {
                    return urbanizationCode;
                }

                public void setUrbanizationCode(String urbanizationCode) {
                    this.urbanizationCode = urbanizationCode;
                }
            }
        }
    }


}

//@XmlAccessorType(XmlAccessType.FIELD)
//@XmlType(name = "address", propOrder = {
//        "fielded",
//        "unfielded"
//})
//public class Address {
//    @XmlElement(name = "fielded")
//    protected FieldedAddress fielded;
//    @XmlElement(name = "unfielded")
//    protected UnfieldedAddress unfielded;
//
//    public FieldedAddress getFielded() { return fielded; }
//
//    public void setFielded(FieldedAddress value) { this.fielded = value; }
//
//    public UnfieldedAddress getUnfielded() { return unfielded; }
//
//    public void setUnfielded(UnfieldedAddress value) { this.unfielded = value; }
//}
