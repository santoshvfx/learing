// $Id: LIMIDLUtil.java,v 1.5 2003/07/31 18:08:57 rz7367 Exp $

/* Copyright Notice
 * RESTRICTED - PROPRIETARY INFORMATION
 * The information herein is for use only by authorized employees
 * of SBC Services Inc. and authorized Affiliates of SBC Services,
 * Inc., and is not for general distribution within or outside the
 * the respective companies.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2004 SBC Services, Inc.
 * All rights reserved.
 */

/** Description
 *  This file contains the LIMIDLUtil class which provides utility methods
 *  for doing things such as displaying the contents of LIM IDL types.
 *  Description
 */

package com.sbc.eia.bis.lim.util;

import com.sbc.bccs.idl.helpers.IDLUtil;
import com.sbc.eia.bis.lim.transactions.common.ServiceLocationBuilder;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.bis_types.TelephoneNumber;
import com.sbc.eia.idl.lim.AddressMatchResult;
import com.sbc.eia.idl.lim.AddressMatchResultChoice;
import com.sbc.eia.idl.lim.RetrieveServiceAreaByPostalCodeReturn;
import com.sbc.eia.idl.lim_types.Address;
import com.sbc.eia.idl.lim_types.AddressChoice;
import com.sbc.eia.idl.lim_types.AddressOpt;
import com.sbc.eia.idl.lim_types.AlternativeAddress;
import com.sbc.eia.idl.lim_types.AlternativeAddressChoice;
import com.sbc.eia.idl.lim_types.ExtensionProperty;
import com.sbc.eia.idl.lim_types.FieldedAddress;
import com.sbc.eia.idl.lim_types.Location;
import com.sbc.eia.idl.lim_types.MarketInformation;
import com.sbc.eia.idl.lim_types.MarketInformationOpt;
import com.sbc.eia.idl.lim_types.ProviderLocationProperties;
import com.sbc.eia.idl.lim_types.ProviderLocationProperty;
import com.sbc.eia.idl.lim_types.RangedAddress;
import com.sbc.eia.idl.lim_types.ServiceArea;
import com.sbc.eia.idl.lim_types.ServiceAreaSeqOpt;
import com.sbc.eia.idl.lim_types.SubmittedAddressException;
import com.sbc.eia.idl.lim_types.UnfieldedAddress;
import com.sbc.eia.idl.types.LongOpt;
import com.sbc.eia.idl.types.ObjectKeyOpt;
import com.sbc.eia.idl.types.StringOpt;
import com.sbc.eia.idl.types.StringSeqOpt;
import com.sbc.eia.idl.lim.ServiceAddressMatchResult;
import com.sbc.eia.idl.lim_types.ServiceLocation;
import com.sbc.eia.idl.lim_types.AlternativeServiceAddress;
import com.sbc.eia.idl.lim_types.AlternativeServiceAddressChoice;
import com.sbc.eia.idl.lim_types.LocationPropertiesToGetSeqOpt;
import com.sbc.eia.idl.lim_types.Address2;
import  com.sbc.eia.idl.lim_types.Address2Choice;
import com.sbc.eia.idl.types.EiaDate;
import com.sbc.eia.idl.lim.PostalAddressMatchResult;
import com.sbc.eia.idl.lim_types.PostalLocation;
import com.sbc.eia.idl.types.CompositeObjectKey;
import com.sbc.eia.idl.lim.UpdateBillingAddressReturn;
import com.sbc.eia.idl.bim_types.AddNotesToBillingAccountReturn;
import com.sbc.eia.idl.bim_types.ObjectContext;

/**
 * A collection of static helper methods for LIM IDL data types.
 * Creation date: (3/27/01 2:43:06 PM)
 * @author: David Prager
 */
public class LIMIDLUtil {
    /**
     * LIMIDLUtil constructor
     */
    public LIMIDLUtil() {
    	super();
    }
    
    /**
     * Return a display array of strings.
     * Creation date: (5/2/01 10:40:50 AM)
     * @return String
     * @param stringArray String[]
     */
    public static String display(String[] stringArray)
    {
    	StringBuffer outptStr = new StringBuffer();
    	for (int i=0; i < stringArray.length; i++){
            try
            {
                outptStr.append(stringArray[i] + "|");
            }
            catch (Exception e)
            {
                outptStr.append("|");
            }
    	}
    	return outptStr.toString();
    }
    
    /**
     * Method display.
     * @param addrOpt
     * @return String
     */
    public static String display(AddressOpt addrOpt)
    {
        StringBuffer inptStr = new StringBuffer("");
        try
        {
            inptStr.append(display(addrOpt.theValue()));
        }
        catch (Exception e)
        {
        }
    
        return inptStr.toString();
    }
    
    
    /**
     * Return an INPUT String from the Address.
     * Creation date: (4/30/01 11:31:22 AM)
     * @return String
     * @param addr Address
     */
    public static String display(Address addr)
    {
    	StringBuffer inptStr = new StringBuffer("");
    	try
    	{
    		if (addr.discriminator() == AddressChoice.FIELDED_ADDRESS)
    		{
    			inptStr.append(LIMIDLUtil.display(addr.aFieldedAddress()));
    		}
    		else
    		{
    			inptStr.append(LIMIDLUtil.display(addr.aUnfieldedAddress()));
    		}
    	
    	}
    	catch (Exception e)
    	{
            inptStr.append(
                "\nInput error in LIMIDLUtil.display()\n"
                    + e.toString()
                    + "<"
                    + e.getMessage()
                    + ">");
    	}
    
    	return inptStr.toString();
    }
    
    /**
     * Return an INPUT String from the Address2.
     * Creation date: (8/20/07 11:31:22 AM)
     * @return String
     * @param addr2 Address2
     */
    public static String display(Address2 addr2)
    {
    	StringBuffer inptStr = new StringBuffer("");
    	try
    	{
    		if (addr2.discriminator().value() == Address2Choice._FIELDED_ADDRESS2)
    		{
    			inptStr.append(LIMIDLUtil.display(addr2.aFieldedAddress()));
    		}
    		else if (addr2.discriminator().value() == Address2Choice._RANGED_ADDRESS2)
    		{
    		    inptStr.append(LIMIDLUtil.display(addr2.aRangedAddress()));
    		}
    		else
    		{
    			inptStr.append(LIMIDLUtil.display(addr2.aUnfieldedAddress()));
    		}
    	
    	}
    	catch (Exception e)
    	{
            inptStr.append(
                "\nInput error in LIMIDLUtil.display()\n"
                    + e.toString()
                    + "<"
                    + e.getMessage()
                    + ">");
    	}
    
    	return inptStr.toString();
    }
    
