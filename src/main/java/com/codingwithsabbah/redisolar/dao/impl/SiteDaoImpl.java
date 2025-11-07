package com.codingwithsabbah.redisolar.dao.impl;

import com.codingwithsabbah.redisolar.config.JacksonObjectToMapConverter;
import com.codingwithsabbah.redisolar.dao.SiteDao;
import com.codingwithsabbah.redisolar.exception.GenericError;
import com.codingwithsabbah.redisolar.exception.NotFoundException;
import com.codingwithsabbah.redisolar.model.Site;
import com.codingwithsabbah.redisolar.util.RedisSchema;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Component
@AllArgsConstructor
@Slf4j
public class SiteDaoImpl implements SiteDao {
    private final JedisPool jedisPool;

    @Override
    public void save(Site site) {
        try (Jedis jedis = jedisPool.getResource()){
            String siteHashKey = RedisSchema.getSiteHashKey(site.getId());
            String siteIDsKey = RedisSchema.getSiteIDsKey();

            String sitesResult = jedis.hmset(siteHashKey, JacksonObjectToMapConverter.convertToMap(site));
            log.info(String.format("Site object saved successfully. {%s}", sitesResult));

            long keyResult = jedis.sadd(siteIDsKey, siteHashKey);
            log.info(String.format("Key added successfully. {%s}", keyResult));
        }
    }

    @Override
    public Site findById(long id) {
        try (Jedis jedis = jedisPool.getResource()){
            String key = RedisSchema.getSiteHashKey(id);
            Map<String, String> fields = jedis.hgetAll(key);
            if (fields == null || fields.isEmpty()) {
                throw new NotFoundException(GenericError.NOT_FOUND);
            }
            return JacksonObjectToMapConverter.convert(fields, Site.class);
        }
    }

    @Override
    public Set<Site> findAll() {
        Set<Site> sites = new HashSet<>();
        try (Jedis jedis = jedisPool.getResource()) {
            Set<String> keys = jedis.smembers(RedisSchema.getSiteIDsKey());

            for (String key : keys) {
                Map<String, String> fields = jedis.hgetAll(key);
                if (fields != null && !fields.isEmpty()) {
                    sites.add(JacksonObjectToMapConverter.convert(fields, Site.class));
                }
            }
        }

        return sites;
    }
}
