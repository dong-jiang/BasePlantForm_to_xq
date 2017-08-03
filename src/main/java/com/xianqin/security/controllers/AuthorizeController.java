package com.xianqin.security.controllers;

import java.net.URISyntaxException;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.oltu.oauth2.as.issuer.MD5Generator;
import org.apache.oltu.oauth2.as.issuer.OAuthIssuer;
import org.apache.oltu.oauth2.as.issuer.OAuthIssuerImpl;
import org.apache.oltu.oauth2.as.request.OAuthTokenRequest;
import org.apache.oltu.oauth2.as.response.OAuthASResponse;
import org.apache.oltu.oauth2.common.error.OAuthError;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.OAuthResponse;
import org.apache.oltu.oauth2.common.message.types.GrantType;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xianqin.security.service.LoginService;
import com.xianqin.security.service.OAuthService;
import com.xianqin.security.view.MenuVO;

@Controller
public class AuthorizeController
{

    @Autowired
    private OAuthService oAuthService;
    @Autowired
    private LoginService loginService;

    @RequestMapping(value="/login" ,method = RequestMethod.POST)
    public HttpEntity token(HttpServletRequest request) throws URISyntaxException, OAuthSystemException
    {
        try
        {
            
            //构建OAuth请求
            OAuthTokenRequest oauthRequest = new OAuthTokenRequest(request);
            OAuthResponse response = null;
            if (GrantType.PASSWORD.toString().equals(oauthRequest.getGrantType())
                    &&RequestMethod.POST.toString().equals(request.getMethod()))
            {
                //密码模式
                String username = oauthRequest.getUsername();
                String password = oauthRequest.getPassword();
//                String clientId = oauthRequest.getClientId();
//                String ip = request.getRemoteAddr();
                //校验用户名密码是否正确
                Subject subject = SecurityUtils.getSubject();
                UsernamePasswordToken token = new UsernamePasswordToken(username, password);
                subject.login(token);

                //生成Access Token
                OAuthIssuer oauthIssuerImpl = new OAuthIssuerImpl(new MD5Generator());
                final String accessToken = oauthIssuerImpl.accessToken();
                oAuthService.addAccessToken(accessToken, username);

                //生成OAuth响应
                response = OAuthASResponse.tokenResponse(HttpServletResponse.SC_OK).setAccessToken(accessToken)
                        .setExpiresIn(String.valueOf(oAuthService.getExpireIn())).buildJSONMessage();
            }
            else
            {
                response = OAuthASResponse.errorResponse(HttpServletResponse.SC_BAD_REQUEST).setError(OAuthError.TokenResponse.UNSUPPORTED_GRANT_TYPE)
                        .setErrorDescription("不支持此认证").buildJSONMessage();
            }
            return new ResponseEntity(response.getBody(), HttpStatus.valueOf(response.getResponseStatus()));
        }
        catch (OAuthProblemException e)
        {
            //构建错误响应
            OAuthResponse res = OAuthASResponse.errorResponse(HttpServletResponse.SC_BAD_REQUEST).error(e).buildJSONMessage();
            
//            OAuthResponse res = OAuthASResponse.errorResponse(HttpServletResponse.SC_BAD_REQUEST).error(e).buildJSONMessage();
            return new ResponseEntity(res.getBody(), HttpStatus.valueOf(res.getResponseStatus()));
        }
    }
    
    @RequestMapping(value = "/menu/", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<List<MenuVO>> getMenuList(){
        Subject subject = SecurityUtils.getSubject();
        String account = (String)subject.getPrincipal();
        return new ResponseEntity<List<MenuVO>>(loginService.queryMenuListByAccount(account), HttpStatus.OK);
    }
}
