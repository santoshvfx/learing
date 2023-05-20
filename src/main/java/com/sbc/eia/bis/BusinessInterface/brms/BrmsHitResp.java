// $Id: BrmsHitResp.java,v 1.19 2008/02/29 23:21:51 jh9785 Exp $

package com.sbc.eia.bis.BusinessInterface.brms;

import com.sbc.bccs.idl.helpers.IDLUtil;
import com.sbc.bccs.idl.helpers.TN;
import com.sbc.eia.bis.framework.BisContextManager;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.bis.lim.transactions.RetrieveLocation.AddrMatchResp;
import com.sbc.eia.bis.lim.transactions.RetrieveLocation.ProviderLocationPropertyBuilder;
import com.sbc.eia.bis.lim.transactions.RetrieveLocation.RetrieveLocReq;
import com.sbc.eia.bis.lim.util.LIMBase;
import com.sbc.eia.bis.lim.util.LIMTag;
import com.sbc.eia.idl.lim.helpers.AddressHandler;
import com.sbc.eia.idl.lim.helpers.AddressHandlerBRMS;
import com.sbc.eia.idl.lim.helpers.AddressHandlerException;
import com.sbc.eia.idl.lim_types.AddressOpt;
import com.sbc.eia.idl.lim_types.ProviderLocationProperty;
import com.sbc.eia.bis.lim.database.queries.BRMSSagInfo;

/**
 * This class represents an address-match from SAG01F.
 * Creation date: (3/12/01 9:37:38 AM)
 * @author: David Brawley
 */
public class BrmsHitResp extends AddrMatchResp
{
	private String descAddr = "";
	private boolean stNmInd = false;
    boolean facsLoopInd = false;

    protected ProviderLocationProperty addressData = null;
	protected AddressHandlerBRMS ahBrmsAddr = null;
	protected RetrieveLocReq request = null;
    protected String wireCenter = "";
	protected BRMSSagInfo [] sagResp = null; 
	protected String aasgndHousNbr = "";
	protected String circuitId = null;
	protected String lpStatus = null;
	protected int addrCount = 0;
    /**
     * Construct this object from a LFACS_SAG_LU table "hit" response.
     * @param utility LIMBase
     */
    public BrmsHitResp (LIMBase utility, RetrieveLocReq aRequest)
    {
    	super(utility);
        request = aRequest;
    }

    /**
     * Construct and store a LocationId from the ASON Hit data.
     * Creation date: (3/7/01 10:02:01 AM)
     * @param unitArray Unit[]
     */
    protected void setLocationId()
    {
        locationId = IDLUtil.toOpt("");
    }

    /**
     * Construct and store an ProviderLocationPropertySeq from the BRMS Hit data.
     * Creation date: (3/7/01 10:02:01 AM)
     */
    protected void setProviderLocationPropertySeq()
    {
        ProviderLocationPropertyBuilder propertyBuilder = new ProviderLocationPropertyBuilder(request.getLocationPropertiesRequested());

        try
        {
            propertyBuilder.setProviderName( request.getProviderLocationPropertiesToGet()[0].aProviderName.theValue() );
        }
        catch (org.omg.CORBA.BAD_OPERATION e) 
        {
            propertyBuilder.setProviderName (null);
        }
        catch (java.lang.NullPointerException e) 
        {
            propertyBuilder.setProviderName (null);
        }
        propertyBuilder.setServiceAddress((AddressOpt) IDLUtil.toOpt(AddressOpt.class, getServiceAddress().getAddress()));

        propertyBuilder.setExchangeCode (sagResp[0].exchange);
        propertyBuilder.setLataCode (BRMSTag.LATA_CODE);
        propertyBuilder.setLocalProviderServingOfficeCode (getWireCenter());
        propertyBuilder.setPrimaryNpaNxx (getWireCenter());
        propertyBuilder.setSagWireCenter (sagResp[0].wireCenter);
        propertyBuilder.setTarCode (sagResp[0].townTaxCode);        
        
        if (facsLoopInd) 
        {
            if (workingTnSet.size() > 0) 
            {
                propertyBuilder.setWorkingServiceOnLocation("true");
        
                TN[] tnList = getWorkingTnList();
                propertyBuilder.setTelephoneNumber(tnList[0].toString());      
            } 
            else 
            {         
                propertyBuilder.setWorkingServiceOnLocation("false");
            }   
        } 
        else
        {
            propertyBuilder.setWorkingServiceOnLocation("");
        }
          
        // populate ProviderLocationPropertySeq value returned to client 
        providerLocationPropertyArray = new ProviderLocationProperty[1];
        providerLocationPropertyArray[0] = propertyBuilder.getProviderLocationProperty();
		utility.locProviderPropForCache[0] = propertyBuilder.getCachedProviderLocationProperty();
    }
    
