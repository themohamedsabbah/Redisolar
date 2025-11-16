package com.codingwithsabbah.redisolar.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import redis.clients.jedis.args.GeoUnit;

@Builder
@Getter
@Setter
public class GeoQuery {
    private Coordinate coordinate;
    private Double redius;
    private GeoUnit geoUnit;
    private boolean onlyExcessCapacity;

}
