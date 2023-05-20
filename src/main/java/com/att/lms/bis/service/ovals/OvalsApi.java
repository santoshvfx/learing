package com.att.lms.bis.service.ovals;

import com.att.lms.bis.service.ovals.model.avsqub.request.AvsqubRequest;
import com.att.lms.bis.service.ovals.model.avsqub.response.AvsqubResponse;
import com.att.lms.bis.service.ovals.model.pavs.request.PavsRequest;
import com.att.lms.bis.service.ovals.model.pavs.response.PavsResponse;

/**
 * OVALS API for AVSQUB - AddressValidationServiceQualificationUnifiedBill.
 * This api replaces the GIS implementation
 */
public interface OvalsApi
{
    AvsqubResponse requestAddressInfo(AvsqubRequest request) throws OvalsException;
}
