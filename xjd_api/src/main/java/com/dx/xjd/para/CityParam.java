package com.dx.xjd.para;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class CityParam extends BaseSearchParam implements Serializable {

    @ApiModelProperty(value = "ID")
    private Integer cityId;

    @ApiModelProperty(value = "城市名称，比如''")
    private String cityName;

    @ApiModelProperty(value = "父id，比如''")
    private Integer cityParentId;

    @ApiModelProperty(value = "创建时间，比如''")
    private Date cityCreateTime;

    @ApiModelProperty(value = "城市编号，比如''")
    private String cityNo;


}
