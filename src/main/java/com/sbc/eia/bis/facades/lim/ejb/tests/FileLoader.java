//$Id: FileLoader.java,v 1.1 2008/06/25 14:12:27 nl9267 Exp $
//############################################################################
//#
//# Copyright Notice:
//#
//#     RESTRICTED - PROPRIETARY INFORMATION
//#      The information herein is for use only by authorized employees
//#      of ATT Services Inc. and authorized Affiliates of ATT Services,
//#      Inc., and is not for general distribution within or outside the
//#      respective companies.
//#      Copying or reproduction without prior written approval is prohibited.
//#
//#      � 2002-2005 ATT Knowledge Ventures, L.P. All rights reserved.
//#
//# History :
//# Date      | Author              | Notes
//# --------------------------------------------------------------------
//# 02/13/2008  am9643             Creation.

package com.sbc.eia.bis.facades.lim.ejb.tests;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Hashtable;
import java.util.Properties;
import java.util.StringTokenizer;
import com.sbc.bccs.utilities.Logger;
import com.sbc.bccs.utilities.PropertiesFileLoader;
import com.sbc.eia.bis.encryption.Encryption;
import com.sbc.eia.bis.framework.logging.BisLogger;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.vicunalite.api.MProperty;
import com.sbc.vicunalite.api.MPropertyException;
import com.sbc.vicunalite.api.MPropertyNotFound;
import com.sbc.vicunalite.api.MPropertyParserException;
import com.sbc.vicunalite.api.MWrongPropertyType;
import com.sun.java.util.collections.ArrayList;




/**
* 
* FileLoader - This class parses property files and xml files needed for the selftest implementation 
* @author am9643
* 
*/
public class FileLoader 
{
	/**
	 * Method loadFileContents 
	 * @param file
	 * @param serviceType
	 * @param bisLogger
	 */
	public static Hashtable loadFileContents(String file, String serviceType, BisLogger bisLogger)
	{
		String myMethodName = "FileLoader::loadFileContents()";
		bisLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);
		Hashtable services  = new Hashtable();
		InputStreamReader bufIn = null;
		InputStream input = null;
		BufferedReader bufferedReader  =null;
		String line = null;
		ArrayList resultArray = null;
		String serviceName = "";
		String serviceHelper = "";
		int i;
		int j;
		
		
		try 
		{
			
			input = PropertiesFileLoader.getAsStream(file, bisLogger);
			
			bufIn = new InputStreamReader(input);
			bufferedReader  = new BufferedReader(bufIn);
			resultArray = new ArrayList();
		
			i=0;
			
			while (( line = bufferedReader.readLine()) != null)
			{
				if(!(line.trim().startsWith("#")))
				{
					if(line.trim().startsWith(serviceType))
					{
						StringTokenizer stringTokenizer = new StringTokenizer(line);
					    i = 0; 
					    while(stringTokenizer.hasMoreTokens())
					    {
						    if (i==0)
						    serviceName = stringTokenizer.nextToken("=");
						    else
						    serviceHelper = stringTokenizer.nextToken("=");	
						    i++;
					    }  
					    services.put(serviceName,serviceHelper);
				    }
				}
			}
		}

		catch(Exception e)
		{
			bisLogger.log(
					LogEventId.EXCEPTION,"Exception in loading propertiesFileName <" + file + "> " + e.getMessage());
			
			return null;
		}
		bisLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
		
