package com.zrkj.oa.service.impl;

import com.alibaba.fastjson.JSON;
import com.zrkj.oa.core.ServiceException;
import com.zrkj.oa.mapper.CustomFormMapper;
import com.zrkj.oa.model.CustomForm;
import com.zrkj.oa.service.IConditionService;
import com.zrkj.oa.service.ICustomFormService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by gaowenfeng on 2017/7/4.
 */
@Service
@Scope("prototype")
public class CustomServiceImpl implements ICustomFormService {
    @Autowired
    CustomFormMapper customFormMapper;
    @Autowired
    RepositoryService repositoryService;

    public void save(List<CustomForm> list) {
        if (customFormMapper.insertBatch(list) != list.size())
            throw new ServiceException("插入异常");
    }

    @Transactional
    public void update(List<CustomForm> list) {
        for (CustomForm customForm : list) {
            if (customForm.getId() == null || customFormMapper.selectByPrimaryKey(customForm.getId()) == null) {
                if (customFormMapper.insertSelective(customForm) != 1)
                    throw new ServiceException("添加异常");
            } else {
                if (customFormMapper.updateByPrimaryKeySelective(customForm) != 1)
                    throw new ServiceException("修改异常");
            }

        }

    }

    public void delete(Integer id) {
        CustomForm customForm = new CustomForm();
        customForm.setId(id);
        customForm.setIsdel(1);
        if (customFormMapper.selectByPrimaryKey(id) != null) {
            if (customFormMapper.updateByPrimaryKeySelective(customForm) != 1)
                throw new ServiceException("删除异常");
        }
    }

    public List<com.zrkj.oa.model.ProcessDefinition> getProDefList() {
        List<ProcessDefinition> list = null;
        List<com.zrkj.oa.model.ProcessDefinition> list1 = new ArrayList<com.zrkj.oa.model.ProcessDefinition>();
        list = (list = repositoryService.createProcessDefinitionQuery().list()) == null ? Collections.<ProcessDefinition>emptyList() : list;
        for (ProcessDefinition p : list) {
            com.zrkj.oa.model.ProcessDefinition definition = new com.zrkj.oa.model.ProcessDefinition();
            definition.setCategory(p.getCategory());
            definition.setId(p.getId());
            if (p.getName() == null || p.getName() == "" || p.getName().equals(""))
                definition.setName(repositoryService.createDeploymentQuery().deploymentId(p.getDeploymentId()).list().get(0).getName());
            else
                definition.setName(p.getName());

            definition.setKey(p.getKey());
            definition.setDescription(p.getDescription());
            definition.setDeploymentId(p.getDeploymentId());
            definition.setDiagramResourceName(p.getDiagramResourceName());
            definition.setTenantId(p.getTenantId());
            definition.setVersion(p.getVersion());
            definition.setResourceName(p.getResourceName());
            list1.add(definition);
        }
        return list1;
    }

    public List<CustomForm> getListByProDefId(String prodefId) {
        return customFormMapper.selectByProcessDefineId(prodefId);
    }
}
