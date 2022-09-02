package com.app.zero.controller;

import com.app.zero.dto.user.LoginRequestDto;
import com.app.zero.dto.user.LoginResponseDto;
import com.app.zero.dto.user.SignUpRequestDto;
import com.app.zero.dto.user.UserResponseDto;
import com.app.zero.service.AuthService;

import com.app.zero.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final AuthService authService;
    private final UserService userService;

    @PostMapping("/users/sign-up")
    public ResponseEntity<UserResponseDto> singUp(@RequestBody SignUpRequestDto signUpRequestDto) {
        return new ResponseEntity<>(authService.signUp(signUpRequestDto), HttpStatus.OK);
    }

    @PostMapping("/users/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) {
        return new ResponseEntity<>(authService.login(loginRequestDto), HttpStatus.OK);
    }

    @GetMapping("/users/{userIdx}")
    public ResponseEntity<UserResponseDto> findById(@PathVariable Long userIdx) {
        return new ResponseEntity<>(userService.findById(userIdx), HttpStatus.OK);
    }

    @DeleteMapping("/users/{userIdx}")
    public ResponseEntity<Long> delete(@PathVariable Long userIdx) {
        return new ResponseEntity<>(userService.delete(userIdx), HttpStatus.OK);
    }
}

