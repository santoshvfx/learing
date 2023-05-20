// $Id: QmsGeocode.java,v 1.3 2008/02/29 22:54:30 gg2712 Exp $
package com.sbc.ovalshost.client;

/**
 * Creation date: (10/17/00 12:48:28 PM)
 * @author: Donald W. Lee - Local
 */

import com.sbc.ovals.*; // Your Ovals Interface Classes

public class QmsGeocode {
/**
 * QmsGeocode constructor.
 */
public QmsGeocode() {
	super();
}
/**
 * Title:        Access Ovals Production Resources<p>
 * Description:  Uses Ovals workbench to Run transactions on the Prod BackEnds<p>
 * Copyright:    Copyright (c) Paul Vendrick<p>
 * Company:      SBC<p>
 * @author Paul Vendrick
 * @version 1.05bld40
 */


public static void main( String[] args ) throws Exception  {
	  /***
	String[] args
  ) throws Exception {
	new QmsGeocode(args);
  }

  public QmsGeocode(
	String[] args
  ) throws Exception {

  ***/
  
	IWorkbench workbench = null;

	// Create a SINGLETON workbench from the Ovals configuration file
	try {
		// Requires a new xml file which includes setup for the Address Broker resource
		workbench =
			com.sbc.esri.WorkbenchImpl.getInstance( "http://tspgis2a.sbc.com/website/oh_dal110_config.xml");
			// com.sbc.esri.WorkbenchImpl.getInstance( "http://tspgis2a.sbc.com/website/oh_dal106_config.xml");
	} catch( com.sbc.ovals.OvalsException oe){
		  System.err.println("WorkBench Creation Error " + oe);
	}

	// Get references to the geocoder and proximity
	IResource abResource = workbench.getResource( "qms"); 

	// Open the resource
	abResource.open();

	System.out.println( "== Creating an address GeoObject == ");

	// the ABresource had the same methods as the Locate Resource

	IGeoObject address = com.sbc.gis.qms.ABResource.createGeoObject( "7814 greenwood ave", "98103");

	IFeatureList featureList = abResource.getFeatureList( address);

	if( featureList.isEmpty()){
	  System.out.println( "No geocoding candidates found.");
	} else {
		  java.awt.Shape aShape = null;
		  com.sbc.ovals.IMetadata featureMeta;
		  com.sbc.ovals.IFeature aFeature;
		  System.out.println( "Found "+featureList.size()+" features.");
		  for (int n = 0;n <featureList.size();n++) {
			aFeature = featureList.getFeature(n);
			featureMeta = aFeature.getMetadata();

			aShape = aFeature.getShape();

			// Print the feature values (note city and state)
			for (int c = 0;c <featureMeta.getColumnCount();c++) {
				System.out.println( featureMeta.getColumnName(c)+
				  " ("+featureMeta.getColumnLabel(c)+"):"+
				  aFeature.getValue(c) );
			}
			System.out.println( "**** End \n" );

		  }
	}
	abResource.close();
  }
//}

}
