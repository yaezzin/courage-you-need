package com.app.zero.controller;

import com.app.zero.dto.user.LoginRequestDto;
import com.app.zero.dto.user.LoginResponseDto;
import com.app.zero.dto.user.SignUpRequestDto;
import com.app.zero.dto.user.UserResponseDto;
import com.app.zero.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/user/sign-up")
    public ResponseEntity<UserResponseDto> singUp(@RequestBody SignUpRequestDto signUpRequestDto) {
        return new ResponseEntity<>(userService.signUp(signUpRequestDto), HttpStatus.OK);
    }

    @PostMapping("/user/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) {
        return new ResponseEntity<>(userService.login(loginRequestDto), HttpStatus.OK);
    }
}

