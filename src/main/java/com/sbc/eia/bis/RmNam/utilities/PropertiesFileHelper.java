// $Id: PropertiesFileHelper.java,v 1.4 2003/03/21 22:55:38 ts8181 Exp $

package com.sbc.eia.bis.RmNam.utilities;

import java.io.*;
import com.sbc.bccs.utilities.*;
import java.util.*;

import com.sbc.eia.bis.framework.logging.LogEventId ;

/**
 * Insert the type's description here.
 * Creation date: (5/7/01 3:48:13 PM)
 * @author: Sam Lok - Local
 * @deprecated Use com.sbc.bccs.utilities.PropertiesFileLoader instead.
 */
public class PropertiesFileHelper {
/**
 * PropertiesFileHelper constructor comment.
 */
public PropertiesFileHelper() {
	super();
}
/**
 * Read a properties file and return a Properties object.
 * Creation date: (5/3/01 3:56:39 PM)
 * @return java.util.Properties
 * @param propertiesFileName java.lang.String
 * @param utility com.sbc.bccs.utilities.Utility
 * @exception java.io.FileNotFoundException Thrown when the propertiesFileName is undefined or invalid.
 * @exception java.io.IOException Thrown when IO error happens while reading propertiesFile.
 */
public static Properties read(String propertiesFileName, Utility utility)
throws FileNotFoundException, IOException
{
	return PropertiesFileLoader.read( propertiesFileName, utility);
}
}
