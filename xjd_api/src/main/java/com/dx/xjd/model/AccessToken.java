package com.dx.xjd.model;

import com.dx.xjd.entity.Admininfo;
import com.dx.xjd.entity.vo.ActionVo;
import lombok.Data;

import java.util.List;
import java.util.Set;

/**
 * 
* @ClassName: AccessToken 
* @Description: 登录信息
* @author devil 
* @date 2018年6月1日 上午9:39:40 
*
 */
@Data
public class AccessToken {
	
	private String accessToken;//token
	
    private long expiresAt;//Token过期时间

    private String refreshToken;//刷新用Token

    private String macKey;//Mac key值

    private String macAlgorithm;//Mac算法
 
    private Long serverTime;//服务器时间

    private Admininfo adminInfo; //管理员信息

    private List<ActionVo> actions;//所有权限信息(路由、名称等完整信息)

    private Set<String> rules;//所有权限

}
