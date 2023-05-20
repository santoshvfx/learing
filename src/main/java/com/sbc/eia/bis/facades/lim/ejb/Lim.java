// $Id: Lim.java,v 1.23 2009/02/03 18:42:06 jv7958 Exp $

package com.sbc.eia.bis.facades.lim.ejb;

import java.rmi.RemoteException;

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
import com.sbc.eia.idl.lim.FieldAddressReturn;
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
import com.sbc.eia.idl.types.LongOpt;
import com.sbc.eia.idl.types.StringOpt;
/**
 * This is an Enterprise Java Bean Remote Interface.
 */
public interface Lim extends javax.ejb.EJBObject {

/**
 * fieldAddress method is used to convert an unfielded address into a fielded address.
 * @return FieldAddressReturn
 * @param aBisContext      a BisContext object
 * @param aUnfieldAddress  an UnfieldedAddress object
 * @exception InvalidData The data supplied by the client is not valid for performing the method. 
 * @exception AccessDenied The client does not have appropriate authority to invoke this method 
 * @exception BusinessViolation The BIS is unable to perform the operation because the operation violates a business process. 
 * @exception SystemFailure The BIS is unable to perform the operation due to an error with a subsystem or or supporting system. 
 * @exception NotImplemented The requested method has not been implemented.
 * @exception DataNotFound No data found in SBC OSS.
 * @exception ObjectNotFound For a method that requires an objectId as an input parameter. The objectId does not exist.
 * @exception java.rmi.RemoteException
 */
 
FieldAddressReturn fieldAddress(BisContext aBisContext, UnfieldedAddress aUnfieldAddress) throws ObjectNotFound, InvalidData, NotImplemented, AccessDenied, SystemFailure, java.rmi.RemoteException, DataNotFound, BusinessViolation;

/**
 * retrieveLocationForAddress method is used to retrieve an address that exists in SBC's OSS.
 * When a valid address is selected, this operation will return location information
 * associated with that address ( e.g.local serving office, primary NPANXX, TAR code, etc...)
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
 * @exception java.rmi.RemoteException
 */

RetrieveLocationForAddressReturn retrieveLocationForAddress(BisContext aContext, Address aAddress, ProviderLocationPropertiesSeqOpt aPropertiesToGet, LongOpt aMaximumBasicAddressReturnLimit, LongOpt aMaximumUnitReturnLimit, StringOpt  aExchangeCode) throws ObjectNotFound, InvalidData, NotImplemented, AccessDenied, SystemFailure, java.rmi.RemoteException, DataNotFound, BusinessViolation;

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
 * @exception java.rmi.RemoteException
 */

RetrieveLocationForServiceReturn retrieveLocationForService(BisContext aContext, TelephoneNumber aTelephoneNumber, ProviderLocationPropertiesSeqOpt aPropertiesToGet, LongOpt aMaximumBasicAddressReturnLimit, LongOpt aMaximumUnitReturnLimit) throws ObjectNotFound, InvalidData, NotImplemented, AccessDenied, SystemFailure, java.rmi.RemoteException, DataNotFound, BusinessViolation;

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

RetrieveServiceAreaByPostalCodeReturn retrieveServiceAreaByPostalCode (BisContext aContext, String aCingularSalesChannel, String aPostalCode, boolean aRequestServiceAreaIndicator, boolean aRequestMarketInformationIndicator) throws BusinessViolation, ObjectNotFound, InvalidData, AccessDenied, SystemFailure, DataNotFound, MultipleExceptions, java.rmi.RemoteException, NotImplemented;
 
    /**
	 * Ping.
     * Creation date: (07/05/06 10:47 PM)
     * This operation is used to test the bis context
 	 * @return PingReturn 
	 * @param aContext The client's calling context. 
	 * @throws NotImplemented a NotImplemented exception
	 * @throws RemoteException a RemoteException exception
	 */     
    public PingReturn ping(BisContext aContext)
    	throws
            BusinessViolation,
            ObjectNotFound,
            InvalidData,
            AccessDenied,
            SystemFailure,
            DataNotFound,
            NotImplemented,
            MultipleExceptions,
            java.rmi.RemoteException;
            
