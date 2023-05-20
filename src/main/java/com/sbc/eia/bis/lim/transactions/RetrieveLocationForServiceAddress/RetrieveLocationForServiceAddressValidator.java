//$Id: RetrieveLocationForServiceAddressValidator.java,v 1.6 2007/10/22 23:16:14 gg2712 Exp $

package com.sbc.eia.bis.lim.transactions.RetrieveLocationForServiceAddress;

import java.util.Properties;

import java.sql.SQLException;

import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilder;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilderResult;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilderRule;
import com.sbc.eia.idl.types.Severity;
import com.sbc.eia.bis.lim.util.LIMTag;
import com.sbc.eia.bis.lim.util.LIMBase;
import com.sbc.eia.bis.lim.util.LIMIDLUtil;
import com.sbc.eia.idl.bis_types.AccessDenied;
import com.sbc.eia.idl.bis_types.BusinessViolation;
import com.sbc.eia.idl.bis_types.DataNotFound;
import com.sbc.eia.idl.bis_types.InvalidData;
import com.sbc.eia.idl.bis_types.NotImplemented;
import com.sbc.eia.idl.bis_types.ObjectNotFound;
import com.sbc.eia.idl.bis_types.SystemFailure;
import com.sbc.eia.idl.lim_types.Address;
import com.sbc.eia.idl.lim_types.LocationPropertiesToGetSeqOpt;
import com.sbc.eia.idl.types.LongOpt;
import com.sbc.eia.idl.types.StringOpt;
import com.sbc.eia.idl.lim.helpers.AddressHandler;
import com.sbc.eia.idl.lim.helpers.AddressHandlerException;
import com.sbc.eia.bis.lim.util.BisContextValidations;
import com.sbc.eia.bis.lim.transactions.RetrieveLocation.RetrieveLocTag;
import com.sbc.eia.bis.lim.util.LIMBisContextManager;
import com.sbc.eia.idl.lim_types.LocationPropertiesToGetValue;

import com.sbc.eia.bis.BusinessInterface.Company;
import com.sbc.eia.bis.BusinessInterface.CompanyFactory;
import com.sbc.eia.bis.BusinessInterface.BusinessException;
import com.sbc.eia.bis.BusinessInterface.State;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.idl.lim.queries.RetrieveStateCodeByZip;
import com.sbc.eia.bis.lim.transactions.common.ServiceLocator;

/**
 * Contain validation rules for RetrieveLocationForServiceAddress 
 */
public class RetrieveLocationForServiceAddressValidator
{
    public static final String METHOD_RETRIEVE_LOCATION_FOR_SVC_ADDR  = "retrieveLocationForServiceAddress" ;
    public static final String ad_sign = "@ ";
    private LIMBase loc_LIMBase = null;
    private String ruleFile = null;
    private ExceptionBuilderResult exBldReslt = null;
    private AddressHandler ah = null;
    private String applicationName = null;
    private String businessUnit = null;
    private String serviceCenter = null;
    private CompanyFactory companyFactory = null;
    private String saga = "";
    private String state = "";
    private ServiceLocator m_serviceLocator = null;
    
    /**
     * RetrieveLocationForServiceAddressValidator constructor.
     */
    public RetrieveLocationForServiceAddressValidator(LIMBase limBase)
    {
        loc_LIMBase = limBase;
        ruleFile = (String) loc_LIMBase.getPROPERTIES().get(LIMTag.CSV_FileName_LIM);
    }
    
    /**
     * This method implements the validations
     * Method validate.
     * Creation date: jh9785 (7/22/07)
     * @param aAddress Address
     * @param aLocationPropertiesToGet LocationPropertiesToGetSeqOpt
     * @param aMaxBasicAddressAlternatives LongOpt
     * @param aMaxLivingUnitAlternatives LongOpt
     */
    public void validate(
            Address aAddress,
            LocationPropertiesToGetSeqOpt aLocationPropertiesToGet,
            StringOpt aPreviousOwnerName,
            StringOpt aCrossBoundaryState,
            LongOpt aMaxBasicAddressAlternatives,
            LongOpt aMaxLivingUnitAlternatives)
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
        
