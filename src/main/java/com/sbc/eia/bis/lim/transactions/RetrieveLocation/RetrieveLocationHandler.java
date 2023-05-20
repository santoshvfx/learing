// $Id:$
package com.sbc.eia.bis.lim.transactions.RetrieveLocation;

import java.io.*;
import java.util.Vector;
import java.util.Properties;

import com.sbc.bccs.utilities.Logger;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.bis.lim.transactions.RetrieveLocation.RetrieveLocReq;
import com.sbc.eia.bis.lim.transactions.RetrieveLocation.ProviderLocationPropertyBlob;

import com.sbc.bccs.idl.helpers.IDLUtil;
import com.sbc.eia.bis.lim.util.LIMIDLUtil;
import com.sbc.eia.idl.lim.*;
import com.sbc.eia.idl.lim_types.*;
import com.sbc.eia.idl.types.*;
import com.sbc.eia.bis.lim.database.queries.LimAddressCache;

/**
 * @author RZ7367
 *
 * A class which serialize/deserialize ProviderLocationProperty class and calls the LimAddressCache class in order
 * to get or insert the stream of bytes.
 * Creation date: (07/22/04 12:00:00 PM)
 */
 
public class RetrieveLocationHandler implements java.io.Serializable
{	
	private Properties properties;
	private Logger logger;
	private RetrieveLocReq request = null;
	private LimAddressCache limAddressCache = null;

    /**
     * RetrieveLocationHandler constructor.
     */
    
    public RetrieveLocationHandler() 
    {
    	super();
    }
    
    public  RetrieveLocationHandler (Properties aProperty, Logger aLogger, RetrieveLocReq req  ) 
    throws RetrieveLocationException
    {
    	properties = aProperty;
    	logger = aLogger;
    	request = req;
    	try
    	{
    		limAddressCache = new LimAddressCache (properties, logger);
    	}
    	catch (Exception e)
    	{
    		throw new RetrieveLocationException (e.getMessage());
    	}
    }
    
    /**
     * Get a stream of bytes from the DataBase, deserialize it and call a method to Create AddressMatchResult 
     * Object from ProviderLocationPropertyBlob class
     * @return AddressMatchResult
     * @param locationId String
     * @param serviceId String
     */
    
    public AddressMatchResult getAddressMatchResult ( String locationId, String serviceId ) 
    throws RetrieveLocationException
    {
    	byte [] providerLocationPropertyByte = null;
    	//
    	// Get the stream of bytes from the database based on LocationId or Service Id
    	//
    	try
    	{
    		if (locationId != null && !locationId.equals(""))
    			providerLocationPropertyByte = limAddressCache.getBlobByLocationId (locationId);
    		else if (serviceId != null && !serviceId.equals(""))
    			providerLocationPropertyByte = limAddressCache.getBlobByServiceId (serviceId);
    		else
    			throw new RetrieveLocationException ("Location Id and Service Id are missing...");
    			
    		if (providerLocationPropertyByte == null)
    			return null;
    	}
    	catch (Exception e)
    	{
    		throw new RetrieveLocationException (e.getMessage());
    	}
    	
    	//
     	// Deserialize ProviderLocationPropertyBlob and creates ProviderLocationPropertyBlob Object.
		//    
    	ProviderLocationPropertyBlob providerLocationPropertyBlob = new ProviderLocationPropertyBlob ();
    	
    	try
    	{
    		ByteArrayInputStream byteInStream = new ByteArrayInputStream (providerLocationPropertyByte);
    		ObjectInputStream objectInStream = new ObjectInputStream (byteInStream);
    		providerLocationPropertyBlob = (ProviderLocationPropertyBlob) objectInStream.readObject ();
    		byteInStream.close();
    	}
    	catch (NotSerializableException e)
    	{
    		e.printStackTrace();	// rz remove
    		throw new RetrieveLocationException ("NotSerializableException caught while trying to deSerialize: " + e.getMessage());
    	}
    	catch (IOException e)
    	{
    		e.printStackTrace();	// rz remove
    		throw new RetrieveLocationException ("IOException caught while trying to deSerialize:: " + e.getMessage());
    	}
    	catch (ClassNotFoundException e)
    	{
    		e.printStackTrace();	// rz remove
    		throw new RetrieveLocationException ("ClassNotFoundException caught while trying to deSerialize:: " + e.getMessage());
    	} 
    	catch (Exception e)
    	{
    		e.printStackTrace();	// rz remove
    		throw new RetrieveLocationException ("Exception caught while trying to deSerialize:: " + e.getClass().getName() + e.getMessage());
    	} 
    	   		
    	try
    	{
    		return createAddressMatchResult (providerLocationPropertyBlob);
    	}
    	catch (Exception e)
    	{
    		return null;
    	}
    }
	 
