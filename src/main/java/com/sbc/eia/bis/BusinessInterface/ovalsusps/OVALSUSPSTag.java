// $Id: OVALSUSPSTag.java,v 1.2 2003/03/21 19:24:32 dm2328 Exp $

package com.sbc.eia.bis.BusinessInterface.ovalsusps;

/**
 * A convenient place to keep all constants used in OVALSUSPS Tandem DataGate Service..
 * Creation date: (10/10/01 8:17:23 PM)
 * @author: Donald W. Lee
 */
public interface OVALSUSPSTag {

	/**
	 *  OVALSUSPS specific tag information values.
	 */
	public static final String XML_PROLOG          = "<?xml version=\"1.0\" ?> ";
	public static final String XML_OPEN_REQ        = "<OVALS><USPS-VALIDATE><CONTROL/><REQUEST>";	 
	public static final String XML_ADDRESS         = "<ADDRESS LINE1=\"\" LINE2=\"\" LINE3=\"\" LINE4=\"\" LINE5=\"\" ID=\"\"";
	public static final String XML_ADRLL   		 = " ADRLL=\"\"";
	public static final String XML_CLOSING_BRACKET = " />";
	public static final String XML_CLOSE_REQ       = "</REQUEST><ERROR/></USPS-VALIDATE></OVALS>";	 
	public static final String OVALS_JAXB_PACKAGE  = "com.sbc.eia.bis.lim.jaxb.ovalsusps";

	/**
	 *  OVALSUSPS LocationRegistraion tag information values.
	 */
	public static final String USPS_ERR_CD_DEFAULT = "E999";
	public static final String CLIENT_EWBAV = "EWBAV";
	public static final String OVALSUSPS_CASS_ADDITIONAL_ADDRESS = "OVALSUSPS_CASS_ADDITIONAL_ADDRESS";
		
	/**
     *  Logging Parameters
     */
    public static final String OVALSUSPS = "OVALSUSPS";
    public static final String OVALSUSPS_MQ_BROKER = "OvalsUspsMQBroker";
    public static final String OVALSUSPS_MQ_SERVICE = "OvalsUspsMQService";;
    public static final String ADDRESS_SERVICE = "AddressService";
	/**
	 *  OVALSUSPS Misc tag values.
	 */
	public static final String EMPTY_STRING    = "";
	
	/**
     * Statics for Company
     */
	public static final String Company_USPostalService = "USPS";
}
