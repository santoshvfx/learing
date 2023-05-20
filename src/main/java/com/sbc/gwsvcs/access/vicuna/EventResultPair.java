// $Id: EventResultPair.java,v 1.2 2002/09/29 05:32:06 dm2328 Exp $

package com.sbc.gwsvcs.access.vicuna;

/**
 * Provides a holder for an object and Datagate event.
 * Creation date: (4/16/00 1:00:04 PM)
 * @author: Creighton Malet
 */
public class EventResultPair implements java.io.Serializable  {
	private java.lang.Object theObject;
	private int eventNbr;
/**
 * Class constructor.
 */
public EventResultPair() {
	theObject = null;
	eventNbr = 0;
}
/**
 * Class constructor accepting object and Datagate event.
 * Creation date: (4/16/00 1:04:00 PM)
 * @param theObject java.lang.Object
 * @param eventNbr int
 */
public EventResultPair(Object theObject, int eventNbr)
{
	this.theObject = theObject;
	this.eventNbr = eventNbr;
}
/**
 * Returns the event number.
 * Creation date: (2/27/01 8:28:33 AM)
 * @return int
 */
public int getEventNbr() {
	return eventNbr;
}
/**
 * Return the object.
 * Creation date: (2/27/01 8:28:33 AM)
 * @return java.lang.Object
 */
public java.lang.Object getTheObject() {
	return theObject;
}
}
