package com.att.lms.bis.service.ovals;

import com.att.lms.bis.service.ovals.model.pla.request.PlaRequest;
import com.att.lms.bis.service.ovals.model.pla.response.PlaResponse;

public interface OvalsPlaApi {
    PlaResponse processLocationAttributes(PlaRequest request) throws OvalsException;
}
