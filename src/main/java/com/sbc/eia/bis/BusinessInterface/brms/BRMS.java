// $Id: BRMS.java,v 1.30 2006/06/09 17:44:36 ak7627 Exp $

package com.sbc.eia.bis.BusinessInterface.brms;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.List;

import com.sbc.bccs.idl.helpers.IDLUtil;
import com.sbc.bccs.idl.helpers.TN;
import com.sbc.bccs.utilities.Logger;
import com.sbc.bccs.utilities.Utility;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilder;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilderResult;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilderRule;
import com.sbc.eia.bis.BusinessInterface.Company;
import com.sbc.eia.bis.BusinessInterface.Host;
import com.sbc.eia.bis.BusinessInterface.InvalidCompanyException;
import com.sbc.eia.bis.BusinessInterface.InvalidStateException;
import com.sbc.eia.bis.BusinessInterface.NullDataException;
import com.sbc.eia.bis.BusinessInterface.Selector;
import com.sbc.eia.bis.BusinessInterface.State;
import com.sbc.eia.bis.BusinessInterface.location.LocationI;
import com.sbc.eia.bis.RmNam.utilities.SmClient.SmClient;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.bis.lim.database.queries.LFACSSagLu;
import com.sbc.eia.bis.lim.database.queries.RetrieveAddrByWireCntrAndStNm;
import com.sbc.eia.bis.lim.database.queries.RetrieveAddrCountByWireCntrAndStNm;
import com.sbc.eia.bis.lim.database.queries.RetrieveCommunityByZip;
import com.sbc.eia.bis.lim.database.queries.RetrieveLFACSSagLuByWireCntrAndStNm;
import com.sbc.eia.bis.lim.database.queries.RetrieveLfacs_npanxxBySag;
import com.sbc.eia.bis.lim.database.queries.CommunityInfo;
import com.sbc.eia.bis.lim.database.queries.BRMSSagInfo;
import com.sbc.eia.bis.lim.database.queries.RetrieveBRMSSagInfo;
import com.sbc.eia.bis.lim.transactions.RetrieveLocation.ProviderLocationPropertyBuilder;
import com.sbc.eia.bis.lim.transactions.RetrieveLocation.RetrieveLocReq;
import com.sbc.eia.bis.lim.transactions.RetrieveLocation.RetrieveLocResp;
import com.sbc.eia.bis.lim.transactions.RetrieveLocation.RetrieveLocationBase;
import com.sbc.eia.bis.lim.util.LIMBase;
import com.sbc.eia.bis.lim.util.LIMBisContextManager;
import com.sbc.eia.bis.lim.util.LIMIDLUtil;
import com.sbc.eia.bis.lim.util.LIMTag;
import com.sbc.eia.idl.bis_types.AccessDenied;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.bis_types.BusinessViolation;
import com.sbc.eia.idl.bis_types.DataNotFound;
import com.sbc.eia.idl.bis_types.InvalidData;
import com.sbc.eia.idl.bis_types.NotImplemented;
import com.sbc.eia.idl.bis_types.ObjectNotFound;
import com.sbc.eia.idl.bis_types.SystemFailure;
import com.sbc.eia.idl.exception_types.ExceptionCode;
import com.sbc.eia.idl.lim.RetrieveLocationForAddressReturn;
import com.sbc.eia.idl.lim.RetrieveLocationForServiceReturn;
import com.sbc.eia.idl.lim.helpers.AddressHandler;
import com.sbc.eia.idl.lim.helpers.AddressHandlerBRMS;
import com.sbc.eia.idl.lim.helpers.AddressHandlerException;
import com.sbc.eia.idl.lim.helpers.ProviderLocationPropertyHandler;
import com.sbc.eia.idl.lim_types.Address;
import com.sbc.eia.idl.lim_types.Location;
import com.sbc.eia.idl.lim_types.ProviderLocationProperties;
import com.sbc.eia.idl.lim_types.ProviderLocationProperty;
import com.sbc.eia.idl.sm.SmFacade;
import com.sbc.eia.idl.types.LongOpt;
import com.sbc.eia.idl.types.ObjectKey;
import com.sbc.eia.idl.types.Severity;
import com.sbc.eia.idl.types.StringOpt;
import com.sbc.eia.idl.sm.GetServiceAddressReturn;

/**
 * This is the BRMS Host BusinessInterface class for the LIM RetrieveLocation
 * transactions.
 * Creation date: (2/28/01 11:20:02 AM)
 * @author: David Brawley
 */
public class BRMS extends Host implements LocationI 
{
	protected static String smHostNm = "";
	private static final String HostList[] = null;
	protected static String brmsLFACSMaxAddrLimit = "";
	private RetrieveBRMSSagInfo retrieveBRMSSagInfo = null;
	private BRMSSagInfo [] brmsSagInfoArray = null;
	private CommunityInfo [] townDfltExchangeInfo = null;
	private CommunityInfo [] exchangeInfo = null;
	private CommunityInfo [] communityInfo = null;
	private boolean serviceReq = false;	// true only if retrieve by TN.
	private boolean exactMatchReq = false;
	private boolean descriptiveAddr = false;
	private String community = "";
	private String exchange = "";
	private int maxAddrSize = 0;
	private int maxUnitSize = 0;

	private RetrieveLocReq request = null;
	private AddressHandlerBRMS ahBrmsReq = null;
	private AddressHandlerBRMS ahBrmsDesc = null;
	private AddressHandlerBRMS ahBrmsSag = null;
	private boolean rangeResp = false;
	private boolean unNumberedAddr = false;
	private boolean multCommAddr = false;
	private boolean multWireExch = false;
	private boolean multSrvdByExch = false;
	private String wireCenter = "";
	private String wireExch = "";
	private String savedDescAddr = "";
	private String savedDescHseNbr = "";
	private String srvdByExch = "";
	private String postalCode = null;
	private boolean outsideTerritory;

 	/**
	 *  SM BIS related info.
	 */
	private static String aProviderURL = "";
	private static String aSmHome = null;

	private static final String SM_EXCEPTION_MSG = "SM Exception";
	private static final String LIM_ORIGINATION = "LIM";
	private ExceptionBuilderResult exBldReslt = null;
	private String ruleFile = null;
    
