//$Id: BimxRequestHelper.java,v 1.9.14.1 2012/05/01 18:13:00 dc860q Exp $

package com.sbc.eia.bis.BusinessInterface.bcam;

import java.util.StringTokenizer;

import com.sbc.eia.bis.lim.util.LIMBase;
import com.sbc.eia.idl.lim.helpers.AddressHandler;
import com.sbc.eia.idl.types.StringOpt;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.types.ObjectProperty;
import com.sbc.bccs.idl.helpers.ObjectPropertyManager;
import com.sbc.eia.bis.lim.util.LIMBisContextManager;
import com.sbc.eia.bis.lim.util.LIMTag;
import com.sbc.eia.bis.framework.logging.LogEventId;

/**
 * Create input components for BIMX AddNotesToBillingAccount
 */
public class BimxRequestHelper
{
    public LIMBase limBase = null;
    private String region = "";
    private StringOpt contactName = null;
    private AddressHandler ahNewAddress = null;
    private AddressHandler ahOldAddress = null;
    private LIMBisContextManager bisContextManager;
    private final int MAX_NOTE_LENGTH_FOR_S = 1512;
    private final int MAX_NOTE_LENGTH_FOR_P = 222;
    
    
    /**
     * Constructor
     * @param m_LimBase LIMBase
     * @param m_bisContextManager LIMBisContextManager
     * @param m_Region String
     * @param m_OldAddress AddressHandler
     * @param m_NewAddress AddressHandler
     * @param m_ContactName StringOpt
     */
    public BimxRequestHelper(
            LIMBase lim_Base,
            LIMBisContextManager m_bisContextManager,
            String m_Region,
            AddressHandler m_OldAddress,
            AddressHandler m_NewAddress,
            StringOpt m_ContactName)
    {
        limBase = lim_Base;
        bisContextManager = m_bisContextManager;
        region = m_Region;
        ahOldAddress = m_OldAddress;
        ahNewAddress = m_NewAddress;
        contactName = m_ContactName;
    }
    
    /**
     * Create BisContext for BIMX input.
     * @return BisContext
     */
    public BisContext createBisContext()
    {
        LIMBisContextManager retContextManager = new LIMBisContextManager();
        StringBuffer logInfo = new StringBuffer("BIMX Input Data BisContext: ");
        
        //Application
        if (bisContextManager.getApplication() != null)
        {
        	if(bisContextManager.getApplication().trim().equalsIgnoreCase("LIM_BIS"))
        	{
        		retContextManager.setApplication("LIMBIS");
                logInfo.append("Application=").append("LIMBIS");
        	}
        	else
        	{
        		retContextManager.setApplication(bisContextManager.getApplication().trim());
        		logInfo.append("Application=").append(bisContextManager.getApplication().trim());
        	}
        }
        //CustomerName
        if (bisContextManager.getCustomerName() != null)
        {
            retContextManager.setCustomerName(bisContextManager.getCustomerName().trim());
            logInfo.append("|").append("CustomerName=").append(bisContextManager.getCustomerName().trim());
        }
        //BusinessUnit
        if (bisContextManager.getBusinessUnit() != null)
        {
            retContextManager.setBusinessUnit(bisContextManager.getBusinessUnit().trim());
            logInfo.append("|").append("BusinessUnit=").append(bisContextManager.getBusinessUnit().trim());
        }
        //LoggingInformation
        if (bisContextManager.getLoggingInformation() != null)
        {
            retContextManager.setLoggingInformation(
                bisContextManager.getLoggingInformation());
            logInfo.append("|").append("LoggingInformation=").append(bisContextManager.getLoggingInformation());
        }
        //SouthWest Region: RACFID
        if (bisContextManager.getRacfId() != null)
        {
            retContextManager.setRacfId(
                bisContextManager.getRacfId());
            logInfo.append("|").append("RACFID=").append(bisContextManager.getRacfId());
        }
        //SouthWest Region: RACFPassword
        if (bisContextManager.getRacfPassword() != null)
        {
            retContextManager.setRacfPassword(
                bisContextManager.getRacfPassword());
            logInfo.append("|").append("RacfPassword=****");
        }
        //SouthWest Region: RACFSessionToken
        if (bisContextManager.getRacfSessionToken() != null)
        {
            retContextManager.setRacfSessionToken(
                bisContextManager.getRacfSessionToken());
            logInfo.append("|").append("RacfSessionToken=").append(bisContextManager.getRacfSessionToken());
        }
        //SouthWest Region: BOSSTypistId
        if (bisContextManager.getBossTypistId() != null)
        {
            retContextManager.setBossTypistId(
                bisContextManager.getBossTypistId());
            logInfo.append("|").append("BossTypistId=").append(bisContextManager.getBossTypistId());
        }
        
        limBase.log(LogEventId.INFO_LEVEL_1, logInfo.toString());
        logInfo = null;
        
        return retContextManager.getBisContext();
    }
    
