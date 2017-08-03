package com.xianqin.security.shiro;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSON;
import com.xianqin.security.view.ResponseData;

public class OAuth2AuthenticationFilter extends AccessControlFilter
{

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)throws Exception
    {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        String token = httpRequest.getHeader(Constants.HEAD_TOKEN);
        if (StringUtils.isEmpty(token)){
            return false;
        }
        OAuth2Token oauth2Token = new OAuth2Token(token);
        try {
            //5、委托给Realm进行登录
            getSubject(request, response).login(oauth2Token);
        } catch (Exception e) {
            httpServletResponse.setHeader("Content-Type", "application/json;charset=UTF-8");
            ResponseData responseData = ResponseData.customerError();
            responseData.putDataValue("error", "002");
            responseData.putDataValue("error_description", "认证失败");
            String result = JSON.toJSONString(responseData);
            httpServletResponse.getWriter().write(result);
            return false;
        }
        return true;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception
    {

//        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
//        String method = httpServletRequest.getMethod();
//        if (RequestMethod.GET.toString().equals(method))
//        {
//            return getOnAccessDenied(httpServletRequest, response);
//        }
//        else
//        {
//            return postOnAccessDenied(httpServletRequest, response);
//        }
        
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
      //没有登录 没有token
        httpServletResponse.setHeader("Content-Type", "application/json;charset=UTF-8");
        ResponseData responseData = ResponseData.customerError();
        responseData.putDataValue("error", "002");
        responseData.putDataValue("error_description", "需要登录后操作");
        String result = JSON.toJSONString(responseData);
        httpServletResponse.getWriter().write(result);
        return true;
    }

    private boolean postOnAccessDenied(ServletRequest request, ServletResponse response) throws Exception
    {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        String token = httpRequest.getHeader(Constants.HEAD_TOKEN);
        if (StringUtils.isEmpty(token))
        {
            //没有登录 没有token
            httpServletResponse.setHeader("Content-Type", "application/json;charset=UTF-8");
            ResponseData responseData = ResponseData.customerError();
            responseData.putDataValue("error", "002");
            responseData.putDataValue("error_description", "需要登录后操作");
            String result = JSON.toJSONString(responseData);
            httpServletResponse.getWriter().write(result);
            return true;
        }

        String error = request.getParameter("error");
        String errorDescription = request.getParameter("error_description");
        if (!StringUtils.isEmpty(error))
        {
            //如果服务端返回了错误
            httpServletResponse.setHeader("Content-Type", "application/json;charset=UTF-8");
            ResponseData responseData = ResponseData.customerError();
            responseData.putDataValue("error", error);
            responseData.putDataValue("error_description", errorDescription);
            String result = JSON.toJSONString(responseData);
            httpServletResponse.getWriter().write(result);
            return true;
        }
        OAuth2Token oauth2Token = new OAuth2Token(token);
        try {
            //5、委托给Realm进行登录
            getSubject(request, response).login(oauth2Token);
        } catch (Exception e) {
            httpServletResponse.setHeader("Content-Type", "application/json;charset=UTF-8");
            ResponseData responseData = ResponseData.customerError();
            responseData.putDataValue("error", "002");
            responseData.putDataValue("error_description", "认证失败");
            String result = JSON.toJSONString(responseData);
            httpServletResponse.getWriter().write(result);
            return true;
        }
        
        return false;
    }

    private boolean getOnAccessDenied(ServletRequest request, ServletResponse response) throws Exception
    {
//      String error = request.getParameter("error");
//      String errorDescription = request.getParameter("error_description");
//      if (!StringUtils.isEmpty(error))
//      {//如果服务端返回了错误
//          WebUtils.issueRedirect(request, response, failureUrl + "?error=" + error + "error_description=" + errorDescription);
//          return true;
//      }
//
//      HttpServletRequest httpRequest = (HttpServletRequest) request;
//      String token = httpRequest.getHeader(Constants.HEAD_TOKEN);
//      Subject subject = getSubject(request, response);
//      if (!subject.isAuthenticated())
//      {
//          if (StringUtils.isEmpty(request.getParameter(authcCodeParam)))
//          {
//              return false;//在登录认证
//          }
//      }
//      //执行父类里的登录逻辑，调用Subject.login登录
//      return false;
        return false;
    }

}
