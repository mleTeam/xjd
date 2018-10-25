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
public class IdcardInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("frontImageUrl")
    private String frontImageUrl;

    @TableField("backImageUrl")
    private String backImageUrl;

    @TableField("name")
    private String name;

    @TableField("sex")
    private String sex;

    @TableField("nation")
    private String nation;

    @TableField("birthday")
    private String birthday;

    @TableField("address")
    private String address;

    @TableField("number")
    private String number;

    @TableField("authority")
    private String authority;

    @TableField("checkStatus")
    private String checkStatus;

    @TableField("message")
    private String message;

    @TableField("userId")
    private Integer userId;

    @TableField("error")
    private String error;

    @TableField("msg")
    private String msg;

    @TableField("addTime")
    private LocalDateTime addTime;

    @TableField("timeLimit")
    private String timeLimit;

    @TableField("step1Time")
    private LocalDateTime step1Time;


}
