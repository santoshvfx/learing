//$Id: SendRequestToFACSRC.java,v 1.11.14.1 2011/04/05 23:25:53 jr5306 Exp $

//############################################################################
//#
//# Copyright Notice:
//#
//#     RESTRICTED - PROPRIETARY INFORMATION
//#      The information herein is for use only by authorized employees
//#      of SBC Services Inc. and authorized Affiliates of SBC Services,
//#      Inc., and is not for general distribution within or outside the
//#      respective companies.
//#      Copying or reproduction without prior written approval is prohibited.
//#
//#      © 2002-2007 SBC Knowledge Ventures, L.P. All rights reserved.
//#
//# History :
//# Date      | Author               | Notes
//# --------------------------------------------------------------------------------------------------------------
//# 01/08/2007  | Manjula Goniguntla | create
//# 04/06/2007	| Prasad Ganji		 | Changed the Response object from INQFASGImpl to ResponseMessageImpl
//# 04/09/2007	| Prasad Ganji		 | Added code to catch FACSRCAccess and FACSAdapter error messages
//# 04/11/2007  | Prasad Ganji		 | Moved the processFACSErrors outside the try-catch block
//# 04/12/2007  | Prasad Ganji		 | Moved the logReturn statement inside the try-catch block
//# 02/04/2008  | Shyamali Banerjee  | LS 7
//# 01/14/2009  | Julius Sembrano    | LS 10

package com.sbc.eia.bis.embus.service.facsrc;

import java.util.Hashtable;
import java.util.List;
import java.util.Properties;

import com.sbc.bccs.utilities.Utility;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilder;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilderRule;
import com.sbc.eia.bis.embus.service.access.EmbusServiceException;
import com.sbc.eia.bis.embus.service.access.EncoderException;
import com.sbc.eia.bis.embus.service.access.ResourceConnectorServiceException;
import com.sbc.eia.bis.embus.service.access.ServiceTimeOutException;
import com.sbc.eia.bis.embus.service.facsrc.InqOspRequest.impl.NINQImpl;
import com.sbc.eia.bis.embus.service.facsrc.InqTermRequest.impl.INQTERMImpl;
import com.sbc.eia.bis.embus.service.facsrc.InqfasgRequest.impl.INQFASGImpl;
import com.sbc.eia.bis.embus.service.facsrc.InqfasgResponse.impl.ResponseMessageImpl;
import com.sbc.eia.bis.embus.service.facsrc.InqfasgResponse.impl.ErrorsAggTypeImpl.ErrorCodeImpl;
import com.sbc.eia.bis.embus.service.facsrc.InqfasgResponse.impl.ErrorsAggTypeImpl.ErrorDescriptionImpl;
import com.sbc.eia.bis.embus.service.utilities.ExceptionHelper;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.bis.lim.util.LIMTag;
import com.sbc.eia.idl.bis_types.AccessDenied;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.bis_types.BusinessViolation;
import com.sbc.eia.idl.bis_types.DataNotFound;
import com.sbc.eia.idl.bis_types.InvalidData;
import com.sbc.eia.idl.bis_types.NotImplemented;
import com.sbc.eia.idl.bis_types.ObjectNotFound;
import com.sbc.eia.idl.bis_types.SystemFailure;

/**
* @author mg5629
*
* To change this generated comment edit the template variable "typecomment":
* Window>Preferences>Java>Templates.
* To enable and disable the creation of type comments go to
* Window>Preferences>Java>Code Generation.
*/
public class SendRequestToFACSRC {

	public static final String FACSRC_EXCEPTION_RULE_FILE_TAG =
		"EXCEPTION_BUILDER_FACSRC_RULE_FILE";
	private static final String INQFASG_EVENT = "INQFASG";
	private static final String INQOSP_EVENT = "INQOSP";
	private static final String INQTERM_EVENT = "INQTERM";
	//private static com.sbc.bccs.utilities.Utility utility = null;
	
