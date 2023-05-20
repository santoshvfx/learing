// $Id: TestRetrieveClecNameByExchAndCoId.java,v 1.3 2003/03/25 23:20:45 as5472 Exp $

package com.sbc.eia.bis.facades.lim.ejb.tests;

import java.util.Properties;

import com.sbc.bccs.idl.helpers.ObjectPropertyManager;
import com.sbc.bccs.utilities.PropertiesFileLoader;
import com.sbc.eia.bis.lim.database.queries.RetrieveClecNameByExchAndCoId;
import com.sbc.eia.bis.lim.util.LIMBase;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.bis_types.BisContextProperty;
import com.sbc.eia.idl.lim.helpers.AddressHandlerException;

/**
 * Test TestRetrieveAddrByDescriptiveAndState.
 * Creation date: (08/24/01 2:27:36 PM)
 * @author: Rachel Zadok
 */
public class TestRetrieveClecNameByExchAndCoId extends LIMBase {
/**
 * Construct a TestRetrieveClecNameByExchAndCoId object.
 * @param aContext com.sbc.eia.idl.bis_types_2_0_0.BisContext
 * @param aProperties java.util.Properties
 */
public TestRetrieveClecNameByExchAndCoId(BisContext aContext, Properties aProperties) {
	super.setCallerContext( aContext );
	super.setPROPERTIES( aProperties );
	initLIMBase();
}
/**
 * Starts the TestRetrieveClecNameByExchAndCoId transaction.
 * Creation date: (08/24/01 2:29:24 PM)
 * @param args java.lang.String[]
 */
public static void main(String[] args) 
{
	System.out.println("Retrieving Clec Name by Exch and Co Id ");

	//String propertiesFile = "c:/deploy/limJdbc.properties";
	String propertiesFile = "c:/sbc/bis/lim.properties";

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
		
		TestRetrieveClecNameByExchAndCoId aDbHelper = new TestRetrieveClecNameByExchAndCoId (aContext, myProperty);		// aDbHelper.retrieveClecNameByExchAndCoId( propertiesFile );
		aDbHelper.retrieveClecNameByExchAndCoId( myProperty );

	}
	catch( Exception e ) {
		System.out.println("Main() - Exception caught...") ;
		e.printStackTrace();
	};
	
}
/**
 * Populate RetrieveCommAbbrevByCityAndState fields.
 * Creation date: (08/24/01 11:08:36 AM)
 * @param aFileName java.lang.String
 * @exception java.sql.SQLException The exception description.
 */
public void retrieveClecNameByExchAndCoId( String aFileName ) 
throws java.sql.SQLException
{
	String exch = "AM";
	String coId = "PA";
	
//	String exch = "AZU";
//	String coId = "IN";
	
//  String exch = "GY";
//	String coId = "EV";

	String clecName = null;
	
	clecName = RetrieveClecNameByExchAndCoId.retrieveClecNameByExchAndCoId 
		(aFileName, exch, coId, this);

	if (clecName != null)
	{
		System.out.println ("The following ClecName was returned:" + clecName );
	}
	else
		System.out.println ("No match was found for Exch < " + exch + "> and Co Id <" + coId + ">" );
}
/**
 * Populate RetrieveClecNameByExchAndCoId fields.
 * Creation date: (08/24/01 11:08:36 AM)
 * @param aProperties Properties
 * @exception java.sql.SQLException The exception description.
 * @exception AddressHandlerException
 */
public void retrieveClecNameByExchAndCoId( Properties aProperties ) 
throws java.sql.SQLException, AddressHandlerException
{
	String exch = "AM";
	String coId = "PA";
	
//	String exch = "AZU";
//	String coId = "IN";
	
//  String exch = "GY";
//	String coId = "EV";

	String clecName = null;
	clecName = RetrieveClecNameByExchAndCoId.retrieveClecNameByExchAndCoId 
		(aProperties, exch, coId, this);

	if (clecName != null)
	{
		System.out.println ("The following ClecName was returned:" + clecName );
	}
	else
		System.out.println ("No match was found for Exch < " + exch + "> and Co Id <" + coId + ">" );
		
}
}
