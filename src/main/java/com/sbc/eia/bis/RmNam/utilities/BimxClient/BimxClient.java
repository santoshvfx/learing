package com.sbc.eia.bis.RmNam.utilities.BimxClient;

/**
 * Connects to BIMX Beam.
 * Creation date: (11/01/07 1:20:00 PM)
 * @author: David Brawley
 */

import java.rmi.RemoteException;
import java.util.Properties;

import javax.ejb.CreateException;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.att.lms.bis.common.config.StaticContextAccessor;
import com.sbc.bccs.utilities.PropertiesFileLoader;
import com.sbc.bccs.utilities.Utility;
import com.sbc.eia.bis.RmNam.utilities.InitialContextFactory;
import com.sbc.eia.bis.RmNam.utilities.InitialContextFactoryException;
import com.sbc.eia.bis.framework.logging.LogEventId;
//import com.sbc.eia.bis.facades.bimx.ejb.BIMX;
//import com.sbc.eia.bis.facades.bimx.ejb.BIMXHome;
import com.sbc.eia.idl.bimx.BimxFacade;
import com.sbc.eia.idl.bis_types.AccessDenied;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.bis_types.BusinessViolation;
import com.sbc.eia.idl.bis_types.DataNotFound;
import com.sbc.eia.idl.bis_types.InvalidData;
import com.sbc.eia.idl.bis_types.NotImplemented;
import com.sbc.eia.idl.bis_types.ObjectNotFound;
import com.sbc.eia.idl.bis_types.SystemFailure;
import com.sbc.eia.idl.exception_types.ExceptionCode;
import com.sbc.eia.idl.types.ExceptionData;
import com.sbc.eia.idl.types.Severity;

public final class BimxClient
{
//    private BIMXHome bimxHome = null;
    String aProviderURL = null;
    String bimxHomeName = null;
    String origination = null;
    String initialContextPropertiesFile = null;
    /**
     * BimxClient constructor comment.
     */
    public BimxClient()
    {
    }
    /**
     * BimxClient constructor comment.
     */
/*    public BimxClient(
        String newProviderURL,
        String aBimxHomeName,
        String anOrigination,
        String anInitialContextPropertiesFile)
    {
        aProviderURL = newProviderURL;
        bimxHomeName = aBimxHomeName;
        origination = anOrigination;
        initialContextPropertiesFile = anInitialContextPropertiesFile;
    }*/


    private final BimxFacade getBimxBean(BisContext aContext, Utility aUtility)
            throws
            InvalidData,
            AccessDenied,
            BusinessViolation,
            SystemFailure,
            NotImplemented,
            ObjectNotFound,
            DataNotFound
    {
        return StaticContextAccessor.getBean(BimxFacade.class);
    }

    /**
     * Establishes BIMX Bean connection if it is not cached.
     * Creation date: (10/31/05 1:20:00 PM)
     * @param aContext com.sbc.eia.idl.bis_types.BisContext
     * @param aUtility com.sbc.bccs.utilities.Utility
     * @return com.sbc.eia.bis.facades.bimx.ejb.BIMX
     * @exception com.sbc.eia.idl.bis_types.InvalidData: An input parameter contained invalid data.
     * @exception com.sbc.eia.idl.bis_types.AccessDenied: Access to the specified domain object or information is not allowed.
     * @exception com.sbc.eia.idl.bis_types.BusinessViolation: The attempted action violates a business rule.
     * @exception com.sbc.eia.idl.bis_types.SystemFailure: The method could not be completed due to system level errors (e.g., out of memory, loss of network connectivity)n.
     * @exception com.sbc.eia.idl.bis_types.NotImplemented: The method has not been implemented.
     * @exception com.sbc.eia.idl.bis_types.ObjectNotFound: The desired domain object could not be found.
     * @exception com.sbc.eia.idl.bis_types.DataNotFound: No data found.
     */
    /*
    private final BIMX getBimxBean(BisContext aContext, Utility aUtility)
        throws
            InvalidData,
            AccessDenied,
            BusinessViolation,
            SystemFailure,
            NotImplemented,
            ObjectNotFound,
            DataNotFound
    {
        aUtility.log(LogEventId.DEBUG_LEVEL_2, "BimxClient::getBimxBean()");

        BIMX bimxBean = null;

        try
        {
            bimxBean = getBimxHome(aContext, aUtility).create();
        }
        catch (RemoteException e)
        {
            aUtility.log(
                LogEventId.INFO_LEVEL_1,
                "Caught RemoteException on BIMXHome::create(): "
                    + e.getMessage());
            // The home may be stale - get a new home
            try
            {
                bimxHome = null;
                bimxBean = getBimxHome(aContext, aUtility).create();
            }
            catch (RemoteException x)
            {
                bimxHome = null;
                aUtility.throwException(
                    ExceptionCode.ERR_BIMXCL_REMOTE_EXCEPTION,
                    "RemoteExeption: "
                        + "[ provider url<"
                        + aProviderURL
                        + "> home<"
                        + bimxHomeName
                        + "> ] "
                        + e.getMessage(),
                    origination,
                    Severity.UnRecoverable);
            }
            catch (CreateException x)
            {
                bimxHome = null;
                aUtility.throwException(
                    ExceptionCode.ERR_BIMXCL_CREATE_EXCEPTION,
                    "CreateException: "
                        + "[ provider url<"
                        + aProviderURL
                        + "> home<"
                        + bimxHomeName
                        + "> ] "
                        + e.getMessage(),
                    origination,
                    Severity.UnRecoverable);
            }
        }
        catch (CreateException e)
        {
            aUtility.log(
                LogEventId.INFO_LEVEL_1,
                "Caught CreateException on BIMXHome::create(): "
                    + e.getMessage());
            // The home may be stale - get a new home
            try
            {
                bimxHome = null;
                bimxBean = getBimxHome(aContext, aUtility).create();

            }
            catch (RemoteException x)
            {
                bimxHome = null;
                aUtility.throwException(
                    ExceptionCode.ERR_BIMXCL_REMOTE_EXCEPTION,
                    "RemoteExeption: "
                        + "[ provider url<"
                        + aProviderURL
                        + "> home<"
                        + bimxHomeName
                        + "> ] "
                        + e.getMessage(),
                    origination,
                    Severity.UnRecoverable);
            }
            catch (CreateException x)
            {
                bimxHome = null;
                aUtility.throwException(
                    ExceptionCode.ERR_BIMXCL_CREATE_EXCEPTION,
                    "CreateException: "
                        + "[ provider url<"
                        + aProviderURL
                        + "> home<"
                        + bimxHomeName
                        + "> ] "
                        + e.getMessage(),
                    origination,
                    Severity.UnRecoverable);
            }
        }

        return bimxBean;
    }*/

