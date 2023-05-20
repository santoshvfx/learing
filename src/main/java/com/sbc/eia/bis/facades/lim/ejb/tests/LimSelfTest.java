//$Id: LimSelfTest.java,v 1.12 2005/05/03 22:14:12 rz7367 Exp $
package com.sbc.eia.bis.facades.lim.ejb.tests;


import java.util.Hashtable;
import java.util.Properties;

import com.sbc.bccs.idl.helpers.IDLUtil;
import com.sbc.bccs.idl.helpers.ObjectPropertyManager;
import com.sbc.bccs.utilities.Utility;
import com.sbc.eia.bis.RmNam.utilities.TranBase;
import com.sbc.eia.bis.authorization.AuthorizationException;
import com.sbc.eia.bis.authorization.ClientAuthorization;
import com.sbc.eia.bis.framework.BisContextManager;
import com.sbc.eia.bis.framework.logging.BisLogger;
import com.sbc.eia.bis.framework.logging.BisLoggerFactory;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.bis.framework.methods.SelfTest;
import com.sbc.eia.bis.lim.util.BisContextHelper;
import com.sbc.eia.bis.lim.util.Emailer;
//import com.sbc.eia.bis.selftest.SelfTestDBConnection;
//import com.sbc.eia.bis.selftest.SelfTestPropertiesFiles;
import com.sbc.eia.bis.selftest.SelfTestResult;
import com.sbc.eia.idl.bis_types.AccessDenied;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.bis_types.BisContextProperty;
import com.sbc.eia.idl.bis_types.BusinessViolation;
import com.sbc.eia.idl.bis_types.DataNotFound;
import com.sbc.eia.idl.bis_types.InvalidData;
import com.sbc.eia.idl.bis_types.MultipleExceptions;
import com.sbc.eia.idl.bis_types.NotImplemented;
import com.sbc.eia.idl.bis_types.ObjectNotFound;
import com.sbc.eia.idl.bis_types.SystemFailure;
import com.sbc.eia.idl.exception_types.ExceptionCode;
import com.sbc.eia.idl.lim.SelfTestReturn;
import com.sbc.eia.idl.types.ExceptionData;
import com.sbc.eia.idl.types.Severity;
import com.sbc.eia.idl.types.SeverityOpt;
import com.sun.java.util.collections.ArrayList;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.naming.NamingException;

/**
 * @author Jan Michael Soan (js3826)
 * Creation date: (11/26/06 5:05 AM)
 * @author Noemi Luzung (nl9267) 05/22/2008
 * Enhanced SelfTest Implementation
 * 
 */

public class LimSelfTest extends SelfTest 
{

	private Hashtable aProperties = null;
	private ArrayList aResultList = new ArrayList ();

	
	private class SecondaryLogger extends TranBase
	{
		BisLogger abisLogger = null;
		public SecondaryLogger(BisContext aContext, BisLogger bisLogger)
		{
			super();
			this.setPROPERTIES(aProperties);
			this.setCallerContext(aContext);
			this.theLogAssistant.setCorrID(bisLogger.getCorrelationId());

			abisLogger = bisLogger;
		}
		// Override log methods to use the BisLogger log methods
		public void log(String eventId, String message)
		{
			abisLogger.log(eventId, message);
		}

		public void log(String eventId, String code, String text, String origination)
		{
			abisLogger.log(eventId, code, text, origination);
		}
	};
    
	/**
	 * Constructor for  SelfTest.
	 * @param param
	 */
	public LimSelfTest(Hashtable param) 
    {
		aProperties = param;
	}

