//$Id: Loop.java,v 1.1.2.2 2011/04/05 23:32:28 jr5306 Exp $
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
//# 09/20/2007   Deepti Nayar          Creation for LS6.
//# 11/06/2007   Rene Duka             RM 410745: Project Lightspeed - Release 6.0.
//# 12/17/2007   Rene Duka             CR 16563: Project Lightspeed - Release 6.0.
//# 08/08/2008   Shyamali Banerjee     PR 22487342: Added indicator for LS-Conditioned loops
//# 08/18/2008   Vickie Ng             LS 9.
//# 01/09/2009   Julius Sembrano       LS 10. CR23216: Added NIF Field
//# 07/22/2009   Julius Sembrano       CR26940: ValidateFacilities clean up - RM will use the TEA from the Loop Level if the TEA from the Segment Level is not present

package com.sbc.eia.bis.BusinessInterface.ason;

import java.util.ArrayList;
import java.util.Hashtable;

import com.sbc.bccs.utilities.Utility;
import com.sbc.eia.bis.embus.service.facsrc.InqfasgResponse.impl.ADDRTypeImpl;
import com.sbc.eia.bis.embus.service.facsrc.InqfasgResponse.impl.SEGTypeImpl;
import com.sbc.eia.bis.embus.service.facsrc.InqfasgResponse.impl.INQFASGTypeImpl.RSPTypeImpl.LOOPTypeImpl;
import com.sbc.eia.bis.embus.service.facsrc.InqfasgResponse.impl.INQFASGTypeImpl.RSPTypeImpl.LOOPTypeImpl.SOTypeImpl;
import com.sbc.eia.bis.framework.logging.LogEventId;

/**
 * Class      : Loop
 * Description: Helper used for handling the Loop object.  
 */
public class Loop 
{
    private Utility aUtility = null;
	private Hashtable aProperties = null;

	private String aCKID = null;
	private String aCKID2 = null;
	private String aCKID3 = null;
	private String aDCAPR = null;
	private String aTID = null;
	private String aSTAT = null;
	private String aUSOC = null;
	private String aADL = null;
	private String aSRVTYP = null;
	private String aNIF = null;
	private String aTEA = null;
    private Segment[] aSegments = null;
    private ServiceOrder[] aServiceOrders = null;
    private AddressType[] aAddressTypes = null;
    
    private boolean bIsLoopConditionedForLS = false;
    private boolean bIsBBPMissing = false;
    private boolean bIsLoadCoilExhausted = false;
    private boolean bIsPremiseBasedVRADPresent = false;
	
    /**
     * Constructor: Loop
     * 
     * @param Utility   utility
     * @param Hashtable properties
     * 
     * @author Rene Duka
     */
	public Loop(Utility utility, Hashtable properties) {
		aProperties = properties;
		aUtility = utility;

	}

