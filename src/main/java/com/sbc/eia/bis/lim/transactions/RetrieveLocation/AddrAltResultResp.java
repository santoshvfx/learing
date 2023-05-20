// $Id: $
package com.sbc.eia.bis.lim.transactions.RetrieveLocation;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import com.sbc.eia.bis.lim.util.LIMBase;
import com.sbc.eia.idl.lim.helpers.AddressHandler;
import com.sbc.eia.idl.lim_types.Address;
import com.sbc.eia.idl.lim_types.AlternativeAddress;
import com.sbc.eia.idl.lim_types.AlternativeAddressSeqOpt;
import com.sbc.eia.idl.lim_types.Location;
import com.sbc.eia.idl.lim_types.RangedAddress;
import com.sbc.eia.idl.lim_types.AlternativeAddressResult;
import com.sbc.eia.idl.lim_types.SubmittedAddressException;
import com.sbc.eia.idl.lim_types.SubmittedAddressExceptionSeqOpt;

/**
 * @author rz7367
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class AddrAltResultResp extends RetrieveLocResp implements AlternativeAddressResultResp
{
	protected List addrList = new ArrayList();
	protected int locSize = 0;
	protected int submitAddrExcSize = 0;

    /**
     * Construct an empty AddrListResp.
     * @param utility LIMBase
     */
    public AddrAltResultResp(LIMBase utility)
    {
    	super(utility);
    }
    
    /**
     * Create an array of AlternativeAddresses from the internal list.  This method implements
     * the AlternativeAddressesResp interface.
     * Creation date: (4/21/04 9:01:52 AM)
     * @return AlternativeAddress[]
     */
    public AlternativeAddressResult toAltAddrResult()
    {   	
    	AlternativeAddress[] aa = null;
    	if (locSize > 0)
    		aa = new AlternativeAddress [locSize];
    	
    	SubmittedAddressException[] bb = null;
    	if (submitAddrExcSize > 0)	
    		bb = new SubmittedAddressException [submitAddrExcSize];
    	
    	ListIterator it = addrList.listIterator();
    	int j = 0;
    	int k = 0;
    
    	while (it.hasNext())
    	{
    		Object obj = it.next();

            if (obj instanceof SubmittedAddressException)
            {
                bb[j] = (SubmittedAddressException) obj;
                j++;
            }
            if (obj instanceof Location)
            {
            	aa[k] = new AlternativeAddress();
                aa[k].aLocation((Location) obj);
                k++;
            }
    	}
    	
    	AlternativeAddressSeqOpt aaSeqOpt = new AlternativeAddressSeqOpt ();
    	if (locSize > 0)
    		aaSeqOpt.theValue (aa);
    	else
    		aaSeqOpt.__default();
    		
    	SubmittedAddressExceptionSeqOpt bbSeqOpt = new SubmittedAddressExceptionSeqOpt ();
    	if (submitAddrExcSize > 0)
    		bbSeqOpt.theValue (bb);
    	else
    		bbSeqOpt.__default();
    	
    	AlternativeAddressResult altAddrResult = new AlternativeAddressResult (aaSeqOpt, bbSeqOpt);
    	
    	return altAddrResult;
    }
}
