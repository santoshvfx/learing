// $Id: SmClient.java,v 1.5 2003/03/31 21:11:16 as5472 Exp $

package com.sbc.eia.bis.RmNam.utilities.SmClient;

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
import com.sbc.eia.idl.bis_types.AccessDenied;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.bis_types.BusinessViolation;
import com.sbc.eia.idl.bis_types.DataNotFound;
import com.sbc.eia.idl.bis_types.InvalidData;
import com.sbc.eia.idl.bis_types.NotImplemented;
import com.sbc.eia.idl.bis_types.ObjectNotFound;
import com.sbc.eia.idl.bis_types.SystemFailure;
import com.sbc.eia.idl.exception_types.ExceptionCode;
import com.sbc.eia.idl.sm.SmFacade;
import com.sbc.eia.idl.types.ExceptionData;
import com.sbc.eia.idl.types.Severity;

/**
 * Connects to SM Bean.
 * Creation date: (4/26/01 2:57:56 PM)
 * @author: Hongmei Parkin
 */
public final class SmClient {
//	private SmFacadeHome smHome = null;

	String aProviderURL = null;
	String smHomeName = null;
	String origination = null;
	String initialContextPropertiesFile = null;
	/**
	 * SmClient constructor.
	 */
	public SmClient() {
		super();
	}
	/**
	 * SmClient constructor.
	 * Creation date: (9/17/01 3:57:07 PM)
	 * @param newProviderURL
	 * @param aSmHomeName
	 * @param anOrigination
	 * @param anInitialContextPropertiesFile
	 */
	/*public SmClient(
		String newProviderURL,
		String aSmHomeName,
		String anOrigination,
		String anInitialContextPropertiesFile) {
		aProviderURL = newProviderURL;
		smHomeName = aSmHomeName;
		origination = anOrigination;
		initialContextPropertiesFile = anInitialContextPropertiesFile;

	}*/
	/**
	 * Establishes Sm Bean connection if it is not cached.
	 * Creation date: (4/26/01 3:04:35 PM)
	 * @param aContext
	 * @param aUtility
	 * @return SmFacade
	 * @throws InvalidData: An input parameter contained invalid data.
	 * @throws AccessDenied: Access to the specified domain object or information is not allowed.
	 * @throws BusinessViolation: The attempted action violates a business rule.
	 * @throws SystemFailure: The method could not be completed due to system level errors (e.g., out of memory, loss of network connectivity)n.
	 * @throws NotImplemented: The method has not been implemented.
	 * @throws ObjectNotFound: The desired domain object could not be found.
	 * @throws DataNotFound
	 */
	public final SmFacade getSmBean(
		BisContext aContext,
		Utility aUtility)
		throws
			InvalidData,
			AccessDenied,
			BusinessViolation,
			SystemFailure,
			NotImplemented,
			ObjectNotFound,
			DataNotFound {
		aUtility.log(LogEventId.DEBUG_LEVEL_2, "SmClient::getSmBean()");
		return StaticContextAccessor.getBean(SmFacade.class);

		/*
		SmFacade smBean = null;

		try {
			smBean = getSmHome(aContext, aUtility).create();
		} catch (RemoteException e) {
			aUtility.log(
				LogEventId.INFO_LEVEL_1,
				"Caught RemoteException on smHome::create(): "
					+ e.getMessage());
			// The home may be stale - get a new home
			try {
				smHome = null;
				smBean = getSmHome(aContext, aUtility).create();
			} catch (RemoteException x) {
				smHome = null;
				aUtility.throwException(
					ExceptionCode.ERR_SMCL_REMOTE_EXCEPTION,
					"RemoteExeption: "
						+ "[ provider url<"
						+ aProviderURL
						+ "> home<"
						+ smHomeName
						+ "> ] "
						+ e.getMessage(),
					origination,
					Severity.UnRecoverable);
			} catch (CreateException x) {
				smHome = null;
				aUtility.throwException(
					ExceptionCode.ERR_SMCL_CREATE_EXCEPTION,
					"CreateException: "
						+ "[ provider url<"
						+ aProviderURL
						+ "> home<"
						+ smHomeName
						+ "> ] "
						+ e.getMessage(),
					origination,
					Severity.UnRecoverable);
			}
		} catch (CreateException e) {
			aUtility.log(
				LogEventId.INFO_LEVEL_1,
				"Caught CreateException on smHome::create(): "
					+ e.getMessage());
			// The home may be stale - get a new home
			try {
				smHome = null;
				smBean = getSmHome(aContext, aUtility).create();

			} catch (RemoteException x) {
				smHome = null;
				aUtility.throwException(
					ExceptionCode.ERR_SMCL_REMOTE_EXCEPTION,
					"RemoteExeption: "
						+ "[ provider url<"
						+ aProviderURL
						+ "> home<"
						+ smHomeName
						+ "> ] "
						+ e.getMessage(),
					origination,
					Severity.UnRecoverable);
			} catch (CreateException x) {
				smHome = null;
				aUtility.throwException(
					ExceptionCode.ERR_SMCL_CREATE_EXCEPTION,
					"CreateException: "
						+ "[ provider url<"
						+ aProviderURL
						+ "> home<"
						+ smHomeName
						+ "> ] "
						+ e.getMessage(),
					origination,
					Severity.UnRecoverable);
			}
		}

		return smBean;*/
	}
	/**
	 * Gets a SM Bean.
	 * Creation date: (4/25/01 9:53:57 AM)
	 * @param aContext
	 * @param aUtility
	 * @param aCode
	 * @param aDescription
	 * @return SmFacade
	 * @throws InvalidData: An input parameter contained invalid data.
	 * @throws AccessDenied: Access to the specified domain object or information is not allowed.
	 * @throws BusinessViolation: The attempted action violates a business rule.
	 * @throws SystemFailure: The method could not be completed due to system level errors (e.g., out of memory, loss of network connectivity)n.
	 * @throws NotImplemented: The method has not been implemented.
	 * @throws ObjectNotFound: The desired domain object could not be found.
	 * @throws DataNotFound: No data found.
	 */
	public final SmFacade getSmConnection(
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
			DataNotFound {

		aUtility.log(LogEventId.DEBUG_LEVEL_2, "SmClient::getSmConnection()");

		SmFacade smBean = null;

		try {
			smBean = getSmBean(aContext, aUtility);
		} catch (NotImplemented e) {
			throwSmException(e.aExceptionData, aUtility, aCode, aDescription);
		} catch (SystemFailure e) {
			throwSmException(e.aExceptionData, aUtility, aCode, aDescription);
		} catch (InvalidData e) {
			throwSmException(e.aExceptionData, aUtility, aCode, aDescription);
		} catch (ObjectNotFound e) {
			throwSmException(e.aExceptionData, aUtility, aCode, aDescription);
		} catch (AccessDenied e) {
			throwSmException(e.aExceptionData, aUtility, aCode, aDescription);
		} catch (BusinessViolation e) {
			throwSmException(e.aExceptionData, aUtility, aCode, aDescription);
		} catch (DataNotFound e) {
			throwSmException(e.aExceptionData, aUtility, aCode, aDescription);
		}
		return smBean;
	}
	/**
	 * Get a SM home.
	 * Creation date: (4/26/01 3:04:35 PM)
	 * @param aContext
	 * @param aUtility
	 * @return SmFacadeHome
 	 * @throws InvalidData: An input parameter contained invalid data.
	 * @throws AccessDenied: Access to the specified domain object or information is not allowed.
	 * @throws BusinessViolation: The attempted action violates a business rule.
	 * @throws SystemFailure: The method could not be completed due to system level errors (e.g., out of memory, loss of network connectivity)n.
	 * @throws NotImplemented: The method has not been implemented.
	 * @throws ObjectNotFound: The desired domain object could not be found.
	 * @throws DataNotFound: No data found.
	 */
	/*private final SmFacadeHome getSmHome(BisContext aContext, Utility aUtility)
		throws
			InvalidData,
			AccessDenied,
			BusinessViolation,
			SystemFailure,
			NotImplemented,
			ObjectNotFound,
			DataNotFound {
		aUtility.log(LogEventId.DEBUG_LEVEL_2, "SmClient::getSmHome()");

		if (smHome == null) {
			InitialContext initialContext = null;
			aUtility.log(
				LogEventId.DEBUG_LEVEL_2,
				"Get SM Bean: host<"
					+ "[ provider url<"
					+ aProviderURL
					+ "> home<"
					+ smHomeName
					+ ">");

			// Look for any properties for the Context factory
			Properties initialContextProperties = null;
			try {
				initialContextProperties =
					PropertiesFileLoader.read(
						initialContextPropertiesFile,
						aUtility);
			} catch (Exception e) {
				initialContextProperties = new Properties();
			}
			InitialContextFactory aContextFactory =
				new InitialContextFactory(
					aProviderURL,
					initialContextProperties);

			try {
				aUtility.log(
					LogEventId.DEBUG_LEVEL_2,
					"SmClient::initialContextProperties: "
						+ initialContextProperties.toString());
				initialContext =
					aContextFactory.getInitialContext(initialContextProperties);
			} catch (InitialContextFactoryException e) {
				aUtility.throwException(
					ExceptionCode.ERR_SMCL_CONTEXT_FACTORY_EXCEPTION,
					"InitialContextFactoryException: "
						+ "[ provider url<"
						+ aProviderURL
						+ "> home<"
						+ smHomeName
						+ "> ] "
						+ e.getMessage(),
					origination,
					Severity.UnRecoverable);
			}

			try {
				Object o = initialContext.lookup(smHomeName);
				smHome =
					(SmFacadeHome) javax.rmi.PortableRemoteObject.narrow(
						o,
						SmFacadeHome.class);
			} catch (NamingException e) {
				aUtility.throwException(
					ExceptionCode.ERR_SMCL_NAMING_EXCEPTION,
					"NamingException: "
						+ "[ provider url<"
						+ aProviderURL
						+ "> home<"
						+ smHomeName
						+ "> ] "
						+ e.getMessage(),
					origination,
					Severity.UnRecoverable);
			}
		}
		return smHome;
	}*/
	/**
	 * Throws Sm Exception.
	 * Creation date: (5/7/01 12:43:31 PM)
	 * @param aExceptionData
	 * @param aUtility
	 * @param aCode
	 * @param aDescription
	 * @throws InvalidData
	 * @throws AccessDenied
	 * @throws BusinessViolation
	 * @throws SystemFailure
	 * @throws NotImplemented
	 * @throws ObjectNotFound
	 * @throws DataNotFound
	 */
	public void throwSmException(
		ExceptionData aExceptionData,
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
			DataNotFound {
		String aOrigination = null;
		Severity aSeverity = null;

		try {
			aOrigination = aExceptionData.aOrigination.theValue();
		} catch (Exception e) {
		}
		try {
			aSeverity = aExceptionData.aSeverity.theValue();
		} catch (Exception e) {
		}

		aUtility.throwException(
			aCode,
			aDescription
				+ ": "
				+ aExceptionData.aCode
				+ "|"
				+ aExceptionData.aDescription,
			aOrigination,
			aSeverity);
	}
}
