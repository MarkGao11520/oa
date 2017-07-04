package com.zrkj.oa.service;

import com.zrkj.oa.model.Condition;
import com.zrkj.oa.model.Depart;

import java.util.List;

/**
 * Created by gaowenfeng on 2017/7/4.
 */
public interface IConditionService {
    /**
     * 添加
     * @param condition
     */
    void save(Condition condition);

    /**
     * 修改
     * @param condition
     */
    void update(Condition condition);

    /**
     * 删除
     * @param ids
     */
    void delete(Integer[] ids);

    /**
     * 获取列表
     * @return
     */
    List<Condition> findList();
}
