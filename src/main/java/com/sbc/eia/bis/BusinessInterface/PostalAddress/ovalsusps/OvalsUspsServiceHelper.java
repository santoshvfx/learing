// $Id: OvalsUspsServiceHelper.java,v 1.1 2007/09/25 19:54:30 jh9785 Exp $

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
 *  This file contains the OvalsUspsServiceHelper.
 *  Description
 */
package com.sbc.eia.bis.BusinessInterface.PostalAddress.ovalsusps;

import java.util.Hashtable;
import java.util.Properties;

import com.sbc.bccs.utilities.Logger;
import com.sbc.bccs.utilities.Utility;
import com.sbc.eia.bis.embus.service.access.ServiceException;
import com.sbc.eia.bis.embus.service.access.ServiceHelper;
import com.sbc.eia.bis.embus.service.access.ServiceHelperSelfTestException;
import com.sbc.eia.bis.embus.service.access.ServicePasswordDecrypter;
import com.sbc.eia.bis.facades.lim.ejb.tests.LimSelfTest;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.bis.lim.jaxb.ovalsusps.OVALS;
import com.sbc.eia.bis.lim.util.LIMBase;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.lim.RetrieveLocationForPostalAddressReturn;
import com.sbc.eia.idl.lim_types.Address;
import com.sbc.eia.idl.lim_types.FieldedAddress;
import com.sbc.eia.idl.types.LongOpt;
import com.sbc.eia.idl.types.StringOpt;
import com.sbc.eia.idl.types.StringSeqOpt;

/** Description
 *  The OvalsUspsServiceHelper helps configure the service access layer
 *  and provides a view of service specific methods.
 *  Description
 */
public class OvalsUspsServiceHelper extends ServiceHelper
{
    /*
     * The name of the ovalsusps service, this value is used as part of
     * a configuration property to find both the envionment name
     * and the service access configuration file.
     */
    private final static String SERVICENAME = "OVALSUSPS";
    private Hashtable properties;
    private Logger logger;
    
    /**
     * Constructor for OvalsUspsServiceHelper.
     */
    public OvalsUspsServiceHelper(Hashtable properties, LIMBase base)
        throws ServiceException
    {
        super( properties, base, SERVICENAME );
        setServiceAccess(
            new OvalsUspsServiceAccess(
                getEnvironmentName(),
                getClientConfigFileName(),
                base));
    }


    /**
     * Constructor for OvalsUspsServiceHelper SelfTest
     */
    public OvalsUspsServiceHelper (Hashtable properties, Logger logger)
            throws ServiceException

    {
        super( properties, logger, SERVICENAME );
        setServiceAccess(
                new com.sbc.eia.bis.BusinessInterface.ovalsusps.OvalsUspsServiceAccess(
                        getEnvironmentName(),
                        getClientConfigFileName(),
                        logger));
        this.logger = logger;
        this.properties = properties;
    }


    public OVALS ovalsUspsRequestAndResponse(
        OvalsUspsRetrieveLocReq request)
        throws ServiceException
    {
        return (OVALS) getServiceAccess().sendAndReceive(
            "getLocationPropertiesByAddress",
            new Object[] { request })[0];
    }

    /**
     * Closes any existing messaging artifacts that may cause resource
     * leaks.
     */
    public void exit()
    {
        getServiceAccess().exit();

    }

    private StringOpt toStringOpt(String str) {
        StringOpt strOpt = new StringOpt();
        strOpt.theValue(str);
        return strOpt;
    }

    public BisContext selfTest(BisContext aContext) throws ServiceException {
        OVALSUSPS ovalsusps = new OVALSUSPS((Utility)this.logger, this.properties);

        Address aAddress = new Address();
        LongOpt aMaxAddressAlternatives = new LongOpt();
        FieldedAddress fieldedAddress = new FieldedAddress();
        fieldedAddress.aRoute = new StringOpt();
        fieldedAddress.aBox = new StringOpt();
        fieldedAddress.aHouseNumberPrefix = new StringOpt();
        fieldedAddress.aHouseNumber = toStringOpt("100");
        fieldedAddress.aAssignedHouseNumber = new StringOpt();
        fieldedAddress.aHouseNumberSuffix = new StringOpt();
        fieldedAddress.aStreetDirection = toStringOpt("S");
        fieldedAddress.aStreetName = toStringOpt("S MAIN ST");
        fieldedAddress.aStreetThoroughfare = new StringOpt();
        fieldedAddress.aStreetNameSuffix = new StringOpt();
        fieldedAddress.aCity = toStringOpt("ST CHARLES");
        fieldedAddress.aState = toStringOpt("MO");
        fieldedAddress.aPostalCode = toStringOpt("63301");
        fieldedAddress.aPostalCodePlus4 = new StringOpt();
        fieldedAddress.aCounty = new StringOpt();
        fieldedAddress.aCountry = new StringOpt();
        fieldedAddress.aStructureType = new StringOpt();
        fieldedAddress.aStructureValue = new StringOpt();
        fieldedAddress.aLevelType = new StringOpt();
        fieldedAddress.aLevelValue = new StringOpt();
        fieldedAddress.aUnitType = new StringOpt();
        fieldedAddress.aUnitValue = new StringOpt();
        fieldedAddress.aOriginalStreetDirection = new StringOpt();
        fieldedAddress.aOriginalStreetNameSuffix = new StringOpt();
        fieldedAddress.aCassAddressLines = new StringSeqOpt();
        fieldedAddress.aAuxiliaryAddressLines = new StringSeqOpt();
        fieldedAddress.aCassAdditionalInfo = new StringOpt();
        fieldedAddress.aAdditionalInfo = new StringOpt();
        fieldedAddress.aBusinessName = new StringOpt();
        fieldedAddress.aCountryCode = new StringOpt();
        fieldedAddress.aCityCode = new StringOpt();
        fieldedAddress.aServiceLocationName = new StringOpt();
        fieldedAddress.aAddressId = new StringOpt();
        fieldedAddress.aAliasName = new StringOpt();
        fieldedAddress.aAttention = new StringOpt();
        aAddress.aFieldedAddress(fieldedAddress);

        try {
           RetrieveLocationForPostalAddressReturn response =
                   ovalsusps.retrieveLocationForPostalAddress(aContext,aAddress,aMaxAddressAlternatives);
        } catch (Exception e) {
            log(LogEventId.DEBUG_LEVEL_2, "ServiceHelper::selfTest failed. Exception message: " + e.getMessage());
            throw new ServiceHelperSelfTestException("ServiceHelper::selfTest failed! " + e.getMessage(),	e);
        }

        return aContext;
    }

}
