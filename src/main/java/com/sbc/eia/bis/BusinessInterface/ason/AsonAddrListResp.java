// $Id: AsonAddrListResp.java,v 1.9 2004/07/28 23:13:48 rz7367 Exp $

package com.sbc.eia.bis.BusinessInterface.ason;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.bis.lim.transactions.RetrieveLocation.AddrAltResp;
import com.sbc.eia.bis.lim.util.LIMBase;
import com.sbc.eia.idl.lim.helpers.AddressHandlerASON;
import com.sbc.eia.idl.lim.helpers.AddressHandlerException;
import com.sbc.eia.idl.lim_types.Address;
import com.sbc.gwsvcs.service.asonservice.ASONServiceTest;
import com.sbc.gwsvcs.service.asonservice.interfaces.ASONLvngUnitInqReq;
import com.sbc.gwsvcs.service.asonservice.interfaces.ASONLvngUnitInqResp;
import com.sbc.gwsvcs.service.asonservice.interfaces.ASONLvngUnitValResp;
import com.sbc.gwsvcs.service.asonservice.interfaces.ASONSagValidResp;
import com.sbc.gwsvcs.service.asonservice.interfaces.infoline_st;
import com.sbc.gwsvcs.service.asonservice.interfaces.lufloctagsarea_st;
import com.sbc.gwsvcs.service.asonservice.interfaces.lufrecordkeys_st;

/**
 * Build a list of addresses from ASON menus.
 * Creation date: (3/13/01 2:00:56 PM)
 * @author: David Brawley
 */
public class AsonAddrListResp extends AddrAltResp
{
	protected AddressHandlerASON address = null;
	protected ASONLvngUnitValResp luv = null;
	protected ASONSagValidResp svr = null;
    protected Unit unit = new Unit();
    protected int unitSize = 0;
   
