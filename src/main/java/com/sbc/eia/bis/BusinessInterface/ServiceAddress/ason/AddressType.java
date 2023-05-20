//$Id: AddressType.java,v 1.1.2.2 2011/04/05 23:33:57 jr5306 Exp $
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
//# 12/17/2007   Rene Duka             RM 410745: Project Lightspeed - Release 6.0.
//# 07/22/2009   Julius Sembrano       CR26940: ValidateFacilities clean up - RM will use the TEA from the Loop Level if the TEA from the Segment Level is not present

package com.sbc.eia.bis.BusinessInterface.ServiceAddress.ason;

import java.util.Hashtable;

import com.sbc.bccs.utilities.Utility;
import com.sbc.eia.bis.embus.service.facsrc.InqfasgResponse.impl.ADDRTypeImpl;
import com.sbc.eia.bis.framework.logging.LogEventId;

/**
* Class      : AddressType
* Description: Helper used for handling the AddressType object.  
*/
public class AddressType 
{
    private Utility aUtility = null;
    private Hashtable aProperties = null;

    private String aADDRNO = null;
    private String aBSTE = null;
    private String aTEA = null;
  
    /**
     * Constructor: AddressType
     * 
     * @param Utility   utility
     * @param Hashtable properties
     * 
     * @author Rene Duka
     */
    public AddressType(Utility utility, Hashtable properties) 
    {
        aProperties = properties;
        aUtility = utility;
    }

    /**
     * Parses the address type information from the loop.
     * 
     * @param ADDRTypeImpl aADDRTypeImpl
     * 
     * @author Rene Duka
     */
    public void parseAddressType(ADDRTypeImpl aADDRTypeImpl) 
    {
        try 
        {
            // set Address number
            if (aADDRTypeImpl.getADDRNO() != null)
                aADDRNO = aADDRTypeImpl.getADDRNO();
            // set Broadband Serving Terminal
            if (aADDRTypeImpl.getBSTE() != null)
                aBSTE = aADDRTypeImpl.getBSTE();
            // set Terminal Address
            if (aADDRTypeImpl.getTEA() != null)
            	aTEA = aADDRTypeImpl.getTEA();
        } 
        catch (NullPointerException npe) 
        {
            aUtility.log(LogEventId.EXCEPTION, ">" + "Exception in AddressType: parseAddressType() " + npe.getMessage());
        } 
        catch (Exception e) 
        {
            aUtility.log(LogEventId.EXCEPTION, ">" + "Exception in AddressType: parseAddressType() " + e.getMessage());
        } 
    }

    /**
     * Get ADDRNO.
     *
     * @return String
     * 
     * @author Rene Duka
     */
    public String getADDRNO() 
    {
        return aADDRNO;
    }

    /**
     * Get TEA.
     * 
     * @return String
     * 
     * @author Julius Sembrano
     */
    public String getTEA() 
    {
        return aTEA;
    }
    /**
     * Get BSTE.
     * 
     * @return String
     * 
     * @author Rene Duka
     */
    public String getBSTE() 
    {
        return aBSTE;
    }
}