    /**
     * Create AddressMatchResult Object from ProviderLocationPropertyBlob class.
     * @return AddressMatchResult
     * @param providerLocationPropertyBlob ProviderLocationPropertyBlob
     */
    
    private AddressMatchResult createAddressMatchResult (ProviderLocationPropertyBlob providerLocationPropertyBlob)
    throws RetrieveLocationException
    {
    	if (providerLocationPropertyBlob == null)
    		return null;	
    		
    	Address address = new Address ();	
    	if (providerLocationPropertyBlob.aServiceAddress != null)
    	{
    		String s [] = null;
    		FieldedAddress fa = new FieldedAddress (
    			IDLUtil.toOpt (providerLocationPropertyBlob.aServiceAddress.aRoute),
    			IDLUtil.toOpt (providerLocationPropertyBlob.aServiceAddress.aBox),
    			IDLUtil.toOpt (providerLocationPropertyBlob.aServiceAddress.aHouseNumberPrefix),
    			IDLUtil.toOpt (providerLocationPropertyBlob.aServiceAddress.aHouseNumber),
    			IDLUtil.toOpt (providerLocationPropertyBlob.aServiceAddress.aAssignedHouseNumber),
    			IDLUtil.toOpt (providerLocationPropertyBlob.aServiceAddress.aHouseNumberSuffix),
    			IDLUtil.toOpt (providerLocationPropertyBlob.aServiceAddress.aStreetDirection),
    			IDLUtil.toOpt (providerLocationPropertyBlob.aServiceAddress.aStreetName),
    			IDLUtil.toOpt (providerLocationPropertyBlob.aServiceAddress.aStreetThoroughfare),
    			IDLUtil.toOpt (providerLocationPropertyBlob.aServiceAddress.aStreetNameSuffix),
    			IDLUtil.toOpt (providerLocationPropertyBlob.aServiceAddress.aCity),
    			IDLUtil.toOpt (providerLocationPropertyBlob.aServiceAddress.aState),
    			IDLUtil.toOpt (providerLocationPropertyBlob.aServiceAddress.aPostalCode),
    			IDLUtil.toOpt (providerLocationPropertyBlob.aServiceAddress.aPostalCodePlus4),
    			IDLUtil.toOpt (providerLocationPropertyBlob.aServiceAddress.aCounty),
    			IDLUtil.toOpt (providerLocationPropertyBlob.aServiceAddress.aCountry),
    			IDLUtil.toOpt (providerLocationPropertyBlob.aServiceAddress.aStructureType),
    			IDLUtil.toOpt (providerLocationPropertyBlob.aServiceAddress.aStructureValue),
    			IDLUtil.toOpt (providerLocationPropertyBlob.aServiceAddress.aLevelType),
    			IDLUtil.toOpt (providerLocationPropertyBlob.aServiceAddress.aLevelValue),
    			IDLUtil.toOpt (providerLocationPropertyBlob.aServiceAddress.aUnitType),
    			IDLUtil.toOpt (providerLocationPropertyBlob.aServiceAddress.aUnitValue),
    			IDLUtil.toOpt (providerLocationPropertyBlob.aServiceAddress.aOriginalStreetDirection),
    			IDLUtil.toOpt (providerLocationPropertyBlob.aServiceAddress.aOriginalStreetNameSuffix),
    			IDLUtil.toOpt (s),
    			IDLUtil.toOpt (s),
    			IDLUtil.toOpt (""),
    			IDLUtil.toOpt (providerLocationPropertyBlob.aServiceAddress.aAdditionalInfo),
    			IDLUtil.toOpt (""),  // gp6383
    			IDLUtil.toOpt (""),				 
		        IDLUtil.toOpt (""),				
		        IDLUtil.toOpt (""),			
		        IDLUtil.toOpt (""),				
		        IDLUtil.toOpt (""),				
		        IDLUtil.toOpt (""));		
    			
    			address.aFieldedAddress(fa);
    	}
    	else
    		throw new  RetrieveLocationException ("Address is empty");
    		
    	ProviderLocationPropertyBuilder propertyBuilder = new ProviderLocationPropertyBuilder (request.getLocationPropertiesRequested());
    	
  		propertyBuilder.setProviderName (providerLocationPropertyBlob.aProviderName); 
  		propertyBuilder.setServiceAddress (address);
  		propertyBuilder.setCentralOfficeCode  (providerLocationPropertyBlob.aCentralOfficeCode);
		propertyBuilder.setCommunityName  (providerLocationPropertyBlob.aCommunityName);
  		propertyBuilder.setE911Exempt  (providerLocationPropertyBlob.aE911Exempt);
  		propertyBuilder.setE911NonRecurringCharge  (providerLocationPropertyBlob.aE911NonRecurringCharge);
  		propertyBuilder.setE911Surcharge  (providerLocationPropertyBlob.aE911Surcharge);
  		propertyBuilder.setExchangeCode  (providerLocationPropertyBlob.aExchangeCode);
  		propertyBuilder.setExco  (providerLocationPropertyBlob.aExco);
 		propertyBuilder.setLataCode  (providerLocationPropertyBlob.aLataCode);
  		propertyBuilder.setLataName  (providerLocationPropertyBlob.aLataName);
  		propertyBuilder.setLocalProviderName  (providerLocationPropertyBlob.aLocalProviderName);
  		propertyBuilder.setLocalProviderNumber  (providerLocationPropertyBlob.aLocalProviderNumber);
  		propertyBuilder.setLocalProviderServingOfficeCode  (providerLocationPropertyBlob.aLocalProviderServingOfficeCode);
  		propertyBuilder.setOwnedWiring  (providerLocationPropertyBlob.aOwnedWiring);
  		propertyBuilder.setPrimaryDirectoryCode  (providerLocationPropertyBlob.aPrimaryDirectoryCode);
  		propertyBuilder.setPrimaryNpaNxx  (providerLocationPropertyBlob.aPrimaryNpaNxx);
  		propertyBuilder.setQuickDialTone  (providerLocationPropertyBlob.aQuickDialTone);
  		propertyBuilder.setQuickDialToneNumber  (providerLocationPropertyBlob.aQuickDialToneNumber);
  		propertyBuilder.setRateZone  (providerLocationPropertyBlob.aRateZone);
  		propertyBuilder.setRateZoneBandCode  (providerLocationPropertyBlob.aRateZoneBandCode);
  		propertyBuilder.setSagNpa  (providerLocationPropertyBlob.aSagNpa);
  		propertyBuilder.setSagWireCenter  (providerLocationPropertyBlob.aSagWireCenter);
  		propertyBuilder.setSbcServingOfficeCode  (providerLocationPropertyBlob.aSbcServingOfficeCode);
  		propertyBuilder.setSbcServingOfficeWirecenter  (providerLocationPropertyBlob.aSbcServingOfficeWirecenter);
  		propertyBuilder.setServingAreaDescription  (providerLocationPropertyBlob.aServingAreaDescription);
  		propertyBuilder.setStreetAddressGuideArea  (providerLocationPropertyBlob.aStreetAddressGuideArea);
  		propertyBuilder.setSurcharge4Percent  (providerLocationPropertyBlob.aSurcharge4Percent);
  		propertyBuilder.setSurcharge16Percent  (providerLocationPropertyBlob.aSurcharge16Percent);
  		propertyBuilder.setTarCode  (providerLocationPropertyBlob.aTarCode);
  		propertyBuilder.setTelephoneNumber  (providerLocationPropertyBlob.aTelephoneNumber);
  		propertyBuilder.setWorkingServiceOnLocation  (providerLocationPropertyBlob.aWorkingServiceOnLocation);
  		
 		ProviderLocationProperty [] providerLocationPropertyArray = new ProviderLocationProperty[1];
		providerLocationPropertyArray[0] = propertyBuilder.getProviderLocationProperty();
		
		providerLocationPropertyArray[0].aExtensions = new ExtensionPropertySeqOpt ();
		providerLocationPropertyArray[0].aExtensions.__default();
		if (providerLocationPropertyBlob.aExtensions.length > 0)
		{
			ExtensionProperty [] ep = new ExtensionProperty [providerLocationPropertyBlob.aExtensions.length];
  			for (int ij = 0; ij < providerLocationPropertyBlob.aExtensions.length ; ij++)
  			{
  				ep[ij].aId = providerLocationPropertyBlob.aExtensions[ij].aId;
				ep[ij].aValue = providerLocationPropertyBlob.aExtensions[ij].aValue;
  			}
			providerLocationPropertyArray[0].aExtensions.theValue (ep);
		}	

    	Location loc = new Location ();
    	loc.aLocationId = IDLUtil.toOpt("");
    	loc.aProviderLocationProperties = providerLocationPropertyArray;
    	AddressMatchResult addressMatchResult = new AddressMatchResult ();
    	addressMatchResult.aLocation (loc);
     	return addressMatchResult;	
} 
    
