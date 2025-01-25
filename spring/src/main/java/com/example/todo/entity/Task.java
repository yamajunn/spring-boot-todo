package com.example.todo.entity;

import java.time.LocalDateTime;
import com.example.todo.enums.TaskPriority;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "tasks")
@EqualsAndHashCode(callSuper = false) // 親クラスの equals, hashCode を使わない（これを設定しないと警告が出る）
public class Task extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  /**
   * @ManyToOne JPA（Java Persistence API）のアノテーション。多対1のリレーションシップを示す。
   *            これを付与する事により、エンティティ取得時に、関連するエンティティも取得することができる。
   *
   * @JoinColumn JPA（Java Persistence API）のアノテーション。リレーションシップの所有者側のエンティティに付与する。
   *             name属性には、外部キーのカラム名を指定する。
   */
  @ManyToOne
  @JoinColumn(name = "project_id")
  private Project project;

  @Column(length = 255, nullable = false)
  private String name;

  /**
   * @Enumerated EnumType.ORDINAL を指定することで、列挙型の値をデータベースに保存する際に、列挙型のインデックス（0, 1, 2, ...）を保存する。
   */
  @Column(nullable = false, columnDefinition = "SMALLINT")
  @Enumerated(EnumType.ORDINAL)
  private TaskPriority priority = TaskPriority.MEDIUM;

  @Column(columnDefinition = "TEXT")
  private String memo;

  @Column(name = "deadline_at")
  private LocalDateTime deadlineAt;

  @Column(name = "completed_at")
  private LocalDateTime completedAt;

  /**
   * タスクが完了済みかどうかを返す。
   *
   * @return 完了済みの場合は true、未完了の場合は false
   */
  public boolean isCompleted() {
    return this.completedAt != null;
  }

  /**
   * タスクの完了状態を切り替える。
   *
   * 完了済みの場合は未完了に、未完了の場合は完了済に。
   */
  public void toggleCompleted() {
    this.setCompletedAt(this.isCompleted() ? null : LocalDateTime.now());
  }
}
