// $Id: OvalsApi.java,v 1.13 2008/02/29 23:27:21 jd3462 Exp $
package com.sbc.eia.bis.lim.ovals.api;

import java.util.*;

import com.sbc.bccs.utilities.exceptionbuilder.*;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.bis.lim.util.*;
import com.sbc.eia.idl.bis_types.*;
import com.sbc.eia.idl.lim.helpers.*;
import com.sbc.eia.idl.lim_types.FieldedAddress;
import com.sbc.eia.idl.types.Severity;
import com.sbc.ovals.IFeatureList;
import com.sbc.ovals.ILayer;
import com.sbc.ovals.LayerType;

/**
 * OvalsApi creates methods to interact with the OVALS APP classes.
 * It is used by OVALS class of the BusinessInterface and GetAddressLocationProperties
 * Creation date: (12/20/01 11:16:39 AM)
 * @author: Rachel Zadok - Local
 */

public class OvalsApi 
{
	public static com.sbc.ovals.IWorkbench workbench = null;
	public com.sbc.ovals.IResource qmsResource;
	public com.sbc.ovals.IResource proximityResource;
	public com.sbc.gis.geo.path.ShapeIndex shapeIndex;
	public com.sbc.ovals.IGeoObject proximity;
	public com.sbc.gis.geo.path.ShapeInfo shapeInfo;
	public com.sbc.ovals.IGeoObject address;
	public com.sbc.ovals.IFeatureList addressList;
	public com.sbc.ovals.IFeature aFeature;
	public com.sbc.ovals.ILayerList aLayerList;
	public com.sbc.ovals.IFeatureList proximityList;

	static com.sun.java.util.collections.ArrayList allPolyIds = null;
	
	public java.util.Hashtable aFeatureTable;

	private static Calendar prevDate = null;

	// OVALS hours of operations
	//
	// ---IMPORTANT--- check hours of operations.
	private final static String OVALS_OPERATION_M_F_S_H = "OVALS_OPERATION_M_F_S_H";
	private final static String OVALS_OPERATION_M_F_S_M = "OVALS_OPERATION_M_F_S_M";
	private final static String OVALS_OPERATION_M_F_E_H = "OVALS_OPERATION_M_F_E_H";
	private final static String OVALS_OPERATION_M_F_E_M = "OVALS_OPERATION_M_F_E_M";
	private final static String OVALS_OPERATION_SAT_S_H = "OVALS_OPERATION_SAT_S_H";
	private final static String OVALS_OPERATION_SAT_S_M = "OVALS_OPERATION_SAT_S_M";
	private final static String OVALS_OPERATION_SAT_E_H = "OVALS_OPERATION_SAT_E_H";
	private final static String OVALS_OPERATION_SAT_E_M = "OVALS_OPERATION_SAT_E_M";
	private final static String OVALS_OPERATION_SUN_S_H = "OVALS_OPERATION_SUN_S_H";
	private final static String OVALS_OPERATION_SUN_S_M = "OVALS_OPERATION_SUN_S_M";
	private final static String OVALS_OPERATION_SUN_E_H = "OVALS_OPERATION_SUN_E_H";
	private final static String OVALS_OPERATION_SUN_E_M = "OVALS_OPERATION_SUN_E_M";
	
	private String startHrMF = null;
	private String startMnMF = null;
	private String endHrMF = null;
	private String endMnMF = null;
	private String startHrSat = null;
	private String startMnSat = null;
	private String endHrSat = null;
	private String endMnSat = null;
	private String startHrSun = null;
	private String startMnSun = null;
	private String endHrSun = null;
	private String endMnSun = null;
		
	private int startHrMFint;
	private int startMnMFint;
	private int endHrMFint;
	private int endMnMFint;
	private int startHrSatint;
	private int startMnSatint;
	private int endHrSatint;
	private int endMnSatint;
	private int startHrSunint;
	private int startMnSunint;
	private int endHrSunint;
	private int endMnSunint;
	private boolean ovalsOffHours = false;

	//
	//  For logging and error handler.
	//
	
	public javax.naming.InitialContext initialContext;
	private java.lang.String hostName;
	private java.lang.String portNumber;

	private LIMBase myUtility;
	
	// For logging purposes
	private String OVALS_HOST_URL = myUtility.OVALS_HOST_URL;
	/**
	 *  Rule file related variables for external error exception builder.
	 */
	ExceptionBuilderResult exBldReslt = null;
	String ruleFile =null;
	
    public OvalsApi() 
    {
    	super();
    }
    
