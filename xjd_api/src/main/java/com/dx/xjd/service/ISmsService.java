package com.dx.xjd.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dx.xjd.entity.Sms;
import com.dx.xjd.para.SmsParam;

import java.util.List;

/**
 * <p>
 * 短信发送表 服务类
 * </p>
 *
 * @author XC
 * @since 2018-07-26
 */
public interface ISmsService extends IService<Sms> {
    /**
     * 根据请求参数查询列表
     * @param smsParam
     * @return
     */
    List<Sms> listByParam(SmsParam smsParam);

    /**
     * 根据请求参数查询总数
     * @param smsParam
     * @return
     */
    Integer countByParam(SmsParam smsParam);
}
