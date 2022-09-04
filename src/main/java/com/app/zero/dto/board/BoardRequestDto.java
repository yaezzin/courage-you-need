package com.app.zero.dto.board;

import com.app.zero.domain.board.Board;
import com.app.zero.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BoardRequestDto {
    private String title;
    private String description;
    private User user;

    public Board toEntity() {
        return Board.builder()
                .title(title)
                .description(description)
                .user(user)
                .build();
    }
}
