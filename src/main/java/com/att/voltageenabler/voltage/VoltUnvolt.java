package com.att.voltageenabler.voltage;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.StringTokenizer;

//import Utils.Voltage.common.VoltageDataElement;
//import Utils.Voltage.common.VoltageException;

import com.sbc.bccs.utilities.Logger;
import com.sbc.bccs.utilities.PropertiesFileLoader;
import com.sbc.eia.bis.framework.logging.LogEventId;

import com.voltage.securedata.enterprise.FPE;
import com.voltage.securedata.enterprise.LibraryContext;
import com.voltage.securedata.enterprise.VeException;

public class VoltUnvolt {
    private static final String stringTrue = "true";
    private static final String stringFalse = "false";
    private static final String stringInvalid = "invalid";
    private static VoltUnvolt instance;
    private static String authMethod;
    private static String authInfo;
    private static String keyServerURL;
    private static String batchMode;
    private static String caseSensitiveSupport;
    private static String inMemory;
    private static String storage;
    private static String trustStore;
    private static LibraryContext library = null;
    private static HashMap<String, String> identityMap = new HashMap <String, String>();
    private static boolean voltageFlag = false;
    private static HashMap<String, int[]> inputSets = new HashMap<String, int[]>();
    private static HashMap<String, int[]> outputSets = new HashMap<String, int[]>();
    private HashMap<String, int[]> passSets = new HashMap<String, int[]>();


    /**
     * @return the passSets
     */
    public HashMap<String, int[]> getPassSets() {
        return passSets;
    }

    protected VoltUnvolt(Logger aLogger) throws Exception{
        String tmpStr = System.getProperty("VoltageFlag");
        if (tmpStr != null && tmpStr.equalsIgnoreCase("on"))
            voltageFlag = true;
        loadConfigData(aLogger);
        createLibrary(aLogger);
        loadIdentitiesData(aLogger);
    }
    protected void log(String method,String logEventId,Logger aLogger,String message)
    {
        if(aLogger != null)
            aLogger.log(logEventId,method + message);
        else
            System.out.println(method + message);
    }

    public synchronized void loadConfigData(Logger aLogger)
    {
        String method = " com.att.voltageenabler.voltage.VoltUnvolt :: loadConfigData ::";
        if (!voltageFlag) return;

        String fileName = getSourceFileName(aLogger);

        if (fileName == null) {
            voltageFlag = false;
            return;
        }
/*
        File file = new File (fileName);


        if(!file.exists()) {
            voltageFlag = false;
            log(method,LogEventId.EXCEPTION,aLogger,"VoltageServerConnection Properties xml file/dir not found: " + fileName);
            return;
        }

        if (file.isFile())
            loadVoltageServerConnection(file,aLogger);*/
        loadVoltageServerConnection(fileName, aLogger);

    }

    public boolean isVoltageFlagOn(){
        return voltageFlag;
    }

    public synchronized void loadIdentitiesData(Logger aLogger)
    {
        String method = " com.att.voltageenabler.voltage.VoltUnvolt :: loadIdentitiesData ::";
        if (!voltageFlag) return;

        String fileName = getIdentitiesFileName(aLogger);

        if (fileName == null) {
            voltageFlag = false;
            return;
        }

        /*
        File file = new File (fileName);

        if(!file.exists()) {
            voltageFlag = false;
            log(method,LogEventId.EXCEPTION,aLogger,"VoltageIdentities Properties xml file/dir not found: " + fileName);
            return;
        }

        if (file.isFile())
            loadVoltageIdentities(file,aLogger);
         */
        loadVoltageIdentities(fileName,aLogger);

    }

    public String getSourceFileName(Logger aLogger)
    {
        String method = " com.att.voltageenabler.voltage.VoltUnvolt :: getSourceFileName ::";
        // the file name of VoltageServerConnection is in hydra.xml
        String fileName = System.getProperty("VoltageServerConnection");

        if (fileName == null)
            log(method,LogEventId.EXCEPTION,aLogger,"can not find value of VoltageServerConnection");

        return fileName;
    }

    public String getIdentitiesFileName(Logger aLogger)
    {
        String method = " com.att.voltageenabler.voltage.VoltUnvolt :: getIdentitiesFileName ::";
        // the file name of VoltageServerConnection is in hydra.xml
        String fileName = System.getProperty("VoltageIdentities");

        if (fileName == null)
            log(method,LogEventId.EXCEPTION,aLogger,"can not find value of VoltageIdentities");

        return fileName;
    }

