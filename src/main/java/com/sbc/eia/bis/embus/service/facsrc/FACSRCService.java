//$Id: FACSRCService.java,v 1.3 2009/02/04 18:03:54 js7440 Exp $
 						
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
//#      © 2002-2007 SBC Knowledge Ventures, L.P. All rights reserved.
//#
//# History :
//# Date      | Author              | Notes
//# --------------------------------------------------------------------
//# 1/8/2007  | Manjula Goniguntla	| create
//# 02/04/2008  Shyamali Banerjee      LS7
//# 01/14/2009  Julius Sembrano        LS10
                              
package com.sbc.eia.bis.embus.service.facsrc;																																																								

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import com.sbc.bccs.utilities.Logger;
import com.sbc.eia.bis.embus.service.access.ServiceException;
import com.sbc.eia.bis.embus.service.facsrc.InqOspRequest.impl.NINQImpl;
import com.sbc.eia.bis.embus.service.facsrc.InqTermRequest.impl.INQTERMImpl;
import com.sbc.eia.bis.embus.service.facsrc.InqfasgRequest.impl.INQFASGImpl;



/**
 * @author mg5629
 *
 */
public class FACSRCService {

	FACSRCHelper theFACSRCHelper;

	Properties theProperties;
	Logger theLogger;

	/**
	 * Method FACSRCService.
	 * @throws ServiceException
	 */

	public FACSRCService() throws ServiceException {
		theFACSRCHelper = null;
	}

	/**
	 * @param hash
	 * @param logger
	 * @throws ServiceException
	 */
	public FACSRCService(Hashtable hash, Logger logger)
		throws ServiceException {

		this();

		theLogger = logger;
		theProperties = new Properties();

		// Convert the hash to a properties.

		Set set = hash.entrySet();
		Iterator it = set.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			theProperties.setProperty(
				(String) entry.getKey(),
				(String) entry.getValue());
		}

	}

	/**
	 * @param properties
	 * @param logger
	 * @throws ServiceException
	 */
	public FACSRCService(Properties properties, Logger logger)
		throws ServiceException {

		this();

		theLogger = logger;
		theProperties = properties;

	}

	/**
	 * @param request
	 * @param jmsRequstProperties
	 * @param jmsResponseProperties
	 * @return
	 * @throws ServiceException
	 */
	public Object facsrcRequest(
		INQFASGImpl request,
		Properties jmsRequestProperties,
		Properties jmsResponseProperties)
		throws ServiceException {

		if (theFACSRCHelper == null) {
			theFACSRCHelper = new FACSRCHelper(theProperties, theLogger);
		}

		return theFACSRCHelper.facsrcRequest(
			request,
			jmsRequestProperties,
			jmsResponseProperties);

	}
	
	/**
	 * @param request
	 * @param jmsRequstProperties
	 * @param jmsResponseProperties
	 * @return
	 * @throws ServiceException
	 */
	public Object ninqFacsrcRequest(
		   NINQImpl request,
		   Properties jmsRequestProperties,
		   Properties jmsResponseProperties)
		   throws ServiceException {

		   if (theFACSRCHelper == null) 
		   {
			theFACSRCHelper = new FACSRCHelper(theProperties, theLogger);
		   }

		   return theFACSRCHelper.ospfacsrcRequest(
			      request,
			      jmsRequestProperties,
			      jmsResponseProperties);

	}

	/**
	 * @param request
	 * @param jmsRequstProperties
	 * @param jmsResponseProperties
	 * @return String
	 * @throws ServiceException
	 */
	public String inqtermFacsrcRequest(
		   INQTERMImpl request,
		   Properties jmsRequestProperties,
		   Properties jmsResponseProperties)
		   throws ServiceException {

		   if (theFACSRCHelper == null) 
		   {
			theFACSRCHelper = new FACSRCHelper(theProperties, theLogger);
		   }

		   return theFACSRCHelper.inqtermfacsrcRequest(
			      request,
			      jmsRequestProperties,
			      jmsResponseProperties);

	}
}
