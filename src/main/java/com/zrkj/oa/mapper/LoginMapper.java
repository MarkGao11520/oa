package com.zrkj.oa.mapper;

import com.zrkj.oa.model.Login;
import org.apache.ibatis.annotations.Select;

public interface LoginMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Login record);

    int insertSelective(Login record);

    Login selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Login record);

    int updateByPrimaryKey(Login record);

    @Select("select * from tb_login where username = #{username} and password = #{password}")
    Login selectByLogin(Login login);
}