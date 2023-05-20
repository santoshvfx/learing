//$Id: PostalLocationBuilder.java,v 1.1 2008/01/17 21:40:17 jh9785 Exp $

package com.sbc.eia.bis.lim.transactions.common;

import com.sbc.bccs.idl.helpers.IDLUtil;
import com.sbc.eia.idl.lim_types.PostalLocation;
import com.sbc.eia.idl.lim_types.Address;
import com.sbc.eia.idl.types.StringOpt;

/**
 * The PostalLocationBuilder initializes the 
 * PostalLocation. Based on new retrieveLocationForPostalAddress, 
 * requested properties are not required when formatting the PostalLocation
 */
public class PostalLocationBuilder 
{
    private PostalLocation m_PostalLocation = null;
    
    public PostalLocationBuilder() 
    {
        initializePostalLocation();
    }
    
    /**
     * Initialize ServiceLocation
     */
    private void initializePostalLocation()
    {
        setPostalLocation(getDefaultPostalLocation());     
    }
    
    /**
     * Return a defaulted ServiceLocation
     * @return ServiceLocation
     */
    public static PostalLocation getDefaultPostalLocation()
    {
        PostalLocation loc_PostalLocation = new PostalLocation();
        
        loc_PostalLocation.aPostalAddress = new Address();
        loc_PostalLocation.aAddressMatchCode = "";
        loc_PostalLocation.aAddressMatchCodeDescription = IDLUtil.toOpt((String)null);
        loc_PostalLocation.aDeliveryPointValidationCode = IDLUtil.toOpt((String)null);
        loc_PostalLocation.aCityStatePostalCodeValid = IDLUtil.toOpt((String)null);
        
        return loc_PostalLocation;
    }
    
    /**
     * Set local class variable m_serviceLocation.
     * @param m_serviceLocation ServiceLocation
     */
    public void setPostalLocation(PostalLocation postalLocation)
    {
        m_PostalLocation = postalLocation;
    }
    
    /**
     * Get local class variable m_PostalLocation.
     */
    public PostalLocation getPostalLocation()
    {
        return m_PostalLocation;
    }
    
    /**
     * Set PostalLocation.aPostalAddress variable
     * @param value Address
     */
    public void setPostalAddress(Address value)
    {
        if (value != null)
        {
            getPostalLocation().aPostalAddress = value;
        }
        else
        {
            getPostalLocation().aPostalAddress = new Address();
        }
    }
    
    /**
     * Method setAddressMatchCode.
     * @param value
     */
    public void setAddressMatchCode(String value)
    {
        if (value != null && value.length() > 0)
        {
            getPostalLocation().aAddressMatchCode = value;
        }
        else
        {
            getPostalLocation().aAddressMatchCode = "";
            
        }
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
                // reset
               optToSet.theValue("");    
            }
        }
        else
        {
            System.out.println("Warning: A PostalLocation being set by " + getClass().getName() + " is unexpectedly null.");
        }
    }
    
    /**
     * Method setAddressMatchCodeDescription.
     * @param value
     */
    public void setAddressMatchCodeDescription(String value)
    {
        setForProperty(getPostalLocation().aAddressMatchCodeDescription, value);
    }
    
    /**
     * Method setDeliveryPointValidationCode.
     * @param value
     */
    public void setDeliveryPointValidationCode(String value)
    {
        setForProperty(getPostalLocation().aDeliveryPointValidationCode, value);
    }
    
    /**
     * Method setCityStatePostalCodeValid.
     * @param value
     */
    public void setCityStatePostalCodeValid(String value)
    {
        setForProperty(getPostalLocation().aCityStatePostalCodeValid, value);
    }
}
