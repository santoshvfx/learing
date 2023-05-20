// $Id: ServiceHelperHolderSupport.java,v 1.1 2002/09/29 05:31:38 dm2328 Exp $

package com.sbc.gwsvcs.access.vicuna;

import com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException;
/**
 * Defines functionality required of the user of ServiceHelperHolder.
 * Creation date: (8/31/01 9:23:57 AM)
 * @author: Creighton Malet
 */
public interface ServiceHelperHolderSupport
{
/**
 * Filters out application type exceptions.
 * This method must RETURN any exception that should not cause a disconnect/connect (and possible
 * retry) sequence. Returned exceptions are thrown to the client with no further action.
 * It is very important that the top level ServiceExceptions (including ServiceTimeoutException) are
 * thrown back out to the calling controller because they MUST cause a disconnect/reconnect (and may
 * cause a retry if retry was specified). Generally the RETURNED exception is the top-level exception
 * specific to the wrapper eg: SWITCHServerException
 * @param aServiceException com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException
 * @exception com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException
 */
public ServiceException filterException(ServiceException aServiceException) throws ServiceException;
/**
 * Returns a new ServiceHelper specific to the implementation.
 * ServiceExceptions should not be managed.
 * @return com.sbc.gwsvcs.access.vicuna.ServiceHelper
 * @exception com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException: a service exception occurred.
 */
public ServiceHelper getNewServiceHelper() throws ServiceException;
/**
 * Runs the transaction.
 * The parameters are those as supplied by the client in the incoming request.
 * ServiceExceptions should not be managed.
 * @param transactionId int
 * @param input java.lang.Object
 * @param aTimeOut long
 * @return com.sbc.gwsvcs.access.vicuna.EventResultPair
 * @exception com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException: a service exception occurred.
 */
public EventResultPair runTransaction(int transactionId, Object input, long aTimeOut) throws ServiceException;
/**
 * Sets the caller context for BIS logging.
 * Creation date: (8/31/01 9:24:39 AM)
 * @param aContext java.lang.Object
 */
public void setCallerContext(Object aContext);
}
