//$Id: RetrieveLocationForServiceAddressTrans.java,v 1.8 2007/10/22 21:58:28 gg2712 Exp $

package com.sbc.eia.bis.lim.transactions.RetrieveLocationForServiceAddress;

import com.sbc.eia.bis.lim.transactions.common.RetrieveServiceLocationBase;
import com.sbc.eia.bis.BusinessInterface.location.ServiceAddressLocationI;
import com.sbc.eia.bis.lim.util.LIMBase;
import com.sbc.eia.bis.lim.util.LIMTag;
import com.sbc.eia.idl.bis_types.AccessDenied;
import com.sbc.eia.idl.bis_types.BusinessViolation;
import com.sbc.eia.idl.bis_types.DataNotFound;
import com.sbc.eia.idl.bis_types.InvalidData;
import com.sbc.eia.idl.bis_types.NotImplemented;
import com.sbc.eia.idl.bis_types.ObjectNotFound;
import com.sbc.eia.idl.bis_types.SystemFailure;
import com.sbc.eia.idl.lim.RetrieveLocationForServiceAddressReturn;
import com.sbc.eia.idl.lim_types.Address;
import com.sbc.eia.idl.lim_types.LocationPropertiesToGetSeqOpt;
import com.sbc.eia.idl.types.LongOpt;
import com.sbc.eia.idl.types.StringOpt;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilder;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilderRule;
import com.sbc.eia.idl.types.Severity;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.bis.lim.util.LIMIDLUtil;


/**
 * A new class to replace RetrieveLocationForAddressTrans.java
 * All common methods, which will be shared with RetrieveLocationForTelephoneNumber, were moved to RetrieveServiceLocationBase.java  
 */

public class RetrieveLocationForServiceAddressTrans extends RetrieveServiceLocationBase
{
    
    private RetrieveLocationForServiceAddressValidator m_validator = null;
    private String[] m_LocationPropertiesToGet = null;
    private String m_PreviousOwnerName = null;
    private String m_CrossBoundaryState = null; 

	/**
     * Constructor for RetrieveLocationForServiceAddressTrans.
     * @param lim_base LIMBase
     */
    public RetrieveLocationForServiceAddressTrans(LIMBase lim_base)
    {
        super (lim_base);
    }
    
    /**
     * This method implements the Retrieve Location By Address business function.
     * Creation date: jh9785 (7/22/07)
     * @return RetrieveLocationForServiceAddressReturn
     * @param aAddress Address
     * @param aLocationPropertiesToGet LocationPropertiesToGetSeqOpt
     * @param aPreviousOwnerName StringOpt
     * @param aMaxBasicAddressAlternatives LongOpt
     * @param aMaxLivingUnitAlternatives LongOpt
     * @exception InvalidData An input parameter contained invalid data.
     * @exception AccessDenied Access to the specified domain object or information is not allowed.
     * @exception BusinessViolation The attempted action violates a business rule.
     * @exception SystemFailure The method could not be completed due to system level errors (e.g., out of memory, loss of network connectivity)n.
     * @exception NotImplemented The method has not been implemented.
     * @exception ObjectNotFound The desired domain object could not be found.
     * @exception DataNotFound No data found.
     */
    public RetrieveLocationForServiceAddressReturn execute(
            Address aAddress,
            LocationPropertiesToGetSeqOpt aLocationPropertiesToGet,
            StringOpt aPreviousOwnerName,
            StringOpt aCrossBoundaryState,
            LongOpt aMaxBasicAddressAlternatives,
            LongOpt aMaxLivingUnitAlternatives)
        throws 
        	BusinessViolation,
            InvalidData,
            SystemFailure,
            ObjectNotFound,
            NotImplemented,
            DataNotFound,
            AccessDenied
    {
        RetrieveLocationForServiceAddressReturn rv = null;
        
        setSaga("");
        limBase.setCurrentState("");
        
        getRequestValidator().validate(aAddress, 
                					   aLocationPropertiesToGet, 
                					   aPreviousOwnerName, 
                					   aCrossBoundaryState,
                					   aMaxBasicAddressAlternatives, 
                					   aMaxLivingUnitAlternatives); 
        
        setSaga(getRequestValidator().getSaga());
        
		limBase.setSagInfoOnly(false);
		limBase.log(LogEventId.AUDIT_TRAIL, "RetrieveLocationForServiceAddressTrans::execute()|LocationI::retrieveLocationForServiceAddress()|PRE");

        ServiceAddressLocationI locationService = getServiceLocator().getServiceLocationI(getRequestValidator().getState(), getRequestValidator().getServiceCenter(), this);
        
        setPassingParameters(aLocationPropertiesToGet,
                			 aPreviousOwnerName,
                			 aCrossBoundaryState);
        
        rv = locationService.retrieveLocationForServiceAddress(
				aAddress,
				m_LocationPropertiesToGet,
				m_PreviousOwnerName,
				m_CrossBoundaryState,
                aMaxBasicAddressAlternatives.theValue(),
                aMaxLivingUnitAlternatives.theValue());
        
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
        
		limBase.log (LogEventId.OUTPUT_DATA, LIMIDLUtil.displayServiceAddressMatchResult(rv.aServiceAddressMatchResult));

        limBase.log(LogEventId.AUDIT_TRAIL, "RetrieveLocationForServiceAddressTrans::execute()|LocationI::retrieveLocationForServiceAddress()|POST");
        return rv;
    }

    /**
     * Return caching value m_validator
     * @return m_validator RetrieveLocationForServiceAddressValidator
     */
    private RetrieveLocationForServiceAddressValidator getRequestValidator()
    {
        if (m_validator == null)
        {
            m_validator = new RetrieveLocationForServiceAddressValidator(limBase);
        }
        return m_validator;
    } 
    
    /**+
     *
     * Convert to primary data type.
     * Handle CORBA.BAD_OPERATION and NullPointerException error
     * @param aLocationPropertiesToGet
     * @param aPreviousOwnerName
     * @param aCrossBoundaryState
     */
    private void setPassingParameters(LocationPropertiesToGetSeqOpt aLocationPropertiesToGet,
            						  StringOpt aPreviousOwnerName,
            						  StringOpt aCrossBoundaryState)
    {
        m_LocationPropertiesToGet = null;
        m_PreviousOwnerName = null;
        m_CrossBoundaryState = null;
        
        try 
        {
            m_LocationPropertiesToGet = aLocationPropertiesToGet.theValue();  
        } 
        catch (org.omg.CORBA.BAD_OPERATION e) {}
        catch (java.lang.NullPointerException e) {}
        
        try 
        {
            m_PreviousOwnerName = aPreviousOwnerName.theValue();  
        } 
        catch (org.omg.CORBA.BAD_OPERATION e) {}
        catch (java.lang.NullPointerException e) {}
        
        try 
        {
            m_CrossBoundaryState = aCrossBoundaryState.theValue();    
        } 
        catch (org.omg.CORBA.BAD_OPERATION e) {}
        catch (java.lang.NullPointerException e) {}
    }
}