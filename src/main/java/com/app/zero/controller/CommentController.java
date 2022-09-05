package com.app.zero.controller;

import com.app.zero.config.util.SecurityUtil;
import com.app.zero.domain.user.User;
import com.app.zero.dto.comment.CommentRequestDto;
import com.app.zero.dto.comment.CommentResponseDto;
import com.app.zero.repository.UserRepository;
import com.app.zero.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class CommentController {

    private final CommentService commentService;
    private final UserRepository userRepository;

    @PostMapping("/boards/{boardIdx}/comments")
    public ResponseEntity<CommentResponseDto> createComment(@PathVariable("boardIdx") Long id, @RequestBody CommentRequestDto commentRequestDto) {
        return new ResponseEntity<>(commentService.createComment(id, loginUser(), commentRequestDto), HttpStatus.OK);
    }

    private User loginUser() {
        return userRepository.findByPhoneNumber(SecurityUtil.getLoginUsername()).orElseThrow();
    }
}
