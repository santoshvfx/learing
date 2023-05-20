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
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for FiberServiceProductQualificationInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FiberServiceProductQualificationInfo"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ProductType" type="{http://csi.att.com/CSI/Namespaces/OVALSGISAddressValidationServiceQualUB/Types/Public/CommonDataModel.xsd}FiberServiceProductQualStatusInfo"/&gt;
 *         &lt;element name="CustomerPremisesEquipment" type="{http://csi.att.com/CSI/Namespaces/OVALSGISAddressValidationServiceQualUB/Types/Public/CommonDataModel.xsd}CustomerPremisesEquipmentInfo" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="ProductCategory" type="{http://csi.att.com/CSI/Namespaces/OVALSGISAddressValidationServiceQualUB/Types/Public/CommonDataModel.xsd}FiberServiceProductCategoryInfo" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FiberServiceProductQualificationInfo", propOrder = {
    "productType",
    "customerPremisesEquipment",
    "productCategory"
})
public class FiberServiceProductQualificationInfo {

    @XmlElement(name = "ProductType", required = true)
    protected FiberServiceProductQualStatusInfo productType;
    @XmlElement(name = "CustomerPremisesEquipment")
    protected List<CustomerPremisesEquipmentInfo> customerPremisesEquipment;
    @XmlElement(name = "ProductCategory")
    protected List<FiberServiceProductCategoryInfo> productCategory;

    /**
     * Gets the value of the productType property.
     * 
     * @return
     *     possible object is
     *     {@link FiberServiceProductQualStatusInfo }
     *     
     */
    public FiberServiceProductQualStatusInfo getProductType() {
        return productType;
    }

    /**
     * Sets the value of the productType property.
     * 
     * @param value
     *     allowed object is
     *     {@link FiberServiceProductQualStatusInfo }
     *     
     */
    public void setProductType(FiberServiceProductQualStatusInfo value) {
        this.productType = value;
    }

    /**
     * Gets the value of the customerPremisesEquipment property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the customerPremisesEquipment property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCustomerPremisesEquipment().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CustomerPremisesEquipmentInfo }
     * 
     * 
     */
    public List<CustomerPremisesEquipmentInfo> getCustomerPremisesEquipment() {
        if (customerPremisesEquipment == null) {
            customerPremisesEquipment = new ArrayList<CustomerPremisesEquipmentInfo>();
        }
        return this.customerPremisesEquipment;
    }

    /**
     * Gets the value of the productCategory property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the productCategory property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProductCategory().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FiberServiceProductCategoryInfo }
     * 
     * 
     */
    public List<FiberServiceProductCategoryInfo> getProductCategory() {
        if (productCategory == null) {
            productCategory = new ArrayList<FiberServiceProductCategoryInfo>();
        }
        return this.productCategory;
    }

}