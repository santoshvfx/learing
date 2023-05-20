//$Id: BCAM.java,v 1.9 2008/07/08 15:43:43 jh9785 Exp $

package com.sbc.eia.bis.BusinessInterface.bcam;

import java.rmi.RemoteException;
import java.util.Properties;

import com.sbc.eia.idl.bimx.BimxFacade;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.lim_types.Address;
import com.sbc.eia.idl.lim_types.AddressOpt;
import com.sbc.eia.idl.types.SeverityOpt;
import com.sbc.eia.idl.types.Severity;
import com.sbc.eia.idl.types.StringOpt;
import com.sbc.eia.idl.types.CompositeObjectKey;
import com.sbc.eia.idl.lim.helpers.AddressHandler;
import com.sbc.eia.idl.lim.helpers.AddressHandlerException;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilder;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilderRule;
import com.sbc.eia.idl.bis_types.AccessDenied;
import com.sbc.eia.idl.bis_types.BusinessViolation;
import com.sbc.eia.idl.bis_types.DataNotFound;
import com.sbc.eia.idl.bis_types.InvalidData;
import com.sbc.eia.idl.bis_types.NotImplemented;
import com.sbc.eia.idl.bis_types.ObjectNotFound;
import com.sbc.eia.idl.bis_types.SystemFailure;
import com.sbc.eia.idl.lim.UpdateBillingAddressReturn;
import com.sbc.eia.bis.lim.util.LIMBase;
import com.sbc.eia.bis.lim.util.LIMBisContextManager;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.bis.RmNam.utilities.BimxClient.BimxClient;
import com.sbc.eia.idl.bim_types.AddNotesToBillingAccountReturn;
import com.sbc.eia.idl.exception_types.ExceptionCode;
import com.sbc.eia.bis.lim.util.LIMTag;
import com.sbc.eia.idl.types.ExceptionData;
import com.sbc.eia.bis.lim.util.LIMIDLUtil;

/**
 * This is the BCAM BusinessInterface class for update billing address.
 */
public class BCAM
{
    private LIMBase limBase = null;
    private BcamRequestHelper requestHelper = null;
    private BcamResponseHelper responseHelper = null;
    private BcamService service = null;
    private String aProviderURL = null;
    private String aBimxHome = null;
	
	/**
     * Constructor
     * @param m_LimBase LIMBase
     */
    public BCAM(LIMBase m_LimBase) 
    {
        limBase = m_LimBase;
    }
    