    public void loadVoltageServerConnection(String fileName,Logger aLogger)
    {
        String method = " com.att.voltageenabler.voltage.VoltUnvolt :: loadVoltageServerConnection ::";
        if (fileName == null) {
            log(method,LogEventId.EXCEPTION,aLogger,"VoltageServerConnection Properties xml file not found  " + fileName);
        }
        //loadVoltageServerConnection(new File (fileName),aLogger);

        try
        {
            log(method,LogEventId.DEBUG_LEVEL_2,aLogger,"loading VoltageServerConnection Properties data from " + fileName);

            Properties props = new Properties();
            try {
                props = PropertiesFileLoader.read(fileName, aLogger);
            } catch (FileNotFoundException e1) {
                log(method,LogEventId.EXCEPTION,aLogger,e1.getMessage());
            } catch (IOException e2) {
                log(method,LogEventId.EXCEPTION,aLogger,e2.getMessage());
            }

            initialize(props);

            log(method,LogEventId.DEBUG_LEVEL_2,aLogger,"loaded VoltageServerConnection Properties data from " + fileName);
//			logger.debug("props.entrySet" + props.entrySet());

        } catch (Exception e) {
            log(method,LogEventId.EXCEPTION,aLogger,"Unable to load VoltageServerConnection Properties data from " + fileName);
            //throw e;
        }

    }

    public void loadVoltageServerConnection(File file,Logger aLogger)
    {
        String method = " com.att.voltageenabler.voltage.VoltUnvolt :: loadVoltageServerConnection ::";
        try
        {
            log(method,LogEventId.DEBUG_LEVEL_2,aLogger,"loading VoltageServerConnection Properties data from " + file.getName());

            if (!(file.exists())) {
                log(method,LogEventId.EXCEPTION,aLogger,"VoltageServerConnection Properties xml file not found  " + file.getName());
            }

            Properties props = new Properties();
            try {
                props.load(new FileInputStream(file));
            } catch (FileNotFoundException e1) {
                log(method,LogEventId.EXCEPTION,aLogger,e1.getMessage());
            } catch (IOException e2) {
                log(method,LogEventId.EXCEPTION,aLogger,e2.getMessage());
            }

            initialize(props);

            log(method,LogEventId.DEBUG_LEVEL_2,aLogger,"loaded VoltageServerConnection Properties data from " + file.getName());
//			logger.debug("props.entrySet" + props.entrySet());

        } catch (Exception e) {
            log(method,LogEventId.EXCEPTION,aLogger,"Unable to load VoltageServerConnection Properties data from " + file.getName());
            //throw e;
        }
    }

    public void loadVoltageIdentities(String fileName,Logger aLogger) {
        String method = " com.att.voltageenabler.voltage.VoltUnvolt :: loadVoltageIdentities ::";
        try
        {
            log(method,LogEventId.DEBUG_LEVEL_2,aLogger,"loading VoltageIdentities Properties data from " + fileName);

            Properties props = new Properties();
            try {
                props = PropertiesFileLoader.read(fileName,aLogger);
            } catch (FileNotFoundException e1) {
                log(method,LogEventId.EXCEPTION,aLogger,e1.getMessage());
            } catch (IOException e2) {
                log(method,LogEventId.EXCEPTION,aLogger,e2.getMessage());
            }

            loadIdentityTypes(props);

            log(method,LogEventId.DEBUG_LEVEL_2,aLogger,"loaded VoltageIdentities Properties data from " + fileName);
//			logger.debug("props.entrySet" + props.entrySet());

        } catch (Exception e) {
            log(method,LogEventId.EXCEPTION,aLogger,"Unable to load VoltageIdentities Properties data from " + fileName);
            //throw e;
        }
    }

