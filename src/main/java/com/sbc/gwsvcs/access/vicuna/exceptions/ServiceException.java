// $Id: ServiceException.java,v 1.1 2002/09/29 05:28:58 dm2328 Exp $

package com.sbc.gwsvcs.access.vicuna.exceptions;

/**
 * Provides a base exception type.
 * Creation date: (4/17/00 4:14:51 PM)
 * @author: Creighton Malet
 */
public class ServiceException extends com.sbc.vicunalite.api.MGCException {
	private String exceptionCode;
	private String originalExceptionCode;
/**
 * Constructor accepting code and original code.
 * @param arg1 java.lang.String
 * @param arg2 java.lang.String
 */
public ServiceException(String arg1, String arg2) {
	super(arg2);
	exceptionCode = arg1;
	originalExceptionCode = "";
}
/**
 * Constructor accepting code, text and an exception.
 * @param arg1 java.lang.String
 * @param arg2 java.lang.String
 * @param arg3 java.lang.Exception
 */
public ServiceException(String arg1, String arg2, Exception arg3) {
	super(arg2, arg3);
	exceptionCode = arg1;
	originalExceptionCode = "";
}
/**
 * Constructor accepting code, text and original code.
 * @param arg1 java.lang.String
 * @param arg2 java.lang.String
 * @param arg3 java.lang.String
 */
public ServiceException(String arg1, String arg2, String arg3) {
	super(arg2);
	exceptionCode = arg1;
	originalExceptionCode = arg3;
}
/**
 * Constructor accepting code, text, original code and an exception.
 * @param arg1 java.lang.String
 * @param arg2 java.lang.String
 * @param arg3 java.lang.String
 * @param arg4 java.lang.Exception
 */
public ServiceException(String arg1, String arg2, String arg3, Exception arg4) {
	super(arg2, arg4);
	exceptionCode = arg1;
	originalExceptionCode = arg3;
}
/**
 * Returns the exception code.
 * Creation date: (5/12/00 9:15:15 AM)
 * @return java.lang.String
 */
public java.lang.String getExceptionCode() {
	return exceptionCode;
}
/**
 * Returns the original exception code.
 * Creation date: (3/5/01 3:33:06 PM)
 * @return java.lang.String
 */
public java.lang.String getOriginalExceptionCode() {
	return originalExceptionCode;
}
}
