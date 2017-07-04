package com.zrkj.oa.service.impl;

import com.zrkj.oa.core.ServiceException;
import com.zrkj.oa.mapper.LoginMapper;
import com.zrkj.oa.mapper.UserPropertyMapper;
import com.zrkj.oa.model.Login;
import com.zrkj.oa.model.UserProperty;
import com.zrkj.oa.service.IUserService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.rmi.runtime.Log;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by gaowenfeng on 2017/7/3.
 */
@Service
@Scope("prototype")
public class UserServiceImpl implements IUserService{
    @Autowired
    UserPropertyMapper userPropertyMapper;
    @Autowired
    LoginMapper loginMapper;
    @Autowired
    IdentityService identityService;

    public void loginCheck(Login login,HttpSession session) {
        Login login1 = null;
        if((login1 = loginMapper.selectByLogin(login))==null)
            throw new ServiceException("账号密码错误");
        else {
            session.setAttribute("login",login1);
        }
    }

    @Transactional
    public void save(Login login, UserProperty user) {
        if(loginMapper.selectByUserName(login.getUsername())!=null)
            throw new ServiceException("改用户已经存在");
        login.setPassword("66666666");
        if(loginMapper.insertSelective(login)==1){
            user.setUid(login.getId());
            if(userPropertyMapper.insertSelective(user)!=1)
                throw new ServiceException("添加用户异常");
            else
                creatUser(login, user);
        }else
            throw new ServiceException("添加用户异常");
    }

    public void update(UserProperty user) {
        if(userPropertyMapper.updateByPrimaryKeySelective(user)!=1)
            throw new ServiceException("修改用户异常");
    }

    public void updatePassword(String oldPassword, String newPassword, HttpSession session) {
        Login login = (Login) session.getAttribute("login");
        if(login.getPassword()==oldPassword&&login.getPassword().equals(oldPassword))
        {
            login.setPassword(newPassword);
            if(loginMapper.updateByPrimaryKeySelective(login)!=1)
                throw new ServiceException("修改密码异常");
        }else
            throw new ServiceException("原密码错误");
    }

    public List<UserProperty> findUserList(Integer did) {
        return userPropertyMapper.selectUserList(did);
    }


    //创建用户方法
    @Transactional
    private void creatUser(Login login,UserProperty userProperty) {
        // 使用newUser方法创建User实例
        User user = identityService.newUser(login.getId().toString());
        // 设置用户的各个属性
        user.setFirstName(userProperty.getRealname());
        user.setLastName(login.getUsername());
        user.setEmail(userProperty.getMobile());
        // 使用saveUser方法保存用户
        identityService.saveUser(user);
        identityService.createMembership(userProperty.getUid().toString(),login.getDid().toString());
    }
}
