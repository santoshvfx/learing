package com.att.lms.bis.service.ovals;

import com.att.lms.bis.service.LimLegacyService;
import com.att.lms.bis.service.ovals.model.avsqub.request.AvsqubRequest;
import com.att.lms.bis.service.ovals.model.avsqub.response.AvsqubResponse;
import com.att.lms.bis.service.ovals.model.pla.request.PlaRequest;
import com.att.lms.bis.service.ovals.model.pla.response.PlaResponse;
import com.sbc.bccs.idl.helpers.IDLUtil;
import com.sbc.bccs.utilities.Utility;
import com.sbc.eia.bis.BusinessInterface.ovals.OVALS;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.idl.bis_types.*;
import com.sbc.eia.idl.exception_types.ExceptionCode;
import com.sbc.eia.idl.lim.*;
import com.sbc.eia.idl.lim_types.*;
import com.sbc.eia.idl.types.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OvalsWrapper
{
    private final OvalsApi ovals;
    private final OvalsPavApi ovalsPav;
    private final OvalsPlaApi ovalsPla;
    private final OVALS oldInterface;
    private final Utility logger;
    private BisContext aBisContext;

    public OvalsWrapper(Utility logger, OvalsApi ovals, OvalsPavApi ovalsPav, OvalsPlaApi ovalsPla, OVALS old)
    {
        this.ovals = ovals;
        this.oldInterface = old;
        this.ovalsPav = ovalsPav;
        this.ovalsPla = ovalsPla;
        this.logger = logger;

        try {
            aBisContext = ((LimLegacyService) logger).getCallerContext();
        } catch (Exception e) {
            aBisContext = new BisContext();
            ObjectProperty aObjectProperty = new ObjectProperty("APPLICATION", "LIM_BIS");
            ObjectProperty[] aProperties = new ObjectProperty[1];
            aProperties[0] = aObjectProperty;
            aBisContext.aProperties = aProperties;
        }
    }

    public RetrieveLocationForAddressReturn retrieveLocationForAddressAvsqub(
            Address address,
            ProviderLocationProperties[] locationPropertiesToGet,
            LongOpt maximumBasicAddressReturnLimit,
            LongOpt maximumUnitReturnLimit,
            StringOpt exchangeCode)
            throws InvalidData, AccessDenied, BusinessViolation, DataNotFound, SystemFailure, NotImplemented, ObjectNotFound
    {
        logger.log(LogEventId.AUDIT_TRAIL, "OVALS::retrieveLocationForAddress()|OVALS::doAddressValidation()|PRE");

        RetrieveLocationForAddressReturn result = null;

        AvsqubRequest request = mapToRequest(
                address,
                locationPropertiesToGet,
                maximumBasicAddressReturnLimit,
                maximumUnitReturnLimit,
                exchangeCode);

        AvsqubResponse response;

        try {
            response = ovals.requestAddressInfo(request);
        } catch (OvalsException e) {
            throw new SystemFailure(
                    aBisContext,
                    new ExceptionData(
                            ExceptionCode.ERR_LIM_OVALS_FATAL,
                            " " + e.toString(),
                            IDLUtil.toOpt("OVALS_GIS"),
                            (SeverityOpt) IDLUtil.toOpt(SeverityOpt.class, Severity.UnRecoverable)));
        }

        if (response == null) {
            throw new NullPointerException();
        }
        result = mapFromResponse(response);

        return result;
    }

    public AvsqubRequest mapToRequest(
            Address aAddress,
            ProviderLocationProperties[] locationPropertiesToGet,
            LongOpt aMaximumBasicAddressReturnLimit,
            LongOpt aMaximumUnitReturnLimit,
            StringOpt aExchangeCode)
    {
        AvsqubRequest request = new AvsqubRequest();
        // Map arguments to new Request
//        request.setRequestType(aAddress);

        // Setting up Request Type tree
        com.att.lms.bis.service.ovals.model.FieldedWirelineAddressInfo fieldedAddress = null;
        com.att.lms.bis.service.ovals.model.UnfieldedWirelineAddressInfo unfieldedAddress = null;

        if (aAddress.discriminator().value() == AddressChoice._FIELDED_ADDRESS) {

            fieldedAddress = new com.att.lms.bis.service.ovals.model.FieldedWirelineAddressInfo();

          try {
            if (aAddress.aFieldedAddress().aRoute.discriminator() == true)
              fieldedAddress.setRoute(aAddress.aFieldedAddress().aRoute.theValue());
          } catch (Exception e) {
            fieldedAddress.setRoute("");
          }

          try {
            if (aAddress.aFieldedAddress().aBox.discriminator() == true)
              fieldedAddress.setBox(aAddress.aFieldedAddress().aBox.theValue());
          } catch (Exception e) {
            fieldedAddress.setBox("");
          }

          try {
            if (aAddress.aFieldedAddress().aHouseNumberPrefix.discriminator() == true)
              fieldedAddress.setHouseNumberPrefix(
                  aAddress.aFieldedAddress().aHouseNumberPrefix.theValue());
          } catch (Exception e) {
            fieldedAddress.setHouseNumberPrefix("");
          }

          try {
            if (aAddress.aFieldedAddress().aHouseNumber.discriminator() == true)
              fieldedAddress.setHouseNumber(aAddress.aFieldedAddress().aHouseNumber.theValue());
          } catch (Exception e) {
            fieldedAddress.setHouseNumber("");
          }

          try {
            if (aAddress.aFieldedAddress().aAssignedHouseNumber.discriminator() == true)
              fieldedAddress.setAssignedHouseNumber(
                  aAddress.aFieldedAddress().aAssignedHouseNumber.theValue());
          } catch (Exception e) {
            fieldedAddress.setAssignedHouseNumber("");
          }

          try {
            if (aAddress.aFieldedAddress().aHouseNumberSuffix.discriminator() == true)
              fieldedAddress.setHouseNumberSuffix(
                  aAddress.aFieldedAddress().aHouseNumberSuffix.theValue());
          } catch (Exception e) {
            fieldedAddress.setHouseNumberSuffix("");
          }

          try {
            if (aAddress.aFieldedAddress().aStreetDirection.discriminator() == true)
              fieldedAddress.setStreetDirection(aAddress.aFieldedAddress().aStreetDirection.theValue());
          } catch (Exception e) {
            fieldedAddress.setStreetDirection("");
          }

          try {
            if (aAddress.aFieldedAddress().aStreetName.discriminator() == true)
              fieldedAddress.setStreetName(aAddress.aFieldedAddress().aStreetName.theValue());
          } catch (Exception e) {
            fieldedAddress.setStreetName("");
          }

          try {
            if (aAddress.aFieldedAddress().aStreetThoroughfare.discriminator() == true)
              fieldedAddress.setStreetThoroughfare(
                  aAddress.aFieldedAddress().aStreetThoroughfare.theValue());
          } catch (Exception e) {
            fieldedAddress.setStreetThoroughfare("");
          }

          try {
            if (aAddress.aFieldedAddress().aStreetNameSuffix.discriminator() == true)
              fieldedAddress.setStreetNameSuffix(
                  aAddress.aFieldedAddress().aStreetNameSuffix.theValue());
          } catch (Exception e) {
            fieldedAddress.setStreetNameSuffix("");
          }

          try {
            if (aAddress.aFieldedAddress().aCity.discriminator() == true)
              fieldedAddress.setCity(aAddress.aFieldedAddress().aCity.theValue());
          } catch (Exception e) {
            fieldedAddress.setCity("");
          }

          try {
            if (aAddress.aFieldedAddress().aState.discriminator() == true)
              fieldedAddress.setState(aAddress.aFieldedAddress().aState.theValue());
          } catch (Exception e) {
            fieldedAddress.setState("");
          }

          try {
            if (aAddress.aFieldedAddress().aPostalCode.discriminator() == true)
              fieldedAddress.setPostalCode(aAddress.aFieldedAddress().aPostalCode.theValue());
          } catch (Exception e) {
            fieldedAddress.setPostalCode("");
          }

          try {
            if (aAddress.aFieldedAddress().aPostalCodePlus4.discriminator() == true)
              fieldedAddress.setPostalCodePlus4(aAddress.aFieldedAddress().aPostalCodePlus4.theValue());
          } catch (Exception e) {
            fieldedAddress.setPostalCodePlus4("");
          }

          try {
            if (aAddress.aFieldedAddress().aCounty.discriminator() == true)
              fieldedAddress.setCounty(aAddress.aFieldedAddress().aCounty.theValue());
          } catch (Exception e) {
            fieldedAddress.setCounty("");
          }

          try {
            if (aAddress.aFieldedAddress().aCountry.discriminator() == true)
              fieldedAddress.setCountry(aAddress.aFieldedAddress().aCountry.theValue());
          } catch (Exception e) {
            fieldedAddress.setCountry("");
          }

          try {
            if (aAddress.aFieldedAddress().aStructureType.discriminator() == true)
              fieldedAddress.setStructureType(aAddress.aFieldedAddress().aStructureType.theValue());
          } catch (Exception e) {
            fieldedAddress.setStructureType("");
          }

          try {
            if (aAddress.aFieldedAddress().aStructureValue.discriminator() == true)
              fieldedAddress.setStructureValue(aAddress.aFieldedAddress().aStructureValue.theValue());
          } catch (Exception e) {
            fieldedAddress.setStructureValue("");
          }

          try {
            if (aAddress.aFieldedAddress().aLevelType.discriminator() == true)
              fieldedAddress.setLevelType(aAddress.aFieldedAddress().aLevelType.theValue());
          } catch (Exception e) {
            fieldedAddress.setLevelType("");
          }

          try {
            if (aAddress.aFieldedAddress().aLevelValue.discriminator() == true)
              fieldedAddress.setLevelValue(aAddress.aFieldedAddress().aLevelValue.theValue());
          } catch (Exception e) {
            fieldedAddress.setLevelValue("");
          }

          try {
            if (aAddress.aFieldedAddress().aUnitType.discriminator() == true)
              fieldedAddress.setUnitType(aAddress.aFieldedAddress().aUnitType.theValue());
          } catch (Exception e) {
            fieldedAddress.setUnitType("");
          }

          try {
            if (aAddress.aFieldedAddress().aUnitValue.discriminator() == true)
              fieldedAddress.setUnitValue(aAddress.aFieldedAddress().aUnitValue.theValue());
          } catch (Exception e) {
            fieldedAddress.setUnitValue("");
          }

          try {
            if (aAddress.aFieldedAddress().aOriginalStreetDirection.discriminator() == true)
              fieldedAddress.setOriginalStreetDirection(
                  aAddress.aFieldedAddress().aOriginalStreetDirection.theValue());
          } catch (Exception e) {
            fieldedAddress.setOriginalStreetDirection("");
          }

          try {
            if (aAddress.aFieldedAddress().aOriginalStreetNameSuffix.discriminator() == true)
              fieldedAddress.setOriginalStreetNameSuffix(
                  aAddress.aFieldedAddress().aOriginalStreetNameSuffix.theValue());
          } catch (Exception e) {
            fieldedAddress.setOriginalStreetNameSuffix("");
          }

          try {
            if (aAddress.aFieldedAddress().aCassAdditionalInfo.discriminator() == true)
              fieldedAddress.setCassAdditionalInfo(
                  aAddress.aFieldedAddress().aCassAdditionalInfo.theValue());
          } catch (Exception e) {
            fieldedAddress.setCassAdditionalInfo("");
          }

          try {
            if (aAddress.aFieldedAddress().aAdditionalInfo.discriminator() == true)
              fieldedAddress.setAdditionalInfo(aAddress.aFieldedAddress().aAdditionalInfo.theValue());
          } catch (Exception e) {
            fieldedAddress.setAdditionalInfo("");
          }

          try {
            if (aAddress.aFieldedAddress().aBusinessName.discriminator() == true)
              fieldedAddress.setBusinessName(aAddress.aFieldedAddress().aBusinessName.theValue());
          } catch (Exception e) {
            fieldedAddress.setBusinessName("");
          }

          try {
            if (aAddress.aFieldedAddress().aCountryCode.discriminator() == true)
              fieldedAddress.setCountryCode(aAddress.aFieldedAddress().aCountryCode.theValue());
          } catch (Exception e) {
            fieldedAddress.setCountryCode("");
          }

          try {
            if (aAddress.aFieldedAddress().aCityCode.discriminator() == true)
              fieldedAddress.setCityCode(aAddress.aFieldedAddress().aCityCode.theValue());
          } catch (Exception e) {
            fieldedAddress.setCityCode("");
          }

          try {
            if (aAddress.aFieldedAddress().aServiceLocationName.discriminator() == true)
              fieldedAddress.setServiceLocationName(
                  aAddress.aFieldedAddress().aServiceLocationName.theValue());
          } catch (Exception e) {
            fieldedAddress.setServiceLocationName("");
          }

          try {
            if (aAddress.aFieldedAddress().aAddressId.discriminator() == true)
              fieldedAddress.setAddressId(aAddress.aFieldedAddress().aAddressId.theValue());
          } catch (Exception e) {
            fieldedAddress.setAddressId("");
          }

          try {
            if (aAddress.aFieldedAddress().aAliasName.discriminator() == true)
              fieldedAddress.setAliasName(aAddress.aFieldedAddress().aAliasName.theValue());
          } catch (Exception e) {
            fieldedAddress.setAliasName("");
          }

          try {
            if (aAddress.aFieldedAddress().aAttention.discriminator() == true)
              fieldedAddress.setAttention(aAddress.aFieldedAddress().aAttention.theValue());
          } catch (Exception e) {
            fieldedAddress.setAttention("");
          }

          try {
            if (aExchangeCode.discriminator() == true)
              fieldedAddress.setExchangeCode(aExchangeCode.theValue());
          } catch (Exception e) {
            fieldedAddress.setExchangeCode("");
          }
          String auxiliaryAddressLine = "";
          fieldedAddress.getAuxiliaryAddressLines().add(auxiliaryAddressLine);
          String customerName = "";
          fieldedAddress.setCustomerName(customerName);
          Boolean primaryStreetUnnamedIndicator = null;
          fieldedAddress.setPrimaryStreetUnnamedIndicator(primaryStreetUnnamedIndicator);
          //        fieldedAddress.setExchangeCode(aExchangeCode.theValue());
          String alternateStreetDirection = "";
          fieldedAddress.setAlternateStreetDirection(alternateStreetDirection);
          String alternateStreetName = "";
          fieldedAddress.setAlternateStreetName(alternateStreetName);
          String alternateStreetThoroughfare = "";
          fieldedAddress.setAlternateStreetThoroughfare(alternateStreetThoroughfare);
          String alternateStreetNameSuffix = "";
          fieldedAddress.setAlternateStreetNameSuffix(alternateStreetNameSuffix);
          String alternateCommunity = "";
          fieldedAddress.setAlternateCommunity(alternateCommunity);
          String alternateCommunity2 = "";
          fieldedAddress.setAlternateCommunity2(alternateCommunity2);
          String abbreviatedCity = "";
          fieldedAddress.setAbbreviatedCity(abbreviatedCity);
          String alternateDescriptiveName = "";
          fieldedAddress.setAlternateDescriptiveName(alternateDescriptiveName);
          String addressRemark = "";
          fieldedAddress.getAddressRemark().add(addressRemark);
          String additionalName = "";
          fieldedAddress.setAdditionalName(additionalName);
          String serviceId = "";
          fieldedAddress.setServiceId(serviceId);
          String singleLineStandardizedAddress = "";
          fieldedAddress.setSingleLineStandardizedAddress(singleLineStandardizedAddress);
          String primaryAddressLine = "";
          fieldedAddress.setPrimaryAddressLine(primaryAddressLine);
          String secondaryAddressLine = "";
          fieldedAddress.setSecondaryAddressLine(secondaryAddressLine);
          String urbanizationCode = "";
          fieldedAddress.setUrbanizationCode(urbanizationCode);
          String postalBox = "";
          fieldedAddress.setPostalBox(postalBox);
          String cityAbbreviatedName = "";
          fieldedAddress.setCityAbbreviatedName(cityAbbreviatedName);
          String countyFIPS = "";
          fieldedAddress.setCountyFIPS(countyFIPS);
    } else {

            unfieldedAddress = new com.att.lms.bis.service.ovals.model.UnfieldedWirelineAddressInfo();
            aAddress.set___aUnfieldedAddress(LimLegacyService.parseUnfieldedAddress(aAddress.aUnfieldedAddress(),aBisContext));

          try {
            if (aAddress.aUnfieldedAddress().aAddressLines.discriminator() == true)
              unfieldedAddress.setAddressLines(
                  Arrays.asList(aAddress.aUnfieldedAddress().aAddressLines.theValue()));
          } catch (Exception e) {
            unfieldedAddress.setAddressLines(new ArrayList<>());
          }
          try {
            if (aAddress.aUnfieldedAddress().aCity.discriminator() == true)
              unfieldedAddress.setCity(aAddress.aUnfieldedAddress().aCity.theValue());
          } catch (Exception e) {
            unfieldedAddress.setCity("");
          }

          try {
            if (aAddress.aUnfieldedAddress().aState.discriminator() == true)
              unfieldedAddress.setState(aAddress.aUnfieldedAddress().aState.theValue());
          } catch (Exception e) {
            unfieldedAddress.setState("");
          }

          try {
            if (aAddress.aUnfieldedAddress().aPostalCode.discriminator() == true)
              unfieldedAddress.setPostalCode(aAddress.aUnfieldedAddress().aPostalCode.theValue());
          } catch (Exception e) {
            unfieldedAddress.setPostalCode("");
          }

          try {
            if (aAddress.aUnfieldedAddress().aPostalCodePlus4.discriminator() == true)
              unfieldedAddress.setPostalCodePlus4(
                  aAddress.aUnfieldedAddress().aPostalCodePlus4.theValue());
          } catch (Exception e) {
            unfieldedAddress.setPostalCodePlus4("");
          }

          try {
            if (aAddress.aUnfieldedAddress().aCounty.discriminator() == true)
              unfieldedAddress.setCounty(aAddress.aUnfieldedAddress().aCounty.theValue());
          } catch (Exception e) {
            unfieldedAddress.setCounty("");
          }

          try {
            if (aAddress.aUnfieldedAddress().aCountry.discriminator() == true)
              unfieldedAddress.setCountry(aAddress.aUnfieldedAddress().aCountry.theValue());
          } catch (Exception e) {
            unfieldedAddress.setCountry("");
          }

            unfieldedAddress.setStructureType("");
            unfieldedAddress.setStructureValue("");
            unfieldedAddress.setLevelType("");
            unfieldedAddress.setLevelValue("");
            unfieldedAddress.setUnitType("");
            unfieldedAddress.setUnitValue("");

          try {
            if (aAddress.aUnfieldedAddress().aAdditionalInfo.discriminator() == true)
              unfieldedAddress.setAdditionalInfo(
                  aAddress.aUnfieldedAddress().aAdditionalInfo.theValue());
          } catch (Exception e) {
            unfieldedAddress.setAdditionalInfo("");
          }

          try {
            if (aAddress.aUnfieldedAddress().aBusinessName.discriminator() == true)
              unfieldedAddress.setBusinessName(aAddress.aUnfieldedAddress().aBusinessName.theValue());
          } catch (Exception e) {
            unfieldedAddress.setBusinessName("");
          }

          try {
            if (aAddress.aUnfieldedAddress().aCountryCode.discriminator() == true)
              unfieldedAddress.setCountryCode(aAddress.aUnfieldedAddress().aCountryCode.theValue());
          } catch (Exception e) {
            unfieldedAddress.setCountryCode("");
          }

          try {
            if (aAddress.aUnfieldedAddress().aCityCode.discriminator() == true)
              unfieldedAddress.setCityCode(aAddress.aUnfieldedAddress().aCityCode.theValue());
          } catch (Exception e) {
            unfieldedAddress.setCityCode("");
          }

          try {
            if (aAddress.aUnfieldedAddress().aServiceLocationName.discriminator() == true)
              unfieldedAddress.setServiceLocationName(
                  aAddress.aUnfieldedAddress().aServiceLocationName.theValue());
          } catch (Exception e) {
            unfieldedAddress.setServiceLocationName("");
          }

          try {
            if (aAddress.aUnfieldedAddress().aAddressId.discriminator() == true)
              unfieldedAddress.setAddressId(aAddress.aUnfieldedAddress().aAddressId.theValue());
          } catch (Exception e) {
            unfieldedAddress.setAddressId("");
          }

          try {
            if (aAddress.aUnfieldedAddress().aAliasName.discriminator() == true)
              unfieldedAddress.setAliasName(aAddress.aUnfieldedAddress().aAliasName.theValue());
          } catch (Exception e) {
            unfieldedAddress.setAliasName("");
          }

          try {
            if (aAddress.aUnfieldedAddress().aAttention.discriminator() == true)
              unfieldedAddress.setAttention(aAddress.aUnfieldedAddress().aAttention.theValue());
          } catch (Exception e) {

            unfieldedAddress.setAttention("");
          }
          String unfieldedCustomerName = "";
          unfieldedAddress.setCustomerName(unfieldedCustomerName);
          String unfieldedUrbanizationCode = "";
          unfieldedAddress.setUrbanizationCode(unfieldedUrbanizationCode);
          String unfieldedCountyFIPS = "";
          unfieldedAddress.setCountyFIPS(unfieldedCountyFIPS);
        }

        com.att.lms.bis.service.ovals.model.AddressGeocodeCoordinatesInfo geoCode = new com.att.lms.bis.service.ovals.model.AddressGeocodeCoordinatesInfo();
        String elevation = "";
        geoCode.setElevation(elevation);
        Double latitude = Double.valueOf(0);
        geoCode.setLatitude(latitude);
        Double longitude = Double.valueOf(0);
        geoCode.setLongitude(longitude);
        String addressType = "";
        AvsqubRequest.AddressValidationServiceQualificationUnifiedBillRequest.RequestType.UserLocation.RequestedLocation.RequestedUserLocation.LocationDetails locationDetails = new AvsqubRequest.AddressValidationServiceQualificationUnifiedBillRequest.RequestType.UserLocation.RequestedLocation.RequestedUserLocation.LocationDetails();
        locationDetails.setFieldedAddress(fieldedAddress);
        locationDetails.setUnfieldedAddress(unfieldedAddress);
        String primaryNpanxx = "";

        AvsqubRequest.AddressValidationServiceQualificationUnifiedBillRequest.RequestType.UserLocation.RequestedLocation.RequestedUserLocation requestedUserLocation = new AvsqubRequest.AddressValidationServiceQualificationUnifiedBillRequest.RequestType.UserLocation.RequestedLocation.RequestedUserLocation();
        requestedUserLocation.setGEOCode(geoCode);
        requestedUserLocation.setAddressType(addressType);
        requestedUserLocation.setLocationDetails(locationDetails);
        requestedUserLocation.setPrimaryNpanxx(primaryNpanxx);



        AvsqubRequest.AddressValidationServiceQualificationUnifiedBillRequest.RequestType.UserLocation.RequestedLocation requestedLocation = new AvsqubRequest.AddressValidationServiceQualificationUnifiedBillRequest.RequestType.UserLocation.RequestedLocation();
        requestedLocation.setRequestedUserLocation(requestedUserLocation);
        String globalLocationId = "";
        requestedLocation.setGlobalLocationId(globalLocationId);


        AvsqubRequest.AddressValidationServiceQualificationUnifiedBillRequest.RequestType.UserLocation userLocation = new AvsqubRequest.AddressValidationServiceQualificationUnifiedBillRequest.RequestType.UserLocation();
        userLocation.setRequestedLocation(requestedLocation);
        String addressValidationType = "";
        userLocation.setAddressValidationType(addressValidationType);
        Integer maxAlternateLocations = null;
        userLocation.setMaxAlternateLocations(maxAlternateLocations);
        String nearbyType = "";
        userLocation.setNearbyType(nearbyType);

        AvsqubRequest.AddressValidationServiceQualificationUnifiedBillRequest.RequestType requestType = new AvsqubRequest.AddressValidationServiceQualificationUnifiedBillRequest.RequestType();
        String userTelephoneNumber = "";
        requestType.setUserTelephoneNumber(userTelephoneNumber);
        requestType.setUserLocation(userLocation);
        AvsqubRequest.AddressValidationServiceQualificationUnifiedBillRequest.RequestType.UserZip userZip = new AvsqubRequest.AddressValidationServiceQualificationUnifiedBillRequest.RequestType.UserZip();
        String zipCode = "";
        userZip.setZipCode(zipCode);
        String zipExtension = "";
        userZip.setZipExtension(zipExtension);
        requestType.setUserZip(userZip);
        AvsqubRequest.AddressValidationServiceQualificationUnifiedBillRequest.RequestType.UserBan userBan = new AvsqubRequest.AddressValidationServiceQualificationUnifiedBillRequest.RequestType.UserBan();
        String ban = "";
        userBan.setBan(ban);
        requestType.setUserBan(userBan);



        // Setting up Additional Request Data Tree
        Boolean bandwidthProfileDataIndicator = null;
        Boolean bandwidthDataIndicator = null;
        Boolean unNormalizedBandwithIndicator = null;
        Boolean adslNetworkDataIndicator = null;
        Boolean attDSLIndicator = null;
        Boolean attSwitchedEthernetServiceQualificationIndicator = null;
        Boolean singleDispatchIndicator = null;
        String productType = "";
        Boolean overrideAddressIndicator = null;
        AvsqubRequest.AddressValidationServiceQualificationUnifiedBillRequest.AdditionalRequestData.DTVValidationOptions dtvValidationOptions = new AvsqubRequest.AddressValidationServiceQualificationUnifiedBillRequest.AdditionalRequestData.DTVValidationOptions();
        Boolean dtvIndicator = false;
        dtvValidationOptions.setDtvIndicator(dtvIndicator);
        Boolean checkServiceIndicator = false;
        dtvValidationOptions.setCheckServiceIndicator(checkServiceIndicator);
        String dealerCode = "";
        dtvValidationOptions.setDealerCode(dealerCode);
        Boolean d2LiteInfoIndicator = null;
        dtvValidationOptions.setD2LiteInfoIndicator(d2LiteInfoIndicator);
        Boolean exactMatchOnlyIndicator = null;
        dtvValidationOptions.setExactMatchOnlyIndicator(exactMatchOnlyIndicator);
        Boolean supplementalCheckOverrideIndicator = null;
        dtvValidationOptions.setSupplementalCheckOverrideIndicator(supplementalCheckOverrideIndicator);
        AvsqubRequest.AddressValidationServiceQualificationUnifiedBillRequest.AdditionalRequestData.USPSDeliveryPointValidationOptions uspsDeliveryPointValidationOptions = new AvsqubRequest.AddressValidationServiceQualificationUnifiedBillRequest.AdditionalRequestData.USPSDeliveryPointValidationOptions();
        Integer maximumAlternativeAddress = 0;
        uspsDeliveryPointValidationOptions.setMaximumAlternativeAddresses(maximumAlternativeAddress);

        String maximumCASSAddressLineLength = "40";

        ObjectProperty[] props = aBisContext.getAProperties();
        for (ObjectProperty prop : props) {
            if ("MaxCassCharPerLine".equals(prop.getATag())) {
                maximumCASSAddressLineLength = prop.getAValue();
                break;
            }
        }

        uspsDeliveryPointValidationOptions.setMaximumCASSAddressLineLength(maximumCASSAddressLineLength);
        Boolean taxationGeocodeIndicator = null;
        Boolean speedCalculateIndicator = null;
        Boolean abfLGQualificationIndicator = null;
        Boolean ponNetworkDataIndicator = null;
        Boolean instantHSIAOnIndicator = null;

        AvsqubRequest.AddressValidationServiceQualificationUnifiedBillRequest.AdditionalRequestData additionalRequestData = new AvsqubRequest.AddressValidationServiceQualificationUnifiedBillRequest.AdditionalRequestData();
        additionalRequestData.setBandwidthProfileDataIndicator(bandwidthProfileDataIndicator);
        additionalRequestData.setBandwidthDataIndicator(bandwidthDataIndicator);
        additionalRequestData.setUnNormalizedBandwithIndicator(unNormalizedBandwithIndicator);
        additionalRequestData.setAdslNetworkDataIndicator(adslNetworkDataIndicator);
        additionalRequestData.setAttDSLIndicator(attDSLIndicator);
        additionalRequestData.setAttSwitchedEthernetServiceQualificationIndicator(attSwitchedEthernetServiceQualificationIndicator);
        additionalRequestData.setSingleDispatchIndicator(singleDispatchIndicator);
        additionalRequestData.setProductType(productType);
        additionalRequestData.setOverrideAddressIndicator(overrideAddressIndicator);
        additionalRequestData.setDTVValidationOptions(dtvValidationOptions);
        additionalRequestData.setUSPSDeliveryPointValidationOptions(uspsDeliveryPointValidationOptions);
        additionalRequestData.setTaxationGeocodeIndicator(taxationGeocodeIndicator);
        additionalRequestData.setSpeedCalculateIndicator(speedCalculateIndicator);
        additionalRequestData.setAbfLGQualificationIndicator(abfLGQualificationIndicator);
        additionalRequestData.setPonNetworkDataIndicator(ponNetworkDataIndicator);
        additionalRequestData.setInstantHSIAOnIndicator(instantHSIAOnIndicator);



        // Setting up Request From tree
        AvsqubRequest.AddressValidationServiceQualificationUnifiedBillRequest.RequestFrom requestFrom = new AvsqubRequest.AddressValidationServiceQualificationUnifiedBillRequest.RequestFrom();
        String agentId = "";
        requestFrom.setAgentId(agentId);
        String storeId = "";
        requestFrom.setStoreId(storeId);
        String applicationName = "";
        requestFrom.setApplicationName(applicationName);


//        AvsqubRequest.RequestType requestType = new AvsqubRequest.RequestType();
//        AvsqubRequest.RequestType.UserLocation location = new AvsqubRequest.RequestType.UserLocation();
//        AvsqubRequest.AdditionalRequestData additionalRequestData;
//        AvsqubRequest.RequestFrom requestFrom;

        AvsqubRequest.AddressValidationServiceQualificationUnifiedBillRequest addressValidationServiceQualificationUnifiedBillRequest = new AvsqubRequest.AddressValidationServiceQualificationUnifiedBillRequest();
        addressValidationServiceQualificationUnifiedBillRequest.setRequestType(requestType);
        addressValidationServiceQualificationUnifiedBillRequest.setAdditionalRequestData(additionalRequestData);
        addressValidationServiceQualificationUnifiedBillRequest.setRequestFrom(requestFrom);
        request.setAddressValidationServiceQualificationUnifiedBillRequest(addressValidationServiceQualificationUnifiedBillRequest);
        return request;
    }

    private RetrieveLocationForAddressReturn mapFromResponse(AvsqubResponse response)
            throws InvalidData, AccessDenied, BusinessViolation, DataNotFound, SystemFailure, NotImplemented, ObjectNotFound
    {
        RetrieveLocationForAddressReturn result = new RetrieveLocationForAddressReturn();

        // map BisContext

        List<AvsqubResponse.ResultResponse> responseList = response.getAddressValidationServiceQualificationUnifiedBillResponse().getResultResponse();

        AvsqubResponse.ResultResponse postalResponse = null;

        for (int i = 0; i < responseList.size(); i++) {
            AvsqubResponse.ResultResponse resultResponse = responseList.get(i);
            if (resultResponse.getResponseApplicationName().equals("POSTAL")) {
                postalResponse = resultResponse;
                break;
            }
        }

        if (postalResponse == null) {
            throw new SystemFailure(
                    aBisContext,
                    new ExceptionData(
                            ExceptionCode.ERR_LIM_OVALS,
                            "Error in LIM OVALS GIS",
                            IDLUtil.toOpt("OVALS_GIS"),
                            (SeverityOpt) IDLUtil.toOpt(SeverityOpt.class, Severity.UnRecoverable)));
        } else if (!postalResponse.getCode().startsWith("4S") && !postalResponse.getCode().startsWith("3S")) {

            AddressMatchResult addressMatchResult = new AddressMatchResult();

            SubmittedAddressException submittedAddressException = new SubmittedAddressException();
            submittedAddressException.setACode(toStringOpt(postalResponse.getCode()));
            submittedAddressException.setADescription(toStringOpt(postalResponse.getDescription()));

            SubmittedAddressException[] submittedAddressExceptions = new SubmittedAddressException[1];
            submittedAddressExceptions[0] = submittedAddressException;


            SubmittedAddressExceptionSeqOpt aSubmittedAddressExceptionSeqOpt = new SubmittedAddressExceptionSeqOpt();
            aSubmittedAddressExceptionSeqOpt.theValue(submittedAddressExceptions);

            AlternativeAddressResult aAlternativeAddressResult = new AlternativeAddressResult();
            aAlternativeAddressResult.setASubmittedAddressExceptions(aSubmittedAddressExceptionSeqOpt);

            addressMatchResult.set___aAlternativeAddressResult(aAlternativeAddressResult);

            result.setAContext(aBisContext);
            result.setAAddressMatchResult(addressMatchResult);
            return result;
        }

        AvsqubResponse.LocationMatchAvailabilityResults locationMatchAvailabilityResults = response.getAddressValidationServiceQualificationUnifiedBillResponse().getLocationMatchAvailabilityResults();
        AvsqubResponse.SeviceOfferingFromLocation serviceOfferingFromLocation = response.getAddressValidationServiceQualificationUnifiedBillResponse().getSeviceOfferingFromLocation();
        List<AvsqubResponse.USPSDeliveryPointValidation> uspsDeliveryPointValidation = response.getAddressValidationServiceQualificationUnifiedBillResponse().getUSPSDeliveryPointValidation();
        AvsqubResponse.DTVLocationProperties dtvLocationProperties = response.getAddressValidationServiceQualificationUnifiedBillResponse().getDTVLocationProperties();
        List<AvsqubResponse.ResultResponse> resultResponse = response.getAddressValidationServiceQualificationUnifiedBillResponse().getResultResponse();

        // map AddressMatchResult
        AddressMatchResult aAddressMatchResult = new AddressMatchResult();

        /// map AddressMatchResult.Location
        Location aLocation = new Location();

        StringOpt aLocationId = new StringOpt();
        try {
            aLocationId.theValue(locationMatchAvailabilityResults.getExactLocationAvailabilityResults().getLocation().getGlobalLocationId());
        } catch (Exception e) {
        }
        ProviderLocationProperty[] aProviderLocationProperties = new ProviderLocationProperty[1];
        AddressOpt aPostalAddress = new AddressOpt();
//        aPostalAddress.theValue(respLocProps.getPos());
        AddressOpt aServiceAddress = new AddressOpt();
        aServiceAddress.theValue(null);
//        aServiceAddress.theValue(respLocProps.getServ());
        AddressOpt aE911Address = new AddressOpt();
        aE911Address.theValue(null);
//        aE911Address.theValue(respLocProps.getE911());
        AddressOpt aSwitchSuperPopAddress = new AddressOpt();
        aSwitchSuperPopAddress.theValue(new Address());
//        aSwitchSuperPopAddress.theValue(respLocProps.getProviderName());
        StringOpt aProviderName = new StringOpt();
        StringOpt aAddressMatchCode = new StringOpt();
        StringOpt aAddressMatchCodeDescription = new StringOpt();
        StringOpt aCoSwitchSuperPop = new StringOpt();
        StringOpt aCentralOfficeCode = new StringOpt();
        StringOpt aCentralOfficeType = new StringOpt();
        StringOpt aCommunityName = new StringOpt();
        StringOpt aDomSwitchPop = new StringOpt();
        StringOpt aE911Exempt = new StringOpt();
        StringOpt aE911NonRecurringCharge = new StringOpt();
        StringOpt aE911Surcharge = new StringOpt();
        StringOpt aExchangeCode = new StringOpt();
        StringOpt aExco = new StringOpt();
        StringOpt aGeoCode = new StringOpt();
        StringOpt aHorizontalCoordinate = new StringOpt();
        StringOpt aLataCode = new StringOpt();
        StringOpt aLataName = new StringOpt();
        StringOpt aLatitude = new StringOpt();
        StringOpt aLongitude = new StringOpt();
        StringOpt aLocalProviderAbbreviatedName = new StringOpt();
        StringOpt aLocalProviderExchangeCode = new StringOpt();
        StringOpt aLocalProviderName = new StringOpt();
        StringOpt aLocalProviderNumber = new StringOpt();
        StringOpt aLocalProviderServingOfficeCode = new StringOpt();
        StringOpt aMailingBarCodeSuffix = new StringOpt();
        StringOpt aMsaCode = new StringOpt();
        StringOpt aMsaName = new StringOpt();
        StringOpt aNearestDistanceColoToCo = new StringOpt();
        StringOpt aNearestDistanceSuperPopToCo = new StringOpt();
        StringOpt aNearestSbcColo = new StringOpt();
        StringOpt aNearestSbcCoSuperPop = new StringOpt();
        StringOpt aNearestSbcCoWirecenter = new StringOpt();
        StringOpt aOwnedWiring = new StringOpt();
        StringOpt aPostalCarrierCode = new StringOpt();
        StringOpt aPrimaryDirectoryCode = new StringOpt();
        StringOpt aPrimaryNpaNxx = new StringOpt();
        StringOpt aQuickDialTone = new StringOpt();
        StringOpt aQuickDialToneNumber = new StringOpt();
        StringOpt aRateCenterCode = new StringOpt();
        StringOpt aRateZone = new StringOpt();
        StringOpt aRateZoneBandCode = new StringOpt();
        StringOpt aSagNpa = new StringOpt();
        StringOpt aSagWireCenter = new StringOpt();
        StringOpt aSbcColoLsoCode = new StringOpt();
        StringOpt aSbcColoWirecenter = new StringOpt();
        StringOpt aSbcServingOfficeCode = new StringOpt();
        StringOpt aSbcServingOfficeWirecenter = new StringOpt();
        StringOpt aServingAreaDescription = new StringOpt();
        StringOpt aStreetAddressGuideArea = new StringOpt();
        StringOpt aSurcharge4Percent = new StringOpt();
        StringOpt aSurcharge16Percent = new StringOpt();
        StringOpt aSwitchDataSuperPop = new StringOpt();
        StringOpt aSwitchVoiceSuperPop = new StringOpt();
        StringOpt aTarCode = new StringOpt();
        StringOpt aTelephoneNumber = new StringOpt();
        StringOpt aVerticalCoordinate = new StringOpt();
        StringOpt aWorkingServiceOnLocation = new StringOpt();
        StringOpt aEcktId = new StringOpt();
        StringOpt aCustomerPremiseIndicator = new StringOpt();
        AddressOpt aSAGAddress = new AddressOpt();
        StringOpt aBuildingType = new StringOpt();
        StringOpt aLegalEntity = new StringOpt();
        StringOpt aVideoHubOffice = new StringOpt();
        StringOpt aSmartmoves = new StringOpt();
        StringOpt aServingNetworkType = new StringOpt();
        StringOpt aLocationType = new StringOpt();
        StringOpt aCityStatePostalCodeValid = new StringOpt();
        StringOpt aPublicSafetyAnsweringPointId = new StringOpt();
        StringOpt aCountyId = new StringOpt();
        StringOpt aExceptionCode = new StringOpt();
        StringOpt aExceptionDescription = new StringOpt();
        ExtensionPropertySeqOpt aExtensions = new ExtensionPropertySeqOpt();
        ExtensionProperty[] aExtensionProperties = new ExtensionProperty[1];
        ExtensionProperty aExtensionProperty = null;



        try {
        	com.att.lms.bis.service.ovals.model.FiberServiceProviderLocationPropertyInfo respLocProps = locationMatchAvailabilityResults.getExactLocationAvailabilityResults().getLocationProperties().getLocationProperties();
            aProviderName.theValue(respLocProps.getProviderName());
            aAddressMatchCode.theValue(respLocProps.getAddressMatchCode());
            aAddressMatchCodeDescription.theValue(respLocProps.getAddressMatchCodeDescription());
            aCoSwitchSuperPop.theValue(respLocProps.getCoSwitchSuperPop());
            aCentralOfficeCode.theValue(respLocProps.getCentralOfficeCode());
            aCentralOfficeType.theValue(respLocProps.getCentralOfficeType());
            aCommunityName.theValue(respLocProps.getCommunityName());
            aDomSwitchPop.theValue(respLocProps.getDomSwitchPop());
            aE911Exempt.theValue(respLocProps.getE911Exempt());
            aE911NonRecurringCharge.theValue(respLocProps.getE911NonRecurringCharge());
            aE911Surcharge.theValue(respLocProps.getE911Surcharge());
            aExchangeCode.theValue(respLocProps.getExchangeCode());
            aExco.theValue(respLocProps.getExco());
            aGeoCode.theValue(respLocProps.getGeoCode());
            aHorizontalCoordinate.theValue(respLocProps.getHorizontalCoordinate());
            aLataCode.theValue(respLocProps.getLataCode());
            aLataName.theValue(respLocProps.getLataCode());
            aLatitude.theValue(respLocProps.getLatitude());
            aLongitude.theValue(respLocProps.getLongitude());
            aLocalProviderAbbreviatedName.theValue(respLocProps.getLocalProviderAbbreviatedName());
            aLocalProviderExchangeCode.theValue(respLocProps.getLocalProviderExchangeCode());
            aLocalProviderName.theValue(respLocProps.getLocalProviderName());
            aLocalProviderNumber.theValue(respLocProps.getLocalProviderNumber());
            aLocalProviderServingOfficeCode.theValue(respLocProps.getLocalProviderServingOfficeCode());
            aMailingBarCodeSuffix.theValue(respLocProps.getMailingBarCodeSuffix());
            aMsaCode.theValue(respLocProps.getMsaCode());
            aMsaName.theValue(respLocProps.getMsaName());
            aNearestDistanceColoToCo.theValue(respLocProps.getNearestDistanceColoToCo());
            aNearestDistanceSuperPopToCo.theValue(respLocProps.getNearestDistanceSuperPopToCo());
            aNearestSbcColo.theValue(respLocProps.getNearestSbcColo());
            aNearestSbcCoSuperPop.theValue(respLocProps.getNearestSbcCoSuperPop());
            aNearestSbcCoWirecenter.theValue(respLocProps.getNearestSbcCoWirecenter());
            aOwnedWiring.theValue(respLocProps.getOwnedWiring());
            aPostalCarrierCode.theValue(respLocProps.getPostalCarrierCode());
            aPrimaryDirectoryCode.theValue(respLocProps.getPrimaryDirectoryCode());
            aPrimaryNpaNxx.theValue(respLocProps.getPrimaryNpaNxx());
            aQuickDialTone.theValue(respLocProps.getQuickDialTone());
            aQuickDialToneNumber.theValue(respLocProps.getQuickDialToneNumber());
            aRateCenterCode.theValue(respLocProps.getRateCenterCode());
            aRateZone.theValue(respLocProps.getRateZone());
            aRateZoneBandCode.theValue(respLocProps.getRateZoneBandCode());
            aSagNpa.theValue(respLocProps.getSagNpa());
            aSagWireCenter.theValue(respLocProps.getSagWireCenter());
            aSbcColoLsoCode.theValue(respLocProps.getSbcColoLsoCode());
            aSbcColoWirecenter.theValue(respLocProps.getSbcColoWirecenter());
            aSbcServingOfficeCode.theValue(respLocProps.getSbcServingOfficeCode());
            aSbcServingOfficeWirecenter.theValue(respLocProps.getSbcServingOfficeWirecenter());
            aServingAreaDescription.theValue(respLocProps.getServingAreaDescription());
            aStreetAddressGuideArea.theValue(respLocProps.getStreetAddressGuideArea());
            aSurcharge4Percent.theValue(respLocProps.getSurcharge4Percent());
            aSurcharge16Percent.theValue(respLocProps.getSurcharge16Percent());
            aSwitchDataSuperPop.theValue(respLocProps.getSwitchDataSuperPop());
            aSwitchVoiceSuperPop.theValue(respLocProps.getSwitchVoiceSuperPop());
            aTarCode.theValue(respLocProps.getTarCode());
            aTelephoneNumber.theValue(respLocProps.getTelephoneNumber());
            aVerticalCoordinate.theValue(respLocProps.getVerticalCoordinate());
            aWorkingServiceOnLocation.theValue(respLocProps.getWorkingServiceOnLocation());
            aEcktId.theValue(respLocProps.getEcktId());
            aCustomerPremiseIndicator.theValue(respLocProps.getCustomerPremiseIndicator());
            aSAGAddress.theValue(new Address());
//        aSAGAddress.theValue(respLocProps.getSAGAddress());
            aBuildingType.theValue(respLocProps.getBuildingType());
            aLegalEntity.theValue(respLocProps.getLegalEntity());
            aLegalEntity.theValue(respLocProps.getVideoHubOffice());
            aSmartmoves.theValue(respLocProps.getSmartmoves());
            aServingNetworkType.theValue(respLocProps.getServingNetworkType());
            aLocationType.theValue(respLocProps.getLocationType());
            aCityStatePostalCodeValid.theValue(respLocProps.getCityStatePostalCodeValid());
            aPublicSafetyAnsweringPointId.theValue(respLocProps.getPublicSafetyAnsweringPointId());
            aCountyId.theValue(respLocProps.getCountyId());
            aExceptionCode.theValue(respLocProps.getExceptionCode());
            aExceptionDescription.theValue(respLocProps.getExceptionDescription());
            try {
                aExtensionProperty = new ExtensionProperty(respLocProps.getExtensions().get(0).getName(), respLocProps.getExtensions().get(0).getValue());
            } catch (Exception e) {
                aExtensionProperty = new ExtensionProperty();
            }
        } catch (Exception e) {

        }



        aExtensionProperties[0] = aExtensionProperty;
        aExtensions.theValue(aExtensionProperties);
        ProviderLocationProperty aProviderLocationProperty = new ProviderLocationProperty(aProviderName, aPostalAddress, aServiceAddress, aE911Address, aSwitchSuperPopAddress, aAddressMatchCode, aAddressMatchCodeDescription, aCoSwitchSuperPop, aCentralOfficeCode, aCentralOfficeType, aCommunityName, aDomSwitchPop, aE911Exempt, aE911NonRecurringCharge, aE911Surcharge, aExchangeCode, aExco, aGeoCode, aHorizontalCoordinate, aLataCode, aLataName, aLatitude, aLongitude, aLocalProviderAbbreviatedName, aLocalProviderExchangeCode, aLocalProviderName, aLocalProviderNumber, aLocalProviderServingOfficeCode, aMailingBarCodeSuffix, aMsaCode, aMsaName, aNearestDistanceColoToCo, aNearestDistanceSuperPopToCo, aNearestSbcColo, aNearestSbcCoSuperPop, aNearestSbcCoWirecenter, aOwnedWiring, aPostalCarrierCode, aPrimaryDirectoryCode, aPrimaryNpaNxx, aQuickDialTone, aQuickDialToneNumber, aRateCenterCode, aRateZone, aRateZoneBandCode, aSagNpa, aSagWireCenter, aSbcColoLsoCode, aSbcColoWirecenter, aSbcServingOfficeCode, aSbcServingOfficeWirecenter, aServingAreaDescription, aStreetAddressGuideArea, aSurcharge4Percent, aSurcharge16Percent, aSwitchDataSuperPop, aSwitchVoiceSuperPop, aTarCode, aTelephoneNumber, aVerticalCoordinate, aWorkingServiceOnLocation, aEcktId, aCustomerPremiseIndicator, aSAGAddress, aBuildingType, aLegalEntity, aVideoHubOffice, aSmartmoves, aServingNetworkType, aLocationType, aCityStatePostalCodeValid, aPublicSafetyAnsweringPointId, aCountyId, aExceptionCode, aExceptionDescription, aExtensions);
        aProviderLocationProperties[0] = aProviderLocationProperty;

        aLocation.aLocationId = aLocationId;
        aLocation.aProviderLocationProperties = aProviderLocationProperties;


        /// map AddressMatchResult.AlternativeAddressResult
        AlternativeAddressResult aAlternativeAddressResult = new AlternativeAddressResult();
        AlternativeAddressSeqOpt aAlternativeAddresses = new AlternativeAddressSeqOpt();
        AlternativeAddress[] aAlternativeAddress = new AlternativeAddress[1];
        AlternativeAddress aAltAddress = new AlternativeAddress();

        //// map Address
        Address aAddress = new Address();
        StringOpt aRoute = new StringOpt();
        StringOpt aBox = new StringOpt();
        StringOpt aHouseNumberPrefix = new StringOpt();
        StringOpt aHouseNumber = new StringOpt();
        StringOpt aAssignedHouseNumber = new StringOpt();
        StringOpt aHouseNumberSuffix = new StringOpt();
        StringOpt aStreetDirection = new StringOpt();
        StringOpt aStreetName = new StringOpt();
        StringOpt aStreetThoroughfare = new StringOpt();
        StringOpt aStreetNameSuffix = new StringOpt();
        StringOpt aCity = new StringOpt();
        StringOpt aState = new StringOpt();
        StringOpt aPostalCode = new StringOpt();
        StringOpt aPostalCodePlus4 = new StringOpt();
        StringOpt aCounty = new StringOpt();
        StringOpt aCountry = new StringOpt();
        StringOpt aStructureType = new StringOpt();
        StringOpt aStructureValue = new StringOpt();
        StringOpt aLevelType = new StringOpt();
        StringOpt aLevelValue = new StringOpt();
        StringOpt aUnitType = new StringOpt();
        StringOpt aUnitValue = new StringOpt();
        StringOpt aOriginalStreetDirection = new StringOpt();
        StringOpt aOriginalStreetNameSuffix = new StringOpt();
        StringSeqOpt aCassAddressLines = new StringSeqOpt();
        StringSeqOpt aAuxiliaryAddressLines = new StringSeqOpt();
        StringOpt aCassAdditionalInfo = new StringOpt();
        StringOpt aAdditionalInfo = new StringOpt();
        StringOpt aBusinessName = new StringOpt();
        StringOpt aCountryCode = new StringOpt();
        StringOpt aCityCode = new StringOpt();
        StringOpt aServiceLocationName = new StringOpt();
        StringOpt aAddressId = new StringOpt();
        StringOpt aAliasName = new StringOpt();
        StringOpt aAttention = new StringOpt();
        com.att.lms.bis.service.ovals.model.FieldedWirelineAddressInfo respFielded;
        
        try {
            respFielded = uspsDeliveryPointValidation.get(0).getPostalAddress();

            AvsqubResponse.USPSDeliveryPointValidation.LocationProperties locationProperties = uspsDeliveryPointValidation.get(0).getLocationProperties();


            //respFielded = locationMatchAvailabilityResults.getExactLocationAvailabilityResults().getLocation().getLocationList().get(0).getLocationDetails().getFieldedAddress();
            aRoute.theValue(respFielded.getRoute());
            aBox.theValue(respFielded.getBox());
            aHouseNumberPrefix.theValue(respFielded.getHouseNumberPrefix());
            aHouseNumber.theValue(respFielded.getHouseNumber());
            aAssignedHouseNumber.theValue(respFielded.getAssignedHouseNumber());
            aHouseNumberSuffix.theValue(respFielded.getHouseNumberSuffix());
            aStreetDirection.theValue(respFielded.getStreetDirection());
            aStreetName.theValue(respFielded.getStreetName());
            aStreetThoroughfare.theValue(respFielded.getStreetThoroughfare());
            aStreetNameSuffix.theValue(respFielded.getStreetNameSuffix());
            aCity.theValue(respFielded.getCity());
            aState.theValue(respFielded.getState());
            aPostalCode.theValue(respFielded.getPostalCode());
            aPostalCodePlus4.theValue(respFielded.getPostalCodePlus4());
            aCounty.theValue(respFielded.getCounty());
            aCountry.theValue(respFielded.getCountry());
            aStructureType.theValue(respFielded.getStructureType());
            aStructureValue.theValue(respFielded.getStructureValue());
            aLevelType.theValue(respFielded.getLevelValue());
            aLevelValue.theValue(respFielded.getLevelValue());
            aUnitType.theValue(respFielded.getUnitType());
            aUnitValue.theValue(respFielded.getUnitValue());
            aOriginalStreetDirection.theValue(respFielded.getOriginalStreetDirection());
            aOriginalStreetNameSuffix.theValue(respFielded.getOriginalStreetNameSuffix());
            aCassAddressLines.theValue(respFielded.getCassAddressLines().toArray(new java.lang.String[0]));
            aAuxiliaryAddressLines.theValue(respFielded.getAuxiliaryAddressLines().toArray(new java.lang.String[0]));
            aCassAdditionalInfo.theValue(respFielded.getCassAdditionalInfo());
            aAdditionalInfo.theValue(respFielded.getAdditionalInfo());
            aBusinessName.theValue(respFielded.getBusinessName());
            aCountryCode.theValue(respFielded.getCountryCode());
            aCityCode.theValue(respFielded.getCityCode());
            aServiceLocationName.theValue(respFielded.getServiceLocationName());
            aAddressId.theValue(respFielded.getAddressId());
            aAliasName.theValue(respFielded.getAliasName());
            aAttention.theValue(respFielded.getAttention());
            aAddressMatchCode.theValue(locationProperties.getAddressMatchCode());
            aAddressMatchCodeDescription.theValue(locationProperties.getAddressMatchCodeDescription());
        } catch (Exception e) {
            respFielded = new com.att.lms.bis.service.ovals.model.FieldedWirelineAddressInfo();
        }
        FieldedAddress aFieldedAddress = new FieldedAddress(aRoute, aBox, aHouseNumberPrefix, aHouseNumber, aAssignedHouseNumber, aHouseNumberSuffix, aStreetDirection, aStreetName, aStreetThoroughfare, aStreetNameSuffix, aCity, aState, aPostalCode, aPostalCodePlus4, aCounty, aCountry, aStructureType, aStructureValue, aLevelType, aLevelValue, aUnitType, aUnitValue, aOriginalStreetDirection, aOriginalStreetNameSuffix, aCassAddressLines, aAuxiliaryAddressLines, aCassAdditionalInfo, aAdditionalInfo, aBusinessName, aCountryCode, aCityCode, aServiceLocationName, aAddressId, aAliasName, aAttention);

        com.att.lms.bis.service.ovals.model.UnfieldedWirelineAddressInfo respUnfielded;
        try {
            respUnfielded = locationMatchAvailabilityResults.getExactLocationAvailabilityResults().getLocation().getLocationList().get(0).getLocationDetails().getUnfieldedAddress();
        } catch (Exception e) {
            respUnfielded = new com.att.lms.bis.service.ovals.model.UnfieldedWirelineAddressInfo();
        }
        StringSeqOpt aAddressLines = new StringSeqOpt();
        StringOpt aCity2 = new StringOpt();
        StringOpt aState2 = new StringOpt();
        StringOpt aPostalCode2 = new StringOpt();
        StringOpt aPostalCodePlus42 = new StringOpt();
        StringOpt aCounty2 = new StringOpt();
        StringOpt aCountry2 = new StringOpt();
        StringOpt aStructureType2 = new StringOpt();
        StringOpt aStructureValue2 = new StringOpt();
        StringOpt aLevelType2 = new StringOpt();
        StringOpt aLevelValue2 = new StringOpt();
        StringOpt aUnitType2 = new StringOpt();
        StringOpt aUnitValue2 = new StringOpt();
        StringOpt aAdditionalInfo2 = new StringOpt();
        StringOpt aBusinessName2 = new StringOpt();
        StringOpt aCountryCode2 = new StringOpt();
        StringOpt aCityCode2 = new StringOpt();
        StringOpt aServiceLocationName2 = new StringOpt();
        StringOpt aAddressId2 = new StringOpt();
        StringOpt aAliasName2 = new StringOpt();
        StringOpt aAttention2 = new StringOpt();
        if (respUnfielded != null) {
            aAddressLines.theValue(respUnfielded.getAddressLines().toArray(new java.lang.String[0]));
//            StringOpt aCity2 = new StringOpt();
            aCity2.theValue(respUnfielded.getCity());
//            StringOpt aState2 = new StringOpt();
            aState2.theValue(respUnfielded.getState());
//            StringOpt aPostalCode2 = new StringOpt();
            aPostalCode2.theValue(respUnfielded.getPostalCode());
//            StringOpt aPostalCodePlus42 = new StringOpt();
            aPostalCodePlus42.theValue(respUnfielded.getPostalCodePlus4());
//            StringOpt aCounty2 = new StringOpt();
            aCounty2.theValue(respUnfielded.getCounty());
//            StringOpt aCountry2 = new StringOpt();
            aCountry2.theValue(respUnfielded.getCountry());
//            StringOpt aStructureType2 = new StringOpt();
            aStructureType2.theValue(respUnfielded.getStructureType());
//            StringOpt aStructureValue2 = new StringOpt();
            aStructureValue2.theValue(respUnfielded.getStructureValue());
//            StringOpt aLevelType2 = new StringOpt();
            aLevelType2.theValue(respUnfielded.getLevelType());
//            StringOpt aLevelValue2 = new StringOpt();
            aLevelValue2.theValue(respUnfielded.getLevelValue());
//            StringOpt aUnitType2 = new StringOpt();
            aUnitType2.theValue(respUnfielded.getUnitType());
//            StringOpt aUnitValue2 = new StringOpt();
            aUnitValue2.theValue(respUnfielded.getUnitValue());
//            StringOpt aAdditionalInfo2 = new StringOpt();
            aAdditionalInfo2.theValue(respUnfielded.getAdditionalInfo());
//            StringOpt aBusinessName2 = new StringOpt();
            aBusinessName2.theValue(respUnfielded.getBusinessName());
//            StringOpt aCountryCode2 = new StringOpt();
            aCountryCode2.theValue(respUnfielded.getCountryCode());
//            StringOpt aCityCode2 = new StringOpt();
            aCityCode2.theValue(respUnfielded.getCityCode());
//            StringOpt aServiceLocationName2 = new StringOpt();
            aServiceLocationName2.theValue(respUnfielded.getServiceLocationName());
//            StringOpt aAddressId2 = new StringOpt();
            aAddressId2.theValue(respUnfielded.getAddressId());
//            StringOpt aAliasName2 = new StringOpt();
            aAliasName2.theValue(respUnfielded.getAliasName());
//            StringOpt aAttention2 = new StringOpt();
            aAttention2.theValue(respUnfielded.getAttention());
        }
//        aAddressLines.theValue(respUnfielded.getAddressLines().toArray(new java.lang.String[0]));
//        StringOpt aCity2 = new StringOpt();
//        aCity2.theValue(respUnfielded.getCity());
//        StringOpt aState2 = new StringOpt();
//        aState2.theValue(respUnfielded.getState());
//        StringOpt aPostalCode2 = new StringOpt();
//        aPostalCode2.theValue(respUnfielded.getPostalCode());
//        StringOpt aPostalCodePlus42 = new StringOpt();
//        aPostalCodePlus42.theValue(respUnfielded.getPostalCodePlus4());
//        StringOpt aCounty2 = new StringOpt();
//        aCounty2.theValue(respUnfielded.getCounty());
//        StringOpt aCountry2 = new StringOpt();
//        aCountry2.theValue(respUnfielded.getCountry());
//        StringOpt aStructureType2 = new StringOpt();
//        aStructureType2.theValue(respUnfielded.getStructureType());
//        StringOpt aStructureValue2 = new StringOpt();
//        aStructureValue2.theValue(respUnfielded.getStructureValue());
//        StringOpt aLevelType2 = new StringOpt();
//        aLevelType2.theValue(respUnfielded.getLevelType());
//        StringOpt aLevelValue2 = new StringOpt();
//        aLevelValue2.theValue(respUnfielded.getLevelValue());
//        StringOpt aUnitType2 = new StringOpt();
//        aUnitType2.theValue(respUnfielded.getUnitType());
//        StringOpt aUnitValue2 = new StringOpt();
//        aUnitValue2.theValue(respUnfielded.getUnitValue());
//        StringOpt aAdditionalInfo2 = new StringOpt();
//        aAdditionalInfo2.theValue(respUnfielded.getAdditionalInfo());
//        StringOpt aBusinessName2 = new StringOpt();
//        aBusinessName2.theValue(respUnfielded.getBusinessName());
//        StringOpt aCountryCode2 = new StringOpt();
//        aCountryCode2.theValue(respUnfielded.getCountryCode());
//        StringOpt aCityCode2 = new StringOpt();
//        aCityCode2.theValue(respUnfielded.getCityCode());
//        StringOpt aServiceLocationName2 = new StringOpt();
//        aServiceLocationName2.theValue(respUnfielded.getServiceLocationName());
//        StringOpt aAddressId2 = new StringOpt();
//        aAddressId2.theValue(respUnfielded.getAddressId());
//        StringOpt aAliasName2 = new StringOpt();
//        aAliasName2.theValue(respUnfielded.getAliasName());
//        StringOpt aAttention2 = new StringOpt();
//        aAttention2.theValue(respUnfielded.getAttention());
        aAddress.aFieldedAddress(aFieldedAddress);
        aAddress.aUnfieldedAddress(null);

        aPostalAddress.theValue(aAddress);

        //// map RangedAddress
        StringOpt aHouseNumberLow3 = new StringOpt();
        StringOpt aHouseNumberHigh3 = new StringOpt();
        StringOpt aHouseNumberPrefix3 = new StringOpt();
        StringOpt aHouseNumberPrefixHigh3 = new StringOpt();
        StringOpt aHouseNumberSuffix3 = new StringOpt();
        StringOpt aHouseNumberSuffixHigh3 = new StringOpt();
        StringOpt aStreetDirection3 = new StringOpt();
        StringOpt aStreetName3 = new StringOpt();
        StringOpt aStreetThoroughfare3 = new StringOpt();
        StringOpt aStreetNameSuffix3 = new StringOpt();
        StringOpt aCity3 = new StringOpt();
        StringOpt aState3 = new StringOpt();
        StringOpt aPostalCode3 = new StringOpt();
        StringOpt aPostalCodePlus43 = new StringOpt();
        StringOpt aCounty3 = new StringOpt();
        StringOpt aCountry3 = new StringOpt();
        StringOpt aAdditionalInfo3 = new StringOpt();
        try {
            aHouseNumberLow3.theValue(locationMatchAvailabilityResults.getExactLocationAvailabilityResults().getLocation().getLocationList().get(0).getLocationDetails().getRangedAddress().getHouseNumberLow());
        } catch (Exception e) {
        }
        try {
            aHouseNumberHigh3.theValue(locationMatchAvailabilityResults.getExactLocationAvailabilityResults().getLocation().getLocationList().get(0).getLocationDetails().getRangedAddress().getHouseNumberHigh());
        } catch (Exception e) {
        }
        try {
            aHouseNumberPrefix3.theValue(locationMatchAvailabilityResults.getExactLocationAvailabilityResults().getLocation().getLocationList().get(0).getLocationDetails().getRangedAddress().getHouseNumberPrefix());
        } catch (Exception e) {
        }
        try {
            aHouseNumberPrefixHigh3.theValue(locationMatchAvailabilityResults.getExactLocationAvailabilityResults().getLocation().getLocationList().get(0).getLocationDetails().getRangedAddress().getHouseNumberPrefixHigh());
        } catch (Exception e) {
        }
        try {
            aHouseNumberSuffix3.theValue(locationMatchAvailabilityResults.getExactLocationAvailabilityResults().getLocation().getLocationList().get(0).getLocationDetails().getRangedAddress().getHouseNumberSuffix());
        } catch (Exception e) {
        }
        try {
            aHouseNumberSuffixHigh3.theValue(locationMatchAvailabilityResults.getExactLocationAvailabilityResults().getLocation().getLocationList().get(0).getLocationDetails().getRangedAddress().getHouseNumberSuffixHigh());
        } catch (Exception e) {
        }
        try {
            aStreetDirection3.theValue(locationMatchAvailabilityResults.getExactLocationAvailabilityResults().getLocation().getLocationList().get(0).getLocationDetails().getRangedAddress().getStreetDirection());
        } catch (Exception e) {
        }
        try {
            aStreetName3.theValue(locationMatchAvailabilityResults.getExactLocationAvailabilityResults().getLocation().getLocationList().get(0).getLocationDetails().getRangedAddress().getStreetName());
        } catch (Exception e) {
        }
        try {
            aStreetThoroughfare3.theValue(locationMatchAvailabilityResults.getExactLocationAvailabilityResults().getLocation().getLocationList().get(0).getLocationDetails().getRangedAddress().getStreetThoroughfare());
        } catch (Exception e) {
        }
        try {
            aStreetNameSuffix3.theValue(locationMatchAvailabilityResults.getExactLocationAvailabilityResults().getLocation().getLocationList().get(0).getLocationDetails().getRangedAddress().getStreetNameSuffix());
        } catch (Exception e) {
        }
        try {
            aCity3.theValue(locationMatchAvailabilityResults.getExactLocationAvailabilityResults().getLocation().getLocationList().get(0).getLocationDetails().getRangedAddress().getCity());
        } catch (Exception e) {
        }
        try {
            aState3.theValue(locationMatchAvailabilityResults.getExactLocationAvailabilityResults().getLocation().getLocationList().get(0).getLocationDetails().getRangedAddress().getState());
        } catch (Exception e) {
        }
        try {
            aPostalCode3.theValue(locationMatchAvailabilityResults.getExactLocationAvailabilityResults().getLocation().getLocationList().get(0).getLocationDetails().getRangedAddress().getPostalCode());
        } catch (Exception e) {
        }
        try {
            aPostalCodePlus43.theValue(locationMatchAvailabilityResults.getExactLocationAvailabilityResults().getLocation().getLocationList().get(0).getLocationDetails().getRangedAddress().getPostalCodePlus4());
        } catch (Exception e) {
        }
        try {
            aCounty3.theValue(locationMatchAvailabilityResults.getExactLocationAvailabilityResults().getLocation().getLocationList().get(0).getLocationDetails().getRangedAddress().getCounty());
        } catch (Exception e) {
        }
        try {
            aCountry3.theValue(locationMatchAvailabilityResults.getExactLocationAvailabilityResults().getLocation().getLocationList().get(0).getLocationDetails().getRangedAddress().getCountry());
        } catch (Exception e) {
        }
        try {
            aAdditionalInfo3.theValue(locationMatchAvailabilityResults.getExactLocationAvailabilityResults().getLocation().getLocationList().get(0).getLocationDetails().getRangedAddress().getAdditionalInfo());
        } catch (Exception e) {
        }
        RangedAddress aRangedAddress = new RangedAddress(aHouseNumberLow3, aHouseNumberHigh3, aHouseNumberPrefix3, aHouseNumberPrefixHigh3, aHouseNumberSuffix3, aHouseNumberSuffixHigh3, aStreetDirection3, aStreetName3, aStreetThoroughfare3, aStreetNameSuffix3, aCity3, aState3, aPostalCode3, aPostalCodePlus43, aCounty3, aCountry3, aAdditionalInfo3);

        //// map Location
        Location aLocation2 = new Location();
        StringOpt aLocationId2 = new StringOpt();
        try {
            aLocationId2.theValue(locationMatchAvailabilityResults.getExactLocationAvailabilityResults().getLocation().getGlobalLocationId());
        } catch (Exception e) {
        }
        ProviderLocationProperty[] aProviderLocationProperties2 = new ProviderLocationProperty[1];

        ProviderLocationProperty aProviderLocationProperty2 = new ProviderLocationProperty(aProviderName, aPostalAddress, aServiceAddress, aE911Address, aSwitchSuperPopAddress, aAddressMatchCode, aAddressMatchCodeDescription, aCoSwitchSuperPop, aCentralOfficeCode, aCentralOfficeType, aCommunityName, aDomSwitchPop, aE911Exempt, aE911NonRecurringCharge, aE911Surcharge, aExchangeCode, aExco, aGeoCode, aHorizontalCoordinate, aLataCode, aLataName, aLatitude, aLongitude, aLocalProviderAbbreviatedName, aLocalProviderExchangeCode, aLocalProviderName, aLocalProviderNumber, aLocalProviderServingOfficeCode, aMailingBarCodeSuffix, aMsaCode, aMsaName, aNearestDistanceColoToCo, aNearestDistanceSuperPopToCo, aNearestSbcColo, aNearestSbcCoSuperPop, aNearestSbcCoWirecenter, aOwnedWiring, aPostalCarrierCode, aPrimaryDirectoryCode, aPrimaryNpaNxx, aQuickDialTone, aQuickDialToneNumber, aRateCenterCode, aRateZone, aRateZoneBandCode, aSagNpa, aSagWireCenter, aSbcColoLsoCode, aSbcColoWirecenter, aSbcServingOfficeCode, aSbcServingOfficeWirecenter, aServingAreaDescription, aStreetAddressGuideArea, aSurcharge4Percent, aSurcharge16Percent, aSwitchDataSuperPop, aSwitchVoiceSuperPop, aTarCode, aTelephoneNumber, aVerticalCoordinate, aWorkingServiceOnLocation, aEcktId, aCustomerPremiseIndicator, aSAGAddress, aBuildingType, aLegalEntity, aVideoHubOffice, aSmartmoves, aServingNetworkType, aLocationType, aCityStatePostalCodeValid, aPublicSafetyAnsweringPointId, aCountyId, aExceptionCode, aExceptionDescription, aExtensions);
        aProviderLocationProperties2[0] = aProviderLocationProperty2;
        aLocation2.aLocationId = aLocationId2;
        aLocation2.aProviderLocationProperties = aProviderLocationProperties2;

        aAltAddress.aAddress(aAddress);
        aAltAddress.aRangedAddress(aRangedAddress);
        aAltAddress.aLocation(aLocation2);

        aAlternativeAddress[0] = aAltAddress;
        aAlternativeAddresses.theValue(aAlternativeAddress);

        /// map AddressMatchResult.SubmittedAddressExceptionSeqOpt
        SubmittedAddressExceptionSeqOpt aSubmittedAddressExceptions = new SubmittedAddressExceptionSeqOpt();
        SubmittedAddressException[] aSubmittedAddressException = new SubmittedAddressException[1];
        Address aAddress2 = new Address();
        StringOpt aCode = new StringOpt();
        StringOpt aDescription = new StringOpt();
        try {
        	com.att.lms.bis.service.ovals.model.FiberServiceProviderLocationPropertyInfo respLocProps = locationMatchAvailabilityResults.getExactLocationAvailabilityResults().getLocationProperties().getLocationProperties();
            aCode.theValue(respLocProps.getExceptionCode());
            aDescription.theValue(respLocProps.getExceptionDescription());
        } catch (Exception e) {
        }

        // Uses same props as before
        aAddress2.aFieldedAddress(aFieldedAddress);
        aAddress2.aUnfieldedAddress(null);
        SubmittedAddressException aSubAddressException = new SubmittedAddressException(aAddress2, aCode, aDescription);
        aSubmittedAddressException[0] = aSubAddressException;
        aSubmittedAddressExceptions.theValue(aSubmittedAddressException);

        aAlternativeAddressResult.aAlternativeAddresses = aAlternativeAddresses;
        aAlternativeAddressResult.aSubmittedAddressExceptions = aSubmittedAddressExceptions;


//        AddressMatchResultChoice aAddressMatchResultChoice = new AddressMatchResultChoice();


        aAddressMatchResult.aLocation(aLocation);
        aAddressMatchResult.aAlternativeAddressResult(null);
        //aAddressMatchResult.aAlternativeAddressResult(aAlternativeAddressResult);

        result.aAddressMatchResult = aAddressMatchResult;
        result.aContext = aBisContext;

        // Map response back into the expected retun type
        return result;
    }

    public RetrieveLocationForPostalAddressReturn retrieveLocationForPostalAddress(
            BisContext aBisContext,
            Address aAddress,
            LongOpt aMaxAddressAlternatives
    ) {
        logger.log(LogEventId.AUDIT_TRAIL, "OVALS::retrieveLocationForPostalAddress()|OVALS::doAddressValidation()|PRE");

        RetrieveLocationForPostalAddressReturn result = null;

        try {
        	com.att.lms.bis.service.ovals.model.pavs.request1.PostalAddressValidationServiceRequest request = mapToNewPavsRequest(
                    aBisContext,
                    aAddress,
                    aMaxAddressAlternatives                    
            );
        	com.att.lms.bis.service.ovals.model.pavs.response1.PostalAddressValidationServiceResponse response = 
        			ovalsPav.retrieveLocationForPostalAddressNew(request);
            if (response == null) {
                throw new NullPointerException();
            }
            result = mapFromNewPavsResponse(response);
        }
        catch (Exception oe)
        {
            throw new RuntimeException(oe);
        }
        return result;
    }

    private RetrieveLocationForPostalAddressReturn mapFromNewPavsResponse(
    		com.att.lms.bis.service.ovals.model.pavs.response1.PostalAddressValidationServiceResponse response) {
    	
        RetrieveLocationForPostalAddressReturn mappedResponse = new RetrieveLocationForPostalAddressReturn();
        PostalAddressMatchResult postalAddressMatchResult = new PostalAddressMatchResult();  
        
        List<com.att.lms.bis.service.ovals.model.pavs.response1.USPSDeliveryPointValidationAttributesType> uspsDeliveryPointValidationAttributes 
        			= response.getUSPSDeliveryPointValidationAttributes();
        com.att.lms.bis.service.ovals.model.pavs.response1.HostResponseType hostResponse = response.getHostResponse();

        String code = hostResponse.getStatus().getCode();

        if (!code.startsWith("4S") && !code.startsWith("3S")) {
            SubmittedAddressException submittedAddressException = new SubmittedAddressException();
            submittedAddressException.setACode(toStringOpt(code));
            submittedAddressException.setADescription(toStringOpt(hostResponse.getStatus().getDescription()));

            SubmittedAddressException[] submittedAddressExceptions = new SubmittedAddressException[1];
            submittedAddressExceptions[0] = submittedAddressException;


            SubmittedAddressExceptionSeqOpt aSubmittedAddressExceptionSeqOpt = new SubmittedAddressExceptionSeqOpt();
            aSubmittedAddressExceptionSeqOpt.theValue(submittedAddressExceptions);

            AlternativePostalAddressResult aAlternativeAddressResult = new AlternativePostalAddressResult();
            aAlternativeAddressResult.setASubmittedAddressExceptions(aSubmittedAddressExceptionSeqOpt);

            postalAddressMatchResult.set___aAlternativePostalAddressResult(aAlternativeAddressResult);

            mappedResponse.setAContext(aBisContext);
            mappedResponse.setAPostalAddressMatchResult(postalAddressMatchResult);
            return mappedResponse;
        }

        if ((uspsDeliveryPointValidationAttributes != null) && uspsDeliveryPointValidationAttributes.size() >= 1) {
        	com.att.lms.bis.service.ovals.model.pavs.response1.USPSDeliveryPointValidationAttributesType deliveryPointValidationAttr = 
        			uspsDeliveryPointValidationAttributes.get(0);
        	com.att.lms.bis.service.ovals.model.pavs.response1.USAFieldedAddressInfo  postalAddress = deliveryPointValidationAttr.getPostalAddress();
        	com.att.lms.bis.service.ovals.model.pavs.response1.USPSDeliveryPointValidationAttributesType.LocationProperties locationProperties = 
        			deliveryPointValidationAttr.getLocationProperties();

	        PostalLocation postalLocation = new PostalLocation();
	        Address address = new Address();
	        FieldedAddress fieldedAddress = new FieldedAddress();
	        try {
	            fieldedAddress.aHouseNumber = toStringOpt(postalAddress.getHouseNumber());
	        } catch (Exception e) {
	            fieldedAddress.aHouseNumber = null;
	        }
	        try {
	            fieldedAddress.aStreetName = toStringOpt(postalAddress.getStreetName());
	        } catch (Exception e) {
	            fieldedAddress.aStreetName = null;
	        }
	        try {
	            fieldedAddress.aStreetThoroughfare = toStringOpt(postalAddress.getStreetThoroughfare());
	        } catch (Exception e) {
	            fieldedAddress.aStreetThoroughfare = null;
	        }
	        try {
	            fieldedAddress.aStreetNameSuffix = toStringOpt(postalAddress.getStreetNameSuffix());
	        } catch (Exception e) {
	            fieldedAddress.aStreetNameSuffix = null;
	        }
	        try {
	            fieldedAddress.aCity = toStringOpt(postalAddress.getCity());
	        } catch (Exception e) {
	            fieldedAddress.aCity = null;
	        }
	        try {
	            fieldedAddress.aState = toStringOpt(postalAddress.getState().value());
	        } catch (Exception e) {
	            fieldedAddress.aState = null;
	        }
	        try {
	            fieldedAddress.aPostalCode = toStringOpt(postalAddress.getPostalCode());
	        } catch (Exception e) {
	            fieldedAddress.aPostalCode = null;
	        }
	        try {
	            fieldedAddress.aPostalCodePlus4 = toStringOpt(postalAddress.getPostalCodePlus4());
	        } catch (Exception e) {
	            fieldedAddress.aPostalCodePlus4 = null;
	        }
	        try {
	            fieldedAddress.aCounty = toStringOpt(postalAddress.getCounty());
	        } catch (Exception e) {
	            fieldedAddress.aCounty = null;
	        }
	        try {
	            fieldedAddress.aUnitType = toStringOpt(postalAddress.getUnitType());
	        } catch (Exception e) {
	            fieldedAddress.aUnitType = null;
	        }
	        try {
	            fieldedAddress.aUnitValue = toStringOpt(postalAddress.getUnitValue());
	        } catch (Exception e) {
	            fieldedAddress.aUnitValue = null;
	        }
	        try {
	            fieldedAddress.aCassAddressLines = seq(postalAddress.getAdditionalData().getCassAddressLines());
	        } catch (Exception e) {
	            fieldedAddress.aCassAddressLines = seq("");
	        }
	        try {
	            fieldedAddress.aAuxiliaryAddressLines = seq(postalAddress.getAdditionalData().getAuxiliaryAddressLines());
	        } catch (Exception e) {
	            fieldedAddress.aAuxiliaryAddressLines = seq("");
	        }
	        try {
	            fieldedAddress.aCountryCode = toStringOpt(postalAddress.getAdditionalData().getCountryCode());
	        } catch (Exception e) {
	            fieldedAddress.aCountryCode = null;
	        }
	        try {
	        	List<com.att.lms.bis.service.ovals.model.pavs.response1.USPSDeliveryPointValidationAttributesType.LocationProperties.PostalSupplementalData> postalSupplementalData = 
	        			locationProperties.getPostalSupplementalData();
	        	
	        	if ((postalSupplementalData != null) && (postalSupplementalData.size() >= 1)) {
	        		for (com.att.lms.bis.service.ovals.model.pavs.response1.USPSDeliveryPointValidationAttributesType.LocationProperties.PostalSupplementalData supData :
	        			postalSupplementalData) {
	        			fieldedAddress.aBusinessName = toStringOpt(supData.getBusinessName());
	        			break;
	        		}	        				        	
	        	}	            
	        } catch (Exception e) {
	            fieldedAddress.aBusinessName = null;
	        }
	        try {
	            fieldedAddress.aRoute = toStringOpt(postalAddress.getRoute());
	        } catch (Exception e) {
	            fieldedAddress.aRoute = null;
	        }
	        try {
	            fieldedAddress.aBox = toStringOpt(postalAddress.getBox());
	        } catch (Exception e) {
	            fieldedAddress.aBox = null;
	        }
	        try {
	            fieldedAddress.aHouseNumberPrefix = toStringOpt(postalAddress.getHouseNumberPrefix());
	        } catch (Exception e) {
	            fieldedAddress.aHouseNumberPrefix = null;
	        }
	        try {
	            fieldedAddress.aAssignedHouseNumber = toStringOpt(postalAddress.getAssignedHouseNumber());
	        } catch (Exception e) {
	            fieldedAddress.aAssignedHouseNumber = null;
	        }
	        try {
	            fieldedAddress.aHouseNumberSuffix = toStringOpt(postalAddress.getHouseNumberSuffix());
	        } catch (Exception e) {
	            fieldedAddress.aHouseNumberSuffix = null;
	        }
	        try {
	            fieldedAddress.aStreetDirection = toStringOpt(postalAddress.getStreetDirection());
	        } catch (Exception e) {
	            fieldedAddress.aStreetDirection = null;
	        }
	        try {
	            fieldedAddress.aCountry = toStringOpt(postalAddress.getCountry());
	        } catch (Exception e) {
	            fieldedAddress.aCountry = null;
	        }
	        try {
	            fieldedAddress.aStructureType = toStringOpt(postalAddress.getStructureType());
	        } catch (Exception e) {
	            fieldedAddress.aStructureType = null;
	        }
	        try {
	            fieldedAddress.aStructureValue = toStringOpt(postalAddress.getStructureValue());
	        } catch (Exception e) {
	            fieldedAddress.aStructureValue = null;
	        }
	        try {
	            fieldedAddress.aLevelType = toStringOpt(postalAddress.getLevelType());
	        } catch (Exception e) {
	            fieldedAddress.aLevelType = null;
	        }
	        try {
	            fieldedAddress.aLevelValue = toStringOpt(postalAddress.getLevelValue());
	        } catch (Exception e) {
	            fieldedAddress.aLevelValue = null;
	        }
	
	        address.aFieldedAddress(fieldedAddress);
	        postalLocation.aPostalAddress = address;
	        try {
	            postalLocation.aAddressMatchCode = locationProperties.getAddressMatchCode();
	        } catch (Exception e) {
	            postalLocation.aAddressMatchCode = "";
	        }
	        try {
	            postalLocation.aAddressMatchCodeDescription = toStringOpt(locationProperties.getAddressMatchCodeDescription());
	        } catch (Exception e) {
	            postalLocation.aAddressMatchCodeDescription = null;
	        }
	        try {
	            postalLocation.aDeliveryPointValidationCode = toStringOpt(locationProperties.getDeliveryPointValidationCode());
	        } catch (Exception e) {
	            postalLocation.aDeliveryPointValidationCode = null;
	        }
	        try {
	            postalLocation.aCityStatePostalCodeValid = toStringOpt(locationProperties.getCityStatePostalCodeValidType());
	        } catch (Exception e) {
	            postalLocation.aCityStatePostalCodeValid = null;
	        }
	        postalAddressMatchResult.aPostalLocation(postalLocation);
	        mappedResponse.aPostalAddressMatchResult = postalAddressMatchResult;
        }

        return mappedResponse;
	}

	private com.att.lms.bis.service.ovals.model.pavs.request1.PostalAddressValidationServiceRequest 
			mapToNewPavsRequest(BisContext aBisContext2, Address aAddress,
			LongOpt aMaxAddressAlternatives) {
		com.att.lms.bis.service.ovals.model.pavs.request1.PostalAddressValidationServiceRequest request = 
				new com.att.lms.bis.service.ovals.model.pavs.request1.PostalAddressValidationServiceRequest();
		com.att.lms.bis.service.ovals.model.pavs.request1.USAUniversalAddressInfo addressInfo = new com.att.lms.bis.service.ovals.model.pavs.request1.USAUniversalAddressInfo();
		com.att.lms.bis.service.ovals.model.pavs.request1.USAFieldedAddressInfo pavsFielded = null;
		com.att.lms.bis.service.ovals.model.pavs.request1.USAUnfieldedAddressInfo pavsUnfielded = null;

        if (aAddress.get__discriminator() == AddressChoice.FIELDED_ADDRESS) {
        	pavsFielded = new com.att.lms.bis.service.ovals.model.pavs.request1.USAFieldedAddressInfo();
        	addressInfo.setFielded(pavsFielded);
            FieldedAddress fieldedInput;
            try {
                fieldedInput = aAddress.get___aFieldedAddress();
            } catch (Exception e) {
                fieldedInput = new FieldedAddress();
            }

           
            try {
                pavsFielded.setHouseNumber(fieldedInput.aHouseNumber.theValue());
            }
            catch (Exception e) {
                pavsFielded.setHouseNumber("");
            }

            try {
                pavsFielded.setStreetName(fieldedInput.aStreetName.theValue());
            }
            catch (Exception e) {
                pavsFielded.setStreetName("");
            }

            try {
                pavsFielded.setStreetThoroughfare(fieldedInput.aStreetThoroughfare.theValue());
            }
            catch (Exception e) {
                pavsFielded.setStreetThoroughfare("");
            }

            try {
                pavsFielded.setStreetNameSuffix(fieldedInput.aStreetNameSuffix.theValue());
            }
            catch (Exception e) {
                pavsFielded.setStreetNameSuffix("");
            }

            try {
                pavsFielded.setCity(fieldedInput.aCity.theValue());
            }
            catch (Exception e) {
                pavsFielded.setCity("");
            }

            try {
                pavsFielded.setState(com.att.lms.bis.service.ovals.model.pavs.request1.StateCodeUSOnlyInfo.fromValue(fieldedInput.aState.theValue()));
            }
            catch (Exception e) {
 //               pavsFielded.setState("");
            }

            try {
                pavsFielded.setPostalCode(fieldedInput.aPostalCode.theValue());
            }
            catch (Exception e) {
                pavsFielded.setPostalCode("");
            }

            try {
                pavsFielded.setStructureType(fieldedInput.aStructureType.theValue());
            }
            catch (Exception e) {
                pavsFielded.setStructureType("");
            }

            try {
                pavsFielded.setStructureValue(fieldedInput.aStructureValue.theValue());
            }
            catch (Exception e) {
                pavsFielded.setStructureValue("");
            }

            try {
                pavsFielded.setLevelType(fieldedInput.aLevelType.theValue());
            }
            catch (Exception e) {
                pavsFielded.setLevelType("");
            }

            try {
                pavsFielded.setLevelValue(fieldedInput.aLevelValue.theValue());
            }
            catch (Exception e) {
                pavsFielded.setLevelValue("");
            }

            try {
                pavsFielded.setUnitType(fieldedInput.aUnitType.theValue());
            }
            catch (Exception e) {
                pavsFielded.setUnitType("");
            }

            try {
                pavsFielded.setUnitValue(fieldedInput.aUnitValue.theValue());
            }
            catch (Exception e) {
                pavsFielded.setUnitValue("");
            }
            
            com.att.lms.bis.service.ovals.model.pavs.request1.PostalAddressExtralDataInfo additionalData = 
            		new com.att.lms.bis.service.ovals.model.pavs.request1.PostalAddressExtralDataInfo();
            try {
                ObjectProperty[] customerName = (ObjectProperty[]) Arrays.stream(aBisContext.aProperties).filter(s -> s.aTag.equals("CustomerName")).toArray();
                additionalData.setCustomerName(customerName[0].aValue);
            }
            catch (Exception e) {
                additionalData.setCustomerName("");
            }
            try {
                additionalData.setBusinessName(fieldedInput.aBusinessName.theValue());
            }
            catch (Exception e) {
                additionalData.setBusinessName("");
            }
            try {
                additionalData.setAttention(fieldedInput.aAttention.theValue());
            }
            catch (Exception e) {
                additionalData.setAttention("");
            }
    //        try {
    //             TODO
    //            additionalData.setUrbanizationCode(fieldedInput.);
    //        }
    //        catch (Exception e) {
                additionalData.setUrbanizationCode("");
    //        }
            pavsFielded.setAdditionalData(additionalData);
 
            try {
                pavsFielded.setRoute(fieldedInput.aRoute.theValue());
            } catch (Exception e) {
                pavsFielded.setRoute("");
            }
            try {
                pavsFielded.setBox(fieldedInput.aBox.theValue());
            } catch (Exception e) {
                pavsFielded.setBox("");
            }
            try {
                pavsFielded.setHouseNumberPrefix(fieldedInput.aHouseNumberPrefix.theValue());
            } catch (Exception e) {
                pavsFielded.setHouseNumberPrefix("");
            }
            try {
                pavsFielded.setAssignedHouseNumber(fieldedInput.aAssignedHouseNumber.theValue());
            } catch (Exception e) {
                pavsFielded.setAssignedHouseNumber("");
            }
            try {
                pavsFielded.setHouseNumberSuffix(fieldedInput.aHouseNumberSuffix.theValue());
            } catch (Exception e) {
                pavsFielded.setHouseNumberSuffix("");
            }
            try {
                pavsFielded.setStreetDirection(fieldedInput.aStreetDirection.theValue());
            } catch (Exception e) {
                pavsFielded.setStreetDirection("");
            }
            try {
                pavsFielded.setPostalCodePlus4(fieldedInput.aPostalCodePlus4.theValue());
            } catch (Exception e) {
                pavsFielded.setPostalCodePlus4("");
            }
            try {
                pavsFielded.setCounty(fieldedInput.aCounty.theValue());
            } catch (Exception e) {
                pavsFielded.setCounty("");
            }
            try {
                pavsFielded.setCountry(fieldedInput.aCountry.theValue());
            } catch (Exception e) {
                pavsFielded.setCountry("");
            }
        } else {
              pavsUnfielded = new com.att.lms.bis.service.ovals.model.pavs.request1.USAUnfieldedAddressInfo();
              
              UnfieldedAddress unfieldedInput;
        	
	          try {
	            unfieldedInput = aAddress.get___aUnfieldedAddress();
	          } catch (Exception e) {
	            unfieldedInput = new UnfieldedAddress();
	          }
	
	          unfieldedInput = LimLegacyService.parseUnfieldedAddress(unfieldedInput, aBisContext);
	          pavsUnfielded = new com.att.lms.bis.service.ovals.model.pavs.request1.USAUnfieldedAddressInfo();
	
	          try {	       
	            pavsUnfielded.setAddressLine(Arrays.asList(unfieldedInput.aAddressLines.theValue()));
	          } catch (Exception e) {
	            pavsUnfielded.setAddressLine(Arrays.asList());
	          }
	
	          try {
	            pavsUnfielded.setStructureType(unfieldedInput.aStructureType.theValue());
	          } catch (Exception e) {
	            pavsUnfielded.setStructureType("");
	          }
	
	          try {
	            pavsUnfielded.setStructureValue(unfieldedInput.aStructureValue.theValue());
	          } catch (Exception e) {
	            pavsUnfielded.setStructureValue("");
	          }
	
	          try {
	            pavsUnfielded.setLevelType(unfieldedInput.aLevelType.theValue());
	          } catch (Exception e) {
	            pavsUnfielded.setLevelType("");
	          }
	
	          try {
	            pavsUnfielded.setLevelValue(unfieldedInput.aLevelValue.theValue());
	          } catch (Exception e) {
	            pavsUnfielded.setLevelValue("");
	          }
	
	          try {
	            pavsUnfielded.setUnitType(unfieldedInput.aUnitType.theValue());
	          } catch (Exception e) {
	            pavsUnfielded.setUnitType("");
	          }
	
	          try {
	            pavsUnfielded.setUnitValue(unfieldedInput.aUnitValue.theValue());
	          } catch (Exception e) {
	            pavsUnfielded.setUnitValue("");
	          }
	
	          try {
	            pavsUnfielded.setCity(unfieldedInput.aCity.theValue());
	          } catch (Exception e) {
	            pavsUnfielded.setCity("");
	          }
	
	          try {
	            pavsUnfielded.setState(com.att.lms.bis.service.ovals.model.pavs.request1.StateCodeUSOnlyInfo.fromValue(unfieldedInput.aState.theValue()));
	          } catch (Exception e) {
//	            pavsUnfielded.setState("");
	          }
	
	          try {
	            pavsUnfielded.setPostalCode(unfieldedInput.aPostalCode.theValue());
	          } catch (Exception e) {
	            pavsUnfielded.setPostalCode("");
	          }
	
	          try {
	            pavsUnfielded.setPostalCodePlus4(unfieldedInput.aPostalCodePlus4.theValue());
	          } catch (Exception e) {
	            pavsUnfielded.setPostalCodePlus4("");
	          }
	
	          try {
	            pavsUnfielded.setCounty(unfieldedInput.aCounty.theValue());
	          } catch (Exception e) {
	            pavsUnfielded.setCounty("");
	          }
	
	          try {
	            pavsUnfielded.setCountry(unfieldedInput.aCountry.theValue());
	          } catch (Exception e) {
	            pavsUnfielded.setCountry("");
	          }
	
              com.att.lms.bis.service.ovals.model.pavs.request1.PostalAddressExtralDataInfo additionalData = 
            		  new com.att.lms.bis.service.ovals.model.pavs.request1.PostalAddressExtralDataInfo();

	          try {
	              ObjectProperty[] customerName =
	                  (ObjectProperty[])
	                      Arrays.stream(aBisContext.aProperties)
	                          .filter(s -> s.aTag.equals("CustomerName"))
	                          .toArray();
	              additionalData.setCustomerName(customerName[0].aValue);
	            } catch (Exception e) {
	              additionalData.setCustomerName("");
	            }
	            try {
	              additionalData.setBusinessName(unfieldedInput.aBusinessName.theValue());
	            } catch (Exception e) {
	              additionalData.setBusinessName("");
	            }
	            try {
	              additionalData.setAttention(unfieldedInput.aAttention.theValue());
	            } catch (Exception e) {
	              additionalData.setAttention("");
	            }
	            //        try {
	            //            additionalData1.setUrbanizationCode(unfieldedInput);
	            //        } catch (Exception e) {
	            additionalData.setUrbanizationCode("");
	            //        }
	            pavsUnfielded.setAdditionalData(additionalData);
        }

        try {
            request.setMaximumAlternativeAddresses(aMaxAddressAlternatives.theValue());
        } catch (Exception e) {
            request.setMaximumAlternativeAddresses(10);
        }

        int maximumCASSAddressLineLength = 40;

        //Dynamically handle MaxCassCharPerLine
        ObjectProperty[] props = aBisContext.getAProperties();
        for (ObjectProperty prop : props) {
            if ("MaxCassCharPerLine".equals(prop.getATag())) {
                maximumCASSAddressLineLength = Integer.parseInt(prop.getAValue());
                break;
            }
        }

        request.setMaximumCASSAddressLineLength(maximumCASSAddressLineLength);
        addressInfo.setUnfielded(pavsUnfielded);
        request.setAddress(addressInfo);
		return request;
	}

/*	public PavsRequest mapToPavsRequest(
            BisContext aBisContext,
            Address aAddress,
            LongOpt aMaxAddressAlternatives)
    {
        PavsRequest request = new PavsRequest();
        PavsRequest.Address.Fielded pavsFielded = null;
        PavsRequest.Address.Unfielded pavsUnfielded = null;

        if (aAddress.get__discriminator() == AddressChoice.FIELDED_ADDRESS) {
            pavsFielded = new PavsRequest.Address.Fielded();

            FieldedAddress fieldedInput;
            try {
                fieldedInput = aAddress.get___aFieldedAddress();
            } catch (Exception e) {
                fieldedInput = new FieldedAddress();
            }

            try {
                pavsFielded.setPrimaryAddressLine(fieldedInput.aHouseNumber.theValue().concat(" ").concat(fieldedInput.aStreetName.theValue()));
            } catch (Exception e) {
                pavsFielded.setPrimaryAddressLine("");
            }

            try {
    //            pavsFielded.setSecondaryAddressLine(fieldedInput.aHouseNumber.theValue().concat(" ").concat(fieldedInput.aStreetName.theValue()));
            } catch (Exception e) {
                pavsFielded.setSecondaryAddressLine("");
            }

            try {
                pavsFielded.setHouseNumber(fieldedInput.aHouseNumber.theValue());
            }
            catch (Exception e) {
                pavsFielded.setHouseNumber("");
            }

            try {
                pavsFielded.setStreetName(fieldedInput.aStreetName.theValue());
            }
            catch (Exception e) {
                pavsFielded.setStreetName("");
            }

            try {
                pavsFielded.setStreetThoroughfare(fieldedInput.aStreetThoroughfare.theValue());
            }
            catch (Exception e) {
                pavsFielded.setStreetThoroughfare("");
            }

            try {
                pavsFielded.setStreetNameSuffix(fieldedInput.aStreetNameSuffix.theValue());
            }
            catch (Exception e) {
                pavsFielded.setStreetNameSuffix("");
            }

            try {
                pavsFielded.setCity(fieldedInput.aCity.theValue());
            }
            catch (Exception e) {
                pavsFielded.setCity("");
            }

            try {
                pavsFielded.setState(fieldedInput.aState.theValue());
            }
            catch (Exception e) {
                pavsFielded.setState("");
            }

            try {
                pavsFielded.setPostalCode(fieldedInput.aPostalCode.theValue());
            }
            catch (Exception e) {
                pavsFielded.setPostalCode("");
            }

            try {
                pavsFielded.setStructureType(fieldedInput.aStructureType.theValue());
            }
            catch (Exception e) {
                pavsFielded.setStructureType("");
            }

            try {
                pavsFielded.setStructureValue(fieldedInput.aStructureValue.theValue());
            }
            catch (Exception e) {
                pavsFielded.setStructureValue("");
            }

            try {
                pavsFielded.setLevelType(fieldedInput.aLevelType.theValue());
            }
            catch (Exception e) {
                pavsFielded.setLevelType("");
            }

            try {
                pavsFielded.setLevelValue(fieldedInput.aLevelValue.theValue());
            }
            catch (Exception e) {
                pavsFielded.setLevelValue("");
            }

            try {
                pavsFielded.setUnitType(fieldedInput.aUnitType.theValue());
            }
            catch (Exception e) {
                pavsFielded.setUnitType("");
            }

            try {
                pavsFielded.setUnitValue(fieldedInput.aUnitValue.theValue());
            }
            catch (Exception e) {
                pavsFielded.setUnitValue("");
            }

            PavsRequest.Address.Fielded.AdditionalData additionalData = new PavsRequest.Address.Fielded.AdditionalData();
            try {
                ObjectProperty[] customerName = (ObjectProperty[]) Arrays.stream(aBisContext.aProperties).filter(s -> s.aTag.equals("CustomerName")).toArray();
                additionalData.setCustomerName(customerName[0].aValue);
            }
            catch (Exception e) {
                additionalData.setCustomerName("");
            }
            try {
                additionalData.setBusinessName(fieldedInput.aBusinessName.theValue());
            }
            catch (Exception e) {
                additionalData.setBusinessName("");
            }
            try {
                additionalData.setAttention(fieldedInput.aAttention.theValue());
            }
            catch (Exception e) {
                additionalData.setAttention("");
            }
    //        try {
    //             TODO
    //            additionalData.setUrbanizationCode(fieldedInput.);
    //        }
    //        catch (Exception e) {
                additionalData.setUrbanizationCode("");
    //        }
            pavsFielded.setAdditionalData(additionalData);

            try {
                pavsFielded.setRoute(fieldedInput.aRoute.theValue());
            } catch (Exception e) {
                pavsFielded.setRoute("");
            }
            try {
                pavsFielded.setBox(fieldedInput.aBox.theValue());
            } catch (Exception e) {
                pavsFielded.setBox("");
            }
            try {
                pavsFielded.setHouseNumberPrefix(fieldedInput.aHouseNumberPrefix.theValue());
            } catch (Exception e) {
                pavsFielded.setHouseNumberPrefix("");
            }
            try {
                pavsFielded.setAssignedHouseNumber(fieldedInput.aAssignedHouseNumber.theValue());
            } catch (Exception e) {
                pavsFielded.setAssignedHouseNumber("");
            }
            try {
                pavsFielded.setHouseNumberSuffix(fieldedInput.aHouseNumberSuffix.theValue());
            } catch (Exception e) {
                pavsFielded.setHouseNumberSuffix("");
            }
            try {
                pavsFielded.setStreetDirection(fieldedInput.aStreetDirection.theValue());
            } catch (Exception e) {
                pavsFielded.setStreetDirection("");
            }
    //        try {
    //            pavsFielded.setPrimaryStreetUnnamedIndicator();
    //        } catch (Exception e) {
                pavsFielded.setPrimaryStreetUnnamedIndicator(null);
    //        }
            try {
                pavsFielded.setPostalCodePlus4(fieldedInput.aPostalCodePlus4.theValue());
            } catch (Exception e) {
                pavsFielded.setPostalCodePlus4("");
            }
            try {
                pavsFielded.setCounty(fieldedInput.aCounty.theValue());
            } catch (Exception e) {
                pavsFielded.setCounty("");
            }
            try {
                pavsFielded.setCountry(fieldedInput.aCountry.theValue());
            } catch (Exception e) {
                pavsFielded.setCountry("");
            }
    //        try {
    //            pavsFielded.setAdditionalName();
    //        } catch (Exception e) {
                pavsFielded.setAdditionalName("");
    //        }
    //        try {
    //            pavsFielded.setServiceId();
    //        } catch (Exception e) {
                pavsFielded.setServiceId("");
    //        }
    //        try {
    //            pavsFielded.setSingleLineStandardizedAddress(fieldedInput.aCountry.theValue());
    //        } catch (Exception e) {
                pavsFielded.setSingleLineStandardizedAddress("");
    //        }
    //        try {
    //            pavsFielded.setPostalBox();
    //        } catch (Exception e) {
                pavsFielded.setPostalBox("");
    //        }
    //        try {
    //            pavsFielded.setCityAbbreviatedName();
    //        } catch (Exception e) {
                pavsFielded.setCityAbbreviatedName("");
      //        }
    } else {

            pavsUnfielded = new PavsRequest.Address.Unfielded();
            UnfieldedAddress unfieldedInput = new UnfieldedAddress();

            try {
            unfieldedInput = aAddress.get___aUnfieldedAddress();
          } catch (Exception e) {
            unfieldedInput = new UnfieldedAddress();
          }

          unfieldedInput = LimLegacyService.parseUnfieldedAddress(unfieldedInput,aBisContext);
          pavsUnfielded = new PavsRequest.Address.Unfielded();

          try {
            pavsUnfielded.setAddressLine(String.join(" ",unfieldedInput.aAddressLines.theValue()));
          } catch (Exception e) {
            pavsUnfielded.setAddressLine("");
          }

          try {
            pavsUnfielded.setStructureType(unfieldedInput.aStructureType.theValue());
          } catch (Exception e) {
            pavsUnfielded.setStructureType("");
          }

          try {
            pavsUnfielded.setStructureValue(unfieldedInput.aStructureValue.theValue());
          } catch (Exception e) {
            pavsUnfielded.setStructureValue("");
          }

          try {
            pavsUnfielded.setLevelType(unfieldedInput.aLevelType.theValue());
          } catch (Exception e) {
            pavsUnfielded.setLevelType("");
          }

          try {
            pavsUnfielded.setLevelValue(unfieldedInput.aLevelValue.theValue());
          } catch (Exception e) {
            pavsUnfielded.setLevelValue("");
          }

          try {
            pavsUnfielded.setUnitType(unfieldedInput.aUnitType.theValue());
          } catch (Exception e) {
            pavsUnfielded.setUnitType("");
          }

          try {
            pavsUnfielded.setUnitValue(unfieldedInput.aUnitValue.theValue());
          } catch (Exception e) {
            pavsUnfielded.setUnitValue("");
          }

          try {
            pavsUnfielded.setCity(unfieldedInput.aCity.theValue());
          } catch (Exception e) {
            pavsUnfielded.setCity("");
          }

          try {
            pavsUnfielded.setState(unfieldedInput.aState.theValue());
          } catch (Exception e) {
            pavsUnfielded.setState("");
          }

          try {
            pavsUnfielded.setPostalCode(unfieldedInput.aPostalCode.theValue());
          } catch (Exception e) {
            pavsUnfielded.setPostalCode("");
          }

          try {
            pavsUnfielded.setPostalCodePlus4(unfieldedInput.aPostalCodePlus4.theValue());
          } catch (Exception e) {
            pavsUnfielded.setPostalCodePlus4("");
          }

          try {
            pavsUnfielded.setCounty(unfieldedInput.aCounty.theValue());
          } catch (Exception e) {
            pavsUnfielded.setCounty("");
          }

          try {
            pavsUnfielded.setCountry(unfieldedInput.aCountry.theValue());
          } catch (Exception e) {
            pavsUnfielded.setCountry("");
          }

          //        try {
          //            pavsUnfielded.setAdditionalName();
          //        } catch (Exception e) {
          pavsUnfielded.setAdditionalName("");
          //        }

          //        try {
          //            pavsUnfielded.setCrossStreet(unfieldedInput.aPostalCode.theValue());
          //        } catch (Exception e) {
          pavsUnfielded.setCrossStreet("");
          //        }

          //        try {
          //            pavsUnfielded.setServiceId(unfieldedInput.aPostalCode.theValue());
          //        } catch (Exception e) {
          pavsUnfielded.setServiceId("");
          //        }

          PavsRequest.Address.Unfielded.AdditionalData additionalData1 =
              new PavsRequest.Address.Unfielded.AdditionalData();
          try {
            ObjectProperty[] customerName =
                (ObjectProperty[])
                    Arrays.stream(aBisContext.aProperties)
                        .filter(s -> s.aTag.equals("CustomerName"))
                        .toArray();
            additionalData1.setCustomerName(customerName[0].aValue);
          } catch (Exception e) {
            additionalData1.setCustomerName("");
          }
          try {
            additionalData1.setBusinessName(unfieldedInput.aBusinessName.theValue());
          } catch (Exception e) {
            additionalData1.setBusinessName("");
          }
          try {
            additionalData1.setAttention(unfieldedInput.aAttention.theValue());
          } catch (Exception e) {
            additionalData1.setAttention("");
          }
          //        try {
          //            additionalData1.setUrbanizationCode(unfieldedInput);
          //        } catch (Exception e) {
          additionalData1.setUrbanizationCode("");
          //        }
          pavsUnfielded.setAdditionalData(additionalData1);
        }



        PavsRequest.Address pavsAddress = new PavsRequest.Address();
        pavsAddress.setFielded(pavsFielded);
        pavsAddress.setUnfielded(pavsUnfielded);

        request.setAddress(pavsAddress);
//        try {
//            request.setConversationId();
//        } catch (Exception e) {
            request.setConversationId("buyonline~CNG-CSI~ac3072e5-d965-44a3-ab8a-315365e07197");
//        }
        try {
            request.setMaximumAlternativeAddresses(aMaxAddressAlternatives.theValue());
        } catch (Exception e) {
            request.setMaximumAlternativeAddresses(10);
        }
//        try {
//            request.setMaximumCASSAddressLineLength();
//        } catch (Exception e) {
        int maximumCASSAddressLineLength = 40;

        //Dynamically handle MaxCassCharPerLine
        ObjectProperty[] props = aBisContext.getAProperties();
        for (ObjectProperty prop : props) {
            if ("MaxCassCharPerLine".equals(prop.getATag())) {
                maximumCASSAddressLineLength = Integer.parseInt(prop.getAValue());
                break;
            }
        }

        request.setMaximumCASSAddressLineLength(maximumCASSAddressLineLength);


        return request;
    }

    private RetrieveLocationForPostalAddressReturn mapFromPavsResponse(PavsResponse response) {

        RetrieveLocationForPostalAddressReturn mappedResponse = new RetrieveLocationForPostalAddressReturn();

        PavsResponse.USPSDeliveryPointValidationAttributes uspsDeliveryPointValidationAttributes = response.getUspsDeliveryPointValidationAttributes();
        PavsResponse.HostResponse hostResponse = response.getHostResponse();

        String code = hostResponse.getStatus().getCode();

        if (!code.startsWith("4S") && !code.startsWith("3S")) {
            SubmittedAddressException submittedAddressException = new SubmittedAddressException();
            submittedAddressException.setACode(toStringOpt(code));
            submittedAddressException.setADescription(toStringOpt(hostResponse.getStatus().getDescription()));

            SubmittedAddressException[] submittedAddressExceptions = new SubmittedAddressException[1];
            submittedAddressExceptions[0] = submittedAddressException;


            SubmittedAddressExceptionSeqOpt aSubmittedAddressExceptionSeqOpt = new SubmittedAddressExceptionSeqOpt();
            aSubmittedAddressExceptionSeqOpt.theValue(submittedAddressExceptions);

            AlternativePostalAddressResult aAlternativeAddressResult = new AlternativePostalAddressResult();
            aAlternativeAddressResult.setASubmittedAddressExceptions(aSubmittedAddressExceptionSeqOpt);

            PostalAddressMatchResult postalAddressMatchResult = new PostalAddressMatchResult();
            postalAddressMatchResult.set___aAlternativePostalAddressResult(aAlternativeAddressResult);

            mappedResponse.setAContext(aBisContext);
            mappedResponse.setAPostalAddressMatchResult(postalAddressMatchResult);
            return mappedResponse;
        }

        PavsResponse.USPSDeliveryPointValidationAttributes.PostalAddress postalAddress = uspsDeliveryPointValidationAttributes.getPostalAddress();
        PavsResponse.USPSDeliveryPointValidationAttributes.LocationProperties locationProperties = uspsDeliveryPointValidationAttributes.getLocationProperties();


        PostalAddressMatchResult postalAddressMatchResult = new PostalAddressMatchResult();
        PostalLocation postalLocation = new PostalLocation();
        Address address = new Address();
        FieldedAddress fieldedAddress = new FieldedAddress();
        try {
            fieldedAddress.aHouseNumber = toStringOpt(postalAddress.getHouseNumber());
        } catch (Exception e) {
            fieldedAddress.aHouseNumber = null;
        }
        try {
            fieldedAddress.aStreetName = toStringOpt(postalAddress.getStreetName());
        } catch (Exception e) {
            fieldedAddress.aStreetName = null;
        }
        try {
            fieldedAddress.aStreetThoroughfare = toStringOpt(postalAddress.getStreetThoroughfare());
        } catch (Exception e) {
            fieldedAddress.aStreetThoroughfare = null;
        }
        try {
            fieldedAddress.aStreetNameSuffix = toStringOpt(postalAddress.getStreetNameSuffix());
        } catch (Exception e) {
            fieldedAddress.aStreetNameSuffix = null;
        }
        try {
            fieldedAddress.aCity = toStringOpt(postalAddress.getCity());
        } catch (Exception e) {
            fieldedAddress.aCity = null;
        }
        try {
            fieldedAddress.aState = toStringOpt(postalAddress.getState());
        } catch (Exception e) {
            fieldedAddress.aState = null;
        }
        try {
            fieldedAddress.aPostalCode = toStringOpt(postalAddress.getPostalCode());
        } catch (Exception e) {
            fieldedAddress.aPostalCode = null;
        }
        try {
            fieldedAddress.aPostalCodePlus4 = toStringOpt(postalAddress.getPostalCodePlus4());
        } catch (Exception e) {
            fieldedAddress.aPostalCodePlus4 = null;
        }
        try {
            fieldedAddress.aCounty = toStringOpt(postalAddress.getCounty());
        } catch (Exception e) {
            fieldedAddress.aCounty = null;
        }
        try {
            fieldedAddress.aUnitType = toStringOpt(postalAddress.getUnitType());
        } catch (Exception e) {
            fieldedAddress.aUnitType = null;
        }
        try {
            fieldedAddress.aUnitValue = toStringOpt(postalAddress.getUnitValue());
        } catch (Exception e) {
            fieldedAddress.aUnitValue = null;
        }
        try {
            fieldedAddress.aCassAddressLines = seq(postalAddress.getAdditionalData().getCassAddressLines());
        } catch (Exception e) {
            fieldedAddress.aCassAddressLines = seq("");
        }
        try {
            fieldedAddress.aAuxiliaryAddressLines = seq(postalAddress.getAdditionalData().getAuxiliaryAddressLines());
        } catch (Exception e) {
            fieldedAddress.aAuxiliaryAddressLines = seq("");
        }
        try {
            fieldedAddress.aCountryCode = toStringOpt(postalAddress.getAdditionalData().getCountryCode());
        } catch (Exception e) {
            fieldedAddress.aCountryCode = null;
        }
        try {
            fieldedAddress.aBusinessName = toStringOpt(locationProperties.getPostalSupplementalData().getBusinessName());
        } catch (Exception e) {
            fieldedAddress.aBusinessName = null;
        }
        try {
            fieldedAddress.aRoute = toStringOpt(postalAddress.getRoute());
        } catch (Exception e) {
            fieldedAddress.aRoute = null;
        }
        try {
            fieldedAddress.aBox = toStringOpt(postalAddress.getBox());
        } catch (Exception e) {
            fieldedAddress.aBox = null;
        }
        try {
            fieldedAddress.aHouseNumberPrefix = toStringOpt(postalAddress.getHouseNumberPrefix());
        } catch (Exception e) {
            fieldedAddress.aHouseNumberPrefix = null;
        }
        try {
            fieldedAddress.aAssignedHouseNumber = toStringOpt(postalAddress.getAssignedHouseNumber());
        } catch (Exception e) {
            fieldedAddress.aAssignedHouseNumber = null;
        }
        try {
            fieldedAddress.aHouseNumberSuffix = toStringOpt(postalAddress.getHouseNumberSuffix());
        } catch (Exception e) {
            fieldedAddress.aHouseNumberSuffix = null;
        }
        try {
            fieldedAddress.aStreetDirection = toStringOpt(postalAddress.getStreetDirection());
        } catch (Exception e) {
            fieldedAddress.aStreetDirection = null;
        }
        try {
            fieldedAddress.aCountry = toStringOpt(postalAddress.getCountry());
        } catch (Exception e) {
            fieldedAddress.aCountry = null;
        }
        try {
            fieldedAddress.aStructureType = toStringOpt(postalAddress.getStructureType());
        } catch (Exception e) {
            fieldedAddress.aStructureType = null;
        }
        try {
            fieldedAddress.aStructureValue = toStringOpt(postalAddress.getStructureValue());
        } catch (Exception e) {
            fieldedAddress.aStructureValue = null;
        }
        try {
            fieldedAddress.aLevelType = toStringOpt(postalAddress.getLevelType());
        } catch (Exception e) {
            fieldedAddress.aLevelType = null;
        }
        try {
            fieldedAddress.aLevelValue = toStringOpt(postalAddress.getLevelValue());
        } catch (Exception e) {
            fieldedAddress.aLevelValue = null;
        }

        address.aFieldedAddress(fieldedAddress);
        postalLocation.aPostalAddress = address;
        try {
            postalLocation.aAddressMatchCode = locationProperties.getAddressMatchCode();
        } catch (Exception e) {
            postalLocation.aAddressMatchCode = "";
        }
        try {
            postalLocation.aAddressMatchCodeDescription = toStringOpt(locationProperties.getAddressMatchCodeDescription());
        } catch (Exception e) {
            postalLocation.aAddressMatchCodeDescription = null;
        }
        try {
            postalLocation.aDeliveryPointValidationCode = toStringOpt(locationProperties.getDeliveryPointValidationCode());
        } catch (Exception e) {
            postalLocation.aDeliveryPointValidationCode = null;
        }
        try {
            postalLocation.aCityStatePostalCodeValid = toStringOpt(locationProperties.getCityStatePostalCodeValidType());
        } catch (Exception e) {
            postalLocation.aCityStatePostalCodeValid = null;
        }



//        AlternativePostalAddressResult alternativePostalAddressResult = postalAddressMatchResult.aAlternativePostalAddressResult();

        postalAddressMatchResult.aPostalLocation(postalLocation);
        mappedResponse.aPostalAddressMatchResult = postalAddressMatchResult;
        return mappedResponse;
    }
*/
    public RetrieveLocationForAddressReturn retrieveLocationForAddressPla(
            Address address,
            ProviderLocationProperties[] locationPropertiesToGet,
            LongOpt aMaximumBasicAddressReturnLimit,
            LongOpt aMaximumUnitReturnLimit
    ) {
        logger.log(LogEventId.AUDIT_TRAIL, "OVALS::retrieveLocationForService()|OVALS::doAddressValidation()|PRE");

        RetrieveLocationForAddressReturn result = null;
        try
        {
            PlaRequest request = mapToPlaRequest(
                    address,
                    locationPropertiesToGet,
                    aMaximumBasicAddressReturnLimit,
                    aMaximumUnitReturnLimit
            );

            PlaResponse response = ovalsPla.processLocationAttributes(request);
            if (response == null) {
                throw new NullPointerException();
            }
            result = mapFromPlaResponse(response);
        }
        catch (OvalsException oe)
        {
            throw new RuntimeException(oe);
        }
        return result;
    }

    public PlaRequest mapToPlaRequest(
            Address address,
            ProviderLocationProperties[] locationPropertiesToGet,
            LongOpt aMaximumBasicAddressReturnLimit,
            LongOpt aMaximumUnitReturnLimit
    ) {
        PlaRequest request = new PlaRequest();
//        PlaRequest.ProcessLocationAttributesRequestInfo plaRequestInfo = new PlaRequest.ProcessLocationAttributesRequestInfo();


        PlaRequest.AddressInfoUniversal addressInfoUniversal = new PlaRequest.AddressInfoUniversal();

        PlaRequest.AddressInfoUniversal.USAAttributes usaAttributes = new PlaRequest.AddressInfoUniversal.USAAttributes();
        PlaRequest.AddressInfoUniversal.USAAttributes.AddressIdentifier addressIdentifier = new PlaRequest.AddressInfoUniversal.USAAttributes.AddressIdentifier();
        PlaRequest.AddressInfoUniversal.USAAttributes.AddressIdentifier.USAUniversalAddressInfo usaUniversalAddressInfo = new PlaRequest.AddressInfoUniversal.USAAttributes.AddressIdentifier.USAUniversalAddressInfo();
        com.att.lms.bis.service.ovals.model.USAFieldedAddressInfo fieldedAddress = new com.att.lms.bis.service.ovals.model.USAFieldedAddressInfo();
//        USAUnfieldedAddressInfo unfieldedAddress = new USAUnfieldedAddressInfo();

        //TODO: Remove hardcode, correctly map
//        fieldedAddress.setHouseNumber("8239");
//        fieldedAddress.setStreetName("GREENHOLLOW LN");
//        fieldedAddress.setStreetThoroughfare("DR");
//        fieldedAddress.setCity("DALLAS");
//        fieldedAddress.setState(StateCodeUSOnlyInfo.fromValue("TX"));
//        fieldedAddress.setPostalCode("75240");

        try {
            fieldedAddress.setRoute(address.aFieldedAddress().aRoute.theValue());
        } catch (Exception e) {
//            fieldedAddress.setRoute("");
        }
        try {
            fieldedAddress.setBox(address.aFieldedAddress().aBox.theValue());
        } catch (Exception e) {
//            fieldedAddress.setBox("");
        }
        try {
            fieldedAddress.setHouseNumberPrefix(address.aFieldedAddress().aHouseNumberPrefix.theValue());
        } catch (Exception e) {
//            fieldedAddress.setHouseNumberPrefix("");
        }
        try {
            fieldedAddress.setHouseNumber(address.aFieldedAddress().aHouseNumber.theValue());
        } catch (Exception e) {
//            fieldedAddress.setHouseNumber("");
        }
        try {
            fieldedAddress.setAssignedHouseNumber(address.aFieldedAddress().aAssignedHouseNumber.theValue());
        } catch (Exception e) {
//            fieldedAddress.setAssignedHouseNumber("");
        }
        try {
            fieldedAddress.setHouseNumberSuffix(address.aFieldedAddress().aHouseNumberSuffix.theValue());
        } catch (Exception e) {
//            fieldedAddress.setHouseNumberSuffix("");
        }
        try {
            fieldedAddress.setStreetDirection(address.aFieldedAddress().aStreetDirection.theValue());
        } catch (Exception e) {
//            fieldedAddress.setStreetDirection("");
        }
        try {
            fieldedAddress.setStreetName(address.aFieldedAddress().aStreetName.theValue());
        } catch (Exception e) {
//            fieldedAddress.setStreetName("");
        }
        try {
            fieldedAddress.setStreetThoroughfare(address.aFieldedAddress().aStreetThoroughfare.theValue());
        } catch (Exception e) {
//            fieldedAddress.setStreetThoroughfare("");
        }
        try {
            fieldedAddress.setStreetNameSuffix(address.aFieldedAddress().aStreetNameSuffix.theValue());
        } catch (Exception e) {
//            fieldedAddress.setStreetNameSuffix("");
        }
//        try {
//            fieldedAddress.setPrimaryStreetUnnamedIndicator(address.aFieldedAddress()..theValue());
//        } catch (Exception e) {
//            fieldedAddress.setPrimaryStreetUnnamedIndicator(null);
//        }
        try {
            fieldedAddress.setCity(address.aFieldedAddress().aCity.theValue());
        } catch (Exception e) {
//            fieldedAddress.setCity("");
        }
        try {
            fieldedAddress.setState(com.att.lms.bis.service.ovals.model.StateCodeUSOnlyInfo.valueOf(address.aFieldedAddress().aState.theValue()));
        } catch (Exception e) {
//            fieldedAddress.setState(null);
        }
        try {
            fieldedAddress.setPostalCode(address.aFieldedAddress().aPostalCode.theValue());
        } catch (Exception e) {
//            fieldedAddress.setPostalCode("");
        }
        try {
            fieldedAddress.setPostalCodePlus4(address.aFieldedAddress().aPostalCodePlus4.theValue());
        } catch (Exception e) {
//            fieldedAddress.setPostalCodePlus4("");
        }
        try {
            fieldedAddress.setCounty(address.aFieldedAddress().aCounty.theValue());
        } catch (Exception e) {
//            fieldedAddress.setCounty("");
        }
        try {
            fieldedAddress.setCountry(address.aFieldedAddress().aCountry.theValue());
        } catch (Exception e) {
//            fieldedAddress.setCountry("");
        }
        try {
            fieldedAddress.setStructureType(address.aFieldedAddress().aStructureType.theValue());
        } catch (Exception e) {
//            fieldedAddress.setStructureType("");
        }
        try {
            fieldedAddress.setStructureValue(address.aFieldedAddress().aStructureValue.theValue());
        } catch (Exception e) {
//            fieldedAddress.setStructureValue("");
        }
        try {
            fieldedAddress.setLevelType(address.aFieldedAddress().aLevelType.theValue());
        } catch (Exception e) {
//            fieldedAddress.setLevelType("");
        }
        try {
            fieldedAddress.setLevelValue(address.aFieldedAddress().aLevelValue.theValue());
        } catch (Exception e) {
//            fieldedAddress.setLevelValue("");
        }
        try {
            fieldedAddress.setUnitType(address.aFieldedAddress().aUnitType.theValue());
        } catch (Exception e) {
//            fieldedAddress.setUnitType("");
        }
        try {
            fieldedAddress.setUnitValue(address.aFieldedAddress().aUnitValue.theValue());
        } catch (Exception e) {
//            fieldedAddress.setUnitValue("");
        }
        try {
            fieldedAddress.setAdditionalName(address.aFieldedAddress().aAdditionalInfo.theValue());
        } catch (Exception e) {
//            fieldedAddress.setAdditionalName("");
        }
//        try {
//            fieldedAddress.setServiceId(address.aFieldedAddress().a.theValue());
//        } catch (Exception e) {
//            fieldedAddress.setServiceId("");
//        }
//        try {
//            fieldedAddress.setSingleLineStandardizedAddress(address.aFieldedAddress().aAdd.theValue());
//        } catch (Exception e) {
//            fieldedAddress.setSingleLineStandardizedAddress("");
//        }

//        try {
//            unfieldedAddress.setAddressLine(address.aUnfieldedAddress().aAddressLines.theValue()[0]);
//        } catch (Exception e) {
//            unfieldedAddress.setAddressLine("");
//        }
//        try {
//            unfieldedAddress.setCity(address.aUnfieldedAddress().aCity.theValue());
//        } catch (Exception e) {
//            unfieldedAddress.setCity("");
//        }
//        try {
//            unfieldedAddress.setState(StateCodeUSOnlyInfo.valueOf(address.aUnfieldedAddress().aState.theValue()));
//        } catch (Exception e) {
//            unfieldedAddress.setState(null);
//        }
//        try {
//            unfieldedAddress.setPostalCode(address.aUnfieldedAddress().aPostalCode.theValue());
//        } catch (Exception e) {
//            unfieldedAddress.setPostalCode("");
//        }
//        try {
//            unfieldedAddress.setPostalCodePlus4(address.aUnfieldedAddress().aPostalCodePlus4.theValue());
//        } catch (Exception e) {
//            unfieldedAddress.setPostalCodePlus4("");
//        }
//        try {
//            unfieldedAddress.setCounty(address.aUnfieldedAddress().aCounty.theValue());
//        } catch (Exception e) {
//            unfieldedAddress.setCounty("");
//        }
//        try {
//            unfieldedAddress.setCountry(address.aUnfieldedAddress().aCountry.theValue());
//        } catch (Exception e) {
//            unfieldedAddress.setCountry("");
//        }
//        try {
//            unfieldedAddress.setStructureType(address.aUnfieldedAddress().aStructureType.theValue());
//        } catch (Exception e) {
//            unfieldedAddress.setStructureType("");
//        }
//        try {
//            unfieldedAddress.setStructureValue(address.aUnfieldedAddress().aStructureValue.theValue());
//        } catch (Exception e) {
//            unfieldedAddress.setStructureValue("");
//        }
//        try {
//            unfieldedAddress.setLevelType(address.aUnfieldedAddress().aLevelType.theValue());
//        } catch (Exception e) {
//            unfieldedAddress.setLevelType("");
//        }
//        try {
//            unfieldedAddress.setLevelValue(address.aUnfieldedAddress().aLevelValue.theValue());
//        } catch (Exception e) {
//            unfieldedAddress.setLevelValue("");
//        }
//        try {
//            unfieldedAddress.setUnitType(address.aUnfieldedAddress().aUnitType.theValue());
//        } catch (Exception e) {
//            unfieldedAddress.setUnitType("");
//        }
//        try {
//            unfieldedAddress.setUnitValue(address.aUnfieldedAddress().aUnitValue.theValue());
//        } catch (Exception e) {
//            unfieldedAddress.setUnitValue("");
//        }
//        try {
//            unfieldedAddress.setAdditionalName(address.aUnfieldedAddress().aAdditionalInfo.theValue());
//        } catch (Exception e) {
//            unfieldedAddress.setAdditionalName("");
//        }
////        try {
////            unfieldedAddress.setCrossStreet(address.aUnfieldedAddress().aCr.theValue());
////        } catch (Exception e) {
//            unfieldedAddress.setCrossStreet("");
////        }
////        try {
////            unfieldedAddress.setServiceId(address.aUnfieldedAddress().aSer.theValue());
////        } catch (Exception e) {
//            unfieldedAddress.setServiceId("");
//        }
//
//
        usaUniversalAddressInfo.setFielded(fieldedAddress);
//        usaUniversalAddressInfo.setUnfielded(unfieldedAddress);
        addressIdentifier.setAddress(usaUniversalAddressInfo);
//
//        TODO: Unfielded

        usaAttributes.setAddressIdentifier(addressIdentifier);
        addressInfoUniversal.setUsaAttributes(usaAttributes);

        PlaRequest.RequestFunctionType requestFunctionType = new PlaRequest.RequestFunctionType();
        PlaRequest.RequestFunctionType.USAOptions usaOptions = new PlaRequest.RequestFunctionType.USAOptions();
        PlaRequest.RequestFunctionType.USAOptions.SAGValidationOptions sagValidationOptions = new PlaRequest.RequestFunctionType.USAOptions.SAGValidationOptions();
        sagValidationOptions.setValidationIndicator(false);
        usaOptions.setSagValidationOptions(sagValidationOptions);

        PlaRequest.RequestFunctionType.USAOptions.GISValidationOptions gisValidationOptions = new PlaRequest.RequestFunctionType.USAOptions.GISValidationOptions();
        gisValidationOptions.setValidationIndicator(false);
        usaOptions.setGisValidationOptions(gisValidationOptions);

        PlaRequest.RequestFunctionType.USAOptions.USPSDeliveryPointValidationOptions uspsDeliveryPointValidationOptions = new PlaRequest.RequestFunctionType.USAOptions.USPSDeliveryPointValidationOptions();
        uspsDeliveryPointValidationOptions.setMaximumAlternativeAddresses(10);
        uspsDeliveryPointValidationOptions.setMaximumCASSAddressLineLength("40");
        usaOptions.setUspsDeliveryPointValidationOptions(uspsDeliveryPointValidationOptions);
        requestFunctionType.setUsaOptions(usaOptions);

        request.setAddressInfoUniversal(addressInfoUniversal);
        request.setRequestFunctionType(requestFunctionType);
        return request;
    }

    private RetrieveLocationForAddressReturn mapFromPlaResponse(PlaResponse response) {
        RetrieveLocationForAddressReturn serviceReturn = new RetrieveLocationForAddressReturn();

//        BisContext aContext = new BisContext();
//        AddressMatchResult aAddressMatchResult = new AddressMatchResult();











//        serviceReturn.aAddressMatchResult.

        PlaResponse.GISLocationAttributes.LocationProperties gisLocProps = null;
        PlaResponse.SAGLocationAttributes.SAGProperties sagProps = null;;

        if (response.getGisLocationAttributes() != null) {
            gisLocProps = response.getGisLocationAttributes().getLocationProperties();
        }

        if (response.getSagLocationAttributes() != null) {
            sagProps = response.getSagLocationAttributes().getSagProperties();
        }

//        RetrieveLocationForAddressReturn serviceReturn = new RetrieveLocationForAddressReturn();

        BisContext aContext = new BisContext();
        ObjectProperty[] objectProperties = new ObjectProperty[0];
        aContext.setAProperties(objectProperties);

        AddressMatchResult addressMatchResult = new AddressMatchResult();
        Location location = new Location();
        try {
            location.aLocationId = toStringOpt(response.getSagLocationAttributes().getGlobalLocationId());
        } catch (Exception e) {
            location.aLocationId = null;
        }
        ProviderLocationProperty[] providerLocationProperties = new ProviderLocationProperty[1];
        ProviderLocationProperty providerLocationProperty = new ProviderLocationProperty();
        try {
            providerLocationProperty.aProviderName = toStringOpt(gisLocProps.getLocalProviderName());
        } catch (Exception e) {
            providerLocationProperty.aProviderName = null;
        }
        try {
            providerLocationProperty.aAddressMatchCode = toStringOpt(gisLocProps.getAddressMatchCode());
        } catch (Exception e) {
            providerLocationProperty.aAddressMatchCode = null;
        }
        try {
            providerLocationProperty.aAddressMatchCodeDescription = toStringOpt(gisLocProps.getAddressMatchCodeDescription());
        } catch (Exception e) {
            providerLocationProperty.aAddressMatchCodeDescription = null;
        }
//        try {
//            providerLocationProperty.aCoSwitchSuperPop = toStringOpt(gisLocProps.getCoS);
//        } catch (Exception e) {
            providerLocationProperty.aCoSwitchSuperPop = null;
//        }
        try {
            providerLocationProperty.aCentralOfficeType = toStringOpt(gisLocProps.getCentralOfficeType());
        } catch (Exception e) {
            providerLocationProperty.aCentralOfficeType = null;
        }
        try {
            providerLocationProperty.aCentralOfficeCode = toStringOpt(gisLocProps.getCentralOfficeCode());
        } catch (Exception e) {
            providerLocationProperty.aCentralOfficeCode = null;
        }
        try {
            providerLocationProperty.aCommunityName = toStringOpt(response.getSagLocationAttributes().getAddress().getDescriptiveAlternatives().getCommunityName());
        } catch (Exception e) {
            providerLocationProperty.aCommunityName = null;
        }
//        try {
//            providerLocationProperty.aDomSwitchPop = toStringOpt();
//        } catch (Exception e) {
            providerLocationProperty.aDomSwitchPop = null;
//        }
        try {
            providerLocationProperty.aE911Exempt = toStringOpt(sagProps.getE911Exempt());
        } catch (Exception e) {
            providerLocationProperty.aE911Exempt = null;
        }
        try {
            providerLocationProperty.aE911NonRecurringCharge = toStringOpt(sagProps.getE911NonRecurringCharge());
        } catch (Exception e) {
            providerLocationProperty.aE911NonRecurringCharge = null;
        }
        try {
            providerLocationProperty.aE911Surcharge = toStringOpt(sagProps.getE911Surcharge());
        } catch (Exception e) {
            providerLocationProperty.aE911Surcharge = null;
        }
        try {
            providerLocationProperty.aExchangeCode = toStringOpt(sagProps.getE911NonRecurringCharge());
        } catch (Exception e) {
            providerLocationProperty.aExchangeCode = null;
        }
//        try {
//            providerLocationProperty.aGeoCode = toStringOpt();
//        } catch (Exception e) {
            providerLocationProperty.aGeoCode = null;
//        }
        try {
            providerLocationProperty.aHorizontalCoordinate = toStringOpt(gisLocProps.getHorizontalCoordinate());
        } catch (Exception e) {
            providerLocationProperty.aHorizontalCoordinate = null;
        }
        try {
            providerLocationProperty.aLataCode = toStringOpt(sagProps.getLataCode());
        } catch (Exception e) {
            providerLocationProperty.aLataCode = null;
        }
        try {
            providerLocationProperty.aLataName = toStringOpt(gisLocProps.getLataName());
        } catch (Exception e) {
            providerLocationProperty.aLataName = null;
        }
        try {
            providerLocationProperty.aLatitude = toStringOpt(response.getSagLocationAttributes().getLocationProperties().getVHCoordinates().getLatitude().toString());
        } catch (Exception e) {
            providerLocationProperty.aLatitude = null;
        }
        try {
            providerLocationProperty.aLongitude = toStringOpt(response.getSagLocationAttributes().getLocationProperties().getVHCoordinates().getLongitude().toString());
        } catch (Exception e) {
            providerLocationProperty.aLongitude = null;
        }
        try {
            providerLocationProperty.aLocalProviderAbbreviatedName = toStringOpt(gisLocProps.getLocalProviderAbbreviatedName());
        } catch (Exception e) {
            providerLocationProperty.aLocalProviderAbbreviatedName = null;
        }
        try {
            providerLocationProperty.aLocalProviderExchangeCode = toStringOpt(gisLocProps.getLocalProviderExchangeCode());
        } catch (Exception e) {
            providerLocationProperty.aLocalProviderExchangeCode = null;
        }
        try {
            providerLocationProperty.aLocalProviderName = toStringOpt(gisLocProps.getLocalProviderName());
        } catch (Exception e) {
            providerLocationProperty.aLocalProviderName = null;
        }
        try {
            providerLocationProperty.aLocalProviderNumber = toStringOpt(gisLocProps.getLocalProviderNumber());
        } catch (Exception e) {
            providerLocationProperty.aLocalProviderNumber = null;
        }
        try {
            providerLocationProperty.aLocalProviderServingOfficeCode = toStringOpt(gisLocProps.getLocalProviderServingOfficeCode());
        } catch (Exception e) {
            providerLocationProperty.aLocalProviderServingOfficeCode = null;
        }
//        try {
//            providerLocationProperty.aMailingBarCodeSuffix = toStringOpt();
//        } catch (Exception e) {
            providerLocationProperty.aMailingBarCodeSuffix = null;
//        }
//        try {
//            providerLocationProperty.aMsaCode = toStringOpt();
//        } catch (Exception e) {
            providerLocationProperty.aMsaCode = null;
//        }
//        try {
//            providerLocationProperty.aMsaName = toStringOpt();
//        } catch (Exception e) {
            providerLocationProperty.aMsaName = null;
//        }
//        try {
//            providerLocationProperty.aNearestDistanceColoToCo = toStringOpt();
//        } catch (Exception e) {
            providerLocationProperty.aNearestDistanceColoToCo = null;
//        }
//        try {
//            providerLocationProperty.aNearestDistanceSuperPopToCo = toStringOpt();
//        } catch (Exception e) {
            providerLocationProperty.aNearestDistanceSuperPopToCo = null;
//        }
//        try {
//            providerLocationProperty.aNearestSbcColo = toStringOpt();
//        } catch (Exception e) {
            providerLocationProperty.aNearestSbcColo = null;
//        }
//        try {
//            providerLocationProperty.aNearestSbcCoSuperPop = toStringOpt();
//        } catch (Exception e) {
            providerLocationProperty.aNearestSbcCoSuperPop = null;
//        }
//        try {
//            providerLocationProperty.aNearestSbcCoWirecenter = toStringOpt();
//        } catch (Exception e) {
            providerLocationProperty.aNearestSbcCoWirecenter = null;
//        }
        try {
            providerLocationProperty.aOwnedWiring = toStringOpt(response.getSagLocationAttributes().getLocationProperties().getOwnedWiringFlag().toString());
        } catch (Exception e) {
            providerLocationProperty.aOwnedWiring = null;
        }
//        try {
//            providerLocationProperty.aPostalCarrierCode = toStringOpt();
//        } catch (Exception e) {
            providerLocationProperty.aPostalCarrierCode = null;
//        }
        try {
            providerLocationProperty.aProviderName = toStringOpt(gisLocProps.getLocalProviderName());
        } catch (Exception e) {
            providerLocationProperty.aProviderName = null;
        }
//        try {
//            providerLocationProperty.aPrimaryDirectoryCode = toStringOpt();
//        } catch (Exception e) {
            providerLocationProperty.aPrimaryDirectoryCode = null;
//        }
        try {
            providerLocationProperty.aPrimaryNpaNxx = toStringOpt(sagProps.getPrimaryNpaNxx().toString());
        } catch (Exception e) {
            providerLocationProperty.aPrimaryNpaNxx = null;
        }
        try {
            providerLocationProperty.aQuickDialTone = toStringOpt(response.getSagLocationAttributes().getTelephoneProperties().getQuickDialToneFlag().toString());
        } catch (Exception e) {
            providerLocationProperty.aQuickDialTone = null;
        }
        try {
            if (response.getSagLocationAttributes().getTelephoneProperties().getQuickDialToneFlag().equals(true)){
                providerLocationProperty.aQuickDialToneNumber = toStringOpt(response.getSagLocationAttributes().getTelephoneProperties().getTelephoneNumber());
            }
        } catch (Exception e) {
            providerLocationProperty.aQuickDialToneNumber = null;
        }
        try {
            providerLocationProperty.aRateCenterCode = toStringOpt(gisLocProps.getRateCenterCode());
        } catch (Exception e) {
            providerLocationProperty.aRateCenterCode = null;
        }
        try {
            providerLocationProperty.aRateZone = toStringOpt(gisLocProps.getRateZone());
        } catch (Exception e) {
            providerLocationProperty.aRateZone = null;
        }
        try {
            providerLocationProperty.aRateZoneBandCode = toStringOpt(gisLocProps.getRateZoneBandCode());
        } catch (Exception e) {
            providerLocationProperty.aRateZoneBandCode = null;
        }
        try {
            providerLocationProperty.aSagNpa = toStringOpt(sagProps.getSagNpa());
        } catch (Exception e) {
            providerLocationProperty.aSagNpa = null;
        }
        try {
            providerLocationProperty.aSagWireCenter = toStringOpt(sagProps.getSagWireCenter());
        } catch (Exception e) {
            providerLocationProperty.aSagWireCenter = null;
        }
//        try {
//            providerLocationProperty.aSbcColoLsoCode = toStringOpt();
//        } catch (Exception e) {
            providerLocationProperty.aSbcColoLsoCode = null;
//        }
//        try {
//            providerLocationProperty.aSbcColoWirecenter = toStringOpt();
//        } catch (Exception e) {
            providerLocationProperty.aSbcColoWirecenter = null;
//        }
//        try {
//            providerLocationProperty.aSbcServingOfficeCode = toStringOpt();
//        } catch (Exception e) {
            providerLocationProperty.aSbcServingOfficeCode = null;
//        }
//        try {
//            providerLocationProperty.aSbcServingOfficeWirecenter = toStringOpt();
//        } catch (Exception e) {
            providerLocationProperty.aSbcServingOfficeWirecenter = null;
//        }
//        try {
//            providerLocationProperty.aServingAreaDescription = toStringOpt();
//        } catch (Exception e) {
            providerLocationProperty.aServingAreaDescription = null;
//        }
        try {
            providerLocationProperty.aStreetAddressGuideArea = toStringOpt(sagProps.getSagArea());
        } catch (Exception e) {
            providerLocationProperty.aStreetAddressGuideArea = null;
        }
        try {
            providerLocationProperty.aSurcharge4Percent = toStringOpt(sagProps.getE911FourPercentSurchargeFlag());
        } catch (Exception e) {
            providerLocationProperty.aSurcharge4Percent = null;
        }
        try {
            providerLocationProperty.aSurcharge16Percent = toStringOpt(sagProps.getE911SixteenPercentSurchargeFlag());
        } catch (Exception e) {
            providerLocationProperty.aSurcharge16Percent = null;
        }
//        try {
//            providerLocationProperty.aSwitchDataSuperPop = toStringOpt();
//        } catch (Exception e) {
            providerLocationProperty.aSwitchDataSuperPop = null;
//        }
//        try {
//            providerLocationProperty.aSwitchVoiceSuperPop = toStringOpt();
//        } catch (Exception e) {
            providerLocationProperty.aSwitchVoiceSuperPop = null;
//        }
        try {
            providerLocationProperty.aTarCode = toStringOpt(sagProps.getTarCode());
        } catch (Exception e) {
            providerLocationProperty.aTarCode = null;
        }
        try {
            providerLocationProperty.aTelephoneNumber = toStringOpt(response.getSagLocationAttributes().getTelephoneProperties().getTelephoneNumber());
        } catch (Exception e) {
            providerLocationProperty.aTelephoneNumber = null;
        }
        try {
            providerLocationProperty.aVerticalCoordinate = toStringOpt(gisLocProps.getVerticalCoordinate());
        } catch (Exception e) {
            providerLocationProperty.aVerticalCoordinate = null;
        }
        try {
            providerLocationProperty.aWorkingServiceOnLocation = toStringOpt(response.getSagLocationAttributes().getLocationProperties().getWsopiFlag().toString());
        } catch (Exception e) {
            providerLocationProperty.aWorkingServiceOnLocation = null;
        }
//        try {
//            providerLocationProperty.aEcktId = toStringOpt();
//        } catch (Exception e) {
            providerLocationProperty.aEcktId = null;
//        }
//        try {
//            providerLocationProperty.aCustomerPremiseIndicator = toStringOpt();
//        } catch (Exception e) {
            providerLocationProperty.aCustomerPremiseIndicator = null;
//        }
        try {
            providerLocationProperty.aBuildingType = toStringOpt(response.getSagLocationAttributes().getLocationProperties().getLocationStandards().getBuildingType());
        } catch (Exception e) {
            providerLocationProperty.aBuildingType = null;
        }
        try {
            providerLocationProperty.aLegalEntity = toStringOpt(sagProps.getLegalEntity());
        } catch (Exception e) {
            providerLocationProperty.aLegalEntity = null;
        }
//        try {
//            providerLocationProperty.aVideoHubOffice = toStringOpt();
//        } catch (Exception e) {
            providerLocationProperty.aVideoHubOffice = null;
//        }
        try {
            providerLocationProperty.aSmartmoves = toStringOpt(response.getSagLocationAttributes().getLocationProperties().getSmartMovesIndicator().toString());
        } catch (Exception e) {
            providerLocationProperty.aSmartmoves = null;
        }
//        try {
//            providerLocationProperty.aServingNetworkType = toStringOpt();
//        } catch (Exception e) {
            providerLocationProperty.aServingNetworkType = null;
//        }
//        try {
//            providerLocationProperty.aLocationType = toStringOpt();
//        } catch (Exception e) {
            providerLocationProperty.aLocationType = null;
//        }
        try {
            providerLocationProperty.aCityStatePostalCodeValid = toStringOpt(gisLocProps.getCityStatePostalCodeValidFlag().toString());
        } catch (Exception e) {
            providerLocationProperty.aCityStatePostalCodeValid = null;
        }
//        try {
//            providerLocationProperty.aPublicSafetyAnsweringPointId = toStringOpt();
//        } catch (Exception e) {
            providerLocationProperty.aPublicSafetyAnsweringPointId = null;
//        }
        try {
            providerLocationProperty.aCountyId = toStringOpt(response.getMsagLocationAttributes().getMsagProperties().getCountyId());
        } catch (Exception e) {
            providerLocationProperty.aCountyId = null;
        }
//        try {
//            providerLocationProperty.aExceptionCode = toStringOpt();
//        } catch (Exception e) {
            providerLocationProperty.aExceptionCode = null;
//        }
//        try {
//            providerLocationProperty.aExceptionDescription = toStringOpt();
//        } catch (Exception e) {
            providerLocationProperty.aExceptionDescription = null;
//        }
        providerLocationProperty.aPostalAddress = new AddressOpt();
        FieldedAddress fieldedAddress = new FieldedAddress();

        //TODO: Parsing
        com.att.lms.bis.service.ovals.model.USAFieldedAddressInfo respFielded;
        try {
            respFielded = response.getUspsDeliveryPointValidationAttributes().getPostalAddress();;
        } catch (Exception e) {
            respFielded = new com.att.lms.bis.service.ovals.model.USAFieldedAddressInfo();
        }

        try {
            fieldedAddress.aRoute = toStringOpt(respFielded.getRoute());
        } catch (Exception e) {
            fieldedAddress.aRoute = null;
        }
        try {
            fieldedAddress.aBox = toStringOpt(respFielded.getBox());
        } catch (Exception e) {
            fieldedAddress.aBox = null;
        }
        try {
            fieldedAddress.aHouseNumberPrefix = toStringOpt(respFielded.getHouseNumberPrefix());
        } catch (Exception e) {
            fieldedAddress.aHouseNumberPrefix = null;
        }
        try {
            fieldedAddress.aHouseNumber = toStringOpt(respFielded.getHouseNumber());
        } catch (Exception e) {
            fieldedAddress.aHouseNumber = null;
        }
        try {
            fieldedAddress.aAssignedHouseNumber = toStringOpt(respFielded.getAssignedHouseNumber());
        } catch (Exception e) {
            fieldedAddress.aAssignedHouseNumber = null;
        }
        try {
            fieldedAddress.aHouseNumberSuffix = toStringOpt(respFielded.getHouseNumberSuffix());
        } catch (Exception e) {
            fieldedAddress.aHouseNumberSuffix = null;
        }
        try {
            fieldedAddress.aStreetDirection = toStringOpt(respFielded.getStreetDirection());
        } catch (Exception e) {
            fieldedAddress.aStreetDirection = null;
        }
        try {
            fieldedAddress.aStreetName = toStringOpt(respFielded.getStreetName());
        } catch (Exception e) {
            fieldedAddress.aStreetName = null;
        }
        try {
            fieldedAddress.aStreetThoroughfare = toStringOpt(respFielded.getStreetThoroughfare());
        } catch (Exception e) {
            fieldedAddress.aStreetThoroughfare = null;
        }
        try {
            fieldedAddress.aStreetNameSuffix = toStringOpt(respFielded.getStreetNameSuffix());
        } catch (Exception e) {
            fieldedAddress.aStreetNameSuffix = null;
        }
        try {
            fieldedAddress.aCity = toStringOpt(respFielded.getCity());
        } catch (Exception e) {
            fieldedAddress.aCity = null;
        }
        try {
            fieldedAddress.aState = toStringOpt(respFielded.getState().toString());
        } catch (Exception e) {
            fieldedAddress.aState = null;
        }
        try {
            fieldedAddress.aPostalCode = toStringOpt(respFielded.getPostalCode());
        } catch (Exception e) {
            fieldedAddress.aPostalCode = null;
        }
        try {
            fieldedAddress.aPostalCodePlus4 = toStringOpt(respFielded.getPostalCodePlus4());
        } catch (Exception e) {
            fieldedAddress.aPostalCodePlus4 = null;
        }
        try {
            fieldedAddress.aCounty = toStringOpt(respFielded.getCounty());
        } catch (Exception e) {
            fieldedAddress.aCounty = null;
        }
        try {
            fieldedAddress.aCountry = toStringOpt(respFielded.getCountry());
        } catch (Exception e) {
            fieldedAddress.aCountry = null;
        }
        try {
            fieldedAddress.aStructureType = toStringOpt(respFielded.getStructureType());
        } catch (Exception e) {
            fieldedAddress.aStructureType = null;
        }
        try {
            fieldedAddress.aStructureValue = toStringOpt(respFielded.getStructureValue());
        } catch (Exception e) {
            fieldedAddress.aStructureValue = null;
        }
        try {
            fieldedAddress.aLevelType = toStringOpt(respFielded.getLevelType());
        } catch (Exception e) {
            fieldedAddress.aLevelType = null;
        }
        try {
            fieldedAddress.aLevelValue = toStringOpt(respFielded.getLevelValue());
        } catch (Exception e) {
            fieldedAddress.aLevelValue = null;
        }
        try {
            fieldedAddress.aUnitType = toStringOpt(respFielded.getUnitType());
        } catch (Exception e) {
            fieldedAddress.aUnitType = null;
        }
        try {
            fieldedAddress.aUnitValue = toStringOpt(respFielded.getUnitValue());
        } catch (Exception e) {
            fieldedAddress.aUnitValue = null;
        }
//        try {
//            fieldedAddress.aOriginalStreetDirection = toStringOpt(respFielded);
//        } catch (Exception e) {
            fieldedAddress.aOriginalStreetDirection = null;
//        }
//        try {
//            fieldedAddress.aOriginalStreetNameSuffix = toStringOpt(respFielded.get);
//        } catch (Exception e) {
            fieldedAddress.aOriginalStreetNameSuffix = null;
//        }
//        try {
//            fieldedAddress.aCassAdditionalInfo = toStringOpt(respFielded.getCa);
//        } catch (Exception e) {
            fieldedAddress.aCassAdditionalInfo = null;
//        }
//        try {
//            fieldedAddress.aAdditionalInfo = toStringOpt(respFielded.getAd);
//        } catch (Exception e) {
            fieldedAddress.aAdditionalInfo = null;
//        }
        try {
            fieldedAddress.aBusinessName = toStringOpt(response.getUspsDeliveryPointValidationAttributes().getLocationProperties().getPostalSupplementalData().getBusinessName());
        } catch (Exception e) {
            fieldedAddress.aBusinessName = null;
        }
//        try {
//            fieldedAddress.aCountryCode = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress.aCountryCode = null;
//        }
//        try {
//            fieldedAddress.aCityCode = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress.aCityCode = null;
//        }
//        try {
//            fieldedAddress.aServiceLocationName = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress.aServiceLocationName = null;
//        }
        try {
            fieldedAddress.aAddressId = toStringOpt(response.getSagLocationAttributes().getSagProperties().getSagAddressId());
        } catch (Exception e) {
            fieldedAddress.aAddressId = null;
        }
//        try {
//            fieldedAddress.aAliasName = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress.aAliasName = null;
//        }
//        try {
//            fieldedAddress.aAttention = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress.aAttention = null;
//        }
        fieldedAddress.aCassAddressLines = new StringSeqOpt();
        fieldedAddress.aAuxiliaryAddressLines = new StringSeqOpt();
        UnfieldedAddress unfieldedAddress = new UnfieldedAddress();
//        UnfieldedAddress respUnfielded = response.getSagLocationAttributes().getAddress().getDescriptiveAlternatives().
//        try {
//            unfieldedAddress.aCity = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress.aCity = null;
//        }
//        try {
//            unfieldedAddress.aState = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress.aState = null;
//        }
//        try {
//            unfieldedAddress.aPostalCode = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress.aPostalCode = null;
//        }
//        try {
//            unfieldedAddress.aPostalCodePlus4 = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress.aPostalCodePlus4 = null;
//        }
//        try {
//            unfieldedAddress.aCounty = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress.aCounty = null;
//        }
//        try {
//            unfieldedAddress.aCountry = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress.aCountry = null;
//        }
//        try {
//            unfieldedAddress.aStructureType = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress.aStructureType = null;
//        }
//        try {
//            unfieldedAddress.aStructureValue = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress.aStructureValue = null;
//        }
//        try {
//            unfieldedAddress.aLevelType = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress.aLevelType = null;
//        }
//        try {
//            unfieldedAddress.aLevelValue = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress.aLevelValue = null;
//        }
//        try {
//            unfieldedAddress.aUnitType = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress.aUnitType = null;
//        }
//        try {
//            unfieldedAddress.aUnitValue = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress.aUnitValue = null;
//        }
//        try {
//            unfieldedAddress.aAdditionalInfo = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress.aAdditionalInfo = null;
//        }
//        try {
//            unfieldedAddress.aBusinessName = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress.aBusinessName = null;
//        }
//        try {
//            unfieldedAddress.aCountryCode = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress.aCountryCode = null;
//        }
//        try {
//            unfieldedAddress.aCityCode = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress.aCityCode = null;
//        }
//        try {
//            unfieldedAddress.aServiceLocationName = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress.aServiceLocationName = null;
//        }
//        try {
//            unfieldedAddress.aAddressId = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress.aAddressId = null;
//        }
//        try {
//            unfieldedAddress.aAliasName = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress.aAliasName = null;
//        }
//        try {
//            unfieldedAddress.aAttention = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress.aAttention = null;
//        }
        unfieldedAddress.aAddressLines = new StringSeqOpt();
        Address address = new Address();
        address.aFieldedAddress(fieldedAddress);
        address.aUnfieldedAddress(unfieldedAddress);
        providerLocationProperty.aPostalAddress.theValue(address);

        providerLocationProperty.aServiceAddress = new AddressOpt();
        FieldedAddress fieldedAddress1 = new FieldedAddress();
//        try {
//            fieldedAddress1.aRoute = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress1.aRoute = null;
//        }
//        try {
//            fieldedAddress1.aBox = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress1.aBox = null;
//        }
//        try {
//            fieldedAddress1.aHouseNumberPrefix = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress1.aHouseNumberPrefix = null;
//        }
//        try {
//            fieldedAddress1.aHouseNumber = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress1.aHouseNumber = null;
//        }
//        try {
//            fieldedAddress1.aAssignedHouseNumber = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress1.aAssignedHouseNumber = null;
//        }
//        try {
//            fieldedAddress1.aHouseNumberSuffix = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress1.aHouseNumberSuffix = null;
//        }
//        try {
//            fieldedAddress1.aStreetDirection = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress1.aStreetDirection = null;
//        }
//        try {
//            fieldedAddress1.aStreetName = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress1.aStreetName = null;
//        }
//        try {
//            fieldedAddress1.aStreetThoroughfare = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress1.aStreetThoroughfare = null;
//        }
//        try {
//            fieldedAddress1.aStreetNameSuffix = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress1.aStreetNameSuffix = null;
//        }
//        try {
//            fieldedAddress1.aCity = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress1.aCity = null;
//        }
//        try {
//            fieldedAddress1.aState = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress1.aState = null;
//        }
//        try {
//            fieldedAddress1.aPostalCode = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress1.aPostalCode = null;
//        }
//        try {
//            fieldedAddress1.aPostalCodePlus4 = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress1.aPostalCodePlus4 = null;
//        }
//        try {
//            fieldedAddress1.aCounty = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress1.aCounty = null;
//        }
//        try {
//            fieldedAddress1.aCountry = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress1.aCountry = null;
//        }
//        try {
//            fieldedAddress1.aStructureType = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress1.aStructureType = null;
//        }
//        try {
//            fieldedAddress1.aStructureValue = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress1.aStructureValue = null;
//        }
//        try {
//            fieldedAddress1.aLevelType = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress1.aLevelType = null;
//        }
//        try {
//            fieldedAddress1.aLevelValue = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress1.aLevelValue = null;
//        }
//        try {
//            fieldedAddress1.aUnitType = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress1.aUnitType = null;
//        }
//        try {
//            fieldedAddress1.aUnitValue = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress1.aUnitValue = null;
//        }
//        try {
//            fieldedAddress1.aOriginalStreetDirection = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress1.aOriginalStreetDirection = null;
//        }
//        try {
//            fieldedAddress1.aOriginalStreetNameSuffix = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress1.aOriginalStreetNameSuffix = null;
//        }
//        try {
//            fieldedAddress1.aCassAdditionalInfo = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress1.aCassAdditionalInfo = null;
//        }
//        try {
//            fieldedAddress1.aAdditionalInfo = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress1.aAdditionalInfo = null;
//        }
//        try {
//            fieldedAddress1.aBusinessName = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress1.aBusinessName = null;
//        }
//        try {
//            fieldedAddress1.aCountryCode = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress1.aCountryCode = null;
//        }
//        try {
//            fieldedAddress1.aCityCode = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress1.aCityCode = null;
//        }
//        try {
//            fieldedAddress1.aServiceLocationName = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress1.aServiceLocationName = null;
//        }
//        try {
//            fieldedAddress1.aAddressId = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress1.aAddressId = null;
//        }
//        try {
//            fieldedAddress1.aAliasName = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress1.aAliasName = null;
//        }
//        try {
//            fieldedAddress1.aAttention = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress1.aAttention = null;
//        }
        fieldedAddress1.aCassAddressLines = new StringSeqOpt();
        fieldedAddress1.aAuxiliaryAddressLines = new StringSeqOpt();
        UnfieldedAddress unfieldedAddress1 = new UnfieldedAddress();
//        try {
//            unfieldedAddress1.aCity = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress1.aCity = null;
//        }
//        try {
//            unfieldedAddress1.aState = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress1.aState = null;
//        }
//        try {
//            unfieldedAddress1.aPostalCode = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress1.aPostalCode = null;
//        }
//        try {
//            unfieldedAddress1.aPostalCodePlus4 = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress1.aPostalCodePlus4 = null;
//        }
//        try {
//            unfieldedAddress1.aCounty = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress1.aCounty = null;
//        }
//        try {
//            unfieldedAddress1.aCountry = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress1.aCountry = null;
//        }
//        try {
//            unfieldedAddress1.aStructureType = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress1.aStructureType = null;
//        }
//        try {
//            unfieldedAddress1.aStructureValue = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress1.aStructureValue = null;
//        }
//        try {
//            unfieldedAddress1.aLevelType = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress1.aLevelType = null;
//        }
//        try {
//            unfieldedAddress1.aLevelValue = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress1.aLevelValue = null;
//        }
//        try {
//            unfieldedAddress1.aUnitType = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress1.aUnitType = null;
//        }
//        try {
//            unfieldedAddress1.aUnitValue = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress1.aUnitValue = null;
//        }
//        try {
//            unfieldedAddress1.aAdditionalInfo = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress1.aAdditionalInfo = null;
//        }
//        try {
//            unfieldedAddress1.aBusinessName = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress1.aBusinessName = null;
//        }
//        try {
//            unfieldedAddress1.aCountryCode = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress1.aCountryCode = null;
//        }
//        try {
//            unfieldedAddress1.aCityCode = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress1.aCityCode = null;
//        }
//        try {
//            unfieldedAddress1.aServiceLocationName = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress1.aServiceLocationName = null;
//        }
//        try {
//            unfieldedAddress1.aAddressId = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress1.aAddressId = null;
//        }
//        try {
//            unfieldedAddress1.aAliasName = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress1.aAliasName = null;
//        }
//        try {
//            unfieldedAddress1.aAttention = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress1.aAttention = null;
//        }
        unfieldedAddress1.aAddressLines = new StringSeqOpt();
        Address address1 = new Address();
        address1.aFieldedAddress(fieldedAddress1);
        address1.aUnfieldedAddress(unfieldedAddress1);
        providerLocationProperty.aServiceAddress.theValue(address1);

        providerLocationProperty.aE911Address = new AddressOpt();
        FieldedAddress fieldedAddress2 = new FieldedAddress();
//        try {
//            fieldedAddress2.aRoute = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress2.aRoute = null;
//        }
//        try {
//            fieldedAddress2.aBox = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress2.aBox = null;
//        }
//        try {
//            fieldedAddress2.aHouseNumberPrefix = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress2.aHouseNumberPrefix = null;
//        }
//        try {
//            fieldedAddress2.aHouseNumber = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress2.aHouseNumber = null;
//        }
//        try {
//            fieldedAddress2.aAssignedHouseNumber = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress2.aAssignedHouseNumber = null;
//        }
//        try {
//            fieldedAddress2.aHouseNumberSuffix = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress2.aHouseNumberSuffix = null;
//        }
//        try {
//            fieldedAddress2.aStreetDirection = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress2.aStreetDirection = null;
//        }
//        try {
//            fieldedAddress2.aStreetName = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress2.aStreetName = null;
//        }
//        try {
//            fieldedAddress2.aStreetThoroughfare = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress2.aStreetThoroughfare = null;
//        }
//        try {
//            fieldedAddress2.aStreetNameSuffix = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress2.aStreetNameSuffix = null;
//        }
//        try {
//            fieldedAddress2.aCity = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress2.aCity = null;
//        }
//        try {
//            fieldedAddress2.aState = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress2.aState = null;
//        }
//        try {
//            fieldedAddress2.aPostalCode = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress2.aPostalCode = null;
//        }
//        try {
//            fieldedAddress2.aPostalCodePlus4 = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress2.aPostalCodePlus4 = null;
//        }
//        try {
//            fieldedAddress2.aCounty = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress2.aCounty = null;
//        }
//        try {
//            fieldedAddress2.aCountry = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress2.aCountry = null;
//        }
//        try {
//            fieldedAddress2.aStructureType = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress2.aStructureType = null;
//        }
//        try {
//            fieldedAddress2.aStructureValue = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress2.aStructureValue = null;
//        }
//        try {
//            fieldedAddress2.aLevelType = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress2.aLevelType = null;
//        }
//        try {
//            fieldedAddress2.aLevelValue = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress2.aLevelValue = null;
//        }
//        try {
//            fieldedAddress2.aUnitType = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress2.aUnitType = null;
//        }
//        try {
//            fieldedAddress2.aUnitValue = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress2.aUnitValue = null;
//        }
//        try {
//            fieldedAddress2.aOriginalStreetDirection = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress2.aOriginalStreetDirection = null;
//        }
//        try {
//            fieldedAddress2.aOriginalStreetNameSuffix = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress2.aOriginalStreetNameSuffix = null;
//        }
//        try {
//            fieldedAddress2.aCassAdditionalInfo = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress2.aCassAdditionalInfo = null;
//        }
//        try {
//            fieldedAddress2.aAdditionalInfo = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress2.aAdditionalInfo = null;
//        }
//        try {
//          fieldedAddress2.aBusinessName = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress2.aBusinessName = null;
//        }
//        try {
//            fieldedAddress2.aCountryCode = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress2.aCountryCode = null;
//        }
//        try {
//            fieldedAddress2.aCityCode = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress2.aCityCode = null;
//        }
//        try {
//            fieldedAddress2.aServiceLocationName = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress2.aServiceLocationName = null;
//        }
//        try {
//            fieldedAddress2.aAddressId = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress2.aAddressId = null;
//        }
//        try {
//            fieldedAddress2.aAliasName = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress2.aAliasName = null;
//        }
//        try {
//            fieldedAddress2.aAttention = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress2.aAttention = null;
//        }
        fieldedAddress2.aCassAddressLines = new StringSeqOpt();
        fieldedAddress2.aAuxiliaryAddressLines = new StringSeqOpt();
        UnfieldedAddress unfieldedAddress2 = new UnfieldedAddress();
//        try {
//            unfieldedAddress2.aCity = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress2.aCity = null;
//        }
//        try {
//            unfieldedAddress2.aState = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress2.aState = null;
//        }
//        try {
//            unfieldedAddress2.aPostalCode = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress2.aPostalCode = null;
//        }
//        try {
//            unfieldedAddress2.aPostalCodePlus4 = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress2.aPostalCodePlus4 = null;
//        }
//        try {
//            unfieldedAddress2.aCounty = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress2.aCounty = null;
//        }
//        try {
//            unfieldedAddress2.aCountry = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress2.aCountry = null;
//        }
//        try {
//            unfieldedAddress2.aStructureType = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress2.aStructureType = null;
//        }
//        try {
//            unfieldedAddress2.aStructureValue = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress2.aStructureValue = null;
//        }
//        try {
//            unfieldedAddress2.aLevelType = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress2.aLevelType = null;
//        }
//        try {
//            unfieldedAddress2.aLevelValue = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress2.aLevelValue = null;
//        }
//        try {
//            unfieldedAddress2.aUnitType = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress2.aUnitType = null;
//        }
//        try {
//            unfieldedAddress2.aUnitValue = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress2.aUnitValue = null;
//        }
//        try {
//            unfieldedAddress2.aAdditionalInfo = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress2.aAdditionalInfo = null;
//        }
//        try {
//            unfieldedAddress2.aBusinessName = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress2.aBusinessName = null;
//        }
//        try {
//            unfieldedAddress2.aCountryCode = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress2.aCountryCode = null;
//        }
//        try {
//            unfieldedAddress2.aCityCode = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress2.aCityCode = null;
//        }
//        try {
//            unfieldedAddress2.aServiceLocationName = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress2.aServiceLocationName = null;
//        }
//        try {
//            unfieldedAddress2.aAddressId = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress2.aAddressId = null;
//        }
//        try {
//            unfieldedAddress2.aAliasName = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress2.aAliasName = null;
//        }
//        try {
//            unfieldedAddress2.aAttention = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress2.aAttention = null;
//        }
        unfieldedAddress2.aAddressLines = new StringSeqOpt();
        Address address2 = new Address();
        address2.aFieldedAddress(fieldedAddress2);
        address2.aUnfieldedAddress(unfieldedAddress2);
        providerLocationProperty.aE911Address.theValue(address2);

        providerLocationProperty.aSwitchSuperPopAddress = new AddressOpt();
        FieldedAddress fieldedAddress3 = new FieldedAddress();
//        try {
//            fieldedAddress3.aRoute = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress3.aRoute = null;
//        }
//        try {
//            fieldedAddress3.aBox = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress3.aBox = null;
//        }
//        try {
//            fieldedAddress3.aHouseNumberPrefix = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress3.aHouseNumberPrefix = null;
//        }
//        try {
//            fieldedAddress3.aHouseNumber = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress3.aHouseNumber = null;
//        }
//        try {
//            fieldedAddress3.aAssignedHouseNumber = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress3.aAssignedHouseNumber = null;
//        }
//        try {
//            fieldedAddress3.aHouseNumberSuffix = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress3.aHouseNumberSuffix = null;
//        }
//        try {
//            fieldedAddress3.aStreetDirection = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress3.aStreetDirection = null;
//        }
//        try {
//            fieldedAddress3.aStreetName = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress3.aStreetName = null;
//        }
//        try {
//            fieldedAddress3.aStreetThoroughfare = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress3.aStreetThoroughfare = null;
//        }
//        try {
//            fieldedAddress3.aStreetNameSuffix = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress3.aStreetNameSuffix = null;
//        }
//        try {
//            fieldedAddress3.aCity = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress3.aCity = null;
//        }
//        try {
//            fieldedAddress3.aState = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress3.aState = null;
//        }
//        try {
//            fieldedAddress3.aPostalCode = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress3.aPostalCode = null;
//        }
//        try {
//            fieldedAddress3.aPostalCodePlus4 = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress3.aPostalCodePlus4 = null;
//        }
//        try {
//            fieldedAddress3.aCounty = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress3.aCounty = null;
//        }
//        try {
//            fieldedAddress3.aCountry = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress3.aCountry = null;
//        }
//        try {
//            fieldedAddress3.aStructureType = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress3.aStructureType = null;
//        }
//        try {
//            fieldedAddress3.aStructureValue = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress3.aStructureValue = null;
//        }
//        try {
//            fieldedAddress3.aLevelType = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress3.aLevelType = null;
//        }
//        try {
//            fieldedAddress3.aLevelValue = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress3.aLevelValue = null;
//        }
//        try {
//            fieldedAddress3.aUnitType = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress3.aUnitType = null;
//        }
//        try {
//            fieldedAddress3.aUnitValue = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress3.aUnitValue = null;
//        }
//        try {
//            fieldedAddress3.aOriginalStreetDirection = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress3.aOriginalStreetDirection = null;
//        }
//        try {
//            fieldedAddress3.aOriginalStreetNameSuffix = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress3.aOriginalStreetNameSuffix = null;
//        }
//        try {
//            fieldedAddress3.aCassAdditionalInfo = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress3.aCassAdditionalInfo = null;
//        }
//        try {
//            fieldedAddress3.aAdditionalInfo = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress3.aAdditionalInfo = null;
//        }
//        try {
//            fieldedAddress3.aBusinessName = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress3.aBusinessName = null;
//        }
//        try {
//            fieldedAddress3.aCountryCode = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress3.aCountryCode = null;
//        }
//        try {
//            fieldedAddress3.aCityCode = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress3.aCityCode = null;
//        }
//        try {
//            fieldedAddress3.aServiceLocationName = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress3.aServiceLocationName = null;
//        }
//        try {
//            fieldedAddress3.aAddressId = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress3.aAddressId = null;
//        }
//        try {
//            fieldedAddress3.aAliasName = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress3.aAliasName = null;
//        }
//        try {
//            fieldedAddress3.aAttention = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress3.aAttention = null;
//        }
        fieldedAddress3.aCassAddressLines = new StringSeqOpt();
        fieldedAddress3.aAuxiliaryAddressLines = new StringSeqOpt();
        UnfieldedAddress unfieldedAddress3 = new UnfieldedAddress();
//        try {
//            unfieldedAddress3.aCity = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress3.aCity = null;
//        }
//        try {
//            unfieldedAddress3.aState = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress3.aState = null;
//        }
//        try {
//            unfieldedAddress3.aPostalCode = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress3.aPostalCode = null;
//        }
//        try {
//            unfieldedAddress3.aPostalCodePlus4 = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress3.aPostalCodePlus4 = null;
//        }
//        try {
//            unfieldedAddress3.aCounty = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress3.aCounty = null;
//        }
//        try {
//            unfieldedAddress3.aCountry = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress3.aCountry = null;
//        }
//        try {
//            unfieldedAddress3.aStructureType = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress3.aStructureType = null;
//        }
//        try {
//            unfieldedAddress3.aStructureValue = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress3.aStructureValue = null;
//        }
//        try {
//            unfieldedAddress3.aLevelType = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress3.aLevelType = null;
//        }
//        try {
//            unfieldedAddress3.aLevelValue = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress3.aLevelValue = null;
//        }
//        try {
//            unfieldedAddress3.aUnitType = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress3.aUnitType = null;
//        }
//        try {
//            unfieldedAddress3.aUnitValue = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress3.aUnitValue = null;
//        }
//        try {
//            unfieldedAddress3.aAdditionalInfo = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress3.aAdditionalInfo = null;
//        }
//        try {
//            unfieldedAddress3.aBusinessName = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress3.aBusinessName = null;
//        }
//        try {
//            unfieldedAddress3.aCountryCode = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress3.aCountryCode = null;
//        }
//        try {
//            unfieldedAddress3.aCityCode = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress3.aCityCode = null;
//        }
//        try {
//            unfieldedAddress3.aServiceLocationName = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress3.aServiceLocationName = null;
//        }
//        try {
//            unfieldedAddress3.aAddressId = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress3.aAddressId = null;
//        }
//        try {
//            unfieldedAddress3.aAliasName = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress3.aAliasName = null;
//        }
//        try {
//            unfieldedAddress3.aAttention = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress3.aAttention = null;
//        }
        unfieldedAddress3.aAddressLines = new StringSeqOpt();
        Address address3 = new Address();
        address3.aFieldedAddress(fieldedAddress3);
        address3.aUnfieldedAddress(unfieldedAddress3);
        providerLocationProperty.aSwitchSuperPopAddress.theValue(address3);

        providerLocationProperty.aSAGAddress = new AddressOpt();
        FieldedAddress fieldedAddress4 = new FieldedAddress();
        try {
            fieldedAddress4.aRoute = toStringOpt(respFielded.getRoute());
        } catch (Exception e) {
            fieldedAddress4.aRoute = null;
        }
        try {
            fieldedAddress4.aBox = toStringOpt(respFielded.getBox());
        } catch (Exception e) {
            fieldedAddress4.aBox = null;
        }
        try {
            fieldedAddress4.aHouseNumberPrefix = toStringOpt(respFielded.getHouseNumberPrefix());
        } catch (Exception e) {
            fieldedAddress4.aHouseNumberPrefix = null;
        }
        try {
            fieldedAddress4.aHouseNumber = toStringOpt(respFielded.getHouseNumber());
        } catch (Exception e) {
            fieldedAddress4.aHouseNumber = null;
        }
        try {
            fieldedAddress4.aAssignedHouseNumber = toStringOpt(respFielded.getAssignedHouseNumber());
        } catch (Exception e) {
            fieldedAddress4.aAssignedHouseNumber = null;
        }
        try {
            fieldedAddress4.aHouseNumberSuffix = toStringOpt(respFielded.getHouseNumberSuffix());
        } catch (Exception e) {
            fieldedAddress4.aHouseNumberSuffix = null;
        }
        try {
            fieldedAddress4.aStreetDirection = toStringOpt(respFielded.getStreetDirection());
        } catch (Exception e) {
            fieldedAddress4.aStreetDirection = null;
        }
        try {
            fieldedAddress4.aStreetName = toStringOpt(respFielded.getStreetName());
        } catch (Exception e) {
            fieldedAddress4.aStreetName = null;
        }
        try {
            fieldedAddress4.aStreetThoroughfare = toStringOpt(respFielded.getStreetThoroughfare());
        } catch (Exception e) {
            fieldedAddress4.aStreetThoroughfare = null;
        }
        try {
            fieldedAddress4.aStreetNameSuffix = toStringOpt(respFielded.getStreetNameSuffix());
        } catch (Exception e) {
            fieldedAddress4.aStreetNameSuffix = null;
        }
        try {
            fieldedAddress4.aCity = toStringOpt(respFielded.getCity());
        } catch (Exception e) {
            fieldedAddress4.aCity = null;
        }
        try {
            fieldedAddress4.aState = toStringOpt(respFielded.getState().toString());
        } catch (Exception e) {
            fieldedAddress4.aState = null;
        }
        try {
            fieldedAddress4.aPostalCode = toStringOpt(respFielded.getPostalCode());
        } catch (Exception e) {
            fieldedAddress4.aPostalCode = null;
        }
        try {
            fieldedAddress4.aPostalCodePlus4 = toStringOpt(respFielded.getPostalCodePlus4());
        } catch (Exception e) {
            fieldedAddress4.aPostalCodePlus4 = null;
        }
        try {
            fieldedAddress4.aCounty = toStringOpt(respFielded.getCounty());
        } catch (Exception e) {
            fieldedAddress4.aCounty = null;
        }
        try {
            fieldedAddress4.aCountry = toStringOpt(respFielded.getCountry());
        } catch (Exception e) {
            fieldedAddress4.aCountry = null;
        }
        try {
            fieldedAddress4.aStructureType = toStringOpt(respFielded.getStructureType());
        } catch (Exception e) {
            fieldedAddress4.aStructureType = null;
        }
        try {
            fieldedAddress4.aStructureValue = toStringOpt(respFielded.getStructureValue());
        } catch (Exception e) {
            fieldedAddress4.aStructureValue = null;
        }
        try {
            fieldedAddress4.aLevelType = toStringOpt(respFielded.getLevelType());
        } catch (Exception e) {
            fieldedAddress4.aLevelType = null;
        }
        try {
            fieldedAddress4.aLevelValue = toStringOpt(respFielded.getLevelValue());
        } catch (Exception e) {
            fieldedAddress4.aLevelValue = null;
        }
        try {
            fieldedAddress4.aUnitType = toStringOpt(respFielded.getUnitType());
        } catch (Exception e) {
            fieldedAddress4.aUnitType = null;
        }
        try {
            fieldedAddress4.aUnitValue = toStringOpt(respFielded.getUnitValue());
        } catch (Exception e) {
            fieldedAddress4.aUnitValue = null;
        }
//        try {
//            fieldedAddress4.aOriginalStreetDirection = toStringOpt(respFielded.);
//        } catch (Exception e) {
            fieldedAddress4.aOriginalStreetDirection = null;
//        }
//        try {
//            fieldedAddress4.aOriginalStreetNameSuffix = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress4.aOriginalStreetNameSuffix = null;
//        }
//        try {
//            fieldedAddress4.aCassAdditionalInfo = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress4.aCassAdditionalInfo = null;
//        }
//        try {
//            fieldedAddress4.aAdditionalInfo = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress4.aAdditionalInfo = null;
//        }
        try {
            fieldedAddress4.aBusinessName = toStringOpt(response.getUspsDeliveryPointValidationAttributes().getLocationProperties().getPostalSupplementalData().getBusinessName());
        } catch (Exception e) {
            fieldedAddress4.aBusinessName = null;
        }
//        try {
//            fieldedAddress4.aCountryCode = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress4.aCountryCode = null;
//        }
//        try {
//            fieldedAddress4.aCityCode = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress4.aCityCode = null;
//        }
//        try {
//            fieldedAddress4.aServiceLocationName = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress4.aServiceLocationName = null;
//        }
        try {
            fieldedAddress4.aAddressId = toStringOpt(response.getSagLocationAttributes().getSagProperties().getSagAddressId());
        } catch (Exception e) {
            fieldedAddress4.aAddressId = null;
        }
//        try {
//            fieldedAddress4.aAliasName = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress4.aAliasName = null;
//        }
//        try {
//            fieldedAddress4.aAttention = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress4.aAttention = null;
//        }
        fieldedAddress4.aCassAddressLines = new StringSeqOpt();
        fieldedAddress4.aAuxiliaryAddressLines = new StringSeqOpt();
        UnfieldedAddress unfieldedAddress4 = new UnfieldedAddress();
//        try {
//            unfieldedAddress4.aCity = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress4.aCity = null;
//        }
//        try {
//            unfieldedAddress4.aState = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress4.aState = null;
//        }
//        try {
//            unfieldedAddress4.aPostalCode = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress4.aPostalCode = null;
//        }
//        try {
//            unfieldedAddress4.aPostalCodePlus4 = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress4.aPostalCodePlus4 = null;
//        }
//        try {
//            unfieldedAddress4.aCounty = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress4.aCounty = null;
//        }
//        try {
//            unfieldedAddress4.aCountry = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress4.aCountry = null;
//        }
//        try {
//            unfieldedAddress4.aStructureType = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress4.aStructureType = null;
//        }
//        try {
//            unfieldedAddress4.aStructureValue = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress4.aStructureValue = null;
//        }
//        try {
//            unfieldedAddress4.aLevelType = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress4.aLevelType = null;
//        }
//        try {
//            unfieldedAddress4.aLevelValue = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress4.aLevelValue = null;
//        }
//        try {
//            unfieldedAddress4.aUnitType = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress4.aUnitType = null;
//        }
//        try {
//            unfieldedAddress4.aUnitValue = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress4.aUnitValue = null;
//        }
//        try {
//            unfieldedAddress4.aAdditionalInfo = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress4.aAdditionalInfo = null;
//        }
//        try {
//            unfieldedAddress4.aBusinessName = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress4.aBusinessName = null;
//        }
//        try {
//            unfieldedAddress4.aCountryCode = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress4.aCountryCode = null;
//        }
//        try {
//            unfieldedAddress4.aCityCode = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress4.aCityCode = null;
//        }
//        try {
//            unfieldedAddress4.aServiceLocationName = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress4.aServiceLocationName = null;
//        }
//        try {
//            unfieldedAddress4.aAddressId = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress4.aAddressId = null;
//        }
//        try {
//            unfieldedAddress4.aAliasName = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress4.aAliasName = null;
//        }
//        try {
//            unfieldedAddress4.aAttention = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress4.aAttention = null;
//        }
        unfieldedAddress4.aAddressLines = new StringSeqOpt();
        Address address4 = new Address();
        address4.aFieldedAddress(fieldedAddress4);
        address4.aUnfieldedAddress(unfieldedAddress4);
        providerLocationProperty.aSAGAddress.theValue(address4);

        providerLocationProperty.aExtensions = new ExtensionPropertySeqOpt();
        providerLocationProperties[0] = providerLocationProperty;

        location.aProviderLocationProperties = providerLocationProperties;


        AlternativeAddressResult alternativeAddressResult = new AlternativeAddressResult();
        AlternativeAddressSeqOpt alternativeAddressSeqOpt = new AlternativeAddressSeqOpt();
        AlternativeAddress[] alternativeAddresses = new AlternativeAddress[1];
        AlternativeAddress alternativeAddress = new AlternativeAddress();
        FieldedAddress fieldedAddress5 = new FieldedAddress();
//        try {
//            fieldedAddress5.aRoute = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress5.aRoute = null;
//        }
//        try {
//            fieldedAddress5.aBox = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress5.aBox = null;
//        }
//        try {
//            fieldedAddress5.aHouseNumberPrefix = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress5.aHouseNumberPrefix = null;
//        }
//        try {
//            fieldedAddress5.aHouseNumber = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress5.aHouseNumber = null;
//        }
//        try {
//            fieldedAddress5.aAssignedHouseNumber = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress5.aAssignedHouseNumber = null;
//        }
//        try {
//            fieldedAddress5.aHouseNumberSuffix = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress5.aHouseNumberSuffix = null;
//        }
//        try {
//            fieldedAddress5.aStreetDirection = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress5.aStreetDirection = null;
//        }
//        try {
//            fieldedAddress5.aStreetName = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress5.aStreetName = null;
//        }
//        try {
//            fieldedAddress5.aStreetThoroughfare = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress5.aStreetThoroughfare = null;
//        }
//        try {
//            fieldedAddress5.aStreetNameSuffix = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress5.aStreetNameSuffix = null;
//        }
//        try {
//            fieldedAddress5.aCity = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress5.aCity = null;
//        }
//        try {
//            fieldedAddress5.aState = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress5.aState = null;
//        }
//        try {
//            fieldedAddress5.aPostalCode = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress5.aPostalCode = null;
//        }
//        try {
//            fieldedAddress5.aPostalCodePlus4 = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress5.aPostalCodePlus4 = null;
//        }
//        try {
//            fieldedAddress5.aCounty = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress5.aCounty = null;
//        }
//        try {
//            fieldedAddress5.aCountry = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress5.aCountry = null;
//        }
//        try {
//            fieldedAddress5.aStructureType = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress5.aStructureType = null;
//        }
//        try {
//            fieldedAddress5.aStructureValue = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress5.aStructureValue = null;
//        }
//        try {
//            fieldedAddress5.aLevelType = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress5.aLevelType = null;
//        }
//        try {
//            fieldedAddress5.aLevelValue = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress5.aLevelValue = null;
//        }
//        try {
//            fieldedAddress5.aUnitType = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress5.aUnitType = null;
//        }
//        try {
//            fieldedAddress5.aUnitValue = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress5.aUnitValue = null;
//        }
//        try {
//            fieldedAddress5.aOriginalStreetDirection = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress5.aOriginalStreetDirection = null;
//        }
//        try {
//            fieldedAddress5.aOriginalStreetNameSuffix = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress5.aOriginalStreetNameSuffix = null;
//        }
//        try {
//            fieldedAddress5.aCassAdditionalInfo = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress5.aCassAdditionalInfo = null;
//        }
//        try {
//            fieldedAddress5.aAdditionalInfo = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress5.aAdditionalInfo = null;
//        }
//        try {
//            fieldedAddress5.aBusinessName = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress5.aBusinessName = null;
//        }
//        try {
//            fieldedAddress5.aCountryCode = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress5.aCountryCode = null;
//        }
//        try {
//            fieldedAddress5.aCityCode = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress5.aCityCode = null;
//        }
//        try {
//            fieldedAddress5.aServiceLocationName = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress5.aServiceLocationName = null;
//        }
//        try {
//            fieldedAddress5.aAddressId = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress5.aAddressId = null;
//        }
//        try {
//            fieldedAddress5.aAliasName = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress5.aAliasName = null;
//        }
//        try {
//            fieldedAddress5.aAttention = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress5.aAttention = null;
//        }
        fieldedAddress5.aCassAddressLines = new StringSeqOpt();
        fieldedAddress5.aAuxiliaryAddressLines = new StringSeqOpt();
        UnfieldedAddress unfieldedAddress5 = new UnfieldedAddress();
//        try {
//            unfieldedAddress5.aCity = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress5.aCity = null;
//        }
//        try {
//            unfieldedAddress5.aState = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress5.aState = null;
//        }
//        try {
//            unfieldedAddress5.aPostalCode = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress5.aPostalCode = null;
//        }
//        try {
//            unfieldedAddress5.aPostalCodePlus4 = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress5.aPostalCodePlus4 = null;
//        }
//        try {
//            unfieldedAddress5.aCounty = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress5.aCounty = null;
//        }
//        try {
//            unfieldedAddress5.aCountry = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress5.aCountry = null;
//        }
//        try {
//            unfieldedAddress5.aStructureType = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress5.aStructureType = null;
//        }
//        try {
//            unfieldedAddress5.aStructureValue = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress5.aStructureValue = null;
//        }
//        try {
//            unfieldedAddress5.aLevelType = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress5.aLevelType = null;
//        }
//        try {
//            unfieldedAddress5.aLevelValue = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress5.aLevelValue = null;
//        }
//        try {
//            unfieldedAddress5.aUnitType = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress5.aUnitType = null;
//        }
//        try {
//            unfieldedAddress5.aUnitValue = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress5.aUnitValue = null;
//        }
//        try {
//            unfieldedAddress5.aAdditionalInfo = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress5.aAdditionalInfo = null;
//        }
//        try {
//            unfieldedAddress5.aBusinessName = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress5.aBusinessName = null;
//        }
//        try {
//            unfieldedAddress5.aCountryCode = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress5.aCountryCode = null;
//        }
//        try {
//            unfieldedAddress5.aCityCode = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress5.aCityCode = null;
//        }
//        try {
//            unfieldedAddress5.aServiceLocationName = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress5.aServiceLocationName = null;
//        }
//        try {
//            unfieldedAddress5.aAddressId = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress5.aAddressId = null;
//        }
//        try {
//            unfieldedAddress5.aAliasName = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress5.aAliasName = null;
//        }
//        try {
//            unfieldedAddress5.aAttention = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress5.aAttention = null;
//        }
        unfieldedAddress5.aAddressLines = new StringSeqOpt();
        Address address5 = new Address();
        address5.aUnfieldedAddress(unfieldedAddress5);
        address5.aFieldedAddress(fieldedAddress5);
        alternativeAddress.aAddress(address5);

        RangedAddress rangedAddress = new RangedAddress();
//        try {
//            rangedAddress.aHouseNumberLow = toStringOpt();
//        } catch (Exception e) {
            rangedAddress.aHouseNumberLow = null;
//        }
//        try {
//            rangedAddress.aHouseNumberHigh = toStringOpt();
//        } catch (Exception e) {
            rangedAddress.aHouseNumberHigh = null;
//        }
//        try {
//            rangedAddress.aHouseNumberPrefix = toStringOpt();
//        } catch (Exception e) {
            rangedAddress.aHouseNumberPrefix = null;
//        }
//        try {
//            rangedAddress.aHouseNumberPrefixHigh = toStringOpt();
//        } catch (Exception e) {
            rangedAddress.aHouseNumberPrefixHigh = null;
//        }
//        try {
//            rangedAddress.aHouseNumberSuffix = toStringOpt();
//        } catch (Exception e) {
            rangedAddress.aHouseNumberSuffix = null;
//        }
//        try {
//            rangedAddress.aHouseNumberSuffixHigh = toStringOpt();
//        } catch (Exception e) {
            rangedAddress.aHouseNumberSuffixHigh = null;
//        }
//        try {
//            rangedAddress.aStreetDirection = toStringOpt();
//        } catch (Exception e) {
            rangedAddress.aStreetDirection = null;
//        }
//        try {
//            rangedAddress.aStreetName = toStringOpt();
//        } catch (Exception e) {
            rangedAddress.aStreetName = null;
//        }
//        try {
//            rangedAddress.aStreetThoroughfare = toStringOpt();
//        } catch (Exception e) {
            rangedAddress.aStreetThoroughfare = null;
//        }
//        try {
//            rangedAddress.aStreetNameSuffix = toStringOpt();
//        } catch (Exception e) {
            rangedAddress.aStreetNameSuffix = null;
//        }
//        try {
//            rangedAddress.aCity = toStringOpt();
//        } catch (Exception e) {
            rangedAddress.aCity = null;
//        }
//        try {
//            rangedAddress.aState = toStringOpt();
//        } catch (Exception e) {
            rangedAddress.aState = null;
//        }
//        try {
//            rangedAddress.aPostalCode = toStringOpt();
//        } catch (Exception e) {
            rangedAddress.aPostalCode = null;
//        }
//        try {
//            rangedAddress.aPostalCodePlus4 = toStringOpt();
//        } catch (Exception e) {
            rangedAddress.aPostalCodePlus4 = null;
//        }
//        try {
//            rangedAddress.aCounty = toStringOpt();
//        } catch (Exception e) {
            rangedAddress.aCounty = null;
//        }
//        try {
//            rangedAddress.aCountry = toStringOpt();
//        } catch (Exception e) {
            rangedAddress.aCountry = null;
//        }
//        try {
//            rangedAddress.aAdditionalInfo = toStringOpt();
//        } catch (Exception e) {
            rangedAddress.aAdditionalInfo = null;
//        }
        alternativeAddress.aRangedAddress(rangedAddress);


        Location location2 = new Location();
//        try {
//            location2.aLocationId = toStringOpt();
//        } catch (Exception e) {
            location2.aLocationId = null;
//        }
        ProviderLocationProperty[] providerLocationProperties2 = new ProviderLocationProperty[1];
        ProviderLocationProperty providerLocationProperty2 = new ProviderLocationProperty();
//        try {
//            providerLocationProperty2.aProviderName = toStringOpt();
//        } catch (Exception e) {
            providerLocationProperty2.aProviderName = null;
//        }
//        try {
//            providerLocationProperty2.aAddressMatchCode = toStringOpt();
//        } catch (Exception e) {
            providerLocationProperty2.aAddressMatchCode = null;
//        }
//        try {
//            providerLocationProperty2.aAddressMatchCodeDescription = toStringOpt();
//        } catch (Exception e) {
            providerLocationProperty2.aAddressMatchCodeDescription = null;
//        }
//        try {
//            providerLocationProperty2.aCoSwitchSuperPop = toStringOpt();
//        } catch (Exception e) {
            providerLocationProperty2.aCoSwitchSuperPop = null;
//        }
//        try {
//            providerLocationProperty2.aCentralOfficeType = toStringOpt();
//        } catch (Exception e) {
            providerLocationProperty2.aCentralOfficeType = null;
//        }
//        try {
//            providerLocationProperty2.aCentralOfficeCode = toStringOpt();
//        } catch (Exception e) {
            providerLocationProperty2.aCentralOfficeCode = null;
//        }
        try {
            providerLocationProperty2.aCommunityName = toStringOpt(response.getSagLocationAttributes().getAddress().getDescriptiveAlternatives().getCommunityName());
        } catch (Exception e) {
            providerLocationProperty2.aCommunityName = null;
        }
//        try {
//            providerLocationProperty2.aDomSwitchPop = toStringOpt();
//        } catch (Exception e) {
            providerLocationProperty2.aDomSwitchPop = null;
//        }
//        try {
//            providerLocationProperty2.aE911Exempt = toStringOpt();
//        } catch (Exception e) {
            providerLocationProperty2.aE911Exempt = null;
//        }
//        try {
//            providerLocationProperty2.aE911NonRecurringCharge = toStringOpt();
//        } catch (Exception e) {
            providerLocationProperty2.aE911NonRecurringCharge = null;
//        }
//        try {
//            providerLocationProperty2.aE911Surcharge = toStringOpt();
//        } catch (Exception e) {
            providerLocationProperty2.aE911Surcharge = null;
//        }
//        try {
//            providerLocationProperty2.aExchangeCode = toStringOpt();
//        } catch (Exception e) {
            providerLocationProperty2.aExchangeCode = null;
//        }
//        try {
//            providerLocationProperty2.aGeoCode = toStringOpt();
//        } catch (Exception e) {
            providerLocationProperty2.aGeoCode = null;
//        }
//        try {
//            providerLocationProperty2.aHorizontalCoordinate = toStringOpt();
//        } catch (Exception e) {
            providerLocationProperty2.aHorizontalCoordinate = null;
//        }
//        try {
//            providerLocationProperty2.aLataCode = toStringOpt();
//        } catch (Exception e) {
            providerLocationProperty2.aLataCode = null;
//        }
//        try {
//            providerLocationProperty2.aLataName = toStringOpt();
//        } catch (Exception e) {
            providerLocationProperty2.aLataName = null;
//        }
//        try {
//            providerLocationProperty2.aLatitude = toStringOpt();
//        } catch (Exception e) {
            providerLocationProperty2.aLatitude = null;
//        }
//        try {
//            providerLocationProperty2.aLongitude = toStringOpt();
//        } catch (Exception e) {
            providerLocationProperty2.aLongitude = null;
//        }
//        try {
//            providerLocationProperty2.aLocalProviderAbbreviatedName = toStringOpt();
//        } catch (Exception e) {
            providerLocationProperty2.aLocalProviderAbbreviatedName = null;
//        }
//        try {
//            providerLocationProperty2.aLocalProviderExchangeCode = toStringOpt();
//        } catch (Exception e) {
            providerLocationProperty2.aLocalProviderExchangeCode = null;
//        }
//        try {
//            providerLocationProperty2.aLocalProviderName = toStringOpt();
//        } catch (Exception e) {
            providerLocationProperty2.aLocalProviderName = null;
//        }
//        try {
//            providerLocationProperty2.aLocalProviderNumber = toStringOpt();
//        } catch (Exception e) {
            providerLocationProperty2.aLocalProviderNumber = null;
//        }
//        try {
//            providerLocationProperty2.aLocalProviderServingOfficeCode = toStringOpt();
//        } catch (Exception e) {
            providerLocationProperty2.aLocalProviderServingOfficeCode = null;
//        }
//        try {
//            providerLocationProperty2.aMailingBarCodeSuffix = toStringOpt();
//        } catch (Exception e) {
            providerLocationProperty2.aMailingBarCodeSuffix = null;
//        }
//        try {
//            providerLocationProperty2.aMsaCode = toStringOpt();
//        } catch (Exception e) {
            providerLocationProperty2.aMsaCode = null;
//        }
//        try {
//            providerLocationProperty2.aMsaName = toStringOpt();
//        } catch (Exception e) {
            providerLocationProperty2.aMsaName = null;
//        }
//        try {
//            providerLocationProperty2.aNearestDistanceColoToCo = toStringOpt();
//        } catch (Exception e) {
            providerLocationProperty2.aNearestDistanceColoToCo = null;
//        }
//        try {
//            providerLocationProperty2.aNearestDistanceSuperPopToCo = toStringOpt();
//        } catch (Exception e) {
            providerLocationProperty2.aNearestDistanceSuperPopToCo = null;
//        }
//        try {
//            providerLocationProperty2.aNearestSbcColo = toStringOpt();
//        } catch (Exception e) {
            providerLocationProperty2.aNearestSbcColo = null;
//        }
//        try {
//            providerLocationProperty2.aNearestSbcCoSuperPop = toStringOpt();
//        } catch (Exception e) {
            providerLocationProperty2.aNearestSbcCoSuperPop = null;
//        }
//        try {
//            providerLocationProperty2.aNearestSbcCoWirecenter = toStringOpt();
//        } catch (Exception e) {
            providerLocationProperty2.aNearestSbcCoWirecenter = null;
//        }
//        try {
//            providerLocationProperty2.aOwnedWiring = toStringOpt();
//        } catch (Exception e) {
            providerLocationProperty2.aOwnedWiring = null;
//        }
        try {
            providerLocationProperty2.aPostalCarrierCode = toStringOpt(response.getSagLocationAttributes().getAddress().getDescriptiveAlternatives().getPostalCode());
        } catch (Exception e) {
            providerLocationProperty2.aPostalCarrierCode = null;
        }
//        try {
//            providerLocationProperty2.aProviderName = toStringOpt();
//        } catch (Exception e) {
            providerLocationProperty2.aProviderName = null;
//        }
//        try {
//            providerLocationProperty2.aPrimaryDirectoryCode = toStringOpt();
//        } catch (Exception e) {
            providerLocationProperty2.aPrimaryDirectoryCode = null;
//        }
//        try {
//            providerLocationProperty2.aPrimaryNpaNxx = toStringOpt();
//        } catch (Exception e) {
            providerLocationProperty2.aPrimaryNpaNxx = null;
//        }
//        try {
//            providerLocationProperty2.aQuickDialTone = toStringOpt();
//        } catch (Exception e) {
            providerLocationProperty2.aQuickDialTone = null;
//        }
//        try {
//            providerLocationProperty2.aQuickDialToneNumber = toStringOpt();
//        } catch (Exception e) {
            providerLocationProperty2.aQuickDialToneNumber = null;
//        }
//        try {
//            providerLocationProperty2.aRateCenterCode = toStringOpt();
//        } catch (Exception e) {
            providerLocationProperty2.aRateCenterCode = null;
//        }
//        try {
//            providerLocationProperty2.aRateZone = toStringOpt();
//        } catch (Exception e) {
            providerLocationProperty2.aRateZone = null;
//        }
//        try {
//            providerLocationProperty2.aRateZoneBandCode = toStringOpt();
//        } catch (Exception e) {
            providerLocationProperty2.aRateZoneBandCode = null;
//        }
//        try {
//            providerLocationProperty2.aSagNpa = toStringOpt();
//        } catch (Exception e) {
            providerLocationProperty2.aSagNpa = null;
//        }
//        try {
//            providerLocationProperty2.aSagWireCenter = toStringOpt();
//        } catch (Exception e) {
            providerLocationProperty2.aSagWireCenter = null;
//        }
//        try {
//            providerLocationProperty2.aSbcColoLsoCode = toStringOpt();
//        } catch (Exception e) {
            providerLocationProperty2.aSbcColoLsoCode = null;
//        }
//        try {
//            providerLocationProperty2.aSbcColoWirecenter = toStringOpt();
//        } catch (Exception e) {
            providerLocationProperty2.aSbcColoWirecenter = null;
//        }
//        try {
//            providerLocationProperty2.aSbcServingOfficeCode = toStringOpt();
//        } catch (Exception e) {
            providerLocationProperty2.aSbcServingOfficeCode = null;
//        }
//        try {
//            providerLocationProperty2.aSbcServingOfficeWirecenter = toStringOpt();
//        } catch (Exception e) {
            providerLocationProperty2.aSbcServingOfficeWirecenter = null;
//        }
//        try {
//            providerLocationProperty2.aServingAreaDescription = toStringOpt();
//        } catch (Exception e) {
            providerLocationProperty2.aServingAreaDescription = null;
//        }
//        try {
//            providerLocationProperty2.aStreetAddressGuideArea = toStringOpt();
//        } catch (Exception e) {
            providerLocationProperty2.aStreetAddressGuideArea = null;
//        }
//        try {
//            providerLocationProperty2.aSurcharge4Percent = toStringOpt();
//        } catch (Exception e) {
            providerLocationProperty2.aSurcharge4Percent = null;
//        }
//        try {
//            providerLocationProperty2.aSurcharge16Percent = toStringOpt();
//        } catch (Exception e) {
            providerLocationProperty2.aSurcharge16Percent = null;
//        }
//        try {
//            providerLocationProperty2.aSwitchDataSuperPop = toStringOpt();
//        } catch (Exception e) {
            providerLocationProperty2.aSwitchDataSuperPop = null;
//        }
//        try {
//            providerLocationProperty2.aSwitchVoiceSuperPop = toStringOpt();
//        } catch (Exception e) {
            providerLocationProperty2.aSwitchVoiceSuperPop = null;
//        }
//        try {
//            providerLocationProperty2.aTarCode = toStringOpt();
//        } catch (Exception e) {
            providerLocationProperty2.aTarCode = null;
//        }
//        try {
//            providerLocationProperty2.aTelephoneNumber = toStringOpt();
//        } catch (Exception e) {
            providerLocationProperty2.aTelephoneNumber = null;
//        }
//        try {
//            providerLocationProperty2.aVerticalCoordinate = toStringOpt();
//        } catch (Exception e) {
            providerLocationProperty2.aVerticalCoordinate = null;
//        }
//        try {
//            providerLocationProperty2.aWorkingServiceOnLocation = toStringOpt();
//        } catch (Exception e) {
            providerLocationProperty2.aWorkingServiceOnLocation = null;
//        }
//        try {
//            providerLocationProperty2.aEcktId = toStringOpt();
//        } catch (Exception e) {
            providerLocationProperty2.aEcktId = null;
//        }
//        try {
//            providerLocationProperty2.aCustomerPremiseIndicator = toStringOpt();
//        } catch (Exception e) {
            providerLocationProperty2.aCustomerPremiseIndicator = null;
//        }
//        try {
//            providerLocationProperty2.aBuildingType = toStringOpt();
//        } catch (Exception e) {
            providerLocationProperty2.aBuildingType = null;
//        }
//        try {
//            providerLocationProperty2.aLegalEntity = toStringOpt();
//        } catch (Exception e) {
            providerLocationProperty2.aLegalEntity = null;
//        }
//        try {
//            providerLocationProperty2.aVideoHubOffice = toStringOpt();
//        } catch (Exception e) {
            providerLocationProperty2.aVideoHubOffice = null;
//        }
//        try {
//            providerLocationProperty2.aSmartmoves = toStringOpt();
//        } catch (Exception e) {
            providerLocationProperty2.aSmartmoves = null;
//        }
//        try {
//            providerLocationProperty2.aServingNetworkType = toStringOpt();
//        } catch (Exception e) {
            providerLocationProperty2.aServingNetworkType = null;
//        }
//        try {
//            providerLocationProperty2.aLocationType = toStringOpt();
//        } catch (Exception e) {
            providerLocationProperty2.aLocationType = null;
//        }
//        try {
//            providerLocationProperty2.aCityStatePostalCodeValid = toStringOpt();
//        } catch (Exception e) {
            providerLocationProperty2.aCityStatePostalCodeValid = null;
//        }
//        try {
//            providerLocationProperty2.aPublicSafetyAnsweringPointId = toStringOpt();
//        } catch (Exception e) {
            providerLocationProperty2.aPublicSafetyAnsweringPointId = null;
//        }
//        try {
//            providerLocationProperty2.aCountyId = toStringOpt();
//        } catch (Exception e) {
            providerLocationProperty2.aCountyId = null;
//        }
//        try {
//            providerLocationProperty2.aExceptionCode = toStringOpt();
//        } catch (Exception e) {
            providerLocationProperty2.aExceptionCode = null;
//        }
//        try {
//            providerLocationProperty2.aExceptionDescription = toStringOpt();
//        } catch (Exception e) {
            providerLocationProperty2.aExceptionDescription = null;
//        }
        providerLocationProperty2.aPostalAddress = new AddressOpt();
        FieldedAddress fieldedAddress6 = new FieldedAddress();
//        try {
//            fieldedAddress6.aRoute = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress6.aRoute = null;
//        }
//        try {
//            fieldedAddress6.aBox = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress6.aBox = null;
//        }
//        try {
//            fieldedAddress6.aHouseNumberPrefix = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress6.aHouseNumberPrefix = null;
//        }
//        try {
//            fieldedAddress6.aHouseNumber = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress6.aHouseNumber = null;
//        }
//        try {
//            fieldedAddress6.aAssignedHouseNumber = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress6.aAssignedHouseNumber = null;
//        }
//        try {
//            fieldedAddress6.aHouseNumberSuffix = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress6.aHouseNumberSuffix = null;
//        }
//        try {
//            fieldedAddress6.aStreetDirection = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress6.aStreetDirection = null;
//        }
//        try {
//            fieldedAddress6.aStreetName = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress6.aStreetName = null;
//        }
//        try {
//            fieldedAddress6.aStreetThoroughfare = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress6.aStreetThoroughfare = null;
//        }
//        try {
//            fieldedAddress6.aStreetNameSuffix = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress6.aStreetNameSuffix = null;
//        }
//        try {
//            fieldedAddress6.aCity = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress6.aCity = null;
//        }
//        try {
//            fieldedAddress6.aState = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress6.aState = null;
//        }
//        try {
//            fieldedAddress6.aPostalCode = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress6.aPostalCode = null;
//        }
//        try {
//            fieldedAddress6.aPostalCodePlus4 = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress6.aPostalCodePlus4 = null;
//        }
//        try {
//            fieldedAddress6.aCounty = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress6.aCounty = null;
//        }
//        try {
//            fieldedAddress6.aCountry = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress6.aCountry = null;
//        }
//        try {
//            fieldedAddress6.aStructureType = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress6.aStructureType = null;
//        }
//        try {
//            fieldedAddress6.aStructureValue = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress6.aStructureValue = null;
//        }
//        try {
//            fieldedAddress6.aLevelType = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress6.aLevelType = null;
//        }
//        try {
//            fieldedAddress6.aLevelValue = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress6.aLevelValue = null;
//        }
//        try {
//            fieldedAddress6.aUnitType = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress6.aUnitType = null;
//        }
//        try {
//            fieldedAddress6.aUnitValue = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress6.aUnitValue = null;
//        }
//        try {
//            fieldedAddress6.aOriginalStreetDirection = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress6.aOriginalStreetDirection = null;
//        }
//        try {
//            fieldedAddress6.aOriginalStreetNameSuffix = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress6.aOriginalStreetNameSuffix = null;
//        }
//        try {
//            fieldedAddress6.aCassAdditionalInfo = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress6.aCassAdditionalInfo = null;
//        }
//        try {
//            fieldedAddress6.aAdditionalInfo = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress6.aAdditionalInfo = null;
//        }
//        try {
//            fieldedAddress6.aBusinessName = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress6.aBusinessName = null;
//        }
//        try {
//            fieldedAddress6.aCountryCode = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress6.aCountryCode = null;
//        }
//        try {
//            fieldedAddress6.aCityCode = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress6.aCityCode = null;
//        }
//        try {
//            fieldedAddress6.aServiceLocationName = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress6.aServiceLocationName = null;
//        }
//        try {
//            fieldedAddress6.aAddressId = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress6.aAddressId = null;
//        }
//        try {
//            fieldedAddress6.aAliasName = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress6.aAliasName = null;
//        }
//        try {
//            fieldedAddress6.aAttention = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress6.aAttention = null;
//        }
        fieldedAddress6.aCassAddressLines = new StringSeqOpt();
        fieldedAddress6.aAuxiliaryAddressLines = new StringSeqOpt();
        UnfieldedAddress unfieldedAddress6 = new UnfieldedAddress();
//        try {
//            unfieldedAddress6.aCity = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress6.aCity = null;
//        }
//        try {
//            unfieldedAddress6.aState = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress6.aState = null;
//        }
//        try {
//            unfieldedAddress6.aPostalCode = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress6.aPostalCode = null;
//        }
//        try {
//            unfieldedAddress6.aPostalCodePlus4 = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress6.aPostalCodePlus4 = null;
//        }
//        try {
//            unfieldedAddress6.aCounty = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress6.aCounty = null;
//        }
//        try {
//            unfieldedAddress6.aCountry = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress6.aCountry = null;
//        }
//        try {
//            unfieldedAddress6.aStructureType = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress6.aStructureType = null;
//        }
//        try {
//            unfieldedAddress6.aStructureValue = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress6.aStructureValue = null;
//        }
//        try {
//            unfieldedAddress6.aLevelType = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress6.aLevelType = null;
//        }
//        try {
//            unfieldedAddress6.aLevelValue = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress6.aLevelValue = null;
//        }
//        try {
//            unfieldedAddress6.aUnitType = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress6.aUnitType = null;
//        }
//        try {
//            unfieldedAddress6.aUnitValue = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress6.aUnitValue = null;
//        }
//        try {
//            unfieldedAddress6.aAdditionalInfo = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress6.aAdditionalInfo = null;
//        }
//        try {
//            unfieldedAddress6.aBusinessName = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress6.aBusinessName = null;
//        }
//        try {
//            unfieldedAddress6.aCountryCode = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress6.aCountryCode = null;
//        }
//        try {
//            unfieldedAddress6.aCityCode = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress6.aCityCode = null;
//        }
//        try {
//            unfieldedAddress6.aServiceLocationName = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress6.aServiceLocationName = null;
//        }
//        try {
//            unfieldedAddress6.aAddressId = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress6.aAddressId = null;
//        }
//        try {
//            unfieldedAddress6.aAliasName = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress6.aAliasName = null;
//        }
//        try {
//            unfieldedAddress6.aAttention = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress6.aAttention = null;
//        }
        unfieldedAddress6.aAddressLines = new StringSeqOpt();
        Address address6 = new Address();
        address6.aFieldedAddress(fieldedAddress6);
        address6.aUnfieldedAddress(unfieldedAddress6);
        providerLocationProperty2.aPostalAddress.theValue(address6);

        providerLocationProperty2.aServiceAddress = new AddressOpt();
        FieldedAddress fieldedAddress7 = new FieldedAddress();
//        try {
//            fieldedAddress7.aRoute = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress7.aRoute = null;
//        }
//        try {
//            fieldedAddress7.aBox = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress7.aBox = null;
//        }
//        try {
//            fieldedAddress7.aHouseNumberPrefix = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress7.aHouseNumberPrefix = null;
//        }
//        try {
//            fieldedAddress7.aHouseNumber = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress7.aHouseNumber = null;
//        }
//        try {
//            fieldedAddress7.aAssignedHouseNumber = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress7.aAssignedHouseNumber = null;
//        }
//        try {
//            fieldedAddress7.aHouseNumberSuffix = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress7.aHouseNumberSuffix = null;
//        }
//        try {
//            fieldedAddress7.aStreetDirection = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress7.aStreetDirection = null;
//        }
//        try {
//            fieldedAddress7.aStreetName = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress7.aStreetName = null;
//        }
//        try {
//            fieldedAddress7.aStreetThoroughfare = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress7.aStreetThoroughfare = null;
//        }
//        try {
//            fieldedAddress7.aStreetNameSuffix = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress7.aStreetNameSuffix = null;
//        }
//        try {
//            fieldedAddress7.aCity = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress7.aCity = null;
//        }
//        try {
//            fieldedAddress7.aState = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress7.aState = null;
//        }
//        try {
//            fieldedAddress7.aPostalCode = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress7.aPostalCode = null;
//        }
//        try {
//            fieldedAddress7.aPostalCodePlus4 = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress7.aPostalCodePlus4 = null;
//        }
//        try {
//            fieldedAddress7.aCounty = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress7.aCounty = null;
//        }
//        try {
//            fieldedAddress7.aCountry = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress7.aCountry = null;
//        }
//        try {
//            fieldedAddress7.aStructureType = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress7.aStructureType = null;
//        }
//        try {
//            fieldedAddress7.aStructureValue = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress7.aStructureValue = null;
//        }
//        try {
//            fieldedAddress7.aLevelType = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress7.aLevelType = null;
//        }
//        try {
//            fieldedAddress7.aLevelValue = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress7.aLevelValue = null;
//        }
//        try {
//            fieldedAddress7.aUnitType = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress7.aUnitType = null;
//        }
//        try {
//            fieldedAddress7.aUnitValue = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress7.aUnitValue = null;
//        }
//        try {
//            fieldedAddress7.aOriginalStreetDirection = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress7.aOriginalStreetDirection = null;
//        }
//        try {
//            fieldedAddress7.aOriginalStreetNameSuffix = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress7.aOriginalStreetNameSuffix = null;
//        }
//        try {
//            fieldedAddress7.aCassAdditionalInfo = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress7.aCassAdditionalInfo = null;
//        }
//        try {
//            fieldedAddress7.aAdditionalInfo = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress7.aAdditionalInfo = null;
//        }
//        try {
//            fieldedAddress7.aBusinessName = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress7.aBusinessName = null;
//        }
//        try {
//            fieldedAddress7.aCountryCode = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress7.aCountryCode = null;
//        }
//        try {
//            fieldedAddress7.aCityCode = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress7.aCityCode = null;
//        }
//        try {
//            fieldedAddress7.aServiceLocationName = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress7.aServiceLocationName = null;
//        }
//        try {
//            fieldedAddress7.aAddressId = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress7.aAddressId = null;
//        }
//        try {
//            fieldedAddress7.aAliasName = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress7.aAliasName = null;
//        }
//        try {
//            fieldedAddress7.aAttention = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress7.aAttention = null;
//        }
        fieldedAddress7.aCassAddressLines = new StringSeqOpt();
        fieldedAddress7.aAuxiliaryAddressLines = new StringSeqOpt();
        UnfieldedAddress unfieldedAddress7 = new UnfieldedAddress();
//        try {
//            unfieldedAddress7.aCity = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress7.aCity = null;
//        }
//        try {
//            unfieldedAddress7.aState = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress7.aState = null;
//        }
//        try {
//            unfieldedAddress7.aPostalCode = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress7.aPostalCode = null;
//        }
//        try {
//            unfieldedAddress7.aPostalCodePlus4 = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress7.aPostalCodePlus4 = null;
//        }
//        try {
//            unfieldedAddress7.aCounty = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress7.aCounty = null;
//        }
//        try {
//            unfieldedAddress7.aCountry = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress7.aCountry = null;
//        }
//        try {
//            unfieldedAddress7.aStructureType = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress7.aStructureType = null;
//        }
//        try {
//            unfieldedAddress7.aStructureValue = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress7.aStructureValue = null;
//        }
//        try {
//            unfieldedAddress7.aLevelType = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress7.aLevelType = null;
//        }
//        try {
//            unfieldedAddress7.aLevelValue = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress7.aLevelValue = null;
//        }
//        try {
//            unfieldedAddress7.aUnitType = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress7.aUnitType = null;
//        }
//        try {
//            unfieldedAddress7.aUnitValue = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress7.aUnitValue = null;
//        }
//        try {
//            unfieldedAddress7.aAdditionalInfo = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress7.aAdditionalInfo = null;
//        }
//        try {
//            unfieldedAddress7.aBusinessName = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress7.aBusinessName = null;
//        }
//        try {
//            unfieldedAddress7.aCountryCode = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress7.aCountryCode = null;
//        }
//        try {
//            unfieldedAddress7.aCityCode = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress7.aCityCode = null;
//        }
//        try {
//            unfieldedAddress7.aServiceLocationName = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress7.aServiceLocationName = null;
//        }
//        try {
//            unfieldedAddress7.aAddressId = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress7.aAddressId = null;
//        }
//        try {
//            unfieldedAddress7.aAliasName = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress7.aAliasName = null;
//        }
//        try {
//            unfieldedAddress7.aAttention = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress7.aAttention = null;
//        }
        unfieldedAddress7.aAddressLines = new StringSeqOpt();
        Address address7 = new Address();
        address7.aFieldedAddress(fieldedAddress7);
        address7.aUnfieldedAddress(unfieldedAddress7);
        providerLocationProperty2.aServiceAddress.theValue(address7);

        providerLocationProperty2.aE911Address = new AddressOpt();
        FieldedAddress fieldedAddress8 = new FieldedAddress();
//        try {
//            fieldedAddress8.aRoute = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress8.aRoute = null;
//        }
//        try {
//            fieldedAddress8.aBox = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress8.aBox = null;
//        }
//        try {
//            fieldedAddress8.aHouseNumberPrefix = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress8.aHouseNumberPrefix = null;
//        }
//        try {
//            fieldedAddress8.aHouseNumber = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress8.aHouseNumber = null;
//        }
//        try {
//            fieldedAddress8.aAssignedHouseNumber = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress8.aAssignedHouseNumber = null;
//        }
//        try {
//            fieldedAddress8.aHouseNumberSuffix = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress8.aHouseNumberSuffix = null;
//        }
//        try {
//            fieldedAddress8.aStreetDirection = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress8.aStreetDirection = null;
//        }
//        try {
//            fieldedAddress8.aStreetName = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress8.aStreetName = null;
//        }
//        try {
//            fieldedAddress8.aStreetThoroughfare = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress8.aStreetThoroughfare = null;
//        }
//        try {
//            fieldedAddress8.aStreetNameSuffix = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress8.aStreetNameSuffix = null;
//        }
//        try {
//            fieldedAddress8.aCity = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress8.aCity = null;
//        }
//        try {
//            fieldedAddress8.aState = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress8.aState = null;
//        }
//        try {
//            fieldedAddress8.aPostalCode = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress8.aPostalCode = null;
//        }
//        try {
//            fieldedAddress8.aPostalCodePlus4 = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress8.aPostalCodePlus4 = null;
//        }
//        try {
//            fieldedAddress8.aCounty = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress8.aCounty = null;
//        }
//        try {
//            fieldedAddress8.aCountry = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress8.aCountry = null;
//        }
//        try {
//            fieldedAddress8.aStructureType = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress8.aStructureType = null;
//        }
//        try {
//            fieldedAddress8.aStructureValue = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress8.aStructureValue = null;
//        }
//        try {
//            fieldedAddress8.aLevelType = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress8.aLevelType = null;
//        }
//        try {
//            fieldedAddress8.aLevelValue = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress8.aLevelValue = null;
//        }
//        try {
//            fieldedAddress8.aUnitType = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress8.aUnitType = null;
//        }
//        try {
//            fieldedAddress8.aUnitValue = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress8.aUnitValue = null;
//        }
//        try {
//            fieldedAddress8.aOriginalStreetDirection = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress8.aOriginalStreetDirection = null;
//        }
//        try {
//            fieldedAddress8.aOriginalStreetNameSuffix = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress8.aOriginalStreetNameSuffix = null;
//        }
//        try {
//            fieldedAddress8.aCassAdditionalInfo = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress8.aCassAdditionalInfo = null;
//        }
//        try {
//            fieldedAddress8.aAdditionalInfo = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress8.aAdditionalInfo = null;
//        }
//        try {
//            fieldedAddress8.aBusinessName = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress8.aBusinessName = null;
//        }
//        try {
//            fieldedAddress8.aCountryCode = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress8.aCountryCode = null;
//        }
//        try {
//            fieldedAddress8.aCityCode = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress8.aCityCode = null;
//        }
//        try {
//            fieldedAddress8.aServiceLocationName = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress8.aServiceLocationName = null;
//        }
//        try {
//            fieldedAddress8.aAddressId = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress8.aAddressId = null;
//        }
//        try {
//            fieldedAddress8.aAliasName = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress8.aAliasName = null;
//        }
//        try {
//            fieldedAddress8.aAttention = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress8.aAttention = null;
//        }
        fieldedAddress8.aCassAddressLines = new StringSeqOpt();
        fieldedAddress8.aAuxiliaryAddressLines = new StringSeqOpt();
        UnfieldedAddress unfieldedAddress8 = new UnfieldedAddress();
//        try {
//            unfieldedAddress8.aCity = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress8.aCity = null;
//        }
//        try {
//            unfieldedAddress8.aState = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress8.aState = null;
//        }
//        try {
//            unfieldedAddress8.aPostalCode = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress8.aPostalCode = null;
//        }
//        try {
//            unfieldedAddress8.aPostalCodePlus4 = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress8.aPostalCodePlus4 = null;
//        }
//        try {
//            unfieldedAddress8.aCounty = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress8.aCounty = null;
//        }
//        try {
//            unfieldedAddress8.aCountry = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress8.aCountry = null;
//        }
//        try {
//            unfieldedAddress8.aStructureType = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress8.aStructureType = null;
//        }
//        try {
//            unfieldedAddress8.aStructureValue = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress8.aStructureValue = null;
//        }
//        try {
//            unfieldedAddress8.aLevelType = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress8.aLevelType = null;
//        }
//        try {
//            unfieldedAddress8.aLevelValue = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress8.aLevelValue = null;
//        }
//        try {
//            unfieldedAddress8.aUnitType = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress8.aUnitType = null;
//        }
//        try {
//            unfieldedAddress8.aUnitValue = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress8.aUnitValue = null;
//        }
//        try {
//            unfieldedAddress8.aAdditionalInfo = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress8.aAdditionalInfo = null;
//        }
//        try {
//            unfieldedAddress8.aBusinessName = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress8.aBusinessName = null;
//        }
//        try {
//            unfieldedAddress8.aCountryCode = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress8.aCountryCode = null;
//        }
//        try {
//            unfieldedAddress8.aCityCode = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress8.aCityCode = null;
//        }
//        try {
//            unfieldedAddress8.aServiceLocationName = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress8.aServiceLocationName = null;
//        }
//        try {
//            unfieldedAddress8.aAddressId = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress8.aAddressId = null;
//        }
//        try {
//            unfieldedAddress8.aAliasName = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress8.aAliasName = null;
//        }
//        try {
//            unfieldedAddress8.aAttention = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress8.aAttention = null;
//        }
        unfieldedAddress8.aAddressLines = new StringSeqOpt();
        Address address8 = new Address();
        address8.aFieldedAddress(fieldedAddress8);
        address8.aUnfieldedAddress(unfieldedAddress8);
        providerLocationProperty2.aE911Address.theValue(address8);

        providerLocationProperty2.aSwitchSuperPopAddress = new AddressOpt();
        FieldedAddress fieldedAddress9 = new FieldedAddress();
//        try {
//            fieldedAddress9.aRoute = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress9.aRoute = null;
//        }
//        try {
//            fieldedAddress9.aBox = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress9.aBox = null;
//        }
//        try {
//            fieldedAddress9.aHouseNumberPrefix = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress9.aHouseNumberPrefix = null;
//        }
//        try {
//            fieldedAddress9.aHouseNumber = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress9.aHouseNumber = null;
//        }
//        try {
//            fieldedAddress9.aAssignedHouseNumber = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress9.aAssignedHouseNumber = null;
//        }
//        try {
//            fieldedAddress9.aHouseNumberSuffix = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress9.aHouseNumberSuffix = null;
//        }
//        try {
//            fieldedAddress9.aStreetDirection = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress9.aStreetDirection = null;
//        }
//        try {
//            fieldedAddress9.aStreetName = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress9.aStreetName = null;
//        }
//        try {
//            fieldedAddress9.aStreetThoroughfare = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress9.aStreetThoroughfare = null;
//        }
//        try {
//            fieldedAddress9.aStreetNameSuffix = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress9.aStreetNameSuffix = null;
//        }
//        try {
//            fieldedAddress9.aCity = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress9.aCity = null;
//        }
//        try {
//            fieldedAddress9.aState = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress9.aState = null;
//        }
//        try {
//            fieldedAddress9.aPostalCode = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress9.aPostalCode = null;
//        }
//        try {
//            fieldedAddress9.aPostalCodePlus4 = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress9.aPostalCodePlus4 = null;
//        }
//        try {
//            fieldedAddress9.aCounty = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress9.aCounty = null;
//        }
//        try {
//            fieldedAddress9.aCountry = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress9.aCountry = null;
//        }
//        try {
//            fieldedAddress9.aStructureType = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress9.aStructureType = null;
//        }
//        try {
//            fieldedAddress9.aStructureValue = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress9.aStructureValue = null;
//        }
//        try {
//            fieldedAddress9.aLevelType = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress9.aLevelType = null;
//        }
//        try {
//            fieldedAddress9.aLevelValue = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress9.aLevelValue = null;
//        }
//        try {
//            fieldedAddress9.aUnitType = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress9.aUnitType = null;
//        }
//        try {
//            fieldedAddress9.aUnitValue = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress9.aUnitValue = null;
//        }
//        try {
//            fieldedAddress9.aOriginalStreetDirection = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress9.aOriginalStreetDirection = null;
//        }
//        try {
//            fieldedAddress9.aOriginalStreetNameSuffix = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress9.aOriginalStreetNameSuffix = null;
//        }
//        try {
//            fieldedAddress9.aCassAdditionalInfo = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress9.aCassAdditionalInfo = null;
//        }
//        try {
//            fieldedAddress9.aAdditionalInfo = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress9.aAdditionalInfo = null;
//        }
//        try {
//            fieldedAddress9.aBusinessName = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress9.aBusinessName = null;
//        }
//        try {
//            fieldedAddress9.aCountryCode = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress9.aCountryCode = null;
//        }
//        try {
//            fieldedAddress9.aCityCode = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress9.aCityCode = null;
//        }
//        try {
//            fieldedAddress9.aServiceLocationName = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress9.aServiceLocationName = null;
//        }
//        try {
//            fieldedAddress9.aAddressId = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress9.aAddressId = null;
//        }
//        try {
//            fieldedAddress9.aAliasName = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress9.aAliasName = null;
//        }
//        try {
//            fieldedAddress9.aAttention = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress9.aAttention = null;
//        }
        fieldedAddress9.aCassAddressLines = new StringSeqOpt();
        fieldedAddress9.aAuxiliaryAddressLines = new StringSeqOpt();
        UnfieldedAddress unfieldedAddress9 = new UnfieldedAddress();
//        try {
//            unfieldedAddress9.aCity = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress9.aCity = null;
//        }
//        try {
//            unfieldedAddress9.aState = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress9.aState = null;
//        }
//        try {
//            unfieldedAddress9.aPostalCode = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress9.aPostalCode = null;
//        }
//        try {
//            unfieldedAddress9.aPostalCodePlus4 = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress9.aPostalCodePlus4 = null;
//        }
//        try {
//            unfieldedAddress9.aCounty = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress9.aCounty = null;
//        }
//        try {
//            unfieldedAddress9.aCountry = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress9.aCountry = null;
//        }
//        try {
//            unfieldedAddress9.aStructureType = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress9.aStructureType = null;
//        }
//        try {
//            unfieldedAddress9.aStructureValue = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress9.aStructureValue = null;
//        }
//        try {
//            unfieldedAddress9.aLevelType = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress9.aLevelType = null;
//        }
//        try {
//            unfieldedAddress9.aLevelValue = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress9.aLevelValue = null;
//        }
//        try {
//            unfieldedAddress9.aUnitType = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress9.aUnitType = null;
//        }
//        try {
//            unfieldedAddress9.aUnitValue = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress9.aUnitValue = null;
//        }
//        try {
//            unfieldedAddress9.aAdditionalInfo = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress9.aAdditionalInfo = null;
//        }
//        try {
//            unfieldedAddress9.aBusinessName = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress9.aBusinessName = null;
//        }
//        try {
//            unfieldedAddress9.aCountryCode = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress9.aCountryCode = null;
//        }
//        try {
//            unfieldedAddress9.aCityCode = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress9.aCityCode = null;
//        }
//        try {
//            unfieldedAddress9.aServiceLocationName = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress9.aServiceLocationName = null;
//        }
//        try {
//            unfieldedAddress9.aAddressId = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress9.aAddressId = null;
//        }
//        try {
//            unfieldedAddress9.aAliasName = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress9.aAliasName = null;
//        }
//        try {
//            unfieldedAddress9.aAttention = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress9.aAttention = null;
//        }
        unfieldedAddress9.aAddressLines = new StringSeqOpt();
        Address address9 = new Address();
        address9.aFieldedAddress(fieldedAddress9);
        address9.aUnfieldedAddress(unfieldedAddress9);
        providerLocationProperty2.aSwitchSuperPopAddress.theValue(address9);

        providerLocationProperty2.aSAGAddress = new AddressOpt();
        FieldedAddress fieldedAddress10 = new FieldedAddress();
        PlaResponse.SAGLocationAttributes.Address.DescriptiveAlternatives sagAlternate;
        try {
            sagAlternate = response.getSagLocationAttributes().getAddress().getDescriptiveAlternatives();
        } catch (Exception e) {
            sagAlternate = new PlaResponse.SAGLocationAttributes.Address.DescriptiveAlternatives();
        }

//        try {
//            fieldedAddress10.aRoute = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress10.aRoute = null;
//        }
//        try {
//            fieldedAddress10.aBox = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress10.aBox = null;
//        }
        try {
            fieldedAddress10.aHouseNumberPrefix = toStringOpt(sagAlternate.getHouseNumberPrefix());
        } catch (Exception e) {
            fieldedAddress10.aHouseNumberPrefix = null;
        }
        try {
            fieldedAddress10.aHouseNumber = toStringOpt(sagAlternate.getHouseNumber());
        } catch (Exception e) {
            fieldedAddress10.aHouseNumber = null;
        }
        try {
            fieldedAddress10.aAssignedHouseNumber = toStringOpt(sagAlternate.getAssignedHouseNumber());
        } catch (Exception e) {
            fieldedAddress10.aAssignedHouseNumber = null;
        }
        try {
            fieldedAddress10.aHouseNumberSuffix = toStringOpt(sagAlternate.getHouseNumberSuffix());
        } catch (Exception e) {
            fieldedAddress10.aHouseNumberSuffix = null;
        }
        try {
            fieldedAddress10.aStreetDirection = toStringOpt(sagAlternate.getStreetDirectional());
        } catch (Exception e) {
            fieldedAddress10.aStreetDirection = null;
        }
        try {
            fieldedAddress10.aStreetName = toStringOpt(sagAlternate.getStreetName());
        } catch (Exception e) {
            fieldedAddress10.aStreetName = null;
        }
        try {
            fieldedAddress10.aStreetThoroughfare = toStringOpt(sagAlternate.getStreetThoroughfare());
        } catch (Exception e) {
            fieldedAddress10.aStreetThoroughfare = null;
        }
        try {
            fieldedAddress10.aStreetNameSuffix = toStringOpt(sagAlternate.getStreetPostDirectionalSuffix());
        } catch (Exception e) {
            fieldedAddress10.aStreetNameSuffix = null;
        }
        try {
            fieldedAddress10.aCity = toStringOpt(sagAlternate.getCommunityName());
        } catch (Exception e) {
            fieldedAddress10.aCity = null;
        }
        try {
            fieldedAddress10.aState = toStringOpt(sagAlternate.getState());
        } catch (Exception e) {
            fieldedAddress10.aState = null;
        }
        try {
            fieldedAddress10.aPostalCode = toStringOpt(sagAlternate.getPostalCode());
        } catch (Exception e) {
            fieldedAddress10.aPostalCode = null;
        }
//        try {
//            fieldedAddress10.aPostalCodePlus4 = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress10.aPostalCodePlus4 = null;
//        }
        try {
            fieldedAddress10.aCounty = toStringOpt(sagAlternate.getCommunityName());
        } catch (Exception e) {
            fieldedAddress10.aCounty = null;
        }
//        try {
//            fieldedAddress10.aCountry = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress10.aCountry = null;
//        }
//        try {
//            fieldedAddress10.aStructureType = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress10.aStructureType = null;
//        }
//        try {
//            fieldedAddress10.aStructureValue = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress10.aStructureValue = null;
//        }
//        try {
//            fieldedAddress10.aLevelType = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress10.aLevelType = null;
//        }
//        try {
//            fieldedAddress10.aLevelValue = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress10.aLevelValue = null;
//        }
//        try {
//            fieldedAddress10.aUnitType = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress10.aUnitType = null;
//        }
//        try {
//            fieldedAddress10.aUnitValue = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress10.aUnitValue = null;
//        }
//        try {
//            fieldedAddress10.aOriginalStreetDirection = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress10.aOriginalStreetDirection = null;
//        }
//        try {
//            fieldedAddress10.aOriginalStreetNameSuffix = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress10.aOriginalStreetNameSuffix = null;
//        }
//        try {
//            fieldedAddress10.aCassAdditionalInfo = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress10.aCassAdditionalInfo = null;
//        }
//        try {
//            fieldedAddress10.aAdditionalInfo = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress10.aAdditionalInfo = null;
//        }
//        try {
//            fieldedAddress10.aBusinessName = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress10.aBusinessName = null;
//        }
//        try {
//            fieldedAddress10.aCountryCode = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress10.aCountryCode = null;
//        }
//        try {
//            fieldedAddress10.aCityCode = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress10.aCityCode = null;
//        }
//        try {
//            fieldedAddress10.aServiceLocationName = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress10.aServiceLocationName = null;
//        }
//        try {
//            fieldedAddress10.aAddressId = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress10.aAddressId = null;
//        }
//        try {
//            fieldedAddress10.aAliasName = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress10.aAliasName = null;
//        }
//        try {
//            fieldedAddress10.aAttention = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress10.aAttention = null;
//        }
        fieldedAddress10.aCassAddressLines = new StringSeqOpt();
        fieldedAddress10.aAuxiliaryAddressLines = new StringSeqOpt();
        UnfieldedAddress unfieldedAddress10 = new UnfieldedAddress();
//        try {
//            unfieldedAddress10.aCity = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress10.aCity = null;
//        }
//        try {
//            unfieldedAddress10.aState = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress10.aState = null;
//        }
//        try {
//            unfieldedAddress10.aPostalCode = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress10.aPostalCode = null;
//        }
//        try {
//            unfieldedAddress10.aPostalCodePlus4 = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress10.aPostalCodePlus4 = null;
//        }
//        try {
//            unfieldedAddress10.aCounty = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress10.aCounty = null;
//        }
//        try {
//            unfieldedAddress10.aCountry = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress10.aCountry = null;
//        }
//        try {
//            unfieldedAddress10.aStructureType = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress10.aStructureType = null;
//        }
//        try {
//            unfieldedAddress10.aStructureValue = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress10.aStructureValue = null;
//        }
//        try {
//            unfieldedAddress10.aLevelType = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress10.aLevelType = null;
//        }
//        try {
//            unfieldedAddress10.aLevelValue = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress10.aLevelValue = null;
//        }
//        try {
//            unfieldedAddress10.aUnitType = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress10.aUnitType = null;
//        }
//        try {
//            unfieldedAddress10.aUnitValue = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress10.aUnitValue = null;
//        }
//        try {
//            unfieldedAddress10.aAdditionalInfo = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress10.aAdditionalInfo = null;
//        }
//        try {
//            unfieldedAddress10.aBusinessName = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress10.aBusinessName = null;
//        }
//        try {
//            unfieldedAddress10.aCountryCode = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress10.aCountryCode = null;
//        }
//        try {
//            unfieldedAddress10.aCityCode = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress10.aCityCode = null;
//        }
//        try {
//            unfieldedAddress10.aServiceLocationName = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress10.aServiceLocationName = null;
//        }
//        try {
//            unfieldedAddress10.aAddressId = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress10.aAddressId = null;
//        }
//        try {
//            unfieldedAddress10.aAliasName = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress10.aAliasName = null;
//        }
//        try {
//            unfieldedAddress10.aAttention = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress10.aAttention = null;
//        }
        unfieldedAddress10.aAddressLines = new StringSeqOpt();
        Address address10 = new Address();
        address10.aFieldedAddress(fieldedAddress10);
        address10.aUnfieldedAddress(unfieldedAddress10);
        providerLocationProperty2.aSAGAddress.theValue(address10);

        providerLocationProperty2.aExtensions = new ExtensionPropertySeqOpt();
        providerLocationProperties2[0] = providerLocationProperty2;

        location2.aProviderLocationProperties = providerLocationProperties2;
        alternativeAddress.aLocation(location2);

        alternativeAddresses[0] = alternativeAddress;
        alternativeAddressSeqOpt.theValue(alternativeAddresses);


        SubmittedAddressExceptionSeqOpt submittedAddressExceptionSeqOpt = new SubmittedAddressExceptionSeqOpt();
        SubmittedAddressException[] submittedAddressExceptions = new SubmittedAddressException[1];
        SubmittedAddressException submittedAddressException = new SubmittedAddressException();

        FieldedAddress fieldedAddress11 = new FieldedAddress();
//        try {
//            fieldedAddress11.aRoute = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress11.aRoute = null;
//        }
//        try {
//            fieldedAddress11.aBox = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress11.aBox = null;
//        }
//        try {
//            fieldedAddress11.aHouseNumberPrefix = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress11.aHouseNumberPrefix = null;
//        }
//        try {
//            fieldedAddress11.aHouseNumber = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress11.aHouseNumber = null;
//        }
//        try {
//            fieldedAddress11.aAssignedHouseNumber = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress11.aAssignedHouseNumber = null;
//        }
//        try {
//            fieldedAddress11.aHouseNumberSuffix = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress11.aHouseNumberSuffix = null;
//        }
//        try {
//            fieldedAddress11.aStreetDirection = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress11.aStreetDirection = null;
//        }
//        try {
//            fieldedAddress11.aStreetName = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress11.aStreetName = null;
//        }
//        try {
//            fieldedAddress11.aStreetThoroughfare = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress11.aStreetThoroughfare = null;
//        }
//        try {
//            fieldedAddress11.aStreetNameSuffix = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress11.aStreetNameSuffix = null;
//        }
//        try {
//            fieldedAddress11.aCity = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress11.aCity = null;
//        }
//        try {
//            fieldedAddress11.aState = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress11.aState = null;
//        }
//        try {
//            fieldedAddress11.aPostalCode = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress11.aPostalCode = null;
//        }
//        try {
//            fieldedAddress11.aPostalCodePlus4 = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress11.aPostalCodePlus4 = null;
//        }
//        try {
//            fieldedAddress11.aCounty = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress11.aCounty = null;
//        }
//        try {
//            fieldedAddress11.aCountry = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress11.aCountry = null;
//        }
//        try {
//            fieldedAddress11.aStructureType = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress11.aStructureType = null;
//        }
//        try {
//            fieldedAddress11.aStructureValue = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress11.aStructureValue = null;
//        }
//        try {
//            fieldedAddress11.aLevelType = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress11.aLevelType = null;
//        }
//        try {
//            fieldedAddress11.aLevelValue = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress11.aLevelValue = null;
//        }
//        try {
//            fieldedAddress11.aUnitType = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress11.aUnitType = null;
//        }
//        try {
//            fieldedAddress11.aUnitValue = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress11.aUnitValue = null;
//        }
//        try {
//            fieldedAddress11.aOriginalStreetDirection = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress11.aOriginalStreetDirection = null;
//        }
//        try {
//            fieldedAddress11.aOriginalStreetNameSuffix = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress11.aOriginalStreetNameSuffix = null;
//        }
//        try {
//            fieldedAddress11.aCassAdditionalInfo = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress11.aCassAdditionalInfo = null;
//        }
//        try {
//            fieldedAddress11.aAdditionalInfo = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress11.aAdditionalInfo = null;
//        }
//        try {
//            fieldedAddress11.aBusinessName = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress11.aBusinessName = null;
//        }
//        try {
//            fieldedAddress11.aCountryCode = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress11.aCountryCode = null;
//        }
//        try {
//            fieldedAddress11.aCityCode = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress11.aCityCode = null;
//        }
//        try {
//            fieldedAddress11.aServiceLocationName = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress11.aServiceLocationName = null;
//        }
//        try {
//            fieldedAddress11.aAddressId = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress11.aAddressId = null;
//        }
//        try {
//            fieldedAddress11.aAliasName = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress11.aAliasName = null;
//        }
//        try {
//            fieldedAddress11.aAttention = toStringOpt();
//        } catch (Exception e) {
            fieldedAddress11.aAttention = null;
//        }
        fieldedAddress11.aCassAddressLines = new StringSeqOpt();
        fieldedAddress11.aAuxiliaryAddressLines = new StringSeqOpt();
        UnfieldedAddress unfieldedAddress11 = new UnfieldedAddress();
//        try {
//            unfieldedAddress11.aCity = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress11.aCity = null;
//        }
//        try {
//            unfieldedAddress11.aState = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress11.aState = null;
//        }
//        try {
//            unfieldedAddress11.aPostalCode = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress11.aPostalCode = null;
//        }
//        try {
//            unfieldedAddress11.aPostalCodePlus4 = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress11.aPostalCodePlus4 = null;
//        }
//        try {
//            unfieldedAddress11.aCounty = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress11.aCounty = null;
//        }
//        try {
//            unfieldedAddress11.aCountry = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress11.aCountry = null;
//        }
//        try {
//            unfieldedAddress11.aStructureType = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress11.aStructureType = null;
//        }
//        try {
//            unfieldedAddress11.aStructureValue = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress11.aStructureValue = null;
//        }
//        try {
//            unfieldedAddress11.aLevelType = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress11.aLevelType = null;
//        }
//        try {
//            unfieldedAddress11.aLevelValue = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress11.aLevelValue = null;
//        }
//        try {
//            unfieldedAddress11.aUnitType = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress11.aUnitType = null;
//        }
//        try {
//            unfieldedAddress11.aUnitValue = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress11.aUnitValue = null;
//        }
//        try {
//            unfieldedAddress11.aAdditionalInfo = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress11.aAdditionalInfo = null;
//        }
//        try {
//            unfieldedAddress11.aBusinessName = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress11.aBusinessName = null;
//        }
//        try {
//            unfieldedAddress11.aCountryCode = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress11.aCountryCode = null;
//        }
//        try {
//            unfieldedAddress11.aCityCode = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress11.aCityCode = null;
//        }
//        try {
//            unfieldedAddress11.aServiceLocationName = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress11.aServiceLocationName = null;
//        }
//        try {
//            unfieldedAddress11.aAddressId = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress11.aAddressId = null;
//        }
//        try {
//            unfieldedAddress11.aAliasName = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress11.aAliasName = null;
//        }
//        try {
//            unfieldedAddress11.aAttention = toStringOpt();
//        } catch (Exception e) {
            unfieldedAddress11.aAttention = null;
//        }
        unfieldedAddress11.aAddressLines = new StringSeqOpt();
        Address address11 = new Address();
        address11.aFieldedAddress(fieldedAddress11);
        address11.aUnfieldedAddress(unfieldedAddress11);
        submittedAddressException.setAAddress(address11);

//        try {
//            submittedAddressException.setACode(toStringOpt());
//        } catch (Exception e) {
            submittedAddressException.setACode(null);
//        }
//        try {
//            submittedAddressException.setADescription(toStringOpt());
//        } catch (Exception e) {
            submittedAddressException.setADescription(null);
//        }
        submittedAddressExceptions[0] = submittedAddressException;
        submittedAddressExceptionSeqOpt.theValue(submittedAddressExceptions);

        alternativeAddressResult.aSubmittedAddressExceptions = submittedAddressExceptionSeqOpt;
        alternativeAddressResult.aAlternativeAddresses = alternativeAddressSeqOpt;

        addressMatchResult.aLocation(location);
        addressMatchResult.aAlternativeAddressResult(alternativeAddressResult);

        serviceReturn.setAContext(aContext);
        serviceReturn.setAAddressMatchResult(addressMatchResult);
        return serviceReturn;
    }

    private StringOpt toStringOpt(String val) {
        StringOpt stringOpt = new StringOpt();
        stringOpt.theValue(val);
        return stringOpt;
    }

    private StringSeqOpt seq(String... seq)
    {
        StringSeqOpt opt = new StringSeqOpt();
        opt.theValue(seq);
        return opt;
    }
    
    private StringSeqOpt seq(List<String> options)
    {
    	String[] arr = new String [options.size()];
        StringSeqOpt opt = new StringSeqOpt();
        opt.theValue(options.toArray(arr));
        return opt;
    }
}
