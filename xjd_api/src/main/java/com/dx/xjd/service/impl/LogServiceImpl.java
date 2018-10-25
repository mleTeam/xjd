package com.dx.xjd.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dx.xjd.entity.Log;
import com.dx.xjd.mapper.LogMapper;
import com.dx.xjd.para.LogParam;
import com.dx.xjd.service.ILogService;
import com.dx.xjd.util.ObjectUtils;
import com.dx.xjd.util.WrapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 日志表 服务实现类
 * </p>
 *
 * @author XC
 * @since 2018-07-26
 */
@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, Log> implements ILogService {

    @Autowired
    private LogMapper logMapper;

    /**
     * 根据请求参数查询列表
     * @param logParam
     * @return
     */
    public List<Log> listByParam(LogParam logParam){
        QueryWrapper<Log> wrapper = getWrapper(logParam);
        WrapperUtil.setOrder(wrapper, logParam, "log_id");
        Page<Log> page = WrapperUtil.getPage(logParam);
        if (ObjectUtils.isNotEmpty(page)) {
            return logMapper.selectPage(page, wrapper).getRecords();
        } else {
            return logMapper.selectList(wrapper);
        }
    }

    /**
     * 根据请求参数查询总数
     * @param logParam
     * @return
     */
    public Integer countByParam(LogParam logParam){
        QueryWrapper<Log> wrapper = getWrapper(logParam);
        return logMapper.selectCount(wrapper);
    }

    /**
     * 自定义查询参数
     * @param param
     * @return
     */
    private QueryWrapper<Log> getWrapper(LogParam param){
        QueryWrapper<Log> wrapper = new QueryWrapper<Log>();

        return wrapper;
    }
}
