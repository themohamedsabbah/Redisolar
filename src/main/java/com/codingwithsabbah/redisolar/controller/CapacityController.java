package com.codingwithsabbah.redisolar.controller;

import com.codingwithsabbah.redisolar.model.CapacityReport;
import com.codingwithsabbah.redisolar.service.CapacityService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/v1/capacity")
public class CapacityController {
    private final CapacityService capacityService;

    @GetMapping("/")
    public ResponseEntity<CapacityReport> getCapacityReport(@RequestParam("limit") Integer limit) {
        CapacityReport report = capacityService.getCapacityReport(limit);
        return ResponseEntity.ok(report);
    }
}
