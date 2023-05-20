/**
 * Created on Jun 21, 2004
 *
 * To change this generated comment edit the template variable "filecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of file comments go to
 * Window>Preferences>Java>Code Generation.
 */
package com.sbc.bccs.utility.soaphelpers;

/**
 * @author rk7964
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class SoapParserHelper
{
    
    private static final String SOAP_ENVELOP_HEADER   = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>" + 
                    "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:soapenc=" + 
                    "\"http://schemas.xmlsoap.org/soap/encoding/\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\"" +
                    " xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><soapenv:Body>" ;
                    
    private static final String SOAP_ENVELOP_FOOTER   = "</soapenv:Body></soapenv:Envelope>";
    
    /**
    * Wraps xml in a soap envelop.
    * Creation date: (4/20/04 4:13:28 PM)
    * @param String xml
    * @return String
    */
    public static String appendSoapEnvelope(String xml) {
        
        if (xml != null) {
            
            return SOAP_ENVELOP_HEADER + xml.trim() + SOAP_ENVELOP_FOOTER;
            
        } else
        {
            return "";
         
        }
        
        
    }
    
    /**
    * Extracts xml request wrapped in the soap envelop.
    * Creation date: (4/20/04 4:13:28 PM)
    * @param String xml
    * @return String
    */
    public static String removeSoapEnvelope(String xml) {
        
        if (xml != null) {
            
            int startIndex = xml.indexOf("<soapenv:Body>");
            int endIndex = xml.indexOf("</soapenv:Body>");
        
            return xml.substring(startIndex + 14, endIndex).trim();
            
        } else
        {
            return "";
          
        }
        
        
    }
    
    /**
    * Wraps xml in a soap fault header and footer and inturn wraps it in a soap envelop.
    * Creation date: (4/20/04 4:13:28 PM)
    * @param String xml
    * @return String
    */
    public static String appendSoapFaultEnvelope(String exceptionName, String xml) {
        
        String soapFaultHeader = "<soapenv:Fault>" +
         "<faultcode xmlns:ns-1382140156=\"http://bis.eia.sbc.com/2004/04\"" +
         " xmlns=\"\">ns-1382140156:" + exceptionName + "</faultcode>" +
         "<faultstring xmlns=\"\"><![CDATA[com.sbc.eia.bis." +
         exceptionName + "]]></faultstring>" +
         "<detail xmlns=\"\">";
         
        String soapFaultFooter = "<stackTrace xmlns=\"http://websphere.ibm.com/webservices/\">" +
               "com.sbc.eia.bis." + exceptionName + 
               "&#xd;</stackTrace></detail></soapenv:Fault>";
               
        return appendSoapEnvelope(soapFaultHeader + xml.trim() + soapFaultFooter);
   
        
    }
    
    public static String replaceVaxBWithSoapEnvelope(String xml) {
        
        int index = xml.indexOf("<VAXB");
           
        if (index == -1) {
            return xml;
        } else
        {
            index = xml.indexOf(">", index + 1);
            xml = xml.substring(index + 1);
        }
        
        index = xml.indexOf("</VAXB>");
        
        if (index == -1) {
            return xml;
        } else
        {
            xml = xml.substring(0,index - 1);
        }
        
        return appendSoapEnvelope(xml);
        
    }

}
