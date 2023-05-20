// $Id: ASONServiceHelper.java,v 1.2 2002/09/29 03:54:12 dm2328 Exp $

package com.sbc.gwsvcs.service.asonservice;

import java.util.*;
import com.sbc.vicunalite.api.*;
import com.sbc.gwsvcs.service.asonservice.exceptions.*;
import com.sbc.gwsvcs.service.asonservice.interfaces.*;
import com.sbc.gwsvcs.access.vicuna.*;
import com.sbc.gwsvcs.access.vicuna.exceptions.*;
import com.sbc.eia.idl.exception_types.*;

/**
 * A collection of helper methods for access to ASONService Service.
 * Creation date: (4/26/01 11:35:56 AM)
 * @author: David Brawley
 */
public class ASONServiceHelper extends ServiceHelper 
{

	public final static int RECEIVE_ALL_MESSAGES = -1;
	private int maximumMessagesToReceive = RECEIVE_ALL_MESSAGES;

	static final EventClassPair asonSagValRespExpected[] = new EventClassPair [] { 
			new EventClassPair(ASONServiceAccess.ASON_SAG_VALIDATION_RESP, ASONSagValRespMsg.class, ASONServiceAccess.ASON_SAG_VALIDATION_RESP_NBR),
			new EventClassPair(ASONServiceAccess.ASON_SAG_VALIDATION_ERR, ASONErrorRespMsg.class, ASONServiceAccess.ASON_SAG_VALIDATION_ERR_NBR),
			new EventClassPair(ASONServiceAccess.DG_TANDEM_ERR, TandemDgSrvErrMsg.class, ASONServiceAccess.DG_TANDEM_ERR_NBR)
	};
	static final EventClassPair asonSagValidRespExpected[] = new EventClassPair [] { 
			new EventClassPair(ASONServiceAccess.ASON_SAG_VALID_RESP, ASONSagValidRespMsg.class, ASONServiceAccess.ASON_SAG_VALID_RESP_NBR),
			new EventClassPair(ASONServiceAccess.ASON_SAG_VALID_ERR, ASONSagValidErrMsg.class, ASONServiceAccess.ASON_SAG_VALID_ERR_NBR),
			new EventClassPair(ASONServiceAccess.ASON_SAG_VALID_APP_ERR, ASONSagValidAppErrMsg.class, ASONServiceAccess.ASON_SAG_VALID_APP_ERR_NBR),
			new EventClassPair(ASONServiceAccess.ASON_SAG_VALID_DESC_ERR, ASONSagValidDescErrMsg.class, ASONServiceAccess.ASON_SAG_VALID_DESC_ERR_NBR),
			new EventClassPair(ASONServiceAccess.ASON_SAG_VALID_INVREQ_ERR, ASONSagValidInvReqErrMsg.class, ASONServiceAccess.ASON_SAG_VALID_INVREQ_ERR_NBR),
			new EventClassPair(ASONServiceAccess.DG_TANDEM_ERR, TandemDgSrvErrMsg.class, ASONServiceAccess.DG_TANDEM_ERR_NBR)
	};
	static final EventClassPair asonSagInqRespExpected[] = new EventClassPair [] { 
			new EventClassPair(ASONServiceAccess.ASON_SAG_INQUIRY_RESP, ASONSagInqRespMsg.class, ASONServiceAccess.ASON_SAG_INQUIRY_RESP_NBR),
			new EventClassPair(ASONServiceAccess.ASON_SAG_INQUIRY_ERR, ASONErrorRespMsg.class, ASONServiceAccess.ASON_SAG_INQUIRY_ERR_NBR),
			new EventClassPair(ASONServiceAccess.DG_TANDEM_ERR, TandemDgSrvErrMsg.class, ASONServiceAccess.DG_TANDEM_ERR_NBR)
	};

