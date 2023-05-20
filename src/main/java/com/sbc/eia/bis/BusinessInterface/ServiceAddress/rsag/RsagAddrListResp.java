//$Id: RsagAddrListResp.java,v 1.15 2007/11/10 04:16:12 gg2712 Exp $
package com.sbc.eia.bis.BusinessInterface.ServiceAddress.rsag;

import com.bellsouth.cbs.order.ws.CbsBasicAddressV7;
import com.bellsouth.cbs.order.ws.CbsOrderServiceAddressV7;
import com.bellsouth.cbs.order.ws.CbsStatusMessageV7;
import com.sbc.bccs.idl.helpers.IDLUtil;
import com.sbc.eia.bis.lim.transactions.common.ServiceAddrAltResp;
import com.sbc.eia.bis.lim.transactions.common.ServiceLocationBuilder;
import com.sbc.eia.bis.lim.util.LIMBase;
import com.sbc.eia.idl.lim.helpers.AddressHandlerRSAG;
import com.sbc.eia.idl.lim_types.ServiceLocation;

/**
 * @author jd3462
 */
public class RsagAddrListResp extends ServiceAddrAltResp
{
    CbsOrderServiceAddressV7 cbsServiceAddress[] = null;
    CbsStatusMessageV7 cbsStatusMessage = null;
    RsagRetrieveLocReq retrieveLocReq = null;

    /**
     * RsagAddrListResp
     * @param utility
     * @param retrieveLocReq
     * @param cbsResponse
     */ 
    public RsagAddrListResp(LIMBase utility,
            CbsOrderServiceAddressV7[] m_cbsServiceAddress,
            CbsStatusMessageV7 m_cbsStatusMessage,
            RsagRetrieveLocReq m_retrieveLocReq)
    {
        super(utility);
        setMaxAddresses(m_retrieveLocReq.getMaxBasicAddressAlternatives());
        setMaxUnits(m_retrieveLocReq.getMaxLivingUnitAlternatives());
        cbsServiceAddress = m_cbsServiceAddress;
        cbsStatusMessage = m_cbsStatusMessage;
        retrieveLocReq = m_retrieveLocReq;
        buildAlternativeServiceAddresses();
    }
    
    /**
     * addToList
     * @param serviceLocation
     */
    protected void addToList (ServiceLocation serviceLocation)
    {
        addrList.add(serviceLocation);
    }
    
