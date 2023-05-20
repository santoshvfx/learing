//$Id: CbsService.java,v 1.9.18.1 2016/05/10 13:56:05 np839m Exp $
package com.sbc.eia.bis.BusinessInterface.ServiceAddress.rsag;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Holder;
import com.bellsouth.cbs.order.ws.CbsBasicAddressV7;
import com.bellsouth.cbs.order.ws.CbsOrderAddressResponseV7;
import com.bellsouth.cbs.order.ws.CbsOrderValidateAddressV7;
import com.bellsouth.cbs.order.ws.CbsOrderValidateAddressV7Port;
import com.bellsouth.cbs.order.ws.CbsSOAPResponseHeaderV3;
import com.bellsouth.cbs.order.ws.CbsSOAPTransactionHeaderV3;
import com.bellsouth.cbs.order.ws.CbsTelephoneNumberV7;
import com.bellsouth.cbs.order.ws.ObjectFactory;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.bis.lim.util.LIMBase;


/**
 * @author gg2712
 */
public class CbsService
{
    public static final String CATALYST_MESSAGING_SERVICE_ENV_CONTEXT = "catalyst.messaging.service.envContext";
    public static final String CATALYST_MESSAGING_SERVICE_INTERFACE = "catalyst.messaging.service.interface";
    public static final String CATALYST_MESSAGING_SERVICE_TRACKING_ID = "catalyst.messaging.service.trackingID";
    public static final String CATALYST_MESSAGING_SERVICE_USER_ID = "catalyst.messaging.service.userID";
    public static final String CATALYST_USERID = "CATALYST_USERID";//change to segregate test and prod catalyst userids
    public static final String CATALYST_MESSAGING_SERVICE_APP_ID = "catalyst.messaging.service.appID";
    public static final String CATALYST_MESSAGING_SERVICE_TIME_OUT = "catalyst.messaging.service.timeout";
    public static final String CBS_CLIENT_APP_ID_TAG = "clientAppId";
    public static final String CBS_USER_ID_TAG = "userID";
    public static final String CBS_RUN_MODE_TAG = "runMode";
    public static final String CBS_TRANSACTION_ID_TAG = "transactionID";
    public static final String CBS_TIME_OUT_TAG = "Timeout";
    public static final String CBS_ORDER_VALIDATE_ADDRESS_V7_INTERFACE_NAME = "CbsOrderValidateAddressV7";
    public static final String RSAG_PRD_REGION = "P";
    public static final String RSAG_SCC_REGION = "D";
    public static final String RSAG_SCZ_REGION = "T";
    public static final String RSAG_CST_REGION = "X";
    LIMBase aLimBase = null;
    String aCbsRunMode  = null;
    final ObjectFactory objectFactory = new ObjectFactory();
    

    
    public CbsService(LIMBase limBase)
    {
        super();
        aLimBase = limBase;
    }
    
    /**
     * locateAddressByAddress
     * @param CbsBasicAddressV1
     * @return CbsOrderAddressResponseV7 
     */
    public CbsOrderAddressResponseV7 locateAddressByAddress(CbsBasicAddressV7 cbsBasicAddress)
        	throws   CbsServiceException
        {
            CbsOrderAddressResponseV7 cbsResp = null;
            Properties props = null;
            CbsOrderValidateAddressV7 service = null;
            
            try
            {
                props = getProperties();	
                String endpoint = aLimBase.getPROPERTIES().get("CBS_ORDERVALIDATION_WSDL").toString(); //"http://crcbsu09.bst.bls.com:16830/ws/CbsOrderValidateAddressV7/CbsOrderValidateAddressV7";
                service = new com.bellsouth.cbs.order.ws.CbsOrderValidateAddressV7();              
               
                CbsOrderValidateAddressV7Port port = service.getCbsOrderValidateAddressV7();
  
                BindingProvider prov = (BindingProvider) port;
                
                prov.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpoint);
                prov.getRequestContext().put("javax.xml.ws.client.connectionTimeout", 20000); // Timeout in millis
                prov.getRequestContext().put("javax.xml.ws.client.receiveTimeout", 30000); // Timeout in millis
                
                Holder<CbsSOAPResponseHeaderV3> cbsResponse1 = null;
                
                cbsResp =  port.locateAddressByAddress(cbsBasicAddress, null, getCbsTransactions(props), cbsResponse1);
    	
                printCbsOrderAddressResponseWs(cbsResp);
            }
            catch(Exception e)
            {
                throw new RuntimeException(e.getMessage(), e);
            }

