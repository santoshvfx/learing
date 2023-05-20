// $Id: AsonResponseFactory.java,v 1.7 2005/01/07 23:46:10 biscvsid Exp $

package com.sbc.eia.bis.BusinessInterface.ason;

import java.util.ArrayList;

import com.sbc.bccs.idl.helpers.IDLUtil;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilder;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilderResult;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilderRule;
import com.sbc.eia.bis.framework.logging.LogEventId;
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
import com.sbc.eia.idl.exception_types.ExceptionCode;
import com.sbc.eia.idl.lim.helpers.AddressHandlerAIT;
import com.sbc.eia.idl.lim.helpers.AddressHandlerASON;
import com.sbc.eia.idl.lim.helpers.AddressHandlerException;
import com.sbc.eia.idl.lim_types.Address;
import com.sbc.eia.idl.lim_types.FieldedAddress;
import com.sbc.eia.idl.lim_types.UnfieldedAddress;
import com.sbc.eia.idl.types.Severity;
import com.sbc.eia.idl.types.StringSeqOpt;
import com.sbc.gwsvcs.access.vicuna.EventResultPair;
import com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException;
import com.sbc.gwsvcs.service.asonservice.ASONServiceAccess;
import com.sbc.gwsvcs.service.asonservice.ASONServiceTest;
import com.sbc.gwsvcs.service.asonservice.interfaces.ASONLvngUnitValResp;
import com.sbc.gwsvcs.service.asonservice.interfaces.ASONSagValidDescErr;
import com.sbc.gwsvcs.service.asonservice.interfaces.ASONSagValidErr;
import com.sbc.gwsvcs.service.asonservice.interfaces.ASONSagValidResp;

/**
 * AsonResponseFactory class.
 * Creation date: (6/1/01 9:45:53 AM)
 * @author: David Brawley
 */
public class AsonResponseFactory {

