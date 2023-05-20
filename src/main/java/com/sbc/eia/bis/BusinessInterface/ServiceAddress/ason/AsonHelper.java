//$Id: AsonHelper.java,v 1.3 2008/02/29 23:13:32 jh9785 Exp $

package com.sbc.eia.bis.BusinessInterface.ServiceAddress.ason;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.sbc.eia.idl.lim.helpers.AddressHandlerASON;
import com.sbc.gwsvcs.service.asonservice.interfaces.commandline_st;
import com.sbc.gwsvcs.service.asonservice.interfaces.taginformation_st;

/**
 * AsonHelper class.
 * Creation date: (7/17/07)
 * @author: Jean Duka
 */
public class AsonHelper
{
	protected AddressHandlerASON address = null;
	protected String highHouseNbr = "";
	protected String highHouseNbrPfx = "";
	protected String lowHouseNbr = "";
	protected String lowHouseNbrPfx = "";
	protected String aaiForSag = "";

    /**
     * Constructor for AsonHelper.
     */
    public AsonHelper() 
    {
    	super();
    	address = new AddressHandlerASON();
    }
    /**
     * Method to remove leading zeros and spaces from ASON Street Number.
     * Creation date: (8/31/01 8:04:58 AM)
     * @return String
     * @param value String
     */
    public static String formatStNbr(String value) 
    {
    	if (value == null)
    		return value;
    		
    	if (value.trim().length() == 0)
    		return value.trim();
    
    	StringBuffer temp = new StringBuffer (value);
    	int j = 0;
    	
    	for (int i = 0; i < value.length(); i++)
        {
    		if ((value.charAt(i) == ' ') ||
    			(value.charAt(i) == '0'))
            {
    		}
    		else
            {
    			temp = temp.delete(0,i);
    			i = value.length();
    		}
    	}
    
    	for (int i = 0; i < temp.length(); i++)
        {
    		if (Character.isLetter(temp.charAt(i)))
            {
    			j = i;
    			i = temp.length();
    		}
    	}
    	
    	if ((temp.length() > (j + 1)) &&
    		(Character.isLetter(temp.charAt(j))) &&
    		(temp.charAt(j + 1) == '0'))
        {
    		while (temp.length() > j + 1 && temp.charAt(j + 1) == '0')
    			temp = temp.deleteCharAt(j + 1);
    	}
    		 
    	return temp.toString().trim();
    }
    /**
     * Getter for CmdLine argument of ASON requests.
     * Creation date: (8/14/01 12:55:29 PM)
     * @return commandline_st
     */
    public static commandline_st getCmndLine() 
    {
    	return (new commandline_st(
    				ASONTag.EMPTY_STRING,		//commandName
    				ASONTag.EMPTY_STRING));		//commandFiller
    
    }
    /**
     * Getter for HighHouseNbr in AsonHelper class.
     * Creation date: (8/2/01 11:23:02 AM)
     * @return String
     * @see #setHighHouseNbr(String)
     */
    public String getHighHouseNbr() 
    {
    	return highHouseNbr;
    }
    /**
     * Getter for HighHouseNbrPfx of AsonHelper class.
     * Creation date: (9/26/01 8:40:23 AM)
     * @return String
     * @see #setHighHouseNbrPfx(String)
     */
    public String getHighHouseNbrPfx() 
    {
    	return highHouseNbrPfx;
    }
    /**
     * Getter for lowHouseNbr of AsonHelper class.
     * Creation date: (8/2/01 11:22:24 AM)
     * @return String
     * @see #setLowHouseNbr(String)
     */
    public String getLowHouseNbr() 
    {
    	return lowHouseNbr;
    }
    /**
     * Getter for LowHouseNbrPfx of AsonHelper class.
     * Creation date: (9/26/01 8:39:47 AM)
     * @return String
     * @see #setLowHouseNbrPfx(String)
     */
    public String getLowHouseNbrPfx() 
    {
    	return lowHouseNbrPfx;
    }
    /**
     * Create TagInfo parameter for ASON requests.
     * Creation date: (8/14/01 12:54:48 PM)
     * @return taginformation_st
     */
    public static taginformation_st getTagInfo() 
    {
    	Date today = new java.util.Date();
    	SimpleDateFormat formatter = new SimpleDateFormat( "yyyyMMdd.HHmmss.SSS" ) ;
    	String dateString = formatter.format( today ) ;
    	String sendingDate = dateString.substring(2,8);
    	String sendingTime = dateString.substring(9,15) + dateString.substring(16,18);
    				  
    	return (new taginformation_st(	
    					ASONTag.EMPTY_STRING,		//headerInfo1
    					ASONTag.SENDING_SYSTEM,		//sendingSystem
    					ASONTag.EMPTY_STRING,		//headerInfo2
    					sendingDate,				//sendingDate
    					ASONTag.EMPTY_STRING,		//headerInfo3
    					sendingTime,				//sendingTime
    					ASONTag.EMPTY_STRING,		//headerInfo4
    					ASONTag.EMPTY_STRING,		//receivingSystem
    					ASONTag.EMPTY_STRING,		//headerInfo5
    					ASONTag.EMPTY_STRING,		//receivingDate
    					ASONTag.EMPTY_STRING,		//headerInfo6
    					ASONTag.EMPTY_STRING,		//receivingTime
    					ASONTag.EMPTY_STRING,		//headerInfo7
    					ASONTag.EMPTY_STRING,		//tagErrCode
    					ASONTag.EMPTY_STRING,		//headerInfo8
    					ASONTag.EMPTY_STRING));		//wordAlignmentByte
    }
    /**
     * Setter for HighHouseNbr of AsonHelper class.
     * Creation date: (8/2/01 11:23:02 AM)
     * @param newHighHouseNbr String
     * @see #getHighHouseNbr
     */
    public void setHighHouseNbr(String newHighHouseNbr) 
    {
    	highHouseNbr = newHighHouseNbr;
    }
    /**
     * Setter for HighHouseNbrPfx of AsonHelper class.
     * Creation date: (9/26/01 8:40:23 AM)
     * @param newHighHouseNbrPfx String
     * @see #getHighHouseNbrPfx
     */
    public void setHighHouseNbrPfx(String newHighHouseNbrPfx) 
    {
    	highHouseNbrPfx = newHighHouseNbrPfx;
    }
    /**
     * Setter for LowHouseNbr of AsonHelper class.
     * Creation date: (8/2/01 11:22:24 AM)
     * @param newLowHouseNbr String
     * @see #getLowHouseNbr
     */
    public void setLowHouseNbr(java.lang.String newLowHouseNbr) 
    {
    	lowHouseNbr = newLowHouseNbr;
    }
    /**
     * Setter for LowHouseNbrPfx of AsonHelper class.
     * Creation date: (9/26/01 8:39:47 AM)
     * @param newLowHouseNbrPfx String
     * @see #getLowHouseNbrPfx
     */
    public void setLowHouseNbrPfx(String newLowHouseNbrPfx) 
    {
    	lowHouseNbrPfx = newLowHouseNbrPfx;
    }
    
    /**
     * Setter for the SagValidationRequest AAI filed.
     * @param aai String
     */
    public void setAAI (String aai)
    {
    	aaiForSag = aai;
    }
    
    /**Getter for the SagValidationRequest AAI filed.
     * @return String
     */
    public String getAAI ()
    {
    	return aaiForSag;
    }
}
