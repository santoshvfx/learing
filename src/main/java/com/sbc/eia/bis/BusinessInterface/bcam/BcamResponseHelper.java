//$Id: BcamResponseHelper.java,v 1.7 2008/03/20 22:41:21 gg2712 Exp $

package com.sbc.eia.bis.BusinessInterface.bcam;

import java.util.Properties;
import javax.xml.bind.Marshaller;

import com.sbc.eia.bis.lim.util.LIMBase;
import com.sbc.eia.bis.lim.util.LIMTag;
import com.sbc.eia.bis.lim.jaxb.bcam.BCDRequestType;
import com.sbc.eia.bis.lim.util.JaxbXmlEncoderDecoder;
import com.sbc.eia.bis.lim.jaxb.bcam.impl.BCDResponseImpl;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilder;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilderRule;
import com.sbc.eia.bis.embus.service.access.ServiceException;
import com.sbc.eia.idl.bis_types.AccessDenied;
import com.sbc.eia.idl.bis_types.BusinessViolation;
import com.sbc.eia.idl.bis_types.DataNotFound;
import com.sbc.eia.idl.bis_types.InvalidData;
import com.sbc.eia.idl.bis_types.NotImplemented;
import com.sbc.eia.idl.bis_types.ObjectNotFound;
import com.sbc.eia.idl.bis_types.SystemFailure;
import com.sbc.eia.idl.types.Severity;
import com.sbc.eia.idl.lim.UpdateBillingAddressReturn;

/**
 * Process responded xml string from BCAM.
 */
public class BcamResponseHelper
{
    private LIMBase limBase = null;
    private String regionProviderId = "";
    private String accountId = "";
    private String returnCode = "";
    private String errorMessage = "";
    private JaxbXmlEncoderDecoder bcmsEncoderDecoder = null;
    
    /**
     * Constructor
     * @param m_LimBase LIMBase
     */
    public BcamResponseHelper(LIMBase m_LimBase) 
    {
        limBase = m_LimBase;  
    }
    
