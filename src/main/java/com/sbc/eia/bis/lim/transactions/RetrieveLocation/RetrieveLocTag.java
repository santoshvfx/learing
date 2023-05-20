// $Id:$
package com.sbc.eia.bis.lim.transactions.RetrieveLocation;

/**
 * @author Amanda Surya (as2362)
 *
 * Creation Date: 10/28/2002
 * A convenient place to keep all constants used in RetrieveLocation Service.
 * 
 */
public interface RetrieveLocTag {
	/**
	 *  Default RetrieveLocation values.
	 */
	public static final String HOSTLOOKUP = "HOSTLOOKUP";
	public static final String HOSTLOOKUP_HELPER = "HOSTLOOKUPHelper";
	
	// Methods
	public static final String GET_HOST_LOOKUP_RECORD = "RetrieveLocationBase::getHostLookupRecord()";
	public static final String HL_LOOKUP_FULL = "HOSTLOOKUPHelper::hlLookupFull()";

    public static final String FIXED_LENGTH_DATA_START_TAG = "<data>";
    public static final String FIXED_LENGTH_CDATA_START_TAG = "<![CDATA[";
    public static final String FIXED_LENGTH_CDATA_END_TAG = "]]>";
    public static final String FIXED_LENGTH_DATA_END_TAG = "</data>";
  
    /**
     * Fixed Length Proxy Output Values
     */
    public static final int DATA_INDEX                      = 0;
    public static final int CDATA_INDEX                     = 6;
    public static final int APPLICATION_INDEX               = 15;
    public static final int CUSTOMER_NAME_INDEX             = 45;
    public static final int SERVICE_CTR_INDEX               = 75;
    public static final int BUS_UNIT_INDEX                  = 80;
    public static final int ADD_MATCH_COUNT_INDEX           = 110;
    public static final int CASS_ADD_LINE1_INDEX            = 112;
    public static final int CASS_ADD_LINE2_INDEX            = 142;
    public static final int CASS_ADD_LINE3_INDEX            = 172;
    public static final int CASS_ADD_LINE4_INDEX            = 202;
    public static final int CASS_ADD_LINE5_INDEX            = 232;
    public static final int AUX_ADD_LINE1_INDEX             = 262;
    public static final int AUX_ADD_LINE2_INDEX             = 292;
    public static final int AUX_ADD_LINE3_INDEX             = 322;
    public static final int AUX_ADD_LINE4_INDEX             = 352;
    public static final int AUX_ADD_LINE5_INDEX             = 382;
    public static final int AMQ_CODE_INDEX                  = 412;
    public static final int AMQ_DESC_INDEX                  = 417;
    public static final int ERR_CODE_INDEX                  = 517;
    public static final int ERR_DESC_INDEX                  = 547;
    public static final int END_CDATA_INDEX                 = 647;
    public static final int END_DATA_INDEX                  = 650;
    public static final int END_BODY_INDEX                  = 1152;
    public static final int TOTAL_MSG_LENGTH                = 657;

	/**
	 * Method name to be used for checkAuthorization
	 *
	 */
	public static final String METHOD_RETRIEVE_LOCATION_FOR_ADDR  = "retrieveLocationForAddress" ;
	public static final String METHOD_RETRIEVE_LOCATION_FOR_SVC  = "retrieveLocationForService" ;
	
}

