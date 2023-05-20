package com.att.lms.bis.service.ovals;

import com.att.lms.bis.service.ovals.model.pavs.request1.PostalAddressValidationServiceRequest;
import com.att.lms.bis.service.ovals.model.pavs.response1.PostalAddressValidationServiceResponse;

public interface OvalsPavApi {

//    PavsResponse retrieveLocationForPostalAddress(PavsRequest request) throws OvalsException;

	PostalAddressValidationServiceResponse retrieveLocationForPostalAddressNew(
			PostalAddressValidationServiceRequest request) throws OvalsException;
}
