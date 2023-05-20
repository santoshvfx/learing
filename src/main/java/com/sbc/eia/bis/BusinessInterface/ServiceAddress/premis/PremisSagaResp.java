// $Id: PremisSagaResp.java,v 1.2 2007/10/06 01:04:24 gg2712 Exp $

package com.sbc.eia.bis.BusinessInterface.ServiceAddress.premis;

import java.util.ArrayList;
import java.util.List;

import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.bis.lim.transactions.common.RetrieveLocResp;
import com.sbc.eia.bis.lim.util.LIMBase;
import com.sbc.eia.idl.bis_types.SystemFailure;
import com.sbc.gwsvcs.service.premisserver.interfaces.PremisSagaMenuResp_t;
import com.sbc.gwsvcs.service.premisserver.interfaces.PremisZipMenuResp_t;
import com.sbc.gwsvcs.service.premisserver.interfaces.SagaMenuProcStatus_t;
import com.sbc.gwsvcs.service.premisserver.interfaces.ZipMenuProcStatus_t;

/**
 * Stores the list of sagas from a PREMISServer saga or zip menu.
 * Creation date: (3/26/01 4:33:27 PM)
 * @author: David Prager
 */
public class PremisSagaResp extends RetrieveLocResp
{
	protected List sagaList = new ArrayList();

    /**
     * Construct a PremisSagaResp from a list.
     * @param utility LIMBase
     * @param aSagaList List
     */
    public PremisSagaResp(LIMBase utility, List aSagaList)
    {
    	super(utility);
    	
        setSagaList(aSagaList); 
     	getUtility().log(LogEventId.DEBUG_LEVEL_1,
    			"PremisSagaResp: SAGAs from ZipToSagaTbl " + sagaList.toString());
    }
    
    /**
     * Construct a PremisSagaResp from a PremisSagaMenuResp_t.
     * @param utility LIMBase
     * @param result PremisSagaMenuResp_t
     * @exception SystemFailure
     */
    public PremisSagaResp(LIMBase utility, PremisSagaMenuResp_t result)
    throws SystemFailure
    {
    	super(utility);
    
    	try
    	{
    		for (int i = 0; i < result.SagaMenuPktResp.SagaMenuProcStatus.length; i++)
    		{
    			SagaMenuProcStatus_t menu = result.SagaMenuPktResp.SagaMenuProcStatus[i];
    	
    			for (int j = 0; j < menu.SagaMenu.length; j++)
    			{
    				sagaList.add(menu.SagaMenu[j].SAGA);
    			}
    		}
    	}
    	finally
    	{
    		getUtility().log(LogEventId.DEBUG_LEVEL_1,
    			"PremisSagaResp: SAGAs from Premis " + sagaList.toString());
    	}
    }
    /**
     * Construct a PremisSagaResp from a PremisZipMenuResp_t.
     * Creation date: (3/26/01 5:01:00 PM)
     * @param utility LIMBase
     * @param result PremisZipMenuResp_t
     * @exception SystemFailure
     */
    public PremisSagaResp(LIMBase utility, PremisZipMenuResp_t result)
    	throws SystemFailure
    {
    	super(utility);
    
    	try
    	{
    		for (int i = 0; i < result.ZipMenuPktResp.ZipMenuProcStatus.length; i++)
    		{
    			ZipMenuProcStatus_t menu = result.ZipMenuPktResp.ZipMenuProcStatus[i];
    	
    			for (int j = 0; j < menu.ZipMenu.length; j++)
    			{
    				sagaList.add(menu.ZipMenu[j].SAGA);
    			}
    		}
    	}
    	finally
    	{
    		getUtility().log(LogEventId.DEBUG_LEVEL_1,
    			"PremisSagaResp: SAGAs from Premis " + sagaList.toString());
    	}
    }
    /**
     * Compares two objects for equality. Returns a boolean that indicates
     * whether this object is equivalent to the specified object. This method
     * is used when an object is stored in a hashtable.
     * @param obj the Object to compare with
     * @return true if these Objects are equal; false otherwise.
     * @see java.util.Hashtable
     */
    public boolean equals(Object obj)
    {
    	if (obj instanceof PremisSagaResp)
    	{
    		return sagaList.equals(((PremisSagaResp) obj).sagaList);
    	}
    	return false;
    }
    /**
     * Getter method for the internal List of sagas.
     * Creation date: (3/26/01 5:03:09 PM)
     * @return List
     */
    public List getSagaList()
    {
    	return sagaList;
    }
    /**
     * Generates a hash code for the receiver.
     * This method is supported primarily for
     * hash tables, such as those provided in java.util.
     * @return an integer hash code for the receiver
     * @see java.util.Hashtable
     */
    public int hashCode()
    {
    	return sagaList.hashCode();
    }
	/**
	 * Sets the sagaList.
	 * @param sagaList The sagaList to set
	 */
	public void setSagaList(List sagaList) {
		this.sagaList = sagaList;
	}

}
