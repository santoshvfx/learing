package com.att.lms.bis.service.ovals.model.pla.request;

import lombok.*;
import org.apache.axis2.saaj.SOAPElementImpl;

import javax.xml.bind.annotation.*;
import javax.xml.soap.SOAPElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MessageHeader", propOrder = {
        "trackingMessageHeader",
        "securityMessageHeader",
        "sequenceMessageHeader"
}, namespace = "http://csi.cingular.com/CSI/Namespaces/OneViewAddressLocationSystem/InfrastructureCommon/Types/Public/MessageHeader.xsd")
@XmlRootElement(name = "MessageHeader")
@Data
public class MessageHeader {

    @XmlElement(name = "TrackingMessageHeader")
    protected MessageHeader.TrackingMessageHeader trackingMessageHeader;

    @XmlElement(name = "SecurityMessageHeader")
    protected MessageHeader.SecurityMessageHeader securityMessageHeader;

    @XmlElement(name = "SequenceMessageHeader")
    protected MessageHeader.SequenceMessageHeader sequenceMessageHeader;

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "TrackingMessageHeader", propOrder = {
            "infrastructureVersion",
            "applicationName",
            "version",
            "messageId",
            "originatorId",
            "responseTo",
            "conversationId",
            "dateTimeStamp"
    }, namespace = "http://csi.cingular.com/CSI/Namespaces/OneViewAddressLocationSystem/InfrastructureCommon/Types/Public/MessageHeader.xsd")
    @Data
    public static class TrackingMessageHeader {
        @XmlElement(name = "infrastructureVersion")
        protected int infrastructureVersion;

        @XmlElement(name = "applicationName")
        protected String applicationName;

        @XmlElement(name = "version")
        protected int version;

        @XmlElement(name = "messageId")
        protected String messageId;

        @XmlElement(name = "originatorId")
        protected String originatorId;

        @XmlElement(name = "responseTo")
        protected String responseTo;

        @XmlElement(name = "conversationId")
        protected String conversationId;

        @XmlElement(name = "dateTimeStamp")
        protected String dateTimeStamp;
    }


    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "SecurityMessageHeader", propOrder = {
            "userName",
            "userPassword"
    }, namespace = "http://csi.cingular.com/CSI/Namespaces/OneViewAddressLocationSystem/InfrastructureCommon/Types/Public/MessageHeader.xsd")
    @Data
    public static class SecurityMessageHeader {
        @XmlElement(name = "userName")
        protected String userName;

        @XmlElement(name = "userPassword")
        protected String userPassword;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "SequenceMessageHeader", propOrder = {
            "sequenceNumber",
            "totalInSequence"
    }, namespace = "http://csi.cingular.com/CSI/Namespaces/OneViewAddressLocationSystem/InfrastructureCommon/Types/Public/MessageHeader.xsd")
    @Data
    public static class SequenceMessageHeader {
        @XmlElement(name = "sequenceNumber")
        protected int sequenceNumber;

        @XmlElement(name = "totalInSequence")
        protected int totalInSequence;
    }
}
