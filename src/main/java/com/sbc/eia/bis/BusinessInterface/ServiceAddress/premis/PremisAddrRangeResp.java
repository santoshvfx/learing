// $Id: PremisAddrRangeResp.java,v 1.3 2007/10/06 01:04:24 gg2712 Exp $

package com.sbc.eia.bis.BusinessInterface.ServiceAddress.premis;

import java.util.List;

import com.sbc.eia.bis.lim.transactions.common.ServiceAddrRangeResp;
import com.sbc.eia.bis.lim.util.LIMBase;
import com.sbc.eia.idl.bis_types.SystemFailure;
import com.sbc.gwsvcs.service.premisserver.interfaces.AddrMenuItem_t;
import com.sbc.gwsvcs.service.premisserver.interfaces.AddrMenuProcStatus_t;
import com.sbc.gwsvcs.service.premisserver.interfaces.AddrRngeMenuItem_t;
import com.sbc.gwsvcs.service.premisserver.interfaces.AddrRngeMenuProcStatus_t;
import com.sbc.gwsvcs.service.premisserver.interfaces.AppPrmRespItem_t;
import com.sbc.gwsvcs.service.premisserver.interfaces.FacActnLnItem_t;
import com.sbc.gwsvcs.service.premisserver.interfaces.GeoSegMenuItem_t;
import com.sbc.gwsvcs.service.premisserver.interfaces.GeoSegMenuProcStatus_t;
import com.sbc.gwsvcs.service.premisserver.interfaces.PremisAddrMenuResp_t;
import com.sbc.gwsvcs.service.premisserver.interfaces.PremisAddrRngeMenuResp_t;
import com.sbc.gwsvcs.service.premisserver.interfaces.PremisGeoSegMenuResp_t;
import com.sbc.gwsvcs.service.premisserver.interfaces.PremisStAddrRngeMenuResp_t;
import com.sbc.gwsvcs.service.premisserver.interfaces.PremisStNmMenuResp_t;
import com.sbc.gwsvcs.service.premisserver.interfaces.PremisUnnbrdMenuResp_t;
import com.sbc.gwsvcs.service.premisserver.interfaces.StAddrRngeMenuItem_t;
import com.sbc.gwsvcs.service.premisserver.interfaces.StAddrRngeMenuProcStatus_t;
import com.sbc.gwsvcs.service.premisserver.interfaces.StNmMenuItem_t;
import com.sbc.gwsvcs.service.premisserver.interfaces.StNmMenuProcStatus_t;
import com.sbc.gwsvcs.service.premisserver.interfaces.UnnbrdMenuItem_t;
import com.sbc.gwsvcs.service.premisserver.interfaces.UnnbrdMenuProcStatus_t;

/**
 * Build a list of address ranges from Premis menus.
 * Creation date: (3/12/01 11:06:17 AM)
 * @author: David Prager
 */
public class PremisAddrRangeResp extends ServiceAddrRangeResp
{
	protected AppPrmRespItem_t apr = null;
	protected FacActnLnItem_t fal = null;
	
