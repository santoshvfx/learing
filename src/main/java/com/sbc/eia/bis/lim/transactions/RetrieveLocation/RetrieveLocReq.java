// $Id: RetrieveLocReq.java,v 1.33 2007/10/07 23:19:34 gg2712 Exp $

package com.sbc.eia.bis.lim.transactions.RetrieveLocation;

import com.sbc.bccs.idl.helpers.TN;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.bis.lim.util.LIMBase;
import com.sbc.eia.bis.lim.util.LIMTag;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.lim.helpers.AddressHandler;
import com.sbc.eia.idl.lim_types.LocationPropertiesToGetValue;
import com.sbc.eia.idl.lim_types.ProviderLocationProperties;
import com.sbc.eia.idl.types.StringOpt;

/**
 * This class wraps a request to retrieve location information.  It has NO
 * host-specific knowledge and is intended to be subclassed by a class that
 * knows how to convert to a host transaction.
 * Creation date: (3/1/01 4:46:51 PM)
 */
public class RetrieveLocReq
{
	protected LIMBase utility = null;
    protected BisContext bisContext = null;
	protected TN tn = null;
	protected AddressHandler addr = null;
    protected ProviderLocationProperties[] aPropertiesToGet = null;
    protected String propertiesToGetArray[] = null;
    public LocationPropertiesRequested locationPropertiesRequested = null;
    protected String exchangeCode = null;

    
    /**
     * This class creates booleans of LocationPropertiesRequested
     * to be used by host-specific classes to properly populate response.
     */
    public class LocationPropertiesRequested 
    {
        boolean retrieveALL                            = false;
        boolean retrievePostalAddress                  = false;
        boolean retrieveServiceAddress                 = false;
        boolean retrieveE911Address					= false;
        boolean retrieveSwitchSuperPopAddress          = false;
        
        boolean retrieveAddressMatchCode               = false;
        boolean retrieveAddressMatchCodeDescription    = false;
        boolean retrieveCentralOfficeCode              = false;
        boolean retrieveCentralOfficeType              = false;
        boolean retrieveCityStatePostalCodeValid       = false;
        boolean retrieveCommunityName                  = false;
        boolean retrieveCoSwitchSuperPop               = false;
        boolean retrieveCountyId						= false;
        boolean retrieveCustomerPremiseIndicator       = false;
        boolean retrieveDomSwitchPop                   = false;
        boolean retrieveE911Exempt                     = false;
        boolean retrieveE911NonRecurringCharge         = false;
        boolean retrieveE911Surcharge                  = false;
        boolean retrieveExceptionCode                  = false;
        boolean retrieveExceptionDescription           = false;
        boolean retrieveExchangeCode                   = false;
        boolean retrieveExco                           = false;
        boolean retrieveEktId                          = false;
        boolean retrieveGeoCode                        = false;
        boolean retrieveHorizontalCoordinate           = false;
        boolean retrieveLataCode                       = false;
        boolean retrieveLataName                       = false;
        boolean retrieveLatitude                       = false;
        boolean retrieveLongitude                      = false;
        boolean retrieveLocalProviderAbbreviatedName   = false;
        boolean retrieveLocalProviderExchangeCode      = false;
        boolean retrieveLocalProviderName              = false;
        boolean retrieveLocalProviderNumber            = false;
        boolean retrieveLocalProviderServingOfficeCode = false;
        boolean retrieveMailingBarCodeSuffix           = false;
        boolean retrieveMsaCode                        = false;
        boolean retrieveMsaName                        = false;
        boolean retrieveNearestDistanceColoToCo        = false;
        boolean retrieveNearestDistanceSuperPopToCo    = false;
        boolean retrieveNearestSbcColo                 = false;
        boolean retrieveNearestSbcCoSuperPop           = false;
        boolean retrieveNearestSbcCoWirecenter         = false;
        boolean retrieveOwnedWiring                    = false;
        boolean retrievePostalCarrierCode              = false;
        boolean retrievePrimaryDirectoryCode           = false;
        boolean retrievePrimaryNpaNxx                  = false;
        boolean retrievePublicSafetyAnsweringPointId   = false;
        boolean retrieveQuickDialTone                  = false;
        boolean retrieveQuickDialToneNumber            = false;
        boolean retrieveRateCenterCode                 = false;
        boolean retrieveRateZone                       = false;
        boolean retrieveRateZoneBandCode               = false;
        boolean retrieveSagNpa                         = false;
        boolean retrieveSagWireCenter                  = false;
        boolean retrieveSbcColoLsoCode                 = false;
        boolean retrieveSbcColoWirecenter              = false;
        boolean retrieveSbcServingOfficeCode           = false;
        boolean retrieveSbcServingOfficeWirecenter     = false;
        boolean retrieveServingAreaDescription         = false;
        boolean retrieveStreetAddressGuideArea         = false;
        boolean retrieveSurcharge4Percent              = false;
        boolean retrieveSurcharge16Percent             = false;
        boolean retrieveSwitchDataSuperPop             = false;
        boolean retrieveSwitchVoiceSuperPop            = false;
        boolean retrieveTarCode                        = false;
        boolean retrieveTelephoneNumber                = false;
        boolean retrieveVerticalCoordinate             = false;
        boolean retrieveWorkingServiceOnLocation       = false;

        /**
         * Constructor for LocationPropertiesRequested class.
         */
        public LocationPropertiesRequested()
        {
            setLocationPropertiesRequested();
        }