    /**
     * Construct an OvalsApi Object.
     * @param utility     a LIMBase object
     */
    public OvalsApi( LIMBase utility ) 
    {
    	super();
    
    	myUtility = utility;
    	ruleFile = (String) myUtility.getPROPERTIES().get(LIMTag.CSV_FileName_OVALS);
    
    	// Save the OVALS hours of operations.
    	//
    	startHrMF = (String) myUtility.getPROPERTIES().get (OVALS_OPERATION_M_F_S_H);
    	startMnMF = (String) myUtility.getPROPERTIES().get (OVALS_OPERATION_M_F_S_M);
    	endHrMF = (String) myUtility.getPROPERTIES().get (OVALS_OPERATION_M_F_E_H);
    	endMnMF = (String) myUtility.getPROPERTIES().get (OVALS_OPERATION_M_F_E_M);
    	startHrSat = (String) myUtility.getPROPERTIES().get (OVALS_OPERATION_SAT_S_H);
    	startMnSat = (String) myUtility.getPROPERTIES().get (OVALS_OPERATION_SAT_S_M);
    	endHrSat = (String) myUtility.getPROPERTIES().get (OVALS_OPERATION_SAT_E_H);
    	endMnSat = (String) myUtility.getPROPERTIES().get (OVALS_OPERATION_SAT_E_M);
    	startHrSun = (String) myUtility.getPROPERTIES().get (OVALS_OPERATION_SUN_S_H);
    	startMnSun = (String) myUtility.getPROPERTIES().get (OVALS_OPERATION_SUN_S_M);
    	endHrSun = (String) myUtility.getPROPERTIES().get (OVALS_OPERATION_SUN_E_H);
    	endMnSun = (String) myUtility.getPROPERTIES().get (OVALS_OPERATION_SUN_E_M);
    	
    	startHrMFint = Integer.parseInt (startHrMF);
    	startMnMFint = Integer.parseInt (startMnMF);
    	endHrMFint = Integer.parseInt (endHrMF);
    	endMnMFint = Integer.parseInt (endMnMF);
    	startHrSatint = Integer.parseInt (startHrSat);
    	startMnSatint = Integer.parseInt (startMnSat);
    	endHrSatint = Integer.parseInt (endHrSat);
    	endMnSatint = Integer.parseInt (endMnSat);
    	startHrSunint = Integer.parseInt (startHrSun);
    	startMnSunint = Integer.parseInt (startMnSun);
    	endHrSunint = Integer.parseInt (endHrSun);
    	endMnSunint = Integer.parseInt (endMnSun);
    
    }
    /**
     * Construct AddressHandler object from the ovals.IFeature.
     * @return an AddressHandlerOvals object
     * @param poBox boolean
     * @exception BusinessViolation
     * @exception InvalidData
     * @exception SystemFailure
     * @exception AccessDenied
     * @exception ObjectNotFound
     * @exception NotImplemented
     * @exception DataNotFound
     */
    public AddressHandlerOvals buildAddress (boolean poBox) 
        throws 
            BusinessViolation, 
            InvalidData, 
            SystemFailure, 
            AccessDenied, 
            ObjectNotFound, 
            NotImplemented, 
            DataNotFound
    {
    	AddressHandlerOvals ah = new AddressHandlerOvals ();
    	try 
    	{
    		myUtility.log (LogEventId.INFO_LEVEL_2, "Build Fielded Address");
    
    		if (poBox)
    		{
    			ah = new AddressHandlerOvals ("", this.aFeature.getValue("housenumber").toString(), 
    				"", this.aFeature.getValue("housenumber").toString(),
    				"", "", "", "PO BOX", "", "",
    				this.aFeature.getValue("city").toString(),
    				this.aFeature.getValue("state").toString(),
    				this.aFeature.getValue("zip").toString(),
    				this.aFeature.getValue("zip4").toString(),
    				"", "", null, null, null, null, null, null, "");
    		}
    		else
    		{
    			ah = new AddressHandlerOvals ("", "", "", this.aFeature.getValue("housenumber").toString(),
    				"", "", this.aFeature.getValue("prefixdirection").toString(),
    				this.aFeature.getValue("streetname").toString(),
    				this.aFeature.getValue("streettype").toString(),
    				this.aFeature.getValue("postfixdirection").toString(),
    				this.aFeature.getValue("city").toString(),
    				this.aFeature.getValue("state").toString(),
    				this.aFeature.getValue("zip").toString(),
    				this.aFeature.getValue("zip4").toString(),
    				"", "", null, null, null, null, null, null, "");
    		}
    	}
    	catch (com.sbc.ovals.OvalsException e)
    	{
    		disconnect ();;
    		e.printStackTrace();
    		exBldReslt =
    			ExceptionBuilder.parseException(
    				myUtility.getCallerContext(),
    				ruleFile,
    				"",
    				LIMTag.CSV_InternalError,
    				"buildAddress:" + e.getMessage (),
    				true,
    				ExceptionBuilderRule.NO_DEFAULT,
    				null,
    				null,
    				myUtility,
    				"OVALS",
    				Severity.UnRecoverable,
    				null);
    	}
    	catch(Exception e) 
        { 
    		e.printStackTrace();
    		exBldReslt =
    			ExceptionBuilder.parseException(
    				myUtility.getCallerContext(),
    				ruleFile,
    				"",
    				LIMTag.CSV_InternalError,
    				"buildAddress:" + e.getMessage (),
    				true,
    				ExceptionBuilderRule.NO_DEFAULT,
    				null,
    				null,
    				myUtility,
    				"OVALS",
    				Severity.UnRecoverable,
    				null);
    	}
    	return ah;
    }
    
