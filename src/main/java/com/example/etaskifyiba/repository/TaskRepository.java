package com.example.etaskifyiba.repository;

import com.example.etaskifyiba.model.entity.Task;
import com.example.etaskifyiba.model.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findAllByOrganizationId(int orgId);
    List<Task> findAllByStatus(Status status);
    List<Task> findAllByUsersId(long id);
}
