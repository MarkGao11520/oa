package com.zrkj.oa.mapper;

import com.zrkj.oa.model.Condition;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ConditionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Condition record);

    int insertSelective(Condition record);

    Condition selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Condition record);

    int updateByPrimaryKey(Condition record);

    /**
     * 获取列表
     * @return
     */
    List<Condition> selectList();

    Condition selectByName(String name);

    /**
     * 批量删除
     * @param ids
     * @return
     */
    int deleteBatch(@Param("list")Integer[] ids);
}