// $Id: ASONServiceException.java,v 1.1 2002/09/29 03:53:46 dm2328 Exp $

package com.sbc.gwsvcs.service.asonservice.exceptions;

import com.sbc.gwsvcs.access.vicuna.exceptions.*;

/**
 * This is the asonservice Exception class.
 * Creation date: (4/26/01 12:27:52 PM)
 * @author: David Brawley
 */
public class ASONServiceException extends ServiceException {
/**
 * Construct a asonserviceException object.
 * Creation date: (4/26/01 12:33:23 PM)
 * @param arg1 java.lang.String
 * @param arg2 java.lang.String 
 */
public ASONServiceException(String arg1, String arg2) {
	super(arg1, arg2);
}
/**
 * Construct a asonserviceException object.
 * Creation date: (4/26/01 12:33:23 PM)
 * @param arg1 java.lang.String
 * @param arg2 java.lang.String
 * @param arg3 java.lang.String
 */
public ASONServiceException(String arg1, String arg2, String arg3) {
	super(arg1, arg2, arg3);
}
}