	static final EventClassPair asonLvngUnitValRespExpected[] = new EventClassPair [] { 
			new EventClassPair(ASONServiceAccess.ASON_LU_VALIDATION_RESP, ASONLvngUnitValRespMsg.class, ASONServiceAccess.ASON_LU_VALIDATION_RESP_NBR),
			new EventClassPair(ASONServiceAccess.ASON_LU_VALIDATION_ERR, ASONErrorRespMsg.class, ASONServiceAccess.ASON_LU_VALIDATION_ERR_NBR),
			new EventClassPair(ASONServiceAccess.DG_TANDEM_ERR, TandemDgSrvErrMsg.class, ASONServiceAccess.DG_TANDEM_ERR_NBR)
	};

	static final EventClassPair asonLvngUnitInqRespExpected[] = new EventClassPair [] { 
			new EventClassPair(ASONServiceAccess.ASON_LU_INQUIRY_RESP, ASONLvngUnitInqRespMsg.class, ASONServiceAccess.ASON_LU_INQUIRY_RESP_NBR),
			new EventClassPair(ASONServiceAccess.ASON_LU_INQUIRY_ERR, ASONErrorRespMsg.class, ASONServiceAccess.ASON_LU_INQUIRY_ERR_NBR),
			new EventClassPair(ASONServiceAccess.DG_TANDEM_ERR, TandemDgSrvErrMsg.class, ASONServiceAccess.DG_TANDEM_ERR_NBR)
	};

