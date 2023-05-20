package com.att.lms.bis.service.ovals.model.pavs.response;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

@XmlRegistry
public class ObjectFactory {

    // TODO
    private final static QName _PostalAddressValidationServiceResponse_QNAME = new QName("", "");

    public ObjectFactory() {
    }

    public PavsResponse createPostalAddressValidationServiceResponse() {
        return new PavsResponse();
    }

    public PavsResponse.HostResponse createPostalAddressValidationServiceHostResponse() {
        return new PavsResponse.HostResponse();
    }

    public PavsResponse.HostResponse.Status createPostalAddressValidationServiceHostResponseStatus() {
        return new PavsResponse.HostResponse.Status();
    }

    public PavsResponse.USPSDeliveryPointValidationAttributes createPostalValidationServiceUspsDeliveryPointValidationAttributes() {
        return new PavsResponse.USPSDeliveryPointValidationAttributes();
    }

    public PavsResponse.USPSDeliveryPointValidationAttributes.LocationProperties createPostalValidationServiceUspsDeliveryPointValidationAttributesLocationProperties() {
        return new PavsResponse.USPSDeliveryPointValidationAttributes.LocationProperties();
    }

    public PavsResponse.USPSDeliveryPointValidationAttributes.LocationProperties.AdditionalData createPostalValidationServiceUspsDeliveryPointValidationAttributesLocationPropertiesAdditionalData() {
        return new PavsResponse.USPSDeliveryPointValidationAttributes.LocationProperties.AdditionalData();
    }

    public PavsResponse.USPSDeliveryPointValidationAttributes.LocationProperties.Coordinates createPostalValidationServiceUspsDeliveryPointValidationAttributesLocationPropertiesCoordinates() {
        return new PavsResponse.USPSDeliveryPointValidationAttributes.LocationProperties.Coordinates();
    }

    public PavsResponse.USPSDeliveryPointValidationAttributes.LocationProperties.LACSDetail createPostalValidationServiceUspsDeliveryPointValidationAttributesLocationPropertiesLACSDetail() {
        return new PavsResponse.USPSDeliveryPointValidationAttributes.LocationProperties.LACSDetail();
    }

    public PavsResponse.USPSDeliveryPointValidationAttributes.PostalAddress createPostalValidationServiceUspsDeliveryPointValidationAttributesPostalAddress() {
        return new PavsResponse.USPSDeliveryPointValidationAttributes.PostalAddress();
    }

    public PavsResponse.USPSDeliveryPointValidationAttributes.PostalAddress.AdditionalData createPostalValidationServiceUspsDeliveryPointValidationAttributesPostalAddressAdditionalData() {
        return new PavsResponse.USPSDeliveryPointValidationAttributes.PostalAddress.AdditionalData();
    }

    // TODO
    @XmlElementDecl(namespace = "", name = "")
    public JAXBElement<PavsResponse> createPostalAddressValidationServiceResponse(PavsResponse value) {
        return new JAXBElement<PavsResponse>(_PostalAddressValidationServiceResponse_QNAME, PavsResponse.class, null, value);
    }
}
