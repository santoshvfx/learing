// $Id: OvalsUspsXMLParser.java,v 1.1 2007/09/25 19:54:30 jh9785 Exp $

package com.sbc.eia.bis.BusinessInterface.PostalAddress.ovalsusps;

import java.io.StringReader;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.xml.sax.InputSource;

import com.sbc.bccs.idl.helpers.ObjectPropertyManager;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilder;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilderResult;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilderRule;
import com.sbc.eia.bis.framework.BisContextManager;
import com.sbc.eia.bis.lim.jaxb.ovalsusps.OVALS;
import com.sbc.eia.bis.lim.jaxb.ovalsusps.impl.ADDRESSTypeImpl;
import com.sbc.eia.bis.lim.util.LIMBase;
import com.sbc.eia.bis.lim.util.LIMBisContextManager;
import com.sbc.eia.bis.lim.util.LIMTag;
import com.sbc.eia.idl.bis_types.AccessDenied;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.bis_types.BusinessViolation;
import com.sbc.eia.idl.bis_types.DataNotFound;
import com.sbc.eia.idl.bis_types.InvalidData;
import com.sbc.eia.idl.bis_types.NotImplemented;
import com.sbc.eia.idl.bis_types.ObjectNotFound;
import com.sbc.eia.idl.bis_types.SystemFailure;
import com.sbc.eia.idl.lim.helpers.AddressHandlerUSPS;
import com.sbc.eia.idl.types.Severity;

/**
 * This provides xml parsing for OVALSUSPS.
 * Creation date: (7/28/03 1:04:38 PM)
 * @author: David Brawley
 */
public class OvalsUspsXMLParser
{
    protected AddressHandlerUSPS uspsAddr = null;
    protected LIMBase limBase = null;
    protected OVALS ovals = null;
    protected String adrll = null;
    protected String xml = null;
    protected boolean incudeCSZ = false;
		
	/**
 	* OvalsUspsXMLParser constructor.
 	* @param utility LIMBase
 	* @param address AddressHandlerUSPS
 	*/
	public OvalsUspsXMLParser(LIMBase aLimBase, AddressHandlerUSPS address, boolean includeCSZ)
		throws SystemFailure
	{
		setLimBase(aLimBase);
		setUspsAddr(address);
		setAdrll();
        setIncudeCSZ(includeCSZ);
	}
	/**
 	* OvalsUspsXMLParser constructor.
 	* @param utility LIMBase
 	* @param xml String
 	*/
	public OvalsUspsXMLParser(LIMBase aLimBase, String xml)
		throws SystemFailure
	{
		setLimBase(aLimBase);
		setXML(xml);
	}

	/**
 	* Converts xml string from OVALSUSPS to objects (OVALS)
 	* Creation date: (3/26/02 7:52:09 AM)
	* @return OVALS
	*/
	public OVALS fromXML() throws SystemFailure {
        
        try {
   			// create a JAXBContext capable of handling classes generated into
    		// the generated package
    		JAXBContext jc = JAXBContext.newInstance( OVALSUSPSTag.OVALS_JAXB_PACKAGE );  // package name
        
    		// create an Unmarshaller
    		Unmarshaller u = jc.createUnmarshaller();

    		// unmarshal a generated instance document into a tree of Java content
    		// objects composed of classes from the generated package.
    		return ((OVALS)u.unmarshal( new InputSource(new StringReader(getXML()))));
            
        } 
        catch( JAXBException je ) {
			handleSystemFailure(je.getMessage());
        } 
        return null;
 	}

