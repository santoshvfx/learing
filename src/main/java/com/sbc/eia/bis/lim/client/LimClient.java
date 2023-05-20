//$Id: LimClient.java,v 1.12 2009/02/03 18:43:12 jv7958 Exp $
package com.sbc.eia.bis.lim.client;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;

import com.sbc.bccs.utilities.PropertiesFileLoader;
import com.sbc.eia.bis.facades.lim.ejb.Lim;
import com.sbc.eia.bis.facades.lim.ejb.LimHome;
import com.sbc.eia.idl.lim.FieldAddressReturn;
import com.sbc.eia.idl.lim.PingReturn;
import com.sbc.eia.idl.lim.RetrieveLocationForPostalAddress2Return;
import com.sbc.eia.idl.lim.RetrieveLocationForTelephoneNumberReturn;
import com.sbc.eia.idl.lim.RetrieveLocationForE911AddressReturn;
import com.sbc.eia.idl.lim.RetrieveLocationForGeoAddressReturn;
import com.sbc.eia.idl.lim.RetrieveLocationForPostalAddressReturn;
import com.sbc.eia.idl.lim.RetrieveLocationForServiceAddressReturn;
import com.sbc.eia.idl.lim.RetrieveLocationForAddressReturn;
import com.sbc.eia.idl.lim.RetrieveLocationForServiceReturn;
import com.sbc.eia.idl.lim.RetrieveServiceAreaByPostalCodeReturn;
import com.sbc.eia.idl.lim.SelfTestReturn;
import com.sbc.eia.idl.lim.LimFacadePackage._fieldAddressRequest;
import com.sbc.eia.idl.lim.LimFacadePackage._pingRequest;
import com.sbc.eia.idl.lim.LimFacadePackage._retrieveLocationForPostalAddress2Request;
import com.sbc.eia.idl.lim.LimFacadePackage._retrieveLocationForTelephoneNumberRequest;
import com.sbc.eia.idl.lim.LimFacadePackage._retrieveLocationForE911AddressRequest;
import com.sbc.eia.idl.lim.LimFacadePackage._retrieveLocationForGeoAddressRequest;
import com.sbc.eia.idl.lim.LimFacadePackage._retrieveLocationForPostalAddressRequest;
import com.sbc.eia.idl.lim.LimFacadePackage._retrieveLocationForServiceAddressRequest;
import com.sbc.eia.idl.lim.LimFacadePackage._retrieveLocationForAddressRequest;
import com.sbc.eia.idl.lim.LimFacadePackage._retrieveLocationForServiceRequest;
import com.sbc.eia.idl.lim.LimFacadePackage._retrieveServiceAreaByPostalCodeRequest;
import com.sbc.eia.idl.lim.LimFacadePackage._selfTestRequest;
import com.sbc.eia.idl.lim.LimFacadePackage._updateBillingAddressRequest;
import com.sbc.eia.idl.lim.UpdateBillingAddressReturn;


