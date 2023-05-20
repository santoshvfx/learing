package com.att.lms.bis.service.ovals.model.pla.response;

import com.att.amnq.LocationStandards;
import com.att.amnq.RestrictionProperties;
import com.att.amnq.TelephoneNumberInfo;
import com.att.lms.bis.service.ovals.model.*;
import com.att.lms.bis.service.ovals.model.pla.request.PlaRequest;
//import org.apache.axis2.databinding.types.xsd.Date;

import javax.xml.bind.annotation.*;

import org.apache.axis2.databinding.types.Time;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProcessLocationAttributesResponse", propOrder = {
        "internationalLocationAttributes",
        "msagLocationAttributes",
        "sagLocationAttributes",
        "gisLocationAttributes",
        "lecLocationAttributes",
        "uspsDeliveryPointValidationAttributes",
        "additionalLocationAttributes",
        "hostResponse",
        "response"
})
@XmlRootElement(name = "ProcessLocationAttributesResponse")
public class PlaResponse {
    @XmlElement(name = "InternationalLocationAttributes")
    protected InternationalLocationAttributes internationalLocationAttributes;
    @XmlElement(name = "msagLocationAttributes")
    protected MSAGLocationAttributes msagLocationAttributes;
    @XmlElement(name = "sagLocationAttributes")
    protected SAGLocationAttributes sagLocationAttributes;
    @XmlElement(name = "GISLocationAttributes")
    protected GISLocationAttributes gisLocationAttributes;
    @XmlElement(name = "LECLocationAttributes")
    protected LECLocationAttributes lecLocationAttributes;
    @XmlElement(name = "USPSDeliveryPointValidationAttributes")
    //TODO maybe can pull from pavs response
    protected USPSDeliveryPointValidationAttributes uspsDeliveryPointValidationAttributes;
    @XmlElement(name = "AdditionalLocationAttributes")
    protected AdditionalLocationAttributes additionalLocationAttributes;
    @XmlElement(name = "HostResponse")
    //todo maybe pavs response
    protected HostResponse hostResponse;
    @XmlElement(name = "Response")
    protected ResponseInfo response;

    public InternationalLocationAttributes getInternationalLocationAttributes() {
        return internationalLocationAttributes;
    }

    public void setInternationalLocationAttributes(InternationalLocationAttributes internationalLocationAttributes) {
        this.internationalLocationAttributes = internationalLocationAttributes;
    }

    public MSAGLocationAttributes getMsagLocationAttributes() {
        return msagLocationAttributes;
    }

    public void setMsagLocationAttributes(MSAGLocationAttributes msagLocationAttributes) {
        this.msagLocationAttributes = msagLocationAttributes;
    }

    public SAGLocationAttributes getSagLocationAttributes() {
        return sagLocationAttributes;
    }

    public void setSagLocationAttributes(SAGLocationAttributes sagLocationAttributes) {
        this.sagLocationAttributes = sagLocationAttributes;
    }

    public GISLocationAttributes getGisLocationAttributes() {
        return gisLocationAttributes;
    }

    public void setGisLocationAttributes(GISLocationAttributes gisLocationAttributes) {
        this.gisLocationAttributes = gisLocationAttributes;
    }

    public LECLocationAttributes getLecLocationAttributes() {
        return lecLocationAttributes;
    }

    public void setLecLocationAttributes(LECLocationAttributes lecLocationAttributes) {
        this.lecLocationAttributes = lecLocationAttributes;
    }

    public USPSDeliveryPointValidationAttributes getUspsDeliveryPointValidationAttributes() {
        return uspsDeliveryPointValidationAttributes;
    }

    public void setUspsDeliveryPointValidationAttributes(USPSDeliveryPointValidationAttributes uspsDeliveryPointValidationAttributes) {
        this.uspsDeliveryPointValidationAttributes = uspsDeliveryPointValidationAttributes;
    }

    public AdditionalLocationAttributes getAdditionalLocationAttributes() {
        return additionalLocationAttributes;
    }

    public void setAdditionalLocationAttributes(AdditionalLocationAttributes additionalLocationAttributes) {
        this.additionalLocationAttributes = additionalLocationAttributes;
    }

    public HostResponse getHostResponse() {
        return hostResponse;
    }

    public void setHostResponse(HostResponse hostResponse) {
        this.hostResponse = hostResponse;
    }

    public ResponseInfo getResponse() {
        return response;
    }

