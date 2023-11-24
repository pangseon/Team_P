package com.example.team_p.dto;

import com.example.team_p.entity.UserInfo;
import lombok.Getter;

@Getter
public class UserInfoResponseDto {
  private Long id;
  private String contents;
  private String age;
  private String blog;
  private String git;

  public UserInfoResponseDto(UserInfo memo) {
    this.id = memo.getId();
    this.contents = memo.getContents();
    this.age = memo.getAge();
    this.blog = memo.getBlog();
    this.git = memo.getGit();
  }
}