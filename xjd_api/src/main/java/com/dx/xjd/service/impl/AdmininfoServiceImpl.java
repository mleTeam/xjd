package com.dx.xjd.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dx.xjd.entity.Admininfo;
import com.dx.xjd.entity.ActionGroup;
import com.dx.xjd.entity.vo.AdmininfoVo;
import com.dx.xjd.mapper.AdmininfoMapper;
import com.dx.xjd.mapper.ActionGroupMapper;
import com.dx.xjd.para.AdmininfoParam;
import com.dx.xjd.service.IAdmininfoService;
import com.dx.xjd.util.ObjectUtils;
import com.dx.xjd.util.WrapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 管理员表 服务实现类
 * </p>
 *
 * @author XC
 * @since 2018-07-26
 */
@Service
public class AdmininfoServiceImpl extends ServiceImpl<AdmininfoMapper, Admininfo> implements IAdmininfoService {

    @Autowired
    private AdmininfoMapper admininfoMapper;

    @Autowired
    private ActionGroupMapper groupMapper;

    public Admininfo login(Admininfo admininfo) {
        QueryWrapper<Admininfo> wrapper = new QueryWrapper<Admininfo>();
        wrapper.eq("admin_name", admininfo.getAdminName());
        wrapper.eq("admin_pwd", admininfo.getAdminPwd());
        return admininfoMapper.selectOne(wrapper);
    }

    /**
     * 根据请求参数查询列表
     * @param admininfoParam
     * @return
     */
    public List<AdmininfoVo> listByParam(AdmininfoParam admininfoParam){
        QueryWrapper<Admininfo> wrapper = getWrapper(admininfoParam);
        WrapperUtil.setOrder(wrapper, admininfoParam, "admin_id");
        Page<Admininfo> page = WrapperUtil.getPage(admininfoParam);
        List<Admininfo> admininfos = null;
        if (ObjectUtils.isNotEmpty(page)) {
            admininfos = admininfoMapper.selectPage(page, wrapper).getRecords();
        } else {
            admininfos =  admininfoMapper.selectList(wrapper);
        }
        List<AdmininfoVo> admininfoVos = new ArrayList<AdmininfoVo>();
        for (int i = 0; i < admininfos.size(); i++){
            Admininfo admininfo = admininfos.get(i);
            AdmininfoVo admininfoVo = ObjectUtils.convert(admininfo, AdmininfoVo.class);
            if(admininfo != null && ObjectUtils.isNotEmpty(admininfo.getGroupId())){
                QueryWrapper<ActionGroup> groupWrapper = new QueryWrapper();
                groupWrapper.in("group_id", admininfos.get(i).getGroupId());
                List<ActionGroup> groups = groupMapper.selectList(groupWrapper);
                String groupName = "";
                for (int j = 0; j < groups.size(); j++){
                    ActionGroup group = groupMapper.selectById(groups.get(j).getGroupId());
                    groupName = groupName + group.getGroupName() + ",";
                }
                if(ObjectUtils.isNotEmpty(groupName)){
                    groupName = groupName.substring(0, groupName.length()-1);
                }
                admininfoVo.setGroupName(groupName);
            }
            admininfoVos.add(admininfoVo);
        }
        return admininfoVos;
    }

    /**
     * 根据请求参数查询总数
     * @param admininfoParam
     * @return
     */
    public Integer countByParam(AdmininfoParam admininfoParam){
        QueryWrapper<Admininfo> wrapper = getWrapper(admininfoParam);
        return admininfoMapper.selectCount(wrapper);
    }

    /**
     * 自定义查询参数
     * @param param
     * @return
     */
    private QueryWrapper<Admininfo> getWrapper(AdmininfoParam param){
        QueryWrapper<Admininfo> wrapper = new QueryWrapper<Admininfo>();
        if(ObjectUtils.isNotEmpty(param.getAdminDesc()))
            wrapper.like("admin_desc", param.getAdminDesc());
        return wrapper;
    }

}
