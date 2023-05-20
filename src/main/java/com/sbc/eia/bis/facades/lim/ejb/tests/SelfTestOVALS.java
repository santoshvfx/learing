//$Id: SelfTestOVALS.java,v 1.1 2008/06/25 14:34:08 nl9267 Exp $
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
//# --------------------------------------------------------------------
//# 12/19/2006  Doris Sunga             Creation.
//# 02/28/2008  Julius Sembrano			SelfTest Enhancements

package com.sbc.eia.bis.facades.lim.ejb.tests;

import java.lang.reflect.Constructor;
import java.util.Enumeration;
import java.util.Hashtable;

// TODO Removed ServiceName import
//import com.ibm.security.krb5.internal.ServiceName;
import com.sbc.eia.bis.embus.service.access.ServiceHelper;
//import com.sbc.gwsvcs.access.vicuna.ServiceHelper;
//added by js7440
//import com.sbc.eia.bis.embus.service.bbnms.BBNMSHelper;
//import com.sbc.eia.bis.embus.service.codes.access.CodesRCAccessHelper;
//import com.sbc.eia.bis.embus.service.facsrc.FACSRCHelper;
//import com.sbc.eia.bis.embus.service.first.FIRSTHelper;
//import com.sbc.eia.bis.embus.service.netprovision.access.NetProvisionHelper;
//import com.sbc.eia.bis.embus.service.npconnector.access.NpConnectorHelper;
//import com.sbc.eia.bis.embus.service.oms.access.OMSHelper;
//import com.sbc.eia.bis.embus.service.soa.SOAHelper;
//import com.sbc.eia.bis.embus.service.xng.access.XNGHelper;
import com.sbc.bccs.utilities.Logger;
import com.sbc.eia.bis.framework.logging.BisLogger;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.bis.selftest.SelfTestResult;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sun.java.util.collections.ArrayList;


public class SelfTestOVALS
{
		
					
	/**
	 * Constructor for selfTestOVALS.
	 */
		
	public SelfTestOVALS(
			String fileName,
			BisContext aContext, 
			Hashtable aProperties, 
			Logger aLogger,
			BisLogger bisLogger, 
			ArrayList aResultList)
	{
		super();
		String myMethodName = "selfTestOVALS::selfTestOVALS()";
		bisLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);
		
		Hashtable embusServices = FileLoader.loadFileContents(fileName, "OVALS",  bisLogger);
		String service = "";
		String serviceName = "";
		Enumeration enumServices = embusServices.keys();
		while(enumServices.hasMoreElements())
		{
			try
			{
				service = (String) enumServices.nextElement();
				serviceName = service.substring((service.lastIndexOf("_")+1), service.length());
				
				// Obtain helper class instance
				Class myHelper = Class.forName(embusServices.get(service).toString());

				Constructor C =
					myHelper.getConstructor(
						new Class[] {
							Hashtable.class,
							Logger.class });

				ServiceHelper aServiceHelper =
					(ServiceHelper) C.newInstance(new Object[] { aProperties, aLogger });
				
				aServiceHelper.selfTest(aContext);
				
				bisLogger.log(
					LogEventId.INFO_LEVEL_1,
					serviceName + " Access Tested Successfully");
					
				SelfTestResult.addResultToList(
					"OVALS",
					serviceName + " Access Tested Successfully.",
					true,
					bisLogger,
					aResultList);
			}
			catch (Exception e)
			{
				bisLogger.log(LogEventId.FAILURE, serviceName + " Access Failed");
				SelfTestResult.addResultToList(
					"OVALS",
					serviceName + " Access Failed. " + e.getMessage(),
					false,
					bisLogger,
					aResultList);
			}
		}

		bisLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
	}
}