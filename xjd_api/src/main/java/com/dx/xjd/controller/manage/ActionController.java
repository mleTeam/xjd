package com.dx.xjd.controller.manage;

import com.alibaba.fastjson.JSONObject;
import com.dx.xjd.annotation.AuthPrincipal;
import com.dx.xjd.annotation.Rule;
import com.dx.xjd.entity.Action;
import com.dx.xjd.entity.Admininfo;
import com.dx.xjd.para.ActionParam;
import com.dx.xjd.service.IActionService;
import com.dx.xjd.util.Constants;
import com.dx.xjd.util.ObjectUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = { "权限接口" })
@RestController
@RequestMapping("/manage/action")
@Slf4j
public class ActionController {

    @Autowired
    private IActionService actionService;

    @Autowired
    private Constants constants;

    @ApiOperation(value = "权限列表", notes = "根据页码及显示数量来获取权限")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "toke", required = false, dataType = "String", paramType = "header", defaultValue = ""),
            @ApiImplicitParam(name = "actionParam", value = "权限", required = false, dataType = "actionParam")
    })
    @RequestMapping(method = RequestMethod.GET)
    @Rule("ADMIN_ACTION")
    public JSONObject getParam(@RequestHeader(required = false) String Authorization, ActionParam actionParam, @AuthPrincipal Admininfo admininfo) {
        log.info("权限查询开始，请求参数：{}", actionParam);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(constants.getSTATUS(), HttpServletResponse.SC_OK);
        jsonObject.put(constants.getMESSAGE(), "获得权限列表");
        jsonObject.put(constants.getITEM(), actionService.listByParam(actionParam));
        jsonObject.put(constants.getCOUNT(), actionService.countByParam(actionParam));
        log.info("权限查询结束");
        return jsonObject;
    }

    @ApiOperation(value = "添加权限", notes = "添加权限信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "toke", required = false, dataType = "String", paramType = "header", defaultValue = ""),
            @ApiImplicitParam(name = "actionParam", value = "权限", required = true, dataType = "ActionParam")
    })
    @RequestMapping(method = RequestMethod.POST)
    @Rule("ADMIN_ACTION_ADD")
    public JSONObject add(@RequestHeader(required = false) String Authorization, @RequestBody @Valid ActionParam actionParam, @AuthPrincipal Admininfo admininfo) {
        log.info("权限添加开始，请求参数：{}", actionParam);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(constants.getSTATUS(), HttpServletResponse.SC_CREATED);
        Action action = ObjectUtils.convert(actionParam, Action.class);
        jsonObject.put(constants.getDATA(), actionService.save(action));
        jsonObject.put(constants.getMESSAGE(), "添加权限成功");
        log.info("权限添加结束");
        return jsonObject;
    }

    @ApiOperation(value = "ID查询", notes = "按管理员ID查询权限信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "toke", required = false, dataType = "String", paramType = "header", defaultValue = ""),
            @ApiImplicitParam(name = "actionId", value = "ID", required = true, dataType = "Integer", paramType = "path")
    })
    @RequestMapping(value = "{actionId}", method = RequestMethod.GET)
    @Rule("ADMIN_ACTION")
    public JSONObject getById(@RequestHeader(required = false) String Authorization, @PathVariable Integer actionId, @AuthPrincipal Admininfo admininfo) {
        log.info("权限ID查询添加开始，请求参数：{}", actionId);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(constants.getSTATUS(), HttpServletResponse.SC_OK);
        jsonObject.put(constants.getMESSAGE(), "权限信息");
        jsonObject.put(constants.getDATA(), actionService.getById(actionId));
        log.info("权限ID查询添加结束");
        return jsonObject;
    }

    @ApiOperation(value = "修改权限", notes = "修改权限(所有字段)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "toke", required = false, dataType = "String", paramType = "header", defaultValue = ""),
            @ApiImplicitParam(name = "actionParam", value = "权限", required = true, dataType = "actionParam")
    })
    @RequestMapping(method = RequestMethod.PUT)
    @Rule("ADMIN_ACTION_UPDATE")
    public JSONObject updateAllColumn(@RequestHeader(required = false) String Authorization, @RequestBody @Valid ActionParam actionParam, @AuthPrincipal Admininfo admininfo) {
        log.info("权限全部修改开始，请求参数：{}", actionParam);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(constants.getSTATUS(), HttpServletResponse.SC_CREATED);
        Action action = ObjectUtils.convert(actionParam, Action.class);
        jsonObject.put(constants.getDATA(), actionService.updateById(action));
        jsonObject.put(constants.getMESSAGE(), "权限修改成功");
        log.info("权限全部修改结束");
        return jsonObject;
    }

    @ApiOperation(value = "修改权限", notes = "修改权限(所选字段)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "toke", required = false, dataType = "String", paramType = "header", defaultValue = ""),
            @ApiImplicitParam(name = "actionParam", value = "权限", required = true, dataType = "ActionParam")
    })
    @RequestMapping(method = RequestMethod.PATCH)
    @Rule("ADMIN_ACTION_UPDATE")
    public JSONObject updateSelective(@RequestHeader(required = false) String Authorization, @RequestBody @Valid ActionParam actionParam, @AuthPrincipal Admininfo admininfo) {
        log.info("权限所选字段修改开始，请求参数：{}", actionParam);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(constants.getSTATUS(), HttpServletResponse.SC_CREATED);
        Action action = ObjectUtils.convert(actionParam, Action.class);
        jsonObject.put(constants.getDATA(), actionService.updateById(action));
        jsonObject.put(constants.getMESSAGE(), "权限修改成功");
        log.info("权限所选字段修改结束");
        return jsonObject;
    }

    @ApiOperation(value = "删除权限", notes = "按权限ID删除权限")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "toke", required = false, dataType = "String", paramType = "header", defaultValue = ""),
            @ApiImplicitParam(name = "admininfoId", value = "管理员ID", required = true, dataType = "Integer", paramType = "path")
    })
    @RequestMapping(value = "{actionId}", method = RequestMethod.DELETE)
    @Rule("ADMIN_ACTION_DELETE")
    public JSONObject delete(@RequestHeader(required = false) String Authorization, @PathVariable Integer actionId, @AuthPrincipal Admininfo admininfo) {
        log.info("权限删除开始，请求参数：{}", actionId);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(constants.getSTATUS(), HttpServletResponse.SC_NO_CONTENT);
        jsonObject.put(constants.getDATA(), actionService.removeById(actionId));
        jsonObject.put(constants.getMESSAGE(), "权限删除成功");
        log.info("权限删除结束");
        return jsonObject;
    }

    @ApiOperation(value = "权限父类列表", notes = "获取所有一级，二级权限")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "toke", required = false, dataType = "String", paramType = "header", defaultValue = "")
    })
    @RequestMapping(value = "getParentAll", method = RequestMethod.GET)
    @Rule("ADMIN_ACTION")
    public JSONObject getParentAll(@RequestHeader(required = false) String Authorization, @AuthPrincipal Admininfo admininfo) {
        log.info("权限父类查询开始");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(constants.getSTATUS(), HttpServletResponse.SC_OK);
        jsonObject.put(constants.getMESSAGE(), "获得权限父类列表");
        jsonObject.put(constants.getITEM(), getChild(0, 1, 2));
        log.info("权限父类查询结束");
        return jsonObject;
    }

    @ApiOperation(value = "权限全部列表", notes = "获取全部权限")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "toke", required = false, dataType = "String", paramType = "header", defaultValue = "")
    })
    @RequestMapping(value = "getAll", method = RequestMethod.GET)
    @Rule("ADMIN_ACTION")
    public JSONObject getAll(@RequestHeader(required = false) String Authorization, @AuthPrincipal Admininfo admininfo) {
        log.info("权限全部查询开始");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(constants.getSTATUS(), HttpServletResponse.SC_OK);
        jsonObject.put(constants.getMESSAGE(), "获得权限全部列表");
        jsonObject.put(constants.getITEM(), getChild(0, 1, 3));
        log.info("权限全部查询结束");
        return jsonObject;
    }


    /**
     * 封装权限树
     * @param parentId
     * @param index     当前次数
     * @param count     循环总次数
     * @return
     */
    private List<Map<String, Object>> getChild(Integer parentId, Integer index, Integer count){
        List<Map<String, Object>> listMap = new ArrayList();
        log.info("权限全部查询开始,递归parentId==" + parentId);
        ActionParam actionParam = new ActionParam();
        actionParam.setActionParentId(parentId);
        actionParam.setCurrent(1);
        actionParam.setSize(1000);
        List<Action> actionList = actionService.listByParam(actionParam);
        for(Action action : actionList){
            actionParam.setActionParentId(action.getActionId());
            List<Action> actionChildList = actionService.listByParam(actionParam);//把父类的编号传入,查询改父亲下的子类
            Map tempMap = new HashMap();
            tempMap.put("value", action.getActionId());
            tempMap.put("label", action.getActionName());
            if(actionChildList.size() > 0 && index < count){//如果该设备还有孩子,继续做查询,直到设备没有孩子,也就是最后一个节点
                Integer nextIndex = index + 1;  //获取下一次次数
                tempMap.put("children", getChild(action.getActionId(), nextIndex, count));
            }
            listMap.add(tempMap);
        }
        return listMap;
    }

    /**
     * 组装一级二级权限
     * @param actions
     * @return
     */
    private List<Map<String, Object>> getActions(List<Action> actions, boolean flag){
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (Action action : actions){
            if (action.getActionParentid() == 0) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("value", action.getActionId());
                map.put("label", action.getActionName());
                List<Map<String, Object>> tempList = new ArrayList<Map<String, Object>>();
                for (Action temp : actions){
                    if (action.getActionId().equals(temp.getActionParentid())) {
                        Map<String, Object> tmepMap = new HashMap<String, Object>();
                        tmepMap.put("value", temp.getActionId());
                        tmepMap.put("label", temp.getActionName());
                        tempList.add(tmepMap);
                        if(flag){
                            List<Map<String, Object>> tempNodeList = new ArrayList<Map<String, Object>>();
                            for (Action tempNode : actions){
                                if (temp.getActionId().equals(tempNode.getActionParentid())) {
                                    Map<String, Object> tmepNodeMap = new HashMap<String, Object>();
                                    tmepNodeMap.put("value", tempNode.getActionId());
                                    tmepNodeMap.put("label", tempNode.getActionName());
                                    tempNodeList.add(tmepNodeMap);
                                }
                            }
                            if(tempNodeList.size() > 0)
                                tmepMap.put("children", tempNodeList);
                        }
                    }
                }
                map.put("children", tempList);
                list.add(map);
            }
        }
        return list;
    }

}
