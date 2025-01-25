package com.example.todo.service.tasks;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import com.example.todo.dto.request.tasks.TaskUpdateRequest;
import com.example.todo.dto.response.tasks.TaskBaseResponse;
import com.example.todo.entity.Project;
import com.example.todo.entity.Task;
import com.example.todo.repository.ProjectRepository;
import com.example.todo.repository.TaskRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class TaskUpdateService {

  private final TaskRepository taskRepository;

  private final ProjectRepository projectRepository;

  public TaskUpdateService(TaskRepository taskRepository, ProjectRepository projectRepository) {
    this.taskRepository = taskRepository;
    this.projectRepository = projectRepository;
  }

  public TaskBaseResponse invoke(TaskUpdateRequest request) {
    // 更新対象のタスクを取得
    Task task = this.taskRepository.findById(request.getId()).orElseThrow(
        () -> new EntityNotFoundException("Task not found with ID: " + request.getId()));

    // リクエストで受け取った値を更新対象のタスクにセット
    if (request.getProjectId() != null) {
      // プロジェクトIDが指定されている場合は、対象のプロジェクトを取得する。
      Project project = this.projectRepository.findById(request.getProjectId())
          .orElseThrow(() -> new EntityNotFoundException(
              "Project not found with ID: " + request.getProjectId()));

      // 取得したものをセットする
      task.setProject(project);
    } else {
      task.setProject(null);
    }

    // リクエストで受け取った値を更新対象のTaskEntity にコピー出来る
    // => 第一引数のオブジェクトが持つプロパティを、第二引数の"同名 かつ 同じ型"のプロパティに対してコピーする
    // => 要は、第一引数がコピー元、第二引数がコピー先
    // => もしも、コピーしたくないプロパティが存在する場合は、第三引数で指定可能
    // => 今回は "id" を除外設定。データ更新では自身のIDを変更することはあり得ないため、コピー対象外とする
    BeanUtils.copyProperties(request, task, "id");

    // タスクを更新
    Task updatedTask = this.taskRepository.save(task);

    // TaskBaseResponse に変換して返却
    return new TaskBaseResponse(updatedTask);
  }
}