        public void setLocationPropertiesRequested()
        {
            if (propertiesToGetArray == null)
            {
                retrieveServiceAddress = true;
                retrieveALL = true;
                setDefaultToTrue();
            }
            else
            {
                for (int i=0; i < propertiesToGetArray.length; i++)
                {
                   if (propertiesToGetArray[i].equalsIgnoreCase(LocationPropertiesToGetValue.ALL))
                       retrieveALL = true;
                   if (propertiesToGetArray[i].equalsIgnoreCase(LocationPropertiesToGetValue.POSTALADDRESS))
                       retrievePostalAddress = true;
                   if (propertiesToGetArray[i].equalsIgnoreCase(LocationPropertiesToGetValue.SERVICEADDRESS))
                       retrieveServiceAddress = true;
                   if (propertiesToGetArray[i].equalsIgnoreCase(LocationPropertiesToGetValue.E911ADDRESS))
                       retrieveE911Address = true;
                }  // end for loop
                
                if (retrieveALL)
                    setDefaultToTrue();
                else 
                    setRetrieveLocationProperties();
            }
        }

        public void setDefaultToTrue()
        {
         	retrieveSwitchSuperPopAddress          = true;
            retrieveAddressMatchCode               = true;
            retrieveAddressMatchCodeDescription    = true;
            retrieveCentralOfficeCode              = true;
            retrieveCentralOfficeType              = true;
            retrieveCityStatePostalCodeValid       = true;
            retrieveCommunityName                  = true;
            retrieveCoSwitchSuperPop               = true;
            retrieveCountyId					   = true;	
            retrieveCustomerPremiseIndicator       = true;
            retrieveDomSwitchPop                   = true;
            retrieveE911Exempt                     = true;
            retrieveE911NonRecurringCharge         = true;
            retrieveE911Surcharge                  = true;
            retrieveExceptionCode                  = true;
            retrieveExceptionDescription           = true;
            retrieveExchangeCode                   = true;
            retrieveExco                           = true;
            retrieveEktId                          = true;
            retrieveGeoCode                        = true;
            retrieveHorizontalCoordinate           = true;
            retrieveLataCode                       = true;
            retrieveLataName                       = true;
            retrieveLatitude                       = true;
            retrieveLongitude                      = true;
            retrieveLocalProviderExchangeCode      = true;
            retrieveLocalProviderName              = true;
            retrieveLocalProviderNumber            = true;
            retrieveLocalProviderServingOfficeCode = true;
            retrieveLocalProviderAbbreviatedName   = true;
            retrieveMailingBarCodeSuffix           = true;
            retrieveMsaCode                        = true;
            retrieveMsaName                        = true;
            retrieveNearestDistanceColoToCo        = true;
            retrieveNearestDistanceSuperPopToCo    = true;
            retrieveNearestSbcColo                 = true;
            retrieveNearestSbcCoSuperPop           = true;
            retrieveNearestSbcCoWirecenter         = true;
            retrieveOwnedWiring                    = true;
            retrievePostalCarrierCode              = true;
            retrievePrimaryDirectoryCode           = true;
            retrievePrimaryNpaNxx                  = true;
            retrievePublicSafetyAnsweringPointId   = true;	
            retrieveQuickDialTone                  = true;
            retrieveQuickDialToneNumber            = true;
            retrieveRateCenterCode                 = true;
            retrieveRateZone                       = true;
            retrieveRateZoneBandCode               = true;
            retrieveSagNpa                         = true;
            retrieveSagWireCenter                  = true;
            retrieveSbcColoLsoCode                 = true;
            retrieveSbcColoWirecenter              = true;
            retrieveSbcServingOfficeCode           = true;
            retrieveSbcServingOfficeWirecenter     = true;
            retrieveServingAreaDescription         = true;
            retrieveStreetAddressGuideArea         = true;
            retrieveSurcharge4Percent              = true;
            retrieveSurcharge16Percent             = true;
            retrieveSwitchDataSuperPop             = true;
            retrieveSwitchVoiceSuperPop            = true;
            retrieveTarCode                        = true;
            retrieveTelephoneNumber                = true;
            retrieveVerticalCoordinate             = true;
            retrieveWorkingServiceOnLocation       = true;
        }

        /**
         * sets LocationProperties to get.
         * @return String 
         */