    public void loadVoltageIdentities(File file,Logger aLogger)
    {
        String method = " com.att.voltageenabler.voltage.VoltUnvolt :: loadVoltageIdentities ::";
        try
        {
            log(method,LogEventId.DEBUG_LEVEL_2,aLogger,"loading VoltageIdentities Properties data from " + file.getName());

            if (!(file.exists())) {
                log(method,LogEventId.EXCEPTION,aLogger,"VoltageIdentities Properties xml file not found  " + file.getName());
            }

            Properties props = new Properties();
            try {
                props.load(new FileInputStream(file));
            } catch (FileNotFoundException e1) {
                log(method,LogEventId.EXCEPTION,aLogger,e1.getMessage());
            } catch (IOException e2) {
                log(method,LogEventId.EXCEPTION,aLogger,e2.getMessage());
            }

            loadIdentityTypes(props);

            log(method,LogEventId.DEBUG_LEVEL_2,aLogger,"loaded VoltageIdentities Properties data from " + file.getName());
//			logger.debug("props.entrySet" + props.entrySet());

        } catch (Exception e) {
            log(method,LogEventId.EXCEPTION,aLogger,"Unable to load VoltageIdentities Properties data from " + file.getName());
            //throw e;
        }
    }

    public static synchronized VoltUnvolt getSharedInstance(Logger aLogger) throws Exception{
        String method = " com.att.voltageenabler.voltage.VoltUnvolt :: getSharedInstance ::";
        if(instance==null){
            instance = new VoltUnvolt(aLogger);
            instance.log(method,LogEventId.DEBUG_LEVEL_2,aLogger,"Created new instance of VoltUnvolt");
        }

        instance.log(method,LogEventId.DEBUG_LEVEL_2,aLogger,"Using existing instance of VoltUnvolt");


        return instance;
    }


    private synchronized void loadIdentityTypes(Properties props){
        String method = " com.att.voltageenabler.voltage.VoltUnvolt :: loadIdentityTypes ::";
        String result = "";
        String elements[] = new String[]{"CC_NUM","CC_VARNUM","ALLNUM_SSN","PARTIAL_ALLNUM_SSN","ALPHANUM_SSN",
                "DRIVER_LIC_NUM","BIRTH_DATE","NAT_ID_NUM","STATE_ID_NUM",
                "BIO_DATA","RACE","ALLNUM_PIN","ALPHANUM_PIN","PSWD_HINT","MOBILITY_LOC",
                "GENERIC_STRING","GENERIC_STRING_PCI",
                "BANK_NUM","MED_INDIVL","MED_INDIV","HEALTH_INS","HEALTH_PROV_INDV","HEALTH_PMT_INDV",
                "ALLNUM_SSN_ROTATION","ALPHANUM_SSN_ROTATION","CC_VARNUM_ROTATION","CV_ALLNUM_PIN",
                "CC_NAME","CC_EXP","B64_GENERIC","SO_GENERIC_STRING","SO_GENERIC_SHORT","SO_ALPHANUM_PIN"};



        ArrayList<String> temp = new ArrayList<String>();
        for(int i = 0;i< elements.length; i++){
            if(keyServerURL.toUpperCase().indexOf("PROD") != -1){
                result = props.getProperty(elements[i] + "_PROD");
                result = getElementName(elements[i], result);
                identityMap.put(elements[i], result);
            }else{
                result = props.getProperty(elements[i] + "_QA");
                result = getElementName(elements[i], result);
                identityMap.put(elements[i], result);
            }
        }
    }

    private String getElementName(String format, String data) {
        String result = "";

        StringTokenizer st = new StringTokenizer(data, "|");
        int i=0;

        String input="", output="", pass="";
        while (st.hasMoreElements()) {
            if (i == 0)
                result = st.nextToken();
            else if (i == 1)
                input = st.nextToken();
            else if (i == 2)
                output = st.nextToken();
            else if (i == 3)
                pass = st.nextToken();
            i++;
        }

        createCharSet(format, input, pass, inputSets);
        createCharSet(format, output, pass, outputSets);
        createCharSetofPassThruChars(format,pass,passSets);
        return result;
    }
    private void createCharSetofPassThruChars(String format,String pass, HashMap<String, int[]> dataSets) {

        int sets[] = new int[256];
        int count = 0;

        if (pass != null && !pass.isEmpty()) {
            StringTokenizer pst = new StringTokenizer(pass, ",");
            while (pst.hasMoreElements()) {
                sets[count++] = Integer.parseInt(pst.nextToken());
            }
        }

        if (count <= 0)
            return;
        int result[] = new int[count];
        for (int i=0; i < count; i++)
            result[i] = sets[i];
        dataSets.put(format, result);
    }

