package com.zrkj.oa.web;

import com.alibaba.fastjson.JSON;
import com.zrkj.oa.core.ResultGenerator;
import com.zrkj.oa.model.CustomForm;
import com.zrkj.oa.model.Depart;
import com.zrkj.oa.service.ICustomFormService;
import org.activiti.engine.repository.ProcessDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by gaowenfeng on 2017/7/4.
 */
@RestController
@RequestMapping("customFormController")
@Scope("prototype")
public class CustomFormController {
    @Autowired
    ICustomFormService iCustomFormService;

    @RequestMapping(value = "addCustomForm",produces = "application/json;charset=UTF-8")
    public String addCustomForm(@RequestBody List<CustomForm> customForms){
        iCustomFormService.save(customForms);
        return ResultGenerator.genSuccessResult();
    }

    @RequestMapping(value = "updateCustomForm",produces = "application/json;charset=UTF-8")
    public String updateCustomForm(List<CustomForm> customForm){
        iCustomFormService.update(customForm);
        return ResultGenerator.genSuccessResult();
    }

    @RequestMapping(value = "delete",produces = "application/json;charset=UTF-8")
    public String deleteCustomForm(Integer id){
        iCustomFormService.delete(id);
        return ResultGenerator.genSuccessResult();
    }

    @RequestMapping(value = "getList",produces = "application/json;charset=UTF-8")
    public String getList(){
        List<com.zrkj.oa.model.ProcessDefinition> list =  iCustomFormService.getProDefList();
        return ResultGenerator.genSuccessResult(list);
    }

    @RequestMapping(value = "getListByProId",produces = "application/json;charset=UTF-8")
    public String getListByProId(String id){
        List<CustomForm> list =  iCustomFormService.getListByProDefId(id);
        return ResultGenerator.genSuccessResult(list);
    }
}
