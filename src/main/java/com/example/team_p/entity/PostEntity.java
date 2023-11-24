package com.example.team_p.entity;

import com.example.team_p.dto.PostRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "post")
@NoArgsConstructor
public class PostEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;
    @Column(nullable = false, length = 20)
    private String title;
    @Column(nullable = false, length = 500)
    private String contents;

    public PostEntity(PostRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
    }
}
