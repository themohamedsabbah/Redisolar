package com.codingwithsabbah.redisolar.dao.impl;

import com.codingwithsabbah.redisolar.dao.MetricDao;
import com.codingwithsabbah.redisolar.model.MeasurementMinute;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Component
@AllArgsConstructor
public class MetricDaoImpl implements MetricDao {
    private final JedisPool jedisPool;

    @Override
    public void save(String redisKey, MeasurementMinute measurementMinute) {
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.zadd(redisKey, measurementMinute.getMinuteOfDay(), measurementMinute.toString());
        }
    }

}
