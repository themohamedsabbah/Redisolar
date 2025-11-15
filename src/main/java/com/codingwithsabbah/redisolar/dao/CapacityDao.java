package com.codingwithsabbah.redisolar.dao;

import com.codingwithsabbah.redisolar.model.CapacityReport;
import com.codingwithsabbah.redisolar.model.MeterReader;

public interface CapacityDao {

    void update(MeterReader meterReader);

    CapacityReport getReport(Integer limit);
}
