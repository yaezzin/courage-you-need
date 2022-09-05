package com.app.zero.dto.board;

import com.app.zero.domain.board.Board;
import com.app.zero.domain.board.BoardCategory;
import com.app.zero.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BoardRequestDto {
    private String title;
    private String description;
    private User user;
    private BoardCategory category;

    public Board toEntity() {
        return Board.builder()
                .title(title)
                .description(description)
                .user(user)
                .category(category)
                .build();
    }
}
