package com.dx.xjd.entity.vo;

import com.dx.xjd.entity.Action;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class ActionVo extends Action {

    @ApiModelProperty(value = "下级列表，比如'[权限列表，角色列表]'")
    private List<Action> actionList;

}
