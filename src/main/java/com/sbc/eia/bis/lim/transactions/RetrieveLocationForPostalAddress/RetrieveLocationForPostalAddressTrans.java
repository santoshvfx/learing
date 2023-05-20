//$Id: RetrieveLocationForPostalAddressTrans.java,v 1.2 2007/09/25 21:45:46 jh9785 Exp $

package com.sbc.eia.bis.lim.transactions.RetrieveLocationForPostalAddress;

import com.sbc.eia.bis.BusinessInterface.PostalAddress.ovalsusps.OVALSUSPS;
import com.sbc.eia.bis.lim.util.LIMBase;
import com.sbc.eia.bis.lim.util.LIMIDLUtil;
import com.sbc.eia.bis.lim.util.LIMTag;
import com.sbc.eia.idl.bis_types.*;
import com.sbc.eia.idl.lim.RetrieveLocationForPostalAddressReturn;
import com.sbc.eia.idl.lim_types.Address;
import com.sbc.eia.idl.types.LongOpt;
import com.sbc.eia.idl.types.Severity;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.bccs.utilities.Utility;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilder;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilderResult;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilderRule;

/**
 * A new class to replace RetrieveLocationForPostalAddressTrans.java
 * All common methods, which will be shared with RetrieveLocationForTelephoneNumber, were moved to RetrieveServiceLocationBase.java  
 */

public class RetrieveLocationForPostalAddressTrans
{
    public LIMBase limBase = null;
    private RetrieveLocationForPostalAddressValidator m_requestValidator = null;
    private OVALSUSPS m_OvalsUsps = null;
    private ExceptionBuilderResult exBldReslt = null;
	private String ruleFile = null;
	private boolean isCSZ = false;
    
    /**
     * Constructor for RetrieveLocationForPostalAddressTrans.
     * @param limBase LIMBase
     */
    public RetrieveLocationForPostalAddressTrans(LIMBase lim_Base)
    {
        limBase = lim_Base;
        ruleFile = (String) limBase.getPROPERTIES().get(LIMTag.CSV_FileName_LIM);
    }

    /**
     * This method implements the Retrieve Location By Postal Address business function.
     * Creation date: jh9785 (7/22/07)
     * @return RetrieveLocationForPostalAddressReturn
     * @param aAddress Address
     * @param aMaxAddressAlternatives LongOpt
     * @exception InvalidData An input parameter contained invalid data.
     * @exception AccessDenied Access to the specified domain object or information is not allowed.
     * @exception BusinessViolation The attempted action violates a business rule.
     * @exception SystemFailure The method could not be completed due to system level errors (e.g., out of memory, loss of network connectivity)n.
     * @exception NotImplemented The method has not been implemented.
     * @exception ObjectNotFound The desired domain object could not be found.
     * @exception DataNotFound No data found.
     */
    public RetrieveLocationForPostalAddressReturn execute(
            BisContext aContext,
            Address aAddress,
            LongOpt aMaxAddressAlternatives)
        throws 
        	BusinessViolation,
            InvalidData,
            SystemFailure,
            ObjectNotFound,
            NotImplemented,
            DataNotFound,
            AccessDenied
    {
        RetrieveLocationForPostalAddressReturn rv = null;
        setIsCSZ(false);
        getRequestValidator().validate(aAddress, aMaxAddressAlternatives);
        setIsCSZ(getRequestValidator().getIsCSZ());
        limBase.log(LogEventId.AUDIT_TRAIL, "RetrieveLocationForPostalAddressTrans::execute()|OVALSUSPS::retrieveLocationForPostalAddress()|PRE");
        getOvalsUSPS().setPassThru(this);
        rv = getOvalsUSPS().retrieveLocationForPostalAddress(aContext, aAddress, aMaxAddressAlternatives);
        
        if (rv == null)
        {
			exBldReslt = ExceptionBuilder.parseException(
			    limBase.getCallerContext(),
				ruleFile,
				"",
				LIMTag.CSV_AddressError,
				null,
				true,
				ExceptionBuilderRule.NO_DEFAULT,
				null,
				null,
				limBase.myLogger.getBisLogger(),
				"LIM",
				Severity.UnRecoverable,
				null);
		}
        
        limBase.log (LogEventId.OUTPUT_DATA, LIMIDLUtil.displayPostalAddressMatchResult(rv.aPostalAddressMatchResult));
        
        limBase.log(LogEventId.AUDIT_TRAIL, "RetrieveLocationForPostalAddressTrans::execute()|OVALSUSPS::retrieveLocationForPostalAddress()|POST");
        return rv;
    }
    
    /**
     * Return caching value m_requestValidator
     * @return m_requestValidator RetrieveLocationForPostalAddressValidator
     */
    private RetrieveLocationForPostalAddressValidator getRequestValidator()
    {
        if (m_requestValidator == null)
        {
            m_requestValidator = new RetrieveLocationForPostalAddressValidator(limBase);
        }
        return m_requestValidator;
    }
    
    private OVALSUSPS  getOvalsUSPS()
    {
        if (m_OvalsUsps == null)
        {
            m_OvalsUsps = new OVALSUSPS(limBase, limBase.getPROPERTIES());
        }

        return m_OvalsUsps;
    }
    
    /**
     * @return Returns the isCSZ.
     */
    public boolean getIsCSZ() {
        return isCSZ;
    }
    /**
     * @param isCSZ The isCSZ to set.
     */
    public void setIsCSZ(boolean iscsz) {
        this.isCSZ = iscsz;
    }
}