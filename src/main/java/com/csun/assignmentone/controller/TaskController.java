package com.csun.assignmentone.controller;

import com.csun.assignmentone.entity.Task;
import com.csun.assignmentone.service.task.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping("/tasks")
    public Task saveTask(@RequestBody Task task) {
        return taskService.saveTask(task);
    }

    @GetMapping("/tasks")
    public List<Integer> fetchSortFields() {
        return taskService.getAllSortFields();
    }

    public List<Task> fetchAllTasks() {
        return taskService.fetchTaskList();
    }

    @PutMapping("/tasks/{id}")
    public Task updateTask(@RequestBody Task task, @PathVariable("id") Long taskId) {
        Optional<Long> primaryKey = fetchAllTasks().stream().filter(it -> it.getSortField() == taskId).map(Task::getId).findFirst();
        Long key = primaryKey.get();
        return taskService.updateTask(task, key);
    }

    @PutMapping("tasks/{oldIndex}/{newIndex}")
    public void updateSlotFields(@PathVariable Long oldIndex, @PathVariable Long newIndex) {
        Optional<Long> oldIndexKey = fetchAllTasks().stream().filter(it -> it.getSortField() == oldIndex).map(Task::getId).findFirst();
        Optional<Long> newIndexKey = fetchAllTasks().stream().filter(it -> it.getSortField() == newIndex).map(Task::getId).findFirst();
        taskService.updateTaskSlotFields(oldIndexKey.get(), newIndexKey.get());
    }

    @GetMapping("/tasks/{id}")
    public Task getTask(@PathVariable("id") Long taskId) {
        return taskService.findById(taskId).orElseGet(Task::new);
    }

    @DeleteMapping("/tasks/{id}")
    public String deleteTaskById(@PathVariable("id") Long departmentId) {
        taskService.deleteTaskById(departmentId);
        return "Deleted Successfully";
    }
}
