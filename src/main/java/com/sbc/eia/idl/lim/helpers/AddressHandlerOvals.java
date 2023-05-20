// $Id: AddressHandlerOvals.java,v 1.6 2006/10/20 20:59:39 jd3462 Exp $

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
 *  This file contains the AddressHandlerOvals class, the base class for
 *  other ovals related address handlers.
 *  Description
 */
package com.sbc.eia.idl.lim.helpers;

import com.sbc.eia.idl.lim_types.Address;

/** Description
 *  AddressHandlerOvals is the base class for Ovals related AddressHandlers.
 *  Description
 */
public class AddressHandlerOvals extends AddressHandler
{
    /**
     * Constructor for AddressHandlerOvals.
     */
    public AddressHandlerOvals()
    {
        super();
    }
    
    /**
     * Constructor for AddressHandlerOvals.
     * @param route
     * @param box
     * @param houseNumberPrefix
     * @param houseNumber
     * @param assignedHouseNumber
     * @param houseNumberSuffix
     * @param streetDirection
     * @param streetName
     * @param streetThoroughfare
     * @param streetNameSuffix
     * @param city
     * @param state
     * @param postalCode
     * @param postalCodePlus4
     * @param county
     * @param country
     * @param unitStructType
     * @param unitStructValue
     * @param unitLevelType
     * @param unitLevelValue
     * @param unitUnitType
     * @param unitUnitValue
     * @param additionalAddressInformation
     */
    public AddressHandlerOvals(
        String route,
        String box,
        String houseNumberPrefix,
        String houseNumber,
        String assignedHouseNumber,
        String houseNumberSuffix,
        String streetDirection,
        String streetName,
        String streetThoroughfare,
        String streetNameSuffix,
        String city,
        String state,
        String postalCode,
        String postalCodePlus4, 
        String county,
        String country,
        String unitStructType,
        String unitStructValue,
        String unitLevelType,
        String unitLevelValue,
        String unitUnitType,
        String unitUnitValue,
        String additionalAddressInformation)
    {
        super(
            route,
            box,
            houseNumberPrefix,
            houseNumber,
            assignedHouseNumber,
            houseNumberSuffix,
            streetDirection,
            streetName,
            streetThoroughfare,
            streetNameSuffix,
            city,
            state,
            postalCode,
            postalCodePlus4,
            county,
            country,
            unitStructType,
            unitStructValue,
            unitLevelType,
            unitLevelValue,
            unitUnitType,
            unitUnitValue,
            null,
            null,
            null,
            null,
            null,
            additionalAddressInformation,
            null,
            null);
    }

    /**
     * Constructor for AddressHandlerOvals.
     * @param route
     * @param box
     * @param houseNumberPrefix
     * @param houseNumber
     * @param assignedHouseNumber
     * @param houseNumberSuffix
     * @param streetDirection
     * @param streetName
     * @param streetThoroughfare
     * @param streetNameSuffix
     * @param city
     * @param state
     * @param postalCode
     * @param postalCodePlus4
     * @param county
     * @param country
     * @param unitStructType
     * @param unitStructValue
     * @param unitLevelType
     * @param unitLevelValue
     * @param unitUnitType
     * @param unitUnitValue
     * @param originalStreetDirection
     * @param originalStreetNameSuffix
     * @param cassAddressLines
     * @param auxiliaryAddressLines
     * @param cassAdditionalInfo
     * @param additionalInfo
     * @param businessName
     */
    public AddressHandlerOvals(
        String route,
        String box,
        String houseNumberPrefix,
        String houseNumber,
        String assignedHouseNumber,
        String houseNumberSuffix,
        String streetDirection,
        String streetName,
        String streetThoroughfare,
        String streetNameSuffix,
        String city,
        String state,
        String postalCode,
        String postalCodePlus4,
        String county,
        String country,
        String unitStructType,
        String unitStructValue,
        String unitLevelType,
        String unitLevelValue,
        String unitUnitType,
        String unitUnitValue,
        String originalStreetDirection,
        String originalStreetNameSuffix,
        String[] cassAddressLines,
        String[] auxiliaryAddressLines,
        String cassAdditionalInfo,
        String additionalInfo,
        String businessName,
        String attention)
    {
        super(
            route,
            box,
            houseNumberPrefix,
            houseNumber,
            assignedHouseNumber,
            houseNumberSuffix,
            streetDirection,
            streetName,
            streetThoroughfare,
            streetNameSuffix,
            city,
            state,
            postalCode,
            postalCodePlus4,
            county,
            country,
            unitStructType,
            unitStructValue,
            unitLevelType,
            unitLevelValue,
            unitUnitType,
            unitUnitValue,
            originalStreetDirection,
            originalStreetNameSuffix,
            cassAddressLines,
            auxiliaryAddressLines,
            cassAdditionalInfo,
            additionalInfo,
            businessName,
            attention);
    }

    /**
     * Constructor for AddressHandlerOvals.
     * @param address
     * @throws AddressHandlerException
     */
    public AddressHandlerOvals(Address address)
        throws AddressHandlerException
    {
        super(address);
    }
    
}
