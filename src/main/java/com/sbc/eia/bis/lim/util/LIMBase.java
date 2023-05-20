// $Id: LIMBase.java,v 1.20 2008/02/17 03:25:13 jh9785 Exp $

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
 *  This file contains the LIMBase class that serves as the base for LIM
 *  applications that would like to share common utilities.
 *  Description
 */

package com.sbc.eia.bis.lim.util;

/*****************************************************************************************
  
  Date		History
  
  08/02/00	dl6543 - Added GIS_DEFAULT_ADDRESS and GIS_DEFAULT_ZIPCODE stuff.
  09/01/00  dl6543 - Replaced GIS_DB_URL and GIS_DB_DRIVER with DATA_SOURCE names.
  09/21/00  rz7367 - Added LIM_RELEASE_VERSION
  10/13/00  rz7367 - Changed DTD_HOME and DTD_NAME to public.
  10/19/00  rz7367 - Removed the GIS default Address and Zip.
  11/01/00  dl6543 - Restored the GIS default Address and Zip.
  11/02/00  dp1941 - Moved myProperty from local constructor var to member var.  Added
					 a getter method for it.
  12/08/00	dl6543 - Set the LIM_RELEASE_VERSION within the code.
  12/18/00  dl6543 - 1.4.1 - CR144 - POI CLLI enhancement.
  01/02/01  rz7367 - 1.4.2 - New OVALS jar file.
  01/22/01  rz7367 - Added BISPREF.
  03/02/01  dp1941 - Changes for BIS 2.0.  New IDL, eliminate XML, use new exceptions.
					 Change constructor to take BisContext argument instead of String.
					 Implement the BusinessInterface.Utility interface.  Add required
					 handleException() method.
  10/23/02  as2362 - Added environment variables needed for remote logging.
  					 Update BISPERF to eLogging v1.3 standard
  07/28/03  db4252 - Added USPS_ERR_CD properties file 

  
******************************************************************************************/

import java.io.IOException;
import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;

import com.sbc.bccs.idl.helpers.ObjectPropertyManager;
import com.sbc.bccs.utilities.PropertiesFileLoader;
import com.sbc.bccs.utilities.Utility;
import com.sbc.eia.bis.BusinessInterface.Business;
import com.sbc.eia.bis.BusinessInterface.BusinessException;
import com.sbc.eia.bis.BusinessInterface.InvalidServiceCenterException;
import com.sbc.eia.bis.BusinessInterface.InvalidStateException;
import com.sbc.eia.bis.BusinessInterface.UnknownServiceCenterException;
import com.sbc.eia.bis.BusinessInterface.UnknownStateException;
import com.sbc.eia.bis.RmNam.utilities.TranBase;
import com.sbc.eia.bis.encryption.Encryption;
import com.sbc.eia.bis.framework.BisContextManager;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.idl.bis_types.AccessDenied;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.bis_types.BusinessViolation;
import com.sbc.eia.idl.bis_types.DataNotFound;
import com.sbc.eia.idl.bis_types.InvalidData;
import com.sbc.eia.idl.bis_types.NotImplemented;
import com.sbc.eia.idl.bis_types.ObjectNotFound;
import com.sbc.eia.idl.bis_types.SystemFailure;
import com.sbc.eia.idl.exception_types.ExceptionCode;
import com.sbc.eia.idl.lim_types.ProviderLocationProperty;
import com.sbc.eia.idl.lim_types.ServiceLocation;
import com.sbc.eia.idl.types.ObjectProperty;
import com.sbc.eia.idl.types.Severity;

/**
 * LIMBase can be extended by all the EJBs to take advantage of the common
 * utility method provided here.
 * Creation date: (1/16/01 11:16:39 AM)
 */
 
public class LIMBase extends TranBase implements Utility {

	/**
	 * The location of the LIM environment variable names for property file.
	 */

