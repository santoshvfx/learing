// $Id: ServiceAddressLocationI.java,v 1.5 2007/09/25 21:24:44 jh9785 Exp $

package com.sbc.eia.bis.BusinessInterface.location;

import com.sbc.eia.bis.BusinessInterface.Business;
import com.sbc.eia.idl.bis_types.AccessDenied;
import com.sbc.eia.idl.bis_types.BusinessViolation;
import com.sbc.eia.idl.bis_types.DataNotFound;
import com.sbc.eia.idl.bis_types.InvalidData;
import com.sbc.eia.idl.bis_types.NotImplemented;
import com.sbc.eia.idl.bis_types.ObjectNotFound;
import com.sbc.eia.idl.bis_types.SystemFailure;
import com.sbc.eia.idl.bis_types.TelephoneNumber;
import com.sbc.eia.idl.lim.RetrieveLocationForServiceAddressReturn;
import com.sbc.eia.idl.lim.RetrieveLocationForTelephoneNumberReturn;
import com.sbc.eia.idl.lim_types.Address;

/**
 * Business Interface for service address location retrieval. Creation date:
 * (7/17/2007 12:39:14 PM)
 * @author: Jean Duka
 */
public interface ServiceAddressLocationI extends Business
{
	public final static String LOCATION_INTERFACE_NAME = "ServiceAddressLocationI";

    /**
     * Retrieve location information by service address. Creation date:
     * (7/16/2007 04:13:09 PM)
     * @param aAddress
     * @param aLocationPropertiesToGet
     * @param aPreviousOwnerName
     * @param aCrossBoundaryState
     * @param aMaxBasicAddressAlternatives
     * @param aMaxLivingUnitAlternatives
     * @return RetrieveLocationForServiceAddressReturn
     * @throws InvalidData
     * @throws AccessDenied
     * @throws BusinessViolation
     * @throws DataNotFound
     * @throws SystemFailure
     * @throws NotImplemented
     * @throws ObjectNotFound
     */
    public RetrieveLocationForServiceAddressReturn retrieveLocationForServiceAddress(
    	Address aAddress,
    	String[] aLocationPropertiesToGet,
	    String aPreviousOwnerName,
	    String aCrossBoundaryState,
	    int aMaxBasicAddressAlternatives,
	    int aMaxLivingUnitAlternatives)
	throws 
		InvalidData, 
		AccessDenied, 
		BusinessViolation, 
		DataNotFound, 
		SystemFailure, 
		NotImplemented, 
		ObjectNotFound;
    /**
     * Retrieve location information by service address. Creation date:
     * (7/16/2007 04:13:09 PM)
     * @param aTelephoneNumber
     * @param aLocationPropertiesToGet
     * @return RetrieveLocationForTelephoneNumberReturn
     * @throws InvalidData
     * @throws AccessDenied
     * @throws BusinessViolation
     * @throws DataNotFound
     * @throws SystemFailure
     * @throws NotImplemented
     * @throws ObjectNotFound
     */    
    public RetrieveLocationForTelephoneNumberReturn retrieveLocationForTelephoneNumber (
    	TelephoneNumber aTelephoneNumber,
    	String[] aLocationPropertiesToGet) 
	throws 
		InvalidData, 
		AccessDenied, 
		BusinessViolation, 
		DataNotFound, 
		SystemFailure, 
		NotImplemented, 
		ObjectNotFound;
}