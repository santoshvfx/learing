// $Id: PremisTNMenu.java,v 1.2 2007/10/06 01:04:24 gg2712 Exp $

package com.sbc.eia.bis.BusinessInterface.ServiceAddress.premis;

import com.sbc.eia.bis.framework.BisContextManager;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.bis.lim.transactions.common.RetrieveLocResp;
import com.sbc.eia.bis.lim.util.LIMBase;
import com.sbc.eia.idl.lim.helpers.AddressHandlerPremis;
import com.sbc.gwsvcs.service.premisserver.interfaces.Addr_t;
import com.sbc.gwsvcs.service.premisserver.interfaces.AppPrmRespItem_t;
import com.sbc.gwsvcs.service.premisserver.interfaces.BascAddrInfo_t;
import com.sbc.gwsvcs.service.premisserver.interfaces.FacActnLnItem_t;
import com.sbc.gwsvcs.service.premisserver.interfaces.Header_t;
import com.sbc.gwsvcs.service.premisserver.interfaces.NpaPrfxLn_t;
import com.sbc.gwsvcs.service.premisserver.interfaces.PremisTnMatchMenuResp_t;
import com.sbc.gwsvcs.service.premisserver.interfaces.PremisValdtAddrReq_t;
import com.sbc.gwsvcs.service.premisserver.interfaces.PrmStNm_t;
import com.sbc.gwsvcs.service.premisserver.interfaces.RapReq_t;
import com.sbc.gwsvcs.service.premisserver.interfaces.Scratch_t;
import com.sbc.gwsvcs.service.premisserver.interfaces.StNbrId_t;
import com.sbc.gwsvcs.service.premisserver.interfaces.SuppAddrInfo_t;
import com.sbc.gwsvcs.service.premisserver.interfaces.TnMatchMenuItem_t;
import com.sbc.gwsvcs.service.premisserver.interfaces.TnMatchMenuProcStatus_t;
import com.sbc.gwsvcs.service.premisserver.interfaces.TrnsptType_e;
import com.sbc.gwsvcs.service.premisserver.interfaces.UnnbrdAddrIdent_t;

/**
 * PremisTNMenu class.
 * Creation date: (2/21/02 7:58:55 AM)
 * @author: David Brawley
 */
public class PremisTNMenu extends RetrieveLocResp {
	protected AppPrmRespItem_t apr = null;
	protected FacActnLnItem_t fal = null;
	protected AddressHandlerPremis premisAddr  = null;
	private boolean stNmInd = false;
    /**
     * AsonMultiValidation constructor.
     * @param utility LIMBase
     */
    public PremisTNMenu(LIMBase utility) {
    	super(utility);
    }
    /**
     * Construct an address from a PREMISServer TN Match Menu.
     * Creation date: (3/13/01 2:21:30 PM)
     * @param utility LIMBase
     * @param result PremisTnMatchMenuResp_t
     */
    public PremisTNMenu(LIMBase utility, PremisTnMatchMenuResp_t result)
    {
    	super(utility);
    	apr = result.AppPrmRespItem;
    	fal = apr.FacActnLn;
    
    	for (int i = 0; i < result.TnMatchMenuPktResp.TnMatchMenuProcStatus.length; i++)
    	{
    		TnMatchMenuProcStatus_t menu = result.TnMatchMenuPktResp.TnMatchMenuProcStatus[i];
    
    		for (int j = 0; j < menu.TnMatchMenu.length; j++)
    		{
    			TnMatchMenuItem_t item = menu.TnMatchMenu[j];
    			getUtility().log(LogEventId.INFO_LEVEL_2,
    				"PREMIS.HandleTNMenu(): Address[" + j + "] Status[" + item.LtdLnInfo.LN_STS_ID + "] ");
    
    			if (PREMIS.lineIsWorking(item.LtdLnInfo))
    			{
    				premisAddr = new AddressHandlerPremis(
    					apr.CntlData.Addr.UnnbrdAddrIdent.PSTL_RTE_CD.trim(),
    					apr.CntlData.Addr.UnnbrdAddrIdent.BOX_CD.trim(),
    					item.BascAddrInfo.StNbrId.SAD_HOUS_PRFX.trim(),
    					item.BascAddrInfo.StNbrId.SAD_HOUS_NBR.trim(),
    					item.BascAddrInfo.ASGND_HOUS_NBR_ID.trim(),
    					item.BascAddrInfo.StNbrId.SAD_HOUS_NBR_SUFX.trim(),
    					item.BascAddrInfo.StNm.SAD_ST_DRCTL.trim(),
    					item.BascAddrInfo.StNm.SAD_ST_NM.trim(),
    					item.BascAddrInfo.StNm.SAD_THRFR.trim(),
    					item.BascAddrInfo.StNm.SAD_ST_SUFX.trim(),
    					item.BascAddrInfo.CMTY_NM.trim(),
    					item.BascAddrInfo.STATE_CD.trim(),
    					item.ZC.trim(),
    					PREMISTag.EMPTY_STRING,		//county
    					PREMISTag.EMPTY_STRING,     //country
                        item.SuppAddrInfo.STRUCT_TYPE,
                        item.SuppAddrInfo.STRUCT_ID,
                        item.SuppAddrInfo.ELEV_TYPE,
                        item.SuppAddrInfo.ELEV_ID,
                        item.SuppAddrInfo.UNIT_TYPE,
                        item.SuppAddrInfo.UNIT_ID,
                        fal.DESC_ADDR.trim(),
    					false);
    			}
    		}
    	}
    }

