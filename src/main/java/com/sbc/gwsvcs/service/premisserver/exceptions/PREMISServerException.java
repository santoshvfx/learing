// $Id: PREMISServerException.java,v 1.1 2002/09/29 04:28:09 dm2328 Exp $

package com.sbc.gwsvcs.service.premisserver.exceptions;

import com.sbc.gwsvcs.access.vicuna.exceptions.*;

/**
 * Base class for PREMISServer exceptions.
 * Creation date: (4/16/00 12:27:52 PM)
 * @author: Creighton Malet
 */
public class PREMISServerException extends ServiceException {
/**
 * Constructor accepting code and text.
 * @param arg1 java.lang.String
 * @param arg2 java.lang.String
 */
public PREMISServerException(String arg1, String arg2) {
	super(arg1, arg2);
}
/**
 * Constructor accepting code, text and original code.
 * @param arg1 java.lang.String
 * @param arg2 java.lang.String
 * @param arg3 java.lang.String
 */
public PREMISServerException(String arg1, String arg2, String arg3) {
	super(arg1, arg2, arg3);
}
}