    /**
     * Getter method for the PROPERTIES
     * @return java.util.Hashtable
     */
    public java.util.Hashtable getProperties() 
    {
    	return limBase.getPROPERTIES();
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
     * Update Billing Address in BCAM. Optionally post note in BIMX BIS.
     * @param aBillingAccountKey CompositeObjectKey
     * @param aOldAddress AddressOpt
     * @param aNewAddress Address
     * @param aDeliveryPointValidationCode StringOpt
     * @param aContactName StringOpt
     * @return UpdateBillingAddressReturn
     * @throws InvalidData
     * @throws AccessDenied
     * @throws BusinessViolation
     * @throws DataNotFound
     * @throws SystemFailure
     * @throws NotImplemented
     * @throws ObjectNotFound
     */
    public UpdateBillingAddressReturn updateBillingAddress(
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
        UpdateBillingAddressReturn ubaReturn = null;
        
        AddressHandler newAddress = null;
        try
        {
            newAddress = new AddressHandler(aNewAddress);
        }
        catch (AddressHandlerException ahe){}
            
        String requestXML = getBcamRequestHelper().getXMLRequest(aBillingAccountKey, newAddress, aDeliveryPointValidationCode);
            
        limBase.log(LogEventId.INFO_LEVEL_1, "XML before sending to BCAM: \n" + requestXML);
        
        String response = "";
        try 
        { 
            limBase.log (LogEventId.AUDIT_TRAIL, "BCAM::updateBillingAddress()|BcamService::processXMLRequest()|PRE");
            response = getBcamService().processXMLRequest(requestXML);
            limBase.log (LogEventId.AUDIT_TRAIL, "BCAM::updateBillingAddress()|BcamService::processXMLRequest()|POST");
            limBase.log(LogEventId.INFO_LEVEL_1, "XML responded from BCAM: \n" + response);
        } 
        catch(BcamServiceException e) 
        { 
            if (e.isDataNotFound())
            {
                Properties tagValues = new Properties();
                tagValues.setProperty("MSG", e.getMessage());
        
            	ExceptionBuilder.parseException(
            	    limBase.getCallerContext(),
                    limBase.getBcamRulesFile(),
                    "",
                    LIMTag.CSV_InternalError,     
                    "BCAM Data Not Found",        
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
                Properties tagValues = new Properties();
                if (e.getMessage() != null)
                	tagValues.setProperty("MSG", e.getMessage());
                else 
                	tagValues.setProperty("MSG", "BCAM: SYSTEM FAILURE");
        
            	ExceptionBuilder.parseException(
            	    limBase.getCallerContext(),
                    limBase.getBcamRulesFile(),
                    "",
                    LIMTag.CSV_InternalError,     
                    "Unexpected BCAM Error Encountered",        
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
              
        ubaReturn = getBcamResponseHelper().processResponseXML(response);
            
        LIMBisContextManager bisContextManager = new LIMBisContextManager(getBisContext());
        try
        {
            if (getBcamResponseHelper().getReturnCode().equals("S") && 
                (bisContextManager.getBypassBossNotesPosting() == null ||   
    		     !bisContextManager.getBypassBossNotesPosting().trim().equalsIgnoreCase("true")))
        	{
            	if (getBcamResponseHelper().getRegionProviderId().equals("P") ||
                    getBcamResponseHelper().getRegionProviderId().equals("S"))
            	{
                	//Only for West and SouthWest. Call BIMX
                	AddressHandler oldAddress = null;
                	try
                	{
                    	oldAddress = new AddressHandler(aOldAddress.theValue());
                	}
                	catch (AddressHandlerException ahe){}
                
                	limBase.log(LogEventId.AUDIT_TRAIL,"BCAM::updateBillingAddress()|BCAM::postNoteInBimx()|PRE");
                	boolean isFailedInCallingBimx = false;
                	ExceptionData exceptionInfo = new ExceptionData("", "", new StringOpt(), new SeverityOpt());
                	isFailedInCallingBimx = postNoteInBimx(
                    							bisContextManager,
                    							getBcamResponseHelper().getRegionProviderId(),
                    							aBillingAccountKey,
                    							oldAddress,
                    							newAddress,
                    							aContactName,
                    							exceptionInfo);
                
                	limBase.log(LogEventId.AUDIT_TRAIL,"BCAM::updateBillingAddress()|BCAM::postNoteInBimx()|POST");
                
                	if (isFailedInCallingBimx)
                	{
                    	StringBuffer tempMsg = new StringBuffer();
                    	
                    	//aCode = 1 
                	    //aMessage = Warning: billing address update sent to BCAM but BIMX call for BOSS notes posting failed. BIMX code: <xxxx>; BIMX message: <xxx>
                    	ubaReturn.aCode = 1;
                
                    	tempMsg.append("Warning: billing address update was sent to BCAM but notes posting to BOSS was not completed. Account: <")
                	   		   .append(aBillingAccountKey.aKeys[0].aValue.trim())
                	   		   .append(">. BIMX code: <")
                	   		   .append(exceptionInfo.aCode)
                	   		   .append(">. BIMX message: <")
                	   		   .append(exceptionInfo.aDescription)
                	   		   .append(">");
                
                    	ubaReturn.aMessage = tempMsg.toString();
                    	tempMsg = null;
                	}
                	else
                	{
                    	ubaReturn.aMessage = "Success: BOSS notes posted per client request";
                	}
            	}
            	else
            	{
                	//For other Region like AIT and EAST, return a warning message
            	    //aCode = 1, 
            	    //aMessage = Warning: billing address update sent to BCAM but LIM did not call BIMX. Account is in a region that is currently out of scope
                	StringBuffer tempMsg = new StringBuffer();
                
                	ubaReturn.aCode = 1;
                
                	tempMsg.append("Warning: billing address update was sent to BCAM but notes posting to BOSS was not completed. Account: <")
                	   	   .append(aBillingAccountKey.aKeys[0].aValue.trim())
                	       .append("> is in a region that is currently out of scope for BOSS notes posting: Account regionProviderId: <")
                	       .append(getBcamResponseHelper().getRegionProviderId())
                	   	   .append(">");
                
                	ubaReturn.aMessage = tempMsg.toString();
                	tempMsg = null;
            	}   
        	}
        }
        finally
        {
            bisContextManager = null;
        }
        
        return ubaReturn;
    }
    
    /**
     * getBcamRequestHelper
     * @return BcamRequestHelper
     */
    private BcamRequestHelper getBcamRequestHelper()
    {
        if (requestHelper == null)
        {
            requestHelper = new BcamRequestHelper(limBase);
        }
        
        return requestHelper;
    }
    
    /**
     * getBcamResponseHelper
     * @return BcamResponseHelper
     */
    private BcamResponseHelper getBcamResponseHelper()
    {
        if (responseHelper == null)
        {
            responseHelper = new BcamResponseHelper(limBase);
        }
        
        return responseHelper;
    }
    
    /**
     * getBcamService
     * @return BcamService
     */
    private BcamService getBcamService()
    {
        if (service == null)
        {
            service = new BcamService(limBase);
        }
        
        return service;
    }
    
    /**
     * Format input data and call BIMX for note posting
     * @param bisContextManager LIMBisContextManager
     * @param region String
     * @param aBillingAccountKey CompositeObjectKey
     * @param ahOldAddress AddressHandler
     * @param adNewAddress AddressHandler
     * @param aContactName StringOpt
     * @param exception ExceptionData
     * @throws InvalidData
     * @throws AccessDenied
     * @throws BusinessViolation
     * @throws DataNotFound
     * @throws SystemFailure
     * @throws NotImplemented
     * @throws ObjectNotFound
     */
    private boolean postNoteInBimx(
            		LIMBisContextManager bisContextManager,
            		String region,
            		CompositeObjectKey aBillingAccountKey,
            		AddressHandler ahOldAddress,
            		AddressHandler ahNewAddress,
            		StringOpt aContactName,
            		ExceptionData exception)
    {
        boolean isFailed = false;
      
        BimxRequestHelper bimxRequestHelper = new BimxRequestHelper(
													limBase,
													bisContextManager,
													region,
													ahOldAddress,
													ahNewAddress,
													aContactName);
        boolean callBIMX = false;   
        try
        {
            BimxFacade bimxBean = getBimxInterface();
            
            AddNotesToBillingAccountReturn bimxReturn = new AddNotesToBillingAccountReturn();
            
            limBase.log(LogEventId.REMOTE_CALL, aProviderURL, 
                    aBimxHome, "BIMX", "addNotesToBillingAccount");
            limBase.log(LogEventId.INFO_LEVEL_1, "BIMX Input Data BTN: " + aBillingAccountKey.aKeys[0].aValue);
            callBIMX = true;
            bimxReturn = bimxBean.addNotesToBillingAccount(
                    			bimxRequestHelper.createBisContext(),
                    			aBillingAccountKey,
                    			bimxRequestHelper.createNoteType(),
                    			bimxRequestHelper.createNoteText(),
                    			bimxRequestHelper.createNoteProperties());
            
            limBase.log(LogEventId.REMOTE_RETURN, aProviderURL, 
                    aBimxHome, "BIMX", "addNotesToBillingAccount. Return Data:" + LIMIDLUtil.displayBIMXOutput(bimxReturn));
            limBase.log(LogEventId.INFO_LEVEL_1, "BIMX Returned Data: " + LIMIDLUtil.displayBIMXOutput(bimxReturn));
        }
        /*catch (RemoteException e)
        {
            isFailed = true;
            exception.aCode = ExceptionCode.ERR_BIMXCL_REMOTE_EXCEPTION;
            exception.aDescription = "RemoteExeption from calling BIMX.addNotesToBillingAccount()";
            exception.aOrigination.theValue("BIMX");
            exception.aSeverity.theValue(Severity.UnRecoverable);
            limBase.log(LogEventId.FAILURE, exception.aCode, exception.aDescription, exception.aOrigination.theValue());
        }	*/
        catch(NotImplemented e)
        {
            isFailed = true;
            processBimxException(exception, e.aExceptionData, callBIMX);
        }
        catch(SystemFailure e)
        {
            isFailed = true;
            processBimxException(exception, e.aExceptionData, callBIMX);
        }
        catch(InvalidData e) 
        {
            isFailed = true;
            processBimxException(exception, e.aExceptionData, callBIMX);
        }
        catch(ObjectNotFound e) 
        {
            isFailed = true;
            processBimxException(exception, e.aExceptionData, callBIMX);
        }
        catch(DataNotFound e) 
        {
            isFailed = true;
            processBimxException(exception, e.aExceptionData, callBIMX);
        }
        catch(AccessDenied e) 
        {
            isFailed = true;
            processBimxException(exception, e.aExceptionData, callBIMX);
        }
        catch(BusinessViolation e) 
        {
            isFailed = true;
            processBimxException(exception, e.aExceptionData, callBIMX);
        }
        catch (Exception e)
        {
            isFailed = true;
            exception.aCode = ExceptionCode.ERR_LIM_UNEXPECTED_ERROR;
            exception.aDescription = "UnKnown Exception from calling BIMX.addNotesToBillingAccount(). Error: " + e.getMessage();
            exception.aOrigination.theValue("BIMX");
            exception.aSeverity.theValue(Severity.UnRecoverable);
            limBase.log(LogEventId.FAILURE, exception.aCode, exception.aDescription, exception.aOrigination.theValue());
        }
        finally
        {
            bimxRequestHelper = null;
        }
        
        return isFailed;
    }
    
    /**
	 * Returns the Bimx interface by instantiating BimxClient object.
	 * @throws InvalidData
     * @throws AccessDenied
     * @throws BusinessViolation
     * @throws DataNotFound
     * @throws SystemFailure
     * @throws NotImplemented
     * @throws ObjectNotFound
	 */
	private BimxFacade getBimxInterface()
		throws
		ObjectNotFound,
		DataNotFound,
		NotImplemented,
		SystemFailure,
		BusinessViolation,
		InvalidData,
		AccessDenied 
    { 
	    aProviderURL = (String) limBase.getPROPERTIES().get("BIMX_PROVIDER_URL");
	    aBimxHome = (String) limBase.getPROPERTIES().get("BIMX_Home");
	    
	    if (aBimxHome == null || aBimxHome.trim().length() == 0)
	    {  
	        limBase.throwException(
	                ExceptionCode.ERR_LIM_INTERNAL,
	                "Missing BIMX Home Information in properties file",
	                "LIM",
	                Severity.UnRecoverable);
	    }
	    
	    BimxClient bimxClient = new BimxClient();
//	            						aProviderURL,
//	            						aBimxHome,
//	            						"LIM",
//	            						(String) limBase.getPROPERTIES().get("INITIAL_CONTEXT_PROPERTIES_FILE"));
	    
	    limBase.log(LogEventId.AUDIT_TRAIL,"BCAM::getBimxInterface()|BimxClient::getBimxConnection()|PRE");
        BimxFacade retBimxBean = bimxClient.getBimxConnection(getBisContext(), limBase, ExceptionCode.ERR_LIM_INTERNAL, LIMTag.BIMX_INTF_LOOKUP_ERROR);
        limBase.log(LogEventId.AUDIT_TRAIL,"BCAM::getBimxInterface()|BimxClient::getBimxConnection()|POST");
	    
        return retBimxBean;
    }
	
	 /**
	 * Format Exception Data from BIMX
	 * @param newException ExceptionData
	 * @param oldException ExceptionData
	 * @param callBimx boolean
	 */
	private void processBimxException(ExceptionData newException, 
	        						  ExceptionData oldException,
	        						  boolean callBimx)
	{
	    newException.aCode = oldException.aCode;
	    newException.aDescription = oldException.aDescription;
	    newException.aOrigination = oldException.aOrigination;
	    newException.aSeverity = oldException.aSeverity;
        if (callBimx)
        {
            try
            {
                if (newException.aOrigination.theValue().trim().length() > 0)
                    limBase.log(LogEventId.FAILURE, newException.aCode, newException.aDescription, newException.aOrigination.theValue());
                else
                    limBase.log(LogEventId.FAILURE, newException.aCode, newException.aDescription, "BIMX");
            }
            catch (Exception bo)
            {
                limBase.log(LogEventId.FAILURE, newException.aCode, newException.aDescription, "BIMX");
            }
        }
	}
}
