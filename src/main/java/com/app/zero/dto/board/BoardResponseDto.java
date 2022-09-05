package com.app.zero.dto.board;


import com.app.zero.domain.board.Board;
import com.app.zero.domain.board.Wish;
import com.app.zero.domain.user.User;
import lombok.*;

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
    private String category;

    public BoardResponseDto(Board board) {
        this.boardIdx = board.getId();
        this.title = board.getTitle();
        this.description = board.getDescription();
        this.viewCount = board.getViewCount();
        this.wishCount = board.getWish().stream().count();
        this.nickname = board.getUser().getNickname();
        this.category = board.getCategory().getName();
    }
}