    /**
	 * SelfTest.
     * Creation date: (07/05/06 10:47 PM)
     * This operation is used to test the bis context
 	 * @return SelfTestReturn 
	 * @param aContext The client's calling context. 
	 * @throws NotImplemented a NotImplemented exception
	 * @throws RemoteException a RemoteException exception
	 */
	public SelfTestReturn selfTest(BisContext aContext) 
    	throws
            BusinessViolation,
            ObjectNotFound,
            InvalidData,
            AccessDenied,
            SystemFailure,
            DataNotFound,
            NotImplemented,
            MultipleExceptions,
            java.rmi.RemoteException;
	
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
     * @exception java.rmi.RemoteException
	 */
	public RetrieveLocationForE911AddressReturn retrieveLocationForE911Address(BisContext aContext, Address aAddress, StringOpt aExchangeCode, LongOpt aMaxAddressAlternatives) 
    	throws
            BusinessViolation,
            ObjectNotFound,
            InvalidData,
            AccessDenied,
            SystemFailure,
            DataNotFound,
            NotImplemented,
            java.rmi.RemoteException;

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
     * @exception java.rmi.RemoteException
	 */
	public RetrieveLocationForGeoAddressReturn retrieveLocationForGeoAddress(BisContext aContext, Address aAddress, LongOpt aMaxAddressAlternatives) 
    	throws
            BusinessViolation,
            ObjectNotFound,
            InvalidData,
            AccessDenied,
            SystemFailure,
            DataNotFound,
            NotImplemented,
            java.rmi.RemoteException;	

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
	public RetrieveLocationForPostalAddressReturn retrieveLocationForPostalAddress(BisContext aContext, Address aAddress, LongOpt aMaxAddressAlternatives) 
    	throws
            BusinessViolation,
            ObjectNotFound,
            InvalidData,
            AccessDenied,
            SystemFailure,
            DataNotFound,
            NotImplemented,
            java.rmi.RemoteException;
	
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
     * @exception java.rmi.RemoteException
	 */
	public RetrieveLocationForServiceAddressReturn retrieveLocationForServiceAddress(BisContext aContext, Address aAddress, LocationPropertiesToGetSeqOpt aLocationPropertiesToGet, StringOpt aPreviousCustomerName, StringOpt aCrossBoundaryState, LongOpt aMaxBasicAddressAlternatives, LongOpt aMaxLivingUnitAlternatives) 
    	throws
            BusinessViolation,
            ObjectNotFound,
            InvalidData,
            AccessDenied,
            SystemFailure,
            DataNotFound,
            NotImplemented,
            java.rmi.RemoteException;
	
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
     * @exception java.rmi.RemoteException
	 */
	public RetrieveLocationForTelephoneNumberReturn retrieveLocationForTelephoneNumber(BisContext aContext, TelephoneNumber aTelephoneNumber, LocationPropertiesToGetSeqOpt aLocationPropertiesToGet) 
    	throws
            BusinessViolation,
            ObjectNotFound,
            InvalidData,
            AccessDenied,
            SystemFailure,
            DataNotFound,
            NotImplemented,
            java.rmi.RemoteException;			
    /**
     * updateBillingAddress method is used to change a customer's billing address and add notes in BOSS systems.
     * When an address is updated successfully, this operation will return the BisContext as confirmation to
     * the client.  Otherwise, it will return an exception.
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
     * @exception java.rmi.RemoteException
     */
    public UpdateBillingAddressReturn updateBillingAddress(
        BisContext aContext,
        CompositeObjectKey aBillingAccountKey,
        AddressOpt aOldAddress,
        Address aNewAddress,
        StringOpt aDeliveryPointValidationCode,
        StringOpt aContactName)
        throws 
        	BusinessViolation,
        	InvalidData,
        	SystemFailure,
        	ObjectNotFound,
        	NotImplemented,
        	DataNotFound,
        	AccessDenied,
        	java.rmi.RemoteException;
    
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
	public RetrieveLocationForPostalAddress2Return retrieveLocationForPostalAddress2(BisContext aContext, Address aAddress, LongOpt aMaxAddressAlternatives,LongOpt aMaxCassCharPerLine) 
    	throws
            BusinessViolation,
            ObjectNotFound,
            InvalidData,
            AccessDenied,
            SystemFailure,
            DataNotFound,
            NotImplemented,
            java.rmi.RemoteException;
}
