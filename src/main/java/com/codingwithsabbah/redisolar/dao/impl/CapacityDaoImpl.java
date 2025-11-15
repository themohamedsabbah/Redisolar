package com.codingwithsabbah.redisolar.dao.impl;

import com.codingwithsabbah.redisolar.dao.CapacityDao;
import com.codingwithsabbah.redisolar.model.CapacityReport;
import com.codingwithsabbah.redisolar.model.MeterReader;
import com.codingwithsabbah.redisolar.util.RedisSchema;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;
import redis.clients.jedis.resps.Tuple;

import java.util.List;

@Component
@AllArgsConstructor
@Slf4j
public class CapacityDaoImpl implements CapacityDao {
    private final JedisPool jedisPool;

    @Override
    public void update(MeterReader meterReader) {
        String key = RedisSchema.getCapacityRankingKey();
        double currentCapacity = meterReader.getWhGenerated() - meterReader.getWhUsed();
        try (Jedis jedis = jedisPool.getResource()){
            long result = jedis.zadd(key, currentCapacity, String.valueOf(meterReader.getSiteId()));
            log.info(String.format("capacity added successfully. {%s}", result));
        }
    }

    @Override
    public CapacityReport getReport(Integer limit) {
        String key = RedisSchema.getCapacityRankingKey();
        CapacityReport report = null;
        try (Jedis jedis = jedisPool.getResource()) {
            Pipeline pipeline = jedis.pipelined();
            Response<List<Tuple>> lowCapacity = pipeline.zrangeWithScores(key, 0, limit - 1);
            Response<List<Tuple>> highCapacity = pipeline.zrevrangeWithScores(key, 0, limit - 1);
            pipeline.sync();

            report = getCapacityReport(lowCapacity.get(), highCapacity.get());
        }
        return report;
    }

    private CapacityReport getCapacityReport(List<Tuple> lowCapacity, List<Tuple> highCapacity) {
        List<CapacityReport.SiteCapacityTuple> low = lowCapacity.stream().map(CapacityReport.SiteCapacityTuple::new).toList();
        List<CapacityReport.SiteCapacityTuple> high = highCapacity.stream().map(CapacityReport.SiteCapacityTuple::new).toList();
        return  new CapacityReport(low, high);
    }
}
