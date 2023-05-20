// $Id: LimClient.java,v 1.4 2003/04/01 20:29:24 ts8181 Exp $

package com.sbc.eia.bis.RmNam.utilities.LimClient;

import java.rmi.RemoteException;
import java.util.Properties;

import javax.ejb.CreateException;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.sbc.bccs.utilities.PropertiesFileLoader;
import com.sbc.bccs.utilities.Utility;
import com.sbc.eia.bis.facades.lim.ejb.Lim;
import com.sbc.eia.bis.facades.lim.ejb.LimHome;
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
import com.sbc.eia.idl.types.ExceptionData;
import com.sbc.eia.idl.types.Severity;



/**
 * Connects to Lim Beam.
 * Creation date: (4/26/01 2:57:56 PM)
 * @author: Vickie Chui
 */
public final class LimClient {
	private LimHome limHome = null;
	String aProviderURL = null;
	String limHomeName = null;
	String origination = null;
	String initialContextPropertiesFile = null;
	/**
	 * LimClient constructor comment.
	 */
	private LimClient() {
	}
	/**
	 * LimClient constructor comment.
	 */
	public LimClient(
		String newProviderURL,
		String aLimHomeName,
		String anOrigination,
		String anInitialContextPropertiesFile) {
		aProviderURL = newProviderURL;
		limHomeName = aLimHomeName;
		origination = anOrigination;
		initialContextPropertiesFile = anInitialContextPropertiesFile;
	}
	/**
	 * Establishes Lim Bean connection if it is not cached.
	 * Creation date: (4/26/01 3:04:35 PM)
	 * @param aContext com.sbc.eia.idl.bis_types.BisContext
	 * @param aUtility com.sbc.bccs.utilities.Utility
	 * @return com.sbc.eia.bis.facades.lim.ejb.Lim
	 * @exception com.sbc.eia.idl.bis_types.InvalidData: An input parameter contained invalid data.
	 * @exception com.sbc.eia.idl.bis_types.AccessDenied: Access to the specified domain object or information is not allowed.
	 * @exception com.sbc.eia.idl.bis_types.BusinessViolation: The attempted action violates a business rule.
	 * @exception com.sbc.eia.idl.bis_types.SystemFailure: The method could not be completed due to system level errors (e.g., out of memory, loss of network connectivity)n.
	 * @exception com.sbc.eia.idl.bis_types.NotImplemented: The method has not been implemented.
	 * @exception com.sbc.eia.idl.bis_types.ObjectNotFound: The desired domain object could not be found.
	 * @exception com.sbc.eia.idl.bis_types.DataNotFound: No data found.
	 */
	private final Lim getLimBean(BisContext aContext, Utility aUtility)
		throws
			InvalidData,
			AccessDenied,
			BusinessViolation,
			SystemFailure,
			NotImplemented,
			ObjectNotFound,
			DataNotFound {
		aUtility.log(LogEventId.DEBUG_LEVEL_2, "LimClient::getLimBean()");

		Lim limBean = null;

		try {
			limBean = getLimHome(aContext, aUtility).create();
		} catch (RemoteException e) {
			aUtility.log(
				LogEventId.INFO_LEVEL_1,
				"Caught RemoteException on limHome::create(): "
					+ e.getMessage());
			// The home may be stale - get a new home
			try {
				limHome = null;
				limBean = getLimHome(aContext, aUtility).create();
			} catch (RemoteException x) {
				limHome = null;
				aUtility.throwException(
					ExceptionCode.ERR_LIMCL_REMOTE_EXCEPTION,
					"RemoteExeption: "
						+ "[ provider url<"
						+ aProviderURL
						+ "> home<"
						+ limHomeName
						+ "> ] "
						+ e.getMessage(),
					origination,
					Severity.UnRecoverable);
			} catch (CreateException x) {
				limHome = null;
				aUtility.throwException(
					ExceptionCode.ERR_LIMCL_CREATE_EXCEPTION,
					"CreateException: "
						+ "[ provider url<"
						+ aProviderURL
						+ "> home<"
						+ limHomeName
						+ "> ] "
						+ e.getMessage(),
					origination,
					Severity.UnRecoverable);
			}
		} catch (CreateException e) {
			aUtility.log(
				LogEventId.INFO_LEVEL_1,
				"Caught CreateException on limHome::create(): "
					+ e.getMessage());
			// The home may be stale - get a new home
			try {
				limHome = null;
				limBean = getLimHome(aContext, aUtility).create();

			} catch (RemoteException x) {
				limHome = null;
				aUtility.throwException(
					ExceptionCode.ERR_LIMCL_REMOTE_EXCEPTION,
					"RemoteExeption: "
						+ "[ provider url<"
						+ aProviderURL
						+ "> home<"
						+ limHomeName
						+ "> ] "
						+ e.getMessage(),
					origination,
					Severity.UnRecoverable);
			} catch (CreateException x) {
				limHome = null;
				aUtility.throwException(
					ExceptionCode.ERR_LIMCL_CREATE_EXCEPTION,
					"CreateException: "
						+ "[ provider url<"
						+ aProviderURL
						+ "> home<"
						+ limHomeName
						+ "> ] "
						+ e.getMessage(),
					origination,
					Severity.UnRecoverable);
			}
		}

		return limBean;
	}
	/**
	 * Calls LIM Bean.
	 * Creation date: (4/25/01 9:53:57 AM)
	 * @param aContext com.sbc.eia.idl.bis_types.BisContext
	 * @param aUtility com.sbc.bccs.utilities.Utility
	 * @param aCode java.lang.String
	 * @param aDescription java.lang.String
	 * @return com.sbc.eia.bis.facades.lim.ejb.Lim 
	 * @exception com.sbc.eia.idl.bis_types.InvalidData: An input parameter contained invalid data.
	 * @exception com.sbc.eia.idl.bis_types.AccessDenied: Access to the specified domain object or information is not allowed.
	 * @exception com.sbc.eia.idl.bis_types.BusinessViolation: The attempted action violates a business rule.
	 * @exception com.sbc.eia.idl.bis_types.SystemFailure: The method could not be completed due to system level errors (e.g., out of memory, loss of network connectivity)n.
	 * @exception com.sbc.eia.idl.bis_types.NotImplemented: The method has not been implemented.
	 * @exception com.sbc.eia.idl.bis_types.ObjectNotFound: The desired domain object could not be found.
	 * @exception com.sbc.eia.idl.bis_types.DataNotFound: No data found.
	 */
	public final Lim getLimConnection(
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

		aUtility.log(LogEventId.DEBUG_LEVEL_2, "LimClient::getLimConnection()");

		Lim limBean = null;

		try {
			limBean = getLimBean(aContext, aUtility);
		} catch (NotImplemented e) {
			throwLimException(e.aExceptionData, aUtility, aCode, aDescription);
		} catch (SystemFailure e) {
			throwLimException(e.aExceptionData, aUtility, aCode, aDescription);
		} catch (InvalidData e) {
			throwLimException(e.aExceptionData, aUtility, aCode, aDescription);
		} catch (ObjectNotFound e) {
			throwLimException(e.aExceptionData, aUtility, aCode, aDescription);
		} catch (AccessDenied e) {
			throwLimException(e.aExceptionData, aUtility, aCode, aDescription);
		} catch (BusinessViolation e) {
			throwLimException(e.aExceptionData, aUtility, aCode, aDescription);
		} catch (DataNotFound e) {
			throwLimException(e.aExceptionData, aUtility, aCode, aDescription);
		}
		return limBean;
	}
	/**
	 * Get a LIM home.
	 * Creation date: (4/26/01 3:04:35 PM)
	 * @param aContext com.sbc.eia.idl.bis_types.BisContext
	 * @param aUtility com.sbc.bccs.utilities.Utility
	 * @return com.sbc.eia.bis.facades.nam.ejb.Lim
	 * @exception com.sbc.eia.idl.bis_types.InvalidData: An input parameter contained invalid data.
	 * @exception com.sbc.eia.idl.bis_types.AccessDenied: Access to the specified domain object or information is not allowed.
	 * @exception com.sbc.eia.idl.bis_types.BusinessViolation: The attempted action violates a business rule.
	 * @exception com.sbc.eia.idl.bis_types.SystemFailure: The method could not be completed due to system level errors (e.g., out of memory, loss of network connectivity)n.
	 * @exception com.sbc.eia.idl.bis_types.NotImplemented: The method has not been implemented.
	 * @exception com.sbc.eia.idl.bis_types.ObjectNotFound: The desired domain object could not be found.
	 * @exception com.sbc.eia.idl.bis_types.DataNotFound: No data found.
	 */
	private final LimHome getLimHome(BisContext aContext, Utility aUtility)
		throws
			InvalidData,
			AccessDenied,
			BusinessViolation,
			SystemFailure,
			NotImplemented,
			ObjectNotFound,
			DataNotFound {
		aUtility.log(LogEventId.DEBUG_LEVEL_2, "LimClient::getLimHome()");

		if (limHome == null) {
			InitialContext initialContext = null;
			aUtility.log(
				LogEventId.DEBUG_LEVEL_2,
				"Get LIM Bean: "
					+ "[ provider url<"
					+ aProviderURL
					+ "> home<"
					+ limHomeName
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
				new InitialContextFactory(aProviderURL, initialContextProperties);

			try {
				aUtility.log(
					LogEventId.DEBUG_LEVEL_2,
					"LimClient::initialContextProperties: "
						+ initialContextProperties.toString());
				initialContext =
					aContextFactory.getInitialContext(initialContextProperties);
			} catch (InitialContextFactoryException e) {
				aUtility.throwException(
					ExceptionCode.ERR_LIMCL_INITIAL_CONTEXT_FACTORY_EXCEPTION,
					"InitialContextFactoryException: "
						+ "[ provider url<"
						+ aProviderURL
						+ "> home<"
						+ limHomeName
						+ "> ] "
						+ e.getMessage(),
					origination,
					Severity.UnRecoverable);
			}

			try {
				Object o = initialContext.lookup(limHomeName);
				limHome =
					(LimHome) javax.rmi.PortableRemoteObject.narrow(
						o,
						LimHome.class);
			} catch (NamingException e) {
				aUtility.throwException(
					ExceptionCode.ERR_LIMCL_NAMING_EXCEPTION,
					"NamingException: "
						+ "[ provider url<"
						+ aProviderURL
						+ "> home<"
						+ limHomeName
						+ "> ] "
						+ e.getMessage(),
					origination,
					Severity.UnRecoverable);
			}
		}
		return limHome;
	}
	/**
	 * Throws Lim Exception.
	 * Creation date: (5/7/01 12:43:31 PM)
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
	public void throwLimException(
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
			DataNotFound {
		String aOrigination = null;
		Severity aSeverity = null;

		try {
			aOrigination = anExceptionData.aOrigination.theValue();
		} catch (Exception e) {
		}
		try {
			aSeverity = anExceptionData.aSeverity.theValue();
		} catch (Exception e) {
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
