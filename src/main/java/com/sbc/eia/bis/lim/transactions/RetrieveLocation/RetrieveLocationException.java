// $Id:$
package com.sbc.eia.bis.lim.transactions.RetrieveLocation;

/**
 * @author RZ7367
 *
 * RetrieveLocationException is derived from Exception class and is the exception thrown by 
 * RetrieveLocationHandler classes.
 * Creation date: (02/06/03 12:00:00 PM)
 */
public class RetrieveLocationException extends Exception {

	private java.lang.String message = null;
    /**
     * RetrieveLocationException constructor.
     */
    
    public RetrieveLocationException() {
    	super();
    }
    /**
     * RetrieveLocationException constructor.
     * @param s String
     */
    
    public RetrieveLocationException(String s) {
    	super(s);
    	message = s;
    }
    /**
     * returns the Exception Message.
     * @return String
     */
    
    public java.lang.String getMessage() {
    	return message;
    }
}

