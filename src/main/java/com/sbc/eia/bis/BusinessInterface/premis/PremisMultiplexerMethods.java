// $Id: PremisMultiplexerMethods.java,v 1.4 2008/02/29 23:27:20 jd3462 Exp $

package com.sbc.eia.bis.BusinessInterface.premis;

import java.util.Properties;

import com.sbc.bccs.utilities.Utility;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.bis.lim.util.LIMBase;
import com.sbc.gwsvcs.access.vicuna.ServiceHelper;
import com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException;
import com.sbc.gwsvcs.access.vicuna.exceptions.ServiceTimeoutException;
import com.sbc.gwsvcs.access.vicuna.multiplexer.ServiceDetails;
import com.sbc.gwsvcs.access.vicuna.multiplexer.VicunaWrapperMultiplexerData;
import com.sbc.gwsvcs.access.vicuna.multiplexer.VicunaWrapperMultiplexerMethods;
import com.sbc.gwsvcs.access.vicuna.multiplexer.VicunaWrapperMultiplexerMethods.TransactionState;
import com.sbc.gwsvcs.service.premisserver.PREMISServerHelper;
import com.sbc.gwsvcs.service.premisserver.interfaces.PremisValdtAddrReq_t;

/**
 * PremisMultiplexerMethods class.
 * Creation date: (6/28/01 5:33:56 PM)
 * @author: Creighton Malet
 */
public class PremisMultiplexerMethods implements VicunaWrapperMultiplexerMethods, Utility
{
	/**
	 * Evaluate ServiceException to set data element and determine TransactionState.
	 * Creation date: (6/28/01 5:34:34 PM)
	 */
	public TransactionState evaluateException(VicunaWrapperMultiplexerData aData, com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException param)
	{
		PremisMultiplexerData myData = (PremisMultiplexerData)aData;
		log(LogEventId.DEBUG_LEVEL_2, "PremisMultiplexerMethods::evaluateException - transactionId<" + myData.transactionId + ">");
        myData.serviceException = param;	
		return TransactionState.COMPLETE;
	}

	/**
	 * Evaluate Timeout to set data element and determine TransactionState.
	 * Creation date: (6/28/01 5:34:34 PM)
	 */
	public TransactionState evaluateTimeOut(VicunaWrapperMultiplexerData aData, com.sbc.gwsvcs.access.vicuna.exceptions.ServiceTimeoutException param)
	{
		PremisMultiplexerData myData = (PremisMultiplexerData)aData;
		log(LogEventId.DEBUG_LEVEL_2, "PremisMultiplexerMethods::evaluateTimeOut - transactionId<" + myData.transactionId + ">");
        myData.serviceTimeoutException = param;	
		return TransactionState.COMPLETE;
	}

	/**
	 * Evaluate Connection Exception to set data element and determine TransactionState.
	 * Creation date: (6/28/01 5:34:34 PM)
	 */
	public TransactionState evaluateConnectionException(VicunaWrapperMultiplexerData aData, com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException param)
	{
		PremisMultiplexerData myData = (PremisMultiplexerData)aData;
 		log(LogEventId.DEBUG_LEVEL_2, "PremisMultiplexerMethods::evaluateConnectionException - transactionId<" + myData.transactionId + ">");
        myData.serviceException = param;	
		return TransactionState.COMPLETE;
	}

	Properties props = new Properties();
    LIMBase utility = null;
    String premisSvcNm = null;
    
