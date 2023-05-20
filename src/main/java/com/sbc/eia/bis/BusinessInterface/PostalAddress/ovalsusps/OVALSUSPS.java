// $Id: OVALSUSPS.java,v 1.3 2008/01/17 21:58:14 jh9785 Exp $

package com.sbc.eia.bis.BusinessInterface.PostalAddress.ovalsusps;


import java.util.Hashtable;
import java.util.Properties;

import javax.jms.JMSException;
import javax.xml.bind.JAXBException;

import com.att.lms.bis.service.ovals.OvalsPav;
import com.att.lms.bis.service.ovals.OvalsWrapper;
import com.sbc.bccs.idl.helpers.IDLUtil;
import com.sbc.bccs.utilities.Utility;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilder;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilderResult;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilderRule;
import com.sbc.eia.bis.embus.service.access.ServiceException;
import com.sbc.eia.bis.embus.service.access.ServiceTimeOutException;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.bis.lim.transactions.common.RetrieveLocResp;
import com.sbc.eia.bis.lim.util.LIMBase;
import com.sbc.eia.bis.lim.util.LIMTag;
import com.sbc.eia.idl.bis_types.AccessDenied;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.bis_types.BusinessViolation;
import com.sbc.eia.idl.bis_types.DataNotFound;
import com.sbc.eia.idl.bis_types.InvalidData;
import com.sbc.eia.idl.bis_types.NotImplemented;
import com.sbc.eia.idl.bis_types.ObjectNotFound;
import com.sbc.eia.idl.bis_types.SystemFailure;
import com.sbc.eia.idl.exception_types.ExceptionCode;
import com.sbc.eia.idl.lim.helpers.AddressHandlerException;
import com.sbc.eia.idl.lim.helpers.AddressHandlerUSPS;
import com.sbc.eia.idl.lim_types.Address;
import com.sbc.eia.idl.lim_types.ProviderLocationProperties;
import com.sbc.eia.idl.lim_types.SubmittedAddressExceptionSeqOpt;
import com.sbc.eia.idl.lim_types.UnfieldedAddress;
import com.sbc.eia.idl.types.ExceptionData;
import com.sbc.eia.idl.types.LongOpt;
import com.sbc.eia.idl.types.Severity;
import com.sbc.eia.idl.types.SeverityOpt;
import com.sbc.eia.idl.types.StringOpt;
import com.sbc.embus.common.EMBusException;
import com.sbc.eia.bis.lim.transactions.RetrieveLocationForPostalAddress.RetrieveLocationForPostalAddressTrans;

import com.sbc.eia.idl.lim.RetrieveLocationForPostalAddressReturn;

/**
 * This is the OVALSUSPS Host BusinessInterface class for the LIM RetrieveLocation
 * transactions.
 * Creation date: (2/28/01 11:20:02 AM)
 * @author: David Brawley
 */
public class OVALSUSPS
{
    private OvalsUspsServiceHelper ovalsUspsServiceHelper = null;
    private ExceptionBuilderResult exBldReslt = null;
    private String ruleFile =null;
    
    protected com.sbc.bccs.utilities.Utility utility = null;
	private java.lang.Object passThru = null;
	private java.util.Hashtable properties = null;
    
    /**
     * Construct a OVALSUSPS Host object.
     * @param aUtility Utility
     * @param aProperties Hashtable
     */
    public OVALSUSPS(Utility aUtility,
            		 Hashtable aProperties)
    {
        utility = aUtility;
    	properties = aProperties;
        ruleFile = (String) getProperties().get(LIMTag.CSV_FileName_OVALS_USPS);
    }
    
    /**
     * Returns the pass through object passed in by the caller when
     *	the getBusinessInterface method was invoked on Company.
     * Creation date: (1/22/01 12:39:22 PM)
     * @return java.lang.Object
     */
    public java.lang.Object getPassThru() 
    {
    	return passThru;
    }
    
    /**
     * Sets the pass through object passed in by the caller when
     *	the getBusinessInterface method is invoked on Company.
     * Creation date: (1/22/01 12:39:22 PM)
     * @param newPassThru java.lang.Object
     */
    public void setPassThru(java.lang.Object newPassThru) 
    {
    	passThru = newPassThru;
    }
    
    /**
     * Returns the properties passed in by the caller when
     *	the CompanyFactory was constructed.
     * Creation date: (1/31/01 10:37:51 AM)
     * @return java.util.Hashtable
     */
    public java.util.Hashtable getProperties() 
    {
    	return properties;
    }
    
