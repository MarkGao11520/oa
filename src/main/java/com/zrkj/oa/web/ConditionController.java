package com.zrkj.oa.web;

import com.zrkj.oa.core.ResultGenerator;
import com.zrkj.oa.model.Condition;
import com.zrkj.oa.model.Depart;
import com.zrkj.oa.service.IConditionService;
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
@RequestMapping("conditionController")
@Scope("prototype")
public class ConditionController {
    @Autowired
    IConditionService iConditionService;

    /**
     * 添加条件
     * @param condition
     * @return
     */
    @RequestMapping(value = "addCondition",produces = "application/json;charset=UTF-8")
    public String addCondition(Condition condition){
        iConditionService.save(condition);
        return ResultGenerator.genSuccessResult();
    }

    /**
     * 修改条件
     * @param condition
     * @return
     */
    @RequestMapping(value = "updateCondition",produces = "application/json;charset=UTF-8")
    public String updateCondition(Condition condition){
        iConditionService.update(condition);
        return ResultGenerator.genSuccessResult();
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @RequestMapping(value = "delete",produces = "application/json;charset=UTF-8")
    public String deleteBatch(@RequestBody Integer[] ids){
        iConditionService.delete(ids);
        return ResultGenerator.genSuccessResult();
    }

    /**
     * 获取列表
     * @return
     */
    @RequestMapping(value = "getList",produces = "application/json;charset=UTF-8")
    public String getList(){
        List<Condition> list =  iConditionService.findList();
        return ResultGenerator.genSuccessResult(list);
    }
}
