package com.app.zero.service;

import com.app.zero.domain.board.Board;
import com.app.zero.domain.board.Comment;
import com.app.zero.domain.user.User;
import com.app.zero.dto.comment.CommentRequestDto;
import com.app.zero.dto.comment.CommentResponseDto;
import com.app.zero.exception.board.BoardNotFoundException;
import com.app.zero.repository.BoardRepository;
import com.app.zero.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public CommentResponseDto createComment(Long id, User user, CommentRequestDto commentRequestDto) {
        Board board = boardRepository.findById(id).orElseThrow(() -> new BoardNotFoundException());
        Comment comment = commentRequestDto.toEntity();
        comment.addCommentInfo(user, board);
        commentRepository.save(comment);
        return new CommentResponseDto(comment.getId(), comment.getComment(), user.getNickname(), user.getProfileImage(), comment.getModifiedAt());
    }
}
