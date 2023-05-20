// $Id: FACSAccessAccess.java,v 1.1 2002/09/29 04:15:31 dm2328 Exp $

package com.sbc.gwsvcs.service.facsaccess;

import com.sbc.vicunalite.api.*;
import com.sbc.vicunalite.api.orb.*;
import java.io.*;
import com.sbc.gwsvcs.access.vicuna.*;
import com.sbc.gwsvcs.access.vicuna.exceptions.*;

/**
 * Insert the type's description here.
 * Creation date: 
 * @author: Ram Kishore (rk7964) 
 */
public class FACSAccessAccess extends ServiceAccess {

	public final static String name    = "FACSAccess";
	public final static String version = "5.0.0"; 

	public final static int	INQ_FASG_REQ_NBR 				= 5120;	
	public final static int	INQ_FASG_DATA_RESP_NBR     	  	= 5121; 

	public final static int FACS_ERROR_NBR                = 9999;
	

	public final static MEventType INQ_FASG_REQ =   new MEventType("INQ_FASG_REQ");								//Event 5120			
	public final static MEventType INQ_FASG_DATA_RESP =            new MEventType("INQ_FASG_DATA_RESP");			//Event 5121 

	public final static MEventType FACS_ERROR =                  new MEventType("FACS_ERROR");                  //Event 9999
	
	
/**
 * FACSAccessAccess constructor comment.
 * @param version java.lang.String
 * @param xmlName java.lang.String
 * @param aDefaultTimeOut long
 * @param vicunaXmlFile java.lang.String
 * @param serviceXmlDir java.lang.String
 * @param aLogger com.sbc.bccs.utilities.Logger
 * @exception com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException The exception description.
 */
public FACSAccessAccess(String vicunaXmlFile, String serviceXmlDir, com.sbc.bccs.utilities.Logger aLogger) throws ServiceException {
	super(version, name, 30000, vicunaXmlFile, serviceXmlDir, aLogger);
}
}
