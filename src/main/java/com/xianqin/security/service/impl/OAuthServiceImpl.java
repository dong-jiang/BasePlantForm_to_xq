package com.xianqin.security.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import com.xianqin.security.service.OAuthService;

@Service
public class OAuthServiceImpl implements OAuthService {

    private Cache cache;


    @Autowired
    public OAuthServiceImpl(CacheManager cacheManager) {
        this.cache = cacheManager.getCache("code-cache");
    }


    @Override
    public void addAccessToken(String accessToken, String username) {
        cache.put(accessToken, username);
    }

    @Override
    public String getUsernameByAccessToken(String accessToken) {
        return (String)cache.get(accessToken).get();
    }

    @Override
    public boolean checkAccessToken(String accessToken) {
        return cache.get(accessToken) != null;
    }

    @Override
    public long getExpireIn() {
        return 3600L;
    }
}