    /**
     * Send the AddressValidation request to the OVALS USPS service and get the response.
     * Creation date: (3/28/01 3:36:26 PM)
     * @return RetrieveLocResp
     * @param request OvalsUspsRetrieveLocReq
     * @param maxAddressLimit LongOpt
     * @param maxUnitLimit LongOpt
     * @exception InvalidData
     * @exception AccessDenied
     * @exception BusinessViolation
     * @exception DataNotFound
     * @exception SystemFailure
     * @exception NotImplemented
     * @exception ObjectNotFound
     */
    protected RetrieveLocResp doPostalAddressValidation(OvalsUspsRetrieveLocReq request,
        LongOpt maxAddressLimit)
        throws ServiceException, InvalidData, AccessDenied, BusinessViolation, DataNotFound, SystemFailure, NotImplemented, ObjectNotFound
    {
        int maxAddr = getMaxValue(maxAddressLimit);
    
        RetrieveLocResp response = null;
    
        getUtility().log(LogEventId.REMOTE_CALL, OVALSUSPSTag.OVALSUSPS_MQ_BROKER, 
                    OVALSUSPSTag.OVALSUSPS_MQ_SERVICE, OVALSUSPSTag.OVALSUSPS,
                    OVALSUSPSTag.ADDRESS_SERVICE);
            
        response = OvalsUspsResponseFactory.handleUspsXMLFromOvals(getLimBase().limBase, getOvalsUspsServiceHelper().ovalsUspsRequestAndResponse(request),
            request, maxAddr);
            
        getUtility().log(LogEventId.REMOTE_RETURN, OVALSUSPSTag.OVALSUSPS_MQ_BROKER, 
                    OVALSUSPSTag.OVALSUSPS_MQ_SERVICE,  OVALSUSPSTag.OVALSUSPS,
                    OVALSUSPSTag.ADDRESS_SERVICE);
    
        if (response instanceof OvalsUspsListResp){
            getUtility().log(LogEventId.AUDIT_TRAIL,"OVALSUSPS::doAddressValidation()|OVALSUSPS::handleListResp()|PRE");
            handleListResp((OvalsUspsListResp) response);
            getUtility().log(LogEventId.AUDIT_TRAIL,"OVALSUSPS::doAddressValidation()|OVALSUSPS::handleListResp()|POST");
        }
    
        return response;
    }
    
    /**
     * Getter method for the BisContext.
     * Creation date: (3/20/01 4:07:35 PM)
     * @return BisContext
     */
    public BisContext getBisContext()
    {
        return getLimBase().limBase.getCallerContext();
    }
    
    /**
     * Getter for the RetrieveLocationForPostalAddressTrans object.
     * Creation date: (3/21/01 3:21:40 PM)
     * @return RetrieveLocationForPostalAddressTrans
     */
    protected RetrieveLocationForPostalAddressTrans getLimBase()
    {
        return (RetrieveLocationForPostalAddressTrans) getPassThru();
    }
    
    /**
     * Getter method for the LIM Properties.
     * Creation date: (3/20/01 5:00:10 PM)
     * @return Properties
     */
    public Properties getLimProps()
    {
        return (Properties) getLimBase().limBase.getPROPERTIES();
    }
    
    /**
     * Extract the int value from a max-value LongOpt object.
     * Creation date: (3/26/01 2:42:08 PM)
     * @return int
     * @param max LongOpt
     */
    protected int getMaxValue(LongOpt max)
    {
        try
        {
            if (max != null)
                return max.theValue();
        }
        catch (org.omg.CORBA.BAD_OPERATION bo) {}
    
        return 0;
    }
    
    /**
     * Getter method for the Utility object.
     * Creation date: (3/20/01 4:13:28 PM)
     * @return Utility
     */
    public Utility getUtility()
    {
        return utility;
    }
    
