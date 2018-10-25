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
 * 日志表
 * </p>
 *
 * @author xc
 * @since 2018-10-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Log implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "log_id", type = IdType.AUTO)
    private Integer logId;

    /**
     * 日志标识 1管理员 2用户
     */
    @TableField("log_flag")
    private Integer logFlag;

    /**
     * 日志类型
     */
    @TableField("log_type_id")
    private Integer logTypeId;

    /**
     * 日志内容
     */
    @TableField("log_content")
    private String logContent;

    /**
     * 日志ip
     */
    @TableField("log_ip")
    private String logIp;

    /**
     * 日志创建时间
     */
    @TableField("log_create_date")
    private LocalDateTime logCreateDate;

    /**
     * 用户或管理员id
     */
    @TableField("user_id")
    private Integer userId;


}
