package com.att.lms.bis.service.ovals.model.pla.request;

import com.att.lms.bis.service.ovals.model.pavs.request.PavsRequest;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

@XmlRegistry
public class ObjectFactory {

    private final static QName _ProcessLocationAttributesRequest_QNAME = new QName("");

    public ObjectFactory() {
    }

    public PlaRequest createProcessLocationAttributesRequest() {
        return new PlaRequest();
    }

    public PlaRequest createProcessLocationAttributesRequestProcessLocationAttributesRequestInfo() {
        return new PlaRequest();
    }

    public PlaRequest.AddressInfoUniversal createProcessLocationAttributesRequestProcessLocationAttributesRequestInfoAddressInfoUniversal() {
        return new PlaRequest.AddressInfoUniversal();
    }

    public PlaRequest.AddressInfoUniversal.InternationalAttributes createProcessLocationAttributesRequestProcessLocationAttributesRequestInfoAddressInfoUniversalInternationalAttributes() {
        return new PlaRequest.AddressInfoUniversal.InternationalAttributes();
    }

    public PlaRequest.AddressInfoUniversal.USAAttributes createProcessLocationAttributesRequestProcessLocationAttributesRequestInfoAddressInfoUniversalUSAAttributes() {
        return new PlaRequest.AddressInfoUniversal.USAAttributes();
    }

    public PlaRequest.AddressInfoUniversal.USAAttributes.AddressIdentifier createProcessLocationAttributesRequestProcessLocationAttributesRequestInfoAddressInfoUniversalUSAAttributesAddressIdentifier() {
        return new PlaRequest.AddressInfoUniversal.USAAttributes.AddressIdentifier();
    }

    public PlaRequest.AddressInfoUniversal.USAAttributes.AddressIdentifier.USAUniversalAddressInfo createProcessLocationAttributesRequestProcessLocationAttributesRequestInfoAddressInfoUniversalUSAAttributesAddressIdentifierUSAUniversalAddressInfo() {
        return new PlaRequest.AddressInfoUniversal.USAAttributes.AddressIdentifier.USAUniversalAddressInfo();
    }

    public PlaRequest.AddressInfoUniversal.USAAttributes.AddressIdentifier.GeoCode createProcessLocationAttributesRequestProcessLocationAttributesRequestInfoAddressInfoUniversalUSAAttributesAddressIdentifierGeoCode() {
        return new PlaRequest.AddressInfoUniversal.USAAttributes.AddressIdentifier.GeoCode();
    }

    public PlaRequest.AddressInfoUniversal.USAAttributes.ServiceIdentifier createProcessLocationAttributesRequestProcessLocationAttributesRequestInfoAddressInfoUniversalUSAAttributesServiceIdentifier() {
        return new PlaRequest.AddressInfoUniversal.USAAttributes.ServiceIdentifier();
    }

    public PlaRequest.RequestFunctionType createProcessLocationAttributesRequestProcessLocationAttributesRequestInfoRequestFunctionType() {
        return new PlaRequest.RequestFunctionType();
    }

    public PlaRequest.RequestFunctionType.InternationalOptions createProcessLocationAttributesRequestProcessLocationAttributesRequestInfoRequestFunctionTypeInternationalOptions() {
        return new PlaRequest.RequestFunctionType.InternationalOptions();
    }

    public PlaRequest.RequestFunctionType.InternationalOptions.AddressTimeZone createProcessLocationAttributesRequestProcessLocationAttributesRequestInfoRequestFunctionTypeInternationalOptionsAddressTimeZone() {
        return new PlaRequest.RequestFunctionType.InternationalOptions.AddressTimeZone();
    }

    public PlaRequest.RequestFunctionType.InternationalOptions.AddressTimeZone.GeoCode createProcessLocationAttributesRequestProcessLocationAttributesRequestInfoRequestFunctionTypeInternationalOptionsAddressTimeZoneGeoCode() {
        return new PlaRequest.RequestFunctionType.InternationalOptions.AddressTimeZone.GeoCode();
    }