    /**
     * Call a method to create ProviderLocationPropertyBlob class from input, call a method to create a stream
     * of bytes and call a method to insert into database.
     * @param locationId String
     * @param serviceId String
     * @param providerLocationProperty ProviderLocationProperty
     * @exception RetrieveLocationException
     */
    
    public void setProviderLocationPropertyByte (String locationId, String serviceId, Address serviceAddress, ProviderLocationProperty [] providerLocationProperty)
    throws RetrieveLocationException
    {	

		ProviderLocationPropertyBlob providerLocationPropertyBlob = new ProviderLocationPropertyBlob ();

    	try	
    	{
     		providerLocationPropertyBlob = createProviderLocationPropertyBlob (serviceAddress, providerLocationProperty);
    		byte [] providerLocationPropertyByte = getProviderLocationPropertyByte (providerLocationPropertyBlob);
    		String state = "";
  			try {
  				state = serviceAddress.aFieldedAddress().aState.theValue();
    		} catch (org.omg.CORBA.BAD_OPERATION e) {}
    		limAddressCache.insertLocDataBlob (
    					locationId, 
    					serviceId, 
    					state, 
    					providerLocationPropertyByte);
    	} 
    	catch (Exception e) 
    	{
    		e.printStackTrace();
    		throw new RetrieveLocationException (e.getMessage());
    	}
    }

