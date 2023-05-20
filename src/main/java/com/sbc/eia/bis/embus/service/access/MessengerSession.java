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
 *  This class serves to maintain a messenger and its relationship with other
 *  messaging related objects.
 *  Description
 */
package com.sbc.eia.bis.embus.service.access;

import java.io.IOException;
import java.util.HashMap;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;

import com.sbc.bccs.utilities.Logger;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.embus.client.Delivery;
import com.sbc.embus.client.Messenger;
import com.sbc.embus.client.Receipt;
import com.sbc.embus.client.ResponseListener;
import com.sbc.embus.common.EMBusException;

/** Description
 *  This class serves to maintain a messenger and its relationship with other
 *  messaging related objects.
 *  Description
 */
public class MessengerSession extends Messenger
{
    private Destination reusableDestination = null;
    private Logger m_logger = null;

    /**
     * Constructor for MessengerSession.
     * @param arg0
     * @param arg1
     * @throws EMBusException
     * @throws JMSException
     */
    public MessengerSession(String arg0, HashMap arg1)
        throws EMBusException, JMSException
    {
        super(arg0, arg1);
    }

    /**
     * Method MessengerSession.
     * @param arg0
     * @param arg1
     * @param logger
     * @throws EMBusException
     * @throws JMSException
     */
    public MessengerSession(String arg0, HashMap arg1, Logger logger)
        throws EMBusException, JMSException
    {
        this(arg0, arg1);
        m_logger = logger;
    }

    /**
     * @see com.sbc.embus.client.Messenger#dismiss()
     */
    public void dismiss()
    {
        try
        {
            if (reusableDestination != null)
            {
                try
                {
                    deleteTemporaryDestination(reusableDestination);
                    log(
                        LogEventId.DEBUG_LEVEL_2,
                        "Temporary destination deleted.");
                }
                catch (Exception e)
                {
                    log(
                        LogEventId.DEBUG_LEVEL_2,
                        e.getClass().getName()
                            + " occured while deleting temporary destination."
                            + "  Exception message is "
                            + e.getMessage());
                }
                finally
                {
                    reusableDestination = null;
                    log(
                        LogEventId.DEBUG_LEVEL_2,
                        "Reusable destination dereferenced in finally.");
                }
            }
        }
        catch (Exception exception)
        {
            System.out.println(
                "Unexpected exception occurred deleting temporary "
                    + "destination in dismiss.");
        }

        try
        {
            try
            {
                super.dismiss();
                log(
                    LogEventId.DEBUG_LEVEL_2,
                    "Call to super.dismiss() completed.");
            }
            catch (Exception e)
            {
                log(
                    LogEventId.DEBUG_LEVEL_2,
                    e.getClass().getName()
                        + " occured while dismissing messenger.  "
                        + "Exception message is "
                        + e.getMessage());
            }
        }
        catch (Exception e)
        {
            System.out.println(
                "Unexpected exception occurred while calling super "
                    + "class's dismiss.");
        }
    }

    /**
     * @see com.sbc.embus.client.Messenger#request(HashMap, Message)
     */
    public Delivery request(HashMap config, Message message)
        throws IOException, EMBusException, JMSException
    {
        boolean shouldReuseTempQueue = false;
        try
        {
            shouldReuseTempQueue =
                new Boolean(
                    (String) config.get("pickup.reuseTemporaryQueue"))
                    .booleanValue();
            log(
                LogEventId.INFO_LEVEL_2,
                "Reuse temporary destination indicator is "
                    + shouldReuseTempQueue
                    + ".");
        }
        catch (Exception e)
        {
            log(
                LogEventId.DEBUG_LEVEL_2,
                "Failed to retrieve reuse temporary destination indicator.");
        }

        if (shouldReuseTempQueue)
        {
            log(
                LogEventId.DEBUG_LEVEL_2,
                "Will reuse temporary destination.");
            if (reusableDestination == null)
            {
                reusableDestination = createTemporaryDestination();
                log(
                    LogEventId.INFO_LEVEL_2,
                    "Reusable temporary destination created.  Name is "
                        + reusableDestination);
            }

            config.put("pickup.destination", reusableDestination);
            log(
                LogEventId.DEBUG_LEVEL_2,
                "Reusable temporary destination set to pickup configuration.");
        }

        log(LogEventId.DEBUG_LEVEL_2, "Making request.");
        return super.request(config, message);
    }

    /**
     * Method log.
     * @param logEventId
     * @param logMsg
     */
    private void log(String logEventId, String logMsg)
    {
        if (m_logger != null)
        {
            m_logger.log(logEventId, logMsg);
        }
    }
}