	/**
	 * Construct a BRMS Host object.
	 * @param aCompany Company
	 * @param aUtility LIMBase
	 * @param aProperties Hashtable
	 */
	public BRMS(Company aCompany, Utility aUtility, Hashtable aProperties) {
		super(aCompany, aUtility, aProperties);
		ruleFile = (String) getProperties().get(LIMTag.CSV_FileName_BRMS);
	}
	/**
	 * Send the AddressValidation request to brmsservice and get the response.
	 * Creation date: (3/28/01 3:36:26 PM)
	 * @return RetrieveLocResp
	 * @param request BRMSRetrieveLocReq
	 * @param maxAddressLimit LongOpt
	 * @param maxUnitLimit LongOpt
	 * @exception InvalidData
	 * @exception AccessDenied
	 * @exception BusinessViolation
	 * @exception DataNotFound
	 * @exception SystemFailure
	 * @exception NotImplemented
	 * @exception ObjectNotFound
	 */
	protected RetrieveLocResp doAddressValidation(
		RetrieveLocReq request,
		LongOpt maxAddressLimit,
		LongOpt maxUnitLimit)
		throws
			InvalidData,
			AccessDenied,
			BusinessViolation,
			DataNotFound,
			SystemFailure,
			NotImplemented,
			ObjectNotFound 
	{
		maxAddrSize = getMaxValue (maxAddressLimit);
		maxUnitSize = getMaxValue (maxUnitLimit);
		
		townDfltExchangeInfo = null;
		exchangeInfo = null;
	 	communityInfo = null;
		ahBrmsDesc = null;
		ahBrmsSag = null;
		unNumberedAddr = false;
		multWireExch = false;
		multSrvdByExch = false;
		wireCenter = "";
		wireExch = "";
		savedDescAddr = "";
		savedDescHseNbr = "";
		srvdByExch = "";
		outsideTerritory = false;
		rangeResp = false;
		multCommAddr = false;
		
		RetrieveLocResp response = null;
		ahBrmsSag = new AddressHandlerBRMS ();
		BRMSSagInfo [] sagInfoTemp = null;
		
		try 
		{
			getUtility().log (LogEventId.REMOTE_CALL, "BRMS-DB2", "RetrieveBRMSSagInfo", "BRMS-DB2", "RetrieveBRMSSagInfo");
			retrieveBRMSSagInfo = new RetrieveBRMSSagInfo ((Properties) getLimBase().limBase.getPROPERTIES(),  getLimBase().limBase.myLogger.getBisLogger());
			retrieveBRMSSagInfo.connect();
			if (community != null && !community.equals (""))
			{
				townDfltExchangeInfo = retrieveBRMSSagInfo.getTownDfltExchange (community);
				if (townDfltExchangeInfo == null)
				{
					exchangeInfo = retrieveBRMSSagInfo.getExchange (community);
					communityInfo = exchangeInfo;
				}
				else 
					communityInfo = townDfltExchangeInfo;
					
				if (communityInfo == null)
				{					
					exBldReslt =	
						ExceptionBuilder.parseException(
							getBisContext(),
							ruleFile,
							"",
							LIMTag.CSV_InternalError,
							"Default Exchange or exchange was not found",
							true,
							ExceptionBuilderRule.NO_DEFAULT,
							null,
							null,
							utility,
							"BRMS",
							Severity.UnRecoverable,
							null);
				}
			}
			
			String streetInfo = null;
			if (!ahBrmsReq.getBrmsStreetRoute().equals (""))
				streetInfo = ahBrmsReq.getBrmsStreetRoute();
			else if (!ahBrmsReq.getAddAddrInfo().equals (""))
			{
				descriptiveAddr = true;
				streetInfo = ahBrmsReq.getAddAddrInfo();
			}

			brmsSagInfoArray = retrieveBRMSSagInfo.getBRMSSagInfo (streetInfo, exchange, communityInfo, false);
			
			if (brmsSagInfoArray == null)
			{
				if (exchangeInfo == null)
				{
					exchangeInfo = retrieveBRMSSagInfo.getExchange (community);
					if (exchangeInfo == null)
					{	
						exBldReslt =
							ExceptionBuilder.parseException(
								getBisContext(),
								ruleFile,
								"",
								LIMTag.CSV_AddressError,
								"Default Exchange or exchange was not found",
								true,
								ExceptionBuilderRule.NO_DEFAULT,
								null,
								null,
								utility,
								"BRMS",
								Severity.UnRecoverable,
								null);
					}
					exchangeInfo = retrieveBRMSSagInfo.getExchange (community);
					communityInfo = exchangeInfo;
					brmsSagInfoArray = retrieveBRMSSagInfo.getBRMSSagInfo (streetInfo, exchange, communityInfo, false);
				}
			}
			
			if (brmsSagInfoArray == null)
			{
				if (descriptiveAddr) 	
				{
					exBldReslt =
						ExceptionBuilder.parseException(
							getBisContext(),
							ruleFile,
							"",
							LIMTag.CSV_AddressError,
							null,
							true,
							ExceptionBuilderRule.NO_DEFAULT,
							null,
							null,
							utility,
							"BRMS",
							Severity.UnRecoverable,
							null);
				}
				
				if ((ahBrmsReq.getStThorofare().length() > 0) || (ahBrmsReq.getStNameSfx().length() > 0)) 
				{
					if (townDfltExchangeInfo != null)
						communityInfo = townDfltExchangeInfo;
					else 
						communityInfo = exchangeInfo;
					brmsSagInfoArray = retrieveBRMSSagInfo.getBRMSSagInfo (ahBrmsReq.getStreetNmDir(),
							exchange, communityInfo, true);
					if (brmsSagInfoArray == null && townDfltExchangeInfo != null && exchangeInfo != null)
					{
						communityInfo = exchangeInfo;
						brmsSagInfoArray = retrieveBRMSSagInfo.getBRMSSagInfo (ahBrmsReq.getStreetNmDir(),
							exchange, communityInfo, true);
					}
				} 
				else if (brmsSagInfoArray == null)
				{
					if (townDfltExchangeInfo != null)
						communityInfo = townDfltExchangeInfo;
					else 
						communityInfo = exchangeInfo;
						
					if (!ahBrmsReq.getBrmsStreetRoute().equals (""))
						streetInfo = ahBrmsReq.getBrmsStreetRoute();
					streetInfo = (streetInfo.length()>= 4) ? streetInfo.substring(0,4) : streetInfo;
					
					brmsSagInfoArray = retrieveBRMSSagInfo.getBRMSSagInfo (streetInfo,
							exchange, communityInfo, true);
					if (brmsSagInfoArray == null && townDfltExchangeInfo != null && exchangeInfo != null)
					{
						communityInfo = exchangeInfo;
						brmsSagInfoArray = retrieveBRMSSagInfo.getBRMSSagInfo (streetInfo,
							exchange, communityInfo, true);
					}
				}

				// if no data returned from re-entry, error
				if (brmsSagInfoArray == null) 
				{
					exBldReslt =
						ExceptionBuilder.parseException(
							getBisContext(),
							ruleFile,
							"",
							LIMTag.CSV_AddressError,
							null,
							true,
							ExceptionBuilderRule.NO_DEFAULT,
							null,
							null,
							utility,
							"BRMS",
							Severity.UnRecoverable,
							null);
				} 
				else 
				{
					// return address range object
					response = new BrmsAddrRangeResp (getLimBase().limBase, brmsSagInfoArray);
					response.setMaxAddresses (maxAddrSize);
					response.setMaxUnits (maxUnitSize);
					handleAddrRangeResp (request, (BrmsAddrRangeResp) response);
					return response;
				}			
			}	// end if (brmsSagInfoArray == null)
			getUtility().log (LogEventId.REMOTE_RETURN, "BRMS-DB2", "RetrieveBRMSSagInfo", "BRMS-DB2", "RetrieveBRMSSagInfo");

			// check if request was for descriptive address	
			if (descriptiveAddr) 
			{
				// if request not equal response, error
				if (!(streetInfo.equalsIgnoreCase (brmsSagInfoArray[0].street))) 
				{
					exBldReslt =
						ExceptionBuilder.parseException(
							getBisContext(),
							ruleFile,
							"",
							LIMTag.CSV_AddressError,
							"Descriptive not found",
							true,
							ExceptionBuilderRule.NO_DEFAULT,
							null,
							null,
							utility,
							"BRMS",
							Severity.UnRecoverable,
							null);
				}
				response = handleDescriptiveAddress ();

				if (response != null)
					return response;

				getUtility().log (LogEventId.REMOTE_CALL, "BRMS-DB2", "RetrieveBRMSSagInfo", "BRMS-DB2", "RetrieveBRMSSagInfo");
				// if comments field contains address information, 
				// re-enter BRMS-DB2 using street address
				streetInfo = savedDescAddr;

				if (townDfltExchangeInfo != null)
					communityInfo = townDfltExchangeInfo;
				else 
					communityInfo = exchangeInfo;
						
				brmsSagInfoArray = retrieveBRMSSagInfo.getBRMSSagInfo (streetInfo,
						exchange, communityInfo, false);
				if (brmsSagInfoArray == null && townDfltExchangeInfo != null && exchangeInfo != null)
				{
					communityInfo = exchangeInfo;
					brmsSagInfoArray = retrieveBRMSSagInfo.getBRMSSagInfo (streetInfo,
						exchange, communityInfo, false);
				}
				getUtility().log (LogEventId.REMOTE_RETURN, "BRMS-DB2", "RetrieveBRMSSagInfo", "BRMS-DB2", "RetrieveBRMSSagInfo");
							
				// if BRMS-DB2 data returned is empty for comments street address, error
				if (brmsSagInfoArray == null) 
				{
					exBldReslt =
						ExceptionBuilder.parseException(
							getBisContext(),
							ruleFile,
							"",
							LIMTag.CSV_AddressError,
							"Descriptive not found",
							true,
							ExceptionBuilderRule.NO_DEFAULT,
							null,
							null,
							utility,
							"BRMS",
							Severity.UnRecoverable,
							null);
				}

				// if descriptive (comments) street address request not equal sag response, error
				if (!(savedDescAddr.equalsIgnoreCase (brmsSagInfoArray[0].street))) 
				{
					exBldReslt =
						ExceptionBuilder.parseException(
							getBisContext(),
							ruleFile,
							"",
							LIMTag.CSV_AddressError,
							"Descriptive not found",
							true,
							ExceptionBuilderRule.NO_DEFAULT,
							null,
							null,
							utility,
							"BRMS",
							Severity.UnRecoverable,
							null);
				}
			}
			
			// not descriptive address
			else 
			{
				String reqStNm = ahBrmsReq.getBrmsStreetRoute();

				// if request not equal response, send range to client	
				if (!(reqStNm.equalsIgnoreCase (brmsSagInfoArray[0].street))) 
				{
					response = new BrmsAddrRangeResp (getLimBase().limBase, brmsSagInfoArray);
					response.setMaxAddresses (maxAddrSize);
					response.setMaxUnits (maxUnitSize);
					handleAddrRangeResp (request, (BrmsAddrRangeResp) response);
					return response;
				}
			} // end else

			// parse sag response to determine address results
			getUtility().log(LogEventId.AUDIT_TRAIL,"BRMS::handleAddrSagResp()|BrmsAddrSagResp::parseSagResp()|PRE");
			sagInfoTemp = parseSagResp();
			if (sagInfoTemp != null)
				brmsSagInfoArray = sagInfoTemp;
			
			getUtility().log(LogEventId.AUDIT_TRAIL,"BRMS::handleAddrSagResp()|BrmsAddrSagResp::parseSagResp()|POST");
		}
		catch (SQLException sqlEx)
		{	// rz change to DB2
			exBldReslt =
				ExceptionBuilder.parseException(
					getBisContext(),
					ruleFile,
					"",
					LIMTag.CSV_OracleError,
					"BRMS-DB2 :" + sqlEx.getMessage(),
					true,
					ExceptionBuilderRule.NO_DEFAULT,
					null,
					null,
					utility,
					"BRMS",
					Severity.UnRecoverable,
					null);
		} 

		// street name found, but no location returned from BRMS-DB2 matching request, 
		// check for range response and exceptions
		if (sagInfoTemp == null || sagInfoTemp[0] == null)
		{
			if ((descriptiveAddr) && (rangeResp)) 
			{
				exBldReslt =
					ExceptionBuilder.parseException(
						getBisContext(),
						ruleFile,
						"",
						LIMTag.CSV_AddressError,
						"Descriptive not found",
						true,
						ExceptionBuilderRule.NO_DEFAULT,
						null,
						null,
						utility,
						"BRMS",
						Severity.UnRecoverable,
						null);
			}

			// if multiple community in sagResp, business violation
			if (multCommAddr) 
			{
				exBldReslt =
					ExceptionBuilder.parseException(
						getBisContext(),
						ruleFile,
						"",
						LIMTag.CSV_AddressError,
						"Multiple Addresses",
						true,
						ExceptionBuilderRule.NO_DEFAULT,
						null,
						null,
						utility,
						"BRMS",
						Severity.UnRecoverable,
						null);
			}

			// if wire exch in sagResp is outside territory, business violation
			if (outsideTerritory) 
			{
				exBldReslt =
					ExceptionBuilder.parseException(
						getBisContext(),
						ruleFile,
						"",
						LIMTag.CSV_AddressError,
						"Outside territory",
						true,
						ExceptionBuilderRule.NO_DEFAULT,
						null,
						null,
						utility,
						"BRMS",
						Severity.UnRecoverable,
						null);
			}

			// if multiple wire exch in sagResp, business violation
			if (multSrvdByExch || multWireExch) 
			{
				exBldReslt =
					ExceptionBuilder.parseException(
						getBisContext(),
						ruleFile,
						"",
						LIMTag.CSV_AddressError,
						"Multiple Wire",
						true,
						ExceptionBuilderRule.NO_DEFAULT,
						null,
						null,
						utility,
						"BRMS",
						Severity.UnRecoverable,
						null);
			}

			// send Range Response
			//
			response = new BrmsAddrRangeResp (getLimBase().limBase, brmsSagInfoArray);
			response.setMaxAddresses (maxAddrSize);
			response.setMaxUnits (maxUnitSize);
			handleAddrRangeResp (request, (BrmsAddrRangeResp) response);
			return response;

		} // end if (sagInfoTemp == null || sagInfoTemp[0] == null)

		// if Served By Exchange populated, but WireExchange not populated
		// use Served By Exchange as WireExchange
		if (!srvdByExch.equals("") && wireExch.equals(""))
			wireExch = srvdByExch;
			
		try 
		{
			// use WireExch to WireCenter conversion table 
			wireCenter = RetrieveLfacs_npanxxBySag.retrieveLfacs_npanxxBySag (getLimProps(), wireExch,
				(Logger) getLimBase().limBase);
		} 
		catch (SQLException e) 
		{
			if (e.getMessage().indexOf("Exhausted Resultset") >= 0) {
				Properties tagValues = new Properties();
				tagValues.setProperty("WIREEXCH", wireExch);
				exBldReslt =
					ExceptionBuilder.parseException(
						getBisContext(),
						ruleFile,
						"",
						LIMTag.CSV_OracleError,
						"SagLfacsWc:Exhasted Resultset",
						true,
						ExceptionBuilderRule.NO_DEFAULT,
						null,
						null,
						utility,
						"BRMS",
						Severity.UnRecoverable,
						tagValues);
			} 
			else 
			{
				exBldReslt =
					ExceptionBuilder.parseException(
						getBisContext(),
						ruleFile,
						"",
						LIMTag.CSV_OracleError,
						"SagLfacsWc : " + e.getMessage(),
						true,
						ExceptionBuilderRule.NO_DEFAULT,
						null,
						null,
						utility,
						"BRMS",
						Severity.Recoverable,
						null);
			}
		}

		try 
		{
			// use requested data as input to LFACS Sag Living Unit Table
			// response is brmsHit or brmsList, or throw exception 
			getUtility().log(LogEventId.AUDIT_TRAIL,"BRMS::doAddressValidation() - calling handleLfacsSagLu |PRE");

			response = BrmsResponseFactory.handleLfacsSagLu (
					getLimBase().limBase,
					request,
					ahBrmsReq,					
					RetrieveLFACSSagLuByWireCntrAndStNm.retrieveLFACSSagLuByWireCntrAndStNm(
						getLimProps(),
						wireCenter,
						(descriptiveAddr) ? savedDescAddr : ahBrmsReq.getBrmsStreetRoute(),
						(descriptiveAddr) ? savedDescHseNbr : ahBrmsReq.getBrmsHouseNumber(),
						null,
                        ahBrmsReq.getStructType(),
                        ahBrmsReq.getStructValue(),
                        ahBrmsReq.getLevelType(),
                        ahBrmsReq.getLevelValue(),
                        ahBrmsReq.getUnitType(),
                        ahBrmsReq.getUnitValue(),
						(Logger) getLimBase().limBase),
					getBrmsLFACSMaxAddrLimit (maxAddrSize),
					getBrmsLFACSMaxAddrLimit (maxUnitSize));

			getUtility().log(LogEventId.AUDIT_TRAIL,"BRMS::doAddressValidation() - calling handleLfacsSagLu |POST");
		} 
		catch (SQLException e) 
		{
			exBldReslt =
				ExceptionBuilder.parseException(
					getBisContext(),
					ruleFile,
					"",
					LIMTag.CSV_OracleError,
					"LFACS SAG Table :" + e.getMessage(),
					true,
					ExceptionBuilderRule.NO_DEFAULT,
					null,
					null,
					utility,
					"BRMS",
					Severity.Recoverable,
					null);
		}

		if ((descriptiveAddr)
			&& (response instanceof BrmsAddrListResp)) 
		{
			exBldReslt =
				ExceptionBuilder.parseException(
					getBisContext(),
					ruleFile,
					"",
					LIMTag.CSV_AddressError,
					"Descriptive not found",
					true,
					ExceptionBuilderRule.NO_DEFAULT,
					null,
					null,
					utility,
					"BRMS",
					Severity.UnRecoverable,
					null);
		}

		if (response instanceof BrmsMultiValidation) 
		{
			getUtility().log(LogEventId.AUDIT_TRAIL,"BRMS::doAddressValidation()|BRMS::handleMultiValidation()|PRE");
			((BrmsMultiValidation) response).setWireCenter(	wireCenter);
			((BrmsMultiValidation) response).setSagInfo (brmsSagInfoArray);
			((BrmsMultiValidation) response).setBrmsReq (ahBrmsReq);
			response = handleMultiValidation(request, (BrmsMultiValidation) response);
			getUtility().log(LogEventId.AUDIT_TRAIL,"BRMS::doAddressValidation()|BRMS::handleMultiValidation()|POST");
		}

		if (response instanceof BrmsHitResp) 
		{
			getUtility().log(LogEventId.AUDIT_TRAIL,"BRMS::doAddressValidation()|BRMS::handleAddrHitResp()|PRE");
			((BrmsHitResp) response).setWireCenter (wireCenter);
			((BrmsHitResp) response).setSagResp (brmsSagInfoArray);
			((BrmsHitResp) response).ahBrmsAddr.setPostalCode (postalCode);
			handleAddrHitResp((BrmsHitResp) response);
			getUtility().log(LogEventId.AUDIT_TRAIL,"BRMS::doAddressValidation()|BRMS::handleAddrHitResp()|POST");
		} 
        else if (response instanceof BrmsAddrRangeResp) 
        {
			/**
			 *  WTN response must be an exactMatch, 
			 *  otherwise it will be an ObjectNotFound condition.
			 */
			if (serviceReq) 
			{
				exBldReslt =
					ExceptionBuilder.parseException(
						getBisContext(),
						ruleFile,
						"",
						LIMTag.CSV_TnServiceError,
						null,
						true,
						ExceptionBuilderRule.NO_DEFAULT,
						null,
						null,
						utility,
						"BRMS",
						Severity.UnRecoverable,
						null);
			}

			/**
			 *  If aMaximumBasicAddressReturnLimit is set to EXACT_MATCH_REQ (-1)  
			 *  request is for exactMatch only, 
			 *  otherwise it will be an ObjectNotFound condition.
			 */
			if (exactMatchReq) 
			{
				exBldReslt =
					ExceptionBuilder.parseException(
						getBisContext(),
						ruleFile,
						"",
						LIMTag.CSV_AddressError,
						null,
						true,
						ExceptionBuilderRule.NO_DEFAULT,
						null,
						null,
						utility,
						"BRMS",
						Severity.UnRecoverable,
						null);
			}

			getUtility().log(LogEventId.AUDIT_TRAIL,"BRMS::doAddressValidation()|BRMS::handleAddrRangeResp()|PRE");
			handleAddrRangeResp(request, (BrmsAddrRangeResp) response);
			getUtility().log(LogEventId.AUDIT_TRAIL,"BRMS::doAddressValidation()|BRMS::handleAddrRangeResp()|POST");
		} 
		else if (response instanceof BrmsAddrListResp) 
		{
			/**
			 *  WTN response must be an exactMatch, 
			 *  otherwise it will be an ObjectNotFound condition.
			 */
			if (serviceReq) 
			{
				exBldReslt =
					ExceptionBuilder.parseException(
						getBisContext(),
						ruleFile,
						"",
						LIMTag.CSV_TnServiceError,
						null,
						true,
						ExceptionBuilderRule.NO_DEFAULT,
						null,
						null,
						utility,
						"BRMS",
						Severity.UnRecoverable,
						null);
			}

			/**
			 *  If aMaximumBasicAddressReturnLimit is set to EXACT_MATCH_REQ (-1)  
			 *  request is for exactMatch only, 
			 *  otherwise it will be an ObjectNotFound condition.
			 */
			if (exactMatchReq) 
			{
				exBldReslt =
					ExceptionBuilder.parseException(
						getBisContext(),
						ruleFile,
						"",
						LIMTag.CSV_AddressError,
						null,
						true,
						ExceptionBuilderRule.NO_DEFAULT,
						null,
						null,
						utility,
						"BRMS",
						Severity.UnRecoverable,
						null);
			}

			getUtility().log(LogEventId.AUDIT_TRAIL,"BRMS::doAddressValidation()|BRMS::handleAddrListResp()|PRE");
			((BrmsAddrListResp) response).setSagInfo (brmsSagInfoArray);
			((BrmsAddrListResp) response).setBrmsReq (ahBrmsReq);
			handleAddrListResp(request, (BrmsAddrListResp) response);
			getUtility().log(LogEventId.AUDIT_TRAIL,"BRMS::doAddressValidation()|BRMS::handleAddrListResp()|POST");
		}

		return response;
	}

