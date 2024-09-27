package com.javapub.flowable.myflowable.controller;

import org.flowable.engine.ProcessEngine;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author keda
 * @date 2024/9/26 18:44:18
 */
@RequestMapping(value = "/dispatch")
@RestController
public class DispatchController {

    @Resource
    private ProcessEngine processEngine;

    @Resource
    private RuntimeService runtimeService;

    @Resource
    private TaskService taskService;

    @GetMapping(value = "/add")
    public Object start(String incidentCode, Boolean autoAssign) {
        Map<String, Object> param = new HashMap<>();
        param.put("incidentCode", incidentCode);
        param.put("autoAssign", autoAssign);
        ProcessInstance dispatch = runtimeService.startProcessInstanceByKey("Dispatch", param);
        return dispatch.getId();
    }

    @GetMapping(value = "/assign")
    public Object assign(String taskId, String incidentCode, @RequestBody List<String> dispatchers) {
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        Map<String, Object> param = new HashMap<>();
        param.put("incidentCode", incidentCode);
        param.put("dispatchers", dispatchers);
        taskService.complete(taskId, param);
        return "processed ok!";
    }

    @GetMapping(value = "/taskList")
    public Object taskList(String userId) {
        List<Task> tasks = taskService.createTaskQuery().taskAssignee(userId).orderByTaskCreateTime().desc().list();
        for (Task task : tasks) {
            System.out.println(task.toString());
        }
        return tasks;
    }
}
