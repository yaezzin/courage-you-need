package com.app.zero.controller;

import com.app.zero.config.util.SecurityUtil;
import com.app.zero.domain.board.Wish;
import com.app.zero.domain.user.User;
import com.app.zero.dto.board.BoardResponseDto;
import com.app.zero.repository.UserRepository;
import com.app.zero.service.MyPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class MyPageController {

    private final MyPageService myPageService;
    private final UserRepository userRepository;

    /* 유저가 작성한 게시물 조회 */
    @GetMapping("/mypage/boards")
    public ResponseEntity<List<BoardResponseDto>> findUserBoards() {
        return new ResponseEntity<>(myPageService.findUserBoards(loginUser()), HttpStatus.OK);
    }

    /* 유저가 스크랩(wish)한 게시물 조회 */
    @GetMapping("/mypage/wish")
    public ResponseEntity<List<BoardResponseDto>> findUserWishBoards() {
        return new ResponseEntity<>(myPageService.findUserWishBoards(loginUser()), HttpStatus.OK);
    }

    private User loginUser() {
        return userRepository.findByPhoneNumber(SecurityUtil.getLoginUsername()).orElseThrow();
    }
}
