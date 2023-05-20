// $ Id$
package com.sbc.eia.bis.authorization;

/* Copyright Notice
 * RESTRICTED - PROPRIETARY INFORMATION
 * The information herein is for use only by authorized employees
 * of SBC Services Inc. and authorized Affiliates of SBC Services,
 * Inc., and is not for general distribution within or outside the
 * the respective companies.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2004 SBC Services, Inc.
 * All rights reserved.
 */
 
import java.util.*;
import java.io.*;

/**
 * Class used to lock the cache when reading from and writing to cache. An attempt to acquire the lock for reading 
 * will be prevented only if any write operations are in progress, so simultaneous read operations are the norm. An 
 * attempt to acquire the lock for writing will be prevented when ether read or write operations are in progress, and 
 * the requesting thread will be released when the current read or write completes.
 *
 * Creation date: (6/5/04)
 * @author rs8434
 * 
 *  History :
 *  Date      	| Author        	| Version	| Notes
 *  ----------------------------------------------------------------------------
 * 	06/05/2004	  Rajarsi Sarkar 	      1.0		  Creation.
 *	
 */

	public class ReadWriteLock
	{
		private int m_activeReaders = 0;
	    private int m_waitingReaders = 0;
	    private int m_activeWriters = 0;
	
		private final LinkedList writerLockList = new LinkedList();
		
		/**  
		 * requestRead: Attempts to acquire read lock. If any write thread is active or any write thread is in waiting
		 * 				then it waits until the write thread finishes
	     * @param    	- 
	     * 							
	     * @return   	-
	     * @throws		-
	     * @author     rs8434
	     */
		public synchronized void requestRead()
	    {
	    	//System.out.println("READING");
	        if( m_activeWriters==0 && writerLockList.size()==0 )
	            ++m_activeReaders;
	        else
	        {   ++m_waitingReaders;
	            try{ wait(); }catch(InterruptedException e){}
	        }
	    }
	    
	    /**  
		 * readAccomplished: Attempts to release the read lock. If there are no more active threads reading the cache
		 * 					it atempts to notify the writer threads.
		 * 				
	     * @param    	- 
	     * 							
	     * @return   	-
	     * @throws		-
	     * @author     rs8434
	     */
	    public synchronized void readAccomplished()
	    {   
	        --m_activeReaders;
	    	if( m_activeReaders == 0 )
	    	{
	            notifyWriters();
	    	}
	    	//System.out.println("FINISHED READING");
	    }
	    
		/**  
		 * requestWrite: Attempts to acquire the write lock. If any thread is actively reading the cache, it goes 
		 * 				into the waiting mode until notified.
		 * 				
	     * @param    	- 
	     * 							
	     * @return   	-
	     * @throws		-
	     * @author     rs8434
	     */
	    public void requestWrite()
	    {
	    	Object lock = new Object();
	        synchronized( lock )
	        {   
	        	synchronized( this )
	        	{   
	        		//System.out.println("WRITING");
	        		boolean okayToWrite = 	writerLockList.size() == 0 && // The first writer thread gets precedence
	        								m_activeReaders ==0 &&
	                                       	m_activeWriters == 0;
	                if( okayToWrite )
	                {   
	                	++m_activeWriters;
	                    return; 
	                }
	                writerLockList.addLast( lock );
	            }
	            try{ lock.wait(); } catch(InterruptedException e){}
	        }
	    }

		/**  
		 * writeAccomplished: Attempts to release the write lock. If gives precedence to threads waiting to read. If
		 * 					there are threads waiting to read it notifies them, else prompts the next writer thread 
		 * 					in waiting to resume.
		 * 				
	     * @param    	- 
	     * 							
	     * @return   	-
	     * @throws		-
	     * @author     rs8434
	     */
		public synchronized void writeAccomplished()
	    {
	        --m_activeWriters;
	        if( m_waitingReaders > 0 )
	            notifyReaders();
	        else
	            notifyWriters();
	        
	        //System.out.println("FINISHED WRITING");
	    }
		
		/**  
		 * notifyReaders: Notifies the reader threads to resume reading the cache. All the waiting threads will now 
		 * 				turn into active readers.
		 * 				
	     * @param    	- 
	     * 							
	     * @return   	-
	     * @throws		-
	     * @author     rs8434
	     */	
		private void notifyReaders()
	    {   
	        m_activeReaders  += m_waitingReaders;
	        m_waitingReaders = 0;
	        notifyAll();
	    }
	
		/**  
		 * notifyWriters: Notifies the next writer thread to resume writing to the cache.
		 * 				
	     * @param    	- 
	     * 							
	     * @return   	-
	     * @throws		-
	     * @author     rs8434
	     */	
		private void notifyWriters()
	    {   
	        if( writerLockList.size() > 0 )
	        {   
	            Object oldest = writerLockList.removeFirst();
	            ++m_activeWriters;
	            synchronized( oldest ){ oldest.notify(); }
	        }
	    }
	}
