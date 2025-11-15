package com.codingwithsabbah.redisolar.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import redis.clients.jedis.resps.Tuple;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class CapacityReport {
    private List<SiteCapacityTuple> low;
    private List<SiteCapacityTuple> high;

    @Getter
    @Setter
    public static class SiteCapacityTuple {
        private Double capacity;
        private Long siteId;

        public SiteCapacityTuple(Tuple tuple) {
            this.capacity = tuple.getScore();
            this.siteId = Long.valueOf(tuple.getElement());
        }
    }
}
