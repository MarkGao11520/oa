package com.zrkj.oa.controller;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.zrkj.oa.dao.TestDao;
import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.Model;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by gaowenfeng on 2017/6/6.
 */
@Controller
public class TestController {
    @Autowired
    TestDao testDao;
    @Autowired
    RepositoryService repositoryService;

    @RequestMapping(value = "test",produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public List<User> test(){
        List list = new ArrayList();
        list.add(new User("1","gwf"));
        list.add(new User("2","gzf"));
        return list;
    }


    /**
     * 模型列表
     */
    @RequestMapping(value = "list")
    @ResponseBody
    public String  list() {
        List<Model> list = repositoryService.createModelQuery().list();
        return JSON.toJSONString(list);
    }


    /**
     * 导出model的xml文件
     */
    @RequestMapping("export")
    public void export(String modelId, HttpServletResponse response) {
        try {
            Model modelData = repositoryService.getModel(modelId);
            BpmnJsonConverter jsonConverter = new BpmnJsonConverter();
            JsonNode editorNode = new ObjectMapper().readTree(repositoryService.getModelEditorSource(modelData.getId()));
            BpmnModel bpmnModel = jsonConverter.convertToBpmnModel(editorNode);
            BpmnXMLConverter xmlConverter = new BpmnXMLConverter();
            byte[] bpmnBytes = xmlConverter.convertToXML(bpmnModel);

            ByteArrayInputStream in = new ByteArrayInputStream(bpmnBytes);
            IOUtils.copy(in, response.getOutputStream());
            String filename = bpmnModel.getMainProcess().getId() + ".bpmn20.xml";
            response.setHeader("Content-Disposition", "attachment; filename=" + filename);
            response.flushBuffer();
        } catch (Exception e) {
            System.out.println("导出model的xml文件失败：modelId={"+modelId+"}");
            e.printStackTrace();
        }
    }

    /**
     * 根据Model部署流程
     */
    @ResponseBody
    @RequestMapping(value = "deploy",produces = "application/json;charset=utf-8")
    public String deploy(String modelId) {
        Map<String,Object> stringObjectMap = new HashMap<String,Object>();
        try {
            Model modelData = repositoryService.getModel(modelId);
            ObjectNode modelNode = (ObjectNode) new ObjectMapper().readTree(repositoryService.getModelEditorSource(modelData.getId()));
            byte[] bpmnBytes = null;

            BpmnModel model = new BpmnJsonConverter().convertToBpmnModel(modelNode);
            bpmnBytes = new BpmnXMLConverter().convertToXML(model);

            String processName = modelData.getName() + ".bpmn20.xml";
            Deployment deployment = repositoryService.createDeployment().name(modelData.getName()).addString(processName, new String(bpmnBytes)).deploy();
            stringObjectMap.put("msg","部署成功，部署ID=" + deployment.getId());
            stringObjectMap.put("result",1);
        } catch (Exception e) {
            stringObjectMap.put("msg","根据模型部署流程失败：modelId="+modelId );
            stringObjectMap.put("result",0);
            e.printStackTrace();
        }finally {
            return JSON.toJSONString(stringObjectMap);

        }
    }
}
