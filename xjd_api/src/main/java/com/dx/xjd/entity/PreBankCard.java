package com.dx.xjd.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

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
public class PreBankCard implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("userId")
    private Integer userId;

    @TableField("IDCardNumber")
    private String IDCardNumber;

    @TableField("realName")
    private String realName;

    @TableField("accNo")
    private String accNo;

    /**
     * 预绑卡订单号
     */
    @TableField("preOrderNo")
    private String preOrderNo;

    @TableField("mobile")
    private String mobile;

    @TableField("payCode")
    private String payCode;

    /**
     * 确认绑卡订单号
     */
    @TableField("confirmOrderNo")
    private String confirmOrderNo;

    @TableField("preCode")
    private String preCode;

    @TableField("preMessage")
    private String preMessage;

    @TableField("confirmCode")
    private String confirmCode;

    @TableField("confirmMessage")
    private String confirmMessage;

    @TableField("validDate")
    private String validDate;

    @TableField("validNo")
    private String validNo;

    @TableField("additionalInfo")
    private String additionalInfo;

    @TableField("resultId")
    private String resultId;


}
