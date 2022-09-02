package com.app.zero.controller;

import com.app.zero.domain.user.User;
import com.app.zero.dto.user.SignUpRequestDto;
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
    public ResponseEntity<User> singUp(@RequestBody SignUpRequestDto signUpRequestDto) {
        return new ResponseEntity<>(userService.signUp(signUpRequestDto), HttpStatus.OK);
    }
}

