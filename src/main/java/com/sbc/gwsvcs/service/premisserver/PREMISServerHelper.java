// $Id: PREMISServerHelper.java,v 1.3 2003/02/06 20:44:54 as5472 Exp $

package com.sbc.gwsvcs.service.premisserver;

import com.sbc.vicunalite.api.*;
import com.sbc.gwsvcs.service.premisserver.exceptions.*;
import com.sbc.gwsvcs.service.premisserver.interfaces.*;
import com.sbc.gwsvcs.access.vicuna.*;
import com.sbc.gwsvcs.access.vicuna.exceptions.*;
import com.sbc.eia.idl.exception_types.*;

/**
 * Provides support for client access to the PREMISServer service.
 * Creation date: (4/16/00 11:35:56 AM)
 * @author: Creighton Malet
 */
public class PREMISServerHelper 
	extends ServiceHelper
{
	static final EventClassPair premisValdtAddrExpected[] = new EventClassPair [] { 
			new EventClassPair(PREMISServerAccess.PREMIS_HIT_RESP, PremisHITResp_tMsg.class, PREMISServerAccess.PREMIS_HIT_RESP_NBR),
			new EventClassPair(PREMISServerAccess.PREMIS_SAGA_MENU_RESP, PremisSagaMenuResp_tMsg.class, PREMISServerAccess.PREMIS_SAGA_MENU_RESP_NBR),
			new EventClassPair(PREMISServerAccess.PREMIS_ZIP_MENU_RESP, PremisZipMenuResp_tMsg.class, PREMISServerAccess.PREMIS_ZIP_MENU_RESP_NBR),
			new EventClassPair(PREMISServerAccess.PREMIS_ST_NM_MENU_RESP, PremisStNmMenuResp_tMsg.class, PREMISServerAccess.PREMIS_ST_NM_MENU_RESP_NBR),
			new EventClassPair(PREMISServerAccess.PREMIS_DESC_NM_MENU_RESP, PremisDescNmMenuResp_tMsg.class, PREMISServerAccess.PREMIS_DESC_NM_MENU_RESP_NBR),
			new EventClassPair(PREMISServerAccess.PREMIS_ADDR_RNGE_MENU_RESP, PremisAddrRngeMenuResp_tMsg.class, PREMISServerAccess.PREMIS_ADDR_RNGE_MENU_RESP_NBR),
			new EventClassPair(PREMISServerAccess.PREMIS_ADDR_MENU_RESP, PremisAddrMenuResp_tMsg.class, PREMISServerAccess.PREMIS_ADDR_MENU_RESP_NBR),
			new EventClassPair(PREMISServerAccess.PREMIS_ST_ADDR_RANGE_MENU_RESP, PremisStAddrRngeMenuResp_tMsg.class, PREMISServerAccess.PREMIS_ST_ADDR_RANGE_MENU_RESP_NBR),
			new EventClassPair(PREMISServerAccess.PREMIS_UNNBRD_MENU_RESP, PremisUnnbrdMenuResp_tMsg.class, PREMISServerAccess.PREMIS_UNNBRD_MENU_RESP_NBR),
			new EventClassPair(PREMISServerAccess.PREMIS_BASC_ADDR_MENU_RESP, PremisBascAddrMenuResp_tMsg.class, PREMISServerAccess.PREMIS_BASC_ADDR_MENU_RESP_NBR),
			new EventClassPair(PREMISServerAccess.PREMIS_UNADRM_MENU_RESP, PremisUnnbrdAddrRngeMenuResp_tMsg.class, PREMISServerAccess.PREMIS_UNADRM_MENU_RESP_NBR),
			new EventClassPair(PREMISServerAccess.PREMIS_GSGM_MENU_RESP, PremisGeoSegMenuResp_tMsg.class, PREMISServerAccess.PREMIS_GSGM_MENU_RESP_NBR),
			new EventClassPair(PREMISServerAccess.PREMIS_TN_MATCH_MENU_RESP, PremisTnMatchMenuResp_tMsg.class, PREMISServerAccess.PREMIS_TN_MATCH_MENU_RESP_NBR),
			new EventClassPair(PREMISServerAccess.PREMIS_SUPP_ADDR_MENU_RESP, PremisSuppAddrMenuResp_tMsg.class, PREMISServerAccess.PREMIS_SUPP_ADDR_MENU_RESP_NBR),
			new EventClassPair(PREMISServerAccess.PREMIS_UNADRM_GSGM_MENU_RESP, PremisUnadrmGsgmResp_tMsg.class, PREMISServerAccess.PREMIS_UNADRM_GSGM_MENU_RESP_NBR),
			new EventClassPair(PREMISServerAccess.EXCEPTION, ExceptionResp_tMsg.class, PREMISServerAccess.EXCEPTION_NBR)
		};
	static final EventClassPair premisTnRsrvExpected[] = new EventClassPair [] { 
			new EventClassPair(PREMISServerAccess.PREMIS_TN_RSRV_RESP, PremisTnRsrvResp_tMsg.class, PREMISServerAccess.PREMIS_TN_RSRV_RESP_NBR),
			new EventClassPair(PREMISServerAccess.PREMIS_TN_RSRV_TCAT_RESP, PremisTnRsrvTCATResp_tMsg.class, PREMISServerAccess.PREMIS_TN_RSRV_TCAT_RESP_NBR),
			new EventClassPair(PREMISServerAccess.PREMIS_TN_RSRV_ADDLN_RESP, PremisTnRsrvADDLNResp_tMsg.class, PREMISServerAccess.PREMIS_TN_RSRV_ADDLN_RESP_NBR),
			new EventClassPair(PREMISServerAccess.EXCEPTION, ExceptionResp_tMsg.class, PREMISServerAccess.EXCEPTION_NBR)
		};
	static final EventClassPair premisTnSaveExpected[] = new EventClassPair [] { 
			new EventClassPair(PREMISServerAccess.PREMIS_TN_SAVE_RESP, PremisTnSaveResp_tMsg.class, PREMISServerAccess.PREMIS_TN_SAVE_RESP_NBR),
			new EventClassPair(PREMISServerAccess.PREMIS_TN_SAVE_ADDLN_RESP, PremisTnSaveADDLNResp_tMsg.class, PREMISServerAccess.PREMIS_TN_SAVE_ADDLN_RESP_NBR),
			new EventClassPair(PREMISServerAccess.EXCEPTION, ExceptionResp_tMsg.class, PREMISServerAccess.EXCEPTION_NBR)
		};
	static final EventClassPair premisTnRetnExpected[] = new EventClassPair [] { 
			new EventClassPair(PREMISServerAccess.PREMIS_TN_RETN_RESP, PremisTnRetnResp_tMsg.class, PREMISServerAccess.PREMIS_TN_RETN_RESP_NBR),
			new EventClassPair(PREMISServerAccess.EXCEPTION, ExceptionResp_tMsg.class, PREMISServerAccess.EXCEPTION_NBR)
		};
	static final EventClassPair premisTnStaExpected[] = new EventClassPair [] { 
			new EventClassPair(PREMISServerAccess.PREMIS_TN_STA_RESP, PremisTnStaResp_tMsg.class, PREMISServerAccess.PREMIS_TN_STA_RESP_NBR),
			new EventClassPair(PREMISServerAccess.EXCEPTION, ExceptionResp_tMsg.class, PREMISServerAccess.EXCEPTION_NBR)
		};
	static final EventClassPair premisTnMttExpected[] = new EventClassPair [] { 
			new EventClassPair(PREMISServerAccess.PREMIS_TN_MTT_RESP, PremisTnMttResp_tMsg.class, PREMISServerAccess.PREMIS_TN_MTT_RESP_NBR),
			new EventClassPair(PREMISServerAccess.EXCEPTION, ExceptionResp_tMsg.class, PREMISServerAccess.EXCEPTION_NBR)
		};
/**
 * Constructor accepting properties and a Logger.
 * @exception com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException: an exception occurred.
 */
public PREMISServerHelper(java.util.Hashtable properties, com.sbc.bccs.utilities.Logger aLogger)
	throws ServiceException
{
	super(properties, aLogger, PREMISServerAccess.name);

	serviceAccess = new PREMISServerAccess(vicunaXmlFile, serviceXmlDir, logger);
	if (extractedTimeOut != null)
		setDefaultTimeOut(Integer.parseInt(extractedTimeOut));
	setDefaultApplData(extractedApplData);
}
/**
 * Evaluate result from premisTnMttReq().
 * Creation date: (4/16/00 2:22:56 PM)
 * @return com.sbc.gwsvcs.access.vicuna.EventResultPair
 * @param result com.sbc.gwsvcs.access.vicuna.EventObjectPair
 * @exception com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException: a service exception occurred
 */
private EventResultPair evalPremisTnMttReq(EventObjectPair result) throws ServiceException
{
	switch(result.eventNbr)
	{
		case PREMISServerAccess.PREMIS_TN_MTT_RESP_NBR:
			return new EventResultPair(((PremisTnMttResp_tMsg)result.anObject).value, result.eventNbr);
		case PREMISServerAccess.EXCEPTION_NBR:
			ExceptionResp_t e = ((ExceptionResp_tMsg)result.anObject).value;
			throw new PREMISServerException(ExceptionCode.ERR_GWS_PREMISSERVER, e.OutExcp.ERR_TX, String.valueOf(e.OutExcp.ERR_CD));
		default:
			throw new PREMISServerException(ExceptionCode.ERR_GWS_PREMISSERVER, "PREMISServerHelper::evalPremisTnMttReq: Unexpected return from sendAndReceive() " +
				result.event);
	}
}
/**
 * Evaluate result from premisTnRetnReq().
 * Creation date: (4/16/00 2:22:56 PM)
 * @return com.sbc.gwsvcs.access.vicuna.EventResultPair
 * @param result com.sbc.gwsvcs.access.vicuna.EventObjectPair
 * @exception com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException: a service exception occurred
 */
private EventResultPair evalPremisTnRetnReq(EventObjectPair result) throws ServiceException
{
	switch(result.eventNbr)
	{
		case PREMISServerAccess.PREMIS_TN_RETN_RESP_NBR:
			return new EventResultPair(((PremisTnRetnResp_tMsg)result.anObject).value, result.eventNbr);
		case PREMISServerAccess.EXCEPTION_NBR:
			ExceptionResp_t e = ((ExceptionResp_tMsg)result.anObject).value;
			throw new PREMISServerException(ExceptionCode.ERR_GWS_PREMISSERVER, e.OutExcp.ERR_TX, String.valueOf(e.OutExcp.ERR_CD));
		default:
			throw new PREMISServerException(ExceptionCode.ERR_GWS_PREMISSERVER, "PREMISServerHelper::evalPremisTnRetnReq: Unexpected return from sendAndReceive() " +
				result.event);
	}
}
/**
 * Evaluate result from premisTnRsrvReq().
 * Creation date: (4/16/00 2:22:56 PM)
 * @return com.sbc.gwsvcs.access.vicuna.EventResultPair
 * @param result com.sbc.gwsvcs.access.vicuna.EventObjectPair
 * @exception com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException: a service exception occurred
 */
private EventResultPair evalPremisTnRsrvReq(EventObjectPair result) throws ServiceException
{
	switch(result.eventNbr)
	{
		case PREMISServerAccess.PREMIS_TN_RSRV_RESP_NBR:
			return new EventResultPair(((PremisTnRsrvResp_tMsg)result.anObject).value, result.eventNbr);
		case PREMISServerAccess.PREMIS_TN_RSRV_TCAT_RESP_NBR:
			return new EventResultPair(((PremisTnRsrvTCATResp_tMsg)result.anObject).value, result.eventNbr);
		case PREMISServerAccess.PREMIS_TN_RSRV_ADDLN_RESP_NBR:
			return new EventResultPair(((PremisTnRsrvADDLNResp_tMsg)result.anObject).value, result.eventNbr);
		case PREMISServerAccess.EXCEPTION_NBR:
			ExceptionResp_t e = ((ExceptionResp_tMsg)result.anObject).value;
			throw new PREMISServerException(ExceptionCode.ERR_GWS_PREMISSERVER, e.OutExcp.ERR_TX, String.valueOf(e.OutExcp.ERR_CD));
		default:
			throw new PREMISServerException(ExceptionCode.ERR_GWS_PREMISSERVER, "PREMISServerHelper::evalPremisTnRsrvReq: Unexpected return from sendAndReceive() " +
				result.event);
	}
}
/**
 * Evaluate result from premisTnSaveReq().
 * Creation date: (4/16/00 2:22:56 PM)
 * @return com.sbc.gwsvcs.access.vicuna.EventResultPair
 * @param result com.sbc.gwsvcs.access.vicuna.EventObjectPair
 * @exception com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException: a service exception occurred
 */
private EventResultPair evalPremisTnSaveReq(EventObjectPair result) throws ServiceException
{
	switch(result.eventNbr)
	{
		case PREMISServerAccess.PREMIS_TN_SAVE_RESP_NBR:
			return new EventResultPair(((PremisTnSaveResp_tMsg)result.anObject).value, result.eventNbr);
		case PREMISServerAccess.PREMIS_TN_SAVE_ADDLN_RESP_NBR:
			return new EventResultPair(((PremisTnSaveADDLNResp_tMsg)result.anObject).value, result.eventNbr);
		case PREMISServerAccess.EXCEPTION_NBR:
			ExceptionResp_t e = ((ExceptionResp_tMsg)result.anObject).value;
			throw new PREMISServerException(ExceptionCode.ERR_GWS_PREMISSERVER, e.OutExcp.ERR_TX, String.valueOf(e.OutExcp.ERR_CD));
		default:
			throw new PREMISServerException(ExceptionCode.ERR_GWS_PREMISSERVER, "PREMISServerHelper::evalPremisTnSaveReq: Unexpected return from sendAndReceive() " +
				result.event);
	}
}
/**
 * Evaluate result from premisTnStaReq().
 * Creation date: (4/16/00 2:22:56 PM)
 * @return com.sbc.gwsvcs.access.vicuna.EventResultPair
 * @param result com.sbc.gwsvcs.access.vicuna.EventObjectPair
 * @exception com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException: a service exception occurred
 */
private EventResultPair evalPremisTnStaReq(EventObjectPair result) throws ServiceException
{
	switch(result.eventNbr)
	{
		case PREMISServerAccess.PREMIS_TN_STA_RESP_NBR:
			return new EventResultPair(((PremisTnStaResp_tMsg)result.anObject).value, result.eventNbr);
		case PREMISServerAccess.EXCEPTION_NBR:
			ExceptionResp_t e = ((ExceptionResp_tMsg)result.anObject).value;
			throw new PREMISServerException(ExceptionCode.ERR_GWS_PREMISSERVER, e.OutExcp.ERR_TX, String.valueOf(e.OutExcp.ERR_CD));
		default:
			throw new PREMISServerException(ExceptionCode.ERR_GWS_PREMISSERVER, "PREMISServerHelper::evalPremisTnStaReq: Unexpected return from sendAndReceive() " +
				result.event);
	}
}
/**
 * Evaluate result from premisValdtAddrReq().
 * Creation date: (4/16/00 2:22:56 PM)
 * @return com.sbc.gwsvcs.access.vicuna.EventResultPair
 * @param result com.sbc.gwsvcs.access.vicuna.EventObjectPair
 * @exception com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException: a service exception occurred
 */
private EventResultPair evalPremisValdtAddrReq(EventObjectPair result) throws ServiceException
{
	switch(result.eventNbr)
	{
		case PREMISServerAccess.PREMIS_HIT_RESP_NBR:
			return new EventResultPair(((PremisHITResp_tMsg)result.anObject).value, result.eventNbr);
		case PREMISServerAccess.PREMIS_SAGA_MENU_RESP_NBR:
			return new EventResultPair(((PremisSagaMenuResp_tMsg)result.anObject).value, result.eventNbr);
		case PREMISServerAccess.PREMIS_ZIP_MENU_RESP_NBR:
			return new EventResultPair(((PremisZipMenuResp_tMsg)result.anObject).value, result.eventNbr);
		case PREMISServerAccess.PREMIS_ST_NM_MENU_RESP_NBR:
			return new EventResultPair(((PremisStNmMenuResp_tMsg)result.anObject).value, result.eventNbr);
		case PREMISServerAccess.PREMIS_DESC_NM_MENU_RESP_NBR:
			return new EventResultPair(((PremisDescNmMenuResp_tMsg)result.anObject).value, result.eventNbr);
		case PREMISServerAccess.PREMIS_ADDR_RNGE_MENU_RESP_NBR:
			return new EventResultPair(((PremisAddrRngeMenuResp_tMsg)result.anObject).value, result.eventNbr);
		case PREMISServerAccess.PREMIS_ADDR_MENU_RESP_NBR:
			return new EventResultPair(((PremisAddrMenuResp_tMsg)result.anObject).value, result.eventNbr);
		case PREMISServerAccess.PREMIS_ST_ADDR_RANGE_MENU_RESP_NBR:
			return new EventResultPair(((PremisStAddrRngeMenuResp_tMsg)result.anObject).value, result.eventNbr);
		case PREMISServerAccess.PREMIS_UNNBRD_MENU_RESP_NBR:
			return new EventResultPair(((PremisUnnbrdMenuResp_tMsg)result.anObject).value, result.eventNbr);
		case PREMISServerAccess.PREMIS_BASC_ADDR_MENU_RESP_NBR:
			return new EventResultPair(((PremisBascAddrMenuResp_tMsg)result.anObject).value, result.eventNbr);
		case PREMISServerAccess.PREMIS_UNADRM_MENU_RESP_NBR:
			return new EventResultPair(((PremisUnnbrdAddrRngeMenuResp_tMsg)result.anObject).value, result.eventNbr);
		case PREMISServerAccess.PREMIS_GSGM_MENU_RESP_NBR:
			return new EventResultPair(((PremisGeoSegMenuResp_tMsg)result.anObject).value, result.eventNbr);
		case PREMISServerAccess.PREMIS_TN_MATCH_MENU_RESP_NBR:
			return new EventResultPair(((PremisTnMatchMenuResp_tMsg)result.anObject).value, result.eventNbr);
		case PREMISServerAccess.PREMIS_SUPP_ADDR_MENU_RESP_NBR:
			return new EventResultPair(((PremisSuppAddrMenuResp_tMsg)result.anObject).value, result.eventNbr);
		case PREMISServerAccess.PREMIS_UNADRM_GSGM_MENU_RESP_NBR:
			return new EventResultPair(((PremisUnadrmGsgmResp_tMsg)result.anObject).value, result.eventNbr);
		case PREMISServerAccess.EXCEPTION_NBR:
			ExceptionResp_t e = ((ExceptionResp_tMsg)result.anObject).value;
			throw new PREMISServerException(ExceptionCode.ERR_GWS_PREMISSERVER, e.OutExcp.ERR_TX, String.valueOf(e.OutExcp.ERR_CD));
		default:
			throw new PREMISServerException(ExceptionCode.ERR_GWS_PREMISSERVER, "PREMISServerHelper::evalPremisValdtAddrReq: Unexpected return from sendAndReceive() " +
				result.event);
	}
}
/**
 * Event 6070 (send and receive - already connected).
 * Creation date: (4/16/00 2:22:56 PM)
 * @return com.sbc.gwsvcs.access.vicuna.EventResultPair
 * @param aTimeOut long
 * @param request com.sbc.gwsvcs.service.premisserver.interfaces.PremisTnMttReq_t
 * @exception com.sbc.gwsvcs.service.premisserver.exceptions.PREMISServerException: an PREMISServer exception occurred
 * @exception com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException: a service exception occurred.
 */
public EventResultPair premisTnMttReq(long aTimeOut, PremisTnMttReq_t request) throws PREMISServerException, ServiceException
{
	PREMISServerAccess premisServerAccess = (PREMISServerAccess)serviceAccess;

	EventObjectPair inRequest = new EventObjectPair(premisServerAccess.PREMIS_TN_MTT_REQ, new PremisTnMttReq_tMsg(request),premisServerAccess.PREMIS_TN_MTT_REQ_NBR);

	return evalPremisTnMttReq(premisServerAccess.sendAndReceive(factorTimeOut(aTimeOut), inRequest, premisTnMttExpected));
}
/**
 * Event 6070 (connect, send, receive and disconnect).
 * Creation date: (4/16/00 2:22:56 PM)
 * @return com.sbc.gwsvcs.access.vicuna.EventResultPair
 * @param aTimeOut long
 * @param request com.sbc.gwsvcs.service.premisserver.interfaces.PremisTnMttReq_t
 * @exception com.sbc.gwsvcs.service.premisserver.exceptions.PREMISServerException: an PREMISServer exception occurred
 * @exception com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException: a service exception occurred.
 */
public EventResultPair premisTnMttReq(String anApplData, String aService, long aTimeOut, PremisTnMttReq_t request) throws PREMISServerException, ServiceException
{
	PREMISServerAccess premisServerAccess = (PREMISServerAccess)serviceAccess;

	EventObjectPair inRequest = new EventObjectPair(premisServerAccess.PREMIS_TN_MTT_REQ, new PremisTnMttReq_tMsg(request),premisServerAccess.PREMIS_TN_MTT_REQ_NBR);

	return evalPremisTnMttReq(premisServerAccess.connectSendReceiveAndDisconnect(anApplData, aService, factorTimeOut(aTimeOut), inRequest, premisTnMttExpected));
}
/**
 * Event 6050 (send and receive - already connected).
 * Creation date: (4/16/00 2:22:56 PM)
 * @return com.sbc.gwsvcs.access.vicuna.EventResultPair
 * @param aTimeOut long
 * @param request com.sbc.gwsvcs.service.premisserver.interfaces.PremisTnRetnReq_t
 * @exception com.sbc.gwsvcs.service.premisserver.exceptions.PREMISServerException: an PREMISServer exception occurred
 * @exception com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException: a service exception occurred.
 */
public EventResultPair premisTnRetnReq(long aTimeOut, PremisTnRetnReq_t request) throws PREMISServerException, ServiceException
{
	PREMISServerAccess premisServerAccess = (PREMISServerAccess)serviceAccess;

	EventObjectPair inRequest = new EventObjectPair(premisServerAccess.PREMIS_TN_RETN_REQ, new PremisTnRetnReq_tMsg(request),premisServerAccess.PREMIS_TN_RETN_REQ_NBR);

	return evalPremisTnRetnReq(premisServerAccess.sendAndReceive(factorTimeOut(aTimeOut), inRequest, premisTnRetnExpected));
}
/**
 * Event 6050 (connect, send, receive and disconnect).
 * Creation date: (4/16/00 2:22:56 PM)
 * @return com.sbc.gwsvcs.access.vicuna.EventResultPair
 * @param aTimeOut long
 * @param request com.sbc.gwsvcs.service.premisserver.interfaces.PremisTnRetnReq_t
 * @exception com.sbc.gwsvcs.service.premisserver.exceptions.PREMISServerException: an PREMISServer exception occurred
 * @exception com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException: a service exception occurred.
 */
public EventResultPair premisTnRetnReq(String anApplData, String aService, long aTimeOut, PremisTnRetnReq_t request) throws PREMISServerException, ServiceException
{
	PREMISServerAccess premisServerAccess = (PREMISServerAccess)serviceAccess;

	EventObjectPair inRequest = new EventObjectPair(premisServerAccess.PREMIS_TN_RETN_REQ, new PremisTnRetnReq_tMsg(request),premisServerAccess.PREMIS_TN_RETN_REQ_NBR);

	return evalPremisTnRetnReq(premisServerAccess.connectSendReceiveAndDisconnect(anApplData, aService, factorTimeOut(aTimeOut), inRequest, premisTnRetnExpected));
}
/**
 * Event 6030 (send and receive - already connected).
 * Creation date: (4/16/00 2:22:56 PM)
 * @return com.sbc.gwsvcs.access.vicuna.EventResultPair
 * @param aTimeOut long
 * @param request com.sbc.gwsvcs.service.premisserver.interfaces.PremisTnRsrvReq_t
 * @exception com.sbc.gwsvcs.service.premisserver.exceptions.PREMISServerException: an PREMISServer exception occurred
 * @exception com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException: a service exception occurred.
 */
public EventResultPair premisTnRsrvReq(long aTimeOut, PremisTnRsrvReq_t request) throws PREMISServerException, ServiceException
{
	PREMISServerAccess premisServerAccess = (PREMISServerAccess)serviceAccess;

	EventObjectPair inRequest = new EventObjectPair(premisServerAccess.PREMIS_TN_RSRV_REQ, new PremisTnRsrvReq_tMsg(request), premisServerAccess.PREMIS_TN_RSRV_REQ_NBR);

	return evalPremisTnRsrvReq(premisServerAccess.sendAndReceive(factorTimeOut(aTimeOut), inRequest, premisTnRsrvExpected));
}
/**
 * Event 6030 (connect, send, receive and disconnect).
 * Creation date: (4/16/00 2:22:56 PM)
 * @return com.sbc.gwsvcs.access.vicuna.EventResultPair
 * @param aTimeOut long
 * @param request com.sbc.gwsvcs.service.premisserver.interfaces.PremisTnRsrvReq_t
 * @exception com.sbc.gwsvcs.service.premisserver.exceptions.PREMISServerException: an PREMISServer exception occurred
 * @exception com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException: a service exception occurred.
 */
public EventResultPair premisTnRsrvReq(String anApplData, String aService, long aTimeOut, PremisTnRsrvReq_t request) throws PREMISServerException, ServiceException
{
	PREMISServerAccess premisServerAccess = (PREMISServerAccess)serviceAccess;

	EventObjectPair inRequest = new EventObjectPair(premisServerAccess.PREMIS_TN_RSRV_REQ, new PremisTnRsrvReq_tMsg(request), premisServerAccess.PREMIS_TN_RSRV_REQ_NBR);

	return evalPremisTnRsrvReq(premisServerAccess.connectSendReceiveAndDisconnect(anApplData, aService, factorTimeOut(aTimeOut), inRequest, premisTnRsrvExpected));
}
/**
 * Event 6040 (send and receive - already connected).
 * Creation date: (4/16/00 2:22:56 PM)
 * @return com.sbc.gwsvcs.access.vicuna.EventResultPair
 * @param aTimeOut long
 * @param request com.sbc.gwsvcs.service.premisserver.interfaces.PremisTnSaveReq_t
 * @exception com.sbc.gwsvcs.service.premisserver.exceptions.PREMISServerException: an PREMISServer exception occurred
 * @exception com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException: a service exception occurred.
 */
public EventResultPair premisTnSaveReq(long aTimeOut, PremisTnSaveReq_t request) throws PREMISServerException, ServiceException
{
	PREMISServerAccess premisServerAccess = (PREMISServerAccess)serviceAccess;

	EventObjectPair inRequest = new EventObjectPair(premisServerAccess.PREMIS_TN_SAVE_REQ, new PremisTnSaveReq_tMsg(request), premisServerAccess.PREMIS_TN_SAVE_REQ_NBR);

	return evalPremisTnSaveReq(premisServerAccess.sendAndReceive(factorTimeOut(aTimeOut), inRequest, premisTnSaveExpected));
}
/**
 * Event 6040 (connect, send, receive and disconnect).
 * Creation date: (4/16/00 2:22:56 PM)
 * @return com.sbc.gwsvcs.access.vicuna.EventResultPair
 * @param aTimeOut long
 * @param request com.sbc.gwsvcs.service.premisserver.interfaces.PremisTnSaveReq_t
 * @exception com.sbc.gwsvcs.service.premisserver.exceptions.PREMISServerException: an PREMISServer exception occurred
 * @exception com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException: a service exception occurred.
 */
public EventResultPair premisTnSaveReq(String anApplData, String aService, long aTimeOut, PremisTnSaveReq_t request) throws PREMISServerException, ServiceException
{
	PREMISServerAccess premisServerAccess = (PREMISServerAccess)serviceAccess;

	EventObjectPair inRequest = new EventObjectPair(premisServerAccess.PREMIS_TN_SAVE_REQ, new PremisTnSaveReq_tMsg(request), premisServerAccess.PREMIS_TN_SAVE_REQ_NBR);

	return evalPremisTnSaveReq(premisServerAccess.connectSendReceiveAndDisconnect(anApplData, aService, factorTimeOut(aTimeOut), inRequest, premisTnSaveExpected));
}
/**
 * Event 6060 (send and receive - already connected).
 * Creation date: (4/16/00 2:22:56 PM)
 * @return com.sbc.gwsvcs.access.vicuna.EventResultPair
 * @param aTimeOut long
 * @param request com.sbc.gwsvcs.service.premisserver.interfaces.PremisTnStaReq_t
 * @exception com.sbc.gwsvcs.service.premisserver.exceptions.PREMISServerException: an PREMISServer exception occurred
 * @exception com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException: a service exception occurred.
 */
public EventResultPair premisTnStaReq(long aTimeOut, PremisTnStaReq_t request) throws PREMISServerException, ServiceException
{
	PREMISServerAccess premisServerAccess = (PREMISServerAccess)serviceAccess;

	EventObjectPair inRequest = new EventObjectPair(premisServerAccess.PREMIS_TN_STA_REQ, new PremisTnStaReq_tMsg(request),premisServerAccess.PREMIS_TN_STA_REQ_NBR);

	return evalPremisTnStaReq(premisServerAccess.sendAndReceive(factorTimeOut(aTimeOut), inRequest, premisTnStaExpected));
}
/**
 * Event 6060 (connect, send, receive and disconnect).
 * Creation date: (4/16/00 2:22:56 PM)
 * @return com.sbc.gwsvcs.access.vicuna.EventResultPair
 * @param aTimeOut long
 * @param request com.sbc.gwsvcs.service.premisserver.interfaces.PremisTnStaReq_t
 * @exception com.sbc.gwsvcs.service.premisserver.exceptions.PREMISServerException: an PREMISServer exception occurred
 * @exception com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException: a service exception occurred.
 */
public EventResultPair premisTnStaReq(String anApplData, String aService, long aTimeOut, PremisTnStaReq_t request) throws PREMISServerException, ServiceException
{
	PREMISServerAccess premisServerAccess = (PREMISServerAccess)serviceAccess;

	EventObjectPair inRequest = new EventObjectPair(premisServerAccess.PREMIS_TN_STA_REQ, new PremisTnStaReq_tMsg(request),premisServerAccess.PREMIS_TN_STA_REQ_NBR);

	return evalPremisTnStaReq(premisServerAccess.connectSendReceiveAndDisconnect(anApplData, aService, factorTimeOut(aTimeOut), inRequest, premisTnStaExpected));
}
/**
 * Event 6000 (connect, send, receive and disconnect).
 * Creation date: (4/16/00 2:22:56 PM)
 * @return com.sbc.gwsvcs.access.vicuna.EventResultPair
 * @param aTimeOut long
 * @param request com.sbc.gwsvcs.service.premisserver.interfaces.PremisValdtAddrReq_t
 * @exception com.sbc.gwsvcs.service.premisserver.exceptions.PREMISServerException: an PREMISServer exception occurred
 * @exception com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException: a service exception occurred.
 */
public EventResultPair premisValdtAddrReq(long aTimeOut, PremisValdtAddrReq_t request) throws PREMISServerException, ServiceException
{
	PREMISServerAccess premisServerAccess = (PREMISServerAccess)serviceAccess;

	EventObjectPair inRequest = new EventObjectPair(premisServerAccess.PREMIS_VALDT_ADDR_REQ, new PremisValdtAddrReq_tMsg(request), premisServerAccess.PREMIS_VALDT_ADDR_REQ_NBR);

	return evalPremisValdtAddrReq(premisServerAccess.sendAndReceive(factorTimeOut(aTimeOut), inRequest, premisValdtAddrExpected));
}
/**
 * Event 6000 (receive only - already connnected and sent).
 * Creation date: (4/16/00 2:22:56 PM)
 * @return com.sbc.gwsvcs.access.vicuna.EventResultPair
 * @param aTimeOut double
 * @exception com.sbc.gwsvcs.service.premisserver.exceptions.PREMISServerException: an PREMISServer exception occurred
 * @exception com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException: a service exception occurred.
 */
public EventResultPair premisValdtAddrReq(double aTimeOut) throws PREMISServerException, ServiceException
{
	PREMISServerAccess premisServerAccess = (PREMISServerAccess)serviceAccess;

	return evalPremisValdtAddrReq(premisServerAccess.receive(premisValdtAddrExpected,  (long)(aTimeOut * getTimeOutFactor())) );
}
/**
 * Event 6000 (send only - already connected).
 * Creation date: (4/16/00 2:22:56 PM)
 * @return com.sbc.gwsvcs.access.vicuna.EventResultPair
 * @param request com.sbc.gwsvcs.service.premisserver.interfaces.PremisValdtAddrReq_t
 * @exception com.sbc.gwsvcs.service.premisserver.exceptions.PREMISServerException: an PREMISServer exception occurred
 * @exception com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException: a service exception occurred.
 */
public void premisValdtAddrReq(PremisValdtAddrReq_t request) throws PREMISServerException, ServiceException
{
	PREMISServerAccess premisServerAccess = (PREMISServerAccess)serviceAccess;

	EventObjectPair inRequest = new EventObjectPair(premisServerAccess.PREMIS_VALDT_ADDR_REQ, new PremisValdtAddrReq_tMsg(request), premisServerAccess.PREMIS_VALDT_ADDR_REQ_NBR);

	premisServerAccess.send(inRequest);
}
/**
 * Event 6000 (send and receive - already connected).
 * Creation date: (4/16/00 2:22:56 PM)
 * @return com.sbc.gwsvcs.access.vicuna.EventResultPair
 * @param aTimeOut long
 * @param request com.sbc.gwsvcs.service.premisserver.interfaces.PremisValdtAddrReq_t
 * @exception com.sbc.gwsvcs.service.premisserver.exceptions.PREMISServerException: an PREMISServer exception occurred
 * @exception com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException: a service exception occurred.
 */
public EventResultPair premisValdtAddrReq(String anApplData, String aService, long aTimeOut, PremisValdtAddrReq_t request) throws PREMISServerException, ServiceException
{
	PREMISServerAccess premisServerAccess = (PREMISServerAccess)serviceAccess;
	
	EventObjectPair inRequest = new EventObjectPair(premisServerAccess.PREMIS_VALDT_ADDR_REQ, new PremisValdtAddrReq_tMsg(request), premisServerAccess.PREMIS_VALDT_ADDR_REQ_NBR);

	return evalPremisValdtAddrReq(premisServerAccess.connectSendReceiveAndDisconnect(anApplData, aService, factorTimeOut(aTimeOut), inRequest, premisValdtAddrExpected));
}
}
