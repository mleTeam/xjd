package com.dx.xjd.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 风控三方接口请求响应记录表
 * </p>
 *
 * @author xc
 * @since 2018-10-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class RiskInterfaceLog implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 接口类型
     */
    @TableField("type")
    private Integer type;

    /**
     * 接口名
     */
    @TableField("typeName")
    private String typeName;

    /**
     * 请求参数
     */
    @TableField("requestBody")
    private String requestBody;

    /**
     * 响应参数
     */
    @TableField("responseBody")
    private String responseBody;

    @TableField("addtime")
    private LocalDateTime addtime;

    @TableField("updatetime")
    private LocalDateTime updatetime;

    /**
     * 认证订单id
     */
    @TableField("identificateId")
    private Integer identificateId;

    /**
     * 用户id
     */
    @TableField("userId")
    private Integer userId;


}
