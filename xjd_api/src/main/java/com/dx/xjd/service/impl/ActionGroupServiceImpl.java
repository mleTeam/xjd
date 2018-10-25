package com.dx.xjd.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dx.xjd.entity.ActionGroup;
import com.dx.xjd.mapper.ActionGroupMapper;
import com.dx.xjd.para.ActionGroupParam;
import com.dx.xjd.service.IActionGroupService;
import com.dx.xjd.util.ObjectUtils;
import com.dx.xjd.util.WrapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 管理组 服务实现类
 * </p>
 *
 * @author XC
 * @since 2018-07-26
 */
@Service
public class ActionGroupServiceImpl extends ServiceImpl<ActionGroupMapper, ActionGroup> implements IActionGroupService {

    @Autowired
    private ActionGroupMapper groupMapper;

    /**
     * 根据请求参数查询列表
     * @param groupParam
     * @return
     */
    public List<ActionGroup> listByParam(ActionGroupParam groupParam){
        QueryWrapper<ActionGroup> wrapper = getWrapper(groupParam);
        WrapperUtil.setOrder(wrapper, groupParam, "group_id");
        Page<ActionGroup> page = WrapperUtil.getPage(groupParam);
        if (ObjectUtils.isNotEmpty(page)) {
            return groupMapper.selectPage(page, wrapper).getRecords();
        } else {
            return groupMapper.selectList(wrapper);
        }
    }

    /**
     * 根据请求参数查询总数
     * @param groupParam
     * @return
     */
    public Integer countByParam(ActionGroupParam groupParam) {
        QueryWrapper<ActionGroup> wrapper = getWrapper(groupParam);
        return groupMapper.selectCount(wrapper);
    }
    /**
     * 自定义查询参数
     * @param param
     * @return
     */
    private QueryWrapper<ActionGroup> getWrapper(ActionGroupParam param){
        QueryWrapper<ActionGroup> wrapper = new QueryWrapper<ActionGroup>();
        if(ObjectUtils.isNotEmpty(param.getGroupName()))
            wrapper.like("group_name", param.getGroupName());
        return wrapper;
    }
}