        public void setRetrieveLocationProperties()
        {
            // COMMENT FOR FUTURE ENHANCEMENT
            // When BisContext is used to pass properties to get
            // values for the ExtensionPropertySeq in the response
            // add loop here to analyze BisContext and set  
            // appropriate properties flags
            
            String locationPropertyToGet = null;
            
            for (int i=0; i < propertiesToGetArray.length; i++){
               
               locationPropertyToGet = propertiesToGetArray[i];
 
               if (locationPropertyToGet.equalsIgnoreCase(LocationPropertiesToGetValue.POSTALADDRESS))
                   retrievePostalAddress = true;
                   
               if (locationPropertyToGet.equalsIgnoreCase(LocationPropertiesToGetValue.SERVICEADDRESS))
                   retrieveServiceAddress = true;
                   
               if (locationPropertyToGet.equalsIgnoreCase(LocationPropertiesToGetValue.E911ADDRESS))
                   retrieveE911Address = true;                     
                   
//               if (locationPropertyToGet.equalsIgnoreCase(LocationPropertiesToGetValue.SWITCHSUPERPOPADDRESS)) 
//                   retrieveSwitchSuperPopAddress = true;
//               
//               if (locationPropertyToGet.equalsIgnoreCase(LocationPropertiesToGetValue.ADDRESSMATCHCODE))
//                   retrieveAddressMatchCode = true;
//               
//               if (locationPropertyToGet.equalsIgnoreCase(LocationPropertiesToGetValue.ADDRESSMATCHCODEDESCRIPTION))
//                   retrieveAddressMatchCodeDescription = true;
               
               if (locationPropertyToGet.equalsIgnoreCase(LocationPropertiesToGetValue.CENTRALOFFICECODE)) 
                   retrieveCentralOfficeCode = true;

//               if (locationPropertyToGet.equalsIgnoreCase(LocationPropertiesToGetValue.CENTRALOFFICETYPE))
//                   retrieveCentralOfficeType = true;
               
               if (locationPropertyToGet.equalsIgnoreCase(LocationPropertiesToGetValue.CITYSTATEPOSTALCODEVALID))
                   retrieveCityStatePostalCodeValid = true;
                                  
               if (locationPropertyToGet.equalsIgnoreCase(LocationPropertiesToGetValue.COMMUNITYNAME))
                   retrieveCommunityName = true;
               
//               if (locationPropertyToGet.equalsIgnoreCase(LocationPropertiesToGetValue.COSWITCHSUPERPOP)) 
//                   retrieveCoSwitchSuperPop = true;

               if (locationPropertyToGet.equalsIgnoreCase(LIMTag.COUNTYID)) 
                   retrieveCountyId = true;
   
//               if (locationPropertyToGet.equalsIgnoreCase(LocationPropertiesToGetValue.CUSTOMERPREMISEINDICATOR)) 
//                   retrieveCustomerPremiseIndicator = true;
//
//               if (locationPropertyToGet.equalsIgnoreCase(LocationPropertiesToGetValue.DOMSWITCHPOP))
//                   retrieveDomSwitchPop = true;
               
               if (locationPropertyToGet.equalsIgnoreCase(LocationPropertiesToGetValue.E911EXEMPT))
                   retrieveE911Exempt = true;
               
               if (locationPropertyToGet.equalsIgnoreCase(LocationPropertiesToGetValue.E911NONRECURRINGCHARGE)) 
                   retrieveE911NonRecurringCharge = true;

               if (locationPropertyToGet.equalsIgnoreCase(LocationPropertiesToGetValue.E911SURCHARGE))
                   retrieveE911Surcharge = true;
               
               if (locationPropertyToGet.equalsIgnoreCase(LocationPropertiesToGetValue.EXCHANGECODE))
                   retrieveExchangeCode = true;
               
               if (locationPropertyToGet.equalsIgnoreCase(LocationPropertiesToGetValue.EXCO)) 
                   retrieveExco = true;

//               if (locationPropertyToGet.equalsIgnoreCase(LocationPropertiesToGetValue.ECKTID)) 
//                   retrieveEktId = true;
//
//               if (locationPropertyToGet.equalsIgnoreCase(LocationPropertiesToGetValue.GEOCODE))
//                   retrieveGeoCode = true;
//               
//               if (locationPropertyToGet.equalsIgnoreCase(LocationPropertiesToGetValue.HORIZONTALCOORDINATE))
//                   retrieveHorizontalCoordinate = true;
               
               if (locationPropertyToGet.equalsIgnoreCase(LocationPropertiesToGetValue.LATACODE)) 
                   retrieveLataCode = true;

//               if (locationPropertyToGet.equalsIgnoreCase(LocationPropertiesToGetValue.LATANAME))
//                   retrieveLataName = true;
               
               if (locationPropertyToGet.equalsIgnoreCase(LIMTag.LATITUDE))
                   retrieveLatitude = true;
               
               if (locationPropertyToGet.equalsIgnoreCase(LIMTag.LONGITUDE))
                   retrieveLongitude = true;
                   
//               if (locationPropertyToGet.equalsIgnoreCase(LocationPropertiesToGetValue.LOCALPROVIDERABBREVIATEDNAME)) 
//                   retrieveLocalProviderAbbreviatedName = true;
//
//               if (locationPropertyToGet.equalsIgnoreCase(LocationPropertiesToGetValue.LOCALPROVIDEREXCHANGECODE))
//                   retrieveLocalProviderExchangeCode = true;
//               
//               if (locationPropertyToGet.equalsIgnoreCase(LocationPropertiesToGetValue.LOCALPROVIDERNAME))
//                   retrieveLocalProviderName = true;
//               
//               if (locationPropertyToGet.equalsIgnoreCase(LocationPropertiesToGetValue.LOCALPROVIDERNUMBER)) 
//                   retrieveLocalProviderNumber = true;
    
               if (locationPropertyToGet.equalsIgnoreCase(LocationPropertiesToGetValue.LOCALPROVIDERSERVINGOFFICECODE))
                   retrieveLocalProviderServingOfficeCode = true;
               
//               if (locationPropertyToGet.equalsIgnoreCase(LocationPropertiesToGetValue.MAILINGBARCODESUFFIX)) 
//                   retrieveMailingBarCodeSuffix = true;
//     
//               if (locationPropertyToGet.equalsIgnoreCase(LocationPropertiesToGetValue.MSACODE))
//                   retrieveMsaCode = true;
//               
//               if (locationPropertyToGet.equalsIgnoreCase(LocationPropertiesToGetValue.MSANAME))
//                   retrieveMsaName = true;
//               
//               if (locationPropertyToGet.equalsIgnoreCase(LocationPropertiesToGetValue.NEARESTDISTANCECOLOTOCO)) 
//                   retrieveNearestDistanceColoToCo = true;
//   
//               if (locationPropertyToGet.equalsIgnoreCase(LocationPropertiesToGetValue.NEARESTDISTANCESUPERPOPTOCO))
//                   retrieveNearestDistanceSuperPopToCo = true;
//               
//               if (locationPropertyToGet.equalsIgnoreCase(LocationPropertiesToGetValue.NEARESTSBCCOLO))
//                   retrieveNearestSbcColo = true;
//               
//               if (locationPropertyToGet.equalsIgnoreCase(LocationPropertiesToGetValue.NEARESTSBCCOSUPERPOP)) 
//                   retrieveNearestSbcCoSuperPop = true;
//
//               if (locationPropertyToGet.equalsIgnoreCase(LocationPropertiesToGetValue.NEARESTSBCCOWIRECENTER))
//                   retrieveNearestSbcCoWirecenter = true;
               
               if (locationPropertyToGet.equalsIgnoreCase(LocationPropertiesToGetValue.OWNEDWIRING))
                   retrieveOwnedWiring = true;
                
//               if (locationPropertyToGet.equalsIgnoreCase(LocationPropertiesToGetValue.POSTALCARRIERCODE)) 
//                   retrievePostalCarrierCode = true;

               if (locationPropertyToGet.equalsIgnoreCase(LocationPropertiesToGetValue.PRIMARYDIRECTORYCODE))
                   retrievePrimaryDirectoryCode = true;
               
               if (locationPropertyToGet.equalsIgnoreCase(LocationPropertiesToGetValue.PRIMARYNPANXX))
                   retrievePrimaryNpaNxx = true;

//               if (locationPropertyToGet.equalsIgnoreCase(LocationPropertiesToGetValue.PUBLICSAFETYANSWERINGPOINTID))
//                   retrievePublicSafetyAnsweringPointId = true;
               
               if (locationPropertyToGet.equalsIgnoreCase(LocationPropertiesToGetValue.QUICKDIALTONE)) 
                   retrieveQuickDialTone = true;

               if (locationPropertyToGet.equalsIgnoreCase(LocationPropertiesToGetValue.QUICKDIALTONENUMBER))
                   retrieveQuickDialToneNumber = true;
               
//               if (locationPropertyToGet.equalsIgnoreCase(LocationPropertiesToGetValue.RATECENTERCODE))
//                   retrieveRateCenterCode = true;
//               
//               if (locationPropertyToGet.equalsIgnoreCase(LocationPropertiesToGetValue.RATEZONE)) 
//                   retrieveRateZone = true;

               if (locationPropertyToGet.equalsIgnoreCase(LocationPropertiesToGetValue.RATEZONEBANDCODE))
                   retrieveRateZoneBandCode = true;
               
//               if (locationPropertyToGet.equalsIgnoreCase(LocationPropertiesToGetValue.SAGNPA))
//                   retrieveSagNpa = true;
               
               if (locationPropertyToGet.equalsIgnoreCase(LocationPropertiesToGetValue.SAGWIRECENTER)) 
                   retrieveSagWireCenter = true;

//               if (locationPropertyToGet.equalsIgnoreCase(LocationPropertiesToGetValue.SBCCOLOLSOCODE))
//                   retrieveSbcColoLsoCode = true;
//               
//               if (locationPropertyToGet.equalsIgnoreCase(LocationPropertiesToGetValue.SBCCOLOWIRECENTER))
//                   retrieveSbcColoWirecenter = true;
//               
//               if (locationPropertyToGet.equalsIgnoreCase(LocationPropertiesToGetValue.SBCSERVINGOFFICECODE)) 
//                   retrieveSbcServingOfficeCode = true;
//
//               if (locationPropertyToGet.equalsIgnoreCase(LocationPropertiesToGetValue.SBCSERVINGOFFICEWIRECENTER))
//                   retrieveSbcServingOfficeWirecenter = true;

               if (locationPropertyToGet.equalsIgnoreCase(LocationPropertiesToGetValue.SERVINGAREADESCRIPTION))
                   retrieveServingAreaDescription = true;
               
               if (locationPropertyToGet.equalsIgnoreCase(LocationPropertiesToGetValue.STREETADDRESSGUIDEAREA))
                   retrieveStreetAddressGuideArea = true;
               
               if (locationPropertyToGet.equalsIgnoreCase(LocationPropertiesToGetValue.SURCHARGE4PERCENT)) 
                   retrieveSurcharge4Percent = true;
   
               if (locationPropertyToGet.equalsIgnoreCase(LocationPropertiesToGetValue.SURCHARGE16PERCENT))
                   retrieveSurcharge16Percent = true;
                
//               if (locationPropertyToGet.equalsIgnoreCase(LocationPropertiesToGetValue.SWITCHDATASUPERPOP))
//                   retrieveSwitchDataSuperPop = true;
//               
//               if (locationPropertyToGet.equalsIgnoreCase(LocationPropertiesToGetValue.SWITCHVOICESUPERPOP)) 
//                   retrieveSwitchVoiceSuperPop = true;
  
               if (locationPropertyToGet.equalsIgnoreCase(LocationPropertiesToGetValue.TARCODE))
                   retrieveTarCode = true;
                
               if (locationPropertyToGet.equalsIgnoreCase(LocationPropertiesToGetValue.TELEPHONENUMBER))
                   retrieveTelephoneNumber = true;
               
//               if (locationPropertyToGet.equalsIgnoreCase(LocationPropertiesToGetValue.VERTICALCOORDINATE)) 
//                   retrieveVerticalCoordinate = true;

               if (locationPropertyToGet.equalsIgnoreCase(LocationPropertiesToGetValue.WORKINGSERVICEONLOCATION)) 
                   retrieveWorkingServiceOnLocation = true;
                   
            } // end for loop
        }

