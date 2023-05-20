//$Id: UpdateBillingAddressValidator.java,v 1.6 2008/02/26 21:00:35 jh9785 Exp $

package com.sbc.eia.bis.lim.transactions.UpdateBillingAddress;

import java.util.Properties;

import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilder;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilderResult;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilderRule;
import com.sbc.eia.idl.types.CompositeObjectKey;
import com.sbc.eia.idl.types.Severity;
import com.sbc.eia.bis.lim.util.LIMBase;
import com.sbc.eia.bis.lim.util.LIMBisContextManager;
import com.sbc.eia.bis.lim.util.LIMTag;
import com.sbc.eia.idl.bis_types.AccessDenied;
import com.sbc.eia.idl.bis_types.BusinessViolation;
import com.sbc.eia.idl.bis_types.DataNotFound;
import com.sbc.eia.idl.bis_types.InvalidData;
import com.sbc.eia.idl.bis_types.NotImplemented;
import com.sbc.eia.idl.bis_types.ObjectNotFound;
import com.sbc.eia.idl.bis_types.SystemFailure;
import com.sbc.eia.idl.lim_types.Address;
import com.sbc.eia.idl.lim_types.AddressOpt;
import com.sbc.eia.idl.lim.helpers.AddressHandler;
import com.sbc.eia.idl.lim.helpers.AddressHandlerException;
import com.sbc.eia.bis.lim.util.BisContextValidations;
import com.sbc.eia.idl.bis_types.BisContext;

/**
 * Contain validation rules for UpdateBillingAddress
 */
public class UpdateBillingAddressValidator 
{
    public static final String METHOD_UPDATE_BILLING_ADDR  = "updateBillingAddress" ;
    private LIMBase loc_LIMBase = null;
    private String ruleFile = null;
    private ExceptionBuilderResult exBldReslt = null;
    private AddressHandler ahNewAddress = null;
    private AddressHandler ahOldAddress = null;
    
    /**
     * Constructor
     * @param limBase LIMBase
     */
    public UpdateBillingAddressValidator(LIMBase limBase) 
    {
        loc_LIMBase = limBase;
        ruleFile = (String) loc_LIMBase.getPROPERTIES().get(LIMTag.CSV_FileName_LIM);
    }

