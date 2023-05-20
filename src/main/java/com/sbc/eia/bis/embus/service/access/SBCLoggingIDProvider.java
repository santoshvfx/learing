// $Id:$

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
 *  Appropriate description of what this file (or classes within) is used for.
 *  Description
 */
package com.sbc.eia.bis.embus.service.access;

import com.sbc.logging.LogAssistant;
import com.sbc.logging.LogAssistantFactory;

/** Description
 *  Appropriate description of what this class is used for.
 *  Description
 */
public class SBCLoggingIDProvider implements CorrelationIDProviderI
{
    String m_bisName = null;
    String m_bisVersion = null;
    LogAssistant logAssist = null;
    /**
     * Constructor for SBCLoggingIDProvider.
     */
    public SBCLoggingIDProvider( String i_bisName, String i_bisVersion )
    {
        super();
        m_bisName = i_bisName;
        m_bisVersion = i_bisVersion;
        logAssist = LogAssistantFactory.create( m_bisName, m_bisVersion );
    }

    /**
     * @see com.sbc.eia.bis.embus.service.access.CorrelationIDProviderI#getCorrelationId()
     */
    public String getCorrelationId()
    {
        logAssist.genNewCorrID();
        return logAssist.getCorrID();
    }

    public static void main(String[] args)
    {
        CorrelationIDProviderI provider = new SBCLoggingIDProvider("MYAPP1.0", "1.0.1");
        System.out.println(provider.getCorrelationId());
        synchronized(provider) {
            try
            {
                provider.wait(5000);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            
            provider.notify();
        }
        System.out.println(provider.getCorrelationId());
    }
}

