//$Id: AsonHitResp.java,v 1.4.18.2 2011/11/09 00:47:59 ve1794 Exp $

package com.sbc.eia.bis.BusinessInterface.ServiceAddress.ason;

import java.util.Hashtable;
import java.util.StringTokenizer;

import com.sbc.bccs.idl.helpers.TN;
import com.sbc.eia.bis.BusinessInterface.ason.ASONTag;
import com.sbc.eia.bis.framework.BisContextManager;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.bis.lim.transactions.common.RetrieveLocReq;
import com.sbc.eia.bis.lim.transactions.common.ServiceAddrMatchResp;
import com.sbc.eia.bis.lim.transactions.common.ServiceLocationBuilder;
import com.sbc.eia.bis.lim.util.LIMBase;
import com.sbc.eia.bis.lim.util.LIMIDLUtil;
import com.sbc.eia.idl.bis_types.SystemFailure;
import com.sbc.eia.idl.lim.helpers.AddressHandler;
import com.sbc.eia.idl.lim.helpers.AddressHandlerASON;
import com.sbc.eia.idl.lim.helpers.AddressHandlerException;
import com.sbc.eia.idl.lim_types.ServiceLocation;
import com.sbc.gwsvcs.service.asonservice.interfaces.ASONLvngUnitValResp;
import com.sbc.gwsvcs.service.asonservice.interfaces.ASONSagValidResp;
import com.sbc.gwsvcs.service.facsaccess.interfaces.C1_Section_t;
import com.sbc.gwsvcs.service.facsaccess.interfaces.CTL_Section_t;
import com.sbc.gwsvcs.service.facsaccess.interfaces.Fasg_Inq_Req_t;
import com.sbc.gwsvcs.service.facsaccess.interfaces.Header_t;
import com.sbc.gwsvcs.service.facsaccess.interfaces.INQ_FASG_Section_t;
import com.sbc.gwsvcs.service.facsaccess.interfaces.LOOP_Section_t;
import com.sbc.gwsvcs.service.facsaccess.interfaces.TrnsptType_e;

/**
 * This class represents an address-match from ASONService.
 * Creation date: (7/17/07)
 * @author: Jean Duka
 */
public class AsonHitResp extends ServiceAddrMatchResp
{
	protected ASONSagValidResp svr = null;
	protected AsonHelper asonHelper = null;
	protected ASONLvngUnitValResp luv = null;
    protected ServiceLocation addressData = null;
    protected RetrieveLocReq request = null;
    	
	private String descAddr = "";
	private boolean stNmInd = false;
    private boolean facsLoopInd = false;

    /**
     * Construct this object from a ASONService "hit" response.
     * @param utility LIMBase
     * @param aLuv ASONLvngUnitValResp
     * @param aRequest AsonRetrieveLocReq
     * @exception SystemFailure
     */
    public AsonHitResp(
            LIMBase utility, 
            ASONLvngUnitValResp aLuv, 
            AsonRetrieveLocReq aRequest)
    	throws 
            SystemFailure
    {
    	super(utility);
    	luv = aLuv;
        request = aRequest;
    	asonHelper = new AsonHelper();
    	asonHelper.address = new AddressHandlerASON();
    	try
        {
    		asonHelper.address.setASONFields(
    							luv.stNbrFld1.trim(),		     // StNbr 
    							luv.stNbrFld2.trim(),		     // StNbrSfx
    							luv.streetDirection.trim(),      // StDir
    							luv.streetName.trim(),		     // AddressName
    			 				luv.communityName.trim(),	     // City
    			 				luv.raiCode.trim(),			     // State
    			 				"",							     // ZipCode
    			 				"",							     // County
    			 				luv.assignedHseNumberInd.trim(), // AssignedHseNbrInd
                                luv.locTag1.trim(),              // Structure Tag
                                luv.loc1.trim(),                 // Structure Value
                                luv.locTag2.trim(),              // Level Tag
                                luv.loc2.trim(),                 // Level Value
                                luv.locTag3.trim(),              // Unit Tag
                                luv.loc3.trim()                  // Unit Value
                                 );
        }    
    	catch (AddressHandlerException ahe){}
    }
    
