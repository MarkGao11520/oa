package com.zrkj.oa.mapper;

import com.zrkj.oa.model.Condition;

public interface ConditionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Condition record);

    int insertSelective(Condition record);

    Condition selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Condition record);

    int updateByPrimaryKey(Condition record);
}