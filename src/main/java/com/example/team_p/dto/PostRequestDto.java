package com.example.team_p.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PostRequestDto {
    private String title;
    private String contents;
    private Long post_id;
}
