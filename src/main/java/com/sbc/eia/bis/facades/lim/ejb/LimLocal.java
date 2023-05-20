//$Id: LimLocal.java,v 1.15 2008/02/15 00:23:53 jd3462 Exp $
package com.sbc.eia.bis.facades.lim.ejb;

import com.sbc.eia.idl.bis_types.AccessDenied;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.bis_types.BusinessViolation;
import com.sbc.eia.idl.bis_types.DataNotFound;
import com.sbc.eia.idl.bis_types.InvalidData;
import com.sbc.eia.idl.bis_types.MultipleExceptions;
import com.sbc.eia.idl.bis_types.NotImplemented;
import com.sbc.eia.idl.bis_types.ObjectNotFound;
import com.sbc.eia.idl.bis_types.SystemFailure;
import com.sbc.eia.idl.lim.PingReturn;
import com.sbc.eia.idl.lim.SelfTestReturn;
import com.sbc.eia.idl.lim_types.ProviderLocationPropertiesSeqOpt;
import com.sbc.eia.idl.lim_types.Address;
import com.sbc.eia.idl.lim_types.AddressOpt;
import com.sbc.eia.idl.types.StringOpt;
import com.sbc.eia.idl.types.LongOpt;
import com.sbc.eia.idl.lim.RetrieveLocationForE911AddressReturn;
import com.sbc.eia.idl.lim.RetrieveLocationForGeoAddressReturn;
import com.sbc.eia.idl.lim.RetrieveLocationForPostalAddress2Return;
import com.sbc.eia.idl.lim.RetrieveLocationForPostalAddressReturn;
import com.sbc.eia.idl.lim_types.LocationPropertiesToGetSeqOpt;
import com.sbc.eia.idl.lim.RetrieveLocationForServiceAddressReturn;
import com.sbc.eia.idl.bis_types.TelephoneNumber;
import com.sbc.eia.idl.lim.RetrieveLocationForTelephoneNumberReturn;
import com.sbc.eia.idl.types.CompositeObjectKey;
import com.sbc.eia.idl.lim.UpdateBillingAddressReturn;
/**
 * Local interface for Enterprise Bean: Lim
 */
public interface LimLocal extends javax.ejb.EJBLocalObject {
	
	/**
	 * fieldAddress
	 */
	public com.sbc.eia.idl.lim.FieldAddressReturn fieldAddress(
		com.sbc.eia.idl.bis_types.BisContext aBisContext,
		com.sbc.eia.idl.lim_types.UnfieldedAddress aUnfieldAddress)
		throws
			com.sbc.eia.idl.bis_types.ObjectNotFound,
			com.sbc.eia.idl.bis_types.InvalidData,
			com.sbc.eia.idl.bis_types.NotImplemented,
			com.sbc.eia.idl.bis_types.AccessDenied,
			com.sbc.eia.idl.bis_types.SystemFailure,
			com.sbc.eia.idl.bis_types.DataNotFound,
			com.sbc.eia.idl.bis_types.BusinessViolation;
	
