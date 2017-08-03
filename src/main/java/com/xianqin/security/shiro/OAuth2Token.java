package com.xianqin.security.shiro;

import org.apache.shiro.authc.AuthenticationToken;

public class OAuth2Token implements AuthenticationToken
{

    private static final long serialVersionUID = 1L;

    private String oauthToken;

    private String principal;

    public OAuth2Token(String oauthToken)
    {
        this.oauthToken = oauthToken;
    }

    public String getOauthToken()
    {
        return oauthToken;
    }

    public void setOauthToken(String oauthToken)
    {
        this.oauthToken = oauthToken;
    }

    public String getPrincipal()
    {
        return principal;
    }

    public void setPrincipal(String principal)
    {
        this.principal = principal;
    }

    @Override
    public Object getCredentials()
    {
        return oauthToken;
    }
}
