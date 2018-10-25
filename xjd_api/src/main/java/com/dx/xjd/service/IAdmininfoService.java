package com.dx.xjd.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dx.xjd.entity.Admininfo;
import com.dx.xjd.entity.vo.AdmininfoVo;
import com.dx.xjd.para.AdmininfoParam;

import java.util.List;

/**
 * <p>
 * 管理员表 服务类
 * </p>
 *
 * @author XC
 * @since 2018-07-26
 */
public interface IAdmininfoService extends IService<Admininfo> {

    /**
     * 登录
     * @param admininfo
     * @return
     */
    Admininfo login(Admininfo admininfo);

    /**
     * 根据请求参数查询列表
     * @param admininfoParam
     * @return
     */
    List<AdmininfoVo> listByParam(AdmininfoParam admininfoParam);

    /**
     * 根据请求参数查询总数
     * @param admininfoParam
     * @return
     */
    Integer countByParam(AdmininfoParam admininfoParam);
}