    public final static String PROP_FILE_LOCATION = "PROP_FILE_LOCATION";
    public final static String CHK_APPLICATION_AUTH_PROP =
        "CHK_APPLICATION_AUTH";
    public final static String USPS_EMATCH_CODE_MAP_FILE_PROP =
        "USPS_EMATCH_CODE_MAP_FILE";
    public final static String LATA_MAP_FILE_PROP = "LATA_MAP_FILE";
    public final static String LFACS_MAP_FILE_PROP = "LFACS_MAP_FILE";
    public final static String LIM_APPLICATION_ID = "LIM_BIS";

    public static String CHK_APPLICATION_AUTH = "";
    public static String USPS_EMATCH_CODE_MAP_FILE = "";
    public static String LATA_MAP_FILE = "";
    public static String LFACS_MAP_FILE = "";

    public static java.util.Properties myUSPSErrCdProperty = null;
    public static java.util.Properties myLataProperty = null;
    public static java.util.Properties myWireCntrProperty = null;
    public static String OVALS_HOST_URL = "";
    public static String OVALS_MAX_ADDRESS_LIMIT = "";
    public final static String OVALS_MAX_ADDRESS_LIMIT_PROP =
        "OVALS_MAX_ADDRESS_LIMIT";

    /**
     *  Numbers states in the region.
     */
    public final static int REGIONS_ALL = 13;
    public final static int REGIONS_PB_SWBT = 7;
    public final static int REGIONS_SNET = 1;
    public final static int REGIONS_AIT = 5;
    public final static int REGIONS_BELLSOUTH = 9;

	/**
	 * Rules Files
	 */
	public static String limRulesFile = "";
	public static String ovalsNspRulesFile = "";
	public static String rsagRulesFile = "";
	public static String bcamRulesFile = "";
   
	/**
	 *  OVALS related properties.
	 */
	 
    public final static String OVALS_HOST_URL_PROP = "OVALS_HOST_URL";
				
	// ZipToSaga related variables
	protected Properties zipToSaga = null;

	protected DateFormat df = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss.SSS");
    
    public ProviderLocationProperty [] locProviderPropForCache = new ProviderLocationProperty [1];
    
    public ServiceLocation locServiceLocation = null;

	// a flag for the SagValidationRequest
	protected boolean sagInfoOnly = false;
	
	//The following are used in By Catalyst/CBS
	protected String aCurrentState = "";
	protected boolean useRsagProduction = false;
	 
    /**
     * LIMBase default constructor.
     */
    public LIMBase() 
    {
    	super();
    }
    
    /**
     * Getter method for BisContext.
     * Creation date: (3/19/01 3:30:45 PM)
     * @return BisContext
     * @see #setCallerContext(BisContext)
     */
    public BisContext getCallerContext()
    {
    	return callerContext;
    }
    
    /**
     * Create and throw a standard BIS exception from a BusinessInterface-generated exception.
     * Creation date: (3/27/01 3:44:39 PM)
     * @param bizExc BusinessException
     * @param bizInterface Business
     * @exception InvalidData An input parameter contained invalid data.
     * @exception AccessDenied Access to the specified domain object or information is not allowed.
     * @exception BusinessViolation The attempted action violates a business rule.
     * @exception SystemFailure The method could not be completed due to system level errors (e.g., out of memory, loss of network connectivity)n.
     * @exception NotImplemented The method has not been implemented.
     * @exception ObjectNotFound The desired domain object could not be found.
     * @exception DataNotFound No data found.
     */
    public void handleBusinessException(BusinessException bizExc,Business bizInterface)
    	throws InvalidData, AccessDenied, BusinessViolation, SystemFailure, NotImplemented, ObjectNotFound, DataNotFound
    {
    	String bizClass = "";
    	
    	if (bizInterface != null)
    	{
    		bizClass = bizInterface.getClass().getName();
    		bizClass = bizClass.substring(bizClass.lastIndexOf(".")+1);
    	}
    
    	String code = ExceptionCode.ERR_LIM_INTERNAL_BUSINESS_INTERFACE;
    
    	if (bizExc instanceof InvalidServiceCenterException)
        {
    		code = ExceptionCode.ERR_LIM_INVALID_SERVICE_CENTER;
        }
        else if (bizExc instanceof InvalidStateException)
        {
    		code = ExceptionCode.ERR_LIM_INVALID_STATE;
        }
        else if (bizExc instanceof UnknownServiceCenterException)
        {
    		code = ExceptionCode.ERR_LIM_UNKNOWN_SERVICE_CENTER;
        }
        else if (bizExc instanceof UnknownStateException)
        {
    		code = ExceptionCode.ERR_LIM_UNKNOWN_STATE;
        }
    	
    	String errText = bizExc.getClass().getName();
        errText =
            errText.substring(errText.lastIndexOf(".") + 1)
                + " received from BusinessInterface "
                + bizClass
                + ": Code ["
                + bizExc.getExceptionCode()
                + "], Msg ["
                + bizExc.getMessage()
                + "]";
    		
    	throwException(code, errText, bizExc.getExceptionCode(),
            Severity.UnRecoverable, bizExc);
    }
    
