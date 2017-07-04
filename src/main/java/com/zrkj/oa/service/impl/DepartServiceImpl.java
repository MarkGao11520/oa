package com.zrkj.oa.service.impl;

import com.zrkj.oa.core.ServiceException;
import com.zrkj.oa.mapper.DepartMapper;
import com.zrkj.oa.model.Depart;
import com.zrkj.oa.service.IDepartService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by gaowenfeng on 2017/7/3.
 */
@Service
@Scope("prototype")
public class DepartServiceImpl implements IDepartService {
    @Autowired
    DepartMapper departMapper;
    @Autowired
    IdentityService identityService;

    @Transactional
    public void save(Depart depart) {
        if(departMapper.selectByName(depart.getName())!=null)
            throw new ServiceException("部门已经存在");
        if(departMapper.insertSelective(depart)!=1)
            throw new ServiceException("添加失败");
        else
            createGroup(depart.getId().toString(),depart.getName());
    }

    public void update(Depart depart) {
        if(departMapper.updateByPrimaryKeySelective(depart)!=1)
            throw new ServiceException("编辑失败");
    }

    public void delete(Integer[] ids) {
        if(departMapper.deleteBatch(ids)!=ids.length)
            throw new ServiceException("删除失败");
    }

    public List<Depart> findList() {
        return departMapper.selectList();
    }

    // 将用户组数据保存到数据库中
    private void createGroup(String id,
                     String name) {
        // 调用newGroup方法创建Group实例
        Group group = identityService.newGroup(id);
        group.setName(name);
        identityService.saveGroup(group);
    }
}
