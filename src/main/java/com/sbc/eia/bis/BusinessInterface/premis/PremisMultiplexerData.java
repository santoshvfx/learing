// $Id: PremisMultiplexerData.java,v 1.3 2004/04/21 12:53:54 db4252 Exp $

package com.sbc.eia.bis.BusinessInterface.premis;

import com.sbc.gwsvcs.access.vicuna.EventResultPair;
import com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException;
import com.sbc.gwsvcs.access.vicuna.exceptions.ServiceTimeoutException;
import com.sbc.gwsvcs.access.vicuna.multiplexer.VicunaWrapperMultiplexerData;
// $Id: PremisMultiplexerData.java,v 1.3 2004/04/21 12:53:54 db4252 Exp $

/**
 * This class is used to store the results of PREMIS access
 * using the VicunaWrapperMultiplexer process.
 * Creation date: (1/28/03 6:24:18 AM)
 * @author: David Brawley
 */
public class PremisMultiplexerData  implements VicunaWrapperMultiplexerData
{
    public PremisRetrieveLocReq dataReq = null;
    public EventResultPair dataResp = null;
    public ServiceException serviceException = null;
    public ServiceTimeoutException serviceTimeoutException = null;
	public String transactionId = null;

    /**
     * Default Constructer for a PremisMultiplexerData object.
     * Creation date: (1/28/03 6:30:49 AM)
     */
    private PremisMultiplexerData() {}
    
    /**
     * Construct a PremisMultiplexerData object.
     * @param transId String
     * @param aDataReq PremisRetrieveLocReq
     */
    public PremisMultiplexerData(String transId, PremisRetrieveLocReq aDataReq)
    {
        dataReq = aDataReq;
        transactionId = transId;
    }
    
    /**
     * Setter method for dataResp.
     * @param aDataResp EventResultPair
     * @see #getDataResp
     */
    public void setDataResp(EventResultPair aDataResp)
    {
    	dataResp = aDataResp;
    }
    /**
     * getter method for dataResp.
     * @return EventResultPair
     * @see #setDataResp
     */
    public EventResultPair getDataResp() {
    	return dataResp;
    }

}
