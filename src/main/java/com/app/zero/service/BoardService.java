package com.app.zero.service;

import com.app.zero.config.util.SecurityUtil;
import com.app.zero.domain.board.Board;
import com.app.zero.domain.user.User;
import com.app.zero.dto.board.BoardListResponseDto;
import com.app.zero.dto.board.BoardRequestDto;
import com.app.zero.dto.board.BoardResponseDto;
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
        return new BoardResponseDto(board.getBoardIdx(), board.getTitle(), board.getDescription(), board.getViewCount(), user);
    }

    public List<BoardListResponseDto> getBoards() {
        List<BoardListResponseDto> boards = boardRepository.findAll()
                .stream()
                .map(BoardListResponseDto::new)
                .collect(Collectors.toList());
        return boards;
    }

}
