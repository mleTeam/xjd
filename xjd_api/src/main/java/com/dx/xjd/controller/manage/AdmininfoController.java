package com.dx.xjd.controller.manage;


import com.alibaba.fastjson.JSONObject;
import com.dx.xjd.annotation.AuthPrincipal;
import com.dx.xjd.annotation.Rule;
import com.dx.xjd.entity.Admininfo;
import com.dx.xjd.para.AdmininfoParam;
import com.dx.xjd.service.IAdmininfoService;
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
import java.util.Map;

@Api(tags = { "管理员接口" })
@RestController
@RequestMapping("/manage/admininfo")
@Slf4j
public class AdmininfoController {

    @Autowired
    private IAdmininfoService admininfoService;

    @Autowired
    private Constants constants;

    @ApiOperation(value = "管理员列表", notes = "根据页码及显示数量来获取管理员")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "toke", required = false, dataType = "String", paramType = "header", defaultValue = ""),
            @ApiImplicitParam(name = "admininfoParam", value = "管理员", required = false, dataType = "admininfoParam")
    })
    @RequestMapping(method = RequestMethod.GET)
    @Rule("ADMIN_ADMIN")
    public JSONObject getParam(@RequestHeader(required = false) String Authorization, AdmininfoParam admininfoParam, @AuthPrincipal Admininfo admininfo) {
        log.info("管理员查询开始，请求参数：{}", admininfoParam);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(constants.getSTATUS(), HttpServletResponse.SC_OK);
        jsonObject.put(constants.getMESSAGE(), "获得管理员列表");
        jsonObject.put(constants.getITEM(), admininfoService.listByParam(admininfoParam));
        jsonObject.put(constants.getCOUNT(), admininfoService.countByParam(admininfoParam));
        log.info("管理员查询结束");
        return jsonObject;
    }

    @ApiOperation(value = "添加管理员", notes = "添加管理员信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "toke", required = false, dataType = "String", paramType = "header", defaultValue = ""),
            @ApiImplicitParam(name = "admininfoParam", value = "管理员", required = true, dataType = "AdmininfoParam")
    })
    @RequestMapping(method = RequestMethod.POST)
    @Rule("ADMIN_ADMIN_ADD")
    public JSONObject add(@RequestHeader(required = false) String Authorization, @RequestBody AdmininfoParam admininfoParam, @AuthPrincipal Admininfo admininfo) {
        log.info("管理员添加开始，请求参数：{}", admininfoParam);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(constants.getSTATUS(), HttpServletResponse.SC_CREATED);
        Admininfo newAdmininfo = ObjectUtils.convert(admininfoParam, Admininfo.class);
        jsonObject.put(constants.getDATA(), admininfoService.save(newAdmininfo));
        jsonObject.put(constants.getMESSAGE(), "添加管理员成功");
        log.info("管理员添加结束");
        return jsonObject;
    }

    @ApiOperation(value = "ID查询", notes = "按管理员ID查询管理员信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "toke", required = false, dataType = "String", paramType = "header", defaultValue = ""),
            @ApiImplicitParam(name = "admininfoId", value = "ID", required = true, dataType = "Integer", paramType = "path")
    })
    @RequestMapping(value = "{admininfoId}", method = RequestMethod.GET)
    @Rule("ADMIN_ADMIN")
    public JSONObject getById(@RequestHeader(required = false) String Authorization, @PathVariable Integer admininfoId, @AuthPrincipal Admininfo admininfo) {
        log.info("管理员ID查询添加开始，请求参数：{}", admininfoId);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(constants.getSTATUS(), HttpServletResponse.SC_OK);
        jsonObject.put(constants.getDATA(), admininfoService.getById(admininfoId));
        jsonObject.put(constants.getMESSAGE(), "管理员信息");
        log.info("管理员ID查询添加结束");
        return jsonObject;
    }

    @ApiOperation(value = "修改管理员", notes = "修改管理员(所有字段)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "toke", required = false, dataType = "String", paramType = "header", defaultValue = ""),
            @ApiImplicitParam(name = "admininfoParam", value = "管理员", required = true, dataType = "AdmininfoParam")
    })
    @RequestMapping(method = RequestMethod.PUT)
    @Rule("ADMIN_ADMIN_UPDATE")
    public JSONObject updateAllColumn(@RequestHeader(required = false) String Authorization, @RequestBody AdmininfoParam admininfoParam, @AuthPrincipal Admininfo admininfo) {
        log.info("管理员全部修改开始，请求参数：{}", admininfoParam);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(constants.getSTATUS(), HttpServletResponse.SC_CREATED);
        Admininfo newAdmininfo = ObjectUtils.convert(admininfoParam, Admininfo.class);
        jsonObject.put(constants.getDATA(), admininfoService.updateById(newAdmininfo));
        jsonObject.put(constants.getMESSAGE(), "管理员修改所有字段成功");
        log.info("管理员全部修改结束");
        return jsonObject;
    }

    @ApiOperation(value = "修改管理员", notes = "修改管理员(所选字段)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "toke", required = false, dataType = "String", paramType = "header", defaultValue = ""),
            @ApiImplicitParam(name = "admininfoParam", value = "管理员", required = true, dataType = "AdmininfoParam")
    })
    @RequestMapping(method = RequestMethod.PATCH)
    @Rule("ADMIN_ADMIN_UPDATE")
    public JSONObject updateSelective(@RequestHeader(required = false) String Authorization, @RequestBody AdmininfoParam admininfoParam, @AuthPrincipal Admininfo admininfo) {
        log.info("管理员所选字段修改开始，请求参数：{}", admininfoParam);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(constants.getSTATUS(), HttpServletResponse.SC_CREATED);
        Admininfo newAdmininfo = ObjectUtils.convert(admininfoParam, Admininfo.class);
        jsonObject.put(constants.getDATA(), admininfoService.updateById(newAdmininfo));
        jsonObject.put(constants.getMESSAGE(), "管理员修改所选字段成功");
        log.info("管理员所选字段修改结束");
        return jsonObject;
    }

    @ApiOperation(value = "删除管理员", notes = "按管理员ID删除管理员")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "toke", required = false, dataType = "String", paramType = "header", defaultValue = ""),
            @ApiImplicitParam(name = "admininfoId", value = "管理员ID", required = true, dataType = "Integer", paramType = "path")
    })
    @RequestMapping(value = "{admininfoId}", method = RequestMethod.DELETE)
    @Rule("ADMIN_ADMIN_DELETE")
    public JSONObject delete(@RequestHeader(required = false) String Authorization, @PathVariable Integer admininfoId, @AuthPrincipal Admininfo admininfo) {
        log.info("管理员删除开始，请求参数：{}", admininfoId);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(constants.getSTATUS(), HttpServletResponse.SC_NO_CONTENT);
        jsonObject.put(constants.getDATA(), admininfoService.removeById(admininfoId));
        jsonObject.put(constants.getMESSAGE(), "管理员删除成功");
        log.info("管理员删除结束");
        return jsonObject;
    }

}
