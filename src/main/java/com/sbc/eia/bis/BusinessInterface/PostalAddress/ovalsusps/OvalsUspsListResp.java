// $Id: OvalsUspsListResp.java,v 1.2 2008/01/17 21:58:14 jh9785 Exp $

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
 *  This file contains the OvalsUspsListResp class.
 *  Description
 */

package com.sbc.eia.bis.BusinessInterface.PostalAddress.ovalsusps;

import java.util.List;
import java.util.ListIterator;
import java.util.Properties;
import java.util.Vector;

import com.sbc.bccs.idl.helpers.IDLUtil;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.bis.lim.jaxb.ovalsusps.impl.ADDRESSTypeImpl;
import com.sbc.eia.bis.lim.jaxb.ovalsusps.impl.SAGENTTypeImpl;
import com.sbc.eia.bis.lim.transactions.common.PostalAddrAltResultResp;
import com.sbc.eia.bis.lim.transactions.common.PostalLocationBuilder;
import com.sbc.eia.bis.lim.util.LIMBase;
import com.sbc.eia.idl.lim.helpers.AddressHandlerException;
import com.sbc.eia.idl.lim.helpers.AddressHandlerUSPS;
import com.sbc.eia.idl.lim_types.SubmittedAddressException;

/**
 * Build a list of addresses from OVALSUSPS menus.
 * Creation date: (3/13/01 2:00:56 PM)
 * @author: David Brawley
 */
public class OvalsUspsListResp extends PostalAddrAltResultResp 
{
    
    private OvalsUspsRetrieveLocReq m_request = null;
    
    protected static String addCassAdditionalAddressInfo  = null;
	protected ADDRESSTypeImpl addressType = null;
	protected String application = "";
	protected String warning = "";
    
	/**
	 * This default (package) access constructor allows other classes
	 * to use this class in an atypical way.
	 * Creation date: (3/16/01 10:05:16 AM)
	 * @param utility LIMBase
	 */
	OvalsUspsListResp(LIMBase utility) {
		super(utility);
	}
	/**
	 * Construct lists of CassAddresses from OVALS USPS
	 * OVALSUSPS ADDRESSTypeImpl.
	 * OVALSUSPS SAGENTTypeImpl.
	 * Creation date: (7/28/03 10:05:16 AM)
	 * @param utility LIMBase
	 * @param aAddressType ADDRESSTypeImpl
	 * @param aWarning String
	 */
	OvalsUspsListResp(LIMBase utility, OvalsUspsRetrieveLocReq request,
        ADDRESSTypeImpl aAddressType, String aApplication, String aWarning) {
		super(utility);
		addressType = aAddressType;
		setApplication(aApplication);
		setWarning(aWarning);
        setRequest(request);
	}

	/**
	 * Package-access getter for the internal List.
	 * Creation date: (3/16/01 10:06:07 AM)
	 * @return List
	 */
	List getAddrCass() {
		return addrList;
	}

	/**
	 * Returns the application.
	 * @return String
	 */
	public String getApplication() {
		return application;
	}

	/**
	 * Returns the warning message.
	 * @return String
	 */
	public String getWarning() {
		return warning;
	}

