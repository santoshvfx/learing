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
//#       � 2002-2005 SBC Knowledge Ventures, L.P.  All rights reserved.
//#
//# History :
//# Date      | Author        | Notes
//# ----------------------------------------------------------------------------
//# 03/30/05    Stevan Dunkin   Creation

package com.sbc.eia.bis.encryption;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

/**
 * Created on Dec 23, 2004
 *
 */
public class EncryptionIO {

    //#Code segment is part of the Sun Microsystems, Inc. java/util/Properties.java code
    private static final String specialSaveChars = "=: \t\r\n\f#!";

    /**
    * Convert a nibble to a hex character
    * @param   nibble  the nibble to convert.
    */
    private static char toHex(int nibble) {
        return hexDigit[(nibble & 0xF)];
    }

    /** A table of hex digits */
    private static final char[] hexDigit =
        { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
    //#End of Sun Microsystems, Inc. java/util/Properties.java code

    public EncryptionIO() {
    }

    /**
     * @param input File
     * @return BufferedReader
     */
    public BufferedReader readFile(File input) {
        BufferedReader read = null;
        try {
            InputStream fileIn = new FileInputStream(input);
            read = new BufferedReader(new InputStreamReader(fileIn, "8859_1"));
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return read;
    }

    /**
     * @param output File
     * @param fileText String
     */
    public void writeFile(File output, String fileText) {

        try {
            FileOutputStream fileOut = new FileOutputStream(output);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fileOut, "8859_1"));
            writer.write(fileText);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    /**
     * @param input File
     * @param propNames List
     * @return Hashtable
     */
    public synchronized Hashtable readFileFor(File input, List propNames) {
        Hashtable hash = null;
        BufferedReader read = this.readFile(input);

        try {
            String line = null;
            while ((line = read.readLine()) != null) {
                Iterator iter = propNames.iterator();
                while (iter.hasNext()) {
                    String tempKey = (String) iter.next();
                    if (line.startsWith(tempKey)) {
                        int index = 0;
                        while (line.charAt(index) != '=') {
                            index++;
                        }
                        hash.put(tempKey, EncryptionIO.loadConvert(line.substring(++index)));
                        break;
                    }
                }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return hash;
    }

    /**
     * @param input File
     * @param propName String
     * @return String
     */
    public synchronized String readFileFor(File input, String propName) {
        String out = new String();
        BufferedReader read = this.readFile(input);
        try {
            String line = null;
            while ((line = read.readLine()) != null) {

                if (line.startsWith(propName)) {
                    int index = 0;
                    while (line.charAt(index) != '=') {
                        index++;
                    }
                    out = line.substring(++index);
                    //System.out.println(index);
                }
            }
            read.close();
            //System.out.println("Before Convert: " + out);
            out = loadConvert(out);
            //System.out.println(out);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return out;
    }

    /**
     * @param input File
     * @param values Hashtable
     */
    public synchronized void changePropValues(File input, Hashtable values) {

        BufferedReader read = this.readFile(input);

        String line = null;
        String out = new String();
        try {
            while ((line = read.readLine()) != null) {
                // TODO add documentation
                Enumeration enum1 = values.keys();
                while (enum1.hasMoreElements()) {
                    String temp = (String) enum1.nextElement();
                    if (line.startsWith(temp)) {
                        int index = 0;
                        while (line.charAt(index) != '=') {
                            index++;
                        }
                        line = line.substring(0, ++index) + EncryptionIO.saveConvert((String) values.get(temp), false);
                        //System.out.println(line);
                        break;
                    }
                }
                out = out + line + "\n";
            }
            //System.out.println(out);
            read.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        this.writeFile(input, out);
    }

    //  #Code segment is part of the Sun Microsystems, Inc. java/util/Properties.java code
    /*
     * Converts encoded &#92;uxxxx to unicode chars
     * and changes special saved chars to their original forms
     */
    public static String loadConvert(String theString) {
        char aChar;
        int len = theString.length();
        StringBuffer outBuffer = new StringBuffer(len);

        for (int x = 0; x < len;) {
            aChar = theString.charAt(x++);
            if (aChar == '\\') {
                aChar = theString.charAt(x++);
                if (aChar == 'u') {
                    // Read the xxxx
                    int value = 0;
                    for (int i = 0; i < 4; i++) {
                        aChar = theString.charAt(x++);
                        switch (aChar) {
                            case '0' :
                            case '1' :
                            case '2' :
                            case '3' :
                            case '4' :
                            case '5' :
                            case '6' :
                            case '7' :
                            case '8' :
                            case '9' :
                                value = (value << 4) + aChar - '0';
                                break;
                            case 'a' :
                            case 'b' :
                            case 'c' :
                            case 'd' :
                            case 'e' :
                            case 'f' :
                                value = (value << 4) + 10 + aChar - 'a';
                                break;
                            case 'A' :
                            case 'B' :
                            case 'C' :
                            case 'D' :
                            case 'E' :
                            case 'F' :
                                value = (value << 4) + 10 + aChar - 'A';
                                break;
                            default :
                                throw new IllegalArgumentException("Malformed \\uxxxx encoding.");
                        }
                    }
                    outBuffer.append((char) value);
                } else {
                    if (aChar == 't')
                        aChar = '\t';
                    else if (aChar == 'r')
                        aChar = '\r';
                    else if (aChar == 'n')
                        aChar = '\n';
                    else if (aChar == 'f')
                        aChar = '\f';
                    outBuffer.append(aChar);
                }
            } else
                outBuffer.append(aChar);
        }
        return outBuffer.toString();
    }

    /*
     * Converts unicodes to encoded &#92;uxxxx
     * and writes out any of the characters in specialSaveChars
     * with a preceding slash
     */
    public static String saveConvert(String theString, boolean escapeSpace) {
        int len = theString.length();
        StringBuffer outBuffer = new StringBuffer(len * 2);

        for (int x = 0; x < len; x++) {
            char aChar = theString.charAt(x);
            switch (aChar) {
                case ' ' :
                    if (x == 0 || escapeSpace)
                        outBuffer.append('\\');

                    outBuffer.append(' ');
                    break;
                case '\\' :
                    outBuffer.append('\\');
                    outBuffer.append('\\');
                    break;
                case '\t' :
                    outBuffer.append('\\');
                    outBuffer.append('t');
                    break;
                case '\n' :
                    outBuffer.append('\\');
                    outBuffer.append('n');
                    break;
                case '\r' :
                    outBuffer.append('\\');
                    outBuffer.append('r');
                    break;
                case '\f' :
                    outBuffer.append('\\');
                    outBuffer.append('f');
                    break;
                default :
                    if ((aChar < 0x0020) || (aChar > 0x007e)) {
                        outBuffer.append('\\');
                        outBuffer.append('u');
                        outBuffer.append(toHex((aChar >> 12) & 0xF));
                        outBuffer.append(toHex((aChar >> 8) & 0xF));
                        outBuffer.append(toHex((aChar >> 4) & 0xF));
                        outBuffer.append(toHex(aChar & 0xF));
                    } else {
                        if (specialSaveChars.indexOf(aChar) != -1)
                            outBuffer.append('\\');
                        outBuffer.append(aChar);
                    }
            }
        }
        return outBuffer.toString();
    }
    //#End of Sun Microsystems, Inc. java/util/Properties.java code

}
