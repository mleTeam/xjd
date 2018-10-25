package com.dx.xjd.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dx.xjd.entity.Dept;
import com.dx.xjd.mapper.DeptMapper;
import com.dx.xjd.para.DeptParam;
import com.dx.xjd.service.IDeptService;
import com.dx.xjd.util.ObjectUtils;
import com.dx.xjd.util.WrapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author XC
 * @since 2018-07-26
 */
@Service
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements IDeptService {

    @Autowired
    private DeptMapper deptMapper;

    /**
     * 根据请求参数查询列表
     * @param deptParam
     * @return
     */
    public List<Dept> listByParam(DeptParam deptParam){
        QueryWrapper<Dept> wrapper = getWrapper(deptParam);
        WrapperUtil.setOrder(wrapper, deptParam, "dept_id");
        Page<Dept> page = WrapperUtil.getPage(deptParam);
        if (ObjectUtils.isNotEmpty(page)) {
            return deptMapper.selectPage(page, wrapper).getRecords();
        } else {
            return deptMapper.selectList(wrapper);
        }
    }

    /**
     * 根据请求参数查询总数
     * @param deptParam
     * @return
     */
    public Integer countByParam(DeptParam deptParam){
        QueryWrapper<Dept> wrapper = getWrapper(deptParam);
        return deptMapper.selectCount(wrapper);
    }

    /**
     * 自定义查询参数
     * @param deptParam
     * @return
     */
    private QueryWrapper<Dept> getWrapper(DeptParam deptParam){
        QueryWrapper<Dept> wrapper = new QueryWrapper<Dept>();
        if(ObjectUtils.isNotEmpty(deptParam.getDeptName()))
            wrapper.like("dept_name", deptParam.getDeptName());
        if(ObjectUtils.isNotEmpty(deptParam.getDeptParentId()))
            wrapper.eq("dept_parent_id", deptParam.getDeptParentId());
        return wrapper;
    }
}
