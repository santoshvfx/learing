//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.05.20 at 02:19:21 AM EDT 
//


package com.att.lms.bis.service.ovals.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ADSLNetworkLoopInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ADSLNetworkLoopInfo"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="loadCoilCount" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}short"&gt;
 *               &lt;totalDigits value="2"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="bridgeTapCount" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}short"&gt;
 *               &lt;totalDigits value="2"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="repeaterCount" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}short"&gt;
 *               &lt;totalDigits value="2"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="loopmediumcode" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;minLength value="0"/&gt;
 *               &lt;maxLength value="1"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="loopmediumcodeDistributionArea" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;minLength value="0"/&gt;
 *               &lt;maxLength value="1"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="remoteSwitchingIndicator" type="{http://csi.att.com/CSI/Namespaces/OVALSGISAddressValidationServiceQualUB/Types/Public/CommonDataModel.xsd}BooleanTrueInfo" minOccurs="0"/&gt;
 *         &lt;element name="loopProperties" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="taperCode"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;minLength value="0"/&gt;
 *                         &lt;maxLength value="50"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="servingTerminal"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;minLength value="0"/&gt;
 *                         &lt;maxLength value="50"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="f1Cable"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;minLength value="0"/&gt;
 *                         &lt;maxLength value="50"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="f1Pair"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;minLength value="0"/&gt;
 *                         &lt;maxLength value="50"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="connectedCommunity"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;minLength value="0"/&gt;
 *                         &lt;maxLength value="50"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ADSLNetworkLoopInfo", propOrder = {
    "loadCoilCount",
    "bridgeTapCount",
    "repeaterCount",
    "loopmediumcode",
    "loopmediumcodeDistributionArea",
    "remoteSwitchingIndicator",
    "loopProperties"
})
public class ADSLNetworkLoopInfo {

    protected Short loadCoilCount;
    protected Short bridgeTapCount;
    protected Short repeaterCount;
    protected String loopmediumcode;
    protected String loopmediumcodeDistributionArea;
    protected Boolean remoteSwitchingIndicator;
    protected LoopProperties loopProperties;

    /**
     * Gets the value of the loadCoilCount property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getLoadCoilCount() {
        return loadCoilCount;
    }

    /**
     * Sets the value of the loadCoilCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setLoadCoilCount(Short value) {
        this.loadCoilCount = value;
    }

    /**
     * Gets the value of the bridgeTapCount property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getBridgeTapCount() {
        return bridgeTapCount;
    }

    /**
     * Sets the value of the bridgeTapCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setBridgeTapCount(Short value) {
        this.bridgeTapCount = value;
    }

    /**
     * Gets the value of the repeaterCount property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getRepeaterCount() {
        return repeaterCount;
    }

    /**
     * Sets the value of the repeaterCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setRepeaterCount(Short value) {
        this.repeaterCount = value;
    }

    /**
     * Gets the value of the loopmediumcode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLoopmediumcode() {
        return loopmediumcode;
    }

    /**
     * Sets the value of the loopmediumcode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLoopmediumcode(String value) {
        this.loopmediumcode = value;
    }

    /**
     * Gets the value of the loopmediumcodeDistributionArea property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLoopmediumcodeDistributionArea() {
        return loopmediumcodeDistributionArea;
    }

    /**
     * Sets the value of the loopmediumcodeDistributionArea property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLoopmediumcodeDistributionArea(String value) {
        this.loopmediumcodeDistributionArea = value;
    }

    /**
     * Gets the value of the remoteSwitchingIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isRemoteSwitchingIndicator() {
        return remoteSwitchingIndicator;
    }

    /**
     * Sets the value of the remoteSwitchingIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setRemoteSwitchingIndicator(Boolean value) {
        this.remoteSwitchingIndicator = value;
    }

    /**
     * Gets the value of the loopProperties property.
     * 
     * @return
     *     possible object is
     *     {@link LoopProperties }
     *     
     */
    public LoopProperties getLoopProperties() {
        return loopProperties;
    }

    /**
     * Sets the value of the loopProperties property.
     * 
     * @param value
     *     allowed object is
     *     {@link LoopProperties }
     *     
     */
    public void setLoopProperties(LoopProperties value) {
        this.loopProperties = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="taperCode"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;minLength value="0"/&gt;
     *               &lt;maxLength value="50"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="servingTerminal"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;minLength value="0"/&gt;
     *               &lt;maxLength value="50"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="f1Cable"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;minLength value="0"/&gt;
     *               &lt;maxLength value="50"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="f1Pair"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;minLength value="0"/&gt;
     *               &lt;maxLength value="50"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="connectedCommunity"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;minLength value="0"/&gt;
     *               &lt;maxLength value="50"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "taperCode",
        "servingTerminal",
        "f1Cable",
        "f1Pair",
        "connectedCommunity"
    })
    public static class LoopProperties {

        @XmlElement(required = true)
        protected String taperCode;
        @XmlElement(required = true)
        protected String servingTerminal;
        @XmlElement(required = true)
        protected String f1Cable;
        @XmlElement(required = true)
        protected String f1Pair;
        @XmlElement(required = true)
        protected String connectedCommunity;

        /**
         * Gets the value of the taperCode property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTaperCode() {
            return taperCode;
        }

        /**
         * Sets the value of the taperCode property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTaperCode(String value) {
            this.taperCode = value;
        }

        /**
         * Gets the value of the servingTerminal property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getServingTerminal() {
            return servingTerminal;
        }

        /**
         * Sets the value of the servingTerminal property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setServingTerminal(String value) {
            this.servingTerminal = value;
        }

        /**
         * Gets the value of the f1Cable property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getF1Cable() {
            return f1Cable;
        }

        /**
         * Sets the value of the f1Cable property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setF1Cable(String value) {
            this.f1Cable = value;
        }

        /**
         * Gets the value of the f1Pair property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getF1Pair() {
            return f1Pair;
        }

        /**
         * Sets the value of the f1Pair property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setF1Pair(String value) {
            this.f1Pair = value;
        }

        /**
         * Gets the value of the connectedCommunity property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getConnectedCommunity() {
            return connectedCommunity;
        }

        /**
         * Sets the value of the connectedCommunity property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setConnectedCommunity(String value) {
            this.connectedCommunity = value;
        }

    }

}
