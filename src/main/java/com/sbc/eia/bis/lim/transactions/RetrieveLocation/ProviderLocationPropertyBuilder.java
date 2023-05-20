// $Id: ProviderLocationPropertyBuilder.java,v 1.21 2007/07/05 18:30:30 jh9785 Exp $

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
 *  This file contains the ProviderLocationPropertyBuilder class.
 *  Description
 */
package com.sbc.eia.bis.lim.transactions.RetrieveLocation;

import java.util.Properties;

import com.sbc.eia.bis.lim.util.LIMIDLUtil;
import com.sbc.eia.idl.lim.helpers.ProviderLocationPropertyHandler;
import com.sbc.eia.idl.lim_types.Address;
import com.sbc.eia.idl.lim_types.AddressOpt;
import com.sbc.eia.idl.lim_types.ExtensionProperty;
import com.sbc.eia.idl.lim_types.LocationPropertiesToGetValue;
import com.sbc.eia.idl.lim_types.ProviderLocationProperty;
import com.sbc.eia.idl.types.StringOpt;

/** Description
 *  The ProviderLocationPropertyBuilder builds a ProviderLocationProperty
 *  based on the properties the client has asked for.  The
 *  ProviderLocationProperty is initially defaulted in the following manner:
 *      Non-optional fields are set to an appropriate default value.  For
 *      Strings, the default is an empty string ("").
 * 
 *      Optional fields are assigned new instance of the optional type,
 *      and the instance's __default method is called.
 * 
 *  The ProviderLocationPropertyBuilder then initializes the 
 *  ProviderLocationProperty based on the requested properties.  Each
 *  property is set in the following manner:
 *      If the "ALL" property was requested
 *          and a valid value exists
 *              set the property to the value
 *          otherwise
 *              leave the property defaulted
 *          
 *      If the specific property is requested but the "ALL" property was not
 *          and a valid value exists
 *              set the property to the value
 *          otherwise
 *              set the property to an appropriate default value
 *              (ex: "" for String)
 *  Description
 */
public class ProviderLocationPropertyBuilder
{
    private RetrieveLocReq.LocationPropertiesRequested
        m_locationPropertiesRequested = null;
    private ProviderLocationProperty m_providerLocationProperty = null;
    private ProviderLocationProperty m_cachedProviderLocationProperty = null;
    private Properties properties = new Properties();
    private Properties cachedProperties = new Properties();

    /**
     * Constructor for ProviderLocationPropertyBuilder.
     */
    public ProviderLocationPropertyBuilder(
        RetrieveLocReq.LocationPropertiesRequested request)
    {
        super();
        setLocationPropertiesRequested(request);
        initializePropertiesBasedOnRequest();

    }

    private void setLocationPropertiesRequested(
        RetrieveLocReq
            .LocationPropertiesRequested locationPropertiesRequested)
    {
        m_locationPropertiesRequested = locationPropertiesRequested;
    }

    private RetrieveLocReq
        .LocationPropertiesRequested getLocationPropertiesRequested()
    {
        return m_locationPropertiesRequested;
    }

    private void setProviderLocationProperty(ProviderLocationProperty
        providerLocationProperty)
    {
        m_providerLocationProperty = providerLocationProperty;
    }

    /**
     * Method getProviderLocationProperty returns the internal
     * ProviderLocationProperty.  The returned ProviderLocationProperty
     * is initalized based upon the properties to get in the request.
     * At the very least, the return ProviderLocationProperty will contain
     * a ProviderLocationProperty with all properties defaulted.
     * @return ProviderLocationProperty
     */
    public ProviderLocationProperty getProviderLocationProperty()
    {
        return m_providerLocationProperty;
    }
    
    /**
     * Method setCachedProviderLocationProperty.
     * @param cachedProviderLocationProperty
     */
    private void setCachedProviderLocationProperty(ProviderLocationProperty cachedProviderLocationProperty)
    {
        m_cachedProviderLocationProperty = cachedProviderLocationProperty;
    }

    /**
     * Method getCachedProviderLocationProperty.
     * @return ProviderLocationProperty
     */
    public ProviderLocationProperty getCachedProviderLocationProperty()
    {
        return m_cachedProviderLocationProperty;
    }

    /**
     * Method getExensionProperties returns an array of Extension Properties
     * collected thus far.
     * @return ExtensionProperty[]
     */
    private ExtensionProperty[] getExensionProperties()
    {
        ExtensionProperty[] props = new ExtensionProperty[properties.values().size()];
        properties.values().toArray(props);
        return props;
    }
    
    /**
     * Method getCachedExtensionProperties returns an array of cached 
     * Extension Properties collected thus far.
     * NOTE:  The cached properties includes all properties, not just
     * what was requested.
     * @return ExtensionProperty[]
     */
    private ExtensionProperty[] getCachedExtensionProperties()
    {
        ExtensionProperty[] props = new ExtensionProperty[cachedProperties.values().size()];
        cachedProperties.values().toArray(props);
        return props;
    }
    
