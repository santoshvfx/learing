//$Id: ServiceOrder.java,v 1.1.2.2 2011/04/05 23:33:21 jr5306 Exp $
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
//# 11/01/2007   Rene Duka             RM 410745: Project Lightspeed - Release 6.0.
//# 12/17/2007   Rene Duka             CR 16563: Project Lightspeed - Release 6.0.
//# 03/27/2008   Rene Duka             Defect 88209: CKID in TN format followed by TID not to be used as TN/RTID.

package com.sbc.eia.bis.BusinessInterface.ason;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.StringTokenizer;

import com.sbc.bccs.utilities.Utility;
import com.sbc.eia.bis.embus.service.facsrc.InqfasgResponse.impl.ADDRTypeImpl;
import com.sbc.eia.bis.embus.service.facsrc.InqfasgResponse.impl.SEGTypeImpl;
import com.sbc.eia.bis.embus.service.facsrc.InqfasgResponse.impl.INQFASGTypeImpl.RSPTypeImpl.LOOPTypeImpl.SOTypeImpl;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.idl.types.EiaDate;

/**
 * Class      : ServiceOrder
 * Description: Helper used for handling the ServiceOrder object.  
 */
public class ServiceOrder 
{
    private Utility aUtility = null;
    private Hashtable aProperties = null;

    private String aORD = null;
    private EiaDate aDD = null;
    private String aCKID = null;
    private String aCKID2 = null;
    private String aCKID3 = null;
    private String aSTAT = null;
    private String aUSOC = null;
    private String aADL = null;
    private String aSRVTYP = null;
    private String aNIF = null;
    private String aTEA = null;
    private String aTID = null;
    private String aADDR_BSTE = null;
    private String aADDR_TEA = null;
    private Segment[] aSegments = null;
    private AddressType[] aAddressTypes = null;

    /**
     * Constructor: ServiceOrder
     * 
     * @param Utility   utility
     * @param Hashtable properties
     * 
     * @author Rene Duka
     */
    public ServiceOrder(Utility utility, Hashtable properties) 
    {
        aProperties = properties;
        aUtility = utility;
    }