    /**
     * Build Layer Info Table.
     * @return Hashtable
     * @param layerNames ArrayList
     * @param xny String
     * @param aHash Hashtable
     * @exception BusinessViolation
     * @exception InvalidData
     * @exception SystemFailure
     * @exception AccessDenied
     * @exception ObjectNotFound
     * @exception NotImplemented
     * @exception DataNotFound
     */
    public java.util.Hashtable buildLayerInfoTable (
            ArrayList layerNames,
    		java.lang.String xny, 
    		java.util.Hashtable aHash) 
        throws 
            BusinessViolation, 
            InvalidData, 
            SystemFailure, 
            AccessDenied, 
            ObjectNotFound, 
            NotImplemented, 
            DataNotFound
    {
    	com.sbc.gis.geo.path.ShapeInfo shapeInfo = new com.sbc.gis.geo.path.ShapeInfo();
    	com.sbc.gis.geo.path.ShapeIndex shapeIndex =
    			new com.sbc.gis.geo.path.ShapeIndex(com.sbc.gis.geo.path.ShapeIndex.WORLD);
    	com.sbc.gis.geo.path.ShapeFactory shapeFactory = new com.sbc.gis.geo.path.ShapeFactory();
    	
    	myUtility.log(LogEventId.REMOTE_CALL, OVALS_HOST_URL, OVALS_HOST_URL, "OVALSQMS", "Layers");
    	try
    	{
    		myUtility.log( LogEventId.DEBUG_LEVEL_2,"In buildLayerInfoTable()");
    
    		/*
    		 * For purely academic reasons, lets print out a list of the Layers
    		 * which are currently visible to the resources of the WorkBench.
    		 */
    		ILayer aLayer = null;
    
    		//
    		// Load GIS layer names once if needed.
    		//
    		if (allPolyIds == null) 
    		{
    			allPolyIds   = new com.sun.java.util.collections.ArrayList( 10 );
    			
    			myUtility.log( LogEventId.DEBUG_LEVEL_2,"== Examining Layers visible to this WorkBench == ");
    			String tmpLayerType = null;
    			String tmpLayerName    = null;
    			LayerType curLayerType = null;
    		
    			for (int n = 0;n <aLayerList.size();n++) 
    			{
    	  			aLayer = aLayerList.getLayer(n);
    	  			curLayerType = aLayer.getLayerType();
    	  			if (curLayerType  ==  com.sbc.ovals.LayerType.Polygon) 
    	  			{
    		  			tmpLayerName = aLayer.getName();
    		  			if( layerNames.contains (tmpLayerName))
    		  			{						
    		  				// remember the layer ids of Polygons
    		  				allPolyIds.add( aLayer.getID() );
    					} 
    					tmpLayerType = " Type (Polygon)" ;
    	  			} 
    	  			else if (curLayerType  ==  com.sbc.ovals.LayerType.Point) {
    	  				tmpLayerType = " Type (Point)" ;
    	  			} else if (curLayerType  ==  com.sbc.ovals.LayerType.Line) {
    		  			tmpLayerType = " Type (Line)" ;
    	  			} else {
    		  			tmpLayerType = " Type (Unknown)" ;
    	  			}
    	  			myUtility.log( LogEventId.DEBUG_LEVEL_2,"Name:"+aLayer.getName()+" | "+"LayerID:"+aLayer.getID() + tmpLayerType );
    	 		
    			}  // end for loop
    		} // end for allPolyIds
    
    		myUtility.log( LogEventId.DEBUG_LEVEL_1, "Discovered <"+allPolyIds.size()+"> polygon Layer(s).");
    		//
    		// rz -- Temporary fix, check with Paul
    		// 
    
    		if (allPolyIds.size() == 0)
    			throw new SystemFailure (null, new com.sbc.eia.idl.types.ExceptionData (null, "Problem with Ovals Layer, Please try again", null, null));
    			
    		com.sbc.ovals.IMetadata pFeatureMeta;
    		com.sbc.ovals.IFeature pFeature;
    		String pColumnName;
    		this.proximity   = null;
    		String proxLayer = null;
    		double metersAwayToSearch = 1;
    		String aliasTabName = null;
    		String tmpColumnNm = null;
    		String tmpName = null;
    		Object ob = null ;		
    
    		java.awt.geom.GeneralPath aShape = new java.awt.geom.GeneralPath();
    	  
    	  	aShape = shapeFactory.createShape("0@" + xny);
    	  	myUtility.log( LogEventId.INFO_LEVEL_1,"Looking for point: " + xny );
    	  	
    	  	// Make this point into an empty feature
    	  	com.sbc.esri.FeatureImpl arbitraryFeature =
    		  	new com.sbc.esri.FeatureImpl( new com.sbc.esri.MetadataImpl() );
    
    	  	arbitraryFeature.setShape( aShape );
    	  	
    	  	// Find all telephony about this Arbitrary Point
    	  	for(int p = 0; p < allPolyIds.size(); p++) 
    	  	{
    			proxLayer = (String) allPolyIds.get(p);
    
    			// Skip the following layers for BIS AM request.
    			//
    			proximity = com.sbc.esri.ProximityResource.createGeoObject( arbitraryFeature, proxLayer, metersAwayToSearch);
    
    			myUtility.log( LogEventId.DEBUG_LEVEL_1, "Looking for:"+aLayerList.getLayerByID(proxLayer).getName()+ ", Layer:"+proxLayer );
    			IFeatureList proximityList = proximityResource.getFeatureList( proximity);
    
    			if( proximityList.isEmpty())
    			{
    		  		myUtility.log( LogEventId.DEBUG_LEVEL_1, "Cannot find any features.");
    			} 
    			else 
    			{
    				aliasTabName = aLayerList.getLayerByID(proxLayer).getName() + "." ; // Save the alias table name.
    				
    				// Print the telephony data about this point
    		  		for (int z = 0;z <proximityList.size();z++) 
    		  		{
    					pFeature = proximityList.getFeature(z);
    					pFeatureMeta = pFeature.getMetadata();
    					
    					for (int c = 0;c <pFeatureMeta.getColumnCount();c++) 
    					{
    			  			pColumnName = pFeatureMeta.getColumnName(c);
    			  			tmpColumnNm = "";
    			  			myUtility.log( LogEventId.DEBUG_LEVEL_2, pFeature.getValue(pColumnName) + "-->" + pColumnName );
    
    			  			StringTokenizer columnNameTok = new StringTokenizer (pColumnName, "." );
    						try 
    						{
    							//  Pick up the actual table column name which is the 3rd element.
    							//
    							ob = columnNameTok.nextElement() ;
    							ob = columnNameTok.nextElement() ;
    							tmpColumnNm = columnNameTok.nextElement().toString();
    						}
    						catch ( Exception e ) { /* ignored */ }
    
    						// Store it in hash table. We want this.
    						tmpColumnNm = aliasTabName + tmpColumnNm;
    						if ( aHash.containsKey( tmpColumnNm ) )
    			  				aHash.put(tmpColumnNm, pFeature.getValue(pColumnName) );
    					}
    					myUtility.log( LogEventId.DEBUG_LEVEL_2, "**** End" );
    		  		}
    			}
    	  	} // end allPloyIds
    	}
    	catch(com.sbc.ovals.OvalsException e) 
    	{
    		e.printStackTrace();
    		myUtility.log( LogEventId.DEBUG_LEVEL_2,"OvalsException caught in buildLayerInfoTable -- " + e); 
    		exBldReslt =
    			ExceptionBuilder.parseException(
    				myUtility.getCallerContext(),
    				ruleFile,
    				"",
    				LIMTag.CSV_InternalError,
    				"buildLayerInfoTable:" + e.getMessage (),
    				true,
    				ExceptionBuilderRule.NO_DEFAULT,
    				null,
    				null,
    				myUtility,
    				"OVALS",
    				Severity.UnRecoverable,
    				null);
    	}
    	catch(java.lang.NullPointerException e) 
    	{
    		e.printStackTrace();
    		myUtility.log( LogEventId.DEBUG_LEVEL_2,"*** NullPointerException caught in buildLayerInfoTable -- " + e.getMessage() );
    		exBldReslt =
    			ExceptionBuilder.parseException(
    				myUtility.getCallerContext(),
    				ruleFile,
    				"",
    				LIMTag.CSV_InternalError,
    				"buildLayerInfoTable:" + e.getMessage (),
    				true,
    				ExceptionBuilderRule.NO_DEFAULT,
    				null,
    				null,
    				myUtility,
    				"OVALS",
    				Severity.UnRecoverable,
    				null);
    	}
    	catch(java.lang.Exception e) 
    	{
    		e.printStackTrace();
    		myUtility.log( LogEventId.DEBUG_LEVEL_2,"Exception caught in buildLayerInfoTable -- " + e.getMessage() );
    		exBldReslt =
    			ExceptionBuilder.parseException(
    				myUtility.getCallerContext(),
    				ruleFile,
    				"",
    				LIMTag.CSV_InternalError,
    				"buildLayerInfoTable:" + e.getMessage (),
    				true,
    				ExceptionBuilderRule.NO_DEFAULT,
    				null,
    				null,
    				myUtility,
    				"OVALS",
    				Severity.UnRecoverable,
    				null);
    	}
    
    	myUtility.log(LogEventId.REMOTE_RETURN, OVALS_HOST_URL, OVALS_HOST_URL, "OVALSQMS", "Layers");
    	return aHash;
    }
    
