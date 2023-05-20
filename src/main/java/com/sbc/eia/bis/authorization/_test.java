package com.sbc.eia.bis.authorization;

import java.util.Properties;
import java.util.Hashtable;
import com.sbc.eia.idl.types.*;
import com.sbc.eia.idl.bis_types.*;
import com.sbc.bccs.utilities.*;
import java.util.Properties;

/**
 * @author vc7563
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */


public class _test implements Logger {
	public static class Data implements Runnable {
		private int id = 0;
		private BisContext context;
		private Logger logger;

		private String methodName;
		private String application;
		private String businessUnit;
		private String company;
		private String state;
		private String groupId;
		private String bisOwner;
		private Hashtable dataSet;
		
		private Data() {};
		private Data (	String aMethodName, 
						String anApplication, 
						String aBusinessUnit, 
					 	String aState,
					 	String aGroupId,
					 	String aBisOwner,
					 	Hashtable aDataSet) 
		{
					
			methodName = aMethodName;
			application = anApplication;
			businessUnit = aBusinessUnit;
			state = aState;
			groupId = aGroupId;
			bisOwner = aBisOwner;
			dataSet = aDataSet;
		}
		
		public void run()
		{
			try {
				logger.log("CLIENT<"+id+">", "Testing: <" + 
						methodName + "> <" + 
						application + "> <" + 
						businessUnit + "> <" +
						state + "> <" + 
						groupId + "> <" +
						bisOwner + ">" );
				try {		
				if ( ClientAuthorization.isAuthorized(
								methodName,
								application,
								businessUnit,
								state, 
								groupId,
								bisOwner,
								dataSet,
								logger) == true )
				{
					System.out.println( "CLIENT<"+ id + ">Authorized User");
				}
				else
				{
					System.out.println( "CLIENT<"+ id + ">Unauthorized User");
				}
				}
				catch( AuthorizationException e )
				{
				};
			}
			catch (Exception e ) 
			{
				e.printStackTrace();
				System.out.println ("Exception: " + e.getMessage());
			}
		}
	}
/**
 * Void constructor.
 */
private _test() {}
/**
 * Implementation of Logger.log(String, String).
 * @param aLevel java.lang.String
 * @param aText java.lang.String
 */
public void log(String aLevel, String aText)
{
	System.out.println(System.currentTimeMillis() + ":" + aLevel + ":" + aText);
}

public void log(String s1, String s2, String s3, String s4){}

public void log(String s1, String s2, String s3, String s4, String s5){}

/**
 * main.
 * Creation date: (2/5/02 3:56:45 PM)
 * @param args java.lang.String[]
 */
public static void main(String[] args) {
	_test aTest = new _test();
	
	Hashtable props = new Hashtable();
	
	//props.put("BIS_AUTHORIZATION_DURATION", "60000");  // one minute in milliseconds
	props.put("AUTHORIZATION_TIMEOUT", "1"); 
	props.put("jdbcDATA_SOURCE_NAME", "jdbc/NAMDataSource");
	props.put( "jdbcUSERID", "rmnadmin");
	props.put( "jdbcPASSWORD", "rmnadmin" );
	props.put( "jdbcDRIVER", "oracle.jdbc.driver.OracleDriver");
	props.put( "jdbcUSE_CONNECTION_POOL", "BOTH");
	props.put( "jdbcURL", "jdbc:oracle:thin:@cid51.sbc.com:1521:ostl314");
	props.put( "AUTHORIZATION_TABLE", "BIS_AUTHORIZATION" );
	//props.put( "AUTHORIZATION_INITIAL_LOAD", "");
		
	ObjectProperty[] anObjectProperty =
		new ObjectProperty[] {
			new ObjectProperty(BisContextProperty.SERVICECENTER, "in"),
			new ObjectProperty(BisContextProperty.CUSTOMERNAME, "cm2159")};
	BisContext aContext = new BisContext(anObjectProperty);


	Data[] data = new Data[] 
	{
		new Data ("RetrieveFeatures", "EBTA", "ASI", "ABC", "123", "RM", props),
		new Data ("RetrieveFeatures", "EBTA", "", "", "", "RM", props),
		new Data ("RetrieveFeatures", "EBTA", "ASI", "CA", null, "RM", props),
		new Data ("RetrieveFeatures", "EBTA", "", "CA", "", "RM", props),
		new Data ("LoopQual", "WSC", "ASI", "CA", null, "RM", props),
		new Data ("RetrieveFeatures", "EBTA", "ASI", "CA", "", "RM", props),
		new Data ("LoopQual", "WSC", "ASI", "MI", null, "RM", props),
		new Data ("RetrieveFeatures", "EBTA", "ASI", "TX", "", "RM", props),
		new Data ("RetrieveFeatures", "EBTA", "ASI", "CA", "", "RM", props),
		new Data ("RetrieveFeatures", "EBTA", "ASI", "",  "", "RM", props),
		new Data ("RetrieveFeatures", "EBTA", "", "CA", "", "RM", props),
		new Data ("RetrieveFeatures", "TEST", "", "", "", "RM", props),
		new Data ("RetrieveFeatures", "EBTA", "ASI", "CA", "", "RM", props)
	};

	// Single threaded	
/*	for (int i = 0; i < data.length; i++)
	{
		data[i].id= i;
		data[i].context= aContext;
		data[i].logger= aTest;

		aTest.log("CLIENT:", "Singlethread Running " + data[i].id);
		data[i].run();	
	}
*/
	// Multi threaded
	Thread threads[] = new Thread[data.length];
	for (int j = 0; j < data.length; j++)
	{	
		data[j].id = j;
		data[j].context= aContext;
		data[j].logger = aTest;
		
		threads[j] = new Thread(data[j]);
	}
	for (int j = 0; j < data.length; j++)
	{
		aTest.log("CLIENT:", "Multithread Running " + data[j].id);
		try {
			threads[j].sleep(1000);
		}
		catch (InterruptedException e)
		{
		} 
		threads[j].start();
	}

}
}
	
	
