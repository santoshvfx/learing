//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.sbc.bccs.utilities;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

public class PropertiesFileLoader {
    private PropertiesFileLoader() {
    }

    static Class propertiesFileLoaderCached = null;

    private static void log(Logger logger, String eventId, String message) {
        if (logger != null) {
            logger.log(eventId, message);
        } else {
            System.out.println("PropertiesFileLoader (no logger): " + eventId + ":" + message);
        }

    }

    public static InputStream getAsStream(String name, Logger logger) throws FileNotFoundException {
        try {
            return getAsURL(name, logger).openStream();
        } catch (Exception var5) {
            log(logger, "DEBUG_LEVEL_2", "File not found on relative path.  Will attempt absolute path");

            try {
                return getAsStreamFromAbsolutePath(name, logger);
            } catch (FileNotFoundException var4) {
                throw var4;
            }
        }
    }

    public static URL getAsURL(String name, Logger logger) throws FileNotFoundException {
        if (name != null && (name = name.trim()).length() >= 1) {
            String platform = System.getProperty("bis.platform", "").trim();
            if (platform.length() < 1) {
                throw new FileNotFoundException("System property bis.platform not set/invalid");
            } else {
                String resourceName = name + "." + platform.toLowerCase();
                log(logger, "DEBUG_LEVEL_2", "Trying to find properties resource '" + resourceName + "'.");
                Class var10000 = propertiesFileLoaderCached;
                if (var10000 == null) {
                    try {
                        var10000 = Class.forName("com.sbc.bccs.utilities.PropertiesFileLoader");
                    } catch (ClassNotFoundException var7) {
                        throw new NoClassDefFoundError(var7.getMessage());
                    }

                    propertiesFileLoaderCached = var10000;
                }

                //URL url = var10000.getResource(resourceName);
                URL url = PropertiesFileLoader.class.getClassLoader().getResource(resourceName);

                if (url == null) {
                    log(logger, "DEBUG_LEVEL_2", "Properties resource '" + resourceName + "' not found.");
                    resourceName = name + "." + platform.toUpperCase();
                    log(logger, "DEBUG_LEVEL_2", "Trying to find properties resource '" + resourceName + "'.");
                    var10000 = propertiesFileLoaderCached;
                    if (var10000 == null) {
                        try {
                            var10000 = Class.forName("com.sbc.bccs.utilities.PropertiesFileLoader");
                        } catch (ClassNotFoundException var6) {
                            throw new NoClassDefFoundError(var6.getMessage());
                        }

                        propertiesFileLoaderCached = var10000;
                    }

                    //url = var10000.getResource(resourceName);
                    url = PropertiesFileLoader.class.getClassLoader().getResource(resourceName);

                    if (url == null) {
                        log(logger, "DEBUG_LEVEL_2", "Properties resource '" + resourceName + "' not found.");
                        resourceName = name;
                        log(logger, "DEBUG_LEVEL_2", "Trying to find properties resource '" + resourceName + "'.");
                        var10000 = propertiesFileLoaderCached;
                        if (var10000 == null) {
                            try {
                                var10000 = Class.forName("com.sbc.bccs.utilities.PropertiesFileLoader");
                            } catch (ClassNotFoundException var5) {
                                throw new NoClassDefFoundError(var5.getMessage());
                            }

                            propertiesFileLoaderCached = var10000;
                        }

                        //url = var10000.getResource(resourceName);
                        url = PropertiesFileLoader.class.getClassLoader().getResource(resourceName);

                        if (url == null) {
                            throw new FileNotFoundException("propertiesFileName <" + name + "> not found ");
                        }
                    }
                }

                log(logger, "INFO_LEVEL_1", "Using properties resource '" + resourceName + "'");
                return url;
            }
        } else {
            throw new FileNotFoundException("propertiesFileName not defined ");
        }
    }

    public static void read(Properties props, String propertiesFileName, Logger logger) throws FileNotFoundException, IOException {
        InputStream inputStream = getAsStream(propertiesFileName, logger);

        try {
            int oldCount = 0;
            props.load(inputStream);
            log(logger, "DEBUG_LEVEL_2", "Loaded " + (props.size() - oldCount) + " records from " + propertiesFileName);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException var10) {
                    log(logger, "EXCEPTION", "IOException on inputStream.close(): " + var10.getMessage());
                }
            }

        }

    }

    public static Properties read(String propertiesFileName, Logger logger) throws FileNotFoundException, IOException {
        Properties props = new Properties();
        read(props, propertiesFileName, logger);
        return props;
    }

    public static InputStream getAsStreamFromAbsolutePath(String absolutePropertiesFilePath, Logger logger) throws FileNotFoundException {
        FileInputStream fis = null;
        if (absolutePropertiesFilePath != null && (absolutePropertiesFilePath = absolutePropertiesFilePath.trim()).length() >= 1) {
            String platform = System.getProperty("bis.platform", "").trim();
            if (platform.length() < 1) {
                throw new FileNotFoundException("System property bis.platform not set/invalid");
            } else {
                String resourceName = absolutePropertiesFilePath + "." + platform.toLowerCase();
                fis = fetchFileStream(resourceName, logger);
                if (fis == null) {
                    resourceName = absolutePropertiesFilePath + "." + platform.toUpperCase();
                    fis = fetchFileStream(resourceName, logger);
                    if (fis == null) {
                        resourceName = absolutePropertiesFilePath;
                        fis = fetchFileStream(absolutePropertiesFilePath, logger);
                        if (fis == null) {
                            throw new FileNotFoundException("propertiesFileName <" + absolutePropertiesFilePath + "> not found ");
                        }
                    }
                }

                log(logger, "INFO_LEVEL_1", "Using properties resource '" + resourceName + "'");
                return new BufferedInputStream(fis);
            }
        } else {
            throw new FileNotFoundException("absolutePropertiesFilePath not defined ");
        }
    }

    public static FileInputStream fetchFileStream(String absolutePropertiesFilePath, Logger logger) {
        File file = new File(absolutePropertiesFilePath);
        FileInputStream fis = null;

        try {
            log(logger, "DEBUG_LEVEL_2", "Trying to find properties resource '" + absolutePropertiesFilePath + "'.");
            fis = new FileInputStream(file);
        } catch (FileNotFoundException var5) {
            log(logger, "DEBUG_LEVEL_2", "Properties resource '" + absolutePropertiesFilePath + "' not found.");
        }

        return fis;
    }
}