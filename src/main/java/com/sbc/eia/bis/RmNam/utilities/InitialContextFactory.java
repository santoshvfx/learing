// $Id: InitialContextFactory.java,v 1.3 2006/12/13 16:25:05 pd3249 Exp $

package com.sbc.eia.bis.RmNam.utilities;

import java.util.Properties;

import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * Factory for creating an initial context.
 * Creation date: (5/22/00 10:08:32 AM)
 * @author: Administrator
 */
public class InitialContextFactory {
	public String aProviderURL = null;

	/**
	 * @deprecated This variable is deprecated and will no longer be available through this class.  Please use other means of getting this value as soon as possible.
	 */
	public static final String DEFAULT_PROTOCOL = "IIOP";
	/**
	 * @deprecated This variable is deprecated and will no longer be available through this class.  Please use other means of getting this value as soon as possible.
	 */
	public static final String DEFAULT_HOST = "localhost";

	private InitialContext ctx;

	/**
	 * InitialContextFactory constructor.
	 */
	public InitialContextFactory() {
		try {
			ctx = getInitialContextInternal();
			
		} catch (NamingException e) {
			//dont do anything here
		}
	}
	/**
	 * InitialContextFactory constructor.
	 * @param newProviderURL
	 */
	public InitialContextFactory(String newProviderURL) {
		this.aProviderURL = newProviderURL;

		try {
			ctx = getInitialContextInternal();
		} catch (NamingException e) {
			//dont do anything here
		}
	}
	/**
	 * InitialContextFactory constructor.
	 * @param newProviderURL
	 * @param user
	 * @param password
	 */
	public InitialContextFactory(
		String newProviderURL,
		String user,
		String password) {
		this.aProviderURL = newProviderURL;
		try {
			ctx = getInitialContextInternal();
		} catch (NamingException e) {
			//dont do anything here
		}
	}
	/**
	 * InitialContextFactory constructor.
	 * @param newProviderURL The initial context URL.
	 * @param p
	 */
	public InitialContextFactory(String newProviderURL, Properties p) {
		this.aProviderURL = newProviderURL;

		try {
			ctx = getInitialContextInternal(p);
		} catch (NamingException e) {
			//dont do anything here
		}
	}
	/**
	 * Gets initial context from factory state information.
	 * @return InitialContext
	 * @throws InitialContextFactoryException
	 */
	public InitialContext getInitialContext()
		throws InitialContextFactoryException {
		InitialContext retVal = null;

		if (this.ctx == null) {
			try {
				this.ctx = this.getInitialContextInternal();
				retVal = ctx;
			} catch (NamingException e) {
				throw new InitialContextFactoryException(e.getMessage());
			}
		} else {
			retVal = this.ctx;
		}
		return retVal;
	}
	
	/**
	 * Gets initial context using passed in properties, allows user
	 * to pass in custom properties.
	 * @param p
	 * @return InitialContext
	 * @throws InitialContextFactoryException
	 */
	public InitialContext getInitialContext(Properties p)
		throws InitialContextFactoryException {
		InitialContext retVal = null;

		if (this.ctx == null) {
			try {
				this.ctx = this.getInitialContextInternal(p);
				retVal = ctx;
			} catch (NamingException e) {
				throw new InitialContextFactoryException(e.getMessage());
			}
		} else {
			retVal = this.ctx;
		}
		return retVal;
	}
	/**
	 * Gets initial context using current factory settings.
	 * @return InitialContext
	 * @throws NamingException
	 */
	private InitialContext getInitialContextInternal() throws NamingException {
		InitialContext retVal = null;
		Properties p = new Properties();

		if (this.ctx == null) {
			
			//if the provider URL is defined in the property file, then use that, otherwise don't pass it in
			if (this.aProviderURL != null) {
				
				System.out.println("Using provider URL of: " + this.aProviderURL);
				p.put(javax.naming.Context.PROVIDER_URL, this.aProviderURL);
				
				try {
					retVal = new InitialContext(p);
				
				} catch (NamingException e) {
					throw new NamingException(
						"Error Retrieving InitialContext:" + e.getMessage());
				}

			}
			
			else {
				
				try {
					retVal = new InitialContext();
				} catch (NamingException e) {
						throw new NamingException(
						"Error Retrieving InitialContext:" + e.getMessage());
				}
			}
			
			
			
		} 
		
		else {
			retVal = this.ctx;
		}
		return retVal;
	}
	/**
	 * Gets initial context using passed in properties.  However, the provider url used is from
	 * the factory and not the properties.
	 * @param p
	 * @return InitialContext
	 * @throws NamingException
	 */
	private InitialContext getInitialContextInternal(Properties p)
		throws NamingException {
		InitialContext retVal = null;

		if (this.ctx == null) {
			
			//if the provider URL is defined in the property file, then use that, otherwise don't pass it in
			  if (this.aProviderURL != null) {
				
				  System.out.println("Using provider URL of: " + this.aProviderURL);
				  p.put(javax.naming.Context.PROVIDER_URL, this.aProviderURL);
				
				  try {
					  retVal = new InitialContext(p);
				
				  } catch (NamingException e) {
					  throw new NamingException(
						  "Error Retrieving InitialContext:" + e.getMessage());
				  }

			  }
			
			  else {
				
				  try {
					  retVal = new InitialContext();
				  } catch (NamingException e) {
						  throw new NamingException(
						  "Error Retrieving InitialContext:" + e.getMessage());
				  }
			  }
			  
			  
		} 
		
		else {
			retVal = this.ctx;
		}
		
		return retVal;
	}
}
