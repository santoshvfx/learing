package com.sbc.gwsvcs.service.hostlookup.interfaces;
import com.sbc.vicunalite.api.*;

final public class MobileCTNLookup_R implements java.io.Serializable { 
	public String tn;
	public String property;
	public int OrigEvent;

	public MobileCTNLookup_R () {
	}
	public MobileCTNLookup_R (String tn, String property, int OrigEvent) { 
		this.tn = tn;
		this.property = property;
		this.OrigEvent = OrigEvent;

	} 
}
