//$Id: FACSRCHelper.java,v 1.7 2009/02/13 19:32:32 js7440 Exp $

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
//# Date      | Author               | Notes
//# --------------------------------------------------------------------
//# 01/08/2007  | Manjula Goniguntla	 | create
//# 03/14/2007  | Manjula Goniguntla	 | Changed INQFASG_REQUEST value
//# 04/03/2007  | Prasad Ganji			 | Changed INQFASG_REQUEST value and FACS RC Request and Response
//# 02/04/2008  | Shyamali Banerjee      | LS 7
//# 01/22/2009  | Julius Sembrano        | LS 10

package com.sbc.eia.bis.embus.service.facsrc;

import java.util.Hashtable;
import java.util.Properties;

import javax.xml.bind.Marshaller;

import com.sbc.bccs.utilities.Logger;
import com.sbc.eia.bis.embus.service.access.ServiceException;
import com.sbc.eia.bis.embus.service.access.ServiceHelperSelfTestException;
import com.sbc.eia.bis.embus.service.decryption.DecryptionServiceHelper;
import com.sbc.eia.bis.embus.service.facsrc.InqOspRequest.NINQ;
import com.sbc.eia.bis.embus.service.facsrc.InqOspRequest.impl.NINQImpl;
import com.sbc.eia.bis.embus.service.facsrc.InqOspResponse.NINQType;
import com.sbc.eia.bis.embus.service.facsrc.InqTermRequest.impl.INQTERMImpl;
import com.sbc.eia.bis.embus.service.facsrc.InqfasgRequest.INQFASG;
import com.sbc.eia.bis.embus.service.facsrc.InqfasgRequest.impl.INQFASGImpl;
import com.sbc.eia.bis.embus.service.facsrc.InqfasgResponse.impl.ResponseMessageImpl;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.idl.bis_types.BisContext;


/**
 * @author mg5629
 *
 */

public class FACSRCHelper extends DecryptionServiceHelper {
	public final static String FACSRC_SERVICE_NAME = "FACS_RC";
	public static final String FACS_RC_VERSION = "3.0";
	public final static String INQFASG_REQUEST = "INQFASGRequest";
	public final static String INQOSP_REQUEST = "INQOSPRequest";
	public final static String INQTERM_REQUEST = "INQTERMRequest";
	private Properties marshalUnmarshalOptions;
	private Properties properties;

	/**
	 * @param properties
	 * @param logger
	 * @throws ServiceException
	 */
	public FACSRCHelper(Hashtable properties, Logger logger)
		throws ServiceException {
		super(properties, logger, FACSRC_SERVICE_NAME);

		this.properties = new Properties();
		this.properties.putAll(properties);

		// Set marshalUnmarshalOptions properties.
		marshalUnmarshalOptions = new Properties();
		marshalUnmarshalOptions.setProperty(
			Marshaller.JAXB_FORMATTED_OUTPUT,
			"true");
	}

	/**
	 * @param request
	 * @param jmsRequstProperties
	 * @param jmsResponseProperties
	 * @return
	 * @throws ServiceException
	 */
	//requet for RetrieveCustomerTransportInfo()
	public Object facsrcRequest(
		INQFASGImpl request,
		Properties jmsRequstProperties,
		Properties jmsResponseProperties)
		throws ServiceException {

		// Set the encoder for the request class.
		getServiceAccess().setEncoder(
			new FACSRCEncoderDecoder(
				com.sbc.eia.bis.embus.service.facsrc.InqfasgRequest.INQFASG
					.class
					.getPackage()
					.getName(),
				marshalUnmarshalOptions));

		// Set the decoder for the response class.
		getServiceAccess().setDecoder(
			new FACSRCEncoderDecoder(
				com.sbc.eia.bis.embus.service.facsrc.InqfasgResponse.INQFASG
					.class
					.getPackage()
					.getName(),
				marshalUnmarshalOptions));

		return getServiceAccess().sendAndReceive(
			INQFASG_REQUEST,
			new Object[] { request },
			jmsRequstProperties,
			jmsResponseProperties)[0];

	}
	
	/**
	 * @param request
	 * @param jmsRequstProperties
	 * @param jmsResponseProperties
	 * @return
	 * @throws ServiceException
	 */
	public Object ospfacsrcRequest(
			NINQImpl request,
			Properties jmsRequstProperties,
			Properties jmsResponseProperties)
			throws ServiceException {

			// Set the encoder for the request class.
			getServiceAccess().setEncoder(
				new FACSRCEncoderDecoder(
						com.sbc.eia.bis.embus.service.facsrc.InqOspRequest.NINQ.class.getPackage().getName(),
					marshalUnmarshalOptions));

			// Set the decoder for the response class.
			getServiceAccess().setDecoder(
				new FACSRCEncoderDecoder(
						com.sbc.eia.bis.embus.service.facsrc.InqOspResponse.NINQType.class.getPackage().getName(),
					marshalUnmarshalOptions));

			return getServiceAccess().sendAndReceive(
				INQOSP_REQUEST,
				new Object[] { request },
				jmsRequstProperties,
				jmsResponseProperties)[0];

		}

	/**
	 * @param request
	 * @param jmsRequstProperties
	 * @param jmsResponseProperties
	 * @return
	 * @throws ServiceException
	 */
	public String inqtermfacsrcRequest(
			INQTERMImpl request,
			Properties jmsRequestProperties,
			Properties jmsResponseProperties)
			throws ServiceException {

			// Set the encoder for the request class.
			getServiceAccess().setEncoder(
				new FACSRCEncoderDecoder(
						com.sbc.eia.bis.embus.service.facsrc.InqTermRequest.INQTERM.class.getPackage().getName(),
					marshalUnmarshalOptions));

			// Set the decoder for the response class.
			getServiceAccess().setDecoder(
				new FACSRCEncoderDecoder(
						com.sbc.eia.bis.embus.service.facsrc.InqTermResponse.INQTERM.class.getPackage().getName(),
					marshalUnmarshalOptions));

			return getServiceAccess().sendAndReceive(
					new Object[] { request },
					INQTERM_REQUEST,
					jmsRequestProperties,
					jmsResponseProperties,
					null);

		}


	/**
	 * Method selfTest.
	 */
	public BisContext selfTest(BisContext aBisContext) throws ServiceException
	{

		log(LogEventId.DEBUG_LEVEL_2, "ServiceHelper::selfTest started.");

		try
		{
			INQFASGImpl.REQTypeImpl req = new INQFASGImpl.REQTypeImpl();
			req.setEMP("AOTL");
			req.setCA("1");
			req.setPR("1");

			INQFASGImpl request = new INQFASGImpl();
			request.setREQ((INQFASG.REQType)req);

			Properties jmsRequestProperties = BisToFACSRCMapping.mapProperties(
					aBisContext, "cipam","314231","INQFASG", this.properties);

			ResponseMessageImpl response = (ResponseMessageImpl)this.facsrcRequest(request, jmsRequestProperties, new Properties());
		}
		catch(Exception e)
		{
			log(LogEventId.DEBUG_LEVEL_2, "ServiceHelper::selfTest failed. Exception message: " + e.getMessage());
			throw new ServiceHelperSelfTestException("ServiceHelper::selfTest failed! " + e.getMessage(),	e);
		}

		log(LogEventId.DEBUG_LEVEL_2, "ServiceHelper::selfTest completed.");

		return aBisContext;
	}
}