package com.example.team_p.dto;

// import com.example.team_p.entity.PostEntity;
// public record PostResponseDto (
//         Long userId,
//         Long postId,
//         String title,
//         String contents
// ){
//     public PostResponseDto(PostEntity post) {
//         this(
//                 post.getPostId(),
//                 post.getUserId(),
//                 post.getTitle(),
//                 post.getContents()
//         );
// =======
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
        
    public PostResponseDto(PostEntity post){
      this.id = post.getPostId(),
      this.title = post.getTitle(),
      this.content = post.getContents()
    }
    }
}