	static final EventClassPair asonDueDateRespExpected[] = new EventClassPair [] { 
			new EventClassPair(ASONServiceAccess.ASON_DUE_DATE_RESP, ASONDueDateRespMsg.class, ASONServiceAccess.ASON_DUE_DATE_RESP_NBR),
			new EventClassPair(ASONServiceAccess.ASON_DUE_DATE_ERR, ASONDueDateErrMsg.class, ASONServiceAccess.ASON_DUE_DATE_ERR_NBR),
			new EventClassPair(ASONServiceAccess.DG_TANDEM_ERR, TandemDgSrvErrMsg.class, ASONServiceAccess.DG_TANDEM_ERR_NBR)
	};

/**
 * Construct a asonserviceHelper object.
 * Creation date: (4/26/01 11:36:38 AM)
 * @param properties java.util.Hashtable
 * @param aLogger com.sbc.bccs.utilities.Logger
 */
public ASONServiceHelper(java.util.Hashtable properties, com.sbc.bccs.utilities.Logger aLogger)
	throws ServiceException
{
	super(properties, aLogger, ASONServiceAccess.name);

	serviceAccess = new ASONServiceAccess(vicunaXmlFile, serviceXmlDir, logger);
	if (extractedTimeOut != null)
		setDefaultTimeOut(Integer.parseInt(extractedTimeOut));
	setDefaultApplData(extractedApplData);
}
/**
 * Helper to request ASONService.
 * Creation date: (4/26/01 2:22:56 PM)
 * @param aTimeOut long
 * @param request com.sbc.gwsvcs.service.asonservice.interfaces.ASONOpenDatesReq
 */
public EventResultPair asonDueDateReq(long aTimeOut, ASONDueDateReq request) throws ServiceException
{
	ASONServiceAccess asonServiceAccess = (ASONServiceAccess)serviceAccess;

	EventObjectPair inRequest = new EventObjectPair(asonServiceAccess.ASON_DUE_DATE_REQ, 
								new ASONDueDateReqMsg(request),ASONServiceAccess.ASON_DUE_DATE_REQ_NBR);

	return evalASONDueDateReq(asonServiceAccess.sendAndReceive(factorTimeOut(aTimeOut), inRequest, asonDueDateRespExpected));
}
/**
 * Helper to request ASONService
 * Creation date: (4/26/01 2:22:56 PM)
 * @param anApplData java.lang.String
 * @param aService java.lang.String
 * @param aTimeOut long
 * @param request com.sbc.gwsvcs.service.asonservice.interfaces.ASONOpenDatesReq
 */
public EventResultPair asonDueDateReq(String anApplData, String aService, long aTimeOut, ASONDueDateReq request) throws ServiceException
{
	ASONServiceAccess asonServiceAccess = (ASONServiceAccess)serviceAccess;

	EventObjectPair inRequest = new EventObjectPair(asonServiceAccess.ASON_DUE_DATE_REQ, new ASONDueDateReqMsg(request),ASONServiceAccess.ASON_DUE_DATE_REQ_NBR);

	return evalASONDueDateReq(asonServiceAccess.connectSendReceiveAndDisconnect(anApplData, aService, factorTimeOut(aTimeOut), inRequest, asonDueDateRespExpected));
}
/**
 * Helper to request ASONService.
 * Creation date: (4/26/01 2:22:56 PM)
 * @param aTimeOut long
 * @param request com.sbc.gwsvcs.service.asonservice.interfaces.ASONLvngUnitInqReq
 */
public Vector asonLvngUnitInqReq(long aTimeOut, ASONLvngUnitInqReq request) throws ServiceException
{
	ASONServiceAccess asonServiceAccess = (ASONServiceAccess)serviceAccess;

	EventObjectPair inRequest = new EventObjectPair(asonServiceAccess.ASON_LU_INQUIRY_REQ, 
								new ASONLvngUnitInqReqMsg(request),ASONServiceAccess.ASON_LU_INQUIRY_REQ_NBR);

	Vector resultSet = new Vector();	
	EventResultPair result;
	try {
		result = evalASONLvngUnitInqReq(asonServiceAccess.sendAndReceive(factorTimeOut(aTimeOut), inRequest, asonLvngUnitInqRespExpected));
		int numberOfReceivedMessages = 1;
		while ((!((((ASONLvngUnitInqResp)result.getTheObject()).tagInformation.tagErrCode.trim()).equalsIgnoreCase("END"))) &&
			((maximumMessagesToReceive == RECEIVE_ALL_MESSAGES) || (numberOfReceivedMessages < maximumMessagesToReceive)))
		{
			resultSet.add((ASONLvngUnitInqResp) result.getTheObject());
			result = evalASONLvngUnitInqReq(asonServiceAccess.receive(asonLvngUnitInqRespExpected, factorTimeOut(aTimeOut)));
			numberOfReceivedMessages++;
		}
		resultSet.add((ASONLvngUnitInqResp) result.getTheObject());
	}
	finally {
		disconnect();
	}

	
	return resultSet;
}
/**
 * Helper to request ASONService
 * Creation date: (4/26/01 2:22:56 PM)
 * @param anApplData java.lang.String
 * @param aService java.lang.String
 * @param aTimeOut long
 * @param request com.sbc.gwsvcs.service.asonservice.interfaces.ASONLvngUnitInqReq
 */
public Vector asonLvngUnitInqReq(String anApplData, String aService, long aTimeOut, ASONLvngUnitInqReq request) throws ServiceException
{
	ASONServiceAccess asonServiceAccess = (ASONServiceAccess)serviceAccess;

	EventObjectPair inRequest = new EventObjectPair(asonServiceAccess.ASON_LU_INQUIRY_REQ, 
								new ASONLvngUnitInqReqMsg(request),ASONServiceAccess.ASON_LU_INQUIRY_REQ_NBR);

	Vector resultSet = new Vector();	
	EventResultPair result;
	try {
		connect(anApplData, aService);
		result = evalASONLvngUnitInqReq(asonServiceAccess.sendAndReceive(factorTimeOut(aTimeOut), inRequest, asonLvngUnitInqRespExpected));
		int numberOfReceivedMessages = 1;
		while ((!((((ASONLvngUnitInqResp)result.getTheObject()).tagInformation.tagErrCode.trim()).equalsIgnoreCase("END"))) &&
			((maximumMessagesToReceive == RECEIVE_ALL_MESSAGES) || (numberOfReceivedMessages < maximumMessagesToReceive)))
		{
			resultSet.add((ASONLvngUnitInqResp) result.getTheObject());
			result = evalASONLvngUnitInqReq(asonServiceAccess.receive(asonLvngUnitInqRespExpected, factorTimeOut(aTimeOut)));
			numberOfReceivedMessages++;
		}
		resultSet.add((ASONLvngUnitInqResp) result.getTheObject());
	}
	finally {
		disconnect();
	}

	
	return resultSet;
}
/**
 * Helper to request ASONService.
 * Creation date: (4/26/01 2:22:56 PM)
 * @param aTimeOut long
 * @param request com.sbc.gwsvcs.service.asonservice.interfaces.ASONLvngUnitValReq
 */
public EventResultPair asonLvngUnitValReq(long aTimeOut, ASONLvngUnitValReq request) throws ServiceException
{
	ASONServiceAccess asonServiceAccess = (ASONServiceAccess)serviceAccess;

	EventObjectPair inRequest = new EventObjectPair(asonServiceAccess.ASON_LU_VALIDATION_REQ, 
								new ASONLvngUnitValReqMsg(request),ASONServiceAccess.ASON_LU_VALIDATION_REQ_NBR);

	return evalASONLvngUnitValReq(asonServiceAccess.sendAndReceive(factorTimeOut(aTimeOut), inRequest, asonLvngUnitValRespExpected));
}
/**
 * Helper to request ASONService
 * Creation date: (4/26/01 2:22:56 PM)
 * @param anApplData java.lang.String
 * @param aService java.lang.String
 * @param aTimeOut long
 * @param request com.sbc.gwsvcs.service.asonservice.interfaces.ASONLvngUnitValReq
 */
public EventResultPair asonLvngUnitValReq(String anApplData, String aService, long aTimeOut, ASONLvngUnitValReq request) throws ServiceException
{
	ASONServiceAccess asonServiceAccess = (ASONServiceAccess)serviceAccess;

	EventObjectPair inRequest = new EventObjectPair(asonServiceAccess.ASON_LU_VALIDATION_REQ, 
								new ASONLvngUnitValReqMsg(request),ASONServiceAccess.ASON_LU_VALIDATION_REQ_NBR);

	return evalASONLvngUnitValReq(asonServiceAccess.connectSendReceiveAndDisconnect(anApplData, aService, factorTimeOut(aTimeOut), inRequest, asonLvngUnitValRespExpected));
}
/**
 * Helper to request ASONService.
 * Creation date: (4/26/01 2:22:56 PM)
 * @param aTimeOut long
 * @param request com.sbc.gwsvcs.service.asonservice.interfaces.ASONSag1InqReq
 */
public Vector asonSagInqReq(long aTimeOut, ASONSagInqReq request) throws ServiceException
{
	ASONServiceAccess asonServiceAccess = (ASONServiceAccess)serviceAccess;

	EventObjectPair inRequest = new EventObjectPair(asonServiceAccess.ASON_SAG_INQUIRY_REQ, 
								new ASONSagInqReqMsg(request),ASONServiceAccess.ASON_SAG_INQUIRY_REQ_NBR);

	Vector resultSet = new Vector();	
	EventResultPair result;
	try {
		result = evalASONSagInqReq(asonServiceAccess.sendAndReceive(factorTimeOut(aTimeOut), inRequest, asonSagInqRespExpected));
		int numberOfReceivedMessages = 1;
		while ((!((((ASONSagInqResp)result.getTheObject()).tagInformation.tagErrCode.trim()).equalsIgnoreCase("END"))) &&
			((maximumMessagesToReceive == RECEIVE_ALL_MESSAGES) || (numberOfReceivedMessages < maximumMessagesToReceive)))
		{
			resultSet.add((ASONSagInqResp) result.getTheObject());
			result = evalASONSagInqReq(asonServiceAccess.receive(asonSagInqRespExpected, factorTimeOut(aTimeOut)));
			numberOfReceivedMessages++;
		}
		resultSet.add((ASONSagInqResp) result.getTheObject());
	}
	finally {
		disconnect();
	}
	
	return resultSet;

}
/**
 * Helper to request ASONService
 * Creation date: (4/26/01 2:22:56 PM)
 * @param anApplData java.lang.String
 * @param aService java.lang.String
 * @param aTimeOut long
 * @param request com.sbc.gwsvcs.service.asonservice.interfaces.ASONSagInqReq
 */
public Vector asonSagInqReq(String anApplData, String aService, long aTimeOut, ASONSagInqReq request) throws ServiceException
{
	ASONServiceAccess asonServiceAccess = (ASONServiceAccess)serviceAccess;

	EventObjectPair inRequest = new EventObjectPair(asonServiceAccess.ASON_SAG_INQUIRY_REQ, 
								new ASONSagInqReqMsg(request),ASONServiceAccess.ASON_SAG_INQUIRY_REQ_NBR);

	Vector resultSet = new Vector();	
	EventResultPair result;
	try {
		connect(anApplData, aService);
		result = evalASONSagInqReq(asonServiceAccess.sendAndReceive(factorTimeOut(aTimeOut), inRequest, asonSagInqRespExpected));
		int numberOfReceivedMessages = 1;
		while ((!((((ASONSagInqResp)result.getTheObject()).tagInformation.tagErrCode.trim()).equalsIgnoreCase("END"))) &&
			((maximumMessagesToReceive == RECEIVE_ALL_MESSAGES) || (numberOfReceivedMessages < maximumMessagesToReceive)))
		{
			resultSet.add((ASONSagInqResp) result.getTheObject());
			result = evalASONSagInqReq(asonServiceAccess.receive(asonSagInqRespExpected, factorTimeOut(aTimeOut)));
			numberOfReceivedMessages++;
		}
		resultSet.add((ASONSagInqResp) result.getTheObject());
	}
	finally {
		disconnect();
	}
	return resultSet;









	
}
/**
 * Helper to request ASONService.
 * Creation date: (4/26/01 2:22:56 PM)
 * @param aTimeOut long
 * @param request com.sbc.gwsvcs.service.asonservice.interfaces.ASONSagValidReq
 */
public EventResultPair asonSagValidReq(long aTimeOut, ASONSagValidReq request) throws ServiceException
{
	ASONServiceAccess asonServiceAccess = (ASONServiceAccess)serviceAccess;

	EventObjectPair inRequest = new EventObjectPair(asonServiceAccess.ASON_SAG_VALID_REQ, 
								new ASONSagValidReqMsg(request),ASONServiceAccess.ASON_SAG_VALID_REQ_NBR);

	return evalASONSagValidReq(asonServiceAccess.sendAndReceive(factorTimeOut(aTimeOut), inRequest, asonSagValidRespExpected));
}
/**
 * Helper to request ASONService
 * Creation date: (4/26/01 2:22:56 PM)
 * @param anApplData java.lang.String
 * @param aService java.lang.String
 * @param aTimeOut long
 * @param request com.sbc.gwsvcs.service.asonservice.interfaces.ASONSagValidReq
 */
public EventResultPair asonSagValidReq(String anApplData, String aService, long aTimeOut, ASONSagValidReq request) throws ServiceException
{
	ASONServiceAccess asonServiceAccess = (ASONServiceAccess)serviceAccess;

	EventObjectPair inRequest = new EventObjectPair(asonServiceAccess.ASON_SAG_VALID_REQ, new ASONSagValidReqMsg(request),ASONServiceAccess.ASON_SAG_VALID_REQ_NBR);

	return evalASONSagValidReq(asonServiceAccess.connectSendReceiveAndDisconnect(anApplData, aService, factorTimeOut(aTimeOut), inRequest, asonSagValidRespExpected));
}
/**
 * Helper to request ASONService.
 * Creation date: (4/26/01 2:22:56 PM)
 * @param aTimeOut long
 * @param request com.sbc.gwsvcs.service.asonservice.interfaces.ASONSagValReq
 */
public EventResultPair asonSagValReq(long aTimeOut, ASONSagValReq request) throws ServiceException
{
	ASONServiceAccess asonServiceAccess = (ASONServiceAccess)serviceAccess;

	EventObjectPair inRequest = new EventObjectPair(asonServiceAccess.ASON_SAG_VALIDATION_REQ, 
								new ASONSagValReqMsg(request),ASONServiceAccess.ASON_SAG_VALIDATION_REQ_NBR);

	return evalASONSagValReq(asonServiceAccess.sendAndReceive(factorTimeOut(aTimeOut), inRequest, asonSagValRespExpected));
}
/**
 * Helper to request ASONService
 * Creation date: (4/26/01 2:22:56 PM)
 * @param anApplData java.lang.String
 * @param aService java.lang.String
 * @param aTimeOut long
 * @param request com.sbc.gwsvcs.service.asonservice.interfaces.ASONSagValReq
 */
public EventResultPair asonSagValReq(String anApplData, String aService, long aTimeOut, ASONSagValReq request) throws ServiceException
{
	ASONServiceAccess asonServiceAccess = (ASONServiceAccess)serviceAccess;

	EventObjectPair inRequest = new EventObjectPair(asonServiceAccess.ASON_SAG_VALIDATION_REQ, new ASONSagValReqMsg(request),ASONServiceAccess.ASON_SAG_VALIDATION_REQ_NBR);

	return evalASONSagValReq(asonServiceAccess.connectSendReceiveAndDisconnect(anApplData, aService, factorTimeOut(aTimeOut), inRequest, asonSagValRespExpected));
}
/**
 * Evaluate response to asonservice by DIV.
 * Creation date: (4/26/01 2:22:56 PM)
 * @param result com.sbc.gwsvcs.access.vicuna.EventObjectPair
 */
private EventResultPair evalASONDueDateReq(EventObjectPair result) throws ServiceException
{
	switch(result.eventNbr)
	{
		case ASONServiceAccess.ASON_DUE_DATE_RESP_NBR:
			return new EventResultPair(((ASONDueDateRespMsg)result.anObject).value, result.eventNbr);
		case ASONServiceAccess.ASON_DUE_DATE_ERR_NBR:
			return new EventResultPair(((ASONDueDateErrMsg)result.anObject).value, result.eventNbr);
		case ASONServiceAccess.DG_TANDEM_ERR_NBR:
			TandemDgSrvErr te = ((TandemDgSrvErrMsg)result.anObject).value;
			throw new ASONServiceException(ExceptionCode.ERR_GWS_ASONSERVICE, String.valueOf(te.advisoryMsg), String.valueOf(ASONServiceAccess.ASON_DUE_DATE_RESP_NBR));
		default:
			throw new ASONServiceException(ExceptionCode.ERR_GWS_ASONSERVICE, "ASONServiceHelper::evalASONSagValReq: Unexpected return from sendAndReceive() " +
				result.event);
	}
}
/**
 * Evaluate response to asonservice by DIV.
 * Creation date: (4/26/01 2:22:56 PM)
 * @param result com.sbc.gwsvcs.access.vicuna.EventObjectPair
 */
private EventResultPair evalASONLvngUnitInqReq(EventObjectPair result) throws ServiceException
{
	switch(result.eventNbr)
	{
		case ASONServiceAccess.ASON_LU_INQUIRY_RESP_NBR:
			return new EventResultPair(((ASONLvngUnitInqRespMsg)result.anObject).value, result.eventNbr);
		case ASONServiceAccess.ASON_LU_INQUIRY_ERR_NBR:
			ASONErrorResp ae = ((ASONErrorRespMsg)result.anObject).value;
			throw new ASONServiceException(ExceptionCode.ERR_GWS_ASONSERVICE, String.valueOf(ae.advisoryMsg), String.valueOf(ASONServiceAccess.ASON_LU_INQUIRY_RESP_NBR));
		case ASONServiceAccess.DG_TANDEM_ERR_NBR:
			TandemDgSrvErr te = ((TandemDgSrvErrMsg)result.anObject).value;
			throw new ASONServiceException(ExceptionCode.ERR_GWS_ASONSERVICE, String.valueOf(te.advisoryMsg), String.valueOf(ASONServiceAccess.ASON_LU_INQUIRY_RESP_NBR));
		default:
			throw new ASONServiceException(ExceptionCode.ERR_GWS_ASONSERVICE, "ASONServiceHelper::evalASONLvngUnitInqReq: Unexpected return from sendAndReceive() " +
				result.event);
	}
}
/**
 * Evaluate response to asonservice by DIV.
 * Creation date: (4/26/01 2:22:56 PM)
 * @param result com.sbc.gwsvcs.access.vicuna.EventObjectPair
 */
private EventResultPair evalASONLvngUnitValReq(EventObjectPair result) throws ServiceException
{
	switch(result.eventNbr)
	{
		case ASONServiceAccess.ASON_LU_VALIDATION_RESP_NBR:
			return new EventResultPair(((ASONLvngUnitValRespMsg)result.anObject).value, result.eventNbr);
		case ASONServiceAccess.ASON_LU_VALIDATION_ERR_NBR:
			ASONErrorResp ae = ((ASONErrorRespMsg)result.anObject).value;
			throw new ASONServiceException(ExceptionCode.ERR_GWS_ASONSERVICE, String.valueOf(ae.advisoryMsg), String.valueOf(ASONServiceAccess.ASON_LU_VALIDATION_RESP_NBR));
		case ASONServiceAccess.DG_TANDEM_ERR_NBR:
			TandemDgSrvErr te = ((TandemDgSrvErrMsg)result.anObject).value;
			throw new ASONServiceException(ExceptionCode.ERR_GWS_ASONSERVICE, String.valueOf(te.advisoryMsg), String.valueOf(ASONServiceAccess.ASON_LU_VALIDATION_RESP_NBR));
		default:
			throw new ASONServiceException(ExceptionCode.ERR_GWS_ASONSERVICE, "ASONServiceHelper::evalASONLvngUnitValReq: Unexpected return from sendAndReceive() " +
				result.event);
	}
}
/**
 * Evaluate response to asonservice by DIV.
 * Creation date: (4/26/01 2:22:56 PM)
 * @param result com.sbc.gwsvcs.access.vicuna.EventObjectPair
 */
private EventResultPair evalASONSagInqReq(EventObjectPair result) throws ServiceException
{
	switch(result.eventNbr)
	{
		case ASONServiceAccess.ASON_SAG_INQUIRY_RESP_NBR:
			return new EventResultPair(((ASONSagInqRespMsg)result.anObject).value, result.eventNbr);
		case ASONServiceAccess.ASON_SAG_INQUIRY_ERR_NBR:
			ASONErrorResp ae = ((ASONErrorRespMsg)result.anObject).value;
			throw new ASONServiceException(ExceptionCode.ERR_GWS_ASONSERVICE, String.valueOf(ae.advisoryMsg), String.valueOf(ASONServiceAccess.ASON_SAG_INQUIRY_RESP_NBR));
		case ASONServiceAccess.DG_TANDEM_ERR_NBR:
			TandemDgSrvErr te = ((TandemDgSrvErrMsg)result.anObject).value;
			throw new ASONServiceException(ExceptionCode.ERR_GWS_ASONSERVICE, String.valueOf(te.advisoryMsg), String.valueOf(ASONServiceAccess.ASON_SAG_INQUIRY_RESP_NBR));
		default:
			throw new ASONServiceException(ExceptionCode.ERR_GWS_ASONSERVICE, "ASONServiceHelper::evalASONSagInqReq: Unexpected return from sendAndReceive() " +
				result.event);
	}
}
/**
 * Evaluate response to asonservice by DIV.
 * Creation date: (4/26/01 2:22:56 PM)
 * @param result com.sbc.gwsvcs.access.vicuna.EventObjectPair
 */
private EventResultPair evalASONSagValidReq(EventObjectPair result) throws ServiceException
{
	switch(result.eventNbr)
	{
		case ASONServiceAccess.ASON_SAG_VALID_RESP_NBR:
			return new EventResultPair(((ASONSagValidRespMsg)result.anObject).value, result.eventNbr);

		case ASONServiceAccess.ASON_SAG_VALID_ERR_NBR:
			ASONSagValidErr ae = ((ASONSagValidErrMsg)result.anObject).value;
			if (ae.comReplyHdr1.MsgCode.indexOf("W548") < 0) 
				throw new ASONServiceException(ExceptionCode.ERR_GWS_ASONSERVICE, ae.comReplyHdr1.MsgText, ae.comReplyHdr1.MsgCode);
			else
				return new EventResultPair(((ASONSagValidErrMsg)result.anObject).value, result.eventNbr);

		case ASONServiceAccess.ASON_SAG_VALID_APP_ERR_NBR:
			ASONSagValidAppErr aae = ((ASONSagValidAppErrMsg)result.anObject).value;
			throw new ASONServiceException(ExceptionCode.ERR_GWS_ASONSERVICE, aae.comReplyHdr1.MsgText, aae.comReplyHdr1.MsgCode);

		case ASONServiceAccess.ASON_SAG_VALID_DESC_ERR_NBR:
			return new EventResultPair(((ASONSagValidDescErrMsg)result.anObject).value, result.eventNbr);
			
		case ASONServiceAccess.ASON_SAG_VALID_INVREQ_ERR_NBR:
			ASONSagValidInvReqErr aie = ((ASONSagValidInvReqErrMsg)result.anObject).value;
			throw new ASONServiceException(ExceptionCode.ERR_GWS_ASONSERVICE, aie.comReplyHdr1.MsgText, aie.comReplyHdr1.MsgCode);

		case ASONServiceAccess.DG_TANDEM_ERR_NBR:
			TandemDgSrvErr te = ((TandemDgSrvErrMsg)result.anObject).value;
			throw new ASONServiceException(ExceptionCode.ERR_GWS_ASONSERVICE, te.advisoryMsg, String.valueOf(ASONServiceAccess.ASON_SAG_VALID_RESP_NBR));
		default:
			throw new ASONServiceException(ExceptionCode.ERR_GWS_ASONSERVICE, "ASONServiceHelper::evalASONSagValidReq: Unexpected return from sendAndReceive() " +
				result.event);
	}
}
/**
 * Evaluate response to asonservice by DIV.
 * Creation date: (4/26/01 2:22:56 PM)
 * @param result com.sbc.gwsvcs.access.vicuna.EventObjectPair
 */
private EventResultPair evalASONSagValReq(EventObjectPair result) throws ServiceException
{
	switch(result.eventNbr)
	{
		case ASONServiceAccess.ASON_SAG_VALIDATION_RESP_NBR:
			return new EventResultPair(((ASONSagValRespMsg)result.anObject).value, result.eventNbr);
		case ASONServiceAccess.ASON_SAG_VALIDATION_ERR_NBR:
			ASONErrorResp ae = ((ASONErrorRespMsg)result.anObject).value;
			if (ae.advisoryMsg.indexOf("C026") < 0) 
				throw new ASONServiceException(ExceptionCode.ERR_GWS_ASONSERVICE, String.valueOf(ae.advisoryMsg), String.valueOf(ASONServiceAccess.ASON_SAG_VALIDATION_RESP_NBR));
			else
				return new EventResultPair(((ASONErrorRespMsg)result.anObject).value, result.eventNbr);
		case ASONServiceAccess.DG_TANDEM_ERR_NBR:
			TandemDgSrvErr te = ((TandemDgSrvErrMsg)result.anObject).value;
			throw new ASONServiceException(ExceptionCode.ERR_GWS_ASONSERVICE, String.valueOf(te.advisoryMsg), String.valueOf(ASONServiceAccess.ASON_SAG_VALIDATION_RESP_NBR));
		default:
			throw new ASONServiceException(ExceptionCode.ERR_GWS_ASONSERVICE, "ASONServiceHelper::evalASONSagValReq: Unexpected return from sendAndReceive() " +
				result.event);
	}
}
/**
 * Gets the setting for the maximum number of messages to receive in a set
 * Creation date: (5/31/01 1:08:42 PM)
 * @return int
 */
public int getMaximumMessagesToReceive() {
	return maximumMessagesToReceive;
}
/**
 * Sets the maximum number of messages to receive in a set
 * Creation date: (5/31/01 1:08:42 PM)
 * @param newMaximumMessagesToReceive int
 */
public void setMaximumMessagesToReceive(int newMaximumMessagesToReceive) {
	maximumMessagesToReceive = newMaximumMessagesToReceive;
}
}
