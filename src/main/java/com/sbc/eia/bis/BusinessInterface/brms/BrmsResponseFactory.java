// $Id: BrmsResponseFactory.java,v 1.6 2005/08/22 17:09:25 rz7367 Exp $

package com.sbc.eia.bis.BusinessInterface.brms;

import java.util.Properties;

import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilder;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilderResult;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilderRule;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.bis.lim.database.queries.LFACSSagLu;
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
import com.sbc.eia.idl.exception_types.ExceptionCode;
import com.sbc.eia.idl.lim.helpers.AddressHandlerBRMS;
import com.sbc.eia.idl.lim.helpers.AddressHandlerException;
import com.sbc.eia.idl.lim_types.Address;
import com.sbc.eia.idl.types.Severity;
import com.sbc.gwsvcs.access.vicuna.EventResultPair;
import com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException;

/**
 * BrmsResponseFactory class.
 * Creation date: (6/1/01 9:45:53 AM)
 * @author: David Brawley
 */
public class BrmsResponseFactory {
	
    /**
     * Private default constructor.  You never need to instantiate this class.
     * Creation date: (6/1/01 9:46:29 AM)
     */
    private BrmsResponseFactory() {}
    /**
     * Factory method to instantiate a RetrieveLocResp object of the appropriate subtype.
     * Creation date: (3/6/01 4:35:51 PM)
     * @return RetrieveLocResp
     * @param utility LIMBase
     * @param req           BrmsRetrieveLocReq
     * @param LFACSSagLu  Address[]
     * @param maxAddr int the max number of addresses to return
     * @param maxUnit int the max number of units to return
     * @exception ServiceException
     * @exception InvalidData
     * @exception AccessDenied
     * @exception BusinessViolation
     * @exception DataNotFound
     * @exception SystemFailure
     * @exception NotImplemented
     * @exception ObjectNotFound
     */
    public static RetrieveLocResp handleLfacsSagLu (LIMBase utility, RetrieveLocReq req,
    	AddressHandlerBRMS ahReq, LFACSSagLu[] lfacsSagLuArray, int maxAddr, int maxUnit)
    	throws
    		// ServiceException,
    		InvalidData,
    		AccessDenied,
    		BusinessViolation,
    		DataNotFound,
    		SystemFailure,
    		NotImplemented,
    		ObjectNotFound {
    	ExceptionBuilderResult exBldReslt = null;
    
    	String ruleFile = null;
    	ruleFile = (String) utility.getEnv(LIMTag.CSV_FileName_BRMS);
    	RetrieveLocResp rv = null; //rv = Return Value
    
    	switch (lfacsSagLuArray.length) {
    		case 0 :
    			if ((ahReq.getStName().indexOf("*") > 0) ||
    				(ahReq.getStName().indexOf("'") > 0))
    				rv = new BrmsMultiValidation(utility);
    			else
    				rv = new BrmsAddrListResp(utility);
    			break;
    
    		case 1 :
    			try {
    				AddressHandlerBRMS brmsAddr = new AddressHandlerBRMS(lfacsSagLuArray[0].getAddress());
    				rv = new BrmsHitResp(utility, req);
    				((BrmsHitResp) rv).setBrmsAddr(brmsAddr);
    				((BrmsHitResp) rv).setCircuitId(lfacsSagLuArray[0].getCircuitId());
    				((BrmsHitResp) rv).setLpStatus(lfacsSagLuArray[0].getLpStatus());
    				((BrmsHitResp) rv).setAasgndHousNbr(req.getAddr().getAasgndHousNbr());
    				((BrmsHitResp) rv).setDescAddr(ahReq.getAddAddrInfo());
    				((BrmsHitResp) rv).setStNmInd(req.getAddr().getStNmInd());
    				break;
    			} catch (AddressHandlerException ahe) {
    				Properties tagValues = new Properties();
    				tagValues.setProperty("ERR", ahe.getMessage());
    				exBldReslt =
    					ExceptionBuilder.parseException(
    						utility.getCallerContext(),
    						ruleFile,
    						"",
    						LIMTag.CSV_AddressHandlerError,
    						"Address format",
    						true,
    						ExceptionBuilderRule.NO_DEFAULT,
    						null,
    						null,
    						utility,
    						"BRMS",
    						Severity.UnRecoverable,
    						tagValues);
    			}
    			break;
    		default :
    			for (int i = 0; i < lfacsSagLuArray.length; i++) {
    				try {
    					if (lfacsSagLuArray[i].getAddress().aFieldedAddress().aCity.theValue().trim().equalsIgnoreCase(ahReq.getCity())) {
    						AddressHandlerBRMS brmsAddr = new AddressHandlerBRMS(lfacsSagLuArray[i].getAddress());
    						rv = new BrmsHitResp(utility, req);
    						((BrmsHitResp) rv).setBrmsAddr(brmsAddr);
    						((BrmsHitResp) rv).setCircuitId(lfacsSagLuArray[i].getCircuitId());
    						((BrmsHitResp) rv).setLpStatus(lfacsSagLuArray[i].getLpStatus());
    						((BrmsHitResp) rv).setAasgndHousNbr(req.getAddr().getAasgndHousNbr());
    						((BrmsHitResp) rv).setDescAddr(ahReq.getAddAddrInfo());
    						((BrmsHitResp) rv).setStNmInd(req.getAddr().getStNmInd());
    						rv.setMaxAddresses(maxAddr);
    						rv.setMaxUnits(maxUnit);
    						return rv;
    					}
    				} catch (org.omg.CORBA.BAD_OPERATION e) {
    				} catch (java.lang.NullPointerException e) {
    				} catch (AddressHandlerException ahe) {
    					Properties tagValues = new Properties();
    					tagValues.setProperty("ERR", ahe.getMessage());
    					exBldReslt =
    						ExceptionBuilder.parseException(
    						utility.getCallerContext(),
    						ruleFile,
    						"",
    						LIMTag.CSV_AddressHandlerError,
    						"Address format",
    						true,
    						ExceptionBuilderRule.NO_DEFAULT,
    						null,
    						null,
    						utility,
    						"BRMS",
    						Severity.UnRecoverable,
    						tagValues);
    				}
    			}
    
    			rv = new BrmsAddrListResp(utility);
    			Address[] addressArray = new Address[lfacsSagLuArray.length];
    			for (int i = 0; i < lfacsSagLuArray.length; i++)
    				addressArray[i] = lfacsSagLuArray[i].getAddress();
    		 	((BrmsAddrListResp) rv).setAddressArray(addressArray);
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
     * @param req           BrmsRetrieveLocReq
     * @param addressArray  Address[]
     * @param maxAddr int the max number of addresses to return
     * @param maxUnit int the max number of units to return
     * @exception InvalidData
     * @exception AccessDenied
     * @exception BusinessViolation
     * @exception DataNotFound
     * @exception SystemFailure
     * @exception NotImplemented
     * @exception ObjectNotFound
     */
    public static RetrieveLocResp handleMultiValid(
    	LIMBase utility,
    	RetrieveLocReq req,
    	AddressHandlerBRMS ahReq,
    	LFACSSagLu[] lfacsSagLuArray,
    	int maxAddr,
    	int maxUnit)
    	throws
    		InvalidData,
    		AccessDenied,
    		BusinessViolation,
    		DataNotFound,
    		SystemFailure,
    		NotImplemented,
    		ObjectNotFound {
    	RetrieveLocResp rv = null; //rv = Return Value
    	ExceptionBuilderResult exBldReslt = null;
    
    	String ruleFile = null;
    	ruleFile = (String) utility.getEnv(LIMTag.CSV_FileName_BRMS);
    
    	switch (lfacsSagLuArray.length) {
    		case 0 :
    			break;
    
    		case 1 :
    			try {
    				AddressHandlerBRMS brmsAddr = new AddressHandlerBRMS(lfacsSagLuArray[0].getAddress());
    				rv = new BrmsHitResp(utility, req);
    				((BrmsHitResp) rv).setBrmsAddr(brmsAddr);
    				((BrmsHitResp) rv).setCircuitId(lfacsSagLuArray[0].getCircuitId());
    				((BrmsHitResp) rv).setLpStatus(lfacsSagLuArray[0].getLpStatus());
    				((BrmsHitResp) rv).setAasgndHousNbr(req.getAddr().getAasgndHousNbr());
    				((BrmsHitResp) rv).setDescAddr(ahReq.getAddAddrInfo());
    				((BrmsHitResp) rv).setStNmInd(ahReq.getStNmInd());
    				break;
    			} catch (AddressHandlerException ahe) {
    				Properties tagValues = new Properties();
    				tagValues.setProperty("ERR", ahe.getMessage());
    				exBldReslt =
    					ExceptionBuilder.parseException(
    						utility.getCallerContext(),
    						ruleFile,
    						"",
    						LIMTag.CSV_AddressHandlerError,
    						"Address format",
    						true,
    						ExceptionBuilderRule.NO_DEFAULT,
    						null,
    						null,
    						utility,
    						"BRMS",
    						Severity.UnRecoverable,
    						tagValues);
    			}
    			break;
    		default :
    			for (int i = 0; i < lfacsSagLuArray.length; i++) {
    				try {
    					if (lfacsSagLuArray[i].getAddress().aFieldedAddress().aCity.theValue().trim()
    						.equalsIgnoreCase(ahReq.getCity())) {
    						AddressHandlerBRMS brmsAddr = new AddressHandlerBRMS(lfacsSagLuArray[i].getAddress());
    						rv = new BrmsHitResp(utility, req);
    						((BrmsHitResp) rv).setBrmsAddr(brmsAddr);
    						((BrmsHitResp) rv).setCircuitId(lfacsSagLuArray[i].getCircuitId());
    						((BrmsHitResp) rv).setLpStatus(lfacsSagLuArray[i].getLpStatus());
    						((BrmsHitResp) rv).setAasgndHousNbr(req.getAddr().getAasgndHousNbr());
    						((BrmsHitResp) rv).setDescAddr(ahReq.getAddAddrInfo());
    						((BrmsHitResp) rv).setStNmInd(req.getAddr().getStNmInd());
    						break;
    					}
    				} catch (org.omg.CORBA.BAD_OPERATION e) {
    				} catch (java.lang.NullPointerException e) {
    				} catch (AddressHandlerException ahe) {
    					Properties tagValues = new Properties();
    					tagValues.setProperty("ERR", ahe.getMessage());
    					exBldReslt =
    						ExceptionBuilder.parseException(
    							utility.getCallerContext(),
    							ruleFile,
    							"",
    							LIMTag.CSV_AddressHandlerError,
    							"Address format",
    							true,
    							ExceptionBuilderRule.NO_DEFAULT,
    							null,
    							null,
    							utility,
    							"BRMS",
    							Severity.UnRecoverable,
    							tagValues);
    				}
    			}
    
    			exBldReslt =
    					ExceptionBuilder.parseException(
    					utility.getCallerContext(),
    					ruleFile,
    					"",
    					LIMTag.CSV_AddressError,
    					"Multiple Addresses", 
    					true,
    					ExceptionBuilderRule.NO_DEFAULT,
    					null,
    					null,
    					utility,
    					"BRMS",
    					Severity.UnRecoverable,
    					null);
    
    			break;
    	}
    
    	if (rv != null) {
    		rv.setMaxAddresses(maxAddr);
    		rv.setMaxUnits(maxUnit);
    	}
    
    	return rv;
    }
    /**
     * Invoke handleException() to create a SystemFailure exception.  Hide the other
     * exception types.
     * Creation date: (3/6/01 4:59:19 PM)
     * @param util Utility
     * @param code String
     * @param text String
     * @exception SystemFailure
     */
    public static void handleSystemFailure(LIMBase util, String code, String text) throws SystemFailure
    {
    	try
    	{
    		
    	String ruleFile = null;
    	ruleFile = (String) util.getEnv(LIMTag.CSV_FileName_BRMS);
    		ExceptionBuilderResult exBldReslt = null;
    		exBldReslt =
    					ExceptionBuilder.parseException(
    					util.getCallerContext(),
    					ruleFile,
    					"",
    					LIMTag.CSV_InternalError,
    					null, 
    					true,
    					ExceptionBuilderRule.NO_DEFAULT,
    					null,
    					null,
    					util,
    					"BRMS",
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
