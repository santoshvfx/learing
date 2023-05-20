// $Id: LimHome.java,v 1.2 2003/03/21 19:24:35 dm2328 Exp $

package com.sbc.eia.bis.facades.lim.ejb;

/**
 * This is a Home interface for the Session Bean.
 */
public interface LimHome extends javax.ejb.EJBHome {

/**
 * create method for a session bean.
 * @return com.sbc.eia.bis.facades.lim.ejb.Lim
 * @exception javax.ejb.CreateException The exception description.
 * @exception java.rmi.RemoteException The exception description.
 */
com.sbc.eia.bis.facades.lim.ejb.Lim create() throws javax.ejb.CreateException, java.rmi.RemoteException;
}
