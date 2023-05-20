//$Id: LimBean.java,v 1.62.10.1 2012/01/04 00:36:29 me227h Exp $

package com.sbc.eia.bis.facades.lim.ejb;

import com.sbc.bccs.idl.helpers.ObjectPropertyManager;
import com.sbc.eia.idl.bis_types.BisContextProperty;
import com.sbc.eia.idl.types.ObjectProperty;
import java.io.*;
import java.rmi.RemoteException;
import java.util.Hashtable;
import java.util.Properties;

import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import javax.naming.Context;
import javax.naming.InitialContext;

import com.sbc.bccs.idl.helpers.IDLUtil;
import com.sbc.bccs.utilities.PropertiesFileLoader;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilderResult;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.bis.facades.lim.ejb.tests.LimSelfTest;
import com.sbc.eia.bis.facades.lim.ejb.tests.Ping_Validate;
import com.sbc.eia.bis.lim.transactions.Cingular.RetrieveServiceAreaByPostalCodeTrans;
import com.sbc.eia.bis.lim.transactions.FieldAddress.FieldAddressTrans;
import com.sbc.eia.bis.lim.transactions.RetrieveLocation.RetrieveLocationForAddressTrans;
import com.sbc.eia.bis.lim.transactions.RetrieveLocation.RetrieveLocationForServiceTrans;
import com.sbc.eia.bis.lim.transactions.RetrieveLocationForPostalAddress.RetrieveLocationForPostalAddressTrans;
import com.sbc.eia.bis.lim.transactions.RetrieveLocationForServiceAddress.RetrieveLocationForServiceAddressTrans;
import com.sbc.eia.bis.lim.transactions.RetrieveLocationForTelephoneNumber.RetrieveLocationForTelephoneNumberTrans;
import com.sbc.eia.bis.lim.transactions.UpdateBillingAddress.UpdateBillingAddressTrans;
import com.sbc.eia.bis.lim.util.LIMBase;
import com.sbc.eia.idl.bis_types.AccessDenied;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.bis_types.BusinessViolation;
import com.sbc.eia.idl.bis_types.DataNotFound;
import com.sbc.eia.idl.bis_types.InvalidData;
import com.sbc.eia.idl.bis_types.MultipleExceptions;
import com.sbc.eia.idl.bis_types.NotImplemented;
import com.sbc.eia.idl.bis_types.ObjectNotFound;
import com.sbc.eia.idl.bis_types.SystemFailure;
import com.sbc.eia.idl.bis_types.TelephoneNumber;
import com.sbc.eia.idl.exception_types.ExceptionCode;
import com.sbc.eia.idl.lim.FieldAddressReturn;
import com.sbc.eia.idl.lim.LimFacadeOperations;
import com.sbc.eia.idl.lim.PingReturn;
import com.sbc.eia.idl.lim.RetrieveLocationForAddressReturn;
import com.sbc.eia.idl.lim.RetrieveLocationForE911AddressReturn;
import com.sbc.eia.idl.lim.RetrieveLocationForGeoAddressReturn;
import com.sbc.eia.idl.lim.RetrieveLocationForPostalAddress2Return;
import com.sbc.eia.idl.lim.RetrieveLocationForPostalAddressReturn;
import com.sbc.eia.idl.lim.RetrieveLocationForServiceAddressReturn;
import com.sbc.eia.idl.lim.RetrieveLocationForServiceReturn;
import com.sbc.eia.idl.lim.RetrieveLocationForTelephoneNumberReturn;
import com.sbc.eia.idl.lim.RetrieveServiceAreaByPostalCodeReturn;
import com.sbc.eia.idl.lim.SelfTestReturn;
import com.sbc.eia.idl.lim.UpdateBillingAddressReturn;
import com.sbc.eia.idl.lim_types.Address;
import com.sbc.eia.idl.lim_types.AddressOpt;
import com.sbc.eia.idl.lim_types.LocationPropertiesToGetSeqOpt;
import com.sbc.eia.idl.lim_types.ProviderLocationPropertiesSeqOpt;
import com.sbc.eia.idl.lim_types.UnfieldedAddress;
import com.sbc.eia.idl.types.CompositeObjectKey;
import com.sbc.eia.idl.types.ExceptionData;
import com.sbc.eia.idl.types.LongOpt;
import com.sbc.eia.idl.types.Severity;
import com.sbc.eia.idl.types.SeverityOpt;
import com.sbc.eia.idl.types.StringOpt;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


/**
 * This is the LIM Session Bean.  It provides LIM services to
 * clients via the LIM facade.
 */
public class LimBean extends LIMBase implements SessionBean, LimFacadeOperations
{
	private javax.ejb.SessionContext mySessionCtx = null;
	final static long serialVersionUID = 3206093459760846163L;

	private RetrieveLocationForServiceAddressTrans cacheRetrieveLocationForServiceAddress = null ;
	private RetrieveLocationForTelephoneNumberTrans cacheRetrieveLocationForTelephoneNumber=null;
	private RetrieveLocationForAddressTrans cacheRetrieveLocationForAddress = null ;
	private RetrieveLocationForServiceTrans cacheRetrieveLocationForService = null ;
	private RetrieveServiceAreaByPostalCodeTrans cacheRetrieveServiceAreaByPostalCode = null ;
	private RetrieveLocationForPostalAddressTrans cacheRetrieveLocationForPostalAddress = null;
	private UpdateBillingAddressTrans cacheUpdateBillingAddress = null;
	private FieldAddressTrans cacheFieldAddress = null ;
	private static String zipToSagaFile = null;

	//
	// The location of the LIM environment variable names for property file.
	//
	protected final static String PROP_FILE_LOCATION = "PROP_FILE_LOCATION";

	private ExceptionBuilderResult exBldReslt;

	public LimBean()
	{
		super ();
	}

