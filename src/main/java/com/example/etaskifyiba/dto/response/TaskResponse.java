package com.example.etaskifyiba.dto.response;

import com.example.etaskifyiba.dto.TaskDTO;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskResponse {
    private List<TaskDTO> tasks;
}
