package com.dx.xjd.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 功能模块表
 * </p>
 *
 * @author xc
 * @since 2018-10-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Action implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "action_id", type = IdType.AUTO)
    private Integer actionId;

    /**
     * 功能名称
     */
    @TableField("action_name")
    private String actionName;

    /**
     * 功能URL
     */
    @TableField("action_url")
    private String actionUrl;

    /**
     * 左侧导航栏是否可见1可见2不可见
     */
    @TableField("action_viewMode")
    private Integer actionViewmode;

    /**
     * 父类 0为根类
     */
    @TableField("action_parentId")
    private Integer actionParentid;

    /**
     * 是否需要session 1否 2是
     */
    @TableField("action_isSession")
    private Integer actionIssession;

    /**
     * 是否按钮 1否 2是
     */
    @TableField("action_isButton")
    private Integer actionIsbutton;

    /**
     * 排序
     */
    @TableField("action_sort")
    private Integer actionSort;

    /**
     * 权限
     */
    @TableField("action_rule")
    private String actionRule;


}