    /**
     * Method initializePropertiesBasedOnRequest creates a
     * ProviderLocationProperty object with defaulted fields.  Optional IDL
     * types like StringOpt are defauled by calling their _default method.
     * The method then proceeds to set default values for the properties
     * that were requested by the client.  Depending on what properties were
     * requested, the resulting ProviderLocationProperty can contain a 
     * mixture of IDL optional fields defaulted by calling their _default
     * methods, or the fields will contain an appropriate default value
     * (ex: "" if String).
     */
    private void initializePropertiesBasedOnRequest()
    {
        // set a provider location property with defaulted opt fields 
        setProviderLocationProperty(ProviderLocationPropertyHandler.getDefaultProviderLocationProperty());
        setCachedProviderLocationProperty(ProviderLocationPropertyHandler.getDefaultProviderLocationProperty());

        // initialize opt fields based on request
        setAddressMatchCode(null);
        setAddressMatchCodeDescription(null);
        setCentralOfficeCode(null);
        setCentralOfficeType(null);
        setCommunityName(null);
        setCoSwitchSuperPop(null);
        setCustomerPremiseIndicator(null);
        setDomSwitchPop(null);
        setE911Address((AddressOpt) null);
        setE911Exempt(null);
        setE911NonRecurringCharge(null);
        setE911Surcharge(null);
        setExchangeCode(null);
        setExco(null);
		setEktId(null);
        setGeoCode(null);
        setHorizontalCoordinate(null);
        setLataCode(null);
        setLataName(null);
  //      setLatitudeLongitude(null);
        setLatitude(null);
        setLongitude(null);
        setLocalProviderExchangeCode(null);
        setLocalProviderName(null);
        setLocalProviderNumber(null);
        setLocalProviderServingOfficeCode(null);
        setLocalProviderAbbreviatedName(null);
        setMailingBarCodeSuffix(null);
        setMsaCode(null);
        setMsaName(null);
        setNearestDistanceColoToCo(null);
        setNearestDistanceSuperPopToCo(null);
        setNearestSbcColo(null);
        setNearestSbcCoSuperPop(null);
        setNearestSbcCoWirecenter(null);
        setOwnedWiring(null);
        setPostalCarrierCode(null);
        setPrimaryDirectoryCode(null);
        setPrimaryNpaNxx(null);
        setQuickDialTone(null);
        setQuickDialToneNumber(null);
        setRateCenterCode(null);
        setRateZone(null);
        setRateZoneBandCode(null);
        setSagNpa(null);
        setSagWireCenter(null);
        setSbcColoLsoCode(null);
        setSbcColoWirecenter(null);
        setSbcServingOfficeCode(null);
        setSbcServingOfficeWirecenter(null);
        setServingAreaDescription(null);
        setStreetAddressGuideArea(null);
        setSurcharge16Percent(null);
        setSurcharge4Percent(null);
        setSwitchDataSuperPop(null);
        setSwitchSuperPopAddress((AddressOpt) null);
        setSwitchVoiceSuperPop(null);
        setTarCode(null);
        setTelephoneNumber(null);
        setVerticalCoordinate(null);
        setWorkingServiceOnLocation(null);
        
        // set extension properties
        setCityStatePostalCodeValid(null);
    }

    /**
     * Method isRequestedALL.  Convenience method for determining if
     * RetrievAll was specified in the request for properties.
     * @return boolean
     */
    private boolean isRequestedALL()
    {
        return getLocationPropertiesRequested().isRetrieveALL();
    }
    
    /**
     * Method setForProperty.  This method encompasses the logic for whether
     * or not to set a Location Property based on what location properties
     * were asked for in the request.
     * @param isRequested indicates whether the property being set was
     * actually being requested
     * @param optToSet the StringOpt value to set
     * @param valueToUse the String value to use
     */
    private void setForProperty(
        boolean isRequested,
        StringOpt optToSet,
        String valueToUse)
    {
        
        if(optToSet != null)
        {
            if (isRequested)
            {
                if (valueToUse != null && valueToUse.length() > 0)
                {
                    // set or overwrite existing value
                    optToSet.theValue(valueToUse);
                }
                else
                {
                    // value to use is null or ""
                    if (!isRequestedALL())
                    {
                        // explicitly requested property
                        if( valueToUse == null || valueToUse.equals("") )
                        {
                            // reset
                            optToSet.theValue("");
                        }
                    }
                    else
                    {
                        // not explicit requested property
                        if( valueToUse == null || valueToUse.equals("") )
                        {
                            // reset
                           optToSet.theValue(null);
                           optToSet.__default();
                        }
                    }
                }
            }
        }
        else
        {
            System.out.println("Warning: A ProviderLocationProperty being set by " + getClass().getName() + " is unexpectedly null.");
        }
    }
    
    /**
     * Method setForCachedProperty.
     * @param optToSet
     * @param valueToUse
     */
    private void setForCachedProperty(
        StringOpt optToSet,
        String valueToUse)
    {
        
        if(optToSet != null)
        {
            if (valueToUse != null && valueToUse.length() > 0)
            {
                // set or overwrite existing value
                optToSet.theValue(valueToUse);
            }
            else
            {
                optToSet.theValue(null);
                optToSet.__default();
            }
        }
        else
        {
            System.out.println("Warning: A ProviderLocationProperty being set by " + getClass().getName() + " is unexpectedly null.");
        }
    }

    /**
     * Method setForExtensionProperty.  This method encompasses the logic
     * for setting Extension Properties.  The rules are similar to those for
     * StringOpt but unlike a StringOpt, an ExtensionProperty is a required
     * field.
     * @param isRequested indicates whether the property is requested
     * @param propertyId the id of the property to set
     * @param valueToUse the value of the property to use
     */
    private void setForExtensionProperty( boolean isRequested, String propertyId, String valueToUse )
    {
        ExtensionProperty property = (ExtensionProperty)properties.get(propertyId);;

        if (isRequested)
        {
            if (valueToUse != null && valueToUse.length() > 0)
            {
                // set or overwrite existing value
                if( property == null )
                {
                    property = new ExtensionProperty(propertyId, "");
                    properties.put(propertyId, property);
                }
                property.aValue = valueToUse;
            }
            else
            {
                // value to use is null or ""
                if (!isRequestedALL())
                {
                    // explicitly requested property
                    if( valueToUse == null || valueToUse.equals("") )
                    {
                        // reset
                        if( property == null )
                        {
                            property = new ExtensionProperty(propertyId, "");
                        } else {
                            property.aValue = "";
                        }
                        
                        properties.put(propertyId, property);
                    }
                }
                else
                {
                    // not explicit requested property
                    if( valueToUse == null || valueToUse.equals("") )
                    {
                        // remove
                        if(property != null)
                        {
                            properties.remove(propertyId);
                        }
                    }
                }
            }
        }
        
        m_providerLocationProperty.aExtensions.theValue (getExensionProperties());
    }

