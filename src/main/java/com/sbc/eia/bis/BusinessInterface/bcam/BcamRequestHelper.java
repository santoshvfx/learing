//$Id: BcamRequestHelper.java,v 1.9 2008/04/07 22:11:37 gg2712 Exp $

package com.sbc.eia.bis.BusinessInterface.bcam;

import java.util.Properties;
import javax.xml.bind.Marshaller;
import java.util.StringTokenizer;

import com.sbc.eia.bis.lim.util.LIMBase;
import com.sbc.eia.bis.lim.jaxb.bcam.impl.BCDRequestImpl;
import com.sbc.eia.bis.lim.util.LIMBisContextManager;
import com.sbc.eia.bis.lim.jaxb.bcam.impl.ProbationaryAddressRequestImpl;
import com.sbc.eia.bis.lim.jaxb.bcam.impl.UpdateBillingAddressImpl;
import com.sbc.eia.idl.lim.helpers.AddressHandler;
import com.sbc.eia.idl.types.CompositeObjectKey;
import com.sbc.eia.idl.types.StringOpt;
import com.sbc.eia.bis.lim.util.JaxbXmlEncoderDecoder;
import com.sbc.eia.bis.lim.jaxb.bcam.BCDRequestType;
import com.sbc.eia.bis.embus.service.access.ServiceException;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilder;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilderRule;
import com.sbc.eia.bis.lim.util.LIMTag;
import com.sbc.eia.idl.types.Severity;
import com.sbc.eia.idl.bis_types.InvalidData;
import com.sbc.eia.idl.bis_types.AccessDenied;
import com.sbc.eia.idl.bis_types.BusinessViolation;
import com.sbc.eia.idl.bis_types.DataNotFound;
import com.sbc.eia.idl.bis_types.SystemFailure;
import com.sbc.eia.idl.bis_types.NotImplemented;
import com.sbc.eia.idl.bis_types.ObjectNotFound;

/**
 * Build a xml string for BCAM Request
 */
public class BcamRequestHelper
{
    private LIMBase limBase = null;
    private String[] newAddress_CassAddressLines = null;
    private String[] newAddress_AuxiliaryAddressLines = null;
    private String btn = "";
    private String deliveryPointValidationCode = "";
    private int count_AuxAddrLine = 0;
    private JaxbXmlEncoderDecoder bcmsEncoderDecoder = null;
    
    /**
     * Constructor
     * @param m_LimBase LIMBase
     */
    public BcamRequestHelper(LIMBase m_LimBase) 
    {
        limBase = m_LimBase;
    }
    
    /**
     * Create xml string
     * @return String
     * @param m_billingAccount CompositeObjectKey
     * @param m_address AddressHandler
     * @param m_deliveryPointValidationCode StringOpt
     * @throws SystemFailure
     * @throws InvalidData
     * @throws ObjectNotFound
     * @throws BusinessViolation
     * @throws AccessDenied
     * @throws NotImplemented
     * @throws DataNotFound
     */
    public String getXMLRequest(CompositeObjectKey m_billingAccount,
            					AddressHandler m_address,
            					StringOpt m_deliveryPointValidationCode)
    	throws 
			InvalidData, 
			AccessDenied, 
			BusinessViolation, 
			DataNotFound, 
			SystemFailure, 
			NotImplemented, 
			ObjectNotFound
    
    {
        String retXML = "";
            
        processBTN(m_billingAccount);
        processCassAndAuxAddressLines(m_address);
        processDeliveryPoint(m_deliveryPointValidationCode);
        
        BCDRequestImpl requestTemp = getBCAMInputRequest(m_address);
        
		try
        {
		    retXML = getEncodeDecode().encode(new Object[]{requestTemp});
        }
        catch (ServiceException e)
        {
            Properties tagValues = new Properties();
            tagValues.setProperty("SYSTEM", "BCAM");
            tagValues.setProperty("MSG", e.getOriginalException().getMessage());
    
        	ExceptionBuilder.parseException(
        	    limBase.getCallerContext(),
                limBase.getLimRulesFile(),
                "",
                LIMTag.CSV_JAXBError,     
                "JAXB Exception",        
                true,
                ExceptionBuilderRule.NO_DEFAULT,
                null,
                null,
                limBase,
                "BcamRequestHelper",
                Severity.UnRecoverable,
                tagValues);
        }
        
        return retXML;
    }
    
    /**
     * Publish local variable btn. Trim and prevent null pointing exception.
     * @param m_BillingAccountKey CompositeObjectKey
     */
    private void processBTN(CompositeObjectKey m_BillingAccountKey)
    {
        btn = "";
        try
        {
            if (m_BillingAccountKey != null &&
                m_BillingAccountKey.aKeys != null &&
                m_BillingAccountKey.aKeys.length > 0 &&
                m_BillingAccountKey.aKeys[0].aValue != null)
            {
                btn = m_BillingAccountKey.aKeys[0].aValue.trim();
            }
        }
        catch (Exception e) {}
    }