    /**
     * Create NoteType for BIMX input.
     * @return String
     */
    public String createNoteType()
    {
        String retNoteType = "";
        String defaultValue = "";
        String noteTypeList = "";
        StringBuffer TypeChoice = new StringBuffer("BIMX_NOTETYPE");
        
        if (region.equals("S"))
        {
            //SouthWest
            TypeChoice.append("_SW");
            defaultValue = "DASH";
        }
        else if (region.equals("P"))
        {
            //West
            TypeChoice.append("_W");
            defaultValue = "GeneralReview";
        }
        else if (region.equals("A"))
        {
            //AIT. So far, out of LIM's scope.
            TypeChoice.append("_MW");
            defaultValue = "CheckingAccount";
        }
        else if (region.equals("N"))
        {
            //East. So far, out of LIM's scope.
            TypeChoice.append("_E");
            defaultValue = "CheckingAccount";
        }
        
        try
        {
            noteTypeList = ((String) limBase.getPROPERTIES().get(TypeChoice.toString())).trim();
        }
        catch (Exception e) {}
        
        if (noteTypeList != null && noteTypeList.length() > 0)
        {
            //The format always = APPLICATION1,VALUE1,APPLICATION2,VALUE2....
            StringTokenizer st = new StringTokenizer(noteTypeList, ",");
            while (st.hasMoreTokens())
			{
                //Application
                if(st.nextToken().trim().equalsIgnoreCase(bisContextManager.getApplication()))
				{
                    if (st.hasMoreTokens())
                    {
                        //Next token is Value
                        retNoteType = st.nextToken().trim();
                        break;
                    }
				}
                else
                {
                    if (st.hasMoreTokens())
                    {
                        //Next token is Value, ignore it and keep move on
                        st.nextToken();
                    }
                }
			}
            
            if (retNoteType.length() == 0)
            {
                //If lim.properties setup properly, it should not come in here.
                //Set to default value
                retNoteType = defaultValue;
                limBase.log(LogEventId.ERROR, 
                        	"BIMX_NOTETYPE can not be found in lim.properties file: Region<" +
                        	region + ">, Application<" + bisContextManager.getApplication() + ">");
            }
        }
        else
        {
            //Basically, Note Type should be set in lim.properties files.
            //If NoteType not in lim.properties file, set to default value.
            retNoteType = defaultValue;
            limBase.log(LogEventId.ERROR, 
                		"BIMX_NOTETYPE can not be found in lim.properties file: Region<" +
                		region + ">, Application<" + bisContextManager.getApplication() + ">");
        }
        
        limBase.log(LogEventId.INFO_LEVEL_1, "BIMX Input Data NoteType: " + retNoteType);
        
        return retNoteType;
    }
    
