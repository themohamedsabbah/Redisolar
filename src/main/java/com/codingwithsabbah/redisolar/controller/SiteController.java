package com.codingwithsabbah.redisolar.controller;

import com.codingwithsabbah.redisolar.dao.SiteDao;
import com.codingwithsabbah.redisolar.model.Coordinate;
import com.codingwithsabbah.redisolar.model.Site;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("/v1/sites")
public class SiteController {
    private final SiteDao siteDao;

    @GetMapping
    public ResponseEntity<?> getSites() {
        Set<Site> sites = siteDao.findAll();
        return ResponseEntity.ok(sites);
    }
}
