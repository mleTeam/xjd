package com.dx.xjd.para;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class ActionParam extends BaseSearchParam implements Serializable {

    @ApiModelProperty(value = "权限ID，比如'1'")
    private Integer actionId;

    @ApiModelProperty(value = "权限名称，比如'权限列表'")
    @NotNull(message = "权限名称不能为空")
    private String actionName;

    @ApiModelProperty(value = "权限路径，比如'action/list'")
    @NotNull(message = "权限路由不能为空")
    private String actionUrl;

    @ApiModelProperty(value = "左侧导航栏是否可见，比如'1'")
    @NotNull(message = "左侧导航栏显示控制不能为空")
    private Integer actionViewMode;

    @ApiModelProperty(value = "父类Id，比如'0'")
    @NotNull(message = "权限父类ID不能为空")
    private Integer actionParentId;

    @ApiModelProperty(value = "是否需要session，比如'1'")
    @NotNull(message = "是否开启session不能为空")
    private Integer actionIsSession;

    @ApiModelProperty(value = "按钮是否显示，比如'1'")
    private Integer actionIsButton;

    @ApiModelProperty(value = "排序，比如'30'")
    @NotNull(message = "排序不能为空")
    private Integer actionSort;

    @ApiModelProperty(value = "路由，比如''")
    private String actionRule;

}
