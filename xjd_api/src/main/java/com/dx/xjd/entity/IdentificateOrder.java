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
public class IdentificateOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("userId")
    private Integer userId;

    @TableField("cellphone")
    private String cellphone;

    @TableField("type")
    private Boolean type;

    @TableField("name")
    private String name;

    @TableField("IDNumber")
    private String IDNumber;

    /**
     * 身份证正反照
     */
    @TableField("IDUpPicUrl")
    private String IDUpPicUrl;

    @TableField("IDDownPicUrl")
    private String IDDownPicUrl;

    /**
     * 0:男, 1:女
     */
    @TableField("sex")
    private Integer sex;

    @TableField("firstContactType")
    private Boolean firstContactType;

    @TableField("firstContactName")
    private String firstContactName;

    @TableField("firstContactCellphone")
    private String firstContactCellphone;

    @TableField("secondContactType")
    private Boolean secondContactType;

    @TableField("secondContactName")
    private String secondContactName;

    @TableField("secondContactCellphone")
    private String secondContactCellphone;

    /**
     * 0:没有获取  1:有获取
     */
    @TableField("hasGetPhoneList")
    private Boolean hasGetPhoneList;

    @TableField("checkManagerId")
    private Integer checkManagerId;

    /**
     * 审核人姓名
     */
    @TableField("checkManagerName")
    private String checkManagerName;

    /**
     * 2:等待审核, 3:认证通过, 4:认证驳回, 5:认证失败
     */
    @TableField("state")
    private Integer state;

    @TableField("passTime")
    private LocalDateTime passTime;

    /**
     * 接单时间
     */
    @TableField("acceptTime")
    private LocalDateTime acceptTime;

    @TableField("rejectedTime")
    private LocalDateTime rejectedTime;

    /**
     * 否单时间
     */
    @TableField("failTime")
    private LocalDateTime failTime;

    @TableField("picUrl1")
    private String picUrl1;

    @TableField("picUrl2")
    private String picUrl2;

    @TableField("picUrl3")
    private String picUrl3;

    @TableField("picUrl4")
    private String picUrl4;

    @TableField("idphoneComparisionResult")
    private Double idphoneComparisionResult;

    @TableField("idphoneComparisionInfo")
    private String idphoneComparisionInfo;

    @TableField("idphoneComparisionMessage")
    private String idphoneComparisionMessage;

    @TableField("finalScore")
    private Integer finalScore;

    @TableField("tongdunInfo")
    private String tongdunInfo;

    @TableField("mobileAddress")
    private String mobileAddress;

    /**
     * 是否提交运营商信息
     */
    @TableField("hasSubmitMobile")
    private Boolean hasSubmitMobile;

    @TableField("salary")
    private Integer salary;

    @TableField("company")
    private String company;

    @TableField("companyPhone")
    private String companyPhone;

    @TableField("workAddress")
    private String workAddress;

    /**
     * 提交资料步数
     */
    @TableField("step")
    private Integer step;

    @TableField("submitTime")
    private LocalDateTime submitTime;

    @TableField("step1Time")
    private LocalDateTime step1Time;

    @TableField("mobileInfo")
    private String mobileInfo;

    /**
     * 是否提交京东信息
     */
    @TableField("hasSubmitJd")
    private Boolean hasSubmitJd;

    @TableField("jdInfo")
    private String jdInfo;

    @TableField("step2Time")
    private LocalDateTime step2Time;

    @TableField("step3Time")
    private LocalDateTime step3Time;

    @TableField("step4Time")
    private LocalDateTime step4Time;

    /**
     * 驳回原因
     */
    @TableField("failReason")
    private String failReason;

    @TableField("failType")
    private Boolean failType;

    /**
     * 手机位置
     */
    @TableField("cellphoneLocation")
    private String cellphoneLocation;

    /**
     * 第一联系人手机位置
     */
    @TableField("firstContactCellphoneLocation")
    private String firstContactCellphoneLocation;

    /**
     * 第二联系人手机位置
     */
    @TableField("secondContactCellphoneLocation")
    private String secondContactCellphoneLocation;

    /**
     * 运营商报告分数
     */
    @TableField("xsScore")
    private String xsScore;

    /**
     * 芝麻openid
     */
    @TableField("zmOpenId")
    private String zmOpenId;

    /**
     * 芝麻分
     */
    @TableField("zmScore")
    private Integer zmScore;

    /**
     * 是否授权芝麻分0否1是
     */
    @TableField("hadSetZmOpenId")
    private Integer hadSetZmOpenId;

    /**
     * 是否有学历0否1是
     */
    @TableField("hasEduBg")
    private Integer hasEduBg;

    /**
     * 学历
     */
    @TableField("educationBackground")
    private String educationBackground;

    @TableField("ip")
    private String ip;

    @TableField("ipCity")
    private String ipCity;

    @TableField("token")
    private String token;

    /**
     * 是否命中前海0否1是
     */
    @TableField("isHitQh")
    private Integer isHitQh;

    /**
     * 是否命中百度0否1是
     */
    @TableField("isHitBd")
    private Integer isHitBd;

    /**
     * 三月内多平台借款数
     */
    @TableField("threeMonthLoanCount")
    private Integer threeMonthLoanCount;

    /**
     * 手机在网时长
     */
    @TableField("phoneWorkLength")
    private String phoneWorkLength;

    /**
     * 身份三维认证
     */
    @TableField("threeItemMessage")
    private String threeItemMessage;

    /**
     * 是否命中机构G0否1是
     */
    @TableField("isHitAgentG")
    private Integer isHitAgentG;

    /**
     * 是否命中机构R0否1是
     */
    @TableField("isHitAgentR")
    private Integer isHitAgentR;

    /**
     * 是否命中芝麻ivs0否1是
     */
    @TableField("isHitZmIvs")
    private Integer isHitZmIvs;

    /**
     * 1个月多平台 申请借款
     */
    @TableField("oneMonthLoanCount")
    private Integer oneMonthLoanCount;

    /**
     * 七天内多平台 申请借款
     */
    @TableField("sevenDayLoanCount")
    private Integer sevenDayLoanCount;

    /**
     * 是否命中运营商风险检测
     */
    @TableField("isHitRiskAnalysis")
    private Integer isHitRiskAnalysis;

    /**
     * 是否命中运营商特殊号码
     */
    @TableField("isHitSpecialCate")
    private Integer isHitSpecialCate;

    /**
     * 是否命中运营商特殊号码110
     */
    @TableField("isHitSpecialCate110")
    private Integer isHitSpecialCate110;

    /**
     * 是否命中运营商特殊号码贷款类催收
     */
    @TableField("isHitSpecialCateDebt")
    private Integer isHitSpecialCateDebt;

    /**
     * 朋友地址圈与户籍和ip地址都不一致
     */
    @TableField("isHitFriendArea")
    private Integer isHitFriendArea;

    /**
     * 运营商报告通讯录记录少于等于10
     */
    @TableField("isCallLogLess10")
    private Integer isCallLogLess10;

    /**
     * 所有联系人都不匹配通话记录
     */
    @TableField("callLogNotMatch")
    private Integer callLogNotMatch;

    /**
     * 通讯录匹配通话记录数
     */
    @TableField("phoneListMatchCount")
    private Integer phoneListMatchCount;

    /**
     * 是否命中机构Y信用评分
     */
    @TableField("isHitAgentYScore")
    private Integer isHitAgentYScore;

    /**
     * 当前余额
     */
    @TableField("phoneCurBalance")
    private Double phoneCurBalance;

    /**
     * 月均消费
     */
    @TableField("phoneMonthlyAvgConsumption")
    private Double phoneMonthlyAvgConsumption;

    /**
     * 同盾分高于40
     */
    @TableField("tongdunMoreThan40")
    private Boolean tongdunMoreThan40;


}