	/**
	 * Creates SM Bean and calls getServiceLocation interface.
	 * Creation date: (9/6/01 12:52:50 PM)
	 * @return Location
	 * @param objectHandleTN ObjectHandle
	 * @exception InvalidData An input parameter contained invalid data.
	 * @exception AccessDenied Access to the specified domain object or information is not allowed.
	 * @exception BusinessViolation The attempted action violates a business rule.
	 * @exception DataNotFound The attempted action has no data.
	 * @exception SystemFailure The method could not be completed due to system level errors (e.g., out of memory, loss of network connectivity)n.
	 * @exception NotImplemented The method has not been implemented.
	 * @exception ObjectNotFound The desired domain object could not be found.
	 */
	public Location getAddressByTN (ObjectKey objectKeyTN)
		throws
			InvalidData,
			AccessDenied,
			BusinessViolation,
			SystemFailure,
			NotImplemented,
			ObjectNotFound,
			DataNotFound {
        getUtility().log(LogEventId.DEBUG_LEVEL_2, "BRMS::getAddressByTN()");

        /**
         *  Determine SM BIS connection info.
         */
        aProviderURL = getLimProps().getProperty("SM_PROVIDER_URL");
        aSmHome = getLimProps().getProperty("SM_Home");

        // get Address from SM
        SmClient smClient = new SmClient();
//		new SmClient(aProviderURL, aSmHome, LIM_ORIGINATION,
//        	getLimProps().getProperty("INITIAL_CONTEXT_PROPERTIES_FILE"));

        Location loc = new Location();

        //
        // Temporary local context to specify "LIMBIS" as 
        // application name on input to SM
        //
        BisContext aBisContext =
            getLimBase().limBase.buildBisContext(getBisContext());

        try {
            getUtility().log(LogEventId.AUDIT_TRAIL,"BRMS::getAddressByTN()|SmClient::getSmBean()|PRE");
            SmFacade smBean =
                smClient.getSmBean(aBisContext, getUtility());
            getUtility().log(LogEventId.AUDIT_TRAIL,"BRMS::getAddressByTN()|SmClient::getSmBean()|POST");

			GetServiceAddressReturn getServiceLocation =
				new GetServiceAddressReturn();

            getUtility().log (LogEventId.REMOTE_CALL, aProviderURL, aSmHome, aSmHome,
                BRMSTag.SM_GET_SERVICE_LOCATION);

            getServiceLocation = smBean.getServiceLocation(aBisContext, objectKeyTN);

            getUtility().log (LogEventId.REMOTE_RETURN, aProviderURL, aSmHome, aSmHome,
                BRMSTag.SM_GET_SERVICE_LOCATION);

            loc = getServiceLocation.aAddress;
        } 
       
        /*catch (RemoteException e)
        {
            exBldReslt =
                ExceptionBuilder.parseException(
                    getBisContext(),
                    ruleFile,
                    "",
                    ExceptionCode.ERR_SMCL_REMOTE_EXCEPTION,
                    e.getMessage(),
                    true,
                    ExceptionBuilderRule.NO_DEFAULT,
                    null,
                    null,
                    getUtility(),
                    "BRMS",
                    Severity.UnRecoverable,
                    null);
        }*/

        // SM should not throw this NotImplemented exception. Redefine as SystemFailure because LIM needs to know about it. 
        catch (NotImplemented e) 
        {
            ////smClient.throwSmException(e.aExceptionData, getUtility(), ExceptionCode.ERR_LIM_SMCLIENT_EXCEPTION_SYSTEM_FAILURE, SM_EXCEPTION_MSG);
            exBldReslt =
                ExceptionBuilder.parseException(
                    getBisContext(),
                    ruleFile,
                    "",
                    e.aExceptionData.aCode,
                    "",
                    true,
                    ExceptionBuilderRule.NO_DEFAULT,
                    null,
                    null,
                    getUtility(),
                    "SM",
                    Severity.UnRecoverable,
                    null);
        } 
        catch (SystemFailure e) 
        {
            ////smClient.throwSmException(e.aExceptionData, getUtility(), ExceptionCode.ERR_LIM_SMCLIENT_EXCEPTION_SYSTEM_FAILURE, SM_EXCEPTION_MSG);
            exBldReslt =
                ExceptionBuilder.parseException(
                    getBisContext(),
                    ruleFile,
                    "",
                    e.aExceptionData.aCode,
                    "",
                    true,
                    ExceptionBuilderRule.NO_DEFAULT,
                    null,
                    null,
                    getUtility(),
                    "SM",
                    Severity.UnRecoverable,
                    null);
        }
        // SM should not throw this InvalidData exception. Redefine as SystemFailure because LIM needs to know about it.
        catch (InvalidData e) 
        {
            ////smClient.throwSmException(e.aExceptionData, getUtility(), ExceptionCode.ERR_LIM_SMCLIENT_EXCEPTION_SYSTEM_FAILURE, SM_EXCEPTION_MSG);
            exBldReslt =
                ExceptionBuilder.parseException(
                    getBisContext(),
                    ruleFile,
                    "",
                    e.aExceptionData.aCode,
                    "",
                    true,
                    ExceptionBuilderRule.NO_DEFAULT,
                    null,
                    null,
                    getUtility(),
                    "SM",
                    Severity.UnRecoverable,
                    null);
        }
        // Change ObjectNotFound to DataNotFound from LIM.
        catch (ObjectNotFound e) 
        {
            /////smClient.throwSmException(e.aExceptionData, getUtility(), ExceptionCode.ERR_LIM_SMCLIENT_EXCEPTION_DATA_NOT_FOUND, SM_EXCEPTION_MSG);
            exBldReslt =
                ExceptionBuilder.parseException(
                    getBisContext(),
                    ruleFile,
                    "",
                    e.aExceptionData.aCode,
                    "",
                    true,
                    ExceptionBuilderRule.NO_DEFAULT,
                    null,
                    null,
                    getUtility(),
                    "SM",
                    Severity.UnRecoverable,
                    null);
        } 
        catch (DataNotFound e) 
        {
            ////smClient.throwSmException(e.aExceptionData, getUtility(), ExceptionCode.ERR_LIM_SMCLIENT_EXCEPTION_DATA_NOT_FOUND, SM_EXCEPTION_MSG);
            exBldReslt =
                ExceptionBuilder.parseException(
                    getBisContext(),
                    ruleFile,
                    "",
                    e.aExceptionData.aCode,
                    "",
                    true,
                    ExceptionBuilderRule.NO_DEFAULT,
                    null,
                    null,
                    getUtility(),
                    "SM",
                    Severity.UnRecoverable,
                    null);
        }
        // SM should not throw this AccessDenied exception. Redefine as SystemFailure because LIM needs to know about it.
        catch (AccessDenied e) 
        {
            /////smClient.throwSmException(e.aExceptionData, getUtility(), ExceptionCode.ERR_LIM_SMCLIENT_EXCEPTION_SYSTEM_FAILURE, SM_EXCEPTION_MSG);
            exBldReslt =
                ExceptionBuilder.parseException(
                    getBisContext(),
                    ruleFile,
                    "",
                    e.aExceptionData.aCode,
                    "",
                    true,
                    ExceptionBuilderRule.NO_DEFAULT,
                    null,
                    null,
                    getUtility(),
                    "SM",
                    Severity.UnRecoverable,
                    null);
        }
        // SM should not throw this BusinessViolation exception. Redefine as SystemFailure because LIM needs to know about it.
        catch (BusinessViolation e) 
        {
            /////smClient.throwSmException(e.aExceptionData, getUtility(), ExceptionCode.ERR_LIM_SMCLIENT_EXCEPTION_SYSTEM_FAILURE, SM_EXCEPTION_MSG);
            exBldReslt =
                ExceptionBuilder.parseException(
                    getBisContext(),
                    ruleFile,
                    "",
                    e.aExceptionData.aCode,
                    "",
                    true,
                    ExceptionBuilderRule.NO_DEFAULT,
                    null,
                    null,
                    getUtility(),
                    "SM",
                    Severity.UnRecoverable,
                    null);
        }
        return loc;
	}

