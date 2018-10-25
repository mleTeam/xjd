package com.dx.xjd.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
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
public class UrgeOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 账单id
     */
    @TableId("orderId")
    private Integer orderId;

    @TableField("orderNum")
    private String orderNum;

    /**
     * 用户id
     */
    @TableField("userId")
    private Integer userId;

    /**
     * 用户名称
     */
    @TableField("name")
    private String name;

    /**
     * 用户号码
     */
    @TableField("cellphone")
    private String cellphone;

    /**
     * 金额
     */
    @TableField("money")
    private Float money;

    /**
     * 应还本金
     */
    @TableField("primeCost")
    private Float primeCost;

    /**
     * 服务费
     */
    @TableField("serviceFee")
    private Float serviceFee;

    /**
     * 利息
     */
    @TableField("interest")
    private Float interest;

    /**
     * 滞纳金
     */
    @TableField("lateFee")
    private Float lateFee;

    @TableField("penal")
    private Float penal;

    @TableField("interestOfDemurrage")
    private Float interestOfDemurrage;

    /**
     * 角色id
     */
    @TableField("managerId")
    private Integer managerId;

    /**
     * 角色名称
     */
    @TableField("managerName")
    private String managerName;

    /**
     * 角色类型
     */
    @TableField("type")
    private Integer type;

    /**
     * 外包角色id
     */
    @TableField("outManagerId")
    private Integer outManagerId;

    /**
     * 外包角色名称
     */
    @TableField("outManagerName")
    private String outManagerName;

    /**
     * 0:正常  1:锁定  默认正常
     */
    @TableField("isLock")
    private Integer isLock;

    /**
     * 锁定者
     */
    @TableField("lockPerson")
    private String lockPerson;

    /**
     * 应还时间
     */
    @TableField("latestPaymentDate")
    private LocalDateTime latestPaymentDate;

    /**
     * 还款时间
     */
    @TableField("paymentDate")
    private LocalDateTime paymentDate;

    /**
     * 锁定时间
     */
    @TableField("lockTime")
    private LocalDateTime lockTime;

    /**
     * 添加时间
     */
    @TableField("addTime")
    private LocalDateTime addTime;

    /**
     * 分配给外包时间
     */
    @TableField("outAddTime")
    private LocalDateTime outAddTime;

    /**
     * 异常时间
     */
    @TableField("exceptionTime")
    private LocalDateTime exceptionTime;

    /**
     * 0:未还款 1:异常单 2:坏账 3:已还款 4:已失效 5:外包确认还款  6:财务确认还款 默认未还款
     */
    @TableField("state")
    private Integer state;

    @TableField("loanDay")
    private Integer loanDay;

    @TableField("orderTime")
    private LocalDateTime orderTime;


}
