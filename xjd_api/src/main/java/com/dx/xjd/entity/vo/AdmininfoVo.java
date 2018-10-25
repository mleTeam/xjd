package com.dx.xjd.entity.vo;

import com.dx.xjd.entity.Admininfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class AdmininfoVo extends Admininfo {

    @ApiModelProperty(value = "管理组名称，比如'后台管理组'")
    private String groupName;

}