    /**
     * Publish local variable newAddress_CassAddressLines, newAddress_AuxiliaryAddressLines, 
     * and count_AuxAddrLine.Trim and prevent null pointing exception.
     * @param address AddressHandler
     */
    private void processCassAndAuxAddressLines(AddressHandler address)
    {
        newAddress_CassAddressLines = null;
        newAddress_AuxiliaryAddressLines = null;
        count_AuxAddrLine = 0;
        
        if (address.getCassAddressLines() != null)
        {
            newAddress_CassAddressLines = new String[address.getCassAddressLines().length];
            
            for (int i = 0; i < address.getCassAddressLines().length; i++)
            {
                if (address.getCassAddressLines()[i] != null)
                {
                    if (address.getCassAddressLines()[i].trim().length() > 0)
                    {
                        newAddress_CassAddressLines[i] = address.getCassAddressLines()[i].trim();
                    }
                    else
                    {
                        newAddress_CassAddressLines[i] = "";
                    }
                }
                else
                {
                    newAddress_CassAddressLines[i] = "";
                }
            }
        }
        else
        {
            newAddress_CassAddressLines = new String[0];
        }
        
        if (address.getAuxiliaryAddressLines() != null)
        {
            newAddress_AuxiliaryAddressLines = new String[address.getAuxiliaryAddressLines().length];
            
            for (int i = 0; i < address.getAuxiliaryAddressLines().length; i++)
            {
                if (address.getAuxiliaryAddressLines()[i] != null)
                {
                    if (address.getAuxiliaryAddressLines()[i].trim().length() > 0)
                    {
                        newAddress_AuxiliaryAddressLines[i] = address.getAuxiliaryAddressLines()[i].trim();
                        count_AuxAddrLine++;
                    }
                    else
                    {
                        newAddress_AuxiliaryAddressLines[i] = "";
                    }
                }
                else
                {
                    newAddress_AuxiliaryAddressLines[i] = "";
                }
            }
        }
        else
        {
            newAddress_AuxiliaryAddressLines = new String[0];
        }
    }
    
    /**
     * Publish local variable deliveryPointValidationCode .Trim and prevent null pointing exception.
     * @param dpv StringOpt
     */
    private void processDeliveryPoint(StringOpt dpv)
    {
        deliveryPointValidationCode = "";
        try
        {
            deliveryPointValidationCode = dpv.theValue().trim();
        }
        catch (org.omg.CORBA.BAD_OPERATION e) {}
        catch (java.lang.NullPointerException e) {}
    }
    
    /**
     * Formate and return BCDRequestImpl before calling encode.
     * @return BCDRequestImpl
     * @param address AddressHandler
     * @throws SystemFailure
     * @throws InvalidData
     * @throws ObjectNotFound
     * @throws BusinessViolation
     * @throws AccessDenied
     * @throws NotImplemented
     * @throws DataNotFound
     */
    public BCDRequestImpl getBCAMInputRequest(AddressHandler address)
    	throws 
    		InvalidData, 
    		AccessDenied, 
    		BusinessViolation, 
    		DataNotFound, 
    		SystemFailure, 
    		NotImplemented, 
    		ObjectNotFound
    {
        boolean billNameFromCassLinesZero = false;
        BCDRequestImpl request = new BCDRequestImpl();
        UpdateBillingAddressImpl updateBillingAddress = new UpdateBillingAddressImpl();
        
        //ACCT_ID (Required)
        updateBillingAddress.setAccountId(btn);
        
        //SOURCE_APPL_IND (Required)
        LIMBisContextManager bisContextManager = new LIMBisContextManager(limBase.getCallerContext());
        try
        {
            updateBillingAddress.setSourceApplInd(getApplInd(bisContextManager.getApplication().trim().toUpperCase()));
        }
        catch (NullPointerException e)
        {
            updateBillingAddress.setSourceApplInd("");
        }
        bisContextManager = null;
        
        
        //BILL_NAME (Required)
        if (newAddress_CassAddressLines.length > 0 && 
            newAddress_CassAddressLines[0].length() > 0)
        {
            updateBillingAddress.setBillName(newAddress_CassAddressLines[0]);
        }
        else
        {
            updateBillingAddress.setBillName("");
        }
        
        //DPV_STATUS_CD (Optional)
        if (deliveryPointValidationCode.length() > 0)
        {
            updateBillingAddress.setDpvStatusCd(deliveryPointValidationCode);
        }
        
        //BYPASS_VALIDATION_IND (Optional)
        if ((deliveryPointValidationCode.length() > 0) &&
            (address.getCassAdditionalInfo() != null &&
             address.getCassAdditionalInfo().length() > 0 &&
             (address.getCassAdditionalInfo().startsWith("3") ||
              address.getCassAdditionalInfo().startsWith("4"))))
        {
            updateBillingAddress.setBypassValidationInd("N");
        }
        else
        {
            updateBillingAddress.setBypassValidationInd("Y");
        }
        
        //Probationary Address (Required)
        ProbationaryAddressRequestImpl probationAddress = new ProbationaryAddressRequestImpl();
        if (newAddress_CassAddressLines.length > 0)
        {
            int lastIndex = 0;
            for (int i = 0; i < newAddress_CassAddressLines.length; i++)
            {
                if (newAddress_CassAddressLines[i].length() > 0 &&
                    i > lastIndex)
                {
                    lastIndex = i;
                }
            }
            //Post_Office (Required)
            probationAddress.setPostOffice(newAddress_CassAddressLines[lastIndex]);
             
            int startIndex = 1;
            
            for (int i = startIndex; i < lastIndex; i++)
            {
                if (newAddress_CassAddressLines[i].length() > 0)
                {
                    if (probationAddress.getAddress2() == null)
                    {
                        //Address2 (Required)
                        probationAddress.setAddress2(newAddress_CassAddressLines[i]);
                    }
                    else if (probationAddress.getAddress3() == null)
                    {
                        //Address3 (Optional)
                        probationAddress.setAddress3(newAddress_CassAddressLines[i]);
                    }
                    else if (probationAddress.getAddress4() == null)
                    {
                        //Address4 (Optional)
                        probationAddress.setAddress4(newAddress_CassAddressLines[i]);
                    }
                }
            }   
        }
        
        //City (Optional)
        if (address.getCity().length() > 0)
        {
            probationAddress.setCity(address.getCity());
        }
        
        //State (Optional)
        if (address.getState().length() > 0)
        {
            probationAddress.setState(address.getState());
        }
        
        //Zip (Optional)
        if (address.getPostalCode().length() > 0)
        {
            probationAddress.setZip5(address.getPostalCode());
        }
        
        //Zip5 (Optional)
        if (address.getPostalCodePlus4().length() > 0)
        {
            probationAddress.setZip4(address.getPostalCodePlus4());
        }
                    
        //Foreign_Address_Ind (Optional)
        if (address.getCountry().length() == 0 ||
            address.getCountry().equalsIgnoreCase("US") ||
            address.getCountry().equalsIgnoreCase("USA"))
        {
            probationAddress.setForeignAddressInd("N");
        }
        else
        {
            probationAddress.setForeignAddressInd("Y");
        }
          
        //Nbr_Non_Address_Lines (Required)
        probationAddress.setNbrNonAddressLines("" + count_AuxAddrLine);
         
        updateBillingAddress.setProbationaryAddressRequest(probationAddress);
        
        request.setUpdateBillingAddress(updateBillingAddress);
        
        return request;
    }
    
