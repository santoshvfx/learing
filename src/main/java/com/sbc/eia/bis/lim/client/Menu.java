//$Id: Menu.java,v 1.16 2009/02/03 18:43:33 jv7958 Exp $
package com.sbc.eia.bis.lim.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import com.sbc.eia.bis.lim.util.LIMIDLPrintFormatter;
import com.sbc.eia.idl.lim.LimFacadePackage._fieldAddressRequest;
import com.sbc.eia.idl.lim.LimFacadePackage._retrieveLocationForTelephoneNumberRequest;
import com.sbc.eia.idl.lim.LimFacadePackage._retrieveLocationForE911AddressRequest;
import com.sbc.eia.idl.lim.LimFacadePackage._retrieveLocationForGeoAddressRequest;
import com.sbc.eia.idl.lim.LimFacadePackage._retrieveLocationForPostalAddressRequest;
import com.sbc.eia.idl.lim.LimFacadePackage._retrieveLocationForPostalAddress2Request;
import com.sbc.eia.idl.lim.LimFacadePackage._retrieveLocationForServiceAddressRequest;
import com.sbc.eia.idl.lim.LimFacadePackage._updateBillingAddressRequest;
import com.sbc.eia.idl.lim.LimFacadePackage._pingRequest;
import com.sbc.eia.idl.lim.LimFacadePackage._retrieveLocationForAddressRequest;
import com.sbc.eia.idl.lim.LimFacadePackage._retrieveLocationForServiceRequest;
import com.sbc.eia.idl.lim.LimFacadePackage._retrieveServiceAreaByPostalCodeRequest;
import com.sbc.eia.idl.lim.LimFacadePackage._selfTestRequest;

/**
 * @author gg2712
 */
public class Menu
{
	public static final String TEST_FILE_KEY = "TEST_FILE";
	public static final String AUTO_RUN_KEY = "AUTO_RUN";
	public static final String CLIENT_LOGFILE_KEY = "CLIENT_LOGFILE";
	private Date today = null;
	public static final int EXIT = 999;
	public static final int RELOAD = 998;
	public static final int SHOW_ALL_TESTCASES =996 ;
	protected String m_limVersion = "LIM";
	protected LimClient m_limClient = null;
	protected TestCaseHelper m_testCaseHelper = null;
	protected boolean m_autoRun = false;
	protected TestLogger m_logger = null;
	protected LIMIDLPrintFormatter m_formatter = new LIMIDLPrintFormatter(); 
	final int MIN_NUMBER_OF_MENU_CHOICES = 50;
	final int MAX_NUMBER_OF_MENU_CHOICES = 799;
	protected int displayNumberOfMenuItems=0;
	private Calendar calendar = null;
	private SimpleDateFormat formatter =
			new SimpleDateFormat("yyyyMMdd.HH:mm:ss.SSS");
	/**
	 * Constructor
	 */
	public Menu(LimClient limClient, Properties props) throws LimClientException
	{
		if(limClient == null)
		{
			throw new LimClientException("LimClient parameter is null!");
		}
		
		m_limClient = limClient;

		/* Load test cases
		 * ---------------*/
		
		if(props == null)
		{
			throw new LimClientException("Properties parameter is null!");
		}
		 
		String testFileName = props.getProperty(TEST_FILE_KEY);

		if(testFileName == null || testFileName.trim().length() == 0)
		{
			throw new LimClientException("LIM client test file name is not defined.");
		}
		
		m_testCaseHelper = new TestCaseHelper(testFileName);
		
		if(m_testCaseHelper.size() == 0)
		{
			throw new LimClientException("No test cases available!");
		}
		
		/* Set autorun flag
		 */
		 
		try
		{
			m_autoRun = new Boolean(props.getProperty(AUTO_RUN_KEY, "false")).booleanValue();
		}
		catch(Exception e)
		{
		 	m_autoRun = false;
		}

		/* Setup optional log file - used by testLimDeployment.sh
		 */
		 
		String clientLogFile = props.getProperty(CLIENT_LOGFILE_KEY);
		
		if(clientLogFile != null && clientLogFile.trim().length() > 0)
		{
			m_logger = new TestLogger( clientLogFile, "Test LIM EJBs" );		 
		}
		
		/* For logging purpose, try to get LIM version and host connected to
		 */

		int begin = m_limClient.m_bisHome.indexOf("LIM");
		int end = m_limClient.m_bisHome.indexOf("Cluster");
		
		if(end > 0)
		{
			m_limVersion = m_limClient.m_bisHome.substring(begin, end);
		}
	}
	