    private void createCharSet(String format, String indata, String pass, HashMap<String, int[]> dataSets) {
        if (indata == null || indata.isEmpty())
            return;

        StringTokenizer st = new StringTokenizer(indata, ",");
        int sets[] = new int[256];
        int count = 0;

        while (st.hasMoreElements()) {
            StringTokenizer et = new StringTokenizer(st.nextToken(), "-");
            int start = -1, stop = -1;

            while (et.hasMoreElements()) {
                if (start == -1)
                    start = Integer.parseInt(et.nextToken());
                else
                    stop = Integer.parseInt(et.nextToken());
            }
            sets[count++] = start;
            if (stop > start) {
                for (int j=start+1; j<= stop; j++)
                    sets[count++] = j;
            }
        }

        if (pass != null && !pass.isEmpty()) {
            StringTokenizer pst = new StringTokenizer(pass, ",");
            while (pst.hasMoreElements()) {
                sets[count++] = Integer.parseInt(pst.nextToken());
            }
        }

        if (count <= 0)
            return;
        int result[] = new int[count];
        for (int i=0; i < count; i++)
            result[i] = sets[i];
        dataSets.put(format, result);
    }

    public synchronized void initialize(Properties props){
        /*
         *  Load the java bindings. This requires the directory containing vtksimplejava.dll library to be specified in java.library.path.
         */
        System.loadLibrary("vibesimplejava");

        authMethod = props.getProperty("authMethod");
        /* Use your own mechid and password for authentication. For details on
         * how to acquire and set the password for an ITSERVICES MechID see the
         * "Getting Started" folder on the SecureData SharePoint:
         * https://spfd03.web.att.com/sites/CSO-Crypto/EEDAR/Shared%20Documents/Forms/AllItems.aspx
         *
         */
        authInfo = props.getProperty("authDetails");
        /* There are 2 SecureData environments to consider when establishing the
         * library context. The 2 environments are the Application Test/QA and
         * Application Production. The two possible policy XML files are as follows:
         *
         * Application Test/QA    https://voltage-pp-0000.dpsqa.att.com/policy/clientPolicy.xml
         * Application Production https://voltage-pp-0000.dpsprod.att.com/policy/clientPolicy.xml
         *
         */
        keyServerURL = props.getProperty("keyServerURL");
        batchMode = props.getProperty("batchMode");
        caseSensitiveSupport = props.getProperty("caseSensitiveSupport");
        inMemory = props.getProperty("inMemory");
        storage = props.getProperty("storage");

        trustStore = props.getProperty("trustStore");
    }

    protected void finalize() throws Throwable{
        try{
            library.delete();
            instance = null;
        }finally{
            super.finalize();
        }
    }
    private synchronized void  createLibrary(Logger aLogger) throws Exception{
        String method = " com.att.voltageenabler.voltage.VoltUnvolt :: createLibrary :: ";
        if (!voltageFlag) return;

        /* The first thing you need to do is build a library context.
         * This function creates a basic, default library context
         * loaded with all the supporting contexts.
         * NOTE: This will attempt to find a policy.xml file at the specified URL
         */
        //storage is not used by Windows, but it is needed in Unix environments
        //trustStore is not used by Windows, but it is needed in Unix environments
        try
        {
            /*
            if(isInMemoryFlagOn()){
                library = new LibraryContext(LibraryContext.LIB_CTX_MEMORY_STORAGE, 0, keyServerURL,"", "",trustStore);
            }else{
                library = new LibraryContext(0, 0, keyServerURL,storage, "", trustStore);
                log(method,LogEventId.DEBUG_LEVEL_2,aLogger,"storage directory being used :  " + storage);
            }
            log(method,LogEventId.DEBUG_LEVEL_2,aLogger,"trustStore directory being used :  " + trustStore);

            library.GetToolkitVersionNumber((int)LibraryContext.LIBRARY_VERSION_VIBECRYPTO);
            log(method,LogEventId.DEBUG_LEVEL_2,aLogger,"ToolkitVersionNumber = " + library.GetToolkitVersionNumber((int)LibraryContext.LIBRARY_VERSION_VIBECRYPTO));
            library = new LibraryContext(0, 0, keyServerURL, storage, "", trustStore);
             library.GetToolkitVersionString((int)LibraryContext.LIBRARY_VERSION_VIBECRYPTO);

             */
            library = new LibraryContext.Builder()
                    .setPolicyURL(keyServerURL)
                    .setFileCachePath(storage)
                    .setTrustStorePath(trustStore)
                    .build();
        }
        catch (VeException e)
        {
            log(method,LogEventId.EXCEPTION,aLogger,"Unable to create Voltage Library Object,  errorCode : " + e.getErrorCode() + " VeException errorMessage :" + e.getMessage());
            throw new VoltageException(e.getErrorCode(),e.getMessage());
        }
        catch (Exception e2){
            log(method,LogEventId.EXCEPTION,aLogger,"Unable to create Voltage Library Object, General Exception Message : " + e2.getMessage());
            throw new VoltageException(0,e2.getMessage());
        }
    }

