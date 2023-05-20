//$Id: LimClientException.java,v 1.2 2008/02/29 23:27:20 jd3462 Exp $
package com.sbc.eia.bis.lim.client;

/**
 * @author gg2712
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class LimClientException extends Exception
{
	Exception m_origException = null;
	
	public LimClientException()
	{
		super("LimClientException encountered!");
	}

	public LimClientException(String s)
	{
		super(s);
	}
	
	public LimClientException(Exception e, String msg)
	{
		super(msg);
		m_origException = e;			
	}
	
	/**
	 * Returns the m_origException.
	 * @return Exception
	 */
	public Exception getOrigException() 
	{
		return m_origException;
	}

}
