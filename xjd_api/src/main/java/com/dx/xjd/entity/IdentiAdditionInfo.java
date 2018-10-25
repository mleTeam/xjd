package com.dx.xjd.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 风控附加认证信息
 * </p>
 *
 * @author xc
 * @since 2018-10-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class IdentiAdditionInfo implements Serializable {

    private static final long serialVersionUID = 1L;

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

    /**
     * 毕业学校
     */
    @TableField("graduateSchool")
    private String graduateSchool;

    /**
     * 学历
     */
    @TableField("educationBackground")
    private String educationBackground;

    /**
     * 入学年份
     */
    @TableField("matriculationTime")
    private String matriculationTime;

    /**
     * 专业
     */
    @TableField("profession")
    private String profession;

    /**
     * 毕业时间
     */
    @TableField("graduationTime")
    private String graduationTime;

    /**
     * 毕业结论
     */
    @TableField("graduationConclusion")
    private String graduationConclusion;

    /**
     * 学历类型
     */
    @TableField("educationType")
    private String educationType;

    @TableField("queryResult")
    private String queryResult;

    @TableField("setEducationalTime")
    private LocalDateTime setEducationalTime;

    @TableField("addtime")
    private LocalDateTime addtime;

    @TableField("updatetime")
    private LocalDateTime updatetime;

    /**
     * 百度黑名单
     */
    @TableField("baiduBlacklistDesc")
    private String baiduBlacklistDesc;

    /**
     * 该用户所有家人联系人都不匹配通讯录
     */
    @TableField("manualRule1")
    private Boolean manualRule1;

    /**
     * 一天内同一设备关联不同的借款人身份证数大于等于2
     */
    @TableField("manualRule2")
    private Boolean manualRule2;

    /**
     * 通讯录联系人姓名含中介敏感信息
     */
    @TableField("manualRule3")
    private Boolean manualRule3;

    /**
     * 通讯录存储条数过少
     */
    @TableField("manualRule4")
    private Boolean manualRule4;

    /**
     * 不同用户相同家人联系人手机号码
     */
    @TableField("manualRule5")
    private Boolean manualRule5;

    /**
     * 前海返回吗
     */
    @TableField("qhCode")
    private String qhCode;

    /**
     * 前海返回信息
     */
    @TableField("qhMessage")
    private String qhMessage;

    /**
     * 来源代码
     */
    @TableField("rskType")
    private String rskType;

    /**
     * 风险得分
     */
    @TableField("rskScore")
    private String rskScore;

    /**
     * 风险标记
     */
    @TableField("rskMark")
    private String rskMark;

    @TableField("setQhInfoTime")
    private LocalDateTime setQhInfoTime;

    /**
     * 白骑士黑名单权重分
     */
    @TableField("bqsBlacklistScore")
    private String bqsBlacklistScore;

    @TableField("setBqsBlacklistTime")
    private LocalDateTime setBqsBlacklistTime;

    /**
     * 机构G
     */
    @TableField("agentGBlacklistDesc")
    private String agentGBlacklistDesc;

    /**
     * 机构R
     */
    @TableField("agentRBlacklistDesc")
    private String agentRBlacklistDesc;

    /**
     * 联系人信息
     */
    @TableField("phoneListString")
    private String phoneListString;

    /**
     * 风险检测
     */
    @TableField("riskAnalysis")
    private String riskAnalysis;

    /**
     * 特殊号码类型
     */
    @TableField("specialCate")
    private String specialCate;

    /**
     * 紧急联系人分析
     */
    @TableField("emergencyAnalysis")
    private String emergencyAnalysis;

    /**
     * 机构Y信用评分
     */
    @TableField("agentYScore")
    private Integer agentYScore;

    /**
     * 机构Y信用评分描述
     */
    @TableField("agentYTag")
    private String agentYTag;

    @TableField("callLog")
    private String callLog;

    /**
     * 运营商月消费
     */
    @TableField("monthlyConsumption")
    private String monthlyConsumption;

    /**
     * 用户画像
     */
    @TableField("userPortrait")
    private String userPortrait;


}
