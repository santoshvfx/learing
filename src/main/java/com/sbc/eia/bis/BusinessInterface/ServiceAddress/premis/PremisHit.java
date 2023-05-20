// $Id: PremisHit.java,v 1.4 2007/10/06 01:04:24 gg2712 Exp $

package com.sbc.eia.bis.BusinessInterface.ServiceAddress.premis;

import com.sbc.bccs.idl.helpers.TN;
import com.sbc.eia.bis.BusinessInterface.State;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.bis.lim.transactions.common.RetrieveLocReq;
import com.sbc.eia.bis.lim.transactions.common.ServiceAddrMatchResp;
import com.sbc.eia.bis.lim.transactions.common.ServiceLocationBuilder;
import com.sbc.eia.bis.lim.util.LIMBase;
import com.sbc.eia.bis.lim.util.LIMIDLUtil;
import com.sbc.eia.idl.bis_types.SystemFailure;
import com.sbc.eia.idl.lim.helpers.AddressHandler;
import com.sbc.eia.idl.lim.helpers.AddressHandlerException;
import com.sbc.eia.idl.lim.helpers.AddressHandlerPremis;
import com.sbc.eia.idl.lim_types.ServiceLocation;
import com.sbc.gwsvcs.service.premisserver.interfaces.AppPrmRespItem_t;
import com.sbc.gwsvcs.service.premisserver.interfaces.FacActnLnItem_t;
import com.sbc.gwsvcs.service.premisserver.interfaces.PremisHITResp_t;
import com.sbc.gwsvcs.service.premisserver.interfaces.PremisSuppAddrMenuResp_t;
import com.sbc.gwsvcs.service.premisserver.interfaces.SuppAddrMenuItem_t;
import com.sbc.gwsvcs.service.premisserver.interfaces.SuppAddrMenuProcStatus_t;
import com.sbc.gwsvcs.service.premisserver.interfaces.SuppLnInfo_t;

/**
 * This class represents an address-match from PREMISServer. Creation date:
 * (3/12/01 9:37:38 AM)
 * @author: David Prager
 */
public class PremisHit extends ServiceAddrMatchResp
{
    private String additionalAddressInfo = null;

    private boolean stNmInd = false;

    private String servingArea = "";

    protected RetrieveLocReq request = null;

    protected ServiceLocation addressData = null;

    /*
     * added protected ServiceLocation serviceData = null;
     */
    protected AppPrmRespItem_t apr = null;
    protected FacActnLnItem_t fal = null;

    /**
     * Construct this object from a PREMISServer "hit" response.
     * @param utility LIMBase
     * @param result PremisHITResp_t
     * @exception SystemFailure
     */
    public PremisHit(LIMBase utility, PremisHITResp_t result, PremisRetrieveLocReq aRequest) throws SystemFailure
    {
        super(utility);
        request = aRequest;
        apr = result.AppPrmRespItem;
        fal = apr.FacActnLn;

        // Determine if SAG INFORMATION ONLY address record
        if ((result.HITPktResp.RTCD.startsWith("301")) || (result.HITPktResp.RTCD.startsWith("308")))
            setAdditionalAddressInfo(PREMISTag.SAG_INFO_ONLY_MSG);
        else
            setAdditionalAddressInfo(fal.DESC_ADDR.trim());

        for (int i = 0; i < fal.LnData.length; i++)
            addWorkingTn(fal.LnData[i].LnInfo.SuppLnInfo);
    }

    /**
     * Construct this object from a PREMISServer Supplemental Address Menu. This
     * menu is considered a hit because it includes a single address, although
     * with multiple units. Creation date: (3/14/01 12:12:31 PM)
     * @param utility LIMBase
     * @param result PremisSuppAddrMenuResp_t
     * @exception SystemFailure
     */
    public PremisHit(LIMBase utility, PremisSuppAddrMenuResp_t result, PremisRetrieveLocReq aRequest)
            throws SystemFailure
    {
        super(utility);
        request = aRequest;
        apr = result.AppPrmRespItem;
        fal = apr.FacActnLn;
        setAdditionalAddressInfo(PREMISTag.SUPPLEMENTAL_ADDRESS_MSG);

        for (int i = 0; i < result.SuppAddrMenuPktResp.SuppAddrMenuProcStatus.length; i++)
        {
            SuppAddrMenuProcStatus_t menu = result.SuppAddrMenuPktResp.SuppAddrMenuProcStatus[i];

            for (int j = 0; j < menu.SuppAddrMenu.length; j++)
            {
                SuppAddrMenuItem_t item = menu.SuppAddrMenu[j];

                for (int k = 0; k < item.SuppLnInfoItem.length; k++)
                    addWorkingTn(item.SuppLnInfoItem[k].SuppLnInfo);
            }
        }

        for (int i = 0; i < fal.LnData.length; i++)
            addWorkingTn(fal.LnData[i].LnInfo.SuppLnInfo);

    }

