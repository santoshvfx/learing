package com.att.lms.bis.service.ovals.model.pavs.request;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

@XmlRegistry
public class ObjectFactory {

    // TODO
    private final static QName _PostalAddressValidationServiceRequest_QNAME = new QName("");

    public ObjectFactory() {
    }

    public PavsRequest createPostalAddressValidationServiceRequest() {
        return new PavsRequest();
    }

    public PavsRequest.Address createPostalAddressValidationServiceRequestAddress() {
        return new PavsRequest.Address();
    }

    public PavsRequest.Address.Unfielded createPostalAddressValidationServiceRequestAddressUnfielded() {
        return new PavsRequest.Address.Unfielded();
    }

    public PavsRequest.Address.Unfielded.AdditionalData createPostalAddressValidationServiceRequestAddressUnfieldedAdditionalData() {
        return new PavsRequest.Address.Unfielded.AdditionalData();
    }

    public PavsRequest.Address.Fielded createPostalAddressValidationServiceRequestAddressFielded() {
        return new PavsRequest.Address.Fielded();
    }

    public PavsRequest.Address.Fielded.AdditionalData createPostalAddressValidationServiceRequestAddressFieldedAdditionalData() {
        return new PavsRequest.Address.Fielded.AdditionalData();
    }

    // TODO
    @XmlElementDecl(namespace = "", name = "")
    public JAXBElement<PavsRequest> createPostalAddressValidationServiceRequest(PavsRequest value) {
        return new JAXBElement<PavsRequest>(_PostalAddressValidationServiceRequest_QNAME, PavsRequest.class, null, value);
    }
}