        /**
         * Returns a String representation of LocationPropertiesRequested.
         * @return String 
         */
        public String toString()
        {
           StringBuffer temp = new StringBuffer();
           try{
               if (propertiesToGetArray.length > 0){
                   temp.append("|");
                   for (int i=0; i < propertiesToGetArray.length; i++){
                       temp.append(propertiesToGetArray[i] + "|");
                   }
               }
           }
           catch (org.omg.CORBA.BAD_OPERATION e) {}
           catch (java.lang.NullPointerException e) {}

           return temp.toString();
        }
        
        /**
         * Returns the retrieveAddressMatchCode.
         * @return boolean
         */
        public boolean isRetrieveAddressMatchCode()
        {
            return retrieveAddressMatchCode;
        }

        /**
         * Returns the retrieveAddressMatchCodeDescription.
         * @return boolean
         */
        public boolean isRetrieveAddressMatchCodeDescription()
        {
            return retrieveAddressMatchCodeDescription;
        }

        /**
         * Returns the retrieveAddressMatchCodeDescription.
         * @return boolean
         */
        public boolean isRetrieveALL()
        {
            return retrieveALL;
        }

        /**
         * Returns the retrieveCentralOfficeCode.
         * @return boolean
         */
        public boolean isRetrieveCentralOfficeCode()
        {
            return retrieveCentralOfficeCode;
        }

