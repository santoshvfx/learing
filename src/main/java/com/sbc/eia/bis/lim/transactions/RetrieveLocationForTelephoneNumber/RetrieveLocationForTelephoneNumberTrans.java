// $Id: RetrieveLocationForTelephoneNumberTrans.java,v 1.6 2008/02/29 22:40:35 gg2712 Exp $
package com.sbc.eia.bis.lim.transactions.RetrieveLocationForTelephoneNumber;

import java.util.Properties;

import com.sbc.bccs.idl.helpers.TN;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilder;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilderRule;
import com.sbc.eia.bis.BusinessInterface.location.ServiceAddressLocationI;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.bis.lim.transactions.common.RetrieveServiceLocationBase;
import com.sbc.eia.bis.lim.util.LIMBase;
import com.sbc.eia.bis.lim.util.LIMIDLUtil;
import com.sbc.eia.bis.lim.util.LIMTag;
import com.sbc.eia.idl.bis_types.AccessDenied;
import com.sbc.eia.idl.bis_types.BusinessViolation;
import com.sbc.eia.idl.bis_types.DataNotFound;
import com.sbc.eia.idl.bis_types.InvalidData;
import com.sbc.eia.idl.bis_types.NotImplemented;
import com.sbc.eia.idl.bis_types.ObjectNotFound;
import com.sbc.eia.idl.bis_types.SystemFailure;
import com.sbc.eia.idl.bis_types.TelephoneNumber;
import com.sbc.eia.idl.lim.RetrieveLocationForTelephoneNumberReturn;
import com.sbc.eia.idl.lim_types.LocationPropertiesToGetSeqOpt;
import com.sbc.eia.idl.types.Severity;

/**
 * This is the RetrieveLocationForTelephoneNumber (TN) Transaction class.
 */
public class RetrieveLocationForTelephoneNumberTrans extends RetrieveServiceLocationBase
{

    private RetrieveLocationForTelephoneNumberValidator m_validator = null;

    private String[] m_LocationPropertiesToGet = null;

    private TelephoneNumber m_TelephoneNumber = null;

    /**
     * Object constructor for RetrieveLocationForTelephoneNumberTrans. Creation
     * date: (4/17/01 9:41:47 AM)
     * @param lim_base LIMBase
     */

    public RetrieveLocationForTelephoneNumberTrans(LIMBase lim_base)
    {
        super(lim_base);
    }

    /**
     * This method implements the RetrieveLocationForTelephoneNumber business
     * function. Created by: am9643
     * @return RetrieveLocationForTelephoneNumberReturn
     * @param aContext BisContext
     * @param aTelephoneNumber TelephoneNumber
     * @param aLocationPropertiesToGet LocationPropertiesToGetSeqOpt
     * @exception InvalidData An input parameter contained invalid data.
     * @exception AccessDenied Access to the specified domain object or
     *            information is not allowed.
     * @exception BusinessViolation The attempted action violates a business
     *            rule.
     * @exception SystemFailure The method could not be completed due to system
     *            level errors (e.g., out of memory, loss of network
     *            connectivity)n.
     * @exception NotImplemented The method has not been implemented.
     * @exception ObjectNotFound The desired domain object could not be found.
     * @exception DataNotFound No data found.
     */
    public RetrieveLocationForTelephoneNumberReturn execute(
            TelephoneNumber telephoneNumber,
            LocationPropertiesToGetSeqOpt locationPropertiesToGet)
        throws BusinessViolation,
            ObjectNotFound,
            InvalidData,
            AccessDenied,
            SystemFailure,
            DataNotFound,
            NotImplemented
    {

        RetrieveLocationForTelephoneNumberReturn rv = null;
        ServiceAddressLocationI locationI = null;
        Properties tagValues = new Properties();
        String[] locationPropertiesToGetArray = null;

        setSaga("");
        limBase.setCurrentState("");

        limBase.log(LogEventId.AUDIT_TRAIL,
                        "RetrieveLocationForTelephonNumberTrans::execute()|ServiceAddressLocationI::retrieveLocationForTelephoneNumber()|PRE");
        
        limBase.log(LogEventId.INPUT_DATA, LIMIDLUtil.displayTelephoneNumberInput(telephoneNumber, locationPropertiesToGet));

        getRequestValidator().validateBisContext();
        getRequestValidator().validate(telephoneNumber);

        locationI = getServiceLocator().getServiceLocationI(
                new TN(telephoneNumber.aNpa + telephoneNumber.aNxx + telephoneNumber.aLine), this);

        try
        {
            locationPropertiesToGetArray = locationPropertiesToGet.theValue();
        }
        catch (Exception e)
        {
            locationPropertiesToGetArray = new String[0];
        }

        rv = locationI.retrieveLocationForTelephoneNumber(telephoneNumber, locationPropertiesToGetArray);

        if (rv == null)
        {
            exBldReslt = ExceptionBuilder.parseException(limBase.getCallerContext(), ruleFile, "",
                    LIMTag.CSV_TnServiceError, null, true, ExceptionBuilderRule.NO_DEFAULT, null, null,
                    limBase.myLogger.getBisLogger(), "LIM", Severity.UnRecoverable, null);
        }

        limBase.log(LogEventId.OUTPUT_DATA, LIMIDLUtil.display(rv.aServiceLocation));

        limBase.log(LogEventId.AUDIT_TRAIL,
        	"RetrieveLocationForTelephoneNumberTrans::execute()|ServiceAddressLocationI::retrieveLocationForTelephoneNumber()|POST");

        return rv;
    }

    /**
     * Return value m_validator
     * @return m_validator RetrieveLocationForTelephoneNumberValidator
     */
    private RetrieveLocationForTelephoneNumberValidator getRequestValidator()
    {
        if (m_validator == null)
        {
            m_validator = new RetrieveLocationForTelephoneNumberValidator(limBase);
        }
        return m_validator;
    }

}