// $Id:$
package com.sbc.eia.bis.lim.transactions.RetrieveLocation;

import com.sbc.eia.idl.lim_types.AlternativeAddressResult;

/**
 * @author rz7367
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */

public interface AlternativeAddressResultResp
{
    /**
     * Instantiate and return an AlternativeAddress array from contained host data.
     * Creation date: (3/16/01 10:35:47 AM)
     * @return AlternativeAddress[]
     */
    AlternativeAddressResult toAltAddrResult();
}