        /**
         * Returns the retrieveCentralOfficeType.
         * @return boolean
         */
        public boolean isRetrieveCentralOfficeType()
        {
            return retrieveCentralOfficeType;
        }

        /**
         * Returns the retrieveCityStatePostalCodeValid.
         * @return boolean
         */
        public boolean isRetrieveCityStatePostalCodeValid()
        {
            return retrieveCityStatePostalCodeValid;
        }

        /**
         * Returns the retrieveCommunityName.
         * @return boolean
         */
        public boolean isRetrieveCommunityName()
        {
            return retrieveCommunityName;
        }

        /**
         * Returns the retrieveCoSwitchSuperPop.
         * @return boolean
         */
        public boolean isRetrieveCoSwitchSuperPop()
        {
            return retrieveCoSwitchSuperPop;
        }

        /**
         * Returns the retrieveCountyId.
         * @return boolean
         */
        public boolean isRetrieveCountyId()
        {
            return retrieveCountyId;
        }
                
        /**
         * Returns the retrieveCustomerPremiseIndicator.
         * @return boolean
         */
        public boolean isRetrieveCustomerPremiseIndicator()
        {
            return retrieveCustomerPremiseIndicator;
        }

        /**
         * Returns the retrieveDomSwitchPop.
         * @return boolean
         */
        public boolean isRetrieveDomSwitchPop()
        {
            return retrieveDomSwitchPop;
        }

        /**
         * Returns the retrieveE911Address.
         * @return boolean
         */
        public boolean isRetrieveE911Address()
        {
            return retrieveE911Address;
        }

        /**
         * Returns the retrieveE911Exempt.
         * @return boolean
         */
        public boolean isRetrieveE911Exempt()
        {
            return retrieveE911Exempt;
        }

        /**
         * Returns the retrieveE911NonRecurringCharge.
         * @return boolean
         */
        public boolean isRetrieveE911NonRecurringCharge()
        {
            return retrieveE911NonRecurringCharge;
        }

        /**
         * Returns the retrieveE911Surcharge.
         * @return boolean
         */
        public boolean isRetrieveE911Surcharge()
        {
            return retrieveE911Surcharge;
        }

        /**
         * Returns the retrieveE911Surcharge.
         * @return boolean
         */
        public boolean isRetrieveExceptionCode()
        {
            return retrieveExceptionCode;
        }

        /**
         * Returns the retrieveExceptionDescription.
         * @return boolean
         */
        public boolean isRetrieveExceptionDescription()
        {
            return retrieveExceptionDescription;
        }

        /**
         * Returns the retrieveExchangeCode.
         * @return boolean
         */
        public boolean isRetrieveExchangeCode()
        {
            return retrieveExchangeCode;
        }

        /**
         * Returns the retrieveExco.
         * @return boolean
         */
        public boolean isRetrieveExco()
        {
            return retrieveExco;
        }
        
        /**
         * Returns the retrieveEktId.
         * @return boolean
         */
        public boolean isRetrieveEktId()
        {
            return retrieveEktId;
        }

        /**
         * Returns the retrieveGeoCode.
         * @return boolean
         */
        public boolean isRetrieveGeoCode()
        {
            return retrieveGeoCode;
        }

        /**
         * Returns the retrieveHorizontalCoordinate.
         * @return boolean
         */
        public boolean isRetrieveHorizontalCoordinate()
        {
            return retrieveHorizontalCoordinate;
        }

        /**
         * Returns the retrieveLataCode.
         * @return boolean
         */
        public boolean isRetrieveLataCode()
        {
            return retrieveLataCode;
        }

        /**
         * Returns the retrieveLataName.
         * @return boolean
         */
        public boolean isRetrieveLataName()
        {
            return retrieveLataName;
        }

        /**
         * Returns the retrieveLatitude.
         * @return boolean
         */
        public boolean isRetrieveLatitude()
        {
            return retrieveLatitude;
        }
        
        /**
         * Returns the retrieveLongitude.
         * @return boolean
         */
        public boolean isRetrieveLongitude()
        {
            return retrieveLongitude;
        }

        /**
         * Returns the retrieveLocalProviderExchangeCode.
         * @return boolean
         */
        public boolean isRetrieveLocalProviderExchangeCode()
        {
            return retrieveLocalProviderExchangeCode;
        }

        /**
         * Returns the retrieveLocalProviderName.
         * @return boolean
         */
        public boolean isRetrieveLocalProviderName()
        {
            return retrieveLocalProviderName;
        }

        /**
         * Returns the retrieveLocalProviderNumber.
         * @return boolean
         */
        public boolean isRetrieveLocalProviderNumber()
        {
            return retrieveLocalProviderNumber;
        }

        /**
         * Returns the retrieveLocalProviderServingOfficeCode.
         * @return boolean
         */
        public boolean isRetrieveLocalProviderServingOfficeCode()
        {
            return retrieveLocalProviderServingOfficeCode;
        }

        /**
         * Returns the retrieveLocalProviderAbbreviatedName.
         * @return boolean
         */
        public boolean isRetrieveLocalProviderAbbreviatedName()
        {
            return retrieveLocalProviderAbbreviatedName;
        }