	/**
	 * ejbActivate method.
	 * @exception java.rmi.RemoteException
	 */
	public void ejbActivate() throws java.rmi.RemoteException {}
	/**
	 * ejbCreate method.
	 * @exception javax.ejb.CreateException
	 * @exception java.rmi.RemoteException
	 */
	public void ejbCreate() throws javax.ejb.CreateException {}
	/**
	 * ejbPassivate method.
	 * @exception java.rmi.RemoteException
	 */
	public void ejbPassivate() throws java.rmi.RemoteException {}
	/**
	 * ejbRemove method.
	 * @exception java.rmi.RemoteException
	 */
	public void ejbRemove() throws java.rmi.RemoteException {}
	/**
	 * fieldAddress method is used to convert an unfielded address into a fielded address.
	 * Creation date: (9/20/01 9:49:30 AM)
	 * @return FieldAddressReturn
	 * @param aContext BisContext
	 * @param aUnfieldedAddress UnfieldedAddress
	 * @exception InvalidData The data supplied by the client is not valid for performing the method.
	 * @exception AccessDenied The client does not have appropriate authority to invoke this method
	 * @exception BusinessViolation The BIS is unable to perform the operation because the operation violates a business process.
	 * @exception SystemFailure The BIS is unable to perform the operation due to an error with a subsystem or or supporting system.
	 * @exception NotImplemented The requested method has not been implemented.
	 * @exception DataNotFound No data found in SBC OSS.
	 * @exception ObjectNotFound For a method that requires an objectId as an input parameter. The objectId does not exist.
	 */
	public FieldAddressReturn fieldAddress (
			BisContext aContext,
			UnfieldedAddress aUnfieldedAddress)
			throws BusinessViolation,
			InvalidData,
			SystemFailure,
			ObjectNotFound,
			NotImplemented,
			DataNotFound,
			AccessDenied
	{
		FieldAddressReturn retVal = null;

		try {
			try {
				loadProperty( aContext );
			}
			catch( SystemFailure e ) {
				throw e;
			}

			/**
			 * Starts new trans logging
			 */
			validateBisContext (aContext, getEnv( "LIM_BIS_NAME" ),
					ExceptionCode.ERR_LIM_MISSING_BISCONTEXT, "CallerContext can not be null");

			log( LogEventId.ENTRY, "LIM Facade - fieldAddress" ) ;

			log(LogEventId.AUDIT_TRAIL, "LimBean::fieldAddress()|FieldAddressTrans::execute()|PRE");

			try {
				if ( cacheFieldAddress == null )
					cacheFieldAddress = (FieldAddressTrans)new FieldAddressTrans( this ) ;

				/* Convert the unfielded address into a fielded address. */
				retVal = cacheFieldAddress.execute( aUnfieldedAddress );
			}
			catch( AccessDenied e ) 		{throw e;}
			catch( BusinessViolation e ) 	{throw e;}
			catch( DataNotFound e ) 		{throw e;}
			catch( InvalidData e ) 		{throw e;}
			catch( NotImplemented e ) 		{throw e;}
			catch( ObjectNotFound e ) 		{throw e;}
			catch( SystemFailure e ) 		{throw e;}

			/**
			 * Unexpected exception caught, need to follow up on the stack trace.
			 */
			catch ( Throwable e )
			{
				throwException( ExceptionCode.ERR_LIM_UNEXPECTED_ERROR,
						"LIM caught an uncaught exception: " + e.toString(),
						"LIM", Severity.UnRecoverable, (Exception)e ) ;
			}
		}
		finally {
			log(LogEventId.AUDIT_TRAIL, "LimBean::fieldAddress()|FieldAddressTrans::execute()|POST");
			log( LogEventId.EXIT, "LIM Facade - fieldAddress" ) ;
		}

		return retVal;
	}

	/**
	 * getSessionContext method.
	 * @return javax.ejb.SessionContext
	 * @see #setSessionContext(SessionContext)
	 */
	public javax.ejb.SessionContext getSessionContext() {
		return mySessionCtx;
	}

	/**
	 * loadProperty method is used to load LIM related properties from the LIM config file.
	 * Creation date: (4/10/01 9:03:16 AM)
	 * @param aContext BisContext
	 * @exception SystemFailure The BIS is unable to perform the operation due to an error with a subsystem or or supporting system.
	 */
//    public void loadProperty( BisContext aContext )
//    throws SystemFailure
//    {
//    	try
//    	{
//    		/**
//    		 * Load LIM related properties from a file.
//    		 */
//    		if( PROPERTIES == null )
//    		{
//                PROPERTIES = new java.util.Properties();
//    			Context cxt = new InitialContext();
//    			String loc = (String)cxt.lookup("java:comp/env/" + PROP_FILE_LOCATION);
//    			PROPERTIES = PropertiesFileLoader.read(loc, null);
//    			setPROPERTIES (PROPERTIES);
//    			getRulesFiles();
//    			decodePasswords();
//    			setCatalystEnvironment();
//    			initLIMBase ();
//    			setCallerContext( aContext );
//    			log( LogEventId.DEBUG_LEVEL_2, "LIM related properties loaded...") ;
//    		}
//    		else
//    			setCallerContext( aContext );
//    	}
//    	catch( NullPointerException e )
//    	{
//    		e.printStackTrace();
//    		System.out.println("Load Properties NullPointerException <" + e + ">" );
//    		ExceptionData excData = new ExceptionData(
//    			ExceptionCode.ERR_LIM_PROPERTIES_FILE_NOT_FOUND,
//    			"Required LIM properties not found during server startup.",
//    			IDLUtil.toOpt("LIM"),
//    			(SeverityOpt) IDLUtil.toOpt(SeverityOpt.class,Severity.UnRecoverable));
//
//    		System.out.println("Code=[" + excData.aCode + "] Description=" + excData.aDescription );
//    		throw new SystemFailure( aContext, excData );
//    	}
//    	catch( RemoteException e )
//    	{
//    		e.printStackTrace();
//    		System.out.println("Load Properties Exception <" + e.getMessage() + ">" );
//    		ExceptionData excData = new ExceptionData(
//    			ExceptionCode.ERR_LIM_PROPERTIES_FILE_NOT_FOUND,
//    			e.getMessage(),
//    			IDLUtil.toOpt("LIM"),
//    			(SeverityOpt) IDLUtil.toOpt(SeverityOpt.class,Severity.UnRecoverable));
//
//    		System.out.println("Code=[" + excData.aCode + "] Description=" + excData.aDescription );
//    		throw new SystemFailure( aContext, excData );
//    	}
//    	catch( Exception e )
//    	{
//    		e.printStackTrace();
//    		System.out.println("Load Properties Exception <" + e.getMessage() + ">" );
//    		ExceptionData excData = new ExceptionData(
//    			ExceptionCode.ERR_LIM_PROPERTIES_FILE_NOT_FOUND,
//    			"LIM properties file not found during server startup.",
//    			IDLUtil.toOpt("LIM"),
//    			(SeverityOpt) IDLUtil.toOpt(SeverityOpt.class,Severity.UnRecoverable));
//
//    		System.out.println("Code=[" + excData.aCode + "] Description=" + excData.aDescription );
//    		throw new SystemFailure( aContext, excData );
//    	}
//    }

