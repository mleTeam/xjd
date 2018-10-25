package com.dx.xjd.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dx.xjd.entity.Dept;
import com.dx.xjd.para.DeptParam;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author XC
 * @since 2018-07-26
 */
public interface IDeptService extends IService<Dept> {

    /**
     * 根据请求参数查询列表
     * @param deptParam
     * @return
     */
    List<Dept> listByParam(DeptParam deptParam);

    /**
     * 根据请求参数查询总数
     * @param deptParam
     * @return
     */
    Integer countByParam(DeptParam deptParam);
}