	/**
	 * @param request
	 * @param aUtility
	 * @param aProp
	 * @param aContext
	 * @param service
	 * @param destination
	 * @param wireCenter
	 * @param eventName
	 * @return
	 * @throws InvalidData
	 * @throws AccessDenied
	 * @throws BusinessViolation
	 * @throws SystemFailure
	 * @throws NotImplemented
	 * @throws ObjectNotFound
	 * @throws DataNotFound
	 */
	public static ResponseMessageImpl sendRequest(
			INQFASGImpl request,
			Utility aUtility,
			Hashtable aProp,
			BisContext aContext,
			FACSRCService service,
			String destination,
			String wireCenter)
		/*throws
			InvalidData,
			AccessDenied,
			BusinessViolation,
			SystemFailure,
			NotImplemented,
			ObjectNotFound,
			DataNotFound*/ {

		logCall(FACSRCHelper.INQFASG_REQUEST, aUtility);

		ResponseMessageImpl response = null;

		try {

			response =
				(ResponseMessageImpl) service.facsrcRequest(
					(INQFASGImpl) request,
					createJMSProperties(
					    aContext,
						aProp,
						destination,
						wireCenter,
						INQFASG_EVENT),
					new Properties());

			logReturn(FACSRCHelper.INQFASG_REQUEST, aUtility);		
						
		} catch (Exception e) 
			{
			logReturn(FACSRCHelper.INQFASG_REQUEST, aUtility);

			ParseFACSRCException(
				request.getClass().getPackage().getName(),
				aUtility,
				aProp,
				aContext,
				e);
		}
		
		processFACSErrors(response, aUtility, aProp, aContext);
			
		return response;
		
	}
	/**
	 * @param aNINQRequest
	 * @param aUtility
	 * @param aProp
	 * @param aContext
	 * @param service
	 * @param destination
	 * @param wireCenter
	 * @param eventName
	 * @return
	 * @throws InvalidData
	 * @throws AccessDenied
	 * @throws BusinessViolation
	 * @throws SystemFailure
	 * @throws NotImplemented
	 * @throws ObjectNotFound
	 * @throws DataNotFound
	 */
	public static com.sbc.eia.bis.embus.service.facsrc.InqOspResponse.impl.ResponseMessageImpl sendOSPRequest(
			NINQImpl request,
			Utility aUtility,
			Hashtable aProp,
			BisContext aContext,
			FACSRCService service,
			String destination,
			String wireCenter)
		throws
			InvalidData,
			AccessDenied,
			BusinessViolation,
			SystemFailure,
			NotImplemented,
			ObjectNotFound,
			DataNotFound {

		logCall(FACSRCHelper.INQOSP_REQUEST, aUtility);

		com.sbc.eia.bis.embus.service.facsrc.InqOspResponse.impl.ResponseMessageImpl response = null;

		try {

			response =
				(com.sbc.eia.bis.embus.service.facsrc.InqOspResponse.impl.ResponseMessageImpl) service.ninqFacsrcRequest(
					(NINQImpl) request,
					createJMSProperties(
						aContext,
						aProp,
						destination,
						wireCenter,
						INQOSP_EVENT),
					new Properties());

			logReturn(FACSRCHelper.INQOSP_REQUEST, aUtility);		
						
		} catch (Exception e) 
			{
			logReturn(FACSRCHelper.INQOSP_REQUEST, aUtility);

			ParseFACSRCException(
					request.getClass().getPackage().getName(),
				aUtility,
				aProp,
				aContext,
				e);
		}

		processINQFACSErrors(response, aUtility, aProp, aContext);
			
		return response;

	}