	/**
	 * @param aBisContext
	 * @return SelfTestReturn
	 * @throws InvalidData
	 * @throws AccessDenied
	 * @throws BusinessViolation
	 * @throws SystemFailure
	 * @throws NotImplemented
	 * @throws ObjectNotFound
	 * @throws DataNotFound
	 * @throws MultipleExceptions
	 */
	public SelfTestReturn execute(BisContext aContext)
		throws
			InvalidData,
			AccessDenied,
			BusinessViolation,
			SystemFailure, 
			NotImplemented,
			ObjectNotFound,
			DataNotFound,
			MultipleExceptions 
    {
		SelfTestReturn aReturn = null;
	
//		 if aContext or aContext.aProperties is null initialize BisContext to prevent null pointer or marshalling errors. 
		aContext = BisContextHelper.initialize(aContext);
		
		BisLogger bisLogger =
			BisLoggerFactory.create(
				(String) aProperties.get("BIS_NAME"),
				(String) aProperties.get("BIS_VERSION"));		

		try 
        {
			BisContextManager bisContextManager = new BisContextManager(aContext);

			String li = bisContextManager.getLoggingInformationString();

			if (li != null && li.trim().length() > 0)
			{
				//this is done for the case where the client has put logging information in the bis context 
				bisLogger.setLoggingInformationString(li);
			}

			BisContext resultBisContext = execute(bisContextManager.getBisContext(), bisLogger);
			aReturn = new SelfTestReturn(resultBisContext);
		} 
		catch( AccessDenied e ) 		{throw e;}
		catch( BusinessViolation e ) 	{throw e;}
		catch( DataNotFound e ) 		{throw e;}
		catch( InvalidData e ) 		    {throw e;}
		catch( NotImplemented e ) 		{throw e;}
		catch( ObjectNotFound e ) 		{throw e;}
		catch( SystemFailure e ) 		{throw e;}
		catch (Throwable e) 
		{
		e.printStackTrace();
		bisLogger.log (
				LogEventId.FAILURE,
				"LIM Caught an exception while executing SelfTest method:"  + e.toString());
				throw new SystemFailure(
						aContext,
						new ExceptionData(
							ExceptionCode.ERR_LIM_UNEXPECTED_ERROR,
							"LIM Caught an exception while executing SelfTest method: " + e.toString(),
							IDLUtil.toOpt("LIM"),
							(SeverityOpt) IDLUtil.toOpt(SeverityOpt.class, Severity.UnRecoverable)));
			
		}
    
		return aReturn;
	}

	

	/**
	 * @see com.sbc.eia.bis.framework.methods.SelfTest#selfTestLogic(BisContext, BisLogger)
	 */
	public BisContext selfTestLogic(BisContext aContext, BisLogger bisLogger) 
		throws
			InvalidData,
			DataNotFound,
			ObjectNotFound,
			AccessDenied,
			MultipleExceptions,
			NotImplemented,
			BusinessViolation,
			SystemFailure
	{
		String myMethodName = "LIMSelfTest::selfTestLogic()";
		bisLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);

		SecondaryLogger aSecondaryLogger = new SecondaryLogger(aContext, bisLogger);
		ObjectPropertyManager aContextOPM = new ObjectPropertyManager( aContext.aProperties );
		
		//get the selftest property files from the lim.properties
		String selfTestPropertiesFile = (String)aProperties.get("SELFTEST_PROPERTIES_FILE");
		String selfTestServicesFile = (String)aProperties.get("SELFTEST_SERVICES_FILE");
		String selfTestDatabaseFile = (String)aProperties.get("SELFTEST_DATABASE_FILE");
		
		//Check if Application is authorized to run selftest
		
		validateContextAndClient(
				aContext,
				aContextOPM , 
				null,
				ExceptionCode.ERR_LIM_APPLICATION_ACCESS_DENIED,
		  bisLogger );
				
						

		///////Comment other Selftest Logic Classes if you only want to test one class.

//		new SelfTestPropertiesFiles(selfTestPropertiesFile, aContext, bisLogger, aResultList);
//		new SelfTestVicunaServices(selfTestServicesFile, aContext, aProperties, aSecondaryLogger, bisLogger, aResultList);
//		new SelfTestEMBUS(selfTestServicesFile, aContext, aProperties, aSecondaryLogger, bisLogger, aResultList);
//		new SelfTestOVALS(selfTestServicesFile, aContext, aProperties, aSecondaryLogger, bisLogger, aResultList);
//		new SelfTestRCAccess(selfTestServicesFile, aContext, aProperties, aSecondaryLogger, bisLogger, aResultList);
		try {
			new SelfTestBISAccess(selfTestServicesFile, aContext, aProperties, bisLogger, (Utility) aSecondaryLogger, aResultList);
		} catch (NamingException e) {
			e.printStackTrace();
		}
//		new SelfTestDBConnection(selfTestDatabaseFile, aContext, aProperties, aSecondaryLogger, bisLogger, aResultList);
	
		//////Recipient wll receive email notification once testing is done.
		this.sendEmail(aContext, aSecondaryLogger, bisLogger); 
		//
		SelfTestResult.displayResultSet(bisLogger, aResultList);
		
		validateResult(aContext, bisLogger, aResultList);
		///////////////////////////////
		
		bisLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
		return aContext;
	}

	/**
	 * Method getEmailProps.
	 * @param bisLogger
	 * @return Properties
	 */
	private Properties getEmailProps(BisLogger bisLogger)
	{
		String myMethodName = "LIMSelfTest::getEmailProps()";
		bisLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);

		Properties aEmailProps = new Properties();

		aEmailProps.put("mail.transport.protocol", "smtp");

		String PRIMARY_MAIL_HOST;
		if ((PRIMARY_MAIL_HOST = ((String) aProperties.get("PRIMARY_SMTP_SERVER")).trim()) != null)
		{
			aEmailProps.put("mail.smtp.host", PRIMARY_MAIL_HOST);

			try
			{
				bisLogger.log(
					LogEventId.INFO_LEVEL_1,
					"PRIMARY_SMTP_SERVER=<" + PRIMARY_MAIL_HOST + ">");
			}
			catch (Exception e)
			{
				bisLogger.log(LogEventId.FAILURE, "PRIMARY_SMTP_SERVER not set");
			}
		}

		String SECONDARY_MAIL_HOST;
		if ((SECONDARY_MAIL_HOST = ((String) aProperties.get("SECONDARY_SMTP_SERVER")).trim())
			!= null)
		{
			aEmailProps.put("secondary.mail.smtp.host", SECONDARY_MAIL_HOST);

			try
			{
				bisLogger.log(
					LogEventId.INFO_LEVEL_1,
					"SECONDARY_SMTP_SERVER=<" + SECONDARY_MAIL_HOST + ">");
			}
			catch (Exception e)
			{
				bisLogger.log(LogEventId.FAILURE, "SECONDARY_MAIL_HOST not set");
			}
		}

		if ((PRIMARY_MAIL_HOST.trim().equals("")) && (SECONDARY_MAIL_HOST.trim().equals("")))
			bisLogger.log(
				LogEventId.FAILURE,
				"Missing Both Primary SMTP Server "
					+ "and  the Secondary SMTP Server Names. "
					+ "Require atleast one SMTP Server Name");

		bisLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
		return aEmailProps;
	}

	/**
	 * Method sendEmail.
	 * @param aContext
	 * @param aSecondaryLogger
	 * @param bisLogger
	 */
	private void sendEmail(
		BisContext aContext,
		SecondaryLogger aSecondaryLogger,
		BisLogger bisLogger)
	{
		String myMethodName = "LIMSelfTest::sendEmail()";
		bisLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);

		String recipient = null;
		ObjectPropertyManager aContextOPM = new ObjectPropertyManager(aContext.aProperties);

		if ((recipient = aContextOPM.getValue("EMAIL_RECIPIENT_LIMSELFTEST")) != null)
		{
			String[] recipients = { recipient };
			Emailer aEmailer = new Emailer((Utility) aSecondaryLogger, aProperties);
			aEmailer.setEmailConfig(getEmailProps(bisLogger));

			try
			{
				aEmailer.sendEmail(
					recipients,
					((String) aProperties.get("SENDER_EMAIL_ADDRESS")).trim(),
					"LIMSelfTest result log",
					SelfTestResult.toString(aResultList));
			}
			catch (Exception e)
			{
				bisLogger.log(LogEventId.FAILURE, "Email failed to send: " + e.getMessage());
			}
		}
		bisLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
	}

	/**
	 * Method validateContextAndClient
	 * @param bisLogger
	 * @param aResultList
	 * @param aContext
	 * @param aContextOPM
	 * @param groupId
	 * @param errorCode
	 * @param systemFailureCode
	 * @param bisLogger
	 */
	public void validateContextAndClient(
			BisContext aContext,
			ObjectPropertyManager aContextOPM, 
			String groupId,
			String errorCode,
			BisLogger bisLogger)
			throws 
			InvalidData, 
			AccessDenied, 
			BusinessViolation, 
			SystemFailure, 
			NotImplemented, 
			ObjectNotFound, 
			DataNotFound
	{
		
		String myMethodName = "LIMSelfTest::validateContextAndClient()";
		bisLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);
		
		try 
		{	
			if( aContextOPM.getValue( BisContextProperty.APPLICATION ) == null ||
		    aContextOPM.getValue( BisContextProperty.APPLICATION ).trim().equals(""))
			{
			     bisLogger.log (LogEventId.FAILURE, ExceptionCode.ERR_LIM_INVALID_APPLICATION, 
			     "Required input is not defined",(String) aProperties.get("BIS_NAME"));
			

				throw  new InvalidData (aContext,
						new ExceptionData(
								ExceptionCode.ERR_LIM_INVALID_APPLICATION,
								"Required input is not defined",
								IDLUtil.toOpt("LIM"),
								(SeverityOpt) IDLUtil.toOpt(SeverityOpt.class, Severity.UnRecoverable)));
		
		}
			
			if ( ClientAuthorization.isAuthorized(				
				new String("selfTest"),
				aContextOPM.getValue( BisContextProperty.APPLICATION ),		// application
				aContextOPM.getValue( BisContextProperty.BUSINESSUNIT),		// business unit
				aContextOPM.getValue( BisContextProperty.SERVICECENTER),	// service center
				groupId,													// group id 
				aProperties,												// hash table
				bisLogger ) == false )										// logger
			{
				throw new AuthorizationException(
						"Unauthorized user <" + aContextOPM.getValue( BisContextProperty.APPLICATION ) + "> <" +
						aContextOPM.getValue( BisContextProperty.BUSINESSUNIT) + "> <" +
						aContextOPM.getValue( BisContextProperty.SERVICECENTER) + "> <" +
						groupId + ">");
			}
		}
		catch (AuthorizationException e )
		{
			bisLogger.log(LogEventId.FAILURE, ExceptionCode.ERR_LIM_APPLICATION_ACCESS_DENIED, e.getMessage(), (String) aProperties.get("BIS_NAME"));
			
			throw new AccessDenied(
					aContext,
					new ExceptionData(
						ExceptionCode.ERR_LIM_APPLICATION_ACCESS_DENIED,
						e.toString(),
						IDLUtil.toOpt("LIM"),
						(SeverityOpt) IDLUtil.toOpt(SeverityOpt.class, Severity.UnRecoverable)));
		} 
		bisLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
	}

	/**
	 * Method validateResult
	 * @param aContext
	 * @param bisLogger
	 * @param aResultSet
	 */
	public void validateResult(BisContext aContext, BisLogger bisLogger, ArrayList aResultSet) 
		throws 
		InvalidData, 
		AccessDenied, 
		BusinessViolation, 
		SystemFailure, 
		NotImplemented, 
		ObjectNotFound, 
		DataNotFound,
		MultipleExceptions{
		
		String myMethodName = "LIMSelfTest::validateResult()";
		bisLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);
		
		ArrayList failures = new ArrayList();
		
		ArrayList failedResults = SelfTestResult.validateResultSet(bisLogger, aResultList);
		
		if(!failedResults.isEmpty())
		{	
			if(failedResults.size() == 1)
			{
				bisLogger.log(
						LogEventId.FAILURE,
						ExceptionCode.ERR_LIM_UNEXPECTED_ERROR,
						failedResults.get(0).toString(),
					aProperties.get("BIS_NAME").toString());
				
				throw new SystemFailure(
					aContext,
					new ExceptionData(
						ExceptionCode.ERR_LIM_UNEXPECTED_ERROR,
						failedResults.get(0).toString(),
						IDLUtil.toOpt("LIM"),
						(SeverityOpt) IDLUtil.toOpt(SeverityOpt.class, Severity.UnRecoverable)));
			}
			else
			{
				for(int i = 0; i < failedResults.size(); i++)
				{
					String results = (String)failedResults.get(i);
					failures.add(new ExceptionData(
						ExceptionCode.ERR_LIM_UNEXPECTED_ERROR,
						results.toString(),
						IDLUtil.toOpt("LIM"),
						(SeverityOpt) IDLUtil.toOpt(SeverityOpt.class, Severity.UnRecoverable)));
				}
			}
		}
		if(!failures.isEmpty())
		{
			bisLogger.log(
					LogEventId.INFO_LEVEL_1,
					"LIM encountered MultipleExceptions while running selfTest transaction.");
			
			ExceptionData[] exceptions = new ExceptionData[failures.size()];
			
			for(int i = 0; i < failures.size(); i++)
			{
				exceptions[i] = (ExceptionData)failures.get(i);
				
				bisLogger.log(
					LogEventId.FAILURE, exceptions[i].aCode,
					exceptions[i].aDescription,
					(String) aProperties.get("BIS_NAME"));
			}
			throw new MultipleExceptions(aContext, exceptions);
		}
		bisLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
	}
}
