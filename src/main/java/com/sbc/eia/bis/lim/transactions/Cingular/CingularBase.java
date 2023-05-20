//$Id: CingularBase.java,v 1.4 2008/01/18 23:04:30 jd3462 Exp $
package com.sbc.eia.bis.lim.transactions.Cingular;

import java.util.ArrayList;
import java.util.Properties;

import javax.jms.JMSException;
import javax.xml.bind.JAXBException;

import com.sbc.bccs.utilities.exceptionbuilder.CodeElement;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilder;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilderResult;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilderRule;
import com.sbc.eia.bis.embus.service.access.ReceiveTimeOutException;
import com.sbc.eia.bis.embus.service.access.ServiceException;
import com.sbc.eia.bis.embus.service.atlas.AtlasException;
import com.sbc.eia.bis.embus.service.atlas.AtlasService;
import com.sbc.eia.bis.embus.service.atlas.AtlasSoapFaultException;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.bis.lim.util.LIMBase;
import com.sbc.eia.bis.lim.util.LIMTag;
import com.sbc.eia.idl.bis_types.AccessDenied;
import com.sbc.eia.idl.bis_types.BusinessViolation;
import com.sbc.eia.idl.bis_types.DataNotFound;
import com.sbc.eia.idl.bis_types.InvalidData;
import com.sbc.eia.idl.bis_types.MultipleExceptions;
import com.sbc.eia.idl.bis_types.NotImplemented;
import com.sbc.eia.idl.bis_types.ObjectNotFound;
import com.sbc.eia.idl.bis_types.SystemFailure;
import com.sbc.eia.idl.types.Severity;
import com.sbc.embus.common.EMBusException;

/**
 * @author jd3462
 *
 * This is the base transaction class for Cingular transactions, which currently
 * are RetrieveServiceAreaByPostalCode and DetermineShippingPriorityAvailability
 * 
 */
public class CingularBase
{
    private LIMBase m_limBase = null;
    private String m_ruleFile = null;
    protected AtlasService m_service = null;
    private ExceptionBuilderResult m_exBldReslt = null;

    /**
     *  Logging Parameters
     */
    public static final String ATLAS = "ATLAS";
    public static final String ATLAS_CONTROLLER = "AtlasController";
    public static final String ATLAS_MQ_BROKER = "AtlasRCMQBroker";
    public static final String ATLAS_MQ_SERVICE = "AtlasRCMQService";;
    public static final String SERVICE_AREA_BY_POSTALCODE = "RetrieveServiceAreaByPostalCode";
    public static final String CONVERSATION_ID = "ConversationID";
    public static final String JMS_CORRELATION_ID = "JMSCorrelationID";
    
    /**
     * Atlas Errors parameters
     */
    public static final String ERR_300_SERIES_PREFIX = "300";
    public static final int ERR_CODE_LENGTH = 11;
    
    /**
     * Method names to be used in checkAuthorization
     */
    public static final String METHOD_RETRIEVE_SVC_AREA_BY_POSTALCODE  = "retrieveServiceAreaByPostalCode" ;
    public static final String METHOD_DETERMINE_SHIPPING_PRIORITY_AVAILABILITY  = "determineShippingPriorityAvailability" ;

	/**
	 * Constructor for CingularBase.
	 */
	public CingularBase(LIMBase newLimBase)
	{
        m_limBase = newLimBase;
	}

    /**
     * Method setAtlasService.
     * @return AtlasService
     */
    public AtlasService setAtlasService ()
    throws InvalidData, 
            AccessDenied, 
            BusinessViolation,
            ObjectNotFound,
            SystemFailure,
            DataNotFound,
            MultipleExceptions,
            NotImplemented
    {
        try 
        {
            Properties p = new Properties();    
            p.setProperty ("ATLAS_CLIENT_CONFIG_FILE_NAME",
                ((Properties)(getLimBase().getPROPERTIES())).getProperty("ATLAS_CLIENT_CONFIG_FILE_NAME"));
            p.setProperty ("ATLAS_ENVIRONMENT_NAME", 
                ((Properties)(getLimBase().getPROPERTIES())).getProperty("ATLAS_ENVIRONMENT_NAME"));
            m_service = new AtlasService (p, getLimBase());
        }
        catch (AtlasException e) 
        {
            handleAtlasException(e);
        }
        catch(AtlasSoapFaultException e)
        {
            handleAtlasSoapFaultException(e);
        }
        catch (ServiceException e) 
        {
            handleServiceException(e);
        }
        catch (Exception e) 
        {
            handleException(e);
        }
        return m_service;
    }
        
