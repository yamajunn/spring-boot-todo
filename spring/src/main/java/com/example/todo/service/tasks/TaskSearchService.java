package com.example.todo.service.tasks;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import com.example.todo.dto.request.tasks.TaskSearchRequest;
import com.example.todo.dto.response.tasks.TaskBaseResponse;
import com.example.todo.entity.Task;
import com.example.todo.repository.TaskRepository;
import com.example.todo.repository.specification.TaskSpecification;

@Service
public class TaskSearchService {

  private final TaskRepository taskRepository;

  public TaskSearchService(TaskRepository taskRepository) {
    this.taskRepository = taskRepository;
  }

  public List<TaskBaseResponse> invoke(TaskSearchRequest request) {
    TaskSpecification taskSpec = new TaskSpecification();
    Specification<Task> spec =
        Specification.where(taskSpec.projectIdEquals(request.getProjectId()));

    // completedAt降順 > deadlineAt昇順 > id降順 の並びになるようにソート条件を生成
    Sort sort = Sort.by(Sort.Order.desc("completedAt"), Sort.Order.asc("deadlineAt"),
        Sort.Order.desc("id"));

    // 引数に検索条件を指定することで、条件に合致する Task Entity のリストを取得する
    List<Task> tasks = this.taskRepository.findAll(spec, sort);

    // Task Entity のリストを、TaskBaseResponse のリストに変換して返す
    // .collect(Collectors.toList()) は、Stream の要素を List に変換するメソッド
    return tasks.stream().map(task -> new TaskBaseResponse(task)).collect(Collectors.toList());
  }
}
