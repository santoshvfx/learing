// $Id:$
package com.sbc.eia.bis.lim.util;

import java.util.Hashtable;
import java.util.Properties;

import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilder;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilderResult;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilderRule;
import com.sbc.eia.idl.bis_types.AccessDenied;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.bis_types.BusinessViolation;
import com.sbc.eia.idl.bis_types.DataNotFound;
import com.sbc.eia.idl.bis_types.InvalidData;
import com.sbc.eia.idl.bis_types.NotImplemented;
import com.sbc.eia.idl.bis_types.ObjectNotFound;
import com.sbc.eia.idl.bis_types.SystemFailure;
import com.sbc.eia.idl.types.Severity;
import com.sbc.eia.bis.authorization.ClientAuthorization;
import com.sbc.eia.bis.framework.logging.LogEventId;

/**
 * @author rz7367
 *
 * A class containing common code for validating BisContext fields.
 */
public class BisContextValidations 
{
	private LIMBase limBase = null;	
	protected static Hashtable authorizationProps = null;
	ExceptionBuilderResult exBldReslt = null;
	String ruleFile =null;
	
    /**
     * Constructor for BisContextValidations
     * @param lim_base LIMBase
     */
    public BisContextValidations (LIMBase lim_base)
    {
    	limBase = lim_base;
    	ruleFile = (String) limBase.getPROPERTIES().get(LIMTag.CSV_FileName_LIM);
    }
        
    /**
     * Edit BisContext for validity.
     * Creation date: (9/25/01 9:12:01 AM)
     * @exception InvalidData An input parameter contained invalid data.
     * @exception AccessDenied Access to the specified domain object or information is not allowed.
     * @exception BusinessViolation The attempted action violates a business rule.
     * @exception SystemFailure The method could not be completed due to system level errors (e.g., out of memory, loss of network connectivity)n.
     * @exception NotImplemented The method has not been implemented.
     * @exception ObjectNotFound The desired domain object could not be found.
     * @exception DataNotFound No data found.
     */
    public void editBisContext () 
    throws InvalidData, 
    	   SystemFailure, 
    	   BusinessViolation, 
    	   DataNotFound, 
    	   AccessDenied, 
    	   ObjectNotFound, 
    	   NotImplemented
    {
    	
    	LIMBisContextManager bisContextManager = new LIMBisContextManager (limBase.getCallerContext()); 
    	try{	
    		if ((bisContextManager.getCustomerName().trim()).length() == 0){
    			Properties tagValues = new Properties();
    			tagValues.setProperty("CLASS", BisContext.class.getName());
    			tagValues.setProperty("CUSTOMER NAME", bisContextManager.getCustomerName());
    			
    			exBldReslt =
    				ExceptionBuilder.parseException(
    					limBase.getCallerContext(),
    					ruleFile,
    					"",
    					LIMTag.CSV_EditError,
    					"Missing Customer Name",
    					true,
    					ExceptionBuilderRule.NO_DEFAULT,
    					null,
    					null,
    					limBase.myLogger.getBisLogger(),
    					"LIM",
    					Severity.UnRecoverable,
    					tagValues);
    					
    		}
    		
    	}catch(Exception e){
    		Properties tagValues = new Properties();
    			tagValues.setProperty("CLASS", BisContext.class.getName());
    			tagValues.setProperty("CUSTOMER NAME", "null");
    		ExceptionBuilder.parseException(
    		limBase.getCallerContext(),
    					ruleFile,
    					"",
    					LIMTag.CSV_EditError,
    					"Missing Customer Name",
    					true,
    					ExceptionBuilderRule.NO_DEFAULT,
    					null,
    					e, // printing stackTrace in BisLog
    					limBase.myLogger.getBisLogger(),
    					"LIM",
    					Severity.UnRecoverable,
    					tagValues);
    	}
    	
    	try{
    		// Creation date: (10/14/02:9:26 AM) - as2362
    		// Checks if the calling client application is allowed to access LIM BIS
    		if ((bisContextManager.getApplication().trim()).length() == 0)
    		{	
    			Properties tagValues = new Properties();
    					tagValues.setProperty("CLASS", BisContext.class.getName());
    					tagValues.setProperty("APPLICATION", "");
    					
    					exBldReslt =
    					ExceptionBuilder.parseException(
    						limBase.getCallerContext(),
    						ruleFile,
    						"",
    						LIMTag.CSV_EditError,
    						"Missing Application",
    						true,
    						ExceptionBuilderRule.NO_DEFAULT,
    						null,
    						null,
    						limBase.myLogger.getBisLogger(),
    						"LIM",
    						Severity.UnRecoverable,
    						tagValues);
    		}
    	}catch(NullPointerException e){
    		Properties tagValues = new Properties();
    					tagValues.setProperty("CLASS", BisContext.class.getName());
    					tagValues.setProperty("APPLICATION", "null");
    		ExceptionBuilder.parseException(
    					limBase.getCallerContext(),
    					ruleFile,
    					"",
    					LIMTag.CSV_EditError,
    					"Missing Application",
    					true,
    					ExceptionBuilderRule.NO_DEFAULT,
    					null,
    					e, // printing stackTrace in BisLog
    					limBase.myLogger.getBisLogger(),
    					"LIM",
    					Severity.UnRecoverable,
    					tagValues);
    	}
    	
    	String addressCassMaximumLengthPerLine = bisContextManager.getAddressCassMaximumLengthPerLine();
    	if (addressCassMaximumLengthPerLine != null) 
    	{
    		
    		
    		if (!limBase.allNumeric(addressCassMaximumLengthPerLine.trim())) 
    		{
    			
    			
    			Properties tagValues = new Properties();
    					tagValues.setProperty("CLASS", BisContext.class.getName());
    					tagValues.setProperty("MAXCASSCHARPERLINE", addressCassMaximumLengthPerLine.trim());
    			ExceptionBuilder.parseException(
    					limBase.getCallerContext(),
    					ruleFile,
    					"",
    					LIMTag.CSV_EditError,
    					"Invalid AddressMaxLengthPerLine",
    					true,
    					ExceptionBuilderRule.NO_DEFAULT,
    					null,
    					null, 
    					limBase.myLogger.getBisLogger(),
    					"LIM",
    					Severity.UnRecoverable,
    					tagValues);
    			
    		}
    		
    		
    	}
    }
    

