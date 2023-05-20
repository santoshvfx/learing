//$Id: SelfTestResult.java,v 1.2 2008/06/25 17:52:56 ch1463 Exp $
//############################################################################
//#
//# Copyright Notice:
//#
//#     RESTRICTED - PROPRIETARY INFORMATION
//#      The information herein is for use only by authorized employees
//#      of SBC Services Inc. and authorized Affiliates of SBC Services,
//#      Inc., and is not for general distribution within or outside the
//#      respective companies.
//#      Copying or reproduction without prior written approval is prohibited.
//#
//#      © 2002-2005 SBC Knowledge Ventures, L.P. All rights reserved.
//#
//# History :
//# Date      | Author              | Notes
//# --------------------------------------------------------------------
//# 11/28/2006  Jon Costa             Creation.
//#	02/21/2008	Julius Sembrano		  Added validateResultSet() method  for selfTest
//#									  enhancements

package com.sbc.eia.bis.selftest;

import com.sbc.eia.bis.framework.logging.BisLogger;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.idl.bis_types.SystemFailure;
import com.sun.java.util.collections.ArrayList;

/**
* @author jc1421
*
* To change this generated comment edit the template variable "typecomment":
* Window>Preferences>Java>Templates.
* To enable and disable the creation of type comments go to
* Window>Preferences>Java>Code Generation.
*/
public class SelfTestResult
{
	private String type;
	private String description;
	private boolean isOK;

	/**
	 * Constructor for selftestResult.
	 */
	public SelfTestResult(String aType, String aDesc, boolean aIsOK)
	{
		super();
		type = aType;
		description = aDesc;
		isOK = aIsOK;
	}

	/**
	 * Method displayResultSet.
	 * @param bisLogger
	 * @param aResultList
	 */
	public static void displayResultSet(BisLogger bisLogger, ArrayList aResultList)
	{
		String myMethodName = "selftestResult::displayResultSet()";
		bisLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);

		for (int i = 0; i < aResultList.size(); i++)
		{
			SelfTestResult aResult = (SelfTestResult) aResultList.get(i);
			bisLogger.log(
				LogEventId.INFO_LEVEL_1,
				"Type="
					+ aResult.type
					+ " "
					+ "Result="
					+ aResult.isOK
					+ " "
					+ "Desc=<"
					+ aResult.description
					+ ">");
		};

		bisLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
	}

	public static ArrayList validateResultSet(BisLogger bisLogger, ArrayList aResultList){
		
		String myMethodName = "selftestResult::validateResultSet()";
		bisLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);
		
		ArrayList failedResults = new ArrayList();
		
		for (int i = 0; i < aResultList.size(); i++)
		{
			SelfTestResult aResult = (SelfTestResult) aResultList.get(i);
			
			if(aResult.isOK == false)
			{
				String result = aResult.description;
				
				failedResults.add(result);
			}			
		}
	
		
		bisLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
		return failedResults;
	}
	
	/**
	 * Method addResultToList.
	 * @param aType
	 * @param aDesc
	 * @param aIsOK
	 * @param bisLogger
	 * @param aResultList
	 */
	public static void addResultToList(
		String aType,
		String aDesc,
		boolean aIsOK,
		BisLogger bisLogger,
		ArrayList aResultList)
	{
		String myMethodName = "selftestResult::addResultToList()";
		bisLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);

		SelfTestResult aResult = new SelfTestResult(aType, aDesc, aIsOK);
		aResultList.add(aResult);

		bisLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
	}

	/**
	 * Method toString.
	 * @param aResultList
	 * @return String
	 */
	public static String toString(ArrayList aResultList)
	{
		String resultLog = "";

		if (aResultList != null && aResultList.size() > 0)
		{
			for (int i = 0; i < aResultList.size(); i++)
			{
				SelfTestResult aResult = (SelfTestResult) aResultList.get(i);
				resultLog =
					resultLog
						+ "Type="
						+ aResult.type
						+ " "
						+ "Result="
						+ aResult.isOK
						+ " "
						+ "Desc=<"
						+ aResult.description
						+ "\n";
			}
		}
		return resultLog;
	}

	/**
	 * Returns the description.
	 * @return String
	 */
	public String getDescription()
	{
		return description;
	}

	/**
	 * Returns the isOK.
	 * @return boolean
	 */
	public boolean isOK()
	{
		return isOK;
	}

	/**
	 * Returns the type.
	 * @return String
	 */
	public String getType()
	{
		return type;
	}

	/**
	 * Sets the description.
	 * @param description The description to set
	 */
	public void setDescription(String description)
	{
		this.description = description;
	}

	/**
	 * Sets the isOK.
	 * @param isOK The isOK to set
	 */
	public void setIsOK(boolean isOK)
	{
		this.isOK = isOK;
	}

	/**
	 * Sets the type.
	 * @param type The type to set
	 */
	public void setType(String type)
	{
		this.type = type;
	}
}
