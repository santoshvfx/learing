// $Id: LocationI.java,v 1.6 2006/06/08 19:27:50 gg2712 Exp $

package com.sbc.eia.bis.BusinessInterface.location;

import com.sbc.bccs.idl.helpers.TN;
import com.sbc.eia.bis.BusinessInterface.Business;
import com.sbc.eia.idl.bis_types.AccessDenied;
import com.sbc.eia.idl.bis_types.BusinessViolation;
import com.sbc.eia.idl.bis_types.DataNotFound;
import com.sbc.eia.idl.bis_types.InvalidData;
import com.sbc.eia.idl.bis_types.NotImplemented;
import com.sbc.eia.idl.bis_types.ObjectNotFound;
import com.sbc.eia.idl.bis_types.SystemFailure;
import com.sbc.eia.idl.lim.RetrieveLocationForAddressReturn;
import com.sbc.eia.idl.lim.RetrieveLocationForServiceReturn;
import com.sbc.eia.idl.lim_types.Address;
import com.sbc.eia.idl.lim_types.ProviderLocationProperties;
import com.sbc.eia.idl.types.LongOpt;
import com.sbc.eia.idl.types.StringOpt;

/**
 * Business Interface for location retrieval.
 * Creation date: (2/22/01 12:39:14 PM)
 * @author: David Prager
 */
public interface LocationI extends Business
{
	public final static String LOCATION_INTERFACE_NAME = "LocationI";


    /**
     * Retrieve location information by address.
     * Creation date: (2/22/01 12:45:09 PM)
     * @return RetrieveLocationForAddressReturn
     * @param aAddress AddressHandler
     * @param aMaximumBasicAddressReturnLimit LongOpt
     * @param aMaximumUnitReturnLimit LongOpt
     * @param aExchangeCode
     * @exception InvalidData
     * @exception AccessDenied
     * @exception BusinessViolation
     * @exception DataNotFound
     * @exception SystemFailure
     * @exception NotImplemented
     * @exception ObjectNotFound
     */
    public RetrieveLocationForAddressReturn retrieveLocationForAddress (Address aAddress,
    	ProviderLocationProperties[] aLocationPropertiesToGet, LongOpt aMaximumBasicAddressReturnLimit, LongOpt aMaximumUnitReturnLimit, StringOpt aExchangeCode)
    	throws InvalidData, AccessDenied, BusinessViolation, DataNotFound, SystemFailure, NotImplemented, ObjectNotFound;
    
    /**
     * Retrieve location information by telephone number.
     * Creation date: (2/22/01 12:45:09 PM)
     * @return RetrieveLocationForServiceReturn
     * @param aService TN
     * @param aMaximumBasicAddressReturnLimit LongOpt
     * @param aMaximumUnitReturnLimit LongOpt
     * @exception InvalidData
     * @exception AccessDenied
     * @exception BusinessViolation
     * @exception DataNotFound
     * @exception SystemFailure
     * @exception NotImplemented
     * @exception ObjectNotFound
     */
    public RetrieveLocationForServiceReturn retrieveLocationForService (TN aService,
    	ProviderLocationProperties[] aLocationPropertiesToGet, LongOpt aMaximumBasicAddressReturnLimit, LongOpt aMaximumUnitReturnLimit)
    	throws InvalidData, AccessDenied, BusinessViolation, DataNotFound, SystemFailure, NotImplemented, ObjectNotFound;
}