    /**
     * checkLogging: Validate that logging information is populated in the BisContext.
     * @exception InvalidData An input parameter contained invalid data.
     * @exception AccessDenied Access to the specified domain object or information is not allowed.
     * @exception BusinessViolation The attempted action violates a business rule.
     * @exception SystemFailure The method could not be completed due to system level errors (e.g., out of memory, loss of network connectivity)n.
     * @exception NotImplemented The method has not been implemented.
     * @exception ObjectNotFound The desired domain object could not be found.
     * @exception DataNotFound No data found.
     */
    public void checkLogging () 
    throws InvalidData, 
    	   SystemFailure, 
    	   BusinessViolation, 
    	   DataNotFound, 
    	   AccessDenied, 
    	   ObjectNotFound, 
    	   NotImplemented
    {
    	
    	LIMBisContextManager bisContextManager = new LIMBisContextManager (limBase.getCallerContext()); 
    	try
    	{	
    		if ((bisContextManager.getLoggingInformationString().trim()).length() == 0)
    		{
    			Properties tagValues = new Properties();
    			tagValues.setProperty("CLASS", BisContext.class.getName());
    			tagValues.setProperty("LOGGING INFORMATION", bisContextManager.getLoggingInformationString());
    			
    			exBldReslt =
    				ExceptionBuilder.parseException(
    					limBase.getCallerContext(),
    					ruleFile,
    					"",
    					LIMTag.CSV_EditError,
    					"Missing Logging Information",
    					true,
    					ExceptionBuilderRule.NO_DEFAULT,
    					null,
    					null,
    					limBase.myLogger.getBisLogger(),
    					"LIM",
    					Severity.UnRecoverable,
    					tagValues);
    		}
    	}
    	catch(Exception e)
    	{
    		Properties tagValues = new Properties();
    			tagValues.setProperty("CLASS", BisContext.class.getName());
    			tagValues.setProperty("LOGGING INFORMATION", "null");
    		ExceptionBuilder.parseException(
    		limBase.getCallerContext(),
    					ruleFile,
    					"",
    					LIMTag.CSV_EditError,
    					"Missing Logging Information",
    					true,
    					ExceptionBuilderRule.NO_DEFAULT,
    					null,
    					e, // printing stackTrace in BisLog
    					limBase.myLogger.getBisLogger(),
    					"LIM",
    					Severity.UnRecoverable,
    					tagValues);
    	}    	
    }

    /**
     * checkConversionId: Validate that the conversion ID information is populated in the BisContext.
     * @exception InvalidData An input parameter contained invalid data.
     * @exception AccessDenied Access to the specified domain object or information is not allowed.
     * @exception BusinessViolation The attempted action violates a business rule.
     * @exception SystemFailure The method could not be completed due to system level errors (e.g., out of memory, loss of network connectivity)n.
     * @exception NotImplemented The method has not been implemented.
     * @exception ObjectNotFound The desired domain object could not be found.
     * @exception DataNotFound No data found.
     */
    public void checkConversionId () 
    throws InvalidData, 
    	   SystemFailure, 
    	   BusinessViolation, 
    	   DataNotFound, 
    	   AccessDenied, 
    	   ObjectNotFound, 
    	   NotImplemented
    {
    	
    	LIMBisContextManager bisContextManager = new LIMBisContextManager (limBase.getCallerContext()); 
    	try
    	{	
    		if ((bisContextManager.getConversationId().trim()).length() == 0)
    		{    			
    			exBldReslt =
    				ExceptionBuilder.parseException(
    					limBase.getCallerContext(),
    					ruleFile,
    					"",
    					LIMTag.CSV_EditError,
    					"Missing Data: Conversation ID",
    					true,
    					ExceptionBuilderRule.NO_DEFAULT,
    					null,
    					null,
    					limBase.myLogger.getBisLogger(),
    					"LIM",
    					Severity.UnRecoverable,
    					null);
    		}
    	}
    	catch(Exception e)
    	{
    		ExceptionBuilder.parseException(
    		limBase.getCallerContext(),
    					ruleFile,
    					"",
    					LIMTag.CSV_EditError,
    					"Missing Data: Conversation ID",
    					true,
    					ExceptionBuilderRule.NO_DEFAULT,
    					null,
    					e, // printing stackTrace in BisLog
    					limBase.myLogger.getBisLogger(),
    					"LIM",
    					Severity.UnRecoverable,
    					null);
    	}    	
    }