    public PlaRequest.RequestFunctionType.USAOptions createProcessLocationAttributesRequestProcessLocationAttributesRequestInfoRequestFunctionTypeUSAOptions() {
        return new PlaRequest.RequestFunctionType.USAOptions();
    }

    public PlaRequest.RequestFunctionType.USAOptions.GISValidationOptions createProcessLocationAttributesRequestProcessLocationAttributesRequestInfoRequestFunctionTypeUSAOptionsGISValidationOptions() {
        return new PlaRequest.RequestFunctionType.USAOptions.GISValidationOptions();
    }

    public PlaRequest.RequestFunctionType.USAOptions.GISValidationOptions.DTVDetails createProcessLocationAttributesRequestProcessLocationAttributesRequestInfoRequestFunctionTypeUSAOptionsGISValidationOptionsDTVDetails() {
        return new PlaRequest.RequestFunctionType.USAOptions.GISValidationOptions.DTVDetails();
    }

    public PlaRequest.RequestFunctionType.USAOptions.GISValidationOptions.DTVDetails.ValidationOptions createProcessLocationAttributesRequestProcessLocationAttributesRequestInfoRequestFunctionTypeUSAOptionsGISValidationOptionsDTVDetailsValidationOptions() {
        return new PlaRequest.RequestFunctionType.USAOptions.GISValidationOptions.DTVDetails.ValidationOptions();
    }

    public PlaRequest.RequestFunctionType.USAOptions.GISValidationOptions.DTVDetails.BuildingDetails createProcessLocationAttributesRequestProcessLocationAttributesRequestInfoRequestFunctionTypeUSAOptionsGISValidationOptionsDTVDetailsBuildingDetails() {
        return new PlaRequest.RequestFunctionType.USAOptions.GISValidationOptions.DTVDetails.BuildingDetails();
    }

    public PlaRequest.RequestFunctionType.USAOptions.GISValidationOptions.DTVDetails.BuildingDetails.PropertySelection createProcessLocationAttributesRequestProcessLocationAttributesRequestInfoRequestFunctionTypeUSAOptionsGISValidationOptionsDTVDetailsBuildingDetailsPropertySelection() {
        return new PlaRequest.RequestFunctionType.USAOptions.GISValidationOptions.DTVDetails.BuildingDetails.PropertySelection();
    }

    public PlaRequest.RequestFunctionType.USAOptions.MSAGValidationOptions createProcessLocationAttributesRequestProcessLocationAttributesRequestInfoRequestFunctionTypeUSAOptionsMSAGValidationOptions() {
        return new PlaRequest.RequestFunctionType.USAOptions.MSAGValidationOptions();
    }

    public PlaRequest.RequestFunctionType.USAOptions.SAGValidationOptions createProcessLocationAttributesRequestProcessLocationAttributesRequestInfoRequestFunctionTypeUSAOptionsSAGValidationOptions() {
        return new PlaRequest.RequestFunctionType.USAOptions.SAGValidationOptions();
    }

    public PlaRequest.RequestFunctionType.USAOptions.WLLValidationOptions createProcessLocationAttributesRequestProcessLocationAttributesRequestInfoRequestFunctionTypeUSAOptionsWLLValidationOptions() {
        return new PlaRequest.RequestFunctionType.USAOptions.WLLValidationOptions();
    }

    public PlaRequest.RequestFunctionType.USAOptions.USPSDeliveryPointValidationOptions createProcessLocationAttributesRequestProcessLocationAttributesRequestInfoRequestFunctionTypeUSAOptionsUSPSDeliveryPointValidationOptions() {
        return new PlaRequest.RequestFunctionType.USAOptions.USPSDeliveryPointValidationOptions();
    }

    @XmlElementDecl(namespace = "", name = "")
    public JAXBElement<PlaRequest> createProcessLocationAttributesRequest(PlaRequest value) {
        return new JAXBElement<PlaRequest>(_ProcessLocationAttributesRequest_QNAME, PlaRequest.class, null, value);
    }
}