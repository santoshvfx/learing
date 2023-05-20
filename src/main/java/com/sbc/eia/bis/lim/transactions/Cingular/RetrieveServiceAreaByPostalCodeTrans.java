//$Id: RetrieveServiceAreaByPostalCodeTrans.java ,v 1.7 2008/02/20 21:40:17 jd3462 Exp $
package com.sbc.eia.bis.lim.transactions.Cingular;

import java.sql.SQLException;
import java.util.Properties;

import com.sbc.bccs.idl.helpers.IDLUtil;
import com.sbc.bccs.idl.helpers.ObjectPropertyManager;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilder;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilderResult;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilderRule;
import com.sbc.eia.bis.embus.service.access.ServiceException;
import com.sbc.eia.bis.embus.service.atlas.AtlasException;
import com.sbc.eia.bis.embus.service.atlas.AtlasSoapFaultException;
import com.sbc.eia.bis.embus.service.atlas.interfaces.InquireMarketServiceAreasResponse;
import com.sbc.eia.bis.embus.service.atlas.interfaces.ServiceAreaLookupInfo;
import com.sbc.eia.bis.embus.service.atlas.interfaces.impl.ServiceAreaLookupInfoImpl;
import com.sbc.eia.bis.embus.service.atlas.utilities.InquireMarketServiceAreasRequestHelper;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.bis.lim.util.BisContextValidations;
import com.sbc.eia.bis.lim.util.LIMBase;
import com.sbc.eia.bis.lim.util.LIMBisContextManager;
import com.sbc.eia.bis.lim.util.LIMIDLUtil;
import com.sbc.eia.bis.lim.util.LIMTag;
import com.sbc.eia.idl.bis_types.AccessDenied;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.bis_types.BusinessViolation;
import com.sbc.eia.idl.bis_types.DataNotFound;
import com.sbc.eia.idl.bis_types.InvalidData;
import com.sbc.eia.idl.bis_types.MultipleExceptions;
import com.sbc.eia.idl.bis_types.NotImplemented;
import com.sbc.eia.idl.bis_types.ObjectNotFound;
import com.sbc.eia.idl.bis_types.SystemFailure;
import com.sbc.eia.idl.lim.RetrieveServiceAreaByPostalCodeReturn;
import com.sbc.eia.idl.lim.queries.RetrieveStateCodeByZip;
import com.sbc.eia.idl.lim_types.MarketInformation;
import com.sbc.eia.idl.lim_types.MarketInformationOpt;
import com.sbc.eia.idl.lim_types.ServiceArea;
import com.sbc.eia.idl.lim_types.ServiceAreaSeqOpt;
import com.sbc.eia.idl.types.Severity;
import com.sbc.embus.common.MessageConstants;

/**
 * @author rz7367
 *
 * This class implements the RetrieveServiceAreaByPostalCode transaction.
 * Currently, this transaction solely goes to the Atlas/Cingular backend.
 * 
 */
public class RetrieveServiceAreaByPostalCodeTrans extends CingularBase
{
    private String m_limRuleFile = null;
    private String m_cingularRuleFile = null;
    private ExceptionBuilderResult m_exBldReslt = null;
    private CingularTableLookupService m_ctls = null;

    /**
     * Constructor.
     * Method RetrieveServiceAreaByPostalCodeTrans.
     * @param lim_base
     */
    public RetrieveServiceAreaByPostalCodeTrans (LIMBase lim_base) 
    {
        super(lim_base);
        m_limRuleFile =
            (String) getLimBase().getPROPERTIES().get(
                LIMTag.CSV_FileName_LIM);
        m_cingularRuleFile =
            (String) getLimBase().getPROPERTIES().get(
                LIMTag.CSV_FileName_CINGULAR);
    }

