package com.zrkj.oa.service;

import com.zrkj.oa.model.CustomForm;
import org.activiti.engine.repository.ProcessDefinition;

import java.util.List;

/**
 * Created by gaowenfeng on 2017/7/4.
 */
public interface ICustomFormService {
    /**
     * 批量添加
     * @param list
     */
    void save(List<CustomForm> list);

    /**
     * 批量编辑
     * @param list
     */
    void update(List<CustomForm> list);

    /**
     * 删除
     * @param id
     */
    void delete(Integer id);

    /**
     * 获取流程定义列表
     * @return
     */
    List<com.zrkj.oa.model.ProcessDefinition> getProDefList();

    /**
     * 根据流程定义id获取自定义工单
     * @param prodefId
     * @return
     */
    List<CustomForm> getListByProDefId(String prodefId);
}
