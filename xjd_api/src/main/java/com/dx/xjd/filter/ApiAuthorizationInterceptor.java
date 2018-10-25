package com.dx.xjd.filter;

import com.dx.xjd.entity.User;
import com.dx.xjd.model.UserAccessToken;
import com.dx.xjd.redis.RedisComponent;
import com.dx.xjd.util.Constants;
import com.dx.xjd.util.ObjectUtils;
import com.google.common.net.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ApiAuthorizationInterceptor implements HandlerInterceptor {

	@Autowired
	private RedisComponent redisComponent;

	@Autowired
    private Constants constants;

	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String requestURI = request.getRequestURI().toString();
        /**
         * 根据Authorization信息获取TOKEN信息
         *
         */  	  
        UserAccessToken token = null;
        User user = null;
        String authentication = request.getHeader(HttpHeaders.AUTHORIZATION);
        // 判断是否携带了认证信息
        if (ObjectUtils.isEmpty(authentication)) {
        	Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    String name = cookie.getName().toUpperCase();
                    if ("MILIBANKING_API".equals(name)) {
                    	authentication = cookie.getValue();              
                    }
                }
            } 
        }
        if (ObjectUtils.isEmpty(authentication)){
        	response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        	return false;
        }
        // 从缓存中拿去token信息
        token = redisComponent.get(authentication, UserAccessToken.class);
        // 验证token是否生效
        if (ObjectUtils.isEmpty(token)) {
        	response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        	return false;
        }
		user = token.getUser();
        request.setAttribute(constants.getUSER_INFO(), user);
        request.setAttribute(constants.getUSER_ACCESS_TOKEN(), token.getAccessToken());
        return true;
    }

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
	
}
