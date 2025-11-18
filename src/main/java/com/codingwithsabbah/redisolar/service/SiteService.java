package com.codingwithsabbah.redisolar.service;

import com.codingwithsabbah.redisolar.model.Site;

import java.util.Set;

public interface SiteService {

    Set<Site> findAll();

    Site findById(long id);

}
