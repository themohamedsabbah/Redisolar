package com.codingwithsabbah.redisolar.service.impl;

import com.codingwithsabbah.redisolar.dao.MetricDao;
import com.codingwithsabbah.redisolar.model.MeasurementMinute;
import com.codingwithsabbah.redisolar.model.MeterReader;
import com.codingwithsabbah.redisolar.model.MetricUnit;
import com.codingwithsabbah.redisolar.service.MetricService;
import com.codingwithsabbah.redisolar.util.RedisSchema;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

@Service
@AllArgsConstructor
public class MetricServiceImpl implements MetricService {
    private final MetricDao metricDao;

    @Override
    public void save(MeterReader meterReader) {
        Integer minuteOfDay = getMinuteOfDay(meterReader.getDateTime());
        save(meterReader, minuteOfDay, MetricUnit.WHGenerated, meterReader.getWhGenerated());
        save(meterReader, minuteOfDay, MetricUnit.WHUsed, meterReader.getWhUsed());
        save(meterReader, minuteOfDay, MetricUnit.TemperatureCelsius, meterReader.getTempC());
    }

    private Integer getMinuteOfDay(ZonedDateTime dateTime) {
        int hour = dateTime.getHour();
        int min = dateTime.getMinute();
        return hour * 50 + min;
    }

    private void save(MeterReader meterReader, Integer minuteOfDay, MetricUnit metricUnit, Double value) {
        String metricKey = RedisSchema.getDayMetricKey(meterReader.getSiteId(), metricUnit, meterReader.getDateTime());
        MeasurementMinute measurementMinute = new MeasurementMinute(value, minuteOfDay);
        metricDao.save(metricKey, measurementMinute);
    }

}
