// $Id: LIMTag.java,v 1.39 2008/02/29 22:48:40 gg2712 Exp $

package com.sbc.eia.bis.lim.util;

/**
 * Creation date: (4/8/02 3:53:11 PM)
 * @author: Donald W. Lee
 */
public interface LIMTag {

	
	/**
	 *  Reference by CSV Rules File.
	 */
	public static final String CSV_FileName_ASON       = "EXCEPTION_BUILDER_ASON_RULE_FILE" ;
	public static final String CSV_FileName_BRMS       = "EXCEPTION_BUILDER_BRMS_RULE_FILE" ;
	public static final String CSV_FileName_LIM        = "EXCEPTION_BUILDER_LIM_RULE_FILE" ;
	public static final String CSV_FileName_OVALS      = "EXCEPTION_BUILDER_OVALS_RULE_FILE" ;
	public static final String CSV_FileName_OVALS_USPS = "EXCEPTION_BUILDER_OVALS_USPS_RULE_FILE" ;
	public static final String CSV_FileName_OVALS_NSP  = "EXCEPTION_BUILDER_OVALS_NSP_RULE_FILE" ;
	public static final String CSV_FileName_PREMIS     = "EXCEPTION_BUILDER_PREMIS_RULE_FILE" ;
	public static final String CSV_FileName_CINGULAR   = "EXCEPTION_BUILDER_CINGULAR_RULE_FILE" ;
    public static final String CSV_FileName_LIM_XNG    = "EXCEPTION_BUILDER_XNG_RULE_FILE" ;
    public static final String CSV_FileName_RSAG	   = "EXCEPTION_BUILDER_RSAG_RULE_FILE" ;
    public static final String CSV_FileName_BCAM	   = "EXCEPTION_BUILDER_BCAM_RULE_FILE" ;
    
    /**
     *  Reference by retrieveLocationForAddress Trans
     */
    public static final String DATA_SERVICES           = "DataServices" ;
    
    /**
     *  For Soap envelop used by Sonic MQ
     */
    public static final String SOAP_ENVELOP_HEADER   = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>" + 
                    "\n<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:soapenc=" + 
                    "\"http://schemas.xmlsoap.org/soap/encoding/\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\"" +
                    " xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\n<soapenv:Body>" ;
    public static final String SOAP_ENVELOP_FOOTER   = "\n</soapenv:Body></soapenv:Envelope>";
		
	public static final String CSV_LimTimedOutCode     	= "TIME_OUT" ;	
	public static final String CSV_OracleError     	 	= "ORACLE_ERR" ;
	public static final String CSV_AddressError        	= "ADDR_ERR" ;
	public static final String CSV_AddressHandlerError 	= "ADDR_PARSER" ;
	public static final String CSV_TnServiceError      	= "SVC_ERR" ;
	public static final String CSV_InternalError       	= "LIM_ERR" ;
	public static final String CSV_EditError           	= "EDIT_ERR" ;
	public static final String CSV_OvalsError          	= "OVALS_ERR" ;
	public static final String CSV_SonicMQError       	= "SONICMQ_ERR" ;
	public static final String CSV_RCodeError 		 	= "RCODE_ERR";
	public static final String CSV_XNGError 		    = "XNG_ERR";
	public static final String CSV_JAXBError 		 	= "JAXB_ERR"; 
    
    /**
     *  Reference by LimMessageDrivenBean for jndi name key of jms/addressValidation
     */
    public static final String ADDRESS_VALIDATION_QUEUE_NAME_KEY        = "ADDRESS_VALIDATION_QUEUE_NAME" ;
    
    public static final int EXACT_MATCH_REQ            = -1 ;
    