    /**
     * Method setForExtensionProperty.  This method encompasses the logic
     * for setting the cached Extension Properties.
     * The rules are similar to those for StringOpt but unlike a StringOpt,
     * an ExtensionProperty is a required field.
     * 
     * NOTE: This method caches a property regardless of whether it was
     * requested or not.
     * 
     * @param propertyId the id of the property to set
     * @param valueToUse the value of the property to use
     */
    private void setForCachedExtensionProperty(String propertyId, String valueToUse )
    {
        ExtensionProperty cachedProperty = (ExtensionProperty)cachedProperties.get(propertyId);;

        if (valueToUse != null && valueToUse.length() > 0)
        {
            // set or overwrite existing value
            if( cachedProperty == null )
            {
                cachedProperty = new ExtensionProperty(propertyId, "");
                cachedProperties.put(propertyId, cachedProperty);
            }
            cachedProperty.aValue = valueToUse;
        }
        else
        {
            // value to use is null or ""
            if (!isRequestedALL())
            {
                // explicitly requested cachedProperty
                if( valueToUse == null || valueToUse.equals("") )
                {
                    // reset
                    if( cachedProperty == null )
                    {
                        cachedProperty = new ExtensionProperty(propertyId, "");
                    } else {
                        cachedProperty.aValue = "";
                    }
                    
                    cachedProperties.put(propertyId, cachedProperty);
                }
            }
            else
            {
                // not explicit requested property
                if( valueToUse == null || valueToUse.equals("") )
                {
                    // remove
                    if(cachedProperty != null)
                    {
                        cachedProperties.remove(propertyId);
                    }
                }
            }
        }
        
        m_cachedProviderLocationProperty.aExtensions.theValue (getCachedExtensionProperties());
    }    

    /**
     * Method setForProperty.
     * @param isRequested
     * @param addressOptToSet
     * @param valueToUse
     */
    private void setForProperty(
        boolean isRequested,
        AddressOpt addressOptToSet,
        Address valueToUse)
    {
        if (isRequested)
        {
            if (valueToUse != null)
            {
                // set or overwrite existing value
                addressOptToSet.theValue(valueToUse);
            }
            else if (!isRequestedALL())
            {
                // value to use is null and property explicitly requested
                // reset
                addressOptToSet.theValue(null);
                addressOptToSet.__default();
            }
        }
    }

    /**
     * Method setForCachedProperty.
     * @param addressOptToSet
     * @param valueToUse
     */
    private void setForCachedProperty(
        AddressOpt addressOptToSet,
        Address valueToUse)
    {
        if (valueToUse != null)
        {
            // set or overwrite existing value
            addressOptToSet.theValue(valueToUse);
        }
        else
        {
            // value to use is null and property explicitly requested
            // reset
            addressOptToSet.theValue(null);
            addressOptToSet.__default();
        }
    }
    
    /* ======================================================= *
     * Set methods for setting the location properties
     * ======================================================= */

    public void setPostalAddress(Address value)
    {
        setForProperty(
            getLocationPropertiesRequested().isRetrievePostalAddress(),
            getProviderLocationProperty().aPostalAddress,
            value);
            
        setForCachedProperty(
            getCachedProviderLocationProperty().aPostalAddress,
            value);
    }
    
    public void setPostalAddress(AddressOpt value)
    {
        if (getLocationPropertiesRequested().isRetrievePostalAddress())
        {
            if (value != null)
            {
                getProviderLocationProperty().aPostalAddress = value;
            }
            else if (!getLocationPropertiesRequested().isRetrieveALL())
            {
                getProviderLocationProperty().aPostalAddress.theValue(null);
                getProviderLocationProperty().aPostalAddress.__default();
            }
        }    
    }
    
    public void setServiceAddress(Address value)
    {
        setForProperty(
            getLocationPropertiesRequested().isRetrieveServiceAddress(),
            getProviderLocationProperty().aServiceAddress,
            value);
            
        setForCachedProperty(
            getCachedProviderLocationProperty().aServiceAddress,
            value);
    }

    public void setServiceAddress(AddressOpt value)
    {
        if (getLocationPropertiesRequested().isRetrieveServiceAddress())
        {
            if (value != null)
            {
                getProviderLocationProperty().aServiceAddress = value;
            }
            else if (!getLocationPropertiesRequested().isRetrieveALL())
            {
                getProviderLocationProperty().aServiceAddress.theValue(null);
                getProviderLocationProperty().aServiceAddress.__default();
            }
        }
    }

    public void setE911Address(Address value)
    {
        setForProperty(
            getLocationPropertiesRequested()
                .isRetrieveE911Address(),
            getProviderLocationProperty().aE911Address,
            value);
            
       setForCachedProperty(
            getCachedProviderLocationProperty().aE911Address,
            value);
    }

    public void setE911Address(AddressOpt value)
    {
        if (getLocationPropertiesRequested()
            .isRetrieveE911Address())
        {
            if (value != null)
            {
                getProviderLocationProperty().aE911Address =
                    value;
            }
            else if (!getLocationPropertiesRequested().isRetrieveALL())
            {
                getProviderLocationProperty().aE911Address.theValue(null);
                getProviderLocationProperty()
                    .aE911Address.theValue(new Address());
                getProviderLocationProperty()
                    .aE911Address.theValue().aFieldedAddress(
                        LIMIDLUtil.instantiateNewInitializedFieldedAddress());   
            }
        }
    }

    public void setSwitchSuperPopAddress(Address value)
    {
        setForProperty(
            getLocationPropertiesRequested()
                .isRetrieveSwitchSuperPopAddress(),
            getProviderLocationProperty().aSwitchSuperPopAddress,
            value);
            
       setForCachedProperty(
            getCachedProviderLocationProperty().aSwitchSuperPopAddress,
            value);
    }

    public void setSwitchSuperPopAddress(AddressOpt value)
    {
        if (getLocationPropertiesRequested()
            .isRetrieveSwitchSuperPopAddress())
        {
            if (value != null)
            {
                getProviderLocationProperty().aSwitchSuperPopAddress =
                    value;
            }
            else if (!getLocationPropertiesRequested().isRetrieveALL())
            {
                getProviderLocationProperty().aSwitchSuperPopAddress.theValue(null);
                getProviderLocationProperty()
                    .aSwitchSuperPopAddress.theValue(new Address());
                getProviderLocationProperty()
                    .aSwitchSuperPopAddress.theValue().aFieldedAddress(
                        LIMIDLUtil.instantiateNewInitializedFieldedAddress());
                    
            }
        }
    }
    