            return cbsResp;
        }
/*    
    	private List<Handler> getHandlerList(Properties props) {
 //           CbsSOAPTransactionHeaderV3 tranHeader = getCbsTransactions(props);
            
            List<Handler> handlersList = new ArrayList<Handler>();
 
 //           final JAXBElement<CbsSOAPTransactionHeaderV3> header = objectFactory.createCbsSOAPTransactionHdrV3(tranHeader);

            handlersList.add(new SOAPHandler<SOAPMessageContext>() {
                @Override
                public boolean handleMessage(final SOAPMessageContext context) {
                    try {
                        final Boolean outbound = (Boolean) context.get("javax.xml.ws.handler.message.outbound");
                        if (outbound != null && outbound) {
                        	System.out.println(context.get("javax.xml.ws.http.request.headers"));
                        	System.out.println(context.get("jaxws.message.accessor"));
                            // obtaining marshaller which should marshal instance to xml
 //                           final Marshaller marshaller = JAXBContext.newInstance(CbsSOAPTransactionHeaderV3.class).createMarshaller();
                            // if get return null, call addHeader
 //                       	Map<String, Object> requestHeaders = new HashMap<String, Object>();
 //                           requestHeaders.put("transactionInfo", header);
 //                           context.put(context.HTTP_REQUEST_HEADERS, requestHeaders);
 //                           SOAPHeader soapHeader = context.getMessage().getSOAPPart().getEnvelope().getHeader();
                            // marshalling instance (appending) to SOAP header's xml node
 //                           marshaller.marshal(header, soapHeader);
                        }
                    } catch (final Exception e) {
                        throw new RuntimeException(e);
                    }
                    return true;
                }

                @Override
                public Set<QName> getHeaders() {
                    Set<QName> ts = new HashSet<>();
 //                   ts.add(header.getName());
                    return ts;
                }

                @Override
                public boolean handleFault(SOAPMessageContext context) {
                    return false;
                }

                @Override
                public void close(MessageContext context) {
                }
            });
            return handlersList;
    	}
*/
    /**
     * locateAddressByTN
     * @param cbsTelephoneNumber
     * @param cbsTransactions
     * @return CbsOrderAddressResponseV7
     */
    public CbsOrderAddressResponseV7 locateAddressByTN(CbsTelephoneNumberV7 cbsTelephoneNumber)
    	throws CbsServiceException
    {
        CbsOrderValidateAddressV7 service = null;
        CbsOrderAddressResponseV7 cbsResp = null;
        Properties props = null;
        
        try
        {
            props = getProperties();	
            String endpoint = aLimBase.getPROPERTIES().get("CBS_ORDERVALIDATION_WSDL").toString(); //"http://crcbsu09.bst.bls.com:16830/ws/CbsOrderValidateAddressV7/CbsOrderValidateAddressV7";
//            URL wsdlLocation = CbsOrderValidateAddressV7.class.getClassLoader().getResource("file://META-INF/wsdl/CbsOrderValidateAddressV7.wsdl");
            service = new com.bellsouth.cbs.order.ws.CbsOrderValidateAddressV7();              
           
            CbsOrderValidateAddressV7Port port = service.getCbsOrderValidateAddressV7();

            BindingProvider prov = (BindingProvider) port;
            
            prov.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpoint);
            
//            prov.getBinding().setHandlerChain(getHandlerList(props));
            Holder<CbsSOAPResponseHeaderV3> cbsResponse1 = null;
            
            cbsResp =  port.locateAddressByTN(cbsTelephoneNumber, null, getCbsTransactions(props), cbsResponse1);
          
            printCbsOrderAddressResponse(cbsResp);
        }
        catch(Exception e)
        {
            throw new CbsServiceException(e.getMessage());
        }

       return cbsResp;
    }
    
    /**
     * getProperties
     * @return Properties
     */
    private Properties getProperties()
    {
        Properties props = new Properties();
        String trackingId = getTrackingId();

        aLimBase.log(LogEventId.INFO_LEVEL_1, "CatalystTrackingID: " + trackingId);
	
        props.setProperty(CATALYST_MESSAGING_SERVICE_INTERFACE, CBS_ORDER_VALIDATE_ADDRESS_V7_INTERFACE_NAME);
        props.setProperty(CATALYST_MESSAGING_SERVICE_TRACKING_ID, trackingId);
        
        return props;
    }
    
    /**
     * getCbsTransactions
     * @param props
     * @return CbsTransactionItemV6[]
     */
