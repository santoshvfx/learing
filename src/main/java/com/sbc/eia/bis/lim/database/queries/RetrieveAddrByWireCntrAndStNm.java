//$Id: RetrieveAddrByWireCntrAndStNm.java,v 1.16 2008/02/29 23:27:20 jd3462 Exp $

package com.sbc.eia.bis.lim.database.queries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import javax.sql.DataSource;

import com.ibm.websphere.ce.cm.StaleConnectionException;
import com.sbc.bccs.idl.helpers.IDLUtil;
import com.sbc.bccs.utilities.Logger;
import com.sbc.bccs.utility.database.DBConnection;
import com.sbc.eia.bis.BusinessInterface.brms.BRMSTag;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.idl.lim.helpers.AddressHandler;
import com.sbc.eia.idl.lim.helpers.AddressHandlerBRMS;
import com.sbc.eia.idl.lim.helpers.AddressHandlerException;
import com.sbc.eia.idl.lim_types.Address;
import com.sbc.eia.idl.lim_types.FieldedAddress;

/**
 * Contains methods to retrieve addresses data by Wire Center and Street Name,
 * from the lfacs_sag_lu_status table.
 * Creation date: (01/16/02 8:58:41 AM)
 * @author: David Brawley
 */
public class RetrieveAddrByWireCntrAndStNm 
{