    /**
     * Method handleAtlasSoapFaultException.
     * @param ase
     * @exception SystemFailure The method could not be completed due to system level errors.
     * @exception InvalidData An input parameter contained invalid data.
     * @exception BusinessViolation The attempted action violates a business rule.
     * @exception ObjectNotFound The desired domain object could not be found.
     * @exception MultipleExceptions
     */
    protected void handleAtlasSoapFaultException(AtlasSoapFaultException ase)
    throws SystemFailure, 
            InvalidData, 
            BusinessViolation, 
            ObjectNotFound, 
            MultipleExceptions 
    {
        try
        {
            m_ruleFile = (String) m_limBase.getPROPERTIES ().get (LIMTag.CSV_FileName_CINGULAR);
            // Check if MultipleExceptions is to be thrown
            if (ase.getServiceProviderEntityList().size() == 0) 
            {
                ExceptionBuilderResult exBldReslt = null;
                Properties tagValues = new Properties();
                tagValues.setProperty("MSG", ase.getMessage()== null ? "No message available." : ase.getMessage());
                exBldReslt =
                    ExceptionBuilder.parseException(
                        m_limBase.getCallerContext(),
                        m_ruleFile,
                        LIMTag.EMPTY_STRING,
                        null,
                        ase.getCode(), 
                        true,
                        ExceptionBuilderRule.NO_DEFAULT,
                        null,
                        null,
                        m_limBase,
                        ATLAS,
                        Severity.UnRecoverable,
                        tagValues);
            }
            else if (ase.getServiceProviderEntityList().size() == 1) 
            {
                AtlasSoapFaultException.ServiceProviderEntity ent = 
                    (AtlasSoapFaultException.ServiceProviderEntity)ase.getServiceProviderEntityList().get(0);                       
                
                Properties tagValues = new Properties();
                tagValues.setProperty("MSG", ent.getFaultDescription());
                
                ExceptionBuilderResult exBldReslt = null;
    
                exBldReslt =
                    ExceptionBuilder.parseException(
                        m_limBase.getCallerContext(),
                        m_ruleFile,
                        LIMTag.EMPTY_STRING,
                        LIMTag.CSV_RCodeError,
                        ent.getFaultCode(),
                        true,
                        ExceptionBuilderRule.NO_DEFAULT,
                        null,
                        null,
                        m_limBase,
                        ATLAS,
                        Severity.UnRecoverable,
                        tagValues);
            }
            else
            {
                //Handle Multiple Exceptions
                ArrayList aCodeList = new ArrayList();
                
                for( int i = 0; i <  ase.getServiceProviderEntityList().size() ; i++ )
                {
                    AtlasSoapFaultException.ServiceProviderEntity ent = 
                        (AtlasSoapFaultException.ServiceProviderEntity)ase.getServiceProviderEntityList().get(i);
                    
                    //Log original error code from Atlas/Cingular
                    m_limBase.log(LogEventId.FAILURE, 
                        "Cingular Multiple Exceptions|Original Error Code[" + i + "]: " + ent.getFaultCode());

                    aCodeList.add(new CodeElement(LIMTag.CSV_RCodeError, ent.getFaultCode(), ent.getFaultDescription()));
                }   

                //ExceptionBuilderResult exBldReslt = null;
                ExceptionBuilder.parseMultipleException(
                    m_limBase.getCallerContext(),
                    m_ruleFile,
                    null,
                    aCodeList,
                    ExceptionBuilderRule.NO_DEFAULT,
                    null,
                    m_limBase,
                    null,
                    null,
                    null);      
            }
        }
        catch (DataNotFound e) 
        {
            m_limBase.log(LogEventId.EXCEPTION, "Cingular Exception::" + e.getMessage());
        }
        catch (AccessDenied e) 
        {
            m_limBase.log(LogEventId.EXCEPTION, "Cingular Exception::" + e.getMessage());
        }
        catch (NotImplemented e) 
        {
            m_limBase.log(LogEventId.EXCEPTION, "Cingular Exception::" + e.getMessage());
        }
    }
    

