//$Id: OvalsNspController.java,v 1.11 2008/02/29 23:24:01 jh9785 Exp $

package com.sbc.eia.bis.BusinessInterface.ovalsnsp;

import java.util.Hashtable;
import java.util.Properties;

import javax.jms.JMSException;
import javax.xml.bind.JAXBException;

import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilder;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilderRule;
import com.sbc.eia.bis.embus.service.access.ServiceException;
import com.sbc.eia.bis.embus.service.access.ServiceTimeOutException;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.bis.lim.jaxb.ovalsnsp.impl.OVALSNSPImpl;
import com.sbc.eia.bis.lim.jaxb.ovalsnsp.impl.OVALSNSPTypeImpl;
import com.sbc.eia.bis.lim.util.LIMBase;
import com.sbc.eia.bis.lim.util.LIMTag;
import com.sbc.eia.idl.bis_types.AccessDenied;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.bis_types.BusinessViolation;
import com.sbc.eia.idl.bis_types.DataNotFound;
import com.sbc.eia.idl.bis_types.InvalidData;
import com.sbc.eia.idl.bis_types.NotImplemented;
import com.sbc.eia.idl.bis_types.ObjectNotFound;
import com.sbc.eia.idl.bis_types.SystemFailure;
import com.sbc.eia.idl.types.Severity;
import com.sbc.embus.common.EMBusException;
import com.sbc.eia.bis.embus.service.access.EmbusServiceException;

/**
 * @author gg2712
 */
public class OvalsNspController
{
	private OvalsNspServiceHelper aOvalsNspServiceHelper;	
	private LIMBase limBase = null;
    private String ruleFile =null;
    	
	/**
	 * Constructor for OvalsNspController.
	 */
	public OvalsNspController(LIMBase limBase)
	{
		this.setLimBase(limBase);
	}

	/**
	 * Build the JAXB request object and forward to the EMBUS ServiceAccess wrapper classes.
	 * @parm retrieveLocReq OvalsNspRetrieveLocReq
	 * @return OVALSNSPTypeImpl
	 */
    protected OvalsNspResponseHelper processOvalsNspMsagValidation(OvalsNspRetrieveLocReq retrieveLocReq)
        throws
            InvalidData,
            AccessDenied,
            BusinessViolation,
            DataNotFound,
            SystemFailure,
            NotImplemented,
            ObjectNotFound
    {
        OvalsNspResponseHelper response = new OvalsNspResponseHelper();
        
        try
        {
            //TODO: Add implementation here
			OVALSNSPImpl ovalsNspRequest = 
				new OvalsNspRequestHelper(limBase).getOvalsNspMsagValidationRequest(retrieveLocReq);

			limBase.log(LogEventId.REMOTE_CALL, OVALSNSPTag.OVALS_NSP_MQ_BROKER, OVALSNSPTag.OVALS_NSP_MQ_SERVICE, OVALSNSPTag.OVALS_NSP, OVALSNSPTag.OVALS_NSP_MSAG_VALIDATION_TAG);
			
			OVALSNSPTypeImpl jaxbResp = getOvalsNspServiceHelper().sendOvalsNspMsagValidationRequest(ovalsNspRequest);
            
            limBase.log(LogEventId.REMOTE_RETURN, OVALSNSPTag.OVALS_NSP_MQ_BROKER, OVALSNSPTag.OVALS_NSP_MQ_SERVICE, OVALSNSPTag.OVALS_NSP, OVALSNSPTag.OVALS_NSP_MSAG_VALIDATION_TAG);
            
            response.setOvalsNspMsagValidationResponse(jaxbResp);
            
        }
        catch (ServiceException serviceException)
        {
            handleServiceException(serviceException);
        }
        catch (Exception e)
        {
        	handleException(e);
        }
        
		return response;	   
    }
    
    /**
     * Method getOvalsNspHelper.
     * @return OvalsNspServiceHelper
     * @throws ServiceException
     */
    private OvalsNspServiceHelper getOvalsNspServiceHelper()
        throws ServiceException
    {
        if (aOvalsNspServiceHelper == null)
        {
            aOvalsNspServiceHelper = new OvalsNspServiceHelper(getProperties(), getLimBase());
        }
        return aOvalsNspServiceHelper;
    }
        
