package com.dx.xjd.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 导流平台确认数据
 * </p>
 *
 * @author xc
 * @since 2018-10-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class DaoliuPlatformConfirmData implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("platformId")
    private Integer platformId;

    /**
     * 日期字符串形式
     */
    @TableField("countDate")
    private String countDate;

    @TableField("addtime")
    private LocalDateTime addtime;

    /**
     * 点击量
     */
    @TableField("clickCount")
    private Integer clickCount;

    /**
     * 注册量
     */
    @TableField("registerCount")
    private Integer registerCount;

    /**
     * 认证量
     */
    @TableField("applyCount")
    private Integer applyCount;

    /**
     * 认证成功量
     */
    @TableField("applySuccessCount")
    private Integer applySuccessCount;

    /**
     * 下单量
     */
    @TableField("orderCount")
    private Integer orderCount;

    /**
     * 下单金额
     */
    @TableField("orderMoney")
    private Float orderMoney;

    /**
     * 认证转化率
     */
    @TableField("applyRate")
    private Float applyRate;

    /**
     * 认证成功转化率
     */
    @TableField("applySuccessRate")
    private Float applySuccessRate;

    /**
     * 下单转化率
     */
    @TableField("orderRate")
    private Float orderRate;


}
