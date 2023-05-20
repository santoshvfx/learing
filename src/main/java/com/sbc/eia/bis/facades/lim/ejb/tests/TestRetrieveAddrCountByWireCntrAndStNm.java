//$Id: TestRetrieveAddrCountByWireCntrAndStNm.java,v 1.4 2008/02/29 23:27:20 jd3462 Exp $
package com.sbc.eia.bis.facades.lim.ejb.tests;

import java.util.Properties;

import com.sbc.bccs.idl.helpers.ObjectPropertyManager;
import com.sbc.bccs.utilities.PropertiesFileLoader;
import com.sbc.eia.bis.lim.database.queries.RetrieveAddrCountByWireCntrAndStNm;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.bis_types.BisContextProperty;

/**
 * Test TestRetrieveAddrCountByWireCntrAndStNm.
 * Creation date: (08/24/01 2:27:36 PM)
 * @author: Rachel Zadok
 */
public class TestRetrieveAddrCountByWireCntrAndStNm extends com.sbc.eia.bis.lim.util.LIMBase 
{
    /**
     * Construct a TestRetrieveAddrCountByWireCntrAndStNm object.
     * @param aContext com.sbc.eia.idl.bis_types_2_0_0.BisContext
     * @param aProperties java.util.Properties
     */
    public TestRetrieveAddrCountByWireCntrAndStNm(BisContext aContext, Properties aProperties) 
    {
    	super.setCallerContext( aContext );
    	super.setPROPERTIES( aProperties );
    	initLIMBase();
    }
    /**
     * Starts the TestRetrieveAddrCountByWireCntrAndStNm transaction.
     * Creation date: (08/24/01 2:29:24 PM)
     * @param args java.lang.String[]
     */
    public static void main(String[] args) 
    {
    	System.out.println("Retrieving Addresses Count by Street Name and Wire Center ");
    
    	//String propertiesFile = "c:/deploy/limJdbc.properties";
    	String propertiesFile = "c:/deploy/lim.properties";
    
    	// use alternate properties file	
    	if ( args.length > 0 ) {
    		propertiesFile = args[0];
    	}
    	
    	ObjectPropertyManager opm = new ObjectPropertyManager();
    	opm.add(BisContextProperty.APPLICATION,"LIM_BIS");
    	opm.add(BisContextProperty.SERVICECENTER,"CT");
    	BisContext aContext = new BisContext(opm.toArray()) ;
    
    	Properties myProperty = new Properties();
     	
    	try 
        {
    		// Load LIM properties for the LIMBase class..
    		try
            {
    			myProperty = PropertiesFileLoader.read(propertiesFile, null);
    		}
    		catch(Exception e)
            {
    			e.printStackTrace();
    			System.out.println("Load Property file failed.");
    		}
    	
    		System.out.println("Setup DB stuff... ");
    
    		TestRetrieveAddrCountByWireCntrAndStNm aDbHelper = new TestRetrieveAddrCountByWireCntrAndStNm (aContext, myProperty);
    		aDbHelper.retrieveAddrCountByWireCntrAndStNm( myProperty );
    
    	}
    	catch( Exception e ) 
        {
    		System.out.println("Main() - Exception caught...") ;
    		e.printStackTrace();
    	};
    	
    }
    
    /**
     * Populate RetrieveAddrCountByWireCntrAndStNm fields.
     * Creation date: (08/24/01 11:08:36 AM)
     * @param aFileName java.lang.String
     * @exception java.sql.SQLException The exception description.
     */
    public void retrieveAddrCountByWireCntrAndStNm( String aFileName ) 
        throws java.sql.SQLException
    {
    	String wireCenter = "203329";
    	String streetName = "buckingham dr";
    	String houseNumber = "90";
    	String city = "STAM";
    	
    	int addressCount = RetrieveAddrCountByWireCntrAndStNm.retrieveAddrCountByWireCntrAndStNm 
    		(aFileName, wireCenter, streetName, houseNumber, city, this);
    
    	System.out.println ("The number of addresses found were: " + addressCount );
    }
    
    /**
     * Populate RetrieveAddrCountByWireCntrAndStNm fields.
     * Creation date: (08/24/01 11:08:36 AM)
     * @param aProperties Properties
     * @exception java.sql.SQLException The exception description.
     */
    public void retrieveAddrCountByWireCntrAndStNm( Properties aProperties ) 
        throws java.sql.SQLException
    {
    	String wireCenter = "203329";
    	String streetName = "buckingham dr";
    	String houseNumber = "90";
    	String city = "STAM";
    	
    	int addressCount = RetrieveAddrCountByWireCntrAndStNm.retrieveAddrCountByWireCntrAndStNm 
    		(aProperties, wireCenter, streetName, houseNumber, city, this);
    
    	System.out.println ("The number of addresses found were: " + addressCount );
    		
    }
}