	/**
 	* Returns OVALS USPS request in form of XML string
  	* Creation date: (3/26/02 7:52:09 AM)
	* @return String
	*/
	public String toXML() throws SystemFailure
    {
       try {
   			// create a JAXBContext capable of handling classes generated into
    		// the generated package
    		JAXBContext jc = JAXBContext.newInstance( OVALSUSPSTag.OVALS_JAXB_PACKAGE );  // package name

            // create a Marshaller and marshal to a string
            StringWriter temp = new StringWriter();
            Marshaller m = jc.createMarshaller();
            m.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE );
            m.marshal( getOvals(), temp );
           	return temp.toString();
            
        } 
        catch( JAXBException je ) {
			handleSystemFailure(je.getMessage());
        }
        
        return null; 
 	}

	/**
	 * Returns the ovals.
	 * @return OVALS
	 */
	public OVALS getOvals() throws SystemFailure {
		setOvals();
		return ovals;
	}

	/**
	 * Sets the ovals.
	 * @param ovals The ovals to set
	 */
	public void setOvals() throws SystemFailure {
 
    	try {
       		// Set CASS address line length if option provided in BisContext
    		setAdrll();
    		       
       		// create a JAXBContext capable of handling classes generated into
      		// the generated package
       		JAXBContext jc = JAXBContext.newInstance( OVALSUSPSTag.OVALS_JAXB_PACKAGE );  // package name

       		// create an Unmarshaller
       		Unmarshaller u = jc.createUnmarshaller();

       		// unmarshal a generated instance document into a tree of Java content
       		// objects composed of classes from the generated package.
			//  use tag/class name for XML document e.g. <USPSVALIDATE>
           	StringBuffer sb = new StringBuffer();
           	sb.append(OVALSUSPSTag.XML_PROLOG).append(OVALSUSPSTag.XML_OPEN_REQ);
           	sb.append(OVALSUSPSTag.XML_ADDRESS);

           	if (getAdrll() !=null && getAdrll().length() > 0)
           		sb.append(OVALSUSPSTag.XML_ADRLL);
           		
           	sb.append(OVALSUSPSTag.XML_CLOSING_BRACKET);
           	sb.append(OVALSUSPSTag.XML_CLOSE_REQ);
            
           	this.ovals = 
               	(OVALS)u.unmarshal( new InputSource(new StringReader(sb.toString())));

			((ADDRESSTypeImpl) ovals.getUSPSVALIDATE().getREQUEST().getADDRESS().get(0)).setLINE1(getUspsAddr().getLine1());
			((ADDRESSTypeImpl) ovals.getUSPSVALIDATE().getREQUEST().getADDRESS().get(0)).setLINE2(getUspsAddr().getLine2());
			((ADDRESSTypeImpl) ovals.getUSPSVALIDATE().getREQUEST().getADDRESS().get(0)).setLINE3(getUspsAddr().getLine3());
			((ADDRESSTypeImpl) ovals.getUSPSVALIDATE().getREQUEST().getADDRESS().get(0)).setLINE4(getUspsAddr().getLine4());
			((ADDRESSTypeImpl) ovals.getUSPSVALIDATE().getREQUEST().getADDRESS().get(0)).setLINE5(getUspsAddr().getLine5());

            if( isIncudeCSZ() )
            {
                ((ADDRESSTypeImpl) ovals.getUSPSVALIDATE().getREQUEST().getADDRESS().get(0)).setCSZCITY(getUspsAddr().getCity());
                ((ADDRESSTypeImpl) ovals.getUSPSVALIDATE().getREQUEST().getADDRESS().get(0)).setCSZSTATE(getUspsAddr().getState());
                ((ADDRESSTypeImpl) ovals.getUSPSVALIDATE().getREQUEST().getADDRESS().get(0)).setCSZZIP(getUspsAddr().getPostalCode());
            }

           	if (getAdrll() !=null && getAdrll().length() > 0)
           		((ADDRESSTypeImpl) ovals.getUSPSVALIDATE().getREQUEST().getADDRESS().get(0)).setADRLL(getAdrll());						

			Date today = new java.util.Date();
			SimpleDateFormat formatter = new SimpleDateFormat( "yyyyMMddHHmmssSSS" ) ;
			String dateString = formatter.format( today ) ;
			// get random number between 1000 and 9999
			int randomNbr = 1000 + (int)(9000*Math.random());  

			((ADDRESSTypeImpl) ovals.getUSPSVALIDATE().getREQUEST().getADDRESS().get(0)).setID("LIMBIS" + dateString + randomNbr);
    	}
        catch( JAXBException je ) {
			handleSystemFailure(je.getMessage());
        } 
	}

	/**
	 * Returns the adrll.
	 * @return String
	 */
	public String getAdrll() {
		return adrll;
	}

	/**
	 * Sets the adrll.
	 * @param adrll The adrll to set
	 */
	public void setAdrll() {
		LIMBisContextManager limBisContextManager = new LIMBisContextManager (limBase.getCallerContext()); 
		try{
			if (((limBisContextManager.getAddressCassMaximumLengthPerLine() != null) &&
				 (limBisContextManager.getAddressCassMaximumLengthPerLine().trim()).length() > 0) &&
				  limBase.allNumeric((limBisContextManager.getAddressCassMaximumLengthPerLine().trim()))){
			  this.adrll = limBisContextManager.getAddressCassMaximumLengthPerLine().trim();
			}
		}
		catch(Exception e){}
	}

	/**
	 * Returns the uspsAddr.
	 * @return AddressHandlerUSPS
	 */
	public AddressHandlerUSPS getUspsAddr() {
		return uspsAddr;
	}

	/**
	 * Sets the uspsAddr.
	 * @param uspsAddr The uspsAddr to set
	 */
	public void setUspsAddr(AddressHandlerUSPS uspsAddr) {
		this.uspsAddr = uspsAddr;
	}

	/**
	 * Returns the xml.
	 * @return String
	 */
	public String getXML() {
		return xml;
	}

	/**
	 * Sets the xml.
	 * @param xml The xml to set
	 */
	public void setXML(String xml) {
		this.xml = xml;
	}

	/**
	 * Returns the LimBase.
	 * @return LIMBase
	 */
	public LIMBase getLimBase() {
		return limBase;
	}

	/**
	 * Sets the limBase.
	 * @param limBase The LimBase to set
	 */
	public void setLimBase(LIMBase aLimBase) {
		this.limBase = aLimBase;
	}


