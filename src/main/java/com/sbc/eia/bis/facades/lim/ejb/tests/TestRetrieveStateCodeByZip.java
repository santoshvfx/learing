// $Id: TestRetrieveStateCodeByZip.java,v 1.4 2003/06/04 23:33:57 rz7367 Exp $

package com.sbc.eia.bis.facades.lim.ejb.tests;

import java.util.Properties;

import com.sbc.bccs.idl.helpers.ObjectPropertyManager;
import com.sbc.bccs.utilities.PropertiesFileLoader;
import com.sbc.eia.bis.lim.util.LIMBase;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.bis_types.BisContextProperty;
import com.sbc.eia.idl.lim.queries.RetrieveStateCodeByZip;

/**
 * Test RetrieveStateCodeByZip.
 * Creation date: (4/17/01 2:27:36 PM)
 * @author: Donald W. Lee
 */
public class TestRetrieveStateCodeByZip extends LIMBase {
/**
 * Contruct a TestRetrieveStateCodeByZip object.
 * @param aContext com.sbc.eia.idl.bis_types_2_0_0.BisContext
 * @param aProperties java.util.Properties
 */
public TestRetrieveStateCodeByZip(BisContext aContext, Properties aProperties) {
	super.setCallerContext( aContext );
	super.setPROPERTIES( aProperties );
	initLIMBase();
}
/**
 * Starts the TestRetrieveStatCodeByZip transaction.
 * Creation date: (4/17/01 2:29:24 PM)
 * @param args java.lang.String[]
 */
public static void main(String[] args) 
{
	System.out.println("Retrieving a state code by zip... ");

	//String propertiesFile = "c:/deploy/limJdbc.properties";
	String propertiesFile = "c:/deploy/lim.properties";

	// use alternate properties file	
	if ( args.length > 0 ) {
		propertiesFile = args[0];
	}	
		
	ObjectPropertyManager opm = new ObjectPropertyManager();
	opm.add(BisContextProperty.APPLICATION,"LIM_BIS");
	opm.add(BisContextProperty.SERVICECENTER,"CA");
	BisContext aContext = new BisContext(opm.toArray()) ;

	Properties myProperty = new Properties();
 	
	try {
		// Load LIM properties for the LIMBase class..
		try{
			myProperty = PropertiesFileLoader.read(propertiesFile, null);
		}
		catch(Exception e){
			e.printStackTrace();
			System.out.println("Load Property file failed.");
		}
	
		System.out.println("Setup DB stuff... ");

		TestRetrieveStateCodeByZip aDbHelper = new TestRetrieveStateCodeByZip(aContext, myProperty);
		aDbHelper.retrieveStateCodeByZip( propertiesFile );

	}
	catch( Exception e ) {
		System.out.println("Main() - Exception caught...") ;
		e.printStackTrace();
	};
	
}
/**
 * Populate RetrieveStateCodeByZip fields.
 * Creation date: (4/16/01 11:08:36 AM)
 * @param aFileName String
 * @exception java.sql.SQLException The exception description.
 */
public void retrieveStateCodeByZip( String aFileName ) 
throws java.sql.SQLException
{
	//String fileName = "c:/deploy/lim.properties";
	String rslt = null;
	String aZip = "94583";
	String aStateCd = RetrieveStateCodeByZip.retrieveStateCodeByZip( aFileName, aZip, this ) ;
	System.out.println("aZip=" + aZip + " aStateCd = " + aStateCd);

	aZip = "63101";
	aStateCd = RetrieveStateCodeByZip.retrieveStateCodeByZip( aFileName, aZip, this ) ;
	System.out.println("aZip=" + aZip + " aStateCd = " + aStateCd);

}
}
