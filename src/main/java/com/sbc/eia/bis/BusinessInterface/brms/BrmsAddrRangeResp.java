// $Id: BrmsAddrRangeResp.java,v 1.8 2007/02/19 20:16:27 gg2712 Exp $

package com.sbc.eia.bis.BusinessInterface.brms;

import java.util.List;

import com.sbc.eia.bis.lim.transactions.RetrieveLocation.AddrRangeResp;
import com.sbc.eia.bis.lim.util.LIMBase;
import com.sbc.eia.idl.lim.helpers.AddressHandlerBRMS;
import com.sbc.eia.idl.lim.helpers.AddressHandlerException;
import com.sbc.eia.bis.lim.database.queries.RetrieveBRMSSagInfo;
import com.sbc.eia.bis.lim.database.queries.BRMSSagInfo;

/**
 * Build a range of addresses from SAG01F Sag Response.
 * Creation date: (3/16/01 9:49:27 AM)
 * @author: David Brawley
 */
public class BrmsAddrRangeResp extends AddrRangeResp 
{
	protected BRMSSagInfo [] sagResp = null;
	protected AddressHandlerBRMS ahBrmsSag = null;
    /**
     * Construct lists of addresses and of address ranges from a SAG01F 
     * BRMS Address Range Response.
     * Creation date: (3/16/01 9:53:18 AM)
     * @param utility LIMBase
     */
    public BrmsAddrRangeResp(LIMBase utility)
    {
    	super(utility);
    	ahBrmsSag = new AddressHandlerBRMS();
    }
    /**
     * Construct lists of addresses and of address ranges from a CIWINService 
     * BRMS Address Range Response.
     * Creation date: (3/16/01 9:53:18 AM)
     * @param utility LIMBase
     * @param aSagResp  a SAGResponse object
     */
    public BrmsAddrRangeResp(LIMBase utility, BRMSSagInfo [] brmsSagInfo)
    {
    	super(utility);
    	sagResp = brmsSagInfo;
    	ahBrmsSag = new AddressHandlerBRMS();	
    }
    /**
     * Add to the address range list of AddressRangeResp.
     * Creation date: (3/16/01 10:19:31 AM)
     * @param ah          an AddressHandlerBRMS object
     * @param lowHseNbr   a String
     * @param highHseNbr  a String
     */
    protected void addToRangeList(AddressHandlerBRMS ah, String lowHseNbr, String highHseNbr) 
    {
    	// add to final rangeList
    	rangeList.add(new RangeData(
    		ah.getStDir(),  
    		ah.getStName(),
    		ah.getStThorofare(),
    		ah.getStNameSfx(),
    		"",							// lowHouseNbrPfx	
    		formatStNbr(lowHseNbr),
    		"",							// lowHouseNbrSfx
    		"",							// highHouseNbrPfx
    		formatStNbr(highHseNbr),
    		"",							// highHouseNbrSfx
    		ah.getCity(),
    		BRMSTag.CT, 
    		ah.getPostalCode(),
            ""));                       // zip4
    }
    /**
     * Method to remove leading zeros and spaces from BRMS Street Number.
     * Creation date: (8/31/01 8:04:58 AM)
     * @return String
     * @param value String
     */
    public static String formatStNbr (String value) 
    {
    	if (value == null)
    		return value;
    		
    	if (value.trim().length() == 0)
    		return value.trim();
    
    	StringBuffer temp = new StringBuffer (value);
    	
    	for (int i = 0; i < value.length(); i++){
    		if ((value.charAt(i) == ' ') ||
    			(value.charAt(i) == '0')){
    			if ((i + 1) == value.length()){
    				temp = temp.delete(0,i);
    				i = value.length();
    			}
    		}
    		else{
    			temp = temp.delete(0,i);
    			i = value.length();
    		}
    	}
    
    	return temp.toString().trim();
    }
    /**
     * Non-public getter for the internal List.
     * Creation date: (3/16/01 10:04:02 AM)
     * @return List
     */
    List getRangeList()
    {
    	return rangeList;
    }
    /**
     * parseSagResp will process the SAGType records returned from CIWIN
     * via the SAG01f service, for each record the address data will be 
     * parsed and added to the address range list.
     * Creation date: (3/27/01 12:01:14 PM)
     */
    protected void parseSagResp()
    {
    	for (int i = 0; i < sagResp.length; i++) 
    	{
    		if (!sagResp[i].street.trim().equals(""))
    		{
    			try
    			{
    				ahBrmsSag.setBRMSFields("", 
                                          sagResp[i].street.trim(), 
                                          sagResp[i].city.trim(), //"", PR19290796 Include city on a ranged response 
                                          BRMSTag.CT, 
                                          "",
                                          "",
                                          "",
                                          "",
                                          "",
                                          "", 
                                          "");
    			}
    			catch (AddressHandlerException e){}
    		}
    
    		if ((!sagResp[i].wireCenter.trim().equals("")))
    		{
    			ahBrmsSag.setPostalCode (sagResp[i].zipCode.trim());
    			addToRangeList(ahBrmsSag, 
    						   sagResp[i].lowStreetRange,
    						   sagResp[i].highStreetRange);
    		}
    	} // end for loop
    }
}
