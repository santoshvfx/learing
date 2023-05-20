//$Id: TestSMClient.java,v 1.11 2008/10/17 17:43:55 jd3462 Exp $
package com.sbc.eia.bis.facades.lim.ejb.tests;

import java.util.Properties;

import com.sbc.bccs.idl.helpers.ObjectPropertyManager;
import com.sbc.bccs.idl.helpers.TN;
import com.sbc.bccs.utilities.PropertiesFileLoader;
import com.sbc.eia.bis.RmNam.utilities.SmClient.SmClient;
import com.sbc.eia.bis.framework.logging.BisLogger;
import com.sbc.eia.bis.framework.logging.BisLoggerFactory;
import com.sbc.eia.bis.lim.util.LIMBase;
import com.sbc.eia.idl.bis_types.AccessDenied;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.bis_types.BisContextProperty;
import com.sbc.eia.idl.bis_types.BisTypesObjectKeyKind;
import com.sbc.eia.idl.bis_types.BusinessViolation;
import com.sbc.eia.idl.bis_types.InvalidData;
import com.sbc.eia.idl.bis_types.NotImplemented;
import com.sbc.eia.idl.bis_types.ObjectNotFound;
import com.sbc.eia.idl.bis_types.SystemFailure;
import com.sbc.eia.idl.types.ObjectKey;

/**
 * Test SM Client to retrieve Address by TN.
 * Creation date: (9/11/01 11:59:46 AM)
 * @author: Rachel Zadok - Local
 */
public class TestSMClient 
{
	static String propertiesFile = "lim.properties";

	/**
	 *  SM BIS related info.
	 */
	private static String aProviderURL   = "corbaloc::cidb2001.sbc.com:11000";
	private static String aSmHome = "cell/clusters/SM6.1Cluster/SMHome-14.10";

	private static final String SM_CONNECTION_FAILED_MSG = "SmConnection Failed";
	private static final String LIM_ORIGINATION = "LIM";

    public TestSMClient() 
    {
    	super();
    }
    
    /**
     * Create ObjectHandle from TN.
     * Creation date: (9/10/01 12:28:49 PM)
     * @return com.sbc.eia.idl.types_2_0_0.ObjectHandle
     * @param tn com.sbc.bccs.idl.helpers.TN
     */
    private ObjectKey createObjectKey (TN tn) 
    {
    	ObjectKey objKey = new ObjectKey();
    
    	if (tn != null)
    	{
    		objKey = new ObjectKey(tn.toString(), BisTypesObjectKeyKind.WORKINGTELEPHONENUMBER);
    		return objKey;
    	}
    	return null;
    }
    /**
     * Starts the TestRetrieveAddrByDescriptiveAndState transaction.
     * Creation date: (08/24/01 2:29:24 PM)
     * @param args java.lang.String[]
     */
    public static void main(String[] args) 
    {
    	System.out.println("Running SM Client to retrieve Address by TN  ");
    
    	ObjectPropertyManager opm = new ObjectPropertyManager();
    	opm.add(BisContextProperty.APPLICATION,"LIM_BIS");
    	opm.add(BisContextProperty.SERVICECENTER,"IN");
    	BisContext aContext = new BisContext(opm.toArray()) ;
    
    	Properties myProperty = new Properties();
    	
     	BisLoggerFactory blFactory = null;
     	BisLogger 		 myLogger  = null ;
     	
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
    
    		/**
    		 * Define BisLogger parameters.
    		 */
    		if( blFactory == null ) 
            {
    			String limName =  myProperty.getProperty( "BIS_NAME" ).trim() ;
    			String limVersion =  myProperty.getProperty( "BIS_VERSION" ).trim() ;
    			String limLogPolicy =  myProperty.getProperty( "LOG_POLICY_PATH" ).trim() ;
    			
    			blFactory = new BisLoggerFactory();
    			myLogger  = blFactory.create( limName, limVersion);
    		}
    
    		LIMBase limBase = new LIMBase ();
            limBase.setPROPERTIES( myProperty );
            limBase.initLIMBase();
    		limBase.setCallerContext( aContext );
    		TestSMClient aSMClient = new TestSMClient ();
    		
    		TN tn = new TN ("7153421226");		
    //		TN tn = new TN ("6083653942");
    //		TN tn = new TN ("4142657072");	// recieved exception
    
    		aSMClient.retrieveAddressByTN (aContext, limBase, tn );
    	}
    	catch( Exception e ) 
        {
    		System.out.println("Main() - Exception caught...") ;
    		e.printStackTrace();
    	}
    }
    /**
     * Uses the GetAddressByTN class to retrieve Address by TN.
     * Creation date: (09/12/01 11:08:36 AM)
     * @param aContext  a BisContext object
     * @param limBase   a LIMBase object
     * @param tn        a TN object
     * @exception InvalidData
     * @exception AccessDenied
     * @exception BusinessViolation
     * @exception SystemFailure
     * @exception NotImplemented
     * @exception ObjectNotFound
     */
    public void retrieveAddressByTN (BisContext aContext, LIMBase limBase, TN tn) 
        throws  
            InvalidData, 
    		AccessDenied, 
    		BusinessViolation, 
    		SystemFailure, 
    		NotImplemented, 
    		ObjectNotFound
    {
    	try
    	{
    		/**
    		 * get Address from SM
    		 */
    		SmClient smClient = new SmClient();
//    			new SmClient(aProviderURL, aSmHome, LIM_ORIGINATION, propertiesFile);
    	
    		 		ObjectKey objectKeyTN = createObjectKey (tn);
    /* rz 
     		System.out.println ("Calling getSmBean...");
    		com.sbc.bis.sm.facade.SmFacade smBean = smClient.getSmBean(aContext,limBase);
    		
    		System.out.println ("Calling smBeam.getServiceLocation...");	
    		com.sbc.eia.idl.sm_16_0.GetServiceAddressReturn getServiceLocation = new com.sbc.eia.idl.sm_16_0.GetServiceAddressReturn ();
    		getServiceLocation = smBean.getServiceLocation (aContext, objectHandleTN);
    
    		System.out.println ("Got address from smBeam.getServiceLocation...");
    
            ProviderLocationProperty props = convertFromOldLocation( getServiceLocation );
    
            Address newAddress = props.aServiceAddress.theValue();
    
    		AddressHandler aAddressHandler = 
    			new AddressHandler(newAddress);	
    					
    		System.out.println (aAddressHandler);
    	}
    	catch (SystemFailure e)
    	{
    		System.out.println ("SystemFailure Exception: " + e.aExceptionData.aCode + "|" + e.aExceptionData.aDescription + "|");
    		throw e;
    */
    	}
    	catch (Exception e)
    	{
    		System.out.println ("Exception: " + e.getMessage ());
    		e.printStackTrace();
    	}
    }
}
