//$Id: ServiceLocationBuilder.java,v 1.2 2007/10/11 21:39:53 gg2712 Exp $

package com.sbc.eia.bis.lim.transactions.common;

import java.util.Properties;

import com.sbc.bccs.idl.helpers.IDLUtil;
import com.sbc.eia.bis.lim.util.LIMBase;
import com.sbc.eia.idl.lim_types.Address2;
import com.sbc.eia.idl.lim_types.AddressOpt;
import com.sbc.eia.idl.lim_types.ExtensionProperty;
import com.sbc.eia.idl.lim_types.ExtensionPropertySeqOpt;
import com.sbc.eia.idl.lim_types.ServiceLocation;
import com.sbc.eia.idl.types.EiaDate;
import com.sbc.eia.idl.types.EiaDateOpt;
import com.sbc.eia.idl.types.StringOpt;

/**
 * /** Description
 *  Mimin this class from ProviderLocationPropertyBuilder.java. The ServiceLocationBuilder builds a ServiceLocation
 *  based on the properties the client has asked for.  The
 *  ServiceLocation is initially defaulted in the following manner:
 *      Non-optional fields are set to an appropriate default value.  For
 *      Strings, the default is an empty string ("").
 * 
 *      Optional fields are assigned new instance of the optional type,
 *      and the instance's __default method is called.
 * 
 *  The ServiceLocationBuilder then initializes the 
 *  ServiceLocation based on the requested properties.  Each
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
 * @author jh9785
 *
 */
public class ServiceLocationBuilder
{
    private RetrieveLocReq.LocationPropertiesRequested m_locationPropertiesRequested = null;
    private ServiceLocation m_serviceLocation = null;
    private ServiceLocation m_cachedServiceLocation = null;
    private Properties properties = new Properties();
    private Properties cachedProperties = new Properties();
    
    /**
     * Constructor for ServiceLocationBuilder.
     * @param request RetrieveLocReq.LocationPropertiesRequested
     */
    public ServiceLocationBuilder(RetrieveLocReq.LocationPropertiesRequested request) 
    {
        super();
        setLocationPropertiesRequested(request);
        initializeServiceLocation();
    }
    
    /**
     * Set local class variable m_locationPropertiesRequested.
     * @param locationPropertiesRequested LocationPropertiesRequested
     */
    private void setLocationPropertiesRequested(
            RetrieveLocReq
                .LocationPropertiesRequested locationPropertiesRequested)
    {
        m_locationPropertiesRequested = locationPropertiesRequested;
    }
    
    /**
     * Get local class variable m_locationPropertiesRequested.
     */
    private RetrieveLocReq
    	.LocationPropertiesRequested getLocationPropertiesRequested()
    {
        return m_locationPropertiesRequested;
    }
    
    /**
     * Set local class variable m_serviceLocation.
     * @param m_serviceLocation ServiceLocation
     */
    public void setServiceLocation(ServiceLocation serviceLocation)
    {
        m_serviceLocation = serviceLocation;
    }
    
    /**
     * Get local class variable m_serviceLocation.
     */
    public ServiceLocation getServiceLocation()
    {
        return m_serviceLocation;
    }
    
    /**
     * Set local class variable m_serviceLocation.
     * @param cachedServiceLocation ServiceLocation
     */
    public void setCachedServiceLocation(ServiceLocation cachedServiceLocation)
    {
        m_cachedServiceLocation = cachedServiceLocation;
    }
    
    /**
     * Get local class variable m_cachedServiceLocation.
     */
    public ServiceLocation getCachedServiceLocation()
    {
        return m_cachedServiceLocation;
    }
    
