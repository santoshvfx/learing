//$Id: ServiceAddrRangeResp.java,v 1.2 2007/10/17 23:56:20 jh9785 Exp $

package com.sbc.eia.bis.lim.transactions.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ListIterator;

import com.sbc.bccs.idl.helpers.IDLUtil;
import com.sbc.eia.bis.lim.util.LIMBase;
import com.sbc.eia.bis.lim.util.LIMIDLUtil;
import com.sbc.eia.idl.lim.helpers.AddressHandler;
import com.sbc.eia.idl.lim_types.AlternativeServiceAddress;
import com.sbc.eia.idl.lim_types.RangedAddress;
import com.sbc.eia.idl.lim_types.ServiceLocation;

/**
 * Clone from AddrRangeResp.java. Represents a list of address ranges from a host response, but it has NO host-specific
 * knowledge.  This class is not abstract, but it is meant to be subclassed by a
 * host-specific class.  The subclass constructor has the
 * responsibility to load the rangeList collection.  Using this class alone would
 * result in an empty address-range list.
 * @author jh9785
 */
public class ServiceAddrRangeResp extends RetrieveLocResp implements ServiceAddressAlternativeAddressesResp
{
    final static String nl = System.getProperty("line.separator","\n");
	protected List rangeList = new ArrayList();
	protected boolean isOldSBCRegionFlag = true;
    
//	Instances of this internal class RangeData are stored in the rangeList.
	protected class RangeData
	{
		public String stDir = "";
		public String stName = "";
		public String stThorofare = "";
		public String stSfx = "";
		public String loHousNbrPfx = "";
		public String loHousNbr = "";
		public String loHousNbrSfx = "";
		public String hiHousNbrPfx = "";
		public String hiHousNbr = "";
		public String hiHousNbrSfx = "";
		public String cmty = "";
		public String state = "";
		public String zip = "";
        public String zip4 = "";
		public String county = "";
		public String country = "";
		public String addlInfo = "";
		//In ServiceLocation
		public String addressMatchCode = "";
		public String addressMatchCodeDescription = "";
		public String crossBoundaryState = "";

		public RangeData() {}
		public RangeData(String stDir,String stName,String stThorofare,String stSfx,
			String loHousNbrPfx, String loHousNbr,String loHousNbrSfx, 
			String hiHousNbrPfx, String hiHousNbr,String hiHousNbrSfx, 
			String cmty,String state,String zip, String zip4)
		{
			this(stDir,
				 stName,
				 stThorofare,
				 stSfx,
				 loHousNbrPfx,
				 loHousNbr,
				 loHousNbrSfx,
				 hiHousNbrPfx,
				 hiHousNbr,
				 hiHousNbrSfx,
				 cmty,
				 state,
				 zip,
                 zip4,
				 "", 
				 "",
				 "");
		}
		
		public RangeData(String stDir,String stName,String stThorofare,String stSfx,
			String loHousNbrPfx,String loHousNbr,String loHousNbrSfx,
			String hiHousNbrPfx,String hiHousNbr,String hiHousNbrSfx,
			String cmty,String state,String zip,String zip4,
            String county,String country,String addlInfo)
			
		{
		    this(stDir,
		         stName,
				 stThorofare,
				 stSfx,
				 loHousNbrPfx,
				 loHousNbr,
				 loHousNbrSfx,
				 hiHousNbrPfx,
				 hiHousNbr,
				 hiHousNbrSfx,
				 cmty,
				 state,
				 zip,
	             zip4,
	             county, 
	             country,
	             addlInfo,
	             "",
	             "",
	             "");
		}
		
		public RangeData(String stDir,String stName,String stThorofare,String stSfx,
				String loHousNbrPfx,String loHousNbr,String loHousNbrSfx,
				String hiHousNbrPfx,String hiHousNbr,String hiHousNbrSfx,
				String cmty,String state,String zip,String zip4,
	            String county,String country,String addlInfo,
	            String addressMatchCode,String addressMatchCodeDescription, 
	            String crossBoundaryState)
				