    /**
     * Process xml responded from BCAM. Format UpdateBillingAddressReturn
     * @return UpdateBillingAddressReturn
     * @param responseXml String
     * @throws SystemFailure
     * @throws InvalidData
     * @throws ObjectNotFound
     * @throws BusinessViolation
     * @throws AccessDenied
     * @throws NotImplemented
     * @throws DataNotFound
     */
    public UpdateBillingAddressReturn processResponseXML(String responseXml)
    	throws 
    		InvalidData, 
    		AccessDenied, 
    		BusinessViolation, 
    		DataNotFound, 
    		SystemFailure, 
    		NotImplemented, 
    		ObjectNotFound
    {  
        BCDResponseImpl bcdResponse = null;
        regionProviderId = "";
        accountId = "";
        returnCode = "";
        errorMessage = "";
        
        if (responseXml != null && responseXml.trim().length() > 0)
        {
            try
            {
                bcdResponse = (BCDResponseImpl) getEncodeDecode().decode(responseXml)[0];
            }
            catch (ServiceException e)
            {
                Properties tagValues = new Properties();
                tagValues.setProperty("SYSTEM", "BCAM");
                tagValues.setProperty("MSG", e.getOriginalException().getMessage());
        
            	ExceptionBuilder.parseException(
            	    limBase.getCallerContext(),
                    limBase.getLimRulesFile(),
                    "",
                    LIMTag.CSV_JAXBError,     
                    "JAXB Exception",        
                    true,
                    ExceptionBuilderRule.NO_DEFAULT,
                    null,
                    null,
                    limBase,
                    "BcamResponseHelper",
                    Severity.UnRecoverable,
                    tagValues);
            }
        }
        else
        {
            Properties tagValues = new Properties();
            tagValues.setProperty("SYSTEM", "BCAM");
            tagValues.setProperty("MSG", "BCAM returned a null or empty xml response");
    
        	ExceptionBuilder.parseException(
        	    limBase.getCallerContext(),
                limBase.getLimRulesFile(),
                "",
                LIMTag.CSV_JAXBError,     
                "JAXB Exception",        
                true,
                ExceptionBuilderRule.NO_DEFAULT,
                null,
                null,
                limBase,
                "BcamResponseHelper",
                Severity.UnRecoverable,
                tagValues);
        }
         
        if (bcdResponse != null && bcdResponse.getUpdateAddressResponse() != null)
        {
            if (bcdResponse.getUpdateAddressResponse().getRegionProviderId() != null)
            {
                regionProviderId = bcdResponse.getUpdateAddressResponse().getRegionProviderId().trim().toUpperCase();
            }
            
            if (bcdResponse.getUpdateAddressResponse().getAccountId() != null)
            {
                accountId = bcdResponse.getUpdateAddressResponse().getAccountId().trim();
            }
            
            if (bcdResponse.getUpdateAddressResponse().getReturnCode() != null)
            {
                returnCode = bcdResponse.getUpdateAddressResponse().getReturnCode().trim().toUpperCase();
            }
            
            if (bcdResponse.getUpdateAddressResponse().getErrorMessage() != null)
            {
                errorMessage = bcdResponse.getUpdateAddressResponse().getErrorMessage().trim();
            }
        }
        else if (bcdResponse != null && bcdResponse.getBCDError() != null)
        {
            if (bcdResponse.getBCDError().getRegionProviderId() != null)
            {
                regionProviderId = bcdResponse.getBCDError().getRegionProviderId().trim().toUpperCase();
            }
            
            if (bcdResponse.getBCDError().getAccountId() != null)
            {
                accountId = bcdResponse.getBCDError().getAccountId().trim();
            }
            
            if (bcdResponse.getBCDError().getErrorMessage() != null)
            {
                errorMessage = bcdResponse.getBCDError().getErrorMessage().trim();
            }
            
            returnCode = "F";
        } 
        
        if (!returnCode.equals("S"))
        {
            if (errorMessage.equalsIgnoreCase("Account Not Found"))
            {
                Properties tagValues = new Properties();
                tagValues.setProperty("MSG", errorMessage);
        
            	ExceptionBuilder.parseException(
            	    limBase.getCallerContext(),
                    limBase.getBcamRulesFile(),
                    "",
                    LIMTag.CSV_InternalError,     
                    "BCAM Account Not Found",        
                    true,
                    ExceptionBuilderRule.NO_DEFAULT,
                    null,
                    null,
                    limBase,
                    "BCAM",
                    Severity.UnRecoverable,
                    tagValues);
            }
            else if (errorMessage.equalsIgnoreCase("DUPLICATE LIVE ACCOUNTS"))
            {
                Properties tagValues = new Properties();
                tagValues.setProperty("MSG", errorMessage);
        
            	ExceptionBuilder.parseException(
            	    limBase.getCallerContext(),
                    limBase.getBcamRulesFile(),
                    "",
                    LIMTag.CSV_InternalError,     
                    "BCAM Duplicate Live Accounts",        
                    true,
                    ExceptionBuilderRule.NO_DEFAULT,
                    null,
                    null,
                    limBase,
                    "BCAM",
                    Severity.UnRecoverable,
                    tagValues);
            } 
            else if (errorMessage.equalsIgnoreCase("DUPLICATE INACTIVE ACCOUNTS"))
            {
                Properties tagValues = new Properties();
                tagValues.setProperty("MSG", errorMessage);
        
            	ExceptionBuilder.parseException(
            	    limBase.getCallerContext(),
                    limBase.getBcamRulesFile(),
                    "",
                    LIMTag.CSV_InternalError,     
                    "BCAM Duplicate Inactive Accounts",        
                    true,
                    ExceptionBuilderRule.NO_DEFAULT,
                    null,
                    null,
                    limBase,
                    "BCAM",
                    Severity.UnRecoverable,
                    tagValues);
            }                        
            else if (errorMessage.equalsIgnoreCase("Service Unavailable"))
            {
                Properties tagValues = new Properties();
                tagValues.setProperty("MSG", errorMessage);
        
            	ExceptionBuilder.parseException(
            	    limBase.getCallerContext(),
                    limBase.getBcamRulesFile(),
                    "",
                    LIMTag.CSV_InternalError,     
                    "BCAM Service Unavailable",        
                    true,
                    ExceptionBuilderRule.NO_DEFAULT,
                    null,
                    null,
                    limBase,
                    "BCAM",
                    Severity.UnRecoverable,
                    tagValues);
            }
            else if (errorMessage.equalsIgnoreCase("Invalid Request"))
            {
                Properties tagValues = new Properties();
                tagValues.setProperty("MSG", errorMessage);
        
            	ExceptionBuilder.parseException(
            	    limBase.getCallerContext(),
                    limBase.getBcamRulesFile(),
                    "",
                    LIMTag.CSV_InternalError,     
                    "BCAM Invalid Request",        
                    true,
                    ExceptionBuilderRule.NO_DEFAULT,
                    null,
                    null,
                    limBase,
                    "BCAM",
                    Severity.UnRecoverable,
                    tagValues);
            }
            else
            {
                //Anything else default to "Unknown Error"
                Properties tagValues = new Properties();
                tagValues.setProperty("MSG", errorMessage);
        
            	ExceptionBuilder.parseException(
            	    limBase.getCallerContext(),
                    limBase.getBcamRulesFile(),
                    "",
                    LIMTag.CSV_InternalError,     
                    "BCAM Unknown Error",        
                    true,
                    ExceptionBuilderRule.NO_DEFAULT,
                    null,
                    null,
                    limBase,
                    "BCAM",
                    Severity.UnRecoverable,
                    tagValues);
            }
        }
        
        return formatUpdateBillingAddressReturn();
    }
    
    /**
     * Initiate Encoder Decoder.
     */
    private JaxbXmlEncoderDecoder getEncodeDecode()
    {
        if (bcmsEncoderDecoder == null)
        {
            //Set the properties. 
    		Properties options = new Properties();
    		options.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, "true");
            
            bcmsEncoderDecoder = new JaxbXmlEncoderDecoder(limBase, 
										BCDRequestType.class.getPackage().getName(), 
										options);
        }
        
        return bcmsEncoderDecoder;
    }
    
    /**
     * Format and return UpdateBillingAddressReturn
     * @return UpdateBillingAddressReturn
     */
    private UpdateBillingAddressReturn formatUpdateBillingAddressReturn()
    {
        UpdateBillingAddressReturn ret = new UpdateBillingAddressReturn();
        
        if (returnCode.equalsIgnoreCase("S"))
        {
            ret = new UpdateBillingAddressReturn(limBase.getCallerContext(), (short) 0, "Success: bypassed BOSS notes posting per client request");
        }
        else
        {
            //It should not happen in here. For "F", it will throw an exception.
        }
        
        return ret;
    }
    
    /**
     * @return Returns the accountId.
     */
    public String getAccountId() 
    {
        return accountId;
    }
    
    /**
     * @return Returns the errorMessage.
     */
    public String getErrorMessage() 
    {
        return errorMessage;
    }
    
    /**
     * @return Returns the regionProviderId.
     */
    public String getRegionProviderId() 
    {
        return regionProviderId;
    }
    
    /**
     * @return Returns the returnCode.
     */
    public String getReturnCode() 
    {
        return returnCode;
    }
}