    /**
     * Private default constructor.  You never need to instantiate this class.
     * Creation date: (6/1/01 9:46:29 AM)
     */
    private AsonResponseFactory() {
    }
    /**
     * Get addresses from ASONSagValidResp descAddrRmks fields.
     * Creation date: (3/6/01 4:35:51 PM)
     * @return addressArray  an array of Address objects
     * @param utility LIMBase
     * @param  sagResp   an ASONSagValidResp object
     * @throws InvalidData
     * @throws AccessDenied
     * @throws BusinessViolation
     * @throws DataNotFound
     * @throws SystemFailure
     * @throws NotImplemented
     * @throws ObjectNotFound
    
     */
    public static Address[] getAsonDescAddr(LIMBase utility, ASONSagValidResp sagResp)
    	throws InvalidData, AccessDenied, BusinessViolation, DataNotFound, SystemFailure, NotImplemented, ObjectNotFound
    {
    	ExceptionBuilderResult exBldReslt = null;
    	String ruleFile = (String) utility.getEnv(LIMTag.CSV_FileName_ASON);
    
    	Address [] addressArray = null;
    	ArrayList addrList = new ArrayList ();
    
    	try{
    		String addrRmks = "";
    		
    		for (int i = 0; i < ASONTag.SAG_DESC_ADDR_RMK_SIZE; i++){
    	
    			switch (i){
    				case 0:
    					addrRmks = sagResp.descAddrRmks1;
    					break;
    				case 1:
    					addrRmks = sagResp.descAddrRmks2;
    					break;
    				case 2:
    					addrRmks = sagResp.descAddrRmks3;
    					break;
    				case 3:
    					addrRmks = sagResp.descAddrRmks4;
    					break;
    			}
    	
    			if (addrRmks.toUpperCase().indexOf("CORR AD") >= 0){
    	
    				// use AddressHandlerAIT to parse unfielded address
    				String unfAddr = addrRmks.substring(addrRmks.indexOf(":") + 1).trim();
    
    				UnfieldedAddress ufa       = new UnfieldedAddress();
    				String aAddressLineArray[] = new String [] { unfAddr } ;
    				ufa.aAddressLines          = (StringSeqOpt) IDLUtil.toOpt ( aAddressLineArray );
    				ufa.aCity                  = IDLUtil.toOpt (sagResp.communitySag);
    				ufa.aCounty                = IDLUtil.toOpt (sagResp.countyAbbrev);
    				ufa.aState                 = IDLUtil.toOpt (sagResp.state);
                    ufa.aPostalCode            = IDLUtil.toOpt (sagResp.zipCodeSag);
                    ufa.aPostalCodePlus4       = IDLUtil.toOpt ("");
                    ufa.aCountry               = IDLUtil.toOpt (""); 
                    ufa.aStructureType         = IDLUtil.toOpt ("");
                    ufa.aStructureValue        = IDLUtil.toOpt ("");
                    ufa.aLevelType             = IDLUtil.toOpt ("");
                    ufa.aLevelValue            = IDLUtil.toOpt ("");
                    ufa.aUnitType              = IDLUtil.toOpt ("");
                    ufa.aUnitValue             = IDLUtil.toOpt ("");
    				ufa.aAdditionalInfo        = IDLUtil.toOpt ("");
                    ufa.aBusinessName          = IDLUtil.toOpt ("");
    
    				Address addr = new Address();
    				addr.aUnfieldedAddress( ufa );
    
    				AddressHandlerAIT addrAIT = new AddressHandlerAIT(addr);
    
    				// create fielded address required for output to client
    				FieldedAddress fa            = new FieldedAddress();
    				fa.aRoute				     = IDLUtil.toOpt ("");
    				fa.aBox					     = IDLUtil.toOpt ("");					
    				fa.aHouseNumberPrefix	     = IDLUtil.toOpt (addrAIT.getHousNbrPfx());
    				fa.aHouseNumber			     = IDLUtil.toOpt (addrAIT.getHousNbr());
    				fa.aAssignedHouseNumber      = IDLUtil.toOpt ("");		
    				fa.aHouseNumberSuffix	     = IDLUtil.toOpt (addrAIT.getHousNbrSfx());
    				fa.aStreetDirection		     = IDLUtil.toOpt (addrAIT.getStDir());
    				fa.aStreetName			     = IDLUtil.toOpt (addrAIT.getStName());
    				fa.aStreetThoroughfare	     = IDLUtil.toOpt (addrAIT.getStThorofare());
    				fa.aStreetNameSuffix	     = IDLUtil.toOpt (addrAIT.getStNameSfx());
    				fa.aCity				     = IDLUtil.toOpt (addrAIT.getCity());
    				fa.aState				     = IDLUtil.toOpt (addrAIT.getState());
    				fa.aPostalCode			     = IDLUtil.toOpt (addrAIT.getPostalCode());
                    fa.aPostalCodePlus4          = IDLUtil.toOpt ("");
    				fa.aCounty				     = IDLUtil.toOpt (addrAIT.getCounty());
    				fa.aCountry				     = IDLUtil.toOpt ("");
                    fa.aStructureType            = IDLUtil.toOpt ("");
                    fa.aStructureValue           = IDLUtil.toOpt ("");
                    fa.aLevelType                = IDLUtil.toOpt ("");
                    fa.aLevelValue               = IDLUtil.toOpt ("");
                    fa.aUnitType                 = IDLUtil.toOpt ("");
                    fa.aUnitValue                = IDLUtil.toOpt ("");
                    fa.aAdditionalInfo           = IDLUtil.toOpt ("");
                    fa.aOriginalStreetDirection  = IDLUtil.toOpt ("");
                    fa.aOriginalStreetNameSuffix = IDLUtil.toOpt ("");
                    fa.aCassAddressLines         = new StringSeqOpt();
                    fa.aCassAddressLines.__default();
                    fa.aAuxiliaryAddressLines    = new StringSeqOpt();
                    fa.aAuxiliaryAddressLines.__default();
                    fa.aCassAdditionalInfo       = IDLUtil.toOpt ("");
                    fa.aBusinessName             = IDLUtil.toOpt ("");
    	
    				Address newAddress = new Address ();
    				newAddress.aFieldedAddress(fa);
    			
    				addrList.add (newAddress);
    			}
    		} // end for loop
    	}
    	catch (AddressHandlerException ahe){
    		exBldReslt =
    				ExceptionBuilder.parseException(
    				utility.getCallerContext(),
    				ruleFile,
    				"",
    				LIMTag.CSV_AddressHandlerError,
    				ahe.getMessage(),
    				true,
    				ExceptionBuilderRule.NO_DEFAULT,
    				null,
    				null,
    				utility,
    				"ASON",
    				Severity.UnRecoverable,
    				null);
    	}
    
    	addressArray = new Address [addrList.size()];
    			
    	for (int j = 0; j < addrList.size(); j++)
    	{
    		addressArray[j] = (Address) addrList.get (j);	
    	}
    
    	return addressArray;
    }
    /**
     * Factory method to instantiate a RetrieveLocResp object of the appropriate subtype.
     * Creation date: (3/6/01 4:35:51 PM)
     * @return RetrieveLocResp
     * @param utility LIMBase
     * @param sagResp  an ASONSagValidResp object
     * @param maxAddr int the max number of addresses to return
     * @param maxUnit int the max number of units to return
     * @throws InvalidData
     * @throws AccessDenied
     * @throws BusinessViolation
     * @throws DataNotFound
     * @throws SystemFailure
     * @throws NotImplemented
     * @throws ObjectNotFound
     */
    public static RetrieveLocResp handleDescAddr(LIMBase utility, ASONSagValidResp sagResp, int maxAddr, int maxUnit)
    	throws InvalidData, AccessDenied, BusinessViolation, DataNotFound, SystemFailure, NotImplemented, ObjectNotFound
    {
    	RetrieveLocResp rv = null;  	//rv = Return Value
    	ExceptionBuilderResult exBldReslt = null;
    	String ruleFile = (String) utility.getEnv(LIMTag.CSV_FileName_ASON);
    
    	Address [] addressArray = getAsonDescAddr(utility, sagResp);
    
    	switch (addressArray.length)
    	{
    		case 0:
    		
    			exBldReslt =
    					ExceptionBuilder.parseException(
    					utility.getCallerContext(),
    					ruleFile,
    					"",
    					LIMTag.CSV_AddressError,
    					null, 
    					true,
    					ExceptionBuilderRule.NO_DEFAULT,
    					null,
    					null,
    					utility,
    					"ASON",
    					Severity.UnRecoverable,
    					null);
    			break;
    			
    		case 1:
    			rv = new AsonMultiValidation(utility, addressArray[0]);
    			((AsonMultiValidation) rv).setDescriptiveAddr(true);
    			break;
    			
    		default:  
    			rv = new AsonAddrListResp(utility);
    			int maxArray = maxAddr;
    			if ((maxArray == 0) || 
    				(maxArray > addressArray.length))
    				maxArray = addressArray.length;
    			for (int i = 0; i < maxArray; i++)
    				((AsonAddrListResp)rv).addToList(addressArray[i]);
    			break;
    	}
    
    	rv.setMaxAddresses(maxAddr);
    	rv.setMaxUnits(maxUnit);
    
    	return rv;
    }
    /**
     * Factory method to instantiate a RetrieveLocResp object of the appropriate subtype.
     * Creation date: (3/6/01 4:35:51 PM)
     * @return RetrieveLocResp
     * @param utility LIMBase
     * @param result EventResultPair
     * @param maxAddr int the max number of addresses to return
     * @param maxUnit int the max number of units to return
     * @throws SystemFailure
     * @throws InvalidData
     */
    public static RetrieveLocResp handleLuvFromVicuna(LIMBase utility, EventResultPair result, 
    	AsonRetrieveLocReq aRequest, int maxAddr,int maxUnit, EventResultPair savedResult, boolean sagValidReq)
    	throws SystemFailure, InvalidData
    {
    	RetrieveLocResp rv = null;  //rv = Return Value
    	String ruleFile = (String) utility.getEnv(LIMTag.CSV_FileName_ASON);

    	switch (result.getEventNbr())
    	{
    		case ASONServiceAccess.ASON_LU_VALIDATION_RESP_NBR:
    			utility.log(LogEventId.DEBUG_LEVEL_2, ASONServiceTest.display((ASONLvngUnitValResp) result.getTheObject())); 
    			switch (((ASONLvngUnitValResp) result.getTheObject()).replyCode)
    			{
    				case 0:		// Good return, address match is found
    					rv = new AsonHitResp(utility, (ASONLvngUnitValResp) result.getTheObject(), aRequest);
    					break;
    				case 5712:  // complete match on StNbr but no match on LOC
    				case 5714:  // no match on StNbr
    					if (sagValidReq)
    					{
    						String sagMsg = "SAG INFORMATION ONLY";
    						rv = new AsonHitResp(utility, (ASONSagValidResp) savedResult.getTheObject(), aRequest, sagMsg);
    					}
    					else
   							rv = new AsonAddrListResp(utility, (ASONLvngUnitValResp) result.getTheObject());
    					break;
    				default:  // ReplyCode unknown from Living Unit Validation Response
    					if (sagValidReq)
    					{
    						String sagMsg = "SAG INFORMATION ONLY - LIVING UNIT SYSTEM FAILURE";
    						rv = new AsonHitResp(utility, (ASONSagValidResp) savedResult.getTheObject(), aRequest, sagMsg);
    					}
    					else
    						handleSystemFailure(ruleFile, utility, ExceptionCode.ERR_LIM_INTERNAL,
    							"RetrieveLocResp.fromVicuna(): Unknown ReplyCode <" +
    							((ASONLvngUnitValResp) result.getTheObject()).replyCode + "> for ASONLvngUnitValResp from ASONService!");
    					break;
    			}
    			break;
    		default:  //should never land here because ASONServiceHelper would throw an exception
    			handleSystemFailure(ruleFile, utility, LIMTag.CSV_InternalError,
    				"RetrieveLocResp.fromVicuna(): Unknown EventID <" + 
    				result.getEventNbr() + "> from ASONService!");
    			break;
    	}
    
    	rv.setMaxAddresses(maxAddr);
    	rv.setMaxUnits(maxUnit);
    	
    	return rv;
    }
    /**
     * Factory method to instantiate a RetrieveLocResp object of the appropriate subtype.
     * Creation date: (3/6/01 4:35:51 PM)
     * @return RetrieveLocResp
     * @param utility LIMBase
     * @param result EventResultPair
     * @param request an AsonRetrieveLocReq object
     * @param addr             an AddressHandlerASON object
     * @param maxAddr int the max number of addresses to return
     * @param maxUnit int the max number of units to return
     * @param compoundStNmSfx  a boolean
     * @throws ServiceException
     * @throws InvalidData
     * @throws AccessDenied
     * @throws BusinessViolation
     * @throws DataNotFound
     * @throws SystemFailure
     * @throws NotImplemented
     * @throws ObjectNotFound
     */
    public static RetrieveLocResp handleSagValidFromVicuna(LIMBase utility, EventResultPair result, AsonRetrieveLocReq request, AddressHandlerASON addr, int maxAddr,int maxUnit, boolean compoundStNmSfx)
    	throws ServiceException, InvalidData, AccessDenied, BusinessViolation, DataNotFound, SystemFailure, NotImplemented, ObjectNotFound
    {
    	RetrieveLocResp rv = null;  //rv = Return Value
    	ExceptionBuilderResult exBldReslt = null;
    	String ruleFile = (String) utility.getEnv(LIMTag.CSV_FileName_ASON);
    	
    	switch (result.getEventNbr())
    	{
    		case ASONServiceAccess.ASON_SAG_VALID_RESP_NBR:
    			utility.log(LogEventId.DEBUG_LEVEL_2, ASONServiceTest.display((ASONSagValidResp) result.getTheObject()));
    			if (((ASONSagValidResp) result.getTheObject()).comReplyHdr2.descriptiveAddrInd == 'Y')
    				rv = handleDescAddr(utility, (ASONSagValidResp) result.getTheObject(), maxAddr, maxUnit);
    			else
    				rv = new AsonAddrSagValidResp(utility, (ASONSagValidResp) result.getTheObject(), request, addr);
    			break;
    
    		case ASONServiceAccess.ASON_SAG_VALID_ERR_NBR:
    			utility.log(LogEventId.DEBUG_LEVEL_2, ASONServiceTest.display((ASONSagValidErr) result.getTheObject()));
    			if (((ASONSagValidErr) result.getTheObject()).comReplyHdr2.descriptiveAddrInd == 'Y'){
    				exBldReslt = ExceptionBuilder.parseException(
    					utility.getCallerContext(),
    					ruleFile,
    					"",
    					LIMTag.CSV_AddressError,
    					"Descriptive not found",
    					true,
    					ExceptionBuilderRule.NO_DEFAULT,
    					null,
    					null,
    					utility,
    					"ASON",
    					Severity.UnRecoverable,
    					null);
    			}
     
    			if (((ASONSagValidErr) result.getTheObject()).comReplyHdr3.sagErrorCode.equalsIgnoreCase("SAG01")){
    			    // SAG01 is zipCode invalid error, no need to proceed
    			    // Exception: 9999 Description: C026 - SAG RECORD NOT FOUND 
    				exBldReslt =
    					ExceptionBuilder.parseException(
    						utility.getCallerContext(),
    						ruleFile,
    						"",
    						LIMTag.CSV_AddressError,
    						String.valueOf(((ASONSagValidErr) result.getTheObject()).comReplyHdr1.MsgCode) + " Description: " + 
    						String.valueOf(((ASONSagValidErr) result.getTheObject()).comReplyHdr1.MsgText),
    						true,
    						ExceptionBuilderRule.NO_DEFAULT,
    						null,
    						null,
    						utility,
    						"ASON",
    						Severity.UnRecoverable,
    						null);
    				
    			}
    			else if ((((ASONSagValidErr) result.getTheObject()).comReplyHdr3.sagErrorCode.equalsIgnoreCase("SAG12")) &&
    					 (request.asonAddr.getCity().length() > 0)){
    				// SAG12 is community required error
    				rv = new AsonMultiValidation(utility, request.asonAddr.getAddress());
    				((AsonMultiValidation) rv).setCommunityRequired(true);
    				((AsonMultiValidation) rv).setSagIdList((ASONSagValidErr) result.getTheObject());
    			}
    			else if (((((ASONSagValidErr) result.getTheObject()).comReplyHdr3.sagErrorCode.equalsIgnoreCase("SAG02")) ||
                          (((ASONSagValidErr) result.getTheObject()).comReplyHdr3.sagErrorCode.equalsIgnoreCase("SAG04")) ||
                          (((ASONSagValidErr) result.getTheObject()).comReplyHdr3.sagErrorCode.equalsIgnoreCase("SAG09")))
                              &&
    					 (compoundStNmSfx)) {
    			    // SAG02 is street address error, SAG04 is street number errror, SAG09 is dir range zip error
                    // maybe due to compund StNameSfx
    				rv = new AsonMultiValidation(utility, request.asonAddr.getAddress());
    				((AsonMultiValidation) rv).setCompoundStNameSfx(true);
    				((AsonMultiValidation) rv).setSagIdList((ASONSagValidErr) result.getTheObject());			
    			}
    			else{
    				rv = new AsonAddrRangeResp(utility,((ASONSagValidErr) result.getTheObject())); 
    			}
    			break;
    
    		case ASONServiceAccess.ASON_SAG_VALID_DESC_ERR_NBR:
    			utility.log(LogEventId.DEBUG_LEVEL_2, ASONServiceTest.display((ASONSagValidDescErr) result.getTheObject()));
    			if (request.asonAddr.getAddAddrInfo().length() > 0){
    			
    				exBldReslt = ExceptionBuilder.parseException(
    					utility.getCallerContext(),
    					ruleFile,
    					"",
    					LIMTag.CSV_AddressError,
    					"Descriptive not found",
    					true,
    					ExceptionBuilderRule.NO_DEFAULT,
    					null,
    					null,
    					utility,
    					"ASON",
    					Severity.UnRecoverable,
    					null);
    			}
    	
    		default:  //should never land here because ASONServiceHelper would throw an exception
    			handleSystemFailure(ruleFile, utility, LIMTag.CSV_InternalError,
    				"RetrieveLocResp.fromVicuna(): Unknown EventID <" + 
    				result.getEventNbr() + "> From ASONService!");
    			break;
    	}
    
    	rv.setMaxAddresses(maxAddr);
    	rv.setMaxUnits(maxUnit);
    	
    	return rv;
    }
    /**
     * Invoke handleException() to create a SystemFailure exception.  Hide the other
     * exception types.
     * Creation date: (3/6/01 4:59:19 PM)
     * @param ruleFile String
     * @param utility LimBase
     * @param code String
     * @param text String
     * @exception SystemFailure
     */
    public static void handleSystemFailure(String ruleFile, LIMBase utility, String code, String text) throws SystemFailure
    {
    	try
    	{
    		ExceptionBuilderResult exBldReslt = null;
    	
    		exBldReslt =
    				ExceptionBuilder.parseException(
    					utility.getCallerContext(),
    					ruleFile,
    					"",
    					code,
    					text,
    					true,
    					ExceptionBuilderRule.NO_DEFAULT,
    					null,
    					null,
    					utility,
    					"ASONService",
    					Severity.UnRecoverable,
    					null);
    	}
    	catch (InvalidData e) {}
    	catch (BusinessViolation e) {}
    	catch (DataNotFound e) {}
    	catch (AccessDenied e) {}
    	catch (ObjectNotFound e) {}
    	catch (NotImplemented e) {}
    }
}
