package com.example.etaskifyiba.controller;

import com.example.etaskifyiba.dto.TaskDTO;
import com.example.etaskifyiba.dto.request.TaskRequest;
import com.example.etaskifyiba.dto.response.TaskResponse;
import com.example.etaskifyiba.model.enums.Status;
import com.example.etaskifyiba.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @GetMapping("/org/{orgId}")
    public TaskResponse getAllTaskByOrgId(@PathVariable(name = "orgId") long id) {
        return taskService.getAllTaskByOrgId(id);
    }

    @GetMapping("/user_tasks/{id}")
    public TaskResponse getAllTasksByUserId(@PathVariable(name = "id")long id){
        return taskService.getAllTasksByUserId(id);
    }

    @GetMapping("/{id}")
    public TaskDTO getTaskById(@PathVariable(name = "id") long id) {
        return taskService.getTaskById(id);
    }

    @GetMapping("/status/")
    public TaskResponse getTasksByStatusId(@RequestParam(name = "status") String status) {
        return taskService.getTasksByStatus(Status.valueOf(status));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
//    @PreAuthorize("hasRole('ADMIN')")
    public void createTask(@Valid @RequestBody TaskRequest taskRequest) {
        taskService.create(taskRequest);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void updateTaskById(@PathVariable(name = "id") long id,
                               @Valid @RequestBody TaskRequest taskRequest) {
        taskService.update(id, taskRequest);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteTaskById(@PathVariable(name = "id") long id) {
        taskService.delete(id);
    }
}
