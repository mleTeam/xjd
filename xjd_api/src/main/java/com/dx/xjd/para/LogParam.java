package com.dx.xjd.para;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
public class LogParam extends BaseSearchParam implements Serializable {

    @ApiModelProperty(value = "ID")
    private Integer logId;

    @ApiModelProperty(value = "日志标识 1管理员 2用户，比如''")
    @NotNull(message = "日志标识不能为空")
    private Integer logFlag;

    @ApiModelProperty(value = "日志类型，比如''")
    @NotNull(message = "日志类型不能为空")
    private Integer logTypeId;

    @ApiModelProperty(value = "日志内容，比如''")
    @NotNull(message = "日志内容不能为空")
    private String logContent;

    @ApiModelProperty(value = "日志ip，比如'0'")
    @NotNull(message = "日志ip不能为空")
    private Integer logIp;

    @ApiModelProperty(value = "日志创建时间，比如''")
    @NotNull(message = "日志创建时间不能为空")
    private Date logCreateDate;

    @ApiModelProperty(value = "用户或管理员id，比如''")
    @NotNull(message = "用户或管理员id不能为空")
    private Integer userId;


}
