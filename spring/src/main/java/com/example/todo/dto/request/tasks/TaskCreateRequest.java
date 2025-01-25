package com.example.todo.dto.request.tasks;

import com.example.todo.enums.TaskPriority;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class TaskCreateRequest {

  @NotEmpty
  private final String name;

  private final Integer projectId;

  /**
   * リクエストされるJSONデータでは 0, 1, 2 で受け取る。
   *
   * 型を TaskPriority にしているため、TaskPriority の値に自動変換される。
   */
  @NotNull
  private final TaskPriority priority;
}
