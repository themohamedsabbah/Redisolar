package com.codingwithsabbah.redisolar.service.impl;

import com.codingwithsabbah.redisolar.dao.SiteDao;
import com.codingwithsabbah.redisolar.model.Site;
import com.codingwithsabbah.redisolar.service.SiteService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class SiteServiceImpl implements SiteService {
    private final SiteDao siteDao;

    public SiteServiceImpl(@Qualifier("siteDaoImpl") SiteDao siteDao) {
        this.siteDao = siteDao;
    }

    @Override
    public Set<Site> findAll() {
        return siteDao.findAll();
    }

    @Override
    public Site findById(long id) {
        return siteDao.findById(id);
    }
}
