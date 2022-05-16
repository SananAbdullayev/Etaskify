package com.example.etaskifyiba.repository;

import com.example.etaskifyiba.model.entity.UserTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserTaskRepository extends JpaRepository<UserTask, Integer> {
    List<UserTask> findByTaskId(Integer taskId);

    List<UserTask> findByUserId(Integer userId);
}
