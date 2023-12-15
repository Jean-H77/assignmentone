package com.csun.assignmentone.service;

import com.csun.assignmentone.entity.Task;

import java.util.List;

public interface TaskService {
    
    Task saveDepartment(Task department);

    List<Task> fetchTaskList();

    Task updateTask(Task task, Long taskId);

    void deleteTaskById(Long taskId);
}
