//$Id: OvalsNspListResp.java,v 1.8 2008/02/29 23:24:56 jh9785 Exp $

package com.sbc.eia.bis.BusinessInterface.ovalsnsp;

import java.util.List;

import com.sbc.bccs.idl.helpers.IDLUtil;
import com.sbc.eia.bis.lim.jaxb.ovalsnsp.RESPONSEType;
import com.sbc.eia.bis.lim.jaxb.ovalsnsp.impl.MSAGADDRESSTypeImpl;
import com.sbc.eia.bis.lim.jaxb.ovalsnsp.impl.RESPONSETypeImpl;
import com.sbc.eia.bis.lim.transactions.RetrieveLocation.AddrAltResultResp;
import com.sbc.eia.bis.lim.transactions.RetrieveLocation.ProviderLocationPropertyBuilder;
import com.sbc.eia.bis.lim.transactions.RetrieveLocation.RetrieveLocReq;
import com.sbc.eia.bis.lim.util.LIMBase;
import com.sbc.eia.idl.bis_types.SystemFailure;
import com.sbc.eia.idl.lim.helpers.AddressHandlerOvalsNSP;
import com.sbc.eia.idl.lim_types.Location;
import com.sbc.eia.idl.lim_types.ProviderLocationProperty;

/**
 * @author gg2712
 * Build a list alternate addresses from OVALS NSP Msag Validation
 */
public class OvalsNspListResp extends AddrAltResultResp
{
	private RetrieveLocReq request = null;
	RESPONSEType response = null;
	
	/**
	 * This default (package) access constructor allows other classes
	 * to use this class in an atypical way.
	 * @param utility LIMBase
	 */
	OvalsNspListResp(LIMBase utility) 
	{
		super(utility);
	}
		
	public OvalsNspListResp(
		LIMBase utility, 
		RetrieveLocReq request, 
		RESPONSEType msagAddress) 
	throws 
		SystemFailure 
	{
		super(utility);
		setRequest(request);
		setResponse(msagAddress);
		processListResponse();
	}	
	
	protected void processListResponse()
	{
		List msagAddressList = response.getMSAGADDRESS();
		
		for(int i=0; i < msagAddressList.size(); i++)
		{
			addrList.add(toLocation((MSAGADDRESSTypeImpl) msagAddressList.get(i)));
			locSize++;
		}
	}
	
	protected Location toLocation(MSAGADDRESSTypeImpl msagAddress)
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
//      propertyBuilder.setTarCode(msagAddress.getTARCODE(), true);
//      propertyBuilder.setCountyId(msagAddress.getCOUNTYID(), true);
//      propertyBuilder.setExchangeCode(msagAddress.getEXCHCODE(), true);
//		propertyBuilder.setPublicSafetyAnsweringPointId(msagAddress.getPSAPID(), true);
//      propertyBuilder.setLatitude(msagAddress.getLATITUDE(), true);
//      propertyBuilder.setLongitude(msagAddress.getLONGITUDE(), true);

		//CR 13404: If clients do not provide supplement Location, the following properties should be blank
        propertyBuilder.setTarCode("", true);
        propertyBuilder.setCountyId("", true);
        propertyBuilder.setExchangeCode("", true);
		propertyBuilder.setPublicSafetyAnsweringPointId("", true);
        propertyBuilder.setLatitude("", true);
        propertyBuilder.setLongitude("", true);
		
		ProviderLocationProperty[] providerLocationProperties = 
			new ProviderLocationProperty[] { propertyBuilder.getProviderLocationProperty() };
		
		Location location = 
			new Location(
				IDLUtil.toOpt(""),
				providerLocationProperties);
				
		return location;
	}

	/**
	 * Returns the response.
	 * @return RESPONSETypeImpl
	 */
	public RESPONSEType getResponse()
	{
		return response;
	}

	/**
	 * Sets the response.
	 * @param response The response to set
	 */
	public void setResponse(RESPONSEType response)
	{
		this.response = response;
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
