// $Id: TestRetrieveCommunityByZip.java,v 1.3 2003/03/25 23:20:44 as5472 Exp $

package com.sbc.eia.bis.facades.lim.ejb.tests;

import java.util.Properties;

import com.sbc.bccs.idl.helpers.ObjectPropertyManager;
import com.sbc.bccs.utilities.PropertiesFileLoader;
import com.sbc.eia.bis.lim.database.queries.RetrieveCommunityByZip;
import com.sbc.eia.bis.lim.util.LIMBase;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.bis_types.BisContextProperty;

/**
 * Test RetrieveCommunityByZip.
 * Creation date: (02/07/02 2:27:36 PM)
 * @author: David Brawley
 */
public class TestRetrieveCommunityByZip extends LIMBase {
/**
 * Contruct a TestRetrieveCommunityByZip object.
 * @param aContext com.sbc.eia.idl.bis_types.BisContext
 * @param aProperties java.util.Properties
 */
public TestRetrieveCommunityByZip(BisContext aContext, Properties aProperties) {
	super.setCallerContext( aContext );
	super.setPROPERTIES( aProperties );
	initLIMBase();
}
/**
 * Starts the TestRetrieveCommunityByZip transaction.
 * Creation date: (4/17/01 2:29:24 PM)
 * @param args java.lang.String[]
 */
public static void main(String[] args) 
{
	System.out.println("Retrieving community by zip... ");

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
		
		TestRetrieveCommunityByZip aDbHelper = new TestRetrieveCommunityByZip(aContext, myProperty);
		aDbHelper.retrieveCommunityByZip( myProperty );

	}
	catch( Exception e ) {
		System.out.println("Main() - Exception caught...") ;
		e.printStackTrace();
	};
	
}
/**
 * Populate RetrieveCommunityByZip fields.
 * Creation date: (4/16/01 11:08:36 AM)
 * @param aProperties  a Properties object
 * @exception java.sql.SQLException The exception description.
 */
public void retrieveCommunityByZip( Properties aProperties ) 
throws java.sql.SQLException
{
	//String fileName = "c:/deploy/lim.properties";
	String rslt = null;
	String aZip = "06119";
	String aCommuinity = RetrieveCommunityByZip.retrieveCommunityByZip( aProperties, aZip, this ) ;
	System.out.println("aZip=" + aZip + " aCommunity = " + aCommuinity);

	aZip = "06110";
	aCommuinity = RetrieveCommunityByZip.retrieveCommunityByZip( aProperties, aZip, this ) ;
	System.out.println("aZip=" + aZip + " aCommunity = " + aCommuinity);

}
}