	/**
	 * Getter method for the BisContext.
	 * Creation date: (3/20/01 4:07:35 PM)
	 * @return BisContext
	 */
	public BisContext getBisContext() 
	{
		return getLimBase().limBase.getCallerContext();
	}
	/**
	 * Getter for brmsLFACSMaxAddressLimit. 
	 * Creation date: (9/7/01 11:01:38 AM)
	 * @return int
	 * @param maxAddr int
	 */
	public int getBrmsLFACSMaxAddrLimit (int maxAddr) 
	{
		try 
		{
			if (maxAddr > 0)
				return maxAddr;

			if (brmsLFACSMaxAddrLimit.length() > 0)
				return (new Integer(brmsLFACSMaxAddrLimit).intValue());

			brmsLFACSMaxAddrLimit = (getLimProps().getProperty("BRMS_LFACS_MAX_ADDRESS_LIMIT")).trim();

			if (getLimBase().limBase.allNumeric(brmsLFACSMaxAddrLimit))
				return (new Integer(brmsLFACSMaxAddrLimit).intValue());
		} 
        catch (NullPointerException npe) 
        {
		}

		brmsLFACSMaxAddrLimit = BRMSTag.DEFAULT_MAX_ADDRESS_LIMIT;

		return (new Integer(brmsLFACSMaxAddrLimit).intValue());
	}
	
	/**
	 * Return a list of Selectors of company/state combinations that this class supports.
	 * Creation date: (3/19/01 11:25:58 AM)
	 * @return Selector[]
	 * @param util Utility
	 * @exception InvalidStateException The exception description.
	 * @exception NullDataException The exception description.
	 * @exception InvalidCompanyException The exception description.
	 */
	public static Selector[] getCacheEntries(Utility util)
		throws InvalidStateException, NullDataException, InvalidCompanyException 
	{
		util.log(LogEventId.INFO_LEVEL_2, "BRMS::getCacheEntries()");

		// Add entries to the HostFactory cache at start time to avoid long searches
		return new Selector[] {
			new Selector(new Company(Company.Company_SouthernNETelephone,
				new State(State.State_Connecticut),	null,null),LocationI.LOCATION_INTERFACE_NAME,
				(BRMS.class).getName()),};
	}
	
	/**
	 * Return a list of host subclasses of this class.
	 * Creation date: (3/19/01 10:36:20 AM)
	 * @return String[]
	 * @param util Utility
	 */
	public static String[] getHostList(Utility util) {
		util.log(LogEventId.INFO_LEVEL_2, "BRMS::getHostList()");
		return HostList;
	}
	
	/**
	 * Return the business interfaces that this class implements.
	 * Creation date: (3/19/01 10:38:34 AM)
	 * @return String[]
	 * @param util Utility
	 */
	public static String[] getInterfaceList(Utility util) {
		util.log(LogEventId.INFO_LEVEL_2, "BRMS::getInterfaceList()");
		return new String[] { LocationI.LOCATION_INTERFACE_NAME };
	}
	
	/**
	 * Getter for the RetrieveLocationBase object.
	 * Creation date: (3/21/01 3:21:40 PM)
	 * @return RetrieveLocationBase
	 */
	protected RetrieveLocationBase getLimBase() 
	{
		return (RetrieveLocationBase) getPassThru();
	}
	/**
	 * Getter method for the LIM Properties.
	 * Creation date: (3/20/01 5:00:10 PM)
	 * @return Properties
	 */
	public Properties getLimProps() 
	{
		return (Properties) getLimBase().limBase.getPROPERTIES();
	}
	/**
	 * Extract the int value from a max-value LongOpt object.
	 * Creation date: (3/26/01 2:42:08 PM)
	 * @return int
	 * @param max LongOpt
	 */
	protected int getMaxValue(LongOpt max) 
	{
		try 
		{
			if (max != null)
				return max.theValue();
		} 
        catch (org.omg.CORBA.BAD_OPERATION bo) {
		}

		return 0;
	}
	/**
	 * Return a list of supported companies.
	 * Creation date: (3/19/01 10:02:48 AM)
	 * @return Company[]
	 * @param util Utility
	 * @exception InvalidStateException
	 * @exception NullDataException
	 * @exception InvalidCompanyException
	 */
	public static Company[] getSupportedCompanies(Utility util)
		throws InvalidStateException, NullDataException, InvalidCompanyException 
	{
		util.log(LogEventId.INFO_LEVEL_2, "BRMS::getSupportedCompanies()");
		return new Company[] 
		{
			 new Company(
				Company.Company_SouthernNETelephone,
				State.getAnAnyState(),
				null,
				null)
		};
	}
	/**
	 * Getter method for the Utility object.
	 * Creation date: (3/20/01 4:13:28 PM)
	 * @return Utility
	 */
	public Utility getUtility() 
	{
		return utility;
	}

	/**
	 * A few WireCenters are considered invalid and must be converted to a valid WireCenter.
	 * Creation date: (8/30/01 9:59:18 AM)
	 * @return String
	 * @param wireCenter String
	 */
	public String getWireCenterConversion (String wireCenter) 
	{
		try 
		{
			// check if wireCenter is invalid, convert to valid wireCenter.
			String validWC = getLimBase().limBase.getMyWireCntrProperty().getProperty(wireCenter.trim()).trim();
			if (validWC != null && validWC.length() > 0) 
			{
				getUtility().log(LogEventId.INFO_LEVEL_1,"BRMS wireCenter converted from <" 
                     + wireCenter + "> to <" + validWC + ">");
				return validWC;
			}
		} 
		catch (NullPointerException npe) 
		{
		}
		return wireCenter;
	}
    
