// $Id: NullDataException.java,v 1.2 2003/03/12 00:10:58 dm2328 Exp $

package com.sbc.eia.bis.BusinessInterface;

/**
 * NullDataException is the exception for null data.
 * Creation date: (2/21/01 10:06:33 AM)
 * @author: Creighton Malet
 */
public class NullDataException extends BusinessException {
/**
 * Class constructor accepting exception code and message.
 * @param anExceptionCode java.lang.String
 * @param anErrorMessage java.lang.String
 */
public NullDataException(String anExceptionCode, String anErrorMessage) {
	super(anExceptionCode, anErrorMessage);
}
}