    /**
     * The street information was not exact match for input address. 
     * Handle it as an List CassAddress, instead of an ExactMatch response.
     * Creation date: (6/27/01 12:01:14 PM)
     * @param listResp  an OvalsUspsListResp object
     * @exception InvalidData
     * @exception AccessDenied
     * @exception BusinessViolation
     * @exception DataNotFound
     * @exception SystemFailure
     * @exception NotImplemented
     * @exception ObjectNotFound
     */
    protected void handleListResp(OvalsUspsListResp listResp)
        throws InvalidData, AccessDenied, BusinessViolation, DataNotFound, SystemFailure, NotImplemented, ObjectNotFound
    {
        getUtility().log(LogEventId.AUDIT_TRAIL,"OVALSUSPS::handleListResp()|OvalsUspsListResp::parseAddressTypeImpl()|PRE");       
            
        listResp.parseAddressTypeImpl();
     
        getUtility().log(LogEventId.AUDIT_TRAIL,"OVALSUSPS::handleListResp()|OvalsUspsListResp::parseAddressTypeImpl()|POST");
    
        if (listResp.getAddrCass().isEmpty()){
    
            exBldReslt =
                    ExceptionBuilder.parseException(
                        getBisContext(),
                        ruleFile,
                        "",
                        LIMTag.CSV_AddressError,
                        null,
                        true,
                        ExceptionBuilderRule.NO_DEFAULT,
                        null,
                        null,
                        utility,
                        "OVALS_USPS",
                        Severity.UnRecoverable,
                        null);
        }
    }
    
    /**
     * @see com.sbc.eia.bis.BusinessInterface.location.LocationI#retrieveLocationForAddress(Address, ProviderLocationProperties[], LongOpt, LongOpt, StringOpt)
     */
    public RetrieveLocationForPostalAddressReturn retrieveLocationForPostalAddress(
            BisContext aContext,
        Address aAddress,
        LongOpt aMaxAddressAlternatives)
        throws
            InvalidData,
            AccessDenied,
            BusinessViolation,
            DataNotFound,
            SystemFailure,
            NotImplemented,
            ObjectNotFound
    {

	        OvalsWrapper wrapper = new OvalsWrapper(utility,null, new OvalsPav(getProperties()),null, null);
	        
	        RetrieveLocationForPostalAddressReturn result =  wrapper.retrieveLocationForPostalAddress(
	                aContext,
	                aAddress,
	                aMaxAddressAlternatives
	        );
	        
	        if ((result.getAPostalAddressMatchResult().get___aAlternativePostalAddressResult() != null) &&
	        		result.getAPostalAddressMatchResult().get___aAlternativePostalAddressResult().getASubmittedAddressExceptions() != null) {
	        	SubmittedAddressExceptionSeqOpt exceptions = result.getAPostalAddressMatchResult().get___aAlternativePostalAddressResult().getASubmittedAddressExceptions();    
	        	
	        	if (exceptions.theValue() != null) {
		            throw new DataNotFound (
		                    result.getAContext(),
		                    new ExceptionData(
		                            ExceptionCode.ERR_LIM_OVALS_FATAL,
		                            " " + exceptions.theValue()[0].aDescription.theValue(),
		                            IDLUtil.toOpt("OVALS_GIS"),
		                            (SeverityOpt) IDLUtil.toOpt(SeverityOpt.class, Severity.UnRecoverable)));
	        	} else {
	        		throw new SystemFailure(
		                    result.getAContext(),
		                    new ExceptionData(
		                            ExceptionCode.ERR_LIM_OVALS_FATAL,
		                            " " ,
		                            IDLUtil.toOpt("OVALS_GIS"),
		                            (SeverityOpt) IDLUtil.toOpt(SeverityOpt.class, Severity.UnRecoverable)));
	        	}
	        } else {
	        	return result;
	        }

        
        /*
        try
        {
            AddressHandlerUSPS uspsAddr = new AddressHandlerUSPS(aAddress);
            OvalsUspsRetrieveLocReq request = new OvalsUspsRetrieveLocReq(getLimBase().limBase, uspsAddr, getLimBase().getIsCSZ());
   
            request.logAddressReq();
        
            getUtility().log(LogEventId.AUDIT_TRAIL,"OVALSUSPS::getLocationPropertiesByAddress()|OVALSUSPS::doAddressValidation()|PRE");        
                result = (doPostalAddressValidation(request, aMaxAddressAlternatives)).toPostalAddressReturn();
            getUtility().log(LogEventId.AUDIT_TRAIL,"OVALSUSPS::getLocationPropertiesByAddress()|OVALSUSPS::doAddressValidation()|POST");
        }
        catch ( ServiceException serviceException ) 
        {
            handleServiceException(serviceException);
        }
        catch (AddressHandlerException ahe)
        {
            exBldReslt =
                    ExceptionBuilder.parseException(
                        getBisContext(),
                        ruleFile,
                        "",
                        LIMTag.CSV_AddressHandlerError,
                        ahe.getMessage(),
                        true,
                        ExceptionBuilderRule.NO_DEFAULT,
                        null,
                        null,
                        utility,
                        "OVALS_USPS",
                        Severity.UnRecoverable,
                        null);
        }
        return result;
        */
    }
    
