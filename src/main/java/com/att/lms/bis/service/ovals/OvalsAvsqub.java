package com.att.lms.bis.service.ovals;

import com.att.lms.bis.service.ovals.model.avsqub.request.AvsqubRequest;
import com.att.lms.bis.service.ovals.model.avsqub.response.AvsqubResponse;
import com.sbc.eia.bis.lim.util.LIMBase;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;
import java.util.UUID;

/**
 * OVALS API for AVSQUB - AddressValidationServiceQualificationUnifiedBill.
 * This api replaces the GIS implementation, and performs the service calls
 */
public class OvalsAvsqub implements OvalsApi
{
    public static final String OVALS_AVSQUB_HOST = "OVALS_AVSQUB_HOST";
    public static final String OVALS_AVSQUB_URL = "OVALS_AVSQUB_URL";
    public static final String OVALS_AVSQUB_USER = "OVALS_AVSQUB_USER";
    public static final String OVALS_AVSQUB_PSWD = "OVALS_AVSQUB_PSWD";
    public static final String OVALS_AVSQUB_VERSION = "OVALS_AVSQUB_VERSION";

    private final LIMBase limBase;

    public OvalsAvsqub(LIMBase limBase)
    {
        if (limBase == null)
        {
            throw new IllegalArgumentException("LIMBase cannot be null.");
        }
        this.limBase = limBase;
    }

    public LIMBase getLimBase()
    {
        return limBase;
    }

    public AvsqubResponse requestAddressInfo(AvsqubRequest request) throws OvalsException
    {
        String host = (String) limBase.getPROPERTIES().get(OVALS_AVSQUB_HOST);
        String url = (String) limBase.getPROPERTIES().get(OVALS_AVSQUB_URL);

        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            addHeaders(headers);
            addAuth(headers);
            HttpEntity<AvsqubRequest> httpRequest = new HttpEntity<>(request,headers);
            ResponseEntity<AvsqubResponse> response =
                    restTemplate.exchange(host+url,HttpMethod.POST,httpRequest,AvsqubResponse.class);

            return parseResponse(response);
        }
        catch (Exception e)
        {
            throw new OvalsException(e);
        }
    }

    private void addHeaders(HttpHeaders http)
    {
        String version = (String) limBase.getPROPERTIES().get(OVALS_AVSQUB_VERSION);

        UUID messageId = UUID.randomUUID();
        http.add("X-ATT-Version",version);
        http.add("X-ATT-TimeToLive","239999");
        http.add("X-ATT-MessageId",messageId.toString());
        http.add("Content-type","application/json");
    }

    private void addAuth(HttpHeaders http)
    {
        String user = (String) limBase.getPROPERTIES().get(OVALS_AVSQUB_USER);
        String pwd = (String) limBase.getPROPERTIES().get(OVALS_AVSQUB_PSWD);
        http.setBasicAuth(user,pwd);
    }

    /**
     * Parse the response, looking for error information in the headers
     * @param response
     * @return
     * @throws OvalsException
     */
    private AvsqubResponse parseResponse(ResponseEntity<AvsqubResponse> response) throws OvalsException
    {
        Optional<OvalsException> opt = checkStatus(response.getStatusCode());
        if (opt.isPresent())
        {
            throw opt.get();
        }
        opt = checkHeaders(response.getHeaders());
        if (opt.isPresent())
        {
            throw opt.get();
        }
        return response.getBody();
    }

    private Optional<OvalsException> checkStatus(HttpStatus status)
    {
        OvalsException oe = null;
        switch (status)
        {
            default: oe = null;
        }
        return Optional.ofNullable(oe);
    }

    private Optional<OvalsException> checkHeaders(HttpHeaders headers)
    {
        OvalsException oe = null;
        for (String key : headers.keySet())
        {

        }
        return Optional.ofNullable(oe);
    }
}
