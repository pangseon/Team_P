package com.example.team_p.dto;

import com.example.team_p.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserResponseDto {
    private String age;
    private String user_info;
    private String user_url;


    public UserResponseDto(User user){
        this.age=user.getAge();
        this.user_info=getUser_info();
        this.user_url=getUser_url();
    }
}
