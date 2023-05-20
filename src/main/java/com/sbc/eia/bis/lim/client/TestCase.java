//$Id: TestCase.java,v 1.9 2009/02/03 18:43:50 jv7958 Exp $
package com.sbc.eia.bis.lim.client;

import org.jdom.Element;

import com.sbc.eia.idl.lim.LimFacadePackage._fieldAddressRequestBISMsg;
import com.sbc.eia.idl.lim.LimFacadePackage._retrieveLocationForTelephoneNumberRequestBISMsg;
import com.sbc.eia.idl.lim.LimFacadePackage._retrieveLocationForE911AddressRequestBISMsg;
import com.sbc.eia.idl.lim.LimFacadePackage._retrieveLocationForGeoAddressRequestBISMsg;
import com.sbc.eia.idl.lim.LimFacadePackage._retrieveLocationForPostalAddressRequestBISMsg;
import com.sbc.eia.idl.lim.LimFacadePackage._retrieveLocationForPostalAddress2RequestBISMsg;
import com.sbc.eia.idl.lim.LimFacadePackage._retrieveLocationForServiceAddressRequestBISMsg;
import com.sbc.eia.idl.lim.LimFacadePackage._updateBillingAddressRequestBISMsg;
import com.sbc.eia.idl.lim.LimFacadePackage._pingRequestBISMsg;
import com.sbc.eia.idl.lim.LimFacadePackage._retrieveLocationForAddressRequestBISMsg;
import com.sbc.eia.idl.lim.LimFacadePackage._retrieveLocationForServiceRequestBISMsg;
import com.sbc.eia.idl.lim.LimFacadePackage._retrieveServiceAreaByPostalCodeRequestBISMsg;
import com.sbc.eia.idl.lim.LimFacadePackage._selfTestRequestBISMsg;
import com.sbc.vicunalite.vaxb.VAXBReader;

