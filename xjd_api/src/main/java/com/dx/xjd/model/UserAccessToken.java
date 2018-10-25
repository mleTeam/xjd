package com.dx.xjd.model;

import com.dx.xjd.entity.User;
import lombok.Data;

/**
 * 
* @ClassName: AccessToken 
* @Description: 登录信息
* @author devil 
* @date 2018年6月1日 上午9:39:40 
*
 */
@Data
public class UserAccessToken {

	private User user; //用户信息

    private String accessToken;//token
	
    private long expiresAt;//Token过期时间

    private String refreshToken;//刷新用Token

    private String macKey;//Mac key值

    private String macAlgorithm;//Mac算法
 
    private Long serverTime;//服务器时间

}