    /**
     * Method execute.  This method implements the Retrieve Service Area By Postal 
     * Code.  It is enhanced for WOW3B and LS R2 to include retrieving the Market/SubMarket 
     * info from the Atlas RC and capturing the CAS Region information.
     * @param aCingularSalesChannel String
     * @param aPostalCode String
     * @param aRequestServiceAreaIndicator boolean
     * @param aRequestMarketInformationIndicator boolean
     * @return RetrieveServiceAreaByPostalCodeReturn
     * @exception InvalidData An input parameter contained invalid data.
     * @exception AccessDenied Access to the specified domain object or information is not allowed.
     * @exception BusinessViolation The attempted action violates a business rule.
     * @exception ObjectNotFound The desired domain object could not be found.
     * @exception SystemFailure The method could not be completed due to system level errors.
     * @exception DataNotFound No data found.
     * @exception NotImplemented The method has not been implemented.
     * @exception MultipleExceptions
     */
    public RetrieveServiceAreaByPostalCodeReturn execute (
        String aCingularSalesChannel,
        String aPostalCode,
        boolean aRequestServiceAreaIndicator,
        boolean aRequestMarketInformationIndicator)
    throws InvalidData, 
    	   AccessDenied, 
    	   BusinessViolation,
    	   ObjectNotFound, 
    	   SystemFailure,
    	   DataNotFound, 
    	   MultipleExceptions,
    	   NotImplemented, Exception 
    {	
    	getLimBase().log (LogEventId.AUDIT_TRAIL, "RetrieveServiceAreaByPostalCode::execute()|RetrieveServiceAreaByPostalCodeReturn::RetrieveServiceAreaByPostalCodeReturn()|PRE"); 
    	
    	//	Display INPUT_DATA
    	StringBuffer buf = new StringBuffer();
    			buf	.append("RetrieveServiceAreaByPostalCodeRequest|")
    				.append("CingularSalesChannel|")
    				.append(aCingularSalesChannel)
    				.append("|")
                    .append("PostalCode|")
                    .append(aPostalCode)
                    .append("|")
                    .append("RequestServiceAreaIndicator|")
                    .append(aRequestServiceAreaIndicator)
                    .append("|")
                    .append("RequestMarketInformationIndicator|")
                    .append(aRequestMarketInformationIndicator)
                    .append("|");
    				
    	getLimBase().log(LogEventId.INPUT_DATA, buf.toString());
    
        try 
        {
            BisContextValidations bisContextValidations = new BisContextValidations (getLimBase());
            bisContextValidations.editBisContext();
            bisContextValidations.checkAuthorization ("", METHOD_RETRIEVE_SVC_AREA_BY_POSTALCODE);
        }                
        catch (InvalidData id) 
        {
            throw id;
        }
        catch (BusinessViolation bv) 
        {
            throw bv;
        }
        
        editInputData(
            aCingularSalesChannel, 
            aPostalCode, 
            aRequestServiceAreaIndicator, 
            aRequestMarketInformationIndicator);
            
        InquireMarketServiceAreasResponse imsaResponse = null;
    	
        RetrieveServiceAreaByPostalCodeReturn serviceAreaReturn = createDefaultServiceAreaByPostalCodeReturn();
        
        Properties propertiesFromMessage = null;
        LIMBisContextManager bisContextManager = new LIMBisContextManager (getLimBase().getCallerContext());  
        propertiesFromMessage = new Properties();
        
        if (aRequestServiceAreaIndicator == true 
            || aRequestMarketInformationIndicator == true)
        
        {
            getLimBase().log(LogEventId.AUDIT_TRAIL, "RetrieveServiceAreaByPostalCodeReturn::execute()|RetrieveServiceAreaByPostalCodeReturn::processInquireMarketServiceAreas()|PRE");
            serviceAreaReturn = processInquireMarketServiceAreas(
                    aPostalCode,
                    aCingularSalesChannel,
                    aRequestServiceAreaIndicator,
                    aRequestMarketInformationIndicator,
                    serviceAreaReturn,
                    bisContextManager,
                    propertiesFromMessage);
            getLimBase().log(LogEventId.AUDIT_TRAIL, "RetrieveServiceAreaByPostalCodeReturn::execute()|RetrieveServiceAreaByPostalCodeReturn::processInquireMarketServiceAreas()|POST");
        }
        
        //Display OUTPUT_DATA
        StringBuffer outbuf = new StringBuffer();
          outbuf.append("RetrieveServiceAreaByPostalCodeResponse|")
                .append("BisContext|")
                .append(LIMIDLUtil.display(serviceAreaReturn.aContext))
                .append("PostalCode|")
                .append(serviceAreaReturn.aPostalCode)
                .append("|")
                .append("ServiceAreas|")
                .append(LIMIDLUtil.display(serviceAreaReturn.aServiceAreas))
                .append("|")
                .append("MarketInformation|")
                .append(LIMIDLUtil.display(serviceAreaReturn.aMarketInformation))
                .append("|");
        
        getLimBase().log(LogEventId.OUTPUT_DATA, outbuf.toString());
    	return serviceAreaReturn;		
    }