    /**
     * This method checks if there is a need to establish new connection to OVALS.
     * @exception BusinessViolation
     * @exception InvalidData
     * @exception SystemFailure
     * @exception AccessDenied
     * @exception ObjectNotFound
     * @exception NotImplemented
     * @exception DataNotFound
     */
    public void checkConnection () 
        throws 
            BusinessViolation, 
            InvalidData, 
            SystemFailure, 
            AccessDenied, 
            ObjectNotFound, 
            NotImplemented, 
            DataNotFound
    {
    	myUtility.log (LogEventId.INFO_LEVEL_2, "in checkConnection...");
    	ovalsOffHours = false;
    	
    	try
    	{
    		Calendar currDate = Calendar.getInstance(TimeZone.getTimeZone("CST"));
    		
    		// First check if we are within OVALS opertions hours, if we are not => return true,
    		// always try to connect if we are not within OVALS opertions hours.
    		//
    		int currDay = currDate.get(Calendar.DAY_OF_WEEK);
    		int currHour = currDate.get(Calendar.HOUR_OF_DAY);
    		int currMin = currDate.get(Calendar.MINUTE);
    			
    		if (currDay == 7)
    		{
    			if (currHour < startHrSatint || 
    				currHour == startHrSatint && currMin <= startMnSatint)
    			{
    				myUtility.log (LogEventId.INFO_LEVEL_2, "Sat - before hours");
    				ovalsOffHours = true;
    				connect ();
    				return;
    			}
    			if (currHour > endHrSatint ||
    				currHour == endHrSatint && currMin >= endMnSatint)
    			{
    				myUtility.log (LogEventId.INFO_LEVEL_2, "Sat - after hours");
    				ovalsOffHours = true;
    				connect ();
    				return;
    			}
    			else
    			{
    				ovalsOffHours = false;
    			}
    		}
    		else if (currDay == 1)
    		{
    			if (startHrSunint == 0 && endHrSunint == 0)
    			{
    				myUtility.log (LogEventId.INFO_LEVEL_2, "Sun - no operation");
    				ovalsOffHours = true;
    				connect ();
    				return;
    			}
    			if (currHour < startHrSunint || 
    				currHour == startHrSunint && currMin <= startMnSunint)
    			{
    				myUtility.log (LogEventId.INFO_LEVEL_2, "Sun - before hours");
    				ovalsOffHours = true;
    				connect ();
    				return;
    			}
    			if (currHour > endHrSunint ||
    				currHour == endHrSunint && currMin >= endMnSunint)
    			{
    				myUtility.log (LogEventId.INFO_LEVEL_2, "Sun - after hours");
    				ovalsOffHours = true;
    				connect ();
    				return;
    			}
    			else
    			{
    				ovalsOffHours = false;
    			}
    		}
    		else
    		{
    			if (currHour < startHrMFint || 
    				currHour == startHrMFint && currMin <= startMnMFint)
    			{
    				myUtility.log (LogEventId.INFO_LEVEL_2, "M-F - before hours");
    				ovalsOffHours = true;
    				connect ();
    				return;
    			}
    			if (currHour > endHrMFint ||
    				currHour == endHrMFint && currMin >= endMnMFint)
    			{
    				myUtility.log (LogEventId.INFO_LEVEL_2, "M-F after hours");
    				ovalsOffHours = true;
    				connect ();
    				return;
    			}
    			else
    			{
    				ovalsOffHours = false;
    			}
    		}
    
    		// if prevDate == null => JVM is initiated => set prevDate and connection to OVALS
    		//
    		if (prevDate == null)
    		{
    			myUtility.log (LogEventId.INFO_LEVEL_2, "Initializing Connection, Previous Date is null.");
    			updateDate (currDate);
    			connect ();
    			return;
    		}
    		else
    		{			
    			// if currDate != prevDate => new day, reinitialize the connection to OVALS.
    			//
    			int prevY = prevDate.get(Calendar.YEAR);
    			int prevM = prevDate.get(Calendar.MONTH);
    			int prevD = prevDate.get(Calendar.DAY_OF_MONTH);
    			int currY = currDate.get(Calendar.YEAR);
    			int currM = currDate.get(Calendar.MONTH);
    			int currD = currDate.get(Calendar.DAY_OF_MONTH);
    						
    			if (currD != prevD || currM != prevM || currD != prevD)
    			{
    				myUtility.log (LogEventId.INFO_LEVEL_2, "New Day, reconnect...");
    				updateDate (currDate);
    				connect ();
    				return;
    			}
    			else if (workbench == null)
    			{
    				myUtility.log (LogEventId.INFO_LEVEL_2, "workbench is null...");
    				connect ();
    				return;
    			}
    			else
    				return;
    		}
    	}
    	catch(BusinessViolation e)
    	{
    		throw e;
    	}
    	catch(InvalidData e)
    	{
    		throw e;
    	}
    	catch(SystemFailure e)
    	{
    		throw e;
    	}
    	catch(AccessDenied e)
    	{
    		throw e;
    	}
    	catch(ObjectNotFound e)
    	{
    		throw e;
    	}
    	catch(NotImplemented e)
    	{
    		throw e;
    	}
    	catch(DataNotFound e)
    	{
    		throw e;
    	}
    }
    /**
     * Connect to the OVALS system.
     * @exception BusinessViolation
     * @exception InvalidData
     * @exception SystemFailure
     * @exception AccessDenied
     * @exception ObjectNotFound
     * @exception NotImplemented
     * @exception DataNotFound
     */
    public void connect () 
        throws 
            BusinessViolation, 
            InvalidData, 
            SystemFailure, 
            AccessDenied, 
            ObjectNotFound, 
            NotImplemented, 
            DataNotFound
    {
    	try
    	{
    		myUtility.log(LogEventId.REMOTE_CALL, OVALS_HOST_URL, OVALS_HOST_URL, "OVALSQMS", "setWorkbench");
    
    		if (workbench != null)
    			disconnect ();
    
    		setWorkbench (OVALS_HOST_URL);
    
    		myUtility.log(LogEventId.REMOTE_RETURN, OVALS_HOST_URL, OVALS_HOST_URL, "OVALSQMS", "setWorkBench");
    		myUtility.log (LogEventId.INFO_LEVEL_2, "WorkBench is ready. Ovals Host URL = " + OVALS_HOST_URL );
    	}
    	catch(Exception e)
    	{
    		workbench = null;
    		e.printStackTrace();
    		if (ovalsOffHours)
    			exBldReslt =
    				ExceptionBuilder.parseException(
    					myUtility.getCallerContext(),
    					ruleFile,
    					"",
    					LIMTag.CSV_InternalError,
    					"connect off hours:" + e.getMessage (),
    					true,
    					ExceptionBuilderRule.NO_DEFAULT,
    					null,
    					null,
    					myUtility,
    					"OVALS",
    					Severity.UnRecoverable,
    					null);
    		else
    			exBldReslt =
    				ExceptionBuilder.parseException(
    					myUtility.getCallerContext(),
    					ruleFile,
    					"",
    					LIMTag.CSV_InternalError,
    					"connect:" + e.getMessage (),
    					true,
    					ExceptionBuilderRule.NO_DEFAULT,
    					null,
    					null,
    					myUtility,
    					"OVALS",
    					Severity.UnRecoverable,
    				null);
    	}
    
    	try
    	{
    		myUtility.log (LogEventId.INFO_LEVEL_2, "Connecting to OVALS Host server.");
    		
    		shapeInfo = new com.sbc.gis.geo.path.ShapeInfo();
    		myUtility.log (LogEventId.INFO_LEVEL_2, "ShapeInfo was instantiated." ) ;
    		
    		shapeIndex = new com.sbc.gis.geo.path.ShapeIndex(com.sbc.gis.geo.path.ShapeIndex.WORLD);
    		myUtility.log (LogEventId.INFO_LEVEL_2, "ShapeIndex was instantiated." ) ;
    		
    		aLayerList = workbench.getLayerList();
    		myUtility.log (LogEventId.INFO_LEVEL_2, "LayerList was set." ) ;
    		
    		qmsResource = workbench.getResource( "qms");
    		proximityResource = workbench.getResource( "proximity");
    		myUtility.log (LogEventId.INFO_LEVEL_2, "QMS and Proximity were set." ) ;
    		
    		qmsResource.open();
    		proximityResource.open();
    		myUtility.log (LogEventId.INFO_LEVEL_2, "QMSResource and Proximity are ready.");
    	
    		address = null;
    		proximity = null;
    
    	}
    	catch(Exception e)
    	{
    		workbench = null;
    		e.printStackTrace();
    		exBldReslt =
    			ExceptionBuilder.parseException(
    				myUtility.getCallerContext(),
    				ruleFile,
    				"",
    				LIMTag.CSV_InternalError,
    				"connect:" + e.getMessage (),
    				true,
    				ExceptionBuilderRule.NO_DEFAULT,
    				null,
    				null,
    				myUtility,
    				"OVALS",
    				Severity.UnRecoverable,
    				null);
    	}
    	return;
    }
    /**
     * DisConnect from the OVALS system.
     * @exception BusinessViolation
     * @exception InvalidData
     * @exception SystemFailure
     * @exception AccessDenied
     * @exception ObjectNotFound
     * @exception NotImplemented
     * @exception DataNotFound
     */
    
