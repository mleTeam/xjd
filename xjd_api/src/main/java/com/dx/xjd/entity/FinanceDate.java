package com.dx.xjd.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDate;

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
public class FinanceDate implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("date")
    private LocalDate date;

    @TableField("money")
    private Float money;

    @TableField("primeCost")
    private Float primeCost;

    @TableField("interest")
    private Float interest;

    @TableField("serviceFee")
    private Float serviceFee;

    @TableField("loan_primeCost")
    private Float loanPrimecost;

    @TableField("loan_interest")
    private Float loanInterest;

    @TableField("loan_serviceFee")
    private Float loanServicefee;

    @TableField("loan_money")
    private Float loanMoney;

    @TableField("receive_primeCost")
    private Float receivePrimecost;

    @TableField("receive_interest")
    private Float receiveInterest;

    @TableField("receive_serviceFee")
    private Float receiveServicefee;

    @TableField("receive_penal")
    private Float receivePenal;

    @TableField("receive_interestOfDemurrage")
    private Float receiveInterestofdemurrage;

    @TableField("receive_money")
    private Float receiveMoney;

    @TableField("yuqi_primeCost")
    private Float yuqiPrimecost;

    @TableField("yuqi_interest")
    private Float yuqiInterest;

    @TableField("yuqi_serviceFee")
    private Float yuqiServicefee;

    @TableField("yuqi_money")
    private Float yuqiMoney;

    @TableField("cur_yuqi_primeCost")
    private Float curYuqiPrimecost;

    @TableField("cur_yuqi_serviceFee")
    private Float curYuqiServicefee;

    @TableField("cur_yuqi_interest")
    private Float curYuqiInterest;

    @TableField("cur_yuqi_money")
    private Float curYuqiMoney;


}
