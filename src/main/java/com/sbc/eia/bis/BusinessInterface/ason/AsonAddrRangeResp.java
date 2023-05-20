// $Id: AsonAddrRangeResp.java,v 1.6 2007/03/12 20:09:56 rz7367 Exp $

package com.sbc.eia.bis.BusinessInterface.ason;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.bis.lim.transactions.RetrieveLocation.AddrRangeResp;
import com.sbc.eia.bis.lim.util.LIMBase;
import com.sbc.eia.idl.lim.helpers.AddressHandlerASON;
import com.sbc.eia.idl.lim.helpers.AddressHandlerException;
import com.sbc.gwsvcs.service.asonservice.ASONServiceTest;
import com.sbc.gwsvcs.service.asonservice.interfaces.ASONSagInqReq;
import com.sbc.gwsvcs.service.asonservice.interfaces.ASONSagInqResp;
import com.sbc.gwsvcs.service.asonservice.interfaces.ASONSagValidErr;
import com.sbc.gwsvcs.service.asonservice.interfaces.sagbypassarea_st;
import com.sbc.gwsvcs.service.asonservice.interfaces.sagkey_st;
import com.sbc.gwsvcs.service.asonservice.interfaces.sagline_st;

/**
 * Build a range of addresses from ASON menus.
 * Creation date: (3/16/01 9:49:27 AM)
 * @author: David Brawley
 */
public class AsonAddrRangeResp extends AddrRangeResp 
{
	protected AsonHelper asonHelper = null;
	protected AsonRetrieveLocReq req = null;
	protected List sagAddrList0 = new ArrayList();
	protected List sagAddrList1 = new ArrayList();
	protected List sagAddrList2 = new ArrayList();
	protected List sagAddrList3	= new ArrayList();
	protected List sagIdList  	= new ArrayList();
	protected List sagRangeList	= new ArrayList();
	// stNmReq contains inpur street name with '*' and ' ' removed
	private String stNmReq = null;