		return services;		
	}

	/**
	 * Method loadFileContents 
	 * @param file
	 * @param bisLogger
	 */
	public static ArrayList loadFileContents(String file, BisLogger bisLogger)
	{
		String myMethodName = "FileLoader::loadFileContents()";
		bisLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);
		
		InputStreamReader bufIn = null;
		InputStream input = null;
		BufferedReader bufferedReader  =null;
		String line = null;
		ArrayList resultArray = null;
		int i;
		int j;
		
		
		try 
		{			
			input = PropertiesFileLoader.getAsStream(file, bisLogger);
			
			bufIn = new InputStreamReader(input);
			bufferedReader  = new BufferedReader(bufIn);
			resultArray = new ArrayList();
		
			i=0;
			
			while (( line = bufferedReader.readLine()) != null)
			{
				if(!(line.trim().startsWith("#")))
				{
					StringTokenizer stringTokenizer = new StringTokenizer(line);
				
				    while(stringTokenizer.hasMoreTokens())
				    {
					    resultArray.add(stringTokenizer.nextToken("="));
					    System.out.println(resultArray.get(i));
					    i++;
				    }  
				}
			}
		}

		catch(Exception e)
		{
			bisLogger.log(
					LogEventId.EXCEPTION,"Exception in loading propertiesFileName <" + file + "> " + e.getMessage());
			
			return null;
		}
		bisLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
		
		return resultArray;		
	}

	/**
	 * Method loadXmlContents 
	 * @param file
	 * @param bisLogger
	 */
	public static ArrayList loadXmlContents(String file, BisLogger bisLogger)
	{
		InputStreamReader bufIn = null;
		InputStream input = null;
		BufferedReader bufferedReader  =null;
		String line = null;
		
		ArrayList tempArray = null;
		MProperty propertyList;
		int dataSourceCount = 0;
		ArrayList resultArray = new ArrayList();
		String myMethodName = "FileLoader::loadXmlContents()";
		bisLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);
		
		try 
		{
			input = PropertiesFileLoader.getAsStream(file, bisLogger);
			bufIn = new InputStreamReader(input);
			MProperty initList;
			MProperty dataSrcList;		
			initList = new MProperty(bufIn);
			propertyList = initList.getSubList("databaseResources");

			do	
			{
				dataSrcList = propertyList.getSubList("DATASOURCE");
				tempArray = retrieveDataSourceFields(dataSrcList,bisLogger);
				resultArray = appendArrayList(resultArray,tempArray,bisLogger);
				propertyList.removeProperty("DATASOURCE");	
			}
			while(propertyList.isPropertyDefined("DATASOURCE"));				
		} 

		catch (Exception e) 
		{
			bisLogger.log(
					LogEventId.EXCEPTION,"Exception in loading propertiesFileName <" + file + "> " + e.getMessage());
			return null;
		}
		bisLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
		 
		return resultArray;
	}

	/**
	 * Method loadDBProperties 
	 * @param file
	 * @param bisLogger
	 */
	public static ArrayList loadDBProperties(String file, BisLogger bisLogger)
	{
		ArrayList resultArray = null;
		String myMethodName = "FileLoader::loadDBProperties()";
		bisLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);
		
		try 
		{
			Properties p = new Properties();
		 	Encryption enc = new Encryption() ;
			p =	PropertiesFileLoader.read(file, bisLogger);
			p.put("jdbcPASSWORD", enc.decodePassword(p.getProperty("OSS_PUBLIC_KEY"), p.getProperty("jdbcPASSWORD")));
			resultArray = new ArrayList();
			resultArray.add(((String)p.getProperty("jdbcDRIVER")).trim());;
			resultArray.add(((String)p.getProperty("jdbcURL")).trim());;
			resultArray.add(((String)p.getProperty("jdbcUSERID")).trim());;
			resultArray.add(((String)p.getProperty("jdbcPASSWORD")).trim());
			resultArray.add(((String)p.getProperty("jdbcDATA_SOURCE_NAME")).trim());
			resultArray.add(((String)p.getProperty("jdbcUSE_CONNECTION_POOL")).trim());
			
		}
		catch(Exception fe) 
		{ 

			System.out.println("Properties File Not Found: " + fe.getMessage() );
			return null;

		}
		bisLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
		return resultArray;
		
	}

	/**
	 * Method appendArrayList 
	 * @param aList1
	 * @param aList2
	 * @param aLogger
	 */
	private static ArrayList appendArrayList(ArrayList aList1, ArrayList aList2, Logger aLogger)
	{
		int ctr;
		String myMethodName = "FileLoader::appendArrayList()";
		aLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);
		
		for (ctr=0;ctr<aList2.size();ctr++)
			aList1.add(aList2.get(ctr));
		aLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
		
		return aList1;
	}

	/**
	 * Method retrieveDataSourceFields 
	 * @param dataSource
	 * @param bisLogger
	 */
	private static ArrayList retrieveDataSourceFields(MProperty dataSource, BisLogger bisLogger)
	{
		String[] fields = {"DB_DESCRIPTION", "DB_PROPERTY_FILE","TABLE_LIST"};
		ArrayList dataSourceList = new ArrayList();
		MProperty field = null;
		MProperty tableList = null;
		String tableNames = "";
		int ctr;
		String value = "";
		String startTag = null;
		String endTag = null;
		String myMethodName = "FileLoader::retrieveDataSourceFields()";
		bisLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);
		try 
		{
			
			for (ctr=0; ctr<fields.length; ctr++)
			{
					field = dataSource.getProperty(fields[ctr]);
					startTag = "<" + fields[ctr]+ ">";
					endTag = "</" + fields[ctr]+ ">";
					
					if (fields[ctr].equals("TABLE_LIST"))
					{
						tableList = dataSource.getSubList("TABLE_LIST");	
						do	
						{
							value = value + getValue(tableList.getProperty("TABLE_NAME").toString(),"<TABLE_NAME>","</TABLE_NAME>",bisLogger) + ",";
							tableList.removeProperty("TABLE_NAME");	
							
						}
						while(tableList.isPropertyDefined("TABLE_NAME"));
					}
					else
						value = getValue(dataSource.toString(),startTag,endTag,bisLogger); 
					
					dataSourceList.add(value);
					value = "";
			}

		}
		catch (MPropertyException e) 
		{
		 	bisLogger.log(
					LogEventId.EXCEPTION,"Exception in loading propertiesFileName " + e.getMessage());
		 	return null;
		}
			bisLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
			return dataSourceList;
			
		}

	/**
	 * Method getValue 
	 * @param str
	 * @param startTag
	 * @param endTag
	 * @param aLogger
	 */
	private static String getValue(String str, String startTag, String endTag, Logger aLogger) 
	{
		int start;
		int end;
		int i;
		String myMethodName = "FileLoader::getValue()";
		aLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);
		String resultStr=null;
		i = str.indexOf(startTag);
		start = i + startTag.length();
		i = str.indexOf(endTag);
		end = i;
		resultStr = str.substring(start,end);
		aLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
		
		return resultStr;
		
	}
}
