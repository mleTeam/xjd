package com.dx.xjd.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dx.xjd.entity.Action;
import com.dx.xjd.entity.Admininfo;
import com.dx.xjd.entity.ActionGroup;
import com.dx.xjd.mapper.ActionMapper;
import com.dx.xjd.mapper.AdmininfoMapper;
import com.dx.xjd.mapper.ActionGroupMapper;
import com.dx.xjd.para.ActionParam;
import com.dx.xjd.service.IActionService;
import com.dx.xjd.util.ObjectUtils;
import com.dx.xjd.util.WrapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 功能模块表 服务实现类
 * </p>
 *
 * @author XC
 * @since 2018-07-26
 */
@Service
public class ActionServiceImpl extends ServiceImpl<ActionMapper, Action> implements IActionService {

    @Autowired
    private ActionMapper actionMapper;

    @Autowired
    private AdmininfoMapper admininfoMapper;

    @Autowired
    private ActionGroupMapper groupMapper;

    /**
     * 根据请求参数查询列表
     * @param actionParam
     * @return
     */
    public List<Action> listByParam(ActionParam actionParam){
        QueryWrapper<Action> queryWrapper = getWrapper(actionParam);
        WrapperUtil.setOrder(queryWrapper, actionParam, "action_id");
        Page<Action> page = WrapperUtil.getPage(actionParam);
        if (ObjectUtils.isNotEmpty(page)) {
            return actionMapper.selectPage(page, queryWrapper).getRecords();
        } else {
            return actionMapper.selectList(queryWrapper);
        }
    }

    /**
     * 根据请求参数查询总数
     * @param actionParam
     * @return
     */
    public Integer countByParam(ActionParam actionParam){
        QueryWrapper<Action> queryWrapper = getWrapper(actionParam);
        return actionMapper.selectCount(queryWrapper);
    }

    /**
     * 获取管理员的功能模块
     * @param adminId
     * @return
     */
    public List<Action> listActionByAdminId(Integer adminId){
        List<Action> set = new ArrayList<Action>();

        //查询管理信息
        Admininfo admininfo = admininfoMapper.selectById(adminId);

        //查询管理所有的角色
        QueryWrapper<ActionGroup> groupWwrapper = new QueryWrapper<ActionGroup>();
        groupWwrapper.in("group_id", admininfo.getGroupId());
        List<ActionGroup> groups = groupMapper.selectList(groupWwrapper);

        //查询所有角色的权限
        for (ActionGroup group : groups){
            QueryWrapper<Action> actionWwrapper = new QueryWrapper<Action>();
            actionWwrapper.in("action_id", group.getGroupCheckAction() + "," + group.getGroupHalfCheckAction());
            actionWwrapper.orderByAsc("action_parentId");
            actionWwrapper.orderByAsc("action_sort");
            List<Action> actions = actionMapper.selectList(actionWwrapper);
            for (Action action: actions) {
                boolean flag = true;
                for (Action actionTemp: set){
                    if(actionTemp.getActionId().equals(action.getActionId()))
                        flag = false;
                }
                if(flag)
                    set.add(action);
            }
        }

        return set;
    }

    /**
     * 自定义查询参数
     * @param param
     * @return
     */
    private QueryWrapper<Action> getWrapper(ActionParam param){
        QueryWrapper<Action> wrapper = new QueryWrapper<Action>();
        if(ObjectUtils.isNotEmpty(param.getActionName())){
            wrapper.like("action_name", param.getActionName());
        }
        if(ObjectUtils.isNotEmpty(param.getActionUrl())){
            wrapper.like("action_url", param.getActionUrl());
        }
        if(ObjectUtils.isNotEmpty(param.getActionIsButton())){
            wrapper.eq("action_isButton", param.getActionIsButton());
        }
        if(ObjectUtils.isNotEmpty(param.getActionParentId())){
            wrapper.eq("action_parentId", param.getActionParentId());
        }
        return wrapper;
    }

}
