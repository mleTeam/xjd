package com.dx.xjd.para;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
public class DeptParam extends BaseSearchParam implements Serializable {

    @ApiModelProperty(value = "ID")
    private Integer deptId;

    @ApiModelProperty(value = "部门名称，比如''")
    @NotNull(message = "部门名称不能为空")
    private String deptName;

    @ApiModelProperty(value = "部门描述，比如''")
    private String deptDesc;

    @ApiModelProperty(value = "上级ID，比如'0'")
    @NotNull(message = "上级ID不能为空")
    private Integer deptParentId;

    @ApiModelProperty(value = "创建时间，比如'2018-08-08 08:08:08'")
    private Date deptCreateTime;

    @ApiModelProperty(value = "修改时间，比如'2018-08-08 08:08:08''")
    private Date deptUpdateTime;

}
