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
public class Feedback implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("phone")
    private String phone;

    @TableField("content")
    private String content;

    @TableField("submitTime")
    private LocalDateTime submitTime;


}