	/**
	 * The address was found for the request. 
	 * Return an ExactMatch response.
	 * Creation date: (6/27/01 12:01:14 PM)
	 * @param hitResp  a BrmsHitResp object
	 * @exception InvalidData
	 * @exception AccessDenied
	 * @exception BusinessViolation
	 * @exception DataNotFound
	 * @exception SystemFailure
	 * @exception NotImplemented
	 * @exception ObjectNotFound
	 */
	protected void handleAddrHitResp (BrmsHitResp hitResp)
		throws
			InvalidData,
			AccessDenied,
			BusinessViolation,
			DataNotFound,
			SystemFailure,
			NotImplemented,
			ObjectNotFound 
	{
		try 
		{
			//if (hitResp.getCircuitId() != null)
            if (hitResp.getCircuitId() != null)
            {
				hitResp.addWorkingTn(hitResp.getCircuitId().trim());
                hitResp.facsLoopInd = true;             
            }

			// check to see if there are units, If Unit NOT populated
			// set address count for use in hitResp.setAddressData()
			getUtility().log(LogEventId.AUDIT_TRAIL,"BRMS::handleAddrHitResp()|BRMS::getAddrCountByWireCntrAndStNm()|PRE");
			if (!(hitResp.isUnitPopulated()))
				hitResp.setAddrCount(
					getAddrCountByWireCntrAndStNm(
						hitResp.getWireCenter(),
						hitResp.ahBrmsAddr.getBrmsStreetRoute(),
						hitResp.ahBrmsAddr.getBrmsHouseNumber(),
						hitResp.ahBrmsAddr.getCity()));
			getUtility().log(LogEventId.AUDIT_TRAIL,"BRMS::handleAddrHitResp()|BRMS::getAddrCountByWireCntrAndStNm()|POST");

			//check if wireCenter needs to be converted to valid NPANXX
			getUtility().log(LogEventId.AUDIT_TRAIL,"BRMS::handleAddrHitResp()|BRMS::getWireCenterConversion()|PRE");
			hitResp.setWireCenter(getWireCenterConversion(hitResp.getWireCenter()));
			getUtility().log(LogEventId.AUDIT_TRAIL,"BRMS::handleAddrHitResp()|BRMS::getWireCenterConversion()|POST");
		} 
		catch (SQLException e) 
		{
			exBldReslt =
				ExceptionBuilder.parseException(
					getBisContext(),
					ruleFile,
					"",
					LIMTag.CSV_OracleError,
					"LFACS SAG Table :" + e.getMessage(),
					true,
					ExceptionBuilderRule.NO_DEFAULT,
					null,
					null,
					utility,
					"BRMS",
					Severity.Recoverable,
					null);
		}
    }
    
	/**
	 * The street number was not found for address. 
	 * Need to return AddressList response.
	 * Creation date: (6/27/01 12:01:14 PM)
	 * @param locReq    a BrmsRetrieveLocReq object
	 * @param listResp  a BrmsAddrListResp object
	 * @exception InvalidData
	 * @exception AccessDenied
	 * @exception BusinessViolation
	 * @exception DataNotFound
	 * @exception SystemFailure
	 * @exception NotImplemented
	 * @exception ObjectNotFound
	 */
	protected void handleAddrListResp (RetrieveLocReq locReq, BrmsAddrListResp listResp)
		throws
			InvalidData,
			AccessDenied,
			BusinessViolation,
			DataNotFound,
			SystemFailure,
			NotImplemented,
			ObjectNotFound 
	{
		try 
		{
			// check if multiple city for single address
			if ((listResp.getAddrList().isEmpty())
				&& (listResp.getAddressArray() != null)) 
			{
				getUtility().log(LogEventId.AUDIT_TRAIL,"BRMS::handleAddrListResp()|BrmsAddrListResp::parseAddressArray()|PRE");
				listResp.parseAddressArray (descriptiveAddr);
				getUtility().log(LogEventId.AUDIT_TRAIL,"BRMS::handleAddrListResp()|BrmsAddrListResp::parseAddressArray()|POST");
			}

			// request Address List      
			if (listResp.getAddrList().isEmpty()) 
			{
				getUtility().log(LogEventId.AUDIT_TRAIL,"BRMS::handleAddrListResp()|BRMS::getAddrByWireCntrAndStNm()|PRE");
				listResp.setAddressArray(			
					RetrieveAddrByWireCntrAndStNm.retrieveAddrByWireCntrAndStNm(
					getLimProps(),
					wireCenter,
					listResp.getBrmsReq().getBrmsStreetRoute(),
					listResp.getBrmsReq().getHouseNumber(), // rz getBrmsHouseNumber(),
					null,
                	ahBrmsReq.getStructValue(),
                	ahBrmsReq.getLevelValue(),
                	ahBrmsReq.getUnitValue(),
					getBrmsLFACSMaxAddrLimit(listResp.getMaxAddresses()),
					getBrmsLFACSMaxAddrLimit(listResp.getMaxUnits()),
					(Logger) getLimBase().limBase));

				getUtility().log(LogEventId.AUDIT_TRAIL,"BRMS::handleAddrListResp()|BRMS::getAddrByWireCntrAndStNm()|POST");
				getUtility().log(LogEventId.AUDIT_TRAIL,"BRMS::handleAddrListResp()|BrmsAddrListResp::parseAddressArray()|PRE");
				listResp.parseAddressArray (descriptiveAddr);
				getUtility().log(LogEventId.AUDIT_TRAIL,"BRMS::handleAddrListResp()|BrmsAddrListResp::parseAddressArray()|POST");
			}
		} 
		catch (SQLException e) 
		{
			exBldReslt =
				ExceptionBuilder.parseException(
					getBisContext(),
					ruleFile,
					"",
					LIMTag.CSV_OracleError,
					"LFACS SAG Table:" + e.getMessage(),
					true,
					ExceptionBuilderRule.NO_DEFAULT,
					null,
					null,
					utility,
					"BRMS",
					Severity.UnRecoverable,
					null);
		}

		if (listResp.getAddrList().isEmpty()) 
		{
			exBldReslt =
				ExceptionBuilder.parseException(
					getBisContext(),
					ruleFile,
					"",
					LIMTag.CSV_AddressError,
					null,
					true,
					ExceptionBuilderRule.NO_DEFAULT,
					null,
					null,
					utility,
					"BRMS",
					Severity.UnRecoverable,
					null);
		}
	}

	/**
	 * Parse the SAGType array to populate range response returned to client.
	 * Creation date: (3/27/01 12:01:14 PM)
	 * @param rangeResp  a BrmsAddrRangeResp object
	 * @exception InvalidData
	 * @exception AccessDenied
	 * @exception BusinessViolation
	 * @exception DataNotFound
	 * @exception SystemFailure
	 * @exception NotImplemented
	 * @exception ObjectNotFound
	 */
	protected void handleAddrRangeResp (RetrieveLocReq locReq, BrmsAddrRangeResp rangeResp)
		throws
			InvalidData,
			AccessDenied,
			BusinessViolation,
			DataNotFound,
			SystemFailure,
			NotImplemented,
			ObjectNotFound {
		// check if rangelist is already created
		if (rangeResp.getRangeList().isEmpty()) {
			getUtility().log(LogEventId.AUDIT_TRAIL,"BRMS::handleAddrRangeResp()|BrmsAddrRangeResp::parseSagResp()|PRE");
			rangeResp.parseSagResp();
			getUtility().log(LogEventId.AUDIT_TRAIL,"BRMS::handleAddrRangeResp()|BrmsAddrRangeResp::parseSagResp()|POST");
		}

		// if rangelist is still empty, error
		if (rangeResp.getRangeList().isEmpty()) 
		{
			exBldReslt =
				ExceptionBuilder.parseException(
					getBisContext(),
					ruleFile,
					"",
					LIMTag.CSV_AddressError,
					null,
					true,
					ExceptionBuilderRule.NO_DEFAULT,
					null,
					null,
					utility,
					"BRMS",
					Severity.UnRecoverable,
					null);
		}

		// set request address for sorting in AddressRangeResp class
		getUtility().log(LogEventId.AUDIT_TRAIL,"BRMS::handleAddrRangeResp()|BrmsAddrRangeResp::setOrigReq()|PRE");
		rangeResp.setOrigReq((AddressHandler) locReq.getAddr());
		getUtility().log(LogEventId.AUDIT_TRAIL,"BRMS::handleAddrRangeResp()|BrmsAddrRangeResp::setOrigReq()|POST");
	}

