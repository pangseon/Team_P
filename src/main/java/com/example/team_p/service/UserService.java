package com.example.team_p.service;

import com.example.team_p.dto.UserRequestDto;
import com.example.team_p.dto.UserResponseDto;
import com.example.team_p.entity.User;
import com.example.team_p.repository.UserRepository;
import jakarta.transaction.Transactional;
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
        String user_url = userRequestDto.getUser_url();
        String userinfo = userRequestDto.getUser_info();
        if (userRepository.findByUsername(username).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 유저 입니다.");
        }

        User user = new User(username,password,userid,age,userinfo,user_url);
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
    @Transactional
    public UserResponseDto updateUser(UserRequestDto res){
        User user = getUser(res);
        user.setUser_url(res.getUser_url());
        user.setUser_info(res.getUser_info());
        user.setAge(res.getAge());
        return new UserResponseDto(user);
    }

    public User getUser(UserRequestDto res){
        User user = userRepository.findByUsername(res.getUsername())
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 유저입니다"));

        if (!passwordEncoder.matches(res.getPassword(), user.getPassword()))
        {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        return user;
    }
}