    /**
	 * retrieveLocationForAddress
	 */
	public com
		.sbc
		.eia
		.idl
		.lim
		.RetrieveLocationForAddressReturn retrieveLocationForAddress(
			com.sbc.eia.idl.bis_types.BisContext aContext,
			com.sbc.eia.idl.lim_types.Address aAddress,
            ProviderLocationPropertiesSeqOpt aPropertiesToGet,
			com.sbc.eia.idl.types.LongOpt aMaximumBasicAddressReturnLimit,
			com.sbc.eia.idl.types.LongOpt aMaximumUnitReturnLimit,
			com.sbc.eia.idl.types.StringOpt aExchangeCode)
		throws
			com.sbc.eia.idl.bis_types.ObjectNotFound,
			com.sbc.eia.idl.bis_types.InvalidData,
			com.sbc.eia.idl.bis_types.NotImplemented,
			com.sbc.eia.idl.bis_types.AccessDenied,
			com.sbc.eia.idl.bis_types.SystemFailure,
			com.sbc.eia.idl.bis_types.DataNotFound,
			com.sbc.eia.idl.bis_types.BusinessViolation;
	/**
	 * retrieveLocationForService
	 */
	public com
		.sbc
		.eia
		.idl
		.lim
		.RetrieveLocationForServiceReturn retrieveLocationForService(
			com.sbc.eia.idl.bis_types.BisContext aContext,
			com.sbc.eia.idl.bis_types.TelephoneNumber aTelephoneNumber,
            com.sbc.eia.idl.lim_types.ProviderLocationPropertiesSeqOpt aPropertiesToGet,
			com.sbc.eia.idl.types.LongOpt aMaximumBasicAddressReturnLimit,
			com.sbc.eia.idl.types.LongOpt aMaximumUnitReturnLimit)
		throws
			com.sbc.eia.idl.bis_types.ObjectNotFound,
			com.sbc.eia.idl.bis_types.InvalidData,
			com.sbc.eia.idl.bis_types.NotImplemented,
			com.sbc.eia.idl.bis_types.AccessDenied,
			com.sbc.eia.idl.bis_types.SystemFailure,
			com.sbc.eia.idl.bis_types.DataNotFound,
			com.sbc.eia.idl.bis_types.BusinessViolation;
	/**
	 * retrieveServiceAreaByPostalCode
	 */
	public com
  		.sbc
  		.eia
  		.idl
  		.lim
  		.RetrieveServiceAreaByPostalCodeReturn retrieveServiceAreaByPostalCode(
  			com.sbc.eia.idl.bis_types.BisContext aContext, 
            String aCingularSalesChannel,
            String aPostalCode,
            boolean aRequestServiceAreaIndicator,
            boolean aRequestMarketInformationIndicator)
  		throws 
  			com.sbc.eia.idl.bis_types.BusinessViolation, 
  			com.sbc.eia.idl.bis_types.ObjectNotFound, 
  			com.sbc.eia.idl.bis_types.InvalidData, 
  			com.sbc.eia.idl.bis_types.AccessDenied, 
  			com.sbc.eia.idl.bis_types.SystemFailure, 
  			com.sbc.eia.idl.bis_types.DataNotFound, 
  			com.sbc.eia.idl.bis_types.MultipleExceptions, 
  			com.sbc.eia.idl.bis_types.NotImplemented;
	
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
            NotImplemented;     
    
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
            NotImplemented;     	
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
    public RetrieveLocationForE911AddressReturn retrieveLocationForE911Address(
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
        AccessDenied;
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
    public RetrieveLocationForGeoAddressReturn retrieveLocationForGeoAddress(
        BisContext aContext,
        Address aAddress,
        LongOpt aMaxAddressAlternatives)
        throws BusinessViolation,
        InvalidData,
        SystemFailure,
        ObjectNotFound,
        NotImplemented,
        DataNotFound,
        AccessDenied;
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
    public RetrieveLocationForPostalAddressReturn retrieveLocationForPostalAddress(
        BisContext aContext,
        Address aAddress,
        LongOpt aMaxAddressAlternatives)
        throws BusinessViolation,
        InvalidData,
        SystemFailure,
        ObjectNotFound,
        NotImplemented,
        DataNotFound,
        AccessDenied;
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
    public RetrieveLocationForServiceAddressReturn retrieveLocationForServiceAddress(
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
        AccessDenied;
    /**
     * retrieveLocationForTelephoneNumber method is used to retrieve an address based on telephone number that's valid.
     * When a valid address is selected, this operation will return location information
     * associated with that address ( e.g. local serving office, TAR code, etc...)
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
    public RetrieveLocationForTelephoneNumberReturn retrieveLocationForTelephoneNumber(
        BisContext aContext,
        TelephoneNumber aTelephoneNumber,
        LocationPropertiesToGetSeqOpt aLocationPropertiesToGet)
        throws BusinessViolation,
        InvalidData,
        SystemFailure,
        ObjectNotFound,
        NotImplemented,
        DataNotFound,
        AccessDenied;
    /**
     * updateBillingAddress method is 
     * @return UpdateBillingAddressReturn
     * @param aContext BisContext
     * @param aBillingAccountKey CompositeObjectKey
     * @param aOldAddress AddressOpt
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
    public UpdateBillingAddressReturn updateBillingAddress(
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
        AccessDenied;
    
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
			NotImplemented;
}
