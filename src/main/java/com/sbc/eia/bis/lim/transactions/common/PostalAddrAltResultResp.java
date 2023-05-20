//$Id: PostalAddrAltResultResp.java,v 1.1 2008/01/17 21:40:17 jh9785 Exp $

package com.sbc.eia.bis.lim.transactions.common;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import com.sbc.eia.bis.lim.util.LIMBase;
import com.sbc.eia.idl.lim_types.AlternativePostalAddressResult;
import com.sbc.eia.idl.lim_types.SubmittedAddressException;
import com.sbc.eia.idl.lim_types.SubmittedAddressExceptionSeqOpt;
import com.sbc.eia.idl.lim_types.PostalLocation;
import com.sbc.eia.idl.lim_types.AlternativePostalLocationSeqOpt;

/**
 * Clone from AddrAltResultResp. Represents a list of address ranges from a host response, but it has NO host-specific
 * knowledge.  This class is not abstract, but it is meant to be subclassed by a
 * host-specific class.  The subclass constructor has the
 * responsibility to load the rangeList collection.  Using this class alone would
 * result in an empty address-range list.
 * @author jh9785
 */
public class PostalAddrAltResultResp extends RetrieveLocResp implements PostalAddressAlternativeAddressesResultResp
{

    protected List addrList = new ArrayList();
	protected int locSize = 0;
	protected int submitAddrExcSize = 0;

    /**
     * Construct an empty AddrListResp.
     * @param utility LIMBase
     */
    public PostalAddrAltResultResp(LIMBase utility)
    {
    	super(utility);
    }
    
    /**
     * Create AlternativePostalAddressResult.  This method implements
     * the AlternativeAddressesResp interface.
     * Creation date: (9/14/07 9:01:52 AM)
     * @return AlternativePostalAddressResult
     */
    public AlternativePostalAddressResult toAltAddrResult()
    {   	
        PostalLocation[] aa = null;
    	if (locSize > 0)
    		aa = new PostalLocation[locSize];
    	
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
            if (obj instanceof PostalLocation)
            {
            	aa[k] = (PostalLocation) obj;
                k++;
            }
    	}
    	
    	AlternativePostalLocationSeqOpt aaSeqOpt = new AlternativePostalLocationSeqOpt();
    	if (locSize > 0)
    		aaSeqOpt.theValue(aa);
    	else
    		aaSeqOpt.__default();
    		
    	SubmittedAddressExceptionSeqOpt bbSeqOpt = new SubmittedAddressExceptionSeqOpt();
    	if (submitAddrExcSize > 0)
    		bbSeqOpt.theValue(bb);
    	else
    		bbSeqOpt.__default();
    	
    	AlternativePostalAddressResult altAddrResult = new AlternativePostalAddressResult(aaSeqOpt, bbSeqOpt);
    	
    	return altAddrResult;
    }

}