    //this method Volts with input parameter as single element
    public String volt(String element, String type, String formatName,Logger aLogger) throws VoltageException{
        String method = " com.att.voltageenabler.voltage.VoltUnvolt :: volt :: ";
        String response = "";
        FPE fpeEncObj = null;

        String identityType = identityMap.get(formatName.toUpperCase());
        log(method,LogEventId.DEBUG_LEVEL_2,aLogger,"element received to volt " + element + " identityType " + identityType + " format name  " + formatName);

        if(formatName.equalsIgnoreCase("DRIVER_LIC_NUM")){
            if(isCaseSensitiveSupportOn()){
                element = element.toUpperCase();
                log(method,LogEventId.DEBUG_LEVEL_2,aLogger,"caseSensitiveSupport flag is true, element received to volt " + element);
            }else{
                log(method,LogEventId.DEBUG_LEVEL_2,aLogger,"caseSensitiveSupport flag not found or is false, element received to volt " + element);
            }
        }

        if(formatName.equalsIgnoreCase("B64_GENERIC")){
            log(method,LogEventId.DEBUG_LEVEL_2,aLogger,"Base64 element recieved to volt = " + element);
            //identityType is passed on to voltBase64 method, because the "B64_GENERIC" is
            //required to derive the identity type from properties file and voltBase64 method is
            //expecting parameter "formatName=B64_GENERIC"
            response = voltBase64(element,identityType,"formatName=" + formatName.toUpperCase(),aLogger);
            return response;
        }

        try
        {
//            fpeEncObj = new FPE(library, 0, identityType,
//                    LibraryContext.getFPE_FORMAT_CUSTOM(),
//                    "formatName=" + formatName.toUpperCase(),
//                    authMethod,
//                    authInfo,
//                    0);

            String[] mechidPasswd = authInfo.split(":");
            String mechId = mechidPasswd[0];
            String passwd = mechidPasswd[1];
            fpeEncObj = library.getFPEBuilder(formatName)
                    .setUsernamePassword(mechId,passwd)
                    .setIdentity(identityType)
                    .build();
            response = fpeEncObj.protect(element);

            fpeEncObj.delete();
            log(method,LogEventId.DEBUG_LEVEL_2,aLogger,"element received to volt " + element + " encrypted element " + response);
        }
        catch (VeException e)
        {
            log(method,LogEventId.EXCEPTION,aLogger,"Voltage VeException errorCode : " + e.getErrorCode() + " errorMessage :" + e.getMessage());
            throw new VoltageException(e.getErrorCode(),e.getMessage());
        }
        catch (Exception e2){
            log(method,LogEventId.EXCEPTION,aLogger,"General Exception Message : " + e2.getMessage());
            throw new VoltageException(0,e2.getMessage());
        }

        //	return response;
        return response;
    }

    //this method unVolts with input parameter as single element
    public String unVolt(String key, String type, String formatName,Logger aLogger) throws VoltageException{
        String method = " com.att.voltageenabler.voltage.VoltUnvolt :: unVolt :: ";
        String response = "";
        FPE fpeDecObj = null;

        String identityType = identityMap.get(formatName.toUpperCase());
        log(method,LogEventId.DEBUG_LEVEL_2,aLogger,"element received to unvolt " + key + " format name  " + formatName);

        if(formatName.equalsIgnoreCase("B64_GENERIC")){
            log(method,LogEventId.DEBUG_LEVEL_2,aLogger,"Base64 element recieved to unvolt = " + key);
            //identityType is passed on to voltBase64 method, because the "B64_GENERIC" is
            //required to derive the identity type from properties file and voltBase64 method is
            //expecting parameter "formatName=B64_GENERIC"
            response = unVoltBase64(key,identityType,"formatName=" + formatName.toUpperCase(),aLogger);
            return response;
        }


        try
        {
//            fpeDecObj = new FPE(library, 0, identityType,
//                    LibraryContext.getFPE_FORMAT_CUSTOM(),
//                    "formatName=" + formatName.toUpperCase(),
//                    authMethod,
//                    authInfo,
//                    (int) LibraryContext.MODE_ACCESS);

            String[] mechidPasswd = authInfo.split(":");
            String mechId = mechidPasswd[0];
            String passwd = mechidPasswd[1];
            fpeDecObj = library.getFPEBuilder(formatName)
                    .setUsernamePassword(mechId,passwd)
                    .setIdentity(identityType)
                    .build();


            response = fpeDecObj.access(key);
            fpeDecObj.delete();
            log(method,LogEventId.DEBUG_LEVEL_2,aLogger,"element received to unvolt " + key + " decrypted element " + response);
        }
        catch (VeException e)
        {
            log(method,LogEventId.EXCEPTION,aLogger,"Voltage VeException errorCode : " + e.getErrorCode() + " errorMessage :" + e.getMessage());
            throw new VoltageException(e.getErrorCode(),e.getMessage());
        }
        catch (Exception e2){
            log(method,LogEventId.EXCEPTION,aLogger,"General Exception Message : " + e2.getMessage());
            throw new VoltageException(0,e2.getMessage());
        }
        return response;
    }


