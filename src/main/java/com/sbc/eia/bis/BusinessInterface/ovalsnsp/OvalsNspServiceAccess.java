//$Id: OvalsNspServiceAccess.java,v 1.9 2008/02/29 23:27:10 jh9785 Exp $

package com.sbc.eia.bis.BusinessInterface.ovalsnsp;

/**
 * @author gg2712
 */

import java.util.Hashtable;
import java.util.Properties;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;
import javax.xml.bind.Marshaller;

import com.sbc.bccs.utilities.Logger;
import com.sbc.eia.bis.embus.service.access.DefaultJAXBEncoderDecoder;
import com.sbc.eia.bis.embus.service.access.ServiceAccess;
import com.sbc.eia.bis.embus.service.access.ServiceException;
import com.sbc.eia.bis.lim.jaxb.ovalsnsp.OVALSNSPType;

/**
 * This class extends ServiceAccess to access OvalsNSP backend.
 */

public class OvalsNspServiceAccess extends ServiceAccess
{
	private Logger aLogger = null;

    /**
     * Constructor for OvalsNspServiceAccess.
     * @param environmentName
     * @param configFileName
     * @param logger
     * @throws ServiceException
     */
    public OvalsNspServiceAccess(String environmentName,
                                  String configurationFileName,
                                  Logger logger)
     throws ServiceException
    {
        super(environmentName, configurationFileName, logger);
        aLogger = logger;
        
        Properties options = new Properties();
        options.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, "true");
        
        DefaultJAXBEncoderDecoder ovalsNspEncoderdecoder =
        	new DefaultJAXBEncoderDecoder(
     	   			OVALSNSPType.class.getPackage().getName(),
     	   			options);
     	   			
		setEncoder(ovalsNspEncoderdecoder);
		setDecoder(ovalsNspEncoderdecoder);     	   			
    }	
    
    /**
     * @see com.sbc.eia.bis.embus.service.access.ServiceAccess# handleEmbusResponse
     * @param Message
     * @param Properties
     */
    
    public String handleEmbusResponse(Message responseMessage, 
                                       Properties propertiesInResponse)
     throws ServiceException
    {
        return defaultHandleEmbusResponse(responseMessage, propertiesInResponse);
    }
}