    /**
     * Formate and return ApplID. Retrieve this data from lim.properties files
     * @return String 
     * @param applID String
     * @throws SystemFailure
     * @throws InvalidData
     * @throws ObjectNotFound
     * @throws BusinessViolation
     * @throws AccessDenied
     * @throws NotImplemented
     * @throws DataNotFound
     */
    private String getApplInd(String application)
    	throws 
    		InvalidData, 
    		AccessDenied, 
    		BusinessViolation, 
    		DataNotFound, 
    		SystemFailure, 
    		NotImplemented, 
    		ObjectNotFound
    {
        String retValue = "";
        String applicationList = "";
        boolean notFound = false;
        
        try
        {
            applicationList = ((String) limBase.getPROPERTIES().get(LIMTag.BCAM_SOURCE_APPLICATION_CODE)).trim();
        }
        catch (Exception e) {}
        
        if (applicationList != null && applicationList.length() > 0)
        {
            //The format always = APPLICATION1,VALUE1,APPLICATION2,VALUE2....
            StringTokenizer st = new StringTokenizer(applicationList, ",");
            while (st.hasMoreTokens())
			{
                //Application
                if(st.nextToken().trim().equalsIgnoreCase(application))
				{
                    if (st.hasMoreTokens())
                    {
                        //Next token is Value
                        retValue = st.nextToken().trim();
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
            
            if (retValue.length() == 0)
            {
                //If lim.properties setup properly, it should not come in here.
                //Throw LIM Internal Error Exception
                notFound = true;
            }
        }
        else
        {
            notFound = true;
        }
            
        if (notFound)
        {
            ExceptionBuilder.parseException(
            	    limBase.getCallerContext(),
                    limBase.getLimRulesFile(),
                    "",
                    LIMTag.CSV_InternalError,     
                    null,        
                    true,
                    ExceptionBuilderRule.NO_DEFAULT,
                    null,
                    null,
                    limBase,
                    "BcamRequestHelper: getApplInd()",
                    Severity.UnRecoverable,
                    null);
        }
        
        return retValue;
    }
    
    /**
     * Initiate Encoder Decoder.
     * @return Returns JaxbXmlEncoderDecoder
     */
    private JaxbXmlEncoderDecoder getEncodeDecode()
    {
        if (bcmsEncoderDecoder == null)
        {
            //Set the properties. 
    		Properties options = new Properties();
    		options.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, "true");
            
            bcmsEncoderDecoder = new JaxbXmlEncoderDecoder(limBase, 
										BCDRequestType.class.getPackage().getName(), 
										options);
        }
        
        return bcmsEncoderDecoder;
    }
    /**
     * @return Returns the count_AuxAddrLine.
     */
    public int getCount_AuxAddrLine() 
    {
        return count_AuxAddrLine;
    }
}
