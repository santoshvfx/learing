//$Id: OvalsNspResponseFactory.java,v 1.13.12.1 2011/08/20 00:10:27 dc860q Exp $

package com.sbc.eia.bis.BusinessInterface.ovalsnsp;

import java.util.Properties;

import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilder;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilderRule;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.bis.lim.jaxb.ovalsnsp.ERRORType;
import com.sbc.eia.bis.lim.jaxb.ovalsnsp.RESPONSEType;
import com.sbc.eia.bis.lim.jaxb.ovalsnsp.impl.FATALTypeImpl;
import com.sbc.eia.bis.lim.jaxb.ovalsnsp.impl.MSAGADDRESSTypeImpl;
import com.sbc.eia.bis.lim.jaxb.ovalsnsp.impl.WARNINGTypeImpl;
import com.sbc.eia.bis.lim.transactions.RetrieveLocation.RetrieveLocReq;
import com.sbc.eia.bis.lim.transactions.RetrieveLocation.RetrieveLocResp;
import com.sbc.eia.bis.lim.util.LIMBase;
import com.sbc.eia.bis.lim.util.LIMTag;
import com.sbc.eia.idl.bis_types.AccessDenied;
import com.sbc.eia.idl.bis_types.BusinessViolation;
import com.sbc.eia.idl.bis_types.DataNotFound;
import com.sbc.eia.idl.bis_types.InvalidData;
import com.sbc.eia.idl.bis_types.NotImplemented;
import com.sbc.eia.idl.bis_types.ObjectNotFound;
import com.sbc.eia.idl.bis_types.SystemFailure;
import com.sbc.eia.idl.types.Severity;

/**
 * @author Jean Duka
 * @author Gilbert Gamboa
 */
public class OvalsNspResponseFactory
{
	/**
	 * Constructor for OvalsNspResponseFactory.
	 */
	public OvalsNspResponseFactory()
	{
		super();
	}

