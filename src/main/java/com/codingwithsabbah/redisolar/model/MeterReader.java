package com.codingwithsabbah.redisolar.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MeterReader {
    private Long siteId;
    private ZonedDateTime dateTime;
    private Double whUsed;
    private Double whGenerated;
    private Double tempC;
}