    /**
     * Method updateBisContext. Update the BisContext with conversationID and JMSCorrelationID
     * @param propertiesFromMessage
     */
    private void updateBisContext(Properties propertiesFromMessage) 
    {
        String conversationId = propertiesFromMessage.getProperty(MessageConstants.CONVERSATION_KEY);
    	String jmsCorrelationID = propertiesFromMessage.getProperty(JMS_CORRELATION_ID);
    
    	ObjectPropertyManager opm = new ObjectPropertyManager(getLimBase().getCallerContext().aProperties);
            
        if(conversationId != null)
        {
        	boolean notFound = true;
			for (int i = 0; i < getLimBase().getCallerContext().aProperties.length; i++)
            {
                if (getLimBase().getCallerContext().aProperties[i].aTag.equalsIgnoreCase(CONVERSATION_ID))
                {
                    getLimBase().getCallerContext().aProperties[i].aValue = conversationId;
                    notFound=false;
                    break;
                }
            }
            if(notFound)
            {        	
        		opm.add(CONVERSATION_ID, conversationId);
            }
        }
        
        if(jmsCorrelationID != null)
        {
        	boolean notFound = true;
			for (int i = 0; i < getLimBase().getCallerContext().aProperties.length; i++)
            {
                if (getLimBase().getCallerContext().aProperties[i].aTag.equalsIgnoreCase(JMS_CORRELATION_ID))
                {
                    getLimBase().getCallerContext().aProperties[i].aValue = jmsCorrelationID;
                    notFound=false;
                    break;
                }
            }
            if(notFound)
            {        	
        		opm.add(JMS_CORRELATION_ID, jmsCorrelationID);
            }        	
        }
        
        getLimBase().setCallerContext(new BisContext(opm.toArray()));
        opm = null;
    }

    /**
     * Method createDefaultServiceAreaByPostalCodeReturn.
     * @return RetrieveServiceAreaByPostalCodeReturn
     */
    public RetrieveServiceAreaByPostalCodeReturn createDefaultServiceAreaByPostalCodeReturn() 
    {
        RetrieveServiceAreaByPostalCodeReturn serviceAreaByPostalCodeReturn = new RetrieveServiceAreaByPostalCodeReturn();
        serviceAreaByPostalCodeReturn.aContext = getLimBase().getCallerContext();
        
        serviceAreaByPostalCodeReturn.aPostalCode = " ";
        serviceAreaByPostalCodeReturn.aMarketInformation = new MarketInformationOpt();
        serviceAreaByPostalCodeReturn.aMarketInformation.__default();
        
        serviceAreaByPostalCodeReturn.aServiceAreas = new ServiceAreaSeqOpt();
        serviceAreaByPostalCodeReturn.aServiceAreas.__default();
                
        return serviceAreaByPostalCodeReturn;
    }

