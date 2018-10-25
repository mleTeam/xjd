package com.dx.xjd.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

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
public class Version implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("downLoadUrl")
    private String downLoadUrl;

    @TableField("description")
    private String description;

    @TableField("isForceUpdate")
    private Integer isForceUpdate;

    @TableField("type")
    private Integer type;

    @TableField("currentVersion")
    private Integer currentVersion;

    @TableField("remark")
    private String remark;


}
