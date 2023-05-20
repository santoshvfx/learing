// $Id: AddrListResp.java,v 1.3 2004/04/15 15:13:40 db4252 Exp $

package com.sbc.eia.bis.lim.transactions.RetrieveLocation;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import com.sbc.eia.bis.lim.util.LIMBase;
import com.sbc.eia.idl.lim.helpers.AddressHandler;
import com.sbc.eia.idl.lim_types.Address;
import com.sbc.eia.idl.lim_types.AlternativeAddress;
import com.sbc.eia.idl.lim_types.Location;
import com.sbc.eia.idl.lim_types.RangedAddress;

/**
 * Represents a list of addresses from a host response, but it has NO host-specific
 * knowledge.  This class is not abstract, but it is meant to be
 * subclassed by a host-specific class.  The subclass constructor has the
 * responsibility to load the addrList collection.  Using this class alone would
 * result in an empty address list.
 *
 * Creation date: (4/21/04 8:10:44 AM)
 * @author: David Brawley
 */
public class AddrAltResp extends RetrieveLocResp implements AlternativeAddressesResp
{
	protected List addrList = new ArrayList();
    protected class Unit 
    {
        protected String structTag = "";
        protected String structVal = "";
        protected String levelTag  = "";
        protected String levelVal  = "";
        protected String unitTag   = "";
        protected String unitVal   = "";       

        public Unit()
        {
        }

        public Unit(String aStructTag, String aStructVal, String aLevelTag, String aLevelVal, String aUnitTag, String aUnitVal)
        {
           setStructTag(aStructTag);
           setStructVal(aStructVal);
           setLevelTag(aLevelTag);
           setLevelVal(aLevelVal);
           setUnitTag(aUnitTag);
           setUnitVal(aUnitVal); 
        }

        /**
         * Returns the levelTag.
         * @return String
         */
        public String getLevelTag()
        {
            return levelTag;
        }

        /**
         * Returns the levelVal.
         * @return String
         */
        public String getLevelVal()
        {
            return levelVal;
        }

        /**
         * Returns the structTag.
         * @return String
         */
        public String getStructTag()
        {
            return structTag;
        }

        /**
         * Returns the structVal.
         * @return String
         */
        public String getStructVal()
        {
            return structVal;
        }

        /**
         * Returns the unitTag.
         * @return String
         */
        public String getUnitTag()
        {
            return unitTag;
        }

        /**
         * Returns the unitVal.
         * @return String
         */
        public String getUnitVal()
        {
            return unitVal;
        }

        /**
         * Sets the levelTag.
         * @param levelTag The levelTag to set
         */
        public void setLevelTag(String levelTag)
        {
            this.levelTag = levelTag;
        }

        /**
         * Sets the levelVal.
         * @param levelVal The levelVal to set
         */
        public void setLevelVal(String levelVal)
        {
            this.levelVal = levelVal;
        }

        /**
         * Sets the structTag.
         * @param structTag The structTag to set
         */
        public void setStructTag(String structTag)
        {
            this.structTag = structTag;
        }

        /**
         * Sets the structVal.
         * @param structVal The structVal to set
         */
        public void setStructVal(String structVal)
        {
            this.structVal = structVal;
        }

        /**
         * Sets the unitTag.
         * @param unitTag The unitTag to set
         */
        public void setUnitTag(String unitTag)
        {
            this.unitTag = unitTag;
        }

        /**
         * Sets the unitVal.
         * @param unitVal The unitVal to set
         */
        public void setUnitVal(String unitVal)
        {
            this.unitVal = unitVal;
        }

        /**
         * Returns the string representation of the Unit.
         * @return String
         */

        public String toString()
        {
          return (new String(((structTag + " " + 
                               structVal).trim() + " " +
                              (levelTag + " " +
                               levelVal).trim() + " " +
                              (unitTag + " " +
                               unitVal).trim())).trim());
        }

    }; 

    /**
     * Construct an empty AddrListResp.
     * @param utility LIMBase
     */
    public AddrAltResp(LIMBase utility)
    {
    	super(utility);
    }
    /**
     * Compares two objects for equality. Returns a boolean that indicates
     * whether this object is equivalent to the specified object. This method
     * is used when an object is stored in a hashtable.
     * @param obj the Object to compare with
     * @return true if these Objects are equal; false otherwise.
     * @see java.util.Hashtable
     */
    public boolean equals(Object obj)
    {
    	if (obj instanceof AddrAltResp)
    	{
    		return addrList.equals(((AddrAltResp) obj).addrList);
    	}
    	return false;
    }
    /**
     * Generates a hash code for the receiver.
     * This method is supported primarily for
     * hash tables, such as those provided in java.util.
     * @return an integer hash code for the receiver
     * @see java.util.Hashtable
     */
    public int hashCode()
    {
    	return addrList.hashCode();
    }
    
    /**
     * Create an array of AlternativeAddresses from the internal list.  This method implements
     * the AlternativeAddressesResp interface.
     * Creation date: (4/21/04 9:01:52 AM)
     * @return AlternativeAddress[]
     */
    public AlternativeAddress[] toAltAddr()
    {
    	AlternativeAddress[] aa = new AlternativeAddress[addrList.size()];
    	
    	ListIterator it = addrList.listIterator();
    	int i = 0;
    
    	while (it.hasNext())
    	{
    		Object obj = it.next();

            aa[i] = new AlternativeAddress();
            if (obj instanceof Address)
                aa[i].aAddress((Address) obj);
            if (obj instanceof Location)
                aa[i].aLocation((Location) obj);
            if (obj instanceof RangedAddress)
                aa[i].aRangedAddress((RangedAddress) obj);
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
    	if (addrList == null ) return "AddrAltResp size=0";
    	return "AddrAltResp size=" + addrList.size();
    }
}