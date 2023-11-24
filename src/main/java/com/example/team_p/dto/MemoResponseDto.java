package com.sparta.memo2.dto;

import com.sparta.memo2.entity.Memo;
import lombok.Getter;

@Getter
public class MemoResponseDto {
  private Long id;
  private String username;
  private String contents;
  private String age;
  private String blog;
  private String git;

  public MemoResponseDto(Memo memo) {
    this.id = memo.getId();
    this.username = memo.getUsername();
    this.contents = memo.getContents();
    this.age = memo.getAge();
    this.blog = memo.getBlog();
    this.git = memo.getGit();
  }
}