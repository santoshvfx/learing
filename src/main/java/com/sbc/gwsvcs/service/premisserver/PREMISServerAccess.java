// $Id: PREMISServerAccess.java,v 1.1 2002/09/29 04:28:09 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver;

import com.sbc.vicunalite.api.*;
import com.sbc.vicunalite.api.orb.*;
import java.io.*;
import com.sbc.gwsvcs.service.premisserver.exceptions.*;
import com.sbc.gwsvcs.service.premisserver.interfaces.*;
import com.sbc.gwsvcs.access.vicuna.*;
import com.sbc.gwsvcs.access.vicuna.exceptions.*;

/**
 * Wraps the service side for access to the PREMISServer service.
 * Creation date: (4/16/00 11:36:38 AM)
 * @author: Creighton Malet
 */
public class PREMISServerAccess extends ServiceAccess
{
	public final static String version = "6.1";
	public final static String name = "PREMISSERVER";

	public final static int PREMIS_VALDT_ADDR_REQ_NBR 			= PREMISSERVER_Const.PremisValdtAddrReq;
	public final static int PREMIS_HIT_RESP_NBR 				= PREMISSERVER_Const.PremisHITResp;
	public final static int PREMIS_SAGA_MENU_RESP_NBR 			= PREMISSERVER_Const.PremisSagaMenuResp;
	public final static int PREMIS_ZIP_MENU_RESP_NBR 			= PREMISSERVER_Const.PremisZipMenuResp;
	public final static int PREMIS_ST_NM_MENU_RESP_NBR 			= PREMISSERVER_Const.PremisStNmMenuResp;
	public final static int PREMIS_DESC_NM_MENU_RESP_NBR 		= PREMISSERVER_Const.PremisDescNmMenuResp;
	public final static int PREMIS_ADDR_RNGE_MENU_RESP_NBR 		= PREMISSERVER_Const.PremisAddrRngeMenuResp;
	public final static int PREMIS_ADDR_MENU_RESP_NBR 			= PREMISSERVER_Const.PremisAddrMenuResp;
	public final static int PREMIS_ST_ADDR_RANGE_MENU_RESP_NBR	= PREMISSERVER_Const.PremisStAddrRngeMenuResp;
	public final static int PREMIS_UNNBRD_MENU_RESP_NBR 		= PREMISSERVER_Const.PremisUnnbrdMenuResp;
	public final static int PREMIS_BASC_ADDR_MENU_RESP_NBR		= PREMISSERVER_Const.PremisBascAddrMenuResp;
	public final static int PREMIS_UNADRM_MENU_RESP_NBR 		= PREMISSERVER_Const.PremisUnadrmMenuResp;
	public final static int PREMIS_GSGM_MENU_RESP_NBR 			= PREMISSERVER_Const.PremisGsgmMenuResp;
	public final static int PREMIS_TN_MATCH_MENU_RESP_NBR 		= PREMISSERVER_Const.PremisTnMatchMenuResp;
	public final static int PREMIS_SUPP_ADDR_MENU_RESP_NBR 		= PREMISSERVER_Const.PremisSuppAddrMenuResp;
	public final static int PREMIS_UNADRM_GSGM_MENU_RESP_NBR 	= PREMISSERVER_Const.PremisUnadrmGsgmResp;

	public final static int PREMIS_TN_RSRV_REQ_NBR 			= PREMISSERVER_Const.PremisTnRsrvReq;
	public final static int PREMIS_TN_RSRV_RESP_NBR 		= PREMISSERVER_Const.PremisTnRsrvResp;
	public final static int PREMIS_TN_RSRV_TCAT_RESP_NBR 	= PREMISSERVER_Const.PremisTnRsrvTCATResp;
	public final static int PREMIS_TN_RSRV_ADDLN_RESP_NBR 	= PREMISSERVER_Const.PremisTnRsrvADDLNResp;
	
	public final static int PREMIS_TN_SAVE_REQ_NBR 			= PREMISSERVER_Const.PremisTnSaveReq;
	public final static int PREMIS_TN_SAVE_RESP_NBR 		= PREMISSERVER_Const.PremisTnSaveResp;
	public final static int PREMIS_TN_SAVE_ADDLN_RESP_NBR 	= PREMISSERVER_Const.PremisTnSaveADDLNResp;
	
	public final static int PREMIS_TN_RETN_REQ_NBR 	= PREMISSERVER_Const.PremisTnRetnReq;
	public final static int PREMIS_TN_RETN_RESP_NBR = PREMISSERVER_Const.PremisTnRetnResp;

	public final static int PREMIS_TN_STA_REQ_NBR 	= PREMISSERVER_Const.PremisTnStaReq;
	public final static int PREMIS_TN_STA_RESP_NBR  = PREMISSERVER_Const.PremisTnStaResp;
	
	public final static int PREMIS_TN_MTT_REQ_NBR 	= PREMISSERVER_Const.PremisTnMttReq;
	public final static int PREMIS_TN_MTT_RESP_NBR  = PREMISSERVER_Const.PremisTnMttResp;
		
	public final static int EXCEPTION_NBR = PREMISSERVER_Const.ExceptionResp;
	
