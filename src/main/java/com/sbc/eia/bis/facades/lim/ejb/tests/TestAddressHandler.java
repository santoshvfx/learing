// $Id: TestAddressHandler.java,v 1.1.2.15 2007/04/26 21:38:43 jd3462 Exp $

/* Copyright Notice
 * RESTRICTED - PROPRIETARY INFORMATION
 * The information herein is for use only by authorized employees
 * of SBC Services Inc. and authorized Affiliates of SBC Services,
 * Inc., and is not for general distribution within or outside the
 * the respective companies.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2004 SBC Services, Inc.
 * All rights reserved.
 */

/** Description
 *  This file contains the TestAddressHandler class used to verify that
 *  AddressHandlers work correctly.
 *  Description
 */

package com.sbc.eia.bis.facades.lim.ejb.tests;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectStreamClass;
import java.util.Properties;
import java.util.StringTokenizer;

import com.sbc.bccs.idl.helpers.IDLUtil;
import com.sbc.bccs.idl.helpers.ObjectPropertyManager;
import com.sbc.bccs.utilities.PropertiesFileLoader;
import com.sbc.eia.bis.framework.BisContextManager;
import com.sbc.eia.bis.framework.logging.BisLogger;
import com.sbc.eia.bis.framework.logging.BisLoggerFactory;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.bis_types.BisContextProperty;
import com.sbc.eia.idl.lim.helpers.AddressHandler;
import com.sbc.eia.idl.lim.helpers.AddressHandlerAIT;
import com.sbc.eia.idl.lim.helpers.AddressHandlerASON;
import com.sbc.eia.idl.lim.helpers.AddressHandlerException;
import com.sbc.eia.idl.lim.helpers.AddressHandlerGranite;
import com.sbc.eia.idl.lim.helpers.AddressHandlerLFACS;
import com.sbc.eia.idl.lim.helpers.AddressHandlerPremis;
import com.sbc.eia.idl.lim.helpers.AddressHandlerUSPS;
import com.sbc.eia.idl.lim.helpers.queries.AddressHandlerAITCRIS;
import com.sbc.eia.idl.lim.helpers.queries.AddressHandlerCRIS;
import com.sbc.eia.idl.lim.helpers.queries.AddressHandlerCRM;
import com.sbc.eia.idl.lim.helpers.queries.AddressHandlerMI;
import com.sbc.eia.idl.lim.helpers.queries.AddressHandlerSNETBOSS;
import com.sbc.eia.idl.lim_types.Address;
import com.sbc.eia.idl.lim_types.AddressChoice;
import com.sbc.eia.idl.lim_types.UnfieldedAddress;
import com.sbc.eia.idl.types.StringSeqOpt;
import com.sbc.logging.LogAssistant;
import com.sbc.logging.LogAssistantFactory;
import com.sbc.logging.message.MessageFactory;

/**
 * @author rz7367
 *
 * The TestAddressHandler class tests AddressHandlers by preparing address
 * to be parsed/formatted.
 */
public class TestAddressHandler {
    
