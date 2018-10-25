package com.dx.xjd.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dx.xjd.entity.Action;
import com.dx.xjd.para.ActionParam;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 功能模块表 服务类
 * </p>
 *
 * @author XC
 * @since 2018-07-26
 */
public interface IActionService extends IService<Action> {

    /**
     * 根据请求参数查询列表
     * @param actionParam
     * @return
     */
    List<Action> listByParam(ActionParam actionParam);

    /**
     * 根据请求参数查询总数
     * @param actionParam
     * @return
     */
    Integer countByParam(ActionParam actionParam);

    /**
     * 获取管理员的功能模块
     * @param adminId
     * @return
     */
    List<Action> listActionByAdminId(Integer adminId);

}

