package com.dx.xjd.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 
* @ClassName: AssignModel  
* @Description: 分配权限管理参数传输 
* @author yeouyi  
* @date 2018年6月6日  
*
 */
@Data
public class AssignActionModel implements Serializable{
	
	private static final long serialVersionUID = -1427911035240573471L;

	private List<Assign> list;
	
	private List<Integer> idsList;

}