    protected void setServiceLocation()
    {
        ServiceLocationBuilder serviceLocationBuilder = new ServiceLocationBuilder(request
                .getLocationPropertiesRequested());

        serviceLocationBuilder.setServiceAddress(getServiceAddress().getAddress2_HitResponse());
        serviceLocationBuilder.setExchangeCode(fal.SagInfo.EXCH_ID);
        serviceLocationBuilder.setPrimaryDirectoryCode(fal.SagInfo.DIR_CD);
        serviceLocationBuilder.setStreetAddressGuideArea(fal.SAGA);
        serviceLocationBuilder.setTarCode(fal.SagInfo.TAX_AREA_CD);
        serviceLocationBuilder.setWorkingServiceOnLocation((workingTnSet.size() > 0) ? "true" : "false");

        // set TelephoneNumber
        if (workingTnSet.size() > 0)
        {
            TN[] tnList = getWorkingTnList();
            serviceLocationBuilder.setTelephoneNumber(tnList[0].toString());
        }

        // set OwnedWiring
        if (fal.PrmAddr.BascAddrInfo.STATE_CD != null
                && fal.PrmAddr.BascAddrInfo.STATE_CD.equalsIgnoreCase(State.State_Texas))
        {
            String yn = PREMISTag.NO;
            if (fal.RmkData != null && fal.RmkData.length > 0 && fal.RmkData[0].BASC_ADDR_RMK != null
                    && fal.RmkData[0].BASC_ADDR_RMK.toUpperCase().indexOf(PREMISTag.OWNED_WIRING) != -1)
            {
                yn = PREMISTag.YES;
            }
            serviceLocationBuilder.setOwnedWiring(yn);
        }

        // set Exco
        if ((fal.SagInfo.EXCH_ID != null && fal.SagInfo.EXCH_ID.length() > 0)
                && (fal.PrmAddr.BascAddrInfo.STATE_CD != null)
                && (fal.PrmAddr.BascAddrInfo.STATE_CD.equalsIgnoreCase(State.State_California) || fal.PrmAddr.BascAddrInfo.STATE_CD
                        .equalsIgnoreCase(State.State_Nevada)))
        {
            StringBuffer exco = new StringBuffer(fal.SagInfo.EXCH_ID.substring(0, Math.min(3, fal.SagInfo.EXCH_ID
                    .length())));
            while (exco.length() < 3)
                exco.append(" ");
            if (fal.SagInfo.CO_ID.length() > 0)
                exco.append(fal.SagInfo.CO_ID.substring(0, Math.min(2, fal.SagInfo.CO_ID.length())));
            serviceLocationBuilder.setExco(exco.toString());
        }

        // set LataCode
        String aLata = "";
        if (fal.SagInfo.LATA_PREMIS != null && fal.SagInfo.LATA_PREMIS.length() > 0)
        {
            //  get 3 byte lata code from Lata properties file.
            try
            {
                aLata = utility.getMyLataProperty().getProperty(fal.SagInfo.LATA_PREMIS.trim()).trim();
            }
            catch (NullPointerException npe)
            {
            }
            if (aLata != null && aLata.length() > 0)
            {
                serviceLocationBuilder.setLataCode(aLata);
            }
            else
            {
                utility.log(LogEventId.INFO_LEVEL_1, "Warning: LATA Value<" + fal.SagInfo.LATA_PREMIS.trim()
                        + "> key not found in LATA.properties file");
            }
        }

        // set LocalProviderServingOfficeCode
        if (fal.SagInfo.NPA_LST != null && fal.SagInfo.NPA_LST.length > 0 && fal.SagInfo.TERMN_TRAF_AREA_CD != null)
        {
            serviceLocationBuilder.setLocalProviderServingOfficeCode(fal.SagInfo.NPA_LST[0]
                    + fal.SagInfo.TERMN_TRAF_AREA_CD);
            // set PrimaryNpaNxx
            if ((fal.PrmAddr.BascAddrInfo.STATE_CD != null)
                    && (fal.PrmAddr.BascAddrInfo.STATE_CD.equalsIgnoreCase(State.State_Arkansas)
                            || fal.PrmAddr.BascAddrInfo.STATE_CD.equalsIgnoreCase(State.State_Kansas)
                            || fal.PrmAddr.BascAddrInfo.STATE_CD.equalsIgnoreCase(State.State_Missouri)
                            || fal.PrmAddr.BascAddrInfo.STATE_CD.equalsIgnoreCase(State.State_Oklahoma) || fal.PrmAddr.BascAddrInfo.STATE_CD
                            .equalsIgnoreCase(State.State_Texas)))
            {
                serviceLocationBuilder.setPrimaryNpaNxx(validNpaNxx(fal.SagInfo.NPA_LST[0]
                        + fal.SagInfo.TERMN_TRAF_AREA_CD));
            }
            else
            {
                serviceLocationBuilder.setPrimaryNpaNxx(fal.SagInfo.NPA_LST[0] + fal.SagInfo.TERMN_TRAF_AREA_CD);
            }
        }

        // Set QuickDialTone
        String tempTN = "";
        if (fal.PrmAddr.BascAddrInfo.STATE_CD.equalsIgnoreCase(State.State_California))
        {
            for (int i = 0; i < fal.LnData.length; i++)
            {
                String mwi = fal.LnData[i].MODULR_WIRE_ID;

                if (mwi != null && mwi.length() >= 12 && Character.isDigit(mwi.charAt(0))
                        && Character.isDigit(mwi.charAt(1)) && Character.isDigit(mwi.charAt(2)))
                {
                    tempTN = (mwi.substring(0, 3) + mwi.substring(4, 7) + mwi.substring(8, 12));
                }
            }

            if (tempTN.length() > 0)
            {
                serviceLocationBuilder.setQuickDialTone(PREMISTag.YES);
                serviceLocationBuilder.setQuickDialToneNumber(tempTN);
            }
            else
            {
                serviceLocationBuilder.setQuickDialTone(PREMISTag.NO);
            }
        }

        // Set CommunityName
        if ((fal.PrmAddr.BascAddrInfo.STATE_CD != null)
                && (fal.PrmAddr.BascAddrInfo.STATE_CD.equalsIgnoreCase(State.State_California) || fal.PrmAddr.BascAddrInfo.STATE_CD
                        .equalsIgnoreCase(State.State_Nevada)))
        {
            serviceLocationBuilder.setCommunityName(fal.ALT_CMTY_1_NM);
        }

        // Set ServingAreaDescription
        if (getServingArea() != null && getServingArea().length() > 0)
        {
            StringBuffer servingArea = new StringBuffer(PREMISTag.NON_SBC);
            servingArea.append(getServingArea());
            serviceLocationBuilder.setServingAreaDescription(servingArea.toString());
        }

        // Set RateZone
        if ((fal.PrmAddr.BascAddrInfo.STATE_CD != null)
                && (fal.PrmAddr.BascAddrInfo.STATE_CD.equalsIgnoreCase(State.State_Arkansas)
                        || fal.PrmAddr.BascAddrInfo.STATE_CD.equalsIgnoreCase(State.State_Kansas)
                        || fal.PrmAddr.BascAddrInfo.STATE_CD.equalsIgnoreCase(State.State_Missouri)
                        || fal.PrmAddr.BascAddrInfo.STATE_CD.equalsIgnoreCase(State.State_Oklahoma) || fal.PrmAddr.BascAddrInfo.STATE_CD
                        .equalsIgnoreCase(State.State_Texas)))
        {
            serviceLocationBuilder.setRateZone(fal.SagInfo.RT_ZONE_CD);
        }

        // populate ServiceLocation value returned to client
        utility.locServiceLocation = serviceLocationBuilder.getCachedServiceLocation();
        serviceLocation = serviceLocationBuilder.getServiceLocation();
    }

