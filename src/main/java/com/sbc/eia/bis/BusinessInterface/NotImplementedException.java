// $Id: NotImplementedException.java,v 1.2 2003/03/12 00:10:58 dm2328 Exp $

package com.sbc.eia.bis.BusinessInterface;

/**
 * NotImplementedException is the exception for an interface that is not implemented.
 * Creation date: (2/21/01 10:06:33 AM)
 * @author: Creighton Malet
 */
public class NotImplementedException extends BusinessException {
/**
 * Class constructor accepting exception code and message.
 * @param anExceptionCode java.lang.String
 * @param anErrorMessage java.lang.String
 */
public NotImplementedException(String anExceptionCode, String anErrorMessage) {
	super(anExceptionCode, anErrorMessage);
}
}
