package com.dx.xjd.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dx.xjd.entity.ActionGroup;
import com.dx.xjd.para.ActionGroupParam;

import java.util.List;

/**
 * <p>
 * 管理组 服务类
 * </p>
 *
 * @author XC
 * @since 2018-07-26
 */
public interface IActionGroupService extends IService<ActionGroup> {

    /**
     * 根据请求参数查询列表
     * @param groupParam
     * @return
     */
    List<ActionGroup> listByParam(ActionGroupParam groupParam);

    /**
     * 根据请求参数查询总数
     * @param groupParam
     * @return
     */
    Integer countByParam(ActionGroupParam groupParam);
}
