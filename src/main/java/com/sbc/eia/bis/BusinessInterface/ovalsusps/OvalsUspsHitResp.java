// $Id: OvalsUspsHitResp.java,v 1.4 2003/04/28 19:51:35 dl6543 Exp $

package com.sbc.eia.bis.BusinessInterface.ovalsusps;

import java.util.Properties;
import java.util.Vector;

import com.sbc.bccs.idl.helpers.IDLUtil;
import com.sbc.eia.bis.lim.jaxb.ovalsusps.impl.SAGENTTypeImpl;
import com.sbc.eia.bis.lim.transactions.RetrieveLocation.AddrMatchResp;
import com.sbc.eia.bis.lim.transactions.RetrieveLocation.ProviderLocationPropertyBuilder;
import com.sbc.eia.bis.lim.transactions.RetrieveLocation.RetrieveLocReq;
import com.sbc.eia.bis.lim.util.LIMBase;
import com.sbc.eia.idl.bis_types.SystemFailure;
import com.sbc.eia.idl.lim.helpers.AddressHandlerException;
import com.sbc.eia.idl.lim.helpers.AddressHandlerUSPS;
import com.sbc.eia.idl.lim_types.AddressOpt;
import com.sbc.eia.idl.lim_types.ProviderLocationProperty;

/**
 * This class represents an exact-match from OVALS USPS.
 * Creation date: (7/28/03 9:37:38 AM)
 * @author: David Brawley
 */
public class OvalsUspsHitResp extends AddrMatchResp {
    
    protected static String addCassAdditionalAddressInfo  = null;

    private RetrieveLocReq m_request = null;
	protected SAGENTTypeImpl sagent = null;
	protected String application = "";
	protected String warning = "";
    
	/**
	 * Construct this object from a OVALS USPS "hit" response.
	 * @param utility LIMBase
	 * @param aSagent SAGENTType
	 * @exception SystemFailure
	 */
	public OvalsUspsHitResp(LIMBase utility, RetrieveLocReq request, SAGENTTypeImpl aSagent, 
				String aApplication, String aWarning)
		throws SystemFailure 
	{
		super(utility);
		setSagent(aSagent);
		setApplication(aApplication);
		setWarning(aWarning);
        setRequest(request);
	}

	/**
	 * Compares two objects for equality. Returns a boolean that indicates
	 * whether this object is equivalent to the specified object. This method
	 * is used when an object is stored in a hashtable.
	 * @param obj the Object to compare with
	 * @return true if these Objects are equal; false otherwise.
	 * @see Hashtable
	 */
	public boolean equals(Object obj) {
		if (obj instanceof OvalsUspsHitResp) {
			return hashString().equals(((OvalsUspsHitResp) obj).hashString());
		}
		return false;
	}

	/**
	 * Generates a hash code for the receiver.
	 * This method is supported primarily for
	 * hash tables, such as those provided in java.util.
	 * @return an integer hash code for the receiver
	 * @see Hashtable
	 */
	public int hashCode() {
		return hashString().hashCode();
	}
	/**
	 * Return a String to be used by methods equals() and hashCode().
	 * Creation date: (3/13/01 3:11:37 PM)
	 * @return String
	 */
	protected String hashString() {

		return (
				sagent.getADDRESSLINE()
				+ sagent.getAMQ()
				+ sagent.getAUX1()
				+ sagent.getAUX2()
				+ sagent.getAUX3()
				+ sagent.getAUX4()
				+ sagent.getAUX5()
				+ sagent.getAUX1()
				+ sagent.getCITY()
				+ sagent.getCOUNTYNAME()
                + sagent.getCSZVALID()
				+ sagent.getH()
				+ sagent.getHOUSENUMBER()
				+ sagent.getID()
				+ sagent.getLASTLINE()
				+ sagent.getLATITUDE()
				+ sagent.getLINE1()
				+ sagent.getLINE2()
				+ sagent.getLINE3()
				+ sagent.getLINE4()
				+ sagent.getLINE5()
				+ sagent.getLOCATIONQUALITYCODE()
				+ sagent.getLONGITUDE()
				+ sagent.getMATCHCODE()
				+ sagent.getPOSTFIXDIRECTION()
				+ sagent.getPREFIXDIRECTION()
				+ sagent.getSTATE()
				+ sagent.getSTREETNAME()
				+ sagent.getSTREETTYPE()
				+ sagent.getUNITNUMBER()
                + sagent.getUNITNUMBER2()
                + sagent.getUNITTYPE()
                + sagent.getUNITTYPE2()
				+ sagent.getV()
				+ sagent.getZIP()
				+ sagent.getZIP4());
						
	}

