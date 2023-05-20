//$Id: OvalsNspRequestHelper.java,v 1.9 2008/02/29 23:25:27 jh9785 Exp $

package com.sbc.eia.bis.BusinessInterface.ovalsnsp;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.sbc.eia.bis.lim.jaxb.ovalsnsp.impl.CONTROLImpl;
import com.sbc.eia.bis.lim.jaxb.ovalsnsp.impl.ERRORImpl;
import com.sbc.eia.bis.lim.jaxb.ovalsnsp.impl.MSAGADDRESSImpl;
import com.sbc.eia.bis.lim.jaxb.ovalsnsp.impl.MSAGVALIDATEImpl;
import com.sbc.eia.bis.lim.jaxb.ovalsnsp.impl.OVALSNSPImpl;
import com.sbc.eia.bis.lim.jaxb.ovalsnsp.impl.REQUESTImpl;
import com.sbc.eia.bis.lim.util.LIMBase;
import com.sbc.eia.idl.lim.helpers.AddressHandlerOvalsNSP;

/**
 * @author gg2712
 * 
 * Description:
 * 
 */
public class OvalsNspRequestHelper
{
	private LIMBase limBase;
	AddressHandlerOvalsNSP address = null; 
	
	/**
	 * Constructor for OvalsNspRequestHelper.
	 */
	public OvalsNspRequestHelper(LIMBase aLimBase)
	{
		limBase = aLimBase;
	}

	/**
	 * Constructor for OvalsNspRequestHelper.
	 */
	public OVALSNSPImpl getOvalsNspMsagValidationRequest(OvalsNspRetrieveLocReq req)
	{
		address = (AddressHandlerOvalsNSP) req.getAddr();

		MSAGADDRESSImpl msagAddress = new MSAGADDRESSImpl();
		
		msagAddress.setNSPINQID(address.getAddressId());
		msagAddress.setHOUSENUMBER(this.getHouseNumber());		
		msagAddress.setPREFIXDIRECTION(address.getStreetDirection());
		msagAddress.setSTREETNAME(this.getStreetName());
		msagAddress.setCOMMUNITY(address.getCity());
		msagAddress.setSTATE(address.getState());
		msagAddress.setZIP(address.getPostalCode());
		msagAddress.setEXCHANGECODE(req.getExchangeCode());
		msagAddress.setMAXCANDIDATES(Integer.toString(req.getMaximumAlternateAddress()).toString()); 
		msagAddress.setID(getID());
		msagAddress.setLEVELTYPE(address.getLevelType());
		msagAddress.setLEVELVALUE(address.getLevelValue());
		msagAddress.setSTRUCTURETYPE(address.getStructType());
		msagAddress.setSTRUCTUREVALUE(address.getStructValue());
		msagAddress.setUNITTYPE(address.getUnitType());
		msagAddress.setUNITVALUE(address.getUnitValue());

		REQUESTImpl request = new REQUESTImpl();
		request.getMSAGADDRESS().add(msagAddress);
		
		CONTROLImpl control = new CONTROLImpl();
		ERRORImpl error = new ERRORImpl();
		
		MSAGVALIDATEImpl msagValidate = new MSAGVALIDATEImpl();
		msagValidate.setREQUEST(request);
		msagValidate.setCONTROL(control);
		msagValidate.setERROR(error);
				
		OVALSNSPImpl ovalsNsp = new OVALSNSPImpl();
		ovalsNsp.setMSAGVALIDATE(msagValidate);
		
		return ovalsNsp;
	}
	
	/**
	 * Get the StreetName
	 * @return void
	 */
	protected String getStreetName()
	{
		StringBuffer sb = new StringBuffer(address.getStreetName());
		sb.append(" ");
		sb.append(address.getStreetThoroughfare());
		sb.append(" ");
		sb.append(address.getStreetNameSuffix());
		return sb.toString().trim();
	}
	
	/**
	 * Get houseNumber
	 */
	protected String getHouseNumber()
	{
		StringBuffer sbHseNbr = new StringBuffer();
		sbHseNbr.append(address.getHouseNumberPrefix());
		sbHseNbr.append(" ");
		sbHseNbr.append(address.getHouseNumber());
		sbHseNbr.append(" ");
		sbHseNbr.append(address.getHouseNumberSuffix());	
		return sbHseNbr.toString().trim();	
	}	
	
	/**
	 * Get a unique ID
	 */
	protected String getID()
	{
		Date today = new java.util.Date();
		SimpleDateFormat formatter = new SimpleDateFormat( "yyyyMMddHHmmssSSS" ) ;
		String dateString = formatter.format( today ) ;
		
		int randomNbr = 1000 + (int)(9000*Math.random());  // get random number between 1000 and 9999
		
		StringBuffer sb = new StringBuffer("LIMBIS");
		sb.append(dateString);
		sb.append(randomNbr);
		
		return sb.toString().trim();
	}
}