    /**
     * Contains methods to retrieve address array by WireCenter and SteetName from an lfacs_sag_lu_status table.
     * Creation date: 01/16/02 4:02:35 PM)
     * @return Address[]
     * @param wireCenter String
     * @param streetName String
     * @param houseNumber String
     * @param city String
     * @param unit Unit[]
     * @param maxAddr int
     * @param maxUnits int
     * @param aLogger Logger
     * @exception SQLException If the query command fails, it will throw a SQLException.
     */
    public static Address [] getAddrByWireCntrAndStNm (String aWireCenter, String aStreetName, 
    	String aHouseNumber, String aCity, String aStructValue, String aLevelValue, String aUnitValue, 
        int maxAddr, int maxUnits, Logger aLogger, DBConnection dbConn, Connection tblConnection) 
    throws SQLException 
    {
    	Address[] addressArray = null; 
    	ArrayList addrList = new ArrayList();
    	ArrayList unitList = new ArrayList();
    	PreparedStatement preparedSql = null;
    	ResultSet rsltSet   = null;
        String reqHouseNumber = "";
    	String reqCity =  "";
    	String structType = "";
        String structValue = "";
    	String levelType = "";
        String levelValue = "";
    	String unitType = "";
        String unitValue = "";
        int unitSize = 0;
        	
    	AddressHandlerBRMS brmsAddr = new AddressHandlerBRMS();
    	AddressHandlerBRMS prevAddr = null;
    	
    	StringBuffer whereClause = new StringBuffer("WHERE LFACS_WIRECENTER=? AND STREET_NAME=?");
    
    	if ((aHouseNumber != null) &&
    		((aHouseNumber.trim()).length() > 0)){
    		reqHouseNumber = aHouseNumber.trim();
    		whereClause.append(" AND ((STREET_NUMBER_SIZE = '" + aHouseNumber.trim().length() + "'");
    		whereClause.append(" AND STREET_NUMBER >= '" + aHouseNumber.toUpperCase().trim() + "')");
    		whereClause.append(" OR (STREET_NUMBER_SIZE > '" + aHouseNumber.trim().length() + "'))");
    	}
    
    	if (aCity != null){
    		reqCity = sqlMarkUp(aCity).trim();
    		whereClause.append(" AND TOWN" + ((reqCity.length() > 0) ? "='" + reqCity.toUpperCase() + "'" : " IS NULL"));
    	}
    
    	String orderClause = " ORDER BY NVL(TOWN,' '), NVL(STREET_NUMBER_SIZE,' '), NVL(STREET_NUMBER,' '), NVL(LU_BLDG,' '), NVL(LU_FLOOR,' '), NVL(LU_UNIT,' ')";	
    		
    	String sqlSelect =
    	"SELECT LFACS_WIRECENTER, STREET_NAME, STREET_NUMBER, " + 
    	"STRUCTURE_TYPE, LU_BLDG, ELEVATION_TYPE, LU_FLOOR, UNIT_TYPE, LU_UNIT, TOWN " +
    	"FROM LFACS_SAG_LU_STATUS ";
    	
    	
    	String SQLstatement = sqlSelect + whereClause + orderClause;
    	
    	//log variable
    	int index = whereClause.toString().indexOf((int)'?');
    	int index2 = whereClause.toString().indexOf((int)'?',index+1);
    	StringBuffer logableStmt = whereClause;				
    	logableStmt.deleteCharAt(index); logableStmt.insert(index,aWireCenter.toUpperCase()+' ');
    	logableStmt.deleteCharAt(index2+aWireCenter.length()); logableStmt.insert(index2+aWireCenter.length(),aStreetName.toUpperCase());
    
    	aLogger.log( LogEventId.DEBUG_LEVEL_2, "SQL cmd=<"+sqlSelect+logableStmt+orderClause+">" );
    		
    	try {
    		// access the Oracle lfacs_sag_lu table.
    		//
    		aLogger.log (LogEventId.DEBUG_LEVEL_2, "Looking for " + logableStmt);
    		preparedSql = tblConnection.prepareStatement (SQLstatement);
    		preparedSql.setString(1,aWireCenter.toUpperCase().trim()); // lfacs_wirecenter var
    		preparedSql.setString(2,aStreetName.toUpperCase().trim()); // street_name var
    		
            aLogger.log(LogEventId.REMOTE_CALL, dbConn.getJdbcUrl(), dbConn.getJdbcUrl(),
                              dbConn.getUserId(), "SELECT FROM LFACS_SAG_LU_STATUS WHERE " + logableStmt);
    		rsltSet = preparedSql.executeQuery();
            aLogger.log(LogEventId.REMOTE_RETURN, dbConn.getJdbcUrl(), dbConn.getJdbcUrl(),
                              dbConn.getUserId(), "SELECT FROM LFACS_SAG_LU_STATUS WHERE " + logableStmt);
    
    //		aLogger.log( LogEventId.DEBUG_LEVEL_2, "Fetch Size = " + rsltSet.getFetchSize());
    		aLogger.log( LogEventId.DEBUG_LEVEL_2, "Found the following addresses:" );
    		String stNbr		= "";
    		String stNm			= "";
    		String town			= "";
            
    		while (rsltSet.next())
    		{
    			stNbr = (rsltSet.getString("STREET_NUMBER") == null) ? "" : rsltSet.getString("STREET_NUMBER");
                stNm  = rsltSet.getString("STREET_NAME");
    			town  = (rsltSet.getString("TOWN") == null) ? "": rsltSet.getString("TOWN");
                structType = (rsltSet.getString("STRUCTURE_TYPE") == null) ? "": rsltSet.getString("STRUCTURE_TYPE");
                structValue = (rsltSet.getString("LU_BLDG") == null) ? "": rsltSet.getString("LU_BLDG");
                levelType = (rsltSet.getString("ELEVATION_TYPE") == null) ? "": rsltSet.getString("ELEVATION_TYPE");
                levelValue = (rsltSet.getString("LU_FLOOR") == null) ? "": rsltSet.getString("LU_FLOOR");
                unitType = (rsltSet.getString("UNIT_TYPE") == null) ? "": rsltSet.getString("UNIT_TYPE");
                unitValue = (rsltSet.getString("LU_UNIT") == null) ? "": rsltSet.getString("LU_UNIT");
    
    			aLogger.log (LogEventId.DEBUG_LEVEL_2, rsltSet.getString("LFACS_WIRECENTER") + " " +
    				stNbr + " " + stNm + " " + town + " " +
    				structType + " " + structValue  + " " +
    				levelType  + " " + levelValue   + " " +
    				unitType   + " " + unitValue);
    
    		  	brmsAddr.setBRMSFields(stNbr, stNm, town, BRMSTag.CT, "", 
                      structType, structValue, levelType, levelValue, unitType, unitValue);
    		  	
    		  	if (prevAddr == null){
    		  		prevAddr = new AddressHandlerBRMS();
    			  	prevAddr.setBRMSFields(stNbr, stNm, town, BRMSTag.CT, "", 
                      structType, structValue, levelType, levelValue, unitType, unitValue);
    		  	}
    	  
    			// addressHandler compareAddr method 
                // does not use unit values in comparison
                if (brmsAddr.compareAddr((AddressHandler)prevAddr))  
                {
    				if (reqHouseNumber.equalsIgnoreCase(stNbr))
                    {
    					if (structValue.equals("") &&
    						levelValue.equals("") &&
    						unitValue.equals("") &&
    						aStructValue.equals("") &&
    						aLevelValue.equals("") &&
    						aUnitValue.equals("")){
    						addrList.add (getAddress(brmsAddr));
    					}
    					else if ((unitSize < maxUnits) &&	
    							 (isAddedToUnitList(aStructValue, aLevelValue, aUnitValue,
                                                    structValue, levelValue, unitValue)))
                        { 
                            addrList.add (getAddress(brmsAddr));
                            unitSize++;
    					}
    				}
    				else if (structValue.equals("") &&
    						 levelValue.equals("") &&
    						 unitValue.equals(""))
                    {
                        addrList.add (getAddress(brmsAddr));
                    }
                    else if ((unitSize < maxUnits) &&   
                             (isAddedToUnitList(aStructValue, aLevelValue, aUnitValue,
                                                 structValue, levelValue, unitValue)))
                    { 
                        addrList.add (getAddress(brmsAddr));
                        unitSize++;
    				}
    			} // end compareAddr
    			else
                {
    				unitSize = 0;
                    prevAddr.setBRMSFields(stNbr, stNm, town, BRMSTag.CT, "", 
                      structType, structValue, levelType, levelValue, unitType, unitValue);
                    if(structValue.equals("") &&
                       levelValue.equals("") &&
                       unitValue.equals(""))
                    {
                        addrList.add (getAddress(brmsAddr));
                    }
                    else if (unitSize < maxUnits)  
                    { 
                        addrList.add (getAddress(brmsAddr));
                        unitSize++;
                    }
    			}
    			if (addrList.size() > maxAddr)
    				break;
    		} // end while
    			
    		if (!addrList.isEmpty()){
    			int addrSize = (addrList.size() > maxAddr) ? maxAddr : addrList.size();
    			addressArray = new Address [addrSize];
    			for (int i = 0; i < addrSize; i++)
    			{
    				addressArray[i] = (Address) addrList.get (i);	
    			}
    			addrList.clear();
    		}
    		else{			
    			aLogger.log (LogEventId.DEBUG_LEVEL_2, "No address records found for LFACS WireCenter=" + aWireCenter + 
    				" HouseNumber>=" + aHouseNumber + " StreetName=" + aStreetName + 
    				" Bldg=" + aStructValue + " Floor=" + aLevelValue + " Unit=" + aUnitValue);
    			addressArray = new Address [0];
    		}
    
    	} // end try
    	catch (AddressHandlerException ahe){}
    	catch (SQLException e) {
    		e.printStackTrace();
    		aLogger.log( LogEventId.DEBUG_LEVEL_2, "Get Address by Wire Center and Street Name failed." );
    		throw e;
    			
    	} finally {
    		try {
    			if (rsltSet != null)
    				rsltSet.close();
    			if (preparedSql != null)
    				preparedSql.close();
    		} catch (Exception e) {
    			aLogger.log(LogEventId.DEBUG_LEVEL_2, "getAddrByWireCntrAndStNm: Exception occurred in finally with message " + e.getMessage());
    		}
    	}
    	
    	return addressArray;
    }
    