    protected void setOvalsUspsServiceHelper(OvalsUspsServiceHelper ovalsUspsServiceHelper)
    {
        this.ovalsUspsServiceHelper = ovalsUspsServiceHelper;
    }
    
    protected OvalsUspsServiceHelper getOvalsUspsServiceHelper()
        throws ServiceException
    {
        if (ovalsUspsServiceHelper == null)
        {
            setOvalsUspsServiceHelper(
                new OvalsUspsServiceHelper(
                    getLimProps(),
                    getLimBase().limBase));
        }

        return ovalsUspsServiceHelper;
    }
    /**
     * Method handleException examines the type of exception encountered and
     * while reponse accordingly.
     * @param e Exception the exception to be handled.
     * @throws InvalidData
     * @throws AccessDenied
     * @throws BusinessViolation
     * @throws SystemFailure
     * @throws NotImplemented
     * @throws ObjectNotFound
     * @throws DataNotFound
     */
    public void handleServiceException(ServiceException serviceException) 
        throws
            InvalidData,
            AccessDenied,
            BusinessViolation,
            SystemFailure,
            NotImplemented,
            ObjectNotFound,
            DataNotFound 
    {
                
        if ( serviceException.getOriginalException() != null && serviceException.getOriginalException() instanceof JAXBException ) 
        {
            Properties tagValues = new Properties();
            tagValues.setProperty("MSG", serviceException.getOriginalException().getMessage());
    
            exBldReslt =
                    ExceptionBuilder.parseException(
                        getBisContext(),
                        ruleFile,
                        "",
                        LIMTag.CSV_AddressError,
                        "XML Parsing Exception",
                        true,
                        ExceptionBuilderRule.NO_DEFAULT,
                        null,
                        null,
                        getUtility(),
                        "OVALS_USPS",
                        Severity.UnRecoverable,
                        tagValues);
        } 
        else if ( serviceException instanceof ServiceTimeOutException ) 
        {
            Properties p = new Properties();
            p.setProperty("SYSTEM", "OvalsUsps");

            ExceptionBuilder.parseException(
                getBisContext(),
                (String) getProperties().get(LIMTag.CSV_FileName_LIM),
                "",
                LIMTag.CSV_LimTimedOutCode,
                "Time out",
                true,
                ExceptionBuilderRule.NO_DEFAULT,
                null,
                serviceException,
                getUtility(),
                "OvalsUspsServiceAccess",
                Severity.Recoverable,
                p);
                
        } 
        else if ( serviceException.getOriginalException() != null && serviceException.getOriginalException() instanceof JMSException ) 
        {
            Properties p = new Properties();

            String msg = "";
            if(serviceException.getOriginalException() != null)
            {
                msg = serviceException.getOriginalException().getMessage() ;
                msg = msg != null ? msg : "";
            }
            
            p.setProperty("MSG", msg );
            
            ExceptionBuilder.parseException(
                getBisContext(),
                ruleFile,
                "",
                LIMTag.CSV_InternalError,
                "JMS Exception",
                true,
                ExceptionBuilderRule.NO_DEFAULT,
                null,
                serviceException,
                getUtility(),
                "OvalsUspsServiceAccess",
                Severity.UnRecoverable,
                p);

       } 
       else if ( serviceException.getOriginalException() instanceof EMBusException ) 
       {
            Properties p = new Properties();
            p.setProperty("MSG", serviceException.getMessage());
            
            ExceptionBuilder.parseException(
                getBisContext(),
                ruleFile,
                "",
                LIMTag.CSV_InternalError,
                "EMBus Exception",
                true,
                ExceptionBuilderRule.NO_DEFAULT,
                null,
                serviceException,
                getUtility(),
                "OvalsUspsServiceAccess",
                Severity.UnRecoverable,
                p);
            
        } 
        else 
        {

            ExceptionBuilder.parseException(
                getBisContext(),
                ruleFile,
                "",
                null,
                null,
                true,
                ExceptionBuilderRule.NO_DEFAULT,
                null,
                serviceException,
                getUtility(),
                "OvalsUspsServiceAccess",
                Severity.UnRecoverable,
                null);

        }
    }

    /**
     * @see java.lang.Object#finalize()
     */
    protected void finalize() throws Throwable
    {
        super.finalize();
        try {
            getUtility().log(LogEventId.AUDIT_TRAIL, "OVALSUSPS::finalize()|OvalsUspsServiceHelper::exit()");
            /* close resources. */
            ovalsUspsServiceHelper.exit();
        } catch ( Exception ignored ) {
            /* ignore any errors */
        }   

    }
}