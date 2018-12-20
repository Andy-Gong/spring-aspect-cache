package com.aspect.cache;

import com.google.common.cache.Cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LocalCaches {
    private final static Map<String, Cache> localCaches = new ConcurrentHashMap();

    public static Cache getCache(String name) {
        return localCaches.get(name);
    }

    public static void addCache(String name, Cache cache) {
        localCaches.put(name, cache);
    }
}
