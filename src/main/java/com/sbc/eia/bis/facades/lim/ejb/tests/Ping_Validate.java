
package com.sbc.eia.bis.facades.lim.ejb.tests;

/**
 * @author Noemi Luzung (nl9267)
 * Creation Date: 06/19/2008
 * Added Ping Validate class to check for client authorizatin of ping method.
 */

	import java.util.Hashtable;

	import com.sbc.bccs.idl.helpers.IDLUtil;
	import com.sbc.bccs.idl.helpers.ObjectPropertyManager;
	import com.sbc.bccs.utilities.Utility;
	import com.sbc.eia.bis.authorization.AuthorizationException;
	import com.sbc.eia.bis.authorization.ClientAuthorization;
	import com.sbc.eia.bis.framework.BisContextManager;
	import com.sbc.eia.bis.framework.logging.BisLogger;
	import com.sbc.eia.bis.framework.logging.BisLoggerFactory;
	import com.sbc.eia.bis.framework.logging.LogEventId;
	import com.sbc.eia.bis.lim.util.BisContextHelper;
	import com.sbc.eia.idl.bis_types.AccessDenied;
	import com.sbc.eia.idl.bis_types.BisContext;
	import com.sbc.eia.idl.bis_types.BisContextProperty;
	import com.sbc.eia.idl.bis_types.BusinessViolation;
	import com.sbc.eia.idl.bis_types.DataNotFound;
import com.sbc.eia.idl.bis_types.DuplicateObject;
	import com.sbc.eia.idl.bis_types.InvalidData;
	import com.sbc.eia.idl.bis_types.MultipleExceptions;
	import com.sbc.eia.idl.bis_types.NotImplemented;
	import com.sbc.eia.idl.bis_types.ObjectNotFound;
	import com.sbc.eia.idl.bis_types.SystemFailure;
	import com.sbc.eia.idl.bis_types.bishelpers.BisContextBisHelper;
	import com.sbc.eia.idl.lim.bishelpers.PingReturnBisHelper;
	import com.sbc.eia.idl.exception_types.ExceptionCode;
	import com.sbc.eia.bis.RmNam.utilities.TranBase;
	//import com.sbc.eia.idl.lim.BisContextHelper;
	import com.sbc.eia.idl.lim.*;	
	import com.sbc.eia.idl.lim.bishelpers.PingReturnBisHelper;
	import com.sbc.eia.idl.types.ExceptionData;
	import com.sbc.eia.idl.types.Severity;
	import com.sbc.eia.idl.types.SeverityOpt;
	

	public class Ping_Validate extends com.sbc.eia.bis.framework.methods.Ping{

		private Hashtable aProperties = null;
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
		public Ping_Validate(Hashtable param) {
			aProperties = param;
		}

		/**
		 * @param aBisContext
		 * @return PingReturn
		 * @throws InvalidData
		 * @throws AccessDenied
		 * @throws BusinessViolation
		 * @throws SystemFailure
		 * @throws NotImplemented
		 * @throws ObjectNotFound
		 * @throws DataNotFound
		 * @throws MultipleExceptions
		 */
		public PingReturn execute(BisContext aContext)
		throws
			InvalidData,
			AccessDenied,
			BusinessViolation,
			SystemFailure,
			NotImplemented,
			ObjectNotFound,
			DataNotFound,
			MultipleExceptions {

		PingReturn pingReturn = null;
		
		// if aContext or aContext.aProperties is null initialize BisContext to prevent null pointer or marshalling errors.
		aContext = BisContextHelper.initialize(aContext);

		BisLogger bisLogger =
			BisLoggerFactory.create(
				(String) aProperties.get("BIS_NAME"),
				(String) aProperties.get("BIS_VERSION"));

		try {

			BisContextManager bisContextManager =
				new BisContextManager(aContext);

			String li = bisContextManager.getLoggingInformationString();

			if (li != null && li.trim().length() > 0) {
				//this is done for the case where the client has put logging information in the bis context 
				bisLogger.setLoggingInformationString(li);
			}			
			
			BisContext resultBisContext =
				execute(
					bisContextManager.getBisContext(),
					bisLogger);

			pingReturn = new PingReturn(resultBisContext);
			//aReturn = new PingReturn(resultBisContext);
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
			LogEventId.FAILURE, "LIM Caught an exception while executing Ping method: " + e.toString());
			throw new SystemFailure(
					aContext,
					new ExceptionData(
						ExceptionCode.ERR_LIM_UNEXPECTED_ERROR,
						"LIM Caught an exception while executing SelfTest method: " + e.toString(),
						IDLUtil.toOpt("LIM"),
						(SeverityOpt) IDLUtil.toOpt(SeverityOpt.class, Severity.UnRecoverable)));
			
			}					

		return pingReturn;
	}
	
	/**
	 * Method validateContextAndClient
	 * @param bisLogger
	 * @param aResultList
	 * @param aContext
	 * @param aContextOPM
	 * @param groupId
	 * @param errorCode
	 * @param bisLogger
	 */
	public void validateContextAndClient(
			BisContext aContext,
			ObjectPropertyManager aContextOPM, 
			String groupId,
			//String errorCode,
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
		
		String myMethodName = "ping::validateContextAndClient()";
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
				new String("ping"),
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

	protected BisContext pingLogic(
			BisContext bisContext, 
			BisLogger bisLogger) 
		throws 
		InvalidData, 
		DataNotFound, 
		DuplicateObject, 
		ObjectNotFound, 
		AccessDenied, 
		MultipleExceptions, 
		NotImplemented, 
		BusinessViolation, 
		SystemFailure 
	{
		
		ObjectPropertyManager aContextOPM = new ObjectPropertyManager( bisContext.aProperties );
		
		validateContextAndClient(
				bisContext,
			 	aContextOPM,
			 	null,			// group_ids
			 	//ExceptionCode.ERR_LIM_APPLICATION_ACCESS_DENIED,				
				bisLogger);
		
		return bisContext;
	}
}


