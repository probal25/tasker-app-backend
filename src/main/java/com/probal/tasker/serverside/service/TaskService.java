package com.probal.tasker.serverside.service;

import com.probal.tasker.clientside.request.TaskRequest;
import com.probal.tasker.serverside.dto.CountStatus;
import com.probal.tasker.serverside.exception.ResourceNotFoundException;
import com.probal.tasker.serverside.model.Task;
import com.probal.tasker.serverside.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    public List<Task> getAllTasks() {
        return taskRepository.findAllByOrderByDueDateAsc();
    }

    @Transactional
    public Task createTask(TaskRequest request) {
        Task task = TaskRequest.createTask(request);
        return taskRepository.save(task);
    }

    public Task getTaskById(Long id) {
        return taskRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    @Transactional
    public Task updateTask(Long id, TaskRequest request) {
        Task task = taskRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        TaskRequest.applyUpdate(task, request);
        return taskRepository.save(task);
    }

    @Transactional
    public void deleteTaskById(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        taskRepository.delete(task);
    }

    public List<CountStatus> getTaskStatusPercentage() {
        List<Object[]> objects =  taskRepository.getTaskPercentageGroupByStatus();
        List<CountStatus> countStatuses = new ArrayList<>();
        for (Object[] object : objects) {
            CountStatus countStatus =  new CountStatus((long) Double.parseDouble(String.valueOf(object[0])), String.valueOf(object[1]));
            countStatuses.add(countStatus);
        }
        return countStatuses;
    }
}
