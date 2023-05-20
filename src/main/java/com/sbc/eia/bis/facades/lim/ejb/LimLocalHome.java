//$Id: LimLocalHome.java,v 1.1 2008/02/15 00:23:53 jd3462 Exp $
package com.sbc.eia.bis.facades.lim.ejb;
/**
 * Local Home interface for Enterprise Bean: Lim
 */
public interface LimLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates a default instance of Session Bean: Lim
	 */
	public com.sbc.eia.bis.facades.lim.ejb.LimLocal create()
		throws javax.ejb.CreateException;
}
