package com.dx.xjd.para;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class AdmininfoLoginParam implements Serializable {

    @ApiModelProperty(value = "管理账号，比如'admin'")
    @NotNull(message = "管理账号不能为空")
    private String adminName;

    @ApiModelProperty(value = "管理密码，比如'123456'")
    @NotNull(message = "管理密码不能为空")
    private String adminPwd;
}
