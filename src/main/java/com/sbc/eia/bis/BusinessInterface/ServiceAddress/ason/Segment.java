//$Id: Segment.java,v 1.1.2.2 2011/04/05 23:35:22 jr5306 Exp $
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
//# Date       | Author              | Notes
//# --------------------------------------------------------------------
//# 10/17/2007   Rene Duka             Creation for LS6.
//# 11/06/2007   Rene Duka             RM 410745: Project Lightspeed - Release 6.0.
//# 08/18/2008   Vickie Ng		       LS 9.
//# 02/11/2009   Julius Sembrano       LS 10. CR 23216.

package com.sbc.eia.bis.BusinessInterface.ServiceAddress.ason;

import java.util.Hashtable;

import com.sbc.bccs.utilities.Utility;
import com.sbc.eia.bis.embus.service.facsrc.InqfasgResponse.impl.SEGTypeImpl;
import com.sbc.eia.bis.framework.logging.LogEventId;

/**
 * Class      : Segment
 * Description: Helper used for handling the Segment object.  
 */
public class Segment 
{
    private Utility aUtility = null;
    private Hashtable aProperties = null;

    private String aTEA = null;
    private String aTP = null;
    private String aMODEL = null;
    private String aCOND = null;
    private String aCOMM = null;
    private String aLTS = null;
    private String aLT = null;
    private String aSYSTP = null;
    
    /**
     * Constructor: Segment
     * 
     * @param Utility   utility
     * @param Hashtable properties
     * 
     * @author Rene Duka
     */
    public Segment(Utility utility, Hashtable properties) 
    {
        aProperties = properties;
        aUtility = utility;
    }

    /**
     * Parses the segment information from the loop.
     * 
     * @param SEGTypeImpl aSEGTypeImpl
     * 
     * @author Rene Duka
     */
    public void parseSegment(SEGTypeImpl aSEGTypeImpl) 
    {
        try 
        {
            // set Terminal
            if (aSEGTypeImpl.getTEA() != null)
                aTEA = aSEGTypeImpl.getTEA();
            // set Terminal Type
            if (aSEGTypeImpl.getTP() != null)
                aTP = aSEGTypeImpl.getTP();
            // set Terminal Model
            if (aSEGTypeImpl.getMODEL() != null)
                aMODEL = aSEGTypeImpl.getMODEL();
            // set Conditioning Code
            if (aSEGTypeImpl.getCOND() != null)
                aCOND = aSEGTypeImpl.getCOND();
            // set Commit Status
            if (aSEGTypeImpl.getCOMM() != null)
                aCOMM = aSEGTypeImpl.getCOMM();
            // set Line Terminal Status
            if (aSEGTypeImpl.getLTS() != null)
                aLTS = aSEGTypeImpl.getLTS();
            //  set Load Term
            if (aSEGTypeImpl.getLT() != null)
                aLT = aSEGTypeImpl.getLT();
            //  set SYSTP
            if (aSEGTypeImpl.getSYSTP() != null)
            	aSYSTP = aSEGTypeImpl.getSYSTP();
        } 
        catch (NullPointerException npe) 
        {
            aUtility.log(LogEventId.EXCEPTION, ">" + "Exception in Segment: parseSegment() " + npe.getMessage());
        } 
        catch (Exception e) 
        {
            aUtility.log(LogEventId.EXCEPTION, ">" + "Exception in Segment: parseSegment() " + e.getMessage());
        } 
    }

    /**
     * Get TEA.
     * 
     * @return String
     * 
     * @author Rene Duka
     */
    public String getTEA() 
    {
        return aTEA;
    }

    /**
     * Get TP.
     * 
     * @return String
     * 
     * @author Rene Duka
     */
    public String getTP() 
    {
        return aTP;
    }
    
    /**
     * Get MODEL.
     * 
     * @return String
     * 
     * @author Rene Duka
     */
    public String getMODEL() 
    {
        return aMODEL;
    }

    /**
     * Get COND.
     * 
     * @return String
     * 
     * @author Rene Duka
     */
    public String getCOND() 
    {
        return aCOND;
    }

    /**
     * Get COMM.
     * 
     * @return String
     * 
     * @author Rene Duka
     */
    public String getCOMM() 
    {
        return aCOMM;
    }

    /**
     * Get LTS.
     * 
     * @return String
     * 
     * @author Rene Duka
     */
    public String getLTS() 
    {
        return aLTS;
    }
    
    /**
     * Get LT - Load Term
     * 
     * @return String
     * 
     * @author Vickie Ng
     */
	public String getLT() 
	{
		return aLT;
	}

    /**
     * Get SYSTP
     * 
     * @return String
     * 
     * @author Julius Sembrano
     */
	public String getSYSTP() 
	{
		return aSYSTP;
	}
}
