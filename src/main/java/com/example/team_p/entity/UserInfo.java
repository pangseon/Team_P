package com.example.team_p.entity;

import com.example.team_p.dto.UserInfoRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "userinfo")
public class UserInfo {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column
  private String contents;
  @Column
  private String blog;
  @Column
  private String age;
  @Column
  private String git;
  @ManyToOne
  @JoinColumn(name = "userid")
   User user;

  public UserInfo(String contents,String age, String blog,String git,User user) {
    this.contents = contents;
    this.age = age;
    this.blog = blog;
    this.git = git;
    this.user = user;
  }

  public void update(UserInfoRequestDto requestDto) {
    this.contents=requestDto.getContents();
    this.age = requestDto.getAge();
    this.blog = requestDto.getBlog();
    this.git = requestDto.getGit();
  }
}