package com.example.team_p.service;

import com.example.team_p.dto.UserRequestDto;
import com.example.team_p.dto.UserDto;
import com.example.team_p.dto.UserResponseDto;
import com.example.team_p.entity.User;
import com.example.team_p.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import java.util.concurrent.RejectedExecutionException;

@Service
@RequiredArgsConstructor
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public void signup(UserRequestDto userRequestDto) {
        String username = userRequestDto.getUsername();
        String password = passwordEncoder.encode(userRequestDto.getPassword());
        String name = userRequestDto.getName();
        String age = userRequestDto.getAge();
        String userinfo = userRequestDto.getUser_info();
        String userurl = userRequestDto.getUser_url();

        if (userRepository.findByUsername(username).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 유저 입니다.");
        }

        User user = new User(username,password,name,age,userinfo,userurl);
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
    public UserResponseDto updateUser(Long userId, User userDetails, UserRequestDto res){
        User user = getUser(userId, userDetails);

        user.setUser_url(res.getUser_url());
        user.setUser_info(res.getUser_info());
        user.setAge(res.getAge());

        return new UserResponseDto(user);
    }

    public User getUser(Long userId, User userDetails){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 ID입니다."));

        if(!userRepository.findById(userId).get().getId().equals(userDetails.getId())) {
            throw new RejectedExecutionException("작성자만 수정할 수 있습니다.");
        }

        return user;
    }
}