    public void setProviderName (String value)
    {
        if (value != null)
        {
            getProviderLocationProperty().aProviderName.theValue (value);
            getCachedProviderLocationProperty().aProviderName.theValue (value);
        }
        else
        {
            getProviderLocationProperty().aProviderName.__default();
            getCachedProviderLocationProperty().aProviderName.__default();
        }

    }

    public void setEktId(String value)
    {
        setForProperty(
            getLocationPropertiesRequested().isRetrieveEktId(),
            getProviderLocationProperty().aEcktId,
            value);
            
        setForCachedProperty(
            getCachedProviderLocationProperty().aEcktId,
            value);

    }

    public void setCustomerPremiseIndicator(String value)
    {
        setForProperty(
            getLocationPropertiesRequested().isRetrieveCustomerPremiseIndicator(),
            getProviderLocationProperty().aCustomerPremiseIndicator,
            value);
            
        setForCachedProperty(
            getCachedProviderLocationProperty().aCustomerPremiseIndicator,
            value);

    }

    /**
     * Method setAddressMatchCode.
     * @param value
     */
    private void setAddressMatchCode(String value)
    {
        setForProperty(
            getLocationPropertiesRequested().isRetrieveAddressMatchCode(),
            getProviderLocationProperty().aAddressMatchCode,
            value);
            
        setForCachedProperty(
            getCachedProviderLocationProperty().aAddressMatchCode,
            value);
    }

    /**
     * Method setAddressMatchCode sets the AMQ code.  This method
     * is different than the other set methods because in certain 
     * cased the AMQ code is always returned whether the client
     * requested it or not.  One case is when the response is a List
     * from OvalsUSPS.
     * @param value
     * @param shouldAlwaysPopulateWhetherOrNotIsRequested
     */
    public void setAddressMatchCode(
        String value,
        boolean shouldAlwaysPopulateWhetherOrNotIsRequested)
    {
        if (shouldAlwaysPopulateWhetherOrNotIsRequested)
        {
            if (value != null && value.length() > 0)
            {
                getProviderLocationProperty().aAddressMatchCode.theValue(value);
            }
            else
            {
                getProviderLocationProperty().aAddressMatchCode.theValue("");
            }
        }
        else
        {
            setAddressMatchCode(value);
        }
    }

    /**
     * Method setAddressMatchCodeDescription.
     * @param value
     */
    private void setAddressMatchCodeDescription(String value)
    {
        setForProperty(
            getLocationPropertiesRequested()
                .isRetrieveAddressMatchCodeDescription(),
            getProviderLocationProperty().aAddressMatchCodeDescription,
            value);
            
        setForCachedProperty(
            getCachedProviderLocationProperty().aAddressMatchCodeDescription,
            value);

    }

    /**
     * Method setAddressMatchCodeDescription sets the AMQ description.
     * This method is different than the other set methods because
     * in certain cased the AMQ description is always returned whether the
     * client requested it or not.  One case is when the response is a List
     * from OvalsUSPS.
     * @param value
     * @param shouldAlwaysPopulateWhetherOrNotIsRequested
     */
    public void setAddressMatchCodeDescription(
        String value,
        boolean shouldAlwaysPopulateWhetherOrNotIsRequested)
    {
        if (shouldAlwaysPopulateWhetherOrNotIsRequested)
        {
            if (value != null && value.length() > 0)
            {
                getProviderLocationProperty().aAddressMatchCodeDescription.theValue(value);
            }
            else
            {
                getProviderLocationProperty().aAddressMatchCodeDescription.theValue("");
            }
        }
        else
        {
            setAddressMatchCodeDescription(value);
        }
    }

    public void setCentralOfficeCode(String value)
    {
        setForProperty(
            getLocationPropertiesRequested().isRetrieveCentralOfficeCode(),
            getProviderLocationProperty().aCentralOfficeCode,
            value);
            
        setForCachedProperty(
            getCachedProviderLocationProperty().aCentralOfficeCode,
            value);

    }

    public void setCentralOfficeType(String value)
    {
        setForProperty(
            getLocationPropertiesRequested().isRetrieveCentralOfficeType(),
            getProviderLocationProperty().aCentralOfficeType,
            value);
            
        setForCachedProperty(
            getCachedProviderLocationProperty().aCentralOfficeType,
            value);

    }
    
    public void setCentralOfficeType(String value, boolean shouldAlwaysPopulateWhetherOrNotIsRequested)
    {
        if (shouldAlwaysPopulateWhetherOrNotIsRequested)
        {
            if (value != null && value.length() > 0)
            {
                getProviderLocationProperty().aCentralOfficeType.theValue(value);
            }
            else
            {
                getProviderLocationProperty().aCentralOfficeType.theValue("");
            }
        }
        else
        {
            setCentralOfficeType(value);
        }
    }
    
    public void setCityStatePostalCodeValid(String value)
    {
        setForProperty(
            !getLocationPropertiesRequested().isRetrieveALL()
                && getLocationPropertiesRequested().isRetrieveCityStatePostalCodeValid(),
            getProviderLocationProperty().aCityStatePostalCodeValid,
            value);

        setForCachedProperty(
            getCachedProviderLocationProperty().aCityStatePostalCodeValid,
            value);
    }
    
    public void setCommunityName(String value)
    {
        setForProperty(
            getLocationPropertiesRequested().isRetrieveCommunityName(),
            getProviderLocationProperty().aCommunityName,
            value);
            
        setForCachedProperty(
            getCachedProviderLocationProperty().aCommunityName,
            value);

    }

    public void setCountyId(String value)
    {
        setForProperty(
            getLocationPropertiesRequested().isRetrieveCountyId(),
            getProviderLocationProperty().aCountyId,
            value);
            
        setForCachedProperty(
            getCachedProviderLocationProperty().aCountyId,
            value);

    }
    
