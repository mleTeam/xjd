package com.dx.xjd.filter;

import com.dx.xjd.annotation.Rule;
import com.dx.xjd.entity.Admininfo;
import com.dx.xjd.model.AccessToken;
import com.dx.xjd.redis.RedisComponent;
import com.dx.xjd.util.Constants;
import com.dx.xjd.util.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ManageAuthorizationInterceptor implements HandlerInterceptor {

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
        AccessToken token = null;
        Admininfo adminInfo = null;
        String authentication = request.getHeader(HttpHeaders.AUTHORIZATION);
		if(!(handler instanceof HandlerMethod)){
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return false;
		}
        // 判断是否携带了认证信息
        if (ObjectUtils.isEmpty(authentication)) {
        	Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    String name = cookie.getName().toUpperCase();
                    if ("MILIBANKING_MANAGE".equals(name)) {
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
        token = redisComponent.get(authentication, AccessToken.class);
        // 验证token是否生效
        if (ObjectUtils.isEmpty(token)) {
        	response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        	return false;
        }
        adminInfo = token.getAdminInfo();
        request.setAttribute(constants.getADMIN_INFO(), adminInfo);
        request.setAttribute(constants.getACCESS_TOKEN(), token.getAccessToken());
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		Rule anno = handlerMethod.getMethodAnnotation(Rule.class);//判断请求接口是否含有Rule注解
		if(ObjectUtils.isNotEmpty(anno)){
			String rule = anno.value();
			Boolean flag = false;
			//从token获取该管理员所有权限，与目标接口所需要权限进行比对
			for(String myRule : token.getRules()){
				if(myRule.equals(rule)){
					flag = true;
					break;
				}
			}
			if(!flag){
				response.setStatus(HttpServletResponse.SC_FORBIDDEN);
				return false;
			}
		}

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