    /**
     * Get Address from data extracted from the LFACS_SAG_LU_STATUS table.
     * Creation date: (1/22/02 12:32:35 PM)
     * @return Address
     * @param brmsAddr AddressHandlerBRMS
     * @param unitList ArrayList
     */
    private static Address getAddress(AddressHandlerBRMS brmsAddr) 
    {
    	FieldedAddress fa = new FieldedAddress(
    		IDLUtil.toOpt (brmsAddr.getRoute()),
    		IDLUtil.toOpt (brmsAddr.getBox()),
    		IDLUtil.toOpt (brmsAddr.getHousNbrPfx()),
    		IDLUtil.toOpt (brmsAddr.getHousNbr()),
    		IDLUtil.toOpt (brmsAddr.getAasgndHousNbr()),
    		IDLUtil.toOpt (brmsAddr.getHousNbrSfx()),
    		IDLUtil.toOpt (brmsAddr.getStDir()),
    		IDLUtil.toOpt (brmsAddr.getStName()),
    		IDLUtil.toOpt (brmsAddr.getStThorofare()),
    		IDLUtil.toOpt (brmsAddr.getStNameSfx()),
    		IDLUtil.toOpt (brmsAddr.getCity()),
    		IDLUtil.toOpt (brmsAddr.getState()),
    		IDLUtil.toOpt (brmsAddr.getPostalCode()),
            IDLUtil.toOpt (""),              // PostalCodePlus4
    		IDLUtil.toOpt (brmsAddr.getCounty()),
            IDLUtil.toOpt (brmsAddr.getCountry()),
    		IDLUtil.toOpt (brmsAddr.getStructType()),
    		IDLUtil.toOpt (brmsAddr.getStructValue()),
            IDLUtil.toOpt (brmsAddr.getLevelType()),
            IDLUtil.toOpt (brmsAddr.getLevelValue()),
            IDLUtil.toOpt (brmsAddr.getUnitType()),
            IDLUtil.toOpt (brmsAddr.getUnitValue()),
            IDLUtil.toOpt (""),              // OriginalStreetDirection
            IDLUtil.toOpt (""),              // OriginalStreetNameSuffix
            IDLUtil.toOpt (new String[0]),   // CassAddressLines
            IDLUtil.toOpt (new String[0]),   // AuxiliaryAddressLines
            IDLUtil.toOpt (""),              // CassAdditionalInfo
            IDLUtil.toOpt (brmsAddr.getAddAddrInfo()),
            IDLUtil.toOpt (""),				// BusinessName
            IDLUtil.toOpt(""),
    		IDLUtil.toOpt(""),
    		IDLUtil.toOpt(""),
    		IDLUtil.toOpt(""),
    		IDLUtil.toOpt(""),
    		IDLUtil.toOpt(""));             
    
    	Address newAddress = new Address ();
    	newAddress.aFieldedAddress(fa);
    	return newAddress;
    }
    
