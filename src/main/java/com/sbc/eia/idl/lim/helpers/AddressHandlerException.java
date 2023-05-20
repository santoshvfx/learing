// $Id: AddressHandlerException.java,v 1.1 2002/09/29 02:53:32 dm2328 Exp $

package com.sbc.eia.idl.lim.helpers;

/**
 * AddressHandlerException is derived from Exception class and is the exception thrown by all
 * the AddressHandler's classes.
 * Creation date: (5/3/01 12:09:01 PM)
 * @author: Rachel Zadok - Local
 */

public class AddressHandlerException extends Exception {
	private java.lang.String message = null;
/**
 * AddressHandlerException constructor.
 */

public AddressHandlerException() {
	super();
}
/**
 * AddressHandlerException constructor.
 * Creation date: (5/3/01 12:20:56 PM)
 * @param s String
 */

public AddressHandlerException(String s) {
	super(s);
	message = s;
}
/**
 * returns the Exception Message.
 * Creation date: (5/3/01 12:20:56 PM)
 * @return String
 */

public java.lang.String getMessage() {
	return message;
}
}