    //this method Volts with input parameter as single element
    public String voltBase64(String element, String identityType, String formatName,Logger aLogger) throws VoltageException{
        String method = " com.att.voltageenabler.voltage.VoltUnvolt :: voltBase64 :: ";
        String response = "";
        FPE fpeEncObj = null;


        try
        {
//            fpeEncObj = new FPE(library, 0,
//                    identityType,
//                    LibraryContext.getFPE_FORMAT_CUSTOM(),
//                    formatName,
//                    //"formatName=B64_GENERIC",
//                    authMethod,
//                    authInfo,
//                    (int) LibraryContext.MODE_PROTECT);

            String[] mechidPasswd = authInfo.split(":");
            String mechId = mechidPasswd[0];
            String passwd = mechidPasswd[1];
            fpeEncObj = library.getFPEBuilder(formatName)
                    .setUsernamePassword(mechId,passwd)
                    .setIdentity(identityType)
                    .build();

//            byte [] b_str;
//            byte[] b64_b_str;
//            b_str = element.getBytes();
//            b64_b_str = library.base64Encode(LibraryContext.BASE64_NO_NEW_LINE, b_str);
//            String b64_str = new String(b64_b_str);
            byte [] b_str;
            //byte[] b64_b_str;
            String b64_b_str;
            b_str = element.getBytes();
            //b64_b_str = library.Base64Encode(LibraryContext.BASE64_NO_NEW_LINE, b_str);
            b64_b_str =  library.base64Encode(b_str, false);
            String b64_str = new String(b64_b_str);

            // Pad with :
            String pb64_str=String.format("%-15s", b64_str).replace(' ', ':');
            // Encrypt
            response = fpeEncObj.protect(pb64_str);

            log(method,LogEventId.DEBUG_LEVEL_2,aLogger,"Original String \"" +  element + "\"   Encoded/Padded: \"" + pb64_str + "\"   Ciphered: \"" + response + "\"");

            fpeEncObj.delete();
            log(method,LogEventId.DEBUG_LEVEL_2,aLogger,"element received to volt " + element + " encrypted element " + response);
        }
        catch (VeException e)
        {
            log(method,LogEventId.EXCEPTION,aLogger,"Voltage VeException errorCode : " + e.getErrorCode() + " errorMessage :" + e.getMessage());
            throw new VoltageException(e.getErrorCode(),e.getMessage());
        }
        catch (Exception e2){
            log(method,LogEventId.EXCEPTION,aLogger,"General Exception Message : " + e2.getMessage());
            throw new VoltageException(0,e2.getMessage());
        }

        return response;
    }

