package com.zrkj.oa.mapper;

import com.zrkj.oa.model.CustomForm;

public interface CustomFormMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CustomForm record);

    int insertSelective(CustomForm record);

    CustomForm selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CustomForm record);

    int updateByPrimaryKey(CustomForm record);
}