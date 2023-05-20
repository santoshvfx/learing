// $Id: HOSTLOOKUPTest.java,v 1.3 2004/03/15 21:15:11 hw7243 Exp $

package com.sbc.gwsvcs.service.hostlookup;

import java.util.*;
import com.sbc.bccs.utilities.*;
import com.sbc.gwsvcs.service.hostlookup.*;
import com.sbc.gwsvcs.service.hostlookup.exceptions.*;
import com.sbc.gwsvcs.service.hostlookup.interfaces.*;
import com.sbc.gwsvcs.access.vicuna.*;
import com.sbc.gwsvcs.access.vicuna.exceptions.*;

/**
 * This is the HOSTLOOKUP Test class.
 * Creation date: (4/26/01 12:32:12 PM)
 * @author: David Brawley
 */
public class HOSTLOOKUPTest implements Logger {
/**
 * Construct a HostLookupTest object.
 * Creation date: (4/26/01 12:33:23 PM)
 */
public HOSTLOOKUPTest() {
	super();
}
/**
 * Display HostLookup_Error response.
 * Creation date: (6/5/01 10:38:58 AM)
 * @return java.lang.String
 * @param param com.sbc.gwsvcs.service.hostlookup.interfaces.HostLookup_Error
 */
public static String display(HostLookup_Error response) {
	
	StringBuffer outptStr = new StringBuffer("HostLookup_Error = ");

	outptStr.append(response.tn + "|" +
					response.ErrorMsg + "|" +
					response.OrigEvent + "|");

	return new String(outptStr.toString());
}
/**
 * Display HostLookup_R response.
 * Creation date: (6/5/01 10:38:58 AM)
 * @return java.lang.String
 * @param param com.sbc.gwsvcs.service.hostlookup.interfaces.HostLookup_R
 */
public static String display(HostLookup_R response) {
	
	StringBuffer outptStr = new StringBuffer("HostLookup_R = ");

	outptStr.append(response.wc + "|" +
					response.facs + "|" +
					response.lmos + "|" +
					response.cosmos + "|" +
					response.premis + "|" +
					response.saga + "|" +
					response.sord + "|" +
					response.tirks + "|" +
					response.nsdb + "|" +
					response.boss + "|" +
					response.march + "|" +
					response.swtch + "|" +
					response.swtch_entity + "|" +
					response.wfado + "|" +
					response.wc_alpha + "|" +
					response.div_code + "|" +
					response.pics + "|" +
					response.pacbell_mi + "|" +
					response.split + "|" +
					response.location + "|" +
					//response.temp5 + "|" +
					response.OrigEvent + "|" +
					response.ErrorMsg + "|");
	
	return new String(outptStr.toString());
}
/**
 * Display HostLookupFull response.
 * Creation date: (6/5/01 10:38:58 AM)
 * @return java.lang.String
 * @param param com.sbc.gwsvcs.service.hostlookup.interfaces.HostLookupFull_R
 */
public static String display(HostLookupFull_R response) {
	
	StringBuffer outptStr = new StringBuffer("HostLookupFull_R = ");

	outptStr.append(response.wc + "|" +
					response.facs + "|" +
					response.lmos + "|" +
					response.cosmos + "|" +
					response.premis + "|" +
					response.saga + "|" +
					response.sord + "|" +
					response.tirks + "|" +
					response.nsdb + "|" +
					response.boss + "|" +
					response.march + "|" +
					response.swtch + "|" +
					response.swtch_entity + "|" +
					response.wfado + "|" +
					response.wc_alpha + "|" +
					response.div_code + "|" +
					response.pics + "|" +
					response.pacbell_mi + "|" +
					response.property + "|" +
					response.split + "|" +
					response.lmos_pacbell + "|" +
					response.lmos_snet + "|" +
					response.acis + "|" +
					response.location + "|" +
					response.temp1 + "|" +
					response.affiliate_ind + "|" +
					response.boss_2 + "|" +
					response.boss_3 + "|" +
					response.boss_4 + "|" +
					response.multiple_boss_region_ind + "|" +
					response.rcrms + "|" +
					response.dial + "|" +
					//response.temp2 + "|" +
					response.temp3 + "|" +
					response.temp4 + "|" +
					response.temp5 + "|");

	return new String(outptStr.toString());
}
/**
 * Display HostLookupST response.
 * Creation date: (6/5/01 10:38:58 AM)
 * @return java.lang.String
 * @param param com.sbc.gwsvcs.service.hostlookup.interfaces.HostLookupST
 */
public static String display(HostLookupST_R response) {
	
	StringBuffer outptStr = new StringBuffer("HostLookupST_R = ");

	outptStr.append(response.state + "|");

	return new String(outptStr.toString());
}
/**
 * Log HOSTLOOKUPTest processes.
 * Creation date: (4/26/01 12:42:18 PM)
 * @param eventId java.lang.String
 * @param message java.lang.String
 */
public void log(String eventId, String message) {
	System.out.println("LOG: " + eventId + " " + message);
}

public void log(String s1, String s2, String s3, String s4){}

public void log(String s1, String s2, String s3, String s4, String s5){}

/**
 * Starts the HOSTLOOKUPTest application
 * Creation date: (4/26/01 12:33:23 PM)
 * @param args java.lang.String[]
 */
public static void main(String[] args)
{
	Hashtable props = new Hashtable();
	
	props.put("APPLDATA", "");
	props.put("HOSTLOOKUP_APPLDATA", "");  
	props.put("HOSTLOOKUP_TIMEOUT", "60");

 	props.put("VICUNA_SERVICE_CONFIG_DIR", "");
	props.put("VICUNA_XML_FILE", "vicunalite.xml");
// 	props.put("VICUNA_SERVICE_CONFIG_DIR", "c:\\vicunalite\\etc");
//	props.put("VICUNA_XML_FILE", "c:\\vicunalite\\etc\\vicunalite.xml");
		
	try
	{
		EventResultPair response = null;
		HOSTLOOKUPTest aHOSTLOOKUPTest = new HOSTLOOKUPTest();
		HOSTLOOKUPHelper helper = new HOSTLOOKUPHelper(props, aHOSTLOOKUPTest);

		// Active
		// C=US,O=SBC,OU=DGBusSvcs,CN=HostLookup	APPL <> 
// *** 
// ***  HOSTLOOKUP BY TN

		HostLookupTN hostLookupTN = new HostLookupTN("3142359350");
//		HostLookupTN hostLookupTN = new HostLookupTN("0234079865");  // fictitious
		response = helper.hlLookupFull(null, null, 0, hostLookupTN);
		System.out.println("Received event: " + response.getEventNbr());
		switch (response.getEventNbr())
		{
			case HOSTLOOKUPAccess.HL_LOOKUP_FULL_NBR:
				HostLookupFull_R resultTN = (HostLookupFull_R)response.getTheObject();
				System.out.println(display(resultTN));
				break;
			case HOSTLOOKUPAccess.HL_LOOKUP_FULL_FICTITIOUS_NBR:
				HostLookupFull_R resultTN_F = (HostLookupFull_R)response.getTheObject();
				System.out.println(display(resultTN_F));
				break;
			case HOSTLOOKUPAccess.HL_OSS_LOOKUP_ERROR_NBR:
				HostLookup_Error resultErr = (HostLookup_Error)response.getTheObject();
				System.out.println(display(resultErr));
				break;
			default:
				System.out.println("UNKNOWN EVENT<" + response.getEventNbr() + ">");
		} // end switch

// *** 
// ***  HOSTLOOKUP BY WC
	
		HostLookupWC hostLookupWC = new HostLookupWC("510881");
		response = helper.hlLookupWC(null, null, 0, hostLookupWC);
		System.out.println("Received event: " + response.getEventNbr());
		switch (response.getEventNbr())
		{
			case HOSTLOOKUPAccess.HL_LOOKUP_R_NBR:
				HostLookup_R resultWC = (HostLookup_R)response.getTheObject();
				System.out.println(display(resultWC));
				break;
			case HOSTLOOKUPAccess.HL_LOOKUP_ERROR_NBR:
				HostLookup_R resultErr = (HostLookup_R)response.getTheObject();
				System.out.println(display(resultErr));
				break;
			default:
				System.out.println("UNKNOWN EVENT<" + response.getEventNbr() + ">");
		} // end switch

// ***       
// ***	HOSTLOOKUP BY DIV          Div Codes:  C, L, R, H, T, X, N

		HostLookupDIV hostLookupDIV = new HostLookupDIV("T");  // Div Code 
		response = helper.hlLookupDIV(null, null, 0, hostLookupDIV);
		System.out.println("Received event: " + response.getEventNbr());
		switch (response.getEventNbr())
		{
			case HOSTLOOKUPAccess.HL_LOOKUP_R_NBR:
				HostLookup_R resultDIV = (HostLookup_R)response.getTheObject();
				System.out.println(display(resultDIV));
				break;
			case HOSTLOOKUPAccess.HL_LOOKUP_ERROR_NBR:
				HostLookup_R resultErr = (HostLookup_R)response.getTheObject();
				System.out.println(display(resultErr));
				break;
			default:
				System.out.println("UNKNOWN EVENT<" + response.getEventNbr() + ">");
		} // end switch

// *** 
// ***  HOSTLOOKUP BY STATE
 
//      State Code by telephonenumber success.
		HostLookupST hostLookupst = new HostLookupST("9259019255");
		
//      State Code by telephonenumber failure.
//		HostLookupST hostLookupst = new HostLookupST("XX59019255");

		response = helper.hlLookupState(null, null, 0, hostLookupst);
		System.out.println("Received event: " + response.getEventNbr());
		switch (response.getEventNbr())
		{
			case HOSTLOOKUPAccess.HL_LOOKUP_STATE_R_NBR:
				HostLookupST_R resultState = (HostLookupST_R)response.getTheObject();
				System.out.println(display(resultState));
				break;
			case HOSTLOOKUPAccess.HL_OSS_LOOKUP_ERROR_NBR:
				HostLookup_Error resultErr = (HostLookup_Error)response.getTheObject();
				System.out.println(display(resultErr));
				break;
			default:
				System.out.println("UNKNOWN EVENT<" + response.getEventNbr() + ">");
		} // end switch
	}
	catch (HOSTLOOKUPException e)
	{
		System.out.println("HOSTLOOKUPException: " + e.getExceptionCode() + " " + e.getMessage());
	}
	catch (ServiceException e)
	{
		System.out.println("ServiceException: " + e.getExceptionCode() + " " + e.getMessage());
		e.printStackTraces();
	}

}
}
