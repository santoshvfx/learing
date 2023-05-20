// $Id: ServiceCenter.java,v 1.2 2003/03/12 00:10:59 dm2328 Exp $

package com.sbc.eia.bis.BusinessInterface;

import com.sbc.eia.idl.exception_types.*;
/**
 * ServiceCenter is the class which defines a service center.
 * Creation date: (3/27/01 8:49:36 AM)
 * @author: Creighton Malet
 */
public class ServiceCenter {
	private State state = null;
/**
 * Class constructor.
 */
private ServiceCenter() {
	super();
}
/**
 * Class constructor accepting service center code.
 * Creation date: (3/27/01 8:50:31 AM)
 * @param aServiceCenter java.lang.String
 */
public ServiceCenter(String aServiceCenter)
	throws NullDataException, InvalidServiceCenterException
{
	if (aServiceCenter == null)
		throw new NullDataException(ExceptionCode.ERR_BSI_BUSINESS_NULL_DATA,
			"Null ServiceCenter in ServiceCenter::ServiceCenter()");

	try {
		state = new State(aServiceCenter);
	}
	catch (InvalidStateException e)
	{
		throw new InvalidServiceCenterException(ExceptionCode.ERR_BSI_BUSINESS_INVALID_SERVICE_CENTER,
			"Invalid ServiceCenter: " +  aServiceCenter);	
	}
}
/**
 * Returns the code.
 * Creation date: (3/27/01 9:05:26 AM)
 * @return java.lang.String
 */
public java.lang.String getCode() {
	return state.getCode();
}
/**
 * Returns the state.
 * Creation date: (3/27/01 9:15:54 AM)
 * @return com.sbc.eia.bis.BusinessInterface.State
 */
public State getState() {
	return state;
}
}
