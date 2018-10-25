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
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户注册手机号码
     */
    @TableField("cellphone")
    private String cellphone;

    /**
     * 姓名
     */
    @TableField("name")
    private String name;

    @TableField("IDNumber")
    private String IDNumber;

    @TableField("cookie")
    private String cookie;

    @TableField("transactionPassword")
    private String transactionPassword;

    @TableField("headImgUrl")
    private String headImgUrl;

    /**
     * 0:为认证 1:认证通过 2:认证不通过
     */
    @TableField("hasIdentified")
    private Boolean hasIdentified;

    @TableField("identiTime")
    private LocalDateTime identiTime;

    @TableField("passTime")
    private LocalDateTime passTime;

    @TableField("limitMoney")
    private Integer limitMoney;

    @TableField("availableMoney")
    private Float availableMoney;

    @TableField("password")
    private String password;

    @TableField("registerTime")
    private LocalDateTime registerTime;

    /**
     * 预留字段，用户推荐码
     */
    @TableField("inviteCode")
    private Integer inviteCode;

    @TableField("type")
    private Integer type;

    /**
     * 机器码
     */
    @TableField("token")
    private String token;

    /**
     * 区分ios、andriod、h5
     */
    @TableField("platform")
    private String platform;

    /**
     * 手机系统
     */
    @TableField("os_name")
    private String osName;

    /**
     * 系统版本
     */
    @TableField("os_version")
    private String osVersion;

    /**
     * 注册时提交ip
     */
    @TableField("ip")
    private String ip;

    /**
     * 注册时ip对应的城市
     */
    @TableField("ipCity")
    private String ipCity;

    @TableField("loanCount")
    private Integer loanCount;

    /**
     * 0:正常  1：冻结
     */
    @TableField("isForbidMoney")
    private Boolean isForbidMoney;

    /**
     * 引流平台id
     */
    @TableField("platformId")
    private Integer platformId;


}
