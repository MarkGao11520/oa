package com.zrkj.oa.web;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by gaowenfeng on 2017/7/3.
 */
@Controller
@Scope("prototype")
public class PageController {
    @RequestMapping("/")
    public String index(){
        return "index";
    }

    @RequestMapping("/activiti_process_define")
    public void activiti_process_define(){}

    @RequestMapping("/modellist")
    public void modellist(){}

    @RequestMapping("/login")
    public void login(){}

    @RequestMapping("/depart")
    public void depart(){}

    @RequestMapping("/user")
    public void user(){}

    @RequestMapping("/condition")
    public void condition(){}
}
