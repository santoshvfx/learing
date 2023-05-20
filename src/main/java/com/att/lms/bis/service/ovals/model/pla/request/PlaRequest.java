package com.att.lms.bis.service.ovals.model.pla.request;

import com.att.lms.bis.service.ovals.model.AddressGeocodeCoordinatesInfo;
import com.att.lms.bis.service.ovals.model.InternationalAddressInfo;
import com.att.lms.bis.service.ovals.model.USAFieldedAddressInfo;
import com.att.lms.bis.service.ovals.model.USAUnfieldedAddressInfo;
import lombok.Data;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProcessLocationAttributesRequest", propOrder = {
//        "processLocationAttributesRequestInfo"
        "requestFunctionType",
        "addressInfoUniversal",
        "internationalReverseGeocode"
})
@XmlRootElement(name = "ProcessLocationAttributesRequest")
@Data
public class PlaRequest {

//    @XmlElement(name = "ProcessLocationAttributesRequestInfo")
//    protected ProcessLocationAttributesRequestInfo processLocationAttributesRequestInfo;
//
//    public ProcessLocationAttributesRequestInfo getProcessLocationAttributesRequestInfo() {
//        return processLocationAttributesRequestInfo;
//    }
//
//    public void setProcessLocationAttributesRequestInfo(ProcessLocationAttributesRequestInfo processLocationAttributesRequestInfo) {
//        this.processLocationAttributesRequestInfo = processLocationAttributesRequestInfo;
//    }

//    @XmlAccessorType(XmlAccessType.FIELD)
//    @XmlType(name = "", propOrder = {
//            "requestFunctionType",
//            "addressInfoUniversal",
//            "internationalReverseGeocode"
//    })
//    public static class ProcessLocationAttributesRequestInfo {
        @XmlElement(name = "RequestFunctionType")
        protected RequestFunctionType requestFunctionType;

        @XmlElement(name = "AddressInfoUniversal")
        protected AddressInfoUniversal addressInfoUniversal;

