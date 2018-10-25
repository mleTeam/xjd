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
public class UserMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 消息类型 0系统消息1通知消息
     */
    @TableField("msgType")
    private Boolean msgType;

    /**
     * 标题
     */
    @TableField("title")
    private String title;

    /**
     * 内容
     */
    @TableField("content")
    private String content;

    /**
     * 附加内容
     */
    @TableField("additionalContent")
    private String additionalContent;

    @TableField("addtime")
    private LocalDateTime addtime;

    @TableField("updatetime")
    private LocalDateTime updatetime;

    @TableField("userId")
    private Integer userId;

    @TableField("managerId")
    private Integer managerId;


}
