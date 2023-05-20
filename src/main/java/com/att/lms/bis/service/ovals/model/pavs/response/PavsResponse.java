package com.att.lms.bis.service.ovals.model.pavs.response;

import com.att.lms.bis.service.ovals.model.avsqub.request.AvsqubRequest;

import javax.xml.bind.annotation.*;
import javax.xml.registry.infomodel.PostalAddress;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * <?xml version="1.0" encoding="UTF-8" standalone="yes"?>
 * <rns:postalAddressValidationServiceResponse xmlns:tns="http://ovalgis.att.com/PostalAddressValidationRequest.xsd" xmlns:cdm="http://ovalgis.att.com/GIS/Namespaces/OVALSGIS/Types/Public/CommonDataModelPAV.xsd" xmlns:rns="http://ovalgis.att.com/PostalAddressValidationResponse.xsd">
 *     <rns:USPSDeliveryPointValidationAttributes>
 *         <rns:LocationProperties>
 *             <rns:addressMatchCode>4S80</rns:addressMatchCode>
 *             <rns:addressType>H</rns:addressType>
 *             <rns:addressMatchCodeDescription>Address validation successful</rns:addressMatchCodeDescription>
 *             <rns:addressMatchCodeStatus>S80000</rns:addressMatchCodeStatus>
 *             <rns:cassRecordType>H</rns:cassRecordType>
 *             <rns:cassAssignmentType>0</rns:cassAssignmentType>
 *             <rns:deliveryPointValidationCode>Y</rns:deliveryPointValidationCode>
 *             <rns:Coordinates>
 *                 <cdm:latitude>37.261545</cdm:latitude>
 *                 <cdm:longitude>-79.978998</cdm:longitude>
 *             </rns:Coordinates>
 *             <rns:LACSDetail>
 *                 <rns:lacsCode>F</rns:lacsCode>
 *             </rns:LACSDetail>
 *             <rns:AdditionalData>
 *                 <cdm:deliveryPointValidationFootnote>AABB</cdm:deliveryPointValidationFootnote>
 *                 <cdm:taxationGeocode>0147770056000</cdm:taxationGeocode>
 *             </rns:AdditionalData>
 *         </rns:LocationProperties>
 *         <rns:PostalAddress>
 *             <cdm:houseNumber>1730</cdm:houseNumber>
 *             <cdm:streetName>GRANDIN</cdm:streetName>
 *             <cdm:streetThoroughfare>RD</cdm:streetThoroughfare>
 *             <cdm:streetNameSuffix>SW</cdm:streetNameSuffix>
 *             <cdm:city>ROANOKE</cdm:city>
 *             <cdm:state>VA</cdm:state>
 *             <cdm:postalCode>24015</cdm:postalCode>
 *             <cdm:postalCodePlus4>2850</cdm:postalCodePlus4>
 *             <cdm:county>ROANOKE CITY</cdm:county>
 *             <cdm:unitType>APT</cdm:unitType>
 *             <cdm:unitValue>45</cdm:unitValue>
 *             <cdm:primaryAddressLine>1730 GRANDIN RD SW APT 45</cdm:primaryAddressLine>
 *             <cdm:cityAbbreviatedName>ROANOKE</cdm:cityAbbreviatedName>
 *             <cdm:AdditionalData>
 *                 <cdm:cassAddressLines>JOHN SMITH</cdm:cassAddressLines>
 *                 <cdm:cassAddressLines>AMY SMITH</cdm:cassAddressLines>
 *                 <cdm:cassAddressLines>C/O AMY SMITH</cdm:cassAddressLines>
 *                 <cdm:cassAddressLines>1730 GRANDIN RD SW APT 45</cdm:cassAddressLines>
 *                 <cdm:cassAddressLines>ROANOKE VA  24015-2850</cdm:cassAddressLines>
 *                 <cdm:auxiliaryAddressLines>JOHN SMITH</cdm:auxiliaryAddressLines>
 *                 <cdm:auxiliaryAddressLines>AMY SMITH</cdm:auxiliaryAddressLines>
 *                 <cdm:auxiliaryAddressLines>C/O AMY SMITH</cdm:auxiliaryAddressLines>
 *                 <cdm:countryCode>770</cdm:countryCode>
 *                 <cdm:abbreviatedCity>ROANOKE</cdm:abbreviatedCity>
 *             </cdm:AdditionalData>
 *         </rns:PostalAddress>
 *     </rns:USPSDeliveryPointValidationAttributes>
 *     <rns:HostResponse>
 *         <rns:status>
 *             <cdm:code>4S80</cdm:code>
 *             <cdm:description>Address validation successful</cdm:description>
 *         </rns:status>
 *         <rns:matchStatus>USPS_MATCH</rns:matchStatus>
 *     </rns:HostResponse>
 * </rns:postalAddressValidationServiceResponse>
 *
 *
 * <?xml version="1.0" encoding="UTF-8" standalone="yes"?>
 * <rns:postalAddressValidationServiceResponse xmlns:cdm="http://ovalgis.att.com/GIS/Namespaces/OVALSGIS/Types/Public/CommonDataModelPAV.xsd" xmlns:tns="http://ovalgis.att.com/PostalAddressValidationRequest.xsd" xmlns:rns="http://ovalgis.att.com/PostalAddressValidationResponse.xsd">
 *     <rns:USPSDeliveryPointValidationAttributes>
 *         <rns:LocationProperties>
 *             <rns:addressMatchCode>4S81</rns:addressMatchCode>
 *             <rns:addressType>H</rns:addressType>
 *             <rns:addressMatchCodeDescription>Address validation successful</rns:addressMatchCodeDescription>
 *             <rns:addressMatchCodeStatus>S81100</rns:addressMatchCodeStatus>
 *             <rns:cassRecordType>H</rns:cassRecordType>
 *             <rns:cassAssignmentType>0</rns:cassAssignmentType>
 *             <rns:deliveryPointValidationCode>Y</rns:deliveryPointValidationCode>
 *             <rns:Coordinates>
 *                 <cdm:latitude>29.518792</cdm:latitude>
 *                 <cdm:longitude>-98.462538</cdm:longitude>
 *             </rns:Coordinates>
 *             <rns:LACSDetail>
 *                 <rns:lacsCode>F</rns:lacsCode>
 *             </rns:LACSDetail>
 *             <rns:AdditionalData>
 *                 <cdm:deliveryPointValidationFootnote>AABB</cdm:deliveryPointValidationFootnote>
 *                 <cdm:taxationGeocode>0144029257000</cdm:taxationGeocode>
 *             </rns:AdditionalData>
 *         </rns:LocationProperties>
 *         <rns:PostalAddress>
 *             <cdm:houseNumber>8546</cdm:houseNumber>
 *             <cdm:streetName>BROADWAY</cdm:streetName>
 *             <cdm:city>SAN ANTONIO</cdm:city>
 *             <cdm:state>TX</cdm:state>
 *             <cdm:postalCode>78217</cdm:postalCode>
 *             <cdm:postalCodePlus4>6340</cdm:postalCodePlus4>
 *             <cdm:county>BEXAR</cdm:county>
 *             <cdm:unitType>STE</cdm:unitType>
 *             <cdm:unitValue>201</cdm:unitValue>
 *             <cdm:primaryAddressLine>8546 BROADWAY STE 201</cdm:primaryAddressLine>
 *             <cdm:cityAbbreviatedName>SAN ANTONIO</cdm:cityAbbreviatedName>
 *             <cdm:AdditionalData>
 *                 <cdm:cassAddressLines>JOHN SMITH</cdm:cassAddressLines>
 *                 <cdm:cassAddressLines>AMY SMITH</cdm:cassAddressLines>
 *                 <cdm:cassAddressLines>8546 BROADWAY STE 201</cdm:cassAddressLines>
 *                 <cdm:cassAddressLines>SAN ANTONIO TX  78217-6340</cdm:cassAddressLines>
 *                 <cdm:auxiliaryAddressLines>JOHN SMITH</cdm:auxiliaryAddressLines>
 *                 <cdm:auxiliaryAddressLines>AMY SMITH</cdm:auxiliaryAddressLines>
 *                 <cdm:countryCode>029</cdm:countryCode>
 *                 <cdm:abbreviatedCity>SAN ANTONIO</cdm:abbreviatedCity>
 *             </cdm:AdditionalData>
 *         </rns:PostalAddress>
 *     </rns:USPSDeliveryPointValidationAttributes>
 *     <rns:HostResponse>
 *         <rns:status>
 *             <cdm:code>4S81</cdm:code>
 *             <cdm:description>Address validation successful</cdm:description>
 *         </rns:status>
 *         <rns:matchStatus>USPS_MATCH</rns:matchStatus>
 *     </rns:HostResponse>
 * </rns:postalAddressValidationServiceResponse>
 *
 *
 *
 *
 *
 * <?xml version="1.0" encoding="UTF-8"?>
 * <!-- =========================================================================
 * 	AT&T Proprietary (Internal Use Only) Not for use or disclosure outside the
 * 	AT&T companies except under written agreement (c) 2007 AT&T Intellectual
 * 	Property. All rights reserved. AT&T and the AT&T logo are trademarks of AT&T
 * 	Intellectual Property. ======================================================================= -->
 *
 * <xs:schema xmlns:xs="http://www.w3.org/2001/XMLSchema"
 * 	targetNamespace="http://ovalgis.att.com/PostalAddressValidationResponse.xsd"
 * 	xmlns:tns="http://ovalgis.att.com/PostalAddressValidationResponse.xsd"
 * 	xmlns:cdm="http://ovalgis.att.com/GIS/Namespaces/OVALSGIS/Types/Public/CommonDataModelPAV.xsd"
 * 	elementFormDefault="qualified" version="1.0.0">
 *
 * 	<xs:import schemaLocation="CommonDataModelPAV.xsd"
 * 		namespace="http://ovalgis.att.com/GIS/Namespaces/OVALSGIS/Types/Public/CommonDataModelPAV.xsd"></xs:import>
 *
 * 	<xs:element name="postalAddressValidationServiceResponse"
 * 		type="tns:postalAddressValidationServiceResponseType">
 * 	</xs:element>
 *
 * 	<xs:complexType name="postalAddressValidationServiceResponseType">
 * 		<xs:sequence>
 * 			<xs:annotation>
 * 				<xs:documentation>
 * 					For reference, the OVALS GIS counterpart elements
 * 					are located in the GIS-OVALS PAVS schema in
 * 					PostalAddressValidationServiceRequest:Address
 * 				</xs:documentation>
 * 			</xs:annotation>
 *
 *
 *
 *
 * 			<xs:element name="USPSDeliveryPointValidationAttributes"
 * 				type="tns:USPSDeliveryPointValidationAttributesType" maxOccurs="10"
 * 				minOccurs="0">
 * 			</xs:element>
 * 			<xs:element name="HostResponse" type="tns:HostResponseType"
 * 				maxOccurs="1" minOccurs="1">
 * 			</xs:element>
 * 		</xs:sequence>
 * 	</xs:complexType>
 *
 *
 * 	<xs:complexType name="USPSDeliveryPointValidationAttributesType">
 * 		<xs:sequence>
 *
 *             <xs:element name="LocationProperties">
 * 				<xs:complexType>
 * 					<xs:sequence>
 * 						<xs:element name="addressMatchCode">
 * 							<xs:annotation>
 * 								<xs:documentation>
 * 									Match code indicating the precision
 * 									to which the address information was
 * 									matched.
 * 								</xs:documentation>
 * 								<xs:documentation>
 * 									Empty tag will not return
 * 								</xs:documentation>
 * 							</xs:annotation>
 * 							<xs:simpleType>
 * 								<xs:restriction base="xs:string">
 * 									<xs:minLength value="1" />
 * 									<xs:maxLength value="5" />
 * 								</xs:restriction>
 * 							</xs:simpleType>
 * 						</xs:element>
 * 						<xs:element name="cityStatePostalCodeValidType" minOccurs="0">
 * 							<xs:annotation>
 * 								<xs:documentation>
 * 									Indicator for validation of city,
 * 									state, postal code combination. "Y"
 * 									- valid, "N" - not valid, "I" -
 * 									incomplete input
 * 								</xs:documentation>
 * 								<xs:documentation>
 * 									Empty tag will not return, therefore, the
 * 									"" enumeration will never happen
 * 								</xs:documentation>
 * 							</xs:annotation>
 * 							<xs:simpleType>
 * 								<xs:restriction base="xs:string">
 * 									<xs:length value="1" />
 * 									<xs:enumeration value="" />
 * 									<xs:enumeration value="Y" />
 * 									<xs:enumeration value="N" />
 * 									<xs:enumeration value="I" />
 * 								</xs:restriction>
 * 							</xs:simpleType>
 * 						</xs:element>
 * 						<xs:element name="addressType" minOccurs="0">
 * 							<xs:annotation>
 * 								<xs:documentation>
 * 									The record-type indicator for the
 * 									assigned address. (e.g. F - Firm, G
 * 									- General delivery, H - High-rise, M
 * 									- Military, P - PO box, R - Rural
 * 									route, S - Street)
 * 								</xs:documentation>
 * 								<xs:documentation>
 * 									Empty tag will not return
 * 								</xs:documentation>
 * 							</xs:annotation>
 * 							<xs:simpleType>
 * 								<xs:restriction base="xs:string">
 * 									<xs:minLength value="1" />
 * 									<xs:maxLength value="2" />
 * 								</xs:restriction>
 * 							</xs:simpleType>
 * 						</xs:element>
 * 						<xs:element name="addressMatchCodeDescription" minOccurs="0">
 * 							<xs:annotation>
 * 								<xs:documentation>
 * 									Description for Address Match Code
 * 									values that designate no match, an
 * 									inexact match, or a match with
 * 									extenuating circumstances
 * 								</xs:documentation>
 * 							</xs:annotation>
 * 							<xs:simpleType>
 * 								<xs:restriction base="xs:string">
 * 									<xs:minLength value="0" />
 * 									<xs:maxLength value="100" />
 * 								</xs:restriction>
 * 							</xs:simpleType>
 * 						</xs:element>
 * 						<xs:element name="addressMatchCodeStatus" minOccurs="0">
 * 							<xs:annotation>
 * 								<xs:documentation>
 * 									Match code status indicating the
 * 									Business Object precision to which
 * 									the address information was matched.
 * 								</xs:documentation>
 * 								<xs:documentation>
 * 									Empty tag will not return
 * 								</xs:documentation>
 * 							</xs:annotation>
 * 							<xs:simpleType>
 * 								<xs:restriction base="xs:string">
 * 									<xs:minLength value="1" />
 * 									<xs:maxLength value="6" />
 * 								</xs:restriction>
 * 							</xs:simpleType>
 * 						</xs:element>
 * 						<xs:element name="cassRecordType" minOccurs="0">
 * 							<xs:annotation>
 * 								<xs:documentation>
 * 									The Coding Accuracy Support System
 * 									(CASS) Record Type. ( 1st char same
 * 									as Address Type; e.g. F - Firm, G -
 * 									General delivery, H - High-rise, M -
 * 									Military, P - PO box, R - Rural
 * 									route, S ?Street. 2nd char is D or
 * 									blank; D means that a more precise
 * 									zip4 could be assigned if more info
 * 									had been provided. Also ?D?= Unique
 * 									default; i.e., no zip4 assignments
 * 									or addr could not be matched.
 * 								</xs:documentation>
 * 								<xs:documentation>
 * 									Empty tag will not return
 * 								</xs:documentation>
 * 							</xs:annotation>
 * 							<xs:simpleType>
 * 								<xs:restriction base="xs:string">
 * 									<xs:minLength value="1" />
 * 									<xs:maxLength value="2" />
 * 								</xs:restriction>
 * 							</xs:simpleType>
 * 						</xs:element>
 * 						<xs:element name="cassAssignmentType" minOccurs="0">
 * 							<xs:annotation>
 * 								<xs:documentation>
 * 									The Coding Accuracy Support System
 * 									(CASS) Record Type. (e.g. O - No
 * 									tiebreak options, 1 - Inexact
 * 									postcode1 move assignemt, 2 -Input
 * 									postcode2 assignment, 3 -
 * 									tiebreaking used to make assignment
 * 								</xs:documentation>
 * 								<xs:documentation>
 * 									Empty tag will not return
 * 								</xs:documentation>
 * 							</xs:annotation>
 * 							<xs:simpleType>
 * 								<xs:restriction base="xs:string">
 * 									<xs:length value="1" />
 * 								</xs:restriction>
 * 							</xs:simpleType>
 * 						</xs:element>
 * 						<xs:element name="deliveryPointValidationCode" minOccurs="0">
 * 							<xs:annotation>
 * 								<xs:documentation>
 * 									The Delivery Point Validation Code
 * 									provides address status within DPV
 * 									directory. (e.g. Y - Primary ragne
 * 									and secondary ragne are valid, N -
 * 									Not valid delivery point, S -
 * 									Primary range is valid but seondary
 * 									is not, D - Primary range is valie
 * 									but seoncary not available, L -
 * 									Address triggered DPB locking)
 * 								</xs:documentation>
 * 								<xs:documentation>
 * 									Empty tag will not return
 * 								</xs:documentation>
 * 							</xs:annotation>
 * 							<xs:simpleType>
 * 								<xs:restriction base="xs:string">
 * 									<xs:length value="1" />
 * 								</xs:restriction>
 * 							</xs:simpleType>
 * 						</xs:element>
 * 						<xs:element name="Coordinates" type="cdm:AddressGeocodeCoordinatesInfo" minOccurs="0">
 * 							<xs:annotation>
 * 								<xs:documentation>
 * 									The MSAG Geographic positional
 * 									coordinates for longitude and
 * 									latitude
 * 								</xs:documentation>
 * 							</xs:annotation>
 * 						</xs:element>
 * 						<xs:element name="LACSDetail" minOccurs="0">
 * 							<xs:annotation>
 * 								<xs:documentation>
 * 									Locatable Address Conversion System
 * 									(LACS) Information. Returns the
 * 									match status for LACSLink
 * 									processing. ??= address converted by
 * 									LACSLink
 * 								</xs:documentation>
 * 							</xs:annotation>
 * 							<xs:complexType>
 * 								<xs:sequence>
 * 									<xs:element name="lacsCode" minOccurs="0">
 * 										<xs:annotation>
 * 											<xs:documentation>
 * 												Locatable Address
 * 												Conversion System (LACS)
 * 												code. (e.g. T - Address
 * 												needs 911 conversion, F
 * 												- Address does not need
 * 												conversion)
 * 											</xs:documentation>
 * 											<xs:documentation>
 * 												Empty tag will not return
 * 											</xs:documentation>
 * 										</xs:annotation>
 * 										<xs:simpleType>
 * 											<xs:restriction base="xs:string">
 * 												<xs:length value="1" />
 * 											</xs:restriction>
 * 										</xs:simpleType>
 * 									</xs:element>
 * 									<xs:element name="lacsLinkCode" minOccurs="0">
 * 										<xs:annotation>
 * 											<xs:documentation>
 * 												Returns the conversion
 * 												status of addresses
 * 												processed by LACS
 * 												Link:.. "Y" - Address
 * 												converted by LACSLink,
 * 												"N" - Address looked up
 * 												with LACSLink but not
 * 												converted, "F" - address
 * 												was a false-positive,
 * 												"S" - LACSLink
 * 												conversion was made, but
 * 												it was necessary to drop
 * 												the secondary
 * 												information
 * 											</xs:documentation>
 * 											<xs:documentation>
 * 												Empty tag will not return, therefore, the
 * 												?? enumeration will never happen
 * 											</xs:documentation>
 * 										</xs:annotation>
 * 										<xs:simpleType>
 * 											<xs:restriction base="xs:string">
 * 												<xs:length value="1" />
 * 												<xs:enumeration value="" />
 * 												<xs:enumeration value="Y" />
 * 												<xs:enumeration value="N" />
 * 												<xs:enumeration value="F" />
 * 												<xs:enumeration value="S" />
 * 											</xs:restriction>
 * 										</xs:simpleType>
 * 									</xs:element>
 * 									<xs:element name="lacsReturnCode" minOccurs="0">
 * 										<xs:annotation>
 * 											<xs:documentation>
 * 												Returns the match status
 * 												for LACSLink processing.
 * 											</xs:documentation>
 * 											<xs:documentation>
 * 												Empty tag will not return
 * 											</xs:documentation>
 * 										</xs:annotation>
 * 										<xs:simpleType>
 * 											<xs:restriction base="xs:string">
 * 												<xs:minLength value="1" />
 * 												<xs:maxLength value="2" />
 * 											</xs:restriction>
 * 										</xs:simpleType>
 * 									</xs:element>
 * 								</xs:sequence>
 * 							</xs:complexType>
 * 						</xs:element>
 * 						<xs:element name="PostalSupplementalData" minOccurs="0" maxOccurs="1000">
 * 							<xs:complexType>
 * 								<xs:annotation>
 * 									<xs:documentation>
 * 										Structure containing postal
 * 										location supplemental
 * 										information.
 * 									</xs:documentation>
 * 									<xs:documentation>
 * 										Empty tag will not return
 * 									</xs:documentation>
 * 								</xs:annotation>
 * 								<xs:sequence>
 * 									<xs:element name="businessName" minOccurs="0">
 * 										<xs:simpleType>
 * 											<xs:restriction base="xs:string">
 * 												<xs:maxLength value="40" />
 * 											</xs:restriction>
 * 										</xs:simpleType>
 * 									</xs:element>
 * 									<xs:element name="unitDescription" minOccurs="0">
 * 										<xs:simpleType>
 * 											<xs:restriction base="xs:string">
 * 												<xs:maxLength value="100" />
 * 											</xs:restriction>
 * 										</xs:simpleType>
 * 									</xs:element>
 * 									<xs:element name="unitNumberLow" minOccurs="0">
 * 										<xs:simpleType>
 * 											<xs:restriction base="xs:string">
 * 												<xs:maxLength value="100" />
 * 											</xs:restriction>
 * 										</xs:simpleType>
 * 									</xs:element>
 * 									<xs:element name="unitNumberHigh" minOccurs="0">
 * 										<xs:simpleType>
 * 											<xs:restriction base="xs:string">
 * 												<xs:maxLength value="100" />
 * 											</xs:restriction>
 * 										</xs:simpleType>
 * 									</xs:element>
 * 								</xs:sequence>
 * 							</xs:complexType>
 * 						</xs:element>
 * 						<xs:element name="AdditionalData" type="cdm:PostalLocationAttributesAdditionalDataInfo" minOccurs="0" />
 *
 * 					</xs:sequence>
 * 				</xs:complexType>
 * 			</xs:element>
 *             <xs:element name="PostalAddress"
 * 				type="cdm:USAFieldedAddressInfo">
 * 			</xs:element>
 * 		</xs:sequence>
 * 	</xs:complexType>
 *
 *     <xs:complexType name="LocationPropertiesType"></xs:complexType>
 *
 *     <xs:complexType name="HostResponseType">
 *     	<xs:sequence>
 *     		<xs:element name="status" type="cdm:ResponseInfo" maxOccurs="1" minOccurs="1"/>
 *     		<xs:element name="hostName" type="xs:string" maxOccurs="1" minOccurs="0"></xs:element>
 *     		<xs:element name="matchStatus" type="xs:string" maxOccurs="1" minOccurs="1"></xs:element>
 *     	</xs:sequence>
 *     </xs:complexType>
 *
 * </xs:schema>
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PostalAddressValidationServiceResponse", propOrder = {
        "uspsDeliveryPointValidationAttributes",
        "hostResponse"
})
@XmlRootElement(name="postalAddressValidationServiceResponse")
public class PavsResponse {

