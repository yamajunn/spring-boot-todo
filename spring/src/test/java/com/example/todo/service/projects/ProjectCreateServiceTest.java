package com.example.todo.service.projects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import com.example.todo.dto.request.projects.ProjectCreateRequest;
import com.example.todo.entity.Project;
import com.example.todo.repository.ProjectRepository;

class ProjectCreateServiceTest {

  /**
   * @Mock Mockitoによってモック化されたインスタンスを生成するアノテーション。
   *
   *       モック化されたインスタンスは、実際のインスタンスと同じように振る舞うが、実際の処理は行わない。
   *       モック化されたインスタンスは、テスト対象のクラスが依存しているクラスを置き換えるために使用される。
   */
  @Mock
  private ProjectRepository projectRepository;

  /**
   * @InjectMocks テスト対象のクラス（今回だとProjectCreateService）に、@Mock で作成したモックを注入する。
   *              具体的には、@Mockアノテーションでモック化されたProjectRepositoryが、ProjectCreateServiceのコンストラクタに渡される。
   *              これにより、依存関係がモックオブジェクトで自動的に満たされるため、テスト対象クラスを手動でインスタンス化する必要がなくなる。
   */
  @InjectMocks
  private ProjectCreateService projectCreateService;

  @BeforeEach
  void setUp() {
    // Mockito のアノテーション（@Mock, @Spy, @InjectMocks）でマークされたフィールドにモックを注入するためのメソッド
    // 内部的には、Mockito が各フィールドに対してモックオブジェクトを生成し、それをセットする
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void プロジェクトが作成されること() {
    // モックデータの設定（ProjectRepository.save()メソッドが呼ばれたときの戻り値を準備）
    Project mockProject = new Project();
    mockProject.setId(1);
    mockProject.setName("Test Project");
    mockProject.setSummary("Test Summary");

    // モックリポジトリの動作を設定
    Mockito.when(this.projectRepository.save(Mockito.any(Project.class))).thenReturn(mockProject);

    // リクエストオブジェクトを作成
    ProjectCreateRequest request = new ProjectCreateRequest("Test Project", "Test Summary");

    // サービスメソッドを実行し、戻り値を取得（実際にテストしたい処理）
    Project createdProject = this.projectCreateService.invoke(request);

    // 結果を検証
    assertEquals(1, createdProject.getId());
    assertEquals("Test Project", createdProject.getName());
    assertEquals("Test Summary", createdProject.getSummary());

    // リポジトリのsaveメソッドが1回呼ばれたことを確認
    Mockito.verify(this.projectRepository, Mockito.times(1)).save(Mockito.any(Project.class));
  }
}