			{
				this.stDir 			= stDir;
				this.stName 		= stName;
				this.stThorofare 	= stThorofare;
				this.stSfx 			= stSfx;
				this.loHousNbrPfx 	= loHousNbrPfx;
				this.loHousNbr 		= loHousNbr;
				this.loHousNbrSfx 	= loHousNbrSfx;
				this.hiHousNbrPfx 	= hiHousNbrPfx;
				this.hiHousNbr 		= hiHousNbr;
				this.hiHousNbrSfx 	= hiHousNbrSfx;
				this.cmty 			= cmty;
				this.state 			= state;
				this.zip 			= zip;
	            this.zip4           = zip4;
				this.county 		= county;
				this.country 		= country;
				this.addlInfo 		= addlInfo;
				this.addressMatchCode = addressMatchCode;
				this.addressMatchCodeDescription = addressMatchCodeDescription;
				this.crossBoundaryState = crossBoundaryState;
			}

		
		public String toString()
		{
			StringBuffer sb = new StringBuffer();
			sb.append(nl + "AddressRange[ StDir=[" + stDir);
			sb.append("]" + nl + "StName=[" + stName);
			sb.append("]" + nl + "StThrfr=[" + stThorofare);
			sb.append("]" + nl + "StSuffix=[" + stSfx);
			sb.append("]" + nl + "LowHousNbrPrefix=[" + loHousNbrPfx);
			sb.append("]" + nl + "LowHousNbr=[" + loHousNbr);
			sb.append("]" + nl + "LowHousNbrSfx=[" + loHousNbrSfx);
			sb.append("]" + nl + "HighHousNbrPrefix=[" + hiHousNbrPfx);
			sb.append("]" + nl + "HighHousNbr=[" + hiHousNbr);
			sb.append("]" + nl + "HighHousNbrSfx=[" + hiHousNbrSfx);
			sb.append("]" + nl + "City=[" + cmty);
			sb.append("]" + nl + "State=[" + state);
			sb.append("]" + nl + "Zip=[" + zip);
            sb.append("]" + nl + "Zip4=[" + zip4);
			sb.append("]" + nl + "County=[" + county);
			sb.append("]" + nl + "Country=[" + country);
			sb.append("]" + nl + "AddlAddrInfo=[" + addlInfo);
			sb.append("]" + nl + "AddressMatchCode=[" + addressMatchCode);
			sb.append("]" + nl + "AddressMatchCodeDescription=[" + addressMatchCodeDescription);
			sb.append("]" + nl + "CrossBoundaryState=[" + crossBoundaryState);
			sb.append("] ]");
			return sb.toString();
		}

		public RangedAddress toRangedAddress()
		{
			return new RangedAddress(
				IDLUtil.toOpt(loHousNbr),
				IDLUtil.toOpt(hiHousNbr),
				IDLUtil.toOpt(loHousNbrPfx),
				IDLUtil.toOpt(hiHousNbrPfx),
				IDLUtil.toOpt(loHousNbrSfx),
				IDLUtil.toOpt(hiHousNbrSfx),
				IDLUtil.toOpt(stDir),
				IDLUtil.toOpt(stName),
				IDLUtil.toOpt(stThorofare),
				IDLUtil.toOpt(stSfx),
				IDLUtil.toOpt(cmty),
				IDLUtil.toOpt(state),
				IDLUtil.toOpt(zip),
                IDLUtil.toOpt(zip4),
				IDLUtil.toOpt(county),
				IDLUtil.toOpt(country),
				IDLUtil.toOpt(addlInfo)
			);
		}

		protected String hashString()
		{
			return (stDir +
					stName +
					stThorofare +
					stSfx +
					loHousNbrPfx +
					loHousNbr +
					loHousNbrSfx +
					hiHousNbrPfx +
					hiHousNbr +
					hiHousNbrSfx +
					cmty +
					state +
					zip +
                    zip4 +
					county +
					country +
					addlInfo +
					addressMatchCode +
					addressMatchCodeDescription +
					crossBoundaryState);
		}

