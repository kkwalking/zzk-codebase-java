package com.kkrunning.cache;


import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class TaskService {

    static final String TASK_CACHE = "taskCache";


    /**
     * 在redis中存的是一个key为 "task"的hash结构
     * hash有一条记录，field是"AllTasks", value是方法返回值
     * @return
     */
    @Cacheable(value = "task ",key = "'AllTasks'", cacheManager = "redissionCache")
    public List<String> getTasks() {
        log.info("get tasks all ***");
        ArrayList<String> tasks = new ArrayList<>();
        tasks.add("task1");
        tasks.add("task2");
        tasks.add("task3");
        return tasks;

    }

}
