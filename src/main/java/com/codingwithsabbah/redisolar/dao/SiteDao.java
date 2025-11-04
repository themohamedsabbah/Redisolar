package com.codingwithsabbah.redisolar.dao;

import com.codingwithsabbah.redisolar.model.Site;

import java.util.Set;

public interface SiteDao {

    void save(Site site);

    Site findById(long id);

    Set<Site> findAll();
}