    public static void main(String args[])
    {
    	BisLogger myLogger = null;
    	String iLogPolicyPath = "c:/deploy/BisLogPolicy.xml";
        BisLoggerFactory blFactory = new BisLoggerFactory ();
    
        ObjectPropertyManager opm = new ObjectPropertyManager();
        LogAssistant logAssist = LogAssistantFactory.create ("LIM6.0", "6.0.2");
        logAssist.log (MessageFactory.create (""));
    	opm.add(BisContextProperty.APPLICATION,"BIS");
        opm.add(BisContextProperty.CUSTOMERNAME,"Rachel");
        opm.add(BisContextProperty.SERVICECENTER, "ALL");
        logAssist.log (MessageFactory.create (""));
        opm.add (BisContextProperty.LOGGINGINFORMATION, logAssist.getCorrID());
        BisContext clientContext = new BisContext(opm.toArray()) ;	    
    

		String propertiesFile = "lim.properties";
		FileInputStream fInput = null;
		Properties limProps = null;
		try
		{	
			System.out.println("Try to load lim properties using File IO.");
			try 
			{
				File f = new File( propertiesFile );
				fInput = new FileInputStream(f);
				limProps = new Properties();
				limProps.load(fInput);
			} 
			catch (Exception e) 
			{	
				System.out.println("Failed to load client properties using File IO: " + e.getMessage());
			} 
			finally
			{
				try
				{
					fInput.close();
				} 
				catch ( Exception e ) 
				{
				}
			}		
			if( limProps == null )
			{
				System.out.println("Try to load client properties using PropertiesFileLoader.");
				limProps = PropertiesFileLoader.read(propertiesFile, null);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

        try {
        	myLogger  = blFactory.create( "LIM6.0", "6.0.2");
    	} catch (Throwable ex) {
            ex.printStackTrace();
        }
        BisContextManager bisContext = new BisContextManager(clientContext);
        
        myLogger.setLoggingInformationString(bisContext.getLoggingInformationString());
        myLogger.logEntry(bisContext.getLoggingInformationString());
    
    	try {
    	    myLogger.log (LogEventId.ENTRY, "Address Handler is Starting...");
    	}catch (Throwable e) {e.printStackTrace(); System.exit(1);}
    
    	System.out.println("1 - CRIS TEST");
    	System.out.println("2 - MI TEST");
    	System.out.println("3 - PREMIS TEST");
    	System.out.println("4 - LFACS TEST");
    	System.out.println("5 - UNFIELDED ADDRESS TEST");
    	System.out.println("6 - BLOB TEST");
    	System.out.println("7 - AIT CRIS TEST");
    	System.out.println("8 - AIT ASON TEST");
    	System.out.println("9 - SNET BOSS TEST");
    	System.out.println("10 - OVALS-USPS TEST");
		System.out.println("11 - CRM TEST");
		System.out.println("12 - GRANITE TEST");
		System.out.println("13 - Get Cingular Type");
		System.out.println("14 - Test AMNQ");
    	// System.out.println("9 - LOCATION HANDLER TEST");
    	System.out.println("0 - Quit");
    	
    	BufferedReader in = new BufferedReader(new java.io.InputStreamReader(System.in));
    	String option = "";
    	Integer optSelected;
    	
    	try
    	{	
    		option = in.readLine();
    		optSelected = new Integer(option);
    		System.out.println("Option selected = <" + option + ">...");
    	}
    	catch (IOException e)
    	{
    		System.out.println("Exception = " + e.getMessage());
    		return;
    	}
    	switch (optSelected.intValue()) 
    	{
    	case 1 :		
    	// test CRIS Class
    	//
    	System.out.println ("");
    	System.out.println ("CRIS Class Test");
    	System.out.println ("================");
    
    	AddressHandlerCRIS cris = null;
    	try
    	{	
    		cris = new AddressHandlerCRIS ("(1) 1 FIRSTAR PLAZA	/DZIP 63101","RM 1;DES (SECRETARIAL POOL)","AHN", limProps, myLogger);
    		System.out.println ("\nTest Case 1");
    		System.out.println ("===========");
    		System.out.println (cris);
    		
    		cris = new AddressHandlerCRIS ("4018-B S GRAND, ST LOUIS, MO","63118","BLDG 1N;FLR 2;APT 52;DES WNG 2", "123", limProps, myLogger);
    		System.out.println ("\nTest Case 2");
    		System.out.println ("===========");
    		System.out.println (cris);
    		
    		cris = new AddressHandlerCRIS ("@HIGHWAY M*M /DZIP NONE","BLDGFLR 52;DES WNG 2", "", limProps, myLogger);
    		System.out.println ("\nTest Case 3");
    		System.out.println ("===========");
    		System.out.println (cris);
    
    		cris = new AddressHandlerCRIS ("1241 W 69 ST, MIAMI, FL","33014-89","APT S", null, limProps, myLogger);
    		System.out.println ("\nTest Case 4");
    		System.out.println ("===========");
    		System.out.println (cris);
    		
    		cris = new AddressHandlerCRIS ("10 WARE","02138", "", null, limProps, myLogger);
    		System.out.println ("\nTest Case 5");
    		System.out.println ("===========");
    		System.out.println (cris);
    		
    		cris = new AddressHandlerCRIS ("(OAD)","", "", limProps, myLogger);
    		System.out.println ("\nTest Case 6");
    		System.out.println ("===========");
    		System.out.println (cris);
    		
    		cris = new AddressHandlerCRIS (" ,PORTAGE DES SIOUX /DZIP NONE","", "AHN", limProps, myLogger);
    		System.out.println ("\nTest Case 7");
    		System.out.println ("===========");
    		System.out.println (cris);
    		
    		cris = new AddressHandlerCRIS (",MAYES COUNTY,              MAYES COUNTY,               OK /DZIP 74361              ","", "123", limProps, myLogger);
    		System.out.println ("\nTest Case 8");
    		System.out.println ("===========");
    		System.out.println (cris);
    
    		cris = new AddressHandlerCRIS ("(OAD)700-1 N 2 /DZIP 6310212345","RM (2584)", "345", limProps, myLogger);
    		System.out.println ("\nTest Case 9");
    		System.out.println ("===========");
    		System.out.println (cris);
    		
    		cris = new AddressHandlerCRIS ("2747-1/2 W CLAY(SFX)SUITE D /DZIP 63301-09876/RL Y6V","BLDGFLR 52;DES WNG 2", "678", limProps, myLogger);
    		System.out.println ("\nTest Case 10");
    		System.out.println ("===========");
    		System.out.println (cris);
    		
    		cris = new AddressHandlerCRIS ("@P*O BOX 6047", "BLDGFLR 52;DES WNG 2", "AHN", limProps, myLogger);
    		System.out.println ("\nTest Case 11");
    		System.out.println ("===========");
    		System.out.println (cris);
    
    		cris = new AddressHandlerCRIS ("8020 N*W 162ND ST", "33016123456789", "", "987", limProps, myLogger);
    		System.out.println ("\nTest Case 12");
    		System.out.println ("===========");
    		System.out.println (cris);
    
    		cris = new AddressHandlerCRIS ("8020 NW 162ND ST,HIALEAH,FL", "33016", "", "321", limProps, myLogger);
    		System.out.println ("\nTest Case 13");
    		System.out.println ("===========");
    		System.out.println (cris);
    
    		return;	
    	}
    	catch (AddressHandlerException e)
    	{
    		System.out.println("Exception = " + e.getMessage());
    		return;
    	}
    
    	case 2 :		
    	// test MI Class
    	//
    	System.out.println ("");
    	System.out.println ("MI Class Test");
    	System.out.println ("=============");
    
    	AddressHandlerMI mi = null;
    	try
    	{	
    		mi = new AddressHandlerMI ("120/W/LODI AV/LODI","(BLDG 1,FLR 1,UNIT SP 16)", "12345-1234", limProps, myLogger);
    		System.out.println ("\nTest Case 1");
    		System.out.println ("===========");
    		System.out.println (mi);		
    
    		mi = new AddressHandlerMI ("6245//PLEASANT VALEY RD/",null, "78901--", limProps, myLogger);
    		System.out.println ("\nTest Case 2");
    		System.out.println ("===========");
    		System.out.println (mi);	
    	
    		mi = new AddressHandlerMI ("6245//PLEASANT VALEY RD/EL DRDO","(BLDG GAR)", "12345", limProps,  myLogger);
    		System.out.println ("\nTest Case 3");
    		System.out.println ("===========");
    		System.out.println (mi);
    	
    		mi = new AddressHandlerMI ("(OMIT)5321/N/ORCHARD AVE/FRS","(AHN 55)", "87654", limProps, myLogger);
    		System.out.println ("\nTest Case 4");
    		System.out.println ("===========");
    		System.out.println (mi);		
    
    		mi = new AddressHandlerMI ("3333 FAIRVIEW RD","COSTA MESA CA","92626","", limProps, myLogger);
    		System.out.println ("\nTest Case 5");
    		System.out.println ("===========");
    		System.out.println (mi);		
    
    		mi = new AddressHandlerMI ("101 S 4TH ST","ST LOUIS MO","63102","RESALE ATTN 2ND FL", limProps, myLogger);
    		System.out.println ("\nTest Case 6");
    		System.out.println ("===========");
    		System.out.println (mi);
    	
    		mi = new AddressHandlerMI ("P O BOX 27","ST LOUIS MOM","92309","DBA BKR VLLY NEWS", limProps, myLogger);
    		System.out.println ("\nTest Case 7");
    		System.out.println ("===========");
    		System.out.println (mi);
    
    		mi = new AddressHandlerMI ("PSC 1650-10","","92309",null, limProps, myLogger);
    		System.out.println ("\nTest Case 8");
    		System.out.println ("===========");
    		System.out.println (mi);
    		
    		mi = new AddressHandlerMI ("1114#S NEVADA,OCNSD(BLDG REAR)", limProps, myLogger);
    		System.out.println ("\nTest Case 9");
    		System.out.println ("===========");
    		System.out.println (mi);
    
    		mi = new AddressHandlerMI ("9633 # SAN CARLOS AV ,  S GT ( BLDG  GAR ) ", limProps, myLogger);
    		System.out.println ("\nTest Case 10");
    		System.out.println ("===========");
    		System.out.println (mi);
    		
    		mi = new AddressHandlerMI ("420 1-2# FERNLEAF AV,CDM ", limProps, myLogger);
    		System.out.println ("\nTest Case 11");
    		System.out.println ("===========");
    		System.out.println (mi);
    		
    		mi = new AddressHandlerMI ("3009 1-4 #DALTON AV", limProps, myLogger);
    		System.out.println ("\nTest Case 12");
    		System.out.println ("===========");
    		System.out.println (mi);
    
    		mi = new AddressHandlerMI ("# SAN CLEMENTE RANCHO,CV (ANH 49)", limProps, myLogger);
    		System.out.println ("\nTest Case 13");
    		System.out.println ("===========");
    		System.out.println (mi);
    
    		mi = new AddressHandlerMI (" #S MAIN , WH ( AHN 100 ) ", limProps, myLogger);
    		System.out.println ("\nTest Case 14");
    		System.out.println ("===========");
    		System.out.println (mi);
    
    		mi = new AddressHandlerMI ("9633 # ,  S GT ( BLDG  GAR ) ", limProps, myLogger);
    		System.out.println ("\nTest Case 15");
    		System.out.println ("===========");
    		System.out.println (mi);
    	
    		mi = new AddressHandlerMI ("70//DESCANSO DR/SJ ","(BLDG 01 APT 3128)", "", limProps, myLogger);
    		System.out.println ("\nTest Case 16");
    		System.out.println ("===========");
    		System.out.println (mi);		
    
    		mi = new AddressHandlerMI ("120/W/LODI AV/LODI","(BLDG 1 FLR 1 UNIT SP 16)", null, limProps, myLogger);
    		System.out.println ("\nTest Case 17");
    		System.out.println ("===========");
    		System.out.println (mi);		
    
    		mi = new AddressHandlerMI ("","RESALE ATTN 2ND FL", "", limProps, myLogger);
    		System.out.println ("\nTest Case 18");
    		System.out.println ("===========");
    		System.out.println (mi);
    
    		mi = new AddressHandlerMI ("70# DESCANSO DR,(BLDG 01 APT 3128)", limProps, myLogger);
    		System.out.println ("\nTest Case 19");
    		System.out.println ("===========");
    		System.out.println (mi);
    
    		return;	
    	}
    	catch (AddressHandlerException e)
    	{
    		System.out.println("Exception = " + e.getMessage());
    		return;
    	}
    		
    	case 3 :		
    	// test PREMIS Class
    	//
    	System.out.println ("");
    	System.out.println ("Premis Class Test");
    	System.out.println ("=================");
    
    	try
    	{
    		System.out.println ("\nTest Case 1");
    		System.out.println ("==========");
    
            String structType = "Bldg";
            String structValue = "A";
            String levelType = "Floor";
            String levelValue = "2";
            String unitType = "Apt";
            String unitValue = "3C";
    
    		System.out.println("Unit[ ");
            System.out.println("Structure:" + structType + ":" + structValue + " ]]");
            System.out.print("Unit[ ");
            System.out.println("Floor:" + levelType + ":" + levelValue + " ]");
            System.out.print("Unit[ ");
            System.out.println("Unit:" + unitType + ":" + unitValue + " ]");
    		
    		AddressHandlerPremis pah1 = new AddressHandlerPremis ("route", "box", "houseNumberPrefix", "houseNumber", 
    			"assignedHouseNumber", "1-2", "streetDirection", "StNm"/*@ , streetName"*/, "streetThoroughfare", 
    			"streetNameSuffix", "$$$ city", "state", "postalCode", "county", "country", structType, structValue, levelType, levelValue, unitType, unitValue, "additionalAddressInformation", 
    			true);
    
    		System.out.println (pah1);
    		AddressHandler ahInd = new AddressHandler (pah1.getAddress ());
    		System.out.println (ahInd);
    		
    		Address addr = pah1.getAddress ();
    
    		AddressHandlerPremis pah2 = null;
    		try
    		{
    			pah2 = new AddressHandlerPremis (addr);
    		}
    		catch (AddressHandlerException e)
    		{
    			System.out.println("Exception = " + e.getMessage ());
    			return;
    		}
    
    		pah2.setPostalCode ("94583");
    		// System.out.println (pah2);
    		
    		String city = pah2.getCity();
    		System.out.println("City = " + city); 
    		String lt = pah2.getLevelType();
    		System.out.println("LevelType = " + lt);
    		String lv = pah2.getLevelValue();
    		System.out.println("LevelValue = " + lv);
    		String pc = pah2.getPostalCode();
    		System.out.println("PostalCode = " + pc);
    		String state = pah2.getState();
    		System.out.println("State = " + state);
    		String st = pah2.getStructType();
    		System.out.println("StructType = " + st);
    		String sv = pah2.getStructValue();
    		System.out.println("StructValue = " + sv);
    		String ut = pah2.getUnitType();
    		System.out.println("UnitType = " + ut);
    		String uv = pah2.getUnitValue();
    		System.out.println("UnitValue = " + uv);
    		String ahn = pah2.getAasgndHousNbr(); 
    		System.out.println("AasgndHousNbr = " + ahn);
    		String box = pah2.getBox(); 
    		System.out.println("Box = " + box);
    		String country = pah2.getCountry(); 
    		System.out.println("Country = " + country);
    		String county = pah2.getCounty(); 
    		System.out.println("County = " + county);
    		String hn = pah2.getHousNbr(); 
    		System.out.println("HousNbr = " + hn);
    		String hnp = pah2.getHousNbrPfx(); 
    		System.out.println("HousNbrPfx = " + hnp);
    		String hns = pah2.getHousNbrSfx(); 
    		System.out.println("HousNbrSfx = " + hns);
    		String route = pah2.getRoute(); 
    		System.out.println("Route = " + route);
    		String sd = pah2.getStDir(); 
    		System.out.println("StDir = " + sd);
    		String sn = pah2.getStName(); 
    		System.out.println("StName = " + sn);
    		String sns = pah2.getStNameSfx(); 
    		System.out.println("StNameSfx = " + sns);
    		String sth = pah2.getStThorofare(); 
    		System.out.println("StThorofare = " + sth);
    		String ufaddr = pah2.getUFAddr(); 
    		System.out.println("UnFielded Address = " + ufaddr);
    
    		System.out.println ("\nTest Case 2");
    		System.out.println ("==========");
    		
    		String aAddressLineArray[] = new String [] {
    				"1"+" houseNumber"+" houseNumberSuffix"+" streetDirection"+
    				" streetName"+" streetThoroughfare"+" streetNameSuffix" } ;
    
    		StringSeqOpt aAddressLines = (StringSeqOpt) IDLUtil.toOpt( aAddressLineArray );
    		
    		Address ufAddr = new Address ();
    		UnfieldedAddress ufa = new UnfieldedAddress(
    			aAddressLines,
    //			IDLUtil.toOpt("1"+" houseNumber"+" houseNumberSuffix"+" streetDirection"+
    //				" streetName"+" streetThoroughfare"+" streetNameSuffix"),
    //			IDLUtil.toOpt(" 123"+"-3AB"+" E"+
    //				" streetName"+" ALY"+" NE"),
    //			IDLUtil.toOpt(" 123"+"-3AB"+" E"+ " SN" +
    //				" ALY"+" NE"),
    //			IDLUtil.toOpt("23"+ " abc"),
    			
    //			IDLUtil.toOpt(""),
    			IDLUtil.toOpt("city"),
    			IDLUtil.toOpt("state"),
    			IDLUtil.toOpt("postalCode"), 
                IDLUtil.toOpt("postalPlus4"),
    			IDLUtil.toOpt("county"),
    			IDLUtil.toOpt("country"),
                IDLUtil.toOpt(structType),
                IDLUtil.toOpt(structValue),
                IDLUtil.toOpt(levelType),
                IDLUtil.toOpt(levelValue),
                IDLUtil.toOpt(unitType),
                IDLUtil.toOpt(unitValue),
    			IDLUtil.toOpt("AdditionalAddressInformation"),
                IDLUtil.toOpt("BusinessName"),
                IDLUtil.toOpt("CountryCode"),
                IDLUtil.toOpt("CityCode"),
                IDLUtil.toOpt("ServiceLocationName"),
                IDLUtil.toOpt("AddressId"),
                IDLUtil.toOpt("AliasName"),
                IDLUtil.toOpt("Attention")
    		);
    		ufAddr.aUnfieldedAddress(ufa);
    		AddressHandlerPremis ahp = new AddressHandlerPremis (ufAddr);
    		ufaddr = ahp.getUFAddr(); 
    		System.out.println("UnFielded Address = " + ufaddr);
    		
    		return;	
    	}
    	catch (Exception e)
    	{
    		System.out.println("Exception = " + e.getMessage());
    		return;
    	}
    		
    	case 4 :		
    	// test LFACS Class
    	//
    	System.out.println ("");
    	System.out.println ("LFACS Class Test");
    	System.out.println ("================");
    
    	try
    	{	
    
            String structType = "Bldg";
            String structValue = "A";
            String levelType = "Floor";
            String levelValue = "2";
            String unitType = "Apt";
            String unitValue = "3C";
    
    		AddressHandlerPremis pah3 = new AddressHandlerPremis (""/*route"*/, "box", "houseNumberPrefix", "houseNumber", 
    			"assignedHouseNumber"/*"*assignedHouseNumber"*/, "   1/2", "streetDirection","streetName", "streetThoroughfare", 
    			"streetNameSuffix", "city", "state", "postalCode", "", null, structType, structValue, levelType, levelValue, unitType, unitValue, "additionalAddressInformation", false);
    		Address lfacsAddr = pah3.getAddress ();
    		AddressHandlerLFACS lfacs = null;
    		try
    		{
    			lfacs = new AddressHandlerLFACS (lfacsAddr);
    		}
    		catch (AddressHandlerException e)
    		{
    			System.out.println("Exception = " + e.getMessage());
    			return;
    		}
    	
    		System.out.println (lfacs);
    
    		String stNmbrONLY = lfacs.getHousNbrONLY ();
    		System.out.println("LFACS Street Number ONLY = " + stNmbrONLY);
    		String stNm = lfacs.getStreetName ();
    		System.out.println("LFACS Street Name = " + stNm);
    		String stNmbr = lfacs.getStreetNumber ();
    		System.out.println("LFACS Street Number = " + stNmbr);
    		String st1 = lfacs.getStructType();
    		System.out.println("StructType = " + st1);
    		String sv1 = lfacs.getStructValue();
    		System.out.println("StructValue = " + sv1);
    		String lt1 = lfacs.getLevelType();
    		System.out.println("LevelType = " + lt1);
    		String lv1 = lfacs.getLevelValue();
    		System.out.println("LevelValue = " + lv1);
    		String ut1 = lfacs.getUnitType();
    		System.out.println("UnitType = " + ut1);
    		String uv1 = lfacs.getUnitValue();
    		System.out.println("UnitValue = " + uv1);
    		st1 = lfacs.getStructType();
    
    		String ld1 = lfacs.getLivingUnitDescription ();
    		System.out.println ("ld1 = " + ld1 );
    		String lv_1 = lfacs.getLivingUnitValue ();
    		System.out.println ("lv1 = " + lv_1 );
    		ld1 = lfacs.getLivingUnitDescription ();
    		System.out.println ("ld1 = " + ld1 );
    		lv_1 = lfacs.getLivingUnitValue ();
    		System.out.println ("lv1 = " + lv_1 );		
    		return;	
    	}
    	catch (Exception e)
    	{
    		System.out.println("Exception = " + e.getMessage());
    		return;
    	}
    		
    	case 5 :		
    	// test Address with unfielded address
    	//
    	System.out.println ("");
    	System.out.println ("AddressLine Test");
    	System.out.println ("================");
    
    	try
    	{	
    
            String structType = "Bldg";
            String structValue = "A";
            String levelType = "Floor";
            String levelValue = "2";
            String unitType = "Apt";
            String unitValue = "3C";
    
    		Address ufAddr = new Address();
    	
    //		String aAddressLineArray[] = new String [] {
    //			" 123"+"-3AB","E"+ " SN", " AVE" + " ALY", "NE" } ;
    //		String aAddressLineArray[] = new String [] {
    //			" 400","S"+ " LA", " SALLY" } ;
    //		String aAddressLineArray[] = new String [] {
    //			" 400","S"+ " LA" } ;
    //		String aAddressLineArray[] = new String [] {
    //			"1"+" houseNumber"+" houseNumberSuffix"+" streetDirection"+
    //				" streetName"+" streetThoroughfare"+" streetNameSuffix" } ;
    //		String aAddressLineArray[] = new String [] {
    //			" 123"+"-3AB"+" E"+" streetName"+" ALY"+" NE" } ;
    //		String aAddressLineArray[] = new String [] {
    //			" 123"+"-3AB"+" E"+ " SN" + " ALY"+" NE" } ;
    //		String aAddressLineArray[] = new String [] {
    //			"23"+ " abc" } ;
    //		String aAddressLineArray[] = new String [] {
    //			"23"+ " North Market" } ;
    //		String aAddressLineArray[] = new String [] {
    //			"123 LEISURE CREEK DR SE" } ;
    //		String aAddressLineArray[] = new String [] {
    //			"123 LEISURE CREEK AV DR SE" } ;
    //		String aAddressLineArray[] = new String [] {
    //			"123 LEISURE SOUTH DR SE" } ;
    //		String aAddressLineArray[] = new String [] {
    //			"123 LEISURE DR SE" } ;
    //		String aAddressLineArray[] = new String [] {
    //			"123 LEISURE DR SE E NE" } ;
    //		String aAddressLineArray[] = new String [] {
    //			"123 LEISURE DR SE DR N N N" } ;
    //		String aAddressLineArray[] = new String [] {
    //			"12800", "n", "LAKE SHORE DR 9*W" } ;
    //		String aAddressLineArray[] = new String [] {
    //			"12800", "n", "LAKE SHORE DR W" } ;
    //		String aAddressLineArray[] = new String [] {
    //			"12800", "n", "R 32 W" } ;
    //		String aAddressLineArray[] = new String [] {
    //			"12800", "n", "200 S RD S RD ABC RD S" } ;
    //		String aAddressLineArray[] = new String [] {
    //			"12800", "n", "StreetName NE ALY" } ;
    //		String aAddressLineArray[] = new String [] {
    //			"12800", "S DR N" } ;
    		String aAddressLineArray[] = new String [] {
    			// "S0076 W14098 MC*SHANE" };
    			// "12800", "Avenue E" } ;
    			"ROUTE 136", "AHN 12" };

    
    		StringSeqOpt aAddressLines = (StringSeqOpt) IDLUtil.toOpt( aAddressLineArray );
    
    		UnfieldedAddress ufa = new UnfieldedAddress(
    			aAddressLines,
    			IDLUtil.toOpt("city"),
    			IDLUtil.toOpt("state"),
    			IDLUtil.toOpt("postalCode"), 
                IDLUtil.toOpt("postalCodePlus4"), 
    			IDLUtil.toOpt("county"),
    			IDLUtil.toOpt("country"),
                IDLUtil.toOpt(structType),
                IDLUtil.toOpt(structValue),
                IDLUtil.toOpt(levelType),
                IDLUtil.toOpt(levelValue),
                IDLUtil.toOpt(unitType),
                IDLUtil.toOpt(unitValue),
                IDLUtil.toOpt("AdditionalAddressInformation"),
                IDLUtil.toOpt("BusinessName"),
                IDLUtil.toOpt("CountryCode"),
                IDLUtil.toOpt("CityCode"),
                IDLUtil.toOpt("ServiceLocationName"),
                IDLUtil.toOpt("AddressId"),
                IDLUtil.toOpt("AliasName"),
                IDLUtil.toOpt("Attention")
            );
    
    		ufAddr.aUnfieldedAddress(ufa);
    	
    		AddressHandlerPremis pah_uf = null;
    		try
    		{
    			pah_uf = new AddressHandlerPremis (ufAddr);
    		}
    		catch (AddressHandlerException e)
    		{
    			System.out.println("Exception = " + e.getMessage ());
    			return;
    		}
    		System.out.println (pah_uf);
    		
    		return;	
    	}
    	catch (Exception e)
    	{
    		System.out.println("Exception = " + e.getMessage());
    		return;
    	}
    	
    	case 6 :		
    	// test Serialization and UnSerialization
    	//
     	System.out.println ("");
    	System.out.println ("BLOB Test");
    	System.out.println ("=========");
    
    	try
    	{	
            String structType = "Bldg";
            String structValue = "A";
            String levelType = "Floor";
            String levelValue = "2";
            String unitType = "Apt";
            String unitValue = "3C";
    
            System.out.print("Unit[ ");
            System.out.println("Structure:" + structType + ":" + structValue + " ]");
            System.out.print("Unit[ ");
            System.out.println("Floor:" + levelType + ":" + levelValue + " ]");
            System.out.print("Unit[ ");
            System.out.println("Unit:" + unitType + ":" + unitValue + " ]");
    		
    		AddressHandlerPremis pah = new AddressHandlerPremis ("route", "box", "houseNumberPrefix", "houseNumber", 
    			"assignedHouseNumber", "houseNumberSuffix", "streetDirection", "streetName", "streetThoroughfare", 
    			"streetNameSuffix", "city", "state", "postalCode", "county", "country", structType, structValue, levelType, levelValue, unitType, unitValue, "additionalAddressInformation", 
    			false);
    		AddressHandler ahtmp = new AddressHandler (pah.getAddress ());
            
    		// Just print the SerialVersionUID.
    		//
    		Class cl = Class.forName ("com.sbc.eia.idl.lim.helpers.AddressHandler");
    		ObjectStreamClass osc = ObjectStreamClass.lookup (cl );
    		long svuid = osc.getSerialVersionUID();
    		System.out.println("Main - SerialVersionUID = " + svuid);
    
    		Class clP = Class.forName ("com.sbc.eia.idl.lim.helpers.AddressHandlerPremis");
    		ObjectStreamClass oscP = ObjectStreamClass.lookup (clP );
    		long svuidP = oscP.getSerialVersionUID();
    		System.out.println("Main - SerialVersionUID = " + svuidP);
    
    		byte [] addrBlob = null;
    		try
    		{
    			addrBlob = ahtmp.getAddressBlob();
    		}
    		catch (AddressHandlerException e)
    		{
    			System.out.println("Exception = " + e.getMessage ());
    			return;
    		}
    		
    		/*
    		// The following code is for writing the blob to a flat file, and was used to test
    		// the versioning when changing AddressHandler.
    		//
    		try 
    		{
    			FileOutputStream fos = new FileOutputStream ("c:\\rz9.blob");
    			fos.write (addrBlob);
    		}
    		catch (Exception e) 
    		{
    			System.out.println("Exception = " + e.getClass().getPropertyLabelName() + " " + e.getMessage ());
    			return;
    		}

    		// The following code is for reading the blob from a flat file, and was used to test
    		// the versioning when changing AddressHandler.
    		//
    		byte [] addrBlob = new byte [2000];
    		int n = 0;
    		try 
    		{
    			FileInputStream fis = new FileInputStream ("c:\\rz9.blob");
    			n = fis.read (addrBlob);
    		}
    		catch (Exception e) 
    		{
    			System.out.println("Exception = " + e.getClass().getPropertyLabelName() + " " + e.getMessage ());
    			return;
    		}
    		*/
    
    		AddressHandler ah = null;
    		try
    		{
    			ah = new AddressHandler (addrBlob);
    		}
    		catch (Exception e)
    		{
    			System.out.println("Exception = " + e.getMessage());
                e.printStackTrace();
    			return;
    		}
            AddressHandler blobAddr = ah.getAddressHandler ();
    		
    		String city = blobAddr.getCity();
    		System.out.println("City = " + city);
    		
    		String pc = blobAddr.getPostalCode();
    		System.out.println("PostalCode = " + pc);
    		String state = blobAddr.getState();
    		System.out.println("State = " + state);
    		
    		String st = blobAddr.getStructType();
    		System.out.println("StructType = " + st);
    		String sv = blobAddr.getStructValue();
    		System.out.println("StructValue = " + sv);

            String lt = blobAddr.getLevelType();
            System.out.println("LevelType = " + lt);
            String lv = blobAddr.getLevelValue();
            System.out.println("LevelValue = " + lv);
    		
    		String ut = blobAddr.getUnitType();
    		System.out.println("UnitType = " + ut);
    		String uv = blobAddr.getUnitValue();
    		System.out.println("UnitValue = " + uv);
    		ut = blobAddr.getUnitType();
    		
    		String ahn = blobAddr.getAasgndHousNbr(); 
    		System.out.println("AasgndHousNbr = " + ahn);
    		String box = blobAddr.getBox(); 
    		System.out.println("Box = " + box);
    		String country = blobAddr.getCountry(); 
    		System.out.println("Country = " + country);
    		String county = blobAddr.getCounty(); 
    		System.out.println("County = " + county);
    		String hn = blobAddr.getHousNbr(); 
    		System.out.println("HousNbr = " + hn);
    		String hnp = blobAddr.getHousNbrPfx(); 
    		System.out.println("HousNbrPfx = " + hnp);
    		String hns = blobAddr.getHousNbrSfx(); 
    		System.out.println("HousNbrSfx = " + hns);
    		String route = blobAddr.getRoute(); 
    		System.out.println("Route = " + route);
    		String sd = blobAddr.getStDir(); 
    		System.out.println("StDir = " + sd);
    		String sn = blobAddr.getStName(); 
    		System.out.println("StName = " + sn);
    		String sns = blobAddr.getStNameSfx(); 
    		System.out.println("StNameSfx = " + sns);
    		String sth = blobAddr.getStThorofare(); 
    		System.out.println("StThorofare = " + sth);
    
    		Address myAddr = ah.getAddress ();
    		return;	

    	} 
    	catch (Exception e)
    	{
    		System.out.println("Exception = " + e.getMessage());
    		return;
    	}
    	
    	case 7 :		
    	// test AIT CRIS Class
    	//
    	System.out.println ("");
    	System.out.println ("AIT CRIS Class Test");
    	System.out.println ("===================");
    
    	AddressHandlerAITCRIS AITcris = null;
    	try
    	{	
    		AITcris = new AddressHandlerAITCRIS ("440 s main st,chicago", "08879-1234567890", "bldg 24;(flr 14);apt 214-d;(des entrance at rear of bldg", limProps, myLogger);
    		AITcris.setAHN ("AHN");
    		System.out.println ("\nTest Case 1");
    		System.out.println ("===========");
    		System.out.println (AITcris);
    		
    		AITcris = new AddressHandlerAITCRIS (",homewood","","", limProps, myLogger);
    		AITcris.setAHN ("");
    		System.out.println ("\nTest Case 2");
    		System.out.println ("===========");
    		System.out.println (AITcris);
    		
    		AITcris = new AddressHandlerAITCRIS ("@pine rd,chicago", "", "", limProps, myLogger);
    		AITcris.setAHN ("123");
    		System.out.println ("\nTest Case 3");
    		System.out.println ("===========");
    		System.out.println (AITcris);
    
    		AITcris = new AddressHandlerAITCRIS ("(1) 105-a s broad (DNO) st,bonner,ma","","apt 214-d", limProps, myLogger);
    		AITcris.setAHN (null);
    		System.out.println ("\nTest Case 4");
    		System.out.println ("===========");
    		System.out.println (AITcris);
    		
    		AITcris = new AddressHandlerAITCRIS ("(OAD) 456 cherry st,southfield","", "", limProps, myLogger);
    		System.out.println ("\nTest Case 5");
    		System.out.println ("===========");
    		System.out.println (AITcris);
    		
    		AITcris = new AddressHandlerAITCRIS ("(2),flowood", "", "", limProps, myLogger);
    		System.out.println ("\nTest Case 6");
    		System.out.println ("===========");
    		System.out.println (AITcris);
    		
    		AITcris = new AddressHandlerAITCRIS ("#217 anyplace st, milwakee", "", "", limProps, myLogger);
    		System.out.println ("\nTest Case 7");
    		System.out.println ("===========");
    		System.out.println (AITcris);
    		
    		AITcris = new AddressHandlerAITCRIS ("N*W st",null , null, limProps, myLogger);
    		System.out.println ("\nTest Case 8");
    		System.out.println ("===========");
    		System.out.println (AITcris);
    		
    		AITcris = new AddressHandlerAITCRIS ("FOOT OF MARQUETTE DR","", "", limProps, myLogger);
    		System.out.println ("\nTest Case 9");
    		System.out.println ("===========");
    		System.out.println (AITcris);
    		
    		AITcris = new AddressHandlerAITCRIS ("n123 W456 ABC RD", "", "", limProps, myLogger);
    		System.out.println ("\nTest Case 10");
    		System.out.println ("===========");
    		System.out.println (AITcris);
    		
    		AITcris = new AddressHandlerAITCRIS ("(DNO) 2747 W (DNA) CLAY(SFX)", "", "", limProps, myLogger);
    		System.out.println ("\nTest Case 11");
    		System.out.println ("===========");
    		System.out.println (AITcris);
    		
    		AITcris = new AddressHandlerAITCRIS ("@P*O BOX 6047", "", "", limProps, myLogger);
    		System.out.println ("\nTest Case 12");
    		System.out.println ("===========");
    		System.out.println (AITcris);
    		
    		AITcris = new AddressHandlerAITCRIS ("n123 N*W Wa56 ABC RD N*W", "", "", limProps, myLogger);
    		System.out.println ("\nTest Case 13");
    		System.out.println ("===========");
    		System.out.println (AITcris);
    
    		AITcris = new AddressHandlerAITCRIS ("(1) 6046 WHIPPLE AV N\\*W, NORTH CANTON", "", "", limProps, myLogger);
    		System.out.println ("\nTest Case 14");
    		System.out.println ("===========");
    		System.out.println (AITcris);
    
    		AITcris = new AddressHandlerAITCRIS ("20765 W Grass Lake Rd,LINDENHURST/TN Antioch Tel No", "", "", limProps, myLogger);
    		System.out.println ("\nTest Case 15");
    		System.out.println ("===========");
    		System.out.println (AITcris);
    
    		AITcris = new AddressHandlerAITCRIS ("2851 S DR MARTN LUTHR KING JR DR, CHGO, IL", "60616", "APT  706  ", limProps, myLogger);
    		System.out.println ("\nTest Case 16");
    		System.out.println ("===========");
    		AddressHandler crisAddr = new AddressHandler(AITcris.getAddress ());
    		System.out.println (crisAddr);
    
    		return;	
    	}
    	catch (AddressHandlerException e)
    	{
    		System.out.println("Exception = " + e.getMessage());
    		return;
    	}
    
    	case 8 :		
    	// test ASON Class
    	//
    	System.out.println ("");
    	System.out.println ("ASON Class Test");
    	System.out.println ("===============");
    
    	try
    	{	
            String structType = "Bldg";
            String structValue = "A";
            String levelType = "Floor";
            String levelValue = "2";
            String unitType = "Apt";
            String unitValue = "3C";
    
            System.out.print("Unit[ ");
            System.out.println("Structure:" + structType + ":" + structValue + " ]");
            System.out.print("Unit[ ");
            System.out.println("Floor:" + levelType + ":" + levelValue + " ]");
            System.out.print("Unit[ ");
            System.out.println("Unit:" + unitType + ":" + unitValue + " ]");
    		
    		AddressHandlerASON ason1 = new AddressHandlerASON ("route", "box", "houseNumberPrefix", "houseNumber", 
    			"assignedHouseNumber", "houseNumberSuffix", "streetDirection", ""/*streetName"*/, "streetThoroughfare", 
    			"streetNameSuffix", "city", "state", "postalCode", "county", "country", structType, structValue, levelType, levelValue, unitType, unitValue, "additionalAddressInformation", 
    			true);
    
    		System.out.println ("\nTest Case 1");
    		System.out.println ("===========");
    		System.out.println (ason1);
    		ason1.setState ("CA");
    		
    		System.out.println ("\nTest Case 2");
    		System.out.println ("===========");
    		AddressHandler asonInd = new AddressHandler (ason1.getAddress ());
    		System.out.println (asonInd);
    
    		String aAddressLineArray[] = new String [] {
    		//	"123 LEISURE CREEK DR SE" } ;
    			"S0076 W14098 MC*SHANE" } ;
    
    		StringSeqOpt aAddressLines = (StringSeqOpt) IDLUtil.toOpt( aAddressLineArray );
    
    		UnfieldedAddress ufa = new UnfieldedAddress(
    			aAddressLines,
    //			toStringOpt("1"+" houseNumber"+" houseNumberSuffix"+" streetDirection"+
    //				" streetName"+" streetThoroughfare"+" streetNameSuffix"),
    //			toStringOpt(" 123"+"-3AB"+" E"+
    //				" streetName"+" ALY"+" NE"),
    //			toStringOpt(" 123"+"-3AB"+" E"+" StreetName"+
    //				" ALY"+" NE"),
    //			toStringOpt("n123 W456 ABC RD"),
    //			toStringOpt("FOOT OF MARQUETTE DR NE"),	
    //			IDLUtil.toOpt("123 LEISURE CREEK DR SE"),
    //			toStringOpt("123 LEISURE CREEK AV DR SE"),
    //			toStringOpt("123 LEISURE SOUTH DR SE"),
    //			toStringOpt("123 LEISURE DR SE"),	
    //			toStringOpt("123 LEISURE DR SE E NE"),	
    //			toStringOpt("123 LEISURE DR SE DR N N N"),	
    //			toStringOpt(""),
    			IDLUtil.toOpt("city"),
    			IDLUtil.toOpt("state"),
    			IDLUtil.toOpt("postalCode"), 
                IDLUtil.toOpt("postalCodePlus4"), 
    			IDLUtil.toOpt("county"),
    			IDLUtil.toOpt("country"),
    			IDLUtil.toOpt(structType),
                IDLUtil.toOpt(structValue),
                IDLUtil.toOpt(levelType),
                IDLUtil.toOpt(levelValue),
                IDLUtil.toOpt(unitType),
                IDLUtil.toOpt(unitValue),
    			IDLUtil.toOpt("AdditionalAddressInformation"),
                IDLUtil.toOpt("BusinessName"),
                IDLUtil.toOpt("CountryCode"),
                IDLUtil.toOpt("CityCode"),
                IDLUtil.toOpt("ServiceLocationName"),
                IDLUtil.toOpt("AddressId"),
                IDLUtil.toOpt("AliasName"),
                IDLUtil.toOpt("Attention")
    		);
    		Address ufAddr = new Address ();
    		ufAddr.aUnfieldedAddress(ufa);
    		AddressHandlerASON ason2 = new AddressHandlerASON (ufAddr);
    		
    		System.out.println ("\nTest Case 3");
    		System.out.println ("===========");
    		System.out.println (ason2);
    		String get_sd = ason2.getStreetDir(); 
    		System.out.println("StDir = " + get_sd);
    		String get_sns = ason2.getStreetNameSfx(); 
    		System.out.println("StNameSfx = " + get_sns);
    		String get_sn = ason2.getStreetName(); 
    		System.out.println("StreetName = " + get_sn);
    		String get_sa = ason2.getStreetAddr (true); 
    		System.out.println("StreetAddr = " + get_sa);
    
    		System.out.println ("\nTest Case 4");
    		System.out.println ("===========");
    		ason2.setASONFields ("n123 W456", "", "E", "StreetName ALY N*E", "CITY", "STATE",
    		// ason2.setASONFields ("n123 W456", "", "E", "StreetName N*E ALY ", "CITY", "STATE",
    		// ason2.setASONFields ("12800", "", "n", "LAKE SHORE DR 9*W", "CITY", "STATE",
    		// ason2.setASONFields ("12800", "", "n", "R 32 W", "CITY", "STATE",
    		// ason2.setASONFields ("12800", "", "n", "200 S RD S RD ABC RD S", "CITY", "STATE",
    		 	"12345", "COUNTY", null, structType, structValue, levelType, levelValue, unitType, unitValue);
    
    		// ason2.setASONFields ("n123 W456", "houseNumberSuffix", "streetDirection", "streetName", 
    		// 	"streetThoroughfare", "streetNmSuffix", "city", "state", "postalCode", "houseNumberInd");
    	
    		String city = ason2.getCity();
    		System.out.println("City = " + city); 
    		String lt = ason2.getLevelType();
    		System.out.println("LevelType = " + lt);
    		String lv = ason2.getLevelValue();
    		System.out.println("LevelValue = " + lv);
    		String pc = ason2.getPostalCode();
    		System.out.println("PostalCode = " + pc);
    		String state = ason2.getState();
    		System.out.println("State = " + state);
    		String st = ason2.getStructType();
    		System.out.println("StructType = " + st);
    		String sv = ason2.getStructValue();
    		System.out.println("StructValue = " + sv);
    		String ut = ason2.getUnitType();
    		System.out.println("UnitType = " + ut);
    		String uv = ason2.getUnitValue();
    		System.out.println("UnitValue = " + uv);
    		String ahn = ason2.getAasgndHousNbr(); 
    		System.out.println("AasgndHousNbr = " + ahn);
    		String box = ason2.getBox(); 
    		System.out.println("Box = " + box);
    		String country = ason2.getCountry(); 
    		System.out.println("Country = " + country);
    		String county = ason2.getCounty(); 
    		System.out.println("County = " + county);
    		String hn = ason2.getHousNbr(); 
    		System.out.println("HousNbr = " + hn);
    		String hnp = ason2.getHousNbrPfx(); 
    		System.out.println("HousNbrPfx = " + hnp);
    		String hns = ason2.getHousNbrSfx(); 
    		System.out.println("HousNbrSfx = " + hns);
    		String route = ason2.getRoute(); 
    		System.out.println("Route = " + route);
    		String sd = ason2.getStreetDir(); 
    		System.out.println("StDir = " + sd);
    		String sn = ason2.getStName(); 
    		System.out.println("StName = " + sn);
    		String sns = ason2.getStreetNameSfx(); 
    		System.out.println("StNameSfx = " + sns);
    		String sth = ason2.getStThorofare(); 
    		System.out.println("StThorofare = " + sth);
    
    		System.out.println ("\nTest Case 5");
    		System.out.println ("===========");
    		ason2.setASONFields ("", "12800", "", "n", "200", "S", "RD", "CITY", "STATE",
    			"12345", "COUNTY", null);
    
    		city = ason2.getCity();
    		System.out.println("City = " + city); 
    		lt = ason2.getLevelType();
    		System.out.println("LevelType = " + lt);
    		lv = ason2.getLevelValue();
    		System.out.println("LevelValue = " + lv);
    		pc = ason2.getPostalCode();
    		System.out.println("PostalCode = " + pc);
    		state = ason2.getState();
    		System.out.println("State = " + state);
    		st = ason2.getStructType();
    		System.out.println("StructType = " + st);
    		sv = ason2.getStructValue();
    		System.out.println("StructValue = " + sv);
    		ut = ason2.getUnitType();
    		System.out.println("UnitType = " + ut);
    		uv = ason2.getUnitValue();
    		System.out.println("UnitValue = " + uv);
    		ahn = ason2.getAasgndHousNbr(); 
    		System.out.println("AasgndHousNbr = " + ahn);
    		box = ason2.getBox(); 
    		System.out.println("Box = " + box);
    		country = ason2.getCountry(); 
    		System.out.println("Country = " + country);
    		county = ason2.getCounty(); 
    		System.out.println("County = " + county);
    		hn = ason2.getHousNbr(); 
    		System.out.println("HousNbr = " + hn);
    		hnp = ason2.getHousNbrPfx(); 
    		System.out.println("HousNbrPfx = " + hnp);
    		hns = ason2.getHousNbrSfx(); 
    		System.out.println("HousNbrSfx = " + hns);
    		route = ason2.getRoute(); 
    		System.out.println("Route = " + route);
    		sd = ason2.getStreetDir(); 
    		System.out.println("StDir = " + sd);
    		sn = ason2.getStName(); 
    		System.out.println("StName = " + sn);
    		sns = ason2.getStreetNameSfx(); 
    		System.out.println("StNameSfx = " + sns);
    		sth = ason2.getStThorofare(); 
    		System.out.println("StThorofare = " + sth);
    
    		return;	
    	}
    	catch (Exception e)
    	{
    		System.out.println("Exception = " + e.getMessage());
    		return;
    	}
    
    	case 9 :		
    	// test SNET BOSS Class
    	//
    	System.out.println ("");
    	System.out.println ("SNET BOSS Class Test");
    	System.out.println ("====================");
    
    	AddressHandlerSNETBOSS SNETBoss = null;
    	try
    	{	
    		SNETBoss = new AddressHandlerSNETBOSS ("(1A)25 RIDGE RD", "EASTON CT                06612", "(BASEMENT)", limProps, myLogger);
    		System.out.println ("\nTest Case 1");
    		System.out.println ("===========");
    		System.out.println (SNETBoss);
    		
    		SNETBoss = new AddressHandlerSNETBOSS ("23 SEGAR ST/FFLD", "", "BASEMENT", limProps, myLogger);
    		System.out.println ("\nTest Case 2");
    		System.out.println ("===========");
    		System.out.println (SNETBoss);
    		
    		SNETBoss = new AddressHandlerSNETBOSS ("(OAD)", "", "(TRM B-TELE COMM RM)", limProps, myLogger);
    		System.out.println ("\nTest Case 3");
    		System.out.println ("===========");
    		System.out.println (SNETBoss);
    		
    		SNETBoss = new AddressHandlerSNETBOSS ("(A) 910 MT CARMEL AV/HMDN", "A B CT 06612-123", "(UNIT 6)", limProps, myLogger);
    		System.out.println ("\nTest Case 4");
    		System.out.println ("===========");
    		System.out.println (SNETBoss);
    		
    		SNETBoss = new AddressHandlerSNETBOSS ("(A) 910 MT CARMEL AV/HMDN", "066121234567890", "(UNIT 6)", limProps, myLogger);
    		System.out.println ("\nTest Case 5");
    		System.out.println ("===========");
    		System.out.println (SNETBoss);
    
    		SNETBoss = new AddressHandlerSNETBOSS (" 910 MT CARMEL AV/SAN FRANCISCO", "DY 06612", "(UNIT 6)", limProps, myLogger);
    		System.out.println ("\nTest Case 6");
    		System.out.println ("===========");
    		System.out.println (SNETBoss);
    
    		SNETBoss = new AddressHandlerSNETBOSS ("910 MT CARMEL AV", " CA 06612 ", "(UNIT 6)", limProps, myLogger);
    		System.out.println ("\nTest Case 7");
    		System.out.println ("===========");
    		System.out.println (SNETBoss);
    
    		SNETBoss = new AddressHandlerSNETBOSS ("910 MT CARMEL AV", " CA SF 06612 ", "(UNIT 6)", limProps, myLogger);
    		System.out.println ("\nTest Case 8");
    		System.out.println ("===========");
    		System.out.println (SNETBoss);
    
    		SNETBoss = new AddressHandlerSNETBOSS ("910 MT CARMEL AV", " SF 06612 ", "(UNIT 6)", limProps, myLogger);
    		System.out.println ("\nTest Case 9");
    		System.out.println ("===========");
    		System.out.println (SNETBoss);
    
    		SNETBoss = new AddressHandlerSNETBOSS ("910 MT CARMEL AV", " SAN FRANCISCO 06612 ", "(UNIT 6)", limProps, myLogger);
    		System.out.println ("\nTest Case 10");
    		System.out.println ("===========");
    		System.out.println (SNETBoss);
    
    		SNETBoss = new AddressHandlerSNETBOSS ("2356-1/2 STATE ST", "NEW HAVEN CT 06123", "APT G-2", limProps, myLogger);
    		System.out.println ("\nTest Case 11");
    		System.out.println ("===========");
    		System.out.println (SNETBoss);
    
    		SNETBoss = new AddressHandlerSNETBOSS ("15-A SUMMER ST", "", "APT 15 (BSMT)", limProps, myLogger);
    		System.out.println ("\nTest Case 12");
    		System.out.println ("===========");
    		System.out.println (SNETBoss);
    
    		SNETBoss = new AddressHandlerSNETBOSS ("SNOW HL COR WINTER RD", " SAN FRANCISCO 06612 ", "WING A;FLR 2;RM 12A", limProps, myLogger);
    		System.out.println ("\nTest Case 13");
    		System.out.println ("===========");
    		System.out.println (SNETBoss);
    
    		SNETBoss = new AddressHandlerSNETBOSS ("RR 261 MAIN ST", "", "", limProps, myLogger);
    		System.out.println ("\nTest Case 14");
    		System.out.println ("===========");
    		System.out.println (SNETBoss);
    
    		SNETBoss = new AddressHandlerSNETBOSS ("(CUMBERLAND DAIRY/734-6900) (1)(2B) 100 CHURCH ST", "", "", limProps, myLogger);
    		System.out.println ("\nTest Case 15");
    		System.out.println ("===========");
    		System.out.println (SNETBoss);
    
    		SNETBoss = new AddressHandlerSNETBOSS ("CARRINGTON RD,SHRT BCH", "", "", limProps, myLogger);
    		System.out.println ("\nTest Case 16");
    		System.out.println ("===========");
    		System.out.println (SNETBoss);
    
    		SNETBoss = new AddressHandlerSNETBOSS (",LANESVILLE", "", "", limProps, myLogger);
    		System.out.println ("\nTest Case 17");
    		System.out.println ("===========");
    		System.out.println (SNETBoss);
    
    		SNETBoss = new AddressHandlerSNETBOSS ("/MOOSUP", "", "", limProps, myLogger);
    		System.out.println ("\nTest Case 18");
    		System.out.println ("===========");
    		System.out.println (SNETBoss);
    
    		SNETBoss = new AddressHandlerSNETBOSS ("PARK CITY SHIPPING PIZ", "", "", limProps, myLogger);
    		System.out.println ("\nTest Case 19");
    		System.out.println ("===========");
    		System.out.println (SNETBoss);
    
    		SNETBoss = new AddressHandlerSNETBOSS ("801 1/2 ABC ST /PARK CITY SHIPPING PIZ", "", "", limProps, myLogger);
    		System.out.println ("\nTest Case 20");
    		System.out.println ("===========");
    		System.out.println (SNETBoss);
    
    		return;	
    	}
    	catch (AddressHandlerException e)
    	{
    		System.out.println("Exception = " + e.getMessage());
    		return;
    	}
    /*
    	case 9 :		
    	// test LocationHandler Class
    	//
    	System.out.println ("");
    	System.out.println ("LocationHandler Class Test");
    	System.out.println ("==========================");
    	try
    	{
    		com.sbc.eia.idl.lim_types_2_0_0.Address addr_2 = new com.sbc.eia.idl.lim_types_2_0_0.Address ();
    		com.sbc.eia.idl.lim_types_2_0_0.Location loc_2 = new com.sbc.eia.idl.lim_types_2_0_0.Location ();
    	
    		com.sbc.eia.idl.lim_types_2_0_0.FieldedAddress fa = new com.sbc.eia.idl.lim_types_2_0_0.FieldedAddress();
    		fa.aAdditionalAddressInformation = IDLUtil.toOpt("AdditionalAddressInformation");
    		fa.aAssignedHouseNumber          = IDLUtil.toOpt("AssignedHouseNumber");
    		fa.aBox                          = IDLUtil.toOpt("Box");
    		fa.aCity                         = IDLUtil.toOpt("City");
    		fa.aCountry                      = IDLUtil.toOpt("Country");
    		fa.aCounty                       = IDLUtil.toOpt("County");
    		fa.aHouseNumber                  = IDLUtil.toOpt("HouseNumber");
    		fa.aHouseNumberPrefix            = IDLUtil.toOpt("HouseNumberPrefix");
    		fa.aHouseNumberSuffix            = IDLUtil.toOpt("HouseNumberSuffix");
    		fa.aPostalCode                   = IDLUtil.toOpt("PostalCode");
    		fa.aRoute                        = IDLUtil.toOpt("Route");
    		fa.aState                        = IDLUtil.toOpt("State");
    		fa.aStreetDirection              = IDLUtil.toOpt("StreetDirection");
    		fa.aStreetName                   = IDLUtil.toOpt("StreetName");
    		fa.aStreetNameSuffix             = IDLUtil.toOpt("StreetNameSuffix");
    		fa.aStreetThoroughfare           = IDLUtil.toOpt("StreetThoroughfare");
    		fa.aUnit                         = new com.sbc.eia.idl.lim_types_2_0_0.UnitSeqOpt();
    		fa.aUnit.__default();
    	
    		com.sbc.eia.idl.lim_types_2_0_0.UnfieldedAddress ufa  = new com.sbc.eia.idl.lim_types_2_0_0.UnfieldedAddress();
    		ufa.aAddressLine   = IDLUtil.toOpt("AddressLine");
    		ufa.aCity          = IDLUtil.toOpt("City");
    		ufa.aCountry       = IDLUtil.toOpt("Country");
    		ufa.aCounty        = IDLUtil.toOpt("County");
    		ufa.aPostalCode    = IDLUtil.toOpt("PostalCode");
    		ufa.aState         = IDLUtil.toOpt("State");
    		ufa.aAdditionalAddressInformation = IDLUtil.toOpt("AdditionalAddressInformation");
    		ufa.aUnit          = new com.sbc.eia.idl.lim_types_2_0_0.UnitSeqOpt();
    		ufa.aUnit.__default();
    	
    		addr_2.aUnfieldedAddress (ufa);
    		
    		Vector unitV = new Vector(0, 1 );
    		unitV.addElement( new com.sbc.eia.idl.lim_types_2_0_0.Unit(com.sbc.eia.idl.lim_types_2_0_0.UnitCategory.STRUCTURE,"structType", "structVal") );
    		unitV.addElement( new com.sbc.eia.idl.lim_types_2_0_0.Unit(com.sbc.eia.idl.lim_types_2_0_0.UnitCategory.LEVEL, "lvlType", "lvlVal") );
    		unitV.addElement( new com.sbc.eia.idl.lim_types_2_0_0.Unit(com.sbc.eia.idl.lim_types_2_0_0.UnitCategory.UNIT_CATEGORY, "unitType", "unitVal") );
    		unitV.addElement( new com.sbc.eia.idl.lim_types_2_0_0.Unit(com.sbc.eia.idl.lim_types_2_0_0.UnitCategory.UNIT_CATEGORY, "unitType_1", "unitVal_1") );
    		unitV.addElement( new com.sbc.eia.idl.lim_types_2_0_0.Unit(com.sbc.eia.idl.lim_types_2_0_0.UnitCategory.LEVEL, "lvlType_1", "lvlVal_1") );
    
    		com.sbc.eia.idl.lim_types_2_0_0.UnitSeqOpt unitSeqOpt = new com.sbc.eia.idl.lim_types_2_0_0.UnitSeqOpt();
    		com.sbc.eia.idl.lim_types_2_0_0.Unit[] unitArray = new com.sbc.eia.idl.lim_types_2_0_0.Unit[ unitV.size() ] ;
    		unitV.copyInto( unitArray );
    		unitSeqOpt.theValue(unitArray);
    		if (unitSeqOpt.discriminator() == true )
    		{
    			if (addr_2.discriminator() == com.sbc.eia.idl.lim_types_2_0_0.AddressChoice.FIELDED_ADDRESS)
    				fa.aUnit.theValue( unitSeqOpt.theValue() );
    			else
    		    	ufa.aUnit.theValue( unitSeqOpt.theValue() );
    		}
    
    		byte b [] = new byte [1];
    		b[0] = 1;
    		ObjectId objectId = new ObjectId (b, "class");
    		ObjectKey objectKey = new ObjectKey ("value", "kind");
    		ObjectHandle locationHandle = new ObjectHandle (
    			(ObjectIdOpt)IDLUtil.toOpt (ObjectIdOpt.class, objectId),
    			(ObjectKeyOpt)IDLUtil.toOpt (ObjectKeyOpt.class, objectKey));
    		boolean postalCertifiedAddress = true;
    		String mapUrl = "MapUrl";
    		String latitudeLongitude = "LatitudeLongitude";
    		String drivingDirections = "DrivingDirections";
    		ObjectProperty properties [] = new ObjectProperty [2];
    		properties[0] = new ObjectProperty ("Tag_1", "Value_1");
    		properties[1] = new ObjectProperty ("Tag_2", "Value_2");
    	
    		loc_2 = new com.sbc.eia.idl.lim_types_2_0_0.Location (
    			locationHandle, 
    			(com.sbc.eia.idl.lim_types_2_0_0.AddressOpt) IDLUtil.toOpt (com.sbc.eia.idl.lim_types_2_0_0.AddressOpt.class, addr_2), 
    			postalCertifiedAddress,
    			IDLUtil.toOpt (mapUrl),
    			IDLUtil.toOpt (latitudeLongitude), 
    			IDLUtil.toOpt (drivingDirections),
    			properties);
    	
    		Location loc = new Location ();
    
    		loc = LocationHandler.convertLocation_v2 (loc_2);
    		
    //		I've tested the result by looking at the output with the debuger.
    		
    		return;	
    	}
    	catch (Exception e)
    	{
    		System.out.println("Exception = " + e.getMessage());
    		return;
    	}
    */
    	case 10 :		
    	// test USPS Class
    	//
    	System.out.println ("");
    	System.out.println ("USPS Class Test");
    	System.out.println ("=================");
    
    	try
    	{
    		System.out.println ("\nTest Case 1");
    		System.out.println ("===========");
    
    		AddressHandlerUSPS usps_1 = new AddressHandlerUSPS (
                "city",
    			"county",
                "houseNumber",
    			"streetDirection",
    			"streetNameSuffix",
                "state",
                "streetName",
    			"streetThoroughfare",
                "1",
                "DEPT",
    			"postalCode",
                "postalCodePlus4",
                new String[]{"cassLine1", "cassLine2"},
                new String[]{"auxLine1", "auxLine2"},
                "cassAdditionalInfo"
                );

    					
    		Address uspsAddress = usps_1.getAddress (); 
    		System.out.println ("\nAddressHandlerUSPS (String, String, String...)\n");
    		System.out.println (usps_1); 
    		
    		System.out.println ("\nTest Case 2");
    		System.out.println ("===========");
    
            String structType = "Bldg";
            String structValue = "A";
            String levelType = "Floor";
            String levelValue = "2";
            String unitType = "Apt";
            String unitValue = "3C";
    
            System.out.print("Unit[ ");
            System.out.println("Structure:" + structType + ":" + structValue + " ]");
            System.out.print("Unit[ ");
            System.out.println("Floor:" + levelType + ":" + levelValue + " ]");
            System.out.print("Unit[ ");
            System.out.println("Unit:" + unitType + ":" + unitValue + " ]");
    					
    	    uspsAddress.aFieldedAddress().aStructureType.theValue(structType);
            uspsAddress.aFieldedAddress().aStructureValue.theValue(structValue);
            uspsAddress.aFieldedAddress().aLevelType.theValue(levelType);
            uspsAddress.aFieldedAddress().aLevelValue.theValue(levelValue);
            uspsAddress.aFieldedAddress().aUnitType.theValue(unitType);        
            uspsAddress.aFieldedAddress().aUnitValue.theValue(unitValue);
    
    		AddressHandlerUSPS usps_2 = new AddressHandlerUSPS (uspsAddress);
    		System.out.println ("\nAddressHandlerUSPS (Address, String)\n");
    		System.out.println (usps_2);
    		System.out.println ("\nOVALS-USPS-5 LINES\n\nline_1 = " + usps_2.getLine1() + 
    			"\nline_2 = " + usps_2.getLine2() + 
    			"\nline_3 = " + usps_2.getLine3() +
    			"\nline_4 = " + usps_2.getLine4() +
    			"\nline_5 = " + usps_2.getLine5());
    
    		System.out.println ("\nTest Case 3");
    		System.out.println ("===========");
    		
    			
    		String aAddressLineArray[] = new String [] {
    			"MRS JAY J POTTER", "SUNSET VILLAGE", "123 LEISURE CREEK DR SE", "ROOM 3043", "JENISON, MI 49428" } ;
    
    		StringSeqOpt aAddressLines = (StringSeqOpt) IDLUtil.toOpt( aAddressLineArray );
    
    		UnfieldedAddress ufa = new UnfieldedAddress(
    			aAddressLines,
    			IDLUtil.toOpt("city"),
    			IDLUtil.toOpt("state"),
    			IDLUtil.toOpt("postalCode"), 
                IDLUtil.toOpt("postalCodePlus4"), 
    			IDLUtil.toOpt("county"),
    			IDLUtil.toOpt("country"),
                IDLUtil.toOpt(structType),
                IDLUtil.toOpt(structValue),
                IDLUtil.toOpt(levelType),
                IDLUtil.toOpt(levelValue),
                IDLUtil.toOpt(unitType),
                IDLUtil.toOpt(unitValue),
    			IDLUtil.toOpt("AdditionalAddressInformation"),
                IDLUtil.toOpt("BusinessName"),
                IDLUtil.toOpt("CountryCode"),
                IDLUtil.toOpt("CityCode"),
                IDLUtil.toOpt("ServiceLocationName"),
                IDLUtil.toOpt("AddressId"),
                IDLUtil.toOpt("AliasName"),
                IDLUtil.toOpt("Attention")
    		);
    		Address ufAddr = new Address ();
    		ufAddr.aUnfieldedAddress(ufa);
    		AddressHandlerUSPS usps_3 = new AddressHandlerUSPS (ufAddr);
    		
    		System.out.println ("\n" + usps_3);
    		System.out.println ("\nOVALS-USPS-5 LINES\n\nline_1 = " + usps_3.getLine1() + 
    			"\nline_2 = " + usps_3.getLine2() + 
    			"\nline_3 = " + usps_3.getLine3() +
    			"\nline_4 = " + usps_3.getLine4() +
    			"\nline_5 = " + usps_3.getLine5());
    
    		return;	
    	}
    	catch (Exception e)
    	{
    		System.out.println("Exception in AddressHandlerUSPS = " + e.getMessage());
            e.printStackTrace();
    		return;
    	}
    
		case 11 :		
		// test CRM Class
		//
		System.out.println ("");
		System.out.println ("CRM Class Test");
		System.out.println ("==============");

		AddressHandlerCRM crm = null;
		try
		{	
			crm = new AddressHandlerCRM ("0001 ANY ST","BLDG 4 FLR 37 APT 14-X-1", "Chicago", "IL", "60605-12345", limProps, myLogger);
			System.out.println ("\nTest Case 1");
			System.out.println ("===========");
			System.out.println (crm);		

			crm = new AddressHandlerCRM (null, null, null, null, null, limProps, myLogger);
			System.out.println ("\nTest Case 2");
			System.out.println ("===========");
			System.out.println (crm);	

			crm = new AddressHandlerCRM ("", "", "", "","", limProps, myLogger);
			System.out.println ("\nTest Case 3");
			System.out.println ("===========");
			System.out.println (crm);

			crm = new AddressHandlerCRM ("", "", "", "","6060509", limProps, myLogger);
			System.out.println ("\nTest Case 4");
			System.out.println ("===========");
			System.out.println (crm);
			
			return;	
		}
		catch (AddressHandlerException e)
		{
			System.out.println("Exception = " + e.getMessage());
			return;
		}
		
		case 12 :		
		// test Granite Class
		//
		System.out.println ("");
		System.out.println ("GRANITE Class Test");
		System.out.println ("==================");

		AddressHandlerGranite granite = null;
		try
		{	
			AddressHandler ah1 = new AddressHandler ("route", "box", " "/*houseNumberPrefix"*/, "houseNumber", 
    			"assignedHouseNumber", "-houseNumberSuffix", "streetDirection", "streetName ", "  "/*streetThoroughfare"*/, 
    			"streetNameSuffix ", "city", "state", "postalCode", "county", "country", "structType", "structValue", 
    			"levelType", "levelValue", "unitType", "unitValue", "additionalInfo");
   
			granite = new AddressHandlerGranite (ah1.getAddress());
			String graniteSN = granite.getStreetAddress();
			System.out.println ("\nTest Case 1");
			System.out.println ("===========");
			System.out.println (graniteSN);		
			
			AddressHandler ah2 = new AddressHandler ("123-1/2 W streetName ST W", 
    			"city", "county","state", "postalCode", "postalCodePlus4", "structType", "structValue", 
    			"levelType", "levelValue", "unitType", "unitValue");
			Address graniteAddr = ah2.getAddress();
			System.out.println ("\nTest Case 2");
			System.out.println ("===========");
			System.out.println (ah2);
		}
		catch (AddressHandlerException e)
		{
			System.out.println("Exception = " + e.getMessage());
			return;
		}
			
		case 13 :		
		System.out.println ("");
		System.out.println ("Get Cingular Type");
		System.out.println ("=================");

		try
		{
 			String structType = "Bldg";
            String structValue = "A";
            String levelType = "Floor";
            String levelValue = "2";
            String unitType = "Apt";
            String unitValue = "3C";
    
    		Address ufAddr = new Address();
    		// String aAddressLineArray[] = new String [] { "123 main st", "po box 456" } ;
    		// String aAddressLineArray[] = new String [] { " p o box 456 " } ;
    		String aAddressLineArray[] = new String [] { "123 main st  " } ;
    
    		StringSeqOpt aAddressLines = (StringSeqOpt) IDLUtil.toOpt( aAddressLineArray );
    
    		UnfieldedAddress ufa = new UnfieldedAddress(
    			aAddressLines,
    			IDLUtil.toOpt(""),
    			IDLUtil.toOpt(""),
    			IDLUtil.toOpt("zip"), 
                IDLUtil.toOpt(""), 
    			IDLUtil.toOpt("county"),
    			IDLUtil.toOpt(""), // rz country"),
                IDLUtil.toOpt(structType),
                IDLUtil.toOpt(structValue),
                IDLUtil.toOpt(levelType),
                IDLUtil.toOpt(levelValue),
                IDLUtil.toOpt(unitType),
                IDLUtil.toOpt(unitValue),
                IDLUtil.toOpt("AdditionalAddressInformation"),
                IDLUtil.toOpt("BusinessName"),
                IDLUtil.toOpt("CountryCode"),
                IDLUtil.toOpt("CityCode"),
                IDLUtil.toOpt("ServiceLocationName"),
                IDLUtil.toOpt("AddressId"),
                IDLUtil.toOpt("AliasName"),
                IDLUtil.toOpt("Attention")
            );
    
    		ufAddr.aUnfieldedAddress(ufa);
    	
    		AddressHandler cah_uf = null;
			System.out.println ("\nTest Case 1");
			System.out.println ("===========");
    		try
    		{
    			cah_uf = new AddressHandler (ufAddr);
    			String cat = cah_uf.getCingularAddressType();
    			System.out.println("CingularAddressType = " + cat);
    		}
			catch (AddressHandlerException e)
			{
				System.out.println("Exception = " + e.getMessage());
				return;
			}	
			
			AddressHandler ahg = new AddressHandler (""/*route"*/, ""/*box"*/, " "/*houseNumberPrefix"*/, "houseNumber", 
    			"assignedHouseNumber", "-houseNumberSuffix", "streetDirection", "Carrot Box "/*" RR 123 box 567""streetName "*/, "  "/*streetThoroughfare"*/, 
    			"streetNameSuffix ", "city", "aaa"/*"state"*/, "postalCode", "county", "united states"/*"country"*/, "structType", "structValue", 
    			"levelType", "levelValue", "unitType", "unitValue", "additionalInfo");
   
			AddressHandler cingular = new AddressHandler (ahg.getAddress());
			String CAT = cingular.getCingularAddressType();
			System.out.println ("\nTest Case 2");
			System.out.println ("===========");
			System.out.println("CingularAddressType = " + CAT);

		}
		catch (AddressHandlerException e)
		{
			System.out.println("Exception = " + e.getMessage());
			return;
		}
		return;	

    	case 14 :		
		System.out.println ("");
		System.out.println ("Test AMNQ");
		System.out.println ("=========");

    	Address ufAddr = new Address();
    	
    	String lineArray[] = new String [] {
    			 "S0076 W14098 MC*SHANE" };
    			// "12800", "Avenue E" } ;
    			// "AHN 126", "camino ramon" };
 
    	StringSeqOpt lines = (StringSeqOpt) IDLUtil.toOpt( lineArray );
    
    	UnfieldedAddress ufa = new UnfieldedAddress(
    			lines,
    			IDLUtil.toOpt("city"),
    			IDLUtil.toOpt(""), // state"),
    			IDLUtil.toOpt("postalCode"), 
                IDLUtil.toOpt("postalCodePlus4"), 
    			IDLUtil.toOpt("county"),
    			IDLUtil.toOpt("country"),
                IDLUtil.toOpt("structType"),
                IDLUtil.toOpt("structValue"),
                IDLUtil.toOpt("levelType"),
                IDLUtil.toOpt("levelValue"),
                IDLUtil.toOpt("unitType"),
                IDLUtil.toOpt("unitValue"),
                IDLUtil.toOpt("AdditionalAddressInformation"),
                IDLUtil.toOpt("BusinessName"),
                IDLUtil.toOpt("CountryCode"),
                IDLUtil.toOpt("CityCode"),
                IDLUtil.toOpt("ServiceLocationName"),
                IDLUtil.toOpt("AddressId"),
                IDLUtil.toOpt("AliasName"),
                IDLUtil.toOpt("Attention")
        );    
    	ufAddr.aUnfieldedAddress(ufa);
    	
		AddressHandler ahf = new AddressHandler ("route", "box", "houseNumberPrefix", "houseNumber", 
    			"assignedHouseNumber", "-houseNumberSuffix", "streetDirection", "streetName ", "streetThoroughfare", 
    			"streetNameSuffix ", "city", "state", "postalCode", "county", "country", "structType", "structValue", 
    			"levelType", "levelValue", "unitType", "unitValue", "additionalInfo");
   
		Address address = ufAddr; // ahf.getAddress();
		Address retAddr = null;
		
    	String ahn = "";
    	String addrLine = "";
    	String state = "";
    	String zip = "";
    	
    	try
    	{
        	switch (address.discriminator().value())
        	{
        		case AddressChoice._FIELDED_ADDRESS:
        			System.out.println ("Fielded Address...");
        			break;
        			
        		case AddressChoice._UNFIELDED_ADDRESS:
        			System.out.println ("UnFielded Address...");
        			try
        			{
        				if (address.aUnfieldedAddress().aAddressLines.theValue().length == 0)
        					break;
        			}
        			catch (org.omg.CORBA.BAD_OPERATION e) {break;}
        			catch (java.lang.NullPointerException e) {break;}
        				
        			String sn = "";
        			for (int ij=0; ij < address.aUnfieldedAddress().aAddressLines.theValue().length; ij++) 
        			{
        				try 
        				{ 
        					sn = sn + address.aUnfieldedAddress().aAddressLines.theValue()[ij].trim() + " "; 
        				}
        				catch (org.omg.CORBA.BAD_OPERATION e) {}
        				catch (java.lang.NullPointerException e) {}
        			}
        			sn = sn.trim ();
        			if (sn == null || sn.equals (""))
        				break;
        				
    				StringTokenizer sn1 = new StringTokenizer (sn, " ");
					String tok = sn1.nextToken();
					String newSN = "";
			
    				if (tok.equalsIgnoreCase ("AHN"))
    				{
    					if (sn1.hasMoreTokens())
    					{
    						tok = sn1.nextToken();
    						ahn = tok;
    					}
    					while (sn1.hasMoreTokens())
    						newSN = newSN + sn1.nextToken() + " ";
    					String addrLineArray[] = new String [1];
    					addrLineArray[0] = newSN.trim();
    					address.aUnfieldedAddress().aAddressLines.theValue (addrLineArray );
    				}
    				
    				try { state = address.aUnfieldedAddress().aState.theValue().trim(); } 
        			catch (org.omg.CORBA.BAD_OPERATION e) {}
        			catch (java.lang.NullPointerException e) {}
        			
        			try { zip = address.aUnfieldedAddress().aPostalCode.theValue().trim(); } 
        			catch (org.omg.CORBA.BAD_OPERATION e) {}
        			catch (java.lang.NullPointerException e) {}
        			
        			if ((state == null || state.equals ("")) && zip != null && !zip.equals (""))
        				try
    					{
    						 state = "CA"; // rz RetrieveStateCodeByZip.retrieveStateCodeByZip (properties, aPostalCode, theLogger);
    						 address.aUnfieldedAddress().aState.theValue (state);
    					}
    					catch (Exception e) // rz SQLException e )
    					{
    						if ( e.getMessage().indexOf("Exhausted Resultset") >= 0)
    						{
    							System.out.println ("RetrieveStateCodeByZip-Exhausted Resultset: " + e.getMessage());			
    						}
    						else
    						{
    							System.out.println ("RetrieveStateCodeByZip-SQL ERROR: " + e.getMessage());
    						}
    					} 
    				
    				if (state != null && !state.equals (""))
    					if (state.equalsIgnoreCase("IL") ||
    		 				state.equalsIgnoreCase("IN") ||
    		 				state.equalsIgnoreCase("MI") ||
    		 				state.equalsIgnoreCase("OH") ||
    		 				state.equalsIgnoreCase("WI") )	
    		 			{
    		 				AddressHandlerAIT ahAIT = new AddressHandlerAIT (address);
    		 				// rz retAddr = ahAIT.getAddress();
    		 				System.out.println ("AIT Street Name:" + ahAIT.getStName());
    		 				System.out.println ("Street Number=" + ahAIT.getHousNbr());
    		 				System.out.println ("Street Number Prefix=" + ahAIT.getHousNbrPfx());
    		 			}
    		 			else
    		 			{
    		 				AddressHandler ahReg = new AddressHandler (address);
    		 				// rz retAddr = ahReg.getAddress();
    		 				System.out.println ("Regular Street Name:" + ahReg.getStName());
    		 				System.out.println ("Street Number=" + ahReg.getHousNbr());
    		 				System.out.println ("Street Number Prefix=" + ahReg.getHousNbrPfx());
    		 			}
    		 			
    		 			System.out.println ("AHN=" + ahn);
     
    		 			// rz retAddr.aFieldedAddress().aAssignedHouseNumber.theValue (ahn);
        	} // end switch	
        } // end try
		catch (AddressHandlerException e)
		{
			System.out.println("Exception = " + e.getMessage());
			return;
		}
		return;	
    	
    	case 0 :
    	return;
    	}
    			
    	// E N D ! ! !
    	//
    	System.out.println("E N D");
    }

}
