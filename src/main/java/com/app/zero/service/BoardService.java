package com.app.zero.service;

import com.app.zero.config.util.SecurityUtil;
import com.app.zero.domain.board.Board;
import com.app.zero.domain.user.User;
import com.app.zero.dto.board.BoardRequestDto;
import com.app.zero.dto.board.BoardResponseDto;
import com.app.zero.exception.board.BoardNotFoundException;
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

    public List<BoardResponseDto> getBoards() {
        List<BoardResponseDto> boards = boardRepository.findAll()
                .stream()
                .map(BoardResponseDto::new)
                .collect(Collectors.toList());
        return boards;
    }

    public BoardResponseDto getBoard(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(() -> new BoardNotFoundException());
        return new BoardResponseDto(board);
    }

    public List<BoardResponseDto> getBoardsByViewCount() {
        List<BoardResponseDto> boards = boardRepository.findAllOrderByViewCountDesc()
                .stream()
                .map(BoardResponseDto::new)
                .collect(Collectors.toList());
        return boards;
    }


    @Transactional
    public BoardResponseDto create(BoardRequestDto requestDto) {
        User user = userRepository.findByPhoneNumber(SecurityUtil.getLoginUsername()).orElseThrow();
        requestDto.setUser(user); // 유저 정보를 가져와서 Dto에 담아준다
        Board board = boardRepository.save(requestDto.toEntity());
        return new BoardResponseDto(board);
    }

    @Transactional
    public BoardResponseDto update(Long boardIdx, BoardRequestDto requestDto) {
        Board board = boardRepository.findById(boardIdx).orElseThrow(() -> new BoardNotFoundException());
        board.update(requestDto.getTitle(), requestDto.getDescription());
        return new BoardResponseDto(board);
    }

    @Transactional
    public int updateViewCount(Long id) {
        return boardRepository.updateViewCount(id); // 항상 1 리턴 -> 하지만 게시물 조회하면 증가되어있음
    }

    @Transactional
    public String delete(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(() -> new BoardNotFoundException());
        // 게시글 작성자가 지금 로그인한 유저와 같으면 삭제
        if (board.getUser().equals(loginUser())) {
            boardRepository.deleteById(id);
            return "게시글 삭제에 성공하였습니다.";
        } else {
            return "게시글 삭제 권한이 없습니다.";
        }
    }

    // 현재 로그인한 유저 리턴
    private User loginUser() {
        return userRepository.findByPhoneNumber(SecurityUtil.getLoginUsername()).orElseThrow();
    }

}
