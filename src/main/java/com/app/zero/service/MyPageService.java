package com.app.zero.service;

import com.app.zero.config.util.SecurityUtil;
import com.app.zero.domain.board.Board;
import com.app.zero.domain.board.Wish;
import com.app.zero.domain.user.User;
import com.app.zero.dto.board.BoardResponseDto;
import com.app.zero.repository.BoardRepository;
import com.app.zero.repository.WishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class MyPageService {
    
    private final BoardRepository boardRepository;
    private final WishRepository wishRepository;
    
    public List<BoardResponseDto> findUserBoards(User user) {
        List<BoardResponseDto> boards = boardRepository.findByUser(user)
                .stream()
                .map(BoardResponseDto::new)
                .collect(Collectors.toList());
        return boards;
    }

    public List<BoardResponseDto> findUserWishBoards(User user) {
        // 로직 개선 필요함
        List<Wish> wishes = wishRepository.findAllByUser(user);
        List<Board> boards = boardRepository.findAll();
        List<BoardResponseDto> boardList = new ArrayList<>();
        for (Board b : boards) {
            for(Wish w : wishes) {
                if (b.getId() == w.getBoard().getId()) {
                    boardList.add(new BoardResponseDto(b));
                }
            }
        }
        return boardList;
    }
}
