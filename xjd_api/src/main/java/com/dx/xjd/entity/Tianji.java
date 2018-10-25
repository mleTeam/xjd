package com.dx.xjd.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

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
public class Tianji implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("userId")
    private String userId;

    @TableField("outUniqueId")
    private String outUniqueId;

    @TableField("state")
    private String state;

    @TableField("account")
    private String account;

    @TableField("accountType")
    private String accountType;

    /**
     * 查询id
     */
    @TableField("search_id")
    private String searchId;

    @TableField("error")
    private Integer error;

    @TableField("msg")
    private String msg;

    @TableField("tianjiReturnAll")
    private String tianjiReturnAll;

    @TableField("html")
    private String html;

    @TableField("downloadUrl")
    private String downloadUrl;

    @TableField("identiOrderId")
    private String identiOrderId;


}
