//$Id: RsagHitResp.java,v 1.8 2007/10/18 22:11:49 jh9785 Exp $

package com.sbc.eia.bis.BusinessInterface.ServiceAddress.rsag;

import com.bellsouth.cbs.order.ws.CbsOrderServiceAddressV7;
import com.bellsouth.cbs.order.ws.CbsStatusMessageV7;
import com.sbc.eia.bis.lim.transactions.common.RetrieveLocReq;
import com.sbc.eia.bis.lim.transactions.common.ServiceAddrMatchResp;
import com.sbc.eia.bis.lim.transactions.common.ServiceLocationBuilder;
import com.sbc.eia.bis.lim.util.LIMBase;
import com.sbc.eia.idl.lim.helpers.AddressHandlerRSAG;

/**
 * This class represents an address-match from RSAG.
 */
public class RsagHitResp extends ServiceAddrMatchResp
{
    RetrieveLocReq aRetrieveLocReq = null;
    CbsOrderServiceAddressV7 cbsServiceAddress = null;
    CbsStatusMessageV7 cbsStatusMessage = null;
    
    /**
     * Construct this object from CbsOrderAddressResponseV7 "hit" response.
     * @param utility LIMBase
     * @param retrieveLocReq RetrieveLocReq
     * @param cbsResponse CbsOrderAddressResponseV7
     */
 