	/**
	 * A Descriptive Address has been found, need to detemine whether to 
	 * return AddressRange response, AddressList response, or continue
	 * to retrieve the service address for an ExactMatch response.
	 * Creation date: (6/19/02 1:27:36 PM)
	 * @return RetrieveLocResp
	 * @param brmsSag com.sbc.eia.bis.BusinessInterface.brms.BrmsAddrSagResp
	 * @exception InvalidData
	 * @exception AccessDenied
	 * @exception BusinessViolation
	 * @exception DataNotFound
	 * @exception SystemFailure
	 * @exception NotImplemented
	 * @exception ObjectNotFound	
	 */
	protected RetrieveLocResp handleDescriptiveAddress ()
		throws
			InvalidData,
			AccessDenied,
			BusinessViolation,
			DataNotFound,
			SystemFailure,
			NotImplemented,
			ObjectNotFound {

		RetrieveLocResp response    = null;
		AddressHandlerBRMS brmsAddr = new AddressHandlerBRMS();
		String sagStNm    = "";
		String reqStNm       = "";
		String cmntStNm      = "";
		String zipCode       = ahBrmsReq.getPostalCode();
		String[] houseNumber = null;
		StringBuffer temp    = new StringBuffer("");
		int startItem        = 0;

		reqStNm = ahBrmsReq.getAddAddrInfo();

		try 
		{
			// loop thru sagResp looking for streetName match from request	
			for (int i = 0; i < brmsSagInfoArray.length; i++) 
			{
				if (!brmsSagInfoArray[i].street.trim().equals("")) 
				{
					sagStNm = brmsSagInfoArray[i].street.trim();
					// check if sag streetname equal to request
					if (reqStNm.equalsIgnoreCase (sagStNm)) 
					{
						if (brmsSagInfoArray[i].remarks.indexOf("SA ") >= 0)
						{
							cmntStNm = brmsSagInfoArray[i].remarks.substring((
                                brmsSagInfoArray[i].remarks.indexOf("SA "))+ 3).trim();
							if (cmntStNm.indexOf("(") > 0) 
								cmntStNm = cmntStNm.substring(0,cmntStNm.indexOf("("));
							break;
						}
					}
				}
			} // end for loop

			// if cmntStNm equals spaces then streetName match not found 
			if (cmntStNm.equals("")) 
			{
				exBldReslt =
					ExceptionBuilder.parseException(
						getBisContext(),
						ruleFile,
						"",
						LIMTag.CSV_AddressError,
						"Descriptive not found",
						true,
						ExceptionBuilderRule.NO_DEFAULT,
						null,
						null,
						utility,
						"BRMS",
						Severity.UnRecoverable,
						null);
			}

			// check if comments provide multiple streetnames
			if ((cmntStNm.indexOf(",") >= 0)  ||
				(cmntStNm.toUpperCase().indexOf(" OR ") >= 0)) 
			{
				exBldReslt =
					ExceptionBuilder.parseException(
						getBisContext(),
						ruleFile,
						"",
						LIMTag.CSV_AddressError,
						"Unrecognized service address",
						true,
						ExceptionBuilderRule.NO_DEFAULT,
						null,
						null,
						utility,
						"BRMS",
						Severity.UnRecoverable,
						null);
			}

			// check if comments provide a range of address alternatives	
			if (cmntStNm.indexOf('-') >= 0) 
			{
				String lowRange = "";
				String highRange = "";
				String[] item = LIMBase.stringToTokens(cmntStNm);

				if (cmntStNm.indexOf(" - ") >= 0) 
				{
					lowRange = item[0];
					highRange = item[2];
					startItem = 3;
				} 
				else 
				{
					StringTokenizer houseRange = new java.util.StringTokenizer(item[0], "-");
					String[] houseNbr = new String[houseRange.countTokens()];
					int counter = 0;
					while (houseRange.hasMoreTokens()) 
					{
						houseNbr[counter] = houseRange.nextToken();
						counter++;
					} // while
					lowRange = houseNbr[0];
					highRange = houseNbr[1];
					startItem = 1;
				}

				// check for errors in data	
				if ((!(LIMBase.allNumeric(lowRange)))
					&& (!(LIMBase.allNumeric(highRange)))) 
				{
					exBldReslt =
						ExceptionBuilder.parseException(
							getBisContext(),
							ruleFile,
							"",
							LIMTag.CSV_AddressError,
							"Unrecognized service address",
							true,
							ExceptionBuilderRule.NO_DEFAULT,
							null,
							null,
							utility,
							"BRMS",
							Severity.UnRecoverable,
							null);
				}

				// create range response
				for (int i = startItem; i < item.length; i++) {
					temp.append(item[i]);
					temp.append(" ");
				}
				ahBrmsSag.setBRMSFields(
					"",
					temp.toString(),
					"",
					BRMSTag.CT,
					zipCode,
                    "","","","","","");
				response = new BrmsAddrRangeResp (getLimBase().limBase);
				response.setMaxAddresses (maxAddrSize);
				response.setMaxUnits (maxUnitSize);
				((BrmsAddrRangeResp) response).addToRangeList (ahBrmsSag, lowRange, highRange);
				return response;

			} // end if ranged alternative addresses

			// check if comments provide a list of address alternatives	
			if (cmntStNm.indexOf('&') >= 0) 
			{
				int counter = 0;
				houseNumber =
					new String[(LIMBase.charCount(cmntStNm, '&')) + 1];
				String[] item = LIMBase.stringToTokens(cmntStNm);
				if (cmntStNm.indexOf(" & ") >= 0) 
				{
					for (int i = 0; i < item.length; i++) 
					{
						if ((counter < houseNumber.length) &&
							(!(item[i].equals("&")))) 
						{
							houseNumber[counter] = item[i];
							counter++;
						}
						startItem = (i + 1);
						if (counter == houseNumber.length)
							break;
					}
				} 
				else 
				{
					StringTokenizer houseRange = new java.util.StringTokenizer(item[0], "&");

					startItem = 1;
					while (houseRange.hasMoreTokens()) 
					{
						houseNumber[counter] = houseRange.nextToken();
						counter++;
					} // while
				}

				// check for errors in data
				for (int i = 0; i < houseNumber.length; i++) 
				{
					if (!(LIMBase.allNumeric(houseNumber[i]))) 
					{
						exBldReslt =
							ExceptionBuilder.parseException(
								getBisContext(),
								ruleFile,
								"",
								LIMTag.CSV_AddressError,
								"Unrecognized service address",
								true,
								ExceptionBuilderRule.NO_DEFAULT,
								null,
								null,
								utility,
								"BRMS",
								Severity.UnRecoverable,
								null);
					}
				} // end for loop

				// create range response
				for (int i = startItem; i < item.length; i++) 
				{
					temp.append(item[i]);
					temp.append(" ");
				}

				response = new BrmsAddrListResp (getLimBase().limBase);

				for (int i = 0; i < houseNumber.length; i++) 
				{
					ahBrmsSag.setBRMSFields(
						houseNumber[i],
						temp.toString(),
						"",
						BRMSTag.CT,
						zipCode,
                        "","","","","","");
					((BrmsAddrListResp) response).addToList(
						ahBrmsSag);
				}
				return response;

			} // end if list alternative addresses

			// normal address found, format request
			ahBrmsSag.setBRMSFields(
					cmntStNm,
					"",
					BRMSTag.CT,
					zipCode);

			savedDescAddr = ahBrmsSag.getBrmsStreetRoute();
			savedDescHseNbr = ahBrmsSag.getBrmsHouseNumber();
		} // try
		catch (AddressHandlerException ahe) 
		{
			Properties tagValues = new Properties();
			tagValues.setProperty("ERR", ahe.getMessage());
			exBldReslt =
				ExceptionBuilder.parseException(
					getBisContext(),
					ruleFile,
					"",
					LIMTag.CSV_AddressHandlerError,
					"Address format",
					true,
					ExceptionBuilderRule.NO_DEFAULT,
					null,
					null,
					utility,
					"BRMS",
					Severity.UnRecoverable,
					tagValues);
		}

		return response;
	}

	/**
	 * The Street Name/Community has been found, need to detemine whether to 
	 * return AddressRange response, instead of an ExactMatch response.
	 * Creation date: (6/27/01 12:01:14 PM)
	 * @return RetrieveLocResp
	 * @param req           a BrmsRetrieveLocReq object
	 * @param brmsMultiVal  a BrmsMultiValidation object
	 * @exception InvalidData
	 * @exception AccessDenied
	 * @exception BusinessViolation
	 * @exception DataNotFound
	 * @exception SystemFailure
	 * @exception NotImplemented
	 * @exception ObjectNotFound
	 */
	protected RetrieveLocResp handleMultiValidation (RetrieveLocReq req, BrmsMultiValidation brmsMultiVal)
		throws
			InvalidData,
			AccessDenied,
			BusinessViolation,
			DataNotFound,
			SystemFailure,
			NotImplemented,
			ObjectNotFound {
		RetrieveLocResp response = null;

		try 
		{
			// remove special character from request street name and
			// input to LFACS Sag Living Unit Table
			// response is null, brmsHit or throw exception 
			getUtility().log(LogEventId.AUDIT_TRAIL,"BRMS::handleMultiValidation() - retrieveLFACSSagLuByWireCntrAndStNm |PRE");

			response =
				BrmsResponseFactory.handleMultiValid (
					getLimBase().limBase,
					req,
					ahBrmsReq, 
					RetrieveLFACSSagLuByWireCntrAndStNm.retrieveLFACSSagLuByWireCntrAndStNm (
						getLimProps(),
						brmsMultiVal.getWireCenter(),
						brmsMultiVal.getStNmCnctntd(),
						brmsMultiVal.getBrmsReq().getBrmsHouseNumber(),
						null,
						ahBrmsReq.getStructType(),
                		ahBrmsReq.getStructValue(),
                		ahBrmsReq.getLevelType(),
                		ahBrmsReq.getLevelValue(),
                		ahBrmsReq.getUnitType(),
                		ahBrmsReq.getUnitValue(),
						(Logger) getLimBase().limBase),
					brmsMultiVal.getMaxAddresses(),
					brmsMultiVal.getMaxUnits());
					
			getUtility().log(LogEventId.AUDIT_TRAIL,"BRMS::handleMultiValidation() - retrieveLFACSSagLuByWireCntrAndStNm |POST");

			if ((response != null) && (response instanceof BrmsHitResp)) 
			{
				((BrmsHitResp) response).setWireCenter (brmsMultiVal.getWireCenter());
				((BrmsHitResp) response).setSagResp (brmsSagInfoArray);
				return response;
			}

			// replace special character with space from request street name and
			// input to LFACS Sag Living Unit Table
			// response is null, brmsHit or throw exception 
			getUtility().log(LogEventId.AUDIT_TRAIL,"BRMS::handleMultiValidation()|BRMS::getLFACSSagLuByWireCntrAndStNm()|PRE");

			response =
				BrmsResponseFactory.handleMultiValid (
					getLimBase().limBase,
					req,
					ahBrmsReq, 
					RetrieveLFACSSagLuByWireCntrAndStNm.retrieveLFACSSagLuByWireCntrAndStNm (
						getLimProps(),
						brmsMultiVal.getWireCenter(),
						brmsMultiVal.getStNmSpace(),
						brmsMultiVal.getBrmsReq().getBrmsHouseNumber(),
						null,
						ahBrmsReq.getStructType(),
                		ahBrmsReq.getStructValue(),
                		ahBrmsReq.getLevelType(),
                		ahBrmsReq.getLevelValue(),
                		ahBrmsReq.getUnitType(),
                		ahBrmsReq.getUnitValue(),
						(Logger) getLimBase().limBase),					
					brmsMultiVal.getMaxAddresses(),
					brmsMultiVal.getMaxUnits());

			getUtility().log(LogEventId.AUDIT_TRAIL,"BRMS::handleMultiValidation()|BRMS::getLFACSSagLuByWireCntrAndStNm()|POST");

			if ((response != null) && (response instanceof BrmsHitResp)) {
				((BrmsHitResp) response).setWireCenter(	brmsMultiVal.getWireCenter());
				((BrmsHitResp) response).setSagResp (brmsSagInfoArray);
				((BrmsHitResp) response).ahBrmsAddr.setPostalCode (brmsMultiVal.brmsSagInfo[0].zipCode); // rz ? postalCode
				return response;
			}
		} 
		catch (SQLException e) 
		{
			exBldReslt =
				ExceptionBuilder.parseException(
					getBisContext(),
					ruleFile,
					"",
					LIMTag.CSV_OracleError,
					"LFACS SAG Table :" + e.getMessage(),
					true,
					ExceptionBuilderRule.NO_DEFAULT,
					null,
					null,
					utility,
					"BRMS",
					Severity.UnRecoverable,
					null);
		}

		if (descriptiveAddr) 
		{
			exBldReslt =
				ExceptionBuilder.parseException(
					getBisContext(),
					ruleFile,
					"",
					LIMTag.CSV_AddressError,
					null,
					true,
					ExceptionBuilderRule.NO_DEFAULT,
					null,
					null,
					utility,
					"BRMS",
					Severity.UnRecoverable,
					null);
		}

		response = new BrmsAddrListResp(getLimBase().limBase);
		((BrmsAddrListResp) response).setSagInfo (brmsSagInfoArray);
		((BrmsAddrListResp) response).setBrmsReq (brmsMultiVal.getBrmsReq());

		return response;
	}