    /**
     * Method handleAtlasException.
     * @param ae AtlasException
     * @throws SystemFailure
     * @throws InvalidData An input parameter contained invalid data.
     * @throws BusinessViolation The attempted action violates a business rule.
     * @throws ObjectNotFound The desired domain object could not be found.
     */
    protected void handleAtlasException (AtlasException ae)
    throws SystemFailure, 
            InvalidData, 
            BusinessViolation, 
            ObjectNotFound 
    {
        try
        {
            m_ruleFile = (String) m_limBase.getPROPERTIES ().get (LIMTag.CSV_FileName_LIM);
            int errCodeEndIndex = 0;
            if(ae.getMessage() != null)
            {   
                //The Original error code is found in the beginning of the 
                //string before the occurence of '['
                errCodeEndIndex = ae.getMessage().indexOf('[');
            }
            else
            {
                errCodeEndIndex = ae.getExceptionCode().length();
            }
            if(errCodeEndIndex == 3)
            {
                Properties p = new Properties();
                p.setProperty("MSG", ae.getMessage()== null ? "No message available." : ae.getMessage());
     
                ExceptionBuilder.parseException(
                    m_limBase.getCallerContext(),
                    m_ruleFile,
                    "",
                    LIMTag.CSV_InternalError,
                    "EMBus Exception",
                    true,
                    ExceptionBuilderRule.NO_DEFAULT,
                    null,
                    ae,
                    m_limBase,
                    ATLAS,
                    Severity.UnRecoverable,
                    p);
            }
            else if (errCodeEndIndex == 4)
            {
                Properties p = new Properties();
                p.setProperty("MSG",  ae.getMessage()== null ? "No message available." : ae.getMessage());
     
                ExceptionBuilder.parseException(
                    m_limBase.getCallerContext(),
                    m_ruleFile,
                    "",
                    LIMTag.CSV_InternalError,
                    "Resource Connector Exception",
                    true,
                    ExceptionBuilderRule.NO_DEFAULT,
                    null,
                    ae,
                    m_limBase,
                    ATLAS,
                    Severity.UnRecoverable,
                    p);
            }
        }
        catch (DataNotFound e) 
        {
            m_limBase.log(LogEventId.EXCEPTION, "Cingular Exception::" + e.getMessage());
        }
        catch (AccessDenied e) 
        {
            m_limBase.log(LogEventId.EXCEPTION, "Cingular Exception::" + e.getMessage());
        }
        catch (NotImplemented e) 
        {
            m_limBase.log(LogEventId.EXCEPTION, "Cingular Exception::" + e.getMessage());
        }
    }
    