	/**
	 * parseAddressTypeImpl will process the AddressTypeImpl, for each SAGENTTYpeImpl
	 * the lines will be parsed and added to the alternate CassAddress list.
	 * Creation date: (7/28/03 12:01:14 PM)
	 */
	protected void parseAddressTypeImpl() 
	{
		AddressHandlerUSPS address = null;
		int startRow = 0;
		
		try {
			ListIterator sagentIt = addressType.getSAGENT().listIterator();

			while (sagentIt.hasNext()) 
			{
                SAGENTTypeImpl sagent = (SAGENTTypeImpl) sagentIt.next();
                
                Vector lines = new Vector();
                if (sagent.getLINE1() != null && sagent.getLINE1().trim().length() > 0)
                {
                    lines.add(sagent.getLINE1().trim());
                }
                    
                if (sagent.getLINE2() != null && sagent.getLINE2().trim().length() > 0)
                {
                    lines.add(sagent.getLINE2().trim());
                }
                    
                if (sagent.getLINE3() != null && sagent.getLINE3().trim().length() > 0)
                {
                    lines.add(sagent.getLINE3().trim());
                }
                    
                if (sagent.getLINE4() != null && sagent.getLINE4().trim().length() > 0)
                {
                    lines.add(sagent.getLINE4().trim());
                }
                    
                if (sagent.getLINE5() != null && sagent.getLINE5().trim().length() > 0)
                {
                    lines.add(sagent.getLINE5().trim());
                }
    
                String[] cassAddrLines = null;
                lines.copyInto((cassAddrLines = new String[lines.size()]));
    
                lines = new Vector();
                
                if (sagent.getAUX1() != null && (sagent.getAUX1().trim()).length() > 0)
                    lines.add(sagent.getAUX1().trim());
                if (sagent.getAUX2() != null && (sagent.getAUX2().trim()).length() > 0)
                    lines.add(sagent.getAUX2().trim());
                if (sagent.getAUX3() != null && (sagent.getAUX3().trim()).length() > 0)
                    lines.add(sagent.getAUX3().trim());
                if (sagent.getAUX4() != null && (sagent.getAUX4().trim()).length() > 0)
                    lines.add(sagent.getAUX4().trim());
                if (sagent.getAUX5() != null && (sagent.getAUX5().trim()).length() > 0)
                    lines.add(sagent.getAUX5().trim());
                
                String[] auxLines = null;
                lines.copyInto((auxLines = new String[lines.size()]));

				// create alternative address
				address =  
					new AddressHandlerUSPS(
                        sagent.getCITY().toUpperCase().trim(),              // aCity
                        sagent.getCOUNTYNAME().toUpperCase().trim(),        // aCounty
                        sagent.getHOUSENUMBER().toUpperCase().trim(),       // aHouseNumber
                        sagent.getPREFIXDIRECTION().toUpperCase().trim(),   // aStreetDirection
                        sagent.getPOSTFIXDIRECTION().toUpperCase().trim(),  // aStreetNameSuffix
                        sagent.getSTATE().toUpperCase().trim(),             // aState
                        sagent.getSTREETNAME().toUpperCase().trim(),        // aStreetName
                        sagent.getSTREETTYPE().toUpperCase().trim(),        // aStreetThoroughfare
                        sagent.getUNITNUMBER().toUpperCase().trim(),        // Unit_value
                        sagent.getUNITNUMBER2().toUpperCase().trim(),        // Unit_value
                        sagent.getUNITTYPE().toUpperCase().trim(),          // Unit_type
                        sagent.getUNITTYPE2().toUpperCase().trim(),          // Unit_type
                        sagent.getZIP().toUpperCase().trim(),
                        sagent.getZIP4().toUpperCase().trim(),
                        cassAddrLines,
                        auxLines,
                        getCassAdditionalAddressInfo()
                        );
              
                boolean isAMQValueExist = sagent.getAMQ() != null && sagent.getAMQ().length() > 0;
                boolean populateSubmitted = false;
                boolean populateAlt = false;
                
                if (isAMQValueExist && sagent.getAMQ().substring(0,1).equalsIgnoreCase("2"))
                	populateSubmitted = true;
                	
                if (! populateSubmitted ||
                	(!sagentIt.hasNext() && (locSize == 0) && (sagent.getCSZVALID() != null))) 
                	populateAlt = true;

                if (populateSubmitted)                 	
                {
                	SubmittedAddressException addrException = 
                		new SubmittedAddressException (address.getAddress(), 
                									   IDLUtil.toOpt (sagent.getAMQ()), 
                									   IDLUtil.toOpt (getAMQDescription( sagent )));
                	addrList.add(addrException);
                	submitAddrExcSize++;
                }
                
                if (populateAlt)
                {
                    PostalLocationBuilder postalLocBuilder = new PostalLocationBuilder();
                   
                    postalLocBuilder.setPostalAddress(address.getAddress());
                    postalLocBuilder.setAddressMatchCode(sagent.getAMQ());
                    
                    if (isAMQValueExist && sagent.getAMQ().substring(0,1).equalsIgnoreCase("2"))
                    {
                        postalLocBuilder.setAddressMatchCodeDescription(getAMQDescription(sagent));
                    }
                    
                    //New OVALSUSPS.jar needs to return DPV
                    postalLocBuilder.setDeliveryPointValidationCode(sagent.getDPV());
                    
                    //Followig two fields are retired in new Postal Location
                    //postalLocBuilder.setHorizontalCoordinate(sagent.getH());
                    //postalLocBuilder.setVerticalCoordinate(sagent.getV());
                    
                	
                    if(sagent.getCSZVALID() != null)
                    {
                        postalLocBuilder.setCityStatePostalCodeValid(sagent.getCSZVALID());
                    }
             	
					addrList.add(postalLocBuilder.getPostalLocation());
					locSize++;
                }

				if (getMaxAddresses() != NO_SIZE_LIMIT && addrList.size() >= getMaxAddresses())
					return;
			}
		}
		catch (AddressHandlerException ahe) {}
	}
    
	/**
	 * Sets the application.
	 * @param application The application to set
	 */
	public void setApplication(String application) {
		this.application = application;
	}

	/**
	 * Sets the warning message.
	 * @param warning The warning to set
	 */
	public void setWarning(String warning) {
		this.warning = warning;
	}
    
    public String getCassAdditionalAddressInfo() {

        String cassAddInfo = null; 
        if (addCassAdditionalAddressInfo == null){
            if (((Properties)(getUtility().getPROPERTIES())).getProperty(OVALSUSPSTag.OVALSUSPS_CASS_ADDITIONAL_ADDRESS) != null)
                addCassAdditionalAddressInfo = ((Properties)(getUtility().getPROPERTIES())).getProperty(OVALSUSPSTag.OVALSUSPS_CASS_ADDITIONAL_ADDRESS).trim();                          
            else
                addCassAdditionalAddressInfo = "FALSE";
        }

        if ((addCassAdditionalAddressInfo.equalsIgnoreCase("TRUE")) &&
            (getWarning() != null && getWarning().length() > 0))
            cassAddInfo = getWarning();
            
        return cassAddInfo;
    }

    /**
     * get AMQ Description.
     * @param opm ObjectPropertyManager
     */
    public String getAMQDescription(SAGENTTypeImpl sagent) {
        String description = null;
        try{
            description = getUtility().getMyUSPSErrCdProperty().getProperty (sagent.getAMQ().substring(1).trim()).trim();
        }
        catch( NullPointerException npe ) {}
        
        try{
            if (description == null && sagent.getAMQ().substring(0,2).equalsIgnoreCase("2E")){
                getUtility().log(LogEventId.INFO_LEVEL_1, "Error Match Code <" + sagent.getAMQ().substring(1).trim().trim()
                    + "> not found in OvalsUspsEmatchCode.properties using default <" + OVALSUSPSTag.USPS_ERR_CD_DEFAULT + ">"); 
                description = getUtility().getMyUSPSErrCdProperty().getProperty (OVALSUSPSTag.USPS_ERR_CD_DEFAULT).trim();
            }
        }
        catch( NullPointerException npe ) {}
        
        return description;
    }

    private void setRequest(OvalsUspsRetrieveLocReq request)
    {
        m_request = request;
    }

    private OvalsUspsRetrieveLocReq getRequest()
    {
        return m_request;
    }
}