	/**
	 * Returns the application.
	 * @return String
	 */
	public String getApplication() {
		return application;
	}

	/**
	 * Sets the application.
	 * @param application The application to set
	 */
	public void setApplication(String application) {
		this.application = application;
	}

	/**
	 * Returns the sagent.
	 * @return SAGENTTypeImpl
	 */
	public SAGENTTypeImpl getSagent() {
		return sagent;
	}

	/**
	 * Sets the sagent.
	 * @param sagent The sagent to set
	 */
	public void setSagent(SAGENTTypeImpl sagent) {
		this.sagent = sagent;
	}

	/**
	 * Returns the warning.
	 * @return String
	 */
	public String getWarning() {
		return warning;
	}

	/**
	 * Sets the warning.
	 * @param warning The warning to set
	 */
	public void setWarning(String warning) {
		this.warning = warning;
	}

    /**
     * @see com.sbc.eia.bis.lim.transactions.RetrieveLocation.AddrMatchResp#setLocationId()
     */
    protected void setLocationId()
    {
        locationId = IDLUtil.toOpt("");
    }

    /**
     * @see com.sbc.eia.bis.lim.transactions.RetrieveLocation.AddrMatchResp#setProviderLocationPropertySeq()
     */
    protected void setProviderLocationPropertySeq()
    {
        // set the array        
        providerLocationPropertyArray = new ProviderLocationProperty[]{
            toProviderLocationProperty()};
        
    }

