package com.zrkj.oa.web;

import com.zrkj.oa.core.ResultGenerator;
import com.zrkj.oa.model.Depart;
import com.zrkj.oa.model.Login;
import com.zrkj.oa.model.UserProperty;
import com.zrkj.oa.service.IDepartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by gaowenfeng on 2017/7/3.
 */
@RestController()
@RequestMapping("departController")
@Scope("prototype")
public class DepartController {
    @Autowired
    IDepartService iDepartService;



    @RequestMapping(value = "addDepart",produces = "application/json;charset=UTF-8")
    public String addDepart(Depart depart){
        iDepartService.save(depart);
        return ResultGenerator.genSuccessResult();
    }

    @RequestMapping(value = "updateDepart",produces = "application/json;charset=UTF-8")
    public String updateDepart(Depart depart){
        iDepartService.update(depart);
        return ResultGenerator.genSuccessResult();
    }

    @RequestMapping(value = "delete",produces = "application/json;charset=UTF-8")
    public String updateUser(@RequestBody Integer[] ids){
        iDepartService.delete(ids);
        return ResultGenerator.genSuccessResult();
    }

    @RequestMapping(value = "getList",produces = "application/json;charset=UTF-8")
    public String getList(){
        List<Depart> list =  iDepartService.findList();
        return ResultGenerator.genSuccessResult(list);
    }
}