		public boolean equals(Object o)
		{
			if (o instanceof RangeData)
			{
				return hashString().equals(((RangeData) o).hashString());
			}
			return false;
		}

		public int hashCode()
		{
			return hashString().hashCode();
		}
	}
	
	/**
     * ServiceAddrRangeResp constructor.
     * @param utility LIMBase
     */
    public ServiceAddrRangeResp(LIMBase utility) {
        super(utility);
        isOldSBCRegionFlag = true;
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
    	if (obj instanceof ServiceAddrRangeResp)
    	{
    		return rangeList.equals(((ServiceAddrRangeResp) obj).rangeList);
    	}
    	return false;
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
    	return rangeList.hashCode();
    }
    
    /**
     * Create an array of AlternativeServiceAddress from the internal list.  This method implements
     * the ServiceAddressAlternativeAddressesResp interface.
     * Creation date: (3/12/01 4:58:45 PM)
     * @return AlternativeServiceAddress[]
     */
    public AlternativeServiceAddress[] toAltAddr()
    {
    	// Sort the rangeList. The order of the sort: StreetName, PostalCode, 
    	// StreetThoroughfare, StreetDirection, StreetNameSuffix and City.
    	// For Southeast, RSAG doesn't need to sort.
        if (isOldSBCRegionFlag)
        {
            Collections.sort (rangeList, new Comparator () 
	        {
                public int compare (Object o1, Object o2)
                {
                    RangeData rd1 = (RangeData) o1;
                    RangeData rd2 = (RangeData) o2;
                    boolean cmpCity1 = false;
                    boolean cmpCity2 = false;
                    boolean cmpStNmSuf1 = false;
                    boolean cmpStNmSuf2 = false;
                    boolean cmpStDir1 = false;
                    boolean cmpStDir2 = false;
                    boolean cmpStThorofare1 = false;
                    boolean cmpStThorofare2 = false;
                    boolean cmpZip1 = false;
                    boolean cmpZip2 = false;
                    boolean cmpStName1 = false;
                    boolean cmpStName2 = false;
    
                    cmpCity1 = rd1.cmty.trim().equalsIgnoreCase(origReq.getCity());
                    cmpCity2 = rd2.cmty.trim().equalsIgnoreCase(origReq.getCity());
    
                    cmpStNmSuf1 = rd1.stSfx.trim().equalsIgnoreCase(origReq.getStNameSfx());
                    cmpStNmSuf2 = rd2.stSfx.trim().equalsIgnoreCase(origReq.getStNameSfx());
    						
                    cmpStDir1 = rd1.stDir.trim().equalsIgnoreCase(origReq.getStDir());
                    cmpStDir2 = rd2.stDir.trim().equalsIgnoreCase(origReq.getStDir());
    
                    cmpStThorofare1 = rd1.stThorofare.trim().equalsIgnoreCase(origReq.getStThorofare());
                    cmpStThorofare2 = rd2.stThorofare.trim().equalsIgnoreCase(origReq.getStThorofare());
    
                    cmpZip1 = rd1.zip.trim().equalsIgnoreCase(origReq.getPostalCode());
                    cmpZip2 = rd2.zip.trim().equalsIgnoreCase(origReq.getPostalCode());
    
                    cmpStName1 = rd1.stName.trim().equalsIgnoreCase(origReq.getStName());
                    cmpStName2 = rd2.stName.trim().equalsIgnoreCase(origReq.getStName());
    
                    // Weighted order	
                    if (cmpStName1 && !cmpStName2) 
                        return -1;
                    if (cmpStName2 && !cmpStName1) 
                        return 1;
    			
                    if (cmpZip1 && !cmpZip2) 
                        return -1;
                    if (cmpZip2 && !cmpZip1) 
                        return 1;
    				
                    if (cmpStThorofare1 && !cmpStThorofare2) 
                        return -1;
                    if (cmpStThorofare2 && !cmpStThorofare1) 
                        return 1;
    				
                    if (cmpStDir1 && !cmpStDir2) 
                        return -1;
                    if (cmpStDir2 && !cmpStDir1) 
                        return 1;
    				
                    if (cmpStNmSuf1 && !cmpStNmSuf2) 
                        return -1;
                    if (cmpStNmSuf2 && !cmpStNmSuf1) 
                        return 1;
    				
                    if (cmpCity1 && !cmpCity2) 
                        return -1;
                    if (cmpCity2 && !cmpCity1) 
                        return 1;
    
                    // Sort equal weighted values
                    int	cmp = 0;
                    if ((cmp = rd1.stName.trim().compareTo (rd2.stName.trim())) != 0)
                        return cmp;
                    if ((cmp = rd1.stDir.trim().compareTo (rd2.stDir.trim())) != 0)
                        return cmp;
                    if ((cmp = rd1.stThorofare.trim().compareTo (rd2.stThorofare.trim())) != 0)
                        return cmp;
                    if ((cmp = rd1.stSfx.trim().compareTo (rd2.stSfx.trim())) != 0)
                        return cmp;
                    if ((cmp = rd1.cmty.trim().compareTo (rd2.cmty.trim())) != 0)
                        return cmp;
                    if ((cmp = rd1.zip.trim().compareTo (rd2.zip.trim())) != 0)
                        return cmp;
                    if ((cmp = rd1.loHousNbrPfx.trim().compareTo (rd2.loHousNbrPfx.trim())) != 0)
                        return cmp;
                    if ((cmp = rd1.loHousNbr.trim().compareTo (rd2.loHousNbr.trim())) != 0)
                        return cmp;
    			
                    return 0;
                }
	        });
        }
    	ListIterator it = rangeList.listIterator();
    	int i = 0;
    	int maxAddr = 0;
    
    	if ((getMaxAddresses() != NO_SIZE_LIMIT) &&
    	    (getMaxAddresses() < rangeList.size()))
    		maxAddr = getMaxAddresses();
    	else
    		maxAddr = rangeList.size();
    	
    	AlternativeServiceAddress[] aa = new AlternativeServiceAddress[maxAddr];
    	while ((it.hasNext()) && 
    		   (i < maxAddr))
    	{
    		aa[i] = new AlternativeServiceAddress();
    		RangeData tempRangedata = (RangeData) it.next();
    		ServiceLocation tempServiceLocation = LIMIDLUtil.formatServiceLocation((tempRangedata).toRangedAddress());
    		if (!isOldSBCRegionFlag)
    		{
    		    if (tempRangedata.addressMatchCode != null && tempRangedata.addressMatchCode.length() > 0)
    		    {
    		        tempServiceLocation.aAddressMatchCode.theValue(tempRangedata.addressMatchCode);
    		    }
    		    if (tempRangedata.addressMatchCodeDescription != null && tempRangedata.addressMatchCodeDescription.length() > 0)
    		    {
    		        tempServiceLocation.aAddressMatchCodeDescription.theValue(tempRangedata.addressMatchCodeDescription);
    		    }
    		    if (tempRangedata.crossBoundaryState != null && tempRangedata.crossBoundaryState.length() > 0)
    		    {
    		        tempServiceLocation.aCrossBoundaryState.theValue(tempRangedata.crossBoundaryState);
    		    }
    		}
    		aa[i].aServiceLocation(tempServiceLocation);
    		tempServiceLocation = null;
    		i++;
    	}
    	
    	return aa;
    }
      
    /**
     * Returns a String that represents the value of this object.
     * @return a string representation of the receiver
     */
    public String toString()
    {
    	return rangeList.toString();
    }
    
    protected AddressHandler origReq = new AddressHandler ();
    
    /**
     * Setter method for origReq.
     * Creation date: (10/4/01 2:00:19 PM)
     * @param aOrigReq AddressHandler
     */
    public void setOrigReq(AddressHandler aOrigReq) {
    	origReq = aOrigReq;
    }
}
