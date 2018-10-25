package com.dx.xjd.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dx.xjd.entity.City;
import com.dx.xjd.para.CityParam;

import java.util.List;

/**
 * <p>
 * 城市表 服务类
 * </p>
 *
 * @author XC
 * @since 2018-07-26
 */
public interface ICityService extends IService<City> {

    /**
     * 根据请求参数查询列表
     * @param cityParam
     * @return
     */
    List<City> listByParam(CityParam cityParam);

    /**
     * 根据请求参数查询总数
     * @param cityParam
     * @return
     */
    Integer countByParam(CityParam cityParam);
}
