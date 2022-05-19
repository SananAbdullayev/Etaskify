package com.example.etaskifyiba.service.impl;

import com.example.etaskifyiba.dto.MailDTO;
import com.example.etaskifyiba.dto.TaskDTO;
import com.example.etaskifyiba.dto.request.TaskRequest;
import com.example.etaskifyiba.dto.response.TaskResponse;
import com.example.etaskifyiba.exception.CustomNotFoundException;
import com.example.etaskifyiba.exception.handling.ErrorCodeEnum;
import com.example.etaskifyiba.model.entity.Organization;
import com.example.etaskifyiba.model.entity.Task;
import com.example.etaskifyiba.model.entity.User;
import com.example.etaskifyiba.model.enums.Status;
import com.example.etaskifyiba.repository.OrganizationRepository;
import com.example.etaskifyiba.repository.TaskRepository;
import com.example.etaskifyiba.repository.UserRepository;
import com.example.etaskifyiba.security.JwtUtils;
import com.example.etaskifyiba.service.MailService;
import com.example.etaskifyiba.service.TaskService;
import com.example.etaskifyiba.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    private final UserRepository userRepository;

    private final MailService mailService;

    private final UserService userService;

    private final OrganizationRepository organizationRepository;

    private final JwtUtils jwtUtils;

    @Override
    public TaskResponse getAllTaskByOrgId(int id) {
        List<TaskDTO> taskList = taskRepository.findAllByOrganizationId(id)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        return makeTaskResponse(taskList);
    }

    @Override
    public TaskResponse getTasksByStatus(Status status) {
        List<TaskDTO> taskList = taskRepository.findAllByStatus(status)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());

        return makeTaskResponse(taskList);
    }

    @Override
    public TaskResponse getAllTasksByUserId(long id) {
        List<TaskDTO> taskList = taskRepository.findAllByUsersId(id)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());

        return makeTaskResponse(taskList);
    }

    @Override
    public TaskDTO getTaskById(long id) {
        return taskRepository.findById(id)
                .map(this::convertToDto)
                .orElseThrow(() -> new CustomNotFoundException(ErrorCodeEnum.TASK_NOT_FOUND));
    }

    public void create(TaskRequest taskRequest) {
        Organization organization = organizationRepository.findByUserUsername(jwtUtils.getUserNameFromJwtToken())
                .orElseThrow(() -> new CustomNotFoundException(ErrorCodeEnum.ORGANIZATION_NOT_FOUND));

        Set<User> users = new HashSet<>();
        taskRequest.getAssignId()
                .forEach(id -> {
                    User user = userRepository.findById(id)
                            .orElseThrow(() -> new CustomNotFoundException(ErrorCodeEnum.USER_NOT_FOUND));
                    users.add(user);
                });
        Task task = Task.builder()
                .title(taskRequest.getTitle())
                .description(taskRequest.getDescription())
                .deadline(taskRequest.getDeadline())
                .status(Status.valueOf(taskRequest.getStatus()))
                .users(users)
                .organization(organization)
                .build();

        users.forEach(id ->
                {
                    try {
                        mailService.sendMail(
                                MailDTO.builder()
                                        .mailTo(userService.getUser(id.getId()).getEmail())
                                        .mailSubject(taskRequest.getTitle())
                                        .mailContent(taskRequest.getDescription())
                                        .build());
                    } catch (MessagingException e) {
                        e.printStackTrace();
                    }
                }
        );
        taskRepository.save(task);
    }

    @Override
    public void update(long id, TaskRequest taskRequest) {
        Task task = getTask(id);
        task = Task.builder()
                .id(task.getId())
                .deadline(taskRequest.getDeadline())
                .status(Status.valueOf(taskRequest.getStatus()))
                .description(taskRequest.getDescription())
                .title(taskRequest.getTitle())
                .build();

        taskRepository.save(task);
    }

    @Override
    public void delete(long id) {
        Task task = getTask(id);
        taskRepository.delete(task);
    }

    private TaskDTO convertToDto(Task task) {
        return TaskDTO.builder()
                .title(task.getTitle())
                .description(task.getDescription())
                .status(task.getStatus())
                .deadline(task.getDeadline())
                .build();
    }

    private TaskResponse makeTaskResponse(List<TaskDTO> tasks) {
        return TaskResponse.builder()
                .tasks(tasks)
                .build();
    }

    private Task getTask(long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new CustomNotFoundException(ErrorCodeEnum.TASK_NOT_FOUND));
    }
}
