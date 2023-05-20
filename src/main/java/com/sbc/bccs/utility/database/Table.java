// $Id: Table.java,v 1.1 2002/09/29 02:27:13 dm2328 Exp $

package com.sbc.bccs.utility.database;

 import java.sql.*;
 import java.util.*;
 import javax.sql.*;
 import java.lang.*;
 import java.io.*;
 
/**
 * Used as base table for individual tables to accept connection
 * for dabase transaction
 * Creation date: (1/31/01 11:20:45 AM)
 * @author: Hongmei Parkin
 */

 
public class Table{
	protected Connection connection = null;
/**
 * Constructor
 */
public Table() {
	super();
}
/**
 * Table constructor.
 */
public Table(Connection dbConnection) {
  super();

  connection = dbConnection;	
}
}
