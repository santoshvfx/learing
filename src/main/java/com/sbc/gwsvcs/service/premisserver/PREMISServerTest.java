// $Id: PREMISServerTest.java,v 1.2 2002/10/17 17:42:17 rg3454 Exp $

package com.sbc.gwsvcs.service.premisserver;

import java.util.*;
import com.sbc.gwsvcs.service.premisserver.exceptions.*;
import com.sbc.gwsvcs.service.premisserver.interfaces.*;
import com.sbc.gwsvcs.access.vicuna.exceptions.*;

/**
 * Provides standalone testing.
 * Creation date: (2/26/01 12:32:12 PM)
 * @author: Creighton Malet
 */
public class PREMISServerTest implements com.sbc.bccs.utilities.Logger {
/**
 * Class constructor.
 */
public PREMISServerTest() {
	super();
}
/**
 * Implementation of Logger.log().
 * Creation date: (2/26/01 12:42:18 PM)
 * @param eventId java.lang.String
 * @param message java.lang.String
 */
public void log(String eventId, String message) {
	System.out.println("LOG: " + eventId + " " + message);
}

public void log(String s1, String s2, String s3, String s4){}

public void log(String s1, String s2, String s3, String s4, String s5){}

/**
 * Executes the program.
 * Creation date: (2/26/01 12:33:23 PM)
 * @param args java.lang.String[]
 */
public static void main(String[] args)
{
	Hashtable props = new Hashtable();
	props.put("APPLDATA", "ccmalet");
	props.put("PREMISSERVER_APPLDATA", "gwsvcTEST");
	props.put("PREMISSERVER_TIMEOUT", "60");
	props.put("GWSVCS_CLNTUUID", "nrmbis");
	props.put("VICUNA_XML_FILE", "vicunalite.xml");
	props.put("VICUNA_SERVICE_CONFIG_DIR", "");
	
	try
	{
		com.sbc.gwsvcs.access.vicuna.EventResultPair response = null;
		PREMISServerTest aPREMISServerTest = new PREMISServerTest();
		PREMISServerHelper helper = new PREMISServerHelper(props, aPREMISServerTest);

		Header_t hdr = new Header_t("BIS", "BIS", "", "", TrnsptType_e.RPC_TRNSPT, "");

		StNbrId_t stNbrId = new StNbrId_t("", "3915", "");
		PrmStNm_t prmStNm = new PrmStNm_t("", "delta fair", "bl", "");
		BascAddrInfo_t bascAddrInfo = new BascAddrInfo_t(stNbrId, "", prmStNm, "", "ca");
		
		SuppAddrInfo_t suppAddrInfo = new SuppAddrInfo_t("", "", "", "", "a7", "apt");
		
		UnnbrdAddrIdent_t unnbrdAddrIdent = new UnnbrdAddrIdent_t("", "", "", "", '\0', '\0');
		
		Addr_t addr = new Addr_t(bascAddrInfo, suppAddrInfo, unnbrdAddrIdent, "");
		
		RapReq_t rapReq = new RapReq_t("eby", "", new NpaPrfxLn_t("", "", ""), addr);

		Scratch_t scratch = new Scratch_t(new char[] { '\0' });

		// Validate address
		// 6001 as is
		// 6002 saga eby -> x
		// 6003 95124 1700 willow creek
		// 6004 89428 427 fairview (sad_st_nm)
		// 6008 bay 120 via loco
		// 6009 bay avenue w (UNNBR_SRCH_STS_IND)
		// 6011 bay avenua a y (ASGND_HOUS_NBR_LSTG_IND)
		// 6014 eby 246 canyon woods wy sn rmn 31 bldg

		// Validate address
		PremisValdtAddrReq_t premisValdtAddrRequest = new PremisValdtAddrReq_t(hdr, rapReq, scratch, "");
//		response = helper.premisValdtAddrReq(null, null, 0, premisValdtAddrRequest);
//		System.out.println("Received event: " + response.getEventNbr());

		// Reserve numbers
		PrmAddr_t prmAddr = new PrmAddr_t(bascAddrInfo, suppAddrInfo);
		TnRsrvReq_t tnRsrvReq = new TnRsrvReq_t("eby", prmAddr, "", "X", "", "", "1RT", 1, '4');
		PremisTnRsrvReq_t premisTnRsrvRequest = new PremisTnRsrvReq_t(hdr, scratch, tnRsrvReq, "");
//		response = helper.premisTnRsrvReq(null, null, 0, premisTnRsrvRequest);
//		System.out.println("Received event: " + response.getEventNbr());

		// Save numbers
		NpaPrfxLn_t npaPrfxLn = new NpaPrfxLn_t("510", "753", "0413");
		TnSaveReq_t tnSaveReq = new TnSaveReq_t("eby", prmAddr, npaPrfxLn, '4');
		PremisTnSaveReq_t premisTnSaveRequest  = new PremisTnSaveReq_t(hdr, scratch, tnSaveReq, "");
//		response = helper.premisTnSaveReq(null, null, 0, premisTnSaveRequest);
//		System.out.println("Received event: " + response.getEventNbr());

		// Return numbers
		NpaPrfxLn_t npaPrfxLnArray[] = new  NpaPrfxLn_t[] { new NpaPrfxLn_t("510", "753", "0413") };
		TnRetnReq_t tnRetnReq = new TnRetnReq_t("eby", prmAddr, "", npaPrfxLnArray, '4');
		PremisTnRetnReq_t premisTnRetnRequest  = new PremisTnRetnReq_t(hdr, scratch, tnRetnReq, "");
//		response = helper.premisTnRetnReq(null, null, 0, premisTnRetnRequest);
//		System.out.println("Received event: " + response.getEventNbr());


		// MTT
		// 1500 MARKET BAY 94102 BAY
		Dt_t soDt = new Dt_t("", "", "");
		TNAMttReq_t tnaMttReq = new TNAMttReq_t("SNFCCA04", "553", "55302", new NpaPrfxLn_t("415", "861", "5512"),
			"1RT", "1500 MARKET", soDt, "", "",	scratch);
		PremisTnMttReq_t premisTnMttRequest  = new PremisTnMttReq_t(hdr, tnaMttReq);
//		response = helper.premisTnMttReq(null, null, 0, premisTnMttRequest);
//		System.out.println("Received event: " + response.getEventNbr());


		// STA
		TNAStaReq_t tnaStaReq = new TNAStaReq_t(new NpaPrfxLn_t("415", "861", "5512"), "", scratch);
		PremisTnStaReq_t premisTnStaRequest  = new PremisTnStaReq_t(hdr, tnaStaReq);
//		response = helper.premisTnStaReq(null, null, 0, premisTnStaRequest);
//		System.out.println("Received event: " + response.getEventNbr());
	}
	catch (PREMISServerException e)
	{
		System.out.println("PREMISServerException: " + e.getExceptionCode() + " " + e.getMessage());
	}
	catch (ServiceException e)
	{
		System.out.println("ServiceException: " + e.getExceptionCode() + " " + e.getMessage());
		e.printStackTraces();
	}
}
}
