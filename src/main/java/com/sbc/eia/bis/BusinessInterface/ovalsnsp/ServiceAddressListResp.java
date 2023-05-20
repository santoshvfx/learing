//$Id: ServiceAddressListResp.java,v 1.4 2008/08/15 20:32:28 jh9785 Exp $

package com.sbc.eia.bis.BusinessInterface.ovalsnsp;

import java.util.Properties;

import com.sbc.bccs.idl.helpers.IDLUtil;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilder;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilderRule;
import com.sbc.eia.bis.lim.transactions.RetrieveLocation.AddrAltResultResp;
import com.sbc.eia.bis.lim.transactions.RetrieveLocation.ProviderLocationPropertyBuilder;
import com.sbc.eia.bis.lim.transactions.RetrieveLocation.RetrieveLocReq;
import com.sbc.eia.bis.lim.util.LIMBase;
import com.sbc.eia.bis.lim.util.LIMTag;
import com.sbc.eia.idl.bis_types.AccessDenied;
import com.sbc.eia.idl.bis_types.BusinessViolation;
import com.sbc.eia.idl.bis_types.DataNotFound;
import com.sbc.eia.idl.bis_types.InvalidData;
import com.sbc.eia.idl.bis_types.NotImplemented;
import com.sbc.eia.idl.bis_types.ObjectNotFound;
import com.sbc.eia.idl.bis_types.SystemFailure;
import com.sbc.eia.idl.lim.helpers.AddressHandler;
import com.sbc.eia.idl.lim_types.AlternativeServiceAddress;
import com.sbc.eia.idl.lim_types.Location;
import com.sbc.eia.idl.lim_types.ProviderLocationProperty;
import com.sbc.eia.idl.types.Severity;
import com.sbc.eia.idl.lim_types.Address2;
import com.sbc.eia.idl.lim_types.Address2Choice;
import com.sbc.eia.bis.lim.util.LIMIDLUtil;

/**
 * @author gg2712
 * Build a list alternate addresses from OVALS NSP Msag Validation
 */
public class ServiceAddressListResp extends AddrAltResultResp
{
	public static final String ADDRESS_MATCH_CODE = "95";
	
	private RetrieveLocReq request = null;
	private AlternativeServiceAddress[] aAlternativeServiceAddress = null;


	/**
	 * This default (package) access constructor allows other classes
	 * to use this class in an atypical way.
	 * @param utility LIMBase
	 */
	ServiceAddressListResp(LIMBase utility) 
	{
		super(utility);
	}
		
	public ServiceAddressListResp(
		LIMBase utility, 
		RetrieveLocReq retrieveLocReq, 
		AlternativeServiceAddress[] altServiceAddr) 
	throws 
		SystemFailure 
	{
		super(utility);
		request = retrieveLocReq;
		aAlternativeServiceAddress = altServiceAddr;
	}	
	
		
	protected void processListResponse() 
	throws InvalidData, AccessDenied, BusinessViolation, DataNotFound, SystemFailure, NotImplemented, ObjectNotFound
	{
		/* maxAddresses and NO_SIZE_LIMIT is defined in the parent class AddrAltResultResp */
			
		if (maxAddresses == NO_SIZE_LIMIT)
		{
			setMaxAddresses(9999);
		}

		for(int i = 0; i < aAlternativeServiceAddress.length && i < maxAddresses; i++)
		{
		    if ((aAlternativeServiceAddress[i].aServiceLocation() != null) &&
		        (aAlternativeServiceAddress[i].aServiceLocation().aServiceAddress != null) &&
		        (aAlternativeServiceAddress[i].aServiceLocation().aServiceAddress.discriminator().value() 
		         != Address2Choice._RANGED_ADDRESS2))
		    {
		        Address2 address2 = aAlternativeServiceAddress[i].aServiceLocation().aServiceAddress;
		        addrList.add(toLocation(address2));
		        locSize++;
		    }
		} 
	}
	
	protected Location toLocation(Address2 address2) 
	throws InvalidData, AccessDenied, BusinessViolation, DataNotFound, SystemFailure, NotImplemented, ObjectNotFound
	{
		Location location = null;
		
		try
		{
			AddressHandler ah = new AddressHandler(LIMIDLUtil.convertAddress2ToAddress(address2));
    	
	        ProviderLocationPropertyBuilder propertyBuilder = 
	        	new ProviderLocationPropertyBuilder(getRequest().getLocationPropertiesRequested());
	
	        propertyBuilder.setE911Address(ah.toAddressOpt());
	        propertyBuilder.setAddressMatchCode(ADDRESS_MATCH_CODE, true);
	        propertyBuilder.setTarCode("", true);
	        propertyBuilder.setCountyId("", true);
	        propertyBuilder.setExchangeCode("", true);
			propertyBuilder.setPublicSafetyAnsweringPointId("", true);
	        propertyBuilder.setLatitude("", true);
	        propertyBuilder.setLongitude("", true);
			
			ProviderLocationProperty[] providerLocationProperties = 
				new ProviderLocationProperty[] { propertyBuilder.getProviderLocationProperty() };
			
			location = 
				new Location(
					IDLUtil.toOpt(""),
					providerLocationProperties);
		}
		catch(Exception e)
		{
			Properties p = new Properties();
			p.setProperty("MSG", "AddressHandlerException: message: " + e.getMessage());
	
			ExceptionBuilder.parseException(
				utility.getCallerContext(),
				utility.getOvalsNspRulesFile(),
				"",
				LIMTag.CSV_InternalError,
				"LIM/OVALSNSP Error",
				true,
				ExceptionBuilderRule.NO_DEFAULT,
				null,
				null,
				utility,
				"LIM/OVALSNSP",
				Severity.UnRecoverable,
				p);
		}		
		return location;
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
