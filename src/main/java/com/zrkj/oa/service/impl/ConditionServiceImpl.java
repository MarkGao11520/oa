package com.zrkj.oa.service.impl;

import com.zrkj.oa.core.ServiceException;
import com.zrkj.oa.mapper.ConditionMapper;
import com.zrkj.oa.model.Condition;
import com.zrkj.oa.service.IConditionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * Created by gaowenfeng on 2017/7/4.
 */
@Service
@Scope("prototype")
public class ConditionServiceImpl implements IConditionService{
    @Autowired
    ConditionMapper conditionMapper;

    public void save(Condition condition) {
        if(conditionMapper.selectByName(condition.getName())!=null)
            throw new ServiceException("该条件已经存在");
        if(conditionMapper.insertSelective(condition)!=1)
            throw new ServiceException("添加条件失败");
    }

    public void update(Condition condition) {
        if(conditionMapper.updateByPrimaryKeySelective(condition)!=1)
            throw new ServiceException("编辑条件失败");
    }

    public void delete(Integer[] ids) {
        if(conditionMapper.deleteBatch(ids)!=ids.length)
            throw new ServiceException("删除失败");
    }

    public List<Condition> findList() {
        List<Condition> list = null;
        return (list = conditionMapper.selectList())==null? Collections.<Condition>emptyList():list;
    }
}