	public final static MEventType PREMIS_VALDT_ADDR_REQ = 			new MEventType("PREMIS_VALDT_ADDR_REQ");			// Event 6000
	public final static MEventType PREMIS_HIT_RESP = 				new MEventType("PREMIS_HIT_RESP");					// Event 6001
	public final static MEventType PREMIS_SAGA_MENU_RESP = 			new MEventType("PREMIS_SAGA_MENU_RESP");			// Event 6002
	public final static MEventType PREMIS_ZIP_MENU_RESP = 			new MEventType("PREMIS_ZIP_MENU_RESP");				// Event 6003
	public final static MEventType PREMIS_ST_NM_MENU_RESP = 		new MEventType("PREMIS_ST_NM_MENU_RESP");			// Event 6004
	public final static MEventType PREMIS_DESC_NM_MENU_RESP = 		new MEventType("PREMIS_DESC_NM_MENU_RESP");			// Event 6015
	public final static MEventType PREMIS_ADDR_RNGE_MENU_RESP = 	new MEventType("PREMIS_ADDR_RNGE_MENU_RESP");		// Event 6006
	public final static MEventType PREMIS_ADDR_MENU_RESP = 			new MEventType("PREMIS_ADDR_MENU_RESP");			// Event 6007
	public final static MEventType PREMIS_ST_ADDR_RANGE_MENU_RESP =	new MEventType("PREMIS_ST_ADDR_RANGE_MENU_RESP");	// Event 6008
	public final static MEventType PREMIS_UNNBRD_MENU_RESP = 		new MEventType("PREMIS_UNNBRD_MENU_RESP");			// Event 6009
	public final static MEventType PREMIS_BASC_ADDR_MENU_RESP = 	new MEventType("PREMIS_BASC_ADDR_MENU_RESP");		// Event 6010
	public final static MEventType PREMIS_UNADRM_MENU_RESP = 		new MEventType("PREMIS_UNADRM_MENU_RESP");			// Event 6011
	public final static MEventType PREMIS_GSGM_MENU_RESP = 			new MEventType("PREMIS_GSGM_MENU_RESP");			// Event 6012
	public final static MEventType PREMIS_TN_MATCH_MENU_RESP = 		new MEventType("PREMIS_TN_MATCH_MENU_RESP");		// Event 6013
	public final static MEventType PREMIS_SUPP_ADDR_MENU_RESP = 	new MEventType("PREMIS_SUPP_ADDR_MENU_RESP");		// Event 6014
	public final static MEventType PREMIS_UNADRM_GSGM_MENU_RESP = 	new MEventType("PREMIS_UNADRM_GSGM_MENU_RESP");		// Event 6015

	public final static MEventType PREMIS_TN_RSRV_REQ = 			new MEventType("PREMIS_TN_RSRV_REQ");				// Event 6030
	public final static MEventType PREMIS_TN_RSRV_RESP = 			new MEventType("PREMIS_TN_RSRV_RESP");				// Event 6031
	public final static MEventType PREMIS_TN_RSRV_TCAT_RESP = 		new MEventType("PREMIS_TN_RSRV_TCAT_RESP");			// Event 6032
	public final static MEventType PREMIS_TN_RSRV_ADDLN_RESP = 		new MEventType("PREMIS_TN_RSRV_ADDLN_RESP");		// Event 6033
	
	public final static MEventType PREMIS_TN_SAVE_REQ = 			new MEventType("PREMIS_TN_SAVE_REQ");				// Event 6040
	public final static MEventType PREMIS_TN_SAVE_RESP = 			new MEventType("PREMIS_TN_SAVE_RESP");				// Event 6041
	public final static MEventType PREMIS_TN_SAVE_ADDLN_RESP = 		new MEventType("PREMIS_TN_SAVE_ADDLN_RESP");		// Event 6042
	
	public final static MEventType PREMIS_TN_RETN_REQ = 			new MEventType("PREMIS_TN_RETN_REQ");				// Event 6050
	public final static MEventType PREMIS_TN_RETN_RESP = 			new MEventType("PREMIS_TN_RETN_RESP");				// Event 6051
		
	public final static MEventType PREMIS_TN_STA_REQ = 				new MEventType("PREMIS_TN_STA_REQ");				// Event 6060
	public final static MEventType PREMIS_TN_STA_RESP = 			new MEventType("PREMIS_TN_STA_RESP");				// Event 6061
		
	public final static MEventType PREMIS_TN_MTT_REQ = 				new MEventType("PREMIS_TN_MTT_REQ");				// Event 6070
	public final static MEventType PREMIS_TN_MTT_RESP = 			new MEventType("PREMIS_TN_MTT_RESP");				// Event 6071
		
	public final static MEventType EXCEPTION = 						new MEventType("EXCEPTION");						// Event 9999
/**
 * Constructor accepting Vicuna configuration file, directory for configuration files and a Logger.
 * @exception com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException: an exception occurred.
 */
public PREMISServerAccess(String vicunaXmlFile, String serviceXmlDir, com.sbc.bccs.utilities.Logger aLogger) throws ServiceException
{
	super(version, name, 30000, vicunaXmlFile, serviceXmlDir, aLogger);
}
}
