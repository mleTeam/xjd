package com.dx.xjd.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 关键字出现次数
 * </p>
 *
 * @author xc
 * @since 2018-10-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class KeywordCount implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 手机号或名称
     */
    @TableField("keyword")
    private String keyword;

    /**
     * 出现次数
     */
    @TableField("kwCount")
    private Integer kwCount;

    /**
     * 订单id，多个用‘,’隔开
     */
    @TableField("orderIds")
    private String orderIds;

    /**
     * 类型：0:手机号 1:名称
     */
    @TableField("type")
    private Integer type;


}
