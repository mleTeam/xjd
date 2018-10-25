package com.dx.xjd.controller.manage;

import com.alibaba.fastjson.JSONObject;
import com.dx.xjd.entity.Action;
import com.dx.xjd.entity.Admininfo;
import com.dx.xjd.entity.vo.ActionVo;
import com.dx.xjd.exception.MleAuthenticationException;
import com.dx.xjd.model.AccessToken;
import com.dx.xjd.para.AdmininfoLoginParam;
import com.dx.xjd.redis.RedisComponent;
import com.dx.xjd.service.IActionService;
import com.dx.xjd.service.IAdmininfoService;
import com.dx.xjd.util.Constants;
import com.dx.xjd.util.EncryptUtils;
import com.dx.xjd.util.ObjectUtils;
import com.dx.xjd.util.string.StringUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.MessageFormat;
import java.util.*;

@Api(tags = { "管理员登录接口" })
@RestController
@RequestMapping("/manage")
@Slf4j
public class LoginController {

    @Autowired
    private IAdmininfoService admininfoService;

    @Autowired
    private IActionService actionService;

    @Autowired
    private Constants constants;

    @Autowired
    private RedisComponent redisComponent;

    @ApiOperation(value = "登录", notes = "登录获取token令牌")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public JSONObject add(@RequestBody AdmininfoLoginParam admininfoLoginParam) {
        JSONObject json = new JSONObject();
        log.info("管理员登录开始，请求参数：{}", admininfoLoginParam);
        if (admininfoLoginParam == null || StringUtil.isEmpty(admininfoLoginParam.getAdminName()) || StringUtil.isEmpty(admininfoLoginParam.getAdminPwd())) {
            throw new MleAuthenticationException("用户名及密码不得为空！");
        }else{
            Admininfo admininfoParam = ObjectUtils.convert(admininfoLoginParam, Admininfo.class);
            Admininfo admininfo = admininfoService.login(admininfoParam);
            if(admininfo != null){
                List<Action> actionList = actionService.listActionByAdminId(admininfo.getAdminId());
                List<ActionVo> actionVoList = new ArrayList<ActionVo>();
                for(Action action : actionList){
                    ActionVo actionVo = ObjectUtils.convert(action, ActionVo.class);
                    actionVoList.add(actionVo);
                }
                List<ActionVo> actions = new ArrayList<ActionVo>();
                Set<String> rules = new HashSet<String>();
                for (ActionVo actionVo : actionVoList) {
                    if (actionVo.getActionParentid() == 0) {
                        actions.add(actionVo);
                        for (Action temp : actionList){
                            if (actionVo.getActionId().equals(temp.getActionParentid())) {
                                if (actionVo.getActionList() == null) {
                                    actionVo.setActionList(new ArrayList<Action>());
                                }
                                actionVo.getActionList().add(temp);
                                rules.add(temp.getActionRule());
                            }
                        }
                    }
                    if(actionVo.getActionIsbutton().equals(2)){
                        rules.add(actionVo.getActionRule());
                    }
                }

                //构建Token实体
                AccessToken token = new AccessToken();
                Long serverTime = System.currentTimeMillis();
                token.setAdminInfo(admininfo);
                token.setAccessToken(EncryptUtils.encryptMD5_STD(MessageFormat.format("mle_admin_access_token_{0}_{1}", admininfo.getAdminId(), serverTime)));
                token.setRefreshToken(EncryptUtils.encryptMD5_STD(MessageFormat.format("mle_admin_refresh_token_{0}_{1}", admininfo.getAdminId(), serverTime)));
                token.setMacAlgorithm(EncryptUtils.KEY_MD5);
                token.setMacKey(UUID.randomUUID().toString());
                token.setExpiresAt(serverTime + constants.getADMIN_EXSPIRETIME());
                token.setServerTime(serverTime);
                token.setActions(actions);//路由详情
                token.setRules(rules);//所有权限
                redisComponent.set(token.getAccessToken(), token, constants.getADMIN_EXSPIRETIME());

                json.put(constants.getMESSAGE(), "管理员登录");
                json.put(constants.getDATA(), token);
                json.put(constants.getSTATUS(), HttpServletResponse.SC_CREATED);
            }else{
                throw new MleAuthenticationException("用户名或密码错误！");
            }
        }
        log.info("管理员登录结束");
        return json;
    }

    @ApiOperation(value = "获取token", notes = "登录获取token令牌")
    @RequestMapping(value = "menus", method = RequestMethod.GET)
    public JSONObject getMenus(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        String auth = request.getAttribute(constants.getACCESS_TOKEN()).toString();
        log.info("获取token开始，请求参数：{}", auth);
        AccessToken accessToken = redisComponent.get(auth, AccessToken.class);
        if(ObjectUtils.isNotEmpty(accessToken)){
            json.put(constants.getMESSAGE(), "用户获取侧边栏菜单");
            json.put(constants.getITEM(), accessToken.getActions());
            json.put(constants.getSTATUS(), HttpServletResponse.SC_OK);
        }else{
            throw new MleAuthenticationException("未获取到认证信息.....");
        }
        log.info("获取token开始");
        return json;
    }

}