    /**
     * Create ProviderLocationPropertyBlob class from ProviderLocationProperty Object.
     * @return ProviderLocationPropertyBlob
     * @param providerLocationPropert ProviderLocationPropert
     */
    
    private ProviderLocationPropertyBlob createProviderLocationPropertyBlob (Address addr, ProviderLocationProperty [] providerLocationProperty)
    throws RetrieveLocationException
    {
    	ProviderLocationPropertyBlob providerLocationPropertyBlob = new ProviderLocationPropertyBlob ();
    	ProviderLocationPropertyBlob.FieldedAddressBlob fieldedAddressBlob = providerLocationPropertyBlob.new FieldedAddressBlob ();
    	int extension_len = 0;
    	try {
    		extension_len = providerLocationProperty[0].aExtensions.theValue().length;
    	} catch (Exception e) {}
    	ProviderLocationPropertyBlob.ExtensionPropertyBlob [] extensionPropertyBlob = new ProviderLocationPropertyBlob.ExtensionPropertyBlob [extension_len];
     	
    	try
    	{
    		FieldedAddress fa = null;
    		try
    		{
    			fa = addr.aFieldedAddress();
    		}
    		catch (Exception e)
    		{
    			throw new RetrieveLocationException (e.getMessage());
    		}
    		try {
  				fieldedAddressBlob.aRoute = fa.aRoute.theValue ();
    		} catch (org.omg.CORBA.BAD_OPERATION e) {}
    		try {
  				fieldedAddressBlob.aBox = fa.aBox.theValue ();
    		} catch (org.omg.CORBA.BAD_OPERATION e) {}
  			try {
  				fieldedAddressBlob.aHouseNumberPrefix = fa.aHouseNumberPrefix.theValue ();
    		} catch (org.omg.CORBA.BAD_OPERATION e) {}
  			try {
  				fieldedAddressBlob.aHouseNumber = fa.aHouseNumber.theValue ();
    		} catch (org.omg.CORBA.BAD_OPERATION e) {}
  			try {
  				fieldedAddressBlob.aAssignedHouseNumber = fa.aAssignedHouseNumber.theValue ();
    		} catch (org.omg.CORBA.BAD_OPERATION e) {}
  			try {
  				fieldedAddressBlob.aHouseNumberSuffix = fa.aHouseNumberSuffix.theValue ();
    		} catch (org.omg.CORBA.BAD_OPERATION e) {}
  			try {
  				fieldedAddressBlob.aStreetDirection = fa.aStreetDirection.theValue ();
    		} catch (org.omg.CORBA.BAD_OPERATION e) {}
  			try {
  				fieldedAddressBlob.aStreetName = fa.aStreetName.theValue ();
    		} catch (org.omg.CORBA.BAD_OPERATION e) {}
  			try {
  				fieldedAddressBlob.aStreetThoroughfare = fa.aStreetThoroughfare.theValue ();
    		} catch (org.omg.CORBA.BAD_OPERATION e) {}
  			try {
  				fieldedAddressBlob.aStreetNameSuffix = fa.aStreetNameSuffix.theValue ();
    		} catch (org.omg.CORBA.BAD_OPERATION e) {}
  			try {
  				fieldedAddressBlob.aCity = fa.aCity.theValue ();
    		} catch (org.omg.CORBA.BAD_OPERATION e) {}
  			try {
  				fieldedAddressBlob.aState = fa.aState.theValue ();
    		} catch (org.omg.CORBA.BAD_OPERATION e) {}
  			try {
  				fieldedAddressBlob.aPostalCode = fa.aPostalCode.theValue ();
    		} catch (org.omg.CORBA.BAD_OPERATION e) {}
  			try {
  				fieldedAddressBlob.aPostalCodePlus4 = fa.aPostalCodePlus4.theValue ();
    		} catch (org.omg.CORBA.BAD_OPERATION e) {}
  			try {
  				fieldedAddressBlob.aCounty = fa.aCounty.theValue ();
    		} catch (org.omg.CORBA.BAD_OPERATION e) {}
  			try {
  				fieldedAddressBlob.aCountry = fa.aCountry.theValue ();
    		} catch (org.omg.CORBA.BAD_OPERATION e) {}
  			try {
  				fieldedAddressBlob.aStructureType = fa.aStructureType.theValue ();
    		} catch (org.omg.CORBA.BAD_OPERATION e) {}
  			try {
  				fieldedAddressBlob.aStructureValue = fa.aStructureValue.theValue ();
    		} catch (org.omg.CORBA.BAD_OPERATION e) {}
  			try {
  				fieldedAddressBlob.aLevelType = fa.aLevelType.theValue ();
    		} catch (org.omg.CORBA.BAD_OPERATION e) {}
  			try {
  				fieldedAddressBlob.aLevelValue = fa.aLevelValue.theValue ();
    		} catch (org.omg.CORBA.BAD_OPERATION e) {}
  			try {
  				fieldedAddressBlob.aUnitType = fa.aUnitType.theValue ();
    		} catch (org.omg.CORBA.BAD_OPERATION e) {}
  			try {
  				fieldedAddressBlob.aUnitValue = fa.aUnitValue.theValue ();
    		} catch (org.omg.CORBA.BAD_OPERATION e) {}
  			try {
  				fieldedAddressBlob.aOriginalStreetDirection = fa.aOriginalStreetDirection.theValue ();
    		} catch (org.omg.CORBA.BAD_OPERATION e) {}
  			try {
  				fieldedAddressBlob.aOriginalStreetNameSuffix = fa.aOriginalStreetNameSuffix.theValue ();
    		} catch (org.omg.CORBA.BAD_OPERATION e) {}
   			try {
  				fieldedAddressBlob.aAdditionalInfo = fa.aAdditionalInfo.theValue ();
    		} catch (org.omg.CORBA.BAD_OPERATION e) {}

    		if (extension_len > 0)
    		{
    			ExtensionProperty [] ep = providerLocationProperty[0].aExtensions.theValue();
    			for (int ij = 0; ij < extension_len; ij++)
    			{
    				extensionPropertyBlob[ij] = providerLocationPropertyBlob.new ExtensionPropertyBlob ();
    				extensionPropertyBlob[ij].aId = ep[ij].aId;
    				extensionPropertyBlob[ij].aValue = ep[ij].aValue;
    			} 
    		}
    		
    		try {
  				providerLocationPropertyBlob.aProviderName = providerLocationProperty[0].aProviderName.theValue();
    		} catch (org.omg.CORBA.BAD_OPERATION e) {}
    		 
   			ProviderLocationPropertyBlob.FieldedAddressBlob aServiceAddress = fieldedAddressBlob;
   			providerLocationPropertyBlob.aServiceAddress = aServiceAddress;
   			providerLocationPropertyBlob.aExtensions = extensionPropertyBlob;
   			
  			try {
  				providerLocationPropertyBlob.aCentralOfficeCode = providerLocationProperty[0].aCentralOfficeCode.theValue();
    		} catch (org.omg.CORBA.BAD_OPERATION e) {}
  			try {
				providerLocationPropertyBlob.aCommunityName = providerLocationProperty[0].aCommunityName.theValue();
    		} catch (org.omg.CORBA.BAD_OPERATION e) {}
  			try {
  				providerLocationPropertyBlob.aE911Exempt = providerLocationProperty[0].aE911Exempt.theValue();
    		} catch (org.omg.CORBA.BAD_OPERATION e) {}
  			try {
  				providerLocationPropertyBlob.aE911NonRecurringCharge = providerLocationProperty[0].aE911NonRecurringCharge.theValue();
    		} catch (org.omg.CORBA.BAD_OPERATION e) {}
  			try {
  				providerLocationPropertyBlob.aE911Surcharge = providerLocationProperty[0].aE911Surcharge.theValue();
    		} catch (org.omg.CORBA.BAD_OPERATION e) {}
  			try {
  				providerLocationPropertyBlob.aExchangeCode = providerLocationProperty[0].aExchangeCode.theValue();
    		} catch (org.omg.CORBA.BAD_OPERATION e) {}
  			try {
  				providerLocationPropertyBlob.aExco = providerLocationProperty[0].aExco.theValue();
    		} catch (org.omg.CORBA.BAD_OPERATION e) {}
  			try {
  				providerLocationPropertyBlob.aLataCode = providerLocationProperty[0].aLataCode.theValue();
    		} catch (org.omg.CORBA.BAD_OPERATION e) {}
  			try {
  				providerLocationPropertyBlob.aLataName = providerLocationProperty[0].aLataName.theValue();
    		} catch (org.omg.CORBA.BAD_OPERATION e) {}
  			try {
  				providerLocationPropertyBlob.aLocalProviderName = providerLocationProperty[0].aLocalProviderName.theValue();
    		} catch (org.omg.CORBA.BAD_OPERATION e) {}
  			try {
  				providerLocationPropertyBlob.aLocalProviderNumber = providerLocationProperty[0].aLocalProviderNumber.theValue();
    		} catch (org.omg.CORBA.BAD_OPERATION e) {}
  			try {
  				providerLocationPropertyBlob.aLocalProviderServingOfficeCode = providerLocationProperty[0].aLocalProviderServingOfficeCode.theValue();
    		} catch (org.omg.CORBA.BAD_OPERATION e) {}
  			try {
  				providerLocationPropertyBlob.aOwnedWiring = providerLocationProperty[0].aOwnedWiring.theValue();
    		} catch (org.omg.CORBA.BAD_OPERATION e) {}
  			try {
  				providerLocationPropertyBlob.aPrimaryDirectoryCode = providerLocationProperty[0].aPrimaryDirectoryCode.theValue();
    		} catch (org.omg.CORBA.BAD_OPERATION e) {}
  			try {
  				providerLocationPropertyBlob.aPrimaryNpaNxx = providerLocationProperty[0].aPrimaryNpaNxx.theValue();
    		} catch (org.omg.CORBA.BAD_OPERATION e) {}
  			try {
  				providerLocationPropertyBlob.aQuickDialTone = providerLocationProperty[0].aQuickDialTone.theValue();
    		} catch (org.omg.CORBA.BAD_OPERATION e) {}
  			try {
  				providerLocationPropertyBlob.aQuickDialToneNumber = providerLocationProperty[0].aQuickDialToneNumber.theValue();
    		} catch (org.omg.CORBA.BAD_OPERATION e) {}
  			try {
  				providerLocationPropertyBlob.aRateZone = providerLocationProperty[0].aRateZone.theValue();
    		} catch (org.omg.CORBA.BAD_OPERATION e) {}
  			try {
  				providerLocationPropertyBlob.aRateZoneBandCode = providerLocationProperty[0].aRateZoneBandCode.theValue();
    		} catch (org.omg.CORBA.BAD_OPERATION e) {}
  			try {
  				providerLocationPropertyBlob.aSagNpa = providerLocationProperty[0].aSagNpa.theValue();
    		} catch (org.omg.CORBA.BAD_OPERATION e) {}
  			try {
  				providerLocationPropertyBlob.aSagWireCenter = providerLocationProperty[0].aSagWireCenter.theValue();
    		} catch (org.omg.CORBA.BAD_OPERATION e) {}
  			try {
  				providerLocationPropertyBlob.aSbcServingOfficeCode = providerLocationProperty[0].aSbcServingOfficeCode.theValue();
    		} catch (org.omg.CORBA.BAD_OPERATION e) {}
  			try {
  				providerLocationPropertyBlob.aSbcServingOfficeWirecenter = providerLocationProperty[0].aSbcServingOfficeWirecenter.theValue();
    		} catch (org.omg.CORBA.BAD_OPERATION e) {}
  			try {
  				providerLocationPropertyBlob.aServingAreaDescription = providerLocationProperty[0].aServingAreaDescription.theValue();
    		} catch (org.omg.CORBA.BAD_OPERATION e) {}
  			try {
  				providerLocationPropertyBlob.aStreetAddressGuideArea = providerLocationProperty[0].aStreetAddressGuideArea.theValue();
    		} catch (org.omg.CORBA.BAD_OPERATION e) {}
  			try {
  				providerLocationPropertyBlob.aSurcharge4Percent = providerLocationProperty[0].aSurcharge4Percent.theValue();
    		} catch (org.omg.CORBA.BAD_OPERATION e) {}
  			try {
  				providerLocationPropertyBlob.aSurcharge16Percent = providerLocationProperty[0].aSurcharge16Percent.theValue();
    		} catch (org.omg.CORBA.BAD_OPERATION e) {}
 			try {
  				providerLocationPropertyBlob.aTarCode = providerLocationProperty[0].aTarCode.theValue();
    		} catch (org.omg.CORBA.BAD_OPERATION e) {}
  			try {
  				providerLocationPropertyBlob.aTelephoneNumber = providerLocationProperty[0].aTelephoneNumber.theValue();
    		} catch (org.omg.CORBA.BAD_OPERATION e) {}
  			try {
  				providerLocationPropertyBlob.aWorkingServiceOnLocation = providerLocationProperty[0].aWorkingServiceOnLocation.theValue();
    		} catch (org.omg.CORBA.BAD_OPERATION e) {}
    	} // end try
    	catch (Exception e)
    	{
    		e.getMessage();
    	}
    	return providerLocationPropertyBlob;
    }
    	
