// $Id: OvalsListResp.java,v 1.7 2006/06/30 18:40:34 gg2712 Exp $

package com.sbc.eia.bis.BusinessInterface.ovals;

import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.bis.lim.transactions.RetrieveLocation.*;
import com.sbc.eia.bis.lim.util.*;
import com.sbc.eia.idl.bis_types.SystemFailure;
import com.sbc.eia.idl.lim.helpers.*;
import com.sbc.eia.idl.lim_types.*;
import com.sbc.bccs.idl.helpers.*;

/**
 * Build a list of addresses from OVALS.
 * Creation date: (01/08/02 2:00:56 PM)
 * @author: Rachel Zadok
 */
public class OvalsListResp extends AddrAltResp
{

/**
 * @param utility LIMBase
 * @param  address Address []
 * @param  matchCode String [] 
 * @param  matchDesc String []
 * @param  latLong String []
 * @param  listSize int
 * @exception SystemFailure
 */
public OvalsListResp (LIMBase utility, Address [] address, String [] matchCode, String [] matchDesc, String [] latitude, String [] longitude, int listSize)
	throws SystemFailure
{
	super(utility);
    
    for (int ij = 0; ij < listSize; ij++)
    {
    	Location loc = new Location ();
    	ProviderLocationProperty locProp = ProviderLocationPropertyHandler.getDefaultProviderLocationProperty();
        locProp.aServiceAddress = (AddressOpt) IDLUtil.toOpt (AddressOpt.class, address[ij]);
        locProp.aAddressMatchCode = IDLUtil.toOpt (matchCode[ij]);
        locProp.aAddressMatchCodeDescription = IDLUtil.toOpt (matchDesc[ij]);
        
        locProp.aLatitude = IDLUtil.toOpt(latitude[ij]);
        locProp.aLongitude = IDLUtil.toOpt(longitude[ij]);

    	locProp.aProviderName.theValue (LIMTag.DATA_SERVICES); 

        loc.aLocationId = IDLUtil.toOpt("");
        loc.aProviderLocationProperties = new ProviderLocationProperty[]{locProp};
        addrList.add(loc);
    }
}


}