    @XmlElement(name = "USPSDeliveryPointValidationAttributes")
    protected USPSDeliveryPointValidationAttributes uspsDeliveryPointValidationAttributes;

    @XmlElement(name = "HostResponse")
    protected HostResponse hostResponse;

    public USPSDeliveryPointValidationAttributes getUspsDeliveryPointValidationAttributes() {
        return uspsDeliveryPointValidationAttributes;
    }

    public void setUspsDeliveryPointValidationAttributes(USPSDeliveryPointValidationAttributes value) { this.uspsDeliveryPointValidationAttributes = value; }

    public HostResponse getHostResponse() {
        return hostResponse;
    }

    public void setHostResponse(HostResponse value) { this.hostResponse = value; }


    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "locationProperties",
            "postalAddress"
    })
    public static class USPSDeliveryPointValidationAttributes {
        @XmlElement(name = "LocationProperties")
        protected LocationProperties locationProperties;
        @XmlElement(name = "PostalAddress")
        protected PostalAddress postalAddress;

        public LocationProperties getLocationProperties() {
            return locationProperties;
        }

        public void setLocationProperties(LocationProperties value) { this.locationProperties = value; }

        public PostalAddress getPostalAddress() { return postalAddress; }

        public void setPostalAddress(PostalAddress value) { this.postalAddress = value; }

        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "locationProperties", propOrder = {
                "addressMatchCode",
                "cityStatePostalCodeValidType",
                "addressType",
                "addressMatchCodeDescription",
                "addressMatchCodeStatus",
                "cassRecordType",
                "cassAssignmentType",
                "deliveryPointValidationCode",
                "Coordinates",
                "LACSDetail",
                "PostalSupplementalData",
                "AdditionalData"
        })
        public static class LocationProperties {
            protected String addressMatchCode;
            protected String cityStatePostalCodeValidType;
            protected String addressType;
            protected String addressMatchCodeDescription;
            protected String addressMatchCodeStatus;
            protected String cassRecordType;
            protected String cassAssignmentType;
            protected String deliveryPointValidationCode;
            protected Coordinates Coordinates;
            protected LACSDetail LACSDetail;
            protected PostalSupplementalData PostalSupplementalData;
            protected AdditionalData AdditionalData;

            public String getAddressMatchCode() { return addressMatchCode; }

            public void setAddressMatchCode(String value) { this.addressMatchCode = value; }

            public String getCityStatePostalCodeValidType() {
                return cityStatePostalCodeValidType;
            }

            public void setCityStatePostalCodeValidType(String cityStatePostalCodeValidType) {
                this.cityStatePostalCodeValidType = cityStatePostalCodeValidType;
            }

            public String getAddressType() { return addressType; }

            public void setAddressType(String value) { this.addressType = value; }

            public String getAddressMatchCodeDescription() { return addressMatchCodeDescription; }

            public void setAddressMatchCodeDescription(String value) { this.addressMatchCodeDescription = value; }

            public String getAddressMatchCodeStatus() { return addressMatchCodeStatus; }

            public void setAddressMatchCodeStatus(String value) { this.addressMatchCodeStatus = value; }

            public String getCassRecordType() { return cassRecordType; }

            public void setCassRecordType(String value) { this.cassRecordType = value; }

            public String getCassAssignmentType() { return cassAssignmentType; }

            public void setCassAssignmentType(String value) { this.cassAssignmentType = value; }

            public String getDeliveryPointValidationCode() { return deliveryPointValidationCode; }

            public void setDeliveryPointValidationCode(String value) { this.deliveryPointValidationCode = value; }

            public Coordinates getCoordinates() { return Coordinates; }

            public void setCoordinates(Coordinates value) { this.Coordinates = value; }

            public LACSDetail getLacsDetail() { return LACSDetail; }

            public void setLacsDetail(LACSDetail value) { this.LACSDetail = value; }

            public PostalSupplementalData getPostalSupplementalData() {
                return PostalSupplementalData;
            }

            public void setPostalSupplementalData(PostalSupplementalData postalSupplementalData) {
                this.PostalSupplementalData = postalSupplementalData;
            }

            public AdditionalData getAdditionalData() { return AdditionalData; }

            public void setAdditionalData(AdditionalData value) { this.AdditionalData = value; }

            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "Coordinates", propOrder = {
                    "latitude",
                    "longitude"
            })
            public static class Coordinates {
                @XmlElement(name = "latitude", namespace = "http://ovalgis.att.com/GIS/Namespaces/OVALSGIS/Types/Public/CommonDataModelPAV.xsd")
                protected String latitude;
                @XmlElement(name = "longitude", namespace = "http://ovalgis.att.com/GIS/Namespaces/OVALSGIS/Types/Public/CommonDataModelPAV.xsd")
                protected String longitude;

                public String getLatitude() { return latitude; }

                public void setLatitude(String value) { this.latitude = value; }

                public String getLongitude() { return longitude; }

                public void setLongitude(String value) { this.longitude = value; }
            }

            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "LACSDetail", propOrder = {
                    "lacsCode",
                    "lacsLinkCode",
                    "lacsReturnCode"
            })
            public static class LACSDetail {
                protected String lacsCode;
                protected String lacsLinkCode;
                protected String lacsReturnCode;

                public String getLacsCode() { return lacsCode; }

                public void setLacsCode(String value) { this.lacsCode = value; }

                public String getLacsLinkCode() {
                    return lacsLinkCode;
                }

                public void setLacsLinkCode(String lacsLinkCode) {
                    this.lacsLinkCode = lacsLinkCode;
                }

                public String getLacsReturnCode() {
                    return lacsReturnCode;
                }

                public void setLacsReturnCode(String lacsReturnCode) {
                    this.lacsReturnCode = lacsReturnCode;
                }
            }

            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "PostalSupplementalData", propOrder = {
                    "businessName",
                    "unitDescription",
                    "unitNumberLow",
                    "unitNumberHigh"
            })
            public static class PostalSupplementalData {
                protected String businessName;
                protected String unitDescription;
                protected String unitNumberLow;
                protected String unitNumberHigh;

                public String getBusinessName() {
                    return businessName;
                }

                public void setBusinessName(String businessName) {
                    this.businessName = businessName;
                }

                public String getUnitDescription() {
                    return unitDescription;
                }

                public void setUnitDescription(String unitDescription) {
                    this.unitDescription = unitDescription;
                }

                public String getUnitNumberLow() {
                    return unitNumberLow;
                }

                public void setUnitNumberLow(String unitNumberLow) {
                    this.unitNumberLow = unitNumberLow;
                }

                public String getUnitNumberHigh() {
                    return unitNumberHigh;
                }

                public void setUnitNumberHigh(String unitNumberHigh) {
                    this.unitNumberHigh = unitNumberHigh;
                }
            }

            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "AdditionalData", propOrder = {
                    "deliveryPointValidationFootnote",
                    "taxationGeocode"
            })
            public static class AdditionalData {
                @XmlElement(name = "deliveryPointValidationFootnote", namespace = "http://ovalgis.att.com/GIS/Namespaces/OVALSGIS/Types/Public/CommonDataModelPAV.xsd")
                protected String deliveryPointValidationFootnote;
                @XmlElement(name = "taxationGeocode", namespace = "http://ovalgis.att.com/GIS/Namespaces/OVALSGIS/Types/Public/CommonDataModelPAV.xsd")
                protected String taxationGeocode;

                public String getDeliveryPointValidationFootnote() { return deliveryPointValidationFootnote; }

                public void setDeliveryPointValidationFootnote(String value) { this.deliveryPointValidationFootnote = value; }

                public String getTaxationGeocode() { return taxationGeocode; }

                public void setTaxationGeocode(String value) { this.taxationGeocode = value; }
            }
        }

        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
                "route",
                "box",
                "houseNumberPrefix",
                "assignedHouseNumber",
                "houseNumberSuffix",
                "streetDirection",
                "primaryStreetUnnamedIndicator",
                "country",
                "structureType",
                "structureValue",
                "levelType",
                "levelValue",
                "additionalName",
                "serviceId",
                "singleLineStandardizedAddress",
                "secondaryAddressLine",
                "postalBox",
                "houseNumber",
                "streetName",
                "streetThoroughfare",
                "streetNameSuffix",
                "city",
                "state",
                "postalCode",
                "postalCodePlus4",
                "county",
                "unitType",
                "unitValue",
                "primaryAddressLine",
                "cityAbbreviatedName",
                "AdditionalData"
        })
        public static class PostalAddress {
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
            @XmlElement(name = "postalCodePlus4", namespace = "http://ovalgis.att.com/GIS/Namespaces/OVALSGIS/Types/Public/CommonDataModelPAV.xsd")
            protected String postalCodePlus4;
            @XmlElement(name = "county", namespace = "http://ovalgis.att.com/GIS/Namespaces/OVALSGIS/Types/Public/CommonDataModelPAV.xsd")
            protected String county;
            @XmlElement(name = "unitType", namespace = "http://ovalgis.att.com/GIS/Namespaces/OVALSGIS/Types/Public/CommonDataModelPAV.xsd")
            protected String unitType;
            @XmlElement(name = "unitValue", namespace = "http://ovalgis.att.com/GIS/Namespaces/OVALSGIS/Types/Public/CommonDataModelPAV.xsd")
            protected String unitValue;
            @XmlElement(name = "primaryAddressLine", namespace = "http://ovalgis.att.com/GIS/Namespaces/OVALSGIS/Types/Public/CommonDataModelPAV.xsd")
            protected String primaryAddressLine;
            @XmlElement(name = "cityAbbreviatedName", namespace = "http://ovalgis.att.com/GIS/Namespaces/OVALSGIS/Types/Public/CommonDataModelPAV.xsd")
            protected String cityAbbreviatedName;
            @XmlElement(name = "AdditionalData", namespace = "http://ovalgis.att.com/GIS/Namespaces/OVALSGIS/Types/Public/CommonDataModelPAV.xsd")
            protected AdditionalData AdditionalData;
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
            @XmlElement(name = "country", namespace = "http://ovalgis.att.com/GIS/Namespaces/OVALSGIS/Types/Public/CommonDataModelPAV.xsd")
            protected String country;
            @XmlElement(name = "structureType", namespace = "http://ovalgis.att.com/GIS/Namespaces/OVALSGIS/Types/Public/CommonDataModelPAV.xsd")
            protected String structureType;
            @XmlElement(name = "structureValue", namespace = "http://ovalgis.att.com/GIS/Namespaces/OVALSGIS/Types/Public/CommonDataModelPAV.xsd")
            protected String structureValue;
            @XmlElement(name = "levelType", namespace = "http://ovalgis.att.com/GIS/Namespaces/OVALSGIS/Types/Public/CommonDataModelPAV.xsd")
            protected String levelType;
            @XmlElement(name = "levelValue", namespace = "http://ovalgis.att.com/GIS/Namespaces/OVALSGIS/Types/Public/CommonDataModelPAV.xsd")
            protected String levelValue;
            @XmlElement(name = "additionalName", namespace = "http://ovalgis.att.com/GIS/Namespaces/OVALSGIS/Types/Public/CommonDataModelPAV.xsd")
            protected String additionalName;
            @XmlElement(name = "serviceId", namespace = "http://ovalgis.att.com/GIS/Namespaces/OVALSGIS/Types/Public/CommonDataModelPAV.xsd")
            protected String serviceId;
            @XmlElement(name = "singleLineStandardizedAddress", namespace = "http://ovalgis.att.com/GIS/Namespaces/OVALSGIS/Types/Public/CommonDataModelPAV.xsd")
            protected String singleLineStandardizedAddress;
            @XmlElement(name = "secondaryAddressLine", namespace = "http://ovalgis.att.com/GIS/Namespaces/OVALSGIS/Types/Public/CommonDataModelPAV.xsd")
            protected String secondaryAddressLine;
            @XmlElement(name = "postalBox", namespace = "http://ovalgis.att.com/GIS/Namespaces/OVALSGIS/Types/Public/CommonDataModelPAV.xsd")
            protected String postalBox;


            public String getHouseNumber() { return houseNumber; }

            public void setHouseNumber(String value) { this.houseNumber = value; }

            public String getStreetName() { return streetName; }

            public void setStreetName(String value) { this.streetName = value; }

            public String getStreetThoroughfare() { return streetThoroughfare; }

            public void setStreetThoroughfare(String value) { this.streetThoroughfare = value;}

            public String getStreetNameSuffix() { return streetNameSuffix; }

            public void setStreetNameSuffix(String value) { this.streetNameSuffix = value;}

            public String getCity() { return city; }

            public void setCity(String value) { this.city = value; }

            public String getState() { return state; }

            public void setState(String value) { this.state = value; }

            public String getPostalCode() { return postalCode; }

            public void setPostalCode(String value) { this.postalCode = value; }

            public String getPostalCodePlus4() { return postalCodePlus4; }

            public void setPostalCodePlus4(String value) { this.postalCodePlus4 = value; }

            public String getCounty() { return county; }

            public void setCounty(String value) { this.county = value; }

            public String getUnitType() { return unitType; }

            public void setUnitType(String value) { this.unitType = value; }

            public String getUnitValue() { return unitValue; }

            public void setUnitValue(String value) { this.unitValue = value; }

            public String getPrimaryAddressLine() { return primaryAddressLine; }

            public void setPrimaryAddressLine(String value) { this.primaryAddressLine = value; }

            public String getCityAbbreviatedName() { return cityAbbreviatedName; }

            public void setCityAbbreviatedName(String value) { this.cityAbbreviatedName = value; }

            public AdditionalData getAdditionalData() { return AdditionalData; }

            public void setAdditionalData(AdditionalData value) { this.AdditionalData = value; }

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

            public String getCountry() {
                return country;
            }

            public void setCountry(String country) {
                this.country = country;
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

            public String getSecondaryAddressLine() {
                return secondaryAddressLine;
            }

            public void setSecondaryAddressLine(String secondaryAddressLine) {
                secondaryAddressLine = secondaryAddressLine;
            }

            public String getPostalBox() {
                return postalBox;
            }

            public void setPostalBox(String postalBox) {
                this.postalBox = postalBox;
            }

            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = {
                    "cassAddressLines",
                    "auxiliaryAddressLines",
                    "countryCode",
                    "abbreviatedCity"
            })
            public static class AdditionalData {
                @XmlElement(name = "cassAddressLines", namespace = "http://ovalgis.att.com/GIS/Namespaces/OVALSGIS/Types/Public/CommonDataModelPAV.xsd")
                protected String cassAddressLines;
                @XmlElement(name = "auxiliaryAddressLines", namespace = "http://ovalgis.att.com/GIS/Namespaces/OVALSGIS/Types/Public/CommonDataModelPAV.xsd")
                protected String auxiliaryAddressLines;
                @XmlElement(name = "countryCode", namespace = "http://ovalgis.att.com/GIS/Namespaces/OVALSGIS/Types/Public/CommonDataModelPAV.xsd")
                protected String countryCode;
                @XmlElement(name = "abbreviatedCity", namespace = "http://ovalgis.att.com/GIS/Namespaces/OVALSGIS/Types/Public/CommonDataModelPAV.xsd")
                protected String abbreviatedCity;

                /** To add a new item, do as follows:
                 * <pre>
                 *    getCassAddressLines().add(newItem);
                 * </pre>
                 * */
                public String getCassAddressLines() {
//                    if (cassAddressLines == null) {
//                        cassAddressLines = new ArrayList<String>();
//                    }
                    return this.cassAddressLines;
                }

                public String getAuxiliaryAddressLines() {
//                    if (auxiliaryAddressLines == null) {
//                        auxiliaryAddressLines = new ArrayList<String>();
//                    }
                    return this.auxiliaryAddressLines;
                }

                public String getCountryCode() { return countryCode; }

                public void setCountryCode(String value) { this.countryCode = value; }

                public String getAbbreviatedCity() { return abbreviatedCity; }

                public void setAbbreviatedCity(String value) { this.abbreviatedCity = value; }
            }
        }
    }


    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "status",
            "hostName",
            "matchStatus"
    })
    public static class HostResponse {
        @XmlElement(name = "status")
        protected Status status;
        protected String hostName;
        protected String matchStatus;

        public Status getStatus() {return status;}

        public void setStatus(Status value) { this.status = value; }

        public String getHostName() {
            return hostName;
        }

        public void setHostName(String hostName) {
            this.hostName = hostName;
        }

        public String getMatchStatus() {return matchStatus; }

        public void setMatchStatus(String value) {this.matchStatus = value; }

        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
                "code",
                "description"
        })
        public static class Status {
            @XmlElement(name = "code", namespace = "http://ovalgis.att.com/GIS/Namespaces/OVALSGIS/Types/Public/CommonDataModelPAV.xsd")
            protected String code;
            @XmlElement(name = "description", namespace = "http://ovalgis.att.com/GIS/Namespaces/OVALSGIS/Types/Public/CommonDataModelPAV.xsd")
            protected String description;

            public String getCode() {return code;}

            public void setCode(String value) {this.code = value;}

            public String getDescription() {return description;}

            public void setDescription(String value) {this.description = value;}
        }
    }
}
