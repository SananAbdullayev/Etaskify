package com.example.etaskifyiba.service;

import com.example.etaskifyiba.dto.TaskDTO;
import com.example.etaskifyiba.dto.request.TaskRequest;
import com.example.etaskifyiba.dto.response.TaskResponse;
import com.example.etaskifyiba.model.enums.Status;

public interface TaskService {
    TaskResponse getAllTaskByOrgId(int id);

    void create(TaskRequest taskRequest);

    TaskDTO getTaskById(long id);

    TaskResponse getTasksByStatus(Status status);

    void update(long id, TaskRequest taskRequest);

    void delete(long id);

    TaskResponse getAllTasksByUserId(long id);
}
