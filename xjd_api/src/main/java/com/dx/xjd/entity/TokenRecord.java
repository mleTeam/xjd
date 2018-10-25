package com.dx.xjd.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 设备号token记录
 * </p>
 *
 * @author xc
 * @since 2018-10-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TokenRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 设备号
     */
    @TableField("token")
    private String token;

    /**
     * 对应身份证号
     */
    @TableField("idNumber")
    private String idNumber;

    @TableField("addtime")
    private LocalDateTime addtime;


}
