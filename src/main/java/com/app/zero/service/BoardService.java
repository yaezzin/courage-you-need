package com.app.zero.service;

import com.app.zero.config.util.SecurityUtil;
import com.app.zero.domain.board.Board;
import com.app.zero.domain.user.User;
import com.app.zero.dto.board.BoardListResponseDto;
import com.app.zero.dto.board.BoardRequestDto;
import com.app.zero.dto.board.BoardResponseDto;
import com.app.zero.exception.board.BoardNotFoundException;
import com.app.zero.exception.user.UserNotFoundException;
import com.app.zero.repository.BoardRepository;
import com.app.zero.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    @Transactional
    public BoardResponseDto create(BoardRequestDto requestDto) {
        User user = userRepository.findByPhoneNumber(SecurityUtil.getLoginUsername()).orElseThrow();
        Board board = boardRepository.save(requestDto.toEntity());
        return new BoardResponseDto(board.getId(), board.getTitle(), board.getDescription(), board.getViewCount(), user);
    }

    public List<BoardListResponseDto> getBoards() {
        List<BoardListResponseDto> boards = boardRepository.findAll()
                .stream()
                .map(BoardListResponseDto::new)
                .collect(Collectors.toList());
        return boards;
    }

    public Board getBoard(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(() -> new BoardNotFoundException());
        return board;
    }

    @Transactional
    public BoardResponseDto update(Long boardIdx, BoardRequestDto requestDto) {
        Board board = boardRepository.findById(boardIdx).orElseThrow(() -> new BoardNotFoundException());
        board.update(requestDto.getTitle(), requestDto.getDescription());
        return new BoardResponseDto(board.getId(), board.getTitle(), board.getDescription(), board.getViewCount(), loginUser());
    }

    // 현재 로그인한 유저 리턴
    private User loginUser() {
        return userRepository.findByPhoneNumber(SecurityUtil.getLoginUsername()).orElseThrow();
    }

}