	/**
	 * loadProperty method is used to load LIM related properties from the LIM config file
	 * and validate and publish BisContext.
	 * Creation date: (7/22/07 9:03:16 AM)
	 * @param aContext BisContext
	 * @exception InvalidData The data supplied by the client is not valid for performing the method.
	 * @exception AccessDenied The client does not have appropriate authority to invoke this method
	 * @exception BusinessViolation The BIS is unable to perform the operation because the operation violates a business process.
	 * @exception SystemFailure The BIS is unable to perform the operation due to an error with a subsystem or or supporting system.
	 * @exception NotImplemented The requested method has not been implemented.
	 * @exception DataNotFound No data found in SBC OSS.
	 * @exception ObjectNotFound For a method that requires an objectId as an input parameter. The objectId does not exist.
	 */
	public void loadProperty( BisContext aContext )
			throws
			SystemFailure,
			InvalidData,
			AccessDenied,
			BusinessViolation,
			NotImplemented,
			ObjectNotFound,
			DataNotFound
	{
		try
		{
			/**
			 * Load LIM related properties from a file.
			 */
			if( PROPERTIES == null )
			{
				PROPERTIES = new java.util.Properties();
				Context cxt = new InitialContext();
				String loc = (String)cxt.lookup("java:comp/env/" + PROP_FILE_LOCATION);
				PROPERTIES = PropertiesFileLoader.read(loc, null);
				setPROPERTIES (PROPERTIES);
				getRulesFiles();
				decodePasswords();
				setCatalystEnvironment();
				initLIMBase ();
				validateBisContext (aContext, getEnv( "LIM_BIS_NAME" ),
						ExceptionCode.ERR_LIM_MISSING_BISCONTEXT, "CallerContext can not be null");
				log( LogEventId.DEBUG_LEVEL_2, "LIM related properties loaded...") ;
			}
			else
				validateBisContext (aContext, getEnv( "LIM_BIS_NAME" ),
						ExceptionCode.ERR_LIM_MISSING_BISCONTEXT, "CallerContext can not be null");

		}
		catch( AccessDenied e ) 		{throw e;}
		catch( BusinessViolation e ) 	{throw e;}
		catch( DataNotFound e ) 		{throw e;}
		catch( InvalidData e ) 			{throw e;}
		catch( NotImplemented e ) 		{throw e;}
		catch( ObjectNotFound e ) 		{throw e;}
		catch( SystemFailure e ) 		{throw e;}
		catch( NullPointerException e )
		{
			e.printStackTrace();
			System.out.println("Load Properties NullPointerException <" + e + ">" );
			ExceptionData excData = new ExceptionData(
					ExceptionCode.ERR_LIM_PROPERTIES_FILE_NOT_FOUND,
					"Required LIM properties not found during server startup.",
					IDLUtil.toOpt("LIM"),
					(SeverityOpt) IDLUtil.toOpt(SeverityOpt.class,Severity.UnRecoverable));

			System.out.println("Code=[" + excData.aCode + "] Description=" + excData.aDescription );
			throw new SystemFailure( aContext, excData );
		}
		catch( RemoteException e )
		{
			e.printStackTrace();
			System.out.println("Load Properties Exception <" + e.getMessage() + ">" );
			ExceptionData excData = new ExceptionData(
					ExceptionCode.ERR_LIM_PROPERTIES_FILE_NOT_FOUND,
					e.getMessage(),
					IDLUtil.toOpt("LIM"),
					(SeverityOpt) IDLUtil.toOpt(SeverityOpt.class,Severity.UnRecoverable));

			System.out.println("Code=[" + excData.aCode + "] Description=" + excData.aDescription );
			throw new SystemFailure( aContext, excData );
		}
		catch( Exception e )
		{
			e.printStackTrace();
			System.out.println("Load Properties Exception <" + e.getMessage() + ">" );
			ExceptionData excData = new ExceptionData(
					ExceptionCode.ERR_LIM_PROPERTIES_FILE_NOT_FOUND,
					"LIM properties file not found during server startup.",
					IDLUtil.toOpt("LIM"),
					(SeverityOpt) IDLUtil.toOpt(SeverityOpt.class,Severity.UnRecoverable));

			System.out.println("Code=[" + excData.aCode + "] Description=" + excData.aDescription );
			throw new SystemFailure( aContext, excData );
		}
	}

	/**
	 * Initializes the zip to SAGA map from a properties file.
	 * @exception SystemFailure
	 * @author as2362
	 */
	public void loadZipToSaga() throws SystemFailure
	{
		// check if file is already loaded, load only once
		if(zipToSaga == null)
		{
			// The file contains data fo the form: ZipCode=SAGAs (eg: 94002=BAY,LAN,SA)
			zipToSagaFile = (String)PROPERTIES.get("ZIP_TO_SAGA_MAP_FILE");
			if (zipToSagaFile == null || zipToSagaFile.length() < 1)
			{
				ExceptionData excData = new ExceptionData(
						ExceptionCode.ERR_LIM_INTERNAL,
						"ZIP_TO_SAGA_MAP_FILE property not found/not set",
						IDLUtil.toOpt("LIM"),
						(SeverityOpt) IDLUtil.toOpt(SeverityOpt.class,Severity.UnRecoverable));
				throw new SystemFailure(this.callerContext, excData);
			}
			InputStream inFileInputStream = null;
			try
			{
				inFileInputStream = this.getClass().getClassLoader().getResourceAsStream(zipToSagaFile);
				//inFileInputStream = new FileInputStream(inFile);
				zipToSaga = new Properties();
				zipToSaga.load(inFileInputStream);
				log(LogEventId.DEBUG_LEVEL_2, ("Loaded " + zipToSaga.size() + " zip to saga mappings"));
			}
			catch (FileNotFoundException e)
			{
				ExceptionData excData = new ExceptionData(
						ExceptionCode.ERR_LIM_INTERNAL,
						"FileNotFoundException: " + e.getMessage(),
						IDLUtil.toOpt("LIM"),
						(SeverityOpt) IDLUtil.toOpt(SeverityOpt.class,Severity.UnRecoverable));
				throw new SystemFailure(this.callerContext,excData);
			}
			catch (IOException e)
			{
				ExceptionData excData = new ExceptionData(
						ExceptionCode.ERR_LIM_INTERNAL,
						"IOException: " + e.getMessage(),
						IDLUtil.toOpt("LIM"),
						(SeverityOpt) IDLUtil.toOpt(SeverityOpt.class,Severity.UnRecoverable));
				throw new SystemFailure(this.callerContext, excData);
			}
			finally
			{
				if (inFileInputStream != null)
				{
					try {
						inFileInputStream.close();
					}
					catch (IOException e)
					{
						log(LogEventId.EXCEPTION, "IOException on bufIn.close(): " + e.getMessage());
					}
				}
			}
		}
	}

	/**
	 * retrieveLocationForAddress method is used to retrieve an address that exists in SBC's OSS.
	 * When a valid address is selected, this operation will return location information
	 * associated with that address ( e.g.local serving office, primary NPANXX, TAR code, etc...)
	 * Creation date: (3/16/01 4:21:37 PM)
	 * @return RetrieveLocationForAddressReturn
	 * @param aContext BisContext
	 * @param aAddress Address
	 * @param aMaximumBasicAddressReturnLimit LongOpt
	 * @param aMaximumUnitReturnLimit LongOpt
	 * @exception InvalidData The data supplied by the client is not valid for performing the method.
	 * @exception AccessDenied The client does not have appropriate authority to invoke this method
	 * @exception BusinessViolation The BIS is unable to perform the operation because the operation violates a business process.
	 * @exception SystemFailure The BIS is unable to perform the operation due to an error with a subsystem or or supporting system.
	 * @exception NotImplemented The requested method has not been implemented.
	 * @exception DataNotFound No data found in SBC OSS.
	 * @exception ObjectNotFound For a method that requires an objectId as an input parameter. The objectId does not exist.
	 */
	public RetrieveLocationForAddressReturn retrieveLocationForAddress (
			BisContext aContext,
			Address aAddress,
			ProviderLocationPropertiesSeqOpt aPropertiesSeqOpt,
			LongOpt aMaximumBasicAddressReturnLimit,
			LongOpt aMaximumUnitReturnLimit,
			StringOpt aExchangeCode)
			throws BusinessViolation,
			InvalidData,
			SystemFailure,
			ObjectNotFound,
			NotImplemented,
			DataNotFound,
			AccessDenied
	{
		RetrieveLocationForAddressReturn retVal = null;

		try {
			try {
				loadProperty( aContext );
				loadZipToSaga();
			}
			catch( SystemFailure e ) {
				throw e;
			}

			/**
			 * Starts new trans logging
			 */
			validateBisContext (aContext, getEnv( "LIM_BIS_NAME" ),
					ExceptionCode.ERR_LIM_MISSING_BISCONTEXT, "CallerContext can not be null");

			log( LogEventId.ENTRY, "LIM Facade - retrieveLocationForAddress" ) ;

			log(LogEventId.AUDIT_TRAIL, "LimBean::retrieveLocationForAddress()|RetrieveLocationForAddressTrans::execute()|PRE");

			try {
				if ( cacheRetrieveLocationForAddress == null )
					cacheRetrieveLocationForAddress = (RetrieveLocationForAddressTrans)new RetrieveLocationForAddressTrans((LIMBase) this ) ;

				//Making sure ProviderLocationPropertiesSeqOpt is not null;
				if (aPropertiesSeqOpt == null) {
					aPropertiesSeqOpt = new ProviderLocationPropertiesSeqOpt();
					aPropertiesSeqOpt.__default();
				}

//				return ret;
				/* Retrieve the location information for the address */
				retVal = cacheRetrieveLocationForAddress.execute(
						aAddress,
						aPropertiesSeqOpt,
						aMaximumBasicAddressReturnLimit,
						aMaximumUnitReturnLimit,
						aExchangeCode);
			}
			catch( AccessDenied e ) 		{throw e;}
			catch( BusinessViolation e ) 	{throw e;}
			catch( DataNotFound e ) 		{throw e;}
			catch( InvalidData e ) 		{throw e;}
			catch( NotImplemented e ) 		{throw e;}
			catch( ObjectNotFound e ) 		{throw e;}
			catch( SystemFailure e ) 		{throw e;}

			/**
			 * Unexpected exception caught, need to follow up on the stack trace.
			 */
			catch ( Throwable e )
			{
				throwException( ExceptionCode.ERR_LIM_UNEXPECTED_ERROR,
						"LIM caught an uncaught exception: " + e.toString(),
						"LIM", Severity.UnRecoverable, (Exception)e ) ;
			}
		}
		finally {
			log(LogEventId.AUDIT_TRAIL, "LimBean::retrieveLocationForAddress()|RetrieveLocationForAddressTrans::execute()|POST");
			log( LogEventId.EXIT, "LIM Facade - retrieveLocationForAddress" ) ;
		}

        ObjectPropertyManager opm = new ObjectPropertyManager(retVal.getAContext().getAProperties());

        opm.add("APP_NAME", "LIM_BIS");

        retVal.getAContext().setAProperties(opm.toArray());

		return retVal;
	}

