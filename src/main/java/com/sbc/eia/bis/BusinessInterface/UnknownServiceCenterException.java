// $Id: UnknownServiceCenterException.java,v 1.2 2003/03/12 00:10:59 dm2328 Exp $

package com.sbc.eia.bis.BusinessInterface;

/**
 * UnknownServiceCenterException is the exception for an unknown service center.
 * Creation date: (3/27/01 8:51:44 AM)
 * @author: Creighton Malet
 */
public class UnknownServiceCenterException extends BusinessException {
/**
 * Class constructor accepting exception code and message.
 * @param anExceptionCode java.lang.String
 * @param anErrorMessage java.lang.String
 */
public UnknownServiceCenterException(String anExceptionCode, String anErrorMessage) {
	super(anExceptionCode, anErrorMessage);
}
}
