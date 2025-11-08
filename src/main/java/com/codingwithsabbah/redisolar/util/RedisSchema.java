package com.codingwithsabbah.redisolar.util;

import com.codingwithsabbah.redisolar.model.MetricUnit;

import java.time.ZonedDateTime;

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

    public static String getDayMetricKey(Long siteId, MetricUnit unit,
                                  ZonedDateTime dateTime) {
        return KeyHelper.getPrefix() +
                ":metric:" +
                unit.getShortName() +
                ":" +
                getYearMonthDay(dateTime) +
                ":" +
                String.valueOf(siteId);
    }

    private static String getYearMonthDay(ZonedDateTime dateTime) {
        return String.format("%d-%d-%d",
                dateTime.getYear(),
                dateTime.getMonthValue(),
                dateTime.getDayOfMonth()
        );
    }
}