    /**
     * Construct this object from a ASONSagValidResp response.
     * This constructor was created for the SagValidationRequest enhancement.
     * @param utility LIMBase
     * @param addrResp ASONSagValidResp
     * @param aRequest AsonRetrieveLocReq
     * @param sagMsg String
     * @exception SystemFailure
     */
    public AsonHitResp(
            LIMBase utility, 
            ASONSagValidResp addrResp, 
            AsonRetrieveLocReq aRequest, 
            String sagMsg)
    	throws 
            SystemFailure
    {
    	super(utility);
        request = aRequest;
    	asonHelper = new AsonHelper();
    	asonHelper.address = new AddressHandlerASON();
    	setSvr (addrResp);
    	
    	try
        {
    		asonHelper.address.setASONFields(
    							aRequest.getAddr().getHouseNumber(),		    // StNbr 
    							aRequest.getAddr().getHouseNumberSuffix(),		// StNbrSfx 
    							svr.directional.trim(),      					// StDir
    							svr.addressNameSag.trim(),		     			// AddressName
    			 				svr.communitySag.trim(),	     				// City
    			 				svr.raiCode.trim(),			     				// State
    			 				svr.zipCodeSag.trim (),							// ZipCode
    			 				svr.countyAbbrev,							    // County
    			 				"", 											// AssignedHseNbrInd
                                "",              								// Structure Tag
                                "",                 							// Structure Value
                                "",              								// Level Tag
                                "",                 							// Level Value
                                "",              								// Unit Tag
                                ""                  							// Unit Value
                                 );
        }    
    	catch (AddressHandlerException ahe){}	
    	asonHelper.setAAI (sagMsg);
    	utility.setSagInfoOnly (true);
    }
    
    /**
     * Compares two objects for equality. Returns a boolean that indicates
     * whether this object is equivalent to the specified object. This method
     * is used when an object is stored in a hashtable.
     * @param obj the Object to compare with
     * @return true if these Objects are equal; false otherwise.
     * @see Hashtable
     */
    public boolean equals(Object obj)
    {
    	if (obj instanceof AsonHitResp)
    	{
    		return hashString().equals(((AsonHitResp) obj).hashString());
    	}
    	return false;
    }
    /**
     * Getter for Descriptive Address.
     * Creation date: (8/21/01 6:33:54 AM)
     * @return String
     * @see #setDescAddr
     */
    public String getDescAddr() 
    {
    	return descAddr;
    }
    /**
     * Getter method for the boolean stNmInd.
     * Creation date: (8/10/01 8:11:51 AM)
     * @return boolean
     * @see #setStNmInd
     */
    public boolean getStNmInd() 
    {
    	return stNmInd;
    }
    
    /**
     * Generates a hash code for the receiver.
     * This method is supported primarily for
     * hash tables, such as those provided in java.util.
     * @return an integer hash code for the receiver
     * @see Hashtable
     */
    public int hashCode()
    {
    	return hashString().hashCode();
    }
    /**
     * Return a String to be used by methods equals() and hashCode().
     * Creation date: (3/13/01 3:11:37 PM)
     * @return String
     */
    protected String hashString()
    {
    	return (
    		svr.comReplyHdr1.ReplyCode +
    		svr.comReplyHdr1.MsgLength +
    		svr.comReplyHdr1.TmfAction +
    		svr.comReplyHdr1.RequestDateYYYYMMDD +
    		svr.comReplyHdr1.RequestTimeHHMMSSCC +
    		svr.comReplyHdr1.ReplyDateYYYYMMDD +
    		svr.comReplyHdr1.ReplyTimeHHMMSSCC +
    		svr.comReplyHdr1.MsgCode +
    		svr.comReplyHdr1.MsgDelimiter +
    		svr.comReplyHdr1.MsgText +
    		svr.comReplyHdr2.addressName +
    		svr.comReplyHdr2.community +
    		svr.comReplyHdr2.zipCode +
    		svr.comReplyHdr2.descriptiveAddrInd +
    		svr.raiCode +
    		svr.sagAreaId +
    		svr.addressNameSag +
    		svr.alphaNumInd +
    		svr.alternateAddressInd +
    		svr.analogOrDigitalType +
    		svr.busRateBand +
    		svr.centralOffice +
    		svr.cityAbbreviation +
    		svr.communitySag +
    		svr.countyAbbrev +
    		svr.directional +
    		svr.e911Exempt +
    		svr.e911Nrg +
    		svr.e911Sur +
    		svr.needBillAddrInd +
    		svr.editAgainstLufFile +
    		svr.needLocLevel1Ind +
    		svr.needLocLevel2Ind +
    		svr.needLocLevel3Ind +
    		svr.needCommNameInd +
    		svr.secondLineInd +
    		svr.needBillAddrInd +
    		svr.equipmentType +
    		svr.exchange +
    		svr.highAddrRange +
    		svr.lastAssignedHouseNumUsed +
    		svr.lfacsDupAddressInd +
    		svr.lowAddrRange +
    		svr.map +
    		svr.matchInd +
    		svr.municipality +
    		svr.oddEvenIndicator +
    		svr.rateBand +
    		svr.rateZone +
    		svr.state +
    		svr.tar +
    		svr.sagWireCenter +
    		svr.zipCodeSag
    		);
    }