    /**
     * Method setAddressMatchCode sets the County ID.  This method
     * is different than the other set methods because in certain 
     * cases the County ID is always returned whether the client
     * requested it or not.  One case is when the response is 
     * from OVALS NSP for E911 address validation.
     * @param value
     * @param shouldAlwaysPopulateWhetherOrNotIsRequested
     */
    public void setCountyId(
        String value,
        boolean shouldAlwaysPopulateWhetherOrNotIsRequested)
    {
        if (shouldAlwaysPopulateWhetherOrNotIsRequested)
        {
            if (value != null && value.length() > 0)
            {
                getProviderLocationProperty().aCountyId.theValue(value);
            }
            else
            {
                getProviderLocationProperty().aCountyId.theValue("");
            }
        }
        else
        {
            setCountyId(value);
        }
    }
    

    public void setCoSwitchSuperPop(String value)
    {
        setForProperty(
            getLocationPropertiesRequested().isRetrieveCoSwitchSuperPop(),
            getProviderLocationProperty().aCoSwitchSuperPop,
            value);
            
        setForCachedProperty(
            getCachedProviderLocationProperty().aCoSwitchSuperPop,
            value);


    }

    public void setDomSwitchPop(String value)
    {
        setForProperty(
            getLocationPropertiesRequested().isRetrieveDomSwitchPop(),
            getProviderLocationProperty().aDomSwitchPop,
            value);
            
        setForCachedProperty(
            getCachedProviderLocationProperty().aDomSwitchPop,
            value);

    }

    public void setE911Exempt(String value)
    {
        setForProperty(
            getLocationPropertiesRequested().isRetrieveE911Exempt(),
            getProviderLocationProperty().aE911Exempt,
            value);
            
        setForCachedProperty(
            getCachedProviderLocationProperty().aE911Exempt,
            value);

    }

    public void setE911NonRecurringCharge(String value)
    {
        setForProperty(
            getLocationPropertiesRequested()
                .isRetrieveE911NonRecurringCharge(),
            getProviderLocationProperty().aE911NonRecurringCharge,
            value);
                        
        setForCachedProperty(
            getCachedProviderLocationProperty().aE911NonRecurringCharge,
            value);


    }

    public void setE911Surcharge(String value)
    {
        setForProperty(
            getLocationPropertiesRequested().isRetrieveE911Surcharge(),
            getProviderLocationProperty().aE911Surcharge,
            value);
            
        setForCachedProperty(
            getCachedProviderLocationProperty().aE911Surcharge,
            value);

    }

    public void setExchangeCode(String value)
    {
        setForProperty(
            getLocationPropertiesRequested().isRetrieveExchangeCode(),
            getProviderLocationProperty().aExchangeCode,
            value);
            
        setForCachedProperty(
            getCachedProviderLocationProperty().aExchangeCode,
            value);

    }
    
    /**
     * Method setExchangeCode sets the ExchangeCode.  
     * This method is different than the other set methods because in certain 
     * cases the Latitude is always returned whether the client
     * requested it or not.  One case is when the response is 
     * from OVALS NSP for E911 address validation.
     * @param value
     * @param shouldAlwaysPopulateWhetherOrNotIsRequested
     */
    public void setExchangeCode(
        String value,
        boolean shouldAlwaysPopulateWhetherOrNotIsRequested)
    {
        if (shouldAlwaysPopulateWhetherOrNotIsRequested)
        {
            if (value != null && value.length() > 0)
            {
                getProviderLocationProperty().aExchangeCode.theValue(value);
            }
            else
            {
                getProviderLocationProperty().aExchangeCode.theValue("");
            }
        }
        else
        {
            setExchangeCode(value);
        }
    }            

    public void setExco(String value)
    {
        setForProperty(
            getLocationPropertiesRequested().isRetrieveExco(),
            getProviderLocationProperty().aExco,
            value);
            
        setForCachedProperty(
            getCachedProviderLocationProperty().aExco,
            value);

    }

    public void setGeoCode(String value)
    {
        setForProperty(
            getLocationPropertiesRequested().isRetrieveGeoCode(),
            getProviderLocationProperty().aGeoCode,
            value);
            
        setForCachedProperty(
            getCachedProviderLocationProperty().aGeoCode,
            value);

    }

    public void setHorizontalCoordinate(String value)
    {
        setForProperty(
            getLocationPropertiesRequested()
                .isRetrieveHorizontalCoordinate(),
            getProviderLocationProperty().aHorizontalCoordinate,
            value);
                        
        setForCachedProperty(
            getCachedProviderLocationProperty().aHorizontalCoordinate,
            value);

    }

 	public void setHorizontalCoordinate(
        String value,
        boolean shouldAlwaysPopulateWhetherOrNotIsRequested)
    {
        if (shouldAlwaysPopulateWhetherOrNotIsRequested)
        {
            if (value != null && value.length() > 0)
            {
                getProviderLocationProperty().aHorizontalCoordinate.theValue(value);
            }
            else
            {
                getProviderLocationProperty().aHorizontalCoordinate.theValue("");
            }
        }
        else
        {
            setHorizontalCoordinate(value);
        }
    }

    public void setLataCode(String value)
    {
        setForProperty(
            getLocationPropertiesRequested().isRetrieveLataCode(),
            getProviderLocationProperty().aLataCode,
            value);
            
        setForCachedProperty(
            getCachedProviderLocationProperty().aLataCode,
            value);

    }

    public void setLataName(String value)
    {
        setForProperty(
            getLocationPropertiesRequested().isRetrieveLataName(),
            getProviderLocationProperty().aLataName,
            value);
            
        setForCachedProperty(
            getCachedProviderLocationProperty().aLataName,
            value);

    }

  /*  public void setLatitudeLongitude(String value)
    {
        setForProperty(
            getLocationPropertiesRequested().isRetrieveLatitudeLongitude(),
            getProviderLocationProperty().aLatitudeLongitude,
            value);
            
        setForCachedProperty(
            getCachedProviderLocationProperty().aLatitudeLongitude,
            value);
 
    }*/
    
