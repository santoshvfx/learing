// $Id: RetrieveLocationForServiceTrans.java,v 1.30 2007/09/25 23:22:16 jh9785 Exp $

package com.sbc.eia.bis.lim.transactions.RetrieveLocation;

import java.util.Properties;

import com.sbc.bccs.idl.helpers.TN;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilder;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilderRule;
import com.sbc.eia.bis.BusinessInterface.Company;
import com.sbc.eia.bis.BusinessInterface.location.LocationI;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.bis.lim.util.LIMBase;
import com.sbc.eia.bis.lim.util.LIMBisContextManager;
import com.sbc.eia.bis.lim.util.LIMIDLUtil;
import com.sbc.eia.bis.lim.util.LIMTag;
import com.sbc.eia.bis.lim.util.BisContextValidations;
import com.sbc.eia.idl.bis_types.AccessDenied;
import com.sbc.eia.idl.bis_types.BusinessViolation;
import com.sbc.eia.idl.bis_types.DataNotFound;
import com.sbc.eia.idl.bis_types.InvalidData;
import com.sbc.eia.idl.bis_types.NotImplemented;
import com.sbc.eia.idl.bis_types.ObjectNotFound;
import com.sbc.eia.idl.bis_types.SystemFailure;
import com.sbc.eia.idl.bis_types.TelephoneNumber;
import com.sbc.eia.idl.lim.AddressMatchResult;
import com.sbc.eia.idl.lim.AddressMatchResultChoice;
import com.sbc.eia.idl.lim.RetrieveLocationForServiceReturn;
import com.sbc.eia.idl.lim_types.ProviderLocationProperties;
import com.sbc.eia.idl.lim_types.ProviderLocationPropertiesSeqOpt;
import com.sbc.eia.idl.types.LongOpt;
import com.sbc.eia.idl.types.SbcServiceProviderValue;
import com.sbc.eia.idl.types.Severity;
import com.sbc.eia.bis.BusinessInterface.ovalsnsp.OVALSNSPTag;
import com.sbc.eia.bis.BusinessInterface.ovalsusps.OVALSUSPSTag;

/**
 * This is the Retrieve Location By Service (TN) Transaction class.
 * Creation date: (3/29/01 11:05:50 AM)
 * @author: David Prager
 */
public class RetrieveLocationForServiceTrans extends RetrieveLocationBase
{
    /**
     * Object constructor for RetrieveLocationForServiceTrans.
     * Creation date: (4/17/01 9:41:47 AM)
     * @param bisContext BisContext
     * @param properties Properties
     * @param logger BisLogger
     */
    public RetrieveLocationForServiceTrans (LIMBase lim_base)
    {
    	super (lim_base);
    }
    
