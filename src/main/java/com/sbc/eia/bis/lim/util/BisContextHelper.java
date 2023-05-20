//$Id: BisContextHelper.java,v 1.1 2008/06/25 14:42:56 nl9267 Exp $
//###############################################################################
//#
//#   Copyright Notice:
//#
//#       This software/documentation is the proprietary trade secret and
//#       property of SBC Services, Inc. Receipt or possession of it does not
//#       convey any rights to divulge, reproduce, use or allow others to
//#       use it without the specific written authorization of SBC Services, Inc.
//#       Use must conform strictly to the license agreement between user and
//#       SBC Services, Inc.
//#
//#       (C) SBC Services, Inc 2004.  All Rights Reserved.
//#
//# History :
//# Date      | Author        | Notes
//# ----------------------------------------------------------------------------------------
//# 06/08/06	Doris Sunga		initial author...
//# 10/06/06    Jyothi Jasti    Added initialize method.
//# 06/18/2008  Noemi Luzung    Enhancements for Selftest and Ping
//###############################################################################

package com.sbc.eia.bis.lim.util;

import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.types.ObjectProperty;
import com.sbc.eia.bis.embus.service.access.SBCLoggingIDProvider;
import java.util.Hashtable;

public class BisContextHelper {
	
	public static BisContext setBisContext(String aApplication, 
			String aCustomerName, 
			String aBusinessUnit,
			String aLoggingInformation,
			Hashtable aProperties) 
	{
		//ObjectProperty[] of 4 pairs of tag/value...
		ObjectProperty[] temp = new ObjectProperty[4];
	
		//create aBisContext properties ..
		if (aApplication != null) {
			temp[0] = new ObjectProperty("Application",aApplication);
		} else temp[0] = new ObjectProperty("Application","LIM_BIS");
		
		if (aCustomerName != null) {
			temp[1] = new ObjectProperty("CustomerName",aCustomerName);
		} else temp[1] = new ObjectProperty("CustomerName","LIM_BIS");

		if (aBusinessUnit != null) {
			temp[2] = new ObjectProperty("BusinessUnit",aBusinessUnit);
		} else temp[2] = new ObjectProperty("BusinessUnit","LIM_BIS");

		if (aLoggingInformation != null){
			temp[3] = new ObjectProperty("LoggingInformation",aLoggingInformation);
		} else {
			SBCLoggingIDProvider loggingIDProvider = new SBCLoggingIDProvider(
					(String) aProperties.get("BIS_NAME"),
					(String) aProperties.get("BIS_VERSION"));
			temp[3] = new ObjectProperty("LoggingInformation",loggingIDProvider.getCorrelationId());
		}												

		return new BisContext(temp);
	}
	
	/**
	 * initialize method returns initialized BisContext if BisContext or BisContext.ObjectProperties is null.
	 * @param aContext
	 * @return BisContext
	 */
	public static BisContext initialize( BisContext aContext )
	{
		
		if ( aContext == null )
		{
			ObjectProperty objProperties[] = { new ObjectProperty()};
			aContext = new BisContext(objProperties);
		}
		else if ( aContext.aProperties == null)
		{
				ObjectProperty objProperties[] = { new ObjectProperty()};
				aContext.aProperties = objProperties;
		}
		
		return aContext;
	}	

}
