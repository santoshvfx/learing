package com.sbc.eia.bis.RmNam.utilities;

import com.sbc.eia.bis.framework.*;
import java.util.*;
import java.text.*;
import com.sbc.eia.bis.framework.logging.*;



import java.util.Hashtable;


import com.sbc.eia.idl.bis_types.*;

/**
 * A Logger that uses the BisLogger Framework to do logging.
 * Creation date: (10/10/00 1:43:35 PM)
 * @author: Sam Lok - Local
 */
public class Logger {
	private BisLogger bl = null ;
	private BisLoggerFactory blFactory = null;

 	// if STDOUT == TRUE, then logging will go to stdout as well.
	private boolean STDOUT = false;

	private final static java.lang.String LOG_DEMARCATION_STR = "++++++++++++++++++++";
/**
 * Logger constructor comment.
 */
public Logger() {
	super();
}
/**
 * Logger contructor manages the BisLoggerFactory and the BisLogger.
 * Creation date: (10/10/00 3:19:15 PM)
 * @param stdout String
 * @param bisName String
 * @param bisVersion String
 * @param logPolicyPath String
 */
public Logger( String stdout,
			   String bisName,
			   String bisVersion,
			   String logPolicyPath ) 
{
	_init( stdout, bisName, bisVersion, logPolicyPath ) ;
}
/**
 * Logger contructor manages the BisLoggerFactory and the BisLogger.  Requires a hashtable
 *		of BIS properties to contain BIS_NAME, BIS_VERSION, LOG_POLICY_PATH and STDOUT (TRUE or FALSE).
 * Creation date: (04/24/01 3:19:15 PM)
 * @param BisProps Hashtable
 */
public Logger( Hashtable BisProps ) 
{
	_init( ((String)BisProps.get("STDOUT")).trim(), 
		   ((String)BisProps.get("BIS_NAME")).trim(),
		   ((String)BisProps.get("BIS_VERSION")).trim(),
		   ((String)BisProps.get("LOG_POLICY_PATH")).trim() ) ;

}
/**
 * Logger _init() manages the BisLoggerFactory and the BisLogger.
 * Creation date: (04/24/01 3:19:15 PM)
 * @param stdout String
 * @param bisName String
 * @param bisVersion String
 * @param logPolicyPath String
 */
private void _init( String stdout,
			  		String bisName,
			  		String bisVersion,
			  		String logPolicyPath ) 
{
	// setup BisLogger
	if ( blFactory == null )
			blFactory = new BisLoggerFactoryImpl() ;
	if ( bl == null )
		bl = blFactory.createBisLogger( bisName, bisVersion, logPolicyPath ) ;

	setSTDOUT( stdout ) ;
}
/**
 * Calls bl.endConversationContext() to terminate a logging context.
 * Creation date: (4/27/01 9:20:22 AM)
 */
public void end() 
{
	bl.endConversationContext() ;
}
/**
 * Insert the method's description here.
 * Creation date: (7/25/01 11:05:54 AM)
 * @return com.sbc.eia.bis.framework.logging.BisLogger
 */
public BisLogger getBisLogger() {
	return bl;
}
/**
 * Returns the state of STDOUT.
 * Creation date: (10/11/00 9:01:23 AM)
 * @return boolean
 */
boolean isSTDOUT() {
	return STDOUT;
}
/**
 * Logs the message using bisContextHelper.  The demarcation string is added for certain events
 * for readability.  message is also sent to stdout if the STDOUT flag is set.
 * Creation date: (10/11/00 8:58:56 AM)
 * @param eventId java.lang.String
 * @param message java.lang.String
 */
public void log(String eventId, String message) 
{
	
	if ( eventId == LogEventId.ENTRY || eventId == LogEventId.EXIT || eventId == LogEventId.EXCEPTION )
			message = LOG_DEMARCATION_STR + " " + message + " " + LOG_DEMARCATION_STR ;
			
	if ( bl != null ) {
		if (eventId == LogEventId.ENTRY)
			bl.log_entry(message);
		
		else if (eventId == LogEventId.EXIT)
			bl.log_exit(message);
			
		else
			bl.log( eventId, message ) ;
	
	}
	if ( isSTDOUT() )
	{
		Calendar now = Calendar.getInstance();
		System.out.println( now.get( Calendar.YEAR ) + "/" +
							( now.get( Calendar.MONTH ) + 1 ) + "/" +
							now.get( Calendar.DAY_OF_MONTH ) + " " +
							now.get( Calendar.HOUR_OF_DAY  ) + ":" +
							now.get( Calendar.MINUTE ) + ":" +
							now.get( Calendar.SECOND ) + "." +
							now.get( Calendar.MILLISECOND ) + 
							( now.get( Calendar.ZONE_OFFSET ) / 1000 / 60 / 60 ) + " " +
							eventId + " = " + message );
	}
}
/**
 * setter for STDOUT.
 * Creation date: (10/11/00 9:01:23 AM)
 * @param newSTDOUT String TRUE/FALSE
 */
public void setSTDOUT(String newSTDOUT) 
{
	if ( newSTDOUT.equalsIgnoreCase( "TRUE" ) )
		STDOUT = true ;
}
/**
 * setter for STDOUT.
 * Creation date: (10/11/00 9:01:23 AM)
 * @param newSTDOUT boolean
 */
public void setSTDOUT(boolean newSTDOUT) {
	STDOUT = newSTDOUT;
}
/**
 * Calls bl.startConversationContext() to begin a new logging context.
 * Creation date: (4/24/01 3:16:22 PM)
 * @param aContext com.sbc.eia.idl.bis_types_2_0_0.BisContext
 */
public void start(BisContext aContext) 
{
	bl.startConversationContext( new BisContextManager( aContext ) ) ;
}

/**
logFailure() calls BisLoger to log failure
*/

public void log(String eventId, String code, String text, String origination)	
{
	// ExceptionThrower.throwException( callerContext, code, text, origination, severity, this ) ;

	if (bl != null)
	{
		bl.log( eventId, code, text, origination);
	}
	
	if ( isSTDOUT() )
	{
		Calendar now = Calendar.getInstance();
		System.out.println( now.get( Calendar.YEAR ) + "/" +
							( now.get( Calendar.MONTH ) + 1 ) + "/" +
							now.get( Calendar.DAY_OF_MONTH ) + " " +
							now.get( Calendar.HOUR_OF_DAY  ) + ":" +
							now.get( Calendar.MINUTE ) + ":" +
							now.get( Calendar.SECOND ) + "." +
							now.get( Calendar.MILLISECOND ) + 
							( now.get( Calendar.ZONE_OFFSET ) / 1000 / 60 / 60 ) + " " +
							eventId + " = " + code + " " + text + " " + origination  );
	}
}

/**
 * Calls Bislogger to log the remote call.
 * Creation date: (9/16/02 1:53:35 PM)
 */
public void log(
	String eventId,
	String location,
	String service,
	String component,
	String operation) {
	if (bl != null) {
		bl.log(eventId, location, service, component, operation);
	}
	
	if ( isSTDOUT() )
	{
		Calendar now = Calendar.getInstance();
		System.out.println( now.get( Calendar.YEAR ) + "/" +
							( now.get( Calendar.MONTH ) + 1 ) + "/" +
							now.get( Calendar.DAY_OF_MONTH ) + " " +
							now.get( Calendar.HOUR_OF_DAY  ) + ":" +
							now.get( Calendar.MINUTE ) + ":" +
							now.get( Calendar.SECOND ) + "." +
							now.get( Calendar.MILLISECOND ) + 
							( now.get( Calendar.ZONE_OFFSET ) / 1000 / 60 / 60 ) + " " +
							eventId + " = " + 
							location + " " + service + " " + component + " " + operation );
	}
}
}