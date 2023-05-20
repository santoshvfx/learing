// $Id: ServiceException.java,v 1.8 2005/05/04 16:54:34 as5472 Exp $

/* Copyright Notice
 * RESTRICTED - PROPRIETARY INFORMATION
 * The information herein is for use only by authorized employees
 * of SBC Services Inc. and authorized Affiliates of SBC Services,
 * Inc., and is not for general distribution within or outside the
 * the respective companies.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2004 SBC Services, Inc.
 * All rights reserved.
 */

/** Description
 *  This file contains the ServiceException class.
 *  Description
 */
package com.sbc.eia.bis.embus.service.access;

import java.io.PrintStream;
import java.io.PrintWriter;

/** Description
 *  The ServiceException class is the base for all exceptions pertaining to
 *  service access operations.  The user should subclass this class to 
 *  create more specific service exception.  However, this class can be
 *  instantiate to indicate that a general service exception.
 *  Description
 */
public class ServiceException extends Exception
{
    private Exception m_originalException = null;

    /**
     * @see java.lang.Object#Object()
     */
    public ServiceException()
    {
        super();
    }

    /**
     * Method ServiceException.
     * @param i_serviceExceptionMessage
     * @param i_originalException
     */
    public ServiceException(
        String i_serviceExceptionMessage,
        Exception i_originalException)
    {
        super(i_serviceExceptionMessage);
        setOriginalException(i_originalException);
    }

    /**
     * This constructor allows the user to throw a service exception with a
     * user provided exception message.
     * @see java.lang.Throwable#Throwable(String)
     */
    public ServiceException(String i_serviceExceptionMessage)
    {
        super(i_serviceExceptionMessage);
    }

    /**
     * Method ServiceException.
     * @param i_originalException
     */
    public ServiceException(Exception i_originalException)
    {
        super(i_originalException.getMessage());
        setOriginalException(i_originalException);
    }

    /**
     * Method setOriginalException.
     * @param originalException
     */
    private void setOriginalException(Exception originalException)
    {
        m_originalException = originalException;
    }

    /**
     * Method getOriginalException.
     * @return Exception
     */
    public Exception getOriginalException()
    {
        return m_originalException;
    }

    /**
     * This method overrides the method defined in Throwable by design to
     * print a stacktrace that combines both the stacktrace for the 
     * service exception itself and if an original exception is set, the 
     * stacktrace for the original exception as well.  The intent is to
     * not only show where the service exception was thrown and where the
     * original exception was thrown.
     * 
     * If the original exception is the service exception itself (e.g. when
     * no original exception is set or is null), only the service exception's
     * stacktrace will be printed.
     * @see java.lang.Throwable#printStackTrace(PrintWriter)
     */
    public void printStackTrace(PrintWriter s)
    {
        if (getOriginalException() == null)
        {
            super.printStackTrace(s);
        }
        else
        {
            super.printStackTrace(s);
            if (getOriginalException() != this)
            {
                s.println("Nested exception's stack trace:");
                getOriginalException().printStackTrace(s);
            }
        }
    }

    /**
     * This method overrides the method defined in Throwable by design to
     * print a stacktrace that combines both the stacktrace for the 
     * service exception itself and if an original exception is set, the 
     * stacktrace for the original exception as well.  The intent is to
     * not only show where the service exception was thrown and where the
     * original exception was thrown.
     * 
     * If the original exception is the service exception itself (e.g. when
     * no original exception is set or is null), only the service exception's
     * stacktrace will be printed.
     * @see java.lang.Throwable#printStackTrace(PrintStream)
     */
    public void printStackTrace(PrintStream s)
    {
        if (getOriginalException() == null)
        {
            super.printStackTrace(s);
        }
        else
        {
            super.printStackTrace(s);
            if (getOriginalException() != this)
            {
                s.println("Nested exception's stack trace:");
                getOriginalException().printStackTrace(s);
            }
        }
    }

    /**
     * This method overrides the method defined in Throwable by design to
     * print a stacktrace that combines both the stacktrace for the 
     * service exception itself and if an original exception is set, the 
     * stacktrace for the original exception as well.  The intent is to
     * not only show where the service exception was thrown and where the
     * original exception was thrown.
     * 
     * If the original exception is the service exception itself (e.g. when
     * no original exception is set or is null), only the service exception's
     * stacktrace will be printed.
     * @see java.lang.Throwable#printStackTrace()
     */
    public void printStackTrace()
    {
        if (getOriginalException() == null)
        {
            super.printStackTrace();
        }
        else
        {
            super.printStackTrace();
            if (getOriginalException() != this)
            {
                System.err.println("Nested exception's stack trace:");
                getOriginalException().printStackTrace();
            }
        }
    }
}