    /**
     * Construct and store an ServiceLocation from the BRMS Hit data.
     * Creation date: (2007/7/022 10:02:01 AM)
     */
    protected void setServiceLocation()
    {
        ServiceLocationBuilder serviceLocationBuilder = new ServiceLocationBuilder (request.getLocationPropertiesRequested());
        
        serviceLocationBuilder.setServiceAddress(getServiceAddress().getAddress2_HitResponse());
        serviceLocationBuilder.setCentralOfficeCode(svr.centralOffice);
        serviceLocationBuilder.setE911Exempt(svr.e911Exempt);
        serviceLocationBuilder.setE911NonRecurringCharge(svr.e911Nrg);
        serviceLocationBuilder.setE911Surcharge(svr.e911Sur);
        serviceLocationBuilder.setExchangeCode(svr.exchange);
        serviceLocationBuilder.setSurcharge4Percent(String.valueOf(svr.operSur4Ind));
        serviceLocationBuilder.setSurcharge16Percent(String.valueOf(svr.operSur16Ind));
        serviceLocationBuilder.setTarCode(svr.tar);
        
        if (svr.sagWireCenter != null && (svr.sagWireCenter.trim()).length() > 0)
        {
            serviceLocationBuilder.setSagWireCenter(svr.sagWireCenter);
        }
        
        if (facsLoopInd) 
        {
            if (workingTnSet.size() > 0) 
            {
                serviceLocationBuilder.setWorkingServiceOnLocation("true");
        
                TN[] tnList = getWorkingTnList();
                serviceLocationBuilder.setTelephoneNumber(tnList[0].toString());
            } 
            else 
            {
                serviceLocationBuilder.setWorkingServiceOnLocation("false");
            }   
        } 
        else
        {
            serviceLocationBuilder.setWorkingServiceOnLocation("");
        }

        // set RateZoneBandCode        
        StringBuffer rateBandZone = new StringBuffer((String.valueOf(svr.rateBand).equals("")) ? " " : String.valueOf(svr.rateBand));
        rateBandZone.append(((String.valueOf(svr.rateZone).equals("")) ? " " : String.valueOf(svr.rateZone)));
        if (!rateBandZone.toString().equals("  "))
        {
            serviceLocationBuilder.setRateZoneBandCode(rateBandZone.toString());
        }
        
        // set LocalProviderServingOfficeCode
        String aNpaNxx = "";
        if (svr.primaryLso != null && svr.primaryLso.trim().length() > 0)
        {
            serviceLocationBuilder.setLocalProviderServingOfficeCode(svr.primaryLso);
            serviceLocationBuilder.setPrimaryNpaNxx(svr.primaryLso);
        }
        else
        {
           for (int i = 0; i < svr.npaNxx.length; i++)
           {
                if (svr.npa.equalsIgnoreCase(svr.npaNxx[i].npa))
                {
                    aNpaNxx = svr.npaNxx[i].npa + svr.npaNxx[i].nxx;
                    i = svr.npaNxx.length;
                }
           }
    
           if (!(aNpaNxx.equalsIgnoreCase("")))
           {
            serviceLocationBuilder.setLocalProviderServingOfficeCode(aNpaNxx);
            serviceLocationBuilder.setPrimaryNpaNxx(aNpaNxx);
            }
        }

        utility.locServiceLocation = serviceLocationBuilder.getCachedServiceLocation();
        serviceLocation = serviceLocationBuilder.getServiceLocation();
    }
    
