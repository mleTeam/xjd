package com.dx.xjd.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 导流平台显示权限
 * </p>
 *
 * @author xc
 * @since 2018-10-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class DaoliuPlatformLimit implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("platformId")
    private Integer platformId;

    /**
     * 是否显示点击量0否1是
     */
    @TableField("clickCountLimit")
    private Boolean clickCountLimit;

    /**
     * 是否显示注册量0否1是
     */
    @TableField("registerCountLimit")
    private Boolean registerCountLimit;

    /**
     * 是否显示认证量0否1是
     */
    @TableField("applyCountLimit")
    private Boolean applyCountLimit;

    /**
     * 是否显示认证成功量0否1是
     */
    @TableField("applySuccessCountLimit")
    private Boolean applySuccessCountLimit;

    /**
     * 是否显示下单量0否1是
     */
    @TableField("orderCountLimit")
    private Boolean orderCountLimit;

    /**
     * 是否显示下单金额0否1是
     */
    @TableField("orderMoneyLimit")
    private Boolean orderMoneyLimit;

    /**
     * 是否显示注册认证转化率0否1是
     */
    @TableField("applyRateLimit")
    private Boolean applyRateLimit;

    /**
     * 是否显示认证成功转化率0否1是
     */
    @TableField("applySuccessRateLimit")
    private Boolean applySuccessRateLimit;

    /**
     * 是否显示下单转化率0否1是
     */
    @TableField("orderRateLimit")
    private Boolean orderRateLimit;


}
