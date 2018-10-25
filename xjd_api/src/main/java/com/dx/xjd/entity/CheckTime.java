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
public class CheckTime implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 审核者id
     */
    @TableField("checkManagerId")
    private Integer checkManagerId;

    /**
     * 审核者名称
     */
    @TableField("checkManagerName")
    private String checkManagerName;

    /**
     * 订单id
     */
    @TableField("orderId")
    private Integer orderId;

    /**
     * 审核状态  -1:开始接单 11:挂起  7:成功 6:否单 5:驳回  -2:发回池里
     */
    @TableField("checkState")
    private Integer checkState;

    /**
     * 审核开始时间
     */
    @TableField("startCheckTime")
    private LocalDateTime startCheckTime;

    /**
     * 审核结束时间
     */
    @TableField("endCheckTime")
    private LocalDateTime endCheckTime;

    /**
     * 审核总时间
     */
    @TableField("totalCheckTime")
    private String totalCheckTime;


}
