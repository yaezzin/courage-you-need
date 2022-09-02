package com.app.zero.controller;

import com.app.zero.dto.user.UserResponseDto;

import com.app.zero.dto.user.UserUpdateRequestDto;
import com.app.zero.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/users/{userIdx}")
    public ResponseEntity<UserResponseDto> findById(@PathVariable Long userIdx) {
        return new ResponseEntity<>(userService.findById(userIdx), HttpStatus.OK);
    }

    @DeleteMapping("/users/{userIdx}")
    public ResponseEntity<Long> delete(@PathVariable Long userIdx) {
        return new ResponseEntity<>(userService.delete(userIdx), HttpStatus.OK);
    }

    @PatchMapping("/users/{userIdx}")
    public ResponseEntity<Long> update(@PathVariable Long userIdx, @RequestBody UserUpdateRequestDto requestDto) {
        return new ResponseEntity<>(userService.update(requestDto), HttpStatus.OK);
    }
}

