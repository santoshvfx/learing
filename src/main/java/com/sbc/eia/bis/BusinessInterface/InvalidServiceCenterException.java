// $Id: InvalidServiceCenterException.java,v 1.2 2003/03/12 00:10:58 dm2328 Exp $

package com.sbc.eia.bis.BusinessInterface;

/**
 * InvalidServiceCenterException is the exception for an invalid service center.
 * Creation date: (3/27/01 8:51:23 AM)
 * @author: Creighton Malet
 */
public class InvalidServiceCenterException extends BusinessException {
/**
 * Class constructor accepting exception code and message.
 * @param anExceptionCode java.lang.String
 * @param anErrorMessage java.lang.String
 */
public InvalidServiceCenterException(String anExceptionCode, String anErrorMessage) {
	super(anExceptionCode, anErrorMessage);
}
}
