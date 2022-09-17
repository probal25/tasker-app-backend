package com.probal.tasker.clientside.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.probal.tasker.serverside.enums.Status;
import com.probal.tasker.serverside.model.Task;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskRequest {

    private Long id;

    private String title;

    private String status;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date dueDate;

    private String description;

    public static Task createTask(TaskRequest taskRequest) {

        Task task = new Task();
        task.setTitle(taskRequest.getTitle());
        task.setStatus(Status.forValue(taskRequest.getStatus()));
        task.setDescription(taskRequest.getDescription());
        task.setDueDate(taskRequest.getDueDate());

        return task;
    }
    public static void applyUpdate(Task task, TaskRequest taskRequest) {
        task.setTitle(taskRequest.getTitle());
        task.setStatus(Status.forValue(taskRequest.getStatus()));
        task.setDescription(taskRequest.getDescription());
        task.setDueDate(taskRequest.getDueDate());
    }

}
