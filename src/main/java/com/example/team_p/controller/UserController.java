package com.example.team_p.controller;

import com.example.team_p.dto.*;
import com.example.team_p.entity.User;
import com.example.team_p.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @PostMapping("/login")
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
     @PostMapping("login/{id}")
     public ResponseEntity<CommonResponseDto> createUserInfo(@PathVariable Long id , @RequestBody UserInfoRequestDto res, User user){
            try {
                userService.CreateProfile(res,user);
            }
            catch (IllegalArgumentException exception){
                return ResponseEntity.badRequest().body(new CommonResponseDto(
                        "잘못된 등록정보입니다.",HttpStatus.BAD_REQUEST.value()
                ));
            }
         return ResponseEntity.status(
                         HttpStatus.CREATED.value())
                 .body(new CommonResponseDto("회원가입 성공", HttpStatus.CREATED.value()));
     }


    @GetMapping("/login/{id}/info")
    public List<UserInfoResponseDto> getUserInfo(){
        return null;
    }
    }