    /**
     * buildAlternativeServiceAddresses
     */
    protected void buildAlternativeServiceAddresses()
    {
        ServiceLocation serviceLocation = null;
        AddressHandlerRSAG ahr = null;
        AddressHandlerRSAG ahrPrev = null;
        boolean isBasicAddressNotNull = false;
        boolean isCmntyNotNull = false;
        boolean isGeoStreetNotNull = false;
        boolean isNotContractE = false;
        boolean isStructrNoNull = false;
        boolean isStructrUnknown = false;
        boolean isElevtnNoNull = false;
        boolean isElevtnUnknown = false;
        boolean isUntNoNull = false;
        boolean isUntUnknown = false;
        boolean addAddrToAltList = false;
        int maxAddr = 0;
        int addrListCnt = 0;
        int addrLUCnt = 0;
        String stName = "";
        
        if (cbsStatusMessage.getCode().equalsIgnoreCase("E901")
                || cbsStatusMessage.getCode().equalsIgnoreCase("E931"))
        {
            stName = retrieveLocReq.getAddr().getStName();
            isNotContractE = false;
        }
        else
        {
            isNotContractE = true;
        }

        for (int i=0; i < cbsServiceAddress.length; i++)
        {
            CbsBasicAddressV7 cbsBasicAddress = cbsServiceAddress[i].getBasicAddr();
            
            isBasicAddressNotNull = false;
            isCmntyNotNull = false;
            isGeoStreetNotNull = false;
            isStructrNoNull = false;
            isStructrUnknown = false;
            isElevtnNoNull = false;
            isElevtnUnknown = false;
            isUntNoNull = false;
            isUntUnknown = false;
            
            if (cbsServiceAddress[i].getBasicAddr() != null)
            {
                isBasicAddressNotNull = true;
                if (cbsBasicAddress.getCmnty() != null)
                {
                    isCmntyNotNull = true;
                }
                if (cbsBasicAddress.getStructr() != null)
                {
                    isStructrNoNull = true;
                    if (cbsBasicAddress.getStructr().getStructureType() != null)
                    {
                        if (cbsBasicAddress.getStructr().getStructureType() == 0)
                        {
                            isStructrUnknown = true;
                        }
                    }
                }
                if (cbsBasicAddress.getElevtn() != null)
                {
                    isElevtnNoNull = true;
                    if (cbsBasicAddress.getElevtn().getElevationType() != null)
                    {
                        if (cbsBasicAddress.getElevtn().getElevationType() == 0)
                        {
                            isElevtnUnknown = true;
                        }
                    }
                }
                if (cbsBasicAddress.getUnt() != null)
                {
                    isUntNoNull = true;
                    if (cbsBasicAddress.getUnt().getUnitType() != null)
                    {
                        if (cbsBasicAddress.getUnt().getUnitType() == 0)
                        {
                            isUntUnknown = true;
                        }
                    }
                }
            }
            
            if (cbsServiceAddress[i].getGeoArea() != null &&
                    cbsServiceAddress[i].getGeoArea().getGeoStreet() != null)
            {
                isGeoStreetNotNull = true;
            }
            
            ahr = new AddressHandlerRSAG(
                    (isBasicAddressNotNull ? cbsBasicAddress.getRuralRoute() : ""),                  // aRoute
                    (isBasicAddressNotNull ? cbsBasicAddress.getRuralBox() : ""),                    // aBox,
                    "",                                                                         // aHouseNumberPrefix
                    (isBasicAddressNotNull ? cbsBasicAddress.getAddressNumber() : ""),               // aHouseNumber
                    (isBasicAddressNotNull ? cbsBasicAddress.getAssignedHouseNumber() : ""),         // aAssignedHouseNumber
                    "",                                                                         // aHouseNumberSuffix
                    (isGeoStreetNotNull ? cbsServiceAddress[i].getGeoArea().getGeoStreet().getPrefix() : ""),          // aStreetDirection
                    (isGeoStreetNotNull && isNotContractE ? cbsServiceAddress[i].getGeoArea().getGeoStreet().getName() : stName), // aStreetName
                    (isGeoStreetNotNull ? cbsServiceAddress[i].getGeoArea().getGeoStreet().getThoroughfare() : ""),    // aStreetThoroughfare
                    (isGeoStreetNotNull ? cbsServiceAddress[i].getGeoArea().getGeoStreet().getSuffix() : ""),          // aStreetNameSuffix
                    (isCmntyNotNull ? cbsServiceAddress[i].getBasicAddr().getCmnty().getName() : ""),                  // aCity
                    (isBasicAddressNotNull ? cbsBasicAddress.getState() : ""),                       // aState
                    (isBasicAddressNotNull ? cbsBasicAddress.getZipCode() : ""),                     // aPostalCode
                    (isBasicAddressNotNull ? cbsBasicAddress.getCounty() : ""),                      // aCounty
                    "",                                                                         // aCountry
                    ((isStructrNoNull && !isStructrUnknown) ? cbsBasicAddress.getStructr().getTypeDescription() : ""),    // aStructure Type
                    ((isStructrNoNull && !isStructrUnknown) ? cbsBasicAddress.getStructr().getData() : ""),               // aStructure Type
                    ((isElevtnNoNull && !isElevtnUnknown) ? cbsBasicAddress.getElevtn().getTypeDescription() : ""),       // aLevel Type
                    ((isElevtnNoNull && !isElevtnUnknown) ? cbsBasicAddress.getElevtn().getData() : ""),                  // aLevel Value
                    ((isUntNoNull && !isUntUnknown) ? cbsBasicAddress.getUnt().getTypeDescription() : ""),                // aUnit Type
                    ((isUntNoNull && !isUntUnknown) ? cbsBasicAddress.getUnt().getData() : ""),                           // aUnit Value
                    (isBasicAddressNotNull ? cbsBasicAddress.getDescriptiveAddress() : ""));        // aAdditionalAddressInformation
            
            if (!ahr.equalsAlternativeAddress(ahrPrev))
            {
                if (ahr.isAlternativeLU(ahrPrev))
                {
                    if (getMaxUnits() > 0)
                    {
                        addrLUCnt++;
                        if (addrLUCnt <= getMaxUnits())
                        {
                            addAddrToAltList = true;
                        }
                    }
                    else
                    {
                        addAddrToAltList = true;
                    }
                }
                else
                {
                    addAddrToAltList = true;
                    addrListCnt++;
                    addrLUCnt = 1;
                    if (getMaxAddresses() > 0 && addrListCnt > getMaxAddresses())
                    {
                        ahrPrev = null;
                        ahr = null;
                        break;
                    }
                }
                if (addAddrToAltList)
                {
                    serviceLocation = ServiceLocationBuilder.getDefaultServiceLocation();
                    serviceLocation.aServiceAddress = ahr.getAddress2_ForFieldedAddress();
                    serviceLocation.aAddressMatchCode = IDLUtil.toOpt(cbsStatusMessage.getCode());
                    serviceLocation.aAddressMatchCodeDescription = IDLUtil.toOpt(cbsStatusMessage.getText());
                    serviceLocation.aCrossBoundaryState = IDLUtil.toOpt(cbsBasicAddress.getCmnty().getCrossBoundaryStates()); 

                    addToList(serviceLocation);
                    addAddrToAltList = false;
                }
            }
            //Help on releasing the memory
            ahrPrev = null;
            ahrPrev = ahr;
            ahr = null;
        }
    }
}