    /**
     * Build BisContext to set the application paramater.
     * Creation date: (9/30/02 10:56:15 AM)
     * @return com.sbc.eia.idl.bis_types.BisContext
     * @param oldBisContext com.sbc.eia.idl.bis_types.BisContext
     */
    public BisContext buildBisContext(BisContext oldBisContext)
    {
        BisContextManager tempContextManager = new BisContextManager();
        BisContextManager oldContextManager =
            new BisContextManager(oldBisContext);
        tempContextManager.setApplication(LIM_APPLICATION_ID);

        if (oldContextManager.getCustomerName() != null)
        {
            tempContextManager.setCustomerName(
                oldContextManager.getCustomerName());
        }

        if (oldContextManager.getLoggingInformationString() != null)
        {
            tempContextManager.setLoggingInformationString(
                oldContextManager.getLoggingInformationString());
        }

        if (oldContextManager.getServiceCenter() != null)
        {
            tempContextManager.setServiceCenter(
                oldContextManager.getServiceCenter());
        }

        if (oldContextManager.getOperatorId() != null)
        {
            tempContextManager.setOperatorId(oldContextManager.getOperatorId());
        }

        if (oldContextManager.getUserId() != null)
        {
            tempContextManager.setUserId(oldContextManager.getUserId());
        }

        if (oldContextManager.getPassword() != null)
        {
            tempContextManager.setPassword(oldContextManager.getPassword());
        }

        return tempContextManager.getBisContext();
    
    }
    
    /**
     * Examine if input string is all alpha.
     * Creation date: (4/26/01 1:32:26 PM)
     * @return boolean
     * @param s String
     */
    public static boolean allAlpha(String s)
    {
    	if (s.length() == 0)
        {
    		return false;
        }
    	
    	for (int i = 0; i < s.length(); i++)
    	{
    		if ((!Character.isLetter(s.charAt(i))) &&
    			(!Character.isSpaceChar(s.charAt(i))))
            {
    			return false;
            }
    	}
    	return true;
    }
    
    /**
     * Examine if input string is all numeric.
     * Creation date: (4/26/01 1:32:26 PM)
     * @return boolean
     * @param s String
     */
    public static boolean allNumeric(String s)
    {
    	if (s.length() == 0)
        {
    		return false;
        }
    	
    	for (int i = 0; i < s.length(); i++)
    	{
            if (!Character.isDigit(s.charAt(i)))
            {
                return false;
            }
    	}
    	return true;
    }
    
    
    /**
     * Return the number of times a char is found in input string.
     * Creation date: (4/26/01 1:32:26 PM)
     * @return int
     * @param s String
     */
    public static int charCount(String s, char c)
    {
    	if (s.length() == 0)
        {
    		return 0;
        }
    
    	int j = 0;
    	int i = 0;
    		
    	while (s.indexOf(c, j) > 0)
    	{
    		j = s.indexOf(c, j);
    		j++;
    		i++;
    	}
    	
    	return i;
    }
    
