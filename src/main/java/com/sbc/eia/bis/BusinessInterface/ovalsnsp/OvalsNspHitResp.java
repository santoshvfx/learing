//$Id: OvalsNspHitResp.java,v 1.8 2008/02/29 23:24:25 jh9785 Exp $

package com.sbc.eia.bis.BusinessInterface.ovalsnsp;

import com.sbc.bccs.idl.helpers.IDLUtil;
import com.sbc.eia.bis.lim.jaxb.ovalsnsp.impl.MSAGADDRESSTypeImpl;
import com.sbc.eia.bis.lim.transactions.RetrieveLocation.AddrMatchResp;
import com.sbc.eia.bis.lim.transactions.RetrieveLocation.ProviderLocationPropertyBuilder;
import com.sbc.eia.bis.lim.transactions.RetrieveLocation.RetrieveLocReq;
import com.sbc.eia.bis.lim.util.LIMBase;
import com.sbc.eia.idl.bis_types.SystemFailure;
import com.sbc.eia.idl.lim.AddressMatchResultChoice;
import com.sbc.eia.idl.lim.helpers.AddressHandlerOvalsNSP;
import com.sbc.eia.idl.lim_types.AddressOpt;
import com.sbc.eia.idl.lim_types.ProviderLocationProperty;

/**
 * @author gg2712
 * This class handles an exact match response from OVALS NSP MSAG validation
 */
public class OvalsNspHitResp extends AddrMatchResp
{
	private RetrieveLocReq request = null;
	private MSAGADDRESSTypeImpl msagAddress = null;
	
	/**
	 * Constructor for OvalsNspHitResp.
	 * @param utility
	 */
	public OvalsNspHitResp(LIMBase utility)
	{
		super(utility);
	}
	
	/**
	 * Construct this object from a OVALS NSP "hit" response.
	 * @param utility LIMBase
	 * @param RetrieveLocReq aRequest
	 * @param MSAGADDRESSTypeImpl aMsagAddress
	 * @exception SystemFailure
	 */
	public OvalsNspHitResp(
		LIMBase utility, 
		RetrieveLocReq aRequest, 
		MSAGADDRESSTypeImpl aMsagAddress) 
	throws 
		SystemFailure 
	{
		super(utility);
		setRequest(aRequest);
		setMsagAddress(aMsagAddress);
	}
	
    /**
     * @see com.sbc.eia.bis.lim.transactions.RetrieveLocation.AddrMatchResp#setLocationId()
     */
    protected void setLocationId()
    {
        locationId = IDLUtil.toOpt("");
    }

    /**
     * @see com.sbc.eia.bis.lim.transactions.RetrieveLocation.AddrMatchResp#setProviderLocationPropertySeq()
     */
    protected void setProviderLocationPropertySeq()
    {
        providerLocationPropertyArray = new ProviderLocationProperty[]
        	{ toProviderLocationProperty() };
    }

	/**
	 * Builds and returns the providerLocationProperty object based on the JAXB response object. 
	 */
    protected ProviderLocationProperty toProviderLocationProperty()
    {
    	AddressHandlerOvalsNSP ah = 
	  		new AddressHandlerOvalsNSP(
					msagAddress.getHOUSENUMBER(),
					msagAddress.getPREFIXDIRECTION(),
					msagAddress.getSTREETNAME(),
					msagAddress.getSTREETTYPE(),
					msagAddress.getPOSTFIXDIRECTIONAL(),
					msagAddress.getCOMMUNITY(),
					msagAddress.getSTATE(),
					getRequest().getAddr().getPostalCode(),
					msagAddress.getSTRUCTURETYPE(),
					msagAddress.getSTRUCTUREVALUE(),
					msagAddress.getLEVELTYPE(),
					msagAddress.getLEVELVALUE(),
					msagAddress.getUNITTYPE(),
					msagAddress.getUNITVALUE());    
    	
        ProviderLocationPropertyBuilder propertyBuilder = 
        	new ProviderLocationPropertyBuilder(getRequest().getLocationPropertiesRequested());

        propertyBuilder.setE911Address(ah.toAddressOpt());
        propertyBuilder.setAddressMatchCode(msagAddress.getSCORE(), true);
        propertyBuilder.setTarCode(msagAddress.getTARCODE(), true);
        propertyBuilder.setCountyId(msagAddress.getCOUNTYID(), true);
        propertyBuilder.setExchangeCode(msagAddress.getEXCHCODE(), true);
        propertyBuilder.setPublicSafetyAnsweringPointId(msagAddress.getPSAPID(), true);
        propertyBuilder.setLatitude(msagAddress.getLATITUDE(), true);
        propertyBuilder.setLongitude(msagAddress.getLONGITUDE(), true);

    	return propertyBuilder.getProviderLocationProperty();
    }
    
	/**
	 * Returns the msagAddress.
	 * @return MSAGADDRESSTypeImpl
	 */
	public MSAGADDRESSTypeImpl getMsagAddress()
	{
		return msagAddress;
	}

	/**
	 * Sets the msagAddress.
	 * @param msagAddress The msagAddress to set
	 */
	public void setMsagAddress(MSAGADDRESSTypeImpl msagAddress)
	{
		this.msagAddress = msagAddress;
	}

	/**
	 * Returns the request.
	 * @return RetrieveLocReq
	 */
	public RetrieveLocReq getRequest()
	{
		return request;
	}

	/**
	 * Sets the request.
	 * @param request The request to set
	 */
	public void setRequest(RetrieveLocReq request)
	{
		this.request = request;
	}
}
