package com.zrkj.oa.mapper;

import com.zrkj.oa.model.CustomForm;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CustomFormMapper {
    int deleteByPrimaryKey(Integer id);

    /**
     * 批量添加
     * @param list
     * @return
     */
    int insertBatch(@Param("list") List<CustomForm> list);

    int insertSelective(CustomForm record);

    CustomForm selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CustomForm record);

    int updateByPrimaryKey(CustomForm record);

    /**
     * 根据条件定义查找
     * @param pro_def_id
     * @return
     */
    List<CustomForm> selectByProcessDefineId(String pro_def_id);
}