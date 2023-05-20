// $Id: FieldAddressTrans.java,v 1.8 2008/02/29 23:27:21 jd3462 Exp $

/* Copyright Notice
 * RESTRICTED - PROPRIETARY INFORMATION
 * The information herein is for use only by authorized employees
 * of SBC Services Inc. and authorized Affiliates of SBC Services,
 * Inc., and is not for general distribution within or outside the
 * the respective companies.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2004 SBC Services, Inc.
 * All rights reserved.
 */

/** Description
 *  This file contains the FieldAddressTrans class used to parse an unfielded
 *  address into a fielded address.
 *  Description
 */

package com.sbc.eia.bis.lim.transactions.FieldAddress;

import com.sbc.bccs.idl.helpers.IDLUtil;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.bis.lim.util.LIMBase;
import com.sbc.eia.bis.lim.util.LIMIDLUtil;
import com.sbc.eia.idl.bis_types.AccessDenied;
import com.sbc.eia.idl.bis_types.BusinessViolation;
import com.sbc.eia.idl.bis_types.DataNotFound;
import com.sbc.eia.idl.bis_types.InvalidData;
import com.sbc.eia.idl.bis_types.NotImplemented;
import com.sbc.eia.idl.bis_types.ObjectNotFound;
import com.sbc.eia.idl.bis_types.SystemFailure;
import com.sbc.eia.idl.exception_types.ExceptionCode;
import com.sbc.eia.idl.lim.FieldAddressReturn;
import com.sbc.eia.idl.lim.helpers.AddressHandler;
import com.sbc.eia.idl.lim.helpers.AddressHandlerAIT;
import com.sbc.eia.idl.lim.helpers.AddressHandlerException;
import com.sbc.eia.idl.lim_types.Address;
import com.sbc.eia.idl.lim_types.FieldedAddress;
import com.sbc.eia.idl.lim_types.UnfieldedAddress;
import com.sbc.eia.idl.types.Severity;

/**
 * A class that converts an unfielded address to a fielded address.
 * For example, FieldAddressTrans converts the unfielded address
 * "123 Test Drive" to a fielded address with "123" as the house number, 
 * "Test" as the street name and "Drive" as the thoroughfare.
 * Creation date: (9/20/01 1:17:35 PM)
 * @author: Donald W. Lee - Local
 */
public class FieldAddressTrans 
{
	LIMBase limBase = null;
    
