package com.zrkj.oa.dao;

import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by gaowenfeng on 2017/6/6.
 */

public interface TestDao {
    @Select("select FIRST_ from ACT_ID_USER ")
    List<String> testSelect();
}
