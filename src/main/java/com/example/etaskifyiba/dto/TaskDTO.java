package com.example.etaskifyiba.dto;

import com.example.etaskifyiba.model.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskDTO {
    private String title;
    private String description;
    private Status status;
    private Date deadline;

}
