package com.att.voltageenabler.voltage;

public class VoltageException extends Exception {
    private int code;

    public VoltageException()
    {
        super();
        this.code = 0;
    }

    public VoltageException(int code, String msg)
    {
        super(msg);
        this.code = code;
    }

    public int getErrorCode()
    {
        return code;
    }

    public String getErrorDescription()
    {
        return getMessage();
    }
}
