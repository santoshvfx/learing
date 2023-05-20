// $Id: InvalidInterfaceException.java,v 1.2 2003/03/12 00:10:58 dm2328 Exp $

package com.sbc.eia.bis.BusinessInterface;

/**
 * InvalidInterfaceException is the exception for an invalid interface.
 * Creation date: (2/21/01 10:05:07 AM)
 * @author: Creighton Malet
 */
public class InvalidInterfaceException extends BusinessException {
/**
 * Class constructor accepting exception code and message.
 * @param anExceptionCode java.lang.String
 * @param anErrorMessage java.lang.String
 */
public InvalidInterfaceException(String anExceptionCode, String anErrorMessage) {
	super(anExceptionCode, anErrorMessage);
}
}