    public void disconnect() 
        throws 
            BusinessViolation, 
            InvalidData, 
            SystemFailure, 
            AccessDenied, 
            ObjectNotFound, 
            NotImplemented, 
            DataNotFound
    {
    	try 
    	{		
    		myUtility.log(LogEventId.REMOTE_CALL, OVALS_HOST_URL, OVALS_HOST_URL, "OVALSQMS", "disposeWorkbench");
    		myUtility.log (LogEventId.INFO_LEVEL_2, "DisConnecting from OVALS Host server.");
    		qmsResource.close ();
    		qmsResource = null;
    		proximityResource.close();
    		proximityResource = null;
    		if (workbench != null)
    			disposeWorkbench ();
    		allPolyIds = null;
    		myUtility.log(LogEventId.REMOTE_RETURN, OVALS_HOST_URL, OVALS_HOST_URL, "OVALSQMS", "disposeWorkbench");
    	}
    	catch (com.sbc.ovals.OvalsException e) 
    	{
    		workbench = null;
    		myUtility.log (LogEventId.FAILURE, "OvalsException while DisConnecting. " + e.getMessage());
    		/* ignored and continue, not important */
    	}
    	catch (Exception e) 
    	{
    		workbench = null;
    		myUtility.log (LogEventId.FAILURE, "Exception while DisConnecting. " + e.getMessage());
    		/* ignored and continue, not important */
    	}
    	
    	return;
    }
    /**
     * dispose Workbench.
     * @exception Exception
     */
    private static synchronized void  disposeWorkbench ()
        throws Exception
    {
    	try
    	{
    		System.out.println ( "About to call disposeWorkbench at " + new Date());
    		workbench.dispose ();
    		workbench = null;
    		System.out.println ( "About to call System.qc at " + new Date());
    		System.gc ();
    		System.out.println ( "Back from disposeWorkbench at " + new Date());
    	}
    	catch (Exception e)
    	{
    		e.printStackTrace();
    		/* ignored and continue, not important */
    	}
    	return;
    }
    /**
     * Get List of Addresses from OVALS.
     * @return boolean true if OVALS returns list of addresses, false if list is empty 
     * @param ah AddressHandler
     * @param poBox boolean
     * @exception BusinessViolation
     * @exception InvalidData
     * @exception SystemFailure
     * @exception AccessDenied
     * @exception ObjectNotFound
     * @exception NotImplemented
     * @exception DataNotFound
     */
    public boolean findValidRange (AddressHandler ah, boolean poBox) 
        throws 
            BusinessViolation, 
            InvalidData, 
            SystemFailure, 
            AccessDenied, 
            ObjectNotFound, 
            NotImplemented, 
            DataNotFound
    {	
    	boolean x = false;
    	
    	try
    	{
    		myUtility.log (LogEventId.INFO_LEVEL_2, "In findValidRange()");
    	
    		String streetAddr = "";
    		String cityPostAddr = "";
    		
    		if (ah.isFielded ())
    		{
    			if (poBox)
    			{
    				streetAddr = "PO BOX ";
    				if (ah.getBox() != null)
    					streetAddr += ah.getBox ();
    			}
    			else
    			{
    				if (ah.getHousNbr() != null)
    					streetAddr = ah.getHousNbr();
    				if (ah.getHousNbrSfx() != null)
    					streetAddr = streetAddr + " " + ah.getHousNbrSfx();
    				if (ah.getStDir() != null)
    					streetAddr = streetAddr + " " + ah.getStDir();
    				if (ah.getStName() != null)
    					streetAddr = streetAddr + " " + ah.getStName();
    				if (ah.getStThorofare() != null)
    					streetAddr = streetAddr + " " + ah.getStThorofare();
    				if (ah.getStNameSfx() != null) 
    					streetAddr = streetAddr + " " + ah.getStNameSfx();
    			}
    		}
    		else
    		{
    			if (ah.getAddressLine() != null)
    				streetAddr = ah.getAddressLine();
    		}
    		
    		if (ah.getCity() != null)
    			cityPostAddr = ah.getCity();
    		if (ah.getState() != null)
    			cityPostAddr = cityPostAddr + " " + ah.getState();
    		if (ah.getPostalCode() != null)
    			cityPostAddr = cityPostAddr + " " + ah.getPostalCode();
    
    		myUtility.log (LogEventId.INFO_LEVEL_2, "Calling GeocodeResource createGeoObject");			
    		
    		myUtility.log(LogEventId.REMOTE_CALL, OVALS_HOST_URL, OVALS_HOST_URL, "OVALSQMS", "FeatureList");
    		address = com.sbc.gis.qms.ABResource.createGeoObject(streetAddr, cityPostAddr);
    		
    		myUtility.log (LogEventId.INFO_LEVEL_2, "Calling GeocodeResource getFeatureList with <" + streetAddr + " " + cityPostAddr + ">");
    		
    		this.addressList = this.qmsResource.getFeatureList (this.address);
    
    		myUtility.log (LogEventId.INFO_LEVEL_2, "GeocodeResource FeatureList Count ===>" + this.addressList.size() + "<===");
    
    		// Check if the geocoding operation was successful
    		if (this.addressList.isEmpty()) 
            {
    			myUtility.log (LogEventId.INFO_LEVEL_2, "OVALS AddressList is empty...");			
    			x = false;
    		} else 
            {
    			x = true;
    		}
    	}
    	catch (com.sbc.ovals.OvalsException e) 
        {
    		e.printStackTrace();
    		myUtility.log (LogEventId.INFO_LEVEL_2,"OvalsException caught in findValidRange -- " + e );
    		// disconnect ();
    		exBldReslt =
    				ExceptionBuilder.parseException(
    					myUtility.getCallerContext(),
    					ruleFile,
    					"",
    					LIMTag.CSV_InternalError,
    					"findValidRange:" + e.getMessage (),
    					true,
    					ExceptionBuilderRule.NO_DEFAULT,
    					null,
    					null,
    					myUtility,
    					"OVALS",
    					Severity.UnRecoverable,
    					null);
    	}
    	catch (Exception e) 
        {
    		e.printStackTrace();
    		myUtility.log (LogEventId.INFO_LEVEL_2,"OvalsException caught in findValidRange -- " + e );
    		// disconnect ();
    		exBldReslt =
    				ExceptionBuilder.parseException(
    					myUtility.getCallerContext(),
    					ruleFile,
    					"",
    					LIMTag.CSV_InternalError,
    					"findValidRange:" + e.getMessage (),
    					true,
    					ExceptionBuilderRule.NO_DEFAULT,
    					null,
    					null,
    					myUtility,
    					"OVALS",
    					Severity.UnRecoverable,
    					null);
    	}
    	
    	myUtility.log(LogEventId.REMOTE_RETURN, OVALS_HOST_URL, OVALS_HOST_URL, "OVALSQMS", "FeatureList");
    	return x;
    }
    
    /**
     * get Workbench Instance.
     * @exception Exception
     */
    private static synchronized void  setWorkbench (String ovalsHostUrl)
        throws Exception
    {
    	try
    	{
    		System.out.println ( "About to call WorkbenchImpl.getInstance at " + new Date());
    		workbench = com.sbc.esri.WorkbenchImpl.getInstance( ovalsHostUrl );
    		System.out.println ( "Back from WorkbenchImpl.getInstance at " + new Date());
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    		throw e;
    	}
    	return;
    }
    /**
     * update Previous Date with current Date, this is done only once a day.
     */
    private static synchronized void  updateDate (Calendar date)
    {
    	prevDate = date;
    	return;
    }
}
