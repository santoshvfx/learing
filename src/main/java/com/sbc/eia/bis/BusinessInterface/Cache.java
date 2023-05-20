// $Id: Cache.java,v 1.2 2003/03/12 00:10:58 dm2328 Exp $

package com.sbc.eia.bis.BusinessInterface;

/**
 * Cache is the interface which defines the mechanism by which Host can manipulate a cache.
 * Creation date: (2/19/01 1:01:32 PM)
 * @author: Creighton Malet
 */
public interface Cache {
/**
 * Adds a key to the cache, based on the Selector.
 * @param aSelector com.sbc.eia.bis.BusinessInterface.Selector
 */
void addKeyToCache(Selector aSelector);
}
