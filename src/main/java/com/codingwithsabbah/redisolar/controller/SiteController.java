package com.codingwithsabbah.redisolar.controller;

import com.codingwithsabbah.redisolar.model.Site;
import com.codingwithsabbah.redisolar.service.SiteService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("/v1/sites")
public class SiteController {
    private final SiteService siteService;

    @GetMapping
    public ResponseEntity<?> getSites() {
        Set<Site> sites = siteService.findAll();
        return ResponseEntity.ok(sites);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSite(@PathVariable("id") Long id) {
        return ResponseEntity.ok(siteService.findById(id));
    }
}