    /**
     * Method handleOvalsNspMsagValidation.  Factory method to instantiate a 
     * RetrieveLocResp object of the appropriate subtype.
     * @param utility
     * @param ovalsNspJaxbResp
     * @param request
     * @param maxAddr
     * @param maxUnit
     * @throws InvalidData
     * @throws AccessDenied
     * @throws BusinessViolation
     * @throws SystemFailure
     * @throws NotImplemented
     * @throws ObjectNotFound
     * @throws DataNotFound
     * @return RetrieveLocResp
     */
	public static RetrieveLocResp handleOvalsNspMsagValidation(
		LIMBase utility, 
		OvalsNspResponseHelper ovalsNspResponseHelper, 
        RetrieveLocReq request,
        String originalCity,
		int maxAddr, 
		int maxUnit)
	throws 
       	InvalidData,
       	AccessDenied,
       	BusinessViolation,
       	DataNotFound,
       	SystemFailure,
       	NotImplemented,
       	ObjectNotFound
	{
		// check jaxb response for match, no-match, alternate addresses or error.
		
        RetrieveLocResp rv = null; 
        RESPONSEType response = null;
        
		try
		{
			// This will catch any null pointer exceptions
        	response = ovalsNspResponseHelper.getOvalsNspMsagValidationResponse().getMSAGVALIDATE().getRESPONSE();
        }
        catch(Exception e)
        {
        }
                
        if (response != null && response.getMSAGADDRESS().size() > 0) 
        {
			// Exact match: SCORE = 100
			// No Match: SCORE = <blank>
			// Alternative addresses: SCORE = 1 to 99
			///////////////////////////////////////
			String matchCode = "";
			try
			{
			    matchCode = ((MSAGADDRESSTypeImpl) response.getMSAGADDRESS().get(0)).getSCORE().trim();
			}
			catch (Exception e){/*If null happen, default to empty string*/}
        	
        	if (matchCode.equalsIgnoreCase(OVALSNSPTag.OVALS_NSP_NO_MATCH_CODE))
        	{
        		utility.log(LogEventId.DEBUG_LEVEL_2, "OvalsNspResponsefactory::handleOvalsNspMsagValidation() - no address found!");
        		//CR 13404: If clients provide a supplemental address in request but get no matching address, return "LIM-BusinessViolation-10083".
        		//CR 53263: Need to remove this line of code since we have not pass any LU into OVALS functionally
        		/*if (request.getAddr().getUnitType().length() > 0 ||
        			request.getAddr().getUnitValue().length() > 0 ||
        			request.getAddr().getStructType().length() > 0 ||
        			request.getAddr().getStructValue().length() > 0 ||
        			request.getAddr().getLevelType().length() > 0 ||
        			request.getAddr().getLevelValue().length() > 0)
        		{
        			handleMsagValidationAddressSAGNotMatchMSAG(utility);
        		} 
        		else
        		{
        			handleMsagValidationNoAddressFound(utility);
        		}*/
        		handleMsagValidationNoAddressFound(utility);
        	}
        	else if (matchCode.equalsIgnoreCase(OVALSNSPTag.OVALS_NSP_MATCH_CODE))
            {
            	utility.log(LogEventId.DEBUG_LEVEL_2, "OvalsNspResponsefactory::handleOvalsNspMsagValidation() - address match found!");
            	utility.log(LogEventId.AUDIT_TRAIL, "OvalsNspResponseFactory::handleOvalsNspMsagValidation()|OvalsNspHitResp::OvalsNspHitResp()|PRE");
           		rv = new OvalsNspHitResp(utility, request, (MSAGADDRESSTypeImpl) response.getMSAGADDRESS().get(0));
           		utility.log(LogEventId.AUDIT_TRAIL, "OvalsNspResponseFactory::handleOvalsNspMsagValidation()|OvalsNspHitResp::OvalsNspHitResp()|POST");
           	}
            else 
            {
            	utility.log(LogEventId.DEBUG_LEVEL_2, "OvalsNspResponsefactory::handleOvalsNspMsagValidation() - alternate address(es) found!");
            	//CR 13404: If clients provide a supplemental address in request but get a alternative address response from OVALS NSP, return "LIM-BusinessViolation-10083" 
            	if (request.getAddr().getUnitType().length() > 0 ||
            		request.getAddr().getUnitValue().length() > 0 ||
            		request.getAddr().getStructType().length() > 0 ||
        			request.getAddr().getStructValue().length() > 0 ||
        			request.getAddr().getLevelType().length() > 0 ||
        			request.getAddr().getLevelValue().length() > 0)
        		{
            	    //If matchCode is not numeric, the default value 999 will throw 
            	    //error "Regional SAG address and MSAG address don't match. Contact the DIU for assistance."
            	    int code = 999;
            	    try
            	    {
            	        code = Integer.parseInt(matchCode);
            	    }
            	    catch (Exception e) {/*Default to 999*/}
            	    
            	    if (response.getMSAGADDRESS().size() == 1 &&
            	        code < 100)
            	    {
            	        //CR 19718
            	        //Only one listed address response and MatchCode <= 99
            	        String community = "";
            	        try
            	        {
            	            community = ((MSAGADDRESSTypeImpl) response.getMSAGADDRESS().get(0)).getCOMMUNITY().trim();
            	        }
            	        catch (Exception e) {}
            	        
            	        if (community.equalsIgnoreCase(originalCity))
            	        {
            	            //If MSAG Community equals client's input city, return Exact match
            	            utility.log(LogEventId.AUDIT_TRAIL, 
            	                    	"OvalsNspResponseFactory::handleOvalsNspMsagValidation()|OvalsNspHitResp::OvalsNspHitResp() City and Community equal, Match Code <= 99|PRE");
                       		rv = new OvalsNspHitResp(utility, request, (MSAGADDRESSTypeImpl) response.getMSAGADDRESS().get(0));
                       		utility.log(LogEventId.AUDIT_TRAIL, 
                       		        	"OvalsNspResponseFactory::handleOvalsNspMsagValidation()|OvalsNspHitResp::OvalsNspHitResp() City and Community equal, Match Code <= 99|POST");
            	        }
            	        else
            	        {
            	            //If MSAG Community not equal input City, return Alternative Address response
            	            utility.log(LogEventId.AUDIT_TRAIL, 
            	                    	"OvalsNspResponseFactory::handleOvalsNspMsagValidation()|OvalsNspListResp::OvalsNspListResp() City and Community not equal, Match Code <= 99|PRE");
                    		rv = new OvalsNspListResp(utility, request, response);
                    		utility.log(LogEventId.AUDIT_TRAIL, 
                    		        	"OvalsNspResponseFactory::handleOvalsNspMsagValidation()|OvalsNspListResp::OvalsNspListResp() City and Community not equal, Match Code <= 99|POST");
            	        }
            	    }
            	    else
            	    {
            	    	//Commented this to stop throwing the Business Violation -10083
            	        //More than one listed address response
            	        //handleMsagValidationAddressSAGNotMatchMSAG(utility);
            	        
            	        handleMsagValidationNoAddressFound(utility);
            	    }
        		} 
        		else
        		{
            		utility.log(LogEventId.AUDIT_TRAIL, "OvalsNspResponseFactory::handleOvalsNspMsagValidation()|OvalsNspListResp::OvalsNspListResp()|PRE");
            		rv = new OvalsNspListResp(utility, request, response);
            		utility.log(LogEventId.AUDIT_TRAIL, "OvalsNspResponseFactory::handleOvalsNspMsagValidation()|OvalsNspListResp::OvalsNspListResp()|POST");
        		}
           	}	
        }
        else
        {
            handleMsagValidationError(utility, ovalsNspResponseHelper.getOvalsNspMsagValidationResponse().getMSAGVALIDATE().getERROR());
        }

        rv.setMaxAddresses(maxAddr);
        rv.setMaxUnits(maxUnit);

        return rv;
	}	

