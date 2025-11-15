package com.codingwithsabbah.redisolar.service.impl;

import com.codingwithsabbah.redisolar.dao.CapacityDao;
import com.codingwithsabbah.redisolar.model.CapacityReport;
import com.codingwithsabbah.redisolar.model.MeterReader;
import com.codingwithsabbah.redisolar.service.CapacityService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CapacityServiceImpl implements CapacityService {
    private final static int DEFAULT_LIMIT = 10;
    private final CapacityDao capacityDao;

    @Override
    public void update(MeterReader meterReader) {
        capacityDao.update(meterReader);
    }

    @Override
    public CapacityReport getCapacityReport(Integer limit) {
        return capacityDao.getReport(limit == null || limit < 0 ? DEFAULT_LIMIT : limit);
    }
}
