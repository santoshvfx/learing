// $Id: QmsAddressSuite.java,v 1.5 2008/02/29 22:53:06 gg2712 Exp $
package com.sbc.ovalshost.client;

/**
 * Title:        Access Ovals Production Resources<p>
 * Description:  Uses Ovals workbench to Run transactions on the Prod BackEnds<p>
 * Copyright:    Copyright (c) Paul Vendrick<p>
 * Company:      SBC<p>
 * @author Paul Vendrick
 * @version 1.05bld40
 */


import com.sun.java.util.collections.ArrayList;

import com.sbc.ovals.*; // Your Ovals Interface Classes


public class QmsAddressSuite {

	// Create some timer array longs to store milliseconds

	private static long[] _timers = new long[5];
	public static StringBuffer sb = new StringBuffer(128);

	public static IResource abResource;
	public static IResource proximityResource;

	public static ILayerList aLayerList;
	public static ILayer aLayer = null;

	public static ArrayList allPolyIds =
		new ArrayList( 30);

	public static ArrayList telephonyIDs =
		new ArrayList( 10);

	public static ArrayList a = new ArrayList( 19);
	public static ArrayList z = new ArrayList( 19);
	
/**
 * QmsAddressSuite constructor comment.
 */
public QmsAddressSuite() {
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


  public static void main(
	String[] args
  ) throws Exception {

	IWorkbench workbench = null;

	// Create a SINGLETON workbench from the Ovals configuration file
	try {
		// Requires a new xml file which includes setup for the Address Broker resource
		workbench =
			com.sbc.esri.WorkbenchImpl.getInstance( "http://tspgis2a.sbc.com/website/oh_dal110_config.xml");
	} catch( com.sbc.ovals.OvalsException oe){
		  System.err.println("WorkBench Creation Error " + oe);
	}

	// Get references to the Address Broker and proximity
	abResource = workbench.getResource( "qms");
	proximityResource = workbench.getResource( "proximity");

	// Open the resources
	abResource.open();
	proximityResource.open();

	// In version 1.05 you can shut off proximity geometry if your app doesn't use it
	// This can retrieval of spatially intensive data

	proximityResource.setGeometryEnabled(false);

	aLayerList = workbench.getLayerList();

	// Capture the layer ids of my polygon layers

	System.out.println( "== Finding Polygon Layers visible to this WorkBench == ");

	for (int n = 0;n <aLayerList.size();n++) {
	  aLayer = aLayerList.getLayer(n);
	  if (aLayer.getLayerType()  ==  com.sbc.ovals.LayerType.Polygon) {
		  // remember the layer ids of Polygons
		  allPolyIds.add( aLayer.getID() );
		  // I also want all polygons which contain SBCT telephony
		  if( aLayer.getName().startsWith("SBCT") ) {
			telephonyIDs.add( aLayer.getID() );
		  }
		  if( aLayer.getName().startsWith("MSA") ) {
			telephonyIDs.add( aLayer.getID() );
		  }
	  } else if (aLayer.getLayerType()  ==  com.sbc.ovals.LayerType.Point) {
	  } else if (aLayer.getLayerType()  ==  com.sbc.ovals.LayerType.Line) {
	  } else {
	  }
	}

	System.out.println( "Each point in this run will search "+telephonyIDs.size()+" polygon Layer(s).");

	validateSomeAddresses();

	System.out.println("AddCount "+a.size()+"\t\t\t\t\t\t\tAll\t"+
			(( ( new java.util.Date() ).getTime() - _timers[4])/ 1000));

	proximityResource.close();
	abResource.close();
  }


/**
 * Insert the method's description here.
 * Creation date: (10/17/00 2:06:35 PM)
 */

private static void validateAddress(
	String anAddress,
	String aZone
	) throws Exception {
	_timers[1] = ( new java.util.Date() ).getTime();

	IGeoObject proximity = null; //Make a base vocabulary interface
	String proxLayer = null;
	double metersAwayToSearch = 0.01;

	com.sbc.ovals.IMetadata pFeatureMeta;
	com.sbc.ovals.IFeature pFeature;
	String pColumnName;

	try{

	  System.out.println( anAddress+","+aZone+"\t\t\t\t\t\t\t");

	  _timers[1] = ( new java.util.Date() ).getTime();

	  // Notice the package change!!! not using "com.sbc.esri"
	  IGeoObject address = com.sbc.gis.qms.ABResource.createGeoObject( anAddress, aZone);

	  IFeatureList featureList = abResource.getFeatureList( address);

	  //Geocode time
	  _timers[1] = ((( ( new java.util.Date() ).getTime() - _timers[1])/ 1000));

	  if( featureList.isEmpty()){
		System.out.print( "\tNOT FOUND\t"+_timers[1]+"\t\t\t\t\t\t");
		System.out.println(_timers[1]);
	  } else {

			//System.out.println( "Found "+featureList.size()+" features.");
			for (int n = 0;n <featureList.size();n++) {
			  IFeature aFeature = featureList.getFeature(n);


			  // ignore any geocoding reponses that are not found

			  if( ( aFeature.getValue("matchcode").toString() ).startsWith("E") ) {
				System.out.print( "\tNOT FOUND\t"+_timers[1]+"\t\t\t\t\t\t");
				System.out.println(_timers[1]);
				continue;
			  }

			  // Assemble an address from the fielded values sent back.

			  sb.setLength(0);
			  sb.append( aFeature.getValue("housenumber").toString() );
			  sb.append( " " );
			  sb.append( aFeature.getValue("prefixdirection").toString() );
			  sb.append( " " );
			  sb.append( aFeature.getValue("streetname").toString() );
			  sb.append( " " );
			  sb.append( aFeature.getValue("streettype").toString() );
			  sb.append( ", " );
			  sb.append( aFeature.getValue("city").toString().toUpperCase() );
			  sb.append( " " );
			  sb.append( aFeature.getValue("state").toString() );
			  sb.append( " " );
			  sb.append( aFeature.getValue("zip").toString() );

			  System.out.print( "\t"+sb.toString()+"\t"+
					aFeature.getValue("matchcode").toString()+"\t");

			  System.out.print(_timers[1]+"\t");

			  // Find all telephony about this address
			  _timers[2] = ( new java.util.Date() ).getTime();

			  for(int p = 0; p < telephonyIDs.size(); p++) {
				_timers[3] = ( new java.util.Date() ).getTime();
				proxLayer = (String) telephonyIDs.get(p);
				proximity = com.sbc.esri.ProximityResource.createGeoObject( aFeature, proxLayer, metersAwayToSearch);

				IFeatureList proximityList = proximityResource.getFeatureList( proximity);

				System.out.print((( ( new java.util.Date() ).getTime() - _timers[3])/ 1000) + "\t");
			  }
			System.out.println((( ( new java.util.Date() ).getTime() - _timers[2])/ 1000) );
		  }
	  }

	} catch (com.sbc.ovals.OvalsException oe) {
	  throw new Exception( oe.toString() );
	}
  }
  
/**
 * Creation date: (10/17/00 2:06:13 PM)
 */
  private static void validateSomeAddresses() {


	a.add("80 Hancock St");            z.add("02139");
	a.add("52 2nd Ave");               z.add("02129");
	a.add("100 Marlboro St");          z.add("02170");
	a.add("65 Wood Ave");              z.add("01702");
	a.add("35 Rocky Hill Rd");         z.add("01803");
	a.add("2510 E Marion");            z.add("98122");
	a.add("650 Maynard Ave");          z.add("98104");
	a.add("950 102nd Ave NE");         z.add("98004");
	a.add("801 Dayton St");            z.add("98020");
	a.add("10580 NE 198th St");        z.add("98011");
	a.add("6625 149th Ave NE");        z.add("98052");
	a.add("10600 Holly Dr");           z.add("98204");
	a.add("21350 66th Ave S");         z.add("98032");
	a.add("12250 SE 166th St");        z.add("98058");
	a.add("777 Not Found Lane");       z.add("98058");
	a.add("8016 NW 162nd St");         z.add("33016");
	a.add("10500 NW 29th Ter");        z.add("33172");
	a.add("9800 SW 191st St");         z.add("33157");
	a.add("550 W 37th St");            z.add("33140");

	// Print column headers
	String proxLayer;
	try{
	  System.out.print("Address\t");
	  System.out.print("Candidate\t");
	  System.out.print("MatchCode\t");
	  System.out.print("Geocode\t");
	  for(int p = 0; p < telephonyIDs.size(); p++) {
		proxLayer = (String) telephonyIDs.get(p);
		System.out.print(aLayerList.getLayerByID(proxLayer).getName()+"\t");
	  }
	  System.out.println("ProxDur");
	} catch (com.sbc.ovals.OvalsException ee) {
	  System.err.println("Layer Access Error: " + ee);
	}

	_timers[4] = ( new java.util.Date() ).getTime();

	// Load some addresses to the host for geocoding and conflation
	com.sbc.ovals.IFeatureList aFeatureList = null;

	try{
	  for(int i = 0; i < a.size(); i++) {  // do all of them in the list
	  //for(int i = 0; i < 1; i++) {  // just do the first one

		_timers[0] = ( new java.util.Date() ).getTime();
		validateAddress( (String) a.get(i), (String) z.get(i) );
		System.out.println("\t\t\t\t\t\t\tAddress\t"+
			(( ( new java.util.Date() ).getTime() - _timers[0])/ 1000));

	  }
	} catch (Exception e) {
	  System.err.println("Validate and get all info error: " + e);
	}
  }

  
}
