// $Id: InvalidStateException.java,v 1.2 2003/03/12 00:10:58 dm2328 Exp $

package com.sbc.eia.bis.BusinessInterface;

/**
 * InvalidStateException is the exception for an invalid state.
 * Creation date: (2/21/01 10:05:49 AM)
 * @author: Creighton Malet
 */
public class InvalidStateException extends BusinessException {
/**
 * Class constructor accepting exception code and message.
 * @param anExceptionCode java.lang.String
 * @param anErrorMessage java.lang.String
 */
public InvalidStateException(String anExceptionCode, String anErrorMessage) {
	super(anExceptionCode, anErrorMessage);
}
}
