package com.dx.xjd.conf;

import com.dx.xjd.filter.ApiAuthorizationInterceptor;
import com.dx.xjd.filter.ManageAuthorizationInterceptor;
import com.dx.xjd.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.List;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter implements WebMvcRegistrations {

    @Autowired
    private Constants constants;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        if (constants.getSWAGGER()) {
            registry.addResourceHandler("swagger-ui.html")
                    .addResourceLocations("classpath:/META-INF/resources/");

            registry.addResourceHandler("docs.html")
                    .addResourceLocations("classpath:/META-INF/resources/");

            registry.addResourceHandler("/webjars/**")
                    .addResourceLocations("classpath:/META-INF/resources/webjars/");
        }
    }

    /*@Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new AdminInfoArgumentResolver());
    }

    @Override
    public RequestMappingHandlerMapping getRequestMappingHandlerMapping() {
        return new FeignRequestMappingHandlerMapping();
    }*/

    @Bean   //把我们的拦截器注入为bean
    public HandlerInterceptor getApiInterceptor(){
        return new ApiAuthorizationInterceptor();
    }

    @Bean   //把我们的拦截器注入为bean
    public HandlerInterceptor getManageInterceptor(){
        return new ManageAuthorizationInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //后台拦截器
        registry.addInterceptor(getManageInterceptor())
                .addPathPatterns("/manage/**")
                .excludePathPatterns("/manage/login")
                .excludePathPatterns("/manage/logOut");

        //API拦截器
        registry.addInterceptor(getApiInterceptor())
                .addPathPatterns("/api/**")
                .excludePathPatterns("/api/login")
                .excludePathPatterns("/api/logOut")
                .excludePathPatterns("/api/fastdfs/**");

        super.addInterceptors(registry);
    }

}