        checkMaxBasicAddressAndMaxLivingUnit(aMaxBasicAddressAlternatives,
                							 aMaxLivingUnitAlternatives);
        
        if (loc_LIMBase.getMyLataProperty() == null)
        {
    		tagValues.clear();
    		tagValues.setProperty("LOC", LIMBase.LATA_MAP_FILE);
    
    		exBldReslt =
    			ExceptionBuilder.parseException(
    			    loc_LIMBase.getCallerContext(),
    				ruleFile,
    				"",
    				LIMTag.CSV_InternalError,
    				"Missing LATA Properties File.",
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
    				    loc_LIMBase.getCallerContext(),
    					ruleFile,
    					"",
    					LIMTag.CSV_EditError,
    					"Missing Address Info. Address data is not accessable.",
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
    			tagValues.setProperty("CLASS", Address.class.getName());
    
    			exBldReslt =
    				ExceptionBuilder.parseException(
    				    loc_LIMBase.getCallerContext(),
    					ruleFile,
    					"",
    					LIMTag.CSV_EditError,
    					"Missing Address Info",
    					true,
    					ExceptionBuilderRule.NO_DEFAULT,
    					null,
    					null,
    					loc_LIMBase.myLogger.getBisLogger(),
    					"LIM",
    					Severity.UnRecoverable,
    					tagValues);	
    		}
            
            loc_LIMBase.log(LogEventId.INPUT_DATA,LIMIDLUtil.displayAddressInput(aAddress, aLocationPropertiesToGet, aPreviousOwnerName, aMaxBasicAddressAlternatives, aMaxLivingUnitAlternatives));
            
            ah = null;
            setBusinessUnit("");
            setServiceCenter("");
            setApplicationName("");
            setState("");
            setSaga("");
            
            try
			{
    			ah = new AddressHandler(aAddress);
			}
            catch (AddressHandlerException ahe) {}
                       
            retrieveBisContextData();
            
            try
            {
                BisContextValidations bisContextValidations = new BisContextValidations(loc_LIMBase);
                bisContextValidations.editBisContext();
                bisContextValidations.checkAuthorization(ah.getState(), METHOD_RETRIEVE_LOCATION_FOR_SVC_ADDR);
                editLocationPropertiesToGet(aLocationPropertiesToGet);
                editAddress();
            }
            catch (InvalidData id)
            {
    			throw id;
    		} 
            catch (BusinessViolation bv)
            {
    			throw bv;
    		}
            
            setState(ah.getState());
        }
        catch (org.omg.CORBA.BAD_OPERATION badop)
        {
            tagValues.clear();
    		tagValues.setProperty("CLASS", Address.class.getName());
    
    		exBldReslt =
    			ExceptionBuilder.parseException(
    			    loc_LIMBase.getCallerContext(),
    				ruleFile,
    				"",
    				LIMTag.CSV_EditError,
    				"Missing Address Info. Address data is not accessable.",
    				true,
    				ExceptionBuilderRule.NO_DEFAULT,
    				null,
    				null,
    				loc_LIMBase.myLogger.getBisLogger(),
    				"LIM",
    				Severity.UnRecoverable,
    				tagValues);
        }
        finally
        {
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
     * Method checkMaxBasicAddressAndMaxLivingUnit.
     * @param aMaxBasicAddressAlternatives LongOpt
     * @param aMaxLivingUnitAlternatives LongOpt
     */
    private void checkMaxBasicAddressAndMaxLivingUnit(
            LongOpt aMaxBasicAddressAlternatives,
            LongOpt aMaxLivingUnitAlternatives)
    {
        try 
        {
            if (!aMaxBasicAddressAlternatives.discriminator() ||
                aMaxBasicAddressAlternatives.theValue() < -1)
            {
                aMaxBasicAddressAlternatives.theValue(0);
            }
        } 
        catch (org.omg.CORBA.BAD_OPERATION e) 
        {
            aMaxBasicAddressAlternatives.theValue(0);
        }
        catch (java.lang.NullPointerException e) 
        {
            aMaxBasicAddressAlternatives = new LongOpt();
            aMaxBasicAddressAlternatives.theValue(0);
        }
           
        try
        {  
            if (!aMaxLivingUnitAlternatives.discriminator() ||
                aMaxLivingUnitAlternatives.theValue() < -1)
            {
                aMaxLivingUnitAlternatives.theValue(0);
            }
        }
        catch (org.omg.CORBA.BAD_OPERATION e) 
        {
            aMaxLivingUnitAlternatives.theValue(0);
        }
        catch (java.lang.NullPointerException e) 
        {
            aMaxLivingUnitAlternatives = new LongOpt();
            aMaxLivingUnitAlternatives.theValue(0);
        }
    }
     
    /**
     * Method retrieveBisContextData.
     */
    private void retrieveBisContextData()
    {
        LIMBisContextManager limBisContextMgr = new LIMBisContextManager(loc_LIMBase.getCallerContext());
        
        setApplicationName(limBisContextMgr.getApplication());
        setBusinessUnit(limBisContextMgr.getBusinessUnit());
        setServiceCenter(limBisContextMgr.getServiceCenter());
    }
    
    /**
    * Method editLocationPropertiesToGet.
    * @param locPropertiesToGetSeqOpt LocationPropertiesToGetSeqOpt
    */
    private void editLocationPropertiesToGet(LocationPropertiesToGetSeqOpt locPropertiesToGetSeqOpt)
    	throws
    	InvalidData, 
        SystemFailure, 
        BusinessViolation, 
        DataNotFound, 
        AccessDenied, 
        ObjectNotFound, 
        NotImplemented
    {
        boolean isPostalAddress = false;
    	boolean isServiceAddress = false;
    	boolean isE911Address = false;
    	//boolean isGEOAddress = false;
        String [] theValue = null;
        
        Properties tagValues = new Properties();
        tagValues.setProperty("APPLNAME", getApplicationName());
        if (getBusinessUnit() == "")
            tagValues.setProperty("BUSUNIT", "null");
        else
            tagValues.setProperty("BUSUNIT", getBusinessUnit());
        
        try
        {
            if (locPropertiesToGetSeqOpt.discriminator() &&
                locPropertiesToGetSeqOpt.theValue() != null &&
                locPropertiesToGetSeqOpt.theValue().length > 0)
            {
                theValue = locPropertiesToGetSeqOpt.theValue();
                for (int i = 0; i < theValue.length; i++)
                {
                    if (theValue[i].equalsIgnoreCase(LocationPropertiesToGetValue.SERVICEADDRESS))
						isServiceAddress = true;
                    if (theValue[i].equalsIgnoreCase(LocationPropertiesToGetValue.POSTALADDRESS))
						isPostalAddress = true;
                    if (theValue[i].equalsIgnoreCase(LocationPropertiesToGetValue.E911ADDRESS))
						isE911Address = true;   
                }
                
                if (isPostalAddress)
                {
                    exBldReslt = 
                        ExceptionBuilder.parseException(
                            loc_LIMBase.getCallerContext(),
                            ruleFile,
                            "",
                            LIMTag.CSV_EditError,
                            "Specifying Postal Address in Service " +
                             "Address validation is prohibited",
                            true,
                            ExceptionBuilderRule.NO_DEFAULT,
                            null,
                            null,
                            loc_LIMBase.myLogger.getBisLogger(),
                            "LIM",
                            Severity.UnRecoverable,
                            null);
                }
                
                if (isE911Address)
                {
                    exBldReslt = 
                        ExceptionBuilder.parseException(
                            loc_LIMBase.getCallerContext(),
                            ruleFile,
                            "",
                            LIMTag.CSV_EditError,
                            "Specifying E911 Address in Service " +
                             "Address validation is prohibited",
                            true,
                            ExceptionBuilderRule.NO_DEFAULT,
                            null,
                            null,
                            loc_LIMBase.myLogger.getBisLogger(),
                            "LIM",
                            Severity.UnRecoverable,
                            null);
                }
                
                if (!isServiceAddress)
                {
                    //TODO: not sure how to handle it in here:
                    //setDefaultLocationPropertiesToGetSeqOpt(locPropertiesToGetSeqOpt) or,
                    //Add SERVICEADDRESS to the list
                    String [] loc_theValue = new String[theValue.length + 1];
                    
                    for (int i = 0; i < theValue.length; i++)
                    {
                        loc_theValue[i] = theValue[i];
                    }
                    
                    loc_theValue[theValue.length] = LocationPropertiesToGetValue.SERVICEADDRESS;   
                    locPropertiesToGetSeqOpt.theValue(loc_theValue);                   
                }
            }
            else
            {
                setDefaultLocationPropertiesToGetSeqOpt(locPropertiesToGetSeqOpt);
            }
        }
        catch (org.omg.CORBA.BAD_OPERATION badop)
        {
            setDefaultLocationPropertiesToGetSeqOpt(locPropertiesToGetSeqOpt);
        }
        catch (NullPointerException npe)
        {
            setDefaultLocationPropertiesToGetSeqOpt(locPropertiesToGetSeqOpt);
        }
            
    }
    
    /**
     * Method setDefaultLocationPropertiesToGetSeqOpt.
     * @param locPropertiesToGetSeqOpt LocationPropertiesToGetSeqOpt
     */
    private void setDefaultLocationPropertiesToGetSeqOpt(LocationPropertiesToGetSeqOpt locationProperties)
    {
        String [] theValue = new String[2];
        
        theValue[0] = LocationPropertiesToGetValue.SERVICEADDRESS;
        theValue[1] = LocationPropertiesToGetValue.ALL;
        locationProperties.theValue(theValue);
    }
    
    /**
     * Method editAddress
     * Edit the Address input for missing data, invalid data, business violations.
     * Creation date: (7/20/07)
     */
    private void editAddress()
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
        
        if (!loc_LIMBase.allNumeric(ah.getPostalCode()) &&  //non-numeric zip is assumed to be a saga
	    	(ah.getState().length() == 0))
	    {
            if (loc_LIMBase.validateState(getServiceCenter(), loc_LIMBase.REGIONS_ALL))
	        {
	    		ah.setState(getServiceCenter());
	    	}
	    }
        
        editInvalidData();
        editBusinessViolation();
        
        //set address fields if needed	
    	if	(!loc_LIMBase.allNumeric(ah.getPostalCode()))
        //non-numeric zip is assumed to be a saga
    	{
    		setSaga(ah.getPostalCode());
    		ah.setPostalCode("");
    	}
    	else if (ah.getState().length() == 0)
    	{
    		try
    		{    
    		    loc_LIMBase.log(LogEventId.AUDIT_TRAIL, "RetrieveLocationForServiceAddressValidator::editAddress()|RetrieveStateCodeByZip::retrieveStateCodeByZip()|PRE");
    							
    			ah.setState(RetrieveStateCodeByZip.retrieveStateCodeByZip((Properties)loc_LIMBase.getPROPERTIES(),
    					ah.getPostalCode(),loc_LIMBase.myLogger.getBisLogger()));
    			loc_LIMBase.log(LogEventId.AUDIT_TRAIL, "RetrieveLocationForServiceAddressValidator::editAddress()|RetrieveStateCodeByZip::retrieveStateCodeByZip()|POST");
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
    						    loc_LIMBase.getCallerContext(),
    							ruleFile,
    							"",
    							LIMTag.CSV_OracleError,
    							"Zip:" + e.getMessage(),
    							true,
    							ExceptionBuilderRule.NO_DEFAULT,
    							null,
    							null,
    							loc_LIMBase.myLogger.getBisLogger(),
    							"Oracle DB",
    							Severity.UnRecoverable,
    							tagValues);
    			}
    			else
                {
    				exBldReslt = 
    						ExceptionBuilder.parseException(
    						    loc_LIMBase.getCallerContext(),
    							ruleFile,
    							"",
    							LIMTag.CSV_OracleError,
    							"Zip:" + e.getMessage(),
    							true,
    							ExceptionBuilderRule.NO_DEFAULT,
    							null,
    							null,
    							loc_LIMBase.myLogger.getBisLogger(),
    							"Oracle DB",
    							Severity.UnRecoverable,
    							null);
                }
    		}
    	}    	
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
    private void editInvalidData()
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
    		&& (!loc_LIMBase.validateState(ah.getState(), loc_LIMBase.REGIONS_ALL)))
        {
    		tagValues.clear();
    		tagValues.setProperty("CLASS", Address.class.getName());
    		tagValues.setProperty("STATE", ah.getState());
    
    		exBldReslt =
    			ExceptionBuilder.parseException(
    			    loc_LIMBase.getCallerContext(),
    				ruleFile,
    				"",
    				LIMTag.CSV_EditError,
    				"Invalid State",
    				true,
    				ExceptionBuilderRule.NO_DEFAULT,
    				null,
    				null,
    				loc_LIMBase.myLogger.getBisLogger(),
    				"LIM",
    				Severity.UnRecoverable,
    				tagValues);
    	}
    
        // check if postal code is populated
        if (ah.getPostalCode().length() == 0)
        {
            tagValues.clear();
            tagValues.setProperty("CLASS", Address.class.getName());

            exBldReslt =
                ExceptionBuilder.parseException(
                    loc_LIMBase.getCallerContext(),
                    ruleFile,
    				"",
    				LIMTag.CSV_EditError,
    				"Missing Postal Code",
    				true,
    				ExceptionBuilderRule.NO_DEFAULT,
    				null,
    				null,
    				loc_LIMBase.myLogger.getBisLogger(),
    				"LIM",
    				Severity.UnRecoverable,
    				tagValues);
         }
        
        if (ah.getPostalCode().length() > 0)
        {
            // check if postalCode numeric: must be 5 byte zipCode
            if ((loc_LIMBase.allNumeric(ah.getPostalCode())))
            {
                //check if zipCode right size
                if (ah.getPostalCode().length() != 5)
                {
                    tagValues.clear();
                    tagValues.setProperty("CLASS", Address.class.getName());
                    tagValues.setProperty("ZIP", ah.getPostalCode());
    
                    exBldReslt =
                        ExceptionBuilder.parseException(
                                loc_LIMBase.getCallerContext(),
                                ruleFile,
                                "",
                                LIMTag.CSV_EditError,
                                "Invalid Postal Code",
                                true,
                                ExceptionBuilderRule.NO_DEFAULT,
                                null,
                                null,
                                loc_LIMBase.myLogger.getBisLogger(),
                                "LIM",
                                Severity.UnRecoverable,
                                tagValues);
                }
            } // end allNumeric
            // check if postalCode is alpha: must be 2-4 byte saga 	
            else if (loc_LIMBase.allAlpha(ah.getPostalCode()))
            { 
                // check if saga right size 
                if ((loc_LIMBase.validateState(ah.getState(), loc_LIMBase.REGIONS_PB_SWBT))
                        && ((ah.getPostalCode().length() < 2) || (ah.getPostalCode().length() > 4)))
                {
                    tagValues.clear();
                    tagValues.setProperty("CLASS", Address.class.getName());
                    tagValues.setProperty("ZIP", ah.getPostalCode());
    
                    exBldReslt =
                        ExceptionBuilder.parseException(
                                loc_LIMBase.getCallerContext(),
                                ruleFile,
                                "",
                                LIMTag.CSV_EditError,
                                "Invalid SAGA or Postal Code",
                                true,
                                ExceptionBuilderRule.NO_DEFAULT,
                                null,
                                null,
                                loc_LIMBase.myLogger.getBisLogger(),
                                "LIM",
                                Severity.UnRecoverable,
                                tagValues);
    
                } 
                else if (loc_LIMBase.validateState(ah.getState(), loc_LIMBase.REGIONS_AIT))
                {
                    if (!(ah.getPostalCode().length() == 1))
                    {
                        tagValues.clear();
                        tagValues.setProperty("CLASS", Address.class.getName());
                        tagValues.setProperty("ZIP", ah.getPostalCode());
    
                        exBldReslt =
                            ExceptionBuilder.parseException(
                                    loc_LIMBase.getCallerContext(),
                                    ruleFile,
                                    "",
                                    LIMTag.CSV_EditError,
                                    "Invalid SAGID or Postal Code",
                                    true,
                                    ExceptionBuilderRule.NO_DEFAULT,
                                    null,
                                    null,
                                    loc_LIMBase.myLogger.getBisLogger(),
                                    "LIM",
                                    Severity.UnRecoverable,
                                    tagValues);
                    } 
                    else if (ah.getCity().length() == 0) 
                    {
                        tagValues.clear();
                        tagValues.setProperty("CLASS", Address.class.getName());
    
                        exBldReslt =
                            ExceptionBuilder.parseException(
                                    loc_LIMBase.getCallerContext(),
                                    ruleFile,
                                    "",
                                    LIMTag.CSV_EditError,
                                    "Missing City",
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
                    //If no State provided, 2-4 characters treat as PREMIS, 1 character treat as ASON.
                    //All Alpha but the length is bigger than 4. 
                    //Less than 1 will not be the case here.
                    //Throw LIM-InvalidData-00434
                    if (ah.getPostalCode().length() > 4)
                    {
                        tagValues.clear();
                        tagValues.setProperty("CLASS", Address.class.getName());
                        tagValues.setProperty("ZIP", ah.getPostalCode());
        
                        exBldReslt =
                            ExceptionBuilder.parseException(
                                    loc_LIMBase.getCallerContext(),
                                    ruleFile,
                                    "",
                                    LIMTag.CSV_EditError,
                                    "Invalid Postal Code",
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
            } // end allAlpha
            else 
            { 	// error postalCode is alphaNumeric
                tagValues.clear();
                tagValues.setProperty("CLASS", Address.class.getName());
                tagValues.setProperty("ZIP", ah.getPostalCode());
    
                exBldReslt =
                    ExceptionBuilder.parseException(
                            loc_LIMBase.getCallerContext(),
                            ruleFile,
                            "",
                            LIMTag.CSV_EditError,
                            "Invalid Postal Code",
                            true,
                            ExceptionBuilderRule.NO_DEFAULT,
                            null,
                            null,
                            loc_LIMBase.myLogger.getBisLogger(),
                            "LIM",
                            Severity.UnRecoverable,
                            tagValues);
            }
        } // end if PostalCode.length > 0
    
    	// examine house number
    	if ((!getServiceCenter().equals("CT")) && (ah.getHousNbr().length() > 0)) 
    	{
    		// street name cannot be blank
    		if (ah.getStName().length() == 0) 
    		{
    			tagValues.clear();
    			tagValues.setProperty("CLASS", Address.class.getName());
    
    			exBldReslt =
    				ExceptionBuilder.parseException(
    				    loc_LIMBase.getCallerContext(),
    					ruleFile,
    					"",
    					LIMTag.CSV_EditError,
    					"Missing Street Name",
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
    
    	// valid address data not entered
    	// TODO: If postal code is required, this rule can be deleted
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
    			    loc_LIMBase.getCallerContext(),
    				ruleFile,
    				"",
    				LIMTag.CSV_EditError,
    				"Missing Address Info",
    				true,
    				ExceptionBuilderRule.NO_DEFAULT,
    				null,
    				null,
    				loc_LIMBase.myLogger.getBisLogger(),
    				"LIM",
    				Severity.UnRecoverable,
    				tagValues);
    	}
    
    	tagValues.clear();
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
    private void editBusinessViolation() 
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
    	if (ah.getHousNbr().length() > 0)
    	{
    		// descriptive address cannot be entered
    		if (ah.getAddAddrInfo().length() > 0)
    		{	
    			tagValues.clear();
    			tagValues.setProperty("CLASS", Address.class.getName());
    			tagValues.setProperty("SANO", ah.getHousNbr());
    			tagValues.setProperty("AAI", ah.getAddAddrInfo());
    
    			exBldReslt = 
    					ExceptionBuilder.parseException(
    					    loc_LIMBase.getCallerContext(),
    						ruleFile,
    						"",
    						LIMTag.CSV_EditError,
    						"Cannot specify House Number and Descriptive Address",
    						true,
    						ExceptionBuilderRule.NO_DEFAULT,
    						null,
    						null,
    						loc_LIMBase.myLogger.getBisLogger(),
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
    					    loc_LIMBase.getCallerContext(),
    						ruleFile,
    						"",
    						LIMTag.CSV_EditError,
    						"Cannot specify House Number and Assigned House Number",
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
    					    loc_LIMBase.getCallerContext(),
    						ruleFile,
    						"",
    						LIMTag.CSV_EditError,
    						"Cannot specify Street Name and Descriptive Address",
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
    					    loc_LIMBase.getCallerContext(),
    						ruleFile,
    						"",
    						LIMTag.CSV_EditError,
    						"Cannot specify Street Name or Route and Descriptive Address",
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
    
    	/**
    	 * examine unnamed address validation. It's valid only for PB/SWBT PREMIS
    	 * Unnamed address is "@ , streetName". The "@ " has been removed by AddressHandler(),
    	 * just need to check for "," in the street name.
    	 */
    	if (ah.getStName().length() > 0)
        {
    	    if (ah.getStName().charAt(0) == ',') 
    	    {
    			/**
    		 	 * If State is specified, use it. Otherwise, use the Service Center to validate the region.
    			 */
    			String sTmpState = getServiceCenter();
    			if (ah.getState().length() > 0)
    				sTmpState = ah.getState();
    			
    			if (! loc_LIMBase.validateState(sTmpState, loc_LIMBase.REGIONS_PB_SWBT)) 
    			{
    		 		String sTmpStName = "@ " + ah.getStName();
    				
    				tagValues.clear();
    				tagValues.setProperty("CLASS", Address.class.getName());
    				tagValues.setProperty("SANA", sTmpStName);
    				tagValues.setProperty("STATE", sTmpState);
    
    				exBldReslt = 
    					ExceptionBuilder.parseException(
    					    loc_LIMBase.getCallerContext(),
    						ruleFile,
    						"",
    						LIMTag.CSV_EditError,
    						"Unnamed Address validation",
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
    	tagValues.clear();
    }
    
    /**
     * Return true if and only if the param company equals
     * the company produced by the CompanyFactory for the param state.
     * Creation date: (01/14/02 11:11:30 AM)
     * @return boolean
     * @param company String
     * @param state String
     */
    protected boolean validateCompany(String company, String state)
    {
    	try
    	{
    		Company co = getServiceLocator().getCompanyFactory().getCompany(new State (state));
    		if (co.getCode().equals(company))
    			return true;
    		else
    			return false;
    	}
    	catch (BusinessException e)
    	{
    		return false;
    	}
    }
    
    /**
     * Return a ServiceLocator
     */
    private ServiceLocator getServiceLocator()
    {
        if (m_serviceLocator == null)
        {
            m_serviceLocator = new ServiceLocator(loc_LIMBase);
        }
        return m_serviceLocator;
    }
    
    /**
     * Setter method for the PREMIS SAGA.
     * Creation date: (4/25/01 11:55:25 AM)
     * @param newSaga String
     * @see #getSaga
     */
    public void setSaga(String newSaga)
    {
    	saga = (newSaga == null) ? "" : newSaga;
    }
    
    /**
     * Getter method for the PREMIS SAGA that just returns whatever is stored, empty or not.
     * Creation date: (4/25/01 11:55:25 AM)
     * @return String
     * @see #setSaga(String)
     */
    public String getSaga()
    {
    	return saga;
    }
    
    /**
     * Setter method for the State value.
     * @param newState String
     */
    public void setState(String newState)
    {
    	state = (newState == null) ? "" : newState;
    }
    
    /**
     * Getter method for the State.
     * @return String
     */
    public String getState()
    {
    	return state;
    }
    
    /**
     * Setter method for the Application value.
     * @param newApplicationName String
     */
    public void setApplicationName(String newApplicationName)
    {
        applicationName = (newApplicationName == null) ? "" : newApplicationName.trim();
    }
    
    /**
     * Getter method for the Application.
     * @return String
     */
    public String getApplicationName()
    {
    	return applicationName;
    }
    
    /**
     * Setter method for the BusinessUnit value.
     * @param newBusinessUnit String
     */
    public void setBusinessUnit(String newBusinessUnit)
    {
        businessUnit = (newBusinessUnit == null) ? "" : newBusinessUnit.trim();
    }
    
    /**
     * Getter method for the BusinessUnit.
     * @return String
     */
    public String getBusinessUnit()
    {
    	return businessUnit;
    }
    
    /**
     * Setter method for the ServiceCenter value.
     * @param newServiceCenter String
     */
    public void setServiceCenter(String newServiceCenter)
    {
        serviceCenter = (newServiceCenter == null) ? "" : newServiceCenter.trim();
    }
    
    /**
     * Getter method for the BusinessUnit.
     * @return String
     */
    public String getServiceCenter()
    {
    	return serviceCenter;
    }  
}
