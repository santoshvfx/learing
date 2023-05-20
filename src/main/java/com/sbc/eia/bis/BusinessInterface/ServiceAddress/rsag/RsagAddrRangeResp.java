//$Id: RsagAddrRangeResp.java,v 1.8 2007/11/06 22:34:04 jh9785 Exp $

package com.sbc.eia.bis.BusinessInterface.ServiceAddress.rsag;

import com.sbc.eia.bis.lim.transactions.common.ServiceAddrRangeResp;
import com.bellsouth.cbs.order.ws.CbsOrderServiceAddressV7;
import com.bellsouth.cbs.order.ws.CbsStatusMessageV7;
import com.sbc.eia.bis.lim.util.LIMBase;
import com.sbc.eia.idl.lim.helpers.AddressHandlerRSAG;

/**
 * Build a range of addresses from RSag Response.
 */
public class RsagAddrRangeResp extends ServiceAddrRangeResp
{
    CbsOrderServiceAddressV7 cbsServiceAddress[] = null;
    CbsStatusMessageV7 cbsStatusMessage = null;
    
    /**
     * Construct this object from CbsOrderAddressResponseV7 "Range" response.
     * @param utility LIMBase
     * @param m_cbsServiceAddress CbsOrderServiceAddressV7[]
     * @param m_cbsStatusMessage CbsStatusMessageV6
     * @param m_maxBasicAddress int
     */
    public RsagAddrRangeResp(LIMBase utility, 
            				 CbsOrderServiceAddressV7[] m_cbsServiceAddress,
            				 CbsStatusMessageV7 m_cbsStatusMessage,
            				 int m_maxBasicAddress)
    {
        super(utility);
        setMaxAddresses(m_maxBasicAddress);
        cbsServiceAddress = m_cbsServiceAddress;
        cbsStatusMessage = m_cbsStatusMessage;
        addToList();
    }
    
    /**
     * Format RangeData and add to the address range list.
     */
    public void addToList()
    {
        isOldSBCRegionFlag = false;
        boolean isGeoStreetNotNull = false;
        boolean isBasicAddressNotNull = false;
        boolean isCommunityNotNull = false;
        boolean isMessagesNotNull = false;
        AddressHandlerRSAG previous_ahRsag = null;
       
        for (int i = 0; i < cbsServiceAddress.length; i++) 
        {
            isGeoStreetNotNull = false;
            isBasicAddressNotNull = false;
            isCommunityNotNull = false;
            isMessagesNotNull = false;
                
            AddressHandlerRSAG ahRSag = new AddressHandlerRSAG();
                
            if (cbsServiceAddress[i].getGeoArea() != null &&
                cbsServiceAddress[i].getGeoArea().getGeoStreet() != null)
            {
                isGeoStreetNotNull = true;
                
                //If HighHouseNumber and LowHouseNumber are empty, do not include this address in array list
                if (cbsServiceAddress[i].getGeoArea().getGeoStreet().getRealHouseNumberRangeLow().trim().length() == 0 &&
                    cbsServiceAddress[i].getGeoArea().getGeoStreet().getRealHouseNumberRangeHigh().trim().length() == 0)
                {
                    continue;
                }
            }
                
            if (cbsServiceAddress[i].getBasicAddr() != null)
            {
                isBasicAddressNotNull = true;
                if (cbsServiceAddress[i].getBasicAddr().getCmnty() != null)
                {
                    isCommunityNotNull = true;
                }
            }

            ahRSag.setRSagFields(null,				//House Number
                        		 (isGeoStreetNotNull ? cbsServiceAddress[i].getGeoArea().getGeoStreet().getRealHouseNumberRangeLow() : ""),	//House Number Low
                        		 (isGeoStreetNotNull ? cbsServiceAddress[i].getGeoArea().getGeoStreet().getRealHouseNumberRangeHigh() : ""),	//House Number High
                        		 (isGeoStreetNotNull ? cbsServiceAddress[i].getGeoArea().getGeoStreet().getPrefix() : ""),						//Street Direction
                        		 (isGeoStreetNotNull ? cbsServiceAddress[i].getGeoArea().getGeoStreet().getName() : ""),						//Street Name
                        		 (isGeoStreetNotNull ? cbsServiceAddress[i].getGeoArea().getGeoStreet().getThoroughfare() : ""),				//Street Thoroughfare
                        		 (isGeoStreetNotNull ? cbsServiceAddress[i].getGeoArea().getGeoStreet().getSuffix() : ""),						//Street Name Suffix
                        		 (isCommunityNotNull ? cbsServiceAddress[i].getBasicAddr().getCmnty().getName() : ""),							//City
                        		 (isBasicAddressNotNull ? cbsServiceAddress[i].getBasicAddr().getState() : ""),							//State
                        		 (isBasicAddressNotNull ? cbsServiceAddress[i].getBasicAddr().getZipCode() : ""),							//Postal Code
                        		 (isBasicAddressNotNull ? cbsServiceAddress[i].getBasicAddr().getCounty() : ""));							//County
            
            if (!ahRSag.equalsRangedAddress(previous_ahRsag))
            {
                addToRangeList(ahRSag,
                        	   ((cbsStatusMessage.getCode() != null) ? cbsStatusMessage.getCode() : ""),
                        	   ((cbsStatusMessage.getText() != null) ? cbsStatusMessage.getText() : ""),
                        	   ((isCommunityNotNull && cbsServiceAddress[i].getBasicAddr().getCmnty().getCrossBoundaryStates() != null) ? cbsServiceAddress[i].getBasicAddr().getCmnty().getCrossBoundaryStates() : ""));  
            }
            
            //Help on releasing the memory
            previous_ahRsag = null;
            previous_ahRsag = ahRSag;
            ahRSag = null;
        }     
    }
    
    /**
     * Add to the address range list.
     * @param item ah AddressHandlerRSAG
     * @param addressMatchCode String
     * @param addressMatchCodeDescription String
     * @param crossBoundaryState String
     */
    protected void addToRangeList(AddressHandlerRSAG ah, 
            					  String addressMatchCode, 
            					  String addressMatchCodeDescription,
            					  String crossBoundaryState) 
    {
    	// add to final rangeList
    	rangeList.add(new RangeData(
    		ah.getStDir(),  
    		ah.getStName(),
    		ah.getStThorofare(),
    		ah.getStNameSfx(),
    		ah.getHouseNumberPrefixLow(),		
    		ah.getHouseNumberLow(),
    		ah.getHouseNumberSuffixLow(),	
    		ah.getHouseNumberPrefixHigh(),	
    		ah.getHouseNumberHigh(),
    		ah.getHouseNumberSuffixHigh(),	
    		ah.getCity(),
    		ah.getState(), 
    		ah.getPostalCode(),
            "",
            ah.getCounty(),
            "",
            "",
            addressMatchCode,
            addressMatchCodeDescription,
            crossBoundaryState));                    
    }
}
