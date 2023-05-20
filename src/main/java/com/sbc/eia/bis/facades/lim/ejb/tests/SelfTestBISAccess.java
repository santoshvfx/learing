//$Id: SelfTestBISAccess.java,v 1.9 2009/05/14 16:48:13 am9643 Exp $
//############################################################################
//#
//# Copyright Notice:
//#
//#     RESTRICTED - PROPRIETARY INFORMATION
//#      The information herein is for use only by authorized employees
//#      of SBC Services Inc. and authorized Affiliates of SBC Services,
//#      Inc., and is not for general distribution within or outside the
//#      respective companies.
//#      Copying or reproduction without prior written approval is prohibited.
//#
//#      � 2002-2005 SBC Knowledge Ventures, L.P. All rights reserved.
//#
//# History :
//# Date      | Author              | Notes
//# ------------------------------------------------------------------------
//# 05/22/2008  Noemi Luzung        Creation Selftest and Ping Enhancement 
//# 								to call the Downstream BIS Dependencies


package com.sbc.eia.bis.facades.lim.ejb.tests;

import java.io.File;
import java.io.FileInputStream;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;

import com.sbc.bccs.utilities.PropertiesFileLoader;
import com.sbc.bccs.utilities.Utility;
import com.sbc.billing.bcam.BCAM;
import com.sbc.eia.bis.BusinessInterface.bcam.BcamService;
import com.sbc.eia.bis.RmNam.utilities.BimxClient.BimxClient;
import com.sbc.eia.bis.RmNam.utilities.SmClient.SmClient;
import com.sbc.eia.bis.framework.logging.BisLogger;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.bis.lim.client.LimClientException;
import com.sbc.eia.bis.lim.util.LIMBase;
import com.sbc.eia.bis.selftest.SelfTestResult;
import com.sbc.eia.idl.bimx.BimxFacade;
import com.sbc.eia.idl.bimx.PingReturn;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.exception_types.ExceptionCode;
import com.sbc.eia.idl.sm.SmFacade;
import com.sun.java.util.collections.ArrayList;
import com.sbc.eia.idl.types.ObjectProperty;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class SelfTestBISAccess
{
	private String PING_TEST = "ping()";
	private String RESULT_TEST_TYPE = "BIS";
	private String ACCESS_FAILED = "BIS Access Failed.";
	private String CONNECT_FAILED = "Connection Failed.";
	private String PING_RETURN = "Ping returned successfully.";
	private String ACCESS_SUCCESSFUL = "BIS Access Tested Successfully.";
	private FileInputStream fInput = null;
	private Properties m_testClientProperties = null;

	private SmFacade smBean;

	/**
	 * Constructor for selfTestBISAccess.
	 * @throws LimClientException 
	 */
	public SelfTestBISAccess(
		String selfTestServicesFile,
		BisContext aContext,
		Hashtable aProperties,
		BisLogger bisLogger,
		Utility aUtility,
		ArrayList aResultList) throws NamingException {
		String myMethodName = "selfTestBISAccess::selfTestBISAccess()";
		String strValBIMX = "";
		String strValSM = "";
		String strValBCAM = "";
		boolean testBIMX = false;
		boolean testSM = false;
		boolean testBCAM = false;
		
		loadProperties(selfTestServicesFile);
		
		strValBIMX = (String)m_testClientProperties.get("BIS_BIMX");
		strValSM = (String)m_testClientProperties.get("BIS_SM");
		strValBCAM = (String)m_testClientProperties.get("BIS_BCAM");
		testBIMX =  Boolean.valueOf(strValBIMX.trim()).booleanValue();
		testSM = Boolean.valueOf(strValSM.trim()).booleanValue();
		testBCAM = Boolean.valueOf(strValBCAM.trim()).booleanValue();
		
		if (testSM)
		{
			bisLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);
			this.SM_SelfTest(aContext, aProperties, bisLogger, aUtility, aResultList);
			bisLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
		}
		if (testBIMX)
		{
			bisLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);
			this.BIMX_SelfTest(aContext, aProperties, bisLogger, aUtility, aResultList);
			bisLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
		}
		if (testBCAM)
		{
			this.BCAM_NewTest(aContext, aProperties, bisLogger, aUtility, aResultList);
			bisLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);
			this.BCAM_SelfTest(aContext, aProperties, bisLogger, aUtility, aResultList);
			bisLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
		}
	}
	
	protected void log(String s)
	{
		System.out.println(s);
	}	
		
	protected void loadProperties(String propsFile) 
	{
			
		try 
		{
			log("Try to load client properties using File IO: " + propsFile);
			fInput = new FileInputStream(new File(propsFile));
			m_testClientProperties = new Properties();
			m_testClientProperties.load(fInput);
		} 
		catch (Exception e)
		{
			log("Failed to load client properties using File IO: " + e.getMessage());
			log("Try to load client properties using PropertiesFileLoader: " + propsFile);
			try
			{
				m_testClientProperties = PropertiesFileLoader.read(propsFile, null);
			}
			catch(Exception ex)
			{
				log("Exception in loading propertiesFileName <" + propsFile + "> " + e.getMessage());
			}	
		} 
		finally 
		{
			try 
			{
				fInput.close();
			}
			catch (Exception e) {}
		}
	}	

	
	/**
	 * Method BIMX_SelfTest.
	 * @param aContext
	 * @param bisLogger
	 * @param aUtility
	 * @param aResultList
	 */
	
	private void BIMX_SelfTest(
		BisContext aContext,
		Hashtable aProperties,
		BisLogger bisLogger,
		Utility aUtility,
		ArrayList aResultList)
	{
		String myMethodName = "selfTestBISAccess::BIMX_SelfTest()";
		bisLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);
		try
		{
			BimxClient theBIMXClient =
				new BimxClient();
//					(String) aProperties.get("BIMX_PROVIDER_URL"),
//					(String) aProperties.get("BIMX_Home"),
//					(String) aProperties.get("BIS_NAME"),
//					(String) aProperties.get("INITIAL_CONTEXT_PROPERTIES_FILE"));

			bisLogger.log(
				LogEventId.REMOTE_CALL,
//				(String) aProperties.get("BIMX_PROVIDER_URL"),
//				(String) aProperties.get("BIMX_Home"),
//				(String) aProperties.get("BIMX_Home"),
				PING_TEST);

			BimxFacade bimxBean =
				theBIMXClient.getBimxConnection(
					aContext,
					aUtility,
					"BIMXException",
					"BIMX " + CONNECT_FAILED);

			
			ObjectProperty[] objectProp = new ObjectProperty[1];
			objectProp[0] = new ObjectProperty("Application","LIMBIS");
			BisContext bisContext = new BisContext(objectProp);				
			PingReturn aPingReturn = bimxBean.ping(bisContext);
			bisLogger.log(LogEventId.REMOTE_RETURN, PING_RETURN);
			bisLogger.log(LogEventId.INFO_LEVEL_1, "BIMX " + ACCESS_SUCCESSFUL);

			

			SelfTestResult.addResultToList(
				RESULT_TEST_TYPE,
				"BIMX " + ACCESS_SUCCESSFUL,
				true,
				bisLogger,
				aResultList);
		}
		catch (Exception e)
		{
			bisLogger.log(LogEventId.FAILURE, "BIMX " + ACCESS_FAILED);
			SelfTestResult.addResultToList(
				RESULT_TEST_TYPE,
				"BIMX " + ACCESS_FAILED, 
				false,
				bisLogger,
				aResultList);
		}
		finally
		{
			bisLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
		}
	}

	/**
	 * Method SM_SelfTest.
	 * @param aContext
	 * @param bisLogger
	 * @param aUtility
	 * @param aResultList
	 */
	private void SM_SelfTest(
		BisContext aContext,
		Hashtable aProperties,
		BisLogger bisLogger,
		Utility aUtility,
		ArrayList aResultList)
	{
		String myMethodName = "selfTestBISAccess::SM_SelfTest()";
		bisLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);
		try
		{
			SmClient theSMClient =
				new SmClient();
//					(String) aProperties.get("SM_PROVIDER_URL"),
//					(String) aProperties.get("SM_Home"),
//					(String) aProperties.get("BIS_NAME"),
//					(String) aProperties.get("INITIAL_CONTEXT_PROPERTIES_FILE"));

			bisLogger.log(
				LogEventId.REMOTE_CALL,
//				(String) aProperties.get("SM_PROVIDER_URL"),
//				(String) aProperties.get("SM_Home"),
//				(String) aProperties.get("SM_Home"),
				PING_TEST);

			SmFacade smBean =
				theSMClient.getSmConnection(
					aContext,
					aUtility,
					ExceptionCode.ERR_SMCL_REMOTE_EXCEPTION,
					"SM " + CONNECT_FAILED);

			com.sbc.eia.idl.sm.PingReturn aPingReturn = smBean.ping(aContext);

			bisLogger.log(LogEventId.REMOTE_RETURN, PING_RETURN);
			bisLogger.log(LogEventId.INFO_LEVEL_1, "SM " + ACCESS_SUCCESSFUL);

			SelfTestResult.addResultToList(
				RESULT_TEST_TYPE,
				"SM " + ACCESS_SUCCESSFUL,
				true,
				bisLogger,
				aResultList);
		}
		catch (Exception e)
		{
			
			bisLogger.log(LogEventId.FAILURE, "SM " + ACCESS_FAILED);
			SelfTestResult.addResultToList(
				RESULT_TEST_TYPE,
				"SM " + ACCESS_FAILED, 
				false,
				bisLogger,
				aResultList);
		}
		finally
		{
			bisLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
		}
	}

	private void BCAM_NewTest(
			BisContext aContext,
			Hashtable aProperties,
			BisLogger bisLogger,
			Utility aUtility,
			ArrayList aResultList
	) throws NamingException {
			BCAM bcamProcessor = lookupBcamEJB();
			String acctNumber = "6088832217230";

			try {
				List acctVerifications = bcamProcessor.findBCDAccountVerification(acctNumber, false);
			}catch(Exception e) {
				e.printStackTrace(System.err);
			}

		}

	private static BCAM lookupBcamEJB() throws NamingException {
//		final Hashtable<String, String> jndiProperties = new Hashtable<>();
//		jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
//		jndiProperties.put(Context.PROVIDER_URL, "remote+https://bds-eastus2-stge-web-vm-2.az.3pc.att.com:443");
//		final Context context = new InitialContext(jndiProperties);

		Properties p = new Properties();
		p.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
		p.put(Context.PROVIDER_URL, "remote+https://bds-eastus2-stge-web-vm-2.az.3pc.att.com:443");
		Context context = new InitialContext(p);
		return (BCAM) context.lookup("ejb:BCAM-EAR/BCAM-EJB/BCAM!com.sbc.billing.bcam.BCAM");
	}


	/**
	 * Method BIMX_SelfTest.
	 * @param aContext
	 * @param bisLogger
	 * @param aUtility
	 * @param aResultList
	 */

	private void BCAM_SelfTest(
			BisContext aContext,
			Hashtable aProperties,
			BisLogger bisLogger,
			Utility aUtility,
			ArrayList aResultList)
	{
		String myMethodName = "selfTestBISAccess::BCAM_SelfTest()";
		bisLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);
		try
		{
			LIMBase base = new LIMBase();
			base.setPROPERTIES(aProperties);
			base.setCallerContext(aContext);
			base.initLIMBase();
			BcamService bcamService = new BcamService(base);
			bcamService.getBCAMBean();
		}
		catch (Exception e)
		{
			bisLogger.log(LogEventId.FAILURE, "BCAM " + ACCESS_FAILED);
			SelfTestResult.addResultToList(
					RESULT_TEST_TYPE,
					"BCAM " + ACCESS_FAILED,
					false,
					bisLogger,
					aResultList);

			SelfTestResult.addResultToList(
					RESULT_TEST_TYPE,
					"BCAM " + ACCESS_SUCCESSFUL,
					true,
					bisLogger,
					aResultList);
		}
		finally
		{
			bisLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
		}
	}
}