	/**
	 * Handle invalid response.
	 * Creation date: (3/6/01 4:59:19 PM)
	 * @param utility LimBase
	 * @param error ERRORType
     * @throws InvalidData
     * @throws AccessDenied
     * @throws BusinessViolation
     * @throws SystemFailure
     * @throws NotImplemented
     * @throws ObjectNotFound
     * @throws DataNotFound
	 */
	public static void handleMsagValidationError(
		LIMBase utility, 
		ERRORType error)
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
    	String type = null;
    	String text = "";

		// Check for FATAL element first    	
    	try 
    	{
			type = ((FATALTypeImpl) error.getFATAL().get(0)).getTYPE();
            text = ((FATALTypeImpl) error.getFATAL().get(0)).getValue();    		
    	}
    	catch(Exception e)
    	{
    	}
    	
    	if (type == null || type.trim().length() == 0)
    	{
    		// Check for WARNING if no ERROR
    		try 
    		{
				type = ((WARNINGTypeImpl) error.getWARNING().get(0)).getTYPE();
            	text = ((WARNINGTypeImpl) error.getWARNING().get(0)).getValue();    		
    		}
    		catch(Exception e)
    		{
    		}    		
    	}
    	
        if (type != null && type.trim().length() > 0)
        	 
        {
            tagValues.setProperty("MSG", text);
            
            if(type != null && type.trim().equalsIgnoreCase(OVALSNSPTag.OVALS_NSP_INVALID_DATA_ERROR_CODE))
            {
            	ExceptionBuilder.parseException(
                	utility.getCallerContext(),
                	utility.getOvalsNspRulesFile(),
                	"",
                	LIMTag.CSV_EditError,
                	"OVALS NSP Invalid Data",
                	true,
                	ExceptionBuilderRule.NO_DEFAULT,
                	null,
                	null,
                	utility,
                	"OVALS_NSP",
                	Severity.UnRecoverable,
                	tagValues);	
            }
            else
            {
            	ExceptionBuilder.parseException(
                	utility.getCallerContext(),
                	utility.getOvalsNspRulesFile(),
                	"",
                	LIMTag.CSV_InternalError,
                	"OVALS NSP Error",
                	true,
                	ExceptionBuilderRule.NO_DEFAULT,
                	null,
                	null,
                	utility,
                	"OVALS_NSP",
                	Severity.UnRecoverable,
                	tagValues);	            	
            }
        }
        else
        {
            tagValues.setProperty(
            	"MSG", 
            	"Unexpected response received from OVALS NSP. RESPONSE AND ERROR tags are both empty.");

            ExceptionBuilder.parseException(
                utility.getCallerContext(),
                utility.getOvalsNspRulesFile(),
                "",
                null,
                null,
                true,
                ExceptionBuilderRule.NO_DEFAULT,
                null,
                null,
                utility,
                "OVALS_NSP",
                Severity.UnRecoverable,
                tagValues);
        }
	}

	/**
	 * Handle no-match response.
	 * Creation date: (3/6/01 4:59:19 PM)
	 * @param utility LimBase
     * @throws InvalidData
     * @throws AccessDenied
     * @throws BusinessViolation
     * @throws SystemFailure
     * @throws NotImplemented
     * @throws ObjectNotFound
     * @throws DataNotFound
	 */
	public static void handleMsagValidationNoAddressFound(LIMBase utility)
	throws 
       	InvalidData,
       	AccessDenied,
       	BusinessViolation,
       	DataNotFound,
       	SystemFailure,
       	NotImplemented,
       	ObjectNotFound	
	{
    	ExceptionBuilder.parseException(
        	utility.getCallerContext(),
        	utility.getLimRulesFile(),
        	"",
        	LIMTag.CSV_AddressError,
        	"Neither an exact match or a close alternative was found.",
        	true,
        	ExceptionBuilderRule.NO_DEFAULT,
        	null,
        	null,
        	utility,
        	"OVALS_NSP",
        	Severity.UnRecoverable,
        	null);	    	
	}
	
	/**
	 * Handle no-match response.
	 * Creation date: (4/30/07 4:59:19 PM)
	 * @param utility LimBase
     * @throws InvalidData
     * @throws AccessDenied
     * @throws BusinessViolation
     * @throws SystemFailure
     * @throws NotImplemented
     * @throws ObjectNotFound
     * @throws DataNotFound
	 */
	public static void handleMsagValidationAddressSAGNotMatchMSAG(LIMBase utility)
	throws 
       	InvalidData,
       	AccessDenied,
       	BusinessViolation,
       	DataNotFound,
       	SystemFailure,
       	NotImplemented,
       	ObjectNotFound	
	{
    	ExceptionBuilder.parseException(
        	utility.getCallerContext(),
        	utility.getLimRulesFile(),
        	"",
        	LIMTag.CSV_EditError,
        	"Regional SAG address and MSAG address don't match. Contact the DIU for assistance.",
        	true,
        	ExceptionBuilderRule.NO_DEFAULT,
        	null,
        	null,
        	utility,
        	"OVALS_NSP",
        	Severity.UnRecoverable,
        	null);	    	
	}
	
}