    /**
     * This method implements the validations
     * Method validate.
     * @param aBillingAccountKey CompositeObjectKey
     * @param aNewAddress Address
     * @param aOldAddress Address
     */
    public void validate(CompositeObjectKey aBillingAccountKey,
            			 Address aNewAddress,
            			 AddressOpt aOldAddress)
        throws 
        	InvalidData,
        	AccessDenied,
        	BusinessViolation,
        	DataNotFound,
        	SystemFailure,
        	NotImplemented,
        	ObjectNotFound
    {
        Properties tagValues = new Properties();
        LIMBisContextManager bisContextManager = null;
        
        try
        {
            try
            {
                //BTN is required.
                if (aBillingAccountKey.aKeys[0].aValue.trim().length() == 0)
                {
        			exBldReslt =
        				ExceptionBuilder.parseException(
        				    loc_LIMBase.getCallerContext(),
        					ruleFile,
        					"",
        					LIMTag.CSV_EditError,
        					"Missing Billing Account",
        					true,
        					ExceptionBuilderRule.NO_DEFAULT,
        					null,
        					null,
        					loc_LIMBase.myLogger.getBisLogger(),
        					"LIM",
        					Severity.UnRecoverable,
        					tagValues);
                }
            }
            catch (org.omg.CORBA.BAD_OPERATION badop)
            {
                exBldReslt =
    				ExceptionBuilder.parseException(
    				    loc_LIMBase.getCallerContext(),
    					ruleFile,
    					"",
    					LIMTag.CSV_EditError,
    					"Missing Billing Account",
    					true,
    					ExceptionBuilderRule.NO_DEFAULT,
    					null,
    					null,
    					loc_LIMBase.myLogger.getBisLogger(),
    					"LIM",
    					Severity.UnRecoverable,
    					tagValues);
            }
            catch (NullPointerException npe)
            {
                exBldReslt =
    				ExceptionBuilder.parseException(
    				    loc_LIMBase.getCallerContext(),
    					ruleFile,
    					"",
    					LIMTag.CSV_EditError,
    					"Missing Billing Account",
    					true,
    					ExceptionBuilderRule.NO_DEFAULT,
    					null,
    					null,
    					loc_LIMBase.myLogger.getBisLogger(),
    					"LIM",
    					Severity.UnRecoverable,
    					tagValues);
            }
            
            try
            {
                //Billing Number Type is required for note posting.
                if (aBillingAccountKey.aKeys[0].aKind.trim().length() == 0)
                {
                    exBldReslt =
        				ExceptionBuilder.parseException(
        				    loc_LIMBase.getCallerContext(),
        					ruleFile,
        					"",
        					LIMTag.CSV_EditError,
        					"Missing Billing Account",
        					true,
        					ExceptionBuilderRule.NO_DEFAULT,
        					null,
        					null,
        					loc_LIMBase.myLogger.getBisLogger(),
        					"LIM",
        					Severity.UnRecoverable,
        					tagValues);
                }
            }
            catch (org.omg.CORBA.BAD_OPERATION badop)
            {
                exBldReslt =
    				ExceptionBuilder.parseException(
    				    loc_LIMBase.getCallerContext(),
    					ruleFile,
    					"",
    					LIMTag.CSV_EditError,
    					"Missing Billing Account",
    					true,
    					ExceptionBuilderRule.NO_DEFAULT,
    					null,
    					null,
    					loc_LIMBase.myLogger.getBisLogger(),
    					"LIM",
    					Severity.UnRecoverable,
    					tagValues);
            }
            catch (NullPointerException npe)
            {
                exBldReslt =
    				ExceptionBuilder.parseException(
    				    loc_LIMBase.getCallerContext(),
    					ruleFile,
    					"",
    					LIMTag.CSV_EditError,
    					"Missing Billing Account",
    					true,
    					ExceptionBuilderRule.NO_DEFAULT,
    					null,
    					null,
    					loc_LIMBase.myLogger.getBisLogger(),
    					"LIM",
    					Severity.UnRecoverable,
    					tagValues);
            }
            
    		try
            {
    		    //NewAddress is required
    		    //Make sure it's initialized
    		    aNewAddress.discriminator();
    		} 
            catch (org.omg.CORBA.BAD_OPERATION badop) 
            {
    			tagValues.clear();
    			tagValues.setProperty("MSG", Address.class.getName() + ": NewAddress");
    
    			exBldReslt =
    				ExceptionBuilder.parseException(
    				    loc_LIMBase.getCallerContext(),
    					ruleFile,
    					"",
    					LIMTag.CSV_EditError,
    					"Missing New Address Info",
    					true,
    					ExceptionBuilderRule.NO_DEFAULT,
    					null,
    					null,
    					loc_LIMBase.myLogger.getBisLogger(),
    					"LIM",
    					Severity.UnRecoverable,
    					tagValues);
    		}
            catch (NullPointerException npe)
            {
                tagValues.clear();
    			tagValues.setProperty("MSG", Address.class.getName() + ": NewAddress");
    
    			exBldReslt =
    				ExceptionBuilder.parseException(
    				    loc_LIMBase.getCallerContext(),
    					ruleFile,
    					"",
    					LIMTag.CSV_EditError,
    					"Missing New Address Info",
    					true,
    					ExceptionBuilderRule.NO_DEFAULT,
    					null,
    					null,
    					loc_LIMBase.myLogger.getBisLogger(),
    					"LIM",
    					Severity.UnRecoverable,
    					tagValues);
    		}
            
            boolean postNoteInBimx = false;
    		try
            {
    		    bisContextManager = new LIMBisContextManager(loc_LIMBase.getCallerContext());
    		    //If clients don't provide BisContext.BypassBossNotesPosting, default to false
    		    //OldAddress is required on posting note in BIMX
    		    if (bisContextManager.getBypassBossNotesPosting() == null ||   
    		        !bisContextManager.getBypassBossNotesPosting().trim().equalsIgnoreCase("true"))
    		    {
    		        aOldAddress.discriminator();
    		        aOldAddress.theValue().discriminator();
    		        postNoteInBimx = true;
    		    }
    		} 
            catch (org.omg.CORBA.BAD_OPERATION badop) 
            {
    			tagValues.clear();
    			tagValues.setProperty("MSG", Address.class.getName() + ": OldAddress");
    
    			exBldReslt =
    				ExceptionBuilder.parseException(
    				    loc_LIMBase.getCallerContext(),
    					ruleFile,
    					"",
    					LIMTag.CSV_EditError,
    					"Missing Old Address Info",
    					true,
    					ExceptionBuilderRule.NO_DEFAULT,
    					null,
    					null,
    					loc_LIMBase.myLogger.getBisLogger(),
    					"LIM",
    					Severity.UnRecoverable,
    					tagValues);
    		}
            catch (NullPointerException npe)
            {
                tagValues.clear();
    			tagValues.setProperty("MSG", Address.class.getName() + ": OldAddress");
    
    			exBldReslt =
    				ExceptionBuilder.parseException(
    				    loc_LIMBase.getCallerContext(),
    					ruleFile,
    					"",
    					LIMTag.CSV_EditError,
    					"Missing Old Address Info",
    					true,
    					ExceptionBuilderRule.NO_DEFAULT,
    					null,
    					null,
    					loc_LIMBase.myLogger.getBisLogger(),
    					"LIM",
    					Severity.UnRecoverable,
    					tagValues);
    		}
            
            BisContextValidations bisContextValidations = new BisContextValidations(loc_LIMBase);
            editBisContext(bisContextManager);
            bisContextValidations.checkAuthorization("", METHOD_UPDATE_BILLING_ADDR);
            
            ahOldAddress = null;
            ahNewAddress = null;
            
            if (postNoteInBimx)
            {
                //If need to post note in BIMX, the OldAddress needs to be validated.
                try
    			{
                    ahOldAddress = new AddressHandler(aOldAddress.theValue());
    			}
                catch (AddressHandlerException ahe)
                {
                    tagValues.clear();
        			tagValues.setProperty("MSG", Address.class.getName() + ": OldAddress");
        
        			exBldReslt =
        				ExceptionBuilder.parseException(
        				    loc_LIMBase.getCallerContext(),
        					ruleFile,
        					"",
        					LIMTag.CSV_EditError,
        					"Missing Old Address Info",
        					true,
        					ExceptionBuilderRule.NO_DEFAULT,
        					null,
        					null,
        					loc_LIMBase.myLogger.getBisLogger(),
        					"LIM",
        					Severity.UnRecoverable,
        					tagValues);
                }
                
                if (ahOldAddress.isFielded())
                {
                    //For Old Fielded Address, number, street name, city, state, and zip code are required.
                    StringBuffer tmpMsg = new StringBuffer();
                    boolean isFailed = false; 
                    if (ahOldAddress.getHouseNumber().length() == 0)
                    {
                        tmpMsg.append("HouseNumber,");
                        isFailed = true;
                    }
                    if (ahOldAddress.getStName().length() == 0)
                    {
                        tmpMsg.append("StreetName,");
                        isFailed = true;
                    }
                    if (ahOldAddress.getCity().length() == 0)
                    {
                        tmpMsg.append("City,");
                        isFailed = true;
                    }
                    if (ahOldAddress.getState().length() == 0)
                    {
                        tmpMsg.append("State,");
                        isFailed = true;
                    }
                    if (ahOldAddress.getPostalCode().length() == 0)
                    {
                        tmpMsg.append("PostalCode,");
                        isFailed = true;
                    }
                    if (isFailed)
                    {
                        tagValues.clear();
            			tagValues.setProperty("MSG", tmpMsg.toString());
            
            			exBldReslt =
            				ExceptionBuilder.parseException(
            				    loc_LIMBase.getCallerContext(),
            					ruleFile,
            					"",
            					LIMTag.CSV_EditError,
            					"Missing Old Address Info",
            					true,
            					ExceptionBuilderRule.NO_DEFAULT,
            					null,
            					null,
            					loc_LIMBase.myLogger.getBisLogger(),
            					"LIM",
            					Severity.UnRecoverable,
            					tagValues);
                    }
                }
                else
                {
                    boolean invalidOldAddress = false;
                    int lineCount = 0;
                    //For Old Unfielded Address, AddressLines must be populated at least one line.
                    if (ahOldAddress.m_addressLine != null &&
                        ahOldAddress.m_addressLine.length > 0)
                    {
                        for (int i = 0; i < ahOldAddress.m_addressLine.length; i++)
                        {
                            if (ahOldAddress.m_addressLine[i].length() > 0)
                                lineCount++;
                        }
                        
                        if(lineCount == 0)
                        {
                            invalidOldAddress = true;
                        }
                    }
                    else
                    {
                        invalidOldAddress = true;
                    }
                    
                    if (invalidOldAddress)
                    {
                        tagValues.clear();
            			tagValues.setProperty("MSG", "AddressLines");
            
            			exBldReslt =
            				ExceptionBuilder.parseException(
            				    loc_LIMBase.getCallerContext(),
            					ruleFile,
            					"",
            					LIMTag.CSV_EditError,
            					"Missing Old Address Info",
            					true,
            					ExceptionBuilderRule.NO_DEFAULT,
            					null,
            					null,
            					loc_LIMBase.myLogger.getBisLogger(),
            					"LIM",
            					Severity.UnRecoverable,
            					tagValues);
                    }
                    
                    //If all 5 AddressLines are populated, don't populated city, state, and postal code fields.
                    if (lineCount > 4)
                    {
                        if (ahOldAddress.getCity().length() > 0 ||
                            ahOldAddress.getState().length() > 0 ||
                            ahOldAddress.getPostalCode().length() > 0)
                        {
                            tagValues.setProperty("CLASS", Address.class.getName());
                            tagValues.setProperty("ADDRESSLINES", ahOldAddress.m_addressLine[4]);
                            tagValues.setProperty("CITY", ahOldAddress.getCity());
                            tagValues.setProperty("STATE", ahOldAddress.getState());
                            tagValues.setProperty("POSTALCODE", ahOldAddress.getPostalCode());
                                
                            exBldReslt =
                                ExceptionBuilder.parseException(
                                    loc_LIMBase.getCallerContext(),
                                    ruleFile,
                                    "",
                                    LIMTag.CSV_EditError,
                                    "Cannot Specify AddressLine with City or State or PostalCode",
                                    true,
                                    ExceptionBuilderRule.NO_DEFAULT,
                                    null,
                                    null,
                                    loc_LIMBase.myLogger.getBisLogger(),
                                    "LIM",
                                    Severity.UnRecoverable,
                                    tagValues);
                        }
                    }
                }
            }
            
            try
			{
                ahNewAddress = new AddressHandler(aNewAddress);
			}
            catch (AddressHandlerException ahe)
            {
                tagValues.clear();
    			tagValues.setProperty("MSG", Address.class.getName() + ": NewAddress");
    
    			exBldReslt =
    				ExceptionBuilder.parseException(
    				    loc_LIMBase.getCallerContext(),
    					ruleFile,
    					"",
    					LIMTag.CSV_EditError,
    					"Missing New Address Info",
    					true,
    					ExceptionBuilderRule.NO_DEFAULT,
    					null,
    					null,
    					loc_LIMBase.myLogger.getBisLogger(),
    					"LIM",
    					Severity.UnRecoverable,
    					tagValues);
            }
         
            boolean InvalidateBillName = false;
            
            if (ahNewAddress.getCassAddressLines() != null && 
                ahNewAddress.getCassAddressLines().length > 2)
            {
                int countline = 0;
                for (int i = 0; i < ahNewAddress.getCassAddressLines().length; i++)
                {
                    if (ahNewAddress.getCassAddressLines()[i] != null &&
                        ahNewAddress.getCassAddressLines()[i].trim().length() > 0)
                    {
                        countline++;
                    }
                }
                
                if (countline < 3)
                {
                    InvalidateBillName = true;
                }
            }
            else
            {
                InvalidateBillName = true;
            }
            
            if (!InvalidateBillName)
            {
                if (ahNewAddress.getAuxiliaryAddressLines() != null &&
                    ahNewAddress.getAuxiliaryAddressLines().length > 0)
                {
                    int countline = 0;
                    for (int i = 0; i < ahNewAddress.getAuxiliaryAddressLines().length; i++)
                    {
                        if (ahNewAddress.getAuxiliaryAddressLines()[i] != null &&
                            ahNewAddress.getAuxiliaryAddressLines()[i].trim().length() > 0)
                        {
                            countline++;
                        }
                    }
                
                    if (countline == 0)
                    {
                        InvalidateBillName = true;
                    }
                }
                else
                {
                    InvalidateBillName = true;
                }
            }
            
            if (InvalidateBillName)
            {
                tagValues.clear();
                tagValues.setProperty("MSG", "Bill Name must be provided");
                exBldReslt =
        			ExceptionBuilder.parseException(
        			    loc_LIMBase.getCallerContext(),
        				ruleFile,
        				"",
        				LIMTag.CSV_EditError,
        				"Missing Data: Bill Name",
        				true,
        				ExceptionBuilderRule.NO_DEFAULT,
        				null,
        				null,
        				loc_LIMBase.myLogger.getBisLogger(),
        				"LIM",
        				Severity.UnRecoverable,
        				tagValues);
            }
            
        }
        finally
        {
            bisContextManager = null;
            try
        	{
        		tagValues.clear();
        	}
        	catch (Exception e)
        	{
        		// no need to worry...
        	}		  
        }      
    }
    
