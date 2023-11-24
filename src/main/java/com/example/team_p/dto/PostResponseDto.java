package com.example.team_p.dto;

import com.example.team_p.entity.PostEntity;
public record PostResponseDto (
        Long userId,
        Long postId,
        String title,
        String contents
){
    public PostResponseDto(PostEntity post) {
        this(
                post.getPostId(),
                post.getUserId(),
                post.getTitle(),
                post.getContents()
        );
    }
}
