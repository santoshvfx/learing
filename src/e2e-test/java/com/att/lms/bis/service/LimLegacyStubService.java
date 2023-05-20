package com.att.lms.bis.service;

import com.sbc.bccs.utilities.ExceptionThrower;
import com.sbc.bccs.utilities.PropertiesFileLoader;
import com.sbc.eia.bis.framework.logging.BisLogger;
import com.sbc.eia.idl.bis_types.*;
import com.sbc.eia.idl.lim.*;
import com.sbc.eia.idl.lim_types.*;
import com.sbc.eia.idl.types.*;
import com.sbc.eia.idl.exception_types.ExceptionCode;

public class LimLegacyStubService extends LimLegacyService {

    public LimLegacyStubService() throws Exception {
        super();
    }

    public UpdateBillingAddressReturn updateBillingAddress (
            BisContext aContext,
            CompositeObjectKey aBillingAccountKey,
            AddressOpt aOldAddress,
            Address aNewAddress,
            StringOpt aDeliveryPointValidationCode,
            StringOpt aContactName)
            throws BusinessViolation,
            InvalidData,
            SystemFailure,
            ObjectNotFound,
            NotImplemented,
            DataNotFound,
            AccessDenied
    {

        UpdateBillingAddressReturn updateBillingAddressReturn = new UpdateBillingAddressReturn();
        String aMessage = "";
        Short aCode = 0;

        updateBillingAddressReturn.aContext = aContext;
        updateBillingAddressReturn.aCode = aCode;
        updateBillingAddressReturn.aMessage = aMessage;

        return updateBillingAddressReturn;
    }

    public RetrieveLocationForServiceReturn retrieveLocationForService (
            BisContext aContext,
            TelephoneNumber aTelephoneNumber,
            ProviderLocationPropertiesSeqOpt aPropertiesSeqOpt,
            LongOpt aMaximumBasicAddressReturnLimit,
            LongOpt aMaximumUnitReturnLimit)
            throws InvalidData,
            AccessDenied,
            BusinessViolation,
            SystemFailure,
            NotImplemented,
            DataNotFound,
            ObjectNotFound
    {
        RetrieveLocationForServiceReturn retrieveLocationForServiceReturn = new RetrieveLocationForServiceReturn();
        AddressMatchResult aAddressMatchResult = new AddressMatchResult();

        StringOpt aLocationId = new StringOpt();
        aLocationId.__default();
        Location aLocation = new Location(aLocationId, new ProviderLocationProperty[0]);
        aAddressMatchResult.aLocation(aLocation);

        AlternativeAddressSeqOpt aAlternativeAddress = new AlternativeAddressSeqOpt();
        aAlternativeAddress.__default();
        SubmittedAddressExceptionSeqOpt aSubmittedAddressExceptions = new SubmittedAddressExceptionSeqOpt();
        aSubmittedAddressExceptions.__default();
        AlternativeAddressResult aAlternativeAddressResult = new AlternativeAddressResult(aAlternativeAddress, aSubmittedAddressExceptions);
        aAddressMatchResult.aAlternativeAddressResult(aAlternativeAddressResult);

        retrieveLocationForServiceReturn.aContext = aContext;
        retrieveLocationForServiceReturn.aAddressMatchResult = aAddressMatchResult;

        return retrieveLocationForServiceReturn;
    }

