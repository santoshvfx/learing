//$Id: TestLimAddressCache.java,v 1.6 2008/02/29 23:27:20 jd3462 Exp $
package com.sbc.eia.bis.facades.lim.ejb.tests;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

import com.sbc.bccs.idl.helpers.ObjectPropertyManager;
import com.sbc.bccs.utilities.PropertiesFileLoader;
import com.sbc.eia.bis.framework.logging.BisLogger;
import com.sbc.eia.bis.lim.transactions.RetrieveLocation.RetrieveLocationException;
import com.sbc.eia.bis.lim.util.LIMBase;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.bis_types.BisContextProperty;
import com.sbc.eia.idl.bis_types.SystemFailure;
import com.sbc.eia.idl.types.ExceptionData;
import com.sbc.eia.idl.types.Severity;
import com.sbc.eia.idl.types.SeverityOpt;
import com.sbc.eia.idl.types.StringOpt;
import com.sbc.logging.LogAssistant;
import com.sbc.logging.LogAssistantFactory;
import com.sbc.logging.message.MessageFactory;

/**
 * @author as2362
 *
 * Tests database calls to LIM_ADDRESS_CACHE table.
 * Creation date: (2/19/03 9:26:35 AM)
 */
public class TestLimAddressCache extends LIMBase
{

    /**
     * Contruct a TestRetrieveStateCodeByZip object.
     * @param aContext com.sbc.eia.idl.bis_types_2_0_0.BisContext
     * @param aProperties java.util.Properties
     */
    public TestLimAddressCache(BisContext aContext, Properties aProperties) 
    {
    	super.setCallerContext( aContext );
    	super.setPROPERTIES( aProperties );
    	initLIMBase();
    }
    
