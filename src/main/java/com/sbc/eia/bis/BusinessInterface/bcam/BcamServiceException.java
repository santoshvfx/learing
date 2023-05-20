//$Id: BcamServiceException.java,v 1.4 2008/02/28 18:05:01 gg2712 Exp $
package com.sbc.eia.bis.BusinessInterface.bcam;

import com.sbc.billing.biba.exception.DataNotFound;
import com.sbc.billing.biba.exception.InvalidData;

/**
 * @author gg2712
 */
public class BcamServiceException extends Exception
{
    private Throwable aOrigException = null;
    
    /**
     * The constructor saves the original exception from BCAM.
     * @param msg
     * @param origException
     */
    public BcamServiceException(String msg, Exception origException)
    {
        super(msg);
        aOrigException = origException;
    }
    
    /**
     * Check if a DataNotFound exception from BCAM
     * @return 
     */
    protected boolean isDataNotFound()
    {
        return (aOrigException instanceof DataNotFound);
    }
    
    /**
     * Check if a InvalidDataNotFound exception from BCAM
     * @return
     */
    protected boolean isInvalidData()
    {
        return (aOrigException instanceof InvalidData);
    }
}