    /**
     * Method editInputData. Validates client's input data.
     * @param aCingularSalesChannel
     * @param aPostalCode
     * @param aRequestServiceAreaIndicator
     * @param aRequestMarketInformationIndicator
     * @throws InvalidData
     * @throws AccessDenied
     * @throws BusinessViolation
     * @throws ObjectNotFound
     * @throws SystemFailure
     * @throws DataNotFound
     * @throws MultipleExceptions
     * @throws NotImplemented
     */
    private void editInputData(
            String aCingularSalesChannel, 
            String aPostalCode,
            boolean aRequestServiceAreaIndicator,
            boolean aRequestMarketInformationIndicator)
        throws 
            InvalidData, 
            AccessDenied, 
            BusinessViolation,
            ObjectNotFound,
            SystemFailure,
            DataNotFound,
            MultipleExceptions,
            NotImplemented,
            Exception
    {
        if ((aCingularSalesChannel == null || aCingularSalesChannel.equals("")) ||
           (!(aCingularSalesChannel.trim().equalsIgnoreCase("lightspeed")) && 
            !(aCingularSalesChannel.trim().equalsIgnoreCase("swot")) &&
            !(aCingularSalesChannel.trim().equalsIgnoreCase("wow"))  &&
            !(aCingularSalesChannel.trim().equalsIgnoreCase("iwow"))))
        {                            
            Properties tagValues = new Properties();
            tagValues.setProperty("CLASS", RetrieveServiceAreaByPostalCodeTrans.class.getName());
                            
            m_exBldReslt = ExceptionBuilder.parseException(
                    getLimBase().getCallerContext(),
                    m_limRuleFile,
                    "",
                    LIMTag.CSV_EditError,
                    "Invalid Cingular Sales Channel",
                    true,
                    ExceptionBuilderRule.NO_DEFAULT,
                    null,
                    null,
                    getLimBase(),
                    "LIM",
                    Severity.UnRecoverable,
                    tagValues);                                                   
        }   
        
        if (aPostalCode == null || aPostalCode.trim().equals(""))
        {                            
            Properties tagValues = new Properties();
            tagValues.setProperty("CLASS", RetrieveServiceAreaByPostalCodeTrans.class.getName());
                            
            m_exBldReslt = ExceptionBuilder.parseException(
                    getLimBase().getCallerContext(),
                    m_limRuleFile,
                    "",
                    LIMTag.CSV_EditError,
                    "Missing Postal Code",
                    true,
                    ExceptionBuilderRule.NO_DEFAULT,
                    null,
                    null,
                    getLimBase(),
                    "LIM",
                    Severity.UnRecoverable,
                    tagValues);                                                   
        }
        
        if (aRequestServiceAreaIndicator == false && aRequestMarketInformationIndicator == false)
        {                            
            Properties tagValues = new Properties();
            tagValues.setProperty("CLASS", RetrieveServiceAreaByPostalCodeTrans.class.getName());
                            
            m_exBldReslt = ExceptionBuilder.parseException(
                    getLimBase().getCallerContext(),
                    m_limRuleFile,
                    "",
                    LIMTag.CSV_EditError,
                    "No operation requested",
                    true,
                    ExceptionBuilderRule.NO_DEFAULT,
                    null,
                    null,
                    getLimBase(),
                    "LIM",
                    Severity.UnRecoverable,
                    tagValues);                                                   
        }
        
        if (!aCingularSalesChannel.trim().equalsIgnoreCase("wow")
            && !aCingularSalesChannel.trim().equalsIgnoreCase("iwow"))
        {
            getLimBase().log(LogEventId.AUDIT_TRAIL, "RetrieveServiceAreaByPostalCodeReturn::editInputData()|CingularTableLookupService::isSellableToChannel()|PRE");
            boolean isSellable =
                CingularTableLookupService.isSellableToChannel(
                    getLimBase(),
                    (Properties) getLimBase().getPROPERTIES(),
                    aPostalCode,
                    aCingularSalesChannel);
            getLimBase().log(LogEventId.AUDIT_TRAIL, "RetrieveServiceAreaByPostalCodeReturn::editInputData()|CingularTableLookupService::isSellableToChannel()|POST");

            if(!isSellable)
            {
                throwNotAuthorizedToSellCingularException();
            }
        }
    }
    
    /**
     * Method findStateForZip.
     * @param aPostalCode
     * @return String
     * @throws ObjectNotFound
     * @throws NotImplemented
     * @throws DataNotFound
     * @throws AccessDenied
     * @throws SystemFailure
     * @throws InvalidData
     * @throws BusinessViolation
     */
    private String findStateForZip(String aPostalCode)
        throws 
            ObjectNotFound,
            NotImplemented,
            DataNotFound,
            AccessDenied,
            SystemFailure,
            InvalidData,
            BusinessViolation
    {
        String stateCode = null;
        
        try 
        {
            getLimBase().log(LogEventId.AUDIT_TRAIL, "RetrieveServiceAreaByPostalCodeReturn::findStateForZip()|RetrieveStateCodeByZip::retrieveStateCodeByZip()|PRE");
            stateCode = RetrieveStateCodeByZip
                .retrieveStateCodeByZip(
                    (Properties) getLimBase().getPROPERTIES(),
                    aPostalCode,
                    getLimBase());
            getLimBase().log(LogEventId.AUDIT_TRAIL, "RetrieveServiceAreaByPostalCodeReturn::findStateForZip()|RetrieveStateCodeByZip::retrieveStateCodeByZip()|POST");
        }
        catch( SQLException e )
        {
            //throwNotAuthorizedToSellCingularException();
    		if ( e.getMessage().indexOf("Exhausted Resultset") >= 0)
    		{
    			getLimBase().log (LogEventId.FAILURE, "RetrieveStateCodeByZip-Exhausted Resultset: " + e.getMessage());			
    		}
    		else
    		{
    			getLimBase().log (LogEventId.FAILURE, "RetrieveStateCodeByZip-SQL ERROR: " + e.getMessage());
    		}
        }
        
        if( stateCode == null || stateCode.trim().equals("") )
        {
            //throwNotAuthorizedToSellCingularException();
            stateCode = "";
        }
                
        return stateCode;
    }
    
