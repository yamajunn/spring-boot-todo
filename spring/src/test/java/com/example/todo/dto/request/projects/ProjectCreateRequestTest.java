package com.example.todo.dto.request.projects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

class ProjectCreateRequestTest {

  private Validator validator;

  /**
   * @BeforeEach このアノテーションが付与されたメソッドは、各テストメソッドが実行される前に実行される。
   */
  @BeforeEach
  void setUp() {
    // バリデーションのためのインスタンスを生成
    // Validation.buildDefaultValidatorFactory() は Validator を生成するためのファクトリー（工場的なもの）を生成する
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

    // そのfactoryから、getValidator() で Validator を生成する
    this.validator = factory.getValidator();
  }

  @Test
  void 有効なリクエストであること() {
    // テスト対象のインスタンスを作成
    ProjectCreateRequest request = new ProjectCreateRequest("name", "summary");

    // バリデーションを実行し、違反が無いことを確認
    Set<ConstraintViolation<ProjectCreateRequest>> validations = this.validator.validate(request);

    assertTrue(validations.isEmpty());
  }

  @Test
  void 名前が空の場合にエラーになること() {
    // テスト対象のインスタンスを作成
    ProjectCreateRequest request = new ProjectCreateRequest("", "summary");

    // バリデーションを実行し、違反があることを確認
    Set<ConstraintViolation<ProjectCreateRequest>> validations = this.validator.validate(request);

    assertEquals(1, validations.size());
  }

  @Test
  void 概要が空の場合でも有効なリクエストであること() {
    // テスト対象のインスタンスを作成
    ProjectCreateRequest request = new ProjectCreateRequest("name", "");

    // バリデーションを実行し、違反があることを確認
    Set<ConstraintViolation<ProjectCreateRequest>> validations = this.validator.validate(request);
    assertTrue(validations.isEmpty());
  }
}
