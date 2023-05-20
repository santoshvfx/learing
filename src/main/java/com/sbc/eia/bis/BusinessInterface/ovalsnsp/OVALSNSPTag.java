//$Id: OVALSNSPTag.java,v 1.8 2008/02/29 23:27:52 jh9785 Exp $

package com.sbc.eia.bis.BusinessInterface.ovalsnsp;

/**
 * @author gg2712
 */
public interface OVALSNSPTag
{
	public static final String OVALS_NSP = "OVALS_NSP";
	public static final String OVALS_NSP_MSAG_VALIDATION_TAG = "validateMsagAddress";
	public static final String OVALS_NSP_MATCH_CODE = "100";
	public static final String OVALS_NSP_NO_MATCH_CODE = "";
	public static final String OVALS_NSP_INVALID_DATA_ERROR_CODE = "-999";

	/**
     *  Logging Parameters
     */
    public static final String OVALS_NSP_MQ_BROKER = "OvalsNspMQBroker";
    public static final String OVALS_NSP_MQ_SERVICE = "OvalsNspMQService";
    
    /**
     * Statics for Company
     */
    public static final String Company_OvalsNSP = "OVALS NSP";
}