    public void setLatitude(String value)
    {
        setForProperty(
            getLocationPropertiesRequested().isRetrieveLatitude(),
            getProviderLocationProperty().aLatitude,
            value);
            
        setForCachedProperty(
            getCachedProviderLocationProperty().aLatitude,
            value);
 
    }
    
    /**
     * Method setLatitude sets the Latitude.  
     * This method is different than the other set methods because in certain 
     * cases the Latitude is always returned whether the client
     * requested it or not.  One case is when the response is 
     * from OVALS NSP for E911 address validation.
     * @param value
     * @param shouldAlwaysPopulateWhetherOrNotIsRequested
     */
    public void setLatitude(
        String value,
        boolean shouldAlwaysPopulateWhetherOrNotIsRequested)
    {
        if (shouldAlwaysPopulateWhetherOrNotIsRequested)
        {
            if (value != null && value.length() > 0)
            {
                getProviderLocationProperty().aLatitude.theValue(value);
            }
            else
            {
                getProviderLocationProperty().aLatitude.theValue("");
            }
        }
        else
        {
            setLatitude(value);
        }
    }        
    
    public void setLongitude(String value)
    {
        setForProperty(
            getLocationPropertiesRequested().isRetrieveLongitude(),
            getProviderLocationProperty().aLongitude,
            value);
            
        setForCachedProperty(
            getCachedProviderLocationProperty().aLongitude,
            value);
 
    }
    
    /**
     * Method setLongitude sets the Latitude.  
     * This method is different than the other set methods because in certain 
     * cases the Longitude is always returned whether the client
     * requested it or not.  One case is when the response is 
     * from OVALS NSP for E911 address validation.
     * @param value
     * @param shouldAlwaysPopulateWhetherOrNotIsRequested
     */
    public void setLongitude(
        String value,
        boolean shouldAlwaysPopulateWhetherOrNotIsRequested)
    {
        if (shouldAlwaysPopulateWhetherOrNotIsRequested)
        {
            if (value != null && value.length() > 0)
            {
                getProviderLocationProperty().aLongitude.theValue(value);
            }
            else
            {
                getProviderLocationProperty().aLongitude.theValue("");
            }
        }
        else
        {
            setLongitude(value);
        }
    }            

    public void setLocalProviderExchangeCode(String value)
    {
        setForProperty(
            getLocationPropertiesRequested()
                .isRetrieveLocalProviderExchangeCode(),
            getProviderLocationProperty().aLocalProviderExchangeCode,
            value);
                        
        setForCachedProperty(
            getCachedProviderLocationProperty().aLocalProviderExchangeCode,
            value);

    }

    public void setLocalProviderName(String value)
    {
        setForProperty(
            getLocationPropertiesRequested().isRetrieveLocalProviderName(),
            getProviderLocationProperty().aLocalProviderName,
            value);
            
        setForCachedProperty(
            getCachedProviderLocationProperty().aLocalProviderName,
            value);

    }

    public void setLocalProviderNumber(String value)
    {
        setForProperty(
            getLocationPropertiesRequested()
                .isRetrieveLocalProviderNumber(),
            getProviderLocationProperty().aLocalProviderNumber,
            value);
                        
        setForCachedProperty(
            getCachedProviderLocationProperty().aLocalProviderNumber,
            value);

    }

    public void setLocalProviderServingOfficeCode(String value)
    {
        setForProperty(
            getLocationPropertiesRequested()
                .isRetrieveLocalProviderServingOfficeCode(),
            getProviderLocationProperty().aLocalProviderServingOfficeCode,
            value);
                        
        setForCachedProperty(
            getCachedProviderLocationProperty().aLocalProviderServingOfficeCode,
            value);

    }

    public void setLocalProviderAbbreviatedName(String value)
    {
        setForProperty(
            getLocationPropertiesRequested()
                .isRetrieveLocalProviderAbbreviatedName(),
            getProviderLocationProperty().aLocalProviderAbbreviatedName,
            value);
                        
        setForCachedProperty(
            getCachedProviderLocationProperty().aLocalProviderAbbreviatedName,
            value);

    }

    public void setMailingBarCodeSuffix(String value)
    {
        setForProperty(
            getLocationPropertiesRequested()
                .isRetrieveMailingBarCodeSuffix(),
            getProviderLocationProperty().aMailingBarCodeSuffix,
            value);
                        
        setForCachedProperty(
            getCachedProviderLocationProperty().aMailingBarCodeSuffix,
            value);
 
    }

    public void setMsaCode(String value)
    {
        setForProperty(
            getLocationPropertiesRequested().isRetrieveMsaCode(),
            getProviderLocationProperty().aMsaCode,
            value);
            
        setForCachedProperty(
            getCachedProviderLocationProperty().aMsaCode,
            value);

    }

    public void setMsaName(String value)
    {
        setForProperty(
            getLocationPropertiesRequested().isRetrieveMsaName(),
            getProviderLocationProperty().aMsaName,
            value);
            
        setForCachedProperty(
            getCachedProviderLocationProperty().aMsaName,
            value);

    }

    public void setNearestDistanceColoToCo(String value)
    {
        setForProperty(
            getLocationPropertiesRequested()
                .isRetrieveNearestDistanceColoToCo(),
            getProviderLocationProperty().aNearestDistanceColoToCo,
            value);
                        
        setForCachedProperty(
            getCachedProviderLocationProperty().aNearestDistanceColoToCo,
            value);

    }

    public void setNearestDistanceSuperPopToCo(String value)
    {
        setForProperty(
            getLocationPropertiesRequested()
                .isRetrieveNearestDistanceSuperPopToCo(),
            getProviderLocationProperty().aNearestDistanceSuperPopToCo,
            value);
                        
        setForCachedProperty(
            getCachedProviderLocationProperty().aNearestDistanceSuperPopToCo,
            value);

    }

    public void setNearestSbcColo(String value)
    {
        setForProperty(
            getLocationPropertiesRequested().isRetrieveNearestSbcColo(),
            getProviderLocationProperty().aNearestSbcColo,
            value);
            
        setForCachedProperty(
            getCachedProviderLocationProperty().aNearestSbcColo,
            value);

    }