    public RsagHitResp(LIMBase utility, 
 		   RetrieveLocReq retrieveLocReq, 
 		   CbsOrderServiceAddressV7 m_cbsServiceAddress,
 		   CbsStatusMessageV7 m_cbsStatusMessage)
	{
		super(utility);
		aRetrieveLocReq = retrieveLocReq;
		cbsServiceAddress = m_cbsServiceAddress;
		cbsStatusMessage = m_cbsStatusMessage;
		setServiceLocation();
	}

    
    /**
     * setServiceLocation
     * Construct and store an ServiceLocation from the RSag Hit data.
     */
    protected void setServiceLocation()
    {
        String temp_value1 = "";
        String temp_value2 = "";
        String temp_value3 = "";
        
        ServiceLocationBuilder serviceLocationBuilder = new ServiceLocationBuilder(aRetrieveLocReq.getLocationPropertiesRequested());
        
        serviceLocationBuilder.setServiceAddress(getServiceAddress().getAddress2_ForFieldedAddress());
        
        //Address Match Code
        serviceLocationBuilder.setAddressMatchCode(cbsStatusMessage.getCode());
        //Address Match Code Description
        serviceLocationBuilder.setAddressMatchCodeDescription(cbsStatusMessage.getText());     
        
        if (cbsServiceAddress.getMiscServiceAddressInfo() != null)
        {
            //Exchange Code
            serviceLocationBuilder.setExchangeCode(cbsServiceAddress.getMiscServiceAddressInfo().getTariffExchangeCode());
            //Tax Area Rate Code (TAR)
            serviceLocationBuilder.setTarCode(cbsServiceAddress.getMiscServiceAddressInfo().getTaxAreaCode());
            //Rate Zone
            serviceLocationBuilder.setRateZone(cbsServiceAddress.getMiscServiceAddressInfo().getRateZoneCode());
            //Independent Company Indicator
            serviceLocationBuilder.setIndependentCompanyIndicator(cbsServiceAddress.getMiscServiceAddressInfo().getTariffExchangeICOIndicator());
        }
        //Local Provider Serving Office Code (LSO)
        //Format NPA, TTA
        temp_value1 = (cbsServiceAddress.getNPA() == null ? "" : cbsServiceAddress.getNPA());
        temp_value2 = (cbsServiceAddress.getTTA() == null ? "" : cbsServiceAddress.getTTA());
        serviceLocationBuilder.setLocalProviderServingOfficeCode(temp_value1 + temp_value2);
        
        if (cbsServiceAddress.getBasicAddr() != null)
        {
            if (cbsServiceAddress.getBasicAddr().getPrimaryTN() != null &&
                cbsServiceAddress.getBasicAddr().getPrimaryTN().getTn() != null)
            {
                //Primary NPA-NXX
                temp_value1 = (cbsServiceAddress.getBasicAddr().getPrimaryTN().getTn().getNPA() == null ? "" : cbsServiceAddress.getBasicAddr().getPrimaryTN().getTn().getNPA());
                temp_value2 = (cbsServiceAddress.getBasicAddr().getPrimaryTN().getTn().getNXX() == null ? "" : cbsServiceAddress.getBasicAddr().getPrimaryTN().getTn().getNXX());
                serviceLocationBuilder.setPrimaryNpaNxx(temp_value1 + temp_value2);
            }
            
            if (cbsServiceAddress.getBasicAddr().getCmnty() != null)
            {
                //Cross Boundary State
                serviceLocationBuilder.setCrossBoundaryState(cbsServiceAddress.getBasicAddr().getCmnty().getCrossBoundaryStates());
            }
        }
             
        if (cbsServiceAddress.getNetworkFacilities() != null)
        {
            boolean wsopi = false;
            for (int i = 0; i < cbsServiceAddress.getNetworkFacilities().size(); i++)
            {   
                //Take the frist TN found that has an Status value equals to W
                if (cbsServiceAddress.getNetworkFacilities().get(i).getInstalledTN() != null &&
                    cbsServiceAddress.getNetworkFacilities().get(i).getInstalledTN().getStatus() != null &&
                    cbsServiceAddress.getNetworkFacilities().get(i).getInstalledTN().getStatus().equalsIgnoreCase("W"))
                {
                    //Telephone Number
                    if (cbsServiceAddress.getNetworkFacilities().get(i).getInstalledTN().getTn() != null)
                    {
                        temp_value1 = (cbsServiceAddress.getNetworkFacilities().get(i).getInstalledTN().getTn().getNPA() == null ? "" : cbsServiceAddress.getNetworkFacilities().get(i).getInstalledTN().getTn().getNPA());
                        temp_value2 = (cbsServiceAddress.getNetworkFacilities().get(i).getInstalledTN().getTn().getNXX() == null ? "" : cbsServiceAddress.getNetworkFacilities().get(i).getInstalledTN().getTn().getNXX());
                        temp_value3 = (cbsServiceAddress.getNetworkFacilities().get(i).getInstalledTN().getTn().getLINE() == null ? "" : cbsServiceAddress.getNetworkFacilities().get(i).getInstalledTN().getTn().getLINE());
                        serviceLocationBuilder.setTelephoneNumber(temp_value1 + temp_value2 + temp_value3);
                    }
                    
                    wsopi = true;
                    
                    //If the address has several TNs associated to it, LIM BIS will search for the TN that has a status of "W".  
                    //When LIM BIS finds this TN (with status W), it will get the quick dial tone and connect through information from this occurence.
                    //Connect Through
                    serviceLocationBuilder.setConnectThrough(cbsServiceAddress.getNetworkFacilities().get(i).getCT());
                    //Quick Dial Tone
                    serviceLocationBuilder.setQuickDialTone(cbsServiceAddress.getNetworkFacilities().get(i).getQuickServe());
                    //Quick Dial Tone Number
                    if (cbsServiceAddress.getNetworkFacilities().get(i).getQuickServe() != null &&
                        cbsServiceAddress.getNetworkFacilities().get(i).getQuickServe().equalsIgnoreCase("Y") &&
                        cbsServiceAddress.getNetworkFacilities().get(i).getInstalledTN() != null &&
                        cbsServiceAddress.getNetworkFacilities().get(i).getInstalledTN().getTn() != null)
                    {
                        temp_value1 = (cbsServiceAddress.getNetworkFacilities().get(i).getInstalledTN().getTn().getNPA() == null ? "" : cbsServiceAddress.getNetworkFacilities().get(i).getInstalledTN().getTn().getNPA());
                        temp_value2 = (cbsServiceAddress.getNetworkFacilities().get(i).getInstalledTN().getTn().getNXX() == null ? "" : cbsServiceAddress.getNetworkFacilities().get(i).getInstalledTN().getTn().getNXX());
                        temp_value3 = (cbsServiceAddress.getNetworkFacilities().get(i).getInstalledTN().getTn().getLINE() == null ? "" : cbsServiceAddress.getNetworkFacilities().get(i).getInstalledTN().getTn().getLINE());
                        serviceLocationBuilder.setQuickDialToneNumber(temp_value1 + temp_value2 + temp_value3);
                    }
                    
                    break;
                }
            }
            //Working Service On Location Indicator (WSOPI)
            if (wsopi)
            {
                serviceLocationBuilder.setWorkingServiceOnLocation("true");
            }
            else
            {
                //If the address has 1 or more TNs associated to it AND all TNs do not have a status of "W", 
                //LIM BIS will return the first occurence of the quick dial tone and connect through information, leave the TN blank and not set the WSOPI indicator.
                
                //Set WorkingServiceOnLocation (WSOPI) field and Working Telephone Number field to blank
                serviceLocationBuilder.setWorkingServiceOnLocation("");
                serviceLocationBuilder.setTelephoneNumber("");
                
                if (cbsServiceAddress.getNetworkFacilities().size() > 0)
                {
                    //Connect Through
                    serviceLocationBuilder.setConnectThrough(cbsServiceAddress.getNetworkFacilities().get(0).getCT());
                    //Quick Dial Tone
                    serviceLocationBuilder.setQuickDialTone(cbsServiceAddress.getNetworkFacilities().get(0).getQuickServe());
                    //Quick Dial Tone Number
                    if (cbsServiceAddress.getNetworkFacilities().get(0).getQuickServe() != null &&
                        cbsServiceAddress.getNetworkFacilities().get(0).getQuickServe().equalsIgnoreCase("Y") &&
                        cbsServiceAddress.getNetworkFacilities().get(0).getInstalledTN() != null &&
                        cbsServiceAddress.getNetworkFacilities().get(0).getInstalledTN().getTn() != null)
                    {
                        temp_value1 = (cbsServiceAddress.getNetworkFacilities().get(0).getInstalledTN().getTn().getNPA() == null ? "" : cbsServiceAddress.getNetworkFacilities().get(0).getInstalledTN().getTn().getNPA());
                        temp_value2 = (cbsServiceAddress.getNetworkFacilities().get(0).getInstalledTN().getTn().getNXX() == null ? "" : cbsServiceAddress.getNetworkFacilities().get(0).getInstalledTN().getTn().getNXX());
                        temp_value3 = (cbsServiceAddress.getNetworkFacilities().get(0).getInstalledTN().getTn().getLINE() == null ? "" : cbsServiceAddress.getNetworkFacilities().get(0).getInstalledTN().getTn().getLINE());
                        serviceLocationBuilder.setQuickDialToneNumber(temp_value1 + temp_value2 + temp_value3);
                    } 
                }
            }     
        }
        
        if (cbsServiceAddress.getTransferInfo() != null)
        {
            //Area Transfer Cut Date
            serviceLocationBuilder.setAreaTransferCutDate(cbsServiceAddress.getTransferInfo().getCutDate());
            //Area Transfer Number Change Date
            serviceLocationBuilder.setAreaTransferNumberChangeDate(cbsServiceAddress.getTransferInfo().getNumberChange());
            //Area Transfer NPA NXX
            serviceLocationBuilder.setAreaTransferNpaNxx(cbsServiceAddress.getTransferInfo().getNumberingInstruction());
            //Area Transfer Wire Center CLLI
            serviceLocationBuilder.setAreaTransferWireCenter(cbsServiceAddress.getTransferInfo().getNewCLLI());
        }
        
        if (cbsServiceAddress.getClli() != null)
        {
            //Wire Center - CLLI
            serviceLocationBuilder.setWireCenter(cbsServiceAddress.getClli().getCLLIFirst8());
        }
         
        //Set the serviceLocation
        serviceLocation = serviceLocationBuilder.getServiceLocation();
    }
    
