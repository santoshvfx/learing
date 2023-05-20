// $Id: EventClassPair.java,v 1.1 2002/09/29 05:28:58 dm2328 Exp $

package com.sbc.gwsvcs.access.vicuna;

/**
 * Provides a holder for a class and event (Vicuna and Datagate).
 * Creation date: (4/16/00 12:58:04 PM)
 * @author: Creighton Malet
 */
public class EventClassPair {
	public com.sbc.vicunalite.api.MEventType event;
	public java.lang.Class aClass;
	public int eventNbr;
/**
 * Class constructor.
 */
public EventClassPair() {
	aClass = null;
	event = null;
	eventNbr = 0;
}
/**
 * Class constructor accepting Vicuna event, class and Datagate event.
 * Creation date: (4/16/00 1:02:46 PM)
 * @param event com.sbc.vicunalite.api.MEventType
 * @param aClass java.lang.Class
 * @param eventNbr int
 */
public EventClassPair(com.sbc.vicunalite.api.MEventType event, Class aClass, int eventNbr)
{
	this.event = event;
	this.aClass = aClass;
	this.eventNbr = eventNbr;
}
}
