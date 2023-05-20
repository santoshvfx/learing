//###############################################################################
//#
//#   Copyright Notice:
//#
//#       This software/documentation is the proprietary trade secret and
//#       property of SBC Knowledge Ventures, L.P. Receipt or possession of it does not
//#       convey any rights to divulge, reproduce, use or allow others to
//#       use it without the specific written authorization of SBC Knowledge Ventures, L.P.
//#       Use must conform strictly to the license agreement between user and
//#       SBC Knowledge Ventures, L.P.
//#
//#       © 2002-2005 SBC Knowledge Ventures, L.P.  All rights reserved.
//#
//#	History :
//#	Date      | Author        | Notes
//#	----------------------------------------------------------------------------
//# 06/29/04 	Stevan Dunkin	Creation
//# 4/04/2005   Stevan Dunkin   Updated Copyright Notice

package com.sbc.eia.bis.encryption;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Properties;
import java.util.Random;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.OutputStream;

import com.sbc.bccs.utilities.*;

/**
*	Stand alone program used to encrypt and decrypt string objects using a BASE64 cipher.
* 	Arguments:
		"-encode" - used to encrypt string
		"-decode" - used to decrypt string

		string - value to process.
*/
public class Encrypt extends Encryption {

	private final String KEY_NAME = "key_name";
	private final String KEY_PATH = "key_path";
	private final String PASSWORD = "password";
	private final String ENCRYPTED_PASSWORD = "encrypted_password";

	public static void main(String[] args) {
		Encrypt encrypt = new Encrypt();

		if (args[0].equals("-encode") && args.length == 2) {
			encrypt.encryptString(args[1]);
		}
		else if (args[0].equals("-decode") && args.length == 2) {
			encrypt.decryptString(args[1]);
		}
		else {
			System.out.println("***** ERROR *****");
			System.out.println(
				"Must supply argument '-encode' or '-decode' along with the location of the properties file.");
			System.out.println(
				"Example: java Encrypt -encode C:\\parameters.properties");
		}
	}

	/**
	 * Constructor - EMPTY
	 */
	public Encrypt() {

	}

	/**
	 * Method encryptString()
	 * Encrypts string using shared key from given properties files.
	 * @param propLocation
	 */
	public void encryptString(String propLocation) {
		try {
			Properties propValues = this.loadProperties(propLocation);
			Properties prop =
				this.loadProperties(propValues.getProperty(this.KEY_PATH));
			String key =
				prop.getProperty(propValues.getProperty(this.KEY_NAME));

			//Load encrypted password into properties file
			propValues.setProperty(
				this.ENCRYPTED_PASSWORD,
				this.encode(key, propValues.getProperty(this.PASSWORD)));
			this.writeProperties(propValues, propLocation);
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Method decryptString()
	 * Decrypts string using shared key from given properties files.
	 * @param propLocation
	 */
	public void decryptString(String propLocation) {
		try {
			Properties propValues = this.loadProperties(propLocation);
			Properties prop =
				this.loadProperties(propValues.getProperty(this.KEY_PATH));
			String key =
				prop.getProperty(propValues.getProperty(this.KEY_NAME));

			//Decrypt password and load into properties file.
			propValues.setProperty(
				this.PASSWORD,
				this.decode(
					key,
					propValues.getProperty(this.ENCRYPTED_PASSWORD)));
			this.writeProperties(propValues, propLocation);

		}
		catch (Exception e) {
			System.out.println("***** ERROR *****");
			System.out.println("Could not decode string!");
			e.printStackTrace();
		}
	}
	
	/**
		 * Method loadProperties() 
		 * Loads properties file
		 * @param fileLocation
		 * @return Properties file
		 * @throws FileNotFoundException
		 * @throws IOException
		 */
		public Properties loadProperties(String fileLocation)
			throws FileNotFoundException, IOException {
			Properties prop = new Properties();
			File tempFile = new File(fileLocation);
			FileInputStream fileIn = new FileInputStream(tempFile);
			prop.load(fileIn);
			fileIn.close();

			return prop;
		}

	/**
	 * Method writeProperties()
	 * Writes the properties file to the disk.
	 * @param prop - Properties file
	 * @param filePath - Path of Properties file
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void writeProperties(Properties prop, String filePath)
		throws FileNotFoundException, IOException {
		File tempFile = new File(filePath);
		FileOutputStream fileOut = new FileOutputStream(tempFile);
		prop.store(fileOut, null);
		fileOut.close();
	}
}
