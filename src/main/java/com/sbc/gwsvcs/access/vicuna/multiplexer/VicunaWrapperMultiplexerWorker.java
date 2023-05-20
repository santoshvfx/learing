// $Id: VicunaWrapperMultiplexerWorker.java,v 1.3 2003/02/14 17:23:54 as5472 Exp $

package com.sbc.gwsvcs.access.vicuna.multiplexer;

import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.gwsvcs.access.vicuna.ServiceHelper;
import com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException;
import com.sbc.gwsvcs.access.vicuna.exceptions.ServiceTimeoutException;
import com.sbc.gwsvcs.access.vicuna.multiplexer.VicunaWrapperMultiplexerMethods.TransactionState;

/**
 * Manages a single transaction for the multiplexer.
 * Creation date: (6/28/01 3:27:48 PM)
 * @author: Creighton Malet
 */
public class VicunaWrapperMultiplexerWorker {
	private VicunaWrapperMultiplexerMethods multiPlexerMethods = null;
	private com.sbc.bccs.utilities.Utility utility = null;
	private ServiceHelper serviceHelper = null;
	private double receiveTimeOut = 0;
	private int maximumTimeOuts = 0;

	private int transactionState = TransactionState._NEW;
	private VicunaWrapperMultiplexerData inputData = null;
	private ServiceDetails serviceDetails = null;
	private int numberOfTimeOuts = 0;
	/**
	 * Class constructor.
	 */
	private VicunaWrapperMultiplexerWorker() {
	}
	/**
	 * Class constructor accepting initializers.
	 */
	public VicunaWrapperMultiplexerWorker(
		VicunaWrapperMultiplexerMethods aMultiPlexerMethods,
		com.sbc.bccs.utilities.Utility aUtility,
		ServiceHelper aServiceHelper,
		double aReceiveTimeOut,
		int aMaximumTimeOuts) {
		multiPlexerMethods = aMultiPlexerMethods;
		utility = aUtility;
		serviceHelper = aServiceHelper;
		receiveTimeOut = aReceiveTimeOut;
		maximumTimeOuts = aMaximumTimeOuts;
	}
	/**
	 * Runs a cycle of the transaction
	 * @return boolean
	 * Creation date: (6/28/01 3:35:11 PM)
	 */
	public boolean cycle() {
		utility.log(
			LogEventId.DEBUG_LEVEL_2,
			"VicunaWrapperMultiplexerWorker::cycle()");

		// If the state is new, switch to send. Start of transaction initialization could go here
		if (transactionState == TransactionState._NEW) {
			transactionState = TransactionState._SEND;
		}

		// Invoke sends and receives as controlled by the client multiplexer methods
		try {
			switch (transactionState) {
				case (TransactionState._SEND) :
					{
						numberOfTimeOuts = 0;
						try {
							if (!evaluateConnection())
								return true;
						} catch (ServiceException s) {
							switch (transactionState =
								multiPlexerMethods
									.evaluateConnectionException(inputData, s)
									.state()) {
								case TransactionState._SEND :
									return false;
								case TransactionState._RECEIVE :
									utility.log(
										LogEventId.ERROR,
										"Invalid value from VicunaWrapperMultiplexerMethods::evaluateConnectionFailure(): <"
											+ transactionState
											+ ">.  Cannot Continue.");
									return true;
								case TransactionState._COMPLETE :
									return true;
								default :
									utility.log(
										LogEventId.ERROR,
										"Invalid value from VicunaWrapperMultiplexerMethods::evaluateConnectionFailure(): <"
											+ transactionState
											+ ">.  Cannot Continue.");
									return true;
							}
						}

						switch (transactionState =
							multiPlexerMethods
								.send(serviceHelper, inputData)
								.state()) {
							case TransactionState._SEND :
								return false;
							case TransactionState._RECEIVE :
								return false;
							case TransactionState._COMPLETE :
								return true;
							default :
								utility.log(
									LogEventId.ERROR,
									"Invalid value from VicunaWrapperMultiplexerMethods::send(): <"
										+ transactionState
										+ ">.  Cannot continue");
								return true;
						}
					}

				case (TransactionState._RECEIVE) :
					{
						switch (transactionState =
							multiPlexerMethods
								.receive(
									serviceHelper,
									inputData,
									receiveTimeOut)
								.state()) {
							case TransactionState._SEND :
								return false;
							case TransactionState._RECEIVE :
								return false;
							case TransactionState._COMPLETE :
								return true;
							default :
								utility.log(
									LogEventId.ERROR,
									"Invalid value from VicunaWrapperMultiplexerMethods::receive(): <"
										+ transactionState
										+ ">,  Cannot continue.");
								return true;
						}
					}
			}
		} catch (ServiceTimeoutException e) {
			numberOfTimeOuts++;
			utility.log(
				LogEventId.DEBUG_LEVEL_2,
				"numberOfTimeOuts<" + numberOfTimeOuts + ">");
			if (numberOfTimeOuts >= maximumTimeOuts) {
				switch (transactionState =
					multiPlexerMethods.evaluateTimeOut(inputData, e).state()) {
					case TransactionState._SEND :
						return false;
					case TransactionState._RECEIVE :
						utility.log(
							LogEventId.DEBUG_LEVEL_2,
							"Invalid value from VicunaWrapperMultiplexerMethods::evaluateTimeOut(): <"
								+ transactionState
								+ ">.  Maximum timeouts reached, cannot continue.");
						return true;
					case TransactionState._COMPLETE :
						return true;
					default :
						utility.log(
							LogEventId.ERROR,
							"Invalid value from VicunaWrapperMultiplexerMethods::evaluateTimeOut(): <"
								+ transactionState
								+ ">.  Cannot continue.");
						return true;
				}
			}
			transactionState = TransactionState._RECEIVE;
			return false;
		} catch (ServiceException e) {
			switch (transactionState =
				multiPlexerMethods.evaluateException(inputData, e).state()) {
				case TransactionState._SEND :
					return false;
				case TransactionState._RECEIVE :
					return false;
				case TransactionState._COMPLETE :
					return true;
				default :
					utility.log(
						LogEventId.ERROR,
						"Invalid value from VicunaWrapperMultiplexerMethods::evaluateException(): <"
							+ transactionState
							+ ">.  Cannot continue.");
					return true;
			}
		}

		return true;
	}
	/**
	 * Shuts down the connect for this worker's current service helper.
	 * Creation date: (6/28/01 4:49:52 PM)
	 * @param newData com.sbc.engine.MultiplexEngineData
	 */
	public void disconnectHelper() {
		try {
			serviceHelper.disconnect();
		} catch (ServiceException e) {
			utility.log(
				LogEventId.DEBUG_LEVEL_2,
				"VicunaWrapperMultiplexer::disconnectHelper - Unable to shutdown connection for this helper.");
		}
	}
	/**
	 * This method makes attemps to make a service connection.
	 * @return boolean
	 * @throws ServiceException
	 */
	private boolean evaluateConnection() throws ServiceException {
		utility.log(
			LogEventId.DEBUG_LEVEL_2,
			"VicunaWrapperMultiplexerWorker::evaluateConnection()");

		ServiceDetails newServiceDetails =
			multiPlexerMethods.getServiceDetails(inputData);
		if (newServiceDetails == null) {
			utility.log(
				LogEventId.ERROR,
				"Null value from VicunaWrapperMultiplexerMethods::getServiceDetails()");
			return false;
		}
		if (serviceDetails == null
			|| !serviceDetails.equals(newServiceDetails)) {
			if (serviceDetails != null)
				disconnectHelper();
			serviceDetails = newServiceDetails;
			try {
				serviceHelper.connect(
					serviceDetails.getApplData(),
					serviceDetails.getServiceName());
			} catch (ServiceException e) {
				serviceDetails = null;
				throw e;
			}
		}
		return true;
	}
	/**
	 * Returns the transaction state value.
	 * Creation date: (7/2/01 4:58:09 PM)
	 * @return int
	 */
	public int getTransactionState() {
		return transactionState;
	}
	/**
	 * Translates transaction state to string.
	 * Creation date: (2/5/03 6:58:09 PM)
	 * @return String
	 */
	public String getTransactionStateAsString() {
		return getTransactionStateAsString(getTransactionState());
	}
	/**
	 * Translates transaction state to string.
	 * Creation date: (2/5/03 6:58:09 PM)
	 * @return String
	 */
	public static String getTransactionStateAsString(int aState) {
		try {
			return VicunaWrapperMultiplexerMethods
				.TransactionState
				.stateAsString[aState];
		} catch (ArrayIndexOutOfBoundsException e) {
			return null;
		}
	}
	/**
	 * Method isNotComplete.
	 * @return boolean
	 */
	public boolean isNotComplete() {
		return transactionState == TransactionState._COMPLETE ? false : true;
	}
	/**
	 * This method sets the transaction state to complete and subsequently shutdown the service helper's connection.
	 */
	public void setComplete() {
		transactionState = TransactionState._COMPLETE;
		disconnectHelper();
	}
	/**
	 * This method sets the data for the transaction and initializes the transaction state to new.
	 * @param newData VicunaWrapperMultiplexerData
	 */
	public void setInputData(VicunaWrapperMultiplexerData newData) {
		inputData = newData;
		transactionState = TransactionState._NEW;
	}
}
