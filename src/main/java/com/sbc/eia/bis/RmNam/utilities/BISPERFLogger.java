// $Id: BISPERFLogger.java,v 1.1 2002/09/29 03:23:27 dm2328 Exp $

package com.sbc.eia.bis.RmNam.utilities;

/**
 * Insert the type's description here.
 * Creation date: (5/10/01 10:30:32 AM)
 * @author: Sam Lok - Local
 */
public final class BISPERFLogger {
/**
 * BISPERFLogger constructor comment.
 */
public BISPERFLogger() {
	super();
}
/**
 * Returns a string with the proper BISPERF format.
 * Creation date: (5/10/01 3:53:39 PM)
 * @param BIS_NAME java.lang.String
 * @param eventSubType java.lang.String
 * @param originMethod java.lang.String
 * @param destApp java.lang.String
 * @param destMethod java.lang.String
 * @param eventId java.lang.String
 */
public static String formatMsg( String BIS_NAME,
					String eventSubType, 
					String originMethod,
					String destApp,
					String destMethod ) 
{
	return ( new String( BIS_NAME ) + "|" +
				  originMethod + "|" +
				  destApp + "|" +
				  destMethod + "|" +
				  eventSubType ) ;
}
}