    /**
     * Returns a formatted string suitable for constructing BisContext data.
     * Creation date: (3/27/02 3:59:06 PM)
     * @return String
     * @param aContext  a BisContext object
     */
    public static String formatBisContext( BisContext aContext )
    {
    	StringBuffer outputStr = new StringBuffer( "BisContext<" );
    	
        ObjectPropertyManager opm =
            new ObjectPropertyManager(aContext.aProperties);
    	ObjectProperty[] array = opm.toArray();
    	
    	for (int i = 0; i < array.length; i++)
        {
    		/**
    		 * Ignored LoggingInformation Tag - this is part of correlation id
    		 */
    		if( ! array[i].aTag.equalsIgnoreCase( "LoggingInformation" ) )
            {
    		    if (array[i].aTag.equalsIgnoreCase( "RACFPassword" ))
    		    {
    		        //Block RACFPassword password in log file
    		        outputStr.append( array[i].aTag + "|****|" );
    		    }
    		    else
    		    {
    		        outputStr.append( array[i].aTag + "|" + array[i].aValue + "|");
    		    }
            }
    	}
    	outputStr.append( ">" );
    	return outputStr.toString();
    }
    
    /**
     * Gets the LATA properties.
     * Creation date: (10/25/00 3:24:25 PM)
     * @return Properties
     * @see #setMyLataProperty
     */
    public java.util.Properties getMyLataProperty()
    {
    	if (myLataProperty == null)
        {
    		setMyLataProperty();
        }

    	return myLataProperty;
    }
    
    /**
     * Gets the USPS_ERR_CODE properties.
     * Creation date: (7/28/03 6:44:25 AM)
     * @return Properties
     * @see #setMyUSPSErrCdProperty
     */
    public java.util.Properties getMyUSPSErrCdProperty()
    {
    	if (myUSPSErrCdProperty == null)
        {
    		setMyUSPSErrCdProperty();
        }

    	return myUSPSErrCdProperty;
    }
    
    /**
     * myWireCntrProperty getter method.
     * Creation date: (10/25/00 3:24:25 PM)
     * @return Properties
     * @see #setMyWireCntrProperty
     */
    public java.util.Properties getMyWireCntrProperty()
    {
    	if (myWireCntrProperty == null)
        {
    		setMyWireCntrProperty();
        }

    	return myWireCntrProperty;
    }
    
    /**
     * do some initializations.
     * Creation date: (10/08/02 2:31:50 PM)
     */
    public void initLIMBase () 
    {
    	//
    	//  Find the path the naming hierarchy
    	//
        USPS_EMATCH_CODE_MAP_FILE =
            (String) PROPERTIES.get(USPS_EMATCH_CODE_MAP_FILE_PROP);
        LATA_MAP_FILE = (String) PROPERTIES.get(LATA_MAP_FILE_PROP);
        LFACS_MAP_FILE = (String) PROPERTIES.get(LFACS_MAP_FILE_PROP);
        OVALS_HOST_URL = (String) PROPERTIES.get(OVALS_HOST_URL_PROP);
        OVALS_MAX_ADDRESS_LIMIT =
            (String) PROPERTIES.get(OVALS_MAX_ADDRESS_LIMIT_PROP);
        CHK_APPLICATION_AUTH =
            (String) PROPERTIES.get(CHK_APPLICATION_AUTH_PROP);
    }
    
    /**
     * Sets the properties using data from the LATA file.
     * Creation date: (3/29/01 2:31:50 PM)
     * @see #getMyLataProperty
     */
    public void setMyLataProperty()
    {
    	try{
    		myLataProperty = PropertiesFileLoader.read(LATA_MAP_FILE, this);
            log(LogEventId.INFO_LEVEL_1,
                "setMyLataProperty(): using file at location <"
                    + LATA_MAP_FILE + ">");
    	}
    	catch(IOException e){}
    	catch(NullPointerException npe){}
    }
    
    /**
     * Sets the properties using data from the USPS_ERR_CODE file.
     * Creation date: (7/28/03 6:44:50 AM)
     * @see #getMyUSPSErrCdProperty
     */
    public void setMyUSPSErrCdProperty()
    {
    	try
        {
            myUSPSErrCdProperty =
                PropertiesFileLoader.read(USPS_EMATCH_CODE_MAP_FILE, this);
            log(LogEventId.INFO_LEVEL_1,
                "setMyUSPSErrCdProperty(): using file at location <"
                    + USPS_EMATCH_CODE_MAP_FILE
                    + ">");
    	}
    	catch(IOException e){}
    	catch(NullPointerException npe){}
    }
    
