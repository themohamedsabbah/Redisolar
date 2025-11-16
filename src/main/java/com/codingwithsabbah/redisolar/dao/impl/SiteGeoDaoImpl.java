package com.codingwithsabbah.redisolar.dao.impl;

import com.codingwithsabbah.redisolar.config.JacksonObjectToMapConverter;
import com.codingwithsabbah.redisolar.dao.SiteGeoDao;
import com.codingwithsabbah.redisolar.model.Coordinate;
import com.codingwithsabbah.redisolar.model.GeoQuery;
import com.codingwithsabbah.redisolar.model.Site;
import com.codingwithsabbah.redisolar.util.RedisSchema;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.resps.GeoRadiusResponse;

import java.util.*;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
@Slf4j
public class SiteGeoDaoImpl implements SiteGeoDao {
    private final JedisPool jedisPool;

    @Override
    public Set<Site> findByGeo(GeoQuery geoQuery) {
        Coordinate coordinate = geoQuery.getCoordinate();

        try (Jedis jedis = jedisPool.getResource()){
            List<GeoRadiusResponse> georadius = jedis.georadius(
                    RedisSchema.getSiteGeoKey(),
                    coordinate.getLng(),
                    coordinate.getLat(),
                    geoQuery.getRedius(),
                    geoQuery.getGeoUnit()
            );

            return georadius.stream()
                    .map(response -> jedis.hgetAll(response.getMemberByString()))
                    .filter(Objects::nonNull)
                    .map(s -> JacksonObjectToMapConverter.convert(s, Site.class))
                    .collect(Collectors.toSet());
        }
    }

    @Override
    public void save(Site site) {
        try (Jedis jedis = jedisPool.getResource()){
            String key = RedisSchema.getSiteHashKey(site.getId());
            jedis.hmset(key, JacksonObjectToMapConverter.convertToMap(key));

            if (site.getCoordinate() != null) {
                Double lng = site.getCoordinate().getLng();
                Double lat = site.getCoordinate().getLat();
                jedis.geoadd(RedisSchema.getSiteGeoKey(), lng, lat, key);
            }
        }
    }

    @Override
    public Site findById(long id) {
        Site site = null;
        try (Jedis jedis = jedisPool.getResource()){
            String siteHashKey = RedisSchema.getSiteHashKey(id);
            Map<String, String> fields = jedis.hgetAll(siteHashKey);
            if (fields != null && !fields.isEmpty()) {
                site = JacksonObjectToMapConverter.convert(fields, Site.class);
            }
        }
        return site;
    }

    @Override
    public Set<Site> findAll() {
        try (Jedis jedis = jedisPool.getResource()){
            List<String> siteKeys = jedis.zrange(RedisSchema.getSiteGeoKey(), 0, -1);

            Set<Site> sites = new HashSet<>(siteKeys.size());
            for (String key : siteKeys) {
                Map<String, String> fields = jedis.hgetAll(key);
                if (fields != null && !fields.isEmpty()) {
                    sites.add(JacksonObjectToMapConverter.convert(fields, Site.class));
                }
            }
            return sites;
        }
    }
}
