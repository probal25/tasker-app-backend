package com.probal.tasker.clientside.response;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class TaskResponse {

    private Long id;

    private String title;

    private String type;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date dueDate;

    private String description;

    public static TaskResponse from(Task task) {
        return TaskResponse.builder()
                .id(task.getId())
                .title(task.getTitle())
                .type(task.getStatus().value)
                .dueDate(task.getDueDate())
                .description(task.getDescription())
                .build();
    }

}
