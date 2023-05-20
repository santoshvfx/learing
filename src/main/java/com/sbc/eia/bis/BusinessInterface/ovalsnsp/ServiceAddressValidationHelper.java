//$Id: ServiceAddressValidationHelper.java,v 1.9 2009/07/01 22:58:45 am9643 Exp $

package com.sbc.eia.bis.BusinessInterface.ovalsnsp;

import java.rmi.RemoteException;
import java.util.Properties;

import javax.ejb.CreateException;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.sbc.bccs.idl.helpers.IDLUtil;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilder;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilderRule;
import com.sbc.eia.bis.facades.lim.ejb.LimLocal;
import com.sbc.eia.bis.facades.lim.ejb.LimLocalHome;
import com.sbc.eia.bis.facades.lim.ejb.Lim;
import com.sbc.eia.bis.RmNam.utilities.LimClient.LimClient;
import com.sbc.eia.bis.framework.BisContextManager;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.bis.lim.transactions.RetrieveLocation.RetrieveLocReq;
import com.sbc.eia.bis.lim.util.LIMBase;
import com.sbc.eia.bis.lim.util.LIMIDLUtil;
import com.sbc.eia.bis.lim.util.LIMTag;
import com.sbc.eia.idl.bis_types.AccessDenied;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.bis_types.BisContextProperty;
import com.sbc.eia.idl.bis_types.BusinessViolation;
import com.sbc.eia.idl.bis_types.DataNotFound;
import com.sbc.eia.idl.bis_types.InvalidData;
import com.sbc.eia.idl.bis_types.NotImplemented;
import com.sbc.eia.idl.bis_types.ObjectNotFound;
import com.sbc.eia.idl.bis_types.SystemFailure;
import com.sbc.eia.idl.lim.AddressMatchResultChoice;
import com.sbc.eia.idl.lim.RetrieveLocationForAddressReturn;
import com.sbc.eia.idl.lim.RetrieveLocationForServiceAddressReturn;
import com.sbc.eia.idl.lim.helpers.AddressHandler;
import com.sbc.eia.idl.lim_types.Address;
import com.sbc.eia.idl.lim_types.LocationPropertiesToGetValue;
import com.sbc.eia.idl.lim_types.ProviderLocationProperties;
import com.sbc.eia.idl.lim_types.LocationPropertiesToGetSeqOpt;
import com.sbc.eia.idl.types.LongOpt;
import com.sbc.eia.idl.types.ObjectProperty;
import com.sbc.eia.idl.types.Severity;
import com.sbc.eia.idl.types.StringOpt;
import com.sbc.eia.idl.lim_types.Address2Choice;
import com.sbc.eia.idl.lim_types.AlternativeServiceAddressChoice;
import com.sbc.eia.idl.lim_types.AlternativeServiceAddress;
import com.sbc.bccs.utilities.Utility;
import com.sbc.eia.idl.exception_types.ExceptionCode;
/**
 * This class uses a local LIM ejb to call the retrieveLocationForAddress method to perform ServiceAddress validation.
 * @author GG2712
 */
public class ServiceAddressValidationHelper
{
	private LIMBase aLimBase = null;
	private BisContext aBisContext = null;
	private LocationPropertiesToGetSeqOpt aServiceAddressLocationPropertiesToGet = null;
	private RetrieveLocationForAddressReturn rlfaReturn = null;
	private RetrieveLocationForServiceAddressReturn rlfseraReturn = null;
	private boolean isExactMatch = false;
	private boolean isListAddress = false;

	/**
	 * Constructor for ServiceAddressValidationHelper.
	 */
	public ServiceAddressValidationHelper(LIMBase limBase)
	{
		super();
		aLimBase = limBase;
	}

	/**
	 * Get cached BisContext object or create a new one if needed.
	 * @return BisContext
	 */
	protected BisContext getBisContext()
	{
		if (aBisContext == null)
		{
			ObjectProperty[] properties =
				{
					new ObjectProperty(BisContextProperty.APPLICATION, "LIM_BIS"),
					new ObjectProperty(BisContextProperty.CUSTOMERNAME, "LIM_BIS")};
			aBisContext = new BisContext(properties);
		}
		return aBisContext;
	}

