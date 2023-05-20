// $Id: AlternativeAddressesResp.java,v 1.3 2004/04/15 15:13:40 db4252 Exp $

package com.sbc.eia.bis.lim.transactions.RetrieveLocation;



import com.sbc.eia.idl.lim_types.AlternativeAddress;

/**
 * This interface is implemented by classes that know how to convert a host
 * RetrieveLocation response to an IDL AlternativeAddress[].
 * Creation date: (3/16/01 10:33:40 AM)
 * @author: David Prager
 */
public interface AlternativeAddressesResp
{
    /**
     * Instantiate and return an AlternativeAddress array from contained host data.
     * Creation date: (3/16/01 10:35:47 AM)
     * @return AlternativeAddress[]
     */
    AlternativeAddress[] toAltAddr();
}