    /**
     * Construct and store an AddressHandler from the ASON Hit data.
     * Creation date: (3/7/01 10:02:01 AM)
     */
    protected AddressHandlerASON getServiceAddress()
    {
    	String ahaAddrInfo = "";
    	String ahaAssignedHouseNbr = "";
    	String ahaHouseNbrPfx = "";
    	String ahaHouseNbr = "";
    	String ahaHouseNbrSfx = "";
   
   		if (luv != null)	
   		{
    		if	(luv.advisoryMsg.indexOf("A045") >= 0)
    			ahaAddrInfo = ASONTag.SUPPLEMENTAL_ADDRESS_MSG;
    		else if ((getDescAddr().trim()).equals(""))
    			ahaAddrInfo = asonHelper.address.getAddAddrInfo();
    		else
    			ahaAddrInfo = getDescAddr();
    	
    		if (luv.assignedHseNumberInd.equalsIgnoreCase("Y"))
        	{
    			ahaAssignedHouseNbr = AsonHelper.formatStNbr(luv.stNbrFld1);	
    		}
    		else
        	{
    			asonHelper.address.parseHouseNbr(luv.stNbrFld1);
    			ahaHouseNbrPfx  = AsonHelper.formatStNbr(asonHelper.address.getHousNbrPfx());
    			ahaHouseNbr 	= AsonHelper.formatStNbr(asonHelper.address.getHousNbr());
    			ahaHouseNbrSfx	= luv.stNbrFld2.trim();
    		}
   		}
   		else	// SagValidationRequest case
   		{
   			ahaAddrInfo = asonHelper.getAAI();
   			ahaHouseNbrPfx  = asonHelper.address.getHousNbrPfx();	
    		ahaHouseNbr 	= asonHelper.address.getHousNbr();		
    		ahaHouseNbrSfx  = asonHelper.address.getHouseNumberSuffix();	
   		}

    	return (new AddressHandlerASON(
    		asonHelper.address.getRoute(),				// aRoute
    		asonHelper.address.getBox(),				// aBox,
    		ahaHouseNbrPfx,								// aHouseNumberPrefix
    		ahaHouseNbr,								// aHouseNumber
    		ahaAssignedHouseNbr,						// aAssignedHouseNumber
    		ahaHouseNbrSfx,    							// aHouseNumberSuffix
    		asonHelper.address.getStDir(),				// aStreetDirection
    		asonHelper.address.getStName(),				// aStreetName
    		asonHelper.address.getStThorofare(),		// aStreetThoroughfare
    		asonHelper.address.getStNameSfx(),			// aStreetNameSuffix
    		asonHelper.address.getCity(),				// aCity
    		asonHelper.address.getState(),				// aState
    		svr.zipCodeSag.trim(),						// aPostalCode
    		svr.countyAbbrev.trim(),					// aCounty
    		asonHelper.address.getCountry(),			// aCountry
    		asonHelper.address.getStructType(),			// aStructure Type
            asonHelper.address.getStructValue(),        // aStructure Type
            asonHelper.address.getLevelType(),          // aLevel Type
            asonHelper.address.getLevelValue(),         // aLevel Value
            asonHelper.address.getUnitType(),           // aUnit Type
            asonHelper.address.getUnitValue(),          // aUnit Value
    		ahaAddrInfo.trim(),                         // aAdditionalInfo
            getStNmInd()));								// aStNmInd
    }

    /**
     * Setter for Descriptive Address of AsonHitResp class.
     * Creation date: (8/21/01 6:33:54 AM)
     * @param newDescAddr String
     * @see #getDescAddr
     */
    public void setDescAddr(String newDescAddr) 
    {
    	descAddr = newDescAddr;
    }

