// $Id: RetrieveLocationForAddressTrans.java,v 1.88 2008/10/28 20:40:53 jh9785 Exp $

package com.sbc.eia.bis.lim.transactions.RetrieveLocation;

import java.sql.SQLException;
import java.util.Properties;

import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilder;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilderRule;
import com.sbc.eia.bis.BusinessInterface.*;
import com.sbc.eia.bis.BusinessInterface.location.LocationI;
import com.sbc.eia.bis.framework.BisContextManager;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.bis.lim.util.BisContextValidations;
import com.sbc.eia.bis.lim.util.LIMBase;
import com.sbc.eia.bis.lim.util.LIMBisContextManager;
import com.sbc.eia.bis.lim.util.LIMIDLUtil;
import com.sbc.eia.bis.lim.util.LIMTag;
import com.sbc.eia.idl.bis_types.AccessDenied;
import com.sbc.eia.idl.bis_types.BusinessViolation;
import com.sbc.eia.idl.bis_types.DataNotFound;
import com.sbc.eia.idl.bis_types.InvalidData;
import com.sbc.eia.idl.bis_types.NotImplemented;
import com.sbc.eia.idl.bis_types.ObjectNotFound;
import com.sbc.eia.idl.bis_types.SystemFailure;
import com.sbc.eia.idl.lim.AddressMatchResult;
import com.sbc.eia.idl.lim.AddressMatchResultChoice;
import com.sbc.eia.idl.lim.RetrieveLocationForAddressReturn;
import com.sbc.eia.idl.lim.queries.RetrieveStateCodeByZip;
import com.sbc.eia.idl.lim_types.Address;
import com.sbc.eia.idl.lim_types.AddressChoice;
import com.sbc.eia.idl.lim_types.ProviderLocationProperties;
import com.sbc.eia.idl.lim_types.ProviderLocationPropertiesSeqOpt;
import com.sbc.eia.idl.types.LongOpt;
import com.sbc.eia.idl.types.Severity;
import com.sbc.eia.idl.types.StringOpt;
import com.sbc.bccs.idl.helpers.IDLUtil;
import com.sbc.eia.idl.lim_types.LocationPropertiesToGetValue;
import com.sbc.eia.idl.lim.helpers.AddressHandler;
import com.sbc.eia.idl.lim.helpers.AddressHandlerException;
import com.sbc.eia.bis.BusinessInterface.ovalsnsp.OVALSNSPTag;
import com.sbc.eia.bis.BusinessInterface.ovalsusps.OVALSUSPSTag;

/**
 * This is the Retrieve Location By Address Transaction class.
 * Creation date: (3/23/01 3:53:07 PM)
 * @author: David Prager
 */
public class RetrieveLocationForAddressTrans extends RetrieveLocationBase {
    
    private String ad_sign = "@ ";
    private AddressHandler ah = null;
    private String saveStName = "";
    private String serviceCenter = null;
    private String m_exchangeCode = "";

    /**
     * Retrieve Location for Address Transaction.
     * Creation date: (4/17/01 9:34:03 AM)
     * @param bisContext BisContext
     * @param properties Properties
     * @param logger BisLogger
     */
    public RetrieveLocationForAddressTrans (LIMBase lim_base)
    {
    	super (lim_base);
    }
    