    /**
     * checkAuthorization: Validate if client is authorized client.
     * @param aState String
     * @param methodName String
     * @exception InvalidData An input parameter contained invalid data.
     * @exception AccessDenied Access to the specified domain object or information is not allowed.
     * @exception BusinessViolation The attempted action violates a business rule.
     * @exception SystemFailure The method could not be completed due to system level errors (e.g., out of memory, loss of network connectivity)n.
     * @exception NotImplemented The method has not been implemented.
     * @exception ObjectNotFound The desired domain object could not be found.
     * @exception DataNotFound No data found.
     */
    public void checkAuthorization (String aState, String methodName)
    throws InvalidData, 
           SystemFailure, 
           BusinessViolation, 
           DataNotFound, 
           AccessDenied, 
           ObjectNotFound, 
           NotImplemented
    {
        
        LIMBisContextManager bisContextManager = new LIMBisContextManager(limBase.getCallerContext());
        String applicationName = "";
        String busUnit = "";
        if (bisContextManager.getBusinessUnit() != null)
            busUnit = bisContextManager.getBusinessUnit().trim();
        if (bisContextManager.getApplication() != null)
            applicationName = bisContextManager.getApplication().trim();
        
        if (authorizationProps == null) 
        {
            authorizationProps = new Hashtable();
            authorizationProps.put( LIMTag.AUTHORIZATION_TIMEOUT_KEY, limBase.getPROPERTIES().get(LIMTag.AUTHORIZATION_TIMEOUT_KEY) );
            authorizationProps.put(LIMTag.DATA_SOURCE_NAME_KEY, limBase.getPROPERTIES().get(LIMTag.DATA_SOURCE_NAME_KEY));
            authorizationProps.put( LIMTag.USERID_KEY, limBase.getPROPERTIES().get(LIMTag.USERID_KEY));
            authorizationProps.put( LIMTag.PASSWORD_KEY, limBase.getPROPERTIES().get(LIMTag.PASSWORD_KEY) );
            authorizationProps.put( LIMTag.DRIVER_KEY, limBase.getPROPERTIES().get(LIMTag.DRIVER_KEY));
            authorizationProps.put( LIMTag.USE_CONNECTION_POOL_KEY, limBase.getPROPERTIES().get(LIMTag.USE_CONNECTION_POOL_KEY));
            authorizationProps.put( LIMTag.URL_KEY, limBase.getPROPERTIES().get(LIMTag.URL_KEY));
            authorizationProps.put( LIMTag.AUTHORIZATION_TABLE_KEY, LIMTag.AUTHORIZATION_TABLE_VALUE );
        }
        
        try {
            if ( !ClientAuthorization.isAuthorized(
                                methodName,
                                applicationName, //bisContextManager.getApplication().trim(),
                                busUnit, //bisContextManager.getBusinessUnit().trim(),
                                aState, 
                                LIMTag.EMPTY_STRING,
                                authorizationProps,
                                limBase)) 
            {
                                    
                        Properties tagValues = new Properties();
                        tagValues.setProperty("CLASS", BisContext.class.getName());
                        tagValues.setProperty("APPLNAME", applicationName);
                        tagValues.setProperty("BUSUNIT", busUnit);
                        
                        exBldReslt =
                        ExceptionBuilder.parseException(
                            limBase.getCallerContext(),
                            ruleFile,
                            "",
                            LIMTag.CSV_EditError,
                            "Application and BusinessUnit access denied",
                            true,
                            ExceptionBuilderRule.NO_DEFAULT,
                            null,
                            null,
                            limBase.myLogger.getBisLogger(),
                            "LIM",
                            Severity.UnRecoverable,
                            tagValues);                               
                                    
                                    
            } 
         
        } catch (AccessDenied ad)
        {
            throw ad;
        } catch (Throwable e) {
            
            limBase.log(LogEventId.ERROR, "Exception in Client Authorization  : \n" + e.toString());
            Properties tagValues = new Properties();
                        tagValues.setProperty("CLASS", BisContext.class.getName());
                        tagValues.setProperty("APPLNAME", applicationName);
                        tagValues.setProperty("BUSUNIT", busUnit);
                        
                        exBldReslt =
                        ExceptionBuilder.parseException(
                            limBase.getCallerContext(),
                            ruleFile,
                            "",
                            LIMTag.CSV_EditError,
                            "Application and BusinessUnit access denied",
                            true,
                            ExceptionBuilderRule.NO_DEFAULT,
                            null,
                            null,
                            limBase.myLogger.getBisLogger(),
                            "LIM",
                            Severity.UnRecoverable,
                            tagValues);
            
        }   
    }
}