    /**
     *  Reference by ClientAuthorization for database properties
     */
    public static final String AUTHORIZATION_TIMEOUT_KEY        = "AUTHORIZATION_TIMEOUT" ;
    public static final String AUTHORIZATION_TIMEOUT_VALUE      = "1" ;
    public static final String DATA_SOURCE_NAME_KEY             = "jdbcDATA_SOURCE_NAME" ;
    public static final String USERID_KEY                       = "jdbcUSERID" ;
    public static final String PASSWORD_KEY                     = "jdbcPASSWORD" ;
    public static final String DRIVER_KEY                       = "jdbcDRIVER" ;
    public static final String USE_CONNECTION_POOL_KEY          = "jdbcUSE_CONNECTION_POOL" ;
    public static final String URL_KEY                          = "jdbcURL" ;
    public static final String AUTHORIZATION_TABLE_KEY          = "AUTHORIZATION_TABLE" ;
    public static final String AUTHORIZATION_TABLE_VALUE        = "LIM_BIS_AUTHORIZATION" ;
    public static final String METHOD_CLIENT_AUTHORIZATION_FOR  = "retrieveLocationForAddress" ;
    public static final String OSS_PUBLIC_KEY                   = "OSS_PUBLIC_KEY";
    public static final String GIS_PSWD                         = "GIS_PSWD";
    public static final String BRMS_PASSWORD                    = "BRMS_PASSWORD";
    
	/**
	 * Reference for  Routing	OverrideParser-Removed
	 
	public static final String AUTHORIZATION_OVERRIDE			= "BIS_OVERRIDE";
	public static final String ASON_APPLDATA					= "ASONSERVICE_APPLDATA";
	public static final String PREMIS_APPLDATA					= "PREMISSERVICE_APPLDATA";
	public static final String BRMS_APPLDATA					= "SAG01F_APPLDATA";
	*/
    /**
     *  Misc tag values.
     */
    public static final String EMPTY_STRING  = "" ;
    public static final String EQUALTO  = "=" ;
    
    /**
     * Temporary tags until lim_types.idl is updated
     */
    public static final String COUNTYID			= "CountyId";   
   	public static final String LATITUDE			= "Latitude";
   	public static final String LONGITUDE			= "Longitude";  
   	
   	/*
   	 * IDL 45_0 misses these new fields in 
   	 * com.sbc.eia.idl.lim_types.LocationPropertiesToGetValue.java
   	 * Temporary put these new fields in here until new IDL is released  
   	 */
   	public static final String ADDRESSMATCHCODE = "AddressMatchCode";
    public static final String ADDRESSMATCHCODEDESCRIPTION = "AddressMatchCodeDescription";
    public static final String RATEZONE = "RateZone";
    public static final String SAGADDRESS = "SAGAddress";
    public static final String BUILDINGTYPE = "BuildingType";
    public static final String LEGALENTITY = "LegalEntity";
    public static final String VIDEOHUBOFFICE = "VideoHubOffice";
    public static final String SMARTMOVES = "Smartmoves";
    public static final String SERVINGNETWORKTYPE = "ServingNetworkType";
    public static final String LOCATIONTYPE = "LocationType";
    public static final String CROSSBOUNDARYSTATE = "CrossBoundaryState";
    public static final String INDEPENDENTCOMPANYINDICATOR = "IndependentCompanyIndicator";
    public static final String AREATRANSFERCUTDATE = "AreaTransferCutDate";
    public static final String AREATRANSFERNUMBERCHANGEDATE = "AreaTransferNumberChangeDate";
    public static final String AREATRANSFERNPANXX = "AreaTransferNpaNxx";
    public static final String AREATRANSFERWIRECENTER = "AreaTransferWireCenter";
    public static final String WIRECENTER = "WireCenter";
    public static final String CONNECTTHROUGH = "ConnectThrough";
    public static final String EXTENSIONS = "Extensions";
    
    //CATALYST/CBS 
	public static final String CATALYST_ENVIRONMENT = "CATALYST_ENVIRONMENT";
	public static final String CATALYST_ENVIRONMENT_TAG = "catalyst.environment";
	public static final String CATALYST_ENVIRONMENT_DEV = "DEV";
	public static final String CATALYST_ENVIRONMENT_PRD = "PRD";
	public static final String USE_RSAG_PRODUCTION_ENVIRONMENT = "UseRsagProductionEnvironment";
	
	//BCAM
	public static final String BCAM_SOURCE_APPLICATION_CODE = "BCAM_SOURCE_APPLICATION_CODE";
	
	//BIMX
	public static final String BIMX_INTF_LOOKUP_ERROR = "Error getting BIMX interface";
	public static final String LIM_ORIGINATION = "LIM";
}