    //this method unVolts with input parameter as single element
    public String unVoltBase64(String key, String identityType, String formatName,Logger aLogger) throws VoltageException{
        String method = " com.att.voltageenabler.voltage.VoltUnvolt :: unVoltBase64 :: ";
        String response = "";
        FPE fpeDecObj = null;

        try
        {

//            fpeDecObj = new FPE(library, 0,
//                    identityType,
//                    LibraryContext.getFPE_FORMAT_CUSTOM(),
//                    formatName,
//                    authMethod,
//                    authInfo,
//                    (int) LibraryContext.MODE_ACCESS);
            String[] mechidPasswd = authInfo.split(":");
            String mechId = mechidPasswd[0];
            String passwd = mechidPasswd[1];
            fpeDecObj = library.getFPEBuilder(formatName)
                    .setUsernamePassword(mechId,passwd)
                    .setIdentity(identityType)
                    .build();

            String dec_pb64 = fpeDecObj.access(key).replace(':',' ').trim();
            // Decode
            byte [] b_strd;
            //byte[] b64_b_strd;
            //b64_b_strd = dec_pb64.getBytes();
            //b_strd = library.base64Decode(0, b64_b_strd);
            b_strd = library.base64Decode(dec_pb64, true);
            response = new String(b_strd);

            log(method,LogEventId.DEBUG_LEVEL_2,aLogger,"Deciphered w/o padding: \"" + dec_pb64 + "\"   Decoded: \"" + response + "\"");
            fpeDecObj.delete();
            log(method,LogEventId.DEBUG_LEVEL_2,aLogger,"element received to unvolt " + key + " decrypted element " + response);
        }
        catch (VeException e)
        {
            log(method,LogEventId.DEBUG_LEVEL_2,aLogger,"Voltage VeException errorCode : " + e.getErrorCode() + " errorMessage :" + e.getMessage());
            throw new VoltageException(e.getErrorCode(),e.getMessage());
        }
        catch (Exception e2){
            log(method,LogEventId.DEBUG_LEVEL_2,aLogger,"General Exception Message : " + e2.getMessage());
            throw new VoltageException(0,e2.getMessage());
        }
        return response;
    }

    public boolean isProcessBatchMode() {
        return (batchMode == null || !batchMode.equalsIgnoreCase("true")) ? false:true;
    }

    public boolean isCaseSensitiveSupportOn() {
        return (caseSensitiveSupport == null || !caseSensitiveSupport.equalsIgnoreCase("true")) ? false:true;
    }


    public boolean isInMemoryFlagOn() {
        return (inMemory == null || !inMemory.equalsIgnoreCase("true")) ? false:true;
    }
    /*
    //this method Volts with input parameter as multiple elements with input as
    //VoltageDataElement ArrayList which comprises of sequence, inputdata, type, formatName, outputdata
    public ArrayList<VoltageDataElement> volt(ArrayList<VoltageDataElement> vde,Logger aLogger) throws VoltageException{
        String method = " com.att.voltageenabler.voltage.VoltUnvolt :: volt :: ";
        ArrayList<VoltageDataElement> outDataElements = vde;

        String[] voltInData = new String[vde.size()];
        String formatName = vde.get(0).getFormatName();
        String identityType = identityMap.get(formatName);

        for (int i=0; i < vde.size(); i ++) {
            voltInData[i] = vde.get(i).getInData();
        }
        try{
            String[] voltOutData = library.FPEProtectList(identityType,
                    LibraryContext.getFPE_FORMAT_CUSTOM(),
                    "formatName=" + formatName,
                    authMethod, authInfo, voltInData, voltInData.length);
            for (int i=0; i < outDataElements.size(); i ++) {
                outDataElements.get(i).setOutData(voltOutData[i]);
            }

        }
        catch (VeException e)
        {
            log(method,LogEventId.EXCEPTION,aLogger,"Voltage VeException errorCode : " + e.getErrorCode() + " errorMessage :" + e.getMessage());
            throw new VoltageException(e.getErrorCode(),e.getMessage());
        }
        catch (Exception e2){
            log(method,LogEventId.EXCEPTION,aLogger,"General Exception Message : " + e2.getMessage());
            throw new VoltageException(0,e2.getMessage());
        }
        return outDataElements;
    }

    //this method unVolts with input parameter as multiple elements with input as
    //VoltageDataElement ArrayList which comprises of sequence, inputdata, type, formatName, outputdata
    public ArrayList<VoltageDataElement> unVolt(ArrayList<VoltageDataElement> vde,Logger aLogger) throws VoltageException{
        String method = " com.att.voltageenabler.voltage.VoltUnvolt :: unVolt :: ";
        ArrayList<VoltageDataElement> outDataElements = vde;
        String[] encryptedKeys = new String[vde.size()];
        String formatName = vde.get(0).getFormatName();
        String identityType = identityMap.get(formatName);

        for (int i=0; i < vde.size(); i ++) {
            encryptedKeys[i] = vde.get(i).getInData();
        }

        try{
            String[] voltOutData = library.FPEAccessList(identityType,
                    LibraryContext.getFPE_FORMAT_CUSTOM(),
                    "formatName=" + formatName,
                    authMethod, authInfo, encryptedKeys, encryptedKeys.length);
            for (int i=0; i < outDataElements.size(); i ++) {
                outDataElements.get(i).setOutData(voltOutData[i]);
            }
        }
        catch (VeException e)
        {
            log(method,LogEventId.EXCEPTION,aLogger,"Voltage VeException errorCode : " + e.getErrorCode() + " errorMessage :" + e.getMessage());
            throw new VoltageException(e.getErrorCode(),e.getMessage());
        }
        catch (Exception e2){
            log(method,LogEventId.EXCEPTION,aLogger,"General Exception Message : " + e2.getMessage());
            throw new VoltageException(0,e2.getMessage());
        }
        return outDataElements;
    }*/

//	private Logger getLogger(String loggerName) {
//		return Logger.getLogger("interfaces." + loggerName);
//
//	}

