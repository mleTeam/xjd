package com.dx.xjd.entity;

import com.baomidou.mybatisplus.annotation.TableField;
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
public class BankCard implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("preBankCardId")
    private Integer preBankCardId;

    @TableField("userId")
    private Integer userId;

    @TableField("accNo")
    private String accNo;

    @TableField("mobile")
    private String mobile;

    @TableField("payCode")
    private String payCode;

    @TableField("singleAmount")
    private Float singleAmount;

    @TableField("dayAmount")
    private Float dayAmount;

    @TableField("validDate")
    private String validDate;

    @TableField("validNo")
    private String validNo;

    @TableField("additionalInfo")
    private String additionalInfo;

    /**
     * 是否绑卡0否1是
     */
    @TableField("state")
    private Integer state;

    @TableField("bankName")
    private String bankName;

    /**
     * 银行地址
     */
    @TableField("bankAddr")
    private String bankAddr;

    /**
     * 连连付签约协议号
     */
    @TableField("agreeNo")
    private String agreeNo;

    /**
     * 卡类型2借记卡3信用卡
     */
    @TableField("cardType")
    private String cardType;

    @TableField("preBindTime")
    private LocalDateTime preBindTime;

    @TableField("bindTime")
    private LocalDateTime bindTime;


}
