// $Id:$
package com.sbc.eia.bis.lim.transactions.RetrieveLocation;
 
/**
 * @author RZ7367
 *
 * This class contains the elements that we need to serialize in order to keep them as a BLOB in the DB.
 * All the elements that are in the IDL but are not used for the in-region transactions have been commented, 
 * rather than being removed. (so we now exactly what elements has not been included.)
 * Creation date: (07/22/04 3:53:07 PM)
 */
public class ProviderLocationPropertyBlob implements java.io.Serializable
{
	static final long serialVersionUID = 6081643455113727014L; 
  	String aProviderName = null;
  	String aCentralOfficeCode = null;
	String aCommunityName = null;
  	String aE911Exempt = null;
  	String aE911NonRecurringCharge = null;
  	String aE911Surcharge = null;
  	String aExchangeCode = null;
  	String aExco = null;
  	String aLataCode = null;
  	String aLataName = null;
  	String aLocalProviderName = null;
  	String aLocalProviderNumber = null;
  	String aLocalProviderServingOfficeCode = null;
  	String aOwnedWiring = null;
  	String aPrimaryDirectoryCode = null;
  	String aPrimaryNpaNxx = null;
  	String aQuickDialTone = null;
  	String aQuickDialToneNumber = null;
  	String aRateZone = null;
  	String aRateZoneBandCode = null;
  	String aSagNpa = null;
  	String aSagWireCenter = null;
   	String aSbcServingOfficeCode = null;
  	String aSbcServingOfficeWirecenter = null;
  	String aServingAreaDescription = null;
  	String aStreetAddressGuideArea = null;
  	String aSurcharge4Percent = null;
  	String aSurcharge16Percent = null;
  	String aTarCode = null;
  	String aTelephoneNumber = null;
  	String aWorkingServiceOnLocation = null;
  	
	class ExtensionPropertyBlob implements java.io.Serializable
	{
		static final long serialVersionUID = -8687513089359985119L;
  		String aId = null;
  		String aValue = null;
	};
	
	class FieldedAddressBlob implements java.io.Serializable
	{
		static final long serialVersionUID = 5143627829906515363L;
  		String aRoute = null;
  		String aBox = null;
  		String aHouseNumberPrefix = null;
  		String aHouseNumber = null;
  		String aAssignedHouseNumber = null;
  		String aHouseNumberSuffix = null;
  		String aStreetDirection = null;
  		String aStreetName = null;
  		String aStreetThoroughfare = null;
  		String aStreetNameSuffix = null;
  		String aCity = null;
  		String aState = null;
  		String aPostalCode = null;
  		String aPostalCodePlus4 = null;
  		String aCounty = null;
  		String aCountry = null;
  		String aStructureType = null;
  		String aStructureValue = null;
  		String aLevelType = null;
  		String aLevelValue = null;
  		String aUnitType = null;
  		String aUnitValue = null;
  		String aOriginalStreetDirection = null;
  		String aOriginalStreetNameSuffix = null;
  		// String aCassAddressLines [] = null;
  		// String aAuxiliaryAddressLines [] = null;
  		// String aCassAdditionalInfo = null;
  		String aAdditionalInfo = null;
  		// String aBusinessName = null;
	};

	ExtensionPropertyBlob [] aExtensions = null;
  	FieldedAddressBlob aServiceAddress = null;
}
