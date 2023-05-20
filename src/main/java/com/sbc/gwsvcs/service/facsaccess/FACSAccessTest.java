// $Id: FACSAccessTest.java,v 1.2 2002/10/17 17:33:17 rg3454 Exp $

package com.sbc.gwsvcs.service.facsaccess;

import com.sbc.gwsvcs.access.vicuna.*;
import com.sbc.gwsvcs.access.vicuna.exceptions.*;
import com.sbc.gwsvcs.service.facsaccess.exceptions.*;
import com.sbc.gwsvcs.service.facsaccess.interfaces.*;

import java.text.SimpleDateFormat;
import java.util.*;
/**
 * Insert the type's description here.
 * Creation date: (1/24/04 10:54:10 AM)
 * @author: Ram Kishore (rk7964)
 */
public class FACSAccessTest implements com.sbc.bccs.utilities.Logger {
/**
 * FACSAccessTest constructor comment.
 */
public FACSAccessTest() {
	super();
}
/**
 * Handle logging of message for eventId type.
 * Creation date: (6/4/01 10:54:10 AM)
 * @param eventId java.lang.String
 * @param message java.lang.String
 */
public void log(String eventId, String message) {
	System.out.println("LOG: " + eventId + " " + message);
	}

public void log(String s1, String s2, String s3, String s4){}

public void log(String s1, String s2, String s3, String s4, String s5){}

/*
 * Starts the application.
 * @param args an array of command-line arguments
 */
public static void main(java.lang.String[] args) {
	Hashtable props = new Hashtable();
	props.put("APPLDATA", "");
	props.put("FACSACCESS_APPLDATA", "");
	props.put("FACSACCESS_TIMEOUT", "60");
	props.put("GWSVCS_CLNTUUID", "limbis");
//	props.put("VICUNA_XML_FILE", "c:\\Program Files\\vicunalite\\etc\\vicunalite.xml");
//	props.put("VICUNA_SERVICE_CONFIG_DIR", "c:\\Program Files\\vicunalite\\etc");
	props.put("VICUNA_XML_FILE", "vicunalite.xml");
	props.put("VICUNA_SERVICE_CONFIG_DIR", "");
	System.setProperty("bis.platform","PC");

	try
	{
		com.sbc.gwsvcs.access.vicuna.EventResultPair response = null;
		FACSAccessTest aFACSAccessTest = new FACSAccessTest();
		FACSAccessHelper helper = new FACSAccessHelper(props, aFACSAccessTest);

		//Header_t hdr = new Header_t("GWSVC", "DGATER", "","",TrnsptType_e.FILE_TRNSPT, "");
		Header_t hdr = new Header_t("XXX", "", "","",TrnsptType_e.FILE_TRNSPT, "");
		
		C1_Section_t c1 = new C1_Section_t("INQ",
											"FAS",
											"",
											"",
											"",
											"",
											"902371", //"905321", //"902924", //"314231",//
											"",
											"",
											"",
											"",
											"",
											"",
											"",
											"",
											"");
		
		CTL_Section_t ctl = new CTL_Section_t("",
											  "",
											  "",
											  "",
											  "",
											  "",
											  "",
											  "",
											  ""); 
		
		INQ_FASG_Section_t fasg = new INQ_FASG_Section_t(
														 "DGATEL",
														 "8000", //"8914", //"867",  //"1","1000"
														 "Norvell", //"LIVENSHIRE",   //"METROPOLITON SQ","N Broadway"
														 "",
														 "",
														 "",
														 "",
														 "",
														 "",
														 "XANADU WEST", //"XANADU WEST ",
														 "", //"WI",
														 "",
														 "",
														 "",
														 "",
														 "",
														 "",
														 "",
														 "",
														 "",
														 ""
														 ); 
		


		Fasg_Inq_Req_t request = new Fasg_Inq_Req_t(hdr, c1, ctl, fasg);

		helper.connect(null, null);
		
		response = helper.inqFasgReq(30,request);
		
		System.out.println("Received Event: " + response.getEventNbr());
		Result_t data = (Result_t)response.getTheObject();
		System.out.println("Status: " + data.C1.ST);
		
		for (int i=0; i < data.RESP.length; i++)
			System.out.println("Error Msg  : " + data.RESP[i].ERRMSG + "  Error Type  :  " + data.RESP[i].ETYP );
		for (int i=0; i < data.LOOP.length; i++)
			System.out.println("LOOP  : " + data.LOOP[i].LPNO + "  CKID  :  " + data.LOOP[i].CKID + "  Status  :  " + data.LOOP[i].STAT);
		
	     helper.disconnect();
		

/**/	
	}
	catch (FACSAccessException e)
	{
		System.out.println("FACSAccessException: " + e.getExceptionCode() + " " + e.getMessage());
	}
	catch (ServiceException e)
	{
		System.out.println("ServiceException: " + e.getExceptionCode() + " " + e.getMessage());
		e.printStackTraces();
	}
			
}
}