    /**
     * Method handleException examines the type of exception encountered and
     * while reponse accordingly.
     * @param e Exception the exception to be handled.

     */
    public void handleServiceException(ServiceException serviceException) 
        throws
            InvalidData,
            AccessDenied,
            BusinessViolation,
            SystemFailure,
            NotImplemented,
            ObjectNotFound,
            DataNotFound 
	{
        
        if (serviceException.getOriginalException() != null &&
        	 serviceException.getOriginalException() instanceof JAXBException) 
        {
            Properties tagValues = new Properties();
            tagValues.setProperty("SYSTEM", "OVALS NSP");
            tagValues.setProperty("MSG", serviceException.getOriginalException().getMessage());
    
        	ExceptionBuilder.parseException(
                getBisContext(),
                getLimBase().getLimRulesFile(),
                "",
                LIMTag.CSV_JAXBError,     
                "JAXB Exception",        
                true,
                ExceptionBuilderRule.NO_DEFAULT,
                null,
                null,
                getLimBase(),
                "OvalsNspServiceAccess",
                Severity.UnRecoverable,
                tagValues);
        } 
        else if (serviceException instanceof ServiceTimeOutException) 
        {
            Properties p = new Properties();
            p.setProperty("SYSTEM", "OVALS NSP");

            ExceptionBuilder.parseException(
                getBisContext(),
                getLimBase().getLimRulesFile(),
                "",
                LIMTag.CSV_LimTimedOutCode,
                "Time out",
                true,
                ExceptionBuilderRule.NO_DEFAULT,
                null,
                serviceException,
                limBase,
                "OvalsNspServiceAccess",
                Severity.Recoverable,
                p);
        } 
        else if (serviceException.getOriginalException() != null && 
        		  serviceException.getOriginalException() instanceof JMSException) 
        {
            String msg = "";
            
            if(serviceException.getOriginalException() != null)
            {
                msg = serviceException.getOriginalException().getMessage() ;
                msg = msg != null ? msg : "";
            }

			Properties p = new Properties();
            p.setProperty("MSG", msg );
            
            ExceptionBuilder.parseException(
                getBisContext(),
                getLimBase().getLimRulesFile(),
                "",
                LIMTag.CSV_InternalError,
                "JMS Exception",
                true,
                ExceptionBuilderRule.NO_DEFAULT,
                null,
                serviceException,
                getLimBase(),
                "OvalsNspServiceAccess",
                Severity.UnRecoverable,
                p);
        }  
        else if (serviceException instanceof EmbusServiceException)
        {
        	String msg = "";
            
            msg = "ErrorCode: " + ((EmbusServiceException) serviceException).getErrorCode() + " ,ErrorDescription: " 
            		+ ((EmbusServiceException) serviceException).getErrorDescription();

            Properties p = new Properties();
            p.setProperty("MSG", msg );
            
            ExceptionBuilder.parseException(
                getBisContext(),
                getLimBase().getLimRulesFile(),
                "",
                LIMTag.CSV_InternalError,
                "EMBus Exception",
                true,
                ExceptionBuilderRule.NO_DEFAULT,
                null,
                serviceException,
                getLimBase(),
                "OvalsNspServiceAccess",
                Severity.UnRecoverable,
                p);
        }
        else 
        {
            String msg = "";
            if(serviceException.getOriginalException() != null)
            {
                msg = serviceException.getMessage() ;
                msg = msg != null ? msg : "";
            }
                    	
            Properties p = new Properties();
            p.setProperty("MSG", msg);
        	
            ExceptionBuilder.parseException(
                getBisContext(),
                getLimBase().getOvalsNspRulesFile(),
                "",
                null,
                null,
                true,
                ExceptionBuilderRule.NO_DEFAULT,
                null,
                serviceException,
                getLimBase(),
                "OvalsNspServiceAccess",
                Severity.UnRecoverable,
                p);
        }
    }
    
	/**
	 * Handle catch all exceptions and rethrow then as SystemFailure exceptions  
	 * @param ex Exception Exceptions caught
     * @throws InvalidData
     * @throws AccessDenied
     * @throws BusinessViolation
     * @throws SystemFailure
     * @throws NotImplemented
     * @throws ObjectNotFound
     * @throws DataNotFound
	 */
	private void handleException(Exception e)
		throws 
		    InvalidData,
            AccessDenied,
            BusinessViolation,
            SystemFailure,
            NotImplemented,
            ObjectNotFound,
            DataNotFound 
	{
		e.printStackTrace();
		Properties p = new Properties();
        p.setProperty("MSG", e.getMessage() == null ? "no message" : e.getMessage());
       			
		ExceptionBuilder.parseException(
			getLimBase().getCallerContext(),
			getLimBase().getOvalsNspRulesFile(),
			"",
			null,
			null,
			true,
			ExceptionBuilderRule.NO_DEFAULT,
			null,
			null,
			getLimBase(),
			OVALSNSPTag.OVALS_NSP,
			Severity.UnRecoverable,
			p);
	}
   
	/**
	 * Returns the limBase.
	 * @return LIMBase
	 */
	public LIMBase getLimBase()
	{
		return limBase;
	}

	/**
	 * Sets the limBase.
	 * @param limBase The limBase to set
	 */
	public void setLimBase(LIMBase limBase)
	{
		this.limBase = limBase;
	}

    /**
     * Getter method for the BisContext.
     * @return BisContext
     */
    public BisContext getBisContext()
    {
        return limBase.getCallerContext();
    }
    
    /**
     * Returns the lim.properties file
     */
    private Hashtable getProperties()
    {
    	return limBase.getPROPERTIES();
    }
}
