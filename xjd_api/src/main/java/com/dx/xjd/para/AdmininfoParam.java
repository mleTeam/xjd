package com.dx.xjd.para;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
public class AdmininfoParam extends BaseSearchParam implements Serializable {

    @ApiModelProperty(value = "管理员ID，比如'1'")
    private Integer adminId;

    @ApiModelProperty(value = "管理员帐号，比如'admin'")
    @NotNull(message = "管理员帐号不能为空")
    private String adminName;

    @ApiModelProperty(value = "管理员密码，比如'admin'")
    @NotNull(message = "管理员密码不能为空")
    private String adminPwd;

    @ApiModelProperty(value = "创建时间，比如''")
    private Date adminCreateTime;

    @ApiModelProperty(value = "最后登录时间，比如''")
    private Date adminLastLoginTime;

    @ApiModelProperty(value = "描述，比如''")
    private String adminDesc;

    @ApiModelProperty(value = "管理员级别，比如''")
    @NotNull(message = "管理员级别不能为空")
    private Integer adminLevel;

    @ApiModelProperty(value = "部门id，比如''")
    private Integer deptId;

    @ApiModelProperty(value = "用户id，比如''")
    private Integer userId;

    @ApiModelProperty(value = "角色组，比如'1,2'")
    private String groupId;
}
