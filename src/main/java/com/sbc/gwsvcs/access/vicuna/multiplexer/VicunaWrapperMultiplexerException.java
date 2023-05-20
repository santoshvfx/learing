// $Id: VicunaWrapperMultiplexerException.java,v 1.3 2003/02/14 17:23:54 as5472 Exp $

package com.sbc.gwsvcs.access.vicuna.multiplexer;

/**
 * This class should be used to indicate an error has occured in the multiplexer.
 * Creation date: (7/3/01 2:39:54 PM)
 * @author: Creighton Malet
 */
public class VicunaWrapperMultiplexerException extends Exception {
	/**
	 * VicunaWrapperMultiplexerException constructor comment.
	 */
	public VicunaWrapperMultiplexerException() {
		super();
	}
	/**
	 * VicunaWrapperMultiplexerException constructor comment.
	 * @param s String
	 */
	public VicunaWrapperMultiplexerException(String s) {
		super(s);
	}
}