        /**
         * Returns the retrieveMailingBarCodeSuffix.
         * @return boolean
         */
        public boolean isRetrieveMailingBarCodeSuffix()
        {
            return retrieveMailingBarCodeSuffix;
        }

        /**
         * Returns the retrieveMsaCode.
         * @return boolean
         */
        public boolean isRetrieveMsaCode()
        {
            return retrieveMsaCode;
        }

        /**
         * Returns the retrieveMsaName.
         * @return boolean
         */
        public boolean isRetrieveMsaName()
        {
            return retrieveMsaName;
        }

        /**
         * Returns the retrieveNearestDistanceColoToCo.
         * @return boolean
         */
        public boolean isRetrieveNearestDistanceColoToCo()
        {
            return retrieveNearestDistanceColoToCo;
        }

        /**
         * Returns the retrieveNearestDistanceSuperPopToCo.
         * @return boolean
         */
        public boolean isRetrieveNearestDistanceSuperPopToCo()
        {
            return retrieveNearestDistanceSuperPopToCo;
        }

        /**
         * Returns the retrieveNearestSbcColo.
         * @return boolean
         */
        public boolean isRetrieveNearestSbcColo()
        {
            return retrieveNearestSbcColo;
        }

        /**
         * Returns the retrieveNearestSbcCoSuperPop.
         * @return boolean
         */
        public boolean isRetrieveNearestSbcCoSuperPop()
        {
            return retrieveNearestSbcCoSuperPop;
        }

        /**
         * Returns the retrieveNearestSbcCoWirecenter.
         * @return boolean
         */
        public boolean isRetrieveNearestSbcCoWirecenter()
        {
            return retrieveNearestSbcCoWirecenter;
        }

        /**
         * Returns the retrieveOwnedWiring.
         * @return boolean
         */
        public boolean isRetrieveOwnedWiring()
        {
            return retrieveOwnedWiring;
        }

        /**
         * Returns the retrievePostalAddress.
         * @return boolean
         */
        public boolean isRetrievePostalAddress()
        {
            return retrievePostalAddress;
        }

        /**
         * Returns the retrievePostalCarrierCode.
         * @return boolean
         */
        public boolean isRetrievePostalCarrierCode()
        {
            return retrievePostalCarrierCode;
        }

        /**
         * Returns the retrievePrimaryDirectoryCode.
         * @return boolean
         */
        public boolean isRetrievePrimaryDirectoryCode()
        {
            return retrievePrimaryDirectoryCode;
        }

        /**
         * Returns the retrievePrimaryNpaNxx.
         * @return boolean
         */
        public boolean isRetrievePrimaryNpaNxx()
        {
            return retrievePrimaryNpaNxx;
        }

        /**
         * Returns the retrievePrimaryNpaNxx.
         * @return boolean
         */
        public boolean isRetrievePublicSafetyAnsweringPointId()
        {
            return retrievePublicSafetyAnsweringPointId;
        }

        /**
         * Returns the retrieveQuickDialTone.
         * @return boolean
         */
        public boolean isRetrieveQuickDialTone()
        {
            return retrieveQuickDialTone;
        }

        /**
         * Returns the retrieveQuickDialToneNumber.
         * @return boolean
         */
        public boolean isRetrieveQuickDialToneNumber()
        {
            return retrieveQuickDialToneNumber;
        }

        /**
         * Returns the retrieveRateCenterCode.
         * @return boolean
         */
        public boolean isRetrieveRateCenterCode()
        {
            return retrieveRateCenterCode;
        }

        /**
         * Returns the retrieveRateZone.
         * @return boolean
         */
        public boolean isRetrieveRateZone()
        {
            return retrieveRateZone;
        }

        /**
         * Returns the retrieveRateZoneBandCode.
         * @return boolean
         */
        public boolean isRetrieveRateZoneBandCode()
        {
            return retrieveRateZoneBandCode;
        }

        /**
         * Returns the retrieveSagNpa.
         * @return boolean
         */
        public boolean isRetrieveSagNpa()
        {
            return retrieveSagNpa;
        }

        /**
         * Returns the retrieveSagWireCenter.
         * @return boolean
         */
        public boolean isRetrieveSagWireCenter()
        {
            return retrieveSagWireCenter;
        }

        /**
         * Returns the retrieveSbcColoLsoCode.
         * @return boolean
         */
        public boolean isRetrieveSbcColoLsoCode()
        {
            return retrieveSbcColoLsoCode;
        }

        /**
         * Returns the retrieveSbcColoWirecenter.
         * @return boolean
         */
        public boolean isRetrieveSbcColoWirecenter()
        {
            return retrieveSbcColoWirecenter;
        }

        /**
         * Returns the retrieveSbcServingOfficeCode.
         * @return boolean
         */
        public boolean isRetrieveSbcServingOfficeCode()
        {
            return retrieveSbcServingOfficeCode;
        }

        /**
         * Returns the retrieveSbcServingOfficeWirecenter.
         * @return boolean
         */
        public boolean isRetrieveSbcServingOfficeWirecenter()
        {
            return retrieveSbcServingOfficeWirecenter;
        }

         /**
         * Returns the retrieveServingAreaDescription.
         * @return boolean
         */
        public boolean isRetrieveServingAreaDescription()
        {
            return retrieveServingAreaDescription;
        }

       /**
         * Returns the retrieveServiceAddress.
         * @return boolean
         */
        public boolean isRetrieveServiceAddress()
        {
            return retrieveServiceAddress;
        }

        /**
         * Returns the retrieveStreetAddressGuideArea.
         * @return boolean
         */
        public boolean isRetrieveStreetAddressGuideArea()
        {
            return retrieveStreetAddressGuideArea;
        }

