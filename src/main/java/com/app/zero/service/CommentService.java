package com.app.zero.service;

import com.app.zero.domain.board.Board;
import com.app.zero.domain.board.Comment;
import com.app.zero.domain.user.User;
import com.app.zero.dto.comment.CommentRequestDto;
import com.app.zero.dto.comment.CommentResponseDto;
import com.app.zero.dto.comment.CommentUpdateRequestDto;
import com.app.zero.exception.board.BoardNotFoundException;
import com.app.zero.exception.comment.CommentNotAuthException;
import com.app.zero.exception.comment.CommentNotFoundException;
import com.app.zero.repository.BoardRepository;
import com.app.zero.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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

    public List<CommentResponseDto> getComments(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(() -> new BoardNotFoundException());
        List<Comment> comments = commentRepository.findAllByBoardId(board.getId());
        List<CommentResponseDto> commentList = new ArrayList<>();
        for (Comment c : comments) {
            CommentResponseDto commentResponseDto = new CommentResponseDto(c); // 응답값으로 바꾸기
            commentList.add(commentResponseDto);
        }
        return commentList;
    }

    @Transactional
    public CommentResponseDto updateComment(Long id, User user, CommentUpdateRequestDto requestDto) {
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new CommentNotFoundException());

        // 댓글 작성자랑 현재 로그인한 작성자가 다르면
        if (!comment.getUser().getNickname().equals(user.getNickname())) {
            throw new CommentNotAuthException();
        }

        comment.updateComment(requestDto);
        commentRepository.save(comment);
        return new CommentResponseDto(comment.getId(), comment.getComment(), user.getNickname(), user.getProfileImage(), comment.getModifiedAt());
    }

    @Transactional
    public void deleteComment(Long id, User user) {
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new CommentNotFoundException());

        if (!comment.getUser().getNickname().equals(user.getNickname())) {
            throw new CommentNotAuthException();
        }
        commentRepository.deleteById(id);
    }

}