	/**
	 * Implementation of method retrieveLocationForAddress() from the LocationI interface by
	 * BRMS host.
	 * Creation date: (2/22/01 12:45:09 PM)
	 * @return RetrieveLocationForAddressReturn
	 * @param aContext BisContext
	 * @param aAddress AddressHandler
	 * @param aMaximumBasicAddressReturnLimit LongOpt
	 * @param aMaximumUnitReturnLimit LongOpt
	 * @exception InvalidData
	 * @exception AccessDenied
	 * @exception BusinessViolation
	 * @exception DataNotFound 
	 * @exception SystemFailure
	 * @exception NotImplemented
	 * @exception ObjectNotFound
	 */
	public RetrieveLocationForAddressReturn retrieveLocationForAddress(
		Address aAddress,
        ProviderLocationProperties[] aPropertiesToGet,
		LongOpt aMaximumBasicAddressReturnLimit,
		LongOpt aMaximumUnitReturnLimit,
		StringOpt aExchangeCode)
		throws
			InvalidData,
			AccessDenied,
			BusinessViolation,
			DataNotFound,
			SystemFailure,
			NotImplemented,
			ObjectNotFound
	{
		RetrieveLocationForAddressReturn result = null;
			
		try 
		{
			ahBrmsReq = new AddressHandlerBRMS (aAddress);
			request = new RetrieveLocReq (getLimBase().limBase, ahBrmsReq, aPropertiesToGet);
			request.logAddressReq();
			descriptiveAddr = false;
			community = "";

			if (ahBrmsReq.getAddAddrInfo().equals("") && ahBrmsReq.getStName().equals("") && 
				ahBrmsReq.getRoute().equals(""))
				{
					Properties tagValues = new Properties();
					tagValues.setProperty("CLASS", Address.class.getName());
    				exBldReslt = 
    					ExceptionBuilder.parseException(
    						getBisContext(),
    						(String) getProperties().get(LIMTag.CSV_FileName_LIM),
    						"",
    						LIMTag.CSV_EditError,
    						"Missing Street Name",
    						true,
    						ExceptionBuilderRule.NO_DEFAULT,
    						null,
    						null,
    						utility,
    						"LIM",
    						Severity.UnRecoverable,
    						tagValues);
				}
				
			if ((!ahBrmsReq.getAasgndHousNbr().equals("")) && (!ahBrmsReq.getAasgndHousNbr().equals("*"))) 
			{
				Properties tagValues = new Properties();
				tagValues.setProperty("CLASS", Address.class.getName());
				tagValues.setProperty("AHN", ahBrmsReq.getAasgndHousNbr());
				exBldReslt =
					ExceptionBuilder.parseException(
						getBisContext(),
						ruleFile,
						"",
						LIMTag.CSV_EditError,
						"Invalid AHN",
						true,
						ExceptionBuilderRule.NO_DEFAULT,
						null,
						null,
						utility,
						"BRMS",
						Severity.Recoverable,
						tagValues);
			}

			serviceReq = false;
			// If aMaximumBasicAddressReturnLimit is set to EXACT_MATCH_REQ (-1) request is for exactMatch only response
			exactMatchReq = (getMaxValue (aMaximumBasicAddressReturnLimit) == LIMTag.EXACT_MATCH_REQ
					? true: false);

			// lookup community in RetrieveCommunityByZip table.

			postalCode = ahBrmsReq.getPostalCode ();
			if (!ahBrmsReq.getPostalCode().equals(""))
			{
				community = RetrieveCommunityByZip.retrieveCommunityByZip (getLimProps(), ahBrmsReq.getPostalCode(),
					(Logger) getLimBase().limBase);
			}
			else
			{
				exchange = getLimBase().getSaga(); 
			}
											
			getUtility().log(LogEventId.AUDIT_TRAIL,"BRMS::retrieveLocationForAddress()|BRMS::doAddressValidation()|PRE");
			result = (doAddressValidation(request,
				aMaximumBasicAddressReturnLimit,
				aMaximumUnitReturnLimit)).toAddressReturn();
			getUtility().log(LogEventId.AUDIT_TRAIL,"BRMS::retrieveLocationForAddress()|BRMS::doAddressValidation()|POST");

		} 
		catch (AddressHandlerException ahe) 
		{
			Properties tagValues = new Properties();
			tagValues.setProperty("ERR", ahe.getMessage());
			exBldReslt =
				ExceptionBuilder.parseException(
					getBisContext(),
					ruleFile,
					"",
					LIMTag.CSV_AddressHandlerError,
					"Address format",
					true,
					ExceptionBuilderRule.NO_DEFAULT,
					null,
					null,
					utility,
					"BRMS",
					Severity.UnRecoverable,
					tagValues);
		} 
		catch (SQLException e) 
		{
			if (e.getMessage().indexOf("Exhausted Resultset") >= 0) 
			{
				Properties tagValues = new Properties();
				tagValues.setProperty("CLASS", Address.class.getName());
				tagValues.setProperty("ZIP", ahBrmsReq.getPostalCode());

				exBldReslt =
					ExceptionBuilder.parseException(
						getBisContext(),
						ruleFile,
						"",
						LIMTag.CSV_EditError,
						"Invalid Zip",
						true,
						ExceptionBuilderRule.NO_DEFAULT,
						null,
						null,
						utility,
						"BRMS",
						Severity.UnRecoverable,
						tagValues);
			} 
			else
				exBldReslt =
					ExceptionBuilder.parseException(
						getBisContext(),
						ruleFile,
						"",
						LIMTag.CSV_OracleError,
						"ZIP COMMUNITY Table " + e.getMessage(),
						true,
						ExceptionBuilderRule.NO_DEFAULT,
						null,
						null,
						utility,
						"BRMS",
						Severity.Recoverable,
						null);
		}
		finally 
		{
    		try
    		{
            	retrieveBRMSSagInfo.disconnect();
    		}
    		catch (Exception e)
    		{
    			// no need to worry...
    		}
		}
		return result;
	}
	
	/**
	 * Implementation of method retrieveLocationForService() from the LocationI interface by
	 * BRMS host.
	 * Creation date: (2/22/01 12:45:09 PM)
	 * @return RetrieveLocationForServiceReturn
	 * @param aContext BisContext
	 * @param aService TN
	 * @param aMaximumBasicAddressReturnLimit LongOpt
	 * @param aMaximumUnitReturnLimit LongOpt
	 * @exception InvalidData
	 * @exception AccessDenied
	 * @exception BusinessViolation
	 * @exception DataNotFound
	 * @exception SystemFailure
	 * @exception NotImplemented
	 * @exception ObjectNotFound
	 */
	public RetrieveLocationForServiceReturn retrieveLocationForService(
		TN aService,
        ProviderLocationProperties[] aPropertiesToGet,
		LongOpt aMaximumBasicAddressReturnLimit,
		LongOpt aMaximumUnitReturnLimit)
		throws
			InvalidData,
			AccessDenied,
			BusinessViolation,
			DataNotFound,
			SystemFailure,
			NotImplemented,
			ObjectNotFound {
        RetrieveLocationForServiceReturn result = null;
        ObjectKey service = new ObjectKey();

        try 
        {
            service = new ObjectKey(aService.toString(), "");
            community = "";

            getUtility().log (LogEventId.AUDIT_TRAIL, "BRMS::retrieveLocationForService()|BRMS::getAddressByTN()|PRE");
            Location smLocation = (Location) getAddressByTN(service);
            getUtility().log (LogEventId.AUDIT_TRAIL, "BRMS::retrieveLocationForService()|BRMS::getAddressByTN()|POST");

            getUtility().log (LogEventId.DEBUG_LEVEL_2, LIMIDLUtil.display(smLocation));

            AddressHandler aAddress =
                new AddressHandler(smLocation.aProviderLocationProperties[0].aServiceAddress.theValue());

            if (aAddress.getState().equals(""))
                aAddress.setState(BRMSTag.CT);

            ahBrmsReq = new AddressHandlerBRMS (aAddress.getAddress());
            request = new RetrieveLocReq(getLimBase().limBase, ahBrmsReq, aPropertiesToGet);
            request.logAddressReq();

            // perform lookup in RetrieveCommunityByZip table.
            postalCode = ahBrmsReq.getPostalCode ();
            if (!ahBrmsReq.getPostalCode().equals(""))
                community = RetrieveCommunityByZip.retrieveCommunityByZip (getLimProps(), ahBrmsReq.getPostalCode(),
					(Logger) getLimBase().limBase);

            // set Exchange if available
            try 
            {
            	if (community.equals(""))
                	exchange = smLocation.aProviderLocationProperties[0].aExchangeCode.theValue();
            } 
            catch (Exception e) {}

            if ((community.equals("")) && (exchange.equals(""))) 
            {
                exBldReslt =
                    ExceptionBuilder.parseException(
                        getBisContext(),
                        ruleFile,
                        "",
                        LIMTag.CSV_TnServiceError,
                        "SM incomplete address",
                        true,
                        ExceptionBuilderRule.NO_DEFAULT,
                        null,
                        null,
                        utility,
                        "BRMS",
                        Severity.Recoverable,
                        null);
            }

            serviceReq = true;
            descriptiveAddr = false;
			
            getUtility().log (LogEventId.AUDIT_TRAIL,
                "BRMS::retrieveLocationForService()|BRMS::doAddressValidation()|PRE");
            result = (doAddressValidation(request,
                    aMaximumBasicAddressReturnLimit,
                    aMaximumUnitReturnLimit))
                    .toServiceReturn();
            getUtility().log (LogEventId.AUDIT_TRAIL,
                "BRMS::retrieveLocationForService()|BRMS::doAddressValidation()|POST");

        } 
        catch (AddressHandlerException ahe) 
        {
        } 
        catch (SQLException sqle) 
        {
            if (sqle.getMessage().indexOf("Exhausted Resultset") >= 0) 
            {
                Properties tagValues = new Properties();
                tagValues.setProperty("CLASS", Address.class.getName());
                tagValues.setProperty("ZIP", ahBrmsReq.getPostalCode());

                exBldReslt =
                    ExceptionBuilder.parseException(
                        getBisContext(),
                        ruleFile,
                        "",
                        LIMTag.CSV_EditError,
                        "Invalid Postal Code ",
                        true,
                        ExceptionBuilderRule.NO_DEFAULT,
                        null,
                        null,
                        utility,
                        "BRMS",
                        Severity.UnRecoverable,
                        tagValues);
            } 
            else
                exBldReslt =
                    ExceptionBuilder.parseException(
                        getBisContext(),
                        ruleFile,
                        "",
                        LIMTag.CSV_OracleError,
                        "Zip:SQLException : " + sqle.getMessage(),
                        true,
                        ExceptionBuilderRule.NO_DEFAULT,
                        null,
                        null,
                        utility,
                        "BRMS",
                        Severity.Recoverable,
                        null);
        } 
        catch (ObjectNotFound onf) 
        {
            exBldReslt =
                ExceptionBuilder.parseException(
                    getBisContext(),
                    ruleFile,
                    "",
                    LIMTag.CSV_TnServiceError,
                    null,
                    true,
                    ExceptionBuilderRule.NO_DEFAULT,
                    null,
                    null,
                    utility,
                    "BRMS",
                    Severity.Recoverable,
                    null);
        }
		finally 
		{
    		try
    		{
            	retrieveBRMSSagInfo.disconnect();
    		}
    		catch (Exception e)
    		{
    			// no need to worry...
    		}
		}
        return result;
	}
	
