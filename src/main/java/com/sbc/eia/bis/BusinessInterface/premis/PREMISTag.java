// $Id: PREMISTag.java,v 1.4 2008/02/29 23:27:20 jd3462 Exp $

package com.sbc.eia.bis.BusinessInterface.premis;

/**
 * PREMISTag class
 * Creation date: (4/6/02 10:36:55 AM)
 * @author: Donald W. Lee
 */
public interface PREMISTag {

	/**
	 *  Default PREMIS values.
	 */
	public static final int HSE_NBR_PRFX_MAX = 5;
	public static final int HSE_NBR_MAX = 8;
	public static final int ST_NM_MAX = 50;
	public static final int COM_MAX = 32;
    public static final int PREMIS_DEFAULT_MAX_HELPERS = 5;
    public static final int PREMIS_DEFAULT_TIMEOUT = 15;
    
    public static final double PREMIS_DEFAULT_SLICE_TIMEOUT = 0.1;   
    
	public static final String EMPTY_STRING = "";
	public static final String YES = "Y";
	public static final String NO = "N";
	public static final String TRUE	= "true";
	public static final String FALSE = "false";
	public static final String NON_SBC = "NON-SBC territory: ";
	public static final String LINE_WORKING = "WORKING";
	public static final String OWNED_WIRING	= "OWNED WIRING";
	public static final String THREE_DOLLAR_SIGNS = "$$$";
	public static final String THREE_ASTERICKS = "***";
	public static final String SUPPLEMENTAL_ADDRESS_MSG  = "ADDR FOUND W & W/O LOC INFO";
	public static final String SAG_INFO_ONLY_MSG  = "SAG INFORMATION ONLY";
		
	public static final String NAME = "PREMIS";
	public static final String SERVER = "PREMISServer";
	public static final String PREMIS_VALDT_ADDR_REQ = "PREMISServerHelper::premisValdtAddrReq()";
	public static final String PREMIS_RETRIEVE_LOC_REQ = "PremisRetrieveLocReq";
}
