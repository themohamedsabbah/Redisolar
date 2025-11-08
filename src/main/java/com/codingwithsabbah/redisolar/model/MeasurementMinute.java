package com.codingwithsabbah.redisolar.model;

import lombok.Getter;

import java.text.DecimalFormat;

@Getter
public class MeasurementMinute {
    private final Double measurement;
    private final Integer minuteOfDay;
    private final DecimalFormat decimalFormat;

    public MeasurementMinute(Double measurement, Integer minuteOfDay) {
        this.measurement = measurement;
        this.minuteOfDay = minuteOfDay;
        this.decimalFormat = new DecimalFormat("#.##");
    }

    public String toString() {
        return decimalFormat.format(measurement) + ':' + minuteOfDay;
    }
}
