// $Id: openDates_st.java,v 1.1 2002/09/29 03:53:00 dm2328 Exp $

package com.sbc.gwsvcs.service.asonservice.interfaces;

import com.sbc.vicunalite.api.*;

final public class openDates_st implements java.io.Serializable { 
	public String weekDay1;
	public String mmDD1;
	public char filler1;
	public char amOrPmOrAll1;
	public char filler2;
	public String weekDay2;
	public String mmDD2;
	public char filler3;
	public char amOrPmOrAll2;
	public char filler4;

	public openDates_st () {
	}
	public openDates_st (String weekDay1, String mmDD1, char filler1, char amOrPmOrAll1, char filler2, String weekDay2, String mmDD2, char filler3, char amOrPmOrAll2, char filler4) { 
		this.weekDay1 = weekDay1;
		this.mmDD1 = mmDD1;
		this.filler1 = filler1;
		this.amOrPmOrAll1 = amOrPmOrAll1;
		this.filler2 = filler2;
		this.weekDay2 = weekDay2;
		this.mmDD2 = mmDD2;
		this.filler3 = filler3;
		this.amOrPmOrAll2 = amOrPmOrAll2;
		this.filler4 = filler4;

	}
}
