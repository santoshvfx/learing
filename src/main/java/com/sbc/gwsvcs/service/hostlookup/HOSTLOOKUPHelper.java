// $Id: HOSTLOOKUPHelper.java,v 1.1 2002/09/29 04:16:26 dm2328 Exp $

package com.sbc.gwsvcs.service.hostlookup;

import com.sbc.vicunalite.api.*;
import com.sbc.gwsvcs.service.hostlookup.exceptions.*;
import com.sbc.gwsvcs.service.hostlookup.interfaces.*;
import com.sbc.gwsvcs.access.vicuna.*;
import com.sbc.gwsvcs.access.vicuna.exceptions.*;
import com.sbc.eia.idl.exception_types.*;

/**
 * A collection of helper methods for access to HOSTLOOKUP Service.
 * Creation date: (4/26/01 11:35:56 AM)
 * @author: David Brawley
 */
 /*
  * 
  * 01/2202
  * Added the returns expexted for state codes.
  * These are event type, message classes and event numbers. 
  * Mark L.
  */
  
public class HOSTLOOKUPHelper 
	extends ServiceHelper
{
	static final EventClassPair hostLookupRespExpected[] = new EventClassPair [] { 
			new EventClassPair(HOSTLOOKUPAccess.HL_LOOKUP_R, HostLookup_RMsg.class, HOSTLOOKUPAccess.HL_LOOKUP_R_NBR),
			new EventClassPair(HOSTLOOKUPAccess.HL_LOOKUP_ERROR, HostLookup_RMsg.class, HOSTLOOKUPAccess.HL_LOOKUP_ERROR_NBR)
	};
	static final EventClassPair hostLookupFullRespExpected[] = new EventClassPair [] { 
			new EventClassPair(HOSTLOOKUPAccess.HL_LOOKUP_FULL, HostLookupFull_RMsg.class, HOSTLOOKUPAccess.HL_LOOKUP_FULL_NBR),
			new EventClassPair(HOSTLOOKUPAccess.HL_LOOKUP_FULL_FICTITIOUS, HostLookupFull_RMsg.class, HOSTLOOKUPAccess.HL_LOOKUP_FULL_FICTITIOUS_NBR),
			new EventClassPair(HOSTLOOKUPAccess.HL_OSS_LOOKUP_ERROR, HostLookup_ErrorMsg.class, HOSTLOOKUPAccess.HL_OSS_LOOKUP_ERROR_NBR)
	};
 	static final EventClassPair hostLookupStateRespExpected[] = new EventClassPair [] { 
			new EventClassPair(HOSTLOOKUPAccess.HL_LOOKUP_STATE_R, HostLookupST_RMsg.class, HOSTLOOKUPAccess.HL_LOOKUP_STATE_R_NBR),
			new EventClassPair(HOSTLOOKUPAccess.HL_OSS_LOOKUP_ERROR, HostLookup_ErrorMsg.class, HOSTLOOKUPAccess.HL_OSS_LOOKUP_ERROR_NBR)
	};
 
/**
 * Construct a HOSTLOOKUPHelper object.
 * Creation date: (4/26/01 11:36:38 AM)
 * @param properties java.util.Hashtable
 * @param aLogger com.sbc.bccs.utilities.Logger
 */
public HOSTLOOKUPHelper(java.util.Hashtable properties, com.sbc.bccs.utilities.Logger aLogger)
	throws ServiceException
{
	super(properties, aLogger, HOSTLOOKUPAccess.name);

	serviceAccess = new HOSTLOOKUPAccess(vicunaXmlFile, serviceXmlDir, logger);
	if (extractedTimeOut != null)
		setDefaultTimeOut(Integer.parseInt(extractedTimeOut));
	setDefaultApplData(extractedApplData);
}
/**
 * Evaluate response to HOSTLOOKUP by DIV.
 * Creation date: (4/26/01 2:22:56 PM)
 * @param result com.sbc.gwsvcs.access.vicuna.EventObjectPair
 */
private EventResultPair evalHlLookupDIV(EventObjectPair result) throws ServiceException
{
	switch(result.eventNbr)
	{
		case HOSTLOOKUPAccess.HL_LOOKUP_R_NBR:
			return new EventResultPair(((HostLookup_RMsg)result.anObject).value, result.eventNbr);
		case HOSTLOOKUPAccess.HL_LOOKUP_ERROR_NBR:
			HostLookup_R e = ((HostLookup_RMsg)result.anObject).value;
			throw new HOSTLOOKUPException(ExceptionCode.ERR_GWS_HOSTLOOKUP, e.ErrorMsg, String.valueOf(e.OrigEvent));
		default:
			throw new HOSTLOOKUPException(ExceptionCode.ERR_GWS_HOSTLOOKUP, "HOSTLOOKUPHelper::evalHlLookupDIV: Unexpected return from sendAndReceive() " +
				result.event);
	}
}
/**
 * Evaluate response from HOSTLOOKUP by TN.
 * Creation date: (4/26/01 2:22:56 PM)
 * @param result com.sbc.gwsvcs.access.vicuna.EventObjectPair
 */
private EventResultPair evalHlLookupFull(EventObjectPair result) throws ServiceException
{
	switch(result.eventNbr)
	{
		case HOSTLOOKUPAccess.HL_LOOKUP_FULL_NBR:
			return new EventResultPair(((HostLookupFull_RMsg)result.anObject).value, result.eventNbr);
		case HOSTLOOKUPAccess.HL_LOOKUP_FULL_FICTITIOUS_NBR:
			return new EventResultPair(((HostLookupFull_RMsg)result.anObject).value, result.eventNbr);
		case HOSTLOOKUPAccess.HL_OSS_LOOKUP_ERROR_NBR:
			HostLookup_Error e = ((HostLookup_ErrorMsg)result.anObject).value;
			throw new HOSTLOOKUPException(ExceptionCode.ERR_GWS_HOSTLOOKUP, e.ErrorMsg, String.valueOf(e.OrigEvent));
		default:
			throw new HOSTLOOKUPException(ExceptionCode.ERR_GWS_HOSTLOOKUP, "HOSTLOOKUPHelper::evalevalHlLookupFull: Unexpected return from sendAndReceive() " +
				result.event);
	}
}
/**
 * Evaluate response for HOSTLOOKUP by STATE.
 * Creation date: (01/2002)
 * @param result com.sbc.gwsvcs.access.vicuna.EventObjectPair
 * Mark L.
 */


private EventResultPair evalHlLookupState(EventObjectPair result) throws ServiceException
{
	switch(result.eventNbr)
	{
		case HOSTLOOKUPAccess.HL_LOOKUP_STATE_R_NBR:
			return new EventResultPair(((HostLookupST_RMsg)result.anObject).value, result.eventNbr);
		case HOSTLOOKUPAccess.HL_OSS_LOOKUP_ERROR_NBR:
			HostLookup_Error e = ((HostLookup_ErrorMsg)result.anObject).value;
			throw new HOSTLOOKUPException(ExceptionCode.ERR_GWS_HOSTLOOKUP, e.ErrorMsg, String.valueOf(e.OrigEvent));
		default:
			throw new HOSTLOOKUPException(ExceptionCode.ERR_GWS_HOSTLOOKUP, "HOSTLOOKUPHelper::evalHlLookupSTATE: Unexpected return from sendAndReceive() " +
				result.event);
	}
	
}
/**
 * Evaluate response for HOSTLOOKUP by WC.
 * Creation date: (4/26/01 2:22:56 PM)
 * @param result com.sbc.gwsvcs.access.vicuna.EventObjectPair
 */
private EventResultPair evalHlLookupWC(EventObjectPair result) throws ServiceException
{
	switch(result.eventNbr)
	{
		case HOSTLOOKUPAccess.HL_LOOKUP_R_NBR:
			return new EventResultPair(((HostLookup_RMsg)result.anObject).value, result.eventNbr);
		case HOSTLOOKUPAccess.HL_LOOKUP_ERROR_NBR:
			HostLookup_R e = ((HostLookup_RMsg)result.anObject).value;
			throw new HOSTLOOKUPException(ExceptionCode.ERR_GWS_HOSTLOOKUP, e.ErrorMsg, String.valueOf(e.OrigEvent));
		default:
			throw new HOSTLOOKUPException(ExceptionCode.ERR_GWS_HOSTLOOKUP, "HOSTLOOKUPHelper::evalHlLookupWC: Unexpected return from sendAndReceive() " +
				result.event);
	}
}
/**
 * Helper to request HOSTLOOKUP by DIV
 * Creation date: (4/26/01 2:22:56 PM)
 * @param aTimeOut long
 * @param request com.sbc.gwsvcs.service.hostlookup.interfaces.HostLookupDIV
 */
public EventResultPair hlLookupDIV(long aTimeOut, HostLookupDIV request) throws ServiceException
{
	HOSTLOOKUPAccess hostLookupAccess = (HOSTLOOKUPAccess)serviceAccess;

	EventObjectPair inRequest = new EventObjectPair(hostLookupAccess.HL_LOOKUP_DIV, new HostLookupDIVMsg(request),hostLookupAccess.HL_LOOKUP_DIV_NBR);

	return evalHlLookupDIV(hostLookupAccess.sendAndReceive(factorTimeOut(aTimeOut), inRequest, hostLookupRespExpected));
}
/**
 * Helper to request HOSTLOOKUP by DIV
 * Creation date: (4/26/01 2:22:56 PM)
 * @param anApplData java.lang.String
 * @param aService java.lang.String
 * @param aTimeOut long
 * @param request com.sbc.gwsvcs.service.hostlookup.interfaces.HostLookupDIV
 */
public EventResultPair hlLookupDIV(String anApplData, String aService, long aTimeOut, HostLookupDIV request) throws ServiceException
{
	HOSTLOOKUPAccess hostLookupAccess = (HOSTLOOKUPAccess)serviceAccess;

	EventObjectPair inRequest = new EventObjectPair(hostLookupAccess.HL_LOOKUP_DIV, new HostLookupDIVMsg(request),hostLookupAccess.HL_LOOKUP_DIV_NBR);

	return evalHlLookupDIV(hostLookupAccess.connectSendReceiveAndDisconnect(anApplData, aService, factorTimeOut(aTimeOut), inRequest, hostLookupRespExpected));
}
/**
 * Helper to request HOSTLOOKUP by TN
 * Creation date: (4/26/01 2:22:56 PM)
 * @param aTimeOut long
 * @param request com.sbc.gwsvcs.service.hostlookup.interfaces.HostLookupTN
 */
public EventResultPair hlLookupFull(long aTimeOut, HostLookupTN request) throws ServiceException
{
	HOSTLOOKUPAccess hostLookupAccess = (HOSTLOOKUPAccess)serviceAccess;

	EventObjectPair inRequest = new EventObjectPair(hostLookupAccess.HL_LOOKUP_FULL, new HostLookupTNMsg(request),hostLookupAccess.HL_LOOKUP_FULL_NBR);

	return evalHlLookupFull(hostLookupAccess.sendAndReceive(factorTimeOut(aTimeOut), inRequest, hostLookupFullRespExpected));
}
/**
 * Helper to request HOSTLOOKUP by TN
 * Creation date: (4/26/01 2:22:56 PM)
 * @param anApplData java.lang.String
 * @param aService java.lang.String
 * @param aTimeOut long
 * @param request com.sbc.gwsvcs.service.hostlookup.interfaces.HostLookupTN
 */
public EventResultPair hlLookupFull(String anApplData, String aService, long aTimeOut, HostLookupTN request) throws ServiceException
{
	HOSTLOOKUPAccess hostLookupAccess = (HOSTLOOKUPAccess)serviceAccess;

	EventObjectPair inRequest = new EventObjectPair(hostLookupAccess.HL_LOOKUP_FULL, new HostLookupTNMsg(request),hostLookupAccess.HL_LOOKUP_FULL_NBR);

	return evalHlLookupFull(hostLookupAccess.connectSendReceiveAndDisconnect(anApplData, aService, factorTimeOut(aTimeOut), inRequest, hostLookupFullRespExpected));
}
/**
 * Helper to request HOSTLOOKUP by TN
 * Creation date: (01/2002)
 * @param aTimeOut long
 * @param request com.sbc.gwsvcs.service.hostlookup.interfaces.HostLookupST
 * Mark L.
 */
public EventResultPair hlLookupState(long aTimeOut, HostLookupST request) throws ServiceException
{
	HOSTLOOKUPAccess hostLookupAccess = (HOSTLOOKUPAccess)serviceAccess;

	EventObjectPair inRequest = new EventObjectPair(hostLookupAccess.HL_LOOKUP_STATE, new HostLookupSTMsg(request),hostLookupAccess.HL_LOOKUP_STATE_NBR);

	return evalHlLookupState(hostLookupAccess.sendAndReceive(factorTimeOut(aTimeOut), inRequest, hostLookupStateRespExpected));
}
/**
 * Helper to request HOSTLOOKUP by DIV
 * Creation date: (4/26/01 2:22:56 PM)
 * @param anApplData java.lang.String
 * @param aService java.lang.String
 * @param aTimeOut long
 * @param request com.sbc.gwsvcs.service.hostlookup.interfaces.HostLookupST
 */
public EventResultPair hlLookupState(String anApplData, String aService, long aTimeOut, HostLookupST request) throws ServiceException
{
	HOSTLOOKUPAccess hostLookupAccess = (HOSTLOOKUPAccess)serviceAccess;

	EventObjectPair inRequest = new EventObjectPair(hostLookupAccess.HL_LOOKUP_STATE, new HostLookupSTMsg(request),hostLookupAccess.HL_LOOKUP_STATE_NBR);

	return evalHlLookupState(hostLookupAccess.connectSendReceiveAndDisconnect(anApplData, aService, factorTimeOut(aTimeOut), inRequest, hostLookupStateRespExpected));
}
/**
 * Helper to request HOSTLOOKUP by WC
 * Creation date: (4/26/01 2:22:56 PM)
 * @param aTimeOut long
 * @param request com.sbc.gwsvcs.service.hostlookup.interfaces.HostLookupWC
 */
public EventResultPair hlLookupWC(long aTimeOut, HostLookupWC request) throws ServiceException
{
	HOSTLOOKUPAccess hostLookupAccess = (HOSTLOOKUPAccess)serviceAccess;

	EventObjectPair inRequest = new EventObjectPair(hostLookupAccess.HL_LOOKUP_WC, new HostLookupWCMsg(request),hostLookupAccess.HL_LOOKUP_WC_NBR);

	return evalHlLookupWC(hostLookupAccess.sendAndReceive(factorTimeOut(aTimeOut), inRequest, hostLookupRespExpected));
}
/**
 * Helper to request HOSTLOOKUP by WC
 * Creation date: (4/26/01 2:22:56 PM)
 * @param anApplData java.lang.String
 * @param aService java.lang.String
 * @param aTimeOut long
 * @param request com.sbc.gwsvcs.service.hostlookup.interfaces.HostLookupWC
 */
public EventResultPair hlLookupWC(String anApplData, String aService, long aTimeOut, HostLookupWC request) throws ServiceException
{
	HOSTLOOKUPAccess hostLookupAccess = (HOSTLOOKUPAccess)serviceAccess;

	EventObjectPair inRequest = new EventObjectPair(hostLookupAccess.HL_LOOKUP_WC, new HostLookupWCMsg(request),hostLookupAccess.HL_LOOKUP_WC_NBR);

	return evalHlLookupWC(hostLookupAccess.connectSendReceiveAndDisconnect(anApplData, aService, factorTimeOut(aTimeOut), inRequest, hostLookupRespExpected));
}
}
