package com.app.zero.controller;

import com.app.zero.domain.board.Board;
import com.app.zero.dto.board.BoardRequestDto;
import com.app.zero.dto.board.BoardResponseDto;
import com.app.zero.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class BoardController {

    private final BoardService boardService;

    /* 게시물 생성 */
    @PostMapping("/boards")
    public ResponseEntity<BoardResponseDto> create(@RequestBody BoardRequestDto requestDto) {
        return new ResponseEntity<>(boardService.create(requestDto), HttpStatus.OK);
    }

    /* 게시물 전체 조회 */
    @GetMapping("/boards")
    public ResponseEntity<List<BoardResponseDto>> getBoards() {
        return new ResponseEntity<>(boardService.getBoards(), HttpStatus.OK);
    }

    /* 게시물 조회 by BoardIdx */
    @GetMapping("/boards/{boardIdx}")
    public ResponseEntity<BoardResponseDto> getBoard(@PathVariable("boardIdx") Long id) {
        return new ResponseEntity<>(boardService.getBoard(id), HttpStatus.OK);
    }

    /* 키워드 검색 */
    @GetMapping("/boards/keyword")
    public ResponseEntity<List<BoardResponseDto>> getBoardsByKeyword(@RequestParam String keyword) {
        return new ResponseEntity<>(boardService.getBoardsByKeyword(keyword), HttpStatus.OK);
    }

    /* 게시물 조회 - 조회수 */
    @GetMapping("/boards/order/view")
    public ResponseEntity<List<BoardResponseDto>> getBoardsByViewCount() {
        return new ResponseEntity<>(boardService.getBoardsByViewCount(), HttpStatus.OK);
    }

    /* 게시물 조회 - 좋아요 */
    @GetMapping("/boards/order/wish")
    public ResponseEntity<List<BoardResponseDto>> getBoardsByWishCount() {
        return new ResponseEntity<>(boardService.getBoardsByWishCount(), HttpStatus.OK);
    }

    /* 게시물 수정 */
    @PatchMapping("/boards/{boardIdx}")
    public ResponseEntity<BoardResponseDto> update(@PathVariable("boardIdx") Long id, @RequestBody BoardRequestDto requestDto) {
        return new ResponseEntity<>(boardService.update(id, requestDto), HttpStatus.OK);
    }

    /* 게시물 조회수 증가 */
    @PatchMapping("/boards/{boardIdx}/view")
    public ResponseEntity<Integer> updateViewCount(@PathVariable("boardIdx") Long id) {
        return new ResponseEntity<>(boardService.updateViewCount(id), HttpStatus.OK);
    }

    /* 게시물 삭제 */
    @DeleteMapping("/boards/{boardIdx}")
    public String delete(@PathVariable("boardIdx") Long id) {
        return boardService.delete(id);
    }

}