    /**
     * Edit the Address input for missing data, invalid data, business violations.
     * Creation date: (7/13/01 12:10:29 PM)
     * @param addr AddressHandler
     * @exception InvalidData An input parameter contained invalid data.
     * @exception AccessDenied Access to the specified domain object or information is not allowed.
     * @exception BusinessViolation The attempted action violates a business rule.
     * @exception SystemFailure The method could not be completed due to system level errors (e.g., out of memory, loss of network connectivity)n.
     * @exception NotImplemented The method has not been implemented.
     * @exception ObjectNotFound The desired domain object could not be found.
     * @exception DataNotFound No data found.
     */
    private void editAddress(
        ProviderLocationPropertiesSeqOpt providerLocPropsSeqOpt)
    throws 
        InvalidData, 
    	SystemFailure, 
    	BusinessViolation, 
    	DataNotFound, 
    	AccessDenied, 
    	ObjectNotFound, 
    	NotImplemented 
    {
        LIMBisContextManager contextManager = new LIMBisContextManager(limBase.getCallerContext());
        String businessUnit = ""; 
        if (contextManager.getBusinessUnit() != null)
        {
            businessUnit = contextManager.getBusinessUnit();
        }
                                   
         // if going to USPS and is unfielded address
        if (processUsps)
        {
            // LIM EWBAV req doc section 3.1.2.2
            if (ah.getAddress().discriminator().value() == AddressChoice._UNFIELDED_ADDRESS)
            {
                if ((ah.m_addressLine.length > 4 && ah.m_addressLine[4] != null && ah.m_addressLine[4].length() > 0)  &&
                    (!ah.getCity().equals("")  || 
                    (!ah.getState().equals(""))  || 
                    (!ah.getPostalCode().equals(""))))
                { 
                    Properties tagValues = new Properties();
                    tagValues.setProperty("CLASS", Address.class.getName());
                    tagValues.setProperty("ADDRESSLINES", ah.m_addressLine[4]);
                    tagValues.setProperty("CITY", ah.getCity());
                    tagValues.setProperty("STATE", ah.getState());
                    tagValues.setProperty("POSTALCODE", ah.getPostalCode());
                        
                    exBldReslt =
                        ExceptionBuilder.parseException(
                        limBase.getCallerContext(),
                        ruleFile,
                        "",
                        LIMTag.CSV_EditError,
                        "Cannot Specify AddressLine with City or State or PostalCode",
                        true,
                        ExceptionBuilderRule.NO_DEFAULT,
                        null,
                        null,
                        limBase.myLogger.getBisLogger(),
                        "LIM",
                        Severity.UnRecoverable,
                        tagValues);
                }
                
                //LIM 13.0
                if(ah.m_addressLine.length == 0 && 
                        ah.getCity().length() == 0 &&
                        ah.getState().length() == 0 &&
                        ah.getPostalCode().length() == 0)
                {
                    Properties tagValues = new Properties();
        			tagValues.setProperty("MSG", "Missing address lines, city, state and zip information");
        
        			exBldReslt =
        				ExceptionBuilder.parseException(
        				    limBase.getCallerContext(),
        					ruleFile,
        					"",
        					LIMTag.CSV_EditError,
        					"Missing Address Data",
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
            
            // only check city, state and zip are populated with values
            // if the csz validation property is explicitly requested.
            if (req1 != null
                && !req1.getLocationPropertiesRequested().isRetrieveALL()
                && req1
                    .getLocationPropertiesRequested()
                    .isRetrieveCityStatePostalCodeValid())
            {
                if (ah.getCity() == null
                    || ah.getCity().equalsIgnoreCase("")
                    || ah.getState() == null
                    || ah.getState().equalsIgnoreCase("")
                    || ah.getPostalCode() == null
                    || ah.getPostalCode().equalsIgnoreCase(""))
                {
                    exBldReslt =
                        ExceptionBuilder.parseException(
                        limBase.getCallerContext(),
                        (String) limBase.getPROPERTIES().get(LIMTag.CSV_FileName_OVALS_USPS),
                        "",
                        LIMTag.CSV_EditError,
                        "Missing Required Data.",
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
             
        } //end if goToUsps
                                        
         // if going to OVALSNSP
        if (processNsp)
        {            
            // only check city, state and zip are populated with values
            // if AddressId is not populated.
            if (ah.getAddressId() == null || ah.getAddressId().equalsIgnoreCase(""))
            {
                if (ah.getPostalCode() == null || ah.getPostalCode().equalsIgnoreCase(""))
                {
                    Properties tagValues = new Properties();
                    tagValues.setProperty("CLASS", Address.class.getName());
                    
                    exBldReslt =
                        ExceptionBuilder.parseException(
                        limBase.getCallerContext(),
                        (String) limBase.getPROPERTIES().get(LIMTag.CSV_FileName_LIM),
                        "",
                        LIMTag.CSV_EditError,
                        "Missing Postal Code.",
                        true,
                        ExceptionBuilderRule.NO_DEFAULT,
                        null,
                        null,
                        limBase.myLogger.getBisLogger(),
                        "LIM",
                        Severity.UnRecoverable,
                        tagValues);
                }
                if (ah.getState() == null || ah.getState().equalsIgnoreCase(""))
                {
                    Properties tagValues = new Properties();
                    tagValues.setProperty("STATE", ah.getState());
                    exBldReslt =
                        ExceptionBuilder.parseException(
                        limBase.getCallerContext(),
                        (String) limBase.getPROPERTIES().get(LIMTag.CSV_FileName_LIM),
                        "",
                        LIMTag.CSV_EditError,
                        "Missing Data: Missing State Code",
                        true,
                        ExceptionBuilderRule.NO_DEFAULT,
                        null,
                        null,
                        limBase.myLogger.getBisLogger(),
                        "LIM",
                        Severity.UnRecoverable,
                        tagValues);
                }
                if (ah.getCity() == null|| ah.getCity().equalsIgnoreCase(""))
                {
                    Properties tagValues = new Properties();
                    tagValues.setProperty("CITY", ah.getCity());
                    exBldReslt =
                        ExceptionBuilder.parseException(
                        limBase.getCallerContext(),
                        (String) limBase.getPROPERTIES().get(LIMTag.CSV_FileName_LIM),
                        "",
                        LIMTag.CSV_EditError,
                        "Missing Data: City",
                        true,
                        ExceptionBuilderRule.NO_DEFAULT,
                        null,
                        null,
                        limBase.myLogger.getBisLogger(),
                        "LIM",
                        Severity.UnRecoverable,
                        tagValues);
                }
                //Validate street name and house number
                if (ah.getAddress().discriminator().value() == AddressChoice._FIELDED_ADDRESS)
                {
                    //without street name, but has house number 
                    //has street name, but without house number
                    //without street name, without house number 
                	if (ah.getStName().length() == 0 || ah.getHousNbr().length() == 0)
                    {
                        Properties tagValues = new Properties();
                        tagValues.clear();
                        tagValues.setProperty("CLASS", Address.class.getName());
    
                        exBldReslt =
                            ExceptionBuilder.parseException(
                            limBase.getCallerContext(),
                            ruleFile,
                            "",
                            LIMTag.CSV_EditError,
                            "Missing Address Info",
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
                //Validate AddressLine
                if (ah.getAddress().discriminator().value() == AddressChoice._UNFIELDED_ADDRESS)
            	{
            		boolean allzerolength = true;
            		try{
            				if (ah.m_addressLine == null || ah.m_addressLine.length == 0)
            				{
            					Properties tagValues = new Properties();
                    			tagValues.setProperty("CLASS", Address.class.getName());
                        
                    			exBldReslt =
    								ExceptionBuilder.parseException(
    								limBase.getCallerContext(),
    								ruleFile,
    								"",
    								LIMTag.CSV_EditError,
    								"Missing Address Info",
    								true,
    								ExceptionBuilderRule.NO_DEFAULT,
    								null,
    								null,
    								limBase.myLogger.getBisLogger(),
    								"LIM",
    								Severity.UnRecoverable,
    								tagValues);
            				}
            		
            				for (int i=0; i < ah.m_addressLine.length; i++)
            				{
            					if (ah.m_addressLine[i].trim().length() > 0)
            					{
            						allzerolength = false;
            						break;
            					}
            				}
            		
                			if (allzerolength)
                			{ 
                    			Properties tagValues = new Properties();
                    			tagValues.setProperty("CLASS", Address.class.getName());
                        
                    			exBldReslt =
    								ExceptionBuilder.parseException(
    								limBase.getCallerContext(),
    								ruleFile,
    								"",
    								LIMTag.CSV_EditError,
    								"Missing Address Info",
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
            		catch (org.omg.CORBA.BAD_OPERATION e) {
            			Properties tagValues = new Properties();
                    	tagValues.setProperty("CLASS", Address.class.getName());
                        
                    	exBldReslt =
    						ExceptionBuilder.parseException(
    						limBase.getCallerContext(),
    						ruleFile,
    						"",
    						LIMTag.CSV_EditError,
    						"Missing Address Info",
    						true,
    						ExceptionBuilderRule.NO_DEFAULT,
    						null,
    						null,
    						limBase.myLogger.getBisLogger(),
    						"LIM",
    						Severity.UnRecoverable,
    						tagValues);
            		}
           			catch (java.lang.NullPointerException e) {
           				Properties tagValues = new Properties();
                    	tagValues.setProperty("CLASS", Address.class.getName());
                        
                    	exBldReslt =
    						ExceptionBuilder.parseException(
    						limBase.getCallerContext(),
    						ruleFile,
    						"",
    						LIMTag.CSV_EditError,
    						"Missing Address Info",
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
            } //end null AddressId
        } //end if goToNsp
    }
    
    /**
     * Edit address for Business Violations.
     * Creation date: (7/13/01 12:10:29 PM)
     * @param addr AddressHandler
     * @exception InvalidData An input parameter contained invalid data.
     * @exception AccessDenied Access to the specified domain object or information is not allowed.
     * @exception BusinessViolation The attempted action violates a business rule.
     * @exception SystemFailure The method could not be completed due to system level errors (e.g., out of memory, loss of network connectivity)n.
     * @exception NotImplemented The method has not been implemented.
     * @exception ObjectNotFound The desired domain object could not be found.
     * @exception DataNotFound No data found.
     */
    private void editBusinessViolation () 
    throws 
        InvalidData, 
    	SystemFailure, 
    	BusinessViolation, 
    	DataNotFound, 
    	AccessDenied, 
    	ObjectNotFound, 
    	NotImplemented
    {
        Properties tagValues = new Properties();
    	
    	// examine house number
    	if (ah.getHousNbr().length()  > 0){
    		// descriptive address cannot be entered
    		if (ah.getAddAddrInfo().length() > 0)
    		{	
    			tagValues.clear();
    			tagValues.setProperty("CLASS", Address.class.getName());
    			tagValues.setProperty("SANO", ah.getHousNbr());
    			tagValues.setProperty("AAI", ah.getAddAddrInfo());
    
    			exBldReslt = 
    					ExceptionBuilder.parseException(
    						limBase.getCallerContext(),
    						ruleFile,
    						"",
    						LIMTag.CSV_EditError,
    						"Cannot specify House Number and Descriptive Address",
    						true,
    						ExceptionBuilderRule.NO_DEFAULT,
    						null,
    						null,
    						limBase.myLogger.getBisLogger(),
    						"LIM",
    						Severity.UnRecoverable,
    						tagValues);
    		}			
    
    		// assigned house number cannot be entered
    		if (ah.getAasgndHousNbr().length() > 0)
    		{
    			tagValues.clear();
    			tagValues.setProperty("CLASS", Address.class.getName());
    			tagValues.setProperty("SANO", ah.getHousNbr());
    			tagValues.setProperty("AHN", ah.getAasgndHousNbr());
    
    			exBldReslt = 
    					ExceptionBuilder.parseException(
    						limBase.getCallerContext(),
    						ruleFile,
    						"",
    						LIMTag.CSV_EditError,
    						"Cannot specify House Number and Assigned House Number",
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
    
    	// examine street name	
    	if (ah.getStName().length() > 0)
        {
    		// discriptive address cannot be entered
    		if (ah.getAddAddrInfo().length() > 0)
    		{
    			tagValues.clear();
    			tagValues.setProperty("CLASS", Address.class.getName());
    			tagValues.setProperty("SANA", ah.getStName());
    			tagValues.setProperty("AAI", ah.getAddAddrInfo());
    
    			exBldReslt = 
    					ExceptionBuilder.parseException(
    						limBase.getCallerContext(),
    						ruleFile,
    						"",
    						LIMTag.CSV_EditError,
    						"Cannot specify Street Name and Descriptive Address",
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
    
    	// examine Route	
    	if (ah.getRoute().length() > 0)
        {
    		// discriptive address cannot be entered
    		if (ah.getAddAddrInfo().length() > 0)
    		{
    			// ERR_LIM_STREETNAME_ROUTE_AAI_COMBO
    			tagValues.clear();
    			tagValues.setProperty("CLASS", Address.class.getName());
    			tagValues.setProperty("SANA", ah.getStName());
    			tagValues.setProperty("ROUTE", ah.getRoute());
    			tagValues.setProperty("AAI", ah.getAddAddrInfo());
    
    			exBldReslt = 
    					ExceptionBuilder.parseException(
    						limBase.getCallerContext(),
    						ruleFile,
    						"",
    						LIMTag.CSV_EditError,
    						"Cannot specify Street Name or Route and Descriptive Address",
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
    
    	/**
    	 * examine unnamed address validation. It's valid only for PB/SWBT PREMIS
    	 * Unnamed address is "@ , streetName". The "@ " has been removed by AddressHandler(),
    	 * just need to check for "," in the street name.
    	 */
    	if (ah.getStName().length() > 0)
        {
    	    if ( ah.getStName().charAt(0) == ',' ) 
    	    {
    			/**
    		 	 * If State is specified, use it. Otherwise, use the Service Center to validate the region.
    			 */
    			String sTmpState = serviceCenter ;
    			if (ah.getState().length() > 0 )
    				sTmpState = ah.getState();
    			
    			if ( ! limBase.validateState( sTmpState, limBase.REGIONS_PB_SWBT) ) 
    			{
    		 		String sTmpStName = "@ " + ah.getStName();
    				
    				tagValues.clear();
    				tagValues.setProperty("CLASS", Address.class.getName());
    				tagValues.setProperty("SANA", sTmpStName);
    				tagValues.setProperty("STATE", sTmpState);
    
    				exBldReslt = 
    					ExceptionBuilder.parseException(
    						limBase.getCallerContext(),
    						ruleFile,
    						"",
    						LIMTag.CSV_EditError,
    						"Unnamed Address validation",
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
    	tagValues.clear();
    }
    
    /**
     * Edit address for InvalidData exceptions.
     * Creation date: (7/13/01 12:10:29 PM)
     * @param addr AddressHandler
     * @exception InvalidData An input parameter contained invalid data.
     * @exception AccessDenied Access to the specified domain object or information is not allowed.
     * @exception BusinessViolation The attempted action violates a business rule.
     * @exception SystemFailure The method could not be completed due to system level errors (e.g., out of memory, loss of network connectivity)n.
     * @exception NotImplemented The method has not been implemented.
     * @exception ObjectNotFound The desired domain object could not be found.
     * @exception DataNotFound No data found.
     */
    private void editInvalidData ()
    throws	InvalidData,
    		SystemFailure,
    		BusinessViolation,
    		DataNotFound,
    		AccessDenied,
    		ObjectNotFound,
    		NotImplemented
    {
    	Properties tagValues = new Properties();

    	// if state is populated, make sure it is valid state
    	if ((!ah.getState().equalsIgnoreCase(""))
    		&& (!limBase.validateState(ah.getState(), limBase.REGIONS_ALL))
    		&& (!validateCompany("SBCT", ah.getState())))
        {
    		tagValues.clear();
    		tagValues.setProperty("CLASS", Address.class.getName());
    		tagValues.setProperty("STATE", ah.getState());
    
    		exBldReslt =
    			ExceptionBuilder.parseException(
    				limBase.getCallerContext(),
    				ruleFile,
    				"",
    				LIMTag.CSV_EditError,
    				"Invalid State",
    				true,
    				ExceptionBuilderRule.NO_DEFAULT,
    				null,
    				null,
    				limBase.myLogger.getBisLogger(),
    				"LIM",
    				Severity.UnRecoverable,
    				tagValues);
    	}
    
    	// if state is populated and is part of SBCT and city is also populated => no need for zip code
    	if ( !(!ah.getState().equals ("") && !ah.getCity().equals("") && serviceCenter.equals ("SBCT")))
     	{
            // check if postal code is populated
            if (ah.getPostalCode().length() == 0)
            {
                tagValues.clear();
                tagValues.setProperty("CLASS", Address.class.getName());

    			exBldReslt =
                    ExceptionBuilder.parseException(
    					limBase.getCallerContext(),
    					ruleFile,
    					"",
    					LIMTag.CSV_EditError,
    					"Missing Postal Code",
    					true,
    					ExceptionBuilderRule.NO_DEFAULT,
    					null,
    					null,
    					limBase.myLogger.getBisLogger(),
    					"LIM",
    					Severity.UnRecoverable,
    					tagValues);
    		 }
        
    	if (ah.getPostalCode().length() > 0)
        {
    		// check if postalCode numeric: must be 5 byte zipCode
    		if ((limBase.allNumeric(ah.getPostalCode())))
            {
    			if (!serviceCenter.equals("SBCT"))
                {
    				//check if zipCode right size
    				if (ah.getPostalCode().length() != 5)
                    {
    					tagValues.clear();
    					tagValues.setProperty("CLASS", Address.class.getName());
    					tagValues.setProperty("ZIP", ah.getPostalCode());
    
    					exBldReslt =
    						ExceptionBuilder.parseException(
    							limBase.getCallerContext(),
    							ruleFile,
    							"",
    							LIMTag.CSV_EditError,
    							"Invalid Postal Code",
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
    		} // end allNumeric
    		// check if postalCode is alpha: must be 2-4 byte saga 	
    		else
    			if (limBase.allAlpha(ah.getPostalCode()))
                { 
    				// check if saga right size 
    				if ((limBase.validateState(ah.getState(), limBase.REGIONS_PB_SWBT))
    					&& ((ah.getPostalCode().length() < 2) || (ah.getPostalCode().length() > 4)))
                    {
    					tagValues.clear();
    					tagValues.setProperty("CLASS", Address.class.getName());
    					tagValues.setProperty("ZIP", ah.getPostalCode());
    
    					exBldReslt =
    						ExceptionBuilder.parseException(
    							limBase.getCallerContext(),
    							ruleFile,
    							"",
    							LIMTag.CSV_EditError,
    							"Invalid SAGA or Postal Code",
    							true,
    							ExceptionBuilderRule.NO_DEFAULT,
    							null,
    							null,
    							limBase.myLogger.getBisLogger(),
    							"LIM",
    							Severity.UnRecoverable,
    							tagValues);
    
    				} else
    					if (limBase.validateState(ah.getState(), limBase.REGIONS_AIT))
                        {
    						if (!(ah.getPostalCode().length() == 1))
                            {
    							tagValues.clear();
    							tagValues.setProperty("CLASS", Address.class.getName());
    							tagValues.setProperty("ZIP", ah.getPostalCode());
    
    							exBldReslt =
    								ExceptionBuilder.parseException(
    									limBase.getCallerContext(),
    									ruleFile,
    									"",
    									LIMTag.CSV_EditError,
    									"Invalid SAGID or Postal Code",
    									true,
    									ExceptionBuilderRule.NO_DEFAULT,
    									null,
    									null,
    									limBase.myLogger.getBisLogger(),
    									"LIM",
    									Severity.UnRecoverable,
    									tagValues);
    						} else
    							if (ah.getCity().length() == 0) {
    								tagValues.clear();
    								tagValues.setProperty("CLASS", Address.class.getName());
    
    								exBldReslt =
    									ExceptionBuilder.parseException(
    										limBase.getCallerContext(),
    										ruleFile,
    										"",
    										LIMTag.CSV_EditError,
    										"Missing City",
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
    			} // end allAlpha
    		else 
            { // error postalCode is alphaNumeric
    			if (!serviceCenter.equals("SBCT")) 
                {
    				tagValues.clear();
    				tagValues.setProperty("CLASS", Address.class.getName());
    				tagValues.setProperty("ZIP", ah.getPostalCode());
    
    				exBldReslt =
    					ExceptionBuilder.parseException(
    						limBase.getCallerContext(),
    						ruleFile,
    						"",
    						LIMTag.CSV_EditError,
    						"Invalid Postal Code",
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
    	} // end if PostalCode.length > 0
        }
    
    	// examine house number
    	if ((!serviceCenter.equals("CT")) && (ah.getHousNbr().length() > 0)) {
    		// street name cannot be blank
    		if (ah.getStName().length() == 0) {
    			tagValues.clear();
    			tagValues.setProperty("CLASS", Address.class.getName());
    
    			exBldReslt =
    				ExceptionBuilder.parseException(
    					limBase.getCallerContext(),
    					ruleFile,
    					"",
    					LIMTag.CSV_EditError,
    					"Missing Street Name",
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
    
    	// valid address data not entered
    	if ((ah.getPostalCode().length() == 0)
    		&& (ah.getStName().length() == 0)
    		&& (ah.getAssignedHouseNumber().length() == 0)
    		&& (ah.getRoute().length() == 0)
    		&& (ah.getAddAddrInfo().length() == 0))
        {
    		tagValues.clear();
    		tagValues.setProperty("CLASS", Address.class.getName());
    
    		exBldReslt =
    			ExceptionBuilder.parseException(
    				limBase.getCallerContext(),
    				ruleFile,
    				"",
    				LIMTag.CSV_EditError,
    				"Missing Address Info",
    				true,
    				ExceptionBuilderRule.NO_DEFAULT,
    				null,
    				null,
    				limBase.myLogger.getBisLogger(),
    				"LIM",
    				Severity.UnRecoverable,
    				tagValues);
    	}
    
    	tagValues.clear();
    }
        
    /**
     * This method implements the Retrieve Location By Address business function.
     * Creation date: (3/23/01 3:59:21 PM)
     * @return RetrieveLocationForAddressReturn
     * @param aAddress Address
     * @param aMaximumBasicAddressReturnLimit LongOpt
     * @param aMaximumUnitReturnLimit LongOpt
     * @exception InvalidData An input parameter contained invalid data.
     * @exception AccessDenied Access to the specified domain object or information is not allowed.
     * @exception BusinessViolation The attempted action violates a business rule.
     * @exception SystemFailure The method could not be completed due to system level errors (e.g., out of memory, loss of network connectivity)n.
     * @exception NotImplemented The method has not been implemented.
     * @exception ObjectNotFound The desired domain object could not be found.
     * @exception DataNotFound No data found.
     */
    public RetrieveLocationForAddressReturn 	execute(
    	Address  aAddress,
        ProviderLocationPropertiesSeqOpt aPropertiesSeqOpt,
    	LongOpt  aMaximumBasicAddressReturnLimit,
    	LongOpt  aMaximumUnitReturnLimit,
        StringOpt aExchangeCode)
    throws
    	InvalidData,
    	AccessDenied,
    	BusinessViolation,
    	DataNotFound,
    	SystemFailure,
    	NotImplemented,
    	ObjectNotFound
    {
    	RetrieveLocationForAddressReturn rv = null;
    	LocationI locationI = null;
    	Properties tagValues = new Properties();
        ProviderLocationProperties[] aPropertiesToGet = null;
        RetrieveLocationHandler rlh = null;
        
        try 
        {
            if (!aMaximumBasicAddressReturnLimit.discriminator() ||
                aMaximumBasicAddressReturnLimit.theValue() < -1)
            {
                aMaximumBasicAddressReturnLimit.theValue(0);
            }
        } 
        catch (org.omg.CORBA.BAD_OPERATION e) 
        {
            aMaximumBasicAddressReturnLimit.theValue(0);
        }
        catch (java.lang.NullPointerException e) 
        {
        	aMaximumBasicAddressReturnLimit = new LongOpt();
            aMaximumBasicAddressReturnLimit.theValue(0);
        }
          
        try
        {  
            if (!aMaximumUnitReturnLimit.discriminator() ||
                 aMaximumUnitReturnLimit.theValue() < -1)
            {
                aMaximumUnitReturnLimit.theValue(0);
            }
        }
        catch (org.omg.CORBA.BAD_OPERATION e) 
        {
            aMaximumUnitReturnLimit.theValue(0);
        }
        catch (java.lang.NullPointerException e) 
        {
        	aMaximumUnitReturnLimit = new LongOpt();
            aMaximumUnitReturnLimit.theValue(0);
        }
    
        if (aPropertiesSeqOpt != null
        	&& aPropertiesSeqOpt.discriminator()
            && aPropertiesSeqOpt.theValue().length > 0)
        {
            aPropertiesToGet = aPropertiesSeqOpt.theValue();
        }
        
    	// Nullify all the local fields cause of caching problem
        serviceCenter = "";
        processUsps = false;
        processNsp = false;
        processSrvc = false;
        req1 = null;
        m_exchangeCode = "";
        ah = null;
    
        try
        {
            m_exchangeCode = aExchangeCode.theValue().trim();
        }
        catch (org.omg.CORBA.BAD_OPERATION e) 
        {
            //do nothing
        }
        catch (java.lang.NullPointerException e) 
        {
            //do nothing
        }
            
    	setSaga("");
    
    	limBase.log(LogEventId.AUDIT_TRAIL, "RetrieveLocationForAddressTrans::execute()|LocationI::retrieveLocationForAddress()|PRE");
    
    	if (limBase.getMyLataProperty() == null)
        {
    		tagValues.clear();
    		tagValues.setProperty("LOC", LIMBase.LATA_MAP_FILE);
    
    		exBldReslt =
    			ExceptionBuilder.parseException(
    				limBase.getCallerContext(),
    				ruleFile,
    				"",
    				LIMTag.CSV_InternalError,
    				"Missing LATA Properties File.",
    				true,
    				ExceptionBuilderRule.NO_DEFAULT,
    				null,
    				null,
    				limBase.myLogger.getBisLogger(),
    				"LIM",
    				Severity.UnRecoverable,
    				tagValues);
    	}
    
    	// 10/24/2008: Moved it further up. --> RetrieveLocationHandler rlh = null;
    	try
        {
    		try
            {
    			aAddress.discriminator(); //make sure it's initialized
    		} 
            catch (org.omg.CORBA.BAD_OPERATION badop) 
            {
    			tagValues.clear();
    			tagValues.setProperty("CLASS", Address.class.getName());
    
    			exBldReslt =
    				ExceptionBuilder.parseException(
    					limBase.getCallerContext(),
    					ruleFile,
    					"",
    					LIMTag.CSV_EditError,
    					"Missing Address Info. Address data is not accessable.",
    					true,
    					ExceptionBuilderRule.NO_DEFAULT,
    					null,
    					null,
    					limBase.myLogger.getBisLogger(),
    					"LIM",
    					Severity.UnRecoverable,
    					tagValues);
    		}
            catch (NullPointerException npe)
            {
    			tagValues.clear();
    			tagValues.setProperty("CLASS", Address.class.getName());
    
    			exBldReslt =
    				ExceptionBuilder.parseException(
    					limBase.getCallerContext(),
    					ruleFile,
    					"",
    					LIMTag.CSV_EditError,
    					"Missing Address Info",
    					true,
    					ExceptionBuilderRule.NO_DEFAULT,
    					null,
    					null,
    					limBase.myLogger.getBisLogger(),
    					"LIM",
    					Severity.UnRecoverable,
    					tagValues);	
    		}
    		
    		limBase.log(LogEventId.INPUT_DATA,LIMIDLUtil.displayAddressInput(aAddress, aPropertiesToGet, aMaximumBasicAddressReturnLimit,aMaximumUnitReturnLimit,aExchangeCode));

    		try
			{
    			ah = new AddressHandler (aAddress);
			}
    		catch (AddressHandlerException ahe) {}
    		
    		if (ah.getStName().startsWith (ad_sign))
    		{
    			saveStName = ah.getStName();
    			ah.setStName (ah.getStName().substring (2, ah.getStName().length ()));
    		}
    		
    		try
            {
                BisContextValidations bisContextValidations = new BisContextValidations (limBase);
                bisContextValidations.editBisContext();
                bisContextValidations.checkAuthorization( ah.getState(), RetrieveLocTag.METHOD_RETRIEVE_LOCATION_FOR_ADDR);
                editProviderLocationProperties(aPropertiesSeqOpt);
                if (req1.getLocationPropertiesRequested().isRetrievePostalAddress())
                    processUsps = true;
                else if (req1.getLocationPropertiesRequested().isRetrieveE911Address())
                    processNsp = true;
                else
                    processSrvc = true;
               
                if (processNsp)
                	editExchangeCode();
            	try
		    	{
		    		serviceCenter = (new BisContextManager(limBase.getCallerContext())).getServiceCenter().trim();
		    	}
		    	catch (NullPointerException e)
		    	{
		    		serviceCenter = "";
		    	}
		    	
		    	if (!limBase.allNumeric(ah.getPostalCode())  &&  //non-numeric zip is assumed to be a saga
		    		(ah.getState().length() == 0) &&
		    		(processSrvc))
		        {
		    		if ( limBase.validateState(serviceCenter, limBase.REGIONS_ALL) )
		            {
		    			ah.setState (serviceCenter);
		    		}
		    	}
    			editAddress (aPropertiesSeqOpt);
    		}
            catch (InvalidData id)
            {
    			throw id;
    		} 
            catch (BusinessViolation bv)
            {
    			throw bv;
    		}

        	//1. Bypass calling these two functions for NSP
        	//2. If it is USPS and unfielded address, it will not call these two functions
            //3. Thus, only call the two functions for Service or if it is USPS and fielded address
            if ((processSrvc) || ((processUsps && 
                aAddress.discriminator().value() == AddressChoice._FIELDED_ADDRESS)))
            {
                editInvalidData (); 
                editBusinessViolation (); 
            }
        
        	// set address fields if needed	
        	if	(!limBase.allNumeric(ah.getPostalCode()) && !serviceCenter.equals ("SBCT") && !processUsps && !processNsp)
            //non-numeric zip is assumed to be a saga
        	{
        		setSaga(ah.getPostalCode());
        		ah.setPostalCode("");
        	}
        	else if (ah.getState().length() == 0 && !serviceCenter.equals ("SBCT") && !processUsps && !processNsp)
        	{
        		try
        		{    
        			limBase.log(LogEventId.AUDIT_TRAIL, "RetrieveLocationForAddressTrans::.editAddress()|RetrieveStateCodeByZip::retrieveStateCodeByZip()|PRE");
        							
        			ah.setState (RetrieveStateCodeByZip.retrieveStateCodeByZip ((Properties)limBase.getPROPERTIES(),
        					ah.getPostalCode(),limBase.myLogger.getBisLogger()));
        			limBase.log(LogEventId.AUDIT_TRAIL, "RetrieveLocationForAddressTrans::editAddress()|RetrieveStateCodeByZip::retrieveStateCodeByZip()|POST");
        		}
        		catch( SQLException e )
        		{
        			if ( e.getMessage().indexOf("Exhausted Resultset") >= 0)
        			{
        				tagValues.clear();
        				tagValues.setProperty("CLASS", Address.class.getName());
        				tagValues.setProperty("ZIP", ah.getPostalCode());
        
        				exBldReslt = 
        						ExceptionBuilder.parseException(
        							limBase.getCallerContext(),
        							ruleFile,
        							"",
        							LIMTag.CSV_OracleError,
        							"Zip:" + e.getMessage(),
        							true,
        							ExceptionBuilderRule.NO_DEFAULT,
        							null,
        							null,
        							limBase.myLogger.getBisLogger(),
        							"Oracle DB",
        							Severity.UnRecoverable,
        							tagValues);
        			}
        			else
                    {
        				exBldReslt = 
        						ExceptionBuilder.parseException(
        							limBase.getCallerContext(),
        							ruleFile,
        							"",
        							LIMTag.CSV_OracleError,
        							"Zip:" + e.getMessage(),
        							true,
        							ExceptionBuilderRule.NO_DEFAULT,
        							null,
        							null,
        							limBase.myLogger.getBisLogger(),
        							"Oracle DB",
        							Severity.UnRecoverable,
        							null);
                    }
        		}
        	}

			if (!saveStName.equals(""))
					ah.setStName (saveStName);

            String businessUnit = 
                new LIMBisContextManager(
                        this.limBase.getCallerContext()).getBusinessUnit();
                        
			// initialize caching variables because of caching problem
            setCache = false;
    		retrieveCache = false;
			limBase.setSagInfoOnly (false);
			
   		    locationI = getLocationI(ah.getState(), null, businessUnit);
    
    		// First try to retrieve the information from the cache database.
    		//
    		if (!(company.equals(Company.Company_SBCTelecom)) 
    				&& !(company.equals(OVALSUSPSTag.Company_USPostalService))
    				&& !(company.equals(OVALSNSPTag.Company_OvalsNSP)))
    		{
    			/**
    			 * 10/24/2008: Determine whether we need to retrieve cached address from Oracle table or not.
    			 */
    			checkCacheExcept ();
    			
    			if (retrieveCache || setCache)
    			{
    				try
    				{
    					String addrKey = getAddressKey (aAddress );
    					
    					if (retrieveCache)
    					{
    						// 10/24/2008: moved after the log msg. rlh.connect();
    						limBase.log (LogEventId.AUDIT_TRAIL, "RetrieveLocationForAddressTrans::execute()|RetrieveLocationHandler::getAddressMatchResult|PRE");
    						
    						/**
    						 * 10/24/2008: Get DB Connection thru retrieveLocationHandler().
    						 */
    						rlh = new RetrieveLocationHandler ((Properties)limBase.getPROPERTIES(), limBase.myLogger.getBisLogger(), req1);
        					
    						/**
    						 * Connection to Database to get data.
    						 */
    						rlh.connect();
    						AddressMatchResult addressMatchResult = rlh.getAddressMatchResult (addrKey.toUpperCase(), ""); 
    						rlh.disconnect();
    						rlh = null;		// 10/24/2008: Get rid of the connection object.
    						/**
    						 * Connection - done.
    						 */
    					
    						limBase.log (LogEventId.AUDIT_TRAIL, "RetrieveLocationForAddressTrans::execute()|RetrieveLocationHandler::getAddressMatchResult|POST");
    						if (addressMatchResult != null)
    						{
    							rv = new RetrieveLocationForAddressReturn(limBase.getCallerContext(), addressMatchResult);
								//
    							if (rv != null)
    							{
    								setCache = false;
     								limBase.log(LogEventId.OUTPUT_DATA, LIMIDLUtil.displayAddressMatchResult(rv.aAddressMatchResult));
    							}
    						}
    						// 10/24/2008: moved up to right after the addressMatchResult. rlh.disconnect();
    					}
    				}
    				catch (Exception e)
    				{
    					if (rlh != null)
    						rlh.disconnect();
    					rlh = null;		// 10/24/2008: Get rid of the connection object.
    				}
    			} // end if (retrieveCache)
    		} // end if (!(company.equals(Company.Company_SBCTelecom)))
    		else
            {
    			setCache = false;
            }
    		
    		/**
    		 * 10/24/2008: At this point, will need to retrieve address data from the corresponding legacy system.
    		 */
    		if (rv == null) {
				rv = locationI.retrieveLocationForAddress(
						aAddress,
						aPropertiesToGet,
						aMaximumBasicAddressReturnLimit,
						aMaximumUnitReturnLimit,
						aExchangeCode);
    
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
    			limBase.log (LogEventId.OUTPUT_DATA, LIMIDLUtil.displayAddressMatchResult(rv.aAddressMatchResult));

    			/**
    			 * 10/24/2008: Insert the exact match address into the Oracle table if applicable.
    			 */
    			if (setCache && !limBase.getSagInfoOnly())
    			{
    				try
    				{
    					if (rv.aAddressMatchResult.discriminator() == AddressMatchResultChoice.EXACT_MATCH)
    					{
    						BisContextManager contextManager = new BisContextManager(limBase.getCallerContext());
    					    String application = "";
    	        	    	String customerName = "";
    	        	    	try
    	        	    	{
    	        	    		application = contextManager.getApplication().trim();
    	        	    	}
    	        	    	catch (Exception e) {}
    	        	    	try
    	        	    	{
    	        	    		customerName = contextManager.getCustomerName().trim();
    	        	    	}
    	        	    	catch (Exception e) {
    	        	    	  e.printStackTrace();
                            }
    					    
    	        	    	contextManager = null;
    						if (!(application.equalsIgnoreCase("OBF") &&
      	          	    		  customerName.equalsIgnoreCase("ZZZZ")))
    						{
    							// 10/24/2008 moved to after the log msg. rlh.connect ();
    						
    							// insert the result in the database in case of ExcatMatch
    							String addrKey = getAddressKey (rv.aAddressMatchResult.aLocation().aProviderLocationProperties[0].aServiceAddress.theValue());
    							limBase.log (LogEventId.AUDIT_TRAIL, "RetrieveLocationForAddressTrans::execute()|RetrieveLocationHandler::setProviderLocationPropertyByte|PRE");
    						
    							/**
    							 * 10/24/2008: Get DB Connection thru retrieveLocationHandler().
    							 */
    							rlh = new RetrieveLocationHandler ((Properties)limBase.getPROPERTIES(), limBase.myLogger.getBisLogger(), req1);
        					
    							/**
    							 * Connection to Database to add data.
    							 */
    							rlh.connect ();
    							rlh.setProviderLocationPropertyByte (addrKey, "", rv.aAddressMatchResult.aLocation().aProviderLocationProperties[0].aServiceAddress.theValue(), this.limBase.locProviderPropForCache);
    							rlh.disconnect();
    							limBase.log (LogEventId.AUDIT_TRAIL, "RetrieveLocationForAddressTrans::execute()|RetrieveLocationHandler::setProviderLocationPropertyByte|POST");
    						}
    					}   
    				}
    				catch (Exception e)
    				{
    					// no need to do anything, look at the logfile to see what the problem was.
    				}
    			
    				/****************************************************** 
    				 * 10/24/2008 let finally block handle the disconnect.
    				if (rlh != null)
                    {
    					rlh.disconnect();
                    *******************************************************
                    **/
    			} // end if (setCache) 
    		} // end if (rv == null)
    		
    		limBase.log(LogEventId.AUDIT_TRAIL, "RetrieveLocationForAddressTrans::execute()|LocationI::retrieveLocationForAddress()|POST");
    		
    	} // end try
    	catch (org.omg.CORBA.BAD_OPERATION badop)
        {
    		tagValues.clear();
    		tagValues.setProperty("CLASS", Address.class.getName());
    
    		exBldReslt =
    			ExceptionBuilder.parseException(
    				limBase.getCallerContext(),
    				ruleFile,
    				"",
    				LIMTag.CSV_EditError,
    				"Missing Address Info. Address data is not accessable.",
    				true,
    				ExceptionBuilderRule.NO_DEFAULT,
    				null,
    				null,
    				limBase.myLogger.getBisLogger(),
    				"LIM",
    				Severity.UnRecoverable,
    				tagValues);
    	} finally
        {
    		try
    		{
    			// 10/24/2008 - moved to after disconnect. tagValues.clear();
    			if (rlh != null)
    				rlh.disconnect();
    			rlh = null;		// 10/24/2008: Get rid of the connection object.
    			tagValues.clear();
    		}
    		catch (Exception e)
    		{
    			// no need to worry...
    		}		  
    	}
    	return rv;
    }
       
    /**
     * Method editExchangeCode.
     * @param aExchangeCode
     * @throws InvalidData
     * @throws SystemFailure
     * @throws BusinessViolation
     * @throws DataNotFound
     * @throws AccessDenied
     * @throws ObjectNotFound
     * @throws NotImplemented
     */
    private void editExchangeCode()
    throws 
        InvalidData,
        SystemFailure,
        BusinessViolation,
        DataNotFound,
        AccessDenied,
        ObjectNotFound,
        NotImplemented
    {
        if (ah.getAddressId()== null || ah.getAddressId().equalsIgnoreCase(""))
        {
        	if (m_exchangeCode == null
            	|| m_exchangeCode.trim().length() == 0)
        	{
            	exBldReslt =
                	ExceptionBuilder.parseException(
                    	limBase.getCallerContext(),
                    	ruleFile,
                    	"",
                    	LIMTag.CSV_EditError,
                    	"Missing Data: Exchange Code",
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
    }
 }
