package com.dx.xjd.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 导流平台点击量
 * </p>
 *
 * @author xc
 * @since 2018-10-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class DaoliuPlatformClick implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("platformId")
    private Integer platformId;

    /**
     * 日期字符串形式
     */
    @TableField("countDate")
    private String countDate;

    @TableField("addtime")
    private LocalDateTime addtime;

    /**
     * 点击量
     */
    @TableField("clickCount")
    private Integer clickCount;


}
