//$Id: ExceptionHelper.java,v 1.2 2005/08/04 19:22:44 jn1936 Exp $

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
//#      © 2002-2005 SBC Knowledge Ventures, L.P. All rights reserved.
//#
//# History :
//# Date      | Author               | Notes
//# --------------------------------------------------------------------
//# 7/12/2005 | Jinmin Ni	         | create
//# 7/27/2005 | Jinmin Ni            | Edit the error description to print
//#                                  | illegalArgumentException
//# 9/27/2005 | Jinmin Ni            | Move to current package from rm/java/src/com/sbc/eia/bis/rm/utilities
//#
package com.sbc.eia.bis.embus.service.utilities;

import gnu.regexp.RE;
import gnu.regexp.REException;
import gnu.regexp.REMatch;
import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;

import com.sbc.bccs.utilities.Utility;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilder;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilderRule;
import com.sbc.eia.bis.embus.service.access.EncoderException;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.idl.bis_types.AccessDenied;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.bis_types.BusinessViolation;
import com.sbc.eia.idl.bis_types.DataNotFound;
import com.sbc.eia.idl.bis_types.InvalidData;
import com.sbc.eia.idl.bis_types.NotImplemented;
import com.sbc.eia.idl.bis_types.ObjectNotFound;
import com.sbc.eia.idl.bis_types.SystemFailure;
import com.sbc.eia.idl.exception_types.ExceptionCode;
import com.sbc.eia.idl.types.Severity;

/**
 * @author jn1936
 */
public class ExceptionHelper
{
	public static String charSetRep = ".*";
	public static String charSetWithoutRuntimeRep = ".[^rR][^uU][^nN][^tT][^iI][^mM][^eE]"+charSetRep;
	public static String aAbortSerializationExceptionRep =
		"com.sun.xml.bind.serializer.AbortSerializationException" + charSetRep;
	public static String CREATE_EMBUS_SERVICE_REQUEST_EXCEPTION_CODE = "JAXB-99999";
	
	/**
	 * @param e
	 * @param fromClassName
	 * @param aUtility
	 * @throws BusinessViolation
	 * @throws ObjectNotFound
	 * @throws InvalidData
	 * @throws AccessDenied
	 * @throws SystemFailure
	 * @throws DataNotFound
	 * @throws NotImplemented
	 */
	public static void throwException(
		IllegalArgumentException e,
		String fromClassName,
		Utility aUtility,
		String ruleFile,
		BisContext aContext)
		throws
			BusinessViolation,
			ObjectNotFound,
			InvalidData,
			AccessDenied,
			SystemFailure,
			DataNotFound,
			NotImplemented
	{
		
		parseAndThrowException(
			e,
			fromClassName,
			"java.lang.IllegalArgumentException: "+e.getMessage(),
			null,
			CREATE_EMBUS_SERVICE_REQUEST_EXCEPTION_CODE,
			aUtility,
			ruleFile,
		    aContext);
	}
	
	/**
	 * @param e
	 * @param fromClassName
	 * @param aUtility
	 * @throws BusinessViolation
	 * @throws ObjectNotFound
	 * @throws InvalidData
	 * @throws AccessDenied
	 * @throws SystemFailure
	 * @throws DataNotFound
	 * @throws NotImplemented
	 */
	public static void throwException(
		EncoderException e,
		String fromClassName,
		Utility aUtility, 
		String ruleFile,
		BisContext aContext)
		throws
			BusinessViolation,
			ObjectNotFound,
			InvalidData,
			AccessDenied,
			SystemFailure,
			DataNotFound,
			NotImplemented
	{
		
		String detailMsg = e.getMessage();
		//when e.getMessage() didn't give any info, extract desired message from exception stacktrace
		if (e.getMessage() == null)
		{
			parseAndThrowException(
				e,
				fromClassName,
				null,
				aAbortSerializationExceptionRep,
				CREATE_EMBUS_SERVICE_REQUEST_EXCEPTION_CODE,
				aUtility,
				ruleFile,
				aContext);
		}
		else
		{
			//e.getMessage() give some info, unknown nested exception for now. Just throw exception for now.
			//for future, when we have collect some unknown nested exception example, might want to extract info
			//if needed. 
			ExceptionBuilder.parseException(
				aContext,
				ruleFile,
				null,
				CREATE_EMBUS_SERVICE_REQUEST_EXCEPTION_CODE,
				e.getMessage(),
				true,
				ExceptionBuilderRule.NO_DEFAULT,
				null,
				e,
				aUtility,
				null,
				null,
				null);
		}
	}
	