    /**
     * Initialize ServiceLocation
     */
    private void initializeServiceLocation()
    {
        setServiceLocation(getDefaultServiceLocation());
        setCachedServiceLocation(getDefaultServiceLocation());
        setCentralOfficeCode(null);
        setCommunityName(null);
        setE911Exempt(null);
        setE911NonRecurringCharge(null);
        setE911Surcharge(null);
        setExchangeCode(null);
        setExco(null);
        setLataCode(null);
        setLocalProviderServingOfficeCode(null);
        setOwnedWiring(null);
        setPrimaryDirectoryCode(null);
        setPrimaryNpaNxx(null);
        setQuickDialTone(null);
        setQuickDialToneNumber(null);
        setRateZone(null);
        setRateZoneBandCode(null);
        setSagWireCenter(null);
        setServingAreaDescription(null);
        setStreetAddressGuideArea(null);
        setSurcharge16Percent(null);
        setSurcharge4Percent(null);
        setTarCode(null);
        setTelephoneNumber(null);
        setWorkingServiceOnLocation(null);       
    }
    
    /**
     * Return a defaulted ServiceLocation
     * @return ServiceLocation
     */
    public static ServiceLocation getDefaultServiceLocation()
    {
        ServiceLocation loc_serviceLocation = new ServiceLocation();
        
        loc_serviceLocation.aServiceAddress = new Address2();
        loc_serviceLocation.aSAGAddress = new AddressOpt();
        loc_serviceLocation.aSAGAddress.__default();
        loc_serviceLocation.aAddressMatchCode = IDLUtil.toOpt((String)null);
        loc_serviceLocation.aAddressMatchCodeDescription = IDLUtil.toOpt((String)null);
        loc_serviceLocation.aBuildingType = IDLUtil.toOpt((String)null);
        loc_serviceLocation.aCentralOfficeCode = IDLUtil.toOpt((String)null) ;
        loc_serviceLocation.aCommunityName = IDLUtil.toOpt((String)null) ;
        loc_serviceLocation.aE911Exempt = IDLUtil.toOpt((String)null) ;
        loc_serviceLocation.aE911NonRecurringCharge = IDLUtil.toOpt((String)null) ;
        loc_serviceLocation.aE911Surcharge = IDLUtil.toOpt((String)null) ;
        loc_serviceLocation.aExchangeCode = IDLUtil.toOpt((String)null) ;
        loc_serviceLocation.aExco = IDLUtil.toOpt((String)null) ;
        loc_serviceLocation.aLataCode = IDLUtil.toOpt((String)null) ;
        loc_serviceLocation.aLegalEntity = IDLUtil.toOpt((String)null);
        loc_serviceLocation.aLocalProviderServingOfficeCode = IDLUtil.toOpt((String)null) ;
        loc_serviceLocation.aLocationType = IDLUtil.toOpt((String)null) ;
        loc_serviceLocation.aOwnedWiring = IDLUtil.toOpt((String)null) ;
        loc_serviceLocation.aPrimaryDirectoryCode = IDLUtil.toOpt((String)null) ;
        loc_serviceLocation.aPrimaryNpaNxx = IDLUtil.toOpt((String)null) ;
        loc_serviceLocation.aQuickDialTone = IDLUtil.toOpt((String)null) ;
        loc_serviceLocation.aQuickDialToneNumber = IDLUtil.toOpt((String)null) ;
        loc_serviceLocation.aRateZoneBandCode = IDLUtil.toOpt((String)null) ;
        loc_serviceLocation.aSagWireCenter = IDLUtil.toOpt((String)null) ;
        loc_serviceLocation.aServingAreaDescription = IDLUtil.toOpt((String)null) ;
        loc_serviceLocation.aServingNetworkType= IDLUtil.toOpt((String)null);
        loc_serviceLocation.aSmartmoves= IDLUtil.toOpt((String)null);
        loc_serviceLocation.aStreetAddressGuideArea = IDLUtil.toOpt((String)null) ;
        loc_serviceLocation.aSurcharge16Percent = IDLUtil.toOpt((String)null) ;
        loc_serviceLocation.aSurcharge4Percent = IDLUtil.toOpt((String)null) ;
        loc_serviceLocation.aTarCode = IDLUtil.toOpt((String)null) ;
        loc_serviceLocation.aTelephoneNumber = IDLUtil.toOpt((String)null) ;     
        loc_serviceLocation.aVideoHubOffice = IDLUtil.toOpt((String)null);
        loc_serviceLocation.aWorkingServiceOnLocation = IDLUtil.toOpt((String)null) ;
        loc_serviceLocation.aRateZone = IDLUtil.toOpt((String)null);
        loc_serviceLocation.aCrossBoundaryState = IDLUtil.toOpt((String)null);
        loc_serviceLocation.aIndependentCompanyIndicator = IDLUtil.toOpt((String)null);
        loc_serviceLocation.aAreaTransferCutDate = new EiaDateOpt();
        loc_serviceLocation.aAreaTransferCutDate.__default();
        loc_serviceLocation.aAreaTransferNumberChangeDate = new EiaDateOpt();
        loc_serviceLocation.aAreaTransferNumberChangeDate.__default();
        loc_serviceLocation.aAreaTransferNpaNxx = IDLUtil.toOpt((String)null);
        loc_serviceLocation.aAreaTransferWireCenter = IDLUtil.toOpt((String)null);
        loc_serviceLocation.aWireCenter = IDLUtil.toOpt((String)null);
        loc_serviceLocation.aConnectThrough = IDLUtil.toOpt((String)null);
        loc_serviceLocation.aExtensions = new ExtensionPropertySeqOpt ();
        loc_serviceLocation.aExtensions.__default();
        
        return loc_serviceLocation;
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
     * or not to set a Service Location Property based on what service location properties
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
     * Method setForEiaDate.  This method encompasses the logic for whether
     * or not to set a Service Location Property based on what service location properties
     * were asked for in the request.
     * @param isRequested indicates whether the property being set was
     * actually being requested
     * @param optToSet the EiaDateOpt value to set
     * @param valueToUse the EiaDate value to use
     */
    private void setForEiaDate(
        boolean isRequested,
        EiaDateOpt optToSet,
        EiaDate valueToUse)
    {       
        if(optToSet != null)
        {
            if (isRequested)
            {
                if (valueToUse != null)
                {
                    // set or overwrite existing value
                    optToSet.theValue(valueToUse);
                }
                else
                {
                    optToSet.__default();
                }
            }
        }
        else
        {
            System.out.println("Warning: A ProviderLocationProperty being set by " + getClass().getName() + " is unexpectedly null.");
        }
    }
    
    /**
     * Method setForCachedEiaDate.
     * @param optToSet
     * @param valueToUse
     */
    private void setForCachedEiaDate(
        EiaDateOpt optToSet,
        EiaDate valueToUse)
    {
        
        if(optToSet != null)
        {
            if (valueToUse != null)
            {
                // set or overwrite existing value
                optToSet.theValue(valueToUse);
            }
            else
            {
                optToSet.__default();
            }
        }
        else
        {
            System.out.println("Warning: A ProviderLocationProperty being set by " + getClass().getName() + " is unexpectedly null.");
        }
    }
    
    /**
     * Set ServiceLocation.ServiceAddress variable
     * @param value Address
     */
    public void setServiceAddress(Address2 value)
    {
        //if (getLocationPropertiesRequested().isRetrieveServiceAddress())
        //{
            if (value != null)
            {
                getServiceLocation().aServiceAddress = value;
            }
            else if (!isRequestedALL())
            {
                getServiceLocation().aServiceAddress = new Address2();
            }
        //}
        
        if (value != null)
        {
            getCachedServiceLocation().aServiceAddress = value;
        }
        else
        {
            getCachedServiceLocation().aServiceAddress = new Address2();
        }
    }
    
    /**
     * Set ServiceLocation.aAddressMatchCode variable
     * @param value String
     */
    public void setAddressMatchCode(String value)
    {
        setForProperty(
            getLocationPropertiesRequested().isRetrieveAddressMatchCode(),
            getServiceLocation().aAddressMatchCode,
            value);
            
        setForCachedProperty(
            getCachedServiceLocation().aAddressMatchCode,
            value);

    }
    
    /**
     * Set ServiceLocation.aAddressMatchCodeDescription variable
     * @param value String
     */
    public void setAddressMatchCodeDescription(String value)
    {
        setForProperty(
            getLocationPropertiesRequested().isRetrieveAddressMatchCodeDescription(),
            getServiceLocation().aAddressMatchCodeDescription,
            value);
            
        setForCachedProperty(
            getCachedServiceLocation().aAddressMatchCodeDescription,
            value);
    }
    
    /**
     * Set ServiceLocation.aAreaTransferCutDate variable
     * @param value String
     */
    public void setAreaTransferCutDate(String value)
    {
        short YYYY = 0;
        short MM = 0;
        short DD = 0;
        
        if (value == null || 
            value.trim().length() != 8 ||
            !LIMBase.allNumeric(value.trim()))
        {
            return;
        }
        
        try
        {
            YYYY = Short.parseShort(value.substring(0, 4));
            MM = Short.parseShort(value.substring(4, 6));
            DD = Short.parseShort(value.substring(6, 8));
        }
        catch (NumberFormatException e)
        {
            return;
        }
        
        EiaDate eiaDate = new EiaDate(YYYY, MM, DD);
        
        setForEiaDate(
            getLocationPropertiesRequested().isRetrieveAreaTransferCutDate(),
            getServiceLocation().aAreaTransferCutDate,
            eiaDate);
            
        setForCachedEiaDate(
            getCachedServiceLocation().aAreaTransferCutDate,
            eiaDate);
    }
    
    /**
     * Set ServiceLocation.aAreaTransferNumberChangeDate variable
     * @param value String
     */
    public void setAreaTransferNumberChangeDate(String value)
    {
        short YYYY = 0;
        short MM = 0;
        short DD = 0;
        
        if (value == null || 
            value.trim().length() != 8 ||
            !LIMBase.allNumeric(value.trim()))
        {
            return;
        }
        
        try
        {
            YYYY = Short.parseShort(value.substring(0, 4));
            MM = Short.parseShort(value.substring(4, 6));
            DD = Short.parseShort(value.substring(6, 8));
        }
        catch (NumberFormatException e)
        {
            return;
        }
        
        EiaDate eiaDate = new EiaDate(YYYY, MM, DD);
        
        setForEiaDate(
            getLocationPropertiesRequested().isRetrieveAreaTransferNumberChangeDate(),
            getServiceLocation().aAreaTransferNumberChangeDate,
            eiaDate);
            
        setForCachedEiaDate(
            getCachedServiceLocation().aAreaTransferNumberChangeDate,
            eiaDate);
    }
    
    /**
     * Set ServiceLocation.aAreaTransferNpaNxx variable
     * @param value String
     */
    public void setAreaTransferNpaNxx(String value)
    {
        setForProperty(
            getLocationPropertiesRequested().isRetrieveAreaTransferNpaNxx(),
            getServiceLocation().aAreaTransferNpaNxx,
            value);
            
        setForCachedProperty(
            getCachedServiceLocation().aAreaTransferNpaNxx,
            value);

    }
    
    /**
     * Set ServiceLocation.aAreaTransferWireCenter variable
     * @param value String
     */
    public void setAreaTransferWireCenter(String value)
    {
        setForProperty(
            getLocationPropertiesRequested().isRetrieveAreaTransferWireCenter(),
            getServiceLocation().aAreaTransferWireCenter,
            value);
            
        setForCachedProperty(
            getCachedServiceLocation().aAreaTransferWireCenter,
            value);

    }
    
    /**
     * Set ServiceLocation.CentralOfficeCode variable
     * @param value String
     */
    public void setCentralOfficeCode(String value)
    {
        setForProperty(
            getLocationPropertiesRequested().isRetrieveCentralOfficeCode(),
            getServiceLocation().aCentralOfficeCode,
            value);
            
        setForCachedProperty(
            getCachedServiceLocation().aCentralOfficeCode,
            value);

    }
    
    /**
     * Set ServiceLocation.CommunityName variable
     * @param value String
     */
    public void setCommunityName(String value)
    {
        setForProperty(
            getLocationPropertiesRequested().isRetrieveCommunityName(),
            getServiceLocation().aCommunityName,
            value);
            
        setForCachedProperty(
            getCachedServiceLocation().aCommunityName,
            value);
    }
    
    /**
     * Set ServiceLocation.aConnectThrough variable
     * @param value String
     */
    public void setConnectThrough(String value)
    {
        setForProperty(
            getLocationPropertiesRequested().isRetrieveConnectThrough(),
            getServiceLocation().aConnectThrough,
            value);
            
        setForCachedProperty(
            getCachedServiceLocation().aConnectThrough,
            value);
    }
    
    /**
     * Set ServiceLocation.aCrossBoundaryState variable
     * @param value String
     */
    public void setCrossBoundaryState(String value)
    {
        setForProperty(
            getLocationPropertiesRequested().isRetrieveCrossBoundaryState(),
            getServiceLocation().aCrossBoundaryState,
            value);
            
        setForCachedProperty(
            getCachedServiceLocation().aCrossBoundaryState,
            value);

    }
    
    /**
     * Set ServiceLocation.E911Exempt variable
     * @param value String
     */
    public void setE911Exempt(String value)
    {
        setForProperty(
            getLocationPropertiesRequested().isRetrieveE911Exempt(),
            getServiceLocation().aE911Exempt,
            value);
            
        setForCachedProperty(
            getCachedServiceLocation().aE911Exempt,
            value);
    }
    
    /**
     * Set ServiceLocation.E911NonRecurringCharge variable
     * @param value String
     */
    public void setE911NonRecurringCharge(String value)
    {
        setForProperty(
            getLocationPropertiesRequested()
                .isRetrieveE911NonRecurringCharge(),
            getServiceLocation().aE911NonRecurringCharge,
            value);
                        
        setForCachedProperty(
            getCachedServiceLocation().aE911NonRecurringCharge,
            value);
    }
    
    /**
     * Set ServiceLocation.E911Surcharge variable
     * @param value String
     */
    public void setE911Surcharge(String value)
    {
        setForProperty(
            getLocationPropertiesRequested().isRetrieveE911Surcharge(),
            getServiceLocation().aE911Surcharge,
            value);
            
        setForCachedProperty(
            getCachedServiceLocation().aE911Surcharge,
            value);
    }
    
    /**
     * Set ServiceLocation.ExchangeCode variable
     * @param value String
     */
    public void setExchangeCode(String value)
    {
        setForProperty(
            getLocationPropertiesRequested().isRetrieveExchangeCode(),
            getServiceLocation().aExchangeCode,
            value);
            
        setForCachedProperty(
            getCachedServiceLocation().aExchangeCode,
            value);
    }
    
    /**
     * Set ServiceLocation.Exco variable
     * @param value String
     */
    public void setExco(String value)
    {
        setForProperty(
            getLocationPropertiesRequested().isRetrieveExco(),
            getServiceLocation().aExco,
            value);
            
        setForCachedProperty(
            getCachedServiceLocation().aExco,
            value);
    }
    
    /**
     * Set ServiceLocation.aIndependentCompanyIndicator variable
     * @param value String
     */
    public void setIndependentCompanyIndicator(String value)
    {
        setForProperty(
            getLocationPropertiesRequested().isRetrieveIndependentCompanyIndicator(),
            getServiceLocation().aIndependentCompanyIndicator,
            value);
            
        setForCachedProperty(
            getCachedServiceLocation().aIndependentCompanyIndicator,
            value);
    }
    
    /**
     * Set ServiceLocation.LataCode variable
     * @param value String
     */
    public void setLataCode(String value)
    {
        setForProperty(
            getLocationPropertiesRequested().isRetrieveLataCode(),
            getServiceLocation().aLataCode,
            value);
            
        setForCachedProperty(
            getCachedServiceLocation().aLataCode,
            value);
    }
    
    /**
     * Set ServiceLocation.LocalProviderServingOfficeCode variable
     * @param value String
     */
    public void setLocalProviderServingOfficeCode(String value)
    {
        setForProperty(
            getLocationPropertiesRequested()
                .isRetrieveLocalProviderServingOfficeCode(),
                getServiceLocation().aLocalProviderServingOfficeCode,
            value);
                        
        setForCachedProperty(
                getCachedServiceLocation().aLocalProviderServingOfficeCode,
            value);

    }
    
    /**
     * Set ServiceLocation.OwnedWiring variable
     * @param value String
     */
    public void setOwnedWiring(String value)
    {
        setForProperty(
            getLocationPropertiesRequested().isRetrieveOwnedWiring(),
            getServiceLocation().aOwnedWiring,
            value);
            
        setForCachedProperty(
            getCachedServiceLocation().aOwnedWiring,
            value);
    }
    
    /**
     * Set ServiceLocation.PrimaryDirectoryCode variable
     * @param value String
     */
    public void setPrimaryDirectoryCode(String value)
    {
        setForProperty(
            getLocationPropertiesRequested()
                .isRetrievePrimaryDirectoryCode(),
            getServiceLocation().aPrimaryDirectoryCode,
            value);
                        
        setForCachedProperty(
            getCachedServiceLocation().aPrimaryDirectoryCode,
            value);
    }
    
    /**
     * Set ServiceLocation.PrimaryNpaNxx variable
     * @param value String
     */
    public void setPrimaryNpaNxx(String value)
    {
        setForProperty(
            getLocationPropertiesRequested().isRetrievePrimaryNpaNxx(),
            getServiceLocation().aPrimaryNpaNxx,
            value);
            
        setForCachedProperty(
            getCachedServiceLocation().aPrimaryNpaNxx,
            value);
    }
    
    /**
     * Set ServiceLocation.QuickDialTone variable
     * @param value String
     */
    public void setQuickDialTone(String value)
    {
        setForProperty(
            getLocationPropertiesRequested().isRetrieveQuickDialTone(),
            getServiceLocation().aQuickDialTone,
            value);
            
        setForCachedProperty(
            getCachedServiceLocation().aQuickDialTone,
            value);
    }
    
    /**
     * Set ServiceLocation.QuickDialToneNumber variable
     * @param value String
     */
    public void setQuickDialToneNumber(String value)
    {
        setForProperty(
            getLocationPropertiesRequested()
                .isRetrieveQuickDialToneNumber(),
            getServiceLocation().aQuickDialToneNumber,
            value);
                        
        setForCachedProperty(
            getCachedServiceLocation().aQuickDialToneNumber,
            value);
    }
    
    /**
     * Set ServiceLocation.RateZone variable
     * @param value String
     */
    public void setRateZone(String value)
    {
        setForProperty(
            getLocationPropertiesRequested().isRetrieveRateZone(),
            getServiceLocation().aRateZone,
            value);
            
        setForCachedProperty(
            getCachedServiceLocation().aRateZone,
            value);

    }
    
    /**
     * Set ServiceLocation.RateZoneBandCode variable
     * @param value String
     */
    public void setRateZoneBandCode(String value)
    {
        setForProperty(
            getLocationPropertiesRequested().isRetrieveRateZoneBandCode(),
            getServiceLocation().aRateZoneBandCode,
            value);
            
        setForCachedProperty(
            getCachedServiceLocation().aRateZoneBandCode,
            value);
    }
    
    /**
     * Set ServiceLocation.SagWireCenter variable
     * @param value String
     */
    public void setSagWireCenter(String value)
    {
        setForProperty(
            getLocationPropertiesRequested().isRetrieveSagWireCenter(),
            getServiceLocation().aSagWireCenter,
            value);
            
        setForCachedProperty(
            getCachedServiceLocation().aSagWireCenter,
            value);
    }
    
    /**
     * Set ServiceLocation.ServingAreaDescription variable
     * @param value String
     */
    public void setServingAreaDescription(String value)
    {
        setForProperty(
            getLocationPropertiesRequested()
                .isRetrieveServingAreaDescription(),
            getServiceLocation().aServingAreaDescription,
            value);
                        
        setForCachedProperty(
            getCachedServiceLocation().aServingAreaDescription,
            value);
    }
    
    /**
     * Set ServiceLocation.StreetAddressGuideArea variable
     * @param value String
     */
    public void setStreetAddressGuideArea(String value)
    {
        setForProperty(
            getLocationPropertiesRequested()
                .isRetrieveStreetAddressGuideArea(),
            getServiceLocation().aStreetAddressGuideArea,
            value);
                        
        setForCachedProperty(
            getCachedServiceLocation().aStreetAddressGuideArea,
            value);
    }
    
    /**
     * Set ServiceLocation.Surcharge16Percent variable
     * @param value String
     */
    public void setSurcharge16Percent(String value)
    {
        setForProperty(
            getLocationPropertiesRequested().isRetrieveSurcharge16Percent(),
            getServiceLocation().aSurcharge16Percent,
            value);
            
        setForCachedProperty(
            getCachedServiceLocation().aSurcharge16Percent,
            value);
    }
    
    /**
     * Set ServiceLocation.Surcharge4Percent variable
     * @param value String
     */
    public void setSurcharge4Percent(String value)
    {
        setForProperty(
            getLocationPropertiesRequested().isRetrieveSurcharge4Percent(),
            getServiceLocation().aSurcharge4Percent,
            value);
            
        setForCachedProperty(
            getCachedServiceLocation().aSurcharge4Percent,
            value);
    }
    
    /**
     * Set ServiceLocation.TarCode variable
     * @param value String
     */
    public void setTarCode(String value)
    {
        setForProperty(
            getLocationPropertiesRequested().isRetrieveTarCode(),
            getServiceLocation().aTarCode,
            value);
            
        setForCachedProperty(
            getCachedServiceLocation().aTarCode,
            value);
    }
    
    /**
     * Set ServiceLocation.TelephoneNumber variable
     * @param value String
     */
    public void setTelephoneNumber(String value)
    {                              
        setForProperty(
            getLocationPropertiesRequested().isRetrieveTelephoneNumber(),
            getServiceLocation().aTelephoneNumber,
            value);
            
        setForCachedProperty(
            getCachedServiceLocation().aTelephoneNumber,
            value);
    }
    
    /**
     * Set ServiceLocation.WorkingServiceOnLocation variable
     * @param value String
     */
    public void setWorkingServiceOnLocation(String value)
    {
        setForProperty(
            getLocationPropertiesRequested()
                .isRetrieveWorkingServiceOnLocation(),
            getServiceLocation().aWorkingServiceOnLocation,
            value);
                        
        setForCachedProperty(
            getCachedServiceLocation().aWorkingServiceOnLocation,
            value);
    }
    
    /**
     * Set ServiceLocation.aWireCenter variable
     * @param value String
     */
    public void setWireCenter(String value)
    {
        setForProperty(
            getLocationPropertiesRequested().isRetrieveWireCenter(),
            getServiceLocation().aWireCenter,
            value);
            
        setForCachedProperty(
            getCachedServiceLocation().aWireCenter,
            value);
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
        
        getServiceLocation().aExtensions.theValue (getExensionProperties());
    }

    /**
     * Method setForCachedExtensionProperty.  This method encompasses the logic
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
        
        getCachedServiceLocation().aExtensions.theValue (getCachedExtensionProperties());
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
}
