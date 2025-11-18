package com.codingwithsabbah.redisolar.service.impl;

import com.codingwithsabbah.redisolar.dao.SiteDao;
import com.codingwithsabbah.redisolar.model.Site;
import com.codingwithsabbah.redisolar.service.SiteService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@AllArgsConstructor
public class SiteServiceImpl implements SiteService {
    private final SiteDao siteDao;

    @Override
    public Set<Site> findAll() {
        return siteDao.findAll();
    }

    @Override
    public Site findById(long id) {
        return siteDao.findById(id);
    }
}
