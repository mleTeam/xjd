package com.dx.xjd.para;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
public class SmsParam extends BaseSearchParam implements Serializable {

    @ApiModelProperty(value = "ID")
    private Integer smsId;

    @ApiModelProperty(value = "短信内容，比如''")
    @NotNull(message = "短信内容不能为空")
    private String smsContent;

    @ApiModelProperty(value = "短信创建时间，比如''")
    @NotNull(message = "短信创建时间不能为空")
    private Date smsCreateTime;

    @ApiModelProperty(value = "短信接收者，比如''")
    @NotNull(message = "短信接收者不能为空")
    private String smsPhone;

    @ApiModelProperty(value = "状态  0成功  1失败，比如'0'")
    @NotNull(message = "状态不能为空")
    private Integer smsState;

    @ApiModelProperty(value = "短信通道，比如''")
    @NotNull(message = "短信通道不能为空")
    private Integer smsTypeId;

    @ApiModelProperty(value = "短信发送时间，比如''")
    private Date smsSendTime;


}
