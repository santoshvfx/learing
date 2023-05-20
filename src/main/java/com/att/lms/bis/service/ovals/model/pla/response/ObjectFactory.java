package com.att.lms.bis.service.ovals.model.pla.response;

import com.att.lms.bis.service.ovals.model.pavs.response.PavsResponse;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

@XmlRegistry
public class ObjectFactory {

    private final static QName _ProcessAddressLocationResponse_QNAME = new QName("");

    public ObjectFactory() {
    }

    public PlaResponse createProcessAddressLocationResponse() { return new PlaResponse(); }

    public PlaResponse.HostResponse createProcessAddressLocationResponseHostResponse() {
        return new PlaResponse.HostResponse();
    }

    public PlaResponse.HostResponse.PageDetails createProcessAddressLocationResponseHostResponsePageDetails() {
        return new PlaResponse.HostResponse.PageDetails();
    }

    public PlaResponse.AdditionalLocationAttributes createProcessAddressLocationResponseAdditionalLocationAttributes() {
        return new PlaResponse.AdditionalLocationAttributes();
    }

    public PlaResponse.AdditionalLocationAttributes.TimeZoneDetails createProcessAddressLocationResponseAdditionalLocationAttributesTimeZoneDetails() {
        return new PlaResponse.AdditionalLocationAttributes.TimeZoneDetails();
    }

    public PlaResponse.AdditionalLocationAttributes.TimeZoneDetails.Types createProcessAddressLocationResponseAdditionalLocationAttributesTimeZoneDetailsTypes() {
        return new PlaResponse.AdditionalLocationAttributes.TimeZoneDetails.Types();
    }

    public PlaResponse.GISLocationAttributes createProcessAddressLocationResponseGISLocationAttributes() {
        return new PlaResponse.GISLocationAttributes();
    }

    public PlaResponse.GISLocationAttributes.LocationProperties createProcessAddressLocationResponseGISLocationAttributesLocationProperties() {
        return new PlaResponse.GISLocationAttributes.LocationProperties();
    }

    public PlaResponse.GISLocationAttributes.LocationProperties.LocalNetworkServices createProcessAddressLocationResponseGISLocationAttributesLocationPropertiesLocalNetworkServices() {
        return new PlaResponse.GISLocationAttributes.LocationProperties.LocalNetworkServices();
    }

    public PlaResponse.GISLocationAttributes.LocationProperties.RateCenter createProcessAddressLocationResponseGISLocationAttributesLocationPropertiesRateCenter() {
        return new PlaResponse.GISLocationAttributes.LocationProperties.RateCenter();
    }

    public PlaResponse.GISLocationAttributes.DTVDetails createProcessAddressLocationResponseGISLocationAttributesDTVDetails() {
        return new PlaResponse.GISLocationAttributes.DTVDetails();
    }

    public PlaResponse.GISLocationAttributes.DTVDetails.BuildingDetails createProcessAddressLocationResponseGISLocationAttributesDTVDetailsBuildingDetails() {
        return new PlaResponse.GISLocationAttributes.DTVDetails.BuildingDetails();
    }

    public PlaResponse.GISLocationAttributes.DTVDetails.BuildingDetails.Building createProcessAddressLocationResponseGISLocationAttributesDTVDetailsBuildingDetailsBuilding() {
        return new PlaResponse.GISLocationAttributes.DTVDetails.BuildingDetails.Building();
    }

    public PlaResponse.GISLocationAttributes.DTVDetails.BuildingDetails.Building.BuildingAddresses createProcessAddressLocationResponseGISLocationAttributesDTVDetailsBuildingDetailsBuildingBuildingAddresses() {
        return new PlaResponse.GISLocationAttributes.DTVDetails.BuildingDetails.Building.BuildingAddresses();
    }

    public PlaResponse.GISLocationAttributes.DTVDetails.BuildingDetails.Building.UnitPropertyDetails createProcessAddressLocationResponseGISLocationAttributesDTVDetailsBuildingDetailsBuildingUnitPropertyDetails() {
        return new PlaResponse.GISLocationAttributes.DTVDetails.BuildingDetails.Building.UnitPropertyDetails();
    }

    public PlaResponse.GISLocationAttributes.DTVDetails.BuildingDetails.D2LiteinstallationNotes createProcessAddressLocationResponseGISLocationAttributesDTVDetailsBuildingDetailsD2LiteinstallationNotes() {
        return new PlaResponse.GISLocationAttributes.DTVDetails.BuildingDetails.D2LiteinstallationNotes();
    }

    public PlaResponse.GISLocationAttributes.DTVDetails.LocationProperties createProcessAddressLocationResponseGISLocationAttributesDTVDetailsLocationProperties() {
        return new PlaResponse.GISLocationAttributes.DTVDetails.LocationProperties();
    }

    public PlaResponse.GISLocationAttributes.DTVDetails.LocationProperties.Census createProcessAddressLocationResponseGISLocationAttributesDTVDetailsLocationPropertiesCensus() {
        return new PlaResponse.GISLocationAttributes.DTVDetails.LocationProperties.Census();
    }

    public PlaResponse.USPSDeliveryPointValidationAttributes createProcessAddressLocationResponseUSPSDeliveryPointValidationAttributes() {
        return new PlaResponse.USPSDeliveryPointValidationAttributes();
    }

