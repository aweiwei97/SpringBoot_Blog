package com.example.demo.utils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MapCache {
    /**
     * 默认存储1024个缓存
     */
    private static final int DEFAULT_CACHES = 1024;

    private static final MapCache INS = new MapCache();

    /**
     * 缓存容器
     */
    private Map<String, CacheObject> cachePool;

    public static MapCache single() {
        return INS;
    }

    public MapCache() {
        this(DEFAULT_CACHES);
    }

    public MapCache(int cacheCount) {
        cachePool = new ConcurrentHashMap<>(cacheCount);
    }

    /**
     * 获取错误次数
     * @param key
     * @param
     * @return
     */
   public int get(String key){
        CacheObject cacheObject=cachePool.get(key);
        if(null!=cacheObject){
            long cur=System.currentTimeMillis()/1000;
            if(cacheObject.getExpired()<=0||cacheObject.getExpired()>cur){ //expired 过期时间，单位为秒,小于或等于0为不过期
                Object result=cacheObject.getValue();
                return (int)result;
            }
        }
        return 0;
   }

    /**
     * 设置一个缓存并带过期时间
     *System.currentTimeMillis()获得的是自1970-1-01 00:00:00.000 到当前时刻的时间距离,类型为long
     * @param key     缓存key
     * @param value   缓存value
     * @param expired 过期时间，单位为秒,小于或等于0为不过期
     */
    public void set(String key, Object value, long expired) {
        expired = expired > 0 ? System.currentTimeMillis() / 1000 + expired : expired;
        CacheObject cacheObject = new CacheObject(key, value, expired);
        cachePool.put(key, cacheObject);
    }

    static class CacheObject {
        private String key;
        private Object value;
        private long expired;

        public CacheObject(String key, Object value, long expired) {
            this.key = key;
            this.value = value;
            this.expired = expired;
        }

        public String getKey() {
            return key;
        }

        public Object getValue() {
            return value;
        }

        public long getExpired() {
            return expired;
        }
    }

}
