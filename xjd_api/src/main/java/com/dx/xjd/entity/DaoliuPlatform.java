package com.dx.xjd.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 导流平台信息
 * </p>
 *
 * @author xc
 * @since 2018-10-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class DaoliuPlatform implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 帐号
     */
    @TableField("account")
    private String account;

    /**
     * 密码
     */
    @TableField("password")
    private String password;

    /**
     * 平台名
     */
    @TableField("platformName")
    private String platformName;

    /**
     * 对接人姓名
     */
    @TableField("contactName")
    private String contactName;

    /**
     * 联系方式
     */
    @TableField("contactPhone")
    private String contactPhone;

    /**
     * 结算方式
     */
    @TableField("settleType")
    private String settleType;

    /**
     * 合作模式
     */
    @TableField("coopMode")
    private String coopMode;

    /**
     * 价格
     */
    @TableField("price")
    private String price;

    /**
     * 状态0下线1上线
     */
    @TableField("state")
    private Integer state;

    /**
     * 链接
     */
    @TableField("link")
    private String link;

    @TableField("addtime")
    private LocalDateTime addtime;

    /**
     * 上线时间
     */
    @TableField("onlineTime")
    private LocalDateTime onlineTime;

    /**
     * 下线时间
     */
    @TableField("offlineTime")
    private LocalDateTime offlineTime;

    /**
     * 是否删除0否1是
     */
    @TableField("isDelete")
    private Integer isDelete;


}
