// $Id: VicunaWrapperMultiplexerMethods.java,v 1.3 2003/02/14 17:23:54 as5472 Exp $

package com.sbc.gwsvcs.access.vicuna.multiplexer;

import com.sbc.gwsvcs.access.vicuna.ServiceHelper;
import com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException;
import com.sbc.gwsvcs.access.vicuna.exceptions.ServiceTimeoutException;
/**
 * This interface defines the essential methods that will do the sending
 * and receiving of messages 
 * Creation date: (6/28/01 3:30:59 PM)
 * @author: Creighton Malet
 */
public interface VicunaWrapperMultiplexerMethods {
	/**
	 * @author Creighton Malet
	 *
	 * This inner class used to represent the various transaction states of each transaction.
	 */
	final class TransactionState {
		int state = -1;

		public static final int _NEW = 0;
		// Reserved for VicunaWrapperMultiplexerWorker
		public static final int _SEND = 1;
		public static final int _RECEIVE = 2;
		public static final int _COMPLETE = 3;

		public static final String[] stateAsString =
			{ "New", "Send", "Receive", "Complete" };

		public static final TransactionState SEND = new TransactionState(_SEND);
		public static final TransactionState RECEIVE =
			new TransactionState(_RECEIVE);
		public static final TransactionState COMPLETE =
			new TransactionState(_COMPLETE);

		private TransactionState() {
		}

		private TransactionState(int aTransactionState) {
			state = aTransactionState;
		}

		public int state() {
			return state;
		}
		public boolean equals(TransactionState aTransactionState) {
			return this.state == aTransactionState.state;
		}
	}
	/**
	 * Must be implemented to provide a way to handle a connection failure.
	 * This method should return an appropriate transaction state.
	 * @param aData VicunaWrapperMultiplexerData
	 * @param param ServiceException
	 * @return TransactionState
	 */
	public TransactionState evaluateConnectionException(
		VicunaWrapperMultiplexerData aData,
		ServiceException param);
	/**
	 * Must be implemented to provide a way to handle an exception.
	 * This method should return an appropriate transaction state.
	 * @param aData VicunaWrapperMultiplexerData
	 * @param param ServiceException
	 * @return TransactionState
	 */
	public TransactionState evaluateException(
		VicunaWrapperMultiplexerData aData,
		ServiceException param);

	/**
	 * Must be implemented to handle a connection timeout.
	 * This method should return an appropriate transaction state.
	 * @param param1 VicunaWrapperMultiplexerData
	 * @param param2 ServiceTimeoutException
	 * @return TransactionState
	 */
	public TransactionState evaluateTimeOut(
		VicunaWrapperMultiplexerData param1,
		ServiceTimeoutException param2);
	/**
	 * Must be implemented to provide a way to create the requested
	 * number of service helpers.
	 * @param param int
	 * @return ServiceHelper[]
	 * @throws ServiceException
	 */
	public ServiceHelper[] getHelperInstances(int param)
		throws ServiceException;

	/**
	 * Must be implemented to build the service details.
	 * @param aData VicunaWrapperMultiplexerData
	 * @return ServiceDetails
	 */
	public ServiceDetails getServiceDetails(VicunaWrapperMultiplexerData aData);
	/**
	 * Must be implemented to perform the receive.
	 * @param param ServiceHelper
	 * @param param2 VicunaWrapperMultiplexerData
	 * @param receiveTimeOut double
	 * @return TransactionState
	 * @throws ServiceTimeoutException
	 * @throws ServiceException
	 */
	public TransactionState receive(
		ServiceHelper param,
		VicunaWrapperMultiplexerData param2,
		double receiveTimeOut)
		throws ServiceTimeoutException, ServiceException;
	/**
	 * Must be implemented to perform the send.
	 * @param param ServiceHelper
	 * @param param2 VicunaWrapperMultiplexerData
	 * @return TransactionState
	 * @throws ServiceException
	 */
	public TransactionState send(
		ServiceHelper param,
		VicunaWrapperMultiplexerData param2)
		throws ServiceException;
}
