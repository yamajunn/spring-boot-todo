package com.example.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.example.todo.entity.Task;

public interface TaskRepository
    extends JpaRepository<Task, Integer>, JpaSpecificationExecutor<Task> {
}
