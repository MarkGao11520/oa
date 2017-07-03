package com.zrkj.oa.mapper;

import com.zrkj.oa.model.Depart;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DepartMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Depart record);

    int insertSelective(Depart record);

    Depart selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Depart record);

    int updateByPrimaryKey(Depart record);

    int deleteBatch(@Param("list") Integer[] list);

    List<Depart> selectList();
}