    public PremisMultiplexerMethods(LIMBase aUtility, Properties props)
    {
        setProps(props);
        setUtility(aUtility);
    }
    /**
     * Get method for building Service Helper array.
     * @param numberOfInstances int
     * @return ServiceHelper[]
     */
    public ServiceHelper[] getHelperInstances(int numberOfInstances)
    	throws ServiceException
    {
    	log(LogEventId.DEBUG_LEVEL_2, "PremisMultiplexerMethods::getInstances: numberOfInstances<" + numberOfInstances + ">");
    
    	PREMISServerHelper[] helpers = new PREMISServerHelper[numberOfInstances];
    	for (int i = 0; i < numberOfInstances; i++)
    	{
    		helpers[i] = new PREMISServerHelper(getProps(), this);
    	}
    
    	return helpers;
    }
    /**
     * Getter method for instantiating and returning ServiceDetails object.
     * @param aData VicunaWrapperMultiplexerData
     * @return ServiceDetails
     */
    public ServiceDetails getServiceDetails(VicunaWrapperMultiplexerData aData)
    {
    	PremisMultiplexerData myData = (PremisMultiplexerData)aData;
        log(LogEventId.DEBUG_LEVEL_2, "PremisMultiplexerMethods::getServiceDetails - transactionId<" + myData.transactionId + ">");
        return new ServiceDetails(null, null);
    }
    /**
     * log() Sets up log message string and calls the real log() to do the work.
     * Creation date: (4/9/01 3:41:22 PM)
     * @param aCode String
     * @param aMessage String
     */
    public void log(String aCode, String aMessage)
    {
        getUtility().log(   aCode,
                            aMessage);
    }
    /**
     * log() Sets up log message string and calls the real log() to do the work.
     * Creation date: (4/9/01 3:41:22 PM)
     * @param eventId String
     * @param eventSubType String
     * @param originMethod String
     * @param destApp String
     * @param destMethod String
     */
    public void log(String eventId, 
                    String originMethod,
                    String destApp,
                    String destMethod )
    {
        getUtility().log( eventId,
             originMethod,
             destApp,
             destMethod);
    }
    /**
     * log() Sets up log message string and calls the real log() to do the work.
     * Creation date: (4/9/01 3:41:22 PM)
     * @param eventId String
     * @param eventSubType String
     * @param originMethod String
     * @param destApp String
     * @param destMethod String
     */
    public void log(String eventId, 
                    String eventSubType,
                    String originMethod,
                    String destApp,
                    String destMethod )
    {
        getUtility().log( eventId, 
             originMethod,
             destApp,
             destMethod,
             eventSubType );
    }
    /**
     * Method to receive response from PREMIS service.
     * Timeout parameter determines splice wait time for receive.
     * Throws timeout exception if response not received.
     * @param serviceHelper ServiceHelper
     * @param aData VicunaWrapperMultiplexerData
     * @param receiveTimeOut double
     * @return TransactionState
     * @exception ServiceTimeoutException
     * @exception ServiceException
     */
    public TransactionState receive(ServiceHelper serviceHelper,
    	VicunaWrapperMultiplexerData aData, double receiveTimeOut)
    		throws ServiceTimeoutException, ServiceException
    {
    	PremisMultiplexerData myData = (PremisMultiplexerData)aData;
    	log(LogEventId.DEBUG_LEVEL_2, "PremisMultiplexerMethods::receive - transactionId<" + myData.transactionId + ">");
        
        myData.setDataResp(((PREMISServerHelper)serviceHelper).premisValdtAddrReq(receiveTimeOut));
        
        log(LogEventId.INFO_LEVEL_2, "Received event " + myData.getDataResp().getEventNbr());
        log(LogEventId.REMOTE_RETURN, PREMISTag.NAME, getPremisSvcNm(), PREMISTag.SERVER, 
              PREMISTag.PREMIS_VALDT_ADDR_REQ + "<" + myData.transactionId + ">"); 
       
    	return (TransactionState.COMPLETE);
    }
    /**
     * Method to send request to PREMIS service.
     * @param serviceHelper ServiceHelper
     * @param aData VicunaWrapperMultiplexerData
     * @return TransactionState
     * @exception ServiceException
     */
    public TransactionState send(ServiceHelper serviceHelper, VicunaWrapperMultiplexerData aData)
    	throws ServiceException
    {
    	PremisMultiplexerData myData = (PremisMultiplexerData)aData;
    	log(LogEventId.DEBUG_LEVEL_2, "PremisMultiplexerMethods::send - transactionId<" + myData.transactionId + ">");
        log(LogEventId.REMOTE_CALL, PREMISTag.NAME, getPremisSvcNm(), PREMISTag.SERVER, 
            PREMISTag.PREMIS_VALDT_ADDR_REQ + "<" + myData.transactionId + ">");
    	
    	PremisValdtAddrReq_t request = myData.dataReq.toVicuna();
    	((PREMISServerHelper)serviceHelper).premisValdtAddrReq(request);
    
    	return TransactionState.RECEIVE;
    }
    /**
     * Throw a standard BIS exception from a BusinessInterface-generated exception.
     * Creation date: (3/27/01 3:44:39 PM)
     * @param code String
     * @param text text
     * @param origination String
     * @param severity Severity
     * @exception InvalidData An input parameter contained invalid data.
     * @exception AccessDenied Access to the specified domain object or information is not allowed.
     * @exception BusinessViolation The attempted action violates a business rule.
     * @exception SystemFailure The method could not be completed due to system level errors (e.g., out of memory, loss of network connectivity)n.
     * @exception NotImplemented The method has not been implemented.
     * @exception ObjectNotFound The desired domain object could not be found.
     * @exception DataNotFound No data found.
     */
    public void throwException(String code,
    					String text,
    					String origination,
    					com.sbc.eia.idl.types.Severity severity)
    throws com.sbc.eia.idl.bis_types.InvalidData,
    	   com.sbc.eia.idl.bis_types.AccessDenied,
    	   com.sbc.eia.idl.bis_types.BusinessViolation,
    	   com.sbc.eia.idl.bis_types.SystemFailure,
    	   com.sbc.eia.idl.bis_types.NotImplemented,
    	   com.sbc.eia.idl.bis_types.ObjectNotFound
    {
    }
    
