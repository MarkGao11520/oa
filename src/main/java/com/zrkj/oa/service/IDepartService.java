package com.zrkj.oa.service;

import com.zrkj.oa.model.Depart;

import java.util.List;

/**
 * Created by gaowenfeng on 2017/7/3.
 */
public interface IDepartService {
    void save(Depart depart);

    void update(Depart depart);

    void delete(Integer[] ids);

    List<Depart> findList();
}
