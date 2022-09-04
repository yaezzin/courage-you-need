package com.app.zero.dto.board;

import com.app.zero.domain.board.Board;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BoardListResponseDto {
    private Long boardIdx;
    private String title;

    public BoardListResponseDto(Board board) {
        this.boardIdx = board.getId();
        this.title = board.getTitle();
    }
}
