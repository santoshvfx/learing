// $Id: OVALSUSPS.java,v 1.6 2007/09/25 23:21:36 jh9785 Exp $

package com.sbc.eia.bis.BusinessInterface.ovalsusps;


import java.util.Hashtable;
import java.util.Properties;

import javax.jms.JMSException;
import javax.xml.bind.JAXBException;

import com.att.lms.bis.service.ovals.OvalsAvsqub;
import com.att.lms.bis.service.ovals.OvalsWrapper;
import com.sbc.bccs.idl.helpers.TN;
import com.sbc.bccs.utilities.Utility;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilder;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilderResult;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilderRule;
import com.sbc.eia.bis.BusinessInterface.Company;
import com.sbc.eia.bis.BusinessInterface.Host;
import com.sbc.eia.bis.BusinessInterface.InvalidCompanyException;
import com.sbc.eia.bis.BusinessInterface.InvalidStateException;
import com.sbc.eia.bis.BusinessInterface.NullDataException;
import com.sbc.eia.bis.BusinessInterface.Selector;
import com.sbc.eia.bis.BusinessInterface.State;
import com.sbc.eia.bis.BusinessInterface.location.LocationI;
import com.sbc.eia.bis.embus.service.access.ServiceException;
import com.sbc.eia.bis.embus.service.access.ServiceTimeOutException;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.bis.lim.transactions.RetrieveLocation.RetrieveLocResp;
import com.sbc.eia.bis.lim.transactions.RetrieveLocation.RetrieveLocationBase;
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
import com.sbc.eia.idl.lim.RetrieveLocationForAddressReturn;
import com.sbc.eia.idl.lim.RetrieveLocationForServiceReturn;
import com.sbc.eia.idl.lim_types.Address;
import com.sbc.eia.idl.lim_types.ProviderLocationProperties;
import com.sbc.eia.idl.types.LongOpt;
import com.sbc.eia.idl.types.Severity;
import com.sbc.eia.idl.types.StringOpt;
import com.sbc.embus.common.EMBusException;

/**
 * This is the OVALSUSPS Host BusinessInterface class for the LIM RetrieveLocation
 * transactions.
 * Creation date: (2/28/01 11:20:02 AM)
 * @author: David Brawley
 */
public class OVALSUSPS extends Host implements LocationI
{
    
    private static final String HostList[] = null;
    private OvalsUspsServiceHelper ovalsUspsServiceHelper = null;

    private static final String LIM_ORIGINATION = "LIM";
    private ExceptionBuilderResult exBldReslt = null;
    private String ruleFile =null;
    
