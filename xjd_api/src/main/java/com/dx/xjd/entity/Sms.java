package com.dx.xjd.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 短信发送表
 * </p>
 *
 * @author xc
 * @since 2018-10-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Sms implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "sms_id", type = IdType.AUTO)
    private Integer smsId;

    /**
     * 短信内容
     */
    @TableField("sms_content")
    private String smsContent;

    /**
     * 短信创建时间
     */
    @TableField("sms_createTime")
    private LocalDateTime smsCreatetime;

    /**
     * 短信接收者
     */
    @TableField("sms_phone")
    private String smsPhone;

    /**
     * 状态  0成功  1失败
     */
    @TableField("sms_state")
    private Integer smsState;

    /**
     * 短信通道
     */
    @TableField("sms_type_id")
    private Integer smsTypeId;

    /**
     * 短信发送时间
     */
    @TableField("sms_sendTime")
    private LocalDateTime smsSendtime;


}
