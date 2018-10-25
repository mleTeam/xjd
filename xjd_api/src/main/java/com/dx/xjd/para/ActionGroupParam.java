package com.dx.xjd.para;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
public class ActionGroupParam extends BaseSearchParam implements Serializable {

    @ApiModelProperty(value = "ID")
    private Integer groupId;

    @ApiModelProperty(value = "管理组名称，比如''")
    @NotNull(message = "管理组名称不能为空")
    private String groupName;

    @ApiModelProperty(value = "管理组描述，比如''")
    @NotNull(message = "管理组描述不能为空")
    private String groupInfo;

    @ApiModelProperty(value = "创建时间，比如''")
    @NotNull(message = "创建时间不能为空")
    private Date createTime;

    @ApiModelProperty(value = "选中的权限，比如'1,2'")
    private String groupCheckAction;

    @ApiModelProperty(value = "半选中的权限，比如'2,3'")
    private String groupHalfCheckAction;

}
