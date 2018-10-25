package com.dx.xjd.controller.manage;


import com.alibaba.fastjson.JSONObject;
import com.dx.xjd.annotation.AuthPrincipal;
import com.dx.xjd.annotation.Rule;
import com.dx.xjd.entity.Admininfo;
import com.dx.xjd.entity.Dept;
import com.dx.xjd.para.DeptParam;
import com.dx.xjd.service.IDeptService;
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
import java.util.*;

@Api(tags = { "部门接口" })
@RestController
@RequestMapping("/manage/dept")
@Slf4j
public class DeptController {

    @Autowired
    private IDeptService deptService;

    @Autowired
    private Constants constants;

    @ApiOperation(value = "部门列表", notes = "根据页码及显示数量来获取部门信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "toke", required = false, dataType = "String", paramType = "header", defaultValue = ""),
            @ApiImplicitParam(name = "deptParam", value = "部门信息", required = false, dataType = "deptParam")
    })
    @RequestMapping(method = RequestMethod.GET)
    @Rule("ADMIN_DEPT")
    public JSONObject getParam(@RequestHeader(required = false) String Authorization, DeptParam deptParam, @AuthPrincipal Admininfo admininfo) {
        log.info("部门查询开始，请求参数：{}", deptParam);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(constants.getSTATUS(), HttpServletResponse.SC_OK);
        jsonObject.put(constants.getMESSAGE(), "获得部门信息列表");
        jsonObject.put(constants.getITEM(), deptService.listByParam(deptParam));
        jsonObject.put(constants.getCOUNT(), deptService.countByParam(deptParam));
        log.info("部门查询结束");
        return jsonObject;
    }

    @ApiOperation(value = "添加部门信息", notes = "添加部门信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "toke", required = false, dataType = "String", paramType = "header", defaultValue = ""),
            @ApiImplicitParam(name = "deptParam", value = "部门", required = true, dataType = "DeptParam")
    })
    @RequestMapping(method = RequestMethod.POST)
    @Rule("ADMIN_DEPT_ADD")
    public JSONObject add(@RequestHeader(required = false) String Authorization, @RequestBody @Valid DeptParam deptParam, @AuthPrincipal Admininfo admininfo) {
        log.info("部门添加开始，请求参数：{}", deptParam);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(constants.getSTATUS(), HttpServletResponse.SC_CREATED);
        Dept dept = ObjectUtils.convert(deptParam, Dept.class);
        jsonObject.put(constants.getDATA(), deptService.save(dept));
        jsonObject.put(constants.getMESSAGE(), "添加部门成功");
        log.info("部门添加结束");
        return jsonObject;
    }

    @ApiOperation(value = "ID查询", notes = "按部门ID查询部门信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "toke", required = false, dataType = "String", paramType = "header", defaultValue = ""),
            @ApiImplicitParam(name = "deptId", value = "部门", required = true, dataType = "Integer", paramType = "path")
    })
    @RequestMapping(value = "{deptId}", method = RequestMethod.GET)
    @Rule("ADMIN_DEPT")
    public JSONObject getById(@RequestHeader(required = false) String Authorization, @PathVariable Integer deptId, @AuthPrincipal Admininfo admininfo) {
        log.info("部门添加开始，请求参数：{}", deptId);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(constants.getSTATUS(), HttpServletResponse.SC_OK);
        jsonObject.put(constants.getMESSAGE(), "部门信息");
        jsonObject.put(constants.getDATA(), deptService.getById(deptId));
        log.info("部门添加结束");
        return jsonObject;
    }

    @ApiOperation(value = "修改部门", notes = "修改部门(所有字段)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "toke", required = false, dataType = "String", paramType = "header", defaultValue = ""),
            @ApiImplicitParam(name = "deptParam", value = "部门", required = true, dataType = "DeptParam")
    })
    @RequestMapping(method = RequestMethod.PUT)
    @Rule("ADMIN_DEPT_UPDATE")
    public JSONObject updateAllColumn(@RequestHeader(required = false) String Authorization, @RequestBody @Valid DeptParam deptParam, @AuthPrincipal Admininfo admininfo) {
        log.info("部门全部修改开始，请求参数：{}", deptParam);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(constants.getSTATUS(), HttpServletResponse.SC_CREATED);
        Dept dept = ObjectUtils.convert(deptParam, Dept.class);
        jsonObject.put(constants.getDATA(), deptService.updateById(dept));
        jsonObject.put(constants.getMESSAGE(), "部门修改成功");
        log.info("部门全部修改结束");
        return jsonObject;
    }

    @ApiOperation(value = "修改部门", notes = "修改部门(所选字段)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "toke", required = false, dataType = "String", paramType = "header", defaultValue = ""),
            @ApiImplicitParam(name = "deptParam", value = "部门", required = true, dataType = "DeptParam")
    })
    @RequestMapping(method = RequestMethod.PATCH)
    @Rule("ADMIN_DEPT_UPDATE")
    public JSONObject updateSelective(@RequestHeader(required = false) String Authorization, @RequestBody DeptParam deptParam, @AuthPrincipal Admininfo admininfo) {
        log.info("部门所选字段修改开始，请求参数：{}", deptParam);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(constants.getSTATUS(), HttpServletResponse.SC_CREATED);
        Dept dept = ObjectUtils.convert(deptParam, Dept.class);
        jsonObject.put(constants.getDATA(), deptService.updateById(dept));
        jsonObject.put(constants.getMESSAGE(), "部门修改所选字段成功");
        log.info("部门所选字段修改结束");
        return jsonObject;
    }

    @ApiOperation(value = "删除部门", notes = "按部门ID删除部门")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "toke", required = false, dataType = "String", paramType = "header", defaultValue = ""),
            @ApiImplicitParam(name = "deptId", value = "部门", required = true, dataType = "Integer", paramType = "path")
    })
    @RequestMapping(value = "{deptId}", method = RequestMethod.DELETE)
    @Rule("ADMIN_DEPT_DELETE")
    public JSONObject delete(@RequestHeader(required = false) String Authorization, @PathVariable Integer deptId, @AuthPrincipal Admininfo admininfo) {
        log.info("部门全部修改开始，请求参数：{}", deptId);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(constants.getSTATUS(), HttpServletResponse.SC_NO_CONTENT);
        jsonObject.put(constants.getDATA(), deptService.removeById(deptId));
        jsonObject.put(constants.getMESSAGE(), "部门删除成功");
        log.info("部门全部修改结束");
        return jsonObject;
    }

    @ApiOperation(value = "部门全部列表", notes = "获取全部部门")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "toke", required = false, dataType = "String", paramType = "header", defaultValue = "")
    })
    @RequestMapping(value = "getAll", method = RequestMethod.GET)
    @Rule("ADMIN_DEPT")
    public JSONObject getAll(@RequestHeader(required = false) String Authorization, @AuthPrincipal Admininfo admininfo) {
        log.info("部门全部查询开始");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(constants.getSTATUS(), HttpServletResponse.SC_OK);
        jsonObject.put(constants.getMESSAGE(), "获得部门全部列表");
        log.info("部门全部查询开始,递归开始时间==" + System.currentTimeMillis());
        List<Map<String, Object>> list = getChild(1);
        log.info("部门全部查询开始,递归结束时间==" + System.currentTimeMillis());
        jsonObject.put(constants.getITEM(), list);
        log.info("部门全部查询结束");
        return jsonObject;
    }

    @ApiOperation(value = "上级部门集合", notes = "获取该部门的全部上级集合")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "toke", required = false, dataType = "String", paramType = "header", defaultValue = "")
    })
    @RequestMapping(value = "getParentId/{deptId}", method = RequestMethod.GET)
    @Rule("ADMIN_DEPT")
    public JSONObject getParentId(@RequestHeader(required = false) String Authorization, @PathVariable Integer deptId, @AuthPrincipal Admininfo admininfo) {
        log.info("上级部门集合开始");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(constants.getSTATUS(), HttpServletResponse.SC_OK);
        jsonObject.put(constants.getMESSAGE(), "获得部门全部列表");
        List<Integer> list = new ArrayList();
        log.info("上级部门集合开始,递归开始时间==" + System.currentTimeMillis());
        getParentId(list, deptId);
        Collections.reverse(list);
        log.info("上级部门集合开始,递归结束时间==" + System.currentTimeMillis());
        jsonObject.put(constants.getDATA(), list);
        log.info("上级部门集合结束");
        return jsonObject;
    }

    /**
     * 封装部门树
     * @param parentId
     * @return
     */
    private List<Map<String, Object>> getChild(Integer parentId){
        List<Map<String, Object>> listMap = new ArrayList();
        log.info("部门全部查询开始,递归parentId==" + parentId);
        DeptParam deptParam = new DeptParam();
        deptParam.setDeptParentId(parentId);
        deptParam.setCurrent(1);
        deptParam.setSize(1000);
        List<Dept> deptList = deptService.listByParam(deptParam);
        for(Dept dept : deptList){
            deptParam.setDeptParentId(dept.getDeptId());
            List<Dept> deptChildList = deptService.listByParam(deptParam);//把父类的编号传入,查询改父亲下的子类
            Map tempMap = new HashMap();
            tempMap.put("value", dept.getDeptId());
            tempMap.put("label", dept.getDeptName());
            if(deptChildList.size() > 0){//如果该设备还有孩子,继续做查询,直到设备没有孩子,也就是最后一个节点
                tempMap.put("children", getChild(dept.getDeptId()));
            }
            listMap.add(tempMap);
        }
        return listMap;
    }

    private void getParentId(List<Integer> list, Integer deptId){
        log.info("上级部门查询开始,递归deptId==" + deptId);
        Dept dept = deptService.getById(deptId);
        if(dept != null){
            list.add(dept.getDeptParentId());
            getParentId(list, dept.getDeptParentId());
        }
    }

}