    /**
     * Setter method for the myWireCntrProperty.
     * Creation date: (3/29/01 2:31:50 PM)
     * @see #getMyWireCntrProperty
     */
    public void setMyWireCntrProperty()
    {
        try
        {
            myWireCntrProperty =
                PropertiesFileLoader.read(LFACS_MAP_FILE, this);
            log(LogEventId.INFO_LEVEL_1,
                "setMyWireCntrProperty(): using file at location <"
                    + LFACS_MAP_FILE
                    + ">");
    	}
    	catch(IOException e){}
    	catch(NullPointerException npe){}
    }
    
    /**
     * Converts a String delimited by spaces into an array of Strings,
     * excluding the spaces.
     * Creation date: (8/31/01 12:00:00 PM)
     * @return String[]
     * @param str String
     */
    public static String[] stringToTokens(String str) 
    {
    	StringTokenizer str_tmp = new java.util.StringTokenizer (str, " ");
    	int tokenCount = str_tmp.countTokens();
    
        String[] item = null;
        
    	if ( tokenCount > 0 )
        {
            item = new String[tokenCount];
            int counter = 0;
            while ( str_tmp.hasMoreTokens() ){
                item[counter] = str_tmp.nextToken();
                counter++;
            } // while
        }

    	return item;	
    }
    
    /**
     * Checks if a State belongs in a specified region.
     * Creation date: (9/29/01 1:32:26 PM)
     * @return boolean
     * @param aState String
     * @param aRegion int
     */
    public static boolean validateState(String aState, int aRegion)
    {
    	/**
    	 * check state is valid value
    	 */
    	switch (aRegion) 
    	{
    		case REGIONS_ALL:
    			if (aState.equalsIgnoreCase("CA") ||
    		 		aState.equalsIgnoreCase("NV") ||
    		 		aState.equalsIgnoreCase("AR") ||
    		 		aState.equalsIgnoreCase("KS") ||
    		 		aState.equalsIgnoreCase("MO") ||
    		 		aState.equalsIgnoreCase("OK") ||
    		 		aState.equalsIgnoreCase("TX") ||
    		 		aState.equalsIgnoreCase("IL") ||
    		 		aState.equalsIgnoreCase("IN") ||
    		 		aState.equalsIgnoreCase("MI") ||
    		 		aState.equalsIgnoreCase("OH") ||
    		 		aState.equalsIgnoreCase("WI") ||
    		 		aState.equalsIgnoreCase("CT") ||
    		 		aState.equalsIgnoreCase("AL") ||
        			aState.equalsIgnoreCase("FL") ||
        			aState.equalsIgnoreCase("GA") ||
        			aState.equalsIgnoreCase("KY") ||
        			aState.equalsIgnoreCase("LA") ||
        			aState.equalsIgnoreCase("MS") ||
        			aState.equalsIgnoreCase("NC") ||
        			aState.equalsIgnoreCase("SC") ||
        			aState.equalsIgnoreCase("TN"))
    			{
    				return true;
    			}
    			break;
    			
    		case REGIONS_BELLSOUTH:
    		    if (aState.equalsIgnoreCase("AL") ||
        			aState.equalsIgnoreCase("FL") ||
        			aState.equalsIgnoreCase("GA") ||
        			aState.equalsIgnoreCase("KY") ||
        			aState.equalsIgnoreCase("LA") ||
        			aState.equalsIgnoreCase("MS") ||
        			aState.equalsIgnoreCase("NC") ||
        			aState.equalsIgnoreCase("SC") ||
        			aState.equalsIgnoreCase("TN"))
        		{
        			return true;
        		}
        		break;  
        		
    		case REGIONS_PB_SWBT:
    			if (aState.equalsIgnoreCase("CA") ||
    		 		aState.equalsIgnoreCase("NV") ||
    		 		aState.equalsIgnoreCase("AR") ||
    		 		aState.equalsIgnoreCase("KS") ||
    		 		aState.equalsIgnoreCase("MO") ||
    		 		aState.equalsIgnoreCase("OK") ||
    		 		aState.equalsIgnoreCase("TX") )
    			{
    				return true;
    			}
    			break;
    			
    		case REGIONS_AIT:	
    			if (aState.equalsIgnoreCase("IL") ||
    		 		aState.equalsIgnoreCase("IN") ||
    		 		aState.equalsIgnoreCase("MI") ||
    		 		aState.equalsIgnoreCase("OH") ||
    		 		aState.equalsIgnoreCase("WI") )
    			{
    				return true;
    			}
    			break;
    		
    		case REGIONS_SNET:
    			if ( aState.equalsIgnoreCase("CT") )
    			{
    				return true;
    			}
    			break;
    			
    		
    	}
    	return false;
    }
    
