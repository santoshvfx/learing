// $Id: ASONServiceAccess.java,v 1.2 2002/09/29 03:54:12 dm2328 Exp $

package com.sbc.gwsvcs.service.asonservice;

import com.sbc.vicunalite.api.*;
import com.sbc.vicunalite.api.orb.*;
import java.io.*;
import com.sbc.gwsvcs.service.asonservice.exceptions.*;
import com.sbc.gwsvcs.service.asonservice.interfaces.*;
import com.sbc.gwsvcs.access.vicuna.*;
import com.sbc.gwsvcs.access.vicuna.exceptions.*;

/**
 * This is the HOSTLOOKUP Access class.
 * Creation date: (4/26/00 11:36:38 AM)
 * @author: David Brawley
 */
public class ASONServiceAccess extends ServiceAccess
{
	public final static String version = "1.0";
	public final static String name = "ASONSERVICE";

	public final static int ASON_DUE_DATE_REQ_NBR			= 1010;
	public final static int ASON_DUE_DATE_RESP_NBR			= 1011;
	public final static int ASON_DUE_DATE_ERR_NBR			= 1012;
	public final static int ASON_SAG_VALIDATION_REQ_NBR 	= 1020;
	public final static int ASON_SAG_VALIDATION_RESP_NBR 	= 1021;
	public final static int ASON_SAG_VALIDATION_ERR_NBR 	= 1022;
	public final static int ASON_SAG_INQUIRY_REQ_NBR 		= 1030;
	public final static int ASON_SAG_INQUIRY_RESP_NBR 		= 1031;
	public final static int ASON_SAG_INQUIRY_ERR_NBR 		= 1032;
	public final static int	ASON_LU_VALIDATION_REQ_NBR 		= 1040;
	public final static int ASON_LU_VALIDATION_RESP_NBR 	= 1041;
	public final static int ASON_LU_VALIDATION_ERR_NBR 		= 1042;
	public final static int ASON_LU_INQUIRY_REQ_NBR 		= 1050;
	public final static int ASON_LU_INQUIRY_RESP_NBR 		= 1051;
	public final static int ASON_LU_INQUIRY_ERR_NBR 		= 1052;
	public final static int DG_TANDEM_ERR_NBR 				= 100;
	public final static int ASON_ERROR_RESP_NBR 			= 9999;

	// added for version ASONSERVICE 1.0 1.2.0
	public final static int ASON_SAG_VALID_REQ_NBR 			= 4441;
	public final static int ASON_SAG_VALID_RESP_NBR 		= 4941;
	public final static int ASON_SAG_VALID_ERR_NBR 			= 4942;
	public final static int ASON_SAG_VALID_DESC_ERR_NBR 	= 4943;
	public final static int ASON_SAG_VALID_APP_ERR_NBR 		= 4944;
	public final static int ASON_SAG_VALID_INVREQ_ERR_NBR 	= 5920;

	
	public final static MEventType ASON_DUE_DATE_REQ 		= new MEventType("ASON_DUE_DATE_REQ");			// Event 1010
	public final static MEventType ASON_DUE_DATE_RESP 		= new MEventType("ASON_DUE_DATE_RESP");			// Event 1011
	public final static MEventType ASON_DUE_DATE_ERR 		= new MEventType("ASON_DUE_DATE_ERR");			// Event 1012
	public final static MEventType ASON_SAG_VALIDATION_REQ	= new MEventType("ASON_SAG_VALIDATION_REQ");	// Event 1020
	public final static MEventType ASON_SAG_VALIDATION_RESP	= new MEventType("ASON_SAG_VALIDATION_RESP");	// Event 1021
	public final static MEventType ASON_SAG_VALIDATION_ERR	= new MEventType("ASON_SAG_VALIDATION_ERR");	// Event 1022
	public final static MEventType ASON_SAG_INQUIRY_REQ 	= new MEventType("ASON_SAG_INQUIRY_REQ");		// Event 1030
	public final static MEventType ASON_SAG_INQUIRY_RESP 	= new MEventType("ASON_SAG_INQUIRY_RESP");		// Event 1031
	public final static MEventType ASON_SAG_INQUIRY_ERR 	= new MEventType("ASON_SAG_INQUIRY_ERR");		// Event 1032
	public final static MEventType ASON_LU_VALIDATION_REQ	= new MEventType("ASON_LU_VALIDATION_REQ");		// Event 1040
	public final static MEventType ASON_LU_VALIDATION_RESP 	= new MEventType("ASON_LU_VALIDATION_RESP");	// Event 1041
	public final static MEventType ASON_LU_VALIDATION_ERR 	= new MEventType("ASON_LU_VALIDATION_ERR");		// Event 1042
	public final static MEventType ASON_LU_INQUIRY_REQ 		= new MEventType("ASON_LU_INQUIRY_REQ");		// Event 1050
	public final static MEventType ASON_LU_INQUIRY_RESP 	= new MEventType("ASON_LU_INQUIRY_RESP");		// Event 1051
	public final static MEventType ASON_LU_INQUIRY_ERR	 	= new MEventType("ASON_LU_INQUIRY_ERR");		// Event 1052
	public final static MEventType DG_TANDEM_ERR 			= new MEventType("DG_TANDEM_ERR");				// Event 100

	// added for version ASONSERVICE 1.0 1.2.0
	public final static MEventType ASON_SAG_VALID_REQ			= new MEventType("ASON_SAG_VALID_REQ");			// Event 4441
	public final static MEventType ASON_SAG_VALID_RESP			= new MEventType("ASON_SAG_VALID_RESP");		// Event 4941
	public final static MEventType ASON_SAG_VALID_ERR			= new MEventType("ASON_SAG_VALID_ERR");			// Event 4942
	public final static MEventType ASON_SAG_VALID_DESC_ERR		= new MEventType("ASON_SAG_VALID_DESC_ERR");	// Event 4943
	public final static MEventType ASON_SAG_VALID_APP_ERR		= new MEventType("ASON_SAG_VALID_APP_ERR");		// Event 4944
	public final static MEventType ASON_SAG_VALID_INVREQ_ERR	= new MEventType("ASON_SAG_VALID_INVREQ_ERR");	// Event 5920

/**
 * Construct a ASONServiceAccess object.
 * Creation date: (4/26/01 12:33:23 PM)
 * @param vicunaXmlFile String
 * @param serviceXmlDir String
 * @param aLogger jcom.sbc.bccs.utilities.Logger
 */
public ASONServiceAccess(String vicunaXmlFile, String serviceXmlDir, com.sbc.bccs.utilities.Logger aLogger) throws ServiceException
{
	super(version, name, 30000, vicunaXmlFile, serviceXmlDir, aLogger);
}
}