	/**
	 * Get cached LocationPropertiesToGetSeqOpt obecjt or create a new one if needed.
	 * @return LocationPropertiesToGetSeqOpt
	 */
	protected LocationPropertiesToGetSeqOpt getServiceAddressLocationPropertiesToGet()
	{
		if (aServiceAddressLocationPropertiesToGet == null)
		{
			StringOpt aProviderName = new StringOpt();
			aProviderName.__default();

			String[] locPropertiesToGetArray = new String[3];
			locPropertiesToGetArray[0] = LocationPropertiesToGetValue.EXCHANGECODE;
			locPropertiesToGetArray[1] = LIMTag.ADDRESSMATCHCODE;
			locPropertiesToGetArray[2] = LIMTag.ADDRESSMATCHCODEDESCRIPTION;

			aServiceAddressLocationPropertiesToGet =
				(LocationPropertiesToGetSeqOpt) IDLUtil.toOpt(
				    LocationPropertiesToGetSeqOpt.class,
				    locPropertiesToGetArray);
		}
		return aServiceAddressLocationPropertiesToGet;
	}

	/**
	 * Call LIM RetrieveLocationForServiceAddress to perform ServiceAddress validation
	 * @param address Address
     * @param locationPropertiesToGet ProviderLocationProperties[]
     * @param maximumBasicAddressReturnLimit LongOpt
     * @param maximumUnitReturnLimit LongOpt
	 */
	public void doServiceAddressValidation(
		Address address,
		ProviderLocationProperties[] locationPropertiesToGet,
		LongOpt maximumBasicAddressReturnLimit,
		LongOpt maximumUnitReturnLimit)
		throws InvalidData, AccessDenied, BusinessViolation, DataNotFound, SystemFailure, NotImplemented, ObjectNotFound
	{
		BisContext limBisContext = null;
		rlfaReturn = null;
		rlfseraReturn = null;
		isExactMatch = false;
		isListAddress = false;

		aLimBase.log(
			LogEventId.REMOTE_CALL,
			"LIM-BIS",
			"Lim EJB",
			"LIM RetrieveLocationForServiceAddress",  
			"retrieveLocationForServiceAddress():ServiceAddress validation"); 
				
		try
		{
			/*  get a reference to a LIM EJB and call retrieveLocationForServiceAddress() */

			String aProviderURL = "";
			String   aLimHome = null;
			aProviderURL = (String) aLimBase.getPROPERTIES().get("LIM_PROVIDER_URL");
			aLimHome = (String) aLimBase.getPROPERTIES().get("LIM_Home");
			
			if (aLimHome == null || aLimHome.trim().length() == 0)
			    {  
			        aLimBase.throwException(
			                ExceptionCode.ERR_LIM_INTERNAL,
			                "Missing LIM Home Information in properties file",
			                "LIM",
			                Severity.UnRecoverable);
			    }	
			 
			LimClient limClient =
					new LimClient( aProviderURL, aLimHome, "LIM", (String)aLimBase.getPROPERTIES().get("INITIAL_CONTEXT_PROPERTIES_FILE"));
				
			Lim limBean = limClient.getLimConnection(aBisContext,(Utility)aLimBase, ExceptionCode.ERR_LIMCL_REMOTE_EXCEPTION, "LIM Connection Failed");
			
			rlfseraReturn =
				limBean.retrieveLocationForServiceAddress(
					getBisContext(),
					address,
					getServiceAddressLocationPropertiesToGet(),
					IDLUtil.toOpt (""),
					IDLUtil.toOpt (""),
					maximumBasicAddressReturnLimit,
					maximumUnitReturnLimit);
					
			limBisContext = rlfseraReturn.aContext;
			
			if (limClient != null)
				limClient = null;
			
			if (limBean != null)
				limBean = null;

		}	
			
		catch (RemoteException e)
	     {
		throwSystemFailureException(
				aLimBase,
				"Exception encountered trying to find Lim. Exception message: " + e.getMessage());
	   }	
		
    	catch(AccessDenied e)
    	{   
    		limBisContext = e.aContext;
    		aLimBase.log(
    			LogEventId.FAILURE, 
    			e.aExceptionData.aCode,
    			e.aExceptionData.aDescription,
    			e.aExceptionData.aOrigination.theValue());
    		e.aContext = aLimBase.getCallerContext();
    		throw e;
    	}
    	catch(BusinessViolation e)
    	{
    		limBisContext = e.aContext;
    		aLimBase.log(
    			LogEventId.FAILURE, 
    			e.aExceptionData.aCode,
    			e.aExceptionData.aDescription,
    			e.aExceptionData.aOrigination.theValue());
    		e.aContext = aLimBase.getCallerContext();
    		throw e;
    	}
    	catch(DataNotFound e)
    	{
    		limBisContext = e.aContext;
    		aLimBase.log(
    			LogEventId.FAILURE, 
    			e.aExceptionData.aCode,
    			e.aExceptionData.aDescription,
    			e.aExceptionData.aOrigination.theValue());
    		e.aContext = aLimBase.getCallerContext();
    		throw e;
    	}
    	catch(InvalidData e)
    	{
    		limBisContext = e.aContext;
    		aLimBase.log(
    			LogEventId.FAILURE, 
    			e.aExceptionData.aCode,
    			e.aExceptionData.aDescription,
    			e.aExceptionData.aOrigination.theValue());
    		e.aContext = aLimBase.getCallerContext();
    		throw e;
    	}
    	catch(NotImplemented e)
    	{
    		limBisContext = e.aContext;
    		aLimBase.log(
    			LogEventId.FAILURE, 
    			e.aExceptionData.aCode,
    			e.aExceptionData.aDescription,
    			e.aExceptionData.aOrigination.theValue());
    		e.aContext = aLimBase.getCallerContext();
    		throw e;
    	}
    	catch(ObjectNotFound e)
    	{
    		limBisContext = e.aContext;
    		aLimBase.log(
    			LogEventId.FAILURE, 
    			e.aExceptionData.aCode,
    			e.aExceptionData.aDescription,
    			e.aExceptionData.aOrigination.theValue());
    		e.aContext = aLimBase.getCallerContext();
    		throw e;
    	}
    	catch(SystemFailure e)
    	{
    		limBisContext = e.aContext;
    		aLimBase.log(
    			LogEventId.FAILURE, 
    			e.aExceptionData.aCode,
    			e.aExceptionData.aDescription,
    			e.aExceptionData.aOrigination.theValue());
    		e.aContext = aLimBase.getCallerContext();
    		throw e;
    	}
    	finally
		{
			String loggingInfo = "";
			if(limBisContext != null)
			{
				BisContextManager theBisContextManager = new BisContextManager(limBisContext);
				loggingInfo = theBisContextManager.getLoggingInformationString();
				theBisContextManager = null;
				limBisContext = null;
			}

			aLimBase.log(
				LogEventId.REMOTE_RETURN,
				"LIM-BIS",
				"Lim EJB",
				"LIM RetrieveLocationForServiceAddress",  
				"Logging information:" + loggingInfo); 
			
			
		}

		try
		{
			/* If response is an an exact match, then return.
			 * If response is a list of alternative addresses, then convert to a list of alternative locations and return. 
			 * If response is a list of ranged addresses, then throw an exception.
			 */
			if (rlfseraReturn != null &&
			    rlfseraReturn.aServiceAddressMatchResult != null)
			{
			    if (rlfseraReturn.aServiceAddressMatchResult.discriminator().value() == AddressMatchResultChoice._ALTERNATIVE)
			    {
			        if (rlfseraReturn
						.aServiceAddressMatchResult
						.aAlternativeServiceAddresses() != null
						&&
						rlfseraReturn
						.aServiceAddressMatchResult
						.aAlternativeServiceAddresses().length > 0)
			        {
			            if (rlfseraReturn
						    .aServiceAddressMatchResult
						    .aAlternativeServiceAddresses()[0]
						    .discriminator()
						    .value()
						    == AlternativeServiceAddressChoice._ALTERNATIVE_LOCATION2)
			            {
			                if (rlfseraReturn
							    .aServiceAddressMatchResult
							    .aAlternativeServiceAddresses()[0]
							    .aServiceLocation() != null
			                    &&
			                    rlfseraReturn
						        .aServiceAddressMatchResult
						        .aAlternativeServiceAddresses()[0]
						        .aServiceLocation()
						        .aServiceAddress != null)
			                {
			                    if (rlfseraReturn
					                .aServiceAddressMatchResult
					                .aAlternativeServiceAddresses()[0]
					                .aServiceLocation()
					                .aServiceAddress
					                .discriminator()
					                .value()
					                == Address2Choice._RANGED_ADDRESS2)
			                    {
			                        /* Ranged address response was received. 
			                         * Throw an exception.
			                         */
			                        aLimBase.log(
						                LogEventId.DEBUG_LEVEL_2,
										"ServiceAddressValidationHelper::doServiceAddressValidion(): Ranged addresses received from regional system.");

			                        OvalsNspResponseFactory.handleMsagValidationAddressSAGNotMatchMSAG(aLimBase);
			                    }
			                    else
			                    {
			                        /* Alternative listed addresses response was received.
			                         * Convert to altervative locations response.
			                         */
			                        isListAddress = true;
			                
			                        aLimBase.log(
						                LogEventId.DEBUG_LEVEL_2,
										"ServiceAddressValidationHelper::doServiceAddressValidion(): Alternative list of addresses received from regional system.");

			                        AlternativeServiceAddress[] altServiceAddr =
			                            rlfseraReturn.aServiceAddressMatchResult.aAlternativeServiceAddresses();

			                        ServiceAddressListResp resp =
			                            new ServiceAddressListResp(
							                aLimBase,
							                new RetrieveLocReq(aLimBase, new AddressHandler(), locationPropertiesToGet),
							                altServiceAddr);
					
			                        resp.setMaxAddresses(maximumBasicAddressReturnLimit.theValue()); 
			                        resp.processListResponse();

			                        rlfaReturn = resp.toAddressReturn();
			                        altServiceAddr = null;
			                    }
			                }
			                else
			                {
			                    //It should never happen in here
			                    //In ALTERNATIVE_LOCATION2, ServiceLocation.aServiceAddress is null 
			                    throwSystemFailureException(
							        aLimBase,
								    "retrieveLocationForServiceAddress returned null ServiceLocation or aServiceAddress.");
			                }
			            }
			            else
			            {
			                //It should never happen in here
			                //ALTERNATIVE_ADDRESS2 is not used by listed or ranged address
			                throwSystemFailureException(
							    aLimBase,
								"retrieveLocationForServiceAddress returned AlternativeServiceAddresses.ALTERNATIVE_ADDRESS2.");
			            }
			        }
			        else
			        {
			            //It should never happen in here.
					    throwSystemFailureException(
							aLimBase,
							"retrieveLocationForServiceAddress alternative list returned a null or zero length aAlternativeServiceAddresses.");
			        }
			    }
			    else
			    {
			        /* Exact match response, return. */
			        isExactMatch = true;
			        
			        aLimBase.log(
					    LogEventId.DEBUG_LEVEL_2,
						"ServiceAddressValidationHelper::doServiceAddressValidion(): Exact match received from regional system.");
			        
			        if (rlfseraReturn
			            .aServiceAddressMatchResult
			            .aServiceLocation() == null
			            ||
			            rlfseraReturn
			            .aServiceAddressMatchResult
			            .aServiceLocation()
			            .aServiceAddress == null)
			        {
			            //It should never happen in here.
					    throwSystemFailureException(
							aLimBase,
							"retrieveLocationForServiceAddress exact match returned a null aServiceLocation or aServiceAddress.");
			        }
			    }
			}
			else
			{
			    //It should never happen in here.
			    throwSystemFailureException(
					aLimBase,
					"retrieveLocationForServiceAddress returned a null value.");
			}
		}
		catch (org.omg.CORBA.BAD_OPERATION e)
		{
			throwSystemFailureException(
				aLimBase,
				"CORBA BAD_OPERATION exception encountered.  Exception message: " + e.getMessage());
		}
	}

