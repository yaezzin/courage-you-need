package com.app.zero.service;

import com.app.zero.config.util.SecurityUtil;
import com.app.zero.domain.board.Board;
import com.app.zero.domain.user.User;
import com.app.zero.dto.board.BoardResponseDto;
import com.app.zero.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class MyPageService {
    private final BoardRepository boardRepository;

    public List<BoardResponseDto> findUserBoards(User user) {
        List<BoardResponseDto> boards = boardRepository.findByUser(user)
                .stream()
                .map(BoardResponseDto::new)
                .collect(Collectors.toList());
        return boards;
    }


}
