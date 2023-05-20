package com.att.lms.bis.service.ovals;

import java.util.HashSet;
import java.util.Set;

public class OvalsException extends Exception
{
    private Set<OvalsException> exceptions = new HashSet<>();

    public OvalsException(Exception cause)
    {
        super(cause);
    }

    public OvalsException(String message, Exception cause)
    {
        super(message,cause);
    }

    public void addException(OvalsException oe)
    {
        exceptions.add(oe);
    }

    public Set<OvalsException> allExceptions()
    {
        return new HashSet<>(exceptions);
    }
}
