package com.xianqin.security.service;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-2-17
 * <p>Version: 1.0
 */
public interface OAuthService {

    //添加 access token
    public void addAccessToken(String accessToken, String username);

    //验证access token是否有效
    public boolean checkAccessToken(String accessToken);

    //根据token获取用户信息
    public String getUsernameByAccessToken(String accessToken);

    //auth code / access token 过期时间
    long getExpireIn();
}