	/**
	 * retrieveLocationForService method is used to retrieve an address that exists in SBC's OSS
	 * based on a TN. When a valid address is selected, this operation will return location information
	 * associated with that address ( e.g.local serving office, primary NPANXX, TAR code, etc...)
	 * Creation date: (3/26/01 3:15:04 PM)
	 * @return RetrieveLocationForServiceReturn
	 * @param aContext BisContext
	 * @param aServiceHandle ObjectHandle
	 * @param aMaximumBasicAddressReturnLimit LongOpt
	 * @param aMaximumUnitReturnLimit LongOpt
	 * @exception InvalidData The data supplied by the client is not valid for performing the method.
	 * @exception AccessDenied The client does not have appropriate authority to invoke this method
	 * @exception BusinessViolation The BIS is unable to perform the operation because the operation violates a business process.
	 * @exception SystemFailure The BIS is unable to perform the operation due to an error with a subsystem or or supporting system.
	 * @exception NotImplemented The requested method has not been implemented.
	 * @exception DataNotFound No data found in SBC OSS.
	 * @exception ObjectNotFound For a method that requires an objectId as an input parameter. The objectId does not exist.
	 */
	public RetrieveLocationForServiceReturn retrieveLocationForService (
			BisContext aContext,
			TelephoneNumber aTelephoneNumber,
			ProviderLocationPropertiesSeqOpt aPropertiesSeqOpt,
			LongOpt aMaximumBasicAddressReturnLimit,
			LongOpt aMaximumUnitReturnLimit)
			throws InvalidData,
			AccessDenied,
			BusinessViolation,
			SystemFailure,
			NotImplemented,
			DataNotFound,
			ObjectNotFound
	{
		RetrieveLocationForServiceReturn retVal = null;

		try {
			try {
				loadProperty( aContext );
				loadZipToSaga();

			}
			catch( SystemFailure e ) {
				throw e;
			}

			/**
			 * Starts new trans logging
			 */
			validateBisContext (aContext, getEnv( "LIM_BIS_NAME" ),
					ExceptionCode.ERR_LIM_MISSING_BISCONTEXT, "CallerContext can not be null");

			log( LogEventId.ENTRY, "LIM Facade - retrieveLocationForService" ) ;

			log(LogEventId.AUDIT_TRAIL, "LimBean::retrieveLocationForService()|RetrieveLocationForServiceTrans::execute()|PRE");

			try {
				if ( cacheRetrieveLocationForService == null )
					cacheRetrieveLocationForService = (RetrieveLocationForServiceTrans)new RetrieveLocationForServiceTrans( this ) ;

				//Making sure ProviderLocationPropertiesSeqOpt is not null;
				if (aPropertiesSeqOpt == null) {
					aPropertiesSeqOpt = new ProviderLocationPropertiesSeqOpt();
					aPropertiesSeqOpt.__default();
				}

/*				OvalsWrapper wrapper = new OvalsWrapper(this, null, null, new OvalsPla(this), null);
				retVal = wrapper.retrieveLocationForService(
						aContext,
						aTelephoneNumber,
						aPropertiesSeqOpt,
						aMaximumBasicAddressReturnLimit,
						aMaximumUnitReturnLimit);*/

				/* Retrieve the location information for the service */
				retVal = cacheRetrieveLocationForService.execute(
						aTelephoneNumber,
						aPropertiesSeqOpt,
						aMaximumBasicAddressReturnLimit,
						aMaximumUnitReturnLimit );
			}
			catch( AccessDenied e ) 		{throw e;}
			catch( BusinessViolation e ) 	{throw e;}
			catch( DataNotFound e ) 		{throw e;}
			catch( InvalidData e ) 		{throw e;}
			catch( NotImplemented e ) 		{throw e;}
			catch( ObjectNotFound e ) 		{throw e;}
			catch( SystemFailure e ) 		{throw e;}

			/**
			 * Unexpected exception caught, need to follow up on the stack trace.
			 */
			catch ( Throwable e )
			{
				throwException( ExceptionCode.ERR_LIM_UNEXPECTED_ERROR,
						"LIM caught an uncaught exception: " + e.toString(),
						"LIM", Severity.UnRecoverable, (Exception)e ) ;
			}
		}
		finally {
			log(LogEventId.AUDIT_TRAIL, "LimBean::retrieveLocationForService()|RetrieveLocationForServiceTrans::execute()|POST");
			log( LogEventId.EXIT, "LIM Facade - retrieveLocationForService" ) ;
		}

		return retVal;
	}

