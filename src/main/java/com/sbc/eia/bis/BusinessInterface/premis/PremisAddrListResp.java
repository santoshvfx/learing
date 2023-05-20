// $Id: PremisAddrListResp.java,v 1.7 2008/10/29 22:38:36 gg2712 Exp $

package com.sbc.eia.bis.BusinessInterface.premis;

import java.util.List;

import com.sbc.eia.bis.lim.transactions.RetrieveLocation.AddrAltResp;
import com.sbc.eia.bis.lim.util.LIMBase;
import com.sbc.eia.idl.bis_types.SystemFailure;
import com.sbc.eia.idl.lim.helpers.AddressHandlerPremis;
import com.sbc.gwsvcs.service.premisserver.interfaces.AppPrmRespItem_t;
import com.sbc.gwsvcs.service.premisserver.interfaces.BascAddrMenuItem_t;
import com.sbc.gwsvcs.service.premisserver.interfaces.BascAddrMenuProcStatus_t;
import com.sbc.gwsvcs.service.premisserver.interfaces.DescNmMenuItem_t;
import com.sbc.gwsvcs.service.premisserver.interfaces.DescNmMenuProcStatus_t;
import com.sbc.gwsvcs.service.premisserver.interfaces.FacActnLnItem_t;
import com.sbc.gwsvcs.service.premisserver.interfaces.PremisBascAddrMenuResp_t;
import com.sbc.gwsvcs.service.premisserver.interfaces.PremisDescNmMenuResp_t;
import com.sbc.gwsvcs.service.premisserver.interfaces.PremisHITResp_t;
import com.sbc.gwsvcs.service.premisserver.interfaces.PremisSuppAddrMenuResp_t;
import com.sbc.gwsvcs.service.premisserver.interfaces.PremisUnadrmGsgmResp_t;
import com.sbc.gwsvcs.service.premisserver.interfaces.PremisUnnbrdAddrRngeMenuResp_t;
import com.sbc.gwsvcs.service.premisserver.interfaces.SuppAddrMenuItem_t;
import com.sbc.gwsvcs.service.premisserver.interfaces.SuppAddrMenuProcStatus_t;
import com.sbc.gwsvcs.service.premisserver.interfaces.UnadrmGsgmProcStatus_t;
import com.sbc.gwsvcs.service.premisserver.interfaces.UnnbrdAddrRngeMenuItem_t;
import com.sbc.gwsvcs.service.premisserver.interfaces.UnnbrdAddrRngeMenuProcStatus_t;

/**
 * Build a list of addresses from Premis menus.
 * Creation date: (3/13/01 2:00:56 PM)
 * @author: David Prager
 */
public class PremisAddrListResp extends AddrAltResp
{
	protected AppPrmRespItem_t apr = null;
	protected FacActnLnItem_t fal = null;
	
