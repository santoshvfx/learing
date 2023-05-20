// $Id: LIMBisContextManager.java,v 1.1 2002/09/29 02:28:30 dm2328 Exp $

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
 *  This file contains the LIMBisContextManager used to retrieve LIM BIS
 *  specific properties form the BisContext.
 *  Description
 */

package com.sbc.eia.bis.lim.util;

import com.sbc.eia.bis.framework.BisContextManager;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.bis_types.BisContextProperty;

/**
 * The LIMBisContextManager provides a convenient utility for retrieving
 * LIM BIS specific properties from a BisContext object..
 * 
 * @author David Brawley
 */
public class LIMBisContextManager extends BisContextManager
{
    /**
     * Default constructor for LIMBisContextManager
     */
    public LIMBisContextManager()
    {
        super();
    }

    /**
     * Constructor for LIMBisContextManager
     */
    public LIMBisContextManager(BisContext i_bisContext)
    {
        super(i_bisContext);
    }

    /**
     * @return a String that can be passed to OvalsUsps
     *   to limit the length of the CASS Address Lines returned.
     */
    public String getAddressCassMaximumLengthPerLine()
    {
        return getObjectPropertyManager().getValue(
            BisContextProperty.MAXCASSCHARPERLINE);
    }

    /**
     * Method setAddressCassMaximumLengthPerLine.
     * @param i_addressCassMaximumLengthPerLine
     */
    public void setAddressCassMaximumLengthPerLine(
        String i_addressCassMaximumLengthPerLine)
    {
        getObjectPropertyManager().add(
            BisContextProperty.MAXCASSCHARPERLINE,
            i_addressCassMaximumLengthPerLine);
    }

    /**
     * @return a string naming the client of the BIS.
     * @roseuid 3ADB2B7501FA
     */
    public String getBusinessUnit()
    {
        return getObjectPropertyManager().getValue(
            BisContextProperty.BUSINESSUNIT);
    }
    
	/**
	 * @return a string routing override key of the BIS.
	 */
	public String getRoutingKey()
	{
		return getObjectPropertyManager().getValue(
			BisContextProperty.ROUTING);
	}

    /**
     * Method getCustomerType.
     * @return String
     */
    public String getCustomerType()
    {
        return getObjectPropertyManager().getValue(
            BisContextProperty.CUSTOMERTYPE);
    }

    /**
     * Method getConversationId.
     * @return String
     */
    public String getConversationId()
    {
        return getObjectPropertyManager().getValue(
            BisContextProperty.CONVERSATIONID);
    }

    /**
     * Method getSagValidationRequest.
     * @return String
     */
    public String getSagValidationRequest()
    {
        return getObjectPropertyManager().getValue(
        	BisContextProperty.SAGVALIDATIONREQUEST);
    }

    /**
     * Get RacfPassword Property from BisContext
     * Creation date: (4/05/04 12:03:17 PM)
     * @return java.lang.String
     */
    public String getRacfPassword()
    {
        return getObjectPropertyManager().getValue(
            BisContextProperty.RACFPASSWORD);
    }

    /**
     * Get RacfId Property from BisContext
     * Creation date: (4/05/04 12:03:17 PM)
     * @return java.lang.String
     */
    public String getRacfId()
    {
        return getObjectPropertyManager().getValue(
            BisContextProperty.RACFID);
    }

    /**
     * Method getRacfSessionToken.
     * @return String
     */
    public String getRacfSessionToken()
    {
        return getObjectPropertyManager().getValue(
            BisContextProperty.RACFSESSIONTOKEN);
    }

    /**
     * Method getBossTypistId.
     * @return String
     */
    public String getBossTypistId()
    {
        return getObjectPropertyManager().getValue(
            BisContextProperty.BOSSTYPISTID);
    }

    /**
     * Method getBossMaskedTypist.
     * @return String
     */
    public String getBossMaskedTypist()
    {
        return getObjectPropertyManager().getValue(
            BisContextProperty.MASKEDTYPIST);
    }

