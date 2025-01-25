package com.example.todo.dto.response.tasks;

import java.time.LocalDateTime;
import com.example.todo.entity.Project;
import com.example.todo.entity.Task;
import com.example.todo.util.TimeUtil;
import lombok.Data;

@Data
public class TaskBaseResponse {

  private Integer id;

  private Project project;

  private String name;

  private TaskPriorityResponse priority;

  private String memo;

  private String deadlineAt;

  private String completedAt;

  private String createdAt;

  private String updatedAt;

  public TaskBaseResponse(Task task) {
    this.id = task.getId();
    this.project = task.getProject();
    this.name = task.getName();
    this.priority = new TaskPriorityResponse(task.getPriority());
    this.memo = task.getMemo();
    this.deadlineAt = this.formatFromDateTimeToYmdHm(task.getDeadlineAt());
    this.completedAt = this.formatFromDateTimeToYmdHm(task.getCompletedAt());
    this.createdAt = this.formatFromDateTimeToYmdHm(task.getCreatedAt());
    this.updatedAt = this.formatFromDateTimeToYmdHm(task.getUpdatedAt());
  }

  private String formatFromDateTimeToYmdHm(LocalDateTime dateTime) {
    if (dateTime == null) {
      return null;
    }

    return TimeUtil.Format.toYmdHm(dateTime);
  }
}
