package com.app.zero.controller;

import com.app.zero.config.util.SecurityUtil;
import com.app.zero.domain.user.User;
import com.app.zero.dto.comment.CommentRequestDto;
import com.app.zero.dto.comment.CommentResponseDto;
import com.app.zero.dto.comment.CommentUpdateRequestDto;
import com.app.zero.repository.UserRepository;
import com.app.zero.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class CommentController {

    private final CommentService commentService;
    private final UserRepository userRepository;

    @PostMapping("/boards/{boardIdx}/comments")
    public ResponseEntity<CommentResponseDto> createComment(@PathVariable("boardIdx") Long id, @RequestBody CommentRequestDto commentRequestDto) {
        return new ResponseEntity<>(commentService.createComment(id, loginUser(), commentRequestDto), HttpStatus.OK);
    }

    @PutMapping("/boards/{boardIdx}/comments/{commentIdx}")
    public ResponseEntity<CommentResponseDto> updateComment(@PathVariable("boardIdx") Long boardIdx,
                                          @PathVariable("commentIdx") Long commentIdx,
                                          @RequestBody CommentUpdateRequestDto requestDto) {
        return new ResponseEntity<>(commentService.updateComment(commentIdx, loginUser(), requestDto), HttpStatus.OK);
    }

    @DeleteMapping("/boards/{boardIdx}/comments/{commentIdx}")
    public void deleteComment(@PathVariable("boardIdx") Long boardIdx, @PathVariable("commentIdx") Long commentIdx) {
        commentService.deleteComment(commentIdx, loginUser());
    }

    private User loginUser() {
        return userRepository.findByPhoneNumber(SecurityUtil.getLoginUsername()).orElseThrow();
    }
}
