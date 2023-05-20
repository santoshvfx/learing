//$Id: OvalsNspResponseHelper.java,v 1.4 2008/02/29 23:26:22 jh9785 Exp $

package com.sbc.eia.bis.BusinessInterface.ovalsnsp;

import com.sbc.eia.bis.lim.jaxb.ovalsnsp.OVALSNSPType;
import com.sbc.eia.bis.lim.jaxb.ovalsnsp.impl.OVALSNSPTypeImpl;

/**
 * @author gg2712
 * This class encapsulates a JAXB response object from OVALS NSP.
 */
public class OvalsNspResponseHelper 
{
	private OVALSNSPType OvalsNspMsagValidationResponse = null;

	/**
	 * Constructor for OvalsNspResponseHelper.
	 */
	public OvalsNspResponseHelper() 
	{
		super();
	}

	/**
	 * Returns the ovalsNspResponse.
	 * @return OVALSNSPType
	 */
	public OVALSNSPType getOvalsNspMsagValidationResponse() {
		return OvalsNspMsagValidationResponse;
	}
	
	/**
	 * Sets the ovalsNspMsagValidationResponse.
	 * @param ovalsNspMsagValidationResponse The ovalsNspMsagValidationResponse to set
	 */
	public void setOvalsNspMsagValidationResponse(OVALSNSPType ovalsNspMsagValidationResponse) {
		OvalsNspMsagValidationResponse = ovalsNspMsagValidationResponse;
	}

}
