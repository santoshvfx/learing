//$Id: RSAG.java,v 1.9 2007/10/22 18:58:00 gg2712 Exp $
package com.sbc.eia.bis.BusinessInterface.ServiceAddress.rsag;

import java.util.Hashtable;

import com.sbc.bccs.idl.helpers.TN;
import com.sbc.bccs.utilities.Utility;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilder;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilderRule;
import com.sbc.eia.bis.BusinessInterface.Company;
import com.sbc.eia.bis.BusinessInterface.Host;
import com.sbc.eia.bis.BusinessInterface.InvalidCompanyException;
import com.sbc.eia.bis.BusinessInterface.InvalidStateException;
import com.sbc.eia.bis.BusinessInterface.NullDataException;
import com.sbc.eia.bis.BusinessInterface.Selector;
import com.sbc.eia.bis.BusinessInterface.State;
import com.sbc.eia.bis.BusinessInterface.location.ServiceAddressLocationI;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.bis.lim.transactions.common.RetrieveServiceLocationBase;
import com.sbc.eia.bis.lim.util.LIMBase;
import com.sbc.eia.bis.lim.util.LIMBisContextManager;
import com.sbc.eia.bis.lim.util.LIMTag;
import com.sbc.eia.idl.bis_types.AccessDenied;
import com.sbc.eia.idl.bis_types.BusinessViolation;
import com.sbc.eia.idl.bis_types.DataNotFound;
import com.sbc.eia.idl.bis_types.InvalidData;
import com.sbc.eia.idl.bis_types.NotImplemented;
import com.sbc.eia.idl.bis_types.ObjectNotFound;
import com.sbc.eia.idl.bis_types.SystemFailure;
import com.sbc.eia.idl.bis_types.TelephoneNumber;
import com.sbc.eia.idl.lim.RetrieveLocationForServiceAddressReturn;
import com.sbc.eia.idl.lim.RetrieveLocationForServiceReturn;
import com.sbc.eia.idl.lim.RetrieveLocationForTelephoneNumberReturn;
import com.sbc.eia.idl.lim.helpers.AddressHandlerException;
import com.sbc.eia.idl.lim.helpers.AddressHandlerRSAG;
import com.sbc.eia.idl.lim_types.Address;
import com.sbc.eia.idl.types.Severity;

/**
 * @author GG2712
 */
public class RSAG extends Host implements ServiceAddressLocationI
{
    private static final String COMPANY_ATT_SE = "SE";
    private static final String HostList[] = null;
    private RsagController aRsagController = null;


    /**
     * Construct a RSAG Host object.
     * @param aCompany Company
     * @param aUtility Utility
     * @param aProperties Hashtable
     */
    public RSAG(Company aCompany, Utility aUtility, Hashtable aProperties)
    {
        super(aCompany, aUtility, aProperties);
    }

    /**
     * Retrieve location information by service address.
     * @param aAddress
     * @param aLocationPropertiesToGet
     * @param aPreviousOwnerName
     * @param aMaxBasicAddressAlternatives
     * @param aMaxLivingUnitAlternatives
     * @return RetrieveLocationForServiceAddressReturn
     * @throws InvalidData
     * @throws AccessDenied
     * @throws BusinessViolation
     * @throws DataNotFound
     * @throws SystemFailure
     * @throws NotImplemented
     * @throws ObjectNotFound
     */
    public RetrieveLocationForServiceAddressReturn retrieveLocationForServiceAddress(
            Address aAddress,
            String[] aLocationPropertiesToGet,
            String aPreviousOwnerName,
            String aCrossBoundaryState,
            int aMaxBasicAddressAlternatives,
            int aMaxLivingUnitAlternatives)
        throws InvalidData,
            AccessDenied,
            BusinessViolation,
            DataNotFound,
            SystemFailure,
            NotImplemented,
            ObjectNotFound
    {
        RetrieveLocationForServiceAddressReturn rlfsaReturn = null; 

        try
        {
            RsagRetrieveLocReq rsagRetrieveLocReq = 
                new RsagRetrieveLocReq(
                    getLimBase(),
                    new AddressHandlerRSAG(aAddress),
                    aLocationPropertiesToGet,
                    aPreviousOwnerName,
                    aCrossBoundaryState);
            
            rsagRetrieveLocReq.setMaxBasicAddressAlternatives(aMaxBasicAddressAlternatives);
            rsagRetrieveLocReq.setMaxLivingUnitAlternatives(aMaxLivingUnitAlternatives);
            rsagRetrieveLocReq.logAddressReq();
            
            rlfsaReturn = 
                getRsagController().validateAddressByAddress(rsagRetrieveLocReq).toServiceAddressReturn();
        }
        catch(AddressHandlerException ahe)
        {
            ExceptionBuilder.parseException(
                    getLimBase().getCallerContext(),
                    getLimBase().getLimRulesFile(),
                    "",
                    LIMTag.CSV_InternalError,
                    "Address parser error",
                    true,
                    ExceptionBuilderRule.NO_DEFAULT,
                    null,
                    null,
                    utility,
                    "RSAG",
                    Severity.UnRecoverable,
                    null);
        }
                
        return rlfsaReturn;
    }