        @XmlElement(name = "InternationalReverseGeocode")
        protected InternationalReverseGeocode internationalReverseGeocode;

        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
                "usaOptions",
                "internationalOptions"
        })
        @Data
        public static class RequestFunctionType {
            @XmlElement(name = "USAOptions")
            protected USAOptions usaOptions;

            @XmlElement(name = "InternationalOptions")
            protected InternationalOptions internationalOptions;

            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = {
                    "sagValidationOptions",
                    "gisValidationOptions",
                    "msagValidationOptions",
                    "wllValidationOptions",
                    "uspsDeliveryPointValidationOptions",
                    "glidLookup",
                    "servingAreaIndicator",
                    "externalLECIndicator",
                    "taxationGeocodeIndicator",
                    "pageNumber"
            })
            @Data
            public static class USAOptions {
                @XmlElement(name = "SAGValidationOptions")
                protected SAGValidationOptions sagValidationOptions;

                @XmlElement(name = "GISValidationOptions")
                protected GISValidationOptions gisValidationOptions;

                @XmlElement(name = "MSAGValidationOptions")
                protected MSAGValidationOptions msagValidationOptions;

                @XmlElement(name = "WLLValidationOptions")
                protected WLLValidationOptions wllValidationOptions;

                @XmlElement(name = "USPSDeliveryPointValidationOptions")
                protected USPSDeliveryPointValidationOptions uspsDeliveryPointValidationOptions;

                @XmlElement(name = "glidLookup")
                protected String glidLookup;

                @XmlElement(name = "servingAreaIndicator", namespace = "http://csi.att.com/CSI/Namespaces/OneViewAddressLocationSystem/Types/Public/CommonDataModel.xsd")
                protected Boolean servingAreaIndicator;

                @XmlElement(name = "externalLECIndicator", namespace = "http://csi.att.com/CSI/Namespaces/OneViewAddressLocationSystem/Types/Public/CommonDataModel.xsd")
                protected Boolean externalLECIndicator;

                @XmlElement(name = "taxationGeocodeIndicator", namespace = "http://csi.att.com/CSI/Namespaces/OneViewAddressLocationSystem/Types/Public/CommonDataModel.xsd")
                protected Boolean taxationGeocodeIndicator;

                @XmlElement(name = "pageNumber")
                protected Integer pageNumber;

                @XmlAccessorType(XmlAccessType.FIELD)
                @XmlType(name = "", propOrder = {
                        "validationIndicator",
                        "fieldedUnfieldedFlag",
                        "returnSupplementalsIndicator",
                        "nearbyTypes",
                        "maxAlternativeReturn",
                        "maxSupplementalReturn",
                        "maxRangeReturn",
                        "primaryNpanxx",
                        "networkQualify",
                        "maxTNsPerLivingUnit",
                        "maxCircuitIdsPerLivingUnit",
                        "maxLivingUnitsPerCircuitId",
                        "maxLivingUnitsPerTN"
                })
                @Data
                public static class SAGValidationOptions {
                    protected Boolean validationIndicator;
                    protected String fieldedUnfieldedFlag;
                    @XmlElement(namespace = "http://csi.att.com/CSI/Namespaces/OneViewAddressLocationSystem/Types/Public/CommonDataModel.xsd")
                    protected Boolean returnSupplementalsIndicator;
                    protected String nearbyTypes;
                    protected Integer maxAlternativeReturn;
                    protected Integer maxSupplementalReturn;
                    protected Integer maxRangeReturn;
                    protected String primaryNpanxx;
                    @XmlElement(namespace = "http://csi.att.com/CSI/Namespaces/OneViewAddressLocationSystem/Types/Public/CommonDataModel.xsd")
                    protected String networkQualify;
                    protected Integer maxTNsPerLivingUnit;
                    protected Integer maxCircuitIdsPerLivingUnit;
                    protected Integer maxLivingUnitsPerCircuitId;
                    protected Integer maxLivingUnitsPerTN;
                }

                @XmlAccessorType(XmlAccessType.FIELD)
                @XmlType(name = "", propOrder = {
                  "validationIndicator",
                  "noAlternativesIndicator",
                  "fieldedUnfieldedFlag",
                  "byPassSelection",
                  "functionType",
                  "connectAmericaFundPhase2Indicator",
                  "overrideAddressIndicator",
                  "onlyGLIDIndicator",
                  "dtvDetails",
                  "returnSupplementalsIndicator",
                  "returnAlternatesIndicator",
                  "addressTimeZone"
                })
                @Data
                public static class GISValidationOptions {
                    protected Boolean validationIndicator;
                    @XmlElement(namespace = "http://csi.att.com/CSI/Namespaces/OneViewAddressLocationSystem/Types/Public/CommonDataModel.xsd")
                    protected Boolean noAlternativesIndicator;
                    protected String fieldedUnfieldedFlag;
                    @XmlElement(name = "ByPassSelection")
                    protected ByPassSelection byPassSelection;
                    protected String functionType;
                    @XmlElement(namespace = "http://csi.att.com/CSI/Namespaces/OneViewAddressLocationSystem/Types/Public/CommonDataModel.xsd")
                    protected Boolean connectAmericaFundPhase2Indicator;
                    @XmlElement(namespace = "http://csi.att.com/CSI/Namespaces/OneViewAddressLocationSystem/Types/Public/CommonDataModel.xsd")
                    protected Boolean overrideAddressIndicator;
                    @XmlElement(namespace = "http://csi.att.com/CSI/Namespaces/OneViewAddressLocationSystem/Types/Public/CommonDataModel.xsd")
                    protected Boolean onlyGLIDIndicator;
                    @XmlElement(name = "DTVDetails")
                    protected DTVDetails dtvDetails;
                    @XmlElement(namespace = "http://csi.att.com/CSI/Namespaces/OneViewAddressLocationSystem/Types/Public/CommonDataModel.xsd")
                    protected Boolean returnSupplementalsIndicator;
                    @XmlElement(namespace = "http://csi.att.com/CSI/Namespaces/OneViewAddressLocationSystem/Types/Public/CommonDataModel.xsd")
                    protected Boolean returnAlternatesIndicator;
                    @XmlElement(name = "AddressTimeZone")
                    protected AddressTimeZone addressTimeZone;

                    @XmlAccessorType(XmlAccessType.FIELD)
                    @XmlType(name = "", propOrder = {
                            "coreGISOnlyIndicator",
                            "telcoOnlyIndicator"
                    })
                    @Data
                    public static class ByPassSelection {
                        @XmlElement(namespace = "http://csi.att.com/CSI/Namespaces/OneViewAddressLocationSystem/Types/Public/CommonDataModel.xsd")
                        protected Boolean coreGISOnlyIndicator;
                        @XmlElement(namespace = "http://csi.att.com/CSI/Namespaces/OneViewAddressLocationSystem/Types/Public/CommonDataModel.xsd")
                        protected Boolean telcoOnlyIndicator;
                    }

                    @XmlAccessorType(XmlAccessType.FIELD)
                    @XmlType(name = "", propOrder = {
                            "validationOptions",
                            "buildingDetails"
                    })
                    @Data
                    public static class DTVDetails {
                        @XmlElement(name = "ValidationOptions")
                        protected ValidationOptions validationOptions;

                        @XmlElement(name = "BuildingDetails")
                        protected BuildingDetails buildingDetails;

                        @XmlAccessorType(XmlAccessType.FIELD)
                        @XmlType(name = "", propOrder = {
                                "dtvIndicator",
                                "checkServiceIndicator",
                                "exactMatchOnlyIndicator",
                                "d2LiteIndicator",
                                "supplementalCheckOverrideIndicator"
                        })
                        @Data
                        public static class ValidationOptions {
                            protected Boolean dtvIndicator;
                            protected Boolean checkServiceIndicator;
                            @XmlElement(namespace = "http://csi.att.com/CSI/Namespaces/OneViewAddressLocationSystem/Types/Public/CommonDataModel.xsd")
                            protected Boolean exactMatchOnlyIndicator;
                            @XmlElement(namespace = "http://csi.att.com/CSI/Namespaces/OneViewAddressLocationSystem/Types/Public/CommonDataModel.xsd")
                            protected Boolean d2LiteIndicator;
                            @XmlElement(namespace = "http://csi.att.com/CSI/Namespaces/OneViewAddressLocationSystem/Types/Public/CommonDataModel.xsd")
                            protected Boolean supplementalCheckOverrideIndicator;
                        }

                        @XmlAccessorType(XmlAccessType.FIELD)
                        @XmlType(name = "", propOrder = {
                                "propertyId",
                                "propertySelection"
                        })
                        @Data
                        public static class BuildingDetails {
                            @XmlElement(namespace = "http://csi.att.com/CSI/Namespaces/OneViewAddressLocationSystem/Types/Public/CommonDataModel.xsd")
                            protected String propertyId;
                            @XmlElement(name = "PropertySelection")
                            protected PropertySelection propertySelection;

                            @XmlAccessorType(XmlAccessType.FIELD)
                            @XmlType(name = "", propOrder = {
                                    "d2LiteInstallationNotesIndicator",
                                    "buildingToPropertyDetailIndicator"
                            })
                            @Data
                            public static class PropertySelection {
                                @XmlElement(namespace = "http://csi.att.com/CSI/Namespaces/OneViewAddressLocationSystem/Types/Public/CommonDataModel.xsd")
                                protected Boolean d2LiteInstallationNotesIndicator;
                                @XmlElement(namespace = "http://csi.att.com/CSI/Namespaces/OneViewAddressLocationSystem/Types/Public/CommonDataModel.xsd")
                                protected Boolean buildingToPropertyDetailIndicator;
                            }
                        }
                    }

                    @XmlAccessorType(XmlAccessType.FIELD)
                    @XmlType(name = "", propOrder = {
                            "buildingCLLI",
                            "coordinates",
                            "addressIndicator",
                            "wireCenter"
                    })
                    @Data
                    public static class AddressTimeZone {
                        @XmlElement(namespace = "http://csi.att.com/CSI/Namespaces/OneViewAddressLocationSystem/Types/Public/CommonDataModel.xsd")
                        protected String buildingCLLI;
                        @XmlElement(name = "Coordinates", namespace = "http://csi.att.com/CSI/Namespaces/OneViewAddressLocationSystem/Types/Public/CommonDataModel.xsd")
                        protected AddressGeocodeCoordinatesInfo coordinates;
                        @XmlElement(namespace = "http://csi.att.com/CSI/Namespaces/OneViewAddressLocationSystem/Types/Public/CommonDataModel.xsd")
                        protected Boolean addressIndicator;
                        @XmlElement(namespace = "http://csi.att.com/CSI/Namespaces/OneViewAddressLocationSystem/Types/Public/CommonDataModel.xsd")
                        protected String wireCenter;
                    }
                }

                @XmlAccessorType(XmlAccessType.FIELD)
                @XmlType(name = "", propOrder = {
                        "validationIndicator",
                        "fieldedUnfieldedFlag",
                        "maxRangeReturn"
                })
                public static class MSAGValidationOptions {
                    protected Boolean validationIndicator;
                    protected String fieldedUnfieldedFlag;
                    protected Integer maxRangeReturn;

                    public Boolean getValidationIndicator() {
                        return validationIndicator;
                    }

                    public void setValidationIndicator(Boolean validationIndicator) {
                        this.validationIndicator = validationIndicator;
                    }

                    public String getFieldedUnfieldedFlag() {
                        return fieldedUnfieldedFlag;
                    }

                    public void setFieldedUnfieldedFlag(String fieldedUnfieldedFlag) {
                        this.fieldedUnfieldedFlag = fieldedUnfieldedFlag;
                    }

                    public Integer getMaxRangeReturn() {
                        return maxRangeReturn;
                    }

                    public void setMaxRangeReturn(Integer maxRangeReturn) {
                        this.maxRangeReturn = maxRangeReturn;
                    }
                }

                @XmlAccessorType(XmlAccessType.FIELD)
                @XmlType(name = "", propOrder = {
                        "lookupTnIndicator",
                        "tnType"
                })
                @Data
                public static class WLLValidationOptions {
                    protected Boolean lookupTnIndicator;
                    protected String tnType;
                }

                @XmlAccessorType(XmlAccessType.FIELD)
                @XmlType(name = "", propOrder = {
                        "maximumCASSAddressLineLength",
                        "maximumAlternativeAddresses"
                })
                @Data
                public static class USPSDeliveryPointValidationOptions {
                    @XmlElement(defaultValue = "40")
                    protected String maximumCASSAddressLineLength;
                    @XmlElement(defaultValue = "10")
                    protected Integer maximumAlternativeAddresses;
                }
            }

            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = {
                    "addressValidationIndicator",
                    "addressFormatIndicator",
                    "reverseGeocodeIndicator",
                    "glidLookup",
                    "ethernetServiceProviderIndicator",
                    "overrideAddressIndicator",
                    "addressTimeZone"
            })
            @Data
            public static class InternationalOptions {
                protected Boolean addressValidationIndicator;
                @XmlElement(namespace = "http://csi.att.com/CSI/Namespaces/OneViewAddressLocationSystem/Types/Public/CommonDataModel.xsd")
                protected Boolean addressFormatIndicator;
                @XmlElement(namespace = "http://csi.att.com/CSI/Namespaces/OneViewAddressLocationSystem/Types/Public/CommonDataModel.xsd")
                protected Boolean reverseGeocodeIndicator;
                protected String glidLookup;
                @XmlElement(namespace = "http://csi.att.com/CSI/Namespaces/OneViewAddressLocationSystem/Types/Public/CommonDataModel.xsd")
                protected Boolean ethernetServiceProviderIndicator;
                @XmlElement(namespace = "http://csi.att.com/CSI/Namespaces/OneViewAddressLocationSystem/Types/Public/CommonDataModel.xsd")
                protected Boolean overrideAddressIndicator;
                @XmlElement(name = "AddressTimeZone")
                protected AddressTimeZone addressTimeZone;

                @XmlAccessorType(XmlAccessType.FIELD)
                @XmlType(name = "", propOrder = {
                        "buildingCLLI",
                        "geoCode",
                        "addressIndicator"
                })
                @Data
                public static class AddressTimeZone {
                    @XmlElement(namespace = "http://csi.att.com/CSI/Namespaces/OneViewAddressLocationSystem/Types/Public/CommonDataModel.xsd")
                    protected String buildingCLLI;
                    @XmlElement(name = "GeoCode")
                    protected GeoCode geoCode;
                    @XmlElement(namespace = "http://csi.att.com/CSI/Namespaces/OneViewAddressLocationSystem/Types/Public/CommonDataModel.xsd")
                    protected Boolean addressIndicator;

                    @XmlAccessorType(XmlAccessType.FIELD)
                    @XmlType(name = "", propOrder = {
                            "country",
                            "coordinates"
                    })
                    @Data
                    public static class GeoCode {
                        protected String country;
                        @XmlElement(name = "Coordinates", namespace = "http://csi.att.com/CSI/Namespaces/OneViewAddressLocationSystem/Types/Public/CommonDataModel.xsd")
                        protected AddressGeocodeCoordinatesInfo coordinates;
                    }
                }
            }
        }

        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
                "internationalAttributes",
                "usaAttributes"
        })
        @Data
        public static class AddressInfoUniversal {
            @XmlElement(name = "InternationalAttributes")
            protected InternationalAttributes internationalAttributes;

            @XmlElement(name = "USAAttributes")
            protected USAAttributes usaAttributes;

            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = {
                    "internationalAddress",
                    "globalLocationId"
            })
            @Data
            public static class InternationalAttributes {
                @XmlElement(name = "InternationalAddress", namespace = "http://csi.att.com/CSI/Namespaces/OneViewAddressLocationSystem/Types/Public/CommonDataModel.xsd")
                protected InternationalAddressInfo internationalAddress;
                @XmlElement(namespace = "http://csi.att.com/CSI/Namespaces/OneViewAddressLocationSystem/Types/Public/CommonDataModel.xsd")
                protected String globalLocationId;

            }

            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = {
                    "addressIdentifier",
                    "serviceIdentifier"
            })
            @Data
            public static class USAAttributes {
                @XmlElement(name = "AddressIdentifier")
                protected AddressIdentifier addressIdentifier;
                @XmlElement(name = "ServiceIdentifier")
                protected ServiceIdentifier serviceIdentifier;


                @XmlAccessorType(XmlAccessType.FIELD)
                @XmlType(name = "", propOrder = {
                        "address",
                        "globalLocationId",
                        "geoCode",
                        "buildingClli"
                })
                @Data
                public static class AddressIdentifier {
                    @XmlElement(name = "Address")
                    protected USAUniversalAddressInfo address;
                    @XmlElement(namespace = "http://csi.att.com/CSI/Namespaces/OneViewAddressLocationSystem/Types/Public/CommonDataModel.xsd")
                    protected String globalLocationId;
                    @XmlElement(name = "GeoCode")
                    protected GeoCode geoCode;
                    @XmlElement(namespace = "http://csi.att.com/CSI/Namespaces/OneViewAddressLocationSystem/Types/Public/CommonDataModel.xsd")
                    protected String buildingClli;

                    @XmlAccessorType(XmlAccessType.FIELD)
                    @XmlType(name = "", propOrder = {
                            "unfielded",
                            "fielded"
                    })
                    @Data
                    public static class USAUniversalAddressInfo {
                        @XmlElement(name = "Unfielded", namespace = "http://csi.att.com/CSI/Namespaces/OneViewAddressLocationSystem/Types/Public/CommonDataModel.xsd")
                        protected USAUnfieldedAddressInfo unfielded;
                        @XmlElement(name = "Fielded", namespace = "http://csi.att.com/CSI/Namespaces/OneViewAddressLocationSystem/Types/Public/CommonDataModel.xsd")
                        protected USAFieldedAddressInfo fielded;
                    }

                    @XmlAccessorType(XmlAccessType.FIELD)
                    @XmlType(name = "", propOrder = {
                            "geocodeFlag",
                            "coordinates"
                    })
                    @Data
                    public static class GeoCode {
                        protected String geocodeFlag;
                        @XmlElement(name = "Coordinates", namespace = "http://csi.att.com/CSI/Namespaces/OneViewAddressLocationSystem/Types/Public/CommonDataModel.xsd")
                        protected AddressGeocodeCoordinatesInfo coordinates;
                    }
                }

                @XmlAccessorType(XmlAccessType.FIELD)
                @XmlType(name = "", propOrder = {
                        "telephoneNumber",
                        "circuitId"
                })
                public static class ServiceIdentifier {
                    @XmlElement(namespace = "http://csi.att.com/CSI/Namespaces/OneViewAddressLocationSystem/Types/Public/CommonDataModel.xsd")
                    protected String telephoneNumber;
                    protected String circuitId;

                    public String getTelephoneNumber() {
                        return telephoneNumber;
                    }

                    public void setTelephoneNumber(String telephoneNumber) {
                        this.telephoneNumber = telephoneNumber;
                    }

                    public String getCircuitId() {
                        return circuitId;
                    }

                    public void setCircuitId(String circuitId) {
                        this.circuitId = circuitId;
                    }
                }
            }


        }

        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
                "country",
                "coordinates"
        })
        @Data
        public static class InternationalReverseGeocode {
            protected String country;
            @XmlElement(name = "Coordinates", namespace = "http://csi.att.com/CSI/Namespaces/OneViewAddressLocationSystem/Types/Public/CommonDataModel.xsd")
            protected AddressGeocodeCoordinatesInfo coordinates;
      }
}