	/**
	 * @param e
	 * @param fromClassName
	 * @param exceptionDetailMessage
	 * @param exceptionDetailMessageExp
	 * @param exceptionCode
	 * @param aUtility
	 * @throws BusinessViolation
	 * @throws ObjectNotFound
	 * @throws InvalidData
	 * @throws AccessDenied
	 * @throws SystemFailure
	 * @throws DataNotFound
	 * @throws NotImplemented
	 */
	private static void parseAndThrowException(
		Exception e,
		String fromClassName,
		String exceptionDetailMessage,
		String exceptionDetailMessageExp,
		String exceptionCode,
		Utility aUtility,
		String ruleFile,
		BisContext aContext)
	throws
		BusinessViolation,
		ObjectNotFound,
		InvalidData,
		AccessDenied,
		SystemFailure,
		DataNotFound,
		NotImplemented
	{
		String errorText = getStackTraceAndLogException(e, aUtility);
		
		//get desired nested exception message
		StringBuffer detailMsg = new StringBuffer("detailMessage: "); 
		if(exceptionDetailMessage == null && exceptionDetailMessageExp != null)
		{
			getMatchString(errorText,exceptionDetailMessageExp, detailMsg);
		}
		else 
		{
			detailMsg.append(exceptionDetailMessage == null ? "" : exceptionDetailMessage);	
		}

		//	getting desired nested exception location
		StringBuffer errorLocation = new StringBuffer("location: "); 
		getMatchString(errorText,fromClassName+charSetWithoutRuntimeRep, errorLocation);
	
		ExceptionBuilder.parseException(
				aContext,
				ruleFile,
				null,
				CREATE_EMBUS_SERVICE_REQUEST_EXCEPTION_CODE,
				detailMsg.toString() + " @ " + errorLocation.toString(),
				true,
				ExceptionBuilderRule.NO_DEFAULT,
				null,
				e,
				aUtility,
				null,
				null,
				null);
	}
	
	/**
	 * @param errorText
	 * @param exp
	 * @param errorDes
	 * @throws BusinessViolation
	 * @throws ObjectNotFound
	 * @throws InvalidData
	 * @throws AccessDenied
	 * @throws SystemFailure
	 * @throws DataNotFound
	 * @throws NotImplemented
	 */
	private static void getMatchString(String errorText,String exp, StringBuffer errorDes)
	throws
		BusinessViolation,
		ObjectNotFound,
		InvalidData,
		AccessDenied,
		SystemFailure,
		DataNotFound,
		NotImplemented
	{
		REMatch match = null;

		try
		{
			RE text = new RE(exp);
			match = text.getMatch(errorText);
		}
		catch (REException aREe)
		{
			//No able to create gnu.regexp.RE object. No search for nested exception message will
			//be performed.  No need to throw any exception.	
		}
		
		errorDes.append("[");
		errorDes.append( (match == null ? "" : match.toString()) );
		errorDes.append("]");
	}
	
	/**
	 * @param e
	 * @param aUtility
	 * @return
	 */
	public static String getStackTraceAndLogException(Exception e, Utility aUtility)
	{
		ByteArrayOutputStream stackTraceBuf = new ByteArrayOutputStream();
		e.printStackTrace(new PrintWriter(stackTraceBuf, true));
		String errorText = stackTraceBuf.toString();

		aUtility.log(LogEventId.STACK_TRACE, stackTraceBuf.toString());
		return errorText;
	}
}		

