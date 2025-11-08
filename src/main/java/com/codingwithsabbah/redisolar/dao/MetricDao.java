package com.codingwithsabbah.redisolar.dao;

import com.codingwithsabbah.redisolar.model.MeasurementMinute;

public interface MetricDao {

    void save(String redisKey, MeasurementMinute measurementMinute);

}
