package com.codingwithsabbah.redisolar.util;

public class RedisSchema {

    // sites:info:[siteId]
    // Redis type: hash
    public static String getSiteHashKey(long siteId) {
        return KeyHelper.getKey("sites:info:" + siteId);
    }

    // sites:ids
    // Redis type: set
    public static String getSiteIDsKey() {
        return KeyHelper.getKey("sites:ids");
    }

}