    /**
     * Setter method for the boolean stNmInd.
     * Creation date: (8/10/01 8:11:51 AM)
     * @param newStNmInd boolean
     * @see #getStNmInd
     */
    public void setStNmInd(boolean newStNmInd) 
    {
    	stNmInd = newStNmInd;
    }
    /**
     * Setter for ASONSagValidResp of AsonHitResp class.
     * Creation date: (7/24/01 7:05:53 AM)
     * @param newSvr ASONSagValidResp
     */
    public void setSvr(ASONSagValidResp aSvr) 
    {
    	svr = aSvr;
    }
    /**
     * Converts a String delimited by spaces into an array of Strings, excluding the spaces.
     * Creation date: (8/31/01 12:00:00 PM)
     * @return String[]
     * @param str String
     */
    protected String[] stringToTokens(String str) 
    {
    	/*
    	 * Alan Sit - Local: (5/1/2002 5:23:32 PM)
    	 * Simpler way to tokenize a string into an array of strings
    	 */
    	StringTokenizer str_tmp = new java.util.StringTokenizer (str, " ");
    	int tokenCount = str_tmp.countTokens();
    	
    	if ( tokenCount == 0 )
    		return null;
    
    	String[] item = new String[tokenCount];
    	int counter = 0;
    	while ( str_tmp.hasMoreTokens() ){
    		item[counter] = str_tmp.nextToken();
    		counter++;
    	} // while
    
    	return item;	
    }
    /**
     * Returns a String that represents the value of this object.
     * @return a string representation of the receiver
     */
    public String toString()
    {
    	final String nl = System.getProperty("line.separator","\n");
    	StringBuffer sb = new StringBuffer(nl + "AsonHit[ ");
        try{
            sb.append(new AddressHandler(LIMIDLUtil.convertAddress2ToAddress(addressData.aServiceAddress)).toString());
        }
        catch (AddressHandlerException e) {}
        catch (org.omg.CORBA.BAD_OPERATION e) {}
        catch (java.lang.NullPointerException e) {}
        
        try { sb.append(nl + "CentralOfficeCode[" + addressData.aCentralOfficeCode.theValue() + "]"); }
        catch (org.omg.CORBA.BAD_OPERATION e) {}
        catch (java.lang.NullPointerException e) {}

        try { sb.append(nl + "E911Exempt[" + addressData.aE911Exempt.theValue() + "]"); }
        catch (org.omg.CORBA.BAD_OPERATION e) {}
        catch (java.lang.NullPointerException e) {}

        try { sb.append(nl + "E911NonRecurringCharge[" + addressData.aE911NonRecurringCharge.theValue() + "]"); }
        catch (org.omg.CORBA.BAD_OPERATION e) {}
        catch (java.lang.NullPointerException e) {}

        try { sb.append(nl + "E911Surcharge[" + addressData.aE911Surcharge.theValue() + "]"); }
        catch (org.omg.CORBA.BAD_OPERATION e) {}
        catch (java.lang.NullPointerException e) {}

        try { sb.append(nl + "ExchangeCode[" + addressData.aExchangeCode.theValue() + "]"); }
        catch (org.omg.CORBA.BAD_OPERATION e) {}
        catch (java.lang.NullPointerException e) {}

        try { sb.append(nl + "LocalProviderServingOfficeCode[" + addressData.aLocalProviderServingOfficeCode.theValue() + "]"); }
        catch (org.omg.CORBA.BAD_OPERATION e) {}
        catch (java.lang.NullPointerException e) {}

        try { sb.append(nl + "PrimaryNpaNxx[" + addressData.aPrimaryNpaNxx.theValue() + "]"); }
        catch (org.omg.CORBA.BAD_OPERATION e) {}
        catch (java.lang.NullPointerException e) {}

        try { sb.append(nl + "RateZoneBandCode[" + addressData.aRateZoneBandCode.theValue() + "]"); }
        catch (org.omg.CORBA.BAD_OPERATION e) {}
        catch (java.lang.NullPointerException e) {}

        try { sb.append(nl + "Surcharge4Percent[" + addressData.aSurcharge4Percent.theValue() + "]"); }
        catch (org.omg.CORBA.BAD_OPERATION e) {}
        catch (java.lang.NullPointerException e) {}

        try { sb.append(nl + "Surcharge16Percent[" + addressData.aSurcharge16Percent.theValue() + "]"); }
        catch (org.omg.CORBA.BAD_OPERATION e) {}
        catch (java.lang.NullPointerException e) {}

        try { sb.append(nl + "TarCode[" + addressData.aTarCode.theValue() + "]"); }
        catch (org.omg.CORBA.BAD_OPERATION e) {}
        catch (java.lang.NullPointerException e) {}

        try { sb.append(nl + "TelephoneNumber[" + addressData.aTelephoneNumber.theValue() + "]"); }
        catch (org.omg.CORBA.BAD_OPERATION e) {}
        catch (java.lang.NullPointerException e) {}

        try { sb.append(nl + "WorkingServiceOnLocation[" + addressData.aWorkingServiceOnLocation.theValue() + "]"); }
        catch (org.omg.CORBA.BAD_OPERATION e) {}
        catch (java.lang.NullPointerException e) {}

        sb.append(" ]");

    	return sb.toString();
    }
   
