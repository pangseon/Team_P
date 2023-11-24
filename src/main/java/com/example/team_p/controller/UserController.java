package com.example.team_p.controller;

import com.example.team_p.dto.CommonResponseDto;
import com.example.team_p.dto.LoginRequestDto;
import com.example.team_p.dto.UserRequestDto;
import com.example.team_p.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/users")
@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<CommonResponseDto> signup(@Valid @RequestBody UserRequestDto userRequestDto){

        try {
            userService.signup(userRequestDto);
        }
        catch (IllegalArgumentException exception){
            return ResponseEntity.badRequest().body(new CommonResponseDto(
                    "중복된 username 입니다",HttpStatus.BAD_REQUEST.value()
            ));
        }

        return ResponseEntity.status(
                HttpStatus.CREATED.value())
                .body(new CommonResponseDto("회원가입 성공", HttpStatus.CREATED.value()));
    }
    @PostMapping("/info")
    public ResponseEntity<CommonResponseDto>login(@Valid @RequestBody LoginRequestDto res){
        try{
            userService.login(res);
        }
        catch (IllegalArgumentException exception){
            return ResponseEntity.badRequest().body(new CommonResponseDto(
                    "id 또는 비밀번호 오류 입니다",HttpStatus.BAD_REQUEST.value()
            ));
        }

        return ResponseEntity.status(
                        HttpStatus.CREATED.value())
                .body(new CommonResponseDto("로그인 성공", HttpStatus.CREATED.value()));
        }
    }

