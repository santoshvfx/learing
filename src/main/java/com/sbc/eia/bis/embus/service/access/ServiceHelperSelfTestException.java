package com.sbc.eia.bis.embus.service.access;

/** Description
 *  The ServiceHelperSelftestException should be used to indicate exceptionx
 *  encountered while trying to run the ServideHelper.selfTest method.
 *  Description
 */
public class ServiceHelperSelfTestException extends ServiceException
{

    /**
     * Constructor for EncoderException.
     * @param i_serviceExceptionMessage String 
     * @param i_originalException Exception
     */
    public ServiceHelperSelfTestException( String i_serviceExceptionMessage,
        Exception i_originalException)
    {
        super( i_serviceExceptionMessage, i_originalException);
    }

    /**
     * Constructor for EncoderException.
     * @param i_originalException Exception
     */
    public ServiceHelperSelfTestException(Exception i_originalException)
    {
        super(i_originalException);
    }

    /**
     * Constructor for EncoderException.
     * @param i_serviceExceptionMessage String
     */
    public ServiceHelperSelfTestException( String i_serviceExceptionMessage )
    {
        super(i_serviceExceptionMessage);
    }

}
