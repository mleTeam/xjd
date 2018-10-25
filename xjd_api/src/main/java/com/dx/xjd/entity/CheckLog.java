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
public class CheckLog implements Serializable {

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
     * 审核状态  -1:开始接单 12:挂起 3:继续审核 7:成功 6:否单 5:驳回  -2:发回池里
     */
    @TableField("checkState")
    private Integer checkState;

    /**
     * 审核时间
     */
    @TableField("checkTime")
    private LocalDateTime checkTime;

    /**
     * 备注
     */
    @TableField("remark")
    private String remark;


}
