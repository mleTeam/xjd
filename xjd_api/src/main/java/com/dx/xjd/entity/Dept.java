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
 * 
 * </p>
 *
 * @author xc
 * @since 2018-10-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Dept implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 部门id
     */
    @TableId(value = "dept_id", type = IdType.AUTO)
    private Integer deptId;

    /**
     * 部门名称
     */
    @TableField("dept_name")
    private String deptName;

    /**
     * 描述
     */
    @TableField("dept_desc")
    private String deptDesc;

    /**
     * 父节点id  0无
     */
    @TableField("dept_parent_id")
    private Integer deptParentId;

    /**
     * 创建时间
     */
    @TableField("dept_create_time")
    private LocalDateTime deptCreateTime;

    /**
     * 修改时间
     */
    @TableField("dept_update_time")
    private LocalDateTime deptUpdateTime;


}
