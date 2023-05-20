// $Id: EventObjectPair.java,v 1.1 2002/09/29 05:28:58 dm2328 Exp $

package com.sbc.gwsvcs.access.vicuna;

/**
 * Provides a holder for an object and event (Vicuna and Datagate).
 * Creation date: (4/16/00 1:00:04 PM)
 * @author: Creighton Malet
 */
public class EventObjectPair {
	public com.sbc.vicunalite.api.MEventType event;
	public java.lang.Object anObject;
	public int eventNbr;
/**
 * Class constructor.
 */
public EventObjectPair() {
	anObject = null;
	event = null;
	eventNbr = 0;
}
/**
 * Class constructor accepting Vicuna event, object and Datagate event.
 * Creation date: (4/16/00 1:04:00 PM)
 * @param event com.sbc.vicunalite.api.MEventType
 * @param anObject java.lang.Object
 * @param eventNbr int
 */
public EventObjectPair(com.sbc.vicunalite.api.MEventType event, Object anObject, int eventNbr)
{
	this.event = event;
	this.anObject = anObject;
	this.eventNbr = eventNbr;
}
}