    /**
     * If the TN in the LFACS_SAG_LU table is working, add it to the TN list.
     * Creation date: (3/15/01 8:45:16 AM)
     * @param String circuitId
     */
    protected void addWorkingTn(String circuitId)
    {
    	String workingTN = "";
    
    	if ((circuitId != null) &&
    		(circuitId.length() > 0))
    	{
    		if (lineIsWorking())
    		{
    			switch(circuitId.length()){
    				case 7 :
    					workingTN = "000" + circuitId;
    					break;
    				case 8 :
    					workingTN = "000-" + circuitId;
    					break;
    				case 10 :
    				case 12 :
    					workingTN = circuitId;
    					break;
    				default :
    					return;
    			} // end switch
    			
    			TN tn = new TN(workingTN);
    
    			if (tn.isValid())
    				if (tn.getNpa().equals("000"))
    					addWorkingTn("", tn.getNxx(),tn.getLine());
    				else		
    					addWorkingTn(tn.getNpa(), tn.getNxx(), tn.getLine());
    		}
    	}
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
    	if (obj instanceof BrmsHitResp)
    	{
    		return hashString().equals(((BrmsHitResp) obj).hashString());
    	}
    	return false;
    }
    /**
     * Getter method for aasgndHousNbr.
     * Creation date: (4/12/02 10:24:22 AM)
     * @return String
     * @see #setAasgndHousNbr(String)
     */
    public String getAasgndHousNbr() {
    	return aasgndHousNbr;
    }
    /**
     * Getter method for brmsAddr.
     * Creation date: (1/7/02 1:39:19 PM)
     * @return AddressHandlerBRMS
     * @see #setBrmsAddr(AddressHandlerBRMS)
     */
    public AddressHandlerBRMS getBrmsAddr() {
    	return ahBrmsAddr;
    }
    /**
     * Getter method for circuitId.
     * Creation date: (9/18/02 8:44:31 AM)
     * @return java.lang.String
     */
    public String getCircuitId() {
    	return circuitId;
    }
    /**
     * Getter for Descriptive Address.
     * Creation date: (8/21/01 6:33:54 AM)
     * @return String
     * @see #setDescAddr(String)
     */
    public String getDescAddr() {
    	return descAddr;
    }
    /**
     * Getter method for lpStatus.
     * Creation date: (9/18/02 8:45:30 AM)
     * @return java.lang.String
     */
    public String getLpStatus() {
    	return lpStatus;
    }
    /**
     * Gets the SAG Type.
     * Creation date: (1/9/02 11:31:28 AM)
     * @return SAGType
     * @see #setSagType(SAGType)
     */
    public BRMSSagInfo [] getSagInfo() 
    {
    	return sagResp;
    }
    /**
     * Getter method for the boolean stNmInd.
     * Creation date: (8/10/01 8:11:51 AM)
     * @return boolean
     * @see #setStNmInd(boolean)
     */
    public boolean getStNmInd() {
    	return stNmInd;
    }
    /**
     * Gets the wire center.
     * Creation date: (1/3/02 2:19:44 PM)
     * @return String
     * @see #setWireCenter(String)
     */
    public java.lang.String getWireCenter() {
    	return wireCenter;
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
    		ahBrmsAddr.getHousNbr() +
    		ahBrmsAddr.getHousNbrSfx() +
    		ahBrmsAddr.getAasgndHousNbr() +
    		ahBrmsAddr.getStreetName() +
    		ahBrmsAddr.getRoute() +
    		ahBrmsAddr.getCity() +
    		ahBrmsAddr.getState() +
    		ahBrmsAddr.getPostalCode() +
    		getCircuitId() +
    		getLpStatus() +
    		getDescAddr()
    		);
    }
    /**
     * Return true if the line status (LpStatus) is "working".
     * Creation date: (4/27/01 10:30:55 AM)
     * @return boolean
     */
    protected boolean lineIsWorking()
    {
    	if (getLpStatus() != null)
    		if ((getLpStatus().equalsIgnoreCase(BRMSTag.WORKING_STATUS)) ||
    			(getLpStatus().equalsIgnoreCase(BRMSTag.R_WORKING_STATUS)))
    			return true;
    	return false;
    }
 
    /**
     * Return true if Unit value is populated.
     * Creation date: (10/2/02 12:15:52 PM)
     * @return boolean
     */
    protected boolean isUnitPopulated() 
    {
        if (ahBrmsAddr.getStructType().equals("") &&
            ahBrmsAddr.getStructValue().equals("") &&
            ahBrmsAddr.getLevelType().equals("") &&
            ahBrmsAddr.getLevelValue().equals("") &&
            ahBrmsAddr.getUnitType().equals("") &&
            ahBrmsAddr.getUnitValue().equals(""))
            return false;
        
        return true;
    }
 
    /**
     * Sets the assigned house number.
     * Creation date: (4/12/02 10:24:22 AM)
     * @param newAasgndHousNbr String
     * @see #getAasgndHousNbr
     */
    public void setAasgndHousNbr(java.lang.String newAasgndHousNbr) {
    	aasgndHousNbr = newAasgndHousNbr;
    }
    /**
     * Construct and store an AddressHandler from the BRMS Hit data.
     * Creation date: (3/7/01 10:02:01 AM)
     */
    protected AddressHandlerBRMS getServiceAddress()
    {
    
    	String addrInfo = "";
    
    	if (sagResp[0].zipCode.trim().equals("") || sagResp[0].zipCode.trim().equals("00000"))
    		addrInfo = BRMSTag.BLANK_ZIPCODE;
    	else
    	{
    		if (getAddrCount() > 1)
    			addrInfo = BRMSTag.SUPPLEMENTAL_ADDRESS_MSG;
    		else
    			addrInfo = getDescAddr().trim();
    	}
    		 
    	return (new AddressHandlerBRMS(
    		ahBrmsAddr.getRoute(),			// aRoute
    		ahBrmsAddr.getBox(),				// aBox,
    		ahBrmsAddr.getHousNbrPfx(),		// aHouseNumberPrefix
    		ahBrmsAddr.getHousNbr(),			// aHouseNumber
    		this.getAasgndHousNbr(),		// aAssignedHouseNumber
    		ahBrmsAddr.getHousNbrSfx(),    	// aHouseNumberSuffix
    		ahBrmsAddr.getStDir(),			// aStreetDirection
    		ahBrmsAddr.getStName(),			// aStreetName
    		ahBrmsAddr.getStThorofare(),		// aStreetThoroughfare
    		ahBrmsAddr.getStNameSfx(),		// aStreetNameSuffix
    		ahBrmsAddr.getCity(),				// aCity
    		ahBrmsAddr.getState(),			// aState
    		sagResp[0].zipCode.trim(), 		// aPostalCode	rz ???
    		ahBrmsAddr.getCounty(),			// aCounty
    		ahBrmsAddr.getCountry(),			// aCountry
            ahBrmsAddr.getStructType(),       // aStructure Type
            ahBrmsAddr.getStructValue(),      // aStructure Type
            ahBrmsAddr.getLevelType(),        // aLevel Type
            ahBrmsAddr.getLevelValue(),       // aLevel Value
            ahBrmsAddr.getUnitType(),         // aUnit Type
            ahBrmsAddr.getUnitValue(),        // aUnit Value
    		addrInfo,                       // aAdditionalAddressInformation
            getStNmInd()));					// aStNmInd
    }
    /**
     * Sets the AddressHandlerBRMS object.
     * Creation date: (1/7/02 1:39:19 PM)
     * @param newBrmsAddr AddressHandlerBRMS
     * @see #getBrmsAddr
     */
    public void setBrmsAddr(com.sbc.eia.idl.lim.helpers.AddressHandlerBRMS newBrmsAddr) {
    	ahBrmsAddr = newBrmsAddr;
    }
    /**
     * Setter method for circuitId.
     * Creation date: (9/18/02 8:44:31 AM)
     * @param newCircuitId java.lang.String
     */
    public void setCircuitId(String newCircuitId) {
    	if (newCircuitId != null)
    		circuitId = newCircuitId.replace('.','-');
    }
    /**
     * Setter for Descriptive Address of BrmsHitResp class.
     * Creation date: (8/21/01 6:33:54 AM)
     * @param newDescAddr String
     * @see #getDescAddr
     */
    public void setDescAddr(String newDescAddr) {
    	descAddr = newDescAddr;
    }
    /**
     * Setter method for lpStatus.
     * Creation date: (9/18/02 8:45:30 AM)
     * @param newLpStatus java.lang.String
     */
    public void setLpStatus(String newLpStatus) {
    	lpStatus = newLpStatus;
    }
    /**
     * Sets the SAG type.
     * Creation date: (1/9/02 11:31:28 AM)
     * @param newSagType SAGType
     * @see #getSagType
     */
    public void setSagResp (BRMSSagInfo [] newSagResp) {
    	sagResp = newSagResp;
    }
    /**
     * Setter method for the boolean stNmInd.
     * Creation date: (8/10/01 8:11:51 AM)
     * @param newStNmInd boolean
     * @see #getStNmInd
     */
    public void setStNmInd(boolean newStNmInd) {
    	stNmInd = newStNmInd;
    }
    /**
     * Sets the wire center.
     * Creation date: (1/3/02 2:19:44 PM)
     * @param newWireCenter String
     * @see #getWireCenter
     */
    public void setWireCenter(java.lang.String newWireCenter) {
    	wireCenter = newWireCenter;
    }
    /**
     * Returns a String that represents the value of this object.
     * @return a string representation of the receiver
     */
    public String toString()
    {
        final String nl = System.getProperty("line.separator","\n");
        StringBuffer sb = new StringBuffer(nl + "BrmsHit[ ");
        try{
            sb.append(new AddressHandler(addressData.aServiceAddress.theValue()).toString());
        }
        catch (AddressHandlerException e) {}
        catch (org.omg.CORBA.BAD_OPERATION e) {}
        catch (java.lang.NullPointerException e) {}
        
        try { sb.append(nl + "ExchangeCode[" + addressData.aExchangeCode.theValue() + "]"); }
        catch (org.omg.CORBA.BAD_OPERATION e) {}
        catch (java.lang.NullPointerException e) {}

        try { sb.append(nl + "LataCode[" + addressData.aLataCode.theValue() + "]"); }
        catch (org.omg.CORBA.BAD_OPERATION e) {}
        catch (java.lang.NullPointerException e) {}

        try { sb.append(nl + "LocalProviderServingOfficeCode[" + addressData.aLocalProviderServingOfficeCode.theValue() + "]"); }
        catch (org.omg.CORBA.BAD_OPERATION e) {}
        catch (java.lang.NullPointerException e) {}
 
        try { sb.append(nl + "PrimaryNpaNxx[" + addressData.aPrimaryNpaNxx.theValue() + "]"); }
        catch (org.omg.CORBA.BAD_OPERATION e) {}
        catch (java.lang.NullPointerException e) {}

        try { sb.append(nl + "SagWireCenter[" + addressData.aSagWireCenter.theValue() + "]"); }
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
     * Getter for addrCount.
     * Creation date: (10/3/02 5:43:28 AM)
     * @return int
     */
    public int getAddrCount() {
    	return addrCount;
    }
    /**
     * Setter for addrCount.
     * Creation date: (10/3/02 5:43:28 AM)
     * @param newAddrCount int
     */
    public void setAddrCount(int newAddrCount) {
    	addrCount = newAddrCount;
    }
}
