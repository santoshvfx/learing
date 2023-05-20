// $Id: HOSTTemplate.java,v 1.2 2003/03/12 00:10:58 dm2328 Exp $

package com.sbc.eia.bis.BusinessInterface;

import com.sbc.eia.bis.BusinessInterface.*;
import com.sbc.eia.bis.framework.logging.LogEventId;

/**
 * HOSTTemplate is the class which defines a template for a host object.
 * Creation date: (10/19/00 3:02:10 PM)
 * @author: Creighton Malet
 */
public class HOSTTemplate extends Host 
//	implements CircuitI, AnotherInterfaceI
{
	static final String CircuitInterfaceName = "CircuitI"; // Normally defined in the CircuitI interface

	// The list of supported interfaces
	private final static java.lang.String[] interfaceList =
		new String[] {
			CircuitInterfaceName /*, AnotherInterface */ 
		};
	// This belongs here - the list of immediate children (classes that derive from this class)
	private static final String hostList[] =
		new String[] {
			// Use this normally: HOSTTemplateChild1.class.getName()
			"com.sbc.eia.bis.BusinessInterface.HOSTTemplateChild1"
		};
/**
 * Class constructor accepting company, utility and properties.
 * Creation date: (12/19/00 11:29:49 AM)
 * @param aCompany com.sbc.eia.bis.BusinessInterface.Company
 * @param aUtility com.sbc.bccs.utilities.Utility aUtility
 * @param aProperties java.util.Hashtable
 */
public HOSTTemplate(Company aCompany, com.sbc.bccs.utilities.Utility aUtility, java.util.Hashtable aProperties)
{
	super(aCompany, aUtility, aProperties);
}
/**
 * Creates a circuit
 * Creation date: (12/18/00 12:09:21 PM)
 * @param aCircuit com.sbc.....
 * @exception com.sbc.eia.bis.BusinessInterface.BusinessException: A business exception...
 */
public void createCircuit(/*Circuit aCircuit*/)
	throws BusinessException
{
	utility.log(LogEventId.DEBUG_LEVEL_2, "HOSTTemplate::createCircuit()");

	// Create a circuit, say with a call to a legacy host using data from the input Circuit object
}
/**
 * Called by the Host Factory to get entries for preloading into the Host cache.
 * 	This method must be overridden.
 * @return com.sbc.eia.bis.BusinessInterface.Selector[]
 * @param aUtility com.sbc.bccs.utilities.Utility
 * @exception com.sbc.eia.bis.BusinessInterface.NullDataException: A required input parameter was null.
 * @exception com.sbc.eia.bis.BusinessInterface.InvalidCompanyException: An attempt was made to create an invalid company.
 * @exception com.sbc.eia.bis.BusinessInterface.InvalidStateException: An attempt was made to create an invalid state.
 */
public static Selector[] getCacheEntries(com.sbc.bccs.utilities.Utility aUtility)
	throws NullDataException, InvalidCompanyException, InvalidStateException
{
	aUtility.log(LogEventId.DEBUG_LEVEL_2, "HOSTTemplate::getCacheEntries()");

	// Add entries to the HostFactory cache at start time to avoid long searches
	return new Selector[] { 
		new Selector(new Company(Company.Company_PacificBell, new State(State.State_California), null, null),
			CircuitInterfaceName, HOSTTemplate.class.getName()),
		new Selector(new Company(Company.Company_PacificBell, new State(State.State_Missouri), null, null),
			CircuitInterfaceName, HOSTTemplate.class.getName())
	};
}
/**
 * Called by the Host Factory to obtain a list of hosts that are the immediate
 * 	children of the called class.
 *	This method must be overridden.
 * @return java.lang.String[]
 * @param aUtility com.sbc.bccs.utilities.Utility
 */
public static String[] getHostList(com.sbc.bccs.utilities.Utility aUtility)
{
	aUtility.log(LogEventId.DEBUG_LEVEL_2, "HOSTTemplate::getHostList()");

	// Return the list of immediate children (classes that derive from this class)
	return hostList; /* null if no children */
}
/**
 * Called by the Host Factory to obtain a list of the interfaces 
 *	that are the supported by the called class.
 * 	This method must be overridden.
 * @return java.lang.String[]
 * @param aUtility com.sbc.bccs.utilities.Utility
 */
public static String[] getInterfaceList(com.sbc.bccs.utilities.Utility aUtility)
{
	aUtility.log(LogEventId.DEBUG_LEVEL_2, "HOSTTemplate::getInterfaceList()");

	// Return the list of interfaces supported by this host
	return interfaceList;
}
/**
 * Called by the Host Factory to obtain a list of the companies 
 *	that are the supported by the called class.
 * 	This method must be overridden.
 * @return com.sbc.eia.bis.BusinessInterface.Company[]
 * @param aUtility com.sbc.bccs.utilities.Utility
 * @exception com.sbc.eia.bis.BusinessInterface.NullDataException: A required input parameter was null.
 * @exception com.sbc.eia.bis.BusinessInterface.InvalidCompanyException: An attempt was made to create an invalid company.
 * @exception com.sbc.eia.bis.BusinessInterface.InvalidStateException: An attempt was made to create an invalid state.
 */
public static Company[] getSupportedCompanies(com.sbc.bccs.utilities.Utility aUtility)
	throws InvalidCompanyException, InvalidStateException, NullDataException
{
	aUtility.log(LogEventId.DEBUG_LEVEL_2, "HOSTTemplate::getSupportedCompanies()");

	// Return the company/state combination supported by this host
	return new Company[] { new Company(Company.Company_PacificBell, State.getAnAnyState(), null, null) };
}
/**
 * Retrieves the circuit.
 * Creation date: (4/21/00 11:26:52 AM)
 * @return com.sbc...Circuit
 * @param aParameter java.lang.String
 * @param anotherParameter java.lang.String
 * @exception com.sbc.eia.bis.BusinessInterface.BusinessException: A business exception...
 */
public /*Circuit*/ void retrieveCircuit(String aParameter, String anotherParameter)
	throws BusinessException
{
	utility.log(LogEventId.DEBUG_LEVEL_2, "HOSTTemplate::retrieveCircuit()");
	
	// Retrieve a Circuit object, say with a call to a legacy host
	return/*(Circuit)*/;
}
}