    /**
     * Method getMessage.
     * @return String
     */
    public String getMessage()
    {
        return getObjectPropertyManager().getValue(
            BisContextProperty.MESSAGE);
    }

    /**
     * Method getMessageCode.
     * @return String
     */
    public String getMessageCode()
    {
        return getObjectPropertyManager().getValue(
            BisContextProperty.MESSAGECODE);
    }

    /**
     * Method getPENDING_TROUBLE_METHOD.
     * @return String
     */
    public String getPENDING_TROUBLE_METHOD()
    {
        return getObjectPropertyManager().getValue(
            BisContextProperty.PENDING_TROUBLE_METHOD);
    }

    /**
     * Method getLogPrefix.
     * @return String
     */
    public String getLogPrefix()
    {
        return getObjectPropertyManager().getValue(
            BisContextProperty.LOG_PREFIX);
    }

    /**
     * Method getPadTrunkId.
     * @return String
     */
    /**
     * @roseuid 3ADB2BA203A3
     */
    public String getPadTrunkId()
    {
        return getObjectPropertyManager().getValue(
            BisContextProperty.PAD_TRUNK_ID);
    }

    /**
     * Method getUnpadTrunkId.
     * @return String
     */
    public String getUnpadTrunkId()
    {
        return getObjectPropertyManager().getValue(
            BisContextProperty.UNPAD_TRUNK_ID);
    }

    /**
     * Method getBlankAuthorizationList.
     * @return String
     */
    public String getBlankAuthorizationList()
    {
        return getObjectPropertyManager().getValue(
            BisContextProperty.BLANK_AUTHORIZATIONLIST);
    }

    /**
     * Method getFunctionFilter.
     * @return String
     */
    public String getFunctionFilter()
    {
        return getObjectPropertyManager().getValue(
            BisContextProperty.FUNCTION_FILTER);
    }

    /**
     * Method getCANCEL_REQ.
     * @return String
     */
    public String getCANCEL_REQ()
    {
        return getObjectPropertyManager().getValue(
            BisContextProperty.CANCEL_REQ);
    }

    /**
     * Method getAUTH_DISPATCH_REQ.
     * @return String
     */
    public String getAUTH_DISPATCH_REQ()
    {
        return getObjectPropertyManager().getValue(
            BisContextProperty.AUTH_DISPATCH_REQ);
    }

    /**
     * Method getAUTH_DISPATCH_REQ.
     * @return String
     */
    public String getUseRsagProductionEnvironment()
    {
        return getObjectPropertyManager().getValue(
            LIMTag.USE_RSAG_PRODUCTION_ENVIRONMENT);
    }
    
    /**
     * Method getBypassBossNotesPosting.
     * @return String
     */
    public String getBypassBossNotesPosting()
    {
        return getObjectPropertyManager().getValue(
            BisContextProperty.BYPASS_BOSS_NOTES_POSTING);
    }

    /**
     * Method getBypassBossNotesPosting.
     * @return String
     */
    public String getBossNoteText()
    {
        return getObjectPropertyManager().getValue(
            "BOSSNoteText");
    }
    
    /**
     * Method setBusinessUnit.
     * @param i_application  a string naming the client of the BIS
     */
    public void setBusinessUnit(String i_businessUnit)
    {

        getObjectPropertyManager().add(
            BisContextProperty.BUSINESSUNIT,
            i_businessUnit);

    }
    
    /**
     * Method setRacfSessionToken.
     * @param i_conversationId
     */
    public void setConversationId(String i_conversationId)
    {
        getObjectPropertyManager().add(
            BisContextProperty.CONVERSATIONID,
            i_conversationId);
    }

    /**
     * Method setCustomerType.
     * @param i_customerType
     */
    public void setCustomerType(String i_customerType)
    {
        getObjectPropertyManager().add(
            BisContextProperty.CUSTOMERTYPE,
            i_customerType);
    }

    /**
     * Method setRacfId.
     * @param i_racfId
     */
    public void setRacfId(String i_racfId)
    {
        getObjectPropertyManager().add(BisContextProperty.RACFID, i_racfId);
    }

