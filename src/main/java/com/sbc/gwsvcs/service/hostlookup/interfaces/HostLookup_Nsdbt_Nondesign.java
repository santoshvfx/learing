package com.sbc.gwsvcs.service.hostlookup.interfaces;
import com.sbc.vicunalite.api.*;

final public class HostLookup_Nsdbt_Nondesign implements java.io.Serializable { 
	public String npa;
	public String pri_ims;
	public String sec_ims;
	public String conv_ind;
	public String conv_dt;
	public String temp1;
	public String temp2;

	public HostLookup_Nsdbt_Nondesign () {
	}
	public HostLookup_Nsdbt_Nondesign (String npa, String pri_ims, String sec_ims, String conv_ind, String conv_dt, String temp1, String temp2) { 
		this.npa = npa;
		this.pri_ims = pri_ims;
		this.sec_ims = sec_ims;
		this.conv_ind = conv_ind;
		this.conv_dt = conv_dt;
		this.temp1 = temp1;
		this.temp2 = temp2;

	} 
}
