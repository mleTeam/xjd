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
public class Article implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("cid")
    private Integer cid;

    @TableField("title")
    private String title;

    @TableField("addtime")
    private Integer addtime;

    @TableField("cont")
    private String cont;

    @TableField("keywords")
    private String keywords;

    @TableField("description")
    private String description;

    @TableField("sort")
    private Integer sort;

    @TableField("thumbnail")
    private String thumbnail;


}
