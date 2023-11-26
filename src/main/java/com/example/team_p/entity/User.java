package com.example.team_p.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String userid;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String age;
    @Column(nullable = false)
    private String user_info;
    @Column(nullable = false)
    private String user_url;



    public User(String username, String password,String user_url,String age,String user_info,String userid) {
        this.username = username;
        this.userid = userid;
        this.password = password;
        this.age=age;
        this.user_info = user_info;
        this.user_url = user_url;
    }
}