    /**
     * Construct a OVALSUSPS Host object.
     * @param aCompany Company
     * @param aUtility Utility
     * @param aProperties Hashtable
     */
    public OVALSUSPS(Company aCompany,Utility aUtility,Hashtable aProperties)
    {
        super(aCompany, aUtility, aProperties);
        ruleFile = (String) getProperties().get(LIMTag.CSV_FileName_OVALS_USPS);
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
    protected RetrieveLocResp doAddressValidation( OvalsUspsRetrieveLocReq request,
        LongOpt maxAddressLimit, LongOpt maxUnitLimit )
        throws ServiceException, InvalidData, AccessDenied, BusinessViolation, DataNotFound, SystemFailure, NotImplemented, ObjectNotFound
    {
        int maxAddr = getMaxValue(maxAddressLimit);
        int maxUnit = getMaxValue(maxUnitLimit);
    
        RetrieveLocResp response = null;
    
        getUtility().log(LogEventId.REMOTE_CALL, OVALSUSPSTag.OVALSUSPS_MQ_BROKER, 
                    OVALSUSPSTag.OVALSUSPS_MQ_SERVICE,  OVALSUSPSTag.OVALSUSPS,
                    OVALSUSPSTag.ADDRESS_SERVICE);
            
        response = OvalsUspsResponseFactory.handleUspsXMLFromOvals( getLimBase().limBase, getOvalsUspsServiceHelper().ovalsUspsRequestAndResponse( request ),
            request, maxAddr, maxUnit);
            
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
     * Return a list of Selectors of company/state combinations that this class supports.
     * Creation date: (3/19/01 11:25:58 AM)
     * @return Selector[]
     * @param util Utility a utility object providing logging functions
     * @exception InvalidStateException attempted to create an invalid State
     * @exception NullDataException a required value is null
     * @exception InvalidCompanyException attempted to create an inalid company
     */
    public static Selector[] getCacheEntries (Utility util)
    throws InvalidStateException, NullDataException, InvalidCompanyException
    {
        util.log (LogEventId.INFO_LEVEL_2, "OVALSUSPS::getCacheEntries()");
        
        // Add entries to the HostFactory cache at start time to avoid long searches
        return new Selector[] {
            new Selector(new Company(OVALSUSPSTag.Company_USPostalService, State.getAnAnyState(), null, null),
                LocationI.LOCATION_INTERFACE_NAME, (OVALSUSPS.class).getName())
        };
    }
    
    /**
     * Return a list of host subclasses of this class.
     * Creation date: (3/19/01 10:36:20 AM)
     * @return String[] an array of host subclasses
     * @param util Utility a logging utility
     */
    public static String[] getHostList(Utility util)
    {
        util.log(LogEventId.INFO_LEVEL_2, "OVALSUSPS::getHostList()");
        return HostList;
    }
    /**
     * Return the business interfaces that this class implements.
     * Creation date: (3/19/01 10:38:34 AM)
     * @return String[] an array of business interface names
     * @param util Utility a logging utility
     */
    public static String[] getInterfaceList(Utility util)
    {
        util.log(LogEventId.INFO_LEVEL_2, "OVALSUSPS::getInterfaceList()");
        return new String[] { LocationI.LOCATION_INTERFACE_NAME };
    }
    /**
     * Getter for the RetrieveLocationBase object.
     * Creation date: (3/21/01 3:21:40 PM)
     * @return RetrieveLocationBase
     */
    protected RetrieveLocationBase getLimBase()
    {
        return (RetrieveLocationBase) getPassThru();
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
     * Return a list of supported companies.
     * Creation date: (3/19/01 10:02:48 AM)
     * @return Company a supported company
     * @param util Utility a logging utility
     * @exception InvalidStateException A state is invalid.
     * @exception NullDataException A required input parameter was null.
     * @exception InvalidCompanyException A company is invalid.
     */
    public static Company[] getSupportedCompanies(Utility util)
        throws InvalidStateException, NullDataException, InvalidCompanyException
    {
        util.log(LogEventId.INFO_LEVEL_2, "OVALSUSPS::getSupportedCompanies()");
        return new Company[] {
            new Company(Company.Company_Ameritech, State.getAnAnyState(), null, null)
            };
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
    public RetrieveLocationForAddressReturn retrieveLocationForAddress(
        Address aAddress,
        ProviderLocationProperties[] aLocationPropertiesToGet,
        LongOpt aMaximumBasicAddressReturnLimit,
        LongOpt aMaximumUnitReturnLimit,
        StringOpt aExchangeCode)
        throws
            InvalidData,
            AccessDenied,
            BusinessViolation,
            DataNotFound,
            SystemFailure,
            NotImplemented,
            ObjectNotFound
        {
    	RetrieveLocationForAddressReturn result = null;

        LIMBase limBase = getLimBase().limBase;

        OvalsWrapper wrapper = new OvalsWrapper(limBase,new OvalsAvsqub(limBase), null,null, null);
        return wrapper.retrieveLocationForAddressAvsqub(
                aAddress,aLocationPropertiesToGet,
                aMaximumBasicAddressReturnLimit,
                aMaximumUnitReturnLimit,aExchangeCode);
        /*
        try
        {
    
            AddressHandlerUSPS uspsAddr = new AddressHandlerUSPS(aAddress);
            OvalsUspsRetrieveLocReq request = new OvalsUspsRetrieveLocReq(getLimBase().limBase, uspsAddr, aLocationPropertiesToGet);
   
            request.logAddressReq();
        
            getUtility().log(LogEventId.AUDIT_TRAIL,"OVALSUSPS::getLocationPropertiesByAddress()|OVALSUSPS::doAddressValidation()|PRE");        
                result = (doAddressValidation( request, aMaximumBasicAddressReturnLimit, aMaximumUnitReturnLimit )).toAddressReturn();
            getUtility().log(LogEventId.AUDIT_TRAIL,"OVALSUSPS::getLocationPropertiesByAddress()|OVALSUSPS::doAddressValidation()|POST");
        }
        catch ( ServiceException serviceException ) {
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

    /**
     * @see com.sbc.eia.bis.BusinessInterface.location.LocationI#retrieveLocationForService(TN, ProviderLocationProperties[], LongOpt, LongOpt)
     */
    public RetrieveLocationForServiceReturn retrieveLocationForService(
        TN aService,
        ProviderLocationProperties[] aLocationPropertiesToGet,
        LongOpt aMaximumBasicAddressReturnLimit,
        LongOpt aMaximumUnitReturnLimit)
        throws
            InvalidData,
            AccessDenied,
            BusinessViolation,
            DataNotFound,
            SystemFailure,
            NotImplemented,
            ObjectNotFound
    {
    	RetrieveLocationForServiceReturn result = null;
        String limRuleFile = (String) getProperties().get(LIMTag.CSV_FileName_LIM);
        
        exBldReslt = 
                ExceptionBuilder.parseException(
                        getBisContext(),
                        limRuleFile,
                        "",
                        LIMTag.CSV_InternalError,
                        "Not Implemented",
                        true,
                        ExceptionBuilderRule.NO_DEFAULT,
                        null,
                        null,
                        utility,
                        "OVALS_USPS",
                        Severity.UnRecoverable,
                        null);
    
        return result;
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
                    getLimBase().limBase.getPROPERTIES(),
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
            DataNotFound {
                
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
        } else if ( serviceException instanceof ServiceTimeOutException ) {
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
                
        } else if ( serviceException.getOriginalException() != null && serviceException.getOriginalException() instanceof JMSException ) {
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

       } else if ( serviceException.getOriginalException() instanceof EMBusException ) {
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
            
        } else {

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