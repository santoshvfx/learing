// $Id: PremisResponseFactory.java,v 1.3 2008/10/29 22:41:17 gg2712 Exp $

package com.sbc.eia.bis.BusinessInterface.ServiceAddress.premis;

import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilder;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilderResult;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilderRule;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.bis.lim.transactions.common.RetrieveLocResp;
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
import com.sbc.gwsvcs.access.vicuna.EventResultPair;
import com.sbc.gwsvcs.service.premisserver.PREMISServerAccess;
import com.sbc.gwsvcs.service.premisserver.interfaces.PremisAddrMenuResp_t;
import com.sbc.gwsvcs.service.premisserver.interfaces.PremisAddrRngeMenuResp_t;
import com.sbc.gwsvcs.service.premisserver.interfaces.PremisBascAddrMenuResp_t;
import com.sbc.gwsvcs.service.premisserver.interfaces.PremisDescNmMenuResp_t;
import com.sbc.gwsvcs.service.premisserver.interfaces.PremisGeoSegMenuResp_t;
import com.sbc.gwsvcs.service.premisserver.interfaces.PremisHITResp_t;
import com.sbc.gwsvcs.service.premisserver.interfaces.PremisSagaMenuResp_t;
import com.sbc.gwsvcs.service.premisserver.interfaces.PremisStAddrRngeMenuResp_t;
import com.sbc.gwsvcs.service.premisserver.interfaces.PremisStNmMenuResp_t;
import com.sbc.gwsvcs.service.premisserver.interfaces.PremisSuppAddrMenuResp_t;
import com.sbc.gwsvcs.service.premisserver.interfaces.PremisTnMatchMenuResp_t;
import com.sbc.gwsvcs.service.premisserver.interfaces.PremisUnadrmGsgmResp_t;
import com.sbc.gwsvcs.service.premisserver.interfaces.PremisUnnbrdAddrRngeMenuResp_t;
import com.sbc.gwsvcs.service.premisserver.interfaces.PremisUnnbrdMenuResp_t;
import com.sbc.gwsvcs.service.premisserver.interfaces.PremisZipMenuResp_t;

/**
 * This factory class produces Premis Response objects of the
 * appropriate type.
 * Creation date: (6/1/01 9:45:53 AM)
 * @author: David Prager
 */
public class PremisResponseFactory {
	
	public static final String PREMIS_PARTIAL_STREET_NAME_INDICATOR = "-1";
	
