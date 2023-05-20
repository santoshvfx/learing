//$Id: INQFASGRequestHelper.java,v 1.1.2.2 2011/04/05 23:30:41 jr5306 Exp $
//############################################################################
//#
//# Copyright Notice:
//#
//#     RESTRICTED - PROPRIETARY INFORMATION
//#      The information herein is for use only by authorized employees
//#      of AT&T Services, Inc. and authorized Affiliates of AT&T Services,
//#      Inc., and is not for general distribution within or outside the
//#      respective companies.
//#      Copying or reproduction without prior written approval is prohibited.
//#
//#      Copyright © 2002-2005 AT&T Knowledge Ventures.
//#      All rights reserved.
//#
//# History :
//# Date      |  Author              | Notes
//# --------------------------------------------------------------------
//# 07/20/2007   Rene Duka             Creation.
//# 11/06/2007   Rene Duka             RM 410745: Project Lightspeed - Release 6.0.
//# 11/28/2007   Rene Duka             Defect 79174: Format STR using only getStreetName().
//# 10/28/2008   Lira Galsim           PR23245380: Fixed the concatenation of house number prefix, house number, and house number suffix for the LFACS's BAD field.

package com.sbc.eia.bis.BusinessInterface.ason;

import java.util.Hashtable;
import java.util.StringTokenizer;

import com.sbc.bccs.utilities.Utility;
import com.sbc.eia.bis.embus.service.facsrc.InqfasgRequest.impl.INQFASGTypeImpl.REQTypeImpl;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.idl.lim.helpers.AddressHandlerException;
import com.sbc.eia.idl.lim.helpers.AddressHandlerLFACS;
import com.sbc.eia.idl.lim_types.Address;
import com.sbc.gwsvcs.service.facsaccess.interfaces.Fasg_Inq_Req_t;

/**
 * Class      : INQFASGRequestHelper
 * Description: Helper used for handling the request of the INQFASG contract of FACSRCAccess.  
 */
public class INQFASGRequestHelper 
{
    protected REQTypeImpl aRequest;

    /**
     * Constructor: INQFASGRequestHelper
     * 
     * @author Rene Duka
     */
    public INQFASGRequestHelper() 
    {
        // initialize request
        aRequest = new REQTypeImpl();
        
        aRequest.setEMP("");
        aRequest.setBAD("");
        aRequest.setSTR("");            
        aRequest.setCNA("");
        aRequest.setUID("");
        aRequest.setEID("");
        aRequest.setSID("");
        aRequest.setSTN("");
        aRequest.setCKID("");
        aRequest.setTID("");
        aRequest.setCA("");
        aRequest.setPR("");

        aRequest.setASGBP("");
        aRequest.setDESTIN("");
        aRequest.setJACK("");
        aRequest.setNPORT("");
        aRequest.setPND("");
        aRequest.setPORT("");
        aRequest.setPOS("");
        aRequest.setTEA("");
        aRequest.setWW("");
    }

    /**
     * Constructor: INQFASGRequestHelper
     * 
     * @param Utility   utility
     * @param Hashtable properties
     * 
     * @author Rene Duka
     */
    public INQFASGRequestHelper (
        Utility aUtility, 
        Hashtable aProperties,
        AsonHitResp hitResp) 
    {
    	Fasg_Inq_Req_t q =hitResp.getFACSQueryReq();
    	
        try 
        {
        	aRequest = new REQTypeImpl();
            // set EMP
            aRequest.setEMP(q.INQFASG.EMP);
            // use LFACS Address Handler from LIM BIS
            //aLFACSAddress = new AddressHandlerLFACS(aFacilityAddress);
            // set BAD
            // Concatenate: HouseNumberPrefix + " " + HouseNumber + "-" + HouseNumberSuffix
            aRequest.setBAD(q.INQFASG.BAD);
            // set STR
            // Concatenate: StreetDirection + StreetName + StreetThoroughfare + StreetSuffix
            //              Note that the concatenation is being done in the getStreetName() method.
            aRequest.setSTR(q.INQFASG.STR);
            
            // set CNA : City
            aRequest.setCNA(q.INQFASG.CNA);
            // set UID : Unit Value
            aRequest.setUID(q.INQFASG.UID);
            // set EID : Elevation Value
            aRequest.setEID(q.INQFASG.EID);
            // set SID : Structure Value
            aRequest.setSID(q.INQFASG.SID);
            // set STN : State
            aRequest.setSTN(q.INQFASG.STN);
        }
        catch (Exception e) 
        {
            String aExceptionMessage = "Error in building REQTypeImpl" + " - "
                                            + e.getMessage() + " - "
                                            + "IGNORED: Ok to continue.";

            aUtility.log(LogEventId.DEBUG_LEVEL_2, aExceptionMessage);
        }
    }

    /**
     * Constructor: INQFASGRequestHelper
     * 
     * @param Utility   aUtility
     * @param Hashtable aProperties
     * @param String    aCircuitID
     * @param String    aTerminalID
     * @param String    aCablePair
     * 
     * @author Rene Duka
     */
    public INQFASGRequestHelper (
        Utility aUtility, 
        Hashtable aProperties,
        String aCircuitID,
        String aTerminalID,
        String aCablePair) 
    {
        this();
        try 
        {
            // set EMP
            aRequest.setEMP((String) aProperties.get("BIS_NAME"));
            // aCircuitID
            if (aCircuitID != null && !aCircuitID.equalsIgnoreCase("NONE")) 
            {
                // set CKID : aCircuitID
                aRequest.setCKID(aCircuitID);
            }
            // aTerminalID
            if (aTerminalID != null) 
            {
                // set TID : aTerminalID
                aRequest.setTID(aTerminalID);
            }            
            // aCablePair (CA:PR)
            if (aCablePair != null) 
            {
                StringTokenizer st = new StringTokenizer(aCablePair, ":");                
                // set CA : CA value of aCablePair
                if (st.hasMoreElements())
                    aRequest.setCA(st.nextToken().trim());
                // set PR : PR value of aCablePair
                if (st.hasMoreElements())
                    aRequest.setPR(st.nextToken().trim());
            }            
        }
        catch (Exception e) 
        {
            String aExceptionMessage = "Error in building REQTypeImpl" + " - "
                                    + e.getMessage() + " - "
                                    + "IGNORED: Ok to continue.";

            aUtility.log(LogEventId.DEBUG_LEVEL_2, aExceptionMessage);
        }
    }
   
    /**
     * Get the request.
     * 
     * @return REQTypeImpl
     * 
     * @author Rene Duka
     */
    public REQTypeImpl getRequest() 
    {
        return aRequest;
    }
}
