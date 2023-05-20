// $Id: BusinessException.java,v 1.2 2003/03/12 00:10:58 dm2328 Exp $

package com.sbc.eia.bis.BusinessInterface;

/**
 * BusinessException is the base class for business interface exceptions.
 * Creation date: (3/13/01 2:39:06 PM)
 * @author: Creighton Malet
 */
public class BusinessException extends Exception {
	private String exceptionCode = null;
/**
 * Class constructor accepting exception code and message.
 * Creation date: (3/13/01 5:52:00 PM)
 * @param aNexceptionCode java.lang.String
 * @param aNerrorMessage java.lang.String
 */
public BusinessException(String aNexceptionCode, String aNerrorMessage) {
	super(aNerrorMessage);
	exceptionCode = aNexceptionCode;
}
/**
 * Returns the exception code.
 * @return java.lang.String
 */
public java.lang.String getExceptionCode() {
	return exceptionCode;
}
}
