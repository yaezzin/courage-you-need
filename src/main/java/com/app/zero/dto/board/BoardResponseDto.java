package com.app.zero.dto.board;


import com.app.zero.domain.board.Board;
import com.app.zero.domain.board.Comment;
import com.app.zero.domain.board.Wish;
import com.app.zero.domain.user.User;
import com.app.zero.dto.comment.CommentResponseDto;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
public class BoardResponseDto {
    private Long boardIdx;
    private String title;
    private String description;
    private int viewCount;
    private long wishCount;
    private String nickname;
    private long category;
    //private List<CommentResponseDto> comments;

    public BoardResponseDto(Board board) {
        this.boardIdx = board.getId();
        this.title = board.getTitle();
        this.description = board.getDescription();
        this.viewCount = board.getViewCount();
        this.wishCount = board.getWish().stream().count();
        this.nickname = board.getUser().getNickname();
        this.category = board.getCategory().getId();
        //this.comments = board.getComments().stream().map(CommentResponseDto::new).collect(Collectors.toList());
    }
}