    /**
     * If the TN in the SupLnInfo_t object is working, add it to the TN list.
     * Creation date: (3/15/01 8:45:16 AM)
     * @param suppLnInfo SuppLnInfo_t
     */

    protected void addWorkingTn(SuppLnInfo_t suppLnInfo)
    {
        if (PREMIS.lineIsWorking(suppLnInfo.LtdLnInfo))
        {
            addWorkingTn(suppLnInfo.NpaPrfxLn.NPA, suppLnInfo.NpaPrfxLn.PRFX_CD, suppLnInfo.NpaPrfxLn.LN);
        }
    }

    /**
     * Compares two objects for equality. Returns a boolean that indicates
     * whether this object is equivalent to the specified object. This method is
     * used when an object is stored in a hashtable.
     * @param obj the Object to compare with
     * @return true if these Objects are equal; false otherwise.
     * @see java.util.Hashtable
     */
    public boolean equals(Object obj)
    {
        if (obj instanceof PremisHit)
        {
            return hashString().equals(((PremisHit) obj).hashString());
        }
        return false;
    }

    /**
     * returns Application PREMIS response structure. Creation date: (3/26/02
     * 12:15:44 PM)
     * @return AppPrmRespItem_t
     */

    public AppPrmRespItem_t getAppPremisResp()
    {
        return apr;
    }

