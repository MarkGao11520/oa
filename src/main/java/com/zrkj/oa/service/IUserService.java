package com.zrkj.oa.service;

import com.zrkj.oa.model.Login;
import com.zrkj.oa.model.UserProperty;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by gaowenfeng on 2017/7/3.
 */
public interface IUserService {
    void loginCheck(Login login,HttpSession session);

    void save(Login login,UserProperty user);

    void update(UserProperty user);

    void updatePassword(String oldPassword, String newPassword, HttpSession session);

    List<UserProperty> findUserList(Integer did);

}
