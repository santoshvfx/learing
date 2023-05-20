// $Id: Company.java,v 1.3 2007/07/17 18:40:48 cm2159 Exp $

package com.sbc.eia.bis.BusinessInterface;

import com.sbc.eia.idl.exception_types.*;
/**
 * Company is the class which defines a company.
 * Creation date: (3/13/01 2:39:06 PM)
 * @author: Creighton Malet
 */
public class Company {
	private String code = null;
	private HostFactory hostFactory = null;
	private State state = null;
	private Selector selector = null;
	private final static java.lang.String ANY_COMPANY = "_ANY";
	private com.sbc.bccs.utilities.Utility utility = null;

	// Statics for companies
	public static final String Company_Ameritech = "AIT";
	public static final String Company_BellSouth = "BLS";
	public static final String Company_PacificBell = "PB";
	public static final String Company_SouthernNETelephone = "SNET";
	public static final String Company_SouthWesternBell = "SWBT";
	public static final String Company_SBCTelecom = "SBCT";
/**
 * Class constructor.
 */
private Company() {
}
/**
 * Class constructor accepting company code, state, host factory and utility
 * Creation date: (3/13/01 5:52:00 PM)
 * @param aCode java.lang.String
 * @param aState com.sbc.eia.bis.BusinessInterface.State
 * @param aHostFactory com.sbc.eia.bis.BusinessInterface.HostFactory
 * @param aUtility com.sbc.bccs.utilities.Utility
 * @exception com.sbc.eia.bis.BusinessInterface.NullDataException. A required input parameter was null.
 * @exception com.sbc.eia.bis.BusinessInterface.InvalidCompanyException. An invalid company code was supplied.
 */
public Company(String aCode, State aState, HostFactory aHostFactory, com.sbc.bccs.utilities.Utility aUtility) 
		throws NullDataException, InvalidCompanyException
{
	if (aCode == null)
		throw new NullDataException(ExceptionCode.ERR_BSI_BUSINESS_NULL_DATA, "Null Code in Company::Company()");
	if (aState == null)
		throw new NullDataException(ExceptionCode.ERR_BSI_BUSINESS_NULL_DATA, "Null State in Company::Company()");
		
	code = aCode.toUpperCase();
	hostFactory = aHostFactory;
	utility = aUtility;
	state = aState;
	// Selector uses state so state must be set
	selector = new Selector();
	selector.setCompany(this);
	
}
/**
 * Determines whether two companies are logically equal.
 * @return boolean
 * @param aCompany com.sbc.eia.bis.BusinessInterface.Company
 */
public boolean equals(Company aCompany) {
	return this.code.equals(aCompany.code) ? true : false;
}
/**
 * Determines whether a company and a company code are logically equal.
 * @return boolean
 * @param aCode java.lang.String
 */
public boolean equals(String aCode) {
	return this.code.equals(aCode.toUpperCase()) ? true : false;
}
/**
 * Returns a Company which represents any company.
 * @return com.sbc.eia.bis.BusinessInterface.Company
 * @param aState com.sbc.eia.bis.BusinessInterface.State
 * @exception com.sbc.eia.bis.BusinessInterface.InvalidStateException. An invalid state was supplied.
 */
public static Company getAnAnyCompany(State aState)
	throws InvalidStateException
{
	Company aCompany = new Company();
	aCompany.code = ANY_COMPANY;
	aCompany.state = aState;
	return aCompany;
}
/**
 * Returns an object that implements the required interface.
 * @return com.sbc.eia.bis.BusinessInterface.Business
 * @param anInterface java.lang.String
 * @exception com.sbc.eia.bis.BusinessInterface.InvalidInterfaceException. The interface supplied is invalid.
 * @exception com.sbc.eia.bis.BusinessInterface.NotImplementedException. The interface requested is not implemented.
 * @exception com.sbc.eia.bis.BusinessInterface.SeriousFailureException. A serious failure occurred.
 */
public Business getBusinessInterface(String anInterface)
	throws InvalidInterfaceException, NotImplementedException,
		SeriousFailureException
{
	selector.setHost(null);
	selector.setInterface(anInterface);
	selector.setClientKey(null);
	return (Business)hostFactory.getHost(selector, null);
}
/**
 * Returns an object that implements the required interface.
 * @return com.sbc.eia.bis.BusinessInterface.Business
 * @param anInterface java.lang.String
 * @param aClientKey com.sbc.eia.bis.BusinessInterface.ClientKey
 * @exception com.sbc.eia.bis.BusinessInterface.InvalidInterfaceException. The interface supplied is invalid.
 * @exception com.sbc.eia.bis.BusinessInterface.NotImplementedException. The interface requested is not implemented.
 * @exception com.sbc.eia.bis.BusinessInterface.SeriousFailureException. A serious failure occurred.
 */
public Business getBusinessInterface(String anInterface, ClientKey aClientKey)
	throws InvalidInterfaceException, NotImplementedException,
		SeriousFailureException
{
	selector.setHost(null);
	selector.setInterface(anInterface);
	selector.setClientKey(aClientKey);
	return (Business)hostFactory.getHost(selector, null);
}
/**
 * Returns an object that implements the required interface. The supplied
 *	pass-through object is attached to the implementing object and can be accessed by that object later such
 *	as when an interface method on that object is invoked.
 * @return com.sbc.eia.bis.BusinessInterface.Business
 * @param anInterface java.lang.String
 * @param aPassThru java.lang.Object
 * @exception com.sbc.eia.bis.BusinessInterface.InvalidInterfaceException. The interface supplied is invalid.
 * @exception com.sbc.eia.bis.BusinessInterface.NotImplementedException. The interface requested is not implemented.
 * @exception com.sbc.eia.bis.BusinessInterface.SeriousFailureException. A serious failure occurred.
 */
public Business getBusinessInterface(String anInterface, Object aPassThru)
	throws InvalidInterfaceException, NotImplementedException,
		SeriousFailureException
{
	selector.setHost(null);
	selector.setInterface(anInterface);
	selector.setClientKey(null);
	return (Business)hostFactory.getHost(selector, aPassThru);
}
/**
 * Returns an object that implements the required interface. The supplied
 *	pass-through object is attached to the implementing object and can be accessed by that object later such
 *	as when an interface method on that object is invoked.
 * @return com.sbc.eia.bis.BusinessInterface.Business
 * @param anInterface java.lang.String
 * @param aPassThru java.lang.Object
 * @param aClientKey com.sbc.eia.bis.BusinessInterface.ClientKey
 * @exception com.sbc.eia.bis.BusinessInterface.InvalidInterfaceException. The interface supplied is invalid.
 * @exception com.sbc.eia.bis.BusinessInterface.NotImplementedException. The interface requested is not implemented.
 * @exception com.sbc.eia.bis.BusinessInterface.SeriousFailureException. A serious failure occurred.
 */
public Business getBusinessInterface(String anInterface, Object aPassThru, ClientKey aClientKey)
	throws InvalidInterfaceException, NotImplementedException,
		SeriousFailureException
{
	selector.setHost(null);
	selector.setInterface(anInterface);
	selector.setClientKey(aClientKey);
	return (Business)hostFactory.getHost(selector, aPassThru);
}
/**
 * Returns an object that implements the required interface.
 *	The search for the appropriate interface object is started at the node in the host tree specified
 *	by the supplied host value.
 * @return com.sbc.eia.bis.BusinessInterface.Business
 * @param anInterface java.lang.String
 * @param aHost java.lang.String
 * @exception com.sbc.eia.bis.BusinessInterface.InvalidInterfaceException. The interface supplied is invalid.
 * @exception com.sbc.eia.bis.BusinessInterface.NotImplementedException. The interface requested is not implemented.
 * @exception com.sbc.eia.bis.BusinessInterface.SeriousFailureException. A serious failure occurred.
 */
public Business getBusinessInterface(String anInterface,
		String aHost)
	throws InvalidInterfaceException, NotImplementedException,
		SeriousFailureException
{
	selector.setHost(aHost);
	selector.setInterface(anInterface);
	selector.setClientKey(null);
	return (Business)hostFactory.getHost(selector, null);
}
/**
 * Returns an object that implements the required interface.
 *	The search for the appropriate interface object is started at the node in the host tree specified
 *	by the supplied host value.
 * @return com.sbc.eia.bis.BusinessInterface.Business
 * @param anInterface java.lang.String
 * @param aHost java.lang.String
 * @param aClientKey com.sbc.eia.bis.BusinessInterface.ClientKey
 * @exception com.sbc.eia.bis.BusinessInterface.InvalidInterfaceException. The interface supplied is invalid.
 * @exception com.sbc.eia.bis.BusinessInterface.NotImplementedException. The interface requested is not implemented.
 * @exception com.sbc.eia.bis.BusinessInterface.SeriousFailureException. A serious failure occurred.
 */
public Business getBusinessInterface(String anInterface,
		String aHost, ClientKey aClientKey)
	throws InvalidInterfaceException, NotImplementedException,
		SeriousFailureException
{
	selector.setHost(aHost);
	selector.setInterface(anInterface);
	selector.setClientKey(aClientKey);
	return (Business)hostFactory.getHost(selector, null);
}
/**
 * Returns an object that implements the required interface.
 *	The search for the appropriate interface object is started at the node in the host tree specified
 *	by the supplied host value.The supplied
 *	pass-through object is attached to the implementing object and can be accessed by that object later such
 *	as when an interface method on that object is invoked.
 * @return com.sbc.eia.bis.BusinessInterface.Business
 * @param anInterface java.lang.String
 * @param aHost java.lang.String
 * @param aPassThru java.lang.Object
 * @exception com.sbc.eia.bis.BusinessInterface.InvalidInterfaceException. The interface supplied is invalid.
 * @exception com.sbc.eia.bis.BusinessInterface.NotImplementedException. The interface requested is not implemented.
 * @exception com.sbc.eia.bis.BusinessInterface.SeriousFailureException. A serious failure occurred.
 */
public Business getBusinessInterface(String anInterface,
		String aHost, Object aPassThru)
	throws InvalidInterfaceException, NotImplementedException,
		SeriousFailureException
{
	selector.setHost(aHost);
	selector.setInterface(anInterface);
	selector.setClientKey(null);
	return (Business)hostFactory.getHost(selector, aPassThru);
}
/**
 * Returns an object that implements the required interface.
 *	The search for the appropriate interface object is started at the node in the host tree specified
 *	by the supplied host value.The supplied
 *	pass-through object is attached to the implementing object and can be accessed by that object later such
 *	as when an interface method on that object is invoked.
 * @return com.sbc.eia.bis.BusinessInterface.Business
 * @param anInterface java.lang.String
 * @param aHost java.lang.String
 * @param aPassThru java.lang.Object
 * @param aClientKey com.sbc.eia.bis.BusinessInterface.ClientKey
 * @exception com.sbc.eia.bis.BusinessInterface.InvalidInterfaceException. The interface supplied is invalid.
 * @exception com.sbc.eia.bis.BusinessInterface.NotImplementedException. The interface requested is not implemented.
 * @exception com.sbc.eia.bis.BusinessInterface.SeriousFailureException. A serious failure occurred.
 */
public Business getBusinessInterface(String anInterface,
		String aHost, Object aPassThru, ClientKey aClientKey)
	throws InvalidInterfaceException, NotImplementedException,
		SeriousFailureException
{
	selector.setHost(aHost);
	selector.setInterface(anInterface);
	selector.setClientKey(aClientKey);
	return (Business)hostFactory.getHost(selector, aPassThru);
}
/**
 * Returns the company code.
 * @return java.lang.String
 */
public java.lang.String getCode() {
	return code;
}
/**
 * Returns the company state.
 * @return com.sbc.eia.bis.BusinessInterface.State
 */
public State getState() {
	return state;
}
/**
 * Determines whether the company is an any company.
 * @return boolean
 */
public boolean isAnyCompany() {
	return code.equals(ANY_COMPANY) ? true : false;
}
}
