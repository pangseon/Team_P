package com.sparta.memo2.entity;

import com.sparta.memo2.dto.MemoRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Memo {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column
  private String username;
  @Column
  private String contents;
  @Column
  private String blog;
  @Column
  private String age;
  @Column
  private String git;

  public Memo(MemoRequestDto requestDto) {
    this.username = requestDto.getUsername();
    this.contents = requestDto.getContents();
    this.age = requestDto.getAge();
    this.blog = requestDto.getBlog();
    this.git = requestDto.getGit();
  }

  public void update(MemoRequestDto requestDto) {
    this.username=requestDto.getUsername();
    this.contents=requestDto.getContents();
    this.age = requestDto.getAge();
    this.blog = requestDto.getBlog();
    this.git = requestDto.getGit();
  }
}