    /**
     * This default (package) access constructor allows other classes (see AsonComboResp)
     * to use this class in an atypical way.
     * Creation date: (3/16/01 10:05:16 AM)
     * @param utility LIMBase
     */
    AsonAddrListResp(LIMBase utility)
    {
    	super(utility);
    	address = new AddressHandlerASON();
    }
    /**
     * Construct lists of addresses from a ASONService
     * ASON Living Unit Validation Response (S13A).
     * ASON Living Unit Inquiry Response (S13B).
     * Creation date: (3/16/01 10:05:16 AM)
     * @param utility LIMBase
     * @param aLuv ASONLvngUnitValResp
     */
    AsonAddrListResp(LIMBase utility,ASONLvngUnitValResp aLuv)
    {
    	super(utility);
    	luv = aLuv;
    	address = new AddressHandlerASON();
    }
    /**
     * Add an address to the list for an AddrListResp.
     * Creation date: (3/16/01 10:14:40 AM)
     * @param addr  an Address object
     */
    protected void addToList(Address addr)
    {
  		addrList.add(addr);
    }
    /**
     * Add an address to the list for an AddrListResp.
     * Creation date: (3/16/01 10:14:40 AM)
     * @param addr  AddressHandlerASON
     */
    protected void addToList(AddressHandlerASON addr)
    {
        // add address to alternative address list (AddrListResp)
   	    addrList.add(new AddressHandlerASON(
   		    addr.getRoute(),			// aRoute
   		    addr.getBox(),				// aBox,
   		    addr.getHousNbrPfx(),		// aHouseNumberPrefix
   		    addr.getHousNbr(),			// aHouseNumber
   		    addr.getAasgndHousNbr(),   	// aAssignedHouseNumber
   		    addr.getHousNbrSfx(),     	// aHouseNumberSuffix
   		    addr.getStDir(), 			// aStreetDirection
   		    addr.getStName(),			// aStreetName
   		    addr.getStThorofare(),		// aStreetThoroughfare
   		    addr.getStNameSfx(),		// aStreetNameSuffix
   		    addr.getCity(),				// aCity
   		    addr.getState(),			// aState
   		    addr.getPostalCode(),		// aPostalCode
   		    addr.getCounty(),			// aCounty
   		    addr.getCountry(),			// aCountry
   		    addr.getStructType(),		// Structure tag
            addr.getStructValue(),      // Structure value
            addr.getLevelType(),        // Level tag
            addr.getLevelValue(),       // Level value
            addr.getUnitType(),         // Unit tag
            addr.getUnitValue(),        // Unit value
            addr.getAddAddrInfo(),      // aAdditionalInformation
            false).getAddress());		// stNmInd boolean
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

    /**
     * Builds a ASONLivingUnitInqReq object for a location request.
     * Creation date: (3/20/01 5:00:10 PM)
     * @return ASONLvngUnitInqReq
     * @param locReq  an AsonRetrieveLocReq object
     */
    public ASONLvngUnitInqReq getAsonLuiReq(AsonRetrieveLocReq locReq)
    {
    	String houseNbr = "";
    	String lowRange = "";
    	String highRange = "";
    	StringBuffer rateBandZone = new StringBuffer((String.valueOf(svr.rateBand).equals("")) ? " " : String.valueOf(svr.rateBand));
    	rateBandZone.append(((String.valueOf(svr.rateZone).equals("")) ? " " : String.valueOf(svr.rateZone)));
    
    	if	((luv.assignedHseNumberInd != null) &&
    		((luv.assignedHseNumberInd.trim()).equalsIgnoreCase("Y")))
    		houseNbr 	= locReq.asonAddr.getAasgndHousNbr();
    	else
    		if ((locReq.asonAddr.getHousNbr()).length() > 0)
    			houseNbr = locReq.asonAddr.getHousNbr();
    		else
    			houseNbr = svr.lowAddrRange;
    
    	if (svr.lowAddrRange.trim().equalsIgnoreCase(""))
    		lowRange = ASONTag.DEFAULT_LOW_RANGE;
    	else
    		lowRange = svr.lowAddrRange;
    		
    	if (svr.highAddrRange.trim().equalsIgnoreCase(""))
    		highRange = ASONTag.DEFAULT_HIGH_RANGE; 
    	else
    		highRange = svr.highAddrRange;
    		
    	
    	return( new ASONLvngUnitInqReq(
    		AsonHelper.getTagInfo(),						//tagInformation
    		ASONTag.REQUEST_CODE_5210,						//requestCode
    		AsonHelper.getCmndLine(),						//commandLine
    		ASONTag.DATE_KEY,								//dateKey
    		ASONTag.FUNCTION_KEY_DEPRESSED,					//functionKeyDepressed
    		ASONTag.ID_GROUP,								//idGroup
    		ASONTag.ID_TERMINAL,							//idTerminal
    		ASONTag.ID_TYPIST,								//idTypist
    		svr.raiCode,									//regionalAreaId
    		ASONTag.TIME_KEY,								//timeKey
    		ASONTag.EMPTY_STRING,							//savedLuKey
    		svr.raiCode,									//raiCode
    		String.valueOf(svr.sagAreaId),					//sagAreaId
    		svr.sagWireCenter,								//wireCenter
    		svr.communitySag,								//communityName
    		locReq.asonAddr.getStreetDir().toUpperCase(),	//streetDirection
    		svr.addressNameSag.trim().substring(0,1), 		//streetName1
    		svr.addressNameSag.trim().substring(1),			//streetName2_40
    		luv.assignedHseNumberInd,						//assignedHouseNumberInd
    		houseNbr.toUpperCase(),	 						//stNbrFld1
    		locReq.asonAddr.getHousNbrSfx(),		 		//stNbrFld2
    		locReq.getAddr().getStructValue(),				//loc1
    		locReq.getAddr().getLevelValue(),				//loc2
    		locReq.getAddr().getUnitValue(),				//loc3
    		ASONTag.EMPTY_STRING,							//loc4
    		ASONTag.EMPTY_STRING,							//loc5
    		ASONTag.EMPTY_STRING,							//custTN
    		ASONTag.EMPTY_STRING,							//custName
    		ASONTag.EMPTY_STRING,							//custAddress
    		lowRange,										//streetRangeLow
    		highRange,										//streetRangeHigh
    		String.valueOf(svr.oddEvenIndicator),			//oddEvenInd
    		svr.exchange,									//exchange
    		svr.centralOffice,								//centralOffice
    		svr.map,										//map
    		rateBandZone.toString(),						//rateBandZone
    		svr.zipCodeSag,									//zipCode
    		svr.npa,										//npa
    		svr.raiCode,									//skRaiCode
    		String.valueOf(svr.sagAreaId),					//skSaiCode
    		String.valueOf(svr.alphaNumInd),				//skAlphanumInd
    		svr.addressNameSag.trim(),						//skAddressName
    		svr.directional,								//skDirectional
    		svr.highAddrRange,								//skHighRange
    		svr.lowAddrRange,								//skLowRange
    		String.valueOf(svr.oddEvenIndicator),			//skOddEvenIndicator
    		svr.exchange,									//skExchange
    		svr.centralOffice,								//skSagCo
    		svr.map,										//skMap
    		rateBandZone.toString(),						//skRateBandZone
    		svr.zipCodeSag,									//skZipCode
    		svr.npa,										//skNpa
    		svr.countyAbbrev,								//skCountyAbbrev
    		svr.municipality,								//skMunicipality
    		ASONTag.SENT_END_STRING,						//sentEndString
    		(new Integer(getMaxUnits()).toString()))); 		//maximumAddresses
    
    }

    /**
     * Create compare Living Unit Record Key for comparison.
     * Creation date: (8/15/01 8:16:25 AM)
     * @return String
     * @param luKey  a lufrecordkeys_st
     */
    protected String getLuCompareKey(lufrecordkeys_st luKey) {
    	
    	return (luKey.raiCode +
    			luKey.sagAreaId +
    			luKey.wireCenter +
    			luKey.communityName +
    			luKey.streetDirection +
    			luKey.streetName +
    			luKey.assignedHouseNumberInd +
    			luKey.stNbrFld1 +
    			luKey.stNbrFld2);
    }

    /**
     * handleAsonLuiVector will process the screen vector, for each screen
     * the lines will be parsed and added to the alternate address list.
     * Creation date: (3/27/01 12:01:14 PM)
     * @param luiScrns  a Vector object
     */
    protected void parseAsonLuiVector(Vector luiScrns)
    {
    	String prevLuCompareKey = "";
    	String prevConcUnitValue = "";
        String currConcUnitValue = "";
        int startRow = 0;
    	
        
        
    	for (int i = 0; i < luiScrns.size(); i++)
    	{
    		getUtility().log(LogEventId.DEBUG_LEVEL_2, ASONServiceTest.display(((ASONLvngUnitInqResp) luiScrns.get(i))));
    		// get a single screen from the screen vector
    		infoline_st     infLines[] = ((ASONLvngUnitInqResp) luiScrns.get(i)).infoLines;
    		lufrecordkeys_st  luKeys[] = ((ASONLvngUnitInqResp) luiScrns.get(i)).lufRecordKeys;
    		lufloctagsarea_st luTags[] = ((ASONLvngUnitInqResp) luiScrns.get(i)).lufLocTagsArea;	

            try{        
        		// setup previous compare key for first screen
        		if (i == 0){
        			prevLuCompareKey = getLuCompareKey(luKeys[0]);
     
        			// if no data return
        			if (infLines[0].infoLine.indexOf(ASONTag.END_OF_LIVING_UNIT) >= 0)
        				return;
    
                    // write first record to list
                    unitSize = 0;
                    setUnit(luKeys[0].locValue1, luKeys[0].locValue2, luKeys[0].locValue3, luTags[0]);
                    address.setASONFields((AsonHelper.formatStNbr(infLines[0].infoLine.substring(0,15))), 
                        luKeys[0].stNbrFld2, luKeys[0].streetDirection, luKeys[0].streetName, 
                        luKeys[0].communityName, svr.raiCode, svr.zipCodeSag, svr.countyAbbrev, 
                        luKeys[0].assignedHouseNumberInd, unit.getStructTag(), unit.getStructVal(),
                        unit.getLevelTag(), unit.getLevelVal(), unit.getUnitTag(), unit.getUnitVal()); 
                    addToList(address);
                    prevConcUnitValue = luKeys[0].locValue1 + luKeys[0].locValue2 + luKeys[0].locValue3;
        		}	


    			// loop thru luKeys lines
                // bypass duplicate first row
    			for (int j=1; j < luKeys.length; j++){
                //	getUtility().log(LogEventId.DEBUG_LEVEL_2,"infoLine[" + infLines[j].infoLine + "]");  

                    // check if maximum number of addresses
                    // to return has been reached
                    if ((getMaxAddresses() != NO_SIZE_LIMIT) && 
                        (getMaxAddresses() == addrList.size()))
                        return;
    	
    				// check for empty line
    				if (((getLuCompareKey(luKeys[j])).trim()).equalsIgnoreCase(""))
    					continue;
    
    				if	(getLuCompareKey(luKeys[j]).equalsIgnoreCase(prevLuCompareKey))
                    {
    					currConcUnitValue = luKeys[j].locValue1 + luKeys[j].locValue2 + luKeys[j].locValue3;
                        
                        if ((!currConcUnitValue.equalsIgnoreCase(prevConcUnitValue)) 
                                          &&
                            ((getMaxUnits() == NO_SIZE_LIMIT) || 
    						 (getMaxUnits() > unitSize)))
                        {
    						setUnit(luKeys[j].locValue1, luKeys[j].locValue2, luKeys[j].locValue3, luTags[j]);
                            address.setASONFields((AsonHelper.formatStNbr(infLines[j].infoLine.substring(0,15))), 
                                luKeys[j].stNbrFld2, luKeys[j].streetDirection, luKeys[j].streetName, 
                                luKeys[j].communityName, svr.raiCode, svr.zipCodeSag, svr.countyAbbrev, 
                                luKeys[j].assignedHouseNumberInd, unit.getStructTag(), unit.getStructVal(), 
                                unit.getLevelTag(), unit.getLevelVal(), unit.getUnitTag(), unit.getUnitVal());
                            addToList(address);
                            prevConcUnitValue = luKeys[j].locValue1 + luKeys[j].locValue2 + luKeys[j].locValue3;
                        }
    				}
    				else
                    {
                        unitSize = 0;
                        setUnit(luKeys[j].locValue1, luKeys[j].locValue2, luKeys[j].locValue3, luTags[j]);
                        address.setASONFields((AsonHelper.formatStNbr(infLines[j].infoLine.substring(0,15))), 
                            luKeys[j].stNbrFld2, luKeys[j].streetDirection, luKeys[j].streetName, 
                            luKeys[j].communityName, svr.raiCode, svr.zipCodeSag, svr.countyAbbrev, 
                            luKeys[j].assignedHouseNumberInd, unit.getStructTag(), unit.getStructVal(), 
                            unit.getLevelTag(), unit.getLevelVal(), unit.getUnitTag(), unit.getUnitVal());
                        addToList(address);
    					prevLuCompareKey = getLuCompareKey(luKeys[j]);
                        prevConcUnitValue = luKeys[j].locValue1 + luKeys[j].locValue2 + luKeys[j].locValue3;
    				}
    			} // end for j
    		}
    		catch (AddressHandlerException ahe){}
    	}
    }

    /**
     * Set unit data with ASON location data.
     * Creation date: (8/15/01 8:33:44 AM)
     * @param structValue String
     * @param levelValue String
     * @param unitValue String
     * @param tag lufloctagsarea_st
     */
    private void setUnit(String structValue, String levelValue, String unitValue, lufloctagsarea_st tag )
    {
        unit.setStructTag(tag.locTag1.trim());
        unit.setStructVal(structValue.trim());
        unit.setLevelTag(tag.locTag2.trim());
        unit.setLevelVal(levelValue.trim());
        unit.setUnitTag(tag.locTag3.trim());
        unit.setUnitVal(unitValue.trim());

       if ((unit.getStructTag()!= null && unit.getStructTag().length() > 0) ||
           (unit.getStructVal()!= null && unit.getStructVal().length() > 0) ||
           (unit.getLevelTag() != null && unit.getLevelTag().length()  > 0) ||
           (unit.getLevelVal() != null && unit.getLevelVal().length()  > 0) ||
           (unit.getUnitTag()  != null && unit.getUnitTag().length()   > 0) ||
           (unit.getUnitVal()  != null && unit.getUnitVal().length()   > 0))
           unitSize++;
    }

    /**
     * Setter for ASONSagValidResp of AsonAddrListResp class.
     * Creation date: (7/24/01 6:55:17 AM)
     * @param newSvr ASONSagValidResp
     */
    public void setSvr(ASONSagValidResp newSvr) {
    	svr = newSvr;
    }
}