	/**
	 * Get address count for requested wirecenter and street Name as input to database table.
	 * Creation date: (1/16/02 9:59:18 AM)
	 * @return int
	 * @param wireCenter  String
	 * @param stNm        String
	 * @param hseNbr      String
	 * @param city        String
	 * @exception SQLException
	 */
	public int getAddrCountByWireCntrAndStNm (String wireCenter, String stNm, String hseNbr, String city)
		throws java.sql.SQLException {

		// call table with addr and wirecenter
		return (
			RetrieveAddrCountByWireCntrAndStNm.retrieveAddrCountByWireCntrAndStNm(
				getLimProps(), wireCenter, stNm, hseNbr, city, (Logger) getLimBase().limBase));
	}
    /**
     * Parse through SagResp, if a match found return brmsOutSagInfo
     */
    public BRMSSagInfo [] parseSagResp()
    {
    	int i = -1;
    
		BRMSSagInfo [] brmsOutSagInfo = new BRMSSagInfo [1];
		brmsOutSagInfo[0] = new BRMSSagInfo ();
		BRMSSagInfo [] brmsZipZeroSagInfo = new BRMSSagInfo [1];
		brmsZipZeroSagInfo[0] = new BRMSSagInfo ();
    	boolean foundMatch = false;
    	boolean foundZipZeroMatch = false;
    	boolean moreThanOneZip = false;
		
    	getUtility().log(LogEventId.DEBUG_LEVEL_2, "Request StreetName<" + ahBrmsReq.getBrmsStreetRoute() + ">");
    	
    	String streetInfo 		= "";
    	String hseNbr = "";
    	int noMatchZipCount = 0;
    					
    	if (descriptiveAddr)
    	{
    		streetInfo = savedDescAddr;
    		hseNbr = savedDescHseNbr;
    	}
    	else
    	{
    		streetInfo = ahBrmsReq.getBrmsStreetRoute();
    		hseNbr = ahBrmsReq.getBrmsHouseNumber ();
    	}
    	
    	int recordIdPrev = 0;
    	
    	while (i+1 < brmsSagInfoArray.length)
    	{
    		if (recordIdPrev == brmsSagInfoArray[i+1].recordId)
    		{
    			i++;
    			continue;
    		}	
    		recordIdPrev = brmsSagInfoArray[i+1].recordId;
    		boolean foundH = false;
			String lowStreetRangeNoWire = "0";
			String highStreetRangeNoWire = "0";
			String lowStreetRange = "";
			String highStreetRange = "";
			String prevZipCode = "";
			moreThanOneZip = false;
			
			if (!ahBrmsReq.getPostalCode().equals (brmsSagInfoArray[i+1].zipCode))
				noMatchZipCount++;
    			
    		while ((i+1 < brmsSagInfoArray.length) && (recordIdPrev == brmsSagInfoArray[i+1].recordId))
    		{
    			i++;
    			
    			// if sag streetname not equal request, return
    			//
    			if (!streetInfo.equalsIgnoreCase (brmsSagInfoArray[i].street))
    				break;
    				 			
    			// if we have more than one record with levelInd == H and same RecordId => multi community address
    			//
    			if (brmsSagInfoArray[i].levelInd.equals("H") &&  foundH)
    			{
    		    	multCommAddr = true;
    		    	foundMatch = false;
    		    	break;
    			}
    			else if (brmsSagInfoArray[i].levelInd.equals("H"))
    				foundH = true;
    				   			
				// if low > 0 and high == 0, change high to match low.
				//    			
    			if (!brmsSagInfoArray[i].lowStreetRange.equals ("0") && 
    				brmsSagInfoArray[i].highStreetRange.equals ("0"))
    				brmsSagInfoArray[i].highStreetRange = brmsSagInfoArray[i].lowStreetRange;
			
    			lowStreetRange = brmsSagInfoArray[i].lowStreetRange;
    			highStreetRange = brmsSagInfoArray[i].highStreetRange;	
    					
    			if (brmsSagInfoArray[i].wireCenter.equals (""))
    			{
    				lowStreetRangeNoWire = brmsSagInfoArray[i].lowStreetRange;
    				highStreetRangeNoWire = brmsSagInfoArray[i].highStreetRange;		
    			}
    			    			
    			if (brmsSagInfoArray[i].lowStreetRange.equals ("0") && 
					brmsSagInfoArray[i].highStreetRange.equals ("0") &&
    				(!lowStreetRangeNoWire.equals ("0") || !highStreetRangeNoWire.equals ("0")))
    			{
    				lowStreetRange = lowStreetRangeNoWire;
    				highStreetRange = highStreetRangeNoWire;	
    			}
    			
    			if (((lowStreetRange.equals("0") && hseNbr.equals (""))
    				    		||
    				(lowStreetRange.equals("0") && highStreetRange.equals("0"))
								||
   			    	(((formatHseNbr (hseNbr)).compareTo (formatHseNbr (lowStreetRange)) >= 0)   &&
    		   		((formatHseNbr (hseNbr)).compareTo (formatHseNbr (highStreetRange)) <= 0)))
    		   					&&
    		   		checkPostalCode (i) )
				{
					if (!brmsSagInfoArray[i].wireCenter.equals (""))
					{
						if (wireExch.equals(""))
    						wireExch = brmsSagInfoArray[i].wireCenter.trim();
    					else if (!wireExch.equalsIgnoreCase (brmsSagInfoArray[i].wireCenter.trim()))
    						multWireExch = true; 
					}	
   					// when wireCenter is blank, check to see if served by another exchange
   					//
    				if	((brmsSagInfoArray[i].wireCenter.trim().equals("")) &&
    					((brmsSagInfoArray[i].remarks.trim().indexOf("SERVED BY") >= 0) ||
    			 		(brmsSagInfoArray[i].remarks.trim().indexOf("SRVD BY") >= 0)))	// rz ??? do we need
    				{
    					if (brmsSagInfoArray[i].remarks.trim().indexOf("TEL") >= 0)
    					{
    						outsideTerritory = true;
    						continue;
    					}
    					else
    					{
    						String tmpSrvdByExch = extractWireExch (brmsSagInfoArray[i].remarks.trim());
    						if (!tmpSrvdByExch.equals (""))
    						{
    							if (srvdByExch.equals(""))
    								srvdByExch = tmpSrvdByExch;
    							else
    								if (!srvdByExch.equals (tmpSrvdByExch))
    									multSrvdByExch = true;
    						}
    					}
    				}
    				  
    				if (prevZipCode.equals ("") && !brmsSagInfoArray[i].zipCode.equals ("00000"))
    					 prevZipCode = brmsSagInfoArray[i].zipCode;
    					 		
    				if (!brmsSagInfoArray[i].zipCode.equals ("00000") && !prevZipCode.equals (""))
    					if (prevZipCode.equals (brmsSagInfoArray[i].zipCode))
    						moreThanOneZip = true;
    					
					if (!multSrvdByExch && !multWireExch && (!srvdByExch.equals ("") || !wireExch.equals ("")))
					{
    					if (brmsSagInfoArray[i].zipCode.equals ("00000"))
    					{
    						foundZipZeroMatch = true;
    						brmsZipZeroSagInfo[0] = brmsSagInfoArray[i];
   							brmsZipZeroSagInfo[0].highStreetRange = highStreetRange;
    						brmsZipZeroSagInfo[0].lowStreetRange = lowStreetRange;
    					}
    					else
    					{
							foundMatch = true;
    						brmsOutSagInfo[0] = brmsSagInfoArray[i];
    						brmsOutSagInfo[0].highStreetRange = highStreetRange;
    						brmsOutSagInfo[0].lowStreetRange = lowStreetRange;
    					}
					}
				}
     	   	} // end while ((i+1 < brmsSagInfoArray.length) && (recordIdPrev == brmsSagInfoArray[i].recordId))    				
    	} // end while (i+1 < brmsSagInfoArray.length)
    	
		if (foundZipZeroMatch && ! foundMatch)
		{
			foundMatch = true;
			brmsOutSagInfo[0] = brmsZipZeroSagInfo[0];
		}

		if (moreThanOneZip && ahBrmsReq.getPostalCode().equals (""))
			brmsOutSagInfo[0].zipCode = "";
			
    	if (foundMatch && !multSrvdByExch && !multWireExch && 
    		!(noMatchZipCount > 1 && brmsOutSagInfo[0].zipCode.equals ("00000")))
    		return brmsOutSagInfo;
    	else 
    		return null;
    }
    
    private boolean checkPostalCode (int k)
    { 
    	if (ahBrmsReq.getPostalCode().equalsIgnoreCase (brmsSagInfoArray[k].zipCode) ||
    		 ahBrmsReq.getPostalCode().equals("") ||
    		 brmsSagInfoArray[k].zipCode.equals ("00000"))
    		return true;
    	else
    		return false;
    }

	/**
     * Setter for wire exchange from SAGType.aComments.
     * Creation date: (11/26/01 12:26:40 PM)
     * @return a String 
     * @param sagComment String
     */
    public String extractWireExch(String sagComment) 
    {
    	String comment = sagComment.substring(sagComment.indexOf("D BY") + 5).trim();
    	String oneStr = "";
    	StringBuffer tmpWireCenter = new StringBuffer("");
    	StringTokenizer comm_tmp = new java.util.StringTokenizer (comment, " ");
    	while (comm_tmp.hasMoreTokens())
    	{
    		oneStr = comm_tmp.nextToken ();
    		if (oneStr.equalsIgnoreCase("EXCH"))
    			break;
    		else
    			tmpWireCenter.append(" " + oneStr);
    	}
    
    	return ((tmpWireCenter.toString()).trim());
    }
    
	/**
	 * Format House Number into BRMS comparible value.
	 * Creation date: (3/6/01 4:59:19 PM)
	 * @return a String
	 * @param aHousNbr String
	 */
	public static String formatHseNbr(String aHousNbr) 
	{

		if ((aHousNbr == null) || (aHousNbr.length() == 0))
			return aHousNbr;

		StringBuffer temp = new StringBuffer("00000");
		for (int i = 0; i < aHousNbr.length(); i++) {
			if (!(Character.isDigit(aHousNbr.charAt(i)))) {
				temp.append(aHousNbr.substring(0, i));
				return (temp.substring(temp.length() - 5));
			}
		} // end for loop

		temp.append(aHousNbr);
		return (temp.substring(temp.length() - 5));

	}
  
  	public int getMaxAddrSize ()
  	{
  		return maxAddrSize;
  	}
  	
  	public int getMaxUnitSize ()
  	{
  		return maxUnitSize;
  	}
  	
  	public String getPostalCode ()
  	{
  		return postalCode;
  	}
  	
  	public AddressHandlerBRMS getAHBrmsReq ()
  	{
  		return ahBrmsReq;
  	}

}

