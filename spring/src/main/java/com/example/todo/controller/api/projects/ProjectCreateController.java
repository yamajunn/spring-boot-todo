package com.example.todo.controller.api.projects;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.todo.dto.request.projects.ProjectCreateRequest;
import com.example.todo.entity.Project;
import com.example.todo.service.projects.ProjectCreateService;
import jakarta.validation.Valid;

/**
 * @RestController Spring MVCのコントローラとして振る舞うクラスに付与するアノテーション。
 *                 このアノテーションが付与されたクラスは、HTTPリクエストを受け取るコントローラとして動作する。
 *                 また、JSONやXMLなどのデータをHTTPレスポンスとして返却することができる。
 *
 * @RequestMapping クラスまたはメソッドに対してHTTPリクエストのパスやメソッドタイプ（GET、POSTなど）をマッピングするためのアノテーション。
 *                 第一引数に、リクエストされるURLパスを指定する。
 */
@RestController
@RequestMapping("/api/projects")
public class ProjectCreateController {

  private final ProjectCreateService projectCreateService;

  public ProjectCreateController(ProjectCreateService projectCreateService) {
    this.projectCreateService = projectCreateService;
  }

  /**
   * @PostMapping HTTP POSTリクエストを受け取るメソッドに付与するアノテーション。このアノテーションが付与されたメソッドは、HTTP
   *              POSTリクエストを受け取るコントローラのメソッドとして動作する。
   *
   * @Valid メソッドの引数に付与するアノテーション。引数のオブジェクトに対してバリデーションを行う。
   *        バリデーションエラーが発生した場合、メソッドの実行を中断し、エラーメッセージを返却する。このアノテーションを付与することで、コントローラ内でバリデーションを行うことができる。
   *
   * @RequestBody HTTPリクエストのボディ部分を受け取るメソッドの引数に付与するアノテーション。リクエストボディを受け取るために使用する。
   *              リクエストボディとは、HTTPリクエストのボディ部分に含まれるJSONやXMLなどのデータのこと。
   */
  @PostMapping
  public ResponseEntity<Project> invoke(@Valid @RequestBody ProjectCreateRequest request) {
    Project project = this.projectCreateService.invoke(request);

    return ResponseEntity.ok(project);
  }
}
