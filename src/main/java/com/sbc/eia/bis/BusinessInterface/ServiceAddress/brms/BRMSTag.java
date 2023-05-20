// $Id: BRMSTag.java,v 1.1 2007/07/17 22:53:56 jd3462 Exp $

package com.sbc.eia.bis.BusinessInterface.ServiceAddress.brms;

/**
 * A convenient place to keep all constants used in BRMS.
 * Creation date: (10/10/01 8:17:23 PM)
 * @author: David Brawley
 */
public interface BRMSTag {

	/**
	 *  Default BRMS values.
	 */
	public static final int NO_SIZE_LIMIT = 0;
	public static final String DEFAULT_MAX_ADDRESS_LIMIT = "100";
	public static final String SUPPLEMENTAL_ADDRESS_MSG  = "ADDR FOUND W & W/O LOC INFO";

	public static final String EMPTY_STRING = "";
	public static final String LATA_CODE = "920";
	public static final String ASTERISK = "*";
	public static final String SINGLE_QUOTE = "'";
	public static final String ZERO = "00000";
	public static final String CT = "CT";

	/**
	 *  LFACS values.
	 */
	public static final String FACSACCESS = "FACSAccess";
	public static final String FACSACCESS_SERVICE = "FACSAccessService";
	public static final String FACSACCESS_INQ_FASG_REQ = "FACSAccessInqFasgReq";
	public static final String WORKING_STATUS = "WKG";
	public static final String R_WORKING_STATUS = "RWKG";
	public static final String BLANK_ZIPCODE =
		"Zip Code is unknown. Contact the LECC/LSC for additional information";
    public static final String FUNCTION_TYPE = "INQ";
    public static final String TRANSACTION_TYPE = "FAS";
    public static final String LFACS_EMP = "DGATEL";
    public static final int LFACS_ST_NM_MAX = 50;
    public static final int LFACS_HSE_NBR_MAX = 13;

    /**
     *  Logging Parameters
     */
    public static final String SM_GET_SERVICE_LOCATION = "getServiceLocation";
}
