package com.dx.xjd.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 预支付信息表
 * </p>
 *
 * @author xc
 * @since 2018-10-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class PreRepayOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 账单id
     */
    @TableField("orderId")
    private Integer orderId;

    /**
     * 用户id
     */
    @TableField("userId")
    private Integer userId;

    /**
     * 发给连连支付的唯一标志
     */
    @TableField("lianlianOrderNum")
    private String lianlianOrderNum;

    /**
     * 处理状态0未处理1已处理2失败
     */
    @TableField("state")
    private Integer state;

    @TableField("addtime")
    private LocalDateTime addtime;

    @TableField("confirmTime")
    private LocalDateTime confirmTime;

    /**
     * 错误信息
     */
    @TableField("errorMsg")
    private String errorMsg;

    @TableField("money")
    private Float money;

    @TableField("interestOfDemurrage")
    private Float interestOfDemurrage;

    @TableField("penal")
    private Float penal;

    @TableField("lateFee")
    private Float lateFee;

    /**
     * 调用查询次数
     */
    @TableField("invokeCount")
    private Integer invokeCount;

    /**
     * 支付类型0主动支付1代扣
     */
    @TableField("repayType")
    private Boolean repayType;

    /**
     * 还款银行卡id
     */
    @TableField("repaymentBankCardId")
    private Integer repaymentBankCardId;


}
