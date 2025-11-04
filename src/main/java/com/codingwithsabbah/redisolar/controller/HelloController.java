package com.codingwithsabbah.redisolar.controller;

import com.codingwithsabbah.redisolar.dao.SiteDao;
import com.codingwithsabbah.redisolar.model.Coordinate;
import com.codingwithsabbah.redisolar.model.Site;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class HelloController {
    private final SiteDao siteDao;

    @GetMapping("/test")
    public ResponseEntity<?> hello() {
        siteDao.save(new Site(
                1L,
                1.0,
                1,
                "A",
                "B",
                "C",
                "D",
                new Coordinate(1.0, 1.0)
        ));
        return null;
    }
}
