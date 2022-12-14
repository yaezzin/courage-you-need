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

    public List<BoardResponseDto> getBoardsByKeyword(String keyword) {
        List<BoardResponseDto> boards = boardRepository.findByTitleContaining(keyword)
                .stream()
                .map(BoardResponseDto::new)
                .collect(Collectors.toList());
        return boards;
    }

    public List<BoardResponseDto> getBoardsByWishCount() {
        List<BoardResponseDto> boards = boardRepository.findAllOrderByWishDesc()
                .stream()
                .map(BoardResponseDto::new)
                .collect(Collectors.toList());
        return boards;
    }

    public List<BoardResponseDto> getBoardsByCategory(Long id) {
        List<BoardResponseDto> boards = boardRepository.finaByCategory(id)
                .stream()
                .map(BoardResponseDto::new)
                .collect(Collectors.toList());
        return boards;
    }

    @Transactional
    public BoardResponseDto create(BoardRequestDto requestDto) {
        User user = userRepository.findByPhoneNumber(SecurityUtil.getLoginUsername()).orElseThrow();
        requestDto.setUser(user); // ?????? ????????? ???????????? Dto??? ????????????
        requestDto.setCategory(requestDto.getCategory());

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
        return boardRepository.updateViewCount(id); // ?????? 1 ?????? -> ????????? ????????? ???????????? ??????????????????
    }

    @Transactional
    public String delete(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(() -> new BoardNotFoundException());
        // ????????? ???????????? ?????? ???????????? ????????? ????????? ??????
        if (board.getUser().equals(loginUser())) {
            boardRepository.deleteById(id);
            return "????????? ????????? ?????????????????????.";
        } else {
            return "????????? ?????? ????????? ????????????.";
        }
    }

    // ?????? ???????????? ?????? ??????
    private User loginUser() {
        return userRepository.findByPhoneNumber(SecurityUtil.getLoginUsername()).orElseThrow();
    }


}