    protected ProviderLocationProperty toProviderLocationProperty()
    {
 
        AddressOpt addressOpt = null;
        
        try{
            Vector lines = new Vector();
            if ( sagent.getLINE1() != null &&  sagent.getLINE1().trim().length() > 0 )
            {
                lines.add(sagent.getLINE1().trim());
            }
                
            if ( sagent.getLINE2() != null && sagent.getLINE2().trim().length() > 0 )
            {
                lines.add(sagent.getLINE2().trim());
            }
                
            if ( sagent.getLINE3() != null && sagent.getLINE3().trim().length() > 0 )
            {
                lines.add(sagent.getLINE3().trim());
            }
                
            if ( sagent.getLINE4() != null && sagent.getLINE4().trim().length() > 0 )
            {
                lines.add(sagent.getLINE4().trim());
            }
                
            if ( sagent.getLINE5() != null && sagent.getLINE5().trim().length() > 0 )
            {
                lines.add(sagent.getLINE5().trim());
            }

            String[] cassAddrLines = null;
            lines.copyInto((cassAddrLines = new String[lines.size()]));

            lines = new Vector();
            
            if ( sagent.getAUX1() != null && (sagent.getAUX1().trim()).length() > 0)
                lines.add(sagent.getAUX1().trim());
            if ( sagent.getAUX2() != null && (sagent.getAUX2().trim()).length() > 0)
                lines.add(sagent.getAUX2().trim());
            if ( sagent.getAUX3() != null && (sagent.getAUX3().trim()).length() > 0)
                lines.add(sagent.getAUX3().trim());
            if ( sagent.getAUX4() != null && (sagent.getAUX4().trim()).length() > 0)
                lines.add(sagent.getAUX4().trim());
            if ( sagent.getAUX5() != null && (sagent.getAUX5().trim()).length() > 0)
                lines.add(sagent.getAUX5().trim());
            
            String[] auxLines = null;
            lines.copyInto((auxLines = new String[lines.size()]));

            AddressHandlerUSPS handlerUSPS = new AddressHandlerUSPS(
                        sagent.getCITY().toUpperCase().trim(),              // aCity
                        sagent.getCOUNTYNAME().toUpperCase().trim(),        // aCounty
                        sagent.getHOUSENUMBER().toUpperCase().trim(),       // aHouseNumber
                        sagent.getPREFIXDIRECTION().toUpperCase().trim(),   // aStreetDirection
                        sagent.getPOSTFIXDIRECTION().toUpperCase().trim(),  // aStreetNameSuffix
                        sagent.getSTATE().toUpperCase().trim(),             // aState
                        sagent.getSTREETNAME().toUpperCase().trim(),        // aStreetName
                        sagent.getSTREETTYPE().toUpperCase().trim(),        // aStreetThoroughfare
                        sagent.getUNITNUMBER().toUpperCase().trim(),        // Unit_value
                        sagent.getUNITNUMBER2().toUpperCase().trim(),        // Unit_value2
                        sagent.getUNITTYPE().toUpperCase().trim(),          // Unit_type
                        sagent.getUNITTYPE2().toUpperCase().trim(),          // Unit_type2
                        sagent.getZIP().toUpperCase().trim(),
                        sagent.getZIP4().toUpperCase().trim(),
                        cassAddrLines,
                        auxLines,
                        getCassAdditionalAddressInfo()
                        );

            addressOpt = handlerUSPS.toAddressOpt();
                        
        }
        catch (AddressHandlerException ahe) {}
        
        ProviderLocationPropertyBuilder propertyBuilder = new ProviderLocationPropertyBuilder(getRequest().getLocationPropertiesRequested());
        try
        {
            propertyBuilder.setProviderName( getRequest().getProviderLocationPropertiesToGet()[0].aProviderName.theValue() );
        }
        catch (org.omg.CORBA.BAD_OPERATION e) 
        {
            propertyBuilder.setProviderName (null);
        }
        catch (java.lang.NullPointerException e) 
        {
            propertyBuilder.setProviderName (null);
        }
        propertyBuilder.setPostalAddress(addressOpt);
        propertyBuilder.setAddressMatchCode( sagent.getAMQ(), true );
        propertyBuilder.setAddressMatchCodeDescription( "", false );
        propertyBuilder.setHorizontalCoordinate(sagent.getH(), true);
        propertyBuilder.setVerticalCoordinate(sagent.getV(), true);

        if(sagent.getCSZVALID() != null)
        {
            propertyBuilder.setCityStatePostalCodeValid (sagent.getCSZVALID());
        }
        
        return propertyBuilder.getProviderLocationProperty();
    }

    public String getCassAdditionalAddressInfo() {

        String cassAddInfo = null; 
        if (addCassAdditionalAddressInfo == null){
            if (((Properties)(getUtility().getPROPERTIES())).getProperty(OVALSUSPSTag.OVALSUSPS_CASS_ADDITIONAL_ADDRESS) != null)
                addCassAdditionalAddressInfo = ((Properties)(getUtility().getPROPERTIES())).getProperty(OVALSUSPSTag.OVALSUSPS_CASS_ADDITIONAL_ADDRESS).trim();                          
            else
                addCassAdditionalAddressInfo = "FALSE";
        }

        if ((addCassAdditionalAddressInfo.equalsIgnoreCase("TRUE"))
            && (getWarning() != null && getWarning().length() > 0))
            cassAddInfo = getWarning();
            
        return cassAddInfo;
    }

    private void setRequest(RetrieveLocReq request)
    {
        m_request = request;
    }

    private RetrieveLocReq getRequest()
    {
        return m_request;
    }

}
