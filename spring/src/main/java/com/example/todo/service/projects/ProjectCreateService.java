package com.example.todo.service.projects;

import org.springframework.stereotype.Service;
import com.example.todo.dto.request.projects.ProjectCreateRequest;
import com.example.todo.entity.Project;
import com.example.todo.repository.ProjectRepository;

/**
 * @Service Spring MVCのサービスとして振る舞うクラスに付与するアノテーション。このアノテーションが付与されたクラスは、ビジネスロジックを持つサービスとして動作する。
 */
@Service
public class ProjectCreateService {

  /**
   * コンストラクタインジェクション（DI）
   *
   * コンストラクタインジェクション（DI）とは、クラスのコンストラクタを使用して、依存性の注入を行う方法のこと。
   * 依存性の注入とは、クラス間の依存関係を外部から注入することで、クラス間の結合度を低くし、柔軟な設計を実現するための手法。
   * 実際の動きとしては、コンストラクタの引数にインターフェースを指定し、そのインターフェースを実装したクラスのインスタンスを渡すことで、依存性の注入を行う。
   *
   * DI に関する Q&A
   *
   * DIされたインスタンスは、どのタイミングで生成されたもの？ → Spring Frameworkによって、アプリケーションの起動時に生成される。
   * DIされたインスタンスは、どのタイミングで破棄されるもの？ → Spring Frameworkによって、アプリケーションの終了時に破棄される。
   */
  private final ProjectRepository projectRepository;

  // コンストラクタ
  public ProjectCreateService(ProjectRepository projectRepository) {
    this.projectRepository = projectRepository;
  }

  /**
   *
   * @param request ProjectCreateController から受け取ったリクエスト
   * @return Project 作成したプロジェクト
   */
  public Project invoke(ProjectCreateRequest request) {
    Project project = new Project();

    project.setName(request.getName());
    project.setSummary(request.getSummary());

    // .save()メソッドを使用して、ProjectRepositoryのsaveメソッドを呼び出し、引数にprojectを渡すことで、データベースにデータを保存する。
    return this.projectRepository.save(project);
  }
}
