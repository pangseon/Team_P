package com.example.team_p.dto;


import com.example.team_p.entity.Post;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PostResponseDto extends CommonResponseDto {
    private Long id;

    private String title;

    private String content;

    // 유저 정보
    private UserDto user;

    private LocalDateTime createDate;

    public PostResponseDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.user = new UserDto(post.getUser());
        this.createDate = post.getCreateDate();
    }
}
