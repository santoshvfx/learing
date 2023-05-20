//$Id: RetrieveLocationForPostalAddressValidator.java,v 1.7 2008/03/22 00:18:47 gg2712 Exp $

package com.sbc.eia.bis.lim.transactions.RetrieveLocationForPostalAddress;

import java.util.Properties;

import java.sql.SQLException;

import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilder;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilderResult;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilderRule;
import com.sbc.eia.idl.types.Severity;
import com.sbc.eia.bis.lim.util.LIMBase;
import com.sbc.eia.bis.lim.util.LIMTag;
import com.sbc.eia.idl.bis_types.AccessDenied;
import com.sbc.eia.idl.bis_types.BusinessViolation;
import com.sbc.eia.idl.bis_types.DataNotFound;
import com.sbc.eia.idl.bis_types.InvalidData;
import com.sbc.eia.idl.bis_types.NotImplemented;
import com.sbc.eia.idl.bis_types.ObjectNotFound;
import com.sbc.eia.idl.bis_types.SystemFailure;
import com.sbc.eia.idl.lim_types.Address;
import com.sbc.eia.idl.types.LongOpt;
import com.sbc.eia.idl.types.StringOpt;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.bis.lim.util.LIMIDLUtil;
import com.sbc.eia.idl.lim.helpers.AddressHandler;
import com.sbc.eia.idl.lim.helpers.AddressHandlerException;
import com.sbc.eia.idl.bis_types.AccessDenied;
import com.sbc.eia.idl.bis_types.BusinessViolation;
import com.sbc.eia.idl.bis_types.DataNotFound;
import com.sbc.eia.idl.bis_types.InvalidData;
import com.sbc.eia.idl.bis_types.NotImplemented;
import com.sbc.eia.idl.bis_types.ObjectNotFound;
import com.sbc.eia.idl.bis_types.SystemFailure;
import com.sbc.eia.bis.lim.util.BisContextValidations;
import com.sbc.eia.bis.lim.transactions.RetrieveLocation.RetrieveLocTag;
import com.sbc.eia.bis.lim.util.LIMBisContextManager;
import com.sbc.eia.idl.lim_types.AddressChoice;
import com.sbc.eia.idl.lim.queries.RetrieveStateCodeByZip;


/**
 * Contain validation rules for RetrieveLocationForPostalAddress 
 */
public class RetrieveLocationForPostalAddressValidator
{
    public static final String ad_sign = "@ ";
    public static final String METHOD_RETRIEVE_LOCATION_FOR_POSTAL_ADDR  = "retrieveLocationForPostalAddress" ;
    private LIMBase loc_LIMBase = null;
    private String ruleFile = null;
    private ExceptionBuilderResult exBldReslt = null;
    private AddressHandler ah = null;
    private String applicationName = null;
    private String businessUnit = null;
    private String serviceCenter = null;
//    private String saga = "";
    private boolean isCSZ = false;

    public RetrieveLocationForPostalAddressValidator(LIMBase limBase)
    {
        loc_LIMBase = limBase;
        ruleFile = (String) loc_LIMBase.getPROPERTIES().get(LIMTag.CSV_FileName_LIM);       
    }

