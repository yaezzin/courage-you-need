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
    public ResponseEntity<UserResponseDto> findById(@PathVariable("userIdx") Long id) {
        return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
    }

    @DeleteMapping("/users/{userIdx}")
    public ResponseEntity<Long> delete(@PathVariable("userIdx") Long id) {
        return new ResponseEntity<>(userService.delete(id), HttpStatus.OK);
    }

    @PatchMapping("/users/{userIdx}")
    public ResponseEntity<Long> update(@PathVariable("userIdx") Long id, @RequestBody UserUpdateRequestDto requestDto) {
        return new ResponseEntity<>(userService.update(requestDto), HttpStatus.OK);
    }
}

