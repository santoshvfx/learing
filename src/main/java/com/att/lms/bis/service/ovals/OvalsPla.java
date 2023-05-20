package com.att.lms.bis.service.ovals;

import com.att.lms.bis.service.ovals.model.avsqub.request.AvsqubRequest;
import com.att.lms.bis.service.ovals.model.avsqub.response.AvsqubResponse;
import com.att.lms.bis.service.ovals.model.pla.request.MessageHeader;
import com.att.lms.bis.service.ovals.model.pla.request.PlaRequest;
import com.att.lms.bis.service.ovals.model.pla.response.PlaResponse;
import com.sbc.eia.bis.lim.util.LIMBase;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.soap.*;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.Hashtable;
import java.util.Optional;
import java.util.UUID;

public class OvalsPla implements OvalsPlaApi {

    public static final String OVALS_PLA_HOST = "OVALS_PLA_HOST";
    public static final String OVALS_PLA_URL = "OVALS_PLA_URL";
    public static final String OVALS_PLA_USER = "OVALS_PLA_USER";
    public static final String OVALS_PLA_PSWD = "OVALS_PLA_PSWD";
    public static final String OVALS_PLA_VERSION = "OVALS_PLA_VERSION";

    private final Hashtable properties;

    public OvalsPla(Hashtable properties)
    {
        if (properties == null)
        {
            throw new IllegalArgumentException("Properties cannot be null.");
        }
        this.properties = properties;
    }


    public PlaResponse processLocationAttributes(PlaRequest request) throws OvalsException
    {
        String host = (String) properties.get(OVALS_PLA_HOST);
        String url = (String) properties.get(OVALS_PLA_URL);

        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            addHeaders(headers);
            addAuth(headers);

            String soapMessage = createSoapMessage(request);

            HttpEntity<String> httpRequest = new HttpEntity<>(soapMessage,headers);
            ResponseEntity<String> response =
                    restTemplate.exchange(host+url, HttpMethod.POST,httpRequest,String.class);

            return parseResponse(response);
        }
        catch (Exception e)
        {
            throw new OvalsException(e);
        }
    }

    private String createSoapMessage(PlaRequest request) throws Exception {
        String soapBody = createSoapBody(request);
        String soapHeaders = createSoapHeaders();
        return "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
                    "\t<soapenv:Header>\n" + soapHeaders +
                    "\t</soapenv:Header>\n" +
                    "\t<soapenv:Body>\n" + soapBody +
                    "\t</soapenv:Body>\n" +
            "</soapenv:Envelope>";
    }

    private String createSoapHeaders() throws Exception {
        String user = (String) properties.get(OVALS_PLA_USER);
        String pwd = (String) properties.get(OVALS_PLA_PSWD);

        MessageHeader.SecurityMessageHeader securityMessageHeader = new MessageHeader.SecurityMessageHeader();
        securityMessageHeader.setUserName(user);
        securityMessageHeader.setUserPassword(pwd);

        MessageHeader.TrackingMessageHeader trackingMessageHeader = new MessageHeader.TrackingMessageHeader();
        trackingMessageHeader.setInfrastructureVersion(86);
        trackingMessageHeader.setApplicationName("OneViewAddressLocationSystem");
        trackingMessageHeader.setVersion(205);
        trackingMessageHeader.setMessageId("ORDER_QUAL.65d8238d-c0a8-4c81-11ee-c0ee65e32b0a");
        trackingMessageHeader.setOriginatorId("CingularCSI");
        trackingMessageHeader.setResponseTo("65d8238d-c0a8-4c81-11ee-c0ee65e32b0a");
        trackingMessageHeader.setConversationId("bestbuy");
        trackingMessageHeader.setDateTimeStamp("2018-02-05T07:02:09.112Z");

        MessageHeader.SequenceMessageHeader sequenceMessageHeader = new MessageHeader.SequenceMessageHeader();
        sequenceMessageHeader.setSequenceNumber(1);
        sequenceMessageHeader.setTotalInSequence(1);

        MessageHeader messageHeader = new MessageHeader();
        messageHeader.setSecurityMessageHeader(securityMessageHeader);
        messageHeader.setTrackingMessageHeader(trackingMessageHeader);
        messageHeader.setSequenceMessageHeader(sequenceMessageHeader);

        JAXBContext jaxbContext = JAXBContext.newInstance(MessageHeader.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        StringWriter writer = new StringWriter();
        marshaller.marshal(messageHeader,writer);
        return writer.toString();
    }


    private String createSoapBody(PlaRequest request) throws Exception {
        JAXBContext jaxbContext = JAXBContext.newInstance(PlaRequest.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        StringWriter writer = new StringWriter();
        marshaller.marshal(request,writer);
        return writer.toString();
    }

    private void addHeaders(HttpHeaders http)
    {
        String version = (String) properties.get(OVALS_PLA_VERSION);

        UUID messageId = UUID.randomUUID();
        http.add("X-ATT-Version",version);
        http.add("X-ATT-TimeToLive","239999");
        http.add("X-ATT-MessageId",messageId.toString());
        http.add("Content-type","application/xml");
    }

    private void addAuth(HttpHeaders http)
    {
        String user = (String) properties.get(OVALS_PLA_USER);
        String pwd = (String) properties.get(OVALS_PLA_PSWD);
        http.setBasicAuth(user,pwd);
    }

    /**
     * Parse the response, looking for error information in the headers
     * @param response
     * @return
     * @throws OvalsException
     */
    private PlaResponse parseResponse(ResponseEntity<String> response) throws OvalsException
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

        String soapBody = response.getBody();
        String responseBody = soapBody.substring(soapBody.indexOf("<SOAP-ENV:Body>")+15,
                soapBody.indexOf("</SOAP-ENV:Body>"));

        PlaResponse plaResponse;

        try {
            JAXBContext jc = JAXBContext.newInstance(PlaResponse.class);
            Unmarshaller unmarshaller = jc.createUnmarshaller();
            InputStream stream = new ByteArrayInputStream(responseBody.getBytes(StandardCharsets.UTF_8));
            plaResponse = (PlaResponse)unmarshaller.unmarshal(stream);
        } catch (Exception e) {
            throw new OvalsException(e);
        }

        return plaResponse;
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