    /**
     * Returns the additionalAddressInfo.
     * @return String
     */
    public String getAdditionalAddressInfo()
    {

        return additionalAddressInfo;
    }

    /**
     * Getter method for the string servingArea. Creation date: (5/21/02
     * 10:00:33 AM)
     * @return String
     * @see #setServingArea
     */
    public String getServingArea()
    {
        return servingArea.trim();
    }

    /**
     * Getter method for the boolean stNmInd. Creation date: (8/10/01 8:11:51
     * AM)
     * @return boolean
     * @see #setStNmInd(boolean)
     */
    public boolean getStNmInd()
    {
        return stNmInd;
    }

    /**
     * Generates a hash code for the receiver. This method is supported
     * primarily for hash tables, such as those provided in java.util.
     * @return an integer hash code for the receiver
     * @see java.util.Hashtable
     */
    public int hashCode()
    {
        return hashString().hashCode();
    }

    /**
     * Return a String to be used by methods equals() and hashCode(). Creation
     * date: (3/13/01 3:11:37 PM)
     * @return java.lang.String
     */
    protected String hashString()
    {
        return (fal.PrmAddr.BascAddrInfo.StNbrId.SAD_HOUS_PRFX + fal.PrmAddr.BascAddrInfo.StNbrId.SAD_HOUS_NBR
                + fal.PrmAddr.BascAddrInfo.StNbrId.SAD_HOUS_NBR_SUFX + fal.PrmAddr.BascAddrInfo.ASGND_HOUS_NBR_ID
                + fal.PrmAddr.BascAddrInfo.StNm.SAD_ST_DRCTL + fal.PrmAddr.BascAddrInfo.StNm.SAD_ST_NM
                + fal.PrmAddr.BascAddrInfo.StNm.SAD_THRFR + fal.PrmAddr.BascAddrInfo.StNm.SAD_ST_SUFX
                + fal.PrmAddr.BascAddrInfo.CMTY_NM + fal.PrmAddr.BascAddrInfo.STATE_CD
                + fal.PrmAddr.SuppAddrInfo.STRUCT_TYPE + fal.PrmAddr.SuppAddrInfo.STRUCT_ID
                + fal.PrmAddr.SuppAddrInfo.ELEV_TYPE + fal.PrmAddr.SuppAddrInfo.ELEV_ID
                + fal.PrmAddr.SuppAddrInfo.UNIT_TYPE + fal.PrmAddr.SuppAddrInfo.UNIT_ID + fal.DESC_ADDR + fal.SagInfo.ZC);
    }

    /**
     * Sets the additionalAddressInfo.
     * @param additionalAddressInfo The additionalAddressInfo to set
     */
    public void setAdditionalAddressInfo(String additionalAddressInfo)
    {
        this.additionalAddressInfo = additionalAddressInfo;
    }

