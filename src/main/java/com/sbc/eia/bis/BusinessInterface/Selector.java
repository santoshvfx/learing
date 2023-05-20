// $Id: Selector.java,v 1.2 2003/03/12 00:10:58 dm2328 Exp $

package com.sbc.eia.bis.BusinessInterface;

/**
 * Selector is the class which defines a selector used to define a company/state/interface combination.
 * Creation date: (12/27/00 1:23:01 PM)
 * @author: Creighton Malet
 */
public class Selector {
	private java.lang.String Interface = null;
	private Company company = null;
	private String host = null;
	private java.lang.String key = null;
	private ClientKey clientKey = null;
/**
 * Class constructor.
 */
public Selector() {}
/**
 * Class constructor accepting company, interface and host.
 */
public Selector(Company aCompany, String anInterface,
	String aHost)
{
	company = aCompany;
	Interface = anInterface;
	host = aHost;
	reKey();
}
/**
 * Class constructor accepting company, interface and host.
 */
public Selector(Company aCompany, String anInterface,
	String aHost, ClientKey aClientKey)
{
	company = aCompany;
	Interface = anInterface;
	clientKey = aClientKey;
	host = aHost;
	reKey();
}
/**
 * Returns the client key.
 * Creation date: (12/27/00 1:31:53 PM)
 * @return java.lang.String
 */
public ClientKey getClientKey() {
	return clientKey;
}
/**
 * Returns the company.
 * Creation date: (12/27/00 4:36:28 PM)
 * @return com.sbc.eia.bis.BusinessInterface.Company
 */
public Company getCompany() {
	return company;
}
/**
 * Returns the host.
 * Creation date: (1/17/01 11:44:22 AM)
 * @return java.lang.String
 */
public java.lang.String getHost() {
	return host;
}
/**
 * Returns the interface.
 * Creation date: (12/27/00 1:31:53 PM)
 * @return java.lang.String
 */
public java.lang.String getInterface() {
	return Interface;
}
/**
 * Returns the key for the selector.
 * Creation date: (1/17/01 2:25:22 PM)
 * @return java.lang.String
 */
public String getKey() {
	return key;
}
/**
 * Recomputes the key for the selector.
 * Creation date: (1/17/01 2:28:53 PM)
 */
private void reKey() {
	key = new String(
		((company == null) ? "null" : company.getCode()) + "/" +
		((company == null || company.getState() == null || company.getState().getCode() == null) ? "null" : company.getState().getCode()) + "/" +
		((Interface == null) ? "null" : Interface) + "/" +
		((host == null) ? "null" : host) + "/" +
		((clientKey == null || clientKey.toString() == null) ? "" : clientKey.toString()));
}
/**
 * Sets the client key.
 * Creation date: (12/27/00 1:31:53 PM)
 * @param newInterface java.lang.String
 */
public void setClientKey(ClientKey newClientKey) {
	clientKey = newClientKey;
	reKey();
}
/**
 * Sets the company
 * Creation date: (12/27/00 4:36:28 PM)
 * @param newCompany com.sbc.eia.bis.BusinessInterface.Company
 */
public void setCompany(Company newCompany) {
	company = newCompany;
	reKey();
}
/**
 * Sets the host.
 * Creation date: (1/17/01 11:44:22 AM)
 * @param newHost java.lang.String
 */
public void setHost(java.lang.String newHost) {
	host = newHost;
	reKey();
}
/**
 * Sets the interface.
 * Creation date: (12/27/00 1:31:53 PM)
 * @param newInterface java.lang.String
 */
public void setInterface(java.lang.String newInterface) {
	Interface = newInterface;
	reKey();
}
}
