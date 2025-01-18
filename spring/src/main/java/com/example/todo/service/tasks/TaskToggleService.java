package com.example.todo.service.tasks;

import org.springframework.stereotype.Service;
import com.example.todo.dto.response.tasks.TaskBaseResponse;
import com.example.todo.entity.Task;
import com.example.todo.repository.TaskRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class TaskToggleService {

  private final TaskRepository taskRepository;

  public TaskToggleService(TaskRepository taskRepository) {
    this.taskRepository = taskRepository;
  }

  public TaskBaseResponse invoke(Integer id) {
    // 対象のタスクを取得する。
    Task task = this.taskRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Task not found with ID: " + id));

    // タスクの完了/未完了状態を切り替える。
    task.toggleCompleted();

    // 切り替えたタスクを更新する。
    Task toggledTask = this.taskRepository.save(task);

    // 更新したタスクをリターンする。
    return new TaskBaseResponse(toggledTask);
  }
}