    /**
     * log() Calls the real log() to do the work.
     * Creation date: (4/9/01 3:41:22 PM)
     * @param eventId String
     * @param message String
     */
    public void log(String eventId, String message )
    {
            if (eventId.equals(LogEventId.INPUT_DATA))
            {
                super.log( eventId,
                    formatBisContext(callerContext) + message);
            }
            else
            {
                super.log(eventId, message);
            }
    	
    }
    
    /**
     * findZipCode() finds the associated SAGAs of a zipcode from the zip2Saga 
     * property hash table in memory amd return sagas as an ArrayList().
     * Creation date: (1/27/03 5:08:05 PM)
     * @param zipcode String
     * @author as2362
     */
    public List findZipCode(String zipcode)
    {
    	List sagaList = new ArrayList();
    	// search hashtable
    	String str = zipToSaga.getProperty(zipcode);
    	// tokenize it
    	if(str != null)
    	{
    		StringTokenizer str_tmp = new java.util.StringTokenizer (str, ",");
    		while ( str_tmp.hasMoreTokens() )
            {
    			sagaList.add((String)str_tmp.nextToken());
    		}
    	} 
    	// return the key contents as a list
    	return sagaList;
    }

	/**
	 * decodePasswords() decodes passwords stored in the PROPERTIES hashtable. 
	 * The decoded values are then used to replace the encrypted ones in PROPERTIES.
	 * It also removes the OSS_PUBLIC_KEY from the PROPERTIES hashtable. Since the 
	 * passwords have been decoded, it is a good idea to remove the encryption key 
	 * from PROPERTIES to eliminate any chance that it will be used to decode the 
	 * already-decrypted passwords.
	 *  
	 * @author gg2712
	 * @throws RemoteException
	 */
	
	public void decodePasswords() throws RemoteException
	{
		String password = "";
		String key = (String) PROPERTIES.get(LIMTag.OSS_PUBLIC_KEY);
		Encryption enc = new Encryption();
		
		// Decode jdbcPASSWORD and replace encrypted value in PROPERTIES
		try
		{
			password = enc.decodePassword(key, (String) PROPERTIES.get(LIMTag.PASSWORD_KEY)); 
			PROPERTIES.put(LIMTag.PASSWORD_KEY, password);
		}
		catch(RemoteException e)
		{
			String msg = "Error decoding jdbcPASSWORD!";
			log(LogEventId.DEBUG_LEVEL_2, msg);
			throw new RemoteException(msg);    
		}
		
		// Decode GIS_PSWD and replace encrypted value in PROPERTIES
		try
		{
			password = enc.decodePassword(key, (String) PROPERTIES.get(LIMTag.GIS_PSWD));	
			PROPERTIES.put(LIMTag.GIS_PSWD, password);	
		}
		catch(RemoteException e)
		{
			String msg = "Error decoding GIS_PSWD!"; 
			log(LogEventId.DEBUG_LEVEL_2, msg);
			throw new RemoteException(msg);
		}

		// Decode BRMS_PASSWORD
		
		try
		{
			password = enc.decodePassword(key, (String) PROPERTIES.get(LIMTag.BRMS_PASSWORD));	
			PROPERTIES.put(LIMTag.BRMS_PASSWORD, password);	
		}
		catch(RemoteException e)
		{
			String msg = "Error decoding BRMS_PASSWORD!"; 
			log(LogEventId.DEBUG_LEVEL_2, msg);
			throw new RemoteException(msg);
		}
		
		// Remove OSS_PUBLIC_KEY from PROPERTIES
		PROPERTIES.remove(LIMTag.OSS_PUBLIC_KEY);
	}
	