    /**
     * Parses the loop information from LFACS response.
     * 
     * @param LOOPTypeImpl aLOOPTypeImpl
     * 
     * @author Rene Duka
     *         Deepti Nayar
     */
	public void parseLoop(LOOPTypeImpl aLOOPTypeImpl) 
    {
		try 
        {
		    // set the Circuit ID 
            if (aLOOPTypeImpl.getCKID() != null)            
                aCKID = aLOOPTypeImpl.getCKID();
			// set the Circuit ID 2 
            if (aLOOPTypeImpl.getCKID2() != null)
                aCKID2 = aLOOPTypeImpl.getCKID2();
			// set the Circuit ID 3 
            if (aLOOPTypeImpl.getCKID3() != null)
                aCKID3 = aLOOPTypeImpl.getCKID3();
			// set Distribution Cable Pair 
            if (aLOOPTypeImpl.getDCAPR() != null)
                aDCAPR = aLOOPTypeImpl.getDCAPR();
			// set Termination Identifier
            if (aLOOPTypeImpl.getTID() != null)            
                aTID = aLOOPTypeImpl.getTID();
			// set Loop Status
            if (aLOOPTypeImpl.getSTAT() != null)
                aSTAT = aLOOPTypeImpl.getSTAT();
			// get Assignable Line USOC
            if (aLOOPTypeImpl.getUSOC() != null)            
                aUSOC = aLOOPTypeImpl.getUSOC();
			// set Additional Line
            if (aLOOPTypeImpl.getADL() != null)            
                aADL = aLOOPTypeImpl.getADL();
			// set Service Type 
            if (aLOOPTypeImpl.getSRVTYP() != null)            
                aSRVTYP = aLOOPTypeImpl.getSRVTYP();
            // set Network Interface
            if (aLOOPTypeImpl.getNIF() != null)
            	aNIF = aLOOPTypeImpl.getNIF();
            // set Terminal Address
            if (aLOOPTypeImpl.getTEA() != null)
            	aTEA = aLOOPTypeImpl.getTEA();
            // set Address Types
            ArrayList aAddressTypeArray = new ArrayList();
            for (int i=0; i < aLOOPTypeImpl.getADDR().size(); i++) 
            {
                ADDRTypeImpl aADDRTypeImpl = (ADDRTypeImpl) aLOOPTypeImpl.getADDR().get(i);
                AddressType aAddress = new AddressType(aUtility, aProperties);
                aAddress.parseAddressType(aADDRTypeImpl);
                aAddressTypeArray.add(aAddress);
            }        
            if (aAddressTypeArray.size() > 0) 
            {
                aAddressTypes = (AddressType[]) aAddressTypeArray.toArray(new AddressType[aAddressTypeArray.size()]);
            }
            // set Segments
            ArrayList aSegmentArray = new ArrayList();
            for (int i=0; i < aLOOPTypeImpl.getSEG().size(); i++) 
            {
                SEGTypeImpl aSEGTypeImpl = (SEGTypeImpl) aLOOPTypeImpl.getSEG().get(i);
                Segment aSegment = new Segment(aUtility, aProperties);
                aSegment.parseSegment(aSEGTypeImpl);
                aSegmentArray.add(aSegment);
            }        
            if (aSegmentArray.size() > 0) 
            {
                aSegments = (Segment[]) aSegmentArray.toArray(new Segment[aSegmentArray.size()]);
            }
            // set ServiceOrders
            ArrayList aServiceOrderArray = new ArrayList();
            for (int i=0; i < aLOOPTypeImpl.getSO().size(); i++) 
            {
                SOTypeImpl aSOTypeImpl = (SOTypeImpl) aLOOPTypeImpl.getSO().get(i);
                ServiceOrder aServiceOrder = new ServiceOrder(aUtility, aProperties);
                aServiceOrder.parseServiceOrder(aSOTypeImpl);
                aServiceOrderArray.add(aServiceOrder);
            }            
            if (aServiceOrderArray.size() > 0) 
            {
                aServiceOrders = (ServiceOrder[]) aServiceOrderArray.toArray(new ServiceOrder[aServiceOrderArray.size()]);
            }
		}
        catch (NullPointerException npe) 
        {
            aUtility.log(LogEventId.EXCEPTION, ">" + "Exception in Loop: parseLoop() " + npe.getMessage());
		}
        catch (Exception e) 
        {
            aUtility.log(LogEventId.EXCEPTION, ">" + "Exception in Loop: parseLoop() " + e.getMessage());
		} 
	}

	/**
	 * Get CKID.
     * 
	 * @return String
     * 
     * @author Deepti Nayar
     */
	public String getCKID() 
    {
		return aCKID;
	}

    /**
     * Get CKID2.
     * 
     * @return String
     * 
     * @author Deepti Nayar
     */
	public String getCKID2() 
    {
		return aCKID2;
	}

    /**
     * Get CKID3.
     * 
     * @return String
     * 
     * @author Deepti Nayar
     */
	public String getCKID3() 
    {
		return aCKID3;
	}
	