    public void setNearestSbcCoSuperPop(String value)
    {
        setForProperty(
            getLocationPropertiesRequested()
                .isRetrieveNearestSbcCoSuperPop(),
            getProviderLocationProperty().aNearestSbcCoSuperPop,
            value);
                        
        setForCachedProperty(
            getCachedProviderLocationProperty().aNearestSbcCoSuperPop,
            value);
 
    }

    public void setNearestSbcCoWirecenter(String value)
    {
        setForProperty(
            getLocationPropertiesRequested()
                .isRetrieveNearestSbcCoWirecenter(),
            getProviderLocationProperty().aNearestSbcCoWirecenter,
            value);
                        
        setForCachedProperty(
            getCachedProviderLocationProperty().aNearestSbcCoWirecenter,
            value);

    }

    public void setOwnedWiring(String value)
    {
        setForProperty(
            getLocationPropertiesRequested().isRetrieveOwnedWiring(),
            getProviderLocationProperty().aOwnedWiring,
            value);
            
        setForCachedProperty(
            getCachedProviderLocationProperty().aOwnedWiring,
            value);

    }

    public void setPostalCarrierCode(String value)
    {
        setForProperty(
            getLocationPropertiesRequested().isRetrievePostalCarrierCode(),
            getProviderLocationProperty().aPostalCarrierCode,
            value);
            
        setForCachedProperty(
            getCachedProviderLocationProperty().aPostalCarrierCode,
            value);

    }

    public void setPrimaryDirectoryCode(String value)
    {
        setForProperty(
            getLocationPropertiesRequested()
                .isRetrievePrimaryDirectoryCode(),
            getProviderLocationProperty().aPrimaryDirectoryCode,
            value);
                        
        setForCachedProperty(
            getCachedProviderLocationProperty().aPrimaryDirectoryCode,
            value);

    }

    public void setPrimaryNpaNxx(String value)
    {
        setForProperty(
            getLocationPropertiesRequested().isRetrievePrimaryNpaNxx(),
            getProviderLocationProperty().aPrimaryNpaNxx,
            value);
            
        setForCachedProperty(
            getCachedProviderLocationProperty().aPrimaryNpaNxx,
            value);

    }
    
    /**
     * Method setPublicSafetyAnsweringPointId sets the PublicSafetyAnsweringPointId.  
     * This method is different than the other set methods because in certain 
     * cases the PublicSafetyAnsweringPointId is always returned whether the client
     * requested it or not.  One case is when the response is 
     * from OVALS NSP for E911 address validation.
     * @param value
     * @param shouldAlwaysPopulateWhetherOrNotIsRequested
     */
    public void setPublicSafetyAnsweringPointId(
        String value,
        boolean shouldAlwaysPopulateWhetherOrNotIsRequested)
    {
        if (shouldAlwaysPopulateWhetherOrNotIsRequested)
        {
            if (value != null && value.length() > 0)
            {
                getProviderLocationProperty().aPublicSafetyAnsweringPointId.theValue(value);
            }
            else
            {
                getProviderLocationProperty().aPublicSafetyAnsweringPointId.theValue("");
            }
        }
        else
        {
            setPublicSafetyAnsweringPointId(value);
        }
    }    
    
    public void setPublicSafetyAnsweringPointId(String value)
    {
        setForProperty(
            getLocationPropertiesRequested().isRetrievePublicSafetyAnsweringPointId(),
            getProviderLocationProperty().aPublicSafetyAnsweringPointId,
            value);
            
        setForCachedProperty(
            getCachedProviderLocationProperty().aPublicSafetyAnsweringPointId,
            value);

    }
    

    public void setQuickDialTone(String value)
    {
        setForProperty(
            getLocationPropertiesRequested().isRetrieveQuickDialTone(),
            getProviderLocationProperty().aQuickDialTone,
            value);
            
        setForCachedProperty(
            getCachedProviderLocationProperty().aQuickDialTone,
            value);
 
    }

    public void setQuickDialToneNumber(String value)
    {
        setForProperty(
            getLocationPropertiesRequested()
                .isRetrieveQuickDialToneNumber(),
            getProviderLocationProperty().aQuickDialToneNumber,
            value);
                        
        setForCachedProperty(
           getCachedProviderLocationProperty().aQuickDialToneNumber,
            value);

    }

    public void setRateCenterCode(String value)
    {
        setForProperty(
            getLocationPropertiesRequested().isRetrieveRateCenterCode(),
            getProviderLocationProperty().aRateCenterCode,
            value);
            
        setForCachedProperty(
            getCachedProviderLocationProperty().aRateCenterCode,
            value);
 
    }

    public void setRateZone(String value)
    {
        setForProperty(
            getLocationPropertiesRequested().isRetrieveRateZone(),
            getProviderLocationProperty().aRateZone,
            value);
            
        setForCachedProperty(
            getCachedProviderLocationProperty().aRateZone,
            value);

    }

    public void setRateZoneBandCode(String value)
    {
        setForProperty(
            getLocationPropertiesRequested().isRetrieveRateZoneBandCode(),
            getProviderLocationProperty().aRateZoneBandCode,
            value);
            
        setForCachedProperty(
           getCachedProviderLocationProperty().aRateZoneBandCode,
            value);

    }

    public void setSagNpa(String value)
    {
        setForProperty(
            getLocationPropertiesRequested().isRetrieveSagNpa(),
            getProviderLocationProperty().aSagNpa,
            value);
            
        setForCachedProperty(
            getCachedProviderLocationProperty().aSagNpa,
            value);
 
    }

    public void setSagWireCenter(String value)
    {
        setForProperty(
            getLocationPropertiesRequested().isRetrieveSagWireCenter(),
            getProviderLocationProperty().aSagWireCenter,
            value);
            
        setForCachedProperty(
           getCachedProviderLocationProperty().aSagWireCenter,
            value);

    }

    public void setSbcColoLsoCode(String value)
    {
        setForProperty(
            getLocationPropertiesRequested().isRetrieveSbcColoLsoCode(),
            getProviderLocationProperty().aSbcColoLsoCode,
            value);
            
        setForCachedProperty(
            getCachedProviderLocationProperty().aSbcColoLsoCode,
            value);
 
    }