	/**
	 * setCatalystEnvironment()
	 */
    protected void setCatalystEnvironment()
    {
        String catalystEnvironment = (String) PROPERTIES.get(LIMTag.CATALYST_ENVIRONMENT);
                
        if(catalystEnvironment != null && 
           catalystEnvironment.trim().equalsIgnoreCase(LIMTag.CATALYST_ENVIRONMENT_PRD))
        {
            System.setProperty(LIMTag.CATALYST_ENVIRONMENT_TAG, LIMTag.CATALYST_ENVIRONMENT_PRD);
            useRsagProduction=true;
        }
        else
        {
            System.setProperty(LIMTag.CATALYST_ENVIRONMENT_TAG, LIMTag.CATALYST_ENVIRONMENT_DEV);
            useRsagProduction=false;
        }
    }
    
	 /**
     * Setter method for SagInformationRequest. Set this parameter if ASON_LU_VALIDATION_REQ was not success,
     * but due to the SagInformationRequest requirment the response was handled as Hit. In this case do not
     * update the cache database.
     * @param sagInfo boolean
     */
    public void setSagInfoOnly (boolean sagInfo)
    {
    	sagInfoOnly = sagInfo;
    }

	 /**
     * getter method for SagInformationRequest.
     * @return boolean
     */
    public boolean getSagInfoOnly ()
    {
    	return sagInfoOnly;
    }
    
	protected void getRulesFiles() throws Exception {

		limRulesFile = (String) PROPERTIES.get(LIMTag.CSV_FileName_LIM);

		if (limRulesFile == null) {

			log(LogEventId.DEBUG_LEVEL_2, "Unable to find LIMRules file");
			throw new Exception("Internal Error : Missing Rules File : LIM Rules File");

		}
		
		ovalsNspRulesFile = (String) PROPERTIES.get(LIMTag.CSV_FileName_OVALS_NSP);

		if (ovalsNspRulesFile == null) {

			log(
				LogEventId.DEBUG_LEVEL_2,
				"Unable to find OVALS NSP Rules file");
			throw new Exception("Internal Error : Missing Rules File : OVALS NSP Rules File");

		}
		
		rsagRulesFile = (String) PROPERTIES.get(LIMTag.CSV_FileName_RSAG);

		if (rsagRulesFile == null) {

			log(
				LogEventId.DEBUG_LEVEL_2,
				"Unable to find RSAG Rules file");
			throw new Exception("Internal Error : Missing Rules File : RSAG Rules File");

		}
		
		bcamRulesFile = (String) PROPERTIES.get(LIMTag.CSV_FileName_BCAM);

		if (bcamRulesFile == null) {

			log(
				LogEventId.DEBUG_LEVEL_2,
				"Unable to find BCAM Rules file");
			throw new Exception("Internal Error : Missing Rules File : BCAM Rules File");

		}
	}		
    
	/**
	 * Returns the limRulesFile.
	 * @return String
	 */
	public String getLimRulesFile()
	{
		return limRulesFile;
	}

	/**
	 * Returns the ovalsNspRulesFile.
	 * @return String
	 */
	public String getOvalsNspRulesFile()
	{
		return ovalsNspRulesFile;
	}
	
	/**
	 * Returns the rsagRulesFile.
	 * @return String
	 */
	public String getRsagRulesFile()
	{
	    return rsagRulesFile;
	}
	
	/**
	 * Returns the bcamRulesFile.
	 * @return String
	 */
	public String getBcamRulesFile()
	{
	    return bcamRulesFile;
	}

    /**
     * @return Returns the aCurrentState.
     */
    public String getCurrentState()
    {
        return aCurrentState;
    }
    /**
     * @param currentState The aCurrentState to set.
     */
    public void setCurrentState(String currentState)
    {
        aCurrentState = currentState;
    }
    /**
     * @return Returns the useRsagProduction.
     */
    public boolean isUseRsagProduction()
    {
        return useRsagProduction;
    }
}