/**
 * Invoke handleException() to create a SystemFailure exception.  
 * Hide the otherexception types.
 * Creation date: (7/28/03 4:59:19 PM)
 * @param text String
 * @exception SystemFailure
 */
public void handleSystemFailure(String text) throws SystemFailure
{
	try
	{
        String ruleFile = (String) limBase.getEnv(LIMTag.CSV_FileName_OVALS_USPS);		
		
		
		ExceptionBuilderResult exBldReslt = null;
		Properties tagValues = new Properties();
		tagValues.setProperty("MSG", text);

		exBldReslt =
				ExceptionBuilder.parseException(
					limBase.getCallerContext(),
					ruleFile,
					"",
					LIMTag.CSV_AddressError,
					"XML Parsing Exception",
					true,
					ExceptionBuilderRule.NO_DEFAULT,
					null,
					null,
					limBase,
					"OVALS_USPS",
					Severity.UnRecoverable,
					tagValues);
	}
	catch (InvalidData e) {}
	catch (BusinessViolation e) {}
	catch (DataNotFound e) {}
	catch (AccessDenied e) {}
	catch (ObjectNotFound e) {}
	catch (NotImplemented e) {}
}


    /**
     * Returns the incudeCSZ.
     * @return boolean
     */
    public boolean isIncudeCSZ()
    {
        return incudeCSZ;
    }

    /**
     * Sets the incudeCSZ.
     * @param incudeCSZ The incudeCSZ to set
     */
    public void setIncudeCSZ(boolean incudeCSZ)
    {
        this.incudeCSZ = incudeCSZ;
    }

}