    public void setResponse(ResponseInfo response) {
        this.response = response;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
           "coordinates",
           "addressAttributes",
           "gisMatchCode",
           "globalLocationId",
           "gisLocationCode",
           "score",
           "standardizedScore",
           "languageCode",
           "countryAddressFormat",
           "countryStreetFormat",
           "countryAddressExample",
           "ethernetServiceProviderCatalog"
    })
    public static class InternationalLocationAttributes {
        @XmlElement(name = "Coordinates")
        protected AddressGeocodeCoordinatesInfo coordinates;
        @XmlElement(name = "AddressAttributes")
        protected AddressAttributes addressAttributes;
        protected String gisMatchCode;
        protected String globalLocationId;
        protected String gisLocationCode;
        protected Double score;
        protected Double standardizedScore;
        protected String languageCode;
        protected String countryAddressFormat;
        protected String countryStreetFormat;
        protected String countryAddressExample;
        protected EthernetServiceProviderCatalog ethernetServiceProviderCatalog;

        public AddressGeocodeCoordinatesInfo getCoordinates() {
            return coordinates;
        }

        public void setCoordinates(AddressGeocodeCoordinatesInfo coordinates) {
            this.coordinates = coordinates;
        }

        public AddressAttributes getAddressAttributes() {
            return addressAttributes;
        }

        public void setAddressAttributes(AddressAttributes addressAttributes) {
            this.addressAttributes = addressAttributes;
        }

        public String getGisMatchCode() {
            return gisMatchCode;
        }

        public void setGisMatchCode(String gisMatchCode) {
            this.gisMatchCode = gisMatchCode;
        }

        public String getGlobalLocationId() {
            return globalLocationId;
        }

        public void setGlobalLocationId(String globalLocationId) {
            this.globalLocationId = globalLocationId;
        }

        public String getGisLocationCode() {
            return gisLocationCode;
        }

        public void setGisLocationCode(String gisLocationCode) {
            this.gisLocationCode = gisLocationCode;
        }

        public Double getScore() {
            return score;
        }

        public void setScore(Double score) {
            this.score = score;
        }

        public Double getStandardizedScore() {
            return standardizedScore;
        }

        public void setStandardizedScore(Double standardizedScore) {
            this.standardizedScore = standardizedScore;
        }

        public String getLanguageCode() {
            return languageCode;
        }

        public void setLanguageCode(String languageCode) {
            this.languageCode = languageCode;
        }

        public String getCountryAddressFormat() {
            return countryAddressFormat;
        }

        public void setCountryAddressFormat(String countryAddressFormat) {
            this.countryAddressFormat = countryAddressFormat;
        }

        public String getCountryStreetFormat() {
            return countryStreetFormat;
        }

        public void setCountryStreetFormat(String countryStreetFormat) {
            this.countryStreetFormat = countryStreetFormat;
        }

        public String getCountryAddressExample() {
            return countryAddressExample;
        }

        public void setCountryAddressExample(String countryAddressExample) {
            this.countryAddressExample = countryAddressExample;
        }

        public EthernetServiceProviderCatalog getEthernetServiceProviderCatalog() {
            return ethernetServiceProviderCatalog;
        }

        public void setEthernetServiceProviderCatalog(EthernetServiceProviderCatalog ethernetServiceProviderCatalog) {
            this.ethernetServiceProviderCatalog = ethernetServiceProviderCatalog;
        }

        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
                "matchAddress",
                "standardizedAddress",
                "percentAlong",
                "streetAddress",
                "neighborhood",
                "city",
                "subregion",
                "region",
                "regionAbbrevation",
                "zone",
                "postalCode",
                "postalCodeExtension",
                "country",
                "requestAddress",
                "administrativeAreaBoundariesLayer",
                "place",
                "locatorUsed",
                "matchLocation"
        })
        public static class AddressAttributes {
            protected String matchAddress;
            protected String standardizedAddress;
            protected Double percentAlong;
            protected String streetAddress;
            protected String neighborhood;
            protected String city;
            protected String subregion;
            protected String region;
            protected String regionAbbrevation;
            protected String zone;
            protected String postalCode;
            protected String postalCodeExtension;
            protected String country;
            protected String requestAddress;
            @XmlElement(name = "AdministrativeAreaBoundariesLayer")
            protected String administrativeAreaBoundariesLayer;
            protected String place;
            protected String locatorUsed;
            protected String matchLocation;

            public String getMatchAddress() {
                return matchAddress;
            }

            public void setMatchAddress(String matchAddress) {
                this.matchAddress = matchAddress;
            }

            public String getStandardizedAddress() {
                return standardizedAddress;
            }

            public void setStandardizedAddress(String standardizedAddress) {
                this.standardizedAddress = standardizedAddress;
            }

            public Double getPercentAlong() {
                return percentAlong;
            }

            public void setPercentAlong(Double percentAlong) {
                this.percentAlong = percentAlong;
            }

            public String getStreetAddress() {
                return streetAddress;
            }

            public void setStreetAddress(String streetAddress) {
                this.streetAddress = streetAddress;
            }

            public String getNeighborhood() {
                return neighborhood;
            }

            public void setNeighborhood(String neighborhood) {
                this.neighborhood = neighborhood;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getSubregion() {
                return subregion;
            }

            public void setSubregion(String subregion) {
                this.subregion = subregion;
            }

            public String getRegion() {
                return region;
            }

            public void setRegion(String region) {
                this.region = region;
            }

            public String getRegionAbbrevation() {
                return regionAbbrevation;
            }

            public void setRegionAbbrevation(String regionAbbrevation) {
                this.regionAbbrevation = regionAbbrevation;
            }

            public String getZone() {
                return zone;
            }

            public void setZone(String zone) {
                this.zone = zone;
            }

            public String getPostalCode() {
                return postalCode;
            }

            public void setPostalCode(String postalCode) {
                this.postalCode = postalCode;
            }

            public String getPostalCodeExtension() {
                return postalCodeExtension;
            }

            public void setPostalCodeExtension(String postalCodeExtension) {
                this.postalCodeExtension = postalCodeExtension;
            }

            public String getCountry() {
                return country;
            }

            public void setCountry(String country) {
                this.country = country;
            }

            public String getRequestAddress() {
                return requestAddress;
            }

            public void setRequestAddress(String requestAddress) {
                this.requestAddress = requestAddress;
            }

            public String getAdministrativeAreaBoundariesLayer() {
                return administrativeAreaBoundariesLayer;
            }

            public void setAdministrativeAreaBoundariesLayer(String administrativeAreaBoundariesLayer) {
                this.administrativeAreaBoundariesLayer = administrativeAreaBoundariesLayer;
            }

            public String getPlace() {
                return place;
            }

            public void setPlace(String place) {
                this.place = place;
            }

            public String getLocatorUsed() {
                return locatorUsed;
            }

            public void setLocatorUsed(String locatorUsed) {
                this.locatorUsed = locatorUsed;
            }

            public String getMatchLocation() {
                return matchLocation;
            }

            public void setMatchLocation(String matchLocation) {
                this.matchLocation = matchLocation;
            }

            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = {
                    "oldVersion",
                    "newVersion"
            })
            public static class AdministrativeAreaBoundariesLayers {
                @XmlElement(name = "OldVersion")
                protected OldVersion oldVersion;
                @XmlElement(name = "NewVersion")
                protected NewVersion newVersion;

                public OldVersion getOldVersion() {
                    return oldVersion;
                }

                public void setOldVersion(OldVersion oldVersion) {
                    this.oldVersion = oldVersion;
                }

                public NewVersion getNewVersion() {
                    return newVersion;
                }

                public void setNewVersion(NewVersion newVersion) {
                    this.newVersion = newVersion;
                }

                @XmlAccessorType(XmlAccessType.FIELD)
                @XmlType(name = "", propOrder = {
                        "level",
                        "value"
                })
                public static class OldVersion {
                    protected String level;
                    protected String value;

                    public String getLevel() {
                        return level;
                    }

                    public void setLevel(String level) {
                        this.level = level;
                    }

                    public String getValue() {
                        return value;
                    }

                    public void setValue(String value) {
                        this.value = value;
                    }
                }

                @XmlAccessorType(XmlAccessType.FIELD)
                @XmlType(name = "", propOrder = {
                        "level",
                        "value"
                })
                public static class NewVersion {
                    protected String level;
                    protected String value;

                    public String getLevel() {
                        return level;
                    }

                    public void setLevel(String level) {
                        this.level = level;
                    }

                    public String getValue() {
                        return value;
                    }

                    public void setValue(String value) {
                        this.value = value;
                    }
                }
            }
        }

        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
                "coverageName",
                "layerName"
        })
        public static class EthernetServiceProviderCatalog {
            protected String coverageName;
            protected String layerName;
        }


    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "msagRangedAddress",
            "msagProperties"
    })
    public static class MSAGLocationAttributes {
        @XmlElement(name = "MSAGRangedAddress")
        protected USARangedAddressInfo msagRangedAddress;
        @XmlElement(name = "MSAGProperties")
        protected MSAGProperties msagProperties;

        public USARangedAddressInfo getMsagRangedAddress() {
            return msagRangedAddress;
        }

        public void setMsagRangedAddress(USARangedAddressInfo msagRangedAddress) {
            this.msagRangedAddress = msagRangedAddress;
        }

        public MSAGProperties getMsagProperties() {
            return msagProperties;
        }

        public void setMsagProperties(MSAGProperties msagProperties) {
            this.msagProperties = msagProperties;
        }

        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
                "primarySafetyAccessPoint",
                "emergencyServiceNumber",
                "exchangeCode",
                "countyId",
                "tarCode",
                "distributionName",
                "mappedStreetName",
                "mappedCity"
        })
        public static class MSAGProperties {
                protected String primarySafetyAccessPoint;
                protected String emergencyServiceNumber;
                protected String exchangeCode;
                protected String countyId;
                protected String tarCode;
                protected String distributionName;
                protected String mappedStreetName;
                protected String mappedCity;

            public String getPrimarySafetyAccessPoint() {
                return primarySafetyAccessPoint;
            }

            public void setPrimarySafetyAccessPoint(String primarySafetyAccessPoint) {
                this.primarySafetyAccessPoint = primarySafetyAccessPoint;
            }

            public String getEmergencyServiceNumber() {
                return emergencyServiceNumber;
            }

            public void setEmergencyServiceNumber(String emergencyServiceNumber) {
                this.emergencyServiceNumber = emergencyServiceNumber;
            }

            public String getExchangeCode() {
                return exchangeCode;
            }

            public void setExchangeCode(String exchangeCode) {
                this.exchangeCode = exchangeCode;
            }

            public String getCountyId() {
                return countyId;
            }

            public void setCountyId(String countyId) {
                this.countyId = countyId;
            }

            public String getTarCode() {
                return tarCode;
            }

            public void setTarCode(String tarCode) {
                this.tarCode = tarCode;
            }

            public String getDistributionName() {
                return distributionName;
            }

            public void setDistributionName(String distributionName) {
                this.distributionName = distributionName;
            }

            public String getMappedStreetName() {
                return mappedStreetName;
            }

            public void setMappedStreetName(String mappedStreetName) {
                this.mappedStreetName = mappedStreetName;
            }

            public String getMappedCity() {
                return mappedCity;
            }

            public void setMappedCity(String mappedCity) {
                this.mappedCity = mappedCity;
            }
        }
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "address",
            "addressType",
            "locationProperties",
            "sagProperties",
            "telephoneProperties",
            "circuitIdProperties",
            "globalLocationId"
    })
    public static class SAGLocationAttributes {
        @XmlElement(name = "Address")
        protected Address address;
        protected String addressType;
        @XmlElement(name = "LocationProperties")
        protected LocationProperties locationProperties;
        @XmlElement(name = "SAGProperties")
        protected SAGProperties sagProperties;
        @XmlElement(name = "TelephoneProperties")
        protected TelephoneProperties telephoneProperties;
        @XmlElement(name = "CircuitIdProperties")
        protected CircuitIdProperties circuitIdProperties;
        protected String globalLocationId;

        public Address getAddress() {
            return address;
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        public String getAddressType() {
            return addressType;
        }

        public void setAddressType(String addressType) {
            this.addressType = addressType;
        }

        public LocationProperties getLocationProperties() {
            return locationProperties;
        }

        public void setLocationProperties(LocationProperties locationProperties) {
            this.locationProperties = locationProperties;
        }

        public SAGProperties getSagProperties() {
            return sagProperties;
        }

        public void setSagProperties(SAGProperties sagProperties) {
            this.sagProperties = sagProperties;
        }

        public TelephoneProperties getTelephoneProperties() {
            return telephoneProperties;
        }

        public void setTelephoneProperties(TelephoneProperties telephoneProperties) {
            this.telephoneProperties = telephoneProperties;
        }

        public CircuitIdProperties getCircuitIdProperties() {
            return circuitIdProperties;
        }

        public void setCircuitIdProperties(CircuitIdProperties circuitIdProperties) {
            this.circuitIdProperties = circuitIdProperties;
        }

        public String getGlobalLocationId() {
            return globalLocationId;
        }

        public void setGlobalLocationId(String globalLocationId) {
            this.globalLocationId = globalLocationId;
        }

        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
                "fielded",
                "sagRanged",
                "descriptiveAlternatives"
        })
        public static class Address {
            @XmlElement(name = "Fielded")
            protected USAFieldedAddressInfo fielded;
            @XmlElement(name = "SAGRanged")
            protected USARangedAddressInfo sagRanged;
            @XmlElement(name = "DescriptiveAlternatives")
            protected DescriptiveAlternatives descriptiveAlternatives;

            public USAFieldedAddressInfo getFielded() {
                return fielded;
            }

            public void setFielded(USAFieldedAddressInfo fielded) {
                this.fielded = fielded;
            }

            public USARangedAddressInfo getSagRanged() {
                return sagRanged;
            }

            public void setSagRanged(USARangedAddressInfo sagRanged) {
                this.sagRanged = sagRanged;
            }

            public DescriptiveAlternatives getDescriptiveAlternatives() {
                return descriptiveAlternatives;
            }

            public void setDescriptiveAlternatives(DescriptiveAlternatives descriptiveAlternatives) {
                this.descriptiveAlternatives = descriptiveAlternatives;
            }

            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = {
                    "descriptiveName",
                    "houseNumberPrefix",
                    "houseNumber",
                    "assignedHouseNumber",
                    "houseNumberSuffix",
                    "streetDirectional",
                    "streetThoroughfare",
                    "streetPostDirectionalSuffix",
                    "streetName",
                    "communityName",
                    "state",
                    "postalCode",
                    "descriptiveRemark"
            })
            public static class DescriptiveAlternatives {
                    protected String descriptiveName;
                    protected String houseNumberPrefix;
                    protected String houseNumber;
                    protected String assignedHouseNumber;
                    protected String houseNumberSuffix;
                    protected String streetDirectional;
                    protected String streetThoroughfare;
                    protected String streetPostDirectionalSuffix;
                    protected String streetName;
                    protected String communityName;
                    protected String state;
                    protected String postalCode;
                    protected String descriptiveRemark;

                public String getDescriptiveName() {
                    return descriptiveName;
                }

                public void setDescriptiveName(String descriptiveName) {
                    this.descriptiveName = descriptiveName;
                }

                public String getHouseNumberPrefix() {
                    return houseNumberPrefix;
                }

                public void setHouseNumberPrefix(String houseNumberPrefix) {
                    this.houseNumberPrefix = houseNumberPrefix;
                }

                public String getHouseNumber() {
                    return houseNumber;
                }

                public void setHouseNumber(String houseNumber) {
                    this.houseNumber = houseNumber;
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

                public String getStreetDirectional() {
                    return streetDirectional;
                }

                public void setStreetDirectional(String streetDirectional) {
                    this.streetDirectional = streetDirectional;
                }

                public String getStreetThoroughfare() {
                    return streetThoroughfare;
                }

                public void setStreetThoroughfare(String streetThoroughfare) {
                    this.streetThoroughfare = streetThoroughfare;
                }

                public String getStreetPostDirectionalSuffix() {
                    return streetPostDirectionalSuffix;
                }

                public void setStreetPostDirectionalSuffix(String streetPostDirectionalSuffix) {
                    this.streetPostDirectionalSuffix = streetPostDirectionalSuffix;
                }

                public String getStreetName() {
                    return streetName;
                }

                public void setStreetName(String streetName) {
                    this.streetName = streetName;
                }

                public String getCommunityName() {
                    return communityName;
                }

                public void setCommunityName(String communityName) {
                    this.communityName = communityName;
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

                public String getDescriptiveRemark() {
                    return descriptiveRemark;
                }

                public void setDescriptiveRemark(String descriptiveRemark) {
                    this.descriptiveRemark = descriptiveRemark;
                }
            }
        }

        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
                "fttbMDUFlag",
                "ownedWiringFlag",
                "wsopiFlag",
                "alternateDescriptiveName",
                "areaTransferCutData",
                "areaTransferNpaNxx",
                "areaTransferNumberChangeDate",
                "areaTransferWireCenterClli",
                "buildingCertification",
                "buildingClli",
                "clli8",
                "coordinates",
                "drivingInstructions",
                "fraudRemark",
                "geoMatchCode",
                "independentCompanyName",
                "livingUnitID",
                "locationStandards",
                "matchCode",
                "mldbGlid",
                "nspInqId",
                "primaryDescriptiveName",
                "regionFranchiseStatus",
                "restrictionProperties",
                "rmkB",
                "serviceInstructions",
                "smartMovesIndicator",
                "mCode",
                "vhCoordinates"
        })
        public static class LocationProperties {
                protected BooleanWithUndefinedInfo fttbMDUFlag;
                protected BooleanWithUndefinedInfo ownedWiringFlag;
                protected BooleanWithUndefinedInfo wsopiFlag;
                protected String alternateDescriptiveName;
                protected String areaTransferCutData;
                protected String areaTransferNpaNxx;
                protected String areaTransferNumberChangeDate;
                protected String areaTransferWireCenterClli;
                protected String buildingCertification;
                protected String buildingClli;
                protected String clli8;
                protected AddressGeocodeCoordinatesInfo coordinates;
                protected String drivingInstructions;
                protected String fraudRemark;
                protected String geoMatchCode;
                protected String independentCompanyName;
                protected String livingUnitID;
                @XmlElement(name = "LocationStandards")
                protected LocationStandards locationStandards;
                protected String matchCode;
                protected String mldbGlid;
                protected String nspInqId;
                protected String primaryDescriptiveName;
                protected String regionFranchiseStatus;
                @XmlElement(name = "restrictionProperties")
                protected RestrictionProperties restrictionProperties;
                protected String rmkB;
                protected String serviceInstructions;
                protected Boolean smartMovesIndicator;
                protected String mCode;
                @XmlElement(name = "VHCoordinates")
                protected VHCoordinates vhCoordinates;

            public BooleanWithUndefinedInfo getFttbMDUFlag() {
                return fttbMDUFlag;
            }

            public void setFttbMDUFlag(BooleanWithUndefinedInfo fttbMDUFlag) {
                this.fttbMDUFlag = fttbMDUFlag;
            }

            public BooleanWithUndefinedInfo getOwnedWiringFlag() {
                return ownedWiringFlag;
            }

            public void setOwnedWiringFlag(BooleanWithUndefinedInfo ownedWiringFlag) {
                this.ownedWiringFlag = ownedWiringFlag;
            }

            public BooleanWithUndefinedInfo getWsopiFlag() {
                return wsopiFlag;
            }

            public void setWsopiFlag(BooleanWithUndefinedInfo wsopiFlag) {
                this.wsopiFlag = wsopiFlag;
            }

            public String getAlternateDescriptiveName() {
                return alternateDescriptiveName;
            }

            public void setAlternateDescriptiveName(String alternateDescriptiveName) {
                this.alternateDescriptiveName = alternateDescriptiveName;
            }

            public String getAreaTransferCutData() {
                return areaTransferCutData;
            }

            public void setAreaTransferCutData(String areaTransferCutData) {
                this.areaTransferCutData = areaTransferCutData;
            }

            public String getAreaTransferNpaNxx() {
                return areaTransferNpaNxx;
            }

            public void setAreaTransferNpaNxx(String areaTransferNpaNxx) {
                this.areaTransferNpaNxx = areaTransferNpaNxx;
            }

            public String getAreaTransferNumberChangeDate() {
                return areaTransferNumberChangeDate;
            }

            public void setAreaTransferNumberChangeDate(String areaTransferNumberChangeDate) {
                this.areaTransferNumberChangeDate = areaTransferNumberChangeDate;
            }

            public String getAreaTransferWireCenterClli() {
                return areaTransferWireCenterClli;
            }

            public void setAreaTransferWireCenterClli(String areaTransferWireCenterClli) {
                this.areaTransferWireCenterClli = areaTransferWireCenterClli;
            }

            public String getBuildingCertification() {
                return buildingCertification;
            }

            public void setBuildingCertification(String buildingCertification) {
                this.buildingCertification = buildingCertification;
            }

            public String getBuildingClli() {
                return buildingClli;
            }

            public void setBuildingClli(String buildingClli) {
                this.buildingClli = buildingClli;
            }

            public String getClli8() {
                return clli8;
            }

            public void setClli8(String clli8) {
                this.clli8 = clli8;
            }

            public AddressGeocodeCoordinatesInfo getCoordinates() {
                return coordinates;
            }

            public void setCoordinates(AddressGeocodeCoordinatesInfo coordinates) {
                this.coordinates = coordinates;
            }

            public String getDrivingInstructions() {
                return drivingInstructions;
            }

            public void setDrivingInstructions(String drivingInstructions) {
                this.drivingInstructions = drivingInstructions;
            }

            public String getFraudRemark() {
                return fraudRemark;
            }

            public void setFraudRemark(String fraudRemark) {
                this.fraudRemark = fraudRemark;
            }

            public String getGeoMatchCode() {
                return geoMatchCode;
            }

            public void setGeoMatchCode(String geoMatchCode) {
                this.geoMatchCode = geoMatchCode;
            }

            public String getIndependentCompanyName() {
                return independentCompanyName;
            }

            public void setIndependentCompanyName(String independentCompanyName) {
                this.independentCompanyName = independentCompanyName;
            }

            public String getLivingUnitID() {
                return livingUnitID;
            }

            public void setLivingUnitID(String livingUnitID) {
                this.livingUnitID = livingUnitID;
            }

            public LocationStandards getLocationStandards() {
                return locationStandards;
            }

            public void setLocationStandards(LocationStandards locationStandards) {
                this.locationStandards = locationStandards;
            }

            public String getMatchCode() {
                return matchCode;
            }

            public void setMatchCode(String matchCode) {
                this.matchCode = matchCode;
            }

            public String getMldbGlid() {
                return mldbGlid;
            }

            public void setMldbGlid(String mldbGlid) {
                this.mldbGlid = mldbGlid;
            }

            public String getNspInqId() {
                return nspInqId;
            }

            public void setNspInqId(String nspInqId) {
                this.nspInqId = nspInqId;
            }

            public String getPrimaryDescriptiveName() {
                return primaryDescriptiveName;
            }

            public void setPrimaryDescriptiveName(String primaryDescriptiveName) {
                this.primaryDescriptiveName = primaryDescriptiveName;
            }

            public String getRegionFranchiseStatus() {
                return regionFranchiseStatus;
            }

            public void setRegionFranchiseStatus(String regionFranchiseStatus) {
                this.regionFranchiseStatus = regionFranchiseStatus;
            }

            public RestrictionProperties getRestrictionProperties() {
                return restrictionProperties;
            }

            public void setRestrictionProperties(RestrictionProperties restrictionProperties) {
                this.restrictionProperties = restrictionProperties;
            }

            public String getRmkB() {
                return rmkB;
            }

            public void setRmkB(String rmkB) {
                this.rmkB = rmkB;
            }

            public String getServiceInstructions() {
                return serviceInstructions;
            }

            public void setServiceInstructions(String serviceInstructions) {
                this.serviceInstructions = serviceInstructions;
            }

            public Boolean getSmartMovesIndicator() {
                return smartMovesIndicator;
            }

            public void setSmartMovesIndicator(Boolean smartMovesIndicator) {
                this.smartMovesIndicator = smartMovesIndicator;
            }

            public String getmCode() {
                return mCode;
            }

            public void setmCode(String mCode) {
                this.mCode = mCode;
            }

            public VHCoordinates getVHCoordinates() {
                return vhCoordinates;
            }

            public void setVHCoordinates(VHCoordinates VHCoordinates) {
                this.vhCoordinates = VHCoordinates;
            }

            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = {
                    "latitude",
                    "longitude"
            })
            public static class VHCoordinates {
                protected Double latitude;
                protected Double longitude;

                public Double getLatitude() {
                    return latitude;
                }

                public void setLatitude(Double latitude) {
                    this.latitude = latitude;
                }

                public Double getLongitude() {
                    return longitude;
                }

                public void setLongitude(Double longitude) {
                    this.longitude = longitude;
                }
            }
        }

        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
                "e911FourPercentSurchargeFlag",
                "e911SixteenPercentSurchargeFlag",
                "rangeOfBasicsFlag",
                "addressTypeCode",
                "assignedHouseNumberStatus",
                "assignedHouseNumberTypeCode",
                "businessOfficeCode",
                "centralOfficeCode",
                "clecText",
                "crossBoundaryState",
                "directoryGroupCode",
                "e911Exempt",
                "e911NonRecurringCharge",
                "e911Surcharge",
                "exchangeCode",
                "exchangeStreetCityRemark",
                "exco",
                "fccWireCenterTrial",
                "foreignTownshipListing",
                "geoSegmentCode",
                "gisLocationCode",
                "gisMatchCode",
                "icoIndicator",
                "lataCode",
                "legalEntity",
                "lineStationTrnasferClli8",
                "localRemarkText",
                "localServingOffice",
                "overlayNpaText",
                "postOffice",
                "primaryNpaNxx",
                "rangeRemark",
                "rangeTypeCode",
                "rateCetnter",
                "rateZoneBand",
                "rateZoneCode",
                "region",
                "remarkForTelephoneNumber",
                "sagAddressId",
                "sagArea",
                "sagNpa",
                "sagWireCenter",
                "segment",
                "sequence",
                "serviceAreaCode",
                "tarCode",
                "telephoneFeaturesCode",
                "ttaCode",
                "lfacsNpaNnx"
        })
        public static class SAGProperties {
            protected String e911FourPercentSurchargeFlag;
            protected String e911SixteenPercentSurchargeFlag;
            protected BooleanWithUndefinedInfo rangeOfBasicsFlag;
            protected String addressTypeCode;
            protected String assignedHouseNumberStatus;
            protected String assignedHouseNumberTypeCode;
            protected String businessOfficeCode;
            protected String centralOfficeCode;
            protected String clecText;
            protected String crossBoundaryState;
            protected String directoryGroupCode;
            protected String e911Exempt;
            protected String e911NonRecurringCharge;
            protected String e911Surcharge;
            protected String exchangeCode;
            protected String exchangeStreetCityRemark;
            protected String exco;
            protected String fccWireCenterTrial;
            protected String foreignTownshipListing;
            protected String geoSegmentCode;
            protected String gisLocationCode;
            protected String gisMatchCode;
            protected Boolean icoIndicator;
            protected String lataCode;
            protected String legalEntity;
            protected String lineStationTrnasferClli8;
            protected String localRemarkText;
            protected String localServingOffice;
            protected String overlayNpaText;
            protected String postOffice;
            protected NpaNxxLineInfo primaryNpaNxx;
            protected String rangeRemark;
            protected String rangeTypeCode;
            protected String rateCetnter;
            protected String rateZoneBand;
            protected String rateZoneCode;
            protected String region;
            protected String remarkForTelephoneNumber;
            protected String sagAddressId;
            protected String sagArea;
            protected String sagNpa;
            protected String sagWireCenter;
            protected String segment;
            protected String sequence;
            protected String serviceAreaCode;
            protected String tarCode;
            protected String telephoneFeaturesCode;
            protected String ttaCode;
            protected String lfacsNpaNnx;

            public String getE911FourPercentSurchargeFlag() {
                return e911FourPercentSurchargeFlag;
            }

            public void setE911FourPercentSurchargeFlag(String e911FourPercentSurchargeFlag) {
                this.e911FourPercentSurchargeFlag = e911FourPercentSurchargeFlag;
            }

            public String getE911SixteenPercentSurchargeFlag() {
                return e911SixteenPercentSurchargeFlag;
            }

            public void setE911SixteenPercentSurchargeFlag(String e911SixteenPercentSurchargeFlag) {
                this.e911SixteenPercentSurchargeFlag = e911SixteenPercentSurchargeFlag;
            }

            public BooleanWithUndefinedInfo getRangeOfBasicsFlag() {
                return rangeOfBasicsFlag;
            }

            public void setRangeOfBasicsFlag(BooleanWithUndefinedInfo rangeOfBasicsFlag) {
                this.rangeOfBasicsFlag = rangeOfBasicsFlag;
            }

            public String getAddressTypeCode() {
                return addressTypeCode;
            }

            public void setAddressTypeCode(String addressTypeCode) {
                this.addressTypeCode = addressTypeCode;
            }

            public String getAssignedHouseNumberStatus() {
                return assignedHouseNumberStatus;
            }

            public void setAssignedHouseNumberStatus(String assignedHouseNumberStatus) {
                this.assignedHouseNumberStatus = assignedHouseNumberStatus;
            }

            public String getAssignedHouseNumberTypeCode() {
                return assignedHouseNumberTypeCode;
            }

            public void setAssignedHouseNumberTypeCode(String assignedHouseNumberTypeCode) {
                this.assignedHouseNumberTypeCode = assignedHouseNumberTypeCode;
            }

            public String getBusinessOfficeCode() {
                return businessOfficeCode;
            }

            public void setBusinessOfficeCode(String businessOfficeCode) {
                this.businessOfficeCode = businessOfficeCode;
            }

            public String getCentralOfficeCode() {
                return centralOfficeCode;
            }

            public void setCentralOfficeCode(String centralOfficeCode) {
                this.centralOfficeCode = centralOfficeCode;
            }

            public String getClecText() {
                return clecText;
            }

            public void setClecText(String clecText) {
                this.clecText = clecText;
            }

            public String getCrossBoundaryState() {
                return crossBoundaryState;
            }

            public void setCrossBoundaryState(String crossBoundaryState) {
                this.crossBoundaryState = crossBoundaryState;
            }

            public String getDirectoryGroupCode() {
                return directoryGroupCode;
            }

            public void setDirectoryGroupCode(String directoryGroupCode) {
                this.directoryGroupCode = directoryGroupCode;
            }

            public String getE911Exempt() {
                return e911Exempt;
            }

            public void setE911Exempt(String e911Exempt) {
                this.e911Exempt = e911Exempt;
            }

            public String getE911NonRecurringCharge() {
                return e911NonRecurringCharge;
            }

            public void setE911NonRecurringCharge(String e911NonRecurringCharge) {
                this.e911NonRecurringCharge = e911NonRecurringCharge;
            }

            public String getE911Surcharge() {
                return e911Surcharge;
            }

            public void setE911Surcharge(String e911Surcharge) {
                this.e911Surcharge = e911Surcharge;
            }

            public String getExchangeCode() {
                return exchangeCode;
            }

            public void setExchangeCode(String exchangeCode) {
                this.exchangeCode = exchangeCode;
            }

            public String getExchangeStreetCityRemark() {
                return exchangeStreetCityRemark;
            }

            public void setExchangeStreetCityRemark(String exchangeStreetCityRemark) {
                this.exchangeStreetCityRemark = exchangeStreetCityRemark;
            }

            public String getExco() {
                return exco;
            }

            public void setExco(String exco) {
                this.exco = exco;
            }

            public String getFccWireCenterTrial() {
                return fccWireCenterTrial;
            }

            public void setFccWireCenterTrial(String fccWireCenterTrial) {
                this.fccWireCenterTrial = fccWireCenterTrial;
            }

            public String getForeignTownshipListing() {
                return foreignTownshipListing;
            }

            public void setForeignTownshipListing(String foreignTownshipListing) {
                this.foreignTownshipListing = foreignTownshipListing;
            }

            public String getGeoSegmentCode() {
                return geoSegmentCode;
            }

            public void setGeoSegmentCode(String geoSegmentCode) {
                this.geoSegmentCode = geoSegmentCode;
            }

            public String getGisLocationCode() {
                return gisLocationCode;
            }

            public void setGisLocationCode(String gisLocationCode) {
                this.gisLocationCode = gisLocationCode;
            }

            public String getGisMatchCode() {
                return gisMatchCode;
            }

            public void setGisMatchCode(String gisMatchCode) {
                this.gisMatchCode = gisMatchCode;
            }

            public Boolean getIcoIndicator() {
                return icoIndicator;
            }

            public void setIcoIndicator(Boolean icoIndicator) {
                this.icoIndicator = icoIndicator;
            }

            public String getLataCode() {
                return lataCode;
            }

            public void setLataCode(String lataCode) {
                this.lataCode = lataCode;
            }

            public String getLegalEntity() {
                return legalEntity;
            }

            public void setLegalEntity(String legalEntity) {
                this.legalEntity = legalEntity;
            }

            public String getLineStationTrnasferClli8() {
                return lineStationTrnasferClli8;
            }

            public void setLineStationTrnasferClli8(String lineStationTrnasferClli8) {
                this.lineStationTrnasferClli8 = lineStationTrnasferClli8;
            }

            public String getLocalRemarkText() {
                return localRemarkText;
            }

            public void setLocalRemarkText(String localRemarkText) {
                this.localRemarkText = localRemarkText;
            }

            public String getLocalServingOffice() {
                return localServingOffice;
            }

            public void setLocalServingOffice(String localServingOffice) {
                this.localServingOffice = localServingOffice;
            }

            public String getOverlayNpaText() {
                return overlayNpaText;
            }

            public void setOverlayNpaText(String overlayNpaText) {
                this.overlayNpaText = overlayNpaText;
            }

            public String getPostOffice() {
                return postOffice;
            }

            public void setPostOffice(String postOffice) {
                this.postOffice = postOffice;
            }

            public NpaNxxLineInfo getPrimaryNpaNxx() {
                return primaryNpaNxx;
            }

            public void setPrimaryNpaNxx(NpaNxxLineInfo primaryNpaNxx) {
                this.primaryNpaNxx = primaryNpaNxx;
            }

            public String getRangeRemark() {
                return rangeRemark;
            }

            public void setRangeRemark(String rangeRemark) {
                this.rangeRemark = rangeRemark;
            }

            public String getRangeTypeCode() {
                return rangeTypeCode;
            }

            public void setRangeTypeCode(String rangeTypeCode) {
                this.rangeTypeCode = rangeTypeCode;
            }

            public String getRateCetnter() {
                return rateCetnter;
            }

            public void setRateCetnter(String rateCetnter) {
                this.rateCetnter = rateCetnter;
            }

            public String getRateZoneBand() {
                return rateZoneBand;
            }

            public void setRateZoneBand(String rateZoneBand) {
                this.rateZoneBand = rateZoneBand;
            }

            public String getRateZoneCode() {
                return rateZoneCode;
            }

            public void setRateZoneCode(String rateZoneCode) {
                this.rateZoneCode = rateZoneCode;
            }

            public String getRegion() {
                return region;
            }

            public void setRegion(String region) {
                this.region = region;
            }

            public String getRemarkForTelephoneNumber() {
                return remarkForTelephoneNumber;
            }

            public void setRemarkForTelephoneNumber(String remarkForTelephoneNumber) {
                this.remarkForTelephoneNumber = remarkForTelephoneNumber;
            }

            public String getSagAddressId() {
                return sagAddressId;
            }

            public void setSagAddressId(String sagAddressId) {
                this.sagAddressId = sagAddressId;
            }

            public String getSagArea() {
                return sagArea;
            }

            public void setSagArea(String sagArea) {
                this.sagArea = sagArea;
            }

            public String getSagNpa() {
                return sagNpa;
            }

            public void setSagNpa(String sagNpa) {
                this.sagNpa = sagNpa;
            }

            public String getSagWireCenter() {
                return sagWireCenter;
            }

            public void setSagWireCenter(String sagWireCenter) {
                this.sagWireCenter = sagWireCenter;
            }

            public String getSegment() {
                return segment;
            }

            public void setSegment(String segment) {
                this.segment = segment;
            }

            public String getSequence() {
                return sequence;
            }

            public void setSequence(String sequence) {
                this.sequence = sequence;
            }

            public String getServiceAreaCode() {
                return serviceAreaCode;
            }

            public void setServiceAreaCode(String serviceAreaCode) {
                this.serviceAreaCode = serviceAreaCode;
            }

            public String getTarCode() {
                return tarCode;
            }

            public void setTarCode(String tarCode) {
                this.tarCode = tarCode;
            }

            public String getTelephoneFeaturesCode() {
                return telephoneFeaturesCode;
            }

            public void setTelephoneFeaturesCode(String telephoneFeaturesCode) {
                this.telephoneFeaturesCode = telephoneFeaturesCode;
            }

            public String getTtaCode() {
                return ttaCode;
            }

            public void setTtaCode(String ttaCode) {
                this.ttaCode = ttaCode;
            }

            public String getLfacsNpaNnx() {
                return lfacsNpaNnx;
            }

            public void setLfacsNpaNnx(String lfacsNpaNnx) {
                this.lfacsNpaNnx = lfacsNpaNnx;
            }
        }

        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
                "telephoneNumber",
                "facilitiesAvailableFlag",
                "quickDialToneFlag",
                "quickServerFlag",
                "disconnectReason",
                "listedName",
                "status",
                "activityDate"
        })
        public static class TelephoneProperties {
            protected String telephoneNumber;
            protected BooleanWithUndefinedInfo facilitiesAvailableFlag;
            protected BooleanWithUndefinedInfo quickDialToneFlag;
            protected BooleanWithUndefinedInfo quickServerFlag;
            protected String disconnectReason;
            protected String listedName;
            protected String status;
            protected String activityDate;

            public String getTelephoneNumber() {
                return telephoneNumber;
            }

            public void setTelephoneNumber(String telephoneNumber) {
                this.telephoneNumber = telephoneNumber;
            }

            public BooleanWithUndefinedInfo getFacilitiesAvailableFlag() {
                return facilitiesAvailableFlag;
            }

            public void setFacilitiesAvailableFlag(BooleanWithUndefinedInfo facilitiesAvailableFlag) {
                this.facilitiesAvailableFlag = facilitiesAvailableFlag;
            }

            public BooleanWithUndefinedInfo getQuickDialToneFlag() {
                return quickDialToneFlag;
            }

            public void setQuickDialToneFlag(BooleanWithUndefinedInfo quickDialToneFlag) {
                this.quickDialToneFlag = quickDialToneFlag;
            }

            public BooleanWithUndefinedInfo getQuickServerFlag() {
                return quickServerFlag;
            }

            public void setQuickServerFlag(BooleanWithUndefinedInfo quickServerFlag) {
                this.quickServerFlag = quickServerFlag;
            }

            public String getDisconnectReason() {
                return disconnectReason;
            }

            public void setDisconnectReason(String disconnectReason) {
                this.disconnectReason = disconnectReason;
            }

            public String getListedName() {
                return listedName;
            }

            public void setListedName(String listedName) {
                this.listedName = listedName;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getActivityDate() {
                return activityDate;
            }

            public void setActivityDate(String activityDate) {
                this.activityDate = activityDate;
            }
        }

        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
                "circuitIdentifier",
                "status",
                "activityDate"
        })
        public static class CircuitIdProperties {
                protected String circuitIdentifier;
                protected String status;
                protected String activityDate;
        }
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "fieldedAddress",
            "addressType",
            "globalLocationId",
            "locationProperties",
            "dtvDetails",
            "globalLocationIdExtension",
            "ovalsPropertyId",
            "ovalsBuildingId"
    })
    public static class GISLocationAttributes {
        @XmlElement(name = "FieldedAddress")
        protected USAFieldedAddressInfo fieldedAddress;
        protected String addressType;
        protected String globalLocationId;
        @XmlElement(name = "LocationProperties")
        protected LocationProperties locationProperties;
        @XmlElement(name = "DTVDetails")
        protected DTVDetails dtvDetails;
        protected String globalLocationIdExtension;
        protected String ovalsPropertyId;
        protected String ovalsBuildingId;

        public USAFieldedAddressInfo getFieldedAddress() {
            return fieldedAddress;
        }

        public void setFieldedAddress(USAFieldedAddressInfo fieldedAddress) {
            this.fieldedAddress = fieldedAddress;
        }

        public String getAddressType() {
            return addressType;
        }

        public void setAddressType(String addressType) {
            this.addressType = addressType;
        }

        public String getGlobalLocationId() {
            return globalLocationId;
        }

        public void setGlobalLocationId(String globalLocationId) {
            this.globalLocationId = globalLocationId;
        }

        public LocationProperties getLocationProperties() {
            return locationProperties;
        }

        public void setLocationProperties(LocationProperties locationProperties) {
            this.locationProperties = locationProperties;
        }

        public DTVDetails getDtvDetails() {
            return dtvDetails;
        }

        public void setDtvDetails(DTVDetails dtvDetails) {
            this.dtvDetails = dtvDetails;
        }

        public String getGlobalLocationIdExtension() {
            return globalLocationIdExtension;
        }

        public void setGlobalLocationIdExtension(String globalLocationIdExtension) {
            this.globalLocationIdExtension = globalLocationIdExtension;
        }

        public String getOvalsPropertyId() {
            return ovalsPropertyId;
        }

        public void setOvalsPropertyId(String ovalsPropertyId) {
            this.ovalsPropertyId = ovalsPropertyId;
        }

        public String getOvalsBuildingId() {
            return ovalsBuildingId;
        }

        public void setOvalsBuildingId(String ovalsBuildingId) {
            this.ovalsBuildingId = ovalsBuildingId;
        }

        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
                "cityStatePostalCodeValidFlag",
                "convergedBillingAvailabilityFlag",
                "additionalDetails",
                "addressId",
                "addressMatchCode",
                "addressMatchCodeDescription",
                "affiliateName",
                "alternateDescriptiveName",
                "buildingClli",
                "centralOfficeCode",
                "centralOfficeType",
                "coordinates",
                "coreBasedStatisticalAreaCode",
                "countyCode",
                "exchangeCode",
                "exco",
                "gisLocationCode",
                "gisMatchCode",
                "horizontalCoordinate",
                "icoCompanyName",
                "icoServingWireCenterCLLI",
                "lataCode",
                "lataName",
                "legalEntity",
                "localProviderAbbreviatedName",
                "localProviderExchangeCode",
                "localProviderName",
                "localProviderNumber",
                "localProviderServingOfficeCode",
                "locator",
                "matchLevel",
                "matchStatus",
                "postOffice",
                "primaryDescriptiveName",
                "primaryNpaNxx",
                "rateCenterCode",
                "rateZone",
                "rateZoneBandCode",
                "region",
                "regionFranchiseStatus",
                "superScore",
                "swcCLLI",
                "tarCode",
                "verticalCoordinate",
                "wllTelephoneNumber",
                "connectAmericaFundPhase2Indicator",
                "localNetworkServices",
                "splitFlag",
                "taxGeoCode",
                "rateCenter"
        })
        public static class LocationProperties {
            protected BooleanWithUndefinedInfo cityStatePostalCodeValidFlag;
            protected BooleanWithUndefinedInfo convergedBillingAvailabilityFlag;
            protected String additionalDetails;
            protected String addressId;
            protected String addressMatchCode;
            protected String addressMatchCodeDescription;
            protected String affiliateName;
            protected String alternateDescriptiveName;
            protected String buildingClli;
            protected String centralOfficeCode;
            protected String centralOfficeType;
            @XmlElement(name = "Coordinates")
            protected AddressGeocodeCoordinatesInfo coordinates;
            protected String coreBasedStatisticalAreaCode;
            protected String countyCode;
            protected String exchangeCode;
            protected String exco;
            protected String gisLocationCode;
            protected String gisMatchCode;
            protected String horizontalCoordinate;
            protected String icoCompanyName;
            protected String icoServingWireCenterCLLI;
            protected String lataCode;
            protected String lataName;
            protected String legalEntity;
            protected String localProviderAbbreviatedName;
            protected String localProviderExchangeCode;
            protected String localProviderName;
            protected String localProviderNumber;
            protected String localProviderServingOfficeCode;
            protected String locator;
            protected String matchLevel;
            protected String matchStatus;
            protected String postOffice;
            protected String primaryDescriptiveName;
            protected NpaNxxLineInfo primaryNpaNxx;
            protected String rateCenterCode;
            protected String rateZone;
            protected String rateZoneBandCode;
            protected String region;
            protected String regionFranchiseStatus;
            protected Integer superScore;
            protected String swcCLLI;
            protected String tarCode;
            protected String verticalCoordinate;
            protected TelephoneNumberInfo wllTelephoneNumber;
            protected Boolean connectAmericaFundPhase2Indicator;
            @XmlElement(name = "LocalNetworkServices")
            protected LocalNetworkServices localNetworkServices;
            protected String splitFlag;
            protected String taxGeoCode;
            @XmlElement(name = "RateCenter")
            protected RateCenter rateCenter;

            public BooleanWithUndefinedInfo getCityStatePostalCodeValidFlag() {
                return cityStatePostalCodeValidFlag;
            }

            public void setCityStatePostalCodeValidFlag(BooleanWithUndefinedInfo cityStatePostalCodeValidFlag) {
                this.cityStatePostalCodeValidFlag = cityStatePostalCodeValidFlag;
            }

            public BooleanWithUndefinedInfo getConvergedBillingAvailabilityFlag() {
                return convergedBillingAvailabilityFlag;
            }

            public void setConvergedBillingAvailabilityFlag(BooleanWithUndefinedInfo convergedBillingAvailabilityFlag) {
                this.convergedBillingAvailabilityFlag = convergedBillingAvailabilityFlag;
            }

            public String getAdditionalDetails() {
                return additionalDetails;
            }

            public void setAdditionalDetails(String additionalDetails) {
                this.additionalDetails = additionalDetails;
            }

            public String getAddressId() {
                return addressId;
            }

            public void setAddressId(String addressId) {
                this.addressId = addressId;
            }

            public String getAddressMatchCode() {
                return addressMatchCode;
            }

            public void setAddressMatchCode(String addressMatchCode) {
                this.addressMatchCode = addressMatchCode;
            }

            public String getAddressMatchCodeDescription() {
                return addressMatchCodeDescription;
            }

            public void setAddressMatchCodeDescription(String addressMatchCodeDescription) {
                this.addressMatchCodeDescription = addressMatchCodeDescription;
            }

            public String getAffiliateName() {
                return affiliateName;
            }

            public void setAffiliateName(String affiliateName) {
                this.affiliateName = affiliateName;
            }

            public String getAlternateDescriptiveName() {
                return alternateDescriptiveName;
            }

            public void setAlternateDescriptiveName(String alternateDescriptiveName) {
                this.alternateDescriptiveName = alternateDescriptiveName;
            }

            public String getBuildingClli() {
                return buildingClli;
            }

            public void setBuildingClli(String buildingClli) {
                this.buildingClli = buildingClli;
            }

            public String getCentralOfficeCode() {
                return centralOfficeCode;
            }

            public void setCentralOfficeCode(String centralOfficeCode) {
                this.centralOfficeCode = centralOfficeCode;
            }

            public String getCentralOfficeType() {
                return centralOfficeType;
            }

            public void setCentralOfficeType(String centralOfficeType) {
                this.centralOfficeType = centralOfficeType;
            }

            public AddressGeocodeCoordinatesInfo getCoordinates() {
                return coordinates;
            }

            public void setCoordinates(AddressGeocodeCoordinatesInfo coordinates) {
                this.coordinates = coordinates;
            }

            public String getCoreBasedStatisticalAreaCode() {
                return coreBasedStatisticalAreaCode;
            }

            public void setCoreBasedStatisticalAreaCode(String coreBasedStatisticalAreaCode) {
                this.coreBasedStatisticalAreaCode = coreBasedStatisticalAreaCode;
            }

            public String getCountyCode() {
                return countyCode;
            }

            public void setCountyCode(String countyCode) {
                this.countyCode = countyCode;
            }

            public String getExchangeCode() {
                return exchangeCode;
            }

            public void setExchangeCode(String exchangeCode) {
                this.exchangeCode = exchangeCode;
            }

            public String getExco() {
                return exco;
            }

            public void setExco(String exco) {
                this.exco = exco;
            }

            public String getGisLocationCode() {
                return gisLocationCode;
            }

            public void setGisLocationCode(String gisLocationCode) {
                this.gisLocationCode = gisLocationCode;
            }

            public String getGisMatchCode() {
                return gisMatchCode;
            }

            public void setGisMatchCode(String gisMatchCode) {
                this.gisMatchCode = gisMatchCode;
            }

            public String getHorizontalCoordinate() {
                return horizontalCoordinate;
            }

            public void setHorizontalCoordinate(String horizontalCoordinate) {
                this.horizontalCoordinate = horizontalCoordinate;
            }

            public String getIcoCompanyName() {
                return icoCompanyName;
            }

            public void setIcoCompanyName(String icoCompanyName) {
                this.icoCompanyName = icoCompanyName;
            }

            public String getIcoServingWireCenterCLLI() {
                return icoServingWireCenterCLLI;
            }

            public void setIcoServingWireCenterCLLI(String icoServingWireCenterCLLI) {
                this.icoServingWireCenterCLLI = icoServingWireCenterCLLI;
            }

            public String getLataCode() {
                return lataCode;
            }

            public void setLataCode(String lataCode) {
                this.lataCode = lataCode;
            }

            public String getLataName() {
                return lataName;
            }

            public void setLataName(String lataName) {
                this.lataName = lataName;
            }

            public String getLegalEntity() {
                return legalEntity;
            }

            public void setLegalEntity(String legalEntity) {
                this.legalEntity = legalEntity;
            }

            public String getLocalProviderAbbreviatedName() {
                return localProviderAbbreviatedName;
            }

            public void setLocalProviderAbbreviatedName(String localProviderAbbreviatedName) {
                this.localProviderAbbreviatedName = localProviderAbbreviatedName;
            }

            public String getLocalProviderExchangeCode() {
                return localProviderExchangeCode;
            }

            public void setLocalProviderExchangeCode(String localProviderExchangeCode) {
                this.localProviderExchangeCode = localProviderExchangeCode;
            }

            public String getLocalProviderName() {
                return localProviderName;
            }

            public void setLocalProviderName(String localProviderName) {
                this.localProviderName = localProviderName;
            }

            public String getLocalProviderNumber() {
                return localProviderNumber;
            }

            public void setLocalProviderNumber(String localProviderNumber) {
                this.localProviderNumber = localProviderNumber;
            }

            public String getLocalProviderServingOfficeCode() {
                return localProviderServingOfficeCode;
            }

            public void setLocalProviderServingOfficeCode(String localProviderServingOfficeCode) {
                this.localProviderServingOfficeCode = localProviderServingOfficeCode;
            }

            public String getLocator() {
                return locator;
            }

            public void setLocator(String locator) {
                this.locator = locator;
            }

            public String getMatchLevel() {
                return matchLevel;
            }

            public void setMatchLevel(String matchLevel) {
                this.matchLevel = matchLevel;
            }

            public String getMatchStatus() {
                return matchStatus;
            }

            public void setMatchStatus(String matchStatus) {
                this.matchStatus = matchStatus;
            }

            public String getPostOffice() {
                return postOffice;
            }

            public void setPostOffice(String postOffice) {
                this.postOffice = postOffice;
            }

            public String getPrimaryDescriptiveName() {
                return primaryDescriptiveName;
            }

            public void setPrimaryDescriptiveName(String primaryDescriptiveName) {
                this.primaryDescriptiveName = primaryDescriptiveName;
            }

            public NpaNxxLineInfo getPrimaryNpaNxx() {
                return primaryNpaNxx;
            }

            public void setPrimaryNpaNxx(NpaNxxLineInfo primaryNpaNxx) {
                this.primaryNpaNxx = primaryNpaNxx;
            }

            public String getRateCenterCode() {
                return rateCenterCode;
            }

            public void setRateCenterCode(String rateCenterCode) {
                this.rateCenterCode = rateCenterCode;
            }

            public String getRateZone() {
                return rateZone;
            }

            public void setRateZone(String rateZone) {
                this.rateZone = rateZone;
            }

            public String getRateZoneBandCode() {
                return rateZoneBandCode;
            }

            public void setRateZoneBandCode(String rateZoneBandCode) {
                this.rateZoneBandCode = rateZoneBandCode;
            }

            public String getRegion() {
                return region;
            }

            public void setRegion(String region) {
                this.region = region;
            }

            public String getRegionFranchiseStatus() {
                return regionFranchiseStatus;
            }

            public void setRegionFranchiseStatus(String regionFranchiseStatus) {
                this.regionFranchiseStatus = regionFranchiseStatus;
            }

            public Integer getSuperScore() {
                return superScore;
            }

            public void setSuperScore(Integer superScore) {
                this.superScore = superScore;
            }

            public String getSwcCLLI() {
                return swcCLLI;
            }

            public void setSwcCLLI(String swcCLLI) {
                this.swcCLLI = swcCLLI;
            }

            public String getTarCode() {
                return tarCode;
            }

            public void setTarCode(String tarCode) {
                this.tarCode = tarCode;
            }

            public String getVerticalCoordinate() {
                return verticalCoordinate;
            }

            public void setVerticalCoordinate(String verticalCoordinate) {
                this.verticalCoordinate = verticalCoordinate;
            }

            public TelephoneNumberInfo getWllTelephoneNumber() {
                return wllTelephoneNumber;
            }

            public void setWllTelephoneNumber(TelephoneNumberInfo wllTelephoneNumber) {
                this.wllTelephoneNumber = wllTelephoneNumber;
            }

            public Boolean getConnectAmericaFundPhase2Indicator() {
                return connectAmericaFundPhase2Indicator;
            }

            public void setConnectAmericaFundPhase2Indicator(Boolean connectAmericaFundPhase2Indicator) {
                this.connectAmericaFundPhase2Indicator = connectAmericaFundPhase2Indicator;
            }

            public LocalNetworkServices getLocalNetworkServices() {
                return localNetworkServices;
            }

            public void setLocalNetworkServices(LocalNetworkServices localNetworkServices) {
                this.localNetworkServices = localNetworkServices;
            }

            public String getSplitFlag() {
                return splitFlag;
            }

            public void setSplitFlag(String splitFlag) {
                this.splitFlag = splitFlag;
            }

            public String getTaxGeoCode() {
                return taxGeoCode;
            }

            public void setTaxGeoCode(String taxGeoCode) {
                this.taxGeoCode = taxGeoCode;
            }

            public RateCenter getRateCenter() {
                return rateCenter;
            }

            public void setRateCenter(RateCenter rateCenter) {
                this.rateCenter = rateCenter;
            }

            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = {
                    "functionType",
                    "clli",
                    "localServingOffice",
                    "responseMessage"
            })
            public static class LocalNetworkServices {
                protected String functionType;
                protected String clli;
                protected String localServingOffice;
                protected String responseMessage;

                public String getFunctionType() {
                    return functionType;
                }

                public void setFunctionType(String functionType) {
                    this.functionType = functionType;
                }

                public String getClli() {
                    return clli;
                }

                public void setClli(String clli) {
                    this.clli = clli;
                }

                public String getLocalServingOffice() {
                    return localServingOffice;
                }

                public void setLocalServingOffice(String localServingOffice) {
                    this.localServingOffice = localServingOffice;
                }

                public String getResponseMessage() {
                    return responseMessage;
                }

                public void setResponseMessage(String responseMessage) {
                    this.responseMessage = responseMessage;
                }
            }

            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = {
                    "state",
                    "name",
                    "abbreviatedName"
            })
            public static class RateCenter {
                protected String state;
                protected String name;
                protected String abbreviatedName;

                public String getState() {
                    return state;
                }

                public void setState(String state) {
                    this.state = state;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getAbbreviatedName() {
                    return abbreviatedName;
                }

                public void setAbbreviatedName(String abbreviatedName) {
                    this.abbreviatedName = abbreviatedName;
                }
            }
        }

        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
                "locationProperties",
                "buildingDetails"
        })
        public static class DTVDetails {
            @XmlElement(name = "LocationProperties")
            protected LocationProperties locationProperties;
            @XmlElement(name = "BuildingDetails")
            protected BuildingDetails buildingDetails;

            public LocationProperties getLocationProperties() {
                return locationProperties;
            }

            public void setLocationProperties(LocationProperties locationProperties) {
                this.locationProperties = locationProperties;
            }

            public BuildingDetails getBuildingDetails() {
                return buildingDetails;
            }

            public void setBuildingDetails(BuildingDetails buildingDetails) {
                this.buildingDetails = buildingDetails;
            }

            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = {
                    "d2LiteEligibility",
                    "d2LiteRestriction",
                    "dtvServiceabilityIndicator",
                    "buildingCount",
                    "buildingCountDescription",
                    "carrierRoute",
                    "coreBasedStatisticalAreaCode",
                    "census",
                    "countyFIPS",
                    "daylightSavingsObservedIndicator",
                    "daylightSavingsObservedDescription",
                    "zipPlus4",
                    "deliveryPointCode",
                    "designatedMarketingArea",
                    "deliveryPointVerificationConfirmation",
                    "deliveryPointVerificationConfirmationDescriptions",
                    "dtvPropertyId",
                    "floorCount",
                    "geoMatchLevel",
                    "geoMatchLevelDescription",
                    "coordinates",
                    "numberBedrooms",
                    "ownOrRentIndicator",
                    "neustarPropertyId",
                    "propertyClass",
                    "propertyId",
                    "propertyType",
                    "propertyTypeDescription",
                    "recordType",
                    "recordTypeDescription",
                    "roomCount",
                    "stateFIPS",
                    "stateFIPSDescription",
                    "timeZoneDescription",
                    "timeZone",
                    "totalSquareFootage",
                    "yearBuilt",
                    "dealerRestrictedProperty",
                    "directTVConnectedProperty",
                    "partnerId",
                    "partnerName",
                    "partnerPhone",
                    "satelliteServicable",
                    "satelliteServiceableReasonCode",
                    "satelliteServiceableReasonCodeDescription",
                    "wiringScheme",
                    "wiringCodeDescription",
                    "fortyLadderFlag",
                    "multipleRoomViewSellableIndicator",
                    "bulkTVRecievers",
                    "propertyContractType",
                    "propertyModelCode",
                    "propertyStatus",
                    "numberOfTVFeesBulked",
                    "numberOfWiredUnits",
                    "connectedCommunityId",
                    "systemOperatorName",
                    "rightOfEntryParty",
                    "fulfillmentParty",
                    "jointBillingPartnerName",
                    "jointBillingPartnerCode"
            })
            public static class LocationProperties {
                protected String d2LiteEligibility;
                protected String d2LiteRestriction;
                protected Boolean dtvServiceabilityIndicator;
                protected String buildingCount;
                protected String buildingCountDescription;
                protected String carrierRoute;
                protected String coreBasedStatisticalAreaCode;
                @XmlElement(name = "Census")
                protected Census census;
                protected String countyFIPS;
                protected Boolean daylightSavingsObservedIndicator;
                protected String daylightSavingsObservedDescription;
                protected String zipPlus4;
                protected String deliveryPointCode;
                protected String designatedMarketingArea;
                protected String deliveryPointVerificationConfirmation;
                protected String deliveryPointVerificationConfirmationDescriptions;
                protected String dtvPropertyId;
                protected String floorCount;
                protected String geoMatchLevel;
                protected String geoMatchLevelDescription;
                @XmlElement(name = "Coordinates")
                protected AddressGeocodeCoordinatesInfo coordinates;
                protected String numberBedrooms;
                protected Boolean ownOrRentIndicator;
                protected String neustarPropertyId;
                protected String propertyClass;
                protected String propertyId;
                protected String propertyType;
                protected String propertyTypeDescription;
                protected String recordType;
                protected String recordTypeDescription;
                protected String roomCount;
                protected String stateFIPS;
                protected String stateFIPSDescription;
                protected String timeZoneDescription;
                protected String timeZone;
                protected String totalSquareFootage;
                protected String yearBuilt;
                protected String dealerRestrictedProperty;
                protected String directTVConnectedProperty;
                protected String partnerId;
                protected String partnerName;
                protected String partnerPhone;
                protected String satelliteServicable;
                protected String satelliteServiceableReasonCode;
                protected String satelliteServiceableReasonCodeDescription;
                protected String wiringScheme;
                protected String wiringCodeDescription;
                protected TrueFalseInfo fortyLadderFlag;
                protected Boolean multipleRoomViewSellableIndicator;
                protected Integer bulkTVRecievers;
                protected String propertyContractType;
                protected String propertyModelCode;
                protected String propertyStatus;
                protected Integer numberOfTVFeesBulked;
                protected Integer numberOfWiredUnits;
                protected String connectedCommunityId;
                protected String systemOperatorName;
                protected String rightOfEntryParty;
                protected String fulfillmentParty;
                protected String jointBillingPartnerName;
                protected String jointBillingPartnerCode;

                public String getD2LiteEligibility() {
                    return d2LiteEligibility;
                }

                public void setD2LiteEligibility(String d2LiteEligibility) {
                    this.d2LiteEligibility = d2LiteEligibility;
                }

                public String getD2LiteRestriction() {
                    return d2LiteRestriction;
                }

                public void setD2LiteRestriction(String d2LiteRestriction) {
                    this.d2LiteRestriction = d2LiteRestriction;
                }

                public Boolean getDtvServiceabilityIndicator() {
                    return dtvServiceabilityIndicator;
                }

                public void setDtvServiceabilityIndicator(Boolean dtvServiceabilityIndicator) {
                    this.dtvServiceabilityIndicator = dtvServiceabilityIndicator;
                }

                public String getBuildingCount() {
                    return buildingCount;
                }

                public void setBuildingCount(String buildingCount) {
                    this.buildingCount = buildingCount;
                }

                public String getBuildingCountDescription() {
                    return buildingCountDescription;
                }

                public void setBuildingCountDescription(String buildingCountDescription) {
                    this.buildingCountDescription = buildingCountDescription;
                }

                public String getCarrierRoute() {
                    return carrierRoute;
                }

                public void setCarrierRoute(String carrierRoute) {
                    this.carrierRoute = carrierRoute;
                }

                public String getCoreBasedStatisticalAreaCode() {
                    return coreBasedStatisticalAreaCode;
                }

                public void setCoreBasedStatisticalAreaCode(String coreBasedStatisticalAreaCode) {
                    this.coreBasedStatisticalAreaCode = coreBasedStatisticalAreaCode;
                }

                public Census getCensus() {
                    return census;
                }

                public void setCensus(Census census) {
                    this.census = census;
                }

                public String getCountyFIPS() {
                    return countyFIPS;
                }

                public void setCountyFIPS(String countyFIPS) {
                    this.countyFIPS = countyFIPS;
                }

                public Boolean getDaylightSavingsObservedIndicator() {
                    return daylightSavingsObservedIndicator;
                }

                public void setDaylightSavingsObservedIndicator(Boolean daylightSavingsObservedIndicator) {
                    this.daylightSavingsObservedIndicator = daylightSavingsObservedIndicator;
                }

                public String getDaylightSavingsObservedDescription() {
                    return daylightSavingsObservedDescription;
                }

                public void setDaylightSavingsObservedDescription(String daylightSavingsObservedDescription) {
                    this.daylightSavingsObservedDescription = daylightSavingsObservedDescription;
                }

                public String getZipPlus4() {
                    return zipPlus4;
                }

                public void setZipPlus4(String zipPlus4) {
                    this.zipPlus4 = zipPlus4;
                }

                public String getDeliveryPointCode() {
                    return deliveryPointCode;
                }

                public void setDeliveryPointCode(String deliveryPointCode) {
                    this.deliveryPointCode = deliveryPointCode;
                }

                public String getDesignatedMarketingArea() {
                    return designatedMarketingArea;
                }

                public void setDesignatedMarketingArea(String designatedMarketingArea) {
                    this.designatedMarketingArea = designatedMarketingArea;
                }

                public String getDeliveryPointVerificationConfirmation() {
                    return deliveryPointVerificationConfirmation;
                }

                public void setDeliveryPointVerificationConfirmation(String deliveryPointVerificationConfirmation) {
                    this.deliveryPointVerificationConfirmation = deliveryPointVerificationConfirmation;
                }

                public String getDeliveryPointVerificationConfirmationDescriptions() {
                    return deliveryPointVerificationConfirmationDescriptions;
                }

                public void setDeliveryPointVerificationConfirmationDescriptions(String deliveryPointVerificationConfirmationDescriptions) {
                    this.deliveryPointVerificationConfirmationDescriptions = deliveryPointVerificationConfirmationDescriptions;
                }

                public String getDtvPropertyId() {
                    return dtvPropertyId;
                }

                public void setDtvPropertyId(String dtvPropertyId) {
                    this.dtvPropertyId = dtvPropertyId;
                }

                public String getFloorCount() {
                    return floorCount;
                }

                public void setFloorCount(String floorCount) {
                    this.floorCount = floorCount;
                }

                public String getGeoMatchLevel() {
                    return geoMatchLevel;
                }

                public void setGeoMatchLevel(String geoMatchLevel) {
                    this.geoMatchLevel = geoMatchLevel;
                }

                public String getGeoMatchLevelDescription() {
                    return geoMatchLevelDescription;
                }

                public void setGeoMatchLevelDescription(String geoMatchLevelDescription) {
                    this.geoMatchLevelDescription = geoMatchLevelDescription;
                }

                public AddressGeocodeCoordinatesInfo getCoordinates() {
                    return coordinates;
                }

                public void setCoordinates(AddressGeocodeCoordinatesInfo coordinates) {
                    this.coordinates = coordinates;
                }

                public String getNumberBedrooms() {
                    return numberBedrooms;
                }

                public void setNumberBedrooms(String numberBedrooms) {
                    this.numberBedrooms = numberBedrooms;
                }

                public Boolean getOwnOrRentIndicator() {
                    return ownOrRentIndicator;
                }

                public void setOwnOrRentIndicator(Boolean ownOrRentIndicator) {
                    this.ownOrRentIndicator = ownOrRentIndicator;
                }

                public String getNeustarPropertyId() {
                    return neustarPropertyId;
                }

                public void setNeustarPropertyId(String neustarPropertyId) {
                    this.neustarPropertyId = neustarPropertyId;
                }

                public String getPropertyClass() {
                    return propertyClass;
                }

                public void setPropertyClass(String propertyClass) {
                    this.propertyClass = propertyClass;
                }

                public String getPropertyId() {
                    return propertyId;
                }

                public void setPropertyId(String propertyId) {
                    this.propertyId = propertyId;
                }

                public String getPropertyType() {
                    return propertyType;
                }

                public void setPropertyType(String propertyType) {
                    this.propertyType = propertyType;
                }

                public String getPropertyTypeDescription() {
                    return propertyTypeDescription;
                }

                public void setPropertyTypeDescription(String propertyTypeDescription) {
                    this.propertyTypeDescription = propertyTypeDescription;
                }

                public String getRecordType() {
                    return recordType;
                }

                public void setRecordType(String recordType) {
                    this.recordType = recordType;
                }

                public String getRecordTypeDescription() {
                    return recordTypeDescription;
                }

                public void setRecordTypeDescription(String recordTypeDescription) {
                    this.recordTypeDescription = recordTypeDescription;
                }

                public String getRoomCount() {
                    return roomCount;
                }

                public void setRoomCount(String roomCount) {
                    this.roomCount = roomCount;
                }

                public String getStateFIPS() {
                    return stateFIPS;
                }

                public void setStateFIPS(String stateFIPS) {
                    this.stateFIPS = stateFIPS;
                }

                public String getStateFIPSDescription() {
                    return stateFIPSDescription;
                }

                public void setStateFIPSDescription(String stateFIPSDescription) {
                    this.stateFIPSDescription = stateFIPSDescription;
                }

                public String getTimeZoneDescription() {
                    return timeZoneDescription;
                }

                public void setTimeZoneDescription(String timeZoneDescription) {
                    this.timeZoneDescription = timeZoneDescription;
                }

                public String getTimeZone() {
                    return timeZone;
                }

                public void setTimeZone(String timeZone) {
                    this.timeZone = timeZone;
                }

                public String getTotalSquareFootage() {
                    return totalSquareFootage;
                }

                public void setTotalSquareFootage(String totalSquareFootage) {
                    this.totalSquareFootage = totalSquareFootage;
                }

                public String getYearBuilt() {
                    return yearBuilt;
                }

                public void setYearBuilt(String yearBuilt) {
                    this.yearBuilt = yearBuilt;
                }

                public String getDealerRestrictedProperty() {
                    return dealerRestrictedProperty;
                }

                public void setDealerRestrictedProperty(String dealerRestrictedProperty) {
                    this.dealerRestrictedProperty = dealerRestrictedProperty;
                }

                public String getDirectTVConnectedProperty() {
                    return directTVConnectedProperty;
                }

                public void setDirectTVConnectedProperty(String directTVConnectedProperty) {
                    this.directTVConnectedProperty = directTVConnectedProperty;
                }

                public String getPartnerId() {
                    return partnerId;
                }

                public void setPartnerId(String partnerId) {
                    this.partnerId = partnerId;
                }

                public String getPartnerName() {
                    return partnerName;
                }

                public void setPartnerName(String partnerName) {
                    this.partnerName = partnerName;
                }

                public String getPartnerPhone() {
                    return partnerPhone;
                }

                public void setPartnerPhone(String partnerPhone) {
                    this.partnerPhone = partnerPhone;
                }

                public String getSatelliteServicable() {
                    return satelliteServicable;
                }

                public void setSatelliteServicable(String satelliteServicable) {
                    this.satelliteServicable = satelliteServicable;
                }

                public String getSatelliteServiceableReasonCode() {
                    return satelliteServiceableReasonCode;
                }

                public void setSatelliteServiceableReasonCode(String satelliteServiceableReasonCode) {
                    this.satelliteServiceableReasonCode = satelliteServiceableReasonCode;
                }

                public String getSatelliteServiceableReasonCodeDescription() {
                    return satelliteServiceableReasonCodeDescription;
                }

                public void setSatelliteServiceableReasonCodeDescription(String satelliteServiceableReasonCodeDescription) {
                    this.satelliteServiceableReasonCodeDescription = satelliteServiceableReasonCodeDescription;
                }

                public String getWiringScheme() {
                    return wiringScheme;
                }

                public void setWiringScheme(String wiringScheme) {
                    this.wiringScheme = wiringScheme;
                }

                public String getWiringCodeDescription() {
                    return wiringCodeDescription;
                }

                public void setWiringCodeDescription(String wiringCodeDescription) {
                    this.wiringCodeDescription = wiringCodeDescription;
                }

                public TrueFalseInfo getFortyLadderFlag() {
                    return fortyLadderFlag;
                }

                public void setFortyLadderFlag(TrueFalseInfo fortyLadderFlag) {
                    this.fortyLadderFlag = fortyLadderFlag;
                }

                public Boolean getMultipleRoomViewSellableIndicator() {
                    return multipleRoomViewSellableIndicator;
                }

                public void setMultipleRoomViewSellableIndicator(Boolean multipleRoomViewSellableIndicator) {
                    this.multipleRoomViewSellableIndicator = multipleRoomViewSellableIndicator;
                }

                public Integer getBulkTVRecievers() {
                    return bulkTVRecievers;
                }

                public void setBulkTVRecievers(Integer bulkTVRecievers) {
                    this.bulkTVRecievers = bulkTVRecievers;
                }

                public String getPropertyContractType() {
                    return propertyContractType;
                }

                public void setPropertyContractType(String propertyContractType) {
                    this.propertyContractType = propertyContractType;
                }

                public String getPropertyModelCode() {
                    return propertyModelCode;
                }

                public void setPropertyModelCode(String propertyModelCode) {
                    this.propertyModelCode = propertyModelCode;
                }

                public String getPropertyStatus() {
                    return propertyStatus;
                }

                public void setPropertyStatus(String propertyStatus) {
                    this.propertyStatus = propertyStatus;
                }

                public Integer getNumberOfTVFeesBulked() {
                    return numberOfTVFeesBulked;
                }

                public void setNumberOfTVFeesBulked(Integer numberOfTVFeesBulked) {
                    this.numberOfTVFeesBulked = numberOfTVFeesBulked;
                }

                public Integer getNumberOfWiredUnits() {
                    return numberOfWiredUnits;
                }

                public void setNumberOfWiredUnits(Integer numberOfWiredUnits) {
                    this.numberOfWiredUnits = numberOfWiredUnits;
                }

                public String getConnectedCommunityId() {
                    return connectedCommunityId;
                }

                public void setConnectedCommunityId(String connectedCommunityId) {
                    this.connectedCommunityId = connectedCommunityId;
                }

                public String getSystemOperatorName() {
                    return systemOperatorName;
                }

                public void setSystemOperatorName(String systemOperatorName) {
                    this.systemOperatorName = systemOperatorName;
                }

                public String getRightOfEntryParty() {
                    return rightOfEntryParty;
                }

                public void setRightOfEntryParty(String rightOfEntryParty) {
                    this.rightOfEntryParty = rightOfEntryParty;
                }

                public String getFulfillmentParty() {
                    return fulfillmentParty;
                }

                public void setFulfillmentParty(String fulfillmentParty) {
                    this.fulfillmentParty = fulfillmentParty;
                }

                public String getJointBillingPartnerName() {
                    return jointBillingPartnerName;
                }

                public void setJointBillingPartnerName(String jointBillingPartnerName) {
                    this.jointBillingPartnerName = jointBillingPartnerName;
                }

                public String getJointBillingPartnerCode() {
                    return jointBillingPartnerCode;
                }

                public void setJointBillingPartnerCode(String jointBillingPartnerCode) {
                    this.jointBillingPartnerCode = jointBillingPartnerCode;
                }

                @XmlAccessorType(XmlAccessType.FIELD)
                @XmlType(name = "", propOrder = {
                        "blockGroup",
                        "blockId",
                        "tract"
                })
                public static class Census {
                    protected String blockGroup;
                    protected String blockId;
                    protected String tract;

                    public String getBlockGroup() {
                        return blockGroup;
                    }

                    public void setBlockGroup(String blockGroup) {
                        this.blockGroup = blockGroup;
                    }

                    public String getBlockId() {
                        return blockId;
                    }

                    public void setBlockId(String blockId) {
                        this.blockId = blockId;
                    }

                    public String getTract() {
                        return tract;
                    }

                    public void setTract(String tract) {
                        this.tract = tract;
                    }
                }
            }

            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = {
                    "d2LiteinstallationNotes",
                    "building"
            })
            public static class BuildingDetails {
                @XmlElement(name = "D2LiteinstallationNotes")
                protected D2LiteinstallationNotes d2LiteinstallationNotes;
                @XmlElement(name = "Building")
                protected Building building;

                public D2LiteinstallationNotes getD2LiteinstallationNotes() {
                    return d2LiteinstallationNotes;
                }

                public void setD2LiteinstallationNotes(D2LiteinstallationNotes d2LiteinstallationNotes) {
                    this.d2LiteinstallationNotes = d2LiteinstallationNotes;
                }

                public Building getBuilding() {
                    return building;
                }

                public void setBuilding(Building building) {
                    this.building = building;
                }

                @XmlAccessorType(XmlAccessType.FIELD)
                @XmlType(name = "", propOrder = {
                        "name",
                        "assetType",
                        "installationNotes",
                        "serialNumber",
                        "buildingId"
                })
                public static class D2LiteinstallationNotes {
                    protected String name;
                    protected String assetType;
                    protected String installationNotes;
                    protected String serialNumber;
                    protected String buildingId;

                    public String getName() {
                        return name;
                    }

                    public void setName(String name) {
                        this.name = name;
                    }

                    public String getAssetType() {
                        return assetType;
                    }

                    public void setAssetType(String assetType) {
                        this.assetType = assetType;
                    }

                    public String getInstallationNotes() {
                        return installationNotes;
                    }

                    public void setInstallationNotes(String installationNotes) {
                        this.installationNotes = installationNotes;
                    }

                    public String getSerialNumber() {
                        return serialNumber;
                    }

                    public void setSerialNumber(String serialNumber) {
                        this.serialNumber = serialNumber;
                    }

                    public String getBuildingId() {
                        return buildingId;
                    }

                    public void setBuildingId(String buildingId) {
                        this.buildingId = buildingId;
                    }
                }

                @XmlAccessorType(XmlAccessType.FIELD)
                @XmlType(name = "", propOrder = {
                        "unitPropertyDetails",
                        "buildingAddresses"
                })
                public static class Building {
                    @XmlElement(name = "UnitPropertyDetails")
                    protected UnitPropertyDetails unitPropertyDetails;
                    @XmlElement(name = "BuildingAddresses")
                    protected BuildingAddresses buildingAddresses;

                    public UnitPropertyDetails getUnitPropertyDetails() {
                        return unitPropertyDetails;
                    }

                    public void setUnitPropertyDetails(UnitPropertyDetails unitPropertyDetails) {
                        this.unitPropertyDetails = unitPropertyDetails;
                    }

                    public BuildingAddresses getBuildingAddresses() {
                        return buildingAddresses;
                    }

                    public void setBuildingAddresses(BuildingAddresses buildingAddresses) {
                        this.buildingAddresses = buildingAddresses;
                    }

                    @XmlAccessorType(XmlAccessType.FIELD)
                    @XmlType(name = "", propOrder = {
                            "d2LiteEligibility",
                            "d2LiteRestriction"
                    })
                    public static class UnitPropertyDetails {
                        protected String d2LiteEligibility;
                        protected String d2LiteRestriction;

                        public String getD2LiteEligibility() {
                            return d2LiteEligibility;
                        }

                        public void setD2LiteEligibility(String d2LiteEligibility) {
                            this.d2LiteEligibility = d2LiteEligibility;
                        }

                        public String getD2LiteRestriction() {
                            return d2LiteRestriction;
                        }

                        public void setD2LiteRestriction(String d2LiteRestriction) {
                            this.d2LiteRestriction = d2LiteRestriction;
                        }
                    }

                    @XmlAccessorType(XmlAccessType.FIELD)
                    @XmlType(name = "", propOrder = {
                            "houseNumber",
                            "streetName",
                            "city",
                            "state",
                            "zipPlus4",
                            "postalCode",
                            "serviceAddressConfirmedIndicator"
                    })
                    public static class BuildingAddresses {
                        protected String houseNumber;
                        protected String streetName;
                        protected String city;
                        protected String state;
                        protected String zipPlus4;
                        protected String postalCode;
                        protected Boolean serviceAddressConfirmedIndicator;

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

                        public String getZipPlus4() {
                            return zipPlus4;
                        }

                        public void setZipPlus4(String zipPlus4) {
                            this.zipPlus4 = zipPlus4;
                        }

                        public String getPostalCode() {
                            return postalCode;
                        }

                        public void setPostalCode(String postalCode) {
                            this.postalCode = postalCode;
                        }

                        public Boolean getServiceAddressConfirmedIndicator() {
                            return serviceAddressConfirmedIndicator;
                        }

                        public void setServiceAddressConfirmedIndicator(Boolean serviceAddressConfirmedIndicator) {
                            this.serviceAddressConfirmedIndicator = serviceAddressConfirmedIndicator;
                        }
                    }
                }
            }
        }




    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "requestId",
            "addressMatchNumber",
            "validationResult",
            "ilecLSO",
            "fieldedAddress",
            "splitFlag"
    })
    public static class LECLocationAttributes {
        protected String requestId;
        protected String addressMatchNumber;
        protected String validationResult;
        protected String ilecLSO;
        @XmlElement(name = "FieldedAddress")
        protected USAFieldedAddressInfo fieldedAddress;
        protected TrueFalseInfo splitFlag;

        public String getRequestId() {
            return requestId;
        }

        public void setRequestId(String requestId) {
            this.requestId = requestId;
        }

        public String getAddressMatchNumber() {
            return addressMatchNumber;
        }

        public void setAddressMatchNumber(String addressMatchNumber) {
            this.addressMatchNumber = addressMatchNumber;
        }

        public String getValidationResult() {
            return validationResult;
        }

        public void setValidationResult(String validationResult) {
            this.validationResult = validationResult;
        }

        public String getIlecLSO() {
            return ilecLSO;
        }

        public void setIlecLSO(String ilecLSO) {
            this.ilecLSO = ilecLSO;
        }

        public USAFieldedAddressInfo getFieldedAddress() {
            return fieldedAddress;
        }

        public void setFieldedAddress(USAFieldedAddressInfo fieldedAddress) {
            this.fieldedAddress = fieldedAddress;
        }

        public TrueFalseInfo getSplitFlag() {
            return splitFlag;
        }

        public void setSplitFlag(TrueFalseInfo splitFlag) {
            this.splitFlag = splitFlag;
        }
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "postalAddress",
            "locationProperties"
    })
    public static class USPSDeliveryPointValidationAttributes {
        @XmlElement(name = "PostalAddress")
        protected USAFieldedAddressInfo postalAddress;
        @XmlElement(name = "LocationProperties")
        protected LocationProperties locationProperties;

        public USAFieldedAddressInfo getPostalAddress() {
            return postalAddress;
        }

        public void setPostalAddress(USAFieldedAddressInfo postalAddress) {
            this.postalAddress = postalAddress;
        }

        public LocationProperties getLocationProperties() {
            return locationProperties;
        }

        public void setLocationProperties(LocationProperties locationProperties) {
            this.locationProperties = locationProperties;
        }

        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
                "addressMatchCode",
                "cityStatePostalCodeValidType",
                "addressType",
                "addressMatchCodeDescription",
                "addressMatchCodeStatus",
                "cassRecordType",
                "cassAssignmentType",
                "deliveryPointValidationCode",
                "coordinates",
                "lacsDetail",
                "postalSupplementalData",
                "additionalData"
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
            @XmlElement(name = "Coordinates")
            protected AddressGeocodeCoordinatesInfo coordinates;
            @XmlElement(name = "LACSDetail")
            protected LACSDetail lacsDetail;
            @XmlElement(name = "PostalSupplementalData")
            protected PostalSupplementalData postalSupplementalData;
            @XmlElement(name = "AdditionalData")
            protected PostalLocationAttributesAdditionalDataInfo additionalData;

            public String getAddressMatchCode() {
                return addressMatchCode;
            }

            public void setAddressMatchCode(String addressMatchCode) {
                this.addressMatchCode = addressMatchCode;
            }

            public String getCityStatePostalCodeValidType() {
                return cityStatePostalCodeValidType;
            }

            public void setCityStatePostalCodeValidType(String cityStatePostalCodeValidType) {
                this.cityStatePostalCodeValidType = cityStatePostalCodeValidType;
            }

            public String getAddressType() {
                return addressType;
            }

            public void setAddressType(String addressType) {
                this.addressType = addressType;
            }

            public String getAddressMatchCodeDescription() {
                return addressMatchCodeDescription;
            }

            public void setAddressMatchCodeDescription(String addressMatchCodeDescription) {
                this.addressMatchCodeDescription = addressMatchCodeDescription;
            }

            public String getAddressMatchCodeStatus() {
                return addressMatchCodeStatus;
            }

            public void setAddressMatchCodeStatus(String addressMatchCodeStatus) {
                this.addressMatchCodeStatus = addressMatchCodeStatus;
            }

            public String getCassRecordType() {
                return cassRecordType;
            }

            public void setCassRecordType(String cassRecordType) {
                this.cassRecordType = cassRecordType;
            }

            public String getCassAssignmentType() {
                return cassAssignmentType;
            }

            public void setCassAssignmentType(String cassAssignmentType) {
                this.cassAssignmentType = cassAssignmentType;
            }

            public String getDeliveryPointValidationCode() {
                return deliveryPointValidationCode;
            }

            public void setDeliveryPointValidationCode(String deliveryPointValidationCode) {
                this.deliveryPointValidationCode = deliveryPointValidationCode;
            }

            public AddressGeocodeCoordinatesInfo getCoordinates() {
                return coordinates;
            }

            public void setCoordinates(AddressGeocodeCoordinatesInfo coordinates) {
                this.coordinates = coordinates;
            }

            public LACSDetail getLacsDetail() {
                return lacsDetail;
            }

            public void setLacsDetail(LACSDetail lacsDetail) {
                this.lacsDetail = lacsDetail;
            }

            public PostalSupplementalData getPostalSupplementalData() {
                return postalSupplementalData;
            }

            public void setPostalSupplementalData(PostalSupplementalData postalSupplementalData) {
                this.postalSupplementalData = postalSupplementalData;
            }

            public PostalLocationAttributesAdditionalDataInfo getAdditionalData() {
                return additionalData;
            }

            public void setAdditionalData(PostalLocationAttributesAdditionalDataInfo additionalData) {
                this.additionalData = additionalData;
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
            @XmlType(name = "", propOrder = {
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

                /**
                 * Gets the value of the businessName property.
                 *
                 * @return
                 *     possible object is
                 *     {@link String }
                 *
                 */
                public String getBusinessName() {
                    return businessName;
                }

                /**
                 * Sets the value of the businessName property.
                 *
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *
                 */
                public void setBusinessName(String value) {
                    this.businessName = value;
                }

                /**
                 * Gets the value of the unitDescription property.
                 *
                 * @return
                 *     possible object is
                 *     {@link String }
                 *
                 */
                public String getUnitDescription() {
                    return unitDescription;
                }

                /**
                 * Sets the value of the unitDescription property.
                 *
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *
                 */
                public void setUnitDescription(String value) {
                    this.unitDescription = value;
                }

                /**
                 * Gets the value of the unitNumberLow property.
                 *
                 * @return
                 *     possible object is
                 *     {@link String }
                 *
                 */
                public String getUnitNumberLow() {
                    return unitNumberLow;
                }

                /**
                 * Sets the value of the unitNumberLow property.
                 *
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *
                 */
                public void setUnitNumberLow(String value) {
                    this.unitNumberLow = value;
                }

                /**
                 * Gets the value of the unitNumberHigh property.
                 *
                 * @return
                 *     possible object is
                 *     {@link String }
                 *
                 */
                public String getUnitNumberHigh() {
                    return unitNumberHigh;
                }

                /**
                 * Sets the value of the unitNumberHigh property.
                 *
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *
                 */
                public void setUnitNumberHigh(String value) {
                    this.unitNumberHigh = value;
                }

            }
        }
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "timeZoneDetails",
            "daPsa",
            "suppressionIndicator",
            "baseAddressGlobalLocationId"
    })
    public static class AdditionalLocationAttributes {
        @XmlElement(name = "TimeZoneDetails")
        protected TimeZoneDetails timeZoneDetails;
        protected String daPsa;
        protected String suppressionIndicator;
        protected String baseAddressGlobalLocationId;

        public TimeZoneDetails getTimeZoneDetails() {
            return timeZoneDetails;
        }

        public void setTimeZoneDetails(TimeZoneDetails timeZoneDetails) {
            this.timeZoneDetails = timeZoneDetails;
        }

        public String getDaPsa() {
            return daPsa;
        }

        public void setDaPsa(String daPsa) {
            this.daPsa = daPsa;
        }

        public String getSuppressionIndicator() {
            return suppressionIndicator;
        }

        public void setSuppressionIndicator(String suppressionIndicator) {
            this.suppressionIndicator = suppressionIndicator;
        }

        public String getBaseAddressGlobalLocationId() {
            return baseAddressGlobalLocationId;
        }

        public void setBaseAddressGlobalLocationId(String baseAddressGlobalLocationId) {
            this.baseAddressGlobalLocationId = baseAddressGlobalLocationId;
        }

        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
                "abbreviatedTimeZone",
                "completeTimeZoneName",
                "observesDstFlag",
                "startDst",
                "endDst",
                "utcStd",
                "utcDst",
                "types",
                "timeZoneResponseType"
        })
        public static class TimeZoneDetails {
            protected String abbreviatedTimeZone;
            protected String completeTimeZoneName;
            protected BooleanWithUndefinedInfo observesDstFlag;
            protected String startDst;
            protected String endDst;
            protected String utcStd;
            protected String utcDst;
            @XmlElement(name = "Types")
            protected String types;
            protected String timeZoneResponseType;

            public String getAbbreviatedTimeZone() {
                return abbreviatedTimeZone;
            }

            public void setAbbreviatedTimeZone(String abbreviatedTimeZone) {
                this.abbreviatedTimeZone = abbreviatedTimeZone;
            }

            public String getCompleteTimeZoneName() {
                return completeTimeZoneName;
            }

            public void setCompleteTimeZoneName(String completeTimeZoneName) {
                this.completeTimeZoneName = completeTimeZoneName;
            }

            public BooleanWithUndefinedInfo getObservesDstFlag() {
                return observesDstFlag;
            }

            public void setObservesDstFlag(BooleanWithUndefinedInfo observesDstFlag) {
                this.observesDstFlag = observesDstFlag;
            }

            public String getStartDst() {
                return startDst;
            }

            public void setStartDst(String startDst) {
                this.startDst = startDst;
            }

            public String getEndDst() {
                return endDst;
            }

            public void setEndDst(String endDst) {
                this.endDst = endDst;
            }

            public String getUtcStd() {
                return utcStd;
            }

            public void setUtcStd(String utcStd) {
                this.utcStd = utcStd;
            }

            public String getUtcDst() {
                return utcDst;
            }

            public void setUtcDst(String utcDst) {
                this.utcDst = utcDst;
            }

            public String getTypes() {
                return types;
            }

            public void setTypes(String types) {
                this.types = types;
            }

            public String getTimeZoneResponseType() {
                return timeZoneResponseType;
            }

            public void setTimeZoneResponseType(String timeZoneResponseType) {
                this.timeZoneResponseType = timeZoneResponseType;
            }

            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = {
                    "force",
                    "ofsc",
                    "iana",
                    "gis"
            })
            public static class Types {
                protected String force;
                protected String ofsc;
                protected String iana;
                protected String gis;

                public String getForce() {
                    return force;
                }

                public void setForce(String force) {
                    this.force = force;
                }

                public String getOfsc() {
                    return ofsc;
                }

                public void setOfsc(String ofsc) {
                    this.ofsc = ofsc;
                }

                public String getIana() {
                    return iana;
                }

                public void setIana(String iana) {
                    this.iana = iana;
                }

                public String getGis() {
                    return gis;
                }

                public void setGis(String gis) {
                    this.gis = gis;
                }
            }
        }
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "status",
            "hostName",
            "matchStatus",
            "enterpriseGeocodingModuleStatus",
            "pageDetails"
    })
    public static class HostResponse {
        @XmlElement(name = "Status")
        protected ResponseInfo status;
        protected String hostName;
        protected String matchStatus;
        protected ResponseInfo enterpriseGeocodingModuleStatus;
        @XmlElement(name = "PageDetails")
        protected PageDetails pageDetails;

        public ResponseInfo getStatus() {
            return status;
        }

        public void setStatus(ResponseInfo status) {
            this.status = status;
        }

        public String getHostName() {
            return hostName;
        }

        public void setHostName(String hostName) {
            this.hostName = hostName;
        }

        public String getMatchStatus() {
            return matchStatus;
        }

        public void setMatchStatus(String matchStatus) {
            this.matchStatus = matchStatus;
        }

        public ResponseInfo getEnterpriseGeocodingModuleStatus() {
            return enterpriseGeocodingModuleStatus;
        }

        public void setEnterpriseGeocodingModuleStatus(ResponseInfo enterpriseGeocodingModuleStatus) {
            this.enterpriseGeocodingModuleStatus = enterpriseGeocodingModuleStatus;
        }

        public PageDetails getPageDetails() {
            return pageDetails;
        }

        public void setPageDetails(PageDetails pageDetails) {
            this.pageDetails = pageDetails;
        }

        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
                "totalNumberPages",
                "currentNumberPages",
                "firstValueOnThetList",
                "lastValueOnThetList"
        })
        public static class PageDetails {
            protected Integer totalNumberPages;
            protected Integer currentNumberPages;
            protected String firstValueOnThetList;
            protected String lastValueOnThetList;
        }
    }
}