    /**
     * Private default constructor.  You never need to instantiate this class.
     * Creation date: (6/1/01 9:46:29 AM)
     */
    private PremisResponseFactory() {}
    /**
     * Factory method to instantiate a RetrieveLocResp object of the appropriate subtype.
     * Creation date: (3/6/01 4:35:51 PM)
     * @return RetrieveLocResp
     * @param utility LIMBase
     * @param result EventResultPair
     * @param maxAddr int the max number of addresses to return
     * @param maxUnit int the max number of units to return
     * @param unitRequested  a boolean
     * @exception SystemFailure
     * @exception InvalidData
     */
    public static RetrieveLocResp fromVicuna(LIMBase utility,
                                             EventResultPair result,
                                             PremisRetrieveLocReq aRequest,
                                             int maxAddr,
                                             int maxUnit) 
    	throws SystemFailure
    {
    	RetrieveLocResp rv = null;  //rv = Return Value
    	ExceptionBuilderResult exBldReslt = null;
    	String ruleFile = (String) utility.getEnv(LIMTag.CSV_FileName_PREMIS);
    	
    	switch (result.getEventNbr())
    	{
    		case PREMISServerAccess.PREMIS_HIT_RESP_NBR:
    			
    			PremisHITResp_t premisResp = (PremisHITResp_t) result.getTheObject();

    			utility.log(LogEventId.DEBUG_LEVEL_2, "premisResp.HITPktResp.RTCD:" + premisResp.HITPktResp.RTCD);

    			if(premisResp.HITPktResp.RTCD != null 
        			&& premisResp.HITPktResp.RTCD.length() > 4
    				&& premisResp.HITPktResp.RTCD.substring(3, 5).equalsIgnoreCase(PREMIS_PARTIAL_STREET_NAME_INDICATOR))
        		{
    				rv = new PremisAddrListResp(utility, premisResp, maxAddr);
        		}
    			else
    			{
    				rv = new PremisHit(utility, premisResp, aRequest);
    			}
    			break;
    			
    		case PREMISServerAccess.PREMIS_SAGA_MENU_RESP_NBR:
    			rv = new PremisSagaResp(utility,(PremisSagaMenuResp_t) result.getTheObject()); break;
    			
    		case PREMISServerAccess.PREMIS_ZIP_MENU_RESP_NBR:
    			rv = new PremisSagaResp(utility,(PremisZipMenuResp_t) result.getTheObject()); break;
    			
    		case PREMISServerAccess.PREMIS_ST_NM_MENU_RESP_NBR:
    			rv = new PremisAddrRangeResp(utility,(PremisStNmMenuResp_t) result.getTheObject(), maxAddr); break;
    			
    		case PREMISServerAccess.PREMIS_DESC_NM_MENU_RESP_NBR:
    			rv = new PremisAddrListResp(utility,(PremisDescNmMenuResp_t) result.getTheObject(), maxAddr); break;
    			
    		case PREMISServerAccess.PREMIS_ADDR_RNGE_MENU_RESP_NBR:
    			rv = new PremisAddrRangeResp(utility,(PremisAddrRngeMenuResp_t) result.getTheObject(), maxAddr); break;
    			
    		case PREMISServerAccess.PREMIS_ADDR_MENU_RESP_NBR:
    			rv = new PremisAddrRangeResp(utility,(PremisAddrMenuResp_t) result.getTheObject(), maxAddr); break;
    			
    		case PREMISServerAccess.PREMIS_ST_ADDR_RANGE_MENU_RESP_NBR:
    			rv = new PremisAddrRangeResp(utility,(PremisStAddrRngeMenuResp_t) result.getTheObject(), maxAddr); break;
    			
    		case PREMISServerAccess.PREMIS_UNNBRD_MENU_RESP_NBR:
    			rv = new PremisAddrRangeResp(utility,(PremisUnnbrdMenuResp_t) result.getTheObject(), maxAddr); break;
    			
    		case PREMISServerAccess.PREMIS_BASC_ADDR_MENU_RESP_NBR:
    			rv = new PremisAddrListResp(utility,(PremisBascAddrMenuResp_t) result.getTheObject(), maxAddr); break;
    			
    		case PREMISServerAccess.PREMIS_UNADRM_MENU_RESP_NBR:
    			rv = new PremisAddrListResp(utility,(PremisUnnbrdAddrRngeMenuResp_t) result.getTheObject(), maxAddr); break;
    			
    		case PREMISServerAccess.PREMIS_GSGM_MENU_RESP_NBR:
    			rv = new PremisAddrRangeResp(utility,(PremisGeoSegMenuResp_t) result.getTheObject(), maxAddr); break;
    			
    		case PREMISServerAccess.PREMIS_TN_MATCH_MENU_RESP_NBR:
    			rv = new PremisTNMenu(utility,(PremisTnMatchMenuResp_t) result.getTheObject()); break;
    			
    		case PREMISServerAccess.PREMIS_SUPP_ADDR_MENU_RESP_NBR:
    			if (aRequest.isUnitRequested()){
    				rv = new PremisAddrListResp(utility,(PremisSuppAddrMenuResp_t) result.getTheObject(), maxUnit); break;
    			}
    			else{
    				rv = new PremisHit(utility,(PremisSuppAddrMenuResp_t) result.getTheObject(), aRequest); break;
    			}
    				
    		case PREMISServerAccess.PREMIS_UNADRM_GSGM_MENU_RESP_NBR:
    			rv = new PremisAddrListResp(utility,(PremisUnadrmGsgmResp_t) result.getTheObject(), maxAddr); break;
    			
    		default:  //should never land here because PREMISServerHelper would throw an exception
    			handleSystemFailure(ruleFile, utility, LIMTag.CSV_InternalError,
    				"RetrieveLocResp.fromVicuna(): Unknown EventID <" + 
    				result.getEventNbr() + "> From PREMISServer!");
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
     * @param ruleFile  a String
     * @param utility   a LIMBase object
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
    					"PREMISServer",
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