    /**
     * Throw a standard BIS exception from a BusinessInterface-generated exception.
     * Creation date: (3/27/01 3:44:39 PM)
     * @param code String
     * @param text text
     * @param origination String
     * @param severity Severity
     * @param exception Exception
     * @exception InvalidData An input parameter contained invalid data.
     * @exception AccessDenied Access to the specified domain object or information is not allowed.
     * @exception BusinessViolation The attempted action violates a business rule.
     * @exception SystemFailure The method could not be completed due to system level errors (e.g., out of memory, loss of network connectivity)n.
     * @exception NotImplemented The method has not been implemented.
     * @exception ObjectNotFound The desired domain object could not be found.
     * @exception DataNotFound No data found.
     */
    public void throwException(String code,
    					String text,
    					String origination,
    					com.sbc.eia.idl.types.Severity severity,
    					Exception exception)
    throws com.sbc.eia.idl.bis_types.InvalidData,
    	   com.sbc.eia.idl.bis_types.AccessDenied,
    	   com.sbc.eia.idl.bis_types.BusinessViolation,
    	   com.sbc.eia.idl.bis_types.SystemFailure,
    	   com.sbc.eia.idl.bis_types.NotImplemented,
    	   com.sbc.eia.idl.bis_types.ObjectNotFound 
    {
    }
    
    /**
     * Getter method for utility.
     * @return LIMBase
     */
    public LIMBase getUtility() {
    	return utility;
    }
    
    /**
     * Setter method for utility.
     * @param utility The utility to set
     */
    public void setUtility(LIMBase utility) {
    	this.utility = utility;
    }
    
    /**
     * Getter method for props.
     * @return Properties
     */
    public Properties getProps() {
    	return props;
    }
    
    /**
     * Setter method for props.
     * @param props The props to set
     */
    public void setProps(Properties props) {
    	this.props = props;
    }
    
    /**
     * Getter method for premisSvcNm.
     * @return String
     */
    public String getPremisSvcNm() {
    	return premisSvcNm;
    }
    
    /**
     * Setter method for premisSvcNm.
     * @param premisSvcNm The premisSvcNm to set
     */
    public void setPremisSvcNm(String premisSvcNm) {
    	this.premisSvcNm = premisSvcNm;
    }

}