    public void setSbcColoWirecenter(String value)
    {
        setForProperty(
            getLocationPropertiesRequested().isRetrieveSbcColoWirecenter(),
            getProviderLocationProperty().aSbcColoWirecenter,
            value);
            
        setForCachedProperty(
            getCachedProviderLocationProperty().aSbcColoWirecenter,
            value);
 
    }

    public void setSbcServingOfficeCode(String value)
    {
        setForProperty(
            getLocationPropertiesRequested()
                .isRetrieveSbcServingOfficeCode(),
            getProviderLocationProperty().aSbcServingOfficeCode,
            value);
                        
        setForCachedProperty(
           getCachedProviderLocationProperty().aSbcServingOfficeCode,
            value);

    }

    public void setSbcServingOfficeWirecenter(String value)
    {
        setForProperty(
            getLocationPropertiesRequested()
                .isRetrieveSbcServingOfficeWirecenter(),
            getProviderLocationProperty().aSbcServingOfficeWirecenter,
            value);
                       
        setForCachedProperty(
            getCachedProviderLocationProperty().aSbcServingOfficeWirecenter,
            value);

    }

    public void setServingAreaDescription(String value)
    {
        setForProperty(
            getLocationPropertiesRequested()
                .isRetrieveServingAreaDescription(),
            getProviderLocationProperty().aServingAreaDescription,
            value);
                        
        setForCachedProperty(
            getCachedProviderLocationProperty().aServingAreaDescription,
            value);

    }

    public void setStreetAddressGuideArea(String value)
    {
        setForProperty(
            getLocationPropertiesRequested()
                .isRetrieveStreetAddressGuideArea(),
            getProviderLocationProperty().aStreetAddressGuideArea,
            value);
                        
        setForCachedProperty(
            getCachedProviderLocationProperty().aStreetAddressGuideArea,
            value);
 
    }

    public void setSurcharge16Percent(String value)
    {
        setForProperty(
            getLocationPropertiesRequested().isRetrieveSurcharge16Percent(),
            getProviderLocationProperty().aSurcharge16Percent,
            value);
            
        setForCachedProperty(
            getCachedProviderLocationProperty().aSurcharge16Percent,
            value);
 
    }

    public void setSurcharge4Percent(String value)
    {
        setForProperty(
            getLocationPropertiesRequested().isRetrieveSurcharge4Percent(),
            getProviderLocationProperty().aSurcharge4Percent,
            value);
            
        setForCachedProperty(
            getCachedProviderLocationProperty().aSurcharge4Percent,
            value);


    }

    public void setSwitchDataSuperPop(String value)
    {
        setForProperty(
            getLocationPropertiesRequested().isRetrieveSwitchDataSuperPop(),
            getProviderLocationProperty().aSwitchDataSuperPop,
            value);
            
        setForCachedProperty(
            getCachedProviderLocationProperty().aSwitchDataSuperPop,
            value);



    }

    public void setSwitchVoiceSuperPop(String value)
    {
        setForProperty(
        	getLocationPropertiesRequested()
                .isRetrieveSwitchVoiceSuperPop(),
            getProviderLocationProperty().aSwitchVoiceSuperPop,
            value);
                        
        setForCachedProperty(
            getCachedProviderLocationProperty().aSwitchVoiceSuperPop,
            value);

    }

    public void setTarCode(String value)
    {
        setForProperty(
            getLocationPropertiesRequested().isRetrieveTarCode(),
            getProviderLocationProperty().aTarCode,
            value);
            
        setForCachedProperty(
            getCachedProviderLocationProperty().aTarCode,
            value);


    }
    
    /**
     * Method setTarCode sets the TAR code.  This method
     * is different than the other set methods because in certain 
     * cases the TAR code is always returned whether the client
     * requested it or not.  One case is when the response is from OVALS NSP
     * for E911 validation.
     * @param value
     * @param shouldAlwaysPopulateWhetherOrNotIsRequested
     */
    public void setTarCode(
        String value,
        boolean shouldAlwaysPopulateWhetherOrNotIsRequested)
    {
        if (shouldAlwaysPopulateWhetherOrNotIsRequested)
        {
            if (value != null && value.length() > 0)
            {
                getProviderLocationProperty().aTarCode.theValue(value);
            }
            else
            {
                getProviderLocationProperty().aTarCode.theValue("");
            }
        }
        else
        {
            setTarCode(value);
        }
    }
    

    public void setTelephoneNumber(String value)
    {
        setForProperty(
            getLocationPropertiesRequested().isRetrieveTelephoneNumber(),
            getProviderLocationProperty().aTelephoneNumber,
            value);
            
        setForCachedProperty(
            getCachedProviderLocationProperty().aTelephoneNumber,
            value);


    }

    public void setVerticalCoordinate(String value)
    {
        setForProperty(
            getLocationPropertiesRequested().isRetrieveVerticalCoordinate(),
            getProviderLocationProperty().aVerticalCoordinate,
            value);
            
        setForCachedProperty(
            getCachedProviderLocationProperty().aVerticalCoordinate,
            value);
    }

 	public void setVerticalCoordinate(
        String value,
        boolean shouldAlwaysPopulateWhetherOrNotIsRequested)
    {
        if (shouldAlwaysPopulateWhetherOrNotIsRequested)
        {
            if (value != null && value.length() > 0)
            {
                getProviderLocationProperty().aVerticalCoordinate.theValue(value);
            }
            else
            {
                getProviderLocationProperty().aVerticalCoordinate.theValue("");
            }
        }
        else
        {
            setVerticalCoordinate(value);
        }
    }

    public void setWorkingServiceOnLocation(String value)
    {
        setForProperty(
            getLocationPropertiesRequested()
                .isRetrieveWorkingServiceOnLocation(),
            getProviderLocationProperty().aWorkingServiceOnLocation,
            value);
                        
        setForCachedProperty(
            getCachedProviderLocationProperty().aWorkingServiceOnLocation,
            value);
    }
    
}
