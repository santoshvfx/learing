// $Id: State.java,v 1.2 2003/03/12 00:10:59 dm2328 Exp $

package com.sbc.eia.bis.BusinessInterface;

import java.util.Hashtable;
import com.sbc.eia.idl.exception_types.*;

/**
 * State is the class which defines a state.
 * Creation date: (12/18/00 1:45:22 PM)
 * @author: Creighton Malet
 */
public class State {
	private java.lang.String Code = null;
	private final static java.lang.String ANY_STATE = "_A";
	private static final Hashtable states =	loadStates();

	// Statics for states
	public static final String State_Alabama = "AL";
	public static final String State_Alaska = "AK";
	public static final String State_Arizona = "AZ";
	public static final String State_Arkansas = "AR";
	public static final String State_California = "CA";
	public static final String State_Colorado = "CO";
	public static final String State_Connecticut = "CT";
	public static final String State_Delaware = "DE";
	public static final String State_DistrictOfColumbia = "DC";
	public static final String State_Florida = "FL";
	public static final String State_Georgia = "GA";
	public static final String State_Guam = "GU";
	public static final String State_Hawaii = "HI";
	public static final String State_Idaho = "ID";
	public static final String State_Illinois = "IL";
	public static final String State_Indiana = "IN";
	public static final String State_Iowa = "IA";
	public static final String State_Kansas = "KS";
	public static final String State_Kentucky = "KY";
	public static final String State_Louisiana = "LA";
	public static final String State_Maine = "ME";
	public static final String State_Maryland = "MD";
	public static final String State_Massachusetts = "MA";
	public static final String State_Michigan = "MI";
	public static final String State_Minnesota = "MN";
	public static final String State_Mississippi = "MS";
	public static final String State_Missouri = "MO";
	public static final String State_Montana = "MT";
	public static final String State_Nebraska = "NE";
	public static final String State_Nevada = "NV";
	public static final String State_NewHampshire = "NH";
	public static final String State_NewJersey = "NJ";
	public static final String State_NewMexico = "NM";
	public static final String State_NewYork = "NY";
	public static final String State_NorthCarolina = "NC";
	public static final String State_NorthDakota = "ND";
	public static final String State_Ohio = "OH";
	public static final String State_Oklahoma = "OK";
	public static final String State_Oregon = "OR";
	public static final String State_Pennsylvania = "PA";
	public static final String State_PuertoRico = "PR";
	public static final String State_RhodeIsland = "RI";
	public static final String State_SouthCarolina = "SC";
	public static final String State_SouthDakota = "SD";
	public static final String State_Tennessee = "TN";
	public static final String State_Texas = "TX";
	public static final String State_Utah = "UT";
	public static final String State_Vermont = "VT";
	public static final String State_VirginIslands = "VI";
	public static final String State_Virginia = "VA";
	public static final String State_Washington = "WA";
	public static final String State_WestVirginia = "WV";
	public static final String State_Wisconsin = "WI";
	public static final String State_Wyoming = "WY";
/**
 * Class constructor.
 */
private State() {}
/**
 * Class constructor accepting state code.
 * Creation date: (12/18/00 1:46:21 PM)
 * @param aState java.lang.String
 */
public State(String aState)
	throws NullDataException, InvalidStateException
{
	if (aState == null)
		throw new NullDataException(ExceptionCode.ERR_BSI_BUSINESS_NULL_DATA, "Null State in State::State()");
		
	if (! states.containsKey(aState.toUpperCase()))
		throw new InvalidStateException(ExceptionCode.ERR_BSI_BUSINESS_INVALID_STATE,
			"Invalid State: " + aState);
	Code = aState.toUpperCase();
}
/**
 * Does a logical comparison between two states.
 * Creation date: (12/19/00 3:11:02 PM)
 * @return boolean
 * @param aState com.sbc.eia.bis.BusinessInterface.State
 */
public boolean equals(State aState) {
	return this.Code.equals(aState.Code) ? true : false;
}
/**
 * Does a logical comparison between two states.
 * Creation date: (12/19/00 3:11:02 PM)
 * @return boolean
 * @param aCode java.lang.String
 */
public boolean equals(String aCode) {
	return this.Code.equals(aCode.toUpperCase()) ? true : false;
}
/**
 * Returns a state representing any state.
 * Creation date: (12/19/00 2:59:52 PM)
 * @return com.sbc.eia.bis.BusinessInterface.State
 */
public static State getAnAnyState() {
	State aState = new State();
	aState.Code = ANY_STATE;
	return aState;
}
/**
 * Returns the code.
 * Creation date: (12/18/00 1:47:06 PM)
 * @return java.lang.String
 */
public java.lang.String getCode() {
	return Code;
}
/**
 * Determines whether a state represents any state.
 * Creation date: (12/19/00 3:11:02 PM)
 * @return boolean
 */
public boolean isAnyState() {
	return Code.equals(ANY_STATE) ? true : false;
}
/**
 * Initialises the state set.
 * Creation date: (1/19/01 9:08:54 AM)
 */
private static Hashtable loadStates() {
	Hashtable theStates = new Hashtable();
	
	theStates.put("AL", "Alabama");
	theStates.put("AK", "Alaska");
	theStates.put("AZ", "Arizona");
	theStates.put("AR", "Arkansas");
	theStates.put("CA", "California");
	theStates.put("CO", "Colorado");
	theStates.put("CT", "Connecticut");
	theStates.put("DE", "Delaware");
	theStates.put("DC", "District of Columbia");
	theStates.put("FL", "Florida");
	theStates.put("GA", "Georgia");
	theStates.put("GU", "Guam");
	theStates.put("HI", "Hawaii");
	theStates.put("ID", "Idaho");
	theStates.put("IL", "Illinois");
	theStates.put("IN", "Indiana");
	theStates.put("IA", "Iowa");
	theStates.put("KS", "Kansas");
	theStates.put("KY", "Kentucky");
	theStates.put("LA", "Louisiana");
	theStates.put("ME", "Maine");
	theStates.put("MD", "Maryland");
	theStates.put("MA", "Massachusetts");
	theStates.put("MI", "Michigan");
	theStates.put("MN", "Minnesota");
	theStates.put("MS", "Mississippi");
	theStates.put("MO", "Missouri");
	theStates.put("MT", "Montana");
	theStates.put("NE", "Nebraska");
	theStates.put("NV", "Nevada");
	theStates.put("NH", "New Hampshire");
	theStates.put("NJ", "New Jersey");
	theStates.put("NM", "New Mexico");
	theStates.put("NY", "New York");
	theStates.put("NC", "North Carolina");
	theStates.put("ND", "North Dakota");
	theStates.put("OH", "Ohio");
	theStates.put("OK", "Oklahoma");
	theStates.put("OR", "Oregon");
	theStates.put("PA", "Pennsylvania");
	theStates.put("PR", "Puerto Rico");
	theStates.put("RI", "Rhode Island");
	theStates.put("SC", "South Carolina");
	theStates.put("SD", "South Dakota");
	theStates.put("TN", "Tennessee");
	theStates.put("TX", "Texas");
	theStates.put("UT", "Utah");
	theStates.put("VT", "Vermont");
	theStates.put("VI", "Virgin Islands");
	theStates.put("VA", "Virginia");
	theStates.put("WA", "Washington");
	theStates.put("WV", "West Virginia");
	theStates.put("WI", "Wisconsin");
	theStates.put("WY", "Wyoming");


	return theStates;
}
}