	//Not Implemented due to AtlasRC Retirement.
	/**
	 * retrieveServiceAreaByPostalCode.
	 * Creation date: (10/20/04 9:49:30 AM)
	 * This operation is used to retrieve a service area based on Postal Code that's valid.
	 * When a valid postal code is selected, this operation will return service area information
	 * associated with that postal code ( e.g. cities, communities)
	 * @return RetrieveServiceAreaByPostalCodeReturn
	 * @param aContext The client's calling context.
	 * @param aCingularSalesChannel Valid values are Lightspeed, SWOT, WOW, iWOW
	 * @param aPostalCode The Postal Code to be validated
	 * @param aRequestServiceAreaIndicator If true, CSI API InquireServiceAreaByZip will be initiated against Atlas RC
	 * @param aRequestMarketInformationIndicator If true, CSI API InquireMarketByZip will be initiated against Atlas RC
	 * @exception InvalidData The data supplied by the client is not valid for performing the method.
	 * @exception AccessDenied The client does not have appropriate authority to invoke this method
	 * @exception BusinessViolation The BIS is unable to perform the operation because the operation violates a business process.
	 * @exception SystemFailure The BIS is unable to perform the operation due to an error with a subsystem or or supporting system.
	 * @exception NotImplemented The requested method has not been implemented.
	 * @exception DataNotFound No data found in SBC OSS.
	 * @exception ObjectNotFound For a method that requires an objectId as an input parameter. The objectId does not exist.
	 * @exception MultipleExceptions A list of exception codes are returned from OSS.
	 */
//    public RetrieveServiceAreaByPostalCodeReturn retrieveServiceAreaByPostalCode (
//        	BisContext aContext, 
//            String aCingularSalesChannel,
//            String aPostalCode,
//            boolean aRequestServiceAreaIndicator,
//            boolean aRequestMarketInformationIndicator)
//        throws BusinessViolation, 
//        	ObjectNotFound, 
//        	InvalidData, 
//        	AccessDenied, 
//        	SystemFailure, 
//        	DataNotFound, 
//        	MultipleExceptions, 
//        	NotImplemented
//        {
//        	RetrieveServiceAreaByPostalCodeReturn retVal = null;
//        
//        	try { 
//        		try {
//        			loadProperty( aContext );
//        		}
//        		catch( SystemFailure e ) {
//        			throw e;
//        		}
//        
//        		/**
//        		 * Starts new trans logging
//        		 */
//        		validateBisContext (aContext, getEnv( "LIM_BIS_NAME" ), 
//        			ExceptionCode.ERR_LIM_MISSING_BISCONTEXT, "CallerContext can not be null");
//       
//        		log( LogEventId.ENTRY, "LIM Facade - retrieveServiceAreaByPostalCode" ) ;
//        
//        		log(LogEventId.AUDIT_TRAIL, "LimBean::retrieveServiceAreaByPostalCode()|RetrieveServiceAreaByPostalCodeTrans::execute()|PRE");
//        
//        		try {
//        			if ( cacheRetrieveServiceAreaByPostalCode == null )
//        				cacheRetrieveServiceAreaByPostalCode = (RetrieveServiceAreaByPostalCodeTrans)new RetrieveServiceAreaByPostalCodeTrans( (LIMBase)this ) ;
//
//        			retVal = cacheRetrieveServiceAreaByPostalCode.execute(
//                        aCingularSalesChannel,
//                        aPostalCode,
//                        aRequestServiceAreaIndicator,
//                        aRequestMarketInformationIndicator);
//        		}
//        		catch( AccessDenied e ) 		{throw e;}
//        		catch( BusinessViolation e ) 	{throw e;}
//        		catch( DataNotFound e ) 		{throw e;}
//        		catch( InvalidData e ) 		{throw e;}
//        		catch( NotImplemented e ) 		{throw e;}
//        		catch( ObjectNotFound e ) 		{throw e;}
//        		catch( SystemFailure e ) 		{throw e;}
//        		catch( MultipleExceptions e ) 	{throw e;}
//        		
//        		/**
//        		 * Unexpected exception caught, need to follow up on the stack trace.
//        		 */
//        		catch ( Throwable e )
//        		{
//        			throwException( ExceptionCode.ERR_LIM_UNEXPECTED_ERROR,
//        								  "LIM caught an uncaught exception: " + e.toString(),
//        					    		  "LIM", Severity.UnRecoverable, (Exception)e ) ;
//        		}
//        	}
//        	finally {
//        		log(LogEventId.AUDIT_TRAIL, "LimBean::retrieveServiceAreaByPostalCode()|RetrieveServiceAreaByPostalCodeTrans::execute()|POST");
//        		log( LogEventId.EXIT, "LIM Facade - retrieveServiceAreaByPostalCode" ) ;
//        	}
//        	
//        	return retVal;
//        }

	/**
	 * retrieveServiceAreaByPostalCode.
	 * This operation is used to retrieve a service area based on Postal Code that's valid.
	 * When a valid postal code is selected, this operation will return service area information
	 * associated with that postal code ( e.g. cities, communities)
	 * @return RetrieveServiceAreaByPostalCodeReturn
	 * @param aContext The client's calling context.
	 * @param aCingularSalesChannel Valid values are Lightspeed, SWOT, WOW, iWOW
	 * @param aPostalCode The Postal Code to be validated
	 * @param aRequestServiceAreaIndicator If true, CSI API InquireServiceAreaByZip will be initiated against Atlas RC
	 * @param aRequestMarketInformationIndicator If true, CSI API InquireMarketByZip will be initiated against Atlas RC
	 * @exception InvalidData The data supplied by the client is not valid for performing the method.
	 * @exception AccessDenied The client does not have appropriate authority to invoke this method
	 * @exception BusinessViolation The BIS is unable to perform the operation because the operation violates a business process.
	 * @exception SystemFailure The BIS is unable to perform the operation due to an error with a subsystem or or supporting system.
	 * @exception NotImplemented The requested method has not been implemented.
	 * @exception DataNotFound No data found in SBC OSS.
	 * @exception ObjectNotFound For a method that requires an objectId as an input parameter. The objectId does not exist.
	 * @exception MultipleExceptions A list of exception codes are returned from OSS.
	 */
	public RetrieveServiceAreaByPostalCodeReturn retrieveServiceAreaByPostalCode (
			BisContext aContext,
			String aCingularSalesChannel,
			String aPostalCode,
			boolean aRequestServiceAreaIndicator,
			boolean aRequestMarketInformationIndicator)
			throws BusinessViolation,
			ObjectNotFound,
			InvalidData,
			AccessDenied,
			SystemFailure,
			DataNotFound,
			MultipleExceptions,
			NotImplemented
	{
		//RetrieveServiceAreaByPostalCodeReturn retVal = null;
		try {
			loadProperty( aContext );
			log( LogEventId.ENTRY, "LIM Facade - retrieveServiceAreaByPostalCode" ) ;
			log(LogEventId.AUDIT_TRAIL, "LimBean::retrieveServiceAreaByPostalCode()|PRE");

			throwException (
					ExceptionCode.ERR_LIM_REQUEST_NOT_IMPLEMENTED,
					"retrieveServiceAreaByPostalCode is not implemented.",
					"LIM",
					Severity.UnRecoverable);
		}
		finally {
			log(LogEventId.AUDIT_TRAIL, "LimBean::retrieveServiceAreaByPostalCode()|POST");
			log(LogEventId.EXIT, "LIM Facade - retrieveServiceAreaByPostalCode" ) ;
		}

		return null;
	}
	/**
	 * setSessionContext method.
	 * @param ctx javax.ejb.SessionContext
	 * @exception java.rmi.RemoteException The exception description.
	 * @see #getSessionContext
	 */
	public void setSessionContext(javax.ejb.SessionContext ctx) throws java.rmi.RemoteException {
		mySessionCtx = ctx;
	}

	/**
	 * Ping.
	 * Creation date: (07/05/06 10:47 PM)
	 * This operation is used to test the bis context
	 * @return PingReturn
	 * @param aContext The client's calling context. 
	 * @exception InvalidData The data supplied by the client is not valid for performing the method.
	 * @exception AccessDenied The client does not have appropriate authority to invoke this method
	 * @exception BusinessViolation The BIS is unable to perform the operation because the operation violates a business process.
	 * @exception SystemFailure The BIS is unable to perform the operation due to an error with a subsystem or or supporting system. 
	 * @exception NotImplemented The requested method has not been implemented.
	 * @exception DataNotFound No data found in SBC OSS.
	 * @exception ObjectNotFound For a method that requires an objectId as an input parameter. The objectId does not exist. 
	 * @exception MultipleExceptions A list of exception codes are returned from OSS.
	 *
	 *
	 */
	public PingReturn ping(BisContext aContext)
			throws
			BusinessViolation,
			ObjectNotFound,
			InvalidData,
			AccessDenied,
			SystemFailure,
			DataNotFound,
			MultipleExceptions,
			NotImplemented
	{
		//PingReturn retVal = null;

		loadProperty(aContext);

		// added for Client Authororization of Ping method
		Ping_Validate test = new Ping_Validate (getPROPERTIES());

		return test.execute(aContext);

	}



