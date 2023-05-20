//$Id: INQFASGResponseHelper.java,v 1.1.2.2 2011/04/05 23:34:44 jr5306 Exp $
//############################################################################
//#
//# Copyright Notice:
//#
//#     RESTRICTED - PROPRIETARY INFORMATION
//#      The information herein is for use only by authorized employees
//#      of AT&T Services, Inc. and authorized Affiliates of AT&T Services,
//#      Inc., and is not for general distribution within or outside the
//#      respective companies.
//#      Copying or reproduction without prior written approval is prohibited.
//#
//#      Copyright © 2002-2005 AT&T Knowledge Ventures.
//#      All rights reserved.
//#
//# History :
//# Date      |  Author              | Notes
//# --------------------------------------------------------------------
//# 07/20/2007   Rene Duka             Creation.
//#	09/25/2007	 Deepti Nayar		   Modified for LS6.
//# 11/06/2007   Rene Duka             RM 410745: Project Lightspeed - Release 6.0.
//# 11/28/2007   Rene Duka             Defect 79174: INQFASGResponseHelper: parseResponse() null.

package com.sbc.eia.bis.BusinessInterface.ServiceAddress.ason;

import java.util.ArrayList;
import java.util.Hashtable;

import com.sbc.bccs.utilities.Utility;
import com.sbc.eia.bis.embus.service.facsrc.InqfasgResponse.INQFASGType.ERRRSPType;
import com.sbc.eia.bis.embus.service.facsrc.InqfasgResponse.impl.INQFASGTypeImpl;
import com.sbc.eia.bis.embus.service.facsrc.InqfasgResponse.impl.INQFASGTypeImpl.RSPTypeImpl.LOOPTypeImpl;
import com.sbc.eia.bis.framework.logging.LogEventId;


/**
 * Class      : INQFASGResponseHelper
 * Description: Helper used for handling the response from the INQFASG contract of FACSRCAccess.  
 */
public class INQFASGResponseHelper 
{
    private Utility aUtility = null;
    private Hashtable aProperties = null;

    private int aNumberOfLoops = 0;
    Loop[] aLoops = null;
    String aErrorType = null;
    String aErrorMessage = null;
    ERRRSPType aErrorResponse = null;

    /**
     * Constructor: INQFASGResponseHelper
     * 
     * @param Utility   utility
     * @param Hashtable properties
     * 
     * @author Rene Duka
     */
    public INQFASGResponseHelper(Utility utility, Hashtable properties) 
    {
        aProperties = properties;
        aUtility = utility;
    }

    /**
     * Parses the response information.
     * 
     * @param INQFASGTypeImpl aResponse
     * 
     * @author Rene Duka
     */
    public void parseResponse(INQFASGTypeImpl aResponse)
        throws 
            Exception
    {
        String aMethodName = "INQFASGResponseHelper: parseResponse()";
        aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + aMethodName);
        try {
            // see if there is an Error Response from LFACS
        	if (aResponse.getERRRSP()!= null){
        		aErrorResponse = aResponse.getERRRSP();
        		aErrorType = aResponse.getERRRSP().getETYP(); 
            	aErrorMessage = aResponse.getERRRSP().getERRMSG();
        	}
        	else
        	{
        	// set aNumberOfLoops
        		aNumberOfLoops = aResponse.getRSP().getLOOP().size();
            // set aLoops
        		ArrayList aLoopArray = new ArrayList();
	            for (int i=0; i < aNumberOfLoops; i++) 
	            {
	                LOOPTypeImpl aLOOPTypeImpl= (LOOPTypeImpl) aResponse.getRSP().getLOOP().get(i);
	                Loop aLoop = new Loop(aUtility, aProperties);
	                aLoop.parseLoop(aLOOPTypeImpl);
	                aLoopArray.add(aLoop);
	            }
	            if (aLoopArray.size() > 0) 
	            {
	                aLoops = (Loop[]) aLoopArray.toArray(new Loop[aLoopArray.size()]);
	            }
        	}
        }
        catch (Exception e) 
        {
        	aUtility.log(LogEventId.EXCEPTION, ">" + "Exception in INQFASGResponseHelper: parseResponse() " + e.getMessage());

            // log: exception message
            StringBuffer eLogMessage = new StringBuffer();
            if (aResponse.getERRRSP().getERRMSG() != null)
            {
                eLogMessage.append(aResponse.getERRRSP().getERRMSG());
            }
            else
            {
                eLogMessage.append(e.getMessage());
            }
            aUtility.log(LogEventId.ERROR, eLogMessage.toString());

            // throw: Exception
            //throw new Exception(eLogMessage.toString());
        }
        finally 
        {
            aUtility.log(LogEventId.DEBUG_LEVEL_1, "<" + aMethodName);
        }
        
    }

    /**
     * Get the number of loops.
     * 
     * @return String
     * 
     * @author Rene Duka
     */
    public int getNumberOfLoops() 
    {
        return aNumberOfLoops;
    }

    /**
     * Get the loops from LFACS.
     * 
     * @return String
     * 
     * @author Rene Duka
     */
    public Loop[] getLoops() 
    {
        return aLoops;
    }
    
    public String getErrorType()
    {
    	return aErrorType;
    }
    
    public String getErrorMessage()
    {
    	return aErrorMessage;
    }
    
    public ERRRSPType getErrorResponse()
    {
    	return aErrorResponse;
    }
}