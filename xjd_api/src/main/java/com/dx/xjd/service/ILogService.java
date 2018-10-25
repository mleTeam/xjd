package com.dx.xjd.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dx.xjd.entity.Log;
import com.dx.xjd.para.LogParam;

import java.util.List;

/**
 * <p>
 * 日志表 服务类
 * </p>
 *
 * @author XC
 * @since 2018-07-26
 */
public interface ILogService extends IService<Log> {
    /**
     * 根据请求参数查询列表
     * @param logParam
     * @return
     */
    List<Log> listByParam(LogParam logParam);

    /**
     * 根据请求参数查询总数
     * @param logParam
     * @return
     */
    Integer countByParam(LogParam logParam);
}
