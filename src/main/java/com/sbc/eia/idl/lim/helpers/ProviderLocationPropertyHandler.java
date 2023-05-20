package com.sbc.eia.idl.lim.helpers;

import com.sbc.bccs.idl.helpers.IDLUtil;
import com.sbc.eia.idl.lim_types.Address;
import com.sbc.eia.idl.lim_types.AddressOpt;
import com.sbc.eia.idl.lim_types.ExtensionProperty;
import com.sbc.eia.idl.lim_types.ExtensionPropertySeqOpt;
import com.sbc.eia.idl.lim_types.ProviderLocationProperty;
/**
 * @author RZ7367
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class ProviderLocationPropertyHandler {
    /**
     * Returns a default ProviderLocationProperty.
     * @return ProviderLocationProperty
     */
    public static ProviderLocationProperty
        getDefaultProviderLocationProperty()
    {
        ProviderLocationProperty locData = new ProviderLocationProperty();
        
        locData.aProviderName = IDLUtil.toOpt((String)null) ;
        
        locData.aPostalAddress = new AddressOpt();
        locData.aPostalAddress.__default();
        locData.aServiceAddress = new AddressOpt();
        locData.aServiceAddress.__default();
        locData.aE911Address = new AddressOpt();
        locData.aE911Address.__default();
        locData.aSAGAddress = new AddressOpt();
        locData.aSAGAddress.__default();
        locData.aSwitchSuperPopAddress = new AddressOpt();
        locData.aSwitchSuperPopAddress.__default();
        
        locData.aAddressMatchCode = IDLUtil.toOpt((String)null) ;
        locData.aAddressMatchCodeDescription = IDLUtil.toOpt((String)null) ;
        locData.aBuildingType = IDLUtil.toOpt((String)null);
        locData.aCentralOfficeCode = IDLUtil.toOpt((String)null) ;
        locData.aCentralOfficeType = IDLUtil.toOpt((String)null) ;
        locData.aCityStatePostalCodeValid = IDLUtil.toOpt((String)null) ;
        locData.aCommunityName = IDLUtil.toOpt((String)null) ;
        locData.aCoSwitchSuperPop = IDLUtil.toOpt((String)null) ;
        locData.aCountyId = IDLUtil.toOpt((String)null) ;
        locData.aCustomerPremiseIndicator = IDLUtil.toOpt((String)null);
        locData.aDomSwitchPop = IDLUtil.toOpt((String)null) ;
        locData.aE911Exempt = IDLUtil.toOpt((String)null) ;
        locData.aE911NonRecurringCharge = IDLUtil.toOpt((String)null) ;
        locData.aE911Surcharge = IDLUtil.toOpt((String)null) ;
        locData.aEcktId = IDLUtil.toOpt((String)null);
        locData.aExceptionCode = IDLUtil.toOpt((String)null) ;
        locData.aExceptionDescription = IDLUtil.toOpt((String)null) ;
        locData.aExchangeCode = IDLUtil.toOpt((String)null) ;
        locData.aExco = IDLUtil.toOpt((String)null) ;
        locData.aGeoCode = IDLUtil.toOpt((String)null) ;
        locData.aHorizontalCoordinate = IDLUtil.toOpt((String)null) ;
        locData.aLataCode = IDLUtil.toOpt((String)null) ;
        locData.aLataName = IDLUtil.toOpt((String)null) ;
        locData.aLatitude = IDLUtil.toOpt((String)null) ;
        locData.aLongitude = IDLUtil.toOpt((String)null) ;
        locData.aLegalEntity	= IDLUtil.toOpt((String)null);
        locData.aLocalProviderExchangeCode = IDLUtil.toOpt((String)null) ;
        locData.aLocalProviderName = IDLUtil.toOpt((String)null) ;
        locData.aLocalProviderNumber = IDLUtil.toOpt((String)null) ;
        locData.aLocalProviderServingOfficeCode = IDLUtil.toOpt((String)null) ;
        locData.aLocalProviderAbbreviatedName = IDLUtil.toOpt((String)null) ;
        locData.aLocationType = IDLUtil.toOpt((String)null) ;
        locData.aMailingBarCodeSuffix = IDLUtil.toOpt((String)null) ;
        locData.aMsaCode = IDLUtil.toOpt((String)null) ;
        locData.aMsaName = IDLUtil.toOpt((String)null) ;
        locData.aNearestDistanceColoToCo = IDLUtil.toOpt((String)null) ;
        locData.aNearestDistanceSuperPopToCo = IDLUtil.toOpt((String)null) ;
        locData.aNearestSbcColo = IDLUtil.toOpt((String)null) ;
        locData.aNearestSbcCoSuperPop = IDLUtil.toOpt((String)null) ;
        locData.aNearestSbcCoWirecenter = IDLUtil.toOpt((String)null) ;
        locData.aOwnedWiring = IDLUtil.toOpt((String)null) ;
        locData.aPostalCarrierCode = IDLUtil.toOpt((String)null) ;
        locData.aPrimaryDirectoryCode = IDLUtil.toOpt((String)null) ;
        locData.aPrimaryNpaNxx = IDLUtil.toOpt((String)null) ;
        locData.aPrimaryNpaNxx = IDLUtil.toOpt((String)null) ;
        locData.aPublicSafetyAnsweringPointId = IDLUtil.toOpt((String)null) ;
        locData.aQuickDialTone = IDLUtil.toOpt((String)null) ;
        locData.aQuickDialToneNumber = IDLUtil.toOpt((String)null) ;
        locData.aRateCenterCode = IDLUtil.toOpt((String)null) ;
        locData.aRateZone = IDLUtil.toOpt((String)null) ;
        locData.aRateZoneBandCode = IDLUtil.toOpt((String)null) ;
        locData.aSagNpa = IDLUtil.toOpt((String)null) ;
        locData.aSagWireCenter = IDLUtil.toOpt((String)null) ;
        locData.aSbcColoLsoCode = IDLUtil.toOpt((String)null) ;
        locData.aSbcColoWirecenter = IDLUtil.toOpt((String)null) ;
        locData.aSbcServingOfficeCode = IDLUtil.toOpt((String)null) ;
        locData.aSbcServingOfficeWirecenter = IDLUtil.toOpt((String)null) ;
        locData.aServingAreaDescription = IDLUtil.toOpt((String)null) ;
        locData.aServingNetworkType= IDLUtil.toOpt((String)null);
        locData.aSmartmoves= IDLUtil.toOpt((String)null);
        locData.aStreetAddressGuideArea = IDLUtil.toOpt((String)null) ;
        locData.aSurcharge16Percent = IDLUtil.toOpt((String)null) ;
        locData.aSurcharge4Percent = IDLUtil.toOpt((String)null) ;
        locData.aSwitchDataSuperPop = IDLUtil.toOpt((String)null) ;
        locData.aSwitchVoiceSuperPop = IDLUtil.toOpt((String)null) ;
        locData.aTarCode = IDLUtil.toOpt((String)null) ;
        locData.aTelephoneNumber = IDLUtil.toOpt((String)null) ;
        locData.aVerticalCoordinate = IDLUtil.toOpt((String)null) ;
        locData.aVideoHubOffice = IDLUtil.toOpt((String)null);
        locData.aWorkingServiceOnLocation = IDLUtil.toOpt((String)null) ;
        
        locData.aExtensions = new ExtensionPropertySeqOpt ();
        locData.aExtensions.__default();
        
        return locData;
    }

}
