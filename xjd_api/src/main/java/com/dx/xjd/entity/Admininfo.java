package com.dx.xjd.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 管理员表
 * </p>
 *
 * @author xc
 * @since 2018-10-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Admininfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "admin_id", type = IdType.AUTO)
    private Integer adminId;

    /**
     * 管理员帐号
     */
    @TableField("admin_name")
    private String adminName;

    /**
     * 管理员密码
     */
    @TableField("admin_pwd")
    private String adminPwd;

    /**
     * 创建时间
     */
    @TableField("admin_createTime")
    private LocalDateTime adminCreatetime;

    /**
     * 最后登录时间
     */
    @TableField("admin_lastLoginTime")
    private LocalDateTime adminLastlogintime;

    /**
     * 描述
     */
    @TableField("admin_desc")
    private String adminDesc;

    /**
     * 管理员级别
     */
    @TableField("admin_level")
    private Integer adminLevel;

    /**
     * 部门id
     */
    @TableField("dept_id")
    private Integer deptId;

    /**
     * 用户id
     */
    @TableField("user_id")
    private Integer userId;

    /**
     * 角色组
     */
    @TableField("group_id")
    private String groupId;


}
