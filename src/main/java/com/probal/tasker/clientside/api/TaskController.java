package com.probal.tasker.clientside.api;

import com.probal.tasker.clientside.request.TaskRequest;
import com.probal.tasker.clientside.response.TaskResponse;
import com.probal.tasker.serverside.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/api/v1/task")
public class TaskController {

    private final TaskService taskService;

    @PostMapping("/add")
    public ResponseEntity<?> addTask(@RequestBody TaskRequest request) {
        TaskResponse response = TaskResponse.from(taskService.createTask(request));
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateTask(@RequestBody TaskRequest request, @PathVariable Long id) {
        TaskResponse response = TaskResponse.from(taskService.updateTask(id, request));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<?> findTaskById(@PathVariable Long id) {
        TaskResponse response = TaskResponse.from(taskService.getTaskById(id));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/find/all")
    public ResponseEntity<?> findAllTasks() {
        List<TaskResponse> responses = taskService.getAllTasks().stream().map(TaskResponse::from).toList();
        return ResponseEntity.ok(responses);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id) {
        taskService.deleteTaskById(id);
        return ResponseEntity.ok("Task Deleted successfully");
    }

    @GetMapping("/status/percentage")
    public ResponseEntity<?> getTaskPercentage() {
        return ResponseEntity.ok(taskService.getTaskStatusPercentage());
    }
}