    /**
     * This default (package) access constructor allows other classes (see PremisComboResp)
     * to use this class in an atypical way.
     * Creation date: (3/16/01 10:02:41 AM)
     * @param utility LIMBase
     * @param appPrmRespItem AppPrmRespItem_t
     */
    PremisAddrRangeResp(LIMBase utility,AppPrmRespItem_t appPrmRespItem)
    {
    	super(utility);
    	apr = appPrmRespItem;
    	fal = apr.FacActnLn;
    }
    /**
     * Construct an address range list from a PREMISServer Address Menu.
     * Creation date: (3/12/01 11:51:28 AM)
     * @param utility LIMBase
     * @param result PremisAddrMenuResp_t
     * @param maxAddr int
     * @exception SystemFailure
     */
    public PremisAddrRangeResp(LIMBase utility, PremisAddrMenuResp_t result, int maxAddr)
    	throws SystemFailure
    {
    	super(utility);
    	apr = result.AppPrmRespItem;
    	fal = apr.FacActnLn;
    	setMaxAddresses(maxAddr);
    
    	for (int i = 0; i < result.AddrMenuPktResp.AddrMenuProcStatus.length; i++)
    	{
    		AddrMenuProcStatus_t menu = result.AddrMenuPktResp.AddrMenuProcStatus[i];
    
    		for (int j = 0; j < menu.AddrMenu.length; j++)
    		{
    			AddrMenuItem_t item = menu.AddrMenu[j];
    
    			rangeList.add(new RangeData(
    				apr.CntlData.Addr.BascAddrInfo.StNm.SAD_ST_DRCTL.trim(),
    				apr.CntlData.Addr.BascAddrInfo.StNm.SAD_ST_NM.trim(),
    				apr.CntlData.Addr.BascAddrInfo.StNm.SAD_THRFR.trim(),
    				apr.CntlData.Addr.BascAddrInfo.StNm.SAD_ST_SUFX.trim(),
    				PREMISTag.EMPTY_STRING,		// lowHouseNbrPfx
    				item.AddrRnge.LOW_HOUS_NBR_VALU_ID.trim(),
    				PREMISTag.EMPTY_STRING,		// lowHouseNbrSfx
    				PREMISTag.EMPTY_STRING,		// highHouseNbrPfx
    				item.AddrRnge.HI_HOUS_NBR_VALU_ID.trim(),
    				PREMISTag.EMPTY_STRING,		// highHouseNbrSfx
    				formatCity(item.CMTY_NM.trim()),
    				item.STATE_CD.trim(),
    				item.ZC.trim(),
                    PREMISTag.EMPTY_STRING));   // zip4
    		}
    	}
    }
    /**
     * Construct an address range list from a PREMISServer Address Range Menu.
     * Creation date: (3/12/01 11:36:42 AM)
     * @param utility LIMBase
     * @param result PremisAddrRngeMenuResp_t
     * @param maxAddr int
     * @exception SystemFailure
     */
    public PremisAddrRangeResp(LIMBase utility, PremisAddrRngeMenuResp_t result, int maxAddr)
    	throws SystemFailure
    {
    	super(utility);
    	apr = result.AppPrmRespItem;
    	fal = apr.FacActnLn;
    	setMaxAddresses(maxAddr);
    
    	for (int i = 0; i < result.AddrRngeMenuPktResp.AddrRngeMenuProcStatus.length; i++)
    	{
    		AddrRngeMenuProcStatus_t menu = result.AddrRngeMenuPktResp.AddrRngeMenuProcStatus[i];
    
    		for (int j = 0; j < menu.AddrRngeMenu.length; j++)
    		{
    			AddrRngeMenuItem_t item = menu.AddrRngeMenu[j];
    			
    			rangeList.add(new RangeData(
    				apr.CntlData.Addr.BascAddrInfo.StNm.SAD_ST_DRCTL.trim(),
    				apr.CntlData.Addr.BascAddrInfo.StNm.SAD_ST_NM.trim(),
    				apr.CntlData.Addr.BascAddrInfo.StNm.SAD_THRFR.trim(),
    				apr.CntlData.Addr.BascAddrInfo.StNm.SAD_ST_SUFX.trim(),
    				PREMISTag.EMPTY_STRING,		// lowHouseNbrPfx
    				item.AddrRnge.LOW_HOUS_NBR_VALU_ID.trim(),
    				PREMISTag.EMPTY_STRING,		// lowHouseNbrSfx
    				PREMISTag.EMPTY_STRING,		// highHouseNbrPfx
    				item.AddrRnge.HI_HOUS_NBR_VALU_ID.trim(),
    				PREMISTag.EMPTY_STRING,		// highHouseNbrSfx
    				formatCity(item.CMTY_NM.trim()),
    				item.STATE_CD.trim(),
    				item.ZC.trim(),
                    PREMISTag.EMPTY_STRING));   // zip4
    		}
    	}
    }
    /**
     * Construct an address range list from a PREMISServer Geographic Segment Menu.
     * Creation date: (3/12/01 12:47:18 PM)
     * @param utility LIMBase
     * @param result PremisGeoSegMenuResp_t
     * @param maxAddr int
     * @exception SystemFailure
     */
    public PremisAddrRangeResp(LIMBase utility, PremisGeoSegMenuResp_t result, int maxAddr)
    	throws SystemFailure
    {
    	super(utility);
    	apr = result.AppPrmRespItem;
    	fal = apr.FacActnLn;
    	setMaxAddresses(maxAddr);
    
    	for (int i = 0; i < result.GeoSegMenuPktResp.GeoSegMenuProcStatus.length; i++)
    	{
    		GeoSegMenuProcStatus_t menu = result.GeoSegMenuPktResp.GeoSegMenuProcStatus[i];
    
    		for (int j = 0; j < menu.Gsgm.GeoSegMenu.length; j++)
    		{
    			addToList(menu.Gsgm.GeoSegMenu[j]);
    		}
    	}
    }
    /**
     * Construct an address range list from a PREMISServer Street Address Range Menu.
     * Creation date: (3/12/01 12:02:38 PM)
     * @param utility LIMBase
     * @param result PremisStAddrRngeMenuResp_t
     * @param maxAddr int
     * @exception SystemFailure
     */
    public PremisAddrRangeResp(LIMBase utility, PremisStAddrRngeMenuResp_t result, int maxAddr)
    	throws SystemFailure
    {
    	super(utility);
    	apr = result.AppPrmRespItem;
    	fal = apr.FacActnLn;
    	setMaxAddresses(maxAddr);
    
    	for (int i = 0; i < result.StAddrRngeMenuPktResp.StAddrRngeMenuProcStatus.length; i++)
    	{
    		StAddrRngeMenuProcStatus_t menu = result.StAddrRngeMenuPktResp.StAddrRngeMenuProcStatus[i];
    
    		for (int j = 0; j < menu.StAddrRngeMenu.length; j++)
    		{
    			StAddrRngeMenuItem_t item = menu.StAddrRngeMenu[j];
    			
    			rangeList.add(new RangeData(
    				item.StNm.SAD_ST_DRCTL.trim(),
    				item.StNm.SAD_ST_NM.trim(),
    				item.StNm.SAD_THRFR.trim(),
    				item.StNm.SAD_ST_SUFX.trim(),
    				PREMISTag.EMPTY_STRING,		// lowHouseNbrPfx
    				item.AddrRnge.LOW_HOUS_NBR_VALU_ID.trim(),
    				PREMISTag.EMPTY_STRING,		// lowHouseNbrSfx
    				PREMISTag.EMPTY_STRING,		// highHouseNbrPfx
    				item.AddrRnge.HI_HOUS_NBR_VALU_ID.trim(),
    				PREMISTag.EMPTY_STRING,		// highHouseNbrSfx
    				formatCity(item.CMTY_NM.trim()),
    				item.STATE_CD.trim(),
    				item.ZC.trim(),
                    PREMISTag.EMPTY_STRING));   // zip4
    		}
    	}
    }
    /**
     * Construct an address range list from a PREMISServer Street Name Menu.
     * @param utility LIMBase
     * @param result   a PremisStNmMenuResp_t object
     * @param maxAddr int
     * @exception SystemFailure
     */
    public PremisAddrRangeResp(LIMBase utility, PremisStNmMenuResp_t result, int maxAddr)
    	throws SystemFailure
    {
    	super(utility);
    	apr = result.AppPrmRespItem;
    	fal = apr.FacActnLn;
    	setMaxAddresses(maxAddr);
    
    	for (int i = 0; i < result.StNmMenuPktResp.StNmMenuProcStatus.length; i++)
    	{
    		StNmMenuProcStatus_t menu = result.StNmMenuPktResp.StNmMenuProcStatus[i];
    		
    		for (int j = 0; j < menu.StNmMenu.length; j++)
    		{
    			StNmMenuItem_t item = menu.StNmMenu[j];
    
    			rangeList.add(new RangeData(
    				item.StNm.SAD_ST_DRCTL.trim(),
    				item.StNm.SAD_ST_NM.trim(),
    				item.StNm.SAD_THRFR.trim(),
    				item.StNm.SAD_ST_SUFX.trim(),
    				PREMISTag.EMPTY_STRING,		// lowHouseNbrPfx
    				item.AddrRnge.LOW_HOUS_NBR_VALU_ID.trim(),
    				PREMISTag.EMPTY_STRING,		// lowHouseNbrSfx
    				PREMISTag.EMPTY_STRING,		// highHouseNbrPfx
    				item.AddrRnge.HI_HOUS_NBR_VALU_ID.trim(),
    				PREMISTag.EMPTY_STRING,		// highHouseNbrSfx
    				formatCity(item.CMTY_NM.trim()),
    				item.STATE_CD.trim(),
    				item.ZC.trim(),
                    PREMISTag.EMPTY_STRING));   // zip4
    		}
    	}
    }
    /**
     * Construct an address range list from a PREMISServer Unnumbered Menu.
     * Creation date: (3/12/01 12:14:16 PM)
     * @param utility LIMBase
     * @param result PremisUnnbrdMenuResp_t
     * @param maxAddr int
     * @exception SystemFailure
     */
    public PremisAddrRangeResp(LIMBase utility, PremisUnnbrdMenuResp_t result, int maxAddr)
    	throws SystemFailure
    {
    	super(utility);
    	apr = result.AppPrmRespItem;
    	fal = apr.FacActnLn;
    	setMaxAddresses(maxAddr);
    
    	for (int i = 0; i < result.UnnbrdMenuPktResp.UnnbrdMenuProcStatus.length; i++)
    	{
    		UnnbrdMenuProcStatus_t menu = result.UnnbrdMenuPktResp.UnnbrdMenuProcStatus[i];
    
    		for (int j = 0; j < menu.UnnbrdMenu.length; j++)
    		{
    			UnnbrdMenuItem_t item = menu.UnnbrdMenu[j];
    
    			for (int k = 0; k < item.AsgndHousNbrRnge.length; k++)
    			{
    				rangeList.add(new RangeData(
    					item.StNm.SAD_ST_DRCTL.trim(),
    					item.StNm.SAD_ST_NM.trim(),
    					item.StNm.SAD_THRFR.trim(),
    					item.StNm.SAD_ST_SUFX.trim(),
    					PREMISTag.EMPTY_STRING,		// lowHouseNbrPfx
    					item.AsgndHousNbrRnge[k].LOW_ASGND_HOUS_NBR_VALU_ID.trim(),
    					PREMISTag.EMPTY_STRING,		// lowHouseNbrSfx
    					PREMISTag.EMPTY_STRING,		// highHouseNbrPfx
    					item.AsgndHousNbrRnge[k].HI_ASGND_HOUS_NBR_VALU_ID.trim(),
    					PREMISTag.EMPTY_STRING,		// highHouseNbrSfx
    					formatCity(item.CMTY_NM.trim()),
    					item.STATE_CD.trim(),
    					item.AsgndHousNbrRnge[k].ZC.trim(),
                        PREMISTag.EMPTY_STRING));   // zip4
    			}
    		}
    	}
    }
    /**
     * Add to the address range list from another PremisAddrRangeResp.
     * Creation date: (3/16/01 10:19:31 AM)
     * @param rangeData List
     */
    protected void addToList(List rangeData)
    {
    	for (int i=0; i < rangeData.size(); i++)	
    		rangeList.add((RangeData) rangeData.get(i));	
    }
    /**
     * Add to the address range list from a Geographic Segment Menu Item.
     * Creation date: (3/16/01 10:19:31 AM)
     * @param item GeoSegMenuItem_t
     */
    protected void addToList(GeoSegMenuItem_t item)
    {
    	rangeList.add(new RangeData(
    		apr.CntlData.Addr.BascAddrInfo.StNm.SAD_ST_DRCTL.trim(),
    		apr.CntlData.Addr.BascAddrInfo.StNm.SAD_ST_NM.trim(),
    		apr.CntlData.Addr.BascAddrInfo.StNm.SAD_THRFR.trim(),
    		apr.CntlData.Addr.BascAddrInfo.StNm.SAD_ST_SUFX.trim(),
    		PREMISTag.EMPTY_STRING,		// lowHouseNbrPfx
    		item.LOW_ASGND_HOUS_NBR_VALU_ID.trim(),
    		PREMISTag.EMPTY_STRING,		// lowHouseNbrSfx
    		PREMISTag.EMPTY_STRING,		// highHouseNbrPfx
    		item.HI_ASGND_HOUS_NBR_VALU_ID.trim(),
    		PREMISTag.EMPTY_STRING,		// highHouseNbrSfx
    		formatCity(item.CMTY_NM.trim()),
    		item.STATE_CD.trim(),
    		item.SagInfo.ZC.trim(),
            PREMISTag.EMPTY_STRING));   // zip4
    }
    /**
     * Remove $$$ and *** if populated in city returned from PREMIS.
     * Creation date: (3/16/01 10:19:31 AM)
     * @param city  a String
     * @return a String
     */
    protected String formatCity(String city)
    {
    	if (city.startsWith (PREMISTag.THREE_DOLLAR_SIGNS) ||
    		city.startsWith (PREMISTag.THREE_ASTERICKS))
    		return(city.substring (3));
    	
    	return city;	
    }
    /**
     * Non-public getter for the internal List.
     * Creation date: (3/16/01 10:04:02 AM)
     * @return List
     */
    List getRangeList()
    {
    	return rangeList;
    }
}
