// $Id: VicunaWrapperMultiplexer.java,v 1.3 2003/02/14 17:23:54 as5472 Exp $

package com.sbc.gwsvcs.access.vicuna.multiplexer;

import java.util.ArrayList;

import com.sbc.bccs.utilities.Utility;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.gwsvcs.access.vicuna.ServiceHelper;
import com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException;

/**
 * The class is the controls the multiplexing. It creates the initial
 * set of service helpers and workers that do the actual work of
 * sending and receiving asychronous messages using Vicuna.  The 
 * multiplexer then asks each worker (in order) to do its current task.
 * The multiplexer will continue to loop through each worker until all
 * workers have completed their tasks.  The max time each worker has to 
 * perform a task and the maximum times they can try to perform that 
 * task is determined by the multiplexer.  It calculates this from the 
 * given gross time out value in seconds and the slice time out value in 
 * whole seconds or a fraction of a second.
 * 
 * Creation date: (6/28/01 3:27:48 PM)
 * @author: Creighton Malet
 */
public class VicunaWrapperMultiplexer {
	/* mainly used for logging */
	private Utility utility = null;
	/* determines the maximum number of helpers (and thus connections) that you can have at one time */
	private int maximumHelpers = 1;
	/* the total (maximum) seconds the multiplexer is allowed to perform its work */
	private int grossTimeOutSeconds = 30;
	/* the whole or faction of a second that each worker gets to perform a receive */
	private double sliceTimeOutSeconds = 1.0;
	/* the service helpers */
	private ArrayList serviceHelpers = null;
	/**
	 * The default constructor.
	 */
	private VicunaWrapperMultiplexer() {
	}
	/**
	 * The constructor used to facilitate logging.
	 */
	public VicunaWrapperMultiplexer(Utility aUtility)
		throws VicunaWrapperMultiplexerException {
		setUtility(aUtility);
	}
	/**
	 * The constructor used to override the multiplexer's default values.
	 */
	public VicunaWrapperMultiplexer(
		Utility aUtility,
		int aMaximumHelpers,
		int aGrossTimeOutSeconds,
		double aSliceTimeOutSeconds)
		throws VicunaWrapperMultiplexerException {
		setUtility(aUtility);
		setMaximumHelpers(aMaximumHelpers);
		setGrossTimeOutSeconds(aGrossTimeOutSeconds);
		setSliceTimeOutSeconds(aSliceTimeOutSeconds);
	}
	/**
	 * Thie method creates a new list of service helpers.  The number
	 * of helpers in the lsit cannot exceed the defined maximum for
	 * this multiplexer.
	 * Creation date: (6/28/01 3:35:11 PM)
	 * @param z int - the requested number of service helpers to allocate
	 * @return ArrayList
	 */
	private ArrayList allocateHelpers(int z) {
		int numberToAllocate = z < maximumHelpers ? z : maximumHelpers;
		ArrayList allocatedHelpers = new ArrayList();

		for (int i = 0; i < numberToAllocate; i++) {
			allocatedHelpers.add(serviceHelpers.get(i));
		}

		return allocatedHelpers;
	}
	/**
	 * This method starts the multiplexing routine.  This is where all of the
	 * control happens.
	 * @param aMethodsImplementor VicunaWrapperMultiplexerMethods
	 * @param inputArray VicunaWrapperMultiplexerData[]
	 * @throws VicunaWrapperMultiplexerException
	 */
	public void run(
		VicunaWrapperMultiplexerMethods aMethodsImplementor,
		VicunaWrapperMultiplexerData[] inputArray)
		throws VicunaWrapperMultiplexerException {
		utility.log(
			LogEventId.DEBUG_LEVEL_2,
			"VicunaWrapperMultiplexer::run()");

		//TODO - specific exceptions? codes?
		// Check the passed parameters
		if (aMethodsImplementor == null)
			throw new VicunaWrapperMultiplexerException("VicunaWrapperMultiplexer::run(): passed attribute is null: VicunaWrapperMultiplexerMethods");
		if (inputArray == null || inputArray.length < 1)
			throw new VicunaWrapperMultiplexerException("VicunaWrapperMultiplexer::run(): passed attribute is null or empty: VicunaWrapperMultiplexerData[]");

		setupHelpers(aMethodsImplementor, inputArray.length);

		ArrayList allocatedHelpers = null;
		try {
			allocatedHelpers = allocateHelpers(inputArray.length);

			// Figure out if multiplexing is needed and what the time outs are
			double receiveTimeOut = 0;
			int maximumTimeOuts = 0;
			if (allocatedHelpers.size() < 2) {
				receiveTimeOut = grossTimeOutSeconds;
				maximumTimeOuts = 1;
			} else {
				receiveTimeOut = sliceTimeOutSeconds;
				maximumTimeOuts =
					(int) (grossTimeOutSeconds / sliceTimeOutSeconds);
			}
			utility.log(
				LogEventId.DEBUG_LEVEL_2,
				"inputArray.length<"
					+ inputArray.length
					+ "> allocatedHelpers.size()<"
					+ allocatedHelpers.size()
					+ "> grossTimeOutSeconds<"
					+ grossTimeOutSeconds
					+ "> sliceTimeOutSeconds<"
					+ sliceTimeOutSeconds
					+ "> receiveTimeOut<"
					+ receiveTimeOut
					+ "> maximumTimeOuts<"
					+ maximumTimeOuts
					+ ">");

			VicunaWrapperMultiplexerWorker[] workers =
				new VicunaWrapperMultiplexerWorker[allocatedHelpers.size()];
			for (int i = 0; i < allocatedHelpers.size(); i++) {
				workers[i] =
					new VicunaWrapperMultiplexerWorker(
						aMethodsImplementor,
						utility,
						(ServiceHelper) allocatedHelpers.get(i),
						receiveTimeOut,
						maximumTimeOuts);
				workers[i].setInputData(inputArray[i]);
			}

			int numberOfTransactionsComplete = 0;
			int nextTransactionToProcess = allocatedHelpers.size();
			boolean workerNeedsNewTransaction = true;
			int activeWorker = 0;
			while (numberOfTransactionsComplete < inputArray.length) {
				for (activeWorker = 0;
					activeWorker < workers.length;
					activeWorker++) {
					utility.log(
						LogEventId.DEBUG_LEVEL_2,
						"NumberOfTransactionsComplete<"
							+ numberOfTransactionsComplete
							+ ">");
					utility.log(
						LogEventId.DEBUG_LEVEL_2,
						"Worker["
							+ activeWorker
							+ "] TransactionState<"
							+ workers[activeWorker].getTransactionStateAsString()
							+ ">");
					if (workers[activeWorker].isNotComplete())
						workerNeedsNewTransaction =
							workers[activeWorker].cycle();
					//TODO catch BIS eceptions or handled lower down in call backs?

					if (workerNeedsNewTransaction) {
						numberOfTransactionsComplete++;
						if (nextTransactionToProcess < inputArray.length) {
							utility.log(
								LogEventId.DEBUG_LEVEL_2,
								"Worker["
									+ activeWorker
									+ "] TransactionState<"
									+ workers[activeWorker]
										.getTransactionStateAsString()
									+ "> NextTransactionToProcess<"
									+ nextTransactionToProcess
									+ ">");
							workers[activeWorker].setInputData(
								inputArray[nextTransactionToProcess++]);
						} else {
							utility.log(
								LogEventId.DEBUG_LEVEL_2,
								"Worker["
									+ activeWorker
									+ "] TransactionState<"
									+ workers[activeWorker]
										.getTransactionStateAsString()
									+ "> setComplete()");
							workers[activeWorker].setComplete();
							workerNeedsNewTransaction = false;
						}
					} else {
						utility.log(
							LogEventId.DEBUG_LEVEL_2,
							"Worker["
								+ activeWorker
								+ "] TransactionState<"
								+ workers[activeWorker]
									.getTransactionStateAsString()
								+ ">");
					}
				}
			}
		} finally // need this regardless in case of 'other' exceptions.. eg NullPointer
			{
			utility.log(
				LogEventId.DEBUG_LEVEL_2,
				"VicunaWrapperMultiplexer::run - finally disconnect all helpers.");
			disconnectHelpers(allocatedHelpers);
		}
	}
	/**
	 * Sets the maximum seconds this multiplexer is allowed to perform all its work.
	 * @param newGrossTimeOutSeconds int - cannot be less than 1
	 * @throws VicunaWrapperMultiplexerException - exception if gross time out passed is less than 1
	 */
	public void setGrossTimeOutSeconds(int newGrossTimeOutSeconds)
		throws VicunaWrapperMultiplexerException {
		if (newGrossTimeOutSeconds < 1)
			throw new VicunaWrapperMultiplexerException(
				"VicunaWrapperMultiplexer::setGrossTimeOutSeconds(): passed attribute is invalid: newGrossTimeOutSeconds<"
					+ newGrossTimeOutSeconds
					+ ">");

		grossTimeOutSeconds = newGrossTimeOutSeconds;
	}
	/**
	 * Sets the maximum number of helpers that can exist.  If
	 * the number of helpers currently used exceeds this maximum,
	 * the extra helpers are removed from the list until only
	 * the maximum number of helpers is left.
	 * @param newMaximumHelpers int - the number of helpers that can exist
	 * @throws VicunaWrapperMultiplexerException - thrown if maximum is less than 1
	 */
	public void setMaximumHelpers(int newMaximumHelpers)
		throws VicunaWrapperMultiplexerException {
		if (newMaximumHelpers < 1)
			throw new VicunaWrapperMultiplexerException(
				"VicunaWrapperMultiplexer::setMaximumHelpers(): passed attribute is invalid: newMaximumHelpers<"
					+ newMaximumHelpers
					+ ">");

		maximumHelpers = newMaximumHelpers;
		if (serviceHelpers != null && serviceHelpers.size() > maximumHelpers) {
			for (int i = maximumHelpers; i < serviceHelpers.size(); i++)
				serviceHelpers.remove(i--);
		}

	}
	/**
	 * This method sets the whole or fraction of a second that each worker
	 * can have to perform a task.
	 * @param newSliceTimeOutSeconds double
	 * @throws VicunaWrapperMultiplexerException
	 */
	public void setSliceTimeOutSeconds(double newSliceTimeOutSeconds)
		throws VicunaWrapperMultiplexerException {
		if (newSliceTimeOutSeconds <= 0)
			throw new VicunaWrapperMultiplexerException(
				"VicunaWrapperMultiplexer::setSliceTimeOutSeconds(): passed attribute is invalid: newSliceTimeOutSeconds<"
					+ newSliceTimeOutSeconds
					+ ">");

		sliceTimeOutSeconds = newSliceTimeOutSeconds;
	}
	/**
	 * This method builds the set of service helpers.  
	 * @param aMethodsImplementor VicunaWrapperMultiplexerMethods
	 * @param inputArrayLength int
	 * @throws VicunaWrapperMultiplexerException
	 */
	private void setupHelpers(
		VicunaWrapperMultiplexerMethods aMethodsImplementor,
		int inputArrayLength)
		throws VicunaWrapperMultiplexerException {
		utility.log(
			LogEventId.DEBUG_LEVEL_2,
			"maximumHelpers<" + maximumHelpers + ">");

		// First time
		if (serviceHelpers == null)
			serviceHelpers = new ArrayList();

		// Calculate how many new helpers are required
		int newHelpersRequired = 0;
		if (serviceHelpers.size() < maximumHelpers
			&& serviceHelpers.size() < inputArrayLength)
			newHelpersRequired =
				inputArrayLength < maximumHelpers
					? inputArrayLength - serviceHelpers.size()
					: maximumHelpers - serviceHelpers.size();
		utility.log(
			LogEventId.DEBUG_LEVEL_2,
			"newHelpersRequired<" + newHelpersRequired + ">");

		// Build the new helpers
		if (newHelpersRequired > 0) {
			ServiceException savedE = null;
			ServiceHelper[] tmpServiceHelpers = null;
			try {
				tmpServiceHelpers =
					aMethodsImplementor.getHelperInstances(newHelpersRequired);
			} catch (ServiceException e) {
				savedE = e;
				utility.log(
					LogEventId.ERROR,
					"Failed to initialize all service helpers: "
						+ e.getExceptionCode()
						+ ": "
						+ e.getMessage());
			}

			// Check there is at least one helper
			if (tmpServiceHelpers == null || tmpServiceHelpers.length < 1) {
				//TODO common mechanism to add statcktrace to message - savedE else remobe savedE
				throw new VicunaWrapperMultiplexerException("VicunaWrapperMultiplexer::setupHelpers(): failed to create at least one ServiceHelper");

			}
			for (int i = 0; i < tmpServiceHelpers.length; i++)
				serviceHelpers.add(tmpServiceHelpers[i]);
		}
	}
	/**
	 * Set the logging utility.
	 * @param newUtility Utility - must not be null
	 * @throws VicunaWrapperMultiplexerException - thrown if utilty parameter is null
	 */
	private void setUtility(Utility newUtility)
		throws VicunaWrapperMultiplexerException {
		if (newUtility == null)
			throw new VicunaWrapperMultiplexerException("VicunaWrapperMultiplexer::setUtility(): passed attribute is null: Utility");

		utility = newUtility;
	}

	/**
	 * This method asks all helpers to shutdown its connection.
	 * @param connectedHelpers ArrayList
	 */
	private void disconnectHelpers(ArrayList connectedHelpers) {

		for (int i = 0; i < connectedHelpers.size(); i++) {
			try {
				utility.log(
					LogEventId.DEBUG_LEVEL_2,
					"VicunaWrapperMultiplexer::disconnectHelpers - Disconnecting connection for service helper <"
						+ i
						+ ">");
				((ServiceHelper) serviceHelpers.get(i)).disconnect();
			} catch (ServiceException e) {
				utility.log(
					LogEventId.DEBUG_LEVEL_2,
					"VicunaWrapperMultiplexer::disconnectHelpers - Unable to disconnect connection for service helper <"
						+ i
						+ ">");
			}
		}

		return;
	}
}