    public String volt(String element, String formatName,Logger aLogger) throws Exception{
        String method = " com.att.voltageenabler.voltage.VoltUnvolt :: volt :: ";
        if (formatName.equalsIgnoreCase("B64_GENERIC")){
            return "";
        }

        String rc = isElementVolted(formatName, element);
        if(rc.equalsIgnoreCase(stringTrue)){
            return element;
        } else if (rc.equalsIgnoreCase(stringInvalid)) {
            return "";
        }
        String response = "";

        try {
            response = volt(element,null,formatName,aLogger);

        }catch (VoltageException e){
            log(method,LogEventId.EXCEPTION,aLogger,"Voltage VeException errorCode : " + e.getErrorCode() + " errorMessage :" + e.getMessage());
            throw new Exception(e.getErrorDescription());
        }
        return response;
    }

    public String unVolt(String element, String formatName,Logger aLogger) throws Exception{
        String method = " com.att.voltageenabler.voltage.VoltUnvolt :: unVolt :: ";
        if (formatName.equalsIgnoreCase("B64_GENERIC")){
            return "";
        }

        String rc = isElementVolted(formatName, element);
        if(rc.equalsIgnoreCase(stringFalse)){
            return element;
        }else if (rc.equalsIgnoreCase(stringInvalid)) {
            return "";
        }

        String response = "";

        try {
            response = unVolt(element, null, formatName,aLogger);

        }catch (VoltageException e){
            log(method,LogEventId.EXCEPTION,aLogger,"Voltage VeException errorCode : " + e.getErrorCode() + " errorMessage :" + e.getMessage());
            throw new Exception(e.getErrorDescription());
        }
        return response;
    }

    public boolean isElementVolted(String element){
        boolean flag = false;
        String str = "";
        str = element.substring(element.length()-1);
        Character ch = str.charAt(0);
        if (!Character.isDigit(ch)){
            flag = true;
        }
        return flag;
    }

    public String isElementVolted(String format, Object element){
        String result = stringInvalid;

        if(element instanceof String)
        {
            String inInput = isElementInSets(format, (String)element, inputSets);
            String inOutput = isElementInSets(format, (String)element, outputSets);



            if (inInput.equalsIgnoreCase(stringTrue) && inOutput.equalsIgnoreCase(stringTrue))
                result = stringFalse;
            else if (inInput.equalsIgnoreCase(stringFalse) && inOutput.equalsIgnoreCase(stringTrue))
                result = stringTrue;
        }else if(element instanceof ArrayList)
        {
            ArrayList encryptedValues = (ArrayList) element;
            result = stringTrue;
            for(int i = 0 ; i < encryptedValues.size() ;i++)
            {
                String currentElement = (String) encryptedValues.get(i);
                String currentResult = isElementVolted(format,currentElement);
                if(stringFalse.equalsIgnoreCase(currentResult))
                {
                    result = stringFalse;
                }
            }
        }
        return result;
    }

    public String isElementInSets(String format, String element, HashMap<String, int[]> charSets){
        String flag = stringInvalid;

        if (!charSets.containsKey(format) || element == null || element.isEmpty()) {
            return flag;
        }
        int sets[] = charSets.get(format);

        flag = stringTrue;
        int i,j;

        for (i=0; i<element.length(); i++) {
            flag = stringFalse;
            for (j=0; j<sets.length; j++) {
                if ((int)element.charAt(i)== sets[j]) {
                    flag = stringTrue;
                    break;
                }
            }
            if (flag.compareToIgnoreCase(stringFalse) == 0)
                break;
        }

        return flag;
    }
}