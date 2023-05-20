// $Id: UnknownStateException.java,v 1.2 2003/03/12 00:10:59 dm2328 Exp $

package com.sbc.eia.bis.BusinessInterface;

/**
 * UnknownStateException is the exception for an unknown state.
 * Creation date: (2/21/01 10:07:55 AM)
 * @author: Creighton Malet
 */
public class UnknownStateException extends BusinessException {
/**
 * Class constructor accepting exception code and message.
 * @param anExceptionCode java.lang.String
 * @param anErrorMessage java.lang.String
 */
public UnknownStateException(String anExceptionCode, String anErrorMessage) {
	super(anExceptionCode, anErrorMessage);
}
}
