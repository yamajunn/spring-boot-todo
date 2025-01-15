package com.example.todo.dto.request.tasks;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class TaskSearchRequest {

  // プロジェクト単位で絞り込みをするために使用するパラメータ
  private final Integer projectId;
}
