package com.csun.assignmentone.service.task;

import com.csun.assignmentone.entity.Task;
import com.csun.assignmentone.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public Task saveTask(Task task) {
        task.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        OptionalInt max = getAllSortFields().stream().mapToInt(Integer::intValue).max();
        if(max.isPresent()) {
            task.setSortField(max.getAsInt()+1);
        } else {
            task.setSortField(1);
        }
        return taskRepository.save(task);
    }

    @Override
    public List<Task> fetchTaskList() {
        return (List<Task>) taskRepository.findAll();
    }

    @Override
    public Optional<Task> findById(Long taskId) {
        return taskRepository.findById(taskId);
    }

    @Override
    public Task updateTask(Task task, Long taskId) {
        Optional<Task> optionalTask = taskRepository.findById(taskId);
        if(optionalTask.isPresent()) {
            Task toUpdate = optionalTask.get();
            toUpdate.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
            toUpdate.setName(task.getName());
            toUpdate.setDescription(task.getDescription());
            return taskRepository.save(toUpdate);
        }
        return null;
    }

    @Override
    public void deleteTaskById(Long taskId) {
        Optional<Task> optionalTask = taskRepository.findById(taskId);
        if(optionalTask.isPresent()) {
            Task toUpdate = optionalTask.get();
            toUpdate.setDeletedAt(new Timestamp(System.currentTimeMillis()));
            taskRepository.save(toUpdate);
        }
    }

    @Override
    public List<Integer> getAllSortFields() {
        return fetchTaskList().stream().map(Task::getSortField).toList();
    }

    @Override
    public void updateTaskSlotFields(Long oldId, Long newId) {
        Task oldTask = taskRepository.findById(oldId).get();
        Task newTask = taskRepository.findById(newId).get();
        final int oldSortField = oldTask.getSortField();
        final int newSortField = newTask.getSortField();
        oldTask.setSortField(newSortField);
        newTask.setSortField(oldSortField);
    }
}
