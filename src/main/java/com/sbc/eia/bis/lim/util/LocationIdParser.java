// $Id: LocationIdParser.java,v 1.4 2008/02/29 22:49:22 gg2712 Exp $

package com.sbc.eia.bis.lim.util;

import java.util.NoSuchElementException;
import java.util.StringTokenizer;

/**
 * This class parses a location from a delimited string.
 * Creation date: (11/14/00 2:23:14 PM)
 * @author: David Prager
 */
public class LocationIdParser
{
	private boolean valid = false;
	private String identifier = "";
	private String subIdentifier = "";
	private String streetNbr = "";
	private String streetNbrSuffix = "";
	private String streetDirectional = "";
	private String streetName = "";
	private String streetThoroughfare = "";
	private String streetNameSuffix = "";
	private String postalCode = "";
	public final static String ADDRESS_DELIMITER = "#";
	private java.lang.String city = "";
	private java.lang.String state = "";
	public java.lang.String streetNbrPrefix = "";
/**
 * LocationIdParser constructor.
 * @param locationId  a String
 */
public LocationIdParser(String addr)
{
	StringTokenizer st = new StringTokenizer(addr, ADDRESS_DELIMITER, true); 
	try
	{
		valid = true;
		//to parse address data, we need to extract the delimiters, otherwise
		// the tokenizer will treat "###" as a single delimiter.
		String str = st.nextToken();
		if (!str.equals (ADDRESS_DELIMITER))
		{
			setStreetNbrPrefix (str); 
			st.nextToken();
		}
		
		str = st.nextToken();		
		if (!str.equals (ADDRESS_DELIMITER))
		{
			setStreetNbr (str); 
			st.nextToken();
		}
		
		str = st.nextToken();
		if (!str.equals (ADDRESS_DELIMITER))
		{
			setStreetNbrSuffix (str); 
			st.nextToken();
		}
		
		str = st.nextToken();
		if (!str.equals (ADDRESS_DELIMITER))
		{
			setStreetDirectional (str); 
			st.nextToken();
		}
		
		str = st.nextToken();
		if (!str.equals (ADDRESS_DELIMITER))
		{
			setStreetName (str); 
			st.nextToken();
		}
		
		str = st.nextToken();
		if (!str.equals (ADDRESS_DELIMITER))
		{
			setStreetThoroughfare (str); 
			st.nextToken();
		}
		
		str = st.nextToken();
		if (!str.equals (ADDRESS_DELIMITER))
		{
			setStreetNameSuffix (str); 
			st.nextToken();
		}
		
		str = st.nextToken();
		if (!str.equals (ADDRESS_DELIMITER))
		{
			setCity (str); 
			st.nextToken();
		}
		
		str = st.nextToken();
		if (!str.equals (ADDRESS_DELIMITER))
		{
			setState (str); 
			st.nextToken();
		}
		
		str = st.nextToken();
		if (!str.equals (ADDRESS_DELIMITER))
		{
			setPostalCode (str); 
			st.nextToken();
		}		
	}
	catch (NumberFormatException nfe) {}
	catch (NoSuchElementException nsee) {}
}
/**
 * Gets the city.
 * Creation date: (4/17/01 1:21:56 PM)
 * @return String
 * @see #setCity(String)
 */
public String getCity() {
	return city;
}
/**
 * Gets the postal code.
 * Creation date: (11/14/00 2:45:17 PM)
 * @return String
 * @see #setPostalCode(String)
 */
public java.lang.String getPostalCode() {
	return postalCode;
}
/**
 * Gets the state.
 * Creation date: (4/17/01 1:22:14 PM)
 * @return String
 * @see #setState(String)
 */
public String getState() {
	return state;
}
/**
 * Gets the street directional.
 * Creation date: (11/14/00 2:42:54 PM)
 * @return String
 * @see #setStreetDirectional(String)
 */
public java.lang.String getStreetDirectional() {
	return streetDirectional;
}
/**
 * Gets the street name.
 * Creation date: (11/14/00 2:43:23 PM)
 * @return String
 * @see #setStreetName(String)
 */
public java.lang.String getStreetName() {
	return streetName;
}
/**
 * Gets the street name suffix.
 * Creation date: (11/14/00 2:44:44 PM)
 * @return String
 * @see #setStreetNameSuffix(String)
 */
public java.lang.String getStreetNameSuffix() {
	return streetNameSuffix;
}
/**
 * Gets the street number.
 * Creation date: (11/14/00 2:40:38 PM)
 * @return String
 * @see #setStreetNbr(String)
 */
public java.lang.String getStreetNbr() {
	return streetNbr;
}
/**
 * Gets the street number prefix.
 * Creation date: (4/19/01 12:17:52 PM)
 * @return String
 * @see #setStreetNbrPrefix(String)
 */
public java.lang.String getStreetNbrPrefix() {
	return streetNbrPrefix;
}
/**
 * Gets the street number suffix.
 * Creation date: (11/14/00 2:42:20 PM)
 * @return String
 * @see #setStreetNbrSuffix(String)
 */
public java.lang.String getStreetNbrSuffix() {
	return streetNbrSuffix;
}
/**
 * Gets the street throughfare.
 * Creation date: (11/14/00 2:43:59 PM)
 * @return String
 * @see #setStreetThoroughfare(String)
 */
public java.lang.String getStreetThoroughfare() {
	return streetThoroughfare;
}
/**
 * Gets the valid indicator.
 * Creation date: (11/14/00 2:27:53 PM)
 * @return boolean
 */
public boolean isValid()
{
	return valid;
}
/**
 * Runs a test of the location id parser.
 * Creation date: (11/15/00 10:28:24 AM)
 * @param args  a String
 */
public static void main(String args[])
{
	LocationIdParser lip = new LocationIdParser("#618###30th#Ave###CA#94403");
	System.out.println("IsValid = " + lip.isValid());
	System.out.println("StNbr = " + lip.getStreetNbr());
	System.out.println("StNbrSuffix = " + lip.getStreetNbrSuffix());
	System.out.println("StDirectional = " + lip.getStreetDirectional());
	System.out.println("StName = " + lip.getStreetName());
	System.out.println("StThoroughfare = " + lip.getStreetThoroughfare());
	System.out.println("StNameSuffix = " + lip.getStreetNameSuffix());
	System.out.println("PostalCode = " + lip.getPostalCode());
}

/**
 * Sets the city.
 * Creation date: (4/17/01 1:23:38 PM)
 * @param newCity  a String specifying the city value
 * @see #getCity
 */
public void setCity(String newCity){
	city = newCity;	
	}
/**
 * Sets the postal code. 
 * Creation date: (11/14/00 2:45:17 PM)
 * @param newPostalCode String
 * @see #getPostalCode
 */
void setPostalCode(java.lang.String newPostalCode) {
	postalCode = newPostalCode;
}
/**
 * Sets the state.
 * Creation date: (4/17/01 1:25:09 PM)
 * @param newState String
 * @see #getState
 */
public void setState(String newState) {
	state = newState;
}
/**
 * Sets the street directional.
 * Creation date: (11/14/00 2:42:54 PM)
 * @param newStreetDirectional String
 * @see #getStreetDirectional
 */
void setStreetDirectional(java.lang.String newStreetDirectional) {
	streetDirectional = newStreetDirectional;
}
/**
 * Sets the street name.
 * Creation date: (11/14/00 2:43:23 PM)
 * @param newStreetName String
 * @see #getStreetName
 */
void setStreetName(java.lang.String newStreetName) {
	streetName = newStreetName;
}
/**
 * Sets the street name suffix.
 * Creation date: (11/14/00 2:44:44 PM)
 * @param newStreetNameSuffix String
 * @see #getStreetNameSuffix
 */
void setStreetNameSuffix(java.lang.String newStreetNameSuffix) {
	streetNameSuffix = newStreetNameSuffix;
}
/**
 * Sets the street number.
 * Creation date: (11/14/00 2:40:38 PM)
 * @param newStreetNbr String
 * @see #getStreetNbr
 */
void setStreetNbr(java.lang.String newStreetNbr) {
	streetNbr = newStreetNbr;
}
/**
 * Sets the street number prefix.
 * Creation date: (4/19/01 12:17:52 PM)
 * @param newStreetNbrPrefix String
 * @see #getStreetNbrPrefix
 */
public void setStreetNbrPrefix(java.lang.String newStreetNbrPrefix) {
	streetNbrPrefix = newStreetNbrPrefix;
}
/**
 * Sets the street number suffix.
 * Creation date: (11/14/00 2:42:20 PM)
 * @param newStreetNbrSuffix String
 * @see #getStreetNbrSuffix
 */
void setStreetNbrSuffix(java.lang.String newStreetNbrSuffix) {
	streetNbrSuffix = newStreetNbrSuffix;
}
/**
 * Sets the street thoroughfare.
 * Creation date: (11/14/00 2:43:59 PM)
 * @param newStreetThoroughfare String
 * @see #getStreetThoroughfare
 */
void setStreetThoroughfare(java.lang.String newStreetThoroughfare) {
	streetThoroughfare = newStreetThoroughfare;
}

}