    /**
     * Return a display formatted String of the given FieldedAddress.
     * Creation date: (5/2/01 10:37:10 AM)
     * @return String
     * @param fa FieldedAddress
     */
    public static String display(FieldedAddress fa)
    {
    	StringBuffer outptStr = new StringBuffer();
        try
        {
            outptStr.append(fa.aRoute.theValue() + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        try
        {
            outptStr.append(fa.aBox.theValue() + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }

        try
        {
            outptStr.append(fa.aHouseNumberPrefix.theValue() + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        try
        {
            outptStr.append(fa.aHouseNumber.theValue() + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        try
        {
            outptStr.append(fa.aAssignedHouseNumber.theValue() + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
                
        try
        {
            outptStr.append(fa.aHouseNumberSuffix.theValue() + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        try
        {
            outptStr.append(fa.aStreetDirection.theValue() + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        try
        {
            outptStr.append(fa.aStreetName.theValue() + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        try
        {
            outptStr.append(fa.aStreetThoroughfare.theValue() + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        try
        {
            outptStr.append(fa.aStreetNameSuffix.theValue() + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }

        try
        {
            outptStr.append(fa.aCity.theValue() + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        try
        {
            outptStr.append(fa.aState.theValue() + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        try
        {
            outptStr.append(fa.aPostalCode.theValue() + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        try
        {
            outptStr.append(fa.aPostalCodePlus4.theValue() + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        try
        {
            outptStr.append(fa.aCounty.theValue() + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        try
        {
            outptStr.append(fa.aCountry.theValue() + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        try
        {
            outptStr.append(fa.aStructureType.theValue()+ "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        try
        {
            outptStr.append(fa.aStructureValue.theValue() + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        try
        {
            outptStr.append(fa.aLevelType.theValue() + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        try
        {
            outptStr.append(fa.aLevelValue.theValue() + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        try
        {
            outptStr.append(fa.aUnitType.theValue() + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        try
        {
            outptStr.append(fa.aUnitValue.theValue() + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        try
        {
            outptStr.append(fa.aAdditionalInfo.theValue() + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }

        try
        {
            outptStr.append(fa.aOriginalStreetDirection.theValue()  + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        try
        {
            outptStr.append(fa.aOriginalStreetNameSuffix.theValue()  + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }   
         
        try
        {
            outptStr.append( fa.aBusinessName.theValue() + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        try
        {
            outptStr.append( display( fa.aAuxiliaryAddressLines.theValue() ));
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }

        try
        {
            outptStr.append( fa.aCassAdditionalInfo.theValue() + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }

        try
        {
            outptStr.append( display( fa.aCassAddressLines.theValue() ) );
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        try
        {
            outptStr.append( fa.aAddressId.theValue() + "|" );
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }

    	return outptStr.toString();
    }
    /**
     * Return a display formatted String of the given Location Address.
     * Creation date: (01/10/02 11:02:52 AM)
     * @return String
     * @param la Location
     */
    public static String display (Location la)
    {
    	StringBuffer outptStr = new StringBuffer();
    
        try
        {
            outptStr.append(la.aLocationId.theValue() + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
    	
    	outptStr.append ( display(la.aProviderLocationProperties) );
    	
    	return outptStr.toString();
    }

    /**
     * Return a display formatted String of the given ServiceLocation Address.
     * Creation date: (01/10/02 11:02:52 AM)
     * @return String
     * @param serviceLa ServiceLocation
     */
    public static String display(ServiceLocation serviceLa)
    {
        StringBuffer outptStr = new StringBuffer("");
        
        outptStr.append("ServiceAddress" + LIMTag.EQUALTO);
        outptStr.append(display(serviceLa.aServiceAddress) + "|");
        
        try
        {
            outptStr.append("AddressMatchCode" + LIMTag.EQUALTO);
            outptStr.append(
                    serviceLa.aAddressMatchCode.theValue()
                    + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        try
        {
            outptStr.append("AddressMatchCodeDescription" + LIMTag.EQUALTO);
            outptStr.append(
                    serviceLa.aAddressMatchCodeDescription.theValue()
                    + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        try
        {
            outptStr.append("CentralOfficeCode" + LIMTag.EQUALTO);
            outptStr.append(
                    serviceLa.aCentralOfficeCode.theValue()
                    + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        try
        {
            outptStr.append("CommunityName" + LIMTag.EQUALTO);
            outptStr.append(
                serviceLa.aCommunityName.theValue() + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }

        try
        {
            outptStr.append("E911Exempt" + LIMTag.EQUALTO);
            outptStr.append(
                serviceLa.aE911Exempt.theValue() + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        try
        {
            outptStr.append("E911NonRecurringCharge" + LIMTag.EQUALTO);
            outptStr.append(
                serviceLa.aE911NonRecurringCharge.theValue()
                    + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        try
        {
            outptStr.append("E911Surcharge" + LIMTag.EQUALTO);
            outptStr.append(
                serviceLa.aE911Surcharge.theValue() + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        try
        {
            outptStr.append("ExchangeCode" + LIMTag.EQUALTO);
            outptStr.append(
                serviceLa.aExchangeCode.theValue() + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        try
        {
            outptStr.append("Exco" + LIMTag.EQUALTO);
            outptStr.append(
                serviceLa.aExco.theValue() + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        try
        {
            outptStr.append("LataCode" + LIMTag.EQUALTO);
            outptStr.append(
                serviceLa.aLataCode.theValue() + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        try
        {
            outptStr.append("LocalProviderServingOfficeCode" + LIMTag.EQUALTO);
            outptStr.append(
                serviceLa
                    .aLocalProviderServingOfficeCode
                    .theValue()
                    + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        try
        {
            outptStr.append("OwnedWiring" + LIMTag.EQUALTO);
            outptStr.append(
                serviceLa.aOwnedWiring.theValue() + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        try
        {
            outptStr.append("PrimaryDirectoryCode" + LIMTag.EQUALTO);
            outptStr.append(
                serviceLa.aPrimaryDirectoryCode.theValue()
                    + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        try
        {
            outptStr.append("PrimaryNpaNxx" + LIMTag.EQUALTO);
            outptStr.append(
                serviceLa.aPrimaryNpaNxx.theValue() + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        try
        {
            outptStr.append("QuickDialTone" + LIMTag.EQUALTO);
            outptStr.append(
                serviceLa.aQuickDialTone.theValue() + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        try
        {
            outptStr.append("QuickDialToneNumber" + LIMTag.EQUALTO);
            outptStr.append(
                serviceLa.aQuickDialToneNumber.theValue()
                    + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        try
        {
            outptStr.append("RateZoneBandCode" + LIMTag.EQUALTO);
            outptStr.append(
                serviceLa.aRateZoneBandCode.theValue()
                    + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        try
        {
            outptStr.append("SagWireCenter" + LIMTag.EQUALTO);
            outptStr.append(
                serviceLa.aSagWireCenter.theValue() + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        try
        {
            outptStr.append("ServingAreaDescription" + LIMTag.EQUALTO);
            outptStr.append(
                serviceLa.aServingAreaDescription.theValue()
                    + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        try
        {
            outptStr.append("StreetAddressGuideArea" + LIMTag.EQUALTO);
            outptStr.append(
                serviceLa.aStreetAddressGuideArea.theValue()
                    + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        try
        {
            outptStr.append("Surcharge4Percent" + LIMTag.EQUALTO);
            outptStr.append(
                serviceLa.aSurcharge4Percent.theValue()
                    + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        try
        {
            outptStr.append("Surcharge16Percent" + LIMTag.EQUALTO);
            outptStr.append(
                serviceLa.aSurcharge16Percent.theValue()
                    + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        try
        {
            outptStr.append("TarCode" + LIMTag.EQUALTO);
            outptStr.append(
                serviceLa.aTarCode.theValue() + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        try
        {
            outptStr.append("TelephoneNumber" + LIMTag.EQUALTO);
            outptStr.append(
                    serviceLa.aTelephoneNumber.theValue() + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        try
        {
            outptStr.append("WorkingServiceOnLocation" + LIMTag.EQUALTO);
            outptStr.append(
                serviceLa
                    .aWorkingServiceOnLocation
                    .theValue()
                    + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        try
        {
            outptStr.append("SAGAddress" + LIMTag.EQUALTO);
            outptStr.append(
                    display(serviceLa.aSAGAddress) + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        try
        {
            outptStr.append("BuildingType" + LIMTag.EQUALTO);
            outptStr.append(
                    serviceLa.aBuildingType.theValue() + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        try
        {
            outptStr.append("LegalEntity" + LIMTag.EQUALTO);
            outptStr.append(
                    serviceLa.aLegalEntity.theValue() + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        try
        {
            outptStr.append("VideoHubOffice" + LIMTag.EQUALTO);
            outptStr.append(
                    serviceLa.aVideoHubOffice.theValue() + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        try
        {
            outptStr.append("Smartmoves" + LIMTag.EQUALTO);
            outptStr.append(
                    serviceLa.aSmartmoves.theValue() + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        try
        {
            outptStr.append("ServingNetworkType" + LIMTag.EQUALTO);
            outptStr.append(
                    serviceLa.aServingNetworkType.theValue() + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        try
        {
            outptStr.append("LocationType" + LIMTag.EQUALTO);
            outptStr.append(
                    serviceLa.aLocationType.theValue() + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        try
        {
            outptStr.append("RateZone" + LIMTag.EQUALTO);
            outptStr.append(
                    serviceLa.aRateZone.theValue() + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        try
        {
            outptStr.append("CrossBoundaryState" + LIMTag.EQUALTO);
            outptStr.append(
                    serviceLa.aCrossBoundaryState.theValue() + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        try
        {
            outptStr.append("IndependentCompanyIndicator" + LIMTag.EQUALTO);
            outptStr.append(
                    serviceLa.aIndependentCompanyIndicator.theValue() + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        try
        {
            outptStr.append("AreaTransferCutDate" + LIMTag.EQUALTO);
            outptStr.append(
                    display(serviceLa.aAreaTransferCutDate.theValue()) + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        try
        {
            outptStr.append("AreaTransferNumberChangeDate" + LIMTag.EQUALTO);
            outptStr.append(
                    display(serviceLa.aAreaTransferNumberChangeDate.theValue()) + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        try
        {
            outptStr.append("AreaTransferNpaNxx" + LIMTag.EQUALTO);
            outptStr.append(
                    serviceLa.aAreaTransferNpaNxx.theValue() + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        try
        {
            outptStr.append("AreaTransferWireCenter" + LIMTag.EQUALTO);
            outptStr.append(
                    serviceLa.aAreaTransferWireCenter.theValue() + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        try
        {
            outptStr.append("WireCenter" + LIMTag.EQUALTO);
            outptStr.append(
                    serviceLa.aWireCenter.theValue() + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        try
        {
            outptStr.append("ConnectThrough" + LIMTag.EQUALTO);
            outptStr.append(
                    serviceLa.aConnectThrough.theValue() + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        try 
        {   
        	outptStr.append (display(serviceLa.aExtensions.theValue()));
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }

        return outptStr.toString();
    }
 
    /**
     * Return a display formatted String of the given PostalLocation Address.
     * Creation date: (01/10/02 11:02:52 AM)
     * @return String
     * @param postalLa PostalLocation
     */
    public static String display(PostalLocation postalLa)
    {
        StringBuffer outptStr = new StringBuffer("");
        
        outptStr.append("PostalAddress" + LIMTag.EQUALTO);
        outptStr.append(display(postalLa.aPostalAddress) + "|");
        
        try
        {
            outptStr.append("AddressMatchCode" + LIMTag.EQUALTO);
            outptStr.append(
                    postalLa.aAddressMatchCode
                    + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        try
        {
            outptStr.append("AddressMatchCodeDescription" + LIMTag.EQUALTO);
            outptStr.append(
                    postalLa.aAddressMatchCodeDescription.theValue()
                    + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        try
        {
            outptStr.append("DeliveryPointValidationCode" + LIMTag.EQUALTO);
            outptStr.append(
                    postalLa.aDeliveryPointValidationCode.theValue()
                    + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        try
        {
            outptStr.append("CityStatePostalCodeValid" + LIMTag.EQUALTO);
            outptStr.append(
                    postalLa.aCityStatePostalCodeValid.theValue() + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }

        return outptStr.toString();
    }
    
    /**
     * Return a display formatted String of the given SubbmitedAddressException.
     * Creation date: (11/08/05)
     * @return String
     * @param sae SubmittedAddressException
     */
    public static String display (SubmittedAddressException [] sae)
    {
    	StringBuffer outptStr = new StringBuffer();
 
 		for (int i = 0; i < sae.length; i++)
 		{   
        	try
        	{
            	outptStr.append (display (sae[i].aAddress) + "|");
        	}
        	catch (Exception e)
        	{
            	outptStr.append("|");
        	}
    	
    		try
    		{
    			outptStr.append ( sae[i].aCode.theValue() + "|" );
    			outptStr.append ( sae[i].aDescription.theValue() + "|" );
        	}
        	catch (Exception e)
        	{
            	outptStr.append("|");
        	}
 		}
    	return outptStr.toString();
    }
 
    /**
     * Method display.
     * @param providerLocationPropertyArray
     * @return String
     */
    public static String display(ProviderLocationProperty[] 
                                                providerLocationPropertyArray)
    {
        StringBuffer outptStr = new StringBuffer("");

        int propToGetCount = providerLocationPropertyArray.length;
        for (int i = 0; i < propToGetCount; i++)
        {
            ProviderLocationProperty locationProperty =
                providerLocationPropertyArray[i];
            outptStr.append(display(locationProperty));
        }

        return outptStr.toString();
    }
    
    /**
     * Display the value of an ObjectHandle.
     * Creation date: (01/10/02 12:47:48 PM)
     * @return String
     * @param oh ObjectHandle
     */
    public static String display (ObjectKeyOpt ok)
    {
    	StringBuffer bufStr = new StringBuffer ("");
        	
    	try
    	{
    		if (ok == null
    		||  ok.theValue() == null
    		||  ok.theValue().aValue == null)
    			bufStr.append ("null");
    		else
    			bufStr.append (ok.theValue().aValue);
    	}
    	catch (org.omg.CORBA.BAD_OPERATION badop)
    	{
    		bufStr.append ("null");
    	}
    	
    	bufStr.append ("|");
    	
    	return bufStr.toString();
    }
    
    /**
     * Method display.
     * @param providerLocationProperty
     * @return String
     */
    public static String display(
        ProviderLocationProperty providerLocationProperty)
    {
        StringBuffer outptStr = new StringBuffer("");
        
        try
        {
            outptStr.append("ProviderName" + LIMTag.EQUALTO);
            outptStr.append(providerLocationProperty.aProviderName.theValue() + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        outptStr.append("PostalAddress" + LIMTag.EQUALTO);
        outptStr.append( display(providerLocationProperty.aPostalAddress) + "|");
        outptStr.append("ServiceAddress" + LIMTag.EQUALTO);
        outptStr.append( display(providerLocationProperty.aServiceAddress) + "|");
        outptStr.append("E911Address" + LIMTag.EQUALTO);
        outptStr.append( display(providerLocationProperty.aE911Address) + "|");
        outptStr.append("SwitchSuperPopAddress" + LIMTag.EQUALTO);
        outptStr.append(
            display(providerLocationProperty.aSwitchSuperPopAddress) + "|");
        
        try
        {
            outptStr.append("AddressMatchCode" + LIMTag.EQUALTO);
            outptStr.append(
                providerLocationProperty.aAddressMatchCode.theValue()
                    + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        try
        {
            outptStr.append("AddressMatchCodeDescription" + LIMTag.EQUALTO);
            outptStr.append(
                providerLocationProperty
                    .aAddressMatchCodeDescription
                    .theValue()
                    + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        try
        {
            outptStr.append("CoSwitchSuperPop" + LIMTag.EQUALTO);
            outptStr.append(
                providerLocationProperty.aCoSwitchSuperPop.theValue()
                    + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        try
        {
            outptStr.append("CentralOfficeCode" + LIMTag.EQUALTO);
            outptStr.append(
                providerLocationProperty.aCentralOfficeCode.theValue()
                    + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        try
        {
            outptStr.append("CentralOfficeType" + LIMTag.EQUALTO);
            outptStr.append(
                providerLocationProperty.aCentralOfficeType.theValue()
                    + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        try
        {
            outptStr.append("CityStatePostalCode" + LIMTag.EQUALTO);
            outptStr.append(
                providerLocationProperty.aCityStatePostalCodeValid.theValue() + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }

        try
        {
            outptStr.append("CommunityName" + LIMTag.EQUALTO);
            outptStr.append(
                providerLocationProperty.aCommunityName.theValue() + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }

        try
        {
            outptStr.append("CountyID" + LIMTag.EQUALTO);
            outptStr.append(
                providerLocationProperty.aCountyId.theValue() + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        try
        {
            outptStr.append("DomSwitchPop" + LIMTag.EQUALTO);
            outptStr.append(
                providerLocationProperty.aDomSwitchPop.theValue() + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        try
        {
            outptStr.append("E911Exempt" + LIMTag.EQUALTO);
            outptStr.append(
                providerLocationProperty.aE911Exempt.theValue() + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        try
        {
            outptStr.append("E911NonRecurringCharge" + LIMTag.EQUALTO);
            outptStr.append(
                providerLocationProperty.aE911NonRecurringCharge.theValue()
                    + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        try
        {
            outptStr.append("E911Surcharge" + LIMTag.EQUALTO);
            outptStr.append(
                providerLocationProperty.aE911Surcharge.theValue() + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        try
        {
            outptStr.append("ExchangeCode" + LIMTag.EQUALTO);
            outptStr.append(
                providerLocationProperty.aExchangeCode.theValue() + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        try
        {
            outptStr.append("Exco" + LIMTag.EQUALTO);
            outptStr.append(
                providerLocationProperty.aExco.theValue() + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        try
        {
            outptStr.append("GeoCode" + LIMTag.EQUALTO);
            outptStr.append(
                providerLocationProperty.aGeoCode.theValue() + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        try
        {
            outptStr.append("HorizontalCoordinate" + LIMTag.EQUALTO);
            outptStr.append(
                providerLocationProperty.aHorizontalCoordinate.theValue()
                    + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        try
        {
            outptStr.append("LataCode" + LIMTag.EQUALTO);
            outptStr.append(
                providerLocationProperty.aLataCode.theValue() + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        try
        {
            outptStr.append("LataName" + LIMTag.EQUALTO);
            outptStr.append(
                providerLocationProperty.aLataName.theValue() + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        try
        {
            outptStr.append("Latitude" + LIMTag.EQUALTO);
            outptStr.append(
                providerLocationProperty.aLatitude.theValue()
                    + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        try
        {
            outptStr.append("Longitude" + LIMTag.EQUALTO);
            outptStr.append(
                providerLocationProperty.aLongitude.theValue()
                    + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        
        try
        {
            outptStr.append("LocalProviderAbbreviatedName" + LIMTag.EQUALTO);
            outptStr.append(
                providerLocationProperty
                    .aLocalProviderAbbreviatedName
                    .theValue()
                    + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        try
        {
            outptStr.append("LocalProviderExchangeCode" + LIMTag.EQUALTO);
            outptStr.append(
                providerLocationProperty
                    .aLocalProviderExchangeCode
                    .theValue()
                    + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        try
        {
            outptStr.append("LocalProviderName" + LIMTag.EQUALTO);
            outptStr.append(
                providerLocationProperty.aLocalProviderName.theValue()
                    + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        try
        {
            outptStr.append("LocalProviderNumber" + LIMTag.EQUALTO);
            outptStr.append(
                providerLocationProperty.aLocalProviderNumber.theValue()
                    + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        try
        {
            outptStr.append("LocalProviderServingOfficeCode" + LIMTag.EQUALTO);
            outptStr.append(
                providerLocationProperty
                    .aLocalProviderServingOfficeCode
                    .theValue()
                    + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        try
        {
            outptStr.append("MailingBarCodeSuffix" + LIMTag.EQUALTO);
            outptStr.append(
                providerLocationProperty.aMailingBarCodeSuffix.theValue()
                    + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        try
        {
            outptStr.append("MsaCode" + LIMTag.EQUALTO);
            outptStr.append(
                providerLocationProperty.aMsaCode.theValue() + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        try
        {
            outptStr.append("MsaName" + LIMTag.EQUALTO);
            outptStr.append(
                providerLocationProperty.aMsaName.theValue() + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        try
        {
            outptStr.append("NearestDistanceColoToCo" + LIMTag.EQUALTO);
            outptStr.append(
                providerLocationProperty
                    .aNearestDistanceColoToCo
                    .theValue()
                    + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        try
        {
            outptStr.append("NearestDistanceSuperPopToCo" + LIMTag.EQUALTO);
            outptStr.append(
                providerLocationProperty
                    .aNearestDistanceSuperPopToCo
                    .theValue()
                    + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        try
        {
            outptStr.append("NearestSbcColo" + LIMTag.EQUALTO);
            outptStr.append(
                providerLocationProperty.aNearestSbcColo.theValue() + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        try
        {
            outptStr.append("NearestSbcCoSuperPop" + LIMTag.EQUALTO);
            outptStr.append(
                providerLocationProperty.aNearestSbcCoSuperPop.theValue()
                    + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        try
        {
            outptStr.append("NearestSbcCoWirecenter" + LIMTag.EQUALTO);
            outptStr.append(
                providerLocationProperty.aNearestSbcCoWirecenter.theValue()
                    + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        try
        {
            outptStr.append("OwnedWiring" + LIMTag.EQUALTO);
            outptStr.append(
                providerLocationProperty.aOwnedWiring.theValue() + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        try
        {
            outptStr.append("PublicSafetyAnsweringPointID" + LIMTag.EQUALTO);
            outptStr.append(
                providerLocationProperty.aPublicSafetyAnsweringPointId.theValue() + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        try
        {
            outptStr.append("PostalCarrierCode" + LIMTag.EQUALTO);
            outptStr.append(
                providerLocationProperty.aPostalCarrierCode.theValue()
                    + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        try
        {
            outptStr.append("PrimaryDirectoryCode" + LIMTag.EQUALTO);
            outptStr.append(
                providerLocationProperty.aPrimaryDirectoryCode.theValue()
                    + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        try
        {
            outptStr.append("PrimaryNpaNxx" + LIMTag.EQUALTO);
            outptStr.append(
                providerLocationProperty.aPrimaryNpaNxx.theValue() + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        try
        {
            outptStr.append("QuickDialTone" + LIMTag.EQUALTO);
            outptStr.append(
                providerLocationProperty.aQuickDialTone.theValue() + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        try
        {
            outptStr.append("QuickDialToneNumber" + LIMTag.EQUALTO);
            outptStr.append(
                providerLocationProperty.aQuickDialToneNumber.theValue()
                    + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        try
        {
            outptStr.append("RateCenterCode" + LIMTag.EQUALTO);
            outptStr.append(
                providerLocationProperty.aRateCenterCode.theValue() + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        try
        {
            outptStr.append("RateZone" + LIMTag.EQUALTO);
            outptStr.append(
                providerLocationProperty.aRateZone.theValue() + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        try
        {
            outptStr.append("RateZoneBandCode" + LIMTag.EQUALTO);
            outptStr.append(
                providerLocationProperty.aRateZoneBandCode.theValue()
                    + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        try
        {
            outptStr.append("SagNpa" + LIMTag.EQUALTO);
            outptStr.append(
                providerLocationProperty.aSagNpa.theValue() + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        try
        {
            outptStr.append("SagWireCenter" + LIMTag.EQUALTO);
            outptStr.append(
                providerLocationProperty.aSagWireCenter.theValue() + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        try
        {
            outptStr.append("SbcColoLsoCode" + LIMTag.EQUALTO);
            outptStr.append(
                providerLocationProperty.aSbcColoLsoCode.theValue() + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        try
        {
            outptStr.append("SbcColoWireCenter" + LIMTag.EQUALTO);
            outptStr.append(
                providerLocationProperty.aSbcColoWirecenter.theValue()
                    + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        try
        {
            outptStr.append("SbcServingOfficeCode" + LIMTag.EQUALTO);
            outptStr.append(
                providerLocationProperty.aSbcServingOfficeCode.theValue()
                    + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        try
        {
            outptStr.append("SbcServingOfficeWireCenter" + LIMTag.EQUALTO);
            outptStr.append(
                providerLocationProperty
                    .aSbcServingOfficeWirecenter
                    .theValue()
                    + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        try
        {
            outptStr.append("ServingAreaDescription" + LIMTag.EQUALTO);
            outptStr.append(
                providerLocationProperty.aServingAreaDescription.theValue()
                    + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        try
        {
            outptStr.append("StreetAddressGuideArea" + LIMTag.EQUALTO);
            outptStr.append(
                providerLocationProperty.aStreetAddressGuideArea.theValue()
                    + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        try
        {
            outptStr.append("Surcharge4Percent" + LIMTag.EQUALTO);
            outptStr.append(
                providerLocationProperty.aSurcharge4Percent.theValue()
                    + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        try
        {
            outptStr.append("Surcharge16Percent" + LIMTag.EQUALTO);
            outptStr.append(
                providerLocationProperty.aSurcharge16Percent.theValue()
                    + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        try
        {
            outptStr.append("aSwitchDataSuperPop" + LIMTag.EQUALTO);
            outptStr.append(
                providerLocationProperty.aSwitchDataSuperPop.theValue()
                    + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        try
        {
            outptStr.append("aSwitchVoiceSuperPop" + LIMTag.EQUALTO);
            outptStr.append(
                providerLocationProperty.aSwitchVoiceSuperPop.theValue()
                    + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        try
        {
            outptStr.append("TarCode" + LIMTag.EQUALTO);
            outptStr.append(
                providerLocationProperty.aTarCode.theValue() + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        try
        {
            outptStr.append("TelephoneNumber" + LIMTag.EQUALTO);
            outptStr.append(
                providerLocationProperty.aTelephoneNumber.theValue() + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        try
        {
            outptStr.append("VerticalCoordinate" + LIMTag.EQUALTO);
            outptStr.append(
                providerLocationProperty.aVerticalCoordinate.theValue()
                    + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        try
        {
            outptStr.append("WorkingServiceOnLocation" + LIMTag.EQUALTO);
            outptStr.append(
                providerLocationProperty
                    .aWorkingServiceOnLocation
                    .theValue()
                    + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        try
        {
            outptStr.append("ExceptionCode" + LIMTag.EQUALTO);
            outptStr.append(
                providerLocationProperty.aExceptionCode.theValue() + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        try
        {
            outptStr.append("ExceptionDescription" + LIMTag.EQUALTO);
            outptStr.append(
                providerLocationProperty.aExceptionDescription.theValue()
                    + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
           
        try 
        {   
        	outptStr.append (display(providerLocationProperty.aExtensions.theValue()));
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }

        return outptStr.toString();
    }
    
    /**
     * Method display.
     * @param extensionPropertyArray
     * @return String
     */
    public static String display(ExtensionProperty [] extensionPropertyArray) 
    {
        StringBuffer outptStr = new StringBuffer("");
    
        int extPropertyCount = extensionPropertyArray.length;
        if (extPropertyCount >0)
            outptStr.append("Extensions" + LIMTag.EQUALTO);
        for (int i=0; i < extPropertyCount; i++)
        {
            outptStr.append( display(extensionPropertyArray[i] ) );
        }
    
        return outptStr.toString();
    }
    
    /**
     * Method display.
     * @param extensionProperty
     * @return String
     */
    public static String display(ExtensionProperty extensionProperty)
    {
        StringBuffer outptStr = new StringBuffer("");
    
        try
        {
            outptStr.append(extensionProperty.aId + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        try
        {
            outptStr.append(extensionProperty.aValue + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        return outptStr.toString();
    }
    
    /**
     * Return a display formatted String of the given RangedAddress.
     * Creation date: (5/2/01 11:02:52 AM)
     * @return String
     * @param ra RangedAddress
     */
    public static String display(RangedAddress ra)
    {
    	StringBuffer outptStr = new StringBuffer();
        try
        {
            outptStr.append(ra.aHouseNumberLow.theValue() + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        try
        {
            outptStr.append(ra.aHouseNumberHigh.theValue() + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        try
        {
            outptStr.append(ra.aHouseNumberPrefix.theValue() + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        try
        {
            outptStr.append(ra.aHouseNumberPrefixHigh.theValue() + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        try
        {
            outptStr.append(ra.aHouseNumberSuffix.theValue() + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        try
        {
            outptStr.append(ra.aHouseNumberSuffixHigh.theValue() + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        try
        {
            outptStr.append(ra.aStreetDirection.theValue() + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        try
        {
            outptStr.append(ra.aStreetName.theValue() + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        try
        {
            outptStr.append(ra.aStreetThoroughfare.theValue() + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        try
        {
            outptStr.append(ra.aStreetNameSuffix.theValue() + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        try
        {
            outptStr.append(ra.aCity.theValue() + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        try
        {
            outptStr.append(ra.aState.theValue() + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        try
        {
            outptStr.append(ra.aPostalCode.theValue() + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        try
        {
            outptStr.append(ra.aPostalCodePlus4.theValue() + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        try
        {
            outptStr.append(ra.aCounty.theValue() + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        try
        {
            outptStr.append(ra.aCountry.theValue() + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        try
        {
            outptStr.append(ra.aAdditionalInfo.theValue() + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
    	return outptStr.toString();
    }
    /**
     * Return a display formatted String of the given UnfieldedAddress.
     * Creation date: (5/2/01 10:49:54 AM)
     * @return String
     * @param ua UnfieldedAddress
     */
    public static String display(UnfieldedAddress ua)
    {
    	StringBuffer outptStr = new StringBuffer();
    
    	try {
			outptStr.append(LIMIDLUtil.display(ua.aAddressLines.theValue()));
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
    	
        try
        {
            outptStr.append(ua.aCity.theValue() + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }

        try
        {
            outptStr.append(ua.aState.theValue() + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }

        try
        {
            outptStr.append(ua.aPostalCode.theValue() + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        try
        {
            outptStr.append(ua.aPostalCodePlus4.theValue() + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }

        try
        {
            outptStr.append(ua.aCounty.theValue() + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }

        try
        {
            outptStr.append(ua.aCountry.theValue() + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }

        try
        {
            outptStr.append(ua.aStructureType.theValue()+ "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }

        try
        {
            outptStr.append(ua.aStructureValue.theValue() + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }

        try
        {
            outptStr.append(ua.aLevelType.theValue() + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }

        try
        {
            outptStr.append(ua.aLevelValue.theValue() + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }

        try
        {
            outptStr.append(ua.aUnitType.theValue() + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }

        try
        {
            outptStr.append(ua.aUnitValue.theValue() + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }

        try
        {
            outptStr.append(ua.aAdditionalInfo.theValue() + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        try
        {
            outptStr.append(ua.aBusinessName.theValue() + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
        try
        {
            outptStr.append(ua.aAddressId.theValue() + "|");
        }
        catch (Exception e)
        {
            outptStr.append("|");
        }
        
    	return outptStr.toString();
    }
    
    /**
     * Return an INPUT String from the request data.
     * Creation date: (4/30/01 11:31:22 AM)
     * @param addr
     * @param aPropertiesToGet
     * @param maxAddress
     * @param maxUnit
     * @return String
     */
    public static String displayAddressInput(
        Address addr,
        ProviderLocationProperties[] providerLocationPropertiesArray,
        LongOpt maxAddress,
        LongOpt maxUnit)
    {
    	StringBuffer inptStr = new StringBuffer("");
    	try
    	{
    		if (addr.discriminator() == AddressChoice.FIELDED_ADDRESS)
    		{
    			inptStr.append(LIMIDLUtil.display(addr.aFieldedAddress()));
    		}
    		else
    		{
    			inptStr.append(LIMIDLUtil.display(addr.aUnfieldedAddress()));
    		}
    
            if (providerLocationPropertiesArray != null)
			{
				for( int i = 0 ; i < providerLocationPropertiesArray.length; i++ )
        		{
            		inptStr.append(displayProviderLocationProperties(providerLocationPropertiesArray[i]));
        		}

			}

            try
            {
                inptStr.append(maxAddress.theValue() + "|");
            }
            catch (Exception e)
            {
                inptStr.append("|");
            }

            try
            {
                inptStr.append(maxUnit.theValue() + "|");
            }
            catch (Exception e)
            {
                inptStr.append("|");
            }
        }
        catch (Exception e)
        {
            inptStr.append(
                "\nInput error in LIMIDLUtil.displayAddressInput()\n"
                    + e.toString()
                    + "<"
                    + e.getMessage()
                    + ">");
        }
    
    	return inptStr.toString();
    }
    
    /**
     * Return an INPUT String from the request data.
     * Creation date: (07/20/07)
     * @param addr
     * @param locationPropertiesToGet
     * @param previousOwnerName
     * @param maxBasinAddress
     * @param maxLivingUnit
     * @return String
     */
    public static String displayAddressInput(
        Address addr,
        LocationPropertiesToGetSeqOpt locationPropertiesToGet,
        StringOpt previousOwnerName,
        LongOpt maxBasinAddress,
        LongOpt maxLivingUnit)
    {
    	StringBuffer inptStr = new StringBuffer("");
    	try
    	{
    		if (addr.discriminator() == AddressChoice.FIELDED_ADDRESS)
    		{
    			inptStr.append(LIMIDLUtil.display(addr.aFieldedAddress()));
    		}
    		else
    		{
    			inptStr.append(LIMIDLUtil.display(addr.aUnfieldedAddress()));
    		}
    		
    		try
            {
                inptStr.append(previousOwnerName.theValue() + "|");
            }
            catch (Exception e)
            {
                inptStr.append("|");
            }
            
            try
            {
                for( int i = 0 ; i < locationPropertiesToGet.theValue().length; i++ )
                {
                    inptStr.append(locationPropertiesToGet.theValue()[i] + "|");
                }
            }
            catch (Exception e)
            {
                inptStr.append("|");
            }

            try
            {
                inptStr.append(maxBasinAddress.theValue() + "|");
            }
            catch (Exception e)
            {
                inptStr.append("|");
            }

            try
            {
                inptStr.append(maxLivingUnit.theValue() + "|");
            }
            catch (Exception e)
            {
                inptStr.append("|");
            }
            
        }
        catch (Exception e)
        {
            inptStr.append(
                "\nInput error in LIMIDLUtil.displayAddressInput()\n"
                    + e.toString()
                    + "<"
                    + e.getMessage()
                    + ">");
        }
    
    	return inptStr.toString();
    }
    
    /**
     * Return an INPUT String from the request data.
     * Creation date: (07/03/01 11:31:22 AM)
     * @param addr
     * @param aPropertiesToGet
     * @param maxAddress
     * @param maxUnit
     * @param exchangeCode
     * @return String
     */
    public static String displayAddressInput(
        Address addr,
        ProviderLocationProperties[] providerLocationPropertiesArray,
        LongOpt maxAddress,
        LongOpt maxUnit,
        StringOpt exchangeCode)
    {
    	StringBuffer inptStr = new StringBuffer("");
    	try
    	{
    		if (addr.discriminator() == AddressChoice.FIELDED_ADDRESS)
    		{
    			inptStr.append(LIMIDLUtil.display(addr.aFieldedAddress()));
    		}
    		else
    		{
    			inptStr.append(LIMIDLUtil.display(addr.aUnfieldedAddress()));
    		}
    		
    		try
            {
                inptStr.append(exchangeCode.theValue() + "|");
            }
            catch (Exception e)
            {
                inptStr.append("|");
            }
    
            if (providerLocationPropertiesArray != null)
			{
				for( int i = 0 ; i < providerLocationPropertiesArray.length; i++ )
        		{
            		inptStr.append(displayProviderLocationProperties(providerLocationPropertiesArray[i]));
        		}

			}

            try
            {
                inptStr.append(maxAddress.theValue() + "|");
            }
            catch (Exception e)
            {
                inptStr.append("|");
            }

            try
            {
                inptStr.append(maxUnit.theValue() + "|");
            }
            catch (Exception e)
            {
                inptStr.append("|");
            }
            
        }
        catch (Exception e)
        {
            inptStr.append(
                "\nInput error in LIMIDLUtil.displayAddressInput()\n"
                    + e.toString()
                    + "<"
                    + e.getMessage()
                    + ">");
        }
    
    	return inptStr.toString();
    }
    
    /**
     * Method displayProviderLocationProperties.
     * @param providerLocationProperties
     * @return String
     */
    public static String displayProviderLocationProperties(
                    ProviderLocationProperties providerLocationProperties ) {
        StringBuffer inptStr = new StringBuffer("");
        try
        {
            inptStr.append(providerLocationProperties.aProviderName + "|");
        }
        catch (Exception e)
        {
            inptStr.append("|");
        }
        
        try
        {
            String[] propsToGet =
                providerLocationProperties.aLocationPropertiesToGet;
            for (int i = 0; i < propsToGet.length; i++)
            {
                try
                {
                    inptStr.append( propsToGet[i] + "|");
                }
                catch (Exception e)
                {
                    inptStr.append("|");
                }
            }
        }
        catch (Exception e)
        {
            inptStr.append("|");
        }
        
        return inptStr.toString();
    
    }
    
    /**
     * To display RetrieveLocation data fields with '|" delimiter.
     * Creation date: (4/26/01 2:08:48 PM)
     * @return String
     * @param value RetrieveLocation
     */
    public static String displayAddressMatchResult(AddressMatchResult value) 
    {
    	String ExceptionMsg = new String("");
    	StringBuffer outptStr = new StringBuffer("OutputData = ");
    	int addressCount  = 0;
    		
    	try
    	{
    		// Exact Match Returned		
    		if (value.discriminator() == AddressMatchResultChoice.EXACT_MATCH)
    		{
                addressCount = 1;
                
    			Location loc = value.aLocation();
    
    			outptStr.append (LIMIDLUtil.display (loc));
    		} 
    		else
    		{        
    			// Alternative Addresses Returned
    			if (value.aAlternativeAddressResult().aAlternativeAddresses.discriminator())
    			{	
                	AlternativeAddress[] tmpAltAddress =
                    	value.aAlternativeAddressResult().aAlternativeAddresses.theValue();
                	addressCount = tmpAltAddress.length;
                	for (int i = 0; i < addressCount; i++)
                	{
                    	// Alternative Address
                    	if (tmpAltAddress[i].discriminator()
                        	== AlternativeAddressChoice.ALTERNATIVE_ADDRESS)
                    	{
                        	Address tmpAddress = tmpAltAddress[i].aAddress();
                        	if (tmpAddress.discriminator()
                            	== AddressChoice.FIELDED_ADDRESS)
                        	{
                            	outptStr.append (LIMIDLUtil.display (tmpAddress.aFieldedAddress()));
                        	}
                        	else
                        	{
                            	// unfielded address
                            	outptStr.append (LIMIDLUtil.display (tmpAddress.aUnfieldedAddress()));
                        	}
                    	}
                    	else if (tmpAltAddress[i].discriminator()
                            == AlternativeAddressChoice.ALTERNATIVE_RANGED_ADDRESS)
                    	{
                        	// ranged Address	
                        	outptStr.append (LIMIDLUtil.display (tmpAltAddress[i].aRangedAddress()));
                    	}
                    	else if (tmpAltAddress[i].discriminator()
                            == AlternativeAddressChoice.ALTERNATIVE_LOCATION)
                    	{
                        // Location
                        outptStr.append (LIMIDLUtil.display (tmpAltAddress[i].aLocation()));
    					}
                	}
                } // end if (value.aAlternativeAddressResult().aAlternativeAddresses != null)
                if (value.aAlternativeAddressResult().aSubmittedAddressExceptions.discriminator())
                {
                	// SubmittedAddressExceptions
                	outptStr.append (LIMIDLUtil.
                		display (value.aAlternativeAddressResult().aSubmittedAddressExceptions.theValue()));
                }
    		}
    	}
        catch (org.omg.CORBA.BAD_OPERATION cboe)
        {
            ExceptionMsg = "Exception in displayAddressMatchResult() -  "
                    + cboe + "<" + cboe.getMessage() + ">\r\n";
        }
        catch (Exception e)
        {
            ExceptionMsg = "Exception in displayAddressMatchResult() -  "
                    + e + "<" + e.getMessage() + "> \r\n";
        }

        return new String( ExceptionMsg + "Address Count=" + addressCount 
                + " " + outptStr.toString());
    }
    
    /**
     * To display ServiceAddressMatchResult data fields with '|" delimiter.
     * Creation date: (7/22/07 2:08:48 PM)
     * @return String
     * @param value ServiceAddressMatchResult
     */
    public static String displayServiceAddressMatchResult(ServiceAddressMatchResult value) 
    {
    	String ExceptionMsg = new String("");
    	StringBuffer outptStr = new StringBuffer("OutputData = ");
    	int addressCount  = 0;
    		
    	try
    	{
    		// Exact Match Returned		
    		if (value.discriminator().value() == AddressMatchResultChoice._EXACT_MATCH)
    		{
                addressCount = 1;
    			outptStr.append(LIMIDLUtil.display(value.aServiceLocation()));
    		} 
    		else
    		{        
    			// Alternative Addresses Returned
    			if (value.aAlternativeServiceAddresses() != null && value.aAlternativeServiceAddresses().length > 0)
    			{	
    			    AlternativeServiceAddress[] tmpAltServiceAddress = value.aAlternativeServiceAddresses();
                	addressCount = tmpAltServiceAddress.length;
                	for (int i = 0; i < addressCount; i++)
                	{
                    	// Alternative Address
                    	if (tmpAltServiceAddress[i].discriminator().value()
                        	== AlternativeServiceAddressChoice._ALTERNATIVE_ADDRESS2)
                    	{
                        	outptStr.append(LIMIDLUtil.display(tmpAltServiceAddress[i].aAddress()));
                    	}
                    	else if (tmpAltServiceAddress[i].discriminator().value()
                            == AlternativeServiceAddressChoice._ALTERNATIVE_LOCATION2)
                    	{
                    	    outptStr.append(LIMIDLUtil.display(tmpAltServiceAddress[i].aServiceLocation()));
                    	}
                	}
                } 
    		}
    	}
        catch (org.omg.CORBA.BAD_OPERATION cboe)
        {
            ExceptionMsg = "Exception in displayServiceAddressMatchResult() -  "
                    + cboe + "<" + cboe.getMessage() + ">\r\n";
        }
        catch (Exception e)
        {
            ExceptionMsg = "Exception in displayServiceAddressMatchResult() -  "
                    + e + "<" + e.getMessage() + "> \r\n";
        }

        return new String( ExceptionMsg + "Address Count=" + addressCount 
                + " " + outptStr.toString());
    }
    
    /**
     * To display PostalAddressMatchResult data fields with '|" delimiter.
     * Creation date: (7/22/07 2:08:48 PM)
     * @return String
     * @param value PostalAddressMatchResult
     */
    public static String displayPostalAddressMatchResult(PostalAddressMatchResult value) 
    {
    	String ExceptionMsg = new String("");
    	StringBuffer outptStr = new StringBuffer("OutputData = ");
    	int addressCount  = 0;
    		
    	try
    	{
    		// Exact Match Returned		
    		if (value.discriminator().value() == AddressMatchResultChoice._EXACT_MATCH)
    		{
                addressCount = 1;
    			outptStr.append(LIMIDLUtil.display(value.aPostalLocation()));
    		} 
    		else
    		{        
    			// Alternative Addresses Returned
    			if (value.aAlternativePostalAddressResult() != null)
    			{	
    			    if (value.aAlternativePostalAddressResult().aAlternativePostalAddresses != null &&
    			        value.aAlternativePostalAddressResult().aAlternativePostalAddresses.discriminator() &&
    			        value.aAlternativePostalAddressResult().aAlternativePostalAddresses.theValue().length > 0)
    			    {
    			        PostalLocation[] tmpPostalLocation = value.aAlternativePostalAddressResult().aAlternativePostalAddresses.theValue();
    			        addressCount = tmpPostalLocation.length;
    			        
    			        for (int i = 0; i < addressCount; i++)
                    	{
    			            outptStr.append(LIMIDLUtil.display(tmpPostalLocation[i]));
                    	}
    			    }
    			    
    			    if (value.aAlternativePostalAddressResult().aSubmittedAddressExceptions != 	null &&
    			        value.aAlternativePostalAddressResult().aSubmittedAddressExceptions.discriminator() &&
    			        value.aAlternativePostalAddressResult().aSubmittedAddressExceptions.theValue().length > 0)
    			    {
    			        outptStr.append(LIMIDLUtil.display(value.aAlternativePostalAddressResult().aSubmittedAddressExceptions.theValue()));
    			    }
                } 
    		}
    	}
        catch (org.omg.CORBA.BAD_OPERATION cboe)
        {
            ExceptionMsg = "Exception in displayPostalAddressMatchResult() -  "
                    + cboe + "<" + cboe.getMessage() + ">\r\n";
        }
        catch (Exception e)
        {
            ExceptionMsg = "Exception in displayPostalAddressMatchResult() -  "
                    + e + "<" + e.getMessage() + "> \r\n";
        }

        return new String( ExceptionMsg + "Address Count=" + addressCount 
                + " " + outptStr.toString());
    }
    
    /**
     * Return an INPUT String from the request data.
     * Creation date: (4/30/01 12:47:48 PM)
     * @param telephoneNumber
     * @param providerLocationPropertiesArray
     * @param maxAddress
     * @param maxUnit
     * @return String
     */
    public static String displayServiceInput(
        TelephoneNumber telephoneNumber,
        ProviderLocationProperties[] providerLocationPropertiesArray,
        LongOpt maxAddress,
        LongOpt maxUnit)
    {
        StringBuffer inptStr = new StringBuffer("");

        inptStr.append(displayTelephoneNumber(telephoneNumber));

		if (providerLocationPropertiesArray != null)
		{
			for( int i = 0 ; i < providerLocationPropertiesArray.length; i++ )
        	{
            	inptStr.append(displayProviderLocationProperties(providerLocationPropertiesArray[i]));
        	}

		}

        try
        {
            inptStr.append(maxAddress.theValue() + "|");
        }
        catch (Exception e)
        {
            inptStr.append("|");
        }

        try
        {
            inptStr.append(maxUnit.theValue() + "|");
        }
        catch (Exception e)
        {
            inptStr.append("|");
        }
    
    	return inptStr.toString();
    }
    
    /**
     * Method displayTelephoneNumber.
     * @param telephoneNumber
     * @return String
     */
    public static String displayTelephoneNumber(
        TelephoneNumber telephoneNumber)
    {
        StringBuffer inptStr = new StringBuffer("");
        try
        {
            inptStr.append(telephoneNumber.aNpa + "|");
        }
        catch (Exception e)
        {
            inptStr.append("|");
        }

        try
        {
            inptStr.append(telephoneNumber.aNxx + "|");
        }
        catch (Exception e)
        {
            inptStr.append("|");
        }

        try
        {
            inptStr.append(telephoneNumber.aLine + "|");
        }
        catch (Exception e)
        {
            inptStr.append("|");
        }

        try
        {
            inptStr.append(telephoneNumber.aExtension + "|");
        }
        catch (Exception e)
        {
            inptStr.append("|");
        }
        return inptStr.toString();
    }
    
    /**
     * Method instantiateNewInitializedFieldedAddress.
     * @return FieldedAddress
     */
    public static FieldedAddress instantiateNewInitializedFieldedAddress() {
        FieldedAddress fa = new FieldedAddress();
        fa.aAdditionalInfo           = IDLUtil.toOpt("");
        fa.aAssignedHouseNumber      = IDLUtil.toOpt("");
        fa.aBox                      = IDLUtil.toOpt("");
        fa.aCity                     = IDLUtil.toOpt("");
        fa.aCountry                  = IDLUtil.toOpt("");
        fa.aCounty                   = IDLUtil.toOpt("");
        fa.aHouseNumber              = IDLUtil.toOpt("");
        fa.aHouseNumberPrefix        = IDLUtil.toOpt("");
        fa.aHouseNumberSuffix        = IDLUtil.toOpt("");
        fa.aPostalCode               = IDLUtil.toOpt("");
        fa.aPostalCodePlus4          = IDLUtil.toOpt("");
        fa.aRoute                    = IDLUtil.toOpt("");
        fa.aState                    = IDLUtil.toOpt("");
        fa.aStreetDirection          = IDLUtil.toOpt("");
        fa.aStreetName               = IDLUtil.toOpt("");
        fa.aStreetNameSuffix         = IDLUtil.toOpt("");
        fa.aStreetThoroughfare       = IDLUtil.toOpt("");
        fa.aStructureType            = IDLUtil.toOpt("");
        fa.aStructureValue           = IDLUtil.toOpt("");
        fa.aLevelType                = IDLUtil.toOpt("");
        fa.aLevelValue               = IDLUtil.toOpt("");
        fa.aUnitType                 = IDLUtil.toOpt("");
        fa.aUnitValue                = IDLUtil.toOpt("");
        fa.aOriginalStreetDirection  = IDLUtil.toOpt("");
        fa.aOriginalStreetNameSuffix = IDLUtil.toOpt("");
        fa.aCassAddressLines         = new StringSeqOpt();
        fa.aCassAddressLines.__default(); 
        fa.aAuxiliaryAddressLines    = new StringSeqOpt();
        fa.aAuxiliaryAddressLines.__default(); 
        fa.aCassAdditionalInfo       = IDLUtil.toOpt("");
        fa.aBusinessName             = IDLUtil.toOpt("");
        return fa;
    }
    
    /**
     * Method display.
     * @param serviceAreas
     * @return String
     */
	public static String display(ServiceArea[] serviceAreas) {
		StringBuffer outptStr = new StringBuffer("");
		if (serviceAreas != null){
    		for (int i = 0; i < serviceAreas.length; i++) {
    			ServiceArea aServiceArea = serviceAreas[i];
    			outptStr.append(aServiceArea.aServiceAreaCode + "|");
    			outptStr.append(aServiceArea.aServiceAreaDescription + "|");
    		}
        }    		
		return outptStr.toString();
	}

    
    /**
     * Method display.
     * @param serviceAreas
     * @return String
     */
    public static String display(ServiceAreaSeqOpt serviceAreas) {
        StringBuffer outptStr = new StringBuffer("");
        ServiceArea[] areas = null;
        try {
            areas = serviceAreas.theValue();
        } catch (Exception e) {
            outptStr.append("|");
        }
        
        outptStr.append(display(areas));
        return outptStr.toString();
    }
    
    /**
     * Method display.
     * @param marketInfo
     * @return String
     */
    public static String display(MarketInformationOpt marketInfo) {
        StringBuffer outptStr = new StringBuffer("");
        String outStr = "";
        try {
            outStr = display(marketInfo.theValue());
        } catch (Exception e) {
            outStr = "|";
        }
        return outStr;
    }
    
    /**
     * Method display.
     * @param marketInfo
     * @return String
     */
	public static String display(MarketInformation marketInfo) {
		StringBuffer outptStr = new StringBuffer("");
		outptStr.append(marketInfo.aBillingMarket + "|");
		outptStr.append(marketInfo.aBillingSubMarket + "|");
		outptStr.append(marketInfo.aBillingSystemId + "|");
		outptStr.append(marketInfo.aCustomerApprovalSystemRegion + "|");
        
        try {
    		outptStr.append(marketInfo.aLocalMarket.theValue() + "|");
        } catch (Exception e){
            outptStr.append("|");
        }
        
		outptStr.append(marketInfo.aMarketDisplayName + "|");
		
		return outptStr.toString();
	}
    
    /**
     * Method display.
     * @param context
     * @return String
     */
    public static String display(BisContext context) {
        StringBuffer sb = new StringBuffer();
        if( context != null && context.aProperties != null)
        {
                for(int idx = 0; idx < context.aProperties.length ; idx++ )
                {
                    if (context.aProperties[idx] != null)
                    {
                        sb.append(context.aProperties[idx].aTag);
                        sb.append("<");
                        //Block RACFPassword password in log file
                        if (context.aProperties[idx].aTag.equalsIgnoreCase("RACFPassword"))
                        {
                            sb.append("****");
                        }
                        else
                        {
                            sb.append(context.aProperties[idx].aValue);
                        }
                        sb.append(">|");
                    }
                }
        }
        
        return sb.toString();
    }
    
    /**
     * Method display.
     * @param result
     * @return String
     */
    public static String display(RetrieveServiceAreaByPostalCodeReturn result) {
        StringBuffer sb = new StringBuffer();
        sb.append(display(result.aContext));
        try {
            sb.append(display(result.aMarketInformation.theValue()));
        } catch (Exception e) {
            sb.append("|");
        }
        sb.append(result.aPostalCode);
        sb.append("|");
        try {
            sb.append(display(result.aServiceAreas.theValue()));
        } catch (Exception e) {
            sb.append("|");
        }
        
        return sb.toString();
    }
    
    /**
     * Method EiaDate
     * @param EiaDate
     * @return String
     */
    public static String display(EiaDate eiaDate)
    {
        StringBuffer sb = new StringBuffer();
        
        try
        {
            sb.append(eiaDate.aDay + "-");
        }
        catch (Exception e)
        {
            sb.append("-");
        }
        
        try
        {
            sb.append(eiaDate.aMonth + "-");
        }
        catch (Exception e)
        {
            sb.append("-");
        }
        
        try
        {
            sb.append(eiaDate.aYear + "-");
        }
        catch (Exception e)
        {
            sb.append("-");
        }
        
        return sb.toString();
    }
    
    /**	
     * Convert Address2 to Address
     * @param address2 Address2
     * @return Address
     */
    public static Address convertAddress2ToAddress(Address2 address2)
    {
        Address address = new Address();
        try
        {
            if (address2.discriminator().value() == Address2Choice._FIELDED_ADDRESS2)
            {
                address.aFieldedAddress(address2.aFieldedAddress());
            }
            else if (address2.discriminator().value() == Address2Choice._UNFIELDED_ADDRESS2)
            {
                address.aUnfieldedAddress(address2.aUnfieldedAddress());
            }
        }
        catch (Exception e) {}
        
        return address;
    }
    
    /**	
     * Convert Address to Address2
     * @param address2 Address2
     * @return Address
     */
    public static Address2 convertAddressToAddress2(Address address)
    {
        Address2 newAddress = new Address2();
        try
        {
            if (address.discriminator().value() == AddressChoice._FIELDED_ADDRESS)
            {
                newAddress.aFieldedAddress(address.aFieldedAddress());
            }
            else if (address.discriminator().value() == AddressChoice._UNFIELDED_ADDRESS)
            {
                newAddress.aUnfieldedAddress(address.aUnfieldedAddress());
            }
        }
        catch (Exception e) {}
        
        return newAddress;
    }
    
    /**	
     * Input Ranged Address and return ServiceLocation
     * @param rangAddress RangedAddress
     * @return ServiceLocation
     */
    public static ServiceLocation formatServiceLocation(RangedAddress rangAddress)
    {
        ServiceLocation locServiceLocation = ServiceLocationBuilder.getDefaultServiceLocation();
        locServiceLocation.aServiceAddress.aRangedAddress(rangAddress);
        return locServiceLocation;
    }
    
    /**	
     * Input Address and return ServiceLocation
     * @param address Address
     * @return ServiceLocation
     */
    public static ServiceLocation formatServiceLocation(Address address)
    {
        ServiceLocation locServiceLocation = ServiceLocationBuilder.getDefaultServiceLocation();
        locServiceLocation.aServiceAddress = convertAddressToAddress2(address);
        return locServiceLocation;
    }
    
    /**	
     * Input Address2 and return ServiceLocation
     * @param address2 Address2
     * @return ServiceLocation
     */
    public static ServiceLocation formatServiceLocation(Address2 address2)
    {
        ServiceLocation locServiceLocation = ServiceLocationBuilder.getDefaultServiceLocation();
        locServiceLocation.aServiceAddress = address2;
        return locServiceLocation;
    }
    
    /** Display method
	 * @param telephoneNumber TelephoneNumber
	 * @param locationPropertiesToGet LocationPropertiesToGetSeqOpt
	 * 
	 **/
    public static String displayTelephoneNumberInput(TelephoneNumber telephoneNumber, LocationPropertiesToGetSeqOpt locationPropertiesToGet) {
		StringBuffer inptStr = new StringBuffer("");
		
        inptStr.append(displayTelephoneNumber(telephoneNumber));
        
        try
        {
            for( int i = 0 ; i < locationPropertiesToGet.theValue().length; i++ )
            {
                inptStr.append(locationPropertiesToGet.theValue()[i]);
            }
        }
        catch (Exception e)
        {
            inptStr.append("|");
        }
        
		return inptStr.toString();
	}
    
    /**
     * Display the value of an CompositeObjectKey.
     * @return String
     * @param cok CompositeObjectKey
     */
    public static String display(CompositeObjectKey cok)
    {
    	StringBuffer bufStr = new StringBuffer ("");
        	
    	try
    	{
    		if (cok == null           ||
    		    cok.aKeys == null     ||  
    		    cok.aKeys.length == 0 ||
    		    cok.aKeys[0].aValue == null)
    		{
    			bufStr.append ("null");
    		}
    		else
    		{
    			bufStr.append (cok.aKeys[0].aValue);
    		}
    	}
    	catch (org.omg.CORBA.BAD_OPERATION badop)
    	{
    		bufStr.append ("null");
    	}
    	
    	return bufStr.toString();
    }
    
    
    /**
     * Return an INPUT String from the BCAM request data.
     * @param billingAccountKey
     * @param oldAddress
     * @param newAddress
     * @param deliveryPointValidationCode
     * @param aContactName
     * @return String
     */
    public static String displayBCAMInput(
        CompositeObjectKey billingAccountKey,
        AddressOpt oldAddress,
        Address newAddress,
        StringOpt deliveryPointValidationCode,
        StringOpt aContactName)
    {
        StringBuffer inptStr = new StringBuffer("");
        
        inptStr.append("BillingAccountKey<");
        inptStr.append(display(billingAccountKey));
        inptStr.append(">");
        inptStr.append("|");
        
        inptStr.append("OldAddress<");
        inptStr.append(display(oldAddress));
        inptStr.append(">");
        inptStr.append("|");
        
        inptStr.append("NewAddress<");
        inptStr.append(display(newAddress));
        inptStr.append(">");
        inptStr.append("|");
        
        inptStr.append("DeliveryPointValidationCode<");
        try
        {
            inptStr.append(deliveryPointValidationCode.theValue());
        }
        catch (Exception e)
        {
            inptStr.append("null");
        }
        inptStr.append(">");
        inptStr.append("|");
        
        inptStr.append("ContactName<");
        try
        {
            inptStr.append(aContactName.theValue());
        }
        catch (Exception e)
        {
            inptStr.append("null");
        }
        inptStr.append(">");
        
        return inptStr.toString();
    }
    
    /**
     * Return an OUTPUT String from the BCAM response data.
     * @param ubar
     * @return String
     */
    public static String displayBCAMOutput(UpdateBillingAddressReturn ubar)
    {
        StringBuffer inptStr = new StringBuffer("");
        
        if (ubar == null)
        {
            return inptStr.append("null").toString();
        }
        
        inptStr.append("BisContext<");
        try
        {
            inptStr.append(display(ubar.aContext));
        }
        catch (Exception e)
        {
            inptStr.append("null");
        }
        inptStr.append(">");
        inptStr.append("|");
        
        inptStr.append("Code<");
        inptStr.append("" + ubar.aCode);
        inptStr.append(">");
        inptStr.append("|");
        
        inptStr.append("Message<");
        inptStr.append(ubar.aMessage);
        inptStr.append(">");
        
        return inptStr.toString();
    }
    
    /**
     * Return an OUTPUT String from the BIMX response.
     * @param bimxRet 
     * @return String
     */
    public static String displayBIMXOutput(AddNotesToBillingAccountReturn bimxRet)
    {
        StringBuffer inptStr = new StringBuffer("");
        
        if (bimxRet == null)
        {
            return inptStr.append("null").toString();
        }
        
        inptStr.append("BisContext<");
        try
        {
            inptStr.append(display(bimxRet.aContext));
        }
        catch (Exception e)
        {
            inptStr.append("null");
        }
        inptStr.append(">");
        inptStr.append("|");
        
        inptStr.append("BillingAccountContext<");
        try
        {
            inptStr.append(display(bimxRet.aBillingAccountContext));
        }
        catch (Exception e)
        {
            inptStr.append("null");
        }
        inptStr.append(">");
        
        return inptStr.toString();
    }
    
    /**
     * Method display.
     * @param objContext
     * @return String
     */
    public static String display(ObjectContext objContext) {
        StringBuffer sb = new StringBuffer();
        if( objContext != null && objContext.aProperties != null)
        {
                for(int idx = 0; idx < objContext.aProperties.length ; idx++ )
                {
                    if (objContext.aProperties[idx] != null)
                    {
                        sb.append(objContext.aProperties[idx].aTag);
                        sb.append("<");
                        sb.append(objContext.aProperties[idx].aValue);
                        sb.append(">|");
                    }
                }
        }
        
        return sb.toString();
    }
}
