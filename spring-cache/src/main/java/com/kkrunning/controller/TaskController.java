package com.kkrunning.controller;


import com.kkrunning.cache.TaskService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {


    @Resource
    private TaskService taskService;

    @GetMapping("/all")
    public List<String> all() {
        List<String> tasks = taskService.getTasks();
        return tasks;
    }


}