    public RetrieveLocationForServiceAddressReturn retrieveLocationForServiceAddress (
            BisContext aContext,
            Address aAddress,
            LocationPropertiesToGetSeqOpt aLocationPropertiesToGet,
            StringOpt aPreviousCustomerName,
            StringOpt aCrossBoundaryState,
            LongOpt aMaxBasicAddressAlternatives,
            LongOpt aMaxLivingUnitAlternatives)
            throws BusinessViolation,
            InvalidData,
            SystemFailure,
            ObjectNotFound,
            NotImplemented,
            DataNotFound,
            AccessDenied
    {
        RetrieveLocationForServiceAddressReturn retrieveLocationForServiceAddressReturn = new RetrieveLocationForServiceAddressReturn();
        ServiceAddressMatchResult aServiceAddressMatchResult = new ServiceAddressMatchResult();

        Address2 aServiceAddress = new Address2();
        StringOpt aAddressMatchCode = new StringOpt();
        aAddressMatchCode.__default();
        StringOpt aAddressMatchCodeDescription = new StringOpt();
        aAddressMatchCodeDescription.__default();
        StringOpt aCentralOfficeCode = new StringOpt();
        aCentralOfficeCode.__default();
        StringOpt aCommunityName = new StringOpt();
        aCommunityName.__default();
        StringOpt aE911Exempt = new StringOpt();
        aE911Exempt.__default();
        StringOpt aE911NonRecurringCharge = new StringOpt();
        aE911NonRecurringCharge.__default();
        StringOpt aE911Surcharge = new StringOpt();
        aE911Surcharge.__default();
        StringOpt aExchangeCode = new StringOpt();
        aExchangeCode.__default();
        StringOpt aExco = new StringOpt();
        aExco.__default();
        StringOpt aLataCode = new StringOpt();
        aLataCode.__default();
        StringOpt aLocalProviderServingOfficeCode = new StringOpt();
        aLocalProviderServingOfficeCode.__default();
        StringOpt aOwnedWiring = new StringOpt();
        aOwnedWiring.__default();
        StringOpt aPrimaryDirectoryCode = new StringOpt();
        aPrimaryDirectoryCode.__default();
        StringOpt aPrimaryNpaNxx = new StringOpt();
        aPrimaryNpaNxx.__default();
        StringOpt aQuickDialTone = new StringOpt();
        aQuickDialTone.__default();
        StringOpt aQuickDialToneNumber = new StringOpt();
        aQuickDialToneNumber.__default();
        StringOpt aRateZoneBandCode = new StringOpt();
        aRateZoneBandCode.__default();
        StringOpt aSagWireCenter = new StringOpt();
        aSagWireCenter.__default();
        StringOpt aServingAreaDescription = new StringOpt();
        aServingAreaDescription.__default();
        StringOpt aStreetAddressGuideArea = new StringOpt();
        aStreetAddressGuideArea.__default();
        StringOpt aSurcharge4Percent = new StringOpt();
        aSurcharge4Percent.__default();
        StringOpt aSurcharge16Percent = new StringOpt();
        aSurcharge16Percent.__default();
        StringOpt aTarCode = new StringOpt();
        aTarCode.__default();
        StringOpt aTelephoneNumber = new StringOpt();
        aTelephoneNumber.__default();
        StringOpt aWorkingServiceOnLocation = new StringOpt();
        aWorkingServiceOnLocation.__default();
        AddressOpt aSAGAddress = new AddressOpt();
        aSAGAddress.__default();
        StringOpt aBuildingType = new StringOpt();
        aBuildingType.__default();
        StringOpt aLegalEntity = new StringOpt();
        aLegalEntity.__default();
        StringOpt aVideoHubOffice = new StringOpt();
        aVideoHubOffice.__default();
        StringOpt aSmartmoves = new StringOpt();
        aSmartmoves.__default();
        StringOpt aServingNetworkType = new StringOpt();
        aServingNetworkType.__default();
        StringOpt aLocationType = new StringOpt();
        aLocationType.__default();
        StringOpt aRateZone = new StringOpt();
        aRateZone.__default();
        StringOpt aIndependentCompanyIndicator = new StringOpt();
        aIndependentCompanyIndicator.__default();
        EiaDateOpt aAreaTransferCutDate = new EiaDateOpt();
        aAreaTransferCutDate.__default();
        EiaDateOpt aAreaTransferNumberChangeDate = new EiaDateOpt();
        aAreaTransferNumberChangeDate.__default();
        StringOpt aAreaTransferNpaNxx = new StringOpt();
        aAreaTransferNpaNxx.__default();
        StringOpt aAreaTransferWireCenter = new StringOpt();
        aAreaTransferWireCenter.__default();
        StringOpt aWireCenter = new StringOpt();
        aWireCenter.__default();
        StringOpt aConnectThrough = new StringOpt();
        aConnectThrough.__default();
        ExtensionPropertySeqOpt aExtensions = new ExtensionPropertySeqOpt();
        aExtensions.__default();
        ServiceLocation aServiceLocation = new ServiceLocation(aServiceAddress, aAddressMatchCode, aAddressMatchCodeDescription, aCentralOfficeCode, aCommunityName, aE911Exempt, aE911NonRecurringCharge, aE911Surcharge, aExchangeCode, aExco, aLataCode, aLocalProviderServingOfficeCode, aOwnedWiring, aPrimaryDirectoryCode, aPrimaryNpaNxx, aQuickDialTone, aQuickDialToneNumber, aRateZoneBandCode, aSagWireCenter, aServingAreaDescription, aStreetAddressGuideArea, aSurcharge4Percent, aSurcharge16Percent, aTarCode, aTelephoneNumber, aWorkingServiceOnLocation, aSAGAddress, aBuildingType, aLegalEntity, aVideoHubOffice, aSmartmoves, aServingNetworkType, aLocationType, aRateZone, aCrossBoundaryState, aIndependentCompanyIndicator, aAreaTransferCutDate, aAreaTransferNumberChangeDate, aAreaTransferNpaNxx, aAreaTransferWireCenter, aWireCenter, aConnectThrough, aExtensions);

        aServiceAddressMatchResult.aServiceLocation(aServiceLocation);
        aServiceAddressMatchResult.aAlternativeServiceAddresses(new AlternativeServiceAddress[0]);

        retrieveLocationForServiceAddressReturn.aContext = aContext;
        retrieveLocationForServiceAddressReturn.aServiceAddressMatchResult = aServiceAddressMatchResult;

        return retrieveLocationForServiceAddressReturn;
    }

