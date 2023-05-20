//$Id: UpdateBillingAddressTrans.java,v 1.7 2008/02/15 00:25:39 jh9785 Exp $

package com.sbc.eia.bis.lim.transactions.UpdateBillingAddress;

import com.sbc.eia.bis.lim.util.LIMBase;
import com.sbc.eia.idl.types.CompositeObjectKey;
import com.sbc.eia.idl.bis_types.AccessDenied;
import com.sbc.eia.idl.bis_types.BusinessViolation;
import com.sbc.eia.idl.bis_types.DataNotFound;
import com.sbc.eia.idl.bis_types.InvalidData;
import com.sbc.eia.idl.bis_types.NotImplemented;
import com.sbc.eia.idl.bis_types.ObjectNotFound;
import com.sbc.eia.idl.bis_types.SystemFailure;
import com.sbc.eia.idl.lim_types.Address;
import com.sbc.eia.idl.lim_types.AddressOpt;
import com.sbc.eia.idl.types.StringOpt;
import com.sbc.eia.idl.lim.UpdateBillingAddressReturn;
import com.sbc.eia.bis.BusinessInterface.bcam.BCAM;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.bis.lim.util.LIMIDLUtil;

/**
 * This is the transaction level of class for Update Billing Address in BCAM
 */
public class UpdateBillingAddressTrans 
{
    public LIMBase limBase = null;
    private UpdateBillingAddressValidator m_validator = null;
    private BCAM bcam = null;
    
    /**
     * Constructor
     * @param m_LimBase LIMBase
     */
    public UpdateBillingAddressTrans(LIMBase lim_Base) 
    {
        limBase = lim_Base;
    }

    /**
     * This method implements the Update Billing Address business function.
     * @return UpdateBillingAddressReturn
     * @param aBillingAccountKey CompositeObjectKey
     * @param aOldAddress AddressOpt
     * @param aNewAddress Address
     * @param aDeliveryPointValidationCode StringOpt
     * @param aContactName StringOpt
     * @exception InvalidData An input parameter contained invalid data.
     * @exception AccessDenied Access to the specified domain object or information is not allowed.
     * @exception BusinessViolation The attempted action violates a business rule.
     * @exception SystemFailure The method could not be completed due to system level errors (e.g., out of memory, loss of network connectivity)n.
     * @exception NotImplemented The method has not been implemented.
     * @exception ObjectNotFound The desired domain object could not be found.
     * @exception DataNotFound No data found.
     */
    public UpdateBillingAddressReturn execute(
            CompositeObjectKey aBillingAccountKey,
            AddressOpt aOldAddress,
            Address aNewAddress,
            StringOpt aDeliveryPointValidationCode,
            StringOpt aContactName)
    	throws 
    		InvalidData, 
    		AccessDenied, 
    		BusinessViolation, 
    		DataNotFound, 
    		SystemFailure, 
    		NotImplemented, 
    		ObjectNotFound
    {
        UpdateBillingAddressReturn rv = null;
        
        limBase.log(LogEventId.INPUT_DATA,LIMIDLUtil.displayBCAMInput(aBillingAccountKey, aOldAddress, aNewAddress, aDeliveryPointValidationCode, aContactName));
        
        getRequestValidator().validate(aBillingAccountKey, aNewAddress, aOldAddress);
        
        limBase.log(LogEventId.AUDIT_TRAIL, "UpdateBillingAddressTrans::execute()|BCAM::updateBillingAddress()|PRE");
        
        rv = getBCAM().updateBillingAddress(aBillingAccountKey,
                						aOldAddress,
                						aNewAddress,
                						aDeliveryPointValidationCode,
                						aContactName);
        
        limBase.log (LogEventId.OUTPUT_DATA, LIMIDLUtil.displayBCAMOutput(rv));
        
        limBase.log(LogEventId.AUDIT_TRAIL, "UpdateBillingAddressTrans::execute()|BCAM::updateBillingAddress()|POST");
        
        return rv;
    }
    
    /**
     * Return caching value m_validator
     * @return m_validator UpdateBillingAddressValidator
     */
    private UpdateBillingAddressValidator getRequestValidator()
    {
        if (m_validator == null)
        {
            m_validator = new UpdateBillingAddressValidator(limBase);
        }
        return m_validator;
    }
    
    /**
     * Return caching value bcam
     * @return bcam getBCAM
     */
    private BCAM getBCAM()
    {
        if (bcam == null )
        {
            bcam = new BCAM(limBase);
        }
        return bcam;
    }
}