        /**
         * Returns the retrieveSurcharge16Percent.
         * @return boolean
         */
        public boolean isRetrieveSurcharge16Percent()
        {
            return retrieveSurcharge16Percent;
        }

        /**
         * Returns the retrieveSurcharge4Percent.
         * @return boolean
         */
        public boolean isRetrieveSurcharge4Percent()
        {
            return retrieveSurcharge4Percent;
        }

        /**
         * Returns the retrieveSwitchDataSuperPop.
         * @return boolean
         */
        public boolean isRetrieveSwitchDataSuperPop()
        {
            return retrieveSwitchDataSuperPop;
        }

        /**
         * Returns the retrieveSwitchSuperPopAddress.
         * @return boolean
         */
        public boolean isRetrieveSwitchSuperPopAddress()
        {
            return retrieveSwitchSuperPopAddress;
        }

        /**
         * Returns the retrieveSwitchVoiceSuperPop.
         * @return boolean
         */
        public boolean isRetrieveSwitchVoiceSuperPop()
        {
            return retrieveSwitchVoiceSuperPop;
        }

        /**
         * Returns the retrieveTarCode.
         * @return boolean
         */
        public boolean isRetrieveTarCode()
        {
            return retrieveTarCode;
        }

        /**
         * Returns the retrieveTelephoneNubmer.
         * @return boolean
         */
        public boolean isRetrieveTelephoneNumber()
        {
            return retrieveTelephoneNumber;
        }

        /**
         * Returns the retrieveVerticalCoordinate.
         * @return boolean
         */
        public boolean isRetrieveVerticalCoordinate()
        {
            return retrieveVerticalCoordinate;
        }

        /**
         * Returns the retrieveWorkingServiceOnLocation.
         * @return boolean
         */
        public boolean isRetrieveWorkingServiceOnLocation()
        {
            return retrieveWorkingServiceOnLocation;
        }

    }; 

    /**
     * Starts the application.
     * @param args an array of command-line arguments
     */
    public static void main(java.lang.String[] args) {
    	// Insert code to start the application here.
    }
    
    /**
     * Returns a String that represents the value of this object.
     * @return a string representation of the receiver
     */
    public String toString()
    {
    	return addr.toString() + "\nTN=[" + getTn().toString() + "]" 
    	        + "\nExchangeCode=[" + getExchangeCode() + "]";
    }
    
    /**
     * Getter method for the TN.
     * Creation date: (3/2/01 1:09:16 PM)
     * @return TN
     * @see #setTn(TN)
     */
    public TN getTn()
    {
    	return tn;
    }
    
    /**
     * Setter method for TN.
     * Creation date: (3/2/01 1:09:16 PM)
     * @param newTn TN
     * @see #getTn
     */
    public void setTn(TN newTn)
    {
    	tn = newTn;
    }
    
    /**
     * Create a RetrieveLocReq object from a retrieveLocationForAddress request.
     * Creation date: (3/1/01 4:50:45 PM)
     * @param utility LIMBase
     * @param address AddressHandler
     */
    public RetrieveLocReq(LIMBase utility,
                          ProviderLocationProperties[] aPropertiesToGet)
    {
        setUtility(utility);
        setBisContext(utility.getCallerContext());
        setAddr(new AddressHandler());
        setTn(new TN());
        
        if (aPropertiesToGet == null  ||
            aPropertiesToGet.length == 0)
        {
            setPropertiesToGet(getDefaultPropertiesToGet());
        }
        else
        {
            setPropertiesToGet(aPropertiesToGet);
        }
        
        setPropertiesToGetArray();
        setLocationPropertiesRequested(new LocationPropertiesRequested());
    }
    
    /**
     * Create a RetrieveLocReq object from a retrieveLocationForAddress request.
     * Creation date: (3/1/01 4:50:45 PM)
     * @param utility LIMBase
     * @param address AddressHandler
     */
    public RetrieveLocReq(LIMBase utility,
                          AddressHandler address, 
                          ProviderLocationProperties[] aPropertiesToGet)
    {
    	 this(utility,
               address, 
               aPropertiesToGet,
               null);
    }
    
    /**
     * Create a RetrieveLocReq object from a retrieveLocationForService request.
     * Creation date: (3/1/01 4:52:26 PM)
     * @param utility  a LIMBase object
     * @param service TN
     */
    public RetrieveLocReq(LIMBase utility,
                          TN service,
                          ProviderLocationProperties[] aPropertiesToGet)
    {
    	setUtility(utility);
        setBisContext(utility.getCallerContext());
    	setTn(service);
    	setAddr(new AddressHandler());
        
        if (aPropertiesToGet == null  ||
            aPropertiesToGet.length == 0)
        {
            setPropertiesToGet(getDefaultPropertiesToGet());
        }
        else
        {
            setPropertiesToGet(aPropertiesToGet);
        }

        setPropertiesToGetArray();
        setLocationPropertiesRequested(new LocationPropertiesRequested());
    }
    
    /**
     * Create a RetrieveLocReq object from a retrieveLocationForAddress request.
     * @param utility LIMBase
     * @param address AddressHandler
     * @param aPropertiesToGet ProviderLocationProperties
     * @param aExchangeCode StringOpt
     */
    public RetrieveLocReq(LIMBase utility,
                          AddressHandler address, 
                          ProviderLocationProperties[] aPropertiesToGet,
                          StringOpt aExchangeCode)
    {   
     	setUtility(utility);
        setBisContext(utility.getCallerContext());
    	setAddr(address);
    	setTn(new TN());
    	
        setExchangeCode(aExchangeCode);
                
        if (aPropertiesToGet == null  ||
            aPropertiesToGet.length == 0)
        {
            setPropertiesToGet(getDefaultPropertiesToGet());
        }
        else
        {
            setPropertiesToGet(aPropertiesToGet);
        }

        setPropertiesToGetArray();
        setLocationPropertiesRequested(new LocationPropertiesRequested());
    }
    