    /**
     * Method setRacfPassword.
     * @param i_racfPassword
     */
    public void setRacfPassword(String i_racfPassword)
    {
        getObjectPropertyManager().add(
            BisContextProperty.RACFPASSWORD,
            i_racfPassword);
    }

    /**
     * Method setRacfSessionToken.
     * @param i_racfSessionToken
     */
    public void setRacfSessionToken(String i_racfSessionToken)
    {
        getObjectPropertyManager().add(
            BisContextProperty.RACFSESSIONTOKEN,
            i_racfSessionToken);
    }

    /**
     * Method setBossTypistId.
     * @param i_bossTypistId
     */
    public void setBossTypistId(String i_bossTypistId)
    {
        getObjectPropertyManager().add(
            BisContextProperty.BOSSTYPISTID,
            i_bossTypistId);
    }

    /**
     * Method setBossMaskedTypist.
     * @param i_bossMaskedTypist
     */
    public void setBossMaskedTypist(String i_bossMaskedTypist)
    {
        getObjectPropertyManager().add(
            BisContextProperty.MASKEDTYPIST,
            i_bossMaskedTypist);
    }

    /**
     * Method setMessage.
     * @param i_message
     */
    public void setMessage(String i_message)
    {
        getObjectPropertyManager().add(
            BisContextProperty.MESSAGE,
            i_message);
    }

    /**
     * Method setMessageCode.
     * @param i_messageCode
     */
    public void setMessageCode(String i_messageCode)
    {
        getObjectPropertyManager().add(
            BisContextProperty.MESSAGECODE,
            i_messageCode);
    }

    /**
     * Method setPending_Trouble_Method.
     * @param i_pending_trouble_method
     */
    public void setPending_Trouble_Method(String i_pending_trouble_method)
    {
        getObjectPropertyManager().add(
            BisContextProperty.PENDING_TROUBLE_METHOD,
            i_pending_trouble_method);
    }

    /**
     * Method setLogPrefix.
     * @param i_logPrefix
     */
    public void setLogPrefix(String i_logPrefix)
    {
        getObjectPropertyManager().add(
            BisContextProperty.LOG_PREFIX,
            i_logPrefix);
    }

    /**
     * Method setPadTrunkId.
     * @param i_pad_trunk_id
     */
    public void setPadTrunkId(String i_pad_trunk_id)
    {
        getObjectPropertyManager().add(
            BisContextProperty.PAD_TRUNK_ID,
            i_pad_trunk_id);
    }

    /**
     * Method setUnpadTrunkId.
     * @param i_unpad_trunk_id
     */
    public void setUnpadTrunkId(String i_unpad_trunk_id)
    {
        getObjectPropertyManager().add(
            BisContextProperty.UNPAD_TRUNK_ID,
            i_unpad_trunk_id);
    }

    /**
     * Method setBlankAuthorizationList.
     * @param i_blankAuthorizationList
     */
    public void setBlankAuthorizationList(String i_blankAuthorizationList)
    {
        getObjectPropertyManager().add(
            BisContextProperty.BLANK_AUTHORIZATIONLIST,
            i_blankAuthorizationList);
    }

    /**
     * Method setFunctionFilter.
     * @param i_functionFilter
     */
    public void setFunctionFilter(String i_functionFilter)
    {
        getObjectPropertyManager().add(
            BisContextProperty.FUNCTION_FILTER,
            i_functionFilter);
    }

    /**
     * Method setCANCEL_REQ.
     * @param i_cancel_req
     */
    public void setCANCEL_REQ(String i_cancel_req)
    {
        getObjectPropertyManager().add(
            BisContextProperty.CANCEL_REQ,
            i_cancel_req);
    }

    /**
     * Method setAUTH_DISPATCH_REQ.
     * @param i_auth_dispatch_req
     */
    public void setAUTH_DISPATCH_REQ(String i_auth_dispatch_req)
    {
        getObjectPropertyManager().add(
            BisContextProperty.AUTH_DISPATCH_REQ,
            i_auth_dispatch_req);
    }

}
