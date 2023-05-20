// $Id: OvalsUspsResponseFactory.java,v 1.2 2008/01/17 21:58:14 jh9785 Exp $

package com.sbc.eia.bis.BusinessInterface.PostalAddress.ovalsusps;

import java.util.Properties;

import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilder;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilderResult;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilderRule;
import com.sbc.eia.bis.framework.BisContextManager;
import com.sbc.eia.bis.lim.jaxb.ovalsusps.ERRORType;
import com.sbc.eia.bis.lim.jaxb.ovalsusps.OVALS;
import com.sbc.eia.bis.lim.jaxb.ovalsusps.RESPONSEType;
import com.sbc.eia.bis.lim.jaxb.ovalsusps.impl.ADDRESSTypeImpl;
import com.sbc.eia.bis.lim.jaxb.ovalsusps.impl.FATALTypeImpl;
import com.sbc.eia.bis.lim.jaxb.ovalsusps.impl.SAGENTTypeImpl;
import com.sbc.eia.bis.lim.jaxb.ovalsusps.impl.WARNINGTypeImpl;
import com.sbc.eia.bis.lim.transactions.common.RetrieveLocResp;
import com.sbc.eia.bis.lim.util.LIMBase;
import com.sbc.eia.bis.lim.util.LIMTag;
import com.sbc.eia.idl.bis_types.AccessDenied;
import com.sbc.eia.idl.bis_types.BusinessViolation;
import com.sbc.eia.idl.bis_types.DataNotFound;
import com.sbc.eia.idl.bis_types.InvalidData;
import com.sbc.eia.idl.bis_types.NotImplemented;
import com.sbc.eia.idl.bis_types.ObjectNotFound;
import com.sbc.eia.idl.bis_types.SystemFailure;
import com.sbc.eia.idl.types.Severity;

/**
 * OvalsUspsResponseFactory class.
 * Creation date: (6/1/01 9:45:53 AM)
 * @author: David Brawley
 */
public class OvalsUspsResponseFactory {

	/**
	 * Private default constructor.  You never need to instantiate this class.
	 * Creation date: (6/1/01 9:46:29 AM)
	 */
	private OvalsUspsResponseFactory() {}
	/**
	 * Factory method to instantiate a RetrieveLocResp object of the appropriate subtype.
	 * Creation date: (3/6/01 4:35:51 PM)
	 * @return RetrieveLocResp
	 * @param utility LIMBase
	 * @param ovals OVALS
	 * @param request OvalsUspsRetrieveLocReq
	 * @param maxAddr int the max number of addresses to return
	 * @throws SystemFailure
	 * @throws InvalidData
	 */
	public static RetrieveLocResp handleUspsXMLFromOvals(LIMBase utility, OVALS ovals, OvalsUspsRetrieveLocReq request, int maxAddr)
		throws SystemFailure, ObjectNotFound 
	{
		RetrieveLocResp rv = null; //rv = Return Value

		String ruleFile = (String) utility.getEnv(LIMTag.CSV_FileName_OVALS_USPS);
    	BisContextManager contextManager = new BisContextManager(utility.getCallerContext());
    	String application = contextManager.getApplication();

		RESPONSEType response = ovals.getUSPSVALIDATE().getRESPONSE();
		ERRORType error = ovals.getUSPSVALIDATE().getERROR();
		String warning = "";
				
		if (response != null && response.getADDRESS().size() > 0) {
			ADDRESSTypeImpl addressTypeImpl = (ADDRESSTypeImpl) response.getADDRESS().get(0);
			
			if (error != null && error.getWARNING().size() > 0)
				warning = ((WARNINGTypeImpl) error.getWARNING().get(0)).getFILTER();
			
			switch (addressTypeImpl.getSAGENT().size()) {
				case 0 : // No SAGENT data for address
					handleSystemFailure(ruleFile, utility, error);
					break;

				case 1 : // only one SAGENT tag returned for address
					String amq = ((SAGENTTypeImpl) addressTypeImpl.getSAGENT().get(0)).getAMQ();

					// determine if address match is found
					if ( (amq.startsWith("3") || amq.startsWith("4")) && !amq.substring(1,2).equalsIgnoreCase("E") )
						rv = new OvalsUspsHitResp(utility, request, (SAGENTTypeImpl) addressTypeImpl.getSAGENT().get(0),
							 	application, warning);
					else
						rv = new OvalsUspsListResp(utility, request, addressTypeImpl, application, warning);

					break;
				default : // More than one SAGENT tag (list) returned from OVALSUSPS
						rv = new OvalsUspsListResp(utility, request, addressTypeImpl, application, warning);
					break;
			}
		}
		else {
			handleSystemFailure(ruleFile, utility, error);
		}

		rv.setMaxAddresses(maxAddr);

		return rv;
	}
	/**
	 * Invoke handleException() to create a SystemFailure exception.  Hide the other
	 * exception types.
	 * Creation date: (3/6/01 4:59:19 PM)
	 * @param ruleFile String
	 * @param utility LimBase
	 * @param code String
	 * @param text String
	 * @exception SystemFailure
	 */
	public static void handleSystemFailure(String ruleFile, LIMBase utility, ERRORType error)
		throws SystemFailure, ObjectNotFound 
	{
		
		try{
			ExceptionBuilderResult exBldReslt = null;
		
			if (error != null && error.getFATAL().size() > 0){
				String text = ((FATALTypeImpl) error.getFATAL().get(0)).getFILTER();
				Properties tagValues = new Properties();
				tagValues.setProperty("MSG", (text == null ? "FATAL error tag returned from OvalsUsps, reason for error not provided" : text));

				exBldReslt =
					ExceptionBuilder.parseException(
						utility.getCallerContext(),
						ruleFile,
						"",
						LIMTag.CSV_OvalsError,
						"Ovals Fatal Error",
						true,
						ExceptionBuilderRule.NO_DEFAULT,
						null,
						null,
						utility,
						"OVALS_USPS",
						Severity.UnRecoverable,
						tagValues);
			}
			else{
				exBldReslt =
					ExceptionBuilder.parseException(
						utility.getCallerContext(),
						ruleFile,
						"",
						LIMTag.CSV_AddressError,
						null,
						true,
						ExceptionBuilderRule.NO_DEFAULT,
						null,
						null,
						utility,
						"OVALS_USPS",
						Severity.UnRecoverable,
						null);
			}
		}
		catch (InvalidData e) {}
		catch (BusinessViolation e) {}
		catch (DataNotFound e) {}
		catch (AccessDenied e) {}
		catch (NotImplemented e) {}
	}

}
