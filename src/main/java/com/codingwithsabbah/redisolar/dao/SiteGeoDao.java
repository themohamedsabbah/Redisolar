package com.codingwithsabbah.redisolar.dao;

import com.codingwithsabbah.redisolar.model.GeoQuery;
import com.codingwithsabbah.redisolar.model.Site;

import java.util.Set;

public interface SiteGeoDao extends SiteDao{

    Set<Site> findByGeo(GeoQuery geoQuery);

}
