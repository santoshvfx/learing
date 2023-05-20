// $Id: InvalidCompanyException.java,v 1.2 2003/03/12 00:10:58 dm2328 Exp $

package com.sbc.eia.bis.BusinessInterface;

/**
 * InvalidCompanyException is the exception for an invalid company.
 * Creation date: (2/21/01 10:03:40 AM)
 * @author: Creighton Malet
 */
public class InvalidCompanyException extends BusinessException {
/**
 * Class constructor accepting exception code and message.
 * @param anExceptionCode java.lang.String
 * @param anErrorMessage java.lang.String
 */
public InvalidCompanyException(String anExceptionCode, String anErrorMessage) {
	super(anExceptionCode, anErrorMessage);
}
}