	/**
	 * SelfTest.
	 * Creation date: (07/05/06 10:47 PM)
	 * This operation is used to test the bis context
	 * @return SelfTestReturn
	 * @param aContext The client's calling context. 
	 * @exception InvalidData The data supplied by the client is not valid for performing the method.
	 * @exception AccessDenied The client does not have appropriate authority to invoke this method
	 * @exception BusinessViolation The BIS is unable to perform the operation because the operation violates a business process.
	 * @exception SystemFailure The BIS is unable to perform the operation due to an error with a subsystem or or supporting system. 
	 * @exception NotImplemented The requested method has not been implemented.
	 * @exception DataNotFound No data found in SBC OSS.
	 * @exception ObjectNotFound For a method that requires an objectId as an input parameter. The objectId does not exist. 
	 * @exception MultipleExceptions A list of exception codes are returned from OSS.
	 */
	public SelfTestReturn selfTest(BisContext aContext)
			throws
			BusinessViolation,
			ObjectNotFound,
			InvalidData,
			AccessDenied,
			SystemFailure,
			DataNotFound,
			MultipleExceptions,
			NotImplemented  {

		loadProperty(aContext);

		Hashtable props = new Hashtable();
		props.put("BIS_NAME", getPROPERTIES().get("BIS_NAME"));
		props.put("BIS_VERSION", getPROPERTIES().get("BIS_VERSION"));

		LimSelfTest test = new LimSelfTest(getPROPERTIES());

		return test.execute(aContext);
	}


	/**
	 * retrieveLocationForE911Address method is used to retrieve an E911 MSAG address that's valid.
	 * When a valid address is selected, this operation will return location information
	 * associated with that address ( e.g. PSAP ID, TAR code, etc...)
	 * @return RetrieveLocationForE911AddressReturn
	 * @param aContext BisContext
	 * @param aAddress Address
	 * @param aExchangeCode StringOpt
	 * @param aMaxAddressAlternatives LongOpt
	 * @exception InvalidData The data supplied by the client is not valid for performing the method.
	 * @exception AccessDenied The client does not have appropriate authority to invoke this method
	 * @exception BusinessViolation The BIS is unable to perform the operation because the operation violates a business process.
	 * @exception SystemFailure The BIS is unable to perform the operation due to an error with a subsystem or or supporting system.
	 * @exception NotImplemented The requested method has not been implemented.
	 * @exception DataNotFound No data found.
	 * @exception ObjectNotFound For a method that requires an objectId as an input parameter. The objectId does not exist.
	 */
	public RetrieveLocationForE911AddressReturn retrieveLocationForE911Address (
			BisContext aContext,
			Address aAddress,
			StringOpt aExchangeCode,
			LongOpt aMaxAddressAlternatives)
			throws BusinessViolation,
			InvalidData,
			SystemFailure,
			ObjectNotFound,
			NotImplemented,
			DataNotFound,
			AccessDenied
	{
		//RetrieveLocationForE911AddressReturn retVal = null;
		try
		{
			loadProperty( aContext );
			log( LogEventId.ENTRY, "LIM Facade - retrieveLocationForE911Address" ) ;
			log(LogEventId.AUDIT_TRAIL, "LimBean::retrieveLocationForE911Address()|PRE");

			throwException (
					ExceptionCode.ERR_LIM_REQUEST_NOT_IMPLEMENTED,
					"retrieveLocationForE911Address is not implemented.",
					"LIM",
					Severity.UnRecoverable);
		}
		finally
		{
			log(LogEventId.AUDIT_TRAIL, "LimBean::retrieveLocationForE911Address()|POST");
			log(LogEventId.EXIT, "LIM Facade - retrieveLocationForE911Address" ) ;
		}
		return null;
	}

	/**
	 * retrieveLocationForGeoAddress method is used retrieve a Geo Coordinate address that's valid.
	 * When a valid address is selected, this operation will return location information
	 * associated with that address ( e.g. local serving office, TAR code, etc...)
	 * @return RetrieveLocationForGeoAddressReturn
	 * @param aContext BisContext
	 * @param aAddress Address
	 * @param aMaxAddressAlternatives LongOpt
	 * @exception InvalidData The data supplied by the client is not valid for performing the method.
	 * @exception AccessDenied The client does not have appropriate authority to invoke this method
	 * @exception BusinessViolation The BIS is unable to perform the operation because the operation violates a business process.
	 * @exception SystemFailure The BIS is unable to perform the operation due to an error with a subsystem or or supporting system.
	 * @exception NotImplemented The requested method has not been implemented.
	 * @exception DataNotFound No data found.
	 * @exception ObjectNotFound For a method that requires an objectId as an input parameter. The objectId does not exist.
	 */
	public RetrieveLocationForGeoAddressReturn retrieveLocationForGeoAddress (
			BisContext aContext,
			Address aAddress,
			LongOpt aMaxAddressAlternatives)
			throws BusinessViolation,
			InvalidData,
			SystemFailure,
			ObjectNotFound,
			NotImplemented,
			DataNotFound,
			AccessDenied
	{
		RetrieveLocationForGeoAddressReturn retVal = null;
		try
		{
			loadProperty( aContext );
			log( LogEventId.ENTRY, "LIM Facade - retrieveLocationForGeoAddress" ) ;
			log(LogEventId.AUDIT_TRAIL, "LimBean::retrieveLocationForGeoAddress()|PRE");

			throwException (
					ExceptionCode.ERR_LIM_REQUEST_NOT_IMPLEMENTED,
					"retrieveLocationForGeoAddress is not implemented.",
					"LIM",
					Severity.UnRecoverable);
		}
		finally
		{
			log(LogEventId.AUDIT_TRAIL, "LimBean::retrieveLocationForGeoAddress()|POST");
			log(LogEventId.EXIT, "LIM Facade - retrieveLocationForGeoAddress" ) ;
		}
		return null;
	}

