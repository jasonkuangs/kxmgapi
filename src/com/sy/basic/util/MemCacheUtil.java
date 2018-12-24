package com.sy.basic.util;

import java.util.List;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.apache.log4j.Logger;

/**
 * @author koler
 * @date 2015年4月16日 下午4:15:25
 */
public class MemCacheUtil {
    private static volatile CacheManager cacheManager;
    private static final Logger log = Logger.getLogger(MemCacheUtil.class);

    static void init(CacheManager cacheManager) {
	MemCacheUtil.cacheManager = cacheManager;
    }

    public static CacheManager getCacheManager() {
	return cacheManager;
    }

    static Cache getOrAddCache(String cacheName) {
	Cache cache = cacheManager.getCache(cacheName);
	if (cache == null) {
	    synchronized (cacheManager) {
		cache = cacheManager.getCache(cacheName);
		if (cache == null) {
		    log.warn("Could not find cache config [" + cacheName + "], using default.");
		    cacheManager.addCacheIfAbsent(cacheName);
		    cache = cacheManager.getCache(cacheName);
		    log.debug("Cache [" + cacheName + "] started.");
		}
	    }
	}
	return cache;
    }

    public static void put(String cacheName, Object key, Object value) {
	getOrAddCache(cacheName).put(new Element(key, value));
    }

    @SuppressWarnings("unchecked")
    public static <T> T get(String cacheName, Object key) {
	Element element = getOrAddCache(cacheName).get(key);
	return element != null ? (T) element.getObjectValue() : null;
    }

    @SuppressWarnings("rawtypes")
    public static List getKeys(String cacheName) {
	return getOrAddCache(cacheName).getKeys();
    }

    public static void remove(String cacheName, Object key) {
	getOrAddCache(cacheName).remove(key);
    }

    public static void removeAll(String cacheName) {
	getOrAddCache(cacheName).removeAll();
    }
}