	/**
	 * Method ParseFACSRCException.
	 * @param objectClass
	 * @param aUtility
	 * @param aProp
	 * @param aContext
	 * @param e
	 * @throws InvalidData
	 * @throws AccessDenied
	 * @throws BusinessViolation
	 * @throws SystemFailure
	 * @throws NotImplemented
	 * @throws ObjectNotFound
	 * @throws DataNotFound
	 */
	private static void ParseFACSRCException(
		String objectClass,
		Utility aUtility,
		Hashtable aProp,
		BisContext aContext,
		Exception e)
		/*throws
			InvalidData,
			AccessDenied,
			BusinessViolation,
			SystemFailure,
			NotImplemented,
			ObjectNotFound,
			DataNotFound*/ {

		String errorCode = "";
		String errorMsg = "";
		String errorText = e.getClass().getName();
		String timeOut = "";
		String originalException = "";

		if (e instanceof ResourceConnectorServiceException) 
		{
			//FACSRC exception
			/*ResourceConnectorServiceException aRCException =
				(ResourceConnectorServiceException) e;
			errorMsg = aRCException.getErrorDescription();
			errorCode = aRCException.getErrorCode();*/
			errorMsg = e.getMessage();
		} 
		else if (e instanceof EmbusServiceException) 
		{
			//FACSRC exception
			/*EmbusServiceException aEmbusException = (EmbusServiceException) e;
			errorMsg = aEmbusException.getErrorDescription();
			errorCode = aEmbusException.getErrorCode();*/
			errorMsg = e.getMessage();
		} 
		else if (e instanceof EncoderException) 
		{
			//rmim exception
			/*ExceptionHelper.throwException(
				(EncoderException) e,
				objectClass,
				aUtility,
				ruleFile,
				aContext);*/
			errorMsg = e.getMessage();
		} 
		else if (e instanceof IllegalArgumentException) 
		{
			//rmim exception
			/*ExceptionHelper.throwException(
				(IllegalArgumentException) e,
				objectClass,
				aUtility,
				ruleFile,
				aContext);*/
			errorMsg = e.getMessage();
		} 
		else if (e instanceof ServiceTimeOutException) 
		{
			errorCode = LIMTag.CSV_LimTimedOutCode;      // Define as Timed out exception code in csv file.
            timeOut = "FACSRC time out. ";
            errorMsg = e.getMessage();
		} 
		else if (e.getMessage().indexOf("Service not available") > -1 && 
                e.getMessage().indexOf("downtime") > -1)
            {
                errorCode = LIMTag.CSV_LimTimedOutCode;      // Define as Timed out exception code in csv file.
                timeOut = "FACSRC Off-Line. ";
            }
		else 
		{
			//FACSRC exception
			errorMsg = e.getMessage();
			//defaultRuleInd = 1;
		}
		
		errorText = timeOut + errorText.substring(errorText.lastIndexOf(".")+1) +
        ": Code[" + errorCode + "], OriginalCode[" +
        originalException + "], Message[" + errorMsg + "]";
		
		aUtility.log(LogEventId.INFO_LEVEL_2, errorText);
		/*ExceptionBuilder.parseException(
			aContext,
			ruleFile,
			null,
			errorCode,
			errorText,
			true,
			defaultRuleInd,
			null,
			e,
			aUtility,
			null,
			null,
			null);*/
	}
	
	
	/**
	 * @param aFACSResponse
	 * @return
	 */	
	private static void processFACSErrors(
		ResponseMessageImpl aFACSResponse,
		Utility aUtility,
		Hashtable aProp,
		BisContext aContext) 
		/*throws
			InvalidData,
			AccessDenied,
			BusinessViolation,
			SystemFailure,
			NotImplemented,
			ObjectNotFound,
			DataNotFound*/ {
			
		String aFACSErrCodeAndDescrp = null;
		String aFACSAdapterErrMsg = null;
		String aLFACSRuleFile = null;

		try {
			List facsErrs = aFACSResponse.getStatusInfo().getErrors().getErrorCodeAndErrorDescription();				

			if(facsErrs.size() > 0) {
				ErrorCodeImpl errCode = (ErrorCodeImpl) facsErrs.get(0);
				ErrorDescriptionImpl errDesc = (ErrorDescriptionImpl) facsErrs.get(1);
				aFACSErrCodeAndDescrp = "Error Code = "+errCode.getValue()+"  Error Description = "+errDesc.getValue();
			}
		} catch(Exception any) { }


		try {	
			aFACSAdapterErrMsg = aFACSResponse.getResults().getFACSADAPTERERROR().getMSG();
		} catch (Exception any) { }

			
		if(aFACSErrCodeAndDescrp != null) {

			//ResponseMessage.StatusInfoType.ErrorsAggType.errorCode and ResponseMessage.StatusInfoType.ErrorsAggType.errorDescription
			//FACS Error Code and Error Description will be populated if there is any error
			//communicating from FACSRCAccess TO FACSAdapter Error message like Communication with FACSAdapter failed, a CORBA System Exception

				aLFACSRuleFile = (String) aProp.get(FACSRC_EXCEPTION_RULE_FILE_TAG);

				aUtility.log(LogEventId.DEBUG_LEVEL_1,"FACSRCAccess TO FACSAdapter Error message : <" + aFACSErrCodeAndDescrp);

				/*ExceptionBuilder.parseException(
									aContext,
									aLFACSRuleFile,
									null,
									null,
									aFACSErrCodeAndDescrp,
									true,
									1,
									null,
									null,
									aUtility,
									null,
									null,
									null);*/
			}
		else if(aFACSAdapterErrMsg != null) {

			//MSG in FACSAdapterType in Results will be populated if there is any error
			//Error Message like COMMUNICATION WITH LEGACY FAILED

				aLFACSRuleFile = (String) aProp.get(FACSRC_EXCEPTION_RULE_FILE_TAG);

				aUtility.log(LogEventId.DEBUG_LEVEL_1,"FACS Adapter Error message : <" + aFACSAdapterErrMsg);

				/*ExceptionBuilder.parseException(
									aContext,
									aLFACSRuleFile,
									null,
									null,
									aFACSAdapterErrMsg,
									true,
									1,
									null,
									null,
									aUtility,
									null,
									null,
									null);*/
			}
			
	}	