    /**
     * Getter method for the FACSQuery request.
     * Creation date: (10/14/03 10:45:10 AM)
     * Created By:	RamaKishore Y (rk7964)
     * @return FACSQuery
     */
    public Fasg_Inq_Req_t getFACSQueryReq()
    {
    	// Create hdr 
    	String userid = (new BisContextManager(getUtility().getCallerContext())).getUserId();
    	Header_t hdr = new Header_t(userid, "LIMBIS", "", "", TrnsptType_e.RPC_TRNSPT, "");
    	
    	C1_Section_t c1 = new C1_Section_t(ASONTag.FUNCTION_TYPE,					//Function type ("ADD", "INQ", etc.) 
    										ASONTag.TRANSACTION_TYPE,				//Tran type ("TRM", etc.) 
    										ASONTag.EMPTY_STRING,					//Order type Set to "S" 
    										ASONTag.EMPTY_STRING,					//Order Number 
    										ASONTag.EMPTY_STRING,					//Correction Suffix 
    										ASONTag.EMPTY_STRING,					//Version Number 
    										svr.facsWireCenter,						//Wire Center 
    										ASONTag.EMPTY_STRING,					//Origin ("DGATEA", etc.) 
    										ASONTag.EMPTY_STRING,					//Destination ("LFACS") 
    										ASONTag.EMPTY_STRING,					//Priority (D=Deferred) 
    										ASONTag.EMPTY_STRING,					//DD, blank fill 
    										ASONTag.EMPTY_STRING,					//DC, Change flag (Blank) 
    										ASONTag.EMPTY_STRING,					//Solicited/Unsol (Blank) 
    										ASONTag.EMPTY_STRING,					//Status. Blank fill 
    										ASONTag.EMPTY_STRING,					//Manual Assig (Blank) 
    										ASONTag.EMPTY_STRING);					//Msg Format Set to null 
    		
    		CTL_Section_t ctl = new CTL_Section_t(ASONTag.EMPTY_STRING,			    //Tran Type ("UPD", etc) 
    											  ASONTag.EMPTY_STRING,				//Date Stamp (YYMMDD)
    											  ASONTag.EMPTY_STRING,				//Time Stamp (HHMMSS) 
    											  ASONTag.EMPTY_STRING,				//Sending System ("DGATE?")
    											  ASONTag.EMPTY_STRING,				//Dest (Alpha RFS or Entity) 
    											  ASONTag.EMPTY_STRING,				//SO Number (Project Number) 
    											  ASONTag.EMPTY_STRING,				//Msg N. (Client Tracking #)
    											  ASONTag.EMPTY_STRING,				//Employee ID
    											  ASONTag.EMPTY_STRING); 			//Wire Center 
    											  
    		// Allow for house number options
    		String streetNumber1 = "";
    		String streetNumber2 = "";
    		if(luv.stNbrFld1 != null)
    			streetNumber1 = luv.stNbrFld1.trim();
    			
    		if(luv.stNbrFld2 != null && !luv.stNbrFld2.trim().equals (""))
    			streetNumber2 = "-" + luv.stNbrFld2.trim();
    			
    		String houseNbr = AsonHelper.formatStNbr(streetNumber1) + streetNumber2;
    		
    		String streetName = "";
    		
			if (luv.streetDirection != null)
    			streetName = luv.streetDirection.trim() + " ";
    		if (luv.streetName != null)
    			streetName = streetName + luv.streetName.trim();
    		streetName = streetName.trim ();
    		    			
    		int index = -1;
    		while ((index = streetName.indexOf("*")) != -1) 
            {
    			streetName = streetName.substring(0,index) + streetName.substring(index + 1);
      		}
    				
    		INQ_FASG_Section_t fasg = new INQ_FASG_Section_t(
    					 ASONTag.LFACS_EMP,			                                                    //employee identifier
    					 (houseNbr.length() > ASONTag.LFACS_HSE_NBR_MAX) ?				
    					             houseNbr.substring(0,ASONTag.LFACS_HSE_NBR_MAX) :
    					             houseNbr,				                                            //basic address house number
    					 (streetName.length() > ASONTag.LFACS_ST_NM_MAX) ?				
    							streetName.substring(0,ASONTag.LFACS_ST_NM_MAX) :
    					        streetName,                                                             //street address
    					 (luv.loc3 == null) ? ASONTag.EMPTY_STRING : luv.loc3.trim(),					//unit id
    					 (luv.locTag3 == null) ? ASONTag.EMPTY_STRING : luv.locTag3.trim(),				//req if UID (apt,lot,rm,slip,etc)
    					 (luv.loc2 == null) ? ASONTag.EMPTY_STRING : luv.loc2.trim(),					//floor designation
    					 (luv.locTag2 == null) ? ASONTag.EMPTY_STRING : luv.locTag2.trim(),				//req if EID (fl)
    					 (luv.loc1 == null) ? ASONTag.EMPTY_STRING : luv.loc1.trim(),					//structure designation
    					 (luv.locTag1 == null) ? ASONTag.EMPTY_STRING : luv.locTag1.trim(),				//req if SID (bldg,wing,pier)
    					 (luv.communityName == null) ? ASONTag.EMPTY_STRING : luv.communityName.trim(),	//community name
    					 ASONTag.EMPTY_STRING,                                                          //(luv.raiCode == null) ? ASONTag.EMPTY_STRING : luv.raiCode.trim(),					//state name
    					 ASONTag.EMPTY_STRING,					                                        //circuit identifier
    					 ASONTag.EMPTY_STRING,					                                        //circuit termination identifier
    					 ASONTag.EMPTY_STRING,					                                        //external cable name
    					 ASONTag.EMPTY_STRING,					                                        //pair number	
    					 ASONTag.EMPTY_STRING,					                                        //pending loop data only (Y/N)
    					 ASONTag.EMPTY_STRING,					                                        //TAS working with telephone number 
    					 ASONTag.EMPTY_STRING,					                                        //TAS pos number of the switchboard
    					 ASONTag.EMPTY_STRING,					                                        //TAS jack number of associated pos
    					 ASONTag.EMPTY_STRING,					                                        //serving terminal address
    					 ASONTag.EMPTY_STRING					                                        //assignable binding post number
    					 ); 		
    
    	StringBuffer facsReqInfo = new StringBuffer("FACS WC - ");
    	facsReqInfo.append(svr.facsWireCenter + "|");
    	facsReqInfo.append(" streetName - ");
    	facsReqInfo.append(streetName + "|");
    	facsReqInfo.append(" houseNumber - ");
    	facsReqInfo.append(houseNbr + "|");
    	facsReqInfo.append(" loc1 - ");
    	facsReqInfo.append(luv.loc1 + "|");
    	facsReqInfo.append(" locTag1 - ");
    	facsReqInfo.append(luv.locTag1 + "|");
    	facsReqInfo.append(" loc2 - ");
    	facsReqInfo.append(luv.loc2 + "|");
    	facsReqInfo.append(" locTag2 - ");
    	facsReqInfo.append(luv.locTag2 + "|");
    	facsReqInfo.append(" loc3 - ");
    	facsReqInfo.append(luv.loc3 + "|");
    	facsReqInfo.append(" locTag3 - ");
    	facsReqInfo.append(luv.locTag3 + "|");
    	facsReqInfo.append(" communityName - ");
    	facsReqInfo.append(luv.communityName + "|");
    	
    	getUtility().log(LogEventId.INFO_LEVEL_2,facsReqInfo.toString());
    	return( new Fasg_Inq_Req_t(hdr, c1, ctl, fasg)); 
    
    }
    	
