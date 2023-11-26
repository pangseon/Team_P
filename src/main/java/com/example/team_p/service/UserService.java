package com.example.team_p.service;

import com.example.team_p.dto.UserRequestDto;
import com.example.team_p.entity.User;
import com.example.team_p.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public void signup(UserRequestDto userRequestDto) {
        String username = userRequestDto.getUsername();
        String password = passwordEncoder.encode(userRequestDto.getPassword());
        String age = userRequestDto.getAge();
        String userid = userRequestDto.getUserid();
        String userurl = userRequestDto.getUserurl();
        String userinfo = userRequestDto.getUser_info();
        if (userRepository.findByUsername(username).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 유저 입니다.");
        }

        User user = new User(userid,password,age,userinfo,userurl,username);
        userRepository.save(user);
    }

    public void login(UserRequestDto userRequestDto) {
        String username = userRequestDto.getUsername();
        String password = userRequestDto.getPassword();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("등록된 유저가 없습니다."));

        if(!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
    }
}
