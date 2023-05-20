package com.sbc.gwsvcs.service.hostlookup.interfaces;
import com.sbc.vicunalite.api.*;

final public class HostLookup_R implements java.io.Serializable { 
	public String wc;
	public String facs;
	public String lmos;
	public String cosmos;
	public String premis;
	public String saga;
	public String sord;
	public String tirks;
	public String nsdb;
	public String boss;
	public String march;
	public String swtch;
	public String swtch_entity;
	public String wfado;
	public String wc_alpha;
	public String div_code;
	public String pics;
	public String pacbell_mi;
	public String split;
	public String location;
	public String rfs_alpha;
	public int OrigEvent;
	public String ErrorMsg;

	public HostLookup_R () {
	}
	public HostLookup_R (String wc, String facs, String lmos, String cosmos, String premis, String saga, String sord, String tirks, String nsdb, String boss, String march, String swtch, String swtch_entity, String wfado, String wc_alpha, String div_code, String pics, String pacbell_mi, String split, String location, String rfs_alpha, int OrigEvent, String ErrorMsg) { 
		this.wc = wc;
		this.facs = facs;
		this.lmos = lmos;
		this.cosmos = cosmos;
		this.premis = premis;
		this.saga = saga;
		this.sord = sord;
		this.tirks = tirks;
		this.nsdb = nsdb;
		this.boss = boss;
		this.march = march;
		this.swtch = swtch;
		this.swtch_entity = swtch_entity;
		this.wfado = wfado;
		this.wc_alpha = wc_alpha;
		this.div_code = div_code;
		this.pics = pics;
		this.pacbell_mi = pacbell_mi;
		this.split = split;
		this.location = location;
		this.rfs_alpha = rfs_alpha;
		this.OrigEvent = OrigEvent;
		this.ErrorMsg = ErrorMsg;

	} 
}
