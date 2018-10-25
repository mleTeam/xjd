package com.dx.xjd.util;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Data
@Component
public class Constants implements Serializable {

    //注解开关
    @Value("${Constants.SWAGGER}")
    private Boolean SWAGGER;

    //描述
    @Value("${Constants.MESSAGE}")
    private String MESSAGE;

    //状态
    @Value("${Constants.STATUS}")
    private String STATUS;

    //管理员TOKEN
    @Value("${Constants.ACCESS_TOKEN}")
    private String ACCESS_TOKEN;

    //管理员信息
    @Value("${Constants.ADMIN_INFO}")
    public String ADMIN_INFO;

    //用户信息
    @Value("${Constants.USER_INFO}")
    private String USER_INFO;

    //用户TOKEN
    @Value("${Constants.USER_ACCESS_TOKEN}")
    private String USER_ACCESS_TOKEN;

    //存储地址
    @Value("${Constants.FDFS_URL}")
    private String FDFS_URL;

    //数据
    @Value("${Constants.DATA}")
    private String DATA;

    //列表
    @Value("${Constants.ITEM}")
    private String ITEM;

    //管理员登录失效时间
    @Value("${Constants.ADMIN_EXSPIRETIME}")
    public Long ADMIN_EXSPIRETIME;

    //用戶登录失效时间
    @Value("${Constants.USER_EXSPIRETIME}")
    public Long USER_EXSPIRETIME;

    //总数
    @Value("${Constants.COUNT}")
    public String COUNT;

}
