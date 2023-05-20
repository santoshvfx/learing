package com.sbc.gwsvcs.service.facsaccess.exceptions;

/**
 * @author rk7964
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */

import com.sbc.gwsvcs.access.vicuna.exceptions.*;

public class FACSAccessException extends ServiceException {

public final static String GWS_ERR_FACSACCESS		 		  = "GWS-XXXXX";

	
/**
 * FACSAccessException constructor comment.
 * @param arg1 java.lang.String
 * @param arg2 java.lang.String
 */
public FACSAccessException(String arg1, String arg2) {
	super(arg1, arg2);
}
/**
 * FACSAccessException constructor comment.
 * @param arg1 java.lang.String
 * @param arg2 java.lang.String
 * @param arg3 java.lang.String
 */
public FACSAccessException(String arg1, String arg2, String arg3) {
	super(arg1, arg2, arg3);
}

}
