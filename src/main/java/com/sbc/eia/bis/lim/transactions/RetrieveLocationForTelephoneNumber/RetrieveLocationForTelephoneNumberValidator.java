// $Id: RetrieveLocationForTelephoneNumberValidator.java,v 1.4 2008/02/29 22:41:04 gg2712 Exp $
package com.sbc.eia.bis.lim.transactions.RetrieveLocationForTelephoneNumber;

import java.util.Properties;

import com.sbc.bccs.idl.helpers.TN;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilder;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilderResult;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilderRule;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.bis.lim.util.BisContextValidations;
import com.sbc.eia.bis.lim.util.LIMBase;
import com.sbc.eia.bis.lim.util.LIMBisContextManager;
import com.sbc.eia.bis.lim.util.LIMIDLUtil;
import com.sbc.eia.bis.lim.util.LIMTag;
import com.sbc.eia.idl.bis_types.AccessDenied;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.bis_types.BusinessViolation;
import com.sbc.eia.idl.bis_types.DataNotFound;
import com.sbc.eia.idl.bis_types.InvalidData;
import com.sbc.eia.idl.bis_types.NotImplemented;
import com.sbc.eia.idl.bis_types.ObjectNotFound;
import com.sbc.eia.idl.bis_types.SystemFailure;
import com.sbc.eia.idl.bis_types.TelephoneNumber;
import com.sbc.eia.idl.lim_types.LocationPropertiesToGetSeqOpt;
import com.sbc.eia.idl.lim_types.LocationPropertiesToGetValue;
import com.sbc.eia.idl.types.Severity;

/**
 * Contain validation rules for RetrieveLocationForTelephoneNumber
 */

public class RetrieveLocationForTelephoneNumberValidator
{
    public static final String METHOD_RETRIEVE_LOCATION_FOR_TELEPHONE_NUMBER  = "retrieveLocationForTelephoneNumber" ;
    private LIMBase aLimBase = null;
    private String ruleFile = null;

    /**
     * RetrieveLocationForTelephoneNumberValidator constructor.
     */
    public RetrieveLocationForTelephoneNumberValidator(LIMBase limBase)
    {
        aLimBase = limBase;
        ruleFile = (String) aLimBase.getPROPERTIES().get(LIMTag.CSV_FileName_LIM);
    }

    protected void validateBisContext()
	throws BusinessViolation,
		ObjectNotFound,
		InvalidData,
		AccessDenied,
		SystemFailure,
		DataNotFound,
		NotImplemented
	{
	    BisContextValidations bisContextValidations = new BisContextValidations(aLimBase);
	    bisContextValidations.editBisContext();
	    bisContextValidations.checkAuthorization( "" , METHOD_RETRIEVE_LOCATION_FOR_TELEPHONE_NUMBER);
	}

	protected void validate(TelephoneNumber telNo)
	    throws BusinessViolation,
	        ObjectNotFound,
	        InvalidData,
	        AccessDenied,
	        SystemFailure,
	        DataNotFound,
	        NotImplemented
	{
	    if (telNo == null)
	    {
	        throwMissingTNException();
	    }
	
	    TN tn = new TN(telNo.aNpa + telNo.aNxx + telNo.aLine);
	
	    if (tn.toString().equals(""))
	    {
	        throwMissingTNException();
	    }
	
	    if (!tn.isValid())
	    {
	        throwInvalidTNFormatException(tn);
	    }
	}
	
	private void throwMissingTNException()
	    throws BusinessViolation,
	        ObjectNotFound,
	        InvalidData,
	        AccessDenied,
	        SystemFailure,
	        DataNotFound,
	        NotImplemented
	{
	    Properties tagValues = new Properties();
	    tagValues.clear();
	    tagValues.setProperty("CLASS", "TN");
	
	    ExceptionBuilder.parseException(
	        aLimBase.getCallerContext(),
	        ruleFile,
	        "",
	        LIMTag.CSV_EditError,
	        "Missing Telephone Number",
	        true, 
	        ExceptionBuilderRule.NO_DEFAULT, 
	        null, 
	        null, 
	        aLimBase.myLogger.getBisLogger(), 
	        "LIM", 
	        Severity.UnRecoverable, 
	        tagValues);
	}
	
	private void throwInvalidTNFormatException(TN tn)
	    throws BusinessViolation,
	        ObjectNotFound,
	        InvalidData,
	        AccessDenied,
	        SystemFailure,
	        DataNotFound,
	        NotImplemented
	{
	    Properties tagValues = new Properties();
	    tagValues.setProperty("CLASS", "TN");
	    tagValues.setProperty("TN", tn.toString());
	
	    ExceptionBuilder.parseException(
	        aLimBase.getCallerContext(), 
	        ruleFile, 
	        "", 
	        LIMTag.CSV_EditError,
	        "Invalid TN Format", 
	        true, 
	        ExceptionBuilderRule.NO_DEFAULT, 
	        null, 
	        null, 
	        aLimBase.myLogger.getBisLogger(), 
	        "LIM", 
	        Severity.UnRecoverable, 
	        tagValues);
	}
}
