package com.example.todo.controller.api.tasks;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.todo.dto.response.tasks.TaskBaseResponse;
import com.example.todo.service.tasks.TaskToggleService;

@RestController
@RequestMapping("/api/tasks")
public class TaskToggleController {

  private final TaskToggleService taskToggleService;

  public TaskToggleController(TaskToggleService taskToggleService) {
    this.taskToggleService = taskToggleService;
  }

  /**
   * 例: PUT /api/tasks/123/toggle というパスになる。
   *
   * @PathVariable パスの中に含まれる {} で囲まれた部分を変数として受け取ることができる。この場合、id という変数にパスの中に含まれる値が入る。
   */
  @PutMapping("/{id}/toggle")
  public ResponseEntity<TaskBaseResponse> invoke(@PathVariable Integer id) {

    TaskBaseResponse response = this.taskToggleService.invoke(id);

    return ResponseEntity.ok(response);
  }
}
