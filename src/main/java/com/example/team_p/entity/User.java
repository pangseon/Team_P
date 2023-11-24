package com.example.team_p.entity;

import com.example.team_p.dto.UserRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = true)
    private String username;
    @Column(nullable = false)
    private String userid;
    @Column(nullable = false)
    private String password;
    @Column(nullable = true)
    private String userinfo;

    public User(String userid,String password){
        this.userid=userid;
        this.password=password;
    }
    public User(UserRequestDto requestDto){
        this.userid = requestDto.getUserid();
        this.password = requestDto.getPassword();
        this.username = requestDto.getUsername();
        this.userinfo = requestDto.getUserinfo();

    }


}