    /**
     * Return TN Menu data in the form of a vicuna PremisValdtAddrReq_t object.
     * Creation date: (3/5/01 12:15:18 PM)
     * @param request  a PremisRetrieveLocReq object
     * @return PremisValdtAddrReq_t
     */
    public PremisValdtAddrReq_t getValdtAddrReq(PremisRetrieveLocReq request)
    {
    	premisAddr.toString();
    	
    	PremisValdtAddrReq_t rv = null;
    	
    	String userid = (new BisContextManager(getUtility().getCallerContext())).getUserId();
    
    	if (userid == null)
    		userid = "BIS";
    
    	Header_t hdr = new Header_t(userid, "LIMBIS", "", "", TrnsptType_e.RPC_TRNSPT, "");
    	
    	Scratch_t scratch = new Scratch_t(new char[] { '\0' });
    
    	Addr_t addrt = new Addr_t(
    		new BascAddrInfo_t(
    			new StNbrId_t((premisAddr.getHousNbrPfx().length() > PREMISTag.HSE_NBR_PRFX_MAX) ?
    								premisAddr.getHousNbrPfx().substring(0,PREMISTag.HSE_NBR_PRFX_MAX) :
    								premisAddr.getHousNbrPfx(),
    						  (premisAddr.getHousNbr().length() > PREMISTag.HSE_NBR_MAX) ?
    						  		premisAddr.getHousNbr().substring(0,PREMISTag.HSE_NBR_MAX) :
    						  		premisAddr.getHousNbr(),
    						  premisAddr.getHousNbrSfx()), 
    						  premisAddr.getAasgndHousNbr(),
    			new PrmStNm_t(premisAddr.getStDir(),
    						  (premisAddr.getStName().length() > PREMISTag.ST_NM_MAX) ?
    								premisAddr.getStName().substring(0,PREMISTag.ST_NM_MAX) :
    								premisAddr.getStName(),
    						  premisAddr.getStThorofare(),
    						  premisAddr.getStNameSfx()), 
    						  (premisAddr.getCity().length() > PREMISTag.COM_MAX) ?
    								premisAddr.getCity().substring(0,PREMISTag.COM_MAX) :
    								premisAddr.getCity(), 
    						  premisAddr.getState()),
    		new SuppAddrInfo_t(premisAddr.getStructValue(), premisAddr.getStructType(),
    			               premisAddr.getLevelValue(), premisAddr.getLevelType(), 
                               premisAddr.getUnitValue(), premisAddr.getUnitType()),
    		new UnnbrdAddrIdent_t(premisAddr.getRoute(),premisAddr.getBox(),"","",(char)'\0',(char)'\0'),
    			premisAddr.getAddAddrInfo() );
    
    	RapReq_t rap = new RapReq_t( request.getSaga(),  premisAddr.getPostalCode(),
    		new NpaPrfxLn_t("","",""), addrt );
    
    	rv = new PremisValdtAddrReq_t(hdr,rap,scratch,"");
    	
    	return rv;
    }
}
