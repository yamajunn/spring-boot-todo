package com.example.todo.entity;

import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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

  // priority は、 0, 1, 2 のいずれかの値を持つ。
  // TODO: このフィールドは、Enum型を使って定義するように修正予定。
  @Column(nullable = false, columnDefinition = "SMALLINT")
  private Integer priority;

  @Column(columnDefinition = "TEXT")
  private String memo;

  @Column(name = "deadline_at")
  private LocalDateTime deadlineAt;

  @Column(name = "completed_at")
  private LocalDateTime completedAt;
}
