// $Id: PropertyLoader.java,v 1.3 2003/04/02 20:22:01 my6546 Exp $

package com.sbc.eia.bis.common.utilities;

import java.util.Properties;
import com.sbc.bccs.utilities.Logger;
import com.sbc.bccs.utilities.PropertiesFileLoader;
/**
 * Helper class to load Properties from a given file
 * Creation date: (2000-04-13 08:25:06 AM)
 *
 * @author: Bill McArthur
 * @version: 1.0.0
 */
public class PropertyLoader {
	private static PropertyLoader instance = null;
/**
 * PropertyLoader constructor comment.
 */
private PropertyLoader() {
	super();
}
/**
 * Insert the method's description here.
 * Creation date: (2000-04-13 08:26:15 AM)
 * @author: Bill McArthur
 *
 * @return com.sbc.bccs.utility.PropertyLoader
 */
public static PropertyLoader getInstance() {
	/*
	Most of the time, the object will exist when getInstance() is
	called, so we won't do any synchronization at all. 
	
	On the first call, however, instance is null, so we enter the 
	if statement and synchronize explicitly on the Class object to 
	enter a critical section. 
	
	Now we have to test for instance==null again, because we might 
	have been preempted just after the first if was processed but 
	before the synchronized statement was executed. 
	
	If instance is still null, then no other thread will be creating
	the singleton, and we can create the object safely.
	
	This version still assures that only one instance of the singleton 
	will be created in this VM, but it won't interfere with the execution of
	other static methods.  As opposed to sychronizing the method 
	which would block execution of all static methods in this class.
	*/
	if (instance == null) {
		synchronized (PropertyLoader.class) {
			if (instance == null) {
				instance = new PropertyLoader();
			}
		}
	}
	return instance;
}
/**
 * Returns Properties object containing the loaded properties from filename
 * Creation date: (2000-04-13 08:30:10 AM)
 * @author: Bill McArthur
 *
 * @return Properties property object containing props from filename, or NULL if error occurs
 * @exception java.io.IOException if resource not found
 */
public Properties loadProperties(String filename) throws java.io.IOException {
	return loadProperties(filename, null);
}
/**
 * Returns Properties object containing the loaded properties from filename
 * Creation date: (2003-04-01 06:55:50 AM)
 * @author: Michael Yeager
 *
 * @return Properties property object containing props from filename, or NULL if error occurs
 * @exception java.io.IOException if resource not found
 */
public Properties loadProperties(String filename, Logger logger) throws java.io.IOException {
	if (filename == null) {
		return null;
	}
	if (filename.indexOf("/") == 0)
		filename = filename.substring(1);
	return PropertiesFileLoader.read(filename, logger);
}
/**
 * Insert the method's description here.
 * Creation date: (2000-04-13 08:26:15 AM)
 * @author: Bill McArthur
 *
 * @param newInstance com.sbc.bccs.utility.PropertyLoader
 */
private static void setInstance(PropertyLoader newInstance) {
	instance = newInstance;
}
}