    /**
     * getServiceAddress
     * Construct and store an AddressHandlerRSAG from the RSag Hit data.
     */
    protected AddressHandlerRSAG getServiceAddress()
    {
        boolean isBasicAddressNotNull = false;
        boolean isCmntyNotNull = false;
        boolean isStructrNoNull = false;
        boolean isStructrUnknown = false;
        boolean isElevtnNoNull = false;
        boolean isElevtnUnknown = false;
        boolean isUntNoNull = false;
        boolean isUntUnknown = false;
        boolean isGeoStreetNotNull = false;
        
        if (cbsServiceAddress.getBasicAddr() != null)
        {
            isBasicAddressNotNull = true;
            if (cbsServiceAddress.getBasicAddr().getCmnty() != null)
            {
                isCmntyNotNull = true;
            }
            if (cbsServiceAddress.getBasicAddr().getStructr() != null)
            {
                isStructrNoNull = true;
                if (cbsServiceAddress.getBasicAddr().getStructr().getStructureType() != null)
                {
                    if (cbsServiceAddress.getBasicAddr().getStructr().getStructureType() == 0)
                    {
                        isStructrUnknown = true;
                    }
                }
            }
            if (cbsServiceAddress.getBasicAddr().getElevtn() != null)
            {
                isElevtnNoNull = true;
                if (cbsServiceAddress.getBasicAddr().getElevtn().getElevationType() != null)
                {
                    if (cbsServiceAddress.getBasicAddr().getElevtn().getElevationType() == 0)
                    {
                        isElevtnUnknown = true;
                    }
                }
            }
            if (cbsServiceAddress.getBasicAddr().getUnt() != null)
            {
                isUntNoNull = true;
                if (cbsServiceAddress.getBasicAddr().getUnt().getUnitType() != null)
                {
                    if (cbsServiceAddress.getBasicAddr().getUnt().getUnitType() == 0)
                    {
                        isUntUnknown = true;
                    }
                }
            }
        }
        
        if (cbsServiceAddress.getGeoArea() != null &&
            cbsServiceAddress.getGeoArea().getGeoStreet() != null)
        {
            isGeoStreetNotNull = true;
        }
        
        return (new AddressHandlerRSAG(
                (isBasicAddressNotNull ? cbsServiceAddress.getBasicAddr().getRuralRoute() : ""),					// aRoute
                (isBasicAddressNotNull ? cbsServiceAddress.getBasicAddr().getRuralBox() : ""),					// aBox,
        		"",																						// aHouseNumberPrefix
        		(isBasicAddressNotNull ? cbsServiceAddress.getBasicAddr().getAddressNumber() : ""),				// aHouseNumber
        		(isBasicAddressNotNull ? cbsServiceAddress.getBasicAddr().getAssignedHouseNumber() : ""),			// aAssignedHouseNumber
        		"",    																					// aHouseNumberSuffix
        		(isGeoStreetNotNull ? cbsServiceAddress.getGeoArea().getGeoStreet().getPrefix() : ""),					// aStreetDirection
        		(isGeoStreetNotNull ? cbsServiceAddress.getGeoArea().getGeoStreet().getName() : ""),					// aStreetName
        		(isGeoStreetNotNull ? cbsServiceAddress.getGeoArea().getGeoStreet().getThoroughfare() : ""),			// aStreetThoroughfare
        		(isGeoStreetNotNull ? cbsServiceAddress.getGeoArea().getGeoStreet().getSuffix() : ""),					// aStreetNameSuffix
        		(isCmntyNotNull ? cbsServiceAddress.getBasicAddr().getCmnty().getName() : ""),							// aCity
        		(isBasicAddressNotNull ? cbsServiceAddress.getBasicAddr().getState() : ""),						// aState
        		(isBasicAddressNotNull ? cbsServiceAddress.getBasicAddr().getZipCode() : ""), 					// aPostalCode
        		(isBasicAddressNotNull ? cbsServiceAddress.getBasicAddr().getCounty() : ""),						// aCounty
        		"",																						// aCountry
        		((isStructrNoNull && !isStructrUnknown) ? cbsServiceAddress.getBasicAddr().getStructr().getTypeDescription() : ""),       	// aStructure Type
        		((isStructrNoNull && !isStructrUnknown) ? cbsServiceAddress.getBasicAddr().getStructr().getData() : ""),      				// aStructure Type
        		((isElevtnNoNull && !isElevtnUnknown) ? cbsServiceAddress.getBasicAddr().getElevtn().getTypeDescription() : ""),       	// aLevel Type
        		((isElevtnNoNull && !isElevtnUnknown) ? cbsServiceAddress.getBasicAddr().getElevtn().getData() : ""),       				// aLevel Value
        		((isUntNoNull && !isUntUnknown) ? cbsServiceAddress.getBasicAddr().getUnt().getTypeDescription() : ""),         			// aUnit Type
        		((isUntNoNull && !isUntUnknown) ? cbsServiceAddress.getBasicAddr().getUnt().getData() : ""),        						// aUnit Value
        		(isBasicAddressNotNull ? cbsServiceAddress.getBasicAddr().getDescriptiveAddress() : "")));        // aAdditionalAddressInformation
    }
}