    /**
     * Create NoteText for BIMX input.
     * @return String[]
     */
    public String[] createNoteText()
    {
        String[] retNoteText = new String[2];
        StringBuffer retTextFirstLine = new StringBuffer();
        StringBuffer retTextSecondLine = new StringBuffer();
        StringBuffer logInfo = new StringBuffer("BIMX Input Data NoteText[]: ");
        String racfIdTag = "";
        String name = "";
        
        if (region.equalsIgnoreCase("P") && bisContextManager.getRacfId() != null && bisContextManager.getRacfId().length() > 0)
        {
       		racfIdTag = "RACFID " + bisContextManager.getRacfId() + " ";
        }
        
        try
		{
        	if(contactName.theValue().trim().length() > 0)
        	{
        		name = contactName.theValue().trim() + " ";
        	}
		}
        catch(Exception e)
		{
        }
        
        retTextFirstLine
			.append(racfIdTag)
			.append(name)
			.append("CHANGE BILL ADDRESS");
        
        //Line 2:
        //OLD: - 2 spaces - Old Address - space - Old City - space - 
        //Old State - space - Old Zip Code - hyphen - Old Zip+4 - space - 
        //Old Country (only if not equal to USA) - 2 spaces - 
        //NEW: - 2 spaces - New Address - space - New City - space - 
        //New State - space - New Zip Code - hyphen - Zip+4 - space - 
        //New Country (only if not equal to USA) - 2 spaces - 
        //Note text from optional Note field in Change Bill Address window
        retTextSecondLine.append("OLD:  ");
        //Old Street Name
        retTextSecondLine.append(formatAddress(ahOldAddress));
        //Old City
        if (ahOldAddress.getCity().length() > 0)
        {
            retTextSecondLine.append(" ").append(ahOldAddress.getCity());
        }
        //Old State
        if (ahOldAddress.getState().length() > 0)
        {
            retTextSecondLine.append(" ").append(ahOldAddress.getState());
        }
        //Old Zip Code
        if (ahOldAddress.getPostalCode().length() > 0)
        {
            retTextSecondLine.append(" ").append(ahOldAddress.getPostalCode());
        }
        //Old Zip Plus 4
        if (ahOldAddress.getPostalCodePlus4().length() > 0)
        {
            retTextSecondLine.append("-").append(ahOldAddress.getPostalCodePlus4());
        }
        
        //Old Country
        if (ahOldAddress.getCountry().length() > 0 &&
            !ahOldAddress.getCountry().equalsIgnoreCase("US") &&
            !ahOldAddress.getCountry().equalsIgnoreCase("USA"))
        {
            retTextSecondLine.append(" ").append(ahOldAddress.getCountry());
        }
        
        retTextSecondLine.append("  New:  ");
        //Format StreetName, City, State, Zip from CassAddressLines
        retTextSecondLine.append(formatCassAddressLines(ahNewAddress));
        
        //New Country
        if (ahNewAddress.getCountry().length() > 0 &&
            !ahNewAddress.getCountry().equalsIgnoreCase("US") &&
            !ahNewAddress.getCountry().equalsIgnoreCase("USA"))
        {
            retTextSecondLine.append(" ").append(ahNewAddress.getCountry());
        }
        
        //Optional Note Text
        if (bisContextManager.getBossNoteText() != null)
        {
            retTextSecondLine.append("  ");
            retTextSecondLine.append(bisContextManager.getBossNoteText());
        }
                
        
        retNoteText[0] = retTextFirstLine.toString().toUpperCase().trim();
        //retNoteText[1] = retTextSecondLine.toString().toUpperCase().trim();

        /* 20120425 Added this code to truncate the retTextSecondeLine for 
         * NoteText that will pass to BIMX. Maximum text size for SW (S) region 
         * is 1512 characters and West (P) region is 222 characters.
         * maxLenghtSecondline includes an extra space for combining Line1 and Line2
         */
          
        int maxLengthSecondLine = 0;
  		if (region.equals("S")) 
  		{
  			maxLengthSecondLine = MAX_NOTE_LENGTH_FOR_S - retTextFirstLine.length() - 1;
  			if (retTextSecondLine.length() > maxLengthSecondLine)
  			{
  				retNoteText[1] = retTextSecondLine.substring
  				( 0 , maxLengthSecondLine).toString().toUpperCase().trim();
  			}
  			else
  			{
  				retNoteText[1] = retTextSecondLine.toString().toUpperCase()
  						.trim();
  			}
  		} 
  		else if (region.equals("P")) 
  		{
  			maxLengthSecondLine = MAX_NOTE_LENGTH_FOR_P - retTextFirstLine.length() - 1;
  			if (retTextSecondLine.length() > maxLengthSecondLine)
  			{
  				retNoteText[1] = retTextSecondLine.substring
  				(0, maxLengthSecondLine).toString().toUpperCase().trim();
  			}
  			else
  			{
  				retNoteText[1] = retTextSecondLine.toString().toUpperCase()
  						.trim();
  			}
  		} 
  		else 
  		{
  			retNoteText[1] = retTextSecondLine.toString().toUpperCase().trim();
  		}
        //End
      
        

        logInfo.append("Line1=").append(retNoteText[0]);
        logInfo.append("|").append("Line2=").append(retNoteText[1]);
        limBase.log(LogEventId.INFO_LEVEL_1, logInfo.toString());
        logInfo = null;
        
        return retNoteText;
    }
    
