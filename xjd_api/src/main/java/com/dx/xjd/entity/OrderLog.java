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
public class OrderLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("orderId")
    private Integer orderId;

    /**
     * 0:提交认证
1:商品订单
2:白条订单
     */
    @TableField("orderType")
    private Integer orderType;

    @TableField("operateTime")
    private LocalDateTime operateTime;

    @TableField("operateManagerId")
    private Integer operateManagerId;

    @TableField("operateManagerName")
    private String operateManagerName;

    @TableField("remark")
    private String remark;


}
