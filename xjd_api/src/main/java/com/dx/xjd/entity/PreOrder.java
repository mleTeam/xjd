package com.dx.xjd.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 预下单
 * </p>
 *
 * @author xc
 * @since 2018-10-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class PreOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("userId")
    private Integer userId;

    @TableField("bankCardId")
    private Integer bankCardId;

    /**
     * 借款金额
     */
    @TableField("money")
    private Float money;

    /**
     * 借款天数
     */
    @TableField("loanDay")
    private Integer loanDay;

    @TableField("interestRatio")
    private Float interestRatio;

    @TableField("serviceRatio")
    private Float serviceRatio;

    /**
     * 状态0待处理1已处理
     */
    @TableField("state")
    private Integer state;

    @TableField("addtime")
    private LocalDateTime addtime;

    @TableField("finishTime")
    private LocalDateTime finishTime;


}
