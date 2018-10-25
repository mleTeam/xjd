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
 * 城市表
 * </p>
 *
 * @author xc
 * @since 2018-10-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class City implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "city_id", type = IdType.AUTO)
    private Integer cityId;

    /**
     * 城市名称
     */
    @TableField("city_name")
    private String cityName;

    /**
     * 父id
     */
    @TableField("city_parentId")
    private Integer cityParentid;

    /**
     * 创建时间
     */
    @TableField("city_createTime")
    private LocalDateTime cityCreatetime;

    /**
     * 城市编号
     */
    @TableField("city_no")
    private String cityNo;


}