    /**
     * Serializes ProviderLocationPropertyBlob class to a stream of bytes.
     * @return byte[]
     * @param providerLocationPropertyBlob ProviderLocationPropertyBlob
     * @throws RetrieveLocationException
     */
     
    public byte [] getProviderLocationPropertyByte (ProviderLocationPropertyBlob providerLocPropBlob)
    throws RetrieveLocationException
    {
    	byte [] byteOut = null;
    	
    	if (!providerLocPropBlob.getClass().getName ().equals ("com.sbc.eia.bis.lim.transactions.RetrieveLocation.ProviderLocationPropertyBlob"))
    		throw new RetrieveLocationException ("Object is not com.sbc.eia.bis.lim.transactions.RetrieveLocation.ProviderLocationPropertyBlob, Object will not be serialized.");
    		
    	try
    	{
    		ByteArrayOutputStream byteOutStream = new ByteArrayOutputStream ();
    		ObjectOutputStream objectOutStream = new ObjectOutputStream (byteOutStream);
    		objectOutStream.writeObject (providerLocPropBlob);
    		byteOut = new byte [byteOutStream.size()];
    		byteOut = byteOutStream.toByteArray ();
    		byteOutStream.close();
    	}
    	catch (NotSerializableException e)
    	{
    		System.err.println (e);
    		throw new RetrieveLocationException ("NotSerializableException caught while trying to Serialize: " + e.getMessage());
    	}
    	catch (IOException e)
    	{
    		System.err.println (e);
    		throw new RetrieveLocationException ("IOException caught while trying to Serialize: " + e.getMessage());
    	}
    	catch (Exception e)
    	{
    		System.err.println (e);
    		throw new RetrieveLocationException ("Exception caught while trying to Serialize: " + e.getMessage());
    	} 
    	return byteOut;
    }
 
