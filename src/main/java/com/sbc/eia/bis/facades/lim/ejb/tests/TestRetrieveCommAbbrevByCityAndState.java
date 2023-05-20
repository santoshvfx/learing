// $Id: TestRetrieveCommAbbrevByCityAndState.java,v 1.3 2003/03/25 23:20:45 as5472 Exp $

package com.sbc.eia.bis.facades.lim.ejb.tests;

import java.util.Properties;

import com.sbc.bccs.idl.helpers.ObjectPropertyManager;
import com.sbc.bccs.utilities.PropertiesFileLoader;
import com.sbc.eia.bis.lim.util.LIMBase;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.bis_types.BisContextProperty;
import com.sbc.eia.idl.lim.helpers.AddressHandlerException;

/**
 * Test TestRetrieveCommAbbrevbyCityAndState.
 * Creation date: (08/24/01 2:27:36 PM)
 * @author: Rachel Zadok
 */
public class TestRetrieveCommAbbrevByCityAndState extends LIMBase {
/**
 * Construct a TestRetrieveCommAbbrevByCityAndState object.
 * @param aContext com.sbc.eia.idl.bis_types_2_0_0.BisContext
 * @param aProperties java.util.Properties
 */
public TestRetrieveCommAbbrevByCityAndState(BisContext aContext, Properties aProperties) {
	super.setCallerContext( aContext );
	super.setPROPERTIES( aProperties );
	initLIMBase();
}
/**
 * Starts the TestRetrieveCommAbbrevByCityAndState transaction.
 * Creation date: (08/24/01 2:29:24 PM)
 * @param args java.lang.String[]
 */
public static void main(String[] args) 
{
	System.out.println("Retrieving Community Abbreviation by City and State ");

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
		
		TestRetrieveCommAbbrevByCityAndState aDbHelper = new TestRetrieveCommAbbrevByCityAndState (aContext, myProperty);
		// aDbHelper.retrieveDescriptiveData( propertiesFile );
		aDbHelper.retrieveCommAbbrevByCityAndState( myProperty );

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
public void retrieveCommAbbrevByCityAndState( String aFileName ) 
throws java.sql.SQLException
{
	/**
	 *	This method is no longer needed.
	 
	String city = "CHICAGO";
//  city="INDIANAPOLIS";
	String state = "IN";
	state = "IL";
	String commAbbrev = "";
	commAbbrev = RetrieveCommAbbrevByCityAndState.retrieveCommAbbrevByCityAndState 
		(aFileName, city, state, this);

	if (!commAbbrev.equals(""))
	{
		System.out.println ("No match was found for city <" + 
			city + "> and state <" + state + ">" );
	}
	else
		System.out.println ("The following commAbbrev<" + commAbbrev + "> was found for city<" +
			city + "> and state <" + state + ">");

		System.out.println ("No match was found for city <" + city + "> and state <" + state + ">" );
	*/
}
/**
 * Populate RetrieveCommAbbrevByCityAndState fields.
 * Creation date: (08/24/01 11:08:36 AM)
 * @param aProperties Properties
 * @exception java.sql.SQLException The exception description.
 * @exception AddressHandlerException
 */
public void retrieveCommAbbrevByCityAndState( Properties aProperties ) 
throws java.sql.SQLException, AddressHandlerException
{
	/**
	 *	The method is no longer needed...
	 	
//	String city = "CHICAGO";
	String city = "INDIANAPOLIS";
//  String city = "ARLINGTON";
	String state = "IN";
//	String state = "IL";
	String commAbbrev = null;
	comtyAndState.retrieveCommAbbrevByCityAndState 
		(aProperties, city, state, this);

	if (commAbbrev != null)
	{
		System.out.println ("The following Community Abbreviation was returned:" + commAbbrev );
	}
	else
		System.out.println ("No matching addresses were found." );
	****/
	
}
}