    /**
     * Main method for unit test
     */
    public static void main(String[] args) 
        throws 
            SQLException, 
            RetrieveLocationException, 
            SystemFailure
    {
    	String clientServiceCenter   = "CA" ;
    	String logInfoStr            = "LIM4.3";
    	String subSystemName = "4.3.0";
    	String clientLogFile  = "c:/sbc/bis/limClient.log" ;
    	String propertiesFile = "c:/deploy/lim.properties";
    	
    	String errorDescription = null;
    	StringOpt errorOrigination = null;
    	SeverityOpt errorSeverity = null;
    	LogAssistant logAssist = null;
    	logAssist = LogAssistantFactory.create( logInfoStr, subSystemName );
    	logAssist.log( MessageFactory.create( "" ) ) ;;
    
    	ObjectPropertyManager opm = new ObjectPropertyManager();
    	opm.add(BisContextProperty.APPLICATION,"LIM_BIS");
    	opm.add(BisContextProperty.CUSTOMERNAME,"LIMTest");
    	opm.add(BisContextProperty.SERVICECENTER, clientServiceCenter);
    	opm.add( BisContextProperty.LOGGINGINFORMATION, logAssist.getCorrID()) ;
    
    	BisContext bisContext = new BisContext(opm.toArray());
    	Properties p = new Properties();
    	
    	try 
        {
    		p = PropertiesFileLoader.read(propertiesFile, null)		;
    	}
    	catch(IOException ioe)
        {
    		ioe.printStackTrace();
    		errorDescription = "Error Loading Properties. <" + propertiesFile + ">" ;
    		errorOrigination.theValue("LIM TestClient") ;
    		errorSeverity.theValue(Severity.from_int(1));
    		throw new SystemFailure(new BisContext(), new ExceptionData("9999",errorDescription,
    			                    errorOrigination,errorSeverity));
    	}
    
    	TestLimAddressCache limCache = new TestLimAddressCache(bisContext, p);
    	
    	BisLogger logG = new BisLogger(logInfoStr, subSystemName);
    	
    	// insert some values into the table
    	// NOTE: Insert will fail if values already in the test table
    	String houseNumber = "68";
    	String houseNumber2 = "22";
    	String streetName = "MCG*UIRE";
    	String streetName2 = "the' emcadero";
    	String city = "DUBLIN";
    	String city2 = "SF";
    	String svcId = "9259018048";
    	String svcId2 = "";
    /*********************************************		
    	RetrieveLocation rl = null;	
    	Time startTime = null,endTime = null;
    	RetrieveLocationHandler rlh = new RetrieveLocationHandler();
    	RetrieveLocationHandler rlh2 = null;
    	// use handler to generate test RetrieveLocation obj
    	rl = rlh.createRetrieveLocation(houseNumber, streetName, city);
    
    	
    	try{
    		System.out.println("first insert..");
    		startTime = new Time(System.currentTimeMillis());
    		System.out.println(startTime.toString());
    		LimAddressCache.setRetrieveLocationCache((Properties)limCache.getPROPERTIES(),
    									svcId,
    									rl,
    									logG);
    		endTime = new Time(System.currentTimeMillis());
    		System.out.println(endTime.toString());
    	
    
    		System.out.println("second insert..");
    	
    		//RetrieveLocationHandler rlh = new RetrieveLocationHandler();
    		rl = rlh.createRetrieveLocation(houseNumber2, streetName2, city2);
    		
    		startTime = new Time(System.currentTimeMillis());
    		System.out.println(startTime.toString()); 
    		LimAddressCache.setRetrieveLocationCache((Properties)limCache.getPROPERTIES(),
    									svcId2,
    									rl,
    									logG);
    		endTime = new Time(System.currentTimeMillis());
    		System.out.println(endTime.toString());
    	}catch (Exception e) {
    		System.out.println("Exception caught during insert: " + e.getMessage());
    	}
    	
    
    	try{
    		System.out.println("Now retrieve the blob by locationId");
    		rlh2 = new RetrieveLocationHandler(rl);
    	
    		startTime = new Time(System.currentTimeMillis());
    		System.out.println(startTime.toString()); 
    		RetrieveLocationForAddressReturn addressRes = LimAddressCache.retrieveLocationCacheByLocId((Properties)limCache.getPROPERTIES(),rlh2.getAddressKey(),
    		bisContext, logG);
    		endTime = new Time(System.currentTimeMillis());
    		System.out.println(endTime.toString());
    	
    		if(addressRes != null)
    		{
    			System.out.println("The retrieved HouseNumber is: ");
    			System.out.println(addressRes.aRetrieveLocation.aExactMatch().aLocation.aAddress.theValue().aFieldedAddress().aHouseNumber.theValue());
    	
    			System.out.println("The retrieved HNumberSuffix is: ");
    			System.out.println(addressRes.aRetrieveLocation.aExactMatch().aLocation.aAddress.theValue().aFieldedAddress().aHouseNumberSuffix.theValue());
    	
    			System.out.println("The retrieved streetname is: ");
    			System.out.println(addressRes.aRetrieveLocation.aExactMatch().aLocation.aAddress.theValue().aFieldedAddress().aStreetName.theValue());
    		}else
    			System.out.println("LocationId <"+rlh.getAddressKey()+"> not found");
    			
    
    		System.out.println("Now retrieve the blob by serviceId");
    	
    		startTime = new Time(System.currentTimeMillis());
    		System.out.println(startTime.toString());
    		RetrieveLocationForServiceReturn serviceRes = LimAddressCache.retrieveLocationCacheBySvcId((Properties)limCache.getPROPERTIES(),svcId,
    		bisContext, logG);
    		endTime = new Time(System.currentTimeMillis());
    		System.out.println(endTime.toString());
    	
    		if(serviceRes != null)
    		{
    			System.out.println("The retrieved City is: ");
    			System.out.println(serviceRes.aRetrieveLocation.aExactMatch().aLocation.aAddress.theValue().aFieldedAddress().aCity.theValue());
    	
    			System.out.println("The retrieved zipcode is: ");
    			System.out.println(serviceRes.aRetrieveLocation.aExactMatch().aLocation.aAddress.theValue().aFieldedAddress().aPostalCode.theValue());
    	
    			System.out.println("The retrieved Country is: ");
    			System.out.println(serviceRes.aRetrieveLocation.aExactMatch().aLocation.aAddress.theValue().aFieldedAddress().aCountry.theValue());
    		}else
    			System.out.println("ServiceId <"+svcId+"> not found");
    	
    	}catch(Exception e){
    		System.out.println("Exception caught during retrieve: " + e.getMessage());
    	}
        ****************/	
    } 	
}
