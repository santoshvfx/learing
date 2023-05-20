// $Id: PremisRetrieveLocReq.java,v 1.4 2004/04/29 14:28:18 db4252 Exp $

package com.sbc.eia.bis.BusinessInterface.premis;

import com.sbc.bccs.idl.helpers.TN;
import com.sbc.eia.bis.framework.BisContextManager;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.bis.lim.transactions.RetrieveLocation.RetrieveLocReq;
import com.sbc.eia.bis.lim.util.LIMBase;
import com.sbc.eia.idl.lim.helpers.AddressHandler;
import com.sbc.eia.idl.lim_types.ProviderLocationProperties;
import com.sbc.gwsvcs.service.premisserver.interfaces.Addr_t;
import com.sbc.gwsvcs.service.premisserver.interfaces.BascAddrInfo_t;
import com.sbc.gwsvcs.service.premisserver.interfaces.Header_t;
import com.sbc.gwsvcs.service.premisserver.interfaces.NpaPrfxLn_t;
import com.sbc.gwsvcs.service.premisserver.interfaces.PremisValdtAddrReq_t;
import com.sbc.gwsvcs.service.premisserver.interfaces.PrmStNm_t;
import com.sbc.gwsvcs.service.premisserver.interfaces.RapReq_t;
import com.sbc.gwsvcs.service.premisserver.interfaces.Scratch_t;
import com.sbc.gwsvcs.service.premisserver.interfaces.StNbrId_t;
import com.sbc.gwsvcs.service.premisserver.interfaces.SuppAddrInfo_t;
import com.sbc.gwsvcs.service.premisserver.interfaces.TrnsptType_e;
import com.sbc.gwsvcs.service.premisserver.interfaces.UnnbrdAddrIdent_t;
/**
 * This class extends RetrieveLocReq to specialize it for PREMIS.
 * Creation date: (4/3/01 1:04:38 PM)
 * @author: David Prager
 */
public class PremisRetrieveLocReq extends RetrieveLocReq
{
	protected String saga = "";
	protected boolean unitRequested = false;
    protected boolean exactMatchReq = false;
    /**
     * PremisRetrieveLocReq constructor.
     * @param utility LIMBase
     * @param address AddressHandler
     */
    public PremisRetrieveLocReq(LIMBase utility, AddressHandler address, ProviderLocationProperties[] aPropertiesToGet)
    {
    	super(utility, address, aPropertiesToGet);
        setUnitRequested();
    }
    /**
     * PremisRetrieveLocReq constructor.
     * @param utility LIMBase
     * @param service TN
     */
    public PremisRetrieveLocReq(LIMBase utility, TN service, ProviderLocationProperties[] aPropertiesToGet)
    {
    	super(utility, service, aPropertiesToGet);
    }
    /**
     * Getter method for saga.
     * Creation date: (3/23/01 11:16:24 AM)
     * @return String
     * @see #setSaga(String)
     */
    public String getSaga()
    {
    	return saga;
    }
    /**
     * Setter method for the saga.
     * Creation date: (3/23/01 11:16:24 AM)
     * @param newSaga String
     * @see #getSaga
     */
    public void setSaga(String newSaga)
    {
    	saga = (newSaga == null) ? PREMISTag.EMPTY_STRING : newSaga;
    }

    /**
     * Returns the exactMatchReq.
     * @return boolean
     */
    public boolean isExactMatchReq() {
        return exactMatchReq;
    }
    
    /**
     * Sets the exactMatchReq.
     * @param exactMatchReq The exactMatchReq to set
     */
    public void setExactMatchReq(boolean exactMatchReq) {
        this.exactMatchReq = exactMatchReq;
    }

    /**
     * Returns the unitRequested.
     * @return boolean
     */
    public boolean isUnitRequested() {
        return unitRequested;
    }

    /**
     * Sets the unitRequested.
     */
    public void setUnitRequested() {
        if (this.addr.getStructType().length()  > 0 ||
            this.addr.getStructValue().length() > 0 ||
            this.addr.getLevelType().length()   > 0 ||
            this.addr.getLevelValue().length()  > 0 ||
            this.addr.getUnitType().length()    > 0 ||
            this.addr.getUnitValue().length()   > 0)
            unitRequested = true;
        else
            unitRequested = false;
    }

    /**
     * Return my data in the form of a vicuna PremisValdtAddrReq_t object.
     * Creation date: (3/5/01 12:15:18 PM)
     * @return PremisValdtAddrReq_t
     */
    public PremisValdtAddrReq_t toVicuna()
    {
    	PremisValdtAddrReq_t rv = null;
    	
    	getUtility().log(LogEventId.AUDIT_TRAIL, "PremisRetrieveLocReq::toVicuna()|PremisValdtAddrReq_t::PremisValdtAddrReq_t()|PRE");
    
    	String userid = (new BisContextManager(getUtility().getCallerContext())).getUserId();
    	if (userid == null)
    		userid = "BIS";
    
    	Header_t hdr = new Header_t(userid, "LIMBIS", "", "", TrnsptType_e.RPC_TRNSPT, "");
    	
    	Scratch_t scratch = new Scratch_t(new char[] { '\0' });
    
    	Addr_t addrt = new Addr_t(
    		new BascAddrInfo_t(
    			new StNbrId_t((addr.getHousNbrPfx().length() > PREMISTag.HSE_NBR_PRFX_MAX) ?
    								addr.getHousNbrPfx().substring(0,PREMISTag.HSE_NBR_PRFX_MAX) :
    								addr.getHousNbrPfx(),
    						  (addr.getHousNbr().length() > PREMISTag.HSE_NBR_MAX) ?
    						  		addr.getHousNbr().substring(0,PREMISTag.HSE_NBR_MAX) :
    						  		addr.getHousNbr(),
    						  addr.getHousNbrSfx()), 
    						  addr.getAasgndHousNbr(),
    			new PrmStNm_t(addr.getStDir(),
    						  (addr.getStName().length() > PREMISTag.ST_NM_MAX) ?
    								addr.getStName().substring(0,PREMISTag.ST_NM_MAX) :
    								addr.getStName(),
    						  addr.getStThorofare(),
    						  addr.getStNameSfx()), 
    						  (addr.getCity().length() > PREMISTag.COM_MAX) ?
    								addr.getCity().substring(0,PREMISTag.COM_MAX) :
    								addr.getCity(), 
    						  addr.getState()),
            new SuppAddrInfo_t(addr.getStructValue(), addr.getStructType(),
                               addr.getLevelValue(), addr.getLevelType(), 
                               addr.getUnitValue(), addr.getUnitType()),
    		new UnnbrdAddrIdent_t(addr.getRoute(),addr.getBox(),"","",(char)'\0',(char)'\0'),
    			addr.getAddAddrInfo() );
    
    	RapReq_t rap = new RapReq_t( getSaga(), addr.getPostalCode(),
    		new NpaPrfxLn_t(tn.getNpa(),tn.getNxx(),tn.getLine()), addrt );
    
    	rv = new PremisValdtAddrReq_t(hdr,rap,scratch,"");
    	
    	getUtility().log(LogEventId.AUDIT_TRAIL, "PremisRetrieveLocReq::toVicuna()|PremisValdtAddrReq_t::PremisValdtAddrReq_t()|POST");
    	
    	return rv;
    }
}