    /**
     * Retrieve location information by telephone number.
     * @param aTelephoneNumber
     * @param aLocationPropertiesToGet
     * @return RetrieveLocationForTelephoneNumberReturn
     * @throws InvalidData
     * @throws AccessDenied
     * @throws BusinessViolation
     * @throws DataNotFound
     * @throws SystemFailure
     * @throws NotImplemented
     * @throws ObjectNotFound
     */
    public RetrieveLocationForTelephoneNumberReturn retrieveLocationForTelephoneNumber(
            TelephoneNumber aTelephoneNumber,
            String[] aLocationPropertiesToGet)
         throws InvalidData,
            AccessDenied,
            BusinessViolation,
            DataNotFound,
            SystemFailure,
            NotImplemented,
            ObjectNotFound
    {
        RsagRetrieveLocReq rsagRetrieveLocReq = 
            new RsagRetrieveLocReq(
                    getLimBase(),
                    new TN(aTelephoneNumber),
                    aLocationPropertiesToGet);
        
        rsagRetrieveLocReq.logAddressReq();
        
        return getRsagController().validateAddressByTN(rsagRetrieveLocReq).toTelephoneNumberReturn();
    }

    /**
     * getRsagController
     * @return RsagController
     */
    private RsagController getRsagController()
    {
        if(aRsagController == null)
        {
            aRsagController = new RsagController(getLimBase());
        }
        return aRsagController;
    }
    
    /**
     * getLimBase
     * @return LIMBase
     */
    protected LIMBase getLimBase()
    {
    	return ((RetrieveServiceLocationBase) getPassThru()).limBase;
    }
    
    /**
     * Return a list of Selectors of company/state combinations that this class
     * supports.
     * @return Selector[]
     * @param util Utility a utility object providing logging functions
     * @exception InvalidStateException attempted to create an invalid State
     * @exception NullDataException a required value is null
     * @exception InvalidCompanyException attempted to create an inalid company
     */
    public static Selector[] getCacheEntries(Utility util)
        throws InvalidStateException,
            NullDataException,
            InvalidCompanyException
    {
        util.log(LogEventId.INFO_LEVEL_2, "RSAG::getCacheEntries()");

        // Add entries to the HostFactory cache at start time to avoid long
        // searches
        return new Selector[] 
        {
            new Selector(new Company(COMPANY_ATT_SE, new State(State.State_Alabama), null, null),
                    ServiceAddressLocationI.LOCATION_INTERFACE_NAME, (RSAG.class).getName()),
            new Selector(new Company(COMPANY_ATT_SE, new State(State.State_Florida), null, null),
                    ServiceAddressLocationI.LOCATION_INTERFACE_NAME, (RSAG.class).getName()),
            new Selector(new Company(COMPANY_ATT_SE, new State(State.State_Georgia), null, null),
                    ServiceAddressLocationI.LOCATION_INTERFACE_NAME, (RSAG.class).getName()),
            new Selector(new Company(COMPANY_ATT_SE, new State(State.State_Kentucky), null, null),
                    ServiceAddressLocationI.LOCATION_INTERFACE_NAME, (RSAG.class).getName()),
            new Selector(new Company(COMPANY_ATT_SE, new State(State.State_Louisiana), null, null),
                    ServiceAddressLocationI.LOCATION_INTERFACE_NAME, (RSAG.class).getName()),
            new Selector(new Company(COMPANY_ATT_SE, new State(State.State_Mississippi), null, null),
                    ServiceAddressLocationI.LOCATION_INTERFACE_NAME, (RSAG.class).getName()),
            new Selector(new Company(COMPANY_ATT_SE, new State(State.State_NorthCarolina), null, null),
                    ServiceAddressLocationI.LOCATION_INTERFACE_NAME, (RSAG.class).getName()),
            new Selector(new Company(COMPANY_ATT_SE, new State(State.State_SouthCarolina), null, null),
                    ServiceAddressLocationI.LOCATION_INTERFACE_NAME, (RSAG.class).getName()),
            new Selector(new Company(COMPANY_ATT_SE, new State(State.State_Tennessee), null, null),
                    ServiceAddressLocationI.LOCATION_INTERFACE_NAME, (RSAG.class).getName()), 
        };
    }

    /**
     * Return a list of host subclasses of this class.
     * @return String[] an array of host subclasses
     * @param util Utility a logging utility
     */
    public static String[] getHostList(Utility util)
    {
        util.log(LogEventId.INFO_LEVEL_2, "RSAG::getHostList()");
        return HostList;
    }

    /**
     * Return the business interfaces that this class implements.
     * @return String[] an array of business interface names
     * @param util Utility a logging utility
     */
    public static String[] getInterfaceList(Utility util)
    {
        util.log(LogEventId.INFO_LEVEL_2, "RSAG::getInterfaceList()");
        return new String[] { ServiceAddressLocationI.LOCATION_INTERFACE_NAME };
    }

    /**
     * Return a list of supported companies.
     * @return Company a supported company
     * @param util Utility a logging utility
     * @exception InvalidStateException A state is invalid.
     * @exception NullDataException A required input parameter was null.
     * @exception InvalidCompanyException A company is invalid.
     */
    public static Company[] getSupportedCompanies(Utility util)
        throws InvalidStateException,
            NullDataException,
            InvalidCompanyException
    {
        util.log(LogEventId.INFO_LEVEL_2, "RSAG::getSupportedCompanies()");
        return new Company[] { new Company(COMPANY_ATT_SE, State.getAnAnyState(), null, null) };
    }
}