	/**
	 * @param aFACSResponse
	 * @return
	 */	
	private static void processINQFACSErrors(
		com.sbc.eia.bis.embus.service.facsrc.InqOspResponse.impl.ResponseMessageImpl aFACSResponse,
		Utility aUtility,
		Hashtable aProp,
		BisContext aContext) 
		throws
			InvalidData,
			AccessDenied,
			BusinessViolation,
			SystemFailure,
			NotImplemented,
			ObjectNotFound,
			DataNotFound {
			
		String aFACSErrCodeAndDescrp = null;
		String aFACSAdapterErrMsg = null;
		String aLFACSRuleFile = null;

		try {
			List facsErrs = aFACSResponse.getStatusInfo().getErrors().getErrorCodeAndErrorDescription();				

			if(facsErrs.size() > 0) {
				ErrorCodeImpl errCode = (ErrorCodeImpl) facsErrs.get(0);
				ErrorDescriptionImpl errDesc = (ErrorDescriptionImpl) facsErrs.get(1);
				aFACSErrCodeAndDescrp = "Error Code = "+errCode.getValue()+"  Error Description = "+errDesc.getValue();
			}
		} catch(Exception any) { }


		try {	
			aFACSAdapterErrMsg = aFACSResponse.getResults().getFACSADAPTERERROR().getMSG();
		} catch (Exception any) { }

			
		if(aFACSErrCodeAndDescrp != null) {

			//ResponseMessage.StatusInfoType.ErrorsAggType.errorCode and ResponseMessage.StatusInfoType.ErrorsAggType.errorDescription
			//FACS Error Code and Error Description will be populated if there is any error
			//communicating from FACSRCAccess TO FACSAdapter Error message like Communication with FACSAdapter failed, a CORBA System Exception

				aLFACSRuleFile = (String) aProp.get(FACSRC_EXCEPTION_RULE_FILE_TAG);

				aUtility.log(LogEventId.DEBUG_LEVEL_1,"FACSRCAccess TO FACSAdapter Error message : <" + aFACSErrCodeAndDescrp);

				ExceptionBuilder.parseException(
									aContext,
									aLFACSRuleFile,
									null,
									null,
									aFACSErrCodeAndDescrp,
									true,
									1,
									null,
									null,
									aUtility,
									null,
									null,
									null);
			}
		else if(aFACSAdapterErrMsg != null) {

			//MSG in FACSAdapterType in Results will be populated if there is any error
			//Error Message like COMMUNICATION WITH LEGACY FAILED

				aLFACSRuleFile = (String) aProp.get(FACSRC_EXCEPTION_RULE_FILE_TAG);

				aUtility.log(LogEventId.DEBUG_LEVEL_1,"FACS Adapter Error message : <" + aFACSAdapterErrMsg);

				ExceptionBuilder.parseException(
									aContext,
									aLFACSRuleFile,
									null,
									null,
									aFACSAdapterErrMsg,
									true,
									1,
									null,
									null,
									aUtility,
									null,
									null,
									null);
			}
			
	}		

	/**
	 * @param aProp
	 * @param destination
	 * @param wireCenter
	 * @param eventName
	 * @return
	 */
	// Create the JMS properties from the prop map.

	private static Properties createJMSProperties(
		BisContext aContext,
		Hashtable aProp,
		String destination,
		String wireCenter,
		String eventName) {

		return BisToFACSRCMapping.mapProperties(
			aContext,
			destination,
			wireCenter,
			eventName,
			aProp);

	}

	/**
	 * @param type
	 * @param aUtility
	 */
	// Log the remote call.

	private static void logCall(String type, Utility aUtility) {

		aUtility.log(
			LogEventId.REMOTE_CALL,
			FACSRCHelper.FACSRC_SERVICE_NAME,
			FACSRCHelper.FACSRC_SERVICE_NAME + FACSRCHelper.FACS_RC_VERSION,
			FACSRCHelper.FACSRC_SERVICE_NAME + FACSRCHelper.FACS_RC_VERSION,
			type);

	}

	/**
	 * @param type
	 * @param aUtility
	 */

	// Log the remote return.

	private static void logReturn(String type, Utility aUtility) {

		aUtility.log(
			LogEventId.REMOTE_RETURN,
			FACSRCHelper.FACSRC_SERVICE_NAME,
			FACSRCHelper.FACSRC_SERVICE_NAME + FACSRCHelper.FACS_RC_VERSION,
			FACSRCHelper.FACSRC_SERVICE_NAME + FACSRCHelper.FACS_RC_VERSION,
			type);

	}

}