    public PlaResponse.USPSDeliveryPointValidationAttributes.LocationProperties createProcessAddressLocationResponseUSPSDeliveryPointValidationAttributesLocationProperties() {
        return new PlaResponse.USPSDeliveryPointValidationAttributes.LocationProperties();
    }

    public PlaResponse.USPSDeliveryPointValidationAttributes.LocationProperties.LACSDetail createProcessAddressLocationResponseUSPSDeliveryPointValidationAttributesLocationPropertiesLACSDetail() {
        return new PlaResponse.USPSDeliveryPointValidationAttributes.LocationProperties.LACSDetail();
    }

    public PlaResponse.USPSDeliveryPointValidationAttributes.LocationProperties.PostalSupplementalData createProcessAddressLocationResponseUSPSDeliveryPointValidationAttributesLocationPropertiesPostalSupplementalData() {
        return new PlaResponse.USPSDeliveryPointValidationAttributes.LocationProperties.PostalSupplementalData();
    }

    public PlaResponse.LECLocationAttributes createProcessAddressLocationResponseLECLocationAttributes() {
        return new PlaResponse.LECLocationAttributes();
    }

    public PlaResponse.InternationalLocationAttributes createProcessAddressLocationResponseInternationalLocationAttributes() {
        return new PlaResponse.InternationalLocationAttributes();
    }

    public PlaResponse.InternationalLocationAttributes.AddressAttributes createProcessAddressLocationResponseInternationalLocationAttributesAddressAttributes() {
        return new PlaResponse.InternationalLocationAttributes.AddressAttributes();
    }

    public PlaResponse.InternationalLocationAttributes.AddressAttributes.AdministrativeAreaBoundariesLayers createProcessAddressLocationResponseInternationalLocationAttributesAddressAttributesAdministrativeAreaBoundariesLayer() {
        return new PlaResponse.InternationalLocationAttributes.AddressAttributes.AdministrativeAreaBoundariesLayers();
    }

    public PlaResponse.InternationalLocationAttributes.AddressAttributes.AdministrativeAreaBoundariesLayers.NewVersion createProcessAddressLocationResponseInternationalLocationAttributesAddressAttributesAdministrativeAreaBoundariesLayerNewVersion() {
        return new PlaResponse.InternationalLocationAttributes.AddressAttributes.AdministrativeAreaBoundariesLayers.NewVersion();
    }

    public PlaResponse.InternationalLocationAttributes.AddressAttributes.AdministrativeAreaBoundariesLayers.OldVersion createProcessAddressLocationResponseInternationalLocationAttributesAddressAttributesAdministrativeAreaBoundariesLayerOldVersion() {
        return new PlaResponse.InternationalLocationAttributes.AddressAttributes.AdministrativeAreaBoundariesLayers.OldVersion();
    }

    public PlaResponse.InternationalLocationAttributes.EthernetServiceProviderCatalog createProcessAddressLocationResponseInternationalLocationAttributesEthernetServiceProviderCatalog() {
        return new PlaResponse.InternationalLocationAttributes.EthernetServiceProviderCatalog();
    }

    public PlaResponse.MSAGLocationAttributes createProcessAddressLocationResponseMSAGLocationAttributes() {
        return new PlaResponse.MSAGLocationAttributes();
    }

    public PlaResponse.MSAGLocationAttributes.MSAGProperties createProcessAddressLocationResponseMSAGLocationAttributesMSAGProperties() {
        return new PlaResponse.MSAGLocationAttributes.MSAGProperties();
    }

    public PlaResponse.SAGLocationAttributes.LocationProperties createProcessAddressLocationResponseSAGLocationAttributesLocationProperties() {
        return new PlaResponse.SAGLocationAttributes.LocationProperties();
    }

    public PlaResponse.SAGLocationAttributes.LocationProperties.VHCoordinates createProcessAddressLocationResponseSAGLocationAttributesLocationPropertiesVHCoordinates() {
        return new PlaResponse.SAGLocationAttributes.LocationProperties.VHCoordinates();
    }

    public PlaResponse.SAGLocationAttributes.Address createProcessAddressLocationResponseSAGLocationAttributesAddress() {
        return new PlaResponse.SAGLocationAttributes.Address();
    }

    public PlaResponse.SAGLocationAttributes.Address.DescriptiveAlternatives createProcessAddressLocationResponseSAGLocationAttributesAddressDescriptiveAlternatives() {
        return new PlaResponse.SAGLocationAttributes.Address.DescriptiveAlternatives();
    }

    public PlaResponse.SAGLocationAttributes.SAGProperties createProcessAddressLocationResponseSAGLocationAttributesSAGProperties() {
        return new PlaResponse.SAGLocationAttributes.SAGProperties();
    }

    public PlaResponse.SAGLocationAttributes.CircuitIdProperties createProcessAddressLocationResponseSAGLocationAttributesCircuitIdProperties() {
        return new PlaResponse.SAGLocationAttributes.CircuitIdProperties();
    }

    public PlaResponse.SAGLocationAttributes.TelephoneProperties createProcessAddressLocationResponseSAGLocationAttributesTelephoneProperties() {
        return new PlaResponse.SAGLocationAttributes.TelephoneProperties();
    }

    @XmlElementDecl(namespace = "", name = "")
    public JAXBElement<PlaResponse> createProcessAddressLocationResponse(PlaResponse value) {
        return new JAXBElement<PlaResponse>(_ProcessAddressLocationResponse_QNAME, PlaResponse.class, null, value);
    }

}


