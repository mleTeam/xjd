package com.dx.xjd.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dx.xjd.entity.Sms;
import com.dx.xjd.mapper.SmsMapper;
import com.dx.xjd.para.SmsParam;
import com.dx.xjd.service.ISmsService;
import com.dx.xjd.util.ObjectUtils;
import com.dx.xjd.util.WrapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 短信发送表 服务实现类
 * </p>
 *
 * @author XC
 * @since 2018-07-26
 */
@Service
public class SmsServiceImpl extends ServiceImpl<SmsMapper, Sms> implements ISmsService {

    @Autowired
    private SmsMapper smsMapper;

    /**
     * 根据请求参数查询列表
     * @param smsParam
     * @return
     */
    public List<Sms> listByParam(SmsParam smsParam){
        QueryWrapper<Sms> wrapper = getWrapper(smsParam);
        WrapperUtil.setOrder(wrapper, smsParam, "sms_id");
        Page<Sms> page = WrapperUtil.getPage(smsParam);
        if (ObjectUtils.isNotEmpty(page)) {
            return smsMapper.selectPage(page, wrapper).getRecords();
        } else {
            return smsMapper.selectList(wrapper);
        }
    }

    /**
     * 根据请求参数查询总数
     * @param smsParam
     * @return
     */
    public Integer countByParam(SmsParam smsParam){
        QueryWrapper<Sms> wrapper = getWrapper(smsParam);
        return smsMapper.selectCount(wrapper);
    }

    /**
     * 自定义查询参数
     * @param param
     * @return
     */
    private QueryWrapper<Sms> getWrapper(SmsParam param){
        QueryWrapper<Sms> wrapper = new QueryWrapper<Sms>();

        return wrapper;
    }
}
