package com.dx.xjd.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 
* @ClassName: Assign  
* @Description: 分配管理参数传输  
* @author yeouyi  
* @date 2018年6月6日  
*
 */
@Data
public class Assign implements Serializable{
	
	private static final long serialVersionUID = 49590552178366030L;

	private Integer actionId;
	
	private String actionName;
	
	private List<Assign> list;

}