    //todo: write validate for each request field. E.g. validate(BisContext aBiscontext)
    protected void validate(
            Address aAddress,
            LongOpt aMaxAddressAlternatives) 
    	throws
    		BusinessViolation,
    		InvalidData,
    		SystemFailure,
    		ObjectNotFound,
    		NotImplemented,
    		DataNotFound,
    		AccessDenied
    {
        Properties tagValues = new Properties();
        
        checkMaxAddressAlternatives(aMaxAddressAlternatives);
        
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
            
            loc_LIMBase.log(LogEventId.INPUT_DATA, LIMIDLUtil.displayAddressInput(aAddress, null, (StringOpt) null, aMaxAddressAlternatives, (LongOpt) null));
            
            ah = null;
            setBusinessUnit("");
            setServiceCenter("");
            setApplicationName("");
            setIsCSZ(false);
            
            try
			{
    			ah = new AddressHandler (aAddress);
			}
            catch (AddressHandlerException ahe) {}
            
            retrieveBisContextData();
            
            try
            {
                BisContextValidations bisContextValidations = new BisContextValidations(loc_LIMBase);
                bisContextValidations.editBisContext();
                bisContextValidations.checkAuthorization( ah.getState(), METHOD_RETRIEVE_LOCATION_FOR_POSTAL_ADDR);
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
     * @param aMaxAddressAlternatives LongOpt
     */
    private void checkMaxAddressAlternatives(LongOpt aMaxAddressAlternatives)
    {
        try 
        {
            if (!aMaxAddressAlternatives.discriminator() ||
                aMaxAddressAlternatives.theValue() < -1)
            {
                aMaxAddressAlternatives.theValue(0);
            }
        } 
        catch (org.omg.CORBA.BAD_OPERATION e) 
        {
            aMaxAddressAlternatives.theValue(0);
        }
        catch (java.lang.NullPointerException e) 
        {
            aMaxAddressAlternatives = new LongOpt();
            aMaxAddressAlternatives.theValue(0);
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
    
    /**
     * Method editAddress
     * Edit the Address input for missing data, invalid data, business violations.
     * Creation date: (9/10/07)
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
        
        //If is unfielded address
        //LIM EWBAV req doc section 3.1.2.2
        if (ah.getAddress().discriminator().value() == AddressChoice._UNFIELDED_ADDRESS)
        {
            if ((ah.m_addressLine.length > 4 && ah.m_addressLine[4] != null && ah.m_addressLine[4].length() > 0)  &&
                (!ah.getCity().equals("")  || 
                (!ah.getState().equals(""))  || 
                (!ah.getPostalCode().equals(""))))
            { 
                tagValues.setProperty("CLASS", Address.class.getName());
                tagValues.setProperty("ADDRESSLINES", ah.m_addressLine[4]);
                tagValues.setProperty("CITY", ah.getCity());
                tagValues.setProperty("STATE", ah.getState());
                tagValues.setProperty("POSTALCODE", ah.getPostalCode());
                    
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
            
            //LIM 13.0
            if(ah.m_addressLine.length == 0 && 
                    ah.getCity().length() == 0 &&
                    ah.getState().length() == 0 &&
                    ah.getPostalCode().length() == 0)
            {
                tagValues.clear();
    			tagValues.setProperty("MSG", "Missing address lines, city, state and zip information");
    
    			exBldReslt =
    				ExceptionBuilder.parseException(
    				    loc_LIMBase.getCallerContext(),
    					ruleFile,
    					"",
    					LIMTag.CSV_EditError,
    					"Missing Address Data",
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
        
        //Since client are not passing ProviderLocationProperties, 
        //The new rule here is: if City, State, and Zip are published, set CSZ to true.
        if (ah.getCity().length() > 0 &&
            ah.getState().length() > 0 &&
            ah.getPostalCode().length() > 0)
        {
            setIsCSZ(true);
        }
        
        //Fielded address validation
        if (ah.getAddress().discriminator().value() == AddressChoice._FIELDED_ADDRESS)
        {
            editInvalidData(); 
        }
    }    
    
    /**
     * Edit address for InvalidData exceptions.
     * Creation date: (7/13/01 12:10:29 PM)
     * @exception InvalidData An input parameter contained invalid data.
     * @exception AccessDenied Access to the specified domain object or information is not allowed.
     * @exception BusinessViolation The attempted action violates a business rule.
     * @exception SystemFailure The method could not be completed due to system level errors (e.g., out of memory, loss of network connectivity)n.
     * @exception NotImplemented The method has not been implemented.
     * @exception ObjectNotFound The desired domain object could not be found.
     * @exception DataNotFound No data found.
     */
    private void editInvalidData()
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
        
    	// examine house number
    	if (ah.getHousNbr().length() > 0)
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
    
    	tagValues.clear();
    }
    
    /**
     * @return Returns the isCSZ.
     */
    public boolean getIsCSZ() {
        return isCSZ;
    }
    /**
     * @param isCSZ The isCSZ to set.
     */
    public void setIsCSZ(boolean iscsz) {
        this.isCSZ = iscsz;
    }
}