package com.example.todo.service.tasks;

import org.springframework.stereotype.Service;
import com.example.todo.dto.request.tasks.TaskCreateRequest;
import com.example.todo.dto.response.tasks.TaskBaseResponse;
import com.example.todo.entity.Project;
import com.example.todo.entity.Task;
import com.example.todo.repository.ProjectRepository;
import com.example.todo.repository.TaskRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class TaskCreateService {

  private final TaskRepository taskRepository;
  private final ProjectRepository projectRepository;

  public TaskCreateService(TaskRepository taskRepository, ProjectRepository projectRepository) {
    this.taskRepository = taskRepository;
    this.projectRepository = projectRepository;

  }

  // 引数で受け取ったリクエストを元に、タスクを作成して、作成した結果を返す
  public TaskBaseResponse invoke(TaskCreateRequest request) {
    // 空のタスクEntityを作成
    Task task = new Task();

    // タスクEntityに、リクエストの内容をセット
    task.setName(request.getName());
    task.setPriority(request.getPriority());

    // プロジェクトIDがリクエストに含まれている場合、プロジェクトをセット
    if (request.getProjectId() != null) {

      // this.projectRepository.findById(...): projectRepositoryを使用して、指定されたプロジェクトIDでプロジェクトを検索する。
      // ____________________________________（findByIdメソッドは、指定されたIDに一致するプロジェクトを検索し、Optional<Project>を返します。）
      // .orElseThrow(() -> new EntityNotFoundException(...)):
      // プロジェクトが見つからない場合は、EntityNotFoundExceptionをスローします。
      Project project = this.projectRepository.findById(request.getProjectId())
          .orElseThrow(() -> new EntityNotFoundException(
              "Project not found with ID: " + request.getProjectId()));

      // 取得したプロジェクトをセットする
      task.setProject(project);
    }

    // タスクEntityを、リポジトリを使ってDBに保存
    Task savedTask = this.taskRepository.save(task);

    // 保存したタスクEntityを元に、TaskBaseResponseを作成して返す
    return new TaskBaseResponse(savedTask);
  }
}