    /**
     * Return true if the line status is "working".
     * @return boolean
     * @param loopInfo LOOP_Section_t
     */
    protected boolean lineIsWorking(LOOP_Section_t loopInfo)
    {
    	if ((loopInfo.STAT.trim().equalsIgnoreCase(ASONTag.LFACS_LINE_WORKING)) ||
    		  (loopInfo.STAT.trim().equalsIgnoreCase(ASONTag.LFACS_LINE_R_WORKING)))
    		return true;
    	return false;
    }
    
    protected boolean lineIsWorking_facsrc(com.sbc.eia.bis.BusinessInterface.ServiceAddress.ason.Loop loop)
    {
    	if ((loop.getSTAT().toString().trim().equalsIgnoreCase(ASONTag.LFACS_LINE_WORKING)) ||
      		  (loop.getSTAT().toString().trim().equalsIgnoreCase(ASONTag.LFACS_LINE_R_WORKING)))
      		return true;
      	return false;
    }
    
    /**
     * If the TN in the SupLnInfo_t object is working, add it to the TN list.
     * @param loopInfo LOOP_Section_t[]
     */
    protected void addWorkingTn(LOOP_Section_t[] loopInfo)
    {
        String workingTN = "";
        for (int i=0; i < loopInfo.length; i++) 
        { 
            facsLoopInd = true;
            
            if (loopInfo[i].CKID.trim().length() == 7)
                workingTN = "000" + loopInfo[i].CKID.trim();
            else if (loopInfo[i].CKID.trim().length() == 8)
                workingTN = "000-" + loopInfo[i].CKID.trim();
            else if ((loopInfo[i].CKID.trim().length() == 10) ||
                     (loopInfo[i].CKID.trim().length() == 12))
                workingTN = loopInfo[i].CKID.trim();
            else
                continue;
                    
            TN tn = new TN(workingTN);
    
            if (tn.isValid()) 
            {
                // check status only for first 3 elements.
                // For the rest of the elements, if TN is valid, status is assumed to be WKG/RWKG
                if ((i < 2 && lineIsWorking(loopInfo[i])) || i >=2) 
                {            
                    if (tn.getNpa().equals("000")) 
                    {
                        addWorkingTn("", tn.getNxx(),tn.getLine());
                    }
                    else
                    {       
                        addWorkingTn(tn.getNpa(), tn.getNxx(), tn.getLine());
                    }
                    break;
                }
            } 
            workingTN = "";
        } // end for loop
    }
    