    /**
     * Calls BIMX Bean.
     * Creation date: (10/31/05 1:20:00 PM)
     * @param aContext com.sbc.eia.idl.bis_types.BisContext
     * @param aUtility com.sbc.bccs.utilities.Utility
     * @param aCode java.lang.String
     * @param aDescription java.lang.String
     * @return com.sbc.eia.bis.facades.bimx.ejb.BIMX
     * @exception com.sbc.eia.idl.bis_types.InvalidData: An input parameter contained invalid data.
     * @exception com.sbc.eia.idl.bis_types.AccessDenied: Access to the specified domain object or information is not allowed.
     * @exception com.sbc.eia.idl.bis_types.BusinessViolation: The attempted action violates a business rule.
     * @exception com.sbc.eia.idl.bis_types.SystemFailure: The method could not be completed due to system level errors (e.g., out of memory, loss of network connectivity)n.
     * @exception com.sbc.eia.idl.bis_types.NotImplemented: The method has not been implemented.
     * @exception com.sbc.eia.idl.bis_types.ObjectNotFound: The desired domain object could not be found.
     * @exception com.sbc.eia.idl.bis_types.DataNotFound: No data found.
     */
    public final BimxFacade getBimxConnection(
        BisContext aContext,
        Utility aUtility,
        String aCode,
        String aDescription)
        throws
            InvalidData,
            AccessDenied,
            BusinessViolation,
            SystemFailure,
            NotImplemented,
            ObjectNotFound,
            DataNotFound
    {

        aUtility.log(
            LogEventId.DEBUG_LEVEL_2,
            "BimxClient::getBimxConnection()");

        BimxFacade bimxBean = null;

        try
        {
            bimxBean = getBimxBean(aContext, aUtility);
        }
        catch (NotImplemented e)
        {
            throwBimxException(
                e.aExceptionData,
                aUtility,
                aCode,
                aDescription);
        }
        catch (SystemFailure e)
        {
            throwBimxException(
                e.aExceptionData,
                aUtility,
                aCode,
                aDescription);
        }
        catch (InvalidData e)
        {
            throwBimxException(
                e.aExceptionData,
                aUtility,
                aCode,
                aDescription);
        }
        catch (ObjectNotFound e)
        {
            throwBimxException(
                e.aExceptionData,
                aUtility,
                aCode,
                aDescription);
        }
        catch (AccessDenied e)
        {
            throwBimxException(
                e.aExceptionData,
                aUtility,
                aCode,
                aDescription);
        }
        catch (BusinessViolation e)
        {
            throwBimxException(
                e.aExceptionData,
                aUtility,
                aCode,
                aDescription);
        }
        catch (DataNotFound e)
        {
            throwBimxException(
                e.aExceptionData,
                aUtility,
                aCode,
                aDescription);
        }
        return bimxBean;
    }
    /**
     * Get a BIMX home.
     * Creation date: (10/31/05 1:20:00 PM)
     * @param aContext com.sbc.eia.idl.bis_types.BisContext
     * @param aUtility com.sbc.bccs.utilities.Utility
     * @return com.sbc.eia.bis.facades.bimx.ejb.BIMXHome
     * @exception com.sbc.eia.idl.bis_types.InvalidData: An input parameter contained invalid data.
     * @exception com.sbc.eia.idl.bis_types.AccessDenied: Access to the specified domain object or information is not allowed.
     * @exception com.sbc.eia.idl.bis_types.BusinessViolation: The attempted action violates a business rule.
     * @exception com.sbc.eia.idl.bis_types.SystemFailure: The method could not be completed due to system level errors (e.g., out of memory, loss of network connectivity)n.
     * @exception com.sbc.eia.idl.bis_types.NotImplemented: The method has not been implemented.
     * @exception com.sbc.eia.idl.bis_types.ObjectNotFound: The desired domain object could not be found.
     * @exception com.sbc.eia.idl.bis_types.DataNotFound: No data found.
     */
    /*
    private final BIMXHome getBimxHome(BisContext aContext, Utility aUtility)
        throws
            InvalidData,
            AccessDenied,
            BusinessViolation,
            SystemFailure,
            NotImplemented,
            ObjectNotFound,
            DataNotFound
    {
        aUtility.log(LogEventId.DEBUG_LEVEL_2, "BimxClient::getBimxHome()");

        if (bimxHome == null)
        {
            InitialContext initialContext = null;
            aUtility.log(
                LogEventId.DEBUG_LEVEL_2,
                "Get BIMX Bean: "
                    + "[ provider url<"
                    + aProviderURL
                    + "> home<"
                    + bimxHomeName
                    + ">");

            // Look for any properties for the Context factory
            Properties initialContextProperties = null;
            try
            {
                initialContextProperties =
                    PropertiesFileLoader.read(
                        initialContextPropertiesFile,
                        aUtility);
            }
            catch (Exception e)
            {
                initialContextProperties = new Properties();
            }
            InitialContextFactory aContextFactory =
                new InitialContextFactory(
                    aProviderURL,
                    initialContextProperties);

            try
            {
                aUtility.log(
                    LogEventId.DEBUG_LEVEL_2,
                    "BimxClient::initialContextProperties: "
                        + initialContextProperties.toString());
                initialContext =
                    aContextFactory.getInitialContext(
                        initialContextProperties);
            }
            catch (InitialContextFactoryException e)
            {
                aUtility.throwException(
                    ExceptionCode
                        .ERR_BIMXCL_CONTEXT_FACTORY_EXCEPTION,
                    "InitialContextFactoryException: "
                        + "[ provider url<"
                        + aProviderURL
                        + "> home<"
                        + bimxHomeName
                        + "> ] "
                        + e.getMessage(),
                    origination,
                    Severity.UnRecoverable);
            }

            try
            {
                Object o = initialContext.lookup(bimxHomeName);
                bimxHome =
                    (BIMXHome) javax.rmi.PortableRemoteObject.narrow(
                        o,
                        BIMXHome.class);
            }
            catch (NamingException e)
            {
                aUtility.throwException(
                    ExceptionCode.ERR_BIMXCL_NAMING_EXCEPTION,
                    "NamingException: "
                        + "[ provider url<"
                        + aProviderURL
                        + "> home<"
                        + bimxHomeName
                        + "> ] "
                        + e.getMessage(),
                    origination,
                    Severity.UnRecoverable);
            }
        }
        return bimxHome;
    }*/
    /**
     * Throws Bimx Exception.
     * Creation date: (10/31/05 1:20:00 PM)
     * @param aCode java.lang.String
     * @param aDescription java.lang.String
     * @param aExceptionData jcom.sbc.eia.idl.types.ExceptionData
     * @param aUtility com.sbc.bccs.utilities.Utility
     * @exception com.sbc.eia.idl.bis_types.InvalidData: An input parameter contained invalid data.
     * @exception com.sbc.eia.idl.bis_types.AccessDenied: Access to the specified domain object or information is not allowed.
     * @exception com.sbc.eia.idl.bis_types.BusinessViolation: The attempted action violates a business rule.
     * @exception com.sbc.eia.idl.bis_types.SystemFailure: The method could not be completed due to system level errors (e.g., out of memory, loss of network connectivity)n.
     * @exception com.sbc.eia.idl.bis_types.NotImplemented: The method has not been implemented.
     * @exception com.sbc.eia.idl.bis_types.ObjectNotFound: The desired domain object could not be found.
     * @exception com.sbc.eia.idl.bis_types.DataNotFound: No data found.
     */
    public void throwBimxException(
        ExceptionData anExceptionData,
        Utility aUtility,
        String anExceptionCode,
        String anExceptionMessage)
        throws
            InvalidData,
            AccessDenied,
            BusinessViolation,
            SystemFailure,
            NotImplemented,
            ObjectNotFound,
            DataNotFound
    {
        String aOrigination = null;
        Severity aSeverity = null;

        try
        {
            aOrigination = anExceptionData.aOrigination.theValue();
        }
        catch (Exception e)
        {
        }
        try
        {
            aSeverity = anExceptionData.aSeverity.theValue();
        }
        catch (Exception e)
        {
        }

        aUtility.throwException(
            anExceptionCode,
            anExceptionMessage
                + " ["
                + anExceptionData.aCode
                + " "
                + anExceptionData.aDescription
                + " ]",
            aOrigination,
            aSeverity);
    }
}