/*    private CbsTransactionItemV7[] getCbsTransactions(Properties props)
    {
        setCbsRunMode();
        
        CbsTransactionItemV7[] cbsTrans = new CbsTransactionItemV7[4];
        cbsTrans[0] = new CbsTransactionItemV6(CBS_CLIENT_APP_ID_TAG, props.getProperty(CATALYST_MESSAGING_SERVICE_APP_ID));
        //np839m/05May2016/Change to differentiate catalyst userid for test and prod
        aLimBase.log(LogEventId.DEBUG_LEVEL_2, "Catalyst Userid: " + aLimBase.PROPERTIES.get(CATALYST_USERID));
        cbsTrans[1] = new CbsTransactionItemV6(CBS_USER_ID_TAG, aLimBase.PROPERTIES.get(CATALYST_USERID).toString());
        cbsTrans[2] = new CbsTransactionItemV6(CBS_RUN_MODE_TAG, getCbsRunMode());
        cbsTrans[3] = new CbsTransactionItemV6(CBS_TIME_OUT_TAG, props.getProperty(CATALYST_MESSAGING_SERVICE_TIME_OUT));
        
        return cbsTrans;
    }
*/
    private CbsSOAPTransactionHeaderV3 getCbsTransactions(Properties props)
    {
        setCbsRunMode();
        
        CbsSOAPTransactionHeaderV3 cbsTrans = new CbsSOAPTransactionHeaderV3();
        cbsTrans.setClientAppID((String) aLimBase.getPROPERTIES().get("CBS_CLIENT_APPID"));//"LIMBIS1");//props.getProperty(CATALYST_MESSAGING_SERVICE_APP_ID));
        cbsTrans.setDataMode(aCbsRunMode);
        cbsTrans.setUserID((String) aLimBase.getPROPERTIES().get("CBS_USERID"));
        String timeout = (String) aLimBase.getPROPERTIES().get("CBS_URL_TIMEOUT");
        cbsTrans.setTimeoutMs(Long.valueOf(timeout));
        cbsTrans.setTransactionID(getTrackingId());
        cbsTrans.setEcho("true");
        return cbsTrans;
    }
    
    /**
     * getRunMode
     * @return String
     */
    private void setCbsRunMode()
    {
        //---------------------------------------------------
        // runMode - identfies the RSAG environment to use
        //
        // P - RSAG Production
        // D - RSAG SCC IMS region - Georgia
        // T - RSAG SCZ IMS region - Florida
        // X - RSAG CST IMS region
        //--------------------------------------------------- 
        
        if(aLimBase.isUseRsagProduction())
        {
            aCbsRunMode = RSAG_PRD_REGION;
        }
        else if(aLimBase.getCurrentState().equalsIgnoreCase("GA"))
        {
            aCbsRunMode = RSAG_SCC_REGION;
        }
        else if(aLimBase.getCurrentState().equalsIgnoreCase("FL"))
        {
            aCbsRunMode = RSAG_SCZ_REGION;
        }
        else
        {
            aCbsRunMode = RSAG_CST_REGION;
        }
    }
    
    /**
     * getCbsRunMode
     * @return
     */
    private String getCbsRunMode()
    {
        return aCbsRunMode;
    }
    
    /**
     * getTrackingId
     * @return String
     */
    public static String getTrackingId()
    {
        Date today = new java.util.Date();
		SimpleDateFormat formatter = new SimpleDateFormat( "yyyyMMddHHmmssSSS" ) ;
		String dateString = formatter.format( today ) ;
		int randomNbr = 1000 + (int)(9000*Math.random());  // get random number between 1000 and 9999
		StringBuffer sb = new StringBuffer("LIMBIS");
		sb.append(dateString);
		sb.append(randomNbr);
		return sb.toString().trim();
    }
    
    /**
     * printCbsOrderAddressResponse
     * @parm CbsOrderAddressResponseV7
     */
    public void printCbsOrderAddressResponseWs(com.bellsouth.cbs.order.ws.CbsOrderAddressResponseV7 cbsResponse)
    {
        StringBuffer sbAddr; 
        
        if(cbsResponse == null)
        {
            return;
        }
        
        aLimBase.log(LogEventId.DEBUG_LEVEL_2, "CbsOrderAddressResponse: StatusCode=" + cbsResponse.getServiceResponseV7().getStatusCode());
        
        try
        {
        	ArrayList<com.bellsouth.cbs.order.ws.CbsOrderServiceAddressV7> addresses = (ArrayList) cbsResponse.getAddresses();
	        for(int i=0; i < cbsResponse.getAddresses().size(); i++)
	        {
	            sbAddr = new StringBuffer();
	            sbAddr.append(addresses.get(i).getBasicAddr().getAddressNumber());
	            sbAddr.append("|");
	            sbAddr.append(addresses.get(i).getBasicAddr().getAddressLine());
	            sbAddr.append("|");
	            sbAddr.append(addresses.get(i).getBasicAddr().getCmnty().getName());
	            sbAddr.append("|");
	            sbAddr.append(addresses.get(i).getBasicAddr().getCmnty().getAbbreviation());
	            sbAddr.append("|");
	            sbAddr.append(addresses.get(i).getBasicAddr().getCmnty().getCrossBoundaryStates());
	            sbAddr.append("|");
	            sbAddr.append(addresses.get(i).getBasicAddr().getCity());
	            sbAddr.append("|");
	            sbAddr.append(addresses.get(i).getBasicAddr().getState());
	            sbAddr.append("|");
	            sbAddr.append(addresses.get(i).getBasicAddr().getZipCode());
	            sbAddr.append("|");
	            sbAddr.append(addresses.get(i).getBasicAddr().getDescriptiveAddress());
	            sbAddr.append("|");
	            sbAddr.append(addresses.get(i).getBasicAddr().getUnt().getUnitType());
	            sbAddr.append("|");
	            sbAddr.append(addresses.get(i).getBasicAddr().getUnt().getTypeDescription());
	            sbAddr.append("|");
	            sbAddr.append(addresses.get(i).getBasicAddr().getUnt().getData()); 
	            sbAddr.append("|");
	            sbAddr.append(addresses.get(i).getBasicAddr().getStructr().getStructureType());
	            sbAddr.append("|");
	            sbAddr.append(addresses.get(i).getBasicAddr().getStructr().getTypeDescription());
	            sbAddr.append("|");
	            sbAddr.append(addresses.get(i).getBasicAddr().getStructr().getData());
	            sbAddr.append("|");
	            sbAddr.append(addresses.get(i).getBasicAddr().getElevtn().getElevationType());
	            sbAddr.append("|");
	            sbAddr.append(addresses.get(i).getBasicAddr().getElevtn().getTypeDescription());
	            sbAddr.append("|");
	            sbAddr.append(addresses.get(i).getBasicAddr().getElevtn().getData());
	            sbAddr.append("|");
	            sbAddr.append(addresses.get(i).getBasicAddr().getAssignedHouseNumber());
	            sbAddr.append("|");
	            sbAddr.append(addresses.get(i).getBasicAddr().getRuralRoute());
	            sbAddr.append("|");
	            sbAddr.append(addresses.get(i).getBasicAddr().getRuralBox());
	            sbAddr.append("|");
	            sbAddr.append(addresses.get(i).getBasicAddr().getOccupantName());
	            sbAddr.append("|");
	            sbAddr.append(addresses.get(i).getGeoArea().getGeoStreet().getRealHouseNumberRangeLow());
	            sbAddr.append("|");
	            sbAddr.append(addresses.get(i).getGeoArea().getGeoStreet().getRealHouseNumberRangeHigh());
	            sbAddr.append("|");
	            sbAddr.append(addresses.get(i).getGeoArea().getGeoStreet().getPrefix());
	            sbAddr.append("|");
	            sbAddr.append(addresses.get(i).getGeoArea().getGeoStreet().getName());
	            sbAddr.append("|");
	            sbAddr.append(addresses.get(i).getGeoArea().getGeoStreet().getSuffix());
	            sbAddr.append("|");
	            sbAddr.append(addresses.get(i).getGeoArea().getGeoStreet().getThoroughfare());
	
	            aLimBase.log(LogEventId.DEBUG_LEVEL_2, "CbsOrderAddressResponse: Address=" + sbAddr.toString());
	        }
        }
        catch(Exception e)
        {
        }
        
        for(int i=0; i < cbsResponse.getServiceResponseV7().getMessages().size(); i++)
        {
            aLimBase.log(LogEventId.DEBUG_LEVEL_2, 
                    "CbsOrderAddressResponse: Message=" + 
                    		cbsResponse.getServiceResponseV7().getMessages().get(i).getCode() +
                    "|" +
                    cbsResponse.getServiceResponseV7().getMessages().get(i).getText());
        }
    }
    
    /**
     * printCbsOrderAddressResponse
     * @parm CbsOrderAddressResponseV7
     */
    public void printCbsOrderAddressResponse(CbsOrderAddressResponseV7 cbsResponse)
    {
        StringBuffer sbAddr; 
        
        if(cbsResponse == null)
        {
            return;
        }
        
        aLimBase.log(LogEventId.DEBUG_LEVEL_2, "CbsOrderAddressResponse: StatusCode=" + cbsResponse.getServiceResponseV7().getStatusCode());
        
        try
        {
	        for(int i=0; i < cbsResponse.getAddresses().size(); i++)
	        {
	            sbAddr = new StringBuffer();
	            sbAddr.append(cbsResponse.getAddresses().get(i).getBasicAddr().getAddressNumber());
	            sbAddr.append("|");
	            sbAddr.append(cbsResponse.getAddresses().get(i).getBasicAddr().getAddressLine());
	            sbAddr.append("|");
	            sbAddr.append(cbsResponse.getAddresses().get(i).getBasicAddr().getCmnty().getName());
	            sbAddr.append("|");
	            sbAddr.append(cbsResponse.getAddresses().get(i).getBasicAddr().getCmnty().getAbbreviation());
	            sbAddr.append("|");
	            sbAddr.append(cbsResponse.getAddresses().get(i).getBasicAddr().getCmnty().getCrossBoundaryStates());
	            sbAddr.append("|");
	            sbAddr.append(cbsResponse.getAddresses().get(i).getBasicAddr().getCity());
	            sbAddr.append("|");
	            sbAddr.append(cbsResponse.getAddresses().get(i).getBasicAddr().getState());
	            sbAddr.append("|");
	            sbAddr.append(cbsResponse.getAddresses().get(i).getBasicAddr().getZipCode());
	            sbAddr.append("|");
	            sbAddr.append(cbsResponse.getAddresses().get(i).getBasicAddr().getDescriptiveAddress());
	            sbAddr.append("|");
	            sbAddr.append(cbsResponse.getAddresses().get(i).getBasicAddr().getUnt().getUnitType());
	            sbAddr.append("|");
	            sbAddr.append(cbsResponse.getAddresses().get(i).getBasicAddr().getUnt().getTypeDescription());
	            sbAddr.append("|");
	            sbAddr.append(cbsResponse.getAddresses().get(i).getBasicAddr().getUnt().getData()); 
	            sbAddr.append("|");
	            sbAddr.append(cbsResponse.getAddresses().get(i).getBasicAddr().getStructr().getStructureType());
	            sbAddr.append("|");
	            sbAddr.append(cbsResponse.getAddresses().get(i).getBasicAddr().getStructr().getTypeDescription());
	            sbAddr.append("|");
	            sbAddr.append(cbsResponse.getAddresses().get(i).getBasicAddr().getStructr().getData());
	            sbAddr.append("|");
	            sbAddr.append(cbsResponse.getAddresses().get(i).getBasicAddr().getElevtn().getElevationType());
	            sbAddr.append("|");
	            sbAddr.append(cbsResponse.getAddresses().get(i).getBasicAddr().getElevtn().getTypeDescription());
	            sbAddr.append("|");
	            sbAddr.append(cbsResponse.getAddresses().get(i).getBasicAddr().getElevtn().getData());
	            sbAddr.append("|");
	            sbAddr.append(cbsResponse.getAddresses().get(i).getBasicAddr().getAssignedHouseNumber());
	            sbAddr.append("|");
	            sbAddr.append(cbsResponse.getAddresses().get(i).getBasicAddr().getRuralRoute());
	            sbAddr.append("|");
	            sbAddr.append(cbsResponse.getAddresses().get(i).getBasicAddr().getRuralBox());
	            sbAddr.append("|");
	            sbAddr.append(cbsResponse.getAddresses().get(i).getBasicAddr().getOccupantName());
	            sbAddr.append("|");
	            sbAddr.append(cbsResponse.getAddresses().get(i).getGeoArea().getGeoStreet().getRealHouseNumberRangeLow());
	            sbAddr.append("|");
	            sbAddr.append(cbsResponse.getAddresses().get(i).getGeoArea().getGeoStreet().getRealHouseNumberRangeHigh());
	            sbAddr.append("|");
	            sbAddr.append(cbsResponse.getAddresses().get(i).getGeoArea().getGeoStreet().getPrefix());
	            sbAddr.append("|");
	            sbAddr.append(cbsResponse.getAddresses().get(i).getGeoArea().getGeoStreet().getName());
	            sbAddr.append("|");
	            sbAddr.append(cbsResponse.getAddresses().get(i).getGeoArea().getGeoStreet().getSuffix());
	            sbAddr.append("|");
	            sbAddr.append(cbsResponse.getAddresses().get(i).getGeoArea().getGeoStreet().getThoroughfare());
	
	            aLimBase.log(LogEventId.DEBUG_LEVEL_2, "CbsOrderAddressResponse: Address=" + sbAddr.toString());
	        }
        }
        catch(Exception e)
        {
        }
        
        for(int i=0; i < cbsResponse.getServiceResponseV7().getMessages().size(); i++)
        {
            aLimBase.log(LogEventId.DEBUG_LEVEL_2, 
                    "CbsOrderAddressResponse: Message=" + 
                    cbsResponse.getServiceResponseV7().getMessages().get(i).getCode() +
                    "|" +
                    cbsResponse.getServiceResponseV7().getMessages().get(i).getText());
        }
    }
}