	/**
	 * retrieveLocationForPostalAddress method is used to retrieve a postal address that's valid.
	 * When a valid address is selected, this operation will return location information
	 * associated with that address ( e.g. local serving office, TAR code, etc...)
	 * @return RetrieveLocationForPostalAddressReturn
	 * @param aContext BisContext
	 * @param aAddress Address
	 * @param aMaxAddressAlternatives LongOpt
	 * @exception InvalidData The data supplied by the client is not valid for performing the method.
	 * @exception AccessDenied The client does not have appropriate authority to invoke this method
	 * @exception BusinessViolation The BIS is unable to perform the operation because the operation violates a business process.
	 * @exception SystemFailure The BIS is unable to perform the operation due to an error with a subsystem or or supporting system.
	 * @exception NotImplemented The requested method has not been implemented.
	 * @exception DataNotFound No data found.
	 * @exception ObjectNotFound For a method that requires an objectId as an input parameter. The objectId does not exist.
	 * @exception java.rmi.RemoteException
	 */
	public RetrieveLocationForPostalAddressReturn retrieveLocationForPostalAddress (
			BisContext aContext,
			Address aAddress,
			LongOpt aMaxAddressAlternatives)
			throws BusinessViolation,
			InvalidData,
			SystemFailure,
			ObjectNotFound,
			NotImplemented,
			DataNotFound,
			AccessDenied
	{
		RetrieveLocationForPostalAddressReturn retVal = null;
		try
		{
			try
			{
				loadProperty(aContext);


				log( LogEventId.ENTRY, "LIM Facade - retrieveLocationForPostalAddress") ;
				log(LogEventId.AUDIT_TRAIL, "LimBean::retrieveLocationForPostalAddress()|PRE");

				if (cacheRetrieveLocationForPostalAddress == null)
					cacheRetrieveLocationForPostalAddress = (RetrieveLocationForPostalAddressTrans)new RetrieveLocationForPostalAddressTrans((LIMBase) this ) ;

				/* Retrieve the location information for the address */
				retVal = cacheRetrieveLocationForPostalAddress.execute(
						aContext,
						aAddress,
						aMaxAddressAlternatives);
			}
			catch(AccessDenied e) 			{throw e;}
			catch(BusinessViolation e) 		{throw e;}
			catch(DataNotFound e) 			{throw e;}
			catch(InvalidData e) 			{throw e;}
			catch(NotImplemented e) 		{throw e;}
			catch(ObjectNotFound e) 		{throw e;}
			catch( SystemFailure e ) 		{throw e;}
			/**
			 * Unexpected exception caught, need to follow up on the stack trace.
			 */
			catch ( Throwable e )
			{
				throwException(ExceptionCode.ERR_LIM_UNEXPECTED_ERROR,
						"LIM caught an uncaught exception: " + e.toString(),
						"LIM", Severity.UnRecoverable, (Exception)e) ;
			}
		}
		finally
		{
			try
			{
				log(LogEventId.AUDIT_TRAIL, "LimBean::retrieveLocationForPostalAddress()|POST");
				log(LogEventId.EXIT, "LIM Facade - retrieveLocationForPostalAddress") ;
			}
			catch (Exception e) {/*When callerContext or PROPERTIES is not initialized properly, it will come to here for Null Pointer Exception */}
		}
		return retVal;
	}

	/**
	 * retrieveLocationForServiceAddress method is used to retrieve a service address that's valid.
	 * When a valid address is selected, this operation will return location information
	 * associated with that address ( e.g. local serving office, TAR code, etc...)
	 * @return RetrieveLocationForServiceAddressReturn
	 * @param aContext BisContext
	 * @param aAddress Address
	 * @param aLocationPropertiesToGet LocationPropertiesToGetSeqOpt
	 * @param aPreviousCustomerName StringOpt
	 * @param aCrossBoundaryState StringOpt
	 * @param aMaxBasicAddressAlternatives LongOpt
	 * @param aMaxLivingUnitAlternatives LongOpt
	 * @exception InvalidData The data supplied by the client is not valid for performing the method.
	 * @exception AccessDenied The client does not have appropriate authority to invoke this method
	 * @exception BusinessViolation The BIS is unable to perform the operation because the operation violates a business process.
	 * @exception SystemFailure The BIS is unable to perform the operation due to an error with a subsystem or or supporting system.
	 * @exception NotImplemented The requested method has not been implemented.
	 * @exception DataNotFound No data found.
	 * @exception ObjectNotFound For a method that requires an objectId as an input parameter. The objectId does not exist.
	 */
	public RetrieveLocationForServiceAddressReturn retrieveLocationForServiceAddress (
			BisContext aContext,
			Address aAddress,
			LocationPropertiesToGetSeqOpt aLocationPropertiesToGet,
			StringOpt aPreviousCustomerName,
			StringOpt aCrossBoundaryState,
			LongOpt aMaxBasicAddressAlternatives,
			LongOpt aMaxLivingUnitAlternatives)
			throws BusinessViolation,
			InvalidData,
			SystemFailure,
			ObjectNotFound,
			NotImplemented,
			DataNotFound,
			AccessDenied
	{
		RetrieveLocationForServiceAddressReturn retVal = null;
		try
		{
			try
			{
				loadProperty(aContext);
				loadZipToSaga();

				log(LogEventId.ENTRY, "LIM Facade - retrieveLocationForServiceAddress");
				log(LogEventId.AUDIT_TRAIL, "LimBean::retrieveLocationForServiceAddress()|PRE");

				if (cacheRetrieveLocationForServiceAddress == null)
					cacheRetrieveLocationForServiceAddress = (RetrieveLocationForServiceAddressTrans)new RetrieveLocationForServiceAddressTrans((LIMBase) this);

				//Making sure aLocationPropertiesToGet is not null;
				if (aLocationPropertiesToGet == null)
				{
					aLocationPropertiesToGet = new LocationPropertiesToGetSeqOpt();
					aLocationPropertiesToGet.__default();
				}

				/* Retrieve the location information for the address */
				retVal = cacheRetrieveLocationForServiceAddress.execute(
						aAddress,
						aLocationPropertiesToGet,
						aPreviousCustomerName,
						aCrossBoundaryState,
						aMaxBasicAddressAlternatives,
						aMaxLivingUnitAlternatives);
			}
			catch(AccessDenied e) 			{throw e;}
			catch(BusinessViolation e) 		{throw e;}
			catch(DataNotFound e) 			{throw e;}
			catch(InvalidData e) 			{throw e;}
			catch(NotImplemented e) 		{throw e;}
			catch(ObjectNotFound e) 		{throw e;}
			catch(SystemFailure e) 			{throw e;}
			/**
			 * Unexpected exception caught, need to follow up on the stack trace.
			 */
			catch (Throwable e)
			{
				throwException(ExceptionCode.ERR_LIM_UNEXPECTED_ERROR,
						"LIM caught an uncaught exception: " + e.toString(),
						"LIM", Severity.UnRecoverable, (Exception)e);
			}
		}
		finally
		{
			try
			{
				log(LogEventId.AUDIT_TRAIL, "LimBean::retrieveLocationForServiceAddress()|POST");
				log(LogEventId.EXIT, "LIM Facade - retrieveLocationForServiceAddress");
			}
			catch (Exception e) {/*When callerContext or PROPERTIES is not initialized properly, it will come to here for Null Pointer Exception */}
		}
		return retVal;
	}




	/**
	 * retrieveLocationForTelephoneNumber method is used to retrieve a service location based on telephone number that's valid.
	 * When a valid address is selected, this operation will return service location information
	 * @return RetrieveLocationForTelephoneNumberReturn
	 * @param aContext BisContext
	 * @param aTelephoneNumber TelephoneNumber
	 * @param aLocationPropertiesToGet LocationPropertiesToGetSeqOpt
	 * @exception InvalidData The data supplied by the client is not valid for performing the method.
	 * @exception AccessDenied The client does not have appropriate authority to invoke this method
	 * @exception BusinessViolation The BIS is unable to perform the operation because the operation violates a business process.
	 * @exception SystemFailure The BIS is unable to perform the operation due to an error with a subsystem or or supporting system.
	 * @exception NotImplemented The requested method has not been implemented.
	 * @exception DataNotFound No data found.
	 * @exception ObjectNotFound For a method that requires an objectId as an input parameter. The objectId does not exist.
	 */