    /**
     * Get DCAPR.
     * 
     * @return String
     * 
     * @author Deepti Nayar
     */
	public String getDCAPR() 
    {
		return aDCAPR;
	}

    /**
     * Get TID.
     * 
     * @return String
     * 
     * @author Deepti Nayar
     */
	public String getTID() 
    {
		return aTID;
	}

    /**
     * Get STAT.
     * 
     * @return String
     * 
     * @author Deepti Nayar
     */
	public String getSTAT() 
    {
		return aSTAT;
	}

    /**
     * Get USOC.
     * 
     * @return String
     * 
     * @author Deepti Nayar
     */
	public String getUSOC() 
    {
		return aUSOC;
	}

    /**
     * Get ADL.
     * 
     * @return String
     * 
     * @author Deepti Nayar
     */
	public String getADL() 
    {
		return aADL;
	}

    /**
     * Get SRVTYP.
     * 
     * @return String
     * 
     * @author Deepti Nayar
     */
	public String getSRVTYP() 
    {
		return aSRVTYP;
	}

    /**
     * Get NIF.
     * 
     * @return String
     * 
     * @author Julius Sembrano
     */
	public String getNIF() 
	{
		return aNIF;
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
     * Get segment infrormation.
     * 
     * @return Segment[]
     * 
     * @author Deepti Nayar
     */
	public Segment[] getSegments() 
    {
		return aSegments;
	}

    /**
     * Get service order information.
     * 
     * @return ServiceOrder[]
     * 
     * @author Deepti Nayar
     */
	public ServiceOrder[] getServiceOrders() 
    {
		return aServiceOrders;
	}

    /**
     * Get address information.
     * 
     * @return AddressType[]
     * 
     * @author Rene Duka
     */
    public AddressType[] getAddressTypes() 
    {
        return aAddressTypes;
    }
    
    /**
     * Get LS conditioned indicator.
     * 
     * @return boolean
     * 
     * @author Rene Duka
     */
    public boolean getIsLoopConditionedForLS() 
    {
        return bIsLoopConditionedForLS;
    }

    /**
     * Set LS conditioned indicator.
     * 
     * @return boolean
     * 
     * @author Rene Duka
     */
    public void setIsLoopConditionedForLS(boolean aInput) 
    {
    	 bIsLoopConditionedForLS = aInput;
    }
    
    /**
     * Get BBPMissing indicator.
     * 
     * @return boolean
     * 
     * @author Vickie Ng
     */
    public boolean getIsBBPMissing() 
    {
        return bIsBBPMissing;
    }

    /**
     * Set LS conditioned indicator.
     * 
     * @return boolean
     * 
     * @author Vickie Ng
     */
    public void setIsBBPMissing(boolean aInput) 
    {
    	bIsBBPMissing = aInput;
    }
    
    /**
     * Get BBPMissing indicator.
     * 
     * @return boolean
     * 
     * @author Vickie Ng
     */
    public boolean getIsLoadCoilExhausted() 
    {
        return bIsLoadCoilExhausted;
    }

    /**
     * Set LS conditioned indicator.
     * 
     * @return boolean
     * 
     * @author Vickie Ng
     */
    public void setIsLoadCoilExhausted(boolean aInput) 
    {
    	bIsLoadCoilExhausted = aInput;
    }

    /**
     * Set get Premise-base VRAD indicator.
     * 
     * @return boolean
     * 
     * @author Julius Sembrano
     */
    public boolean getIsPremiseBasedVRADPresent() 
    {
		return bIsPremiseBasedVRADPresent;
	}

    /**
     * Set Premise-based VRAD indicator indicator.
     * 
     * @return boolean
     * 
     * @author Julius Sembrano
     */
	public void setIsPremiseBasedVRADPresent(boolean aInput) 
	{
		bIsPremiseBasedVRADPresent = aInput;
	}
}
	