    public RetrieveLocationForTelephoneNumberReturn retrieveLocationForTelephoneNumber (
            BisContext aContext,
            TelephoneNumber aTelephoneNumber,
            LocationPropertiesToGetSeqOpt aLocationPropertiesToGet)
            throws BusinessViolation,
            InvalidData,
            SystemFailure,
            ObjectNotFound,
            NotImplemented,
            DataNotFound,
            AccessDenied
    {
        Address2 aServiceAddress = new Address2();
        StringOpt aAddressMatchCode = new StringOpt();
        aAddressMatchCode.__default();
        StringOpt aAddressMatchCodeDescription = new StringOpt();
        aAddressMatchCodeDescription.__default();
        StringOpt aCentralOfficeCode = new StringOpt();
        aCentralOfficeCode.__default();
        StringOpt aCommunityName = new StringOpt();
        aCommunityName.__default();
        StringOpt aE911Exempt = new StringOpt();
        aE911Exempt.__default();
        StringOpt aE911NonRecurringCharge = new StringOpt();
        aE911NonRecurringCharge.__default();
        StringOpt aE911Surcharge = new StringOpt();
        aE911Surcharge.__default();
        StringOpt aExchangeCode = new StringOpt();
        aExchangeCode.__default();
        StringOpt aExco = new StringOpt();
        aExco.__default();
        StringOpt aLataCode = new StringOpt();
        aLataCode.__default();
        StringOpt aLocalProviderServingOfficeCode = new StringOpt();
        aLocalProviderServingOfficeCode.__default();
        StringOpt aOwnedWiring = new StringOpt();
        aOwnedWiring.__default();
        StringOpt aPrimaryDirectoryCode = new StringOpt();
        aPrimaryDirectoryCode.__default();
        StringOpt aPrimaryNpaNxx = new StringOpt();
        aPrimaryNpaNxx.__default();
        StringOpt aQuickDialTone = new StringOpt();
        aQuickDialTone.__default();
        StringOpt aQuickDialToneNumber = new StringOpt();
        aQuickDialToneNumber.__default();
        StringOpt aRateZoneBandCode = new StringOpt();
        aRateZoneBandCode.__default();
        StringOpt aSagWireCenter = new StringOpt();
        aSagWireCenter.__default();
        StringOpt aServingAreaDescription = new StringOpt();
        aServingAreaDescription.__default();
        StringOpt aStreetAddressGuideArea = new StringOpt();
        aStreetAddressGuideArea.__default();
        StringOpt aSurcharge4Percent = new StringOpt();
        aSurcharge4Percent.__default();
        StringOpt aSurcharge16Percent = new StringOpt();
        aSurcharge16Percent.__default();
        StringOpt aTarCode = new StringOpt();
        aTarCode.__default();
        StringOpt aTelephone = new StringOpt();
        aTelephone.theValue(aTelephoneNumber.toString());
        StringOpt aWorkingServiceOnLocation = new StringOpt();
        aWorkingServiceOnLocation.__default();
        AddressOpt aSAGAddress = new AddressOpt();
        aSAGAddress.__default();
        StringOpt aBuildingType = new StringOpt();
        aBuildingType.__default();
        StringOpt aLegalEntity = new StringOpt();
        aLegalEntity.__default();
        StringOpt aVideoHubOffice = new StringOpt();
        aVideoHubOffice.__default();
        StringOpt aSmartmoves = new StringOpt();
        aSmartmoves.__default();
        StringOpt aServingNetworkType = new StringOpt();
        aServingNetworkType.__default();
        StringOpt aLocationType = new StringOpt();
        aLocationType.__default();
        StringOpt aRateZone = new StringOpt();
        aRateZone.__default();
        StringOpt aCrossBoundaryState = new StringOpt();
        aCrossBoundaryState.__default();
        StringOpt aIndependentCompanyIndicator = new StringOpt();
        aIndependentCompanyIndicator.__default();
        EiaDateOpt aAreaTransferCutDate = new EiaDateOpt();
        aAreaTransferCutDate.__default();
        EiaDateOpt aAreaTransferNumberChangeDate = new EiaDateOpt();
        aAreaTransferNumberChangeDate.__default();
        StringOpt aAreaTransferNpaNxx = new StringOpt();
        aAreaTransferNpaNxx.__default();
        StringOpt aAreaTransferWireCenter = new StringOpt();
        aAreaTransferWireCenter.__default();
        StringOpt aWireCenter = new StringOpt();
        aWireCenter.__default();
        StringOpt aConnectThrough = new StringOpt();
        aConnectThrough.__default();
        ExtensionPropertySeqOpt aExtensions = new ExtensionPropertySeqOpt();
        aExtensions.__default();
        ServiceLocation aServiceLocation = new ServiceLocation(aServiceAddress, aAddressMatchCode, aAddressMatchCodeDescription, aCentralOfficeCode, aCommunityName, aE911Exempt, aE911NonRecurringCharge, aE911Surcharge, aExchangeCode, aExco, aLataCode, aLocalProviderServingOfficeCode, aOwnedWiring, aPrimaryDirectoryCode, aPrimaryNpaNxx, aQuickDialTone, aQuickDialToneNumber, aRateZoneBandCode, aSagWireCenter, aServingAreaDescription, aStreetAddressGuideArea, aSurcharge4Percent, aSurcharge16Percent, aTarCode, aTelephone, aWorkingServiceOnLocation, aSAGAddress, aBuildingType, aLegalEntity, aVideoHubOffice, aSmartmoves, aServingNetworkType, aLocationType, aRateZone, aCrossBoundaryState, aIndependentCompanyIndicator, aAreaTransferCutDate, aAreaTransferNumberChangeDate, aAreaTransferNpaNxx, aAreaTransferWireCenter, aWireCenter, aConnectThrough, aExtensions);

        RetrieveLocationForTelephoneNumberReturn response = new RetrieveLocationForTelephoneNumberReturn(aContext, aServiceLocation);
        return response;
    }
}
