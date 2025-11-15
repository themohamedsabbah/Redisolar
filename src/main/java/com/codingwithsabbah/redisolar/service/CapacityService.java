package com.codingwithsabbah.redisolar.service;

import com.codingwithsabbah.redisolar.model.CapacityReport;
import com.codingwithsabbah.redisolar.model.MeterReader;

public interface CapacityService {

    void update(MeterReader meterReader);

    CapacityReport getCapacityReport(Integer limit);

}