    /**
     * Construct and store an AddressHandler from the ASON Hit data. Creation
     * date: (3/7/01 10:02:01 AM)
     */
    protected AddressHandlerPremis getServiceAddress()
    {

        return (new AddressHandlerPremis(apr.CntlData.Addr.UnnbrdAddrIdent.PSTL_RTE_CD.trim(),
                apr.CntlData.Addr.UnnbrdAddrIdent.BOX_CD.trim(), fal.PrmAddr.BascAddrInfo.StNbrId.SAD_HOUS_PRFX.trim(),
                fal.PrmAddr.BascAddrInfo.StNbrId.SAD_HOUS_NBR.trim(),
                fal.PrmAddr.BascAddrInfo.ASGND_HOUS_NBR_ID.trim(), fal.PrmAddr.BascAddrInfo.StNbrId.SAD_HOUS_NBR_SUFX
                        .trim(), fal.PrmAddr.BascAddrInfo.StNm.SAD_ST_DRCTL.trim(),
                fal.PrmAddr.BascAddrInfo.StNm.SAD_ST_NM.trim(), fal.PrmAddr.BascAddrInfo.StNm.SAD_THRFR.trim(),
                fal.PrmAddr.BascAddrInfo.StNm.SAD_ST_SUFX.trim(), fal.PrmAddr.BascAddrInfo.CMTY_NM.trim(),
                fal.PrmAddr.BascAddrInfo.STATE_CD.trim(), fal.SagInfo.ZC.trim(), PREMISTag.EMPTY_STRING, //county
                PREMISTag.EMPTY_STRING, //country
                fal.PrmAddr.SuppAddrInfo.STRUCT_TYPE, fal.PrmAddr.SuppAddrInfo.STRUCT_ID,
                fal.PrmAddr.SuppAddrInfo.ELEV_TYPE, fal.PrmAddr.SuppAddrInfo.ELEV_ID,
                fal.PrmAddr.SuppAddrInfo.UNIT_TYPE, fal.PrmAddr.SuppAddrInfo.UNIT_ID, getAdditionalAddressInfo(),
                getStNmInd()));
    }

    /**
     * Setter method for the string servingArea. Creation date: (5/21/02
     * 10:00:33 AM)
     * @param newServingArea String
     * @see #getServingArea
     */
    public void setServingArea(String newServingArea)
    {
        servingArea = newServingArea;
    }

    /**
     * Setter method for the boolean stNmInd. Creation date: (8/10/01 8:11:51
     * AM)
     * @param newStNmInd boolean
     * @see #getStNmInd
     */
    public void setStNmInd(boolean newStNmInd)
    {
        stNmInd = newStNmInd;
    }