    /**
     * Format Street Name.
     * @return String
     * @param ah AddressHandler
     */
    private String formatAddress(AddressHandler ah)
    {
        StringBuffer retValue = new StringBuffer();
        
        if (ah.isFielded())
        {
            //Fielded Address
            //number
            if (ah.getStreetNumber().length() > 0)
            {
                retValue.append(ah.getStreetNumber()).append(" ");
            }
            
            //street name
            if (ah.getStreetName().length() > 0)
            {
                retValue.append(ah.getStreetName()).append(" ");
            }
            
            //Unit type
            if (ah.getUnitType().length() > 0)
            {
                retValue.append(ah.getUnitType()).append(" ");
            }
            
            //Unit value
            if (ah.getUnitValue().length() > 0)
            {
                retValue.append(ah.getUnitValue()).append(" ");
            }
            
            //Level type
            if (ah.getLevelType().length() > 0)
            {
                retValue.append(ah.getLevelType()).append(" ");
            }
            
            //Level value
            if (ah.getLevelValue().length() > 0)
            {
                retValue.append(ah.getLevelValue()).append(" ");
            }
            
            //Structure type
            if (ah.getStructType().length() > 0)
            {
                retValue.append(ah.getStructType()).append(" ");
            }
            
            //Structure value
            if (ah.getStructValue().length() > 0)
            {
                retValue.append(ah.getStructValue()).append(" ");
            }        
        }
        else
        {
            try
            {
                for (int i = 0; i < ah.m_addressLine.length; i++)
                {
                    if (ah.m_addressLine[i].length() > 0)
                    {
                        retValue.append(ah.m_addressLine[i]).append(" ");
                    }
                }
            }
            catch (Exception e) {}
        }
        
        return retValue.toString().trim();
    }
    
    /**
     * Format Street Name by CassAddressLines.
     * @return String
     * @param ah AddressHandler
     */
    private String formatCassAddressLines(AddressHandler ah)
    {
        StringBuffer retValue = new StringBuffer();
        
        if (ah.getCassAddressLines() != null)
        {
            for (int i = 0; i < ah.getCassAddressLines().length; i++)
            {
                if (ah.getCassAddressLines()[i] != null && ah.getCassAddressLines()[i].trim().length() > 0)
                {
                    retValue.append(ah.getCassAddressLines()[i].trim()).append(" ");
                }
            }
        }
        
        return retValue.toString().trim();
    }
    
    /**
     * Create NoteProperties for BIMX input
     * @return ObjectProperty[]
     */
    public ObjectProperty[] createNoteProperties()
    {
        ObjectPropertyManager objectProperty = new ObjectPropertyManager();
        String tmpApplication = "";
        StringBuffer logInfo = new StringBuffer("BIMX Input Data NoteProperties[]: ");
        
        try
        {
            tmpApplication = bisContextManager.getApplication().trim();
        }
        catch (Exception e) {}
        
        //Use BisContext.Application to format this value
        if (region.equals("S"))
        {
            //SouthWest, needs TypistId. 
            //Maximum is 4 characters for TypistId
            if (tmpApplication.length() > 4)
            {
                objectProperty.add("TypistId", tmpApplication.substring(0, 4));
            }
            else
            {
                objectProperty.add("TypistId", tmpApplication);
            }
            logInfo.append("TypistId=").append(objectProperty.getValue("TypistId"));
        }
        else if (region.equals("P"))
        {
            //West, needs TypistId and ClientId 
            //Maximum is 6 characters for TypistId
            //Make ClientId identical with TypistId 
            if (tmpApplication.length() > 6)
            {
                objectProperty.add("TypistId", tmpApplication.substring(0, 6));
                objectProperty.add("ClientId", tmpApplication.substring(0, 6));
            }
            else
            {
                objectProperty.add("TypistId", tmpApplication);
                objectProperty.add("ClientId", tmpApplication);
            }
            logInfo.append("TypistId=").append(objectProperty.getValue("TypistId"));
            logInfo.append("|").append("ClientId=").append(objectProperty.getValue("ClientId"));
        }
        limBase.log(LogEventId.INFO_LEVEL_1, logInfo.toString());
        logInfo = null;
        
        return objectProperty.toArray();
    }
}
