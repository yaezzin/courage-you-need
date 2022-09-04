package com.app.zero.controller;

import com.app.zero.domain.board.Board;
import com.app.zero.dto.board.BoardListResponseDto;
import com.app.zero.dto.board.BoardRequestDto;
import com.app.zero.dto.board.BoardResponseDto;
import com.app.zero.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class BoardController {

    private final BoardService boardService;

    @PostMapping("/boards")
    public ResponseEntity<BoardResponseDto> create(@RequestBody BoardRequestDto requestDto) {
        return new ResponseEntity<>(boardService.create(requestDto), HttpStatus.OK);
    }

    @GetMapping("/boards")
    public ResponseEntity<List<BoardListResponseDto>> getBoards() {
        return new ResponseEntity<>(boardService.getBoards(), HttpStatus.OK);
    }
}
