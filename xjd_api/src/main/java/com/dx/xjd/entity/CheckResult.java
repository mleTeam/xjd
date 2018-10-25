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
public class CheckResult implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 订单id
     */
    @TableId("orderId")
    private Integer orderId;

    /**
     * 用户id
     */
    @TableField("userId")
    private Integer userId;

    /**
     * 用户名称
     */
    @TableField("userName")
    private String userName;

    /**
     * 手机号码
     */
    @TableField("cellphone")
    private String cellphone;

    /**
     * 电核客户确认本人办理 -1:未核 0:否 1:是
     */
    @TableField("isTransactByOwn")
    private Integer isTransactByOwn;

    /**
     * 客户支付宝是否实名 -1:未核 0:否 1:是
     */
    @TableField("alipayIsRealName")
    private Integer alipayIsRealName;

    /**
     * 了解渠道 -1:未核 1:朋友推荐 2:网上查询  3:应用软件推荐 4 :商户 5:其他渠道
     */
    @TableField("knowChannel")
    private Integer knowChannel;

    /**
     * 渠道备注
     */
    @TableField("channelRemark")
    private String channelRemark;

    /**
     * 电核学历信息 -1:未核 1:专科 2:高技 3:全日制大专4 :全日制自考5:全日制本科 6:211本科7:985本科 8研究生及以上
     */
    @TableField("educationMessage")
    private Integer educationMessage;

    /**
     * 电核信息备注
     */
    @TableField("educationMessageRemark")
    private String educationMessageRemark;

    /**
     * 电核父亲核实身份 -1:未核 0:否 1:是 2:存疑 3:未打
     */
    @TableField("isRealFather")
    private Integer isRealFather;

    /**
     * 电核母亲核实身份 -1:未核 0:否 1:是 2:存疑 3:未打
     */
    @TableField("isRealMother")
    private Integer isRealMother;

    /**
     * 父亲通讯工具/软件可查 -1:未核 0:否 1:是
     */
    @TableField("fatherConnectToolIsCheck")
    private Integer fatherConnectToolIsCheck;

    /**
     * 父亲通讯备注
     */
    @TableField("fatherConnectToolRemark")
    private String fatherConnectToolRemark;

    /**
     * 母亲通讯工具/软件可查 -1:未核 0:否 1:是
     */
    @TableField("motherConnectToolIsCheck")
    private Integer motherConnectToolIsCheck;

    /**
     * 母亲通讯备注
     */
    @TableField("motherConnectToolRemark")
    private String motherConnectToolRemark;

    /**
     * 最大期数
     */
    @TableField("maxTerm")
    private Integer maxTerm;

    /**
     * 备注
     */
    @TableField("remark")
    private String remark;

    /**
     * 否单原因选择 1.资料虚假 2.联系人电话无法接通 3.多头借贷 4.客户电话无效 5.业务代办  6.同业黑名单可查  7.不是本人办理  8.不符合业务办理硬性条件
     */
    @TableField("failReason")
    private String failReason;

    /**
     * 审核状态 7:通过  9:否单  5:驳回
     */
    @TableField("state")
    private Integer state;

    /**
     * 添加时间
     */
    @TableField("addTime")
    private LocalDateTime addTime;

    /**
     * 更新时间
     */
    @TableField("updateTime")
    private LocalDateTime updateTime;


}