     /**
     * Connect to the DataBase,
     */
    
	public void connect() throws RetrieveLocationException {
		try {
			limAddressCache.connect();
		} catch (Exception e) {
			throw new RetrieveLocationException(
				"Exception caught while trying to connect to the DB: "
					+ e.getMessage());
		}
	}
       
    /**
     * Disconnect from the DataBase,
     */
    
	public void disconnect() {
		try {
			limAddressCache.disconnect();
		} catch (Exception e) {
			// No need to worry
		}
	}

    /**
     * This main is for testing.
     * @param args String[]
     */
    
    public static void main (String args[])
    {
    	System.out.println("\nAvailable Options are -");
    	System.out.println("1 - Print Serial UID");
    	System.out.println("2 - Serialize and DeSerialize");
    	System.out.println("3 - Write to a Flat File");
    	System.out.println("4 - Read from a Flat File");
    	System.out.println("0 - Quit");
    	
    	int val;	
    	try
    	{	
    		val = System.in.read () - 48;
    		System.out.println("Option selected = <" + val + ">...");
    	}
    	catch (IOException e)
    	{
    		System.out.println("Exception = " + e.getMessage());
    		return;
    	}
    	
    	if (val == 0)
    		return;
    			
    	if (val == 1)
    	{
    	System.out.println ("");
    	System.out.println ("Print Serial UID for each class:");
    	System.out.println ("");
    
    	try
    	{
    	Class rlha = Class.forName ("com.sbc.eia.bis.lim.transactions.RetrieveLocation.ProviderLocationPropertyBlob");
    	ObjectStreamClass osc = ObjectStreamClass.lookup (rlha);
    	long svuid = osc.getSerialVersionUID();
    	System.out.println("ProviderLocationPropertyBlob - SerialVersionUID = " + svuid);
    	System.out.println ("");
    
    	Class rlhb = Class.forName ("com.sbc.eia.bis.lim.transactions.RetrieveLocation.ProviderLocationPropertyBlob$FieldedAddressBlob");
    	osc = ObjectStreamClass.lookup (rlhb);
    	svuid = osc.getSerialVersionUID();
    	System.out.println("ProviderLocationPropertyBlob$FieldedAddressBlob - SerialVersionUID = " + svuid);
    	System.out.println ("");

    	Class rlhc = Class.forName ("com.sbc.eia.bis.lim.transactions.RetrieveLocation.ProviderLocationPropertyBlob$ExtensionPropertyBlob");
    	osc = ObjectStreamClass.lookup (rlhc);
    	svuid = osc.getSerialVersionUID();
    	System.out.println("ProviderLocationPropertyBlob$$ExtensionPropertyBlob - SerialVersionUID = " + svuid);
    	System.out.println ("");

    	}
    	catch (Exception e)
    	{
    		e.printStackTrace();
    	}
    	} // end if (val == 1)

    }	
    

}
