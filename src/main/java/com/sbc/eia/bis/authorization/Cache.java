package com.sbc.eia.bis.authorization;

import java.util.*;
import com.sbc.bccs.utilities.Logger;
import java.sql.SQLException;
import com.sbc.eia.bis.framework.logging.LogEventId;

/**
 * This class holds the nested Map structure to offer the cache mechanism.
 * Chief public methods are: initialLoad, exists
 * None of the private methods should be called outside synchronied blocks.
 * 
 * Creation date: (06/29/2004)
 * @author rs8434
 * 
 *  History :
 *  Date      	| Author        	| Version	| Notes
 *  ----------------------------------------------------------------------------
 * 	06/29/2004    Rajarsi Sarkar	  1.0		  Modified
 */

	public class Cache {
		static final String BIS_OWNER = "BIS_OWNER";// Substitutes for BisOwner when its input is found to be empty.
		// The following lock ensures that initialLoad and exists dont run simulteneously.
		private ReadWriteLock rwLock_InitialLoad = new ReadWriteLock();
		private HashMap m_cache = new HashMap();
		
		/**  
		 * isEmpty: 	Checks to see if the root map is empty or not.
	     * @param    	none			
	     * @return   	boolean. true if empty, false otherwise.
	     * @throws		none
	     */
		public boolean isEmpty()
		{
			return m_cache.isEmpty();
		}
		
		/**  
		 * initialLoad: Performs loading of the cache with full records from database. 
		 * 				At the end cache is cleaned up in the event no record was found.
	     * @param    	BisAuthorizationRow - aBisAuthorizationRow (bisOwner, which is oprional, can alone suffice for this function)
	     * 				Logger				- aLogger
	     * 				Hashtable			- aProperties
	     * @return   	int(Possible values)- NO_RECORD_FOUND, RECORD_FOUND, GET_DB_RECORD, EXCLUDED_RECORD_FOUND
	     * @throws		AuthorizationException
	     */
		public void initialLoad(BisAuthorizationRow aBisAuthorizationRow, Logger aLogger, Hashtable aProperties) throws AuthorizationException
		{
			try{
				aLogger.log(LogEventId.DEBUG_LEVEL_1, "AUTHORIZATION_INITIAL_LOAD requested" );
				rwLock_InitialLoad.requestWrite();//Blocks entry to cache so exists() doesnot happen simulteneously
				if (m_cache.isEmpty()) 
				{
					//int RC = ClientAuthorization.NO_RECORD_FOUND;
					String bisOwner = aBisAuthorizationRow.get_bisOwner();
					BisAuthorizationRow bisAuthorizationRow = new BisAuthorizationRow();
					bisAuthorizationRow.set_bisOwner(bisOwner);
					BisAuthorizationTable bisUser = new BisAuthorizationTable();
					try 
					{
						ArrayList retList = bisUser.getRecord(bisAuthorizationRow,aLogger,aProperties );
						for(int i = 0; i<retList.size(); i++)
						{
							add((BisAuthorizationRow)retList.get(i));						
						}
					}
					catch ( SQLException e )
					{
						aLogger.log(LogEventId.DEBUG_LEVEL_2, "SQL Exception bisUser.getRecord(): <" + e.getMessage() + ">" );
						throw new AuthorizationException ( e.getMessage() );
					}
					catch ( AuthorizationException e )
					{
						aLogger.log(LogEventId.DEBUG_LEVEL_2, "bisUser.getRecord(): <" + e.getMessage() + ">" );
						throw e ;
					}
				}
				
				//return RC;
			}
			finally
			{
				rwLock_InitialLoad.writeAccomplished();	
			}
		}
		
		/**  
		 * exists:		Loads the cache with partial records from database based on the input fields provided.
		 * 				Rows with null and excluded fields are picked up also if matched, in addition to the requested 
		 * 				rows
		 * 				At the end cache is cleaned up in the event no or timedout record was found.
	     * @param    	BisAuthorizationRow - aBisAuthorizationRow (bisOwner, which is oprional, can alone suffice for this function)
	     * 				Logger				- aLogger
	     * 				Hashtable			- aProperties
	     * @return   	int(Possible values)- NO_RECORD_FOUND, RECORD_FOUND, GET_DB_RECORD, EXCLUDED_RECORD_FOUND
	     * @throws		AuthorizationException
	     */
		public int exists(BisAuthorizationRow aBisAuthorizationRow, Logger aLogger, Hashtable aProperties) throws AuthorizationException
		{
			try{
				rwLock_InitialLoad.requestRead();			
				int RC = ClientAuthorization.GET_DB_RECORD;
				String inBisOwner = aBisAuthorizationRow.get_bisOwner();
				if(inBisOwner.trim().length()==0)
				inBisOwner = BIS_OWNER;
				String inMethodName = aBisAuthorizationRow.get_methodName();
				boolean isUpdate = false;
				
				HashMap methodMap = createBisMethodMaps(inBisOwner, inMethodName);//Rotten stuff created here.
				ArrayList lockMethodList = (ArrayList)methodMap.get(inMethodName);
				ReadWriteLock rwLock = (ReadWriteLock)lockMethodList.get(0);
				HashMap appMap = (HashMap)lockMethodList.get(1);
				String appName = aBisAuthorizationRow.get_application();
				try{
					rwLock.requestRead();
					if(appMap.containsKey(appName))
					{
						if((RC=searchAppMap(aBisAuthorizationRow, appMap))==ClientAuthorization.GET_DB_RECORD)	
						isUpdate = true;
					}
					else 
					isUpdate = true;
				}
				finally
				{
					rwLock.readAccomplished();	
				}
				if(isUpdate)
				{
					try
					{
						rwLock.requestWrite();
						aLogger.log(LogEventId.DEBUG_LEVEL_2,"Update Lock LOCKED by: " + inMethodName + "+" + appName);
						if((RC=searchAppMap(aBisAuthorizationRow, appMap))==ClientAuthorization.GET_DB_RECORD)
						{
							BisAuthorizationTable bisUser = new BisAuthorizationTable();
					 
							try {
								ArrayList retList = bisUser.getRecord(aBisAuthorizationRow, aLogger, aProperties);
								RC = ClientAuthorization.RECORD_FOUND;
								for (int i = 0; i<retList.size(); i++)
								{
									BisAuthorizationRow row = ( BisAuthorizationRow )retList.get(i);
									// add record to List
									addAppMap(row, appMap);
								}
								remove((BisAuthorizationRow)retList.get(0), appMap);
								RC=searchAppMap(aBisAuthorizationRow, appMap);
							}
							catch ( SQLException e )
							{
								//aLogger.log(LogEventId.DEBUG_LEVEL_2,"SQL Exception bisUser.getRecord(): <" + e.getMessage() + ">" );
								if ( e.getMessage().equals( BisAuthorizationTable.NO_ROWS ) )
								{
									remove(aBisAuthorizationRow.get_bisOwner(),aBisAuthorizationRow.get_methodName(),"");
									return ClientAuthorization.NO_RECORD_FOUND;
								}									
								throw new AuthorizationException ( e.getMessage() );
							}
							catch ( AuthorizationException e )
							{
								aLogger.log(LogEventId.DEBUG_LEVEL_2,"bisUser.getRecord(): <" + e.getMessage() + ">" );
								remove(aBisAuthorizationRow.get_bisOwner(),aBisAuthorizationRow.get_methodName(),"");
								throw e ;
							}
						}
					}
					finally
					{
						aLogger.log(LogEventId.DEBUG_LEVEL_2,"Update Lock RELEASED by: " + inMethodName + "+" + appName);
						rwLock.writeAccomplished();
					}
				}
				return RC;
			}
			finally
			{
				rwLock_InitialLoad.readAccomplished();	
			}
		}
		
//------------------------------------------------Private Methods-----------------------------------------------------
		//The following methods should not be invoked outside sunchronized blocks OR ReadWriteLocks as the case maybe.
		
		/**  
		 * searchAppMap:Searches the cache based on the input fields provided.
		 * 				
	     * @param    	BisAuthorizationRow - aBisAuthorizationRow (bisOwner, which is oprional, can alone suffice for this function)
	     * 				HashMap 			- aApplicationMap
	     * @return   	int(Possible values)- NO_RECORD_FOUND, RECORD_FOUND, GET_DB_RECORD, EXCLUDED_RECORD_FOUND
	     * @throws		none
	     */
		private int searchAppMap(BisAuthorizationRow aBisAuthorizationRow, HashMap aApplicationMap)
		{
			String inApplication = aBisAuthorizationRow.get_application();
			int RC = ClientAuthorization.GET_DB_RECORD;
			
			if(aApplicationMap.containsKey(inApplication))
			{
				ArrayList srvcntrBisGrpTOContainerList = (ArrayList)aApplicationMap.get(inApplication);
				String inServiceCenter = aBisAuthorizationRow.get_serviceCenter();
	            if(inServiceCenter == null) inServiceCenter = "";
	            String inBisUnit = aBisAuthorizationRow.get_businessUnit();
	            if(inBisUnit == null) inBisUnit = "";
	            String inGroupId = aBisAuthorizationRow.get_groupId();
	            if(inGroupId == null) inGroupId = "";
				String cachedServiceCenter, cachedBisUnit, cachedGroup = ""; 
				long cachedTimeOut = 0;
				ArrayList srvcntrBisGrpToList = null;
				boolean exclusion_found_but_auth = false;

				for(int i = 0; i<srvcntrBisGrpTOContainerList.size(); i++)
				{
					boolean cache_contains_exclusion = false;
				    RC = ClientAuthorization.GET_DB_RECORD;
					srvcntrBisGrpToList = (ArrayList)srvcntrBisGrpTOContainerList.get(i);
					cachedServiceCenter = srvcntrBisGrpToList.get(0).toString();
					cachedBisUnit = srvcntrBisGrpToList.get(1).toString();
					cachedGroup = srvcntrBisGrpToList.get(2).toString();
					cachedTimeOut = ((Long)srvcntrBisGrpToList.get(3)).longValue();
			
					if (cachedBisUnit.trim().length()!=0) 
					{
					    if(cachedBisUnit.indexOf("!")==0)
					    cache_contains_exclusion = true;

			            if(!cache_contains_exclusion)
						{
						    if(!cachedBisUnit.equalsIgnoreCase(inBisUnit))
						    continue;
						}
						else
						{
						    if(inBisUnit.length()!=0 && cachedBisUnit.equalsIgnoreCase(("!" + inBisUnit)))
						    {
						        RC = ClientAuthorization.EXCLUDED_RECORD_FOUND;
						        exclusion_found_but_auth = false;
						    }
						    else 
						    {
						        if(inBisUnit.length()!=0)
						        exclusion_found_but_auth = true;
						        continue;
						    }
                        }
					}
					if (cachedGroup.trim().length()!=0) 
					{
					    if(cachedGroup.indexOf("!")==0)
					    cache_contains_exclusion = true;
					    else cache_contains_exclusion = false;
			    	
						if(!cache_contains_exclusion)
						{
						    if(!cachedGroup.equalsIgnoreCase(inGroupId))
						    continue;
						}
						else 
						{
						    if(inGroupId.length()!=0 && cachedGroup.equalsIgnoreCase(("!" + inGroupId)))
						    {
						        RC = ClientAuthorization.EXCLUDED_RECORD_FOUND;
						        exclusion_found_but_auth = false;
						    }
						    else 
						    {
						        if(inGroupId.length()!=0)
						        exclusion_found_but_auth = true;
						        continue;
						    }
						}
					}
					if(cachedServiceCenter.trim().length()!=0) 
					{
			    	    if(cachedServiceCenter.indexOf("!")==0)
			    	    cache_contains_exclusion = true;
					    else cache_contains_exclusion = false;
			    
			            if(!cache_contains_exclusion)
						{
						    if(!cachedServiceCenter.equalsIgnoreCase(inServiceCenter))
						    continue;
						}
						else
						{
						    if(inServiceCenter.length()!=0 && cachedServiceCenter.equalsIgnoreCase(("!" + inServiceCenter)))
						    {    
						        RC = ClientAuthorization.EXCLUDED_RECORD_FOUND;
						        exclusion_found_but_auth = false;
						    }
						    else 
						    {
						        if(inServiceCenter.length()!=0)
						        exclusion_found_but_auth = true;
						        continue;
						    }
						}							        
					}
					if(System.currentTimeMillis() < cachedTimeOut)
					{
					    if(RC != ClientAuthorization.EXCLUDED_RECORD_FOUND)
					    return ClientAuthorization.RECORD_FOUND;
					    else return RC;
					}
					else return ClientAuthorization.GET_DB_RECORD;
				}
				if(System.currentTimeMillis() < cachedTimeOut)
				{
				    if(exclusion_found_but_auth)
					return ClientAuthorization.RECORD_FOUND;
				}
				else
				return ClientAuthorization.GET_DB_RECORD;
			}
			return RC;
		}
		
		/**  
		 * addAppMap:	Adds an element to the cache.
		 * 				
	     * @param    	BisAuthorizationRow - aBisAuthorizationRow (bisOwner, which is oprional, can alone suffice for this function)
	     * 				HashMap 			- aApplicationMap
	     * @return   	none
	     * @throws		none
	     */
		private void addAppMap(BisAuthorizationRow aBisAuthorizationRow, HashMap aApplicationMap)
		{
			String appName = aBisAuthorizationRow.get_application();
			if(aApplicationMap.containsKey(appName))
			{
				ArrayList srvcntrBisGrpTOContainerList = (ArrayList)aApplicationMap.get(appName);
				String cachedServiceCenter, cachedBisUnit, cachedGroup = ""; 
				String inBisUnit = aBisAuthorizationRow.get_businessUnit();
				if(inBisUnit == null)inBisUnit = "";
				String inServiceCenter = aBisAuthorizationRow.get_serviceCenter();
				if(inServiceCenter == null) inServiceCenter = "";
				String inGroupId = aBisAuthorizationRow.get_groupId();
				if(inGroupId == null) inGroupId = "";
				boolean foundFlag = false;
				ArrayList srvcntrBisGrpToList = null;					
				for (int i = 0; i<srvcntrBisGrpTOContainerList.size(); i++)
				{
					if(!foundFlag)
					{
						srvcntrBisGrpToList = (ArrayList) srvcntrBisGrpTOContainerList.get(i);
						cachedServiceCenter = srvcntrBisGrpToList.get(0).toString();
						cachedBisUnit = srvcntrBisGrpToList.get(1).toString();
						cachedGroup = srvcntrBisGrpToList.get(2).toString();
						long cachedTimeOut = ((Long)srvcntrBisGrpToList.get(3)).longValue();
						
						if(cachedBisUnit.equalsIgnoreCase(inBisUnit) && cachedServiceCenter.equalsIgnoreCase(inServiceCenter) && cachedGroup.equalsIgnoreCase(cachedGroup))
						{
							//Its updating coz timeout happened. So no need to check timeout
							srvcntrBisGrpToList.remove(0);
							srvcntrBisGrpToList.add(0, inServiceCenter);
							srvcntrBisGrpToList.remove(1);
							srvcntrBisGrpToList.add(1, inBisUnit);
							srvcntrBisGrpToList.remove(2);
							srvcntrBisGrpToList.add(2, inGroupId);
							srvcntrBisGrpToList.remove(3);
							srvcntrBisGrpToList.add(3, new Long(aBisAuthorizationRow.get_timeOutInMilliseconds()));
							foundFlag = true;
						}
					}							
				}
				if(!foundFlag)
				{
					srvcntrBisGrpToList = new ArrayList(4);
					srvcntrBisGrpToList.add(0, inServiceCenter);
					srvcntrBisGrpToList.add(1, inBisUnit);
					srvcntrBisGrpToList.add(2, inGroupId);
					srvcntrBisGrpToList.add(3, new Long(aBisAuthorizationRow.get_timeOutInMilliseconds()));
					srvcntrBisGrpTOContainerList.add(srvcntrBisGrpToList);
				}	 
			}
			else
			{
				//create appication
				aApplicationMap.put(aBisAuthorizationRow.get_application(), createServiceCenterList(aBisAuthorizationRow));
			}
		}
		
		/**  
		 * add:			Adds an element to the cache for initial load ONLY. It creates the bisOwner or methodMap before
		 * 				delegating further to the addAppMap()
		 * 				
	     * @param    	BisAuthorizationRow - aBisAuthorizationRow (bisOwner, which is oprional, can alone suffice for this function)
	     * 				
	     * @return   	none
	     * @throws		none
	     */
		private void add(BisAuthorizationRow aBisAuthorizationRow)
		{
			if(aBisAuthorizationRow.get_methodName().trim().length()==0 ||
			aBisAuthorizationRow.get_application().trim().length()==0)
			return;
			
			int RC = ClientAuthorization.GET_DB_RECORD;
			String inBisOwner = aBisAuthorizationRow.get_bisOwner();
			if(inBisOwner.trim().length()==0)
			inBisOwner = BIS_OWNER;
			String inMethodName = aBisAuthorizationRow.get_methodName();
			boolean isUpdate = false;
			
			HashMap methodMap = createBisMethodMaps(inBisOwner, inMethodName);
			//At this point the Method and App have been created. Populate the App.
			ArrayList lockMethodList = (ArrayList)methodMap.get(inMethodName);
			HashMap applicationMap = (HashMap)lockMethodList.get(1);
			addAppMap(aBisAuthorizationRow, applicationMap);		
		}
		
		/**  
		 * createBisMethodMaps:	Quickly creates the bisOwner or/and methodMap nodes. 
		 * 				
	     * @param    	String - aBisOwner
	     * 				String - aMethodName
	     * @return   	HashMap
	     * @throws		none
	     */
		private synchronized HashMap createBisMethodMaps(String aBisOwner, String aMethodName)
		{
			HashMap methodMap = null;
			
			if(!m_cache.containsKey(aBisOwner))
			m_cache.put(aBisOwner, new HashMap());
				
			methodMap = (HashMap)m_cache.get(aBisOwner);
			if(!methodMap.containsKey(aMethodName))
			{
				ArrayList inList = new ArrayList(2);
				inList.add(0, new ReadWriteLock());
				inList.add(1, new HashMap());
				methodMap.put(aMethodName, inList);
			}	
			return methodMap;	
		}
		
		/**  
		 * createApplicationMap:Creates the application Map. 
		 * 				
	     * @param    	BisAuthorizationRow - aBisAuthorizationRow
	     * @return   	HashMap
	     * @throws		none
	     */
		private HashMap createApplicationMap(BisAuthorizationRow aBisAuthorizationRow)
		{
			HashMap applicationMap = new HashMap();
									
			//Storing application info
			applicationMap.put(aBisAuthorizationRow.get_application(), createServiceCenterList(aBisAuthorizationRow));
			return applicationMap;
		}
		
		/**  
		 * createServiceCenterList:Creates the List for service center, bis group, timeout fields. 
		 * 				
	     * @param    	BisAuthorizationRow - aBisAuthorizationRow
	     * @return   	List
	     * @throws		none
	     */
		private List createServiceCenterList(BisAuthorizationRow aBisAuthorizationRow)
		{
			ArrayList srvcntrBisGrpTOContainerList = new ArrayList();
					
			String inServiceCenter = aBisAuthorizationRow.get_serviceCenter();
			if(inServiceCenter == null) inServiceCenter = "";	
			String inBisUnit = aBisAuthorizationRow.get_businessUnit();
			if(inBisUnit == null) inBisUnit = "";	
			String inGroup = aBisAuthorizationRow.get_groupId();
			if(inGroup == null) inGroup = "";	
			
			//Storing State and Auth info
			ArrayList srvcntrBisGrpToList = new ArrayList(4);
			srvcntrBisGrpToList.add(0, inServiceCenter);
			srvcntrBisGrpToList.add(1, inBisUnit);
			srvcntrBisGrpToList.add(2, inGroup);
			srvcntrBisGrpToList.add(3, new Long(aBisAuthorizationRow.get_timeOutInMilliseconds()));
			
			srvcntrBisGrpTOContainerList.add(srvcntrBisGrpToList);
			
			return srvcntrBisGrpTOContainerList;	
		}
		
		/**  
		 * remove:   	Removes timedout elements from cache 				
		 * @param		HashMap - aAppMap				
	     * 		    	BisAuthorizationRow - aBisAuthorizationRow
	     * @return   	none
	     * @throws		none
	     */
		private void remove(BisAuthorizationRow aBisAuthorizationRow, HashMap aAppMap)
		{
			String appName = aBisAuthorizationRow.get_application();
			if(aAppMap.containsKey(appName))
			{
				ArrayList srvcntrBisGrpTOContainerList = (ArrayList)aAppMap.get(appName);
				ArrayList srvcntrBisGrpToList = null;					
				for (int i = 0; i<srvcntrBisGrpTOContainerList.size(); i++)
				{
					srvcntrBisGrpToList = (ArrayList)srvcntrBisGrpTOContainerList.get(i);
					long cachedTimeOut = ((Long)srvcntrBisGrpToList.get(3)).longValue();
					
					if(System.currentTimeMillis()>cachedTimeOut)
					{
						srvcntrBisGrpTOContainerList.remove(i);
						i--;
					}
				}
				if(srvcntrBisGrpTOContainerList.size() == 0)
				remove(aBisAuthorizationRow.get_bisOwner(), aBisAuthorizationRow.get_methodName(), aBisAuthorizationRow.get_application());
			}		
		}
		
		/**  
		 * remove:   	Removes bisOwner, methodName elements from cache if found to contain no dependent elements				
		 * @param    	String - aBisOwner
		 * 				String - aMethodName
		 * 				String - aAppName
	     * @return   	none
	     * @throws		none
	     */
		private void remove(String aBisOwner, String aMethodName, String aAppName)
		{
			if(aBisOwner.trim().length() == 0)
			aBisOwner = BIS_OWNER;

			if(m_cache.containsKey(aBisOwner))
			{
				HashMap methodMap = (HashMap)m_cache.get(aBisOwner);
				if(methodMap.containsKey(aMethodName))
				{		
					if(aAppName.trim().length()==0)
					methodMap.remove(aMethodName);
					else
					{
						ArrayList lockAppList = (ArrayList)methodMap.get(aMethodName);
						HashMap appMap = (HashMap)lockAppList.get(1);
						if(appMap.containsKey(aAppName))
						{
							appMap.remove(aAppName);
						}
						if(appMap.isEmpty())
						methodMap.remove(aMethodName);
					}
				}
				if(methodMap.isEmpty())
				m_cache.remove(aBisOwner);
			}		
		}
		
		/** 
		 * displayCache: 	This method is NOT thread-safe and is ONLY meant for
		 * 					debugging purposes. Displays the contents of the cache. 
	     * @param    	none			
	     * @return   	String		
	     */
		public String displayCache()
		{
			StringBuffer buffer = new StringBuffer();
			Iterator it = m_cache.entrySet().iterator();
			while(it.hasNext())
			{
				Map.Entry me = (Map.Entry)it.next();
				String bisOwner = me.getKey().toString();
				buffer.append("BisOwner = " + bisOwner + '\n');
				HashMap methodMap = (HashMap)me.getValue();
				
				Iterator itMethod = methodMap.entrySet().iterator();
				while(itMethod.hasNext())
				{
					Map.Entry meMethod = (Map.Entry)itMethod.next();
					String methodName = meMethod.getKey().toString();
					buffer.append("----MethodName = " + methodName + '\n');
					ArrayList lockMethodList = (ArrayList)meMethod.getValue();
					HashMap appMap = (HashMap)lockMethodList.get(1);
					
					Iterator itApp = appMap.entrySet().iterator();
					while(itApp.hasNext())
					{
						Map.Entry meApp = (Map.Entry)itApp.next();
						String appName = meApp.getKey().toString();
						buffer.append("----------Application = " + appName + '\n');
						ArrayList srvcntrBisGrpTOContainerList = (ArrayList)meApp.getValue();
						ArrayList srvcntrBisGrpToList = null;	
						String cachedServiceCenter, cachedBisUnit, cachedGroup = "";
						for(int i = 0; i<srvcntrBisGrpTOContainerList.size(); i++)
						{
							buffer.append((i+1) + ":----\n");
							srvcntrBisGrpToList = (ArrayList) srvcntrBisGrpTOContainerList.get(i);
							cachedServiceCenter = srvcntrBisGrpToList.get(0).toString();
							cachedBisUnit = srvcntrBisGrpToList.get(1).toString();
							cachedGroup = srvcntrBisGrpToList.get(2).toString();
							long cachedTimeOut = ((Long)srvcntrBisGrpToList.get(3)).longValue();						
							buffer.append("BisUnit: " + cachedBisUnit + '\n');
							buffer.append("ServiceCenter: " + cachedServiceCenter + '\n');
							buffer.append("GroupId: " + cachedGroup + '\n');
							buffer.append("TimeOut: " + cachedTimeOut + '\n');							
						}
						buffer.append('\n');
					}
					buffer.append('\n');
				}
				buffer.append('\n');
			}
			return buffer.toString();	
		}
	}
