// $Id: BrmsAddrListResp.java,v 1.3 2007/10/07 22:10:53 gg2712 Exp $

package com.sbc.eia.bis.BusinessInterface.ServiceAddress.brms;

import java.util.List;

import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.bis.lim.transactions.common.ServiceAddrAltResp;
import com.sbc.eia.bis.lim.util.LIMBase;
import com.sbc.eia.idl.lim.helpers.AddressHandlerBRMS;
import com.sbc.eia.idl.lim.helpers.AddressHandlerException;
import com.sbc.eia.idl.lim_types.Address;
import com.sbc.eia.bis.lim.database.queries.BRMSSagInfo;

/**
 * Build a list of addresses from LFACS_SAG_LU table.
 * Creation date: (3/13/01 2:00:56 PM)
 * @author: David Brawley
 */
public class BrmsAddrListResp extends ServiceAddrAltResp
{
	protected java.lang.String postalCode = "";
	protected BRMSSagInfo [] brmsSagInfo = null; 
	protected AddressHandlerBRMS brmsReq = null;
	protected AddressHandlerBRMS brmsResp = null;
	protected Address[] addressArray = null;
    /**
     * Address list class to return BRMS Alternate List data to client.
     * Creation date: (3/16/01 10:05:16 AM)
     * @param utility LIMBase
     */
    BrmsAddrListResp (LIMBase utility)
    {
    	super(utility);
    	setBrmsReq (new AddressHandlerBRMS());
    	setBrmsResp (new AddressHandlerBRMS());
    }
    /**
     * Add an address to the list for an AddrListResp.
     * Creation date: (3/16/01 10:14:40 AM)
     * @param addr  an AddressHandlerBRMS object
     */
    protected void addToList (AddressHandlerBRMS addr)
    {
    
    	// add address to alternative address list (AddrListResp)
    	addrList.add(new AddressHandlerBRMS(
    		addr.getRoute(),				// aRoute
    		addr.getBox(),					// aBox,
    		addr.getHousNbrPfx(),			// aHouseNumberPrefix
    		addr.getHousNbr(),				// aHouseNumber
    		addr.getAasgndHousNbr(),   		// aAssignedHouseNumber
    		addr.getHousNbrSfx(),     		// aHouseNumberSuffix
    		addr.getStDir(), 				// aStreetDirection
    		addr.getStName(),				// aStreetName
    		addr.getStThorofare(),			// aStreetThoroughfare
    		addr.getStNameSfx(),			// aStreetNameSuffix
    		addr.getCity(),					// aCity
    		addr.getState(),				// aState
    		brmsSagInfo[0].zipCode.trim(),	// aPostalCode
    		addr.getCounty(),				// aCounty
    		addr.getCountry(),				// aCountry
    		addr.getStructType(),           // aStructType
            addr.getStructValue(),			// aStructValue
            addr.getLevelType(),            // aLevelType
            addr.getLevelValue(),           // aLevelValue
            addr.getUnitType(),             // aUnitType
            addr.getUnitValue(),            // aUnitValue
                                            // aAdditionalAddressInformation
            (brmsSagInfo[0].zipCode.trim().equals("") || brmsSagInfo[0].zipCode.trim().equals("00000")) 
            	?  BRMSTag.BLANK_ZIPCODE : addr.getAddAddrInfo(),         
    		false).getAddress());		    // stNmInd boolean
    }
    /**
     * Getter method for addressArray.
     * Creation date: (5/22/02 1:17:13 PM)
     * @return Address[]
     * @see #setAddressArray(Address[])
     */
    public Address[] getAddressArray() {
    	return addressArray;
    }
    /**
     * Package-access getter for the internal List.
     * Creation date: (3/16/01 10:06:07 AM)
     * @return List
     */
    List getAddrList()
    {
    	return addrList;
    }
    /**
     * Gets the AddressHandlerBRMS request object.
     * Creation date: (1/17/02 1:53:39 PM)
     * @return AddressHandlerBRMS
     * @see #setBrmsReq(AddressHandlerBRMS)
     */
    public AddressHandlerBRMS getBrmsReq() {
    	return brmsReq;
    }
    /**
     * Gets the AddressHandlerBRMS response object.
     * Creation date: (1/18/02 10:10:18 AM)
     * @return AddressHandlerBRMS
     * @see #setBrmsResp(AddressHandlerBRMS)
     */
    public AddressHandlerBRMS getBrmsResp() {
    	return brmsResp;
    }
    