	public RetrieveLocationForTelephoneNumberReturn retrieveLocationForTelephoneNumber (
			BisContext aContext,
			TelephoneNumber aTelephoneNumber,
			LocationPropertiesToGetSeqOpt aLocationPropertiesToGet)
			throws BusinessViolation,
			InvalidData,
			SystemFailure,
			ObjectNotFound,
			NotImplemented,
			DataNotFound,
			AccessDenied
	{
		RetrieveLocationForTelephoneNumberReturn retVal = null;

		try
		{
			try
			{
				loadProperty( aContext );
				loadZipToSaga();
			}
			catch( SystemFailure e )
			{
				throw e;
			}

			log( LogEventId.ENTRY, "LIM Facade - retrieveLocationForTelephoneNumber" ) ;
			log(LogEventId.AUDIT_TRAIL, "LimBean::retrieveLocationForTelephoneNumber()|RetrieveLocationForTelephoneNumberTrans::execute()|PRE");

			try
			{
				if ( cacheRetrieveLocationForTelephoneNumber == null )
					cacheRetrieveLocationForTelephoneNumber = (RetrieveLocationForTelephoneNumberTrans)new RetrieveLocationForTelephoneNumberTrans( this ) ;

				//Making sure LocationPropertiesToGetSeqOpt is not null;
				if (aLocationPropertiesToGet == null)
				{
					aLocationPropertiesToGet = new LocationPropertiesToGetSeqOpt();
					aLocationPropertiesToGet.__default();
				}

				/* Retrieve the location information for the service */
				retVal = cacheRetrieveLocationForTelephoneNumber.execute(
						aTelephoneNumber,
						aLocationPropertiesToGet);
			}
			catch( AccessDenied e ) 		{throw e;}
			catch( BusinessViolation e ) 	{throw e;}
			catch( DataNotFound e ) 		{throw e;}
			catch( InvalidData e ) 			{throw e;}
			catch( NotImplemented e ) 		{throw e;}
			catch( ObjectNotFound e ) 		{throw e;}
			catch( SystemFailure e ) 		{throw e;}

			/**
			 * Unexpected exception caught, need to follow up on the stack trace.
			 */
			catch ( Throwable e )
			{
				throwException( ExceptionCode.ERR_LIM_UNEXPECTED_ERROR,
						"LIM caught an uncaught exception: " + e.toString(),
						"LIM", Severity.UnRecoverable, (Exception)e ) ;
			}
		}
		finally
		{
			log(LogEventId.AUDIT_TRAIL, "LimBean::retrieveLocationForTelephoneNumber()|RetrieveLocationForTelephoneNumberTrans::execute()|POST");
			log( LogEventId.EXIT, "LIM Facade - retrieveLocationForTelephoneNumber" ) ;
		}

		return retVal;
	}

	/**
	 * updateBillingAddress method is used to update billing address in BCAM. Optionally send a note to BIMX BIS
	 * @return UpdateBillingAddressReturn
	 * @param aContext BisContext
	 * @param aBillingAccountKey CompositeObjectKey
	 * @param aOldAddress Address
	 * @param aNewAddress Address
	 * @param aDeliveryPointValidationCode StringOpt
	 * @param aContactName StringOpt
	 * @exception InvalidData The data supplied by the client is not valid for performing the method.
	 * @exception AccessDenied The client does not have appropriate authority to invoke this method
	 * @exception BusinessViolation The BIS is unable to perform the operation because the operation violates a business process.
	 * @exception SystemFailure The BIS is unable to perform the operation due to an error with a subsystem or or supporting system.
	 * @exception NotImplemented The requested method has not been implemented.
	 * @exception DataNotFound No data found.
	 * @exception ObjectNotFound For a method that requires an objectId as an input parameter. The objectId does not exist.
	 */
	public UpdateBillingAddressReturn updateBillingAddress (
			BisContext aContext,
			CompositeObjectKey aBillingAccountKey,
			AddressOpt aOldAddress,
			Address aNewAddress,
			StringOpt aDeliveryPointValidationCode,
			StringOpt aContactName)
			throws BusinessViolation,
			InvalidData,
			SystemFailure,
			ObjectNotFound,
			NotImplemented,
			DataNotFound,
			AccessDenied
	{
		UpdateBillingAddressReturn retVal = null;
		try
		{
			loadProperty(aContext);
			log(LogEventId.ENTRY, "LIM Facade - updateBillingAddress") ;
			log(LogEventId.AUDIT_TRAIL, "LimBean::updateBillingAddress()|UpdateBillingAddressTrans::execute()|PRE");

			if (cacheUpdateBillingAddress == null)
				cacheUpdateBillingAddress = (UpdateBillingAddressTrans)new UpdateBillingAddressTrans((LIMBase) this);

			retVal = cacheUpdateBillingAddress.execute(
					aBillingAccountKey,
					aOldAddress,
					aNewAddress,
					aDeliveryPointValidationCode,
					aContactName);
		}
		catch(AccessDenied e) 			{throw e;}
		catch(BusinessViolation e) 		{throw e;}
		catch(DataNotFound e) 			{throw e;}
		catch(InvalidData e) 			{throw e;}
		catch(NotImplemented e) 		{throw e;}
		catch(ObjectNotFound e) 		{throw e;}
		catch(SystemFailure e) 			{throw e;}
		/**
		 * Unexpected exception caught, need to follow up on the stack trace.
		 */
		catch (Throwable e)
		{
			throwException(ExceptionCode.ERR_LIM_UNEXPECTED_ERROR,
					"LIM caught an uncaught exception: " + e.toString(),
					"LIM", Severity.UnRecoverable, (Exception)e);
		}
		finally
		{
			try
			{
				log(LogEventId.AUDIT_TRAIL, "LimBean::updateBillingAddress()|UpdateBillingAddressTrans::execute()|POST");
				log(LogEventId.EXIT, "LIM Facade - updateBillingAddress" ) ;
			}
			catch (Exception e) {/*When callerContext or PROPERTIES is not initialized properly, it will come to here for Null Pointer Exception */}
		}
		return retVal;
	}

	/**
	 * retrieveLocationForPostalAddress2 method is used to retrieve a postal address that's valid.
	 * When a valid address is selected, this operation will return location information
	 * associated with that address ( e.g. local serving office, TAR code, etc...)
	 * @return RetrieveLocationForPostalAddressReturn
	 * @param aContext BisContext
	 * @param aAddress Address
	 * @param aMaxAddressAlternatives LongOpt
	 * @exception InvalidData The data supplied by the client is not valid for performing the method.
	 * @exception AccessDenied The client does not have appropriate authority to invoke this method
	 * @exception BusinessViolation The BIS is unable to perform the operation because the operation violates a business process.
	 * @exception SystemFailure The BIS is unable to perform the operation due to an error with a subsystem or or supporting system.
	 * @exception NotImplemented The requested method has not been implemented.
	 * @exception DataNotFound No data found.
	 * @exception ObjectNotFound For a method that requires an objectId as an input parameter. The objectId does not exist.
	 * @exception java.rmi.RemoteException
	 */
	public RetrieveLocationForPostalAddress2Return retrieveLocationForPostalAddress2(
			BisContext aContext,
			Address aAddress,
			LongOpt aMaxAddressAlternatives,
			LongOpt aMaxCassCharPerLine)
			throws BusinessViolation,
			ObjectNotFound,
			InvalidData,
			AccessDenied,
			SystemFailure,
			DataNotFound,
			NotImplemented
	{
		//RetrieveLocationForPostalAddress2Return retVal = null;
		try
		{
			loadProperty( aContext );
			log( LogEventId.ENTRY, "LIM Facade - retrieveLocationForPostalAddress2" ) ;
			log(LogEventId.AUDIT_TRAIL, "LimBean::retrieveLocationForPostalAddress2()|PRE");

			throwException (
					ExceptionCode.ERR_LIM_REQUEST_NOT_IMPLEMENTED,
					"retrieveLocationForPostalAddress2 is not implemented.",
					"LIM",
					Severity.UnRecoverable);
		}
		finally
		{
			log(LogEventId.AUDIT_TRAIL, "LimBean::retrieveLocationForPostalAddress2()|POST");
			log(LogEventId.EXIT, "LIM Facade - retrieveLocationForPostalAddress2" ) ;
		}
		return null;
	}
}

