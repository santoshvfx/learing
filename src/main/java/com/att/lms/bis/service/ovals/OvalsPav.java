package com.att.lms.bis.service.ovals;

import com.att.lms.bis.service.ovals.model.pavs.request1.PostalAddressValidationServiceRequest;
import com.att.lms.bis.service.ovals.model.pavs.response1.PostalAddressValidationServiceResponse;


import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.io.StringWriter;
import java.util.Hashtable;
import java.util.Optional;
import java.util.UUID;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

public class OvalsPav implements OvalsPavApi{

    public static final String OVALS_PAV_HOST = "OVALS_PAV_HOST";
    public static final String OVALS_PAV_URL = "OVALS_PAV_URL";
    public static final String OVALS_PAV_USER = "OVALS_PAV_USER";
    public static final String OVALS_PAV_PSWD = "OVALS_PAV_PSWD";
    public static final String OVALS_PAV_VERSION = "OVALS_PAV_VERSION";
    
    private final Hashtable properties;
    private static boolean logOvalsMsgs = false;
    static {
    	String logOvals = System.getProperty("logOvalsMsgs");
        if (logOvals != null)
        	logOvalsMsgs = Boolean.valueOf(logOvals);
    }

    public OvalsPav(Hashtable properties) {
        if (properties == null) {
            throw new IllegalArgumentException("Hashtable cannot be null.");
        }
        this.properties = properties;
    }

 /*   public PavsResponse retrieveLocationForPostalAddress(PavsRequest request) throws OvalsException {
        String host = (String) properties.get(OVALS_PAV_HOST);
        String url = (String) properties.get(OVALS_PAV_URL);

        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            addHeaders(headers);
            addAuth(headers);
            HttpEntity<PavsRequest> httpRequest = new HttpEntity<>(request,headers);
            JAXBContext jaxbContext = JAXBContext.newInstance(PavsRequest.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            StringWriter sw = new StringWriter();
            jaxbMarshaller.marshal(request, sw);
            String xmlString = sw.toString();
            System.out.println("Ovals Request:" + xmlString);
            ResponseEntity<PavsResponse> response =
                    restTemplate.exchange(host+url, HttpMethod.POST,httpRequest, PavsResponse.class);

            JAXBContext jaxbContext1 = JAXBContext.newInstance(PavsResponse.class);
            Marshaller jaxbMarshaller1 = jaxbContext.createMarshaller();
            StringWriter sw1 = new StringWriter();
            jaxbMarshaller1.marshal(response, sw1);
            System.out.println("Ovals response:" + sw1.toString());
            
            return parseResponse(response);
        }
        catch (Exception e)
        {
            throw new OvalsException(e);
        }
    }
*/
    private void addHeaders(HttpHeaders http)
    {
        String version = (String) properties.get(OVALS_PAV_VERSION);

        UUID messageId = UUID.randomUUID();
        http.add("X-ATT-Version",version);
        http.add("X-ATT-TimeToLive","239999");
        http.add("X-ATT-MessageId","1234");
        http.add("Content-type","application/xml");
    }

    private void addAuth(HttpHeaders http)
    {
        String user = (String) properties.get(OVALS_PAV_USER);
        String pwd = (String) properties.get(OVALS_PAV_PSWD);
        http.setBasicAuth(user,pwd);
    }

 /*
    private PavsResponse parseResponse(ResponseEntity<PavsResponse> response) throws OvalsException
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
*/
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

	@Override
	public PostalAddressValidationServiceResponse retrieveLocationForPostalAddressNew(
			PostalAddressValidationServiceRequest request) throws OvalsException {
        String host = (String) properties.get(OVALS_PAV_HOST);
        String url = (String) properties.get(OVALS_PAV_URL);

        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            addHeaders(headers);
            addAuth(headers);
            HttpEntity<PostalAddressValidationServiceRequest> httpRequest = new HttpEntity<>(request, headers);
            
            if (logOvalsMsgs) {
	            JAXBContext jaxbContext = JAXBContext.newInstance(PostalAddressValidationServiceRequest.class);
	            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
	            StringWriter sw = new StringWriter();
	            jaxbMarshaller.marshal(request, sw);
	            String xmlString = sw.toString();
	            System.out.println("Ovals PostalAddressValidationRequest:" + xmlString);
            }
            
            ResponseEntity<PostalAddressValidationServiceResponse> response =
                    restTemplate.exchange(host+url, HttpMethod.POST,httpRequest, PostalAddressValidationServiceResponse.class);
           
            if (logOvalsMsgs) {
	            JAXBContext jaxbContext1 = JAXBContext.newInstance(PostalAddressValidationServiceResponse.class);
	            Marshaller jaxbMarshaller1 = jaxbContext1.createMarshaller();
	            StringWriter sw1 = new StringWriter();
	            jaxbMarshaller1.marshal(response.getBody(), sw1);
	            String xmlString2 = sw1.toString();
	            System.out.println("Ovals PostalAddressValidationResponse:" + xmlString2);
            }
            
            return parseResponseNew(response);
        }
        catch (Exception e)
        {
            throw new OvalsException(e);
        }
	}

	private PostalAddressValidationServiceResponse parseResponseNew(ResponseEntity<PostalAddressValidationServiceResponse> response)  throws OvalsException {
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
}
