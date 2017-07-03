package com.zrkj.oa.mapper;

import com.zrkj.oa.model.UserProperty;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserPropertyMapper {
    int deleteByPrimaryKey(Integer uid);

    int insert(UserProperty record);

    int insertSelective(UserProperty record);

    UserProperty selectByPrimaryKey(Integer uid);

    int updateByPrimaryKeySelective(UserProperty record);

    int updateByPrimaryKey(UserProperty record);

    List<UserProperty> selectUserList(@Param("did") Integer did);
}