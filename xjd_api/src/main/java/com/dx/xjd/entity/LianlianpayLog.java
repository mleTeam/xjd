package com.dx.xjd.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 连连付接口日志
 * </p>
 *
 * @author xc
 * @since 2018-10-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class LianlianpayLog implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 接口名
     */
    @TableField("interfaceName")
    private String interfaceName;

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

    @TableField("userId")
    private Integer userId;


}