    /**
     * Parses the service order information from the loop.
     * 
     * @param SOTypeImpl aSOTypeImpl
     * 
     * @author Rene Duka
     */
    public void parseServiceOrder(SOTypeImpl aSOTypeImpl) 
    {
        try 
        {
            // set SOAC Service Order
            if (aSOTypeImpl.getORD() != null)
                aORD = aSOTypeImpl.getORD();
            // set Order Due Date
            if (aSOTypeImpl.getDD() != null) 
            {
                String aYear = null;
                String aMonth = null;
                String aDay = null;
                StringTokenizer aTokenizer = new StringTokenizer(aSOTypeImpl.getDD().toString(), "-");
                // 1st field - MM
                if (aTokenizer.hasMoreElements()) 
                {
                    aMonth = aTokenizer.nextToken().trim();
                }
                // 2nd field - DD
                if (aTokenizer.hasMoreElements()) 
                {
                    aDay = aTokenizer.nextToken().trim();
                }
                // 3rd field - YY
                if (aTokenizer.hasMoreElements()) 
                {
                    aYear = "20" + aTokenizer.nextToken().trim();
                }
                if (aYear != null && aMonth != null && aDay != null) 
                {
                    aDD = new EiaDate((Integer.valueOf(aYear)).shortValue(),
                                      (Integer.valueOf(aMonth)).shortValue(),
                                      (Integer.valueOf(aDay)).shortValue());
                
                }
            }
            // set Circuit ID
            if (aSOTypeImpl.getCKID() != null)
                aCKID = aSOTypeImpl.getCKID();
            // set Circuit ID 2
            if (aSOTypeImpl.getCKID2() != null)
                aCKID2 = aSOTypeImpl.getCKID2();
            // set Circuit ID 3
            if (aSOTypeImpl.getCKID3() != null)
                aCKID3 = aSOTypeImpl.getCKID3();
            // set Loop Status
            if (aSOTypeImpl.getSTAT() != null)
                aSTAT = aSOTypeImpl.getSTAT();
            // get Assignable Line USOC
            if (aSOTypeImpl.getUSOC() != null)
                aUSOC = aSOTypeImpl.getUSOC();
            // set Additional Line
            if (aSOTypeImpl.getADL() != null)
                aADL = aSOTypeImpl.getADL();
            // set Service Type
            if (aSOTypeImpl.getSRVTYP() != null)
                aSRVTYP = aSOTypeImpl.getSRVTYP();
            // set Network Interface
            if (aSOTypeImpl.getNIF() != null)
            	aNIF = aSOTypeImpl.getNIF();
            // set Terminal Address
            if (aSOTypeImpl.getTEA() != null)
            	aTEA = aSOTypeImpl.getTEA();
            // set Terminal ID
            if (aSOTypeImpl.getTID() != null)
                aTID = aSOTypeImpl.getTID();
            // set Address Types
            ArrayList aAddressTypeArray = new ArrayList();
            for (int i=0; i < aSOTypeImpl.getADDR().size(); i++) 
            {
                ADDRTypeImpl aADDRTypeImpl = (ADDRTypeImpl) aSOTypeImpl.getADDR().get(i);
                AddressType aAddress = new AddressType(aUtility, aProperties);
                aAddress.parseAddressType(aADDRTypeImpl);
                this.setaADDR_BSTE(aAddress.getBSTE());
                this.setaADDR_TEA(aAddress.getTEA());
                aAddressTypeArray.add(aAddress);
            }        
            if (aAddressTypeArray.size() > 0) 
            {
                aAddressTypes = (AddressType[]) aAddressTypeArray.toArray(new AddressType[aAddressTypeArray.size()]);
            }
            // set Segments
            ArrayList aSegmentArray = new ArrayList();
            for (int i=0; i < aSOTypeImpl.getSEG().size(); i++) 
            {
                SEGTypeImpl aSEGTypeImpl = (SEGTypeImpl) aSOTypeImpl.getSEG().get(i);
                Segment aSegment = new Segment(aUtility, aProperties);
                aSegment.parseSegment(aSEGTypeImpl);
                aSegmentArray.add(aSegment);
            }        
            if (aSegmentArray.size() > 0) 
            {
                aSegments = (Segment[]) aSegmentArray.toArray(new Segment[aSegmentArray.size()]);
            }
        } 
        catch (NullPointerException npe) 
        {
            aUtility.log(LogEventId.EXCEPTION, ">" + "Exception in PendingServiceOrder: parseServiceOrder() " + npe.getMessage());
        } 
        catch (Exception e) 
        {
            aUtility.log(LogEventId.EXCEPTION, ">" + "Exception in PendingServiceOrder: parseServiceOrder() " + e.getMessage());
        } 
    }

    /**
     * Get ORD.
     * 
     * @return String
     * 
     * @author Rene Duka
     */
    public String getORD() 
    {
        return aORD;
    }

    /**
     * Get DD.
     * 
     * @return EiaDate
     * 
     * @author Rene Duka
     */
    public EiaDate getDD() {
        return aDD;
    }

    /**
     * Get CKID.
     * 
     * @return String
     * 
     * @author Rene Duka
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
     * @author Rene Duka
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
     * @author Rene Duka
     */
    public String getCKID3() 
    {
        return aCKID3;
    }

    /**
     * Get TID.
     * 
     * @return String
     * 
     * @author Rene Duka
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
     * @author Rene Duka
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
     * @author Rene Duka
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
     * @author Rene Duka
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
     * @author Rene Duka
     */
    public String getSRVTYP() 
    {
        return aSRVTYP;
    }

    /**
     * Get ADDR_BSTE.
     * 
     * @return String
     * 
     * @author Rene Duka
     */
    public String getADDR_BSTE() 
    {
        return aADDR_BSTE;
    }
    
    /**
     * Get ADDR_TEA.
     * 
     * @return String
     * 
     * @author Julius Sembrano
     */
    public String getADDR_TEA() 
    {
        return aADDR_TEA;
    }

    /**
     * Get segment infrormation.
     * 
     * @return Segment[]
     * 
     * @author Rene Duka
     */
    public Segment[] getSegments() 
    {
        return aSegments;
    }

    /**
     * Get address type information.
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
     * @param aaddr_bste The aADDR_BSTE to set.
     */
    public void setaADDR_BSTE(String aaddr_bste)
    {
        this.aADDR_BSTE = aaddr_bste;
    }
    
    /**
     * @param aaddr_bste The aADDR_BSTE to set.
     */
    public void setaADDR_TEA(String aaddr_tea)
    {
        this.aADDR_TEA = aaddr_tea;
    }


    /**
     * Get SYSTP.
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
}
