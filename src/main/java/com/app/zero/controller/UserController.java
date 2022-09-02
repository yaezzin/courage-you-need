package com.app.zero.controller;

import com.app.zero.dto.user.LoginRequestDto;
import com.app.zero.dto.user.LoginResponseDto;
import com.app.zero.dto.user.SignUpRequestDto;
import com.app.zero.dto.user.UserResponseDto;
import com.app.zero.service.AuthService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final AuthService authService;

    @PostMapping("/user/sign-up")
    public ResponseEntity<UserResponseDto> singUp(@RequestBody SignUpRequestDto signUpRequestDto) {
        return new ResponseEntity<>(authService.signUp(signUpRequestDto), HttpStatus.OK);
    }

    @PostMapping("/user/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) {
        return new ResponseEntity<>(authService.login(loginRequestDto), HttpStatus.OK);
    }
}