    /**
     * Returns a String that represents the value of this object.
     * @return a string representation of the receiver
     */
    public String toString()
    {
        final String nl = System.getProperty("line.separator", "\n");
        StringBuffer sb = new StringBuffer(nl + "PremisHit[ ");

        try
        {
            sb.append(new AddressHandler(LIMIDLUtil.convertAddress2ToAddress(addressData.aServiceAddress)).toString());
        }
        catch (AddressHandlerException e)
        {
        }
        catch (org.omg.CORBA.BAD_OPERATION e)
        {
        }
        catch (java.lang.NullPointerException e)
        {
        }

        try
        {
            sb.append(nl + "CommunityName[" + addressData.aCommunityName.theValue() + "]");
        }
        catch (org.omg.CORBA.BAD_OPERATION e)
        {
        }
        catch (java.lang.NullPointerException e)
        {
        }

        try
        {
            sb.append(nl + "ExchangeCode[" + addressData.aExchangeCode.theValue() + "]");
        }
        catch (org.omg.CORBA.BAD_OPERATION e)
        {
        }
        catch (java.lang.NullPointerException e)
        {
        }

        try
        {
            sb.append(nl + "Exco[" + addressData.aExco.theValue() + "]");
        }
        catch (org.omg.CORBA.BAD_OPERATION e)
        {
        }
        catch (java.lang.NullPointerException e)
        {
        }

        try
        {
            sb.append(nl + "LataCode[" + addressData.aLataCode.theValue() + "]");
        }
        catch (org.omg.CORBA.BAD_OPERATION e)
        {
        }
        catch (java.lang.NullPointerException e)
        {
        }

        try
        {
            sb.append(nl + "LocalProviderServingOfficeCode[" + addressData.aLocalProviderServingOfficeCode.theValue()
                    + "]");
        }
        catch (org.omg.CORBA.BAD_OPERATION e)
        {
        }
        catch (java.lang.NullPointerException e)
        {
        }

        try
        {
            sb.append(nl + "OwnedWiring[" + addressData.aOwnedWiring.theValue() + "]");
        }
        catch (org.omg.CORBA.BAD_OPERATION e)
        {
        }
        catch (java.lang.NullPointerException e)
        {
        }

        try
        {
            sb.append(nl + "PrimaryDirectoryCode[" + addressData.aPrimaryDirectoryCode.theValue() + "]");
        }
        catch (org.omg.CORBA.BAD_OPERATION e)
        {
        }
        catch (java.lang.NullPointerException e)
        {
        }

        try
        {
            sb.append(nl + "PrimaryNpaNxx[" + addressData.aPrimaryNpaNxx.theValue() + "]");
        }
        catch (org.omg.CORBA.BAD_OPERATION e)
        {
        }
        catch (java.lang.NullPointerException e)
        {
        }

        try
        {
            sb.append(nl + "QuickDialTone[" + addressData.aQuickDialTone.theValue() + "]");
        }
        catch (org.omg.CORBA.BAD_OPERATION e)
        {
        }
        catch (java.lang.NullPointerException e)
        {
        }

        try
        {
            sb.append(nl + "QuickDialToneNumber[" + addressData.aQuickDialToneNumber.theValue() + "]");
        }
        catch (org.omg.CORBA.BAD_OPERATION e)
        {
        }
        catch (java.lang.NullPointerException e)
        {
        }

        try
        {
            sb.append(nl + "RateZone[" + addressData.aRateZone.theValue() + "]");
        }
        catch (org.omg.CORBA.BAD_OPERATION e)
        {
        }
        catch (java.lang.NullPointerException e)
        {
        }

        try
        {
            sb.append(nl + "ServingAreaDescription[" + addressData.aServingAreaDescription.theValue() + "]");
        }
        catch (org.omg.CORBA.BAD_OPERATION e)
        {
        }
        catch (java.lang.NullPointerException e)
        {
        }

        try
        {
            sb.append(nl + "StreetAddressGuideArea[" + addressData.aStreetAddressGuideArea.theValue() + "]");
        }
        catch (org.omg.CORBA.BAD_OPERATION e)
        {
        }
        catch (java.lang.NullPointerException e)
        {
        }

        try
        {
            sb.append(nl + "TarCode[" + addressData.aTarCode.theValue() + "]");
        }
        catch (org.omg.CORBA.BAD_OPERATION e)
        {
        }
        catch (java.lang.NullPointerException e)
        {
        }

        try
        {
            sb.append(nl + "TelephoneNumber[" + addressData.aTelephoneNumber.theValue() + "]");
        }
        catch (org.omg.CORBA.BAD_OPERATION e)
        {
        }
        catch (java.lang.NullPointerException e)
        {
        }

        try
        {
            sb.append(nl + "WorkingServiceOnLocation[" + addressData.aWorkingServiceOnLocation.theValue() + "]");
        }
        catch (org.omg.CORBA.BAD_OPERATION e)
        {
        }
        catch (java.lang.NullPointerException e)
        {
        }

        sb.append(" ]");
        return sb.toString();
    }

    /**
     * validNpaNxx contains a list of Fictitious NPA-TTA (first column) and a
     * list of Valid NPA-NXX (second column) for Southwest region. If the
     * Fictitious NPA-TTA was found, the function returns the Valid NPA-NXX.
     * Creation date: (2/24/04 3:01:03 PM)
     * @param fictitiousNpaNxx String
     * @return String
     */
    private String validNpaNxx(String fictitiousNpaNxx)
    {
        final String[][] npa_nxx_list = { { "573220", "573222" }, { "573360", "573365" }, { "785001", "785256" },
                { "785002", "785256" }, { "785003", "785836" }, { "785004", "785836" }, { "785008", "785363" },
                { "785050", "785263" }, { "918001", "918944" } };

        for (int i = 0; i < npa_nxx_list.length; i++)
        {
            if (npa_nxx_list[i][0].equals(fictitiousNpaNxx))
            {
                return npa_nxx_list[i][1];
            }
        }
        return fictitiousNpaNxx;
    }
}
