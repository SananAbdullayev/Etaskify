package com.example.etaskifyiba.dto.request;

import com.example.etaskifyiba.model.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskRequest {
    @NotBlank
    private String title;
    @NotBlank
    private String description;
    @Enumerated(EnumType.STRING)
    @NotBlank
    private String status;
    //    @NotBlank
    private Date deadline;
    private Integer orgId;
//    @NotBlank
    private List<Long> assignId;
}