    /**
     * Method handleServiceException.
     * @param serviceException
     * @throws InvalidData
     * @throws AccessDenied
     * @throws BusinessViolation
     * @throws SystemFailure
     * @throws NotImplemented
     * @throws ObjectNotFound
     * @throws DataNotFound
     */
    protected void handleServiceException(ServiceException serviceException) 
    throws InvalidData,
            AccessDenied,
            BusinessViolation,
            SystemFailure,
            NotImplemented,
            ObjectNotFound,
            DataNotFound {
           
        ExceptionBuilderResult exBldReslt = null;  
        m_ruleFile = (String) m_limBase.getPROPERTIES ().get (LIMTag.CSV_FileName_LIM);
    
        if ( serviceException.getOriginalException() != null && serviceException.getOriginalException() instanceof JAXBException ) 
        {
            Properties tagValues = new Properties();
            tagValues.setProperty("MSG", serviceException.getOriginalException().getMessage());

            exBldReslt =
            ExceptionBuilder.parseException(
                m_limBase.getCallerContext(),
                m_ruleFile,
                "",
                LIMTag.CSV_AddressError,
                "XML Parsing Exception",
                true,
                ExceptionBuilderRule.NO_DEFAULT,
                null,
                null,
                m_limBase,
                ATLAS,
                Severity.UnRecoverable,
                tagValues);
        } else if ( serviceException instanceof ReceiveTimeOutException ) {
            Properties p = new Properties();
            p.setProperty("SYSTEM", "Atlas/Cingular Host System");

            ExceptionBuilder.parseException(
                m_limBase.getCallerContext(),
                m_ruleFile,
                "",
                LIMTag.CSV_LimTimedOutCode,
                "Time out",
                true,
                ExceptionBuilderRule.NO_DEFAULT,
                null,
                serviceException,
                m_limBase,
                ATLAS,
                Severity.Recoverable,
                p);
             
        } else if ( serviceException.getOriginalException() != null && serviceException.getOriginalException() instanceof JMSException ) {
            Properties p = new Properties();
            p.setProperty("MSG", serviceException.getOriginalException().getMessage());
         
            ExceptionBuilder.parseException(
                m_limBase.getCallerContext(),
                m_ruleFile,
                "",
                LIMTag.CSV_InternalError,
                "JMS Exception",
                true,
                ExceptionBuilderRule.NO_DEFAULT,
                null,
                serviceException,
                m_limBase,
                ATLAS,
                Severity.UnRecoverable,
                p);
        } else if ( serviceException.getOriginalException() instanceof EMBusException ) {
            Properties p = new Properties();
            p.setProperty("MSG", serviceException.getMessage());
         
            ExceptionBuilder.parseException(
                m_limBase.getCallerContext(),
                m_ruleFile,
                "",
                LIMTag.CSV_InternalError,
                "EMBus Exception",
                true,
                ExceptionBuilderRule.NO_DEFAULT,
                null,
                serviceException,
                m_limBase,
                ATLAS,
                Severity.UnRecoverable,
                p);
        } else {
            Properties p = new Properties();
            p.setProperty("MSG", serviceException.getMessage());
        
            ExceptionBuilder.parseException(
                m_limBase.getCallerContext(),
                m_ruleFile,
                "",
                null,
                null,
                true,
                ExceptionBuilderRule.NO_DEFAULT,
                null,
                serviceException,
                m_limBase,
                ATLAS,
                Severity.UnRecoverable,
                p);
            }
        }
        
    /**
     * Method handleException examines the type of exception encountered and
     * while reponse accordingly.
     * @param e Exception the exception to be handled.
     * @throws InvalidData
     * @throws AccessDenied
     * @throws BusinessViolation
     * @throws SystemFailure
     * @throws NotImplemented
     * @throws ObjectNotFound
     * @throws DataNotFound
     */
    protected void handleException(Exception e) 
    throws InvalidData,
            AccessDenied,
            BusinessViolation,
            SystemFailure,
            NotImplemented,
            ObjectNotFound,
            DataNotFound {              

                
        m_ruleFile = (String) m_limBase.getPROPERTIES ().get (LIMTag.CSV_FileName_CINGULAR);
        
        Properties p = new Properties();
        p.setProperty("MSG", e.getMessage()==null ? "Error message is null" : e.getMessage());
                        
        ExceptionBuilder.parseException(
            m_limBase.getCallerContext(),
            m_ruleFile,
            "",
            null,
            null,
            true,
            ExceptionBuilderRule.NO_DEFAULT,
            null,
            e,
            m_limBase.myLogger.getBisLogger(),
            ATLAS,
            Severity.UnRecoverable,
            p); 
    }
	/**
	 * Returns the limBase.
	 * @return LIMBase
	 */
	public LIMBase getLimBase()
	{
		return m_limBase;
	}

	/**
	 * Sets the limBase.
	 * @param limBase The limBase to set
	 */
	public void setLimBase(LIMBase limBase)
	{
		this.m_limBase = limBase;
	}

    /**
     * Returns the service.
     * @return AtlasService
     */
    public AtlasService getService()
    {
        return m_service;
    }

}
