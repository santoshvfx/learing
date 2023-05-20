package com.sbc.eia.idl.lim.helpers;

import java.util.StringTokenizer;
import com.sbc.eia.idl.lim_types.Address;
import com.sbc.eia.idl.types.StringSeqOpt;
import com.sbc.bccs.idl.helpers.IDLUtil;
import com.sbc.eia.idl.lim_types.UnfieldedAddress;

/**
 * AddressHandlerGranite is derived from AddressHandler.
 * Creation date: (6/2/05 12:09:01 PM)
 * @author: Rachel Zadok 
 */
public class AddressHandlerGranite extends AddressHandler
{
	private Address ufAddress = null;
	
    /**
     * AddressHandlerGranite constructor.
     */
    
     public AddressHandlerGranite() {
    	super();
    }
    
    /**
     * AddressHandlerGranite constructor.
     * @param address Address
     * @exception AddressHandlerException
     */
    
    public AddressHandlerGranite (Address address) 
    throws AddressHandlerException
    {
    	super (address);
    }
    
    /**
     * AddressHandlerGranite constructor.
     * @param addressLine Address
     * @param city String
     * @param state String
     * @param zip String
     * @exception AddressHandlerException
     */
    public AddressHandlerGranite (String addressLine, String city, String state, String zip, String zipPlus4, String county)
    throws AddressHandlerException
    {
    	super();
    	
  		StringSeqOpt aAddressLines;	
    	if (addressLine == null || addressLine.equals (""))
    	{
    		aAddressLines = new StringSeqOpt ();
    		aAddressLines.__default();
    	}
    	else
    	{	    		
    		String aAddressLineArray[] = new String [] { addressLine };
    		aAddressLines = (StringSeqOpt) IDLUtil.toOpt( aAddressLineArray );
    	}
    		
    	ufAddress = new Address ();
    	UnfieldedAddress ufa = new UnfieldedAddress(
    			aAddressLines,
    			IDLUtil.toOpt (city),
    			IDLUtil.toOpt (state),
    			IDLUtil.toOpt (zip), 
                IDLUtil.toOpt (zipPlus4),
    			IDLUtil.toOpt (county),
    			IDLUtil.toOpt (""),
                IDLUtil.toOpt (""),
                IDLUtil.toOpt (""),
                IDLUtil.toOpt (""),
                IDLUtil.toOpt (""),
                IDLUtil.toOpt (""),
                IDLUtil.toOpt (""),
    			IDLUtil.toOpt (""),
                IDLUtil.toOpt (""),
                IDLUtil.toOpt (""),
                IDLUtil.toOpt (""),
                IDLUtil.toOpt (""),
                IDLUtil.toOpt (""),
                IDLUtil.toOpt (""),
                IDLUtil.toOpt ("")
    		);
    		ufAddress.aUnfieldedAddress(ufa);
    }

    /**
     * AddressHandlerGranite constructor.
     * @param addressLine Address
     * @param city String
     * @param state String
     * @param zip String
     * @param zipPlus4 String
     * @param levelValue String
     * @param unitValue String
     * @exception AddressHandlerException
     */
    public AddressHandlerGranite (String addressLine, String city, String state, String zip, String zipPlus4, 
    	String levelValue, String unitValue)
    throws AddressHandlerException
    {
    	super();
    	
  		StringSeqOpt aAddressLines;	
    	if (addressLine == null || addressLine.equals (""))
    	{
    		aAddressLines = new StringSeqOpt ();
    		aAddressLines.__default();
    	}
    	else
    	{	    		
    		String aAddressLineArray[] = new String [] { addressLine };
    		aAddressLines = (StringSeqOpt) IDLUtil.toOpt( aAddressLineArray );
    	}
    		
    	ufAddress = new Address ();
    	UnfieldedAddress ufa = new UnfieldedAddress(
    			aAddressLines,
    			IDLUtil.toOpt (city),
    			IDLUtil.toOpt (state),
    			IDLUtil.toOpt (zip), 
                IDLUtil.toOpt (zipPlus4),
    			IDLUtil.toOpt (""),
    			IDLUtil.toOpt (""),
                IDLUtil.toOpt (""),
                IDLUtil.toOpt (""),
                IDLUtil.toOpt (""),
                IDLUtil.toOpt (levelValue),
                IDLUtil.toOpt (""),
                IDLUtil.toOpt (unitValue),
    			IDLUtil.toOpt (""),
                IDLUtil.toOpt (""),
                IDLUtil.toOpt (""),
                IDLUtil.toOpt (""),
                IDLUtil.toOpt (""),
                IDLUtil.toOpt (""),
                IDLUtil.toOpt (""),
                IDLUtil.toOpt ("")
    		);
    		ufAddress.aUnfieldedAddress(ufa);
    }

    /**
     * returns Address which if in unfielded format.
     * @return Address
     */
    public Address getUFAddress ()
    {
    	return ufAddress;	
    } 
    
}
