package com.app.zero.dto.board;


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
    private User user;
}
