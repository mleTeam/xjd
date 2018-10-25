package com.dx.xjd.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 管理组
 * </p>
 *
 * @author xc
 * @since 2018-10-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("actionGroup")
public class ActionGroup implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "group_id", type = IdType.AUTO)
    private Integer groupId;

    /**
     * 管理组名称
     */
    @TableField("group_name")
    private String groupName;

    /**
     * 管理组描述
     */
    @TableField("group_info")
    private String groupInfo;

    /**
     * 创建时间
     */
    @TableField("createTime")
    private LocalDateTime createTime;

    /**
     * 选中的权限
     */
    @TableField("group_check_action")
    private String groupCheckAction;

    /**
     * 半选中的权限
     */
    @TableField("group_half_check_action")
    private String groupHalfCheckAction;


}
