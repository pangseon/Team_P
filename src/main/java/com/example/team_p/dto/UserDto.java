package com.example.team_p.dto;

import com.example.team_p.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private String username;

    public UserDto(User user) {
        this.username = user.getUsername();
    }
}
