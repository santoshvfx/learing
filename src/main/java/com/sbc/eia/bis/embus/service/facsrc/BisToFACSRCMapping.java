//$Id: BisToFACSRCMapping.java,v 1.5 2008/01/17 22:20:04 ml2917 Exp $

//############################################################################
//#
//# Copyright Notice:
//#
//#     RESTRICTED - PROPRIETARY INFORMATION
//#      The information herein is for use only by authorized employees
//#      of SBC Services Inc. and authorized Affiliates of SBC Services,
//#      Inc., and is not for general distribution within or outside the
//#      respective companies.
//#      Copying or reproduction without prior written approval is prohibited.
//#
//#      © 2002-2007 SBC Knowledge Ventures, L.P. All rights reserved.
//#
//# History :
//# Date      | Author              | Notes
//# --------------------------------------------------------------------
//# 01/08/2007  | Manjula Goniguntla	| create
//# 04/05/2007  | Prasad Ganji			| Added EVENTNAME property and Changed the case for WIRECENTER
//
package com.sbc.eia.bis.embus.service.facsrc;

import java.util.Hashtable;
import java.util.Properties;

import com.sbc.eia.bis.framework.BisContextManager;
import com.sbc.eia.idl.bis_types.BisContext;


/**
 * @author mg5629
 */
public class BisToFACSRCMapping {
	public static final String defaultValue = "";

	//properties tags
	public static final String FACS_RC_USERID_TAG = "FACS_RC_USERID";
	public static final String FACS_RC_GROUPID_TAG = "FACS_RC_GROUPID";
	public static final String FACS_RC_CLIENTID_TAG = "FACS_RC_CLIENTID";

	//properties values
	public static final String DESTINATION = "Destination";
	public static final String LOGGING_KEY = "embusLoggingKey";
	public static final String WIRECENTER = "WIRECENTER";
	public static final String EVENTNAME = "EVENTNAME";
	public static final String USER_ID = "USERID";
	public static final String GROUP_ID = "GROUPID";
	public static final String CLIENT_ID = "CLIENTID";

	public static Properties mapProperties(
	    BisContext aContext,
		String destination,
		String wireCenter,
		String eventName,
		Hashtable aRmimBisProperties) {
		Properties aJMSProperties = new Properties();

		aJMSProperties.setProperty(
			DESTINATION,
			destination == null ? defaultValue : destination);
			
		aJMSProperties.setProperty(
			WIRECENTER,
			wireCenter == null ? defaultValue : wireCenter);

		String userID = (String) aRmimBisProperties.get(FACS_RC_USERID_TAG);
		aJMSProperties.setProperty(
			USER_ID,
			userID == null ? defaultValue : userID);

		String groupID = (String) aRmimBisProperties.get(FACS_RC_GROUPID_TAG);
		aJMSProperties.setProperty(
			GROUP_ID,
			groupID == null ? defaultValue : groupID);

		String clientID = (String) aRmimBisProperties.get(FACS_RC_CLIENTID_TAG);
		aJMSProperties.setProperty(
			CLIENT_ID,
			clientID == null ? defaultValue : clientID);

		aJMSProperties.setProperty(
			EVENTNAME,
			eventName == null ? defaultValue : eventName);
		
		BisContextManager aBisManager = new BisContextManager(aContext);
		String loggingKey = aBisManager.getLoggingInformationString();
		aJMSProperties.setProperty(
				LOGGING_KEY,
				loggingKey == null ? defaultValue : loggingKey);

		return aJMSProperties;
	}

}