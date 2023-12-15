package com.csun.assignmentone.service.task;

import com.csun.assignmentone.entity.Task;

import java.util.List;
import java.util.Optional;

public interface TaskService {

    Task saveTask(Task task);

    List<Task> fetchTaskList();

    Optional<Task> findById(Long taskId);

    Task updateTask(Task task, Long taskId);

    void deleteTaskById(Long taskId);

    List<Integer> getAllSortFields();

    void updateTaskSlotFields(Long oldId, Long newId);
}
