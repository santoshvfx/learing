package com.sbc.gwsvcs.service.hostlookup.interfaces;
import com.sbc.vicunalite.api.*;

final public class HostLookup_Error implements java.io.Serializable { 
	public String tn;
	public String ErrorMsg;
	public int OrigEvent;

	public HostLookup_Error () {
	}
	public HostLookup_Error (String tn, String ErrorMsg, int OrigEvent) { 
		this.tn = tn;
		this.ErrorMsg = ErrorMsg;
		this.OrigEvent = OrigEvent;

	} 
}