    /**
     * Edit BisContext for validity.
     * @param bisContextManager LIMBisContextManager
     * @exception InvalidData An input parameter contained invalid data.
     * @exception AccessDenied Access to the specified domain object or information is not allowed.
     * @exception BusinessViolation The attempted action violates a business rule.
     * @exception SystemFailure The method could not be completed due to system level errors (e.g., out of memory, loss of network connectivity)n.
     * @exception NotImplemented The method has not been implemented.
     * @exception ObjectNotFound The desired domain object could not be found.
     * @exception DataNotFound No data found.
     */
    public void editBisContext(LIMBisContextManager bisContextManager) 
    	throws 
    		InvalidData, 
    		SystemFailure, 
    		BusinessViolation, 
    		DataNotFound, 
    		AccessDenied, 
    		ObjectNotFound, 
    		NotImplemented
    {
    	try
    	{	
    		if (bisContextManager.getCustomerName().trim().length() == 0)
    		{
    			Properties tagValues = new Properties();
    			tagValues.setProperty("CLASS", BisContext.class.getName());
    			
    			exBldReslt =
    				ExceptionBuilder.parseException(
    				    loc_LIMBase.getCallerContext(),
    					ruleFile,
    					"",
    					LIMTag.CSV_EditError,
    					"Missing Customer Name",
    					true,
    					ExceptionBuilderRule.NO_DEFAULT,
    					null,
    					null,
    					loc_LIMBase.myLogger.getBisLogger(),
    					"LIM",
    					Severity.UnRecoverable,
    					tagValues);
    		}
    	}
    	catch(Exception e)
    	{
    		Properties tagValues = new Properties();
    		tagValues.setProperty("CLASS", BisContext.class.getName());

    		exBldReslt =
    		    ExceptionBuilder.parseException(
    		        loc_LIMBase.getCallerContext(),
    		        ruleFile,
    		        "",
    		        LIMTag.CSV_EditError,
    		        "Missing Customer Name",
    		        true,
    		        ExceptionBuilderRule.NO_DEFAULT,
    		        null,
    		        e, // printing stackTrace in BisLog
    		        loc_LIMBase.myLogger.getBisLogger(),
    		        "LIM",
    		        Severity.UnRecoverable,
    		        tagValues);
    	}
    	
    	try
    	{
    		// Checks if the calling client application is allowed to access LIM BIS
    		if (bisContextManager.getApplication().trim().length() == 0)
    		{	
    			Properties tagValues = new Properties();
    			tagValues.setProperty("CLASS", BisContext.class.getName());
    					
    			exBldReslt =
    					ExceptionBuilder.parseException(
    					    loc_LIMBase.getCallerContext(),
    						ruleFile,
    						"",
    						LIMTag.CSV_EditError,
    						"Missing Application",
    						true,
    						ExceptionBuilderRule.NO_DEFAULT,
    						null,
    						null,
    						loc_LIMBase.myLogger.getBisLogger(),
    						"LIM",
    						Severity.UnRecoverable,
    						tagValues);
    		}
    	}
    	catch(Exception e)
    	{
    		Properties tagValues = new Properties();
    		tagValues.setProperty("CLASS", BisContext.class.getName());
    		
    		exBldReslt =
    		    ExceptionBuilder.parseException(
    		        loc_LIMBase.getCallerContext(),
    				ruleFile,
    				"",
    				LIMTag.CSV_EditError,
    				"Missing Application",
    				true,
    				ExceptionBuilderRule.NO_DEFAULT,
    				null,
    				e, // printing stackTrace in BisLog
    				loc_LIMBase.myLogger.getBisLogger(),
    				"LIM",
    				Severity.UnRecoverable,
    				tagValues);
    	}
    	
    	try
    	{
    		// BusinessUnit is required for BIMX note posting
    		if (bisContextManager.getBusinessUnit().trim().length() == 0)
    		{	
    			Properties tagValues = new Properties();
    			tagValues.setProperty("CLASS", BisContext.class.getName());
    					
    			exBldReslt =
    					ExceptionBuilder.parseException(
    					    loc_LIMBase.getCallerContext(),
    						ruleFile,
    						"",
    						LIMTag.CSV_EditError,
    						"Missing BusinessUnit",
    						true,
    						ExceptionBuilderRule.NO_DEFAULT,
    						null,
    						null,
    						loc_LIMBase.myLogger.getBisLogger(),
    						"LIM",
    						Severity.UnRecoverable,
    						tagValues);
    		}
    	}
    	catch(Exception e)
    	{
    		Properties tagValues = new Properties();
    		tagValues.setProperty("CLASS", BisContext.class.getName());
    		
    		exBldReslt =
    		    ExceptionBuilder.parseException(
    		        loc_LIMBase.getCallerContext(),
    				ruleFile,
    				"",
    				LIMTag.CSV_EditError,
    				"Missing BusinessUnit",
    				true,
    				ExceptionBuilderRule.NO_DEFAULT,
    				null,
    				e, // printing stackTrace in BisLog
    				loc_LIMBase.myLogger.getBisLogger(),
    				"LIM",
    				Severity.UnRecoverable,
    				tagValues);
    	}
    }
}
