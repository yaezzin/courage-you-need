package com.app.zero.controller;

import com.app.zero.domain.user.User;
import com.app.zero.dto.user.UserResponseDto;

import com.app.zero.dto.user.UserUpdateRequestDto;
import com.app.zero.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /* 전체 유저 조회 */
    @GetMapping("/users")
    public ResponseEntity<List<User>> findAllUsers() {
        return new ResponseEntity<>(userService.findAllUsers(), HttpStatus.OK);
    }

    /* 유저 조회 by userIdx */
    @GetMapping("/users/{userIdx}")
    public ResponseEntity<UserResponseDto> findById(@PathVariable("userIdx") Long id) {
        return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
    }

    /* 유저 프로필 수정 - 프로필 사진, 닉네임 */
    @PatchMapping("/users/{userIdx}")
    public ResponseEntity<Long> update(@PathVariable("userIdx") Long id, @RequestBody UserUpdateRequestDto requestDto) {
        return new ResponseEntity<>(userService.update(requestDto), HttpStatus.OK);
    }

    /* 유저 탈퇴 */
    @DeleteMapping("/users/{userIdx}")
    public ResponseEntity<Long> delete(@PathVariable("userIdx") Long id) {
        return new ResponseEntity<>(userService.delete(id), HttpStatus.OK);
    }



}

