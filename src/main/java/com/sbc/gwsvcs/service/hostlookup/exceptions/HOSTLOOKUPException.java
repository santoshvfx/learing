// $Id: HOSTLOOKUPException.java,v 1.1 2002/09/29 04:16:26 dm2328 Exp $

package com.sbc.gwsvcs.service.hostlookup.exceptions;

import com.sbc.gwsvcs.access.vicuna.exceptions.*;

/**
 * This is the HOSTLOOKUP Exception class.
 * Creation date: (4/26/01 12:27:52 PM)
 * @author: David Brawley
 */
public class HOSTLOOKUPException extends ServiceException {
/**
 * Construct a HOSTLOOKUPException object.
 * Creation date: (4/26/01 12:33:23 PM)
 * @param arg1 java.lang.String
 * @param arg2 java.lang.String 
 */
public HOSTLOOKUPException(String arg1, String arg2) {
	super(arg1, arg2);
}
/**
 * Construct a HOSTLOOKUPException object.
 * Creation date: (4/26/01 12:33:23 PM)
 * @param arg1 java.lang.String
 * @param arg2 java.lang.String
 * @param arg3 java.lang.String
 */
public HOSTLOOKUPException(String arg1, String arg2, String arg3) {
	super(arg1, arg2, arg3);
}
}
