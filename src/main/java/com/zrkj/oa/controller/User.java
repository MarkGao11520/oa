package com.zrkj.oa.controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * Created by gaowenfeng on 2017/6/13.
 */
//@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler"})
public class User  {
    private String id;
    private String name;

    public User(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