    /**
     * Method throwNotAuthorizedToSellCingularException.
     * @throws ObjectNotFound
     * @throws NotImplemented
     * @throws DataNotFound
     * @throws AccessDenied
     * @throws SystemFailure
     * @throws InvalidData
     * @throws BusinessViolation
     */
    private void throwNotAuthorizedToSellCingularException()
        throws
            ObjectNotFound,
            NotImplemented,
            DataNotFound,
            AccessDenied,
            SystemFailure,
            InvalidData,
            BusinessViolation
    {
        ExceptionBuilder.parseException(
        getLimBase().getCallerContext(),
        m_limRuleFile,
        null,
        LIMTag.CSV_EditError,
        "Not authorized to sell Cingular",
        true,
        ExceptionBuilderRule.NO_DEFAULT,
        null,
        null,
        getLimBase(),
        "LIM",
        Severity.UnRecoverable,
        null);
    }

    /**
     * Method processInquireMarketServiceAreas. Calls the Atlas RC CSI 
     * InquireMarketServiceAreas API.
     * @param aPostalCode
     * @param serviceAreaReturn
     * @param bisContextManager
     * @param propertiesFromMessage
     * @return RetrieveServiceAreaByPostalCodeReturn
     * @throws SystemFailure
     * @throws InvalidData
     * @throws BusinessViolation
     * @throws ObjectNotFound
     * @throws MultipleExceptions
     * @throws AccessDenied
     * @throws NotImplemented
     * @throws DataNotFound
     */
    public RetrieveServiceAreaByPostalCodeReturn processInquireMarketServiceAreas(
            String aPostalCode,
            String aCingularSalesChannel,
            boolean aRequestServiceAreaIndicator,
            boolean aRequestMarketInformationIndicator,
            RetrieveServiceAreaByPostalCodeReturn serviceAreaReturn,
            LIMBisContextManager bisContextManager,
            Properties propertiesFromMessage)
        throws
            SystemFailure,
            InvalidData,
            BusinessViolation,
            ObjectNotFound,
            MultipleExceptions,
            AccessDenied,
            NotImplemented,
            DataNotFound
    {
        InquireMarketServiceAreasResponse imsaResponse = null;
        InquireMarketServiceAreasRequestHelper request = null;
        try
        {
            if (getService() == null)
            {
                setAtlasService();
            }
            getLimBase().log(LogEventId.REMOTE_CALL, ATLAS_MQ_BROKER, 
                    ATLAS_MQ_SERVICE,  ATLAS,
                    SERVICE_AREA_BY_POSTALCODE);
                                
            request = new InquireMarketServiceAreasRequestHelper (aPostalCode);
            
            imsaResponse = getService().sendInquireMarketServiceAreasRequests(request.getRequest(), bisContextManager.getConversationId(), propertiesFromMessage);
            
            getLimBase().log(LogEventId.REMOTE_RETURN, ATLAS_MQ_BROKER, 
                    ATLAS_MQ_SERVICE,  ATLAS,
                    SERVICE_AREA_BY_POSTALCODE);
        }
        catch (AtlasException e) 
        {
            handleAtlasException(e);
        }
        catch(AtlasSoapFaultException e)
        {
            handleAtlasSoapFaultException(e);
        }
        catch (ServiceException e) 
        {
            handleServiceException(e);
        }
        catch (Exception e) 
        {
            handleException(e);
        }
        
        boolean firstSavedLocation = false;
        boolean firstSavedLocal = false;
        
        int k = 0;
        String serviceAreaCode = "";
        ServiceArea[] serviceAreas = null;
        ServiceAreaLookupInfoImpl srvcAreaLkupInfo = null;
        ServiceAreaLookupInfoImpl.LocationTypeImpl locationType = null;
        ServiceAreaLookupInfoImpl.LocationTypeImpl saveLocationType = null;

        if (imsaResponse.getResponse().getCode().equals ("0") && 
            imsaResponse.getResponse().getDescription().equalsIgnoreCase ("success"))
        {
            try
            {
                if (imsaResponse.getZipcode() != null &&
                    imsaResponse.getZipcode().size() > 0)
                {
                    serviceAreaReturn.aPostalCode = (String) imsaResponse.getZipcode().get(0);
                }
                
                //T-5.1.LIMBIS.3.1.1.1.2 •  When more than one set of “ServiceAreas” objects are returned
                //from the AtlasRCAccess, LIM BIS shall perform as follows:
                //o  If multiple LocationMarketTypes are found, then LIM BIS shall return only the “first”
                //set of the BillingMarket, BillingSubMarket, and MarketDisplayName  where the 
                //LocationMarketType value is “Local” in the LIM BIS response.
                //o If only one LocationMarketType is found, then LIM BIS shall return only the “first”
                //set of the BillingMarket, BillingSubMarket, and MarketDisplayName  from that LocationMarketType
                //(Local, NBI or any other value) in the LIM BIS response
                if (imsaResponse.getServiceAreas() != null &&
                    imsaResponse.getServiceAreas().size() > 0)
                {
                    serviceAreas = new ServiceArea[imsaResponse.getServiceAreas().size()];
                    //i=ServiceAreaLookupInfo counter
                    for (int i=0; i < imsaResponse.getServiceAreas().size(); i++)
                    {
                        srvcAreaLkupInfo = (ServiceAreaLookupInfoImpl) imsaResponse.getServiceAreas().get(i);
                        if (srvcAreaLkupInfo.getLocation() != null &&
                            srvcAreaLkupInfo.getLocation().size() > 0)
                        {
                            serviceAreaCode = "";
                            //j=LocationType counter
                            for (int j=0; j < srvcAreaLkupInfo.getLocation().size(); j++)
                            {
                                locationType = (ServiceAreaLookupInfoImpl.LocationTypeImpl) srvcAreaLkupInfo.getLocation().get(j);
                                if (!firstSavedLocation)
                                {
                                    saveLocationType = locationType;
                                    firstSavedLocation = true;
                                }
                                if (locationType.getLocationMarketType().equalsIgnoreCase("Local"))
                                {
                                    if (!firstSavedLocal)
                                    {
                                        saveLocationType = locationType;
                                        firstSavedLocal = true;
                                    }
                                    serviceAreaCode = locationType.getServiceAreaCode();
                                    break;
                                }
                                if (j==0)
                                {
                                    serviceAreaCode = locationType.getServiceAreaCode();
                                }
                            }
                            serviceAreas[k++] = 
                                new ServiceArea(serviceAreaCode,
                                                srvcAreaLkupInfo.getServiceAreaDescription());
                        }
                    }
                }
                else
                {
                    Properties tagValues = new Properties();
                    tagValues.setProperty("MSG", "Unsuccessful response received (Response Description=" 
                        + imsaResponse.getResponse().getDescription() + ", Response Code =" + imsaResponse.getResponse().getCode());
                            
                    m_exBldReslt = ExceptionBuilder.parseException(
                        getLimBase().getCallerContext(),
                        m_cingularRuleFile,
                        "",
                        null,
                        null,
                        true,
                        ExceptionBuilderRule.NO_DEFAULT,
                        null,
                        null,
                        getLimBase(),
                        ATLAS,
                        Severity.UnRecoverable,
                        tagValues);  
                }
                
                if (aRequestServiceAreaIndicator == true)
                {
                    serviceAreaReturn.aServiceAreas.theValue(serviceAreas);
                }

                if (aRequestMarketInformationIndicator == true)
                {
                    // market info is optional, it's possible to get nothing back
                    MarketInformation marketInfo = new MarketInformation();
                    //T-5.1.LIMBIS.3.1.1.1.2 •  The Mobility BillingSystemIdentifier is no longer 
                    //available from the backend CSI Atlas starting in CSI v9.5 (v10) and future releases.
                    //The output BillingSystemIdentifier shall always be blank in the LIM BIS response interface
                    marketInfo.aBillingSystemId = " ";
                    marketInfo.aBillingMarket = saveLocationType.getMarket().getBillingMarket();
                    marketInfo.aBillingSubMarket = saveLocationType.getMarket().getBillingSubMarket();
                    marketInfo.aLocalMarket = IDLUtil.toOpt(saveLocationType.getMarket().getLocalMarket());
                    marketInfo.aMarketDisplayName = saveLocationType.getMarket().getMarketName();
    
                    //**Changes for CR 003 (WOW in SE Region)
                    //**T-5.1.003.LIMBIS.2 If the input CingularSalesChannel equals to WOW in the request,
                    //LIM BIS shall not use the ZC table look-up to derive the CAS Region Code. LIM BIS shall
                    //instead set the CustomerApprovalSystemRegion (CAS Region) value to blank and return
                    //the Service Area and/or Market information in the LIM BIS response
                    if (aCingularSalesChannel.trim().equalsIgnoreCase("wow"))
                    {
                        marketInfo.aCustomerApprovalSystemRegion = " ";
                    }
                    else
                    {
                    //**T-5.1.003.LIMBIS.3 If a State is not found for the given PostalCode in the ZC table,
                    //then LIM BIS shall set the CustomerApprovalSystemRegion (CAS Region) value to blank
                    //in the response.   
                        String aStateCode = null;
                        aStateCode = findStateForZip(aPostalCode);
                        if (aStateCode.trim().equalsIgnoreCase(""))
                        {
                            marketInfo.aCustomerApprovalSystemRegion = " ";
                        }
                        else
                        {
                            if(m_ctls == null)
                            {
                                m_ctls = new CingularTableLookupService();
                            }
                            getLimBase().log(LogEventId.AUDIT_TRAIL, "RetrieveServiceAreaByPostalCodeReturn::processInquireMarketByZip()|CingularTableLookupService::lookupCasRegionLookupServiceByState()|PRE");
                            marketInfo.aCustomerApprovalSystemRegion =
                                m_ctls.lookupCasRegionLookupServiceByState(
                                    getLimBase(),
                                    (Properties) getLimBase().getPROPERTIES(),
                                    aStateCode,
                                    saveLocationType.getMarket().getBillingMarket());
                            getLimBase().log(LogEventId.AUDIT_TRAIL, "RetrieveServiceAreaByPostalCodeReturn::processInquireMarketByZip()|CingularTableLookupService::lookupCasRegionLookupServiceByState()|POST");
                        }
                    }
                    serviceAreaReturn.aMarketInformation.theValue(marketInfo);
                }

                updateBisContext(propertiesFromMessage);
                serviceAreaReturn.aContext = getLimBase().getCallerContext();
            }
            catch (Exception e)
            {
                m_exBldReslt = ExceptionBuilder.parseException(
                    getLimBase().getCallerContext(),
                    m_cingularRuleFile,
                    "",
                    null,
                    e.getMessage(),
                    true,
                    ExceptionBuilderRule.NO_DEFAULT,
                    null,
                    null,
                    getLimBase(),
                    "LIM",
                    Severity.UnRecoverable,
                    null);  
            }         
        }
        else
        {
            Properties tagValues = new Properties();
            tagValues.setProperty("MSG", "Unsuccessful response received (Response Description=" 
                + imsaResponse.getResponse().getDescription() + ", Response Code =" + imsaResponse.getResponse().getCode());
                    
            m_exBldReslt = ExceptionBuilder.parseException(
                getLimBase().getCallerContext(),
                m_cingularRuleFile,
                "",
                null,
                null,
                true,
                ExceptionBuilderRule.NO_DEFAULT,
                null,
                null,
                getLimBase(),
                ATLAS,
                Severity.UnRecoverable,
                tagValues);  
        }
        return serviceAreaReturn;
    }
}