    /**
     * WARNING --- In order to use this method, you'll need to create new properties file.
     * =======
     * Gets a database connection and calls getAddrByWireCntrAndStNm method to retrieve
     * the Address Data by Street Name and Wire Center from the lfacs_sag_lu_status table
     * If the requested Address is found, the corresponding Data or array of data is returned to the client. 
     * The following JDBC properties are required in the properties file:
     * <P>
     * jdbcDATA_SOURCE_NAME = Data Source Name<BR>
     * jdbcUSERID = User Id<BR>
     * jdbcPASSWORD = Password<BR>
     * jdbcDRIVER = Drive name<BR>
     * jdbcURL = Database location for local driver<BR>
     * jdbcUSE_CONNECTION_POOL = True or False
     * <P>
     * Creation date: (08/23/01 11:08:36 AM)
     * @return Address[]
     * @param aFile String
     * @param wireCenter String
     * @param streetName String
     * @param houseNumber String
     * @param city String
     * @param units Unit[]
     * @param maxAddr int
     * @param maxUnit int
     * @param aLogger Logger
     * @exception SQLException If the DBConnection method or the getAddrByWireCntrAndStNm
     * method detects an exception, SQLException will be thrown or rethrown.
     */
    public static Address [] retrieveAddrByWireCntrAndStNm (String aFile,  
    		String wireCenter, String streetName, String houseNumber, String city, 
            String structValue, String levelValue, String unitValue, int maxAddr, int maxUnit, Logger aLogger) 
    throws SQLException
    {
    	
    	DBConnection dbConn = null;
    	DataSource dsHome = null;
    	Connection tblConnection = null;
    	
    	Address [] retVal = null;
    	
    	try {
    		/**
    		 * Passes the JDBC access related properties in aFile to DBConnection method
    		 * to access the Oracle database.
    		 */
    		aLogger.log( LogEventId.DEBUG_LEVEL_2, "Calling DBConnection... ");
    
    		if ( dsHome == null ) {
    			aLogger.log( LogEventId.DEBUG_LEVEL_2, "Getting a new DBConnection... ");
    			dbConn = new DBConnection( aFile, aLogger );
    			dsHome = dbConn.getDsHome();				// save as cache entry
                aLogger.log(LogEventId.AUDIT_TRAIL,"RetrieveAddrByWireCntrAndStNm::retrieveAddrByWireCntrAndStNm()|DBConnection::getConnection()|PRE");			
                tblConnection = dbConn.getConnection();
                aLogger.log(LogEventId.AUDIT_TRAIL,"RetrieveAddrByWireCntrAndStNm::retrieveAddrByWireCntrAndStNm()|DBConnection::getConnection()|POST");
    		} else {
    			aLogger.log( LogEventId.DEBUG_LEVEL_2, "*** Using an existing DBConnection... ");
                aLogger.log(LogEventId.AUDIT_TRAIL,"RetrieveAddrByWireCntrAndStNm::retrieveAddrByWireCntrAndStNm()|DBConnection::getDSConnection()|PRE");			
                tblConnection = dbConn.getDSConnection( "", 
    										     		dbConn.getUserId(),
    										     		dbConn.getPassWord()
    										     		//"",
    										     		//"" 
    										     		);
                aLogger.log(LogEventId.AUDIT_TRAIL,"RetrieveAddrByWireCntrAndStNm::retrieveAddrByWireCntrAndStNm()|DBConnection::getDSConnection()|POST");
    		}
            aLogger.log(LogEventId.AUDIT_TRAIL,"RetrieveAddrByWireCntrAndStNm::retrieveAddrByWireCntrAndStNm()|RetrieveAddrByWireCntrAndStNm::getAddrByWireCntrAndStNm()|PRE");
    		retVal = getAddrByWireCntrAndStNm (wireCenter, streetName, houseNumber, city, 
                        structValue, levelValue, unitValue, maxAddr, maxUnit, aLogger, dbConn, tblConnection);
            aLogger.log(LogEventId.AUDIT_TRAIL,"RetrieveAddrByWireCntrAndStNm::retrieveAddrByWireCntrAndStNm()|RetrieveAddrByWireCntrAndStNm::getAddrByWireCntrAndStNm()|POST");
    	}
    	catch (SQLException e) {
    		dsHome = null ;								// clear out cache
    		throw e;
    	}
    	catch (Exception e) {
    		e.printStackTrace();
    		aLogger.log( LogEventId.DEBUG_LEVEL_2, "Get DBConnection failed... ");
    		throw new SQLException("DBConnection failed. " + e.getMessage());	
    	}
    	finally {
    		try {
    			dbConn.disconnect();
    			aLogger.log( LogEventId.DEBUG_LEVEL_2, "DBConnection disconnected. Done.");
    		} catch (Exception e) {
    			aLogger.log(LogEventId.DEBUG_LEVEL_2, "retrieveAddrByWireCntrAndStNm:  DBConnection disconnect failed in finally with message " + e.getMessage());
    		}
    	}
    	return retVal;
    }
    