    /**
	 * Returns the exchangeCode.
	 * @return aExchangeCode
	 */
	public String getExchangeCode() {
		
		return exchangeCode;
	}

	/**
	 * Sets the exchangeCode.
	 * @param aExchangeCode The aExchangeCode to set
	 */
	public void setExchangeCode(StringOpt aExchangeCode) {
		
		try
		{
			exchangeCode =  aExchangeCode.theValue().trim();
		}
		catch(Exception e)
		{
			exchangeCode = "";
		}
	}
	
    /**
     * Setter method for Utility.
     * Creation date: (3/1/01 5:39:48 PM)
     * @param newUtility LIMBase
     * @see #getUtility
     */
    public void setUtility(LIMBase newUtility)
    {
    	utility = newUtility;
    }
    
    /**
     * Getter method for Utility.
     * Creation date: (3/1/01 5:39:48 PM)
     * @return LIMBase
     * @see #setUtility(LIMBase)
     */
    public LIMBase getUtility()
    {
    	return utility;
    }

    /**
     * Setter method for BisContext.
     * Creation date: (3/1/01 5:39:48 PM)
     * @param aBisContext BisContext
     * @see #getBisContext
     */
    public void setBisContext(BisContext aBisContext)
    {
        bisContext = aBisContext;
    }
    
    /**
     * Getter method for BisContext.
     * Creation date: (3/1/01 5:39:48 PM)
     * @return BisContext
     * @see #setBisContext
     */
    public BisContext getBisContext()
    {
        return bisContext;
    }
    
    
    /**
     * Getter method for the address.
     * Creation date: (3/28/01 4:29:37 PM)
     * @return AddressHandler
     * @see #setAddr(AddressHandler)
     */
    public AddressHandler getAddr()
    {
    	return addr;
    }
    /**
     * Log Address Request.
     * Creation date: (3/28/01 4:29:37 PM)
     * @return AddressHandler
     * @see #setAddr(AddressHandler)
     */
    public void logAddressReq()
    {
        getUtility().log(LogEventId.DEBUG_LEVEL_2,"RetrieveLocationForAddress: \n" + toString());
    }
    
    /**
     * Log Service Request.
     * Creation date: (3/28/01 4:29:37 PM)
     * @return AddressHandler
     * @see #setAddr(AddressHandler)
     */
    public void logServiceReq()
    {
        getUtility().log(LogEventId.DEBUG_LEVEL_2,"RetrieveLocationForService: \n" + toString());
    }
    
    /**
     * Setter method for the address.
     * Creation date: (8/8/01 7:18:52 AM)
     * @param newAddr AddressHandler
     * @see #getAddr
     */
    public void setAddr(AddressHandler newAddr) {
    	addr = newAddr;	
    }
    /**
     * Returns the locationPropertiesRequested.
     * @return LocationPropertiesRequested
     */
    public LocationPropertiesRequested getLocationPropertiesRequested()
    {
        return locationPropertiesRequested;
    }

    /**
     * Returns the providerLocationPropertiesRequestsArray.
     * @return ProviderLocationPropertiesRequestsSeqOpt
     */
    public String[] getPropertiesToGetArray()
    {
        return propertiesToGetArray;
    }

    /**
     * Sets the locationPropertiesRequested.
     * @param locationPropertiesRequested The locationPropertiesRequested to set
     */
    public void setPropertiesToGetArray()
    {
        if (aPropertiesToGet != null &&
            aPropertiesToGet.length > 0 )
        {
            propertiesToGetArray = aPropertiesToGet[0].aLocationPropertiesToGet;
        }
    }

    /**
     * Sets the locationPropertiesRequested.
     * @param locationPropertiesRequested The locationPropertiesRequested to set
     */
    public void setLocationPropertiesRequested(LocationPropertiesRequested aLocationPropertiesRequested)
    {
        this.locationPropertiesRequested = aLocationPropertiesRequested;
    }

    /**
     * Sets the locationPropertiesToGetArray.
     * @param String[] locationPropertiesToGetArray The providerLocationPropertiesRequestsArray to set
     */
    public void setPropertiesToGet(ProviderLocationProperties[] aPropertiesToGet)
    {
        try{
            this.aPropertiesToGet = aPropertiesToGet;
        }
        catch (org.omg.CORBA.BAD_OPERATION e) {}
        catch (java.lang.NullPointerException e) {}
    }

    /**
     * Returns the aPropertiesToGet.
     * @return ProviderLocationProperties[]
     */
    public ProviderLocationProperties[] getProviderLocationPropertiesToGet()
    {
        return aPropertiesToGet;
    }

    /**
     * Returns the aPropertiesToGet.
     * @return ProviderLocationProperties[]
     */
    public ProviderLocationProperties[] getDefaultPropertiesToGet()
    {
        ProviderLocationProperties providerLocationPropertiesSeq[] = null;
        providerLocationPropertiesSeq = new ProviderLocationProperties[1];
        StringOpt aProviderName = new StringOpt ();
        aProviderName.__default();
        String aLocationPropertiesToGet[] = new String[2];
        aLocationPropertiesToGet[0] = LocationPropertiesToGetValue.SERVICEADDRESS;
        aLocationPropertiesToGet[1] = LocationPropertiesToGetValue.ALL;
        ProviderLocationProperties providerLocationProperties = 
                  new ProviderLocationProperties (aProviderName, aLocationPropertiesToGet);
        providerLocationPropertiesSeq[0] = providerLocationProperties;
      
       
       
        return providerLocationPropertiesSeq;
    }

}