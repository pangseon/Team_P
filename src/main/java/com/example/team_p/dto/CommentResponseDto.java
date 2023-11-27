package com.example.team_p.dto;


import com.example.team_p.entity.Comment;
import com.example.team_p.entity.Post;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommentResponseDto extends CommonResponseDto {
    private Long id;

    private String content;

    private UserDto user;

    private LocalDateTime createDate;

    public CommentResponseDto(Comment comment) {
        this.id = comment.getId();
        this.content = comment.getText();
        this.user = new UserDto(comment.getUser());
        this.createDate = comment.getCreateDate();
    }

}