    /**
     * This default (package) access constructor allows other classes (see PremisComboResp)
     * to use this class in an atypical way.
     * Creation date: (3/16/01 10:05:16 AM)
     * @param utility LIMBase
     * @param appPrmRespItem AppPrmRespItem_t
     */
    PremisAddrListResp(LIMBase utility,AppPrmRespItem_t appPrmRespItem)
    {
    	super(utility);
    	apr = appPrmRespItem;
    	fal = apr.FacActnLn;
    }
    /**
     * Construct an address list from a PREMISServer Hit Response.
     * Creation date: (3/13/01 2:15:21 PM)
     * @param utility LIMBase
     * @param result PremisHITResp_t
     * @param maxAddr int
     * @exception SystemFailure
     */
    public PremisAddrListResp(LIMBase utility, PremisHITResp_t result, int maxAddr)
    	throws SystemFailure
    {
    	super(utility);
    	apr = result.AppPrmRespItem;
    	fal = apr.FacActnLn;
    	setMaxAddresses(maxAddr);
    	
    	addrList.add(new AddressHandlerPremis(
        		apr.CntlData.Addr.UnnbrdAddrIdent.PSTL_RTE_CD.trim(),
        		apr.CntlData.Addr.UnnbrdAddrIdent.BOX_CD.trim(),
        		fal.PrmAddr.BascAddrInfo.StNbrId.SAD_HOUS_PRFX.trim(),
        		fal.PrmAddr.BascAddrInfo.StNbrId.SAD_HOUS_NBR.trim(),
        		fal.PrmAddr.BascAddrInfo.ASGND_HOUS_NBR_ID.trim(),
        		fal.PrmAddr.BascAddrInfo.StNbrId.SAD_HOUS_NBR_SUFX.trim(),
        		fal.PrmAddr.BascAddrInfo.StNm.SAD_ST_DRCTL.trim(),
        		fal.PrmAddr.BascAddrInfo.StNm.SAD_ST_NM.trim(),
        		fal.PrmAddr.BascAddrInfo.StNm.SAD_THRFR.trim(),
        		fal.PrmAddr.BascAddrInfo.StNm.SAD_ST_SUFX.trim(),
        		fal.PrmAddr.BascAddrInfo.CMTY_NM.trim(),
        		fal.PrmAddr.BascAddrInfo.STATE_CD.trim(),
        		fal.SagInfo.ZC.trim(),
        		PREMISTag.EMPTY_STRING,   //county
        		PREMISTag.EMPTY_STRING,   //country
                fal.PrmAddr.SuppAddrInfo.STRUCT_TYPE,
                fal.PrmAddr.SuppAddrInfo.STRUCT_ID,
                fal.PrmAddr.SuppAddrInfo.ELEV_TYPE,
                fal.PrmAddr.SuppAddrInfo.ELEV_ID,
                fal.PrmAddr.SuppAddrInfo.UNIT_TYPE,
                fal.PrmAddr.SuppAddrInfo.UNIT_ID,
                fal.DESC_ADDR.trim(),
        		false).getAddress());
    }    
    /**
     * Construct an address list from a PREMISServer Basic Address Menu.
     * Creation date: (3/13/01 2:15:21 PM)
     * @param utility LIMBase
     * @param result PremisBascAddrMenuResp_t
     * @param maxAddr int
     * @exception SystemFailure
     */
    public PremisAddrListResp(LIMBase utility, PremisBascAddrMenuResp_t result, int maxAddr)
    	throws SystemFailure
    {
    	super(utility);
    	apr = result.AppPrmRespItem;
    	fal = apr.FacActnLn;
    	setMaxAddresses(maxAddr);
    
    	for (int i = 0; i < result.BascAddrMenuPktResp.BascAddrMenuProcStatus.length; i++)
    	{
    		BascAddrMenuProcStatus_t menu = result.BascAddrMenuPktResp.BascAddrMenuProcStatus[i];
    
    		for (int j = 0; j < menu.BascAddrMenu.length; j++)
    		{
    			BascAddrMenuItem_t item = menu.BascAddrMenu[j];
    
    			addrList.add(new AddressHandlerPremis(
    				apr.CntlData.Addr.UnnbrdAddrIdent.PSTL_RTE_CD.trim(),
    				apr.CntlData.Addr.UnnbrdAddrIdent.BOX_CD.trim(),
    				item.StNbrId.SAD_HOUS_PRFX.trim(),
    				item.StNbrId.SAD_HOUS_NBR.trim(),
    				item.ASGND_HOUS_NBR_ID.trim(),
    				item.StNbrId.SAD_HOUS_NBR_SUFX.trim(),
    				item.StNm.SAD_ST_DRCTL.trim(),
    				item.StNm.SAD_ST_NM.trim(),
    				item.StNm.SAD_THRFR.trim(),
    				item.StNm.SAD_ST_SUFX.trim(),
    				item.CMTY_NM.trim(),
    				apr.CntlData.Addr.BascAddrInfo.STATE_CD.trim(),
    				fal.SagInfo.ZC.trim(),
    				PREMISTag.EMPTY_STRING,	//county
    				PREMISTag.EMPTY_STRING, //country
                    PREMISTag.EMPTY_STRING, //structType
                    PREMISTag.EMPTY_STRING, //structValue
                    PREMISTag.EMPTY_STRING, //levelType
                    PREMISTag.EMPTY_STRING, //levelValue
                    PREMISTag.EMPTY_STRING, //unitType
                    PREMISTag.EMPTY_STRING, //unitValue
                    fal.DESC_ADDR.trim(),
    				false).getAddress());
    
    			if (getMaxAddresses() != NO_SIZE_LIMIT && addrList.size() >= getMaxAddresses())
    				return;
    		}
    	}
    }
    /**
     * Construct an address list from a PREMISServer Descriptive Name Menu.
     * Creation date: (3/13/01 2:04:12 PM)
     * @param utility LIMBase
     * @param result PremisDescNmMenuResp_t
     * @param maxAddr int
     * @exception SystemFailure
     */
    public PremisAddrListResp(LIMBase utility, PremisDescNmMenuResp_t result, int maxAddr)
    	throws SystemFailure
    {
    	super(utility);
    	apr = result.AppPrmRespItem;
    	fal = apr.FacActnLn;
    	setMaxAddresses(maxAddr);
    
    	for (int i = 0; i < result.DescNmMenuPktResp.DescNmMenuProcStatus.length; i++)
    	{
    		DescNmMenuProcStatus_t menu = result.DescNmMenuPktResp.DescNmMenuProcStatus[i];
    
    		for (int j = 0; j < menu.DescNmMenu.length; j++)
    		{
    			DescNmMenuItem_t item = menu.DescNmMenu[j];
    
    			addrList.add(new AddressHandlerPremis(
    				apr.CntlData.Addr.UnnbrdAddrIdent.PSTL_RTE_CD.trim(),
    				apr.CntlData.Addr.UnnbrdAddrIdent.BOX_CD.trim(),
    				item.StNbrId.SAD_HOUS_PRFX.trim(),
    				item.StNbrId.SAD_HOUS_NBR.trim(),
    				item.ASGND_HOUS_NBR_ID.trim(),
    				item.StNbrId.SAD_HOUS_NBR_SUFX.trim(),
    				item.StNm.SAD_ST_DRCTL.trim(),
    				item.StNm.SAD_ST_NM.trim(),
    				item.StNm.SAD_THRFR.trim(),
    				item.StNm.SAD_ST_SUFX.trim(),
    				item.CMTY_NM.trim(),
    				apr.CntlData.Addr.BascAddrInfo.STATE_CD.trim(),
    				fal.SagInfo.ZC.trim(),
    				PREMISTag.EMPTY_STRING,	//county
    				PREMISTag.EMPTY_STRING, //country
    				PREMISTag.EMPTY_STRING, //structType
                    PREMISTag.EMPTY_STRING, //structValue
                    PREMISTag.EMPTY_STRING, //levelType
                    PREMISTag.EMPTY_STRING, //levelValue
                    PREMISTag.EMPTY_STRING, //unitType
                    PREMISTag.EMPTY_STRING, //unitValue
                    item.DESC_ADDR.trim(),
    				false).getAddress());
    
    			if (getMaxAddresses() != NO_SIZE_LIMIT && addrList.size() >= getMaxAddresses())
    				return;
    		}
    	}
    }
    /**
     * Construct this object from a PREMISServer Supplemental Address Menu.  This menu is
     * considered a list because it includes multiple units.
     * Creation date: (3/14/01 12:12:31 PM)
     * @param utility LIMBase
     * @param result PremisSuppAddrMenuResp_t
     * @param maxUnit int
     * @exception SystemFailure
     */
    public PremisAddrListResp(LIMBase utility, PremisSuppAddrMenuResp_t result, int maxUnit)
    	throws SystemFailure
    {
    	super(utility);
    	apr = result.AppPrmRespItem;
    	fal = apr.FacActnLn;
    	setMaxUnits(maxUnit);
        int unitCount = 0;
    		
    	for (int i = 0; i < result.SuppAddrMenuPktResp.SuppAddrMenuProcStatus.length; i++)
    	{
    		SuppAddrMenuProcStatus_t menu = result.SuppAddrMenuPktResp.SuppAddrMenuProcStatus[i];
            unitCount = 0;
    
    		for (int j = 0; j < menu.SuppAddrMenu.length; j++)
    		{
                SuppAddrMenuItem_t item = menu.SuppAddrMenu[j];
                                
                addrList.add(new AddressHandlerPremis(
                    apr.CntlData.Addr.UnnbrdAddrIdent.PSTL_RTE_CD.trim(),
                    apr.CntlData.Addr.UnnbrdAddrIdent.BOX_CD.trim(),
                    fal.PrmAddr.BascAddrInfo.StNbrId.SAD_HOUS_PRFX.trim(),
                    fal.PrmAddr.BascAddrInfo.StNbrId.SAD_HOUS_NBR.trim(),
                    fal.PrmAddr.BascAddrInfo.ASGND_HOUS_NBR_ID.trim(),
                    fal.PrmAddr.BascAddrInfo.StNbrId.SAD_HOUS_NBR_SUFX.trim(),
                    fal.PrmAddr.BascAddrInfo.StNm.SAD_ST_DRCTL.trim(),
                    fal.PrmAddr.BascAddrInfo.StNm.SAD_ST_NM.trim(),
                    fal.PrmAddr.BascAddrInfo.StNm.SAD_THRFR.trim(),
                    fal.PrmAddr.BascAddrInfo.StNm.SAD_ST_SUFX.trim(),
                    fal.PrmAddr.BascAddrInfo.CMTY_NM.trim(),
                    fal.PrmAddr.BascAddrInfo.STATE_CD.trim(),
                    fal.SagInfo.ZC.trim(),
                    PREMISTag.EMPTY_STRING, //county
                    PREMISTag.EMPTY_STRING, //country
                    item.SuppAddrInfo.STRUCT_TYPE,
                    item.SuppAddrInfo.STRUCT_ID,
                    item.SuppAddrInfo.ELEV_TYPE,
                    item.SuppAddrInfo.ELEV_ID,
                    item.SuppAddrInfo.UNIT_TYPE,
                    item.SuppAddrInfo.UNIT_ID,
                    fal.DESC_ADDR.trim(),
                    false).getAddress());

                unitCount++;
        
                if (getMaxUnits() != NO_SIZE_LIMIT && unitCount >= getMaxUnits())
                    return;
 
                if (getMaxAddresses() != NO_SIZE_LIMIT && addrList.size() >= getMaxAddresses())
                    return;

    		}
    	}
    }
    /**
     * Construct an address list from a PREMISServer Unnumbered Address Geographic Segment Menu.
     * Creation date: (3/13/01 2:18:44 PM)
     * @param utility LIMBase
     * @param result PremisUnadrmGsgmResp_t
     * @param maxAddr int
     * @exception SystemFailure
     */
    public PremisAddrListResp(LIMBase utility, PremisUnadrmGsgmResp_t result, int maxAddr)
    	throws SystemFailure
    {
    	super(utility);
    	apr = result.AppPrmRespItem;
    	fal = apr.FacActnLn;
    	setMaxAddresses(maxAddr);
    
    	for (int i = 0; i < result.UnadrmGsgmPktResp.UnadrmGsgmProcStatus.length; i++)
    	{
    		UnadrmGsgmProcStatus_t menu = result.UnadrmGsgmPktResp.UnadrmGsgmProcStatus[i];
    
    		for (int j = 0; j < menu.UnadrmGsgm.UnnbrdAddrRngeMenu.length; j++)
    		{
    			addToList(menu.UnadrmGsgm.UnnbrdAddrRngeMenu[j]);
    			
    			if (getMaxAddresses() != NO_SIZE_LIMIT && getAddrList().size() > getMaxAddresses())
    				return;
    		}
     	}
    }
    /**
     * Construct an address list from a PREMISServer Unnumbered Address Range Menu.
     * Creation date: (3/13/01 2:18:44 PM)
     * @param utility LIMBase
     * @param result PremisUnnbrdAddrRngeMenuResp_t
     * @param maxAddr int
     * @exception SystemFailure
     */
    public PremisAddrListResp(LIMBase utility, PremisUnnbrdAddrRngeMenuResp_t result, int maxAddr)
    	throws SystemFailure
    {
    	super(utility);
    	apr = result.AppPrmRespItem;
    	fal = apr.FacActnLn;
    	setMaxAddresses(maxAddr);
    
    	for (int i = 0; i < result.UnnbrdAddrRngeMenuPktResp.UnnbrdAddrRngeMenuProcStatus.length; i++)
    	{
    		UnnbrdAddrRngeMenuProcStatus_t menu =
    			result.UnnbrdAddrRngeMenuPktResp.UnnbrdAddrRngeMenuProcStatus[i];
    
    		for (int j = 0; j < menu.Unadrm.UnnbrdAddrRngeMenu.length; j++)
    		{
    			addToList(menu.Unadrm.UnnbrdAddrRngeMenu[j]);
    
    			if (getMaxAddresses() != NO_SIZE_LIMIT && addrList.size() >= getMaxAddresses())
    				return;
    		}
    	}
    }
    /**
     * Add an address to the list from an Unnumbered Address Range Menu Item.
     * Creation date: (3/16/01 10:14:40 AM)
     * @param item UnnbrdAddrRngeMenuItem_t
     */
    protected void addToList(UnnbrdAddrRngeMenuItem_t item)
    {
    	/**
    	 *  Check for detail (fal) section of output first, the Street Name may not be available.
    	 *  If data is there, use it. Otherwise, use the Control (apr) section data.
    	 */
    	if( fal.PrmAddr.BascAddrInfo.StNm.SAD_ST_NM.trim().length() > 0 ) {
    		addrList.add(new AddressHandlerPremis(
    			apr.CntlData.Addr.UnnbrdAddrIdent.PSTL_RTE_CD.trim(),
    			apr.CntlData.Addr.UnnbrdAddrIdent.BOX_CD.trim(),
    			fal.PrmAddr.BascAddrInfo.StNbrId.SAD_HOUS_PRFX.trim(),
    			fal.PrmAddr.BascAddrInfo.StNbrId.SAD_HOUS_NBR.trim(),
    			item.ASGND_HOUS_NBR_ID.trim(),
    			fal.PrmAddr.BascAddrInfo.StNbrId.SAD_HOUS_NBR_SUFX.trim(),
    			fal.PrmAddr.BascAddrInfo.StNm.SAD_ST_DRCTL.trim(),
    			fal.PrmAddr.BascAddrInfo.StNm.SAD_ST_NM.trim(),
    			fal.PrmAddr.BascAddrInfo.StNm.SAD_THRFR.trim(),
    			fal.PrmAddr.BascAddrInfo.StNm.SAD_ST_SUFX.trim(),
    			item.CMTY_NM.trim(),
    			item.STATE_CD.trim(),
    			item.ZC.trim(),
    			PREMISTag.EMPTY_STRING,	//county
    			PREMISTag.EMPTY_STRING, //country
                item.SuppAddrInfo.STRUCT_TYPE,
                item.SuppAddrInfo.STRUCT_ID,
                item.SuppAddrInfo.ELEV_TYPE,
                item.SuppAddrInfo.ELEV_ID,
                item.SuppAddrInfo.UNIT_TYPE,
                item.SuppAddrInfo.UNIT_ID,
                fal.DESC_ADDR.trim(),
    			false).getAddress());
    	} else {
    		addrList.add(new AddressHandlerPremis(
    			apr.CntlData.Addr.UnnbrdAddrIdent.PSTL_RTE_CD.trim(),
    			apr.CntlData.Addr.UnnbrdAddrIdent.BOX_CD.trim(),
    			apr.CntlData.Addr.BascAddrInfo.StNbrId.SAD_HOUS_PRFX.trim(),
    			apr.CntlData.Addr.BascAddrInfo.StNbrId.SAD_HOUS_NBR.trim(),
    			item.ASGND_HOUS_NBR_ID.trim(),
    			apr.CntlData.Addr.BascAddrInfo.StNbrId.SAD_HOUS_NBR_SUFX.trim(),
    			apr.CntlData.Addr.BascAddrInfo.StNm.SAD_ST_DRCTL.trim(),
    			apr.CntlData.Addr.BascAddrInfo.StNm.SAD_ST_NM.trim(),
    			apr.CntlData.Addr.BascAddrInfo.StNm.SAD_THRFR.trim(),
    			apr.CntlData.Addr.BascAddrInfo.StNm.SAD_ST_SUFX.trim(),
    			item.CMTY_NM.trim(),
    			item.STATE_CD.trim(),
    			item.ZC.trim(),
    			PREMISTag.EMPTY_STRING,	//county
    			PREMISTag.EMPTY_STRING, //country
                item.SuppAddrInfo.STRUCT_TYPE,
                item.SuppAddrInfo.STRUCT_ID,
                item.SuppAddrInfo.ELEV_TYPE,
                item.SuppAddrInfo.ELEV_ID,
                item.SuppAddrInfo.UNIT_TYPE,
                item.SuppAddrInfo.UNIT_ID,
                fal.DESC_ADDR.trim(),
    			false).getAddress());
    	}
    	
    }
    /**
     * Package-access getter for the internal List.
     * Creation date: (3/16/01 10:06:07 AM)
     * @return List
     */
    List getAddrList()
    {
    	return addrList;
    }
}