    /**
     * Gets a database connection and calls getAddrByWireCntrAndStNm method to retrieve
     * the Address Data by Street Name and Wire Center from the lfacs_sag_lu_status table.
     * If the requested Address is found, the corresponding Data or array of data is returned to the client.
     * The following JDBC properties are required in the properties file:
     * <P>
     * jdbcDATA_SOURCE_NAME = Data Source Name<BR>
     * jdbcUSERID = User Id<BR>
     * jdbcPASSWORD = Password<BR>
     * jdbcDRIVER = Drive name<BR>
     * jdbcURL = Database location for local driver<BR>
     * jdbcUSE_CONNECTION_POOL = True or False
     * <P>
     * Creation date: (08/23/01 3:14:59 PM)
     * @return Address[]
     * @param aProperty Properties
     * @param wireCenter String
     * @param streetName String
     * @param houseNumber String
     * @param city String
     * @param units Unit[]
     * @param maxAddr      an int
     * @param maxUnit      an int
     * @param aLogger Logger
     * @exception SQLException If the DBConnection method or the getAddrByWireCntrAndStNm
     * method detects an exception, SQLException will be thrown or rethrown.
     * Also, if the required JDBC properties are not defined, a SQLException will be thrown.
     */
    public static Address [] retrieveAddrByWireCntrAndStNm (Properties aProperty, 
    	String wireCenter, String streetName, String houseNumber, String city, 
        String structValue, String levelValue, String unitValue , int maxAddr, int maxUnit, Logger aLogger) 
    throws SQLException
    {
    	DBConnection dbConn = null;
    	DataSource dsHome = null;
    	Connection tblConnection = null;
    	
    	String jdbcDataSourceName = "";
    	String jdbcUsrId = "";
    	String jdbcPassWord = "";
    	String jdbcDriver = "";
    	String useDataSourcePool = "";
    	String jdbcContextFactory = "";
    	String jdbcContextUrl = "";
    	String jdbcUrl = "";
    
    	try {
    		jdbcDataSourceName = aProperty.getProperty("jdbcDATA_SOURCE_NAME").trim();
    		jdbcUsrId = aProperty.getProperty("jdbcUSERID").trim();
    		jdbcPassWord = aProperty.getProperty("jdbcPASSWORD").trim();
    		jdbcDriver = aProperty.getProperty("jdbcDRIVER").trim();
    		useDataSourcePool = aProperty.getProperty("jdbcUSE_CONNECTION_POOL").trim();
    		jdbcUrl = aProperty.getProperty("jdbcURL").trim();
    	} catch (Exception e) {
    		aLogger.log(LogEventId.DEBUG_LEVEL_2,
    			"Get JDBC properties failed. JDBC required tags are not defined in properties file. ");
    		throw new SQLException("Get JDBC properties failed. JDBC required tags are not defined in properties file.");
    	}
    
    	Address [] retVal = null;
    
    	boolean bRetry  = true;
    	int iRetryCount = 0;
    
    	try {
    		while( bRetry ) {
    			try {
    				/**
    				 * Passes the JDBC access related properties to DBConnection() to
    				 * access the SYBASE-MORTEL database.
    				 */
    				aLogger.log( LogEventId.DEBUG_LEVEL_2, "retrieveAddrByWireCntrAndStNm: Calling DBConnection...");
    
    				if ( dsHome == null ) {
    					aLogger.log( LogEventId.DEBUG_LEVEL_2, "Getting a new DBConnection...");
    					dbConn = new DBConnection(  jdbcDataSourceName,
    												jdbcUsrId,
    												jdbcPassWord,
    												jdbcUrl,
    												jdbcDriver,
    												useDataSourcePool,
    												aLogger);
    					//dsHome = dbConn.getDsHome();  // Don't save as cache entry
                        aLogger.log(LogEventId.AUDIT_TRAIL,"RetrieveAddrByWireCntrAndStNm::retrieveAddrByWireCntrAndStNm()|DBConnection::getConnection()|PRE");
    					tblConnection = dbConn.getConnection();
                        aLogger.log(LogEventId.AUDIT_TRAIL,"RetrieveAddrByWireCntrAndStNm::retrieveAddrByWireCntrAndStNm()|DBConnection::getConnection()|POST");	
    				} else {
    					aLogger.log( LogEventId.DEBUG_LEVEL_2, "*** Using an existing DBConnection...");
                        aLogger.log(LogEventId.AUDIT_TRAIL,"RetrieveAddrByWireCntrAndStNm::retrieveAddrByWireCntrAndStNm()|DBConnection::getDSConnection()|PRE");					
                        tblConnection = dbConn.getDSConnection( jdbcDataSourceName, 
    										     				jdbcUsrId,
    										     				jdbcPassWord );
                        aLogger.log(LogEventId.AUDIT_TRAIL,"RetrieveAddrByWireCntrAndStNm::retrieveAddrByWireCntrAndStNm()|DBConnection::getDSConnection()|POST");
    				}
                    
                    aLogger.log(LogEventId.AUDIT_TRAIL,"RetrieveAddrByWireCntrAndStNm::retrieveAddrByWireCntrAndStNm()|RetrieveAddrByWireCntrAndStNm::getAddrByWireCntrAndStNm()|PRE");	
    				retVal = getAddrByWireCntrAndStNm (wireCenter, streetName, houseNumber, city, 
                                structValue, levelValue, unitValue, maxAddr, maxUnit, aLogger, dbConn, tblConnection );
                    aLogger.log(LogEventId.AUDIT_TRAIL,"RetrieveAddrByWireCntrAndStNm::retrieveAddrByWireCntrAndStNm()|RetrieveAddrByWireCntrAndStNm::getAddrByWireCntrAndStNm()|POST"); 				
                    bRetry = false;									// done
    			}
    			catch (StaleConnectionException e) {
    				aLogger.log( LogEventId.INFO_LEVEL_2, "retrieveAddrByWireCntrAndStNm: Get DBConnection failed with StaleConnection exception. RetryCount<" + iRetryCount + ">");
    				if ( iRetryCount++ > 1) {
    					bRetry = false;								// done
    					e.printStackTrace();
    					aLogger.log( LogEventId.DEBUG_LEVEL_2, "retrieveAddrByWireCntrAndStNm: Get DBConnection failed with StaleConnection exception.");
    					throw new SQLException("DBConnection failed. " + e.getMessage());
    				}
    			}
    			catch (SQLException e) {
    				bRetry = false;									// done
    				dsHome = null ;									// clear out cache
    				throw e;
    			}
    		} // while
    	}
    	finally {
    		try {
    			dbConn.disconnect();
    			aLogger.log( LogEventId.DEBUG_LEVEL_2, "DBConnection disconnected. Done.");
    		} catch (Exception e) {
    			aLogger.log(LogEventId.DEBUG_LEVEL_2, "retrieveAddrByWireCntrAndStNm:  DBConnection disconnect failed in finally with message " + e.getMessage());
    		}
    	}
    	return retVal;
    }
    