    /**
     * parseAddressArray will process the address Array returned from LFACS_SAG_LU table,
     * for each row in array the address will be parsed and added to the alternate address list.
     * Creation date: (3/27/01 12:01:14 PM)
     * @param isDescriptiveAddr  boolean
     */
    protected void parseAddressArray(boolean isDescriptiveAddr)
    {
    	int maxAddr = 0;
    	
    	if (getMaxAddresses() > 0)
    		maxAddr = (addressArray.length > getMaxAddresses()) ? getMaxAddresses() : addressArray.length;
    	else	
    		maxAddr = addressArray.length;
    
    	getUtility().log(LogEventId.DEBUG_LEVEL_2, "Alternative Address List Response - Loop thru and process address array:");
    	// loop thru addressArray	
    	for (int i = 0; i < maxAddr; i++)
    	{
    		try
    		{	
    			brmsResp.setBRMSFields (addressArray[i]);
    			brmsResp.setPostalCode (brmsSagInfo[0].zipCode); // rz ? brmsReq.getPostalCode()); 
    			if (isDescriptiveAddr){
    				if ((brmsReq.getHousNbr().equals(brmsResp.getHousNbr())) &&
    					(brmsReq.getHousNbrSfx().equalsIgnoreCase(brmsResp.getHousNbrSfx())) &&
    					(brmsReq.getStDir().equalsIgnoreCase(brmsResp.getStDir())) &&
    					(brmsReq.getStName().equalsIgnoreCase(brmsResp.getStName())) &&
    					(brmsReq.getStThorofare().equalsIgnoreCase(brmsResp.getStThorofare())) &&
    					(brmsReq.getStNameSfx().equalsIgnoreCase(brmsResp.getStNameSfx()))){
    					addToList(brmsResp);
    				}
    			}
    			else{
    				addToList(brmsResp);
    			}
    		}
    		catch (AddressHandlerException ahe){}
    	}
    }
    /**
     * Setter method for addressArray.
     * Creation date: (5/22/02 1:17:13 PM)
     * @param newAddressArray Address[]
     * @see #getAddressArray
     */
    public void setAddressArray(Address[] newAddressArray) {
    	addressArray = newAddressArray;
    }
    /**
     * Sets the AddressHandlerBRMS request object.
     * Creation date: (1/17/02 1:53:39 PM)
     * @param newBrmsReq  an AddressHandlerBRMS object specifying the brms req value
     * @see #getBrmsReq
     */
    public void setBrmsReq(AddressHandlerBRMS newBrmsReq) {
    	brmsReq = newBrmsReq;
    }
    /**
     * Sets the AddressHandlerBRMS response object.
     * Creation date: (1/18/02 10:10:18 AM)
     * @param newBrmsResp AddressHandlerBRMS
     * @see #getBrmsResp
     */
    public void setBrmsResp(com.sbc.eia.idl.lim.helpers.AddressHandlerBRMS newBrmsResp) 
    {
    	brmsResp = newBrmsResp;
    }
    /**
     * Sets the SAG type.
     * Creation date: (1/16/02 8:03:06 AM)
     * @param newSagType SAGType
     * @see #getSagType
    */ 
    public void setSagInfo (BRMSSagInfo [] newSagInfo) 
    {
    	brmsSagInfo = newSagInfo;
    } 
}
