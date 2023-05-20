// $Id: HostCacheObject.java,v 1.2 2003/03/12 00:10:58 dm2328 Exp $

package com.sbc.eia.bis.BusinessInterface;

/**
 * HostCacheObject is the class which defines the host cache objects.
 * Creation date: (1/22/01 2:21:46 PM)
 * @author: Creighton Malet
 */
public class HostCacheObject {
	private Host host = null;
	private java.lang.String hostClassName = null;
/**
 * Class constructor.
 */
public HostCacheObject() {
}
/**
 * Class constructor accepting host and class name of host.
 * Creation date: (1/22/01 2:25:41 PM)
 * @param aHost  com.sbc.eia.bis.BusinessInterface.Host
 * @param aHostClassName java.lang.String
 */
public HostCacheObject(Host aHost, String aHostClassName)
{
	host = aHost;
	hostClassName = aHostClassName;
}
/**
 * Returns the class name.
 * Creation date: (1/22/01 2:28:34 PM)
 * @return java.lang.String
 */
public java.lang.String getClassName() {
	return hostClassName;
}
/**
 * Returns the Host.
 * Creation date: (1/22/01 2:28:34 PM)
 * @return com.sbc.eia.bis.BusinessInterface.Host
 */
public Host getHost() {
	return host;
}
/**
 * Sets the class name.
 * Creation date: (1/22/01 2:28:34 PM)
 * @param newClassName java.lang.String
 */
public void setClassName(java.lang.String newHostClassName) {
	hostClassName = newHostClassName;
}
/**
 * Sets the Host.
 * Creation date: (1/22/01 2:28:34 PM)
 * @param newHost com.sbc.eia.bis.BusinessInterface.Host
 */
public void setHost(Host newHost) {
	host = newHost;
}
}