	/**
	 * Throw a SystemFailureException
	 * @param utility LIMBase
	 * @param msg String
	 */
	public static void throwSystemFailureException(LIMBase utility, String msg)
		throws InvalidData, AccessDenied, BusinessViolation, DataNotFound, SystemFailure, NotImplemented, ObjectNotFound
	{
		Properties p = new Properties();
		p.setProperty("MSG", msg);

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
	
    /**
     * @return Returns the isExactMatch.
     */
    public boolean isExactMatch()
    {
        return isExactMatch;
    }
    
    /**
     * @return boolean the isListAddress.
     */
    public boolean isListAddress()
    {
        return isListAddress;
    }
    
    /**
     * @return RetrieveLocationForAddressReturn the rlfaReturn.
     */
    public RetrieveLocationForAddressReturn getRlfaReturn()
    {
        return rlfaReturn;
    }
    
    /**
     * Convert Exact Match Address2 to Address
     * @return Address the Address.
     */
    public Address getExactMatchAddress()
    {
        if (isExactMatch)
        { 
            return LIMIDLUtil.convertAddress2ToAddress(
                   rlfseraReturn
                   .aServiceAddressMatchResult
                   .aServiceLocation()
                   .aServiceAddress);   
        }
        else
        {
            return null;
        }
    }
}
