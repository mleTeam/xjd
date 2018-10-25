package com.dx.xjd.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author xc
 * @since 2018-10-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class CreditOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("userId")
    private Integer userId;

    @TableField("name")
    private String name;

    @TableField("cellphone")
    private String cellphone;

    /**
     * 0:秒到来源
     */
    @TableField("type")
    private Boolean type;

    /**
     * 提现金额
     */
    @TableField("money")
    private Float money;

    @TableField("primeCost")
    private Float primeCost;

    @TableField("serviceFee")
    private Float serviceFee;

    @TableField("interest")
    private Float interest;

    @TableField("loanDay")
    private Integer loanDay;

    @TableField("orderNum")
    private String orderNum;

    @TableField("bankCardId")
    private Integer bankCardId;

    @TableField("interestRatio")
    private Float interestRatio;

    @TableField("serviceRaio")
    private Float serviceRaio;

    @TableField("orderTime")
    private LocalDateTime orderTime;

    @TableField("loanState")
    private Boolean loanState;

    @TableField("passTime")
    private LocalDateTime passTime;

    @TableField("latestPaymentDate")
    private LocalDate latestPaymentDate;

    @TableField("repaymentState")
    private Boolean repaymentState;

    @TableField("paymentDate")
    private LocalDateTime paymentDate;

    @TableField("lataFee")
    private Float lataFee;

    @TableField("penal")
    private Float penal;

    @TableField("interestOfDemurrage")
    private Float interestOfDemurrage;

    @TableField("repaymentBankCardId")
    private Integer repaymentBankCardId;

    /**
     * 预下单Id
     */
    @TableField("preOrderId")
    private Integer preOrderId;

    /**
     * 借款失败状态
     */
    @TableField("loanFailedState")
    private Integer loanFailedState;

    /**
     * 借款返回码
     */
    @TableField("loanRetCode")
    private String loanRetCode;

    /**
     * 借款返回信息
     */
    @TableField("loanRetMsg")
    private String loanRetMsg;

    /**
     * 疑似重复订单确认码
     */
    @TableField("confirmCode")
    private String confirmCode;

    /**
     * 借款申请时间
     */
    @TableField("loanApplyTime")
    private LocalDateTime loanApplyTime;

    /**
     * 连连付银行卡绑定协议号
     */
    @TableField("agreeNo")
    private String agreeNo;

    /**
     * 还款方式0主动还款1代扣
     */
    @TableField("repayType")
    private Boolean repayType;

    /**
     * 还款计划唯一标志
     */
    @TableField("repaymentNo")
    private String repaymentNo;


}