	/**
	 * Method: run()
	 */
	public void run() throws LimClientException
	{
		TestCase testCase = null;
		try
		{
			/* Check if AUTO_RUN=true
			 */
			if(m_autoRun)
			{
				display("\nAUTO_RUN set to true. Running all test cases...\n"); 
				for(int i=0; i<m_testCaseHelper.getTestCases().size(); i++)
				{
					display("===============");
					display(" TEST CASE # " + (i + 1));
					display("===============");
					execute((TestCase) m_testCaseHelper.getTestCase(i));
				}
				display("Completed running all test cases.");
				display("Bye! ;)");  
				return;
			}
			
			display("Connected to: " + m_limVersion + " @ " + m_limClient.m_providerURL);
			
			/* AUTO_RUN=false, display menu and wait for user input
			 */
			 
			String userInput;
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			int option = 0;
			displayNumberOfMenuItems = MIN_NUMBER_OF_MENU_CHOICES;
			while(true)
			{
					
				displayMenu();
				option = getOption(in.readLine());
				if (option == EXIT)
				{
					display("Bye! ;)");
					break;
				}
				else if(option == RELOAD)
				{
					m_testCaseHelper.reload();
				} 
				else if(option == SHOW_ALL_TESTCASES)
				{
					showTestCases();
				}
				else if(option > 0)
				{
					display("Running test case no. " + option + "..."); 
					execute((TestCase) m_testCaseHelper.getTestCase(option - 1)); // list has a zero-based index
				}
				else
				{
					display("Invalid option!");
				}					
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
			throw new LimClientException(e, "IO Exception encountered!");
		}	
	}

	/**
	 * method: getOption()
	 * Valid options are 1 through the number of test cases in the list 
	 */
	private int getOption(String s)
	{
		try
		{
			int i = Integer.parseInt(s.trim());
			
			//check if user wants to exit or reload testcases 
			if(i == EXIT || i == RELOAD || i==SHOW_ALL_TESTCASES)
			{
				return i;
			}
			
			//check if valid test case number
			if(i > 0 && i <= m_testCaseHelper.size())	
			{
				return i;
			}
		}
		catch(Exception e)	{}
		return -1; 
	}
	
		/**
		 * Method to show transaction start time
		 * @param atransname
		 */
	private void displayPreInfo(String atransname)
	{
		today = new java.util.Date();
		String preDtStr = formatter.format(today);
		System.out.println(atransname + " started at " + preDtStr);
	
	}
	/**
	* Method to show transaction end time
	* @param atranName
	*/
	private void displayPostInfo(String atranName) {
	    // display date & time transaction ended
		today = new java.util.Date();
		String postDtStr = formatter.format(today);
		System.out.println(atranName + " returned at " + postDtStr);	
	}

	/**
	 * method: execute()
	 */
	private void execute(TestCase testCase)
	{
		Object response = null;
		
		try
		{
			display(testCase.getDescription());
			display("BEGIN_INPUT");
			display(testCase.getRequest(), "TestCase");
			display("END_INPUT");
			display("Calling LIM now.");
			display("Please wait for response...");
			
			switch(testCase.getType())
			{
				case TestCase.FIELD_ADDRESS:
				displayPreInfo("Field address");
					response = m_limClient.execute((_fieldAddressRequest) testCase.getRequest());
				displayPostInfo("Field address");
					break;
					
				case TestCase.RETRIEVE_LOCATION_FOR_E911ADDRESS:
				    displayPreInfo("Retrieve location for E911 Address");
					response = m_limClient.execute((_retrieveLocationForE911AddressRequest) testCase.getRequest());
				    displayPostInfo("Retrieve location for E911 Address");
					break;	
				
				case TestCase.RETRIEVE_LOCATION_FOR_GEOADDRESS:
				    displayPreInfo("Retrieve location for GEO Address");
					response = m_limClient.execute((_retrieveLocationForGeoAddressRequest) testCase.getRequest());
				    displayPostInfo("Retrieve location for GEO Address");
					break;
					
				case TestCase.RETRIEVE_LOCATION_FOR_POSTALADDRESS:
				    displayPreInfo("Retrieve location for Postal Address");
					response = m_limClient.execute((_retrieveLocationForPostalAddressRequest) testCase.getRequest());
				    displayPostInfo("Retrieve location for Postal Address");
					break;
					
				case TestCase.RETRIEVE_LOCATION_FOR_POSTALADDRESS2:
				    displayPreInfo("Retrieve location for Postal Address2");
					response = m_limClient.execute((_retrieveLocationForPostalAddress2Request) testCase.getRequest());
				    displayPostInfo("Retrieve location for Postal Address2");
					break;
					
				case TestCase.RETRIEVE_LOCATION_FOR_SERVICEADDRESS:
				    displayPreInfo("Retrieve location for Service Address");
					response = m_limClient.execute((_retrieveLocationForServiceAddressRequest) testCase.getRequest());
				    displayPostInfo("Retrieve location for Service Address");
					break;
					
				case TestCase.RETRIEVE_LOCATION_FOR_TELEPHONENUMBER:
				    displayPreInfo("Retrieve location for Telephone Number");
					response = m_limClient.execute((_retrieveLocationForTelephoneNumberRequest) testCase.getRequest());
				    displayPostInfo("Retrieve location for Telephone Number");
					break;
				
				case TestCase.UPDATE_BILLINGADDRESS:
				    displayPreInfo("Update Billing Address");
					response = m_limClient.execute((_updateBillingAddressRequest) testCase.getRequest());
				    displayPostInfo("Update Billing Address");
					break;
				
				case TestCase.RETRIEVE_LOCATION_FOR_ADDRESS:
				    displayPreInfo("Retrieve location for address");
					response = m_limClient.execute((_retrieveLocationForAddressRequest) testCase.getRequest());
				    displayPostInfo("Retrieve location for address");
					break;
					
				case TestCase.RETRIEVE_LOCATION_FOR_SERVICE:
				    displayPreInfo("Retrieve location for service");
					response = m_limClient.execute((_retrieveLocationForServiceRequest) testCase.getRequest());
				    displayPostInfo("Retrieve location for service");
					break;
					
				case TestCase.RETRIEVE_SERVICE_AREA_BY_POSTAL_CODE:
				    displayPreInfo("Retrieve service area by postal code");
					response = m_limClient.execute((_retrieveServiceAreaByPostalCodeRequest) testCase.getRequest());
				    displayPostInfo("Retrieve service area by postal code");
					break;
					
				case TestCase.PING:
				    displayPreInfo("Ping");
					response = m_limClient.execute((_pingRequest) testCase.getRequest());
				    displayPostInfo("Ping");
					break;
					
				case TestCase.SELF_TEST:
				    displayPreInfo("Self test");
					response = m_limClient.execute((_selfTestRequest) testCase.getRequest());
				    displayPostInfo("Self test");
					break;
					
				default:
					
					throw new LimClientException("Invalid test case type.");
			}
		
			display("LIM response received!");
			display("BEGIN_OUTPUT");
			display(response, "Response");
			display("END_OUTPUT");
			display("Test execution completed successfully! :)\n");
		}
		catch(LimClientException e)
		{
			display(e.getMessage());
		}
		catch(Exception e)
		{
			display("LIM Exception encountered!");
			display("BEGIN_EXCEPTION");
			display(e, "Exception");
			display("END_EXCEPTION");
			display("Test execution failed! :(\n");			
		}
	}

	/**
	 * method: show all test cases
	 * @return
	 */
	private int showTestCases()
	{
		displayNumberOfMenuItems =	MAX_NUMBER_OF_MENU_CHOICES;
		return(displayNumberOfMenuItems);
	}
	/**
	  * method: displayMenu()
		 */
	public void displayMenu()
	{
		TestCase testCase = null;
		
		display("--------------------------------------------------------------------------------");
		display("                                LIM Client Menu");
		display("--------------------------------------------------------------------------------");
		
		for(int i=0; ((i<m_testCaseHelper.size())&&(i <= displayNumberOfMenuItems)); i++)
		{
			testCase = (TestCase) m_testCaseHelper.getTestCase(i);
			display(i+1 + " - " + testCase.getDescription());
		}
		
		display(SHOW_ALL_TESTCASES+" - Show All Test cases");
		display(RELOAD + " - Reload test cases");
		display(EXIT + " - Exit");
	}
		
	/**
	 * method: display()
	 */
	private void display(String s)
	{
		System.out.println(s);
		log(s);
	}

	private void display(Object o)
	{
		display(m_formatter.print(o, ""));
	}

	private void display(Object o, String s)
	{
		display(m_formatter.print(o, s));
	}
	
	private void log(String s)
	{
		if(m_logger != null)
		{
			m_logger.println(s);
		}
	}
}
