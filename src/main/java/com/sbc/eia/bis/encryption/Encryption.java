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
//#	6/2004		Stevan Dunkin	Creation.
//# 6/2004		Jon Costa		Moved key into properties file and assigned name
//# 4/04/2005   Stevan Dunkin   Updated Copyright Notice

package com.sbc.eia.bis.encryption;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;

import java.util.Properties;
import java.util.Random;
import java.util.StringTokenizer;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.rmi.RemoteException;
import java.security.*;

/**
 * @author sd6248
 *
 * Encryption class uses BASE64 encryption and an Exclusive-OR algorithm to encode and decode String Objects
 * with a given key.
 */
public class Encryption {

	/**
	 * Constructor - Empty
	 */
	public Encryption() {
	}


	/**
	 * Method keyEncrypt()
	 * Encrypts or Decrypts byte array objects using Exclusive-OR.
	 * @param text byte array
	 * @param key byte array
	 * @return buffer byte array
	 */
	private byte[] keyEncrypt(byte[] text, byte[] key) {
		byte buffer[] = new byte[text.length];

		for (int i = 0, j = 0; i < text.length; i++, j++) {
			//sets j to zero when end of key array is reached.
			if (j >= key.length) {
				j = 0;
			}
			buffer[i] = (byte) (text[i] ^ key[j]);
		}
		return buffer;
	}

	/**
	 * Method encode()
	 * Encodes a string using the BASE64 algorithm then wraps the 
	 * encoded text in a shared key cipher.
	 * @param text
	 * @return String
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	protected String encode(String key, String text) throws RemoteException {
		byte[] buffer = text.getBytes();
		//System.out.println(new String(buffer));

		BASE64Encoder en = new BASE64Encoder();

		//Creates a random number to insert as a string to front of encrypted password.
		Random randomNum = new Random(System.currentTimeMillis());
		String result = Integer.toString(randomNum.nextInt(9999));
		result = result.length() + result + en.encode(buffer);

		buffer = this.keyEncrypt(result.getBytes(), key.getBytes());

		return new String(buffer);
	}

	/**
	 * Method decode()
	 * Decrypts using key cipher then decodes text using BASE64 algorithm.
	 * @param param Encodes String
	 * @return String decoded String
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	protected String decode(String key, String text)
		throws IOException, RemoteException {

		byte[] buffer = text.getBytes();
		String result = new String(this.keyEncrypt(buffer, key.getBytes()));

		BASE64Decoder de = new BASE64Decoder();

		//Extracts the starting index of the encrypted password from the string.
		int index = Integer.parseInt(result.substring(0, 1)) + 1;

		return new String(de.decodeBuffer(result.substring(index)));
	}

	/**
	 * Method decodePassword()
	 * Decodes password from a given properties files containing password and shared key.
	 * @param param Properties file
	 * @throws RemoteException
	 */
	public String decodePassword(
		String key,
		String password)
		throws RemoteException {
		
		String result = "";
		// If there is no key, in case it's due to encryption not being
		// implemented, return the password as is.
		if ( key == null || key.trim().length() < 1 ) {
			return password;
		}
		
		try {
			//Decode the encrypted password.
			result = this.decode(key, password);
		}
		catch (FileNotFoundException ex) {
			System.out.println(
				"Throwing RemoteException due to FileNotFoundException decoding PASSWORD.");
			throw new RemoteException(
				"Error Decoding PASSWORD::" + ex.getMessage());
		}
		catch (IOException e) {
			System.out.println(
				"Throwing RemoteException due to IOException decoding PASSWORD.");
			throw new RemoteException(
				"Error Decoding PASSWORD::" + e.getMessage());
		}
		catch (Exception e) {
			System.out.println(
				"Throwing RemoteException due to Exception decoding PASSWORD.");
			throw new RemoteException(
				"Error Decoding PASSWORD::" + e.getMessage());
		}
		return result;
	}
}
