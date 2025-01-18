package com.example.todo.repository.specification;

import org.springframework.data.jpa.domain.Specification;
import com.example.todo.entity.Task;

/**
 * Task Entity に対する検索条件を生成するクラス
 *
 * これをRepository層で使用することで、検索条件を動的に生成することができる。
 */
public class TaskSpecification {

  /**
   * プロジェクトが null の場合の検索条件を生成する。
   */
  public Specification<Task> projectIdIsNull() {
    // root: 検索対象のエンティティを指す。（今回だと、 Task Entity）
    // query: クエリの設定をするためのクエリを指す。（ここでは未使用）
    // _____| 例えば、`query.orderBy(builder.asc(root.get("id")));` の場合、並び替え条件を設定出来る。
    // _____| 他には、「取得フィールドの指定(SELECT句）」や「グループ化（GROUP BY）」なども可能。
    // builder: 検索条件（WHERE句）を生成するためのビルダーを指す。
    // _______| builder.equal() や builder.isNull() など、様々な検索条件を生成するメソッドが用意されている。
    return (root, query, builder) -> builder.isNull(root.get("project"));
  }

  /**
   * プロジェクトが指定されている場合の検索条件で、プロジェクトIDが一致するものを取得する検索条件を生成する。
   */
  public Specification<Task> projectIdEquals(Integer projectId) {
    if (projectId == null) {
      return projectIdIsNull();
    }

    // root.get("project") で、Task Entity の project フィールド（Project 型）を取得している。
    // 更に、その project の id を取得している。
    return (root, query, builder) -> builder.equal(root.get("project").get("id"), projectId);
  }
}
