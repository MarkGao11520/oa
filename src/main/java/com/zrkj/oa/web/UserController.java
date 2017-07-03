package com.zrkj.oa.web;

import com.zrkj.oa.core.Result;
import com.zrkj.oa.core.ResultGenerator;
import com.zrkj.oa.model.Login;
import com.zrkj.oa.model.UserProperty;
import com.zrkj.oa.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by gaowenfeng on 2017/7/3.
 */
@RestController
@RequestMapping("userController")
@Scope("prototype")
public class UserController {
    @Autowired
    IUserService iUserService;

    @RequestMapping(value = "loginCheck",produces = "application/json;charset=UTF-8",method = RequestMethod.POST)
    public String loginCheck(Login login,HttpSession session){
        iUserService.loginCheck(login,session);
        return ResultGenerator.genSuccessResult();
    }

    @RequestMapping(value = "addUser",produces = "application/json;charset=UTF-8")
    public String addUser(Login login, UserProperty userProperty){
        iUserService.save(login,userProperty);
        return ResultGenerator.genSuccessResult();
    }

    @RequestMapping(value = "updateUser",produces = "application/json;charset=UTF-8")
    public String updateUser(UserProperty userProperty){
        iUserService.update(userProperty);
        return ResultGenerator.genSuccessResult();
    }

    @RequestMapping(value = "updatePassword",produces = "application/json;charset=UTF-8",method = RequestMethod.POST)
    public String updateUser(String oldPassword, String newPassword, HttpSession session){
        iUserService.updatePassword(oldPassword,newPassword,session);
        return ResultGenerator.genSuccessResult();
    }

    @RequestMapping(value = "getList",produces = "application/json;charset=UTF-8")
    public String getList(Integer did){
        List<UserProperty> list =  iUserService.findUserList(did);
        return ResultGenerator.genSuccessResult(list);
    }
}
