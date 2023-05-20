//$Id: ASONTag.java,v 1.2 2007/08/10 22:52:05 jd3462 Exp $

package com.sbc.eia.bis.BusinessInterface.ServiceAddress.ason;

/**
 * A convenient place to keep all constants used in ASON Tandem DataGate Service..
 * Creation date: (7/17/07)
 * @author: Jean Duka
 */
public interface ASONTag 
{
	/**
	 *  ASON specific tag information values.
	 */
	public static final String SAG_VALID_REQ_LNGTH  = "139";	 
	public static final String SENDING_SYSTEM  = "LIMBIS";
	public static final String FUNCTION_KEY_DEPRESSED= "01";
	public static final String ID_GROUP        = "VRSAA";
	public static final String ID_TERMINAL     = "LIMBIS";
	public static final String ID_TYPIST       = "999";
	public static final String EMPTY_STRING    = "";
	public static final String DATE_KEY        = "";
	public static final String TIME_KEY        = "";
	public static final String HOLD_SAG_KEY    = "";
	public static final String SENT_END_STRING = "}};";
	public static final String END_OF_LIVING_UNIT = "**END OF LIVING UNIT";

	public static final short REQUEST_CODE_4441 = 4441;
	public static final short REQUEST_CODE_5210 = 5210;
	public static final short REQUEST_CODE_5215 = 5215;
	public static final short REQUEST_CODE_7300 = 7300;

	/**
	 *  Default ASON values.
	 */
	public static final int NO_SIZE_LIMIT = 0;
	public static final int SAG_VAL_ST_NM_MAX = 69;
	public static final int SAG_VAL_COM_MAX = 20;
	public static final int SAG_INQ_ST_NM_MAX = 20;
	public static final int SAG_DESC_ADDR_RMK_SIZE = 4;
	public static final String DEFAULT_MAX_ADDRESS_LIMIT = "99";
	public static final String DEFAULT_MAX_ZIP_LIMIT     = "10";
	public static final String DEFAULT_LOW_RANGE         = "0";
	public static final String DEFAULT_HIGH_RANGE        = "999999";
	public static final String SUPPLEMENTAL_ADDRESS_MSG  = "ADDR FOUND W & W/O LOC INFO";
	
	/**
	 *  Default ASON LFACS request.
	 */
	public static final String FACSACCESS = "FACSAccess";
	public static final String FACSACCESS_SERVICE = "FACSAccessService";
	public static final String FACSACCESS_INQ_FASG_REQ = "FACSAccessInqFasgReq";
	public static final String FUNCTION_TYPE = "INQ";
	public static final String ORIGIN = "LIMBIS";
	public static final String TRANSACTION_TYPE = "FAS";
	public static final String LFACS_EMP = "DGATEL";
	public static final int LFACS_ST_NM_MAX = 50;
    public static final int LFACS_HSE_NBR_MAX = 13;
    public static final String LFACS_LINE_WORKING = "WKG";
    public static final String LFACS_LINE_R_WORKING = "RWKG";

    /**
     *  Logging Parameters
     */
    public static final String ASON = "ASON";
    public static final String ASON_SERVICE = "AsonService";
    public static final String ASON_SAG_VALID_REQ = "ASONSagValidReq";
    public static final String ASON_SAG_INQ_REQ = "ASONSagInqReq";
    public static final String ASON_LVNG_UNIT_VAL_REQ = "ASONLvngUnitValReq";
    public static final String ASON_LVNG_UNIT_INQ_REQ = "ASONLvngUnitInqReq";
    public static final String SM_GET_SERVICE_LOCATION = "getServiceLocation";
    
    public static final String ASTERISK = "*";

}
