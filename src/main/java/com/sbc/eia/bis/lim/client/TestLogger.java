// $Id: TestLogger.java,v 1.1 2003/11/07 17:07:21 as5472 Exp $

package com.sbc.eia.bis.lim.client;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
/**
 * Log Test results.
 * Creation date: (4/26/01 2:22:10 PM)
 * @author: David Brawley
 */

public class TestLogger {
	
	public static java.lang.String fileLocation;
	public java.io.File file;
	public java.io.FileOutputStream fos;
	public java.io.DataOutputStream dos;
/**
 * Construct a TestLogger object.
 * Creation date: (4/26/01 2:22:10 PM)
 */
public TestLogger() {
	super();
}
/**
 * Construct a TestLogger object.
 * Creation date: (4/26/01 2:24:08 PM)
 * @param aLogLocation java.lang.String
 * @param aHeader java.lang.String
 */
public TestLogger(String aLogLocation, String aHeader) {
	fileLocation = aLogLocation;
	
	file = null;
	fos = null;
	dos = null;

	file = new File(fileLocation);

	try{
		fos = new FileOutputStream(file);
		dos = new DataOutputStream(fos);
	}
	catch(IOException ioe){
		System.out.println(ioe.getMessage());
	}
	println("***************************************************");
	println("***************************************************");
	println("                   " + aHeader);
	println("***************************************************");
	println("***************************************************\r\n");
}
/**
 * Print data to logfile.
 * Creation date: (4/26/01 2:26:20 PM)
 * @param aLogData java.lang.String
 */
public void println(String aLogData) {
	try{
		dos.writeBytes(aLogData + "\r\n");
	}catch (IOException ioe){
		System.out.println(ioe.getMessage());
	}
}
}
