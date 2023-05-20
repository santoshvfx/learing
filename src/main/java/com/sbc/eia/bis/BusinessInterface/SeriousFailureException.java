// $Id: SeriousFailureException.java,v 1.2 2003/03/12 00:10:59 dm2328 Exp $

package com.sbc.eia.bis.BusinessInterface;

/**
 * SeriousFailureException is the exception for a serious failure.
 * Creation date: (2/21/01 10:07:16 AM)
 * @author: Creighton Malet
 */
public class SeriousFailureException extends BusinessException {
/**
 * Class constructor accepting exception code and message.
 * @param anExceptionCode java.lang.String
 * @param anErrorMessage java.lang.String
 */
public SeriousFailureException(String anExceptionCode, String anErrorMessage) {
	super(anExceptionCode, anErrorMessage);
}
}