    /**
     * Insert single quote where imbedded in string value.
     * Creation date: (3/8/02 12:19:14 PM)
     * @return String
     * @param value String
     */
    private static String sqlMarkUp(String value) {
    
    	if ((value == null) ||
    		(value.length() == 0))
    		return value;
    
    	String temp = new String(value);
    	StringBuffer valueBuffer = new StringBuffer(value);
    	int i = 0;
    		
    	while(i < temp.length()){
    		if (temp.indexOf("'", i) >= 0){
    			i = temp.indexOf("'", i);
    			valueBuffer.insert(i, "'");
    			temp = valueBuffer.toString();
    			i = i + 2;
    		}
    		else
    			i = temp.length();
    	} // end while
    	
    	return valueBuffer.toString();
    }
    
    /**
     * Only add units greater than the requested units.
     * Creation date: (3/4/02 4:52:38 PM)
     * @param reqBldg String
     * @param reqFloor String
     * @param reqUnit String
     * @param bldgValue String
     * @param floorValue String
     * @param unitValue String
     */
    private static boolean isAddedToUnitList(String reqBldg, String reqFloor, String reqUnit, String bldgValue, String floorValue, String unitValue )
    {
    
        // ***WARNING*** There is a problem with this routine:
        // This routine attempts to return only those unit values equal to or greater than the requested value.
        // The compareToIgnoreCase method will compare strings based on their ASCIII value.
        // Oracle orders the data based on their binary value.  So when determining the entry point
        // to begin extraction of the units for SNET, the results could be unpredictable based on
        // the different sort and comparison criteria.  If the requested unit is alpha and the
        // database is alpha, or if the requested unit is numeric and the database is numeric, the entry
        // point will be correct.  When either the request or database unit value is a mix, the results
        // will be unpredictable based on differences between binary and ASCIII order. This can be corrected
        // by replacing the String compareTo method with a new method that performs a binary comparison.
    
        if (bldgValue != null){
            if ((reqBldg == null) ||
               ((reqBldg.compareToIgnoreCase(bldgValue)) < 0)){
                return true;
            }
            if ((reqBldg != null) &&
               ((reqBldg.compareToIgnoreCase(bldgValue)) > 0)){
                return false;
            }
        }
        else if ((reqBldg != null) &&
                 (reqBldg.length() > 0)){
                return false;
        }
            
        if (floorValue != null){
            if ((reqFloor == null) ||
               ((reqFloor.compareToIgnoreCase(floorValue)) < 0)){
                return true;
            }
            if ((reqFloor != null) &&
                ((reqFloor.compareToIgnoreCase(floorValue)) > 0)){
                return false;
            }
        }   
        else if ((reqFloor != null) &&
                 (reqFloor.length() > 0)){
                return false;
        }
    
        if  (unitValue != null){
            if ((reqUnit == null) ||
               ((reqUnit.compareToIgnoreCase(unitValue)) < 0 )){ 
                return true;
            }
        }
        
        return false;
    }
}