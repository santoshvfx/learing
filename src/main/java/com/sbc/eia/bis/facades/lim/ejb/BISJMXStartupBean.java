package com.sbc.eia.bis.facades.lim.ejb;

import com.att.eia.bis.framework.mbean.JMXStartupBean;

/**
 * Bean implementation class for Enterprise Bean: BISJMXStartup
 */
public class BISJMXStartupBean implements javax.ejb.SessionBean {
	private javax.ejb.SessionContext mySessionCtx;
	/**
	 * getSessionContext
	 */
	public javax.ejb.SessionContext getSessionContext() {
		return mySessionCtx;
	}
	/**
	 * setSessionContext
	 */
	public void setSessionContext(javax.ejb.SessionContext ctx) {
		mySessionCtx = ctx;
	}
	/**
	 * ejbCreate
	 */
	public void ejbCreate() throws javax.ejb.CreateException {
	}
	/**
	 * ejbActivate
	 */
	public void ejbActivate() {
	}
	/**
	 * ejbPassivate
	 */
	public void ejbPassivate() {
	}
	/**
	 * ejbRemove
	 */
	public void ejbRemove() {
	}
	
	public boolean start (){
		return JMXStartupBean.start ();
	}
	
	public void stop () {
		JMXStartupBean.stop ();
	}
}
