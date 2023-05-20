//$Id: TestCaseHelper.java,v 1.6 2008/02/29 23:27:20 jd3462 Exp $
package com.sbc.eia.bis.lim.client;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import java.util.*;
import com.sbc.bccs.utilities.PropertiesFileLoader;

/**
 * @author gg2712
 */
public class TestCaseHelper
{
    private Vector m_testCases = null;;

    private String m_testFile = null;

    private static final String CLIENT_TEST_FILE_VALUE_DELIM = ",";

    private List testFileNames = null;

    /**
     * Contructor
     */
    public TestCaseHelper(String testFile) throws LimClientException
    {
        if (testFile == null || testFile.trim().length() == 0)
        {
            throw new LimClientException("String parameter testFile is null or zero-length.");
        }
        m_testFile = testFile;
        loadTestCases();
    }

    /**
     * Load test cases from XML to an array of VAXB/IDL objects
     */
    private void loadTestCases() throws LimClientException
    {
        String request = null;
        SAXBuilder builder = new SAXBuilder();

        InputStream inputStream = null;
        m_testCases = new Vector(100);

        StringTokenizer testFileList = new StringTokenizer(m_testFile, CLIENT_TEST_FILE_VALUE_DELIM);

        String testFile = null;
        String s = null;

        int index = 0;

        testFileNames = new ArrayList();

        while (testFileList.hasMoreTokens())
        {
            testFileNames.add(testFileList.nextToken());
        }
        System.err.println("Test file size" + testFileNames.size());

        try
        {
            for (int i = 0; (i < testFileNames.size()); i++)
            {
                testFile = (String) testFileNames.get(i);
                System.out.println("\nLoading test cases from: <" + testFile + ">");

                try
                {
                    log("Try to load test file using File IO: " + testFile);
                    inputStream = new FileInputStream(new File(testFile));
                }
                catch (FileNotFoundException fnfe)
                {
                    log("Failed to load test file using File IO: " + fnfe.getMessage());
                    log("Try to load test file using PropertiesFileLoader: " + testFile);
                    inputStream = PropertiesFileLoader.getAsStream(testFile, null);
                }

                Document doc = builder.build(inputStream);
                Element root = doc.getRootElement();

                /*
                 * Get all test cases
                 */

                List list = root.getChildren();
                Iterator iterator = list.iterator();

                TestCase testCase;
                int j = 0;
                while (iterator.hasNext())
                {
                    j++;
                    Element envelope = (Element) iterator.next();
                    try
                    {
                        testCase = new TestCase(envelope);
                        m_testCases.add(testCase);
                        //log("Loaded: " + testCase.getDescription());
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                        System.err.println("Test case # " + j + " not loaded!!! " + e.getMessage());
                    }
                }//end of while iterator
            }//end of for
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new LimClientException(e, "Failed to load test cases.");
        }
        finally
        {
            try
            {
                inputStream.close();
            }
            catch (Exception e)
            {
            }

        }//end of finally

    }

    /**
     * method: getTestcase()
     */
    public TestCase getTestCase(int i)
    {
        return (TestCase) m_testCases.get(i);
    }

    /**
     * method: getTestCases()
     */
    public List getTestCases()
    {
        return m_testCases;
    }

    /**
     * Return the number of testcases
     */
    public int size()
    {
        return m_testCases.size();
    }

    /**
     * Reload test cases
     */
    public void reload() throws LimClientException
    {
        m_testCases = null;
        loadTestCases();
    }

    /**
     * log a message
     */
    public static void log(String s)
    {
        System.out.println(s);
    }
}
