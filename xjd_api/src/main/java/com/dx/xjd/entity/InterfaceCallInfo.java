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
public class InterfaceCallInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("ip")
    private String ip;

    @TableField("ipAddress")
    private String ipAddress;

    @TableField("interfaceName")
    private String interfaceName;

    @TableField("callTime")
    private LocalDateTime callTime;

    @TableField("returnCode")
    private String returnCode;

    @TableField("returnMessage")
    private String returnMessage;


}
