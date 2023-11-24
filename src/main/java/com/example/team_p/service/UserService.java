package com.example.team_p.service;

import com.example.team_p.dto.LoginRequestDto;
import com.example.team_p.dto.UserRequestDto;
import com.example.team_p.dto.LoginRequestDto;
import com.example.team_p.dto.UserRequestDto;
import com.example.team_p.entity.User;
import com.example.team_p.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service

public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    public  UserService(UserRepository userRepository,PasswordEncoder passwordEncoder) {
        this.passwordEncoder=passwordEncoder;
        this.userRepository=userRepository;

    }
    public void signup(@Valid UserRequestDto res){
        String userid = res.getUserid();
        String password = passwordEncoder.encode(res.getPassword());

        if (userRepository.findByUserid(userid).isPresent()){
            throw new IllegalArgumentException("이미 존재하는 유저입니다");
        }
        User user = new User(userid,password);
        userRepository.save(user);

    }
    public void login(@Valid LoginRequestDto requestDto){
        String userid = requestDto.getUserid();
        String password = requestDto.getPassword();

        User user = userRepository.findByUserid(userid).orElseThrow(
                ()-> new IllegalArgumentException("등록된 id 없습니다")

        );
        //-> 비밀번호 확인
        if (!passwordEncoder.matches(password,user.getPassword())){
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다");
        }
        // JWT 생성 후 쿠키에 저장 후 Response 객체에 추가


    }
}

