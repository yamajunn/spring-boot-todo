package com.example.todo.dto.response.tasks;

import com.example.todo.enums.TaskPriority;
import lombok.Getter;

@Getter
public class TaskPriorityResponse {

  private final String name;
  private final Integer value;
  private final String label;

  public TaskPriorityResponse(TaskPriority taskpriority) {
    this.name = taskpriority.name();
    this.value = taskpriority.getValue();
    this.label = taskpriority.getLabel();
  }
}
