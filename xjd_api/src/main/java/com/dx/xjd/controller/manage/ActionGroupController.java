package com.dx.xjd.controller.manage;


import com.alibaba.fastjson.JSONObject;
import com.dx.xjd.annotation.AuthPrincipal;
import com.dx.xjd.annotation.Rule;
import com.dx.xjd.entity.ActionGroup;
import com.dx.xjd.entity.Admininfo;
import com.dx.xjd.para.ActionGroupParam;
import com.dx.xjd.service.IActionGroupService;
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

@Api(tags = { "管理组接口" })
@RestController
@RequestMapping("/manage/group")
@Slf4j
public class ActionGroupController {

    @Autowired
    private IActionGroupService actionGroupService;

    @Autowired
    private Constants constants;

    @ApiOperation(value = "管理组列表", notes = "根据页码及显示数量来获取管理组信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "toke", required = false, dataType = "String", paramType = "header", defaultValue = ""),
            @ApiImplicitParam(name = "groupParam", value = "管理信息", required = false, dataType = "groupParam")
    })
    @RequestMapping(method = RequestMethod.GET)
    @Rule("ADMIN_GROUP")
    public JSONObject getParam(@RequestHeader(required = false) String Authorization, ActionGroupParam groupParam, @AuthPrincipal Admininfo admininfo) {
        log.info("管理组模块查询开始，请求参数：{}", groupParam);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(constants.getSTATUS(), HttpServletResponse.SC_OK);
        jsonObject.put(constants.getMESSAGE(), "获得管理信息列表");
        jsonObject.put(constants.getITEM(), actionGroupService.listByParam(groupParam));
        jsonObject.put(constants.getCOUNT(), actionGroupService.countByParam(groupParam));
        log.info("管理组模块查询结束");
        return jsonObject;
    }

    @ApiOperation(value = "添加管理组信息", notes = "添加管理组信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "toke", required = false, dataType = "String", paramType = "header", defaultValue = ""),
            @ApiImplicitParam(name = "groupParam", value = "管理组", required = true, dataType = "GroupParam")
    })
    @RequestMapping(method = RequestMethod.POST)
    @Rule("ADMIN_GROUP_ADD")
    public JSONObject add(@RequestHeader(required = false) String Authorization, @RequestBody ActionGroupParam actionGroupParam, @AuthPrincipal Admininfo admininfo) {
        log.info("管理组模块添加开始，请求参数：{}", actionGroupParam);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(constants.getSTATUS(), HttpServletResponse.SC_CREATED);
        ActionGroup actionGroup = ObjectUtils.convert(actionGroupParam, ActionGroup.class);
        jsonObject.put(constants.getDATA(), actionGroupService.save(actionGroup));
        jsonObject.put(constants.getMESSAGE(), "添加管理组成功");

        log.info("管理组模块添加结束");
        return jsonObject;
    }

    @ApiOperation(value = "ID查询", notes = "按管理ID查询管理组信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "toke", required = false, dataType = "String", paramType = "header", defaultValue = ""),
            @ApiImplicitParam(name = "groupId", value = "管理组", required = true, dataType = "Integer", paramType = "path")
    })
    @RequestMapping(value = "{groupId}", method = RequestMethod.GET)
    @Rule("ADMIN_GROUP")
    public JSONObject getById(@RequestHeader(required = false) String Authorization, @PathVariable Integer groupId, @AuthPrincipal Admininfo admininfo) {
        log.info("管理组模块添加开始，请求参数：{}", groupId);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(constants.getSTATUS(), HttpServletResponse.SC_OK);
        jsonObject.put(constants.getMESSAGE(), "管理组信息");
        jsonObject.put(constants.getDATA(), actionGroupService.getById(groupId));
        log.info("管理组模块添加结束");
        return jsonObject;
    }

    @ApiOperation(value = "修改管理组", notes = "修改管理组(所有字段)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "toke", required = false, dataType = "String", paramType = "header", defaultValue = ""),
            @ApiImplicitParam(name = "groupParam", value = "管理组", required = true, dataType = "GroupParam")
    })
    @RequestMapping(method = RequestMethod.PUT)
    @Rule("ADMIN_GROUP_UPDATE")
    public JSONObject updateAllColumn(@RequestHeader(required = false) String Authorization, @RequestBody ActionGroupParam actionGroupParam, @AuthPrincipal Admininfo admininfo) {
        log.info("管理组模块全部修改开始，请求参数：{}", actionGroupParam);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(constants.getSTATUS(), HttpServletResponse.SC_CREATED);
        ActionGroup actionGroup = ObjectUtils.convert(actionGroupParam, ActionGroup.class);
        jsonObject.put(constants.getDATA(), actionGroupService.updateById(actionGroup));
        jsonObject.put(constants.getMESSAGE(), "管理组修改成功");
        log.info("管理组模块全部修改结束");
        return jsonObject;
    }

    @ApiOperation(value = "修改管理组", notes = "修改管理组(所选字段)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "toke", required = false, dataType = "String", paramType = "header", defaultValue = ""),
            @ApiImplicitParam(name = "groupParam", value = "管理组", required = true, dataType = "GroupParam")
    })
    @RequestMapping(method = RequestMethod.PATCH)
    @Rule("ADMIN_GROUP_UPDATE")
    public JSONObject updateSelective(@RequestHeader(required = false) String Authorization, @RequestBody ActionGroupParam actionGroupParam, @AuthPrincipal Admininfo admininfo) {
        log.info("管理组所选字段修改开始，请求参数：{}", actionGroupParam);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(constants.getSTATUS(), HttpServletResponse.SC_CREATED);
        ActionGroup actionGroup = ObjectUtils.convert(actionGroupParam, ActionGroup.class);
        jsonObject.put(constants.getDATA(), actionGroupService.updateById(actionGroup));
        jsonObject.put(constants.getMESSAGE(), "管理员修改成功");
        log.info("管理组所选字段修改结束");
        return jsonObject;
    }

    @ApiOperation(value = "删除管理组", notes = "按管理ID删除管理组")
    @Rule("ADMIN_GROUP_DELETE")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "toke", required = false, dataType = "String", paramType = "header", defaultValue = ""),
            @ApiImplicitParam(name = "groupId", value = "管理", required = true, dataType = "Integer", paramType = "path")
    })
    @RequestMapping(value = "{groupId}", method = RequestMethod.DELETE)
    public JSONObject delete(@RequestHeader(required = false) String Authorization, @PathVariable Integer groupId, @AuthPrincipal Admininfo admininfo) {
        log.info("管理组模块全部修改开始，请求参数：{}", groupId);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(constants.getSTATUS(), HttpServletResponse.SC_NO_CONTENT);
        jsonObject.put(constants.getDATA(), actionGroupService.removeById(groupId));
        jsonObject.put(constants.getMESSAGE(), "管理组删除成功");
        log.info("管理组模块全部修改结束");
        return jsonObject;
    }

    @ApiOperation(value = "全部管理组", notes = "获取全部管理组")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "toke", required = false, dataType = "String", paramType = "header", defaultValue = "")
    })
    @RequestMapping(value = "getAll", method = RequestMethod.GET)
    @Rule("ADMIN_GROUP")
    public JSONObject getAll(@RequestHeader(required = false) String Authorization, @AuthPrincipal Admininfo admininfo) {
        log.info("全部管理组查询开始");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(constants.getSTATUS(), HttpServletResponse.SC_OK);
        jsonObject.put(constants.getMESSAGE(), "获得全部管理组列表");
        ActionGroupParam groupParam = new ActionGroupParam();
        groupParam.setCurrent(1);
        groupParam.setSize(100);
        jsonObject.put(constants.getITEM(), actionGroupService.listByParam(groupParam));
        log.info("全部管理组查询结束");
        return jsonObject;
    }
}
