// $Id: FACSAccessHelper.java,v 1.1 2002/09/29 04:15:31 dm2328 Exp $

package com.sbc.gwsvcs.service.facsaccess;

import com.sbc.eia.idl.exception_types.*;
import com.sbc.gwsvcs.access.vicuna.*;
import com.sbc.gwsvcs.access.vicuna.exceptions.*;
import com.sbc.gwsvcs.service.facsaccess.exceptions.*;
import com.sbc.gwsvcs.service.facsaccess.interfaces.*;
import com.sbc.vicunalite.api.*;
/**
 * Insert the type's description here.
 * Creation date: (1/24/04 9:13:39 AM)
 * @author: Ram Kishore (rk7964)
 */
public class FACSAccessHelper extends ServiceHelper {
 		
	EventClassPair facsDataExpected[] = new EventClassPair [] { 
		new EventClassPair(FACSAccessAccess.INQ_FASG_DATA_RESP, Result_tMsg.class, FACSAccessAccess.INQ_FASG_DATA_RESP_NBR),
		new EventClassPair(FACSAccessAccess.FACS_ERROR, ExceptionResp_tMsg.class, FACSAccessAccess.FACS_ERROR_NBR),
	    };

/**
 * FACSAccessHelper constructor comment.
 * @param properties java.util.Hashtable
 * @param aLogger com.sbc.bccs.utilities.Logger
 * 
 */
public FACSAccessHelper(java.util.Hashtable properties, com.sbc.bccs.utilities.Logger aLogger) throws ServiceException{
	super(properties, aLogger, FACSAccessAccess.name);
serviceAccess = new FACSAccessAccess(vicunaXmlFile, serviceXmlDir, logger);
	if (extractedTimeOut != null)
		setDefaultTimeOut(Integer.parseInt(extractedTimeOut));
	setDefaultApplData(extractedApplData);
	}



/**
 * Insert the method's description here.
 * Creation date:
 * @return com.sbc.gwsvcs.access.vicuna.EventResultPair
 * @param result com.sbc.gwsvcs.access.vicuna.EventObjectPair
 * @exception com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException The exception description.
 */
private EventResultPair evalGetInqFasgReq(EventObjectPair result) throws ServiceException {
	switch(result.eventNbr)
	{
		case FACSAccessAccess.INQ_FASG_DATA_RESP_NBR:
			return new EventResultPair(((Result_tMsg)result.anObject).value, result.eventNbr);

		case FACSAccessAccess.FACS_ERROR_NBR:
		{
			ExceptionResp_t e = ((ExceptionResp_tMsg)result.anObject).value;
			throw new FACSAccessException(ExceptionCode.ERR_GWS_FACS_ERROR, e.OutExcp.ERR_TX, String.valueOf(e.OutExcp.ERR_CD));
		}
		default:
			throw new FACSAccessException(ExceptionCode.ERR_GWS_FACS_ERROR, "FACSAccessHelper::evalGetInqFasgReq: Unexpected return from sendAndReceive() " +
				result.event);
	}
}

/**
 * Insert the method's description here.
 * Creation date: (1/24/04 10:25:49 AM)
 * @return com.sbc.gwsvcs.access.vicuna.EventResultPair
 * @param aTimeOut long
 * @param request com.sbc.gwsvcs.service.facsaccess.interfaces.FACSQuery
 * @exception com.sbc.gwsvcs.service.facsaccess.exceptions.FACSAccessException The exception description.
 * @exception com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException The exception description.
 */
public EventResultPair inqFasgReq(long aTimeOut, Fasg_Inq_Req_t request) 
throws FACSAccessException, ServiceException
{
	FACSAccessAccess facsAccess = (FACSAccessAccess)serviceAccess;
	
	EventObjectPair inRequest = new EventObjectPair(FACSAccessAccess.INQ_FASG_REQ,
	    new Fasg_Inq_Req_tMsg(request), FACSAccessAccess.INQ_FASG_REQ_NBR);
	     
	
	return evalGetInqFasgReq(facsAccess.sendAndReceive(factorTimeOut(aTimeOut), inRequest, facsDataExpected));
		
}
/**
 * Insert the method's description here.
 * Creation date: (1/24/04 10:41:23 AM)
 * @return com.sbc.gwsvcs.access.vicuna.EventResultPair
 * @param anApplData java.lang.String
 * @param aService java.lang.String
 * @param aTimeOut long
 * @param request com.sbc.gwsvcs.service.facsaccess.interfaces.FACSQuery
 * @exception com.sbc.gwsvcs.service.facsaccess.exceptions.FACSAccessException The exception description.
 * @exception com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException The exception description.
 */
public EventResultPair inqFasgReq(String anApplData, String aService, long aTimeOut, Fasg_Inq_Req_t request)
		  throws FACSAccessException,ServiceException 
		  {
		 FACSAccessAccess facsAccess = (FACSAccessAccess)serviceAccess;
	
	       EventObjectPair inRequest = new EventObjectPair(facsAccess.INQ_FASG_REQ,
	    new Fasg_Inq_Req_tMsg(request), facsAccess.INQ_FASG_REQ_NBR);
	     
	
	return evalGetInqFasgReq(facsAccess.connectSendReceiveAndDisconnect(anApplData, aService, factorTimeOut(aTimeOut), inRequest, facsDataExpected));	

}


}
