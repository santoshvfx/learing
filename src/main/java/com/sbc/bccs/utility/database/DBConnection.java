// $Id: DBConnection.java,v 1.8 2004/07/22 13:58:08 sm5276 Exp $

package com.sbc.bccs.utility.database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import com.sbc.bccs.utilities.Logger;
import com.sbc.eia.bis.encryption.Encryption;
import com.sbc.bccs.utilities.PropertiesFileLoader;
import com.sbc.eia.bis.framework.logging.LogEventId;

/**
 * Class used to create JDBConnection to the database.
 * Support Pool connection and direct JDBC connection.
 * Creation date: (3/2/01 1:53:34 PM)
 * @author: Hongmei Parkin
 *
#   History :
#   Date      	| Author        	| Version	| Notes
#   ----------------------------------------------------------------------------
# 	04/01/2003	Mark Liljequist 	2.0			Update for datasource lookup.
#	07/21/2004	Jon Costa			4.0			RM128789 Add password encryption
*/



public class DBConnection{
	private java.sql.Connection connection = null;
	private com.sbc.bccs.utilities.Logger logger = null;
	private DataSource dsHome = null;

	private String aJdbcDriver = "";
	private String aJdbcOption = "";
	private String aJdbcUrl = "";
	private String passWord = "";
	private String userId = "";

/**
 * Insert the method's description here.
 * Creation date: (4/27/01 12:15:16 PM)
 */
public DBConnection(Logger aLogger) {
	logger = aLogger;
}
/**
 * DBConnection constructor.
 */
public DBConnection( DBConnection a ) {

	this.aJdbcDriver = a.getJdbcDriver();
	this.aJdbcUrl = a.getJdbcUrl();
	this.aJdbcOption = a.getJdbcOption();
	this.dsHome = a.getDsHome();
	this.logger = a.getLogger();
	this.passWord = a.getPassWord();
	this.userId = a.getUserId();
}
/**
 * This method accept properties file as input
 * The tags that should be defined in the properties file are:
 *
 * jdbcDATA_SOURCE_NAME,
 * jdbcUSERID,
 * jdbcPASSWORD,
 * jdbcDRIVER,
 * jdbcUSE_CONNECTION_POOL,
 *
 * Creation date: (4/10/01 2:44:09 PM)
 */
public DBConnection(String propertyFile, Logger aLogger)
	throws SQLException, IOException {

	logger = aLogger;

	Properties p = null;
	String dataSourceNm = "";
	String jdbcUsrId = "";
	String jdbcPassWord = "";
	String jdbcDriver = "";
	String jdbcOption = "";
	String jdbcUrl = "";

	// load from property file
	try {
		p = loadProperties(propertyFile);
	} catch (IOException e) {
		throw e;
	}

	try {
		// Note: If OSS_PUBLIC_KEY == NULL or encryption not implemented,
		// decodePassword() will return the unchanged value of jdbcPASSWORD.
		//
		Encryption enc = new Encryption();

		dataSourceNm = p.getProperty("jdbcDATA_SOURCE_NAME").trim();
		jdbcUsrId = p.getProperty("jdbcUSERID").trim();
		jdbcPassWord = enc.decodePassword( p.getProperty("OSS_PUBLIC_KEY"),
										   p.getProperty("jdbcPASSWORD")).trim();
		jdbcDriver = p.getProperty("jdbcDRIVER").trim();
		jdbcOption = p.getProperty("jdbcUSE_CONNECTION_POOL").trim();
		jdbcUrl = p.getProperty("jdbcURL").trim();
	} catch (Exception e) {
		logger.log(
			LogEventId.DEBUG_LEVEL_1,
			"Tag or tag value improperly defined in file " + propertyFile);
		throw new SQLException(
			"Tag or tag value improperly defined in file " + propertyFile + e.getMessage());
	}

	userId = jdbcUsrId;
	passWord = jdbcPassWord;
	aJdbcDriver = jdbcDriver;
	aJdbcUrl = jdbcUrl;
	aJdbcOption = jdbcOption;

	jdbcOptionHandle(

		dataSourceNm,
		jdbcUsrId,
		jdbcPassWord,
		jdbcUrl,

		jdbcDriver,
		jdbcOption);

}
/**
 * This method accept properties file as input
 * The tags that should be defined in the properties file are:
 *
 * jdbcDATA_SOURCE_NAME,
 * jdbcUSERID,
 * jdbcPASSWORD,
 * jdbcDRIVER,
 * jdbcUSE_CONNECTION_POOL,
 * jdbcINITIAL_CONTEXT_FACTORY,
 * jdbcCONTEXT_PROVIDER_URL
 *
 * Creation date: (4/10/01 2:44:09 PM)
 */
public DBConnection(String propertyFile, Logger aLogger, String DB, String hostName)
	throws SQLException, IOException {

	logger = aLogger;

	Properties p = null;
	String dataSourceNm = "";
	String jdbcUsrId = "";
	String jdbcPassWord = "";
	String jdbcDriver = "";
	String jdbcOption = "";
	String jdbcUrl = "";

	// load from property file
	try {
		p = loadProperties(propertyFile);
	} catch (IOException e) {
		throw e;
	}

	try {
		// Note: If OSS_PUBLIC_KEY == NULL or encryption not implemented,
		// decodePassword() will return the unchanged value of jdbcPASSWORD.
		//
		Encryption enc = new Encryption();

		dataSourceNm = p.getProperty("jdbcDATA_SOURCE_NAME").trim() + DB;
		jdbcUsrId = p.getProperty("jdbcUSERID").trim();
		jdbcPassWord = enc.decodePassword( p.getProperty("OSS_PUBLIC_KEY"),
										   p.getProperty("jdbcPASSWORD")).trim();
		jdbcDriver = p.getProperty("jdbcDRIVER").trim();
		jdbcOption = p.getProperty("jdbcUSE_CONNECTION_POOL").trim();
		jdbcUrl = p.getProperty("jdbcURL").trim() + hostName;
	} catch (Exception e) {
		logger.log(
			LogEventId.DEBUG_LEVEL_1,
			"Tag or tag value improperly defined in file " + propertyFile);
		throw new SQLException(
			"Tag or tag value improperly defined in file " + propertyFile + e.getMessage());
	}

	userId = jdbcUsrId;
	passWord = jdbcPassWord;
	aJdbcDriver = jdbcDriver;
	aJdbcUrl = jdbcUrl;
	aJdbcOption = jdbcOption;

	jdbcOptionHandle(
		dataSourceNm,
		jdbcUsrId,
		jdbcPassWord,
		jdbcUrl,
		jdbcDriver,
		jdbcOption);

}
/**
 * Insert the method's description here.
 * Creation date: (5/30/01 11:23:40 AM)
 * @param jdbcDriver java.lang.String
 * @param jdbcUrl java.lang.String
 * @param jdbcUserId java.lang.String
 * @param jdbcPassWord java.lang.String
 */
public DBConnection(
	String jdbcDriver,
	String jdbcUrl,
	String jdbcUserId,
	String jdbcPassWord)
	throws SQLException {
	try {
		Class.forName(jdbcDriver).newInstance();
	} catch (Exception e) {
		throw new SQLException("No Suitable Driver: " + e.getMessage());
	}

	try {
		connection = DriverManager.getConnection(jdbcUrl, jdbcUserId, jdbcPassWord);
	} catch (Exception e) {
		throw new SQLException("get JDBC connection failed: " + e.getMessage());
	}
}
/**
 * DBConnection constructor.
 * This method take
 *
 * String dataSourceNm,
 * String jdbcUsrId,
 * String jdbcPassWord,
 * String jdbcUrl,
 * String jdbcDriver,
 * String jdbcOption: DATASOURCE, JDBCDRIVER, BOTH, DSONLY_NOCONNECTION
 * Logger aLogger
 *
 * as input to get JDBC pool connection
 * Creation date: (3/8/01 10:34:17 AM)
 */
public DBConnection(
	String dataSourceNm,
	String jdbcUsrId,
	String jdbcPassWord,
	String jdbcUrl,
	String jdbcDriver,
	String jdbcOption,
	Logger aLogger)
	throws SQLException {

	logger = aLogger;

	userId = jdbcUsrId;
	passWord = jdbcPassWord;
	aJdbcDriver = jdbcDriver;
	aJdbcUrl = jdbcUrl;
	aJdbcOption = jdbcOption;

	jdbcOptionHandle(
		dataSourceNm,
		jdbcUsrId,
		jdbcPassWord,
		jdbcUrl,
		jdbcDriver,
		jdbcOption);
}
/**
 * setAutoCommit method.
 * Creation date: (3/7/04 3:03:42 PM)
 */
public void setAutoCommit(boolean autoCommit) throws SQLException
{
	connection.setAutoCommit( autoCommit );
}
/**
 * Commit method.
 * Creation date: (3/7/01 3:03:42 PM)
 */
public void commit() throws SQLException {
	try {
		connection.commit();
	} catch (SQLException e) {
		throw new SQLException("commit failed: " + e.getMessage());
	}

	logger.log(LogEventId.DEBUG_LEVEL_1, "Database commited");

}
/**
 * disconnect method.
 * Creation date: (3/5/01 3:32:09 PM)
 */
public void disconnect() throws SQLException {

		try {
			connection.close();
		} catch (SQLException e) {
			throw new SQLException("disconnect failed: " + e.getMessage());

		}
	connection = null;

}
/**
 * Return the connection to the caller.
 * Creation date: (3/2/01 2:44:24 PM)
 */
public Connection getConnection() throws SQLException {

	// Return the connection created by the DBConnection constructor
	if (connection != null)
		return connection;

	else
		if (dsHome != null) {
			connection = dsHome.getConnection(userId, passWord);
			return connection;
		} else {
			connection = getJdbcConnection(aJdbcDriver, userId, passWord, aJdbcUrl);
			return connection;
		}
}
/**
 * get datasource connection pool
 * Creation date: (4/24/01 12:18:09 PM)
 */
public Connection getDSConnection(
	String aDataSourceName,
	String aJdbcUserid,
	String aJdbcPassword)
	throws SQLException {

	logger.log(LogEventId.DEBUG_LEVEL_2, "aDataSourceName=" + aDataSourceName);
	logger.log(LogEventId.DEBUG_LEVEL_2, "aJdbcUserid=" + aJdbcUserid);


	/**
	 * Attempts to get the next available DataSource pool connection.
	 */
	DataSourceManager dsManager = new DataSourceManager(logger);

	if (dsHome == null) {
		dsHome = dsManager.createDataSource(aDataSourceName);
	}
	connection = dsHome.getConnection(aJdbcUserid, aJdbcPassword);
	return connection;
}
/**
 * Insert the method's description here.
 * Creation date: (4/26/01 4:26:11 PM)
 * @return java.lang.String
 */
public DataSource getDsHome() {
	return dsHome;
}
/**
 * get direct jdbc connection
 * Creation date: (4/24/01 11:57:31 AM)
 */
public Connection getJdbcConnection(
	String jdbcDriver,
	String jdbcUserId,
	String jdbcPassWord,
	String jdbcUrl)
	throws SQLException {

	logger.log(LogEventId.DEBUG_LEVEL_2, "aJdbcUserid=" + jdbcUserId);
	logger.log(LogEventId.DEBUG_LEVEL_2, "aJdbcDriver=" + jdbcDriver);
	logger.log(LogEventId.DEBUG_LEVEL_2, "aJdbcUrl=" + jdbcUrl);

	if (connection == null) {
		logger.log(LogEventId.DEBUG_LEVEL_2, "Get JDBC Driver...");
		try {
			Class.forName(jdbcDriver).newInstance();
		} catch (Exception e) {
			throw new SQLException("No Suitable Driver: " + e.getMessage());
		}

		logger.log(LogEventId.DEBUG_LEVEL_2, "Get JDBC database connection...");
		try {
			connection = DriverManager.getConnection(jdbcUrl, jdbcUserId, jdbcPassWord);
		} catch (Exception e) {
			throw new SQLException("get JDBC connection failed: " + e.getMessage());
		}
	}

	return connection;

}
/**
 * Insert the method's description here.
 * Creation date: (4/26/01 4:30:56 PM)
 * @return java.lang.String
 */
public String getJdbcDriver() {
	return aJdbcDriver;
}
/**
 * Insert the method's description here.
 * Creation date: (4/26/01 4:25:13 PM)
 * @return java.lang.String
 */
public String getJdbcOption() {
	return aJdbcOption;
}
/**
 * Insert the method's description here.
 * Creation date: (4/26/01 4:24:35 PM)
 */
public String getJdbcUrl() {
	return aJdbcUrl;
}
/**
 * Insert the method's description here.
 * Creation date: (4/26/01 4:26:37 PM)
 * @return java.lang.String
 */
public com.sbc.bccs.utilities.Logger getLogger() {
	return logger;
}
/**
 * Insert the method's description here.
 * Creation date: (4/26/01 4:26:57 PM)
 * @return java.lang.String
 */
public String getPassWord() {
	return passWord;
}
/**
 * Insert the method's description here.
 * Creation date: (4/26/01 4:27:18 PM)
 * @return java.lang.String
 */
public String getUserId() {
	return userId;
}
/**
 * Insert the method's description here.
 * Creation date: (4/26/01 5:14:53 PM)
 */
public void jdbcOptionHandle(

	String dataSourceNm,
	String jdbcUsrId,
	String jdbcPassWord,
	String jdbcUrl,

	String jdbcDriver,
	String jdbcOption)
	throws SQLException {

	if (jdbcOption.equalsIgnoreCase("DATASOURCE")) {
		connection =
			getDSConnection(
				dataSourceNm,
				jdbcUsrId,
				jdbcPassWord);
	}

	if (jdbcOption.equalsIgnoreCase("JDBCDRIVER")) {
		connection = getJdbcConnection(jdbcDriver, jdbcUsrId, jdbcPassWord, jdbcUrl);
	}

	if (jdbcOption.equalsIgnoreCase("BOTH")) {
		try {
			connection =
				getDSConnection(
					dataSourceNm,
					jdbcUsrId,
					jdbcPassWord);
		} catch (SQLException e) {
			logger.log(
				LogEventId.ERROR,
				"Failed to get Database Connection Pool: " + e.getMessage());
			logger.log(
				LogEventId.ERROR,
				"Try JDBC direct connection ......: " + e.getMessage());

			connection = getJdbcConnection(jdbcDriver, jdbcUsrId, jdbcPassWord, jdbcUrl);

		}

	}
}
/**
 * Loads JDBC related properties from a specified properties file.
 * Creation date: (4/10/01 3:00:51 PM)
 * @param aFileName java.lang.String
 * @exception java.io.IOException The exception description.
 */
public Properties loadProperties(String aFileName)
throws IOException
{

	if (aFileName == null) {
		return null;
	}

	Properties props    = null;

	//get properties file
	try {
		props = new Properties();
		PropertiesFileLoader.read(props, aFileName, getLogger());

	} catch (Exception e) {
		e.printStackTrace();
		throw new IOException("Failed to load properties file. fileName=" + aFileName);
	}

	return props;
}
/**
 * Rollback method.
 * Creation date: (3/7/01 3:04:13 PM)
 */
public void rollback() throws SQLException{
	try {
		connection.rollback();
	} catch (SQLException e) {
		throw new SQLException("Rollback failed: " + e.getMessage());

	}
	logger.log(LogEventId.DEBUG_LEVEL_1, "Database rolled back");
}
}
