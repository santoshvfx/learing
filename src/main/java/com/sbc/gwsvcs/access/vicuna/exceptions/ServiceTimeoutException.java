// $Id: ServiceTimeoutException.java,v 1.1 2002/09/29 05:28:58 dm2328 Exp $

package com.sbc.gwsvcs.access.vicuna.exceptions;

/**
 * Defines a time out exception.
 * Creation date: (4/16/00 1:21:20 PM)
 * @author: Creighton Malet
 */
public class ServiceTimeoutException extends ServiceException {
/**
 * Constructor accepting code and original code.
 * @param arg1 java.lang.String
 * @param arg2 java.lang.Exception
 */
public ServiceTimeoutException(String arg1, String arg2) {
	super(arg1, arg2);
}
/**
 * Constructor accepting code, text and original code.
 * @param arg1 java.lang.String
 * @param arg2 java.lang.Exception
 */
public ServiceTimeoutException(String arg1, String arg2, String arg3) {
	super(arg1, arg2, arg3);
}
}