    protected void addWorkingTn_facsrc(com.sbc.eia.bis.BusinessInterface.ServiceAddress.ason.Loop[] loops)
    {
        String workingTN = "";
        for (int i=0; i < loops.length; i++) 
        { 
            facsLoopInd = true;
            if(loops[i].getCKID()!=null)
            {
            	if (loops[i].getCKID().toString().trim().length()== 7)
            		workingTN = "000" + loops[i].getCKID().toString().trim();
            	else if (loops[i].getCKID().toString().trim().length() == 8)
            		workingTN = "000-" + loops[i].getCKID().toString().trim();
            	else if ((loops[i].getCKID().toString().trim().length() == 10) ||
            			(loops[i].getCKID().toString().trim().length() == 12))
            		workingTN = loops[i].getCKID().toString().trim();
            	else
            		continue;
            }
            else
            {
            	continue;
            }
            TN tn = new TN(workingTN);
    
            if (tn.isValid()) 
            {
                // check status only for first 3 elements.
                // For the rest of the elements, if TN is valid, status is assumed to be WKG/RWKG
                if ((i < 2 && lineIsWorking_facsrc(loops[i])) || i >=2) 
                {            
                    if (tn.getNpa().equals("000")) 
                    {
                        addWorkingTn("", tn.getNxx(),tn.getLine());
                    }
                    else
                    {       
                        addWorkingTn(tn.getNpa(), tn.getNxx(), tn.getLine());
                    }
                    break;
                }
            } 
            workingTN = "";
        } // end for loop
    }
}
