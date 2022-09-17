package com.probal.tasker.serverside.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CountStatus {

    private Long count;

    private String status;
}