    /**
     * Edit TN for valid values.
     * Creation date: (7/13/01 1:57:30 PM)
     * @param tn TN
     * @exception InvalidData An input parameter contained invalid data.
     * @exception AccessDenied Access to the specified domain object or information is not allowed.
     * @exception BusinessViolation The attempted action violates a business rule.
     * @exception SystemFailure The method could not be completed due to system level errors (e.g., out of memory, loss of network connectivity)n.
     * @exception NotImplemented The method has not been implemented.
     * @exception ObjectNotFound The desired domain object could not be found.
     * @exception DataNotFound No data found.
     */
    private void editTN (TN tn)	
    throws InvalidData, 
    	   AccessDenied, 
    	   BusinessViolation, 
    	   DataNotFound, 
    	   SystemFailure, 
    	   NotImplemented, 
    	   ObjectNotFound 
    {
    	Properties tagValues = new Properties();
    	
    	// check if data is missing
    	if (tn.toString() == "")
    	{
    		tagValues.clear();
    		tagValues.setProperty("CLASS", "TN");
    								
    		exBldReslt = 
    				ExceptionBuilder.parseException(
    						limBase.getCallerContext(),
    						ruleFile,
    						"",
    						LIMTag.CSV_EditError,
    						"Missing Telephone Number",
    						true,
    						ExceptionBuilderRule.NO_DEFAULT,
    						null,
    						null,
    						limBase.myLogger.getBisLogger(),
    						"LIM",
    						Severity.UnRecoverable,
    						tagValues);
    	}
    
    	// check if data right size and numeric
    	if (!tn.isValid())
    	{
    		tagValues.clear();
    		tagValues.setProperty("CLASS", "TN");
    		tagValues.setProperty("TN", tn.toString());
    								
    		exBldReslt = 
    				ExceptionBuilder.parseException(
    						limBase.getCallerContext(),
    						ruleFile,
    						"",
    						LIMTag.CSV_EditError,
    						"Invalid TN Format",
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
     * This method implements the Retrieve Location By Service business function.
     * Creation date: (3/29/01 11:14:43 AM)
     * @return RetrieveLocationForServiceReturn
     * @param aServiceHandle ObjectHandle
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
    public RetrieveLocationForServiceReturn execute (
            TelephoneNumber aTelephoneNumber,
            ProviderLocationPropertiesSeqOpt aPropertiesSeqOpt,
    		LongOpt aMaximumBasicAddressReturnLimit, 
            LongOpt aMaximumUnitReturnLimit)
    throws InvalidData, 
    	   AccessDenied, 
    	   BusinessViolation, 
    	   DataNotFound, 
    	   SystemFailure, 
    	   NotImplemented, 
    	   ObjectNotFound
    {

    	RetrieveLocationForServiceReturn rv = null;
    	LocationI locationI = null;
    	Properties tagValues = new Properties();
    	ProviderLocationProperties[] aPropertiesToGet = null;
    	
    	req1 = null;
    	
    	try 
        {
            if (!aMaximumBasicAddressReturnLimit.discriminator() ||
                aMaximumBasicAddressReturnLimit.theValue() < -1
               )
                aMaximumBasicAddressReturnLimit.theValue(0);
        } catch (org.omg.CORBA.BAD_OPERATION e) 
        {     
            aMaximumBasicAddressReturnLimit.theValue(0);
        }
        catch (java.lang.NullPointerException e) 
        {
        	aMaximumBasicAddressReturnLimit = new LongOpt();
            aMaximumBasicAddressReturnLimit.theValue(0);
        }
          
        try {  
            if (!aMaximumUnitReturnLimit.discriminator() ||
                 aMaximumUnitReturnLimit.theValue() < -1
               )
                aMaximumUnitReturnLimit.theValue(0);
        } catch (org.omg.CORBA.BAD_OPERATION e) 
        {      
        	aMaximumUnitReturnLimit.theValue(0); 
        }
        catch (java.lang.NullPointerException e) 
        {
            aMaximumUnitReturnLimit = new LongOpt();
            aMaximumUnitReturnLimit.theValue(0);
        }
    
    	setSaga("");
    	
    	if (aPropertiesSeqOpt != null &&
    		aPropertiesSeqOpt.discriminator() &&
            aPropertiesSeqOpt.theValue().length > 0
           )
            aPropertiesToGet = aPropertiesSeqOpt.theValue();
    	
        
    	limBase.log(LogEventId.AUDIT_TRAIL, "RetrieveLocationForServiceTrans::execute()|LocationI::retrieveLocationForService()|PRE");
    	
        
    	RetrieveLocationHandler rlh = null;
    	try
    	{
    		
    		if (aTelephoneNumber == null)
    		{
    			
    			tagValues.clear();
    			tagValues.setProperty("CLASS", "TN");
    								
    			exBldReslt = 
    				ExceptionBuilder.parseException(
    						limBase.getCallerContext(),
    						ruleFile,
    						"",
    						LIMTag.CSV_EditError,
    						"Missing Telephone Number",
    						true,
    						ExceptionBuilderRule.NO_DEFAULT,
    						null,
    						null,
    						limBase.myLogger.getBisLogger(),
    						"LIM",
    						Severity.UnRecoverable,
    						tagValues);	
    			
    		}
    		
    		limBase.log(LogEventId.INPUT_DATA, LIMIDLUtil.displayServiceInput(aTelephoneNumber, aPropertiesToGet,
    														aMaximumBasicAddressReturnLimit,aMaximumUnitReturnLimit));
    		
            TN tn = new TN(aTelephoneNumber.aNpa + aTelephoneNumber.aNxx + aTelephoneNumber.aLine);
            
            try 
            {              
                BisContextValidations bisContextValidations = new BisContextValidations (limBase);
                bisContextValidations.editBisContext();
                bisContextValidations.checkAuthorization( "" , RetrieveLocTag.METHOD_RETRIEVE_LOCATION_FOR_SVC );
                editProviderLocationProperties(aPropertiesSeqOpt);

				if (req1 != null &&
                    req1.getLocationPropertiesRequested().isRetrievePostalAddress())
                {   
                    exBldReslt = 
                            ExceptionBuilder.parseException(
                                limBase.getCallerContext(),
                                ruleFile,
                                "",
                                LIMTag.CSV_InternalError,
                                "Not Implemented" +
                                " Can not process Postal Address for RetrieveLocationForService.",
                                true,
                                ExceptionBuilderRule.NO_DEFAULT,
                                null,
                                null,
                                limBase.myLogger.getBisLogger(),
                                "LIM",
                                Severity.UnRecoverable,
                                null);
                }
                
    			editTN(tn);
                
    		} catch (InvalidData id) {
    			throw id;
    		} catch (BusinessViolation bv) {
    			throw bv;
    		}
    		
    		tn = new TN (tn.getNpa()+tn.getNxx()+tn.getLine());
    		
        	String businessUnit = 
                new LIMBisContextManager(
                        this.limBase.getCallerContext()).getBusinessUnit();
                        
			// initialize caching variables because of caching problem
            setCache = false;
    		retrieveCache = false;
    		limBase.setSagInfoOnly (false);
    		
    		locationI = getLocationI(null,tn,businessUnit);

    		// First try to retrieve the information from the cache database.
    		//
    		if (!(company.equals(Company.Company_SBCTelecom)) 
    				&& !(company.equals(OVALSUSPSTag.Company_USPostalService))
    				&& !(company.equals(OVALSNSPTag.Company_OvalsNSP)))
    		{
    			checkCacheExcept ();
    			if (retrieveCache || setCache)
    			{
    				try
    				{
    					rlh = new RetrieveLocationHandler ((Properties)limBase.getPROPERTIES(), limBase.myLogger.getBisLogger(), req1);
    					if (retrieveCache)
    					{
    						rlh.connect ();
    						limBase.log (LogEventId.AUDIT_TRAIL, "RetrieveLocationForServiceTrans::execute()|RetrieveLocationHandler::getAddressMatchResult|PRE");
    						AddressMatchResult addressMatchResult = rlh.getAddressMatchResult ("", tn.toString()); 
    						limBase.log (LogEventId.AUDIT_TRAIL, "RetrieveLocationForServiceTrans::execute()|RetrieveLocationHandler::getAddressMatchResult|POST");
    						if (addressMatchResult != null)
    						{
    							rv = new RetrieveLocationForServiceReturn(limBase.getCallerContext(), addressMatchResult);
    							if (rv != null)
    							{
    								setCache = false;
    								limBase.log(LogEventId.OUTPUT_DATA, LIMIDLUtil.displayAddressMatchResult(rv.aAddressMatchResult));
    							}
    						}
    					}
    					rlh.disconnect ();
    				}
    				catch (Exception e)
    				{
    					if (rlh != null)
    						rlh.disconnect();
    				}
    			} // end if (retrieveCache)
    		} // end if (!(company.equals(Company.Company_SBCTelecom)))
    		else
    			setCache = false;
    		if (rv == null)
    		{
    			rv = locationI.retrieveLocationForService (tn, aPropertiesToGet, aMaximumBasicAddressReturnLimit,aMaximumUnitReturnLimit);
    
    			if (rv == null)
    			{
    				exBldReslt = 
    					ExceptionBuilder.parseException(
    						limBase.getCallerContext(),
    						ruleFile,
    						"",
    						LIMTag.CSV_TnServiceError,
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
    			limBase.log(LogEventId.OUTPUT_DATA, LIMIDLUtil.displayAddressMatchResult(rv.aAddressMatchResult));
                               
    			if (setCache && !limBase.getSagInfoOnly())
    			{
    				try
    				{
    					if (rv.aAddressMatchResult.discriminator() == AddressMatchResultChoice.EXACT_MATCH)
    					{
    						rlh.connect ();
    						limBase.log (LogEventId.AUDIT_TRAIL, "RetrieveLocationForServiceTrans::execute()|RetrieveLocationHandler::setProviderLocationPropertyByte|PRE");
    						// insert the result in the database in case of ExcatMatch
    						String addrKey = getAddressKey (rv.aAddressMatchResult.aLocation().aProviderLocationProperties[0].aServiceAddress.theValue());
    						rlh.setProviderLocationPropertyByte (addrKey, tn.toString(), rv.aAddressMatchResult.aLocation().aProviderLocationProperties[0].aServiceAddress.theValue(), this.limBase.locProviderPropForCache);
    						limBase.log (LogEventId.AUDIT_TRAIL, "RetrieveLocationForServiceTrans::execute()|RetrieveLocationHandler::setProviderLocationPropertyByte|POST");
    					}
    				}
    				catch (Exception e)
    				{
    					// no need to do anything, look at the logfile to see what the problem was.
    				}
    				if (rlh != null)
    					rlh.disconnect();
    			} // end if (setCache)
    		} // end if (rv == null)
    		
    		limBase.log(LogEventId.AUDIT_TRAIL, "RetrieveLocationForServiceTrans::execute()|LocationI::retrieveLocationForService()|POST");
    		
    	} // end try
    	catch (org.omg.CORBA.BAD_OPERATION badop)
    	{
    		tagValues.clear();
    		tagValues.setProperty("CLASS", "TN");
    								
    		exBldReslt = 
    				ExceptionBuilder.parseException(
    						limBase.getCallerContext(),
    						ruleFile,
    						"",
    						LIMTag.CSV_EditError,
    						"Missing Telephone Number",
    						true,
    						ExceptionBuilderRule.NO_DEFAULT,
    						null,
    						null,
    						limBase.myLogger.getBisLogger(),
    						"LIM",
    						Severity.UnRecoverable,
    						tagValues);
    	}
    	finally
    	{
    		try
    		{
    			tagValues.clear();
    			if (rlh != null)
    				rlh.disconnect();
    		}
    		catch (Exception e)
    		{
    			// no need to worry...
    		}
    		    
    	}
    	
    	return rv;
    }
}