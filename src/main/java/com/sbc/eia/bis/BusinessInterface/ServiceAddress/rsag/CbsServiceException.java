//$Id: CbsServiceException.java,v 1.2 2007/10/22 19:17:14 gg2712 Exp $
/*
 * Created on Oct 13, 2007
 */
package com.sbc.eia.bis.BusinessInterface.ServiceAddress.rsag;

/**
 * @author GG2712
 */
public class CbsServiceException extends Exception
{
    Exception aOriginalException = null;
    
    public CbsServiceException()
    {
        super();
    }
    public CbsServiceException(String message)
    {
        super(message);
    }
    public CbsServiceException(String message, Exception origException)
    {
        super(message);
        aOriginalException = origException;
    }    
    public Exception getOriginalException()
    {
        return aOriginalException;
    }
}