/**
 * @author gg2712
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class LimClient
{
	public static final String BIS_HOME_KEY="BIS_HOME";

	protected Properties m_testClientProperties = null; 
	protected Lim m_limBean;
	protected TestCaseHelper m_testCaseHelper = null;
	protected String m_bisHome = null;
	protected String m_providerURL = null ;
	
	/**
	 * Constructor for TestClient.
	 */
	public LimClient(String propsFile) throws Exception
	{
		loadProperties(propsFile);

		m_bisHome = m_testClientProperties.getProperty(BIS_HOME_KEY);

		if(m_bisHome != null && m_bisHome.trim().length() > 0)
		{
			m_bisHome = m_bisHome.trim();
		} 
		else
		{
			throw new LimClientException("BIS_HOME is not specified.");
		}

		m_providerURL = m_testClientProperties.getProperty(Context.PROVIDER_URL).trim();		

		if(m_providerURL != null && m_providerURL.trim().length() > 0)
		{
			m_providerURL = m_providerURL.trim();
		}
		else
		{
			throw new LimClientException("PROVIDER_URL is not specified.");
		}
		

		/*
		 * Get LIM Bean instance
		 */ 		
		lookupLimBean();
	}

	/**
	 * Load the properties file.
	 */
	protected void loadProperties(String propsFile) throws LimClientException 
	{
		FileInputStream fInput = null;

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
				throw new LimClientException(e, "Failed to load client properties file.");
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
	 * Get the LIM Bean instance
	 */
	private void lookupLimBean() throws Exception
	{
		Properties p = new Properties();
		p.put(Context.PROVIDER_URL, m_providerURL);
		InitialContext ic = new InitialContext(p);
		Object o = ic.lookup(m_bisHome);
		LimHome home = (LimHome) javax.rmi.PortableRemoteObject.narrow(o, LimHome.class);
		m_limBean = home.create();
		log("Retrieved lim bean");
	}
		
	/**
	 * Execute fieldAddress transaction
	 */
	protected FieldAddressReturn execute(_fieldAddressRequest request)
		throws Exception
	{
		return 
			m_limBean.fieldAddress(
				request.aContext,
				request.aUnfieldedAddress);	
	}

	/**
	 * Execute retrieveLocationForE911Address transaction
	 */
	protected RetrieveLocationForE911AddressReturn execute(_retrieveLocationForE911AddressRequest request)
		throws Exception
	{
		return
			m_limBean.retrieveLocationForE911Address(
				request.aContext,
				request.aAddress,
				request.aExchangeCode,
				request.aMaxAddressAlternatives);
	}
	
	/**
	 * Execute retrieveLocationForGeoAddress transaction
	 */
	protected RetrieveLocationForGeoAddressReturn execute(_retrieveLocationForGeoAddressRequest request)
		throws Exception
	{
		return
			m_limBean.retrieveLocationForGeoAddress(
				request.aContext,
				request.aAddress,
				request.aMaxAddressAlternatives);
	}
	
	/**
	 * Execute retrieveLocationForPostalAddress transaction
	 */
	protected RetrieveLocationForPostalAddressReturn execute(_retrieveLocationForPostalAddressRequest request)
		throws Exception
	{
		return
			m_limBean.retrieveLocationForPostalAddress(
				request.aContext,
				request.aAddress,
				request.aMaxAddressAlternatives);
	}
	/**
	 * Execute retrieveLocationForPostalAddress transaction
	 */
	protected RetrieveLocationForPostalAddress2Return execute(_retrieveLocationForPostalAddress2Request request)
		throws Exception
	{
		return
			m_limBean.retrieveLocationForPostalAddress2(
				request.aContext,
				request.aAddress,
				request.aMaxAddressAlternatives,
				request.aMaxCassCharPerLine );
	}
	
	/**
	 * Execute retrieveLocationForServiceAddress transaction
	 */
	protected RetrieveLocationForServiceAddressReturn execute(_retrieveLocationForServiceAddressRequest request)
		throws Exception
	{
		return
			m_limBean.retrieveLocationForServiceAddress(
				request.aContext,
				request.aAddress,
				request.aLocationPropertiesToGet,
				request.aPreviousCustomerName,
				request.aCrossBoundaryState,
				request.aMaxBasicAddressAlternatives,
				request.aMaxLivingUnitAlternatives);
	}
	
	/**
	 * Execute retrieveLocationForServiceAddress transaction
	 */
	protected UpdateBillingAddressReturn execute(_updateBillingAddressRequest request)
		throws Exception
	{
		return
			m_limBean.updateBillingAddress(
				request.aContext,
				request.aBillingAccountKey,
				request.aOldAddress,
				request.aNewAddress,
				request.aDeliveryPointValidationCode,
				request.aContactName);
	}
	
	/**
	 * Execute retrieveLocationForTelephoneNumber transaction
	 */
	protected RetrieveLocationForTelephoneNumberReturn execute(_retrieveLocationForTelephoneNumberRequest request)
		throws Exception
	{
		return
			m_limBean.retrieveLocationForTelephoneNumber(
				request.aContext,
				request.aTelephoneNumber,
				request.aLocationPropertiesToGet);
	}
	
	/**
	 * Execute retrieveLocationForAddress transaction
	 */
	protected RetrieveLocationForAddressReturn execute(_retrieveLocationForAddressRequest request)
		throws Exception
	{
		return
			m_limBean.retrieveLocationForAddress(
				request.aContext,
				request.aAddress,
				request.aProviderLocationProperties,
				request.aMaxBasicAddressAlternatives,
				request.aMaxLivingUnitAlternatives,
				request.aExchangeCode);
	}		

	/**
	 * Execute retrieveLocationForService
	 */
	protected RetrieveLocationForServiceReturn execute(_retrieveLocationForServiceRequest request)
		throws Exception
	{
		return
			m_limBean.retrieveLocationForService(
				request.aContext,
				request.aTelephoneNumber,
				request.aProviderLocationProperties,
				request.aMaxBasicAddressAlternatives,
				request.aMaxLivingUnitAlternatives);
	}

	/**
	 * Execute retrieveServiceAreaByPostalCode
	 */
	protected RetrieveServiceAreaByPostalCodeReturn execute(_retrieveServiceAreaByPostalCodeRequest request)
		throws Exception
	{
		return
			m_limBean.retrieveServiceAreaByPostalCode(
				request.aContext,
				request.aCingularSalesChannel,
				request.aPostalCode,
				request.aRequestServiceAreaIndicator,
				request.aRequestMarketInformationIndicator);
	}

	/**
	 * Execute ping
	 */
	protected PingReturn execute(_pingRequest request)
		throws Exception
	{
		return	m_limBean.ping(request.aBisContext);
	}

	/**
	 * Execute selfTest
	 */
	protected SelfTestReturn execute(_selfTestRequest request)
		throws Exception
	{
		return	m_limBean.selfTest(request.aBisContext);
	}

																	
	/**
	 * log a message
	 */
	public static void log(String s)
	{
		System.out.println(s);
	}
	
	/**
	 * Main method
	 */	
	public static void main(String[] args)
	{
		if(args != null && args.length > 0 )
		{
			try
			{
				LimClient limClient = new LimClient(args[0]);
				Menu menu = new Menu(limClient, limClient.m_testClientProperties);
				menu.run();
			}
			catch(Exception e)
			{
				log(e.getMessage());
				e.printStackTrace();
			}
		}	
	}
}