/**
 * @author gg2712
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class TestCase
{
	public static final String FIELD_ADDRESS_MSG =
		"com.sbc.eia.idl.lim.LimFacadePackage._fieldAddressRequestBISMsg";
	public static final String RETRIEVE_LOCATION_FOR_E911ADDRESS_MSG =
	    "com.sbc.eia.idl.lim.LimFacadePackage._retrieveLocationForE911AddressRequestBISMsg";
	public static final String RETRIEVE_LOCATION_FOR_GEOADDRESS_MSG =
	    "com.sbc.eia.idl.lim.LimFacadePackage._retrieveLocationForGeoAddressRequestBISMsg";
	public static final String RETRIEVE_LOCATION_FOR_POSTALADDRESS_MSG =
	    "com.sbc.eia.idl.lim.LimFacadePackage._retrieveLocationForPostalAddressRequestBISMsg";
	public static final String RETRIEVE_LOCATION_FOR_POSTALADDRESS2_MSG =
	    "com.sbc.eia.idl.lim.LimFacadePackage._retrieveLocationForPostalAddress2RequestBISMsg";
	public static final String RETRIEVE_LOCATION_FOR_SERVICEADDRESS_MSG =
	    "com.sbc.eia.idl.lim.LimFacadePackage._retrieveLocationForServiceAddressRequestBISMsg";
	public static final String RETRIEVE_LOCATION_FOR_TELEPHONENUMBER_MSG =
	    "com.sbc.eia.idl.lim.LimFacadePackage._retrieveLocationForTelephoneNumberRequestBISMsg";
	public static final String RETRIEVE_LOCATION_FOR_UPDATEBILLINGADDRESS_MSG =
	    "com.sbc.eia.idl.lim.LimFacadePackage._updateBillingAddressRequestBISMsg";
	public static final String MODIFY_SERVICE_ADDRESS_MSG =
		"com.sbc.eia.idl.lim.LimFacadePackage._modifyServiceAddressRequestBISMsg";
	public static final String PING_MSG =
		"com.sbc.eia.idl.lim.LimFacadePackage._pingRequestBISMsg";
	public static final String RETRIEVE_LOCATION_FOR_ADDRESS_MSG =
		"com.sbc.eia.idl.lim.LimFacadePackage._retrieveLocationForAddressRequestBISMsg";
	public static final String RETRIEVE_LOCATION_FOR_SERVICE_MSG =
		"com.sbc.eia.idl.lim.LimFacadePackage._retrieveLocationForServiceRequestBISMsg";
	public static final String RETRIEVE_SERVICE_AREA_BY_POSTAL_CODE_MSG =
		"com.sbc.eia.idl.lim.LimFacadePackage._retrieveServiceAreaByPostalCodeRequestBISMsg";
	public static final String SELF_TEST_MSG =
		"com.sbc.eia.idl.lim.LimFacadePackage._selfTestRequestBISMsg";

	public static final String HEADER_TAG_NAME = "Header";
	public static final String DESCRIPTION_TAG_NAME = "Description";
	public static final String VAXB_TAG_NAME = "VAXB";
			
	public static final int FIELD_ADDRESS = 1;
	public static final int MODIFY_SERVICE_ADDRESS = 2;
	public static final int RETRIEVE_LOCATION_FOR_ADDRESS = 3;
	public static final int RETRIEVE_LOCATION_FOR_SERVICE = 4;
	public static final int RETRIEVE_SERVICE_AREA_BY_POSTAL_CODE = 5;
	public static final int PING = 6;
	public static final int SELF_TEST = 7;
	public static final int RETRIEVE_LOCATION_FOR_E911ADDRESS = 8;
	public static final int RETRIEVE_LOCATION_FOR_GEOADDRESS = 9;
	public static final int RETRIEVE_LOCATION_FOR_POSTALADDRESS = 10;
	public static final int RETRIEVE_LOCATION_FOR_SERVICEADDRESS = 11;
	public static final int RETRIEVE_LOCATION_FOR_TELEPHONENUMBER = 12;
	public static final int UPDATE_BILLINGADDRESS = 13;
	public static final int RETRIEVE_LOCATION_FOR_POSTALADDRESS2 = 14;

	private int m_type;		 
	private String m_description;
	private Object m_request;

	/**
	 * Constructor
	 */
	public TestCase(Element envelope) throws Exception
	{
		/* Get the Description from Header
		 */
		
		String description;
		try
		{
			m_description = envelope
							.getChild(HEADER_TAG_NAME)
							.getChild(DESCRIPTION_TAG_NAME)
							.getTextTrim();
		}
		catch(Exception e)
		{
			m_description = "No description";
		}
						
		/* Get VAXB and message elements
		 */

		Element vaxb = envelope.getChild(VAXB_TAG_NAME);
		Element message = (Element) vaxb.getChildren().get(0);

		/* Determine type of message and decode (XML -> IDL)
		 */

		if(message.getName().equalsIgnoreCase(FIELD_ADDRESS_MSG))
		{
			m_type = FIELD_ADDRESS;
			m_request = ((_fieldAddressRequestBISMsg) VAXBReader.decode(vaxb)).value;
		}
		else if(message.getName().equalsIgnoreCase(RETRIEVE_LOCATION_FOR_E911ADDRESS_MSG))
		{
			m_type = RETRIEVE_LOCATION_FOR_E911ADDRESS;
			m_request = ((_retrieveLocationForE911AddressRequestBISMsg) VAXBReader.decode(vaxb)).value;
		}
		else if(message.getName().equalsIgnoreCase(RETRIEVE_LOCATION_FOR_GEOADDRESS_MSG))
		{
			m_type = RETRIEVE_LOCATION_FOR_GEOADDRESS;
			m_request = ((_retrieveLocationForGeoAddressRequestBISMsg) VAXBReader.decode(vaxb)).value;
		}
		else if(message.getName().equalsIgnoreCase(RETRIEVE_LOCATION_FOR_POSTALADDRESS_MSG))
		{
			m_type = RETRIEVE_LOCATION_FOR_POSTALADDRESS;
			m_request = ((_retrieveLocationForPostalAddressRequestBISMsg) VAXBReader.decode(vaxb)).value;
		}
		else if(message.getName().equalsIgnoreCase(RETRIEVE_LOCATION_FOR_SERVICEADDRESS_MSG))
		{
			m_type = RETRIEVE_LOCATION_FOR_SERVICEADDRESS;
			m_request = ((_retrieveLocationForServiceAddressRequestBISMsg) VAXBReader.decode(vaxb)).value;
		}
		else if(message.getName().equalsIgnoreCase(RETRIEVE_LOCATION_FOR_TELEPHONENUMBER_MSG))
		{
			m_type = RETRIEVE_LOCATION_FOR_TELEPHONENUMBER;
			m_request = ((_retrieveLocationForTelephoneNumberRequestBISMsg) VAXBReader.decode(vaxb)).value;
		}
		else if (message.getName().equalsIgnoreCase(RETRIEVE_LOCATION_FOR_UPDATEBILLINGADDRESS_MSG))
		{
		    m_type = UPDATE_BILLINGADDRESS;
		    m_request = ((_updateBillingAddressRequestBISMsg) VAXBReader.decode(vaxb)).value;
		}
		else if(message.getName().equalsIgnoreCase(PING_MSG))
		{
			m_type = PING;
			m_request = ((_pingRequestBISMsg) VAXBReader.decode(vaxb)).value;
		} 
		else if(message.getName().equalsIgnoreCase(RETRIEVE_LOCATION_FOR_ADDRESS_MSG))
		{
			m_type = RETRIEVE_LOCATION_FOR_ADDRESS;
			m_request = ((_retrieveLocationForAddressRequestBISMsg) VAXBReader.decode(vaxb)).value;
		} 
		else if(message.getName().equalsIgnoreCase(RETRIEVE_LOCATION_FOR_SERVICE_MSG))
		{
			m_type = RETRIEVE_LOCATION_FOR_SERVICE;
			m_request = ((_retrieveLocationForServiceRequestBISMsg) VAXBReader.decode(vaxb)).value;
		} 
		else if(message.getName().equalsIgnoreCase(RETRIEVE_SERVICE_AREA_BY_POSTAL_CODE_MSG))
		{
			m_type = RETRIEVE_SERVICE_AREA_BY_POSTAL_CODE;
			m_request = ((_retrieveServiceAreaByPostalCodeRequestBISMsg) VAXBReader.decode(vaxb)).value;
		}
		else if(message.getName().equalsIgnoreCase(SELF_TEST_MSG))
		{
			m_type = SELF_TEST;
			m_request = ((_selfTestRequestBISMsg) VAXBReader.decode(vaxb)).value;
		}
		else if(message.getName().equalsIgnoreCase(RETRIEVE_LOCATION_FOR_POSTALADDRESS2_MSG))
		{
			m_type = RETRIEVE_LOCATION_FOR_POSTALADDRESS2;
			m_request = ((_retrieveLocationForPostalAddress2RequestBISMsg) VAXBReader.decode(vaxb)).value;
		}
		else
		{
			throw new LimClientException("Invalid test case type!");
		}
	}
	
	/**
	 * Returns the m_description.
	 * @return String
	 */
	public String getDescription()
	{
		return m_description;
	}

	/**
	 * Returns the m_request.
	 * @return Object
	 */
	public Object getRequest()
	{
		return m_request;
	}

	/**
	 * Returns the m_type.
	 * @return int
	 */
	public int getType()
	{
		return m_type;
	}

}