    /**
     * @param LimBase
     */
    public FieldAddressTrans (LIMBase LimBase) 
    {
    	limBase = LimBase;
    } 
    /**
     * Converts an unfielded address to a fielded address.
     * Creation date: (9/20/01 1:36:49 PM)
     * @return FieldAddressReturn
     * @param aUnfieldedAddress UnfieldedAddress
     * @exception InvalidData An input parameter contained invalid data.
     * @exception AccessDenied Access to the specified domain object or information is not allowed.
     * @exception BusinessViolation The attempted action violates a business rule.
     * @exception SystemFailure The method could not be completed due to system level errors.
     * @exception NotImplemented The method has not been implemented.
     * @exception ObjectNotFound The desired domain object could not be found.
     * @exception DataNotFound No data found.
     */
    public FieldAddressReturn execute (UnfieldedAddress aUnfieldedAddress) 
    throws InvalidData, 
    	   AccessDenied, 
    	   BusinessViolation,
    	   ObjectNotFound, 
    	   SystemFailure,
    	   DataNotFound, 
    	   NotImplemented 
    {	
    	FieldAddressReturn far = null;
    		
    	limBase.log(LogEventId.AUDIT_TRAIL, "FieldAddressTrans::execute()|FieldAddressReturn::FieldAddressReturn()|PRE");
    	
    	limBase.log(LogEventId.INPUT_DATA, LIMIDLUtil.display (aUnfieldedAddress));
    
    	try
    	{
    		if (aUnfieldedAddress == null)
    			limBase.throwException(ExceptionCode.ERR_LIM_MISSING_ADDRESS_INFO,
    				limBase.formatInvalidData( UnfieldedAddress.class, "aUnfieldedAddress",
    				null,
    				"Insufficient address info provided. Missing required fields."),
    			limBase.getEnv( "LIM_BIS_NAME" ), 
    			Severity.UnRecoverable);
    		
    		Address address = new Address ();
    		address.aUnfieldedAddress (aUnfieldedAddress);	
    		AddressHandler ah = new AddressHandler (address);
    		
    
    		/**
    		 *  Check for missing state.
    		 */
    		if (ah.getState().length() == 0)
    			limBase.throwException(ExceptionCode.ERR_LIM_INVALID_STATE,
    				limBase.formatInvalidData( UnfieldedAddress.class, "aState"),
    				limBase.getEnv( "LIM_BIS_NAME" ), 
    				Severity.UnRecoverable);
    			
    		FieldedAddress fa = null;
    		AddressHandlerAIT ahAIT = null;
    		if (limBase.validateState (ah.getState (), limBase.REGIONS_AIT))
    		{
    			ahAIT = new AddressHandlerAIT (address);
    			fa = new FieldedAddress(
    				IDLUtil.toOpt(ahAIT.getRoute()),
    				IDLUtil.toOpt(ahAIT.getBox()),
					IDLUtil.toOpt(ahAIT.getHousNbrPfx()),
					IDLUtil.toOpt(ahAIT.getHousNbr()),
					IDLUtil.toOpt(ahAIT.getAasgndHousNbr()),
					IDLUtil.toOpt(ahAIT.getHousNbrSfx()),
					IDLUtil.toOpt(ahAIT.getStDir()),
					IDLUtil.toOpt(ahAIT.getStName()),
					IDLUtil.toOpt(ahAIT.getStThorofare()),
					IDLUtil.toOpt(ahAIT.getStNameSfx()),
    				IDLUtil.toOpt(ahAIT.getCity()),
    				IDLUtil.toOpt(ahAIT.getState()),
    				IDLUtil.toOpt(ahAIT.getPostalCode()),
                    IDLUtil.toOpt(ahAIT.getPostalCodePlus4()),
    				IDLUtil.toOpt(ahAIT.getCounty()),
    				IDLUtil.toOpt(ahAIT.getCountry()),
    				IDLUtil.toOpt(ahAIT.getStructType()),
                    IDLUtil.toOpt(ahAIT.getStructValue()),
                    IDLUtil.toOpt(ahAIT.getLevelType()),
                    IDLUtil.toOpt(ahAIT.getLevelValue()),
                    IDLUtil.toOpt(ahAIT.getUnitType()),
                    IDLUtil.toOpt(ahAIT.getUnitValue()),
                    IDLUtil.toOpt(ahAIT.getOriginalStreetDirection()),
                    IDLUtil.toOpt(ahAIT.getOriginalStreetNameSuffix()),
                    IDLUtil.toOpt(ahAIT.getCassAddressLines()),
                    IDLUtil.toOpt(ahAIT.getAuxiliaryAddressLines()),
                    IDLUtil.toOpt(ahAIT.getCassAdditionalInfo()),
    				IDLUtil.toOpt(ahAIT.getAdditionalInfo()),
                    IDLUtil.toOpt(ahAIT.getBusinessName()),
                    IDLUtil.toOpt(""),
                    IDLUtil.toOpt(""),
                    IDLUtil.toOpt(""),
                    IDLUtil.toOpt(""),
                    IDLUtil.toOpt(""),
                    IDLUtil.toOpt("")
                    );
    		}
    		else
    		{
    			fa = new FieldedAddress(
    				IDLUtil.toOpt(ah.getRoute()),
    				IDLUtil.toOpt(ah.getBox()),
					IDLUtil.toOpt(ah.getHousNbrPfx()),
					IDLUtil.toOpt(ah.getHousNbr()),
					IDLUtil.toOpt(ah.getAasgndHousNbr()),
					IDLUtil.toOpt(ah.getHousNbrSfx()),
					IDLUtil.toOpt(ah.getStDir()),
					IDLUtil.toOpt(ah.getStName()),
					IDLUtil.toOpt(ah.getStThorofare()),
					IDLUtil.toOpt(ah.getStNameSfx()),
    				IDLUtil.toOpt(ah.getCity()),
    				IDLUtil.toOpt(ah.getState()),
    				IDLUtil.toOpt(ah.getPostalCode()),
                    IDLUtil.toOpt(ah.getPostalCodePlus4()),
    				IDLUtil.toOpt(ah.getCounty()),
    				IDLUtil.toOpt(ah.getCountry()),
                    IDLUtil.toOpt(ah.getStructType()),
                    IDLUtil.toOpt(ah.getStructValue()),
                    IDLUtil.toOpt(ah.getLevelType()),
                    IDLUtil.toOpt(ah.getLevelValue()),
                    IDLUtil.toOpt(ah.getUnitType()),
                    IDLUtil.toOpt(ah.getUnitValue()),
                    IDLUtil.toOpt(ah.getOriginalStreetDirection()),
                    IDLUtil.toOpt(ah.getOriginalStreetNameSuffix()),
                    IDLUtil.toOpt(ah.getCassAddressLines()),
                    IDLUtil.toOpt(ah.getAuxiliaryAddressLines()),
                    IDLUtil.toOpt(ah.getCassAdditionalInfo()),
                    IDLUtil.toOpt(ah.getAdditionalInfo()),
                    IDLUtil.toOpt(ah.getBusinessName()),
                    IDLUtil.toOpt(""),
                    IDLUtil.toOpt(""),
                    IDLUtil.toOpt(""),
                    IDLUtil.toOpt(""),
                    IDLUtil.toOpt(""),
                    IDLUtil.toOpt(""));
    		}
    
    		Address retAddr = new Address ();		
    		retAddr.aFieldedAddress(fa);
    
    		far = new FieldAddressReturn (limBase.getCallerContext (), retAddr);
    		
    		limBase.log(LogEventId.OUTPUT_DATA, LIMIDLUtil.display (retAddr.aFieldedAddress()));
    	}
    	catch (org.omg.CORBA.BAD_OPERATION badop)
    	{
    		limBase.throwException(ExceptionCode.ERR_LIM_MISSING_ADDRESS_INFO,
    			limBase.formatInvalidData( UnfieldedAddress.class, "aUnfieldedAddress",
    			null,
    			"Address data is not accessible."),
    			limBase.getEnv( "LIM_BIS_NAME" ), 
    			Severity.UnRecoverable, badop);
    	}
    	catch (AddressHandlerException ahe)
    	{
    		limBase.throwException(ExceptionCode.ERR_LIM_MISSING_ADDRESS_INFO,
    				limBase.formatInvalidData( UnfieldedAddress.class, "aUnfieldedAddress",
    				null,
    				"Address data is missing or invalid."),
    			limBase.getEnv( "LIM_BIS_NAME" ), 
    			Severity.UnRecoverable, ahe);
    	}
    	finally
    	{
    
        	limBase.log(LogEventId.AUDIT_TRAIL, "FieldAddressTrans::execute()|FieldAddressReturn::FieldAddressReturn()|POST");
    
    	}
    	
    	return far;
    }
}
