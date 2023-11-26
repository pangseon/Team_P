package com.example.team_p.controller;

import com.example.team_p.dto.CommonResponseDto;
import com.example.team_p.dto.UserRequestDto;
import com.example.team_p.dto.UserResponseDto;
import com.example.team_p.jwt.JwtUtil;
import com.example.team_p.service.UserDetailsImpl;
import com.example.team_p.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/users")
@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final JwtUtil jwtUtil;

    @PostMapping("/signup")
    public ResponseEntity<CommonResponseDto> signup(@Valid @RequestBody UserRequestDto userRequestDto) {
        try {
            userService.signup(userRequestDto);
        } catch (IllegalArgumentException exception) {
            return ResponseEntity.badRequest().body(new CommonResponseDto("중복된 username 입니다.", HttpStatus.BAD_REQUEST.value()));
        }

        return ResponseEntity.status(HttpStatus.CREATED.value())
                .body(new CommonResponseDto("회원가입 성공", HttpStatus.CREATED.value()));
    }
    @PostMapping("/login")
    public ResponseEntity<CommonResponseDto> login(@RequestBody UserRequestDto userRequestDto, HttpServletResponse response) {
        try {
            userService.login(userRequestDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new CommonResponseDto(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
        }

        response.setHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(userRequestDto.getUsername()));

        return ResponseEntity.ok().body(new CommonResponseDto("로그인 성공", HttpStatus.OK.value()));
    }

    @PutMapping("{userId}")
    public ResponseEntity<CommonResponseDto> putPost(@PathVariable Long userId, @AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody UserRequestDto res) {
        try {
            UserResponseDto responseDto = userService.updateUser(userId, userDetails.getUser(), res);
            return ResponseEntity.ok().body(responseDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new CommonResponseDto("작성자만 수정할 수 있습니다.", HttpStatus.BAD_REQUEST.value()));
        }
//        return ResponseEntity.ok().body(new CommonResponseDto("수정이완료되었습니다", HttpStatus.OK.value()));
    }
}
