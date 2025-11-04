package com.codingwithsabbah.redisolar.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Site {
    private Long id;

    private Double capacity;
    private Integer panels;

    private String address;
    private String city;
    private String state;
    private String postalCode;

    private Coordinate coordinate;
}