    /**
     * Construct lists of addresses and of address ranges from a ason service combination
     * ASON Address Range Response (S8B).
     * Creation date: (3/16/01 9:53:18 AM)
     * @param utility LIMBase
     */
    public AsonAddrRangeResp(LIMBase utility)
    {
    	super(utility);
    	asonHelper   = new AsonHelper();
    	asonHelper.address = new AddressHandlerASON();
    }
    /**
     * Construct lists of addresses and of address ranges from a ason service combination
     * ASON Address Range Response (S8B).
     * Creation date: (3/16/01 9:53:18 AM)
     * @param utility LIMBase
     * @param sagError  an ASONSagValidErr object
     */
    public AsonAddrRangeResp(LIMBase utility, ASONSagValidErr sagError)
    {
    	super(utility);
    	asonHelper 	 = new AsonHelper();
    	asonHelper.address = new AddressHandlerASON();	
    	setSagIdList(sagError);
    }
    /**
     * Add to the address range list of AddressRangeResp.
     * Creation date: (3/16/01 10:19:31 AM)
     * @param ah  an AsonHelper object
     */
    protected void addToRangeList(AsonHelper ah) {
    
    
        ((AddressHandlerASON) ah.address).setStreetDirection(ah.address.getStDir());
        ((AddressHandlerASON) ah.address).setStreetNmSuffix(ah.address.getStNameSfx());

    	// add to final rangeList
    	rangeList.add(new RangeData(
    		ah.address.getStDir(),  
    		ah.address.getStName(),
    		ah.address.getStThorofare(),
    		ah.address.getStNameSfx(),
    		ah.getLowHouseNbrPfx(),		
    		ah.getLowHouseNbr(),
    		"",							// lowHouseNbrSfx
    		ah.getHighHouseNbrPfx(),		
    		ah.getHighHouseNbr(),
    		"",							// highHouseNbrSfx
    		ah.address.getCity(),
    		ah.address.getState(), 
    		ah.address.getPostalCode(),
            ""));                       // zip4
    }
    /**
     * Add address data from AsonSagInqResp to sagAddrList.
     * Creation date: (7/25/01 9:55:18 AM)
     * @param sagLine sagline_st
     * @param sagKey sagkey_st
     * @param sagByPassArea sagbypassarea_st
     * @param sagAddrList List
     */
    private void addToSagAddrList(sagline_st sagLine, sagkey_st sagKey, sagbypassarea_st sagByPassArea, List sagAddrList) {
    
    	String lowHouseNbrPfx = "";
    	String lowHouseNbr = "";
    	String highHouseNbrPfx = "";
    	String highHouseNbr = "";
    		
    	try{
    		asonHelper.address.parseHouseNbr(sagLine.sagLine.substring(23,39).trim());
    		lowHouseNbrPfx 	= AsonHelper.formatStNbr(asonHelper.address.getHousNbrPfx());
    		lowHouseNbr 	= AsonHelper.formatStNbr(asonHelper.address.getHousNbr());
    		asonHelper.address.parseHouseNbr(sagLine.sagLine.substring(40,52).trim());
    		highHouseNbrPfx = AsonHelper.formatStNbr(asonHelper.address.getHousNbrPfx());
    		highHouseNbr 	= AsonHelper.formatStNbr(asonHelper.address.getHousNbr());
    		
    		asonHelper.address.setASONFields(
    							"", 								// StNbr 
    							"",									// StNbrSfx
    							sagKey.directional.trim(), 			// StDir
    							sagKey.addressName.trim(),			// AddressName
    			 				sagByPassArea.bpCommunity.trim(),	// City
    			 				sagKey.raiCode.trim(),				// State
    			 				sagKey.zipCode.trim(),				// ZipCode
    			 				sagKey.countyAbbrev.trim(),			// County
    			 				"N",								// AssignedHseNbrInd
                                "",                                 // structType
                                "",                                 // structValue
                                "",                                 // levelType
                                "",                                 // levelValue
                                "",                                 // unitType
                                "");                                // unitValue
    	}
    	catch (AddressHandlerException ahe) {}
    
    	// add address to address list
    	sagAddrList.add(new String(
    		asonHelper.address.getStName()      + "   |" +
    		asonHelper.address.getStDir()       + "   |" +
    		asonHelper.address.getStThorofare() + "   |" +
    		asonHelper.address.getStNameSfx()   + "   |" +
    		asonHelper.address.getCity()        + "   |" +
    		asonHelper.address.getState()       + "   |" + 
    		asonHelper.address.getPostalCode()  + "   |" +
    		asonHelper.address.getCounty()      + "   |" +
    		lowHouseNbrPfx                      + "   |" +
    		lowHouseNbr                         + "   |" +
    		highHouseNbrPfx                     + "   |" +
    		highHouseNbr                        + "   |" ));
    
    }
    /**
     * Build rangeList addresses for AddressRangeResp.
     * Creation date: (3/16/01 10:19:31 AM)
     * @throws AddressHandlerException
     */
    protected void getAltAddrResp() throws AddressHandlerException
    {
    	for (int i=0; i < sagRangeList.size(); i++){	
    		// parse single address string into list of fields
    		parseSagRangeList((String) sagRangeList.get(i));
    		addToRangeList(asonHelper);
    	} // end for loop
    }
    /**
     * Getter method for ASON SAG1 Inquiry Request.
     * Creation date: (3/20/01 5:00:10 PM)
     * @return resp ASONSag1InqReq
     * @param req  an AsonRetrieveLocReq object
     */
    public ASONSagInqReq getAsonSagInqReq(AsonRetrieveLocReq req, int maxAddressLimit)
    {
    	return (new ASONSagInqReq(	
    		AsonHelper.getTagInfo(),						//tagInformation
    		ASONTag.REQUEST_CODE_7300,						//requestCode
    		AsonHelper.getCmndLine(),						//commandLine
    		ASONTag.DATE_KEY,								//dateKey
    		ASONTag.FUNCTION_KEY_DEPRESSED,					//functionKeyDepressed
    		ASONTag.ID_GROUP,								//idGroup
    		ASONTag.ID_TERMINAL,							//idTerminal
    		ASONTag.ID_TYPIST,								//idTypist
    		req.asonAddr.getState().toUpperCase(),			//regionalAreaId
    		ASONTag.TIME_KEY,								//timeKey
    		req.getSagId().toUpperCase(),					//sagAreaId
    		ASONTag.EMPTY_STRING,							//sagDirectional
    														//addressName truncate to max 20 character	
    		(req.asonAddr.getStName().length()> ASONTag.SAG_INQ_ST_NM_MAX) ?		
    		    req.asonAddr.getStName().substring(0, ASONTag.SAG_INQ_ST_NM_MAX).toUpperCase() :
    		    req.asonAddr.getStName().toUpperCase(),
    		ASONTag.EMPTY_STRING,							//zipCode
    		ASONTag.EMPTY_STRING,							//savedSagKey
    		ASONTag.EMPTY_STRING,							//savedSagScreenInd
    		ASONTag.EMPTY_STRING,							//exactPositioningInd
    		ASONTag.SENT_END_STRING ,						//sentEndString
    		new Integer(maxAddressLimit).toString()));		//maxAddressLimit
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
    /**
     * Getter for AsonRetrieveLocReq of AsonAddrRangeResp class.
     * Creation date: (9/17/01 7:17:37 AM)
     * @return AsonRetrieveLocReq
     * @see #setReq(AsonRetrieveLocReq)
     */
    public AsonRetrieveLocReq getReq() {
    	return req;
    }
    /**
     * Non-public getter for the internal List.
     * Creation date: (3/16/01 10:04:02 AM)
     * @return List
     * @see #setSagIdList(String)
     */
    List getSagIdList()
    {
    	return sagIdList;
    }
    /**
     * Add to the address range list from a SAG1 Inquiry Resp.
     * Creation date: (3/16/01 10:19:31 AM)
     * @param maxAddr  an int
     */
    protected void getSagRangeList(int maxAddr)
    {
    	getUtility().log(LogEventId.DEBUG_LEVEL_2, "AsonMaximumAddrLimit[" + maxAddr + "]");		
    	int row = 0;
    	int addrAdded = 0;
    	boolean matchedStr = false;
    
    	matchedStr = isStNmMatch(sagAddrList0);
  
    	if (!matchedStr)
    		matchedStr = isStNmMatch(sagAddrList1);
    		
    	if (!matchedStr)
    		matchedStr = isStNmMatch(sagAddrList2);
    		
    	if (!matchedStr)
    		matchedStr = isStNmMatch(sagAddrList3);
    			
    	// extract address from list and add to response
    	while ( (!(sagAddrList0.isEmpty())) ||
    			(!(sagAddrList1.isEmpty())) ||
    			(!(sagAddrList2.isEmpty())) ||
    			(!(sagAddrList3.isEmpty())) ) {
    		
    		if (!(sagAddrList0.isEmpty())){ 
    			if ((row < sagAddrList0.size()) &&
    				(addrAdded < maxAddr)){
    				if (!matchedStr){
    					sagRangeList.add((String) sagAddrList0.get(row));
    					++addrAdded;
    				}
    				else
    				{
    					String strResp = removeBlankAsterisk (((String) sagAddrList0.get(row)).substring(0, ((String) sagAddrList0.get(row)).indexOf("|")).trim());
    					if (stNmReq.equalsIgnoreCase (strResp))
    					{
    						sagRangeList.add((String) sagAddrList0.get(row));
    						++addrAdded;
    					}
    				}
    			}
    			else
    				sagAddrList0.clear();	
    		}
    
    		if (!(sagAddrList1.isEmpty())){ 
    			if ((row < sagAddrList1.size()) &&
    				(addrAdded < maxAddr)){
    				if (!matchedStr){
    					sagRangeList.add((String) sagAddrList1.get(row));
    					++addrAdded;
    				}
    				else
    				{
    					String strResp = removeBlankAsterisk (((String) sagAddrList1.get(row)).substring(0, ((String) sagAddrList1.get(row)).indexOf("|")).trim());
    					if (stNmReq.equalsIgnoreCase (strResp))
    					{
    						sagRangeList.add((String) sagAddrList1.get(row));
    						++addrAdded;
    					}
    				}
    			}
    			else
    				sagAddrList1.clear();	
    		}
    
    		if (!(sagAddrList2.isEmpty())){ 
    			if ((row < sagAddrList2.size()) &&
    				(addrAdded < maxAddr)){
    				if (!matchedStr){
    					sagRangeList.add((String) sagAddrList2.get(row));
    					++addrAdded;
    				}
    				else
    				{
    					String strResp = removeBlankAsterisk (((String) sagAddrList2.get(row)).substring(0, ((String) sagAddrList2.get(row)).indexOf("|")).trim());
    					if (stNmReq.equalsIgnoreCase (strResp))
    					{			
    						sagRangeList.add((String) sagAddrList2.get(row));
    						++addrAdded;
    					}
    				}
    			}
    			else
    				sagAddrList2.clear();	
    			}
    
    		if (!(sagAddrList3.isEmpty())){ 
    			if ((row < sagAddrList3.size()) &&
    				(addrAdded < maxAddr)){
    				if (!matchedStr){
    					sagRangeList.add((String) sagAddrList3.get(row));
    					++addrAdded;
    				}
    				else	
    				{	
    					String strResp = removeBlankAsterisk (((String) sagAddrList3.get(row)).substring(0, ((String) sagAddrList3.get(row)).indexOf("|")).trim());
    					if (stNmReq.equalsIgnoreCase (strResp))
    					{		
    						sagRangeList.add((String) sagAddrList3.get(row));
    						++addrAdded;
    					}
    				}
    			}
    			else
    				sagAddrList3.clear();	
    		}
    
    		if (!(addrAdded < maxAddr)){
    			sagAddrList0.clear();
    			sagAddrList1.clear();
    			sagAddrList2.clear();
    			sagAddrList3.clear();
    			break;
    		}
    
    		row++;
    	} // end while
    }
    /**
     * Examine address array, if match on requested street name then return true.
     * Creation date: (2/28/02 9:20:41 AM)
     * @return boolean
     * @param list List
     */
    private boolean isStNmMatch(List list) {
    	
    	for (int i=0; i < list.size(); i++)
    	{
    		String strResp = removeBlankAsterisk (((String) list.get(i)).substring(0, ((String) list.get(i)).indexOf("|")).trim());
    		if (stNmReq.equalsIgnoreCase (strResp))
    			return true;
    	}
    
    	return false;
    }
    /**
     * handleAsonSag1Resp will process the screen vector, for each screen
     * the lines will be parsed and added to the address range list.
     * Creation date: (3/27/01 12:01:14 PM)
     * @param resp      an ASONSagInqResp object
     * @param addrList  a List object
     * @param screen    int
     */
    protected void parseAsonSagInqResp(ASONSagInqResp resp, List addrList, int screen)
    {
    	int addrNbr = 0;
    	int startAddr = ((screen > 0) ? 1 : 0);	// bypass duplicate first record 

		if (stNmReq == null)
		{
			stNmReq = removeBlankAsterisk (req.asonAddr.getStName());
			getUtility().log(LogEventId.DEBUG_LEVEL_2, "removeBlankAsterisk: Input Street Name=" + stNmReq);
  		}
	
    	int cmpreLngth = (stNmReq.length() > 4) ? 4 : stNmReq.length();    	
    	String compareStr = "";
    	compareStr = stNmReq.substring(0, cmpreLngth);
    	
    	getUtility().log(LogEventId.DEBUG_LEVEL_2, ASONServiceTest.display(resp));
    	// parse sagByPassArea Array
    	for (int i = 0; i < resp.sagByPassArea.length; i++){
    		// if community populated, valid address
    		if ((resp.sagByPassArea[i].bpCommunity != null) &&
    			((resp.sagByPassArea[i].bpCommunity.trim()).length() > 0)){
    			addrNbr++;
    			// bypass duplicate address on subsequent screens
    			if (addrNbr > startAddr){
    				// only add those addresses that equal first 4 bytes of request
					String stNmSag = removeBlankAsterisk (resp.sagKeys[i].addressName.trim());
    				if (stNmSag.length() >= compareStr.length()  &&
    					compareStr.equalsIgnoreCase (stNmSag.substring(0,compareStr.length())))
    				{
    					addToSagAddrList(resp.sagLines[i], resp.sagKeys[i],resp.sagByPassArea[i],addrList);
    				}
    			}
    		}	
    	} // end for i
    }
    
    private String removeBlankAsterisk (String inpStr)
    {
    	if (inpStr == null || inpStr.equals (""))
    		return "";
    	String outStr = "";
    	char [] strChar = new char [inpStr.length()];
		int j = 0;
		for (int k = 0; k < inpStr.length(); k++)
		{
			if (inpStr.charAt(k) != ' ' &&
				inpStr.charAt(k) != '*')
				strChar[j++] = inpStr.charAt(k);
		}
		outStr = new String (strChar);
		outStr = outStr.trim ();
		return outStr;
    }
    
    /**
     * handleAsonSag1Resp will process the screen vector, for each screen
     * the lines will be parsed and added to the address range list.
     * Creation date: (3/27/01 12:01:14 PM)
     * @param sagScrns  a Vector object
     * @param sagList   a List object
     */
    protected void parseAsonSagInqVector(Vector sagScrns, List sagList)
    {
    	for (int i = 0; i < sagScrns.size(); i++)
    	{
    		parseAsonSagInqResp((ASONSagInqResp) sagScrns.get(i), sagList, i);
    	}
    }
    /**
     * Parse sagAddrList row containing Address Data.
     * Creation date: (6/13/01 10:52:00 AM)
     * @param addrStr  a String
     * @throws AddressHandlerException
     */
    public void parseSagRangeList(String addrStr)
    	throws AddressHandlerException
    {
    
    	List addrData = new ArrayList();
    	int i = 0;
    
    	// parse address string delemited by "|"
    	// and add to list of fields for this address
    	for (int j = 0; j < addrStr.length(); j++) {
    		//move start pointer to beginning of next field
    		i = j;
    		//move end pointer to end of field
    		j = addrStr.indexOf('|', i);
    		//extract field and add to arraylist for this addr
    		if ((i + 1) == j)
    			addrData.add("");
    		else
    			addrData.add(addrStr.substring(i,j));
    	}
    	try{
    		asonHelper.address.setASONFields("","","",					// StNbr & StNbrSfx
    							((String) addrData.get(1)).trim(), 	// StDir
    							((String) addrData.get(0)).trim(),	// StName
    							((String) addrData.get(2)).trim(),	// StThorofare
    							((String) addrData.get(3)).trim(),	// StNameSfx
    							((String) addrData.get(4)).trim(),	// City
    			 				((String) addrData.get(5)).trim(),	// State
    			 				((String) addrData.get(6)).trim(),	// ZipCode
    			 				((String) addrData.get(7)).trim(),	// County
    			 				"N");								// AssignedHseNbrInd
    
    		asonHelper.setLowHouseNbrPfx(((String) addrData.get(8)).trim());
    		asonHelper.setLowHouseNbr(((String) addrData.get(9)).trim());
    		asonHelper.setHighHouseNbrPfx(((String) addrData.get(10)).trim());
    		asonHelper.setHighHouseNbr(((String) addrData.get(11)).trim());
    	}
    	catch (AddressHandlerException ahe) {
    		throw ahe;
    	}
    	finally{addrData.clear();}
    }
    /**
     * Setter for AsonRetrieveLocReq of AsonAddrRangeResp class.
     * Creation date: (9/17/01 7:17:37 AM)
     * @param newReq AsonRetrieveLocReq
     * @see #getReq
     */
    public void setReq(AsonRetrieveLocReq newReq) {
    	req = newReq;
    }
    /**
     * Method to instantiate a AsonSagInqResp object array from a sag info string.
     * Creation date: (3/6/01 4:35:51 PM)
     * @param aSagIdList  a List
     * @see #getSagIdList
     */
    public void setSagIdList(List aSagIdList)
    {
    	sagIdList = aSagIdList;
    }
    /**
     * Method to instantiate the sagIdList from the SAI alternates from ASONSagValidErr.
     * Creation date: (3/6/01 4:35:51 PM)
     * @param sagErr  an ASONSagValidErr object
     */
    public void setSagIdList(ASONSagValidErr sagErr)
    {
    	if ((String.valueOf(sagErr.comReplyHdr3.saiPrimary).trim()).length() > 0)
    			sagIdList.add(String.valueOf(sagErr.comReplyHdr3.saiPrimary).trim());
    
    	if ((String.valueOf(sagErr.comReplyHdr3.saiAlt1).trim()).length() > 0)
    			sagIdList.add(String.valueOf(sagErr.comReplyHdr3.saiAlt1).trim());
    			
    	if ((String.valueOf(sagErr.comReplyHdr3.saiAlt2).trim()).length() > 0)
    			sagIdList.add(String.valueOf(sagErr.comReplyHdr3.saiAlt2).trim());
    			
    	if ((String.valueOf(sagErr.comReplyHdr3.saiAlt3).trim()).length() > 0)
    			sagIdList.add(String.valueOf(sagErr.comReplyHdr3.saiAlt3).trim());
    }
}
