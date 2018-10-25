package com.dx.xjd.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dx.xjd.entity.City;
import com.dx.xjd.mapper.CityMapper;
import com.dx.xjd.para.CityParam;
import com.dx.xjd.service.ICityService;
import com.dx.xjd.util.ObjectUtils;
import com.dx.xjd.util.WrapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 城市表 服务实现类
 * </p>
 *
 * @author XC
 * @since 2018-07-26
 */
@Service
public class CityServiceImpl extends ServiceImpl<CityMapper, City> implements ICityService {

    @Autowired
    private CityMapper cityMapper;

    /**
     * 根据请求参数查询列表
     * @param cityParam
     * @return
     */
    public List<City> listByParam(CityParam cityParam){
        QueryWrapper<City> wrapper = getWrapper(cityParam);
        WrapperUtil.setOrder(wrapper, cityParam, "city_id");
        Page<City> page = WrapperUtil.getPage(cityParam);
        if (ObjectUtils.isNotEmpty(page)) {
            return cityMapper.selectPage(page, wrapper).getRecords();
        } else {
            return cityMapper.selectList(wrapper);
        }
    }

    /**
     * 根据请求参数查询总数
     * @param cityParam
     * @return
     */
    public Integer countByParam(CityParam cityParam){
        QueryWrapper<City> wrapper = getWrapper(cityParam);
        return cityMapper.selectCount(wrapper);
    }

    /**
     * 自定义查询参数
     * @param param
     * @return
     */
    private QueryWrapper<City> getWrapper(CityParam param){
        QueryWrapper<City> wrapper = new QueryWrapper<City>();

        return wrapper;
    }
}
